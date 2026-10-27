package com.google.android.material.timepicker;

import B2.O;
import F1.H;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import com.stingray.drivemodedetector.R;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class ClockFaceView extends f implements d {

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final ClockHandView f12948E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final Rect f12949F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final RectF f12950G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final SparseArray f12951H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final c f12952I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final int[] f12953J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final float[] f12954K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final int f12955L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final int f12956M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final int f12957N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final int f12958O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final String[] f12959P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public float f12960Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final ColorStateList f12961R;

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12949F = new Rect();
        this.f12950G = new RectF();
        SparseArray sparseArray = new SparseArray();
        this.f12951H = sparseArray;
        this.f12954K = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, K4.a.d, R.attr.materialClockStyle, R.style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList colorStateListZ = com.bumptech.glide.c.Z(context, typedArrayObtainStyledAttributes, 1);
        this.f12961R = colorStateListZ;
        LayoutInflater.from(context).inflate(R.layout.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R.id.material_clock_hand);
        this.f12948E = clockHandView;
        this.f12955L = resources.getDimensionPixelSize(R.dimen.material_clock_hand_padding);
        int colorForState = colorStateListZ.getColorForState(new int[]{android.R.attr.state_selected}, colorStateListZ.getDefaultColor());
        this.f12953J = new int[]{colorForState, colorForState, colorStateListZ.getDefaultColor()};
        clockHandView.l.add(this);
        int defaultColor = h6.f.h(context, R.color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateListZ2 = com.bumptech.glide.c.Z(context, typedArrayObtainStyledAttributes, 0);
        setBackgroundColor(colorStateListZ2 != null ? colorStateListZ2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new b(this));
        setFocusable(true);
        typedArrayObtainStyledAttributes.recycle();
        this.f12952I = new c(this);
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        this.f12959P = strArr;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int size = sparseArray.size();
        for (int i7 = 0; i7 < Math.max(this.f12959P.length, size); i7++) {
            TextView textView = (TextView) sparseArray.get(i7);
            if (i7 >= this.f12959P.length) {
                removeView(textView);
                sparseArray.remove(i7);
            } else {
                if (textView == null) {
                    textView = (TextView) layoutInflaterFrom.inflate(R.layout.material_clockface_textview, (ViewGroup) this, false);
                    sparseArray.put(i7, textView);
                    addView(textView);
                }
                textView.setVisibility(0);
                textView.setText(this.f12959P[i7]);
                textView.setTag(R.id.material_value_index, Integer.valueOf(i7));
                H.g(textView, this.f12952I);
                textView.setTextColor(this.f12961R);
            }
        }
        this.f12956M = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_height);
        this.f12957N = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_width);
        this.f12958O = resources.getDimensionPixelSize(R.dimen.material_clock_size);
    }

    public final void l() {
        RadialGradient radialGradient;
        RectF rectF = this.f12948E.f12964p;
        int i7 = 0;
        while (true) {
            SparseArray sparseArray = this.f12951H;
            if (i7 >= sparseArray.size()) {
                return;
            }
            TextView textView = (TextView) sparseArray.get(i7);
            if (textView != null) {
                Rect rect = this.f12949F;
                textView.getDrawingRect(rect);
                rect.offset(textView.getPaddingLeft(), textView.getPaddingTop());
                offsetDescendantRectToMyCoords(textView, rect);
                RectF rectF2 = this.f12950G;
                rectF2.set(rect);
                if (RectF.intersects(rectF, rectF2)) {
                    radialGradient = new RadialGradient(rectF.centerX() - rectF2.left, rectF.centerY() - rectF2.top, 0.5f * rectF.width(), this.f12953J, this.f12954K, Shader.TileMode.CLAMP);
                } else {
                    radialGradient = null;
                }
                textView.getPaint().setShader(radialGradient);
                textView.invalidate();
            }
            i7++;
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) O.e0(1, this.f12959P.length, 1).f527m);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        super.onLayout(z6, i7, i10, i11, i12);
        l();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i7, int i10) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iMax = (int) (this.f12958O / Math.max(Math.max(this.f12956M / displayMetrics.heightPixels, this.f12957N / displayMetrics.widthPixels), 1.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMax, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        setMeasuredDimension(iMax, iMax);
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec);
    }
}
