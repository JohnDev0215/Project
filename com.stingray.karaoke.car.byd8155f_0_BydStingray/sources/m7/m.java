package m7;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class m implements f, Serializable {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19260n = AtomicReferenceFieldUpdater.newUpdater(m.class, Object.class, "m");
    public volatile InterfaceC2772a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile Object f19261m;

    @Override // m7.f
    public final Object getValue() {
        Object obj = this.f19261m;
        p pVar = p.f19266a;
        if (obj != pVar) {
            return obj;
        }
        InterfaceC2772a interfaceC2772a = this.l;
        if (interfaceC2772a != null) {
            Object objA = interfaceC2772a.a();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19260n;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, pVar, objA)) {
                if (atomicReferenceFieldUpdater.get(this) != pVar) {
                }
            }
            this.l = null;
            return objA;
        }
        return this.f19261m;
    }

    public final String toString() {
        return this.f19261m != p.f19266a ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
