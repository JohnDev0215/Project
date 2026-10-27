package f2;

/* JADX INFO: compiled from: SymbolTable.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String[] f4933a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4934b;

    public i(int i8) {
        this.f4934b = i8 - 1;
        this.f4933a = new String[i8];
        a("$ref", 0, 4, 1185263);
        String str = c2.a.DEFAULT_TYPE_KEY;
        a(str, 0, str.length(), c2.a.DEFAULT_TYPE_KEY.hashCode());
    }

    public static String d(String str, int i8, int i9) {
        char[] cArr = new char[i9];
        str.getChars(i8, i9 + i8, cArr, 0);
        return new String(cArr);
    }

    public String a(String str, int i8, int i9, int i10) {
        return b(str, i8, i9, i10, false);
    }

    public String b(String str, int i8, int i9, int i10, boolean z7) {
        int i11 = this.f4934b & i10;
        String str2 = this.f4933a[i11];
        if (str2 == null) {
            if (i9 != str.length()) {
                str = d(str, i8, i9);
            }
            String strIntern = str.intern();
            this.f4933a[i11] = strIntern;
            return strIntern;
        }
        if (i10 == str2.hashCode() && i9 == str2.length() && str.startsWith(str2, i8)) {
            return str2;
        }
        String strD = d(str, i8, i9);
        if (z7) {
            this.f4933a[i11] = strD;
        }
        return strD;
    }

    public String c(char[] cArr, int i8, int i9, int i10) {
        int i11 = this.f4934b & i10;
        String str = this.f4933a[i11];
        if (str == null) {
            String strIntern = new String(cArr, i8, i9).intern();
            this.f4933a[i11] = strIntern;
            return strIntern;
        }
        boolean z7 = false;
        if (i10 == str.hashCode() && i9 == str.length()) {
            int i12 = 0;
            while (true) {
                if (i12 >= i9) {
                    z7 = true;
                    break;
                }
                if (cArr[i8 + i12] != str.charAt(i12)) {
                    break;
                }
                i12++;
            }
        }
        return z7 ? str : new String(cArr, i8, i9);
    }
}
