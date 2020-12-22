package com.ruyue.note.model;

import android.annotation.SuppressLint;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.ruyue.note.utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity(tableName = "notes")
public class Note implements Comparable<Note>{
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String content;
    private String createDate;
    private String modifyDate;

    public Note(long id, String title, String content, String createDate, String modifyDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    @Ignore
    public Note(String title, String content, String createDate, String modifyDate) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                '}';
    }

    @Override
    public int compareTo(Note note) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1;
        Date date2;
        int compareTo = 0;
        try {
            date1 = simpleDateFormat.parse(this.getModifyDate());
            date2 = simpleDateFormat.parse(note.getModifyDate());
            compareTo = date1.compareTo(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return compareTo;
    }
}
