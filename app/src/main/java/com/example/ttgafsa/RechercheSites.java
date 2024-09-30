package com.example.ttgafsa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ttgafsa.Utility.StatusBarColorUtil;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RechercheSites extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {

    private ImageView returnButton;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<String> dataList;
    private List<String> filteredList;
    private TextView noResultText;
    private EditText searchView;
    private AutoCompleteTextView typeDropdown;
    private FirebaseFirestore db;
    String[] types = {"BTS", "IPMSAN"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarColorUtil.setStatusBarColor(this, R.color.white);
        setContentView(R.layout.recherche_sites);

        bindingViews();
        displayItemsOnDropMenu();



        db = FirebaseFirestore.getInstance();

        returnButton.setOnClickListener(view -> finish());

        dataList = new ArrayList<>();
        filteredList = new ArrayList<>();

        adapter = new RecyclerViewAdapter(this, filteredList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Listen for dropdown changes
        typeDropdown.setOnItemClickListener((parent, view, position, id) -> {
            String selectedType = typeDropdown.getText().toString().trim();
            fetchDataByType(selectedType);
        });

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void bindingViews() {
        returnButton = findViewById(R.id.return_row);
        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.recycler_view);
        noResultText = findViewById(R.id.no_result_text);
        typeDropdown = findViewById(R.id.type_dropdown);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchDataByType(String type) {
        db.collection("equipments")
                .document(type)
                .collection("equipmentList")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            dataList.clear();
                            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                dataList.add(document.getId()); // Assuming document ID is the equipment name
                            }
                            filteredList.clear();
                            filteredList.addAll(dataList);
                            adapter.notifyDataSetChanged();
                            noResultText.setVisibility(View.GONE);
                        } else {
                            dataList.clear();
                            filteredList.clear();
                            adapter.notifyDataSetChanged();
                            noResultText.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(dataList);
        } else {
            text = text.toLowerCase();
            for (String model : dataList) {
                if (model.toLowerCase().contains(text)) {
                    filteredList.add(model);
                }
            }
        }
        adapter.notifyDataSetChanged();

        if (filteredList.isEmpty()) {
            noResultText.setVisibility(View.VISIBLE);
        } else {
            noResultText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(String model) {
        // Handle item click
        Toast.makeText(this, "Clicked: " + model, Toast.LENGTH_SHORT).show();
    }


    private void displayItemsOnDropMenu() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, types);
        typeDropdown.setAdapter(adapter);
    }



}
