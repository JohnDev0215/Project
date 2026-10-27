package com.google.android.material.timepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.a;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.timepicker.ClockHandView;
import defpackage.in3;
import defpackage.om3;
import defpackage.ra3;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class TimePickerView extends ConstraintLayout {
    public static final /* synthetic */ int w = 0;
    public final ClockHandView u;
    public final MaterialButtonToggleGroup v;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            int i = TimePickerView.w;
            TimePickerView.this.getClass();
        }
    }

    public class b implements MaterialButtonToggleGroup.d {
        public b() {
        }

        @Override // com.google.android.material.button.MaterialButtonToggleGroup.d
        public final void a() {
            int i = TimePickerView.w;
            TimePickerView.this.getClass();
        }
    }

    public interface c {
    }

    public interface d {
    }

    public TimePickerView(Context context) {
        this(context, null);
    }

    public void addOnRotateListener(ClockHandView.b bVar) {
        this.u.addOnRotateListener(bVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        s();
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            s();
        }
    }

    public final void s() {
        a.C0004a c0004a;
        if (this.v.getVisibility() == 0) {
            androidx.constraintlayout.widget.a aVar = new androidx.constraintlayout.widget.a();
            aVar.e(this);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            char c2 = om3.e.c(this) == 0 ? (char) 2 : (char) 1;
            int i = R$id.material_clock_display;
            HashMap<Integer, a.C0004a> map = aVar.g;
            if (map.containsKey(Integer.valueOf(i)) && (c0004a = map.get(Integer.valueOf(i))) != null) {
                a.b bVar = c0004a.e;
                switch (c2) {
                    case 1:
                        bVar.j = -1;
                        bVar.i = -1;
                        bVar.G = -1;
                        bVar.N = Integer.MIN_VALUE;
                        break;
                    case 2:
                        bVar.l = -1;
                        bVar.k = -1;
                        bVar.H = -1;
                        bVar.P = Integer.MIN_VALUE;
                        break;
                    case 3:
                        bVar.n = -1;
                        bVar.m = -1;
                        bVar.I = 0;
                        bVar.O = Integer.MIN_VALUE;
                        break;
                    case 4:
                        bVar.o = -1;
                        bVar.p = -1;
                        bVar.f9J = 0;
                        bVar.Q = Integer.MIN_VALUE;
                        break;
                    case 5:
                        bVar.q = -1;
                        bVar.r = -1;
                        bVar.s = -1;
                        bVar.M = 0;
                        bVar.T = Integer.MIN_VALUE;
                        break;
                    case 6:
                        bVar.t = -1;
                        bVar.u = -1;
                        bVar.L = 0;
                        bVar.S = Integer.MIN_VALUE;
                        break;
                    case 7:
                        bVar.v = -1;
                        bVar.w = -1;
                        bVar.K = 0;
                        bVar.R = Integer.MIN_VALUE;
                        break;
                    case '\b':
                        bVar.C = -1.0f;
                        bVar.B = -1;
                        bVar.A = -1;
                        break;
                    default:
                        throw new IllegalArgumentException("unknown constraint");
                }
            }
            aVar.b(this);
        }
    }

    public void setOnActionUpListener(ClockHandView.a aVar) {
        this.u.setOnActionUpListener(aVar);
    }

    public TimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a aVar = new a();
        LayoutInflater.from(context).inflate(R$layout.material_timepicker, this);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(R$id.material_clock_period_toggle);
        this.v = materialButtonToggleGroup;
        materialButtonToggleGroup.addOnButtonCheckedListener(new b());
        Chip chip = (Chip) findViewById(R$id.material_minute_tv);
        Chip chip2 = (Chip) findViewById(R$id.material_hour_tv);
        this.u = (ClockHandView) findViewById(R$id.material_clock_hand);
        ra3 ra3Var = new ra3(new GestureDetector(getContext(), new com.google.android.material.timepicker.b(this)));
        chip.setOnTouchListener(ra3Var);
        chip2.setOnTouchListener(ra3Var);
        int i2 = R$id.selection_type;
        chip.setTag(i2, 12);
        chip2.setTag(i2, 10);
        chip.setOnClickListener(aVar);
        chip2.setOnClickListener(aVar);
        chip.setAccessibilityClassName("android.view.View");
        chip2.setAccessibilityClassName("android.view.View");
    }

    public void setOnDoubleTapListener(c cVar) {
    }

    public void setOnPeriodChangeListener(d dVar) {
    }
}
