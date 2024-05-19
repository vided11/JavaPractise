package ru.mirea.shlykvp.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import ru.mirea.shlykvp.employeedb.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();

        Employee superhero1 = new Employee();
        superhero1.name = "Hulk";
        superhero1.power = "Super strength";
        employeeDao.insert(superhero1);

        Employee superhero2 = new Employee();
        superhero2.name = "Iron Man";
        superhero2.power = "Powered armor-suit";
        employeeDao.insert(superhero2);

        Employee superhero3 = new Employee();
        superhero3.name = "Spider-Man";
        superhero3.power = "Spider abilities";
        employeeDao.insert(superhero3);

        List<Employee> superheroes = employeeDao.getAll();

        StringBuilder superheroInfo = new StringBuilder();
        for (int i = 0; i < superheroes.size(); i++) {
            Employee superhero = superheroes.get(i);
            superheroInfo.append("ID: ").append(superhero.id)
                    .append(", Name: ").append(superhero.name)
                    .append(", Power: ").append(superhero.power)
                    .append("\n");
        }

        binding.superheroView1.setText(superheroInfo.toString());

        Employee superhero = employeeDao.getById(1);

        superhero.power = "Super strength, regenerative healing";
        employeeDao.update(superhero);

        StringBuilder superheroInfo2 = new StringBuilder();
        for (int i = 0; i < superheroes.size(); i++) {
            superhero = superheroes.get(i);
            superheroInfo.append("ID: ").append(superhero.id)
                    .append(", Name: ").append(superhero.name)
                    .append(", Power: ").append(superhero.power)
                    .append("\n");
        }

        binding.superheroView2.setText(superheroInfo2.toString());
    }
}