package com.example.upipaymentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.upipaymentapp.database.BackgroundTask;
import com.example.upipaymentapp.model.UPIUsersModel;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_userName,et_userUPI;
    private CircleImageView circleImageView;
    private final int PICK_IMAGE_GALLERY = 10002;
    private String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MyApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        et_userName = findViewById(R.id.et_userName);
        et_userUPI = findViewById(R.id.et_userUPI);
        circleImageView = findViewById(R.id.imgProfilePhoto);
        findViewById(R.id.image_layout).setOnClickListener(this);
        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_allUsers).setOnClickListener(this);
        findViewById(R.id.bt_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_layout:
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_GALLERY);
                break;

            case R.id.bt_add:
                checkValidation();
                break;

            case R.id.bt_allUsers:
                Intent intent1 = new Intent(this, AllUsersListActivity.class);
                startActivity(intent1);
                break;

            case R.id.bt_setting:
                Intent intent2 = new Intent(this, Setting.class);
                startActivity(intent2);
                break;
        }
    }

    private void checkValidation() {
        String name = et_userName.getText().toString().trim();
        String address = et_userUPI.getText().toString().trim();
        if(name.isEmpty()) {
            Toast.makeText(this,"Please Enter User Name",Toast.LENGTH_LONG).show();
        } else if(address.isEmpty()) {
            Toast.makeText(this,"Please Enter User UPI Address",Toast.LENGTH_LONG).show();
        } else if(!address.matches("^\\w+@ok\\w+$")) {
            Toast.makeText(this,"Please Enter Valid UPI Address",Toast.LENGTH_LONG).show();
        } else {
            UPIUsersModel model = new UPIUsersModel();
            model.setUserName(name);
            model.setUserUPIID(address);
            model.setUserImage(imagePath);
            BackgroundTask backgroundTask = new BackgroundTask(this,model, "");
            backgroundTask.execute(1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_GALLERY) {
            if(data != null) {
                circleImageView.setImageURI(data.getData());
                imagePath = data.getData().toString();
            }
        }
    }

    public void addSuccess() {
        et_userName.setText("");
        et_userUPI.setText("");
        circleImageView.setImageResource(R.drawable.profileimageholder);
    }
}