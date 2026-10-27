package p;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class fv {
    public final Object a;
    public final sp b;
    public final ry0 c;
    public final Object d;
    public final Throwable e;

    public fv(Object obj, sp spVar, ry0 ry0Var, Object obj2, Throwable th) {
        this.a = obj;
        this.b = spVar;
        this.c = ry0Var;
        this.d = obj2;
        this.e = th;
    }

    public static fv a(fv fvVar, sp spVar, CancellationException cancellationException, int i) {
        Object obj = (i & 1) != 0 ? fvVar.a : null;
        if ((i & 2) != 0) {
            spVar = fvVar.b;
        }
        sp spVar2 = spVar;
        ry0 ry0Var = (i & 4) != 0 ? fvVar.c : null;
        Object obj2 = (i & 8) != 0 ? fvVar.d : null;
        Throwable th = cancellationException;
        if ((i & 16) != 0) {
            th = fvVar.e;
        }
        fvVar.getClass();
        return new fv(obj, spVar2, ry0Var, obj2, th);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof fv)) {
            return false;
        }
        fv fvVar = (fv) obj;
        return v03.b(this.a, fvVar.a) && v03.b(this.b, fvVar.b) && v03.b(this.c, fvVar.c) && v03.b(this.d, fvVar.d) && v03.b(this.e, fvVar.e);
    }

    public final int hashCode() {
        Object obj = this.a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        sp spVar = this.b;
        int iHashCode2 = (iHashCode + (spVar == null ? 0 : spVar.hashCode())) * 31;
        ry0 ry0Var = this.c;
        int iHashCode3 = (iHashCode2 + (ry0Var == null ? 0 : ry0Var.hashCode())) * 31;
        Object obj2 = this.d;
        int iHashCode4 = (iHashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.e;
        return iHashCode4 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.a + ", cancelHandler=" + this.b + ", onCancellation=" + this.c + ", idempotentResume=" + this.d + ", cancelCause=" + this.e + ')';
    }

    public /* synthetic */ fv(Object obj, sp spVar, ry0 ry0Var, CancellationException cancellationException, int i) {
        this(obj, (i & 2) != 0 ? null : spVar, (i & 4) != 0 ? null : ry0Var, (Object) null, (i & 16) != 0 ? null : cancellationException);
    }
}
