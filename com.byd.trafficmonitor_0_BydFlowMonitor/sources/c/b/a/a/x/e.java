package c.b.a.a.x;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: compiled from: DaysOfWeekAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f4119b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Calendar f4120c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4121d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4122e;

    static {
        f4119b = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public e() {
        Calendar calendarR = o.r();
        this.f4120c = calendarR;
        this.f4121d = calendarR.getMaximum(7);
        this.f4122e = calendarR.getFirstDayOfWeek();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i2) {
        if (i2 >= this.f4121d) {
            return null;
        }
        return Integer.valueOf(b(i2));
    }

    public final int b(int i2) {
        int i3 = i2 + this.f4122e;
        int i4 = this.f4121d;
        return i3 > i4 ? i3 - i4 : i3;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f4121d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public View getView(int i2, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(c.b.a.a.h.mtrl_calendar_day_of_week, viewGroup, false);
        }
        this.f4120c.set(7, b(i2));
        textView.setText(this.f4120c.getDisplayName(7, f4119b, Locale.getDefault()));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(c.b.a.a.j.mtrl_picker_day_of_week_column_header), this.f4120c.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
