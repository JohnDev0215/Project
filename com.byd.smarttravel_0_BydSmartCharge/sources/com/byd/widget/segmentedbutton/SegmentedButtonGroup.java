package com.byd.widget.segmentedbutton;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
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
import c.a.d.k;
import com.byd.bydbase.R$attr;
import com.byd.bydbase.R$styleable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class SegmentedButtonGroup extends LinearLayout {
    public int A;
    public transient int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public transient int G;
    public transient int H;
    public transient boolean I;
    public transient boolean J;
    public boolean K;
    public transient boolean L;
    public transient boolean M;
    public transient Drawable N;
    public transient Interpolator O;
    public transient f P;
    public transient WeakReference<d> Q;
    public transient e R;
    public transient int S;
    public transient float T;
    public transient ValueAnimator U;
    public transient int V;
    public transient float W;
    public transient boolean a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient LinearLayout f2011b;
    public transient g b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient LinearLayout f2012c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient LinearLayout f2013d;
    public transient boolean e;
    public transient int f;
    public transient int g;
    public transient int h;
    public transient int i;
    public transient int j;
    public transient int k;
    public transient int l;
    public transient int m;
    public transient boolean n;
    public transient int o;
    public transient RectF p;
    public transient Paint q;
    public transient int r;
    public transient ArrayList<BackgroundView> s;
    public transient ArrayList<SegmentedButton> t;
    public int u;
    public transient int v;
    public transient int w;
    public int x;
    public int y;
    public int z;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2014b;

        public a(int i) {
            this.f2014b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SegmentedButtonGroup.this.R != null) {
                if (this.f2014b != SegmentedButtonGroup.this.S) {
                    SegmentedButtonGroup.this.R.a(SegmentedButtonGroup.this, this.f2014b);
                }
            } else if (SegmentedButtonGroup.this.I && SegmentedButtonGroup.this.J) {
                SegmentedButtonGroup segmentedButtonGroup = SegmentedButtonGroup.this;
                segmentedButtonGroup.u(this.f2014b, segmentedButtonGroup.w, true);
            }
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float f = SegmentedButtonGroup.this.T = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int i = (int) f;
            SegmentedButtonGroup.this.j(i, Math.abs(f - i));
            SegmentedButtonGroup.this.invalidate();
        }
    }

    @TargetApi(21)
    public class c extends ViewOutlineProvider {
        public c() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), SegmentedButtonGroup.this.A);
        }

        public /* synthetic */ c(SegmentedButtonGroup segmentedButtonGroup, a aVar) {
            this();
        }
    }

    public interface d {
        void a(View view, View view2, int i);
    }

    public interface e {
        void a(View view, int i);
    }

    public interface f {
        void a(View view, View view2, int i);
    }

    public interface g {
        void a(View view, View view2, int i);
    }

    public SegmentedButtonGroup(Context context) throws Throwable {
        super(context);
        this.e = false;
        this.n = false;
        this.o = 0;
        this.r = 0;
        this.s = new ArrayList<>();
        this.S = 0;
        this.T = 0.0f;
        this.V = 0;
        this.W = 0.0f;
        this.a0 = false;
        l(context, null);
    }

    private void setEnabledAlpha(boolean z) {
        setAlpha(!z ? 0.5f : 1.0f);
    }

    private void setRippleState(boolean z) {
        Iterator<BackgroundView> it = this.s.iterator();
        while (it.hasNext()) {
            r(it.next(), z);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (!(view instanceof SegmentedButton)) {
            super.addView(view, i, layoutParams);
            return;
        }
        SegmentedButton segmentedButton = (SegmentedButton) view;
        int i2 = this.r;
        this.r = i2 + 1;
        segmentedButton.setSelectorColor(this.u);
        segmentedButton.setSelectorRadius(this.A);
        segmentedButton.setBorderSize(this.G);
        segmentedButton.n(this.k, this.h, this.j, this.i);
        if (i2 == 0) {
            segmentedButton.f(true);
        }
        if (i2 > 0) {
            this.t.get(i2 - 1).g(false);
        }
        segmentedButton.g(true);
        segmentedButton.m(this.f, this.g);
        this.f2011b.addView(view, layoutParams);
        this.t.add(segmentedButton);
        if (this.x == i2) {
            segmentedButton.c(1.0f);
            this.S = i2;
            this.V = i2;
            float f2 = i2;
            this.T = f2;
            this.W = f2;
        }
        BackgroundView backgroundView = new BackgroundView(getContext());
        if (!this.e) {
            backgroundView.setOnClickListener(new a(i2));
        }
        r(backgroundView, this.J && this.I);
        this.f2012c.addView(backgroundView, new LinearLayout.LayoutParams(segmentedButton.getButtonWidth(), -1, segmentedButton.getWeight()));
        this.s.add(backgroundView);
        if (this.M) {
            this.f2013d.addView(new BackgroundView(getContext()), new LinearLayout.LayoutParams(segmentedButton.getButtonWidth(), -1, segmentedButton.getWeight()));
        }
    }

    public int getBackgroundColor() {
        return this.y;
    }

    public int getButtonChildCount() {
        ArrayList<SegmentedButton> arrayList = this.t;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public int getDividerColor() {
        return this.z;
    }

    @Override // android.widget.LinearLayout
    public int getDividerPadding() {
        return this.E;
    }

    public float getDividerRadius() {
        return this.F;
    }

    public int getDividerSize() {
        return this.C;
    }

    public int getFirstSelectorColor() {
        return this.m;
    }

    public Interpolator getInterpolatorSelector() {
        return this.O;
    }

    public int getPosition() {
        return this.x;
    }

    public float getRadius() {
        return this.A;
    }

    public int getRevertPosition() {
        return this.o;
    }

    public int getRippleColor() {
        return this.D;
    }

    public int getSelectorAnimation() {
        return this.v;
    }

    public int getSelectorAnimationDuration() {
        return this.w;
    }

    public int getSelectorColor() {
        return this.u;
    }

    public final void j(int i, float f2) {
        float f3 = i + f2;
        int i2 = this.V;
        float f4 = this.W;
        float f5 = i2 + f4;
        if (f3 == f5) {
            return;
        }
        int i3 = i + 1;
        if (f2 == 0.0f && f5 <= f3) {
            i3 = i - 1;
        }
        if (i2 > i && f4 > 0.0f) {
            s(i3 + 1, 1.0f);
        }
        if (this.V < i && this.W < 1.0f) {
            t(i - 1, 0.0f);
        }
        float f6 = 1.0f - f2;
        s(i3, f6);
        t(i, f6);
        this.V = i;
        this.W = f2;
    }

    public final void k(AttributeSet attributeSet) throws Throwable {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SegmentedButtonGroup);
        int i = R$styleable.SegmentedButtonGroup_sbg_dividerSize;
        this.M = typedArrayObtainStyledAttributes.hasValue(i);
        this.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(i, 0);
        this.z = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_dividerColor, -1);
        this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_dividerPadding, 0);
        this.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_dividerRadius, 0);
        this.u = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorColor, -7829368);
        this.v = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_animateSelector, 0);
        this.w = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_animateSelectorDuration, 500);
        this.A = getResources().getDimensionPixelSize(c.a.d.d.a(getContext(), R$attr.sbg_radius));
        this.B = getResources().getDimensionPixelSize(c.a.d.d.a(getContext(), R$attr.sbg_radius_btn));
        this.x = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_position, 0);
        this.y = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_backgroundColor, 0);
        this.K = false;
        int i2 = R$styleable.SegmentedButtonGroup_sbg_rippleColor;
        this.L = typedArrayObtainStyledAttributes.hasValue(i2);
        this.D = typedArrayObtainStyledAttributes.getColor(i2, -7829368);
        this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_borderSize, 0);
        this.H = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_borderColor, -16777216);
        this.N = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SegmentedButtonGroup_sbg_dividerBackgroundDrawable);
        this.J = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_sbg_enabled, true);
        this.e = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_sbg_draggable, false);
        this.f = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorGradient_startColor, 0);
        this.g = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorGradient_endColor, 0);
        this.h = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_top, this.l);
        this.i = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_bottom, this.l);
        this.k = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_left, this.l);
        this.j = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_right, this.l);
        this.m = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_firstSelectorColor, this.u);
        try {
            this.I = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_android_clickable, true);
        } catch (Exception e2) {
            k.b(e2.getMessage());
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void l(Context context, AttributeSet attributeSet) throws Throwable {
        this.l = (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
        k(attributeSet);
        setWillNotDraw(false);
        setOutlineProvider(new c(this, null));
        this.I = true;
        setRippleState(true);
        this.t = new ArrayList<>();
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.f2011b = linearLayout;
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.f2011b.setOrientation(0);
        frameLayout.addView(this.f2011b);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        this.f2012c = linearLayout2;
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f2012c.setOrientation(0);
        this.f2012c.setClickable(false);
        this.f2012c.setFocusable(false);
        LinearLayout linearLayout3 = this.f2012c;
        int i = this.G;
        linearLayout3.setPadding(i, i, i, i);
        frameLayout.addView(this.f2012c);
        LinearLayout linearLayout4 = new LinearLayout(getContext());
        this.f2013d = linearLayout4;
        linearLayout4.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f2013d.setOrientation(0);
        this.f2013d.setClickable(false);
        this.f2013d.setFocusable(false);
        frameLayout.addView(this.f2013d);
        m();
        o();
        p();
        this.p = new RectF();
        this.q = new Paint(1);
    }

    public final void m() {
        try {
            this.O = (Interpolator) new ArrayList<Class>() { // from class: com.byd.widget.segmentedbutton.SegmentedButtonGroup.2
                {
                    add(b.l.a.a.b.class);
                    add(BounceInterpolator.class);
                    add(LinearInterpolator.class);
                    add(DecelerateInterpolator.class);
                    add(CycleInterpolator.class);
                    add(AnticipateInterpolator.class);
                    add(AccelerateDecelerateInterpolator.class);
                    add(AccelerateInterpolator.class);
                    add(AnticipateOvershootInterpolator.class);
                    add(b.l.a.a.a.class);
                    add(b.l.a.a.c.class);
                    add(OvershootInterpolator.class);
                }
            }.get(this.v).newInstance();
        } catch (Exception e2) {
            Log.e("smart_charge", e2.getMessage());
        }
    }

    public void n() {
        for (SegmentedButton segmentedButton : this.t) {
            if (segmentedButton != null) {
                segmentedButton.invalidate();
            }
        }
    }

    public final void o() {
        if (isInEditMode()) {
            this.f2011b.setBackgroundColor(this.y);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        this.p.set(0.0f, 0.0f, width, height);
        this.q.setStyle(Paint.Style.FILL);
        this.q.setColor(this.y);
        RectF rectF = this.p;
        int i = this.B;
        canvas.drawRoundRect(rectF, i, i, this.q);
        int i2 = this.G;
        if (i2 > 0) {
            float f2 = i2 / 2.0f;
            float f3 = 0.0f + f2;
            this.p.set(f3, f3, width - f2, height - f2);
            this.q.setStyle(Paint.Style.STROKE);
            this.q.setColor(this.H);
            this.q.setStrokeWidth(this.G);
            RectF rectF2 = this.p;
            int i3 = this.B;
            canvas.drawRoundRect(rectF2, i3, i3, this.q);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureChildren(i, i2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1) {
            float x = ((motionEvent.getX() - ((getWidth() / this.r) / 2.0f)) * this.r) / getWidth();
            int iFloor = (int) Math.floor(((double) x) + 0.5d);
            this.W = x;
            this.T = x;
            u(iFloor, this.w, true);
        } else if (action == 2 && this.e) {
            float width = (getWidth() / this.r) / 2.0f;
            float x2 = ((motionEvent.getX() - width) * this.r) / getWidth();
            int iFloor2 = (int) Math.floor(x2);
            float f2 = x2 - iFloor2;
            if (motionEvent.getRawX() - width < getLeft()) {
                j(iFloor2 + 1, 0.0f);
            } else if (motionEvent.getRawX() + width > getRight()) {
                j(iFloor2 - 1, 1.0f);
            } else {
                j(iFloor2, f2);
            }
        }
        return true;
    }

    public final void p() {
        if (this.M) {
            this.f2013d.setShowDividers(2);
            c.a.e.c.d.b(this.f2013d, this.z, this.F, this.C, this.N);
            this.f2013d.setDividerPadding(this.E);
        }
    }

    public void q(int i, boolean z) {
        this.x = i;
        if (this.t != null) {
            if (z) {
                u(i, this.w, true);
                return;
            } else {
                u(i, 1, false);
                return;
            }
        }
        this.S = i;
        this.V = i;
        float f2 = i;
        this.T = f2;
        this.W = f2;
    }

    public final void r(View view, boolean z) {
        if (!z) {
            c.a.e.c.a.a(view, null);
            return;
        }
        if (this.L) {
            c.a.e.c.c.d(view, this.D, this.A);
            return;
        }
        if (this.K) {
            c.a.e.c.c.f(getContext(), view);
            return;
        }
        for (SegmentedButton segmentedButton : this.t) {
            if (segmentedButton instanceof SegmentedButton) {
                SegmentedButton segmentedButton2 = segmentedButton;
                if (segmentedButton2.h()) {
                    c.a.e.c.c.d(view, segmentedButton2.getRippleColor(), this.A);
                }
            }
        }
    }

    public final void s(int i, float f2) {
        if (i < 0 || i >= this.r) {
            return;
        }
        for (int i2 = 0; i2 < this.r; i2++) {
            if (i2 == i) {
                this.t.get(i2).b(f2);
                this.t.get(i2).setCheckEnable(true);
            } else {
                this.t.get(i2).setCheckEnable(false);
            }
        }
    }

    public void setAnimationEnable(boolean z) {
        this.n = z;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.y = i;
        invalidate();
    }

    public void setBorderColor(int i) {
        this.H = i;
    }

    public void setBorderSize(int i) {
        this.G = i;
    }

    public void setButtonText(String[] strArr) {
        ArrayList<SegmentedButton> arrayList;
        if (strArr == null || (arrayList = this.t) == null || strArr.length == 0 || arrayList.isEmpty()) {
            return;
        }
        for (int i = 0; i < strArr.length && i < this.t.size(); i++) {
            if (strArr[i] != null && this.t.get(i) != null) {
                this.t.get(i).setText(strArr[i]);
            }
        }
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        this.I = z;
        setRippleState(z);
    }

    public void setDivider(boolean z) {
        this.M = z;
    }

    public void setDividerColor(int i) {
        this.z = i;
        c.a.e.c.d.b(this.f2013d, i, this.F, this.C, this.N);
    }

    @Override // android.widget.LinearLayout
    public void setDividerPadding(int i) {
        this.E = i;
    }

    public void setDividerRadius(int i) {
        this.F = i;
        c.a.e.c.d.b(this.f2013d, this.z, i, this.C, this.N);
    }

    public void setDividerSize(int i) {
        this.C = i;
        c.a.e.c.d.b(this.f2013d, this.z, this.F, i, this.N);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.J = z;
        setRippleState(z);
        setEnabledAlpha(z);
    }

    public void setFirstSelectorColor(int i) {
        this.m = i;
        ArrayList<SegmentedButton> arrayList = this.t;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < this.t.size(); i2++) {
            this.t.get(i2).setFirstSelectorColor(i);
        }
    }

    public void setInterpolatorSelector(Interpolator interpolator) {
        this.O = interpolator;
    }

    public void setOnClickedButtonListener(d dVar) {
        this.Q = new WeakReference<>(dVar);
    }

    public void setOnMyClickListener(e eVar) {
        this.R = eVar;
    }

    public void setOnPositionChangedListener(f fVar) {
        this.P = fVar;
    }

    public void setOnSameButtonOnClickListener(g gVar) {
        this.b0 = gVar;
    }

    public void setPosition(int i) {
        this.o = this.V;
        this.x = i;
        if (this.t != null) {
            u(i, this.w, false);
            return;
        }
        this.S = i;
        this.V = i;
        float f2 = i;
        this.T = f2;
        this.W = f2;
    }

    public void setRadius(int i) {
        this.A = i;
    }

    public void setRipple(boolean z) {
        this.K = z;
    }

    public void setRippleColor(int i) {
        this.D = i;
    }

    public void setSelectorAnimation(int i) {
        this.v = i;
    }

    public void setSelectorAnimationDuration(int i) {
        this.w = i;
    }

    public void setSelectorColor(int i) {
        this.u = i;
    }

    public final void t(int i, float f2) {
        if (i < 0 || i >= this.r) {
            return;
        }
        for (int i2 = 0; i2 < this.r; i2++) {
            if (i2 == i) {
                this.t.get(i2).c(f2);
                this.t.get(i2).setCheckEnable(true);
            } else {
                this.t.get(i2).setCheckEnable(false);
            }
        }
    }

    public final void u(int i, int i2, boolean z) {
        v(i, 1, z, true);
    }

    public final void v(int i, int i2, boolean z, boolean z2) {
        g gVar;
        if (this.S == i && (gVar = this.b0) != null) {
            gVar.a(this, getChildAt(i), i);
        }
        if (!this.e && this.S == i) {
            if (this.a0) {
                n();
                f fVar = this.P;
                if (fVar != null) {
                    fVar.a(this, getChildAt(i), i);
                }
                this.a0 = false;
                return;
            }
            return;
        }
        ValueAnimator valueAnimator = this.U;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.U.cancel();
        }
        this.S = i;
        if (this.n) {
            if (this.U == null) {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.T, i);
                this.U = valueAnimatorOfFloat;
                valueAnimatorOfFloat.addUpdateListener(new b());
                this.U.setInterpolator(this.O);
            }
            this.U.setFloatValues(this.T, i);
            this.U.setDuration(i2);
            this.U.start();
        } else {
            j(i, this.T);
            invalidate();
        }
        int i3 = 0;
        while (i3 < this.t.size()) {
            this.t.get(i3).setTextOnSelection(i == i3);
            i3++;
        }
        WeakReference<d> weakReference = this.Q;
        if (weakReference != null && weakReference.get() != null && z) {
            this.Q.get().a(this, getChildAt(i), i);
        }
        f fVar2 = this.P;
        if (fVar2 != null && z && z2) {
            fVar2.a(this, getChildAt(i), i);
        }
        this.x = i;
    }

    public void setRippleColor(boolean z) {
        this.L = z;
    }

    public SegmentedButtonGroup(Context context, AttributeSet attributeSet) throws Throwable {
        super(context, attributeSet);
        this.e = false;
        this.n = false;
        this.o = 0;
        this.r = 0;
        this.s = new ArrayList<>();
        this.S = 0;
        this.T = 0.0f;
        this.V = 0;
        this.W = 0.0f;
        this.a0 = false;
        l(context, attributeSet);
    }

    public SegmentedButtonGroup(Context context, AttributeSet attributeSet, int i) throws Throwable {
        super(context, attributeSet, i);
        this.e = false;
        this.n = false;
        this.o = 0;
        this.r = 0;
        this.s = new ArrayList<>();
        this.S = 0;
        this.T = 0.0f;
        this.V = 0;
        this.W = 0.0f;
        this.a0 = false;
        l(context, attributeSet);
    }
}
