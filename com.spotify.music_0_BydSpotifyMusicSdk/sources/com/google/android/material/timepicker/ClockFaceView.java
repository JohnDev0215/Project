package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import com.spotify.music.R;
import java.util.Arrays;
import p.a02;
import p.ci1;
import p.l13;
import p.xz1;
import p.zt;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
class ClockFaceView extends a02 implements zt {
    public final ClockHandView B;
    public final Rect C;
    public final RectF D;
    public final SparseArray E;
    public final c F;
    public final int[] G;
    public final float[] H;
    public final int I;
    public final int J;
    public final int K;
    public final int L;
    public final String[] M;
    public float N;
    public final ColorStateList O;

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.C = new Rect();
        this.D = new RectF();
        SparseArray sparseArray = new SparseArray();
        this.E = sparseArray;
        this.H = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, xz1.d, R.attr.materialClockStyle, R.style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList colorStateListV = ci1.v(context, typedArrayObtainStyledAttributes, 1);
        this.O = colorStateListV;
        LayoutInflater.from(context).inflate(R.layout.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R.id.material_clock_hand);
        this.B = clockHandView;
        this.I = resources.getDimensionPixelSize(R.dimen.material_clock_hand_padding);
        int colorForState = colorStateListV.getColorForState(new int[]{android.R.attr.state_selected}, colorStateListV.getDefaultColor());
        this.G = new int[]{colorForState, colorForState, colorStateListV.getDefaultColor()};
        clockHandView.c.add(this);
        int defaultColor = l13.d(context, R.color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateListV2 = ci1.v(context, typedArrayObtainStyledAttributes, 0);
        setBackgroundColor(colorStateListV2 != null ? colorStateListV2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new b(this));
        setFocusable(true);
        typedArrayObtainStyledAttributes.recycle();
        this.F = new c(this);
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        this.M = strArr;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int size = sparseArray.size();
        for (int i = 0; i < Math.max(this.M.length, size); i++) {
            TextView textView = (TextView) sparseArray.get(i);
            if (i >= this.M.length) {
                removeView(textView);
                sparseArray.remove(i);
            } else {
                if (textView == null) {
                    textView = (TextView) layoutInflaterFrom.inflate(R.layout.material_clockface_textview, (ViewGroup) this, false);
                    sparseArray.put(i, textView);
                    addView(textView);
                }
                textView.setVisibility(0);
                textView.setText(this.M[i]);
                textView.setTag(R.id.material_value_index, Integer.valueOf(i));
                zt2.k(textView, this.F);
                textView.setTextColor(this.O);
            }
        }
        this.J = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_height);
        this.K = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_width);
        this.L = resources.getDimensionPixelSize(R.dimen.material_clock_size);
    }

    public final void o() {
        RectF rectF = this.B.m;
        int i = 0;
        while (true) {
            SparseArray sparseArray = this.E;
            if (i >= sparseArray.size()) {
                return;
            }
            TextView textView = (TextView) sparseArray.get(i);
            if (textView != null) {
                Rect rect = this.C;
                textView.getDrawingRect(rect);
                rect.offset(textView.getPaddingLeft(), textView.getPaddingTop());
                offsetDescendantRectToMyCoords(textView, rect);
                RectF rectF2 = this.D;
                rectF2.set(rect);
                textView.getPaint().setShader(!RectF.intersects(rectF, rectF2) ? null : new RadialGradient(rectF.centerX() - rectF2.left, rectF.centerY() - rectF2.top, 0.5f * rectF.width(), this.G, this.H, Shader.TileMode.CLAMP));
                textView.invalidate();
            }
            i++;
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(1, this.M.length, false, 1));
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        o();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iMax = (int) (this.L / Math.max(Math.max(this.J / displayMetrics.heightPixels, this.K / displayMetrics.widthPixels), 1.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMax, 1073741824);
        setMeasuredDimension(iMax, iMax);
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec);
    }
}
