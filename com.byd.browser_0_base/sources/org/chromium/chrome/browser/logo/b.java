package org.chromium.chrome.browser.logo;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import defpackage.ax0;
import defpackage.cl;
import defpackage.dx0;
import defpackage.ll;
import defpackage.oj2;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.base.b;
import org.chromium.chrome.browser.logo.LogoBridge;
import org.chromium.chrome.browser.logo.LogoView;
import org.chromium.chrome.browser.logo.a;
import org.chromium.chrome.browser.logo.b;
import org.chromium.chrome.browser.logo.d;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: LogoMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements TemplateUrlService.c {
    public final PropertyModel e;
    public final Context f;
    public Profile g;
    public LogoBridge h;
    public ax0 i;
    public final Callback<LoadUrlParams> j;
    public final Callback<LogoBridge.Logo> k;
    public final Runnable l;
    public boolean m;
    public final boolean n;
    public boolean o;
    public final a.InterfaceC0093a p;
    public final cl q;
    public boolean r;
    public boolean s;
    public String t;
    public String u;
    public boolean v;
    public final org.chromium.base.b<a.InterfaceC0093a> w;

    /* JADX INFO: compiled from: LogoMediator.java */
    public class a implements LogoBridge.LogoObserver {
        public a() {
        }

        @Override // org.chromium.chrome.browser.logo.LogoBridge.LogoObserver
        public final void onCachedLogoRevalidated() {
            Runnable runnable = b.this.l;
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // org.chromium.chrome.browser.logo.LogoBridge.LogoObserver
        public final void onLogoAvailable(LogoBridge.Logo logo, boolean z) {
            final b bVar = b.this;
            if (logo == null) {
                if (z) {
                    return;
                } else {
                    bVar.e.p(d.j, bVar.a(bVar.f));
                }
            }
            bVar.e.p(d.g, new LogoView.c() { // from class: ka1
                @Override // org.chromium.chrome.browser.logo.LogoView.c
                public final void a(boolean z2) {
                    b bVar2 = bVar;
                    if (bVar2.h == null) {
                        return;
                    }
                    if (!z2 && bVar2.u != null) {
                        oj2.a0(1, "NewTabPage.LogoClick");
                        bVar2.e.p(d.k, Boolean.TRUE);
                        bVar2.i.c(ax0.a.b(bVar2.u, "NewTabPageAnimatedLogo"), new dm0(12, bVar2));
                    } else if (bVar2.t != null) {
                        oj2.a0(z2 ? 2 : 0, "NewTabPage.LogoClick");
                        bVar2.j.onResult(new LoadUrlParams(bVar2.t, 0));
                    }
                }
            });
            bVar.e.p(d.i, logo);
            Callback<LogoBridge.Logo> callback = bVar.k;
            if (callback != null) {
                callback.onResult(logo);
            }
        }
    }

    public b(Context context, Callback callback, PropertyModel propertyModel, boolean z, ll.b bVar, ll.c cVar, boolean z2, a.InterfaceC0093a interfaceC0093a) {
        cl clVar = org.chromium.chrome.browser.logo.a.d;
        this.v = true;
        org.chromium.base.b<a.InterfaceC0093a> bVar2 = new org.chromium.base.b<>();
        this.w = bVar2;
        this.f = context;
        this.e = propertyModel;
        this.j = callback;
        this.n = z;
        this.k = bVar;
        this.l = cVar;
        this.o = z2;
        this.p = interfaceC0093a;
        bVar2.a(interfaceC0093a);
        this.q = clVar;
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.c
    public final void M() {
        this.m = false;
        d(this.o, false, true);
    }

    public final Bitmap a(Context context) {
        Bitmap bitmapDecodeResource;
        if (!oj2.q(this.g).e()) {
            return null;
        }
        cl clVar = this.q;
        WeakReference<Bitmap> weakReference = clVar.c;
        Bitmap bitmap = weakReference != null ? weakReference.get() : null;
        int color = context.getColor(clVar.b);
        if (bitmap != null && clVar.d == color) {
            return bitmap;
        }
        Resources resources = context.getResources();
        int i = clVar.a;
        if (color == 0) {
            bitmapDecodeResource = BitmapFactory.decodeResource(resources, i);
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            bitmapDecodeResource = BitmapFactory.decodeResource(resources, i, options);
            Paint paint = new Paint();
            paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
            new Canvas(bitmapDecodeResource).drawBitmap(bitmapDecodeResource, 0.0f, 0.0f, paint);
        }
        clVar.c = new WeakReference<>(bitmapDecodeResource);
        clVar.d = color;
        return bitmapDecodeResource;
    }

    public final void b(boolean z) {
        if (this.m || this.g == null || !this.r) {
            return;
        }
        this.m = true;
        PropertyModel.n nVar = d.f;
        PropertyModel propertyModel = this.e;
        propertyModel.l(nVar, z);
        propertyModel.p(d.j, a(this.f));
        propertyModel.p(d.h, Boolean.TRUE);
        if (!oj2.q(this.g).e() || this.n) {
            if (this.h == null) {
                this.h = new LogoBridge(this.g);
                this.i = dx0.b(1, this.g.g());
            }
            c cVar = new c(this, System.currentTimeMillis(), new a());
            LogoBridge logoBridge = this.h;
            N.MzrOFrdr(logoBridge.a, logoBridge, cVar);
        }
    }

    public final void c() {
        Profile profile = this.g;
        boolean z = this.o && (profile != null ? oj2.q(profile).b() : SharedPreferencesManager.getInstance().readBoolean("Chrome.AppLaunch.SearchEngineHadLogo", true));
        this.r = z;
        this.e.l(d.e, z);
        Iterator<a.InterfaceC0093a> it = this.w.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a.InterfaceC0093a) aVar.next()).r();
            }
        }
    }

    public final void d(boolean z, boolean z2, boolean z3) {
        LogoBridge logoBridge;
        this.o = z;
        c();
        if (this.r) {
            if (this.g != null) {
                b(z3);
                return;
            } else {
                this.s = true;
                return;
            }
        }
        if (!z2 || (logoBridge = this.h) == null) {
            return;
        }
        this.m = false;
        if (logoBridge != null) {
            N.MwFfD4qv(logoBridge.a, logoBridge);
            logoBridge.a = 0L;
            this.h = null;
            this.i.b();
            this.i = null;
        }
    }
}
