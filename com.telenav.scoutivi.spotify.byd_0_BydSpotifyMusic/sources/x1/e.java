package x1;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: BitmapResource.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements q1.v<Bitmap>, q1.r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Bitmap f9753e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final r1.d f9754g;

    public e(Bitmap bitmap, r1.d dVar) {
        this.f9753e = (Bitmap) k2.j.e(bitmap, "Bitmap must not be null");
        this.f9754g = (r1.d) k2.j.e(dVar, "BitmapPool must not be null");
    }

    public static e f(Bitmap bitmap, r1.d dVar) {
        if (bitmap == null) {
            return null;
        }
        return new e(bitmap, dVar);
    }

    @Override // q1.v
    public void a() {
        this.f9754g.d(this.f9753e);
    }

    @Override // q1.r
    public void b() {
        this.f9753e.prepareToDraw();
    }

    @Override // q1.v
    public int c() {
        return k2.k.g(this.f9753e);
    }

    @Override // q1.v
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Bitmap get() {
        return this.f9753e;
    }

    @Override // q1.v
    public Class<Bitmap> e() {
        return Bitmap.class;
    }
}
