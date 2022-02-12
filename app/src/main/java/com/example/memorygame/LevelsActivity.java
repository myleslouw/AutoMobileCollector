package com.example.memorygame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class LevelsActivity extends AppCompatActivity {


    private List<Button> levelButtons;
    private TextView tv_levels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        levelButtons = new ArrayList<>();
        tv_levels = findViewById(R.id.tv_levels);

        FindButtons();  //finds the levelbuttons in the activity

        for (int i = 1; i < levelButtons.size(); i++)
        {
            int finalI = i;

            levelButtons.get(i - 1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LoadLevel(finalI);  //gives the method a level num to load
                }
            });
        }

    }

    public void FindButtons()
    {
        for (int i = 1; i < 7; i++)   //btns start at 1 so loop starts at 1
        {
            int currentBtn = getResources().getIdentifier("btnlvl"+ i, "id", getPackageName()); //increments button id (btn1, btn2 etc)
            levelButtons.add((Button) findViewById(currentBtn));  //adds the buttons to a referencable list
        }
    }

    public void LoadLevel(int levelNum)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("number", levelNum);
        Intent intent = new Intent(LevelsActivity.this, MainActivity.class);
        intent.putExtras(bundle);

        System.out.println("sent = " + bundle.getInt("number"));

        startActivity(intent);
    }

    public void BackButton(View view)
    {
        startActivity(new Intent(LevelsActivity.this, MainMenu.class));
    }

}