package h4;

/* JADX INFO: renamed from: h4.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1362d implements InterfaceC1365g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1363e f15513a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f15514b;
    public Class c;

    public C1362d(C1363e c1363e) {
        this.f15513a = c1363e;
    }

    @Override // h4.InterfaceC1365g
    public final void a() {
        this.f15513a.S0(this);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1362d)) {
            return false;
        }
        C1362d c1362d = (C1362d) obj;
        return this.f15514b == c1362d.f15514b && this.c == c1362d.c;
    }

    public final int hashCode() {
        int i7 = this.f15514b * 31;
        Class cls = this.c;
        return i7 + (cls != null ? cls.hashCode() : 0);
    }

    public final String toString() {
        return "Key{size=" + this.f15514b + "array=" + this.c + '}';
    }
}
