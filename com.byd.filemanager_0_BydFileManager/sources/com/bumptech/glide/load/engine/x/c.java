package com.bumptech.glide.load.engine.x;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: AttributeStrategy.java */
/* JADX INFO: loaded from: classes.dex */
class c implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f3091a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h<a, Bitmap> f3092b = new h<>();

    /* JADX INFO: compiled from: AttributeStrategy.java */
    static class a implements m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f3093a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3094b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3095c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Bitmap.Config f3096d;

        public a(b bVar) {
            this.f3093a = bVar;
        }

        @Override // com.bumptech.glide.load.engine.x.m
        public void a() {
            this.f3093a.c(this);
        }

        public void b(int i, int i2, Bitmap.Config config) {
            this.f3094b = i;
            this.f3095c = i2;
            this.f3096d = config;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f3094b == aVar.f3094b && this.f3095c == aVar.f3095c && this.f3096d == aVar.f3096d;
        }

        public int hashCode() {
            int i = ((this.f3094b * 31) + this.f3095c) * 31;
            Bitmap.Config config = this.f3096d;
            return i + (config != null ? config.hashCode() : 0);
        }

        public String toString() {
            return c.g(this.f3094b, this.f3095c, this.f3096d);
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
    public int a(Bitmap bitmap) {
        return com.bumptech.glide.o.k.g(bitmap);
    }

    @Override // com.bumptech.glide.load.engine.x.l
    public Bitmap b() {
        return this.f3092b.f();
    }

    @Override // com.bumptech.glide.load.engine.x.l
    public Bitmap c(int i, int i2, Bitmap.Config config) {
        return this.f3092b.a(this.f3091a.e(i, i2, config));
    }

    @Override // com.bumptech.glide.load.engine.x.l
    public void d(Bitmap bitmap) {
        this.f3092b.d(this.f3091a.e(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    @Override // com.bumptech.glide.load.engine.x.l
    public String e(int i, int i2, Bitmap.Config config) {
        return g(i, i2, config);
    }

    @Override // com.bumptech.glide.load.engine.x.l
    public String f(Bitmap bitmap) {
        return h(bitmap);
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.f3092b;
    }
}
