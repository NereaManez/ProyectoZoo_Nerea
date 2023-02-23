package es.nemamo.zoonerea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.nemamo.zoonerea.Model.Caretaker;
import es.nemamo.zoonerea.Model.Database;
import io.realm.Realm;

public class SignInActivity extends AppCompatActivity {
    private EditText userNameSignIn, passwordSignIn;
    private Button goSignIn, goToSignUp;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        realm = Database.getInstance().conect(getBaseContext());

        userNameSignIn = findViewById(R.id.userNameSignIn);
        passwordSignIn = findViewById(R.id.passwordSignIn);
        goSignIn = findViewById(R.id.goSignIn);
        goSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Caretaker c =  realm.where(Caretaker.class).equalTo("user", userNameSignIn.getText().toString()).findFirst();

                if (c == null)
                    Toast.makeText(SignInActivity.this, "No users found", Toast.LENGTH_SHORT).show();
                else
                    if (c.getPasswd().equals(passwordSignIn.getText().toString())) {
                        Intent i = new Intent(SignInActivity.this, HomeActivity.class);
                        i.putExtra("caretakerDNI", c.getDNI());
                        i.putExtra("isAdmin", c.getAdmin());

                        startActivity(i);
                    } else
                        passwordSignIn.setError("Incorrect password");
            }
        });

        goToSignUp = findViewById(R.id.goToSignUp);
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}