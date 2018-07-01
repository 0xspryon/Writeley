package com.springfield.y.writeley.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.springfield.y.writeley.R;
import com.springfield.y.writeley.model.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

public class NotesAdapter extends RecyclerView.Adapter<NoteItemVh> {
    public static final int FIRST_NOTE_OF_DAY = 823;
    public static final int NOT_FIRST_NOTE_OF_DAY = 233;
    ArrayList<Note> notes = new ArrayList<>();
    OnItemClickListener listener;
    onDeleteListnener deleteListnener;
    private Date mDate;
    private Context context;

    public NotesAdapter(OnItemClickListener listener, onDeleteListnener deleteListnener, Context context) {
        this.listener = listener;
        this.context = context;
        this.deleteListnener = deleteListnener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            this.mDate = notes.get(position).getUdpatedAt();
            return FIRST_NOTE_OF_DAY;
        } else if (!datesEqual(notes.get(position).getUdpatedAt(), mDate)) {
            this.mDate = notes.get(position).getUdpatedAt();
            return FIRST_NOTE_OF_DAY;
        }
        return NOT_FIRST_NOTE_OF_DAY;
    }

    @NonNull
    @Override
    public NoteItemVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == FIRST_NOTE_OF_DAY) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_note_list_header, parent, false);
            return new NoteListHeaderItemVh(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_note, parent, false);
            return new NoteItemVh(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull NoteItemVh holder, int position) {
        String days[] = context.getResources().getStringArray(R.array.days_of_week);
        holder.bind(notes.get(position), listener, deleteListnener, days);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public boolean datesEqual(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        return sameDay;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public interface onDeleteListnener {
        void onDeleteClick(Note note);
    }
}
