package d2;

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
    public final MaterialCalendar<?> f2742c;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2743b;

        public a(int i3) {
            this.f2743b = i3;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.this.f2742c.q(m.this.f2742c.j().f(Month.g(this.f2743b, m.this.f2742c.l().f2267d)));
            m.this.f2742c.r(MaterialCalendar.CalendarSelector.DAY);
        }
    }

    /* JADX INFO: compiled from: YearGridAdapter.java */
    public static class b extends RecyclerView.c0 {

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public final TextView f2745t;

        public b(TextView textView) {
            super(textView);
            this.f2745t = textView;
        }
    }

    public m(MaterialCalendar<?> materialCalendar) {
        this.f2742c = materialCalendar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f2742c.j().l();
    }

    public final View.OnClickListener v(int i3) {
        return new a(i3);
    }

    public int w(int i3) {
        return i3 - this.f2742c.j().k().f2268e;
    }

    public int x(int i3) {
        return this.f2742c.j().k().f2268e + i3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void j(b bVar, int i3) {
        int iX = x(i3);
        String string = bVar.f2745t.getContext().getString(R$string.mtrl_picker_navigate_to_year_description);
        bVar.f2745t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iX)));
        bVar.f2745t.setContentDescription(String.format(string, Integer.valueOf(iX)));
        d2.b bVarK = this.f2742c.k();
        Calendar calendarJ = l.j();
        d2.a aVar = calendarJ.get(1) == iX ? bVarK.f2713f : bVarK.f2711d;
        Iterator<Long> it = this.f2742c.m().e().iterator();
        while (it.hasNext()) {
            calendarJ.setTimeInMillis(it.next().longValue());
            if (calendarJ.get(1) == iX) {
                aVar = bVarK.f2712e;
            }
        }
        aVar.d(bVar.f2745t);
        bVar.f2745t.setOnClickListener(v(iX));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public b l(ViewGroup viewGroup, int i3) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_year, viewGroup, false));
    }
}
