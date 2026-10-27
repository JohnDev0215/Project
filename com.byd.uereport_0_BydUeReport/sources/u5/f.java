package u5;

import android.content.Context;
import android.widget.BaseAdapter;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.Month;

/* JADX INFO: compiled from: MonthAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends BaseAdapter {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f8265d = l.l().getMaximum(4);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Month f8266e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final DateSelector<?> f8267f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b f8268g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final CalendarConstraints f8269h;

    public f(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.f8266e = month;
        this.f8267f = dateSelector;
        this.f8269h = calendarConstraints;
    }

    public int a(int i8) {
        return b() + (i8 - 1);
    }

    public int b() {
        return this.f8266e.i();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i8) {
        if (i8 < this.f8266e.i() || i8 > h()) {
            return null;
        }
        return Long.valueOf(this.f8266e.j(i(i8)));
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
        throw new UnsupportedOperationException("Method not decompiled: u5.f.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    public final void e(Context context) {
        if (this.f8268g == null) {
            this.f8268g = new b(context);
        }
    }

    public boolean f(int i8) {
        return i8 % this.f8266e.f3854h == 0;
    }

    public boolean g(int i8) {
        return (i8 + 1) % this.f8266e.f3854h == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f8266e.f3855i + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i8) {
        return i8 / this.f8266e.f3854h;
    }

    public int h() {
        return (this.f8266e.i() + this.f8266e.f3855i) - 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public int i(int i8) {
        return (i8 - this.f8266e.i()) + 1;
    }

    public boolean j(int i8) {
        return i8 >= b() && i8 <= h();
    }
}
