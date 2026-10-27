package com.android.providers.contacts;

import android.database.AbstractCursor;
import android.database.Cursor;

/* JADX INFO: loaded from: classes.dex */
public class ReorderingCursorWrapper extends AbstractCursor {
    private final Cursor mCursor;
    private final int[] mPositionMap;

    public ReorderingCursorWrapper(Cursor cursor, int[] iArr) {
        if (cursor.getCount() != iArr.length) {
            throw new IllegalArgumentException("Cursor and position map have different sizes.");
        }
        this.mCursor = cursor;
        this.mPositionMap = iArr;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        this.mCursor.close();
    }

    @Override // android.database.AbstractCursor, android.database.CrossProcessCursor
    public boolean onMove(int i, int i2) {
        return this.mCursor.moveToPosition(this.mPositionMap[i2]);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String[] getColumnNames() {
        return this.mCursor.getColumnNames();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getCount() {
        return this.mCursor.getCount();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public double getDouble(int i) {
        return this.mCursor.getDouble(i);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public float getFloat(int i) {
        return this.mCursor.getFloat(i);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getInt(int i) {
        return this.mCursor.getInt(i);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public long getLong(int i) {
        return this.mCursor.getLong(i);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public short getShort(int i) {
        return this.mCursor.getShort(i);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String getString(int i) {
        return this.mCursor.getString(i);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getType(int i) {
        return this.mCursor.getType(i);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public boolean isNull(int i) {
        return this.mCursor.isNull(i);
    }
}
