package com.example.cyberfruitionmain;

import android.Manifest;
import android.content.Intent;
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

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.example.cyberfruitionmain.AboutUs.AboutUsActivity_SingleCapture;
import com.example.cyberfruitionmain.ml.Model;

import com.example.cyberfruitionmain.AboutUs.AboutUsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CaptureSingleActivity extends AppCompatActivity {

    TextView result;
    TextView emptyInfoTextView, bananaInfoTextView, orangeInfoTextView, appleInfoTextView, pineappleInfoTextView, mangoInfoTextView, lanzonesInfoTextView, jackfruitInfoTextView, papayaInfoTextView, calamansiInfoTextView, durianInfoTextView;

    ImageView imageView;
    int imageSize = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_single);

        result = findViewById(R.id.result);
        imageView = findViewById(R.id.imageView);

        // Informations
        emptyInfoTextView = findViewById(R.id.emptyInfoTextView);
        bananaInfoTextView = findViewById(R.id.bananaInfoTextView);
        orangeInfoTextView = findViewById(R.id.orangeInfoTextView);
        appleInfoTextView = findViewById(R.id.appleInfoTextView);
        pineappleInfoTextView = findViewById(R.id.pineappleInfoTextView);
        mangoInfoTextView = findViewById(R.id.mangoInfoTextView);
        lanzonesInfoTextView = findViewById(R.id.lanzonesInfoTextView);
        jackfruitInfoTextView = findViewById(R.id.jackfruitInfoTextView);
        papayaInfoTextView = findViewById(R.id.papayaInfoTextView);
        calamansiInfoTextView = findViewById(R.id.calamansiInfoTextView);
        durianInfoTextView = findViewById(R.id.durianInfoTextView);

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
            Intent intent = new Intent(CaptureSingleActivity.this, AboutUsActivity_SingleCapture.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Navigate to Page 2
            Intent intent = new Intent(CaptureSingleActivity.this, HomeActivity.class);
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
                    Toast.makeText(CaptureSingleActivity.this, "Fruit Classifications", Toast.LENGTH_SHORT).show();
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
            Model model = Model.newInstance(getApplicationContext());

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
            papayaInfoTextView.setText("");
            calamansiInfoTextView.setText("");
            durianInfoTextView.setText("");


            inputFeature0.loadBuffer(byteBuffer);


            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
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
                    "Apple",
                    "Orange",
                    "Banana",
                    "Pineapple",
                    "Mango",
                    "Papaya",
                    "Calamansi",
                    "Durian",
                    "Jackfruit",
                    "Lanzones",
                    "Unidentified fruit"
            };
            CardView cardView = findViewById(R.id.imageCard);
            result.setText(classes[maxPos]);
            switch (classes[maxPos]) {
                case "Apple":
                    showItemInfo(getString(R.string.apple), getString(R.string.apple_info), appleInfoTextView);
                    break;
                case "Banana":
                    showItemInfo(getString(R.string.banana), getString(R.string.banana_info), bananaInfoTextView);
                    break;
                case "Orange":
                    showItemInfo(getString(R.string.orange), getString(R.string.orange_info), orangeInfoTextView);
                    break;
                case "Pineapple":
                    showItemInfo(getString(R.string.pineapple), getString(R.string.pineapple_info), pineappleInfoTextView);
                    break;
                case "Mango":
                    showItemInfo(getString(R.string.mango), getString(R.string.mango_info), mangoInfoTextView);
                    break;
                case "Lanzones":
                    showItemInfo(getString(R.string.lanzones), getString(R.string.lanzones_info), lanzonesInfoTextView);
                    break;
                case "Jackfruit":
                    showItemInfo(getString(R.string.jackfruit), getString(R.string.jackfruit_info), jackfruitInfoTextView);
                    break;
                case "Papaya":
                    showItemInfo(getString(R.string.papaya), getString(R.string.papaya_info), papayaInfoTextView);
                    break;
                case "Calamansi":
                    showItemInfo(getString(R.string.calamansi), getString(R.string.calamansi_info), calamansiInfoTextView);
                    break;
                case "Durian":
                    showItemInfo(getString(R.string.durian), getString(R.string.durian_info), durianInfoTextView);
                    break;
                case "Unidentified fruit":
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
                    findViewById(R.id.papayaInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.calamansiInfoTextView).setVisibility(View.GONE);
                    findViewById(R.id.durianInfoTextView).setVisibility(View.GONE);
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
            model.close();
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
                Toast.makeText(CaptureSingleActivity.this, "Single Capture", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnMultipleCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaptureSingleActivity.this, CaptureMultipleActivity.class);
                Toast.makeText(CaptureSingleActivity.this, "Multiple Capture", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}