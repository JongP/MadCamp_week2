package com.example.madcamp_week2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madcamp_week2.server.PostResult;
import com.example.madcamp_week2.server.RestResult;
import com.example.madcamp_week2.server.RetrofitInterface;
import com.example.madcamp_week2.user.UserData;

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

public class DetailPostActivity extends AppCompatActivity {

    private String postId;
    private ImageView postImg;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.249.18.117:80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        Intent intent = getIntent();
        postId = intent.getExtras().getString("postId");

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Log.d("finish", "before request_one");
        request_one_post();

    }

    private void request_one_post() {

        Log.d("finish", "start request one post");

        HashMap<String , String> map = new HashMap<>();
        map.put("id", postId);

        Log.d("finish", postId);

        Call<PostResult> call = retrofitInterface.executeGetOnePost(map);

        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(@NotNull Call<PostResult> call, @NotNull Response<PostResult> response) {
                if (response.code() == 200) {

                    PostResult postResult = response.body();

                    TextView restName = findViewById(R.id.post_restaurant_id);
                    TextView rate = findViewById(R.id.post_rate_id);
                    postImg = findViewById(R.id.post_img_id);
                    ImageView gotoRest = findViewById(R.id.btn_goto_rest);
                    TextView title = findViewById(R.id.post_title_id);
                    TextView content = findViewById(R.id.post_content_id);

                    rate.setText("별점 : " + (Math.round(postResult.getRate() * 10) / 10.0));
                    new LoadImage().execute(postResult.getPostImg());
                    restName.setText(postResult.getRestName());
                    restName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                    title.setText(postResult.getTitle());
                    content.setText(postResult.getContent());

                    gotoRest.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), RestaurantActivity.class);
                            intent.putExtra("restId", postResult.getRest());
                            startActivity(intent);
                        }
                    });

                } else if (response.code() == 400) {
                    Toast.makeText(DetailPostActivity.this, "somehow onresponse failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                Toast.makeText(DetailPostActivity.this, "get one post failed", Toast.LENGTH_LONG).show();
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
            postImg.setImageBitmap(bitmap);
        }
    }
}
