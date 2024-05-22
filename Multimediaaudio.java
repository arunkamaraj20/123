activity_main.java<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"android:layout_width="match_parent"android:layout_height="match_parent"android:padding="16dp">

<Button android:id="@+id/btnPlay"android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="Play"android:onClick="onPlay"android:layout_centerHorizontal="true"android:layout_marginTop="50dp"/>

<Button android:id="@+id/btnPause"android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="Pause"android:onClick="onPause"android:layout_below="@id/btnPlay"android:layout_centerHorizontal="true"android:layout_marginTop="20dp"/>

<Button android:id="@+id/btnStop"android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="Stop"android:onClick="onStop"android:layout_below="@id/btnPause"android:layout_centerHorizontal="true"android:layout_marginTop="20dp"/>

</RelativeLayout>

MainActivity.java

package com.example.multimediab; // Replace with your actual package name

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
private boolean isPlaying = false;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);

// Initialize MediaPlayer with the audio file
mediaPlayer = MediaPlayer.create(this, R.raw.music);

// Set completion listener to release MediaPlayer resources
mediaPlayer.setOnCompletionListener(mp -> stopPlayback()); // Using lambda expression
}

public void onPlay(View view) {
if (!isPlaying) {
mediaPlayer.start();
isPlaying = true;
Toast.makeText(this, "Audio playing", Toast.LENGTH_SHORT).show();
}
}

public void onPause(View view) {
if (isPlaying) {
mediaPlayer.pause();
isPlaying = false;
Toast.makeText(this, "Audio paused", Toast.LENGTH_SHORT).show();
}
}

public void onStop(View view) {
stopPlayback();
Toast.makeText(this, "Audio stopped", Toast.LENGTH_SHORT).show();
}

private void stopPlayback() {
if (mediaPlayer != null) {
mediaPlayer.release();
mediaPlayer = null;
isPlaying = false;
}
}

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayback();
    }
}

res inside new
directory create
named as
raw and
drag and
drop music
mp3 file
there