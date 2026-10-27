package com.amazon.mp3.library.db;

import android.database.Cursor;
import android.database.CursorWrapper;

/* JADX INFO: loaded from: classes3.dex */
public class ContentTypeAppendedCursor extends CursorWrapper {
    private final String mColumnName;
    private String[] mColumnNames;
    private final String mContentType;
    private int mContentTypeIndex;

    public ContentTypeAppendedCursor(Cursor c, String columnName, String contentType) {
        super(c);
        this.mContentType = contentType;
        this.mColumnName = columnName;
        this.mContentTypeIndex = c.getColumnCount();
        initColumnNameArray(c);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public String[] getColumnNames() {
        return this.mColumnNames;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnCount() {
        return super.getColumnCount() + 1;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnIndex(String columnName) {
        if (this.mColumnName.equals(columnName)) {
            return this.mContentTypeIndex;
        }
        return super.getColumnIndex(columnName);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
        if (this.mColumnName.equals(columnName)) {
            return this.mContentTypeIndex;
        }
        return super.getColumnIndexOrThrow(columnName);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public String getColumnName(int columnIndex) {
        if (columnIndex == this.mContentTypeIndex) {
            return this.mColumnName;
        }
        return super.getColumnName(columnIndex);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public String getString(int columnIndex) {
        if (columnIndex == this.mContentTypeIndex) {
            return this.mContentType;
        }
        return super.getString(columnIndex);
    }

    private void initColumnNameArray(Cursor c) {
        String[] columnNames = c.getColumnNames();
        int length = columnNames.length;
        String[] strArr = new String[columnNames.length + 1];
        this.mColumnNames = strArr;
        System.arraycopy(columnNames, 0, strArr, 0, length);
        this.mColumnNames[length] = this.mColumnName;
    }
}
