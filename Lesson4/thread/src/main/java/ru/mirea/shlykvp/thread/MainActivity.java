package ru.mirea.shlykvp.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTotalLessons;
    private int counter = 0;
    private EditText editTextTotalDays;
    private Button buttonMirea;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTotalLessons = findViewById(R.id.editTextTotalLessons);
        editTextTotalDays = findViewById(R.id.editTextTotalDays);
        buttonMirea = findViewById(R.id.buttonMirea);
        textView = findViewById(R.id.textView);

        Thread mainThread = Thread.currentThread();
        textView.setText("Имя текущего потока: " + mainThread.getName());

        // Меняем имя и выводим в текстовом поле
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 04, НОМЕР ПО СПИСКУ: 31, МОЙ ЛЮБИМЫЙ ФИЛЬМ: ДРАЙВ");
        textView.append("\nНовое имя потока: " + mainThread.getName());

        buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalLessons = Integer.parseInt(editTextTotalLessons.getText().toString());
                int totalDays = Integer.parseInt(editTextTotalDays.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int numberThread = counter++;
                        double avgLessonsPerDay = (double) totalLessons / totalDays;
                        Log.d("ThreadProject", String.format("Запущен поток No %d студентом группы No %s номер по списку No %d ", numberThread, "БСБО-04-22", 31));
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(String.format("Среднее количество пар в день: %.2f", avgLessonsPerDay));
                            }
                        });

                        // Имитация длительных вычислений в фоновом потоке
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток No " + numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}