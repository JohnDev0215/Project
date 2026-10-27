package d2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.datepicker.MaterialCalendarGridView;
import com.google.android.material.datepicker.Month;
import l0.t;

/* JADX INFO: compiled from: MonthsPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final CalendarConstraints f2728c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final DateSelector<?> f2729d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final MaterialCalendar.k f2730e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2731f;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ MaterialCalendarGridView f2732b;

        public a(MaterialCalendarGridView materialCalendarGridView) {
            this.f2732b = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j3) {
            if (this.f2732b.getAdapter().j(i3)) {
                g.this.f2730e.a(this.f2732b.getAdapter().getItem(i3).longValue());
            }
        }
    }

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public static class b extends RecyclerView.c0 {

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public final TextView f2734t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public final MaterialCalendarGridView f2735u;

        public b(LinearLayout linearLayout, boolean z3) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R$id.month_title);
            this.f2734t = textView;
            t.k0(textView, true);
            this.f2735u = (MaterialCalendarGridView) linearLayout.findViewById(R$id.month_grid);
            if (z3) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    public g(Context context, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints, MaterialCalendar.k kVar) {
        Month monthK = calendarConstraints.k();
        Month monthH = calendarConstraints.h();
        Month monthJ = calendarConstraints.j();
        if (monthK.compareTo(monthJ) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (monthJ.compareTo(monthH) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.f2731f = (f.f2723b * MaterialCalendar.n(context)) + (e.a(context) ? MaterialCalendar.n(context) : 0);
        this.f2728c = calendarConstraints;
        this.f2729d = dateSelector;
        this.f2730e = kVar;
        s(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f2728c.i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long d(int i3) {
        return this.f2728c.k().m(i3).l();
    }

    public Month v(int i3) {
        return this.f2728c.k().m(i3);
    }

    public CharSequence w(int i3) {
        return v(i3).k();
    }

    public int x(Month month) {
        return this.f2728c.k().n(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void j(b bVar, int i3) {
        Month monthM = this.f2728c.k().m(i3);
        bVar.f2734t.setText(monthM.k());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.f2735u.findViewById(R$id.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !monthM.equals(materialCalendarGridView.getAdapter().f2724c)) {
            f fVar = new f(monthM, this.f2729d, this.f2728c);
            materialCalendarGridView.setNumColumns(monthM.f2269f);
            materialCalendarGridView.setAdapter((ListAdapter) fVar);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public b l(ViewGroup viewGroup, int i3) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!e.a(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f2731f));
        return new b(linearLayout, true);
    }
}
