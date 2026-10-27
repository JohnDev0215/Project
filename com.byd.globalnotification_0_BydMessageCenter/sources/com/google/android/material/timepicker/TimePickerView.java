package com.google.android.material.timepicker;

import a.f.b.d;
import a.h.k.q;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.d.a.a.f;
import b.d.a.a.h;
import b.d.a.a.j0.e;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;

/* JADX INFO: loaded from: classes.dex */
public class TimePickerView extends ConstraintLayout {
    public b A;
    public final Chip u;
    public final Chip v;
    public final MaterialButtonToggleGroup w;
    public final View.OnClickListener x;
    public c y;
    public d z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d dVar = TimePickerView.this.z;
            if (dVar != null) {
                dVar.a(((Integer) view.getTag(f.selection_type)).intValue());
            }
        }
    }

    public interface b {
        void a();
    }

    public interface c {
        void a(int i);
    }

    public interface d {
        void a(int i);
    }

    public TimePickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.x = new a();
        LayoutInflater.from(context).inflate(h.material_timepicker, this);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(f.material_clock_period_toggle);
        this.w = materialButtonToggleGroup;
        materialButtonToggleGroup.f2411d.add(new b.d.a.a.j0.d(this));
        this.u = (Chip) findViewById(f.material_minute_tv);
        this.v = (Chip) findViewById(f.material_hour_tv);
        b.d.a.a.j0.f fVar = new b.d.a.a.j0.f(this, new GestureDetector(getContext(), new e(this)));
        this.u.setOnTouchListener(fVar);
        this.v.setOnTouchListener(fVar);
        this.u.setTag(f.selection_type, 12);
        this.v.setTag(f.selection_type, 10);
        this.u.setOnClickListener(this.x);
        this.v.setOnClickListener(this.x);
    }

    public final void m() {
        d.a aVar;
        if (this.w.getVisibility() == 0) {
            a.f.b.d dVar = new a.f.b.d();
            dVar.c(this);
            char c2 = q.p(this) == 0 ? (char) 2 : (char) 1;
            int i = f.material_clock_display;
            if (dVar.f656c.containsKey(Integer.valueOf(i)) && (aVar = dVar.f656c.get(Integer.valueOf(i))) != null) {
                switch (c2) {
                    case 1:
                        d.b bVar = aVar.f660d;
                        bVar.j = -1;
                        bVar.i = -1;
                        bVar.G = -1;
                        bVar.N = Integer.MIN_VALUE;
                        break;
                    case 2:
                        d.b bVar2 = aVar.f660d;
                        bVar2.l = -1;
                        bVar2.k = -1;
                        bVar2.H = -1;
                        bVar2.P = Integer.MIN_VALUE;
                        break;
                    case 3:
                        d.b bVar3 = aVar.f660d;
                        bVar3.n = -1;
                        bVar3.m = -1;
                        bVar3.I = 0;
                        bVar3.O = Integer.MIN_VALUE;
                        break;
                    case 4:
                        d.b bVar4 = aVar.f660d;
                        bVar4.o = -1;
                        bVar4.p = -1;
                        bVar4.J = 0;
                        bVar4.Q = Integer.MIN_VALUE;
                        break;
                    case 5:
                        d.b bVar5 = aVar.f660d;
                        bVar5.q = -1;
                        bVar5.r = -1;
                        bVar5.s = -1;
                        bVar5.M = 0;
                        bVar5.T = Integer.MIN_VALUE;
                        break;
                    case 6:
                        d.b bVar6 = aVar.f660d;
                        bVar6.t = -1;
                        bVar6.u = -1;
                        bVar6.L = 0;
                        bVar6.S = Integer.MIN_VALUE;
                        break;
                    case 7:
                        d.b bVar7 = aVar.f660d;
                        bVar7.v = -1;
                        bVar7.w = -1;
                        bVar7.K = 0;
                        bVar7.R = Integer.MIN_VALUE;
                        break;
                    case '\b':
                        d.b bVar8 = aVar.f660d;
                        bVar8.C = -1.0f;
                        bVar8.B = -1;
                        bVar8.A = -1;
                        break;
                    default:
                        throw new IllegalArgumentException("unknown constraint");
                }
            }
            dVar.b(this, true);
            setConstraintSet(null);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        m();
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            m();
        }
    }
}
