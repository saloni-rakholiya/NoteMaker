package com.example.notesj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private noteviewmodel noteviewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        final noteadapter adapter=new noteadapter();
        recyclerView.setAdapter(adapter);


        noteviewmodel= ViewModelProviders.of(this).get(com.example.notesj.noteviewmodel.class);
        noteviewmodel.getAllnotes().observe(this, new Observer<List<note>>() {
            @Override
            public void onChanged(List<note> notes) {
                adapter.setnotes(notes);
            }
        });

    }
}
