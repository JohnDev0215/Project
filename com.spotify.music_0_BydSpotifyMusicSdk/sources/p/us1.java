package p;

/* JADX INFO: loaded from: classes.dex */
public final class us1 extends mj2 {
    public static final char[] l = {'+'};
    public static final char[] m = "0123456789ABCDEF".toCharArray();
    public final boolean e;
    public final boolean[] k;

    public us1(String str, boolean z) {
        super(4);
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        String strConcat = str.concat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        if (z && strConcat.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        this.e = z;
        char[] charArray = strConcat.toCharArray();
        int iMax = -1;
        for (char c : charArray) {
            iMax = Math.max((int) c, iMax);
        }
        boolean[] zArr = new boolean[iMax + 1];
        for (char c2 : charArray) {
            zArr[c2] = true;
        }
        this.k = zArr;
    }

    @Override // p.mj2
    public final String b(String str) {
        str.getClass();
        int length = str.length();
        int iF = 0;
        while (iF < length) {
            char cCharAt = str.charAt(iF);
            boolean[] zArr = this.k;
            if (cCharAt >= zArr.length || !zArr[cCharAt]) {
                int length2 = str.length();
                char[] cArr = (char[]) gt1.a.get();
                int i = 0;
                int length3 = 0;
                while (iF < length2) {
                    if (iF >= length2) {
                        throw new IndexOutOfBoundsException("Index exceeds specified range");
                    }
                    int i2 = iF + 1;
                    char cCharAt2 = str.charAt(iF);
                    int codePoint = cCharAt2;
                    if (cCharAt2 >= 55296) {
                        codePoint = cCharAt2;
                        if (cCharAt2 <= 57343) {
                            if (cCharAt2 > 56319) {
                                throw new IllegalArgumentException("Unexpected low surrogate character '" + cCharAt2 + "' with value " + ((int) cCharAt2) + " at index " + iF + " in '" + ((Object) str) + "'");
                            }
                            if (i2 == length2) {
                                codePoint = -cCharAt2;
                            } else {
                                char cCharAt3 = str.charAt(i2);
                                if (!Character.isLowSurrogate(cCharAt3)) {
                                    throw new IllegalArgumentException("Expected low surrogate but got char '" + cCharAt3 + "' with value " + ((int) cCharAt3) + " at index " + i2 + " in '" + ((Object) str) + "'");
                                }
                                codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                            }
                        }
                    }
                    if (codePoint < 0) {
                        throw new IllegalArgumentException("Trailing high surrogate at end of input");
                    }
                    char[] cArrD = d(codePoint);
                    int i3 = (Character.isSupplementaryCodePoint(codePoint) ? 2 : 1) + iF;
                    if (cArrD != null) {
                        int i4 = iF - i;
                        int i5 = length3 + i4;
                        int length4 = cArrD.length + i5;
                        if (cArr.length < length4) {
                            int i6 = (length2 - iF) + length4 + 32;
                            if (i6 < 0) {
                                throw new AssertionError("Cannot increase internal buffer any further");
                            }
                            char[] cArr2 = new char[i6];
                            if (length3 > 0) {
                                System.arraycopy(cArr, 0, cArr2, 0, length3);
                            }
                            cArr = cArr2;
                        }
                        if (i4 > 0) {
                            str.getChars(i, iF, cArr, length3);
                            length3 = i5;
                        }
                        if (cArrD.length > 0) {
                            System.arraycopy(cArrD, 0, cArr, length3, cArrD.length);
                            length3 += cArrD.length;
                        }
                        i = i3;
                    }
                    iF = f(str, i3, length2);
                }
                int i7 = length2 - i;
                if (i7 > 0) {
                    int i8 = i7 + length3;
                    if (cArr.length < i8) {
                        if (i8 < 0) {
                            throw new AssertionError("Cannot increase internal buffer any further");
                        }
                        char[] cArr3 = new char[i8];
                        if (length3 > 0) {
                            System.arraycopy(cArr, 0, cArr3, 0, length3);
                        }
                        cArr = cArr3;
                    }
                    str.getChars(i, length2, cArr, length3);
                    length3 = i8;
                }
                return new String(cArr, 0, length3);
            }
            iF++;
        }
        return str;
    }

    public final char[] d(int i) {
        boolean[] zArr = this.k;
        if (i < zArr.length && zArr[i]) {
            return null;
        }
        if (i == 32 && this.e) {
            return l;
        }
        char[] cArr = m;
        if (i <= 127) {
            return new char[]{'%', cArr[i >>> 4], cArr[i & 15]};
        }
        if (i <= 2047) {
            return new char[]{'%', cArr[(i >>> 10) | 12], cArr[(i >>> 6) & 15], '%', cArr[((i >>> 4) & 3) | 8], cArr[i & 15]};
        }
        if (i <= 65535) {
            return new char[]{'%', 'E', cArr[i >>> 12], '%', cArr[((i >>> 10) & 3) | 8], cArr[(i >>> 6) & 15], '%', cArr[((i >>> 4) & 3) | 8], cArr[i & 15]};
        }
        if (i <= 1114111) {
            return new char[]{'%', 'F', cArr[(i >>> 18) & 7], '%', cArr[((i >>> 16) & 3) | 8], cArr[(i >>> 12) & 15], '%', cArr[((i >>> 10) & 3) | 8], cArr[(i >>> 6) & 15], '%', cArr[((i >>> 4) & 3) | 8], cArr[i & 15]};
        }
        throw new IllegalArgumentException(u4.e("Invalid unicode character value ", i));
    }

    public final int f(CharSequence charSequence, int i, int i2) {
        charSequence.getClass();
        while (i < i2) {
            char cCharAt = charSequence.charAt(i);
            boolean[] zArr = this.k;
            if (cCharAt >= zArr.length || !zArr[cCharAt]) {
                break;
            }
            i++;
        }
        return i;
    }
}
