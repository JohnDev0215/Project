package org.chromium.chrome.browser.feed;

import J.N;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.a8;
import defpackage.al0;
import defpackage.bq;
import defpackage.bq2;
import defpackage.cq2;
import defpackage.ho3;
import defpackage.jg;
import defpackage.k81;
import defpackage.l10;
import defpackage.o81;
import defpackage.oj2;
import defpackage.ol0;
import defpackage.pl0;
import defpackage.q11;
import defpackage.qe1;
import defpackage.sk;
import defpackage.tl0;
import defpackage.v62;
import defpackage.wk0;
import defpackage.xg2;
import defpackage.xk0;
import defpackage.yd3;
import defpackage.yg2;
import defpackage.z7;
import defpackage.zk0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.base.MemoryPressureListener;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.app.feed.feedmanagement.FeedManagementActivity;
import org.chromium.chrome.browser.feed.NativeViewListRenderer;
import org.chromium.chrome.browser.feed.e;
import org.chromium.chrome.browser.feed.f;
import org.chromium.chrome.browser.feed.g;
import org.chromium.chrome.browser.feed.settings.FeedAutoplaySettingsFragment;
import org.chromium.chrome.browser.feed.sort_ui.FeedOptionsCoordinator;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.components.signin.identitymanager.PrimaryAccountChangeEvent;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.h;

