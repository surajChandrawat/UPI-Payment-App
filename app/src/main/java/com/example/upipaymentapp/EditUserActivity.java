package com.example.upipaymentapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.upipaymentapp.database.BackgroundTask;
import com.example.upipaymentapp.model.UPIUsersModel;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_userName,et_userUPI;
    private CircleImageView circleImageView;
    private final int PICK_IMAGE_GALLERY = 10002;
    private String imagePath = "";
    private UPIUsersModel upiUsersModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        TextView tv_addUser = findViewById(R.id.tv_addUser);
        tv_addUser.setText("Edit User");
        et_userName = findViewById(R.id.et_userName);
        et_userUPI = findViewById(R.id.et_userUPI);
        circleImageView = findViewById(R.id.imgProfilePhoto);
        upiUsersModel = (UPIUsersModel) getIntent().getSerializableExtra("userData");
        et_userName.setText(upiUsersModel.getUserName());
        et_userUPI.setText(upiUsersModel.getUserUPIID());
        imagePath = upiUsersModel.getUserImage();
        if(!imagePath.equals(""))
            circleImageView.setImageURI(Uri.parse(imagePath));
        findViewById(R.id.image_layout).setOnClickListener(this);
        Button bt_add =  findViewById(R.id.bt_add);
        bt_add.setText("Update");
        bt_add.setOnClickListener(this);
        findViewById(R.id.bt_allUsers).setVisibility(View.GONE);
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
        }
    }

    private void checkValidation() {
        String name = et_userName.getText().toString().trim();
        String address = et_userUPI.getText().toString().trim();
        if(name.isEmpty()) {
            Toast.makeText(this,"Please Enter User Name",Toast.LENGTH_LONG).show();
        } else if(address.isEmpty()) {
            Toast.makeText(this,"Please Enter User UPI Address",Toast.LENGTH_LONG).show();
        } else {
            UPIUsersModel model = new UPIUsersModel();
            model.setId(upiUsersModel.getId());
            model.setUserName(name);
            model.setUserUPIID(address);
            model.setUserImage(imagePath);
            BackgroundTask backgroundTask = new BackgroundTask(this,model, "");
            backgroundTask.execute(2);
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

    public void updateSuccess() {
        finish();
    }
}
