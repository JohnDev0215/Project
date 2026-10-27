package org.chromium.chrome.browser.ui.favicon;

import J.N;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import defpackage.bk3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public final class FaviconHelper {
    public long a = N.MUcnJuRZ();

    public interface ComposedFaviconImageCallback {
        @CalledByNative
        void onComposedFaviconAvailable(Bitmap bitmap, GURL[] gurlArr);
    }

    public interface FaviconImageCallback {
        @CalledByNative
        void onFaviconAvailable(Bitmap bitmap, GURL gurl);
    }

    public static class a {
        public Bitmap a;
        public Bitmap b;
        public Bitmap c;

        public static Bitmap a(Resources resources, int i, boolean z) {
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(resources, i);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            int color = resources.getColor(z ? R$color.default_icon_color_baseline : R$color.default_icon_color_light);
            Paint paint = new Paint();
            paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmapDecodeResource, 0.0f, 0.0f, paint);
            return bitmapCreateBitmap;
        }

        public final Bitmap b(Resources resources, GURL gurl, boolean z) {
            Bitmap bitmapA;
            boolean zJ = bk3.j(gurl);
            Bitmap bitmap = zJ ? this.a : z ? this.b : this.c;
            if (bitmap != null) {
                return bitmap;
            }
            if (zJ) {
                bitmapA = BitmapFactory.decodeResource(resources, bk3.j(gurl) ? R$drawable.chromelogo16 : R$drawable.default_favicon);
            } else {
                bitmapA = a(resources, bk3.j(gurl) ? R$drawable.chromelogo16 : R$drawable.default_favicon, z);
            }
            if (zJ) {
                this.a = bitmapA;
            } else if (z) {
                this.b = bitmapA;
            } else {
                this.c = bitmapA;
            }
            return bitmapA;
        }
    }

    @Deprecated
    public final boolean a(Profile profile, String str, int i, FaviconImageCallback faviconImageCallback) {
        return N.MBZyBYDK(this.a, profile, str, i, faviconImageCallback);
    }
}
