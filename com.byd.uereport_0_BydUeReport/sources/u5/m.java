package u5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.datepicker.Month;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: YearGridAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class m extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MaterialCalendar<?> f8284a;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f8285d;

        public a(int i8) {
            this.f8285d = i8;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.this.f8284a.q(m.this.f8284a.j().f(Month.g(this.f8285d, m.this.f8284a.l().f3852f)));
            m.this.f8284a.r(MaterialCalendar.CalendarSelector.DAY);
        }
    }

    /* JADX INFO: compiled from: YearGridAdapter.java */
    public static class b extends RecyclerView.c0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f8287a;

        public b(TextView textView) {
            super(textView);
            this.f8287a = textView;
        }
    }

    public m(MaterialCalendar<?> materialCalendar) {
        this.f8284a = materialCalendar;
    }

    public final View.OnClickListener b(int i8) {
        return new a(i8);
    }

    public int c(int i8) {
        return i8 - this.f8284a.j().k().f3853g;
    }

    public int d(int i8) {
        return this.f8284a.j().k().f3853g + i8;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i8) {
        int iD = d(i8);
        String string = bVar.f8287a.getContext().getString(R$string.mtrl_picker_navigate_to_year_description);
        bVar.f8287a.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iD)));
        bVar.f8287a.setContentDescription(String.format(string, Integer.valueOf(iD)));
        u5.b bVarK = this.f8284a.k();
        Calendar calendarJ = l.j();
        u5.a aVar = calendarJ.get(1) == iD ? bVarK.f8255f : bVarK.f8253d;
        Iterator<Long> it = this.f8284a.m().c().iterator();
        while (it.hasNext()) {
            calendarJ.setTimeInMillis(it.next().longValue());
            if (calendarJ.get(1) == iD) {
                aVar = bVarK.f8254e;
            }
        }
        aVar.d(bVar.f8287a);
        bVar.f8287a.setOnClickListener(b(iD));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i8) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_year, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        return this.f8284a.j().l();
    }
}
