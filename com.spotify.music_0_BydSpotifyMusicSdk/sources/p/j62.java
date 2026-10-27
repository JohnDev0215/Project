package p;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.SystemClock;
import android.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class j62 implements jk0, wh2, rt {
    public static final ae0 n = new ae0("proto");
    public final n72 c;
    public final yt e;
    public final yt k;
    public final bi l;
    public final xy1 m;

    public j62(yt ytVar, yt ytVar2, bi biVar, n72 n72Var, xy1 xy1Var) {
        this.c = n72Var;
        this.e = ytVar;
        this.k = ytVar2;
        this.l = biVar;
        this.m = xy1Var;
    }

    public static String X(Iterable iterable) {
        StringBuilder sb = new StringBuilder("(");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            sb.append(((ji) it.next()).a);
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        sb.append(')');
        return sb.toString();
    }

    public static Long c(SQLiteDatabase sQLiteDatabase, wi wiVar) {
        StringBuilder sb = new StringBuilder("backend_name = ? and priority = ?");
        ArrayList arrayList = new ArrayList(Arrays.asList(wiVar.a, String.valueOf(gx1.a(wiVar.c))));
        byte[] bArr = wiVar.b;
        if (bArr != null) {
            sb.append(" and extras = ?");
            arrayList.add(Base64.encodeToString(bArr, 0));
        } else {
            sb.append(" and extras is null");
        }
        return (Long) o0(sQLiteDatabase.query("transport_contexts", new String[]{"_id"}, sb.toString(), (String[]) arrayList.toArray(new String[0]), null, null, null), new iy2(11));
    }

    public static Object o0(Cursor cursor, h62 h62Var) {
        try {
            return h62Var.apply(cursor);
        } finally {
            cursor.close();
        }
    }

    public final Object E(af2 af2Var, iy2 iy2Var) {
        lr2 lr2Var = (lr2) this.k;
        long jA = lr2Var.a();
        while (true) {
            try {
                int i = af2Var.c;
                Object obj = af2Var.e;
                switch (i) {
                    case 7:
                        return ((n72) obj).getWritableDatabase();
                    default:
                        ((SQLiteDatabase) obj).beginTransaction();
                        return null;
                }
            } catch (SQLiteDatabaseLockedException e) {
                if (lr2Var.a() >= ((long) this.l.c) + jA) {
                    return iy2Var.apply(e);
                }
                SystemClock.sleep(50L);
            }
        }
    }

    public final Object J(vh2 vh2Var) {
        SQLiteDatabase sQLiteDatabaseB = b();
        E(new af2(8, sQLiteDatabaseB), new iy2(6));
        try {
            Object objB = vh2Var.b();
            sQLiteDatabaseB.setTransactionSuccessful();
            return objB;
        } finally {
            sQLiteDatabaseB.endTransaction();
        }
    }

    public final SQLiteDatabase b() {
        n72 n72Var = this.c;
        Objects.requireNonNull(n72Var);
        return (SQLiteDatabase) E(new af2(7, n72Var), new iy2(4));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.c.close();
    }

    public final Object i(h62 h62Var) {
        SQLiteDatabase sQLiteDatabaseB = b();
        sQLiteDatabaseB.beginTransaction();
        try {
            Object objApply = h62Var.apply(sQLiteDatabaseB);
            sQLiteDatabaseB.setTransactionSuccessful();
            return objApply;
        } finally {
            sQLiteDatabaseB.endTransaction();
        }
    }

    public final ArrayList q(SQLiteDatabase sQLiteDatabase, wi wiVar, int i) {
        ArrayList arrayList = new ArrayList();
        Long lC = c(sQLiteDatabase, wiVar);
        if (lC == null) {
            return arrayList;
        }
        o0(sQLiteDatabase.query("events", new String[]{"_id", "transport_name", "timestamp_ms", "uptime_ms", "payload_encoding", "payload", "code", "inline"}, "context_id = ?", new String[]{lC.toString()}, null, null, null, String.valueOf(i)), new r70(this, (Object) arrayList, wiVar, 2));
        return arrayList;
    }
}
