package org.chromium.base;

import android.text.TextUtils;
import defpackage.Z7;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PiiElider {
    public static final Pattern a = Pattern.compile("((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))");
    public static final Pattern b;
    public static final Pattern c;
    public static final Pattern d;
    public static final String[] e;
    public static final String[] f;

    static {
        StringBuilder sbF = Z7.f("(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef\\-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]){0,1}\\.)+[a-zA-Z -\ud7ff豈-﷏ﷰ-\uffef]{2,63}|");
        sbF.append(a);
        sbF.append(")");
        b = Pattern.compile(sbF.toString());
        c = Pattern.compile("\\sat\\sorg\\.chromium\\.[^ ]+.");
        StringBuilder sbF2 = Z7.f("(?:\\b|^)((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?(?:");
        sbF2.append(b);
        sbF2.append(")(?:\\:\\d{1,5})?)(\\/(?:(?:[");
        sbF2.append("a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef");
        sbF2.append("\\;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$)");
        d = Pattern.compile(sbF2.toString());
        Pattern.compile("([0-9a-fA-F]{2}[-:]+){5}[0-9a-fA-F]{2}");
        Pattern.compile("\\[\\w*:CONSOLE.*\\].*");
        e = new String[]{"org.chromium.", "com.google."};
        f = new String[]{"android.accessibilityservice", "android.accounts", "android.animation", "android.annotation", "android.app", "android.appwidget", "android.bluetooth", "android.content", "android.database", "android.databinding", "android.drm", "android.gesture", "android.graphics", "android.hardware", "android.inputmethodservice", "android.location", "android.media", "android.mtp", "android.net", "android.nfc", "android.opengl", "android.os", "android.preference", "android.print", "android.printservice", "android.provider", "android.renderscript", "android.sax", "android.security", "android.service", "android.speech", "android.support", "android.system", "android.telecom", "android.telephony", "android.test", "android.text", "android.transition", "android.util", "android.view", "android.webkit", "android.widget", "com.android.", "dalvik.", "java.", "javax.", "org.apache.", "org.json.", "org.w3c.dom.", "org.xml.", "org.xmlpull."};
    }

    public static String a(String str) {
        boolean z;
        boolean z2;
        if (c.matcher(str).find()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        Matcher matcher = d.matcher(sb);
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
                        z = false;
                        break;
                    }
                    if (strSubstring.startsWith(strArr2[i3])) {
                        break;
                    }
                    i3++;
                }
                if (!z) {
                    sb.replace(iStart, iEnd, "HTTP://WEBADDRESS.ELIDED");
                    i = iStart + 24;
                    matcher = d.matcher(sb);
                }
            }
            i = iEnd;
        }
        return sb.toString();
    }

    public static String sanitizeStacktrace(String str) {
        String[] strArrSplit = str.split("\\n");
        strArrSplit[0] = a(strArrSplit[0]);
        for (int i = 1; i < strArrSplit.length; i++) {
            if (strArrSplit[i].startsWith("Caused by:")) {
                strArrSplit[i] = a(strArrSplit[i]);
            }
        }
        return TextUtils.join("\n", strArrSplit);
    }
}
