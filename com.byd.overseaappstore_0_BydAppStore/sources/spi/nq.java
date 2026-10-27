package spi;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: loaded from: classes.dex */
public final class nq implements mq {
    public final RoomDatabase a;
    public final b b;
    public final c c;

    public class a extends EntityInsertionAdapter<oq> {
        public a(nq nqVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        public final void bind(SupportSQLiteStatement supportSQLiteStatement, oq oqVar) {
            oq oqVar2 = oqVar;
            String str = oqVar2.a;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = oqVar2.b;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            String str3 = oqVar2.c;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str3);
            }
            supportSQLiteStatement.bindLong(4, oqVar2.d);
            String str4 = oqVar2.e;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, str4);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "INSERT OR REPLACE INTO `SoftwareExtraLocal` (`packageName`,`primePackageName`,`signature`,`versionCode`,`versionName`) VALUES (?,?,?,?,?)";
        }
    }

    public class b extends SharedSQLiteStatement {
        public b(nq nqVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "DELETE from SoftwareExtraLocal Where packageName =?";
        }
    }

    public class c extends SharedSQLiteStatement {
        public c(nq nqVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "DELETE from SoftwareExtraLocal Where primePackageName =?";
        }
    }

    public class d implements Callable<Unit> {
        public final /* synthetic */ String a;

        public d(String str) {
            this.a = str;
        }

        @Override // java.util.concurrent.Callable
        public final Unit call() throws Exception {
            SupportSQLiteStatement supportSQLiteStatementAcquire = nq.this.b.acquire();
            String str = this.a;
            if (str == null) {
                supportSQLiteStatementAcquire.bindNull(1);
            } else {
                supportSQLiteStatementAcquire.bindString(1, str);
            }
            nq.this.a.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                nq.this.a.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                nq.this.a.endTransaction();
                nq.this.b.release(supportSQLiteStatementAcquire);
            }
        }
    }

    public class e implements Callable<Unit> {
        public final /* synthetic */ String a;

        public e(String str) {
            this.a = str;
        }

        @Override // java.util.concurrent.Callable
        public final Unit call() throws Exception {
            SupportSQLiteStatement supportSQLiteStatementAcquire = nq.this.c.acquire();
            String str = this.a;
            if (str == null) {
                supportSQLiteStatementAcquire.bindNull(1);
            } else {
                supportSQLiteStatementAcquire.bindString(1, str);
            }
            nq.this.a.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                nq.this.a.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                nq.this.a.endTransaction();
                nq.this.c.release(supportSQLiteStatementAcquire);
            }
        }
    }

    public class f implements Callable<oq> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public f(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        public final oq call() throws Exception {
            oq oqVar = null;
            Cursor cursorQuery = DBUtil.query(nq.this.a, this.a, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "primePackageName");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "signature");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
                if (cursorQuery.moveToFirst()) {
                    oqVar = new oq(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                }
                return oqVar;
            } finally {
                cursorQuery.close();
                this.a.release();
            }
        }
    }

    public nq(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        new a(this, roomDatabase);
        this.b = new b(this, roomDatabase);
        this.c = new c(this, roomDatabase);
    }

    @Override // spi.mq
    public final Object a(String str, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.a, true, new d(str), continuation);
    }

    @Override // spi.mq
    public final Object b(String str, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.a, true, new e(str), continuation);
    }

    @Override // spi.mq
    public final Object g(String str, Continuation<? super oq> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareExtraLocal WHERE packageName = ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return CoroutinesRoom.execute(this.a, false, DBUtil.createCancellationSignal(), new f(roomSQLiteQueryAcquire), continuation);
    }
}
