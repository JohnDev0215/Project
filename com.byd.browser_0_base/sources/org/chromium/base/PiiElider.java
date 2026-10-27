package org.chromium.base;

import android.text.TextUtils;
import android.util.Log;
import defpackage.xz;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class PiiElider {
    public static final Pattern a = Pattern.compile("(\\b|^)(((((http|https|Http|Https|rtsp|Rtsp)://(([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2})){1,64}(:([a-zA-Z0-9$_.+!*'(),;?&=-]|(%[a-fA-F0-9]{2})){1,25})?@)?)?(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]){0,1}\\.)+[a-zA-Z -\ud7ff豈-﷏ﷰ-\uffef]{2,63}|((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))(:\\d{1,5})?)|([a-zA-Z][a-zA-Z0-9+.-]+://((([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef;/?:@&=#~.+!*'(),_-])|(%[a-fA-F0-9]{2}))+))))(/(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef;/?:@&=#~.+!*'(),_-])|(%[a-fA-F0-9]{2}))*)?(\\b|$)");
    public static final Pattern b = Pattern.compile("\\sat\\s(org\\.chromium|com\\.google|java|android|com\\.android)\\.[^ ]+.|\\(chromium-[^\\.]+\\.aab[^:]+:\\d+\\)|Caused by: java\\.lang\\.(ClassNotFoundException|NoClassDefFoundError):");
    public static final Pattern c = Pattern.compile("([0-9a-fA-F]{2}[-:]+){5}[0-9a-fA-F]{2}");
    public static final Pattern d = Pattern.compile("\\[\\w*:CONSOLE.*\\].*");
    public static final String[] e = {"org.chromium.", "com.google.", "com.chrome."};
    public static final String[] f = {"android.", "com.android.", "dalvik.", "java.", "javax.", "org.apache.", "org.json.", "org.w3c.dom.", "org.xml.", "org.xmlpull.", "System."};

    public static String a(String str) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (b.matcher(str).find()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        Pattern pattern = a;
        Matcher matcher = pattern.matcher(sb);
        int i = 0;
        while (matcher.find(i)) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            String strSubstring = sb.substring(iStart, iEnd);
            String[] strArr = e;
            int length = strArr.length;
            int i2 = 0;
            while (true) {
                z = true;
                if (i2 >= length) {
                    z2 = false;
                    break;
                }
                if (strSubstring.startsWith(strArr[i2])) {
                    z2 = true;
                    break;
                }
                i2++;
            }
            if (!z2) {
                String[] strArr2 = f;
                int length2 = strArr2.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        z3 = false;
                        break;
                    }
                    if (strSubstring.startsWith(strArr2[i3])) {
                        z3 = true;
                        break;
                    }
                    i3++;
                }
                if (!z3) {
                    try {
                        Class.forName(strSubstring, false, xz.a.getClassLoader());
                        z4 = true;
                    } catch (Throwable unused) {
                        z4 = false;
                    }
                    if (!z4) {
                        int iLastIndexOf = strSubstring.lastIndexOf(".");
                        if (iLastIndexOf == -1) {
                            z = false;
                        } else {
                            try {
                                Class.forName(strSubstring.substring(0, iLastIndexOf), false, xz.a.getClassLoader());
                            } catch (Throwable unused2) {
                                z = false;
                            }
                        }
                    }
                    if (!z) {
                        sb.replace(iStart, iEnd, "HTTP://WEBADDRESS.ELIDED");
                        i = iStart + 24;
                        matcher = pattern.matcher(sb);
                    }
                }
            }
            i = iEnd;
        }
        return sb.toString();
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] strArrSplit = str.split("\\n");
        strArrSplit[0] = a(strArrSplit[0]);
        for (int i = 1; i < strArrSplit.length; i++) {
            if (strArrSplit[i].startsWith("Caused by:")) {
                strArrSplit[i] = a(strArrSplit[i]);
            }
        }
        return TextUtils.join("\n", strArrSplit);
    }

    @CalledByNative
    public static String getSanitizedStacktrace(Throwable th) {
        return b(Log.getStackTraceString(th));
    }
}
