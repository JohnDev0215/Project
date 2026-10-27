package g.a.e.b.e;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f5656a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f5657b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5658c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5659d;

    public l(int i2, int i3, g.a.b.n nVar) {
        this.f5658c = i2;
        this.f5659d = i3;
        this.f5657b = new q(h(i2, i3), nVar);
        this.f5656a = a.b(a().getAlgorithmName(), b(), f(), e(), c(), i3);
    }

    public static int h(int i2, int i3) throws IllegalArgumentException {
        if (i2 < 2) {
            throw new IllegalArgumentException("totalHeight must be > 1");
        }
        if (i2 % i3 != 0) {
            throw new IllegalArgumentException("layers must divide totalHeight without remainder");
        }
        int i4 = i2 / i3;
        if (i4 != 1) {
            return i4;
        }
        throw new IllegalArgumentException("height / layers must be greater than 1");
    }

    public g.a.b.n a() {
        return this.f5657b.b();
    }

    public int b() {
        return this.f5657b.c();
    }

    public int c() {
        return this.f5658c;
    }

    public int d() {
        return this.f5659d;
    }

    public int e() {
        return this.f5657b.f().d().c();
    }

    public int f() {
        return this.f5657b.g();
    }

    public q g() {
        return this.f5657b;
    }
}
