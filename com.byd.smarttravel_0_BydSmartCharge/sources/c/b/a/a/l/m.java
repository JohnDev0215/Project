package c.b.a.a.l;

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
/* JADX INFO: loaded from: classes.dex */
public class m extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MaterialCalendar<?> f1734c;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1735b;

        public a(int i) {
            this.f1735b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.this.f1734c.q(m.this.f1734c.j().f(Month.g(this.f1735b, m.this.f1734c.l().f2168d)));
            m.this.f1734c.r(MaterialCalendar.CalendarSelector.DAY);
        }
    }

    /* JADX INFO: compiled from: YearGridAdapter.java */
    public static class b extends RecyclerView.c0 {
        public final TextView t;

        public b(TextView textView) {
            super(textView);
            this.t = textView;
        }
    }

    public m(MaterialCalendar<?> materialCalendar) {
        this.f1734c = materialCalendar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f1734c.j().l();
    }

    public final View.OnClickListener v(int i) {
        return new a(i);
    }

    public int w(int i) {
        return i - this.f1734c.j().k().e;
    }

    public int x(int i) {
        return this.f1734c.j().k().e + i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void j(b bVar, int i) {
        int iX = x(i);
        String string = bVar.t.getContext().getString(R$string.mtrl_picker_navigate_to_year_description);
        bVar.t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iX)));
        bVar.t.setContentDescription(String.format(string, Integer.valueOf(iX)));
        c.b.a.a.l.b bVarK = this.f1734c.k();
        Calendar calendarJ = l.j();
        c.b.a.a.l.a aVar = calendarJ.get(1) == iX ? bVarK.f : bVarK.f1715d;
        Iterator<Long> it = this.f1734c.m().e().iterator();
        while (it.hasNext()) {
            calendarJ.setTimeInMillis(it.next().longValue());
            if (calendarJ.get(1) == iX) {
                aVar = bVarK.e;
            }
        }
        aVar.d(bVar.t);
        bVar.t.setOnClickListener(v(iX));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public b l(ViewGroup viewGroup, int i) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_year, viewGroup, false));
    }
}
