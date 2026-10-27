package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabWebContentsObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l53 extends m53 {
    public final TabImpl f;
    public final b<Callback<WebContents>> g;
    public a h;
    public GURL i;

    /* JADX INFO: compiled from: TabWebContentsObserver.java */
    public class a extends rq3 {

        /* JADX INFO: renamed from: l53$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TabWebContentsObserver.java */
        public class RunnableC0064a implements Runnable {
            public RunnableC0064a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                a aVar = a.this;
                l53.this.f.g.m().c();
                l53.this.f.a();
            }
        }

        public a(WebContents webContents) {
            super(webContents);
        }

        @Override // defpackage.rq3
        public final void destroy() {
            Context context = xz.a;
            l53 l53Var = l53.this;
            yc1.i(context, l53Var.f.getId(), null, l53Var.i);
            Context context2 = xz.a;
            TabImpl tabImpl = l53Var.f;
            Cif.e(context2, tabImpl.getId(), null, l53Var.i, tabImpl.isIncognito());
            ik3.d(xz.a, tabImpl.getId(), null, l53Var.i, tabImpl.isIncognito());
            super.destroy();
        }

        @Override // defpackage.rq3
        public final void didChangeThemeColor() {
            TabImpl tabImpl = l53.this.f;
            tabImpl.Z(tabImpl.g.j());
        }

        @Override // defpackage.rq3
        public final void didFailLoad(boolean z, int i, GURL gurl, int i2) {
            if (z) {
                l53.this.f.J(i);
            }
            gurl.j();
            AppHooks.get().getClass();
        }

        @Override // defpackage.rq3
        public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
            TabImpl tabImpl;
            SwipeRefreshLayout swipeRefreshLayout;
            l53 l53Var = l53.this;
            b.a aVarN = l53Var.f.N();
            while (true) {
                boolean zHasNext = aVarN.hasNext();
                tabImpl = l53Var.f;
                if (!zHasNext) {
                    break;
                } else {
                    ((n23) aVarN.next()).o1(tabImpl, navigationHandle);
                }
            }
            int i = navigationHandle.l;
            if (i != 0) {
                tabImpl.J(i);
            }
            l53Var.i = navigationHandle.e;
            if (navigationHandle.g) {
                tabImpl.a0();
                GURL gurl = navigationHandle.e;
                int i2 = navigationHandle.d;
                tabImpl.w = false;
                if (!tabImpl.T(gurl.j(), (i2 & 255) == 8)) {
                    tabImpl.a0();
                    if (tabImpl.f != null) {
                        tabImpl.Q(true, null);
                    }
                }
                tabImpl.n = navigationHandle.i;
                aVarN.c();
                while (aVarN.hasNext()) {
                    ((n23) aVarN.next()).x0(tabImpl);
                }
                fx2 fx2Var = (fx2) tabImpl.B().b(fx2.class);
                if (fx2Var == null || (swipeRefreshLayout = fx2Var.g) == null || !swipeRefreshLayout.g) {
                    return;
                }
                fx2Var.d();
                SwipeRefreshLayout swipeRefreshLayout2 = fx2Var.g;
                if (fx2Var.j == null) {
                    fx2Var.j = new wv(13, fx2Var);
                }
                swipeRefreshLayout2.postDelayed(fx2Var.j, 500L);
            }
        }

        @Override // defpackage.rq3
        public final void didFirstVisuallyNonEmptyPaint() {
            l53 l53Var = l53.this;
            b.a aVarN = l53Var.f.N();
            while (aVarN.hasNext()) {
                ((n23) aVarN.next()).Z(l53Var.f);
            }
        }

        @Override // defpackage.rq3
        public final void didRedirectNavigation(NavigationHandle navigationHandle) {
            b.a aVarN = l53.this.f.N();
            while (aVarN.hasNext()) {
                ((n23) aVarN.next()).x1();
            }
        }

        @Override // defpackage.rq3
        public final void didStartNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
            boolean z = navigationHandle.c;
            l53 l53Var = l53.this;
            if (!z) {
                l53Var.f.L(navigationHandle.e);
            }
            b.a aVarN = l53Var.f.N();
            while (aVarN.hasNext()) {
                ((n23) aVarN.next()).C0(l53Var.f, navigationHandle);
            }
        }

        @Override // defpackage.rq3
        public final void f(ct0 ct0Var, GURL gurl, boolean z, int i) {
            if (i == 1) {
                l53 l53Var = l53.this;
                TabImpl tabImpl = l53Var.f;
                jk1 jk1Var = tabImpl.f;
                if (jk1Var != null) {
                    N.MhCci$0r(tabImpl.a, jk1Var.getUrl(), tabImpl.f.getTitle());
                }
                l53Var.f.K(gurl);
            }
        }

        @Override // defpackage.rq3
        public final void loadProgressChanged(float f) {
            TabImpl tabImpl = l53.this.f;
            if (!tabImpl.r) {
                return;
            }
            Iterator<n23> it = tabImpl.k.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((n23) aVar.next()).l1(tabImpl, f);
                }
            }
        }

        @Override // defpackage.rq3
        public final void navigationEntriesDeleted() {
            TabImpl tabImpl = l53.this.f;
            Iterator<n23> it = tabImpl.k.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((n23) aVar.next()).K0(tabImpl);
                }
            }
        }

        @Override // defpackage.rq3
        public final void renderProcessGone() {
            StringBuilder sb = new StringBuilder("renderProcessGone() for tab id: ");
            l53 l53Var = l53.this;
            sb.append(l53Var.f.getId());
            sb.append(", already needs reload: ");
            TabImpl tabImpl = l53Var.f;
            sb.append(Boolean.toString(tabImpl.f()));
            Log.i("cr_TabWebContentsObs", sb.toString());
            if (tabImpl.f() || hd2.z1(tabImpl)) {
                Log.w("cr_TabWebContentsObs", "mTab.needsReload() || SadTab.isShowing(mTab)");
                return;
            }
            if (tabImpl.isNativePage() && jk1.h(tabImpl.getUrl(), tabImpl.isIncognito())) {
                Log.w("cr_TabWebContentsObs", "mTab.isNativePage()");
                return;
            }
            int iC = ApplicationStatus.c((Activity) tabImpl.e.k().get());
            if (tabImpl.isHidden() || iC == 4 || iC == 5 || iC == 6) {
                tabImpl.g.m().c();
            } else {
                if (!tabImpl.isHidden() && iC == 3) {
                    Log.e("cr_TabWebContentsObs", "tab resumed need reload");
                    tabImpl.P();
                    ThreadUtils.c().postDelayed(new RunnableC0064a(), 100L);
                    return;
                }
                hd2 hd2Var = (hd2) tabImpl.B().b(hd2.class);
                if (hd2Var == null) {
                    hd2Var = (hd2) tabImpl.B.d(hd2.class, new hd2(tabImpl));
                }
                new Handler().post(new y70(this, 15, hd2Var));
                oj2.O("Stability.Android.RendererCrash", true);
            }
            tabImpl.P();
        }

        @Override // defpackage.rq3
        public final void titleWasSet(String str) {
            TabImpl tabImpl = l53.this.f;
            tabImpl.getClass();
            if (TextUtils.equals(CriticalPersistedTabData.m(tabImpl).n, str)) {
                return;
            }
            CriticalPersistedTabData criticalPersistedTabDataM = CriticalPersistedTabData.m(tabImpl);
            if (!TextUtils.equals(str, criticalPersistedTabDataM.n)) {
                criticalPersistedTabDataM.n = str;
                criticalPersistedTabDataM.l();
            }
            b.a aVarN = tabImpl.N();
            while (aVarN.hasNext()) {
                ((n23) aVarN.next()).r1(tabImpl);
            }
        }

        @Override // defpackage.rq3
        public final void viewportFitChanged(int i) {
            TabImpl tabImpl = l53.this.f;
            mk3 mk3Var = tabImpl.B;
            cb0 cb0Var = (cb0) mk3Var.b(cb0.class);
            if (cb0Var == null) {
                cb0Var = (cb0) mk3Var.d(cb0.class, new cb0(tabImpl));
            }
            bb0 bb0Var = cb0Var.f;
            if (i == bb0Var.f) {
                return;
            }
            bb0Var.f = i;
            bb0Var.c();
        }

        @Override // defpackage.rq3
        public final void virtualKeyboardModeChanged(int i) {
            b.a aVarN = l53.this.f.N();
            while (aVarN.hasNext()) {
                ((n23) aVarN.next()).v1(i);
            }
        }
    }

    public l53(Tab tab) {
        super(tab);
        this.g = new b<>();
        new Handler();
        this.f = (TabImpl) tab;
    }

    public static l53 d(Tab tab) {
        l53 l53Var = (l53) tab.B().b(l53.class);
        if (l53Var != null) {
            return l53Var;
        }
        l53 l53Var2 = new l53(tab);
        tab.B().d(l53.class, l53Var2);
        return l53Var2;
    }

    @Override // defpackage.m53
    public final void a(WebContents webContents) {
        a aVar = this.h;
        if (aVar != null) {
            aVar.destroy();
            this.h = null;
        }
    }

    @Override // defpackage.m53
    public final void b() {
        this.g.clear();
    }

    @Override // defpackage.m53
    public final void c(WebContents webContents) {
        this.h = new a(webContents);
        Iterator<Callback<WebContents>> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((Callback) aVar.next()).onResult(webContents);
            }
        }
    }
}
