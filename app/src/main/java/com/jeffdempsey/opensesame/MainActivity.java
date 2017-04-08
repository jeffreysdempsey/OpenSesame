package com.jeffdempsey.opensesame;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTaskDescription(
                    new ActivityManager.TaskDescription(
                            null, // Leave the default title.
                            R.drawable.,
                            null // Leave the default color
                    )
        }

    }

    public void button1onClick(View view) {
        Toast.makeText(this, "Door 1 Toggled", Toast.LENGTH_SHORT).show();
    }

    public void button2onClick(View view) {
        Toast.makeText(this, "Door 2 Toggled", Toast.LENGTH_SHORT).show();
    }
}