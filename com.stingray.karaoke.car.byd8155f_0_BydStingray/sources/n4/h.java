package n4;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import h4.InterfaceC1359a;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class h extends e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f19942b = "com.bumptech.glide.load.resource.bitmap.CenterCrop".getBytes(e4.e.f14430a);

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        messageDigest.update(f19942b);
    }

    @Override // n4.e
    public final Bitmap c(InterfaceC1359a interfaceC1359a, Bitmap bitmap, int i7, int i10) {
        float width;
        float height;
        Paint paint = y.f19972a;
        if (bitmap.getWidth() == i7 && bitmap.getHeight() == i10) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        float width2 = 0.0f;
        if (bitmap.getWidth() * i10 > bitmap.getHeight() * i7) {
            width = i10 / bitmap.getHeight();
            width2 = (i7 - (bitmap.getWidth() * width)) * 0.5f;
            height = 0.0f;
        } else {
            width = i7 / bitmap.getWidth();
            height = (i10 - (bitmap.getHeight() * width)) * 0.5f;
        }
        matrix.setScale(width, width);
        matrix.postTranslate((int) (width2 + 0.5f), (int) (height + 0.5f));
        Bitmap bitmapJ = interfaceC1359a.j(i7, i10, bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888);
        bitmapJ.setHasAlpha(bitmap.hasAlpha());
        y.a(bitmap, bitmapJ, matrix);
        return bitmapJ;
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        return obj instanceof h;
    }

    @Override // e4.e
    public final int hashCode() {
        return -599754482;
    }
}
