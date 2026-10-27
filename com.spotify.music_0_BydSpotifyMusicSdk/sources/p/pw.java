package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class pw {
    public static final AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(pw.class, Object.class, "_next");
    public static final AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(pw.class, Object.class, "_prev");
    private volatile Object _next;
    private volatile Object _prev;

    public pw(pw pwVar) {
        this._prev = pwVar;
    }

    public final void a() {
        e.lazySet(this, null);
    }

    public final pw b() {
        Object obj = c.get(this);
        if (obj == ow.a) {
            return null;
        }
        return (pw) obj;
    }

    public abstract boolean c();

    public final void d() {
        pw pwVarB;
        if (b() == null) {
            return;
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
            pw pwVar = (pw) atomicReferenceFieldUpdater.get(this);
            while (pwVar != null && pwVar.c()) {
                pwVar = (pw) atomicReferenceFieldUpdater.get(pwVar);
            }
            pw pwVarB2 = b();
            v03.h(pwVarB2);
            while (pwVarB2.c() && (pwVarB = pwVarB2.b()) != null) {
                pwVarB2 = pwVarB;
            }
            while (true) {
                Object obj = atomicReferenceFieldUpdater.get(pwVarB2);
                pw pwVar2 = ((pw) obj) == null ? null : pwVar;
                while (!atomicReferenceFieldUpdater.compareAndSet(pwVarB2, obj, pwVar2)) {
                    if (atomicReferenceFieldUpdater.get(pwVarB2) != obj) {
                        break;
                    }
                }
            }
            if (pwVar != null) {
                c.set(pwVar, pwVarB2);
            }
            if (!pwVarB2.c() || pwVarB2.b() == null) {
                if (pwVar == null || !pwVar.c()) {
                    return;
                }
            }
        }
    }
}
