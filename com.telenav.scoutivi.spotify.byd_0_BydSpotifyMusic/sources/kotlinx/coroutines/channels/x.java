package kotlinx.coroutines.channels;

/* JADX INFO: compiled from: LinkedListChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public class x<E> extends a<E> {
    public x(r5.l<? super E, g5.r> lVar) {
        super(lVar);
    }

    @Override // kotlinx.coroutines.channels.a
    protected final boolean E() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.a
    protected final boolean F() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.c
    protected final boolean n() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.c
    protected final boolean o() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.c
    protected Object q(E e7) {
        d0<?> d0VarS;
        do {
            Object objQ = super.q(e7);
            kotlinx.coroutines.internal.c0 c0Var = b.f7542b;
            if (objQ == c0Var) {
                return c0Var;
            }
            if (objQ != b.f7543c) {
                if (objQ instanceof r) {
                    return objQ;
                }
                throw new IllegalStateException(("Invalid offerInternal result " + objQ).toString());
            }
            d0VarS = s(e7);
            if (d0VarS == null) {
                return c0Var;
            }
        } while (!(d0VarS instanceof r));
        return d0VarS;
    }
}
