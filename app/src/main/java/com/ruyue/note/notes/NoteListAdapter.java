package com.ruyue.note.notes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ruyue.note.R;
import com.ruyue.note.databinding.NoteItemBinding;
import com.ruyue.note.detailPage.DetailPageActivity;
import com.ruyue.note.model.Note;
import com.ruyue.note.repository.LocalDataSource;
import com.ruyue.note.utils.Const;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {

    private List<Note> noteList;
    private Context context;
    private OnItemClickListener onItemClickListener;
        private OnLongItemClickListener onLongItemClickListener;

    public NoteListAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnLongItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLongItemClickListener(OnLongItemClickListener onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }


    static class NoteListViewHolder extends RecyclerView.ViewHolder {
        private View noteView;
        private NoteItemBinding noteItemBinding;

        public NoteListViewHolder(@NonNull View itemView) {
            super(itemView);
            noteView = itemView;
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

        if (onItemClickListener != null) {
            holder.noteView.setOnClickListener(v -> onItemClickListener.onItemClick(v, position));
        }

        if(onLongItemClickListener != null) {
            holder.noteView.setOnLongClickListener(v -> {
                onLongItemClickListener.onItemClick(v, position);
                return false;
            });
        }
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
