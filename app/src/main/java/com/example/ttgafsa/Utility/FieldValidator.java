package com.example.ttgafsa.Utility;

import android.widget.EditText;


public class FieldValidator {

    public static boolean validateName(EditText editText) {
        String val = editText.getText().toString().trim();
        if (val.isEmpty()) {
            editText.requestFocus();
            editText.setError("Field cannot be empty");
            return false;
        } else if (val.length() <= 6) {
            editText.requestFocus();
            editText.setError("Name is too short");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public static boolean validateEmail(EditText editText) {
        String val = editText.getText().toString().trim();
        if (val.isEmpty()) {
            editText.requestFocus();
            editText.setError("Field cannot be empty");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(val).matches()) {
            editText.requestFocus();
            editText.setError("Invalid email address");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }
    //we can use this too : we will see..
    // String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //else if (!val.matches(emailPattern))


    public static boolean validatePassword(EditText editText) {
        String val = editText.getText().toString().trim();
        if (val.isEmpty()) {
            editText.requestFocus();
            editText.setError("Le champ ne peut pas être vide");
            return false;
        } else if (val.length() <= 6) {
            editText.requestFocus();
            editText.setError("Le mot de passe est trop court");
            return false;
        }
        else if (val.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$") || val.trim().length() <= 7) {
            editText.requestFocus();
            editText.setError("Le mot de passe doit contenir des lettres et des chiffres");
            return false;
        }
        else {
            editText.setError(null);
            return true;
        }
    }

    public static boolean validateConfPassword(EditText password, EditText confPassword) {
        String val = confPassword.getText().toString().trim();
        if (val.isEmpty()) {
            confPassword.requestFocus();
            confPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.equals(password.getText().toString().trim())) {
            confPassword.requestFocus();
            confPassword.setError("Passwords do not match");
            return false;
        } else {
            confPassword.setError(null);
            return true;
        }
    }

    public static boolean validatePhone(EditText editText) {
        String val = editText.getText().toString().trim();
        if (val.isEmpty()) {
            editText.requestFocus();
            editText.setError("Field cannot be empty");
            return false;
        } else if (val.length() != 8) {
            editText.requestFocus();
            editText.setError("Invalid phone number");
            return false;
        } else if (!val.startsWith("2") && !val.startsWith("9") && !val.startsWith("5") && !val.startsWith("4")) {
            editText.requestFocus();
            editText.setError("Invalid phone number");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }




    public static boolean validateCIN(EditText editText) {
        String val = editText.getText().toString().trim();
        if (val.isEmpty()) {
            editText.requestFocus();
            editText.setError("Le champ ne peut pas être vide");
            return false;
        } else if (val.length() != 8) {
            editText.requestFocus();
            editText.setError("Le CIN doit contenir 8 chiffres");
            return false;
        } else if (!val.matches("\\d{8}")) {
            editText.requestFocus();
            editText.setError("Le CIN doit contenir uniquement des chiffres");            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }





}
