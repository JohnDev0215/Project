package p;

/* JADX INFO: loaded from: classes.dex */
public final class sm1 {
    public final /* synthetic */ int a;
    public int b;
    public int c;

    public /* synthetic */ sm1(int i, int i2, int i3) {
        this.a = i3;
        this.b = i;
        this.c = i2;
    }

    public final String toString() {
        switch (this.a) {
            case 1:
                StringBuilder sb = new StringBuilder("Location(line = ");
                sb.append(this.b);
                sb.append(", column = ");
                return u4.k(sb, this.c, ')');
            default:
                return super.toString();
        }
    }

    public sm1() {
        this.a = 0;
    }
}
