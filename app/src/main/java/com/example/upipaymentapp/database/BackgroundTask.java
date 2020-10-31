package com.example.upipaymentapp.database;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.upipaymentapp.AllUsersListActivity;
import com.example.upipaymentapp.EditUserActivity;
import com.example.upipaymentapp.MainActivity;
import com.example.upipaymentapp.model.UPIUsersModel;
import java.util.ArrayList;
import java.util.List;

public class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {

    private Context context;
    private UPIUsersModel upiUsersModel;
    private String tag;
    private List<UPIUsersModel> getAllItemList = new ArrayList<>();

    public BackgroundTask(Context context, UPIUsersModel upiUsersModel, String tag) {
        this.context = context;
        this.tag = tag;
        this.upiUsersModel = upiUsersModel;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        switch (integers[0]) {
            case 1:
                DatabaseClient.getInstance(context).getAppDatabase().itemDao().
                        insertItem(upiUsersModel);
                return 1;

            case 2:
                DatabaseClient.getInstance(context).getAppDatabase()
                        .itemDao().updateItem(upiUsersModel);
                return 2;

            case 3:
                int re = DatabaseClient.getInstance(context).getAppDatabase().itemDao()
                        .deleteItem(upiUsersModel.id);
                if (re < 0) {
                    return 402;
                } else return 3;

            case 4:
                getAllItemList = DatabaseClient.getInstance(context).getAppDatabase().itemDao()
                        .getAllItem();
                System.out.println("list size is 11==  " + getAllItemList.size());
                if (getAllItemList == null) {
                    return 402;
                } else return 4;
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case 1:
                Toast.makeText(context,"User is added successfully", Toast.LENGTH_LONG).show();
                ((MainActivity)context).addSuccess();
                break;
            case 2:
                Toast.makeText(context,"User is updated successfully", Toast.LENGTH_LONG).show();
                ((EditUserActivity)context).updateSuccess();
                break;
            case 3:
                Toast.makeText(context,"User delete successfully", Toast.LENGTH_LONG).show();
                break;

            case 4:
                ((AllUsersListActivity)context).getAllData(new ArrayList<>(getAllItemList));
                break;
            default:
                break;
        }
    }
}
