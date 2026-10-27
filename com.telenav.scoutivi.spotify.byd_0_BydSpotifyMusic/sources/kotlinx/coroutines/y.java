package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: CompletionState.kt */
/* JADX INFO: loaded from: classes.dex */
public class y {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7850b = AtomicIntegerFieldUpdater.newUpdater(y.class, "_handled");
    private volatile int _handled;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f7851a;

    public y(Throwable th, boolean z6) {
        this.f7851a = th;
        this._handled = z6 ? 1 : 0;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public final boolean a() {
        return this._handled;
    }

    public final boolean b() {
        return f7850b.compareAndSet(this, 0, 1);
    }

    public String toString() {
        return m0.a(this) + '[' + this.f7851a + ']';
    }

    public /* synthetic */ y(Throwable th, boolean z6, int i7, kotlin.jvm.internal.g gVar) {
        this(th, (i7 & 2) != 0 ? false : z6);
    }
}
