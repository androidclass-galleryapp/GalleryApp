package com.example.galleryapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class FullImageViewActivity extends AppCompatActivity {
    ImageView fullImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);

        fullImage = findViewById(R.id.ImageViewFullImage);
        String data = getIntent().getExtras().getString("image");

        fullImage.setImageURI(Uri.parse(data));
    }

}
