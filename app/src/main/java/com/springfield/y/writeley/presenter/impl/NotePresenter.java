package com.springfield.y.writeley.presenter.impl;

import com.springfield.y.writeley.model.ModelRepository;
import com.springfield.y.writeley.model.Note;
import com.springfield.y.writeley.presenter.NotePresenterInterface;
import com.springfield.y.writeley.view.NoteActivityView;
import com.springfield.y.writeley.view.impl.NoteActivity;

/**
 * Created by ipr0d1g1 on 6/30/18.
 */

public class NotePresenter implements NotePresenterInterface {
    NoteActivityView view;
    Note note;

    ModelRepository model;

    public NotePresenter(NoteActivityView view) {
        this.view = view;
        model = new ModelRepository(((NoteActivity) view).getApplicationContext());
    }

    @Override
    public void takeOverExecution() {
        if (this.view.determineIfEditMode()) {
            this.note = view.getNote();
        }
    }

    @Override
    public void saveNote(Note note) {
        model.saveNotes(note);
        view.finish();
    }

    @Override
    public void updateNote(Note note) {
        model.updateNotes(note);
        view.finish();
    }
}
