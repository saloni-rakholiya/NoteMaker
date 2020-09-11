package com.example.notesj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADDNOTEREQUEST = 1;
    public static final int ADDEDITREQ = 2;
    private noteviewmodel noteviewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton f = findViewById(R.id.floatjust);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, addnote.class);
                startActivityForResult(intent, ADDNOTEREQUEST);
            }
        });


        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        final noteadapter adapter = new noteadapter();
        recyclerView.setAdapter(adapter);


        noteviewmodel = ViewModelProviders.of(this).get(com.example.notesj.noteviewmodel.class);
        noteviewmodel.getAllnotes().observe(this, new Observer<List<note>>() {
            @Override
            public void onChanged(List<note> notes) {
                adapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                noteviewmodel.delete(adapter.getnote(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Deleted", 4000).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setonitemclicklistener(new noteadapter.onitemlistener() {
            @Override
            public void onitemclick(note note) {
                Intent intent = new Intent(MainActivity.this, addnote.class);
                intent.putExtra(addnote.EXTRA_TITLE, note.getTitle());
                intent.putExtra(addnote.EXTRA_DESC, note.getDescription());
                intent.putExtra(addnote.EXTRA_PRIORITY, note.getPriority());
                intent.putExtra(addnote.EXTRA_ID, note.getId());
                startActivityForResult(intent, ADDEDITREQ);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADDNOTEREQUEST && resultCode == RESULT_OK) {
            String t = data.getStringExtra(addnote.EXTRA_TITLE);
            String d = data.getStringExtra(addnote.EXTRA_DESC);
            int p = data.getIntExtra(addnote.EXTRA_PRIORITY, 1);

            note note = new note(t, d, p);
            noteviewmodel.insert(note);

            Toast.makeText(MainActivity.this, "Note Saved", 3000).show();

        } else if (requestCode == ADDEDITREQ && resultCode == RESULT_OK) {
            int id = data.getIntExtra(addnote.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(MainActivity.this, "Note not Updated", 3000).show();
                return;
            }
            String t = data.getStringExtra(addnote.EXTRA_TITLE);
            String d = data.getStringExtra(addnote.EXTRA_DESC);
            int p = data.getIntExtra(addnote.EXTRA_PRIORITY, 1);
            note note = new note(t, d, p);
            note.setId(id);

            noteviewmodel.update(note);
            Toast.makeText(MainActivity.this, "Note Updated", 3000).show();


        } else {
            Toast.makeText(MainActivity.this, "Note not Saved", 3000).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.del: {
                noteviewmodel.deleteallnotes();
                Toast.makeText(MainActivity.this, "All notes deleted", 3000).show();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
