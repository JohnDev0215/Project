package g3;

import h2.t;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: g3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1292a {
    public static final Pattern c = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
    public static final Pattern d = Pattern.compile("^((?:[0-9]*\\.)?[0-9]+)(px|em|%)$");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f14814a = new t();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final StringBuilder f14815b = new StringBuilder();

    public static String a(t tVar, StringBuilder sb) {
        boolean z6 = false;
        sb.setLength(0);
        int i7 = tVar.f15242b;
        int i10 = tVar.c;
        while (i7 < i10 && !z6) {
            char c10 = (char) tVar.f15241a[i7];
            if ((c10 < 'A' || c10 > 'Z') && ((c10 < 'a' || c10 > 'z') && !((c10 >= '0' && c10 <= '9') || c10 == '#' || c10 == '-' || c10 == '.' || c10 == '_'))) {
                z6 = true;
            } else {
                i7++;
                sb.append(c10);
            }
        }
        tVar.I(i7 - tVar.f15242b);
        return sb.toString();
    }

    public static String b(t tVar, StringBuilder sb) {
        c(tVar);
        if (tVar.a() == 0) {
            return null;
        }
        String strA = a(tVar, sb);
        if (!"".equals(strA)) {
            return strA;
        }
        return "" + ((char) tVar.v());
    }

    public static void c(t tVar) {
        while (true) {
            for (boolean z6 = true; tVar.a() > 0 && z6; z6 = false) {
                int i7 = tVar.f15242b;
                byte[] bArr = tVar.f15241a;
                byte b10 = bArr[i7];
                char c10 = (char) b10;
                if (c10 == '\t' || c10 == '\n' || c10 == '\f' || c10 == '\r' || c10 == ' ') {
                    tVar.I(1);
                } else {
                    int i10 = tVar.c;
                    int i11 = i7 + 2;
                    if (i11 <= i10) {
                        int i12 = i7 + 1;
                        if (b10 == 47 && bArr[i12] == 42) {
                            while (true) {
                                int i13 = i11 + 1;
                                if (i13 >= i10) {
                                    break;
                                }
                                if (((char) bArr[i11]) == '*' && ((char) bArr[i13]) == '/') {
                                    i11 += 2;
                                    i10 = i11;
                                } else {
                                    i11 = i13;
                                }
                            }
                            tVar.I(i10 - tVar.f15242b);
                        }
                    }
                }
            }
            return;
        }
    }
}
