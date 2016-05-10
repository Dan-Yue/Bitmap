package com.bitmap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements Handler.Callback{
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
            Bitmap bitmap = loadBitmap();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
        img.setImageBitmap(loadBitmap());
    }

    private Bitmap loadBitmap() {
        Bitmap result = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open("chuangyi31.jpg"));
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "jiyin.jpg");
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            //质量在JPEG下生效，质量一般为80
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            result = BitmapFactory.decodeFile(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what){
            case 0:
            Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_LONG).show();
        }
        return false;
    }

}

































