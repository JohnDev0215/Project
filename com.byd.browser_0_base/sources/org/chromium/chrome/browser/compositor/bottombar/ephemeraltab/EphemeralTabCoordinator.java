package org.chromium.chrome.browser.compositor.bottombar.ephemeraltab;

import J.N;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.ao3;
import defpackage.bh0;
import defpackage.fz2;
import defpackage.g3;
import defpackage.ii;
import defpackage.ji;
import defpackage.n3;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.pq3;
import defpackage.wq0;
import defpackage.wv;
import defpackage.xe3;
import defpackage.xh0;
import defpackage.xr;
import defpackage.z2;
import defpackage.zh0;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.d;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public final class EphemeralTabCoordinator implements View.OnLayoutChangeListener {
    public final Context e;
    public final g3 f;
    public final View g;
    public final z2 h;
    public final nw2<fz2> i;
    public final ji j;
    public final c k;
    public final boolean l;
    public WebContents m;
    public ContentView n;
    public zh0 o;
    public a p;
    public GURL q;
    public GURL r;
    public int s;
    public boolean t;
    public boolean u;

    public static class FaviconLoader {
        public final Context a;
        public final FaviconHelper b = new FaviconHelper();
        public final int c;

        public FaviconLoader(Context context) {
            this.a = context;
            org.chromium.chrome.browser.ui.favicon.a.a(context);
            this.c = context.getResources().getDimensionPixelSize(R$dimen.preview_tab_favicon_size);
        }
    }

    public class a extends bh0 {
        public a() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void a(float f) {
            EphemeralTabCoordinator ephemeralTabCoordinator = EphemeralTabCoordinator.this;
            zh0 zh0Var = ephemeralTabCoordinator.o;
            if (zh0Var != null && ephemeralTabCoordinator.l) {
                View viewFindViewById = zh0Var.l.findViewById(R$id.open_in_new_tab);
                if (f <= 0.5f) {
                    if (viewFindViewById.getVisibility() != 8) {
                        viewFindViewById.setVisibility(8);
                    }
                } else {
                    if (viewFindViewById.getVisibility() != 0) {
                        viewFindViewById.setVisibility(0);
                    }
                    viewFindViewById.setAlpha((f - 0.5f) * 2.0f);
                }
            }
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void b(ii iiVar) {
            EphemeralTabCoordinator ephemeralTabCoordinator = EphemeralTabCoordinator.this;
            if (iiVar != ephemeralTabCoordinator.o) {
                ephemeralTabCoordinator.o = null;
                ephemeralTabCoordinator.t = false;
                ephemeralTabCoordinator.u = false;
                WebContents webContents = ephemeralTabCoordinator.m;
                if (webContents != null) {
                    webContents.destroy();
                    ephemeralTabCoordinator.m = null;
                    ephemeralTabCoordinator.n = null;
                }
                c cVar = ephemeralTabCoordinator.k;
                b bVar = cVar.g;
                if (bVar != null) {
                    bVar.destroy();
                    cVar.g = null;
                }
                cVar.getClass();
                cVar.e = null;
                cVar.f = null;
                cVar.h = null;
                cVar.c.clear();
                ephemeralTabCoordinator.g.removeOnLayoutChangeListener(ephemeralTabCoordinator);
                a aVar = ephemeralTabCoordinator.p;
                if (aVar != null) {
                    ephemeralTabCoordinator.j.e(aVar);
                }
            }
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void d(int i, int i2) {
            EphemeralTabCoordinator ephemeralTabCoordinator = EphemeralTabCoordinator.this;
            if (ephemeralTabCoordinator.o == null) {
                return;
            }
            if (i == 1) {
                if (ephemeralTabCoordinator.t) {
                    return;
                }
                ephemeralTabCoordinator.t = true;
            } else if (i == 3 && !ephemeralTabCoordinator.u) {
                ephemeralTabCoordinator.u = true;
            }
        }
    }

    public EphemeralTabCoordinator(Context context, g3 g3Var, View view, z2 z2Var, nw2<fz2> nw2Var, ji jiVar, boolean z) {
        this.e = context;
        this.f = g3Var;
        this.g = view;
        this.h = z2Var;
        this.i = nw2Var;
        this.j = jiVar;
        this.l = z;
        this.k = new c(jiVar, new FaviconLoader(context), (int) (context.getResources().getDimensionPixelSize(R$dimen.toolbar_height_no_shadow) / g3Var.h.d));
    }

    public final void a(GURL gurl, GURL gurl2, String str, boolean z) {
        Profile profileH0;
        this.q = gurl;
        this.r = gurl2;
        g3 g3Var = this.f;
        if (z) {
            profileH0 = n3.h0(g3Var);
            if (profileH0 == null) {
                profileH0 = Profile.d().f(true);
            }
        } else {
            profileH0 = Profile.d();
        }
        WebContents webContents = this.m;
        c cVar = this.k;
        if (webContents == null) {
            WebContents webContentsA = pq3.a(profileH0, true);
            this.m = webContentsA;
            ContentView contentView = new ContentView(this.e, webContentsA);
            this.n = contentView;
            this.m.i(new ViewAndroidDelegate(contentView), this.n, g3Var, new org.chromium.content_public.browser.b());
            N.Mt4iWzCb(this.m, false);
            a aVar = new a();
            this.p = aVar;
            this.j.g(aVar);
            d dVar = g3Var.e;
            if (dVar == null) {
                Log.w("cr_WindowAndroid", "Cannot get IntentRequestTracker as the WindowAndroid is neither a ActivityWindowAndroid or a FragmentWindowAndroid.");
            }
            Context context = this.e;
            wq0 wq0Var = new wq0(21, this);
            androidx.appcompat.widget.a aVar2 = new androidx.appcompat.widget.a(19, this);
            wv wvVar = new wv(25, this);
            Tab tab = (Tab) this.h.f;
            zh0 zh0Var = new zh0(context, wq0Var, aVar2, wvVar, (tab == null || tab.getView() == null) ? 0 : tab.getView().getHeight(), dVar, new xr(8, this));
            this.o = zh0Var;
            WebContents webContents2 = this.m;
            ContentView contentView2 = this.n;
            cVar.h = profileH0;
            cVar.e = webContents2;
            cVar.f = zh0Var;
            cVar.g = new b(cVar, webContents2);
            xh0 xh0Var = new xh0(cVar);
            cVar.getClass();
            zh0 zh0Var2 = cVar.f;
            zh0Var2.n = cVar.e;
            zh0Var2.o = contentView2;
            if (contentView2.getParent() != null) {
                ((ViewGroup) zh0Var2.o.getParent()).removeView(zh0Var2.o);
            }
            zh0Var2.p.a(zh0Var2.n, zh0Var2.o, xh0Var);
            zh0Var2.i.s(zh0Var2.n.V().r);
            this.g.addOnLayoutChangeListener(this);
        }
        this.t = false;
        this.u = false;
        cVar.a(gurl);
        ((TextView) cVar.f.l.findViewById(R$id.title)).setText(str);
        cVar.a.d(cVar.f, true);
        xe3 xe3VarT = oj2.t(profileH0);
        if (xe3VarT.isInitialized()) {
            xe3VarT.notifyEvent("ephemeral_tab_used");
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.o == null) {
            return;
        }
        Tab tab = (Tab) this.h.f;
        int height = (tab == null || tab.getView() == null) ? 0 : tab.getView().getHeight();
        if (height == 0 || this.s == height) {
            return;
        }
        zh0 zh0Var = this.o;
        if (height == 0) {
            zh0Var.getClass();
        } else {
            zh0Var.p.getView().getLayoutParams().height = ((int) (height * 0.9f)) - zh0Var.h;
            ao3.e(zh0Var.m, "EphemeralTabSheetContent.updateContentHeight");
        }
        this.s = height;
    }
}
