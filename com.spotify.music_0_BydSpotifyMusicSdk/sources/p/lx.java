package p;

import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.internal.UndeliveredElementException;

/* JADX INFO: loaded from: classes.dex */
public final class lx extends kotlinx.coroutines.channels.a {
    public final BufferOverflow t;

    public lx(int i, BufferOverflow bufferOverflow, ry0 ry0Var) {
        super(i, ry0Var);
        this.t = bufferOverflow;
        if (bufferOverflow != BufferOverflow.c) {
            if (i < 1) {
                throw new IllegalArgumentException(u4.f("Buffered channel capacity must be at least 1, but ", i, " was specified").toString());
            }
        } else {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + r22.a(kotlinx.coroutines.channels.a.class).b() + " instead").toString());
        }
    }

    public final Object L(Object obj, boolean z) {
        er erVar;
        ry0 ry0Var;
        UndeliveredElementException undeliveredElementExceptionB;
        BufferOverflow bufferOverflow = BufferOverflow.k;
        lq2 lq2Var = lq2.a;
        if (this.t == bufferOverflow) {
            Object objN = super.n(obj);
            if ((!(objN instanceof cr)) || (objN instanceof br)) {
                return objN;
            }
            if (!z || (ry0Var = this.e) == null || (undeliveredElementExceptionB = kotlinx.coroutines.internal.b.b(ry0Var, obj, null)) == null) {
                return lq2Var;
            }
            throw undeliveredElementExceptionB;
        }
        Object obj2 = ho.d;
        er erVar2 = (er) kotlinx.coroutines.channels.a.o.get(this);
        while (true) {
            long andIncrement = kotlinx.coroutines.channels.a.k.getAndIncrement(this);
            long j = andIncrement & 1152921504606846975L;
            boolean zX = x(andIncrement, false);
            int i = ho.b;
            long j2 = i;
            long j3 = j / j2;
            int i2 = (int) (j % j2);
            if (erVar2.k != j3) {
                er erVarA = kotlinx.coroutines.channels.a.a(this, j3, erVar2);
                if (erVarA != null) {
                    erVar = erVarA;
                } else if (zX) {
                    return new br(u());
                }
            } else {
                erVar = erVar2;
            }
            int iC = kotlinx.coroutines.channels.a.c(this, erVar, i2, obj, j, obj2, zX);
            if (iC == 0) {
                erVar.a();
                return lq2Var;
            }
            if (iC == 1) {
                return lq2Var;
            }
            if (iC == 2) {
                if (zX) {
                    erVar.h();
                    return new br(u());
                }
                qv2 qv2Var = obj2 instanceof qv2 ? (qv2) obj2 : null;
                if (qv2Var != null) {
                    qv2Var.a(erVar, i2 + i);
                }
                p((erVar.k * j2) + ((long) i2));
                return lq2Var;
            }
            if (iC == 3) {
                throw new IllegalStateException("unexpected".toString());
            }
            if (iC == 4) {
                if (j < kotlinx.coroutines.channels.a.l.get(this)) {
                    erVar.a();
                }
                return new br(u());
            }
            if (iC == 5) {
                erVar.a();
            }
            erVar2 = erVar;
        }
    }

    @Override // kotlinx.coroutines.channels.a, p.hb2
    public final Object l(Object obj, c00 c00Var) throws Throwable {
        UndeliveredElementException undeliveredElementExceptionB;
        Object objL = L(obj, true);
        boolean z = objL instanceof br;
        if (!z) {
            return lq2.a;
        }
        if (z) {
        }
        ry0 ry0Var = this.e;
        if (ry0Var == null || (undeliveredElementExceptionB = kotlinx.coroutines.internal.b.b(ry0Var, obj, null)) == null) {
            throw u();
        }
        kotlin.a.a(undeliveredElementExceptionB, u());
        throw undeliveredElementExceptionB;
    }

    @Override // kotlinx.coroutines.channels.a, p.hb2
    public final Object n(Object obj) {
        return L(obj, false);
    }

    @Override // kotlinx.coroutines.channels.a
    public final boolean z() {
        return this.t == BufferOverflow.e;
    }
}
