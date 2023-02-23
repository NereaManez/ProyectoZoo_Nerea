package es.nemamo.zoonerea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.nemamo.zoonerea.Model.Caretaker;
import es.nemamo.zoonerea.Model.Database;
import es.nemamo.zoonerea.Model.SGBD;
import io.realm.Realm;

public class SignUpActivity extends AppCompatActivity {
    private EditText DNISignUp, nameSignUp, surnameSignUp, userNameSignUp, passwordSignUp, confPasswordSignUp;
    private Button goSignUp, goToSignIn;

    private Realm realm;
    private SGBD sgbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        realm = Database.getInstance().conect(getBaseContext());
        sgbd = new SGBD(realm);

        DNISignUp = findViewById(R.id.DNISignUp);
        nameSignUp = findViewById(R.id.nameSignUp);
        surnameSignUp = findViewById(R.id.surnameSignUp);
        userNameSignUp = findViewById(R.id.userNameSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        confPasswordSignUp = findViewById(R.id.confPasswordSignUp);

        goSignUp = findViewById(R.id.goSignUp);
        goSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordSignUp.getText().toString().equals(confPasswordSignUp.getText().toString())) {
                    Caretaker c =  realm.where(Caretaker.class).equalTo("user", userNameSignUp.getText().toString()).findFirst();
                    if (c == null) {
                        c = sgbd.addCaretaker(DNISignUp.getText().toString(), nameSignUp.getText().toString(),
                                surnameSignUp.getText().toString(), userNameSignUp.getText().toString(),
                                passwordSignUp.getText().toString(), false);

                        Intent i = new Intent(SignUpActivity.this, HomeActivity.class);
                        i.putExtra("caretakerDNI", c.getDNI());
                        i.putExtra("isAdmin", c.getAdmin());

                        startActivity(i);
                    } else
                        userNameSignUp.setError("Username not aviable");
                } else {
                    confPasswordSignUp.setError("Passwords doesn't match");
                    confPasswordSignUp.setText("");
                }

            }
        });

    }
}