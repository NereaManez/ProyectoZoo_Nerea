package es.nemamo.zoonerea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.nemamo.zoonerea.Model.Animal;
import es.nemamo.zoonerea.Model.Database;
import es.nemamo.zoonerea.Model.Habitat;
import io.realm.Realm;

public class ViewAnimalDataActivity extends AppCompatActivity {

    private Realm realm;
    private Animal a;
    private Habitat h;
    private boolean isAdmin;

    private TextView infoAnimal, specieAnimal, familyAnimal, alFrequencyAnimal, habitatIDAnimal, habitatTypeAnimal, descHabitatAnimal;
    private ImageButton confirmAnimal, cancelAnimal;
    private LinearLayout llAnimal;
    private Button updateAnimal, deleteAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animal_data);

        realm = Database.getInstance().conect(getBaseContext());

        int aID = getIntent().getExtras().getInt("aID");
        a = realm.where(Animal.class).equalTo("id", aID).findFirst();

        infoAnimal = findViewById(R.id.infoAnimal);
        infoAnimal.setText(a.getId() + "  -  " + a.getName());
        specieAnimal = findViewById(R.id.specieAnimal);
        specieAnimal.setText(a.getSpecie());
        familyAnimal = findViewById(R.id.familyAnimal);
        familyAnimal.setText(a.getFamily());
        alFrequencyAnimal = findViewById(R.id.alFrequencyAnimal);
        alFrequencyAnimal.setText(String.valueOf(a.getFrequency_al()));

        int hID = getIntent().getExtras().getInt("hID");
        h = realm.where(Habitat.class).equalTo("id", hID).findFirst();

        habitatIDAnimal = findViewById(R.id.habitatIDAnimal);
        habitatIDAnimal.setText(habitatIDAnimal.getText().toString() + " " + h.getId());
        habitatTypeAnimal = findViewById(R.id.habitatTypeAnimal);
        habitatTypeAnimal.setText(h.getHabitatType());
        descHabitatAnimal = findViewById(R.id.descHabitatAnimal);
        descHabitatAnimal.setText(h.getDescription());

        llAnimal = findViewById(R.id.llAnimal);
        confirmAnimal = findViewById(R.id.confirmAnimal);
        cancelAnimal = findViewById(R.id.cancelAnimal);

        updateAnimal = findViewById(R.id.updateAnimal);
        updateAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llAnimal.setVisibility(View.VISIBLE);
                updateAnimal.setVisibility(View.GONE);
                deleteAnimal.setVisibility(View.GONE);

                cancelAnimal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        llAnimal.setVisibility(View.GONE);
                        updateAnimal.setVisibility(View.VISIBLE);
                        deleteAnimal.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        deleteAnimal = findViewById(R.id.deleteAnimal);
        deleteAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llAnimal.setVisibility(View.VISIBLE);
                updateAnimal.setVisibility(View.GONE);
                deleteAnimal.setVisibility(View.GONE);

                cancelAnimal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        llAnimal.setVisibility(View.GONE);
                        updateAnimal.setVisibility(View.VISIBLE);
                        deleteAnimal.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        isAdmin = getIntent().getExtras().getBoolean("isAdmin");
        if (isAdmin) {
            updateAnimal.setVisibility(View.VISIBLE);
            deleteAnimal.setVisibility(View.VISIBLE);
        }
    }
}