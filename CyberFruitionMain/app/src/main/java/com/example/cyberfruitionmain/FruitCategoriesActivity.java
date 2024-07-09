package com.example.cyberfruitionmain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cyberfruitionmain.AboutUs.AboutUsActivity_FruitClassifications;
import com.example.cyberfruitionmain.FruitClassifications.BerriesActivity;
import com.example.cyberfruitionmain.FruitClassifications.CitrusActivity;
import com.example.cyberfruitionmain.FruitClassifications.CoresActivity;
import com.example.cyberfruitionmain.FruitClassifications.MelonsActivity;
import com.example.cyberfruitionmain.FruitClassifications.PitsActivity;
import com.example.cyberfruitionmain.FruitClassifications.TropicalsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.cyberfruitionmain.AboutUs.AboutUsActivity;

public class FruitCategoriesActivity extends AppCompatActivity {

    CardView berries,pits,cores,citrus,melons,tropicals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_fruit_categories);

        berries = findViewById(R.id.berriesCard);
        pits = findViewById(R.id.pitsCard);
        cores = findViewById(R.id.coresCard);
        citrus = findViewById(R.id.citrusCard);
        melons = findViewById(R.id.melonsCard);
        tropicals = findViewById(R.id.tropicalsCard);
        berries.setOnClickListener(view -> {
            Intent intent = new Intent(FruitCategoriesActivity.this, BerriesActivity.class);
            Toast.makeText(this, "Berries Classification", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
        pits.setOnClickListener(view -> {
            Intent intent = new Intent(FruitCategoriesActivity.this, PitsActivity.class);
            Toast.makeText(this, "Pits Classification", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
        cores.setOnClickListener(view -> {
            Intent intent = new Intent(FruitCategoriesActivity.this, CoresActivity.class);
            Toast.makeText(this, "Cores Classification", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
        citrus.setOnClickListener(view -> {
            Intent intent = new Intent(FruitCategoriesActivity.this, CitrusActivity.class);
            Toast.makeText(this, "Citrus Classification", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
        melons.setOnClickListener(view -> {
            Intent intent = new Intent(FruitCategoriesActivity.this, MelonsActivity.class);
            Toast.makeText(this, "Melons Classification", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
        tropicals.setOnClickListener(view -> {
            Intent intent = new Intent(FruitCategoriesActivity.this, TropicalsActivity.class);
            Toast.makeText(this, "Tropicals Classification", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        ImageButton buttonAbout = findViewById(R.id.aboutButton);

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Page 1
                Intent intent = new Intent(FruitCategoriesActivity.this, AboutUsActivity_FruitClassifications.class);
                Toast.makeText(FruitCategoriesActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        ImageButton buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Page 2
                Intent intent = new Intent(FruitCategoriesActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.camera:
                    showCustomDialog();
                    return true;

                case R.id.fruitClass:
                    Toast.makeText(FruitCategoriesActivity.this, "Fruit Classifications", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });
    }
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_capture_options, null);

        builder.setView(dialogView)
                .setCancelable(true);

        AlertDialog dialog = builder.create();

        Button btnSingleCapture = dialogView.findViewById(R.id.btnSingleCapture);
        Button btnMultipleCapture = dialogView.findViewById(R.id.btnMultipleCapture);

        btnSingleCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FruitCategoriesActivity.this, CaptureSingleActivity.class);
                Toast.makeText(FruitCategoriesActivity.this, "Single Capture", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
                dialog.dismiss();
            }
        });

        btnMultipleCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FruitCategoriesActivity.this, CaptureMultipleActivity.class);
                Toast.makeText(FruitCategoriesActivity.this, "Multiple Capture", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}