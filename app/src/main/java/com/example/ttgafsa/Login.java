package com.example.ttgafsa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ttgafsa.Helpers.FirestoreHelper;
import com.example.ttgafsa.Helpers.LocalStorage;
import com.example.ttgafsa.Utility.FieldValidator;
import com.example.ttgafsa.Utility.ProgressBar;
import com.example.ttgafsa.Utility.StatusBarColorUtil;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    private EditText cin_edt, password_edt;
    private ConstraintLayout signin_btn;
    private FirebaseFirestore db;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarColorUtil.setStatusBarColor(this, R.color.white);
        setContentView(R.layout.login);

        db = FirebaseFirestore.getInstance();
        bindingViews();

        new FirestoreHelper().createTestUsers();

        progressBar = new ProgressBar(this);


        signin_btn.setOnClickListener(v -> {

            if (!FieldValidator.validateCIN(cin_edt) ||
                    !FieldValidator.validatePassword(password_edt)) {

                // Afficher un message d'erreur si les champs sont invalides
                Toast.makeText(this, "Vérifiez vos informations", Toast.LENGTH_SHORT).show();
            } else {
                // Récupérer les données des champs
                String cin = cin_edt.getText().toString().trim();
                String password = password_edt.getText().toString().trim();

                // Vérifier si l'utilisateur existe dans Firestore
                checkUserInFirestore(cin, password);
            }
        });
    }

    private void checkUserInFirestore(String cin, String password) {
        db.collection("users").document(cin).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                assert document != null;
                if (document.exists()) {
                    // L'utilisateur existe, vérifier le mot de passe
                    String storedPassword = document.getString("password");
                    if (storedPassword != null && storedPassword.equals(password)) {
                        // Mot de passe correct, accès à l'activité suivante
                        LocalStorage.setUserLoggedIn(this, true);
                        progressBar.show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {


                                progressBar.dismiss();
                                Intent intent = new Intent(Login.this, Dashboard.class);
                                startActivity(intent);
                                finish();
                            }
                        },1500);


                    } else {
                        // Mot de passe incorrect
                        Toast.makeText(this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // L'utilisateur n'existe pas
                    Toast.makeText(this, "Utilisateur non trouvé", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Erreur de connexion à Firestore
                Toast.makeText(this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindingViews() {
        cin_edt = findViewById(R.id.cin_edt);
        password_edt = findViewById(R.id.password_edt);
        signin_btn = findViewById(R.id.signin_btn);
    }
}
