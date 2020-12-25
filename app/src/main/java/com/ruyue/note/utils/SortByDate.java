package com.ruyue.note.utils;

import com.ruyue.note.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SortByDate {

    public static List<Note> sortByCreateDate(List<Note> noteList) {
        Collections.sort(noteList, (o1, o2) -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1;
            Date date2;
            int compareTo = 0;
            try {
                date1 = simpleDateFormat.parse(o1.getCreateDate());
                date2 = simpleDateFormat.parse(o2.getCreateDate());
                compareTo = date2.compareTo(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return compareTo;
        });
        return noteList;
    }

    public static List<Note> sortByModifyDate(List<Note> noteList) {
        Collections.sort(noteList, (o1, o2) -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1;
            Date date2;
            int compareTo = 0;
            try {
                date1 = simpleDateFormat.parse(o1.getModifyDate());
                date2 = simpleDateFormat.parse(o2.getModifyDate());
                compareTo = date2.compareTo(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return compareTo;
        });
        return noteList;
    }
}
