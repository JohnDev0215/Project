package y0;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Looper;
import b1.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: RoomDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected volatile b1.b f9920a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Executor f9921b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b1.c f9922c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f9924e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f9925f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected List<b> f9926g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final ReentrantLock f9927h = new ReentrantLock();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final y0.d f9923d = d();

    /* JADX INFO: compiled from: RoomDatabase.java */
    public static class a<T extends f> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f9928a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f9929b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Context f9930c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ArrayList<b> f9931d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Executor f9932e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private c.InterfaceC0031c f9933f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f9934g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private c f9935h = c.AUTOMATIC;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private boolean f9936i = true;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final d f9937j = new d();

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private Set<Integer> f9938k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private Set<Integer> f9939l;

        a(Context context, Class<T> cls, String str) {
            this.f9930c = context;
            this.f9928a = cls;
            this.f9929b = str;
        }

        public a<T> a() {
            this.f9934g = true;
            return this;
        }

        public T b() {
            if (this.f9930c == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            }
            if (this.f9928a == null) {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            }
            if (this.f9932e == null) {
                this.f9932e = k.a.d();
            }
            Set<Integer> set = this.f9939l;
            if (set != null && this.f9938k != null) {
                for (Integer num : set) {
                    if (this.f9938k.contains(num)) {
                        throw new IllegalArgumentException("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + num);
                    }
                }
            }
            if (this.f9933f == null) {
                this.f9933f = new c1.c();
            }
            Context context = this.f9930c;
            y0.a aVar = new y0.a(context, this.f9929b, this.f9933f, this.f9937j, this.f9931d, this.f9934g, this.f9935h.a(context), this.f9932e, this.f9936i, this.f9938k);
            T t6 = (T) e.b(this.f9928a, "_Impl");
            t6.k(aVar);
            return t6;
        }

        public a<T> c(c cVar) {
            this.f9935h = cVar;
            return this;
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.java */
    public static abstract class b {
        public void a(b1.b bVar) {
        }

        public void b(b1.b bVar) {
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.java */
    public enum c {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        @SuppressLint({"NewApi"})
        c a(Context context) {
            if (this != AUTOMATIC) {
                return this;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            return (activityManager == null || w.b.a(activityManager)) ? TRUNCATE : WRITE_AHEAD_LOGGING;
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private n.h<n.h<z0.a>> f9941a = new n.h<>();

        /* JADX WARN: Removed duplicated region for block: B:13:0x001a  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0019 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.util.List<z0.a> b(java.util.List<z0.a> r11, boolean r12, int r13, int r14) {
            /*
                r10 = this;
                r0 = -1
                r1 = 1
                if (r12 == 0) goto L6
                r2 = r0
                goto L7
            L6:
                r2 = r1
            L7:
                if (r12 == 0) goto Lc
                if (r13 >= r14) goto L4d
                goto Le
            Lc:
                if (r13 <= r14) goto L4d
            Le:
                n.h<n.h<z0.a>> r3 = r10.f9941a
                java.lang.Object r3 = r3.e(r13)
                n.h r3 = (n.h) r3
                r4 = 0
                if (r3 != 0) goto L1a
                return r4
            L1a:
                int r5 = r3.k()
                r6 = 0
                if (r12 == 0) goto L25
                int r5 = r5 + (-1)
                r7 = r0
                goto L27
            L25:
                r7 = r5
                r5 = r6
            L27:
                if (r5 == r7) goto L4a
                int r8 = r3.h(r5)
                if (r12 == 0) goto L37
                if (r8 > r14) goto L35
                if (r8 <= r13) goto L35
            L33:
                r9 = r1
                goto L3c
            L35:
                r9 = r6
                goto L3c
            L37:
                if (r8 < r14) goto L35
                if (r8 >= r13) goto L35
                goto L33
            L3c:
                if (r9 == 0) goto L48
                java.lang.Object r13 = r3.l(r5)
                r11.add(r13)
                r6 = r1
                r13 = r8
                goto L4a
            L48:
                int r5 = r5 + r2
                goto L27
            L4a:
                if (r6 != 0) goto L7
                return r4
            L4d:
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: y0.f.d.b(java.util.List, boolean, int, int):java.util.List");
        }

        public List<z0.a> a(int i7, int i8) {
            if (i7 == i8) {
                return Collections.emptyList();
            }
            return b(new ArrayList(), i8 > i7, i7, i8);
        }
    }

    private static boolean m() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public void a() {
        if (!this.f9924e && m()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void b() {
        a();
        b1.b bVarB = this.f9922c.b();
        this.f9923d.g(bVarB);
        bVarB.g();
    }

    public b1.f c(String str) {
        a();
        return this.f9922c.b().w(str);
    }

    protected abstract y0.d d();

    protected abstract b1.c e(y0.a aVar);

    public void f() {
        this.f9922c.b().f();
        if (j()) {
            return;
        }
        this.f9923d.d();
    }

    Lock g() {
        return this.f9927h;
    }

    public b1.c h() {
        return this.f9922c;
    }

    public Executor i() {
        return this.f9921b;
    }

    public boolean j() {
        return this.f9922c.b().W();
    }

    public void k(y0.a aVar) {
        b1.c cVarE = e(aVar);
        this.f9922c = cVarE;
        boolean z6 = aVar.f9893g == c.WRITE_AHEAD_LOGGING;
        cVarE.a(z6);
        this.f9926g = aVar.f9891e;
        this.f9921b = aVar.f9894h;
        this.f9924e = aVar.f9892f;
        this.f9925f = z6;
    }

    protected void l(b1.b bVar) {
        this.f9923d.c(bVar);
    }

    public boolean n() {
        b1.b bVar = this.f9920a;
        return bVar != null && bVar.isOpen();
    }

    public Cursor o(b1.e eVar) {
        a();
        return this.f9922c.b().h(eVar);
    }

    public Cursor p(String str, Object[] objArr) {
        return this.f9922c.b().h(new b1.a(str, objArr));
    }

    public void q() {
        this.f9922c.b().u();
    }
}
