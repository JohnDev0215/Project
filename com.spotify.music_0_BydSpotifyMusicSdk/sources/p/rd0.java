package p;

/* JADX INFO: loaded from: classes.dex */
public final class rd0 implements e41 {
    public final boolean c;

    public rd0(boolean z) {
        this.c = z;
    }

    @Override // p.e41
    public final boolean b() {
        return this.c;
    }

    @Override // p.e41
    public final pn1 c() {
        return null;
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Empty{"), this.c ? "Active" : "New", '}');
    }
}
