package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import defpackage.xt2;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.chrome.browser.ui.favicon.a;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ig2 implements FaviconHelper.FaviconImageCallback, LargeIconBridge.LargeIconCallback {
    public final /* synthetic */ Resources e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ ig2(jg2 jg2Var, s62 s62Var, int i, String str, Resources resources) {
        this.g = jg2Var;
        this.h = s62Var;
        this.f = i;
        this.i = str;
        this.e = resources;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
    public final void onFaviconAvailable(Bitmap bitmap, GURL gurl) {
        int i;
        jg2 jg2Var = (jg2) this.g;
        s62 s62Var = (s62) this.h;
        String str = (String) this.i;
        if (bitmap == null) {
            jg2Var.getClass();
            s62Var.b(new xt2.b(R$drawable.ic_search, kp2.a()));
            jg2.c(3);
            return;
        }
        jg2Var.getClass();
        Resources resources = this.e;
        int iB = jg2.b(resources);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, jg2.b(resources), jg2.b(resources), true);
        if (jg2.h == 0) {
            jg2.h = resources.getDimensionPixelSize(R$dimen.omnibox_search_engine_logo_composed_size);
        }
        int i2 = jg2.h;
        if (jg2Var.c == null) {
            jg2Var.c = new uc2(i2, i2, i2, 0, 0.0f);
        }
        int i3 = 0;
        if (bitmap.getWidth() != 0 && bitmap.getHeight() != 0) {
            HashMap map = new HashMap();
            int i4 = 0;
            while (true) {
                i = -1;
                if (i4 >= bitmap.getWidth()) {
                    break;
                }
                int pixel = bitmap.getPixel(i4, 0);
                if (!map.containsKey(Integer.valueOf(pixel))) {
                    map.put(Integer.valueOf(pixel), 0);
                }
                map.put(Integer.valueOf(pixel), Integer.valueOf(((Integer) map.get(Integer.valueOf(pixel))).intValue() + 1));
                int pixel2 = bitmap.getPixel(i4, bitmap.getHeight() - 1);
                if (!map.containsKey(Integer.valueOf(pixel2))) {
                    map.put(Integer.valueOf(pixel2), 0);
                }
                map.put(Integer.valueOf(pixel2), Integer.valueOf(((Integer) map.get(Integer.valueOf(pixel2))).intValue() + 1));
                if (i4 > 0 && i4 < bitmap.getWidth() - 1) {
                    int pixel3 = bitmap.getPixel(0, i4);
                    if (!map.containsKey(Integer.valueOf(pixel3))) {
                        map.put(Integer.valueOf(pixel3), 0);
                    }
                    map.put(Integer.valueOf(pixel3), Integer.valueOf(((Integer) map.get(Integer.valueOf(pixel3))).intValue() + 1));
                    int pixel4 = bitmap.getPixel(bitmap.getWidth() - 1, i4);
                    if (!map.containsKey(Integer.valueOf(pixel4))) {
                        map.put(Integer.valueOf(pixel4), 0);
                    }
                    map.put(Integer.valueOf(pixel4), Integer.valueOf(((Integer) map.get(Integer.valueOf(pixel4))).intValue() + 1));
                }
                i4++;
            }
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                int iIntValue = ((Integer) it.next()).intValue();
                int iIntValue2 = ((Integer) map.get(Integer.valueOf(iIntValue))).intValue();
                if (iIntValue2 > i) {
                    i3 = iIntValue;
                    i = iIntValue2;
                }
            }
        }
        jg2Var.c.e.setColor(i3);
        Bitmap bitmapA = jg2Var.c.a("");
        float f = (i2 - iB) / 2;
        new Canvas(bitmapA).drawBitmap(bitmapCreateScaledBitmap, f, f, (Paint) null);
        jg2.e = bitmapA;
        jg2.f = str;
        s62Var.b(new xt2.b(bitmapA));
        jg2.c(5);
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        GURL gurl = (GURL) this.g;
        Context context = (Context) this.h;
        ((Callback) this.i).onResult(a.f(bitmap, gurl, i, a.a(context), this.e, this.f));
    }

    public /* synthetic */ ig2(GURL gurl, Context context, Resources resources, int i, Callback callback) {
        this.g = gurl;
        this.h = context;
        this.e = resources;
        this.f = i;
        this.i = callback;
    }
}
