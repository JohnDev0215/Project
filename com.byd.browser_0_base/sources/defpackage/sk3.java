package defpackage;

import com.google.protobuf.d0;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: Utf8.java */
/* JADX INFO: loaded from: classes.dex */
public final class sk3 {
    public static final c a;

    /* JADX INFO: compiled from: Utf8.java */
    public static class a {
        public static void a(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws d0 {
            if (!d(b2)) {
                if ((((b2 + 112) + (b << 28)) >> 30) == 0 && !d(b3) && !d(b4)) {
                    int i2 = ((b & 7) << 18) | ((b2 & 63) << 12) | ((b3 & 63) << 6) | (b4 & 63);
                    cArr[i] = (char) ((i2 >>> 10) + 55232);
                    cArr[i + 1] = (char) ((i2 & 1023) + 56320);
                    return;
                }
            }
            throw d0.c();
        }

        public static void b(byte b, byte b2, char[] cArr, int i) throws d0 {
            if (b < -62 || d(b2)) {
                throw d0.c();
            }
            cArr[i] = (char) (((b & 31) << 6) | (b2 & 63));
        }

        public static void c(byte b, byte b2, byte b3, char[] cArr, int i) throws d0 {
            if (d(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || d(b3)))) {
                throw d0.c();
            }
            cArr[i] = (char) (((b & 15) << 12) | ((b2 & 63) << 6) | (b3 & 63));
        }

        public static boolean d(byte b) {
            return b > -65;
        }
    }

    /* JADX INFO: compiled from: Utf8.java */
    public static abstract class b {
        public static String a(ByteBuffer byteBuffer, int i, int i2) throws d0 {
            if ((i | i2 | ((byteBuffer.limit() - i) - i2)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte b = byteBuffer.get(i);
                if (!(b >= 0)) {
                    break;
                }
                i++;
                cArr[i4] = (char) b;
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte b2 = byteBuffer.get(i);
                if (b2 >= 0) {
                    int i7 = i5 + 1;
                    cArr[i5] = (char) b2;
                    i = i6;
                    while (true) {
                        i5 = i7;
                        if (i >= i3) {
                            break;
                        }
                        byte b3 = byteBuffer.get(i);
                        if (!(b3 >= 0)) {
                            break;
                        }
                        i++;
                        i7 = i5 + 1;
                        cArr[i5] = (char) b3;
                    }
                } else {
                    if (!(b2 < -32)) {
                        if (b2 < -16) {
                            if (i6 >= i3 - 1) {
                                throw d0.c();
                            }
                            int i8 = i6 + 1;
                            a.c(b2, byteBuffer.get(i6), byteBuffer.get(i8), cArr, i5);
                            i = i8 + 1;
                            i5++;
                        } else {
                            if (i6 >= i3 - 2) {
                                throw d0.c();
                            }
                            int i9 = i6 + 1;
                            byte b4 = byteBuffer.get(i6);
                            int i10 = i9 + 1;
                            a.a(b2, b4, byteBuffer.get(i9), byteBuffer.get(i10), cArr, i5);
                            i5 = i5 + 1 + 1;
                            i = i10 + 1;
                        }
                    } else {
                        if (i6 >= i3) {
                            throw d0.c();
                        }
                        a.b(b2, byteBuffer.get(i6), cArr, i5);
                        i = i6 + 1;
                        i5++;
                    }
                }
            }
            return new String(cArr, 0, i5);
        }
    }

    /* JADX INFO: compiled from: Utf8.java */
    public static final class c extends b {
        public final String b(byte[] bArr, int i, int i2) {
            if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte b = bArr[i];
                if (!(b >= 0)) {
                    break;
                }
                i++;
                cArr[i4] = (char) b;
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte b2 = bArr[i];
                if (b2 >= 0) {
                    int i7 = i5 + 1;
                    cArr[i5] = (char) b2;
                    i = i6;
                    while (true) {
                        i5 = i7;
                        if (i >= i3) {
                            break;
                        }
                        byte b3 = bArr[i];
                        if (!(b3 >= 0)) {
                            break;
                        }
                        i++;
                        i7 = i5 + 1;
                        cArr[i5] = (char) b3;
                    }
                } else {
                    if (!(b2 < -32)) {
                        if (b2 < -16) {
                            if (i6 >= i3 - 1) {
                                throw d0.c();
                            }
                            int i8 = i6 + 1;
                            a.c(b2, bArr[i6], bArr[i8], cArr, i5);
                            i = i8 + 1;
                            i5++;
                        } else {
                            if (i6 >= i3 - 2) {
                                throw d0.c();
                            }
                            int i9 = i6 + 1;
                            byte b4 = bArr[i6];
                            int i10 = i9 + 1;
                            a.a(b2, b4, bArr[i9], bArr[i10], cArr, i5);
                            i5 = i5 + 1 + 1;
                            i = i10 + 1;
                        }
                    } else {
                        if (i6 >= i3) {
                            throw d0.c();
                        }
                        a.b(b2, bArr[i6], cArr, i5);
                        i = i6 + 1;
                        i5++;
                    }
                }
            }
            return new String(cArr, 0, i5);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
        
