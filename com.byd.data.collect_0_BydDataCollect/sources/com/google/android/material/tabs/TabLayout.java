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
import androidx.appcompat.widget.h0;
import androidx.viewpager.widget.ViewPager;
import b.a.j;
import b.g.k.c0.c;
import b.g.k.r;
import b.g.k.t;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.q;
import d.b.a.a.k;
import d.b.a.a.l;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class TabLayout extends HorizontalScrollView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f4244b = k.Widget_Design_TabLayout;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final b.g.j.e<g> f4245c = new b.g.j.g(16);
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
    private final ArrayList<g> f4246d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private g f4247e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final RectF f4248f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final f f4249g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f4250h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f4251i;
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
        private g f4252b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private TextView f4253c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ImageView f4254d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private View f4255e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private BadgeDrawable f4256f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private View f4257g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private TextView f4258h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private ImageView f4259i;
        private Drawable j;
        private int k;

        class a implements View.OnLayoutChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ View f4260a;

            a(View view) {
                this.f4260a = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                if (this.f4260a.getVisibility() == 0) {
                    TabView.this.s(this.f4260a);
                }
            }
        }

        public TabView(Context context) {
            super(context);
            this.k = 2;
            u(context);
            t.w0(this, TabLayout.this.f4250h, TabLayout.this.f4251i, TabLayout.this.j, TabLayout.this.k);
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
            return this.f4256f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getContentWidth() {
            View[] viewArr = {this.f4253c, this.f4254d, this.f4257g};
            int iMax = 0;
            int iMin = 0;
            boolean z = false;
            for (int i2 = 0; i2 < 3; i2++) {
                View view = viewArr[i2];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z ? Math.max(iMax, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return iMax - iMin;
        }

        private BadgeDrawable getOrCreateBadge() {
            if (this.f4256f == null) {
                this.f4256f = BadgeDrawable.c(getContext());
            }
            r();
            BadgeDrawable badgeDrawable = this.f4256f;
            if (badgeDrawable != null) {
                return badgeDrawable;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        private float h(Layout layout, int i2, float f2) {
            return layout.getLineWidth(i2) * (f2 / layout.getPaint().getTextSize());
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
            if ((view == this.f4254d || view == this.f4253c) && com.google.android.material.badge.a.f3714a) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean l() {
            return this.f4256f != null;
        }

        private void m() {
            ViewGroup viewGroup;
            if (com.google.android.material.badge.a.f3714a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ, 0);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(d.b.a.a.h.design_layout_tab_icon, viewGroup, false);
            this.f4254d = imageView;
            viewGroup.addView(imageView, 0);
        }

        private void n() {
            ViewGroup viewGroup;
            if (com.google.android.material.badge.a.f3714a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(d.b.a.a.h.design_layout_tab_text, viewGroup, false);
            this.f4253c = textView;
            viewGroup.addView(textView);
        }

        private void p(View view) {
            if (l() && view != null) {
                i(false);
                com.google.android.material.badge.a.a(this.f4256f, view, k(view));
                this.f4255e = view;
            }
        }

        private void q() {
            if (l()) {
                i(true);
                View view = this.f4255e;
                if (view != null) {
                    com.google.android.material.badge.a.d(this.f4256f, view, k(view));
                    this.f4255e = null;
                }
            }
        }

        private void r() {
            g gVar;
            g gVar2;
            if (l()) {
                if (this.f4257g != null) {
                    q();
                    return;
                }
                if (this.f4254d != null && (gVar2 = this.f4252b) != null && gVar2.e() != null) {
                    View view = this.f4255e;
                    ImageView imageView = this.f4254d;
                    if (view == imageView) {
                        s(imageView);
                        return;
                    } else {
                        q();
                        p(this.f4254d);
                        return;
                    }
                }
                if (this.f4253c == null || (gVar = this.f4252b) == null || gVar.g() != 1) {
                    q();
                    return;
                }
                View view2 = this.f4255e;
                TextView textView = this.f4253c;
                if (view2 == textView) {
                    s(textView);
                } else {
                    q();
                    p(this.f4253c);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s(View view) {
            if (l() && view == this.f4255e) {
                com.google.android.material.badge.a.e(this.f4256f, view, k(view));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void u(Context context) {
            int i2 = TabLayout.this.t;
            if (i2 != 0) {
                Drawable drawableD = b.a.k.a.a.d(context, i2);
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
                ColorStateList colorStateListA = d.b.a.a.v.b.a(TabLayout.this.o);
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
            g gVar = this.f4252b;
            Drawable drawableMutate = (gVar == null || gVar.e() == null) ? null : androidx.core.graphics.drawable.a.r(this.f4252b.e()).mutate();
            g gVar2 = this.f4252b;
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
                    if (this.f4252b.f4285g == 1) {
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
                int iC = (z && imageView.getVisibility() == 0) ? (int) q.c(getContext(), 8) : 0;
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
            g gVar3 = this.f4252b;
            h0.a(this, z ? null : gVar3 != null ? gVar3.f4282d : null);
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
            return this.f4252b;
        }

        void o() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            BadgeDrawable badgeDrawable = this.f4256f;
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.f4256f.h()));
            }
            b.g.k.c0.c cVarC0 = b.g.k.c0.c.C0(accessibilityNodeInfo);
            cVarC0.e0(c.C0047c.a(0, 1, this.f4252b.f(), 1, false, isSelected()));
            if (isSelected()) {
                cVarC0.c0(false);
                cVarC0.S(c.a.f2700e);
            }
            cVarC0.s0("Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i2, int i3) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i2 = View.MeasureSpec.makeMeasureSpec(TabLayout.this.u, Integer.MIN_VALUE);
            }
            super.onMeasure(i2, i3);
            if (this.f4253c != null) {
                float f2 = TabLayout.this.r;
                int i4 = this.k;
                ImageView imageView = this.f4254d;
                boolean z = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f4253c;
                    if (textView != null && textView.getLineCount() > 1) {
                        f2 = TabLayout.this.s;
                    }
                } else {
                    i4 = 1;
                }
                float textSize = this.f4253c.getTextSize();
                int lineCount = this.f4253c.getLineCount();
                int iD = androidx.core.widget.i.d(this.f4253c);
                if (f2 != textSize || (iD >= 0 && i4 != iD)) {
                    if (TabLayout.this.C == 1 && f2 > textSize && lineCount == 1 && ((layout = this.f4253c.getLayout()) == null || h(layout, 0, f2) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z = false;
                    }
                    if (z) {
                        this.f4253c.setTextSize(0, f2);
                        this.f4253c.setMaxLines(i4);
                        super.onMeasure(i2, i3);
                    }
                }
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.f4252b == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.f4252b.k();
            return true;
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z && Build.VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            TextView textView = this.f4253c;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.f4254d;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.f4257g;
            if (view != null) {
                view.setSelected(z);
            }
        }

        void setTab(g gVar) {
            if (gVar != this.f4252b) {
                this.f4252b = gVar;
                t();
            }
        }

        final void t() {
            g gVar = this.f4252b;
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
                this.f4257g = viewD;
                TextView textView = this.f4253c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f4254d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f4254d.setImageDrawable(null);
                }
                TextView textView2 = (TextView) viewD.findViewById(R.id.text1);
                this.f4258h = textView2;
                if (textView2 != null) {
                    this.k = androidx.core.widget.i.d(textView2);
                }
                this.f4259i = (ImageView) viewD.findViewById(R.id.icon);
            } else {
                View view = this.f4257g;
                if (view != null) {
                    removeView(view);
                    this.f4257g = null;
                }
                this.f4258h = null;
                this.f4259i = null;
            }
            if (this.f4257g == null) {
                if (this.f4254d == null) {
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
                if (this.f4253c == null) {
                    n();
                    this.k = androidx.core.widget.i.d(this.f4253c);
                }
                androidx.core.widget.i.q(this.f4253c, TabLayout.this.l);
                ColorStateList colorStateList = TabLayout.this.m;
                if (colorStateList != null) {
                    this.f4253c.setTextColor(colorStateList);
                }
                w(this.f4253c, this.f4254d);
                r();
                g(this.f4254d);
                g(this.f4253c);
            } else {
                TextView textView3 = this.f4258h;
                if (textView3 != null || this.f4259i != null) {
                    w(textView3, this.f4259i);
                }
            }
            if (gVar != null && !TextUtils.isEmpty(gVar.f4282d)) {
                setContentDescription(gVar.f4282d);
            }
            setSelected(gVar != null && gVar.i());
        }

        final void v() {
            setOrientation(!TabLayout.this.D ? 1 : 0);
            TextView textView = this.f4258h;
            if (textView == null && this.f4259i == null) {
                w(this.f4253c, this.f4254d);
            } else {
                w(textView, this.f4259i);
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
        private boolean f4263a;

        b() {
        }

        void a(boolean z) {
            this.f4263a = z;
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void b(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.K == viewPager) {
                tabLayout.E(aVar2, this.f4263a);
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
        private int f4266b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Paint f4267c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final GradientDrawable f4268d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f4269e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        float f4270f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f4271g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f4272h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        int f4273i;
        ValueAnimator j;
        private int k;
        private int l;

        class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f4274a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f4275b;

            a(int i2, int i3) {
                this.f4274a = i2;
                this.f4275b = i3;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                f fVar = f.this;
                fVar.f(d.b.a.a.m.a.b(fVar.k, this.f4274a, animatedFraction), d.b.a.a.m.a.b(f.this.l, this.f4275b, animatedFraction));
            }
        }

        class b extends AnimatorListenerAdapter {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f4277a;

            b(int i2) {
                this.f4277a = i2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                f fVar = f.this;
                fVar.f4269e = this.f4277a;
                fVar.f4270f = 0.0f;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                f.this.f4269e = this.f4277a;
            }
        }

        f(Context context) {
            super(context);
            this.f4269e = -1;
            this.f4271g = -1;
            this.f4272h = -1;
            this.f4273i = -1;
            this.k = -1;
            this.l = -1;
            setWillNotDraw(false);
            this.f4267c = new Paint();
            this.f4268d = new GradientDrawable();
        }

        private void d(TabView tabView, RectF rectF) {
            int contentWidth = tabView.getContentWidth();
            int iC = (int) q.c(getContext(), 24);
            if (contentWidth < iC) {
                contentWidth = iC;
            }
            int left = (tabView.getLeft() + tabView.getRight()) / 2;
            int i2 = contentWidth / 2;
            rectF.set(left - i2, 0.0f, left + i2, 0.0f);
        }

        private void j() {
            int left;
            int right;
            View childAt = getChildAt(this.f4269e);
            if (childAt == null || childAt.getWidth() <= 0) {
                left = -1;
                right = -1;
            } else {
                left = childAt.getLeft();
                right = childAt.getRight();
                TabLayout tabLayout = TabLayout.this;
                if (!tabLayout.E && (childAt instanceof TabView)) {
                    d((TabView) childAt, tabLayout.f4248f);
                    left = (int) TabLayout.this.f4248f.left;
                    right = (int) TabLayout.this.f4248f.right;
                }
                if (this.f4270f > 0.0f && this.f4269e < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.f4269e + 1);
                    int left2 = childAt2.getLeft();
                    int right2 = childAt2.getRight();
                    TabLayout tabLayout2 = TabLayout.this;
                    if (!tabLayout2.E && (childAt2 instanceof TabView)) {
                        d((TabView) childAt2, tabLayout2.f4248f);
                        left2 = (int) TabLayout.this.f4248f.left;
                        right2 = (int) TabLayout.this.f4248f.right;
                    }
                    float f2 = this.f4270f;
                    left = (int) ((left2 * f2) + ((1.0f - f2) * left));
                    right = (int) ((right2 * f2) + ((1.0f - f2) * right));
                }
            }
            f(left, right);
        }

        private void k(boolean z, int i2, int i3) {
            View childAt = getChildAt(i2);
            if (childAt == null) {
                j();
                return;
            }
            int left = childAt.getLeft();
            int right = childAt.getRight();
            TabLayout tabLayout = TabLayout.this;
            if (!tabLayout.E && (childAt instanceof TabView)) {
                d((TabView) childAt, tabLayout.f4248f);
                left = (int) TabLayout.this.f4248f.left;
                right = (int) TabLayout.this.f4248f.right;
            }
            int i4 = this.f4272h;
            int i5 = this.f4273i;
            if (i4 == left && i5 == right) {
                return;
            }
            if (z) {
                this.k = i4;
                this.l = i5;
            }
            a aVar = new a(left, right);
            if (!z) {
                this.j.removeAllUpdateListeners();
                this.j.addUpdateListener(aVar);
                return;
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            this.j = valueAnimator;
            valueAnimator.setInterpolator(d.b.a.a.m.a.f4675b);
            valueAnimator.setDuration(i3);
            valueAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimator.addUpdateListener(aVar);
            valueAnimator.addListener(new b(i2));
            valueAnimator.start();
        }

        void c(int i2, int i3) {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
            k(true, i2, i3);
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            Drawable drawable = TabLayout.this.p;
            int height = 0;
            int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
            int i2 = this.f4266b;
            if (i2 >= 0) {
                intrinsicHeight = i2;
            }
            int i3 = TabLayout.this.B;
            if (i3 == 0) {
                height = getHeight() - intrinsicHeight;
                intrinsicHeight = getHeight();
            } else if (i3 == 1) {
                height = (getHeight() - intrinsicHeight) / 2;
                intrinsicHeight = (getHeight() + intrinsicHeight) / 2;
            } else if (i3 != 2) {
                intrinsicHeight = i3 != 3 ? 0 : getHeight();
            }
            int i4 = this.f4272h;
            if (i4 >= 0 && this.f4273i > i4) {
                Drawable drawable2 = TabLayout.this.p;
                if (drawable2 == null) {
                    drawable2 = this.f4268d;
                }
                Drawable drawableMutate = androidx.core.graphics.drawable.a.r(drawable2).mutate();
                drawableMutate.setBounds(this.f4272h, height, this.f4273i, intrinsicHeight);
                Paint paint = this.f4267c;
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
            for (int i2 = 0; i2 < childCount; i2++) {
                if (getChildAt(i2).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        void f(int i2, int i3) {
            if (i2 == this.f4272h && i3 == this.f4273i) {
                return;
            }
            this.f4272h = i2;
            this.f4273i = i3;
            t.a0(this);
        }

        void g(int i2, float f2) {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
            this.f4269e = i2;
            this.f4270f = f2;
            j();
        }

        void h(int i2) {
            if (this.f4267c.getColor() != i2) {
                this.f4267c.setColor(i2);
                t.a0(this);
            }
        }

        void i(int i2) {
            if (this.f4266b != i2) {
                this.f4266b = i2;
                t.a0(this);
            }
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            super.onLayout(z, i2, i3, i4, i5);
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                j();
            } else {
                k(false, this.f4269e, -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            if (View.MeasureSpec.getMode(i2) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            boolean z = true;
            if (tabLayout.z == 1 || tabLayout.C == 2) {
                int childCount = getChildCount();
                int iMax = 0;
                for (int i4 = 0; i4 < childCount; i4++) {
                    View childAt = getChildAt(i4);
                    if (childAt.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount <= getMeasuredWidth() - (((int) q.c(getContext(), 16)) * 2)) {
                    boolean z2 = false;
                    for (int i5 = 0; i5 < childCount; i5++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i5).getLayoutParams();
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
                    super.onMeasure(i2, i3);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i2) {
            super.onRtlPropertiesChanged(i2);
            if (Build.VERSION.SDK_INT >= 23 || this.f4271g == i2) {
                return;
            }
            requestLayout();
            this.f4271g = i2;
        }
    }

    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Object f4279a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Drawable f4280b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CharSequence f4281c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private CharSequence f4282d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private View f4284f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public TabLayout f4286h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public TabView f4287i;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f4283e = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f4285g = 1;

        public View d() {
            return this.f4284f;
        }

        public Drawable e() {
            return this.f4280b;
        }

        public int f() {
            return this.f4283e;
        }

        public int g() {
            return this.f4285g;
        }

        public CharSequence h() {
            return this.f4281c;
        }

        public boolean i() {
            TabLayout tabLayout = this.f4286h;
            if (tabLayout != null) {
                return tabLayout.getSelectedTabPosition() == this.f4283e;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        void j() {
            this.f4286h = null;
            this.f4287i = null;
            this.f4279a = null;
            this.f4280b = null;
            this.f4281c = null;
            this.f4282d = null;
            this.f4283e = -1;
            this.f4284f = null;
        }

        public void k() {
            TabLayout tabLayout = this.f4286h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.C(this);
        }

        public g l(CharSequence charSequence) {
            this.f4282d = charSequence;
            r();
            return this;
        }

        public g m(int i2) {
            return n(LayoutInflater.from(this.f4287i.getContext()).inflate(i2, (ViewGroup) this.f4287i, false));
        }

        public g n(View view) {
            this.f4284f = view;
            r();
            return this;
        }

        public g o(Drawable drawable) {
            this.f4280b = drawable;
            TabLayout tabLayout = this.f4286h;
            if (tabLayout.z == 1 || tabLayout.C == 2) {
                tabLayout.L(true);
            }
            r();
            if (com.google.android.material.badge.a.f3714a && this.f4287i.l() && this.f4287i.f4256f.isVisible()) {
                this.f4287i.invalidate();
            }
            return this;
        }

        void p(int i2) {
            this.f4283e = i2;
        }

        public g q(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.f4282d) && !TextUtils.isEmpty(charSequence)) {
                this.f4287i.setContentDescription(charSequence);
            }
            this.f4281c = charSequence;
            r();
            return this;
        }

        void r() {
            TabView tabView = this.f4287i;
            if (tabView != null) {
                tabView.t();
            }
        }
    }

    public static class h implements ViewPager.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<TabLayout> f4288a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f4289b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f4290c;

        public h(TabLayout tabLayout) {
            this.f4288a = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i2, float f2, int i3) {
            TabLayout tabLayout = this.f4288a.get();
            if (tabLayout != null) {
                int i4 = this.f4290c;
                tabLayout.G(i2, f2, i4 != 2 || this.f4289b == 1, (i4 == 2 && this.f4289b == 0) ? false : true);
            }
        }

        void b() {
            this.f4290c = 0;
            this.f4289b = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i2) {
            this.f4289b = this.f4290c;
            this.f4290c = i2;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i2) {
            TabLayout tabLayout = this.f4288a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i2 || i2 >= tabLayout.getTabCount()) {
                return;
            }
            int i3 = this.f4290c;
            tabLayout.D(tabLayout.v(i2), i3 == 0 || (i3 == 2 && this.f4289b == 0));
        }
    }

    public static class i implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ViewPager f4291a;

        public i(ViewPager viewPager) {
            this.f4291a = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(g gVar) {
            this.f4291a.setCurrentItem(gVar.f());
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(g gVar) {
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private void B(int i2) {
        TabView tabView = (TabView) this.f4249g.getChildAt(i2);
        this.f4249g.removeViewAt(i2);
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
        int size = this.f4246d.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f4246d.get(i2).r();
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
        CharSequence charSequence = tabItem.f4241b;
        if (charSequence != null) {
            gVarW.q(charSequence);
        }
        Drawable drawable = tabItem.f4242c;
        if (drawable != null) {
            gVarW.o(drawable);
        }
        int i2 = tabItem.f4243d;
        if (i2 != 0) {
            gVarW.m(i2);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            gVarW.l(tabItem.getContentDescription());
        }
        c(gVarW);
    }

    private void g(g gVar) {
        TabView tabView = gVar.f4287i;
        tabView.setSelected(false);
        tabView.setActivated(false);
        this.f4249g.addView(tabView, gVar.f(), o());
    }

    private int getDefaultHeight() {
        int size = this.f4246d.size();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                g gVar = this.f4246d.get(i2);
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
        return Math.max(0, ((this.f4249g.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
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
        if (getWindowToken() == null || !t.P(this) || this.f4249g.e()) {
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
        this.f4249g.c(i2, this.A);
    }

    private void j(int i2) {
        if (i2 == 0) {
            Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        } else if (i2 == 1) {
            this.f4249g.setGravity(1);
            return;
        } else if (i2 != 2) {
            return;
        }
        this.f4249g.setGravity(8388611);
    }

    private void k() {
        int i2 = this.C;
        t.w0(this.f4249g, (i2 == 0 || i2 == 2) ? Math.max(0, this.y - this.f4250h) : 0, 0, 0, 0);
        int i3 = this.C;
        if (i3 == 0) {
            j(this.z);
        } else if (i3 == 1 || i3 == 2) {
            if (this.z == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            this.f4249g.setGravity(1);
        }
        L(true);
    }

    private int l(int i2, float f2) {
        int i3 = this.C;
        if (i3 != 0 && i3 != 2) {
            return 0;
        }
        View childAt = this.f4249g.getChildAt(i2);
        int i4 = i2 + 1;
        View childAt2 = i4 < this.f4249g.getChildCount() ? this.f4249g.getChildAt(i4) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i5 = (int) ((width + width2) * 0.5f * f2);
        return t.z(this) == 0 ? left + i5 : left - i5;
    }

    private void m(g gVar, int i2) {
        gVar.p(i2);
        this.f4246d.add(i2, gVar);
        int size = this.f4246d.size();
        while (true) {
            i2++;
            if (i2 >= size) {
                return;
            } else {
                this.f4246d.get(i2).p(i2);
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
        if (TextUtils.isEmpty(gVar.f4282d)) {
            tabViewB.setContentDescription(gVar.f4281c);
        } else {
            tabViewB.setContentDescription(gVar.f4282d);
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
        int childCount = this.f4249g.getChildCount();
        if (i2 < childCount) {
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = this.f4249g.getChildAt(i3);
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
            valueAnimator.setInterpolator(d.b.a.a.m.a.f4675b);
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
        g gVar2 = this.f4247e;
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
        this.f4247e = gVar;
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
        if (iRound < 0 || iRound >= this.f4249g.getChildCount()) {
            return;
        }
        if (z2) {
            this.f4249g.g(i2, f2);
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
        for (int i2 = 0; i2 < this.f4249g.getChildCount(); i2++) {
            View childAt = this.f4249g.getChildAt(i2);
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
        e(gVar, this.f4246d.isEmpty());
    }

    public void d(g gVar, int i2, boolean z) {
        if (gVar.f4286h != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        m(gVar, i2);
        g(gVar);
        if (z) {
            gVar.k();
        }
    }

    public void e(g gVar, boolean z) {
        d(gVar, this.f4246d.size(), z);
    }

    public int getSelectedTabPosition() {
        g gVar = this.f4247e;
        if (gVar != null) {
            return gVar.f();
        }
        return -1;
    }

    public int getTabCount() {
        return this.f4246d.size();
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
        d.b.a.a.x.i.e(this);
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
        for (int i2 = 0; i2 < this.f4249g.getChildCount(); i2++) {
            View childAt = this.f4249g.getChildAt(i2);
            if (childAt instanceof TabView) {
                ((TabView) childAt).drawBackground(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        b.g.k.c0.c.C0(accessibilityNodeInfo).d0(c.b.b(1, getTabCount(), false, 1));
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
            float r0 = com.google.android.material.internal.q.c(r0, r1)
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
            float r1 = com.google.android.material.internal.q.c(r1, r2)
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
        g gVarB = f4245c.b();
        return gVarB == null ? new g() : gVarB;
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        d.b.a.a.x.i.d(this, f2);
    }

    public void setInlineLabel(boolean z) {
        if (this.D != z) {
            this.D = z;
            for (int i2 = 0; i2 < this.f4249g.getChildCount(); i2++) {
                View childAt = this.f4249g.getChildAt(i2);
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
            t.a0(this.f4249g);
        }
    }

    public void setSelectedTabIndicatorColor(int i2) {
        this.f4249g.h(i2);
    }

    public void setSelectedTabIndicatorGravity(int i2) {
        if (this.B != i2) {
            this.B = i2;
            t.a0(this.f4249g);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i2) {
        this.f4249g.i(i2);
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
        t.a0(this.f4249g);
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
            for (int i2 = 0; i2 < this.f4249g.getChildCount(); i2++) {
                View childAt = this.f4249g.getChildAt(i2);
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
            for (int i2 = 0; i2 < this.f4249g.getChildCount(); i2++) {
                View childAt = this.f4249g.getChildAt(i2);
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
        return this.f4246d.get(i2);
    }

    public g w() {
        g gVarP = p();
        gVarP.f4286h = this;
        gVarP.f4287i = q(gVarP);
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
        return f4245c.a(gVar);
    }

    public void z() {
        for (int childCount = this.f4249g.getChildCount() - 1; childCount >= 0; childCount--) {
            B(childCount);
        }
        Iterator<g> it = this.f4246d.iterator();
        while (it.hasNext()) {
            g next = it.next();
            it.remove();
            next.j();
            y(next);
        }
        this.f4247e = null;
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, d.b.a.a.b.tabStyle);
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
        int i3 = f4244b;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f4246d = new ArrayList<>();
        this.f4248f = new RectF();
        this.u = Integer.MAX_VALUE;
        this.H = new ArrayList<>();
        this.Q = new b.g.j.f(12);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        f fVar = new f(context2);
        this.f4249g = fVar;
        super.addView(fVar, 0, new FrameLayout.LayoutParams(-2, -1));
        int[] iArr = l.TabLayout;
        int i4 = l.TabLayout_tabTextAppearance;
        TypedArray typedArrayH = com.google.android.material.internal.l.h(context2, attributeSet, iArr, i2, i3, i4);
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            d.b.a.a.x.h hVar = new d.b.a.a.x.h();
            hVar.X(ColorStateList.valueOf(colorDrawable.getColor()));
            hVar.N(context2);
            hVar.W(t.u(this));
            t.m0(this, hVar);
        }
        fVar.i(typedArrayH.getDimensionPixelSize(l.TabLayout_tabIndicatorHeight, -1));
        fVar.h(typedArrayH.getColor(l.TabLayout_tabIndicatorColor, 0));
        setSelectedTabIndicator(d.b.a.a.u.c.d(context2, typedArrayH, l.TabLayout_tabIndicator));
        setSelectedTabIndicatorGravity(typedArrayH.getInt(l.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorFullWidth(typedArrayH.getBoolean(l.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(l.TabLayout_tabPadding, 0);
        this.k = dimensionPixelSize;
        this.j = dimensionPixelSize;
        this.f4251i = dimensionPixelSize;
        this.f4250h = dimensionPixelSize;
        this.f4250h = typedArrayH.getDimensionPixelSize(l.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.f4251i = typedArrayH.getDimensionPixelSize(l.TabLayout_tabPaddingTop, this.f4251i);
        this.j = typedArrayH.getDimensionPixelSize(l.TabLayout_tabPaddingEnd, this.j);
        this.k = typedArrayH.getDimensionPixelSize(l.TabLayout_tabPaddingBottom, this.k);
        int resourceId = typedArrayH.getResourceId(i4, k.TextAppearance_Design_Tab);
        this.l = resourceId;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, j.TextAppearance);
        try {
            this.r = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.TextAppearance_android_textSize, 0);
            this.m = d.b.a.a.u.c.a(context2, typedArrayObtainStyledAttributes, j.TextAppearance_android_textColor);
            typedArrayObtainStyledAttributes.recycle();
            int i5 = l.TabLayout_tabTextColor;
            if (typedArrayH.hasValue(i5)) {
                this.m = d.b.a.a.u.c.a(context2, typedArrayH, i5);
            }
            int i6 = l.TabLayout_tabSelectedTextColor;
            if (typedArrayH.hasValue(i6)) {
                this.m = n(this.m.getDefaultColor(), typedArrayH.getColor(i6, 0));
            }
            this.n = d.b.a.a.u.c.a(context2, typedArrayH, l.TabLayout_tabIconTint);
            this.q = q.i(typedArrayH.getInt(l.TabLayout_tabIconTintMode, -1), null);
            this.o = d.b.a.a.u.c.a(context2, typedArrayH, l.TabLayout_tabRippleColor);
            this.A = typedArrayH.getInt(l.TabLayout_tabIndicatorAnimationDuration, 300);
            this.v = typedArrayH.getDimensionPixelSize(l.TabLayout_tabMinWidth, -1);
            this.w = typedArrayH.getDimensionPixelSize(l.TabLayout_tabMaxWidth, -1);
            this.t = typedArrayH.getResourceId(l.TabLayout_tabBackground, 0);
            this.y = typedArrayH.getDimensionPixelSize(l.TabLayout_tabContentStart, 0);
            this.C = typedArrayH.getInt(l.TabLayout_tabMode, 1);
            this.z = typedArrayH.getInt(l.TabLayout_tabGravity, 0);
            this.D = typedArrayH.getBoolean(l.TabLayout_tabInlineLabel, false);
            this.F = typedArrayH.getBoolean(l.TabLayout_tabUnboundedRipple, false);
            typedArrayH.recycle();
            Resources resources = getResources();
            this.s = resources.getDimensionPixelSize(d.b.a.a.d.design_tab_text_size_2line);
            this.x = resources.getDimensionPixelSize(d.b.a.a.d.design_tab_scrollable_min_width);
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
