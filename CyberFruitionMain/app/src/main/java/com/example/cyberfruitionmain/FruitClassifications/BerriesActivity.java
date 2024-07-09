package com.example.cyberfruitionmain.FruitClassifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.cyberfruitionmain.FruitCategoriesActivity;
import com.example.cyberfruitionmain.R;

public class BerriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_berries);

        ImageButton buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Page 2
                Intent intent = new Intent(BerriesActivity.this, FruitCategoriesActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}