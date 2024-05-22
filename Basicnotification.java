AndroidManifest<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools">

<application android:allowBackup="true"android:dataExtractionRules="@xml/data_extraction_rules"android:fullBackupContent="@xml/backup_rules"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/Theme.Dialogandnoti"tools:targetApi="31">

<activity android:name=".MainActivity"android:exported="true"><intent-filter><action android:name="android.intent.action.MAIN"/><category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest>MainActivity
package com.example.dialogandnoti;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "channel_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextName = findViewById(R.id.editText1);
        EditText editTextStoreName = findViewById(R.id.editText11);
        EditText editTextAge = findViewById(R.id.editText2);
        EditText editTextPhoneNumber = findViewById(R.id.editText3);
        EditText editTextLocation = findViewById(R.id.editText4);
        Button buttonSave = findViewById(R.id.button);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String storeName = editTextStoreName.getText().toString();
                String age = editTextAge.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String location = editTextLocation.getText().toString();

                showNotification("Profile Added", "Name: " + name + "\nStore Name: " + storeName + "\nAge: " + age
                        + "\nPhone Number: " + phoneNumber + "\nLocation: " + location);
            }
        });

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}activity_main.xml<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"android:paddingLeft="16dp"android:paddingTop="16dp"android:paddingRight="16dp"android:paddingBottom="16dp"tools:context=".MainActivity">

<TextView
        android:id="@+id/textView"android:layout_width="match_parent"android:layout_height="wrap_content"android:layout_marginTop="16dp"android:text="Store Owner Profile"android:textAlignment="center"android:textColor="#9C27B0"android:textSize="34sp"/>

<
EditText android:id="@+id/editText1"android:layout_width="match_parent"android:layout_height="48dp"android:layout_below="@+id/textView"android:layout_marginTop="36dp"android:hint="Enter your name"/>

<
EditText android:id="@+id/editText11"android:layout_width="match_parent"android:layout_height="48dp"android:layout_below="@id/editText1"android:layout_marginTop="16dp"android:hint="Store Name"android:textColorLink="#090909"/>

<
EditText android:id="@+id/editText2"android:layout_width="match_parent"android:layout_height="48dp"android:layout_below="@id/editText11"android:layout_marginTop="19dp"android:hint="Age"/>

<
EditText android:id="@+id/editText3"android:layout_width="match_parent"android:layout_height="48dp"android:layout_below="@id/editText2"android:layout_marginTop="36dp"android:hint="Phone Number"/>

<
EditText android:id="@+id/editText4"android:layout_width="match_parent"android:layout_height="48dp"android:layout_below="@id/editText3"android:layout_marginTop="36dp"android:hint="Location"/>

<
Button android:id="@+id/button"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_below="@id/editText4"android:layout_marginTop="36dp"android:layout_centerHorizontal="true"android:text="Save Details"/></RelativeLayout
>
