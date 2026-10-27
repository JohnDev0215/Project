package g.a.e.b.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5648a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f5649b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5650c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5651d;

    public static abstract class a<T extends a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f5652a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5653b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f5654c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5655d = 0;

        public a(int i2) {
            this.f5652a = i2;
        }

        public abstract T e();

        public T f(int i2) {
            this.f5655d = i2;
            return (T) e();
        }

        public T g(int i2) {
            this.f5653b = i2;
            return (T) e();
        }

        public T h(long j) {
            this.f5654c = j;
            return (T) e();
        }
    }

    public k(a aVar) {
        this.f5648a = aVar.f5653b;
        this.f5649b = aVar.f5654c;
        this.f5650c = aVar.f5652a;
        this.f5651d = aVar.f5655d;
    }

    public final int a() {
        return this.f5651d;
    }

    public final int b() {
        return this.f5648a;
    }

    public final long c() {
        return this.f5649b;
    }

    public byte[] d() {
        byte[] bArr = new byte[32];
        g.a.f.f.d(this.f5648a, bArr, 0);
        g.a.f.f.q(this.f5649b, bArr, 4);
        g.a.f.f.d(this.f5650c, bArr, 12);
        g.a.f.f.d(this.f5651d, bArr, 28);
        return bArr;
    }
}
