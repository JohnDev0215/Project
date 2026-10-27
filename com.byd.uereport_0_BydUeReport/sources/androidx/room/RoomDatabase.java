package androidx.room;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Looper;
import d1.g;
import d1.i;
import d1.m;
import d1.n;
import d1.p;
import h1.c;
import h1.e;
import h1.f;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public abstract class RoomDatabase {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Deprecated
    public volatile h1.b f1735a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Executor f1736b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Executor f1737c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public h1.c f1738d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1740f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1741g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @Deprecated
    public List<b> f1742h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ReentrantReadWriteLock f1743i = new ReentrantReadWriteLock();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ThreadLocal<Integer> f1744j = new ThreadLocal<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Map<String, Object> f1745k = new ConcurrentHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f1739e = e();

    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        private static boolean isLowRamDevice(ActivityManager activityManager) {
            if (Build.VERSION.SDK_INT >= 19) {
                return activityManager.isLowRamDevice();
            }
            return false;
        }

        @SuppressLint({"NewApi"})
        public JournalMode resolve(Context context) {
            ActivityManager activityManager;
            return this != AUTOMATIC ? this : (Build.VERSION.SDK_INT < 16 || (activityManager = (ActivityManager) context.getSystemService("activity")) == null || isLowRamDevice(activityManager)) ? TRUNCATE : WRITE_AHEAD_LOGGING;
        }
    }

    public static class a<T extends RoomDatabase> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Class<T> f1746a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f1747b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Context f1748c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ArrayList<b> f1749d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Executor f1750e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Executor f1751f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public c.InterfaceC0050c f1752g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1753h;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f1755j;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f1757l;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public Set<Integer> f1759n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public Set<Integer> f1760o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public String f1761p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public File f1762q;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public JournalMode f1754i = JournalMode.AUTOMATIC;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public boolean f1756k = true;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final c f1758m = new c();

        public a(Context context, Class<T> cls, String str) {
            this.f1748c = context;
            this.f1746a = cls;
            this.f1747b = str;
        }

        public a<T> a() {
            this.f1753h = true;
            return this;
        }

        @SuppressLint({"RestrictedApi"})
        public T b() {
            Executor executor;
            if (this.f1748c == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            }
            if (this.f1746a == null) {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            }
            Executor executor2 = this.f1750e;
            if (executor2 == null && this.f1751f == null) {
                Executor executorD = s.a.d();
                this.f1751f = executorD;
                this.f1750e = executorD;
            } else if (executor2 != null && this.f1751f == null) {
                this.f1751f = executor2;
            } else if (executor2 == null && (executor = this.f1751f) != null) {
                this.f1750e = executor;
            }
            Set<Integer> set = this.f1760o;
            if (set != null && this.f1759n != null) {
                for (Integer num : set) {
                    if (this.f1759n.contains(num)) {
                        throw new IllegalArgumentException("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + num);
                    }
                }
            }
            if (this.f1752g == null) {
                this.f1752g = new i1.c();
            }
            String str = this.f1761p;
            if (str != null || this.f1762q != null) {
                if (this.f1747b == null) {
                    throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.");
                }
                if (str != null && this.f1762q != null) {
                    throw new IllegalArgumentException("Both createFromAsset() and createFromFile() was called on this Builder but the database can only be created using one of the two configurations.");
                }
                this.f1752g = new n(str, this.f1762q, this.f1752g);
            }
            Context context = this.f1748c;
            d1.a aVar = new d1.a(context, this.f1747b, this.f1752g, this.f1758m, this.f1749d, this.f1753h, this.f1754i.resolve(context), this.f1750e, this.f1751f, this.f1755j, this.f1756k, this.f1757l, this.f1759n, this.f1761p, this.f1762q);
            T t7 = (T) i.b(this.f1746a, "_Impl");
            t7.l(aVar);
            return t7;
        }
    }

    public static abstract class b {
        public void a(h1.b bVar) {
        }

        public void b(h1.b bVar) {
        }

        public void c(h1.b bVar) {
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public HashMap<Integer, TreeMap<Integer, e1.a>> f1763a = new HashMap<>();

        public List<e1.a> a(int i8, int i9) {
            if (i8 == i9) {
                return Collections.emptyList();
            }
            return b(new ArrayList(), i9 > i8, i8, i9);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0016 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0047 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:43:? A[LOOP:1: B:13:0x0026->B:43:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0017  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<e1.a> b(java.util.List<e1.a> r7, boolean r8, int r9, int r10) {
            /*
                r6 = this;
            L0:
                if (r8 == 0) goto L5
                if (r9 >= r10) goto L58
                goto L7
            L5:
                if (r9 <= r10) goto L58
            L7:
                java.util.HashMap<java.lang.Integer, java.util.TreeMap<java.lang.Integer, e1.a>> r0 = r6.f1763a
                java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
                java.lang.Object r0 = r0.get(r1)
                java.util.TreeMap r0 = (java.util.TreeMap) r0
                r1 = 0
                if (r0 != 0) goto L17
                return r1
            L17:
                if (r8 == 0) goto L1e
                java.util.NavigableSet r2 = r0.descendingKeySet()
                goto L22
            L1e:
                java.util.Set r2 = r0.keySet()
            L22:
                java.util.Iterator r2 = r2.iterator()
            L26:
                boolean r3 = r2.hasNext()
                r4 = 1
                r5 = 0
                if (r3 == 0) goto L54
                java.lang.Object r3 = r2.next()
                java.lang.Integer r3 = (java.lang.Integer) r3
                int r3 = r3.intValue()
                if (r8 == 0) goto L40
                if (r3 > r10) goto L45
                if (r3 <= r9) goto L45
            L3e:
                r5 = r4
                goto L45
            L40:
                if (r3 < r10) goto L45
                if (r3 >= r9) goto L45
                goto L3e
            L45:
                if (r5 == 0) goto L26
                java.lang.Integer r9 = java.lang.Integer.valueOf(r3)
                java.lang.Object r9 = r0.get(r9)
                r7.add(r9)
                r9 = r3
                goto L55
            L54:
                r4 = r5
            L55:
                if (r4 != 0) goto L0
                return r1
            L58:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabase.c.b(java.util.List, boolean, int, int):java.util.List");
        }
    }

    public static boolean n() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public void a() {
        if (!this.f1740f && n()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void b() {
        if (!k() && this.f1744j.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    @Deprecated
    public void c() {
        a();
        h1.b bVarB = this.f1738d.b();
        this.f1739e.m(bVarB);
        bVarB.d();
    }

    public f d(String str) {
        a();
        b();
        return this.f1738d.b().u(str);
    }

    public abstract g e();

    public abstract h1.c f(d1.a aVar);

    @Deprecated
    public void g() {
        this.f1738d.b().c();
        if (k()) {
            return;
        }
        this.f1739e.f();
    }

    public Lock h() {
        return this.f1743i.readLock();
    }

    public h1.c i() {
        return this.f1738d;
    }

    public Executor j() {
        return this.f1736b;
    }

    public boolean k() {
        return this.f1738d.b().K();
    }

    public void l(d1.a aVar) {
        h1.c cVarF = f(aVar);
        this.f1738d = cVarF;
        if (cVarF instanceof m) {
            ((m) cVarF).e(aVar);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            z = aVar.f4340g == JournalMode.WRITE_AHEAD_LOGGING;
            this.f1738d.a(z);
        }
        this.f1742h = aVar.f4338e;
        this.f1736b = aVar.f4341h;
        this.f1737c = new p(aVar.f4342i);
        this.f1740f = aVar.f4339f;
        this.f1741g = z;
        if (aVar.f4343j) {
            this.f1739e.i(aVar.f4335b, aVar.f4336c);
        }
    }

    public void m(h1.b bVar) {
        this.f1739e.d(bVar);
    }

    public boolean o() {
        h1.b bVar = this.f1735a;
        return bVar != null && bVar.isOpen();
    }

    public Cursor p(e eVar) {
        return q(eVar, null);
    }

    public Cursor q(e eVar, CancellationSignal cancellationSignal) {
        a();
        b();
        return (cancellationSignal == null || Build.VERSION.SDK_INT < 16) ? this.f1738d.b().y(eVar) : this.f1738d.b().J(eVar, cancellationSignal);
    }

    @Deprecated
    public void r() {
        this.f1738d.b().W();
    }
}
