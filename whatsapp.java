java:
package com.example.myapplication_msg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(
                        Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                // Taking reference of Edit Text
                final EditText messageEditText = findViewById(R.id.message);

                // Taking reference to button
                Button submit = findViewById(R.id.submit);

                submit.setOnClickListener(
                                new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                                // Getting the text
                                                // from edit text
                                                String message = messageEditText
                                                                .getText()
                                                                .toString();

                                                // Calling the function
                                                // to send message
                                                sendMessage(message);
                                        }
                                });
        }

        private void sendMessage(String message) {

                // Creating new intent
                Intent intent = new Intent(
                                Intent.ACTION_SEND);

                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");

                // Give your message here
                intent.putExtra(
                                Intent.EXTRA_TEXT,
                                message);

                // Checking whether Whatsapp
                // is installed or not
                if (intent
                                .resolveActivity(
                                                getPackageManager()) == null) {
                        Toast.makeText(
                                        this,
                                        "Please install whatsapp first.",
                                        Toast.LENGTH_SHORT)
                                        .show();
                        return;
                }

                // Starting Whatsapp
                startActivity(intent);
        }
}

<?xml version="1.0"encoding="utf-8"?><LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity"android:orientation="vertical">

<EditText
        android:id="@+id/message"android:layout_width="match_parent"android:layout_height="wrap_content"android:layout_margin="16dp"android:hint="Enter you message here"android:lines="8"android:inputType="textMultiLine"android:gravity="left|top"/>

<!--
Button to
send message
on Whatsapp--><Button
        android:id="@+id/submit"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_gravity="center_horizontal"android:text="Submit"android:textColor="@android:color/white"/>

</LinearLayout>

<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools">

<uses-feature
        android:name="android.hardware.telephony"android:required="false"/>

<uses-permission android:name="android.permission.QUERY_ALL_PACKAGES"tools:ignore="QueryAllPackagesPermission"/><uses-permission android:name="android.permission.SEND_SMS"/>

<application
        android:allowBackup="true"android:dataExtractionRules="@xml/data_extraction_rules"android:fullBackupContent="@xml/backup_rules"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/Theme.MyApplicationmsg"tools:targetApi="31">

<activity
            android:name=".MainActivity"android:exported="true"><intent-filter><
action android:name="android.intent.action.MAIN"/>

<
category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest
>
