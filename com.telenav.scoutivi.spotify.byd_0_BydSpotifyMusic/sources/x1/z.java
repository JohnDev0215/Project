package x1;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: UnitBitmapDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class z implements n1.k<Bitmap, Bitmap> {

    /* JADX INFO: compiled from: UnitBitmapDecoder.java */
    private static final class a implements q1.v<Bitmap> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Bitmap f9822e;

        a(Bitmap bitmap) {
            this.f9822e = bitmap;
        }

        @Override // q1.v
        public void a() {
        }

        @Override // q1.v
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Bitmap get() {
            return this.f9822e;
        }

        @Override // q1.v
        public int c() {
            return k2.k.g(this.f9822e);
        }

        @Override // q1.v
        public Class<Bitmap> e() {
            return Bitmap.class;
        }
    }

    @Override // n1.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public q1.v<Bitmap> b(Bitmap bitmap, int i7, int i8, n1.i iVar) {
        return new a(bitmap);
    }

    @Override // n1.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(Bitmap bitmap, n1.i iVar) {
        return true;
    }
}
