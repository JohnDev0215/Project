package com.airbnb.lottie.s;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.Nullable;
import com.airbnb.lottie.t.i;
import com.airbnb.lottie.w.d;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private final AssetManager d;

    @Nullable
    private com.airbnb.lottie.a e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final i<String> f773a = new i<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<i<String>, Typeface> f774b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, Typeface> f775c = new HashMap();
    private String f = ".ttf";

    public a(Drawable.Callback callback, @Nullable com.airbnb.lottie.a aVar) {
        AssetManager assets;
        if (callback instanceof View) {
            assets = ((View) callback).getContext().getAssets();
        } else {
            d.b("LottieDrawable must be inside of a view for images to work.");
            assets = null;
        }
        this.d = assets;
    }

    private Typeface a(String str) {
        Typeface typeface = this.f775c.get(str);
        if (typeface != null) {
            return typeface;
        }
        com.airbnb.lottie.a aVar = this.e;
        if (aVar != null || aVar != null) {
            throw null;
        }
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(this.d, "fonts/" + str + this.f);
        this.f775c.put(str, typefaceCreateFromAsset);
        return typefaceCreateFromAsset;
    }

    private Typeface d(Typeface typeface, String str) {
        boolean zContains = str.contains("Italic");
        boolean zContains2 = str.contains("Bold");
        int i = (zContains && zContains2) ? 3 : zContains ? 2 : zContains2 ? 1 : 0;
        return typeface.getStyle() == i ? typeface : Typeface.create(typeface, i);
    }

    public Typeface b(String str, String str2) {
        this.f773a.b(str, str2);
        Typeface typeface = this.f774b.get(this.f773a);
        if (typeface != null) {
            return typeface;
        }
        Typeface typefaceD = d(a(str), str2);
        this.f774b.put(this.f773a, typefaceD);
        return typefaceD;
    }

    public void c(@Nullable com.airbnb.lottie.a aVar) {
    }
}
