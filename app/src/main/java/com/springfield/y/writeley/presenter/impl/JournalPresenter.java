package com.springfield.y.writeley.presenter.impl;

import com.firebase.ui.auth.AuthUI;
import com.springfield.y.writeley.model.ModelRepository;
import com.springfield.y.writeley.model.Note;
import com.springfield.y.writeley.presenter.JournalPresenterInterface;
import com.springfield.y.writeley.view.JournalActivityView;
import com.springfield.y.writeley.view.impl.JournalActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

public class JournalPresenter implements JournalPresenterInterface {
    JournalActivityView view;
    String[] months;
    int monthOfYear;
    ModelRepository model;
    private ArrayList<Note> notes;

    public JournalPresenter(JournalActivityView view) {
        this.view = view;
    }

    @Override
    public void takeOverExecution() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        model = new ModelRepository(((JournalActivity) view).getApplicationContext());

        monthOfYear = calendar.get(Calendar.MONTH);
        setMonth(monthOfYear);

        view.getMonths();

    }

    @Override
    public void setMonth(int month) {
        monthOfYear = month;
        view.setupViewModel(month);
    }

    @Override
    public void deleteNote(Note note) {
        model.deleteNotes(note);
    }

    @Override
    public void logout() {
        model.deleteNotes();
        AuthUI.getInstance().signOut(((JournalActivity) view));
        view.finish();
    }

    public void setNotes(List<Note> notes) {
        this.notes = ((ArrayList<Note>) notes);
        view.setNotesForMonth(this.notes);
    }
}
