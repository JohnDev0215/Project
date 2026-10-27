package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlinx.coroutines.internal.z;

/* JADX INFO: compiled from: Semaphore.kt */
/* JADX INFO: loaded from: classes.dex */
final class e extends z<e> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    AtomicReferenceArray f7825e;

    public e(long j7, e eVar, int i7) {
        super(j7, eVar, i7);
        this.f7825e = new AtomicReferenceArray(d.f7824f);
    }

    @Override // kotlinx.coroutines.internal.z
    public int n() {
        return d.f7824f;
    }

    public final void q(int i7) {
        this.f7825e.set(i7, d.f7823e);
        o();
    }

    public String toString() {
        return "SemaphoreSegment[id=" + m() + ", hashCode=" + hashCode() + ']';
    }
}
