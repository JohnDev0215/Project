package com.google.android.material.timepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.spotify.music.R;
import java.util.HashMap;
import java.util.WeakHashMap;
import p.jt2;
import p.ky;
import p.lt2;
import p.ly;
import p.ol2;
import p.py;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
class TimePickerView extends ConstraintLayout {
    public static final /* synthetic */ int z = 0;
    public final MaterialButtonToggleGroup y;

    public TimePickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        d dVar = new d(this);
        LayoutInflater.from(context).inflate(R.layout.material_timepicker, this);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(R.id.material_clock_period_toggle);
        this.y = materialButtonToggleGroup;
        materialButtonToggleGroup.l.add(new e(this));
        Chip chip = (Chip) findViewById(R.id.material_minute_tv);
        Chip chip2 = (Chip) findViewById(R.id.material_hour_tv);
        WeakHashMap weakHashMap = zt2.a;
        lt2.f(chip, 2);
        lt2.f(chip2, 2);
        ol2 ol2Var = new ol2(new GestureDetector(getContext(), new f(this)));
        chip.setOnTouchListener(ol2Var);
        chip2.setOnTouchListener(ol2Var);
        chip.setTag(R.id.selection_type, 12);
        chip2.setTag(R.id.selection_type, 10);
        chip.setOnClickListener(dVar);
        chip2.setOnClickListener(dVar);
    }

    public final void n() {
        ky kyVar;
        if (this.y.getVisibility() == 0) {
            py pyVar = new py();
            pyVar.b(this);
            WeakHashMap weakHashMap = zt2.a;
            char c = jt2.d(this) == 0 ? (char) 2 : (char) 1;
            HashMap map = pyVar.c;
            if (map.containsKey(Integer.valueOf(R.id.material_clock_display)) && (kyVar = (ky) map.get(Integer.valueOf(R.id.material_clock_display))) != null) {
                ly lyVar = kyVar.d;
                switch (c) {
                    case 1:
                        lyVar.i = -1;
                        lyVar.h = -1;
                        lyVar.F = -1;
                        lyVar.M = Integer.MIN_VALUE;
                        break;
                    case 2:
                        lyVar.k = -1;
                        lyVar.j = -1;
                        lyVar.G = -1;
                        lyVar.O = Integer.MIN_VALUE;
                        break;
                    case 3:
                        lyVar.m = -1;
                        lyVar.l = -1;
                        lyVar.H = 0;
                        lyVar.N = Integer.MIN_VALUE;
                        break;
                    case 4:
                        lyVar.n = -1;
                        lyVar.o = -1;
                        lyVar.I = 0;
                        lyVar.P = Integer.MIN_VALUE;
                        break;
                    case 5:
                        lyVar.f152p = -1;
                        lyVar.q = -1;
                        lyVar.r = -1;
                        lyVar.L = 0;
                        lyVar.S = Integer.MIN_VALUE;
                        break;
                    case 6:
                        lyVar.s = -1;
                        lyVar.t = -1;
                        lyVar.K = 0;
                        lyVar.R = Integer.MIN_VALUE;
                        break;
                    case 7:
                        lyVar.u = -1;
                        lyVar.v = -1;
                        lyVar.J = 0;
                        lyVar.Q = Integer.MIN_VALUE;
                        break;
                    case '\b':
                        lyVar.B = -1.0f;
                        lyVar.A = -1;
                        lyVar.z = -1;
                        break;
                    default:
                        throw new IllegalArgumentException("unknown constraint");
                }
            }
            pyVar.a(this);
            setConstraintSet(null);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        n();
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            n();
        }
    }
}
