package com.example.memorygame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.media.Image;
import android.service.controls.Control;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Card {

    boolean visible;
    boolean matched;
    Drawable currentCardImage;  //the current card Image
    Drawable randomImage;
    Drawable hiddenCardImage;  //the default ? while hidden
    ImageButton btnRef;  //the ref to the button

    public Card(ImageButton _btnRef, Context context)
    {
        btnRef = _btnRef;
        hiddenCardImage = context.getDrawable(R.drawable.questionmark);


    }

    public void HideCard()
    {
        currentCardImage = hiddenCardImage;   //changes image to questions mark
        visible = false;   //sets state to  not visivible
    }

    public void ShowCard()
    {
        currentCardImage = randomImage;  //sets the image to the image it was assigned
        visible = true;
    }
}
