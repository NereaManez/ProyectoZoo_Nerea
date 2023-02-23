package es.nemamo.zoonerea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private String cDNI;
    private boolean isAdmin;

    private ImageButton animalImageView, careImageView, habitatImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cDNI = getIntent().getExtras().getString("caretakerDNI");
        isAdmin = getIntent().getExtras().getBoolean("isAdmin");

        animalImageView = findViewById(R.id.animalImageButton);
        animalImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, AnimalActivity.class);
                i.putExtra("isAdmin", isAdmin);

                startActivity(i);
            }
        });

        careImageView = findViewById(R.id.careImageButton);
        careImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, DailyCareActivity.class);
                i.putExtra("caretakerDNI", cDNI);

                startActivity(i);
            }
        });

        habitatImageView = findViewById(R.id.habitatImageButton);
        habitatImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, HabitatActivity.class);
                i.putExtra("isAdmin", isAdmin);

                startActivity(i);
            }
        });
    }
}