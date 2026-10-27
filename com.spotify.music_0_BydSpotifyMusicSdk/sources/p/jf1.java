package p;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.datepicker.MaterialCalendar;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class jf1 implements View.OnClickListener {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.google.android.material.datepicker.c e;
    public final /* synthetic */ MaterialCalendar k;

    public /* synthetic */ jf1(MaterialCalendar materialCalendar, com.google.android.material.datepicker.c cVar, int i) {
        this.c = i;
        this.k = materialCalendar;
        this.e = cVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.c;
        com.google.android.material.datepicker.c cVar = this.e;
        MaterialCalendar materialCalendar = this.k;
        switch (i) {
            case 0:
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) materialCalendar.i0.getLayoutManager();
                View viewH0 = linearLayoutManager.H0(0, linearLayoutManager.v(), false);
                int iD = (viewH0 == null ? -1 : y12.D(viewH0)) + 1;
                if (iD < materialCalendar.i0.getAdapter().a()) {
                    Calendar calendarA = bs2.a(cVar.d.c.c);
                    calendarA.add(2, iD);
                    materialCalendar.F(new bl1(calendarA));
                }
                break;
            default:
                int iF0 = ((LinearLayoutManager) materialCalendar.i0.getLayoutManager()).F0() - 1;
                if (iF0 >= 0) {
                    Calendar calendarA2 = bs2.a(cVar.d.c.c);
                    calendarA2.add(2, iF0);
                    materialCalendar.F(new bl1(calendarA2));
                }
                break;
        }
    }
}
