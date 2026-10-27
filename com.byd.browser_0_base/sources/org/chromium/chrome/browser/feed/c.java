package org.chromium.chrome.browser.feed;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;
import defpackage.ao3;
import defpackage.aw;
import defpackage.bq;
import defpackage.c3;
import defpackage.ch2;
import defpackage.ck2;
import defpackage.cq2;
import defpackage.fu0;
import defpackage.ho3;
import defpackage.hw0;
import defpackage.ii2;
import defpackage.ji;
import defpackage.ml0;
import defpackage.nr2;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.pk3;
import defpackage.qb3;
import defpackage.qh3;
import defpackage.sf2;
import defpackage.sk;
import defpackage.sw2;
import defpackage.uf2;
import defpackage.ul0;
import defpackage.v42;
import defpackage.v62;
import defpackage.vk0;
import defpackage.wp2;
import defpackage.xg2;
import defpackage.xk0;
import defpackage.yk0;
import defpackage.zj;
import defpackage.zk0;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.feed.sections.SectionHeaderView;
import org.chromium.chrome.browser.feed.sections.StickySectionHeaderView;
import org.chromium.chrome.browser.feed.sort_ui.FeedOptionsCoordinator;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.h;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: FeedSurfaceCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements SwipeRefreshLayout.e, sw2, yk0 {
    public final org.chromium.ui.modelutil.c<h<PropertyModel, v62>, SectionHeaderView, v62> A;
    public final j<PropertyModel, SectionHeaderView, v62> B;
    public f C;
    public final RecyclerView D;
    public NativeViewListRenderer E;
    public uf2 F;
    public final nw2<qb3> G;
    public FeedSwipeRefreshLayout H;
    public final Activity e;
    public final wp2 f;
    public final View g;
    public final boolean h;
    public final ml0 i;
    public final e j;
    public final ji k;
    public final WindowAndroid l;
    public final nw2<ck2> m;
    public final Handler n;
    public final qh3 p;
    public final a q;
    public boolean r;
    public int s;
    public final int u;
    public final Profile v;
    public final PropertyModel w;
    public final SectionHeaderView x;
    public final org.chromium.ui.modelutil.c<h<PropertyModel, v62>, SectionHeaderView, v62> y;
    public final j<PropertyModel, SectionHeaderView, v62> z;
    public final org.chromium.base.b<sw2.a> o = new org.chromium.base.b<>();
    public boolean I = false;
    public int t = 0;

    /* JADX INFO: compiled from: FeedSurfaceCoordinator.java */
    public class a extends FrameLayout {
        public a(Context context) {
            super(context);
        }

        @Override // android.view.View
        public final void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            c.this.p.c();
        }

        @Override // android.view.ViewGroup
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (super.onInterceptTouchEvent(motionEvent)) {
                return true;
            }
            c cVar = c.this;
            e eVar = cVar.j;
            if (eVar == null || eVar.r) {
                return cVar.i.b(motionEvent);
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: FeedSurfaceCoordinator.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            c cVar = c.this;
            e eVar = cVar.j;
            int i = cVar.t;
            if (eVar.h() && !eVar.g(i)) {
                eVar.e.D.g0(i);
            }
        }
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.feed.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FeedSurfaceCoordinator.java */
    public class C0084c implements sf2 {
        public C0084c() {
        }

        @Override // defpackage.sf2
        public final void a(int i) {
            int i2;
            c cVar = c.this;
            int i3 = cVar.u;
            uf2 uf2Var = cVar.F;
            if (uf2Var != null) {
                int[] iArr = new int[2];
                ao3.c(nr2.this.h, cVar.x, iArr);
                i2 = iArr[1];
            } else {
                i2 = Integer.MAX_VALUE;
            }
            cVar.w.l(xg2.m, i3 >= i2);
        }
    }

    /* JADX WARN: Type inference failed for: r13v1, types: [T, org.chromium.ui.modelutil.h] */
    /* JADX WARN: Type inference failed for: r13v2, types: [T, ho3] */
    public c(Activity activity, wp2 wp2Var, WindowAndroid windowAndroid, cq2 cq2Var, NewTabPageLayout newTabPageLayout, int i, boolean z, ml0 ml0Var, Profile profile, boolean z2, ji jiVar, nw2 nw2Var, uf2 uf2Var, int i2, v42 v42Var, nw2 nw2Var2, int i3, long j, FeedSwipeRefreshLayout feedSwipeRefreshLayout, boolean z3, ViewGroup viewGroup, xk0 xk0Var, fu0 fu0Var) {
        this.e = activity;
        this.f = wp2Var;
        this.g = newTabPageLayout;
        this.h = z2;
        this.i = ml0Var;
        this.k = jiVar;
        this.v = profile;
        this.l = windowAndroid;
        this.m = nw2Var;
        this.F = uf2Var;
        this.G = nw2Var2;
        this.H = feedSwipeRefreshLayout;
        this.u = i;
        Resources resources = activity.getResources();
        a aVar = new a(activity);
        this.q = aVar;
        aVar.setPadding(0, resources.getDimensionPixelOffset(R$dimen.tab_strip_height), 0, 0);
        this.p = new qh3(aVar);
        this.C = new f();
        ul0.b().getClass();
        this.E = new NativeViewListRenderer(activity);
        zk0.c(activity);
        RecyclerView recyclerViewD = this.E.d(this.C);
        recyclerViewD.setId(R$id.feed_stream_recycler_view);
        recyclerViewD.setClipToPadding(false);
        recyclerViewD.setBackgroundColor(ii2.a(activity));
        recyclerViewD.setDefaultFocusHighlightEnabled(false);
        if (z3) {
            recyclerViewD.setOverScrollMode(2);
        }
        this.D = recyclerViewD;
        FeedSwipeRefreshLayout feedSwipeRefreshLayout2 = this.H;
        if (feedSwipeRefreshLayout2 == null || feedSwipeRefreshLayout2.getParent() != null) {
            aVar.addView(recyclerViewD);
        } else {
            this.H.addView(recyclerViewD);
            aVar.addView(this.H);
        }
        FeedSwipeRefreshLayout feedSwipeRefreshLayout3 = this.H;
        if (feedSwipeRefreshLayout3 != null) {
            feedSwipeRefreshLayout3.addOnRefreshListener(this);
        }
        this.n = new Handler(Looper.getMainLooper());
        sk skVar = bq.a;
        if (N.M09VlOh_("WebFeed")) {
            this.x = (SectionHeaderView) LayoutInflater.from(activity).inflate(R$layout.new_tab_page_multi_feed_header, (ViewGroup) null, false);
        } else {
            this.x = (SectionHeaderView) LayoutInflater.from(activity).inflate(R$layout.new_tab_page_feed_v2_expandable_header, (ViewGroup) null, false);
        }
        PropertyModel.l<h<PropertyModel, v62>> lVar = xg2.b;
        PropertyModel.r<ho3> rVar = xg2.i;
        PropertyModel.r<View> rVar2 = xg2.j;
        PropertyModel.j jVar = xg2.l;
        PropertyModel.n nVar = xg2.m;
        PropertyModel.r<View> rVar3 = xg2.k;
        PropertyModel.p pVar = xg2.n;
        HashMap mapE = PropertyModel.e(new v62[]{xg2.a, lVar, xg2.c, xg2.d, xg2.e, xg2.f, xg2.g, xg2.h, rVar, rVar2, jVar, nVar, rVar3, pVar});
        ?? hVar = new h();
        int i4 = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = hVar;
        mapE.put(lVar, gVar);
        ?? r13 = ho3.INVISIBLE;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = r13;
        mapE.put(rVar, gVar2);
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i;
        mapE.put(jVar, dVar);
        PropertyModel.a aVar2 = new PropertyModel.a();
        aVar2.a = false;
        mapE.put(nVar, aVar2);
        PropertyModel propertyModel = new PropertyModel(mapE);
        this.w = propertyModel;
        ch2 ch2Var = new ch2();
        this.z = j.b(propertyModel, this.x, ch2Var);
        org.chromium.ui.modelutil.c<h<PropertyModel, v62>, SectionHeaderView, v62> cVar = new org.chromium.ui.modelutil.c<>((h) propertyModel.j(lVar), this.x, ch2Var);
        this.y = cVar;
        ((h) propertyModel.j(lVar)).g(cVar);
        FeedOptionsCoordinator feedOptionsCoordinator = new FeedOptionsCoordinator(activity);
        propertyModel.p(rVar2, feedOptionsCoordinator.a);
        if (N.M09VlOh_("WebFeed") && N.M09VlOh_("FeedHeaderStickToTop")) {
            StickySectionHeaderView stickySectionHeaderView = (StickySectionHeaderView) LayoutInflater.from(activity).inflate(R$layout.new_tab_page_multi_sticky_feed_header, (ViewGroup) null, false);
            aVar.addView(stickySectionHeaderView);
            this.B = j.b(propertyModel, stickySectionHeaderView, ch2Var);
            org.chromium.ui.modelutil.c<h<PropertyModel, v62>, SectionHeaderView, v62> cVar2 = new org.chromium.ui.modelutil.c<>((h) propertyModel.j(lVar), stickySectionHeaderView, ch2Var);
            this.A = cVar2;
            ((h) propertyModel.j(lVar)).g(cVar2);
            propertyModel.p(rVar3, feedOptionsCoordinator.b);
            if (i3 == 2) {
                propertyModel.n(pVar, i / 2);
                nr2.this.H.a(new C0084c());
            } else {
                propertyModel.n(pVar, i);
            }
        }
        e eVar = new e(this, activity, cq2Var, propertyModel, i2 != 1 ? -1 : 1, xk0Var, feedOptionsCoordinator);
        this.j = eVar;
        ul0 ul0VarB = ul0.b();
        if (ul0VarB.c == null) {
            ul0VarB.c = new HashSet<>();
        }
        ul0VarB.c.add(this);
        e(ul0VarB);
        eVar.n();
    }

    @Override // defpackage.yk0
    public final void a(int i) {
        if (i == 1 && this.I) {
            boolean z = zk0.b;
            sk skVar = bq.a;
            if (N.MMltG$kc("WebFeedAwareness", "awareness_style").equals("IPH")) {
                pk3 pk3Var = new pk3(this.e, this.n);
                b bVar = new b();
                SectionHeaderView sectionHeaderView = this.x;
                TabLayout.g gVarB = sectionHeaderView.b(1);
                Resources resources = sectionHeaderView.getContext().getResources();
                int i2 = R$string.web_feed_awareness;
                hw0 hw0Var = new hw0(resources, "IPH_WebFeedAwareness", i2, i2);
                hw0Var.j = gVarB != null ? gVarB.i : null;
                hw0Var.k = bVar;
                pk3Var.a(hw0Var.a());
            }
        }
    }

    @Override // defpackage.sw2
    public final void b() {
        if (!ul0.b().a) {
            return;
        }
        this.r = true;
        Iterator<sw2.a> it = this.o.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                this.j.i();
                vk0.b(4, new aw(29, this));
                return;
            }
            ((sw2.a) aVar.next()).a();
        }
    }

    @Override // org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout.e
    public final void c() {
        qb3 qb3Var = this.G.get();
        if (qb3Var != null) {
            qb3Var.d(true);
        }
        c3 c3Var = new c3(20, this);
        g gVar = this.j.z;
        if (gVar != null) {
            gVar.e(c3Var);
        } else {
            c3Var.onResult(Boolean.FALSE);
        }
        oj2.t(this.v).notifyEvent("feed_swipe_refresh_shown");
    }

    @Override // defpackage.sw2
    public final void d() {
        if (ul0.b().a) {
            this.r = false;
            this.j.l(false);
            vk0.c(4);
        }
    }

    public final void e(ul0 ul0Var) {
        this.o.a(ul0Var);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void f() {
        FeedSwipeRefreshLayout feedSwipeRefreshLayout = this.H;
        if (feedSwipeRefreshLayout != null) {
            if (feedSwipeRefreshLayout.g) {
                feedSwipeRefreshLayout.setRefreshing(false);
                qb3 qb3Var = this.G.get();
                if (qb3Var != null) {
                    qb3Var.d(false);
                }
            }
            this.H.removeOnRefreshListener(this);
            this.H.k();
            this.H = null;
        }
        e eVar = this.j;
        eVar.y.isEmpty();
        uf2 uf2Var = this.F;
        if (uf2Var != null) {
            nr2.this.H.c(null);
            this.F = null;
        }
        uf2 uf2Var2 = this.F;
        if (uf2Var2 != null) {
            nr2.this.H.c(null);
            this.F = null;
        }
        PropertyModel propertyModel = this.w;
        j<PropertyModel, SectionHeaderView, v62> jVar = this.z;
        if (jVar != null) {
            jVar.c();
            ((h) propertyModel.j(xg2.b)).e.c(this.y);
        }
        j<PropertyModel, SectionHeaderView, v62> jVar2 = this.B;
        if (jVar2 != null) {
            jVar2.c();
            ((h) propertyModel.j(xg2.b)).e.c(this.A);
        }
        eVar.d();
        eVar.h.b();
        eVar.j.i(eVar);
        ul0 ul0VarB = ul0.b();
        HashSet<sw2> hashSet = ul0VarB.c;
        if (hashSet != null) {
            hashSet.remove(this);
            g(ul0VarB);
        }
        NativeViewListRenderer nativeViewListRenderer = this.E;
        if (nativeViewListRenderer != null) {
            ((f) nativeViewListRenderer.i).b.remove(nativeViewListRenderer);
            nativeViewListRenderer.r(0, ((f) nativeViewListRenderer.i).d());
            nativeViewListRenderer.k.setAdapter(null);
            nativeViewListRenderer.k.setLayoutManager(null);
            nativeViewListRenderer.i = null;
        }
        this.q.removeAllViews();
    }

    public final void g(ul0 ul0Var) {
        this.o.c(ul0Var);
    }

    @Override // defpackage.sw2
    public final boolean isActive() {
        return this.r;
    }
}
