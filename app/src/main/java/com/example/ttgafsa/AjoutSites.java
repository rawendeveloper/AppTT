package com.example.ttgafsa;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ttgafsa.Utility.FormValidator;
import com.example.ttgafsa.Utility.StatusBarColorUtil;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AjoutSites extends AppCompatActivity {


    String[] types = {"BTS", "IPMSAN"};
    AutoCompleteTextView autoCompleteTextView;
    ImageView returnButton;
    ConstraintLayout ajouter_btn;
    EditText nom_edt,adresse_edt,description_edt;
    TextView repaire_location;
    FirebaseFirestore db;



    @Override
    public void onBackPressed() {
        // Prevent back press on the splash screen
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarColorUtil.setStatusBarColor(this, R.color.white);
        setContentView(R.layout.ajout_sites);

        bindingViews();
        displayItemsOnDropMenu();


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = (String) parent.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), "Selected type: " + selectedType, Toast.LENGTH_SHORT).show();


            }
        });



        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


            ajouter_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (FormValidator.validateNom(nom_edt) &&
                            FormValidator.validateAdresse(adresse_edt) &&
                            FormValidator.validateDescription(description_edt) &&
                            FormValidator.validateLocation(repaire_location, getApplicationContext()) &&
                            FormValidator.validateType(autoCompleteTextView, getApplicationContext())) {

                        // Proceed with form submission or next steps
                        ajout_sites();

                    } else {
                        // Handle the validation failure
                    }
                }
            });



    }  //onCreate

    private void ajout_sites() {
        // Collect data from form
        String address = adresse_edt.getText().toString().trim();
        String description = description_edt.getText().toString().trim();
        String nom_equippement = nom_edt.getText().toString().trim();
        String location = repaire_location.getText().toString().trim();
        String type = autoCompleteTextView.getText().toString().trim();

        // Create a map of the data
        Map<String, Object> equipmentData = new HashMap<>();
        equipmentData.put("address", address);
        equipmentData.put("description", description);
        equipmentData.put("nom_equippement", nom_equippement);
        equipmentData.put("location", location);
        equipmentData.put("type", type);

        // Get Firestore instance
        db = FirebaseFirestore.getInstance();

        // Save data under the correct type collection (BTS or IPMSAN) with the equipment name as the document ID
        db.collection("equipments")
                .document(type)  // BTS or IPMSAN
                .collection("equipmentList")  // Collection for equipment under BTS or IPMSAN
                .document(nom_equippement)  // Document ID is the equipment name
                .set(equipmentData)
                .addOnSuccessListener(aVoid -> {
                    // Handle success
                    Toast.makeText(getApplicationContext(), "Equipment added successfully!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    Toast.makeText(getApplicationContext(), "Error adding equipment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }



    private void displayItemsOnDropMenu() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, types);
        autoCompleteTextView.setAdapter(adapter);
    }



    private void bindingViews() {
        returnButton = findViewById(R.id.return_row);
        autoCompleteTextView = findViewById(R.id.select_type);
        ajouter_btn = findViewById(R.id.ajouter_btn);
        nom_edt = findViewById(R.id.nom_edt);
        adresse_edt = findViewById(R.id.adresse_edt);
        description_edt = findViewById(R.id.description_edt);
        repaire_location = findViewById(R.id.repaire_location);
    }





}