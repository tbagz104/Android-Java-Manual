package com.example.intentshare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button textButton = findViewById(R.id.buttonShareText);
        textButton.setOnClickListener(this);

        Button imageButton = findViewById(R.id.buttonShareImage);
        imageButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.buttonShareText :
                shareText();
                break;
            case R.id.buttonShareImage :
                shareImage();
                break;
        }
    }

    private void shareText(){

        Intent textIntent = new Intent();

        textIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        textIntent.setAction(Intent.ACTION_SEND);
        textIntent.putExtra(Intent.EXTRA_TEXT,"COPY ME PLEASE");
        textIntent.setType("text/plain");

        Intent chooseTextIntent = new Intent(Intent.createChooser(textIntent,"Share Text"));
        startActivity(chooseTextIntent);

    }

    private void shareImage(){

        MainActivity obj;
        obj = this;

        Intent imageIntent = new Intent();

        imageIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        imageIntent.setAction(Intent.ACTION_SEND);
        imageIntent.setType("image/*");

        File imgPath = new File(obj.getFilesDir(),"images");
        File newFile = new File(imgPath,"default_img.jpg");

        Uri uriToImage = FileProvider.getUriForFile(this,"com.example.intentshare", newFile);

        imageIntent.putExtra(Intent.EXTRA_STREAM,uriToImage);

        Intent chooseImageIntent = new Intent(Intent.createChooser(imageIntent,"Share Image"));
        startActivity(chooseImageIntent);

    }
}