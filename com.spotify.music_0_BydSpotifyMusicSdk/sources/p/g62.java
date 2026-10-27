package p;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g62 implements h62 {
    public final /* synthetic */ int c;
    public final /* synthetic */ j62 e;

    public /* synthetic */ g62(j62 j62Var, int i) {
        this.c = i;
        this.e = j62Var;
    }

    @Override // p.h62
    public final Object apply(Object obj) {
        int i = this.c;
        j62 j62Var = this.e;
        switch (i) {
            case 0:
                SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
                j62Var.getClass();
                sQLiteDatabase.compileStatement("DELETE FROM log_event_dropped").execute();
                sQLiteDatabase.compileStatement("UPDATE global_log_event_state SET last_metrics_upload_ms=" + ((lr2) j62Var.e).a()).execute();
                break;
            case 1:
                Cursor cursor = (Cursor) obj;
                j62Var.getClass();
                while (cursor.moveToNext()) {
                    j62Var.i(new ir2(cursor.getInt(0), cursor.getString(1), LogEventDropped$Reason.MESSAGE_TOO_OLD));
                }
                break;
            default:
                Cursor cursor2 = (Cursor) obj;
                j62Var.getClass();
                while (cursor2.moveToNext()) {
                    j62Var.i(new ir2(cursor2.getInt(0), cursor2.getString(1), LogEventDropped$Reason.MAX_RETRIES_REACHED));
                }
                break;
        }
        return null;
    }
}
