package defpackage;

/* JADX INFO: compiled from: CompletionState.kt */
/* JADX INFO: loaded from: classes.dex */
public final class sv {
    public final Object a;
    public final jr0<Throwable, vi3> b;

    /* JADX WARN: Multi-variable type inference failed */
    public sv(Object obj, jr0<? super Throwable, vi3> jr0Var) {
        this.a = obj;
        this.b = jr0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof sv)) {
            return false;
        }
        sv svVar = (sv) obj;
        return i21.a(this.a, svVar.a) && i21.a(this.b, svVar.b);
    }

    public final int hashCode() {
        Object obj = this.a;
        return this.b.hashCode() + ((obj == null ? 0 : obj.hashCode()) * 31);
    }

    public final String toString() {
        return "CompletedWithCancellation(result=" + this.a + ", onCancellation=" + this.b + ')';
    }
}
