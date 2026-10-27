package io.sentry.util;

import h3.y;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f16927a = Pattern.compile("(.+://)(.*@)(.*)");

    public static y a(String str) {
        if (!str.contains("://")) {
            int iIndexOf = str.indexOf("?");
            int iIndexOf2 = str.indexOf("#");
            return new y(iIndexOf >= 0 ? str.substring(0, iIndexOf).trim() : iIndexOf2 >= 0 ? str.substring(0, iIndexOf2).trim() : str, iIndexOf > 0 ? (iIndexOf2 <= 0 || iIndexOf2 <= iIndexOf) ? str.substring(iIndexOf + 1).trim() : str.substring(iIndexOf + 1, iIndexOf2).trim() : null, iIndexOf2 > 0 ? str.substring(iIndexOf2 + 1).trim() : null, 5);
        }
        try {
            String strB = b(str);
            URL url = new URL(str);
            int iIndexOf3 = strB.indexOf("?");
            if (iIndexOf3 >= 0) {
                strB = strB.substring(0, iIndexOf3).trim();
            } else {
                int iIndexOf4 = strB.indexOf("#");
                if (iIndexOf4 >= 0) {
                    strB = strB.substring(0, iIndexOf4).trim();
                }
            }
            return strB.contains("#") ? new y(str, str, str, 5) : new y(strB, url.getQuery(), url.getRef(), 5);
        } catch (MalformedURLException unused) {
            return new y(str, str, str, 5);
        }
    }

    public static String b(String str) {
        Matcher matcher = f16927a.matcher(str);
        if (!matcher.matches() || matcher.groupCount() != 3) {
            return str;
        }
        return matcher.group(1) + (matcher.group(2).contains(":") ? "[Filtered]:[Filtered]@" : "[Filtered]@") + matcher.group(3);
    }
}
