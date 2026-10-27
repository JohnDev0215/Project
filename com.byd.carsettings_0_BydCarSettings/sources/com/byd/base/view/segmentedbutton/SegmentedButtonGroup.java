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
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import com.byd.base.R$attr;
import com.byd.base.R$styleable;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class SegmentedButtonGroup extends LinearLayout implements b.b.a.j.c.a {
    public int A;
    public int B;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public boolean K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public Drawable P;
    public Drawable Q;
    public Drawable R;
    public Interpolator S;
    public f T;
    public WeakReference<d> U;
    public e V;
    public int W;
    public float X;
    public ValueAnimator Y;
    public int Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public LinearLayout f2530a;
    public float a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public LinearLayout f2531b;
    public boolean b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f2532c;
    public g c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2533d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2534e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2535f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public boolean n;
    public int o;
    public int p;
    public RectF q;
    public Paint r;
    public int s;
    public ArrayList<BackgroundView> t;
    public ArrayList<SegmentedButton> u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2536a;

        public a(int i) {
            this.f2536a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.m.g.c.a(SegmentedButtonGroup.this.p)) {
                return;
            }
            if (SegmentedButtonGroup.this.V != null) {
                if (this.f2536a != SegmentedButtonGroup.this.W) {
                    SegmentedButtonGroup.this.V.s(SegmentedButtonGroup.this, this.f2536a);
                    return;
                }
                return;
            }
            b.b.m.c.a.b(SegmentedButtonGroup.this.getTag(), "SegmentedButtonGroup", "onClick", "position: " + this.f2536a);
            if (SegmentedButtonGroup.this.K && SegmentedButtonGroup.this.L) {
                SegmentedButtonGroup segmentedButtonGroup = SegmentedButtonGroup.this;
                segmentedButtonGroup.z(this.f2536a, segmentedButtonGroup.x, true);
            }
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float f2 = SegmentedButtonGroup.this.X = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int i = (int) f2;
            b.b.m.c.a.b(SegmentedButtonGroup.this.getTag(), "SegmentedButtonGroup", "toggle_onAnimationUpdate", "position: " + i + ", " + hashCode());
            SegmentedButtonGroup.this.n(i, Math.abs(f2 - ((float) i)));
            SegmentedButtonGroup.this.invalidate();
        }
    }

    @TargetApi(21)
    public class c extends ViewOutlineProvider {
        public c() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), SegmentedButtonGroup.this.B);
        }

        public /* synthetic */ c(SegmentedButtonGroup segmentedButtonGroup, a aVar) {
            this();
        }
    }

    public interface d {
        void k(View view, View view2, int i);
    }

    public interface e {
        void s(View view, int i);
    }

    public interface f {
        void onPositionChanged(View view, View view2, int i);
    }

    public interface g {
        void a(View view, View view2, int i);
    }

    public SegmentedButtonGroup(Context context) {
        super(context);
        this.f2533d = false;
        this.f2534e = false;
        this.n = false;
        this.o = 0;
        this.p = 300;
        this.s = 0;
        this.t = new ArrayList<>();
        this.W = 0;
        this.X = 0.0f;
        this.Z = 0;
        this.a0 = 0.0f;
        this.b0 = false;
        q(context, null);
    }

    private void setEnabledAlpha(boolean z) {
        setAlpha(!z ? 0.5f : 1.0f);
    }

    private void setRippleState(boolean z) {
        Iterator<BackgroundView> it = this.t.iterator();
        while (it.hasNext()) {
            w(it.next(), z);
        }
    }

    public final void A(int i, int i2, boolean z, boolean z2) {
        g gVar;
        if (this.W == i && (gVar = this.c0) != null && z2) {
            gVar.a(this, getChildAt(i), i);
        }
        if (!this.f2533d && this.W == i) {
            if (this.b0) {
                f fVar = this.T;
                if (fVar != null) {
                    fVar.onPositionChanged(this, getChildAt(i), i);
                }
                this.b0 = false;
                return;
            }
            return;
        }
        ValueAnimator valueAnimator = this.Y;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.Y.cancel();
        }
        this.W = i;
        b.b.m.c.a.b(getTag(), "SegmentedButtonGroup", "toggle", "animationEnable: " + this.n + ", position: " + i + ", " + hashCode());
        if (this.n) {
            if (this.Y == null) {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.X, i);
                this.Y = valueAnimatorOfFloat;
                valueAnimatorOfFloat.addUpdateListener(new b());
                this.Y.setInterpolator(this.S);
            }
            this.Y.setFloatValues(this.X, i);
            this.Y.setDuration(i2);
            this.Y.start();
        } else {
            n(i, this.X);
            invalidate();
        }
        int i3 = 0;
        while (i3 < this.u.size()) {
            this.u.get(i3).setTextOnSelection(i == i3);
            i3++;
        }
        WeakReference<d> weakReference = this.U;
        if (weakReference != null && weakReference.get() != null && z && z2) {
            this.U.get().k(this, getChildAt(i), i);
        }
        f fVar2 = this.T;
        if (fVar2 != null && z && z2) {
            fVar2.onPositionChanged(this, getChildAt(i), i);
        }
        this.y = i;
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
        int i2 = this.s;
        this.s = i2 + 1;
        segmentedButton.setSelectorColor(this.v);
        segmentedButton.setSelectorRadius(this.D);
        segmentedButton.setBorderSize(this.I);
        segmentedButton.setRecfPadding(this.k, this.h, this.j, this.i);
        if (i2 == 0) {
            segmentedButton.j(true);
        }
        if (i2 > 0) {
            this.u.get(i2 - 1).k(false);
        }
        segmentedButton.k(true);
        segmentedButton.setGradientColor(this.f2535f, this.g);
        this.f2530a.addView(view, layoutParams);
        this.u.add(segmentedButton);
        if (this.y == i2) {
            segmentedButton.f(1.0f);
            this.W = i2;
            this.Z = i2;
            float f2 = i2;
            this.X = f2;
            this.a0 = f2;
        }
        BackgroundView backgroundView = new BackgroundView(getContext());
        if (!this.f2533d) {
            backgroundView.setOnClickListener(new a(i2));
        }
        w(backgroundView, this.L && this.K);
        this.f2531b.addView(backgroundView, new LinearLayout.LayoutParams(segmentedButton.getButtonWidth(), -1, segmentedButton.getWeight()));
        this.t.add(backgroundView);
        if (this.O) {
            this.f2532c.addView(new BackgroundView(getContext()), new LinearLayout.LayoutParams(segmentedButton.getButtonWidth(), -1, segmentedButton.getWeight()));
        }
    }

    public int getBackgroundColor() {
        return this.z;
    }

    public int getButtonChildCount() {
        ArrayList<SegmentedButton> arrayList = this.u;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public int getDividerColor() {
        return this.A;
    }

    @Override // android.widget.LinearLayout
    public int getDividerPadding() {
        return this.G;
    }

    public float getDividerRadius() {
        return this.H;
    }

    public int getDividerSize() {
        return this.E;
    }

    public int getFirstSelectorColor() {
        return this.m;
    }

    public Interpolator getInterpolatorSelector() {
        return this.S;
    }

    public int getPosition() {
        return this.y;
    }

    public float getRadius() {
        return this.B;
    }

    public int getRevertPosition() {
        return this.o;
    }

    public int getRippleColor() {
        return this.F;
    }

    public int getSelectorAnimation() {
        return this.w;
    }

    public int getSelectorAnimationDuration() {
        return this.x;
    }

    public int getSelectorColor() {
        return this.v;
    }

    public final void n(int i, float f2) {
        float f3 = i + f2;
        float f4 = this.Z + this.a0;
        if (f3 == f4) {
            return;
        }
        b.b.m.c.a.b(getTag(), "SegmentedButtonGroup", "animateViews", "position: " + i, "lastPosition: " + this.Z + ", " + hashCode());
        int i2 = i + 1;
        if (f2 == 0.0f && f4 <= f3) {
            i2 = i - 1;
        }
        if (this.Z > i && this.a0 > 0.0f) {
            x(i2 + 1, 1.0f);
        }
        if (this.Z < i && this.a0 < 1.0f) {
            y(i - 1, 0.0f);
        }
        float f5 = 1.0f - f2;
        x(i2, f5);
        y(i, f5);
        this.Z = i;
        this.a0 = f2;
    }

    public void o() {
        this.b0 = true;
        for (SegmentedButton segmentedButton : this.u) {
            if (segmentedButton != null) {
                segmentedButton.g();
            }
        }
        setViewSoundEffects(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        b.b.m.c.a.b(getTag(), "SegmentedButtonGroup", "onAttachedToWindow", "this.position: " + this.y + ", " + hashCode());
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        b.b.m.c.a.b(getTag(), "SegmentedButtonGroup", "onDetachedFromWindow", "this.position: " + this.y + ", " + hashCode());
        super.onDetachedFromWindow();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        this.q.set(0.0f, 0.0f, width, height);
        this.r.setStyle(Paint.Style.FILL);
        this.r.setColor(this.z);
        RectF rectF = this.q;
        int i = this.B;
        canvas.drawRoundRect(rectF, i, i, this.r);
        int i2 = this.I;
        if (i2 > 0) {
            float f2 = i2 / 2.0f;
            float f3 = 0.0f + f2;
            this.q.set(f3, f3, width - f2, height - f2);
            this.r.setStyle(Paint.Style.STROKE);
            this.r.setColor(this.J);
            this.r.setStrokeWidth(this.I);
            RectF rectF2 = this.q;
            int i3 = this.B;
            canvas.drawRoundRect(rectF2, i3, i3, this.r);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ArrayList<SegmentedButton> arrayList;
        super.onLayout(z, i, i2, i3, i4);
        if (!this.f2534e || (arrayList = this.u) == null || arrayList.isEmpty()) {
            return;
        }
        Iterator<SegmentedButton> it = this.u.iterator();
        int i5 = 0;
        while (it.hasNext()) {
            int width = it.next().getWidth();
            b.b.m.c.a.a("SegmentedButtonGroup", "-----onLayout----w: " + width);
            if (i5 < width) {
                i5 = width;
            }
        }
        b.b.m.c.a.a("SegmentedButtonGroup", "-----onLayout----wMax: " + i5);
        for (SegmentedButton segmentedButton : this.u) {
            if (segmentedButton.getLayoutParams().width != i5) {
                segmentedButton.getLayoutParams().width = i5;
                segmentedButton.requestLayout();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i2) != 0) {
            super.onMeasure(i, i2);
            measureChildren(i, i2);
        } else {
            measureChildren(i, i2);
            setMeasuredDimension(i, i2);
            super.onMeasure(i, i2);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1) {
            float x = ((motionEvent.getX() - ((getWidth() / this.s) / 2.0f)) * this.s) / getWidth();
            int iFloor = (int) Math.floor(((double) x) + 0.5d);
            this.a0 = x;
            this.X = x;
            z(iFloor, this.x, true);
        } else if (action == 2 && this.f2533d) {
            float width = (getWidth() / this.s) / 2.0f;
            float x2 = ((motionEvent.getX() - width) * this.s) / getWidth();
            int iFloor2 = (int) Math.floor(x2);
            float f2 = x2 - iFloor2;
            if (motionEvent.getRawX() - width < getLeft()) {
                n(iFloor2 + 1, 0.0f);
            } else if (motionEvent.getRawX() + width > getRight()) {
                n(iFloor2 - 1, 1.0f);
            } else {
                n(iFloor2, f2);
            }
        }
        return true;
    }

    public final void p(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SegmentedButtonGroup);
        int i = R$styleable.SegmentedButtonGroup_sbg_dividerSize;
        this.O = typedArrayObtainStyledAttributes.hasValue(i);
        this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(i, 0);
        this.A = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_dividerColor, -1);
        this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_dividerPadding, 0);
        this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_dividerRadius, 0);
        this.v = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorColor, -7829368);
        this.w = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_animateSelector, 0);
        this.x = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_animateSelectorDuration, 500);
        this.B = getResources().getDimensionPixelSize(b.b.a.h.b.a(getContext(), R$attr.sbg_radius));
        this.D = getResources().getDimensionPixelSize(b.b.a.h.b.a(getContext(), R$attr.sbg_button_selected_radius));
        this.y = 0;
        this.z = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_backgroundColor, 0);
        this.M = false;
        int i2 = R$styleable.SegmentedButtonGroup_sbg_rippleColor;
        this.N = typedArrayObtainStyledAttributes.hasValue(i2);
        this.F = typedArrayObtainStyledAttributes.getColor(i2, -7829368);
        this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_borderSize, 0);
        this.J = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_borderColor, ViewCompat.MEASURED_STATE_MASK);
        this.P = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SegmentedButtonGroup_sbg_backgroundDrawable);
        this.Q = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SegmentedButtonGroup_sbg_selectorBackgroundDrawable);
        this.R = typedArrayObtainStyledAttributes.getDrawable(R$styleable.SegmentedButtonGroup_sbg_dividerBackgroundDrawable);
        this.L = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_sbg_enabled, true);
        this.f2533d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_sbg_draggable, false);
        this.f2535f = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorGradient_startColor, 0);
        this.g = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorGradient_endColor, 0);
        this.h = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_top, this.l);
        this.i = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_bottom, this.l);
        this.k = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_left, this.l);
        this.j = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_right, this.l);
        this.m = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_firstSelectorColor, this.v);
        this.p = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_clickTime, 200);
        try {
            this.K = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_android_clickable, true);
        } catch (Exception e2) {
            b.b.m.c.a.a("SegmentedButtonGroup", e2.toString());
        }
        this.f2534e = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_sbg_autoAllocByMaxWidth, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void q(Context context, AttributeSet attributeSet) {
        setLayoutDirection(0);
        this.l = (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
        p(attributeSet);
        setWillNotDraw(false);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new c(this, null));
        }
        this.K = true;
        setRippleState(true);
        this.u = new ArrayList<>();
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.f2530a = linearLayout;
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.f2530a.setOrientation(0);
        frameLayout.addView(this.f2530a);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        this.f2531b = linearLayout2;
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f2531b.setOrientation(0);
        this.f2531b.setClickable(false);
        this.f2531b.setFocusable(false);
        LinearLayout linearLayout3 = this.f2531b;
        int i = this.I;
        linearLayout3.setPadding(i, i, i, i);
        frameLayout.addView(this.f2531b);
        LinearLayout linearLayout4 = new LinearLayout(getContext());
        this.f2532c = linearLayout4;
        linearLayout4.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f2532c.setOrientation(0);
        this.f2532c.setClickable(false);
        this.f2532c.setFocusable(false);
        frameLayout.addView(this.f2532c);
        r();
        u();
        v();
        this.q = new RectF();
        this.r = new Paint(1);
    }

    public final void r() {
        try {
            this.S = (Interpolator) new ArrayList<Class>() { // from class: com.byd.base.view.segmentedbutton.SegmentedButtonGroup.2
                {
                    add(FastOutSlowInInterpolator.class);
                    add(BounceInterpolator.class);
                    add(LinearInterpolator.class);
                    add(DecelerateInterpolator.class);
                    add(CycleInterpolator.class);
                    add(AnticipateInterpolator.class);
                    add(AccelerateDecelerateInterpolator.class);
                    add(AccelerateInterpolator.class);
                    add(AnticipateOvershootInterpolator.class);
                    add(FastOutLinearInInterpolator.class);
                    add(LinearOutSlowInInterpolator.class);
                    add(OvershootInterpolator.class);
                }
            }.get(this.w).newInstance();
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    public void s() {
        for (SegmentedButton segmentedButton : this.u) {
            if (segmentedButton != null) {
                segmentedButton.invalidate();
            }
        }
        setViewSoundEffects(true);
    }

    public void setAnimationEnable(boolean z) {
        this.n = z;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.z = i;
        invalidate();
    }

    public void setBorderColor(int i) {
        this.J = i;
    }

    public void setBorderSize(int i) {
        this.I = i;
    }

    public void setButtonText(String[] strArr) {
        ArrayList<SegmentedButton> arrayList;
        if (strArr == null || (arrayList = this.u) == null || strArr.length == 0 || arrayList.isEmpty()) {
            return;
        }
        for (int i = 0; i < strArr.length && i < this.u.size(); i++) {
            if (strArr[i] != null && this.u.get(i) != null) {
                this.u.get(i).setText(strArr[i]);
            }
        }
    }

    public void setClickTime(int i) {
        this.p = i;
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        this.K = z;
        setRippleState(z);
        setViewSoundEffects(z);
    }

    public void setDivider(boolean z) {
        this.O = z;
    }

    public void setDividerColor(int i) {
        this.A = i;
        b.b.a.j.f.d.b(this.f2532c, i, this.H, this.E, this.R);
    }

    @Override // android.widget.LinearLayout
    public void setDividerPadding(int i) {
        this.G = i;
    }

    public void setDividerRadius(int i) {
        this.H = i;
        b.b.a.j.f.d.b(this.f2532c, this.A, i, this.E, this.R);
    }

    public void setDividerSize(int i) {
        this.E = i;
        b.b.a.j.f.d.b(this.f2532c, this.A, this.H, i, this.R);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.L = z;
        setRippleState(z);
        setEnabledAlpha(z);
        setViewSoundEffects(z);
    }

    public void setFirstSelectorColor(int i) {
        this.m = i;
        ArrayList<SegmentedButton> arrayList = this.u;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < this.u.size(); i2++) {
            this.u.get(i2).setFirstSelectorColor(i);
        }
    }

    public void setInterpolatorSelector(Interpolator interpolator) {
        this.S = interpolator;
    }

    @Override // b.b.a.j.c.a
    public void setInvalid(boolean z) {
        for (SegmentedButton segmentedButton : this.u) {
            if (segmentedButton != null) {
                segmentedButton.setInvalid(z);
            }
        }
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    public void setOnClickedButtonListener(d dVar) {
        this.U = new WeakReference<>(dVar);
    }

    public void setOnMyClickListener(e eVar) {
        this.V = eVar;
    }

    public void setOnPositionChangedListener(f fVar) {
        this.T = fVar;
    }

    public void setOnSameButtonOnClickListener(g gVar) {
        this.c0 = gVar;
    }

    public void setPosition(int i) {
        this.o = this.Z;
        this.y = i;
        b.b.m.c.a.b(getTag(), "SegmentedButtonGroup", "setPosition", "this.position: " + this.y + ", " + hashCode());
        if (this.u != null) {
            z(i, this.x, false);
            return;
        }
        this.W = i;
        this.Z = i;
        float f2 = i;
        this.X = f2;
        this.a0 = f2;
    }

    public void setPositionWithOutCallback(int i, boolean z, boolean z2) {
        this.y = i;
        if (this.u != null) {
            if (z2) {
                A(i, this.x, false, z);
                return;
            } else {
                A(i, 1, false, z);
                return;
            }
        }
        this.W = i;
        this.Z = i;
        float f2 = i;
        this.X = f2;
        this.a0 = f2;
    }

    public void setRadius(int i) {
        this.B = i;
    }

    public void setRipple(boolean z) {
        this.M = z;
    }

    public void setRippleColor(int i) {
        this.F = i;
    }

    public void setSelectorAnimation(int i) {
        this.w = i;
    }

    public void setSelectorAnimationDuration(int i) {
        this.x = i;
    }

    public void setSelectorColor(int i) {
        this.v = i;
    }

    public void setViewSoundEffects(boolean z) {
        LinearLayout linearLayout = this.f2531b;
        if (linearLayout != null) {
            int childCount = linearLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.f2531b.getChildAt(i).setSoundEffectsEnabled(z);
            }
        }
    }

    public void t(int i) {
        ArrayList<BackgroundView> arrayList;
        if (this.s <= 0 || (arrayList = this.t) == null || this.u == null || this.f2531b == null || arrayList.size() <= i || this.u.size() <= i || this.f2531b.getChildCount() <= i) {
            return;
        }
        this.s--;
        this.t.remove(i);
        this.f2531b.removeViewAt(i);
        this.u.remove(i);
    }

    public final void u() {
        if (isInEditMode()) {
            this.f2530a.setBackgroundColor(this.z);
        }
    }

    public final void v() {
        if (this.O) {
            this.f2532c.setShowDividers(2);
            b.b.a.j.f.d.b(this.f2532c, this.A, this.H, this.E, this.R);
            if (Build.VERSION.SDK_INT >= 14) {
                this.f2532c.setDividerPadding(this.G);
            }
        }
    }

    public final void w(View view, boolean z) {
        if (!z) {
            b.b.a.j.f.a.a(view, null);
            return;
        }
        if (this.N) {
            b.b.a.j.f.c.f(view, this.F, this.B);
            return;
        }
        if (this.M) {
            b.b.a.j.f.c.h(getContext(), view);
            return;
        }
        for (SegmentedButton segmentedButton : this.u) {
            if (segmentedButton instanceof SegmentedButton) {
                SegmentedButton segmentedButton2 = segmentedButton;
                if (segmentedButton2.l()) {
                    b.b.a.j.f.c.f(view, segmentedButton2.getRippleColor(), this.B);
                }
            }
        }
    }

    public final void x(int i, float f2) {
        if (i < 0 || i >= this.s) {
            return;
        }
        b.b.m.c.a.b(getTag(), "SegmentedButtonGroup", "toNextPosition", "position: " + i, "clip: " + f2 + ", " + hashCode());
        for (int i2 = 0; i2 < this.s; i2++) {
            if (i2 == i) {
                this.u.get(i2).e(f2);
                this.u.get(i2).setCheckEnable(true);
            } else {
                this.u.get(i2).setCheckEnable(false);
            }
        }
    }

    public final void y(int i, float f2) {
        if (i < 0 || i >= this.s) {
            return;
        }
        b.b.m.c.a.b(getTag(), "SegmentedButtonGroup", "toPosition", "position: " + i, "clip: " + f2 + ", " + hashCode());
        for (int i2 = 0; i2 < this.s; i2++) {
            if (i2 == i) {
                this.u.get(i2).f(f2);
                this.u.get(i2).setCheckEnable(true);
            } else {
                this.u.get(i2).setCheckEnable(false);
            }
        }
    }

    public final void z(int i, int i2, boolean z) {
        A(i, 1, z, true);
    }

    public void setRippleColor(boolean z) {
        this.N = z;
    }

    public void setPositionWithOutCallback(int i, boolean z) {
        setPositionWithOutCallback(i, z, true);
    }

    public void setPosition(int i, int i2) {
        this.y = i;
        this.o = this.Z;
        if (this.u == null) {
            this.W = i;
            this.Z = i;
            float f2 = i;
            this.X = f2;
            this.a0 = f2;
            return;
        }
        z(i, i2, false);
    }

    public void setPosition(int i, boolean z) {
        this.y = i;
        if (this.u != null) {
            if (z) {
                z(i, this.x, false);
                return;
            } else {
                z(i, 1, false);
                return;
            }
        }
        this.W = i;
        this.Z = i;
        float f2 = i;
        this.X = f2;
        this.a0 = f2;
    }

    public SegmentedButtonGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2533d = false;
        this.f2534e = false;
        this.n = false;
        this.o = 0;
        this.p = 300;
        this.s = 0;
        this.t = new ArrayList<>();
        this.W = 0;
        this.X = 0.0f;
        this.Z = 0;
        this.a0 = 0.0f;
        this.b0 = false;
        q(context, attributeSet);
    }

    public SegmentedButtonGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2533d = false;
        this.f2534e = false;
        this.n = false;
        this.o = 0;
        this.p = 300;
        this.s = 0;
        this.t = new ArrayList<>();
        this.W = 0;
        this.X = 0.0f;
        this.Z = 0;
        this.a0 = 0.0f;
        this.b0 = false;
        q(context, attributeSet);
    }
}
