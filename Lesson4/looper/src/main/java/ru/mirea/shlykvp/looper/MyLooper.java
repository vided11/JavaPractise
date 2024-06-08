package ru.mirea.shlykvp.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {
    public Handler mHandler;
    private Handler mainHandler;

    public MyLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }

    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                String age = msg.getData().getString("age");
                String job = msg.getData().getString("job");

                int ageInt = Integer.parseInt(age);
                try {
                    Thread.sleep(ageInt * 1000); // Задержка в соответствии с возрастом
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String result = "Мне " + age + " лет и я " + job;
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}
