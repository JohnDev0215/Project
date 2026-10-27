package u5;

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
import o0.t;

/* JADX INFO: compiled from: MonthsPagerAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CalendarConstraints f8270a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DateSelector<?> f8271b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MaterialCalendar.k f8272c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f8273d;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ MaterialCalendarGridView f8274d;

        public a(MaterialCalendarGridView materialCalendarGridView) {
            this.f8274d = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i8, long j8) {
            if (this.f8274d.getAdapter().j(i8)) {
                g.this.f8272c.a(this.f8274d.getAdapter().getItem(i8).longValue());
            }
        }
    }

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public static class b extends RecyclerView.c0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f8276a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final MaterialCalendarGridView f8277b;

        public b(LinearLayout linearLayout, boolean z7) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R$id.month_title);
            this.f8276a = textView;
            t.m0(textView, true);
            this.f8277b = (MaterialCalendarGridView) linearLayout.findViewById(R$id.month_grid);
            if (z7) {
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
        this.f8273d = (f.f8265d * MaterialCalendar.n(context)) + (e.a(context) ? MaterialCalendar.n(context) : 0);
        this.f8270a = calendarConstraints;
        this.f8271b = dateSelector;
        this.f8272c = kVar;
        setHasStableIds(true);
    }

    public Month b(int i8) {
        return this.f8270a.k().m(i8);
    }

    public CharSequence c(int i8) {
        return b(i8).k();
    }

    public int d(Month month) {
        return this.f8270a.k().n(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i8) {
        Month monthM = this.f8270a.k().m(i8);
        bVar.f8276a.setText(monthM.k());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.f8277b.findViewById(R$id.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !monthM.equals(materialCalendarGridView.getAdapter().f8266e)) {
            f fVar = new f(monthM, this.f8271b, this.f8270a);
            materialCalendarGridView.setNumColumns(monthM.f3854h);
            materialCalendarGridView.setAdapter((ListAdapter) fVar);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i8) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!e.a(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f8273d));
        return new b(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        return this.f8270a.i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long getItemId(int i8) {
        return this.f8270a.k().m(i8).l();
    }
}
