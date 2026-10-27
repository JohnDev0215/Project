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
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import o0.r;
import o0.t;
import p0.c;
import r.g0;
import z5.k;
import z5.p;

/* JADX INFO: loaded from: classes2.dex */
@ViewPager.e
public class TabLayout extends HorizontalScrollView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f4034d = R$style.Widget_Design_TabLayout;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final n0.e<g> f4035e = new n0.g(16);
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public boolean F;
    public boolean G;
    public boolean H;
    public c I;
    public final ArrayList<c> J;
    public c K;
    public ValueAnimator L;
    public ViewPager M;
    public n1.a N;
    public DataSetObserver O;
    public h P;
    public b Q;
    public boolean R;
    public final n0.e<TabView> S;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList<g> f4036f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g f4037g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final RectF f4038h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f f4039i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4040j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4041k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4042l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4043m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f4044n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ColorStateList f4045o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ColorStateList f4046p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ColorStateList f4047q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Drawable f4048r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public PorterDuff.Mode f4049s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f4050t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f4051u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f4052v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f4053w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f4054x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f4055y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f4056z;

    public final class TabView extends LinearLayout {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public g f4057d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public TextView f4058e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ImageView f4059f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public View f4060g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public BadgeDrawable f4061h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public View f4062i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public TextView f4063j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public ImageView f4064k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public Drawable f4065l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f4066m;

        public class a implements View.OnLayoutChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ View f4068a;

            public a(View view) {
                this.f4068a = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
                if (this.f4068a.getVisibility() == 0) {
                    TabView.this.s(this.f4068a);
                }
            }
        }

        public TabView(Context context) {
            super(context);
            this.f4066m = 2;
            u(context);
            t.z0(this, TabLayout.this.f4040j, TabLayout.this.f4041k, TabLayout.this.f4042l, TabLayout.this.f4043m);
            setGravity(17);
            setOrientation(!TabLayout.this.F ? 1 : 0);
            setClickable(true);
            t.A0(this, r.b(getContext(), 1002));
        }

        private BadgeDrawable getBadge() {
            return this.f4061h;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getContentWidth() {
            View[] viewArr = {this.f4058e, this.f4059f, this.f4062i};
            int iMax = 0;
            int iMin = 0;
            boolean z7 = false;
            for (int i8 = 0; i8 < 3; i8++) {
                View view = viewArr[i8];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z7 ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z7 ? Math.max(iMax, view.getRight()) : view.getRight();
                    z7 = true;
                }
            }
            return iMax - iMin;
        }

        private BadgeDrawable getOrCreateBadge() {
            if (this.f4061h == null) {
                this.f4061h = BadgeDrawable.c(getContext());
            }
            r();
            BadgeDrawable badgeDrawable = this.f4061h;
            if (badgeDrawable != null) {
                return badgeDrawable;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        public final void drawBackground(Canvas canvas) {
            Drawable drawable = this.f4065l;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.f4065l.draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.f4065l;
            boolean state = false;
            if (drawable != null && drawable.isStateful()) {
                state = false | this.f4065l.setState(drawableState);
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
            return this.f4057d;
        }

        public final float h(Layout layout, int i8, float f8) {
            return layout.getLineWidth(i8) * (f8 / layout.getPaint().getTextSize());
        }

        public final void i(boolean z7) {
            setClipChildren(z7);
            setClipToPadding(z7);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(z7);
                viewGroup.setClipToPadding(z7);
            }
        }

        public final FrameLayout j() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout;
        }

        public final FrameLayout k(View view) {
            if ((view == this.f4059f || view == this.f4058e) && l5.a.f6289a) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        public final boolean l() {
            return this.f4061h != null;
        }

        public final void m() {
            ViewGroup viewGroup;
            if (l5.a.f6289a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ, 0);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R$layout.design_layout_tab_icon, viewGroup, false);
            this.f4059f = imageView;
            viewGroup.addView(imageView, 0);
        }

        public final void n() {
            ViewGroup viewGroup;
            if (l5.a.f6289a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R$layout.design_layout_tab_text, viewGroup, false);
            this.f4058e = textView;
            viewGroup.addView(textView);
        }

        public void o() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            BadgeDrawable badgeDrawable = this.f4061h;
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.f4061h.h()));
            }
            p0.c cVarF0 = p0.c.F0(accessibilityNodeInfo);
            cVarF0.f0(c.C0101c.a(0, 1, this.f4057d.f(), 1, false, isSelected()));
            if (isSelected()) {
                cVarF0.d0(false);
                cVarF0.T(c.a.f6972e);
            }
            cVarF0.v0("Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i8, int i9) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i8);
            int mode = View.MeasureSpec.getMode(i8);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i8 = View.MeasureSpec.makeMeasureSpec(TabLayout.this.f4053w, Integer.MIN_VALUE);
            }
            super.onMeasure(i8, i9);
            if (this.f4058e != null) {
                float f8 = TabLayout.this.f4050t;
                int i10 = this.f4066m;
                ImageView imageView = this.f4059f;
                boolean z7 = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f4058e;
                    if (textView != null && textView.getLineCount() > 1) {
                        f8 = TabLayout.this.f4051u;
                    }
                } else {
                    i10 = 1;
                }
                float textSize = this.f4058e.getTextSize();
                int lineCount = this.f4058e.getLineCount();
                int iD = r0.i.d(this.f4058e);
                if (f8 != textSize || (iD >= 0 && i10 != iD)) {
                    if (TabLayout.this.E == 1 && f8 > textSize && lineCount == 1 && ((layout = this.f4058e.getLayout()) == null || h(layout, 0, f8) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z7 = false;
                    }
                    if (z7) {
                        this.f4058e.setTextSize(0, f8);
                        this.f4058e.setMaxLines(i10);
                        super.onMeasure(i8, i9);
                    }
                }
            }
        }

        public final void p(View view) {
            if (l() && view != null) {
                i(false);
                l5.a.a(this.f4061h, view, k(view));
                this.f4060g = view;
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.f4057d == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.f4057d.k();
            return true;
        }

        public final void q() {
            if (l()) {
                i(true);
                View view = this.f4060g;
                if (view != null) {
                    l5.a.d(this.f4061h, view, k(view));
                    this.f4060g = null;
                }
            }
        }

        public final void r() {
            g gVar;
            g gVar2;
            if (l()) {
                if (this.f4062i != null) {
                    q();
                    return;
                }
                if (this.f4059f != null && (gVar2 = this.f4057d) != null && gVar2.e() != null) {
                    View view = this.f4060g;
                    ImageView imageView = this.f4059f;
                    if (view == imageView) {
                        s(imageView);
                        return;
                    } else {
                        q();
                        p(this.f4059f);
                        return;
                    }
                }
                if (this.f4058e == null || (gVar = this.f4057d) == null || gVar.g() != 1) {
                    q();
                    return;
                }
                View view2 = this.f4060g;
                TextView textView = this.f4058e;
                if (view2 == textView) {
                    s(textView);
                } else {
                    q();
                    p(this.f4058e);
                }
            }
        }

        public final void s(View view) {
            if (l() && view == this.f4060g) {
                l5.a.e(this.f4061h, view, k(view));
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z7) {
            boolean z8 = isSelected() != z7;
            super.setSelected(z7);
            if (z8 && z7 && Build.VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            TextView textView = this.f4058e;
            if (textView != null) {
                textView.setSelected(z7);
            }
            ImageView imageView = this.f4059f;
            if (imageView != null) {
                imageView.setSelected(z7);
            }
            View view = this.f4062i;
            if (view != null) {
                view.setSelected(z7);
            }
        }

        public void setTab(g gVar) {
            if (gVar != this.f4057d) {
                this.f4057d = gVar;
                t();
            }
        }

        public final void t() {
            g gVar = this.f4057d;
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
                this.f4062i = viewD;
                TextView textView = this.f4058e;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f4059f;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f4059f.setImageDrawable(null);
                }
                TextView textView2 = (TextView) viewD.findViewById(R.id.text1);
                this.f4063j = textView2;
                if (textView2 != null) {
                    this.f4066m = r0.i.d(textView2);
                }
                this.f4064k = (ImageView) viewD.findViewById(R.id.icon);
            } else {
                View view = this.f4062i;
                if (view != null) {
                    removeView(view);
                    this.f4062i = null;
                }
                this.f4063j = null;
                this.f4064k = null;
            }
            if (this.f4062i == null) {
                if (this.f4059f == null) {
                    m();
                }
                if (gVar != null && gVar.e() != null) {
                    drawableMutate = h0.a.r(gVar.e()).mutate();
                }
                if (drawableMutate != null) {
                    h0.a.o(drawableMutate, TabLayout.this.f4046p);
                    PorterDuff.Mode mode = TabLayout.this.f4049s;
                    if (mode != null) {
                        h0.a.p(drawableMutate, mode);
                    }
                }
                if (this.f4058e == null) {
                    n();
                    this.f4066m = r0.i.d(this.f4058e);
                }
                r0.i.q(this.f4058e, TabLayout.this.f4044n);
                ColorStateList colorStateList = TabLayout.this.f4045o;
                if (colorStateList != null) {
                    this.f4058e.setTextColor(colorStateList);
                }
                w(this.f4058e, this.f4059f);
                r();
                g(this.f4059f);
                g(this.f4058e);
            } else {
                TextView textView3 = this.f4063j;
                if (textView3 != null || this.f4064k != null) {
                    w(textView3, this.f4064k);
                }
            }
            if (gVar != null && !TextUtils.isEmpty(gVar.f4094d)) {
                setContentDescription(gVar.f4094d);
            }
            setSelected(gVar != null && gVar.i());
        }

        public final void u(Context context) {
            int i8 = TabLayout.this.f4052v;
            if (i8 != 0) {
                Drawable drawableD = m.a.d(context, i8);
                this.f4065l = drawableD;
                if (drawableD != null && drawableD.isStateful()) {
                    this.f4065l.setState(getDrawableState());
                }
            } else {
                this.f4065l = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            Drawable layerDrawable = gradientDrawable;
            if (TabLayout.this.f4047q != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList colorStateListA = c6.b.a(TabLayout.this.f4047q);
                if (Build.VERSION.SDK_INT >= 21) {
                    boolean z7 = TabLayout.this.H;
                    GradientDrawable gradientDrawable3 = gradientDrawable;
                    if (z7) {
                        gradientDrawable3 = null;
                    }
                    layerDrawable = new RippleDrawable(colorStateListA, gradientDrawable3, z7 ? null : gradientDrawable2);
                } else {
                    Drawable drawableR = h0.a.r(gradientDrawable2);
                    h0.a.o(drawableR, colorStateListA);
                    layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, drawableR});
                }
            }
            t.o0(this, layerDrawable);
            TabLayout.this.invalidate();
        }

        public final void v() {
            setOrientation(!TabLayout.this.F ? 1 : 0);
            TextView textView = this.f4063j;
            if (textView == null && this.f4064k == null) {
                w(this.f4058e, this.f4059f);
            } else {
                w(textView, this.f4064k);
            }
        }

        public final void w(TextView textView, ImageView imageView) {
            g gVar = this.f4057d;
            Drawable drawableMutate = (gVar == null || gVar.e() == null) ? null : h0.a.r(this.f4057d.e()).mutate();
            g gVar2 = this.f4057d;
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
            boolean z7 = !TextUtils.isEmpty(charSequenceH);
            if (textView != null) {
                if (z7) {
                    textView.setText(charSequenceH);
                    if (this.f4057d.f4097g == 1) {
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
                int iC = (z7 && imageView.getVisibility() == 0) ? (int) p.c(getContext(), 8) : 0;
                if (TabLayout.this.F) {
                    if (iC != o0.g.a(marginLayoutParams)) {
                        o0.g.c(marginLayoutParams, iC);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iC != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iC;
                    o0.g.c(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            g gVar3 = this.f4057d;
            g0.a(this, z7 ? null : gVar3 != null ? gVar3.f4094d : null);
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
        public boolean f4071a;

        public b() {
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void a(ViewPager viewPager, n1.a aVar, n1.a aVar2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.M == viewPager) {
                tabLayout.E(aVar2, this.f4071a);
            }
        }

        public void b(boolean z7) {
            this.f4071a = z7;
        }
    }

    @Deprecated
    public interface c<T extends g> {
        void a(T t7);

        void b(T t7);

        void c(T t7);
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

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4074d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Paint f4075e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final GradientDrawable f4076f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4077g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f4078h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f4079i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f4080j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f4081k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public ValueAnimator f4082l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f4083m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f4084n;

        public class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ int f4086d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ int f4087e;

            public a(int i8, int i9) {
                this.f4086d = i8;
                this.f4087e = i9;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                f fVar = f.this;
                fVar.f(j5.a.b(fVar.f4083m, this.f4086d, animatedFraction), j5.a.b(f.this.f4084n, this.f4087e, animatedFraction));
            }
        }

        public class b extends AnimatorListenerAdapter {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ int f4089d;

            public b(int i8) {
                this.f4089d = i8;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                f fVar = f.this;
                fVar.f4077g = this.f4089d;
                fVar.f4078h = 0.0f;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                f.this.f4077g = this.f4089d;
            }
        }

        public f(Context context) {
            super(context);
            this.f4077g = -1;
            this.f4079i = -1;
            this.f4080j = -1;
            this.f4081k = -1;
            this.f4083m = -1;
            this.f4084n = -1;
            setWillNotDraw(false);
            this.f4075e = new Paint();
            this.f4076f = new GradientDrawable();
        }

        public void c(int i8, int i9) {
            ValueAnimator valueAnimator = this.f4082l;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.f4082l.cancel();
            }
            k(true, i8, i9);
        }

        public final void d(TabView tabView, RectF rectF) {
            int contentWidth = tabView.getContentWidth();
            int iC = (int) p.c(getContext(), 24);
            if (contentWidth < iC) {
                contentWidth = iC;
            }
            int left = (tabView.getLeft() + tabView.getRight()) / 2;
            int i8 = contentWidth / 2;
            rectF.set(left - i8, 0.0f, left + i8, 0.0f);
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            Drawable drawable = TabLayout.this.f4048r;
            int height = 0;
            int intrinsicHeight = drawable != null ? drawable.getIntrinsicHeight() : 0;
            int i8 = this.f4074d;
            if (i8 >= 0) {
                intrinsicHeight = i8;
            }
            int i9 = TabLayout.this.D;
            if (i9 == 0) {
                height = getHeight() - intrinsicHeight;
                intrinsicHeight = getHeight();
            } else if (i9 == 1) {
                height = (getHeight() - intrinsicHeight) / 2;
                intrinsicHeight = (getHeight() + intrinsicHeight) / 2;
            } else if (i9 != 2) {
                intrinsicHeight = i9 != 3 ? 0 : getHeight();
            }
            int i10 = this.f4080j;
            if (i10 >= 0 && this.f4081k > i10) {
                Drawable drawable2 = TabLayout.this.f4048r;
                if (drawable2 == null) {
                    drawable2 = this.f4076f;
                }
                Drawable drawableMutate = h0.a.r(drawable2).mutate();
                drawableMutate.setBounds(this.f4080j, height, this.f4081k, intrinsicHeight);
                Paint paint = this.f4075e;
                if (paint != null) {
                    if (Build.VERSION.SDK_INT == 21) {
                        drawableMutate.setColorFilter(paint.getColor(), PorterDuff.Mode.SRC_IN);
                    } else {
                        h0.a.n(drawableMutate, paint.getColor());
                    }
                }
                drawableMutate.draw(canvas);
            }
            super.draw(canvas);
        }

        public boolean e() {
            int childCount = getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                if (getChildAt(i8).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        public void f(int i8, int i9) {
            if (i8 == this.f4080j && i9 == this.f4081k) {
                return;
            }
            this.f4080j = i8;
            this.f4081k = i9;
            t.b0(this);
        }

        public void g(int i8, float f8) {
            ValueAnimator valueAnimator = this.f4082l;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.f4082l.cancel();
            }
            this.f4077g = i8;
            this.f4078h = f8;
            j();
        }

        public void h(int i8) {
            if (this.f4075e.getColor() != i8) {
                this.f4075e.setColor(i8);
                t.b0(this);
            }
        }

        public void i(int i8) {
            if (this.f4074d != i8) {
                this.f4074d = i8;
                t.b0(this);
            }
        }

        public final void j() {
            int left;
            int right;
            View childAt = getChildAt(this.f4077g);
            if (childAt == null || childAt.getWidth() <= 0) {
                left = -1;
                right = -1;
            } else {
                left = childAt.getLeft();
                right = childAt.getRight();
                TabLayout tabLayout = TabLayout.this;
                if (!tabLayout.G && (childAt instanceof TabView)) {
                    d((TabView) childAt, tabLayout.f4038h);
                    left = (int) TabLayout.this.f4038h.left;
                    right = (int) TabLayout.this.f4038h.right;
                }
                if (this.f4078h > 0.0f && this.f4077g < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.f4077g + 1);
                    int left2 = childAt2.getLeft();
                    int right2 = childAt2.getRight();
                    TabLayout tabLayout2 = TabLayout.this;
                    if (!tabLayout2.G && (childAt2 instanceof TabView)) {
                        d((TabView) childAt2, tabLayout2.f4038h);
                        left2 = (int) TabLayout.this.f4038h.left;
                        right2 = (int) TabLayout.this.f4038h.right;
                    }
                    float f8 = this.f4078h;
                    left = (int) ((left2 * f8) + ((1.0f - f8) * left));
                    right = (int) ((right2 * f8) + ((1.0f - f8) * right));
                }
            }
            f(left, right);
        }

        public final void k(boolean z7, int i8, int i9) {
            View childAt = getChildAt(i8);
            if (childAt == null) {
                j();
                return;
            }
            int left = childAt.getLeft();
            int right = childAt.getRight();
            TabLayout tabLayout = TabLayout.this;
            if (!tabLayout.G && (childAt instanceof TabView)) {
                d((TabView) childAt, tabLayout.f4038h);
                left = (int) TabLayout.this.f4038h.left;
                right = (int) TabLayout.this.f4038h.right;
            }
            int i10 = this.f4080j;
            int i11 = this.f4081k;
            if (i10 == left && i11 == right) {
                return;
            }
            if (z7) {
                this.f4083m = i10;
                this.f4084n = i11;
            }
            a aVar = new a(left, right);
            if (!z7) {
                this.f4082l.removeAllUpdateListeners();
                this.f4082l.addUpdateListener(aVar);
                return;
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            this.f4082l = valueAnimator;
            valueAnimator.setInterpolator(j5.a.f5579b);
            valueAnimator.setDuration(i9);
            valueAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimator.addUpdateListener(aVar);
            valueAnimator.addListener(new b(i8));
            valueAnimator.start();
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
            super.onLayout(z7, i8, i9, i10, i11);
            ValueAnimator valueAnimator = this.f4082l;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                j();
            } else {
                k(false, this.f4077g, -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i8, int i9) {
            super.onMeasure(i8, i9);
            if (View.MeasureSpec.getMode(i8) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            boolean z7 = true;
            if (tabLayout.B == 1 || tabLayout.E == 2) {
                int childCount = getChildCount();
                int iMax = 0;
                for (int i10 = 0; i10 < childCount; i10++) {
                    View childAt = getChildAt(i10);
                    if (childAt.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount <= getMeasuredWidth() - (((int) p.c(getContext(), 16)) * 2)) {
                    boolean z8 = false;
                    for (int i11 = 0; i11 < childCount; i11++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i11).getLayoutParams();
                        if (layoutParams.width != iMax || layoutParams.weight != 0.0f) {
                            layoutParams.width = iMax;
                            layoutParams.weight = 0.0f;
                            z8 = true;
                        }
                    }
                    z7 = z8;
                } else {
                    TabLayout tabLayout2 = TabLayout.this;
                    tabLayout2.B = 0;
                    tabLayout2.L(false);
                }
                if (z7) {
                    super.onMeasure(i8, i9);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i8) {
            super.onRtlPropertiesChanged(i8);
            if (Build.VERSION.SDK_INT >= 23 || this.f4079i == i8) {
                return;
            }
            requestLayout();
            this.f4079i = i8;
        }
    }

    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4091a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Drawable f4092b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public CharSequence f4093c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public CharSequence f4094d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f4096f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public TabLayout f4098h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public TabView f4099i;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4095e = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4097g = 1;

        public View d() {
            return this.f4096f;
        }

        public Drawable e() {
            return this.f4092b;
        }

        public int f() {
            return this.f4095e;
        }

        public int g() {
            return this.f4097g;
        }

        public CharSequence h() {
            return this.f4093c;
        }

        public boolean i() {
            TabLayout tabLayout = this.f4098h;
            if (tabLayout != null) {
                return tabLayout.getSelectedTabPosition() == this.f4095e;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public void j() {
            this.f4098h = null;
            this.f4099i = null;
            this.f4091a = null;
            this.f4092b = null;
            this.f4093c = null;
            this.f4094d = null;
            this.f4095e = -1;
            this.f4096f = null;
        }

        public void k() {
            TabLayout tabLayout = this.f4098h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.C(this);
        }

        public g l(CharSequence charSequence) {
            this.f4094d = charSequence;
            r();
            return this;
        }

        public g m(int i8) {
            return n(LayoutInflater.from(this.f4099i.getContext()).inflate(i8, (ViewGroup) this.f4099i, false));
        }

        public g n(View view) {
            this.f4096f = view;
            r();
            return this;
        }

        public g o(Drawable drawable) {
            this.f4092b = drawable;
            TabLayout tabLayout = this.f4098h;
            if (tabLayout.B == 1 || tabLayout.E == 2) {
                tabLayout.L(true);
            }
            r();
            if (l5.a.f6289a && this.f4099i.l() && this.f4099i.f4061h.isVisible()) {
                this.f4099i.invalidate();
            }
            return this;
        }

        public void p(int i8) {
            this.f4095e = i8;
        }

        public g q(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.f4094d) && !TextUtils.isEmpty(charSequence)) {
                this.f4099i.setContentDescription(charSequence);
            }
            this.f4093c = charSequence;
            r();
            return this;
        }

        public void r() {
            TabView tabView = this.f4099i;
            if (tabView != null) {
                tabView.t();
            }
        }
    }

    public static class h implements ViewPager.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<TabLayout> f4100a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4101b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4102c;

        public h(TabLayout tabLayout) {
            this.f4100a = new WeakReference<>(tabLayout);
        }

        public void a() {
            this.f4102c = 0;
            this.f4101b = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrollStateChanged(int i8) {
            this.f4101b = this.f4102c;
            this.f4102c = i8;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrolled(int i8, float f8, int i9) {
            TabLayout tabLayout = this.f4100a.get();
            if (tabLayout != null) {
                int i10 = this.f4102c;
                tabLayout.G(i8, f8, i10 != 2 || this.f4101b == 1, (i10 == 2 && this.f4101b == 0) ? false : true);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageSelected(int i8) {
            TabLayout tabLayout = this.f4100a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i8 || i8 >= tabLayout.getTabCount()) {
                return;
            }
            int i9 = this.f4102c;
            tabLayout.D(tabLayout.v(i8), i9 == 0 || (i9 == 2 && this.f4101b == 0));
        }
    }

    public static class i implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ViewPager f4103a;

        public i(ViewPager viewPager) {
            this.f4103a = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(g gVar) {
            this.f4103a.setCurrentItem(gVar.f());
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private int getDefaultHeight() {
        int size = this.f4036f.size();
        boolean z7 = false;
        int i8 = 0;
        while (true) {
            if (i8 < size) {
                g gVar = this.f4036f.get(i8);
                if (gVar != null && gVar.e() != null && !TextUtils.isEmpty(gVar.h())) {
                    z7 = true;
                    break;
                }
                i8++;
            } else {
                break;
            }
        }
        return (!z7 || this.F) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i8 = this.f4054x;
        if (i8 != -1) {
            return i8;
        }
        int i9 = this.E;
        if (i9 == 0 || i9 == 2) {
            return this.f4056z;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.f4039i.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    public static ColorStateList n(int i8, int i9) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i9, i8});
    }

    private void setSelectedTabView(int i8) {
        int childCount = this.f4039i.getChildCount();
        if (i8 < childCount) {
            int i9 = 0;
            while (i9 < childCount) {
                View childAt = this.f4039i.getChildAt(i9);
                boolean z7 = true;
                childAt.setSelected(i9 == i8);
                if (i9 != i8) {
                    z7 = false;
                }
                childAt.setActivated(z7);
                i9++;
            }
        }
    }

    @Deprecated
    public void A(c cVar) {
        this.J.remove(cVar);
    }

    public final void B(int i8) {
        TabView tabView = (TabView) this.f4039i.getChildAt(i8);
        this.f4039i.removeViewAt(i8);
        if (tabView != null) {
            tabView.o();
            this.S.a(tabView);
        }
        requestLayout();
    }

    public void C(g gVar) {
        D(gVar, true);
    }

    public void D(g gVar, boolean z7) {
        g gVar2 = this.f4037g;
        if (gVar2 == gVar) {
            if (gVar2 != null) {
                r(gVar);
                i(gVar.f());
                return;
            }
            return;
        }
        int iF = gVar != null ? gVar.f() : -1;
        if (z7) {
            if ((gVar2 == null || gVar2.f() == -1) && iF != -1) {
                F(iF, 0.0f, true);
            } else {
                i(iF);
            }
            if (iF != -1) {
                setSelectedTabView(iF);
            }
        }
        this.f4037g = gVar;
        if (gVar2 != null) {
            t(gVar2);
        }
        if (gVar != null) {
            s(gVar);
        }
    }

    public void E(n1.a aVar, boolean z7) {
        DataSetObserver dataSetObserver;
        n1.a aVar2 = this.N;
        if (aVar2 != null && (dataSetObserver = this.O) != null) {
            aVar2.u(dataSetObserver);
        }
        this.N = aVar;
        if (z7 && aVar != null) {
            if (this.O == null) {
                this.O = new e();
            }
            aVar.m(this.O);
        }
        x();
    }

    public void F(int i8, float f8, boolean z7) {
        G(i8, f8, z7, true);
    }

    public void G(int i8, float f8, boolean z7, boolean z8) {
        int iRound = Math.round(i8 + f8);
        if (iRound < 0 || iRound >= this.f4039i.getChildCount()) {
            return;
        }
        if (z8) {
            this.f4039i.g(i8, f8);
        }
        ValueAnimator valueAnimator = this.L;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.L.cancel();
        }
        scrollTo(l(i8, f8), 0);
        if (z7) {
            setSelectedTabView(iRound);
        }
    }

    public void H(ViewPager viewPager, boolean z7) {
        I(viewPager, z7, false);
    }

    public final void I(ViewPager viewPager, boolean z7, boolean z8) {
        ViewPager viewPager2 = this.M;
        if (viewPager2 != null) {
            h hVar = this.P;
            if (hVar != null) {
                viewPager2.J(hVar);
            }
            b bVar = this.Q;
            if (bVar != null) {
                this.M.I(bVar);
            }
        }
        c cVar = this.K;
        if (cVar != null) {
            A(cVar);
            this.K = null;
        }
        if (viewPager != null) {
            this.M = viewPager;
            if (this.P == null) {
                this.P = new h(this);
            }
            this.P.a();
            viewPager.c(this.P);
            i iVar = new i(viewPager);
            this.K = iVar;
            b(iVar);
            n1.a adapter = viewPager.getAdapter();
            if (adapter != null) {
                E(adapter, z7);
            }
            if (this.Q == null) {
                this.Q = new b();
            }
            this.Q.b(z7);
            viewPager.b(this.Q);
            F(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.M = null;
            E(null, false);
        }
        this.R = z8;
    }

    public final void J() {
        int size = this.f4036f.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.f4036f.get(i8).r();
        }
    }

    public final void K(LinearLayout.LayoutParams layoutParams) {
        if (this.E == 1 && this.B == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    public void L(boolean z7) {
        for (int i8 = 0; i8 < this.f4039i.getChildCount(); i8++) {
            View childAt = this.f4039i.getChildAt(i8);
            childAt.setMinimumWidth(getTabMinWidth());
            K((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z7) {
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
        if (this.J.contains(cVar)) {
            return;
        }
        this.J.add(cVar);
    }

    public void c(g gVar) {
        e(gVar, this.f4036f.isEmpty());
    }

    public void d(g gVar, int i8, boolean z7) {
        if (gVar.f4098h != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        m(gVar, i8);
        g(gVar);
        if (z7) {
            gVar.k();
        }
    }

    public void e(g gVar, boolean z7) {
        d(gVar, this.f4036f.size(), z7);
    }

    public final void f(TabItem tabItem) {
        g gVarW = w();
        CharSequence charSequence = tabItem.f4031d;
        if (charSequence != null) {
            gVarW.q(charSequence);
        }
        Drawable drawable = tabItem.f4032e;
        if (drawable != null) {
            gVarW.o(drawable);
        }
        int i8 = tabItem.f4033f;
        if (i8 != 0) {
            gVarW.m(i8);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            gVarW.l(tabItem.getContentDescription());
        }
        c(gVarW);
    }

    public final void g(g gVar) {
        TabView tabView = gVar.f4099i;
        tabView.setSelected(false);
        tabView.setActivated(false);
        this.f4039i.addView(tabView, gVar.f(), o());
    }

    public int getSelectedTabPosition() {
        g gVar = this.f4037g;
        if (gVar != null) {
            return gVar.f();
        }
        return -1;
    }

    public int getTabCount() {
        return this.f4036f.size();
    }

    public int getTabGravity() {
        return this.B;
    }

    public ColorStateList getTabIconTint() {
        return this.f4046p;
    }

    public int getTabIndicatorGravity() {
        return this.D;
    }

    public int getTabMaxWidth() {
        return this.f4053w;
    }

    public int getTabMode() {
        return this.E;
    }

    public ColorStateList getTabRippleColor() {
        return this.f4047q;
    }

    public Drawable getTabSelectedIndicator() {
        return this.f4048r;
    }

    public ColorStateList getTabTextColors() {
        return this.f4045o;
    }

    public final void h(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        f((TabItem) view);
    }

    public final void i(int i8) {
        if (i8 == -1) {
            return;
        }
        if (getWindowToken() == null || !t.Q(this) || this.f4039i.e()) {
            F(i8, 0.0f, true);
            return;
        }
        int scrollX = getScrollX();
        int iL = l(i8, 0.0f);
        if (scrollX != iL) {
            u();
            this.L.setIntValues(scrollX, iL);
            this.L.start();
        }
        this.f4039i.c(i8, this.C);
    }

    public final void j(int i8) {
        if (i8 == 0) {
            Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        } else if (i8 == 1) {
            this.f4039i.setGravity(1);
            return;
        } else if (i8 != 2) {
            return;
        }
        this.f4039i.setGravity(8388611);
    }

    public final void k() {
        int i8 = this.E;
        t.z0(this.f4039i, (i8 == 0 || i8 == 2) ? Math.max(0, this.A - this.f4040j) : 0, 0, 0, 0);
        int i9 = this.E;
        if (i9 == 0) {
            j(this.B);
        } else if (i9 == 1 || i9 == 2) {
            if (this.B == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            this.f4039i.setGravity(1);
        }
        L(true);
    }

    public final int l(int i8, float f8) {
        int i9 = this.E;
        if (i9 != 0 && i9 != 2) {
            return 0;
        }
        View childAt = this.f4039i.getChildAt(i8);
        int i10 = i8 + 1;
        View childAt2 = i10 < this.f4039i.getChildCount() ? this.f4039i.getChildAt(i10) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i11 = (int) ((width + width2) * 0.5f * f8);
        return t.A(this) == 0 ? left + i11 : left - i11;
    }

    public final void m(g gVar, int i8) {
        gVar.p(i8);
        this.f4036f.add(i8, gVar);
        int size = this.f4036f.size();
        while (true) {
            i8++;
            if (i8 >= size) {
                return;
            } else {
                this.f4036f.get(i8).p(i8);
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
        e6.i.e(this);
        if (this.M == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                I((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.R) {
            setupWithViewPager(null);
            this.R = false;
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        for (int i8 = 0; i8 < this.f4039i.getChildCount(); i8++) {
            View childAt = this.f4039i.getChildAt(i8);
            if (childAt instanceof TabView) {
                ((TabView) childAt).drawBackground(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        p0.c.F0(accessibilityNodeInfo).e0(c.b.b(1, getTabCount(), false, 1));
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
            float r0 = z5.p.c(r0, r1)
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
            int r1 = r6.f4055y
            if (r1 <= 0) goto L50
            goto L5d
        L50:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = z5.p.c(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L5d:
            r6.f4053w = r1
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

    public g p() {
        g gVarB = f4035e.b();
        return gVarB == null ? new g() : gVarB;
    }

    public final TabView q(g gVar) {
        n0.e<TabView> eVar = this.S;
        TabView tabViewB = eVar != null ? eVar.b() : null;
        if (tabViewB == null) {
            tabViewB = new TabView(getContext());
        }
        tabViewB.setTab(gVar);
        tabViewB.setFocusable(true);
        tabViewB.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(gVar.f4094d)) {
            tabViewB.setContentDescription(gVar.f4093c);
        } else {
            tabViewB.setContentDescription(gVar.f4094d);
        }
        return tabViewB;
    }

    public final void r(g gVar) {
        for (int size = this.J.size() - 1; size >= 0; size--) {
            this.J.get(size).a(gVar);
        }
    }

    public final void s(g gVar) {
        for (int size = this.J.size() - 1; size >= 0; size--) {
            this.J.get(size).c(gVar);
        }
    }

    @Override // android.view.View
    public void setElevation(float f8) {
        super.setElevation(f8);
        e6.i.d(this, f8);
    }

    public void setInlineLabel(boolean z7) {
        if (this.F != z7) {
            this.F = z7;
            for (int i8 = 0; i8 < this.f4039i.getChildCount(); i8++) {
                View childAt = this.f4039i.getChildAt(i8);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).v();
                }
            }
            k();
        }
    }

    public void setInlineLabelResource(int i8) {
        setInlineLabel(getResources().getBoolean(i8));
    }

    @Deprecated
    public void setOnTabSelectedListener(d dVar) {
        setOnTabSelectedListener((c) dVar);
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        u();
        this.L.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.f4048r != drawable) {
            this.f4048r = drawable;
            t.b0(this.f4039i);
        }
    }

    public void setSelectedTabIndicatorColor(int i8) {
        this.f4039i.h(i8);
    }

    public void setSelectedTabIndicatorGravity(int i8) {
        if (this.D != i8) {
            this.D = i8;
            t.b0(this.f4039i);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i8) {
        this.f4039i.i(i8);
    }

    public void setTabGravity(int i8) {
        if (this.B != i8) {
            this.B = i8;
            k();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.f4046p != colorStateList) {
            this.f4046p = colorStateList;
            J();
        }
    }

    public void setTabIconTintResource(int i8) {
        setTabIconTint(m.a.c(getContext(), i8));
    }

    public void setTabIndicatorFullWidth(boolean z7) {
        this.G = z7;
        t.b0(this.f4039i);
    }

    public void setTabMode(int i8) {
        if (i8 != this.E) {
            this.E = i8;
            k();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.f4047q != colorStateList) {
            this.f4047q = colorStateList;
            for (int i8 = 0; i8 < this.f4039i.getChildCount(); i8++) {
                View childAt = this.f4039i.getChildAt(i8);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).u(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int i8) {
        setTabRippleColor(m.a.c(getContext(), i8));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.f4045o != colorStateList) {
            this.f4045o = colorStateList;
            J();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(n1.a aVar) {
        E(aVar, false);
    }

    public void setUnboundedRipple(boolean z7) {
        if (this.H != z7) {
            this.H = z7;
            for (int i8 = 0; i8 < this.f4039i.getChildCount(); i8++) {
                View childAt = this.f4039i.getChildAt(i8);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).u(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i8) {
        setUnboundedRipple(getResources().getBoolean(i8));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        H(viewPager, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public final void t(g gVar) {
        for (int size = this.J.size() - 1; size >= 0; size--) {
            this.J.get(size).b(gVar);
        }
    }

    public final void u() {
        if (this.L == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.L = valueAnimator;
            valueAnimator.setInterpolator(j5.a.f5579b);
            this.L.setDuration(this.C);
            this.L.addUpdateListener(new a());
        }
    }

    public g v(int i8) {
        if (i8 < 0 || i8 >= getTabCount()) {
            return null;
        }
        return this.f4036f.get(i8);
    }

    public g w() {
        g gVarP = p();
        gVarP.f4098h = this;
        gVarP.f4099i = q(gVarP);
        return gVarP;
    }

    public void x() {
        int currentItem;
        z();
        n1.a aVar = this.N;
        if (aVar != null) {
            int iE = aVar.e();
            for (int i8 = 0; i8 < iE; i8++) {
                e(w().q(this.N.g(i8)), false);
            }
            ViewPager viewPager = this.M;
            if (viewPager == null || iE <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            C(v(currentItem));
        }
    }

    public boolean y(g gVar) {
        return f4035e.a(gVar);
    }

    public void z() {
        for (int childCount = this.f4039i.getChildCount() - 1; childCount >= 0; childCount--) {
            B(childCount);
        }
        Iterator<g> it = this.f4036f.iterator();
        while (it.hasNext()) {
            g next = it.next();
            it.remove();
            next.j();
            y(next);
        }
        this.f4037g = null;
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.tabStyle);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i8) {
        h(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void setOnTabSelectedListener(c cVar) {
        c cVar2 = this.I;
        if (cVar2 != null) {
            A(cVar2);
        }
        this.I = cVar;
        if (cVar != null) {
            b(cVar);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TabLayout(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f4034d;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f4036f = new ArrayList<>();
        this.f4038h = new RectF();
        this.f4053w = Integer.MAX_VALUE;
        this.J = new ArrayList<>();
        this.S = new n0.f(12);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        f fVar = new f(context2);
        this.f4039i = fVar;
        super.addView(fVar, 0, new FrameLayout.LayoutParams(-2, -1));
        int[] iArr = R$styleable.TabLayout;
        int i10 = R$styleable.TabLayout_tabTextAppearance;
        TypedArray typedArrayH = k.h(context2, attributeSet, iArr, i8, i9, i10);
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            e6.h hVar = new e6.h();
            hVar.X(ColorStateList.valueOf(colorDrawable.getColor()));
            hVar.N(context2);
            hVar.W(t.v(this));
            t.o0(this, hVar);
        }
        fVar.i(typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabIndicatorHeight, -1));
        fVar.h(typedArrayH.getColor(R$styleable.TabLayout_tabIndicatorColor, 0));
        setSelectedTabIndicator(b6.c.d(context2, typedArrayH, R$styleable.TabLayout_tabIndicator));
        setSelectedTabIndicatorGravity(typedArrayH.getInt(R$styleable.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorFullWidth(typedArrayH.getBoolean(R$styleable.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabPadding, 0);
        this.f4043m = dimensionPixelSize;
        this.f4042l = dimensionPixelSize;
        this.f4041k = dimensionPixelSize;
        this.f4040j = dimensionPixelSize;
        this.f4040j = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.f4041k = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingTop, this.f4041k);
        this.f4042l = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingEnd, this.f4042l);
        this.f4043m = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingBottom, this.f4043m);
        int resourceId = typedArrayH.getResourceId(i10, R$style.TextAppearance_Design_Tab);
        this.f4044n = resourceId;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, androidx.appcompat.R$styleable.TextAppearance);
        try {
            this.f4050t = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R$styleable.TextAppearance_android_textSize, 0);
            this.f4045o = b6.c.a(context2, typedArrayObtainStyledAttributes, androidx.appcompat.R$styleable.TextAppearance_android_textColor);
            typedArrayObtainStyledAttributes.recycle();
            int i11 = R$styleable.TabLayout_tabTextColor;
            if (typedArrayH.hasValue(i11)) {
                this.f4045o = b6.c.a(context2, typedArrayH, i11);
            }
            int i12 = R$styleable.TabLayout_tabSelectedTextColor;
            if (typedArrayH.hasValue(i12)) {
                this.f4045o = n(this.f4045o.getDefaultColor(), typedArrayH.getColor(i12, 0));
            }
            this.f4046p = b6.c.a(context2, typedArrayH, R$styleable.TabLayout_tabIconTint);
            this.f4049s = p.i(typedArrayH.getInt(R$styleable.TabLayout_tabIconTintMode, -1), null);
            this.f4047q = b6.c.a(context2, typedArrayH, R$styleable.TabLayout_tabRippleColor);
            this.C = typedArrayH.getInt(R$styleable.TabLayout_tabIndicatorAnimationDuration, 300);
            this.f4054x = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabMinWidth, -1);
            this.f4055y = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabMaxWidth, -1);
            this.f4052v = typedArrayH.getResourceId(R$styleable.TabLayout_tabBackground, 0);
            this.A = typedArrayH.getDimensionPixelSize(R$styleable.TabLayout_tabContentStart, 0);
            this.E = typedArrayH.getInt(R$styleable.TabLayout_tabMode, 1);
            this.B = typedArrayH.getInt(R$styleable.TabLayout_tabGravity, 0);
            this.F = typedArrayH.getBoolean(R$styleable.TabLayout_tabInlineLabel, false);
            this.H = typedArrayH.getBoolean(R$styleable.TabLayout_tabUnboundedRipple, false);
            typedArrayH.recycle();
            Resources resources = getResources();
            this.f4051u = resources.getDimensionPixelSize(R$dimen.design_tab_text_size_2line);
            this.f4056z = resources.getDimensionPixelSize(R$dimen.design_tab_scrollable_min_width);
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
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        h(view);
    }

    public void setSelectedTabIndicator(int i8) {
        if (i8 != 0) {
            setSelectedTabIndicator(m.a.d(getContext(), i8));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }
}
