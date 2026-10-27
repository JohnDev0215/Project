package n2;

/* JADX INFO: loaded from: classes.dex */
public final class a0 {
    public static final a0 c = new a0(0, false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f19622a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f19623b;

    public a0(int i7, boolean z6) {
        this.f19622a = i7;
        this.f19623b = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a0.class != obj.getClass()) {
            return false;
        }
        a0 a0Var = (a0) obj;
        return this.f19622a == a0Var.f19622a && this.f19623b == a0Var.f19623b;
    }

    public final int hashCode() {
        return (this.f19622a << 1) + (this.f19623b ? 1 : 0);
    }
}
