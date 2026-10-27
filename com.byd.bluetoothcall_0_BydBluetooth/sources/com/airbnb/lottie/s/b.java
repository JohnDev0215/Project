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
    private static final Object f3129a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f3130b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3131c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.airbnb.lottie.b f3132d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Map<String, g> f3133e;

    public b(Drawable.Callback callback, String str, com.airbnb.lottie.b bVar, Map<String, g> map) {
        this.f3131c = str;
        if (!TextUtils.isEmpty(str)) {
            if (this.f3131c.charAt(r4.length() - 1) != '/') {
                this.f3131c += '/';
            }
        }
        if (callback instanceof View) {
            this.f3130b = ((View) callback).getContext();
            this.f3133e = map;
            d(bVar);
        } else {
            d.b("LottieDrawable must be inside of a view for images to work.");
            this.f3133e = new HashMap();
            this.f3130b = null;
        }
    }

    private Bitmap c(String str, Bitmap bitmap) {
        synchronized (f3129a) {
            this.f3133e.get(str).f(bitmap);
        }
        return bitmap;
    }

    public Bitmap a(String str) {
        g gVar = this.f3133e.get(str);
        if (gVar == null) {
            return null;
        }
        Bitmap bitmapA = gVar.a();
        if (bitmapA != null) {
            return bitmapA;
        }
        com.airbnb.lottie.b bVar = this.f3132d;
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
                d.c("data URL did not have correct base64 format.", e2);
                return null;
            }
        }
        try {
            if (TextUtils.isEmpty(this.f3131c)) {
                throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
            }
            return c(str, h.l(BitmapFactory.decodeStream(this.f3130b.getAssets().open(this.f3131c + strB), null, options), gVar.e(), gVar.c()));
        } catch (IOException e3) {
            d.c("Unable to open asset.", e3);
            return null;
        }
    }

    public boolean b(Context context) {
        return (context == null && this.f3130b == null) || this.f3130b.equals(context);
    }

    public void d(com.airbnb.lottie.b bVar) {
        this.f3132d = bVar;
    }
}
