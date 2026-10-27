package b.a.d.e.c.a;

import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: CalendarFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, List<CalendarDate>> f343a = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static StringBuffer f344b = null;

    public static CalendarDate a(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2 - 1, i3);
        int i4 = calendar.get(1);
        int i5 = calendar.get(2) + 1;
        int i6 = calendar.get(5);
        CalendarDate calendarDate = new CalendarDate(i4, i5, i6);
        calendarDate.week = c.b(i4, i5, i6);
        return calendarDate;
    }

    public static List<CalendarDate> b(int i, int i2) {
        if (f344b == null) {
            f344b = new StringBuffer();
        }
        f344b.setLength(0);
        StringBuffer stringBuffer = f344b;
        stringBuffer.append(i);
        stringBuffer.append("_BYD_");
        stringBuffer.append(i2);
        String string = stringBuffer.toString();
        List<CalendarDate> list = f343a.get(string);
        if (list != null && !list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        int iB = c.b(i, i2, 1);
        int iA = c.a(i, i2);
        int i3 = iB - 1;
        for (int i4 = i3; i4 > 0; i4--) {
            CalendarDate calendarDateA = a(i, i2, 1 - i4);
            calendarDateA.monthFlag = -1;
            arrayList.add(calendarDateA);
        }
        int i5 = 0;
        while (i5 < iA) {
            i5++;
            arrayList.add(a(i, i2, i5));
        }
        for (int i6 = 0; i6 < (42 - i3) - iA; i6++) {
            CalendarDate calendarDateA2 = a(i, i2, iA + i6 + 1);
            calendarDateA2.monthFlag = 1;
            arrayList.add(calendarDateA2);
        }
        f343a.put(string, arrayList);
        return arrayList;
    }
}
