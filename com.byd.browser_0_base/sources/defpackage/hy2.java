package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import defpackage.b9;

/* JADX INFO: compiled from: SystemResourceLoader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hy2 implements b9.b {
    public final /* synthetic */ int a;

    public hy2(int i) {
        this.a = i;
    }

    @Override // b9.b
    public final vs2 a(int i) {
        if (i != 0) {
            return null;
        }
        float f = (this.a * 0.5f) / 0.5f;
        float f2 = 0.866f * f;
        float f3 = -f;
        float f4 = f3 / 2.0f;
        float f5 = f3 - f2;
        float f6 = 2.0f * f;
        RectF rectF = new RectF(f4, f5, f4 + f6, f6 + f5);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAlpha(187);
        paint.setStyle(Paint.Style.FILL);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap((int) f, (int) (f - f2), Bitmap.Config.ALPHA_8);
        new Canvas(bitmapCreateBitmap).drawArc(rectF, 45.0f, 90.0f, true, paint);
        return new vs2(bitmapCreateBitmap);
    }
}
