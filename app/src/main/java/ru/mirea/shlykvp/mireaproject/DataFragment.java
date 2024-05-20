package ru.mirea.shlykvp.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DataFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        Button btnOpenVacancies = view.findViewById(R.id.btnOpenVacancies);
        btnOpenVacancies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUploadWork();
            }
        });

        return view;
    }

    private void startUploadWork() {
        WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
                .build();
        WorkManager.getInstance(requireContext())
                .enqueue(uploadWorkRequest);
    }
}