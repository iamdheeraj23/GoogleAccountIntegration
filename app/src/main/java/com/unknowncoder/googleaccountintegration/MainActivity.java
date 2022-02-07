package com.unknowncoder.googleaccountintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    SharedPreferences sharedPreferences;
    Constants constants=new Constants();
    SharedPreferences.Editor editor;
    ImageView imageView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.signOutButton);
        textView=findViewById(R.id.user_information);
        imageView=findViewById(R.id.imageView);
        sharedPreferences=getSharedPreferences("MyAppData",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        String gName=sharedPreferences.getString(constants.given_name,"");
        String dName=sharedPreferences.getString(constants.display_name,"");
        String email=sharedPreferences.getString(constants.email,"");
        String photo_url=sharedPreferences.getString(constants.photo, "");


        textView.setText("Display Name :- "+dName+"\n"+
                "Given Name :- "+gName+"\n"+
                "Email :- "+email);

        if(photo_url.equals("")){
            imageView.setImageResource(R.drawable.ic_launcher_background);
        }else {
            Glide.with(getApplicationContext()).load(photo_url).placeholder(R.drawable.ic_launcher_background).into(imageView);
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignOutUser();
            }
        });
    }
    private void SignOutUser() {
        editor.clear().apply();
        startActivity(new Intent(MainActivity.this,StartActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
}