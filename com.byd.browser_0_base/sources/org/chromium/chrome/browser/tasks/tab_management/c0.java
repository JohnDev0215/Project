package org.chromium.chrome.browser.tasks.tab_management;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.a8;
import defpackage.ag;
import defpackage.bg;
import defpackage.bq;
import defpackage.d13;
import defpackage.df0;
import defpackage.f13;
import defpackage.g03;
import defpackage.g33;
import defpackage.g72;
import defpackage.h03;
import defpackage.k42;
import defpackage.nm1;
import defpackage.oj2;
import defpackage.on0;
import defpackage.sk;
import defpackage.v80;
import defpackage.vz2;
import defpackage.y13;
import defpackage.z7;
import defpackage.za1;
import defpackage.zb2;
import defpackage.zf;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.c0;
import org.chromium.chrome.browser.tasks.tab_management.i;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.n0;
import org.chromium.chrome.browser.tasks.tab_management.p;
import org.chromium.chrome.browser.tasks.tab_management.s1;
import org.chromium.ui.modelutil.m;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* JADX INFO: compiled from: TabListCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c0 implements i.c, v80 {
    public final j0 e;
    public final TabListRecyclerView f;
    public final org.chromium.ui.modelutil.m g;
    public final int h;
    public final Rect i = new Rect();
    public final Context j;
    public final n0 k;
    public final int l;
    public final ViewGroup m;
    public boolean n;
    public final vz2 o;
    public final nm1 p;
    public final g1 q;
    public androidx.recyclerview.widget.l r;
    public a s;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r23v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r5v4, types: [androidx.recyclerview.widget.RecyclerView, org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView] */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v4, types: [int] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public c0(int i, final Context context, y13 y13Var, j0.p pVar, g72.a aVar, boolean z, s1 s1Var, m.c cVar, int i2, g33 g33Var, s1.c cVar2, final ViewGroup viewGroup, boolean z2, String str, ViewGroup viewGroup2, on0 on0Var, p.a aVar2) {
        RecyclerView.t tVar;
        boolean z3;
        ?? r7;
        int i3;
        int i4;
        int i5;
        this.h = i;
        this.l = i2;
        this.j = context;
        n0 n0Var = new n0();
        this.k = n0Var;
        org.chromium.ui.modelutil.m mVar = new org.chromium.ui.modelutil.m(n0Var);
        this.g = mVar;
        this.m = viewGroup2;
        int i6 = 3;
        final int i7 = 2;
        final int i8 = 0;
        final int i9 = 1;
        if (i == 0 || i == 2) {
            mVar.H(0, new za1() { // from class: x03
                @Override // defpackage.za1
                public final View a(ViewGroup viewGroup3) {
                    int i10 = i8;
                    ViewGroup viewGroup4 = viewGroup;
                    Context context2 = context;
                    switch (i10) {
                        case 0:
                            ViewGroup viewGroup5 = (ViewGroup) LayoutInflater.from(context2).inflate(R$layout.selectable_tab_grid_card_item, viewGroup4, false);
                            viewGroup5.setClickable(true);
                            return viewGroup5;
                        case 1:
                            return (ViewGroup) LayoutInflater.from(context2).inflate(R$layout.tab_strip_item, viewGroup4, false);
                        default:
                            ViewGroup viewGroup6 = (ViewGroup) LayoutInflater.from(context2).inflate(R$layout.selectable_tab_list_card_item, viewGroup4, false);
                            viewGroup6.setClickable(true);
                            return viewGroup6;
                    }
                }
            }, new y0(i6));
            mVar.H(1, new za1() { // from class: y03
                @Override // defpackage.za1
                public final View a(ViewGroup viewGroup3) {
                    c0 c0Var = this.a;
                    c0Var.getClass();
                    Context context2 = context;
                    ViewGroup viewGroup4 = (ViewGroup) LayoutInflater.from(context2).inflate(R$layout.closable_tab_grid_card_item, viewGroup, false);
                    if (c0Var.h == 2) {
                        viewGroup4.getLayoutParams().width = context2.getResources().getDimensionPixelSize(R$dimen.tab_carousel_card_width);
                    }
                    viewGroup4.setClickable(true);
                    return viewGroup4;
                }
            }, new ag(12));
            tVar = new RecyclerView.t() { // from class: z03
                @Override // androidx.recyclerview.widget.RecyclerView.t
                public final void h(RecyclerView.a0 a0Var) {
                    ImageView imageView;
                    int i10 = a0Var.j;
                    if ((i10 == 1 || i10 == 0) && (imageView = (ImageView) ((ViewLookupCachingFrameLayout) a0Var.e).j(R$id.tab_thumbnail)) != null) {
                        imageView.setImageDrawable(null);
                    }
                }
            };
        } else {
            if (i == 1) {
                mVar.H(2, new za1() { // from class: x03
                    @Override // defpackage.za1
                    public final View a(ViewGroup viewGroup3) {
                        int i10 = i9;
                        ViewGroup viewGroup4 = viewGroup;
                        Context context2 = context;
                        switch (i10) {
                            case 0:
                                ViewGroup viewGroup5 = (ViewGroup) LayoutInflater.from(context2).inflate(R$layout.selectable_tab_grid_card_item, viewGroup4, false);
                                viewGroup5.setClickable(true);
                                return viewGroup5;
                            case 1:
                                return (ViewGroup) LayoutInflater.from(context2).inflate(R$layout.tab_strip_item, viewGroup4, false);
                            default:
                                ViewGroup viewGroup6 = (ViewGroup) LayoutInflater.from(context2).inflate(R$layout.selectable_tab_list_card_item, viewGroup4, false);
                                viewGroup6.setClickable(true);
                                return viewGroup6;
                        }
                    }
                }, new zf(25));
            } else {
                if (i != 3) {
                    throw new IllegalArgumentException("Attempting to create a tab list UI with invalid mode");
                }
                mVar.H(1, new za1() { // from class: a13
                    @Override // defpackage.za1
                    public final View a(ViewGroup viewGroup3) {
                        ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = (ViewLookupCachingFrameLayout) LayoutInflater.from(context).inflate(R$layout.closable_tab_list_card_item, viewGroup, false);
                        viewLookupCachingFrameLayout.setClickable(true);
                        ImageView imageView = (ImageView) viewLookupCachingFrameLayout.j(R$id.end_button);
                        imageView.setVisibility(0);
                        Resources resources = viewLookupCachingFrameLayout.getResources();
                        int dimension = (int) resources.getDimension(R$dimen.tab_grid_close_button_size);
                        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(resources, R$drawable.btn_close);
                        Bitmap.createScaledBitmap(bitmapDecodeResource, dimension, dimension, true);
                        imageView.setImageBitmap(bitmapDecodeResource);
                        return viewLookupCachingFrameLayout;
                    }
                }, new bg(22));
                mVar.H(0, new za1() { // from class: x03
                    @Override // defpackage.za1
                    public final View a(ViewGroup viewGroup3) {
                        int i10 = i7;
                        ViewGroup viewGroup4 = viewGroup;
                        Context context2 = context;
                        switch (i10) {
                            case 0:
                                ViewGroup viewGroup5 = (ViewGroup) LayoutInflater.from(context2).inflate(R$layout.selectable_tab_grid_card_item, viewGroup4, false);
                                viewGroup5.setClickable(true);
                                return viewGroup5;
                            case 1:
                                return (ViewGroup) LayoutInflater.from(context2).inflate(R$layout.tab_strip_item, viewGroup4, false);
                            default:
                                ViewGroup viewGroup6 = (ViewGroup) LayoutInflater.from(context2).inflate(R$layout.selectable_tab_list_card_item, viewGroup4, false);
                                viewGroup6.setClickable(true);
                                return viewGroup6;
                        }
                    }
                }, new zf(24));
            }
            tVar = null;
        }
        RecyclerView.t tVar2 = tVar;
        j0 j0Var = new j0(context, n0Var, i, y13Var, pVar, aVar, new h0(context, i == 1), z, g33Var, s1Var, cVar, cVar2, str, i2, aVar2);
        this.e = j0Var;
        TraceEvent traceEventH0 = TraceEvent.H0("TabListCoordinator.setupRecyclerView", null);
        try {
            if (z2) {
                z3 = false;
                r7 = 1;
                LayoutInflater.from(context).inflate(R$layout.tab_list_recycler_view_layout, viewGroup, true);
                this.f = (TabListRecyclerView) viewGroup.findViewById(R$id.tab_list_view);
                i3 = i;
                i4 = 2;
            } else {
                z3 = false;
                this.f = (TabListRecyclerView) LayoutInflater.from(context).inflate(R$layout.tab_list_recycler_view_layout, viewGroup, false);
                i3 = i;
                i4 = 2;
                r7 = 1;
            }
            if (i3 == i4) {
                ViewGroup.LayoutParams layoutParams = this.f.getLayoutParams();
                layoutParams.height = -2;
                this.f.setLayoutParams(layoutParams);
            }
            this.f.setAdapter(mVar);
            this.f.setHasFixedSize(r7);
            if (tVar2 != null) {
                this.f.setRecyclerListener(tVar2);
            }
            if (i3 == 0) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, i4);
                this.f.setLayoutManager(gridLayoutManager);
                d13 d13Var = new d13(j0Var, gridLayoutManager);
                j0Var.s = d13Var;
                context.registerComponentCallbacks(d13Var);
                j0Var.w = gridLayoutManager;
                int i10 = context.getResources().getConfiguration().orientation;
                int i11 = context.getResources().getConfiguration().screenWidthDp;
                j0Var.q(gridLayoutManager);
                TabListRecyclerView tabListRecyclerView = this.f;
                if (oj2.C(context)) {
                    j0Var.G = new i0(j0Var, tabListRecyclerView);
                }
            } else {
                if (i3 == r7 || i3 == i4) {
                    i5 = 3;
                } else {
                    i5 = 3;
                    if (i3 == 3) {
                    }
                }
                this.f.setLayoutManager(new LinearLayoutManager((int) (i3 == i5 ? r7 : z3)));
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            if (i3 != 0) {
                if (i3 == r7) {
                    this.q = new g1(on0Var, n0Var, this.f);
                }
            } else {
                this.o = new vz2(r7, this);
                if (oj2.I(context)) {
                    this.p = new nm1(i4, this);
                }
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public final TabListRecyclerView.b a() {
        View viewU;
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.f.getLayoutManager();
        int iB = linearLayoutManager.b();
        return new TabListRecyclerView.b(iB, (iB == -1 || (viewU = linearLayoutManager.u(iB)) == null) ? 0 : viewU.getTop());
    }

    public final int b() {
        Context context = this.j;
        if (!zb2.d(context)) {
            return 0;
        }
        Rect rect = new Rect();
        this.f.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        this.m.getGlobalVisibleRect(rect2);
        rect.offset(0, (-rect2.top) - ((int) context.getResources().getDimension(R$dimen.toolbar_height_no_shadow)));
        return rect.top;
    }

    public final void c(df0 df0Var) {
        float fFloatValue;
        j0 j0Var = this.e;
        Context context = this.j;
        if (this.n) {
            return;
        }
        TraceEvent traceEventH0 = TraceEvent.H0("TabListCoordinator.initWithNative", null);
        try {
            this.n = true;
            j0Var.g();
            TabListRecyclerView tabListRecyclerView = this.f;
            if (df0Var != null) {
                tabListRecyclerView.getClass();
                s0 s0Var = new s0(tabListRecyclerView, tabListRecyclerView);
                tabListRecyclerView.U0 = s0Var;
                sk skVar = bq.a;
                try {
                    fFloatValue = Float.valueOf(N.MMltG$kc("TabToGTSAnimation", "downsampling-scale")).floatValue();
                } catch (NumberFormatException unused) {
                    fFloatValue = 0.5f;
                }
                if (s0Var.j != fFloatValue) {
                    s0Var.g(null);
                }
                s0Var.j = fFloatValue;
                tabListRecyclerView.T0 = df0Var;
            }
            int i = this.h;
            if ((i == 0 || i == 3) && this.l != 0) {
                float dimension = context.getResources().getDimension(R$dimen.swipe_to_dismiss_threshold);
                float dimension2 = context.getResources().getDimension(R$dimen.tab_grid_merge_threshold);
                float dimension3 = context.getResources().getDimension(R$dimen.bottom_sheet_peek_height);
                p pVar = j0Var.t;
                pVar.k = dimension;
                pVar.l = dimension2;
                pVar.m = dimension3;
                pVar.s = (o1.v(pVar.h) && pVar.p) ^ true ? 51 : 0;
                androidx.recyclerview.widget.l lVar = new androidx.recyclerview.widget.l(pVar);
                this.r = lVar;
                lVar.j(tabListRecyclerView);
                a aVar = new a(pVar);
                this.s = aVar;
                tabListRecyclerView.addOnItemTouchListener(aVar);
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    public final void d() {
        f13 f13Var;
        df0 df0Var;
        vz2 vz2Var = this.o;
        TabListRecyclerView tabListRecyclerView = this.f;
        if (vz2Var != null) {
            tabListRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(vz2Var);
        }
        nm1 nm1Var = this.p;
        if (nm1Var != null) {
            tabListRecyclerView.removeOnLayoutChangeListener(nm1Var);
        }
        if (tabListRecyclerView.U0 != null) {
            if (tabListRecyclerView.V0 && (df0Var = tabListRecyclerView.T0) != null) {
                df0Var.e(tabListRecyclerView.P0);
                tabListRecyclerView.V0 = false;
            }
            tabListRecyclerView.U0.i.b();
        }
        j0 j0Var = this.e;
        j0Var.a = false;
        RecyclerView recyclerView = j0Var.x;
        if (recyclerView != null && (f13Var = j0Var.y) != null) {
            recyclerView.removeOnScrollListener(f13Var);
            j0Var.y = null;
        }
        Tab tab = j0Var.c;
        if (tab != null) {
            j0Var.k(j0Var.H, j0Var.j(tab, !j0Var.r));
            j0Var.c = null;
        }
    }

    public final void e() {
        nm1 nm1Var = this.p;
        if (nm1Var != null) {
            this.f.addOnLayoutChangeListener(nm1Var);
        }
    }

    public final void g(int i, int i2) {
        int I;
        n0 n0Var = this.e.f;
        if (i != 3 && i != 6) {
            I = -1;
        } else {
            if (i2 == 5) {
                while (n0Var.H() != -1) {
                    n0Var.y(n0Var.H());
                }
                return;
            }
            I = n0Var.I(i2);
        }
        if (I == -1) {
            return;
        }
        n0Var.y(I);
    }

    public final boolean i(ArrayList arrayList, boolean z, boolean z2) {
        ArrayList arrayList2;
        n0 n0Var;
        boolean z3;
        j0 j0Var = this.e;
        j0Var.getClass();
        if (arrayList == null || !z2) {
            arrayList2 = arrayList;
        } else {
            arrayList2 = new ArrayList(arrayList);
            Collections.sort(arrayList2, j0.I);
        }
        j0Var.a = arrayList2 != null;
        if (arrayList != null && j0Var.g == 0 && j0Var.r && z7.Q()) {
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            long jCurrentTimeMillis = System.currentTimeMillis() - sharedPreferencesManager.e(-1L, "Chrome.PriceTracking.AnnotationsEnabledMetricsTimestamp");
            int millis = (int) TimeUnit.DAYS.toMillis(1L);
            if (a8.P()) {
                sk skVar = bq.a;
                millis = N.M37SqSAy("CommercePriceTracking", "price_annotations_enabled_metrics_window_duration_ms", millis);
            }
            if (jCurrentTimeMillis >= millis) {
                oj2.O("Commerce.PriceDrop.AnnotationsEnabled", k42.c());
                sharedPreferencesManager.n(System.currentTimeMillis(), "Chrome.PriceTracking.AnnotationsEnabledMetricsTimestamp");
            }
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            n0Var = j0Var.f;
            if (i >= n0Var.size()) {
                break;
            }
            if (n0Var.get(i).b.h(n0.a.a) == 0) {
                i2++;
            }
            i++;
        }
        if (arrayList2 != null) {
            if (arrayList2.size() == i2) {
                int i3 = 0;
                for (int i4 = 0; i4 < n0Var.size(); i4++) {
                    if (n0Var.get(i4).b.h(n0.a.a) == 0) {
                        int i5 = i3 + 1;
                        if (n0Var.get(i4).b.h(u0.a) == ((g72) arrayList2.get(i3)).c()) {
                            i3 = i5;
                        }
                    }
                }
                z3 = true;
            }
            z3 = false;
            break;
        }
        if (i2 != 0) {
            z3 = false;
            break;
        }
        z3 = true;
        y13 y13Var = j0Var.h;
        if (z3) {
            if (arrayList2 == null) {
                return true;
            }
            for (int i6 = 0; i6 < arrayList2.size(); i6++) {
                g72 g72Var = (g72) arrayList2.get(i6);
                j0Var.r(n0Var.G(i6), g72Var, j0Var.h(g72Var, y13Var.k()), false, z);
            }
            return true;
        }
        n0Var.A(new ArrayList());
        j0Var.H = -1;
        if (arrayList2 == null) {
            return true;
        }
        int iK = y13Var.k();
        for (int i7 = 0; i7 < arrayList2.size(); i7++) {
            g72 g72Var2 = (g72) arrayList2.get(i7);
            j0Var.b(g72Var2, i7, j0Var.h(g72Var2, iK));
        }
        return false;
    }

    public final void k() {
        int i = 0;
        while (true) {
            n0 n0Var = this.e.f;
            if (i >= n0Var.size()) {
                return;
            }
            if (n0Var.get(i).b.h(n0.a.a) == 0) {
                n0Var.get(i).b.p(u0.g, null);
                n0Var.get(i).b.p(u0.f, null);
            }
            i++;
        }
    }

    public final void m() {
        Rect rect;
        j0 j0Var = this.e;
        int iK = j0Var.u;
        if (iK == -1) {
            iK = j0Var.h.k();
        }
        int iF = j0Var.f.F(iK);
        if (j0Var.u == -1) {
            j0Var.h.k();
        }
        TabListRecyclerView tabListRecyclerView = this.f;
        m.b bVar = (m.b) tabListRecyclerView.F(iF);
        if (bVar == null || iF == -1) {
            rect = null;
        } else {
            View viewJ = ((ViewLookupCachingFrameLayout) bVar.e).j(R$id.tab_thumbnail);
            Rect rect2 = new Rect();
            rect = new Rect();
            tabListRecyclerView.getGlobalVisibleRect(rect2);
            viewJ.getGlobalVisibleRect(rect);
            rect.offset(-rect2.left, -rect2.top);
        }
        if (rect == null) {
            return;
        }
        rect.offset(0, b());
        this.i.set(rect);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // defpackage.v80
    public final void onDestroy() {
        f13 f13Var;
        j0 j0Var = this.e;
        m0 m0Var = j0Var.D;
        if (m0Var != null) {
            j0Var.f.e.c(m0Var);
        }
        y13 y13Var = j0Var.h;
        TabModel tabModelZ = y13Var.z();
        if (tabModelZ != null) {
            for (int i = 0; i < tabModelZ.getCount(); i++) {
                tabModelZ.getTabAt(i).t(j0Var.B);
            }
        }
        k0 k0Var = j0Var.C;
        if (k0Var != null) {
            y13Var.u().e(k0Var);
        }
        if (j0Var.F != null) {
            ((g03) y13Var.u().d(false)).W(j0Var.F);
            ((g03) y13Var.u().d(true)).W(j0Var.F);
        }
        d13 d13Var = j0Var.s;
        if (d13Var != null) {
            j0Var.e.unregisterComponentCallbacks(d13Var);
        }
        j0.f fVar = j0Var.E;
        if (fVar != null) {
            y13 y13Var2 = fVar.a;
            y13Var2.u().e(fVar.b);
            g03 g03Var = (g03) y13Var2.u().d(false);
            h03.b bVar = fVar.c;
            g03Var.W(bVar);
            ((g03) y13Var2.u().d(true)).W(bVar);
        }
        RecyclerView recyclerView = j0Var.x;
        if (recyclerView != null && (f13Var = j0Var.y) != null) {
            recyclerView.removeOnScrollListener(f13Var);
            j0Var.y = null;
        }
        vz2 vz2Var = this.o;
        TabListRecyclerView tabListRecyclerView = this.f;
        if (vz2Var != null) {
            tabListRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(vz2Var);
        }
        nm1 nm1Var = this.p;
        if (nm1Var != null) {
            tabListRecyclerView.removeOnLayoutChangeListener(nm1Var);
        }
        tabListRecyclerView.setRecyclerListener(null);
        g1 g1Var = this.q;
        if (g1Var != null) {
            g1Var.c.removeOnScrollListener(g1Var.d);
            org.chromium.ui.modelutil.g gVar = g1Var.e;
            gVar.f.e.c(gVar);
            gVar.c(Collections.emptySet());
        }
        if (this.r != null) {
            try {
                Field declaredField = androidx.recyclerview.widget.l.class.getDeclaredField("c");
                declaredField.setAccessible(true);
                declaredField.set(this.r, null);
            } catch (Exception e) {
                Log.e("cr_".concat(c0.class.getSimpleName()), "onDestroy: " + e);
            }
            this.r.j(null);
        }
        a aVar = this.s;
        if (aVar != null) {
            tabListRecyclerView.removeOnItemTouchListener(aVar);
        }
    }

    /* JADX INFO: compiled from: TabListCoordinator.java */
    public class a implements RecyclerView.p {
        public final /* synthetic */ p a;

        public a(p pVar) {
            this.a = pVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.p
        public final boolean b(MotionEvent motionEvent) {
            p pVar = this.a;
            boolean z = pVar.r;
            pVar.r = false;
            return z && (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 6);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.p
        public final void e(boolean z) {
            if (z) {
                this.a.r = false;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.p
        public final void a(MotionEvent motionEvent) {
        }
    }
}
