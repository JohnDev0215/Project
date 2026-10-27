package defpackage;

import android.content.Context;
import android.widget.BaseAdapter;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.Month;

/* JADX INFO: compiled from: MonthAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class hi extends BaseAdapter {
    public static final int b = ni.l().getMaximum(4);
    public final Month c;
    public final DateSelector<?> d;
    public di e;
    public final CalendarConstraints f;

    public hi(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.c = month;
        this.d = dateSelector;
        this.f = calendarConstraints;
    }

    public int a(int i) {
        return b() + (i - 1);
    }

    public int b() {
        return this.c.i();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i) {
        if (i < this.c.i() || i > h()) {
            return null;
        }
        return Long.valueOf(this.c.j(i(i)));
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hi.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    public final void e(Context context) {
        if (this.e == null) {
            this.e = new di(context);
        }
    }

    public boolean f(int i) {
        return i % this.c.f == 0;
    }

    public boolean g(int i) {
        return (i + 1) % this.c.f == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c.g + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.c.f;
    }

    public int h() {
        return (this.c.i() + this.c.g) - 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public int i(int i) {
        return (i - this.c.i()) + 1;
    }

    public boolean j(int i) {
        return i >= b() && i <= h();
    }
}
