package com.byd.mvp.ui.widget.calendarview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.R$string;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import j4.c;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class BydCalendarView extends LinearLayout implements View.OnClickListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f2946d = BydCalendarView.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f2947e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f2948f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public FrameLayout f2949g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CalendarViewPager f2950h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f2951i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CalendarDate f2952j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2953k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2954l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2955m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2956n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public j4.a f2957o;

    public class a implements c {
        public a() {
        }

        @Override // j4.c
        public void a(int i8, int i9) {
            BydCalendarView.this.f2948f.setText(String.format(BydCalendarView.this.f2951i, Integer.valueOf(i8), Integer.valueOf(i9)));
        }
    }

    public class b implements j4.a {
        public b() {
        }

        @Override // j4.a
        public void a(int i8, int i9, int i10) {
            BydCalendarView.this.f2952j.year = i8;
            BydCalendarView.this.f2952j.month = i9;
            BydCalendarView.this.f2952j.day = i10;
            if (BydCalendarView.this.f2957o != null) {
                BydCalendarView.this.f2957o.a(i8, i9, i10);
            }
        }
    }

    public BydCalendarView(Context context) {
        super(context);
        e(context);
    }

    public final void e(Context context) {
        int[] iArrC = h4.c.c(new Date());
        this.f2952j = new CalendarDate(iArrC[0], iArrC[1], iArrC[2]);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.view_byd_calendar, (ViewGroup) null);
        this.f2947e = viewInflate.findViewById(R$id.rl_title_def_ev_calendar);
        this.f2948f = (TextView) viewInflate.findViewById(R$id.tv_title_ev_calendar);
        this.f2949g = (FrameLayout) viewInflate.findViewById(R$id.fl_title_customize_ev_calendar);
        this.f2950h = (CalendarViewPager) viewInflate.findViewById(R$id.cdv_ev_calendar);
        viewInflate.findViewById(R$id.ib_pre_year_ev_calendar).setOnClickListener(this);
        viewInflate.findViewById(R$id.ib_next_year_ev_calendar).setOnClickListener(this);
        viewInflate.findViewById(R$id.tv_pre_month_ev_calendar).setOnClickListener(this);
        viewInflate.findViewById(R$id.tv_next_month_ev_calendar).setOnClickListener(this);
        addView(viewInflate);
        this.f2951i = getContext().getResources().getString(R$string.year_month_date);
        this.f2950h.setOnPageSelectedCallback(new a());
        this.f2950h.setOnDateChangedCallback(new b());
        CalendarViewPager calendarViewPager = this.f2950h;
        CalendarDate calendarDate = this.f2952j;
        calendarViewPager.f0(new CalendarDate(calendarDate.year, calendarDate.month, calendarDate.day));
        this.f2948f.setText(String.format(this.f2951i, Integer.valueOf(this.f2952j.year), Integer.valueOf(this.f2952j.month)));
    }

    public void f() {
        this.f2950h.j0(1);
    }

    public void g() {
        this.f2950h.j0(12);
    }

    public CalendarDate getCurDate() {
        return this.f2950h.getCurDate();
    }

    public void h() {
        this.f2950h.j0(-1);
    }

    public void i() {
        this.f2950h.j0(-12);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R$id.ib_pre_year_ev_calendar || id == this.f2953k) {
            i();
            return;
        }
        if (id == R$id.ib_next_year_ev_calendar || id == this.f2954l) {
            g();
            return;
        }
        if (id == R$id.tv_pre_month_ev_calendar || id == this.f2955m) {
            h();
        } else if (id == R$id.tv_next_month_ev_calendar || id == this.f2956n) {
            f();
        }
    }

    public void setOnDateChangedCallback(j4.a aVar) {
        this.f2957o = aVar;
    }

    public void setPageLimit(int i8) {
        this.f2950h.setPageLimit(i8);
    }

    public void setSelectedBackground(int i8) {
        this.f2950h.setSelectedBackground(i8);
    }

    public BydCalendarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e(context);
    }

    public BydCalendarView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        e(context);
    }
}
