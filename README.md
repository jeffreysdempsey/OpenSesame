# OpenSesame
Garage door opener with live video feed: Android app to monitor and control garage doors.


**Executive Summary**
Have you ever needed to easily open your garage door and found yourself unable to do so? This is where OpenSesame, an in-development Android app comes in. OpenSesame allows a user to control their garage doors via bluetooth as well as visually verify the status of the garage door. There are plenty of hardware IOT and dedicated bluetooth devices with companion apps on the market which either control a garage door or provide a webcam live feed. Open Sesame does both, is open source, and for now, is DIY friendly, using a Raspberry Pi as the central control device, which the user interfaces. I will develop an Android app, that controls two garage door openers and provides a video feed to check the open/close status of the doors. I am developing this app in particular to better learn how to make a native app with a webview. The web side of the app will be served on a Raspberry Pi. The Pi will also serve as the video feed source with a camera module and serve a live video feed sans audio to the app. Additionally, the Pi will interface with the garage door openers themselves through relays (one relay per opener).

**Project Goals**
* Develop a navtive(?) Android app with a webview for a camera feed and two buttons to control for garage doors. 
* Develop an Apache Cordova server to host webview for hybrid app on the Raspberry Pi.
* Set up a Raspberry Pi to control a two-relay board that will trigger the movement of the garage door.
* Secure a class 1 Bluetooth radio against unauthorized connections.

**Project Merits**
* OpenSesame provides a free and open-source DIY solution for garage door management for makers and hobbyests.
* The app allows large households to increase the number of opener remotes beyond the 2-3 that may communicate directly with an opener as provided by the manufacturer.
* Provides an alternative to opening a garage door for those not traveling by car, allowing user to leave their keys at home and only bring their phone with them.

**Project timeline**

![Week View](https://raw.githubusercontent.com/jeffreysdempsey/OpenSesame/4142619478447a57d09b6def96f41a14c8db78ae/Screen%20Shot%202017-04-03%20at%2010.35.03%20PM.png "Gantt chart week view")

![Day View](https://raw.githubusercontent.com/jeffreysdempsey/OpenSesame/4142619478447a57d09b6def96f41a14c8db78ae/Screen%20Shot%202017-04-03%20at%2010.35.30%20PM.png "Gantt chart day view")


**Project-Oriented Risk List**
|Risk name (value)  | Impact     | Likelihood | Description | Mitigation |
|-------------------|------------|------------|-------------|------------|
|Range limit (48) | 8 | 6 | Bluetooth class 1 range is up to 100m. Garage door and other barriers will limit range. Delays development. | Test range extensively before and during development. Relocate device if necessary. Last resort: USB dongle with hi-gain antenna?|
|Discoverability (30) | 10 | 3 | Leaving device in discoverable mode leaves might allow unintended parties to open door. | Dig into Bluez (Linux Bluetooth stack) and whitelist devices. Prevent New devices from connecting. If this doesn't work, the project fails security assurance.|
|Power Interrupt (28) | 7 | 4 | Power interrupt . | Write boot-script for Pi to make sure all settings are correct. Test.|


**User stories**
  * As someone who has a garage, I want to open and close my garage door(s) using my phone, which I always have on me, instead of a dedicated remote.
  * As a worrier, I want to visually verify the status of my garage doors remotely.
  * As a forgetful person, I want to close my open garage door away from home.

**Misuser stories**
  * As an aspiring home invader, I want to connect to the opener to gain unauthorized access to the garage/home.
  * As an incompetent neighbor, I do not want, but still manage to connect to the wrong device.
  * As a power interruption or outage, I want to shut off the server and garage door motor.


**Points of Discussion**
* Video feed. Does this open the system up to more risk than I have time to handle?
* Native vs. hybrid: originally thought hybrid, but lack of on ARM64 has me leaning native with a webview component.

