package com.rygodc.retrofitexample;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView coctailRecyclerView;
    private CoctailAdapter adapter;
    private EditText ingredientInput;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingredientInput = findViewById(R.id.ingredientInput);
        searchButton = findViewById(R.id.searchButton);
        coctailRecyclerView = findViewById(R.id.coctailRecyclerView);

        coctailRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CoctailAdapter(new ArrayList<>(), this::showCoctailDetails);
        coctailRecyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> searchCoctails());
    }

    private void searchCoctails() {
        String ingredient = ingredientInput.getText().toString().trim();
        if (ingredient.isEmpty()) return;

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getDrinksByLicour(ingredient).enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new CoctailAdapter(response.body().drinks, MainActivity.this::showCoctailDetails);
                    coctailRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: El ingrediente no se encuentra", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showCoctailDetails(Drinks.Coctail coctail) {
        new AlertDialog.Builder(this)
                .setTitle(coctail.coctailName)
                .setMessage("ID: " + coctail.coctailIid)
                .setPositiveButton("OK", null)
                .show();
    }
}
