package com.amazon.mp3.library.db;

import android.database.Cursor;
import android.database.CursorWrapper;

/* JADX INFO: loaded from: classes3.dex */
public class HeaderCursor extends CursorWrapper {
    private Cursor mCursor;
    private int mHeaderCount;
    private boolean mHideHeadersIfCursorEmpty;
    private int mPosition;

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean getWantsAllOnMoveCalls() {
        return true;
    }

    public HeaderCursor(Cursor cursor, int headerCount, boolean hideHeadersIfCursorEmpty) {
        super(cursor);
        if (cursor == null) {
            throw new IllegalArgumentException("HeaderCursor requires a valid, non-null cursor to wrap");
        }
        this.mCursor = cursor;
        this.mHeaderCount = headerCount;
        this.mHideHeadersIfCursorEmpty = hideHeadersIfCursorEmpty;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean move(int offset) {
        int i = this.mPosition + offset;
        if (i <= -1 || i > getCount()) {
            return false;
        }
        this.mPosition = i;
        int headerCount = getHeaderCount();
        int i2 = this.mPosition;
        if (i2 >= headerCount) {
            return this.mCursor.moveToPosition(((i2 - headerCount) - 1) + offset);
        }
        return true;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToFirst() {
        if (getCount() == 0) {
            return false;
        }
        this.mPosition = -1;
        this.mCursor.moveToFirst();
        return true;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToLast() {
        if (getCount() == 0) {
            return false;
        }
        this.mPosition = getCount() - 1;
        this.mCursor.moveToLast();
        return true;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToNext() {
        return move(1);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToPosition(int position) {
        if (position < -1 || position > getCount()) {
            return false;
        }
        this.mPosition = position;
        int headerCount = getHeaderCount();
        if (position >= headerCount) {
            this.mCursor.moveToPosition(position - headerCount);
            return true;
        }
        this.mCursor.moveToPosition(-1);
        return true;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public boolean moveToPrevious() {
        return move(-1);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getPosition() {
        return this.mPosition;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getCount() {
        return super.getCount() + getHeaderCount();
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public double getDouble(int columnIndex) {
        if (this.mPosition < getHeaderCount()) {
            return -1.0d;
        }
        return this.mCursor.getDouble(columnIndex);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public float getFloat(int columnIndex) {
        if (this.mPosition < getHeaderCount()) {
            return -1.0f;
        }
        return this.mCursor.getFloat(columnIndex);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getInt(int columnIndex) {
        if (this.mPosition < getHeaderCount()) {
            return -1;
        }
        return this.mCursor.getInt(columnIndex);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public long getLong(int columnIndex) {
        if (this.mPosition < getHeaderCount()) {
            return -1L;
        }
        return this.mCursor.getLong(columnIndex);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public short getShort(int columnIndex) {
        if (this.mPosition < getHeaderCount()) {
            return (short) -1;
        }
        return this.mCursor.getShort(columnIndex);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public String getString(int columnIndex) {
        return this.mPosition < getHeaderCount() ? "" : this.mCursor.getString(columnIndex);
    }

    private int getHeaderCount() {
        if (this.mHideHeadersIfCursorEmpty) {
            if (this.mCursor.getCount() == 0) {
                return 0;
            }
            return this.mHeaderCount;
        }
        return this.mHeaderCount;
    }
}
