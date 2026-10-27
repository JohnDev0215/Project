package s1;

import a2.d;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import o1.c;
import o1.h;

/* JADX INFO: compiled from: ImageAssetManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f7889a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f7890b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f7891c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f7892d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<String, h> f7893e;

    public b(Drawable.Callback callback, String str, c cVar, Map<String, h> map) {
        this.f7891c = str;
        if (!TextUtils.isEmpty(str)) {
            if (this.f7891c.charAt(r4.length() - 1) != '/') {
                this.f7891c += '/';
            }
        }
        if (callback instanceof View) {
            this.f7890b = ((View) callback).getContext();
            this.f7893e = map;
            d(cVar);
        } else {
            d.c("LottieDrawable must be inside of a view for images to work.");
            this.f7893e = new HashMap();
            this.f7890b = null;
        }
    }

    public Bitmap a(String str) {
        h hVar = this.f7893e.get(str);
        if (hVar == null) {
            return null;
        }
        Bitmap bitmapA = hVar.a();
        if (bitmapA != null) {
            return bitmapA;
        }
        c cVar = this.f7892d;
        if (cVar != null) {
            Bitmap bitmapA2 = cVar.a(hVar);
            if (bitmapA2 != null) {
                c(str, bitmapA2);
            }
            return bitmapA2;
        }
        String strB = hVar.b();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 160;
        if (strB.startsWith("data:") && strB.indexOf("base64,") > 0) {
            try {
                byte[] bArrDecode = Base64.decode(strB.substring(strB.indexOf(44) + 1), 0);
                return c(str, BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options));
            } catch (IllegalArgumentException e8) {
                d.d("data URL did not have correct base64 format.", e8);
                return null;
            }
        }
        try {
            if (TextUtils.isEmpty(this.f7891c)) {
                throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
            }
            return c(str, a2.h.l(BitmapFactory.decodeStream(this.f7890b.getAssets().open(this.f7891c + strB), null, options), hVar.e(), hVar.c()));
        } catch (IOException e9) {
            d.d("Unable to open asset.", e9);
            return null;
        }
    }

    public boolean b(Context context) {
        return (context == null && this.f7890b == null) || this.f7890b.equals(context);
    }

    public final Bitmap c(String str, Bitmap bitmap) {
        synchronized (f7889a) {
            this.f7893e.get(str).f(bitmap);
        }
        return bitmap;
    }

    public void d(c cVar) {
        this.f7892d = cVar;
    }
}
