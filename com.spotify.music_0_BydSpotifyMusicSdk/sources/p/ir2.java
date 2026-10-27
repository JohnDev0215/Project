package p;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ir2 implements vh2, h62 {
    public final /* synthetic */ long c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;

    public /* synthetic */ ir2(long j, Object obj, Object obj2) {
        this.e = obj;
        this.k = obj2;
        this.c = j;
    }

    @Override // p.h62
    public final Object apply(Object obj) {
        String str = (String) this.e;
        LogEventDropped$Reason logEventDropped$Reason = (LogEventDropped$Reason) this.k;
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
        ae0 ae0Var = j62.n;
        boolean zBooleanValue = ((Boolean) j62.o0(sQLiteDatabase.rawQuery("SELECT 1 FROM log_event_dropped WHERE log_source = ? AND reason = ?", new String[]{str, Integer.toString(logEventDropped$Reason.a())}), new iy2(8))).booleanValue();
        long j = this.c;
        if (zBooleanValue) {
            sQLiteDatabase.execSQL("UPDATE log_event_dropped SET events_dropped_count = events_dropped_count + " + j + " WHERE log_source = ? AND reason = ?", new String[]{str, Integer.toString(logEventDropped$Reason.a())});
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("log_source", str);
            contentValues.put("reason", Integer.valueOf(logEventDropped$Reason.a()));
            contentValues.put("events_dropped_count", Long.valueOf(j));
            sQLiteDatabase.insert("log_event_dropped", null, contentValues);
        }
        return null;
    }

    @Override // p.vh2
    public final Object b() {
        jr2 jr2Var = (jr2) this.e;
        wi wiVar = (wi) this.k;
        long jA = ((lr2) jr2Var.g).a() + this.c;
        j62 j62Var = (j62) jr2Var.c;
        j62Var.getClass();
        j62Var.i(new e62(jA, wiVar));
        return null;
    }
}
