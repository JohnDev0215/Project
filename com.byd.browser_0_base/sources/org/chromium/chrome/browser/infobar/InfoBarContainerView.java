package org.chromium.chrome.browser.infobar;

import J.N;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import defpackage.bq;
import defpackage.fj;
import defpackage.kj;
import defpackage.l10;
import defpackage.sk;
import defpackage.tz0;
import defpackage.vz0;
import defpackage.ya0;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.components.browser_ui.banners.SwipableOverlayView;
import org.chromium.components.infobars.InfoBarContainerLayout;

/* JADX INFO: loaded from: classes.dex */
public class InfoBarContainerView extends SwipableOverlayView implements kj.a {
    public static boolean v = true;
    public final kj o;
    public final a p;
    public final InfoBarContainerLayout q;
    public ViewGroup r;
    public Animator s;
    public boolean t;
    public int u;

    public interface a extends tz0 {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InfoBarContainerView(Context context, a aVar, fj fjVar, boolean z) {
        super(context, null, !N.M09VlOh_("InfobarScrollOptimization"));
        sk skVar = bq.a;
        this.p = aVar;
        this.o = fjVar;
        if (fjVar != null && N.M09VlOh_("InfobarScrollOptimization")) {
            fjVar.o(this);
        }
        setVerticalScrollBarEnabled(false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
        layoutParams.topMargin = (int) (((z ? 144 : 104) * ya0.a(context).d) + 0.5f);
        setLayoutParams(layoutParams);
        InfoBarContainerLayout infoBarContainerLayout = new InfoBarContainerLayout(context, new l10(22, this), new c(this));
        this.q = infoBarContainerLayout;
        addView(infoBarContainerLayout, new FrameLayout.LayoutParams(-1, -2, 1));
    }

    public static void setIsAllowedToAutoHide(boolean z) {
        v = z;
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        if (v) {
            setTranslationY(((getTotalHeight() * 1.0f) * Math.abs(i)) / this.o.l());
        }
    }

    @Override // org.chromium.components.browser_ui.banners.SwipableOverlayView
    public final boolean d() {
        return v;
    }

    @Override // org.chromium.components.browser_ui.banners.SwipableOverlayView
    public final boolean e() {
        return this.s != null;
    }

    @Override // org.chromium.components.browser_ui.banners.SwipableOverlayView
    public final void g(boolean z) {
        Animator animator = this.s;
        if (animator != null) {
            animator.cancel();
        }
        super.g(z);
    }

    @Override // org.chromium.components.browser_ui.banners.SwipableOverlayView
    public final boolean h(int i) {
        kj kjVar = this.o;
        if (kjVar.g() <= 0) {
            return true;
        }
        boolean z = i > this.u;
        boolean z2 = z != this.t;
        this.u = i;
        this.t = z;
        if (!z2) {
            boolean z3 = kjVar.u() > 0;
            boolean z4 = kjVar.p() == 1.0f;
            boolean z5 = this.t;
            return (z5 || !z3) && (!z5 || z4);
        }
        ObjectAnimator objectAnimatorB = b(i(i));
        this.s = objectAnimatorB;
        objectAnimatorB.addListener(new vz0(this));
        this.s.start();
        return false;
    }

    public final void k() {
        ViewGroup viewGroup = this.r;
        if (viewGroup != null && getParent() == null) {
            viewGroup.addView(this, new FrameLayout.LayoutParams(-1, -2, 81));
            addOnLayoutChangeListener(this.f);
        }
    }

    @Override // org.chromium.components.browser_ui.banners.SwipableOverlayView, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getVisibility() != 8) {
            setVisibility(0);
            setAlpha(0.0f);
            animate().alpha(1.0f).setDuration(250L);
        }
    }

    public void setHidden(boolean z) {
        setVisibility(z ? 8 : 0);
    }

    public void setParentView(ViewGroup viewGroup) {
        this.r = viewGroup;
        if (f()) {
            k();
        }
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        super.setTranslationY(f);
        if (getHeight() > 0) {
            getHeight();
        }
        Iterator<InfoBarContainer.f> it = InfoBarContainer.this.h.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((InfoBarContainer.f) aVar.next()).c();
            }
        }
    }
}
