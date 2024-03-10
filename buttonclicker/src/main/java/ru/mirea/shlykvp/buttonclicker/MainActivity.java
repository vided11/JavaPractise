package ru.mirea.shlykvp.buttonclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView textViewStudent;
    private Button btnWhoAmI;
    CheckBox checkBox;
    private Button btnItIsNotMe ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewStudent = findViewById(R.id.tvOut);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe);
        checkBox = findViewById(R.id.checkBoxMain);
        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStudent.setText("Мой номер по списку № 31");
            }
        };
        View.OnClickListener oclBtnItsnotMe = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Ещё один способ!", Toast.LENGTH_SHORT).show();
            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
        btnItIsNotMe.setOnClickListener(oclBtnItsnotMe);

    }
    public void onMyButtonClick(View view) {
        Toast.makeText(this, "Ещё один способ!", Toast.LENGTH_SHORT).show();

        if (!checkBox.isChecked()) {
            checkBox.setChecked(true);
            return;
        }
        checkBox.setChecked(false);
        return;
    }
}
