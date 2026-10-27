package c.b.h.e;

import c.b.c;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final class c extends c.b.c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f2278c = new e("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadFactory f2279b = f2278c;

    @Override // c.b.c
    public c.b a() {
        return new d(this.f2279b);
    }
}
