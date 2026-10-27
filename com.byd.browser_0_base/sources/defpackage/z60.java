package defpackage;

import android.annotation.SuppressLint;
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
public final class z60 extends BaseAdapter {
    public final Calendar e;
    public final int f;
    public final int g;

    public z60() {
        Calendar calendarD = qk3.d(null);
        this.e = calendarD;
        this.f = calendarD.getMaximum(7);
        this.g = calendarD.getFirstDayOfWeek();
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.f;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        int i2 = this.f;
        if (i >= i2) {
            return null;
        }
        int i3 = i + this.g;
        if (i3 > i2) {
            i3 -= i2;
        }
        return Integer.valueOf(i3);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public final View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_day_of_week, viewGroup, false);
        }
        int i2 = i + this.g;
        int i3 = this.f;
        if (i2 > i3) {
            i2 -= i3;
        }
        Calendar calendar = this.e;
        calendar.set(7, i2);
        textView.setText(calendar.getDisplayName(7, 4, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(R$string.mtrl_picker_day_of_week_column_header), calendar.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }

    public z60(int i) {
        Calendar calendarD = qk3.d(null);
        this.e = calendarD;
        this.f = calendarD.getMaximum(7);
        this.g = i;
    }
}
