package com.example.notesj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class addnote extends AppCompatActivity {

    public static final String
    EditText title;
    EditText desc;
    NumberPicker np;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);
        title=findViewById(R.id.editText);
        desc=findViewById(R.id.editText2);
        np=findViewById(R.id.np);

        np.setMinValue(1);
        np.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save:savenote(); return true;
            case R.id.close: break;
            default:return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void savenote() {
        String t=title.getText().toString();
        String d=desc.getText().toString();
        int p=np.getValue();
        if(t.trim().isEmpty() || d.trim().isEmpty())
            {Toast.makeText(addnote.this,"Enter Valid title and description",4000).show();
            return ;}
    }
}
