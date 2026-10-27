package y0;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: SharedSQLiteStatement.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicBoolean f9956a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f9957b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile b1.f f9958c;

    public j(f fVar) {
        this.f9957b = fVar;
    }

    private b1.f c() {
        return this.f9957b.c(d());
    }

    private b1.f e(boolean z6) {
        if (!z6) {
            return c();
        }
        if (this.f9958c == null) {
            this.f9958c = c();
        }
        return this.f9958c;
    }

    public b1.f a() {
        b();
        return e(this.f9956a.compareAndSet(false, true));
    }

    protected void b() {
        this.f9957b.a();
    }

    protected abstract String d();

    public void f(b1.f fVar) {
        if (fVar == this.f9958c) {
            this.f9956a.set(false);
        }
    }
}
