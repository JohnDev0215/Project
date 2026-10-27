package d2;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: compiled from: DaysOfWeekAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2716b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Calendar f2717c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2718d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2719e;

    static {
        f2716b = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public d() {
        Calendar calendarL = l.l();
        this.f2717c = calendarL;
        this.f2718d = calendarL.getMaximum(7);
        this.f2719e = calendarL.getFirstDayOfWeek();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i3) {
        if (i3 >= this.f2718d) {
            return null;
        }
        return Integer.valueOf(b(i3));
    }

    public final int b(int i3) {
        int i4 = i3 + this.f2719e;
        int i5 = this.f2718d;
        return i4 > i5 ? i4 - i5 : i4;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f2718d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i3) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public View getView(int i3, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_day_of_week, viewGroup, false);
        }
        this.f2717c.set(7, b(i3));
        textView.setText(this.f2717c.getDisplayName(7, f2716b, Locale.getDefault()));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(R$string.mtrl_picker_day_of_week_column_header), this.f2717c.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
