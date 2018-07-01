package com.springfield.y.writeley.model;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

public interface ModelRepositoryInterface {
    void saveNotes(Note... notes);

    void deleteNotes(Note... notes);

    void updateNotes(Note... notes);
//    ArrayList<Note> setupViewModel();

    void deleteAllNotes();

//    LiveData<List<Note>> setupViewModel(int monthOfYear);
}
