package com.example.cyberfruitionmain;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;

import com.example.cyberfruitionmain.AboutUs.AboutUsActivity_MultipleCapture;
import com.example.cyberfruitionmain.ml.Model2;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.example.cyberfruitionmain.AboutUs.AboutUsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CaptureMultipleActivity extends AppCompatActivity {

    TextView result;
    TextView emptyInfoTextView, bananaInfoTextView, orangeInfoTextView, appleInfoTextView,
            pineappleInfoTextView, mangoInfoTextView, lanzonesInfoTextView, jackfruitInfoTextView,
            appleAndJackfruitInfoTextView, appleAndLanzonesInfoTextView,lanzonesAndappleInfoTextView, appleAndMangoInfoTextView,
           appleAndOrangeInfoTextView,orangeAndappleInfoTextView, papayaInfoTextView, pineapplesInfoTextView, calamansiInfoTextView,
            appleAndPapayaInfoTextView,papayaAndappleInfoTextView, appleAndCalamansiInfoTextView,calamansiAndappleInfoTextView, appleAndPineappleInfoTextView,pineappleAndappleInfoTextView,
            appleAndBananaInfoTextView,bananaAndappleInfoTextView, bananaAndCalamansiInfoTextView, calamansiAndbananaInfoTextView, bananaAndPineappleInfoTextView,pineappleAndbananaInfoTextView,
            bananaAndPapayaInfoTextView, papayaAndbananaInfoTextView, bananaAndOrangeInfoTextView, orangeAndbananaInfoTextView, bananaAndMangoInfoTextView, mangoAndbananaInfoTextView,
            bananaAndLanzonesInfoTextView,lanzonesAndBananaInfoTextView, bananaAndJackfruitInfoTextView, jackfruitAndPapayaInfoTextView,
            jackfruitAndMangoInfoTextView, jackfruitAndCalamansiInfoTextView, jackfruitAndOrangeInfoTextView,
            jackfruitAndLanzonesInfoTextView, durianInfoTextView, bananaAndDurianInfoTextView,
            durianAndJackfruitInfoTextView, calamansiAndDurianInfoTextView, durianAndPineappleInfoTextView,
            durianAndPapayaInfoTextView, durianAndMangoInfoTextView, durianAndOrangeInfoTextView,
            durianAndLanzonesInfoTextView, pineappleAndCalamansiInfoTextView,CalamansiAndpineappleInfoTextView, pineappleAndLanzonesInfoTextView, lanzonesAndpineappleInfoTextView,
            pineappleAndMangoInfoTextView,mangoAndpineappleInfoTextView, pineappleAndOrangeInfoTextView,orangeAndpineappleInfoTextView, papayaAndPineappleInfoTextView, pineappleAndpapayaInfoTextView,
            appleAndDurianInfoTextView, lanzonesAndPapayaInfoTextView, papayaAndlanzonesInfoTextView, mangoAndPapayaInfoTextView, papayaAndmangoInfoTextView,
            orangeAndPapayaInfoTextView, papayaAndorangeInfoTextView, calamansiAndPapayaInfoTextView,papayaAndcalamansiInfoTextView, calamansiAndLanzonesInfoTextView,  lanzonesAndcalamansiInfoTextView,
            calamansiAndMangoInfoTextView,mangoAndcalamansiInfoTextView, calamansiAndOrangeInfoTextView, orangeAndcalamansiInfoTextView, lanzonesAndMangoInfoTextView,mangoAndlanzonesInfoTextView,
            lanzonesAndOrangeInfoTextView,calamansiAndpineappleInfoTextView, mangoAndappleInfoTextView, orangeAndlanzonesInfoTextView, mangoAndOrangeInfoTextView, orangeAndmangoInfoTextView, jackfruitAndPineappleInfoTextView;

    ImageView imageView;
    int imageSize = 224;
    ImageButton buttonCaptureCamera, buttonSelectImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_capture_multiple);

        result = findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);

        // Informations
        emptyInfoTextView = findViewById(R.id.emptyInfoTextView);
        appleInfoTextView = findViewById(R.id.appleInfoTextView);
        bananaInfoTextView = findViewById(R.id.bananaInfoTextView);
        orangeInfoTextView = findViewById(R.id.orangeInfoTextView);
        pineappleInfoTextView = findViewById(R.id.pineappleInfoTextView);
        mangoInfoTextView = findViewById(R.id.mangoInfoTextView);
        lanzonesInfoTextView = findViewById(R.id.lanzonesInfoTextView);
        jackfruitInfoTextView = findViewById(R.id.jackfruitInfoTextView);
        papayaInfoTextView = findViewById(R.id.papayaInfoTextView);
        pineapplesInfoTextView = findViewById(R.id.pineapplesInfoTextView);
        calamansiInfoTextView = findViewById(R.id.calamansiInfoTextView);
        appleInfoTextView = findViewById(R.id.appleInfoTextView);
        appleAndJackfruitInfoTextView = findViewById(R.id.appleAndJackfruitInfoTextView);
        appleAndLanzonesInfoTextView = findViewById(R.id.appleAndLanzonesInfoTextView);
        lanzonesAndappleInfoTextView = findViewById(R.id.lanzonesAndappleInfoTextView);
        appleAndMangoInfoTextView = findViewById(R.id.appleAndMangoInfoTextView);
        mangoAndappleInfoTextView = findViewById(R.id.mangoAndappleInfoTextView);
        appleAndOrangeInfoTextView = findViewById(R.id.appleAndOrangeInfoTextView);
        orangeAndappleInfoTextView = findViewById(R.id.orangeAndappleInfoTextView);
        appleAndPapayaInfoTextView = findViewById(R.id.appleAndPapayaInfoTextView);
        papayaAndappleInfoTextView = findViewById(R.id.papayaAndappleInfoTextView);
        appleAndCalamansiInfoTextView = findViewById(R.id.appleAndCalamansiInfoTextView);
        calamansiAndappleInfoTextView = findViewById(R.id.calamansiAndappleInfoTextView);
        appleAndPineappleInfoTextView = findViewById(R.id.appleAndPineappleInfoTextView);
        appleAndPineappleInfoTextView = findViewById(R.id.pineappleAndappleInfoTextView);
        appleAndBananaInfoTextView = findViewById(R.id.appleAndBananaInfoTextView);
        bananaAndappleInfoTextView = findViewById(R.id.bananaAndappleInfoTextView);
        bananaAndCalamansiInfoTextView = findViewById(R.id.bananaAndCalamansiInfoTextView);
        calamansiAndbananaInfoTextView = findViewById(R.id.calamansiAndbananaInfoTextView);
        bananaAndPineappleInfoTextView = findViewById(R.id.bananaAndPineappleInfoTextView);
        pineappleAndbananaInfoTextView = findViewById(R.id.pineappleAndbananaInfoTextView);
        bananaAndPapayaInfoTextView = findViewById(R.id.bananaAndPapayaInfoTextView);
        papayaAndbananaInfoTextView = findViewById(R.id.papayaAndbananaInfoTextView);
        bananaAndOrangeInfoTextView = findViewById(R.id.bananaAndOrangeInfoTextView);
        orangeAndbananaInfoTextView = findViewById(R.id.orangeAndbananaInfoTextView);
        bananaAndMangoInfoTextView = findViewById(R.id.bananaAndMangoInfoTextView);
        mangoAndbananaInfoTextView = findViewById(R.id.mangoAndbananaInfoTextView);
        bananaAndLanzonesInfoTextView = findViewById(R.id.bananaAndLanzonesInfoTextView);
        lanzonesAndBananaInfoTextView = findViewById(R.id.lanzonesAndBananaInfoTextView);
        bananaAndJackfruitInfoTextView = findViewById(R.id.bananaAndJackfruitInfoTextView);
        jackfruitAndPapayaInfoTextView = findViewById(R.id.jackfruitAndPapayaInfoTextView);
        jackfruitAndMangoInfoTextView = findViewById(R.id.jackfruitAndMangoInfoTextView);
        jackfruitAndCalamansiInfoTextView = findViewById(R.id.jackfruitAndCalamansiInfoTextView);
        jackfruitAndOrangeInfoTextView = findViewById(R.id.jackfruitAndOrangeInfoTextView);
        jackfruitAndLanzonesInfoTextView = findViewById(R.id.jackfruitAndLanzonesInfoTextView);
        jackfruitAndPineappleInfoTextView = findViewById(R.id.jackfruitAndPineappleInfoTextView);
        appleAndDurianInfoTextView = findViewById(R.id.appleAndDurianInfoTextView);
        durianInfoTextView = findViewById(R.id.durianInfoTextView);
        bananaAndDurianInfoTextView = findViewById(R.id.bananaAndDurianInfoTextView);
        bananaAndJackfruitInfoTextView = findViewById(R.id.bananaAndJackfruitInfoTextView);
        durianAndJackfruitInfoTextView = findViewById(R.id.durianAndJackfruitInfoTextView);
        calamansiAndDurianInfoTextView = findViewById(R.id.calamansiAndDurianInfoTextView);
        durianAndPineappleInfoTextView = findViewById(R.id.durianAndPineappleInfoTextView);
        durianAndPapayaInfoTextView = findViewById(R.id.durianAndPapayaInfoTextView);
        durianAndMangoInfoTextView = findViewById(R.id.durianAndMangoInfoTextView);
        durianAndOrangeInfoTextView = findViewById(R.id.durianAndOrangeInfoTextView);
        durianAndLanzonesInfoTextView = findViewById(R.id.durianAndLanzonesInfoTextView);
        pineappleAndCalamansiInfoTextView = findViewById(R.id.pineappleAndCalamansiInfoTextView);
        calamansiAndpineappleInfoTextView = findViewById(R.id.calamansiAndpineappleInfoTextView);
        pineappleAndLanzonesInfoTextView = findViewById(R.id.pineappleAndLanzonesInfoTextView);
        lanzonesAndpineappleInfoTextView = findViewById(R.id.lanzonesAndpineappleInfoTextView);
        pineappleAndMangoInfoTextView = findViewById(R.id.pineappleAndMangoInfoTextView);
        mangoAndpineappleInfoTextView = findViewById(R.id.mangoAndpineappleInfoTextView);
        pineappleAndOrangeInfoTextView = findViewById(R.id.pineappleAndOrangeInfoTextView);
        orangeAndpineappleInfoTextView = findViewById(R.id.orangeAndpineappleInfoTextView);
        papayaAndPineappleInfoTextView = findViewById(R.id.papayaAndPineappleInfoTextView);
        pineappleAndpapayaInfoTextView = findViewById(R.id.pineappleAndpapayaInfoTextView);
        lanzonesAndPapayaInfoTextView = findViewById(R.id.lanzonesAndPapayaInfoTextView);
        papayaAndlanzonesInfoTextView = findViewById(R.id.papayaAndlanzonesInfoTextView);
        mangoAndPapayaInfoTextView = findViewById(R.id.mangoAndPapayaInfoTextView);
        papayaAndmangoInfoTextView = findViewById(R.id.papayaAndmangoInfoTextView);
        orangeAndPapayaInfoTextView = findViewById(R.id.orangeAndPapayaInfoTextView);
        orangeAndPapayaInfoTextView = findViewById(R.id.papayaAndorangeInfoTextView);
        calamansiAndPapayaInfoTextView = findViewById(R.id.calamansiAndPapayaInfoTextView);
        papayaAndcalamansiInfoTextView = findViewById(R.id.papayaAndcalamansiInfoTextView);
        calamansiAndLanzonesInfoTextView = findViewById(R.id.calamansiAndLanzonesInfoTextView);
        lanzonesAndcalamansiInfoTextView = findViewById(R.id.lanzonesAndcalamansiInfoTextView);
        calamansiAndMangoInfoTextView = findViewById(R.id.calamansiAndMangoInfoTextView);
        mangoAndcalamansiInfoTextView = findViewById(R.id.mangoAndcalamansiInfoTextView);
        calamansiAndOrangeInfoTextView = findViewById(R.id.calamansiAndOrangeInfoTextView);
        orangeAndcalamansiInfoTextView = findViewById(R.id.orangeAndcalamansiInfoTextView);
        lanzonesAndMangoInfoTextView = findViewById(R.id.lanzonesAndMangoInfoTextView);
        mangoAndlanzonesInfoTextView = findViewById(R.id.mangoAndlanzonesInfoTextView);
        lanzonesAndOrangeInfoTextView = findViewById(R.id.lanzonesAndOrangeInfoTextView);
        orangeAndlanzonesInfoTextView = findViewById(R.id.orangeAndlanzonesInfoTextView);
        mangoAndOrangeInfoTextView = findViewById(R.id.mangoAndOrangeInfoTextView);
        orangeAndmangoInfoTextView = findViewById(R.id.orangeAndmangoInfoTextView);
        pineappleAndappleInfoTextView = findViewById(R.id.pineappleAndappleInfoTextView);
        CalamansiAndpineappleInfoTextView = findViewById(R.id.calamansiAndpineappleInfoTextView);
       papayaAndorangeInfoTextView = findViewById(R.id.papayaAndorangeInfoTextView);
        ImageButton buttonCaptureCamera = findViewById(R.id.buttonCaptureCamera);
        ImageButton buttonCaptureCameraBigger = findViewById(R.id.buttonCaptureCameraBigger);
        ImageButton buttonSelectImage = findViewById(R.id.buttonSelectImage);
        ImageButton buttonSelectImageBigger = findViewById(R.id.buttonSelectImageBigger);

        buttonCaptureCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });

        buttonCaptureCameraBigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        buttonSelectImageBigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        ImageButton buttonAbout = findViewById(R.id.aboutButton);
        ImageButton buttonBack = findViewById(R.id.buttonBack);

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Page 1
                Intent intent = new Intent(CaptureMultipleActivity.this, AboutUsActivity_MultipleCapture.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to Page 2
                Intent intent = new Intent(CaptureMultipleActivity.this, HomeActivity.class);
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
                    startActivity(new Intent(getApplicationContext(), FruitCategoriesActivity.class));
                    Toast.makeText(CaptureMultipleActivity.this, "Fruit Classifications", Toast.LENGTH_SHORT).show();
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
            }
            return false;
        });
    }
    private void showItemInfo(String itemName, String itemInfo, TextView itemInfoTextView) {
        // Update the TextView with item information
        itemInfoTextView.setText(itemInfo);
        CharSequence styledText = HtmlCompat.fromHtml(itemInfo, HtmlCompat.FROM_HTML_MODE_LEGACY);
        itemInfoTextView.setText(styledText);
        itemInfoTextView.setVisibility(View.VISIBLE);
    }


    public void classifyImage(Bitmap image){
        try {
            Model2 model2 = Model2.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            // get 1D array of 224 * 224 pixels in image
            int [] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            // iterate over pixels and extract R, G, and B values. Add to bytebuffer.
            int pixel = 0;
            for(int i = 0; i < imageSize; i++){
                for(int j = 0; j < imageSize; j++){
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }
            // Reset information TextViews before updating
            emptyInfoTextView.setText("");
            bananaInfoTextView.setText("");
            orangeInfoTextView.setText("");
            appleInfoTextView.setText("");
            pineappleInfoTextView.setText("");
            mangoInfoTextView.setText("");
            lanzonesInfoTextView.setText("");
            jackfruitInfoTextView.setText("");
            durianInfoTextView.setText("");
            appleAndJackfruitInfoTextView.setText("");
            appleAndLanzonesInfoTextView.setText("");
            lanzonesAndappleInfoTextView.setText("");
            appleAndMangoInfoTextView.setText("");
            mangoAndappleInfoTextView.setText("");
            appleAndOrangeInfoTextView.setText("");
            orangeAndappleInfoTextView.setText("");
            papayaInfoTextView.setText("");
            pineapplesInfoTextView.setText("");
            calamansiInfoTextView.setText("");
            appleAndPapayaInfoTextView.setText("");
            papayaAndappleInfoTextView.setText("");
            appleAndCalamansiInfoTextView.setText("");
            calamansiAndappleInfoTextView.setText("");
            appleAndPineappleInfoTextView.setText("");
            pineappleAndappleInfoTextView.setText("");
            appleAndBananaInfoTextView.setText("");
            bananaAndappleInfoTextView.setText("");
            bananaAndCalamansiInfoTextView.setText("");
            calamansiAndbananaInfoTextView.setText("");
            bananaAndPineappleInfoTextView.setText("");
            pineappleAndbananaInfoTextView.setText("");
            bananaAndPapayaInfoTextView.setText("");
            papayaAndbananaInfoTextView.setText("");
            bananaAndOrangeInfoTextView.setText("");
            orangeAndbananaInfoTextView.setText("");
            bananaAndMangoInfoTextView.setText("");
            mangoAndbananaInfoTextView.setText("");
            bananaAndLanzonesInfoTextView.setText("");
            lanzonesAndBananaInfoTextView.setText("");
            bananaAndJackfruitInfoTextView.setText("");
            jackfruitAndPapayaInfoTextView.setText("");
            jackfruitAndMangoInfoTextView.setText("");
            jackfruitAndCalamansiInfoTextView.setText("");
            jackfruitAndOrangeInfoTextView.setText("");
            jackfruitAndLanzonesInfoTextView.setText("");
            jackfruitAndPineappleInfoTextView.setText("");
            durianInfoTextView.setText("");
            bananaAndDurianInfoTextView.setText("");
            bananaAndJackfruitInfoTextView.setText("");
            durianAndJackfruitInfoTextView.setText("");
            calamansiAndDurianInfoTextView.setText("");
            durianAndPineappleInfoTextView.setText("");
            durianAndPapayaInfoTextView.setText("");
            durianAndMangoInfoTextView.setText("");
            durianAndOrangeInfoTextView.setText("");
            durianAndLanzonesInfoTextView.setText("");
            pineappleAndCalamansiInfoTextView.setText("");
            CalamansiAndpineappleInfoTextView.setText("");
            pineappleAndLanzonesInfoTextView.setText("");
            lanzonesAndpineappleInfoTextView.setText("");
            pineappleAndMangoInfoTextView.setText("");
            mangoAndpineappleInfoTextView.setText("");
            pineappleAndOrangeInfoTextView.setText("");
            orangeAndpineappleInfoTextView.setText("");
            papayaAndPineappleInfoTextView.setText("");
            pineappleAndpapayaInfoTextView.setText("");
            appleInfoTextView.setText("");
            lanzonesAndPapayaInfoTextView.setText("");
            papayaAndlanzonesInfoTextView.setText("");
            mangoAndPapayaInfoTextView.setText("");
            papayaAndmangoInfoTextView.setText("");
            orangeAndPapayaInfoTextView.setText("");
            papayaAndorangeInfoTextView.setText("");
            calamansiAndPapayaInfoTextView.setText("");
            papayaAndcalamansiInfoTextView.setText("");
            calamansiAndLanzonesInfoTextView.setText("");
            lanzonesAndcalamansiInfoTextView.setText("");
            calamansiAndMangoInfoTextView.setText("");
            mangoAndcalamansiInfoTextView.setText("");
            calamansiAndOrangeInfoTextView.setText("");
            orangeAndcalamansiInfoTextView.setText("");
            lanzonesAndMangoInfoTextView.setText("");
            mangoAndlanzonesInfoTextView.setText("");
            lanzonesAndOrangeInfoTextView.setText("");
            orangeAndlanzonesInfoTextView.setText("");
            mangoAndOrangeInfoTextView.setText("");
            orangeAndmangoInfoTextView.setText("");


            inputFeature0.loadBuffer(byteBuffer);


            // Runs model inference and gets result.
            Model2.Outputs outputs = model2.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            // find the index of the class with the biggest confidence.
            int maxPos = 0;
            float maxConfidence = 0;
            for(int i = 0; i < confidences.length; i++){
                if(confidences[i] > maxConfidence){
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {
                    "Apple and Orange",
                    "Apple and Papaya",
                    "Apple and Pineapple",
                    "Banana and Lanzones",
                    "Banana and Orange",
                    "Banana and Papaya",
                    "Apple and Lanzones",
                    "Banana and Pineapple",
                    "Calamansi and Lanzones",
                    "Calamansi and Orange",
                    "Calamansi and Papaya",
                    "Calamansi and Pineapple",
                    "Lanzones and Apple",
                    "Lanzones and Banana",
                    "Lanzones and Orange",
                    "Lanzones and Papaya",
                    "Lanzones and Pineapple",
                    "Lanzones and Mango",
                    "Mango and Lanzones",
                    "Mango and Orange",
                    "Mango and Papaya",
                    "Mango and Pineapple",
                    "Orange and Apple",
                    "Orange and Banana",
                    "Orange and Calamansi",
                    "Orange and Lanzones",
                    "Orange and Mango",
                    "Orange and Papaya",
                    "Orange and Pineapple",
                    "Papaya and Apple",
                    "Papaya and Banana",
                    "Papaya and Calamansi",
                    "Papaya and Lanzones",
                    "Papaya and Orange",
                    "Papaya and Pineapple",
                    "Pineapple and Apple",
                    "Pineapple and Banana",
                    "Pineapple and Calamansi",
                    "Pineapple and Lanzones",
                    "Pineapple and Mango",
                    "Lanzones and Calamansi",
                    "Apple and Banana",
                    "Banana and Apple",
                    "Apple and Mango",
                    "Mango and Apple",
                    "Banana and Calamansi",
                    "Calamansi and Banana",
                    "Calamansi and Mango",
                    "Mango and Calamansi",
                    "Banana and Mango",
                    "Mango and Banana",
                    "Apple and Calamansi",
                    "Calamansi and Apple",
                    "Papaya and Mango",
                    "Pineapple and Orange",
                    "Pineapple and Papaya",
                    "Unidentified Fruits"
            };

            CardView cardView = findViewById(R.id.imageCard);
            result.setText(classes[maxPos]);
            switch (classes[maxPos]) {
                case "Apple and Jackfruit":
                    showItemInfo(getString(R.string.apple_and_jackfruit), getString(R.string.apple_and_jackfruit_info), appleAndJackfruitInfoTextView);
                    break;
                case "Apple and Lanzones":
                    showItemInfo(getString(R.string.apple_and_lanzones), getString(R.string.apple_and_lanzones_info), appleAndLanzonesInfoTextView);
                    break;
                case "Lanzones and Apple":
                    showItemInfo(getString(R.string.lanzones_and_apple), getString(R.string.lanzones_and_apple_info), lanzonesAndappleInfoTextView);
                    break;
                case "Apple and Mango":
                    showItemInfo(getString(R.string.apple_and_mango), getString(R.string.apple_and_mango_info), appleAndMangoInfoTextView);
                    break;
                case "Mango and Apple":
                    showItemInfo(getString(R.string.mango_and_apple), getString(R.string.mango_and_apple_info), mangoAndappleInfoTextView);
                    break;
                case "Apple and Orange":
                    showItemInfo(getString(R.string.apple_and_orange), getString(R.string.apple_and_orange_info), appleAndOrangeInfoTextView);
                    break;
                case "Orange and Apple":
                    showItemInfo(getString(R.string.orange_and_apple), getString(R.string.orange_and_apple_info), orangeAndappleInfoTextView);
                    break;
                case "Apple and Papaya":
                    showItemInfo(getString(R.string.apple_and_papaya), getString(R.string.apple_and_papaya_info), appleAndPapayaInfoTextView);
                    break;
                case "Papaya and Apple":
                    showItemInfo(getString(R.string.papaya_and_apple), getString(R.string.papaya_and_apple_info), papayaAndappleInfoTextView);
                    break;
                case "Apple and Calamansi":
                    showItemInfo(getString(R.string.apple_and_calamansi), getString(R.string.apple_and_calamansi_info), appleAndCalamansiInfoTextView);
                    break;
                case "Calamansi and Apple":
                    showItemInfo(getString(R.string.calamansi_and_apple), getString(R.string.calamansi_and_apple_info), calamansiAndappleInfoTextView);
                    break;
                case "Apple and Pineapple":
                    showItemInfo(getString(R.string.apple_and_pineapple), getString(R.string.apple_and_pineapple_info), appleAndPineappleInfoTextView);
                    break;
                case "Pineapple and Apple":
                    showItemInfo(getString(R.string.pineapple_and_apple), getString(R.string.pineapple_and_apple_info), pineappleAndappleInfoTextView);
                    break;
                case "Apple and Banana":
                    showItemInfo(getString(R.string.apple_and_banana), getString(R.string.apple_and_banana_info), appleAndBananaInfoTextView);
                    break;
                case "Banana and Apple":
                    showItemInfo(getString(R.string.banana_and_apple), getString(R.string.banana_and_apple_info), bananaAndappleInfoTextView);
                    break;
                case "Banana and Calamansi":
                    showItemInfo(getString(R.string.banana_and_calamansi), getString(R.string.banana_and_calamansi_info), bananaAndCalamansiInfoTextView);
                    break;
                case "Calamansi and Banana":
                    showItemInfo(getString(R.string.calamansi_and_banana), getString(R.string.calamansi_and_banana_info), calamansiAndbananaInfoTextView);
                    break;
                case "Banana and Pineapple":
                    showItemInfo(getString(R.string.banana_and_pineapple), getString(R.string.banana_and_pineapple_info), bananaAndPineappleInfoTextView);
                    break;
                case "Pineapple and Banana":
                    showItemInfo(getString(R.string.pineapple_and_banana), getString(R.string.pineapple_and_banana_info), pineappleAndbananaInfoTextView);
                    break;
                case "Banana and Papaya":
                    showItemInfo(getString(R.string.banana_and_papaya), getString(R.string.banana_and_papaya_info), bananaAndPapayaInfoTextView);
                    break;
                case "Papaya and Banana":
                    showItemInfo(getString(R.string.papaya_and_banana), getString(R.string.papaya_and_banana_info), papayaAndbananaInfoTextView);
                    break;
                case "Banana and Orange":
                    showItemInfo(getString(R.string.banana_and_orange), getString(R.string.banana_and_orange_info), bananaAndOrangeInfoTextView);
                    break;
                case "Orange and Banana":
                    showItemInfo(getString(R.string.orange_and_banana), getString(R.string.orange_and_banana_info), orangeAndbananaInfoTextView);
                    break;
                case "Banana and Mango":
                    showItemInfo(getString(R.string.banana_and_mango), getString(R.string.banana_and_mango_info), bananaAndMangoInfoTextView);
                    break;
                case "Mango and Banana":
                    showItemInfo(getString(R.string.mango_and_banana), getString(R.string.mango_and_banana_info), mangoAndbananaInfoTextView);
                    break;
                case "Banana and Lanzones":
                    showItemInfo(getString(R.string.banana_and_lanzones), getString(R.string.banana_and_lanzones_info), bananaAndLanzonesInfoTextView);
                    break;
                case "Lanzones and Banana":
                    showItemInfo(getString(R.string.lanzones_and_banana), getString(R.string.lanzones_and_banana_info), lanzonesAndBananaInfoTextView);
                    break;
                case "Banana and Jackfruit":
                    showItemInfo(getString(R.string.banana_and_jackfruit), getString(R.string.banana_and_jackfruit_info), bananaAndJackfruitInfoTextView);
                    break;
                case "Jackfruit and Papaya":
                    showItemInfo(getString(R.string.jackfruit_and_papaya), getString(R.string.jackfruit_and_papaya_info), jackfruitAndPapayaInfoTextView);
                    break;
                case "Jackfruit and Mango":
                    showItemInfo(getString(R.string.jackfruit_and_mango), getString(R.string.jackfruit_and_mango_info), jackfruitAndMangoInfoTextView);
                    break;
                case "Jackfruit and Calamansi":
                    showItemInfo(getString(R.string.jackfruit_and_calamansi), getString(R.string.jackfruit_and_calamansi_info), jackfruitAndCalamansiInfoTextView);
                    break;
                case "Jackfruit and Orange":
                    showItemInfo(getString(R.string.jackfruit_and_orange), getString(R.string.jackfruit_and_orange_info), jackfruitAndOrangeInfoTextView);
                    break;
                case "Jackfruit and Lanzones":
                    showItemInfo(getString(R.string.jackfruit_and_lanzones), getString(R.string.jackfruit_and_lanzones_info), jackfruitAndLanzonesInfoTextView);
                    break;
                case "Jackfruit and Pineapple":
                    showItemInfo(getString(R.string.jackfruit_and_pineapple), getString(R.string.jackfruit_and_pineapple_info), jackfruitAndPineappleInfoTextView);
                    break;
                case "Apple and Durian":
                    showItemInfo(getString(R.string.apple_and_durian), getString(R.string.apple_and_durian_info), appleAndDurianInfoTextView);
                    break;
                case "Banana and Durian":
                    showItemInfo(getString(R.string.banana_and_durian), getString(R.string.banana_and_durian_info), bananaAndDurianInfoTextView);
                    break;
                case "Durian and Jackfruit":
                    showItemInfo(getString(R.string.durian_and_jackfruit), getString(R.string.durian_and_jackfruit_info), durianAndJackfruitInfoTextView);
                    break;
                case "Calamansi and Durian":
                    showItemInfo(getString(R.string.durian_and_jackfruit), getString(R.string.durian_and_jackfruit_info), durianAndJackfruitInfoTextView);
                    break;
                case "Durian and Pineapple":
                    showItemInfo(getString(R.string.durian_and_pineapple), getString(R.string.durian_and_pineapple_info), durianAndPineappleInfoTextView);
                    break;
                case "Durian and Papaya":
                    showItemInfo(getString(R.string.durian_and_papaya), getString(R.string.durian_and_papaya_info), durianAndPapayaInfoTextView);
                    break;
                case "Durian and Mango":
                    showItemInfo(getString(R.string.durian_and_mango), getString(R.string.durian_and_mango_info), durianAndMangoInfoTextView);
                    break;
                case "Durian and Orange":
                    showItemInfo(getString(R.string.durian_and_orange), getString(R.string.durian_and_orange_info), durianAndOrangeInfoTextView);
                    break;
                case "Durian and Lanzones":
                    showItemInfo(getString(R.string.durian_and_lanzones), getString(R.string.durian_and_lanzones_info), durianAndLanzonesInfoTextView);
                    break;
                case "Pineapple and Calamansi":
                    showItemInfo(getString(R.string.pineapple_and_calamansi), getString(R.string.pineapple_and_calamansi_info), pineappleAndCalamansiInfoTextView);
                    break;
                case "Calamansi and Pineapple":
                    showItemInfo(getString(R.string.calamansi_and_pineapple), getString(R.string.pineapple_and_calamansi_info), pineappleAndCalamansiInfoTextView);
                    break;
                case "Pineapple and Lanzones":
                    showItemInfo(getString(R.string.pineapple_and_lanzones), getString(R.string.pineapple_and_lanzones_info), pineappleAndCalamansiInfoTextView);
                    break;
                case "Lanzones and Pineapple":
                    showItemInfo(getString(R.string.lanzones_and_pineapple), getString(R.string.lanzones_and_pineapple_info), CalamansiAndpineappleInfoTextView);
                    break;
                case "Pineapple and Mango":
                    showItemInfo(getString(R.string.pineapple_and_mango), getString(R.string.pineapple_and_mango_info), pineappleAndMangoInfoTextView);
                    break;
                case "Mango and Pineapple":
                    showItemInfo(getString(R.string.mango_and_pineapple), getString(R.string.mango_and_pineapple_info), mangoAndpineappleInfoTextView);
                    break;
                case "Pineapple and Orange":
                    showItemInfo(getString(R.string.pineapple_and_orange), getString(R.string.pineapple_and_orange_info), pineappleAndOrangeInfoTextView);
                    break;
                case "Orange and Pineapple":
                    showItemInfo(getString(R.string.orange_and_pineapple), getString(R.string.orange_and_pineapple_info), orangeAndpineappleInfoTextView);
                    break;
                case "Papaya and Pineapple":
                    showItemInfo(getString(R.string.papaya_and_pineapple), getString(R.string.papaya_and_pineapple_info), papayaAndPineappleInfoTextView);
                    break;
                case "Pineapple and Papaya":
                    showItemInfo(getString(R.string.pineapple_and_papaya), getString(R.string.pineapple_and_papaya_info), pineappleAndpapayaInfoTextView);
                    break;
                case "Lanzones and Papaya":
                    showItemInfo(getString(R.string.lanzones_and_papaya), getString(R.string.lanzones_and_papaya_info), lanzonesAndPapayaInfoTextView);
                    break;
                case "Papaya and Lanzones":
                    showItemInfo(getString(R.string.papaya_and_lanzones), getString(R.string.papaya_and_lanzones_info), papayaAndlanzonesInfoTextView);
                    break;
                case "Mango and Papaya":
                    showItemInfo(getString(R.string.mango_and_papaya), getString(R.string.mango_and_papaya_info), mangoAndPapayaInfoTextView);
                    break;
                case "Papaya and Mango":
                    showItemInfo(getString(R.string.papaya_and_mango), getString(R.string.papaya_and_mango_info), papayaAndmangoInfoTextView);
                    break;
                case "Orange and Papaya":
                    showItemInfo(getString(R.string.orange_and_papaya), getString(R.string.orange_and_papaya_info), orangeAndPapayaInfoTextView);
                    break;
                case "Papaya and Orange":
                    showItemInfo(getString(R.string.papaya_and_orange), getString(R.string.papaya_and_orange_info), papayaAndorangeInfoTextView);
                    break;
                case "Calamansi and Papaya":
                    showItemInfo(getString(R.string.calamansi_and_papaya), getString(R.string.calamansi_and_papaya_info), calamansiAndPapayaInfoTextView);
                    break;
                case "Papaya and Calamansi":
                    showItemInfo(getString(R.string.papaya_and_calamansi), getString(R.string.papaya_and_calamansi_info),papayaAndcalamansiInfoTextView);
                    break;
                case "Calamansi and Lanzones":
                    showItemInfo(getString(R.string.calamansi_and_lanzones), getString(R.string.calamansi_and_lanzones_info), calamansiAndLanzonesInfoTextView);
                    break;
                case "Lanzones and Calamansi":
                    showItemInfo(getString(R.string.lanzones_and_calamansi), getString(R.string.lanzones_and_calamansi_info), lanzonesAndcalamansiInfoTextView);
                    break;
                case "Calamansi and Mango":
                    showItemInfo(getString(R.string.calamansi_and_mango), getString(R.string.calamansi_and_mango_info), calamansiAndMangoInfoTextView);
                    break;
                case "Mango and Calamansi":
                    showItemInfo(getString(R.string.mango_and_calamansi), getString(R.string.mango_and_calamansi_info), mangoAndcalamansiInfoTextView);
                    break;
                case "Calamansi and Orange":
                    showItemInfo(getString(R.string.calamansi_and_orange), getString(R.string.calamansi_and_orange_info), calamansiAndOrangeInfoTextView);
                    break;
                case "Orange and Calamansi":
                    showItemInfo(getString(R.string.orange_and_calamansi), getString(R.string.orange_and_calamansi_info), orangeAndcalamansiInfoTextView);
                    break;
                case "Lanzones and Mango":
                    showItemInfo(getString(R.string.lanzones_and_mango), getString(R.string.lanzones_and_mango_info), lanzonesAndMangoInfoTextView);
                    break;
                case "Mango and Lanzones":
                    showItemInfo(getString(R.string.mango_and_lanzones), getString(R.string.mango_and_lanzones_info), mangoAndlanzonesInfoTextView);
                    break;
                case "Lanzones and Orange":
                    showItemInfo(getString(R.string.lanzones_and_orange), getString(R.string.lanzones_and_orange_info), lanzonesAndOrangeInfoTextView);
                    break;
                case "Orange and Lanzones":
                    showItemInfo(getString(R.string.orange_and_lanzones), getString(R.string.orange_and_lanzones_info), orangeAndlanzonesInfoTextView);
                    break;
                case "Mango and Orange":
                    showItemInfo(getString(R.string.mango_and_orange), getString(R.string.mango_and_orange_info), mangoAndOrangeInfoTextView);
                    break;
                case "Orange and Mango":
                    showItemInfo(getString(R.string.orange_and_mango), getString(R.string.orange_and_mango_info), orangeAndmangoInfoTextView);
                    break;
                case "Unidentified fruits":
                    cardView.setCardBackgroundColor(Color.RED);
                    result.setTextColor(Color.RED);
                    break;
                default:
                    findViewById(R.id.emptyInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.orangeInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.pineappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.mangoInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.lanzonesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.jackfruitInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.durianInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleAndJackfruitInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleAndLanzonesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.lanzonesAndappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleAndMangoInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.mangoAndappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleAndOrangeInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.orangeAndappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.papayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.pineapplesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.calamansiInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleAndPapayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.papayaAndappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleAndCalamansiInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.calamansiAndappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleAndPineappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.pineappleAndappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleAndBananaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaAndappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaAndCalamansiInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.calamansiAndbananaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaAndPineappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.pineappleAndbananaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaAndPapayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.papayaAndbananaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaAndOrangeInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.orangeAndbananaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaAndMangoInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.mangoAndbananaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaAndLanzonesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.lanzonesAndBananaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaAndJackfruitInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.jackfruitAndPapayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.jackfruitAndMangoInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.jackfruitAndCalamansiInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.jackfruitAndOrangeInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.jackfruitAndLanzonesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.jackfruitAndPineappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleAndDurianInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.durianInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaAndDurianInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.bananaAndJackfruitInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.durianAndJackfruitInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.calamansiAndDurianInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.durianAndPineappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.durianAndPapayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.durianAndMangoInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.durianAndOrangeInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.durianAndLanzonesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.pineappleAndCalamansiInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.calamansiAndpineappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.pineappleAndLanzonesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.lanzonesAndpineappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.pineappleAndMangoInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.mangoAndpineappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.pineappleAndOrangeInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.orangeAndpineappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.papayaAndPineappleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.pineappleAndpapayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.appleInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.lanzonesAndPapayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.papayaAndlanzonesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.mangoAndPapayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.papayaAndmangoInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.orangeAndPapayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.papayaAndorangeInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.calamansiAndPapayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.papayaAndcalamansiInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.calamansiAndLanzonesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.lanzonesAndcalamansiInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.calamansiAndMangoInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.mangoAndcalamansiInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.calamansiAndOrangeInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.orangeAndcalamansiInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.lanzonesAndMangoInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.mangoAndlanzonesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.lanzonesAndOrangeInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.orangeAndlanzonesInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.mangoAndOrangeInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.orangeAndmangoInfoTextView).setVisibility(View.GONE);
                    cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.orange));

                    break;
            }

            findViewById(R.id.buttonCaptureCamera).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonSelectImage).setVisibility(View.VISIBLE);
            findViewById(R.id.textViewCaptureCamera).setVisibility(View.VISIBLE);
            findViewById(R.id.textViewSelectImage).setVisibility(View.VISIBLE);
            findViewById(R.id.textViewCaptureCameraBigger).setVisibility(View.GONE);
            findViewById(R.id.textViewSelectImageBigger).setVisibility(View.GONE);
            findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
            hideBiggerImageButtons();


            // Releases model resources if no longer used.
            model2.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // File picker result
            try {
                // Get the selected image from the file picker
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(inputStream);

                // Resize the image
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);

                // Display the image
                imageView.setImageBitmap(image);

                // Resize the image to the required input size for the model
                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);

                // Perform image classification
                classifyImage(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            // Camera capture result
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(image);

            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
            classifyImage(image);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    private void captureImage() {
        // Launch camera if we have permission
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 2);
        } else {
            // Request camera permission if we don't have it.
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 200);
        }


    }

    private void selectImage() {
        // Launch the file picker for image selection
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, 1);

    }

    // Function to hide the bigger image buttons
    private void hideBiggerImageButtons() {
        findViewById(R.id.buttonCaptureCameraBigger).setVisibility(View.GONE);
        findViewById(R.id.buttonSelectImageBigger).setVisibility(View.GONE);
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
                Intent intent = new Intent(CaptureMultipleActivity.this, CaptureSingleActivity.class);
                Toast.makeText(CaptureMultipleActivity.this, "Single Capture", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
                dialog.dismiss();
            }
        });

        btnMultipleCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CaptureMultipleActivity.this, "Multiple Capture", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}