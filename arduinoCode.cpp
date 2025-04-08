// Bluetooth + biomedical data pipeline

#include <Wire.h>
#include "MAX30105.h"
#include "spo2_algorithm.h"
#include "heartRate.h"
#include <SoftwareSerial.h>

SoftwareSerial bluetooth(2, 3);
MAX30105 particleSensor;

#define MAX_BRIGHTNESS 255

#if defined(_AVR_ATmega328P_) || defined(_AVR_ATmega168_)
// Arduino Uno doesn't have enough SRAM to store 100 samples of IR led data and red led data in 32-bit format
// To solve this problem, 16-bit MSB of the sampled data will be truncated. Samples become 16-bit data.
uint16_t irBuffer[30]; // infrared LED sensor data
uint16_t redBuffer[30]; // red LED sensor data
#else
uint32_t irBuffer[100]; // infrared LED sensor data
uint32_t redBuffer[100]; // red LED sensor data
#endif

int32_t bufferLength = 30; // data length
int32_t spo2; // SPO2 value
int8_t validSPO2; // indicator to show if the SPO2 calculation is valid
int32_t heartRate; // heart rate value
int8_t validHeartRate; // indicator to show if the heart rate calculation is valid

byte readLED = 13; // Blinks with each data read
const byte RATE_SIZE = 10; // Increase this for more averaging. 4 is good.
byte rates[RATE_SIZE]; // Array of heart rates
byte rateSpot = 0;
long lastBeat = 0; // Time at which the last beat occurred
float beatsPerMinute;
int beatAvg;

const int GSR = A1;
int sensorValue = 0;
int gsr_average = 0;

void setup() {
    Serial.begin(115200); // initialize serial communication
    bluetooth.begin(115200);
    pinMode(readLED, OUTPUT);

    // Initialize sensor
    if (!particleSensor.begin(Wire, I2C_SPEED_FAST)) {
        Serial.println(F("MAX30105 was not found. Please check wiring/power."));
        while (1);
    }

    Serial.println(F("Attach sensor to finger with rubber band. Press any key to start conversion"));
    Serial.read();

    byte ledBrightness = 60;
    byte sampleAverage = 2;
    byte ledMode = 2;
    byte sampleRate = 200;
    int pulseWidth = 118;
    int adcRange = 8192;

    particleSensor.setup(ledBrightness, sampleAverage, ledMode, sampleRate, pulseWidth, adcRange);
}

void loop() {
    long sumGSR = 0;

    // Read the first 30 samples
    for (byte i = 0; i < bufferLength; i++) {
        while (particleSensor.available() == false)
            particleSensor.check();

        long irVal = particleSensor.getIR();
        long redVal = particleSensor.getRed();

        redBuffer[i] = redVal;
        irBuffer[i] = irVal;

        if (checkForBeat(irVal)) {
            long delta = millis() - lastBeat;
            lastBeat = millis();
            beatsPerMinute = 60 / (delta / 1000.0);

            if (beatsPerMinute < 255 && beatsPerMinute > 20) {
                rates[rateSpot++] = (byte)beatsPerMinute;
                rateSpot %= RATE_SIZE;
            }
        }

        particleSensor.nextSample();
        sensorValue = analogRead(GSR);
        sumGSR += sensorValue;
        delay(5);
    }

    maxim_heart_rate_and_oxygen_saturation(irBuffer, bufferLength, redBuffer, &spo2, &validSPO2, &heartRate, &validHeartRate);

    beatAvg = 0;
    for (byte x = 0; x < RATE_SIZE; x++) beatAvg += rates[x];
    beatAvg /= RATE_SIZE;
    gsr_average = sumGSR / 50;

    // Continuous reading loop
    while (1) {
        for (byte i = bufferLength / 2; i < bufferLength; i++) {
            redBuffer[i - bufferLength / 2] = redBuffer[i];
            irBuffer[i - bufferLength / 2] = irBuffer[i];
        }

        sumGSR = 0;

        for (byte i = bufferLength / 2; i < bufferLength; i++) {
            while (particleSensor.available() == false)
                particleSensor.check();

            digitalWrite(readLED, !digitalRead(readLED));

            long irVal = particleSensor.getIR();
            long redVal = particleSensor.getRed();

            redBuffer[i] = redVal;
            irBuffer[i] = irVal;

            if (checkForBeat(irVal)) {
                long delta = millis() - lastBeat;
                lastBeat = millis();
                beatsPerMinute = 60 / (delta / 1000.0);

                if (beatsPerMinute < 255 && beatsPerMinute > 20) {
                    rates[rateSpot++] = (byte)beatsPerMinute;
                    rateSpot %= RATE_SIZE;
                }
            }

            particleSensor.nextSample();
            sensorValue = analogRead(GSR);
            sumGSR += sensorValue;
            delay(5);

            if (validSPO2 && heartRate > 20) {
                int value1 = (int)beatAvg;
                int value2 = (int)spo2;
                int value3 = (int)gsr_average;

                Serial.print("value1=");
                Serial.print(value1);
                Serial.print(", value2=");
                Serial.print(value2);
                Serial.print(", value3=");
                Serial.println(value3);

                byte buffer[6];
                buffer[0] = lowByte(value1);
                buffer[1] = highByte(value1);
                buffer[2] = lowByte(value2);
                buffer[3] = highByte(value2);
                buffer[4] = lowByte(value3);
                buffer[5] = highByte(value3);

                bluetooth.write(buffer, sizeof(buffer));
            }
        }

        // Recalculate HR and SpO2 after every batch of 25 samples
        maxim_heart_rate_and_oxygen_saturation(irBuffer, bufferLength, redBuffer, &spo2, &validSPO2, &heartRate, &validHeartRate);

        beatAvg = 0;
        for (byte x = 0; x < RATE_SIZE; x++) beatAvg += rates[x];
        beatAvg /= RATE_SIZE;
        gsr_average = sumGSR / 25;
    }
}
