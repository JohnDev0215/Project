package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class x61 extends xc {
    public final kotlinx.coroutines.internal.a b;
    public kotlinx.coroutines.internal.a c;
    public final /* synthetic */ kotlinx.coroutines.g d;
    public final /* synthetic */ Object e;

    public x61(kotlinx.coroutines.internal.a aVar, kotlinx.coroutines.g gVar, Object obj) {
        this.d = gVar;
        this.e = obj;
        this.b = aVar;
    }

    @Override // p.xc
    public final void b(Object obj, Object obj2) {
        kotlinx.coroutines.internal.a aVar = (kotlinx.coroutines.internal.a) obj;
        boolean z = obj2 == null;
        kotlinx.coroutines.internal.a aVar2 = this.b;
        kotlinx.coroutines.internal.a aVar3 = z ? aVar2 : this.c;
        if (aVar3 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = kotlinx.coroutines.internal.a.c;
            while (!atomicReferenceFieldUpdater.compareAndSet(aVar, this, aVar3)) {
                if (atomicReferenceFieldUpdater.get(aVar) != this) {
                    return;
                }
            }
            if (z) {
                kotlinx.coroutines.internal.a aVar4 = this.c;
                v03.h(aVar4);
                aVar2.f(aVar4);
            }
        }
    }

    @Override // p.xc
    public final tz2 c(Object obj) {
        if (this.d.I() == this.e) {
            return null;
        }
        return dc1.a;
    }
}
