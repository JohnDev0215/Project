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
import com.bydux.api.common.tools.util.ListUtils;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.slider.BaseSlider;
import defpackage.bj;
import defpackage.ea;
import defpackage.fl;
import defpackage.gl;
import defpackage.h8;
import defpackage.hk;
import defpackage.jj;
import defpackage.mk;
import defpackage.nj;
import defpackage.oj;
import defpackage.pi;
import defpackage.qk;
import defpackage.rk;
import defpackage.sj;
import defpackage.sk;
import defpackage.t9;
import defpackage.u0;
import defpackage.xa;
import defpackage.y7;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends qk<S>, T extends rk<S>> extends View {
    public static final String b = BaseSlider.class.getSimpleName();
    public static final int c = R$style.Widget_MaterialComponents_Slider;
    public MotionEvent A;
    public sk B;
    public boolean C;
    public float D;
    public float E;
    public ArrayList<Float> F;
    public int G;
    public int H;
    public float I;
    public float[] J;
    public int K;
    public boolean L;
    public boolean M;
    public boolean N;
    public ColorStateList O;
    public ColorStateList P;
    public ColorStateList Q;
    public ColorStateList R;
    public ColorStateList S;
    public final hk T;
    public float U;
    public final Paint d;
    public final Paint e;
    public final Paint f;
    public final Paint g;
    public final Paint h;
    public final Paint i;
    public final c j;
    public final AccessibilityManager k;
    public BaseSlider<S, L, T>.b l;
    public final d m;
    public final List<gl> n;
    public final List<L> o;
    public final List<T> p;
    public final int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public float z;

    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new a();
        public float b;
        public float c;
        public ArrayList<Float> d;
        public float e;
        public boolean f;

        public static class a implements Parcelable.Creator<SliderState> {
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

        public /* synthetic */ SliderState(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.b);
            parcel.writeFloat(this.c);
            parcel.writeList(this.d);
            parcel.writeFloat(this.e);
            parcel.writeBooleanArray(new boolean[]{this.f});
        }

        public SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        public SliderState(Parcel parcel) {
            super(parcel);
            this.b = parcel.readFloat();
            this.c = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.d = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.e = parcel.readFloat();
            this.f = parcel.createBooleanArray()[0];
        }
    }

    public class a implements d {
        public final /* synthetic */ AttributeSet a;
        public final /* synthetic */ int b;

        public a(AttributeSet attributeSet, int i) {
            this.a = attributeSet;
            this.b = i;
        }

        @Override // com.google.android.material.slider.BaseSlider.d
        public gl a() {
            TypedArray typedArrayH = jj.h(BaseSlider.this.getContext(), this.a, R$styleable.Slider, this.b, BaseSlider.c, new int[0]);
            gl glVarL = BaseSlider.L(BaseSlider.this.getContext(), typedArrayH);
            typedArrayH.recycle();
            return glVarL;
        }
    }

    public static class c extends xa {
        public final BaseSlider<?, ?, ?> q;
        public Rect r;

        public c(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.r = new Rect();
            this.q = baseSlider;
        }

        @Override // defpackage.xa
        public int B(float f, float f2) {
            for (int i = 0; i < this.q.getValues().size(); i++) {
                this.q.W(i, this.r);
                if (this.r.contains((int) f, (int) f2)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // defpackage.xa
        public void C(List<Integer> list) {
            for (int i = 0; i < this.q.getValues().size(); i++) {
                list.add(Integer.valueOf(i));
            }
        }

        @Override // defpackage.xa
        public boolean L(int i, int i2, Bundle bundle) {
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
            if (!this.q.U(i, h8.a(this.q.getValues().get(i).floatValue() + fK, this.q.getValueFrom(), this.q.getValueTo()))) {
                return false;
            }
            this.q.X();
            this.q.postInvalidate();
            E(i);
            return true;
        }

        @Override // defpackage.xa
        public void P(int i, ea eaVar) {
            eaVar.b(ea.a.H);
            List<Float> values = this.q.getValues();
            float fFloatValue = values.get(i).floatValue();
            float valueFrom = this.q.getValueFrom();
            float valueTo = this.q.getValueTo();
            if (this.q.isEnabled()) {
                if (fFloatValue > valueFrom) {
                    eaVar.a(8192);
                }
                if (fFloatValue < valueTo) {
                    eaVar.a(4096);
                }
            }
            eaVar.s0(ea.d.a(1, valueFrom, valueTo, fFloatValue));
            eaVar.b0(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.q.getContentDescription() != null) {
                sb.append(this.q.getContentDescription());
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            }
            if (values.size() > 1) {
                sb.append(Y(i));
                sb.append(this.q.x(fFloatValue));
            }
            eaVar.f0(sb.toString());
            this.q.W(i, this.r);
            eaVar.W(this.r);
        }

        public final String Y(int i) {
            return i == this.q.getValues().size() + (-1) ? this.q.getContext().getString(R$string.material_slider_range_end) : i == 0 ? this.q.getContext().getString(R$string.material_slider_range_start) : "";
        }
    }

    public interface d {
        gl a();
    }

    public BaseSlider(Context context) {
        this(context, null);
    }

    public static gl L(Context context, TypedArray typedArray) {
        return gl.r0(context, null, 0, typedArray.getResourceId(R$styleable.Slider_labelStyle, R$style.Widget_MaterialComponents_Tooltip));
    }

    public static int N(float[] fArr, float f) {
        return Math.round(f * ((fArr.length / 2) - 1));
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

    private boolean isInScrollingContainer() {
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
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

    public boolean A() {
        return this.B != null;
    }

    public final void B() {
        this.d.setStrokeWidth(this.t);
        this.e.setStrokeWidth(this.t);
        this.h.setStrokeWidth(this.t / 2.0f);
        this.i.setStrokeWidth(this.t / 2.0f);
    }

    public final boolean C() {
        return t9.z(this) == 1;
    }

    public final void D(Resources resources) {
        this.r = resources.getDimensionPixelSize(R$dimen.mtrl_slider_widget_height);
        this.u = resources.getDimensionPixelOffset(R$dimen.mtrl_slider_track_side_padding);
        this.v = resources.getDimensionPixelOffset(R$dimen.mtrl_slider_track_top);
        this.y = resources.getDimensionPixelSize(R$dimen.mtrl_slider_label_padding);
    }

    public final void E(Canvas canvas, int i, int i2) {
        if (R()) {
            int iH = (int) (this.u + (H(this.F.get(this.H).floatValue()) * i));
            if (Build.VERSION.SDK_INT < 28) {
                int i3 = this.x;
                canvas.clipRect(iH - i3, i2 - i3, iH + i3, i3 + i2, Region.Op.UNION);
            }
            canvas.drawCircle(iH, i2, this.x, this.g);
        }
    }

    public final boolean F(int i) {
        int i2 = this.H;
        int iC = (int) h8.c(((long) i2) + ((long) i), 0L, this.F.size() - 1);
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

    public final boolean G(int i) {
        if (C()) {
            i = i == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i;
        }
        return F(i);
    }

    public final float H(float f) {
        float f2 = this.D;
        float f3 = (f - f2) / (this.E - f2);
        return C() ? 1.0f - f3 : f3;
    }

    public final Boolean I(int i, KeyEvent keyEvent) {
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

    public final void J() {
        Iterator<T> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public final void K() {
        Iterator<T> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    public boolean M() {
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

    public final void O(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayH = jj.h(context, attributeSet, R$styleable.Slider, i, c, new int[0]);
        this.D = typedArrayH.getFloat(R$styleable.Slider_android_valueFrom, 0.0f);
        this.E = typedArrayH.getFloat(R$styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.D));
        this.I = typedArrayH.getFloat(R$styleable.Slider_android_stepSize, 0.0f);
        int i2 = R$styleable.Slider_trackColor;
        boolean zHasValue = typedArrayH.hasValue(i2);
        int i3 = zHasValue ? i2 : R$styleable.Slider_trackColorInactive;
        if (!zHasValue) {
            i2 = R$styleable.Slider_trackColorActive;
        }
        ColorStateList colorStateListA = sj.a(context, typedArrayH, i3);
        if (colorStateListA == null) {
            colorStateListA = u0.c(context, R$color.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(colorStateListA);
        ColorStateList colorStateListA2 = sj.a(context, typedArrayH, i2);
        if (colorStateListA2 == null) {
            colorStateListA2 = u0.c(context, R$color.material_slider_active_track_color);
        }
        setTrackActiveTintList(colorStateListA2);
        this.T.X(sj.a(context, typedArrayH, R$styleable.Slider_thumbColor));
        ColorStateList colorStateListA3 = sj.a(context, typedArrayH, R$styleable.Slider_haloColor);
        if (colorStateListA3 == null) {
            colorStateListA3 = u0.c(context, R$color.material_slider_halo_color);
        }
        setHaloTintList(colorStateListA3);
        int i4 = R$styleable.Slider_tickColor;
        boolean zHasValue2 = typedArrayH.hasValue(i4);
        int i5 = zHasValue2 ? i4 : R$styleable.Slider_tickColorInactive;
        if (!zHasValue2) {
            i4 = R$styleable.Slider_tickColorActive;
        }
        ColorStateList colorStateListA4 = sj.a(context, typedArrayH, i5);
        if (colorStateListA4 == null) {
            colorStateListA4 = u0.c(context, R$color.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(colorStateListA4);
        ColorStateList colorStateListA5 = sj.a(context, typedArrayH, i4);
        if (colorStateListA5 == null) {
            colorStateListA5 = u0.c(context, R$color.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(colorStateListA5);
        setThumbRadius(typedArrayH.getDimensionPixelSize(R$styleable.Slider_thumbRadius, 0));
        setHaloRadius(typedArrayH.getDimensionPixelSize(R$styleable.Slider_haloRadius, 0));
        setThumbElevation(typedArrayH.getDimension(R$styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(typedArrayH.getDimensionPixelSize(R$styleable.Slider_trackHeight, 0));
        this.s = typedArrayH.getInt(R$styleable.Slider_labelBehavior, 0);
        if (!typedArrayH.getBoolean(R$styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        typedArrayH.recycle();
    }

    public final void P(int i) {
        BaseSlider<S, L, T>.b bVar = this.l;
        if (bVar == null) {
            this.l = new b(this, null);
        } else {
            removeCallbacks(bVar);
        }
        this.l.a(i);
        postDelayed(this.l, 200L);
    }

    public final void Q(gl glVar, float f) {
        glVar.y0(x(f));
        int iH = (this.u + ((int) (H(f) * this.K))) - (glVar.getIntrinsicWidth() / 2);
        int iM = m() - (this.y + this.w);
        glVar.setBounds(iH, iM - glVar.getIntrinsicHeight(), glVar.getIntrinsicWidth() + iH, iM);
        Rect rect = new Rect(glVar.getBounds());
        bj.c(oj.d(this), this, rect);
        glVar.setBounds(rect);
        oj.e(this).a(glVar);
    }

    public final boolean R() {
        return this.L || Build.VERSION.SDK_INT < 21 || !(getBackground() instanceof RippleDrawable);
    }

    public final boolean S(float f) {
        return U(this.G, f);
    }

    public final double T(float f) {
        float f2 = this.I;
        if (f2 <= 0.0f) {
            return f;
        }
        int i = (int) ((this.E - this.D) / f2);
        return ((double) Math.round(f * i)) / ((double) i);
    }

    public final boolean U(int i, float f) {
        if (Math.abs(f - this.F.get(i).floatValue()) < 1.0E-4d) {
            return false;
        }
        this.F.set(i, Float.valueOf(y(i, f)));
        this.H = i;
        p(i);
        return true;
    }

    public final boolean V() {
        return S(getValueOfTouchPosition());
    }

    public void W(int i, Rect rect) {
        int iH = this.u + ((int) (H(getValues().get(i).floatValue()) * this.K));
        int iM = m();
        int i2 = this.w;
        rect.set(iH - i2, iM - i2, iH + i2, iM + i2);
    }

    public final void X() {
        if (R() || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int iH = (int) ((H(this.F.get(this.H).floatValue()) * this.K) + this.u);
            int iM = m();
            int i = this.x;
            y7.l(background, iH - i, iM - i, iH + i, iM + i);
        }
    }

    public final void Y() {
        if (this.N) {
            a0();
            b0();
            Z();
            c0();
            f0();
            this.N = false;
        }
    }

    public final void Z() {
        if (this.I > 0.0f && !d0(this.E)) {
            throw new IllegalStateException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.toString(this.I), Float.toString(this.D), Float.toString(this.E)));
        }
    }

    public final void a0() {
        if (this.D >= this.E) {
            throw new IllegalStateException(String.format("valueFrom(%s) must be smaller than valueTo(%s)", Float.toString(this.D), Float.toString(this.E)));
        }
    }

    public final void b0() {
        if (this.E <= this.D) {
            throw new IllegalStateException(String.format("valueTo(%s) must be greater than valueFrom(%s)", Float.toString(this.E), Float.toString(this.D)));
        }
    }

    public final void c0() {
        for (Float f : this.F) {
            if (f.floatValue() < this.D || f.floatValue() > this.E) {
                throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", Float.toString(f.floatValue()), Float.toString(this.D), Float.toString(this.E)));
            }
            if (this.I > 0.0f && !d0(f.floatValue())) {
                throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", Float.toString(f.floatValue()), Float.toString(this.D), Float.toString(this.I), Float.toString(this.I)));
            }
        }
    }

    public final boolean d0(float f) {
        double dDoubleValue = new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Float.toString(this.D))).divide(new BigDecimal(Float.toString(this.I)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(dDoubleValue)) - dDoubleValue) < 1.0E-4d;
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
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.d.setColor(z(this.S));
        this.e.setColor(z(this.R));
        this.h.setColor(z(this.Q));
        this.i.setColor(z(this.P));
        for (gl glVar : this.n) {
            if (glVar.isStateful()) {
                glVar.setState(getDrawableState());
            }
        }
        if (this.T.isStateful()) {
            this.T.setState(getDrawableState());
        }
        this.g.setColor(z(this.O));
        this.g.setAlpha(63);
    }

    public final float e0(float f) {
        return (H(f) * this.K) + this.u;
    }

    public final void f0() {
        float f = this.I;
        if (f == 0.0f) {
            return;
        }
        if (((int) f) != f) {
            Log.w(b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "stepSize", Float.valueOf(f)));
        }
        float f2 = this.D;
        if (((int) f2) != f2) {
            Log.w(b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueFrom", Float.valueOf(f2)));
        }
        float f3 = this.E;
        if (((int) f3) != f3) {
            Log.w(b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueTo", Float.valueOf(f3)));
        }
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final int getAccessibilityFocusedVirtualViewId() {
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

    public List<Float> getValues() {
        return new ArrayList(this.F);
    }

    public final void h(gl glVar) {
        glVar.x0(oj.d(this));
    }

    public final Float i(int i) {
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

    public final float j() {
        float f = this.I;
        if (f == 0.0f) {
            return 1.0f;
        }
        return f;
    }

    public final float k(int i) {
        float fJ = j();
        return (this.E - this.D) / fJ <= i ? fJ : Math.round(r1 / r4) * fJ;
    }

    public final void l() {
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

    public final int m() {
        return this.v + (this.s == 1 ? this.n.get(0).getIntrinsicHeight() : 0);
    }

    public final void n() {
        if (this.n.size() > this.F.size()) {
            List<gl> listSubList = this.n.subList(this.F.size(), this.n.size());
            for (gl glVar : listSubList) {
                if (t9.O(this)) {
                    o(glVar);
                }
            }
            listSubList.clear();
        }
        while (this.n.size() < this.F.size()) {
            gl glVarA = this.m.a();
            this.n.add(glVarA);
            if (t9.O(this)) {
                h(glVarA);
            }
        }
        int i = this.n.size() == 1 ? 0 : 1;
        Iterator<gl> it = this.n.iterator();
        while (it.hasNext()) {
            it.next().j0(i);
        }
    }

    public final void o(gl glVar) {
        nj njVarE = oj.e(this);
        if (njVarE != null) {
            njVarE.b(glVar);
            glVar.t0(oj.d(this));
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Iterator<gl> it = this.n.iterator();
        while (it.hasNext()) {
            h(it.next());
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        BaseSlider<S, L, T>.b bVar = this.l;
        if (bVar != null) {
            removeCallbacks(bVar);
        }
        Iterator<gl> it = this.n.iterator();
        while (it.hasNext()) {
            o(it.next());
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
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
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z) {
            w(i);
            this.j.V(this.H);
            return;
        }
        this.G = -1;
        Iterator<gl> it = this.n.iterator();
        while (it.hasNext()) {
            oj.e(this).b(it.next());
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
        Iterator<gl> it = this.n.iterator();
        while (it.hasNext()) {
            oj.e(this).b(it.next());
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
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.r + (this.s == 1 ? this.n.get(0).getIntrinsicHeight() : 0), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.D = sliderState.b;
        this.E = sliderState.c;
        setValuesInternal(sliderState.d);
        this.I = sliderState.e;
        if (sliderState.f) {
            requestFocus();
        }
        q();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.b = this.D;
        sliderState.c = this.E;
        sliderState.d = new ArrayList<>(this.F);
        sliderState.e = this.I;
        sliderState.f = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
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
            Iterator<gl> it = this.n.iterator();
            while (it.hasNext()) {
                oj.e(this).b(it.next());
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

    public final void p(int i) {
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

    public final void q() {
        for (L l : this.o) {
            Iterator<Float> it = this.F.iterator();
            while (it.hasNext()) {
                l.a(this, it.next().floatValue(), false);
            }
        }
    }

    public final void r(Canvas canvas, int i, int i2) {
        float[] activeRange = getActiveRange();
        int i3 = this.u;
        float f = i;
        float f2 = i2;
        canvas.drawLine(i3 + (activeRange[0] * f), f2, i3 + (activeRange[1] * f), f2, this.e);
    }

    public final void s(Canvas canvas, int i, int i2) {
        float[] activeRange = getActiveRange();
        float f = i;
        float f2 = this.u + (activeRange[1] * f);
        if (f2 < r1 + i) {
            float f3 = i2;
            canvas.drawLine(f2, f3, r1 + i, f3, this.d);
        }
        int i3 = this.u;
        float f4 = i3 + (activeRange[0] * f);
        if (f4 > i3) {
            float f5 = i2;
            canvas.drawLine(i3, f5, f4, f5, this.d);
        }
    }

    public void setActiveThumbIndex(int i) {
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
            pi.a((RippleDrawable) background, this.x);
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

    public void setLabelFormatter(sk skVar) {
        this.B = skVar;
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
        this.T.setShapeAppearanceModel(mk.a().p(0, this.w).m());
        hk hkVar = this.T;
        int i2 = this.w;
        hkVar.setBounds(0, 0, i2 * 2, i2 * 2);
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
        this.d.setColor(z(colorStateList));
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

    public void setValues(Float... fArr) {
        ArrayList<Float> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    public final void t(Canvas canvas, int i, int i2) {
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

    public final void u(Canvas canvas) {
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

    public final void v() {
        if (this.s == 2) {
            return;
        }
        Iterator<gl> it = this.n.iterator();
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

    public final void w(int i) {
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

    public final String x(float f) {
        if (A()) {
            return this.B.a(f);
        }
        return String.format(((float) ((int) f)) == f ? "%.0f" : "%.2f", Float.valueOf(f));
    }

    public final float y(int i, float f) {
        int i2 = i + 1;
        int i3 = i - 1;
        return h8.a(f, i3 < 0 ? this.D : this.F.get(i3).floatValue(), i2 >= this.F.size() ? this.E : this.F.get(i2).floatValue());
    }

    public final int z(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    public class b implements Runnable {
        public int b;

        public b() {
            this.b = -1;
        }

        public void a(int i) {
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.this.j.W(this.b, 4);
        }

        public /* synthetic */ b(BaseSlider baseSlider, a aVar) {
            this();
        }
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.sliderStyle);
    }

    public BaseSlider(Context context, AttributeSet attributeSet, int i) {
        super(fl.c(context, attributeSet, i, c), attributeSet, i);
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
        this.C = false;
        this.F = new ArrayList<>();
        this.G = -1;
        this.H = -1;
        this.I = 0.0f;
        this.M = false;
        hk hkVar = new hk();
        this.T = hkVar;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.d = paint;
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
        hkVar.f0(2);
        this.q = ViewConfiguration.get(context2).getScaledTouchSlop();
        c cVar = new c(this);
        this.j = cVar;
        t9.j0(this, cVar);
        this.k = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public void setValues(List<Float> list) {
        setValuesInternal(new ArrayList<>(list));
    }
}
