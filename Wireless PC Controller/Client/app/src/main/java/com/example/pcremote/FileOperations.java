package com.example.pcremote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class FileOperations {

    private DataBaseWrapper dbHelper;
    private String[] File_TABLE_COLUMNS = { DataBaseWrapper.File_ID, DataBaseWrapper.File_NAME };
    private SQLiteDatabase database;

    public FileOperations(Context context) {
        dbHelper = new DataBaseWrapper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public file addFile(String name) {

        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.File_NAME, name);

        long studId = database.insert(DataBaseWrapper.File, null, values);

        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapper.File,
                File_TABLE_COLUMNS, DataBaseWrapper.File_ID + " = "
                        + studId, null, null, null, null);

        cursor.moveToFirst();

        file newComment = parseFile(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteFile(file comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseWrapper.File, DataBaseWrapper.File_ID
                + " = " + id, null);
    }

    public List getAllFiles() {
        List files = new ArrayList();

        Cursor cursor = database.query(DataBaseWrapper.File,
                File_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            file ff = parseFile(cursor);
            files.add(ff);
            cursor.moveToNext();
        }

        cursor.close();
        return files;
    }

    private file parseFile(Cursor cursor) {
        file ff = new file();
        ff.setId((cursor.getInt(0)));
        ff.setName(cursor.getString(1));
        return ff;
    }
}
