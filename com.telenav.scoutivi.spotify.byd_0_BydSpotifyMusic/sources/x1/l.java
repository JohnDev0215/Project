package x1;

/* JADX INFO: compiled from: DownsampleStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f9764a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l f9765b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l f9766c = new e();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final l f9767d = new c();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final l f9768e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final l f9769f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final l f9770g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final n1.h<l> f9771h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static final boolean f9772i;

    /* JADX INFO: compiled from: DownsampleStrategy.java */
    private static class a extends l {
        a() {
        }

        @Override // x1.l
        public g a(int i7, int i8, int i9, int i10) {
            return g.QUALITY;
        }

        @Override // x1.l
        public float b(int i7, int i8, int i9, int i10) {
            if (Math.min(i8 / i10, i7 / i9) == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(r1);
        }
    }

    /* JADX INFO: compiled from: DownsampleStrategy.java */
    private static class b extends l {
        b() {
        }

        @Override // x1.l
        public g a(int i7, int i8, int i9, int i10) {
            return g.MEMORY;
        }

        @Override // x1.l
        public float b(int i7, int i8, int i9, int i10) {
            int iCeil = (int) Math.ceil(Math.max(i8 / i10, i7 / i9));
            return 1.0f / (r2 << (Math.max(1, Integer.highestOneBit(iCeil)) >= iCeil ? 0 : 1));
        }
    }

    /* JADX INFO: compiled from: DownsampleStrategy.java */
    private static class c extends l {
        c() {
        }

        @Override // x1.l
        public g a(int i7, int i8, int i9, int i10) {
            return b(i7, i8, i9, i10) == 1.0f ? g.QUALITY : l.f9766c.a(i7, i8, i9, i10);
        }

        @Override // x1.l
        public float b(int i7, int i8, int i9, int i10) {
            return Math.min(1.0f, l.f9766c.b(i7, i8, i9, i10));
        }
    }

    /* JADX INFO: compiled from: DownsampleStrategy.java */
    private static class d extends l {
        d() {
        }

        @Override // x1.l
        public g a(int i7, int i8, int i9, int i10) {
            return g.QUALITY;
        }

        @Override // x1.l
        public float b(int i7, int i8, int i9, int i10) {
            return Math.max(i9 / i7, i10 / i8);
        }
    }

    /* JADX INFO: compiled from: DownsampleStrategy.java */
    private static class e extends l {
        e() {
        }

        @Override // x1.l
        public g a(int i7, int i8, int i9, int i10) {
            return l.f9772i ? g.QUALITY : g.MEMORY;
        }

        @Override // x1.l
        public float b(int i7, int i8, int i9, int i10) {
            if (l.f9772i) {
                return Math.min(i9 / i7, i10 / i8);
            }
            if (Math.max(i8 / i10, i7 / i9) == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(r2);
        }
    }

    /* JADX INFO: compiled from: DownsampleStrategy.java */
    private static class f extends l {
        f() {
        }

        @Override // x1.l
        public g a(int i7, int i8, int i9, int i10) {
            return g.QUALITY;
        }

        @Override // x1.l
        public float b(int i7, int i8, int i9, int i10) {
            return 1.0f;
        }
    }

    /* JADX INFO: compiled from: DownsampleStrategy.java */
    public enum g {
        MEMORY,
        QUALITY
    }

    static {
        d dVar = new d();
        f9768e = dVar;
        f9769f = new f();
        f9770g = dVar;
        f9771h = n1.h.f("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", dVar);
        f9772i = true;
    }

    public abstract g a(int i7, int i8, int i9, int i10);

    public abstract float b(int i7, int i8, int i9, int i10);
}
