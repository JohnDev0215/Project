package defpackage;

/* JADX INFO: renamed from: Im, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0156Im extends AbstractC0102Fm {
    public static int d(byte[] bArr, int i, long j, int i2) {
        if (i2 == 0) {
            return AbstractC0174Jm.c(i);
        }
        if (i2 == 1) {
            return AbstractC0174Jm.a(i, AbstractC0066Dm.g(bArr, j));
        }
        if (i2 == 2) {
            return AbstractC0174Jm.f(i, AbstractC0066Dm.g(bArr, j), AbstractC0066Dm.g(bArr, j + 1));
        }
        throw new AssertionError();
    }

    @Override // defpackage.AbstractC0102Fm
    public String a(byte[] bArr, int i, int i2) throws C0155Il {
        if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte bG = AbstractC0066Dm.g(bArr, i);
            if (!AbstractC0084Em.b(bG)) {
                break;
            }
            i++;
            cArr[i4] = (char) bG;
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte bG2 = AbstractC0066Dm.g(bArr, i);
            if (AbstractC0084Em.b(bG2)) {
                int i7 = i5 + 1;
                cArr[i5] = (char) bG2;
                i = i6;
                while (true) {
                    i5 = i7;
                    if (i < i3) {
                        byte bG3 = AbstractC0066Dm.g(bArr, i);
                        if (!AbstractC0084Em.b(bG3)) {
                            break;
                        }
                        i++;
                        i7 = i5 + 1;
                        cArr[i5] = (char) bG3;
                    }
                }
            } else {
                if (!(bG2 < -32)) {
                    if (bG2 < -16) {
                        if (i6 >= i3 - 1) {
                            throw C0155Il.b();
                        }
                        int i8 = i6 + 1;
                        AbstractC0084Em.d(bG2, AbstractC0066Dm.g(bArr, i6), AbstractC0066Dm.g(bArr, i8), cArr, i5);
                        i = i8 + 1;
                        i5++;
                    } else {
                        if (i6 >= i3 - 2) {
                            throw C0155Il.b();
                        }
                        int i9 = i6 + 1;
                        byte bG4 = AbstractC0066Dm.g(bArr, i6);
                        int i10 = i9 + 1;
                        AbstractC0084Em.a(bG2, bG4, AbstractC0066Dm.g(bArr, i9), AbstractC0066Dm.g(bArr, i10), cArr, i5);
                        i5 = i5 + 1 + 1;
                        i = i10 + 1;
                    }
                } else {
                    if (i6 >= i3) {
                        throw C0155Il.b();
                    }
                    AbstractC0084Em.c(bG2, AbstractC0066Dm.g(bArr, i6), cArr, i5);
                    i = i6 + 1;
                    i5++;
                }
            }
        }
        return new String(cArr, 0, i5);
    }

    @Override // defpackage.AbstractC0102Fm
    public int b(CharSequence charSequence, byte[] bArr, int i, int i2) {
        char c;
        long j;
        long j2;
        int i3;
        char cCharAt;
        long j3 = i;
        long j4 = ((long) i2) + j3;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            StringBuilder sbF = Z7.f("Failed writing ");
            sbF.append(charSequence.charAt(length - 1));
            sbF.append(" at index ");
            sbF.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sbF.toString());
        }
        int i4 = 0;
        while (true) {
            c = 128;
            j = 1;
            if (i4 >= length || (cCharAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            AbstractC0066Dm.q(bArr, j3, (byte) cCharAt);
            i4++;
            j3 = 1 + j3;
        }
        if (i4 == length) {
            return (int) j3;
        }
        while (i4 < length) {
            char cCharAt2 = charSequence.charAt(i4);
            if (cCharAt2 >= c || j3 >= j4) {
                if (cCharAt2 < 2048 && j3 <= j4 - 2) {
                    long j5 = j3 + j;
                    AbstractC0066Dm.q(bArr, j3, (byte) ((cCharAt2 >>> 6) | 960));
                    AbstractC0066Dm.q(bArr, j5, (byte) ((cCharAt2 & '?') | 128));
                    j3 = j5 + j;
                } else {
                    if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || j3 > j4 - 3) {
                        if (j3 > j4 - 4) {
                            if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i3)))) {
                                throw new C0138Hm(i4, length);
                            }
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + j3);
                        }
                        int i5 = i4 + 1;
                        if (i5 != length) {
                            char cCharAt3 = charSequence.charAt(i5);
                            if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                long j6 = j3 + 1;
                                AbstractC0066Dm.q(bArr, j3, (byte) ((codePoint >>> 18) | 240));
                                long j7 = 1 + j6;
                                AbstractC0066Dm.q(bArr, j6, (byte) (((codePoint >>> 12) & 63) | 128));
                                long j8 = 1 + j7;
                                AbstractC0066Dm.q(bArr, j7, (byte) (((codePoint >>> 6) & 63) | 128));
                                j = 1;
                                AbstractC0066Dm.q(bArr, j8, (byte) ((codePoint & 63) | 128));
                                i4 = i5;
                                j3 = j8 + 1;
                            } else {
                                i4 = i5;
                            }
                        }
                        throw new C0138Hm(i4 - 1, length);
                    }
                    long j9 = j3 + j;
                    AbstractC0066Dm.q(bArr, j3, (byte) ((cCharAt2 >>> '\f') | 480));
                    long j10 = j + j9;
                    AbstractC0066Dm.q(bArr, j9, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                    j2 = j10 + 1;
                    AbstractC0066Dm.q(bArr, j10, (byte) ((cCharAt2 & '?') | 128));
                    j = 1;
                }
                i4++;
                c = 128;
            } else {
                j2 = j3 + j;
                AbstractC0066Dm.q(bArr, j3, (byte) cCharAt2);
            }
            j3 = j2;
            i4++;
            c = 128;
        }
        return (int) j3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0036, code lost:
    
        if (defpackage.AbstractC0066Dm.g(r25, r8) > (-65)) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0067, code lost:
    
        if (defpackage.AbstractC0066Dm.g(r25, r8) > (-65)) goto L32;
     */
    @Override // defpackage.AbstractC0102Fm
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int c(int r24, byte[] r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0156Im.c(int, byte[], int, int):int");
    }
}
