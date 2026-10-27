package p;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class gb2 extends cb2 {
    public final AtomicReferenceArray m;

    public gb2(long j, gb2 gb2Var, int i) {
        super(j, gb2Var, i);
        this.m = new AtomicReferenceArray(fb2.f);
    }

    @Override // p.cb2
    public final int f() {
        return fb2.f;
    }

    @Override // p.cb2
    public final void g(int i, y10 y10Var) {
        this.m.set(i, fb2.e);
        h();
    }

    public final String toString() {
        return "SemaphoreSegment[id=" + this.k + ", hashCode=" + hashCode() + ']';
    }
}
