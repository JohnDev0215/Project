package spi;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.a;

/* JADX INFO: loaded from: classes.dex */
public final class ed extends u7<GifDrawable> {
    public ed(GifDrawable gifDrawable) {
        super(gifDrawable);
    }

    @Override // spi.wm
    public final int a() {
        com.bumptech.glide.load.resource.gif.a aVar = ((GifDrawable) this.a).a.a;
        return aVar.a.f() + aVar.p;
    }

    @Override // spi.wm
    @NonNull
    public final Class<GifDrawable> c() {
        return GifDrawable.class;
    }

    @Override // spi.u7, spi.pf
    public final void initialize() {
        ((GifDrawable) this.a).b().prepareToDraw();
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.ArrayList, java.util.List<com.bumptech.glide.load.resource.gif.a$b>] */
    @Override // spi.wm
    public final void recycle() {
        ((GifDrawable) this.a).stop();
        GifDrawable gifDrawable = (GifDrawable) this.a;
        gifDrawable.d = true;
        com.bumptech.glide.load.resource.gif.a aVar = gifDrawable.a.a;
        aVar.c.clear();
        Bitmap bitmap = aVar.l;
        if (bitmap != null) {
            aVar.e.c(bitmap);
            aVar.l = null;
        }
        aVar.f = false;
        a.C0020a c0020a = aVar.i;
        if (c0020a != null) {
            aVar.d.m(c0020a);
            aVar.i = null;
        }
        a.C0020a c0020a2 = aVar.k;
        if (c0020a2 != null) {
            aVar.d.m(c0020a2);
            aVar.k = null;
        }
        a.C0020a c0020a3 = aVar.n;
        if (c0020a3 != null) {
            aVar.d.m(c0020a3);
            aVar.n = null;
        }
        aVar.a.clear();
        aVar.j = true;
    }
}
