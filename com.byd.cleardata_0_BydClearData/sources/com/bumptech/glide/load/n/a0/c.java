package com.bumptech.glide.load.n.a0;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
class c implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f2030a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h<a, Bitmap> f2031b = new h<>();

    static class a implements m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f2032a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2033b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2034c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Bitmap.Config f2035d;

        public a(b bVar) {
            this.f2032a = bVar;
        }

        @Override // com.bumptech.glide.load.n.a0.m
        public void a() {
            this.f2032a.c(this);
        }

        public void b(int i, int i2, Bitmap.Config config) {
            this.f2033b = i;
            this.f2034c = i2;
            this.f2035d = config;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f2033b == aVar.f2033b && this.f2034c == aVar.f2034c && this.f2035d == aVar.f2035d;
        }

        public int hashCode() {
            int i = ((this.f2033b * 31) + this.f2034c) * 31;
            Bitmap.Config config = this.f2035d;
            return i + (config != null ? config.hashCode() : 0);
        }

        public String toString() {
            return c.g(this.f2033b, this.f2034c, this.f2035d);
        }
    }

    static class b extends d<a> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bumptech.glide.load.n.a0.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public a a() {
            return new a(this);
        }

        a e(int i, int i2, Bitmap.Config config) {
            a aVarB = b();
            aVarB.b(i, i2, config);
            return aVarB;
        }
    }

    c() {
    }

    static String g(int i, int i2, Bitmap.Config config) {
        return "[" + i + "x" + i2 + "], " + config;
    }

    private static String h(Bitmap bitmap) {
        return g(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    @Override // com.bumptech.glide.load.n.a0.l
    public int a(Bitmap bitmap) {
        return com.bumptech.glide.t.k.g(bitmap);
    }

    @Override // com.bumptech.glide.load.n.a0.l
    public Bitmap b() {
        return this.f2031b.f();
    }

    @Override // com.bumptech.glide.load.n.a0.l
    public Bitmap c(int i, int i2, Bitmap.Config config) {
        return this.f2031b.a(this.f2030a.e(i, i2, config));
    }

    @Override // com.bumptech.glide.load.n.a0.l
    public void d(Bitmap bitmap) {
        this.f2031b.d(this.f2030a.e(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    @Override // com.bumptech.glide.load.n.a0.l
    public String e(int i, int i2, Bitmap.Config config) {
        return g(i, i2, config);
    }

    @Override // com.bumptech.glide.load.n.a0.l
    public String f(Bitmap bitmap) {
        return h(bitmap);
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.f2031b;
    }
}
