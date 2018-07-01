package com.springfield.y.writeley.model;

import android.content.Context;
import android.os.AsyncTask;

import com.springfield.y.writeley.model.room.WriteleyDatabase;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

public class ModelRepository implements ModelRepositoryInterface {
    private WriteleyDatabase mDb;
    private Context context;

    public ModelRepository(Context context) {
        this.context = context;
        mDb = WriteleyDatabase.getInstance(context.getApplicationContext());
    }

    @Override
    public void saveNotes(Note... notes) {

        AsyncTask task = new AsyncModel(AsyncModel.ACTION_INSERT, notes);
        task.execute();
    }

    @Override
    public void deleteNotes(Note... notes) {
        AsyncTask task = new AsyncModel(AsyncModel.ACTION_DELETE, notes);
        task.execute();
    }

    @Override
    public void updateNotes(Note... notes) {
        AsyncTask task = new AsyncModel(AsyncModel.ACTION_UPDATE, notes);
        task.execute();
    }

//    @Override
//    public LiveData<ArrayList<Note>> setupViewModel() {
//
//        // TODO: remember to change this to something threadsafe
//        return mDb.noteDAO().loadAllNotes());
//    }

//    @Override
//    public LiveData<List<Note>> setupViewModel(int monthOfYear) {
//        // TODO: update this method so as to take into account the year and not hard code it
//        Date date1 = new Date(2018, monthOfYear,1);
//        Date date2 = new Date(2018, monthOfYear + 1,1);
//        LiveData<List<Note>> notes$ = mDb.noteDAO().getNotesInInterval(date1.getTime(), date2.getTime());
//        notes$.observe(((Activity) context), new Observer<List<Note>>() {
//            @Override
//            public void onChanged(@Nullable List<Note> notes) {
//
//            }
//        });
//
//    }

    @Override
    public void deleteAllNotes() {
        // TODO: remember to implement this method
    }

    private class AsyncModel extends AsyncTask<Object, Object, Object> {
        public static final int ACTION_INSERT = 1;
        public static final int ACTION_UPDATE = 2;
        public static final int ACTION_DELETE = 3;
        public static final int ACTION_READ = 4;

        private int mAction;
        private Note notes[];

        public AsyncModel(int mAction, Note... notes) {
            this.mAction = mAction;
            this.notes = notes;
        }

        @Override
        protected Object doInBackground(Object... objects) {
            for (Note note : notes) {
                switch (mAction) {
                    // TODO: remember to save to firebase db from here
                    case ACTION_INSERT:
                        mDb.noteDAO().insertNote(note);
                        break;

                    case ACTION_UPDATE:
                        mDb.noteDAO().updateNote(note);
                        break;

                    case ACTION_DELETE:
                        mDb.noteDAO().deleteNote(note);
                        break;
                }
            }
            return null;
        }
    }
}
