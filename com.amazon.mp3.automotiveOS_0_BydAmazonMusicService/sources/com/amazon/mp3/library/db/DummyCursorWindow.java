package com.amazon.mp3.library.db;

import android.database.CharArrayBuffer;
import android.database.CursorWindow;
import android.os.Parcel;

/* JADX INFO: loaded from: classes3.dex */
public class DummyCursorWindow extends CursorWindow {
    int mRowCount;
    int mStartPos;

    @Override // android.database.sqlite.SQLiteClosable
    public void acquireReference() {
    }

    @Override // android.database.sqlite.SQLiteClosable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // android.database.CursorWindow
    public void copyStringToBuffer(int row, int col, CharArrayBuffer buffer) {
    }

    @Override // android.database.CursorWindow
    public byte[] getBlob(int row, int col) {
        return new byte[0];
    }

    @Override // android.database.CursorWindow
    public double getDouble(int row, int col) {
        return 0.0d;
    }

    @Override // android.database.CursorWindow
    public float getFloat(int row, int col) {
        return 0.0f;
    }

    @Override // android.database.CursorWindow
    public int getInt(int row, int col) {
        return 0;
    }

    @Override // android.database.CursorWindow
    public long getLong(int row, int col) {
        return 0L;
    }

    @Override // android.database.CursorWindow
    public short getShort(int row, int col) {
        return (short) 0;
    }

    @Override // android.database.CursorWindow
    public String getString(int row, int col) {
        return "";
    }

    @Override // android.database.CursorWindow
    public int getType(int row, int col) {
        return 3;
    }

    @Override // android.database.CursorWindow
    public boolean isBlob(int row, int col) {
        return true;
    }

    @Override // android.database.CursorWindow
    public boolean isFloat(int row, int col) {
        return true;
    }

    @Override // android.database.CursorWindow
    public boolean isLong(int row, int col) {
        return true;
    }

    @Override // android.database.CursorWindow
    public boolean isNull(int row, int col) {
        return true;
    }

    @Override // android.database.CursorWindow
    public boolean isString(int row, int col) {
        return true;
    }

    @Override // android.database.CursorWindow, android.database.sqlite.SQLiteClosable
    protected void onAllReferencesReleased() {
    }

    @Override // android.database.sqlite.SQLiteClosable
    protected void onAllReferencesReleasedFromContainer() {
    }

    @Override // android.database.CursorWindow
    public boolean putBlob(byte[] value, int row, int col) {
        return true;
    }

    @Override // android.database.CursorWindow
    public boolean putDouble(double value, int row, int col) {
        return true;
    }

    @Override // android.database.CursorWindow
    public boolean putLong(long value, int row, int col) {
        return true;
    }

    @Override // android.database.CursorWindow
    public boolean putNull(int row, int col) {
        return true;
    }

    @Override // android.database.CursorWindow
    public boolean putString(String value, int row, int col) {
        return true;
    }

    @Override // android.database.sqlite.SQLiteClosable
    public void releaseReference() {
    }

    @Override // android.database.sqlite.SQLiteClosable
    public void releaseReferenceFromContainer() {
    }

    @Override // android.database.CursorWindow
    public boolean setNumColumns(int columnNum) {
        return true;
    }

    public DummyCursorWindow(int rowCount) {
        super(true);
        this.mRowCount = rowCount;
    }

    @Override // android.database.CursorWindow
    public boolean allocRow() {
        this.mRowCount++;
        return true;
    }

    public void setNumRows(int numRows) {
        this.mRowCount = numRows;
    }

    @Override // android.database.CursorWindow
    public void clear() {
        this.mRowCount = 0;
    }

    @Override // android.database.CursorWindow
    public void freeLastRow() {
        this.mRowCount--;
    }

    @Override // android.database.CursorWindow
    public int getNumRows() {
        return this.mRowCount;
    }

    @Override // android.database.CursorWindow
    public int getStartPosition() {
        return this.mStartPos;
    }

    @Override // android.database.CursorWindow
    public void setStartPosition(int pos) {
        this.mStartPos = pos;
    }

    @Override // android.database.CursorWindow, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }
}
