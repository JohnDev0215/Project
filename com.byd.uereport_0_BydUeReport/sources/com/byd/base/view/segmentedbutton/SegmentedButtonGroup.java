package com.byd.base.view.segmentedbutton;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.byd.base.R$attr;
import com.byd.base.R$styleable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class SegmentedButtonGroup extends LinearLayout implements s2.a {
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public boolean M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public Drawable R;
    public Drawable S;
    public Drawable T;
    public Interpolator U;
    public f V;
    public WeakReference<d> W;
    public e X;
    public int Y;
    public float Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public ValueAnimator f2648a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f2649b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public float f2650c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public LinearLayout f2651d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f2652d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public LinearLayout f2653e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public g f2654e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LinearLayout f2655f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2656g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2657h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2658i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2659j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2660k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2661l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2662m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2663n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f2664o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2665p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2666q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f2667r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f2668s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public RectF f2669t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Paint f2670u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f2671v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ArrayList<BackgroundView> f2672w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ArrayList<SegmentedButton> f2673x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f2674y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f2675z;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f2676d;

        public a(int i8) {
            this.f2676d = i8;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (m4.c.a(SegmentedButtonGroup.this.f2668s)) {
                return;
            }
            if (SegmentedButtonGroup.this.X != null) {
                if (this.f2676d != SegmentedButtonGroup.this.Y) {
                    SegmentedButtonGroup.this.X.a(SegmentedButtonGroup.this, this.f2676d);
                }
            } else if (SegmentedButtonGroup.this.M && SegmentedButtonGroup.this.N) {
                SegmentedButtonGroup segmentedButtonGroup = SegmentedButtonGroup.this;
                segmentedButtonGroup.u(this.f2676d, segmentedButtonGroup.A, true);
            }
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float f8 = SegmentedButtonGroup.this.Z = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int i8 = (int) f8;
            SegmentedButtonGroup.this.k(i8, Math.abs(f8 - i8));
            SegmentedButtonGroup.this.invalidate();
        }
    }

    @TargetApi(21)
    public class c extends ViewOutlineProvider {
        public c() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), SegmentedButtonGroup.this.E);
        }

        public /* synthetic */ c(SegmentedButtonGroup segmentedButtonGroup, a aVar) {
            this();
        }
    }

    public interface d {
        void a(View view, View view2, int i8);
    }

    public interface e {
        void a(View view, int i8);
    }

    public interface f {
        void a(View view, View view2, int i8);
    }

    public interface g {
        void a(View view, View view2, int i8);
    }

    public SegmentedButtonGroup(Context context) {
        super(context);
        this.f2656g = false;
        this.f2657h = false;
        this.f2666q = false;
        this.f2667r = 0;
        this.f2668s = 300;
        this.f2671v = 0;
        this.f2672w = new ArrayList<>();
        this.Y = 0;
        this.Z = 0.0f;
        this.f2649b0 = 0;
        this.f2650c0 = 0.0f;
        this.f2652d0 = false;
        m(context, null);
    }

    private void setEnabledAlpha(boolean z7) {
        setAlpha(!z7 ? 0.5f : 1.0f);
    }

    private void setRippleState(boolean z7) {
        Iterator<BackgroundView> it = this.f2672w.iterator();
        while (it.hasNext()) {
            r(it.next(), z7);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (!(view instanceof SegmentedButton)) {
            super.addView(view, i8, layoutParams);
            return;
        }
        SegmentedButton segmentedButton = (SegmentedButton) view;
        int i9 = this.f2671v;
        this.f2671v = i9 + 1;
        segmentedButton.setSelectorColor(this.f2674y);
        segmentedButton.setSelectorRadius(this.F);
        segmentedButton.setBorderSize(this.K);
        segmentedButton.n(this.f2663n, this.f2660k, this.f2662m, this.f2661l);
        if (i9 == 0) {
            segmentedButton.f(true);
        }
        if (i9 > 0) {
            this.f2673x.get(i9 - 1).g(false);
        }
        segmentedButton.g(true);
        segmentedButton.m(this.f2658i, this.f2659j);
        this.f2651d.addView(view, layoutParams);
        this.f2673x.add(segmentedButton);
        if (this.B == i9) {
            segmentedButton.c(1.0f);
            this.Y = i9;
            this.f2649b0 = i9;
            float f8 = i9;
            this.Z = f8;
            this.f2650c0 = f8;
        }
        BackgroundView backgroundView = new BackgroundView(getContext());
        if (!this.f2656g) {
            backgroundView.setOnClickListener(new a(i9));
        }
        r(backgroundView, this.N && this.M);
        this.f2653e.addView(backgroundView, new LinearLayout.LayoutParams(segmentedButton.getButtonWidth(), -1, segmentedButton.getWeight()));
        this.f2672w.add(backgroundView);
        if (this.Q) {
            this.f2655f.addView(new BackgroundView(getContext()), new LinearLayout.LayoutParams(segmentedButton.getButtonWidth(), -1, segmentedButton.getWeight()));
        }
    }

    public int getBackgroundColor() {
        return this.C;
    }

    public int getButtonChildCount() {
        ArrayList<SegmentedButton> arrayList = this.f2673x;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public int getDividerColor() {
        return this.D;
    }

    @Override // android.widget.LinearLayout
    public int getDividerPadding() {
        return this.I;
    }

    public float getDividerRadius() {
        return this.J;
    }

    public int getDividerSize() {
        return this.G;
    }

    public int getFirstSelectorColor() {
        return this.f2665p;
    }

    public Interpolator getInterpolatorSelector() {
        return this.U;
    }

    public int getPosition() {
        return this.B;
    }

    public float getRadius() {
        return this.E;
    }

    public int getRevertPosition() {
        return this.f2667r;
    }

    public int getRippleColor() {
        return this.H;
    }

    public int getSelectorAnimation() {
        return this.f2675z;
    }

    public int getSelectorAnimationDuration() {
        return this.A;
    }

    public int getSelectorColor() {
        return this.f2674y;
    }

    public final void k(int i8, float f8) {
        float f9 = i8 + f8;
        int i9 = this.f2649b0;
        float f10 = this.f2650c0;
        float f11 = i9 + f10;
        if (f9 == f11) {
            return;
        }
        int i10 = i8 + 1;
        if (f8 == 0.0f && f11 <= f9) {
            i10 = i8 - 1;
        }
        if (i9 > i8 && f10 > 0.0f) {
            s(i10 + 1, 1.0f);
        }
        if (this.f2649b0 < i8 && this.f2650c0 < 1.0f) {
            t(i8 - 1, 0.0f);
        }
        float f12 = 1.0f - f8;
        s(i10, f12);
        t(i8, f12);
        this.f2649b0 = i8;
        this.f2650c0 = f8;
    }

    public final void l(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SegmentedButtonGroup);
        int i8 = R$styleable.SegmentedButtonGroup_sbg_dividerSize;
        this.Q = typedArrayObtainStyledAttributes.hasValue(i8);
        this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(i8, 0);
        this.D = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_dividerColor, -1);
        this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_dividerPadding, 0);
        this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_dividerRadius, 0);
        this.f2674y = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorColor, -7829368);
        this.f2675z = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_animateSelector, 0);
        this.A = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_animateSelectorDuration, 500);
        this.E = getResources().getDimensionPixelSize(q2.a.a(getContext(), R$attr.sbg_radius));
        this.F = getResources().getDimensionPixelSize(q2.a.a(getContext(), R$attr.sbg_button_selected_radius));
        this.B = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_position, 0);
        this.C = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_backgroundColor, 0);
        this.O = false;
        int i9 = R$styleable.SegmentedButtonGroup_sbg_rippleColor;
        this.P = typedArrayObtainStyledAttributes.hasValue(i9);
        this.H = typedArrayObtainStyledAttributes.getColor(i9, -7829368);
        this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_borderSize, 0);
        this.L = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_borderColor, -16777216);
        this.R = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SegmentedButtonGroup_sbg_backgroundDrawable);
        this.S = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SegmentedButtonGroup_sbg_selectorBackgroundDrawable);
        this.T = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SegmentedButtonGroup_sbg_dividerBackgroundDrawable);
        this.N = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_sbg_enabled, true);
        this.f2656g = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_sbg_draggable, false);
        this.f2658i = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorGradient_startColor, 0);
        this.f2659j = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorGradient_endColor, 0);
        this.f2660k = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_top, this.f2664o);
        this.f2661l = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_bottom, this.f2664o);
        this.f2663n = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_left, this.f2664o);
        this.f2662m = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_right, this.f2664o);
        this.f2665p = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_firstSelectorColor, this.f2674y);
        this.f2668s = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_clickTime, 200);
        try {
            this.M = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_android_clickable, true);
        } catch (Exception e8) {
            x3.a.a("SegmentedButtonGroup", e8.toString());
        }
        this.f2657h = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_sbg_autoAllocByMaxWidth, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void m(Context context, AttributeSet attributeSet) {
        setLayoutDirection(0);
        this.f2664o = (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
        l(attributeSet);
        setWillNotDraw(false);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new c(this, null));
        }
        this.M = true;
        setRippleState(true);
        this.f2673x = new ArrayList<>();
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.f2651d = linearLayout;
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.f2651d.setOrientation(0);
        frameLayout.addView(this.f2651d);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        this.f2653e = linearLayout2;
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f2653e.setOrientation(0);
        this.f2653e.setClickable(false);
        this.f2653e.setFocusable(false);
        LinearLayout linearLayout3 = this.f2653e;
        int i8 = this.K;
        linearLayout3.setPadding(i8, i8, i8, i8);
        frameLayout.addView(this.f2653e);
        LinearLayout linearLayout4 = new LinearLayout(getContext());
        this.f2655f = linearLayout4;
        linearLayout4.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f2655f.setOrientation(0);
        this.f2655f.setClickable(false);
        this.f2655f.setFocusable(false);
        frameLayout.addView(this.f2655f);
        n();
        p();
        q();
        this.f2669t = new RectF();
        this.f2670u = new Paint(1);
    }

    public final void n() {
        try {
            this.U = (Interpolator) new ArrayList<Class>() { // from class: com.byd.base.view.segmentedbutton.SegmentedButtonGroup.2
                {
                    add(w0.b.class);
                    add(BounceInterpolator.class);
                    add(LinearInterpolator.class);
                    add(DecelerateInterpolator.class);
                    add(CycleInterpolator.class);
                    add(AnticipateInterpolator.class);
                    add(AccelerateDecelerateInterpolator.class);
                    add(AccelerateInterpolator.class);
                    add(AnticipateOvershootInterpolator.class);
                    add(w0.a.class);
                    add(w0.c.class);
                    add(OvershootInterpolator.class);
                }
            }.get(this.f2675z).newInstance();
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    public void o(int i8) {
        ArrayList<BackgroundView> arrayList;
        if (this.f2671v <= 0 || (arrayList = this.f2672w) == null || this.f2673x == null || this.f2653e == null || arrayList.size() <= i8 || this.f2673x.size() <= i8 || this.f2653e.getChildCount() <= i8) {
            return;
        }
        this.f2671v--;
        this.f2672w.remove(i8);
        this.f2653e.removeViewAt(i8);
        this.f2673x.remove(i8);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        this.f2669t.set(0.0f, 0.0f, width, height);
        this.f2670u.setStyle(Paint.Style.FILL);
        this.f2670u.setColor(this.C);
        RectF rectF = this.f2669t;
        int i8 = this.E;
        canvas.drawRoundRect(rectF, i8, i8, this.f2670u);
        int i9 = this.K;
        if (i9 > 0) {
            float f8 = i9 / 2.0f;
            float f9 = 0.0f + f8;
            this.f2669t.set(f9, f9, width - f8, height - f8);
            this.f2670u.setStyle(Paint.Style.STROKE);
            this.f2670u.setColor(this.L);
            this.f2670u.setStrokeWidth(this.K);
            RectF rectF2 = this.f2669t;
            int i10 = this.E;
            canvas.drawRoundRect(rectF2, i10, i10, this.f2670u);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        ArrayList<SegmentedButton> arrayList;
        super.onLayout(z7, i8, i9, i10, i11);
        if (!this.f2657h || (arrayList = this.f2673x) == null || arrayList.isEmpty()) {
            return;
        }
        Iterator<SegmentedButton> it = this.f2673x.iterator();
        int i12 = 0;
        while (it.hasNext()) {
            int width = it.next().getWidth();
            x3.a.a("SegmentedButtonGroup", "-----onLayout----w: " + width);
            if (i12 < width) {
                i12 = width;
            }
        }
        x3.a.a("SegmentedButtonGroup", "-----onLayout----wMax: " + i12);
        for (SegmentedButton segmentedButton : this.f2673x) {
            if (segmentedButton.getLayoutParams().width != i12) {
                segmentedButton.getLayoutParams().width = i12;
                segmentedButton.requestLayout();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        if (View.MeasureSpec.getMode(i9) != 0) {
            super.onMeasure(i8, i9);
            measureChildren(i8, i9);
        } else {
            measureChildren(i8, i9);
            setMeasuredDimension(i8, i9);
            super.onMeasure(i8, i9);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1) {
            float x7 = ((motionEvent.getX() - ((getWidth() / this.f2671v) / 2.0f)) * this.f2671v) / getWidth();
            int iFloor = (int) Math.floor(((double) x7) + 0.5d);
            this.f2650c0 = x7;
            this.Z = x7;
            u(iFloor, this.A, true);
        } else if (action == 2 && this.f2656g) {
            float width = (getWidth() / this.f2671v) / 2.0f;
            float x8 = ((motionEvent.getX() - width) * this.f2671v) / getWidth();
            int iFloor2 = (int) Math.floor(x8);
            float f8 = x8 - iFloor2;
            if (motionEvent.getRawX() - width < getLeft()) {
                k(iFloor2 + 1, 0.0f);
            } else if (motionEvent.getRawX() + width > getRight()) {
                k(iFloor2 - 1, 1.0f);
            } else {
                k(iFloor2, f8);
            }
        }
        return true;
    }

    public final void p() {
        if (isInEditMode()) {
            this.f2651d.setBackgroundColor(this.C);
        }
    }

    public final void q() {
        if (this.Q) {
            this.f2655f.setShowDividers(2);
            t2.d.b(this.f2655f, this.D, this.J, this.G, this.T);
            if (Build.VERSION.SDK_INT >= 14) {
                this.f2655f.setDividerPadding(this.I);
            }
        }
    }

    public final void r(View view, boolean z7) {
        if (!z7) {
            t2.a.a(view, null);
            return;
        }
        if (this.P) {
            t2.c.f(view, this.H, this.E);
            return;
        }
        if (this.O) {
            t2.c.h(getContext(), view);
            return;
        }
        for (SegmentedButton segmentedButton : this.f2673x) {
            if (segmentedButton instanceof SegmentedButton) {
                SegmentedButton segmentedButton2 = segmentedButton;
                if (segmentedButton2.h()) {
                    t2.c.f(view, segmentedButton2.getRippleColor(), this.E);
                }
            }
        }
    }

    public final void s(int i8, float f8) {
        if (i8 < 0 || i8 >= this.f2671v) {
            return;
        }
        for (int i9 = 0; i9 < this.f2671v; i9++) {
            if (i9 == i8) {
                this.f2673x.get(i9).b(f8);
                this.f2673x.get(i9).setCheckEnable(true);
            } else {
                this.f2673x.get(i9).setCheckEnable(false);
            }
        }
    }

    public void setAnimationEnable(boolean z7) {
        this.f2666q = z7;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i8) {
        this.C = i8;
        invalidate();
    }

    public void setBorderColor(int i8) {
        this.L = i8;
    }

    public void setBorderSize(int i8) {
        this.K = i8;
    }

    public void setButtonText(String[] strArr) {
        ArrayList<SegmentedButton> arrayList;
        if (strArr == null || (arrayList = this.f2673x) == null || strArr.length == 0 || arrayList.isEmpty()) {
            return;
        }
        for (int i8 = 0; i8 < strArr.length && i8 < this.f2673x.size(); i8++) {
            if (strArr[i8] != null && this.f2673x.get(i8) != null) {
                this.f2673x.get(i8).setText(strArr[i8]);
            }
        }
    }

    public void setClickTime(int i8) {
        this.f2668s = i8;
    }

    @Override // android.view.View
    public void setClickable(boolean z7) {
        this.M = z7;
        setRippleState(z7);
    }

    public void setDivider(boolean z7) {
        this.Q = z7;
    }

    public void setDividerColor(int i8) {
        this.D = i8;
        t2.d.b(this.f2655f, i8, this.J, this.G, this.T);
    }

    @Override // android.widget.LinearLayout
    public void setDividerPadding(int i8) {
        this.I = i8;
    }

    public void setDividerRadius(int i8) {
        this.J = i8;
        t2.d.b(this.f2655f, this.D, i8, this.G, this.T);
    }

    public void setDividerSize(int i8) {
        this.G = i8;
        t2.d.b(this.f2655f, this.D, this.J, i8, this.T);
    }

    @Override // android.view.View
    public void setEnabled(boolean z7) {
        this.N = z7;
        setRippleState(z7);
        setEnabledAlpha(z7);
        setViewSoundEffects(z7);
    }

    public void setFirstSelectorColor(int i8) {
        this.f2665p = i8;
        ArrayList<SegmentedButton> arrayList = this.f2673x;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        for (int i9 = 0; i9 < this.f2673x.size(); i9++) {
            this.f2673x.get(i9).setFirstSelectorColor(i8);
        }
    }

    public void setInterpolatorSelector(Interpolator interpolator) {
        this.U = interpolator;
    }

    @Override // s2.a
    public void setInvalid(boolean z7) {
        for (SegmentedButton segmentedButton : this.f2673x) {
            if (segmentedButton != null) {
                segmentedButton.setInvalid(z7);
            }
        }
    }

    public void setOnClickedButtonListener(d dVar) {
        this.W = new WeakReference<>(dVar);
    }

    public void setOnMyClickListener(e eVar) {
        this.X = eVar;
    }

    public void setOnPositionChangedListener(f fVar) {
        this.V = fVar;
    }

    public void setOnSameButtonOnClickListener(g gVar) {
        this.f2654e0 = gVar;
    }

    public void setPosition(int i8) {
        this.f2667r = this.f2649b0;
        this.B = i8;
        if (this.f2673x != null) {
            u(i8, this.A, false);
            return;
        }
        this.Y = i8;
        this.f2649b0 = i8;
        float f8 = i8;
        this.Z = f8;
        this.f2650c0 = f8;
    }

    public void setRadius(int i8) {
        this.E = i8;
    }

    public void setRipple(boolean z7) {
        this.O = z7;
    }

    public void setRippleColor(int i8) {
        this.H = i8;
    }

    public void setSelectorAnimation(int i8) {
        this.f2675z = i8;
    }

    public void setSelectorAnimationDuration(int i8) {
        this.A = i8;
    }

    public void setSelectorColor(int i8) {
        this.f2674y = i8;
    }

    public void setViewSoundEffects(boolean z7) {
        LinearLayout linearLayout = this.f2653e;
        if (linearLayout != null) {
            int childCount = linearLayout.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                this.f2653e.getChildAt(i8).setSoundEffectsEnabled(z7);
            }
        }
    }

    public final void t(int i8, float f8) {
        if (i8 < 0 || i8 >= this.f2671v) {
            return;
        }
        for (int i9 = 0; i9 < this.f2671v; i9++) {
            if (i9 == i8) {
                this.f2673x.get(i9).c(f8);
                this.f2673x.get(i9).setCheckEnable(true);
            } else {
                this.f2673x.get(i9).setCheckEnable(false);
            }
        }
    }

    public final void u(int i8, int i9, boolean z7) {
        v(i8, 1, z7, true);
    }

    public final void v(int i8, int i9, boolean z7, boolean z8) {
        g gVar;
        if (this.Y == i8 && (gVar = this.f2654e0) != null && z8) {
            gVar.a(this, getChildAt(i8), i8);
        }
        if (!this.f2656g && this.Y == i8) {
            if (this.f2652d0) {
                f fVar = this.V;
                if (fVar != null) {
                    fVar.a(this, getChildAt(i8), i8);
                }
                this.f2652d0 = false;
                return;
            }
            return;
        }
        ValueAnimator valueAnimator = this.f2648a0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.f2648a0.cancel();
        }
        this.Y = i8;
        if (this.f2666q) {
            if (this.f2648a0 == null) {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.Z, i8);
                this.f2648a0 = valueAnimatorOfFloat;
                valueAnimatorOfFloat.addUpdateListener(new b());
                this.f2648a0.setInterpolator(this.U);
            }
            this.f2648a0.setFloatValues(this.Z, i8);
            this.f2648a0.setDuration(i9);
            this.f2648a0.start();
        } else {
            k(i8, this.Z);
            invalidate();
        }
        int i10 = 0;
        while (i10 < this.f2673x.size()) {
            this.f2673x.get(i10).setTextOnSelection(i8 == i10);
            i10++;
        }
        WeakReference<d> weakReference = this.W;
        if (weakReference != null && weakReference.get() != null && z7 && z8) {
            this.W.get().a(this, getChildAt(i8), i8);
        }
        f fVar2 = this.V;
        if (fVar2 != null && z7 && z8) {
            fVar2.a(this, getChildAt(i8), i8);
        }
        this.B = i8;
    }

    public void setRippleColor(boolean z7) {
        this.P = z7;
    }

    public SegmentedButtonGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2656g = false;
        this.f2657h = false;
        this.f2666q = false;
        this.f2667r = 0;
        this.f2668s = 300;
        this.f2671v = 0;
        this.f2672w = new ArrayList<>();
        this.Y = 0;
        this.Z = 0.0f;
        this.f2649b0 = 0;
        this.f2650c0 = 0.0f;
        this.f2652d0 = false;
        m(context, attributeSet);
    }

    public SegmentedButtonGroup(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2656g = false;
        this.f2657h = false;
        this.f2666q = false;
        this.f2667r = 0;
        this.f2668s = 300;
        this.f2671v = 0;
        this.f2672w = new ArrayList<>();
        this.Y = 0;
        this.Z = 0.0f;
        this.f2649b0 = 0;
        this.f2650c0 = 0.0f;
        this.f2652d0 = false;
        m(context, attributeSet);
    }
}
