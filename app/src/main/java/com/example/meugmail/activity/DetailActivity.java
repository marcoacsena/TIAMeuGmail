package com.example.meugmail.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meugmail.R;

public class DetailActivity extends AppCompatActivity {

    TextView mIcon;
    TextView mSender;
    TextView mEmailTitle;
    TextView mEmailDetails;
    TextView mEmailTime;
    ImageView mFavorite;

    private String favoritado, detailFavoritado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mIcon = findViewById(R.id.tvIcon);
        mSender = findViewById(R.id.tvEmailSender);
        mEmailTitle = findViewById(R.id.tvEmailTitle);
        mEmailDetails = findViewById(R.id.tvEmailDetails);
        mEmailTime = findViewById(R.id.tvEmailTime);
        mFavorite = findViewById(R.id.ivFavorite);

        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null) {
            mIcon.setText(mBundle.getString("icon"));
            ((GradientDrawable) mIcon.getBackground()).setColor(mBundle.getInt("colorIcon"));
            mSender.setText(mBundle.getString("sender"));
            mEmailTitle.setText(mBundle.getString("title"));
            mEmailDetails.setText(mBundle.getString("details"));
            mEmailTime.setText(mBundle.getString("time"));

            favoritado = mBundle.getString("favoritado");
        }

    try {
        if (favoritado.equals("nao")) {
            mFavorite.clearColorFilter();
            favoritado = "nao";
        } else {
            mFavorite.setColorFilter(ContextCompat.getColor(DetailActivity.this,
                    R.color.colorOrange));
            favoritado = "sim";
        }

    }catch (Exception e){

        Toast.makeText(this, "Ocorreu um problema ao favoritar a mensagem!", Toast.LENGTH_SHORT).show();
        }


//        mFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mFavorite.getColorFilter() != null) {
//                    mFavorite.clearColorFilter();
//                } else {
//                    mFavorite.setColorFilter(ContextCompat.getColor(DetailActivity.this,
//                            R.color.colorOrange));
//                }
//            }
//        });

    }
}