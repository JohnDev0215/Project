package org.chromium.chrome.browser.gesturenav;

import android.R;
import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import defpackage.du;
import defpackage.h21;
import defpackage.i40;
import defpackage.l10;
import defpackage.oj2;
import defpackage.zc0;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes.dex */
public class SideSlideLayout extends ViewGroup {
    public static boolean A;
    public static long z;
    public final DecelerateInterpolator e;
    public final float f;
    public final int g;
    public float h;
    public d i;
    public e j;
    public boolean k;
    public int l;
    public float m;
    public boolean n;
    public final NavigationBubble o;
    public int p;
    public int q;
    public int r;
    public AnimationSet s;
    public int t;
    public boolean u;
    public int v;
    public boolean w;
    public final a x;
    public final b y;

    public class b extends Animation {
        public b() {
        }

        @Override // android.view.animation.Animation
        public final void applyTransformation(float f, Transformation transformation) {
            SideSlideLayout sideSlideLayout = SideSlideLayout.this;
            int left = (sideSlideLayout.q + ((int) ((sideSlideLayout.r - r3) * f))) - sideSlideLayout.o.getLeft();
            sideSlideLayout.m += left;
            Math.min(1.0f, sideSlideLayout.getOverscroll() / sideSlideLayout.f);
            sideSlideLayout.setTargetOffsetLeftAndRight(left);
        }
    }

    public interface d {
    }

    public interface e {
    }

