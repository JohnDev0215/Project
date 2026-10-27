package h4;

import com.byd.mvp.ui.widget.calendarview.model.CalendarDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: CalendarFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, List<CalendarDate>> f5339a = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static StringBuffer f5340b = null;

    public static CalendarDate a(int i8, int i9, int i10) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i8, i9 - 1, i10);
        int i11 = calendar.get(1);
        int i12 = calendar.get(2) + 1;
        int i13 = calendar.get(5);
        CalendarDate calendarDate = new CalendarDate(i11, i12, i13);
        calendarDate.week = c.b(i11, i12, i13);
        return calendarDate;
    }

    public static List<CalendarDate> b(int i8, int i9) {
        if (f5340b == null) {
            f5340b = new StringBuffer();
        }
        f5340b.setLength(0);
        StringBuffer stringBuffer = f5340b;
        stringBuffer.append(i8);
        stringBuffer.append("_BYD_");
        stringBuffer.append(i9);
        String string = stringBuffer.toString();
        List<CalendarDate> list = f5339a.get(string);
        if (list != null && !list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        int iB = c.b(i8, i9, 1);
        int iA = c.a(i8, i9);
        int i10 = iB - 1;
        for (int i11 = i10; i11 > 0; i11--) {
            CalendarDate calendarDateA = a(i8, i9, 1 - i11);
            calendarDateA.monthFlag = -1;
            arrayList.add(calendarDateA);
        }
        int i12 = 0;
        while (i12 < iA) {
            i12++;
            arrayList.add(a(i8, i9, i12));
        }
        for (int i13 = 0; i13 < (42 - i10) - iA; i13++) {
            CalendarDate calendarDateA2 = a(i8, i9, iA + i13 + 1);
            calendarDateA2.monthFlag = 1;
            arrayList.add(calendarDateA2);
        }
        f5339a.put(string, arrayList);
        return arrayList;
    }
}
