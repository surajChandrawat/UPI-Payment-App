package com.example.upipaymentapp.adapter;

import com.example.upipaymentapp.model.UPIUsersModel;
import java.util.ArrayList;
import java.util.List;

public class AllUsersListPresenter {

    private ArrayList<UPIUsersModel> upiUsersModelArrayList;

        public AllUsersListPresenter(ArrayList<UPIUsersModel> upiUsersModelArrayList) {
            this.upiUsersModelArrayList = upiUsersModelArrayList;
        }

        public void onBindAllUsersListRowViewAtPosition(int position, AllUserRowView rowView) {
            UPIUsersModel model = upiUsersModelArrayList.get(position);
            rowView.setName(model.getUserName());
            rowView.setUPI(model.getUserUPIID());
            rowView.setImagePath(model.getUserImage());
        }

        public int getAllUsersListRowsCount() {
            return upiUsersModelArrayList.size();
        }

        public int getAllUsersListRowsViewType(int position) {
            return position;
        }

    public void updateList(ArrayList<UPIUsersModel> temp) {
            upiUsersModelArrayList = temp;
    }
}
