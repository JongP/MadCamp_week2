package com.example.madcamp_week2.frag;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madcamp_week2.R;
import com.example.madcamp_week2.server.RetrofitInterface;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import retrofit2.Retrofit;


public class Fragment3 extends Fragment {

    private static final String LOG_TAG = "Fragment3";
    private ImageView iv_user;
    private TextView tv_userName, tv_userEmail;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton btn_sign;
    private String TAG = "good";
    private RecyclerView rv_fav;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL;


    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("thisway", "onCreateView: there");
        super.onCreate(savedInstanceState);


        GoogleSignInAccount gsa = GoogleSignIn.getLastSignedInAccount(getContext());
        if(gsa!=null){
            Log.d("Frag3: getting Id",gsa.getId());
        }



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.d("thisway", "onCreateView: here");
        View view = inflater.inflate(R.layout.fragment_3, container, false);


        iv_user = view.findViewById(R.id.iv_user);
        tv_userEmail = view.findViewById(R.id.tv_userEmail);
        tv_userName = view.findViewById(R.id.tv_userName);
        rv_fav = view.findViewById(R.id.rv_fav);

        GoogleSignInAccount gsa = GoogleSignIn.getLastSignedInAccount(getContext());
        if(gsa!=null){
            Log.d("Frag3: getting email",gsa.getPhotoUrl().toString());
            tv_userName.setText(gsa.getDisplayName());
            tv_userEmail.setText(gsa.getEmail());
            new LoadImage().execute(gsa.getPhotoUrl().toString());
        }





        return view;
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(strings[0]).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            iv_user.setImageBitmap(bitmap);
        }
    }


}