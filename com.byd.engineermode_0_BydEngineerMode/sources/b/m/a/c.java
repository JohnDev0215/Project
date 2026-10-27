package b.m.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
/* JADX INFO: loaded from: classes.dex */
public interface c {

    /* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
    public static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1903a;

        public a(int i) {
            this.f1903a = i;
        }

        private void a(String str) {
            if (str.equalsIgnoreCase(":memory:") || str.trim().length() == 0) {
                return;
            }
            Log.w("SupportSQLite", "deleting the database file: " + str);
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    SQLiteDatabase.deleteDatabase(new File(str));
                } else {
                    try {
                        if (!new File(str).delete()) {
                            Log.e("SupportSQLite", "Could not delete the database file " + str);
                        }
                    } catch (Exception e) {
                        Log.e("SupportSQLite", "error while deleting corrupted database file", e);
                    }
                }
            } catch (Exception e2) {
                Log.w("SupportSQLite", "delete failed: ", e2);
            }
        }

        public void b(b.m.a.b bVar) {
        }

        public void c(b.m.a.b bVar) {
            Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + bVar.r());
            if (!bVar.c()) {
                a(bVar.r());
                return;
            }
            List<Pair<String, String>> listD = null;
            try {
                try {
                    listD = bVar.d();
                } catch (SQLiteException unused) {
                }
                try {
                    bVar.close();
                } catch (IOException unused2) {
                }
            } finally {
                if (listD != null) {
                    Iterator<Pair<String, String>> it = listD.iterator();
                    while (it.hasNext()) {
                        a((String) it.next().second);
                    }
                } else {
                    a(bVar.r());
                }
            }
        }

        public abstract void d(b.m.a.b bVar);

        public abstract void e(b.m.a.b bVar, int i, int i2);

        public void f(b.m.a.b bVar) {
        }

        public abstract void g(b.m.a.b bVar, int i, int i2);
    }

    /* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f1904a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f1905b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final a f1906c;

        /* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            Context f1907a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            String f1908b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            a f1909c;

            a(Context context) {
                this.f1907a = context;
            }

            public b a() {
                a aVar = this.f1909c;
                if (aVar == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                }
                Context context = this.f1907a;
                if (context != null) {
                    return new b(context, this.f1908b, aVar);
                }
                throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
            }

            public a b(a aVar) {
                this.f1909c = aVar;
                return this;
            }

            public a c(String str) {
                this.f1908b = str;
                return this;
            }
        }

        b(Context context, String str, a aVar) {
            this.f1904a = context;
            this.f1905b = str;
            this.f1906c = aVar;
        }

        public static a a(Context context) {
            return new a(context);
        }
    }

    /* JADX INFO: renamed from: b.m.a.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
    public interface InterfaceC0049c {
        c a(b bVar);
    }

    void a(boolean z);

    b.m.a.b b();

    String c();
}
