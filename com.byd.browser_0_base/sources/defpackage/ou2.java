package defpackage;

import android.content.Context;
import android.text.format.DateUtils;
import java.util.Calendar;
import java.util.Date;
import org.chromium.components.browser_ui.util.R$string;

/* JADX INFO: compiled from: StringUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ou2 {

    /* JADX INFO: compiled from: StringUtils.java */
    public static final class a {
        public static final Calendar a = gl.a();
        public static final Calendar b = gl.a();
    }

    public static StringBuilder a(Date date) {
        Context context = xz.a;
        Calendar calendar = a.a;
        calendar.setTimeInMillis(System.currentTimeMillis());
        Calendar calendar2 = a.b;
        calendar2.setTime(date);
        StringBuilder sb = new StringBuilder();
        if (jl.b(calendar, calendar2)) {
            sb.append(context.getString(R$string.today));
            sb.append(" - ");
        } else {
            calendar.add(5, -1);
            if (jl.b(calendar, calendar2)) {
                sb.append(context.getString(R$string.yesterday));
                sb.append(" - ");
            }
        }
        sb.append(DateUtils.formatDateTime(context, date.getTime(), 98308));
        return sb;
    }
}
