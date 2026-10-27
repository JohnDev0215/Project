package c.a.m.g;

import c.a.h;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final class d extends c.a.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final f f1875b = new f("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ThreadFactory f1876c;

    public d() {
        this(f1875b);
    }

    public d(ThreadFactory threadFactory) {
        this.f1876c = threadFactory;
    }

    @Override // c.a.h
    public h.b a() {
        return new e(this.f1876c);
    }
}
