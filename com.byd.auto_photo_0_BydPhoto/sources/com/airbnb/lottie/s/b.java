package com.airbnb.lottie.s;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import androidx.annotation.Nullable;
import com.airbnb.lottie.g;
import com.airbnb.lottie.w.d;
import com.airbnb.lottie.w.h;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f776a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f777b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f778c;

    @Nullable
    private com.airbnb.lottie.b d;
    private final Map<String, g> e;

    public b(Drawable.Callback callback, String str, com.airbnb.lottie.b bVar, Map<String, g> map) {
        this.f778c = str;
        if (!TextUtils.isEmpty(str)) {
            if (this.f778c.charAt(r4.length() - 1) != '/') {
                this.f778c += '/';
            }
        }
        if (callback instanceof View) {
            this.f777b = ((View) callback).getContext();
            this.e = map;
            d(bVar);
        } else {
            d.b("LottieDrawable must be inside of a view for images to work.");
            this.e = new HashMap();
            this.f777b = null;
        }
    }

    private Bitmap c(String str, @Nullable Bitmap bitmap) {
        synchronized (f776a) {
            this.e.get(str).f(bitmap);
        }
        return bitmap;
    }

    @Nullable
    public Bitmap a(String str) {
        String str2;
        Bitmap bitmapL;
        g gVar = this.e.get(str);
        if (gVar == null) {
            return null;
        }
        Bitmap bitmapA = gVar.a();
        if (bitmapA != null) {
            return bitmapA;
        }
        com.airbnb.lottie.b bVar = this.d;
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
                bitmapL = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options);
                return c(str, bitmapL);
            } catch (IllegalArgumentException e) {
                e = e;
                str2 = "data URL did not have correct base64 format.";
                d.c(str2, e);
                return null;
            }
        }
        try {
            if (TextUtils.isEmpty(this.f778c)) {
                throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
            }
            bitmapL = h.l(BitmapFactory.decodeStream(this.f777b.getAssets().open(this.f778c + strB), null, options), gVar.e(), gVar.c());
            return c(str, bitmapL);
        } catch (IOException e2) {
            e = e2;
            str2 = "Unable to open asset.";
            d.c(str2, e);
            return null;
        }
    }

    public boolean b(Context context) {
        return (context == null && this.f777b == null) || this.f777b.equals(context);
    }

    public void d(@Nullable com.airbnb.lottie.b bVar) {
        this.d = bVar;
    }
}
