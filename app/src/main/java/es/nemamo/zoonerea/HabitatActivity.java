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

import es.nemamo.zoonerea.Model.Database;
import es.nemamo.zoonerea.Model.Habitat;
import es.nemamo.zoonerea.RecyclerViewHabitat.HRecyclerViewAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

public class HabitatActivity extends AppCompatActivity {

    private Realm realm;
    private List<Habitat> habitatList = new ArrayList<>();
    private boolean isAdmin;

    private RecyclerView recyclerView;
    private FloatingActionButton addHabitat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitat);

        realm = Database.getInstance().conect(getBaseContext());
        addHabitat = findViewById(R.id.addHabitat);
        addHabitat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HabitatActivity.this, AddHabitatActivity.class);
                startActivity(i);
                finish();
            }
        });

        isAdmin = getIntent().getExtras().getBoolean("isAdmin");

        if (isAdmin)
            addHabitat.setVisibility(View.VISIBLE);

        RealmResults<Habitat> realmResults = realm.where(Habitat.class).findAll();
        habitatList = realm.copyFromRealm(realmResults);

        recyclerView = findViewById(R.id.recyclerViewHabitat);
        addRecyclerView();
    }

    private void addRecyclerView() {
        HRecyclerViewAdapter HRecyclerViewAdapter = new HRecyclerViewAdapter(this, habitatList);
        HRecyclerViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HabitatActivity.this, ViewHabitatDataActivity.class);
                i.putExtra("isAdmin", isAdmin);
                i.putExtra("hID", habitatList.get(recyclerView.getChildAdapterPosition(view)).getId());

                startActivity(i);
                finish();
            }
        });
        recyclerView.setAdapter(HRecyclerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}