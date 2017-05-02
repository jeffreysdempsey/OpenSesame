package com.jeffdempsey.opensesame;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

public class MainActivity extends Activity {
    private static final String TAG = "OpenSesame";

    Button doorBig, doorLittle;

    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;

    //Set UUID (generated in macOS)
    private static final UUID MY_UUID = UUID.fromString("5BAE33F0-1E5F-4439-A084-C84332D49D04");

    //onCreate method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        doorBig = (Button) findViewById(R.id.ButtonBig);
        doorLittle = (Button) findViewById(R.id.ButtonLittle);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBTState();

        doorBig.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                sendData("1"); //hardcoded 1 to toggle the door
                Toast.makeText(getBaseContext(), "Toggle Main Door", Toast.LENGTH_SHORT).show();
            }
        });

        doorLittle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("2"); //hardcoded 2 to toggle the second door
                Toast.makeText(getBaseContext(),"Toggle Secondary Door", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //create Bluetooth connection
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        try {
            final Method  m = device.getClass().getMethod("createInsecureRFcommSocketToServiceRecord", new Class[] { UUID.class });
            return (BluetoothSocket) m.invoke(device, MY_UUID);
        } catch (Exception e) {
            Log.e(TAG, "Could not create Insecure RFComm Connection",e);
        }
        return  device.createRfcommSocketToServiceRecord(MY_UUID);
    }

    //onResume
    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "...onResume - try connect...");

        String address = "B8:27:EB:A1:B2:0C";
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e1) {
            errorExit("Error", "In onResume() and socket create failed: " + e1.getMessage() + ".");
        }

        //Make sure discovery isn't running
        btAdapter.cancelDiscovery();

        //Establish connection
        Log.d(TAG, "...Connecting...");
        try {
            btSocket.connect();
            Log.d(TAG, "...Connection ok...");
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                errorExit("Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }

        //Create data stream
        Log.d(TAG, "...Create Socket...");

        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
            errorExit("Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
        }
    }

    //onPause
    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "...In onPause()...");

        if (outStream != null) {
            try {
                outStream.flush();
            } catch (IOException e) {
                errorExit("Error", "In onPause() and failed to flush output stream: " + e.getMessage() + ".");
            }
        }

        try     {
            btSocket.close();
        } catch (IOException e2) {
            errorExit("Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }
    }

    //check state of Bluetooth
    private void checkBTState() {
        if(btAdapter==null) {
            errorExit("Error", "Bluetooth not supported on this device.");
        } else {
            if (btAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth ON...");
            } else {
                //Prompt user
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    //Exit in case of error
    private void errorExit(String title, String message){
        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        finish();
    }

    //Send the data, handle IO errors
    private void sendData(String message) {
        byte[] msgBuffer = message.getBytes();

        Log.d(TAG, "...Send data: " + message + "...");

        try {
            outStream.write(msgBuffer);
        } catch (IOException e) {
            String msg = "IO exception when writing out the message buffer.";
            errorExit("Error: ", msg);
        }
    }
}