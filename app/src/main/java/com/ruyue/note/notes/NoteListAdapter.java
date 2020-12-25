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
import com.ruyue.note.utils.Const;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {

    private List<Note> noteList;
    private Context context;
    private AlertDialog deleteConfirmDialog;
    private Note note;

    public NoteListAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;

        deleteConfirmDialog = new AlertDialog.Builder(context)
                .setTitle("Tips")
                .setMessage("Confirm delete this note?")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    new Thread(() -> {
                    }).start();
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> deleteConfirmDialog.cancel())
                .create();
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
        NoteListViewHolder holder = new NoteListViewHolder(view);

        holder.noteView.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            note = noteList.get(position);

            Intent intent = new Intent(context, DetailPageActivity.class);
            intent.putExtra(Const.OPERATION, Const.OPERATION_MODIFY);
            intent.putExtra("note", new Gson().toJson(note));
            context.startActivity(intent);
        });

        holder.noteView.setOnLongClickListener(v -> {
            int position = holder.getAdapterPosition();
            note = noteList.get(position);
            deleteConfirmDialog.show();
            return false;
        });

        return holder;
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
