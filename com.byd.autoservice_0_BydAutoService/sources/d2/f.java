package d2;

import android.content.Context;
import android.widget.BaseAdapter;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.Month;

/* JADX INFO: compiled from: MonthAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2723b = l.l().getMaximum(4);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Month f2724c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final DateSelector<?> f2725d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f2726e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final CalendarConstraints f2727f;

    public f(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.f2724c = month;
        this.f2725d = dateSelector;
        this.f2727f = calendarConstraints;
    }

    public int a(int i3) {
        return b() + (i3 - 1);
    }

    public int b() {
        return this.f2724c.i();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i3) {
        if (i3 < this.f2724c.i() || i3 > h()) {
            return null;
        }
        return Long.valueOf(this.f2724c.j(i(i3)));
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
        throw new UnsupportedOperationException("Method not decompiled: d2.f.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    public final void e(Context context) {
        if (this.f2726e == null) {
            this.f2726e = new b(context);
        }
    }

    public boolean f(int i3) {
        return i3 % this.f2724c.f2269f == 0;
    }

    public boolean g(int i3) {
        return (i3 + 1) % this.f2724c.f2269f == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f2724c.f2270g + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i3) {
        return i3 / this.f2724c.f2269f;
    }

    public int h() {
        return (this.f2724c.i() + this.f2724c.f2270g) - 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public int i(int i3) {
        return (i3 - this.f2724c.i()) + 1;
    }

    public boolean j(int i3) {
        return i3 >= b() && i3 <= h();
    }
}
