package ru.mirea.shlykvp.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Map;

public class ProfileFragment extends Fragment {

    private EditText nameEditText, emailEditText;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameEditText = view.findViewById(R.id.nameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        Button saveButton = view.findViewById(R.id.saveButton);
        Button showProfilesButton = view.findViewById(R.id.showProfilesButton);

        sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        loadUserData();

        saveButton.setOnClickListener(v -> saveUserData());
        showProfilesButton.setOnClickListener(v -> showSavedProfiles());

        return view;
    }

    private void loadUserData() {
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");

        nameEditText.setText(name);
        emailEditText.setText(email);
    }

    private void saveUserData() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.apply();
    }

    private void showSavedProfiles() {
        Map<String, ?> allEntries = sharedPreferences.getAll();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue().toString()).append("\n");
        }


        if (sb.length() > 0) {
            Toast.makeText(requireContext(), sb.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(requireContext(), "Нет сохраненных профилей", Toast.LENGTH_SHORT).show();
        }
    }
}