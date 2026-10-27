package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/* JADX INFO: renamed from: fP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0772fP implements WO {
    public final /* synthetic */ int a;

    public C0772fP(int i) {
        this.a = i;
    }

    @Override // defpackage.WO
    public QO a(int i) {
        int i2 = this.a;
        if (i == 0) {
            return C0605cP.e(Resources.getSystem(), AbstractC1077kw.b(Resources.getSystem(), "android:drawable/overscroll_edge", null, null), 128, 12);
        }
        if (i == 1) {
            return C0605cP.e(Resources.getSystem(), AbstractC1077kw.b(Resources.getSystem(), "android:drawable/overscroll_glow", null, null), 128, 64);
        }
        if (i != 2) {
            return null;
        }
        float f = (i2 * 0.5f) / 0.5f;
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
        return new C0605cP(bitmapCreateBitmap);
    }
}
