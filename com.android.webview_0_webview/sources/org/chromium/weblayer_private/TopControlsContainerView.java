package org.chromium.weblayer_private;

import J.N;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import defpackage.HP;
import defpackage.NQ;
import defpackage.OQ;
import defpackage.PN;
import defpackage.TO;
import defpackage.ViewOnLayoutChangeListenerC0493aP;
import defpackage.ZO;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.resources.ResourceManager;
import org.chromium.weblayer_private.TopControlsContainerView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TopControlsContainerView extends FrameLayout {
    public int A;
    public boolean B;
    public boolean C;
    public Runnable D;
    public final OQ E;
    public final Runnable F;
    public long s;
    public ViewOnLayoutChangeListenerC0493aP t;
    public int u;
    public int v;
    public View w;
    public ContentViewRenderView x;
    public WebContents y;
    public PN z;

    public TopControlsContainerView(Context context, ContentViewRenderView contentViewRenderView, OQ oq) {
        super(context);
        this.F = new Runnable(this) { // from class: JQ
            public final TopControlsContainerView s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                TopControlsContainerView topControlsContainerView = this.s;
                if (topControlsContainerView.w == null) {
                    return;
                }
                N.Mxe_p5sj(topControlsContainerView.s, topControlsContainerView);
            }
        };
        this.x = contentViewRenderView;
        this.z = new PN(new NQ(this));
        this.s = N.MxBee$83(this, contentViewRenderView.v);
        this.E = oq;
    }

    private void didToggleFullscreenModeForTab(final boolean z) {
        if (this.D != null) {
            getHandler().removeCallbacks(this.D);
        }
        this.D = new Runnable(this, z) { // from class: MQ
            public final TopControlsContainerView s;
            public final boolean t;

            {
                this.s = this;
                this.t = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                TopControlsContainerView topControlsContainerView = this.s;
                boolean z2 = this.t;
                topControlsContainerView.D = null;
                if (topControlsContainerView.C == z2) {
                    return;
                }
                topControlsContainerView.C = z2;
                View view = topControlsContainerView.w;
                if (view == null) {
                    return;
                }
                if (z2) {
                    topControlsContainerView.b();
                    topControlsContainerView.d(-topControlsContainerView.v, 0);
                } else {
                    view.setVisibility(0);
                    topControlsContainerView.d(0, topControlsContainerView.v);
                }
            }
        };
        postDelayed(this.D, z ? 500L : 0L);
    }

    public final void a() {
        this.t = new ViewOnLayoutChangeListenerC0493aP(this.w);
        ZO zo = (ZO) ((ResourceManager) N.M7ewD88L(this.x.v)).a.get(1);
        zo.c.put(1001, this.t);
        N.M_eicmWU(this.s, this, 1001);
        this.u = getWidth();
        int height = getHeight();
        this.v = height;
        N.M6kNhy4_(this.s, this, this.u, height);
        d(0, this.v);
    }

    public final void b() {
        View view = this.w;
        if (view != null) {
            view.setVisibility(4);
        }
    }

    public final void c() {
        if (this.t == null || this.w.getVisibility() != 0) {
            return;
        }
        ViewOnLayoutChangeListenerC0493aP viewOnLayoutChangeListenerC0493aP = this.t;
        viewOnLayoutChangeListenerC0493aP.b.set(0, 0, viewOnLayoutChangeListenerC0493aP.a.getWidth(), viewOnLayoutChangeListenerC0493aP.a.getHeight());
        removeCallbacks(this.F);
        postOnAnimation(this.F);
    }

    public final void d(int i, int i2) {
        this.A = i2;
        if (i2 == 0 || i2 == getHeight()) {
            ((HP) this.E).a();
        }
        N.MSVvJOat(this.s, this, i, i2);
    }

    public void e(View view) {
        View view2 = this.w;
        if (view2 == view) {
            return;
        }
        if (view2 != null) {
            if (view2.getParent() == this) {
                removeView(this.w);
            }
            this.t = null;
            N.M0ErzYW_(this.s, this);
            ZO zo = (ZO) ((ResourceManager) N.M7ewD88L(this.x.v)).a.get(1);
            zo.c.remove(1001);
            TO to = zo.b;
            if (to != null) {
                int i = zo.a;
                ResourceManager resourceManager = (ResourceManager) to;
                if (i == 2 || i == 1) {
                    long j = resourceManager.d;
                    if (j != 0) {
                        N.MxwZmAzJ(j, resourceManager, i, 1001);
                    }
                }
            }
        }
        this.w = view;
        if (view == null) {
            return;
        }
        addView(view, new FrameLayout.LayoutParams(-1, -2, -1));
        if (getWidth() > 0 && getHeight() > 0) {
            view.layout(0, 0, getWidth(), getHeight());
            a();
        }
        if (this.C) {
            b();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        c();
        return super.invalidateChildInParent(iArr, rect);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onDescendantInvalidated(View view, View view2) {
        super.onDescendantInvalidated(view, view2);
        c();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.w == null) {
            return;
        }
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (i6 == this.v && i5 == this.u) {
            return;
        }
        this.u = i5;
        this.v = i6;
        if (i5 <= 0 || i6 <= 0) {
            return;
        }
        if (this.t == null) {
            a();
        } else {
            N.M6kNhy4_(this.s, this, i5, i6);
        }
    }
}
