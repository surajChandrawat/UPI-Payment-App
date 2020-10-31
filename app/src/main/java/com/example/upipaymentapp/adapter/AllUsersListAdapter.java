package com.example.upipaymentapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.upipaymentapp.R;
import com.example.upipaymentapp.model.UPIUsersModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AllUsersListAdapter extends RecyclerView.Adapter<AllUsersListPresenterViewHolder> {

    private final AllUsersListPresenter presenter;
    private Context context;

    public AllUsersListAdapter(AllUsersListPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public AllUsersListPresenterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllUsersListPresenterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_all_users_view, parent, false),context);
    }

    @Override
    public void onBindViewHolder(AllUsersListPresenterViewHolder holder, int position) {
        presenter.onBindAllUsersListRowViewAtPosition(position, holder);

    }

    @Override
    public int getItemCount() {
        return presenter.getAllUsersListRowsCount();
    }

    @Override
    public int getItemViewType(int position) {
        return presenter.getAllUsersListRowsViewType(position);
    }

    public void updateList(ArrayList<UPIUsersModel> temp) {
        presenter.updateList(temp);
        notifyDataSetChanged();
    }
}

