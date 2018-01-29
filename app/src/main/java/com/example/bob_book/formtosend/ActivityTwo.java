package com.example.bob_book.formtosend;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {

    Uri photoUri = Uri.EMPTY;
    ImageView imagePhoto;
    TextView textMail;
    TextView textPhone;
    TextView textPass;
    Bitmap photo;

    Button sendEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Intent intent = getIntent();

        imagePhoto = (ImageView) findViewById(R.id.imagePhoto);
        textMail = (TextView) findViewById(R.id.textMail);
        textPhone = (TextView) findViewById(R.id.textPhone);
        textPass = (TextView) findViewById(R.id.textPass);
        sendEmail = (Button) findViewById(R.id.sendEmail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        photoUri = intent.getParcelableExtra("photoUri");
        photo = intent.getParcelableExtra("photo");
        textMail.setText(intent.getStringExtra("email"));
        textPass.setText(intent.getStringExtra("password"));
        textPhone.setText(intent.getStringExtra("phone"));

        sendEmail.setOnClickListener(this);

        imagePhoto.setImageBitmap(photo);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendEmail:
                Toast.makeText(getApplicationContext(), "SENDBYEMAIL", Toast.LENGTH_LONG).show();
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
                emailIntent.setType("image/jpeg");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"jon@example.com"}); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "@projectName: данные анкеты");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email:" + textMail.getText() + "\n\r Phone: " + textPhone.getText() + "\n\r");
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(String.valueOf(photoUri)));
                break;
        }

    }

}
