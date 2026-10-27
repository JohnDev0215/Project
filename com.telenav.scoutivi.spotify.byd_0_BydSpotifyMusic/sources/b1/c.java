package b1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
        public final int f3467a;

        public a(int i7) {
            this.f3467a = i7;
        }

        private void a(String str) {
            if (str.equalsIgnoreCase(":memory:") || str.trim().length() == 0) {
                return;
            }
            Log.w("SupportSQLite", "deleting the database file: " + str);
            try {
                SQLiteDatabase.deleteDatabase(new File(str));
            } catch (Exception e7) {
                Log.w("SupportSQLite", "delete failed: ", e7);
            }
        }

        public void b(b1.b bVar) {
        }

        public void c(b1.b bVar) {
            Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + bVar.R());
            if (!bVar.isOpen()) {
                a(bVar.R());
                return;
            }
            List<Pair<String, String>> listK = null;
            try {
                try {
                    listK = bVar.k();
                } finally {
                    if (listK != null) {
                        Iterator<Pair<String, String>> it = listK.iterator();
                        while (it.hasNext()) {
                            a((String) it.next().second);
                        }
                    } else {
                        a(bVar.R());
                    }
                }
            } catch (SQLiteException unused) {
            }
            try {
                bVar.close();
            } catch (IOException unused2) {
            }
        }

        public abstract void d(b1.b bVar);

        public abstract void e(b1.b bVar, int i7, int i8);

        public void f(b1.b bVar) {
        }

        public abstract void g(b1.b bVar, int i7, int i8);
    }

    /* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f3468a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f3469b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final a f3470c;

        /* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            Context f3471a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            String f3472b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            a f3473c;

            a(Context context) {
                this.f3471a = context;
            }

            public b a() {
                a aVar = this.f3473c;
                if (aVar == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                }
                Context context = this.f3471a;
                if (context != null) {
                    return new b(context, this.f3472b, aVar);
                }
                throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
            }

            public a b(a aVar) {
                this.f3473c = aVar;
                return this;
            }

            public a c(String str) {
                this.f3472b = str;
                return this;
            }
        }

        b(Context context, String str, a aVar) {
            this.f3468a = context;
            this.f3469b = str;
            this.f3470c = aVar;
        }

        public static a a(Context context) {
            return new a(context);
        }
    }

    /* JADX INFO: renamed from: b1.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SupportSQLiteOpenHelper.java */
    public interface InterfaceC0031c {
        c a(b bVar);
    }

    void a(boolean z6);

    b1.b b();
}
