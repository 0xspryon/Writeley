package com.springfield.y.writeley.adapters;

import android.view.View;
import android.widget.TextView;

import com.springfield.y.writeley.R;
import com.springfield.y.writeley.model.Note;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

public class NoteListHeaderItemVh extends NoteItemVh {
    public TextView day, date;

    public NoteListHeaderItemVh(View view) {
        super(view);
        day = view.findViewById(R.id.text_note_item_header_day);
        date = view.findViewById(R.id.text_note_item_header_date);
    }

    public void bind(Note note,
                     NotesAdapter.OnItemClickListener listener,
                     NotesAdapter.onDeleteListnener deleteListnener,
                     final String[] days) {

        String dayText = days[note.getUdpatedAt().getDay()];
        day.setText(dayText);

        String dateShort = note.getUdpatedAt().getDate() + "/"
                + note.getUdpatedAt().getMonth()
                + "/" + note.getUdpatedAt().getYear();

        date.setText(dateShort);
        super.bind(note, listener, deleteListnener, days);
    }
}
