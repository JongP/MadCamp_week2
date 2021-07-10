package com.example.madcamp_week2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madcamp_week2.frag.Fragment3;
import com.example.madcamp_week2.server.RestResult;
import com.example.madcamp_week2.server.RetrofitInterface;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.249.18.117:80";
    private String restId;
    private ImageView restImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent = getIntent();
        restId = intent.getExtras().getString("restId");

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        request_one_rest();
    }

    private void request_one_rest() {
        HashMap<String , String> map = new HashMap<>();
        map.put("id", restId);


        Call<RestResult> call = retrofitInterface.executeGetOneRest(map);

        call.enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(@NotNull Call<RestResult> call, @NotNull Response<RestResult> response) {
                if (response.code() == 200) {

                    RestResult restResult = response.body();

                    TextView restName = findViewById(R.id.rest_name_id);
                    TextView contact = findViewById(R.id.rest_contact_id);
                    TextView rate = findViewById(R.id.rest_rate_id);
                    TextView category = findViewById(R.id.rest_category_id);
                    restImg = findViewById(R.id.rest_image_id);

                    restName.setText(restResult.getName());
                    contact.setText(restResult.getContact());
                    category.setText(restResult.getCategory());
                    rate.setText("" + restResult.getRate());
                    new LoadImage().execute(restResult.getPhotoURL());

                    restName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

                    Log.d("getOne", restResult.getName());
                    Log.d("getOne", restResult.getContact());
                    Log.d("getOne", restResult.getCategory());

                } else if (response.code() == 400) {

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {


            }
        });

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
            restImg.setImageBitmap(bitmap);
        }
    }
}