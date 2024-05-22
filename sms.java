activity_main.xml

<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"android:padding="16dp"tools:context=".MainActivity">

<EditText android:id="@+id/editText"android:layout_width="match_parent"android:layout_height="wrap_content"android:hint="Phone Number"/>

<EditText android:id="@+id/editText2"android:layout_below="@id/editText"android:layout_width="match_parent"android:layout_height="wrap_content"android:hint="Message"/>

<Button android:id="@+id/button"android:layout_below="@id/editText2"android:layout_width="match_parent"android:layout_height="wrap_content"android:text="Send Message"/>

</RelativeLayout>

MainActivity.java
package com.example.smsphonenumber;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private EditText txtPhoneNo;
    private EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPhoneNo = findViewById(R.id.editText);
        txtMessage = findViewById(R.id.editText2);
        Button sendButton = findViewById(R.id.button);

        sendButton.setOnClickListener(v -> sendSMSMessage());
    }

    protected void sendSMSMessage() {
        String phoneNo = txtPhoneNo.getText().toString();
        String message = txtMessage.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
                // Show rationale with a Toast message
                Toast.makeText(this, "Please allow this app to send SMS messages so it can function properly.",
                        Toast.LENGTH_LONG).show();
            } else {
                // Request the permission directly
                ActivityCompat.requestPermissions(this,
                        new String[] { Manifest.permission.SEND_SMS },
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        } else {
            // Permission is already granted, proceed with sending SMS
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(txtPhoneNo.getText().toString(), null, txtMessage.getText().toString(), null,
                        null);
                Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            }
        }
    }
}

<uses-

permission android:name="android.permission.SEND_SMS"/><uses-permission android:name="android.permission.READ_PHONE_STATE"/
>
