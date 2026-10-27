package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import defpackage.b9;

/* JADX INFO: compiled from: StaticResourceLoader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ws2 implements b9.b {
    public final /* synthetic */ Resources a;

    public ws2(Resources resources) {
        this.a = resources;
    }

    @Override // b9.b
    public final vs2 a(int i) {
        if (i <= 0) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Resources resources = this.a;
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(resources, i, options);
        if (bitmapDecodeResource == null) {
            bitmapDecodeResource = null;
        } else if (bitmapDecodeResource.getConfig() != options.inPreferredConfig) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight(), options.inPreferredConfig);
            new Canvas(bitmapCreateBitmap).drawBitmap(bitmapDecodeResource, 0.0f, 0.0f, (Paint) null);
            bitmapDecodeResource.recycle();
            bitmapDecodeResource = bitmapCreateBitmap;
        }
        if (bitmapDecodeResource == null) {
            try {
                Drawable drawableC = c6.c(resources, i, 0);
                int iMax = Math.max(drawableC.getMinimumWidth(), Math.max(0, 1));
                int iMax2 = Math.max(drawableC.getMinimumHeight(), Math.max(0, 1));
                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(iMax, iMax2, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap2);
                drawableC.setBounds(0, 0, iMax, iMax2);
                drawableC.draw(canvas);
                bitmapDecodeResource = bitmapCreateBitmap2;
            } catch (Resources.NotFoundException unused) {
                bitmapDecodeResource = null;
            }
        }
        if (bitmapDecodeResource == null) {
            return null;
        }
        return new vs2(bitmapDecodeResource);
    }
}
