package m2;

/* JADX INFO: compiled from: IntegerParser.java */
/* JADX INFO: loaded from: classes.dex */
class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f8110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f8111b;

    c(long j7, int i7) {
        this.f8111b = j7;
        this.f8110a = i7;
    }

    static c b(String str, int i7, int i8) {
        long j7;
        int i9;
        if (i7 >= i8) {
            return null;
        }
        long j8 = 0;
        int i10 = i7;
        while (i10 < i8) {
            char cCharAt = str.charAt(i10);
            if (cCharAt < '0' || cCharAt > '9') {
                if (cCharAt >= 'A' && cCharAt <= 'F') {
                    j7 = j8 * 16;
                    i9 = cCharAt - 'A';
                } else {
                    if (cCharAt < 'a' || cCharAt > 'f') {
                        break;
                    }
                    j7 = j8 * 16;
                    i9 = cCharAt - 'a';
                }
                j8 = j7 + ((long) i9) + 10;
            } else {
                j8 = (j8 * 16) + ((long) (cCharAt - '0'));
            }
            if (j8 > 4294967295L) {
                return null;
            }
            i10++;
        }
        if (i10 == i7) {
            return null;
        }
        return new c(j8, i10);
    }

    static c c(String str, int i7, int i8, boolean z6) {
        if (i7 >= i8) {
            return null;
        }
        boolean z7 = false;
        if (z6) {
            char cCharAt = str.charAt(i7);
            if (cCharAt == '+') {
                i7++;
            } else if (cCharAt == '-') {
                z7 = true;
                i7++;
            }
        }
        long j7 = 0;
        int i9 = i7;
        while (i9 < i8) {
            char cCharAt2 = str.charAt(i9);
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                break;
            }
            if (z7) {
                j7 = (j7 * 10) - ((long) (cCharAt2 - '0'));
                if (j7 < -2147483648L) {
                    return null;
                }
            } else {
                j7 = (j7 * 10) + ((long) (cCharAt2 - '0'));
                if (j7 > 2147483647L) {
                    return null;
                }
            }
            i9++;
        }
        if (i9 == i7) {
            return null;
        }
        return new c(j7, i9);
    }

    int a() {
        return this.f8110a;
    }

    public int d() {
        return (int) this.f8111b;
    }
}
