# #To verify the Arduino was correctly sending data and that the Raspberry Pi was receiving and interpreting it accurately, we used the Linux tool bluetoothctl. This allowed us to scan for nearby Bluetooth devices and see their MAC addresses. Once we identified our HC-05 module — which was connected to the Arduino — we paired it to the Raspberry Pi.

# After pairing, we wrote a Python script that used the PyBluez library to create an RFCOMM connection with the HC-05. The Arduino was sending a 6-byte buffer over Bluetooth — each biometric metric was serialized as a 16-bit integer split into a low byte and high byte. On the Pi side, we decoded the incoming data using int.from_bytes() with little-endian format to reconstruct the original heart rate, SpO2, and GSR values.

# We then uploaded the parsed data to Firebase Realtime Database using the Firebase Admin SDK, allowing us to display the values in real time through a mobile app. We also added checks to make sure exactly 6 bytes were received before decoding, and wrapped the Bluetooth and Firebase logic in try-except blocks for robustness.

import bluetooth
import firebase_admin
from firebase_admin import credentials, db

# Initialize Firebase Admin SDK
cred = credentials.Certificate("/home/yapiyapo/Downloads/google-services.json")
firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://pmd-android-app-default-rtdb.firebaseio.com'
})

# HC-05 Bluetooth address
bluetooth_addr = "00:22:02:01:08:76"
bluetooth_port = 1  # Default RFCOMM port

try:
    # Connect to the HC-05 module
    bluetooth_socket = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
    bluetooth_socket.connect((bluetooth_addr, bluetooth_port))
    print("Connected to HC-05 module.")

    while True:
        try:
            received_data = bluetooth_socket.recv(1024)
            if len(received_data) == 6:
                bpm = int.from_bytes(received_data[0:2], byteorder='little')
                blood_oxygen = int.from_bytes(received_data[2:4], byteorder='little')
                perspiration = int.from_bytes(received_data[4:6], byteorder='little')

                print(f"BPM: {bpm}, SpO2: {blood_oxygen}, GSR: {perspiration}")

                db.reference('sensor_data').push({
                    'bpm': bpm,
                    'blood_oxygen': blood_oxygen,
                    'perspiration': perspiration
                })
                print("Data uploaded to Firebase.")
        except bluetooth.btcommon.BluetoothError as be:
            print("Bluetooth error:", be)
            break
except bluetooth.btcommon.BluetoothError as e:
    print("Bluetooth connection error:", e)
except KeyboardInterrupt:
    print("Keyboard interrupt detected. Closing connection.")
finally:
    bluetooth_socket.close()
