package com.google.android.material.tabs;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
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
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$layout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.badge.BadgeDrawable;
import i2.k;
import i2.p;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import l0.r;
import l0.t;
import m0.c;
import o.g0;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class TabLayout extends HorizontalScrollView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2455b = R$style.Widget_Design_TabLayout;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k0.e<g> f2456c = new k0.g(16);
    public int A;
    public int B;
    public int C;
    public boolean D;
    public boolean E;
    public boolean F;
    public c G;
    public final ArrayList<c> H;
    public c I;
    public ValueAnimator J;
    public ViewPager K;
    public b1.a L;
    public DataSetObserver M;
    public h N;
    public b O;
    public boolean P;
    public final k0.e<TabView> Q;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList<g> f2457d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g f2458e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f2459f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f f2460g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2461h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2462i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2463j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2464k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2465l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f2466m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ColorStateList f2467n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ColorStateList f2468o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f2469p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public PorterDuff.Mode f2470q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f2471r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f2472s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f2473t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f2474u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f2475v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f2476w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f2477x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f2478y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f2479z;

    public final class TabView extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public g f2480b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2481c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ImageView f2482d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f2483e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public BadgeDrawable f2484f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public View f2485g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public TextView f2486h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public ImageView f2487i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public Drawable f2488j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f2489k;

        public class a implements View.OnLayoutChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f2491a;

            public a(View view) {
                this.f2491a = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                if (this.f2491a.getVisibility() == 0) {
                    TabView.this.s(this.f2491a);
                }
            }
        }

        public TabView(Context context) {
            super(context);
            this.f2489k = 2;
            u(context);
            t.w0(this, TabLayout.this.f2461h, TabLayout.this.f2462i, TabLayout.this.f2463j, TabLayout.this.f2464k);
            setGravity(17);
            setOrientation(!TabLayout.this.D ? 1 : 0);
            setClickable(true);
            t.x0(this, r.b(getContext(), 1002));
        }

        private BadgeDrawable getBadge() {
            return this.f2484f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getContentWidth() {
            View[] viewArr = {this.f2481c, this.f2482d, this.f2485g};
            int iMax = 0;
            int iMin = 0;
            boolean z3 = false;
            for (int i3 = 0; i3 < 3; i3++) {
                View view = viewArr[i3];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z3 ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z3 ? Math.max(iMax, view.getRight()) : view.getRight();
                    z3 = true;
                }
            }
            return iMax - iMin;
        }

        private BadgeDrawable getOrCreateBadge() {
            if (this.f2484f == null) {
                this.f2484f = BadgeDrawable.c(getContext());
            }
            r();
            BadgeDrawable badgeDrawable = this.f2484f;
            if (badgeDrawable != null) {
                return badgeDrawable;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        public final void drawBackground(Canvas canvas) {
            Drawable drawable = this.f2488j;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.f2488j.draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.f2488j;
            boolean state = false;
            if (drawable != null && drawable.isStateful()) {
                state = false | this.f2488j.setState(drawableState);
            }
            if (state) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public final void g(View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(new a(view));
        }

        public g getTab() {
            return this.f2480b;
        }

        public final float h(Layout layout, int i3, float f3) {
            return layout.getLineWidth(i3) * (f3 / layout.getPaint().getTextSize());
        }

        public final void i(boolean z3) {
            setClipChildren(z3);
            setClipToPadding(z3);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(z3);
                viewGroup.setClipToPadding(z3);
            }
        }

        public final FrameLayout j() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout;
        }

        public final FrameLayout k(View view) {
            if ((view == this.f2482d || view == this.f2481c) && u1.a.f5348a) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        public final boolean l() {
            return this.f2484f != null;
        }

        public final void m() {
            ViewGroup viewGroup;
            if (u1.a.f5348a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ, 0);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R$layout.design_layout_tab_icon, viewGroup, false);
            this.f2482d = imageView;
            viewGroup.addView(imageView, 0);
        }

        public final void n() {
            ViewGroup viewGroup;
            if (u1.a.f5348a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R$layout.design_layout_tab_text, viewGroup, false);
            this.f2481c = textView;
            viewGroup.addView(textView);
        }

        public void o() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            BadgeDrawable badgeDrawable = this.f2484f;
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.f2484f.h()));
            }
            m0.c cVarD0 = m0.c.D0(accessibilityNodeInfo);
            cVarD0.e0(c.C0036c.a(0, 1, this.f2480b.f(), 1, false, isSelected()));
            if (isSelected()) {
                cVarD0.c0(false);
                cVarD0.S(c.a.f3806e);
            }
            cVarD0.t0("Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i3, int i4) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i3);
            int mode = View.MeasureSpec.getMode(i3);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i3 = View.MeasureSpec.makeMeasureSpec(TabLayout.this.f2474u, Integer.MIN_VALUE);
            }
            super.onMeasure(i3, i4);
            if (this.f2481c != null) {
                float f3 = TabLayout.this.f2471r;
                int i5 = this.f2489k;
                ImageView imageView = this.f2482d;
                boolean z3 = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f2481c;
                    if (textView != null && textView.getLineCount() > 1) {
                        f3 = TabLayout.this.f2472s;
                    }
                } else {
                    i5 = 1;
                }
                float textSize = this.f2481c.getTextSize();
                int lineCount = this.f2481c.getLineCount();
                int iD = n0.i.d(this.f2481c);
                if (f3 != textSize || (iD >= 0 && i5 != iD)) {
                    if (TabLayout.this.C == 1 && f3 > textSize && lineCount == 1 && ((layout = this.f2481c.getLayout()) == null || h(layout, 0, f3) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z3 = false;
                    }
                    if (z3) {
                        this.f2481c.setTextSize(0, f3);
                        this.f2481c.setMaxLines(i5);
                        super.onMeasure(i3, i4);
                    }
                }
            }
        }

        public final void p(View view) {
            if (l() && view != null) {
                i(false);
                u1.a.a(this.f2484f, view, k(view));
                this.f2483e = view;
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.f2480b == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.f2480b.k();
            return true;
        }

        public final void q() {
            if (l()) {
                i(true);
                View view = this.f2483e;
                if (view != null) {
                    u1.a.d(this.f2484f, view, k(view));
                    this.f2483e = null;
                }
            }
        }

        public final void r() {
            g gVar;
            g gVar2;
            if (l()) {
                if (this.f2485g != null) {
                    q();
                    return;
                }
                if (this.f2482d != null && (gVar2 = this.f2480b) != null && gVar2.e() != null) {
                    View view = this.f2483e;
                    ImageView imageView = this.f2482d;
                    if (view == imageView) {
                        s(imageView);
                        return;
                    } else {
                        q();
                        p(this.f2482d);
                        return;
                    }
                }
                if (this.f2481c == null || (gVar = this.f2480b) == null || gVar.g() != 1) {
                    q();
                    return;
                }
                View view2 = this.f2483e;
                TextView textView = this.f2481c;
                if (view2 == textView) {
                    s(textView);
                } else {
                    q();
                    p(this.f2481c);
                }
            }
        }

        public final void s(View view) {
            if (l() && view == this.f2483e) {
                u1.a.e(this.f2484f, view, k(view));
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z3) {
            boolean z4 = isSelected() != z3;
            super.setSelected(z3);
            if (z4 && z3 && Build.VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            TextView textView = this.f2481c;
            if (textView != null) {
                textView.setSelected(z3);
            }
            ImageView imageView = this.f2482d;
            if (imageView != null) {
                imageView.setSelected(z3);
            }
            View view = this.f2485g;
            if (view != null) {
                view.setSelected(z3);
            }
        }

        public void setTab(g gVar) {
            if (gVar != this.f2480b) {
                this.f2480b = gVar;
                t();
            }
        }

        public final void t() {
            g gVar = this.f2480b;
            Drawable drawableMutate = null;
            View viewD = gVar != null ? gVar.d() : null;
            if (viewD != null) {
                ViewParent parent = viewD.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewD);
                    }
                    addView(viewD);
                }
                this.f2485g = viewD;
                TextView textView = this.f2481c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f2482d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f2482d.setImageDrawable(null);
                }
                TextView textView2 = (TextView) viewD.findViewById(R.id.text1);
                this.f2486h = textView2;
                if (textView2 != null) {
                    this.f2489k = n0.i.d(textView2);
                }
                this.f2487i = (ImageView) viewD.findViewById(R.id.icon);
            } else {
                View view = this.f2485g;
                if (view != null) {
                    removeView(view);
                    this.f2485g = null;
                }
                this.f2486h = null;
                this.f2487i = null;
            }
            if (this.f2485g == null) {
                if (this.f2482d == null) {
                    m();
                }
                if (gVar != null && gVar.e() != null) {
                    drawableMutate = e0.a.r(gVar.e()).mutate();
                }
                if (drawableMutate != null) {
                    e0.a.o(drawableMutate, TabLayout.this.f2467n);
                    PorterDuff.Mode mode = TabLayout.this.f2470q;
                    if (mode != null) {
                        e0.a.p(drawableMutate, mode);
                    }
                }
                if (this.f2481c == null) {
                    n();
                    this.f2489k = n0.i.d(this.f2481c);
                }
                n0.i.q(this.f2481c, TabLayout.this.f2465l);
                ColorStateList colorStateList = TabLayout.this.f2466m;
                if (colorStateList != null) {
                    this.f2481c.setTextColor(colorStateList);
                }
                w(this.f2481c, this.f2482d);
                r();
                g(this.f2482d);
                g(this.f2481c);
            } else {
                TextView textView3 = this.f2486h;
                if (textView3 != null || this.f2487i != null) {
                    w(textView3, this.f2487i);
                }
            }
            if (gVar != null && !TextUtils.isEmpty(gVar.f2517d)) {
                setContentDescription(gVar.f2517d);
            }
            setSelected(gVar != null && gVar.i());
        }

        public final void u(Context context) {
            int i3 = TabLayout.this.f2473t;
            if (i3 != 0) {
                Drawable drawableD = j.a.d(context, i3);
                this.f2488j = drawableD;
                if (drawableD != null && drawableD.isStateful()) {
                    this.f2488j.setState(getDrawableState());
                }
            } else {
                this.f2488j = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            Drawable layerDrawable = gradientDrawable;
            if (TabLayout.this.f2468o != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList colorStateListA = l2.b.a(TabLayout.this.f2468o);
                if (Build.VERSION.SDK_INT >= 21) {
                    boolean z3 = TabLayout.this.F;
                    GradientDrawable gradientDrawable3 = gradientDrawable;
                    if (z3) {
                        gradientDrawable3 = null;
                    }
                    layerDrawable = new RippleDrawable(colorStateListA, gradientDrawable3, z3 ? null : gradientDrawable2);
                } else {
                    Drawable drawableR = e0.a.r(gradientDrawable2);
                    e0.a.o(drawableR, colorStateListA);
                    layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, drawableR});
                }
            }
            t.m0(this, layerDrawable);
            TabLayout.this.invalidate();
        }

        public final void v() {
            setOrientation(!TabLayout.this.D ? 1 : 0);
            TextView textView = this.f2486h;
            if (textView == null && this.f2487i == null) {
                w(this.f2481c, this.f2482d);
            } else {
                w(textView, this.f2487i);
            }
        }

        public final void w(TextView textView, ImageView imageView) {
            g gVar = this.f2480b;
            Drawable drawableMutate = (gVar == null || gVar.e() == null) ? null : e0.a.r(this.f2480b.e()).mutate();
            g gVar2 = this.f2480b;
            CharSequence charSequenceH = gVar2 != null ? gVar2.h() : null;
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
            boolean z3 = !TextUtils.isEmpty(charSequenceH);
            if (textView != null) {
                if (z3) {
                    textView.setText(charSequenceH);
                    if (this.f2480b.f2520g == 1) {
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
                int iC = (z3 && imageView.getVisibility() == 0) ? (int) p.c(getContext(), 8) : 0;
                if (TabLayout.this.D) {
                    if (iC != l0.g.a(marginLayoutParams)) {
                        l0.g.c(marginLayoutParams, iC);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iC != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iC;
                    l0.g.c(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            g gVar3 = this.f2480b;
            g0.a(this, z3 ? null : gVar3 != null ? gVar3.f2517d : null);
        }
    }

    public class a implements ValueAnimator.AnimatorUpdateListener {
        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
        }
    }

    public class b implements ViewPager.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2494a;

        public b() {
        }

        public void a(boolean z3) {
            this.f2494a = z3;
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void b(ViewPager viewPager, b1.a aVar, b1.a aVar2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.K == viewPager) {
                tabLayout.E(aVar2, this.f2494a);
            }
        }
    }

    @Deprecated
    public interface c<T extends g> {
        void a(T t3);

        void b(T t3);

        void c(T t3);
    }

    public interface d extends c<g> {
    }

    public class e extends DataSetObserver {
        public e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TabLayout.this.x();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayout.this.x();
        }
    }

    public class f extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2497b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Paint f2498c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final GradientDrawable f2499d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2500e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f2501f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f2502g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f2503h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f2504i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public ValueAnimator f2505j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f2506k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f2507l;

        public class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f2509a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ int f2510b;

            public a(int i3, int i4) {
                this.f2509a = i3;
                this.f2510b = i4;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                f fVar = f.this;
                fVar.f(s1.a.b(fVar.f2506k, this.f2509a, animatedFraction), s1.a.b(f.this.f2507l, this.f2510b, animatedFraction));
            }
        }

        public class b extends AnimatorListenerAdapter {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f2512a;

            public b(int i3) {
                this.f2512a = i3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                f fVar = f.this;
                fVar.f2500e = this.f2512a;
                fVar.f2501f = 0.0f;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                f.this.f2500e = this.f2512a;
            }
        }

        public f(Context context) {
            super(context);
            this.f2500e = -1;
            this.f2502g = -1;
            this.f2503h = -1;
            this.f2504i = -1;
            this.f2506k = -1;
            this.f2507l = -1;
            setWillNotDraw(false);
            this.f2498c = new Paint();
            this.f2499d = new GradientDrawable();
        }

        public void c(int i3, int i4) {
            ValueAnimator valueAnimator = this.f2505j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.f2505j.cancel();
            }
            k(true, i3, i4);
        }

        public final void d(TabView tabView, RectF rectF) {
            int contentWidth = tabView.getContentWidth();
            int iC = (int) p.c(getContext(), 24);
            if (contentWidth < iC) {
                contentWidth = iC;
            }
            int left = (tabView.getLeft() + tabView.getRight()) / 2;
            int i3 = contentWidth / 2;
            rectF.set(left - i3, 0.0f, left + i3, 0.0f);
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            Drawable drawable = TabLayout.this.f2469p;
            int height = 0;
            int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
            int i3 = this.f2497b;
            if (i3 >= 0) {
                intrinsicHeight = i3;
            }
            int i4 = TabLayout.this.B;
            if (i4 == 0) {
                height = getHeight() - intrinsicHeight;
                intrinsicHeight = getHeight();
            } else if (i4 == 1) {
                height = (getHeight() - intrinsicHeight) / 2;
                intrinsicHeight = (getHeight() + intrinsicHeight) / 2;
            } else if (i4 != 2) {
                intrinsicHeight = i4 != 3 ? 0 : getHeight();
            }
            int i5 = this.f2503h;
            if (i5 >= 0 && this.f2504i > i5) {
                Drawable drawable2 = TabLayout.this.f2469p;
                if (drawable2 == null) {
                    drawable2 = this.f2499d;
                }
                Drawable drawableMutate = e0.a.r(drawable2).mutate();
                drawableMutate.setBounds(this.f2503h, height, this.f2504i, intrinsicHeight);
                Paint paint = this.f2498c;
                if (paint != null) {
                    if (Build.VERSION.SDK_INT == 21) {
                        drawableMutate.setColorFilter(paint.getColor(), PorterDuff.Mode.SRC_IN);
                    } else {
                        e0.a.n(drawableMutate, paint.getColor());
                    }
                }
                drawableMutate.draw(canvas);
            }
            super.draw(canvas);
        }

        public boolean e() {
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                if (getChildAt(i3).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        public void f(int i3, int i4) {
            if (i3 == this.f2503h && i4 == this.f2504i) {
                return;
            }
            this.f2503h = i3;
            this.f2504i = i4;
            t.a0(this);
        }

        public void g(int i3, float f3) {
            ValueAnimator valueAnimator = this.f2505j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.f2505j.cancel();
            }
            this.f2500e = i3;
            this.f2501f = f3;
            j();
        }

        public void h(int i3) {
            if (this.f2498c.getColor() != i3) {
                this.f2498c.setColor(i3);
                t.a0(this);
            }
        }

        public void i(int i3) {
            if (this.f2497b != i3) {
                this.f2497b = i3;
                t.a0(this);
            }
        }

        public final void j() {
            int left;
            int right;
            View childAt = getChildAt(this.f2500e);
            if (childAt == null || childAt.getWidth() <= 0) {
                left = -1;
                right = -1;
            } else {
                left = childAt.getLeft();
                right = childAt.getRight();
                TabLayout tabLayout = TabLayout.this;
                if (!tabLayout.E && (childAt instanceof TabView)) {
                    d((TabView) childAt, tabLayout.f2459f);
                    left = (int) TabLayout.this.f2459f.left;
                    right = (int) TabLayout.this.f2459f.right;
                }
                if (this.f2501f > 0.0f && this.f2500e < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.f2500e + 1);
                    int left2 = childAt2.getLeft();
                    int right2 = childAt2.getRight();
                    TabLayout tabLayout2 = TabLayout.this;
                    if (!tabLayout2.E && (childAt2 instanceof TabView)) {
                        d((TabView) childAt2, tabLayout2.f2459f);
                        left2 = (int) TabLayout.this.f2459f.left;
                        right2 = (int) TabLayout.this.f2459f.right;
                    }
                    float f3 = this.f2501f;
                    left = (int) ((left2 * f3) + ((1.0f - f3) * left));
                    right = (int) ((right2 * f3) + ((1.0f - f3) * right));
                }
            }
            f(left, right);
        }

        public final void k(boolean z3, int i3, int i4) {
            View childAt = getChildAt(i3);
            if (childAt == null) {
                j();
                return;
            }
            int left = childAt.getLeft();
            int right = childAt.getRight();
            TabLayout tabLayout = TabLayout.this;
            if (!tabLayout.E && (childAt instanceof TabView)) {
                d((TabView) childAt, tabLayout.f2459f);
                left = (int) TabLayout.this.f2459f.left;
                right = (int) TabLayout.this.f2459f.right;
            }
            int i5 = this.f2503h;
            int i6 = this.f2504i;
            if (i5 == left && i6 == right) {
                return;
            }
            if (z3) {
                this.f2506k = i5;
                this.f2507l = i6;
            }
            a aVar = new a(left, right);
            if (!z3) {
                this.f2505j.removeAllUpdateListeners();
                this.f2505j.addUpdateListener(aVar);
                return;
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            this.f2505j = valueAnimator;
            valueAnimator.setInterpolator(s1.a.f4902b);
            valueAnimator.setDuration(i4);
            valueAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimator.addUpdateListener(aVar);
            valueAnimator.addListener(new b(i3));
            valueAnimator.start();
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
            super.onLayout(z3, i3, i4, i5, i6);
            ValueAnimator valueAnimator = this.f2505j;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                j();
            } else {
                k(false, this.f2500e, -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i3, int i4) {
            super.onMeasure(i3, i4);
            if (View.MeasureSpec.getMode(i3) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            boolean z3 = true;
            if (tabLayout.f2479z == 1 || tabLayout.C == 2) {
                int childCount = getChildCount();
                int iMax = 0;
                for (int i5 = 0; i5 < childCount; i5++) {
                    View childAt = getChildAt(i5);
                    if (childAt.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount <= getMeasuredWidth() - (((int) p.c(getContext(), 16)) * 2)) {
                    boolean z4 = false;
                    for (int i6 = 0; i6 < childCount; i6++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i6).getLayoutParams();
                        if (layoutParams.width != iMax || layoutParams.weight != 0.0f) {
                            layoutParams.width = iMax;
                            layoutParams.weight = 0.0f;
                            z4 = true;
                        }
                    }
                    z3 = z4;
                } else {
                    TabLayout tabLayout2 = TabLayout.this;
                    tabLayout2.f2479z = 0;
                    tabLayout2.L(false);
                }
                if (z3) {
                    super.onMeasure(i3, i4);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i3) {
            super.onRtlPropertiesChanged(i3);
            if (Build.VERSION.SDK_INT >= 23 || this.f2502g == i3) {
                return;
            }
            requestLayout();
            this.f2502g = i3;
        }
    }

    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f2514a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Drawable f2515b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f2516c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f2517d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f2519f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public TabLayout f2521h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public TabView f2522i;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2518e = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f2520g = 1;

        public View d() {
            return this.f2519f;
        }

        public Drawable e() {
            return this.f2515b;
        }

        public int f() {
            return this.f2518e;
        }

        public int g() {
            return this.f2520g;
        }

        public CharSequence h() {
            return this.f2516c;
        }

        public boolean i() {
            TabLayout tabLayout = this.f2521h;
            if (tabLayout != null) {
                return tabLayout.getSelectedTabPosition() == this.f2518e;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public void j() {
            this.f2521h = null;
            this.f2522i = null;
            this.f2514a = null;
            this.f2515b = null;
            this.f2516c = null;
            this.f2517d = null;
            this.f2518e = -1;
            this.f2519f = null;
        }

        public void k() {
            TabLayout tabLayout = this.f2521h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.C(this);
        }

        public g l(CharSequence charSequence) {
            this.f2517d = charSequence;
            r();
            return this;
        }

        public g m(int i3) {
            return n(LayoutInflater.from(this.f2522i.getContext()).inflate(i3, (ViewGroup) this.f2522i, false));
        }

        public g n(View view) {
            this.f2519f = view;
            r();
            return this;
        }

        public g o(Drawable drawable) {
            this.f2515b = drawable;
            TabLayout tabLayout = this.f2521h;
            if (tabLayout.f2479z == 1 || tabLayout.C == 2) {
                tabLayout.L(true);
            }
            r();
            if (u1.a.f5348a && this.f2522i.l() && this.f2522i.f2484f.isVisible()) {
                this.f2522i.invalidate();
            }
            return this;
        }

        public void p(int i3) {
            this.f2518e = i3;
        }

        public g q(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.f2517d) && !TextUtils.isEmpty(charSequence)) {
                this.f2522i.setContentDescription(charSequence);
            }
            this.f2516c = charSequence;
            r();
            return this;
        }

        public void r() {
            TabView tabView = this.f2522i;
            if (tabView != null) {
                tabView.t();
            }
        }
    }

    public static class h implements ViewPager.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<TabLayout> f2523a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2524b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2525c;

        public h(TabLayout tabLayout) {
            this.f2523a = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i3, float f3, int i4) {
            TabLayout tabLayout = this.f2523a.get();
            if (tabLayout != null) {
                int i5 = this.f2525c;
                tabLayout.G(i3, f3, i5 != 2 || this.f2524b == 1, (i5 == 2 && this.f2524b == 0) ? false : true);
            }
        }

        public void b() {
            this.f2525c = 0;
            this.f2524b = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i3) {
            this.f2524b = this.f2525c;
            this.f2525c = i3;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i3) {
            TabLayout tabLayout = this.f2523a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i3 || i3 >= tabLayout.getTabCount()) {
                return;
            }
            int i4 = this.f2525c;
            tabLayout.D(tabLayout.v(i3), i4 == 0 || (i4 == 2 && this.f2524b == 0));
        }
    }

    public static class i implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ViewPager f2526a;

        public i(ViewPager viewPager) {
            this.f2526a = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(g gVar) {
            this.f2526a.setCurrentItem(gVar.f());
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(g gVar) {
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private int getDefaultHeight() {
        int size = this.f2457d.size();
        boolean z3 = false;
        int i3 = 0;
        while (true) {
            if (i3 < size) {
                g gVar = this.f2457d.get(i3);
                if (gVar != null && gVar.e() != null && !TextUtils.isEmpty(gVar.h())) {
                    z3 = true;
                    break;
                }
                i3++;
            } else {
                break;
            }
        }
        return (!z3 || this.D) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i3 = this.f2475v;
        if (i3 != -1) {
            return i3;
        }
        int i4 = this.C;
        if (i4 == 0 || i4 == 2) {
            return this.f2477x;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.f2460g.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    public static ColorStateList n(int i3, int i4) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i4, i3});
    }

    private void setSelectedTabView(int i3) {
        int childCount = this.f2460g.getChildCount();
        if (i3 < childCount) {
            int i4 = 0;
            while (i4 < childCount) {
                View childAt = this.f2460g.getChildAt(i4);
                boolean z3 = true;
                childAt.setSelected(i4 == i3);
                if (i4 != i3) {
                    z3 = false;
                }
                childAt.setActivated(z3);
                i4++;
            }
        }
    }

    @Deprecated
    public void A(c cVar) {
        this.H.remove(cVar);
    }

    public final void B(int i3) {
        TabView tabView = (TabView) this.f2460g.getChildAt(i3);
        this.f2460g.removeViewAt(i3);
        if (tabView != null) {
            tabView.o();
            this.Q.a(tabView);
        }
        requestLayout();
    }

    public void C(g gVar) {
        D(gVar, true);
    }

    public void D(g gVar, boolean z3) {
        g gVar2 = this.f2458e;
        if (gVar2 == gVar) {
            if (gVar2 != null) {
                r(gVar);
                i(gVar.f());
                return;
            }
            return;
        }
        int iF = gVar != null ? gVar.f() : -1;
        if (z3) {
            if ((gVar2 == null || gVar2.f() == -1) && iF != -1) {
                F(iF, 0.0f, true);
            } else {
                i(iF);
            }
            if (iF != -1) {
                setSelectedTabView(iF);
            }
        }
        this.f2458e = gVar;
        if (gVar2 != null) {
            t(gVar2);
        }
        if (gVar != null) {
            s(gVar);
        }
    }

    public void E(b1.a aVar, boolean z3) {
        DataSetObserver dataSetObserver;
        b1.a aVar2 = this.L;
        if (aVar2 != null && (dataSetObserver = this.M) != null) {
            aVar2.t(dataSetObserver);
        }
        this.L = aVar;
        if (z3 && aVar != null) {
            if (this.M == null) {
                this.M = new e();
            }
            aVar.l(this.M);
        }
        x();
    }

    public void F(int i3, float f3, boolean z3) {
        G(i3, f3, z3, true);
    }

    public void G(int i3, float f3, boolean z3, boolean z4) {
        int iRound = Math.round(i3 + f3);
        if (iRound < 0 || iRound >= this.f2460g.getChildCount()) {
            return;
        }
        if (z4) {
            this.f2460g.g(i3, f3);
        }
        ValueAnimator valueAnimator = this.J;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.J.cancel();
        }
        scrollTo(l(i3, f3), 0);
        if (z3) {
            setSelectedTabView(iRound);
        }
    }

    public void H(ViewPager viewPager, boolean z3) {
        I(viewPager, z3, false);
    }

    public final void I(ViewPager viewPager, boolean z3, boolean z4) {
        ViewPager viewPager2 = this.K;
        if (viewPager2 != null) {
            h hVar = this.N;
            if (hVar != null) {
                viewPager2.J(hVar);
            }
            b bVar = this.O;
            if (bVar != null) {
                this.K.I(bVar);
            }
        }
        c cVar = this.I;
        if (cVar != null) {
            A(cVar);
            this.I = null;
        }
        if (viewPager != null) {
            this.K = viewPager;
            if (this.N == null) {
                this.N = new h(this);
            }
            this.N.b();
            viewPager.c(this.N);
            i iVar = new i(viewPager);
            this.I = iVar;
            b(iVar);
            b1.a adapter = viewPager.getAdapter();
            if (adapter != null) {
                E(adapter, z3);
            }
            if (this.O == null) {
                this.O = new b();
            }
            this.O.a(z3);
            viewPager.b(this.O);
            F(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.K = null;
            E(null, false);
        }
        this.P = z4;
    }

    public final void J() {
        int size = this.f2457d.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.f2457d.get(i3).r();
        }
    }

    public final void K(LinearLayout.LayoutParams layoutParams) {
        if (this.C == 1 && this.f2479z == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    public void L(boolean z3) {
        for (int i3 = 0; i3 < this.f2460g.getChildCount(); i3++) {
            View childAt = this.f2460g.getChildAt(i3);
            childAt.setMinimumWidth(getTabMinWidth());
            K((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z3) {
                childAt.requestLayout();
            }
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        h(view);
    }

    @Deprecated
    public void b(c cVar) {
        if (this.H.contains(cVar)) {
            return;
        }
        this.H.add(cVar);
    }

    public void c(g gVar) {
        e(gVar, this.f2457d.isEmpty());
    }

    public void d(g gVar, int i3, boolean z3) {
        if (gVar.f2521h != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        m(gVar, i3);
        g(gVar);
        if (z3) {
            gVar.k();
        }
    }

    public void e(g gVar, boolean z3) {
        d(gVar, this.f2457d.size(), z3);
    }

    public final void f(TabItem tabItem) {
        g gVarW = w();
        CharSequence charSequence = tabItem.f2452b;
        if (charSequence != null) {
            gVarW.q(charSequence);
        }
        Drawable drawable = tabItem.f2453c;
        if (drawable != null) {
            gVarW.o(drawable);
        }
        int i3 = tabItem.f2454d;
        if (i3 != 0) {
            gVarW.m(i3);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            gVarW.l(tabItem.getContentDescription());
        }
        c(gVarW);
    }

    public final void g(g gVar) {
        TabView tabView = gVar.f2522i;
        tabView.setSelected(false);
        tabView.setActivated(false);
        this.f2460g.addView(tabView, gVar.f(), o());
    }

    public int getSelectedTabPosition() {
        g gVar = this.f2458e;
        if (gVar != null) {
            return gVar.f();
        }
        return -1;
    }

    public int getTabCount() {
        return this.f2457d.size();
    }

    public int getTabGravity() {
        return this.f2479z;
    }

    public ColorStateList getTabIconTint() {
        return this.f2467n;
    }

    public int getTabIndicatorGravity() {
        return this.B;
    }

    public int getTabMaxWidth() {
        return this.f2474u;
    }

    public int getTabMode() {
        return this.C;
    }

    public ColorStateList getTabRippleColor() {
        return this.f2468o;
    }

    public Drawable getTabSelectedIndicator() {
        return this.f2469p;
    }

    public ColorStateList getTabTextColors() {
        return this.f2466m;
    }

    public final void h(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        f((TabItem) view);
    }

    public final void i(int i3) {
        if (i3 == -1) {
            return;
        }
        if (getWindowToken() == null || !t.P(this) || this.f2460g.e()) {
            F(i3, 0.0f, true);
            return;
        }
        int scrollX = getScrollX();
        int iL = l(i3, 0.0f);
        if (scrollX != iL) {
            u();
            this.J.setIntValues(scrollX, iL);
            this.J.start();
        }
        this.f2460g.c(i3, this.A);
    }

    public final void j(int i3) {
        if (i3 == 0) {
            Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        } else if (i3 == 1) {
            this.f2460g.setGravity(1);
            return;
        } else if (i3 != 2) {
            return;
        }
        this.f2460g.setGravity(8388611);
    }

    public final void k() {
        int i3 = this.C;
        t.w0(this.f2460g, (i3 == 0 || i3 == 2) ? Math.max(0, this.f2478y - this.f2461h) : 0, 0, 0, 0);
        int i4 = this.C;
        if (i4 == 0) {
            j(this.f2479z);
        } else if (i4 == 1 || i4 == 2) {
            if (this.f2479z == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            this.f2460g.setGravity(1);
        }
        L(true);
    }

    public final int l(int i3, float f3) {
        int i4 = this.C;
        if (i4 != 0 && i4 != 2) {
            return 0;
        }
        View childAt = this.f2460g.getChildAt(i3);
        int i5 = i3 + 1;
        View childAt2 = i5 < this.f2460g.getChildCount() ? this.f2460g.getChildAt(i5) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i6 = (int) ((width + width2) * 0.5f * f3);
        return t.z(this) == 0 ? left + i6 : left - i6;
    }

    public final void m(g gVar, int i3) {
        gVar.p(i3);
        this.f2457d.add(i3, gVar);
        int size = this.f2457d.size();
        while (true) {
            i3++;
            if (i3 >= size) {
                return;
            } else {
                this.f2457d.get(i3).p(i3);
            }
        }
    }

    public final LinearLayout.LayoutParams o() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        K(layoutParams);
        return layoutParams;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        n2.i.e(this);
        if (this.K == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                I((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.P) {
            setupWithViewPager(null);
            this.P = false;
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        for (int i3 = 0; i3 < this.f2460g.getChildCount(); i3++) {
            View childAt = this.f2460g.getChildAt(i3);
            if (childAt instanceof TabView) {
                ((TabView) childAt).drawBackground(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        m0.c.D0(accessibilityNodeInfo).d0(c.b.b(1, getTabCount(), false, 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0082  */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            int r1 = r6.getDefaultHeight()
            float r0 = i2.p.c(r0, r1)
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
            int r1 = r6.f2476w
            if (r1 <= 0) goto L50
            goto L5d
        L50:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = i2.p.c(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L5d:
            r6.f2474u = r1
        L5f:
            super.onMeasure(r7, r8)
            int r7 = r6.getChildCount()
            if (r7 != r5) goto Lad
            android.view.View r7 = r6.getChildAt(r4)
            int r0 = r6.C
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

    public g p() {
        g gVarB = f2456c.b();
        return gVarB == null ? new g() : gVarB;
    }

    public final TabView q(g gVar) {
        k0.e<TabView> eVar = this.Q;
        TabView tabViewB = eVar != null ? eVar.b() : null;
        if (tabViewB == null) {
            tabViewB = new TabView(getContext());
        }
        tabViewB.setTab(gVar);
        tabViewB.setFocusable(true);
        tabViewB.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(gVar.f2517d)) {
            tabViewB.setContentDescription(gVar.f2516c);
        } else {
            tabViewB.setContentDescription(gVar.f2517d);
        }
        return tabViewB;
    }

    public final void r(g gVar) {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            this.H.get(size).c(gVar);
        }
    }

    public final void s(g gVar) {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            this.H.get(size).b(gVar);
        }
    }

    @Override // android.view.View
    public void setElevation(float f3) {
        super.setElevation(f3);
        n2.i.d(this, f3);
    }

    public void setInlineLabel(boolean z3) {
        if (this.D != z3) {
            this.D = z3;
            for (int i3 = 0; i3 < this.f2460g.getChildCount(); i3++) {
                View childAt = this.f2460g.getChildAt(i3);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).v();
                }
            }
            k();
        }
    }

    public void setInlineLabelResource(int i3) {
        setInlineLabel(getResources().getBoolean(i3));
    }

    @Deprecated
    public void setOnTabSelectedListener(d dVar) {
        setOnTabSelectedListener((c) dVar);
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        u();
        this.J.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.f2469p != drawable) {
            this.f2469p = drawable;
            t.a0(this.f2460g);
        }
    }

    public void setSelectedTabIndicatorColor(int i3) {
        this.f2460g.h(i3);
    }

    public void setSelectedTabIndicatorGravity(int i3) {
        if (this.B != i3) {
            this.B = i3;
            t.a0(this.f2460g);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i3) {
        this.f2460g.i(i3);
    }

    public void setTabGravity(int i3) {
        if (this.f2479z != i3) {
            this.f2479z = i3;
            k();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.f2467n != colorStateList) {
            this.f2467n = colorStateList;
            J();
        }
    }

    public void setTabIconTintResource(int i3) {
        setTabIconTint(j.a.c(getContext(), i3));
    }

    public void setTabIndicatorFullWidth(boolean z3) {
        this.E = z3;
        t.a0(this.f2460g);
    }

    public void setTabMode(int i3) {
        if (i3 != this.C) {
            this.C = i3;
            k();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.f2468o != colorStateList) {
            this.f2468o = colorStateList;
            for (int i3 = 0; i3 < this.f2460g.getChildCount(); i3++) {
                View childAt = this.f2460g.getChildAt(i3);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).u(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int i3) {
        setTabRippleColor(j.a.c(getContext(), i3));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.f2466m != colorStateList) {
            this.f2466m = colorStateList;
            J();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(b1.a aVar) {
        E(aVar, false);
    }

    public void setUnboundedRipple(boolean z3) {
        if (this.F != z3) {
            this.F = z3;
            for (int i3 = 0; i3 < this.f2460g.getChildCount(); i3++) {
                View childAt = this.f2460g.getChildAt(i3);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).u(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i3) {
        setUnboundedRipple(getResources().getBoolean(i3));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        H(viewPager, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public final void t(g gVar) {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            this.H.get(size).a(gVar);
        }
    }

    public final void u() {
        if (this.J == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.J = valueAnimator;
            valueAnimator.setInterpolator(s1.a.f4902b);
            this.J.setDuration(this.A);
            this.J.addUpdateListener(new a());
        }
    }

    public g v(int i3) {
        if (i3 < 0 || i3 >= getTabCount()) {
            return null;
        }
        return this.f2457d.get(i3);
    }

    public g w() {
        g gVarP = p();
        gVarP.f2521h = this;
        gVarP.f2522i = q(gVarP);
        return gVarP;
    }

    public void x() {
        int currentItem;
        z();
        b1.a aVar = this.L;
        if (aVar != null) {
            int iE = aVar.e();
            for (int i3 = 0; i3 < iE; i3++) {
                e(w().q(this.L.g(i3)), false);
            }
            ViewPager viewPager = this.K;
            if (viewPager == null || iE <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            C(v(currentItem));
        }
    }

    public boolean y(g gVar) {
        return f2456c.a(gVar);
    }

    public void z() {
        for (int childCount = this.f2460g.getChildCount() - 1; childCount >= 0; childCount--) {
            B(childCount);
        }
        Iterator<g> it = this.f2457d.iterator();
        while (it.hasNext()) {
            g next = it.next();
            it.remove();
            next.j();
            y(next);
        }
        this.f2458e = null;
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.tabStyle);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i3) {
        h(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void setOnTabSelectedListener(c cVar) {
        c cVar2 = this.G;
        if (cVar2 != null) {
            A(cVar2);
        }
        this.G = cVar;
        if (cVar != null) {
            b(cVar);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TabLayout(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2455b;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f2457d = new ArrayList<>();
        this.f2459f = new RectF();
        this.f2474u = Integer.MAX_VALUE;
        this.H = new ArrayList<>();
        this.Q = new k0.f(12);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        f fVar = new f(context2);
        this.f2460g = fVar;
        super.addView(fVar, 0, new FrameLayout.LayoutParams(-2, -1));
        int[] iArr = R$styleable.TabLayout;
        int i5 = R$styleable.TabLayout_tabTextAppearance;
        TypedArray typedArrayH = k.h(context2, attributeSet, iArr, i3, i4, i5);
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            n2.h hVar = new n2.h();
            hVar.X(ColorStateList.valueOf(colorDrawable.getColor()));
            hVar.N(context2);
            hVar.W(t.u(this));
            t.m0(this, hVar);
        }
        fVar.i(typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabIndicatorHeight, -1));
        fVar.h(typedArrayH.getColor(R$styleable.TabLayout_tabIndicatorColor, 0));
        setSelectedTabIndicator(k2.c.d(context2, typedArrayH, R$styleable.TabLayout_tabIndicator));
        setSelectedTabIndicatorGravity(typedArrayH.getInt(R$styleable.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorFullWidth(typedArrayH.getBoolean(R$styleable.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabPadding, 0);
        this.f2464k = dimensionPixelSize;
        this.f2463j = dimensionPixelSize;
        this.f2462i = dimensionPixelSize;
        this.f2461h = dimensionPixelSize;
        this.f2461h = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.f2462i = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingTop, this.f2462i);
        this.f2463j = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingEnd, this.f2463j);
        this.f2464k = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingBottom, this.f2464k);
        int resourceId = typedArrayH.getResourceId(i5, R$style.TextAppearance_Design_Tab);
        this.f2465l = resourceId;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, androidx.appcompat.R$styleable.TextAppearance);
        try {
            this.f2471r = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R$styleable.TextAppearance_android_textSize, 0);
            this.f2466m = k2.c.a(context2, typedArrayObtainStyledAttributes, androidx.appcompat.R$styleable.TextAppearance_android_textColor);
            typedArrayObtainStyledAttributes.recycle();
            int i6 = R$styleable.TabLayout_tabTextColor;
            if (typedArrayH.hasValue(i6)) {
                this.f2466m = k2.c.a(context2, typedArrayH, i6);
            }
            int i7 = R$styleable.TabLayout_tabSelectedTextColor;
            if (typedArrayH.hasValue(i7)) {
                this.f2466m = n(this.f2466m.getDefaultColor(), typedArrayH.getColor(i7, 0));
            }
            this.f2467n = k2.c.a(context2, typedArrayH, R$styleable.TabLayout_tabIconTint);
            this.f2470q = p.i(typedArrayH.getInt(R$styleable.TabLayout_tabIconTintMode, -1), null);
            this.f2468o = k2.c.a(context2, typedArrayH, R$styleable.TabLayout_tabRippleColor);
            this.A = typedArrayH.getInt(R$styleable.TabLayout_tabIndicatorAnimationDuration, 300);
            this.f2475v = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabMinWidth, -1);
            this.f2476w = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabMaxWidth, -1);
            this.f2473t = typedArrayH.getResourceId(R$styleable.TabLayout_tabBackground, 0);
            this.f2478y = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabContentStart, 0);
            this.C = typedArrayH.getInt(R$styleable.TabLayout_tabMode, 1);
            this.f2479z = typedArrayH.getInt(R$styleable.TabLayout_tabGravity, 0);
            this.D = typedArrayH.getBoolean(R$styleable.TabLayout_tabInlineLabel, false);
            this.F = typedArrayH.getBoolean(R$styleable.TabLayout_tabUnboundedRipple, false);
            typedArrayH.recycle();
            Resources resources = getResources();
            this.f2472s = resources.getDimensionPixelSize(R$dimen.design_tab_text_size_2line);
            this.f2477x = resources.getDimensionPixelSize(R$dimen.design_tab_scrollable_min_width);
            k();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        h(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i3, ViewGroup.LayoutParams layoutParams) {
        h(view);
    }

    public void setSelectedTabIndicator(int i3) {
        if (i3 != 0) {
            setSelectedTabIndicator(j.a.d(getContext(), i3));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }
}
