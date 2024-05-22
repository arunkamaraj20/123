<?xml version="1.0"encoding="utf-8"?>

<menu xmlns:android="http://schemas.android.com/apk/res/android"

xmlns:app="http://schemas.android.com/apk/res-auto"

xmlns:actionProviderClass="http://schemas.android.com/tools">

<item

android:title="Logout"

android:icon="@drawable/ic_launcher_background"

android:id="@+id/log"/>

<item

android:title="Settings"

android:icon="@drawable/ic_launcher_background"

android:id="@+id/settings"/>

</menu>

package com.example.menus;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Menu;

import android.view.MenuInflater;

import android.view.MenuItem;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

 @Override 

 protected void onCreate(Bundle savedInstanceState) { 

 super.onCreate(savedInstanceState); 

 setContentView(R.layout.activity_main); 

 }

 @Override 

 public boolean onCreateOptionsMenu(Menu menu) { 

 MenuInflater inflater=getMenuInflater(); 

 inflater.inflate(R.menu.menu,menu); 

 return true; 

 }

 @Override 

 public boolean onOptionsItemSelected(@NonNull MenuItem item) { 

 int id = item.getItemId(); 

 if (id == R.id.log) { 

 Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show(); 

 return true; 

 } else if (id == R.id.settings) { 

 Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show(); 

 return true; 

 } else { 

 return super.onOptionsItemSelected(item); 

 } 

 }

}
