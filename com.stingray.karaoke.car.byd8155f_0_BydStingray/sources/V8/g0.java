package V8;

import W8.AbstractC0842b;
import W8.AbstractC0843c;
import W8.AbstractC0844d;
import java.util.concurrent.atomic.AtomicReference;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends AbstractC0844d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference f9623a = new AtomicReference(null);

    @Override // W8.AbstractC0844d
    public final boolean a(AbstractC0842b abstractC0842b) {
        AtomicReference atomicReference = this.f9623a;
        if (atomicReference.get() != null) {
            return false;
        }
        atomicReference.set(AbstractC0828q.c);
        return true;
    }

    @Override // W8.AbstractC0844d
    public final InterfaceC2189c[] b(AbstractC0842b abstractC0842b) {
        this.f9623a.set(null);
        return AbstractC0843c.f9910a;
    }
}
