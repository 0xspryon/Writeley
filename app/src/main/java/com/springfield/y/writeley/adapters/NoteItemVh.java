package com.springfield.y.writeley.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.springfield.y.writeley.R;
import com.springfield.y.writeley.model.Note;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

public class NoteItemVh extends RecyclerView.ViewHolder {
    private final View noteItem;
    public TextView title, bodyShort, time;
    public ImageView delete;


    public NoteItemVh(View view) {
        super(view);
        noteItem = view;
        title = view.findViewById(R.id.text_note_title);
        bodyShort = view.findViewById(R.id.text_note_content_short);
        time = view.findViewById(R.id.text_note_edit_time);
        delete = view.findViewById(R.id.image_delete);
    }

    public void bind(final Note note,
                     final NotesAdapter.OnItemClickListener listener,
                     final NotesAdapter.onDeleteListnener deleteListnener,
                     final String[] days
    ) {
        title.setText(note.getTitle());
        bodyShort.setText(note.getBody() + "...");
        if (note.getBody().length() > 20) {
            bodyShort.setText(note.getBody().substring(0, 20) + "...");
        }
        time.setText(getTime(note.getUdpatedAt()));
        noteItem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(note);
                    }
                }
        );

        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteListnener.onDeleteClick(note);
                    }
                }
        );
    }

    public String getTime(Date date) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        String hours = String.valueOf(cal1.get(Calendar.HOUR_OF_DAY));
        String minutes = String.valueOf(cal1.get(Calendar.MINUTE));
        return hours + ":" + minutes;
    }
}
