package androidx.room;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: SharedSQLiteStatement.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicBoolean f1367a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final RoomDatabase f1368b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile b.m.a.f f1369c;

    public o(RoomDatabase roomDatabase) {
        this.f1368b = roomDatabase;
    }

    private b.m.a.f c() {
        return this.f1368b.d(d());
    }

    private b.m.a.f e(boolean z) {
        if (!z) {
            return c();
        }
        if (this.f1369c == null) {
            this.f1369c = c();
        }
        return this.f1369c;
    }

    public b.m.a.f a() {
        b();
        return e(this.f1367a.compareAndSet(false, true));
    }

    protected void b() {
        this.f1368b.a();
    }

    protected abstract String d();

    public void f(b.m.a.f fVar) {
        if (fVar == this.f1369c) {
            this.f1367a.set(false);
        }
    }
}
