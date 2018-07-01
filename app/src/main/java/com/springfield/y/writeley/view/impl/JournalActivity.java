package com.springfield.y.writeley.view.impl;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.springfield.y.writeley.R;
import com.springfield.y.writeley.adapters.MonthSelectorAdapter;
import com.springfield.y.writeley.adapters.NotesAdapter;
import com.springfield.y.writeley.model.Note;
import com.springfield.y.writeley.model.room.WriteleyDatabase;
import com.springfield.y.writeley.presenter.impl.JournalPresenter;
import com.springfield.y.writeley.view.JournalActivityView;
import com.springfield.y.writeley.view.viewModel.JournalViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JournalActivity extends AppCompatActivity implements JournalActivityView, NotesAdapter.OnItemClickListener, NotesAdapter.onDeleteListnener {
    public static final String INTENT_TAG = "NOTE'";
    JournalPresenter presenter;
    private RecyclerView mMonthRecyclerView, mNotesRecyclerView;
    private NotesAdapter mNotesAdapter;
    private String[] mMonths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMonthRecyclerView = findViewById(R.id.recycler_view_month);
        mNotesRecyclerView = findViewById(R.id.recycler_view_notes);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNoteActivity();
            }
        });

        mMonths = getResources().getStringArray(R.array.months_of_year);
        MonthSelectorAdapter monthAdapter = new MonthSelectorAdapter(JournalActivity.this);
        mNotesAdapter = new NotesAdapter(JournalActivity.this, JournalActivity.this, JournalActivity.this);

        mMonthRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMonthRecyclerView.setAdapter(monthAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mMonthRecyclerView);

        mNotesRecyclerView.setAdapter(mNotesAdapter);
        mNotesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMonthRecyclerView.setOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            int position = ((LinearLayoutManager) mMonthRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                            if (!(position < 0 || position > 12)) {
                                Toast.makeText(JournalActivity.this, mMonths[position], Toast.LENGTH_SHORT).show();
                                setupViewModel(position);
                            }
                        }
                    }

                }
        );
        presenter = new JournalPresenter(JournalActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.takeOverExecution();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Date today = new Date();
        mMonthRecyclerView.scrollToPosition(today.getMonth());
        setupViewModel(today.getMonth());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_journal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.logout:
                presenter.logout();
                break;
        }
        return true;
    }

    /**
     * initializes a viewmodel and sets it up with it's various liveData objects for showing journal notes
     */
    @Override
    public void setupViewModel(int monthOfYear) {
        JournalViewModel viewModel = ViewModelProviders.of(JournalActivity.this).get(JournalViewModel.class);
        viewModel.getNotes().observe(JournalActivity.this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
//                presenter.setNotes(notes);
                setNotesForMonth(((ArrayList<Note>) notes));
            }
        });

    }

    /**
     * gets all the journal notes found within an interval of a set month and observes on changes to show in recyclerview
     */
    public void getNotesForMonth(int monthOfYear) {
        // TODO: update this method so as to take into account the year and not hard code it
        Date lowerInterval = new Date();
        int month = lowerInterval.getMonth();
        lowerInterval = new Date(2018, month, 1);
        Date upperInterval = new Date(2018, monthOfYear + 1, 1);
        WriteleyDatabase mDb = WriteleyDatabase.getInstance(JournalActivity.this);
        LiveData<List<Note>> notes$ = mDb.noteDAO().getNotesInInterval(lowerInterval.getTime(), upperInterval.getTime());
        notes$.observe(JournalActivity.this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                setNotesForMonth(((ArrayList<Note>) notes));
            }
        });

    }


    /**
     * sets not of month to local adapter to be called by presenter
     */
    @Override
    public void setNotesForMonth(ArrayList<Note> notes) {
        mNotesAdapter.setNotes(notes);
    }

    /**
     * getter for mMonths
     */
    @Override
    public String[] getMonths() {
        return mMonths;
    }

    public void goToNoteActivity(Note note) {
        Intent intent = new Intent(JournalActivity.this, NoteActivity.class);
        intent.putExtra(INTENT_TAG, note);
        startActivity(intent);
    }

    public void goToNoteActivity() {
        Intent intent = new Intent(JournalActivity.this, NoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(Note note) {
        goToNoteActivity(note);
    }

    @Override
    public void onDeleteClick(Note note) {
        presenter.deleteNote(note);
//        mNotesAdapter.deleteNote(note);
    }

}
