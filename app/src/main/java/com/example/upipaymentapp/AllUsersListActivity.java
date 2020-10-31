package com.example.upipaymentapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.upipaymentapp.adapter.AllUsersListAdapter;
import com.example.upipaymentapp.adapter.AllUsersListPresenter;
import com.example.upipaymentapp.database.BackgroundTask;
import com.example.upipaymentapp.model.UPIUsersModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllUsersListActivity extends AppCompatActivity {

    private SwipeController swipeController;
    private EditText et_search;
    private AllUsersListAdapter adapter;
    private ArrayList<UPIUsersModel> upiUsersModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MyApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_all_users);
        init();
    }

    private void init() {
        et_search = findViewById(R.id.et_search);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ArrayList<UPIUsersModel> temp = new ArrayList();
                for(UPIUsersModel d: upiUsersModelArrayList){
                    //or use .equal(text) with you want equal match
                    //use .toLowerCase() for better matches
                    if(d.getUserName().contains(editable.toString()) || d.getUserUPIID().contains(editable.toString())){
                        temp.add(d);
                    }
                }
                //update recyclerview
                adapter.updateList(temp);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();

        adapter = new AllUsersListAdapter(new AllUsersListPresenter(upiUsersModelArrayList),this);
        recyclerView.setAdapter(adapter);

        BackgroundTask backgroundTask = new BackgroundTask(this,null, "");
        backgroundTask.execute(4);

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                Intent intent = new Intent(AllUsersListActivity.this,EditUserActivity.class);
                intent.putExtra("userData", upiUsersModelArrayList.get(position));
                startActivity(intent);
            }

            @Override
            public void onLeftClicked(int position) {
                BackgroundTask backgroundTask = new BackgroundTask(AllUsersListActivity.this,upiUsersModelArrayList.get(position), "");
                backgroundTask.execute(3);
                upiUsersModelArrayList.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    public void getAllData(ArrayList<UPIUsersModel> upiUsersModels) {
        upiUsersModelArrayList.clear();
        upiUsersModelArrayList.addAll(upiUsersModels);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null) {
            BackgroundTask backgroundTask = new BackgroundTask(this,null, "");
            backgroundTask.execute(4);
        }
    }
}
