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
import b.g.k.c0.c;
import b.g.k.t;
import com.google.android.material.internal.l;
import com.google.android.material.internal.p;
import com.google.android.material.internal.q;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.slider.a;
import com.google.android.material.slider.b;
import d.b.a.a.j;
import d.b.a.a.k;
import d.b.a.a.x.h;
import d.b.a.a.x.m;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends com.google.android.material.slider.a<S>, T extends com.google.android.material.slider.b<S>> extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f4200b = BaseSlider.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f4201c = k.Widget_MaterialComponents_Slider;
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
    private final Paint f4202d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Paint f4203e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Paint f4204f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Paint f4205g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Paint f4206h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Paint f4207i;
    private final c j;
    private final AccessibilityManager k;
    private BaseSlider<S, L, T>.b l;
    private final d m;
    private final List<d.b.a.a.y.a> n;
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
        float f4208b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f4209c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        ArrayList<Float> f4210d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float f4211e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f4212f;

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
            public SliderState[] newArray(int i2) {
                return new SliderState[i2];
            }
        }

        /* synthetic */ SliderState(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeFloat(this.f4208b);
            parcel.writeFloat(this.f4209c);
            parcel.writeList(this.f4210d);
            parcel.writeFloat(this.f4211e);
            parcel.writeBooleanArray(new boolean[]{this.f4212f});
        }

        SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        private SliderState(Parcel parcel) {
            super(parcel);
            this.f4208b = parcel.readFloat();
            this.f4209c = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.f4210d = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.f4211e = parcel.readFloat();
            this.f4212f = parcel.createBooleanArray()[0];
        }
    }

    class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ AttributeSet f4213a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4214b;

        a(AttributeSet attributeSet, int i2) {
            this.f4213a = attributeSet;
            this.f4214b = i2;
        }

        @Override // com.google.android.material.slider.BaseSlider.d
        public d.b.a.a.y.a a() {
            TypedArray typedArrayH = l.h(BaseSlider.this.getContext(), this.f4213a, d.b.a.a.l.Slider, this.f4214b, BaseSlider.f4201c, new int[0]);
            d.b.a.a.y.a aVarL = BaseSlider.L(BaseSlider.this.getContext(), typedArrayH);
            typedArrayH.recycle();
            return aVarL;
        }
    }

    private static class c extends b.i.a.a {
        private final BaseSlider<?, ?, ?> q;
        Rect r;

        c(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.r = new Rect();
            this.q = baseSlider;
        }

        private String Y(int i2) {
            return i2 == this.q.getValues().size() + (-1) ? this.q.getContext().getString(j.material_slider_range_end) : i2 == 0 ? this.q.getContext().getString(j.material_slider_range_start) : "";
        }

        @Override // b.i.a.a
        protected int B(float f2, float f3) {
            for (int i2 = 0; i2 < this.q.getValues().size(); i2++) {
                this.q.W(i2, this.r);
                if (this.r.contains((int) f2, (int) f3)) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // b.i.a.a
        protected void C(List<Integer> list) {
            for (int i2 = 0; i2 < this.q.getValues().size(); i2++) {
                list.add(Integer.valueOf(i2));
            }
        }

        @Override // b.i.a.a
        protected boolean L(int i2, int i3, Bundle bundle) {
            if (!this.q.isEnabled()) {
                return false;
            }
            if (i3 != 4096 && i3 != 8192) {
                if (i3 == 16908349 && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                    if (this.q.U(i2, bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"))) {
                        this.q.X();
                        this.q.postInvalidate();
                        E(i2);
                        return true;
                    }
                }
                return false;
            }
            float fK = this.q.k(20);
            if (i3 == 8192) {
                fK = -fK;
            }
            if (this.q.C()) {
                fK = -fK;
            }
            if (!this.q.U(i2, b.g.f.a.a(this.q.getValues().get(i2).floatValue() + fK, this.q.getValueFrom(), this.q.getValueTo()))) {
                return false;
            }
            this.q.X();
            this.q.postInvalidate();
            E(i2);
            return true;
        }

        @Override // b.i.a.a
        protected void P(int i2, b.g.k.c0.c cVar) {
            cVar.b(c.a.H);
            List<Float> values = this.q.getValues();
            float fFloatValue = values.get(i2).floatValue();
            float valueFrom = this.q.getValueFrom();
            float valueTo = this.q.getValueTo();
            if (this.q.isEnabled()) {
                if (fFloatValue > valueFrom) {
                    cVar.a(8192);
                }
                if (fFloatValue < valueTo) {
                    cVar.a(4096);
                }
            }
            cVar.r0(c.d.a(1, valueFrom, valueTo, fFloatValue));
            cVar.b0(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.q.getContentDescription() != null) {
                sb.append(this.q.getContentDescription());
                sb.append(",");
            }
            if (values.size() > 1) {
                sb.append(Y(i2));
                sb.append(this.q.x(fFloatValue));
            }
            cVar.f0(sb.toString());
            this.q.W(i2, this.r);
            cVar.W(this.r);
        }
    }

    private interface d {
        d.b.a.a.y.a a();
    }

    public BaseSlider(Context context) {
        this(context, null);
    }

    private void B() {
        this.f4202d.setStrokeWidth(this.t);
        this.f4203e.setStrokeWidth(this.t);
        this.f4206h.setStrokeWidth(this.t / 2.0f);
        this.f4207i.setStrokeWidth(this.t / 2.0f);
    }

    private void D(Resources resources) {
        this.r = resources.getDimensionPixelSize(d.b.a.a.d.mtrl_slider_widget_height);
        this.u = resources.getDimensionPixelOffset(d.b.a.a.d.mtrl_slider_track_side_padding);
        this.v = resources.getDimensionPixelOffset(d.b.a.a.d.mtrl_slider_track_top);
        this.y = resources.getDimensionPixelSize(d.b.a.a.d.mtrl_slider_label_padding);
    }

    private void E(Canvas canvas, int i2, int i3) {
        if (R()) {
            int iH = (int) (this.u + (H(this.F.get(this.H).floatValue()) * i2));
            if (Build.VERSION.SDK_INT < 28) {
                int i4 = this.x;
                canvas.clipRect(iH - i4, i3 - i4, iH + i4, i4 + i3, Region.Op.UNION);
            }
            canvas.drawCircle(iH, i3, this.x, this.f4205g);
        }
    }

    private boolean F(int i2) {
        int i3 = this.H;
        int iC = (int) b.g.f.a.c(((long) i3) + ((long) i2), 0L, this.F.size() - 1);
        this.H = iC;
        if (iC == i3) {
            return false;
        }
        if (this.G != -1) {
            this.G = iC;
        }
        X();
        postInvalidate();
        return true;
    }

    private boolean G(int i2) {
        if (C()) {
            i2 = i2 == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i2;
        }
        return F(i2);
    }

    private float H(float f2) {
        float f3 = this.D;
        float f4 = (f2 - f3) / (this.E - f3);
        return C() ? 1.0f - f4 : f4;
    }

    private Boolean I(int i2, KeyEvent keyEvent) {
        if (i2 == 61) {
            return keyEvent.hasNoModifiers() ? Boolean.valueOf(F(1)) : keyEvent.isShiftPressed() ? Boolean.valueOf(F(-1)) : Boolean.FALSE;
        }
        if (i2 != 66) {
            if (i2 != 81) {
                if (i2 == 69) {
                    F(-1);
                    return Boolean.TRUE;
                }
                if (i2 != 70) {
                    switch (i2) {
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
    public static d.b.a.a.y.a L(Context context, TypedArray typedArray) {
        return d.b.a.a.y.a.r0(context, null, 0, typedArray.getResourceId(d.b.a.a.l.Slider_labelStyle, k.Widget_MaterialComponents_Tooltip));
    }

    private static int N(float[] fArr, float f2) {
        return Math.round(f2 * ((fArr.length / 2) - 1));
    }

    private void O(Context context, AttributeSet attributeSet, int i2) {
        TypedArray typedArrayH = l.h(context, attributeSet, d.b.a.a.l.Slider, i2, f4201c, new int[0]);
        this.D = typedArrayH.getFloat(d.b.a.a.l.Slider_android_valueFrom, 0.0f);
        this.E = typedArrayH.getFloat(d.b.a.a.l.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.D));
        this.I = typedArrayH.getFloat(d.b.a.a.l.Slider_android_stepSize, 0.0f);
        int i3 = d.b.a.a.l.Slider_trackColor;
        boolean zHasValue = typedArrayH.hasValue(i3);
        int i4 = zHasValue ? i3 : d.b.a.a.l.Slider_trackColorInactive;
        if (!zHasValue) {
            i3 = d.b.a.a.l.Slider_trackColorActive;
        }
        ColorStateList colorStateListA = d.b.a.a.u.c.a(context, typedArrayH, i4);
        if (colorStateListA == null) {
            colorStateListA = b.a.k.a.a.c(context, d.b.a.a.c.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(colorStateListA);
        ColorStateList colorStateListA2 = d.b.a.a.u.c.a(context, typedArrayH, i3);
        if (colorStateListA2 == null) {
            colorStateListA2 = b.a.k.a.a.c(context, d.b.a.a.c.material_slider_active_track_color);
        }
        setTrackActiveTintList(colorStateListA2);
        this.T.X(d.b.a.a.u.c.a(context, typedArrayH, d.b.a.a.l.Slider_thumbColor));
        ColorStateList colorStateListA3 = d.b.a.a.u.c.a(context, typedArrayH, d.b.a.a.l.Slider_haloColor);
        if (colorStateListA3 == null) {
            colorStateListA3 = b.a.k.a.a.c(context, d.b.a.a.c.material_slider_halo_color);
        }
        setHaloTintList(colorStateListA3);
        int i5 = d.b.a.a.l.Slider_tickColor;
        boolean zHasValue2 = typedArrayH.hasValue(i5);
        int i6 = zHasValue2 ? i5 : d.b.a.a.l.Slider_tickColorInactive;
        if (!zHasValue2) {
            i5 = d.b.a.a.l.Slider_tickColorActive;
        }
        ColorStateList colorStateListA4 = d.b.a.a.u.c.a(context, typedArrayH, i6);
        if (colorStateListA4 == null) {
            colorStateListA4 = b.a.k.a.a.c(context, d.b.a.a.c.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(colorStateListA4);
        ColorStateList colorStateListA5 = d.b.a.a.u.c.a(context, typedArrayH, i5);
        if (colorStateListA5 == null) {
            colorStateListA5 = b.a.k.a.a.c(context, d.b.a.a.c.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(colorStateListA5);
        setThumbRadius(typedArrayH.getDimensionPixelSize(d.b.a.a.l.Slider_thumbRadius, 0));
        setHaloRadius(typedArrayH.getDimensionPixelSize(d.b.a.a.l.Slider_haloRadius, 0));
        setThumbElevation(typedArrayH.getDimension(d.b.a.a.l.Slider_thumbElevation, 0.0f));
        setTrackHeight(typedArrayH.getDimensionPixelSize(d.b.a.a.l.Slider_trackHeight, 0));
        this.s = typedArrayH.getInt(d.b.a.a.l.Slider_labelBehavior, 0);
        if (!typedArrayH.getBoolean(d.b.a.a.l.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        typedArrayH.recycle();
    }

    private void P(int i2) {
        BaseSlider<S, L, T>.b bVar = this.l;
        if (bVar == null) {
            this.l = new b(this, null);
        } else {
            removeCallbacks(bVar);
        }
        this.l.a(i2);
        postDelayed(this.l, 200L);
    }

    private void Q(d.b.a.a.y.a aVar, float f2) {
        aVar.y0(x(f2));
        int iH = (this.u + ((int) (H(f2) * this.K))) - (aVar.getIntrinsicWidth() / 2);
        int iM = m() - (this.y + this.w);
        aVar.setBounds(iH, iM - aVar.getIntrinsicHeight(), aVar.getIntrinsicWidth() + iH, iM);
        Rect rect = new Rect(aVar.getBounds());
        com.google.android.material.internal.c.c(q.d(this), this, rect);
        aVar.setBounds(rect);
        q.e(this).a(aVar);
    }

    private boolean R() {
        return this.L || Build.VERSION.SDK_INT < 21 || !(getBackground() instanceof RippleDrawable);
    }

    private boolean S(float f2) {
        return U(this.G, f2);
    }

    private double T(float f2) {
        float f3 = this.I;
        if (f3 <= 0.0f) {
            return f2;
        }
        int i2 = (int) ((this.E - this.D) / f3);
        return ((double) Math.round(f2 * i2)) / ((double) i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean U(int i2, float f2) {
        if (Math.abs(f2 - this.F.get(i2).floatValue()) < 1.0E-4d) {
            return false;
        }
        this.F.set(i2, Float.valueOf(y(i2, f2)));
        this.H = i2;
        p(i2);
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
            int i2 = this.x;
            androidx.core.graphics.drawable.a.l(background, iH - i2, iM - i2, iH + i2, iM + i2);
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
        for (Float f2 : this.F) {
            if (f2.floatValue() < this.D || f2.floatValue() > this.E) {
                throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", Float.toString(f2.floatValue()), Float.toString(this.D), Float.toString(this.E)));
            }
            if (this.I > 0.0f && !d0(f2.floatValue())) {
                throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", Float.toString(f2.floatValue()), Float.toString(this.D), Float.toString(this.I), Float.toString(this.I)));
            }
        }
    }

    private boolean d0(float f2) {
        double dDoubleValue = new BigDecimal(Float.toString(f2)).subtract(new BigDecimal(Float.toString(this.D))).divide(new BigDecimal(Float.toString(this.I)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(dDoubleValue)) - dDoubleValue) < 1.0E-4d;
    }

    private float e0(float f2) {
        return (H(f2) * this.K) + this.u;
    }

    private void f0() {
        float f2 = this.I;
        if (f2 == 0.0f) {
            return;
        }
        if (((int) f2) != f2) {
            Log.w(f4200b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "stepSize", Float.valueOf(f2)));
        }
        float f3 = this.D;
        if (((int) f3) != f3) {
            Log.w(f4200b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueFrom", Float.valueOf(f3)));
        }
        float f4 = this.E;
        if (((int) f4) != f4) {
            Log.w(f4200b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueTo", Float.valueOf(f4)));
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
        float f2 = this.E;
        float f3 = this.D;
        return (float) ((dT * ((double) (f2 - f3))) + ((double) f3));
    }

    private float getValueOfTouchPositionAbsolute() {
        float f2 = this.U;
        if (C()) {
            f2 = 1.0f - f2;
        }
        float f3 = this.E;
        float f4 = this.D;
        return (f2 * (f3 - f4)) + f4;
    }

    private void h(d.b.a.a.y.a aVar) {
        aVar.x0(q.d(this));
    }

    private Float i(int i2) {
        float fK = this.M ? k(20) : j();
        if (i2 == 21) {
            if (!C()) {
                fK = -fK;
            }
            return Float.valueOf(fK);
        }
        if (i2 == 22) {
            if (C()) {
                fK = -fK;
            }
            return Float.valueOf(fK);
        }
        if (i2 == 69) {
            return Float.valueOf(-fK);
        }
        if (i2 == 70 || i2 == 81) {
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
        float f2 = this.I;
        if (f2 == 0.0f) {
            return 1.0f;
        }
        return f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float k(int i2) {
        float fJ = j();
        return (this.E - this.D) / fJ <= i2 ? fJ : Math.round(r1 / r4) * fJ;
    }

    private void l() {
        Y();
        int iMin = Math.min((int) (((this.E - this.D) / this.I) + 1.0f), (this.K / (this.t * 2)) + 1);
        float[] fArr = this.J;
        if (fArr == null || fArr.length != iMin * 2) {
            this.J = new float[iMin * 2];
        }
        float f2 = this.K / (iMin - 1);
        for (int i2 = 0; i2 < iMin * 2; i2 += 2) {
            float[] fArr2 = this.J;
            fArr2[i2] = this.u + ((i2 / 2) * f2);
            fArr2[i2 + 1] = m();
        }
    }

    private int m() {
        return this.v + (this.s == 1 ? this.n.get(0).getIntrinsicHeight() : 0);
    }

    private void n() {
        if (this.n.size() > this.F.size()) {
            List<d.b.a.a.y.a> listSubList = this.n.subList(this.F.size(), this.n.size());
            for (d.b.a.a.y.a aVar : listSubList) {
                if (t.O(this)) {
                    o(aVar);
                }
            }
            listSubList.clear();
        }
        while (this.n.size() < this.F.size()) {
            d.b.a.a.y.a aVarA = this.m.a();
            this.n.add(aVarA);
            if (t.O(this)) {
                h(aVarA);
            }
        }
        int i2 = this.n.size() == 1 ? 0 : 1;
        Iterator<d.b.a.a.y.a> it = this.n.iterator();
        while (it.hasNext()) {
            it.next().j0(i2);
        }
    }

    private void o(d.b.a.a.y.a aVar) {
        p pVarE = q.e(this);
        if (pVarE != null) {
            pVarE.b(aVar);
            aVar.t0(q.d(this));
        }
    }

    private void p(int i2) {
        Iterator<L> it = this.o.iterator();
        while (it.hasNext()) {
            it.next().a(this, this.F.get(i2).floatValue(), true);
        }
        AccessibilityManager accessibilityManager = this.k;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        P(i2);
    }

    private void q() {
        for (L l : this.o) {
            Iterator<Float> it = this.F.iterator();
            while (it.hasNext()) {
                l.a(this, it.next().floatValue(), false);
            }
        }
    }

    private void r(Canvas canvas, int i2, int i3) {
        float[] activeRange = getActiveRange();
        int i4 = this.u;
        float f2 = i2;
        float f3 = i3;
        canvas.drawLine(i4 + (activeRange[0] * f2), f3, i4 + (activeRange[1] * f2), f3, this.f4203e);
    }

    private void s(Canvas canvas, int i2, int i3) {
        float[] activeRange = getActiveRange();
        float f2 = i2;
        float f3 = this.u + (activeRange[1] * f2);
        if (f3 < r1 + i2) {
            float f4 = i3;
            canvas.drawLine(f3, f4, r1 + i2, f4, this.f4202d);
        }
        int i4 = this.u;
        float f5 = i4 + (activeRange[0] * f2);
        if (f5 > i4) {
            float f6 = i3;
            canvas.drawLine(i4, f6, f5, f6, this.f4202d);
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

    private void t(Canvas canvas, int i2, int i3) {
        if (!isEnabled()) {
            Iterator<Float> it = this.F.iterator();
            while (it.hasNext()) {
                canvas.drawCircle(this.u + (H(it.next().floatValue()) * i2), i3, this.w, this.f4204f);
            }
        }
        for (Float f2 : this.F) {
            canvas.save();
            int iH = this.u + ((int) (H(f2.floatValue()) * i2));
            int i4 = this.w;
            canvas.translate(iH - i4, i3 - i4);
            this.T.draw(canvas);
            canvas.restore();
        }
    }

    private void u(Canvas canvas) {
        float[] activeRange = getActiveRange();
        int iN = N(this.J, activeRange[0]);
        int iN2 = N(this.J, activeRange[1]);
        int i2 = iN * 2;
        canvas.drawPoints(this.J, 0, i2, this.f4206h);
        int i3 = iN2 * 2;
        canvas.drawPoints(this.J, i2, i3 - i2, this.f4207i);
        float[] fArr = this.J;
        canvas.drawPoints(fArr, i3, fArr.length - i3, this.f4206h);
    }

    private void v() {
        if (this.s == 2) {
            return;
        }
        Iterator<d.b.a.a.y.a> it = this.n.iterator();
        for (int i2 = 0; i2 < this.F.size() && it.hasNext(); i2++) {
            if (i2 != this.H) {
                Q(it.next(), this.F.get(i2).floatValue());
            }
        }
        if (!it.hasNext()) {
            throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(this.n.size()), Integer.valueOf(this.F.size())));
        }
        Q(it.next(), this.F.get(this.H).floatValue());
    }

    private void w(int i2) {
        if (i2 == 1) {
            F(Integer.MAX_VALUE);
            return;
        }
        if (i2 == 2) {
            F(Integer.MIN_VALUE);
        } else if (i2 == 17) {
            G(Integer.MAX_VALUE);
        } else {
            if (i2 != 66) {
                return;
            }
            G(Integer.MIN_VALUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String x(float f2) {
        if (A()) {
            return this.B.a(f2);
        }
        return String.format(((float) ((int) f2)) == f2 ? "%.0f" : "%.2f", Float.valueOf(f2));
    }

    private float y(int i2, float f2) {
        int i3 = i2 + 1;
        int i4 = i2 - 1;
        return b.g.f.a.a(f2, i4 < 0 ? this.D : this.F.get(i4).floatValue(), i3 >= this.F.size() ? this.E : this.F.get(i3).floatValue());
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
        for (int i2 = 1; i2 < this.F.size(); i2++) {
            float fAbs2 = Math.abs(this.F.get(i2).floatValue() - valueOfTouchPositionAbsolute);
            float fE02 = e0(this.F.get(i2).floatValue());
            if (Float.compare(fAbs2, fAbs) > 1) {
                break;
            }
            boolean z = !C() ? fE02 - fE0 >= 0.0f : fE02 - fE0 <= 0.0f;
            if (Float.compare(fAbs2, fAbs) < 0) {
                this.G = i2;
            } else {
                if (Float.compare(fAbs2, fAbs) != 0) {
                    continue;
                } else {
                    if (Math.abs(fE02 - fE0) < this.q) {
                        this.G = -1;
                        return false;
                    }
                    if (z) {
                        this.G = i2;
                    }
                }
            }
            fAbs = fAbs2;
        }
        return this.G != -1;
    }

    void W(int i2, Rect rect) {
        int iH = this.u + ((int) (H(getValues().get(i2).floatValue()) * this.K));
        int iM = m();
        int i3 = this.w;
        rect.set(iH - i3, iM - i3, iH + i3, iM + i3);
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
        this.f4202d.setColor(z(this.S));
        this.f4203e.setColor(z(this.R));
        this.f4206h.setColor(z(this.Q));
        this.f4207i.setColor(z(this.P));
        for (d.b.a.a.y.a aVar : this.n) {
            if (aVar.isStateful()) {
                aVar.setState(getDrawableState());
            }
        }
        if (this.T.isStateful()) {
            this.T.setState(getDrawableState());
        }
        this.f4205g.setColor(z(this.O));
        this.f4205g.setAlpha(63);
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
        Iterator<d.b.a.a.y.a> it = this.n.iterator();
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
        Iterator<d.b.a.a.y.a> it = this.n.iterator();
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
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (z) {
            w(i2);
            this.j.V(this.H);
            return;
        }
        this.G = -1;
        Iterator<d.b.a.a.y.a> it = this.n.iterator();
        while (it.hasNext()) {
            q.e(this).b(it.next());
        }
        this.j.o(this.H);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i2, keyEvent);
        }
        if (this.F.size() == 1) {
            this.G = 0;
        }
        if (this.G == -1) {
            Boolean boolI = I(i2, keyEvent);
            return boolI != null ? boolI.booleanValue() : super.onKeyDown(i2, keyEvent);
        }
        this.M |= keyEvent.isLongPress();
        Float fI = i(i2);
        if (fI != null) {
            if (S(this.F.get(this.G).floatValue() + fI.floatValue())) {
                X();
                postInvalidate();
            }
            return true;
        }
        if (i2 != 23) {
            if (i2 == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return F(1);
                }
                if (keyEvent.isShiftPressed()) {
                    return F(-1);
                }
                return false;
            }
            if (i2 != 66) {
                return super.onKeyDown(i2, keyEvent);
            }
        }
        this.G = -1;
        Iterator<d.b.a.a.y.a> it = this.n.iterator();
        while (it.hasNext()) {
            q.e(this).b(it.next());
        }
        postInvalidate();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        this.M = false;
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(this.r + (this.s == 1 ? this.n.get(0).getIntrinsicHeight() : 0), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.D = sliderState.f4208b;
        this.E = sliderState.f4209c;
        setValuesInternal(sliderState.f4210d);
        this.I = sliderState.f4211e;
        if (sliderState.f4212f) {
            requestFocus();
        }
        q();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.f4208b = this.D;
        sliderState.f4209c = this.E;
        sliderState.f4210d = new ArrayList<>(this.F);
        sliderState.f4211e = this.I;
        sliderState.f4212f = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        this.K = Math.max(i2 - (this.u * 2), 0);
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
        float f2 = (x - this.u) / this.K;
        this.U = f2;
        float fMax = Math.max(0.0f, f2);
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
            Iterator<d.b.a.a.y.a> it = this.n.iterator();
            while (it.hasNext()) {
                q.e(this).b(it.next());
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

    protected void setActiveThumbIndex(int i2) {
        this.G = i2;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setLayerType(z ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i2) {
        if (i2 < 0 || i2 >= this.F.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.H = i2;
        this.j.V(i2);
        postInvalidate();
    }

    public void setHaloRadius(int i2) {
        if (i2 == this.x) {
            return;
        }
        this.x = i2;
        Drawable background = getBackground();
        if (R() || !(background instanceof RippleDrawable)) {
            postInvalidate();
        } else {
            d.b.a.a.q.a.a((RippleDrawable) background, this.x);
        }
    }

    public void setHaloRadiusResource(int i2) {
        setHaloRadius(getResources().getDimensionPixelSize(i2));
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
        this.f4205g.setColor(z(colorStateList));
        this.f4205g.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i2) {
        if (this.s != i2) {
            this.s = i2;
            requestLayout();
        }
    }

    public void setLabelFormatter(com.google.android.material.slider.c cVar) {
        this.B = cVar;
    }

    public void setStepSize(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.toString(f2), Float.toString(this.D), Float.toString(this.E)));
        }
        if (this.I != f2) {
            this.I = f2;
            this.N = true;
            postInvalidate();
        }
    }

    public void setThumbElevation(float f2) {
        this.T.W(f2);
    }

    public void setThumbElevationResource(int i2) {
        setThumbElevation(getResources().getDimension(i2));
    }

    public void setThumbRadius(int i2) {
        if (i2 == this.w) {
            return;
        }
        this.w = i2;
        this.T.setShapeAppearanceModel(m.a().p(0, this.w).m());
        h hVar = this.T;
        int i3 = this.w;
        hVar.setBounds(0, 0, i3 * 2, i3 * 2);
        postInvalidate();
    }

    public void setThumbRadiusResource(int i2) {
        setThumbRadius(getResources().getDimensionPixelSize(i2));
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.T.X(colorStateList);
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.P)) {
            return;
        }
        this.P = colorStateList;
        this.f4207i.setColor(z(colorStateList));
        invalidate();
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.Q)) {
            return;
        }
        this.Q = colorStateList;
        this.f4206h.setColor(z(colorStateList));
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
        this.f4203e.setColor(z(colorStateList));
        invalidate();
    }

    public void setTrackHeight(int i2) {
        if (this.t != i2) {
            this.t = i2;
            B();
            postInvalidate();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.S)) {
            return;
        }
        this.S = colorStateList;
        this.f4202d.setColor(z(colorStateList));
        invalidate();
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f2) {
        this.D = f2;
        this.N = true;
        postInvalidate();
    }

    public void setValueTo(float f2) {
        this.E = f2;
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
        int f4216b;

        private b() {
            this.f4216b = -1;
        }

        void a(int i2) {
            this.f4216b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.this.j.W(this.f4216b, 4);
        }

        /* synthetic */ b(BaseSlider baseSlider, a aVar) {
            this();
        }
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, d.b.a.a.b.sliderStyle);
    }

    public BaseSlider(Context context, AttributeSet attributeSet, int i2) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, f4201c), attributeSet, i2);
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
        this.f4202d = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.f4203e = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint(1);
        this.f4204f = paint3;
        paint3.setStyle(Paint.Style.FILL);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.f4205g = paint4;
        paint4.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.f4206h = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        Paint paint6 = new Paint();
        this.f4207i = paint6;
        paint6.setStyle(Paint.Style.STROKE);
        paint6.setStrokeCap(Paint.Cap.ROUND);
        D(context2.getResources());
        this.m = new a(attributeSet, i2);
        O(context2, attributeSet, i2);
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
