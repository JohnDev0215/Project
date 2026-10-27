package spi;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: loaded from: classes.dex */
public final class ur implements tr {
    public final RoomDatabase a;
    public final EntityInsertionAdapter<pq> b;
    public final EntityInsertionAdapter<oq> c;
    public final EntityDeletionOrUpdateAdapter<pq> d;
    public final a0 e;
    public final b0 f;
    public final c0 g;

    public class a extends SharedSQLiteStatement {
        public a(ur urVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "DELETE from SoftwareLocal Where packageName =?";
        }
    }

    public class a0 extends SharedSQLiteStatement {
        public a0(ur urVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "UPDATE SoftwareLocal SET state=? WHERE packageName = ?";
        }
    }

    public class b implements Callable<Unit> {
        public final /* synthetic */ pq a;

        public b(pq pqVar) {
            this.a = pqVar;
        }

        @Override // java.util.concurrent.Callable
        public final Unit call() throws Exception {
            ur.this.a.beginTransaction();
            try {
                ur.this.b.insert(this.a);
                ur.this.a.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                ur.this.a.endTransaction();
            }
        }
    }

    public class b0 extends SharedSQLiteStatement {
        public b0(ur urVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "UPDATE SoftwareLocal SET state=? ,downloadUrl=? WHERE packageName = ?";
        }
    }

    public class c implements Callable<Unit> {
        public final /* synthetic */ oq a;

        public c(oq oqVar) {
            this.a = oqVar;
        }

        @Override // java.util.concurrent.Callable
        public final Unit call() throws Exception {
            ur.this.a.beginTransaction();
            try {
                ur.this.c.insert(this.a);
                ur.this.a.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                ur.this.a.endTransaction();
            }
        }
    }

    public class c0 extends SharedSQLiteStatement {
        public c0(ur urVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "UPDATE SoftwareLocal SET state=? ,versionCode=? WHERE packageName = ?";
        }
    }

    public class d implements Callable<Unit> {
        public final /* synthetic */ List a;

        public d(List list) {
            this.a = list;
        }

        @Override // java.util.concurrent.Callable
        public final Unit call() throws Exception {
            ur.this.a.beginTransaction();
            try {
                ur.this.c.insert(this.a);
                ur.this.a.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                ur.this.a.endTransaction();
            }
        }
    }

    public class d0 extends SharedSQLiteStatement {
        public d0(ur urVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "UPDATE SoftwareLocal SET state=? ,versionCode=? WHERE packageName = ?";
        }
    }

    public class e implements Callable<Unit> {
        public final /* synthetic */ pq a;

        public e(pq pqVar) {
            this.a = pqVar;
        }

        @Override // java.util.concurrent.Callable
        public final Unit call() throws Exception {
            ur.this.a.beginTransaction();
            try {
                ur.this.d.handle(this.a);
                ur.this.a.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                ur.this.a.endTransaction();
            }
        }
    }

    public class f implements Callable<Unit> {
        public final /* synthetic */ int a;
        public final /* synthetic */ String b;

        public f(int i, String str) {
            this.a = i;
            this.b = str;
        }

