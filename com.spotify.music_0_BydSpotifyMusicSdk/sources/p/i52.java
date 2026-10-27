package p;

import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;

/* JADX INFO: loaded from: classes.dex */
public abstract class i52 {
    public volatile nh2 a;
    public Executor b;
    public qh2 c;
    public boolean e;
    public List f;
    public final LinkedHashMap j;
    public final c61 d = e();
    public final LinkedHashMap g = new LinkedHashMap();
    public final ReentrantReadWriteLock h = new ReentrantReadWriteLock();
    public final ThreadLocal i = new ThreadLocal();

    public i52() {
        v03.i(Collections.synchronizedMap(new LinkedHashMap()), "synchronizedMap(mutableMapOf())");
        this.j = new LinkedHashMap();
    }

    public static Object p(Class cls, qh2 qh2Var) {
        if (cls.isInstance(qh2Var)) {
            return qh2Var;
        }
        if (qh2Var instanceof t80) {
            return p(cls, ((t80) qh2Var).b());
        }
        return null;
    }

    public final void a() {
        if (this.e) {
            return;
        }
        if (!(!(Looper.getMainLooper().getThread() == Thread.currentThread()))) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.".toString());
        }
    }

    public final void b() {
        if (!h().u0().S() && this.i.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.".toString());
        }
    }

    public final void c() {
        a();
        a();
        nh2 nh2VarU0 = h().u0();
        this.d.d(nh2VarU0);
        if (nh2VarU0.e0()) {
            nh2VarU0.k0();
        } else {
            nh2VarU0.n();
        }
    }

    public final th2 d(String str) {
        v03.j(str, "sql");
        a();
        b();
        return h().u0().H(str);
    }

    public abstract c61 e();

    public abstract qh2 f(a60 a60Var);

    public List g(LinkedHashMap linkedHashMap) {
        v03.j(linkedHashMap, "autoMigrationSpecs");
        return EmptyList.c;
    }

    public final qh2 h() {
        qh2 qh2Var = this.c;
        if (qh2Var != null) {
            return qh2Var;
        }
        v03.Q("internalOpenHelper");
        throw null;
    }

    public Set i() {
        return EmptySet.c;
    }

    public Map j() {
        return kotlin.collections.c.h0();
    }

    public final void k() {
        h().u0().m();
        if (h().u0().S()) {
            return;
        }
        c61 c61Var = this.d;
        if (c61Var.f.compareAndSet(false, true)) {
            Executor executor = c61Var.a.b;
            if (executor != null) {
                executor.execute(c61Var.m);
            } else {
                v03.Q("internalQueryExecutor");
                throw null;
            }
        }
    }

    public final void l(androidx.sqlite.db.framework.b bVar) {
        c61 c61Var = this.d;
        c61Var.getClass();
        synchronized (c61Var.l) {
            if (c61Var.g) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            bVar.w("PRAGMA temp_store = MEMORY;");
            bVar.w("PRAGMA recursive_triggers='ON';");
            bVar.w("CREATE TEMP TABLE room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
            c61Var.d(bVar);
            c61Var.h = bVar.H("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1");
            c61Var.g = true;
        }
    }

    public final Cursor m(sh2 sh2Var, CancellationSignal cancellationSignal) {
        a();
        b();
        return cancellationSignal != null ? h().u0().o(sh2Var, cancellationSignal) : h().u0().F0(sh2Var);
    }

    public final Object n(Callable callable) {
        c();
        try {
            Object objCall = callable.call();
            o();
            return objCall;
        } finally {
            k();
        }
    }

    public final void o() {
        h().u0().j0();
    }
}
