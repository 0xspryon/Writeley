package com.springfield.y.writeley.model.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.springfield.y.writeley.model.Note;

import java.util.List;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

@Dao
public interface NoteDAO {

    @Query("SELECT * FROM notes ORDER BY updated_at")
    LiveData<List<Note>> loadAllNotes();

    @Insert
    void insertNote(Note note);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM notes")
    void deleteAllNotes();

    @Query("SELECT * FROM notes WHERE updated_at BETWEEN :interval1 AND  :interval2")
    LiveData<List<Note>> getNotesInInterval(Long interval1, Long interval2);
}