        @Override // java.util.concurrent.Callable
        public final Unit call() throws Exception {
            SupportSQLiteStatement supportSQLiteStatementAcquire = ur.this.e.acquire();
            supportSQLiteStatementAcquire.bindLong(1, this.a);
            String str = this.b;
            if (str == null) {
                supportSQLiteStatementAcquire.bindNull(2);
            } else {
                supportSQLiteStatementAcquire.bindString(2, str);
            }
            ur.this.a.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                ur.this.a.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                ur.this.a.endTransaction();
                ur.this.e.release(supportSQLiteStatementAcquire);
            }
        }
    }

    public class g implements Callable<Unit> {
        public final /* synthetic */ int a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;

        public g(int i, String str, String str2) {
            this.a = i;
            this.b = str;
            this.c = str2;
        }

        @Override // java.util.concurrent.Callable
        public final Unit call() throws Exception {
            SupportSQLiteStatement supportSQLiteStatementAcquire = ur.this.f.acquire();
            supportSQLiteStatementAcquire.bindLong(1, this.a);
            String str = this.b;
            if (str == null) {
                supportSQLiteStatementAcquire.bindNull(2);
            } else {
                supportSQLiteStatementAcquire.bindString(2, str);
            }
            String str2 = this.c;
            if (str2 == null) {
                supportSQLiteStatementAcquire.bindNull(3);
            } else {
                supportSQLiteStatementAcquire.bindString(3, str2);
            }
            ur.this.a.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                ur.this.a.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                ur.this.a.endTransaction();
                ur.this.f.release(supportSQLiteStatementAcquire);
            }
        }
    }

    public class h implements Callable<Unit> {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;
        public final /* synthetic */ String c;

        public h(int i, int i2, String str) {
            this.a = i;
            this.b = i2;
            this.c = str;
        }

        @Override // java.util.concurrent.Callable
        public final Unit call() throws Exception {
            SupportSQLiteStatement supportSQLiteStatementAcquire = ur.this.g.acquire();
            supportSQLiteStatementAcquire.bindLong(1, this.a);
            supportSQLiteStatementAcquire.bindLong(2, this.b);
            String str = this.c;
            if (str == null) {
                supportSQLiteStatementAcquire.bindNull(3);
            } else {
                supportSQLiteStatementAcquire.bindString(3, str);
            }
            ur.this.a.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                ur.this.a.setTransactionSuccessful();
                return Unit.INSTANCE;
            } finally {
                ur.this.a.endTransaction();
                ur.this.g.release(supportSQLiteStatementAcquire);
            }
        }
    }

    public class i extends EntityInsertionAdapter<pq> {
        public i(ur urVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        public final void bind(SupportSQLiteStatement supportSQLiteStatement, pq pqVar) {
            pq pqVar2 = pqVar;
            String str = pqVar2.a;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            supportSQLiteStatement.bindLong(2, pqVar2.b);
            String str2 = pqVar2.c;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str2);
            }
            String str3 = pqVar2.d;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str3);
            }
            supportSQLiteStatement.bindLong(5, pqVar2.e);
            String str4 = pqVar2.f;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, str4);
            }
            String str5 = pqVar2.g;
            if (str5 == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, str5);
            }
            supportSQLiteStatement.bindLong(8, pqVar2.h);
            String str6 = pqVar2.i;
            if (str6 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, str6);
            }
            supportSQLiteStatement.bindLong(10, pqVar2.j);
            supportSQLiteStatement.bindLong(11, pqVar2.k);
            String str7 = pqVar2.l;
            if (str7 == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, str7);
            }
            supportSQLiteStatement.bindLong(13, pqVar2.m);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "INSERT OR REPLACE INTO `SoftwareLocal` (`packageName`,`id`,`name`,`sourceDir`,`totalSize`,`downloadUrl`,`icon`,`classifyId`,`classifyName`,`versionCode`,`state`,`signature`,`sourceType`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    public class j implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public j(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.j.call():java.lang.Object");
        }
    }

    public class k implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public k(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.k.call():java.lang.Object");
        }
    }

    public class l implements Callable<qq> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public l(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:75:0x0199 A[Catch: all -> 0x01bc, TryCatch #1 {all -> 0x01bc, blocks: (B:4:0x0015, B:5:0x006f, B:7:0x0075, B:9:0x0083, B:12:0x0095, B:14:0x00a8, B:16:0x00ae, B:18:0x00b4, B:20:0x00ba, B:22:0x00c0, B:24:0x00c6, B:26:0x00cc, B:28:0x00d2, B:30:0x00d8, B:32:0x00de, B:34:0x00e4, B:36:0x00ea, B:38:0x00f2, B:44:0x0105, B:48:0x0114, B:52:0x0127, B:56:0x0136, B:60:0x0149, B:64:0x0158, B:68:0x016b, B:72:0x0182, B:73:0x018d, B:75:0x0199, B:76:0x019e, B:78:0x01a5, B:71:0x017c, B:67:0x0165, B:63:0x0152, B:59:0x0143, B:55:0x0130, B:51:0x0121, B:47:0x010e), top: B:89:0x0015, outer: #0 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final spi.qq call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 463
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.l.call():java.lang.Object");
        }
    }

    public class m implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public m(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.m.call():java.lang.Object");
        }
    }

    public class n implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public n(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.n.call():java.lang.Object");
        }
    }

    public class o implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public o(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.o.call():java.lang.Object");
        }
    }

    public class p implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public p(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.p.call():java.lang.Object");
        }
    }

    public class q extends EntityInsertionAdapter<oq> {
        public q(ur urVar, RoomDatabase roomDatabase) {
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

    public class r implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public r(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.r.call():java.lang.Object");
        }
    }

    public class s implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public s(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.s.call():java.lang.Object");
        }
    }

    public class t implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public t(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.t.call():java.lang.Object");
        }
    }

    public class u implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public u(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.u.call():java.lang.Object");
        }
    }

    public class v implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public v(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.v.call():java.lang.Object");
        }
    }

    public class w implements Callable<List<qq>> {
        public final /* synthetic */ RoomSQLiteQuery a;

        public w(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:76:0x01ac A[Catch: all -> 0x01c7, TryCatch #1 {all -> 0x01c7, blocks: (B:15:0x00b1, B:17:0x00b7, B:19:0x00bd, B:21:0x00c3, B:23:0x00c9, B:25:0x00cf, B:27:0x00d5, B:29:0x00db, B:31:0x00e1, B:33:0x00e7, B:35:0x00ed, B:37:0x00f3, B:39:0x00fb, B:45:0x0112, B:49:0x0121, B:53:0x0134, B:57:0x0143, B:61:0x0156, B:65:0x0165, B:69:0x0178, B:73:0x018f, B:74:0x01a0, B:76:0x01ac, B:77:0x01b1, B:72:0x0189, B:68:0x0172, B:64:0x015f, B:60:0x0150, B:56:0x013d, B:52:0x012e, B:48:0x011b), top: B:93:0x00b1 }] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<spi.qq> call() throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.ur.w.call():java.lang.Object");
        }
    }

    public class x extends EntityDeletionOrUpdateAdapter<pq> {
        public x(ur urVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        public final void bind(SupportSQLiteStatement supportSQLiteStatement, pq pqVar) {
            String str = pqVar.a;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "DELETE FROM `SoftwareLocal` WHERE `packageName` = ?";
        }
    }

    public class y extends EntityDeletionOrUpdateAdapter<pq> {
        public y(ur urVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        public final void bind(SupportSQLiteStatement supportSQLiteStatement, pq pqVar) {
            pq pqVar2 = pqVar;
            String str = pqVar2.a;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            supportSQLiteStatement.bindLong(2, pqVar2.b);
            String str2 = pqVar2.c;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str2);
            }
            String str3 = pqVar2.d;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str3);
            }
            supportSQLiteStatement.bindLong(5, pqVar2.e);
            String str4 = pqVar2.f;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, str4);
            }
            String str5 = pqVar2.g;
            if (str5 == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, str5);
            }
            supportSQLiteStatement.bindLong(8, pqVar2.h);
            String str6 = pqVar2.i;
            if (str6 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, str6);
            }
            supportSQLiteStatement.bindLong(10, pqVar2.j);
            supportSQLiteStatement.bindLong(11, pqVar2.k);
            String str7 = pqVar2.l;
            if (str7 == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, str7);
            }
            supportSQLiteStatement.bindLong(13, pqVar2.m);
            String str8 = pqVar2.a;
            if (str8 == null) {
                supportSQLiteStatement.bindNull(14);
            } else {
                supportSQLiteStatement.bindString(14, str8);
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "UPDATE OR REPLACE `SoftwareLocal` SET `packageName` = ?,`id` = ?,`name` = ?,`sourceDir` = ?,`totalSize` = ?,`downloadUrl` = ?,`icon` = ?,`classifyId` = ?,`classifyName` = ?,`versionCode` = ?,`state` = ?,`signature` = ?,`sourceType` = ? WHERE `packageName` = ?";
        }
    }

    public class z extends SharedSQLiteStatement {
        public z(ur urVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "UPDATE SoftwareLocal SET state=? WHERE id = ?";
        }
    }

    public ur(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new i(this, roomDatabase);
        this.c = new q(this, roomDatabase);
        new x(this, roomDatabase);
        this.d = new y(this, roomDatabase);
        new z(this, roomDatabase);
        this.e = new a0(this, roomDatabase);
        this.f = new b0(this, roomDatabase);
        this.g = new c0(this, roomDatabase);
        new d0(this, roomDatabase);
        new a(this, roomDatabase);
    }

    @Override // spi.tr
    public final Object a(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state >=6 and state<=12", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new r(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object b(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state =3 or state =4 or state =10 or state =11 or state =2 or state =9", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new t(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object c(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state =3 or state =4 or state =2 ", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new u(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object d(int i2, Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state =? ", 1);
        roomSQLiteQueryAcquire.bindLong(1, i2);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new k(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object e(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state >=1 and state<=5", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new n(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object f(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new j(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object g(pq pqVar, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.a, true, new b(pqVar), continuation);
    }

    @Override // spi.tr
    public final Object h(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state =3 or state =10", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new s(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object i(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state =5 or state =12", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new m(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object j(String str, Continuation<? super qq> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE packageName = ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new l(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object k(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state =7 or state =11", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new p(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object l(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state =1 or state =8", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new w(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object m(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state =10 or state =11 or state =9", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new v(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object n(Continuation<? super List<qq>> continuation) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM SoftwareLocal WHERE state >=7 and state<=12", 0);
        return CoroutinesRoom.execute(this.a, true, DBUtil.createCancellationSignal(), new o(roomSQLiteQueryAcquire), continuation);
    }

    @Override // spi.tr
    public final Object o(List<oq> list, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.a, true, new d(list), continuation);
    }

    @Override // spi.tr
    public final Object p(pq pqVar, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.a, true, new e(pqVar), continuation);
    }

    @Override // spi.tr
    public final Object q(String str, int i2, int i3, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.a, true, new h(i2, i3, str), continuation);
    }

    @Override // spi.tr
    public final Object r(oq oqVar, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.a, true, new c(oqVar), continuation);
    }

    @Override // spi.tr
    public final Object s(String str, int i2, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.a, true, new f(i2, str), continuation);
    }

    @Override // spi.tr
    public final Object t(String str, int i2, String str2, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.a, true, new g(i2, str2, str), continuation);
    }

    public final void u(ArrayMap<String, ArrayList<oq>> arrayMap) {
        Set<String> setKeySet = arrayMap.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (arrayMap.size() > 999) {
            ArrayMap<String, ArrayList<oq>> arrayMap2 = new ArrayMap<>(999);
            int size = arrayMap.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                arrayMap2.put(arrayMap.keyAt(i2), arrayMap.valueAt(i2));
                i2++;
                i3++;
                if (i3 == 999) {
                    u(arrayMap2);
                    arrayMap2 = new ArrayMap<>(999);
                    i3 = 0;
                }
            }
            if (i3 > 0) {
                u(arrayMap2);
                return;
            }
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `packageName`,`primePackageName`,`signature`,`versionCode`,`versionName` FROM `SoftwareExtraLocal` WHERE `primePackageName` IN (");
        int size2 = setKeySet.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size2);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size2 + 0);
        int i4 = 1;
        for (String str : setKeySet) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i4);
            } else {
                roomSQLiteQueryAcquire.bindString(i4, str);
            }
            i4++;
        }
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "primePackageName");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                ArrayList<oq> arrayList = arrayMap.get(cursorQuery.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(new oq(cursorQuery.isNull(0) ? null : cursorQuery.getString(0), cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.isNull(2) ? null : cursorQuery.getString(2), cursorQuery.getInt(3), cursorQuery.isNull(4) ? null : cursorQuery.getString(4)));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }
}
