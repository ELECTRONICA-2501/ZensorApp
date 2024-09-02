# Zensor - Personalized Meditation Device

**Team Members:** Salvador Jimenez, YoungJo Choi, Fayez Ghosein, Bhuvanesh Rajagopal  
**Instructor:** Dr. Jafar Saniie  
**Teaching Assistants:** Xinrui Yu, Mikhail Gromov  
**Course:** ECE 441 Spring 2024  

---

## Overview

Zensor is a personalized meditation device designed to help users manage their emotional well-being. The device uses bio-data to predict the user's emotional state and provides customized meditation sessions to help users overcome negative emotions.

### Key Features
- **Emotion Prediction:** Uses sensors to collect bio-data and predicts the user's emotional state using a machine learning model.
- **Personalized Meditation:** Based on the predicted emotion, the device provides a tailored meditation session.
- **Real-Time Feedback:** The device continuously monitors and updates the user's emotional state, ensuring a responsive meditation experience.
- **Customizable Platform:** Built with Arduino and Raspberry Pi, allowing for further customization by users and developers.

## Technical Overview

### Hardware Architecture: 
- **Sensors:** MAX30102 Pulse Oximeter and Grove GSR Sensor for collecting biometric data.
- **Microcontrollers:** Arduino Pro Mini and Raspberry Pi for processing and transmitting data.
- **Communication:** HC-06 Bluetooth module for data transmission between devices.
- **Housing:** 3D printed casing designed to comfortably fit all hardware components.

### Software Components
- **Meditation Algorithm:** Machine learning model (Decision Tree) trained on biometric data to predict emotional states.
- **Firebase Integration:** Used for real-time data storage and user authentication.
- **Android Application:** User interface for starting meditation sessions, viewing data, and managing user accounts.

## Team Contributions

- **Salvador Jimenez:** Led software and mobile application development, designed the meditation algorithm, and developed the connection between Firebase and the device.
- **YoungJo Choi:** Developed the machine learning model, performed research on various algorithms, and integrated the ML functionality with Firebase and the Android application.
- **Fayez Ghosein:** Co-lead on hardware development, responsible for soldering and wiring components, and designing and printing the 3D housing.
- **Bhuvanesh Rajagopal:** Co-lead on hardware development, developed firmware for Arduino and Raspberry Pi, and assisted with Android application testing and debugging.

## Installation and Setup

### Prerequisites
- Arduino IDE
- Python 3.x
- Firebase Account and Project Setup
- Android Studio for the mobile app

### Steps to Set Up

1. **Hardware Setup:**
   - Assemble the sensors (MAX30102 and Grove GSR) and microcontrollers (Arduino Pro Mini and Raspberry Pi) as per the provided circuit diagram.
   - Ensure all components are securely fitted into the 3D-printed housing.

2. **Software Setup:**
   - Clone the repository:
     ```bash
     git clone https://github.com/ELECTRONICA-2501/ZensorApp.git
     cd ZensorApp
     ```
   - Install the required Python libraries:
     ```bash
     pip install -r requirements.txt
     ```
   - Set up Firebase:
     - Generate and download a Firebase private key JSON file and place it in the project directory.
     - Update the Firebase configuration in the Python scripts and Android app with your project's credentials.

3. **Running the Device:**
   - Upload the Arduino firmware to the Arduino Pro Mini.
   - Run the Python scripts on the Raspberry Pi to handle data collection and transmission to Firebase.

4. **Using the Android App:**
   - Open the Android project in Android Studio.
   - Build and run the app on a connected Android device.
   - Log in or create an account to start using the meditation device.

## Usage

1. **Starting a Training Session:**
   - Select the "Start Training Session" button on the app dashboard.
   - Follow the prompts to input your current emotional state while the device collects bio-data.

2. **Starting a Meditation Session:**
   - Select the "Start Meditation Session" button.
   - The device will automatically predict your emotional state and provide a guided meditation session.

3. **Viewing Results:**
   - Access real-time data and meditation history directly from the app dashboard.

## Future Work

- **User Experience Improvements:** Direct communication between the Arduino Pro Mini and the Android app to eliminate the need for a Raspberry Pi.
- **Advanced Sensors:** Incorporating EEG sensors for more accurate emotional state detection.
- **Cross-Platform Support:** Expanding the app to iOS to reach a broader user base.

## Acknowledgments

We would like to thank Dr. Jafar Saniie, Xinrui Yu, and Mikhail Gromov for their guidance and support throughout this project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

For more information, please visit our [GitHub Repository](https://github.com/ELECTRONICA-2501/ZensorApp).

