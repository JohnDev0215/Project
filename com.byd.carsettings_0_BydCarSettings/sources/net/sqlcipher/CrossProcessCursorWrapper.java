package net.sqlcipher;

import android.database.CrossProcessCursor;

/* JADX INFO: loaded from: classes3.dex */
public class CrossProcessCursorWrapper extends CursorWrapper implements CrossProcessCursor {
    public CrossProcessCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override // android.database.CrossProcessCursor
    public void fillWindow(int i, android.database.CursorWindow cursorWindow) {
        DatabaseUtils.cursorFillWindow(this, i, cursorWindow);
    }

    @Override // android.database.CrossProcessCursor
    public android.database.CursorWindow getWindow() {
        return null;
    }

    @Override // android.database.CrossProcessCursor
    public boolean onMove(int i, int i2) {
        return true;
    }
}
