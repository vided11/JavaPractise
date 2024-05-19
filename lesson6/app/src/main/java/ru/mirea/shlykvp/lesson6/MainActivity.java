package ru.mirea.shlykvp.lesson6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.shlykvp.lesson6.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        binding.editTextList.setText(sharedPref.getString("LIST", ""));
        binding.editTextGroup.setText(sharedPref.getString("GROUP", ""));
        binding.editTextFilm.setText(sharedPref.getString("FILM", ""));
    }

    public void SaveData(View view) {
        editor.putString("LIST", binding.editTextList.getText().toString());
        editor.putString("GROUP", binding.editTextGroup.getText().toString());
        editor.putString("FILM", binding.editTextFilm.getText().toString());
        editor.apply();
    }
}