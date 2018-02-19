package com.rahul.git;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rahul.git.Activity.UserDetailsActivity;
import com.rahul.git.Adapter.UserAdapter;
import com.rahul.git.Model.UsersPojo;
import com.rahul.git.Network.NetworkAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity {

    public EditText mSearchName;
    public String  mSearchDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);





        callUsersDetails();
    }

    public void Search(View view){

        mSearchName =(EditText)findViewById(R.id.searchname);
        mSearchDetails = mSearchName.getText().toString();

        Intent i = new Intent(this,UserDetailsActivity.class);
        Log.i("dataPass","Data PAsss "+mSearchDetails);
        i.putExtra("searchname",mSearchDetails);
        startActivity(i);

    }

private void callUsersDetails() {
    NetworkAPIService.DeviceApi apiService = NetworkAPIService
            .getInstance().newTransaction();
    Call<List<UsersPojo>> call= apiService.getuserdetails();
    call.enqueue(new retrofit2.Callback<List<UsersPojo>>() {
        @Override
        public void onResponse(Call<List<UsersPojo>> call, Response<List<UsersPojo>> response) {
            if (!call.isCanceled() && response.isSuccessful()) {
                try{
                   Log.i("message"," "+response.body()) ;

                    List<UsersPojo> UserData = response.body();

                    RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycleUsers);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);

                    UserAdapter mm =  new UserAdapter(UserData,DashBoardActivity.this);

                    recyclerView.setAdapter(mm);

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }else {

                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<List<UsersPojo>> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Error" , Toast.LENGTH_LONG).show();
        }
    });

}
}
