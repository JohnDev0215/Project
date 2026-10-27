package defpackage;

import defpackage.rk3;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: Utf8Safe.java */
/* JADX INFO: loaded from: classes.dex */
public final class tk3 extends rk3 {

    /* JADX INFO: compiled from: Utf8Safe.java */
    public static class a extends IllegalArgumentException {
        public a(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    @Override // defpackage.rk3
    public final String a(ByteBuffer byteBuffer, int i, int i2) {
        if (!byteBuffer.hasArray()) {
            if ((i | i2 | ((byteBuffer.limit() - i) - i2)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = i;
            int i5 = 0;
            while (i4 < i3) {
                byte b = byteBuffer.get(i4);
                if (!(b >= 0)) {
                    break;
                }
                i4++;
                cArr[i5] = (char) b;
                i5++;
            }
            int i6 = i5;
            while (i4 < i3) {
                int i7 = i4 + 1;
                byte b2 = byteBuffer.get(i4);
                if (b2 >= 0) {
                    cArr[i6] = (char) b2;
                    i6++;
                    i4 = i7;
                    while (i4 < i3) {
                        byte b3 = byteBuffer.get(i4);
                        if (!(b3 >= 0)) {
                            break;
                        }
                        i4++;
                        cArr[i6] = (char) b3;
                        i6++;
                    }
                } else {
                    if (!(b2 < -32)) {
                        if (b2 < -16) {
                            if (i7 >= i3 - 1) {
                                throw new IllegalArgumentException("Invalid UTF-8");
                            }
                            int i8 = i7 + 1;
                            rk3.a.b(b2, byteBuffer.get(i7), byteBuffer.get(i8), cArr, i6);
                            i4 = i8 + 1;
                            i6++;
                        } else {
                            if (i7 >= i3 - 2) {
                                throw new IllegalArgumentException("Invalid UTF-8");
                            }
                            int i9 = i7 + 1;
                            byte b4 = byteBuffer.get(i7);
                            int i10 = i9 + 1;
                            byte b5 = byteBuffer.get(i9);
                            i4 = i10 + 1;
                            rk3.a.a(b2, b4, b5, byteBuffer.get(i10), cArr, i6);
                            i6 = i6 + 1 + 1;
                        }
                    } else {
                        if (i7 >= i3) {
                            throw new IllegalArgumentException("Invalid UTF-8");
                        }
                        i4 = i7 + 1;
                        rk3.a.c(b2, byteBuffer.get(i7), cArr, i6);
                        i6++;
                    }
                }
            }
            return new String(cArr, 0, i6);
        }
        byte[] bArrArray = byteBuffer.array();
        int iArrayOffset = byteBuffer.arrayOffset() + i;
        if ((iArrayOffset | i2 | ((bArrArray.length - iArrayOffset) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArrArray.length), Integer.valueOf(iArrayOffset), Integer.valueOf(i2)));
        }
        int i11 = iArrayOffset + i2;
        char[] cArr2 = new char[i2];
        int i12 = 0;
        while (iArrayOffset < i11) {
            byte b6 = bArrArray[iArrayOffset];
            if (!(b6 >= 0)) {
                break;
            }
            iArrayOffset++;
            cArr2[i12] = (char) b6;
            i12++;
        }
        int i13 = i12;
        while (iArrayOffset < i11) {
            int i14 = iArrayOffset + 1;
            byte b7 = bArrArray[iArrayOffset];
            if (b7 >= 0) {
                cArr2[i13] = (char) b7;
                i13++;
                iArrayOffset = i14;
                while (iArrayOffset < i11) {
                    byte b8 = bArrArray[iArrayOffset];
                    if (!(b8 >= 0)) {
                        break;
                    }
                    iArrayOffset++;
                    cArr2[i13] = (char) b8;
                    i13++;
                }
            } else {
                if (!(b7 < -32)) {
                    if (b7 < -16) {
                        if (i14 >= i11 - 1) {
                            throw new IllegalArgumentException("Invalid UTF-8");
                        }
                        int i15 = i14 + 1;
                        rk3.a.b(b7, bArrArray[i14], bArrArray[i15], cArr2, i13);
                        iArrayOffset = i15 + 1;
                        i13++;
                    } else {
                        if (i14 >= i11 - 2) {
                            throw new IllegalArgumentException("Invalid UTF-8");
                        }
                        int i16 = i14 + 1;
                        byte b9 = bArrArray[i14];
                        int i17 = i16 + 1;
                        byte b10 = bArrArray[i16];
                        iArrayOffset = i17 + 1;
                        rk3.a.a(b7, b9, b10, bArrArray[i17], cArr2, i13);
                        i13 = i13 + 1 + 1;
                    }
                } else {
                    if (i14 >= i11) {
                        throw new IllegalArgumentException("Invalid UTF-8");
                    }
                    iArrayOffset = i14 + 1;
                    rk3.a.c(b7, bArrArray[i14], cArr2, i13);
                    i13++;
                }
            }
        }
        return new String(cArr2, 0, i13);
    }

    @Override // defpackage.rk3
    public final void b(String str, ByteBuffer byteBuffer) {
        int i;
        int i2;
        int i3;
        char cCharAt;
        char c = 2048;
        int i4 = 0;
        if (byteBuffer.hasArray()) {
            int iArrayOffset = byteBuffer.arrayOffset();
            byte[] bArrArray = byteBuffer.array();
            int iPosition = byteBuffer.position() + iArrayOffset;
            int iRemaining = byteBuffer.remaining();
            int length = str.length();
            int i5 = iRemaining + iPosition;
            while (i4 < length) {
                int i6 = i4 + iPosition;
                if (i6 >= i5 || (cCharAt = str.charAt(i4)) >= 128) {
                    break;
                }
                bArrArray[i6] = (byte) cCharAt;
                i4++;
            }
            if (i4 == length) {
                i = iPosition + length;
            } else {
                i = iPosition + i4;
                while (i4 < length) {
                    char cCharAt2 = str.charAt(i4);
                    if (cCharAt2 >= 128 || i >= i5) {
                        if (cCharAt2 < c && i <= i5 - 2) {
                            int i7 = i + 1;
                            bArrArray[i] = (byte) ((cCharAt2 >>> 6) | 960);
                            bArrArray[i7] = (byte) ((cCharAt2 & '?') | 128);
                            i = i7 + 1;
                        } else {
                            if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || i > i5 - 3) {
                                if (i > i5 - 4) {
                                    if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i3 = i4 + 1) == str.length() || !Character.isSurrogatePair(cCharAt2, str.charAt(i3)))) {
                                        throw new a(i4, length);
                                    }
                                    throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + i);
                                }
                                int i8 = i4 + 1;
                                if (i8 != str.length()) {
                                    char cCharAt3 = str.charAt(i8);
                                    if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                        int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                        int i9 = i + 1;
                                        bArrArray[i] = (byte) ((codePoint >>> 18) | 240);
                                        int i10 = i9 + 1;
                                        bArrArray[i9] = (byte) (((codePoint >>> 12) & 63) | 128);
                                        int i11 = i10 + 1;
                                        bArrArray[i10] = (byte) (((codePoint >>> 6) & 63) | 128);
                                        i = i11 + 1;
                                        bArrArray[i11] = (byte) ((codePoint & 63) | 128);
                                        i4 = i8;
                                    } else {
                                        i4 = i8;
                                    }
                                }
                                throw new a(i4 - 1, length);
                            }
                            int i12 = i + 1;
                            bArrArray[i] = (byte) ((cCharAt2 >>> '\f') | 480);
                            int i13 = i12 + 1;
                            bArrArray[i12] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                            i2 = i13 + 1;
                            bArrArray[i13] = (byte) ((cCharAt2 & '?') | 128);
                        }
                        i4++;
                        c = 2048;
                    } else {
                        i2 = i + 1;
                        bArrArray[i] = (byte) cCharAt2;
                    }
                    i = i2;
                    i4++;
                    c = 2048;
                }
            }
            return;
        }
        int length2 = str.length();
        int iPosition2 = byteBuffer.position();
        while (i4 < length2) {
            try {
                char cCharAt4 = str.charAt(i4);
                if (cCharAt4 >= 128) {
                    break;
                }
                byteBuffer.put(iPosition2 + i4, (byte) cCharAt4);
                i4++;
            } catch (IndexOutOfBoundsException unused) {
                int iMax = Math.max(i4, (iPosition2 - byteBuffer.position()) + 1) + byteBuffer.position();
                throw new ArrayIndexOutOfBoundsException("Failed writing " + str.charAt(i4) + " at index " + iMax);
            }
        }
        if (i4 == length2) {
            return;
        }
        iPosition2 += i4;
        while (i4 < length2) {
            char cCharAt5 = str.charAt(i4);
            if (cCharAt5 < 128) {
                byteBuffer.put(iPosition2, (byte) cCharAt5);
            } else if (cCharAt5 < 2048) {
                int i14 = iPosition2 + 1;
                try {
                    byteBuffer.put(iPosition2, (byte) ((cCharAt5 >>> 6) | 192));
                    byteBuffer.put(i14, (byte) ((cCharAt5 & '?') | 128));
                    iPosition2 = i14;
                } catch (IndexOutOfBoundsException unused2) {
                    iPosition2 = i14;
                    int iMax2 = Math.max(i4, (iPosition2 - byteBuffer.position()) + 1) + byteBuffer.position();
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + str.charAt(i4) + " at index " + iMax2);
                }
            } else {
                if (cCharAt5 >= 55296 && 57343 >= cCharAt5) {
                    int i15 = i4 + 1;
                    if (i15 != length2) {
                        try {
                            char cCharAt6 = str.charAt(i15);
                            if (Character.isSurrogatePair(cCharAt5, cCharAt6)) {
                                int codePoint2 = Character.toCodePoint(cCharAt5, cCharAt6);
                                int i16 = iPosition2 + 1;
                                try {
                                    byteBuffer.put(iPosition2, (byte) ((codePoint2 >>> 18) | 240));
                                    int i17 = i16 + 1;
                                    byteBuffer.put(i16, (byte) (((codePoint2 >>> 12) & 63) | 128));
                                    int i18 = i17 + 1;
                                    byteBuffer.put(i17, (byte) (((codePoint2 >>> 6) & 63) | 128));
                                    byteBuffer.put(i18, (byte) ((codePoint2 & 63) | 128));
                                    iPosition2 = i18;
                                    i4 = i15;
                                } catch (IndexOutOfBoundsException unused3) {
                                    iPosition2 = i16;
                                    i4 = i15;
                                    int iMax22 = Math.max(i4, (iPosition2 - byteBuffer.position()) + 1) + byteBuffer.position();
                                    throw new ArrayIndexOutOfBoundsException("Failed writing " + str.charAt(i4) + " at index " + iMax22);
                                }
                            } else {
                                i4 = i15;
                            }
                        } catch (IndexOutOfBoundsException unused4) {
                        }
                    }
                    throw new a(i4, length2);
                }
                int i19 = iPosition2 + 1;
                byteBuffer.put(iPosition2, (byte) ((cCharAt5 >>> '\f') | 224));
                iPosition2 = i19 + 1;
                byteBuffer.put(i19, (byte) (((cCharAt5 >>> 6) & 63) | 128));
                byteBuffer.put(iPosition2, (byte) ((cCharAt5 & '?') | 128));
            }
            i4++;
            iPosition2++;
        }
    }

    @Override // defpackage.rk3
    public final int c(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && str.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char cCharAt = str.charAt(i2);
            if (cCharAt < 2048) {
                i3 += (127 - cCharAt) >>> 31;
                i2++;
            } else {
                int length2 = str.length();
                while (i2 < length2) {
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 < 2048) {
                        i += (127 - cCharAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343) {
                            if (Character.codePointAt(str, i2) < 65536) {
                                throw new a(i2, length2);
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
}
