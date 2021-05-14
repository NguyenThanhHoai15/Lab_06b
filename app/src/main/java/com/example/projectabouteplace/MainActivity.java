package com.example.projectabouteplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Place> list;
    private RecyclerView recyclerView;
    private DatabaseHandler databaseHandler;
    public static EditText editValue;
    private PlaceAdapter adapter;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv_view);
        databaseHandler = new DatabaseHandler(this);
        editValue = findViewById(R.id.editValue);

        addControls();

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = editValue.getText().toString().trim();
                if(value.isEmpty())
                    editValue.setError("please enter place name!");
                else{
                    databaseHandler.addPlace(value);
                    editValue.setText("");
                    addControls();
                }
            }
        });
    }

    public void addControls(){
        list = new ArrayList<>(databaseHandler.getALLPlace());
        adapter = new PlaceAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}