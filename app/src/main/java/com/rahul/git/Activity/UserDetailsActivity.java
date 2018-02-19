package com.rahul.git.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rahul.git.Adapter.UserAdapter;
import com.rahul.git.DashBoardActivity;
import com.rahul.git.Model.UserDetailsPojo;
import com.rahul.git.Model.UsersPojo;
import com.rahul.git.Network.NetworkAPIService;
import com.rahul.git.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class UserDetailsActivity extends AppCompatActivity {

    public String mSearchName, mNametoLow;
    public TextView mName;
    public TextView mCompany;
    public TextView mBlog;
    public TextView mLocation;
    public ImageView mUserImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        mName = (TextView)findViewById(R.id.name);
        mBlog = (TextView)findViewById(R.id.blog);
        mLocation  =(TextView)findViewById(R.id.location);
        mUserImg = (ImageView)findViewById(R.id.imgsrc);

        Bundle bundle = getIntent().getExtras();
        mSearchName = bundle.getString("searchname");
        Log.i("ididididid","ididididid"+mSearchName);
        mNametoLow = mSearchName.toLowerCase();

        Log.i("ididididid","ididididid"+ mNametoLow);
        NetworkAPIService.DeviceApi apiService = NetworkAPIService
                .getInstance().newTransaction();
        Call<UserDetailsPojo> call= apiService.getuserinfo(mNametoLow);
        call.enqueue(new retrofit2.Callback<UserDetailsPojo>() {
            @Override
            public void onResponse(Call<UserDetailsPojo> call, Response<UserDetailsPojo> response) {
                if (!call.isCanceled() && response.isSuccessful()) {
                    try{

                        Log.i("response"," Response "+response.body().getName()+" NAme"+response.body().getLogin());

                        mName.setText(response.body().getName().toString());
                        mBlog.setText(response.body().getBlog().toString());
                        mLocation.setText(response.body().getLocation().toString());

                        Picasso.with(getApplicationContext())
                                .load(response.body().getAvatarUrl())
                                .placeholder(R.mipmap.ic_launcher_round) //this is optional the image to display while the url image is downloading
                                .error(R.mipmap.ic_launcher_round)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                                .into(mUserImg);


                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    }
                }else {

                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserDetailsPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error" , Toast.LENGTH_LONG).show();
            }
        });
    }
}
