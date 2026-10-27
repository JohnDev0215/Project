package defpackage;

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

/* JADX INFO: compiled from: MonthsPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class ii extends RecyclerView.g<b> {
    public final CalendarConstraints c;
    public final DateSelector<?> d;
    public final MaterialCalendar.k e;
    public final int f;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public class a implements AdapterView.OnItemClickListener {
        public final /* synthetic */ MaterialCalendarGridView b;

        public a(MaterialCalendarGridView materialCalendarGridView) {
            this.b = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.b.getAdapter().j(i)) {
                ii.this.e.a(this.b.getAdapter().getItem(i).longValue());
            }
        }
    }

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public static class b extends RecyclerView.c0 {
        public final TextView t;
        public final MaterialCalendarGridView u;

        public b(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R$id.month_title);
            this.t = textView;
            t9.k0(textView, true);
            this.u = (MaterialCalendarGridView) linearLayout.findViewById(R$id.month_grid);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    public ii(Context context, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints, MaterialCalendar.k kVar) {
        Month monthK = calendarConstraints.k();
        Month monthH = calendarConstraints.h();
        Month monthJ = calendarConstraints.j();
        if (monthK.compareTo(monthJ) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (monthJ.compareTo(monthH) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.f = (hi.b * MaterialCalendar.n(context)) + (gi.a(context) ? MaterialCalendar.n(context) : 0);
        this.c = calendarConstraints;
        this.d = dateSelector;
        this.e = kVar;
        s(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.c.i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long d(int i) {
        return this.c.k().m(i).l();
    }

    public Month v(int i) {
        return this.c.k().m(i);
    }

    public CharSequence w(int i) {
        return v(i).k();
    }

    public int x(Month month) {
        return this.c.k().n(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void j(b bVar, int i) {
        Month monthM = this.c.k().m(i);
        bVar.t.setText(monthM.k());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.u.findViewById(R$id.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !monthM.equals(materialCalendarGridView.getAdapter().c)) {
            hi hiVar = new hi(monthM, this.d, this.c);
            materialCalendarGridView.setNumColumns(monthM.f);
            materialCalendarGridView.setAdapter((ListAdapter) hiVar);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public b l(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!gi.a(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f));
        return new b(linearLayout, true);
    }
}
