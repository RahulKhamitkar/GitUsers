package com.rahul.git.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahul.git.Model.UsersPojo;
import com.rahul.git.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yogita Rachna on 2/18/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<UsersPojo> users;
    private Context context;


    public UserAdapter(List<UsersPojo> Channell,Context context) {
        this.users= Channell;
        this.context = context;


    }
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.userscard, viewGroup, false);
        return new UserAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder viewHolder, int i) {


        viewHolder.loginid.setText(users.get(i).getId());
        viewHolder.username.setText(users.get(i).getLogin());


        Picasso.with(context)
                .load(users.get(i).getAvatar_url())
                .placeholder(R.mipmap.gitlogo) //this is optional the image to display while the url image is downloading
                .error(R.mipmap.gitlogo)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(viewHolder.pic);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView pic;
        private TextView loginid;
        private TextView username;

        public ViewHolder(View view) {
            super(view);

            pic = (ImageView) view.findViewById(R.id.userimage);
            loginid = (TextView)view.findViewById(R.id.login_id);
            username=(TextView)view.findViewById(R.id.username);

        }
    }
}
