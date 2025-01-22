package com.rygodc.retrofitexample;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoctailDetailActivity extends AppCompatActivity {

    private ImageView coctailDetailImage;
    private TextView coctailDetailName;
    private TextView coctailDetailId;
    private TextView coctailDetailInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coctail_detail);

        coctailDetailImage = findViewById(R.id.coctailDetailImage);
        coctailDetailName = findViewById(R.id.coctailDetailName);
        coctailDetailId = findViewById(R.id.coctailDetailId);
        coctailDetailInstructions = findViewById(R.id.coctailDetailInstructions);

        String coctailName = getIntent().getStringExtra("coctailName");
        String coctailImageUrl = getIntent().getStringExtra("coctailImageUrl");
        String coctailId = getIntent().getStringExtra("coctailId");

        coctailDetailName.setText(coctailName);
        coctailDetailId.setText("ID: " + coctailId);
        Glide.with(this).load(coctailImageUrl).into(coctailDetailImage);

        coctailDetails(coctailId);
    }

    private void coctailDetails(String coctailId) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Drinks> call = apiInterface.getDrinkById(coctailId);
        call.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                if (response.isSuccessful() && response.body() != null) {
                    coctailDetailInstructions.setText(
                            response.body().drinks.get(0).coctailInstructions
                    );
                }
            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) {
                coctailDetailInstructions.setText("Error al cargar detalles.");
            }
        });
    }
}

