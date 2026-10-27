package kotlinx.coroutines;

/* JADX INFO: compiled from: EventLoop.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d extends z0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final Thread f7598l;

    public d(Thread thread) {
        this.f7598l = thread;
    }

    @Override // kotlinx.coroutines.a1
    protected Thread m0() {
        return this.f7598l;
    }
}
