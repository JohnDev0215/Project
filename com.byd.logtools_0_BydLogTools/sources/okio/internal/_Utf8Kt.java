package okio.internal;

import java.util.Arrays;
import kotlin.jvm.internal.i;
import okio.Utf8;

/* JADX INFO: compiled from: -Utf8.kt */
/* JADX INFO: loaded from: classes.dex */
public final class _Utf8Kt {
    public static final byte[] commonAsUtf8ToByteArray(String str) {
        int i;
        int i2;
        char cCharAt;
        i.c(str, "$this$commonAsUtf8ToByteArray");
        byte[] bArr = new byte[str.length() * 4];
        int length = str.length();
        int i3 = 0;
        while (i3 < length) {
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 >= 128) {
                int length2 = str.length();
                int i4 = i3;
                while (i3 < length2) {
                    char cCharAt3 = str.charAt(i3);
                    if (cCharAt3 < 128) {
                        int i5 = i4 + 1;
                        bArr[i4] = (byte) cCharAt3;
                        i3++;
                        while (i3 < length2 && str.charAt(i3) < 128) {
                            bArr[i5] = (byte) str.charAt(i3);
                            i3++;
                            i5++;
                        }
                        i4 = i5;
                    } else {
                        if (cCharAt3 < 2048) {
                            int i6 = i4 + 1;
                            bArr[i4] = (byte) ((cCharAt3 >> 6) | 192);
                            byte b2 = (byte) ((cCharAt3 & '?') | 128);
                            i = i6 + 1;
                            bArr[i6] = b2;
                        } else if (55296 > cCharAt3 || 57343 < cCharAt3) {
                            int i7 = i4 + 1;
                            bArr[i4] = (byte) ((cCharAt3 >> '\f') | 224);
                            int i8 = i7 + 1;
                            bArr[i7] = (byte) (((cCharAt3 >> 6) & 63) | 128);
                            byte b3 = (byte) ((cCharAt3 & '?') | 128);
                            i = i8 + 1;
                            bArr[i8] = b3;
                        } else if (cCharAt3 > 56319 || length2 <= (i2 = i3 + 1) || 56320 > (cCharAt = str.charAt(i2)) || 57343 < cCharAt) {
                            i = i4 + 1;
                            bArr[i4] = Utf8.REPLACEMENT_BYTE;
                        } else {
                            int iCharAt = ((cCharAt3 << '\n') + str.charAt(i2)) - 56613888;
                            int i9 = i4 + 1;
                            bArr[i4] = (byte) ((iCharAt >> 18) | 240);
                            int i10 = i9 + 1;
                            bArr[i9] = (byte) (((iCharAt >> 12) & 63) | 128);
                            int i11 = i10 + 1;
                            bArr[i10] = (byte) (((iCharAt >> 6) & 63) | 128);
                            byte b4 = (byte) ((iCharAt & 63) | 128);
                            i = i11 + 1;
                            bArr[i11] = b4;
                            i3 += 2;
                            i4 = i;
                        }
                        i3++;
                        i4 = i;
                    }
                }
                byte[] bArrCopyOf = Arrays.copyOf(bArr, i4);
                i.b(bArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
                return bArrCopyOf;
            }
            bArr[i3] = (byte) cCharAt2;
            i3++;
        }
        byte[] bArrCopyOf2 = Arrays.copyOf(bArr, str.length());
        i.b(bArrCopyOf2, "java.util.Arrays.copyOf(this, newSize)");
        return bArrCopyOf2;
    }

