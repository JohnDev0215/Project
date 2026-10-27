package h1;

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
        public final int f5122a;

        public a(int i8) {
            this.f5122a = i8;
        }

        public final void a(String str) {
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
                    } catch (Exception e8) {
                        Log.e("SupportSQLite", "error while deleting corrupted database file", e8);
                    }
                }
            } catch (Exception e9) {
                Log.w("SupportSQLite", "delete failed: ", e9);
            }
        }

        public void b(h1.b bVar) {
        }

        public void c(h1.b bVar) {
            Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + bVar.a());
            if (!bVar.isOpen()) {
                a(bVar.a());
                return;
            }
            List<Pair<String, String>> listH = null;
            try {
                try {
                    listH = bVar.h();
                } finally {
                    if (listH != null) {
                        Iterator<Pair<String, String>> it = listH.iterator();
                        while (it.hasNext()) {
                            a((String) it.next().second);
                        }
                    } else {
                        a(bVar.a());
                    }
                }
            } catch (SQLiteException unused) {
            }
            try {
                bVar.close();
            } catch (IOException unused2) {
            }
        }

        public abstract void d(h1.b bVar);

        public abstract void e(h1.b bVar, int i8, int i9);

        public void f(h1.b bVar) {
        }

        public abstract void g(h1.b bVar, int i8, int i9);
    }

    /* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f5123a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f5124b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final a f5125c;

        /* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public Context f5126a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public String f5127b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public a f5128c;

            public a(Context context) {
                this.f5126a = context;
            }

            public b a() {
                a aVar = this.f5128c;
                if (aVar == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                }
                Context context = this.f5126a;
                if (context != null) {
                    return new b(context, this.f5127b, aVar);
                }
                throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
            }

            public a b(a aVar) {
                this.f5128c = aVar;
                return this;
            }

            public a c(String str) {
                this.f5127b = str;
                return this;
            }
        }

        public b(Context context, String str, a aVar) {
            this.f5123a = context;
            this.f5124b = str;
            this.f5125c = aVar;
        }

        public static a a(Context context) {
            return new a(context);
        }
    }

    /* JADX INFO: renamed from: h1.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
    public interface InterfaceC0050c {
        c a(b bVar);
    }

    void a(boolean z7);

    h1.b b();

    String c();
}
