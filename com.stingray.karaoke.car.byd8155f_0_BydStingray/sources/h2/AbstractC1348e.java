package h2;

import java.util.Locale;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: h2.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1348e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f15208a = {0, 0, 0, 1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f15209b = {"", "A", "B", "C"};
    public static final Pattern c = Pattern.compile("^\\D?(\\d+)$");

    public static String a(int i7, boolean z6, int i10, int i11, int[] iArr, int i12) {
        Object[] objArr = {f15209b[i7], Integer.valueOf(i10), Integer.valueOf(i11), Character.valueOf(z6 ? 'H' : 'L'), Integer.valueOf(i12)};
        int i13 = AbstractC1343A.f15195a;
        StringBuilder sb = new StringBuilder(String.format(Locale.US, "hvc1.%s%d.%X.%c%d", objArr));
        int length = iArr.length;
        while (length > 0 && iArr[length - 1] == 0) {
            length--;
        }
        for (int i14 = 0; i14 < length; i14++) {
            sb.append(String.format(".%02X", Integer.valueOf(iArr[i14])));
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.util.Pair b(java.lang.String r12, java.lang.String[] r13, e2.C1200h r14) {
        /*
            Method dump skipped, instruction units count: 806
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.AbstractC1348e.b(java.lang.String, java.lang.String[], e2.h):android.util.Pair");
    }
}
