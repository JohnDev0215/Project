package com.amazon.mp3.library.db;

import android.database.AbstractCursor;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;

/* JADX INFO: loaded from: classes3.dex */
public class SafeMergeCursor extends AbstractCursor {
    private String[] mColumnNames;
    private Cursor mCursor;
    private Cursor[] mCursors;
    boolean mIsClosing = false;
    private DataSetObserver mObserver = new DataSetObserver() { // from class: com.amazon.mp3.library.db.SafeMergeCursor.1
        @Override // android.database.DataSetObserver
        public void onChanged() {
            ((AbstractCursor) SafeMergeCursor.this).mPos = -1;
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ((AbstractCursor) SafeMergeCursor.this).mPos = -1;
        }
    };

    public SafeMergeCursor(Cursor[] cursors) {
        int i = 0;
        for (Cursor cursor : cursors) {
            if (cursor != null) {
                i++;
            }
        }
        this.mCursors = new Cursor[i];
        int i2 = 0;
        for (Cursor cursor2 : cursors) {
            if (cursor2 != null) {
                this.mCursors[i2] = cursor2;
                cursor2.registerDataSetObserver(this.mObserver);
                i2++;
            }
        }
        this.mCursor = this.mCursors[0];
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getCount() {
        int length = this.mCursors.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            Cursor cursor = this.mCursors[i];
            if (cursor != null) {
                count += cursor.getCount();
            }
        }
        return count;
    }

    @Override // android.database.AbstractCursor, android.database.CrossProcessCursor
    public boolean onMove(int oldPosition, int newPosition) {
        this.mCursor = null;
        int length = this.mCursors.length;
        int i = 0;
        int count = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Cursor cursor = this.mCursors[i];
            if (cursor != null) {
                if (newPosition < cursor.getCount() + count) {
                    this.mCursor = this.mCursors[i];
                    setColumnNames();
                    break;
                }
                count += this.mCursors[i].getCount();
            }
            i++;
        }
        Cursor cursor2 = this.mCursor;
        if (cursor2 != null) {
            return cursor2.moveToPosition(newPosition - count);
        }
        return false;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String getString(int column) {
        return this.mCursor.getString(column);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public short getShort(int column) {
        return this.mCursor.getShort(column);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getInt(int column) {
        return this.mCursor.getInt(column);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public long getLong(int column) {
        return this.mCursor.getLong(column);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public float getFloat(int column) {
        return this.mCursor.getFloat(column);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public double getDouble(int column) {
        return this.mCursor.getDouble(column);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public boolean isNull(int column) {
        return this.mCursor.isNull(column);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public byte[] getBlob(int column) {
        return this.mCursor.getBlob(column);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String[] getColumnNames() {
        String[] strArr = this.mColumnNames;
        if (strArr == null || strArr.length == 0) {
            this.mColumnNames = new String[0];
            setColumnNames();
        }
        return this.mColumnNames;
    }

    private void setColumnNames() {
        this.mColumnNames = new String[0];
        String[] columnNames = this.mCursor.getColumnNames();
        if (columnNames == null || columnNames.length <= 0) {
            return;
        }
        String[] strArr = new String[columnNames.length];
        this.mColumnNames = strArr;
        System.arraycopy(columnNames, 0, strArr, 0, columnNames.length);
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void deactivate() {
        int length = this.mCursors.length;
        for (int i = 0; i < length; i++) {
            Cursor cursor = this.mCursors[i];
            if (cursor != null) {
                cursor.deactivate();
            }
        }
        super.deactivate();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public boolean isClosed() {
        return this.mIsClosing || super.isClosed();
    }

    @Override // android.database.AbstractCursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (!this.mIsClosing && !isClosed()) {
                this.mIsClosing = true;
                int length = this.mCursors.length;
                for (int i = 0; i < length; i++) {
                    Cursor cursor = this.mCursors[i];
                    if (cursor != null) {
                        cursor.unregisterDataSetObserver(this.mObserver);
                        this.mCursors[i].close();
                    }
                }
                super.close();
            }
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void registerContentObserver(ContentObserver observer) {
        int length = this.mCursors.length;
        for (int i = 0; i < length; i++) {
            Cursor cursor = this.mCursors[i];
            if (cursor != null) {
                cursor.registerContentObserver(observer);
            }
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void unregisterContentObserver(ContentObserver observer) {
        int length = this.mCursors.length;
        for (int i = 0; i < length; i++) {
            Cursor cursor = this.mCursors[i];
            if (cursor != null) {
                cursor.unregisterContentObserver(observer);
            }
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void registerDataSetObserver(DataSetObserver observer) {
        int length = this.mCursors.length;
        for (int i = 0; i < length; i++) {
            Cursor cursor = this.mCursors[i];
            if (cursor != null) {
                cursor.registerDataSetObserver(observer);
            }
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public void unregisterDataSetObserver(DataSetObserver observer) {
        int length = this.mCursors.length;
        for (int i = 0; i < length; i++) {
            Cursor cursor = this.mCursors[i];
            if (cursor != null) {
                cursor.unregisterDataSetObserver(observer);
            }
        }
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public boolean requery() {
        int length = this.mCursors.length;
        for (int i = 0; i < length; i++) {
            Cursor cursor = this.mCursors[i];
            if (cursor != null && !cursor.requery()) {
                return false;
            }
        }
        return true;
    }
}
