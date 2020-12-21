package com.ruyue.note.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ruyue.note.R;
import com.ruyue.note.databinding.NoteItemBinding;
import com.ruyue.note.model.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {

    private List<Note> noteList;
    private Context context;

    public NoteListAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }

    static class NoteListViewHolder extends RecyclerView.ViewHolder {
        private NoteItemBinding noteItemBinding;

        public NoteListViewHolder(@NonNull View itemView) {
            super(itemView);
            noteItemBinding = DataBindingUtil.bind(itemView);
        }
    }

    @NonNull
    @Override
    public NoteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.noteItemBinding.title.setText(note.getTitle());
        holder.noteItemBinding.content.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
