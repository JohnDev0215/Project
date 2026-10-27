package com.google.android.material.timepicker;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.timepicker.ClockHandView;
import defpackage.C0214v;
import defpackage.bz;
import defpackage.om3;
import defpackage.rc1;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class ClockFaceView extends RadialViewGroup implements ClockHandView.b {
    public final SparseArray<TextView> A;
    public final b B;
    public final int[] C;
    public final float[] D;
    public final int E;
    public final int F;
    public final int G;
    public final int H;
    public String[] I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public float f37J;
    public final ColorStateList K;
    public final ClockHandView x;
    public final Rect y;
    public final RectF z;

    public class a implements ViewTreeObserver.OnPreDrawListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            ClockFaceView clockFaceView = ClockFaceView.this;
            if (!clockFaceView.isShown()) {
                return true;
            }
            clockFaceView.getViewTreeObserver().removeOnPreDrawListener(this);
            int height = ((clockFaceView.getHeight() / 2) - clockFaceView.x.f) - clockFaceView.E;
            if (height != clockFaceView.v) {
                clockFaceView.v = height;
                clockFaceView.s();
                int i = clockFaceView.v;
                ClockHandView clockHandView = clockFaceView.x;
                clockHandView.n = i;
                clockHandView.invalidate();
            }
            return true;
        }
    }

    public class b extends C0214v {
        public b() {
        }

        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            View.AccessibilityDelegate accessibilityDelegate = this.a;
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            int iIntValue = ((Integer) view.getTag(R$id.material_value_index)).intValue();
            if (iIntValue > 0) {
                accessibilityNodeInfo.setTraversalAfter(ClockFaceView.this.A.get(iIntValue - 1));
            }
            accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(0, 1, iIntValue, 1, false, view.isSelected()));
            accessibilityNodeInfoCompat.o(true);
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.g);
        }

        @Override // defpackage.C0214v
        public final boolean g(View view, int i, Bundle bundle) {
            if (i != 16) {
                return super.g(view, i, bundle);
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            float x = view.getX() + (view.getWidth() / 2.0f);
            float height = (view.getHeight() / 2.0f) + view.getY();
            ClockFaceView clockFaceView = ClockFaceView.this;
            clockFaceView.x.onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, x, height, 0));
            clockFaceView.x.onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 1, x, height, 0));
            return true;
        }
    }

    public ClockFaceView(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.b
    public final void a(float f) {
        if (Math.abs(this.f37J - f) > 0.001f) {
            this.f37J = f;
            t();
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        new AccessibilityNodeInfoCompat(accessibilityNodeInfo).p(AccessibilityNodeInfoCompat.c.a(1, this.I.length, 1));
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        t();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iMax = (int) (this.H / Math.max(Math.max(this.F / displayMetrics.heightPixels, this.G / displayMetrics.widthPixels), 1.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMax, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        setMeasuredDimension(iMax, iMax);
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec);
    }

    public final void t() {
        RectF rectF = this.x.i;
        int i = 0;
        while (true) {
            SparseArray<TextView> sparseArray = this.A;
            if (i >= sparseArray.size()) {
                return;
            }
            TextView textView = sparseArray.get(i);
            if (textView != null) {
                Rect rect = this.y;
                textView.getDrawingRect(rect);
                offsetDescendantRectToMyCoords(textView, rect);
                textView.setSelected(rectF.contains(rect.centerX(), rect.centerY()));
                RectF rectF2 = this.z;
                rectF2.set(rect);
                rectF2.offset(textView.getPaddingLeft(), textView.getPaddingTop());
                textView.getPaint().setShader(!RectF.intersects(rectF, rectF2) ? null : new RadialGradient(rectF.centerX() - rectF2.left, rectF.centerY() - rectF2.top, 0.5f * rectF.width(), this.C, this.D, Shader.TileMode.CLAMP));
                textView.invalidate();
            }
            i++;
        }
    }

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialClockStyle);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ClockFaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.y = new Rect();
        this.z = new RectF();
        SparseArray<TextView> sparseArray = new SparseArray<>();
        this.A = sparseArray;
        this.D = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClockFaceView, i, R$style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList colorStateListA = rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.ClockFaceView_clockNumberTextColor);
        this.K = colorStateListA;
        LayoutInflater.from(context).inflate(R$layout.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R$id.material_clock_hand);
        this.x = clockHandView;
        this.E = resources.getDimensionPixelSize(R$dimen.material_clock_hand_padding);
        int colorForState = colorStateListA.getColorForState(new int[]{R.attr.state_selected}, colorStateListA.getDefaultColor());
        this.C = new int[]{colorForState, colorForState, colorStateListA.getDefaultColor()};
        clockHandView.addOnRotateListener(this);
        int defaultColor = bz.b(context, R$color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateListA2 = rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(colorStateListA2 != null ? colorStateListA2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new a());
        setFocusable(true);
        typedArrayObtainStyledAttributes.recycle();
        this.B = new b();
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        this.I = strArr;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int size = sparseArray.size();
        for (int i2 = 0; i2 < Math.max(this.I.length, size); i2++) {
            TextView textView = sparseArray.get(i2);
            if (i2 >= this.I.length) {
                removeView(textView);
                sparseArray.remove(i2);
            } else {
                if (textView == null) {
                    textView = (TextView) layoutInflaterFrom.inflate(R$layout.material_clockface_textview, (ViewGroup) this, false);
                    sparseArray.put(i2, textView);
                    addView(textView);
                }
                textView.setVisibility(0);
                textView.setText(this.I[i2]);
                textView.setTag(R$id.material_value_index, Integer.valueOf(i2));
                om3.k(textView, this.B);
                textView.setTextColor(this.K);
            }
        }
        this.F = resources.getDimensionPixelSize(R$dimen.material_time_picker_minimum_screen_height);
        this.G = resources.getDimensionPixelSize(R$dimen.material_time_picker_minimum_screen_width);
        this.H = resources.getDimensionPixelSize(R$dimen.material_clock_size);
    }
}
