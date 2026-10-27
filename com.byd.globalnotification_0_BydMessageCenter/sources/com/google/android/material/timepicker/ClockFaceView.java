package com.google.android.material.timepicker;

import a.b.k.l;
import a.h.k.a;
import a.h.k.q;
import a.h.k.x.b;
import android.R;
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
import b.d.a.a.b;
import b.d.a.a.d;
import b.d.a.a.f;
import b.d.a.a.h;
import b.d.a.a.j;
import b.d.a.a.j0.c;
import b.d.a.a.k;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ClockFaceView extends c implements ClockHandView.c {
    public final SparseArray<TextView> A;
    public final a B;
    public final int[] C;
    public final float[] D;
    public final int E;
    public final int F;
    public final int G;
    public final int H;
    public String[] I;
    public float J;
    public final ColorStateList K;
    public final ClockHandView x;
    public final Rect y;
    public final RectF z;

    /* JADX WARN: Illegal instructions before constructor call */
    public ClockFaceView(Context context, AttributeSet attributeSet) {
        int i = b.materialClockStyle;
        super(context, attributeSet, i);
        this.y = new Rect();
        this.z = new RectF();
        this.A = new SparseArray<>();
        this.D = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.ClockFaceView, i, j.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        this.K = l.i.y(context, typedArrayObtainStyledAttributes, k.ClockFaceView_clockNumberTextColor);
        LayoutInflater.from(context).inflate(h.material_clockface_view, (ViewGroup) this, true);
        this.x = (ClockHandView) findViewById(f.material_clock_hand);
        this.E = resources.getDimensionPixelSize(d.material_clock_hand_padding);
        ColorStateList colorStateList = this.K;
        int colorForState = colorStateList.getColorForState(new int[]{R.attr.state_selected}, colorStateList.getDefaultColor());
        this.C = new int[]{colorForState, colorForState, this.K.getDefaultColor()};
        this.x.f2491f.add(this);
        int defaultColor = a.b.l.a.a.a(context, b.d.a.a.c.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateListY = l.i.y(context, typedArrayObtainStyledAttributes, k.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(colorStateListY != null ? colorStateListY.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new b.d.a.a.j0.a(this));
        setFocusable(true);
        typedArrayObtainStyledAttributes.recycle();
        this.B = new b.d.a.a.j0.b(this);
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        this.I = strArr;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int size = this.A.size();
        for (int i2 = 0; i2 < Math.max(this.I.length, size); i2++) {
            TextView textView = this.A.get(i2);
            if (i2 >= this.I.length) {
                removeView(textView);
                this.A.remove(i2);
            } else {
                if (textView == null) {
                    textView = (TextView) layoutInflaterFrom.inflate(h.material_clockface_textview, (ViewGroup) this, false);
                    this.A.put(i2, textView);
                    addView(textView);
                }
                textView.setVisibility(0);
                textView.setText(this.I[i2]);
                textView.setTag(f.material_value_index, Integer.valueOf(i2));
                q.U(textView, this.B);
                textView.setTextColor(this.K);
            }
        }
        this.F = resources.getDimensionPixelSize(d.material_time_picker_minimum_screen_height);
        this.G = resources.getDimensionPixelSize(d.material_time_picker_minimum_screen_width);
        this.H = resources.getDimensionPixelSize(d.material_clock_size);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.c
    public void a(float f2, boolean z) {
        if (Math.abs(this.J - f2) > 0.001f) {
            this.J = f2;
            n();
        }
    }

    public final void n() {
        RectF rectF = this.x.j;
        for (int i = 0; i < this.A.size(); i++) {
            TextView textView = this.A.get(i);
            if (textView != null) {
                textView.getDrawingRect(this.y);
                this.y.offset(textView.getPaddingLeft(), textView.getPaddingTop());
                offsetDescendantRectToMyCoords(textView, this.y);
                this.z.set(this.y);
                textView.getPaint().setShader(!RectF.intersects(rectF, this.z) ? null : new RadialGradient(rectF.centerX() - this.z.left, rectF.centerY() - this.z.top, 0.5f * rectF.width(), this.C, this.D, Shader.TileMode.CLAMP));
                textView.invalidate();
            }
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) b.C0016b.a(1, this.I.length, false, 1).f865a);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        n();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iMax = (int) (this.H / Math.max(Math.max(this.F / displayMetrics.heightPixels, this.G / displayMetrics.widthPixels), 1.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMax, 1073741824);
        setMeasuredDimension(iMax, iMax);
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec);
    }
}
