package com.springfield.y.writeley.view;

import com.springfield.y.writeley.model.Note;

/**
 * Created by ipr0d1g1 on 6/30/18.
 */

public interface NoteActivityView extends BaseView {
    boolean determineIfEditMode();

    Note getNote();

    void setNote(Note note);

    void saveNote();

    void finish();
}
