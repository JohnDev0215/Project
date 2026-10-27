package android.internal.telephony.sysprop;

import android.os.SystemProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final class TelephonyProperties {
    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
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
            if (r0 == r1) goto L3e
            r1 = 49
            if (r0 == r1) goto L34
            r1 = 3569038(0x36758e, float:5.001287E-39)
            if (r0 == r1) goto L2a
            r1 = 97196323(0x5cb1923, float:1.9099262E-35)
            if (r0 == r1) goto L20
            goto L48
        L20:
            java.lang.String r0 = "false"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L48
            r5 = r2
            goto L49
        L2a:
            java.lang.String r0 = "true"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L48
            r5 = r4
            goto L49
        L34:
            java.lang.String r0 = "1"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L48
            r5 = 0
            goto L49
        L3e:
            java.lang.String r0 = "0"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L48
            r5 = r3
            goto L49
        L48:
            r5 = -1
        L49:
            if (r5 == 0) goto L56
            if (r5 == r4) goto L56
            if (r5 == r3) goto L53
            if (r5 == r2) goto L53
            r5 = 0
            return r5
        L53:
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        L56:
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.internal.telephony.sysprop.TelephonyProperties.tryParseBoolean(java.lang.String):java.lang.Boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer tryParseInteger(String str) {
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

    public static void airplane_mode_on(Boolean bool) {
        String str;
        if (bool == null) {
            str = "";
        } else {
            str = bool.booleanValue() ? "1" : "0";
        }
        SystemProperties.set("persist.radio.airplane_mode_on", str);
    }

    public static Optional<Long> ecm_exit_timer() {
        return Optional.ofNullable(tryParseLong(SystemProperties.get("ro.cdma.ecmexittimer")));
    }

    public static List<Integer> default_network() {
        return tryParseList(new Function() { // from class: android.internal.telephony.sysprop.-$$Lambda$TelephonyProperties$BlIRcXd-mutUPI-u-vpLwjy9vrY
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TelephonyProperties.tryParseInteger((String) obj);
            }
        }, SystemProperties.get("ro.telephony.default_network"));
    }

    public static Optional<Boolean> data_roaming() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get("ro.com.android.dataroaming")));
    }

    public static Optional<Boolean> mobile_data() {
        return Optional.ofNullable(tryParseBoolean(SystemProperties.get("ro.com.android.mobiledata")));
    }
}
