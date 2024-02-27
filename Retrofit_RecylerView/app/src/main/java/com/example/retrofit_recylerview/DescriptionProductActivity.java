package com.example.retrofit_recylerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

public class DescriptionProductActivity extends AppCompatActivity {
    TextView txtDescript;
    ImageView imageView;
    TextView rating_number;
    TextView total_rating;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_product);

        init();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Data");
        String txt = bundle.getString("Descript");
        String txtRating = bundle.getString("Rating");
        String txtTotal = bundle.getString("Total");
        String img = bundle.getString("Img");
        if (img != null){
            Glide.with(this)
                    .load(img)
                    .into(imageView);
        }
        txtDescript.setText(txt);
        rating_number.setText(txtRating);
        total_rating.setText(txtTotal);

        Float rate = Float.parseFloat(txtRating);
        if (rate >= 5){
            ratingBar.setRating(5);
        } else if (rate >= 4) {
            ratingBar.setRating(4);
        } else if (rate >= 3) {
            ratingBar.setRating(3);
        } else if (rate >= 2) {
            ratingBar.setRating(2);
        } else {
            ratingBar.setRating(1);
        }
    }

    private void init(){
        txtDescript = findViewById(R.id.txtDescriptionData);
        imageView = findViewById(R.id.imgDetail);
        rating_number = findViewById(R.id.ratingNumber);
        total_rating = findViewById(R.id.totalRating);
        ratingBar = findViewById(R.id.ratingBar);
    }

    public void onClickBack(View view){
        finish();
    }
}