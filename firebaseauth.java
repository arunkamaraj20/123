MainActivity.java package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.ktx.Firebase;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button btnLogout;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btnLogout = findViewById(R.id.btnlogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                logout();

            }
        });
    }

    @Override

    public void onStart() {

        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null)

        {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));

        }
    }

    public void logout() {

        FirebaseAuth.getInstance().signOut();

        startActivity(new Intent(MainActivity.this, LoginActivity.class));

    }
}

activity_main.xml

<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".MainActivity"android:layout_margin="10dp">

<TextView
        android:id="@+id/text_welcome"android:layout_width="wrap_content"android:layout_height="wrap_content"android:text="Welcome to Home Page!"android:layout_centerInParent="true"android:textSize="20dp"android:layout_marginBottom="20dp"/>

<Button
        android:id="@+id/btnlogout"android:layout_width="match_parent"android:layout_height="wrap_content"android:layout_below="@+id/text_welcome"android:text="Log Out"/>

</RelativeLayout>

LoginActivity.java

package com.example.firebaseapp
;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button btnLogin;
    private TextView textRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login);
        textRegister = findViewById(R.id.text_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void login() {
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (user.isEmpty()) {
            email.setError("Email can not be empty");
        }
        if (pass.isEmpty()) {
            password.setError("Password can not be empty");
        } else {
            mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed" + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

activity_login.xml

<?xml version="1.0"encoding="utf-8"?><RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".LoginActivity"android:layout_margin="10dp">

<TextView
        android:id="@+id/text_login"android:layout_width="match_parent"android:layout_height="wrap_content"android:text="Enter Login Credentials"android:textSize="20dp"android:layout_marginBottom="20dp"/>

<
EditText android:id="@+id/login_email"android:layout_width="match_parent"android:layout_height="wrap_content"android:hint="email"android:layout_below="@+id/text_login"android:layout_marginBottom="20dp"/>

<
EditText android:id="@+id/login_password"android:layout_width="match_parent"android:layout_height="wrap_content"android:hint="password"android:layout_below="@+id/login_email"android:layout_marginBottom="20dp"/>

<
Button android:id="@+id/login"android:layout_width="match_parent"android:layout_height="wrap_content"android:layout_below="@+id/login_password"android:text="Login"android:layout_marginBottom="20dp"/>

<
TextView android:id="@+id/text_register"android:layout_width="match_parent"android:layout_height="wrap_content"android:layout_below="@+id/login"android:text="New User Register"android:layout_marginBottom="20dp"android:textAlignment="center"/></RelativeLayout>

RegisterActivity.java

package com.example.firebaseapp
;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button btnRegister;
    private TextView textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        btnRegister = findViewById(R.id.register);
        textLogin = findViewById(R.id.text_login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void Register() {
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (user.isEmpty()) {
            email.setError("Email can not be empty");
        }
        if (pass.isEmpty()) {
            password.setError("Password can not be empty");
        } else {
            mAuth.createUserWithEmailAndPassword(user, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "User registered successfully",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(RegisterActivity.this,
                                        "Registration Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                            }

                        }
                    });
        }
    }
}

activity_register.xml

<?

xml version = "1.0"encoding="utf-8"?><
RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"xmlns:app="http://schemas.android.com/apk/res-auto"xmlns:tools="http://schemas.android.com/tools"android:layout_width="match_parent"android:layout_height="match_parent"tools:context=".RegisterActivity"android:layout_margin="10dp">

<
TextView android:id="@+id/text_register"android:layout_width="match_parent"android:layout_height="wrap_content"android:text="Register Here"android:textSize="20dp"android:layout_marginBottom="20dp"/>

<
EditText android:id="@+id/register_email"android:layout_width="match_parent"android:layout_height="wrap_content"android:hint="email"android:layout_below="@+id/text_register"android:layout_marginBottom="20dp"/>

<
EditText android:id="@+id/register_password"android:layout_width="match_parent"android:layout_height="wrap_content"android:hint="password"android:layout_below="@+id/register_email"android:layout_marginBottom="20dp"/>

<
Button android:id="@+id/register"android:layout_width="match_parent"android:layout_height="wrap_content"android:layout_below="@+id/register_password"android:text="Register"android:layout_marginBottom="20dp"/>

<
TextView android:id="@+id/text_login"android:layout_width="match_parent"android:layout_height="wrap_content"android:layout_below="@+id/register"android:text="Existing User Login"android:layout_marginBottom="20dp"android:textAlignment="center"/>

</RelativeLayout
>
