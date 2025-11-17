package com.example.assignmenttwo;

import android.widget.ImageView;

public class Mole {

    private int index;
    private ImageView imageView;
    private  boolean isVisible;

    // constructor
    public Mole(int index, ImageView imageView){
        this.index = index;
        this.imageView = imageView;
        this.isVisible = false;
    }

    public int getIndex(){
        return  index;
    }

    public ImageView getImageView(){
        return  imageView;
    }

}
