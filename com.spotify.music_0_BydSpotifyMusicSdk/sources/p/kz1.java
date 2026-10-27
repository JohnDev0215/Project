package p;

/* JADX INFO: loaded from: classes.dex */
public final class kz1 {
    public static final kz1 c = new kz1(0, 0);
    public final int a;
    public final int b;

    public kz1(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(kz1.class.getSimpleName());
        sb.append("[position = ");
        sb.append(this.a);
        sb.append(", length = ");
        return u4.l(sb, this.b, "]");
    }
}
