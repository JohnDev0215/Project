package p;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.Priority;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e62 implements h62 {
    public final /* synthetic */ int c = 1;
    public final /* synthetic */ long e;
    public final /* synthetic */ Object k;

    public /* synthetic */ e62(long j, wi wiVar) {
        this.e = j;
        this.k = wiVar;
    }

    @Override // p.h62
    public final Object apply(Object obj) {
        int i = this.c;
        long j = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                j62 j62Var = (j62) obj2;
                SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
                j62Var.getClass();
                String[] strArr = {String.valueOf(j)};
                j62.o0(sQLiteDatabase.rawQuery("SELECT COUNT(*), transport_name FROM events WHERE timestamp_ms < ? GROUP BY transport_name", strArr), new g62(j62Var, 1));
                return Integer.valueOf(sQLiteDatabase.delete("events", "timestamp_ms < ?", strArr));
            default:
                wi wiVar = (wi) obj2;
                SQLiteDatabase sQLiteDatabase2 = (SQLiteDatabase) obj;
                ContentValues contentValues = new ContentValues();
                contentValues.put("next_request_ms", Long.valueOf(j));
                String str = wiVar.a;
                Priority priority = wiVar.c;
                if (sQLiteDatabase2.update("transport_contexts", contentValues, "backend_name = ? and priority = ?", new String[]{str, String.valueOf(gx1.a(priority))}) < 1) {
                    contentValues.put("backend_name", wiVar.a);
                    contentValues.put("priority", Integer.valueOf(gx1.a(priority)));
                    sQLiteDatabase2.insert("transport_contexts", null, contentValues);
                }
                return null;
        }
    }

    public /* synthetic */ e62(j62 j62Var, long j) {
        this.k = j62Var;
        this.e = j;
    }
}
