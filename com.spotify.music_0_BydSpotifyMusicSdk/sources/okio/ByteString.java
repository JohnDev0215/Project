package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import p.c;
import p.cs;
import p.fo;
import p.ng2;
import p.q30;
import p.rb;
import p.u4;
import p.v03;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public class ByteString implements Serializable, Comparable<ByteString> {
    public static final ByteString k = new ByteString(new byte[0]);
    private static final long serialVersionUID = 1;
    public transient int c;
    private final byte[] data;
    public transient String e;

    public ByteString(byte[] bArr) {
        v03.j(bArr, "data");
        this.data = bArr;
    }

    public static int i(ByteString byteString, ByteString byteString2) {
        byteString.getClass();
        v03.j(byteString2, "other");
        return byteString.h(byteString2.data, 0);
    }

    public static int m(ByteString byteString, ByteString byteString2) {
        byteString.getClass();
        v03.j(byteString2, "other");
        return byteString.l(byteString2.data, -1234567890);
    }

    public static /* synthetic */ ByteString q(ByteString byteString, int i, int i2, int i3) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = -1234567890;
        }
        return byteString.p(i, i2);
    }

    private final void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, NoSuchFieldException, IOException {
        int i = objectInputStream.readInt();
        if (i < 0) {
            throw new IllegalArgumentException(u4.e("byteCount < 0: ", i).toString());
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = objectInputStream.read(bArr, i2, i - i2);
            if (i3 == -1) {
                throw new EOFException();
            }
            i2 += i3;
        }
        ByteString byteString = new ByteString(bArr);
        Field declaredField = ByteString.class.getDeclaredField("data");
        declaredField.setAccessible(true);
        declaredField.set(this, byteString.data);
    }

    private final void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    public String a() {
        byte[] bArr = this.data;
        byte[] bArr2 = c.a;
        v03.j(bArr, "<this>");
        v03.j(bArr2, "map");
        byte[] bArr3 = new byte[((bArr.length + 2) / 3) * 4];
        int length = bArr.length - (bArr.length % 3);
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i + 2;
            byte b2 = bArr[i + 1];
            i += 3;
            byte b3 = bArr[i3];
            bArr3[i2] = bArr2[(b & 255) >> 2];
            bArr3[i2 + 1] = bArr2[((b & 3) << 4) | ((b2 & 255) >> 4)];
            int i4 = i2 + 3;
            bArr3[i2 + 2] = bArr2[((b2 & 15) << 2) | ((b3 & 255) >> 6)];
            i2 += 4;
            bArr3[i4] = bArr2[b3 & 63];
        }
        int length2 = bArr.length - length;
        if (length2 == 1) {
            byte b4 = bArr[i];
            bArr3[i2] = bArr2[(b4 & 255) >> 2];
            bArr3[i2 + 1] = bArr2[(b4 & 3) << 4];
            bArr3[i2 + 2] = 61;
            bArr3[i2 + 3] = 61;
        } else if (length2 == 2) {
            int i5 = i + 1;
            byte b5 = bArr[i];
            byte b6 = bArr[i5];
            bArr3[i2] = bArr2[(b5 & 255) >> 2];
            bArr3[i2 + 1] = bArr2[((b5 & 3) << 4) | ((b6 & 255) >> 4)];
            bArr3[i2 + 2] = bArr2[(b6 & 15) << 2];
            bArr3[i2 + 3] = 61;
        }
        return new String(bArr3, cs.a);
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
    public final int compareTo(okio.ByteString r10) {
        /*
            r9 = this;
            java.lang.String r0 = "other"
            p.v03.j(r10, r0)
            int r0 = r9.e()
            int r1 = r10.e()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = r3
        L13:
            r5 = -1
            r6 = 1
            if (r4 >= r2) goto L2e
            byte r7 = r9.k(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.k(r4)
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
        throw new UnsupportedOperationException("Method not decompiled: okio.ByteString.compareTo(okio.ByteString):int");
    }

    public ByteString c(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(this.data, 0, e());
        byte[] bArrDigest = messageDigest.digest();
        v03.h(bArrDigest);
        return new ByteString(bArrDigest);
    }

    public final byte[] d() {
        return this.data;
    }

    public int e() {
        return this.data.length;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            int iE = byteString.e();
            byte[] bArr = this.data;
            if (iE == bArr.length && byteString.n(0, 0, bArr.length, bArr)) {
                return true;
            }
        }
        return false;
    }

    public String f() {
        byte[] bArr = this.data;
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = zu.c;
            cArr[i] = cArr2[(b >> 4) & 15];
            i += 2;
            cArr[i2] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public final int g(int i, ByteString byteString) {
        v03.j(byteString, "other");
        return h(byteString.data, i);
    }

    public int h(byte[] bArr, int i) {
        v03.j(bArr, "other");
        int length = this.data.length - bArr.length;
        int iMax = Math.max(i, 0);
        if (iMax <= length) {
            while (!q30.c(iMax, 0, bArr.length, this.data, bArr)) {
                if (iMax != length) {
                    iMax++;
                }
            }
            return iMax;
        }
        return -1;
    }

    public int hashCode() {
        int i = this.c;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.data);
        this.c = iHashCode;
        return iHashCode;
    }

    public byte[] j() {
        return this.data;
    }

    public byte k(int i) {
        return this.data[i];
    }

    public int l(byte[] bArr, int i) {
        v03.j(bArr, "other");
        for (int iMin = Math.min(q30.s(i, this), this.data.length - bArr.length); -1 < iMin; iMin--) {
            if (q30.c(iMin, 0, bArr.length, this.data, bArr)) {
                return iMin;
            }
        }
        return -1;
    }

    public boolean n(int i, int i2, int i3, byte[] bArr) {
        v03.j(bArr, "other");
        if (i >= 0) {
            byte[] bArr2 = this.data;
            if (i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && q30.c(i, i2, i3, bArr2, bArr)) {
                return true;
            }
        }
        return false;
    }

    public boolean o(int i, ByteString byteString, int i2) {
        v03.j(byteString, "other");
        return byteString.n(0, i, i2, this.data);
    }

    public ByteString p(int i, int i2) {
        int iS = q30.s(i2, this);
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0".toString());
        }
        byte[] bArr = this.data;
        if (iS > bArr.length) {
            throw new IllegalArgumentException(u4.k(new StringBuilder("endIndex > length("), this.data.length, ')').toString());
        }
        if (iS - i >= 0) {
            return (i == 0 && iS == bArr.length) ? this : new ByteString(rb.m0(bArr, i, iS));
        }
        throw new IllegalArgumentException("endIndex < beginIndex".toString());
    }

    public ByteString r() {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i >= bArr.length) {
                return this;
            }
            byte b = bArr[i];
            if (b >= 65 && b <= 90) {
                byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                v03.i(bArrCopyOf, "copyOf(...)");
                bArrCopyOf[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArrCopyOf.length; i2++) {
                    byte b2 = bArrCopyOf[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArrCopyOf[i2] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(bArrCopyOf);
            }
            i++;
        }
    }

    public final String s() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        byte[] bArrJ = j();
        v03.j(bArrJ, "<this>");
        String str2 = new String(bArrJ, cs.a);
        this.e = str2;
        return str2;
    }

    public void t(fo foVar, int i) {
        v03.j(foVar, "buffer");
        foVar.V0(this.data, 0, i);
    }

    public String toString() {
        byte b;
        int i;
        byte[] bArr = this.data;
        if (bArr.length == 0) {
            return "[size=0]";
        }
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        loop0: while (true) {
            if (i2 >= length) {
                break;
            }
            byte b2 = bArr[i2];
            if (b2 >= 0) {
                int i5 = i4 + 1;
                if (i4 == 64) {
                    break;
                }
                if ((b2 != 10 && b2 != 13 && ((b2 >= 0 && b2 < 32) || (127 <= b2 && b2 < 160))) || b2 == 65533) {
                    break;
                }
                i3 += b2 < 65536 ? 1 : 2;
                i2++;
                while (true) {
                    i4 = i5;
                    if (i2 < length && (b = bArr[i2]) >= 0) {
                        i2++;
                        i5 = i4 + 1;
                        if (i4 == 64) {
                            break loop0;
                        }
                        if ((b != 10 && b != 13 && ((b >= 0 && b < 32) || (127 <= b && b < 160))) || b == 65533) {
                            break loop0;
                        }
                        i3 += b < 65536 ? 1 : 2;
                    } else {
                        break;
                    }
                }
            } else if ((b2 >> 5) == -2) {
                int i6 = i2 + 1;
                if (length > i6) {
                    byte b3 = bArr[i6];
                    if ((b3 & 192) == 128) {
                        int i7 = (b3 ^ 3968) ^ (b2 << 6);
                        if (i7 >= 128) {
                            i = i4 + 1;
                            if (i4 == 64) {
                                break;
                            }
                            if ((i7 != 10 && i7 != 13 && ((i7 >= 0 && i7 < 32) || (127 <= i7 && i7 < 160))) || i7 == 65533) {
                                break;
                            }
                            i3 += i7 < 65536 ? 1 : 2;
                            i2 += 2;
                            i4 = i;
                        } else if (i4 != 64) {
                            break;
                        }
                    } else if (i4 != 64) {
                        break;
                    }
                } else if (i4 != 64) {
                    break;
                }
            } else if ((b2 >> 4) == -2) {
                int i8 = i2 + 2;
                if (length > i8) {
                    byte b4 = bArr[i2 + 1];
                    if ((b4 & 192) == 128) {
                        byte b5 = bArr[i8];
                        if ((b5 & 192) == 128) {
                            int i9 = ((b5 ^ (-123008)) ^ (b4 << 6)) ^ (b2 << 12);
                            if (i9 < 2048) {
                                if (i4 != 64) {
                                    break;
                                }
                            } else if (55296 > i9 || i9 >= 57344) {
                                i = i4 + 1;
                                if (i4 == 64) {
                                    break;
                                }
                                if ((i9 != 10 && i9 != 13 && ((i9 >= 0 && i9 < 32) || (127 <= i9 && i9 < 160))) || i9 == 65533) {
                                    break;
                                }
                                i3 += i9 < 65536 ? 1 : 2;
                                i2 += 3;
                                i4 = i;
                            } else if (i4 != 64) {
                                break;
                            }
                        } else if (i4 != 64) {
                            break;
                        }
                    } else if (i4 != 64) {
                        break;
                    }
                } else if (i4 != 64) {
                    break;
                }
            } else if ((b2 >> 3) == -2) {
                int i10 = i2 + 3;
                if (length > i10) {
                    byte b6 = bArr[i2 + 1];
                    if ((b6 & 192) == 128) {
                        byte b7 = bArr[i2 + 2];
                        if ((b7 & 192) == 128) {
                            byte b8 = bArr[i10];
                            if ((b8 & 192) == 128) {
                                int i11 = (((b8 ^ 3678080) ^ (b7 << 6)) ^ (b6 << 12)) ^ (b2 << 18);
                                if (i11 > 1114111) {
                                    if (i4 != 64) {
                                        break;
                                    }
                                } else if (55296 > i11 || i11 >= 57344) {
                                    if (i11 >= 65536) {
                                        i = i4 + 1;
                                        if (i4 == 64) {
                                            break;
                                        }
                                        if ((i11 != 10 && i11 != 13 && ((i11 >= 0 && i11 < 32) || (127 <= i11 && i11 < 160))) || i11 == 65533) {
                                            break;
                                        }
                                        i3 += i11 < 65536 ? 1 : 2;
                                        i2 += 4;
                                        i4 = i;
                                    } else if (i4 != 64) {
                                        break;
                                    }
                                } else if (i4 != 64) {
                                    break;
                                }
                            } else if (i4 != 64) {
                                break;
                            }
                        } else if (i4 != 64) {
                            break;
                        }
                    } else if (i4 != 64) {
                        break;
                    }
                } else if (i4 != 64) {
                    break;
                }
            } else if (i4 != 64) {
                break;
            }
        }
        i3 = -1;
        if (i3 != -1) {
            String strS = s();
            String strSubstring = strS.substring(0, i3);
            v03.i(strSubstring, "substring(...)");
            String strE0 = ng2.e0(ng2.e0(ng2.e0(strSubstring, "\\", "\\\\"), "\n", "\\n"), "\r", "\\r");
            if (i3 >= strS.length()) {
                return "[text=" + strE0 + ']';
            }
            return "[size=" + this.data.length + " text=" + strE0 + "…]";
        }
        if (this.data.length <= 64) {
            return "[hex=" + f() + ']';
        }
        StringBuilder sb = new StringBuilder("[size=");
        sb.append(this.data.length);
        sb.append(" hex=");
        int iS = q30.s(64, this);
        byte[] bArr2 = this.data;
        if (iS > bArr2.length) {
            throw new IllegalArgumentException(u4.k(new StringBuilder("endIndex > length("), this.data.length, ')').toString());
        }
        if (iS < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex".toString());
        }
        sb.append((iS == bArr2.length ? this : new ByteString(rb.m0(bArr2, 0, iS))).f());
        sb.append("…]");
        return sb.toString();
    }
}
