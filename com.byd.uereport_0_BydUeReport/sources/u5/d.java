package u5;

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
/* JADX INFO: loaded from: classes2.dex */
public class d extends BaseAdapter {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f8258d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Calendar f8259e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f8260f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f8261g;

    static {
        f8258d = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public d() {
        Calendar calendarL = l.l();
        this.f8259e = calendarL;
        this.f8260f = calendarL.getMaximum(7);
        this.f8261g = calendarL.getFirstDayOfWeek();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i8) {
        if (i8 >= this.f8260f) {
            return null;
        }
        return Integer.valueOf(b(i8));
    }

    public final int b(int i8) {
        int i9 = i8 + this.f8261g;
        int i10 = this.f8260f;
        return i9 > i10 ? i9 - i10 : i9;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f8260f;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i8) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public View getView(int i8, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mtrl_calendar_day_of_week, viewGroup, false);
        }
        this.f8259e.set(7, b(i8));
        textView.setText(this.f8259e.getDisplayName(7, f8258d, Locale.getDefault()));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(R$string.mtrl_picker_day_of_week_column_header), this.f8259e.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
