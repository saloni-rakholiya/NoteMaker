package com.example.notesj;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class noteviewmodel extends AndroidViewModel {

    private repository repository;
    private LiveData<List<note>> allnotes;

    public noteviewmodel(@NonNull Application application) {

        super(application);
        repository=new repository(application);
        allnotes=repository.getAllnotes();

    }

    public void insert(note note)
    {
        repository.inset(note);
    }

    public void update(note note)
    {repository.update(note);}

    public void delete(note note)
    {repository.delete(note);}

    public void deleteallnotes(){
        repository.deleteall();
    }

    public LiveData<List<note>> getAllnotes()
    {return  allnotes;}


}
