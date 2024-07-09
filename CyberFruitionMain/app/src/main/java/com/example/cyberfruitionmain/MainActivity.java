package com.example.cyberfruitionmain;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cyberfruitionmain.AboutUs.AboutUsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
    }

    public void openFruitScannerActivity(View view) {
        // Build a dialog to let the user choose between Model 1, Model 2, and Model 3
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Model")
                .setItems(new CharSequence[]{"Single Capture", "Multiple Capture", "Model 3"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the chosen model
                        Class<?> selectedActivityClass;
                        switch (which) {
                            case 0:
                                // Model 1 selected
                                selectedActivityClass = CaptureSingleActivity.class;
                                break;
                            case 1:
                                // Model 2 selected
                                selectedActivityClass = CaptureMultipleActivity.class;
                                break;
                            default:
                                // Default to Model 1
                                selectedActivityClass = CaptureSingleActivity.class;
                        }

                        // Start the selected scanner activity
                        startFruitScannerActivity(selectedActivityClass);
                    }
                });

        // Show the dialog
        builder.create().show();
    }

    private void startFruitScannerActivity(Class<?> activityClass) {
        // Start the selected scanner activity
        startActivity(new Intent(this, activityClass));
        showToast("Fruit Scanner");
        overridePendingTransition(0, 0);
    }

    public void openFruitCategoriesActivity(View view) {
        startActivity(new Intent(this, FruitCategoriesActivity.class));
        showToast("Fruit Categories");
        overridePendingTransition(0, 0);
    }

    public void openAboutUsActivity(View view) {
        startActivity(new Intent(this, AboutUsActivity.class));
        showToast("About Us");
        overridePendingTransition(0, 0);
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
