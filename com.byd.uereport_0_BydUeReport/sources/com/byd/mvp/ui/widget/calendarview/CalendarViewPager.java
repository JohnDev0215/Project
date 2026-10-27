package com.byd.mvp.ui.widget.calendarview;

import android.content.Context;
import android.util.AttributeSet;
import androidx.viewpager.widget.ViewPager;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import j4.c;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class CalendarViewPager extends ViewPager {

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public static final String f2960l0 = CalendarViewPager.class.getSimpleName();
    public int A0;
    public h4.b B0;
    public n1.a C0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public HashMap<Integer, h4.b> f2961m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public j4.a f2962n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public c f2963o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public int f2964p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public int f2965q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public int f2966r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f2967s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public CalendarDate f2968t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public CalendarDate f2969u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public CalendarDate f2970v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public CalendarDate f2971w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public CalendarDate f2972x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public String f2973y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f2974z0;

    public class a extends ViewPager.m {
        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageSelected(int i8) {
            CalendarViewPager calendarViewPager = CalendarViewPager.this;
            int i9 = calendarViewPager.f2965q0;
            if (i9 > 0) {
                i8 += i9;
            }
            calendarViewPager.f2964p0 = i8;
            calendarViewPager.B0 = calendarViewPager.f2961m0.get(Integer.valueOf(i8));
            if (CalendarViewPager.this.B0 == null) {
                return;
            }
            if (i8 == CalendarViewPager.this.f2974z0) {
                CalendarViewPager.this.B0.e(CalendarViewPager.this.f2970v0, CalendarViewPager.this.f2973y0);
            }
            CalendarViewPager calendarViewPager2 = CalendarViewPager.this;
            if (i8 == calendarViewPager2.f2965q0) {
                calendarViewPager2.B0.g(CalendarViewPager.this.f2971w0);
            }
            CalendarViewPager calendarViewPager3 = CalendarViewPager.this;
            if (i8 == calendarViewPager3.f2966r0) {
                calendarViewPager3.B0.f(CalendarViewPager.this.f2972x0);
            }
            if (CalendarViewPager.this.A0 > 0) {
                CalendarViewPager.this.B0.i(CalendarViewPager.this.A0);
            }
            CalendarViewPager.this.B0.c(h4.a.b(CalendarViewPager.this.B0.a().year, CalendarViewPager.this.B0.a().month));
            if (CalendarViewPager.this.f2963o0 != null) {
                CalendarViewPager.this.f2963o0.a(CalendarViewPager.this.B0.a().year, CalendarViewPager.this.B0.a().month);
            }
        }
    }

    public class b implements j4.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h4.b f2976a;

        public b(h4.b bVar) {
            this.f2976a = bVar;
        }

        @Override // j4.b
        public void a(int i8, CalendarDate calendarDate) {
            if (CalendarViewPager.this.f2962n0 != null) {
                CalendarViewPager.this.f2962n0.a(calendarDate.year, calendarDate.month, calendarDate.day);
            }
            for (h4.b bVar : CalendarViewPager.this.f2961m0.values()) {
                if (!bVar.equals(this.f2976a)) {
                    bVar.d(null);
                }
            }
        }
    }

    public CalendarViewPager(Context context) {
        super(context);
        this.f2964p0 = 0;
        this.f2965q0 = -1;
        this.f2966r0 = -1;
        this.f2967s0 = 0;
        this.A0 = -1;
        e0();
    }

    public CalendarDate d0(int i8) {
        int i9 = this.f2967s0 - i8;
        CalendarDate calendarDate = this.f2969u0;
        int i10 = calendarDate.year - (i9 / 12);
        int i11 = calendarDate.month - (i9 % 12);
        if (i11 <= 0) {
            i11 += 12;
            i10--;
        } else if (i11 > 12) {
            i11 -= 12;
            i10++;
        }
        return new CalendarDate(i10, i11, 1);
    }

    public final void e0() {
        this.f2961m0 = new HashMap<>();
    }

    public void f0(CalendarDate calendarDate) {
        this.f2969u0 = calendarDate;
        this.f2967s0 = 1073741823;
        h0(calendarDate, 1073741823);
        setOffscreenPageLimit(3);
    }

    public h4.b g0(Context context, CalendarDate calendarDate, int i8) {
        h4.b bVar = new h4.b(context, new CalendarDate(calendarDate.year, calendarDate.month, 1));
        bVar.h(new b(bVar));
        this.f2961m0.put(Integer.valueOf(i8), bVar);
        return bVar;
    }

    public CalendarDate getCurDate() {
        return this.f2968t0;
    }

    public int getCurIndex() {
        int i8 = this.f2964p0;
        int i9 = this.f2965q0;
        return i9 > 0 ? i8 - i9 : i8;
    }

    public final void h0(CalendarDate calendarDate, int i8) {
        g0(getContext(), calendarDate, i8);
        i4.b bVar = new i4.b(this);
        this.C0 = bVar;
        setAdapter(bVar);
        c(new a());
        N(this.f2967s0, false);
        this.C0.l();
    }

    public void i0(h4.b bVar) {
        if (bVar == null) {
            return;
        }
        CalendarDate calendarDateA = bVar.a();
        CalendarDate calendarDate = this.f2968t0;
        if (calendarDate != null && calendarDate.year == calendarDateA.year && calendarDate.month == calendarDateA.month) {
            bVar.d(calendarDate);
        }
    }

    public void j0(int i8) {
        N(getCurIndex() + i8, false);
    }

    public void setOnDateChangedCallback(j4.a aVar) {
        this.f2962n0 = aVar;
    }

    public void setOnPageSelectedCallback(c cVar) {
        this.f2963o0 = cVar;
    }

    public void setPageLimit(int i8) {
        setOffscreenPageLimit(i8);
    }

    public void setSelectedBackground(int i8) {
        this.A0 = i8;
    }

    public CalendarViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2964p0 = 0;
        this.f2965q0 = -1;
        this.f2966r0 = -1;
        this.f2967s0 = 0;
        this.A0 = -1;
        e0();
    }
}
