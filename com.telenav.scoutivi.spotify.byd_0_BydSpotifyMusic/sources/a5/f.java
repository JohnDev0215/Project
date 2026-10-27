package a5;

import java.util.concurrent.ThreadFactory;
import q4.d;

/* JADX INFO: compiled from: NewThreadScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends q4.d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final h f90c = new h("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ThreadFactory f91b;

    public f() {
        this(f90c);
    }

    @Override // q4.d
    public d.b a() {
        return new g(this.f91b);
    }

    public f(ThreadFactory threadFactory) {
        this.f91b = threadFactory;
    }
}
