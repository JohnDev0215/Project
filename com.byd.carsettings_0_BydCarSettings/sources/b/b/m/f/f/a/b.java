package b.b.m.f.f.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.c.a.e;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.ui.widget.calendarview.adapter.CalendarDayAdapter;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CalendarPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2153a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<CalendarDate> f2154b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.m.f.f.a.d.b f2155c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f2156d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RecyclerView f2157e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CalendarDayAdapter f2158f;
    public CalendarDate g;
    public CalendarDate h;

    public b(Context context, CalendarDate calendarDate) {
        this.g = calendarDate;
        b(context);
    }

    public CalendarDate a() {
        return this.g;
    }

    public final void b(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.view_calendar_peger, (ViewGroup) null);
        this.f2156d = viewInflate;
        this.f2157e = (RecyclerView) viewInflate.findViewById(R$id.rcv_calendar_pager);
        this.f2154b = new ArrayList();
        CalendarDayAdapter calendarDayAdapter = new CalendarDayAdapter(this.f2154b, this.g);
        this.f2158f = calendarDayAdapter;
        calendarDayAdapter.setOnItemClickedListener(this);
        this.f2157e.setLayoutManager(new GridLayoutManager(context, 7));
        this.f2157e.setAdapter(this.f2158f);
    }

    public void c(List<CalendarDate> list) {
        this.f2154b.clear();
        if (list != null && !list.isEmpty()) {
            this.f2154b.addAll(list);
        }
        this.f2158f.f(this.h);
        this.f2158f.notifyDataSetChanged();
    }

    public void d(CalendarDate calendarDate) {
        if (this.h == calendarDate && calendarDate == null) {
            return;
        }
        this.h = calendarDate;
        this.f2158f.f(calendarDate);
        this.f2158f.notifyDataSetChanged();
    }

    public void e(CalendarDate calendarDate, String str) {
        CalendarDayAdapter calendarDayAdapter = this.f2158f;
        if (calendarDayAdapter != null) {
            calendarDayAdapter.a(calendarDate, str);
        }
    }

    public void f(CalendarDate calendarDate) {
        this.f2158f.b(calendarDate);
        this.f2158f.notifyDataSetChanged();
    }

    public void g(CalendarDate calendarDate) {
        this.f2158f.d(calendarDate);
        this.f2158f.notifyDataSetChanged();
    }

    public void h(b.b.m.f.f.a.d.b bVar) {
        this.f2155c = bVar;
    }

    public void i(int i) {
        this.f2158f.e(i);
    }

    @Override // b.b.m.f.c.a.e
    public void onItemClicked(int i) {
        this.f2153a = i;
        CalendarDate calendarDate = this.f2154b.get(i);
        if (calendarDate.month != this.g.month) {
            return;
        }
        this.h = calendarDate;
        b.b.m.f.f.a.d.b bVar = this.f2155c;
        if (bVar != null) {
            bVar.a(i, calendarDate);
        }
    }
}
