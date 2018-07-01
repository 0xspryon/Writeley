package com.springfield.y.writeley.presenter;

import com.springfield.y.writeley.model.Note;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

public interface JournalPresenterInterface extends BasePresenterInterface {
    void setMonth(int month);

//    void setupViewModel(int monthOfYear);

    void deleteNote(Note note);

    void logout();
}
