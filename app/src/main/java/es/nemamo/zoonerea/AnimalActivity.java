package es.nemamo.zoonerea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.nemamo.zoonerea.Model.Animal;
import es.nemamo.zoonerea.Model.Database;
import es.nemamo.zoonerea.Model.Habitat;
import es.nemamo.zoonerea.RecyclerViewAnimal.ARecyclerViewAdapter;
import es.nemamo.zoonerea.RecyclerViewAnimal.RecyclerViewAnimal;
import io.realm.Realm;
import io.realm.RealmResults;

public class AnimalActivity extends AppCompatActivity {

    private Realm realm;
    private List<RecyclerViewAnimal> animalList = new ArrayList<>();
    private boolean isAdmin;

    private RecyclerView recyclerView;
    private FloatingActionButton addAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        realm = Database.getInstance().conect(getBaseContext());
        addAnimal = findViewById(R.id.addAnimal);
        addAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AnimalActivity.this, AddAnimalActivity.class);
                startActivity(i);
                finish();
            }
        });

        isAdmin = getIntent().getExtras().getBoolean("isAdmin");

        if (isAdmin)
            addAnimal.setVisibility(View.VISIBLE);

        RealmResults<Animal> realmResults = realm.where(Animal.class).findAll();
        List<Animal> animals = realm.copyFromRealm(realmResults);
        getDataFromAnimals(animals);

        recyclerView = findViewById(R.id.recyclerViewAnimal);
        addRecyclerView();
    }

    private void getDataFromAnimals(List<Animal> animals) {
        Habitat h;

        for (Animal a : animals) {
            h = realm.where(Habitat.class).equalTo("id", a.getIdHabitat()).findFirst();
            animalList.add(new RecyclerViewAnimal(a, h));
        }
    }

    private void addRecyclerView() {
        ARecyclerViewAdapter ARecyclerViewAdapter = new ARecyclerViewAdapter(this, animalList);
        ARecyclerViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AnimalActivity.this, ViewAnimalDataActivity.class);
                i.putExtra("isAdmin", isAdmin);
                i.putExtra("aID", animalList.get(recyclerView.getChildAdapterPosition(view)).getAnimal().getId());
                i.putExtra("hID", animalList.get(recyclerView.getChildAdapterPosition(view)).getHabitat().getId());

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