package org.joda.time;

import org.joda.time.format.DateTimeFormat;

/* JADX INFO: loaded from: classes6.dex */
public class IllegalInstantException extends IllegalArgumentException {
    public IllegalInstantException(String str) {
        super(str);
    }

    public IllegalInstantException(long j, String str) {
        super(createMessage(j, str));
    }

    private static String createMessage(long j, String str) {
        String str2;
        String strPrint = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(j));
        if (str != null) {
            str2 = " (" + str + ")";
        } else {
            str2 = "";
        }
        return "Illegal instant due to time zone offset transition (daylight savings time 'gap'): " + strPrint + str2;
    }
}
