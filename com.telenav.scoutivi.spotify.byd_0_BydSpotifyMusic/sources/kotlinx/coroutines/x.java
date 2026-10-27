package kotlinx.coroutines;

/* JADX INFO: compiled from: CancellableContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f7843a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f7844b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r5.l<Throwable, g5.r> f7845c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f7846d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Throwable f7847e;

    /* JADX WARN: Multi-variable type inference failed */
    public x(Object obj, g gVar, r5.l<? super Throwable, g5.r> lVar, Object obj2, Throwable th) {
        this.f7843a = obj;
        this.f7844b = gVar;
        this.f7845c = lVar;
        this.f7846d = obj2;
        this.f7847e = th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ x b(x xVar, Object obj, g gVar, r5.l lVar, Object obj2, Throwable th, int i7, Object obj3) {
        if ((i7 & 1) != 0) {
            obj = xVar.f7843a;
        }
        if ((i7 & 2) != 0) {
            gVar = xVar.f7844b;
        }
        g gVar2 = gVar;
        if ((i7 & 4) != 0) {
            lVar = xVar.f7845c;
        }
        r5.l lVar2 = lVar;
        if ((i7 & 8) != 0) {
            obj2 = xVar.f7846d;
        }
        Object obj4 = obj2;
        if ((i7 & 16) != 0) {
            th = xVar.f7847e;
        }
        return xVar.a(obj, gVar2, lVar2, obj4, th);
    }

    public final x a(Object obj, g gVar, r5.l<? super Throwable, g5.r> lVar, Object obj2, Throwable th) {
        return new x(obj, gVar, lVar, obj2, th);
    }

    public final boolean c() {
        return this.f7847e != null;
    }

    public final void d(k<?> kVar, Throwable th) {
        g gVar = this.f7844b;
        if (gVar != null) {
            kVar.l(gVar, th);
        }
        r5.l<Throwable, g5.r> lVar = this.f7845c;
        if (lVar != null) {
            kVar.n(lVar, th);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return kotlin.jvm.internal.l.a(this.f7843a, xVar.f7843a) && kotlin.jvm.internal.l.a(this.f7844b, xVar.f7844b) && kotlin.jvm.internal.l.a(this.f7845c, xVar.f7845c) && kotlin.jvm.internal.l.a(this.f7846d, xVar.f7846d) && kotlin.jvm.internal.l.a(this.f7847e, xVar.f7847e);
    }

    public int hashCode() {
        Object obj = this.f7843a;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * 31;
        g gVar = this.f7844b;
        int iHashCode2 = (iHashCode + (gVar != null ? gVar.hashCode() : 0)) * 31;
        r5.l<Throwable, g5.r> lVar = this.f7845c;
        int iHashCode3 = (iHashCode2 + (lVar != null ? lVar.hashCode() : 0)) * 31;
        Object obj2 = this.f7846d;
        int iHashCode4 = (iHashCode3 + (obj2 != null ? obj2.hashCode() : 0)) * 31;
        Throwable th = this.f7847e;
        return iHashCode4 + (th != null ? th.hashCode() : 0);
    }

    public String toString() {
        return "CompletedContinuation(result=" + this.f7843a + ", cancelHandler=" + this.f7844b + ", onCancellation=" + this.f7845c + ", idempotentResume=" + this.f7846d + ", cancelCause=" + this.f7847e + ")";
    }

    public /* synthetic */ x(Object obj, g gVar, r5.l lVar, Object obj2, Throwable th, int i7, kotlin.jvm.internal.g gVar2) {
        this(obj, (i7 & 2) != 0 ? null : gVar, (i7 & 4) != 0 ? null : lVar, (i7 & 8) != 0 ? null : obj2, (i7 & 16) != 0 ? null : th);
    }
}
