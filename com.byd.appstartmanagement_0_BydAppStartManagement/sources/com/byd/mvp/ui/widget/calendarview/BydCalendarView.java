package com.byd.mvp.ui.widget.calendarview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import b.a.d.e.c.a.d.c;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.R$string;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class BydCalendarView extends LinearLayout implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f742b = BydCalendarView.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f743c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f744d;
    public FrameLayout e;
    public CalendarViewPager f;
    public String g;
    public CalendarDate h;
    public int i;
    public int j;
    public int k;
    public int l;
    public b.a.d.e.c.a.d.a m;

    public class a implements c {
        public a() {
        }

        @Override // b.a.d.e.c.a.d.c
        public void a(int i, int i2) {
            BydCalendarView.this.f744d.setText(String.format(BydCalendarView.this.g, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public class b implements b.a.d.e.c.a.d.a {
        public b() {
        }

        @Override // b.a.d.e.c.a.d.a
        public void a(int i, int i2, int i3) {
            BydCalendarView.this.h.year = i;
            BydCalendarView.this.h.month = i2;
            BydCalendarView.this.h.day = i3;
            if (BydCalendarView.this.m != null) {
                BydCalendarView.this.m.a(i, i2, i3);
            }
        }
    }

    public BydCalendarView(Context context) {
        super(context);
        e(context);
    }

    public final void e(Context context) {
        int[] iArrC = b.a.d.e.c.a.c.c(new Date());
        this.h = new CalendarDate(iArrC[0], iArrC[1], iArrC[2]);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.view_byd_calendar, (ViewGroup) null);
        this.f743c = viewInflate.findViewById(R$id.rl_title_def_ev_calendar);
        this.f744d = (TextView) viewInflate.findViewById(R$id.tv_title_ev_calendar);
        this.e = (FrameLayout) viewInflate.findViewById(R$id.fl_title_customize_ev_calendar);
        this.f = (CalendarViewPager) viewInflate.findViewById(R$id.cdv_ev_calendar);
        viewInflate.findViewById(R$id.ib_pre_year_ev_calendar).setOnClickListener(this);
        viewInflate.findViewById(R$id.ib_next_year_ev_calendar).setOnClickListener(this);
        viewInflate.findViewById(R$id.tv_pre_month_ev_calendar).setOnClickListener(this);
        viewInflate.findViewById(R$id.tv_next_month_ev_calendar).setOnClickListener(this);
        addView(viewInflate);
        this.g = getContext().getResources().getString(R$string.year_month_date);
        this.f.setOnPageSelectedCallback(new a());
        this.f.setOnDateChangedCallback(new b());
        CalendarViewPager calendarViewPager = this.f;
        CalendarDate calendarDate = this.h;
        calendarViewPager.n(new CalendarDate(calendarDate.year, calendarDate.month, calendarDate.day));
        this.f744d.setText(String.format(this.g, Integer.valueOf(this.h.year), Integer.valueOf(this.h.month)));
    }

    public void f() {
        this.f.r(1);
    }

    public void g() {
        this.f.r(12);
    }

    public CalendarDate getCurDate() {
        return this.f.getCurDate();
    }

    public void h() {
        this.f.r(-1);
    }

    public void i() {
        this.f.r(-12);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R$id.ib_pre_year_ev_calendar || id == this.i) {
            i();
            return;
        }
        if (id == R$id.ib_next_year_ev_calendar || id == this.j) {
            g();
            return;
        }
        if (id == R$id.tv_pre_month_ev_calendar || id == this.k) {
            h();
        } else if (id == R$id.tv_next_month_ev_calendar || id == this.l) {
            f();
        }
    }

    public void setCustomTitleContent(View view, TextView textView, View view2, View view3, View view4, View view5) {
        if (view == null) {
            return;
        }
        this.f744d = textView;
        this.e.recomputeViewAttributes(null);
        this.e.addView(view);
        this.e.setVisibility(0);
        this.f743c.setVisibility(8);
        if (view2 != null) {
            this.i = view2.getId();
            view2.setOnClickListener(this);
        }
        if (view3 != null) {
            this.j = view3.getId();
            view3.setOnClickListener(this);
        }
        if (view4 != null) {
            this.k = view4.getId();
            view4.setOnClickListener(this);
        }
        if (view5 != null) {
            this.l = view5.getId();
            view5.setOnClickListener(this);
        }
        textView.setText(String.format(this.g, Integer.valueOf(this.h.year), Integer.valueOf(this.h.month)));
    }

    public void setHintDate(CalendarDate calendarDate, String str) {
        this.f.setHintDate(calendarDate, str);
    }

    public void setMinMaxDate(CalendarDate calendarDate, CalendarDate calendarDate2) {
        this.f.setMinMaxDate(calendarDate, calendarDate2);
    }

    public void setOnDateChangedCallback(b.a.d.e.c.a.d.a aVar) {
        this.m = aVar;
    }

    public void setPageLimit(int i) {
        this.f.setPageLimit(i);
    }

    public void setSelectedBackground(int i) {
        this.f.setSelectedBackground(i);
    }

    public BydCalendarView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        e(context);
    }

    public BydCalendarView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e(context);
    }
}
