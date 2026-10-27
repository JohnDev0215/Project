package b.a.a.j;

/* JADX INFO: compiled from: SymbolTable.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String[] f257a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f258b;

    public i(int i) {
        this.f258b = i - 1;
        this.f257a = new String[i];
        a("$ref", 0, 4, 1185263);
        String str = b.a.a.a.DEFAULT_TYPE_KEY;
        a(str, 0, str.length(), b.a.a.a.DEFAULT_TYPE_KEY.hashCode());
    }

    public static String d(String str, int i, int i2) {
        char[] cArr = new char[i2];
        str.getChars(i, i2 + i, cArr, 0);
        return new String(cArr);
    }

    public String a(String str, int i, int i2, int i3) {
        return b(str, i, i2, i3, false);
    }

    public String b(String str, int i, int i2, int i3, boolean z) {
        int i4 = this.f258b & i3;
        String str2 = this.f257a[i4];
        if (str2 == null) {
            if (i2 != str.length()) {
                str = d(str, i, i2);
            }
            String strIntern = str.intern();
            this.f257a[i4] = strIntern;
            return strIntern;
        }
        if (i3 == str2.hashCode() && i2 == str2.length() && str.startsWith(str2, i)) {
            return str2;
        }
        String strD = d(str, i, i2);
        if (z) {
            this.f257a[i4] = strD;
        }
        return strD;
    }

    public String c(char[] cArr, int i, int i2, int i3) {
        int i4 = this.f258b & i3;
        String str = this.f257a[i4];
        if (str == null) {
            String strIntern = new String(cArr, i, i2).intern();
            this.f257a[i4] = strIntern;
            return strIntern;
        }
        boolean z = false;
        if (i3 == str.hashCode() && i2 == str.length()) {
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    z = true;
                    break;
                }
                if (cArr[i + i5] != str.charAt(i5)) {
                    break;
                }
                i5++;
            }
        }
        return z ? str : new String(cArr, i, i2);
    }
}
