package org.chromium.chrome.browser.findinpage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import defpackage.h21;
import defpackage.vl;
import org.chromium.chrome.R$dimen;

/* JADX INFO: loaded from: classes.dex */
public class FindToolbarTablet extends FindToolbar {
    public ObjectAnimator E;
    public ObjectAnimator F;
    public ObjectAnimator G;
    public final int H;

    public class a extends vl {
        public a() {
        }

        @Override // defpackage.vl
        public final void b() {
            FindToolbarTablet.this.E = null;
        }

        @Override // defpackage.vl
        public final void c() {
            FindToolbarTablet findToolbarTablet = FindToolbarTablet.this;
            findToolbarTablet.setVisibility(0);
            findToolbarTablet.postInvalidateOnAnimation();
            FindToolbarTablet.super.l();
        }
    }

    public class b extends vl {
        public b() {
        }

        @Override // defpackage.vl
        public final void b() {
            FindToolbarTablet findToolbarTablet = FindToolbarTablet.this;
            findToolbarTablet.setVisibility(8);
            findToolbarTablet.E = null;
        }

        @Override // defpackage.vl
        public final void c() {
            FindToolbarTablet findToolbarTablet = FindToolbarTablet.this;
            findToolbarTablet.setVisibility(0);
            findToolbarTablet.postInvalidateOnAnimation();
        }
    }

    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            FindToolbarTablet.this.E = null;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            FindToolbarTablet.this.postInvalidateOnAnimation();
        }
    }

    public FindToolbarTablet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.H = (int) (context.getResources().getDisplayMetrics().density * 8.0f);
    }

    private void setMakeRoomForResults(boolean z) {
        float f = z ? -(getHeight() - this.H) : 0.0f;
        if (f == getTranslationY()) {
            return;
        }
        ObjectAnimator objectAnimator = this.E;
        if (objectAnimator != null) {
            if (objectAnimator == this.F || objectAnimator == this.G) {
                objectAnimator.end();
            } else {
                objectAnimator.cancel();
            }
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<FindToolbarTablet, Float>) View.TRANSLATION_Y, f);
        this.E = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(200L);
        this.E.setInterpolator(h21.a);
        this.E.addListener(new c());
        this.q.v(this.E);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setShowState(boolean r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L2d
            int r0 = r2.getVisibility()
            if (r0 == 0) goto L2d
            android.animation.ObjectAnimator r0 = r2.E
            android.animation.ObjectAnimator r1 = r2.F
            if (r0 == r1) goto L2d
            android.view.View r3 = r2.getRootView()
            int r0 = org.chromium.chrome.R$id.toolbar
            android.view.View r3 = r3.findViewById(r0)
            android.view.ViewGroup$LayoutParams r0 = r2.getLayoutParams()
            android.widget.FrameLayout$LayoutParams r0 = (android.widget.FrameLayout.LayoutParams) r0
            int r3 = r3.getBottom()
            int r1 = r2.H
            int r3 = r3 - r1
            r0.topMargin = r3
            r2.setLayoutParams(r0)
            android.animation.ObjectAnimator r3 = r2.F
            goto L43
        L2d:
            if (r3 != 0) goto L42
            int r3 = r2.getVisibility()
            r0 = 8
            if (r3 == r0) goto L42
            android.animation.ObjectAnimator r3 = r2.E
            android.animation.ObjectAnimator r0 = r2.G
            if (r3 == r0) goto L42
            r2.s()
            r3 = r0
            goto L43
        L42:
            r3 = 0
        L43:
            if (r3 == 0) goto L56
            android.animation.ObjectAnimator r0 = r2.E
            if (r0 == 0) goto L4c
            r0.cancel()
        L4c:
            r2.E = r3
            org.chromium.ui.base.WindowAndroid r0 = r2.q
            r0.v(r3)
            r2.postInvalidateOnAnimation()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.findinpage.FindToolbarTablet.setShowState(boolean):void");
    }

    @Override // org.chromium.chrome.browser.findinpage.FindToolbar
    public final void f() {
        super.f();
        setMakeRoomForResults(false);
    }

    @Override // org.chromium.chrome.browser.findinpage.FindToolbar
    public final void j(Rect rect) {
        float f = getContext().getResources().getDisplayMetrics().density;
        boolean z = false;
        if (rect != null && rect.intersects((int) (getLeft() / f), 0, (int) (getRight() / f), (int) (getHeight() / f))) {
            z = true;
        }
        setMakeRoomForResults(z);
    }

    @Override // org.chromium.chrome.browser.findinpage.FindToolbar
    public final void l() {
        if (this.E == this.F) {
            return;
        }
        setShowState(true);
    }

    @Override // org.chromium.chrome.browser.findinpage.FindToolbar, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setVisibility(8);
        Resources resources = getContext().getResources();
        float dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.find_in_page_popup_margin_end) + resources.getDimensionPixelSize(R$dimen.find_in_page_popup_width);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, (Property<FindToolbarTablet, Float>) View.TRANSLATION_X, dimensionPixelOffset, 0.0f);
        this.F = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(200L);
        ObjectAnimator objectAnimator = this.F;
        DecelerateInterpolator decelerateInterpolator = h21.a;
        objectAnimator.setInterpolator(decelerateInterpolator);
        this.F.addListener(new a());
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, (Property<FindToolbarTablet, Float>) View.TRANSLATION_X, 0.0f, dimensionPixelOffset);
        this.G = objectAnimatorOfFloat2;
        objectAnimatorOfFloat2.setDuration(200L);
        this.G.setInterpolator(decelerateInterpolator);
        this.G.addListener(new b());
    }

    @Override // org.chromium.chrome.browser.findinpage.FindToolbar
    public final void q(boolean z) {
        if (this.E != this.G) {
            setShowState(false);
        }
        super.q(z);
    }
}
