package ru.mirea.shlykvp.intentapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textViewMessage = findViewById(R.id.textViewMessage);

        String currentTime = getIntent().getStringExtra("currentTime");
        String message = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ 961, а текущее время " + currentTime;

        textViewMessage.setText(message);
    }
}