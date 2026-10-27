package h4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.mvp.R$id;
import com.byd.mvp.R$layout;
import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import d4.d;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CalendarPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5341a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<CalendarDate> f5342b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j4.b f5343c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f5344d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RecyclerView f5345e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i4.a f5346f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public CalendarDate f5347g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CalendarDate f5348h;

    public b(Context context, CalendarDate calendarDate) {
        this.f5347g = calendarDate;
        b(context);
    }

    public CalendarDate a() {
        return this.f5347g;
    }

    public final void b(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.view_calendar_peger, (ViewGroup) null);
        this.f5344d = viewInflate;
        this.f5345e = (RecyclerView) viewInflate.findViewById(R$id.rcv_calendar_pager);
        this.f5342b = new ArrayList();
        i4.a aVar = new i4.a(this.f5342b, this.f5347g);
        this.f5346f = aVar;
        aVar.setOnItemClickedListener(this);
        this.f5345e.setLayoutManager(new GridLayoutManager(context, 7));
        this.f5345e.setAdapter(this.f5346f);
    }

    public void c(List<CalendarDate> list) {
        this.f5342b.clear();
        if (list != null && !list.isEmpty()) {
            this.f5342b.addAll(list);
        }
        this.f5346f.e(this.f5348h);
        this.f5346f.notifyDataSetChanged();
    }

    public void d(CalendarDate calendarDate) {
        if (this.f5348h == calendarDate && calendarDate == null) {
            return;
        }
        this.f5348h = calendarDate;
        this.f5346f.e(calendarDate);
        this.f5346f.notifyDataSetChanged();
    }

    public void e(CalendarDate calendarDate, String str) {
        i4.a aVar = this.f5346f;
        if (aVar != null) {
            aVar.a(calendarDate, str);
        }
    }

    public void f(CalendarDate calendarDate) {
        this.f5346f.b(calendarDate);
        this.f5346f.notifyDataSetChanged();
    }

    public void g(CalendarDate calendarDate) {
        this.f5346f.c(calendarDate);
        this.f5346f.notifyDataSetChanged();
    }

    public void h(j4.b bVar) {
        this.f5343c = bVar;
    }

    public void i(int i8) {
        this.f5346f.d(i8);
    }

    @Override // d4.d
    public void onItemClicked(int i8) {
        this.f5341a = i8;
        CalendarDate calendarDate = this.f5342b.get(i8);
        if (calendarDate.month != this.f5347g.month) {
            return;
        }
        this.f5348h = calendarDate;
        j4.b bVar = this.f5343c;
        if (bVar != null) {
            bVar.a(i8, calendarDate);
        }
    }
}
