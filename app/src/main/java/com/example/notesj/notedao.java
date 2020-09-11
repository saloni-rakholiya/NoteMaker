package com.example.notesj;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface notedao {

    @Insert
    void insert(note note);

    @Update
    void update(note note);

    @Delete
    void delete(note note);


    @Query("DELETE FROM note_table")
    void deleteall();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<note>> getallnotes();
}
