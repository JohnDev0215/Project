package b.d.a.a.v;

import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.datepicker.MaterialCalendarGridView;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class p extends BaseAdapter {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f2110f = w.e().getMaximum(4);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o f2111a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d<?> f2112b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Collection<Long> f2113c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f2114d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f2115e;

    public p(o oVar, d<?> dVar, a aVar) {
        this.f2111a = oVar;
        this.f2112b = dVar;
        this.f2115e = aVar;
        this.f2113c = dVar.f();
    }

    public int a(int i) {
        return b() + (i - 1);
    }

    public int b() {
        return this.f2111a.c();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i) {
        if (i < this.f2111a.c() || i > d()) {
            return null;
        }
        o oVar = this.f2111a;
        int iC = (i - oVar.c()) + 1;
        Calendar calendarB = w.b(oVar.f2104a);
        calendarB.set(5, iC);
        return Long.valueOf(calendarB.getTimeInMillis());
    }

    public int d() {
        return (this.f2111a.c() + this.f2111a.f2108e) - 1;
    }

    public final void e(TextView textView, long j) {
        b bVar;
        if (textView == null) {
            return;
        }
        boolean z = false;
        if (this.f2115e.f2067c.g(j)) {
            textView.setEnabled(true);
            Iterator<Long> it = this.f2112b.f().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                long jLongValue = it.next().longValue();
                Calendar calendarE = w.e();
                calendarE.setTimeInMillis(j);
                long timeInMillis = w.b(calendarE).getTimeInMillis();
                Calendar calendarE2 = w.e();
                calendarE2.setTimeInMillis(jLongValue);
                if (timeInMillis == w.b(calendarE2).getTimeInMillis()) {
                    z = true;
                    break;
                }
            }
            if (z) {
                bVar = this.f2114d.f2078b;
            } else {
                long timeInMillis2 = w.d().getTimeInMillis();
                c cVar = this.f2114d;
                bVar = timeInMillis2 == j ? cVar.f2079c : cVar.f2077a;
            }
        } else {
            textView.setEnabled(false);
            bVar = this.f2114d.g;
        }
        bVar.b(textView);
    }

    public final void f(MaterialCalendarGridView materialCalendarGridView, long j) {
        Calendar calendarE = w.e();
        calendarE.setTimeInMillis(j);
        if (new o(calendarE).equals(this.f2111a)) {
            Calendar calendarB = w.b(this.f2111a.f2104a);
            calendarB.setTimeInMillis(j);
            e((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().a(calendarB.get(5)) - materialCalendarGridView.getFirstVisiblePosition()), j);
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return b() + this.f2111a.f2108e;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.f2111a.f2107d;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00b6  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View getView(int r9, android.view.View r10, android.view.ViewGroup r11) {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.d.a.a.v.p.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }
}
