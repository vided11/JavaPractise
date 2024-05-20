package ru.mirea.shlykvp.mireaproject;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class UploadWorker extends Worker {
    static final String TAG = "UploadWorker";
    private Handler mHandler;

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public Result doWork() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Начало 10-ти секундной задержки", Toast.LENGTH_SHORT).show();
            }
        });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Завершение фоновой активности", Toast.LENGTH_SHORT).show();
            }
        });

        return Result.success();
    }
}
