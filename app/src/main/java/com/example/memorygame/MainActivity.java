package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    long timeLeft = 80000;
    private TextView tv_time, tv_extras; //the timer textview
    private List<Drawable> icons;
    private Card[] cards;
    private List<ImageButton> cardButtons;
    private Random rndm = new Random();;
    private int numCompleted;  //used to create the matches
    private Button showButton;
    private boolean allVisible;
    private List<Card> clickedButtons;
    private int lvltext = 0;
    private int numBtns;
    private GridLayout customLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_time = findViewById(R.id.tv_Timer);
        showButton = findViewById(R.id.btn_Show);
        tv_extras = findViewById(R.id.tv_numbers);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.zapsplat_multimedia_button_click_001_68773);   //the button click sound (only on the cards)


        //gets the level number from the level activity
        GetlevelInfo();
        tv_extras.setText("Level " + lvltext);

        //creates a grid layout
        customLayout = (GridLayout)findViewById(R.id.buttonAreaLayout);  //the area the buttons will spawn


        numBtns = 16 + (lvltext * 4);   //16 is the lowest num, then adds 4 for every additional level
        customLayout.setColumnCount(4);
        customLayout.setRowCount(4);
        customLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);



        //images used on cards/buttons
        LoadIcons();


        cards = new Card[numBtns];   //array of card objects
        cardButtons = new ArrayList<>(); //array of imagebuttons for the cards
        clickedButtons = new ArrayList<>();  //array of buttons clicked

        CreateButtons();   //gets references for buttons and fills them with images
        StartTimer();
        RandomiseImages();

        for (int i = 0; i < cardButtons.size(); i++)
        {
            int finalI = i;

            cardButtons.get(i).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                    CardClicked(cards[finalI]);
                }
            });
        }


    }
    public void GetlevelInfo()
    {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            lvltext = bundle.getInt("number");
        }
        else
        {
            System.out.println("no level number Received");
        }

    }

    public void LoadIcons()
    {
        //images used on cards/buttons
        icons = new ArrayList<>();
        icons.add(getDrawable(R.drawable.artistpalette));
        icons.add(getDrawable(R.drawable.avocado));
        icons.add(getDrawable(R.drawable.basketball));
        icons.add(getDrawable(R.drawable.christmastree));
        icons.add(getDrawable(R.drawable.crystalball));
        icons.add(getDrawable(R.drawable.fireworks));
        icons.add(getDrawable(R.drawable.framedpicture));
        icons.add(getDrawable(R.drawable.heartsuit));
        icons.add(getDrawable(R.drawable.jackolantern));
        icons.add(getDrawable(R.drawable.joker));
        icons.add(getDrawable(R.drawable.pool8ball));
        icons.add(getDrawable(R.drawable.spadesuit));
        icons.add(getDrawable(R.drawable.alien));
        icons.add(getDrawable(R.drawable.bear));
        icons.add(getDrawable(R.drawable.bomb));
        icons.add(getDrawable(R.drawable.bone));
        icons.add(getDrawable(R.drawable.books));
        icons.add(getDrawable(R.drawable.dice));
        icons.add(getDrawable(R.drawable.disc));
        icons.add(getDrawable(R.drawable.dog));
        icons.add(getDrawable(R.drawable.drum));
        icons.add(getDrawable(R.drawable.cat));
        icons.add(getDrawable(R.drawable.cigarette));
        icons.add(getDrawable(R.drawable.firecracker));
        icons.add(getDrawable(R.drawable.golf));
        icons.add(getDrawable(R.drawable.glasses));
        icons.add(getDrawable(R.drawable.guitar));
        icons.add(getDrawable(R.drawable.headphone));
        icons.add(getDrawable(R.drawable.pinata));
        icons.add(getDrawable(R.drawable.pizza));
        icons.add(getDrawable(R.drawable.rabbit));
        icons.add(getDrawable(R.drawable.remote));
    }

    public void CreateButtons()
    {
        for (int i = 0; i < numBtns; i++)   //num of cards on the board
        {
            ImageButton imageButton = new ImageButton(this);   //creates a button
            cardButtons.add(imageButton);   //adds to the list
            cards[i] = new Card(cardButtons.get(i), this);  //creates a new card object from that Imgbutton


            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.height = 350 - (lvltext * 30);
            params.width = 350 - (lvltext * 30);

            imageButton.setLayoutParams(params);

            customLayout.addView(imageButton);
        }
    }

   public void RandomiseImages()
   {
       //it would randomise 2 numbers (the btn numbers)  and then give those 2 cards the same pic
       List<Integer> buttonList = new ArrayList<Integer>();  //creates an array of possible buttons to choose from

       for (int i = 0; i < cardButtons.size(); i++)
       {
           buttonList.add(i); //adds a list of numbers to choose buttons from
       }

       rndm = new Random();
       Collections.shuffle(buttonList);  //shuffles button numbers
       Collections.shuffle(icons);  //shuffles icons


       for (int i = 0; i < cardButtons.size() / 2 ;  i++)
       {
           //1 of 2 cards
            cards[buttonList.get(0)].randomImage = icons.get(i); //sets the first button of the randomised lists Image to an icon in the list
            cards[buttonList.get(0)].HideCard();  //sets the visiability state
            cardButtons.get(buttonList.get(0)).setBackground(cards[buttonList.get(0)].currentCardImage);//sets the first button iamge to a questionmark
            buttonList.remove(0); //removes the button number from the list

           //2 of 2 cards
            cards[buttonList.get(0)].randomImage = icons.get(i);  //sets the second buttons image to match the first
            cards[buttonList.get(0)].HideCard();  //sets the visiability state
            cardButtons.get(buttonList.get(0)).setBackground(cards[buttonList.get(0)].currentCardImage);  //sets the second button image to a questionMark
            buttonList.remove(0);  //removes the buttom number from the list

       }
       allVisible = false;
   }

    public void RevealButton(View view)  //  reveals/hides all cards
    {
        if (!allVisible)
        {
            for (int i = 0; i < cards.length; i++)
            {
                cards[i].ShowCard(); //sets the visibilty state
                cardButtons.get(i).setBackground(cards[i].currentCardImage);
                allVisible = true;
            }
        }
        else
        {
            for (int i = 0; i < cards.length; i++)
            {
                cards[i].HideCard(); //sets the visibilty state
                cardButtons.get(i).setBackground(cards[i].currentCardImage);
                allVisible = false;
            }
        }
        System.out.println("RevealButtonClicked");
    }

    public void CardClicked(Card _clickedButton)
    {


        if (clickedButtons.isEmpty())  //if no buttons clicked
        {
            clickedButtons.add(_clickedButton);   //adds it to the list (1 of 2 )
            _clickedButton.ShowCard();  //sets the cards pic to the icon (not Qmark)
           _clickedButton.btnRef.setBackground(_clickedButton.currentCardImage);  //shows pic of card
        }
        else
        {
            clickedButtons.add(_clickedButton);
            _clickedButton.ShowCard();
            _clickedButton.btnRef.setBackground(_clickedButton.currentCardImage);

            CheckMatch(clickedButtons.get(0), clickedButtons.get(1));
        }
    }
    public void CheckMatch(Card card1, Card card2)
    {
        if (card1.randomImage == card2.randomImage)  //if a match
        {
            MatchMethod();
            clickedButtons.clear();  //clears the list to start again

        }
        else
        {
            noMatchMethod(card1 , card2);
            clickedButtons.clear();  //clears the list to start again
        }
    }
    public void MatchMethod()
    {
        numCompleted += 1;

        System.out.println(numCompleted);
        if (numCompleted == cardButtons.size()/2)
        {
            Toast.makeText(getApplicationContext(), "DONE!", Toast.LENGTH_LONG).show();
        }
    }
    public void noMatchMethod(Card card1, Card card2)   //if there is no match
    {
        //sets cards state to hidden and changes the pic back to Q mark after briefly keeping the cards face up
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 0,7 seconds
                    card1.HideCard();
                    card1.btnRef.setBackground(card1.currentCardImage);

                    card2.HideCard();
                    card2.btnRef.setBackground(card2.currentCardImage);
            }
        }, 700);
    }
    public void QuitButton(View view)
    {
        startActivity(new Intent(MainActivity.this, MainMenu.class));
    }

    public void StartTimer()
    {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                UpdateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void UpdateTimer()
    {
        int minutes = (int)timeLeft/30000;
        int seconds = (int)timeLeft % 30000/ 1000;

        String remainingTime;

        remainingTime = "" + minutes;
        remainingTime += ":";

        if (seconds < 10)
        {
            remainingTime += 0;
        }

        remainingTime += seconds;

        tv_time.setText(remainingTime);
    }
}



