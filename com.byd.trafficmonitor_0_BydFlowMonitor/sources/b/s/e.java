package b.s;

import android.database.Cursor;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Looper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: RoomDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Deprecated
    public volatile b.u.a.b f3162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.u.a.c f3163b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3165d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3166e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ReentrantReadWriteLock f3167f = new ReentrantReadWriteLock();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ThreadLocal<Integer> f3168g = new ThreadLocal<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map<String, Object> f3169h = new ConcurrentHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f3164c = c();

    public static boolean g() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public void a() {
        if (!this.f3165d && g()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void b() {
        if (!f() && this.f3168g.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    public abstract d c();

    public Lock d() {
        return this.f3167f.readLock();
    }

    public b.u.a.c e() {
        return this.f3163b;
    }

    public boolean f() {
        return this.f3163b.a().K();
    }

    public boolean h() {
        b.u.a.b bVar = this.f3162a;
        return bVar != null && bVar.isOpen();
    }

    public Cursor i(b.u.a.d dVar) {
        return j(dVar, null);
    }

    public Cursor j(b.u.a.d dVar, CancellationSignal cancellationSignal) {
        a();
        b();
        return (cancellationSignal == null || Build.VERSION.SDK_INT < 16) ? this.f3163b.a().v(dVar) : this.f3163b.a().I(dVar, cancellationSignal);
    }
}
