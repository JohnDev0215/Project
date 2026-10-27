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
import i2.k;
import i2.o;
import i2.p;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import l0.t;
import m0.c;
import n2.h;
import n2.m;
import o2.a;
import o2.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends o2.a<S>, T extends o2.b<S>> extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2401b = BaseSlider.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f2402c = R$style.Widget_MaterialComponents_Slider;
    public MotionEvent A;
    public o2.c B;
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
    public final Paint f2403d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f2404e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f2405f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f2406g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f2407h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Paint f2408i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final c f2409j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AccessibilityManager f2410k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public BaseSlider<S, L, T>.b f2411l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final d f2412m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final List<s2.a> f2413n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final List<L> f2414o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final List<T> f2415p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f2416q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f2417r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f2418s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f2419t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f2420u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f2421v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f2422w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2423x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f2424y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f2425z;

    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2426b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2427c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ArrayList<Float> f2428d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f2429e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f2430f;

        public static class a implements Parcelable.Creator<SliderState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SliderState createFromParcel(Parcel parcel) {
                return new SliderState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SliderState[] newArray(int i3) {
                return new SliderState[i3];
            }
        }

        public /* synthetic */ SliderState(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeFloat(this.f2426b);
            parcel.writeFloat(this.f2427c);
            parcel.writeList(this.f2428d);
            parcel.writeFloat(this.f2429e);
            parcel.writeBooleanArray(new boolean[]{this.f2430f});
        }

        public SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        public SliderState(Parcel parcel) {
            super(parcel);
            this.f2426b = parcel.readFloat();
            this.f2427c = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.f2428d = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.f2429e = parcel.readFloat();
            this.f2430f = parcel.createBooleanArray()[0];
        }
    }

    public class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AttributeSet f2431a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2432b;

        public a(AttributeSet attributeSet, int i3) {
            this.f2431a = attributeSet;
            this.f2432b = i3;
        }

        @Override // com.google.android.material.slider.BaseSlider.d
        public s2.a a() {
            TypedArray typedArrayH = k.h(BaseSlider.this.getContext(), this.f2431a, R$styleable.Slider, this.f2432b, BaseSlider.f2402c, new int[0]);
            s2.a aVarL = BaseSlider.L(BaseSlider.this.getContext(), typedArrayH);
            typedArrayH.recycle();
            return aVarL;
        }
    }

    public static class c extends p0.a {

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public final BaseSlider<?, ?, ?> f2436q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public Rect f2437r;

        public c(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.f2437r = new Rect();
            this.f2436q = baseSlider;
        }

        @Override // p0.a
        public int B(float f3, float f4) {
            for (int i3 = 0; i3 < this.f2436q.getValues().size(); i3++) {
                this.f2436q.W(i3, this.f2437r);
                if (this.f2437r.contains((int) f3, (int) f4)) {
                    return i3;
                }
            }
            return -1;
        }

        @Override // p0.a
        public void C(List<Integer> list) {
            for (int i3 = 0; i3 < this.f2436q.getValues().size(); i3++) {
                list.add(Integer.valueOf(i3));
            }
        }

        @Override // p0.a
        public boolean L(int i3, int i4, Bundle bundle) {
            if (!this.f2436q.isEnabled()) {
                return false;
            }
            if (i4 != 4096 && i4 != 8192) {
                if (i4 == 16908349 && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                    if (this.f2436q.U(i3, bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"))) {
                        this.f2436q.X();
                        this.f2436q.postInvalidate();
                        E(i3);
                        return true;
                    }
                }
                return false;
            }
            float fK = this.f2436q.k(20);
            if (i4 == 8192) {
                fK = -fK;
            }
            if (this.f2436q.C()) {
                fK = -fK;
            }
            if (!this.f2436q.U(i3, g0.a.a(this.f2436q.getValues().get(i3).floatValue() + fK, this.f2436q.getValueFrom(), this.f2436q.getValueTo()))) {
                return false;
            }
            this.f2436q.X();
            this.f2436q.postInvalidate();
            E(i3);
            return true;
        }

        @Override // p0.a
        public void P(int i3, m0.c cVar) {
            cVar.b(c.a.H);
            List<Float> values = this.f2436q.getValues();
            float fFloatValue = values.get(i3).floatValue();
            float valueFrom = this.f2436q.getValueFrom();
            float valueTo = this.f2436q.getValueTo();
            if (this.f2436q.isEnabled()) {
                if (fFloatValue > valueFrom) {
                    cVar.a(8192);
                }
                if (fFloatValue < valueTo) {
                    cVar.a(4096);
                }
            }
            cVar.s0(c.d.a(1, valueFrom, valueTo, fFloatValue));
            cVar.b0(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.f2436q.getContentDescription() != null) {
                sb.append(this.f2436q.getContentDescription());
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            }
            if (values.size() > 1) {
                sb.append(Y(i3));
                sb.append(this.f2436q.x(fFloatValue));
            }
            cVar.f0(sb.toString());
            this.f2436q.W(i3, this.f2437r);
            cVar.W(this.f2437r);
        }

        public final String Y(int i3) {
            return i3 == this.f2436q.getValues().size() + (-1) ? this.f2436q.getContext().getString(R$string.material_slider_range_end) : i3 == 0 ? this.f2436q.getContext().getString(R$string.material_slider_range_start) : "";
        }
    }

    public interface d {
        s2.a a();
    }

    public BaseSlider(Context context) {
        this(context, null);
    }

    public static s2.a L(Context context, TypedArray typedArray) {
        return s2.a.r0(context, null, 0, typedArray.getResourceId(R$styleable.Slider_labelStyle, R$style.Widget_MaterialComponents_Tooltip));
    }

    public static int N(float[] fArr, float f3) {
        return Math.round(f3 * ((fArr.length / 2) - 1));
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
        float f3 = this.E;
        float f4 = this.D;
        return (float) ((dT * ((double) (f3 - f4))) + ((double) f4));
    }

    private float getValueOfTouchPositionAbsolute() {
        float f3 = this.U;
        if (C()) {
            f3 = 1.0f - f3;
        }
        float f4 = this.E;
        float f5 = this.D;
        return (f3 * (f4 - f5)) + f5;
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
        this.f2403d.setStrokeWidth(this.f2419t);
        this.f2404e.setStrokeWidth(this.f2419t);
        this.f2407h.setStrokeWidth(this.f2419t / 2.0f);
        this.f2408i.setStrokeWidth(this.f2419t / 2.0f);
    }

    public final boolean C() {
        return t.z(this) == 1;
    }

    public final void D(Resources resources) {
        this.f2417r = resources.getDimensionPixelSize(R$dimen.mtrl_slider_widget_height);
        this.f2420u = resources.getDimensionPixelOffset(R$dimen.mtrl_slider_track_side_padding);
        this.f2421v = resources.getDimensionPixelOffset(R$dimen.mtrl_slider_track_top);
        this.f2424y = resources.getDimensionPixelSize(R$dimen.mtrl_slider_label_padding);
    }

    public final void E(Canvas canvas, int i3, int i4) {
        if (R()) {
            int iH = (int) (this.f2420u + (H(this.F.get(this.H).floatValue()) * i3));
            if (Build.VERSION.SDK_INT < 28) {
                int i5 = this.f2423x;
                canvas.clipRect(iH - i5, i4 - i5, iH + i5, i5 + i4, Region.Op.UNION);
            }
            canvas.drawCircle(iH, i4, this.f2423x, this.f2406g);
        }
    }

    public final boolean F(int i3) {
        int i4 = this.H;
        int iC = (int) g0.a.c(((long) i4) + ((long) i3), 0L, this.F.size() - 1);
        this.H = iC;
        if (iC == i4) {
            return false;
        }
        if (this.G != -1) {
            this.G = iC;
        }
        X();
        postInvalidate();
        return true;
    }

    public final boolean G(int i3) {
        if (C()) {
            i3 = i3 == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i3;
        }
        return F(i3);
    }

    public final float H(float f3) {
        float f4 = this.D;
        float f5 = (f3 - f4) / (this.E - f4);
        return C() ? 1.0f - f5 : f5;
    }

    public final Boolean I(int i3, KeyEvent keyEvent) {
        if (i3 == 61) {
            return keyEvent.hasNoModifiers() ? Boolean.valueOf(F(1)) : keyEvent.isShiftPressed() ? Boolean.valueOf(F(-1)) : Boolean.FALSE;
        }
        if (i3 != 66) {
            if (i3 != 81) {
                if (i3 == 69) {
                    F(-1);
                    return Boolean.TRUE;
                }
                if (i3 != 70) {
                    switch (i3) {
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
        Iterator<T> it = this.f2415p.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    public final void K() {
        Iterator<T> it = this.f2415p.iterator();
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
        for (int i3 = 1; i3 < this.F.size(); i3++) {
            float fAbs2 = Math.abs(this.F.get(i3).floatValue() - valueOfTouchPositionAbsolute);
            float fE02 = e0(this.F.get(i3).floatValue());
            if (Float.compare(fAbs2, fAbs) > 1) {
                break;
            }
            boolean z3 = !C() ? fE02 - fE0 >= 0.0f : fE02 - fE0 <= 0.0f;
            if (Float.compare(fAbs2, fAbs) < 0) {
                this.G = i3;
            } else {
                if (Float.compare(fAbs2, fAbs) != 0) {
                    continue;
                } else {
                    if (Math.abs(fE02 - fE0) < this.f2416q) {
                        this.G = -1;
                        return false;
                    }
                    if (z3) {
                        this.G = i3;
                    }
                }
            }
            fAbs = fAbs2;
        }
        return this.G != -1;
    }

    public final void O(Context context, AttributeSet attributeSet, int i3) {
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.Slider, i3, f2402c, new int[0]);
        this.D = typedArrayH.getFloat(R$styleable.Slider_android_valueFrom, 0.0f);
        this.E = typedArrayH.getFloat(R$styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.D));
        this.I = typedArrayH.getFloat(R$styleable.Slider_android_stepSize, 0.0f);
        int i4 = R$styleable.Slider_trackColor;
        boolean zHasValue = typedArrayH.hasValue(i4);
        int i5 = zHasValue ? i4 : R$styleable.Slider_trackColorInactive;
        if (!zHasValue) {
            i4 = R$styleable.Slider_trackColorActive;
        }
        ColorStateList colorStateListA = k2.c.a(context, typedArrayH, i5);
        if (colorStateListA == null) {
            colorStateListA = j.a.c(context, R$color.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(colorStateListA);
        ColorStateList colorStateListA2 = k2.c.a(context, typedArrayH, i4);
        if (colorStateListA2 == null) {
            colorStateListA2 = j.a.c(context, R$color.material_slider_active_track_color);
        }
        setTrackActiveTintList(colorStateListA2);
        this.T.X(k2.c.a(context, typedArrayH, R$styleable.Slider_thumbColor));
        ColorStateList colorStateListA3 = k2.c.a(context, typedArrayH, R$styleable.Slider_haloColor);
        if (colorStateListA3 == null) {
            colorStateListA3 = j.a.c(context, R$color.material_slider_halo_color);
        }
        setHaloTintList(colorStateListA3);
        int i6 = R$styleable.Slider_tickColor;
        boolean zHasValue2 = typedArrayH.hasValue(i6);
        int i7 = zHasValue2 ? i6 : R$styleable.Slider_tickColorInactive;
        if (!zHasValue2) {
            i6 = R$styleable.Slider_tickColorActive;
        }
        ColorStateList colorStateListA4 = k2.c.a(context, typedArrayH, i7);
        if (colorStateListA4 == null) {
            colorStateListA4 = j.a.c(context, R$color.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(colorStateListA4);
        ColorStateList colorStateListA5 = k2.c.a(context, typedArrayH, i6);
        if (colorStateListA5 == null) {
            colorStateListA5 = j.a.c(context, R$color.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(colorStateListA5);
        setThumbRadius(typedArrayH.getDimensionPixelSize(R$styleable.Slider_thumbRadius, 0));
        setHaloRadius(typedArrayH.getDimensionPixelSize(R$styleable.Slider_haloRadius, 0));
        setThumbElevation(typedArrayH.getDimension(R$styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(typedArrayH.getDimensionPixelSize(R$styleable.Slider_trackHeight, 0));
        this.f2418s = typedArrayH.getInt(R$styleable.Slider_labelBehavior, 0);
        if (!typedArrayH.getBoolean(R$styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        typedArrayH.recycle();
    }

    public final void P(int i3) {
        BaseSlider<S, L, T>.b bVar = this.f2411l;
        if (bVar == null) {
            this.f2411l = new b(this, null);
        } else {
            removeCallbacks(bVar);
        }
        this.f2411l.a(i3);
        postDelayed(this.f2411l, 200L);
    }

    public final void Q(s2.a aVar, float f3) {
        aVar.y0(x(f3));
        int iH = (this.f2420u + ((int) (H(f3) * this.K))) - (aVar.getIntrinsicWidth() / 2);
        int iM = m() - (this.f2424y + this.f2422w);
        aVar.setBounds(iH, iM - aVar.getIntrinsicHeight(), aVar.getIntrinsicWidth() + iH, iM);
        Rect rect = new Rect(aVar.getBounds());
        i2.c.c(p.d(this), this, rect);
        aVar.setBounds(rect);
        p.e(this).a(aVar);
    }

    public final boolean R() {
        return this.L || Build.VERSION.SDK_INT < 21 || !(getBackground() instanceof RippleDrawable);
    }

    public final boolean S(float f3) {
        return U(this.G, f3);
    }

    public final double T(float f3) {
        float f4 = this.I;
        if (f4 <= 0.0f) {
            return f3;
        }
        int i3 = (int) ((this.E - this.D) / f4);
        return ((double) Math.round(f3 * i3)) / ((double) i3);
    }

    public final boolean U(int i3, float f3) {
        if (Math.abs(f3 - this.F.get(i3).floatValue()) < 1.0E-4d) {
            return false;
        }
        this.F.set(i3, Float.valueOf(y(i3, f3)));
        this.H = i3;
        p(i3);
        return true;
    }

    public final boolean V() {
        return S(getValueOfTouchPosition());
    }

    public void W(int i3, Rect rect) {
        int iH = this.f2420u + ((int) (H(getValues().get(i3).floatValue()) * this.K));
        int iM = m();
        int i4 = this.f2422w;
        rect.set(iH - i4, iM - i4, iH + i4, iM + i4);
    }

    public final void X() {
        if (R() || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int iH = (int) ((H(this.F.get(this.H).floatValue()) * this.K) + this.f2420u);
            int iM = m();
            int i3 = this.f2423x;
            e0.a.l(background, iH - i3, iM - i3, iH + i3, iM + i3);
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
        for (Float f3 : this.F) {
            if (f3.floatValue() < this.D || f3.floatValue() > this.E) {
                throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", Float.toString(f3.floatValue()), Float.toString(this.D), Float.toString(this.E)));
            }
            if (this.I > 0.0f && !d0(f3.floatValue())) {
                throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", Float.toString(f3.floatValue()), Float.toString(this.D), Float.toString(this.I), Float.toString(this.I)));
            }
        }
    }

    public final boolean d0(float f3) {
        double dDoubleValue = new BigDecimal(Float.toString(f3)).subtract(new BigDecimal(Float.toString(this.D))).divide(new BigDecimal(Float.toString(this.I)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(dDoubleValue)) - dDoubleValue) < 1.0E-4d;
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.f2409j.v(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.f2403d.setColor(z(this.S));
        this.f2404e.setColor(z(this.R));
        this.f2407h.setColor(z(this.Q));
        this.f2408i.setColor(z(this.P));
        for (s2.a aVar : this.f2413n) {
            if (aVar.isStateful()) {
                aVar.setState(getDrawableState());
            }
        }
        if (this.T.isStateful()) {
            this.T.setState(getDrawableState());
        }
        this.f2406g.setColor(z(this.O));
        this.f2406g.setAlpha(63);
    }

    public final float e0(float f3) {
        return (H(f3) * this.K) + this.f2420u;
    }

    public final void f0() {
        float f3 = this.I;
        if (f3 == 0.0f) {
            return;
        }
        if (((int) f3) != f3) {
            Log.w(f2401b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "stepSize", Float.valueOf(f3)));
        }
        float f4 = this.D;
        if (((int) f4) != f4) {
            Log.w(f2401b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueFrom", Float.valueOf(f4)));
        }
        float f5 = this.E;
        if (((int) f5) != f5) {
            Log.w(f2401b, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueTo", Float.valueOf(f5)));
        }
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.f2409j.x();
    }

    public int getActiveThumbIndex() {
        return this.G;
    }

    public int getFocusedThumbIndex() {
        return this.H;
    }

    public int getHaloRadius() {
        return this.f2423x;
    }

    public ColorStateList getHaloTintList() {
        return this.O;
    }

    public int getLabelBehavior() {
        return this.f2418s;
    }

    public float getStepSize() {
        return this.I;
    }

    public float getThumbElevation() {
        return this.T.w();
    }

    public int getThumbRadius() {
        return this.f2422w;
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
        return this.f2419t;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.S;
    }

    public int getTrackSidePadding() {
        return this.f2420u;
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

    public final void h(s2.a aVar) {
        aVar.x0(p.d(this));
    }

    public final Float i(int i3) {
        float fK = this.M ? k(20) : j();
        if (i3 == 21) {
            if (!C()) {
                fK = -fK;
            }
            return Float.valueOf(fK);
        }
        if (i3 == 22) {
            if (C()) {
                fK = -fK;
            }
            return Float.valueOf(fK);
        }
        if (i3 == 69) {
            return Float.valueOf(-fK);
        }
        if (i3 == 70 || i3 == 81) {
            return Float.valueOf(fK);
        }
        return null;
    }

    public final float j() {
        float f3 = this.I;
        if (f3 == 0.0f) {
            return 1.0f;
        }
        return f3;
    }

    public final float k(int i3) {
        float fJ = j();
        return (this.E - this.D) / fJ <= i3 ? fJ : Math.round(r1 / r4) * fJ;
    }

    public final void l() {
        Y();
        int iMin = Math.min((int) (((this.E - this.D) / this.I) + 1.0f), (this.K / (this.f2419t * 2)) + 1);
        float[] fArr = this.J;
        if (fArr == null || fArr.length != iMin * 2) {
            this.J = new float[iMin * 2];
        }
        float f3 = this.K / (iMin - 1);
        for (int i3 = 0; i3 < iMin * 2; i3 += 2) {
            float[] fArr2 = this.J;
            fArr2[i3] = this.f2420u + ((i3 / 2) * f3);
            fArr2[i3 + 1] = m();
        }
    }

    public final int m() {
        return this.f2421v + (this.f2418s == 1 ? this.f2413n.get(0).getIntrinsicHeight() : 0);
    }

    public final void n() {
        if (this.f2413n.size() > this.F.size()) {
            List<s2.a> listSubList = this.f2413n.subList(this.F.size(), this.f2413n.size());
            for (s2.a aVar : listSubList) {
                if (t.O(this)) {
                    o(aVar);
                }
            }
            listSubList.clear();
        }
        while (this.f2413n.size() < this.F.size()) {
            s2.a aVarA = this.f2412m.a();
            this.f2413n.add(aVarA);
            if (t.O(this)) {
                h(aVarA);
            }
        }
        int i3 = this.f2413n.size() == 1 ? 0 : 1;
        Iterator<s2.a> it = this.f2413n.iterator();
        while (it.hasNext()) {
            it.next().j0(i3);
        }
    }

    public final void o(s2.a aVar) {
        o oVarE = p.e(this);
        if (oVarE != null) {
            oVarE.b(aVar);
            aVar.t0(p.d(this));
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Iterator<s2.a> it = this.f2413n.iterator();
        while (it.hasNext()) {
            h(it.next());
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        BaseSlider<S, L, T>.b bVar = this.f2411l;
        if (bVar != null) {
            removeCallbacks(bVar);
        }
        Iterator<s2.a> it = this.f2413n.iterator();
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
    public void onFocusChanged(boolean z3, int i3, Rect rect) {
        super.onFocusChanged(z3, i3, rect);
        if (z3) {
            w(i3);
            this.f2409j.V(this.H);
            return;
        }
        this.G = -1;
        Iterator<s2.a> it = this.f2413n.iterator();
        while (it.hasNext()) {
            p.e(this).b(it.next());
        }
        this.f2409j.o(this.H);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i3, KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i3, keyEvent);
        }
        if (this.F.size() == 1) {
            this.G = 0;
        }
        if (this.G == -1) {
            Boolean boolI = I(i3, keyEvent);
            return boolI != null ? boolI.booleanValue() : super.onKeyDown(i3, keyEvent);
        }
        this.M |= keyEvent.isLongPress();
        Float fI = i(i3);
        if (fI != null) {
            if (S(this.F.get(this.G).floatValue() + fI.floatValue())) {
                X();
                postInvalidate();
            }
            return true;
        }
        if (i3 != 23) {
            if (i3 == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return F(1);
                }
                if (keyEvent.isShiftPressed()) {
                    return F(-1);
                }
                return false;
            }
            if (i3 != 66) {
                return super.onKeyDown(i3, keyEvent);
            }
        }
        this.G = -1;
        Iterator<s2.a> it = this.f2413n.iterator();
        while (it.hasNext()) {
            p.e(this).b(it.next());
        }
        postInvalidate();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i3, KeyEvent keyEvent) {
        this.M = false;
        return super.onKeyUp(i3, keyEvent);
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        super.onMeasure(i3, View.MeasureSpec.makeMeasureSpec(this.f2417r + (this.f2418s == 1 ? this.f2413n.get(0).getIntrinsicHeight() : 0), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.D = sliderState.f2426b;
        this.E = sliderState.f2427c;
        setValuesInternal(sliderState.f2428d);
        this.I = sliderState.f2429e;
        if (sliderState.f2430f) {
            requestFocus();
        }
        q();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.f2426b = this.D;
        sliderState.f2427c = this.E;
        sliderState.f2428d = new ArrayList<>(this.F);
        sliderState.f2429e = this.I;
        sliderState.f2430f = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        this.K = Math.max(i3 - (this.f2420u * 2), 0);
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
        float x3 = motionEvent.getX();
        float f3 = (x3 - this.f2420u) / this.K;
        this.U = f3;
        float fMax = Math.max(0.0f, f3);
        this.U = fMax;
        this.U = Math.min(1.0f, fMax);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f2425z = x3;
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
            if (motionEvent2 != null && motionEvent2.getActionMasked() == 0 && Math.abs(this.A.getX() - motionEvent.getX()) <= this.f2416q && Math.abs(this.A.getY() - motionEvent.getY()) <= this.f2416q) {
                M();
            }
            if (this.G != -1) {
                V();
                this.G = -1;
            }
            Iterator<s2.a> it = this.f2413n.iterator();
            while (it.hasNext()) {
                p.e(this).b(it.next());
            }
            K();
            invalidate();
        } else if (actionMasked == 2) {
            if (!this.C) {
                if (Math.abs(x3 - this.f2425z) < this.f2416q) {
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

    public final void p(int i3) {
        Iterator<L> it = this.f2414o.iterator();
        while (it.hasNext()) {
            it.next().a(this, this.F.get(i3).floatValue(), true);
        }
        AccessibilityManager accessibilityManager = this.f2410k;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        P(i3);
    }

    public final void q() {
        for (L l3 : this.f2414o) {
            Iterator<Float> it = this.F.iterator();
            while (it.hasNext()) {
                l3.a(this, it.next().floatValue(), false);
            }
        }
    }

    public final void r(Canvas canvas, int i3, int i4) {
        float[] activeRange = getActiveRange();
        int i5 = this.f2420u;
        float f3 = i3;
        float f4 = i4;
        canvas.drawLine(i5 + (activeRange[0] * f3), f4, i5 + (activeRange[1] * f3), f4, this.f2404e);
    }

    public final void s(Canvas canvas, int i3, int i4) {
        float[] activeRange = getActiveRange();
        float f3 = i3;
        float f4 = this.f2420u + (activeRange[1] * f3);
        if (f4 < r1 + i3) {
            float f5 = i4;
            canvas.drawLine(f4, f5, r1 + i3, f5, this.f2403d);
        }
        int i5 = this.f2420u;
        float f6 = i5 + (activeRange[0] * f3);
        if (f6 > i5) {
            float f7 = i4;
            canvas.drawLine(i5, f7, f6, f7, this.f2403d);
        }
    }

    public void setActiveThumbIndex(int i3) {
        this.G = i3;
    }

    @Override // android.view.View
    public void setEnabled(boolean z3) {
        super.setEnabled(z3);
        setLayerType(z3 ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i3) {
        if (i3 < 0 || i3 >= this.F.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.H = i3;
        this.f2409j.V(i3);
        postInvalidate();
    }

    public void setHaloRadius(int i3) {
        if (i3 == this.f2423x) {
            return;
        }
        this.f2423x = i3;
        Drawable background = getBackground();
        if (R() || !(background instanceof RippleDrawable)) {
            postInvalidate();
        } else {
            e2.a.a((RippleDrawable) background, this.f2423x);
        }
    }

    public void setHaloRadiusResource(int i3) {
        setHaloRadius(getResources().getDimensionPixelSize(i3));
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
        this.f2406g.setColor(z(colorStateList));
        this.f2406g.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i3) {
        if (this.f2418s != i3) {
            this.f2418s = i3;
            requestLayout();
        }
    }

    public void setLabelFormatter(o2.c cVar) {
        this.B = cVar;
    }

    public void setStepSize(float f3) {
        if (f3 < 0.0f) {
            throw new IllegalArgumentException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.toString(f3), Float.toString(this.D), Float.toString(this.E)));
        }
        if (this.I != f3) {
            this.I = f3;
            this.N = true;
            postInvalidate();
        }
    }

    public void setThumbElevation(float f3) {
        this.T.W(f3);
    }

    public void setThumbElevationResource(int i3) {
        setThumbElevation(getResources().getDimension(i3));
    }

    public void setThumbRadius(int i3) {
        if (i3 == this.f2422w) {
            return;
        }
        this.f2422w = i3;
        this.T.setShapeAppearanceModel(m.a().p(0, this.f2422w).m());
        h hVar = this.T;
        int i4 = this.f2422w;
        hVar.setBounds(0, 0, i4 * 2, i4 * 2);
        postInvalidate();
    }

    public void setThumbRadiusResource(int i3) {
        setThumbRadius(getResources().getDimensionPixelSize(i3));
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.T.X(colorStateList);
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.P)) {
            return;
        }
        this.P = colorStateList;
        this.f2408i.setColor(z(colorStateList));
        invalidate();
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.Q)) {
            return;
        }
        this.Q = colorStateList;
        this.f2407h.setColor(z(colorStateList));
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
        this.f2404e.setColor(z(colorStateList));
        invalidate();
    }

    public void setTrackHeight(int i3) {
        if (this.f2419t != i3) {
            this.f2419t = i3;
            B();
            postInvalidate();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.S)) {
            return;
        }
        this.S = colorStateList;
        this.f2403d.setColor(z(colorStateList));
        invalidate();
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f3) {
        this.D = f3;
        this.N = true;
        postInvalidate();
    }

    public void setValueTo(float f3) {
        this.E = f3;
        this.N = true;
        postInvalidate();
    }

    public void setValues(Float... fArr) {
        ArrayList<Float> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    public final void t(Canvas canvas, int i3, int i4) {
        if (!isEnabled()) {
            Iterator<Float> it = this.F.iterator();
            while (it.hasNext()) {
                canvas.drawCircle(this.f2420u + (H(it.next().floatValue()) * i3), i4, this.f2422w, this.f2405f);
            }
        }
        for (Float f3 : this.F) {
            canvas.save();
            int iH = this.f2420u + ((int) (H(f3.floatValue()) * i3));
            int i5 = this.f2422w;
            canvas.translate(iH - i5, i4 - i5);
            this.T.draw(canvas);
            canvas.restore();
        }
    }

    public final void u(Canvas canvas) {
        float[] activeRange = getActiveRange();
        int iN = N(this.J, activeRange[0]);
        int iN2 = N(this.J, activeRange[1]);
        int i3 = iN * 2;
        canvas.drawPoints(this.J, 0, i3, this.f2407h);
        int i4 = iN2 * 2;
        canvas.drawPoints(this.J, i3, i4 - i3, this.f2408i);
        float[] fArr = this.J;
        canvas.drawPoints(fArr, i4, fArr.length - i4, this.f2407h);
    }

    public final void v() {
        if (this.f2418s == 2) {
            return;
        }
        Iterator<s2.a> it = this.f2413n.iterator();
        for (int i3 = 0; i3 < this.F.size() && it.hasNext(); i3++) {
            if (i3 != this.H) {
                Q(it.next(), this.F.get(i3).floatValue());
            }
        }
        if (!it.hasNext()) {
            throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(this.f2413n.size()), Integer.valueOf(this.F.size())));
        }
        Q(it.next(), this.F.get(this.H).floatValue());
    }

    public final void w(int i3) {
        if (i3 == 1) {
            F(Integer.MAX_VALUE);
            return;
        }
        if (i3 == 2) {
            F(Integer.MIN_VALUE);
        } else if (i3 == 17) {
            G(Integer.MAX_VALUE);
        } else {
            if (i3 != 66) {
                return;
            }
            G(Integer.MIN_VALUE);
        }
    }

    public final String x(float f3) {
        if (A()) {
            return this.B.a(f3);
        }
        return String.format(((float) ((int) f3)) == f3 ? "%.0f" : "%.2f", Float.valueOf(f3));
    }

    public final float y(int i3, float f3) {
        int i4 = i3 + 1;
        int i5 = i3 - 1;
        return g0.a.a(f3, i5 < 0 ? this.D : this.F.get(i5).floatValue(), i4 >= this.F.size() ? this.E : this.F.get(i4).floatValue());
    }

    public final int z(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2434b;

        public b() {
            this.f2434b = -1;
        }

        public void a(int i3) {
            this.f2434b = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.this.f2409j.W(this.f2434b, 4);
        }

        public /* synthetic */ b(BaseSlider baseSlider, a aVar) {
            this();
        }
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.sliderStyle);
    }

    public BaseSlider(Context context, AttributeSet attributeSet, int i3) {
        super(r2.a.c(context, attributeSet, i3, f2402c), attributeSet, i3);
        this.f2413n = new ArrayList();
        this.f2414o = new ArrayList();
        this.f2415p = new ArrayList();
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
        this.f2403d = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.f2404e = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint(1);
        this.f2405f = paint3;
        paint3.setStyle(Paint.Style.FILL);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.f2406g = paint4;
        paint4.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.f2407h = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        Paint paint6 = new Paint();
        this.f2408i = paint6;
        paint6.setStyle(Paint.Style.STROKE);
        paint6.setStrokeCap(Paint.Cap.ROUND);
        D(context2.getResources());
        this.f2412m = new a(attributeSet, i3);
        O(context2, attributeSet, i3);
        setFocusable(true);
        setClickable(true);
        hVar.f0(2);
        this.f2416q = ViewConfiguration.get(context2).getScaledTouchSlop();
        c cVar = new c(this);
        this.f2409j = cVar;
        t.j0(this, cVar);
        this.f2410k = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public void setValues(List<Float> list) {
        setValuesInternal(new ArrayList<>(list));
    }
}
