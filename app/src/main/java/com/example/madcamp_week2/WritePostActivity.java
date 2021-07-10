package com.example.madcamp_week2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.madcamp_week2.server.RetrofitInterface;
import com.example.madcamp_week2.user.UserData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WritePostActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.249.18.117:80";
    private String restId;

    EditText editTitle;
    EditText editContent;
    Button addImage_button;
    Button upload_button;
    RadioButton[] rateButtons = new RadioButton[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        Intent intent = getIntent();
        restId = intent.getExtras().getString("restId");

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        editTitle = findViewById(R.id.edit_title_id);
        editContent = findViewById(R.id.edit_content_id);
        addImage_button = findViewById(R.id.btn_add_img_id);
        upload_button = findViewById(R.id.upload_id);
        rateButtons[0] = findViewById(R.id.rg_btn0);
        rateButtons[1] = findViewById(R.id.rg_btn1);
        rateButtons[2] = findViewById(R.id.rg_btn2);
        rateButtons[3] = findViewById(R.id.rg_btn3);
        rateButtons[4] = findViewById(R.id.rg_btn4);

        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTitle.getText().toString().equals("") || editContent.getText().toString().equals(""))
                    return;

                Log.d("sat", "editText succeed");

                boolean uploadable = false;
                int rate = 0;
                for (int i = 0; i < rateButtons.length; i++) {
                    if (rateButtons[i].isChecked()) {
                        uploadable = true;
                        rate = i + 1;
                    }
                }

                Log.d("sat", "check rate succeed");

                if (!uploadable)
                    return;

                sendPost(rate);

                Log.d("sat", "sendPost done");
            }
        });
    }

    private void sendPost(int rate) {

        Log.d("sat", "start sendPost");

        HashMap<String,String> map =  new HashMap<>();
        map.put("title",editTitle.getText().toString());
        map.put("content",editContent.getText().toString());
        map.put("rate", rate + "");
        map.put("rest",restId);
        map.put("user","106592296388275140582");

        Log.d("sat", "106592296388275140582");

        Call<Void> call = retrofitInterface.executePostAdd(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==200)
                {
                    Toast.makeText(WritePostActivity.this, "Post upload succeed", Toast.LENGTH_LONG).show();
                    Log.d("sat", "post upload");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(WritePostActivity.this, "Post upload failed", Toast.LENGTH_LONG).show();

            }
        });


    }
}