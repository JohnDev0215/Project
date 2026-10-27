package io.reactivex.internal.schedulers;

import d.a.j;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: compiled from: NewThreadScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final RxThreadFactory f4325b = new RxThreadFactory("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ThreadFactory f4326c;

    public c() {
        this(f4325b);
    }

    @Override // d.a.j
    public j.b a() {
        return new d(this.f4326c);
    }

    public c(ThreadFactory threadFactory) {
        this.f4326c = threadFactory;
    }
}
