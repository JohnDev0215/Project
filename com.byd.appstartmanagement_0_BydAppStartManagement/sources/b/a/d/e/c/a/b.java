package b.a.d.e.c.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.ui.widget.calendarview.adapter.CalendarDayAdapter;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CalendarPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements b.a.d.e.a.a.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f345a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<CalendarDate> f346b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.a.d.e.c.a.d.b f347c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f348d;
    public RecyclerView e;
    public CalendarDayAdapter f;
    public CalendarDate g;
    public CalendarDate h;

    public b(Context context, CalendarDate calendarDate) {
        this.g = calendarDate;
        c(context);
    }

    @Override // b.a.d.e.a.a.c
    public void a(int i) {
        this.f345a = i;
        CalendarDate calendarDate = this.f346b.get(i);
        if (calendarDate.month != this.g.month) {
            return;
        }
        this.h = calendarDate;
        b.a.d.e.c.a.d.b bVar = this.f347c;
        if (bVar != null) {
            bVar.a(i, calendarDate);
        }
    }

    public CalendarDate b() {
        return this.g;
    }

    public final void c(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.view_calendar_peger, (ViewGroup) null);
        this.f348d = viewInflate;
        this.e = (RecyclerView) viewInflate.findViewById(R$id.rcv_calendar_pager);
        this.f346b = new ArrayList();
        CalendarDayAdapter calendarDayAdapter = new CalendarDayAdapter(this.f346b, this.g);
        this.f = calendarDayAdapter;
        calendarDayAdapter.setOnItemClickedListener(this);
        this.e.setLayoutManager(new GridLayoutManager(context, 7));
        this.e.setAdapter(this.f);
    }

    public void d(List<CalendarDate> list) {
        this.f346b.clear();
        if (list != null && !list.isEmpty()) {
            this.f346b.addAll(list);
        }
        this.f.m(this.h);
        this.f.notifyDataSetChanged();
    }

    public void e(CalendarDate calendarDate) {
        if (this.h == calendarDate && calendarDate == null) {
            return;
        }
        this.h = calendarDate;
        this.f.m(calendarDate);
        this.f.notifyDataSetChanged();
    }

    public void f(CalendarDate calendarDate, String str) {
        CalendarDayAdapter calendarDayAdapter = this.f;
        if (calendarDayAdapter != null) {
            calendarDayAdapter.i(calendarDate, str);
        }
    }

    public void g(CalendarDate calendarDate) {
        this.f.j(calendarDate);
        this.f.notifyDataSetChanged();
    }

    public void h(CalendarDate calendarDate) {
        this.f.k(calendarDate);
        this.f.notifyDataSetChanged();
    }

    public void i(b.a.d.e.c.a.d.b bVar) {
        this.f347c = bVar;
    }

    public void j(int i) {
        this.f.l(i);
    }
}
