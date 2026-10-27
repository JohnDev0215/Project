package e.a.n.g;

import e.a.h;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: NewThreadScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends e.a.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final f f4939b = new f("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ThreadFactory f4940c;

    public d() {
        this(f4939b);
    }

    @Override // e.a.h
    public h.b a() {
        return new e(this.f4940c);
    }

    public d(ThreadFactory threadFactory) {
        this.f4940c = threadFactory;
    }
}
