package S8;

import java.lang.reflect.InvocationTargetException;
import n7.C1995j;

/* JADX INFO: loaded from: classes.dex */
public abstract class N extends AbstractC0721s {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ int f8093q = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f8094n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C1995j f8095p;

    public final void P(boolean z6) {
        long j3 = this.f8094n - (z6 ? 4294967296L : 1L);
        this.f8094n = j3;
        if (j3 <= 0 && this.o) {
            shutdown();
        }
    }

    public final void Q(D d) {
        C1995j c1995j = this.f8095p;
        if (c1995j == null) {
            c1995j = new C1995j();
            this.f8095p = c1995j;
        }
        c1995j.addLast(d);
    }

    public abstract Thread R();

    public final void S(boolean z6) {
        this.f8094n = (z6 ? 4294967296L : 1L) + this.f8094n;
        if (z6) {
            return;
        }
        this.o = true;
    }

    public final boolean T() {
        return this.f8094n >= 4294967296L;
    }

    public abstract long U();

    public final boolean V() throws IllegalAccessException, InvocationTargetException {
        C1995j c1995j = this.f8095p;
        if (c1995j == null) {
            return false;
        }
        D d = (D) (c1995j.isEmpty() ? null : c1995j.removeFirst());
        if (d == null) {
            return false;
        }
        d.run();
        return true;
    }

    public void W(long j3, K k10) {
        RunnableC0728z.f8152u.b0(j3, k10);
    }

    public abstract void shutdown();
}
