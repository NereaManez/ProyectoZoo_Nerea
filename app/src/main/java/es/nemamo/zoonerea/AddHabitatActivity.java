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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.nemamo.zoonerea.Model.Animal;
import es.nemamo.zoonerea.Model.Database;
import es.nemamo.zoonerea.Model.Enum.HabitatType;
import es.nemamo.zoonerea.Model.Habitat;
import es.nemamo.zoonerea.Model.SGBD;
import es.nemamo.zoonerea.RecyclerViewAnimal.RecyclerViewAnimal;
import io.realm.Realm;
import io.realm.RealmResults;

public class AddHabitatActivity extends AppCompatActivity {

    private Realm realm;
    private SGBD sgbd;

    private EditText descHabitatETAddHabitat;
    private Spinner spinnerAddHabitat;
    private ImageButton confirmAddHabitat, cancelAddHabitat;
    private LinearLayout llAddHabitat;
    private Button addNewHabitat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habitat);

        realm = Database.getInstance().conect(getBaseContext());
        sgbd = new SGBD(realm);

        descHabitatETAddHabitat = findViewById(R.id.descHabitatETAddHabitat);

        ArrayList<String> arrayList = new ArrayList<>();
        for (HabitatType ht : HabitatType.values())
            arrayList.add(ht.getHabitat());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        spinnerAddHabitat = findViewById(R.id.spinnerAddHabitat);
        spinnerAddHabitat.setAdapter(adapter);

        llAddHabitat = findViewById(R.id.llAddHabitat);
        confirmAddHabitat = findViewById(R.id.confirmAddHabitat);

        cancelAddHabitat = findViewById(R.id.cancelAddHabitat);
        cancelAddHabitat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddHabitatActivity.this, HabitatActivity.class);
                i.putExtra("isAdmin", true);
                startActivity(i);
                finish();
            }
        });

        addNewHabitat = findViewById(R.id.addNewHabitat);
        addNewHabitat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = spinnerAddHabitat.getSelectedItem().toString();
                String desc = descHabitatETAddHabitat.getText().toString();

                if (!desc.equals("")) {
                    llAddHabitat.setVisibility(View.VISIBLE);
                    addNewHabitat.setVisibility(View.GONE);

                    confirmAddHabitat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int id = realm.where(Habitat.class).max("id").intValue();
                            sgbd.addHabitat(id+1, type, desc);
                            Toast.makeText(AddHabitatActivity.this, "Habitat added successfully", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(AddHabitatActivity.this, HabitatActivity.class);
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