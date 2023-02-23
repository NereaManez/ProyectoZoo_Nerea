package es.nemamo.zoonerea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.nemamo.zoonerea.Model.Animal;
import es.nemamo.zoonerea.Model.DailyCare;
import es.nemamo.zoonerea.Model.Database;
import es.nemamo.zoonerea.Model.Task;
import es.nemamo.zoonerea.Model.TaskType;
import io.realm.Realm;

public class ViewDailyCareDataActivity extends AppCompatActivity {

    private Realm realm;
    private DailyCare dc;
    private Animal a;
    private Task t;
    private TaskType tt;
    private boolean isAdmin;

    private TextView infoDC, descDC, animalDC, habitatDC, freqDC, timesDC;
    private ImageButton confirmDC, cancelDC;
    private LinearLayout llDC;
    private Button completeDC, updateDC, deleteDC;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_daily_care_data);

        realm = Database.getInstance().conect(getBaseContext());

        int rTimes = getIntent().getExtras().getInt("rTimes");
        timesDC = findViewById(R.id.timesDC);
        timesDC.setText(String.valueOf(rTimes));

        int dcID = getIntent().getExtras().getInt("dcID");
        dc = realm.where(DailyCare.class).equalTo("id", dcID).findFirst();

        int aID = getIntent().getExtras().getInt("aID");
        a = realm.where(Animal.class).equalTo("id", aID).findFirst();

        animalDC = findViewById(R.id.animalDC);
        animalDC.setText(a.getName());
        habitatDC = findViewById(R.id.habitatDC);
        habitatDC.setText(String.valueOf(a.getIdHabitat()));

        int tID = getIntent().getExtras().getInt("tID");
        t = realm.where(Task.class).equalTo("id", tID).findFirst();

        freqDC = findViewById(R.id.freqDC);
        freqDC.setText(t.getFrequency());

        int ttID = getIntent().getExtras().getInt("ttID");
        tt = realm.where(TaskType.class).equalTo("id", ttID).findFirst();

        descDC = findViewById(R.id.descDC);
        descDC.setText(tt.getDescription());
        infoDC = findViewById(R.id.infoDC);
        infoDC.setText(dc.getId() + "  -  " + tt.getTitle());

        llDC = findViewById(R.id.llDC);
        confirmDC = findViewById(R.id.confirmDC);
        cancelDC = findViewById(R.id.cancelDC);

        completeDC = findViewById(R.id.completeDC);
        completeDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDC.setVisibility(View.VISIBLE);
                completeDC.setVisibility(View.GONE);

                cancelDC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        llDC.setVisibility(View.GONE);
                        updateDC.setVisibility(View.VISIBLE);
                        deleteDC.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        updateDC = findViewById(R.id.updateDC);
        updateDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDC.setVisibility(View.VISIBLE);
                updateDC.setVisibility(View.GONE);
                deleteDC.setVisibility(View.GONE);

                cancelDC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        llDC.setVisibility(View.GONE);
                        updateDC.setVisibility(View.VISIBLE);
                        deleteDC.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        deleteDC = findViewById(R.id.deleteDC);
        deleteDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDC.setVisibility(View.VISIBLE);
                updateDC.setVisibility(View.GONE);
                deleteDC.setVisibility(View.GONE);

                cancelDC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        llDC.setVisibility(View.GONE);
                        updateDC.setVisibility(View.VISIBLE);
                        deleteDC.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        isAdmin = getIntent().getExtras().getBoolean("isAdmin");
        if (isAdmin) {
            updateDC.setVisibility(View.VISIBLE);
            deleteDC.setVisibility(View.VISIBLE);
        }
    }
}