package ru.mirea.shlykvp.data_thread;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.concurrent.TimeUnit;

import ru.mirea.shlykvp.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.tvInfo.append(getString(R.string.runn1_description));
            }
        };

        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.tvInfo.append(getString(R.string.runn2_description));
            }
        };

        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.tvInfo.append(getString(R.string.runn3_description));
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 2000);
                    binding.tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        binding.tvInfo.setText(getString(R.string.launch_sequence));
        t.start();
    }
}