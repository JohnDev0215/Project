package d.a.p.g;

import d.a.j;
import io.reactivex.internal.schedulers.RxThreadFactory;

/* JADX INFO: compiled from: NewThreadScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f3233c = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final RxThreadFactory f3232b = new RxThreadFactory("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    public static c d() {
        return f3233c;
    }

    @Override // d.a.j
    public j.b a() {
        return new d(f3232b);
    }
}
