package com.example.cyberfruitionmain.AboutUs;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cyberfruitionmain.FruitCategoriesActivity;
import com.example.cyberfruitionmain.R;

public class AboutUsActivity_FruitClassifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_about_fc);
        TextView titleView = findViewById(R.id.titleView);

        String titleText = "Welcome to <font color='#d04e17'>CyberFruition</font>!";

        titleView.setText(Html.fromHtml(titleText));

        ImageButton buttonAbout = findViewById(R.id.buttonBack);

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Page 1
                Intent intent = new Intent(AboutUsActivity_FruitClassifications.this, FruitCategoriesActivity.class);
                Toast.makeText(AboutUsActivity_FruitClassifications.this, "Fruit Classifications", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}