package B5;

/* JADX INFO: loaded from: classes.dex */
public final class B {
    public static final B c = new B(0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f643a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f644b;

    public B(int i7, int i10) {
        this.f643a = i7;
        this.f644b = i10;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(B.class.getSimpleName());
        sb.append("[position = ");
        sb.append(this.f643a);
        sb.append(", length = ");
        return M5.b.h(sb, this.f644b, "]");
    }
}
