package c.b.a.a.x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import b.h.m.t;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.datepicker.MaterialCalendarGridView;
import com.google.android.material.datepicker.Month;

/* JADX INFO: compiled from: MonthsPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final CalendarConstraints f4135c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final DateSelector<?> f4136d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final MaterialCalendar.k f4137e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4138f;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ MaterialCalendarGridView f4139b;

        public a(MaterialCalendarGridView materialCalendarGridView) {
            this.f4139b = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            if (this.f4139b.getAdapter().j(i2)) {
                j.this.f4137e.a(this.f4139b.getAdapter().getItem(i2).longValue());
            }
        }
    }

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public static class b extends RecyclerView.c0 {
        public final TextView t;
        public final MaterialCalendarGridView u;

        public b(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(c.b.a.a.f.month_title);
            this.t = textView;
            t.l0(textView, true);
            this.u = (MaterialCalendarGridView) linearLayout.findViewById(c.b.a.a.f.month_grid);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    public j(Context context, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints, MaterialCalendar.k kVar) {
        Month monthS = calendarConstraints.s();
        Month monthP = calendarConstraints.p();
        Month monthR = calendarConstraints.r();
        if (monthS.compareTo(monthR) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (monthR.compareTo(monthP) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.f4138f = (i.f4130b * MaterialCalendar.q(context)) + (f.F(context) ? MaterialCalendar.q(context) : 0);
        this.f4135c = calendarConstraints;
        this.f4136d = dateSelector;
        this.f4137e = kVar;
        u(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public void l(b bVar, int i2) {
        Month monthR = this.f4135c.s().r(i2);
        bVar.t.setText(monthR.p());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.u.findViewById(c.b.a.a.f.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !monthR.equals(materialCalendarGridView.getAdapter().f4131c)) {
            i iVar = new i(monthR, this.f4136d, this.f4135c);
            materialCalendarGridView.setNumColumns(monthR.f4699f);
            materialCalendarGridView.setAdapter((ListAdapter) iVar);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public b n(ViewGroup viewGroup, int i2) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(c.b.a.a.h.mtrl_calendar_month_labeled, viewGroup, false);
        if (!f.F(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f4138f));
        return new b(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f4135c.q();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long d(int i2) {
        return this.f4135c.s().r(i2).q();
    }

    public Month x(int i2) {
        return this.f4135c.s().r(i2);
    }

    public CharSequence y(int i2) {
        return x(i2).p();
    }

    public int z(Month month) {
        return this.f4135c.s().s(month);
    }
}
