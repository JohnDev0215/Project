package c.b.a.a.l;

import android.content.Context;
import android.widget.BaseAdapter;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.Month;

/* JADX INFO: compiled from: MonthAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f1722b = l.l().getMaximum(4);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Month f1723c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final DateSelector<?> f1724d;
    public b e;
    public final CalendarConstraints f;

    public f(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.f1723c = month;
        this.f1724d = dateSelector;
        this.f = calendarConstraints;
    }

    public int a(int i) {
        return b() + (i - 1);
    }

    public int b() {
        return this.f1723c.i();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i) {
        if (i < this.f1723c.i() || i > h()) {
            return null;
        }
        return Long.valueOf(this.f1723c.j(i(i)));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0061  */
    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.widget.TextView getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.l.f.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    public final void e(Context context) {
        if (this.e == null) {
            this.e = new b(context);
        }
    }

    public boolean f(int i) {
        return i % this.f1723c.f == 0;
    }

    public boolean g(int i) {
        return (i + 1) % this.f1723c.f == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f1723c.g + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.f1723c.f;
    }

    public int h() {
        return (this.f1723c.i() + this.f1723c.g) - 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public int i(int i) {
        return (i - this.f1723c.i()) + 1;
    }

    public boolean j(int i) {
        return i >= b() && i <= h();
    }
}
