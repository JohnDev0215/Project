package com.airbnb.lottie.s;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import com.airbnb.lottie.g;
import com.airbnb.lottie.u.d;
import com.airbnb.lottie.u.h;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ImageAssetManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f2749a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f2750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2751c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.airbnb.lottie.b f2752d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Map<String, g> f2753e;

    public b(Drawable.Callback callback, String str, com.airbnb.lottie.b bVar, Map<String, g> map) {
        this.f2751c = str;
        if (!TextUtils.isEmpty(str)) {
            if (this.f2751c.charAt(r4.length() - 1) != '/') {
                this.f2751c += '/';
            }
        }
        if (callback instanceof View) {
            this.f2750b = ((View) callback).getContext();
            this.f2753e = map;
            d(bVar);
        } else {
            d.c("LottieDrawable must be inside of a view for images to work.");
            this.f2753e = new HashMap();
            this.f2750b = null;
        }
    }

    private Bitmap c(String str, Bitmap bitmap) {
        synchronized (f2749a) {
            this.f2753e.get(str).f(bitmap);
        }
        return bitmap;
    }

    public Bitmap a(String str) {
        g gVar = this.f2753e.get(str);
        if (gVar == null) {
            return null;
        }
        Bitmap bitmapA = gVar.a();
        if (bitmapA != null) {
            return bitmapA;
        }
        com.airbnb.lottie.b bVar = this.f2752d;
        if (bVar != null) {
            Bitmap bitmapA2 = bVar.a(gVar);
            if (bitmapA2 != null) {
                c(str, bitmapA2);
            }
            return bitmapA2;
        }
        String strB = gVar.b();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 160;
        if (strB.startsWith("data:") && strB.indexOf("base64,") > 0) {
            try {
                byte[] bArrDecode = Base64.decode(strB.substring(strB.indexOf(44) + 1), 0);
                return c(str, BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options));
            } catch (IllegalArgumentException e2) {
                d.d("data URL did not have correct base64 format.", e2);
                return null;
            }
        }
        try {
            if (TextUtils.isEmpty(this.f2751c)) {
                throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
            }
            return c(str, h.l(BitmapFactory.decodeStream(this.f2750b.getAssets().open(this.f2751c + strB), null, options), gVar.e(), gVar.c()));
        } catch (IOException e3) {
            d.d("Unable to open asset.", e3);
            return null;
        }
    }

    public boolean b(Context context) {
        return (context == null && this.f2750b == null) || this.f2750b.equals(context);
    }

    public void d(com.airbnb.lottie.b bVar) {
        this.f2752d = bVar;
    }
}
