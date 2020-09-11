package com.example.notesj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class addnote extends AppCompatActivity {


    public static final String EXTRA_ID = "com.example.notesj.EXTRAID";
    public static final String EXTRA_TITLE = "com.example.notesj.EXTRATITLE";
    public static final String EXTRA_DESC = "com.example.notesj.EXTRADESC";
    public static final String EXTRA_PRIORITY = "com.example.notesj.EXTRAPRIORITY";
    EditText title;
    EditText desc;
    NumberPicker np;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);
        title = findViewById(R.id.editText);
        desc = findViewById(R.id.editText2);
        np = findViewById(R.id.np);

        np.setMinValue(1);
        np.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            Toast.makeText(addnote.this,"popopo",8000).show();
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            desc.setText(intent.getStringExtra(EXTRA_DESC));
            np.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                savenote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void savenote() {
        String t = title.getText().toString();
        String d = desc.getText().toString();
        int p = np.getValue();
        if (t.trim().isEmpty() || d.trim().isEmpty()) {
            Toast.makeText(addnote.this, "Enter Valid title and description", 4000).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, t);
        data.putExtra(EXTRA_DESC, d);
        data.putExtra(EXTRA_PRIORITY, p);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }
}
