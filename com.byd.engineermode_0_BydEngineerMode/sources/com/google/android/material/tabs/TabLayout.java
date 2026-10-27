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
import androidx.appcompat.widget.g0;
import androidx.viewpager.widget.ViewPager;
import b.a.j;
import b.g.k.c0.c;
import b.g.k.r;
import b.g.k.t;
import c.b.a.a.k;
import c.b.a.a.l;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.p;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class TabLayout extends HorizontalScrollView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f2885b = k.n;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final b.g.j.e<g> f2886c = new b.g.j.g(16);
    int A;
    int B;
    int C;
    boolean D;
    boolean E;
    boolean F;
    private c G;
    private final ArrayList<c> H;
    private c I;
    private ValueAnimator J;
    ViewPager K;
    private androidx.viewpager.widget.a L;
    private DataSetObserver M;
    private h N;
    private b O;
    private boolean P;
    private final b.g.j.e<TabView> Q;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ArrayList<g> f2887d;
    private g e;
    private final RectF f;
    final f g;
    int h;
    int i;
    int j;
    int k;
    int l;
    ColorStateList m;
    ColorStateList n;
    ColorStateList o;
    Drawable p;
    PorterDuff.Mode q;
    float r;
    float s;
    final int t;
    int u;
    private final int v;
    private final int w;
    private final int x;
    private int y;
    int z;

    public final class TabView extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private g f2888b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private TextView f2889c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ImageView f2890d;
        private View e;
        private BadgeDrawable f;
        private View g;
        private TextView h;
        private ImageView i;
        private Drawable j;
        private int k;

        class a implements View.OnLayoutChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ View f2891a;

            a(View view) {
                this.f2891a = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (this.f2891a.getVisibility() == 0) {
                    TabView.this.s(this.f2891a);
                }
            }
        }

        public TabView(Context context) {
            super(context);
            this.k = 2;
            u(context);
            t.w0(this, TabLayout.this.h, TabLayout.this.i, TabLayout.this.j, TabLayout.this.k);
            setGravity(17);
            setOrientation(!TabLayout.this.D ? 1 : 0);
            setClickable(true);
            t.x0(this, r.b(getContext(), 1002));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void drawBackground(Canvas canvas) {
            Drawable drawable = this.j;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.j.draw(canvas);
            }
        }

        private void g(View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(new a(view));
        }

        private BadgeDrawable getBadge() {
            return this.f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getContentWidth() {
            View[] viewArr = {this.f2889c, this.f2890d, this.g};
            int iMax = 0;
            int iMin = 0;
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                View view = viewArr[i];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z ? Math.max(iMax, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return iMax - iMin;
        }

        private BadgeDrawable getOrCreateBadge() {
            if (this.f == null) {
                this.f = BadgeDrawable.c(getContext());
            }
            r();
            BadgeDrawable badgeDrawable = this.f;
            if (badgeDrawable != null) {
                return badgeDrawable;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        private float h(Layout layout, int i, float f) {
            return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
        }

        private void i(boolean z) {
            setClipChildren(z);
            setClipToPadding(z);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(z);
                viewGroup.setClipToPadding(z);
            }
        }

        private FrameLayout j() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout;
        }

        private FrameLayout k(View view) {
            if ((view == this.f2890d || view == this.f2889c) && com.google.android.material.badge.a.f2564a) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean l() {
            return this.f != null;
        }

        private void m() {
            ViewGroup viewGroup;
            if (com.google.android.material.badge.a.f2564a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ, 0);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(c.b.a.a.h.f2115b, viewGroup, false);
            this.f2890d = imageView;
            viewGroup.addView(imageView, 0);
        }

        private void n() {
            ViewGroup viewGroup;
            if (com.google.android.material.badge.a.f2564a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(c.b.a.a.h.f2116c, viewGroup, false);
            this.f2889c = textView;
            viewGroup.addView(textView);
        }

        private void p(View view) {
            if (l() && view != null) {
                i(false);
                com.google.android.material.badge.a.a(this.f, view, k(view));
                this.e = view;
            }
        }

        private void q() {
            if (l()) {
                i(true);
                View view = this.e;
                if (view != null) {
                    com.google.android.material.badge.a.d(this.f, view, k(view));
                    this.e = null;
                }
            }
        }

        private void r() {
            g gVar;
            g gVar2;
            if (l()) {
                if (this.g != null) {
                    q();
                    return;
                }
                if (this.f2890d != null && (gVar2 = this.f2888b) != null && gVar2.e() != null) {
                    View view = this.e;
                    ImageView imageView = this.f2890d;
                    if (view == imageView) {
                        s(imageView);
                        return;
                    } else {
                        q();
                        p(this.f2890d);
                        return;
                    }
                }
                if (this.f2889c == null || (gVar = this.f2888b) == null || gVar.g() != 1) {
                    q();
                    return;
                }
                View view2 = this.e;
                TextView textView = this.f2889c;
                if (view2 == textView) {
                    s(textView);
                } else {
                    q();
                    p(this.f2889c);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s(View view) {
            if (l() && view == this.e) {
                com.google.android.material.badge.a.e(this.f, view, k(view));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void u(Context context) {
            int i = TabLayout.this.t;
            if (i != 0) {
                Drawable drawableD = b.a.k.a.a.d(context, i);
                this.j = drawableD;
                if (drawableD != null && drawableD.isStateful()) {
                    this.j.setState(getDrawableState());
                }
            } else {
                this.j = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            Drawable layerDrawable = gradientDrawable;
            if (TabLayout.this.o != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList colorStateListA = c.b.a.a.u.b.a(TabLayout.this.o);
                if (Build.VERSION.SDK_INT >= 21) {
                    boolean z = TabLayout.this.F;
                    GradientDrawable gradientDrawable3 = gradientDrawable;
                    if (z) {
                        gradientDrawable3 = null;
                    }
                    layerDrawable = new RippleDrawable(colorStateListA, gradientDrawable3, z ? null : gradientDrawable2);
                } else {
                    Drawable drawableR = androidx.core.graphics.drawable.a.r(gradientDrawable2);
                    androidx.core.graphics.drawable.a.o(drawableR, colorStateListA);
                    layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, drawableR});
                }
            }
            t.m0(this, layerDrawable);
            TabLayout.this.invalidate();
        }

        private void w(TextView textView, ImageView imageView) {
            g gVar = this.f2888b;
            Drawable drawableMutate = (gVar == null || gVar.e() == null) ? null : androidx.core.graphics.drawable.a.r(this.f2888b.e()).mutate();
            g gVar2 = this.f2888b;
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
            boolean z = !TextUtils.isEmpty(charSequenceH);
            if (textView != null) {
                if (z) {
                    textView.setText(charSequenceH);
                    if (this.f2888b.g == 1) {
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
                int iC = (z && imageView.getVisibility() == 0) ? (int) p.c(getContext(), 8) : 0;
                if (TabLayout.this.D) {
                    if (iC != b.g.k.g.a(marginLayoutParams)) {
                        b.g.k.g.c(marginLayoutParams, iC);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iC != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iC;
                    b.g.k.g.c(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            g gVar3 = this.f2888b;
            g0.a(this, z ? null : gVar3 != null ? gVar3.f2908d : null);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.j;
            boolean state = false;
            if (drawable != null && drawable.isStateful()) {
                state = false | this.j.setState(drawableState);
            }
            if (state) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public g getTab() {
            return this.f2888b;
        }

        void o() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            BadgeDrawable badgeDrawable = this.f;
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.f.h()));
            }
            b.g.k.c0.c cVarD0 = b.g.k.c0.c.D0(accessibilityNodeInfo);
            cVarD0.e0(c.C0042c.a(0, 1, this.f2888b.f(), 1, false, isSelected()));
            if (isSelected()) {
                cVarD0.c0(false);
                cVarD0.S(c.a.e);
            }
            cVarD0.t0("Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i = View.MeasureSpec.makeMeasureSpec(TabLayout.this.u, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.f2889c != null) {
                float f = TabLayout.this.r;
                int i3 = this.k;
                ImageView imageView = this.f2890d;
                boolean z = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f2889c;
                    if (textView != null && textView.getLineCount() > 1) {
                        f = TabLayout.this.s;
                    }
                } else {
                    i3 = 1;
                }
                float textSize = this.f2889c.getTextSize();
                int lineCount = this.f2889c.getLineCount();
                int iD = androidx.core.widget.i.d(this.f2889c);
                if (f != textSize || (iD >= 0 && i3 != iD)) {
                    if (TabLayout.this.C == 1 && f > textSize && lineCount == 1 && ((layout = this.f2889c.getLayout()) == null || h(layout, 0, f) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z = false;
                    }
                    if (z) {
                        this.f2889c.setTextSize(0, f);
                        this.f2889c.setMaxLines(i3);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.f2888b == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.f2888b.k();
            return true;
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z && Build.VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            TextView textView = this.f2889c;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.f2890d;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.g;
            if (view != null) {
                view.setSelected(z);
            }
        }

        void setTab(g gVar) {
            if (gVar != this.f2888b) {
                this.f2888b = gVar;
                t();
            }
        }

        final void t() {
            g gVar = this.f2888b;
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
                this.g = viewD;
                TextView textView = this.f2889c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f2890d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f2890d.setImageDrawable(null);
                }
                TextView textView2 = (TextView) viewD.findViewById(R.id.text1);
                this.h = textView2;
                if (textView2 != null) {
                    this.k = androidx.core.widget.i.d(textView2);
                }
                this.i = (ImageView) viewD.findViewById(R.id.icon);
            } else {
                View view = this.g;
                if (view != null) {
                    removeView(view);
                    this.g = null;
                }
                this.h = null;
                this.i = null;
            }
            if (this.g == null) {
                if (this.f2890d == null) {
                    m();
                }
                if (gVar != null && gVar.e() != null) {
                    drawableMutate = androidx.core.graphics.drawable.a.r(gVar.e()).mutate();
                }
                if (drawableMutate != null) {
                    androidx.core.graphics.drawable.a.o(drawableMutate, TabLayout.this.n);
                    PorterDuff.Mode mode = TabLayout.this.q;
                    if (mode != null) {
                        androidx.core.graphics.drawable.a.p(drawableMutate, mode);
                    }
                }
                if (this.f2889c == null) {
                    n();
                    this.k = androidx.core.widget.i.d(this.f2889c);
                }
                androidx.core.widget.i.q(this.f2889c, TabLayout.this.l);
                ColorStateList colorStateList = TabLayout.this.m;
                if (colorStateList != null) {
                    this.f2889c.setTextColor(colorStateList);
                }
                w(this.f2889c, this.f2890d);
                r();
                g(this.f2890d);
                g(this.f2889c);
            } else {
                TextView textView3 = this.h;
                if (textView3 != null || this.i != null) {
                    w(textView3, this.i);
                }
            }
            if (gVar != null && !TextUtils.isEmpty(gVar.f2908d)) {
                setContentDescription(gVar.f2908d);
            }
            setSelected(gVar != null && gVar.i());
        }

        final void v() {
            setOrientation(!TabLayout.this.D ? 1 : 0);
            TextView textView = this.h;
            if (textView == null && this.i == null) {
                w(this.f2889c, this.f2890d);
            } else {
                w(textView, this.i);
            }
        }
    }

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
        private boolean f2894a;

        b() {
        }

        void a(boolean z) {
            this.f2894a = z;
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void b(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.K == viewPager) {
                tabLayout.E(aVar2, this.f2894a);
            }
        }
    }

    @Deprecated
    public interface c<T extends g> {
        void a(T t);

        void b(T t);

        void c(T t);
    }

    public interface d extends c<g> {
    }

    private class e extends DataSetObserver {
        e() {
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

    class f extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2897b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Paint f2898c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final GradientDrawable f2899d;
        int e;
        float f;
        private int g;
        int h;
        int i;
        ValueAnimator j;
        private int k;
        private int l;

        class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f2900a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f2901b;

            a(int i, int i2) {
                this.f2900a = i;
                this.f2901b = i2;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                f fVar = f.this;
                fVar.f(c.b.a.a.m.a.b(fVar.k, this.f2900a, animatedFraction), c.b.a.a.m.a.b(f.this.l, this.f2901b, animatedFraction));
            }
        }

        class b extends AnimatorListenerAdapter {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f2903a;

            b(int i) {
                this.f2903a = i;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                f fVar = f.this;
                fVar.e = this.f2903a;
                fVar.f = 0.0f;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                f.this.e = this.f2903a;
            }
        }

        f(Context context) {
            super(context);
            this.e = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.k = -1;
            this.l = -1;
            setWillNotDraw(false);
            this.f2898c = new Paint();
            this.f2899d = new GradientDrawable();
        }

        private void d(TabView tabView, RectF rectF) {
            int contentWidth = tabView.getContentWidth();
            int iC = (int) p.c(getContext(), 24);
            if (contentWidth < iC) {
                contentWidth = iC;
            }
            int left = (tabView.getLeft() + tabView.getRight()) / 2;
            int i = contentWidth / 2;
            rectF.set(left - i, 0.0f, left + i, 0.0f);
        }

        private void j() {
            int left;
            int right;
            View childAt = getChildAt(this.e);
            if (childAt == null || childAt.getWidth() <= 0) {
                left = -1;
                right = -1;
            } else {
                left = childAt.getLeft();
                right = childAt.getRight();
                TabLayout tabLayout = TabLayout.this;
                if (!tabLayout.E && (childAt instanceof TabView)) {
                    d((TabView) childAt, tabLayout.f);
                    left = (int) TabLayout.this.f.left;
                    right = (int) TabLayout.this.f.right;
                }
                if (this.f > 0.0f && this.e < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.e + 1);
                    int left2 = childAt2.getLeft();
                    int right2 = childAt2.getRight();
                    TabLayout tabLayout2 = TabLayout.this;
                    if (!tabLayout2.E && (childAt2 instanceof TabView)) {
                        d((TabView) childAt2, tabLayout2.f);
                        left2 = (int) TabLayout.this.f.left;
                        right2 = (int) TabLayout.this.f.right;
                    }
                    float f = this.f;
                    left = (int) ((left2 * f) + ((1.0f - f) * left));
                    right = (int) ((right2 * f) + ((1.0f - f) * right));
                }
            }
            f(left, right);
        }

        private void k(boolean z, int i, int i2) {
            View childAt = getChildAt(i);
            if (childAt == null) {
                j();
                return;
            }
            int left = childAt.getLeft();
            int right = childAt.getRight();
            TabLayout tabLayout = TabLayout.this;
            if (!tabLayout.E && (childAt instanceof TabView)) {
                d((TabView) childAt, tabLayout.f);
                left = (int) TabLayout.this.f.left;
                right = (int) TabLayout.this.f.right;
            }
            int i3 = this.h;
            int i4 = this.i;
            if (i3 == left && i4 == right) {
                return;
            }
            if (z) {
                this.k = i3;
                this.l = i4;
            }
            a aVar = new a(left, right);
            if (!z) {
                this.j.removeAllUpdateListeners();
                this.j.addUpdateListener(aVar);
                return;
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            this.j = valueAnimator;
            valueAnimator.setInterpolator(c.b.a.a.m.a.f2132b);
            valueAnimator.setDuration(i2);
            valueAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimator.addUpdateListener(aVar);
            valueAnimator.addListener(new b(i));
            valueAnimator.start();
        }

        void c(int i, int i2) {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
            k(true, i, i2);
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            Drawable drawable = TabLayout.this.p;
            int height = 0;
            int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
            int i = this.f2897b;
            if (i >= 0) {
                intrinsicHeight = i;
            }
            int i2 = TabLayout.this.B;
            if (i2 == 0) {
                height = getHeight() - intrinsicHeight;
                intrinsicHeight = getHeight();
            } else if (i2 == 1) {
                height = (getHeight() - intrinsicHeight) / 2;
                intrinsicHeight = (getHeight() + intrinsicHeight) / 2;
            } else if (i2 != 2) {
                intrinsicHeight = i2 != 3 ? 0 : getHeight();
            }
            int i3 = this.h;
            if (i3 >= 0 && this.i > i3) {
                Drawable drawable2 = TabLayout.this.p;
                if (drawable2 == null) {
                    drawable2 = this.f2899d;
                }
                Drawable drawableMutate = androidx.core.graphics.drawable.a.r(drawable2).mutate();
                drawableMutate.setBounds(this.h, height, this.i, intrinsicHeight);
                Paint paint = this.f2898c;
                if (paint != null) {
                    if (Build.VERSION.SDK_INT == 21) {
                        drawableMutate.setColorFilter(paint.getColor(), PorterDuff.Mode.SRC_IN);
                    } else {
                        androidx.core.graphics.drawable.a.n(drawableMutate, paint.getColor());
                    }
                }
                drawableMutate.draw(canvas);
            }
            super.draw(canvas);
        }

        boolean e() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        void f(int i, int i2) {
            if (i == this.h && i2 == this.i) {
                return;
            }
            this.h = i;
            this.i = i2;
            t.a0(this);
        }

        void g(int i, float f) {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
            this.e = i;
            this.f = f;
            j();
        }

        void h(int i) {
            if (this.f2898c.getColor() != i) {
                this.f2898c.setColor(i);
                t.a0(this);
            }
        }

        void i(int i) {
            if (this.f2897b != i) {
                this.f2897b = i;
                t.a0(this);
            }
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                j();
            } else {
                k(false, this.e, -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            boolean z = true;
            if (tabLayout.z == 1 || tabLayout.C == 2) {
                int childCount = getChildCount();
                int iMax = 0;
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = getChildAt(i3);
                    if (childAt.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount <= getMeasuredWidth() - (((int) p.c(getContext(), 16)) * 2)) {
                    boolean z2 = false;
                    for (int i4 = 0; i4 < childCount; i4++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i4).getLayoutParams();
                        if (layoutParams.width != iMax || layoutParams.weight != 0.0f) {
                            layoutParams.width = iMax;
                            layoutParams.weight = 0.0f;
                            z2 = true;
                        }
                    }
                    z = z2;
                } else {
                    TabLayout tabLayout2 = TabLayout.this;
                    tabLayout2.z = 0;
                    tabLayout2.L(false);
                }
                if (z) {
                    super.onMeasure(i, i2);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i) {
            super.onRtlPropertiesChanged(i);
            if (Build.VERSION.SDK_INT >= 23 || this.g == i) {
                return;
            }
            requestLayout();
            this.g = i;
        }
    }

    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Object f2905a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Drawable f2906b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CharSequence f2907c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private CharSequence f2908d;
        private View f;
        public TabLayout h;
        public TabView i;
        private int e = -1;
        private int g = 1;

        public View d() {
            return this.f;
        }

        public Drawable e() {
            return this.f2906b;
        }

        public int f() {
            return this.e;
        }

        public int g() {
            return this.g;
        }

        public CharSequence h() {
            return this.f2907c;
        }

        public boolean i() {
            TabLayout tabLayout = this.h;
            if (tabLayout != null) {
                return tabLayout.getSelectedTabPosition() == this.e;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        void j() {
            this.h = null;
            this.i = null;
            this.f2905a = null;
            this.f2906b = null;
            this.f2907c = null;
            this.f2908d = null;
            this.e = -1;
            this.f = null;
        }

        public void k() {
            TabLayout tabLayout = this.h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.C(this);
        }

        public g l(CharSequence charSequence) {
            this.f2908d = charSequence;
            r();
            return this;
        }

        public g m(int i) {
            return n(LayoutInflater.from(this.i.getContext()).inflate(i, (ViewGroup) this.i, false));
        }

        public g n(View view) {
            this.f = view;
            r();
            return this;
        }

        public g o(Drawable drawable) {
            this.f2906b = drawable;
            TabLayout tabLayout = this.h;
            if (tabLayout.z == 1 || tabLayout.C == 2) {
                tabLayout.L(true);
            }
            r();
            if (com.google.android.material.badge.a.f2564a && this.i.l() && this.i.f.isVisible()) {
                this.i.invalidate();
            }
            return this;
        }

        void p(int i) {
            this.e = i;
        }

        public g q(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.f2908d) && !TextUtils.isEmpty(charSequence)) {
                this.i.setContentDescription(charSequence);
            }
            this.f2907c = charSequence;
            r();
            return this;
        }

        void r() {
            TabView tabView = this.i;
            if (tabView != null) {
                tabView.t();
            }
        }
    }

    public static class h implements ViewPager.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<TabLayout> f2909a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2910b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2911c;

        public h(TabLayout tabLayout) {
            this.f2909a = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i, float f, int i2) {
            TabLayout tabLayout = this.f2909a.get();
            if (tabLayout != null) {
                int i3 = this.f2911c;
                tabLayout.G(i, f, i3 != 2 || this.f2910b == 1, (i3 == 2 && this.f2910b == 0) ? false : true);
            }
        }

        void b() {
            this.f2911c = 0;
            this.f2910b = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i) {
            this.f2910b = this.f2911c;
            this.f2911c = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i) {
            TabLayout tabLayout = this.f2909a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i || i >= tabLayout.getTabCount()) {
                return;
            }
            int i2 = this.f2911c;
            tabLayout.D(tabLayout.v(i), i2 == 0 || (i2 == 2 && this.f2910b == 0));
        }
    }

    public static class i implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ViewPager f2912a;

        public i(ViewPager viewPager) {
            this.f2912a = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(g gVar) {
            this.f2912a.setCurrentItem(gVar.f());
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(g gVar) {
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private void B(int i2) {
        TabView tabView = (TabView) this.g.getChildAt(i2);
        this.g.removeViewAt(i2);
        if (tabView != null) {
            tabView.o();
            this.Q.a(tabView);
        }
        requestLayout();
    }

    private void I(ViewPager viewPager, boolean z, boolean z2) {
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
            androidx.viewpager.widget.a adapter = viewPager.getAdapter();
            if (adapter != null) {
                E(adapter, z);
            }
            if (this.O == null) {
                this.O = new b();
            }
            this.O.a(z);
            viewPager.b(this.O);
            F(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.K = null;
            E(null, false);
        }
        this.P = z2;
    }

    private void J() {
        int size = this.f2887d.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f2887d.get(i2).r();
        }
    }

    private void K(LinearLayout.LayoutParams layoutParams) {
        if (this.C == 1 && this.z == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    private void f(TabItem tabItem) {
        g gVarW = w();
        CharSequence charSequence = tabItem.f2882b;
        if (charSequence != null) {
            gVarW.q(charSequence);
        }
        Drawable drawable = tabItem.f2883c;
        if (drawable != null) {
            gVarW.o(drawable);
        }
        int i2 = tabItem.f2884d;
        if (i2 != 0) {
            gVarW.m(i2);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            gVarW.l(tabItem.getContentDescription());
        }
        c(gVarW);
    }

    private void g(g gVar) {
        TabView tabView = gVar.i;
        tabView.setSelected(false);
        tabView.setActivated(false);
        this.g.addView(tabView, gVar.f(), o());
    }

    private int getDefaultHeight() {
        int size = this.f2887d.size();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                g gVar = this.f2887d.get(i2);
                if (gVar != null && gVar.e() != null && !TextUtils.isEmpty(gVar.h())) {
                    z = true;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        return (!z || this.D) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i2 = this.v;
        if (i2 != -1) {
            return i2;
        }
        int i3 = this.C;
        if (i3 == 0 || i3 == 2) {
            return this.x;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.g.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void h(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        f((TabItem) view);
    }

    private void i(int i2) {
        if (i2 == -1) {
            return;
        }
        if (getWindowToken() == null || !t.P(this) || this.g.e()) {
            F(i2, 0.0f, true);
            return;
        }
        int scrollX = getScrollX();
        int iL = l(i2, 0.0f);
        if (scrollX != iL) {
            u();
            this.J.setIntValues(scrollX, iL);
            this.J.start();
        }
        this.g.c(i2, this.A);
    }

    private void j(int i2) {
        if (i2 == 0) {
            Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        } else if (i2 == 1) {
            this.g.setGravity(1);
            return;
        } else if (i2 != 2) {
            return;
        }
        this.g.setGravity(8388611);
    }

    private void k() {
        int i2 = this.C;
        t.w0(this.g, (i2 == 0 || i2 == 2) ? Math.max(0, this.y - this.h) : 0, 0, 0, 0);
        int i3 = this.C;
        if (i3 == 0) {
            j(this.z);
        } else if (i3 == 1 || i3 == 2) {
            if (this.z == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            this.g.setGravity(1);
        }
        L(true);
    }

    private int l(int i2, float f2) {
        int i3 = this.C;
        if (i3 != 0 && i3 != 2) {
            return 0;
        }
        View childAt = this.g.getChildAt(i2);
        int i4 = i2 + 1;
        View childAt2 = i4 < this.g.getChildCount() ? this.g.getChildAt(i4) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i5 = (int) ((width + width2) * 0.5f * f2);
        return t.z(this) == 0 ? left + i5 : left - i5;
    }

    private void m(g gVar, int i2) {
        gVar.p(i2);
        this.f2887d.add(i2, gVar);
        int size = this.f2887d.size();
        while (true) {
            i2++;
            if (i2 >= size) {
                return;
            } else {
                this.f2887d.get(i2).p(i2);
            }
        }
    }

    private static ColorStateList n(int i2, int i3) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i3, i2});
    }

    private LinearLayout.LayoutParams o() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        K(layoutParams);
        return layoutParams;
    }

    private TabView q(g gVar) {
        b.g.j.e<TabView> eVar = this.Q;
        TabView tabViewB = eVar != null ? eVar.b() : null;
        if (tabViewB == null) {
            tabViewB = new TabView(getContext());
        }
        tabViewB.setTab(gVar);
        tabViewB.setFocusable(true);
        tabViewB.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(gVar.f2908d)) {
            tabViewB.setContentDescription(gVar.f2907c);
        } else {
            tabViewB.setContentDescription(gVar.f2908d);
        }
        return tabViewB;
    }

    private void r(g gVar) {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            this.H.get(size).c(gVar);
        }
    }

    private void s(g gVar) {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            this.H.get(size).b(gVar);
        }
    }

    private void setSelectedTabView(int i2) {
        int childCount = this.g.getChildCount();
        if (i2 < childCount) {
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = this.g.getChildAt(i3);
                boolean z = true;
                childAt.setSelected(i3 == i2);
                if (i3 != i2) {
                    z = false;
                }
                childAt.setActivated(z);
                i3++;
            }
        }
    }

    private void t(g gVar) {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            this.H.get(size).a(gVar);
        }
    }

    private void u() {
        if (this.J == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.J = valueAnimator;
            valueAnimator.setInterpolator(c.b.a.a.m.a.f2132b);
            this.J.setDuration(this.A);
            this.J.addUpdateListener(new a());
        }
    }

    @Deprecated
    public void A(c cVar) {
        this.H.remove(cVar);
    }

    public void C(g gVar) {
        D(gVar, true);
    }

    public void D(g gVar, boolean z) {
        g gVar2 = this.e;
        if (gVar2 == gVar) {
            if (gVar2 != null) {
                r(gVar);
                i(gVar.f());
                return;
            }
            return;
        }
        int iF = gVar != null ? gVar.f() : -1;
        if (z) {
            if ((gVar2 == null || gVar2.f() == -1) && iF != -1) {
                F(iF, 0.0f, true);
            } else {
                i(iF);
            }
            if (iF != -1) {
                setSelectedTabView(iF);
            }
        }
        this.e = gVar;
        if (gVar2 != null) {
            t(gVar2);
        }
        if (gVar != null) {
            s(gVar);
        }
    }

    void E(androidx.viewpager.widget.a aVar, boolean z) {
        DataSetObserver dataSetObserver;
        androidx.viewpager.widget.a aVar2 = this.L;
        if (aVar2 != null && (dataSetObserver = this.M) != null) {
            aVar2.t(dataSetObserver);
        }
        this.L = aVar;
        if (z && aVar != null) {
            if (this.M == null) {
                this.M = new e();
            }
            aVar.l(this.M);
        }
        x();
    }

    public void F(int i2, float f2, boolean z) {
        G(i2, f2, z, true);
    }

    public void G(int i2, float f2, boolean z, boolean z2) {
        int iRound = Math.round(i2 + f2);
        if (iRound < 0 || iRound >= this.g.getChildCount()) {
            return;
        }
        if (z2) {
            this.g.g(i2, f2);
        }
        ValueAnimator valueAnimator = this.J;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.J.cancel();
        }
        scrollTo(l(i2, f2), 0);
        if (z) {
            setSelectedTabView(iRound);
        }
    }

    public void H(ViewPager viewPager, boolean z) {
        I(viewPager, z, false);
    }

    void L(boolean z) {
        for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
            View childAt = this.g.getChildAt(i2);
            childAt.setMinimumWidth(getTabMinWidth());
            K((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
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
        e(gVar, this.f2887d.isEmpty());
    }

    public void d(g gVar, int i2, boolean z) {
        if (gVar.h != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        m(gVar, i2);
        g(gVar);
        if (z) {
            gVar.k();
        }
    }

    public void e(g gVar, boolean z) {
        d(gVar, this.f2887d.size(), z);
    }

    public int getSelectedTabPosition() {
        g gVar = this.e;
        if (gVar != null) {
            return gVar.f();
        }
        return -1;
    }

    public int getTabCount() {
        return this.f2887d.size();
    }

    public int getTabGravity() {
        return this.z;
    }

    public ColorStateList getTabIconTint() {
        return this.n;
    }

    public int getTabIndicatorGravity() {
        return this.B;
    }

    int getTabMaxWidth() {
        return this.u;
    }

    public int getTabMode() {
        return this.C;
    }

    public ColorStateList getTabRippleColor() {
        return this.o;
    }

    public Drawable getTabSelectedIndicator() {
        return this.p;
    }

    public ColorStateList getTabTextColors() {
        return this.m;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.b.a.a.w.i.e(this);
        if (this.K == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                I((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.P) {
            setupWithViewPager(null);
            this.P = false;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
            View childAt = this.g.getChildAt(i2);
            if (childAt instanceof TabView) {
                ((TabView) childAt).drawBackground(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        b.g.k.c0.c.D0(accessibilityNodeInfo).d0(c.b.b(1, getTabCount(), false, 1));
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
            float r0 = com.google.android.material.internal.p.c(r0, r1)
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
            int r1 = r6.w
            if (r1 <= 0) goto L50
            goto L5d
        L50:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = com.google.android.material.internal.p.c(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L5d:
            r6.u = r1
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

    protected g p() {
        g gVarB = f2886c.b();
        return gVarB == null ? new g() : gVarB;
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        c.b.a.a.w.i.d(this, f2);
    }

    public void setInlineLabel(boolean z) {
        if (this.D != z) {
            this.D = z;
            for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
                View childAt = this.g.getChildAt(i2);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).v();
                }
            }
            k();
        }
    }

    public void setInlineLabelResource(int i2) {
        setInlineLabel(getResources().getBoolean(i2));
    }

    @Deprecated
    public void setOnTabSelectedListener(d dVar) {
        setOnTabSelectedListener((c) dVar);
    }

    void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        u();
        this.J.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.p != drawable) {
            this.p = drawable;
            t.a0(this.g);
        }
    }

    public void setSelectedTabIndicatorColor(int i2) {
        this.g.h(i2);
    }

    public void setSelectedTabIndicatorGravity(int i2) {
        if (this.B != i2) {
            this.B = i2;
            t.a0(this.g);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i2) {
        this.g.i(i2);
    }

    public void setTabGravity(int i2) {
        if (this.z != i2) {
            this.z = i2;
            k();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            J();
        }
    }

    public void setTabIconTintResource(int i2) {
        setTabIconTint(b.a.k.a.a.c(getContext(), i2));
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.E = z;
        t.a0(this.g);
    }

    public void setTabMode(int i2) {
        if (i2 != this.C) {
            this.C = i2;
            k();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.o != colorStateList) {
            this.o = colorStateList;
            for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
                View childAt = this.g.getChildAt(i2);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).u(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int i2) {
        setTabRippleColor(b.a.k.a.a.c(getContext(), i2));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            J();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(androidx.viewpager.widget.a aVar) {
        E(aVar, false);
    }

    public void setUnboundedRipple(boolean z) {
        if (this.F != z) {
            this.F = z;
            for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
                View childAt = this.g.getChildAt(i2);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).u(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i2) {
        setUnboundedRipple(getResources().getBoolean(i2));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        H(viewPager, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public g v(int i2) {
        if (i2 < 0 || i2 >= getTabCount()) {
            return null;
        }
        return this.f2887d.get(i2);
    }

    public g w() {
        g gVarP = p();
        gVarP.h = this;
        gVarP.i = q(gVarP);
        return gVarP;
    }

    void x() {
        int currentItem;
        z();
        androidx.viewpager.widget.a aVar = this.L;
        if (aVar != null) {
            int iE = aVar.e();
            for (int i2 = 0; i2 < iE; i2++) {
                e(w().q(this.L.g(i2)), false);
            }
            ViewPager viewPager = this.K;
            if (viewPager == null || iE <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            C(v(currentItem));
        }
    }

    protected boolean y(g gVar) {
        return f2886c.a(gVar);
    }

    public void z() {
        for (int childCount = this.g.getChildCount() - 1; childCount >= 0; childCount--) {
            B(childCount);
        }
        Iterator<g> it = this.f2887d.iterator();
        while (it.hasNext()) {
            g next = it.next();
            it.remove();
            next.j();
            y(next);
        }
        this.e = null;
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.b.a.a.b.K);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2) {
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
    public TabLayout(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f2885b;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f2887d = new ArrayList<>();
        this.f = new RectF();
        this.u = Integer.MAX_VALUE;
        this.H = new ArrayList<>();
        this.Q = new b.g.j.f(12);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        f fVar = new f(context2);
        this.g = fVar;
        super.addView(fVar, 0, new FrameLayout.LayoutParams(-2, -1));
        int[] iArr = l.S5;
        int i4 = l.p6;
        TypedArray typedArrayH = com.google.android.material.internal.k.h(context2, attributeSet, iArr, i2, i3, i4);
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            c.b.a.a.w.h hVar = new c.b.a.a.w.h();
            hVar.X(ColorStateList.valueOf(colorDrawable.getColor()));
            hVar.N(context2);
            hVar.W(t.u(this));
            t.m0(this, hVar);
        }
        fVar.i(typedArrayH.getDimensionPixelSize(l.d6, -1));
        fVar.h(typedArrayH.getColor(l.a6, 0));
        setSelectedTabIndicator(c.b.a.a.t.c.d(context2, typedArrayH, l.Y5));
        setSelectedTabIndicatorGravity(typedArrayH.getInt(l.c6, 0));
        setTabIndicatorFullWidth(typedArrayH.getBoolean(l.b6, true));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(l.i6, 0);
        this.k = dimensionPixelSize;
        this.j = dimensionPixelSize;
        this.i = dimensionPixelSize;
        this.h = dimensionPixelSize;
        this.h = typedArrayH.getDimensionPixelSize(l.l6, dimensionPixelSize);
        this.i = typedArrayH.getDimensionPixelSize(l.m6, this.i);
        this.j = typedArrayH.getDimensionPixelSize(l.k6, this.j);
        this.k = typedArrayH.getDimensionPixelSize(l.j6, this.k);
        int resourceId = typedArrayH.getResourceId(i4, k.f2126d);
        this.l = resourceId;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, j.Z2);
        try {
            this.r = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.a3, 0);
            this.m = c.b.a.a.t.c.a(context2, typedArrayObtainStyledAttributes, j.d3);
            typedArrayObtainStyledAttributes.recycle();
            int i5 = l.q6;
            if (typedArrayH.hasValue(i5)) {
                this.m = c.b.a.a.t.c.a(context2, typedArrayH, i5);
            }
            int i6 = l.o6;
            if (typedArrayH.hasValue(i6)) {
                this.m = n(this.m.getDefaultColor(), typedArrayH.getColor(i6, 0));
            }
            this.n = c.b.a.a.t.c.a(context2, typedArrayH, l.W5);
            this.q = p.i(typedArrayH.getInt(l.X5, -1), null);
            this.o = c.b.a.a.t.c.a(context2, typedArrayH, l.n6);
            this.A = typedArrayH.getInt(l.Z5, 300);
            this.v = typedArrayH.getDimensionPixelSize(l.g6, -1);
            this.w = typedArrayH.getDimensionPixelSize(l.f6, -1);
            this.t = typedArrayH.getResourceId(l.T5, 0);
            this.y = typedArrayH.getDimensionPixelSize(l.U5, 0);
            this.C = typedArrayH.getInt(l.h6, 1);
            this.z = typedArrayH.getInt(l.V5, 0);
            this.D = typedArrayH.getBoolean(l.e6, false);
            this.F = typedArrayH.getBoolean(l.r6, false);
            typedArrayH.recycle();
            Resources resources = getResources();
            this.s = resources.getDimensionPixelSize(c.b.a.a.d.r);
            this.x = resources.getDimensionPixelSize(c.b.a.a.d.q);
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
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        h(view);
    }

    public void setSelectedTabIndicator(int i2) {
        if (i2 != 0) {
            setSelectedTabIndicator(b.a.k.a.a.d(getContext(), i2));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }
}
