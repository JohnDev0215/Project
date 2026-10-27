package p;

import android.database.sqlite.SQLiteStatement;

/* JADX INFO: loaded from: classes.dex */
public final class ky0 extends hz1 implements th2 {
    public final SQLiteStatement k;

    public ky0(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.k = sQLiteStatement;
    }

    @Override // p.th2
    public final int F() {
        return this.k.executeUpdateDelete();
    }

    @Override // p.th2
    public final long v0() {
        return this.k.executeInsert();
    }
}
