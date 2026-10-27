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
import b.b.m.f.f.a.d.c;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.R$string;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class BydCalendarView extends LinearLayout implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3824a = BydCalendarView.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f3825b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f3826c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public FrameLayout f3827d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CalendarViewPager f3828e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3829f;
    public CalendarDate g;
    public int h;
    public int i;
    public int j;
    public int k;
    public b.b.m.f.f.a.d.a l;

    public class a implements c {
        public a() {
        }

        @Override // b.b.m.f.f.a.d.c
        public void a(int i, int i2) {
            BydCalendarView.this.f3826c.setText(String.format(BydCalendarView.this.f3829f, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public class b implements b.b.m.f.f.a.d.a {
        public b() {
        }

        @Override // b.b.m.f.f.a.d.a
        public void a(int i, int i2, int i3) {
            BydCalendarView.this.g.year = i;
            BydCalendarView.this.g.month = i2;
            BydCalendarView.this.g.day = i3;
            if (BydCalendarView.this.l != null) {
                BydCalendarView.this.l.a(i, i2, i3);
            }
        }
    }

    public BydCalendarView(Context context) {
        super(context);
        e(context);
    }

    public final void e(Context context) {
        int[] iArrC = b.b.m.f.f.a.c.c(new Date());
        this.g = new CalendarDate(iArrC[0], iArrC[1], iArrC[2]);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.view_byd_calendar, (ViewGroup) null);
        this.f3825b = viewInflate.findViewById(R$id.rl_title_def_ev_calendar);
        this.f3826c = (TextView) viewInflate.findViewById(R$id.tv_title_ev_calendar);
        this.f3827d = (FrameLayout) viewInflate.findViewById(R$id.fl_title_customize_ev_calendar);
        this.f3828e = (CalendarViewPager) viewInflate.findViewById(R$id.cdv_ev_calendar);
        viewInflate.findViewById(R$id.ib_pre_year_ev_calendar).setOnClickListener(this);
        viewInflate.findViewById(R$id.ib_next_year_ev_calendar).setOnClickListener(this);
        viewInflate.findViewById(R$id.tv_pre_month_ev_calendar).setOnClickListener(this);
        viewInflate.findViewById(R$id.tv_next_month_ev_calendar).setOnClickListener(this);
        addView(viewInflate);
        this.f3829f = getContext().getResources().getString(R$string.year_month_date);
        this.f3828e.setOnPageSelectedCallback(new a());
        this.f3828e.setOnDateChangedCallback(new b());
        CalendarViewPager calendarViewPager = this.f3828e;
        CalendarDate calendarDate = this.g;
        calendarViewPager.n(new CalendarDate(calendarDate.year, calendarDate.month, calendarDate.day));
        this.f3826c.setText(String.format(this.f3829f, Integer.valueOf(this.g.year), Integer.valueOf(this.g.month)));
    }

    public void f() {
        this.f3828e.r(1);
    }

    public void g() {
        this.f3828e.r(12);
    }

    public CalendarDate getCurDate() {
        return this.f3828e.getCurDate();
    }

    public void h() {
        this.f3828e.r(-1);
    }

    public void i() {
        this.f3828e.r(-12);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R$id.ib_pre_year_ev_calendar || id == this.h) {
            i();
            return;
        }
        if (id == R$id.ib_next_year_ev_calendar || id == this.i) {
            g();
            return;
        }
        if (id == R$id.tv_pre_month_ev_calendar || id == this.j) {
            h();
        } else if (id == R$id.tv_next_month_ev_calendar || id == this.k) {
            f();
        }
    }

    public void setCustomTitleContent(View view, TextView textView, View view2, View view3, View view4, View view5) {
        if (view == null) {
            return;
        }
        this.f3826c = textView;
        this.f3827d.recomputeViewAttributes(null);
        this.f3827d.addView(view);
        this.f3827d.setVisibility(0);
        this.f3825b.setVisibility(8);
        if (view2 != null) {
            this.h = view2.getId();
            view2.setOnClickListener(this);
        }
        if (view3 != null) {
            this.i = view3.getId();
            view3.setOnClickListener(this);
        }
        if (view4 != null) {
            this.j = view4.getId();
            view4.setOnClickListener(this);
        }
        if (view5 != null) {
            this.k = view5.getId();
            view5.setOnClickListener(this);
        }
        textView.setText(String.format(this.f3829f, Integer.valueOf(this.g.year), Integer.valueOf(this.g.month)));
    }

    public void setHintDate(CalendarDate calendarDate, String str) {
        this.f3828e.setHintDate(calendarDate, str);
    }

    public void setMinMaxDate(CalendarDate calendarDate, CalendarDate calendarDate2) {
        this.f3828e.setMinMaxDate(calendarDate, calendarDate2);
    }

    public void setOnDateChangedCallback(b.b.m.f.f.a.d.a aVar) {
        this.l = aVar;
    }

    public void setPageLimit(int i) {
        this.f3828e.setPageLimit(i);
    }

    public void setSelectedBackground(int i) {
        this.f3828e.setSelectedBackground(i);
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
