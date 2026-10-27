package defpackage;

/* JADX INFO: compiled from: FullscreenOptions.java */
/* JADX INFO: loaded from: classes.dex */
public final class sq0 {
    public final boolean a;
    public final boolean b;
    public boolean c;

    public sq0(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof sq0)) {
            return false;
        }
        sq0 sq0Var = (sq0) obj;
        return this.a == sq0Var.a && this.b == sq0Var.b;
    }

    public final String toString() {
        return "FullscreenOptions(showNavigationBar=" + this.a + ",showStatusBar=" + this.b + ", canceled=" + this.c + ")";
    }
}
