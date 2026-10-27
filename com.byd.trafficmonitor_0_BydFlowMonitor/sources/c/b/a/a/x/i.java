package c.b.a.a.x;

import android.content.Context;
import android.widget.BaseAdapter;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.Month;

/* JADX INFO: compiled from: MonthAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f4130b = o.r().getMaximum(4);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Month f4131c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final DateSelector<?> f4132d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f4133e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final CalendarConstraints f4134f;

    public i(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.f4131c = month;
        this.f4132d = dateSelector;
        this.f4134f = calendarConstraints;
    }

    public int a(int i2) {
        return b() + (i2 - 1);
    }

    public int b() {
        return this.f4131c.n();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i2) {
        if (i2 < this.f4131c.n() || i2 > h()) {
            return null;
        }
        return Long.valueOf(this.f4131c.o(i(i2)));
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
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.x.i.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    public final void e(Context context) {
        if (this.f4133e == null) {
            this.f4133e = new b(context);
        }
    }

    public boolean f(int i2) {
        return i2 % this.f4131c.f4699f == 0;
    }

    public boolean g(int i2) {
        return (i2 + 1) % this.f4131c.f4699f == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f4131c.f4700g + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2 / this.f4131c.f4699f;
    }

    public int h() {
        return (this.f4131c.n() + this.f4131c.f4700g) - 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public int i(int i2) {
        return (i2 - this.f4131c.n()) + 1;
    }

    public boolean j(int i2) {
        return i2 >= b() && i2 <= h();
    }
}
