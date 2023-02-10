package com.anas.implicitintentpassing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity {

    ImageView imgCamera;
    ImageButton btnCamera, btnGallery;
    private static final int CAMERA_REQ_CODE = 100;
    private static final int GALLERY_REQ_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imgCamera = findViewById(R.id.imgCamera);
        btnCamera=findViewById(R.id.btnCamera);
        btnGallery=findViewById(R.id.btnGallery);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera, CAMERA_REQ_CODE);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            if (requestCode== CAMERA_REQ_CODE){
                Bitmap camera_img = (Bitmap) data.getExtras().get("data");
                imgCamera.setImageBitmap(camera_img);
            }
            else if (requestCode==GALLERY_REQ_CODE){
                imgCamera.setImageURI(data.getData());
            }
        }
        else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
}