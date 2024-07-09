package com.example.cyberfruitionmain.AboutUs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyberfruitionmain.FruitCategoriesActivity;
import com.example.cyberfruitionmain.HomeActivity;
import com.example.cyberfruitionmain.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_about);
        TextView titleView = findViewById(R.id.titleView);

        String titleText = "Welcome to <font color='#d04e17'>CyberFruition</font>!";

        titleView.setText(Html.fromHtml(titleText));

        ImageButton buttonAbout = findViewById(R.id.buttonBack);

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Page 1
                Intent intent = new Intent(AboutUsActivity.this, HomeActivity.class);
                Toast.makeText(AboutUsActivity.this, "Home Page", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}