package S8;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: renamed from: S8.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0713j extends C0720q {
    public static final /* synthetic */ AtomicIntegerFieldUpdater c = AtomicIntegerFieldUpdater.newUpdater(C0713j.class, "_resumed$volatile");
    private volatile /* synthetic */ int _resumed$volatile;

    public C0713j(C0712i c0712i, Throwable th, boolean z6) {
        if (th == null) {
            th = new CancellationException("Continuation " + c0712i + " was cancelled normally");
        }
        super(th, z6);
        this._resumed$volatile = 0;
    }
}
