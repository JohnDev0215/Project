package com.squareup.moshi;

import com.squareup.moshi.JsonReader;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import okio.ByteString;
import p.cs;
import p.fo;
import p.ko;
import p.qo;
import p.w02;

/* JADX INFO: loaded from: classes.dex */
final class h extends JsonReader {
    private static final ByteString v;
    private static final ByteString w;
    private static final ByteString x;
    private static final ByteString y;
    private static final ByteString z;
    private final ko o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final fo f67p;
    private int q;
    private long r;
    private int s;
    private String t;
    private k u;

    static {
        ByteString byteString = ByteString.k;
        v = qo.p("'\\");
        w = qo.p("\"\\");
        x = qo.p("{}[]:, \n\t\r\f/\\;#=");
        y = qo.p("\n\r");
        z = qo.p("*/");
    }

    public h(ko koVar) {
        this.q = 0;
        if (koVar == null) {
            throw new NullPointerException("source == null");
        }
        this.o = koVar;
        this.f67p = koVar.d();
        K0(6);
    }

    private void T0() throws JsonEncodingException {
        if (!this.m) {
            throw R0("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private int U0() throws EOFException, JsonEncodingException {
        int[] iArr = this.e;
        int i = this.c;
        int i2 = iArr[i - 1];
        if (i2 == 1) {
            iArr[i - 1] = 2;
        } else if (i2 == 2) {
            int iY0 = Y0(true);
            this.f67p.readByte();
            if (iY0 != 44) {
                if (iY0 != 59) {
                    if (iY0 != 93) {
                        throw R0("Unterminated array");
                    }
                    this.q = 4;
                    return 4;
                }
                T0();
            }
        } else {
            if (i2 == 3 || i2 == 5) {
                iArr[i - 1] = 4;
                if (i2 == 5) {
                    int iY02 = Y0(true);
                    this.f67p.readByte();
                    if (iY02 != 44) {
                        if (iY02 != 59) {
                            if (iY02 != 125) {
                                throw R0("Unterminated object");
                            }
                            this.q = 2;
                            return 2;
                        }
                        T0();
                    }
                }
                int iY03 = Y0(true);
                if (iY03 == 34) {
                    this.f67p.readByte();
                    this.q = 13;
                    return 13;
                }
                if (iY03 == 39) {
                    this.f67p.readByte();
                    T0();
                    this.q = 12;
                    return 12;
                }
                if (iY03 != 125) {
                    T0();
                    if (!X0((char) iY03)) {
                        throw R0("Expected name");
                    }
                    this.q = 14;
                    return 14;
                }
                if (i2 == 5) {
                    throw R0("Expected name");
                }
                this.f67p.readByte();
                this.q = 2;
                return 2;
            }
            if (i2 == 4) {
                iArr[i - 1] = 5;
                int iY04 = Y0(true);
                this.f67p.readByte();
                if (iY04 != 58) {
                    if (iY04 != 61) {
                        throw R0("Expected ':'");
                    }
                    T0();
                    if (this.o.a(1L) && this.f67p.G0(0L) == 62) {
                        this.f67p.readByte();
                    }
                }
            } else if (i2 == 6) {
                iArr[i - 1] = 7;
            } else if (i2 == 7) {
                if (Y0(false) == -1) {
                    this.q = 18;
                    return 18;
                }
                T0();
            } else {
                if (i2 == 9) {
                    this.u.c();
                    this.u = null;
                    this.c--;
                    return U0();
                }
                if (i2 == 8) {
                    throw new IllegalStateException("JsonReader is closed");
                }
            }
        }
        int iY05 = Y0(true);
        if (iY05 == 34) {
            this.f67p.readByte();
            this.q = 9;
            return 9;
        }
        if (iY05 == 39) {
            T0();
            this.f67p.readByte();
            this.q = 8;
            return 8;
        }
        if (iY05 != 44 && iY05 != 59) {
            if (iY05 == 91) {
                this.f67p.readByte();
                this.q = 3;
                return 3;
            }
            if (iY05 != 93) {
                if (iY05 == 123) {
                    this.f67p.readByte();
                    this.q = 1;
                    return 1;
                }
                int iB1 = b1();
                if (iB1 != 0) {
                    return iB1;
                }
                int iC1 = c1();
                if (iC1 != 0) {
                    return iC1;
                }
                if (!X0(this.f67p.G0(0L))) {
                    throw R0("Expected value");
                }
                T0();
                this.q = 10;
                return 10;
            }
            if (i2 == 1) {
                this.f67p.readByte();
                this.q = 4;
                return 4;
            }
        }
        if (i2 != 1 && i2 != 2) {
            throw R0("Unexpected value");
        }
        T0();
        this.q = 7;
        return 7;
    }

    private int V0(String str, JsonReader.b bVar) {
        int length = bVar.a.length;
        for (int i = 0; i < length; i++) {
            if (str.equals(bVar.a[i])) {
                this.q = 0;
                this.k[this.c - 1] = str;
                return i;
            }
        }
        return -1;
    }

    private int W0(String str, JsonReader.b bVar) {
        int length = bVar.a.length;
        for (int i = 0; i < length; i++) {
            if (str.equals(bVar.a[i])) {
                this.q = 0;
                int[] iArr = this.l;
                int i2 = this.c - 1;
                iArr[i2] = iArr[i2] + 1;
                return i;
            }
        }
        return -1;
    }

    private boolean X0(int i) throws JsonEncodingException {
        if (i == 9 || i == 10 || i == 12 || i == 13 || i == 32) {
            return false;
        }
        if (i != 35) {
            if (i == 44) {
                return false;
            }
            if (i != 47 && i != 61) {
                if (i == 123 || i == 125 || i == 58) {
                    return false;
                }
                if (i != 59) {
                    switch (i) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        T0();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
    
        r6.f67p.y(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002d, code lost:
    
        if (r2 != 47) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0037, code lost:
    
        if (r6.o.a(2) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0039, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003a, code lost:
    
        T0();
        r3 = r6.f67p.G0(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
    
        if (r3 == 42) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0049, code lost:
    
        if (r3 == 47) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004b, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004c, code lost:
    
        r6.f67p.readByte();
        r6.f67p.readByte();
        g1();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        r6.f67p.readByte();
        r6.f67p.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0068, code lost:
    
        if (f1() == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0071, code lost:
    
        throw R0("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
    
        if (r2 != 35) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0076, code lost:
    
        T0();
        g1();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007d, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int Y0(boolean r7) throws java.io.EOFException, com.squareup.moshi.JsonEncodingException {
        /*
            r6 = this;
            r0 = 0
        L1:
            r1 = r0
        L2:
            p.ko r2 = r6.o
            int r3 = r1 + 1
            long r4 = (long) r3
            boolean r2 = r2.a(r4)
            if (r2 == 0) goto L80
            p.fo r2 = r6.f67p
            long r4 = (long) r1
            byte r2 = r2.G0(r4)
            r4 = 10
            if (r2 == r4) goto L7e
            r4 = 32
            if (r2 == r4) goto L7e
            r4 = 13
            if (r2 == r4) goto L7e
            r4 = 9
            if (r2 != r4) goto L25
            goto L7e
        L25:
            p.fo r3 = r6.f67p
            long r4 = (long) r1
            r3.y(r4)
            r1 = 47
            if (r2 != r1) goto L72
            p.ko r3 = r6.o
            r4 = 2
            boolean r3 = r3.a(r4)
            if (r3 != 0) goto L3a
            return r2
        L3a:
            r6.T0()
            p.fo r3 = r6.f67p
            r4 = 1
            byte r3 = r3.G0(r4)
            r4 = 42
            if (r3 == r4) goto L5a
            if (r3 == r1) goto L4c
            return r2
        L4c:
            p.fo r1 = r6.f67p
            r1.readByte()
            p.fo r1 = r6.f67p
            r1.readByte()
            r6.g1()
            goto L1
        L5a:
            p.fo r1 = r6.f67p
            r1.readByte()
            p.fo r1 = r6.f67p
            r1.readByte()
            boolean r1 = r6.f1()
            if (r1 == 0) goto L6b
            goto L1
        L6b:
            java.lang.String r7 = "Unterminated comment"
            com.squareup.moshi.JsonEncodingException r7 = r6.R0(r7)
            throw r7
        L72:
            r1 = 35
            if (r2 != r1) goto L7d
            r6.T0()
            r6.g1()
            goto L1
        L7d:
            return r2
        L7e:
            r1 = r3
            goto L2
        L80:
            if (r7 != 0) goto L84
            r7 = -1
            return r7
        L84:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r0 = "End of input"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.h.Y0(boolean):int");
    }

    private String Z0(ByteString byteString) throws EOFException, JsonEncodingException {
        StringBuilder sb = null;
        while (true) {
            long jB0 = this.o.b0(byteString);
            if (jB0 == -1) {
                throw R0("Unterminated string");
            }
            if (this.f67p.G0(jB0) != 92) {
                if (sb == null) {
                    fo foVar = this.f67p;
                    foVar.getClass();
                    String strP0 = foVar.P0(jB0, cs.a);
                    this.f67p.readByte();
                    return strP0;
                }
                fo foVar2 = this.f67p;
                foVar2.getClass();
                sb.append(foVar2.P0(jB0, cs.a));
                this.f67p.readByte();
                return sb.toString();
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            fo foVar3 = this.f67p;
            foVar3.getClass();
            sb.append(foVar3.P0(jB0, cs.a));
            this.f67p.readByte();
            sb.append(d1());
        }
    }

    private String a1() {
        long jB0 = this.o.b0(x);
        if (jB0 == -1) {
            return this.f67p.Q0();
        }
        fo foVar = this.f67p;
        foVar.getClass();
        return foVar.P0(jB0, cs.a);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private int b1() throws EOFException {
        String str;
        String str2;
        int i;
        byte bG0 = this.f67p.G0(0L);
        if (bG0 == 116 || bG0 == 84) {
            str = "true";
            str2 = "TRUE";
            i = 5;
        } else if (bG0 == 102 || bG0 == 70) {
            str = "false";
            str2 = "FALSE";
            i = 6;
        } else {
            if (bG0 != 110 && bG0 != 78) {
                return 0;
            }
            str = "null";
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            int i3 = i2 + 1;
            if (!this.o.a(i3)) {
                return 0;
            }
            byte bG02 = this.f67p.G0(i2);
            if (bG02 != str.charAt(i2) && bG02 != str2.charAt(i2)) {
                return 0;
            }
            i2 = i3;
        }
        if (this.o.a(length + 1) && X0(this.f67p.G0(length))) {
            return 0;
        }
        this.f67p.y(length);
        this.q = i;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0081, code lost:
    
        if (X0(r11) != false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0083, code lost:
    
        if (r6 != 2) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0085, code lost:
    
        if (r7 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x008b, code lost:
    
        if (r8 != Long.MIN_VALUE) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008d, code lost:
    
        if (r10 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0091, code lost:
    
        if (r8 != 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
    
        if (r10 != false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
    
        if (r10 == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0098, code lost:
    
        r8 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0099, code lost:
    
        r16.r = r8;
        r16.f67p.y(r5);
        r16.q = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a5, code lost:
    
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a6, code lost:
    
        if (r6 == 2) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a9, code lost:
    
        if (r6 == 4) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ac, code lost:
    
        if (r6 != 7) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00af, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b0, code lost:
    
        r16.s = r5;
        r16.q = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b6, code lost:
    
        return 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00b7, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int c1() throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.h.c1():int");
    }

    private char d1() throws EOFException, JsonEncodingException {
        int i;
        if (!this.o.a(1L)) {
            throw R0("Unterminated escape sequence");
        }
        byte b = this.f67p.readByte();
        if (b == 10 || b == 34 || b == 39 || b == 47 || b == 92) {
            return (char) b;
        }
        if (b == 98) {
            return '\b';
        }
        if (b == 102) {
            return '\f';
        }
        if (b == 110) {
            return '\n';
        }
        if (b == 114) {
            return '\r';
        }
        if (b == 116) {
            return '\t';
        }
        if (b != 117) {
            if (this.m) {
                return (char) b;
            }
            throw R0("Invalid escape sequence: \\" + ((char) b));
        }
        if (!this.o.a(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + J());
        }
        char c = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            byte bG0 = this.f67p.G0(i2);
            char c2 = (char) (c << 4);
            if (bG0 >= 48 && bG0 <= 57) {
                i = bG0 - 48;
            } else if (bG0 >= 97 && bG0 <= 102) {
                i = bG0 - 87;
            } else {
                if (bG0 < 65 || bG0 > 70) {
                    fo foVar = this.f67p;
                    foVar.getClass();
                    throw R0("\\u".concat(foVar.P0(4L, cs.a)));
                }
                i = bG0 - 55;
            }
            c = (char) (i + c2);
        }
        this.f67p.y(4L);
        return c;
    }

    private void e1(ByteString byteString) throws EOFException, JsonEncodingException {
        while (true) {
            long jB0 = this.o.b0(byteString);
            if (jB0 == -1) {
                throw R0("Unterminated string");
            }
            if (this.f67p.G0(jB0) != 92) {
                this.f67p.y(jB0 + 1);
                return;
            } else {
                this.f67p.y(jB0 + 1);
                d1();
            }
        }
    }

    private boolean f1() throws EOFException {
        ko koVar = this.o;
        ByteString byteString = z;
        long jP = koVar.P(byteString);
        boolean z2 = jP != -1;
        fo foVar = this.f67p;
        foVar.y(z2 ? jP + ((long) byteString.e()) : foVar.e);
        return z2;
    }

    private void g1() throws EOFException {
        long jB0 = this.o.b0(y);
        fo foVar = this.f67p;
        foVar.y(jB0 != -1 ? jB0 + 1 : foVar.e);
    }

    private void h1() throws EOFException {
        long jB0 = this.o.b0(x);
        fo foVar = this.f67p;
        if (jB0 == -1) {
            jB0 = foVar.e;
        }
        foVar.y(jB0);
    }

    @Override // com.squareup.moshi.JsonReader
    public void D() throws EOFException, JsonEncodingException {
        if (this.n) {
            throw new JsonDataException("Cannot skip unexpected " + H0() + " at " + J());
        }
        int i = 0;
        do {
            int iU0 = this.q;
            if (iU0 == 0) {
                iU0 = U0();
            }
            if (iU0 == 3) {
                K0(1);
            } else if (iU0 == 1) {
                K0(3);
            } else {
                if (iU0 == 4) {
                    i--;
                    if (i < 0) {
                        throw new JsonDataException("Expected a value but was " + H0() + " at path " + J());
                    }
                    this.c--;
                } else if (iU0 == 2) {
                    i--;
                    if (i < 0) {
                        throw new JsonDataException("Expected a value but was " + H0() + " at path " + J());
                    }
                    this.c--;
                } else if (iU0 == 14 || iU0 == 10) {
                    h1();
                } else if (iU0 == 9 || iU0 == 13) {
                    e1(w);
                } else if (iU0 == 8 || iU0 == 12) {
                    e1(v);
                } else if (iU0 == 17) {
                    this.f67p.y(this.s);
                } else if (iU0 == 18) {
                    throw new JsonDataException("Expected a value but was " + H0() + " at path " + J());
                }
                this.q = 0;
            }
            i++;
            this.q = 0;
        } while (i != 0);
        int[] iArr = this.l;
        int i2 = this.c;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.k[i2 - 1] = "null";
    }

    @Override // com.squareup.moshi.JsonReader
    public int D0() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 == 16) {
            long j = this.r;
            int i = (int) j;
            if (j == i) {
                this.q = 0;
                int[] iArr = this.l;
                int i2 = this.c - 1;
                iArr[i2] = iArr[i2] + 1;
                return i;
            }
            throw new JsonDataException("Expected an int but was " + this.r + " at path " + J());
        }
        if (iU0 == 17) {
            fo foVar = this.f67p;
            long j2 = this.s;
            foVar.getClass();
            this.t = foVar.P0(j2, cs.a);
        } else if (iU0 == 9 || iU0 == 8) {
            String strZ0 = iU0 == 9 ? Z0(w) : Z0(v);
            this.t = strZ0;
            try {
                int i3 = Integer.parseInt(strZ0);
                this.q = 0;
                int[] iArr2 = this.l;
                int i4 = this.c - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return i3;
            } catch (NumberFormatException unused) {
            }
        } else if (iU0 != 11) {
            throw new JsonDataException("Expected an int but was " + H0() + " at path " + J());
        }
        this.q = 11;
        try {
            double d = Double.parseDouble(this.t);
            int i5 = (int) d;
            if (i5 != d) {
                throw new JsonDataException("Expected an int but was " + this.t + " at path " + J());
            }
            this.t = null;
            this.q = 0;
            int[] iArr3 = this.l;
            int i6 = this.c - 1;
            iArr3[i6] = iArr3[i6] + 1;
            return i5;
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.t + " at path " + J());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00ba  */
    @Override // com.squareup.moshi.JsonReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public p.ko E0() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.h.E0():p.ko");
    }

    @Override // com.squareup.moshi.JsonReader
    public JsonReader.Token H0() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        switch (iU0) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case 6:
                return JsonReader.Token.BOOLEAN;
            case 7:
                return JsonReader.Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader.Token.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return JsonReader.Token.NAME;
            case 16:
            case 17:
                return JsonReader.Token.NUMBER;
            case 18:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public JsonReader I0() {
        return new h(this);
    }

    @Override // com.squareup.moshi.JsonReader
    public void J0() {
        if (hasNext()) {
            this.t = r0();
            this.q = 11;
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public int M0(JsonReader.b bVar) throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 < 12 || iU0 > 15) {
            return -1;
        }
        if (iU0 == 15) {
            return V0(this.t, bVar);
        }
        int iP0 = this.o.p0(bVar.b);
        if (iP0 != -1) {
            this.q = 0;
            this.k[this.c - 1] = bVar.a[iP0];
            return iP0;
        }
        String str = this.k[this.c - 1];
        String strR0 = r0();
        int iV0 = V0(strR0, bVar);
        if (iV0 == -1) {
            this.q = 15;
            this.t = strR0;
            this.k[this.c - 1] = str;
        }
        return iV0;
    }

    @Override // com.squareup.moshi.JsonReader
    public int N0(JsonReader.b bVar) throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 < 8 || iU0 > 11) {
            return -1;
        }
        if (iU0 == 11) {
            return W0(this.t, bVar);
        }
        int iP0 = this.o.p0(bVar.b);
        if (iP0 != -1) {
            this.q = 0;
            int[] iArr = this.l;
            int i = this.c - 1;
            iArr[i] = iArr[i] + 1;
            return iP0;
        }
        String strU = u();
        int iW0 = W0(strU, bVar);
        if (iW0 == -1) {
            this.q = 11;
            this.t = strU;
            this.l[this.c - 1] = r0[r1] - 1;
        }
        return iW0;
    }

    @Override // com.squareup.moshi.JsonReader
    public void Q0() throws EOFException, JsonEncodingException {
        if (this.n) {
            JsonReader.Token tokenH0 = H0();
            r0();
            throw new JsonDataException("Cannot skip unexpected " + tokenH0 + " at " + J());
        }
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 == 14) {
            h1();
        } else if (iU0 == 13) {
            e1(w);
        } else if (iU0 == 12) {
            e1(v);
        } else if (iU0 != 15) {
            throw new JsonDataException("Expected a name but was " + H0() + " at path " + J());
        }
        this.q = 0;
        this.k[this.c - 1] = "null";
    }

    @Override // com.squareup.moshi.JsonReader
    public double T() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 == 16) {
            this.q = 0;
            int[] iArr = this.l;
            int i = this.c - 1;
            iArr[i] = iArr[i] + 1;
            return this.r;
        }
        if (iU0 == 17) {
            fo foVar = this.f67p;
            long j = this.s;
            foVar.getClass();
            this.t = foVar.P0(j, cs.a);
        } else if (iU0 == 9) {
            this.t = Z0(w);
        } else if (iU0 == 8) {
            this.t = Z0(v);
        } else if (iU0 == 10) {
            this.t = a1();
        } else if (iU0 != 11) {
            throw new JsonDataException("Expected a double but was " + H0() + " at path " + J());
        }
        this.q = 11;
        try {
            double d = Double.parseDouble(this.t);
            if (!this.m && (Double.isNaN(d) || Double.isInfinite(d))) {
                throw new JsonEncodingException("JSON forbids NaN and infinities: " + d + " at path " + J());
            }
            this.t = null;
            this.q = 0;
            int[] iArr2 = this.l;
            int i2 = this.c - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return d;
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.t + " at path " + J());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public long Z() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 == 16) {
            this.q = 0;
            int[] iArr = this.l;
            int i = this.c - 1;
            iArr[i] = iArr[i] + 1;
            return this.r;
        }
        if (iU0 == 17) {
            fo foVar = this.f67p;
            long j = this.s;
            foVar.getClass();
            this.t = foVar.P0(j, cs.a);
        } else if (iU0 == 9 || iU0 == 8) {
            String strZ0 = iU0 == 9 ? Z0(w) : Z0(v);
            this.t = strZ0;
            try {
                long j2 = Long.parseLong(strZ0);
                this.q = 0;
                int[] iArr2 = this.l;
                int i2 = this.c - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j2;
            } catch (NumberFormatException unused) {
            }
        } else if (iU0 != 11) {
            throw new JsonDataException("Expected a long but was " + H0() + " at path " + J());
        }
        this.q = 11;
        try {
            long jLongValueExact = new BigDecimal(this.t).longValueExact();
            this.t = null;
            this.q = 0;
            int[] iArr3 = this.l;
            int i3 = this.c - 1;
            iArr3[i3] = iArr3[i3] + 1;
            return jLongValueExact;
        } catch (ArithmeticException | NumberFormatException unused2) {
            throw new JsonDataException("Expected a long but was " + this.t + " at path " + J());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public void b() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 == 3) {
            K0(1);
            this.l[this.c - 1] = 0;
            this.q = 0;
        } else {
            throw new JsonDataException("Expected BEGIN_ARRAY but was " + H0() + " at path " + J());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public void c() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 == 1) {
            K0(3);
            this.q = 0;
        } else {
            throw new JsonDataException("Expected BEGIN_OBJECT but was " + H0() + " at path " + J());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.q = 0;
        this.e[0] = 8;
        this.c = 1;
        this.f67p.X();
        this.o.close();
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean hasNext() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        return (iU0 == 2 || iU0 == 4 || iU0 == 18) ? false : true;
    }

    @Override // com.squareup.moshi.JsonReader
    public void i() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 != 4) {
            throw new JsonDataException("Expected END_ARRAY but was " + H0() + " at path " + J());
        }
        int i = this.c;
        this.c = i - 1;
        int[] iArr = this.l;
        int i2 = i - 2;
        iArr[i2] = iArr[i2] + 1;
        this.q = 0;
    }

    @Override // com.squareup.moshi.JsonReader
    public <T> T o0() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 == 7) {
            this.q = 0;
            int[] iArr = this.l;
            int i = this.c - 1;
            iArr[i] = iArr[i] + 1;
            return null;
        }
        throw new JsonDataException("Expected null but was " + H0() + " at path " + J());
    }

    @Override // com.squareup.moshi.JsonReader
    public void q() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 != 2) {
            throw new JsonDataException("Expected END_OBJECT but was " + H0() + " at path " + J());
        }
        int i = this.c;
        int i2 = i - 1;
        this.c = i2;
        this.k[i2] = null;
        int[] iArr = this.l;
        int i3 = i - 2;
        iArr[i3] = iArr[i3] + 1;
        this.q = 0;
    }

