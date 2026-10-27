package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.widget.AlphabetIndexer;
import android.widget.CursorAdapter;
import android.widget.SectionIndexer;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CursorListAdapter extends CursorAdapter implements SectionIndexer {
    protected final Context mContext;
    protected AlphabetIndexer mIndexer;
    protected int mNameIndex;

    public abstract void setupNewCursor(Cursor c);

    public CursorListAdapter(Context context, Cursor cursor, boolean autoRequery) {
        super(context, cursor, autoRequery);
        this.mContext = context;
    }

    @Override // android.widget.SectionIndexer
    public Object[] getSections() {
        AlphabetIndexer alphabetIndexer = this.mIndexer;
        return alphabetIndexer != null ? alphabetIndexer.getSections() : new Object[0];
    }

    @Override // android.widget.SectionIndexer
    public int getPositionForSection(int section) {
        AlphabetIndexer alphabetIndexer;
        if (!isCursorReady() || (alphabetIndexer = this.mIndexer) == null) {
            return 0;
        }
        return alphabetIndexer.getPositionForSection(section);
    }

    @Override // android.widget.SectionIndexer
    public int getSectionForPosition(int position) {
        if (!isCursorReady() || this.mIndexer == null || getStringAtCursorPosition(position) == null) {
            return 0;
        }
        return this.mIndexer.getSectionForPosition(position);
    }

    @Override // android.widget.CursorAdapter
    public void changeCursor(Cursor c) {
        super.changeCursor(c);
        setupNewCursor(c);
    }

    @Override // android.widget.CursorAdapter
    public Cursor swapCursor(final Cursor newCursor) {
        Cursor cursorSwapCursor = super.swapCursor(newCursor);
        setupNewCursor(newCursor);
        return cursorSwapCursor;
    }

    public boolean isCursorReady() {
        Cursor cursor = getCursor();
        return (cursor == null || cursor.isClosed()) ? false : true;
    }

    protected void checkCreateAlphabetIndexer(Cursor c) {
        if (c != null && c.getCount() > 25) {
            this.mIndexer = new AlphabetIndexer(c, this.mNameIndex, this.mContext.getString(R.string.dmusic_library_songs_alphabet));
        } else {
            this.mIndexer = null;
        }
    }

    public String getStringAtCursorPosition(int position) {
        Cursor cursor = getCursor();
        if (cursor == null) {
            return null;
        }
        int position2 = cursor.getPosition();
        cursor.moveToPosition(position);
        String string = cursor.getString(this.mNameIndex);
        cursor.moveToPosition(position2);
        return string;
    }
}
