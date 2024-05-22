activity_main.xml

<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".WifiActivity">

<TextView android:id="@+id/wifi_status_text_view"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_centerHorizontal="true"android:layout_marginTop="100dp"android:text="Wi-Fi Status"android:textSize="24sp"/>

<Button android:id="@+id/enable_wifi_button"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_below="@id/wifi_status_text_view"android:layout_centerHorizontal="true"android:layout_marginTop="50dp"android:text="Enable Wi-Fi"/>

<Button android:id="@+id/disable_wifi_button"android:layout_width="wrap_content"android:layout_height="wrap_content"android:layout_below="@id/enable_wifi_button"android:layout_centerHorizontal="true"android:layout_marginTop="20dp"android:text="Disable Wi-Fi"/>

</RelativeLayout>

MainActivity.java
package com.example.wifihardware;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private TextView wifiStatusTextView;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);

wifiStatusTextView = findViewById(R.id.wifi_status_text_view);
Button enableWifiButton = findViewById(R.id.enable_wifi_button);
Button disableWifiButton = findViewById(R.id.disable_wifi_button);

wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

enableWifiButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
if (wifiManager.isWifiEnabled()) {
wifiStatusTextView.setText("Wi-Fi is already Enabled");
} else {
if (wifiManager.setWifiEnabled(true)) {
wifiStatusTextView.setText("Wi-Fi Enabled");
} else {
wifiStatusTextView.setText("Failed to enable Wi-Fi");
}
}
}
});

disableWifiButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
if (!wifiManager.isWifiEnabled()) {
wifiStatusTextView.setText("Wi-Fi is already Disabled");
} else {
if (wifiManager.setWifiEnabled(false)) {
wifiStatusTextView.setText("Wi-Fi Disabled");
} else {
wifiStatusTextView.setText("Failed to disable Wi-Fi");
}
}
}
});
}
}AndroidManifest

<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools"><uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/><uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>

<application
android:allowBackup="true"android:dataExtractionRules="@xml/data_extraction_rules"android:fullBackupContent="@xml/backup_rules"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/Theme.Wifihardware"tools:targetApi="31"><
activity android:name=".MainActivity"android:exported="true"android:label="@string/app_name"android:theme="@style/Theme.Wifihardware"><intent-filter><
action android:name="android.intent.action.MAIN"/>

<
category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest
>