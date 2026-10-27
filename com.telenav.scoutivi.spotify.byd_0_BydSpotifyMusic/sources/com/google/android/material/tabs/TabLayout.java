package com.google.android.material.tabs;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.u0;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.l;
import i0.t;
import i0.u;
import j0.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import o2.j;
import o2.k;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class TabLayout extends HorizontalScrollView {
    private static final int T = k.Widget_Design_TabLayout;
    private static final h0.e<f> U = new h0.g(16);
    private int A;
    int B;
    int C;
    int D;
    int E;
    boolean F;
    boolean G;
    int H;
    boolean I;
    private com.google.android.material.tabs.b J;
    private c K;
    private final ArrayList<c> L;
    private c M;
    private ValueAnimator N;
    ViewPager O;
    private g P;
    private b Q;
    private boolean R;
    private final h0.e<h> S;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final ArrayList<f> f4709e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private f f4710g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final e f4711h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f4712i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    int f4713j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    int f4714k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f4715l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    int f4716m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    ColorStateList f4717n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    ColorStateList f4718o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    ColorStateList f4719p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    Drawable f4720q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f4721r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    PorterDuff.Mode f4722s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    float f4723t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    float f4724u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    final int f4725v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    int f4726w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final int f4727x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final int f4728y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private final int f4729z;

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
        }
    }

    private class b implements ViewPager.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f4731a;

        b() {
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void a(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.O == viewPager) {
                tabLayout.I(aVar2, this.f4731a);
            }
        }

        void b(boolean z6) {
            this.f4731a = z6;
        }
    }

    @Deprecated
    public interface c<T extends f> {
        void a(T t6);

        void b(T t6);

        void c(T t6);
    }

    public interface d extends c<f> {
    }

    class e extends LinearLayout {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        ValueAnimator f4733e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f4734g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        float f4735h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f4736i;

        class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ View f4738a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ View f4739b;

            a(View view, View view2) {
                this.f4738a = view;
                this.f4739b = view2;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                e.this.g(this.f4738a, this.f4739b, valueAnimator.getAnimatedFraction());
            }
        }

        class b extends AnimatorListenerAdapter {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f4741a;

            b(int i7) {
                this.f4741a = i7;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                e.this.f4734g = this.f4741a;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                e.this.f4734g = this.f4741a;
            }
        }

        e(Context context) {
            super(context);
            this.f4734g = -1;
            this.f4736i = -1;
            setWillNotDraw(false);
        }

        private void d() {
            View childAt = getChildAt(this.f4734g);
            com.google.android.material.tabs.b bVar = TabLayout.this.J;
            TabLayout tabLayout = TabLayout.this;
            bVar.d(tabLayout, childAt, tabLayout.f4720q);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(View view, View view2, float f7) {
            if (view != null && view.getWidth() > 0) {
                com.google.android.material.tabs.b bVar = TabLayout.this.J;
                TabLayout tabLayout = TabLayout.this;
                bVar.c(tabLayout, view, view2, f7, tabLayout.f4720q);
            } else {
                Drawable drawable = TabLayout.this.f4720q;
                drawable.setBounds(-1, drawable.getBounds().top, -1, TabLayout.this.f4720q.getBounds().bottom);
            }
            u.b0(this);
        }

        private void h(boolean z6, int i7, int i8) {
            View childAt = getChildAt(this.f4734g);
            View childAt2 = getChildAt(i7);
            if (childAt2 == null) {
                d();
                return;
            }
            a aVar = new a(childAt, childAt2);
            if (!z6) {
                this.f4733e.removeAllUpdateListeners();
                this.f4733e.addUpdateListener(aVar);
                return;
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            this.f4733e = valueAnimator;
            valueAnimator.setInterpolator(p2.a.f8821b);
            valueAnimator.setDuration(i8);
            valueAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimator.addUpdateListener(aVar);
            valueAnimator.addListener(new b(i7));
            valueAnimator.start();
        }

        void b(int i7, int i8) {
            ValueAnimator valueAnimator = this.f4733e;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.f4733e.cancel();
            }
            h(true, i7, i8);
        }

        boolean c() {
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                if (getChildAt(i7).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            int iHeight = TabLayout.this.f4720q.getBounds().height();
            if (iHeight < 0) {
                iHeight = TabLayout.this.f4720q.getIntrinsicHeight();
            }
            int i7 = TabLayout.this.D;
            int height = 0;
            if (i7 == 0) {
                height = getHeight() - iHeight;
                iHeight = getHeight();
            } else if (i7 == 1) {
                height = (getHeight() - iHeight) / 2;
                iHeight = (getHeight() + iHeight) / 2;
            } else if (i7 != 2) {
                iHeight = i7 != 3 ? 0 : getHeight();
            }
            if (TabLayout.this.f4720q.getBounds().width() > 0) {
                Rect bounds = TabLayout.this.f4720q.getBounds();
                TabLayout.this.f4720q.setBounds(bounds.left, height, bounds.right, iHeight);
                TabLayout tabLayout = TabLayout.this;
                Drawable drawableL = tabLayout.f4720q;
                if (tabLayout.f4721r != 0) {
                    drawableL = a0.a.l(drawableL);
                    a0.a.h(drawableL, TabLayout.this.f4721r);
                }
                drawableL.draw(canvas);
            }
            super.draw(canvas);
        }

        void e(int i7, float f7) {
            ValueAnimator valueAnimator = this.f4733e;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.f4733e.cancel();
            }
            this.f4734g = i7;
            this.f4735h = f7;
            g(getChildAt(i7), getChildAt(this.f4734g + 1), this.f4735h);
        }

        void f(int i7) {
            Rect bounds = TabLayout.this.f4720q.getBounds();
            TabLayout.this.f4720q.setBounds(bounds.left, 0, bounds.right, i7);
            requestLayout();
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
            super.onLayout(z6, i7, i8, i9, i10);
            ValueAnimator valueAnimator = this.f4733e;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                d();
            } else {
                h(false, this.f4734g, -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i7, int i8) {
            super.onMeasure(i7, i8);
            if (View.MeasureSpec.getMode(i7) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            boolean z6 = true;
            if (tabLayout.B == 1 || tabLayout.E == 2) {
                int childCount = getChildCount();
                int iMax = 0;
                for (int i9 = 0; i9 < childCount; i9++) {
                    View childAt = getChildAt(i9);
                    if (childAt.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount <= getMeasuredWidth() - (((int) l.b(getContext(), 16)) * 2)) {
                    boolean z7 = false;
                    for (int i10 = 0; i10 < childCount; i10++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i10).getLayoutParams();
                        if (layoutParams.width != iMax || layoutParams.weight != 0.0f) {
                            layoutParams.width = iMax;
                            layoutParams.weight = 0.0f;
                            z7 = true;
                        }
                    }
                    z6 = z7;
                } else {
                    TabLayout tabLayout2 = TabLayout.this;
                    tabLayout2.B = 0;
                    tabLayout2.P(false);
                }
                if (z6) {
                    super.onMeasure(i7, i8);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i7) {
            super.onRtlPropertiesChanged(i7);
        }
    }

    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Object f4743a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Drawable f4744b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CharSequence f4745c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private CharSequence f4746d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private View f4748f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public TabLayout f4750h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public h f4751i;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f4747e = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f4749g = 1;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private int f4752j = -1;

        public View e() {
            return this.f4748f;
        }

        public Drawable f() {
            return this.f4744b;
        }

        public int g() {
            return this.f4747e;
        }

        public int h() {
            return this.f4749g;
        }

        public CharSequence i() {
            return this.f4745c;
        }

        public boolean j() {
            TabLayout tabLayout = this.f4750h;
            if (tabLayout != null) {
                return tabLayout.getSelectedTabPosition() == this.f4747e;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        void k() {
            this.f4750h = null;
            this.f4751i = null;
            this.f4743a = null;
            this.f4744b = null;
            this.f4752j = -1;
            this.f4745c = null;
            this.f4746d = null;
            this.f4747e = -1;
            this.f4748f = null;
        }

        public void l() {
            TabLayout tabLayout = this.f4750h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.G(this);
        }

        public f m(CharSequence charSequence) {
            this.f4746d = charSequence;
            s();
            return this;
        }

        public f n(int i7) {
            return o(LayoutInflater.from(this.f4751i.getContext()).inflate(i7, (ViewGroup) this.f4751i, false));
        }

        public f o(View view) {
            this.f4748f = view;
            s();
            return this;
        }

        public f p(Drawable drawable) {
            this.f4744b = drawable;
            TabLayout tabLayout = this.f4750h;
            if (tabLayout.B == 1 || tabLayout.E == 2) {
                tabLayout.P(true);
            }
            s();
            if (com.google.android.material.badge.a.f4122a && this.f4751i.l() && this.f4751i.f4760j.isVisible()) {
                this.f4751i.invalidate();
            }
            return this;
        }

        void q(int i7) {
            this.f4747e = i7;
        }

        public f r(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.f4746d) && !TextUtils.isEmpty(charSequence)) {
                this.f4751i.setContentDescription(charSequence);
            }
            this.f4745c = charSequence;
            s();
            return this;
        }

        void s() {
            h hVar = this.f4751i;
            if (hVar != null) {
                hVar.t();
            }
        }
    }

    public static class g implements ViewPager.j {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final WeakReference<TabLayout> f4753e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f4754g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f4755h;

        public g(TabLayout tabLayout) {
            this.f4753e = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i7, float f7, int i8) {
            TabLayout tabLayout = this.f4753e.get();
            if (tabLayout != null) {
                int i9 = this.f4755h;
                tabLayout.K(i7, f7, i9 != 2 || this.f4754g == 1, (i9 == 2 && this.f4754g == 0) ? false : true);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void b(int i7) {
            this.f4754g = this.f4755h;
            this.f4755h = i7;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i7) {
            TabLayout tabLayout = this.f4753e.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i7 || i7 >= tabLayout.getTabCount()) {
                return;
            }
            int i8 = this.f4755h;
            tabLayout.H(tabLayout.x(i7), i8 == 0 || (i8 == 2 && this.f4754g == 0));
        }

        void d() {
            this.f4755h = 0;
            this.f4754g = 0;
        }
    }

    public final class h extends LinearLayout {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private f f4756e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private TextView f4757g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private ImageView f4758h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private View f4759i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private BadgeDrawable f4760j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private View f4761k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private TextView f4762l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private ImageView f4763m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private Drawable f4764n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private int f4765o;

        class a implements View.OnLayoutChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ View f4767a;

            a(View view) {
                this.f4767a = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
                if (this.f4767a.getVisibility() == 0) {
                    h.this.s(this.f4767a);
                }
            }
        }

        public h(Context context) {
            super(context);
            this.f4765o = 2;
            u(context);
            u.w0(this, TabLayout.this.f4712i, TabLayout.this.f4713j, TabLayout.this.f4714k, TabLayout.this.f4715l);
            setGravity(17);
            setOrientation(!TabLayout.this.F ? 1 : 0);
            setClickable(true);
            u.x0(this, t.b(getContext(), 1002));
        }

        private void f(View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(new a(view));
        }

        private float g(Layout layout, int i7, float f7) {
            return layout.getLineWidth(i7) * (f7 / layout.getPaint().getTextSize());
        }

        private BadgeDrawable getBadge() {
            return this.f4760j;
        }

        private BadgeDrawable getOrCreateBadge() {
            if (this.f4760j == null) {
                this.f4760j = BadgeDrawable.c(getContext());
            }
            r();
            BadgeDrawable badgeDrawable = this.f4760j;
            if (badgeDrawable != null) {
                return badgeDrawable;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        private void h(boolean z6) {
            setClipChildren(z6);
            setClipToPadding(z6);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(z6);
                viewGroup.setClipToPadding(z6);
            }
        }

        private FrameLayout i() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void j(Canvas canvas) {
            Drawable drawable = this.f4764n;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.f4764n.draw(canvas);
            }
        }

        private FrameLayout k(View view) {
            if ((view == this.f4758h || view == this.f4757g) && com.google.android.material.badge.a.f4122a) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean l() {
            return this.f4760j != null;
        }

        private void m() {
            ViewGroup viewGroup;
            if (com.google.android.material.badge.a.f4122a) {
                FrameLayout frameLayoutI = i();
                addView(frameLayoutI, 0);
                viewGroup = frameLayoutI;
            } else {
                viewGroup = this;
            }
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(o2.h.design_layout_tab_icon, viewGroup, false);
            this.f4758h = imageView;
            viewGroup.addView(imageView, 0);
        }

        private void n() {
            ViewGroup viewGroup;
            if (com.google.android.material.badge.a.f4122a) {
                FrameLayout frameLayoutI = i();
                addView(frameLayoutI);
                viewGroup = frameLayoutI;
            } else {
                viewGroup = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(o2.h.design_layout_tab_text, viewGroup, false);
            this.f4757g = textView;
            viewGroup.addView(textView);
        }

        private void p(View view) {
            if (l() && view != null) {
                h(false);
                com.google.android.material.badge.a.a(this.f4760j, view, k(view));
                this.f4759i = view;
            }
        }

        private void q() {
            if (l()) {
                h(true);
                View view = this.f4759i;
                if (view != null) {
                    com.google.android.material.badge.a.b(this.f4760j, view);
                    this.f4759i = null;
                }
            }
        }

        private void r() {
            f fVar;
            f fVar2;
            if (l()) {
                if (this.f4761k != null) {
                    q();
                    return;
                }
                if (this.f4758h != null && (fVar2 = this.f4756e) != null && fVar2.f() != null) {
                    View view = this.f4759i;
                    ImageView imageView = this.f4758h;
                    if (view == imageView) {
                        s(imageView);
                        return;
                    } else {
                        q();
                        p(this.f4758h);
                        return;
                    }
                }
                if (this.f4757g == null || (fVar = this.f4756e) == null || fVar.h() != 1) {
                    q();
                    return;
                }
                View view2 = this.f4759i;
                TextView textView = this.f4757g;
                if (view2 == textView) {
                    s(textView);
                } else {
                    q();
                    p(this.f4757g);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s(View view) {
            if (l() && view == this.f4759i) {
                com.google.android.material.badge.a.c(this.f4760j, view, k(view));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void u(Context context) {
            int i7 = TabLayout.this.f4725v;
            if (i7 != 0) {
                Drawable drawableB = f.a.b(context, i7);
                this.f4764n = drawableB;
                if (drawableB != null && drawableB.isStateful()) {
                    this.f4764n.setState(getDrawableState());
                }
            } else {
                this.f4764n = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            Drawable rippleDrawable = gradientDrawable;
            if (TabLayout.this.f4719p != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList colorStateListA = c3.b.a(TabLayout.this.f4719p);
                boolean z6 = TabLayout.this.I;
                GradientDrawable gradientDrawable3 = gradientDrawable;
                if (z6) {
                    gradientDrawable3 = null;
                }
                rippleDrawable = new RippleDrawable(colorStateListA, gradientDrawable3, z6 ? null : gradientDrawable2);
            }
            u.n0(this, rippleDrawable);
            TabLayout.this.invalidate();
        }

        private void w(TextView textView, ImageView imageView) {
            f fVar = this.f4756e;
            Drawable drawableMutate = (fVar == null || fVar.f() == null) ? null : a0.a.l(this.f4756e.f()).mutate();
            f fVar2 = this.f4756e;
            CharSequence charSequenceI = fVar2 != null ? fVar2.i() : null;
            if (imageView != null) {
                if (drawableMutate != null) {
                    imageView.setImageDrawable(drawableMutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean z6 = !TextUtils.isEmpty(charSequenceI);
            if (textView != null) {
                if (z6) {
                    textView.setText(charSequenceI);
                    if (this.f4756e.f4749g == 1) {
                        textView.setVisibility(0);
                    } else {
                        textView.setVisibility(8);
                    }
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence) null);
                }
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int iB = (z6 && imageView.getVisibility() == 0) ? (int) l.b(getContext(), 8) : 0;
                if (TabLayout.this.F) {
                    if (iB != i0.h.a(marginLayoutParams)) {
                        i0.h.c(marginLayoutParams, iB);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iB != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iB;
                    i0.h.c(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            f fVar3 = this.f4756e;
            CharSequence charSequence = fVar3 != null ? fVar3.f4746d : null;
            if (!z6) {
                charSequenceI = charSequence;
            }
            u0.a(this, charSequenceI);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.f4764n;
            boolean state = false;
            if (drawable != null && drawable.isStateful()) {
                state = false | this.f4764n.setState(drawableState);
            }
            if (state) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        int getContentHeight() {
            View[] viewArr = {this.f4757g, this.f4758h, this.f4761k};
            int iMax = 0;
            int iMin = 0;
            boolean z6 = false;
            for (int i7 = 0; i7 < 3; i7++) {
                View view = viewArr[i7];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z6 ? Math.min(iMin, view.getTop()) : view.getTop();
                    iMax = z6 ? Math.max(iMax, view.getBottom()) : view.getBottom();
                    z6 = true;
                }
            }
            return iMax - iMin;
        }

        int getContentWidth() {
            View[] viewArr = {this.f4757g, this.f4758h, this.f4761k};
            int iMax = 0;
            int iMin = 0;
            boolean z6 = false;
            for (int i7 = 0; i7 < 3; i7++) {
                View view = viewArr[i7];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z6 ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z6 ? Math.max(iMax, view.getRight()) : view.getRight();
                    z6 = true;
                }
            }
            return iMax - iMin;
        }

        public f getTab() {
            return this.f4756e;
        }

        void o() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            BadgeDrawable badgeDrawable = this.f4760j;
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.f4760j.g()));
            }
            j0.c cVarP0 = j0.c.p0(accessibilityNodeInfo);
            cVarP0.S(c.C0090c.a(0, 1, this.f4756e.g(), 1, false, isSelected()));
            if (isSelected()) {
                cVarP0.Q(false);
                cVarP0.J(c.a.f7117i);
            }
            cVarP0.f0(getResources().getString(j.item_view_role_description));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i7, int i8) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i7);
            int mode = View.MeasureSpec.getMode(i7);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i7 = View.MeasureSpec.makeMeasureSpec(TabLayout.this.f4726w, Integer.MIN_VALUE);
            }
            super.onMeasure(i7, i8);
            if (this.f4757g != null) {
                float f7 = TabLayout.this.f4723t;
                int i9 = this.f4765o;
                ImageView imageView = this.f4758h;
                boolean z6 = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f4757g;
                    if (textView != null && textView.getLineCount() > 1) {
                        f7 = TabLayout.this.f4724u;
                    }
                } else {
                    i9 = 1;
                }
                float textSize = this.f4757g.getTextSize();
                int lineCount = this.f4757g.getLineCount();
                int iD = androidx.core.widget.i.d(this.f4757g);
                if (f7 != textSize || (iD >= 0 && i9 != iD)) {
                    if (TabLayout.this.E == 1 && f7 > textSize && lineCount == 1 && ((layout = this.f4757g.getLayout()) == null || g(layout, 0, f7) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z6 = false;
                    }
                    if (z6) {
                        this.f4757g.setTextSize(0, f7);
                        this.f4757g.setMaxLines(i9);
                        super.onMeasure(i7, i8);
                    }
                }
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.f4756e == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.f4756e.l();
            return true;
        }

        @Override // android.view.View
        public void setSelected(boolean z6) {
            if (isSelected() != z6) {
            }
            super.setSelected(z6);
            TextView textView = this.f4757g;
            if (textView != null) {
                textView.setSelected(z6);
            }
            ImageView imageView = this.f4758h;
            if (imageView != null) {
                imageView.setSelected(z6);
            }
            View view = this.f4761k;
            if (view != null) {
                view.setSelected(z6);
            }
        }

        void setTab(f fVar) {
            if (fVar != this.f4756e) {
                this.f4756e = fVar;
                t();
            }
        }

        final void t() {
            f fVar = this.f4756e;
            Drawable drawableMutate = null;
            View viewE = fVar != null ? fVar.e() : null;
            if (viewE != null) {
                ViewParent parent = viewE.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewE);
                    }
                    addView(viewE);
                }
                this.f4761k = viewE;
                TextView textView = this.f4757g;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f4758h;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f4758h.setImageDrawable(null);
                }
                TextView textView2 = (TextView) viewE.findViewById(R.id.text1);
                this.f4762l = textView2;
                if (textView2 != null) {
                    this.f4765o = androidx.core.widget.i.d(textView2);
                }
                this.f4763m = (ImageView) viewE.findViewById(R.id.icon);
            } else {
                View view = this.f4761k;
                if (view != null) {
                    removeView(view);
                    this.f4761k = null;
                }
                this.f4762l = null;
                this.f4763m = null;
            }
            if (this.f4761k == null) {
                if (this.f4758h == null) {
                    m();
                }
                if (fVar != null && fVar.f() != null) {
                    drawableMutate = a0.a.l(fVar.f()).mutate();
                }
                if (drawableMutate != null) {
                    a0.a.i(drawableMutate, TabLayout.this.f4718o);
                    PorterDuff.Mode mode = TabLayout.this.f4722s;
                    if (mode != null) {
                        a0.a.j(drawableMutate, mode);
                    }
                }
                if (this.f4757g == null) {
                    n();
                    this.f4765o = androidx.core.widget.i.d(this.f4757g);
                }
                androidx.core.widget.i.n(this.f4757g, TabLayout.this.f4716m);
                ColorStateList colorStateList = TabLayout.this.f4717n;
                if (colorStateList != null) {
                    this.f4757g.setTextColor(colorStateList);
                }
                w(this.f4757g, this.f4758h);
                r();
                f(this.f4758h);
                f(this.f4757g);
            } else {
                TextView textView3 = this.f4762l;
                if (textView3 != null || this.f4763m != null) {
                    w(textView3, this.f4763m);
                }
            }
            if (fVar != null && !TextUtils.isEmpty(fVar.f4746d)) {
                setContentDescription(fVar.f4746d);
            }
            setSelected(fVar != null && fVar.j());
        }

        final void v() {
            setOrientation(!TabLayout.this.F ? 1 : 0);
            TextView textView = this.f4762l;
            if (textView == null && this.f4763m == null) {
                w(this.f4757g, this.f4758h);
            } else {
                w(textView, this.f4763m);
            }
        }
    }

    public static class i implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ViewPager f4769a;

        public i(ViewPager viewPager) {
            this.f4769a = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(f fVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(f fVar) {
            this.f4769a.setCurrentItem(fVar.g());
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(f fVar) {
        }
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, o2.b.tabStyle);
    }

    private void F(int i7) {
        h hVar = (h) this.f4711h.getChildAt(i7);
        this.f4711h.removeViewAt(i7);
        if (hVar != null) {
            hVar.o();
            this.S.a(hVar);
        }
        requestLayout();
    }

    private void M(ViewPager viewPager, boolean z6, boolean z7) {
        ViewPager viewPager2 = this.O;
        if (viewPager2 != null) {
            g gVar = this.P;
            if (gVar != null) {
                viewPager2.D(gVar);
            }
            b bVar = this.Q;
            if (bVar != null) {
                this.O.C(bVar);
            }
        }
        c cVar = this.M;
        if (cVar != null) {
            D(cVar);
            this.M = null;
        }
        if (viewPager != null) {
            this.O = viewPager;
            if (this.P == null) {
                this.P = new g(this);
            }
            this.P.d();
            viewPager.b(this.P);
            i iVar = new i(viewPager);
            this.M = iVar;
            c(iVar);
            viewPager.getAdapter();
            if (this.Q == null) {
                this.Q = new b();
            }
            this.Q.b(z6);
            viewPager.a(this.Q);
            J(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.O = null;
            I(null, false);
        }
        this.R = z7;
    }

    private void N() {
        int size = this.f4709e.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.f4709e.get(i7).s();
        }
    }

    private void O(LinearLayout.LayoutParams layoutParams) {
        if (this.E == 1 && this.B == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    private int getDefaultHeight() {
        int size = this.f4709e.size();
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            if (i7 < size) {
                f fVar = this.f4709e.get(i7);
                if (fVar != null && fVar.f() != null && !TextUtils.isEmpty(fVar.i())) {
                    z6 = true;
                    break;
                }
                i7++;
            } else {
                break;
            }
        }
        return (!z6 || this.F) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i7 = this.f4727x;
        if (i7 != -1) {
            return i7;
        }
        int i8 = this.E;
        if (i8 == 0 || i8 == 2) {
            return this.f4729z;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.f4711h.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void h(com.google.android.material.tabs.c cVar) {
        f fVarZ = z();
        CharSequence charSequence = cVar.f4770e;
        if (charSequence != null) {
            fVarZ.r(charSequence);
        }
        Drawable drawable = cVar.f4771g;
        if (drawable != null) {
            fVarZ.p(drawable);
        }
        int i7 = cVar.f4772h;
        if (i7 != 0) {
            fVarZ.n(i7);
        }
        if (!TextUtils.isEmpty(cVar.getContentDescription())) {
            fVarZ.m(cVar.getContentDescription());
        }
        e(fVarZ);
    }

    private void i(f fVar) {
        h hVar = fVar.f4751i;
        hVar.setSelected(false);
        hVar.setActivated(false);
        this.f4711h.addView(hVar, fVar.g(), q());
    }

    private void j(View view) {
        if (!(view instanceof com.google.android.material.tabs.c)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        h((com.google.android.material.tabs.c) view);
    }

    private void k(int i7) {
        if (i7 == -1) {
            return;
        }
        if (getWindowToken() == null || !u.P(this) || this.f4711h.c()) {
            J(i7, 0.0f, true);
            return;
        }
        int scrollX = getScrollX();
        int iN = n(i7, 0.0f);
        if (scrollX != iN) {
            w();
            this.N.setIntValues(scrollX, iN);
            this.N.start();
        }
        this.f4711h.b(i7, this.C);
    }

    private void l(int i7) {
        if (i7 == 0) {
            Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        } else if (i7 == 1) {
            this.f4711h.setGravity(1);
            return;
        } else if (i7 != 2) {
            return;
        }
        this.f4711h.setGravity(8388611);
    }

    private void m() {
        int i7 = this.E;
        u.w0(this.f4711h, (i7 == 0 || i7 == 2) ? Math.max(0, this.A - this.f4712i) : 0, 0, 0, 0);
        int i8 = this.E;
        if (i8 == 0) {
            l(this.B);
        } else if (i8 == 1 || i8 == 2) {
            if (this.B == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            this.f4711h.setGravity(1);
        }
        P(true);
    }

    private int n(int i7, float f7) {
        int i8 = this.E;
        if (i8 != 0 && i8 != 2) {
            return 0;
        }
        View childAt = this.f4711h.getChildAt(i7);
        int i9 = i7 + 1;
        View childAt2 = i9 < this.f4711h.getChildCount() ? this.f4711h.getChildAt(i9) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i10 = (int) ((width + width2) * 0.5f * f7);
        return u.y(this) == 0 ? left + i10 : left - i10;
    }

    private void o(f fVar, int i7) {
        fVar.q(i7);
        this.f4709e.add(i7, fVar);
        int size = this.f4709e.size();
        while (true) {
            i7++;
            if (i7 >= size) {
                return;
            } else {
                this.f4709e.get(i7).q(i7);
            }
        }
    }

    private static ColorStateList p(int i7, int i8) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i8, i7});
    }

    private LinearLayout.LayoutParams q() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        O(layoutParams);
        return layoutParams;
    }

    private h s(f fVar) {
        h0.e<h> eVar = this.S;
        h hVarB = eVar != null ? eVar.b() : null;
        if (hVarB == null) {
            hVarB = new h(getContext());
        }
        hVarB.setTab(fVar);
        hVarB.setFocusable(true);
        hVarB.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(fVar.f4746d)) {
            hVarB.setContentDescription(fVar.f4745c);
        } else {
            hVarB.setContentDescription(fVar.f4746d);
        }
        return hVarB;
    }

    private void setSelectedTabView(int i7) {
        int childCount = this.f4711h.getChildCount();
        if (i7 < childCount) {
            int i8 = 0;
            while (i8 < childCount) {
                View childAt = this.f4711h.getChildAt(i8);
                boolean z6 = true;
                childAt.setSelected(i8 == i7);
                if (i8 != i7) {
                    z6 = false;
                }
                childAt.setActivated(z6);
                i8++;
            }
        }
    }

    private void t(f fVar) {
        for (int size = this.L.size() - 1; size >= 0; size--) {
            this.L.get(size).c(fVar);
        }
    }

    private void u(f fVar) {
        for (int size = this.L.size() - 1; size >= 0; size--) {
            this.L.get(size).b(fVar);
        }
    }

    private void v(f fVar) {
        for (int size = this.L.size() - 1; size >= 0; size--) {
            this.L.get(size).a(fVar);
        }
    }

    private void w() {
        if (this.N == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.N = valueAnimator;
            valueAnimator.setInterpolator(p2.a.f8821b);
            this.N.setDuration(this.C);
            this.N.addUpdateListener(new a());
        }
    }

    void A() {
        C();
    }

    protected boolean B(f fVar) {
        return U.a(fVar);
    }

    public void C() {
        for (int childCount = this.f4711h.getChildCount() - 1; childCount >= 0; childCount--) {
            F(childCount);
        }
        Iterator<f> it = this.f4709e.iterator();
        while (it.hasNext()) {
            f next = it.next();
            it.remove();
            next.k();
            B(next);
        }
        this.f4710g = null;
    }

    @Deprecated
    public void D(c cVar) {
        this.L.remove(cVar);
    }

    public void E(d dVar) {
        D(dVar);
    }

    public void G(f fVar) {
        H(fVar, true);
    }

    public void H(f fVar, boolean z6) {
        f fVar2 = this.f4710g;
        if (fVar2 == fVar) {
            if (fVar2 != null) {
                t(fVar);
                k(fVar.g());
                return;
            }
            return;
        }
        int iG = fVar != null ? fVar.g() : -1;
        if (z6) {
            if ((fVar2 == null || fVar2.g() == -1) && iG != -1) {
                J(iG, 0.0f, true);
            } else {
                k(iG);
            }
            if (iG != -1) {
                setSelectedTabView(iG);
            }
        }
        this.f4710g = fVar;
        if (fVar2 != null) {
            v(fVar2);
        }
        if (fVar != null) {
            u(fVar);
        }
    }

    void I(androidx.viewpager.widget.a aVar, boolean z6) {
        A();
    }

    public void J(int i7, float f7, boolean z6) {
        K(i7, f7, z6, true);
    }

    public void K(int i7, float f7, boolean z6, boolean z7) {
        int iRound = Math.round(i7 + f7);
        if (iRound < 0 || iRound >= this.f4711h.getChildCount()) {
            return;
        }
        if (z7) {
            this.f4711h.e(i7, f7);
        }
        ValueAnimator valueAnimator = this.N;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.N.cancel();
        }
        scrollTo(n(i7, f7), 0);
        if (z6) {
            setSelectedTabView(iRound);
        }
    }

    public void L(ViewPager viewPager, boolean z6) {
        M(viewPager, z6, false);
    }

    void P(boolean z6) {
        for (int i7 = 0; i7 < this.f4711h.getChildCount(); i7++) {
            View childAt = this.f4711h.getChildAt(i7);
            childAt.setMinimumWidth(getTabMinWidth());
            O((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z6) {
                childAt.requestLayout();
            }
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        j(view);
    }

    @Deprecated
    public void c(c cVar) {
        if (this.L.contains(cVar)) {
            return;
        }
        this.L.add(cVar);
    }

    public void d(d dVar) {
        c(dVar);
    }

    public void e(f fVar) {
        g(fVar, this.f4709e.isEmpty());
    }

    public void f(f fVar, int i7, boolean z6) {
        if (fVar.f4750h != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        o(fVar, i7);
        i(fVar);
        if (z6) {
            fVar.l();
        }
    }

    public void g(f fVar, boolean z6) {
        f(fVar, this.f4709e.size(), z6);
    }

    public int getSelectedTabPosition() {
        f fVar = this.f4710g;
        if (fVar != null) {
            return fVar.g();
        }
        return -1;
    }

    public int getTabCount() {
        return this.f4709e.size();
    }

    public int getTabGravity() {
        return this.B;
    }

    public ColorStateList getTabIconTint() {
        return this.f4718o;
    }

    public int getTabIndicatorAnimationMode() {
        return this.H;
    }

    public int getTabIndicatorGravity() {
        return this.D;
    }

    int getTabMaxWidth() {
        return this.f4726w;
    }

    public int getTabMode() {
        return this.E;
    }

    public ColorStateList getTabRippleColor() {
        return this.f4719p;
    }

    public Drawable getTabSelectedIndicator() {
        return this.f4720q;
    }

    public ColorStateList getTabTextColors() {
        return this.f4717n;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        e3.h.e(this);
        if (this.O == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                M((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.R) {
            setupWithViewPager(null);
            this.R = false;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        for (int i7 = 0; i7 < this.f4711h.getChildCount(); i7++) {
            View childAt = this.f4711h.getChildAt(i7);
            if (childAt instanceof h) {
                ((h) childAt).j(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        j0.c.p0(accessibilityNodeInfo).R(c.b.a(1, getTabCount(), false, 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0082  */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            int r1 = r6.getDefaultHeight()
            float r0 = com.google.android.material.internal.l.b(r0, r1)
            int r0 = java.lang.Math.round(r0)
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 0
            r5 = 1
            if (r1 == r2) goto L2e
            if (r1 == 0) goto L1f
            goto L41
        L1f:
            int r8 = r6.getPaddingTop()
            int r0 = r0 + r8
            int r8 = r6.getPaddingBottom()
            int r0 = r0 + r8
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            goto L41
        L2e:
            int r1 = r6.getChildCount()
            if (r1 != r5) goto L41
            int r1 = android.view.View.MeasureSpec.getSize(r8)
            if (r1 < r0) goto L41
            android.view.View r1 = r6.getChildAt(r4)
            r1.setMinimumHeight(r0)
        L41:
            int r0 = android.view.View.MeasureSpec.getSize(r7)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            if (r1 == 0) goto L5f
            int r1 = r6.f4728y
            if (r1 <= 0) goto L50
            goto L5d
        L50:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = com.google.android.material.internal.l.b(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L5d:
            r6.f4726w = r1
        L5f:
            super.onMeasure(r7, r8)
            int r7 = r6.getChildCount()
            if (r7 != r5) goto Lad
            android.view.View r7 = r6.getChildAt(r4)
            int r0 = r6.E
            if (r0 == 0) goto L82
            if (r0 == r5) goto L76
            r1 = 2
            if (r0 == r1) goto L82
            goto L8d
        L76:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 == r1) goto L8d
        L80:
            r4 = r5
            goto L8d
        L82:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 >= r1) goto L8d
            goto L80
        L8d:
            if (r4 == 0) goto Lad
            int r0 = r6.getPaddingTop()
            int r1 = r6.getPaddingBottom()
            int r0 = r0 + r1
            android.view.ViewGroup$LayoutParams r1 = r7.getLayoutParams()
            int r1 = r1.height
            int r8 = android.widget.HorizontalScrollView.getChildMeasureSpec(r8, r0, r1)
            int r0 = r6.getMeasuredWidth()
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            r7.measure(r0, r8)
        Lad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onMeasure(int, int):void");
    }

    protected f r() {
        f fVarB = U.b();
        return fVarB == null ? new f() : fVarB;
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        e3.h.d(this, f7);
    }

    public void setInlineLabel(boolean z6) {
        if (this.F != z6) {
            this.F = z6;
            for (int i7 = 0; i7 < this.f4711h.getChildCount(); i7++) {
                View childAt = this.f4711h.getChildAt(i7);
                if (childAt instanceof h) {
                    ((h) childAt).v();
                }
            }
            m();
        }
    }

    public void setInlineLabelResource(int i7) {
        setInlineLabel(getResources().getBoolean(i7));
    }

    @Deprecated
    public void setOnTabSelectedListener(d dVar) {
        setOnTabSelectedListener((c) dVar);
    }

    void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        w();
        this.N.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.f4720q != drawable) {
            if (drawable == null) {
                drawable = new GradientDrawable();
            }
            this.f4720q = drawable;
        }
    }

    public void setSelectedTabIndicatorColor(int i7) {
        this.f4721r = i7;
    }

    public void setSelectedTabIndicatorGravity(int i7) {
        if (this.D != i7) {
            this.D = i7;
            u.b0(this.f4711h);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i7) {
        this.f4711h.f(i7);
    }

    public void setTabGravity(int i7) {
        if (this.B != i7) {
            this.B = i7;
            m();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.f4718o != colorStateList) {
            this.f4718o = colorStateList;
            N();
        }
    }

    public void setTabIconTintResource(int i7) {
        setTabIconTint(f.a.a(getContext(), i7));
    }

    public void setTabIndicatorAnimationMode(int i7) {
        this.H = i7;
        if (i7 == 0) {
            this.J = new com.google.android.material.tabs.b();
        } else {
            if (i7 == 1) {
                this.J = new com.google.android.material.tabs.a();
                return;
            }
            throw new IllegalArgumentException(i7 + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z6) {
        this.G = z6;
        u.b0(this.f4711h);
    }

    public void setTabMode(int i7) {
        if (i7 != this.E) {
            this.E = i7;
            m();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.f4719p != colorStateList) {
            this.f4719p = colorStateList;
            for (int i7 = 0; i7 < this.f4711h.getChildCount(); i7++) {
                View childAt = this.f4711h.getChildAt(i7);
                if (childAt instanceof h) {
                    ((h) childAt).u(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int i7) {
        setTabRippleColor(f.a.a(getContext(), i7));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.f4717n != colorStateList) {
            this.f4717n = colorStateList;
            N();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(androidx.viewpager.widget.a aVar) {
        I(aVar, false);
    }

    public void setUnboundedRipple(boolean z6) {
        if (this.I != z6) {
            this.I = z6;
            for (int i7 = 0; i7 < this.f4711h.getChildCount(); i7++) {
                View childAt = this.f4711h.getChildAt(i7);
                if (childAt instanceof h) {
                    ((h) childAt).u(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i7) {
        setUnboundedRipple(getResources().getBoolean(i7));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        L(viewPager, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public f x(int i7) {
        if (i7 < 0 || i7 >= getTabCount()) {
            return null;
        }
        return this.f4709e.get(i7);
    }

    public boolean y() {
        return this.G;
    }

    public f z() {
        f fVarR = r();
        fVarR.f4750h = this;
        fVarR.f4751i = s(fVarR);
        if (fVarR.f4752j != -1) {
            fVarR.f4751i.setId(fVarR.f4752j);
        }
        return fVarR;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TabLayout(Context context, AttributeSet attributeSet, int i7) {
        int i8 = T;
        super(f3.a.c(context, attributeSet, i7, i8), attributeSet, i7);
        this.f4709e = new ArrayList<>();
        this.f4720q = new GradientDrawable();
        this.f4721r = 0;
        this.f4726w = Integer.MAX_VALUE;
        this.L = new ArrayList<>();
        this.S = new h0.f(12);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        e eVar = new e(context2);
        this.f4711h = eVar;
        super.addView(eVar, 0, new FrameLayout.LayoutParams(-2, -1));
        int[] iArr = o2.l.TabLayout;
        int i9 = o2.l.TabLayout_tabTextAppearance;
        TypedArray typedArrayH = com.google.android.material.internal.j.h(context2, attributeSet, iArr, i7, i8, i9);
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            e3.g gVar = new e3.g();
            gVar.X(ColorStateList.valueOf(colorDrawable.getColor()));
            gVar.M(context2);
            gVar.W(u.t(this));
            u.n0(this, gVar);
        }
        setSelectedTabIndicator(b3.c.d(context2, typedArrayH, o2.l.TabLayout_tabIndicator));
        setSelectedTabIndicatorColor(typedArrayH.getColor(o2.l.TabLayout_tabIndicatorColor, 0));
        eVar.f(typedArrayH.getDimensionPixelSize(o2.l.TabLayout_tabIndicatorHeight, -1));
        setSelectedTabIndicatorGravity(typedArrayH.getInt(o2.l.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorFullWidth(typedArrayH.getBoolean(o2.l.TabLayout_tabIndicatorFullWidth, true));
        setTabIndicatorAnimationMode(typedArrayH.getInt(o2.l.TabLayout_tabIndicatorAnimationMode, 0));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(o2.l.TabLayout_tabPadding, 0);
        this.f4715l = dimensionPixelSize;
        this.f4714k = dimensionPixelSize;
        this.f4713j = dimensionPixelSize;
        this.f4712i = dimensionPixelSize;
        this.f4712i = typedArrayH.getDimensionPixelSize(o2.l.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.f4713j = typedArrayH.getDimensionPixelSize(o2.l.TabLayout_tabPaddingTop, this.f4713j);
        this.f4714k = typedArrayH.getDimensionPixelSize(o2.l.TabLayout_tabPaddingEnd, this.f4714k);
        this.f4715l = typedArrayH.getDimensionPixelSize(o2.l.TabLayout_tabPaddingBottom, this.f4715l);
        int resourceId = typedArrayH.getResourceId(i9, k.TextAppearance_Design_Tab);
        this.f4716m = resourceId;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, e.j.TextAppearance);
        try {
            this.f4723t = typedArrayObtainStyledAttributes.getDimensionPixelSize(e.j.TextAppearance_android_textSize, 0);
            this.f4717n = b3.c.a(context2, typedArrayObtainStyledAttributes, e.j.TextAppearance_android_textColor);
            typedArrayObtainStyledAttributes.recycle();
            int i10 = o2.l.TabLayout_tabTextColor;
            if (typedArrayH.hasValue(i10)) {
                this.f4717n = b3.c.a(context2, typedArrayH, i10);
            }
            int i11 = o2.l.TabLayout_tabSelectedTextColor;
            if (typedArrayH.hasValue(i11)) {
                this.f4717n = p(this.f4717n.getDefaultColor(), typedArrayH.getColor(i11, 0));
            }
            this.f4718o = b3.c.a(context2, typedArrayH, o2.l.TabLayout_tabIconTint);
            this.f4722s = l.e(typedArrayH.getInt(o2.l.TabLayout_tabIconTintMode, -1), null);
            this.f4719p = b3.c.a(context2, typedArrayH, o2.l.TabLayout_tabRippleColor);
            this.C = typedArrayH.getInt(o2.l.TabLayout_tabIndicatorAnimationDuration, 300);
            this.f4727x = typedArrayH.getDimensionPixelSize(o2.l.TabLayout_tabMinWidth, -1);
            this.f4728y = typedArrayH.getDimensionPixelSize(o2.l.TabLayout_tabMaxWidth, -1);
            this.f4725v = typedArrayH.getResourceId(o2.l.TabLayout_tabBackground, 0);
            this.A = typedArrayH.getDimensionPixelSize(o2.l.TabLayout_tabContentStart, 0);
            this.E = typedArrayH.getInt(o2.l.TabLayout_tabMode, 1);
            this.B = typedArrayH.getInt(o2.l.TabLayout_tabGravity, 0);
            this.F = typedArrayH.getBoolean(o2.l.TabLayout_tabInlineLabel, false);
            this.I = typedArrayH.getBoolean(o2.l.TabLayout_tabUnboundedRipple, false);
            typedArrayH.recycle();
            Resources resources = getResources();
            this.f4724u = resources.getDimensionPixelSize(o2.d.design_tab_text_size_2line);
            this.f4729z = resources.getDimensionPixelSize(o2.d.design_tab_scrollable_min_width);
            m();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i7) {
        j(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void setOnTabSelectedListener(c cVar) {
        c cVar2 = this.K;
        if (cVar2 != null) {
            D(cVar2);
        }
        this.K = cVar;
        if (cVar != null) {
            c(cVar);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        j(view);
    }

    public void setSelectedTabIndicator(int i7) {
        if (i7 != 0) {
            setSelectedTabIndicator(f.a.b(getContext(), i7));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        j(view);
    }
}
