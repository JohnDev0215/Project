package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes.dex */
public abstract class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b0 f11834a = new b0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c0 f11835b = new c0();

    public static String c(C0944g c0944g) {
        StringBuilder sb = new StringBuilder(c0944g.size());
        for (int i7 = 0; i7 < c0944g.size(); i7++) {
            byte bA = c0944g.a(i7);
            if (bA == 34) {
                sb.append("\\\"");
            } else if (bA == 39) {
                sb.append("\\'");
            } else if (bA != 92) {
                switch (bA) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (bA < 32 || bA > 126) {
                            sb.append('\\');
                            sb.append((char) (((bA >>> 6) & 3) + 48));
                            sb.append((char) (((bA >>> 3) & 7) + 48));
                            sb.append((char) ((bA & 7) + 48));
                        } else {
                            sb.append((char) bA);
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    public static boolean d(byte b10) {
        return b10 > -65;
    }

    public abstract String a(int i7, int i10, byte[] bArr);

    public abstract int b(String str, byte[] bArr, int i7, int i10);

    public abstract int e(int i7, int i10, byte[] bArr);

    public abstract void f(int i7, int i10, byte[] bArr);
}
