
# Installation
## Raspberry Pi Setup
1. First things first, if you haven't yet installed NOOBS and done initial setup, [follow this tutorial.](https://www.raspberrypi.org/help/noobs-setup/2/)
1. After you have Raspbian up and running and up to date, you'll need to install a few packages and dependencies.
1. Install BlueZ 5.44 (as of 5/2/17) using `sudo apt-get install bluez`: http://www.bluez.org/download/
1. Install PyBluez using `pip install pybluez`: http://karulis.github.io/pybluez/
1. 


## Android App
### Install from APK
*Know that installing APKs from unknown sources poses a security risk. Know what you're doing and why you're doing it. I bear no responsibility if you manage to brick or otherwise damage your phone installing this or something else on your phone.*

1. To install, you'll have to side load the .apk. To enable installing apps not from the Play Store, go to Settings>Security scroll down and toggle the switch for the option "Unknown Sources". Alternatively, you can just search the settings for "unknwon sources."
1. Download the [OpenSesame.apk file](https://github.com/jeffreysdempsey/OpenSesame/blob/master/OpenSesame.apk) on your Android device.
1. Navigate to your download and tap the file to install it on your device.

###Install from source
1. On your device, go to Developer options in the Settings menu and enable USB debugging (toggle about half-way down the menu).
1. Plug in the device you want to install the app on and make sure Developer Options are enabled (Settings>About phone, scroll down to bottom, tap Build number repeatedly until a toast appears informing you that you are now a developer.)
1. Download/clone/fork this repo.
1. Open the project from Android Studio. (or Eclipse or other Java IDE if you prefer and know what you're doing. Instruction below only good for Android Studio.)
1. Go to the Run menu and and select the first option, Run 'app.' This will build the .apk and then ask you which device you want to run it on. Select the one you want. (Note, Bluetooth cannot be emulated, so you must use an actual device, not AVD.)
1. OpenSesame!

## Physical Setup
*Do all wiring before connecting your system to power.*
1. Using a [GPIO pinout diagram](https://az835927.vo.msecnd.net/sites/iot/Resources/images/PinMappings/RP2_Pinout.png) for reference, pick a pin or pins on your device to use. Which specific ones don't matter, so long as they are a GPIO and not other use pin and that they are not powered during the boot process (e.g. pins 8 & 10). I used GPIO 17 & 18, physical pins 11 & 12 for my project.
1. The relay module will have at least three pins (for a single channel), mine is a two-channel relay board, so it has four. Make sure you know which relay each GPIO pin connects to and link them with [jumper wires](https://www.amazon.com/Honbay-120pcs-Multicolored-Female-Breadboard/dp/B017NEGTXC/ref=sr_1_7?s=electronics&ie=UTF8&qid=1493767867&sr=1-7&keywords=jumper+wires). For my set up, pin 11 (GPIO 17) goes to IN1 and pin 12 (GPIO 18) goes to IN2. Colors don't matter, just make sure you know what they mean to you. Mine are white and yellow.
1. Now connect a green jumper to the GND on the relay module and pin 6 on the Pi.
1. Finally, connect a black jumper to the 5VDC pin, which is pin 2 on the Pi and to the VCC pin on the realy board. The relay is all connected to the Pi (unless you're connecting more channels.
1. Wire the relay to your garage door wall switch. This is low voltage and easier than wiring directly to the motor unit. Unless you are mounting the Pi near the motor, then do that. Either way, loosen the two screws or pop the two wire catches and insert your wires. I used some [22awg wire I purchased for the project.](https://www.amazon.com/gp/product/B00NB3SQJU/ref=oh_aui_detailpage_o03_s01?ie=UTF8&psc=1)
1. Connect the other ends of the wire to the relay. Make sure you hook them up to the normally open circuit so that toggling the relay with close the switch and deliver power to the circuit.

# Configuration
1. 

# Getting Started
1. Run the OpenSesame.py server script on the Raspberry Pi
