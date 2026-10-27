package C5;

import B5.L;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends L {
    public d(c cVar, String str, Date date, Map map, Map map2, String str2, String str3, boolean z6) {
        f(b.l, "channel");
        f(cVar, "type");
        f(str, "messageId");
        if (z6) {
            TimeZone timeZone = D5.a.f1190a;
            GregorianCalendar gregorianCalendar = new GregorianCalendar(D5.a.f1190a, Locale.US);
            gregorianCalendar.setTime(date);
            StringBuilder sb = new StringBuilder(30);
            D5.a.b(gregorianCalendar.get(1), 4, sb);
            sb.append('-');
            D5.a.b(gregorianCalendar.get(2) + 1, 2, sb);
            sb.append('-');
            D5.a.b(gregorianCalendar.get(5), 2, sb);
            sb.append('T');
            D5.a.b(gregorianCalendar.get(11), 2, sb);
            sb.append(':');
            D5.a.b(gregorianCalendar.get(12), 2, sb);
            sb.append(':');
            D5.a.b(gregorianCalendar.get(13), 2, sb);
            sb.append('.');
            if (date instanceof D5.c) {
                String string = Long.toString(((D5.c) date).l % 1000000000);
                sb.append(string);
                for (int length = 9 - string.length(); length > 0; length--) {
                    sb.append('0');
                }
            } else {
                String string2 = Long.toString(gregorianCalendar.get(14));
                sb.append(string2);
                for (int length2 = 9 - string2.length(); length2 > 0; length2--) {
                    sb.append('0');
                }
            }
            sb.append('Z');
            f(sb.toString(), "timestamp");
        } else {
            f(D5.g.i(date), "timestamp");
        }
        f(map, "context");
        f(map2, "integrations");
        if (!D5.g.g(str2)) {
            f(str2, "userId");
        }
        f(str3, "anonymousId");
    }

    public final c i() {
        Object obj = this.l.get("type");
        return (c) (c.class.isInstance(obj) ? (Enum) obj : obj instanceof String ? Enum.valueOf(c.class, (String) obj) : null);
    }
}
