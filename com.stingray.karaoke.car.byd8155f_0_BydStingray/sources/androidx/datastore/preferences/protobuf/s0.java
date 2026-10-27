package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes.dex */
public final class s0 extends d0 {
    public final /* synthetic */ int c;

    public /* synthetic */ s0(int i7) {
        this.c = i7;
    }

    public static int g(long j3, byte[] bArr, int i7, int i10) {
        if (i10 == 0) {
            d0 d0Var = u0.f11889a;
            if (i7 > -12) {
                return -1;
            }
            return i7;
        }
        if (i10 == 1) {
            return u0.c(i7, r0.g(j3, bArr));
        }
        if (i10 == 2) {
            return u0.d(i7, r0.g(j3, bArr), r0.g(j3 + 1, bArr));
        }
        throw new AssertionError();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0049  */
    @Override // androidx.datastore.preferences.protobuf.d0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a(int r10, int r11, byte[] r12) throws androidx.datastore.preferences.protobuf.C0961y {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.s0.a(int, int, byte[]):java.lang.String");
    }

    @Override // androidx.datastore.preferences.protobuf.d0
    public final int b(String str, byte[] bArr, int i7, int i10) {
        int i11;
        int i12;
        char cCharAt;
        long j3;
        String str2;
        String str3;
        int i13;
        long j10;
        long j11;
        char cCharAt2;
        switch (this.c) {
            case 0:
                int length = str.length();
                int i14 = i10 + i7;
                int i15 = 0;
                while (i15 < length && (i12 = i15 + i7) < i14 && (cCharAt = str.charAt(i15)) < 128) {
                    bArr[i12] = (byte) cCharAt;
                    i15++;
                }
                if (i15 == length) {
                    return i7 + length;
                }
                int i16 = i7 + i15;
                while (i15 < length) {
                    char cCharAt3 = str.charAt(i15);
                    if (cCharAt3 < 128 && i16 < i14) {
                        bArr[i16] = (byte) cCharAt3;
                        i16++;
                    } else if (cCharAt3 < 2048 && i16 <= i14 - 2) {
                        int i17 = i16 + 1;
                        bArr[i16] = (byte) ((cCharAt3 >>> 6) | 960);
                        i16 += 2;
                        bArr[i17] = (byte) ((cCharAt3 & '?') | 128);
                    } else {
                        if ((cCharAt3 >= 55296 && 57343 >= cCharAt3) || i16 > i14 - 3) {
                            if (i16 > i14 - 4) {
                                if (55296 <= cCharAt3 && cCharAt3 <= 57343 && ((i11 = i15 + 1) == str.length() || !Character.isSurrogatePair(cCharAt3, str.charAt(i11)))) {
                                    throw new t0(i15, length);
                                }
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt3 + " at index " + i16);
                            }
                            int i18 = i15 + 1;
                            if (i18 != str.length()) {
                                char cCharAt4 = str.charAt(i18);
                                if (Character.isSurrogatePair(cCharAt3, cCharAt4)) {
                                    int codePoint = Character.toCodePoint(cCharAt3, cCharAt4);
                                    bArr[i16] = (byte) ((codePoint >>> 18) | 240);
                                    bArr[i16 + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i19 = i16 + 3;
                                    bArr[i16 + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i16 += 4;
                                    bArr[i19] = (byte) ((codePoint & 63) | 128);
                                    i15 = i18;
                                } else {
                                    i15 = i18;
                                }
                            }
                            throw new t0(i15 - 1, length);
                        }
                        bArr[i16] = (byte) ((cCharAt3 >>> '\f') | 480);
                        int i20 = i16 + 2;
                        bArr[i16 + 1] = (byte) (((cCharAt3 >>> 6) & 63) | 128);
                        i16 += 3;
                        bArr[i20] = (byte) ((cCharAt3 & '?') | 128);
                    }
                    i15++;
                }
                return i16;
            default:
                long j12 = i7;
                long j13 = ((long) i10) + j12;
                int length2 = str.length();
                String str4 = " at index ";
                String str5 = "Failed writing ";
                if (length2 > i10 || bArr.length - i10 < i7) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + str.charAt(length2 - 1) + " at index " + (i7 + i10));
                }
                int i21 = 0;
                while (true) {
                    j3 = 1;
                    if (i21 < length2 && (cCharAt2 = str.charAt(i21)) < 128) {
                        r0.n(bArr, j12, (byte) cCharAt2);
                        i21++;
                        j12 = 1 + j12;
                    }
                }
                if (i21 != length2) {
                    while (i21 < length2) {
                        char cCharAt5 = str.charAt(i21);
                        if (cCharAt5 >= 128 || j12 >= j13) {
                            if (cCharAt5 >= 2048 || j12 > j13 - 2) {
                                str2 = str4;
                                str3 = str5;
                                if ((cCharAt5 >= 55296 && 57343 >= cCharAt5) || j12 > j13 - 3) {
                                    if (j12 > j13 - 4) {
                                        if (55296 <= cCharAt5 && cCharAt5 <= 57343 && ((i13 = i21 + 1) == length2 || !Character.isSurrogatePair(cCharAt5, str.charAt(i13)))) {
                                            throw new t0(i21, length2);
                                        }
                                        throw new ArrayIndexOutOfBoundsException(str3 + cCharAt5 + str2 + j12);
                                    }
                                    int i22 = i21 + 1;
                                    if (i22 != length2) {
                                        char cCharAt6 = str.charAt(i22);
                                        if (Character.isSurrogatePair(cCharAt5, cCharAt6)) {
                                            int codePoint2 = Character.toCodePoint(cCharAt5, cCharAt6);
                                            j10 = 1;
                                            r0.n(bArr, j12, (byte) ((codePoint2 >>> 18) | 240));
                                            j11 = j13;
                                            r0.n(bArr, j12 + 1, (byte) (((codePoint2 >>> 12) & 63) | 128));
                                            long j14 = j12 + 3;
                                            r0.n(bArr, j12 + 2, (byte) (((codePoint2 >>> 6) & 63) | 128));
                                            j12 += 4;
                                            r0.n(bArr, j14, (byte) ((codePoint2 & 63) | 128));
                                            i21 = i22;
                                        } else {
                                            i21 = i22;
                                        }
                                    }
                                    throw new t0(i21 - 1, length2);
                                }
                                r0.n(bArr, j12, (byte) ((cCharAt5 >>> '\f') | 480));
                                long j15 = j12 + 2;
                                r0.n(bArr, j12 + 1, (byte) (((cCharAt5 >>> 6) & 63) | 128));
                                j12 += 3;
                                r0.n(bArr, j15, (byte) ((cCharAt5 & '?') | 128));
                            } else {
                                str2 = str4;
                                str3 = str5;
                                long j16 = j12 + j3;
                                r0.n(bArr, j12, (byte) ((cCharAt5 >>> 6) | 960));
                                j12 += 2;
                                r0.n(bArr, j16, (byte) ((cCharAt5 & '?') | 128));
                            }
                            j11 = j13;
                            j10 = 1;
                        } else {
                            r0.n(bArr, j12, (byte) cCharAt5);
                            j11 = j13;
                            str2 = str4;
                            j10 = j3;
                            j12 += j3;
                            str3 = str5;
                        }
                        i21++;
                        str4 = str2;
                        str5 = str3;
                        j3 = j10;
                        j13 = j11;
                    }
                }
                return (int) j12;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.d0
    public final int e(int i7, int i10, byte[] bArr) {
        int i11;
        int i12 = i7;
        switch (this.c) {
            case 0:
                break;
            default:
                if ((i12 | i10 | (bArr.length - i10)) < 0) {
                    throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i7), Integer.valueOf(i10)));
                }
                long j3 = i12;
                int i13 = (int) (((long) i10) - j3);
                if (i13 < 16) {
                    i11 = 0;
                } else {
                    int i14 = 8 - (((int) j3) & 7);
                    i11 = 0;
                    long j10 = j3;
                    while (true) {
                        if (i11 < i14) {
                            long j11 = j10 + 1;
                            if (r0.g(j10, bArr) >= 0) {
                                i11++;
                                j10 = j11;
                            }
                        } else {
                            while (true) {
                                int i15 = i11 + 8;
                                if (i15 <= i13) {
                                    if ((r0.c.h(r0.f11886f + j10, bArr) & (-9187201950435737472L)) == 0) {
                                        j10 += 8;
                                        i11 = i15;
                                    }
                                }
                            }
                            while (true) {
                                if (i11 < i13) {
                                    long j12 = j10 + 1;
                                    if (r0.g(j10, bArr) >= 0) {
                                        i11++;
                                        j10 = j12;
                                    }
                                } else {
                                    i11 = i13;
                                }
                            }
                        }
                    }
                }
                int i16 = i13 - i11;
                long j13 = j3 + ((long) i11);
                while (true) {
                    byte bG = 0;
                    while (true) {
                        if (i16 > 0) {
                            long j14 = j13 + 1;
                            bG = r0.g(j13, bArr);
                            if (bG >= 0) {
                                i16--;
                                j13 = j14;
                            } else {
                                j13 = j14;
                            }
                        }
                    }
                    if (i16 == 0) {
                        return 0;
                    }
                    int i17 = i16 - 1;
                    if (bG < -32) {
                        if (i17 == 0) {
                            return bG;
                        }
                        i16 -= 2;
                        if (bG >= -62) {
                            long j15 = j13 + 1;
                            if (r0.g(j13, bArr) <= -65) {
                                j13 = j15;
                            }
                        }
                    } else if (bG < -16) {
                        if (i17 < 2) {
                            return g(j13, bArr, bG, i17);
                        }
                        i16 -= 3;
                        long j16 = j13 + 1;
                        byte bG2 = r0.g(j13, bArr);
                        if (bG2 <= -65 && ((bG != -32 || bG2 >= -96) && (bG != -19 || bG2 < -96))) {
                            j13 += 2;
                            if (r0.g(j16, bArr) > -65) {
                            }
                        }
                    } else {
                        if (i17 < 3) {
                            return g(j13, bArr, bG, i17);
                        }
                        i16 -= 4;
                        long j17 = j13 + 1;
                        byte bG3 = r0.g(j13, bArr);
                        if (bG3 <= -65) {
                            if ((((bG3 + 112) + (bG << 28)) >> 30) == 0) {
                                long j18 = 2 + j13;
                                if (r0.g(j17, bArr) <= -65) {
                                    j13 += 3;
                                    if (r0.g(j18, bArr) > -65) {
                                    }
                                }
                            }
                        }
                    }
                }
                return -1;
        }
        while (i12 < i10 && bArr[i12] >= 0) {
            i12++;
        }
        if (i12 >= i10) {
            return 0;
        }
        while (i12 < i10) {
            int i18 = i12 + 1;
            byte b10 = bArr[i12];
            if (b10 < 0) {
                if (b10 < -32) {
                    if (i18 >= i10) {
                        return b10;
                    }
                    if (b10 >= -62) {
                        i12 += 2;
                        if (bArr[i18] > -65) {
                        }
                    }
                    return -1;
                }
                if (b10 < -16) {
                    if (i18 >= i10 - 1) {
                        return u0.a(i18, i10, bArr);
                    }
                    int i19 = i12 + 2;
                    byte b11 = bArr[i18];
                    if (b11 <= -65 && ((b10 != -32 || b11 >= -96) && (b10 != -19 || b11 < -96))) {
                        i12 += 3;
                        if (bArr[i19] > -65) {
                        }
                    }
                    return -1;
                }
                if (i18 >= i10 - 2) {
                    return u0.a(i18, i10, bArr);
                }
                int i20 = i12 + 2;
                byte b12 = bArr[i18];
                if (b12 <= -65) {
                    if ((((b12 + 112) + (b10 << 28)) >> 30) == 0) {
                        int i21 = i12 + 3;
                        if (bArr[i20] <= -65) {
                            i12 += 4;
                            if (bArr[i21] > -65) {
                            }
                        }
                    }
                }
                return -1;
            }
            i12 = i18;
        }
        return 0;
    }
}
