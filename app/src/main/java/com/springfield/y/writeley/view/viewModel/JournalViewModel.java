package com.springfield.y.writeley.view.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.springfield.y.writeley.model.Note;
import com.springfield.y.writeley.model.room.WriteleyDatabase;

import java.util.List;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

public class JournalViewModel extends AndroidViewModel {

    private static final String TAG = JournalViewModel.class.getSimpleName();
    private LiveData<List<Note>> notes;

    public JournalViewModel(@NonNull Application application) {
        super(application);
        WriteleyDatabase database = WriteleyDatabase.getInstance(application.getApplicationContext());
        notes = database.noteDAO().loadAllNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }
}
