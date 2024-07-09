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

import com.example.cyberfruitionmain.CaptureMultipleActivity;
import com.example.cyberfruitionmain.R;

public class AboutUsActivity_MultipleCapture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_about_mc);
        TextView titleView = findViewById(R.id.titleView);

        String titleText = "Welcome to <font color='#d04e17'>CyberFruition</font>!";

        titleView.setText(Html.fromHtml(titleText));

        ImageButton buttonAbout = findViewById(R.id.buttonBack);

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Page 1
                Intent intent = new Intent(AboutUsActivity_MultipleCapture.this, CaptureMultipleActivity.class);
                Toast.makeText(AboutUsActivity_MultipleCapture.this, "Multiple Capture", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}