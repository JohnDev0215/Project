package s7;

import A7.m;
import S8.AbstractC0721s;
import S8.C0712i;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import q7.C2190d;
import q7.InterfaceC2189c;
import q7.InterfaceC2191e;
import q7.InterfaceC2192f;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: s7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2410c extends AbstractC2408a {
    private final InterfaceC2194h _context;
    private transient InterfaceC2189c intercepted;

    public AbstractC2410c(InterfaceC2189c interfaceC2189c, InterfaceC2194h interfaceC2194h) {
        super(interfaceC2189c);
        this._context = interfaceC2194h;
    }

    @Override // q7.InterfaceC2189c
    public InterfaceC2194h getContext() {
        InterfaceC2194h interfaceC2194h = this._context;
        m.c(interfaceC2194h);
        return interfaceC2194h;
    }

    public final InterfaceC2189c intercepted() {
        InterfaceC2189c fVar = this.intercepted;
        if (fVar == null) {
            InterfaceC2191e interfaceC2191e = (InterfaceC2191e) getContext().F(C2190d.l);
            fVar = interfaceC2191e != null ? new X8.f((AbstractC0721s) interfaceC2191e, this) : this;
            this.intercepted = fVar;
        }
        return fVar;
    }

    @Override // s7.AbstractC2408a
    public void releaseIntercepted() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        InterfaceC2189c interfaceC2189c = this.intercepted;
        if (interfaceC2189c != null && interfaceC2189c != this) {
            InterfaceC2192f interfaceC2192fF = getContext().F(C2190d.l);
            m.c(interfaceC2192fF);
            X8.f fVar = (X8.f) interfaceC2189c;
            do {
                atomicReferenceFieldUpdater = X8.f.f10554s;
            } while (atomicReferenceFieldUpdater.get(fVar) == X8.a.c);
            Object obj = atomicReferenceFieldUpdater.get(fVar);
            C0712i c0712i = obj instanceof C0712i ? (C0712i) obj : null;
            if (c0712i != null) {
                c0712i.m();
            }
        }
        this.intercepted = C2409b.l;
    }

    public AbstractC2410c(InterfaceC2189c interfaceC2189c) {
        this(interfaceC2189c, interfaceC2189c != null ? interfaceC2189c.getContext() : null);
    }
}
