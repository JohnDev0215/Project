package b9;

import X8.r;
import java.util.concurrent.atomic.AtomicReferenceArray;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class k extends r {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ AtomicReferenceArray f12348p;

    public k(long j3, k kVar, int i7) {
        super(j3, kVar, i7);
        this.f12348p = new AtomicReferenceArray(j.f12347f);
    }

    @Override // X8.r
    public final int g() {
        return j.f12347f;
    }

    @Override // X8.r
    public final void h(int i7, InterfaceC2194h interfaceC2194h) {
        this.f12348p.set(i7, j.f12346e);
        i();
    }

    public final String toString() {
        return "SemaphoreSegment[id=" + this.f10577n + ", hashCode=" + hashCode() + ']';
    }
}
