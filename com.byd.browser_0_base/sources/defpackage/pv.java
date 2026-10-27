package defpackage;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: CancellableContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class pv {
    public final Object a;
    public final wl b;
    public final jr0<Throwable, vi3> c;
    public final Object d;
    public final Throwable e;

    /* JADX WARN: Multi-variable type inference failed */
    public pv(Object obj, wl wlVar, jr0<? super Throwable, vi3> jr0Var, Object obj2, Throwable th) {
        this.a = obj;
        this.b = wlVar;
        this.c = jr0Var;
        this.d = obj2;
        this.e = th;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pv)) {
            return false;
        }
        pv pvVar = (pv) obj;
        return i21.a(this.a, pvVar.a) && i21.a(this.b, pvVar.b) && i21.a(this.c, pvVar.c) && i21.a(this.d, pvVar.d) && i21.a(this.e, pvVar.e);
    }

    public final int hashCode() {
        Object obj = this.a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        wl wlVar = this.b;
        int iHashCode2 = (iHashCode + (wlVar == null ? 0 : wlVar.hashCode())) * 31;
        jr0<Throwable, vi3> jr0Var = this.c;
        int iHashCode3 = (iHashCode2 + (jr0Var == null ? 0 : jr0Var.hashCode())) * 31;
        Object obj2 = this.d;
        int iHashCode4 = (iHashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.e;
        return iHashCode4 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.a + ", cancelHandler=" + this.b + ", onCancellation=" + this.c + ", idempotentResume=" + this.d + ", cancelCause=" + this.e + ')';
    }

    public /* synthetic */ pv(Object obj, wl wlVar, jr0 jr0Var, CancellationException cancellationException, int i) {
        this(obj, (i & 2) != 0 ? null : wlVar, (jr0<? super Throwable, vi3>) ((i & 4) != 0 ? null : jr0Var), (Object) null, (i & 16) != 0 ? null : cancellationException);
    }
}
