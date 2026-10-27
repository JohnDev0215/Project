package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class hf2 {
    public static int a(int i, int i2, int i3) {
        return (Integer.hashCode(i) + i2) * i3;
    }

    public static int b(String str, int i, int i2) {
        return (str.hashCode() + i) * i2;
    }

    public static String c(String str, int i, String str2, int i2) {
        return str + i + str2 + i2;
    }

    public static String d(StringBuilder sb, String str, char c) {
        sb.append(str);
        sb.append(c);
        return sb.toString();
    }

    public static String e(StringBuilder sb, List list, char c) {
        sb.append(list);
        sb.append(c);
        return sb.toString();
    }

    public static StringBuilder f(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb;
    }

    public static void g(StringBuilder sb, String str, String str2, String str3) {
        sb.append(str);
        sb.append(if2.c(str2));
        sb.append(str3);
    }
}
