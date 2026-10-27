package com.google.protobuf;

import java.nio.ByteBuffer;
import p.cr2;
import p.dr2;
import p.p02;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public final class q extends p02 {
    public final /* synthetic */ int g;

    public static int y(long j, byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            q qVar = r.a;
            if (i > -12) {
                return -1;
            }
            return i;
        }
        if (i2 == 1) {
            return r.c(i, dr2.g(bArr, j));
        }
        if (i2 == 2) {
            return r.d(i, dr2.g(bArr, j), dr2.g(bArr, j + 1));
        }
        throw new AssertionError();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String u(byte[] r10, int r11, int r12) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.q.u(byte[], int, int):java.lang.String");
    }

    public final String v(ByteBuffer byteBuffer, int i, int i2) throws InvalidProtocolBufferException {
        long j;
        switch (this.g) {
            case 0:
                return p02.f(byteBuffer, i, i2);
            default:
                if ((i | i2 | ((byteBuffer.limit() - i) - i2)) < 0) {
                    throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
                }
                long j2 = dr2.c.j(dr2.g, byteBuffer) + ((long) i);
                long j3 = ((long) i2) + j2;
                char[] cArr = new char[i2];
                int i3 = 0;
                while (true) {
                    j = 1;
                    if (j2 < j3) {
                        byte bE = dr2.c.e(j2);
                        if (zu.c(bE)) {
                            j2++;
                            cArr[i3] = (char) bE;
                            i3++;
                        }
                    }
                }
                int i4 = i3;
                while (j2 < j3) {
                    long j4 = j2 + j;
                    cr2 cr2Var = dr2.c;
                    byte bE2 = cr2Var.e(j2);
                    if (zu.c(bE2)) {
                        int i5 = i4 + 1;
                        cArr[i4] = (char) bE2;
                        while (j4 < j3) {
                            byte bE3 = dr2.c.e(j4);
                            if (zu.c(bE3)) {
                                j4 += j;
                                cArr[i5] = (char) bE3;
                                i5++;
                            } else {
                                i4 = i5;
                                j2 = j4;
                            }
                        }
                        i4 = i5;
                        j2 = j4;
                    } else {
                        if (!(bE2 < -32)) {
                            if (bE2 < -16) {
                                if (j4 >= j3 - j) {
                                    throw InvalidProtocolBufferException.c();
                                }
                                long j5 = j2 + 2;
                                j2 += 3;
                                zu.e(bE2, cr2Var.e(j4), cr2Var.e(j5), cArr, i4);
                                i4++;
                            } else {
                                if (j4 >= j3 - 2) {
                                    throw InvalidProtocolBufferException.c();
                                }
                                byte bE4 = cr2Var.e(j4);
                                long j6 = j2 + 3;
                                byte bE5 = cr2Var.e(j2 + 2);
                                j2 += 4;
                                zu.b(bE2, bE4, bE5, cr2Var.e(j6), cArr, i4);
                                i4 += 2;
                            }
                        } else {
                            if (j4 >= j3) {
                                throw InvalidProtocolBufferException.c();
                            }
                            j2 += 2;
                            zu.d(bE2, cr2Var.e(j4), cArr, i4);
                            i4++;
                        }
                    }
                    j = 1;
                }
                return new String(cArr, 0, i4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x018e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int w(java.lang.CharSequence r26, byte[] r27, int r28, int r29) {
        /*
            Method dump skipped, instruction units count: 642
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.q.w(java.lang.CharSequence, byte[], int, int):int");
    }

    public final int x(int i, int i2, byte[] bArr) {
        int i3;
        int i4 = i;
        byte b = -62;
        switch (this.g) {
            case 0:
                break;
            default:
                if ((i4 | i2 | (bArr.length - i2)) < 0) {
                    throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
                }
                long j = i4;
                int i5 = (int) (((long) i2) - j);
                if (i5 < 16) {
                    i3 = 0;
                } else {
                    int i6 = 8 - (((int) j) & 7);
                    long j2 = j;
                    i3 = 0;
                    while (true) {
                        if (i3 < i6) {
                            long j3 = j2 + 1;
                            if (dr2.g(bArr, j2) >= 0) {
                                i3++;
                                j2 = j3;
                            }
                        } else {
                            while (true) {
                                int i7 = i3 + 8;
                                if (i7 <= i5) {
                                    if ((dr2.c.j(dr2.f + j2, bArr) & (-9187201950435737472L)) == 0) {
                                        j2 += 8;
                                        i3 = i7;
                                    }
                                }
                            }
                            while (true) {
                                if (i3 < i5) {
                                    long j4 = j2 + 1;
                                    if (dr2.g(bArr, j2) >= 0) {
                                        i3++;
                                        j2 = j4;
                                    }
                                } else {
                                    i3 = i5;
                                }
                            }
                        }
                    }
                }
                int i8 = i5 - i3;
                long j5 = j + ((long) i3);
                while (true) {
                    byte bG = 0;
                    while (true) {
                        if (i8 > 0) {
                            long j6 = j5 + 1;
                            bG = dr2.g(bArr, j5);
                            if (bG >= 0) {
                                i8--;
                                j5 = j6;
                            } else {
                                j5 = j6;
                            }
                        }
                    }
                    if (i8 == 0) {
                        return 0;
                    }
                    int i9 = i8 - 1;
                    if (bG < -32) {
                        if (i9 == 0) {
                            return bG;
                        }
                        i8 -= 2;
                        if (bG >= b) {
                            long j7 = j5 + 1;
                            if (dr2.g(bArr, j5) <= -65) {
                                j5 = j7;
                                b = -62;
                            }
                        }
                    } else if (bG < -16) {
                        if (i9 < 2) {
                            return y(j5, bArr, bG, i9);
                        }
                        i8 -= 3;
                        long j8 = j5 + 1;
                        byte bG2 = dr2.g(bArr, j5);
                        if (bG2 <= -65 && ((bG != -32 || bG2 >= -96) && (bG != -19 || bG2 < -96))) {
                            j5 += 2;
                            if (dr2.g(bArr, j8) <= -65) {
                                b = -62;
                            }
                        }
                    } else {
                        if (i9 < 3) {
                            return y(j5, bArr, bG, i9);
                        }
                        i8 -= 4;
                        long j9 = j5 + 1;
                        byte bG3 = dr2.g(bArr, j5);
                        if (bG3 <= -65) {
                            if ((((bG3 + 112) + (bG << 28)) >> 30) == 0) {
                                long j10 = 2 + j5;
                                if (dr2.g(bArr, j9) <= -65) {
                                    j5 += 3;
                                    if (dr2.g(bArr, j10) <= -65) {
                                        b = -62;
                                    }
                                }
                            }
                        }
                    }
                }
                return -1;
        }
        while (i4 < i2 && bArr[i4] >= 0) {
            i4++;
        }
        if (i4 < i2) {
            while (i4 < i2) {
                int i10 = i4 + 1;
                byte b2 = bArr[i4];
                if (b2 < 0) {
                    if (b2 < -32) {
                        if (i10 >= i2) {
                            return b2;
                        }
                        if (b2 >= -62) {
                            i4 += 2;
                            if (bArr[i10] > -65) {
                            }
                        }
                        return -1;
                    }
                    if (b2 < -16) {
                        if (i10 >= i2 - 1) {
                            return r.a(bArr, i10, i2);
                        }
                        int i11 = i4 + 2;
                        byte b3 = bArr[i10];
                        if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                            i4 += 3;
                            if (bArr[i11] > -65) {
                            }
                        }
                        return -1;
                    }
                    if (i10 >= i2 - 2) {
                        return r.a(bArr, i10, i2);
                    }
                    int i12 = i4 + 2;
                    byte b4 = bArr[i10];
                    if (b4 <= -65) {
                        if ((((b4 + 112) + (b2 << 28)) >> 30) == 0) {
                            int i13 = i4 + 3;
                            if (bArr[i12] <= -65) {
                                i4 += 4;
                                if (bArr[i13] > -65) {
                                }
                            }
                        }
                    }
                    return -1;
                }
                i4 = i10;
            }
        }
        return 0;
    }
}
