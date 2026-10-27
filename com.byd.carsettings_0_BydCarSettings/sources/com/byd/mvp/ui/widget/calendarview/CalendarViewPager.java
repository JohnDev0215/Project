package com.byd.mvp.ui.widget.calendarview;

import android.content.Context;
import android.util.AttributeSet;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import b.b.m.f.f.a.d.c;
import com.byd.mvp.ui.widget.calendarview.adapter.CalendarPagerAdapter;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class CalendarViewPager extends ViewPager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3832a = CalendarViewPager.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public HashMap<Integer, b.b.m.f.f.a.b> f3833b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.m.f.f.a.d.a f3834c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f3835d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3836e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3837f;
    public int g;
    public int h;
    public CalendarDate i;
    public CalendarDate j;
    public CalendarDate k;
    public CalendarDate l;
    public CalendarDate m;
    public String n;
    public int o;
    public int p;
    public b.b.m.f.f.a.b q;
    public PagerAdapter r;

    public class a extends ViewPager.SimpleOnPageChangeListener {
        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            CalendarViewPager calendarViewPager = CalendarViewPager.this;
            int i2 = calendarViewPager.f3837f;
            if (i2 > 0) {
                i += i2;
            }
            calendarViewPager.f3836e = i;
            calendarViewPager.q = calendarViewPager.f3833b.get(Integer.valueOf(i));
            if (CalendarViewPager.this.q == null) {
                return;
            }
            if (i == CalendarViewPager.this.o) {
                CalendarViewPager.this.q.e(CalendarViewPager.this.k, CalendarViewPager.this.n);
            }
            CalendarViewPager calendarViewPager2 = CalendarViewPager.this;
            if (i == calendarViewPager2.f3837f) {
                calendarViewPager2.q.g(CalendarViewPager.this.l);
            }
            CalendarViewPager calendarViewPager3 = CalendarViewPager.this;
            if (i == calendarViewPager3.g) {
                calendarViewPager3.q.f(CalendarViewPager.this.m);
            }
            if (CalendarViewPager.this.p > 0) {
                CalendarViewPager.this.q.i(CalendarViewPager.this.p);
            }
            CalendarViewPager.this.q.c(b.b.m.f.f.a.a.b(CalendarViewPager.this.q.a().year, CalendarViewPager.this.q.a().month));
            if (CalendarViewPager.this.f3835d != null) {
                CalendarViewPager.this.f3835d.a(CalendarViewPager.this.q.a().year, CalendarViewPager.this.q.a().month);
            }
        }
    }

    public class b implements b.b.m.f.f.a.d.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.m.f.f.a.b f3839a;

        public b(b.b.m.f.f.a.b bVar) {
            this.f3839a = bVar;
        }

        @Override // b.b.m.f.f.a.d.b
        public void a(int i, CalendarDate calendarDate) {
            if (CalendarViewPager.this.f3834c != null) {
                CalendarViewPager.this.f3834c.a(calendarDate.year, calendarDate.month, calendarDate.day);
            }
            for (b.b.m.f.f.a.b bVar : CalendarViewPager.this.f3833b.values()) {
                if (!bVar.equals(this.f3839a)) {
                    bVar.d(null);
                }
            }
        }
    }

    public CalendarViewPager(Context context) {
        super(context);
        this.f3836e = 0;
        this.f3837f = -1;
        this.g = -1;
        this.h = 0;
        this.p = -1;
        m();
    }

    public CalendarDate getCurDate() {
        return this.i;
    }

    public int getCurIndex() {
        int i = this.f3836e;
        int i2 = this.f3837f;
        return i2 > 0 ? i - i2 : i;
    }

    public CalendarDate k(int i) {
        int i2 = this.h - i;
        CalendarDate calendarDate = this.j;
        int i3 = calendarDate.year - (i2 / 12);
        int i4 = calendarDate.month - (i2 % 12);
        if (i4 <= 0) {
            i4 += 12;
            i3--;
        } else if (i4 > 12) {
            i4 -= 12;
            i3++;
        }
        return new CalendarDate(i3, i4, 1);
    }

    public int l(CalendarDate calendarDate) {
        if (calendarDate == null) {
            return this.f3836e;
        }
        CalendarDate calendarDate2 = this.j;
        int i = calendarDate2.month - calendarDate.month;
        int i2 = calendarDate2.year - calendarDate.year;
        if (i < 0) {
            i += 12;
            i2--;
        } else if (i > 12) {
            i -= 12;
            i2++;
        }
        return this.h - ((i2 * 12) + i);
    }

    public final void m() {
        this.f3833b = new HashMap<>();
    }

    public void n(CalendarDate calendarDate) {
        this.j = calendarDate;
        this.h = 1073741823;
        p(calendarDate, 1073741823);
        setOffscreenPageLimit(3);
    }

    public b.b.m.f.f.a.b o(Context context, CalendarDate calendarDate, int i) {
        b.b.m.f.f.a.b bVar = new b.b.m.f.f.a.b(context, new CalendarDate(calendarDate.year, calendarDate.month, 1));
        bVar.h(new b(bVar));
        this.f3833b.put(Integer.valueOf(i), bVar);
        return bVar;
    }

    public final void p(CalendarDate calendarDate, int i) {
        o(getContext(), calendarDate, i);
        CalendarPagerAdapter calendarPagerAdapter = new CalendarPagerAdapter(this);
        this.r = calendarPagerAdapter;
        setAdapter(calendarPagerAdapter);
        addOnPageChangeListener(new a());
        setCurrentItem(this.h, false);
        this.r.notifyDataSetChanged();
    }

    public void q(b.b.m.f.f.a.b bVar) {
        if (bVar == null) {
            return;
        }
        CalendarDate calendarDateA = bVar.a();
        CalendarDate calendarDate = this.i;
        if (calendarDate != null && calendarDate.year == calendarDateA.year && calendarDate.month == calendarDateA.month) {
            bVar.d(calendarDate);
        }
    }

    public void r(int i) {
        setCurrentItem(getCurIndex() + i, false);
    }

    public void setHintDate(CalendarDate calendarDate, String str) {
        this.k = calendarDate;
        this.n = str;
        int iL = l(new CalendarDate(calendarDate.year, calendarDate.month, 1));
        this.o = iL;
        b.b.m.f.f.a.b bVar = this.f3833b.get(Integer.valueOf(iL));
        if (bVar != null) {
            bVar.e(calendarDate, str);
        }
    }

    public void setMinMaxDate(CalendarDate calendarDate, CalendarDate calendarDate2) {
        this.l = calendarDate;
        this.m = calendarDate2;
        this.f3837f = l(calendarDate);
        this.g = l(calendarDate2);
        this.r.notifyDataSetChanged();
    }

    public void setOnDateChangedCallback(b.b.m.f.f.a.d.a aVar) {
        this.f3834c = aVar;
    }

    public void setOnPageSelectedCallback(c cVar) {
        this.f3835d = cVar;
    }

    public void setPageLimit(int i) {
        setOffscreenPageLimit(i);
    }

    public void setSelectedBackground(int i) {
        this.p = i;
    }

    public CalendarViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3836e = 0;
        this.f3837f = -1;
        this.g = -1;
        this.h = 0;
        this.p = -1;
        m();
    }
}
