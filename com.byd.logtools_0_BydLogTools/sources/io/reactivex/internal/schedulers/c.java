package io.reactivex.internal.schedulers;

import e.a.j;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: NewThreadScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final RxThreadFactory f5054b = new RxThreadFactory("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ThreadFactory f5055c;

    public c() {
        this(f5054b);
    }

    @Override // e.a.j
    public j.b a() {
        return new d(this.f5055c);
    }

    public c(ThreadFactory threadFactory) {
        this.f5055c = threadFactory;
    }
}
