package z1;

/* JADX INFO: compiled from: JsonScope.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static String a(int i8, int[] iArr, String[] strArr, int[] iArr2) {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        for (int i9 = 0; i9 < i8; i9++) {
            int i10 = iArr[i9];
            if (i10 == 1 || i10 == 2) {
                sb.append('[');
                sb.append(iArr2[i9]);
                sb.append(']');
            } else if (i10 == 3 || i10 == 4 || i10 == 5) {
                sb.append('.');
                if (strArr[i9] != null) {
                    sb.append(strArr[i9]);
                }
            }
        }
        return sb.toString();
    }
}
