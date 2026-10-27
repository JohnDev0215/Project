package com.bumptech.glide.load.p.d;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f2331a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final i f2332b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final i f2333c = new e();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final i f2334d = new c();
    public static final i e;
    public static final i f;
    public static final i g;
    public static final com.bumptech.glide.load.h<i> h;
    static final boolean i;

    private static class a extends i {
        a() {
        }

        @Override // com.bumptech.glide.load.p.d.i
        public g a(int i, int i2, int i3, int i4) {
            return g.QUALITY;
        }

        @Override // com.bumptech.glide.load.p.d.i
        public float b(int i, int i2, int i3, int i4) {
            if (Math.min(i2 / i4, i / i3) == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(r1);
        }
    }

    private static class b extends i {
        b() {
        }

        @Override // com.bumptech.glide.load.p.d.i
        public g a(int i, int i2, int i3, int i4) {
            return g.MEMORY;
        }

        @Override // com.bumptech.glide.load.p.d.i
        public float b(int i, int i2, int i3, int i4) {
            int iCeil = (int) Math.ceil(Math.max(i2 / i4, i / i3));
            return 1.0f / (r2 << (Math.max(1, Integer.highestOneBit(iCeil)) >= iCeil ? 0 : 1));
        }
    }

    private static class c extends i {
        c() {
        }

        @Override // com.bumptech.glide.load.p.d.i
        public g a(int i, int i2, int i3, int i4) {
            return b(i, i2, i3, i4) == 1.0f ? g.QUALITY : i.f2333c.a(i, i2, i3, i4);
        }

        @Override // com.bumptech.glide.load.p.d.i
        public float b(int i, int i2, int i3, int i4) {
            return Math.min(1.0f, i.f2333c.b(i, i2, i3, i4));
        }
    }

    private static class d extends i {
        d() {
        }

        @Override // com.bumptech.glide.load.p.d.i
        public g a(int i, int i2, int i3, int i4) {
            return g.QUALITY;
        }

        @Override // com.bumptech.glide.load.p.d.i
        public float b(int i, int i2, int i3, int i4) {
            return Math.max(i3 / i, i4 / i2);
        }
    }

    private static class e extends i {
        e() {
        }

        @Override // com.bumptech.glide.load.p.d.i
        public g a(int i, int i2, int i3, int i4) {
            return i.i ? g.QUALITY : g.MEMORY;
        }

        @Override // com.bumptech.glide.load.p.d.i
        public float b(int i, int i2, int i3, int i4) {
            if (i.i) {
                return Math.min(i3 / i, i4 / i2);
            }
            if (Math.max(i2 / i4, i / i3) == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(r2);
        }
    }

    private static class f extends i {
        f() {
        }

        @Override // com.bumptech.glide.load.p.d.i
        public g a(int i, int i2, int i3, int i4) {
            return g.QUALITY;
        }

        @Override // com.bumptech.glide.load.p.d.i
        public float b(int i, int i2, int i3, int i4) {
            return 1.0f;
        }
    }

    public enum g {
        MEMORY,
        QUALITY
    }

    static {
        d dVar = new d();
        e = dVar;
        f = new f();
        g = dVar;
        h = com.bumptech.glide.load.h.f("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", dVar);
        i = Build.VERSION.SDK_INT >= 19;
    }

    public abstract g a(int i2, int i3, int i4, int i5);

    public abstract float b(int i2, int i3, int i4, int i5);
}
