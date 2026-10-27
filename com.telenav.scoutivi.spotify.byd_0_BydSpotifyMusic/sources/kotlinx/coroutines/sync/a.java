package kotlinx.coroutines.sync;

import g5.r;
import kotlinx.coroutines.g;

/* JADX INFO: compiled from: Semaphore.kt */
/* JADX INFO: loaded from: classes.dex */
final class a extends g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final e f7809e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f7810g;

    public a(e eVar, int i7) {
        this.f7809e = eVar;
        this.f7810g = i7;
    }

    @Override // kotlinx.coroutines.h
    public void a(Throwable th) {
        this.f7809e.q(this.f7810g);
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ r invoke(Throwable th) {
        a(th);
        return r.f6563a;
    }

    public String toString() {
        return "CancelSemaphoreAcquisitionHandler[" + this.f7809e + ", " + this.f7810g + ']';
    }
}
