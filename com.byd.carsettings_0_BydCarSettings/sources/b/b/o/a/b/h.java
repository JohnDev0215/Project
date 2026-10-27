package b.b.o.a.b;

/* JADX INFO: compiled from: DoorImageFinder.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends a {
    @Override // b.b.o.a.b.a
    public int b(int i, int i2, int i3) {
        switch (i) {
            case 18:
                return e(i2, i3);
            case 19:
                return f(i2, i3);
            case 20:
                return h(i2, i3);
            case 21:
                return g(i2, i3);
            default:
                return d(i2);
        }
    }

    public final int e(int i, int i2) {
        return this.f2183a.q(i);
    }

    public final int f(int i, int i2) {
        return this.f2183a.F(i);
    }

    public final int g(int i, int i2) {
        return this.f2183a.n(i);
    }

    public final int h(int i, int i2) {
        return i2 != 0 ? i2 != 1 ? d(i) : i(i) : j(i);
    }

    public final int i(int i) {
        return this.f2183a.A(i);
    }

    public final int j(int i) {
        return this.f2183a.x(i);
    }
}
