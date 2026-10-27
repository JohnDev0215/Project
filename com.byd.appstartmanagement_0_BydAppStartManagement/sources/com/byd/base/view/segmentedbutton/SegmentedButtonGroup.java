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
public class SegmentedButtonGroup extends LinearLayout implements b.a.b.e.c.a {
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
    public float a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public LinearLayout f598b;
    public boolean b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f599c;
    public g c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public LinearLayout f600d;
    public boolean e;
    public boolean f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public boolean o;
    public int p;
    public int q;
    public RectF r;
    public Paint s;
    public int t;
    public ArrayList<BackgroundView> u;
    public ArrayList<SegmentedButton> v;
    public int w;
    public int x;
    public int y;
    public int z;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f601b;

        public a(int i) {
            this.f601b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.a.d.f.c.a(SegmentedButtonGroup.this.q)) {
                return;
            }
            if (SegmentedButtonGroup.this.V != null) {
                if (this.f601b != SegmentedButtonGroup.this.W) {
                    SegmentedButtonGroup.this.V.a(SegmentedButtonGroup.this, this.f601b);
                    return;
                }
                return;
            }
            b.a.d.b.a.b(SegmentedButtonGroup.this.getTag(), "SegmentedButtonGroup", "onClick", "position: " + this.f601b);
            if (SegmentedButtonGroup.this.K && SegmentedButtonGroup.this.L) {
                SegmentedButtonGroup segmentedButtonGroup = SegmentedButtonGroup.this;
                segmentedButtonGroup.u(this.f601b, segmentedButtonGroup.y, true);
            }
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float f = SegmentedButtonGroup.this.X = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int i = (int) f;
            b.a.d.b.a.b(SegmentedButtonGroup.this.getTag(), "SegmentedButtonGroup", "toggle_onAnimationUpdate", "position: " + i + ", " + hashCode());
            SegmentedButtonGroup.this.k(i, Math.abs(f - ((float) i)));
            SegmentedButtonGroup.this.invalidate();
        }
    }

    @TargetApi(21)
    public class c extends ViewOutlineProvider {
        public c() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), SegmentedButtonGroup.this.C);
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

    public SegmentedButtonGroup(Context context) {
        super(context);
        this.e = false;
        this.f = false;
        this.o = false;
        this.p = 0;
        this.q = 300;
        this.t = 0;
        this.u = new ArrayList<>();
        this.W = 0;
        this.X = 0.0f;
        this.Z = 0;
        this.a0 = 0.0f;
        this.b0 = false;
        m(context, null);
    }

    private void setEnabledAlpha(boolean z) {
        setAlpha(!z ? 0.5f : 1.0f);
    }

    private void setRippleState(boolean z) {
        Iterator<BackgroundView> it = this.u.iterator();
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
        int i2 = this.t;
        this.t = i2 + 1;
        segmentedButton.setSelectorColor(this.w);
        segmentedButton.setSelectorRadius(this.D);
        segmentedButton.setBorderSize(this.I);
        segmentedButton.setRecfPadding(this.l, this.i, this.k, this.j);
        if (i2 == 0) {
            segmentedButton.f(true);
        }
        if (i2 > 0) {
            this.v.get(i2 - 1).g(false);
        }
        segmentedButton.g(true);
        segmentedButton.setGradientColor(this.g, this.h);
        this.f598b.addView(view, layoutParams);
        this.v.add(segmentedButton);
        if (this.z == i2) {
            segmentedButton.c(1.0f);
            this.W = i2;
            this.Z = i2;
            float f2 = i2;
            this.X = f2;
            this.a0 = f2;
        }
        BackgroundView backgroundView = new BackgroundView(getContext());
        if (!this.e) {
            backgroundView.setOnClickListener(new a(i2));
        }
        r(backgroundView, this.L && this.K);
        this.f599c.addView(backgroundView, new LinearLayout.LayoutParams(segmentedButton.getButtonWidth(), -1, segmentedButton.getWeight()));
        this.u.add(backgroundView);
        if (this.O) {
            this.f600d.addView(new BackgroundView(getContext()), new LinearLayout.LayoutParams(segmentedButton.getButtonWidth(), -1, segmentedButton.getWeight()));
        }
    }

    public int getBackgroundColor() {
        return this.A;
    }

    public int getButtonChildCount() {
        ArrayList<SegmentedButton> arrayList = this.v;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public int getDividerColor() {
        return this.B;
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
        return this.n;
    }

    public Interpolator getInterpolatorSelector() {
        return this.S;
    }

    public int getPosition() {
        return this.z;
    }

    public float getRadius() {
        return this.C;
    }

    public int getRevertPosition() {
        return this.p;
    }

    public int getRippleColor() {
        return this.F;
    }

    public int getSelectorAnimation() {
        return this.x;
    }

    public int getSelectorAnimationDuration() {
        return this.y;
    }

    public int getSelectorColor() {
        return this.w;
    }

    public final void k(int i, float f2) {
        float f3 = i + f2;
        float f4 = this.Z + this.a0;
        if (f3 == f4) {
            return;
        }
        b.a.d.b.a.b(getTag(), "SegmentedButtonGroup", "animateViews", "position: " + i, "lastPosition: " + this.Z + ", " + hashCode());
        int i2 = i + 1;
        if (f2 == 0.0f && f4 <= f3) {
            i2 = i - 1;
        }
        if (this.Z > i && this.a0 > 0.0f) {
            s(i2 + 1, 1.0f);
        }
        if (this.Z < i && this.a0 < 1.0f) {
            t(i - 1, 0.0f);
        }
        float f5 = 1.0f - f2;
        s(i2, f5);
        t(i, f5);
        this.Z = i;
        this.a0 = f2;
    }

    public final void l(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SegmentedButtonGroup);
        int i = R$styleable.SegmentedButtonGroup_sbg_dividerSize;
        this.O = typedArrayObtainStyledAttributes.hasValue(i);
        this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(i, 0);
        this.B = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_dividerColor, -1);
        this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_dividerPadding, 0);
        this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButtonGroup_sbg_dividerRadius, 0);
        this.w = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorColor, -7829368);
        this.x = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_animateSelector, 0);
        this.y = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_animateSelectorDuration, 500);
        this.C = getResources().getDimensionPixelSize(b.a.b.c.b.a(getContext(), R$attr.sbg_radius));
        this.D = getResources().getDimensionPixelSize(b.a.b.c.b.a(getContext(), R$attr.sbg_button_selected_radius));
        this.z = 0;
        this.A = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_backgroundColor, 0);
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
        this.e = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_sbg_draggable, false);
        this.g = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorGradient_startColor, 0);
        this.h = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_selectorGradient_endColor, 0);
        this.i = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_top, this.m);
        this.j = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_bottom, this.m);
        this.l = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_left, this.m);
        this.k = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButtonGroup_sbg_padding_right, this.m);
        this.n = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButtonGroup_sbg_firstSelectorColor, this.w);
        this.q = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButtonGroup_sbg_clickTime, 200);
        try {
            this.K = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_android_clickable, true);
        } catch (Exception e2) {
            b.a.d.b.a.a("SegmentedButtonGroup", e2.toString());
        }
        this.f = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButtonGroup_sbg_autoAllocByMaxWidth, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void m(Context context, AttributeSet attributeSet) {
        setLayoutDirection(0);
        this.m = (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
        l(attributeSet);
        setWillNotDraw(false);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new c(this, null));
        }
        this.K = true;
        setRippleState(true);
        this.v = new ArrayList<>();
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.f598b = linearLayout;
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.f598b.setOrientation(0);
        frameLayout.addView(this.f598b);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        this.f599c = linearLayout2;
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f599c.setOrientation(0);
        this.f599c.setClickable(false);
        this.f599c.setFocusable(false);
        LinearLayout linearLayout3 = this.f599c;
        int i = this.I;
        linearLayout3.setPadding(i, i, i, i);
        frameLayout.addView(this.f599c);
        LinearLayout linearLayout4 = new LinearLayout(getContext());
        this.f600d = linearLayout4;
        linearLayout4.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f600d.setOrientation(0);
        this.f600d.setClickable(false);
        this.f600d.setFocusable(false);
        frameLayout.addView(this.f600d);
        n();
        p();
        q();
        this.r = new RectF();
        this.s = new Paint(1);
    }

    public final void n() {
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
            }.get(this.x).newInstance();
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    public void o(int i) {
        ArrayList<BackgroundView> arrayList;
        if (this.t <= 0 || (arrayList = this.u) == null || this.v == null || this.f599c == null || arrayList.size() <= i || this.v.size() <= i || this.f599c.getChildCount() <= i) {
            return;
        }
        this.t--;
        this.u.remove(i);
        this.f599c.removeViewAt(i);
        this.v.remove(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        b.a.d.b.a.b(getTag(), "SegmentedButtonGroup", "onAttachedToWindow", "this.position: " + this.z + ", " + hashCode());
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        b.a.d.b.a.b(getTag(), "SegmentedButtonGroup", "onDetachedFromWindow", "this.position: " + this.z + ", " + hashCode());
        super.onDetachedFromWindow();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        this.r.set(0.0f, 0.0f, width, height);
        this.s.setStyle(Paint.Style.FILL);
        this.s.setColor(this.A);
        RectF rectF = this.r;
        int i = this.C;
        canvas.drawRoundRect(rectF, i, i, this.s);
        int i2 = this.I;
        if (i2 > 0) {
            float f2 = i2 / 2.0f;
            float f3 = 0.0f + f2;
            this.r.set(f3, f3, width - f2, height - f2);
            this.s.setStyle(Paint.Style.STROKE);
            this.s.setColor(this.J);
            this.s.setStrokeWidth(this.I);
            RectF rectF2 = this.r;
            int i3 = this.C;
            canvas.drawRoundRect(rectF2, i3, i3, this.s);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ArrayList<SegmentedButton> arrayList;
        super.onLayout(z, i, i2, i3, i4);
        if (!this.f || (arrayList = this.v) == null || arrayList.isEmpty()) {
            return;
        }
        Iterator<SegmentedButton> it = this.v.iterator();
        int i5 = 0;
        while (it.hasNext()) {
            int width = it.next().getWidth();
            b.a.d.b.a.a("SegmentedButtonGroup", "-----onLayout----w: " + width);
            if (i5 < width) {
                i5 = width;
            }
        }
        b.a.d.b.a.a("SegmentedButtonGroup", "-----onLayout----wMax: " + i5);
        for (SegmentedButton segmentedButton : this.v) {
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
            float x = ((motionEvent.getX() - ((getWidth() / this.t) / 2.0f)) * this.t) / getWidth();
            int iFloor = (int) Math.floor(((double) x) + 0.5d);
            this.a0 = x;
            this.X = x;
            u(iFloor, this.y, true);
        } else if (action == 2 && this.e) {
            float width = (getWidth() / this.t) / 2.0f;
            float x2 = ((motionEvent.getX() - width) * this.t) / getWidth();
            int iFloor2 = (int) Math.floor(x2);
            float f2 = x2 - iFloor2;
            if (motionEvent.getRawX() - width < getLeft()) {
                k(iFloor2 + 1, 0.0f);
            } else if (motionEvent.getRawX() + width > getRight()) {
                k(iFloor2 - 1, 1.0f);
            } else {
                k(iFloor2, f2);
            }
        }
        return true;
    }

    public final void p() {
        if (isInEditMode()) {
            this.f598b.setBackgroundColor(this.A);
        }
    }

    public final void q() {
        if (this.O) {
            this.f600d.setShowDividers(2);
            b.a.b.e.f.d.b(this.f600d, this.B, this.H, this.E, this.R);
            if (Build.VERSION.SDK_INT >= 14) {
                this.f600d.setDividerPadding(this.G);
            }
        }
    }

    public final void r(View view, boolean z) {
        if (!z) {
            b.a.b.e.f.a.a(view, null);
            return;
        }
        if (this.N) {
            b.a.b.e.f.c.f(view, this.F, this.C);
            return;
        }
        if (this.M) {
            b.a.b.e.f.c.h(getContext(), view);
            return;
        }
        for (SegmentedButton segmentedButton : this.v) {
            if (segmentedButton instanceof SegmentedButton) {
                SegmentedButton segmentedButton2 = segmentedButton;
                if (segmentedButton2.h()) {
                    b.a.b.e.f.c.f(view, segmentedButton2.getRippleColor(), this.C);
                }
            }
        }
    }

    public final void s(int i, float f2) {
        if (i < 0 || i >= this.t) {
            return;
        }
        b.a.d.b.a.b(getTag(), "SegmentedButtonGroup", "toNextPosition", "position: " + i, "clip: " + f2 + ", " + hashCode());
        for (int i2 = 0; i2 < this.t; i2++) {
            if (i2 == i) {
                this.v.get(i2).b(f2);
                this.v.get(i2).setCheckEnable(true);
            } else {
                this.v.get(i2).setCheckEnable(false);
            }
        }
    }

    public void setAnimationEnable(boolean z) {
        this.o = z;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.A = i;
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
        if (strArr == null || (arrayList = this.v) == null || strArr.length == 0 || arrayList.isEmpty()) {
            return;
        }
        for (int i = 0; i < strArr.length && i < this.v.size(); i++) {
            if (strArr[i] != null && this.v.get(i) != null) {
                this.v.get(i).setText(strArr[i]);
            }
        }
    }

    public void setClickTime(int i) {
        this.q = i;
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
        this.B = i;
        b.a.b.e.f.d.b(this.f600d, i, this.H, this.E, this.R);
    }

    @Override // android.widget.LinearLayout
    public void setDividerPadding(int i) {
        this.G = i;
    }

    public void setDividerRadius(int i) {
        this.H = i;
        b.a.b.e.f.d.b(this.f600d, this.B, i, this.E, this.R);
    }

    public void setDividerSize(int i) {
        this.E = i;
        b.a.b.e.f.d.b(this.f600d, this.B, this.H, i, this.R);
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
        this.n = i;
        ArrayList<SegmentedButton> arrayList = this.v;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < this.v.size(); i2++) {
            this.v.get(i2).setFirstSelectorColor(i);
        }
    }

    public void setInterpolatorSelector(Interpolator interpolator) {
        this.S = interpolator;
    }

    @Override // b.a.b.e.c.a
    public void setInvalid(boolean z) {
        for (SegmentedButton segmentedButton : this.v) {
            if (segmentedButton != null) {
                segmentedButton.setInvalid(z);
            }
        }
    }

    @Override // b.a.b.e.c.a
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
        this.p = this.Z;
        this.z = i;
        b.a.d.b.a.b(getTag(), "SegmentedButtonGroup", "setPosition", "this.position: " + this.z + ", " + hashCode());
        if (this.v != null) {
            u(i, this.y, false);
            return;
        }
        this.W = i;
        this.Z = i;
        float f2 = i;
        this.X = f2;
        this.a0 = f2;
    }

    public void setPositionWithOutCallback(int i, boolean z, boolean z2) {
        this.z = i;
        if (this.v != null) {
            if (z2) {
                v(i, this.y, false, z);
                return;
            } else {
                v(i, 1, false, z);
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
        this.C = i;
    }

    public void setRipple(boolean z) {
        this.M = z;
    }

    public void setRippleColor(int i) {
        this.F = i;
    }

    public void setSelectorAnimation(int i) {
        this.x = i;
    }

    public void setSelectorAnimationDuration(int i) {
        this.y = i;
    }

    public void setSelectorColor(int i) {
        this.w = i;
    }

    public void setViewSoundEffects(boolean z) {
        LinearLayout linearLayout = this.f599c;
        if (linearLayout != null) {
            int childCount = linearLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.f599c.getChildAt(i).setSoundEffectsEnabled(z);
            }
        }
    }

    public final void t(int i, float f2) {
        if (i < 0 || i >= this.t) {
            return;
        }
        b.a.d.b.a.b(getTag(), "SegmentedButtonGroup", "toPosition", "position: " + i, "clip: " + f2 + ", " + hashCode());
        for (int i2 = 0; i2 < this.t; i2++) {
            if (i2 == i) {
                this.v.get(i2).c(f2);
                this.v.get(i2).setCheckEnable(true);
            } else {
                this.v.get(i2).setCheckEnable(false);
            }
        }
    }

    public final void u(int i, int i2, boolean z) {
        v(i, 1, z, true);
    }

    public final void v(int i, int i2, boolean z, boolean z2) {
        g gVar;
        if (this.W == i && (gVar = this.c0) != null && z2) {
            gVar.a(this, getChildAt(i), i);
        }
        if (!this.e && this.W == i) {
            if (this.b0) {
                f fVar = this.T;
                if (fVar != null) {
                    fVar.a(this, getChildAt(i), i);
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
        b.a.d.b.a.b(getTag(), "SegmentedButtonGroup", "toggle", "animationEnable: " + this.o + ", position: " + i + ", " + hashCode());
        if (this.o) {
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
            k(i, this.X);
            invalidate();
        }
        int i3 = 0;
        while (i3 < this.v.size()) {
            this.v.get(i3).setTextOnSelection(i == i3);
            i3++;
        }
        WeakReference<d> weakReference = this.U;
        if (weakReference != null && weakReference.get() != null && z && z2) {
            this.U.get().a(this, getChildAt(i), i);
        }
        f fVar2 = this.T;
        if (fVar2 != null && z && z2) {
            fVar2.a(this, getChildAt(i), i);
        }
        this.z = i;
    }

    public void setRippleColor(boolean z) {
        this.N = z;
    }

    public void setPositionWithOutCallback(int i, boolean z) {
        setPositionWithOutCallback(i, z, true);
    }

    public void setPosition(int i, int i2) {
        this.z = i;
        this.p = this.Z;
        if (this.v == null) {
            this.W = i;
            this.Z = i;
            float f2 = i;
            this.X = f2;
            this.a0 = f2;
            return;
        }
        u(i, i2, false);
    }

    public void setPosition(int i, boolean z) {
        this.z = i;
        if (this.v != null) {
            if (z) {
                u(i, this.y, false);
                return;
            } else {
                u(i, 1, false);
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
        this.e = false;
        this.f = false;
        this.o = false;
        this.p = 0;
        this.q = 300;
        this.t = 0;
        this.u = new ArrayList<>();
        this.W = 0;
        this.X = 0.0f;
        this.Z = 0;
        this.a0 = 0.0f;
        this.b0 = false;
        m(context, attributeSet);
    }

    public SegmentedButtonGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = false;
        this.f = false;
        this.o = false;
        this.p = 0;
        this.q = 300;
        this.t = 0;
        this.u = new ArrayList<>();
        this.W = 0;
        this.X = 0.0f;
        this.Z = 0;
        this.a0 = 0.0f;
        this.b0 = false;
        m(context, attributeSet);
    }
}
