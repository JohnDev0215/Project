package Q;

/* JADX INFO: loaded from: classes.dex */
public final class S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final J.d f5659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final J.d f5660b;
    public final J.d c;
    public final J.d d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final J.d f5661e;

    public S() {
        J.d dVar = Q.f5651a;
        J.d dVar2 = Q.f5652b;
        J.d dVar3 = Q.c;
        J.d dVar4 = Q.d;
        J.d dVar5 = Q.f5653e;
        this.f5659a = dVar;
        this.f5660b = dVar2;
        this.c = dVar3;
        this.d = dVar4;
        this.f5661e = dVar5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof S)) {
            return false;
        }
        S s6 = (S) obj;
        return A7.m.a(this.f5659a, s6.f5659a) && A7.m.a(this.f5660b, s6.f5660b) && A7.m.a(this.c, s6.c) && A7.m.a(this.d, s6.d) && A7.m.a(this.f5661e, s6.f5661e);
    }

    public final int hashCode() {
        return this.f5661e.hashCode() + ((this.d.hashCode() + ((this.c.hashCode() + ((this.f5660b.hashCode() + (this.f5659a.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Shapes(extraSmall=" + this.f5659a + ", small=" + this.f5660b + ", medium=" + this.c + ", large=" + this.d + ", extraLarge=" + this.f5661e + ')';
    }
}
