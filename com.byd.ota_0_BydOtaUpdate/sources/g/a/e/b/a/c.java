package g.a.e.b.a;

/* JADX INFO: loaded from: classes.dex */
public class c extends a {
    public int s;
    public int x;
    public g.a.e.d.a.a y;

    public c(int i2, int i3, g.a.e.d.a.a aVar, String str) {
        super(false, str);
        this.s = i2;
        this.x = i3;
        this.y = new g.a.e.d.a.a(aVar);
    }

    public g.a.e.d.a.a c() {
        return this.y;
    }

    public int d() {
        return this.y.b();
    }

    public int e() {
        return this.s;
    }

    public int f() {
        return this.x;
    }
}
