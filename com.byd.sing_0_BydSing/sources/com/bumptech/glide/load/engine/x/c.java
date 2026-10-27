package com.bumptech.glide.load.engine.x;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: AttributeStrategy.java */
/* JADX INFO: loaded from: classes.dex */
class c implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f2844a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h<a, Bitmap> f2845b = new h<>();

    /* JADX INFO: compiled from: AttributeStrategy.java */
    static class a implements m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f2846a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2847b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2848c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Bitmap.Config f2849d;

        public a(b bVar) {
            this.f2846a = bVar;
        }

        @Override // com.bumptech.glide.load.engine.x.m
        public void a() {
            this.f2846a.c(this);
        }

        public void b(int i, int i2, Bitmap.Config config) {
            this.f2847b = i;
            this.f2848c = i2;
            this.f2849d = config;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f2847b == aVar.f2847b && this.f2848c == aVar.f2848c && this.f2849d == aVar.f2849d;
        }

        public int hashCode() {
            int i = ((this.f2847b * 31) + this.f2848c) * 31;
            Bitmap.Config config = this.f2849d;
            return i + (config != null ? config.hashCode() : 0);
        }

        public String toString() {
            return c.g(this.f2847b, this.f2848c, this.f2849d);
        }
    }

    /* JADX INFO: compiled from: AttributeStrategy.java */
    static class b extends d<a> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bumptech.glide.load.engine.x.d
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

    @Override // com.bumptech.glide.load.engine.x.l
    public String a(int i, int i2, Bitmap.Config config) {
        return g(i, i2, config);
    }

    @Override // com.bumptech.glide.load.engine.x.l
    public int b(Bitmap bitmap) {
        return com.bumptech.glide.m.k.g(bitmap);
    }

    @Override // com.bumptech.glide.load.engine.x.l
    public Bitmap c(int i, int i2, Bitmap.Config config) {
        return this.f2845b.a(this.f2844a.e(i, i2, config));
    }

    @Override // com.bumptech.glide.load.engine.x.l
    public void d(Bitmap bitmap) {
        this.f2845b.d(this.f2844a.e(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    @Override // com.bumptech.glide.load.engine.x.l
    public Bitmap e() {
        return this.f2845b.f();
    }

    @Override // com.bumptech.glide.load.engine.x.l
    public String f(Bitmap bitmap) {
        return h(bitmap);
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.f2845b;
    }
}
