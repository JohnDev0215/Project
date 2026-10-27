package r9;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: r9.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2322l implements Serializable, Comparable {
    public static final C2322l o = new C2322l(new byte[0]);
    public final byte[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient int f21933m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient String f21934n;

    public C2322l(byte[] bArr) {
        A7.m.f(bArr, "data");
        this.l = bArr;
    }

    public static int g(C2322l c2322l, C2322l c2322l2) {
        c2322l.getClass();
        A7.m.f(c2322l2, "other");
        return c2322l.f(0, c2322l2.l);
    }

    public static int k(C2322l c2322l, C2322l c2322l2) {
        c2322l.getClass();
        A7.m.f(c2322l2, "other");
        return c2322l.j(-1234567890, c2322l2.l);
    }

    public static /* synthetic */ C2322l o(C2322l c2322l, int i7, int i10, int i11) {
        if ((i11 & 1) != 0) {
            i7 = 0;
        }
        if ((i11 & 2) != 0) {
            i10 = -1234567890;
        }
        return c2322l.n(i7, i10);
    }

    public String a() {
        byte[] bArr = AbstractC2311a.f21913a;
        byte[] bArr2 = this.l;
        A7.m.f(bArr2, "<this>");
        A7.m.f(bArr, "map");
        byte[] bArr3 = new byte[((bArr2.length + 2) / 3) * 4];
        int length = bArr2.length - (bArr2.length % 3);
        int i7 = 0;
        int i10 = 0;
        while (i7 < length) {
            byte b10 = bArr2[i7];
            int i11 = i7 + 2;
            byte b11 = bArr2[i7 + 1];
            i7 += 3;
            byte b12 = bArr2[i11];
            bArr3[i10] = bArr[(b10 & 255) >> 2];
            bArr3[i10 + 1] = bArr[((b10 & 3) << 4) | ((b11 & 255) >> 4)];
            int i12 = i10 + 3;
            bArr3[i10 + 2] = bArr[((b11 & 15) << 2) | ((b12 & 255) >> 6)];
            i10 += 4;
            bArr3[i12] = bArr[b12 & 63];
        }
        int length2 = bArr2.length - length;
        if (length2 == 1) {
            byte b13 = bArr2[i7];
            bArr3[i10] = bArr[(b13 & 255) >> 2];
            bArr3[i10 + 1] = bArr[(b13 & 3) << 4];
            bArr3[i10 + 2] = 61;
            bArr3[i10 + 3] = 61;
        } else if (length2 == 2) {
            int i13 = i7 + 1;
            byte b14 = bArr2[i7];
            byte b15 = bArr2[i13];
            bArr3[i10] = bArr[(b14 & 255) >> 2];
            bArr3[i10 + 1] = bArr[((b14 & 3) << 4) | ((b15 & 255) >> 4)];
            bArr3[i10 + 2] = bArr[(b15 & 15) << 2];
            bArr3[i10 + 3] = 61;
        }
        return new String(bArr3, Q8.a.f7211a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0031, code lost:
    
        if (r0 < r1) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:?, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0028, code lost:
    
        if (r7 < r8) goto L9;
     */
    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int compareTo(r9.C2322l r10) {
        /*
            r9 = this;
            java.lang.String r0 = "other"
            A7.m.f(r10, r0)
            int r0 = r9.d()
            int r1 = r10.d()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = r3
        L13:
            r5 = -1
            r6 = 1
            if (r4 >= r2) goto L2e
            byte r7 = r9.i(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.i(r4)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L28
            int r4 = r4 + 1
            goto L13
        L28:
            if (r7 >= r8) goto L2c
        L2a:
            r3 = r5
            goto L34
        L2c:
            r3 = r6
            goto L34
        L2e:
            if (r0 != r1) goto L31
            goto L34
        L31:
            if (r0 >= r1) goto L2c
            goto L2a
        L34:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: r9.C2322l.compareTo(r9.l):int");
    }

    public C2322l c(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(this.l, 0, d());
        byte[] bArrDigest = messageDigest.digest();
        A7.m.c(bArrDigest);
        return new C2322l(bArrDigest);
    }

    public int d() {
        return this.l.length;
    }

    public String e() {
        byte[] bArr = this.l;
        char[] cArr = new char[bArr.length * 2];
        int i7 = 0;
        for (byte b10 : bArr) {
            int i10 = i7 + 1;
            char[] cArr2 = s9.b.f22381a;
            cArr[i7] = cArr2[(b10 >> 4) & 15];
            i7 += 2;
            cArr[i10] = cArr2[b10 & 15];
        }
        return new String(cArr);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C2322l) {
            C2322l c2322l = (C2322l) obj;
            int iD = c2322l.d();
            byte[] bArr = this.l;
            if (iD == bArr.length && c2322l.l(0, 0, bArr.length, bArr)) {
                return true;
            }
        }
        return false;
    }

    public int f(int i7, byte[] bArr) {
        A7.m.f(bArr, "other");
        byte[] bArr2 = this.l;
        int length = bArr2.length - bArr.length;
        int iMax = Math.max(i7, 0);
        if (iMax <= length) {
            while (!AbstractC2312b.a(iMax, 0, bArr.length, bArr2, bArr)) {
                if (iMax != length) {
                    iMax++;
                }
            }
            return iMax;
        }
        return -1;
    }

    public byte[] h() {
        return this.l;
    }

    public int hashCode() {
        int i7 = this.f21933m;
        if (i7 != 0) {
            return i7;
        }
        int iHashCode = Arrays.hashCode(this.l);
        this.f21933m = iHashCode;
        return iHashCode;
    }

    public byte i(int i7) {
        return this.l[i7];
    }

    public int j(int i7, byte[] bArr) {
        A7.m.f(bArr, "other");
        if (i7 == -1234567890) {
            i7 = d();
        }
        byte[] bArr2 = this.l;
        for (int iMin = Math.min(i7, bArr2.length - bArr.length); -1 < iMin; iMin--) {
            if (AbstractC2312b.a(iMin, 0, bArr.length, bArr2, bArr)) {
                return iMin;
            }
        }
        return -1;
    }

    public boolean l(int i7, int i10, int i11, byte[] bArr) {
        A7.m.f(bArr, "other");
        if (i7 >= 0) {
            byte[] bArr2 = this.l;
            if (i7 <= bArr2.length - i11 && i10 >= 0 && i10 <= bArr.length - i11 && AbstractC2312b.a(i7, i10, i11, bArr2, bArr)) {
                return true;
            }
        }
        return false;
    }

    public boolean m(int i7, C2322l c2322l, int i10) {
        A7.m.f(c2322l, "other");
        return c2322l.l(0, i7, i10, this.l);
    }

    public C2322l n(int i7, int i10) {
        if (i10 == -1234567890) {
            i10 = d();
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.l;
        if (i10 > bArr.length) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.j(new StringBuilder("endIndex > length("), bArr.length, ')').toString());
        }
        if (i10 - i7 >= 0) {
            return (i7 == 0 && i10 == bArr.length) ? this : new C2322l(AbstractC1996k.H(i7, i10, bArr));
        }
        throw new IllegalArgumentException("endIndex < beginIndex");
    }

    public C2322l p() {
        int i7 = 0;
        while (true) {
            byte[] bArr = this.l;
            if (i7 >= bArr.length) {
                return this;
            }
            byte b10 = bArr[i7];
            if (b10 >= 65 && b10 <= 90) {
                byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                A7.m.e(bArrCopyOf, "copyOf(...)");
                bArrCopyOf[i7] = (byte) (b10 + 32);
                for (int i10 = i7 + 1; i10 < bArrCopyOf.length; i10++) {
                    byte b11 = bArrCopyOf[i10];
                    if (b11 >= 65 && b11 <= 90) {
                        bArrCopyOf[i10] = (byte) (b11 + 32);
                    }
                }
                return new C2322l(bArrCopyOf);
            }
            i7++;
        }
    }

    public byte[] q() {
        byte[] bArr = this.l;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        A7.m.e(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    public final String r() {
        String str = this.f21934n;
        if (str != null) {
            return str;
        }
        byte[] bArrH = h();
        A7.m.f(bArrH, "<this>");
        String str2 = new String(bArrH, Q8.a.f7211a);
        this.f21934n = str2;
        return str2;
    }

    public void s(C2319i c2319i, int i7) {
        A7.m.f(c2319i, "buffer");
        c2319i.W(this.l, 0, i7);
    }

    public String toString() {
        byte b10;
        int i7;
        C2322l c2322l = this;
        byte[] bArr = c2322l.l;
        if (bArr.length == 0) {
            return "[size=0]";
        }
        int length = bArr.length;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        loop0: while (true) {
            if (i10 >= length) {
                break;
            }
            byte b11 = bArr[i10];
            if (b11 >= 0) {
                int i13 = i12 + 1;
                if (i12 == 64) {
                    break;
                }
                if ((b11 != 10 && b11 != 13 && ((b11 >= 0 && b11 < 32) || (127 <= b11 && b11 < 160))) || b11 == 65533) {
                    break;
                }
                i11 += b11 < 65536 ? 1 : 2;
                i10++;
                while (true) {
                    i12 = i13;
                    if (i10 < length && (b10 = bArr[i10]) >= 0) {
                        i10++;
                        i13 = i12 + 1;
                        if (i12 == 64) {
                            break loop0;
                        }
                        if ((b10 != 10 && b10 != 13 && ((b10 >= 0 && b10 < 32) || (127 <= b10 && b10 < 160))) || b10 == 65533) {
                            break loop0;
                        }
                        i11 += b10 < 65536 ? 1 : 2;
                    } else {
                        break;
                    }
                }
            } else if ((b11 >> 5) == -2) {
                int i14 = i10 + 1;
                if (length > i14) {
                    byte b12 = bArr[i14];
                    if ((b12 & 192) == 128) {
                        int i15 = (b12 ^ 3968) ^ (b11 << 6);
                        if (i15 >= 128) {
                            i7 = i12 + 1;
                            if (i12 == 64) {
                                break;
                            }
                            if ((i15 != 10 && i15 != 13 && ((i15 >= 0 && i15 < 32) || (127 <= i15 && i15 < 160))) || i15 == 65533) {
                                break;
                            }
                            i11 += i15 < 65536 ? 1 : 2;
                            i10 += 2;
                            i12 = i7;
                        } else if (i12 != 64) {
                            break;
                        }
                    } else if (i12 != 64) {
                        break;
                    }
                } else if (i12 != 64) {
                    break;
                }
            } else if ((b11 >> 4) == -2) {
                int i16 = i10 + 2;
                if (length > i16) {
                    byte b13 = bArr[i10 + 1];
                    if ((b13 & 192) == 128) {
                        byte b14 = bArr[i16];
                        if ((b14 & 192) == 128) {
                            int i17 = ((b14 ^ (-123008)) ^ (b13 << 6)) ^ (b11 << 12);
                            if (i17 < 2048) {
                                if (i12 != 64) {
                                    break;
                                }
                            } else if (55296 > i17 || i17 >= 57344) {
                                i7 = i12 + 1;
                                if (i12 == 64) {
                                    break;
                                }
                                if ((i17 != 10 && i17 != 13 && ((i17 >= 0 && i17 < 32) || (127 <= i17 && i17 < 160))) || i17 == 65533) {
                                    break;
                                }
                                i11 += i17 < 65536 ? 1 : 2;
                                i10 += 3;
                                i12 = i7;
                            } else if (i12 != 64) {
                                break;
                            }
                        } else if (i12 != 64) {
                            break;
                        }
                    } else if (i12 != 64) {
                        break;
                    }
                } else if (i12 != 64) {
                    break;
                }
            } else if ((b11 >> 3) == -2) {
                int i18 = i10 + 3;
                if (length > i18) {
                    byte b15 = bArr[i10 + 1];
                    if ((b15 & 192) == 128) {
                        byte b16 = bArr[i10 + 2];
                        if ((b16 & 192) == 128) {
                            byte b17 = bArr[i18];
                            if ((b17 & 192) == 128) {
                                int i19 = (((b17 ^ 3678080) ^ (b16 << 6)) ^ (b15 << 12)) ^ (b11 << 18);
                                if (i19 > 1114111) {
                                    if (i12 != 64) {
                                        break;
                                    }
                                } else if (55296 > i19 || i19 >= 57344) {
                                    if (i19 >= 65536) {
                                        i7 = i12 + 1;
                                        if (i12 == 64) {
                                            break;
                                        }
                                        if ((i19 != 10 && i19 != 13 && ((i19 >= 0 && i19 < 32) || (127 <= i19 && i19 < 160))) || i19 == 65533) {
                                            break;
                                        }
                                        i11 += i19 < 65536 ? 1 : 2;
                                        i10 += 4;
                                        i12 = i7;
                                    } else if (i12 != 64) {
                                        break;
                                    }
                                } else if (i12 != 64) {
                                    break;
                                }
                            } else if (i12 != 64) {
                                break;
                            }
                        } else if (i12 != 64) {
                            break;
                        }
                    } else if (i12 != 64) {
                        break;
                    }
                } else if (i12 != 64) {
                    break;
                }
            } else if (i12 != 64) {
                break;
            }
        }
        i11 = -1;
        if (i11 == -1) {
            if (bArr.length <= 64) {
                return "[hex=" + e() + ']';
            }
            StringBuilder sb = new StringBuilder("[size=");
            sb.append(bArr.length);
            sb.append(" hex=");
            if (64 > bArr.length) {
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.j(new StringBuilder("endIndex > length("), bArr.length, ')').toString());
            }
            if (64 != bArr.length) {
                c2322l = new C2322l(AbstractC1996k.H(0, 64, bArr));
            }
            sb.append(c2322l.e());
            sb.append("…]");
            return sb.toString();
        }
        String strR = r();
        String strSubstring = strR.substring(0, i11);
        A7.m.e(strSubstring, "substring(...)");
        String strX0 = Q8.o.x0(Q8.o.x0(Q8.o.x0(strSubstring, "\\", "\\\\"), "\n", "\\n"), "\r", "\\r");
        if (i11 >= strR.length()) {
            return "[text=" + strX0 + ']';
        }
        return "[size=" + bArr.length + " text=" + strX0 + "…]";
    }
}
