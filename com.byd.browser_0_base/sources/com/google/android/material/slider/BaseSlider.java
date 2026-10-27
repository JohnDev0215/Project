package com.google.android.material.slider;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.slider.BaseSlider;
import defpackage.C0173a;
import defpackage.a41;
import defpackage.bz;
import defpackage.c83;
import defpackage.d70;
import defpackage.gv;
import defpackage.in3;
import defpackage.kd3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.p80;
import defpackage.pi0;
import defpackage.r83;
import defpackage.rc1;
import defpackage.rd;
import defpackage.s73;
import defpackage.sc1;
import defpackage.sd;
import defpackage.u5;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.y41;
import defpackage.yn3;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends rd<S>, T extends sd<S>> extends View {
    public static final int h0 = R$style.Widget_MaterialComponents_Slider;
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public float G;
    public MotionEvent H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public float f34J;
    public float K;
    public ArrayList<Float> L;
    public int M;
    public int N;
    public float O;
    public float[] P;
    public boolean Q;
    public int R;
    public boolean S;
    public boolean T;
    public ColorStateList U;
    public ColorStateList V;
    public ColorStateList W;
    public ColorStateList a0;
    public ColorStateList b0;
    public final sc1 c0;
    public Drawable d0;
    public final Paint e;
    public List<Drawable> e0;
    public final Paint f;
    public float f0;
    public final Paint g;
    public int g0;
    public final Paint h;
    public final Paint i;
    public final Paint j;
    public final e k;
    public final AccessibilityManager l;
    public BaseSlider<S, L, T>.d m;
    public final a n;
    public final ArrayList o;
    public final ArrayList p;
    public final ArrayList q;
    public boolean r;
    public ValueAnimator s;
    public ValueAnimator t;
    public final int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new a();
        public float e;
        public float f;
        public ArrayList<Float> g;
        public float h;
        public boolean i;

        public class a implements Parcelable.Creator<SliderState> {
            @Override // android.os.Parcelable.Creator
            public final SliderState createFromParcel(Parcel parcel) {
                return new SliderState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final SliderState[] newArray(int i) {
                return new SliderState[i];
            }
        }

        public SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.e);
            parcel.writeFloat(this.f);
            parcel.writeList(this.g);
            parcel.writeFloat(this.h);
            parcel.writeBooleanArray(new boolean[]{this.i});
        }

        public SliderState(Parcel parcel) {
            super(parcel);
            this.e = parcel.readFloat();
            this.f = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.g = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.h = parcel.readFloat();
            this.i = parcel.createBooleanArray()[0];
        }
    }

    public class a {
        public final /* synthetic */ AttributeSet a;
        public final /* synthetic */ int b;

        public a(AttributeSet attributeSet, int i) {
            this.a = attributeSet;
            this.b = i;
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {
        public b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            BaseSlider baseSlider = BaseSlider.this;
            for (kd3 kd3Var : baseSlider.o) {
                kd3Var.P = 1.2f;
                kd3Var.N = fFloatValue;
                kd3Var.O = fFloatValue;
                kd3Var.Q = u5.a(0.0f, 1.0f, 0.19f, 1.0f, fFloatValue);
                kd3Var.invalidateSelf();
            }
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(baseSlider);
        }
    }

    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            BaseSlider baseSlider = BaseSlider.this;
            for (kd3 kd3Var : baseSlider.o) {
                ViewGroup viewGroupC = yn3.c(baseSlider);
                ((ViewOverlay) (viewGroupC == null ? null : new a41(viewGroupC)).b).remove(kd3Var);
            }
        }
    }

    public class d implements Runnable {
        public int e = -1;

        public d() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            BaseSlider.this.k.y(this.e, 4);
        }
    }

    public static class e extends pi0 {
        public final BaseSlider<?, ?, ?> q;
        public final Rect r;

        public e(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.r = new Rect();
            this.q = baseSlider;
        }

        @Override // defpackage.pi0
        public final int n(float f, float f2) {
            int i = 0;
            while (true) {
                BaseSlider<?, ?, ?> baseSlider = this.q;
                if (i >= baseSlider.getValues().size()) {
                    return -1;
                }
                Rect rect = this.r;
                baseSlider.r(i, rect);
                if (rect.contains((int) f, (int) f2)) {
                    return i;
                }
                i++;
            }
        }

        @Override // defpackage.pi0
        public final void o(ArrayList arrayList) {
            for (int i = 0; i < this.q.getValues().size(); i++) {
                arrayList.add(Integer.valueOf(i));
            }
        }

        @Override // defpackage.pi0
        public final boolean s(int i, int i2, Bundle bundle) {
            BaseSlider<?, ?, ?> baseSlider = this.q;
            if (!baseSlider.isEnabled()) {
                return false;
            }
            if (i2 != 4096 && i2 != 8192) {
                if (i2 == 16908349 && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                    float f = bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE");
                    int i3 = BaseSlider.h0;
                    if (baseSlider.q(f, i)) {
                        baseSlider.s();
                        baseSlider.postInvalidate();
                        p(i, 0);
                        return true;
                    }
                }
                return false;
            }
            int i4 = BaseSlider.h0;
            float fRound = baseSlider.O;
            if (fRound == 0.0f) {
                fRound = 1.0f;
            }
            if ((baseSlider.K - baseSlider.f34J) / fRound > 20) {
                fRound *= Math.round(r1 / r5);
            }
            if (i2 == 8192) {
                fRound = -fRound;
            }
            if (baseSlider.i()) {
                fRound = -fRound;
            }
            float fFloatValue = baseSlider.getValues().get(i).floatValue() + fRound;
            float valueFrom = baseSlider.getValueFrom();
            float valueTo = baseSlider.getValueTo();
            if (fFloatValue < valueFrom) {
                fFloatValue = valueFrom;
            } else if (fFloatValue > valueTo) {
                fFloatValue = valueTo;
            }
            if (!baseSlider.q(fFloatValue, i)) {
                return false;
            }
            baseSlider.s();
            baseSlider.postInvalidate();
            p(i, 0);
            return true;
        }

        @Override // defpackage.pi0
        public final void v(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.f10J);
            BaseSlider<?, ?, ?> baseSlider = this.q;
            List<Float> values = baseSlider.getValues();
            float fFloatValue = values.get(i).floatValue();
            float valueFrom = baseSlider.getValueFrom();
            float valueTo = baseSlider.getValueTo();
            if (baseSlider.isEnabled()) {
                if (fFloatValue > valueFrom) {
                    accessibilityNodeInfoCompat.a(8192);
                }
                if (fFloatValue < valueTo) {
                    accessibilityNodeInfoCompat.a(4096);
                }
            }
            accessibilityNodeInfoCompat.a.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(1, valueFrom, valueTo, fFloatValue));
            accessibilityNodeInfoCompat.n(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (baseSlider.getContentDescription() != null) {
                sb.append(baseSlider.getContentDescription());
                sb.append(",");
            }
            if (values.size() > 1) {
                sb.append(i == baseSlider.getValues().size() + (-1) ? baseSlider.getContext().getString(R$string.material_slider_range_end) : i == 0 ? baseSlider.getContext().getString(R$string.material_slider_range_start) : "");
                sb.append(String.format(((float) ((int) fFloatValue)) == fFloatValue ? "%.0f" : "%.2f", Float.valueOf(fFloatValue)));
            }
            accessibilityNodeInfoCompat.r(sb.toString());
            Rect rect = this.r;
            baseSlider.r(i, rect);
            accessibilityNodeInfoCompat.l(rect);
        }
    }

    public BaseSlider(Context context) {
        this(context, null);
    }

    private float[] getActiveRange() {
        float fFloatValue = ((Float) Collections.max(getValues())).floatValue();
        float fFloatValue2 = ((Float) Collections.min(getValues())).floatValue();
        if (this.L.size() == 1) {
            fFloatValue2 = this.f34J;
        }
        float fM = m(fFloatValue2);
        float fM2 = m(fFloatValue);
        return i() ? new float[]{fM2, fM} : new float[]{fM, fM2};
    }

    private float getValueOfTouchPosition() {
        double dRound;
        float f = this.f0;
        float f2 = this.O;
        if (f2 > 0.0f) {
            int i = (int) ((this.K - this.f34J) / f2);
            dRound = ((double) Math.round(f * i)) / ((double) i);
        } else {
            dRound = f;
        }
        if (i()) {
            dRound = 1.0d - dRound;
        }
        float f3 = this.K;
        float f4 = this.f34J;
        return (float) ((dRound * ((double) (f3 - f4))) + ((double) f4));
    }

    private float getValueOfTouchPositionAbsolute() {
        float f = this.f0;
        if (i()) {
            f = 1.0f - f;
        }
        float f2 = this.K;
        float f3 = this.f34J;
        return C0173a.f(f2, f3, f, f3);
    }

    private void setValuesInternal(ArrayList<Float> arrayList) {
        ViewGroup viewGroupC;
        int resourceId;
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("At least one value must be set");
        }
        Collections.sort(arrayList);
        if (this.L.size() == arrayList.size() && this.L.equals(arrayList)) {
            return;
        }
        this.L = arrayList;
        this.T = true;
        this.N = 0;
        s();
        ArrayList<kd3> arrayList2 = this.o;
        if (arrayList2.size() > this.L.size()) {
            List<kd3> listSubList = arrayList2.subList(this.L.size(), arrayList2.size());
            for (kd3 kd3Var : listSubList) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                if (om3.g.b(this)) {
                    ViewGroup viewGroupC2 = yn3.c(this);
                    a41 a41Var = viewGroupC2 == null ? null : new a41(viewGroupC2);
                    if (a41Var != null) {
                        ((ViewOverlay) a41Var.b).remove(kd3Var);
                        ViewGroup viewGroupC3 = yn3.c(this);
                        if (viewGroupC3 == null) {
                            kd3Var.getClass();
                        } else {
                            viewGroupC3.removeOnLayoutChangeListener(kd3Var.F);
                        }
                    }
                }
            }
            listSubList.clear();
        }
        while (arrayList2.size() < this.L.size()) {
            a aVar = this.n;
            BaseSlider baseSlider = BaseSlider.this;
            TypedArray typedArrayD = r83.d(baseSlider.getContext(), aVar.a, R$styleable.Slider, aVar.b, h0, new int[0]);
            Context context = baseSlider.getContext();
            int resourceId2 = typedArrayD.getResourceId(R$styleable.Slider_labelStyle, R$style.Widget_MaterialComponents_Tooltip);
            kd3 kd3Var2 = new kd3(context, resourceId2);
            TypedArray typedArrayD2 = r83.d(kd3Var2.C, null, R$styleable.Tooltip, 0, resourceId2, new int[0]);
            Context context2 = kd3Var2.C;
            kd3Var2.L = context2.getResources().getDimensionPixelSize(R$dimen.mtrl_tooltip_arrowSize);
            uj2 uj2Var = kd3Var2.e.a;
            uj2Var.getClass();
            uj2.a aVar2 = new uj2.a(uj2Var);
            aVar2.k = kd3Var2.x();
            kd3Var2.setShapeAppearanceModel(new uj2(aVar2));
            CharSequence text = typedArrayD2.getText(R$styleable.Tooltip_android_text);
            boolean zEquals = TextUtils.equals(kd3Var2.B, text);
            c83 c83Var = kd3Var2.E;
            if (!zEquals) {
                kd3Var2.B = text;
                c83Var.d = true;
                kd3Var2.invalidateSelf();
            }
            int i = R$styleable.Tooltip_android_textAppearance;
            s73 s73Var = (!typedArrayD2.hasValue(i) || (resourceId = typedArrayD2.getResourceId(i, 0)) == 0) ? null : new s73(context2, resourceId);
            if (s73Var != null) {
                int i2 = R$styleable.Tooltip_android_textColor;
                if (typedArrayD2.hasValue(i2)) {
                    s73Var.j = rc1.a(context2, typedArrayD2, i2);
                }
            }
            c83Var.b(s73Var, context2);
            kd3Var2.n(ColorStateList.valueOf(typedArrayD2.getColor(R$styleable.Tooltip_backgroundTint, gv.f(gv.g(oj2.n(R$attr.colorOnBackground, context2, kd3.class.getCanonicalName()), 153), gv.g(oj2.n(R.attr.colorBackground, context2, kd3.class.getCanonicalName()), 229)))));
            kd3Var2.s(ColorStateList.valueOf(oj2.n(R$attr.colorSurface, context2, kd3.class.getCanonicalName())));
            kd3Var2.H = typedArrayD2.getDimensionPixelSize(R$styleable.Tooltip_android_padding, 0);
            kd3Var2.I = typedArrayD2.getDimensionPixelSize(R$styleable.Tooltip_android_minWidth, 0);
            kd3Var2.f43J = typedArrayD2.getDimensionPixelSize(R$styleable.Tooltip_android_minHeight, 0);
            kd3Var2.K = typedArrayD2.getDimensionPixelSize(R$styleable.Tooltip_android_layout_margin, 0);
            typedArrayD2.recycle();
            typedArrayD.recycle();
            arrayList2.add(kd3Var2);
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            if (om3.g.b(this) && (viewGroupC = yn3.c(this)) != null) {
                int[] iArr = new int[2];
                viewGroupC.getLocationOnScreen(iArr);
                kd3Var2.M = iArr[0];
                viewGroupC.getWindowVisibleDisplayFrame(kd3Var2.G);
                viewGroupC.addOnLayoutChangeListener(kd3Var2.F);
            }
        }
        int i3 = arrayList2.size() == 1 ? 0 : 1;
        for (kd3 kd3Var3 : arrayList2) {
            kd3Var3.e.k = i3;
            kd3Var3.invalidateSelf();
        }
        for (rd rdVar : this.p) {
            Iterator<Float> it = this.L.iterator();
            while (it.hasNext()) {
                it.next().floatValue();
                rdVar.a();
            }
        }
        postInvalidate();
    }

    public final void a(Drawable drawable) {
        int i = this.D * 2;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            drawable.setBounds(0, 0, i, i);
        } else {
            float fMax = i / Math.max(intrinsicWidth, intrinsicHeight);
            drawable.setBounds(0, 0, (int) (intrinsicWidth * fMax), (int) (intrinsicHeight * fMax));
        }
    }

    public void addOnChangeListener(L l) {
        this.p.add(l);
    }

    public void addOnSliderTouchListener(T t) {
        this.q.add(t);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int b() {
        /*
            r5 = this;
            int r0 = r5.z
            int r0 = r0 / 2
            int r1 = r5.A
            r2 = 1
            r3 = 0
            if (r1 == r2) goto L11
            r4 = 3
            if (r1 != r4) goto Le
            goto Lf
        Le:
            r2 = r3
        Lf:
            if (r2 == 0) goto L1d
        L11:
            java.util.ArrayList r5 = r5.o
            java.lang.Object r5 = r5.get(r3)
            kd3 r5 = (defpackage.kd3) r5
            int r3 = r5.getIntrinsicHeight()
        L1d:
            int r0 = r0 + r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.b():int");
    }

    public final ValueAnimator c(boolean z) {
        float fFloatValue = z ? 0.0f : 1.0f;
        ValueAnimator valueAnimator = z ? this.t : this.s;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fFloatValue, z ? 1.0f : 0.0f);
        valueAnimatorOfFloat.setDuration(z ? 83L : 117L);
        valueAnimatorOfFloat.setInterpolator(z ? u5.e : u5.c);
        valueAnimatorOfFloat.addUpdateListener(new b());
        return valueAnimatorOfFloat;
    }

    public final void d(Canvas canvas, int i, int i2, float f, Drawable drawable) {
        canvas.save();
        canvas.translate((this.C + ((int) (m(f) * i))) - (drawable.getBounds().width() / 2.0f), i2 - (drawable.getBounds().height() / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.k.m(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        this.e.setColor(f(this.b0));
        this.f.setColor(f(this.a0));
        this.i.setColor(f(this.W));
        this.j.setColor(f(this.V));
        for (kd3 kd3Var : this.o) {
            if (kd3Var.isStateful()) {
                kd3Var.setState(getDrawableState());
            }
        }
        sc1 sc1Var = this.c0;
        if (sc1Var.isStateful()) {
            sc1Var.setState(getDrawableState());
        }
        Paint paint = this.h;
        paint.setColor(f(this.U));
        paint.setAlpha(63);
    }

    public final void e() {
        if (this.r) {
            this.r = false;
            ValueAnimator valueAnimatorC = c(false);
            this.t = valueAnimatorC;
            this.s = null;
            valueAnimatorC.addListener(new c());
            this.t.start();
        }
    }

    public final int f(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    public final boolean g() {
        ViewParent parent = getParent();
        while (true) {
            if (!(parent instanceof ViewGroup)) {
                return false;
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
            parent = parent.getParent();
        }
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.k.k;
    }

    public int getActiveThumbIndex() {
        return this.M;
    }

    public int getFocusedThumbIndex() {
        return this.N;
    }

    public int getHaloRadius() {
        return this.E;
    }

    public ColorStateList getHaloTintList() {
        return this.U;
    }

    public int getLabelBehavior() {
        return this.A;
    }

    public float getMinSeparation() {
        return 0.0f;
    }

    public float getStepSize() {
        return this.O;
    }

    public float getThumbElevation() {
        return this.c0.e.n;
    }

    public int getThumbRadius() {
        return this.D;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.c0.e.d;
    }

    public float getThumbStrokeWidth() {
        return this.c0.e.k;
    }

    public ColorStateList getThumbTintList() {
        return this.c0.e.c;
    }

    public ColorStateList getTickActiveTintList() {
        return this.V;
    }

    public ColorStateList getTickInactiveTintList() {
        return this.W;
    }

    public ColorStateList getTickTintList() {
        if (this.W.equals(this.V)) {
            return this.V;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    public ColorStateList getTrackActiveTintList() {
        return this.a0;
    }

    public int getTrackHeight() {
        return this.B;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.b0;
    }

    public int getTrackSidePadding() {
        return this.C;
    }

    public ColorStateList getTrackTintList() {
        if (this.b0.equals(this.a0)) {
            return this.a0;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    public int getTrackWidth() {
        return this.R;
    }

    public float getValueFrom() {
        return this.f34J;
    }

    public float getValueTo() {
        return this.K;
    }

    public List<Float> getValues() {
        return new ArrayList(this.L);
    }

    public final boolean h(float f) {
        double dDoubleValue = new BigDecimal(Float.toString(f)).divide(new BigDecimal(Float.toString(this.O)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(dDoubleValue)) - dDoubleValue) < 1.0E-4d;
    }

    public final boolean i() {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        return om3.e.c(this) == 1;
    }

    public final void j() {
        if (this.O <= 0.0f) {
            return;
        }
        u();
        int iMin = Math.min((int) (((this.K - this.f34J) / this.O) + 1.0f), (this.R / (this.B * 2)) + 1);
        float[] fArr = this.P;
        if (fArr == null || fArr.length != iMin * 2) {
            this.P = new float[iMin * 2];
        }
        float f = this.R / (iMin - 1);
        for (int i = 0; i < iMin * 2; i += 2) {
            float[] fArr2 = this.P;
            fArr2[i] = ((i / 2) * f) + this.C;
            fArr2[i + 1] = b();
        }
    }

    public final boolean k(int i) {
        int i2 = this.N;
        long j = ((long) i2) + ((long) i);
        long size = this.L.size() - 1;
        if (j < 0) {
            j = 0;
        } else if (j > size) {
            j = size;
        }
        int i3 = (int) j;
        this.N = i3;
        if (i3 == i2) {
            return false;
        }
        if (this.M != -1) {
            this.M = i3;
        }
        s();
        postInvalidate();
        return true;
    }

    public final void l(int i) {
        if (i()) {
            i = i == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i;
        }
        k(i);
    }

    public final float m(float f) {
        float f2 = this.f34J;
        float f3 = (f - f2) / (this.K - f2);
        return i() ? 1.0f - f3 : f3;
    }

    public final void n() {
        Iterator it = this.q.iterator();
        while (it.hasNext()) {
            ((sd) it.next()).b();
        }
    }

    public boolean o() {
        if (this.M != -1) {
            return true;
        }
        float valueOfTouchPositionAbsolute = getValueOfTouchPositionAbsolute();
        float fM = (m(valueOfTouchPositionAbsolute) * this.R) + this.C;
        this.M = 0;
        float fAbs = Math.abs(this.L.get(0).floatValue() - valueOfTouchPositionAbsolute);
        for (int i = 1; i < this.L.size(); i++) {
            float fAbs2 = Math.abs(this.L.get(i).floatValue() - valueOfTouchPositionAbsolute);
            float fM2 = (m(this.L.get(i).floatValue()) * this.R) + this.C;
            if (Float.compare(fAbs2, fAbs) > 1) {
                break;
            }
            boolean z = !i() ? fM2 - fM >= 0.0f : fM2 - fM <= 0.0f;
            if (Float.compare(fAbs2, fAbs) < 0) {
                this.M = i;
            } else {
                if (Float.compare(fAbs2, fAbs) != 0) {
                    continue;
                } else {
                    if (Math.abs(fM2 - fM) < this.u) {
                        this.M = -1;
                        return false;
                    }
                    if (z) {
                        this.M = i;
                    }
                }
            }
            fAbs = fAbs2;
        }
        return this.M != -1;
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        for (kd3 kd3Var : this.o) {
            ViewGroup viewGroupC = yn3.c(this);
            if (viewGroupC == null) {
                kd3Var.getClass();
            } else {
                kd3Var.getClass();
                int[] iArr = new int[2];
                viewGroupC.getLocationOnScreen(iArr);
                kd3Var.M = iArr[0];
                viewGroupC.getWindowVisibleDisplayFrame(kd3Var.G);
                viewGroupC.addOnLayoutChangeListener(kd3Var.F);
            }
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        BaseSlider<S, L, T>.d dVar = this.m;
        if (dVar != null) {
            removeCallbacks(dVar);
        }
        this.r = false;
        for (kd3 kd3Var : this.o) {
            ViewGroup viewGroupC = yn3.c(this);
            a41 a41Var = viewGroupC == null ? null : new a41(viewGroupC);
            if (a41Var != null) {
                ((ViewOverlay) a41Var.b).remove(kd3Var);
                ViewGroup viewGroupC2 = yn3.c(this);
                if (viewGroupC2 == null) {
                    kd3Var.getClass();
                } else {
                    viewGroupC2.removeOnLayoutChangeListener(kd3Var.F);
                }
            }
        }
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x019e  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onDraw(android.graphics.Canvas r13) {
        /*
            Method dump skipped, instruction units count: 514
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        e eVar = this.k;
        if (!z) {
            this.M = -1;
            eVar.j(this.N);
            return;
        }
        if (i == 1) {
            k(Integer.MAX_VALUE);
        } else if (i == 2) {
            k(Integer.MIN_VALUE);
        } else if (i == 17) {
            l(Integer.MAX_VALUE);
        } else if (i == 66) {
            l(Integer.MIN_VALUE);
        }
        eVar.x(this.N);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    @Override // android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onKeyDown(int r14, android.view.KeyEvent r15) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        this.S = false;
        return super.onKeyUp(i, keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x000f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r5, int r6) {
        /*
            r4 = this;
            int r6 = r4.z
            int r0 = r4.A
            r1 = 1
            r2 = 0
            if (r0 == r1) goto Lf
            r3 = 3
            if (r0 != r3) goto Lc
            goto Ld
        Lc:
            r1 = r2
        Ld:
            if (r1 == 0) goto L1b
        Lf:
            java.util.ArrayList r0 = r4.o
            java.lang.Object r0 = r0.get(r2)
            kd3 r0 = (defpackage.kd3) r0
            int r2 = r0.getIntrinsicHeight()
        L1b:
            int r6 = r6 + r2
            r0 = 1073741824(0x40000000, float:2.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r6, r0)
            super.onMeasure(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.f34J = sliderState.e;
        this.K = sliderState.f;
        setValuesInternal(sliderState.g);
        this.O = sliderState.h;
        if (sliderState.i) {
            requestFocus();
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.e = this.f34J;
        sliderState.f = this.K;
        sliderState.g = new ArrayList<>(this.L);
        sliderState.h = this.O;
        sliderState.i = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        this.R = Math.max(i - (this.C * 2), 0);
        j();
        s();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float x = motionEvent.getX();
        float f = (x - this.C) / this.R;
        this.f0 = f;
        float fMax = Math.max(0.0f, f);
        this.f0 = fMax;
        this.f0 = Math.min(1.0f, fMax);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            int i = this.u;
            if (actionMasked == 1) {
                this.I = false;
                MotionEvent motionEvent2 = this.H;
                if (motionEvent2 != null && motionEvent2.getActionMasked() == 0) {
                    float f2 = i;
                    if (Math.abs(this.H.getX() - motionEvent.getX()) <= f2 && Math.abs(this.H.getY() - motionEvent.getY()) <= f2 && o()) {
                        n();
                    }
                }
                if (this.M != -1) {
                    q(getValueOfTouchPosition(), this.M);
                    this.M = -1;
                    Iterator it = this.q.iterator();
                    while (it.hasNext()) {
                        ((sd) it.next()).a();
                    }
                }
                invalidate();
            } else if (actionMasked == 2) {
                if (!this.I) {
                    if (g() && Math.abs(x - this.G) < i) {
                        return false;
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                    n();
                }
                if (o()) {
                    this.I = true;
                    q(getValueOfTouchPosition(), this.M);
                    s();
                    invalidate();
                }
            }
        } else {
            this.G = x;
            if (!g()) {
                getParent().requestDisallowInterceptTouchEvent(true);
                if (o()) {
                    requestFocus();
                    this.I = true;
                    q(getValueOfTouchPosition(), this.M);
                    s();
                    invalidate();
                    n();
                }
            }
        }
        setPressed(this.I);
        this.H = MotionEvent.obtain(motionEvent);
        return true;
    }

    public final void p(kd3 kd3Var, float f) {
        String str = String.format(((float) ((int) f)) == f ? "%.0f" : "%.2f", Float.valueOf(f));
        if (!TextUtils.equals(kd3Var.B, str)) {
            kd3Var.B = str;
            kd3Var.E.d = true;
            kd3Var.invalidateSelf();
        }
        int iM = (this.C + ((int) (m(f) * this.R))) - (kd3Var.getIntrinsicWidth() / 2);
        int iB = b() - (this.F + this.D);
        kd3Var.setBounds(iM, iB - kd3Var.getIntrinsicHeight(), kd3Var.getIntrinsicWidth() + iM, iB);
        Rect rect = new Rect(kd3Var.getBounds());
        p80.c(yn3.c(this), this, rect);
        kd3Var.setBounds(rect);
        ViewGroup viewGroupC = yn3.c(this);
        ((ViewOverlay) (viewGroupC == null ? null : new a41(viewGroupC)).b).add(kd3Var);
    }

    public final boolean q(float f, int i) {
        this.N = i;
        if (Math.abs(f - this.L.get(i).floatValue()) < 1.0E-4d) {
            return false;
        }
        float minSeparation = getMinSeparation();
        if (this.g0 == 0) {
            if (minSeparation == 0.0f) {
                minSeparation = 0.0f;
            } else {
                float f2 = this.f34J;
                minSeparation = C0173a.f(f2, this.K, (minSeparation - this.C) / this.R, f2);
            }
        }
        if (i()) {
            minSeparation = -minSeparation;
        }
        int i2 = i + 1;
        float fFloatValue = i2 >= this.L.size() ? this.K : this.L.get(i2).floatValue() - minSeparation;
        int i3 = i - 1;
        float fFloatValue2 = i3 < 0 ? this.f34J : minSeparation + this.L.get(i3).floatValue();
        if (f < fFloatValue2) {
            f = fFloatValue2;
        } else if (f > fFloatValue) {
            f = fFloatValue;
        }
        this.L.set(i, Float.valueOf(f));
        for (rd rdVar : this.p) {
            this.L.get(i).floatValue();
            rdVar.a();
        }
        AccessibilityManager accessibilityManager = this.l;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return true;
        }
        BaseSlider<S, L, T>.d dVar = this.m;
        if (dVar == null) {
            this.m = new d();
        } else {
            removeCallbacks(dVar);
        }
        BaseSlider<S, L, T>.d dVar2 = this.m;
        dVar2.e = i;
        postDelayed(dVar2, 200L);
        return true;
    }

    public final void r(int i, Rect rect) {
        int iM = this.C + ((int) (m(getValues().get(i).floatValue()) * this.R));
        int iB = b();
        int i2 = this.D;
        rect.set(iM - i2, iB - i2, iM + i2, iB + i2);
    }

    public void removeOnChangeListener(L l) {
        this.p.remove(l);
    }

    public void removeOnSliderTouchListener(T t) {
        this.q.remove(t);
    }

    public final void s() {
        if ((!(getBackground() instanceof RippleDrawable)) || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int iM = (int) ((m(this.L.get(this.N).floatValue()) * this.R) + this.C);
            int iB = b();
            int i = this.E;
            background.setHotspotBounds(iM - i, iB - i, iM + i, iB + i);
        }
    }

    public void setActiveThumbIndex(int i) {
        this.M = i;
    }

    public void setCustomThumbDrawable(int i) {
        setCustomThumbDrawable(getResources().getDrawable(i));
    }

    public void setCustomThumbDrawablesForValues(int... iArr) {
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            drawableArr[i] = getResources().getDrawable(iArr[i]);
        }
        setCustomThumbDrawablesForValues(drawableArr);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setLayerType(z ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i) {
        if (i < 0 || i >= this.L.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.N = i;
        this.k.x(i);
        postInvalidate();
    }

    public void setHaloRadius(int i) {
        if (i == this.E) {
            return;
        }
        this.E = i;
        Drawable background = getBackground();
        if ((!(getBackground() instanceof RippleDrawable)) || !(background instanceof RippleDrawable)) {
            postInvalidate();
        } else {
            ((RippleDrawable) background).setRadius(this.E);
        }
    }

    public void setHaloRadiusResource(int i) {
        setHaloRadius(getResources().getDimensionPixelSize(i));
    }

    public void setHaloTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.U)) {
            return;
        }
        this.U = colorStateList;
        Drawable background = getBackground();
        if (!(!(getBackground() instanceof RippleDrawable)) && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        Paint paint = this.h;
        paint.setColor(f(colorStateList));
        paint.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i) {
        if (this.A != i) {
            this.A = i;
            requestLayout();
        }
    }

    public void setSeparationUnit(int i) {
        this.g0 = i;
        this.T = true;
        postInvalidate();
    }

    public void setStepSize(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.valueOf(f), Float.valueOf(this.f34J), Float.valueOf(this.K)));
        }
        if (this.O != f) {
            this.O = f;
            this.T = true;
            postInvalidate();
        }
    }

    public void setThumbElevation(float f) {
        this.c0.m(f);
    }

    public void setThumbElevationResource(int i) {
        setThumbElevation(getResources().getDimension(i));
    }

    public void setThumbRadius(int i) {
        if (i == this.D) {
            return;
        }
        this.D = i;
        sc1 sc1Var = this.c0;
        uj2.a aVar = new uj2.a();
        float f = this.D;
        d70 d70VarF = oj2.f(0);
        aVar.a = d70VarF;
        float fB = uj2.a.b(d70VarF);
        if (fB != -1.0f) {
            aVar.f(fB);
        }
        aVar.b = d70VarF;
        float fB2 = uj2.a.b(d70VarF);
        if (fB2 != -1.0f) {
            aVar.g(fB2);
        }
        aVar.c = d70VarF;
        float fB3 = uj2.a.b(d70VarF);
        if (fB3 != -1.0f) {
            aVar.e(fB3);
        }
        aVar.d = d70VarF;
        float fB4 = uj2.a.b(d70VarF);
        if (fB4 != -1.0f) {
            aVar.d(fB4);
        }
        aVar.c(f);
        sc1Var.setShapeAppearanceModel(new uj2(aVar));
        int i2 = this.D * 2;
        sc1Var.setBounds(0, 0, i2, i2);
        Drawable drawable = this.d0;
        if (drawable != null) {
            a(drawable);
        }
        Iterator<Drawable> it = this.e0.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        t();
    }

    public void setThumbRadiusResource(int i) {
        setThumbRadius(getResources().getDimensionPixelSize(i));
    }

    public void setThumbStrokeColor(ColorStateList colorStateList) {
        this.c0.s(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeColorResource(int i) {
        if (i != 0) {
            setThumbStrokeColor(bz.b(getContext(), i));
        }
    }

    public void setThumbStrokeWidth(float f) {
        sc1 sc1Var = this.c0;
        sc1Var.e.k = f;
        sc1Var.invalidateSelf();
        postInvalidate();
    }

    public void setThumbStrokeWidthResource(int i) {
        if (i != 0) {
            setThumbStrokeWidth(getResources().getDimension(i));
        }
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        sc1 sc1Var = this.c0;
        if (colorStateList.equals(sc1Var.e.c)) {
            return;
        }
        sc1Var.n(colorStateList);
        invalidate();
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.V)) {
            return;
        }
        this.V = colorStateList;
        this.j.setColor(f(colorStateList));
        invalidate();
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.W)) {
            return;
        }
        this.W = colorStateList;
        this.i.setColor(f(colorStateList));
        invalidate();
    }

    public void setTickTintList(ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public void setTickVisible(boolean z) {
        if (this.Q != z) {
            this.Q = z;
            postInvalidate();
        }
    }

    public void setTrackActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.a0)) {
            return;
        }
        this.a0 = colorStateList;
        this.f.setColor(f(colorStateList));
        invalidate();
    }

    public void setTrackHeight(int i) {
        if (this.B != i) {
            this.B = i;
            this.e.setStrokeWidth(i);
            this.f.setStrokeWidth(this.B);
            this.i.setStrokeWidth(this.B / 2.0f);
            this.j.setStrokeWidth(this.B / 2.0f);
            t();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.b0)) {
            return;
        }
        this.b0 = colorStateList;
        this.e.setColor(f(colorStateList));
        invalidate();
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f) {
        this.f34J = f;
        this.T = true;
        postInvalidate();
    }

    public void setValueTo(float f) {
        this.K = f;
        this.T = true;
        postInvalidate();
    }

    public void setValues(Float... fArr) {
        ArrayList<Float> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    public final void t() {
        boolean z;
        int iMax = Math.max(this.y, Math.max(this.B + getPaddingBottom() + getPaddingTop(), getPaddingBottom() + getPaddingTop() + (this.D * 2)));
        boolean z2 = false;
        if (iMax == this.z) {
            z = false;
        } else {
            this.z = iMax;
            z = true;
        }
        int iMax2 = Math.max(Math.max(this.D - this.w, 0), Math.max((this.B - this.x) / 2, 0)) + this.v;
        if (this.C != iMax2) {
            this.C = iMax2;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.c(this)) {
                this.R = Math.max(getWidth() - (this.C * 2), 0);
                j();
            }
            z2 = true;
        }
        if (z) {
            requestLayout();
        } else if (z2) {
            postInvalidate();
        }
    }

    public final void u() {
        if (this.T) {
            float f = this.f34J;
            float f2 = this.K;
            if (f >= f2) {
                throw new IllegalStateException(String.format("valueFrom(%s) must be smaller than valueTo(%s)", Float.valueOf(this.f34J), Float.valueOf(this.K)));
            }
            if (f2 <= f) {
                throw new IllegalStateException(String.format("valueTo(%s) must be greater than valueFrom(%s)", Float.valueOf(this.K), Float.valueOf(this.f34J)));
            }
            if (this.O > 0.0f && !h(f2 - f)) {
                throw new IllegalStateException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.valueOf(this.O), Float.valueOf(this.f34J), Float.valueOf(this.K)));
            }
            for (Float f3 : this.L) {
                if (f3.floatValue() < this.f34J || f3.floatValue() > this.K) {
                    throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", f3, Float.valueOf(this.f34J), Float.valueOf(this.K)));
                }
                if (this.O > 0.0f && !h(f3.floatValue() - this.f34J)) {
                    throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", f3, Float.valueOf(this.f34J), Float.valueOf(this.O), Float.valueOf(this.O)));
                }
            }
            float minSeparation = getMinSeparation();
            if (minSeparation < 0.0f) {
                throw new IllegalStateException(String.format("minSeparation(%s) must be greater or equal to 0", Float.valueOf(minSeparation)));
            }
            float f4 = this.O;
            if (f4 > 0.0f && minSeparation > 0.0f) {
                if (this.g0 != 1) {
                    throw new IllegalStateException(String.format("minSeparation(%s) cannot be set as a dimension when using stepSize(%s)", Float.valueOf(minSeparation), Float.valueOf(this.O)));
                }
                if (minSeparation < f4 || !h(minSeparation)) {
                    throw new IllegalStateException(String.format("minSeparation(%s) must be greater or equal and a multiple of stepSize(%s) when using stepSize(%s)", Float.valueOf(minSeparation), Float.valueOf(this.O), Float.valueOf(this.O)));
                }
            }
            float f5 = this.O;
            if (f5 != 0.0f) {
                if (((int) f5) != f5) {
                    Log.w("BaseSlider", String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "stepSize", Float.valueOf(f5)));
                }
                float f6 = this.f34J;
                if (((int) f6) != f6) {
                    Log.w("BaseSlider", String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "valueFrom", Float.valueOf(f6)));
                }
                float f7 = this.K;
                if (((int) f7) != f7) {
                    Log.w("BaseSlider", String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.", "valueTo", Float.valueOf(f7)));
                }
            }
            this.T = false;
        }
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.sliderStyle);
    }

    public void setCustomThumbDrawable(Drawable drawable) {
        Drawable drawableNewDrawable = drawable.mutate().getConstantState().newDrawable();
        a(drawableNewDrawable);
        this.d0 = drawableNewDrawable;
        this.e0.clear();
        postInvalidate();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BaseSlider(Context context, AttributeSet attributeSet, int i) {
        int i2 = h0;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        this.o = new ArrayList();
        this.p = new ArrayList();
        this.q = new ArrayList();
        this.r = false;
        this.I = false;
        this.L = new ArrayList<>();
        this.M = -1;
        this.N = -1;
        this.O = 0.0f;
        this.Q = true;
        this.S = false;
        sc1 sc1Var = new sc1();
        this.c0 = sc1Var;
        this.e0 = Collections.emptyList();
        this.g0 = 0;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.e = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.f = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint(1);
        this.g = paint3;
        paint3.setStyle(Paint.Style.FILL);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.h = paint4;
        paint4.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.i = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        Paint paint6 = new Paint();
        this.j = paint6;
        paint6.setStyle(Paint.Style.STROKE);
        paint6.setStrokeCap(Paint.Cap.ROUND);
        Resources resources = context2.getResources();
        this.y = resources.getDimensionPixelSize(R$dimen.mtrl_slider_widget_height);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.mtrl_slider_track_side_padding);
        this.v = dimensionPixelOffset;
        this.C = dimensionPixelOffset;
        this.w = resources.getDimensionPixelSize(R$dimen.mtrl_slider_thumb_radius);
        this.x = resources.getDimensionPixelSize(R$dimen.mtrl_slider_track_height);
        this.F = resources.getDimensionPixelSize(R$dimen.mtrl_slider_label_padding);
        this.n = new a(attributeSet, i);
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.Slider, i, i2, new int[0]);
        this.f34J = typedArrayD.getFloat(R$styleable.Slider_android_valueFrom, 0.0f);
        this.K = typedArrayD.getFloat(R$styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.f34J));
        this.O = typedArrayD.getFloat(R$styleable.Slider_android_stepSize, 0.0f);
        int i3 = R$styleable.Slider_trackColor;
        boolean zHasValue = typedArrayD.hasValue(i3);
        int i4 = zHasValue ? i3 : R$styleable.Slider_trackColorInactive;
        i3 = zHasValue ? i3 : R$styleable.Slider_trackColorActive;
        ColorStateList colorStateListA = rc1.a(context2, typedArrayD, i4);
        setTrackInactiveTintList(colorStateListA == null ? bz.b(context2, R$color.material_slider_inactive_track_color) : colorStateListA);
        ColorStateList colorStateListA2 = rc1.a(context2, typedArrayD, i3);
        setTrackActiveTintList(colorStateListA2 == null ? bz.b(context2, R$color.material_slider_active_track_color) : colorStateListA2);
        sc1Var.n(rc1.a(context2, typedArrayD, R$styleable.Slider_thumbColor));
        int i5 = R$styleable.Slider_thumbStrokeColor;
        if (typedArrayD.hasValue(i5)) {
            setThumbStrokeColor(rc1.a(context2, typedArrayD, i5));
        }
        setThumbStrokeWidth(typedArrayD.getDimension(R$styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList colorStateListA3 = rc1.a(context2, typedArrayD, R$styleable.Slider_haloColor);
        setHaloTintList(colorStateListA3 == null ? bz.b(context2, R$color.material_slider_halo_color) : colorStateListA3);
        this.Q = typedArrayD.getBoolean(R$styleable.Slider_tickVisible, true);
        int i6 = R$styleable.Slider_tickColor;
        boolean zHasValue2 = typedArrayD.hasValue(i6);
        int i7 = zHasValue2 ? i6 : R$styleable.Slider_tickColorInactive;
        i6 = zHasValue2 ? i6 : R$styleable.Slider_tickColorActive;
        ColorStateList colorStateListA4 = rc1.a(context2, typedArrayD, i7);
        setTickInactiveTintList(colorStateListA4 == null ? bz.b(context2, R$color.material_slider_inactive_tick_marks_color) : colorStateListA4);
        ColorStateList colorStateListA5 = rc1.a(context2, typedArrayD, i6);
        setTickActiveTintList(colorStateListA5 == null ? bz.b(context2, R$color.material_slider_active_tick_marks_color) : colorStateListA5);
        setThumbRadius(typedArrayD.getDimensionPixelSize(R$styleable.Slider_thumbRadius, 0));
        setHaloRadius(typedArrayD.getDimensionPixelSize(R$styleable.Slider_haloRadius, 0));
        setThumbElevation(typedArrayD.getDimension(R$styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(typedArrayD.getDimensionPixelSize(R$styleable.Slider_trackHeight, 0));
        setLabelBehavior(typedArrayD.getInt(R$styleable.Slider_labelBehavior, 0));
        if (!typedArrayD.getBoolean(R$styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        typedArrayD.recycle();
        setFocusable(true);
        setClickable(true);
        sc1Var.r();
        this.u = ViewConfiguration.get(context2).getScaledTouchSlop();
        e eVar = new e(this);
        this.k = eVar;
        om3.k(this, eVar);
        this.l = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public void setValues(List<Float> list) {
        setValuesInternal(new ArrayList<>(list));
    }

    public void setCustomThumbDrawablesForValues(Drawable... drawableArr) {
        this.d0 = null;
        this.e0 = new ArrayList();
        for (Drawable drawable : drawableArr) {
            List<Drawable> list = this.e0;
            Drawable drawableNewDrawable = drawable.mutate().getConstantState().newDrawable();
            a(drawableNewDrawable);
            list.add(drawableNewDrawable);
        }
        postInvalidate();
    }

    public void setLabelFormatter(y41 y41Var) {
    }
}
