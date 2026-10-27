package W8;

import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import s7.InterfaceC2411d;

/* JADX INFO: loaded from: classes.dex */
public final class D implements InterfaceC2189c, InterfaceC2411d {
    public final InterfaceC2189c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC2194h f9905m;

    public D(InterfaceC2189c interfaceC2189c, InterfaceC2194h interfaceC2194h) {
        this.l = interfaceC2189c;
        this.f9905m = interfaceC2194h;
    }

    @Override // s7.InterfaceC2411d
    public final InterfaceC2411d getCallerFrame() {
        InterfaceC2189c interfaceC2189c = this.l;
        if (interfaceC2189c instanceof InterfaceC2411d) {
            return (InterfaceC2411d) interfaceC2189c;
        }
        return null;
    }

    @Override // q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        return this.f9905m;
    }

    @Override // q7.InterfaceC2189c
    public final void resumeWith(Object obj) {
        this.l.resumeWith(obj);
    }
}
