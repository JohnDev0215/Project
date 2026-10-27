package com.airbnb.lottie.parser.moshi;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.EOFException;
import java.io.IOException;
import java.util.Objects;
import okio.ByteString;
import okio.d;

/* JADX INFO: compiled from: JsonUtf8Reader.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends JsonReader {
    private static final ByteString i = ByteString.encodeUtf8("'\\");
    private static final ByteString j = ByteString.encodeUtf8("\"\\");
    private static final ByteString k = ByteString.encodeUtf8("{}[]:, \n\t\r\f/\\;#=");
    private static final ByteString l = ByteString.encodeUtf8("\n\r");
    private static final ByteString m = ByteString.encodeUtf8("*/");
    private final d n;
    private final okio.b o;
    private int p = 0;
    private long q;
    private int r;
    private String s;

    b(d dVar) {
        Objects.requireNonNull(dVar, "source == null");
        this.n = dVar;
        this.o = dVar.g();
        x(6);
    }

    private void D() throws IOException {
        if (!this.g) {
            throw C("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private int E() throws IOException {
        int[] iArr = this.f2660d;
        int i2 = this.f2659c;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int iH = H(true);
            this.o.r();
            if (iH != 44) {
                if (iH != 59) {
                    if (iH != 93) {
                        throw C("Unterminated array");
                    }
                    this.p = 4;
                    return 4;
                }
                D();
            }
        } else {
            if (i3 == 3 || i3 == 5) {
                iArr[i2 - 1] = 4;
                if (i3 == 5) {
                    int iH2 = H(true);
                    this.o.r();
                    if (iH2 != 44) {
                        if (iH2 != 59) {
                            if (iH2 != 125) {
                                throw C("Unterminated object");
                            }
                            this.p = 2;
                            return 2;
                        }
                        D();
                    }
                }
                int iH3 = H(true);
                if (iH3 == 34) {
                    this.o.r();
                    this.p = 13;
                    return 13;
                }
                if (iH3 == 39) {
                    this.o.r();
                    D();
                    this.p = 12;
                    return 12;
                }
                if (iH3 != 125) {
                    D();
                    if (!G((char) iH3)) {
                        throw C("Expected name");
                    }
                    this.p = 14;
                    return 14;
                }
                if (i3 == 5) {
                    throw C("Expected name");
                }
                this.o.r();
                this.p = 2;
                return 2;
            }
            if (i3 == 4) {
                iArr[i2 - 1] = 5;
                int iH4 = H(true);
                this.o.r();
                if (iH4 != 58) {
                    if (iH4 != 61) {
                        throw C("Expected ':'");
                    }
                    D();
                    if (this.n.a(1L) && this.o.m(0L) == 62) {
                        this.o.r();
                    }
                }
            } else if (i3 == 6) {
                iArr[i2 - 1] = 7;
            } else if (i3 == 7) {
                if (H(false) == -1) {
                    this.p = 18;
                    return 18;
                }
                D();
            } else if (i3 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iH5 = H(true);
        if (iH5 == 34) {
            this.o.r();
            this.p = 9;
            return 9;
        }
        if (iH5 == 39) {
            D();
            this.o.r();
            this.p = 8;
            return 8;
        }
        if (iH5 != 44 && iH5 != 59) {
            if (iH5 == 91) {
                this.o.r();
                this.p = 3;
                return 3;
            }
            if (iH5 != 93) {
                if (iH5 == 123) {
                    this.o.r();
                    this.p = 1;
                    return 1;
                }
                int iK = K();
                if (iK != 0) {
                    return iK;
                }
                int iL = L();
                if (iL != 0) {
                    return iL;
                }
                if (!G(this.o.m(0L))) {
                    throw C("Expected value");
                }
                D();
                this.p = 10;
                return 10;
            }
            if (i3 == 1) {
                this.o.r();
                this.p = 4;
                return 4;
            }
        }
        if (i3 != 1 && i3 != 2) {
            throw C("Unexpected value");
        }
        D();
        this.p = 7;
        return 7;
    }

    private int F(String str, JsonReader.a aVar) {
        int length = aVar.f2663a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(aVar.f2663a[i2])) {
                this.p = 0;
                this.f2661e[this.f2659c - 1] = str;
                return i2;
            }
        }
        return -1;
    }

    private boolean G(int i2) throws IOException {
        if (i2 == 9 || i2 == 10 || i2 == 12 || i2 == 13 || i2 == 32) {
            return false;
        }
        if (i2 != 35) {
            if (i2 == 44) {
                return false;
            }
            if (i2 != 47 && i2 != 61) {
                if (i2 == 123 || i2 == 125 || i2 == 58) {
                    return false;
                }
                if (i2 != 59) {
                    switch (i2) {
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
        D();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
    
        r6.o.C(r3 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (r1 != 47) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
    
        if (r6.n.a(2) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003b, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        D();
        r3 = r6.o.m(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
    
        if (r3 == 42) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004b, code lost:
    
        if (r3 == 47) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004d, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004e, code lost:
    
        r6.o.r();
        r6.o.r();
        P();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
    
        r6.o.r();
        r6.o.r();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006a, code lost:
    
        if (O() == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0073, code lost:
    
        throw C("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0076, code lost:
    
        if (r1 != 35) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0078, code lost:
    
        D();
        P();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007f, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int H(boolean r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
        L1:
            r1 = r0
        L2:
            okio.d r2 = r6.n
            int r3 = r1 + 1
            long r4 = (long) r3
            boolean r2 = r2.a(r4)
            if (r2 == 0) goto L82
            okio.b r2 = r6.o
            long r4 = (long) r1
            byte r1 = r2.m(r4)
            r2 = 10
            if (r1 == r2) goto L80
            r2 = 32
            if (r1 == r2) goto L80
            r2 = 13
            if (r1 == r2) goto L80
            r2 = 9
            if (r1 != r2) goto L25
            goto L80
        L25:
            okio.b r2 = r6.o
            int r3 = r3 + (-1)
            long r3 = (long) r3
            r2.C(r3)
            r2 = 47
            if (r1 != r2) goto L74
            okio.d r3 = r6.n
            r4 = 2
            boolean r3 = r3.a(r4)
            if (r3 != 0) goto L3c
            return r1
        L3c:
            r6.D()
            okio.b r3 = r6.o
            r4 = 1
            byte r3 = r3.m(r4)
            r4 = 42
            if (r3 == r4) goto L5c
            if (r3 == r2) goto L4e
            return r1
        L4e:
            okio.b r1 = r6.o
            r1.r()
            okio.b r1 = r6.o
            r1.r()
            r6.P()
            goto L1
        L5c:
            okio.b r1 = r6.o
            r1.r()
            okio.b r1 = r6.o
            r1.r()
            boolean r1 = r6.O()
            if (r1 == 0) goto L6d
            goto L1
        L6d:
            java.lang.String r7 = "Unterminated comment"
            com.airbnb.lottie.parser.moshi.JsonEncodingException r7 = r6.C(r7)
            throw r7
        L74:
            r2 = 35
            if (r1 != r2) goto L7f
            r6.D()
            r6.P()
            goto L1
        L7f:
            return r1
        L80:
            r1 = r3
            goto L2
        L82:
            if (r7 != 0) goto L86
            r7 = -1
            return r7
        L86:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r0 = "End of input"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.b.H(boolean):int");
    }

    private String I(ByteString byteString) throws IOException {
        StringBuilder sb = null;
        while (true) {
            long jC = this.n.c(byteString);
            if (jC == -1) {
                throw C("Unterminated string");
            }
            if (this.o.m(jC) != 92) {
                if (sb == null) {
                    String strZ = this.o.z(jC);
                    this.o.r();
                    return strZ;
                }
                sb.append(this.o.z(jC));
                this.o.r();
                return sb.toString();
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.o.z(jC));
            this.o.r();
            sb.append(M());
        }
    }

    private String J() throws IOException {
        long jC = this.n.c(k);
        return jC != -1 ? this.o.z(jC) : this.o.y();
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
    private int K() throws IOException {
        int i2;
        String str;
        String str2;
        byte bM = this.o.m(0L);
        if (bM == 116 || bM == 84) {
            i2 = 5;
            str = "true";
            str2 = "TRUE";
        } else if (bM == 102 || bM == 70) {
            i2 = 6;
            str = "false";
            str2 = "FALSE";
        } else {
            if (bM != 110 && bM != 78) {
                return 0;
            }
            i2 = 7;
            str = "null";
            str2 = "NULL";
        }
        int length = str.length();
        int i3 = 1;
        while (i3 < length) {
            int i4 = i3 + 1;
            if (!this.n.a(i4)) {
                return 0;
            }
            byte bM2 = this.o.m(i3);
            if (bM2 != str.charAt(i3) && bM2 != str2.charAt(i3)) {
                return 0;
            }
            i3 = i4;
        }
        if (this.n.a(length + 1) && G(this.o.m(length))) {
            return 0;
        }
        this.o.C(length);
        this.p = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0082, code lost:
    
        if (G(r11) != false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0084, code lost:
    
        if (r6 != 2) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0086, code lost:
    
        if (r7 == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008c, code lost:
    
        if (r8 != Long.MIN_VALUE) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
    
        if (r10 == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0092, code lost:
    
        if (r8 != 0) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0094, code lost:
    
        if (r10 != false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0096, code lost:
    
        if (r10 == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0099, code lost:
    
        r8 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009a, code lost:
    
        r16.q = r8;
        r16.o.C(r5);
        r16.p = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a6, code lost:
    
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a7, code lost:
    
        if (r6 == 2) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00aa, code lost:
    
        if (r6 == 4) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00ad, code lost:
    
        if (r6 != 7) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b0, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00b2, code lost:
    
        r16.r = r5;
        r16.p = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00b8, code lost:
    
        return 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00b9, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int L() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.b.L():int");
    }

    private char M() throws IOException {
        int i2;
        int i3;
        if (!this.n.a(1L)) {
            throw C("Unterminated escape sequence");
        }
        byte bR = this.o.r();
        if (bR == 10 || bR == 34 || bR == 39 || bR == 47 || bR == 92) {
            return (char) bR;
        }
        if (bR == 98) {
            return '\b';
        }
        if (bR == 102) {
            return '\f';
        }
        if (bR == 110) {
            return '\n';
        }
        if (bR == 114) {
            return '\r';
        }
        if (bR == 116) {
            return '\t';
        }
        if (bR != 117) {
            if (this.g) {
                return (char) bR;
            }
            throw C("Invalid escape sequence: \\" + ((char) bR));
        }
        if (!this.n.a(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + o());
        }
        char c2 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            byte bM = this.o.m(i4);
            char c3 = (char) (c2 << 4);
            if (bM < 48 || bM > 57) {
                if (bM >= 97 && bM <= 102) {
                    i2 = bM - 97;
                } else {
                    if (bM < 65 || bM > 70) {
                        throw C("\\u" + this.o.z(4L));
                    }
                    i2 = bM - 65;
                }
                i3 = i2 + 10;
            } else {
                i3 = bM - 48;
            }
            c2 = (char) (c3 + i3);
        }
        this.o.C(4L);
        return c2;
    }

    private void N(ByteString byteString) throws IOException {
        while (true) {
            long jC = this.n.c(byteString);
            if (jC == -1) {
                throw C("Unterminated string");
            }
            if (this.o.m(jC) != 92) {
                this.o.C(jC + 1);
                return;
            } else {
                this.o.C(jC + 1);
                M();
            }
        }
    }

    private boolean O() throws IOException {
        d dVar = this.n;
        ByteString byteString = m;
        long jF = dVar.f(byteString);
        boolean z = jF != -1;
        okio.b bVar = this.o;
        bVar.C(z ? jF + ((long) byteString.size()) : bVar.B());
        return z;
    }

    private void P() throws IOException {
        long jC = this.n.c(l);
        okio.b bVar = this.o;
        bVar.C(jC != -1 ? jC + 1 : bVar.B());
    }

    private void Q() throws IOException {
        long jC = this.n.c(k);
        okio.b bVar = this.o;
        if (jC == -1) {
            jC = bVar.B();
        }
        bVar.C(jC);
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void A() throws IOException {
        if (this.h) {
            throw new JsonDataException("Cannot skip unexpected " + w() + " at " + o());
        }
        int i2 = 0;
        do {
            int iE = this.p;
            if (iE == 0) {
                iE = E();
            }
            if (iE == 3) {
                x(1);
            } else if (iE == 1) {
                x(3);
            } else {
                if (iE == 4) {
                    i2--;
                    if (i2 < 0) {
                        throw new JsonDataException("Expected a value but was " + w() + " at path " + o());
                    }
                    this.f2659c--;
                } else if (iE == 2) {
                    i2--;
                    if (i2 < 0) {
                        throw new JsonDataException("Expected a value but was " + w() + " at path " + o());
                    }
                    this.f2659c--;
                } else if (iE == 14 || iE == 10) {
                    Q();
                } else if (iE == 9 || iE == 13) {
                    N(j);
                } else if (iE == 8 || iE == 12) {
                    N(i);
                } else if (iE == 17) {
                    this.o.C(this.r);
                } else if (iE == 18) {
                    throw new JsonDataException("Expected a value but was " + w() + " at path " + o());
                }
                this.p = 0;
            }
            i2++;
            this.p = 0;
        } while (i2 != 0);
        int[] iArr = this.f;
        int i3 = this.f2659c;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.f2661e[i3 - 1] = "null";
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.p = 0;
        this.f2660d[0] = 8;
        this.f2659c = 1;
        this.o.j();
        this.n.close();
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void k() throws IOException {
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 3) {
            x(1);
            this.f[this.f2659c - 1] = 0;
            this.p = 0;
        } else {
            throw new JsonDataException("Expected BEGIN_ARRAY but was " + w() + " at path " + o());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void l() throws IOException {
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 1) {
            x(3);
            this.p = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + w() + " at path " + o());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void m() throws IOException {
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE != 4) {
            throw new JsonDataException("Expected END_ARRAY but was " + w() + " at path " + o());
        }
        int i2 = this.f2659c - 1;
        this.f2659c = i2;
        int[] iArr = this.f;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.p = 0;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void n() throws IOException {
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE != 2) {
            throw new JsonDataException("Expected END_OBJECT but was " + w() + " at path " + o());
        }
        int i2 = this.f2659c - 1;
        this.f2659c = i2;
        this.f2661e[i2] = null;
        int[] iArr = this.f;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.p = 0;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean p() throws IOException {
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        return (iE == 2 || iE == 4 || iE == 18) ? false : true;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean q() throws IOException {
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 5) {
            this.p = 0;
            int[] iArr = this.f;
            int i2 = this.f2659c - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (iE == 6) {
            this.p = 0;
            int[] iArr2 = this.f;
            int i3 = this.f2659c - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw new JsonDataException("Expected a boolean but was " + w() + " at path " + o());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public double r() throws IOException {
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 16) {
            this.p = 0;
            int[] iArr = this.f;
            int i2 = this.f2659c - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.q;
        }
        if (iE == 17) {
            this.s = this.o.z(this.r);
        } else if (iE == 9) {
            this.s = I(j);
        } else if (iE == 8) {
            this.s = I(i);
        } else if (iE == 10) {
            this.s = J();
        } else if (iE != 11) {
            throw new JsonDataException("Expected a double but was " + w() + " at path " + o());
        }
        this.p = 11;
        try {
            double d2 = Double.parseDouble(this.s);
            if (this.g || !(Double.isNaN(d2) || Double.isInfinite(d2))) {
                this.s = null;
                this.p = 0;
                int[] iArr2 = this.f;
                int i3 = this.f2659c - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return d2;
            }
            throw new JsonEncodingException("JSON forbids NaN and infinities: " + d2 + " at path " + o());
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.s + " at path " + o());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int s() throws IOException {
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 16) {
            long j2 = this.q;
            int i2 = (int) j2;
            if (j2 == i2) {
                this.p = 0;
                int[] iArr = this.f;
                int i3 = this.f2659c - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new JsonDataException("Expected an int but was " + this.q + " at path " + o());
        }
        if (iE == 17) {
            this.s = this.o.z(this.r);
        } else if (iE == 9 || iE == 8) {
            String strI = iE == 9 ? I(j) : I(i);
            this.s = strI;
            try {
                int i4 = Integer.parseInt(strI);
                this.p = 0;
                int[] iArr2 = this.f;
                int i5 = this.f2659c - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return i4;
            } catch (NumberFormatException unused) {
            }
        } else if (iE != 11) {
            throw new JsonDataException("Expected an int but was " + w() + " at path " + o());
        }
        this.p = 11;
        try {
            double d2 = Double.parseDouble(this.s);
            int i6 = (int) d2;
            if (i6 == d2) {
                this.s = null;
                this.p = 0;
                int[] iArr3 = this.f;
                int i7 = this.f2659c - 1;
                iArr3[i7] = iArr3[i7] + 1;
                return i6;
            }
            throw new JsonDataException("Expected an int but was " + this.s + " at path " + o());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.s + " at path " + o());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String t() throws IOException {
        String strI;
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 14) {
            strI = J();
        } else if (iE == 13) {
            strI = I(j);
        } else if (iE == 12) {
            strI = I(i);
        } else {
            if (iE != 15) {
                throw new JsonDataException("Expected a name but was " + w() + " at path " + o());
            }
            strI = this.s;
        }
        this.p = 0;
        this.f2661e[this.f2659c - 1] = strI;
        return strI;
    }

    public String toString() {
        return "JsonReader(" + this.n + ")";
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String u() throws IOException {
        String strZ;
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 10) {
            strZ = J();
        } else if (iE == 9) {
            strZ = I(j);
        } else if (iE == 8) {
            strZ = I(i);
        } else if (iE == 11) {
            strZ = this.s;
            this.s = null;
        } else if (iE == 16) {
            strZ = Long.toString(this.q);
        } else {
            if (iE != 17) {
                throw new JsonDataException("Expected a string but was " + w() + " at path " + o());
            }
            strZ = this.o.z(this.r);
        }
        this.p = 0;
        int[] iArr = this.f;
        int i2 = this.f2659c - 1;
        iArr[i2] = iArr[i2] + 1;
        return strZ;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public JsonReader.Token w() throws IOException {
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        switch (iE) {
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

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int y(JsonReader.a aVar) throws IOException {
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE < 12 || iE > 15) {
            return -1;
        }
        if (iE == 15) {
            return F(this.s, aVar);
        }
        int i2 = this.n.i(aVar.f2664b);
        if (i2 != -1) {
            this.p = 0;
            this.f2661e[this.f2659c - 1] = aVar.f2663a[i2];
            return i2;
        }
        String str = this.f2661e[this.f2659c - 1];
        String strT = t();
        int iF = F(strT, aVar);
        if (iF == -1) {
            this.p = 15;
            this.s = strT;
            this.f2661e[this.f2659c - 1] = str;
        }
        return iF;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void z() throws IOException {
        if (this.h) {
            throw new JsonDataException("Cannot skip unexpected " + w() + " at " + o());
        }
        int iE = this.p;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 14) {
            Q();
        } else if (iE == 13) {
            N(j);
        } else if (iE == 12) {
            N(i);
        } else if (iE != 15) {
            throw new JsonDataException("Expected a name but was " + w() + " at path " + o());
        }
        this.p = 0;
        this.f2661e[this.f2659c - 1] = "null";
    }
}
