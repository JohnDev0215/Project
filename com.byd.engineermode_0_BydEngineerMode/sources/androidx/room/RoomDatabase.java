package androidx.room;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import b.m.a.c;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
    protected volatile b.m.a.b f1307a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Executor f1308b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Executor f1309c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b.m.a.c f1310d;
    private boolean f;
    boolean g;

    @Deprecated
    protected List<b> h;
    private final ReentrantReadWriteLock i = new ReentrantReadWriteLock();
    private final ThreadLocal<Integer> j = new ThreadLocal<>();
    private final Map<String, Object> k = new ConcurrentHashMap();
    private final g e = e();

    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        private static boolean a(ActivityManager activityManager) {
            if (Build.VERSION.SDK_INT >= 19) {
                return activityManager.isLowRamDevice();
            }
            return false;
        }

        @SuppressLint({"NewApi"})
        JournalMode resolve(Context context) {
            ActivityManager activityManager;
            return this != AUTOMATIC ? this : (Build.VERSION.SDK_INT < 16 || (activityManager = (ActivityManager) context.getSystemService("activity")) == null || a(activityManager)) ? TRUNCATE : WRITE_AHEAD_LOGGING;
        }
    }

    public static class a<T extends RoomDatabase> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f1312a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f1313b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Context f1314c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ArrayList<b> f1315d;
        private Executor e;
        private Executor f;
        private c.InterfaceC0049c g;
        private boolean h;
        private boolean j;
        private boolean l;
        private Set<Integer> n;
        private Set<Integer> o;
        private String p;
        private File q;
        private JournalMode i = JournalMode.AUTOMATIC;
        private boolean k = true;
        private final c m = new c();

        a(Context context, Class<T> cls, String str) {
            this.f1314c = context;
            this.f1312a = cls;
            this.f1313b = str;
        }

        public a<T> a(androidx.room.q.a... aVarArr) {
            if (this.o == null) {
                this.o = new HashSet();
            }
            for (androidx.room.q.a aVar : aVarArr) {
                this.o.add(Integer.valueOf(aVar.f1375a));
                this.o.add(Integer.valueOf(aVar.f1376b));
            }
            this.m.b(aVarArr);
            return this;
        }

        @SuppressLint({"RestrictedApi"})
        public T b() {
            Executor executor;
            if (this.f1314c == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            }
            if (this.f1312a == null) {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            }
            Executor executor2 = this.e;
            if (executor2 == null && this.f == null) {
                Executor executorD = b.b.a.a.a.d();
                this.f = executorD;
                this.e = executorD;
            } else if (executor2 != null && this.f == null) {
                this.f = executor2;
            } else if (executor2 == null && (executor = this.f) != null) {
                this.e = executor;
            }
            Set<Integer> set = this.o;
            if (set != null && this.n != null) {
                for (Integer num : set) {
                    if (this.n.contains(num)) {
                        throw new IllegalArgumentException("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + num);
                    }
                }
            }
            if (this.g == null) {
                this.g = new b.m.a.g.c();
            }
            String str = this.p;
            if (str != null || this.q != null) {
                if (this.f1313b == null) {
                    throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.");
                }
                if (str != null && this.q != null) {
                    throw new IllegalArgumentException("Both createFromAsset() and createFromFile() was called on this Builder but the database can only be created using one of the two configurations.");
                }
                this.g = new n(str, this.q, this.g);
            }
            Context context = this.f1314c;
            androidx.room.a aVar = new androidx.room.a(context, this.f1313b, this.g, this.m, this.f1315d, this.h, this.i.resolve(context), this.e, this.f, this.j, this.k, this.l, this.n, this.p, this.q);
            T t = (T) i.b(this.f1312a, "_Impl");
            t.l(aVar);
            return t;
        }
    }

    public static abstract class b {
        public void a(b.m.a.b bVar) {
        }

        public void b(b.m.a.b bVar) {
        }

        public void c(b.m.a.b bVar) {
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private HashMap<Integer, TreeMap<Integer, androidx.room.q.a>> f1316a = new HashMap<>();

        private void a(androidx.room.q.a aVar) {
            int i = aVar.f1375a;
            int i2 = aVar.f1376b;
            TreeMap<Integer, androidx.room.q.a> treeMap = this.f1316a.get(Integer.valueOf(i));
            if (treeMap == null) {
                treeMap = new TreeMap<>();
                this.f1316a.put(Integer.valueOf(i), treeMap);
            }
            androidx.room.q.a aVar2 = treeMap.get(Integer.valueOf(i2));
            if (aVar2 != null) {
                Log.w("ROOM", "Overriding migration " + aVar2 + " with " + aVar);
            }
            treeMap.put(Integer.valueOf(i2), aVar);
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
        private java.util.List<androidx.room.q.a> d(java.util.List<androidx.room.q.a> r7, boolean r8, int r9, int r10) {
            /*
                r6 = this;
            L0:
                if (r8 == 0) goto L5
                if (r9 >= r10) goto L58
                goto L7
            L5:
                if (r9 <= r10) goto L58
            L7:
                java.util.HashMap<java.lang.Integer, java.util.TreeMap<java.lang.Integer, androidx.room.q.a>> r0 = r6.f1316a
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
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabase.c.d(java.util.List, boolean, int, int):java.util.List");
        }

        public void b(androidx.room.q.a... aVarArr) {
            for (androidx.room.q.a aVar : aVarArr) {
                a(aVar);
            }
        }

        public List<androidx.room.q.a> c(int i, int i2) {
            if (i == i2) {
                return Collections.emptyList();
            }
            return d(new ArrayList(), i2 > i, i, i2);
        }
    }

    private static boolean n() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public void a() {
        if (!this.f && n()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void b() {
        if (!k() && this.j.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    @Deprecated
    public void c() {
        a();
        b.m.a.b bVarB = this.f1310d.b();
        this.e.m(bVarB);
        bVarB.b();
    }

    public b.m.a.f d(String str) {
        a();
        b();
        return this.f1310d.b().j(str);
    }

    protected abstract g e();

    protected abstract b.m.a.c f(androidx.room.a aVar);

    @Deprecated
    public void g() {
        this.f1310d.b().a();
        if (k()) {
            return;
        }
        this.e.f();
    }

    Lock h() {
        return this.i.readLock();
    }

    public b.m.a.c i() {
        return this.f1310d;
    }

    public Executor j() {
        return this.f1308b;
    }

    public boolean k() {
        return this.f1310d.b().t();
    }

    public void l(androidx.room.a aVar) {
        b.m.a.c cVarF = f(aVar);
        this.f1310d = cVarF;
        if (cVarF instanceof m) {
            ((m) cVarF).e(aVar);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            z = aVar.g == JournalMode.WRITE_AHEAD_LOGGING;
            this.f1310d.a(z);
        }
        this.h = aVar.e;
        this.f1308b = aVar.h;
        this.f1309c = new p(aVar.i);
        this.f = aVar.f;
        this.g = z;
        if (aVar.j) {
            this.e.i(aVar.f1318b, aVar.f1319c);
        }
    }

    protected void m(b.m.a.b bVar) {
        this.e.d(bVar);
    }

    public boolean o() {
        b.m.a.b bVar = this.f1307a;
        return bVar != null && bVar.c();
    }

    public Cursor p(b.m.a.e eVar) {
        return q(eVar, null);
    }

    public Cursor q(b.m.a.e eVar, CancellationSignal cancellationSignal) {
        a();
        b();
        return (cancellationSignal == null || Build.VERSION.SDK_INT < 16) ? this.f1310d.b().k(eVar) : this.f1310d.b().s(eVar, cancellationSignal);
    }

    @Deprecated
    public void r() {
        this.f1310d.b().h();
    }
}
