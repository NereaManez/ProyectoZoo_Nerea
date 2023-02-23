package es.nemamo.zoonerea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;

import es.nemamo.zoonerea.Model.Animal;
import es.nemamo.zoonerea.Model.Database;
import es.nemamo.zoonerea.Model.Enum.Family;
import es.nemamo.zoonerea.Model.Enum.HabitatType;
import es.nemamo.zoonerea.Model.Habitat;
import es.nemamo.zoonerea.Model.SGBD;
import io.realm.Realm;
import io.realm.RealmResults;

public class AddAnimalActivity extends AppCompatActivity {

    private Realm realm;
    private SGBD sgbd;
    
    private EditText nameAddAnimal, specieAddAnimal, alFrequencyAddAnimal;
    private Spinner spinnerAddAnimal, spinnerHabitatAddAnimal;
    private ImageButton confirmAddAnimal, cancelAddAnimal;
    private LinearLayout llAddAnimal;
    private Button addNewAnimal;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        realm = Database.getInstance().conect(getBaseContext());
        sgbd = new SGBD(realm);

        nameAddAnimal = findViewById(R.id.nameAddAnimal);
        specieAddAnimal = findViewById(R.id.specieAddAnimal);
        alFrequencyAddAnimal = findViewById(R.id.alFrequencyAddAnimal);

        ArrayList<String> arrayList = new ArrayList<>();
        for (Family f : Family.values())
            arrayList.add(f.getFamily());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        spinnerAddAnimal = findViewById(R.id.spinnerAddAnimal);
        spinnerAddAnimal.setAdapter(adapter);

        ArrayList<String> arrayList2 = new ArrayList<>();
        RealmResults<Habitat> realmResults = realm.where(Habitat.class).findAll();
        List<Habitat> habitatList = realm.copyFromRealm(realmResults);

        for (Habitat h : habitatList)
            arrayList2.add(String.valueOf(h.getId()));

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList2);
        spinnerHabitatAddAnimal = findViewById(R.id.spinnerHabitatAddAnimal);
        spinnerHabitatAddAnimal.setAdapter(adapter2);

        llAddAnimal = findViewById(R.id.llAddAnimal);
        confirmAddAnimal = findViewById(R.id.confirmAddAnimal);

        cancelAddAnimal = findViewById(R.id.cancelAddAnimal);
        cancelAddAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddAnimalActivity.this, AnimalActivity.class);
                i.putExtra("isAdmin", true);
                startActivity(i);
                finish();
            }
        });

        addNewAnimal = findViewById(R.id.addNewAnimal);
        addNewAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameAddAnimal.getText().toString();
                String specie = specieAddAnimal.getText().toString();
                int freq = Integer.parseInt(alFrequencyAddAnimal.getText().toString());
                String fam = spinnerAddAnimal.getSelectedItem().toString();
                int hab = Integer.parseInt(spinnerHabitatAddAnimal.getSelectedItem().toString());

                if (!specie.equals("") && !fam.equals("") && !name.equals("")) {
                    llAddAnimal.setVisibility(View.VISIBLE);
                    addNewAnimal.setVisibility(View.GONE);

                    confirmAddAnimal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sgbd.addAnimal(specie, fam, freq, name, hab);
                            Toast.makeText(AddAnimalActivity.this, "Animal added successfully", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(AddAnimalActivity.this, AnimalActivity.class);
                            i.putExtra("isAdmin", true);
                            startActivity(i);
                            finish();
                        }
                    });
                }
            }
        });
    }
}