package g.a.d.c;

/* JADX INFO: loaded from: classes.dex */
public class c implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f5557a;

    public c(int[] iArr) {
        this.f5557a = g.a.f.a.i(iArr);
    }

    @Override // g.a.d.c.e
    public int[] a() {
        return g.a.f.a.i(this.f5557a);
    }

    @Override // g.a.d.c.e
    public int b() {
        return this.f5557a[r0.length - 1];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            return g.a.f.a.c(this.f5557a, ((c) obj).f5557a);
        }
        return false;
    }

    public int hashCode() {
        return g.a.f.a.B(this.f5557a);
    }
}
