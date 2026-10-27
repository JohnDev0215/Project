package com.airbnb.lottie.s;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.airbnb.lottie.model.h;
import com.airbnb.lottie.u.d;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: FontAssetManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final AssetManager f3127d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.airbnb.lottie.a f3128e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h<String> f3124a = new h<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<h<String>, Typeface> f3125b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, Typeface> f3126c = new HashMap();
    private String f = ".ttf";

    public a(Drawable.Callback callback, com.airbnb.lottie.a aVar) {
        if (callback instanceof View) {
            this.f3127d = ((View) callback).getContext().getAssets();
        } else {
            d.b("LottieDrawable must be inside of a view for images to work.");
            this.f3127d = null;
        }
    }

    private Typeface a(String str) {
        Typeface typeface = this.f3126c.get(str);
        if (typeface != null) {
            return typeface;
        }
        com.airbnb.lottie.a aVar = this.f3128e;
        if (aVar != null || aVar != null) {
            throw null;
        }
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(this.f3127d, "fonts/" + str + this.f);
        this.f3126c.put(str, typefaceCreateFromAsset);
        return typefaceCreateFromAsset;
    }

    private Typeface d(Typeface typeface, String str) {
        boolean zContains = str.contains("Italic");
        boolean zContains2 = str.contains("Bold");
        int i = (zContains && zContains2) ? 3 : zContains ? 2 : zContains2 ? 1 : 0;
        return typeface.getStyle() == i ? typeface : Typeface.create(typeface, i);
    }

    public Typeface b(String str, String str2) {
        this.f3124a.b(str, str2);
        Typeface typeface = this.f3125b.get(this.f3124a);
        if (typeface != null) {
            return typeface;
        }
        Typeface typefaceD = d(a(str), str2);
        this.f3125b.put(this.f3124a, typefaceD);
        return typefaceD;
    }

    public void c(com.airbnb.lottie.a aVar) {
    }
}
