package defpackage;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: outline */
/* JADX INFO: loaded from: classes.dex */
public abstract class Z7 {
    public static String a(String str, int i) {
        return str + i;
    }

    public static String b(String str, K3 k3, String str2) {
        return str + k3 + str2;
    }

    public static String c(String str, String str2) {
        return str + str2;
    }

    public static String d(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String e(XmlPullParser xmlPullParser, StringBuilder sb, String str) {
        sb.append(xmlPullParser.getPositionDescription());
        sb.append(str);
        return sb.toString();
    }

    public static StringBuilder f(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }
}
