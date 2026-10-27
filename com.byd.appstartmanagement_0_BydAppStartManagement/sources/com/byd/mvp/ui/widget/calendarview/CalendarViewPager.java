package com.byd.mvp.ui.widget.calendarview;

import android.content.Context;
import android.util.AttributeSet;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import b.a.d.e.c.a.d.c;
import com.byd.mvp.ui.widget.calendarview.adapter.CalendarPagerAdapter;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class CalendarViewPager extends ViewPager {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f747b = CalendarViewPager.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public HashMap<Integer, b.a.d.e.c.a.b> f748c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.a.d.e.c.a.d.a f749d;
    public c e;
    public int f;
    public int g;
    public int h;
    public int i;
    public CalendarDate j;
    public CalendarDate k;
    public CalendarDate l;
    public CalendarDate m;
    public CalendarDate n;
    public String o;
    public int p;
    public int q;
    public b.a.d.e.c.a.b r;
    public PagerAdapter s;

    public class a extends ViewPager.SimpleOnPageChangeListener {
        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            CalendarViewPager calendarViewPager = CalendarViewPager.this;
            int i2 = calendarViewPager.g;
            if (i2 > 0) {
                i += i2;
            }
            calendarViewPager.f = i;
            calendarViewPager.r = calendarViewPager.f748c.get(Integer.valueOf(i));
            if (CalendarViewPager.this.r == null) {
                return;
            }
            if (i == CalendarViewPager.this.p) {
                CalendarViewPager.this.r.f(CalendarViewPager.this.l, CalendarViewPager.this.o);
            }
            CalendarViewPager calendarViewPager2 = CalendarViewPager.this;
            if (i == calendarViewPager2.g) {
                calendarViewPager2.r.h(CalendarViewPager.this.m);
            }
            CalendarViewPager calendarViewPager3 = CalendarViewPager.this;
            if (i == calendarViewPager3.h) {
                calendarViewPager3.r.g(CalendarViewPager.this.n);
            }
            if (CalendarViewPager.this.q > 0) {
                CalendarViewPager.this.r.j(CalendarViewPager.this.q);
            }
            CalendarViewPager.this.r.d(b.a.d.e.c.a.a.b(CalendarViewPager.this.r.b().year, CalendarViewPager.this.r.b().month));
            if (CalendarViewPager.this.e != null) {
                CalendarViewPager.this.e.a(CalendarViewPager.this.r.b().year, CalendarViewPager.this.r.b().month);
            }
        }
    }

    public class b implements b.a.d.e.c.a.d.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.a.d.e.c.a.b f751a;

        public b(b.a.d.e.c.a.b bVar) {
            this.f751a = bVar;
        }

        @Override // b.a.d.e.c.a.d.b
        public void a(int i, CalendarDate calendarDate) {
            if (CalendarViewPager.this.f749d != null) {
                CalendarViewPager.this.f749d.a(calendarDate.year, calendarDate.month, calendarDate.day);
            }
            for (b.a.d.e.c.a.b bVar : CalendarViewPager.this.f748c.values()) {
                if (!bVar.equals(this.f751a)) {
                    bVar.e(null);
                }
            }
        }
    }

    public CalendarViewPager(Context context) {
        super(context);
        this.f = 0;
        this.g = -1;
        this.h = -1;
        this.i = 0;
        this.q = -1;
        m();
    }

    public CalendarDate getCurDate() {
        return this.j;
    }

    public int getCurIndex() {
        int i = this.f;
        int i2 = this.g;
        return i2 > 0 ? i - i2 : i;
    }

    public CalendarDate k(int i) {
        int i2 = this.i - i;
        CalendarDate calendarDate = this.k;
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
            return this.f;
        }
        CalendarDate calendarDate2 = this.k;
        int i = calendarDate2.month - calendarDate.month;
        int i2 = calendarDate2.year - calendarDate.year;
        if (i < 0) {
            i += 12;
            i2--;
        } else if (i > 12) {
            i -= 12;
            i2++;
        }
        return this.i - ((i2 * 12) + i);
    }

    public final void m() {
        this.f748c = new HashMap<>();
    }

    public void n(CalendarDate calendarDate) {
        this.k = calendarDate;
        this.i = 1073741823;
        p(calendarDate, 1073741823);
        setOffscreenPageLimit(3);
    }

    public b.a.d.e.c.a.b o(Context context, CalendarDate calendarDate, int i) {
        b.a.d.e.c.a.b bVar = new b.a.d.e.c.a.b(context, new CalendarDate(calendarDate.year, calendarDate.month, 1));
        bVar.i(new b(bVar));
        this.f748c.put(Integer.valueOf(i), bVar);
        return bVar;
    }

    public final void p(CalendarDate calendarDate, int i) {
        o(getContext(), calendarDate, i);
        CalendarPagerAdapter calendarPagerAdapter = new CalendarPagerAdapter(this);
        this.s = calendarPagerAdapter;
        setAdapter(calendarPagerAdapter);
        addOnPageChangeListener(new a());
        setCurrentItem(this.i, false);
        this.s.notifyDataSetChanged();
    }

    public void q(b.a.d.e.c.a.b bVar) {
        if (bVar == null) {
            return;
        }
        CalendarDate calendarDateB = bVar.b();
        CalendarDate calendarDate = this.j;
        if (calendarDate != null && calendarDate.year == calendarDateB.year && calendarDate.month == calendarDateB.month) {
            bVar.e(calendarDate);
        }
    }

    public void r(int i) {
        setCurrentItem(getCurIndex() + i, false);
    }

    public void setHintDate(CalendarDate calendarDate, String str) {
        this.l = calendarDate;
        this.o = str;
        int iL = l(new CalendarDate(calendarDate.year, calendarDate.month, 1));
        this.p = iL;
        b.a.d.e.c.a.b bVar = this.f748c.get(Integer.valueOf(iL));
        if (bVar != null) {
            bVar.f(calendarDate, str);
        }
    }

    public void setMinMaxDate(CalendarDate calendarDate, CalendarDate calendarDate2) {
        this.m = calendarDate;
        this.n = calendarDate2;
        this.g = l(calendarDate);
        this.h = l(calendarDate2);
        this.s.notifyDataSetChanged();
    }

    public void setOnDateChangedCallback(b.a.d.e.c.a.d.a aVar) {
        this.f749d = aVar;
    }

    public void setOnPageSelectedCallback(c cVar) {
        this.e = cVar;
    }

    public void setPageLimit(int i) {
        setOffscreenPageLimit(i);
    }

    public void setSelectedBackground(int i) {
        this.q = i;
    }

    public CalendarViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = 0;
        this.g = -1;
        this.h = -1;
        this.i = 0;
        this.q = -1;
        m();
    }
}
