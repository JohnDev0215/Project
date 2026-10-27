package com.google.android.material.slider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.SeekBar;
import c.g.k.c0.c;
import c.g.k.t;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.android.volley.BuildConfig;
import com.google.android.material.internal.o;
import com.google.android.material.internal.p;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.slider.a;
import com.google.android.material.slider.b;
import d.b.a.a.j;
import d.b.a.a.k;
import d.b.a.a.l;
import d.b.a.a.w.h;
import d.b.a.a.w.m;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends com.google.android.material.slider.a<S>, T extends com.google.android.material.slider.b<S>> extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3664b = BaseSlider.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3665c = k.C;
    private MotionEvent A;
    private com.google.android.material.slider.c B;
    private boolean C;
    private float D;
    private float E;
    private ArrayList<Float> F;
    private int G;
    private int H;
    private float I;
    private float[] J;
    private int K;
    private boolean L;
    private boolean M;
    private boolean N;
    private ColorStateList O;
    private ColorStateList P;
    private ColorStateList Q;
    private ColorStateList R;
    private ColorStateList S;
    private final h T;
    private float U;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Paint f3666d;
    private final Paint e;
    private final Paint f;
    private final Paint g;
    private final Paint h;
    private final Paint i;
    private final c j;
    private final AccessibilityManager k;
    private BaseSlider<S, L, T>.b l;
    private final d m;
    private final List<d.b.a.a.x.a> n;
    private final List<L> o;
    private final List<T> p;
    private final int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private float z;

    static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f3667b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f3668c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        ArrayList<Float> f3669d;
        float e;
        boolean f;

        static class a implements Parcelable.Creator<SliderState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SliderState createFromParcel(Parcel parcel) {
                return new SliderState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SliderState[] newArray(int i) {
                return new SliderState[i];
            }
        }

        /* synthetic */ SliderState(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.f3667b);
            parcel.writeFloat(this.f3668c);
            parcel.writeList(this.f3669d);
            parcel.writeFloat(this.e);
            parcel.writeBooleanArray(new boolean[]{this.f});
        }

        SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        private SliderState(Parcel parcel) {
            super(parcel);
            this.f3667b = parcel.readFloat();
            this.f3668c = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.f3669d = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.e = parcel.readFloat();
            this.f = parcel.createBooleanArray()[0];
        }
    }

    class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ AttributeSet f3670a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3671b;

        a(AttributeSet attributeSet, int i) {
            this.f3670a = attributeSet;
            this.f3671b = i;
        }

        @Override // com.google.android.material.slider.BaseSlider.d
        public d.b.a.a.x.a a() {
            TypedArray typedArrayH = com.google.android.material.internal.k.h(BaseSlider.this.getContext(), this.f3670a, l.f5, this.f3671b, BaseSlider.f3665c, new int[0]);
            d.b.a.a.x.a aVarL = BaseSlider.L(BaseSlider.this.getContext(), typedArrayH);
            typedArrayH.recycle();
            return aVarL;
        }
    }

    private static class c extends c.i.a.a {
        private final BaseSlider<?, ?, ?> q;
        Rect r;

        c(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.r = new Rect();
            this.q = baseSlider;
        }

        private String Y(int i) {
            return i == this.q.getValues().size() + (-1) ? this.q.getContext().getString(j.h) : i == 0 ? this.q.getContext().getString(j.i) : BuildConfig.FLAVOR;
        }

        @Override // c.i.a.a
        protected int B(float f, float f2) {
            for (int i = 0; i < this.q.getValues().size(); i++) {
                this.q.W(i, this.r);
                if (this.r.contains((int) f, (int) f2)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // c.i.a.a
        protected void C(List<Integer> list) {
            for (int i = 0; i < this.q.getValues().size(); i++) {
                list.add(Integer.valueOf(i));
            }
        }

        @Override // c.i.a.a
        protected boolean L(int i, int i2, Bundle bundle) {
            if (!this.q.isEnabled()) {
                return false;
            }
            if (i2 != 4096 && i2 != 8192) {
                if (i2 == 16908349 && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                    if (this.q.U(i, bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"))) {
                        this.q.X();
                        this.q.postInvalidate();
                        E(i);
                        return true;
                    }
                }
                return false;
            }
            float fK = this.q.k(20);
            if (i2 == 8192) {
                fK = -fK;
            }
            if (this.q.C()) {
                fK = -fK;
            }
            if (!this.q.U(i, c.g.f.a.a(this.q.getValues().get(i).floatValue() + fK, this.q.getValueFrom(), this.q.getValueTo()))) {
                return false;
            }
            this.q.X();
            this.q.postInvalidate();
            E(i);
            return true;
        }

        @Override // c.i.a.a
        protected void P(int i, c.g.k.c0.c cVar) {
            cVar.b(c.a.H);
            List<Float> values = this.q.getValues();
            float fFloatValue = values.get(i).floatValue();
            float valueFrom = this.q.getValueFrom();
            float valueTo = this.q.getValueTo();
            if (this.q.isEnabled()) {
                if (fFloatValue > valueFrom) {
                    cVar.a(IdentityHashMap.DEFAULT_SIZE);
                }
                if (fFloatValue < valueTo) {
                    cVar.a(4096);
                }
            }
            cVar.s0(c.d.a(1, valueFrom, valueTo, fFloatValue));
            cVar.b0(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.q.getContentDescription() != null) {
                sb.append(this.q.getContentDescription());
                sb.append(",");
            }
            if (values.size() > 1) {
                sb.append(Y(i));
                sb.append(this.q.x(fFloatValue));
            }
            cVar.f0(sb.toString());
            this.q.W(i, this.r);
            cVar.W(this.r);
        }
    }

    private interface d {
        d.b.a.a.x.a a();
    }

    public BaseSlider(Context context) {
        this(context, null);
    }

    private void B() {
        this.f3666d.setStrokeWidth(this.t);
        this.e.setStrokeWidth(this.t);
        this.h.setStrokeWidth(this.t / 2.0f);
        this.i.setStrokeWidth(this.t / 2.0f);
    }

    private void D(Resources resources) {
        this.r = resources.getDimensionPixelSize(d.b.a.a.d.M);
        this.u = resources.getDimensionPixelOffset(d.b.a.a.d.K);
        this.v = resources.getDimensionPixelOffset(d.b.a.a.d.L);
        this.y = resources.getDimensionPixelSize(d.b.a.a.d.J);
    }

    private void E(Canvas canvas, int i, int i2) {
        if (R()) {
            int iH = (int) (this.u + (H(this.F.get(this.H).floatValue()) * i));
            if (Build.VERSION.SDK_INT < 28) {
                int i3 = this.x;
                canvas.clipRect(iH - i3, i2 - i3, iH + i3, i3 + i2, Region.Op.UNION);
            }
            canvas.drawCircle(iH, i2, this.x, this.g);
        }
    }

    private boolean F(int i) {
        int i2 = this.H;
        int iC = (int) c.g.f.a.c(((long) i2) + ((long) i), 0L, this.F.size() - 1);
        this.H = iC;
        if (iC == i2) {
            return false;
        }
        if (this.G != -1) {
            this.G = iC;
        }
        X();
        postInvalidate();
        return true;
    }

    private boolean G(int i) {
        if (C()) {
            i = i == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i;
        }
        return F(i);
    }

    private float H(float f) {
        float f2 = this.D;
        float f3 = (f - f2) / (this.E - f2);
        return C() ? 1.0f - f3 : f3;
    }

    private Boolean I(int i, KeyEvent keyEvent) {
        if (i == 61) {
            return keyEvent.hasNoModifiers() ? Boolean.valueOf(F(1)) : keyEvent.isShiftPressed() ? Boolean.valueOf(F(-1)) : Boolean.FALSE;
        }
        if (i != 66) {
            if (i != 81) {
                if (i == 69) {
                    F(-1);
                    return Boolean.TRUE;
                }
                if (i != 70) {
                    switch (i) {
                        case 21:
                            G(-1);
                            break;
                        case 22:
                            G(1);
                            break;
                    }
                    return Boolean.TRUE;
                }
            }
            F(1);
            return Boolean.TRUE;
        }
        this.G = this.H;
        postInvalidate();
        return Boolean.TRUE;
    }

    private void J() {
        Iterator<T> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    private void K() {
        Iterator<T> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static d.b.a.a.x.a L(Context context, TypedArray typedArray) {
        return d.b.a.a.x.a.r0(context, null, 0, typedArray.getResourceId(l.n5, k.E));
    }

    private static int N(float[] fArr, float f) {
        return Math.round(f * ((fArr.length / 2) - 1));
    }

    private void O(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayH = com.google.android.material.internal.k.h(context, attributeSet, l.f5, i, f3665c, new int[0]);
        this.D = typedArrayH.getFloat(l.i5, 0.0f);
        this.E = typedArrayH.getFloat(l.j5, 1.0f);
        setValues(Float.valueOf(this.D));
        this.I = typedArrayH.getFloat(l.h5, 0.0f);
        int i2 = l.u5;
        boolean zHasValue = typedArrayH.hasValue(i2);
        int i3 = zHasValue ? i2 : l.w5;
        if (!zHasValue) {
            i2 = l.v5;
        }
        ColorStateList colorStateListA = d.b.a.a.t.c.a(context, typedArrayH, i3);
        if (colorStateListA == null) {
            colorStateListA = c.a.k.a.a.c(context, d.b.a.a.c.k);
        }
        setTrackInactiveTintList(colorStateListA);
        ColorStateList colorStateListA2 = d.b.a.a.t.c.a(context, typedArrayH, i2);
        if (colorStateListA2 == null) {
            colorStateListA2 = c.a.k.a.a.c(context, d.b.a.a.c.h);
        }
        setTrackActiveTintList(colorStateListA2);
        this.T.X(d.b.a.a.t.c.a(context, typedArrayH, l.o5));
        ColorStateList colorStateListA3 = d.b.a.a.t.c.a(context, typedArrayH, l.k5);
        if (colorStateListA3 == null) {
            colorStateListA3 = c.a.k.a.a.c(context, d.b.a.a.c.i);
        }
        setHaloTintList(colorStateListA3);
        int i4 = l.r5;
        boolean zHasValue2 = typedArrayH.hasValue(i4);
        int i5 = zHasValue2 ? i4 : l.t5;
        if (!zHasValue2) {
            i4 = l.s5;
        }
        ColorStateList colorStateListA4 = d.b.a.a.t.c.a(context, typedArrayH, i5);
        if (colorStateListA4 == null) {
            colorStateListA4 = c.a.k.a.a.c(context, d.b.a.a.c.j);
        }
        setTickInactiveTintList(colorStateListA4);
        ColorStateList colorStateListA5 = d.b.a.a.t.c.a(context, typedArrayH, i4);
        if (colorStateListA5 == null) {
            colorStateListA5 = c.a.k.a.a.c(context, d.b.a.a.c.g);
        }
        setTickActiveTintList(colorStateListA5);
        setThumbRadius(typedArrayH.getDimensionPixelSize(l.q5, 0));
        setHaloRadius(typedArrayH.getDimensionPixelSize(l.l5, 0));
        setThumbElevation(typedArrayH.getDimension(l.p5, 0.0f));
        setTrackHeight(typedArrayH.getDimensionPixelSize(l.x5, 0));
        this.s = typedArrayH.getInt(l.m5, 0);
        if (!typedArrayH.getBoolean(l.g5, true)) {
            setEnabled(false);
        }
        typedArrayH.recycle();
    }

    private void P(int i) {
        BaseSlider<S, L, T>.b bVar = this.l;
        if (bVar == null) {
            this.l = new b(this, null);
        } else {
            removeCallbacks(bVar);
        }
        this.l.a(i);
        postDelayed(this.l, 200L);
    }

    private void Q(d.b.a.a.x.a aVar, float f) {
        aVar.y0(x(f));
        int iH = (this.u + ((int) (H(f) * this.K))) - (aVar.getIntrinsicWidth() / 2);
        int iM = m() - (this.y + this.w);
        aVar.setBounds(iH, iM - aVar.getIntrinsicHeight(), aVar.getIntrinsicWidth() + iH, iM);
        Rect rect = new Rect(aVar.getBounds());
        com.google.android.material.internal.c.c(p.d(this), this, rect);
        aVar.setBounds(rect);
        p.e(this).a(aVar);
    }

    private boolean R() {
        return this.L || Build.VERSION.SDK_INT < 21 || !(getBackground() instanceof RippleDrawable);
    }

    private boolean S(float f) {
        return U(this.G, f);
    }

    private double T(float f) {
        float f2 = this.I;
        if (f2 <= 0.0f) {
            return f;
        }
        int i = (int) ((this.E - this.D) / f2);
        return ((double) Math.round(f * i)) / ((double) i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean U(int i, float f) {
        if (Math.abs(f - this.F.get(i).floatValue()) < 1.0E-4d) {
            return false;
        }
        this.F.set(i, Float.valueOf(y(i, f)));
        this.H = i;
        p(i);
        return true;
    }

    private boolean V() {
        return S(getValueOfTouchPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X() {
        if (R() || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int iH = (int) ((H(this.F.get(this.H).floatValue()) * this.K) + this.u);
            int iM = m();
            int i = this.x;
            androidx.core.graphics.drawable.a.l(background, iH - i, iM - i, iH + i, iM + i);
        }
    }

    private void Y() {
        if (this.N) {
            a0();
            b0();
            Z();
            c0();
            f0();
            this.N = false;
        }
    }

    private void Z() {
        if (this.I > 0.0f && !d0(this.E)) {
            throw new IllegalStateException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.toString(this.I), Float.toString(this.D), Float.toString(this.E)));
        }
    }

    private void a0() {
        if (this.D >= this.E) {
            throw new IllegalStateException(String.format("valueFrom(%s) must be smaller than valueTo(%s)", Float.toString(this.D), Float.toString(this.E)));
        }
    }

    private void b0() {
        if (this.E <= this.D) {
            throw new IllegalStateException(String.format("valueTo(%s) must be greater than valueFrom(%s)", Float.toString(this.E), Float.toString(this.D)));
        }
    }

    private void c0() {
        for (Float f : this.F) {
            if (f.floatValue() < this.D || f.floatValue() > this.E) {
                throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", Float.toString(f.floatValue()), Float.toString(this.D), Float.toString(this.E)));
            }
            if (this.I > 0.0f && !d0(f.floatValue())) {
                throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", Float.toString(f.floatValue()), Float.toString(this.D), Float.toString(this.I), Float.toString(this.I)));
            }
        }
    }

    private boolean d0(float f) {
        double dDoubleValue = new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Float.toString(this.D))).divide(new BigDecimal(Float.toString(this.I)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(dDoubleValue)) - dDoubleValue) < 1.0E-4d;
    }

    private float e0(float f) {
        return (H(f) * this.K) + this.u;
    }

    private void f0() {
        float f = this.I;
        if (f == 0.0f) {
            return;
        }
        if (((int) f) != f) {
            Log.w(f3664b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "stepSize", Float.valueOf(f)));
        }
        float f2 = this.D;
        if (((int) f2) != f2) {
            Log.w(f3664b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueFrom", Float.valueOf(f2)));
        }
        float f3 = this.E;
        if (((int) f3) != f3) {
            Log.w(f3664b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueTo", Float.valueOf(f3)));
        }
    }

    private float[] getActiveRange() {
        float fFloatValue = ((Float) Collections.max(getValues())).floatValue();
        float fFloatValue2 = ((Float) Collections.min(getValues())).floatValue();
        if (this.F.size() == 1) {
            fFloatValue2 = this.D;
        }
        float fH = H(fFloatValue2);
        float fH2 = H(fFloatValue);
        return C() ? new float[]{fH2, fH} : new float[]{fH, fH2};
    }

    private float getValueOfTouchPosition() {
        double dT = T(this.U);
        if (C()) {
            dT = 1.0d - dT;
        }
        float f = this.E;
        float f2 = this.D;
        return (float) ((dT * ((double) (f - f2))) + ((double) f2));
    }

    private float getValueOfTouchPositionAbsolute() {
        float f = this.U;
        if (C()) {
            f = 1.0f - f;
        }
        float f2 = this.E;
        float f3 = this.D;
        return (f * (f2 - f3)) + f3;
    }

    private void h(d.b.a.a.x.a aVar) {
        aVar.x0(p.d(this));
    }

    private Float i(int i) {
        float fK = this.M ? k(20) : j();
        if (i == 21) {
            if (!C()) {
                fK = -fK;
            }
            return Float.valueOf(fK);
        }
        if (i == 22) {
            if (C()) {
                fK = -fK;
            }
            return Float.valueOf(fK);
        }
        if (i == 69) {
            return Float.valueOf(-fK);
        }
        if (i == 70 || i == 81) {
            return Float.valueOf(fK);
        }
        return null;
    }

    private boolean isInScrollingContainer() {
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    private float j() {
        float f = this.I;
        if (f == 0.0f) {
            return 1.0f;
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float k(int i) {
        float fJ = j();
        return (this.E - this.D) / fJ <= i ? fJ : Math.round(r1 / r4) * fJ;
    }

    private void l() {
        Y();
        int iMin = Math.min((int) (((this.E - this.D) / this.I) + 1.0f), (this.K / (this.t * 2)) + 1);
        float[] fArr = this.J;
        if (fArr == null || fArr.length != iMin * 2) {
            this.J = new float[iMin * 2];
        }
        float f = this.K / (iMin - 1);
        for (int i = 0; i < iMin * 2; i += 2) {
            float[] fArr2 = this.J;
            fArr2[i] = this.u + ((i / 2) * f);
            fArr2[i + 1] = m();
        }
    }

    private int m() {
        return this.v + (this.s == 1 ? this.n.get(0).getIntrinsicHeight() : 0);
    }

    private void n() {
        if (this.n.size() > this.F.size()) {
            List<d.b.a.a.x.a> listSubList = this.n.subList(this.F.size(), this.n.size());
            for (d.b.a.a.x.a aVar : listSubList) {
                if (t.O(this)) {
                    o(aVar);
                }
            }
            listSubList.clear();
        }
        while (this.n.size() < this.F.size()) {
            d.b.a.a.x.a aVarA = this.m.a();
            this.n.add(aVarA);
            if (t.O(this)) {
                h(aVarA);
            }
        }
        int i = this.n.size() == 1 ? 0 : 1;
        Iterator<d.b.a.a.x.a> it = this.n.iterator();
        while (it.hasNext()) {
            it.next().j0(i);
        }
    }

    private void o(d.b.a.a.x.a aVar) {
        o oVarE = p.e(this);
        if (oVarE != null) {
            oVarE.b(aVar);
            aVar.t0(p.d(this));
        }
    }

    private void p(int i) {
        Iterator<L> it = this.o.iterator();
        while (it.hasNext()) {
            it.next().a(this, this.F.get(i).floatValue(), true);
        }
        AccessibilityManager accessibilityManager = this.k;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        P(i);
    }

    private void q() {
        for (L l : this.o) {
            Iterator<Float> it = this.F.iterator();
            while (it.hasNext()) {
                l.a(this, it.next().floatValue(), false);
            }
        }
    }

    private void r(Canvas canvas, int i, int i2) {
        float[] activeRange = getActiveRange();
        int i3 = this.u;
        float f = i;
        float f2 = i2;
        canvas.drawLine(i3 + (activeRange[0] * f), f2, i3 + (activeRange[1] * f), f2, this.e);
    }

    private void s(Canvas canvas, int i, int i2) {
        float[] activeRange = getActiveRange();
        float f = i;
        float f2 = this.u + (activeRange[1] * f);
        if (f2 < r1 + i) {
            float f3 = i2;
            canvas.drawLine(f2, f3, r1 + i, f3, this.f3666d);
        }
        int i3 = this.u;
        float f4 = i3 + (activeRange[0] * f);
        if (f4 > i3) {
            float f5 = i2;
            canvas.drawLine(i3, f5, f4, f5, this.f3666d);
        }
    }

    private void setValuesInternal(ArrayList<Float> arrayList) {
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("At least one value must be set");
        }
        Collections.sort(arrayList);
        if (this.F.size() == arrayList.size() && this.F.equals(arrayList)) {
            return;
        }
        this.F = arrayList;
        this.N = true;
        this.H = 0;
        X();
        n();
        q();
        postInvalidate();
    }

    private void t(Canvas canvas, int i, int i2) {
        if (!isEnabled()) {
            Iterator<Float> it = this.F.iterator();
            while (it.hasNext()) {
                canvas.drawCircle(this.u + (H(it.next().floatValue()) * i), i2, this.w, this.f);
            }
        }
        for (Float f : this.F) {
            canvas.save();
            int iH = this.u + ((int) (H(f.floatValue()) * i));
            int i3 = this.w;
            canvas.translate(iH - i3, i2 - i3);
            this.T.draw(canvas);
            canvas.restore();
        }
    }

    private void u(Canvas canvas) {
        float[] activeRange = getActiveRange();
        int iN = N(this.J, activeRange[0]);
        int iN2 = N(this.J, activeRange[1]);
        int i = iN * 2;
        canvas.drawPoints(this.J, 0, i, this.h);
        int i2 = iN2 * 2;
        canvas.drawPoints(this.J, i, i2 - i, this.i);
        float[] fArr = this.J;
        canvas.drawPoints(fArr, i2, fArr.length - i2, this.h);
    }

    private void v() {
        if (this.s == 2) {
            return;
        }
        Iterator<d.b.a.a.x.a> it = this.n.iterator();
        for (int i = 0; i < this.F.size() && it.hasNext(); i++) {
            if (i != this.H) {
                Q(it.next(), this.F.get(i).floatValue());
            }
        }
        if (!it.hasNext()) {
            throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(this.n.size()), Integer.valueOf(this.F.size())));
        }
        Q(it.next(), this.F.get(this.H).floatValue());
    }

    private void w(int i) {
        if (i == 1) {
            F(Integer.MAX_VALUE);
            return;
        }
        if (i == 2) {
            F(Integer.MIN_VALUE);
        } else if (i == 17) {
            G(Integer.MAX_VALUE);
        } else {
            if (i != 66) {
                return;
            }
            G(Integer.MIN_VALUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String x(float f) {
        if (A()) {
            return this.B.a(f);
        }
        return String.format(((float) ((int) f)) == f ? "%.0f" : "%.2f", Float.valueOf(f));
    }

    private float y(int i, float f) {
        int i2 = i + 1;
        int i3 = i - 1;
        return c.g.f.a.a(f, i3 < 0 ? this.D : this.F.get(i3).floatValue(), i2 >= this.F.size() ? this.E : this.F.get(i2).floatValue());
    }

    private int z(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    public boolean A() {
        return this.B != null;
    }

    final boolean C() {
        return t.z(this) == 1;
    }

    protected boolean M() {
        if (this.G != -1) {
            return true;
        }
        float valueOfTouchPositionAbsolute = getValueOfTouchPositionAbsolute();
        float fE0 = e0(valueOfTouchPositionAbsolute);
        this.G = 0;
        float fAbs = Math.abs(this.F.get(0).floatValue() - valueOfTouchPositionAbsolute);
        for (int i = 1; i < this.F.size(); i++) {
            float fAbs2 = Math.abs(this.F.get(i).floatValue() - valueOfTouchPositionAbsolute);
            float fE02 = e0(this.F.get(i).floatValue());
            if (Float.compare(fAbs2, fAbs) > 1) {
                break;
            }
            boolean z = !C() ? fE02 - fE0 >= 0.0f : fE02 - fE0 <= 0.0f;
            if (Float.compare(fAbs2, fAbs) < 0) {
                this.G = i;
            } else {
                if (Float.compare(fAbs2, fAbs) != 0) {
                    continue;
                } else {
                    if (Math.abs(fE02 - fE0) < this.q) {
                        this.G = -1;
                        return false;
                    }
                    if (z) {
                        this.G = i;
                    }
                }
            }
            fAbs = fAbs2;
        }
        return this.G != -1;
    }

    void W(int i, Rect rect) {
        int iH = this.u + ((int) (H(getValues().get(i).floatValue()) * this.K));
        int iM = m();
        int i2 = this.w;
        rect.set(iH - i2, iM - i2, iH + i2, iM + i2);
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.j.v(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.f3666d.setColor(z(this.S));
        this.e.setColor(z(this.R));
        this.h.setColor(z(this.Q));
        this.i.setColor(z(this.P));
        for (d.b.a.a.x.a aVar : this.n) {
            if (aVar.isStateful()) {
                aVar.setState(getDrawableState());
            }
        }
        if (this.T.isStateful()) {
            this.T.setState(getDrawableState());
        }
        this.g.setColor(z(this.O));
        this.g.setAlpha(63);
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    final int getAccessibilityFocusedVirtualViewId() {
        return this.j.x();
    }

    public int getActiveThumbIndex() {
        return this.G;
    }

    public int getFocusedThumbIndex() {
        return this.H;
    }

    public int getHaloRadius() {
        return this.x;
    }

    public ColorStateList getHaloTintList() {
        return this.O;
    }

    public int getLabelBehavior() {
        return this.s;
    }

    public float getStepSize() {
        return this.I;
    }

    public float getThumbElevation() {
        return this.T.w();
    }

    public int getThumbRadius() {
        return this.w;
    }

    public ColorStateList getThumbTintList() {
        return this.T.x();
    }

    public ColorStateList getTickActiveTintList() {
        return this.P;
    }

    public ColorStateList getTickInactiveTintList() {
        return this.Q;
    }

    public ColorStateList getTickTintList() {
        if (this.Q.equals(this.P)) {
            return this.P;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    public ColorStateList getTrackActiveTintList() {
        return this.R;
    }

    public int getTrackHeight() {
        return this.t;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.S;
    }

    public int getTrackSidePadding() {
        return this.u;
    }

    public ColorStateList getTrackTintList() {
        if (this.S.equals(this.R)) {
            return this.R;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    public int getTrackWidth() {
        return this.K;
    }

    public float getValueFrom() {
        return this.D;
    }

    public float getValueTo() {
        return this.E;
    }

    List<Float> getValues() {
        return new ArrayList(this.F);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Iterator<d.b.a.a.x.a> it = this.n.iterator();
        while (it.hasNext()) {
            h(it.next());
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        BaseSlider<S, L, T>.b bVar = this.l;
        if (bVar != null) {
            removeCallbacks(bVar);
        }
        Iterator<d.b.a.a.x.a> it = this.n.iterator();
        while (it.hasNext()) {
            o(it.next());
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.N) {
            Y();
            if (this.I > 0.0f) {
                l();
            }
        }
        super.onDraw(canvas);
        int iM = m();
        s(canvas, this.K, iM);
        if (((Float) Collections.max(getValues())).floatValue() > this.D) {
            r(canvas, this.K, iM);
        }
        if (this.I > 0.0f) {
            u(canvas);
        }
        if ((this.C || isFocused()) && isEnabled()) {
            E(canvas, this.K, iM);
            if (this.G != -1) {
                v();
            }
        }
        t(canvas, this.K, iM);
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z) {
            w(i);
            this.j.V(this.H);
            return;
        }
        this.G = -1;
        Iterator<d.b.a.a.x.a> it = this.n.iterator();
        while (it.hasNext()) {
            p.e(this).b(it.next());
        }
        this.j.o(this.H);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.F.size() == 1) {
            this.G = 0;
        }
        if (this.G == -1) {
            Boolean boolI = I(i, keyEvent);
            return boolI != null ? boolI.booleanValue() : super.onKeyDown(i, keyEvent);
        }
        this.M |= keyEvent.isLongPress();
        Float fI = i(i);
        if (fI != null) {
            if (S(this.F.get(this.G).floatValue() + fI.floatValue())) {
                X();
                postInvalidate();
            }
            return true;
        }
        if (i != 23) {
            if (i == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return F(1);
                }
                if (keyEvent.isShiftPressed()) {
                    return F(-1);
                }
                return false;
            }
            if (i != 66) {
                return super.onKeyDown(i, keyEvent);
            }
        }
        this.G = -1;
        Iterator<d.b.a.a.x.a> it = this.n.iterator();
        while (it.hasNext()) {
            p.e(this).b(it.next());
        }
        postInvalidate();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        this.M = false;
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.r + (this.s == 1 ? this.n.get(0).getIntrinsicHeight() : 0), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.D = sliderState.f3667b;
        this.E = sliderState.f3668c;
        setValuesInternal(sliderState.f3669d);
        this.I = sliderState.e;
        if (sliderState.f) {
            requestFocus();
        }
        q();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.f3667b = this.D;
        sliderState.f3668c = this.E;
        sliderState.f3669d = new ArrayList<>(this.F);
        sliderState.e = this.I;
        sliderState.f = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.K = Math.max(i - (this.u * 2), 0);
        if (this.I > 0.0f) {
            l();
        }
        X();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float x = motionEvent.getX();
        float f = (x - this.u) / this.K;
        this.U = f;
        float fMax = Math.max(0.0f, f);
        this.U = fMax;
        this.U = Math.min(1.0f, fMax);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.z = x;
            if (!isInScrollingContainer()) {
                getParent().requestDisallowInterceptTouchEvent(true);
                if (M()) {
                    requestFocus();
                    this.C = true;
                    V();
                    X();
                    invalidate();
                    J();
                }
            }
        } else if (actionMasked == 1) {
            this.C = false;
            MotionEvent motionEvent2 = this.A;
            if (motionEvent2 != null && motionEvent2.getActionMasked() == 0 && Math.abs(this.A.getX() - motionEvent.getX()) <= this.q && Math.abs(this.A.getY() - motionEvent.getY()) <= this.q) {
                M();
            }
            if (this.G != -1) {
                V();
                this.G = -1;
            }
            Iterator<d.b.a.a.x.a> it = this.n.iterator();
            while (it.hasNext()) {
                p.e(this).b(it.next());
            }
            K();
            invalidate();
        } else if (actionMasked == 2) {
            if (!this.C) {
                if (Math.abs(x - this.z) < this.q) {
                    return false;
                }
                getParent().requestDisallowInterceptTouchEvent(true);
                J();
            }
            if (M()) {
                this.C = true;
                V();
                X();
                invalidate();
            }
        }
        setPressed(this.C);
        this.A = MotionEvent.obtain(motionEvent);
        return true;
    }

    protected void setActiveThumbIndex(int i) {
        this.G = i;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setLayerType(z ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i) {
        if (i < 0 || i >= this.F.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.H = i;
        this.j.V(i);
        postInvalidate();
    }

    public void setHaloRadius(int i) {
        if (i == this.x) {
            return;
        }
        this.x = i;
        Drawable background = getBackground();
        if (R() || !(background instanceof RippleDrawable)) {
            postInvalidate();
        } else {
            d.b.a.a.p.a.a((RippleDrawable) background, this.x);
        }
    }

    public void setHaloRadiusResource(int i) {
        setHaloRadius(getResources().getDimensionPixelSize(i));
    }

    public void setHaloTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.O)) {
            return;
        }
        this.O = colorStateList;
        Drawable background = getBackground();
        if (!R() && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        this.g.setColor(z(colorStateList));
        this.g.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i) {
        if (this.s != i) {
            this.s = i;
            requestLayout();
        }
    }

    public void setLabelFormatter(com.google.android.material.slider.c cVar) {
        this.B = cVar;
    }

    public void setStepSize(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.toString(f), Float.toString(this.D), Float.toString(this.E)));
        }
        if (this.I != f) {
            this.I = f;
            this.N = true;
            postInvalidate();
        }
    }

    public void setThumbElevation(float f) {
        this.T.W(f);
    }

    public void setThumbElevationResource(int i) {
        setThumbElevation(getResources().getDimension(i));
    }

    public void setThumbRadius(int i) {
        if (i == this.w) {
            return;
        }
        this.w = i;
        this.T.setShapeAppearanceModel(m.a().p(0, this.w).m());
        h hVar = this.T;
        int i2 = this.w;
        hVar.setBounds(0, 0, i2 * 2, i2 * 2);
        postInvalidate();
    }

    public void setThumbRadiusResource(int i) {
        setThumbRadius(getResources().getDimensionPixelSize(i));
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.T.X(colorStateList);
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.P)) {
            return;
        }
        this.P = colorStateList;
        this.i.setColor(z(colorStateList));
        invalidate();
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.Q)) {
            return;
        }
        this.Q = colorStateList;
        this.h.setColor(z(colorStateList));
        invalidate();
    }

    public void setTickTintList(ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public void setTrackActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.R)) {
            return;
        }
        this.R = colorStateList;
        this.e.setColor(z(colorStateList));
        invalidate();
    }

    public void setTrackHeight(int i) {
        if (this.t != i) {
            this.t = i;
            B();
            postInvalidate();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.S)) {
            return;
        }
        this.S = colorStateList;
        this.f3666d.setColor(z(colorStateList));
        invalidate();
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f) {
        this.D = f;
        this.N = true;
        postInvalidate();
    }

    public void setValueTo(float f) {
        this.E = f;
        this.N = true;
        postInvalidate();
    }

    void setValues(Float... fArr) {
        ArrayList<Float> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    private class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3673b;

        private b() {
            this.f3673b = -1;
        }

        void a(int i) {
            this.f3673b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.this.j.W(this.f3673b, 4);
        }

        /* synthetic */ b(BaseSlider baseSlider, a aVar) {
            this();
        }
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, d.b.a.a.b.D);
    }

    public BaseSlider(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, f3665c), attributeSet, i);
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
        this.C = false;
        this.F = new ArrayList<>();
        this.G = -1;
        this.H = -1;
        this.I = 0.0f;
        this.M = false;
        h hVar = new h();
        this.T = hVar;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.f3666d = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.e = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint(1);
        this.f = paint3;
        paint3.setStyle(Paint.Style.FILL);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.g = paint4;
        paint4.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.h = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        Paint paint6 = new Paint();
        this.i = paint6;
        paint6.setStyle(Paint.Style.STROKE);
        paint6.setStrokeCap(Paint.Cap.ROUND);
        D(context2.getResources());
        this.m = new a(attributeSet, i);
        O(context2, attributeSet, i);
        setFocusable(true);
        setClickable(true);
        hVar.f0(2);
        this.q = ViewConfiguration.get(context2).getScaledTouchSlop();
        c cVar = new c(this);
        this.j = cVar;
        t.j0(this, cVar);
        this.k = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    void setValues(List<Float> list) {
        setValuesInternal(new ArrayList<>(list));
    }
}
