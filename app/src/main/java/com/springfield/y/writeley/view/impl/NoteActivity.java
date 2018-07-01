package com.springfield.y.writeley.view.impl;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.springfield.y.writeley.R;
import com.springfield.y.writeley.model.Note;
import com.springfield.y.writeley.presenter.impl.NotePresenter;
import com.springfield.y.writeley.view.NoteActivityView;

import java.util.Date;

public class NoteActivity extends AppCompatActivity implements NoteActivityView {
    private EditText mTitleEt;
    private EditText mBodyEt;
    private NotePresenter mPresenter;
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBodyEt = findViewById(R.id.text_body_journal);
        mTitleEt = findViewById(R.id.text_note_title);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

        this.mPresenter = new NotePresenter(this);
        this.mPresenter.takeOverExecution();

        if (getIntent().hasExtra(JournalActivity.INTENT_TAG)) {
            mNote = ((Note) getIntent().getSerializableExtra(JournalActivity.INTENT_TAG));
            setNote(mNote);
        }
    }

    @Override
    public boolean determineIfEditMode() {
        return false;
    }

    @Override
    public Note getNote() {
        return null;
    }

    @Override
    public void setNote(Note note) {
        this.mTitleEt.setText(note.getTitle());
        this.mBodyEt.setText(note.getBody());
    }

    @Override
    public void saveNote() {
        String title = mTitleEt.getText().toString().trim();
        String body = mBodyEt.getText().toString().trim();
        if (title.length() != 0 && body.length() != 0) {
            if (mNote == null) {
                Date date = new Date();
                Note note = new Note(title, body, date);
                mPresenter.saveNote(note);
            } else { // We're updating
                mNote.setBody(body);
                mNote.setTitle(title);
                mPresenter.updateNote(mNote);
            }
        }
    }


}
