package b.a.a.i;

/* JADX INFO: compiled from: Type.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f223a = new j(0, null, 1443168256, 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f224b = new j(1, null, 1509950721, 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j f225c = new j(2, null, 1124075009, 1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j f226d = new j(3, null, 1107297537, 1);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j f227e = new j(4, null, 1392510721, 1);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final j f228f = new j(5, null, 1224736769, 1);
    public static final j g = new j(6, null, 1174536705, 1);
    public static final j h = new j(7, null, 1241579778, 1);
    public static final j i = new j(8, null, 1141048066, 1);
    public final int j;
    public final char[] k;
    public final int l;
    public final int m;

    public j(int i2, char[] cArr, int i3, int i4) {
        this.j = i2;
        this.k = cArr;
        this.l = i3;
        this.m = i4;
    }

    public static j[] a(String str) {
        char[] charArray = str.toCharArray();
        int i2 = 1;
        int i3 = 0;
        int i4 = 1;
        while (true) {
            int i5 = i4 + 1;
            char c2 = charArray[i4];
            if (c2 == ')') {
                break;
            }
            if (c2 == 'L') {
                while (true) {
                    i4 = i5 + 1;
                    if (charArray[i5] == ';') {
                        break;
                    }
                    i5 = i4;
                }
                i3++;
            } else {
                if (c2 != '[') {
                    i3++;
                }
                i4 = i5;
            }
        }
        j[] jVarArr = new j[i3];
        int i6 = 0;
        while (charArray[i2] != ')') {
            jVarArr[i6] = h(charArray, i2);
            i2 += jVarArr[i6].m + (jVarArr[i6].j == 10 ? 2 : 0);
            i6++;
        }
        return jVarArr;
    }

    public static int b(String str) {
        int i2;
        int i3 = 1;
        int i4 = 1;
        int i5 = 1;
        while (true) {
            i2 = i4 + 1;
            char cCharAt = str.charAt(i4);
            if (cCharAt == ')') {
                break;
            }
            if (cCharAt == 'L') {
                while (true) {
                    i4 = i2 + 1;
                    if (str.charAt(i2) == ';') {
                        break;
                    }
                    i2 = i4;
                }
                i5++;
            } else {
                i5 = (cCharAt == 'D' || cCharAt == 'J') ? i5 + 2 : i5 + 1;
                i4 = i2;
            }
        }
        char cCharAt2 = str.charAt(i2);
        int i6 = i5 << 2;
        if (cCharAt2 == 'V') {
            i3 = 0;
        } else if (cCharAt2 == 'D' || cCharAt2 == 'J') {
            i3 = 2;
        }
        return i6 | i3;
    }

    public static j g(String str) {
        return h(str.toCharArray(), 0);
    }

    public static j h(char[] cArr, int i2) {
        int i3;
        char c2 = cArr[i2];
        if (c2 == 'F') {
            return g;
        }
        if (c2 == 'S') {
            return f227e;
        }
        if (c2 == 'V') {
            return f223a;
        }
        if (c2 == 'I') {
            return f228f;
        }
        if (c2 == 'J') {
            return h;
        }
        if (c2 == 'Z') {
            return f224b;
        }
        if (c2 != '[') {
            switch (c2) {
                case 'B':
                    return f226d;
                case 'C':
                    return f225c;
                case 'D':
                    return i;
                default:
                    int i4 = 1;
                    while (cArr[i2 + i4] != ';') {
                        i4++;
                    }
                    return new j(10, cArr, i2 + 1, i4 - 1);
            }
        }
        int i5 = 1;
        while (true) {
            i3 = i2 + i5;
            if (cArr[i3] != '[') {
                break;
            }
            i5++;
        }
        if (cArr[i3] == 'L') {
            do {
                i5++;
            } while (cArr[i2 + i5] != ';');
        }
        return new j(9, cArr, i2, i5 + 1);
    }

    public String c() {
        switch (this.j) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "char";
            case 3:
                return "byte";
            case 4:
                return "short";
            case 5:
                return "int";
            case 6:
                return "float";
            case 7:
                return "long";
            case 8:
                return "double";
            case 9:
                StringBuilder sb = new StringBuilder(h(this.k, this.l + e()).c());
                for (int iE = e(); iE > 0; iE--) {
                    sb.append("[]");
                }
                return sb.toString();
            default:
                return new String(this.k, this.l, this.m).replace('/', '.');
        }
    }

    public String d() {
        return new String(this.k, this.l, this.m);
    }

    public final int e() {
        int i2 = 1;
        while (this.k[this.l + i2] == '[') {
            i2++;
        }
        return i2;
    }

    public String f() {
        return new String(this.k, this.l, this.m);
    }
}
