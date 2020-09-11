package com.example.notesj;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class repository {
    private notedao notedao;
    private LiveData<List<note>> allnotes;

    public repository(Application application)
    {
        notedatabase notedatabase= com.example.notesj.notedatabase.getInstance(application);
        notedao=notedatabase.notedao();
        allnotes= notedao.getallnotes();

    }

    public void inset(note note)
    {
        new insertnoteasynctask(notedao).execute(note);

    }

    public void update(note note)
    {
        new updatenoteasynctask(notedao).execute(note);

    }
    public void delete(note note)
    {
        new deletenoteasynctask(notedao).execute(note);

    }
    public void deleteall()
    {

     new deleteallnoteasynctask(notedao).execute();
    }
    public LiveData<List<note>> getAllnotes()
    {
        return allnotes;
    }

    private static class insertnoteasynctask extends AsyncTask<note,Void,Void>
    {
        private notedao notedao;

        private insertnoteasynctask(notedao notedao)
        {
            this.notedao=notedao;
        }

        @Override
        protected Void doInBackground(note... notes) {

            notedao.insert(notes[0]);
            return null;
        }
    }



    private static class updatenoteasynctask extends AsyncTask<note,Void,Void>
    {
        private notedao notedao;

        private updatenoteasynctask(notedao notedao)
        {
            this.notedao=notedao;
        }

        @Override
        protected Void doInBackground(note... notes) {

            notedao.update(notes[0]);
            return null;
        }
    }

    private static class deletenoteasynctask extends AsyncTask<note,Void,Void>
    {
        private notedao notedao;

        private deletenoteasynctask(notedao notedao)
        {
            this.notedao=notedao;
        }

        @Override
        protected Void doInBackground(note... notes) {

            notedao.delete(notes[0]);
            return null;
        }
    }

    private static class deleteallnoteasynctask extends AsyncTask<Void,Void,Void>
    {
        private notedao notedao;

        private deleteallnoteasynctask(notedao notedao)
        {
            this.notedao=notedao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            notedao.deleteall();
            return null;
        }
    }


}
