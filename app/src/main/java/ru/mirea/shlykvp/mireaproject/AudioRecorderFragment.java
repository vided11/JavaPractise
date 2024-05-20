package ru.mirea.shlykvp.mireaproject;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;

public class AudioRecorderFragment extends Fragment {

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String audioFilePath;

    private Button startRecordingButton;
    private Button stopRecordingButton;
    private Button playRecordingButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio_recorder, container, false);

        startRecordingButton = view.findViewById(R.id.start_recording_button);
        stopRecordingButton = view.findViewById(R.id.stop_recording_button);
        playRecordingButton = view.findViewById(R.id.play_recording_button);

        startRecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });

        stopRecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });

        playRecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRecording();
            }
        });

        return view;
    }

    private void startRecording() {
        String fileName = System.currentTimeMillis() + ".mp3";
        File externalFilesDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        if (externalFilesDir != null) {
            audioFilePath = new File(externalFilesDir, fileName).getAbsolutePath();

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(audioFilePath);

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
                Toast.makeText(getActivity(), "Recording started", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "Error getting external files directory", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                Toast.makeText(getActivity(), "Recording stopped", Toast.LENGTH_SHORT).show();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Error stopping recording", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void playRecording() {
        if (audioFilePath != null) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(audioFilePath);
                mediaPlayer.prepare();
                mediaPlayer.start();
                Toast.makeText(getActivity(), "Playing recording", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaRecorder != null) {
            mediaRecorder.release();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}