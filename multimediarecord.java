activity_main.xml

<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"android:layout_width="match_parent"android:layout_height="match_parent">

<VideoView android:id="@+id/videoView3"android:layout_width="match_parent"android:layout_height="match_parent"/>

<Button android:id="@+id/buttonRecord"android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="Record Video"android:layout_centerInParent="true"android:onClick="recordvideo"/>

</RelativeLayout>

MainActivity.java

package com.example.multimediaapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoview;
    int REQUEST_CODE_VIDEO_CAPTURE = 2607;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoview = findViewById(R.id.videoView3);
        MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoview);
        videoview.setMediaController(mediacontroller);
    }

    public void recordvideo(View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videouri = data.getData();
            videoview.setVideoURI(videouri);
            videoview.start();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

AndroidManifest

<?xml version="1.0"encoding="utf-8"?><manifest xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools"><uses-permission android:name="android.permission.CAMERA"/><uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/><uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/><uses-permission android:name="android.permission.RECORD_AUDIO"/><uses-permission android:name="android.permission.READ_PHONE_STATE"/><uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/><uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/><uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/><uses-permission android:name="android.permission.BLUETOOTH"/><uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/><uses-permission android:name="android.permission.SEND_SMS"/><uses-permission android:name="android.permission.POST_NOTIFICATIONS"/><uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/><uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/><uses-
permission android:name="android.permission.INTERNET"/><
application android:allowBackup="true"android:dataExtractionRules="@xml/data_extraction_rules"android:fullBackupContent="@xml/backup_rules"android:icon="@mipmap/ic_launcher"android:label="@string/app_name"android:roundIcon="@mipmap/ic_launcher_round"android:supportsRtl="true"android:theme="@style/Theme.MultimediaApplication"tools:targetApi="31"><
activity android:name=".MainActivity"android:exported="true"android:label="@string/app_name"android:theme="@style/Theme.MultimediaApplication"><intent-filter><
action android:name="android.intent.action.MAIN"/>

<
category android:name="android.intent.category.LAUNCHER"/></intent-filter></activity></application>

</manifest
>