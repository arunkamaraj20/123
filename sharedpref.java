go to res create new directory create new menu file - menu_main
xml
<?xml version="1.0" encoding="utf-8"?>

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="fill_parent"
android:layout_height="fill_parent"
android:paddingLeft="16dp"
android:paddingRight="16dp" >
<Button
android:id="@+id/btnSave"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_centerVertical="true"
android:layout_alignParentLeft="true"
android:layout_alignParentStart="true"
android:onClick="Save"
android:text="Save" />
<Button
android:id="@+id/btnRetr"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_centerHorizontal="true"
android:layout_centerVertical="true"
android:onClick="Get"
android:text="Retrieve" />
<Button
android:id="@+id/btnClear"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_alignRight="@+id/etEmail"
android:layout_centerVertical="true"
android:layout_alignParentRight="true"
android:layout_alignParentEnd="true"
android:onClick="clear"
android:text="Clear" />
<EditText
android:id="@+id/etEmail"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:ems="10"
android:hint="Email"
android:inputType="textEmailAddress"
android:layout_below="@+id/etName"
android:layout_marginTop="20dp"
android:layout_alignParentRight="true"
android:layout_alignParentEnd="true" />
<EditText
android:id="@+id/etName"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:ems="10"
android:hint="Name"
android:inputType="text"
android:layout_alignParentTop="true"
android:layout_alignLeft="@+id/etEmail"
android:layout_alignStart="@+id/etEmail" />
</RelativeLayout>

java
package com.example.shared;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends Activity {
SharedPreferences sharedpreferences;
TextView name;
TextView email;
public static final String mypreference = "mypref";
public static final String Name = "nameKey";
public static final String Email = "emailKey";
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
name = (TextView) findViewById(R.id.etName);
email = (TextView) findViewById(R.id.etEmail);
sharedpreferences = getSharedPreferences(mypreference,
Context.MODE_PRIVATE);
if (sharedpreferences.contains(Name)) {
name.setText(sharedpreferences.getString(Name, ""));
}
if (sharedpreferences.contains(Email)) {
email.setText(sharedpreferences.getString(Email, ""));
}
}
public void Save(View view) {
String n = name.getText().toString();
String e = email.getText().toString();
SharedPreferences.Editor editor = sharedpreferences.edit();
editor.putString(Name, n);
editor.putString(Email, e);
editor.commit();
}
public void clear(View view) {
name = (TextView) findViewById(R.id.etName);
email = (TextView) findViewById(R.id.etEmail);
name.setText("");
email.setText("");
}
public void Get(View view) {
name = (TextView) findViewById(R.id.etName);
email = (TextView) findViewById(R.id.etEmail);
sharedpreferences = getSharedPreferences(mypreference,
Context.MODE_PRIVATE);
if (sharedpreferences.contains(Name)) {
name.setText(sharedpreferences.getString(Name, ""));
}
if (sharedpreferences.contains(Email)) {
email.setText(sharedpreferences.getString(Email, ""));
}
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {

getMenuInflater().inflate(R.menu.menu_main, menu);
return true;
}
}
