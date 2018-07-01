package com.springfield.y.writeley.view;

import com.springfield.y.writeley.model.Note;

import java.util.ArrayList;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

public interface JournalActivityView extends BaseView {

    /**
     * Sets the notes for a month in the implementing class
     */
    void setNotesForMonth(ArrayList<Note> notes);

    /**
     * returns the list of months in a year
     */
    String[] getMonths();

    /**
     * closes a view
     */
    void finish();

    /**
     * initializes a viewmodel and sets it up with it's various liveData objects for showing journal notes
     */
    void setupViewModel(int MonthOfYear);
}
