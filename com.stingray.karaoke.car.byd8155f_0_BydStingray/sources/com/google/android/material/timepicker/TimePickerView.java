package com.google.android.material.timepicker;

import F1.H;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.stingray.drivemodedetector.R;
import java.util.HashMap;
import r1.C2276h;
import r1.C2277i;
import r1.m;

/* JADX INFO: loaded from: classes.dex */
class TimePickerView extends ConstraintLayout {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final /* synthetic */ int f12970C = 0;
    public final MaterialButtonToggleGroup B;

    public TimePickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        g gVar = new g(this);
        LayoutInflater.from(context).inflate(R.layout.material_timepicker, this);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(R.id.material_clock_period_toggle);
        this.B = materialButtonToggleGroup;
        materialButtonToggleGroup.o.add(new h(this));
        Chip chip = (Chip) findViewById(R.id.material_minute_tv);
        Chip chip2 = (Chip) findViewById(R.id.material_hour_tv);
        j jVar = new j(new GestureDetector(getContext(), new i(this)));
        chip.setOnTouchListener(jVar);
        chip2.setOnTouchListener(jVar);
        chip.setTag(R.id.selection_type, 12);
        chip2.setTag(R.id.selection_type, 10);
        chip.setOnClickListener(gVar);
        chip2.setOnClickListener(gVar);
    }

    public final void k() {
        C2276h c2276h;
        if (this.B.getVisibility() == 0) {
            m mVar = new m();
            mVar.b(this);
            int[] iArr = H.f2303a;
            char c = getLayoutDirection() == 0 ? (char) 2 : (char) 1;
            HashMap map = mVar.c;
            if (map.containsKey(Integer.valueOf(R.id.material_clock_display)) && (c2276h = (C2276h) map.get(Integer.valueOf(R.id.material_clock_display))) != null) {
                C2277i c2277i = c2276h.d;
                switch (c) {
                    case 1:
                        c2277i.f21715i = -1;
                        c2277i.f21713h = -1;
                        c2277i.f21680F = -1;
                        c2277i.f21687M = Integer.MIN_VALUE;
                        break;
                    case 2:
                        c2277i.f21719k = -1;
                        c2277i.f21717j = -1;
                        c2277i.f21681G = -1;
                        c2277i.f21689O = Integer.MIN_VALUE;
                        break;
                    case 3:
                        c2277i.f21722m = -1;
                        c2277i.l = -1;
                        c2277i.f21682H = 0;
                        c2277i.f21688N = Integer.MIN_VALUE;
                        break;
                    case 4:
                        c2277i.f21724n = -1;
                        c2277i.o = -1;
                        c2277i.f21683I = 0;
                        c2277i.f21690P = Integer.MIN_VALUE;
                        break;
                    case 5:
                        c2277i.f21727p = -1;
                        c2277i.f21728q = -1;
                        c2277i.f21729r = -1;
                        c2277i.f21686L = 0;
                        c2277i.f21693S = Integer.MIN_VALUE;
                        break;
                    case 6:
                        c2277i.f21730s = -1;
                        c2277i.f21731t = -1;
                        c2277i.f21685K = 0;
                        c2277i.f21692R = Integer.MIN_VALUE;
                        break;
                    case 7:
                        c2277i.f21732u = -1;
                        c2277i.f21733v = -1;
                        c2277i.f21684J = 0;
                        c2277i.f21691Q = Integer.MIN_VALUE;
                        break;
                    case '\b':
                        c2277i.B = -1.0f;
                        c2277i.f21676A = -1;
                        c2277i.f21737z = -1;
                        break;
                    default:
                        throw new IllegalArgumentException("unknown constraint");
                }
            }
            mVar.a(this);
            setConstraintSet(null);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        k();
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i7) {
        super.onVisibilityChanged(view, i7);
        if (view == this && i7 == 0) {
            k();
        }
    }
}
