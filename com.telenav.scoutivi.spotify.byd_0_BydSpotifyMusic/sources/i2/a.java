package i2;

import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: DrawableCrossFadeFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e<Drawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f6910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f6911b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b f6912c;

    /* JADX INFO: renamed from: i2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DrawableCrossFadeFactory.java */
    public static class C0085a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f6913a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f6914b;

        public C0085a() {
            this(300);
        }

        public a a() {
            return new a(this.f6913a, this.f6914b);
        }

        public C0085a b(boolean z6) {
            this.f6914b = z6;
            return this;
        }

        public C0085a(int i7) {
            this.f6913a = i7;
        }
    }

    protected a(int i7, boolean z6) {
        this.f6910a = i7;
        this.f6911b = z6;
    }

    private d<Drawable> b() {
        if (this.f6912c == null) {
            this.f6912c = new b(this.f6910a, this.f6911b);
        }
        return this.f6912c;
    }

    @Override // i2.e
    public d<Drawable> a(n1.a aVar, boolean z6) {
        return aVar == n1.a.MEMORY_CACHE ? c.b() : b();
    }
}