    public SideSlideLayout(Context context) {
        super(context);
        this.x = new a();
        b bVar = new b();
        this.y = bVar;
        getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.e = new DecelerateInterpolator(2.0f);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.navigation_bubble_size);
        this.g = dimensionPixelSize;
        NavigationBubble navigationBubble = (NavigationBubble) LayoutInflater.from(getContext()).inflate(R$layout.navigation_bubble, (ViewGroup) null);
        this.o = navigationBubble;
        navigationBubble.getTextView().setText(getResources().getString(R$string.overscroll_navigation_close_chrome, getContext().getString(R$string.app_name)));
        this.p = dimensionPixelSize;
        addView(navigationBubble);
        this.f = getResources().getDisplayMetrics().density * 32.0f;
        bVar.setAnimationListener(new c());
    }

    private void setNavigating(boolean z2) {
        if (this.k != z2) {
            this.k = z2;
            if (z2) {
                e(this.x);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetOffsetLeftAndRight(int i) {
        NavigationBubble navigationBubble = this.o;
        navigationBubble.offsetLeftAndRight(i);
        this.l = navigationBubble.getLeft();
    }

    public final void b(float f) {
        float f2 = f - this.m;
        if (isEnabled() && this.n) {
            float f3 = this.f;
            float f4 = f3 / 3.0f;
            this.m += Math.max(-f4, Math.min(f4, f2));
            float overscroll = getOverscroll();
            float f5 = overscroll - f3;
            if (overscroll > this.h) {
                this.h = overscroll;
            }
            double dMax = Math.max(0.0f, Math.min(f5, f3 * 2.0f) / f3) / 4.0f;
            float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * 2.0f;
            NavigationBubble navigationBubble = this.o;
            if (navigationBubble.getVisibility() != 0) {
                navigationBubble.setVisibility(0);
            }
            float fMin = Math.min(1.0f, Math.abs(overscroll / f3));
            boolean z2 = getOverscroll() > 3.0f * f3;
            if (z2 != this.w) {
                navigationBubble.setImageTint(z2);
                if (z2) {
                    performHapticFeedback(3);
                }
            }
            this.w = z2;
            int i = this.v;
            if (i != 0) {
                if (z2) {
                    navigationBubble.b(i);
                    this.p = navigationBubble.getMeasuredWidth();
                } else {
                    navigationBubble.b(0);
                    this.p = this.g;
                }
            }
            int i2 = (int) ((f3 * fMin) + (fPow * f3 * 2.0f));
            int i3 = this.r;
            if (this.u) {
                i2 = -i2;
            }
            setTargetOffsetLeftAndRight((i3 + i2) - this.l);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(boolean z2) {
        boolean z3;
        if (this.n) {
            this.n = false;
            Object[] objArr = this.h >= ((float) (this.p / 3));
            if (objArr != false) {
                oj2.U(this.u ? 1 : 0, 2, "GestureNavigation.Activated");
            }
            if (isEnabled()) {
                if ((getOverscroll() > this.f * 3.0f) != false) {
                    if (!z2) {
                        this.k = false;
                        e(this.x);
                        return;
                    }
                    setNavigating(true);
                    oj2.U(this.u ? 1 : 0, 2, "GestureNavigation.Completed");
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long j = z;
                    if (j > 0 && jCurrentTimeMillis - j < 3000 && (z3 = this.u) != A) {
                        oj2.U(z3 ? 1 : 0, 2, "GestureNavigation.Reversed");
                    }
                    z = jCurrentTimeMillis;
                    A = this.u;
                    return;
                }
            }
            this.k = false;
            this.q = this.l;
            b bVar = this.y;
            bVar.reset();
            bVar.setDuration(500L);
            bVar.setInterpolator(this.e);
            NavigationBubble navigationBubble = this.o;
            navigationBubble.clearAnimation();
            navigationBubble.startAnimation(bVar);
            if (objArr == true) {
                oj2.U(this.u ? 1 : 0, 2, "GestureNavigation.Cancelled");
            }
        }
    }

    public final void d() {
        this.n = false;
        setNavigating(false);
        NavigationBubble navigationBubble = this.o;
        navigationBubble.b(0);
        this.p = this.g;
        setTargetOffsetLeftAndRight(this.r - this.l);
        this.l = navigationBubble.getLeft();
        e eVar = this.j;
        if (eVar != null) {
            du duVar = (du) eVar;
            org.chromium.chrome.browser.gesturenav.a aVar = (org.chromium.chrome.browser.gesturenav.a) duVar.e;
            SideSlideLayout sideSlideLayout = (SideSlideLayout) duVar.f;
            if (aVar.k != null) {
                return;
            }
            l10 l10Var = new l10(16, aVar);
            aVar.k = l10Var;
            sideSlideLayout.post(l10Var);
        }
    }

    public final void e(a aVar) {
        d dVar;
        if (this.k && (dVar = this.i) != null) {
            boolean z2 = this.u;
            i40 i40Var = (i40) dVar;
            org.chromium.chrome.browser.gesturenav.a aVar2 = (org.chromium.chrome.browser.gesturenav.a) i40Var.e;
            SideSlideLayout sideSlideLayout = (SideSlideLayout) i40Var.f;
            aVar2.g.onResult(Boolean.valueOf(z2));
            zc0 zc0Var = aVar2.j;
            if (zc0Var != null) {
                aVar2.h.removeCallbacks(zc0Var);
                aVar2.j = null;
            }
            if (aVar2.j == null) {
                aVar2.j = new zc0(7, aVar2);
            }
            sideSlideLayout.post(aVar2.j);
        }
        AnimationSet animationSet = this.s;
        NavigationBubble navigationBubble = this.o;
        if (animationSet == null || this.t != this.p) {
            this.t = this.p;
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, this.p / 2, navigationBubble.getHeight() / 2);
            scaleAnimation.setInterpolator(h21.f);
            scaleAnimation.setDuration(600L);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setInterpolator(this.e);
            alphaAnimation.setDuration(600L);
            AnimationSet animationSet2 = new AnimationSet(false);
            this.s = animationSet2;
            animationSet2.addAnimation(alphaAnimation);
            this.s.addAnimation(scaleAnimation);
        }
        navigationBubble.setAnimationListener(aVar);
        navigationBubble.clearAnimation();
        navigationBubble.startAnimation(this.s);
    }

    public final void f() {
        setNavigating(false);
    }

    public float getOverscroll() {
        boolean z2 = this.u;
        float f = this.m;
        return z2 ? -Math.min(0.0f, f) : Math.max(0.0f, f);
    }

    public float getPullOffset() {
        return this.m;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i, int i2, int i3, int i4) {
        if (getChildCount() == 0) {
            return;
        }
        int measuredHeight = getMeasuredHeight();
        NavigationBubble navigationBubble = this.o;
        int measuredWidth = navigationBubble.getMeasuredWidth();
        int measuredHeight2 = navigationBubble.getMeasuredHeight();
        int i5 = this.l;
        int i6 = measuredHeight / 2;
        int i7 = measuredHeight2 / 2;
        navigationBubble.layout(i5, i6 - i7, measuredWidth + i5, i6 + i7);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.o.measure(View.MeasureSpec.makeMeasureSpec(this.p, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(this.g, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    public void setCloseIndicator(int i) {
        this.v = i;
    }

    public void setDirection(boolean z2) {
        this.u = z2;
        this.o.setIcon(z2 ? R$drawable.ic_arrow_forward_blue_24dp : R$drawable.ic_arrow_back_24dp);
    }

    public void setOnNavigationListener(d dVar) {
        this.i = dVar;
    }

    public void setOnResetListener(e eVar) {
        this.j = eVar;
    }

    public class a implements Animation.AnimationListener {
        public a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationEnd(Animation animation) {
            SideSlideLayout sideSlideLayout = SideSlideLayout.this;
            sideSlideLayout.o.a();
            NavigationBubble navigationBubble = sideSlideLayout.o;
            navigationBubble.setVisibility(4);
            if (!sideSlideLayout.k) {
                sideSlideLayout.d();
            }
            navigationBubble.b(0);
            sideSlideLayout.p = sideSlideLayout.g;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationStart(Animation animation) {
        }
    }

    public class c implements Animation.AnimationListener {
        public c() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationEnd(Animation animation) {
            SideSlideLayout.this.d();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationStart(Animation animation) {
        }
    }
}
