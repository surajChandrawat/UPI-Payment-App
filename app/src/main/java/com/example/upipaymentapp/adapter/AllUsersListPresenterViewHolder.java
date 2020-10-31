package com.example.upipaymentapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import com.example.upipaymentapp.R;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AllUsersListPresenterViewHolder extends RecyclerView.ViewHolder implements AllUserRowView {

    private TextView txt_userName,txt_userAddress;
    private CircleImageView circleImageView;

    public AllUsersListPresenterViewHolder(View itemView, Context context) {
        super(itemView);
        txt_userName = itemView.findViewById(R.id.tv_userName);
        txt_userAddress = itemView.findViewById(R.id.tv_userAddress);
        circleImageView = itemView.findViewById(R.id.imgProfilePhoto);
    }

    @Override
    public void setName(String name) {
        txt_userName.setText(name);
    }

    @Override
    public void setUPI(String upi) {
        txt_userAddress.setText(upi);
    }

    @Override
    public void setImagePath(String imagePath) {
        if(!imagePath.equals("")) {
            circleImageView.setImageURI(Uri.parse(imagePath));
        }
    }
}
