# ZensorApp

ZensorApp is an Android application designed to monitor and record users' emotional states and physiological data. The app integrates with Firebase for authentication and data storage, allowing users to sign up, log in, and record their emotional states along with sensor data such as heart rate, oxygen level, and perspiration.

## Features

- User Authentication (Sign Up and Log In)
- Record and store emotional states
- Monitor and display physiological data
- Firebase integration for real-time data storage and retrieval

## Getting Started

### Prerequisites

- Android Studio
- Firebase account
- Basic knowledge of Android development and Firebase

### Installation

1. **Clone the repository:**

    ```sh
    git clone https://github.com/yourusername/zensorapp.git
    cd zensorapp
    ```

2. **Open the project in Android Studio:**

    - Launch Android Studio
    - Select `Open an existing Android Studio project`
    - Navigate to the cloned repository and select it

3. **Set up Firebase:**

    - Go to the [Firebase Console](https://console.firebase.google.com/)
    - Create a new project or use an existing one
    - Add an Android app to your Firebase project
    - Register your app with the package name `com.example.zensorapp`
    - Download the `google-services.json` file and place it in the `app` directory of your project
    - Follow the instructions to add Firebase SDK to your project

4. **Build and run the project:**

    - Connect your Android device or start an emulator
    - Click on the `Run` button in Android Studio

## Project Structure

### Main Activities

#### SignUpActivity

Handles user registration and authentication.
