package es.nemamo.zoonerea;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.nemamo.zoonerea.Model.Animal;
import es.nemamo.zoonerea.Model.Caretaker;
import es.nemamo.zoonerea.Model.DailyCare;
import es.nemamo.zoonerea.Model.Database;
import es.nemamo.zoonerea.Model.Task;
import es.nemamo.zoonerea.Model.TaskType;
import es.nemamo.zoonerea.RecyclerViewDailyCare.DCRecyclerViewAdapter;
import es.nemamo.zoonerea.RecyclerViewDailyCare.RecyclerViewDailyCare;
import io.realm.Realm;
import io.realm.RealmResults;

public class DailyCareActivity extends AppCompatActivity {

    private Realm realm;
    private List<RecyclerViewDailyCare> diaryCareList = new ArrayList<>();
    private Caretaker c;

    private RecyclerView recyclerView;
    private FloatingActionButton addTask;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_care);

        realm = Database.getInstance().conect(getBaseContext());
        addTask = findViewById(R.id.addTask);

        String cDNI = getIntent().getExtras().getString("caretakerDNI");

        c = realm.where(Caretaker.class).equalTo("DNI", cDNI).findFirst();
        RealmResults<DailyCare> dailyCareRealmResults;
        List<DailyCare> dailyCares;

        if (c.getAdmin()) {
            dailyCareRealmResults = realm.where(DailyCare.class).findAll();
            addTask.setVisibility(View.VISIBLE);
        } else
            dailyCareRealmResults = realm.where(DailyCare.class).equalTo("idCaretaker", c.getDNI())
                    .equalTo("done", false).findAll();

        dailyCares = realm.copyFromRealm(dailyCareRealmResults);
        getDataFromDiaryCare(dailyCares);

        recyclerView = findViewById(R.id.recyclerViewDC);
        addRecyclerView();
    }

    private void getDataFromDiaryCare(List<DailyCare> dailyCares) {
        Animal a;
        Task t;
        TaskType tt;
        for (DailyCare dc : dailyCares) {
            a = realm.where(Animal.class).equalTo("id", dc.getIdAnimal()).findFirst();
            t = realm.where(Task.class).equalTo("id", dc.getIdTask()).findFirst();
            tt = realm.where(TaskType.class).equalTo("id", t.getIdTaskType()).findFirst();

            diaryCareList.add(new RecyclerViewDailyCare(dc, t, tt, a));
        }
    }

    private void addRecyclerView() {
        DCRecyclerViewAdapter DCRecyclerViewAdapter = new DCRecyclerViewAdapter(this, diaryCareList);
        DCRecyclerViewAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DailyCareActivity.this, ViewDailyCareDataActivity.class);
                i.putExtra("dcID", diaryCareList.get(recyclerView.getChildAdapterPosition(view)).getDiaryCare().getId());
                i.putExtra("aID", diaryCareList.get(recyclerView.getChildAdapterPosition(view)).getAnimal().getId());
                i.putExtra("tID", diaryCareList.get(recyclerView.getChildAdapterPosition(view)).getTask().getId());
                i.putExtra("ttID", diaryCareList.get(recyclerView.getChildAdapterPosition(view)).getTaskType().getId());
                i.putExtra("isAdmin", c.getAdmin());
                i.putExtra("rTimes", diaryCareList.get(recyclerView.getChildAdapterPosition(view)).getCount());

                startActivity(i);
            }
        });
        recyclerView.setAdapter(DCRecyclerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}