package com.example.galleryapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<File> listFileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.GridViewImage);
        listFileImage = getAllShownImagesPath();

        gridView.setAdapter(new gridAdapter());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(MainActivity.this, FullImageViewActivity.class);
               intent.putExtra("image", listFileImage.get(position).toString());

               startActivity(intent);

           }
      });
    }

    public class gridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return listFileImage.size();
        }

        @Override
        public Object getItem(int position) {
            return listFileImage.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             convertView = null;

            if(convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.row_layout, parent, false);
                ImageView Image = convertView.findViewById(R.id.ImageViewImage);
                Image.setImageURI(Uri.parse(listFileImage.get(position).toString()));
            }
            return convertView;
        }
    }

    private ArrayList<File> getAllShownImagesPath() {

        try {
            ArrayList<File> listOfAllImages = new ArrayList<>();

            Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA};
            Cursor cursor = this.getContentResolver().query(uri, projection, null, null, null);


            while (cursor.moveToNext()) {
                String absolutePathOfImage = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
                listOfAllImages.add(new File(absolutePathOfImage));
            }

            return listOfAllImages;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
