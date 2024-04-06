package ru.mirea.shlykvp.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShareActivity extends AppCompatActivity {
    private TextView textViewDeveloperBook;
    private EditText editTextUserBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        textViewDeveloperBook = findViewById(R.id.textViewDeveloperBook);
        editTextUserBook = findViewById(R.id.editTextUserBook);

        // Получение данных из MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String developerBook = extras.getString(MainActivity.KEY);
            textViewDeveloperBook.setText("Любимая книга разработчика – " + developerBook);
        }
    }

    public void sendUserBook(View view) {
        String userBook = editTextUserBook.getText().toString().trim();
        if (!userBook.isEmpty()) {
            // Отправка введенных пользователем данных по нажатию на кнопку
            Intent data = new Intent();
            data.putExtra(MainActivity.USER_MESSAGE, userBook);
            setResult(Activity.RESULT_OK, data);
            finish();
        } else {
            Toast.makeText(this, "Пожалуйста, введите название книги", Toast.LENGTH_SHORT).show();
        }
    }
}