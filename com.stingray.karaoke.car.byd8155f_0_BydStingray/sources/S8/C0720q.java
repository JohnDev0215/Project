package S8;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: renamed from: S8.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0720q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f8129b = AtomicIntegerFieldUpdater.newUpdater(C0720q.class, "_handled$volatile");
    private volatile /* synthetic */ int _handled$volatile;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f8130a;

    public C0720q(Throwable th, boolean z6) {
        this.f8130a = th;
        this._handled$volatile = z6 ? 1 : 0;
    }

    public final String toString() {
        return getClass().getSimpleName() + '[' + this.f8130a + ']';
    }
}
