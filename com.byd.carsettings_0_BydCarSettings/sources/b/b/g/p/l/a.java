package b.b.g.p.l;

import android.icu.text.TimeZoneNames;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: compiled from: ZoneGetter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static String a(Locale locale, TimeZone timeZone, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ZZZZ");
        simpleDateFormat.setTimeZone(timeZone);
        return BidiFormatter.getInstance().unicodeWrap(simpleDateFormat.format(date), TextUtils.getLayoutDirectionFromLocale(locale) == 1 ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
    }

    public static String b(TimeZone timeZone, Date date) {
        Locale locale = Locale.getDefault();
        String strA = a(locale, timeZone, date);
        String strC = c(TimeZoneNames.getInstance(locale), timeZone, date);
        b.b.m.c.a.a("ZoneGetter", "gmtOffsetString:" + strA + ",zoneLongName:" + strC);
        if (strC == null) {
            return strA;
        }
        return strA + BydAdbHelper.COMMAND_LINE_END + strC;
    }

    public static String c(TimeZoneNames timeZoneNames, TimeZone timeZone, Date date) {
        return timeZoneNames.getDisplayName(timeZone.getID(), timeZone.inDaylightTime(date) ? TimeZoneNames.NameType.LONG_DAYLIGHT : TimeZoneNames.NameType.LONG_STANDARD, date.getTime());
    }
}
