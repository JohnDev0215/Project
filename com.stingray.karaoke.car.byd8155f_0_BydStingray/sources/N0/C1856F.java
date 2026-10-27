package n0;

/* JADX INFO: renamed from: n0.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1856F extends AbstractC1858H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m0.d f19376a;

    public C1856F(m0.d dVar) {
        this.f19376a = dVar;
    }

    @Override // n0.AbstractC1858H
    public final m0.d a() {
        return this.f19376a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C1856F) {
            return A7.m.a(this.f19376a, ((C1856F) obj).f19376a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f19376a.hashCode();
    }
}
