# Mouse Robot 🎯🎮🖱️

## Overview 🎬🖥️📌
Mouse Robot is a Kotlin-based application that monitors mouse movement and simulates movements when the mouse has been idle for a specified duration. This can be useful for preventing system sleep or keeping an active status in various applications. 🕵️‍♂️⚡🔄

## Features 📝🖱️🔧
- 🖱️ Tracks mouse movement.
- 🔄 Simulates random mouse movements after a defined idle period.
- ⚙️ Configurable settings via a properties file.
- 📜 Supports logging, which can be enabled or disabled via command-line arguments.

## Requirements 📦🔧🖥️
- ☕ JDK 21 or later
- 🏗️ Gradle (for building the project)

## Configuration ⚙️🛠️📄
The application uses a properties file located in `config/mouse-robot.properties`. The following settings can be adjusted:

| Property | Description | Default Value |
|----------|-------------|---------------|
| `idleTime` | ⏳ Time (ms) before the robot starts moving the mouse if idle | 2000 |
| `idleCheckPeriod` | 🔍 Interval (ms) to check for mouse movement | 1000 |
| `movementDelay` | ⏱️ Delay (ms) between simulated movements | 1000 |
| `movementDistance` | 📏 Distance (px) for each simulated movement | 100 |

## Build Instructions 🏗️🔨🚀
To build the standalone JAR, run:
```sh
./gradlew clean shadowJar
```
The output JAR will be placed in:
```
build/libs/mouse-robot-1.0.jar
```

## Running the Application ▶️🖱️🏃‍♂️
### **Standard Run** 🎯💻🚀
```sh
java -jar build/libs/mouse-robot-1.0.jar
```
This will start monitoring mouse activity and simulate movements when idle. 🕵️‍♂️🖱️🔄

### **Enable Logging** 📜🖊️🔍
To enable logging, use the `--enable-logging` argument:
```sh
java -jar build/libs/mouse-robot-1.0.jar --enable-logging
```

### **Custom Configuration** ⚙️📄🛠️
Ensure `config/mouse-robot.properties` is in the same directory as the JAR and modify settings as needed. 📝🎯✅

## Notes 📌🛠️💡
- 🪟 If running on Windows, ensure the `config/` folder is in the same directory as the JAR.
- 🐧 On Linux/macOS, you may need to set execute permissions for the JAR:
  ```sh
  chmod +x build/libs/mouse-robot-1.0.jar
  ```

## License 📜🆓🤝
This project is open-source and free to use. See LICENSE for more information. 🎉👨‍💻💡

