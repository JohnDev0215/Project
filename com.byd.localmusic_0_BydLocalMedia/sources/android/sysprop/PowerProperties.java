package android.sysprop;

import android.os.SystemProperties;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
public final class PowerProperties {
    private PowerProperties() {
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Boolean tryParseBoolean(java.lang.String r5) {
        /*
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r5 = r5.toLowerCase(r0)
            int r0 = r5.hashCode()
            r1 = 48
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L3f
            r1 = 49
            if (r0 == r1) goto L35
            r1 = 3569038(0x36758e, float:5.001287E-39)
            if (r0 == r1) goto L2a
            r1 = 97196323(0x5cb1923, float:1.9099262E-35)
            if (r0 == r1) goto L20
            goto L49
        L20:
            java.lang.String r0 = "false"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L49
            r5 = r2
            goto L4a
        L2a:
            java.lang.String r0 = "true"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L49
            r5 = r4
            goto L4a
        L35:
            java.lang.String r0 = "1"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L49
            r5 = 0
            goto L4a
        L3f:
            java.lang.String r0 = "0"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L49
            r5 = r3
            goto L4a
        L49:
            r5 = -1
        L4a:
            if (r5 == 0) goto L57
            if (r5 == r4) goto L57
            if (r5 == r3) goto L54
            if (r5 == r2) goto L54
            r5 = 0
            return r5
        L54:
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        L57:
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.sysprop.PowerProperties.tryParseBoolean(java.lang.String):java.lang.Boolean");
    }

    private static Integer tryParseInteger(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static Long tryParseLong(String str) {
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static Double tryParseDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static String tryParseString(String str) {
        if ("".equals(str)) {
            return null;
        }
        return str;
    }

    private static <T extends Enum<T>> T tryParseEnum(Class<T> cls, String str) {
        try {
            return (T) Enum.valueOf(cls, str.toUpperCase(Locale.US));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static <T> List<T> tryParseList(Function<String, T> function, String str) {
        if ("".equals(str)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            StringBuilder sb = new StringBuilder();
            while (i < str.length() && str.charAt(i) != ',') {
                if (str.charAt(i) == '\\') {
                    i++;
                }
                if (i == str.length()) {
                    break;
                }
                sb.append(str.charAt(i));
                i++;
            }
            arrayList.add(function.apply(sb.toString()));
            if (i == str.length()) {
                return arrayList;
            }
            i++;
        }
    }

    private static <T extends Enum<T>> List<T> tryParseEnumList(Class<T> cls, String str) {
        if ("".equals(str)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(",")) {
            arrayList.add(tryParseEnum(cls, str2));
        }
        return arrayList;
    }

    private static String escape(String str) {
        return str.replaceAll("([\\\\,])", "\\\\$1");
    }

    private static <T> String formatList(List<T> list) {
        StringJoiner stringJoiner = new StringJoiner(",");
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T next = it.next();
            stringJoiner.add(next == null ? "" : escape(next.toString()));
        }
        return stringJoiner.toString();
    }

    private static <T extends Enum<T>> String formatEnumList(List<T> list, Function<T, String> function) {
        StringJoiner stringJoiner = new StringJoiner(",");
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T next = it.next();
            stringJoiner.add(next == null ? "" : function.apply(next));
        }
        return stringJoiner.toString();
    }

    public static Optional<Integer> fixed_performance_scale_factor() {
        return Optional.ofNullable(tryParseInteger(SystemProperties.get("ro.power.fixed_performance_scale_factor")));
    }
}
