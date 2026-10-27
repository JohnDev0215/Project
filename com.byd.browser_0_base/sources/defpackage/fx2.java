package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content_public.browser.WebContents;
import org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout;
import org.chromium.ui.OverscrollRefreshHandler;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SwipeRefreshHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class fx2 extends m53 implements OverscrollRefreshHandler {
    public int f;
    public SwipeRefreshLayout g;
    public final Tab h;
    public ContentView i;
    public wv j;
    public androidx.appcompat.widget.a k;
    public String l;
    public iv0 m;

    /* JADX INFO: compiled from: SwipeRefreshHandler.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
            if (windowAndroid == null) {
                fx2 fx2Var = fx2.this;
                if (fx2Var.g != null) {
                    fx2Var.d();
                    fx2Var.f();
                    fx2Var.g.setOnRefreshListener(null);
                    fx2Var.g.setOnResetListener(null);
                    fx2Var.g = null;
                }
            }
        }
    }

    public fx2(Tab tab) {
        super(tab);
        this.h = tab;
        tab.x(new a());
    }

    public static fx2 g(Tab tab) {
        fx2 fx2Var = (fx2) tab.B().b(fx2.class);
        return fx2Var == null ? (fx2) tab.B().d(fx2.class, new fx2(tab)) : fx2Var;
    }

    @Override // defpackage.m53
    @SuppressLint({"NewApi"})
    public final void a(WebContents webContents) {
        f();
        this.i = null;
        this.m = null;
        reset();
    }

    @Override // defpackage.m53
    public final void b() {
        SwipeRefreshLayout swipeRefreshLayout = this.g;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setOnRefreshListener(null);
            this.g.setOnResetListener(null);
        }
    }

    @Override // defpackage.m53
    @SuppressLint({"NewApi"})
    public final void c(WebContents webContents) {
        webContents.O(this);
        this.i = this.h.d();
    }

    public final void d() {
        if (this.j != null) {
            ThreadUtils.c().removeCallbacks(this.j);
        }
    }

    public final void f() {
        if (this.g == null) {
            return;
        }
        if (this.k != null) {
            ThreadUtils.c().removeCallbacks(this.k);
            this.k = null;
        }
        if (this.g.getParent() != null) {
            this.i.removeView(this.g);
        }
    }

    @Override // org.chromium.ui.OverscrollRefreshHandler
    public final void pull(float f, float f2) {
        iv0 iv0Var;
        wk1 wk1Var;
        TraceEvent.q("SwipeRefreshHandler.pull", null);
        int i = this.f;
        if (i == 1) {
            this.g.c(f2);
        } else if (i == 2 && (iv0Var = this.m) != null && (wk1Var = iv0Var.n) != null) {
            float f3 = wk1Var.j + f;
            wk1Var.j = f3;
            int i2 = wk1Var.h;
            PropertyModel propertyModel = wk1Var.i;
            if (i2 == 2) {
                propertyModel.m(ns0.d, f3);
            } else if (i2 == 3) {
                propertyModel.m(ns0.e, f3);
            }
        }
        TraceEvent.C("SwipeRefreshHandler.pull");
    }

    @Override // org.chromium.ui.OverscrollRefreshHandler
    public final void release(boolean z) {
        iv0 iv0Var;
        wk1 wk1Var;
        TraceEvent.q("SwipeRefreshHandler.release", null);
        int i = this.f;
        if (i == 1) {
            this.g.d(z);
        } else if (i == 2 && (iv0Var = this.m) != null && (wk1Var = iv0Var.n) != null) {
            wk1Var.d(z);
        }
        TraceEvent.C("SwipeRefreshHandler.release");
    }

    @Override // org.chromium.ui.OverscrollRefreshHandler
    public final void reset() {
        wk1 wk1Var;
        d();
        SwipeRefreshLayout swipeRefreshLayout = this.g;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.e();
        }
        iv0 iv0Var = this.m;
        if (iv0Var == null || (wk1Var = iv0Var.n) == null) {
            return;
        }
        wk1Var.e();
    }

    @Override // org.chromium.ui.OverscrollRefreshHandler
    public final boolean start(int i, float f, float f2, boolean z) {
        iv0 iv0Var;
        this.f = i;
        Tab tab = this.h;
        if (i != 1) {
            if (i != 2 || (iv0Var = this.m) == null) {
                this.f = 0;
                return false;
            }
            wk1 wk1Var = iv0Var.n;
            if (wk1Var != null) {
                wk1Var.h = 1;
            }
            return (z && !tab.i()) || (wk1Var != null && wk1Var.f(f, f2, z));
        }
        if (this.g == null) {
            final Context context = tab.getContext();
            SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(context);
            this.g = swipeRefreshLayout;
            swipeRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            boolean zIsIncognito = tab.isIncognito();
            int color = bq.d.a() ? context.getColor(R$color.default_bg_color_dark_elev_2_gm3_baseline) : context.getColor(R$color.default_bg_color_dark_elev_2_baseline);
            if (!zIsIncognito) {
                color = yp.c(context, R$dimen.default_elevation_2);
            }
            this.g.setProgressBackgroundColorSchemeColor(color);
            this.g.setColorSchemeColors(zIsIncognito ? context.getColor(R$color.default_icon_color_blue_light) : ii2.c(context));
            if (this.i != null) {
                this.g.setEnabled(true);
            }
            this.g.setOnRefreshListener(new SwipeRefreshLayout.e() { // from class: ex2
                @Override // org.chromium.third_party.android.swiperefresh.SwipeRefreshLayout.e
                public final void c() {
                    fx2 fx2Var = this.e;
                    fx2Var.d();
                    d73 d73Var = rh3.a;
                    if (fx2Var.j == null) {
                        fx2Var.j = new wv(13, fx2Var);
                    }
                    PostTask.b(d73Var, fx2Var.j, 7500L);
                    if (fx2Var.l == null) {
                        fx2Var.l = context.getResources().getString(R$string.accessibility_swipe_refresh);
                    }
                    fx2Var.g.announceForAccessibility(fx2Var.l);
                    fx2Var.h.a();
                    n3.X0("MobilePullGestureReload");
                }
            });
            this.g.setOnResetListener(new pq2(3, this));
        }
        if (this.k != null) {
            ThreadUtils.c().removeCallbacks(this.k);
            this.k = null;
        }
        if (this.g.getParent() == null) {
            this.i.addView(this.g);
        }
        return this.g.h();
    }
}
