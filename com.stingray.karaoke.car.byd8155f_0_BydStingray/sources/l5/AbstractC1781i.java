package l5;

/* JADX INFO: renamed from: l5.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1781i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f18646a;

    static {
        int i7;
        String property = System.getProperty("java.version");
        try {
            String[] strArrSplit = property.split("[._]", 3);
            i7 = Integer.parseInt(strArrSplit[0]);
            if (i7 == 1 && strArrSplit.length > 1) {
                i7 = Integer.parseInt(strArrSplit[1]);
            }
        } catch (NumberFormatException unused) {
            i7 = -1;
        }
        if (i7 == -1) {
            try {
                StringBuilder sb = new StringBuilder();
                for (int i10 = 0; i10 < property.length(); i10++) {
                    char cCharAt = property.charAt(i10);
                    if (!Character.isDigit(cCharAt)) {
                        break;
                    }
                    sb.append(cCharAt);
                }
                i7 = Integer.parseInt(sb.toString());
            } catch (NumberFormatException unused2) {
                i7 = -1;
            }
        }
        if (i7 == -1) {
            i7 = 6;
        }
        f18646a = i7;
    }
}
