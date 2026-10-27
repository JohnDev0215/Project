package P8;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class a implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference f5458a;

    public a(k kVar) {
        this.f5458a = new AtomicReference(kVar);
    }

    @Override // P8.k
    public final Iterator iterator() {
        k kVar = (k) this.f5458a.getAndSet(null);
        if (kVar != null) {
            return kVar.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
