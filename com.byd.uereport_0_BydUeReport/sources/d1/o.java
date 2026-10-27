package d1;

import androidx.room.RoomDatabase;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: SharedSQLiteStatement.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f4424a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final RoomDatabase f4425b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile h1.f f4426c;

    public o(RoomDatabase roomDatabase) {
        this.f4425b = roomDatabase;
    }

    public h1.f a() {
        b();
        return e(this.f4424a.compareAndSet(false, true));
    }

    public void b() {
        this.f4425b.a();
    }

    public final h1.f c() {
        return this.f4425b.d(d());
    }

    public abstract String d();

    public final h1.f e(boolean z7) {
        if (!z7) {
            return c();
        }
        if (this.f4426c == null) {
            this.f4426c = c();
        }
        return this.f4426c;
    }

    public void f(h1.f fVar) {
        if (fVar == this.f4426c) {
            this.f4424a.set(false);
        }
    }
}