    public static final String commonToUtf8String(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = i;
        i.c(bArr, "$this$commonToUtf8String");
        if (i7 < 0 || i2 > bArr.length || i7 > i2) {
            throw new ArrayIndexOutOfBoundsException("size=" + bArr.length + " beginIndex=" + i7 + " endIndex=" + i2);
        }
        char[] cArr = new char[i2 - i7];
        int i8 = 0;
        while (i7 < i2) {
            byte b2 = bArr[i7];
            if (b2 >= 0) {
                i3 = i8 + 1;
                cArr[i8] = (char) b2;
                i7++;
                while (i7 < i2 && bArr[i7] >= 0) {
                    cArr[i3] = (char) bArr[i7];
                    i7++;
                    i3++;
                }
            } else {
                if ((b2 >> 5) == -2) {
                    int i9 = i7 + 1;
                    if (i2 <= i9) {
                        i3 = i8 + 1;
                        cArr[i8] = (char) Utf8.REPLACEMENT_CODE_POINT;
                    } else {
                        byte b3 = bArr[i7];
                        byte b4 = bArr[i9];
                        if ((b4 & 192) == 128) {
                            int i10 = (b4 ^ 3968) ^ (b3 << 6);
                            if (i10 < 128) {
                                i3 = i8 + 1;
                                cArr[i8] = (char) Utf8.REPLACEMENT_CODE_POINT;
                            } else {
                                i3 = i8 + 1;
                                cArr[i8] = (char) i10;
                            }
                            i4 = 2;
                        } else {
                            i3 = i8 + 1;
                            cArr[i8] = (char) Utf8.REPLACEMENT_CODE_POINT;
                        }
                    }
                    i4 = 1;
                } else if ((b2 >> 4) == -2) {
                    int i11 = i7 + 2;
                    if (i2 <= i11) {
                        i3 = i8 + 1;
                        cArr[i8] = (char) Utf8.REPLACEMENT_CODE_POINT;
                        int i12 = i7 + 1;
                        if (i2 > i12) {
                            if ((bArr[i12] & 192) == 128) {
                                i4 = 2;
                            }
                        }
                        i4 = 1;
                    } else {
                        byte b5 = bArr[i7];
                        byte b6 = bArr[i7 + 1];
                        if ((b6 & 192) == 128) {
                            byte b7 = bArr[i11];
                            if ((b7 & 192) == 128) {
                                int i13 = ((b7 ^ (-123008)) ^ (b6 << 6)) ^ (b5 << 12);
                                if (i13 < 2048) {
                                    i3 = i8 + 1;
                                    cArr[i8] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                } else if (55296 <= i13 && 57343 >= i13) {
                                    i3 = i8 + 1;
                                    cArr[i8] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                } else {
                                    i3 = i8 + 1;
                                    cArr[i8] = (char) i13;
                                }
                                i4 = 3;
                            } else {
                                i3 = i8 + 1;
                                cArr[i8] = (char) Utf8.REPLACEMENT_CODE_POINT;
                                i4 = 2;
                            }
                        } else {
                            i3 = i8 + 1;
                            cArr[i8] = (char) Utf8.REPLACEMENT_CODE_POINT;
                            i4 = 1;
                        }
                    }
                } else {
                    if ((b2 >> 3) == -2) {
                        int i14 = i7 + 3;
                        if (i2 <= i14) {
                            i5 = i8 + 1;
                            cArr[i8] = Utf8.REPLACEMENT_CHARACTER;
                            int i15 = i7 + 1;
                            if (i2 > i15) {
                                if ((bArr[i15] & 192) == 128) {
                                    int i16 = i7 + 2;
                                    if (i2 > i16) {
                                        if ((bArr[i16] & 192) == 128) {
                                            i6 = 3;
                                        }
                                    }
                                    i6 = 2;
                                }
                            }
                            i6 = 1;
                        } else {
                            byte b8 = bArr[i7];
                            byte b9 = bArr[i7 + 1];
                            if ((b9 & 192) == 128) {
                                byte b10 = bArr[i7 + 2];
                                if ((b10 & 192) == 128) {
                                    byte b11 = bArr[i14];
                                    if ((b11 & 192) == 128) {
                                        int i17 = (((b11 ^ 3678080) ^ (b10 << 6)) ^ (b9 << 12)) ^ (b8 << 18);
                                        if (i17 > 1114111) {
                                            i5 = i8 + 1;
                                            cArr[i8] = Utf8.REPLACEMENT_CHARACTER;
                                        } else if ((55296 <= i17 && 57343 >= i17) || i17 < 65536 || i17 == 65533) {
                                            i5 = i8 + 1;
                                            cArr[i8] = Utf8.REPLACEMENT_CHARACTER;
                                        } else {
                                            int i18 = i8 + 1;
                                            cArr[i8] = (char) ((i17 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                            char c2 = (char) ((i17 & 1023) + Utf8.LOG_SURROGATE_HEADER);
                                            i5 = i18 + 1;
                                            cArr[i18] = c2;
                                        }
                                        i6 = 4;
                                    } else {
                                        i5 = i8 + 1;
                                        cArr[i8] = Utf8.REPLACEMENT_CHARACTER;
                                        i6 = 3;
                                    }
                                } else {
                                    i5 = i8 + 1;
                                    cArr[i8] = Utf8.REPLACEMENT_CHARACTER;
                                    i6 = 2;
                                }
                            } else {
                                i5 = i8 + 1;
                                cArr[i8] = Utf8.REPLACEMENT_CHARACTER;
                                i6 = 1;
                            }
                        }
                        i7 += i6;
                    } else {
                        i5 = i8 + 1;
                        cArr[i8] = Utf8.REPLACEMENT_CHARACTER;
                        i7++;
                    }
                    i8 = i5;
                }
                i7 += i4;
            }
            i8 = i3;
        }
        return new String(cArr, 0, i8);
    }

    public static /* synthetic */ String commonToUtf8String$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        return commonToUtf8String(bArr, i, i2);
    }
}
