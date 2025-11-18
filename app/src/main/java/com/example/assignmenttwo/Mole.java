package com.example.assignmenttwo;

import android.widget.ImageView;

public class Mole {

    private final int index;
    private final ImageView imageView;
    private  boolean isVisible;

    // constructor
    public Mole(int index, ImageView imageView){
        this.index = index;
        this.imageView = imageView;
        this.isVisible = false;
    }

    // Getter method
    public int getIndex(){
        return  index;
    }

    public ImageView getImageView(){
        return  imageView;
    }

    public boolean isVisible() {
        return isVisible;
    }

    // Setter method: Control the visibility of groundhogs
    public void setVisible(boolean visible){
        this.isVisible = visible;
        if (visible) {
            // Display Mole
            imageView.setImageResource(R.drawable.img_without_mole);
        } else {
            // Hide Mole
            imageView.setImageResource(R.drawable.img_with_mole);
        }
    }
}
