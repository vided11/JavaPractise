package ru.shlykvp.toastapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toastapp.R;

public class MainActivity extends AppCompatActivity {

    private EditText inputField;
    private Button countButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.input_field);
        countButton = findViewById(R.id.count_button);

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputField.getText().toString();
                int characterCount = input.length();

                String message = "СТУДЕНТ No 31 ГРУППА 4 Количество символов - " + characterCount;
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}