    @Override // com.squareup.moshi.JsonReader
    public String r0() throws EOFException, JsonEncodingException {
        String strZ0;
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 == 14) {
            strZ0 = a1();
        } else if (iU0 == 13) {
            strZ0 = Z0(w);
        } else if (iU0 == 12) {
            strZ0 = Z0(v);
        } else {
            if (iU0 != 15) {
                throw new JsonDataException("Expected a name but was " + H0() + " at path " + J());
            }
            strZ0 = this.t;
            this.t = null;
        }
        this.q = 0;
        this.k[this.c - 1] = strZ0;
        return strZ0;
    }

    public String toString() {
        return "JsonReader(" + this.o + ")";
    }

    @Override // com.squareup.moshi.JsonReader
    public String u() throws EOFException, JsonEncodingException {
        String strP0;
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 == 10) {
            strP0 = a1();
        } else if (iU0 == 9) {
            strP0 = Z0(w);
        } else if (iU0 == 8) {
            strP0 = Z0(v);
        } else if (iU0 == 11) {
            strP0 = this.t;
            this.t = null;
        } else if (iU0 == 16) {
            strP0 = Long.toString(this.r);
        } else {
            if (iU0 != 17) {
                throw new JsonDataException("Expected a string but was " + H0() + " at path " + J());
            }
            fo foVar = this.f67p;
            long j = this.s;
            foVar.getClass();
            strP0 = foVar.P0(j, cs.a);
        }
        this.q = 0;
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return strP0;
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean w0() throws EOFException, JsonEncodingException {
        int iU0 = this.q;
        if (iU0 == 0) {
            iU0 = U0();
        }
        if (iU0 == 5) {
            this.q = 0;
            int[] iArr = this.l;
            int i = this.c - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iU0 == 6) {
            this.q = 0;
            int[] iArr2 = this.l;
            int i2 = this.c - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        }
        throw new JsonDataException("Expected a boolean but was " + H0() + " at path " + J());
    }

    public h(h hVar) {
        super(hVar);
        this.q = 0;
        w02 w02VarPeek = hVar.o.peek();
        this.o = w02VarPeek;
        this.f67p = w02VarPeek.e;
        this.q = hVar.q;
        this.r = hVar.r;
        this.s = hVar.s;
        this.t = hVar.t;
        try {
            w02VarPeek.q0(hVar.f67p.e);
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }
}
