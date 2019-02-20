package com.pace.cs639spring.hw1;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView selectedImage;

    ImageView birdImage;
    ImageView catImage;
    ImageView dogImage;

    Button flipButton;
    Button rotateButton;

    ImageView translateRightButton;
    ImageView translateLeftButton;
    ImageView translateTopButton;
    ImageView translateDownButton;
    ImageView translateResetButton;

    Boolean imageClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        birdImage = (ImageView) findViewById(R.id.birdImage);
        birdImage.setOnClickListener( view -> {
            imageClicked = true;
            selectedImage = birdImage;
            hideDescriptions();
            TextView birdDescription = (TextView)findViewById(R.id.birdDescription);
            birdDescription.setVisibility(View.VISIBLE);
        });

        catImage = (ImageView) findViewById(R.id.catImage);
        catImage.setOnClickListener( view -> {
            imageClicked = true;
            selectedImage = catImage;
            hideDescriptions();
            TextView catDescription = (TextView)findViewById(R.id.catDescription);
            catDescription.setVisibility(View.VISIBLE);
        });

        dogImage = (ImageView) findViewById(R.id.dogImage);
        dogImage.setOnClickListener( view -> {
            imageClicked = true;
            selectedImage = dogImage;
            hideDescriptions();
            TextView dogDescription = (TextView)findViewById(R.id.dogDescription);
            dogDescription.setVisibility(View.VISIBLE);
        });

        setupFlipAndRotateButtonListeners();

        setupTranslationButtonListeners();
    }

    /*Hide descriptions */
    private void hideDescriptions(){
        TextView birdDescription = (TextView)findViewById(R.id.birdDescription);
        birdDescription.setVisibility(View.INVISIBLE);

        TextView catDescription = (TextView)findViewById(R.id.catDescription);
        catDescription.setVisibility(View.INVISIBLE);

        TextView dogDescription = (TextView)findViewById(R.id.dogDescription);
        dogDescription.setVisibility(View.INVISIBLE);
    }

    /*Shows a Toast message if no image is selected and user clicks on a action button*/
    private void showAnimalSelectionToast(){
        Toast.makeText(MainActivity.this,
                R.string.please_select_an_animal_image_before_choosing_an_image_modification, Toast.LENGTH_LONG).show();
    }

    /*Converts dp values to pixels*/
    private float dpToPx(int dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    /*Checks if an image is selected*/
    private boolean isImageSelected(){
        return imageClicked ?  true:  false;
    }

    /*Set up listeners for Flip and Rotate buttons*/
    private void setupFlipAndRotateButtonListeners(){
        flipButton = (Button) findViewById(R.id.flipButton);
        flipButton.setOnClickListener( view -> {
            if(!isImageSelected()){
                showAnimalSelectionToast();
                return;
            }

            float flipAngleInDegrees =  selectedImage.getRotationY() == 0 ? 180 : 0;
            selectedImage.setRotationY(flipAngleInDegrees);
        });

        rotateButton = (Button) findViewById(R.id.rotateButton);
        rotateButton.setOnClickListener( view -> {
            if(!isImageSelected()){
                showAnimalSelectionToast();
                return;
            }

            selectedImage.setRotation(selectedImage.getRotation() + 90);
            if(selectedImage.getRotation() == 360){
                selectedImage.setRotation(0);
            }
        });
    }

    /*Set up the listeners for Translation buttons*/
    private void setupTranslationButtonListeners(){
        translateRightButton = (ImageView) findViewById (R.id.translateRightButton);
        translateRightButton.setOnClickListener( view -> {
            if(!isImageSelected()){
                showAnimalSelectionToast();
                return;
            }

            selectedImage.setTranslationX(selectedImage.getTranslationX()+ dpToPx(10));
        });

        translateLeftButton = (ImageView) findViewById (R.id.translateLeftButton);
        translateLeftButton.setOnClickListener( view -> {
            if(!isImageSelected()){
                showAnimalSelectionToast();
                return;
            }
            selectedImage.setTranslationX(selectedImage.getTranslationX()- dpToPx(10));
        });

        translateTopButton = (ImageView) findViewById (R.id.translateTopButton);
        translateTopButton.setOnClickListener( view -> {
            if(!isImageSelected()){
                showAnimalSelectionToast();
                return;
            }
            selectedImage.setTranslationY(selectedImage.getTranslationY() - dpToPx(10));
        });

        translateDownButton = (ImageView) findViewById (R.id.translateDownButton);
        translateDownButton.setOnClickListener( view -> {
            if(!isImageSelected()){
                showAnimalSelectionToast();
                return;
            }
            selectedImage.setTranslationY(selectedImage.getTranslationY() + dpToPx(10));
        });

        translateResetButton = (ImageView) findViewById (R.id.translateResetButton);
        translateResetButton.setOnClickListener( view -> {
            if(!isImageSelected()){
                showAnimalSelectionToast();
                return;
            }
            selectedImage.setTranslationX(0);
            selectedImage.setTranslationY(0);
        });
    }
}
