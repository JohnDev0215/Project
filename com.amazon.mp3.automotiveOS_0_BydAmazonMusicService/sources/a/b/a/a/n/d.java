package a.b.a.a.n;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f88a;
    public double b;
    public final int c;
    public final int d;

    public d(int i, int i2) {
        this.c = i;
        this.d = i2;
        this.f88a = Math.max(0, Math.min(i2, i));
    }

    public /* synthetic */ d(int i, int i2, int i3) {
        this(i, (i3 & 2) != 0 ? 0 : i2);
    }

    public final void a(int i) {
        double d = this.b;
        int i2 = this.f88a;
        double d2 = (d * ((double) i2)) + ((double) i);
        int i3 = i2 + 1;
        this.b = d2 / ((double) i3);
        this.f88a = Math.min(i3, this.c);
    }
}
