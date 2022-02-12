package com.example.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    Button btnLevels;
    Button btnCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnLevels = findViewById(R.id.btnLevels);
        btnCredits = findViewById(R.id.btnCredits);

    }

    public void LevelButton(View view)
    {
        startActivity(new Intent(MainMenu.this, LevelsActivity.class));
    }

    public void CreditButton(View view)
    {
        startActivity(new Intent(MainMenu.this, CreditsActivity.class));
    }
}