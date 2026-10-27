package p2;

/* JADX INFO: renamed from: p2.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2091f {
    public static final C2091f d = new V2.c().a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f20709a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f20710b;
    public final boolean c;

    public C2091f(V2.c cVar) {
        this.f20709a = cVar.f9334a;
        this.f20710b = cVar.f9335b;
        this.c = cVar.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2091f.class != obj.getClass()) {
            return false;
        }
        C2091f c2091f = (C2091f) obj;
        return this.f20709a == c2091f.f20709a && this.f20710b == c2091f.f20710b && this.c == c2091f.c;
    }

    public final int hashCode() {
        return ((this.f20709a ? 1 : 0) << 2) + ((this.f20710b ? 1 : 0) << 1) + (this.c ? 1 : 0);
    }
}
