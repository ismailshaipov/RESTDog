package com.example.restdog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";

    private ImageView imageViewDog;
    private Button loadDogImageBtn;
    private ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        loadDogImage();
    }

    private void init() {
        imageViewDog = findViewById(R.id.imageViewDog);
        loadDogImageBtn = findViewById(R.id.loadDogImageBtn);
        progress_circular = findViewById(R.id.progress_circular);

    }

    private void loadDogImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(BASE_URL);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder date = new StringBuilder();
                    String result;

                    do {
                        result = bufferedReader.readLine();
                        if (result != null){
                            date.append(result);
                        }
                    }while (result != null);

                    Log.d("MainActivity", date.toString());
                } catch (Exception e) {
                    Log.d("MainActivity", e.toString());
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}