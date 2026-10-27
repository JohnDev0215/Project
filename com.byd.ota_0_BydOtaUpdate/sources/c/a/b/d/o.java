package c.a.b.d;

/* JADX INFO: compiled from: TimeZoneUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class o {
    public static void a(StringBuilder sb, int i2, int i3) {
        String strValueOf = String.valueOf(i3);
        for (int i4 = 0; i4 < i2 - strValueOf.length(); i4++) {
            sb.append("0");
        }
        sb.append(strValueOf);
    }

    public static String b(boolean z, boolean z2, int i2) {
        String str;
        int i3 = i2 / 60000;
        if (i3 < 0) {
            i3 = -i3;
            str = "-";
        } else {
            str = "+";
        }
        StringBuilder sb = new StringBuilder(9);
        if (z) {
            sb.append("GMT");
        }
        sb.append(str);
        a(sb, 2, i3 / 60);
        if (z2) {
            sb.append(":");
        }
        a(sb, 2, i3 % 60);
        return sb.toString();
    }
}