            if (r14[r12] > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0047, code lost:
        
            if (r14[r12] > (-65)) goto L29;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0082, code lost:
        
            if (r14[r12] > (-65)) goto L50;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int c(int r11, int r12, int r13, byte[] r14) {
            /*
                Method dump skipped, instruction units count: 242
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: sk3.c.c(int, int, int, byte[]):int");
        }
    }

    /* JADX INFO: compiled from: Utf8.java */
    public static class d extends IllegalArgumentException {
        public d(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    static {
        if (dj3.e && dj3.d) {
            Class<?> cls = q4.a;
        }
        a = new c();
    }

    public static int a(byte[] bArr, int i, int i2) {
        byte b2 = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            if (b2 > -12) {
                b2 = -1;
            }
            return b2;
        }
        if (i3 == 1) {
            return e(b2, bArr[i]);
        }
        if (i3 == 2) {
            return f(b2, bArr[i], bArr[i + 1]);
        }
        throw new AssertionError();
    }

    public static String b(ByteBuffer byteBuffer, int i, int i2) {
        c cVar = a;
        cVar.getClass();
        if (byteBuffer.hasArray()) {
            return cVar.b(byteBuffer.array(), byteBuffer.arrayOffset() + i, i2);
        }
        return byteBuffer.isDirect() ? b.a(byteBuffer, i, i2) : b.a(byteBuffer, i, i2);
    }

    public static int c(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        char cCharAt;
        a.getClass();
        int length = charSequence.length();
        int i6 = i2 + i;
        int i7 = 0;
        while (i7 < length && (i5 = i7 + i) < i6 && (cCharAt = charSequence.charAt(i7)) < 128) {
            bArr[i5] = (byte) cCharAt;
            i7++;
        }
        if (i7 == length) {
            return i + length;
        }
        int i8 = i + i7;
        while (i7 < length) {
            char cCharAt2 = charSequence.charAt(i7);
            if (cCharAt2 >= 128 || i8 >= i6) {
                if (cCharAt2 < 2048 && i8 <= i6 - 2) {
                    int i9 = i8 + 1;
                    bArr[i8] = (byte) ((cCharAt2 >>> 6) | 960);
                    i8 = i9 + 1;
                    bArr[i9] = (byte) ((cCharAt2 & '?') | 128);
                } else {
                    if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || i8 > i6 - 3) {
                        if (i8 > i6 - 4) {
                            if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i4 = i7 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i4)))) {
                                throw new d(i7, length);
                            }
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + i8);
                        }
                        int i10 = i7 + 1;
                        if (i10 != charSequence.length()) {
                            char cCharAt3 = charSequence.charAt(i10);
                            if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                int i11 = i8 + 1;
                                bArr[i8] = (byte) ((codePoint >>> 18) | 240);
                                int i12 = i11 + 1;
                                bArr[i11] = (byte) (((codePoint >>> 12) & 63) | 128);
                                int i13 = i12 + 1;
                                bArr[i12] = (byte) (((codePoint >>> 6) & 63) | 128);
                                i8 = i13 + 1;
                                bArr[i13] = (byte) ((codePoint & 63) | 128);
                                i7 = i10;
                            } else {
                                i7 = i10;
                            }
                        }
                        throw new d(i7 - 1, length);
                    }
                    int i14 = i8 + 1;
                    bArr[i8] = (byte) ((cCharAt2 >>> '\f') | 480);
                    int i15 = i14 + 1;
                    bArr[i14] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                    i3 = i15 + 1;
                    bArr[i15] = (byte) ((cCharAt2 & '?') | 128);
                }
                i7++;
            } else {
                i3 = i8 + 1;
                bArr[i8] = (byte) cCharAt2;
            }
            i8 = i3;
            i7++;
        }
        return i8;
    }

    public static int d(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char cCharAt = charSequence.charAt(i2);
            if (cCharAt < 2048) {
                i3 += (127 - cCharAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char cCharAt2 = charSequence.charAt(i2);
                    if (cCharAt2 < 2048) {
                        i += (127 - cCharAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new d(i2, length2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i3) + 4294967296L));
    }

    public static int e(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    public static int f(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean g(byte[] bArr, int i, int i2) {
        return a.c(0, i, i2, bArr) == 0;
    }
}
