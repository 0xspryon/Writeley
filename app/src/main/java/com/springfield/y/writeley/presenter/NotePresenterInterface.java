package com.springfield.y.writeley.presenter;

import com.springfield.y.writeley.model.Note;

/**
 * Created by ipr0d1g1 on 6/30/18.
 */

public interface NotePresenterInterface extends BasePresenterInterface {

    void saveNote(Note note);

    void updateNote(Note note);

}
