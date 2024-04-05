package ru.shlykvp.dialog;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dialog.R;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTimePicker = findViewById(R.id.btnTimePicker);
        Button btnDatePicker = findViewById(R.id.btnDatePicker);
        Button btnProgressDialog = findViewById(R.id.btnProgressDialog);

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTimeDialogFragment timeDialogFragment = new MyTimeDialogFragment();
                timeDialogFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDateDialogFragment dateDialogFragment = new MyDateDialogFragment();
                dateDialogFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        btnProgressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyProgressDialogFragment progressDialogFragment = new MyProgressDialogFragment();
                progressDialogFragment.show(getSupportFragmentManager(), "progressDialog");

                // Имитация задержки для демонстрации ProgressDialog
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialogFragment.dismiss();
                        showSnackbar("Операция завершена");
                    }
                }, 3000);
            }
        });
    }

    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }
    public void onClickShowDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }
}