package S8;

/* JADX INFO: loaded from: classes.dex */
public final class H implements S {
    public final boolean l;

    public H(boolean z6) {
        this.l = z6;
    }

    @Override // S8.S
    public final boolean b() {
        return this.l;
    }

    @Override // S8.S
    public final f0 d() {
        return null;
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("Empty{"), this.l ? "Active" : "New", '}');
    }
}
