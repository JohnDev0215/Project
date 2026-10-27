package e2;

/* JADX INFO: compiled from: Type.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f4573a = new j(0, null, 1443168256, 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f4574b = new j(1, null, 1509950721, 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j f4575c = new j(2, null, 1124075009, 1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j f4576d = new j(3, null, 1107297537, 1);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j f4577e = new j(4, null, 1392510721, 1);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final j f4578f = new j(5, null, 1224736769, 1);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final j f4579g = new j(6, null, 1174536705, 1);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final j f4580h = new j(7, null, 1241579778, 1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final j f4581i = new j(8, null, 1141048066, 1);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f4582j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final char[] f4583k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f4584l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f4585m;

    public j(int i8, char[] cArr, int i9, int i10) {
        this.f4582j = i8;
        this.f4583k = cArr;
        this.f4584l = i9;
        this.f4585m = i10;
    }

    public static j[] a(String str) {
        char[] charArray = str.toCharArray();
        int i8 = 1;
        int i9 = 0;
        int i10 = 1;
        while (true) {
            int i11 = i10 + 1;
            char c8 = charArray[i10];
            if (c8 == ')') {
                break;
            }
            if (c8 == 'L') {
                while (true) {
                    i10 = i11 + 1;
                    if (charArray[i11] == ';') {
                        break;
                    }
                    i11 = i10;
                }
                i9++;
            } else {
                if (c8 != '[') {
                    i9++;
                }
                i10 = i11;
            }
        }
        j[] jVarArr = new j[i9];
        int i12 = 0;
        while (charArray[i8] != ')') {
            jVarArr[i12] = h(charArray, i8);
            i8 += jVarArr[i12].f4585m + (jVarArr[i12].f4582j == 10 ? 2 : 0);
            i12++;
        }
        return jVarArr;
    }

    public static int b(String str) {
        int i8;
        int i9 = 1;
        int i10 = 1;
        int i11 = 1;
        while (true) {
            i8 = i10 + 1;
            char cCharAt = str.charAt(i10);
            if (cCharAt == ')') {
                break;
            }
            if (cCharAt == 'L') {
                while (true) {
                    i10 = i8 + 1;
                    if (str.charAt(i8) == ';') {
                        break;
                    }
                    i8 = i10;
                }
                i11++;
            } else {
                i11 = (cCharAt == 'D' || cCharAt == 'J') ? i11 + 2 : i11 + 1;
                i10 = i8;
            }
        }
        char cCharAt2 = str.charAt(i8);
        int i12 = i11 << 2;
        if (cCharAt2 == 'V') {
            i9 = 0;
        } else if (cCharAt2 == 'D' || cCharAt2 == 'J') {
            i9 = 2;
        }
        return i12 | i9;
    }

    public static j g(String str) {
        return h(str.toCharArray(), 0);
    }

    public static j h(char[] cArr, int i8) {
        int i9;
        char c8 = cArr[i8];
        if (c8 == 'F') {
            return f4579g;
        }
        if (c8 == 'S') {
            return f4577e;
        }
        if (c8 == 'V') {
            return f4573a;
        }
        if (c8 == 'I') {
            return f4578f;
        }
        if (c8 == 'J') {
            return f4580h;
        }
        if (c8 == 'Z') {
            return f4574b;
        }
        if (c8 != '[') {
            switch (c8) {
                case 'B':
                    return f4576d;
                case 'C':
                    return f4575c;
                case 'D':
                    return f4581i;
                default:
                    int i10 = 1;
                    while (cArr[i8 + i10] != ';') {
                        i10++;
                    }
                    return new j(10, cArr, i8 + 1, i10 - 1);
            }
        }
        int i11 = 1;
        while (true) {
            i9 = i8 + i11;
            if (cArr[i9] != '[') {
                break;
            }
            i11++;
        }
        if (cArr[i9] == 'L') {
            do {
                i11++;
            } while (cArr[i8 + i11] != ';');
        }
        return new j(9, cArr, i8, i11 + 1);
    }

    public String c() {
        switch (this.f4582j) {
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
                StringBuilder sb = new StringBuilder(h(this.f4583k, this.f4584l + e()).c());
                for (int iE = e(); iE > 0; iE--) {
                    sb.append("[]");
                }
                return sb.toString();
            default:
                return new String(this.f4583k, this.f4584l, this.f4585m).replace('/', '.');
        }
    }

    public String d() {
        return new String(this.f4583k, this.f4584l, this.f4585m);
    }

    public final int e() {
        int i8 = 1;
        while (this.f4583k[this.f4584l + i8] == '[') {
            i8++;
        }
        return i8;
    }

    public String f() {
        return new String(this.f4583k, this.f4584l, this.f4585m);
    }
}
