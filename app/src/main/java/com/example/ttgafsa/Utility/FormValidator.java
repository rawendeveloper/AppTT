package com.example.ttgafsa.Utility;

import android.content.Context;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormValidator {

    // Validate the "Nom" field
    public static boolean validateNom(EditText nomEdt) {
        String nom = nomEdt.getText().toString().trim();
        if (nom.isEmpty()) {
            nomEdt.setError("Le nom ne peut pas être vide");
            nomEdt.requestFocus();
            return false;
        }
        // Add more validation rules if needed
        return true;
    }

    // Validate the "Adresse" field
    public static boolean validateAdresse(EditText adresseEdt) {
        String adresse = adresseEdt.getText().toString().trim();
        if (adresse.isEmpty()) {
            adresseEdt.setError("L'adresse ne peut pas être vide");
            adresseEdt.requestFocus();
            return false;
        }
        // Add more validation rules if needed
        return true;
    }

    // Validate the "Description" field
    public static boolean validateDescription(EditText descriptionEdt) {
        String description = descriptionEdt.getText().toString().trim();
        if (description.isEmpty()) {
            descriptionEdt.setError("La description ne peut pas être vide");
            descriptionEdt.requestFocus();
            return false;
        }
        // Add more validation rules if needed
        return true;
    }

    // Validate location selection
    public static boolean validateLocation(TextView locationEdt, Context context) {
     /*   String location = locationEdt.getText().toString().trim();
        if (location.equals("Choisir la localisation")) {
            Toast.makeText(context, "Veuillez choisir une localisation valide", Toast.LENGTH_SHORT).show();
            return false;
        }
        */

        return true;
    }

    // Validate "Type" selection
    public static boolean validateType(AutoCompleteTextView type, Context context) {
        String selectedText = type.getText().toString().trim();
        if (type.equals("choisir le type")) {
            Toast.makeText(context, "Veuillez choisir un type valide", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Add more validation methods for other fields as required
}
