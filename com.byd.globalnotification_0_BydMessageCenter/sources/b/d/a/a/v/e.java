package b.d.a.a.v;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class e extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Calendar f2083a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2084b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2085c;

    public e() {
        Calendar calendarE = w.e();
        this.f2083a = calendarE;
        this.f2084b = calendarE.getMaximum(7);
        this.f2085c = this.f2083a.getFirstDayOfWeek();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f2084b;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        int i2 = this.f2084b;
        if (i >= i2) {
            return null;
        }
        int i3 = i + this.f2085c;
        if (i3 > i2) {
            i3 -= i2;
        }
        return Integer.valueOf(i3);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(b.d.a.a.h.mtrl_calendar_day_of_week, viewGroup, false);
        }
        Calendar calendar = this.f2083a;
        int i2 = i + this.f2085c;
        int i3 = this.f2084b;
        if (i2 > i3) {
            i2 -= i3;
        }
        calendar.set(7, i2);
        textView.setText(this.f2083a.getDisplayName(7, 4, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(b.d.a.a.i.mtrl_picker_day_of_week_column_header), this.f2083a.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
