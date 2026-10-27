package org.chromium.ui.resources.system;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import org.chromium.ui.resources.Resource;
import org.chromium.ui.resources.ResourceLoader;
import org.chromium.ui.resources.async.AsyncPreloadResourceLoader;
import org.chromium.ui.resources.statics.StaticResource;

/* JADX INFO: loaded from: classes4.dex */
public class SystemResourceLoader extends AsyncPreloadResourceLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final float COS_PI_OVER_6 = 0.866f;
    private static final float SIN_PI_OVER_6 = 0.5f;

    public SystemResourceLoader(int i, ResourceLoader.ResourceLoaderCallback resourceLoaderCallback, final int i2) {
        super(i, resourceLoaderCallback, new AsyncPreloadResourceLoader.ResourceCreator() { // from class: org.chromium.ui.resources.system.SystemResourceLoader.1
            @Override // org.chromium.ui.resources.async.AsyncPreloadResourceLoader.ResourceCreator
            public Resource create(int i3) {
                return SystemResourceLoader.createResource(i2, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Resource createResource(int i, int i2) {
        if (i2 == 0) {
            return StaticResource.create(Resources.getSystem(), getResourceId("android:drawable/overscroll_edge"), 128, 12);
        }
        if (i2 == 1) {
            return StaticResource.create(Resources.getSystem(), getResourceId("android:drawable/overscroll_glow"), 128, 64);
        }
        if (i2 != 2) {
            return null;
        }
        return createOverscrollGlowLBitmap(i);
    }

    private static Resource createOverscrollGlowLBitmap(int i) {
        float f = (i * SIN_PI_OVER_6) / SIN_PI_OVER_6;
        float f2 = COS_PI_OVER_6 * f;
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
        return new StaticResource(bitmapCreateBitmap);
    }

    private static int getResourceId(String str) {
        return Resources.getSystem().getIdentifier(str, null, null);
    }
}
