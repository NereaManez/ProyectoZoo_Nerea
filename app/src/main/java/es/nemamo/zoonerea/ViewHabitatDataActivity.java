package es.nemamo.zoonerea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import es.nemamo.zoonerea.RecyclerViewAnimal.ARecyclerViewAdapter;
import es.nemamo.zoonerea.RecyclerViewAnimal.RecyclerViewAnimal;
import io.realm.Realm;
import io.realm.RealmResults;

public class ViewHabitatDataActivity extends AppCompatActivity {

    private Realm realm;
    private Habitat h;
    private List<RecyclerViewAnimal> animalList = new ArrayList<>();
    private RealmResults<Animal> realmResults;
    List<Animal> animals;
    private boolean isAdmin;

    private TextView infoHabitat, habitatTypeHabitat, descHabitat;
    private ImageButton confirmHabitat, cancelHabitat;
    private LinearLayout llHabitat;
    private Button updateHabitat, deleteHabitat;
    private RecyclerView recyclerView;
    private Spinner spinnerHabitat;
    private EditText descHabitatETHabitat;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habitat_data);

        realm = Database.getInstance().conect(getBaseContext());

        int hID = getIntent().getExtras().getInt("hID");
        h = realm.where(Habitat.class).equalTo("id", hID).findFirst();

        infoHabitat = findViewById(R.id.infoHabitat);
        infoHabitat.setText(infoHabitat.getText().toString() + " " + h.getId());
        habitatTypeHabitat = findViewById(R.id.habitatTypeHabitat);
        habitatTypeHabitat.setText(h.getHabitatType());
        descHabitat = findViewById(R.id.descHabitatHabitat);
        descHabitat.setText(h.getDescription());

        realmResults = realm.where(Animal.class).equalTo("idHabitat", h.getId()).findAll();
        animals = realm.copyFromRealm(realmResults);
        getDataFromAnimals(animals);

        recyclerView = findViewById(R.id.recyclerViewAnimalHabitat);
        addRecyclerView();

        llHabitat = findViewById(R.id.llHabitat);
        confirmHabitat = findViewById(R.id.confirmHabitat);
        cancelHabitat = findViewById(R.id.cancelHabitat);

        ArrayList<String> arrayList = new ArrayList<>();
        for (HabitatType ht : HabitatType.values())
            arrayList.add(ht.getHabitat());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        spinnerHabitat = findViewById(R.id.spinnerHabitat);
        spinnerHabitat.setAdapter(adapter);

        descHabitatETHabitat = findViewById(R.id.descHabitatETHabitat);

        updateHabitat = findViewById(R.id.updateHabitat);
        updateHabitat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llHabitat.setVisibility(View.VISIBLE);
                updateHabitat.setVisibility(View.GONE);
                deleteHabitat.setVisibility(View.GONE);
                habitatTypeHabitat.setVisibility(View.GONE);
                spinnerHabitat.setVisibility(View.VISIBLE);
                descHabitat.setVisibility(View.GONE);
                descHabitatETHabitat.setVisibility(View.VISIBLE);

                spinnerHabitat.setSelection(arrayList.indexOf(h.getHabitatType()));
                descHabitatETHabitat.setText(descHabitat.getText().toString());

                confirmHabitat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ViewHabitatDataActivity.this, "Habitat " + h.getId() + " updated", Toast.LENGTH_SHORT).show();
                        realm.executeTransaction(r -> {
                            h.setHabitatType(spinnerHabitat.getSelectedItem().toString());
                            h.setDescription(descHabitatETHabitat.getText().toString());

                            realm.insertOrUpdate(h);
                        });

                        habitatTypeHabitat.setText(h.getHabitatType());
                        descHabitat.setText(h.getDescription());

                        llHabitat.setVisibility(View.GONE);
                        updateHabitat.setVisibility(View.VISIBLE);
                        deleteHabitat.setVisibility(View.VISIBLE);
                        habitatTypeHabitat.setVisibility(View.VISIBLE);
                        spinnerHabitat.setVisibility(View.GONE);
                        descHabitat.setVisibility(View.VISIBLE);
                        descHabitatETHabitat.setVisibility(View.GONE);
                    }
                });
                cancelHabitat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        llHabitat.setVisibility(View.GONE);
                        updateHabitat.setVisibility(View.VISIBLE);
                        deleteHabitat.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        deleteHabitat = findViewById(R.id.deleteHabitat);
        deleteHabitat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (realmResults.size() == 0) {
                    llHabitat.setVisibility(View.VISIBLE);
                    updateHabitat.setVisibility(View.GONE);
                    deleteHabitat.setVisibility(View.GONE);

                    confirmHabitat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(ViewHabitatDataActivity.this, "Habitat " + h.getId() + " deleted", Toast.LENGTH_SHORT).show();
                            realm.executeTransaction(r -> {
                                h.deleteFromRealm();
                            });
                            finish();
                        }
                    });
                    cancelHabitat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            llHabitat.setVisibility(View.GONE);
                            updateHabitat.setVisibility(View.VISIBLE);
                            deleteHabitat.setVisibility(View.VISIBLE);
                        }
                    });
                } else
                    Toast.makeText(ViewHabitatDataActivity.this, "There are animals living here", Toast.LENGTH_SHORT).show();
            }
        });

        isAdmin = getIntent().getExtras().getBoolean("isAdmin");
        if (isAdmin) {
            updateHabitat.setVisibility(View.VISIBLE);
            deleteHabitat.setVisibility(View.VISIBLE);
        }
    }

    private void getDataFromAnimals(List<Animal> animals) {
        for (Animal a : animals)
            animalList.add(new RecyclerViewAnimal(a, h));
    }

    private void addRecyclerView() {
        ARecyclerViewAdapter ARecyclerViewAdapter = new ARecyclerViewAdapter(this, animalList);
        ARecyclerViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewHabitatDataActivity.this, ViewAnimalDataActivity.class);
                i.putExtra("isAdmin", isAdmin);
                i.putExtra("aID", animalList.get(recyclerView.getChildAdapterPosition(view)).getAnimal().getId());
                i.putExtra("hID", h.getId());

                startActivity(i);
            }
        });
        recyclerView.setAdapter(ARecyclerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}