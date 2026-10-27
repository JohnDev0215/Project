package s1;

import a2.d;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.HashMap;
import java.util.Map;
import t1.h;

/* JADX INFO: compiled from: FontAssetManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AssetManager f7886d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o1.b f7887e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h<String> f7883a = new h<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<h<String>, Typeface> f7884b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<String, Typeface> f7885c = new HashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f7888f = ".ttf";

    public a(Drawable.Callback callback, o1.b bVar) {
        if (callback instanceof View) {
            this.f7886d = ((View) callback).getContext().getAssets();
        } else {
            d.c("LottieDrawable must be inside of a view for images to work.");
            this.f7886d = null;
        }
    }

    public final Typeface a(String str) {
        Typeface typeface = this.f7885c.get(str);
        if (typeface != null) {
            return typeface;
        }
        o1.b bVar = this.f7887e;
        if (bVar != null || bVar != null) {
            throw null;
        }
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(this.f7886d, "fonts/" + str + this.f7888f);
        this.f7885c.put(str, typefaceCreateFromAsset);
        return typefaceCreateFromAsset;
    }

    public Typeface b(String str, String str2) {
        this.f7883a.b(str, str2);
        Typeface typeface = this.f7884b.get(this.f7883a);
        if (typeface != null) {
            return typeface;
        }
        Typeface typefaceD = d(a(str), str2);
        this.f7884b.put(this.f7883a, typefaceD);
        return typefaceD;
    }

    public void c(o1.b bVar) {
    }

    public final Typeface d(Typeface typeface, String str) {
        boolean zContains = str.contains("Italic");
        boolean zContains2 = str.contains("Bold");
        int i8 = (zContains && zContains2) ? 3 : zContains ? 2 : zContains2 ? 1 : 0;
        return typeface.getStyle() == i8 ? typeface : Typeface.create(typeface, i8);
    }
}
