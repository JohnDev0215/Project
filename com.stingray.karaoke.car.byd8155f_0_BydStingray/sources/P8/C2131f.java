package p8;

/* JADX INFO: renamed from: p8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2131f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC2126a f20976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f20977b;

    public C2131f(int i7, AbstractC2126a abstractC2126a) {
        this.f20976a = abstractC2126a;
        this.f20977b = i7;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C2131f)) {
            return false;
        }
        C2131f c2131f = (C2131f) obj;
        return this.f20976a == c2131f.f20976a && this.f20977b == c2131f.f20977b;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.f20976a) * 65535) + this.f20977b;
    }
}
