package com.byd.hvac.view.segmentedbutton;

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
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import com.byd.hvac.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class SegmentedButtonGroup extends LinearLayout {
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public Drawable K;
    public Drawable L;
    public Drawable M;
    public Interpolator N;
    public f O;
    public WeakReference<d> P;
    public e Q;
    public int R;
    public float S;
    public ValueAnimator T;
    public int U;
    public float V;
    public boolean W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public LinearLayout f1074a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public LinearLayout f1075b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f1076c;
    public boolean d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public boolean m;
    public RectF n;
    public Paint o;
    public int p;
    public ArrayList<BackgroundView> q;
    public ArrayList<SegmentedButton> r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1077a;

        public a(int i) {
            this.f1077a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SegmentedButtonGroup.this.Q != null) {
                if (this.f1077a != SegmentedButtonGroup.this.R) {
                    SegmentedButtonGroup.this.Q.a(SegmentedButtonGroup.this, this.f1077a);
                }
            } else if (SegmentedButtonGroup.this.F && SegmentedButtonGroup.this.G) {
                SegmentedButtonGroup segmentedButtonGroup = SegmentedButtonGroup.this;
                segmentedButtonGroup.t(this.f1077a, segmentedButtonGroup.u, true);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float f = SegmentedButtonGroup.this.S = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int i = (int) f;
            SegmentedButtonGroup.this.j(i, Math.abs(f - i));
            SegmentedButtonGroup.this.invalidate();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @TargetApi(21)
    public class c extends ViewOutlineProvider {
        public c() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), SegmentedButtonGroup.this.y);
        }

        public /* synthetic */ c(SegmentedButtonGroup segmentedButtonGroup, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface d {
        void a(View view, View view2, int i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface e {
        void a(View view, int i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface f {
        void a(View view, View view2, int i);
    }

    public SegmentedButtonGroup(Context context) {
        super(context);
        this.d = false;
        this.m = false;
        this.p = 0;
        this.q = new ArrayList<>();
        this.R = 0;
        this.S = 0.0f;
        this.U = 0;
        this.V = 0.0f;
        this.W = false;
        l(context, null);
    }

    private void setEnabledAlpha(boolean z) {
        setAlpha(!z ? 0.5f : 1.0f);
    }

    private void setRippleState(boolean z) {
        Iterator<BackgroundView> it = this.q.iterator();
        while (it.hasNext()) {
            q(it.next(), z);
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
        int i2 = this.p;
        this.p = i2 + 1;
        segmentedButton.setSelectorColor(this.s);
        segmentedButton.setSelectorRadius(this.y);
        segmentedButton.setBorderSize(this.D);
        segmentedButton.setRecfPadding(this.j, this.g, this.i, this.h);
        if (i2 == 0) {
            segmentedButton.f(true);
        }
        if (i2 > 0) {
            this.r.get(i2 - 1).g(false);
        }
        segmentedButton.g(true);
        segmentedButton.setGradientColor(this.e, this.f);
        this.f1074a.addView(view, layoutParams);
        this.r.add(segmentedButton);
        if (this.v == i2) {
            segmentedButton.c(1.0f);
            this.R = i2;
            this.U = i2;
            float f2 = i2;
            this.S = f2;
            this.V = f2;
        }
        BackgroundView backgroundView = new BackgroundView(getContext());
        if (!this.d) {
            backgroundView.setOnClickListener(new a(i2));
        }
        q(backgroundView, this.G && this.F);
        this.f1075b.addView(backgroundView, new LinearLayout.LayoutParams(segmentedButton.getButtonWidth(), -1, segmentedButton.getWeight()));
        this.q.add(backgroundView);
        if (this.J) {
            this.f1076c.addView(new BackgroundView(getContext()), new LinearLayout.LayoutParams(segmentedButton.getButtonWidth(), -1, segmentedButton.getWeight()));
        }
    }

    public int getBackgroundColor() {
        return this.w;
    }

    public int getButtonChildCount() {
        ArrayList<SegmentedButton> arrayList = this.r;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public int getDividerColor() {
        return this.x;
    }

    @Override // android.widget.LinearLayout
    public int getDividerPadding() {
        return this.B;
    }

    public float getDividerRadius() {
        return this.C;
    }

    public int getDividerSize() {
        return this.z;
    }

    public int getFirstSelectorColor() {
        return this.l;
    }

    public Interpolator getInterpolatorSelector() {
        return this.N;
    }

    public int getPosition() {
        return this.v;
    }

    public float getRadius() {
        return this.y;
    }

    public int getRippleColor() {
        return this.A;
    }

    public int getSelectorAnimation() {
        return this.t;
    }

    public int getSelectorAnimationDuration() {
        return this.u;
    }

    public int getSelectorColor() {
        return this.s;
    }

    public final void j(int i, float f2) {
        float f3 = i + f2;
        int i2 = this.U;
        float f4 = this.V;
        float f5 = i2 + f4;
        if (f3 == f5) {
            return;
        }
        int i3 = i + 1;
        if (f2 == 0.0f && f5 <= f3) {
            i3 = i - 1;
        }
        if (i2 > i && f4 > 0.0f) {
            r(i3 + 1, 1.0f);
        }
        if (this.U < i && this.V < 1.0f) {
            s(i - 1, 0.0f);
        }
        float f6 = 1.0f - f2;
        r(i3, f6);
        s(i, f6);
        this.U = i;
        this.V = f2;
    }

    public final void k(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SegmentedButtonGroup);
        this.J = typedArrayObtainStyledAttributes.hasValue(11);
        this.z = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, 0);
        this.x = typedArrayObtainStyledAttributes.getColor(8, -1);
        this.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, 0);
        this.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, 0);
        this.s = typedArrayObtainStyledAttributes.getColor(24, -7829368);
        this.t = typedArrayObtainStyledAttributes.getInt(1, 0);
        this.u = typedArrayObtainStyledAttributes.getInt(2, 500);
        this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(20, 0);
        this.v = typedArrayObtainStyledAttributes.getInt(19, 0);
        this.w = typedArrayObtainStyledAttributes.getColor(3, 0);
        this.H = typedArrayObtainStyledAttributes.getBoolean(21, false);
        this.I = typedArrayObtainStyledAttributes.hasValue(22);
        this.A = typedArrayObtainStyledAttributes.getColor(22, -7829368);
        this.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, 0);
        this.E = typedArrayObtainStyledAttributes.getColor(5, ViewCompat.MEASURED_STATE_MASK);
        this.K = typedArrayObtainStyledAttributes.getDrawable(4);
        this.L = typedArrayObtainStyledAttributes.getDrawable(23);
        this.M = typedArrayObtainStyledAttributes.getDrawable(7);
        this.G = typedArrayObtainStyledAttributes.getBoolean(13, true);
        this.d = typedArrayObtainStyledAttributes.getBoolean(12, false);
        this.e = typedArrayObtainStyledAttributes.getColor(26, 0);
        this.f = typedArrayObtainStyledAttributes.getColor(25, 0);
        this.g = (int) typedArrayObtainStyledAttributes.getDimension(18, this.k);
        this.h = (int) typedArrayObtainStyledAttributes.getDimension(15, this.k);
        this.j = (int) typedArrayObtainStyledAttributes.getDimension(16, this.k);
        this.i = (int) typedArrayObtainStyledAttributes.getDimension(17, this.k);
        this.l = typedArrayObtainStyledAttributes.getColor(14, this.s);
        try {
            this.F = typedArrayObtainStyledAttributes.getBoolean(0, true);
        } catch (Exception e2) {
            Log.d("SegmentedButtonGroup", e2.toString());
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void l(Context context, AttributeSet attributeSet) {
        this.k = (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
        k(attributeSet);
        setWillNotDraw(false);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new c(this, null));
        }
        setClickable(true);
        this.r = new ArrayList<>();
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.f1074a = linearLayout;
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.f1074a.setOrientation(0);
        frameLayout.addView(this.f1074a);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        this.f1075b = linearLayout2;
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f1075b.setOrientation(0);
        this.f1075b.setClickable(false);
        this.f1075b.setFocusable(false);
        LinearLayout linearLayout3 = this.f1075b;
        int i = this.D;
        linearLayout3.setPadding(i, i, i, i);
        frameLayout.addView(this.f1075b);
        LinearLayout linearLayout4 = new LinearLayout(getContext());
        this.f1076c = linearLayout4;
        linearLayout4.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f1076c.setOrientation(0);
        this.f1076c.setClickable(false);
        this.f1076c.setFocusable(false);
        frameLayout.addView(this.f1076c);
        m();
        o();
        p();
        this.n = new RectF();
        this.o = new Paint(1);
    }

    public final void m() {
        try {
            this.N = (Interpolator) new ArrayList<Class>() { // from class: com.byd.hvac.view.segmentedbutton.SegmentedButtonGroup.2
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
            }.get(this.t).newInstance();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void n() {
        for (SegmentedButton segmentedButton : this.r) {
            if (segmentedButton != null) {
                segmentedButton.invalidate();
            }
        }
    }

    public final void o() {
        if (isInEditMode()) {
            this.f1074a.setBackgroundColor(this.w);
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        this.n.set(0.0f, 0.0f, width, height);
        this.o.setStyle(Paint.Style.FILL);
        this.o.setColor(this.w);
        RectF rectF = this.n;
        int i = this.y;
        canvas.drawRoundRect(rectF, i, i, this.o);
        int i2 = this.D;
        if (i2 > 0) {
            float f2 = i2 / 2.0f;
            float f3 = 0.0f + f2;
            this.n.set(f3, f3, width - f2, height - f2);
            this.o.setStyle(Paint.Style.STROKE);
            this.o.setColor(this.E);
            this.o.setStrokeWidth(this.D);
            RectF rectF2 = this.n;
            int i3 = this.y;
            canvas.drawRoundRect(rectF2, i3, i3, this.o);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1) {
            float x = ((motionEvent.getX() - ((getWidth() / this.p) / 2.0f)) * this.p) / getWidth();
            int iFloor = (int) Math.floor(((double) x) + 0.5d);
            this.V = x;
            this.S = x;
            t(iFloor, this.u, true);
        } else if (action == 2 && this.d) {
            float width = (getWidth() / this.p) / 2.0f;
            float x2 = ((motionEvent.getX() - width) * this.p) / getWidth();
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
        if (this.J) {
            this.f1076c.setShowDividers(2);
            b.a.a.i.n.d.b(this.f1076c, this.x, this.C, this.z, this.M);
            if (Build.VERSION.SDK_INT >= 14) {
                this.f1076c.setDividerPadding(this.B);
            }
        }
    }

    public final void q(View view, boolean z) {
        if (!z) {
            b.a.a.i.n.a.a(view, null);
            return;
        }
        if (this.I) {
            b.a.a.i.n.c.f(view, this.A, this.y);
            return;
        }
        if (this.H) {
            b.a.a.i.n.c.h(getContext(), view);
            return;
        }
        for (SegmentedButton segmentedButton : this.r) {
            if (segmentedButton instanceof SegmentedButton) {
                SegmentedButton segmentedButton2 = segmentedButton;
                if (segmentedButton2.h()) {
                    b.a.a.i.n.c.f(view, segmentedButton2.getRippleColor(), this.y);
                }
            }
        }
    }

    public final void r(int i, float f2) {
        if (i < 0 || i >= this.p) {
            return;
        }
        for (int i2 = 0; i2 < this.p; i2++) {
            if (i2 == i) {
                this.r.get(i2).b(f2);
                this.r.get(i2).setCheckEnable(true);
            } else {
                this.r.get(i2).setCheckEnable(false);
            }
        }
    }

    public final void s(int i, float f2) {
        if (i < 0 || i >= this.p) {
            return;
        }
        for (int i2 = 0; i2 < this.p; i2++) {
            if (i2 == i) {
                this.r.get(i2).c(f2);
                this.r.get(i2).setCheckEnable(true);
            } else {
                this.r.get(i2).setCheckEnable(false);
            }
        }
    }

    public void setAnimationEnable(boolean z) {
        this.m = z;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.w = i;
        invalidate();
    }

    public void setBorderColor(int i) {
        this.E = i;
    }

    public void setBorderSize(int i) {
        this.D = i;
    }

    public void setButtonText(String[] strArr) {
        ArrayList<SegmentedButton> arrayList;
        if (strArr == null || (arrayList = this.r) == null || strArr.length == 0 || arrayList.isEmpty()) {
            return;
        }
        for (int i = 0; i < strArr.length && i < this.r.size(); i++) {
            if (strArr[i] != null && this.r.get(i) != null) {
                this.r.get(i).setText(strArr[i]);
            }
        }
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        this.F = z;
        setRippleState(z);
    }

    public void setDivider(boolean z) {
        this.J = z;
    }

    public void setDividerColor(int i) {
        this.x = i;
        b.a.a.i.n.d.b(this.f1076c, i, this.C, this.z, this.M);
    }

    @Override // android.widget.LinearLayout
    public void setDividerPadding(int i) {
        this.B = i;
    }

    public void setDividerRadius(int i) {
        this.C = i;
        b.a.a.i.n.d.b(this.f1076c, this.x, i, this.z, this.M);
    }

    public void setDividerSize(int i) {
        this.z = i;
        b.a.a.i.n.d.b(this.f1076c, this.x, this.C, i, this.M);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.G = z;
        setRippleState(z);
        setEnabledAlpha(z);
    }

    public void setFirstSelectorColor(int i) {
        this.l = i;
        ArrayList<SegmentedButton> arrayList = this.r;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < this.r.size(); i2++) {
            this.r.get(i2).setFirstSelectorColor(i);
        }
    }

    public void setInterpolatorSelector(Interpolator interpolator) {
        this.N = interpolator;
    }

    public void setOnClickedButtonListener(d dVar) {
        this.P = new WeakReference<>(dVar);
    }

    public void setOnMyClickListener(e eVar) {
        this.Q = eVar;
    }

    public void setOnPositionChangedListener(f fVar) {
        this.O = fVar;
    }

    public void setPosition(int i) {
        this.v = i;
        if (this.r != null) {
            t(i, this.u, false);
            return;
        }
        this.R = i;
        this.U = i;
        float f2 = i;
        this.S = f2;
        this.V = f2;
    }

    public void setPositionWithOutCallback(int i, boolean z, boolean z2) {
        this.v = i;
        if (this.r != null) {
            if (z2) {
                u(i, this.u, false, z);
                return;
            } else {
                u(i, 1, false, z);
                return;
            }
        }
        this.R = i;
        this.U = i;
        float f2 = i;
        this.S = f2;
        this.V = f2;
    }

    public void setRadius(int i) {
        this.y = i;
    }

    public void setRipple(boolean z) {
        this.H = z;
    }

    public void setRippleColor(int i) {
        this.A = i;
    }

    public void setSelectorAnimation(int i) {
        this.t = i;
    }

    public void setSelectorAnimationDuration(int i) {
        this.u = i;
    }

    public void setSelectorColor(int i) {
        this.s = i;
    }

    public final void t(int i, int i2, boolean z) {
        u(i, 1, z, true);
    }

    public final void u(int i, int i2, boolean z, boolean z2) {
        if (!this.d && this.R == i) {
            if (this.W) {
                n();
                f fVar = this.O;
                if (fVar != null) {
                    fVar.a(this, getChildAt(i), i);
                }
                this.W = false;
                return;
            }
            return;
        }
        ValueAnimator valueAnimator = this.T;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.T.cancel();
        }
        this.R = i;
        if (this.m) {
            if (this.T == null) {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.S, i);
                this.T = valueAnimatorOfFloat;
                valueAnimatorOfFloat.addUpdateListener(new b());
                this.T.setInterpolator(this.N);
            }
            this.T.setFloatValues(this.S, i);
            this.T.setDuration(i2);
            this.T.start();
        } else {
            j(i, this.S);
            invalidate();
        }
        int i3 = 0;
        while (i3 < this.r.size()) {
            this.r.get(i3).setTextOnSelection(i == i3);
            i3++;
        }
        WeakReference<d> weakReference = this.P;
        if (weakReference != null && weakReference.get() != null && z) {
            this.P.get().a(this, getChildAt(i), i);
        }
        f fVar2 = this.O;
        if (fVar2 != null && z && z2) {
            fVar2.a(this, getChildAt(i), i);
        }
        this.v = i;
    }

    public void setRippleColor(boolean z) {
        this.I = z;
    }

    public void setPosition(int i, int i2) {
        this.v = i;
        if (this.r == null) {
            this.R = i;
            this.U = i;
            float f2 = i;
            this.S = f2;
            this.V = f2;
            return;
        }
        t(i, i2, false);
    }

    public void setPositionWithOutCallback(int i, boolean z) {
        setPositionWithOutCallback(i, z, true);
    }

    public void setPosition(int i, boolean z) {
        this.v = i;
        if (this.r != null) {
            if (z) {
                t(i, this.u, false);
                return;
            } else {
                t(i, 1, false);
                return;
            }
        }
        this.R = i;
        this.U = i;
        float f2 = i;
        this.S = f2;
        this.V = f2;
    }

    public SegmentedButtonGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = false;
        this.m = false;
        this.p = 0;
        this.q = new ArrayList<>();
        this.R = 0;
        this.S = 0.0f;
        this.U = 0;
        this.V = 0.0f;
        this.W = false;
        l(context, attributeSet);
    }

    public SegmentedButtonGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        this.m = false;
        this.p = 0;
        this.q = new ArrayList<>();
        this.R = 0;
        this.S = 0.0f;
        this.U = 0;
        this.V = 0.0f;
        this.W = false;
        l(context, attributeSet);
    }
}