/* JADX INFO: compiled from: FeedSurfaceMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements tl0, yd3, TemplateUrlService.c, k81.a, IdentityManager.a, FeedOptionsCoordinator.a {
    public final org.chromium.chrome.browser.feed.c e;
    public final Context f;
    public final bq2 g;
    public final PrefChangeRegistrar h;
    public final SigninManager i;
    public final TemplateUrlService j;
    public final PropertyModel k;
    public final wk0 l;
    public final FeedOptionsCoordinator m;
    public final org.chromium.chrome.browser.feed.c n;
    public pl0 o;
    public final b p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public int u;
    public int v;
    public int w;
    public int x;
    public final HashMap<Integer, g> y;
    public g z;

    /* JADX INFO: compiled from: FeedSurfaceMediator.java */
    public class a implements al0 {
    }

    /* JADX INFO: compiled from: FeedSurfaceMediator.java */
    public class b implements RecyclerView.j.a {
        public Runnable a;

        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.j.a
        public final void a() {
            new Handler().post(new l10(12, this));
        }

        public final void b() {
            RecyclerView recyclerView = e.this.e.D;
            if (recyclerView != null) {
                RecyclerView.j jVar = recyclerView.R;
                if (jVar != null && jVar.k()) {
                    RecyclerView.j itemAnimator = recyclerView.getItemAnimator();
                    if (itemAnimator.k()) {
                        itemAnimator.b.add(this);
                        return;
                    } else {
                        a();
                        return;
                    }
                }
            }
            Runnable runnable = this.a;
            if (runnable != null) {
                runnable.run();
                this.a = null;
            }
        }
    }

    /* JADX INFO: compiled from: FeedSurfaceMediator.java */
    public class c implements g.b {
        public c() {
        }

        @Override // org.chromium.chrome.browser.feed.g.b
        public final void b() {
            e eVar = e.this;
            int iE = eVar.e(2);
            if (iE != -1) {
                PropertyModel.l<h<PropertyModel, v62>> lVar = xg2.b;
                PropertyModel propertyModel = eVar.k;
                h hVar = (h) propertyModel.j(lVar);
                propertyModel.n(xg2.c, iE);
                ((PropertyModel) hVar.get(iE)).l(yg2.b, false);
                zk0.e(iE);
                g gVar = eVar.y.get(Integer.valueOf(iE));
                if (gVar.f()) {
                    ((PropertyModel) hVar.get(iE)).p(yg2.c, ho3.VISIBLE);
                }
                eVar.e.getClass();
                eVar.b(gVar);
                if (gVar.a() == 2) {
                    zk0.b().e("webfeed.has_seen_feed", true);
                }
            }
        }

        @Override // org.chromium.chrome.browser.feed.g.b
        public final void c() {
            org.chromium.chrome.browser.feed.c cVar = e.this.e;
            FeedSwipeRefreshLayout feedSwipeRefreshLayout = cVar.H;
            if (feedSwipeRefreshLayout != null) {
                int i = feedSwipeRefreshLayout.I.getResources().getDisplayMetrics().heightPixels;
                feedSwipeRefreshLayout.y = Math.max(i / 2, i - ((int) (r1.density * 100.0f)));
                feedSwipeRefreshLayout.m = false;
                feedSwipeRefreshLayout.o.invalidate();
                feedSwipeRefreshLayout.setRefreshing(true);
                feedSwipeRefreshLayout.y = (int) (r1.density * 80.0f);
                feedSwipeRefreshLayout.m = false;
                feedSwipeRefreshLayout.o.invalidate();
            }
            cVar.c();
        }
    }

    /* JADX WARN: Type inference failed for: r3v5, types: [pl0] */
    public e(org.chromium.chrome.browser.feed.c cVar, Context context, cq2 cq2Var, PropertyModel propertyModel, int i, xk0 xk0Var, FeedOptionsCoordinator feedOptionsCoordinator) {
        new org.chromium.base.b();
        this.p = new b();
        this.r = true;
        this.y = new HashMap<>();
        this.e = cVar;
        this.n = cVar;
        this.f = context;
        this.g = cq2Var;
        Profile profileD = Profile.d();
        z7.m().getClass();
        this.i = z7.D(profileD);
        this.j = oj2.q(profileD);
        this.l = xk0Var;
        this.m = feedOptionsCoordinator;
        feedOptionsCoordinator.f = this;
        PrefChangeRegistrar prefChangeRegistrar = new PrefChangeRegistrar();
        this.h = prefChangeRegistrar;
        prefChangeRegistrar.a("ntp_snippets.enable", new ol0(0, this));
        if (i == -1) {
            zk0.a();
        }
        this.k = propertyModel;
        this.o = new g.a() { // from class: pl0
            @Override // org.chromium.chrome.browser.feed.g.a
            public final void a(ArrayList arrayList) {
                e eVar = this.a;
                eVar.getClass();
                ql0 ql0Var = new ql0(0, eVar);
                e.b bVar = eVar.p;
                if (e.this.e.D == null) {
                    return;
                }
                bVar.a = ql0Var;
                new Handler().post(new ql0(1, bVar));
            }
        };
        if (cq2Var == null) {
            return;
        }
        cVar.q.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: rl0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                e eVar = this.e;
                cq2 cq2Var2 = (cq2) eVar.g;
                NewTabPageLayout newTabPageLayout = cq2Var2.b;
                int iF = ((e) newTabPageLayout.getScrollDelegate()).f();
                if (cq2Var2.i != iF) {
                    cq2Var2.i = iF;
                    if (cq2Var2.h) {
                        View view2 = cq2Var2.g;
                        cq2.a aVar = cq2Var2.c;
                        view2.removeCallbacks(aVar);
                        cq2Var2.g.postDelayed(aVar, 30L);
                    }
                    newTabPageLayout.m();
                }
                float f = eVar.f.getResources().getDisplayMetrics().density;
                int i10 = (int) ((i4 - i2) / f);
                eVar.t = i10 >= 360 && ((int) (((float) (i5 - i3)) / f)) >= 360;
                eVar.o(i10 < 600);
            }
        });
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.c
    public final void M() {
        p();
    }

    @Override // k81.a
    public final void a(PropertyModel propertyModel) {
        int iH = propertyModel.h(o81.f);
        HashMap<Integer, g> map = this.y;
        PropertyModel.p pVar = xg2.c;
        PropertyModel propertyModel2 = this.k;
        int iA = map.get(Integer.valueOf(propertyModel2.h(pVar))).a();
        if (iH == R$id.ntp_feed_header_menu_item_manage) {
            Context context = this.f;
            Intent intent = new Intent(context, (Class<?>) FeedManagementActivity.class);
            intent.putExtra("feed_management_initiating_stream_type_extra", iA);
            N.MxULk9PS(iA, 42);
            a8.b0(6);
            context.startActivity(intent);
            return;
        }
        int i = R$id.ntp_feed_header_menu_item_activity;
        wk0 wk0Var = this.l;
        if (iH == i) {
            wk0Var.b(new LoadUrlParams("https://myactivity.google.com/myactivity?product=50", 0));
            N.MxULk9PS(iA, 16);
            a8.b0(0);
            return;
        }
        if (iH == R$id.ntp_feed_header_menu_item_interest) {
            wk0Var.b(new LoadUrlParams("https://www.google.com/preferences/interests", 0));
            N.MxULk9PS(iA, 6);
            a8.b0(1);
            return;
        }
        if (iH == R$id.ntp_feed_header_menu_item_reactions) {
            wk0Var.b(new LoadUrlParams("https://www.google.com/search/contributions/reactions", 0));
            N.MxULk9PS(iA, 28);
            a8.b0(1);
            return;
        }
        if (iH != R$id.ntp_feed_header_menu_item_autoplay) {
            if (iH == R$id.ntp_feed_header_menu_item_learn) {
                wk0Var.c();
                N.MxULk9PS(iA, 3);
                a8.b0(2);
                return;
            } else {
                if (iH == R$id.ntp_feed_header_menu_item_toggle_switch) {
                    boolean z = !propertyModel2.k(xg2.a);
                    oj2.k(Profile.d()).e("ntp_snippets.list_visible", z);
                    a8.b0(3);
                    oj2.O("NewTabPage.ContentSuggestions.ArticlesListVisible", oj2.k(Profile.d()).a("ntp_snippets.list_visible"));
                    N.MxULk9PS(map.get(Integer.valueOf(propertyModel2.h(pVar))).a(), z ? 14 : 15);
                    return;
                }
                return;
            }
        }
        org.chromium.chrome.browser.feed.c cVar = this.e;
        cVar.getClass();
        Bundle bundle = new Bundle();
        String name = FeedAutoplaySettingsFragment.class.getName();
        Intent intent2 = new Intent();
        Activity activity = cVar.e;
        intent2.setClass(activity, SettingsActivity.class);
        if (!(activity instanceof Activity)) {
            intent2.addFlags(268435456);
            intent2.addFlags(67108864);
        }
        intent2.putExtra("show_fragment", name);
        intent2.putExtra("show_fragment_args", bundle);
        ComponentName componentName = q11.a;
        try {
            activity.startActivity(intent2, null);
        } catch (ActivityNotFoundException unused) {
        }
        a8.b0(5);
    }

    public final void b(g gVar) {
        g gVar2 = this.z;
        if (gVar2 == gVar) {
            return;
        }
        if (gVar2 != null) {
            l(true);
        }
        org.chromium.chrome.browser.feed.c cVar = this.e;
        if (cVar.r) {
            if (this.k.k(xg2.a)) {
                this.z = gVar;
                o(false);
                this.z.h(this.o);
                this.z.b(cVar.D, cVar.C, null, cVar.E, new a(), this.x);
                cVar.E.getClass();
            }
        }
    }

    @Override // defpackage.yd3
    public final void c(boolean z) {
        this.r = z;
    }

    public final void d() {
        HashMap<Integer, g> map = this.y;
        if (map.isEmpty()) {
            return;
        }
        Object obj = ThreadUtils.a;
        org.chromium.base.b<qe1> bVar = MemoryPressureListener.a;
        if (bVar != null) {
            bVar.c(null);
        }
        l(false);
        for (g gVar : map.values()) {
            gVar.c(this.o);
            gVar.destroy();
        }
        map.clear();
        this.o = null;
        PrefChangeRegistrar prefChangeRegistrar = this.h;
        ArrayMap arrayMap = prefChangeRegistrar.a;
        if (((PrefChangeRegistrar.a) arrayMap.get("ntp_snippets.list_visible")) != null) {
            arrayMap.remove("ntp_snippets.list_visible");
            N.M0E$fVRB(prefChangeRegistrar.b, prefChangeRegistrar, "ntp_snippets.list_visible");
        }
        this.j.i(this);
        this.i.l().c(this);
        ((h) this.k.j(xg2.b)).clear();
        this.e.getClass();
    }

    public final int e(int i) {
        HashMap<Integer, g> map = this.y;
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (map.get(Integer.valueOf(iIntValue)).a() == i) {
                return iIntValue;
            }
        }
        return -1;
    }

    public final int f() {
        RecyclerView.m layoutManager;
        View viewU;
        if (!h()) {
            return 0;
        }
        if (!g(0) || (layoutManager = this.e.D.getLayoutManager()) == null || (viewU = layoutManager.u(0)) == null) {
            return Integer.MIN_VALUE;
        }
        return -viewU.getTop();
    }

    public final boolean g(int i) {
        NativeViewListRenderer.a aVar;
        if (!h() || (aVar = this.e.E.j) == null) {
            return false;
        }
        int iB = aVar.b();
        int iD = aVar.d();
        return iB != -1 && iD != -1 && iB <= i && i <= iD;
    }

    public final boolean h() {
        RecyclerView recyclerView = this.e.D;
        return recyclerView != null && recyclerView.getHeight() > 0;
    }

    public final void i() {
        if (this.z != null) {
            return;
        }
        g gVar = this.y.get(Integer.valueOf(this.k.h(xg2.c)));
        if (gVar != null) {
            b(gVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j() {
        /*
            r7 = this;
            bq2 r0 = r7.g
            if (r0 != 0) goto L5
            return
        L5:
            boolean r1 = r7.h()
            if (r1 != 0) goto Lc
            return
        Lc:
            int r1 = r7.f()
            cq2 r0 = (defpackage.cq2) r0
            sm1 r2 = r0.a
            org.chromium.chrome.browser.ntp.h$b r2 = (org.chromium.chrome.browser.ntp.h.b) r2
            org.chromium.chrome.browser.ntp.h r2 = org.chromium.chrome.browser.ntp.h.this
            boolean r3 = r2.D
            r4 = 0
            if (r3 == 0) goto L1e
            goto L33
        L1e:
            boolean r3 = r2.x
            boolean r5 = r2.n
            r6 = 1
            if (r5 != 0) goto L29
            if (r3 == 0) goto L29
            r3 = r6
            goto L2a
        L29:
            r3 = r4
        L2a:
            if (r3 == 0) goto L33
            org.chromium.chrome.browser.ntp.NewTabPageLayout r2 = r2.u
            boolean r2 = r2.x
            if (r2 != 0) goto L33
            goto L34
        L33:
            r6 = r4
        L34:
            if (r6 == 0) goto L68
            int r2 = r0.e
            int r3 = r4 + r2
            int r3 = r3 / 2
            if (r1 < 0) goto L45
            if (r1 <= r2) goto L41
            goto L45
        L41:
            if (r1 >= r3) goto L46
            r2 = r4
            goto L46
        L45:
            r2 = r1
        L46:
            org.chromium.chrome.browser.ntp.NewTabPageLayout r3 = r0.b
            android.view.View r3 = r3.getSearchBoxView()
            int r5 = r3.getTop()
            int r3 = r3.getPaddingTop()
            int r3 = r3 + r5
            int r0 = r0.f
            int r0 = r3 - r0
            int r5 = r0 + r3
            int r5 = r5 / 2
            if (r2 < r0) goto L69
            if (r2 <= r3) goto L62
            goto L69
        L62:
            if (r2 >= r5) goto L66
            r2 = r0
            goto L69
        L66:
            r2 = r3
            goto L69
        L68:
            r2 = r1
        L69:
            org.chromium.chrome.browser.feed.c r7 = r7.e
            androidx.recyclerview.widget.RecyclerView r7 = r7.D
            int r2 = r2 - r1
            r7.j0(r4, r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.feed.e.j():void");
    }

    public final void l(boolean z) {
        if (this.z == null) {
            return;
        }
        this.e.E.getClass();
        this.z.g(z);
        this.z.c(this.o);
        this.z = null;
    }

    @Override // org.chromium.components.signin.identitymanager.IdentityManager.a
    public final void m(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
        p();
    }

    public final void n() {
        boolean z = zk0.b;
        boolean zMpwNAobK = N.MpwNAobK();
        this.q = zMpwNAobK;
        if (!zMpwNAobK || this.y.isEmpty()) {
            org.chromium.chrome.browser.feed.c cVar = this.e;
            RecyclerView recyclerView = cVar.D;
            bq2 bq2Var = this.g;
            if (bq2Var != null && recyclerView != null) {
                cq2 cq2Var = (cq2) bq2Var;
                View view = cq2Var.g;
                if (view != null) {
                    cq2Var.h = false;
                    cq2Var.i = -1;
                    view.removeCallbacks(cq2Var.c);
                    cq2Var.g.setOnTouchListener(null);
                }
                cq2Var.g = recyclerView;
                recyclerView.setOnTouchListener(new jg(2, cq2Var));
            }
            Log.i("FeedSurfaceMediator", "updateContent, feed is enabled? " + this.q);
            ArrayList<View> arrayList = new ArrayList();
            View view2 = cVar.g;
            if (view2 != null) {
                arrayList.add(view2);
            }
            cVar.getClass();
            FeedSwipeRefreshLayout feedSwipeRefreshLayout = cVar.H;
            if (feedSwipeRefreshLayout != null) {
                feedSwipeRefreshLayout.k();
            }
            ArrayList arrayList2 = new ArrayList();
            for (View view3 : arrayList) {
                int dimensionPixelSize = cVar.e.getResources().getDimensionPixelSize(R$dimen.ntp_header_lateral_paddings_v2);
                if (view3 == cVar.x) {
                    dimensionPixelSize = 0;
                }
                arrayList2.add(new f.c(dimensionPixelSize, view3, "Header" + view3.hashCode()));
            }
            if (cVar.C.e(arrayList2, 0, cVar.s)) {
                int size = arrayList2.size();
                cVar.s = size;
                e eVar = cVar.j;
                eVar.x = size;
                g gVar = eVar.z;
                if (gVar != null) {
                    gVar.d(size);
                }
            }
            cVar.t = arrayList.size() - 1;
            cVar.D.requestFocus();
            d();
        }
    }

    public final void o(boolean z) {
        g gVar;
        NativeViewListRenderer.a aVar = this.e.E.j;
        if (!zk0.c(this.f) || aVar == null || (gVar = this.z) == null) {
            return;
        }
        boolean zF = gVar.f();
        sk skVar = bq.a;
        if (!N.M09VlOh_("WebFeedSort")) {
            this.z.a();
        }
        if (zF) {
            this.m.getClass();
            N.M3tcgrxA();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01e5  */
    /* JADX WARN: Type inference failed for: r8v16, types: [T, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p() {
        /*
            Method dump skipped, instruction units count: 812
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.feed.e.p():void");
    }
}
