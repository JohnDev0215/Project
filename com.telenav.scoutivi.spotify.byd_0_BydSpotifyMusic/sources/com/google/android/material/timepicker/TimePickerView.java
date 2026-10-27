package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import i0.u;
import o2.h;

/* JADX INFO: loaded from: classes.dex */
class TimePickerView extends ConstraintLayout {
    private final Chip D;
    private final Chip E;
    private final ClockHandView F;
    private final ClockFaceView G;
    private final MaterialButtonToggleGroup H;
    private final View.OnClickListener I;
    private f J;
    private g K;
    private e L;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TimePickerView.this.K != null) {
                TimePickerView.this.K.a(((Integer) view.getTag(o2.f.selection_type)).intValue());
            }
        }
    }

    class b implements MaterialButtonToggleGroup.e {
        b() {
        }

        @Override // com.google.android.material.button.MaterialButtonToggleGroup.e
        public void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i7, boolean z6) {
            int i8 = i7 == o2.f.material_clock_period_pm_button ? 1 : 0;
            if (TimePickerView.this.J == null || !z6) {
                return;
            }
            TimePickerView.this.J.a(i8);
        }
    }

    class c extends GestureDetector.SimpleOnGestureListener {
        c() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean zOnDoubleTap = super.onDoubleTap(motionEvent);
            if (TimePickerView.this.L != null) {
                TimePickerView.this.L.a();
            }
            return zOnDoubleTap;
        }
    }

    class d implements View.OnTouchListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ GestureDetector f4973e;

        d(GestureDetector gestureDetector) {
            this.f4973e = gestureDetector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (((Checkable) view).isChecked()) {
                return this.f4973e.onTouchEvent(motionEvent);
            }
            return false;
        }
    }

    interface e {
        void a();
    }

    interface f {
        void a(int i7);
    }

    interface g {
        void a(int i7);
    }

    public TimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void E() {
        Chip chip = this.D;
        int i7 = o2.f.selection_type;
        chip.setTag(i7, 12);
        this.E.setTag(i7, 10);
        this.D.setOnClickListener(this.I);
        this.E.setOnClickListener(this.I);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void F() {
        d dVar = new d(new GestureDetector(getContext(), new c()));
        this.D.setOnTouchListener(dVar);
        this.E.setOnTouchListener(dVar);
    }

    private void G() {
        if (this.H.getVisibility() == 0) {
            androidx.constraintlayout.widget.d dVar = new androidx.constraintlayout.widget.d();
            dVar.p(this);
            dVar.n(o2.f.material_clock_display, u.y(this) == 0 ? 2 : 1);
            dVar.i(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        G();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i7) {
        super.onVisibilityChanged(view, i7);
        if (view == this && i7 == 0) {
            G();
        }
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.I = new a();
        LayoutInflater.from(context).inflate(h.material_timepicker, this);
        this.G = (ClockFaceView) findViewById(o2.f.material_clock_face);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(o2.f.material_clock_period_toggle);
        this.H = materialButtonToggleGroup;
        materialButtonToggleGroup.g(new b());
        this.D = (Chip) findViewById(o2.f.material_minute_tv);
        this.E = (Chip) findViewById(o2.f.material_hour_tv);
        this.F = (ClockHandView) findViewById(o2.f.material_clock_hand);
        F();
        E();
    }
}
