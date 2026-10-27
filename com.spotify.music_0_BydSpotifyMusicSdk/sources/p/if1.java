package p;

import android.text.format.DateUtils;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialCalendar;
import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class if1 extends c22 {
    public final /* synthetic */ com.google.android.material.datepicker.c a;
    public final /* synthetic */ MaterialButton b;
    public final /* synthetic */ MaterialCalendar c;

    public if1(MaterialCalendar materialCalendar, com.google.android.material.datepicker.c cVar, MaterialButton materialButton) {
        this.c = materialCalendar;
        this.a = cVar;
        this.b = materialButton;
    }

    @Override // p.c22
    public final void a(RecyclerView recyclerView, int i) {
        if (i == 0) {
            recyclerView.announceForAccessibility(this.b.getText());
        }
    }

    @Override // p.c22
    public final void b(RecyclerView recyclerView, int i, int i2) {
        int iF0;
        MaterialCalendar materialCalendar = this.c;
        if (i < 0) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) materialCalendar.i0.getLayoutManager();
            View viewH0 = linearLayoutManager.H0(0, linearLayoutManager.v(), false);
            iF0 = viewH0 == null ? -1 : y12.D(viewH0);
        } else {
            iF0 = ((LinearLayoutManager) materialCalendar.i0.getLayoutManager()).F0();
        }
        com.google.android.material.datepicker.c cVar = this.a;
        Calendar calendarA = bs2.a(cVar.d.c.c);
        calendarA.add(2, iF0);
        materialCalendar.e0 = new bl1(calendarA);
        Calendar calendarA2 = bs2.a(cVar.d.c.c);
        calendarA2.add(2, iF0);
        calendarA2.set(5, 1);
        Calendar calendarA3 = bs2.a(calendarA2);
        calendarA3.get(2);
        calendarA3.get(1);
        calendarA3.getMaximum(7);
        calendarA3.getActualMaximum(5);
        calendarA3.getTimeInMillis();
        long timeInMillis = calendarA3.getTimeInMillis();
        this.b.setText(DateUtils.formatDateTime(cVar.c, timeInMillis - ((long) TimeZone.getDefault().getOffset(timeInMillis)), 36));
    }
}
