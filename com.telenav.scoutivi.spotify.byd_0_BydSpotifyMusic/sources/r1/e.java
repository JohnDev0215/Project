package r1;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: BitmapPoolAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements d {
    @Override // r1.d
    public void a(int i7) {
    }

    @Override // r1.d
    public void b() {
    }

    @Override // r1.d
    public Bitmap c(int i7, int i8, Bitmap.Config config) {
        return Bitmap.createBitmap(i7, i8, config);
    }

    @Override // r1.d
    public void d(Bitmap bitmap) {
        bitmap.recycle();
    }

    @Override // r1.d
    public Bitmap e(int i7, int i8, Bitmap.Config config) {
        return c(i7, i8, config);
    }
}
