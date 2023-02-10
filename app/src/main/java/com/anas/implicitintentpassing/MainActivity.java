package com.anas.implicitintentpassing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQCODE = 100;
    Button btnCall, btnMessage, btnEmail, btnShare, btnCamActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCall=findViewById(R.id.btnCall);
        btnEmail=findViewById(R.id.btnEmail);
        btnMessage=findViewById(R.id.btnMessage);
        btnShare=findViewById(R.id.btnShare);
        btnCamActivity=findViewById(R.id.btnCamActivity);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iCall=new Intent(Intent.ACTION_DIAL);
                iCall.setData(Uri.parse("tel: +917060997580"));
                startActivity(iCall);
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iMsg=new Intent(Intent.ACTION_SENDTO);
                iMsg.setData(Uri.parse("smsto:"+Uri.encode("+917060997580")));
                iMsg.putExtra("sms_body","Please reply on this message");
                startActivity(iMsg);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iEmail=new Intent(Intent.ACTION_SEND);

                //for differentiating email from share and other types
                iEmail.setType("message/rfc822");
                iEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"anas4112002@gmail.com","anas2002aman@gmail.com"});
                iEmail.putExtra(Intent.EXTRA_SUBJECT,"My app for implicit intent passing");
                iEmail.putExtra(Intent.EXTRA_TEXT,"Please check my app and fix the bugs");
                startActivity(Intent.createChooser(iEmail,"Email via"));
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iShare=new Intent(Intent.ACTION_SEND);
                iShare.setType("text/plain");
                iShare.putExtra(Intent.EXTRA_TEXT,"Download my app, https://play.google.com/store/apps/details?id=com.instagram.android");

                //for mutliple app chosing to open
                startActivity(Intent.createChooser(iShare,"Share via Anas"));
            }
        });

        btnCamActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CameraActivity.class));
            }
        });

    }

}