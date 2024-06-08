package ru.mirea.shlykvp.lesson4;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.shlykvp.lesson4.databinding.ActivityPlayerBinding;


public class MPlayer extends AppCompatActivity {

    private ActivityPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set click listeners on the buttons
        binding.prevButton.setOnClickListener(v -> onPrevButtonClicked());
        binding.playButton.setOnClickListener(v -> onPlayButtonClicked());
        binding.pauseButton.setOnClickListener(v -> onPauseButtonClicked());
        binding.nextButton.setOnClickListener(v -> onNextButtonClicked());
    }

    // Define the click event methods
    public void onPrevButtonClicked() {
        Log.d("PlayerActivity", "Previous button clicked");
    }

    public void onPlayButtonClicked() {
        Log.d("PlayerActivity", "Play button clicked");
    }

    public void onPauseButtonClicked() {
        Log.d("PlayerActivity", "Pause button clicked");
    }

    public void onNextButtonClicked() {
        Log.d("PlayerActivity", "Next button clicked");
    }
}