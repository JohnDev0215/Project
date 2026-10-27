package androidx.sqlite.db.framework;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import p.hk0;
import p.j52;
import p.mx1;
import p.nh2;
import p.v03;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
public final class c extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ int f22p = 0;
    public final Context c;
    public final hk0 e;
    public final j52 k;
    public final boolean l;
    public boolean m;
    public final mx1 n;
    public boolean o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(Context context, String str, final hk0 hk0Var, final j52 j52Var, boolean z) {
        super(context, str, null, j52Var.a, new DatabaseErrorHandler() { // from class: p.jy0
            @Override // android.database.DatabaseErrorHandler
            public final void onCorruption(SQLiteDatabase sQLiteDatabase) {
                v03.j(j52Var, "$callback");
                hk0 hk0Var2 = hk0Var;
                v03.j(hk0Var2, "$dbRef");
                int i = androidx.sqlite.db.framework.c.f22p;
                v03.i(sQLiteDatabase, "dbObj");
                androidx.sqlite.db.framework.b bVarZ = zb0.z(hk0Var2, sQLiteDatabase);
                Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + bVarZ + ".path");
                SQLiteDatabase sQLiteDatabase2 = bVarZ.c;
                if (!sQLiteDatabase2.isOpen()) {
                    String path = sQLiteDatabase2.getPath();
                    if (path != null) {
                        j52.b(path);
                        return;
                    }
                    return;
                }
                List<Pair<String, String>> attachedDbs = null;
                try {
                    try {
                        attachedDbs = sQLiteDatabase2.getAttachedDbs();
                    } finally {
                        if (attachedDbs != null) {
                            Iterator<T> it = attachedDbs.iterator();
                            while (it.hasNext()) {
                                Object obj = ((Pair) it.next()).second;
                                v03.i(obj, "p.second");
                                j52.b((String) obj);
                            }
                        } else {
                            String path2 = sQLiteDatabase2.getPath();
                            if (path2 != null) {
                                j52.b(path2);
                            }
                        }
                    }
                } catch (SQLiteException unused) {
                }
                try {
                    bVarZ.close();
                } catch (IOException unused2) {
                }
                if (attachedDbs != null) {
                    return;
                }
            }
        });
        v03.j(context, "context");
        v03.j(j52Var, "callback");
        this.c = context;
        this.e = hk0Var;
        this.k = j52Var;
        this.l = z;
        if (str == null) {
            str = UUID.randomUUID().toString();
            v03.i(str, "randomUUID().toString()");
        }
        this.n = new mx1(str, context.getCacheDir(), false);
    }

    public final nh2 b(boolean z) {
        mx1 mx1Var = this.n;
        try {
            mx1Var.a((this.o || getDatabaseName() == null) ? false : true);
            this.m = false;
            SQLiteDatabase sQLiteDatabaseQ = q(z);
            if (!this.m) {
                b bVarC = c(sQLiteDatabaseQ);
                mx1Var.b();
                return bVarC;
            }
            close();
            nh2 nh2VarB = b(z);
            mx1Var.b();
            return nh2VarB;
        } catch (Throwable th) {
            mx1Var.b();
            throw th;
        }
    }

    public final b c(SQLiteDatabase sQLiteDatabase) {
        v03.j(sQLiteDatabase, "sqLiteDatabase");
        return zb0.z(this.e, sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public final void close() {
        mx1 mx1Var = this.n;
        try {
            mx1Var.a(mx1Var.a);
            super.close();
            this.e.e = null;
            this.o = false;
        } finally {
            mx1Var.b();
        }
    }

    public final SQLiteDatabase i(boolean z) {
        if (z) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            v03.i(writableDatabase, "{\n                super.…eDatabase()\n            }");
            return writableDatabase;
        }
        SQLiteDatabase readableDatabase = getReadableDatabase();
        v03.i(readableDatabase, "{\n                super.…eDatabase()\n            }");
        return readableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
        v03.j(sQLiteDatabase, "db");
        boolean z = this.m;
        j52 j52Var = this.k;
        if (!z && j52Var.a != sQLiteDatabase.getVersion()) {
            sQLiteDatabase.setMaxSqlCacheSize(1);
        }
        try {
            j52Var.c(c(sQLiteDatabase));
        } catch (Throwable th) {
            throw new FrameworkSQLiteOpenHelper$OpenHelper$CallbackException(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.c, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        v03.j(sQLiteDatabase, "sqLiteDatabase");
        try {
            this.k.d(c(sQLiteDatabase));
        } catch (Throwable th) {
            throw new FrameworkSQLiteOpenHelper$OpenHelper$CallbackException(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.e, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        v03.j(sQLiteDatabase, "db");
        this.m = true;
        try {
            this.k.e(c(sQLiteDatabase), i, i2);
        } catch (Throwable th) {
            throw new FrameworkSQLiteOpenHelper$OpenHelper$CallbackException(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.l, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        v03.j(sQLiteDatabase, "db");
        if (!this.m) {
            try {
                this.k.f(c(sQLiteDatabase));
            } catch (Throwable th) {
                throw new FrameworkSQLiteOpenHelper$OpenHelper$CallbackException(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.m, th);
            }
        }
        this.o = true;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        v03.j(sQLiteDatabase, "sqLiteDatabase");
        this.m = true;
        try {
            this.k.g(c(sQLiteDatabase), i, i2);
        } catch (Throwable th) {
            throw new FrameworkSQLiteOpenHelper$OpenHelper$CallbackException(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.k, th);
        }
    }

    public final SQLiteDatabase q(boolean z) throws Throwable {
        File parentFile;
        String databaseName = getDatabaseName();
        boolean z2 = this.o;
        Context context = this.c;
        if (databaseName != null && !z2 && (parentFile = context.getDatabasePath(databaseName).getParentFile()) != null) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                parentFile.toString();
            }
        }
        try {
            return i(z);
        } catch (Throwable unused) {
            super.close();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused2) {
            }
            try {
                return i(z);
            } catch (Throwable th) {
                super.close();
                if (th instanceof FrameworkSQLiteOpenHelper$OpenHelper$CallbackException) {
                    FrameworkSQLiteOpenHelper$OpenHelper$CallbackException frameworkSQLiteOpenHelper$OpenHelper$CallbackException = th;
                    Throwable cause = frameworkSQLiteOpenHelper$OpenHelper$CallbackException.getCause();
                    int iOrdinal = frameworkSQLiteOpenHelper$OpenHelper$CallbackException.a().ordinal();
                    if (iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 2 || iOrdinal == 3 || !(cause instanceof SQLiteException)) {
                        throw cause;
                    }
                } else if (!(th instanceof SQLiteException) || databaseName == null || !this.l) {
                    throw th;
                }
                context.deleteDatabase(databaseName);
                try {
                    return i(z);
                } catch (FrameworkSQLiteOpenHelper$OpenHelper$CallbackException e) {
                    throw e.getCause();
                }
            }
        }
    }
}
