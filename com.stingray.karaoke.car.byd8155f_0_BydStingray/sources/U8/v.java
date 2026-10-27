package U8;

import S8.v0;
import java.lang.reflect.InvocationTargetException;
import q7.InterfaceC2189c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class v extends l {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final EnumC0810a f9274x;

    public v(int i7, EnumC0810a enumC0810a, InterfaceC2782k interfaceC2782k) {
        super(i7, interfaceC2782k);
        this.f9274x = enumC0810a;
        if (enumC0810a != EnumC0810a.l) {
            if (i7 < 1) {
                throw new IllegalArgumentException(A7.k.g("Buffered channel capacity must be at least 1, but ", i7, " was specified").toString());
            }
        } else {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + A7.A.f443a.b(l.class).b() + " instead").toString());
        }
    }

    @Override // U8.l
    public final boolean C() {
        return this.f9274x == EnumC0810a.f9223m;
    }

    public final Object O(Object obj, boolean z6) throws IllegalAccessException, InvocationTargetException {
        t tVar;
        InterfaceC2782k interfaceC2782k;
        A9.k kVarB;
        EnumC0810a enumC0810a = EnumC0810a.f9224n;
        m7.q qVar = m7.q.f19267a;
        if (this.f9274x == enumC0810a) {
            Object objP = super.p(obj);
            if (!(objP instanceof r) || (objP instanceof q)) {
                return objP;
            }
            if (!z6 || (interfaceC2782k = this.f9248m) == null || (kVarB = X8.a.b(interfaceC2782k, obj, null)) == null) {
                return qVar;
            }
            throw kVarB;
        }
        h5.s sVar = n.d;
        t tVar2 = (t) l.f9243s.get(this);
        while (true) {
            long andIncrement = l.o.getAndIncrement(this);
            long j3 = andIncrement & 1152921504606846975L;
            boolean zA = A(andIncrement, false);
            int i7 = n.f9252b;
            long j10 = i7;
            long j11 = j3 / j10;
            int i10 = (int) (j3 % j10);
            if (tVar2.f10577n != j11) {
                t tVarB = l.b(this, j11, tVar2);
                if (tVarB != null) {
                    tVar = tVarB;
                } else if (zA) {
                    return new q(x());
                }
            } else {
                tVar = tVar2;
            }
            int iK = l.k(this, tVar, i10, obj, j3, sVar, zA);
            if (iK == 0) {
                tVar.b();
                return qVar;
            }
            if (iK == 1) {
                return qVar;
            }
            if (iK == 2) {
                if (zA) {
                    tVar.i();
                    return new q(x());
                }
                v0 v0Var = sVar instanceof v0 ? (v0) sVar : null;
                if (v0Var != null) {
                    v0Var.b(tVar, i10 + i7);
                }
                s((tVar.f10577n * j10) + ((long) i10));
                return qVar;
            }
            if (iK == 3) {
                throw new IllegalStateException("unexpected");
            }
            if (iK == 4) {
                if (j3 < l.f9240p.get(this)) {
                    tVar.b();
                }
                return new q(x());
            }
            if (iK == 5) {
                tVar.b();
            }
            tVar2 = tVar;
        }
    }

    @Override // U8.l, U8.A
    public final Object a(Object obj, InterfaceC2189c interfaceC2189c) throws Throwable {
        A9.k kVarB;
        if (!(O(obj, true) instanceof q)) {
            return m7.q.f19267a;
        }
        InterfaceC2782k interfaceC2782k = this.f9248m;
        if (interfaceC2782k == null || (kVarB = X8.a.b(interfaceC2782k, obj, null)) == null) {
            throw x();
        }
        h6.q.l(kVarB, x());
        throw kVarB;
    }

    @Override // U8.l, U8.A
    public final Object p(Object obj) {
        return O(obj, false);
    }
}
