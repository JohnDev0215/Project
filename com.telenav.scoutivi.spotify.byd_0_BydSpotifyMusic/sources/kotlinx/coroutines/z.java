package kotlinx.coroutines;

/* JADX INFO: compiled from: CompletionState.kt */
/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f7855a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r5.l<Throwable, g5.r> f7856b;

    /* JADX WARN: Multi-variable type inference failed */
    public z(Object obj, r5.l<? super Throwable, g5.r> lVar) {
        this.f7855a = obj;
        this.f7856b = lVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        return kotlin.jvm.internal.l.a(this.f7855a, zVar.f7855a) && kotlin.jvm.internal.l.a(this.f7856b, zVar.f7856b);
    }

    public int hashCode() {
        Object obj = this.f7855a;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * 31;
        r5.l<Throwable, g5.r> lVar = this.f7856b;
        return iHashCode + (lVar != null ? lVar.hashCode() : 0);
    }

    public String toString() {
        return "CompletedWithCancellation(result=" + this.f7855a + ", onCancellation=" + this.f7856b + ")";
    }
}
