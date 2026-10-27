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
import b.b.j;
import b.b.q.g0;
import b.h.m.c0.c;
import b.h.m.r;
import b.h.m.t;
import c.b.a.a.d0.p;
import c.b.a.a.k;
import c.b.a.a.l;
import com.google.android.material.badge.BadgeDrawable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class TabLayout extends HorizontalScrollView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f4829b = k.Widget_Design_TabLayout;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.h.l.d<g> f4830c = new b.h.l.f(16);
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
    public b.a0.a.a L;
    public DataSetObserver M;
    public h N;
    public b O;
    public boolean P;
    public final b.h.l.d<TabView> Q;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList<g> f4831d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g f4832e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f4833f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f f4834g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4835h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4836i;
    public int j;
    public int k;
    public int l;
    public ColorStateList m;
    public ColorStateList n;
    public ColorStateList o;
    public Drawable p;
    public PorterDuff.Mode q;
    public float r;
    public float s;
    public final int t;
    public int u;
    public final int v;
    public final int w;
    public final int x;
    public int y;
    public int z;

    public final class TabView extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public g f4837b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f4838c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ImageView f4839d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f4840e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public BadgeDrawable f4841f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public View f4842g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public TextView f4843h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public ImageView f4844i;
        public Drawable j;
        public int k;

        public class a implements View.OnLayoutChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f4845a;

            public a(View view) {
                this.f4845a = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                if (this.f4845a.getVisibility() == 0) {
                    TabView.this.s(this.f4845a);
                }
            }
        }

        public TabView(Context context) {
            super(context);
            this.k = 2;
            u(context);
            t.y0(this, TabLayout.this.f4835h, TabLayout.this.f4836i, TabLayout.this.j, TabLayout.this.k);
            setGravity(17);
            setOrientation(!TabLayout.this.D ? 1 : 0);
            setClickable(true);
            t.z0(this, r.b(getContext(), 1002));
        }

        private BadgeDrawable getBadge() {
            return this.f4841f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getContentWidth() {
            View[] viewArr = {this.f4838c, this.f4839d, this.f4842g};
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
            if (this.f4841f == null) {
                this.f4841f = BadgeDrawable.c(getContext());
            }
            r();
            BadgeDrawable badgeDrawable = this.f4841f;
            if (badgeDrawable != null) {
                return badgeDrawable;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        public final void drawBackground(Canvas canvas) {
            Drawable drawable = this.j;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.j.draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void drawableStateChanged() {
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

        public final void g(View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(new a(view));
        }

        public g getTab() {
            return this.f4837b;
        }

        public final float h(Layout layout, int i2, float f2) {
            return layout.getLineWidth(i2) * (f2 / layout.getPaint().getTextSize());
        }

        public final void i(boolean z) {
            setClipChildren(z);
            setClipToPadding(z);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(z);
                viewGroup.setClipToPadding(z);
            }
        }

        public final FrameLayout j() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout;
        }

        public final FrameLayout k(View view) {
            if ((view == this.f4839d || view == this.f4838c) && c.b.a.a.o.a.f4058a) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        public final boolean l() {
            return this.f4841f != null;
        }

        public final void m() {
            ViewGroup viewGroup;
            if (c.b.a.a.o.a.f4058a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ, 0);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(c.b.a.a.h.design_layout_tab_icon, viewGroup, false);
            this.f4839d = imageView;
            viewGroup.addView(imageView, 0);
        }

        public final void n() {
            ViewGroup viewGroup;
            if (c.b.a.a.o.a.f4058a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(c.b.a.a.h.design_layout_tab_text, viewGroup, false);
            this.f4838c = textView;
            viewGroup.addView(textView);
        }

        public void o() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            BadgeDrawable badgeDrawable = this.f4841f;
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.f4841f.h()));
            }
            b.h.m.c0.c cVarF0 = b.h.m.c0.c.F0(accessibilityNodeInfo);
            cVarF0.f0(c.C0044c.a(0, 1, this.f4837b.f(), 1, false, isSelected()));
            if (isSelected()) {
                cVarF0.d0(false);
                cVarF0.T(c.a.f2616e);
            }
            cVarF0.v0("Tab");
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
            if (this.f4838c != null) {
                float f2 = TabLayout.this.r;
                int i4 = this.k;
                ImageView imageView = this.f4839d;
                boolean z = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f4838c;
                    if (textView != null && textView.getLineCount() > 1) {
                        f2 = TabLayout.this.s;
                    }
                } else {
                    i4 = 1;
                }
                float textSize = this.f4838c.getTextSize();
                int lineCount = this.f4838c.getLineCount();
                int iD = b.h.n.i.d(this.f4838c);
                if (f2 != textSize || (iD >= 0 && i4 != iD)) {
                    if (TabLayout.this.C == 1 && f2 > textSize && lineCount == 1 && ((layout = this.f4838c.getLayout()) == null || h(layout, 0, f2) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z = false;
                    }
                    if (z) {
                        this.f4838c.setTextSize(0, f2);
                        this.f4838c.setMaxLines(i4);
                        super.onMeasure(i2, i3);
                    }
                }
            }
        }

        public final void p(View view) {
            if (l() && view != null) {
                i(false);
                c.b.a.a.o.a.a(this.f4841f, view, k(view));
                this.f4840e = view;
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.f4837b == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.f4837b.k();
            return true;
        }

        public final void q() {
            if (l()) {
                i(true);
                View view = this.f4840e;
                if (view != null) {
                    c.b.a.a.o.a.d(this.f4841f, view, k(view));
                    this.f4840e = null;
                }
            }
        }

        public final void r() {
            g gVar;
            g gVar2;
            if (l()) {
                if (this.f4842g != null) {
                    q();
                    return;
                }
                if (this.f4839d != null && (gVar2 = this.f4837b) != null && gVar2.e() != null) {
                    View view = this.f4840e;
                    ImageView imageView = this.f4839d;
                    if (view == imageView) {
                        s(imageView);
                        return;
                    } else {
                        q();
                        p(this.f4839d);
                        return;
                    }
                }
                if (this.f4838c == null || (gVar = this.f4837b) == null || gVar.g() != 1) {
                    q();
                    return;
                }
                View view2 = this.f4840e;
                TextView textView = this.f4838c;
                if (view2 == textView) {
                    s(textView);
                } else {
                    q();
                    p(this.f4838c);
                }
            }
        }

        public final void s(View view) {
            if (l() && view == this.f4840e) {
                c.b.a.a.o.a.e(this.f4841f, view, k(view));
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z && Build.VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            TextView textView = this.f4838c;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.f4839d;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.f4842g;
            if (view != null) {
                view.setSelected(z);
            }
        }

        public void setTab(g gVar) {
            if (gVar != this.f4837b) {
                this.f4837b = gVar;
                t();
            }
        }

        public final void t() {
            g gVar = this.f4837b;
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
                this.f4842g = viewD;
                TextView textView = this.f4838c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f4839d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f4839d.setImageDrawable(null);
                }
                TextView textView2 = (TextView) viewD.findViewById(R.id.text1);
                this.f4843h = textView2;
                if (textView2 != null) {
                    this.k = b.h.n.i.d(textView2);
                }
                this.f4844i = (ImageView) viewD.findViewById(R.id.icon);
            } else {
                View view = this.f4842g;
                if (view != null) {
                    removeView(view);
                    this.f4842g = null;
                }
                this.f4843h = null;
                this.f4844i = null;
            }
            if (this.f4842g == null) {
                if (this.f4839d == null) {
                    m();
                }
                if (gVar != null && gVar.e() != null) {
                    drawableMutate = b.h.f.l.a.r(gVar.e()).mutate();
                }
                if (drawableMutate != null) {
                    b.h.f.l.a.o(drawableMutate, TabLayout.this.n);
                    PorterDuff.Mode mode = TabLayout.this.q;
                    if (mode != null) {
                        b.h.f.l.a.p(drawableMutate, mode);
                    }
                }
                if (this.f4838c == null) {
                    n();
                    this.k = b.h.n.i.d(this.f4838c);
                }
                b.h.n.i.q(this.f4838c, TabLayout.this.l);
                ColorStateList colorStateList = TabLayout.this.m;
                if (colorStateList != null) {
                    this.f4838c.setTextColor(colorStateList);
                }
                w(this.f4838c, this.f4839d);
                r();
                g(this.f4839d);
                g(this.f4838c);
            } else {
                TextView textView3 = this.f4843h;
                if (textView3 != null || this.f4844i != null) {
                    w(textView3, this.f4844i);
                }
            }
            if (gVar != null && !TextUtils.isEmpty(gVar.f4867d)) {
                setContentDescription(gVar.f4867d);
            }
            setSelected(gVar != null && gVar.i());
        }

        public final void u(Context context) {
            int i2 = TabLayout.this.t;
            if (i2 != 0) {
                Drawable drawableD = b.b.l.a.a.d(context, i2);
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
                ColorStateList colorStateListA = c.b.a.a.g0.b.a(TabLayout.this.o);
                if (Build.VERSION.SDK_INT >= 21) {
                    boolean z = TabLayout.this.F;
                    GradientDrawable gradientDrawable3 = gradientDrawable;
                    if (z) {
                        gradientDrawable3 = null;
                    }
                    layerDrawable = new RippleDrawable(colorStateListA, gradientDrawable3, z ? null : gradientDrawable2);
                } else {
                    Drawable drawableR = b.h.f.l.a.r(gradientDrawable2);
                    b.h.f.l.a.o(drawableR, colorStateListA);
                    layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, drawableR});
                }
            }
            t.n0(this, layerDrawable);
            TabLayout.this.invalidate();
        }

        public final void v() {
            setOrientation(!TabLayout.this.D ? 1 : 0);
            TextView textView = this.f4843h;
            if (textView == null && this.f4844i == null) {
                w(this.f4838c, this.f4839d);
            } else {
                w(textView, this.f4844i);
            }
        }

        public final void w(TextView textView, ImageView imageView) {
            g gVar = this.f4837b;
            Drawable drawableMutate = (gVar == null || gVar.e() == null) ? null : b.h.f.l.a.r(this.f4837b.e()).mutate();
            g gVar2 = this.f4837b;
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
                    if (this.f4837b.f4870g == 1) {
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
                    if (iC != b.h.m.g.a(marginLayoutParams)) {
                        b.h.m.g.c(marginLayoutParams, iC);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iC != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iC;
                    b.h.m.g.c(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            g gVar3 = this.f4837b;
            g0.a(this, z ? null : gVar3 != null ? gVar3.f4867d : null);
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
        public boolean f4848a;

        public b() {
        }

        public void a(boolean z) {
            this.f4848a = z;
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void b(ViewPager viewPager, b.a0.a.a aVar, b.a0.a.a aVar2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.K == viewPager) {
                tabLayout.E(aVar2, this.f4848a);
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
        public int f4851b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Paint f4852c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final GradientDrawable f4853d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4854e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f4855f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4856g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f4857h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f4858i;
        public ValueAnimator j;
        public int k;
        public int l;

        public class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f4859a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ int f4860b;

            public a(int i2, int i3) {
                this.f4859a = i2;
                this.f4860b = i3;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                f fVar = f.this;
                fVar.f(c.b.a.a.m.a.b(fVar.k, this.f4859a, animatedFraction), c.b.a.a.m.a.b(f.this.l, this.f4860b, animatedFraction));
            }
        }

        public class b extends AnimatorListenerAdapter {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f4862a;

            public b(int i2) {
                this.f4862a = i2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                f fVar = f.this;
                fVar.f4854e = this.f4862a;
                fVar.f4855f = 0.0f;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                f.this.f4854e = this.f4862a;
            }
        }

        public f(Context context) {
            super(context);
            this.f4854e = -1;
            this.f4856g = -1;
            this.f4857h = -1;
            this.f4858i = -1;
            this.k = -1;
            this.l = -1;
            setWillNotDraw(false);
            this.f4852c = new Paint();
            this.f4853d = new GradientDrawable();
        }

        public void c(int i2, int i3) {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
            k(true, i2, i3);
        }

        public final void d(TabView tabView, RectF rectF) {
            int contentWidth = tabView.getContentWidth();
            int iC = (int) p.c(getContext(), 24);
            if (contentWidth < iC) {
                contentWidth = iC;
            }
            int left = (tabView.getLeft() + tabView.getRight()) / 2;
            int i2 = contentWidth / 2;
            rectF.set(left - i2, 0.0f, left + i2, 0.0f);
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            Drawable drawable = TabLayout.this.p;
            int height = 0;
            int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
            int i2 = this.f4851b;
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
            int i4 = this.f4857h;
            if (i4 >= 0 && this.f4858i > i4) {
                Drawable drawable2 = TabLayout.this.p;
                if (drawable2 == null) {
                    drawable2 = this.f4853d;
                }
                Drawable drawableMutate = b.h.f.l.a.r(drawable2).mutate();
                drawableMutate.setBounds(this.f4857h, height, this.f4858i, intrinsicHeight);
                Paint paint = this.f4852c;
                if (paint != null) {
                    if (Build.VERSION.SDK_INT == 21) {
                        drawableMutate.setColorFilter(paint.getColor(), PorterDuff.Mode.SRC_IN);
                    } else {
                        b.h.f.l.a.n(drawableMutate, paint.getColor());
                    }
                }
                drawableMutate.draw(canvas);
            }
            super.draw(canvas);
        }

        public boolean e() {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (getChildAt(i2).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        public void f(int i2, int i3) {
            if (i2 == this.f4857h && i3 == this.f4858i) {
                return;
            }
            this.f4857h = i2;
            this.f4858i = i3;
            t.a0(this);
        }

        public void g(int i2, float f2) {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
            this.f4854e = i2;
            this.f4855f = f2;
            j();
        }

        public void h(int i2) {
            if (this.f4852c.getColor() != i2) {
                this.f4852c.setColor(i2);
                t.a0(this);
            }
        }

        public void i(int i2) {
            if (this.f4851b != i2) {
                this.f4851b = i2;
                t.a0(this);
            }
        }

        public final void j() {
            int left;
            int right;
            View childAt = getChildAt(this.f4854e);
            if (childAt == null || childAt.getWidth() <= 0) {
                left = -1;
                right = -1;
            } else {
                left = childAt.getLeft();
                right = childAt.getRight();
                TabLayout tabLayout = TabLayout.this;
                if (!tabLayout.E && (childAt instanceof TabView)) {
                    d((TabView) childAt, tabLayout.f4833f);
                    left = (int) TabLayout.this.f4833f.left;
                    right = (int) TabLayout.this.f4833f.right;
                }
                if (this.f4855f > 0.0f && this.f4854e < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.f4854e + 1);
                    int left2 = childAt2.getLeft();
                    int right2 = childAt2.getRight();
                    TabLayout tabLayout2 = TabLayout.this;
                    if (!tabLayout2.E && (childAt2 instanceof TabView)) {
                        d((TabView) childAt2, tabLayout2.f4833f);
                        left2 = (int) TabLayout.this.f4833f.left;
                        right2 = (int) TabLayout.this.f4833f.right;
                    }
                    float f2 = this.f4855f;
                    left = (int) ((left2 * f2) + ((1.0f - f2) * left));
                    right = (int) ((right2 * f2) + ((1.0f - f2) * right));
                }
            }
            f(left, right);
        }

        public final void k(boolean z, int i2, int i3) {
            View childAt = getChildAt(i2);
            if (childAt == null) {
                j();
                return;
            }
            int left = childAt.getLeft();
            int right = childAt.getRight();
            TabLayout tabLayout = TabLayout.this;
            if (!tabLayout.E && (childAt instanceof TabView)) {
                d((TabView) childAt, tabLayout.f4833f);
                left = (int) TabLayout.this.f4833f.left;
                right = (int) TabLayout.this.f4833f.right;
            }
            int i4 = this.f4857h;
            int i5 = this.f4858i;
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
            valueAnimator.setInterpolator(c.b.a.a.m.a.f4009b);
            valueAnimator.setDuration(i3);
            valueAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimator.addUpdateListener(aVar);
            valueAnimator.addListener(new b(i2));
            valueAnimator.start();
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            super.onLayout(z, i2, i3, i4, i5);
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                j();
            } else {
                k(false, this.f4854e, -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i2, int i3) {
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
                if (iMax * childCount <= getMeasuredWidth() - (((int) p.c(getContext(), 16)) * 2)) {
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
            if (Build.VERSION.SDK_INT >= 23 || this.f4856g == i2) {
                return;
            }
            requestLayout();
            this.f4856g = i2;
        }
    }

    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4864a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Drawable f4865b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f4866c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f4867d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f4869f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public TabLayout f4871h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public TabView f4872i;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4868e = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4870g = 1;

        public View d() {
            return this.f4869f;
        }

        public Drawable e() {
            return this.f4865b;
        }

        public int f() {
            return this.f4868e;
        }

        public int g() {
            return this.f4870g;
        }

        public CharSequence h() {
            return this.f4866c;
        }

        public boolean i() {
            TabLayout tabLayout = this.f4871h;
            if (tabLayout != null) {
                return tabLayout.getSelectedTabPosition() == this.f4868e;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public void j() {
            this.f4871h = null;
            this.f4872i = null;
            this.f4864a = null;
            this.f4865b = null;
            this.f4866c = null;
            this.f4867d = null;
            this.f4868e = -1;
            this.f4869f = null;
        }

        public void k() {
            TabLayout tabLayout = this.f4871h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.C(this);
        }

        public g l(CharSequence charSequence) {
            this.f4867d = charSequence;
            r();
            return this;
        }

        public g m(int i2) {
            return n(LayoutInflater.from(this.f4872i.getContext()).inflate(i2, (ViewGroup) this.f4872i, false));
        }

        public g n(View view) {
            this.f4869f = view;
            r();
            return this;
        }

        public g o(Drawable drawable) {
            this.f4865b = drawable;
            TabLayout tabLayout = this.f4871h;
            if (tabLayout.z == 1 || tabLayout.C == 2) {
                tabLayout.L(true);
            }
            r();
            if (c.b.a.a.o.a.f4058a && this.f4872i.l() && this.f4872i.f4841f.isVisible()) {
                this.f4872i.invalidate();
            }
            return this;
        }

        public void p(int i2) {
            this.f4868e = i2;
        }

        public g q(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.f4867d) && !TextUtils.isEmpty(charSequence)) {
                this.f4872i.setContentDescription(charSequence);
            }
            this.f4866c = charSequence;
            r();
            return this;
        }

        public void r() {
            TabView tabView = this.f4872i;
            if (tabView != null) {
                tabView.t();
            }
        }
    }

    public static class h implements ViewPager.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<TabLayout> f4873a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4874b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4875c;

        public h(TabLayout tabLayout) {
            this.f4873a = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i2, float f2, int i3) {
            TabLayout tabLayout = this.f4873a.get();
            if (tabLayout != null) {
                int i4 = this.f4875c;
                tabLayout.G(i2, f2, i4 != 2 || this.f4874b == 1, (i4 == 2 && this.f4874b == 0) ? false : true);
            }
        }

        public void b() {
            this.f4875c = 0;
            this.f4874b = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i2) {
            this.f4874b = this.f4875c;
            this.f4875c = i2;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i2) {
            TabLayout tabLayout = this.f4873a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i2 || i2 >= tabLayout.getTabCount()) {
                return;
            }
            int i3 = this.f4875c;
            tabLayout.D(tabLayout.v(i2), i3 == 0 || (i3 == 2 && this.f4874b == 0));
        }
    }

    public static class i implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ViewPager f4876a;

        public i(ViewPager viewPager) {
            this.f4876a = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(g gVar) {
            this.f4876a.setCurrentItem(gVar.f());
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(g gVar) {
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private int getDefaultHeight() {
        int size = this.f4831d.size();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                g gVar = this.f4831d.get(i2);
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
        return Math.max(0, ((this.f4834g.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    public static ColorStateList n(int i2, int i3) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i3, i2});
    }

    private void setSelectedTabView(int i2) {
        int childCount = this.f4834g.getChildCount();
        if (i2 < childCount) {
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = this.f4834g.getChildAt(i3);
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

    @Deprecated
    public void A(c cVar) {
        this.H.remove(cVar);
    }

    public final void B(int i2) {
        TabView tabView = (TabView) this.f4834g.getChildAt(i2);
        this.f4834g.removeViewAt(i2);
        if (tabView != null) {
            tabView.o();
            this.Q.a(tabView);
        }
        requestLayout();
    }

    public void C(g gVar) {
        D(gVar, true);
    }

    public void D(g gVar, boolean z) {
        g gVar2 = this.f4832e;
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
        this.f4832e = gVar;
        if (gVar2 != null) {
            t(gVar2);
        }
        if (gVar != null) {
            s(gVar);
        }
    }

    public void E(b.a0.a.a aVar, boolean z) {
        DataSetObserver dataSetObserver;
        b.a0.a.a aVar2 = this.L;
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
        if (iRound < 0 || iRound >= this.f4834g.getChildCount()) {
            return;
        }
        if (z2) {
            this.f4834g.g(i2, f2);
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

    public final void I(ViewPager viewPager, boolean z, boolean z2) {
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
            b.a0.a.a adapter = viewPager.getAdapter();
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

    public final void J() {
        int size = this.f4831d.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f4831d.get(i2).r();
        }
    }

    public final void K(LinearLayout.LayoutParams layoutParams) {
        if (this.C == 1 && this.z == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    public void L(boolean z) {
        for (int i2 = 0; i2 < this.f4834g.getChildCount(); i2++) {
            View childAt = this.f4834g.getChildAt(i2);
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
        e(gVar, this.f4831d.isEmpty());
    }

    public void d(g gVar, int i2, boolean z) {
        if (gVar.f4871h != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        m(gVar, i2);
        g(gVar);
        if (z) {
            gVar.k();
        }
    }

    public void e(g gVar, boolean z) {
        d(gVar, this.f4831d.size(), z);
    }

    public final void f(TabItem tabItem) {
        g gVarW = w();
        CharSequence charSequence = tabItem.f4826b;
        if (charSequence != null) {
            gVarW.q(charSequence);
        }
        Drawable drawable = tabItem.f4827c;
        if (drawable != null) {
            gVarW.o(drawable);
        }
        int i2 = tabItem.f4828d;
        if (i2 != 0) {
            gVarW.m(i2);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            gVarW.l(tabItem.getContentDescription());
        }
        c(gVarW);
    }

    public final void g(g gVar) {
        TabView tabView = gVar.f4872i;
        tabView.setSelected(false);
        tabView.setActivated(false);
        this.f4834g.addView(tabView, gVar.f(), o());
    }

    public int getSelectedTabPosition() {
        g gVar = this.f4832e;
        if (gVar != null) {
            return gVar.f();
        }
        return -1;
    }

    public int getTabCount() {
        return this.f4831d.size();
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

    public int getTabMaxWidth() {
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

    public final void h(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        f((TabItem) view);
    }

    public final void i(int i2) {
        if (i2 == -1) {
            return;
        }
        if (getWindowToken() == null || !t.P(this) || this.f4834g.e()) {
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
        this.f4834g.c(i2, this.A);
    }

    public final void j(int i2) {
        if (i2 == 0) {
            Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        } else if (i2 == 1) {
            this.f4834g.setGravity(1);
            return;
        } else if (i2 != 2) {
            return;
        }
        this.f4834g.setGravity(8388611);
    }

    public final void k() {
        int i2 = this.C;
        t.y0(this.f4834g, (i2 == 0 || i2 == 2) ? Math.max(0, this.y - this.f4835h) : 0, 0, 0, 0);
        int i3 = this.C;
        if (i3 == 0) {
            j(this.z);
        } else if (i3 == 1 || i3 == 2) {
            if (this.z == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            this.f4834g.setGravity(1);
        }
        L(true);
    }

    public final int l(int i2, float f2) {
        int i3 = this.C;
        if (i3 != 0 && i3 != 2) {
            return 0;
        }
        View childAt = this.f4834g.getChildAt(i2);
        int i4 = i2 + 1;
        View childAt2 = i4 < this.f4834g.getChildCount() ? this.f4834g.getChildAt(i4) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i5 = (int) ((width + width2) * 0.5f * f2);
        return t.z(this) == 0 ? left + i5 : left - i5;
    }

    public final void m(g gVar, int i2) {
        gVar.p(i2);
        this.f4831d.add(i2, gVar);
        int size = this.f4831d.size();
        while (true) {
            i2++;
            if (i2 >= size) {
                return;
            } else {
                this.f4831d.get(i2).p(i2);
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
        c.b.a.a.i0.i.e(this);
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
        for (int i2 = 0; i2 < this.f4834g.getChildCount(); i2++) {
            View childAt = this.f4834g.getChildAt(i2);
            if (childAt instanceof TabView) {
                ((TabView) childAt).drawBackground(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        b.h.m.c0.c.F0(accessibilityNodeInfo).e0(c.b.b(1, getTabCount(), false, 1));
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
            float r0 = c.b.a.a.d0.p.c(r0, r1)
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
            float r1 = c.b.a.a.d0.p.c(r1, r2)
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

    public g p() {
        g gVarB = f4830c.b();
        return gVarB == null ? new g() : gVarB;
    }

    public final TabView q(g gVar) {
        b.h.l.d<TabView> dVar = this.Q;
        TabView tabViewB = dVar != null ? dVar.b() : null;
        if (tabViewB == null) {
            tabViewB = new TabView(getContext());
        }
        tabViewB.setTab(gVar);
        tabViewB.setFocusable(true);
        tabViewB.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(gVar.f4867d)) {
            tabViewB.setContentDescription(gVar.f4866c);
        } else {
            tabViewB.setContentDescription(gVar.f4867d);
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
    public void setElevation(float f2) {
        super.setElevation(f2);
        c.b.a.a.i0.i.d(this, f2);
    }

    public void setInlineLabel(boolean z) {
        if (this.D != z) {
            this.D = z;
            for (int i2 = 0; i2 < this.f4834g.getChildCount(); i2++) {
                View childAt = this.f4834g.getChildAt(i2);
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

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        u();
        this.J.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.p != drawable) {
            this.p = drawable;
            t.a0(this.f4834g);
        }
    }

    public void setSelectedTabIndicatorColor(int i2) {
        this.f4834g.h(i2);
    }

    public void setSelectedTabIndicatorGravity(int i2) {
        if (this.B != i2) {
            this.B = i2;
            t.a0(this.f4834g);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i2) {
        this.f4834g.i(i2);
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
        setTabIconTint(b.b.l.a.a.c(getContext(), i2));
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.E = z;
        t.a0(this.f4834g);
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
            for (int i2 = 0; i2 < this.f4834g.getChildCount(); i2++) {
                View childAt = this.f4834g.getChildAt(i2);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).u(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int i2) {
        setTabRippleColor(b.b.l.a.a.c(getContext(), i2));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            J();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(b.a0.a.a aVar) {
        E(aVar, false);
    }

    public void setUnboundedRipple(boolean z) {
        if (this.F != z) {
            this.F = z;
            for (int i2 = 0; i2 < this.f4834g.getChildCount(); i2++) {
                View childAt = this.f4834g.getChildAt(i2);
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

    public final void t(g gVar) {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            this.H.get(size).a(gVar);
        }
    }

    public final void u() {
        if (this.J == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.J = valueAnimator;
            valueAnimator.setInterpolator(c.b.a.a.m.a.f4009b);
            this.J.setDuration(this.A);
            this.J.addUpdateListener(new a());
        }
    }

    public g v(int i2) {
        if (i2 < 0 || i2 >= getTabCount()) {
            return null;
        }
        return this.f4831d.get(i2);
    }

    public g w() {
        g gVarP = p();
        gVarP.f4871h = this;
        gVarP.f4872i = q(gVarP);
        return gVarP;
    }

    public void x() {
        int currentItem;
        z();
        b.a0.a.a aVar = this.L;
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

    public boolean y(g gVar) {
        return f4830c.a(gVar);
    }

    public void z() {
        for (int childCount = this.f4834g.getChildCount() - 1; childCount >= 0; childCount--) {
            B(childCount);
        }
        Iterator<g> it = this.f4831d.iterator();
        while (it.hasNext()) {
            g next = it.next();
            it.remove();
            next.j();
            y(next);
        }
        this.f4832e = null;
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.b.a.a.b.tabStyle);
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
        int i3 = f4829b;
        super(c.b.a.a.m0.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f4831d = new ArrayList<>();
        this.f4833f = new RectF();
        this.u = Integer.MAX_VALUE;
        this.H = new ArrayList<>();
        this.Q = new b.h.l.e(12);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        f fVar = new f(context2);
        this.f4834g = fVar;
        super.addView(fVar, 0, new FrameLayout.LayoutParams(-2, -1));
        int[] iArr = l.TabLayout;
        int i4 = l.TabLayout_tabTextAppearance;
        TypedArray typedArrayH = c.b.a.a.d0.k.h(context2, attributeSet, iArr, i2, i3, i4);
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            c.b.a.a.i0.h hVar = new c.b.a.a.i0.h();
            hVar.X(ColorStateList.valueOf(colorDrawable.getColor()));
            hVar.N(context2);
            hVar.W(t.u(this));
            t.n0(this, hVar);
        }
        fVar.i(typedArrayH.getDimensionPixelSize(l.TabLayout_tabIndicatorHeight, -1));
        fVar.h(typedArrayH.getColor(l.TabLayout_tabIndicatorColor, 0));
        setSelectedTabIndicator(c.b.a.a.f0.c.d(context2, typedArrayH, l.TabLayout_tabIndicator));
        setSelectedTabIndicatorGravity(typedArrayH.getInt(l.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorFullWidth(typedArrayH.getBoolean(l.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(l.TabLayout_tabPadding, 0);
        this.k = dimensionPixelSize;
        this.j = dimensionPixelSize;
        this.f4836i = dimensionPixelSize;
        this.f4835h = dimensionPixelSize;
        this.f4835h = typedArrayH.getDimensionPixelSize(l.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.f4836i = typedArrayH.getDimensionPixelSize(l.TabLayout_tabPaddingTop, this.f4836i);
        this.j = typedArrayH.getDimensionPixelSize(l.TabLayout_tabPaddingEnd, this.j);
        this.k = typedArrayH.getDimensionPixelSize(l.TabLayout_tabPaddingBottom, this.k);
        int resourceId = typedArrayH.getResourceId(i4, k.TextAppearance_Design_Tab);
        this.l = resourceId;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, j.TextAppearance);
        try {
            this.r = typedArrayObtainStyledAttributes.getDimensionPixelSize(j.TextAppearance_android_textSize, 0);
            this.m = c.b.a.a.f0.c.a(context2, typedArrayObtainStyledAttributes, j.TextAppearance_android_textColor);
            typedArrayObtainStyledAttributes.recycle();
            int i5 = l.TabLayout_tabTextColor;
            if (typedArrayH.hasValue(i5)) {
                this.m = c.b.a.a.f0.c.a(context2, typedArrayH, i5);
            }
            int i6 = l.TabLayout_tabSelectedTextColor;
            if (typedArrayH.hasValue(i6)) {
                this.m = n(this.m.getDefaultColor(), typedArrayH.getColor(i6, 0));
            }
            this.n = c.b.a.a.f0.c.a(context2, typedArrayH, l.TabLayout_tabIconTint);
            this.q = p.i(typedArrayH.getInt(l.TabLayout_tabIconTintMode, -1), null);
            this.o = c.b.a.a.f0.c.a(context2, typedArrayH, l.TabLayout_tabRippleColor);
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
            this.s = resources.getDimensionPixelSize(c.b.a.a.d.design_tab_text_size_2line);
            this.x = resources.getDimensionPixelSize(c.b.a.a.d.design_tab_scrollable_min_width);
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
            setSelectedTabIndicator(b.b.l.a.a.d(getContext(), i2));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }
}
