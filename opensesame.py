#OpenSesame Pi-side script by Jeff Dempsey
#adapted from David Vassalo's script aquaPi.py at
#https://gist.github.com/dvas0004/84209b67ff556cb18651d#file-aquapi-py



import os
import time
import RPi.GPIO as GPIO
from bluetooth import *

#OS level setup
os.system('sudo modprobe w1-gpio') #setup gpio module on kernel
os.system('sudo rfkill unblock bluetooth') #turn on bluetooth adapter
os.system('sudo hciconfig hci0 piscan') #enter discoverable mode

#setup gpio pins
GPIO.setmode(GPIO.BCM)
GPIO.setup(17, GPIO.OUT, initial=GPIO.HIGH) #for main door, initialize gpio17 to off, circuit normally open
GPIO.setup(18, GPIO.OUT, initial=GPIO.HIGH) #for future second door, initialize gpio18 to off, circuit normally open

#setup rfcomm listener
server_sock=BluetoothSocket( RFCOMM )
server_sock.bind(("",PORT_ANY))
server_sock.listen(1)

port = server_sock.getsockname()[1]

uuid = "5BAE33F0-1E5F-4439-A084-C84332D49D04" #uuid generated with macOS uuid bash command


advertise_service( server_sock, "OpenSesame",
        service_id = uuid,
        service_classes = [ uuid, SERIAL_PORT_CLASS ],
        profiles = [ SERIAL_PORT_PROFILE ])


#loop to control connection, data transfer, proper cleanup, and handle errors
running = True

print ("Waiting for connection on RFCOMM channel %d" % port)

client_sock, client_info = server_sock.accept()
print ("Accepted connection from ", client_info)

while running:                
        try:

                data = client_sock.recv(1024)
                if len(data) == 0: break
                print ("received [%s]" % data)

                #if data is 1, toggle relay 1 closed, then open after 0.5s
                if data == '1':
                        print "Toggle relay 1 on then off."
                        GPIO.output(17,GPIO.LOW)
                        time.sleep(0.5)
                        GPIO.output(17,GPIO.HIGH)
                
                #if data is 2, toggle relay 2 closed, then open after 0.5s
                if data == '2':
                        print "Toggle relay 2 on then off."
                        GPIO.output(18,GPIO.LOW)
                        time.sleep(0.5)
                        GPIO.output(18,GPIO.HIGH)

                else:
                        #something strange is going on
                        print "Invalid input."
                        data = "I have no idea what is going on."
                        
                client_sock.send(data)
                print ("sending [%s]" % data)

        except IOError:
                print "IO error"
                GPIO.setmode(GPIO.BCM)
                GPIO.setup(17, GPIO.OUT, initial=GPIO.LOW)
                GPIO.output(17, GPIO.LOW)
                GPIO.cleanup()
                pass

        except KeyboardInterrupt:
                print "keyboard interrupt"
                #GPIO.output(17, GPIO.LOW)
                client_sock.close()
                server_sock.close()
                GPIO.cleanup()
                print "disconnected"

                running = False
                
