package com.google.protobuf;

import com.google.protobuf.h1;
import com.google.protobuf.j1;
import com.google.protobuf.n;
import defpackage.d83;
import defpackage.gq2;
import defpackage.lk;
import java.io.IOException;
import java.lang.Character;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: TextFormat.java */
/* JADX INFO: loaded from: classes.dex */
public final class g1 {
    public static final /* synthetic */ int a = 0;

    /* JADX INFO: compiled from: TextFormat.java */
    public static class a extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        public a(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: TextFormat.java */
    public static final class b {
        public static final b b = new b(h1.a.a);
        public final h1 a;

        /* JADX INFO: compiled from: TextFormat.java */
        public static class a implements Comparable<a> {
            public final Object e;
            public final j0 f;
            public final n.f.b g;

            public a(n.f fVar, Object obj) {
                if (obj instanceof j0) {
                    this.f = (j0) obj;
                } else {
                    this.e = obj;
                }
                this.g = fVar.k().k().get(0).k.e;
            }

            public final Object a() {
                j0 j0Var = this.f;
                if (j0Var != null) {
                    return j0Var.f;
                }
                return null;
            }

            @Override // java.lang.Comparable
            public final int compareTo(a aVar) {
                a aVar2 = aVar;
                if (a() != null && aVar2.a() != null) {
                    int iOrdinal = this.g.ordinal();
                    if (iOrdinal == 0) {
                        return Integer.compare(((Integer) a()).intValue(), ((Integer) aVar2.a()).intValue());
                    }
                    if (iOrdinal == 1) {
                        return Long.compare(((Long) a()).longValue(), ((Long) aVar2.a()).longValue());
                    }
                    if (iOrdinal == 4) {
                        return Boolean.compare(((Boolean) a()).booleanValue(), ((Boolean) aVar2.a()).booleanValue());
                    }
                    if (iOrdinal == 5) {
                        String str = (String) a();
                        String str2 = (String) aVar2.a();
                        if (str != null || str2 != null) {
                            if (str != null || str2 == null) {
                                if (str == null || str2 != null) {
                                    return str.compareTo(str2);
                                }
                                return 1;
                            }
                        }
                    }
                    return 0;
                }
                return -1;
            }
        }

        public b(h1 h1Var) {
            this.a = h1Var;
        }

        public static void c(int i, int i2, List<?> list, c cVar) throws IOException {
            for (Object obj : list) {
                cVar.c(String.valueOf(i));
                cVar.c(": ");
                int i3 = i2 & 7;
                if (i3 == 0) {
                    cVar.c(g1.e(((Long) obj).longValue()));
                } else if (i3 == 1) {
                    cVar.c(String.format(null, "0x%016x", (Long) obj));
                } else if (i3 == 2) {
                    try {
                        lk lkVar = (lk) obj;
                        j1 j1Var = j1.f;
                        j1.a aVar = new j1.a();
                        try {
                            try {
                                g gVarT = lkVar.t();
                                aVar.x(gVarT);
                                gVarT.a(0);
                                j1 j1VarG = aVar.build();
                                cVar.c("{");
                                cVar.a();
                                cVar.b.append("  ");
                                d(j1VarG, cVar);
                                cVar.b();
                                cVar.c("}");
                            } catch (d0 e) {
                                throw e;
                            }
                        } catch (IOException e2) {
                            throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e2);
                        }
                    } catch (d0 unused) {
                        cVar.c("\"");
                        int i4 = g1.a;
                        cVar.c(d83.a((lk) obj));
                        cVar.c("\"");
                    }
                } else if (i3 == 3) {
                    d((j1) obj, cVar);
                } else {
                    if (i3 != 5) {
                        throw new IllegalArgumentException(gq2.e("Bad tag: ", i2));
                    }
                    cVar.c(String.format(null, "0x%08x", (Integer) obj));
                }
                cVar.a();
            }
        }

        public static void d(j1 j1Var, c cVar) throws IOException {
            for (Map.Entry entry : ((Map) j1Var.e.clone()).entrySet()) {
                int iIntValue = ((Integer) entry.getKey()).intValue();
                j1.b bVar = (j1.b) entry.getValue();
                c(iIntValue, 0, bVar.a, cVar);
                c(iIntValue, 5, bVar.b, cVar);
                c(iIntValue, 1, bVar.c, cVar);
                c(iIntValue, 2, bVar.d, cVar);
                for (j1 j1Var2 : bVar.e) {
                    cVar.c(((Integer) entry.getKey()).toString());
                    cVar.c(" {");
                    cVar.a();
                    cVar.b.append("  ");
                    d(j1Var2, cVar);
                    cVar.b();
                    cVar.c("}");
                    cVar.a();
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x00b9  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00bc A[RETURN] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void a(com.google.protobuf.t0 r8, com.google.protobuf.g1.c r9) {
            /*
                Method dump skipped, instruction units count: 327
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.g1.b.a(com.google.protobuf.t0, com.google.protobuf.g1$c):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x004d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void b(com.google.protobuf.n.f r7, java.lang.Object r8, com.google.protobuf.g1.c r9) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 392
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.g1.b.b(com.google.protobuf.n$f, java.lang.Object, com.google.protobuf.g1$c):void");
        }
    }

    /* JADX INFO: compiled from: TextFormat.java */
    public static final class c {
        public final Appendable a;
        public final StringBuilder b = new StringBuilder();
        public boolean c = false;

        public c(StringBuilder sb) {
            this.a = sb;
        }

        public final void a() throws IOException {
            this.a.append("\n");
            this.c = true;
        }

        public final void b() {
            StringBuilder sb = this.b;
            int length = sb.length();
            if (length == 0) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            sb.setLength(length - 2);
        }

        public final void c(String str) throws IOException {
            boolean z = this.c;
            Appendable appendable = this.a;
            if (z) {
                this.c = false;
                appendable.append(this.b);
            }
            appendable.append(str);
        }
    }

    static {
        h1 h1Var = h1.a.a;
    }

    public static int a(byte b2) {
        if (48 > b2 || b2 > 57) {
            return ((97 > b2 || b2 > 122) ? b2 - 65 : b2 - 97) + 10;
        }
        return b2 - 48;
    }

    public static boolean b(byte b2) {
        return (48 <= b2 && b2 <= 57) || (97 <= b2 && b2 <= 102) || (65 <= b2 && b2 <= 70);
    }

    public static long c(String str, boolean z, boolean z2) {
        int i;
        int i2 = 0;
        if (str.startsWith("-", 0)) {
            if (!z) {
                throw new NumberFormatException("Number must be positive: ".concat(str));
            }
            i2 = 1;
        }
        int i3 = i2;
        if (str.startsWith("0x", i2)) {
            i2 += 2;
            i = 16;
        } else {
            i = str.startsWith("0", i2) ? 8 : 10;
        }
        String strSubstring = str.substring(i2);
        if (strSubstring.length() < 16) {
            long j = Long.parseLong(strSubstring, i);
            if (i3 != 0) {
                j = -j;
            }
            if (z2) {
                return j;
            }
            if (z) {
                if (j > 2147483647L || j < -2147483648L) {
                    throw new NumberFormatException("Number out of range for 32-bit signed integer: ".concat(str));
                }
                return j;
            }
            if (j >= 4294967296L || j < 0) {
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: ".concat(str));
            }
            return j;
        }
        BigInteger bigInteger = new BigInteger(strSubstring, i);
        if (i3 != 0) {
            bigInteger = bigInteger.negate();
        }
        if (z2) {
            if (z) {
                if (bigInteger.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: ".concat(str));
                }
            } else if (bigInteger.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: ".concat(str));
            }
        } else if (z) {
            if (bigInteger.bitLength() > 31) {
                throw new NumberFormatException("Number out of range for 32-bit signed integer: ".concat(str));
            }
        } else if (bigInteger.bitLength() > 32) {
            throw new NumberFormatException("Number out of range for 32-bit unsigned integer: ".concat(str));
        }
        return bigInteger.longValue();
    }

    public static lk.f d(String str) throws a {
        int i;
        int i2;
        int i3;
        int length;
        String string = str.toString();
        lk.f fVar = lk.f;
        lk.f fVar2 = new lk.f(string.getBytes(c0.a));
        byte[] bArr = fVar2.h;
        int length2 = bArr.length;
        byte[] bArr2 = new byte[length2];
        int i4 = 0;
        int i5 = 0;
        while (i4 < bArr.length) {
            byte b2 = bArr[i4];
            if (b2 == 92) {
                i4++;
                if (i4 >= bArr.length) {
                    throw new a("Invalid escape sequence: '\\' at end of string.");
                }
                byte b3 = bArr[i4];
                if (48 <= b3 && b3 <= 55) {
                    int iA = a(b3);
                    int i6 = i4 + 1;
                    if (i6 < bArr.length) {
                        byte b4 = bArr[i6];
                        if (48 <= b4 && b4 <= 55) {
                            iA = (iA * 8) + a(b4);
                            i4 = i6;
                        }
                    }
                    int i7 = i4 + 1;
                    if (i7 < bArr.length) {
                        byte b5 = bArr[i7];
                        if (48 <= b5 && b5 <= 55) {
                            iA = (iA * 8) + a(b5);
                            i4 = i7;
                        }
                    }
                    i = i5 + 1;
                    bArr2[i5] = (byte) iA;
                } else {
                    if (b3 == 34) {
                        i2 = i5 + 1;
                        bArr2[i5] = 34;
                    } else if (b3 == 39) {
                        i2 = i5 + 1;
                        bArr2[i5] = 39;
                    } else if (b3 != 63) {
                        if (b3 == 85) {
                            int i8 = i4 + 1;
                            i3 = i8 + 7;
                            if (i3 >= bArr.length) {
                                throw new a("Invalid escape sequence: '\\U' with too few hex chars");
                            }
                            int iA2 = 0;
                            int i9 = i8;
                            while (true) {
                                int i10 = i8 + 8;
                                if (i9 < i10) {
                                    byte b6 = bArr[i9];
                                    if (!b(b6)) {
                                        throw new a("Invalid escape sequence: '\\U' with too few hex chars");
                                    }
                                    iA2 = (iA2 << 4) | a(b6);
                                    i9++;
                                } else {
                                    if (!Character.isValidCodePoint(iA2)) {
                                        throw new a("Invalid escape sequence: '\\U" + fVar2.w(i8, i10).z() + "' is not a valid code point value");
                                    }
                                    Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(iA2);
                                    if (unicodeBlockOf.equals(Character.UnicodeBlock.LOW_SURROGATES) || unicodeBlockOf.equals(Character.UnicodeBlock.HIGH_SURROGATES) || unicodeBlockOf.equals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES)) {
                                        throw new a("Invalid escape sequence: '\\U" + fVar2.w(i8, i10).z() + "' refers to a surrogate code unit");
                                    }
                                    byte[] bytes = new String(new int[]{iA2}, 0, 1).getBytes(StandardCharsets.UTF_8);
                                    System.arraycopy(bytes, 0, bArr2, i5, bytes.length);
                                    length = bytes.length;
                                }
                            }
                        } else if (b3 == 92) {
                            i2 = i5 + 1;
                            bArr2[i5] = 92;
                        } else if (b3 == 102) {
                            i2 = i5 + 1;
                            bArr2[i5] = 12;
                        } else if (b3 == 110) {
                            i2 = i5 + 1;
                            bArr2[i5] = 10;
                        } else if (b3 == 114) {
                            i2 = i5 + 1;
                            bArr2[i5] = 13;
                        } else if (b3 == 120) {
                            i4++;
                            if (i4 >= bArr.length || !b(bArr[i4])) {
                                throw new a("Invalid escape sequence: '\\x' with no digits");
                            }
                            int iA3 = a(bArr[i4]);
                            int i11 = i4 + 1;
                            if (i11 < bArr.length && b(bArr[i11])) {
                                iA3 = (iA3 * 16) + a(bArr[i11]);
                                i4 = i11;
                            }
                            i = i5 + 1;
                            bArr2[i5] = (byte) iA3;
                        } else if (b3 == 97) {
                            i2 = i5 + 1;
                            bArr2[i5] = 7;
                        } else if (b3 != 98) {
                            switch (b3) {
                                case 116:
                                    i2 = i5 + 1;
                                    bArr2[i5] = 9;
                                    break;
                                case 117:
                                    int i12 = i4 + 1;
                                    i3 = i12 + 3;
                                    if (i3 < bArr.length && b(bArr[i12])) {
                                        int i13 = i12 + 1;
                                        if (b(bArr[i13])) {
                                            int i14 = i12 + 2;
                                            if (b(bArr[i14]) && b(bArr[i3])) {
                                                char cA = (char) ((a(bArr[i12]) << 12) | (a(bArr[i13]) << 8) | (a(bArr[i14]) << 4) | a(bArr[i3]));
                                                if (Character.isSurrogate(cA)) {
                                                    throw new a("Invalid escape sequence: '\\u' refers to a surrogate");
                                                }
                                                byte[] bytes2 = Character.toString(cA).getBytes(StandardCharsets.UTF_8);
                                                System.arraycopy(bytes2, 0, bArr2, i5, bytes2.length);
                                                length = bytes2.length;
                                                break;
                                            }
                                        }
                                    }
                                    throw new a("Invalid escape sequence: '\\u' with too few hex chars");
                                case 118:
                                    i2 = i5 + 1;
                                    bArr2[i5] = 11;
                                    break;
                                default:
                                    throw new a("Invalid escape sequence: '\\" + ((char) b3) + '\'');
                            }
                        } else {
                            i2 = i5 + 1;
                            bArr2[i5] = 8;
                        }
                        i5 += length;
                        i4 = i3;
                        i4++;
                    } else {
                        i2 = i5 + 1;
                        bArr2[i5] = 63;
                    }
                    i5 = i2;
                    i4++;
                }
            } else {
                i = i5 + 1;
                bArr2[i5] = b2;
            }
            i5 = i;
            i4++;
        }
        return length2 == i5 ? new lk.f(bArr2) : lk.d(bArr2, 0, i5);
    }

    public static String e(long j) {
        return j >= 0 ? Long.toString(j) : BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }
}
