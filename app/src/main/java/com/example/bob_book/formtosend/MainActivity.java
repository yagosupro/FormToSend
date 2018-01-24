package com.example.bob_book.formtosend;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;





//TODO сделать вызов стандартного почтовика и отправку письма с шляпой

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox checkBox;
    Uri photo=Uri.EMPTY;
    EditText editPassword;
    EditText editPhone;
    EditText editMail;
    TextView textCount;
    TextWatcher textWatcher;
    Button buttonView;
    ImageButton buttonPhoto;
    Validation data;
    Bitmap thePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox= (CheckBox) findViewById(R.id.checkBox);
        editPassword= (EditText) findViewById(R.id.editPassword);
        editPhone= (EditText) findViewById(R.id.editPhone);
        textCount= (TextView) findViewById(R.id.textCount);
        buttonView= (Button) findViewById(R.id.buttonView);
        buttonPhoto= (ImageButton) findViewById(R.id.buttonPhoto);
        editMail= (EditText) findViewById(R.id.editEmail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        buttonView.setOnClickListener(this);
        buttonPhoto.setOnClickListener(this);

        //TextPhoneCounter
        textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textCount.setText(String.valueOf(s.length())+ "/12");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editPhone.addTextChangedListener(textWatcher);

        //checkBox change visible password
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) editPassword.setTransformationMethod(new PasswordTransformationMethod());
                else editPassword.setTransformationMethod(null);
                editPassword.setSelection(editPassword.length());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==1){photo = data.getData();
            Bundle extras = data.getExtras();
            // Получим кадрированное изображение
            thePic = extras.getParcelable("data");
            // передаём его в ImageView
            ImageView picView = (ImageView)findViewById(R.id.imageView);
            picView.setImageBitmap(thePic);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonView:

                //Validation DATA
                data=new Validation(photo,editMail.getText().toString(),editPhone.getText().toString(),editPassword.getText().toString());


            switch (data.status){
                case 0:
                    Intent intent= new Intent(this,ActivityTwo.class);
                    intent.putExtra("photo",thePic);
                    intent.putExtra("email",data.getEmail());
                    intent.putExtra("photoUri",photo);


                    intent.putExtra("phone",data.getChangePhone());
                    intent.putExtra("password",data.getPassword());
                    startActivity(intent);

                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), "netPhoto", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "netEmail", Toast.LENGTH_LONG).show();
                    editMail.requestFocus();
//
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(), "netPhone", Toast.LENGTH_LONG).show();
                    editPhone.requestFocus();
                    break;
                case 4:
                    Toast.makeText(getApplicationContext(), "netPassword", Toast.LENGTH_LONG).show();
                    editPassword.requestFocus();
                    break;
            }
                break;

            case R.id.buttonPhoto:
                System.out.println("buttonPhoto");
                try {
                    // Намерение для запуска камеры
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(captureIntent, 1);
                } catch (ActivityNotFoundException e) {
                    // Выводим сообщение об ошибке
                    String errorMessage = "Ваше устройство не поддерживает съемку";
                    Toast toast = Toast
                            .makeText(this, errorMessage, Toast.LENGTH_SHORT);
                    toast.show();
                }
        }
    }
}
