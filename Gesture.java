activity_main.xml<?xml version="1.0"encoding="utf-8"?><LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"android:layout_width="match_parent"android:layout_height="match_parent"android:gravity="center"android:orientation="vertical">

<TextView android:id="@+id/gestureText"android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="Gesture Text"/>

</LinearLayout>

MainActvity.java

package com.example.hardwaregeature;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView gestureText;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureText = findViewById(R.id.gestureText);
        gestureDetector = new GestureDetector(this, new GestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            gestureText.setText("onDown");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            gestureText.setText("onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            gestureText.setText("onFling");
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            gestureText.setText("onSingleTapConfirmed");
            return true;
        }
    }
}

AndroidManifest

<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools">

<uses-permission android:name="android.permission.CAMERA"/><uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/><uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/><uses-permission android:name="android.permission.RECORD_AUDIO"/><uses-permission android:name="android.permission.READ_PHONE_STATE"/><uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/><uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/><uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/><uses-permission android:name="android.permission.BLUETOOTH"/><uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/><uses-permission android:name="android.permission.POST_NOTIFICATIONS"/><uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/><uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/><uses-permission android:name="android.permission.INTERNET"/><uses-permission android:name="android.permission.SEND_SMS"/>

<application
        android:allowBackup="true"android:dataExtractionRules="@xml/data_extraction_rules"android:fullBackupContent="@xml/backup_rules"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/Theme.HardwareGeature"tools:targetApi="31"><
activity android:name=".GestureDetector"android:exported="false"/><
activity android:name=".MainActivity"android:exported="true"android:label="@string/app_name"android:theme="@style/Theme.HardwareGeature"><intent-filter><
action android:name="android.intent.action.MAIN"/>

<
category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest
>