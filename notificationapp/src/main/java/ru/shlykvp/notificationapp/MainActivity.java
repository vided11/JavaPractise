package ru.shlykvp.notificationapp;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.notificationapp.R;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "com.mirea.asd.notification.ANDROID";
    private int PermissionCode = 200;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {

            Log.d(MainActivity.class.getSimpleName().toString(), "Разрешения получены");

        } else {
            Log.d(MainActivity.class.getSimpleName().toString(), "Нет разрешений!");

        }
    }

    public void onClickSendNotification(View view) {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)

                .setContentText("Congratulation!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setContentTitle("Mirea");
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Student FIO Notification", importance);

        channel.setDescription("MIREA Channel");

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.createNotificationChannel(channel);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(1, builder.build());

    }
}