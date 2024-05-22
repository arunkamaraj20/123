java
package com.example.animationapp;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextView
        TextView movingTextView = findViewById(R.id.movingTextView);

        // Create an ObjectAnimator to move the TextView horizontally
        ObjectAnimator animator = ObjectAnimator.ofFloat(movingTextView, "translationX", 0f, 500f);
        animator.setDuration(3000); // Set duration in milliseconds (3 seconds)
        animator.setRepeatCount(ObjectAnimator.INFINITE); // Repeat the animation infinitely
        animator.setRepeatMode(ObjectAnimator.REVERSE); // Reverse the animation direction on each repeat

        // Start the animation
        animator.start();
    }
}xml<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity">

<TextView
        android:id="@+id/movingTextView"android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="Move Me!"android:textSize="24sp"android:layout_centerInParent="true"android:background="@android:color/holo_blue_light"android:padding="16dp"/>

</RelativeLayout
>
