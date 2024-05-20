package ru.mirea.shlykvp.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CompassFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor magneticSensor;
    private TextView directionTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);
        directionTextView = view.findViewById(R.id.direction_text_view);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];

            float angle = (float) Math.toDegrees(Math.atan2(y, x));
            if (angle < 0) {
                angle += 360;
            }

            String direction;
            if (angle >= 315 || angle < 45) {
                direction = "Север";
            } else if (angle >= 45 && angle < 135) {
                direction = "Восток";
            } else if (angle >= 135 && angle < 225) {
                direction = "Юг";
            } else {
                direction = "Запад";
            }

            directionTextView.setText("Направление: " + direction);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}