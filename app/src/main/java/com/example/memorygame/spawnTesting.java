package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class spawnTesting extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spawn_testing);




        for (int i= 0; i < 10; i++)
        {

            //btn.setWidth(290);
            //btn.setHeight(290);
            //btn.setText("btn " + String.valueOf(i));

            //customLayout.addView(btn);
        }
    }

    public void BackButton(View view)
    {
        startActivity(new Intent(spawnTesting.this, MainMenu.class));
    }

}