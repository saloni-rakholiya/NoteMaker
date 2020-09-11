package com.example.notesj;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {note.class},version = 1)
public abstract class notedatabase extends RoomDatabase {

    private static notedatabase instance;

    public abstract notedao notedao();

    public static synchronized notedatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),notedatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();

        }

        return instance;
    }

    private static RoomDatabase.Callback roomcallback=new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populatedb(instance).execute();
        }
    };
    private static class populatedb extends AsyncTask<Void,Void,Void>{
        private notedao notedao;
        private populatedb(notedatabase db)
        {
            notedao=db.notedao();

        }
        @Override
        protected Void doInBackground(Void... voids) {
            notedao.insert(new note("task 1","Description here",3));
            notedao.insert(new note("task 2","Description here",2));
            notedao.insert(new note("task 3","Description here",1));
            return null;
        }
    }
}
