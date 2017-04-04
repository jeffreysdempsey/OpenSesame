# OpenSesame
Garage door opener with video feed: hybrid Ember-Cordova app with video feed and Raspberry Pi server.

**Executive Summary**
1. A problem statement identifying the problem your product addresses.
2. Project goals and objectives in a numbered or bulleted list that you will undertake to address the identified problem.
3. The merit of accomplishing the project goals and objectives in terms of how it helps end users, society, or particular industries/sectors.

Have you ever needed to easily open your garage door and found yourself unable to do so? This is where Open Sesame, an in-development Android app comes in. Open Sesame allows a user to control their garage doors via bluetooth as well as visually verify the status of the garage door. There are plenty of hardware IOT and dedicated bluetooth devices with companion apps on the market which either control a garage door or provide a webcam live feed. Open Sesame does both, is open source, and for now, is DIY friendly, using a Raspberry Pi as the central control device, which the user interfaces.

I will develop an Android app, that controls two garage door openers and provides a video feed to check the open/close status of the doors. I am developing this app in particular to better learn how to make a hybrid Ember-Cordova app. The web side of the app will be served on a Raspberry Pi. The Pi will also serve as the video feed source with a camera module and serve a live video feed sans audio to the app. Additionally, the Pi will interface with the garage door openers themselves through relays (one relay per opener).
Develop an Apache Cordova server to host webview for hybrid app on the Raspberry Pi.


**Project timeline**
![Week View](OpenSesame/https://github.com/jeffreysdempsey/OpenSesame/commit/4142619478447a57d09b6def96f41a14c8db78ae#diff-57021e0c754fef1c38be96997c78a15b "Gantt chart week view")
![Day View](https://github.com/jeffreysdempsey/OpenSesame/commit/4142619478447a57d09b6def96f41a14c8db78ae#diff-e6a43a8738f2ba343e731c63694f6778 "Gantt chart day view")


**Project-Oriented Risk List**

|Risk name (value)  | Impact     | Likelihood | Description | Mitigation |
|-------------------|------------|------------|-------------|------------|
|Range limit (40) | 8 | 6 | Bluetooth class 1 range is up to 100m. Garage door and other barriers will limit range. Delays development. | Test range extensively before and during development. Relocate device if necessary. Last resort: USB dongle with hi-gain antenna?|
|Discoverability (40) | 10 | 3 | Leaving device in discoverable mode leaves might allow unintended parties to open door. | Dig into Bluez (Linux Bluetooth stack) and whitelist devices. Prevent New devices from connecting. If this doesn't work, the project fails security assurance.|
|Power Interrupt (40) | 7 | 4 | Power interrupt . | Write boot-script for Pi to make sure all settings are correct. Test.|


**User stories**
  * As someone who has a garage, I want to open and close my garage door(s) using my phone, which I always have on me, instead of a dedicated remote.
  * As a worrier, I want to visually verify the status of my garage doors remotely.
  * As a forgetful person, I want to close my open garage door away from home.

**Misuser stories**
  * As an aspiring home invader, I want to connect to the opener to gain unauthorized access to the garage/home.
  * As an incompetent neighbor, I do not want, but still manage to connect to the wrong device.
  * As a power interruption or outage, I want to shut off the server and garage door motor.

**Discussion - Talk to Dr. Hale about your idea**
To Discuss:
 * Bluetooth integration.
 * Authenticate pairing?
 * Automatic pairing?

