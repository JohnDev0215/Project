package okio;

import kotlin.j;
import kotlin.jvm.b.l;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: Utf8.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Utf8 {
    public static final int HIGH_SURROGATE_HEADER = 55232;
    public static final int LOG_SURROGATE_HEADER = 56320;
    public static final int MASK_2BYTES = 3968;
    public static final int MASK_3BYTES = -123008;
    public static final int MASK_4BYTES = 3678080;
    public static final byte REPLACEMENT_BYTE = 63;
    public static final char REPLACEMENT_CHARACTER = 65533;
    public static final int REPLACEMENT_CODE_POINT = 65533;

    public static final boolean isIsoControl(int i) {
        return (i >= 0 && 31 >= i) || (127 <= i && 159 >= i);
    }

    public static final boolean isUtf8Continuation(byte b2) {
        return (b2 & 192) == 128;
    }

    public static final int process2Utf8Bytes(byte[] bArr, int i, int i2, l<? super Integer, j> lVar) {
        i.c(bArr, "$this$process2Utf8Bytes");
        i.c(lVar, "yield");
        int i3 = i + 1;
        Integer numValueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            lVar.invoke(numValueOf);
            return 1;
        }
        byte b2 = bArr[i];
        byte b3 = bArr[i3];
        if (!((b3 & 192) == 128)) {
            lVar.invoke(numValueOf);
            return 1;
        }
        int i4 = (b3 ^ 3968) ^ (b2 << 6);
        if (i4 < 128) {
            lVar.invoke(numValueOf);
            return 2;
        }
        lVar.invoke(Integer.valueOf(i4));
        return 2;
    }

    public static final int process3Utf8Bytes(byte[] bArr, int i, int i2, l<? super Integer, j> lVar) {
        i.c(bArr, "$this$process3Utf8Bytes");
        i.c(lVar, "yield");
        int i3 = i + 2;
        Integer numValueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            lVar.invoke(numValueOf);
            int i4 = i + 1;
            if (i2 > i4) {
                if ((bArr[i4] & 192) == 128) {
                    return 2;
                }
            }
            return 1;
        }
        byte b2 = bArr[i];
        byte b3 = bArr[i + 1];
        if (!((b3 & 192) == 128)) {
            lVar.invoke(numValueOf);
            return 1;
        }
        byte b4 = bArr[i3];
        if (!((b4 & 192) == 128)) {
            lVar.invoke(numValueOf);
            return 2;
        }
        int i5 = ((b4 ^ (-123008)) ^ (b3 << 6)) ^ (b2 << 12);
        if (i5 < 2048) {
            lVar.invoke(numValueOf);
            return 3;
        }
        if (55296 <= i5 && 57343 >= i5) {
            lVar.invoke(numValueOf);
            return 3;
        }
        lVar.invoke(Integer.valueOf(i5));
        return 3;
    }

    public static final int process4Utf8Bytes(byte[] bArr, int i, int i2, l<? super Integer, j> lVar) {
        i.c(bArr, "$this$process4Utf8Bytes");
        i.c(lVar, "yield");
        int i3 = i + 3;
        Integer numValueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            lVar.invoke(numValueOf);
            int i4 = i + 1;
            if (i2 > i4) {
                if ((bArr[i4] & 192) == 128) {
                    int i5 = i + 2;
                    if (i2 > i5) {
                        if ((bArr[i5] & 192) == 128) {
                            return 3;
                        }
                    }
                    return 2;
                }
            }
            return 1;
        }
        byte b2 = bArr[i];
        byte b3 = bArr[i + 1];
        if (!((b3 & 192) == 128)) {
            lVar.invoke(numValueOf);
            return 1;
        }
        byte b4 = bArr[i + 2];
        if (!((b4 & 192) == 128)) {
            lVar.invoke(numValueOf);
            return 2;
        }
        byte b5 = bArr[i3];
        if (!((b5 & 192) == 128)) {
            lVar.invoke(numValueOf);
            return 3;
        }
        int i6 = (((b5 ^ 3678080) ^ (b4 << 6)) ^ (b3 << 12)) ^ (b2 << 18);
        if (i6 > 1114111) {
            lVar.invoke(numValueOf);
            return 4;
        }
        if (55296 <= i6 && 57343 >= i6) {
            lVar.invoke(numValueOf);
            return 4;
        }
        if (i6 < 65536) {
            lVar.invoke(numValueOf);
            return 4;
        }
        lVar.invoke(Integer.valueOf(i6));
        return 4;
    }

    public static final void processUtf16Chars(byte[] bArr, int i, int i2, l<? super Character, j> lVar) {
        int i3;
        i.c(bArr, "$this$processUtf16Chars");
        i.c(lVar, "yield");
        int i4 = i;
        while (i4 < i2) {
            byte b2 = bArr[i4];
            if (b2 >= 0) {
                lVar.invoke(Character.valueOf((char) b2));
                i4++;
                while (i4 < i2 && bArr[i4] >= 0) {
                    lVar.invoke(Character.valueOf((char) bArr[i4]));
                    i4++;
                }
            } else {
                if ((b2 >> 5) == -2) {
                    int i5 = i4 + 1;
                    if (i2 > i5) {
                        byte b3 = bArr[i4];
                        byte b4 = bArr[i5];
                        if ((b4 & 192) == 128) {
                            int i6 = (b4 ^ 3968) ^ (b3 << 6);
                            lVar.invoke(Character.valueOf(i6 < 128 ? (char) REPLACEMENT_CODE_POINT : (char) i6));
                            i3 = 2;
                        }
                    }
                    lVar.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                    i3 = 1;
                } else if ((b2 >> 4) == -2) {
                    int i7 = i4 + 2;
                    if (i2 <= i7) {
                        lVar.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                        int i8 = i4 + 1;
                        if (i2 > i8) {
                            if ((bArr[i8] & 192) == 128) {
                                i3 = 2;
                            }
                        }
                        i3 = 1;
                    } else {
                        byte b5 = bArr[i4];
                        byte b6 = bArr[i4 + 1];
                        if ((b6 & 192) == 128) {
                            byte b7 = bArr[i7];
                            if ((b7 & 192) == 128) {
                                int i9 = ((b7 ^ (-123008)) ^ (b6 << 6)) ^ (b5 << 12);
                                lVar.invoke(Character.valueOf((i9 >= 2048 && (55296 > i9 || 57343 < i9)) ? (char) i9 : (char) REPLACEMENT_CODE_POINT));
                                i3 = 3;
                            } else {
                                lVar.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                                i3 = 2;
                            }
                        } else {
                            lVar.invoke(Character.valueOf((char) REPLACEMENT_CODE_POINT));
                            i3 = 1;
                        }
                    }
                } else if ((b2 >> 3) == -2) {
                    int i10 = i4 + 3;
                    if (i2 <= i10) {
                        lVar.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                        int i11 = i4 + 1;
                        if (i2 > i11) {
                            if ((bArr[i11] & 192) == 128) {
                                int i12 = i4 + 2;
                                if (i2 > i12) {
                                    if ((bArr[i12] & 192) == 128) {
                                        i3 = 3;
                                    }
                                }
                                i3 = 2;
                            }
                        }
                        i3 = 1;
                    } else {
                        byte b8 = bArr[i4];
                        byte b9 = bArr[i4 + 1];
                        if ((b9 & 192) == 128) {
                            byte b10 = bArr[i4 + 2];
                            if ((b10 & 192) == 128) {
                                byte b11 = bArr[i10];
                                if ((b11 & 192) == 128) {
                                    int i13 = (((b11 ^ 3678080) ^ (b10 << 6)) ^ (b9 << 12)) ^ (b8 << 18);
                                    if (i13 <= 1114111 && ((55296 > i13 || 57343 < i13) && i13 >= 65536 && i13 != 65533)) {
                                        lVar.invoke(Character.valueOf((char) ((i13 >>> 10) + HIGH_SURROGATE_HEADER)));
                                        lVar.invoke(Character.valueOf((char) ((i13 & 1023) + LOG_SURROGATE_HEADER)));
                                    } else {
                                        lVar.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                                    }
                                    i3 = 4;
                                } else {
                                    lVar.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                                    i3 = 3;
                                }
                            } else {
                                lVar.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                                i3 = 2;
                            }
                        } else {
                            lVar.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                            i3 = 1;
                        }
                    }
                } else {
                    lVar.invoke(Character.valueOf(REPLACEMENT_CHARACTER));
                    i4++;
                }
                i4 += i3;
            }
        }
    }

    public static final void processUtf8Bytes(String str, int i, int i2, l<? super Byte, j> lVar) {
        int i3;
        char cCharAt;
        i.c(str, "$this$processUtf8Bytes");
        i.c(lVar, "yield");
        while (i < i2) {
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 < 128) {
                lVar.invoke(Byte.valueOf((byte) cCharAt2));
                i++;
                while (i < i2 && str.charAt(i) < 128) {
                    lVar.invoke(Byte.valueOf((byte) str.charAt(i)));
                    i++;
                }
            } else {
                if (cCharAt2 < 2048) {
                    lVar.invoke(Byte.valueOf((byte) ((cCharAt2 >> 6) | 192)));
                    lVar.invoke(Byte.valueOf((byte) ((cCharAt2 & '?') | 128)));
                } else if (55296 > cCharAt2 || 57343 < cCharAt2) {
                    lVar.invoke(Byte.valueOf((byte) ((cCharAt2 >> '\f') | 224)));
                    lVar.invoke(Byte.valueOf((byte) (((cCharAt2 >> 6) & 63) | 128)));
                    lVar.invoke(Byte.valueOf((byte) ((cCharAt2 & '?') | 128)));
                } else if (cCharAt2 > 56319 || i2 <= (i3 = i + 1) || 56320 > (cCharAt = str.charAt(i3)) || 57343 < cCharAt) {
                    lVar.invoke(Byte.valueOf(REPLACEMENT_BYTE));
                } else {
                    int iCharAt = ((cCharAt2 << '\n') + str.charAt(i3)) - 56613888;
                    lVar.invoke(Byte.valueOf((byte) ((iCharAt >> 18) | 240)));
                    lVar.invoke(Byte.valueOf((byte) (((iCharAt >> 12) & 63) | 128)));
                    lVar.invoke(Byte.valueOf((byte) (((iCharAt >> 6) & 63) | 128)));
                    lVar.invoke(Byte.valueOf((byte) ((iCharAt & 63) | 128)));
                    i += 2;
                }
                i++;
            }
        }
    }

    public static final void processUtf8CodePoints(byte[] bArr, int i, int i2, l<? super Integer, j> lVar) {
        int i3;
        i.c(bArr, "$this$processUtf8CodePoints");
        i.c(lVar, "yield");
        int i4 = i;
        while (i4 < i2) {
            byte b2 = bArr[i4];
            if (b2 >= 0) {
                lVar.invoke(Integer.valueOf(b2));
                i4++;
                while (i4 < i2 && bArr[i4] >= 0) {
                    lVar.invoke(Integer.valueOf(bArr[i4]));
                    i4++;
                }
            } else {
                if ((b2 >> 5) == -2) {
                    int i5 = i4 + 1;
                    if (i2 > i5) {
                        byte b3 = bArr[i4];
                        byte b4 = bArr[i5];
                        if ((b4 & 192) == 128) {
                            int i6 = (b4 ^ 3968) ^ (b3 << 6);
                            lVar.invoke(i6 < 128 ? Integer.valueOf(REPLACEMENT_CODE_POINT) : Integer.valueOf(i6));
                            i3 = 2;
                        }
                    }
                    lVar.invoke(Integer.valueOf(REPLACEMENT_CODE_POINT));
                    i3 = 1;
                } else if ((b2 >> 4) == -2) {
                    int i7 = i4 + 2;
                    if (i2 <= i7) {
                        lVar.invoke(Integer.valueOf(REPLACEMENT_CODE_POINT));
                        int i8 = i4 + 1;
                        if (i2 > i8) {
                            if ((bArr[i8] & 192) == 128) {
                                i3 = 2;
                            }
                        }
                        i3 = 1;
                    } else {
                        byte b5 = bArr[i4];
                        byte b6 = bArr[i4 + 1];
                        if ((b6 & 192) == 128) {
                            byte b7 = bArr[i7];
                            if ((b7 & 192) == 128) {
                                int i9 = ((b7 ^ (-123008)) ^ (b6 << 6)) ^ (b5 << 12);
                                lVar.invoke((i9 >= 2048 && (55296 > i9 || 57343 < i9)) ? Integer.valueOf(i9) : Integer.valueOf(REPLACEMENT_CODE_POINT));
                                i3 = 3;
                            } else {
                                lVar.invoke(Integer.valueOf(REPLACEMENT_CODE_POINT));
                                i3 = 2;
                            }
                        } else {
                            lVar.invoke(Integer.valueOf(REPLACEMENT_CODE_POINT));
                            i3 = 1;
                        }
                    }
                } else if ((b2 >> 3) == -2) {
                    int i10 = i4 + 3;
                    if (i2 <= i10) {
                        lVar.invoke(Integer.valueOf(REPLACEMENT_CODE_POINT));
                        int i11 = i4 + 1;
                        if (i2 > i11) {
                            if ((bArr[i11] & 192) == 128) {
                                int i12 = i4 + 2;
                                if (i2 > i12) {
                                    if ((bArr[i12] & 192) == 128) {
                                        i3 = 3;
                                    }
                                }
                                i3 = 2;
                            }
                        }
                        i3 = 1;
                    } else {
                        byte b8 = bArr[i4];
                        byte b9 = bArr[i4 + 1];
                        if ((b9 & 192) == 128) {
                            byte b10 = bArr[i4 + 2];
                            if ((b10 & 192) == 128) {
                                byte b11 = bArr[i10];
                                if ((b11 & 192) == 128) {
                                    int i13 = (((b11 ^ 3678080) ^ (b10 << 6)) ^ (b9 << 12)) ^ (b8 << 18);
                                    lVar.invoke((i13 <= 1114111 && (55296 > i13 || 57343 < i13) && i13 >= 65536) ? Integer.valueOf(i13) : Integer.valueOf(REPLACEMENT_CODE_POINT));
                                    i3 = 4;
                                } else {
                                    lVar.invoke(Integer.valueOf(REPLACEMENT_CODE_POINT));
                                    i3 = 3;
                                }
                            } else {
                                lVar.invoke(Integer.valueOf(REPLACEMENT_CODE_POINT));
                                i3 = 2;
                            }
                        } else {
                            lVar.invoke(Integer.valueOf(REPLACEMENT_CODE_POINT));
                            i3 = 1;
                        }
                    }
                } else {
                    lVar.invoke(Integer.valueOf(REPLACEMENT_CODE_POINT));
                    i4++;
                }
                i4 += i3;
            }
        }
    }

    public static final long size(String str) {
        return size$default(str, 0, 0, 3, null);
    }

    public static final long size(String str, int i) {
        return size$default(str, i, 0, 2, null);
    }

    public static final long size(String str, int i, int i2) {
        int i3;
        i.c(str, "$this$utf8Size");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i).toString());
        }
        if (!(i2 >= i)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
        }
        if (!(i2 <= str.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
        }
        long j = 0;
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                j++;
            } else {
                if (cCharAt < 2048) {
                    i3 = 2;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    i3 = 3;
                } else {
                    int i4 = i + 1;
                    char cCharAt2 = i4 < i2 ? str.charAt(i4) : (char) 0;
                    if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                        j++;
                        i = i4;
                    } else {
                        j += (long) 4;
                        i += 2;
                    }
                }
                j += (long) i3;
            }
            i++;
        }
        return j;
    }

    public static /* synthetic */ long size$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return size(str, i, i2);
    }
}
