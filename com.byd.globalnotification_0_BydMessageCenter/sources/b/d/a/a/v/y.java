package b.d.a.a.v;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class y extends RecyclerView.e<a> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f<?> f2128c;

    public static class a extends RecyclerView.b0 {
        public final TextView t;

        public a(TextView textView) {
            super(textView);
            this.t = textView;
        }
    }

    public y(f<?> fVar) {
        this.f2128c = fVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public int a() {
        return this.f2128c.Y.f2069e;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public void f(RecyclerView.b0 b0Var, int i) {
        a aVar = (a) b0Var;
        int i2 = this.f2128c.Y.f2065a.f2106c + i;
        String string = aVar.t.getContext().getString(b.d.a.a.i.mtrl_picker_navigate_to_year_description);
        aVar.t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(i2)));
        aVar.t.setContentDescription(String.format(string, Integer.valueOf(i2)));
        c cVar = this.f2128c.b0;
        Calendar calendarD = w.d();
        b bVar = calendarD.get(1) == i2 ? cVar.f2082f : cVar.f2080d;
        Iterator<Long> it = this.f2128c.X.f().iterator();
        while (it.hasNext()) {
            calendarD.setTimeInMillis(it.next().longValue());
            if (calendarD.get(1) == i2) {
                bVar = cVar.f2081e;
            }
        }
        bVar.b(aVar.t);
        aVar.t.setOnClickListener(new x(this, i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public RecyclerView.b0 h(ViewGroup viewGroup, int i) {
        return new a((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(b.d.a.a.h.mtrl_calendar_year, viewGroup, false));
    }

    public int j(int i) {
        return i - this.f2128c.Y.f2065a.f2106c;
    }
}
