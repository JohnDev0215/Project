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
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import com.google.android.material.timepicker.ClockHandView;
import i0.u;
import j0.c;
import java.util.Arrays;
import o2.d;
import o2.f;
import o2.h;
import o2.k;
import o2.l;

/* JADX INFO: loaded from: classes.dex */
class ClockFaceView extends com.google.android.material.timepicker.b implements ClockHandView.d {
    private final ClockHandView G;
    private final Rect H;
    private final RectF I;
    private final SparseArray<TextView> J;
    private final i0.a K;
    private final int[] L;
    private final float[] M;
    private final int N;
    private final int O;
    private final int P;
    private final int Q;
    private String[] R;
    private float S;
    private final ColorStateList T;

    class a implements ViewTreeObserver.OnPreDrawListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (!ClockFaceView.this.isShown()) {
                return true;
            }
            ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
            ClockFaceView.this.D(((ClockFaceView.this.getHeight() / 2) - ClockFaceView.this.G.g()) - ClockFaceView.this.N);
            return true;
        }
    }

    class b extends i0.a {
        b() {
        }

        @Override // i0.a
        public void g(View view, c cVar) {
            super.g(view, cVar);
            int iIntValue = ((Integer) view.getTag(f.material_value_index)).intValue();
            if (iIntValue > 0) {
                cVar.m0((View) ClockFaceView.this.J.get(iIntValue - 1));
            }
            cVar.S(c.C0090c.a(0, 1, iIntValue, 1, false, view.isSelected()));
        }
    }

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, o2.b.materialClockStyle);
    }

    private void K() {
        RectF rectFD = this.G.d();
        for (int i7 = 0; i7 < this.J.size(); i7++) {
            TextView textView = this.J.get(i7);
            if (textView != null) {
                textView.getDrawingRect(this.H);
                this.H.offset(textView.getPaddingLeft(), textView.getPaddingTop());
                offsetDescendantRectToMyCoords(textView, this.H);
                this.I.set(this.H);
                textView.getPaint().setShader(L(rectFD, this.I));
                textView.invalidate();
            }
        }
    }

    private RadialGradient L(RectF rectF, RectF rectF2) {
        if (RectF.intersects(rectF, rectF2)) {
            return new RadialGradient(rectF.centerX() - this.I.left, rectF.centerY() - this.I.top, rectF.width() * 0.5f, this.L, this.M, Shader.TileMode.CLAMP);
        }
        return null;
    }

    private static float M(float f7, float f8, float f9) {
        return Math.max(Math.max(f7, f8), f9);
    }

    private void O(int i7) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int size = this.J.size();
        for (int i8 = 0; i8 < Math.max(this.R.length, size); i8++) {
            TextView textView = this.J.get(i8);
            if (i8 >= this.R.length) {
                removeView(textView);
                this.J.remove(i8);
            } else {
                if (textView == null) {
                    textView = (TextView) layoutInflaterFrom.inflate(h.material_clockface_textview, (ViewGroup) this, false);
                    this.J.put(i8, textView);
                    addView(textView);
                }
                textView.setVisibility(0);
                textView.setText(this.R[i8]);
                textView.setTag(f.material_value_index, Integer.valueOf(i8));
                u.k0(textView, this.K);
                textView.setTextColor(this.T);
                if (i7 != 0) {
                    textView.setContentDescription(getResources().getString(i7, this.R[i8]));
                }
            }
        }
    }

    @Override // com.google.android.material.timepicker.b
    public void D(int i7) {
        if (i7 != C()) {
            super.D(i7);
            this.G.j(C());
        }
    }

    public void N(String[] strArr, int i7) {
        this.R = strArr;
        O(i7);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.d
    public void a(float f7, boolean z6) {
        if (Math.abs(this.S - f7) > 0.001f) {
            this.S = f7;
            K();
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        c.p0(accessibilityNodeInfo).R(c.b.a(1, this.R.length, false, 1));
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        K();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    protected void onMeasure(int i7, int i8) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iM = (int) (this.Q / M(this.O / displayMetrics.heightPixels, this.P / displayMetrics.widthPixels, 1.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iM, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        setMeasuredDimension(iM, iM);
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ClockFaceView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.H = new Rect();
        this.I = new RectF();
        this.J = new SparseArray<>();
        this.M = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.ClockFaceView, i7, k.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList colorStateListA = b3.c.a(context, typedArrayObtainStyledAttributes, l.ClockFaceView_clockNumberTextColor);
        this.T = colorStateListA;
        LayoutInflater.from(context).inflate(h.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(f.material_clock_hand);
        this.G = clockHandView;
        this.N = resources.getDimensionPixelSize(d.material_clock_hand_padding);
        int colorForState = colorStateListA.getColorForState(new int[]{R.attr.state_selected}, colorStateListA.getDefaultColor());
        this.L = new int[]{colorForState, colorForState, colorStateListA.getDefaultColor()};
        clockHandView.b(this);
        int defaultColor = f.a.a(context, o2.c.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateListA2 = b3.c.a(context, typedArrayObtainStyledAttributes, l.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(colorStateListA2 != null ? colorStateListA2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new a());
        setFocusable(true);
        typedArrayObtainStyledAttributes.recycle();
        this.K = new b();
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        N(strArr, 0);
        this.O = resources.getDimensionPixelSize(d.material_time_picker_minimum_screen_height);
        this.P = resources.getDimensionPixelSize(d.material_time_picker_minimum_screen_width);
        this.Q = resources.getDimensionPixelSize(d.material_clock_size);
    }
}
