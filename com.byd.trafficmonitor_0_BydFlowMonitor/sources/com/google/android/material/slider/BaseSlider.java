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
import b.h.m.c0.c;
import b.h.m.t;
import c.b.a.a.d0.o;
import c.b.a.a.d0.p;
import c.b.a.a.i0.h;
import c.b.a.a.i0.m;
import c.b.a.a.j;
import c.b.a.a.j0.a;
import c.b.a.a.j0.b;
import c.b.a.a.k;
import c.b.a.a.l;
import com.bydux.api.common.tools.util.ListUtils;
import com.google.android.material.slider.BaseSlider;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends c.b.a.a.j0.a<S>, T extends c.b.a.a.j0.b<S>> extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f4795b = BaseSlider.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f4796c = k.Widget_MaterialComponents_Slider;
    public MotionEvent A;
    public c.b.a.a.j0.c B;
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
    public final h T;
    public float U;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Paint f4797d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f4798e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f4799f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f4800g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f4801h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Paint f4802i;
    public final c j;
    public final AccessibilityManager k;
    public BaseSlider<S, L, T>.b l;
    public final d m;
    public final List<c.b.a.a.n0.a> n;
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

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f4803b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f4804c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ArrayList<Float> f4805d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f4806e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f4807f;

        public static class a implements Parcelable.Creator<SliderState> {
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

        public /* synthetic */ SliderState(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeFloat(this.f4803b);
            parcel.writeFloat(this.f4804c);
            parcel.writeList(this.f4805d);
            parcel.writeFloat(this.f4806e);
            parcel.writeBooleanArray(new boolean[]{this.f4807f});
        }

        public SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        public SliderState(Parcel parcel) {
            super(parcel);
            this.f4803b = parcel.readFloat();
            this.f4804c = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.f4805d = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.f4806e = parcel.readFloat();
            this.f4807f = parcel.createBooleanArray()[0];
        }
    }

    public class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AttributeSet f4808a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4809b;

        public a(AttributeSet attributeSet, int i2) {
            this.f4808a = attributeSet;
            this.f4809b = i2;
        }

        @Override // com.google.android.material.slider.BaseSlider.d
        public c.b.a.a.n0.a a() {
            TypedArray typedArrayH = c.b.a.a.d0.k.h(BaseSlider.this.getContext(), this.f4808a, l.Slider, this.f4809b, BaseSlider.f4796c, new int[0]);
            c.b.a.a.n0.a aVarL = BaseSlider.L(BaseSlider.this.getContext(), typedArrayH);
            typedArrayH.recycle();
            return aVarL;
        }
    }

    public static class c extends b.j.a.a {
        public final BaseSlider<?, ?, ?> q;
        public Rect r;

        public c(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.r = new Rect();
            this.q = baseSlider;
        }

        @Override // b.j.a.a
        public int B(float f2, float f3) {
            for (int i2 = 0; i2 < this.q.getValues().size(); i2++) {
                this.q.W(i2, this.r);
                if (this.r.contains((int) f2, (int) f3)) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // b.j.a.a
        public void C(List<Integer> list) {
            for (int i2 = 0; i2 < this.q.getValues().size(); i2++) {
                list.add(Integer.valueOf(i2));
            }
        }

        @Override // b.j.a.a
        public boolean L(int i2, int i3, Bundle bundle) {
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
            if (!this.q.U(i2, b.h.h.a.a(this.q.getValues().get(i2).floatValue() + fK, this.q.getValueFrom(), this.q.getValueTo()))) {
                return false;
            }
            this.q.X();
            this.q.postInvalidate();
            E(i2);
            return true;
        }

        @Override // b.j.a.a
        public void P(int i2, b.h.m.c0.c cVar) {
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
            cVar.u0(c.d.a(1, valueFrom, valueTo, fFloatValue));
            cVar.c0(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.q.getContentDescription() != null) {
                sb.append(this.q.getContentDescription());
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            }
            if (values.size() > 1) {
                sb.append(Y(i2));
                sb.append(this.q.x(fFloatValue));
            }
            cVar.g0(sb.toString());
            this.q.W(i2, this.r);
            cVar.X(this.r);
        }

        public final String Y(int i2) {
            return i2 == this.q.getValues().size() + (-1) ? this.q.getContext().getString(j.material_slider_range_end) : i2 == 0 ? this.q.getContext().getString(j.material_slider_range_start) : "";
        }
    }

    public interface d {
        c.b.a.a.n0.a a();
    }

    public BaseSlider(Context context) {
        this(context, null);
    }

    public static c.b.a.a.n0.a L(Context context, TypedArray typedArray) {
        return c.b.a.a.n0.a.r0(context, null, 0, typedArray.getResourceId(l.Slider_labelStyle, k.Widget_MaterialComponents_Tooltip));
    }

    public static int N(float[] fArr, float f2) {
        return Math.round(f2 * ((fArr.length / 2) - 1));
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
        this.f4797d.setStrokeWidth(this.t);
        this.f4798e.setStrokeWidth(this.t);
        this.f4801h.setStrokeWidth(this.t / 2.0f);
        this.f4802i.setStrokeWidth(this.t / 2.0f);
    }

    public final boolean C() {
        return t.z(this) == 1;
    }

    public final void D(Resources resources) {
        this.r = resources.getDimensionPixelSize(c.b.a.a.d.mtrl_slider_widget_height);
        this.u = resources.getDimensionPixelOffset(c.b.a.a.d.mtrl_slider_track_side_padding);
        this.v = resources.getDimensionPixelOffset(c.b.a.a.d.mtrl_slider_track_top);
        this.y = resources.getDimensionPixelSize(c.b.a.a.d.mtrl_slider_label_padding);
    }

    public final void E(Canvas canvas, int i2, int i3) {
        if (R()) {
            int iH = (int) (this.u + (H(this.F.get(this.H).floatValue()) * i2));
            if (Build.VERSION.SDK_INT < 28) {
                int i4 = this.x;
                canvas.clipRect(iH - i4, i3 - i4, iH + i4, i4 + i3, Region.Op.UNION);
            }
            canvas.drawCircle(iH, i3, this.x, this.f4800g);
        }
    }

    public final boolean F(int i2) {
        int i3 = this.H;
        int iC = (int) b.h.h.a.c(((long) i3) + ((long) i2), 0L, this.F.size() - 1);
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

    public final boolean G(int i2) {
        if (C()) {
            i2 = i2 == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i2;
        }
        return F(i2);
    }

    public final float H(float f2) {
        float f3 = this.D;
        float f4 = (f2 - f3) / (this.E - f3);
        return C() ? 1.0f - f4 : f4;
    }

    public final Boolean I(int i2, KeyEvent keyEvent) {
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

    public final void O(Context context, AttributeSet attributeSet, int i2) {
        TypedArray typedArrayH = c.b.a.a.d0.k.h(context, attributeSet, l.Slider, i2, f4796c, new int[0]);
        this.D = typedArrayH.getFloat(l.Slider_android_valueFrom, 0.0f);
        this.E = typedArrayH.getFloat(l.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.D));
        this.I = typedArrayH.getFloat(l.Slider_android_stepSize, 0.0f);
        int i3 = l.Slider_trackColor;
        boolean zHasValue = typedArrayH.hasValue(i3);
        int i4 = zHasValue ? i3 : l.Slider_trackColorInactive;
        if (!zHasValue) {
            i3 = l.Slider_trackColorActive;
        }
        ColorStateList colorStateListA = c.b.a.a.f0.c.a(context, typedArrayH, i4);
        if (colorStateListA == null) {
            colorStateListA = b.b.l.a.a.c(context, c.b.a.a.c.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(colorStateListA);
        ColorStateList colorStateListA2 = c.b.a.a.f0.c.a(context, typedArrayH, i3);
        if (colorStateListA2 == null) {
            colorStateListA2 = b.b.l.a.a.c(context, c.b.a.a.c.material_slider_active_track_color);
        }
        setTrackActiveTintList(colorStateListA2);
        this.T.X(c.b.a.a.f0.c.a(context, typedArrayH, l.Slider_thumbColor));
        ColorStateList colorStateListA3 = c.b.a.a.f0.c.a(context, typedArrayH, l.Slider_haloColor);
        if (colorStateListA3 == null) {
            colorStateListA3 = b.b.l.a.a.c(context, c.b.a.a.c.material_slider_halo_color);
        }
        setHaloTintList(colorStateListA3);
        int i5 = l.Slider_tickColor;
        boolean zHasValue2 = typedArrayH.hasValue(i5);
        int i6 = zHasValue2 ? i5 : l.Slider_tickColorInactive;
        if (!zHasValue2) {
            i5 = l.Slider_tickColorActive;
        }
        ColorStateList colorStateListA4 = c.b.a.a.f0.c.a(context, typedArrayH, i6);
        if (colorStateListA4 == null) {
            colorStateListA4 = b.b.l.a.a.c(context, c.b.a.a.c.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(colorStateListA4);
        ColorStateList colorStateListA5 = c.b.a.a.f0.c.a(context, typedArrayH, i5);
        if (colorStateListA5 == null) {
            colorStateListA5 = b.b.l.a.a.c(context, c.b.a.a.c.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(colorStateListA5);
        setThumbRadius(typedArrayH.getDimensionPixelSize(l.Slider_thumbRadius, 0));
        setHaloRadius(typedArrayH.getDimensionPixelSize(l.Slider_haloRadius, 0));
        setThumbElevation(typedArrayH.getDimension(l.Slider_thumbElevation, 0.0f));
        setTrackHeight(typedArrayH.getDimensionPixelSize(l.Slider_trackHeight, 0));
        this.s = typedArrayH.getInt(l.Slider_labelBehavior, 0);
        if (!typedArrayH.getBoolean(l.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        typedArrayH.recycle();
    }

    public final void P(int i2) {
        BaseSlider<S, L, T>.b bVar = this.l;
        if (bVar == null) {
            this.l = new b(this, null);
        } else {
            removeCallbacks(bVar);
        }
        this.l.a(i2);
        postDelayed(this.l, 200L);
    }

    public final void Q(c.b.a.a.n0.a aVar, float f2) {
        aVar.y0(x(f2));
        int iH = (this.u + ((int) (H(f2) * this.K))) - (aVar.getIntrinsicWidth() / 2);
        int iM = m() - (this.y + this.w);
        aVar.setBounds(iH, iM - aVar.getIntrinsicHeight(), aVar.getIntrinsicWidth() + iH, iM);
        Rect rect = new Rect(aVar.getBounds());
        c.b.a.a.d0.c.c(p.d(this), this, rect);
        aVar.setBounds(rect);
        p.e(this).a(aVar);
    }

    public final boolean R() {
        return this.L || Build.VERSION.SDK_INT < 21 || !(getBackground() instanceof RippleDrawable);
    }

    public final boolean S(float f2) {
        return U(this.G, f2);
    }

    public final double T(float f2) {
        float f3 = this.I;
        if (f3 <= 0.0f) {
            return f2;
        }
        int i2 = (int) ((this.E - this.D) / f3);
        return ((double) Math.round(f2 * i2)) / ((double) i2);
    }

    public final boolean U(int i2, float f2) {
        if (Math.abs(f2 - this.F.get(i2).floatValue()) < 1.0E-4d) {
            return false;
        }
        this.F.set(i2, Float.valueOf(y(i2, f2)));
        this.H = i2;
        p(i2);
        return true;
    }

    public final boolean V() {
        return S(getValueOfTouchPosition());
    }

    public void W(int i2, Rect rect) {
        int iH = this.u + ((int) (H(getValues().get(i2).floatValue()) * this.K));
        int iM = m();
        int i3 = this.w;
        rect.set(iH - i3, iM - i3, iH + i3, iM + i3);
    }

    public final void X() {
        if (R() || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int iH = (int) ((H(this.F.get(this.H).floatValue()) * this.K) + this.u);
            int iM = m();
            int i2 = this.x;
            b.h.f.l.a.l(background, iH - i2, iM - i2, iH + i2, iM + i2);
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
        for (Float f2 : this.F) {
            if (f2.floatValue() < this.D || f2.floatValue() > this.E) {
                throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", Float.toString(f2.floatValue()), Float.toString(this.D), Float.toString(this.E)));
            }
            if (this.I > 0.0f && !d0(f2.floatValue())) {
                throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", Float.toString(f2.floatValue()), Float.toString(this.D), Float.toString(this.I), Float.toString(this.I)));
            }
        }
    }

    public final boolean d0(float f2) {
        double dDoubleValue = new BigDecimal(Float.toString(f2)).subtract(new BigDecimal(Float.toString(this.D))).divide(new BigDecimal(Float.toString(this.I)), MathContext.DECIMAL64).doubleValue();
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
        this.f4797d.setColor(z(this.S));
        this.f4798e.setColor(z(this.R));
        this.f4801h.setColor(z(this.Q));
        this.f4802i.setColor(z(this.P));
        for (c.b.a.a.n0.a aVar : this.n) {
            if (aVar.isStateful()) {
                aVar.setState(getDrawableState());
            }
        }
        if (this.T.isStateful()) {
            this.T.setState(getDrawableState());
        }
        this.f4800g.setColor(z(this.O));
        this.f4800g.setAlpha(63);
    }

    public final float e0(float f2) {
        return (H(f2) * this.K) + this.u;
    }

    public final void f0() {
        float f2 = this.I;
        if (f2 == 0.0f) {
            return;
        }
        if (((int) f2) != f2) {
            Log.w(f4795b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "stepSize", Float.valueOf(f2)));
        }
        float f3 = this.D;
        if (((int) f3) != f3) {
            Log.w(f4795b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueFrom", Float.valueOf(f3)));
        }
        float f4 = this.E;
        if (((int) f4) != f4) {
            Log.w(f4795b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueTo", Float.valueOf(f4)));
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

    public final void h(c.b.a.a.n0.a aVar) {
        aVar.x0(p.d(this));
    }

    public final Float i(int i2) {
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

    public final float j() {
        float f2 = this.I;
        if (f2 == 0.0f) {
            return 1.0f;
        }
        return f2;
    }

    public final float k(int i2) {
        float fJ = j();
        return (this.E - this.D) / fJ <= i2 ? fJ : Math.round(r1 / r4) * fJ;
    }

    public final void l() {
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

    public final int m() {
        return this.v + (this.s == 1 ? this.n.get(0).getIntrinsicHeight() : 0);
    }

    public final void n() {
        if (this.n.size() > this.F.size()) {
            List<c.b.a.a.n0.a> listSubList = this.n.subList(this.F.size(), this.n.size());
            for (c.b.a.a.n0.a aVar : listSubList) {
                if (t.O(this)) {
                    o(aVar);
                }
            }
            listSubList.clear();
        }
        while (this.n.size() < this.F.size()) {
            c.b.a.a.n0.a aVarA = this.m.a();
            this.n.add(aVarA);
            if (t.O(this)) {
                h(aVarA);
            }
        }
        int i2 = this.n.size() == 1 ? 0 : 1;
        Iterator<c.b.a.a.n0.a> it = this.n.iterator();
        while (it.hasNext()) {
            it.next().j0(i2);
        }
    }

    public final void o(c.b.a.a.n0.a aVar) {
        o oVarE = p.e(this);
        if (oVarE != null) {
            oVarE.b(aVar);
            aVar.t0(p.d(this));
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Iterator<c.b.a.a.n0.a> it = this.n.iterator();
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
        Iterator<c.b.a.a.n0.a> it = this.n.iterator();
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
    public void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (z) {
            w(i2);
            this.j.V(this.H);
            return;
        }
        this.G = -1;
        Iterator<c.b.a.a.n0.a> it = this.n.iterator();
        while (it.hasNext()) {
            p.e(this).b(it.next());
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
        Iterator<c.b.a.a.n0.a> it = this.n.iterator();
        while (it.hasNext()) {
            p.e(this).b(it.next());
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
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(this.r + (this.s == 1 ? this.n.get(0).getIntrinsicHeight() : 0), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.D = sliderState.f4803b;
        this.E = sliderState.f4804c;
        setValuesInternal(sliderState.f4805d);
        this.I = sliderState.f4806e;
        if (sliderState.f4807f) {
            requestFocus();
        }
        q();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.f4803b = this.D;
        sliderState.f4804c = this.E;
        sliderState.f4805d = new ArrayList<>(this.F);
        sliderState.f4806e = this.I;
        sliderState.f4807f = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
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
            Iterator<c.b.a.a.n0.a> it = this.n.iterator();
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

    public final void p(int i2) {
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

    public final void q() {
        for (L l : this.o) {
            Iterator<Float> it = this.F.iterator();
            while (it.hasNext()) {
                l.a(this, it.next().floatValue(), false);
            }
        }
    }

    public final void r(Canvas canvas, int i2, int i3) {
        float[] activeRange = getActiveRange();
        int i4 = this.u;
        float f2 = i2;
        float f3 = i3;
        canvas.drawLine(i4 + (activeRange[0] * f2), f3, i4 + (activeRange[1] * f2), f3, this.f4798e);
    }

    public final void s(Canvas canvas, int i2, int i3) {
        float[] activeRange = getActiveRange();
        float f2 = i2;
        float f3 = this.u + (activeRange[1] * f2);
        if (f3 < r1 + i2) {
            float f4 = i3;
            canvas.drawLine(f3, f4, r1 + i2, f4, this.f4797d);
        }
        int i4 = this.u;
        float f5 = i4 + (activeRange[0] * f2);
        if (f5 > i4) {
            float f6 = i3;
            canvas.drawLine(i4, f6, f5, f6, this.f4797d);
        }
    }

    public void setActiveThumbIndex(int i2) {
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
            c.b.a.a.z.a.a((RippleDrawable) background, this.x);
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
        this.f4800g.setColor(z(colorStateList));
        this.f4800g.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i2) {
        if (this.s != i2) {
            this.s = i2;
            requestLayout();
        }
    }

    public void setLabelFormatter(c.b.a.a.j0.c cVar) {
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
        this.f4802i.setColor(z(colorStateList));
        invalidate();
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.Q)) {
            return;
        }
        this.Q = colorStateList;
        this.f4801h.setColor(z(colorStateList));
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
        this.f4798e.setColor(z(colorStateList));
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
        this.f4797d.setColor(z(colorStateList));
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

    public void setValues(Float... fArr) {
        ArrayList<Float> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    public final void t(Canvas canvas, int i2, int i3) {
        if (!isEnabled()) {
            Iterator<Float> it = this.F.iterator();
            while (it.hasNext()) {
                canvas.drawCircle(this.u + (H(it.next().floatValue()) * i2), i3, this.w, this.f4799f);
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

    public final void u(Canvas canvas) {
        float[] activeRange = getActiveRange();
        int iN = N(this.J, activeRange[0]);
        int iN2 = N(this.J, activeRange[1]);
        int i2 = iN * 2;
        canvas.drawPoints(this.J, 0, i2, this.f4801h);
        int i3 = iN2 * 2;
        canvas.drawPoints(this.J, i2, i3 - i2, this.f4802i);
        float[] fArr = this.J;
        canvas.drawPoints(fArr, i3, fArr.length - i3, this.f4801h);
    }

    public final void v() {
        if (this.s == 2) {
            return;
        }
        Iterator<c.b.a.a.n0.a> it = this.n.iterator();
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

    public final void w(int i2) {
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

    public final String x(float f2) {
        if (A()) {
            return this.B.a(f2);
        }
        return String.format(((float) ((int) f2)) == f2 ? "%.0f" : "%.2f", Float.valueOf(f2));
    }

    public final float y(int i2, float f2) {
        int i3 = i2 + 1;
        int i4 = i2 - 1;
        return b.h.h.a.a(f2, i4 < 0 ? this.D : this.F.get(i4).floatValue(), i3 >= this.F.size() ? this.E : this.F.get(i3).floatValue());
    }

    public final int z(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4811b;

        public b() {
            this.f4811b = -1;
        }

        public void a(int i2) {
            this.f4811b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.this.j.W(this.f4811b, 4);
        }

        public /* synthetic */ b(BaseSlider baseSlider, a aVar) {
            this();
        }
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.b.a.a.b.sliderStyle);
    }

    public BaseSlider(Context context, AttributeSet attributeSet, int i2) {
        super(c.b.a.a.m0.a.a.c(context, attributeSet, i2, f4796c), attributeSet, i2);
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
        this.f4797d = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.f4798e = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint(1);
        this.f4799f = paint3;
        paint3.setStyle(Paint.Style.FILL);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.f4800g = paint4;
        paint4.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.f4801h = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        Paint paint6 = new Paint();
        this.f4802i = paint6;
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
        t.k0(this, cVar);
        this.k = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public void setValues(List<Float> list) {
        setValuesInternal(new ArrayList<>(list));
    }
}
