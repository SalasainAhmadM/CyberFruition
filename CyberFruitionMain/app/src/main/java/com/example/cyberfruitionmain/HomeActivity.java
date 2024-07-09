package com.example.cyberfruitionmain;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.cyberfruitionmain.AboutUs.AboutUsActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home);

        ImageButton aboutUs = findViewById(R.id.aboutButton);
        Button homeButton = findViewById(R.id.centeredButton); // Replace with your actual button ID

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
                Toast.makeText(HomeActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.camera:
                    showCustomDialog();
                    return true;

                case R.id.fruitClass:
                    startActivity(new Intent(getApplicationContext(), FruitCategoriesActivity.class));
                    Toast.makeText(HomeActivity.this, "Fruit Classifications", Toast.LENGTH_SHORT).show();
                    overridePendingTransition(0, 0);
                    finish();
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
                Intent intent = new Intent(HomeActivity.this, CaptureSingleActivity.class);
                Toast.makeText(HomeActivity.this, "Single Capture", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
                dialog.dismiss();
            }
        });

        btnMultipleCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CaptureMultipleActivity.class);
                Toast.makeText(HomeActivity.this, "Multiple Capture", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
