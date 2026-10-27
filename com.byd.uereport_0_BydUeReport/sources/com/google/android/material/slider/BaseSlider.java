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
import e6.h;
import e6.m;
import f6.a;
import f6.b;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import o0.t;
import p0.c;
import z5.k;
import z5.o;
import z5.p;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends f6.a<S>, T extends f6.b<S>> extends View {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f3982d = BaseSlider.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3983e = R$style.Widget_MaterialComponents_Slider;
    public int A;
    public float B;
    public MotionEvent C;
    public f6.c D;
    public boolean E;
    public float F;
    public float G;
    public ArrayList<Float> H;
    public int I;
    public int J;
    public float K;
    public float[] L;
    public int M;
    public boolean N;
    public boolean O;
    public boolean P;
    public ColorStateList Q;
    public ColorStateList R;
    public ColorStateList S;
    public ColorStateList T;
    public ColorStateList U;
    public final h V;
    public float W;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f3984f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f3985g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f3986h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Paint f3987i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Paint f3988j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Paint f3989k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final c f3990l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AccessibilityManager f3991m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public BaseSlider<S, L, T>.b f3992n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final d f3993o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final List<j6.a> f3994p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final List<L> f3995q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final List<T> f3996r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f3997s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3998t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f3999u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f4000v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f4001w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f4002x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f4003y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f4004z;

    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f4005d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f4006e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ArrayList<Float> f4007f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f4008g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f4009h;

        public static class a implements Parcelable.Creator<SliderState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SliderState createFromParcel(Parcel parcel) {
                return new SliderState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SliderState[] newArray(int i8) {
                return new SliderState[i8];
            }
        }

        public /* synthetic */ SliderState(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeFloat(this.f4005d);
            parcel.writeFloat(this.f4006e);
            parcel.writeList(this.f4007f);
            parcel.writeFloat(this.f4008g);
            parcel.writeBooleanArray(new boolean[]{this.f4009h});
        }

        public SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        public SliderState(Parcel parcel) {
            super(parcel);
            this.f4005d = parcel.readFloat();
            this.f4006e = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.f4007f = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.f4008g = parcel.readFloat();
            this.f4009h = parcel.createBooleanArray()[0];
        }
    }

    public class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AttributeSet f4010a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4011b;

        public a(AttributeSet attributeSet, int i8) {
            this.f4010a = attributeSet;
            this.f4011b = i8;
        }

        @Override // com.google.android.material.slider.BaseSlider.d
        public j6.a a() {
            TypedArray typedArrayH = k.h(BaseSlider.this.getContext(), this.f4010a, R$styleable.Slider, this.f4011b, BaseSlider.f3983e, new int[0]);
            j6.a aVarL = BaseSlider.L(BaseSlider.this.getContext(), typedArrayH);
            typedArrayH.recycle();
            return aVarL;
        }
    }

    public static class c extends t0.a {

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public final BaseSlider<?, ?, ?> f4015q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public Rect f4016r;

        public c(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.f4016r = new Rect();
            this.f4015q = baseSlider;
        }

        @Override // t0.a
        public int B(float f8, float f9) {
            for (int i8 = 0; i8 < this.f4015q.getValues().size(); i8++) {
                this.f4015q.W(i8, this.f4016r);
                if (this.f4016r.contains((int) f8, (int) f9)) {
                    return i8;
                }
            }
            return -1;
        }

        @Override // t0.a
        public void C(List<Integer> list) {
            for (int i8 = 0; i8 < this.f4015q.getValues().size(); i8++) {
                list.add(Integer.valueOf(i8));
            }
        }

        @Override // t0.a
        public boolean L(int i8, int i9, Bundle bundle) {
            if (!this.f4015q.isEnabled()) {
                return false;
            }
            if (i9 != 4096 && i9 != 8192) {
                if (i9 == 16908349 && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                    if (this.f4015q.U(i8, bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"))) {
                        this.f4015q.X();
                        this.f4015q.postInvalidate();
                        E(i8);
                        return true;
                    }
                }
                return false;
            }
            float fK = this.f4015q.k(20);
            if (i9 == 8192) {
                fK = -fK;
            }
            if (this.f4015q.C()) {
                fK = -fK;
            }
            if (!this.f4015q.U(i8, j0.a.a(this.f4015q.getValues().get(i8).floatValue() + fK, this.f4015q.getValueFrom(), this.f4015q.getValueTo()))) {
                return false;
            }
            this.f4015q.X();
            this.f4015q.postInvalidate();
            E(i8);
            return true;
        }

        @Override // t0.a
        public void P(int i8, p0.c cVar) {
            cVar.b(c.a.H);
            List<Float> values = this.f4015q.getValues();
            float fFloatValue = values.get(i8).floatValue();
            float valueFrom = this.f4015q.getValueFrom();
            float valueTo = this.f4015q.getValueTo();
            if (this.f4015q.isEnabled()) {
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
            if (this.f4015q.getContentDescription() != null) {
                sb.append(this.f4015q.getContentDescription());
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            }
            if (values.size() > 1) {
                sb.append(Y(i8));
                sb.append(this.f4015q.x(fFloatValue));
            }
            cVar.g0(sb.toString());
            this.f4015q.W(i8, this.f4016r);
            cVar.X(this.f4016r);
        }

        public final String Y(int i8) {
            return i8 == this.f4015q.getValues().size() + (-1) ? this.f4015q.getContext().getString(R$string.material_slider_range_end) : i8 == 0 ? this.f4015q.getContext().getString(R$string.material_slider_range_start) : "";
        }
    }

    public interface d {
        j6.a a();
    }

    public BaseSlider(Context context) {
        this(context, null);
    }

    public static j6.a L(Context context, TypedArray typedArray) {
        return j6.a.r0(context, null, 0, typedArray.getResourceId(R$styleable.Slider_labelStyle, R$style.Widget_MaterialComponents_Tooltip));
    }

    public static int N(float[] fArr, float f8) {
        return Math.round(f8 * ((fArr.length / 2) - 1));
    }

    private float[] getActiveRange() {
        float fFloatValue = ((Float) Collections.max(getValues())).floatValue();
        float fFloatValue2 = ((Float) Collections.min(getValues())).floatValue();
        if (this.H.size() == 1) {
            fFloatValue2 = this.F;
        }
        float fH = H(fFloatValue2);
        float fH2 = H(fFloatValue);
        return C() ? new float[]{fH2, fH} : new float[]{fH, fH2};
    }

    private float getValueOfTouchPosition() {
        double dT = T(this.W);
        if (C()) {
            dT = 1.0d - dT;
        }
        float f8 = this.G;
        float f9 = this.F;
        return (float) ((dT * ((double) (f8 - f9))) + ((double) f9));
    }

    private float getValueOfTouchPositionAbsolute() {
        float f8 = this.W;
        if (C()) {
            f8 = 1.0f - f8;
        }
        float f9 = this.G;
        float f10 = this.F;
        return (f8 * (f9 - f10)) + f10;
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
        if (this.H.size() == arrayList.size() && this.H.equals(arrayList)) {
            return;
        }
        this.H = arrayList;
        this.P = true;
        this.J = 0;
        X();
        n();
        q();
        postInvalidate();
    }

    public boolean A() {
        return this.D != null;
    }

    public final void B() {
        this.f3984f.setStrokeWidth(this.f4000v);
        this.f3985g.setStrokeWidth(this.f4000v);
        this.f3988j.setStrokeWidth(this.f4000v / 2.0f);
        this.f3989k.setStrokeWidth(this.f4000v / 2.0f);
    }

    public final boolean C() {
        return t.A(this) == 1;
    }

    public final void D(Resources resources) {
        this.f3998t = resources.getDimensionPixelSize(R$dimen.mtrl_slider_widget_height);
        this.f4001w = resources.getDimensionPixelOffset(R$dimen.mtrl_slider_track_side_padding);
        this.f4002x = resources.getDimensionPixelOffset(R$dimen.mtrl_slider_track_top);
        this.A = resources.getDimensionPixelSize(R$dimen.mtrl_slider_label_padding);
    }

    public final void E(Canvas canvas, int i8, int i9) {
        if (R()) {
            int iH = (int) (this.f4001w + (H(this.H.get(this.J).floatValue()) * i8));
            if (Build.VERSION.SDK_INT < 28) {
                int i10 = this.f4004z;
                canvas.clipRect(iH - i10, i9 - i10, iH + i10, i10 + i9, Region.Op.UNION);
            }
            canvas.drawCircle(iH, i9, this.f4004z, this.f3987i);
        }
    }

    public final boolean F(int i8) {
        int i9 = this.J;
        int iC = (int) j0.a.c(((long) i9) + ((long) i8), 0L, this.H.size() - 1);
        this.J = iC;
        if (iC == i9) {
            return false;
        }
        if (this.I != -1) {
            this.I = iC;
        }
        X();
        postInvalidate();
        return true;
    }

    public final boolean G(int i8) {
        if (C()) {
            i8 = i8 == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i8;
        }
        return F(i8);
    }

    public final float H(float f8) {
        float f9 = this.F;
        float f10 = (f8 - f9) / (this.G - f9);
        return C() ? 1.0f - f10 : f10;
    }

    public final Boolean I(int i8, KeyEvent keyEvent) {
        if (i8 == 61) {
            return keyEvent.hasNoModifiers() ? Boolean.valueOf(F(1)) : keyEvent.isShiftPressed() ? Boolean.valueOf(F(-1)) : Boolean.FALSE;
        }
        if (i8 != 66) {
            if (i8 != 81) {
                if (i8 == 69) {
                    F(-1);
                    return Boolean.TRUE;
                }
                if (i8 != 70) {
                    switch (i8) {
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
        this.I = this.J;
        postInvalidate();
        return Boolean.TRUE;
    }

    public final void J() {
        Iterator<T> it = this.f3996r.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public final void K() {
        Iterator<T> it = this.f3996r.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    public boolean M() {
        if (this.I != -1) {
            return true;
        }
        float valueOfTouchPositionAbsolute = getValueOfTouchPositionAbsolute();
        float fE0 = e0(valueOfTouchPositionAbsolute);
        this.I = 0;
        float fAbs = Math.abs(this.H.get(0).floatValue() - valueOfTouchPositionAbsolute);
        for (int i8 = 1; i8 < this.H.size(); i8++) {
            float fAbs2 = Math.abs(this.H.get(i8).floatValue() - valueOfTouchPositionAbsolute);
            float fE02 = e0(this.H.get(i8).floatValue());
            if (Float.compare(fAbs2, fAbs) > 1) {
                break;
            }
            boolean z7 = !C() ? fE02 - fE0 >= 0.0f : fE02 - fE0 <= 0.0f;
            if (Float.compare(fAbs2, fAbs) < 0) {
                this.I = i8;
            } else {
                if (Float.compare(fAbs2, fAbs) != 0) {
                    continue;
                } else {
                    if (Math.abs(fE02 - fE0) < this.f3997s) {
                        this.I = -1;
                        return false;
                    }
                    if (z7) {
                        this.I = i8;
                    }
                }
            }
            fAbs = fAbs2;
        }
        return this.I != -1;
    }

    public final void O(Context context, AttributeSet attributeSet, int i8) {
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.Slider, i8, f3983e, new int[0]);
        this.F = typedArrayH.getFloat(R$styleable.Slider_android_valueFrom, 0.0f);
        this.G = typedArrayH.getFloat(R$styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.F));
        this.K = typedArrayH.getFloat(R$styleable.Slider_android_stepSize, 0.0f);
        int i9 = R$styleable.Slider_trackColor;
        boolean zHasValue = typedArrayH.hasValue(i9);
        int i10 = zHasValue ? i9 : R$styleable.Slider_trackColorInactive;
        if (!zHasValue) {
            i9 = R$styleable.Slider_trackColorActive;
        }
        ColorStateList colorStateListA = b6.c.a(context, typedArrayH, i10);
        if (colorStateListA == null) {
            colorStateListA = m.a.c(context, R$color.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(colorStateListA);
        ColorStateList colorStateListA2 = b6.c.a(context, typedArrayH, i9);
        if (colorStateListA2 == null) {
            colorStateListA2 = m.a.c(context, R$color.material_slider_active_track_color);
        }
        setTrackActiveTintList(colorStateListA2);
        this.V.X(b6.c.a(context, typedArrayH, R$styleable.Slider_thumbColor));
        ColorStateList colorStateListA3 = b6.c.a(context, typedArrayH, R$styleable.Slider_haloColor);
        if (colorStateListA3 == null) {
            colorStateListA3 = m.a.c(context, R$color.material_slider_halo_color);
        }
        setHaloTintList(colorStateListA3);
        int i11 = R$styleable.Slider_tickColor;
        boolean zHasValue2 = typedArrayH.hasValue(i11);
        int i12 = zHasValue2 ? i11 : R$styleable.Slider_tickColorInactive;
        if (!zHasValue2) {
            i11 = R$styleable.Slider_tickColorActive;
        }
        ColorStateList colorStateListA4 = b6.c.a(context, typedArrayH, i12);
        if (colorStateListA4 == null) {
            colorStateListA4 = m.a.c(context, R$color.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(colorStateListA4);
        ColorStateList colorStateListA5 = b6.c.a(context, typedArrayH, i11);
        if (colorStateListA5 == null) {
            colorStateListA5 = m.a.c(context, R$color.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(colorStateListA5);
        setThumbRadius(typedArrayH.getDimensionPixelSize(R$styleable.Slider_thumbRadius, 0));
        setHaloRadius(typedArrayH.getDimensionPixelSize(R$styleable.Slider_haloRadius, 0));
        setThumbElevation(typedArrayH.getDimension(R$styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(typedArrayH.getDimensionPixelSize(R$styleable.Slider_trackHeight, 0));
        this.f3999u = typedArrayH.getInt(R$styleable.Slider_labelBehavior, 0);
        if (!typedArrayH.getBoolean(R$styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        typedArrayH.recycle();
    }

    public final void P(int i8) {
        BaseSlider<S, L, T>.b bVar = this.f3992n;
        if (bVar == null) {
            this.f3992n = new b(this, null);
        } else {
            removeCallbacks(bVar);
        }
        this.f3992n.a(i8);
        postDelayed(this.f3992n, 200L);
    }

    public final void Q(j6.a aVar, float f8) {
        aVar.y0(x(f8));
        int iH = (this.f4001w + ((int) (H(f8) * this.M))) - (aVar.getIntrinsicWidth() / 2);
        int iM = m() - (this.A + this.f4003y);
        aVar.setBounds(iH, iM - aVar.getIntrinsicHeight(), aVar.getIntrinsicWidth() + iH, iM);
        Rect rect = new Rect(aVar.getBounds());
        z5.c.c(p.d(this), this, rect);
        aVar.setBounds(rect);
        p.e(this).a(aVar);
    }

    public final boolean R() {
        return this.N || Build.VERSION.SDK_INT < 21 || !(getBackground() instanceof RippleDrawable);
    }

    public final boolean S(float f8) {
        return U(this.I, f8);
    }

    public final double T(float f8) {
        float f9 = this.K;
        if (f9 <= 0.0f) {
            return f8;
        }
        int i8 = (int) ((this.G - this.F) / f9);
        return ((double) Math.round(f8 * i8)) / ((double) i8);
    }

    public final boolean U(int i8, float f8) {
        if (Math.abs(f8 - this.H.get(i8).floatValue()) < 1.0E-4d) {
            return false;
        }
        this.H.set(i8, Float.valueOf(y(i8, f8)));
        this.J = i8;
        p(i8);
        return true;
    }

    public final boolean V() {
        return S(getValueOfTouchPosition());
    }

    public void W(int i8, Rect rect) {
        int iH = this.f4001w + ((int) (H(getValues().get(i8).floatValue()) * this.M));
        int iM = m();
        int i9 = this.f4003y;
        rect.set(iH - i9, iM - i9, iH + i9, iM + i9);
    }

    public final void X() {
        if (R() || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int iH = (int) ((H(this.H.get(this.J).floatValue()) * this.M) + this.f4001w);
            int iM = m();
            int i8 = this.f4004z;
            h0.a.l(background, iH - i8, iM - i8, iH + i8, iM + i8);
        }
    }

    public final void Y() {
        if (this.P) {
            a0();
            b0();
            Z();
            c0();
            f0();
            this.P = false;
        }
    }

    public final void Z() {
        if (this.K > 0.0f && !d0(this.G)) {
            throw new IllegalStateException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.toString(this.K), Float.toString(this.F), Float.toString(this.G)));
        }
    }

    public final void a0() {
        if (this.F >= this.G) {
            throw new IllegalStateException(String.format("valueFrom(%s) must be smaller than valueTo(%s)", Float.toString(this.F), Float.toString(this.G)));
        }
    }

    public final void b0() {
        if (this.G <= this.F) {
            throw new IllegalStateException(String.format("valueTo(%s) must be greater than valueFrom(%s)", Float.toString(this.G), Float.toString(this.F)));
        }
    }

    public final void c0() {
        for (Float f8 : this.H) {
            if (f8.floatValue() < this.F || f8.floatValue() > this.G) {
                throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", Float.toString(f8.floatValue()), Float.toString(this.F), Float.toString(this.G)));
            }
            if (this.K > 0.0f && !d0(f8.floatValue())) {
                throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", Float.toString(f8.floatValue()), Float.toString(this.F), Float.toString(this.K), Float.toString(this.K)));
            }
        }
    }

    public final boolean d0(float f8) {
        double dDoubleValue = new BigDecimal(Float.toString(f8)).subtract(new BigDecimal(Float.toString(this.F))).divide(new BigDecimal(Float.toString(this.K)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(dDoubleValue)) - dDoubleValue) < 1.0E-4d;
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.f3990l.v(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.f3984f.setColor(z(this.U));
        this.f3985g.setColor(z(this.T));
        this.f3988j.setColor(z(this.S));
        this.f3989k.setColor(z(this.R));
        for (j6.a aVar : this.f3994p) {
            if (aVar.isStateful()) {
                aVar.setState(getDrawableState());
            }
        }
        if (this.V.isStateful()) {
            this.V.setState(getDrawableState());
        }
        this.f3987i.setColor(z(this.Q));
        this.f3987i.setAlpha(63);
    }

    public final float e0(float f8) {
        return (H(f8) * this.M) + this.f4001w;
    }

    public final void f0() {
        float f8 = this.K;
        if (f8 == 0.0f) {
            return;
        }
        if (((int) f8) != f8) {
            Log.w(f3982d, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "stepSize", Float.valueOf(f8)));
        }
        float f9 = this.F;
        if (((int) f9) != f9) {
            Log.w(f3982d, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueFrom", Float.valueOf(f9)));
        }
        float f10 = this.G;
        if (((int) f10) != f10) {
            Log.w(f3982d, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueTo", Float.valueOf(f10)));
        }
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.f3990l.x();
    }

    public int getActiveThumbIndex() {
        return this.I;
    }

    public int getFocusedThumbIndex() {
        return this.J;
    }

    public int getHaloRadius() {
        return this.f4004z;
    }

    public ColorStateList getHaloTintList() {
        return this.Q;
    }

    public int getLabelBehavior() {
        return this.f3999u;
    }

    public float getStepSize() {
        return this.K;
    }

    public float getThumbElevation() {
        return this.V.w();
    }

    public int getThumbRadius() {
        return this.f4003y;
    }

    public ColorStateList getThumbTintList() {
        return this.V.x();
    }

    public ColorStateList getTickActiveTintList() {
        return this.R;
    }

    public ColorStateList getTickInactiveTintList() {
        return this.S;
    }

    public ColorStateList getTickTintList() {
        if (this.S.equals(this.R)) {
            return this.R;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    public ColorStateList getTrackActiveTintList() {
        return this.T;
    }

    public int getTrackHeight() {
        return this.f4000v;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.U;
    }

    public int getTrackSidePadding() {
        return this.f4001w;
    }

    public ColorStateList getTrackTintList() {
        if (this.U.equals(this.T)) {
            return this.T;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    public int getTrackWidth() {
        return this.M;
    }

    public float getValueFrom() {
        return this.F;
    }

    public float getValueTo() {
        return this.G;
    }

    public List<Float> getValues() {
        return new ArrayList(this.H);
    }

    public final void h(j6.a aVar) {
        aVar.x0(p.d(this));
    }

    public final Float i(int i8) {
        float fK = this.O ? k(20) : j();
        if (i8 == 21) {
            if (!C()) {
                fK = -fK;
            }
            return Float.valueOf(fK);
        }
        if (i8 == 22) {
            if (C()) {
                fK = -fK;
            }
            return Float.valueOf(fK);
        }
        if (i8 == 69) {
            return Float.valueOf(-fK);
        }
        if (i8 == 70 || i8 == 81) {
            return Float.valueOf(fK);
        }
        return null;
    }

    public final float j() {
        float f8 = this.K;
        if (f8 == 0.0f) {
            return 1.0f;
        }
        return f8;
    }

    public final float k(int i8) {
        float fJ = j();
        return (this.G - this.F) / fJ <= i8 ? fJ : Math.round(r1 / r4) * fJ;
    }

    public final void l() {
        Y();
        int iMin = Math.min((int) (((this.G - this.F) / this.K) + 1.0f), (this.M / (this.f4000v * 2)) + 1);
        float[] fArr = this.L;
        if (fArr == null || fArr.length != iMin * 2) {
            this.L = new float[iMin * 2];
        }
        float f8 = this.M / (iMin - 1);
        for (int i8 = 0; i8 < iMin * 2; i8 += 2) {
            float[] fArr2 = this.L;
            fArr2[i8] = this.f4001w + ((i8 / 2) * f8);
            fArr2[i8 + 1] = m();
        }
    }

    public final int m() {
        return this.f4002x + (this.f3999u == 1 ? this.f3994p.get(0).getIntrinsicHeight() : 0);
    }

    public final void n() {
        if (this.f3994p.size() > this.H.size()) {
            List<j6.a> listSubList = this.f3994p.subList(this.H.size(), this.f3994p.size());
            for (j6.a aVar : listSubList) {
                if (t.P(this)) {
                    o(aVar);
                }
            }
            listSubList.clear();
        }
        while (this.f3994p.size() < this.H.size()) {
            j6.a aVarA = this.f3993o.a();
            this.f3994p.add(aVarA);
            if (t.P(this)) {
                h(aVarA);
            }
        }
        int i8 = this.f3994p.size() == 1 ? 0 : 1;
        Iterator<j6.a> it = this.f3994p.iterator();
        while (it.hasNext()) {
            it.next().j0(i8);
        }
    }

    public final void o(j6.a aVar) {
        o oVarE = p.e(this);
        if (oVarE != null) {
            oVarE.b(aVar);
            aVar.t0(p.d(this));
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Iterator<j6.a> it = this.f3994p.iterator();
        while (it.hasNext()) {
            h(it.next());
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        BaseSlider<S, L, T>.b bVar = this.f3992n;
        if (bVar != null) {
            removeCallbacks(bVar);
        }
        Iterator<j6.a> it = this.f3994p.iterator();
        while (it.hasNext()) {
            o(it.next());
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.P) {
            Y();
            if (this.K > 0.0f) {
                l();
            }
        }
        super.onDraw(canvas);
        int iM = m();
        s(canvas, this.M, iM);
        if (((Float) Collections.max(getValues())).floatValue() > this.F) {
            r(canvas, this.M, iM);
        }
        if (this.K > 0.0f) {
            u(canvas);
        }
        if ((this.E || isFocused()) && isEnabled()) {
            E(canvas, this.M, iM);
            if (this.I != -1) {
                v();
            }
        }
        t(canvas, this.M, iM);
    }

    @Override // android.view.View
    public void onFocusChanged(boolean z7, int i8, Rect rect) {
        super.onFocusChanged(z7, i8, rect);
        if (z7) {
            w(i8);
            this.f3990l.V(this.J);
            return;
        }
        this.I = -1;
        Iterator<j6.a> it = this.f3994p.iterator();
        while (it.hasNext()) {
            p.e(this).b(it.next());
        }
        this.f3990l.o(this.J);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i8, KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i8, keyEvent);
        }
        if (this.H.size() == 1) {
            this.I = 0;
        }
        if (this.I == -1) {
            Boolean boolI = I(i8, keyEvent);
            return boolI != null ? boolI.booleanValue() : super.onKeyDown(i8, keyEvent);
        }
        this.O |= keyEvent.isLongPress();
        Float fI = i(i8);
        if (fI != null) {
            if (S(this.H.get(this.I).floatValue() + fI.floatValue())) {
                X();
                postInvalidate();
            }
            return true;
        }
        if (i8 != 23) {
            if (i8 == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return F(1);
                }
                if (keyEvent.isShiftPressed()) {
                    return F(-1);
                }
                return false;
            }
            if (i8 != 66) {
                return super.onKeyDown(i8, keyEvent);
            }
        }
        this.I = -1;
        Iterator<j6.a> it = this.f3994p.iterator();
        while (it.hasNext()) {
            p.e(this).b(it.next());
        }
        postInvalidate();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i8, KeyEvent keyEvent) {
        this.O = false;
        return super.onKeyUp(i8, keyEvent);
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, View.MeasureSpec.makeMeasureSpec(this.f3998t + (this.f3999u == 1 ? this.f3994p.get(0).getIntrinsicHeight() : 0), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.F = sliderState.f4005d;
        this.G = sliderState.f4006e;
        setValuesInternal(sliderState.f4007f);
        this.K = sliderState.f4008g;
        if (sliderState.f4009h) {
            requestFocus();
        }
        q();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.f4005d = this.F;
        sliderState.f4006e = this.G;
        sliderState.f4007f = new ArrayList<>(this.H);
        sliderState.f4008g = this.K;
        sliderState.f4009h = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i8, int i9, int i10, int i11) {
        this.M = Math.max(i8 - (this.f4001w * 2), 0);
        if (this.K > 0.0f) {
            l();
        }
        X();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float x7 = motionEvent.getX();
        float f8 = (x7 - this.f4001w) / this.M;
        this.W = f8;
        float fMax = Math.max(0.0f, f8);
        this.W = fMax;
        this.W = Math.min(1.0f, fMax);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.B = x7;
            if (!isInScrollingContainer()) {
                getParent().requestDisallowInterceptTouchEvent(true);
                if (M()) {
                    requestFocus();
                    this.E = true;
                    V();
                    X();
                    invalidate();
                    J();
                }
            }
        } else if (actionMasked == 1) {
            this.E = false;
            MotionEvent motionEvent2 = this.C;
            if (motionEvent2 != null && motionEvent2.getActionMasked() == 0 && Math.abs(this.C.getX() - motionEvent.getX()) <= this.f3997s && Math.abs(this.C.getY() - motionEvent.getY()) <= this.f3997s) {
                M();
            }
            if (this.I != -1) {
                V();
                this.I = -1;
            }
            Iterator<j6.a> it = this.f3994p.iterator();
            while (it.hasNext()) {
                p.e(this).b(it.next());
            }
            K();
            invalidate();
        } else if (actionMasked == 2) {
            if (!this.E) {
                if (Math.abs(x7 - this.B) < this.f3997s) {
                    return false;
                }
                getParent().requestDisallowInterceptTouchEvent(true);
                J();
            }
            if (M()) {
                this.E = true;
                V();
                X();
                invalidate();
            }
        }
        setPressed(this.E);
        this.C = MotionEvent.obtain(motionEvent);
        return true;
    }

    public final void p(int i8) {
        Iterator<L> it = this.f3995q.iterator();
        while (it.hasNext()) {
            it.next().a(this, this.H.get(i8).floatValue(), true);
        }
        AccessibilityManager accessibilityManager = this.f3991m;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        P(i8);
    }

    public final void q() {
        for (L l8 : this.f3995q) {
            Iterator<Float> it = this.H.iterator();
            while (it.hasNext()) {
                l8.a(this, it.next().floatValue(), false);
            }
        }
    }

    public final void r(Canvas canvas, int i8, int i9) {
        float[] activeRange = getActiveRange();
        int i10 = this.f4001w;
        float f8 = i8;
        float f9 = i9;
        canvas.drawLine(i10 + (activeRange[0] * f8), f9, i10 + (activeRange[1] * f8), f9, this.f3985g);
    }

    public final void s(Canvas canvas, int i8, int i9) {
        float[] activeRange = getActiveRange();
        float f8 = i8;
        float f9 = this.f4001w + (activeRange[1] * f8);
        if (f9 < r1 + i8) {
            float f10 = i9;
            canvas.drawLine(f9, f10, r1 + i8, f10, this.f3984f);
        }
        int i10 = this.f4001w;
        float f11 = i10 + (activeRange[0] * f8);
        if (f11 > i10) {
            float f12 = i9;
            canvas.drawLine(i10, f12, f11, f12, this.f3984f);
        }
    }

    public void setActiveThumbIndex(int i8) {
        this.I = i8;
    }

    @Override // android.view.View
    public void setEnabled(boolean z7) {
        super.setEnabled(z7);
        setLayerType(z7 ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i8) {
        if (i8 < 0 || i8 >= this.H.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.J = i8;
        this.f3990l.V(i8);
        postInvalidate();
    }

    public void setHaloRadius(int i8) {
        if (i8 == this.f4004z) {
            return;
        }
        this.f4004z = i8;
        Drawable background = getBackground();
        if (R() || !(background instanceof RippleDrawable)) {
            postInvalidate();
        } else {
            v5.a.a((RippleDrawable) background, this.f4004z);
        }
    }

    public void setHaloRadiusResource(int i8) {
        setHaloRadius(getResources().getDimensionPixelSize(i8));
    }

    public void setHaloTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.Q)) {
            return;
        }
        this.Q = colorStateList;
        Drawable background = getBackground();
        if (!R() && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        this.f3987i.setColor(z(colorStateList));
        this.f3987i.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i8) {
        if (this.f3999u != i8) {
            this.f3999u = i8;
            requestLayout();
        }
    }

    public void setLabelFormatter(f6.c cVar) {
        this.D = cVar;
    }

    public void setStepSize(float f8) {
        if (f8 < 0.0f) {
            throw new IllegalArgumentException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.toString(f8), Float.toString(this.F), Float.toString(this.G)));
        }
        if (this.K != f8) {
            this.K = f8;
            this.P = true;
            postInvalidate();
        }
    }

    public void setThumbElevation(float f8) {
        this.V.W(f8);
    }

    public void setThumbElevationResource(int i8) {
        setThumbElevation(getResources().getDimension(i8));
    }

    public void setThumbRadius(int i8) {
        if (i8 == this.f4003y) {
            return;
        }
        this.f4003y = i8;
        this.V.setShapeAppearanceModel(m.a().p(0, this.f4003y).m());
        h hVar = this.V;
        int i9 = this.f4003y;
        hVar.setBounds(0, 0, i9 * 2, i9 * 2);
        postInvalidate();
    }

    public void setThumbRadiusResource(int i8) {
        setThumbRadius(getResources().getDimensionPixelSize(i8));
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.V.X(colorStateList);
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.R)) {
            return;
        }
        this.R = colorStateList;
        this.f3989k.setColor(z(colorStateList));
        invalidate();
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.S)) {
            return;
        }
        this.S = colorStateList;
        this.f3988j.setColor(z(colorStateList));
        invalidate();
    }

    public void setTickTintList(ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public void setTrackActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.T)) {
            return;
        }
        this.T = colorStateList;
        this.f3985g.setColor(z(colorStateList));
        invalidate();
    }

    public void setTrackHeight(int i8) {
        if (this.f4000v != i8) {
            this.f4000v = i8;
            B();
            postInvalidate();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.U)) {
            return;
        }
        this.U = colorStateList;
        this.f3984f.setColor(z(colorStateList));
        invalidate();
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f8) {
        this.F = f8;
        this.P = true;
        postInvalidate();
    }

    public void setValueTo(float f8) {
        this.G = f8;
        this.P = true;
        postInvalidate();
    }

    public void setValues(Float... fArr) {
        ArrayList<Float> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    public final void t(Canvas canvas, int i8, int i9) {
        if (!isEnabled()) {
            Iterator<Float> it = this.H.iterator();
            while (it.hasNext()) {
                canvas.drawCircle(this.f4001w + (H(it.next().floatValue()) * i8), i9, this.f4003y, this.f3986h);
            }
        }
        for (Float f8 : this.H) {
            canvas.save();
            int iH = this.f4001w + ((int) (H(f8.floatValue()) * i8));
            int i10 = this.f4003y;
            canvas.translate(iH - i10, i9 - i10);
            this.V.draw(canvas);
            canvas.restore();
        }
    }

    public final void u(Canvas canvas) {
        float[] activeRange = getActiveRange();
        int iN = N(this.L, activeRange[0]);
        int iN2 = N(this.L, activeRange[1]);
        int i8 = iN * 2;
        canvas.drawPoints(this.L, 0, i8, this.f3988j);
        int i9 = iN2 * 2;
        canvas.drawPoints(this.L, i8, i9 - i8, this.f3989k);
        float[] fArr = this.L;
        canvas.drawPoints(fArr, i9, fArr.length - i9, this.f3988j);
    }

    public final void v() {
        if (this.f3999u == 2) {
            return;
        }
        Iterator<j6.a> it = this.f3994p.iterator();
        for (int i8 = 0; i8 < this.H.size() && it.hasNext(); i8++) {
            if (i8 != this.J) {
                Q(it.next(), this.H.get(i8).floatValue());
            }
        }
        if (!it.hasNext()) {
            throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(this.f3994p.size()), Integer.valueOf(this.H.size())));
        }
        Q(it.next(), this.H.get(this.J).floatValue());
    }

    public final void w(int i8) {
        if (i8 == 1) {
            F(Integer.MAX_VALUE);
            return;
        }
        if (i8 == 2) {
            F(Integer.MIN_VALUE);
        } else if (i8 == 17) {
            G(Integer.MAX_VALUE);
        } else {
            if (i8 != 66) {
                return;
            }
            G(Integer.MIN_VALUE);
        }
    }

    public final String x(float f8) {
        if (A()) {
            return this.D.a(f8);
        }
        return String.format(((float) ((int) f8)) == f8 ? "%.0f" : "%.2f", Float.valueOf(f8));
    }

    public final float y(int i8, float f8) {
        int i9 = i8 + 1;
        int i10 = i8 - 1;
        return j0.a.a(f8, i10 < 0 ? this.F : this.H.get(i10).floatValue(), i9 >= this.H.size() ? this.G : this.H.get(i9).floatValue());
    }

    public final int z(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4013d;

        public b() {
            this.f4013d = -1;
        }

        public void a(int i8) {
            this.f4013d = i8;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.this.f3990l.W(this.f4013d, 4);
        }

        public /* synthetic */ b(BaseSlider baseSlider, a aVar) {
            this();
        }
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.sliderStyle);
    }

    public BaseSlider(Context context, AttributeSet attributeSet, int i8) {
        super(i6.a.c(context, attributeSet, i8, f3983e), attributeSet, i8);
        this.f3994p = new ArrayList();
        this.f3995q = new ArrayList();
        this.f3996r = new ArrayList();
        this.E = false;
        this.H = new ArrayList<>();
        this.I = -1;
        this.J = -1;
        this.K = 0.0f;
        this.O = false;
        h hVar = new h();
        this.V = hVar;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.f3984f = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.f3985g = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint(1);
        this.f3986h = paint3;
        paint3.setStyle(Paint.Style.FILL);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.f3987i = paint4;
        paint4.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.f3988j = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        Paint paint6 = new Paint();
        this.f3989k = paint6;
        paint6.setStyle(Paint.Style.STROKE);
        paint6.setStrokeCap(Paint.Cap.ROUND);
        D(context2.getResources());
        this.f3993o = new a(attributeSet, i8);
        O(context2, attributeSet, i8);
        setFocusable(true);
        setClickable(true);
        hVar.f0(2);
        this.f3997s = ViewConfiguration.get(context2).getScaledTouchSlop();
        c cVar = new c(this);
        this.f3990l = cVar;
        t.l0(this, cVar);
        this.f3991m = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public void setValues(List<Float> list) {
        setValuesInternal(new ArrayList<>(list));
    }
}
