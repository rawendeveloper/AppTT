package com.example.ttgafsa.Helpers;

import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirestoreHelper {

    private FirebaseFirestore db;

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public void createTestUsers() {
        // Exemple de paires CIN/Mot de passe
        addUser("14324669", "admin123");
    }

    private void addUser(String cin, String password) {
        // Créer un map des données utilisateur
        Map<String, String> userData = new HashMap<>();
        userData.put("cin", cin);
        userData.put("password", password);

        // Ajouter les données à la collection "users" avec le CIN comme identifiant
        db.collection("users")
                .document(cin)
                .set(userData)
                .addOnSuccessListener(aVoid -> {
                    // Succès, vous pouvez afficher un message de succès ici si vous le souhaitez

                })
                .addOnFailureListener(e -> {
                    // En cas d'échec, vous pouvez afficher un message d'erreur ici
                });
    }
}
