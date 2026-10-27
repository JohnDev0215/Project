package c.b.a.a.x;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.datepicker.Month;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: YearGridAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MaterialCalendar<?> f4146c;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4147b;

        public a(int i2) {
            this.f4147b = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            p.this.f4146c.u(p.this.f4146c.m().n(Month.k(this.f4147b, p.this.f4146c.o().f4697d)));
            p.this.f4146c.v(MaterialCalendar.CalendarSelector.DAY);
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

    public p(MaterialCalendar<?> materialCalendar) {
        this.f4146c = materialCalendar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public void l(b bVar, int i2) {
        int iZ = z(i2);
        String string = bVar.t.getContext().getString(c.b.a.a.j.mtrl_picker_navigate_to_year_description);
        bVar.t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iZ)));
        bVar.t.setContentDescription(String.format(string, Integer.valueOf(iZ)));
        c.b.a.a.x.b bVarN = this.f4146c.n();
        Calendar calendarP = o.p();
        c.b.a.a.x.a aVar = calendarP.get(1) == iZ ? bVarN.f4111f : bVarN.f4109d;
        Iterator<Long> it = this.f4146c.p().i().iterator();
        while (it.hasNext()) {
            calendarP.setTimeInMillis(it.next().longValue());
            if (calendarP.get(1) == iZ) {
                aVar = bVarN.f4110e;
            }
        }
        aVar.d(bVar.t);
        bVar.t.setOnClickListener(x(iZ));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public b n(ViewGroup viewGroup, int i2) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(c.b.a.a.h.mtrl_calendar_year, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f4146c.m().t();
    }

    public final View.OnClickListener x(int i2) {
        return new a(i2);
    }

    public int y(int i2) {
        return i2 - this.f4146c.m().s().f4698e;
    }

    public int z(int i2) {
        return this.f4146c.m().s().f4698e + i2;
    }
}
