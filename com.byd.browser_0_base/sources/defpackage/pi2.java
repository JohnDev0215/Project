package defpackage;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: SequencedTaskRunnerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class pi2 extends c73 implements oi2 {
    public final AtomicInteger l;
    public volatile boolean m;

    public pi2(d73 d73Var) {
        super(d73Var, "SequencedTaskRunnerImpl", 1);
        this.l = new AtomicInteger();
    }

    @Override // defpackage.c73
    public final void e() {
        this.m = true;
        if (this.l.getAndIncrement() == 0) {
            super.e();
        }
    }

    @Override // defpackage.c73
    public final void g() {
        super.g();
        if (this.l.decrementAndGet() > 0) {
            if (this.m) {
                super.e();
            } else {
                super.h();
            }
        }
    }

    @Override // defpackage.c73
    public final void h() {
        if (this.l.getAndIncrement() == 0) {
            super.h();
        }
    }
}
