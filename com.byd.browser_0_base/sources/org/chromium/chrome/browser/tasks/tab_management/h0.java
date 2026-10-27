package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.ao3;
import defpackage.bk3;
import defpackage.n3;
import defpackage.qc2;
import java.util.Arrays;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabListFaviconProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h0 {
    public static b l;
    public static b m;
    public static b n;
    public static b o;
    public static c p;
    public static b q;
    public static b r;
    public static c s;
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final boolean g;
    public final Context h;
    public boolean i;
    public Profile j;
    public FaviconHelper k;

    /* JADX INFO: compiled from: TabListFaviconProvider.java */
    public static class a extends c {
        public final GURL[] c;

        public a(Drawable drawable, GURL[] gurlArr) {
            super(drawable, drawable, false);
            this.c = gurlArr;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            return Arrays.equals(this.c, ((a) obj).c);
        }

        public final int hashCode() {
            return Arrays.hashCode(this.c);
        }
    }

    /* JADX INFO: compiled from: TabListFaviconProvider.java */
    public static class b extends c {
        public final int c;

        public b() {
            throw null;
        }

        public b(Drawable drawable, Drawable drawable2, boolean z, int i) {
            super(drawable, drawable2, z);
            this.c = i;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof b) {
                return this.c == ((b) obj).c;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.c);
        }
    }

    /* JADX INFO: compiled from: TabListFaviconProvider.java */
    public static abstract class c {
        public final Drawable a;
        public final Drawable b;

        public c(Drawable drawable, Drawable drawable2, boolean z) {
            this.a = drawable;
            this.b = drawable2;
        }
    }

    /* JADX INFO: compiled from: TabListFaviconProvider.java */
    public interface d {
        void a(Callback<c> callback);
    }

    /* JADX INFO: compiled from: TabListFaviconProvider.java */
    public static class e extends c {
        public final GURL c;

        public e() {
            throw null;
        }

        public e(Drawable drawable, Drawable drawable2, boolean z, GURL gurl) {
            super(drawable, drawable2, z);
            this.c = gurl;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof e)) {
                return false;
            }
            return Objects.equals(this.c, ((e) obj).c);
        }

        public final int hashCode() {
            return this.c.hashCode();
        }
    }

    public h0(Context context, boolean z) {
        this.h = context;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.tab_grid_favicon_size);
        this.d = dimensionPixelSize;
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.tab_strip_favicon_size);
        this.c = dimensionPixelSize2;
        this.e = z ? dimensionPixelSize2 : dimensionPixelSize;
        this.f = ao3.a(context, context.getResources().getDimensionPixelSize(R$dimen.tab_strip_favicon_inset));
        this.g = z;
        int iT = n3.T(context, false, false);
        int iT2 = n3.T(context, false, true);
        this.a = iT2;
        int iT3 = n3.T(context, true, false);
        int iT4 = n3.T(context, true, true);
        this.b = iT4;
        if (l == null) {
            l = b(d(dimensionPixelSize, AppCompatResources.a(context, R$drawable.ic_globe_24dp)), iT, iT2, 1);
        }
        if (m == null) {
            Drawable drawableA = AppCompatResources.a(context, R$drawable.chromelogo16);
            m = new b(drawableA, drawableA, false, 8);
        }
        if (n == null) {
            n = b(d(dimensionPixelSize, AppCompatResources.a(context, R$drawable.ic_group_icon_16dp)), iT, iT2, 3);
        }
        if (o == null) {
            o = b(d(dimensionPixelSize, AppCompatResources.a(context, R$drawable.ic_globe_24dp)), iT3, iT4, 4);
        }
        if (p == null) {
            p = m;
        }
        if (q == null) {
            q = b(d(dimensionPixelSize, AppCompatResources.a(context, R$drawable.ic_group_icon_16dp)), iT3, iT4, 6);
        }
        if (r == null) {
            Drawable drawableG = g(d(dimensionPixelSize2, AppCompatResources.a(context, R$drawable.ic_globe_24dp)), true);
            r = new b(drawableG, drawableG, false, 7);
        }
        if (s == null) {
            s = m;
        }
    }

    public static Bitmap d(int i, Drawable drawable) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, i, i);
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public final void a(b bVar) {
        Context context = this.h;
        bVar.a.setColorFilter(new PorterDuffColorFilter(n3.T(context, false, false), PorterDuff.Mode.SRC_IN));
        Drawable drawable = bVar.a;
        Drawable drawable2 = bVar.b;
        if (drawable != drawable2) {
            drawable2.setColorFilter(new PorterDuffColorFilter(n3.T(context, false, true), PorterDuff.Mode.SRC_IN));
        }
    }

    public final b b(Bitmap bitmap, int i, int i2, int i3) {
        return new b(h(bitmap, true, i), h(bitmap, true, i2), true, i3);
    }

    public final void c(final GURL gurl, final boolean z, final Callback<c> callback) {
        if (this.k == null || bk3.l(gurl)) {
            callback.onResult(this.g ? s : z ? p : m);
            return;
        }
        FaviconHelper faviconHelper = this.k;
        Profile profileF = !z ? this.j : this.j.f(false);
        FaviconHelper.FaviconImageCallback faviconImageCallback = new FaviconHelper.FaviconImageCallback() { // from class: o6
            @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
            public final void onFaviconAvailable(Bitmap bitmap, GURL gurl2) {
                h0.c eVar;
                h0 h0Var = (h0) this;
                GURL gurl3 = (GURL) gurl;
                Callback callback2 = (Callback) callback;
                boolean z2 = z;
                if (bitmap == null) {
                    eVar = h0Var.e(z2);
                } else {
                    h0Var.getClass();
                    boolean zJ = bk3.j(gurl3);
                    boolean z3 = h0Var.g;
                    if (!zJ || z3) {
                        Drawable drawableG = h0Var.g(bitmap, z3);
                        eVar = new h0.e(drawableG, drawableG, false, gurl2);
                    } else {
                        Bitmap bitmapD = h0.d(h0Var.d, h0Var.g(bitmap, false));
                        eVar = z2 ? new h0.e(h0Var.h(bitmapD, false, 0), h0Var.h(bitmapD, true, h0Var.b), true, gurl2) : new h0.e(h0Var.h(bitmapD, false, 0), h0Var.h(bitmapD, true, h0Var.a), true, gurl2);
                    }
                }
                callback2.onResult(eVar);
            }
        };
        faviconHelper.getClass();
        faviconHelper.a(profileF, gurl.j(), this.e, faviconImageCallback);
    }

    public final b e(boolean z) {
        if (this.g) {
            return r;
        }
        if (z) {
            return o;
        }
        b bVar = l;
        a(bVar);
        return bVar;
    }

    public final void f(Profile profile) {
        if (this.i) {
            return;
        }
        this.j = profile;
        this.k = new FaviconHelper();
        this.i = true;
    }

    public final Drawable g(Bitmap bitmap, boolean z) {
        int i = z ? this.c : this.d;
        Context context = this.h;
        qc2 qc2VarC = org.chromium.chrome.browser.ui.favicon.a.c(context.getResources(), Bitmap.createScaledBitmap(bitmap, i, i, true));
        if (!z) {
            return qc2VarC;
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{AppCompatResources.a(context, R$drawable.tab_strip_favicon_circle), qc2VarC});
        int i2 = this.f;
        layerDrawable.setLayerInset(1, i2, i2, i2, i2);
        return layerDrawable;
    }

    public final Drawable h(Bitmap bitmap, boolean z, int i) {
        Drawable drawableG = g(bitmap, false);
        if (z) {
            drawableG.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        }
        return drawableG;
    }
}
