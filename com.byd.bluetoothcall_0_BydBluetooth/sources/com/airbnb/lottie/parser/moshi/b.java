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
        this.o = dVar.h();
        y(6);
    }

    private void E() throws IOException {
        if (!this.g) {
            throw D("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private int F() throws IOException {
        int[] iArr = this.f3046d;
        int i2 = this.f3045c;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int I = I(true);
            this.o.s();
            if (I != 44) {
                if (I != 59) {
                    if (I != 93) {
                        throw D("Unterminated array");
                    }
                    this.p = 4;
                    return 4;
                }
                E();
            }
        } else {
            if (i3 == 3 || i3 == 5) {
                iArr[i2 - 1] = 4;
                if (i3 == 5) {
                    int I2 = I(true);
                    this.o.s();
                    if (I2 != 44) {
                        if (I2 != 59) {
                            if (I2 != 125) {
                                throw D("Unterminated object");
                            }
                            this.p = 2;
                            return 2;
                        }
                        E();
                    }
                }
                int I3 = I(true);
                if (I3 == 34) {
                    this.o.s();
                    this.p = 13;
                    return 13;
                }
                if (I3 == 39) {
                    this.o.s();
                    E();
                    this.p = 12;
                    return 12;
                }
                if (I3 != 125) {
                    E();
                    if (!H((char) I3)) {
                        throw D("Expected name");
                    }
                    this.p = 14;
                    return 14;
                }
                if (i3 == 5) {
                    throw D("Expected name");
                }
                this.o.s();
                this.p = 2;
                return 2;
            }
            if (i3 == 4) {
                iArr[i2 - 1] = 5;
                int I4 = I(true);
                this.o.s();
                if (I4 != 58) {
                    if (I4 != 61) {
                        throw D("Expected ':'");
                    }
                    E();
                    if (this.n.a(1L) && this.o.n(0L) == 62) {
                        this.o.s();
                    }
                }
            } else if (i3 == 6) {
                iArr[i2 - 1] = 7;
            } else if (i3 == 7) {
                if (I(false) == -1) {
                    this.p = 18;
                    return 18;
                }
                E();
            } else if (i3 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int I5 = I(true);
        if (I5 == 34) {
            this.o.s();
            this.p = 9;
            return 9;
        }
        if (I5 == 39) {
            E();
            this.o.s();
            this.p = 8;
            return 8;
        }
        if (I5 != 44 && I5 != 59) {
            if (I5 == 91) {
                this.o.s();
                this.p = 3;
                return 3;
            }
            if (I5 != 93) {
                if (I5 == 123) {
                    this.o.s();
                    this.p = 1;
                    return 1;
                }
                int iL = L();
                if (iL != 0) {
                    return iL;
                }
                int iM = M();
                if (iM != 0) {
                    return iM;
                }
                if (!H(this.o.n(0L))) {
                    throw D("Expected value");
                }
                E();
                this.p = 10;
                return 10;
            }
            if (i3 == 1) {
                this.o.s();
                this.p = 4;
                return 4;
            }
        }
        if (i3 != 1 && i3 != 2) {
            throw D("Unexpected value");
        }
        E();
        this.p = 7;
        return 7;
    }

    private int G(String str, JsonReader.a aVar) {
        int length = aVar.f3049a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(aVar.f3049a[i2])) {
                this.p = 0;
                this.f3047e[this.f3045c - 1] = str;
                return i2;
            }
        }
        return -1;
    }

    private boolean H(int i2) throws IOException {
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
        E();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x0025, code lost:
    
        r6.o.D(r3 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x002f, code lost:
    
        if (r1 != 47) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0039, code lost:
    
        if (r6.n.a(2) != false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x003b, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x003c, code lost:
    
        E();
        r3 = r6.o.n(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0049, code lost:
    
        if (r3 == 42) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x004b, code lost:
    
        if (r3 == 47) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x004d, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x004e, code lost:
    
        r6.o.s();
        r6.o.s();
        Q();
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x005c, code lost:
    
        r6.o.s();
        r6.o.s();
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x006a, code lost:
    
        if (P() == false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0073, code lost:
    
        throw D("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0076, code lost:
    
        if (r1 != 35) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0078, code lost:
    
        E();
        Q();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x007f, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int I(boolean r7) throws java.io.IOException {
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
            byte r1 = r2.n(r4)
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
            r2.D(r3)
            r2 = 47
            if (r1 != r2) goto L74
            okio.d r3 = r6.n
            r4 = 2
            boolean r3 = r3.a(r4)
            if (r3 != 0) goto L3c
            return r1
        L3c:
            r6.E()
            okio.b r3 = r6.o
            r4 = 1
            byte r3 = r3.n(r4)
            r4 = 42
            if (r3 == r4) goto L5c
            if (r3 == r2) goto L4e
            return r1
        L4e:
            okio.b r1 = r6.o
            r1.s()
            okio.b r1 = r6.o
            r1.s()
            r6.Q()
            goto L1
        L5c:
            okio.b r1 = r6.o
            r1.s()
            okio.b r1 = r6.o
            r1.s()
            boolean r1 = r6.P()
            if (r1 == 0) goto L6d
            goto L1
        L6d:
            java.lang.String r7 = "Unterminated comment"
            com.airbnb.lottie.parser.moshi.JsonEncodingException r7 = r6.D(r7)
            throw r7
        L74:
            r2 = 35
            if (r1 != r2) goto L7f
            r6.E()
            r6.Q()
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
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.b.I(boolean):int");
    }

    private String J(ByteString byteString) throws IOException {
        StringBuilder sb = null;
        while (true) {
            long jD = this.n.d(byteString);
            if (jD == -1) {
                throw D("Unterminated string");
            }
            if (this.o.n(jD) != 92) {
                if (sb == null) {
                    String strA = this.o.A(jD);
                    this.o.s();
                    return strA;
                }
                sb.append(this.o.A(jD));
                this.o.s();
                return sb.toString();
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.o.A(jD));
            this.o.s();
            sb.append(N());
        }
    }

    private String K() throws IOException {
        long jD = this.n.d(k);
        return jD != -1 ? this.o.A(jD) : this.o.z();
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
    private int L() throws IOException {
        int i2;
        String str;
        String str2;
        byte bN = this.o.n(0L);
        if (bN == 116 || bN == 84) {
            i2 = 5;
            str = "true";
            str2 = "TRUE";
        } else if (bN == 102 || bN == 70) {
            i2 = 6;
            str = "false";
            str2 = "FALSE";
        } else {
            if (bN != 110 && bN != 78) {
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
            byte bN2 = this.o.n(i3);
            if (bN2 != str.charAt(i3) && bN2 != str2.charAt(i3)) {
                return 0;
            }
            i3 = i4;
        }
        if (this.n.a(length + 1) && H(this.o.n(length))) {
            return 0;
        }
        this.o.D(length);
        this.p = i2;
        return i2;
    }

    private int M() throws IOException {
        byte bN;
        char c2;
        boolean z = true;
        int i2 = 0;
        long j2 = 0;
        boolean z2 = true;
        int i3 = 0;
        char c3 = 0;
        boolean z3 = false;
        while (true) {
            int i4 = i3 + 1;
            if (!this.n.a(i4)) {
                break;
            }
            bN = this.o.n(i3);
            if (bN != 43) {
                if (bN == 69 || bN == 101) {
                    if (c3 != 2 && c3 != 4) {
                        return i2;
                    }
                    c3 = 5;
                } else if (bN == 45) {
                    c2 = 6;
                    if (c3 == 0) {
                        c3 = 1;
                        z3 = true;
                    } else if (c3 != 5) {
                        return i2;
                    }
                } else if (bN == 46) {
                    c2 = 3;
                    if (c3 != 2) {
                        return i2;
                    }
                } else {
                    if (bN < 48 || bN > 57) {
                        break;
                    }
                    if (c3 == z || c3 == 0) {
                        j2 = -(bN - 48);
                        c3 = 2;
                    } else if (c3 == 2) {
                        if (j2 == 0) {
                            return i2;
                        }
                        long j3 = (10 * j2) - ((long) (bN - 48));
                        z2 &= j2 > -922337203685477580L || (j2 == -922337203685477580L && j3 < j2);
                        j2 = j3;
                    } else if (c3 == 3) {
                        i2 = 0;
                        c3 = 4;
                    } else if (c3 == 5 || c3 == 6) {
                        i2 = 0;
                        c3 = 7;
                    }
                    i2 = 0;
                }
                i3 = i4;
                z = true;
            } else {
                c2 = 6;
                if (c3 != 5) {
                    return i2;
                }
            }
            c3 = c2;
            i3 = i4;
            z = true;
        }
        if (H(bN)) {
            return 0;
        }
        if (c3 == 2 && z2 && ((j2 != Long.MIN_VALUE || z3) && (j2 != 0 || !z3))) {
            if (!z3) {
                j2 = -j2;
            }
            this.q = j2;
            this.o.D(i3);
            this.p = 16;
            return 16;
        }
        if (c3 != 2 && c3 != 4 && c3 != 7) {
            return 0;
        }
        this.r = i3;
        this.p = 17;
        return 17;
    }

    private char N() throws IOException {
        int i2;
        int i3;
        if (!this.n.a(1L)) {
            throw D("Unterminated escape sequence");
        }
        byte bS = this.o.s();
        if (bS == 10 || bS == 34 || bS == 39 || bS == 47 || bS == 92) {
            return (char) bS;
        }
        if (bS == 98) {
            return '\b';
        }
        if (bS == 102) {
            return '\f';
        }
        if (bS == 110) {
            return '\n';
        }
        if (bS == 114) {
            return '\r';
        }
        if (bS == 116) {
            return '\t';
        }
        if (bS != 117) {
            if (this.g) {
                return (char) bS;
            }
            throw D("Invalid escape sequence: \\" + ((char) bS));
        }
        if (!this.n.a(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + p());
        }
        char c2 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            byte bN = this.o.n(i4);
            char c3 = (char) (c2 << 4);
            if (bN < 48 || bN > 57) {
                if (bN >= 97 && bN <= 102) {
                    i2 = bN - 97;
                } else {
                    if (bN < 65 || bN > 70) {
                        throw D("\\u" + this.o.A(4L));
                    }
                    i2 = bN - 65;
                }
                i3 = i2 + 10;
            } else {
                i3 = bN - 48;
            }
            c2 = (char) (c3 + i3);
        }
        this.o.D(4L);
        return c2;
    }

    private void O(ByteString byteString) throws IOException {
        while (true) {
            long jD = this.n.d(byteString);
            if (jD == -1) {
                throw D("Unterminated string");
            }
            if (this.o.n(jD) != 92) {
                this.o.D(jD + 1);
                return;
            } else {
                this.o.D(jD + 1);
                N();
            }
        }
    }

    private boolean P() throws IOException {
        d dVar = this.n;
        ByteString byteString = m;
        long jG = dVar.g(byteString);
        boolean z = jG != -1;
        okio.b bVar = this.o;
        bVar.D(z ? jG + ((long) byteString.size()) : bVar.C());
        return z;
    }

    private void Q() throws IOException {
        long jD = this.n.d(l);
        okio.b bVar = this.o;
        bVar.D(jD != -1 ? jD + 1 : bVar.C());
    }

    private void R() throws IOException {
        long jD = this.n.d(k);
        okio.b bVar = this.o;
        if (jD == -1) {
            jD = bVar.C();
        }
        bVar.D(jD);
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void A() throws IOException {
        if (this.h) {
            throw new JsonDataException("Cannot skip unexpected " + x() + " at " + p());
        }
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF == 14) {
            R();
        } else if (iF == 13) {
            O(j);
        } else if (iF == 12) {
            O(i);
        } else if (iF != 15) {
            throw new JsonDataException("Expected a name but was " + x() + " at path " + p());
        }
        this.p = 0;
        this.f3047e[this.f3045c - 1] = "null";
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void B() throws IOException {
        if (this.h) {
            throw new JsonDataException("Cannot skip unexpected " + x() + " at " + p());
        }
        int i2 = 0;
        do {
            int iF = this.p;
            if (iF == 0) {
                iF = F();
            }
            if (iF == 3) {
                y(1);
            } else if (iF == 1) {
                y(3);
            } else {
                if (iF == 4) {
                    i2--;
                    if (i2 < 0) {
                        throw new JsonDataException("Expected a value but was " + x() + " at path " + p());
                    }
                    this.f3045c--;
                } else if (iF == 2) {
                    i2--;
                    if (i2 < 0) {
                        throw new JsonDataException("Expected a value but was " + x() + " at path " + p());
                    }
                    this.f3045c--;
                } else if (iF == 14 || iF == 10) {
                    R();
                } else if (iF == 9 || iF == 13) {
                    O(j);
                } else if (iF == 8 || iF == 12) {
                    O(i);
                } else if (iF == 17) {
                    this.o.D(this.r);
                } else if (iF == 18) {
                    throw new JsonDataException("Expected a value but was " + x() + " at path " + p());
                }
                this.p = 0;
            }
            i2++;
            this.p = 0;
        } while (i2 != 0);
        int[] iArr = this.f;
        int i3 = this.f3045c;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.f3047e[i3 - 1] = "null";
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.p = 0;
        this.f3046d[0] = 8;
        this.f3045c = 1;
        this.o.k();
        this.n.close();
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void l() throws IOException {
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF == 3) {
            y(1);
            this.f[this.f3045c - 1] = 0;
            this.p = 0;
        } else {
            throw new JsonDataException("Expected BEGIN_ARRAY but was " + x() + " at path " + p());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void m() throws IOException {
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF == 1) {
            y(3);
            this.p = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + x() + " at path " + p());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void n() throws IOException {
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF != 4) {
            throw new JsonDataException("Expected END_ARRAY but was " + x() + " at path " + p());
        }
        int i2 = this.f3045c - 1;
        this.f3045c = i2;
        int[] iArr = this.f;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.p = 0;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void o() throws IOException {
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF != 2) {
            throw new JsonDataException("Expected END_OBJECT but was " + x() + " at path " + p());
        }
        int i2 = this.f3045c - 1;
        this.f3045c = i2;
        this.f3047e[i2] = null;
        int[] iArr = this.f;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.p = 0;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean q() throws IOException {
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        return (iF == 2 || iF == 4 || iF == 18) ? false : true;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean r() throws IOException {
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF == 5) {
            this.p = 0;
            int[] iArr = this.f;
            int i2 = this.f3045c - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (iF == 6) {
            this.p = 0;
            int[] iArr2 = this.f;
            int i3 = this.f3045c - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw new JsonDataException("Expected a boolean but was " + x() + " at path " + p());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public double s() throws IOException {
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF == 16) {
            this.p = 0;
            int[] iArr = this.f;
            int i2 = this.f3045c - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.q;
        }
        if (iF == 17) {
            this.s = this.o.A(this.r);
        } else if (iF == 9) {
            this.s = J(j);
        } else if (iF == 8) {
            this.s = J(i);
        } else if (iF == 10) {
            this.s = K();
        } else if (iF != 11) {
            throw new JsonDataException("Expected a double but was " + x() + " at path " + p());
        }
        this.p = 11;
        try {
            double d2 = Double.parseDouble(this.s);
            if (this.g || !(Double.isNaN(d2) || Double.isInfinite(d2))) {
                this.s = null;
                this.p = 0;
                int[] iArr2 = this.f;
                int i3 = this.f3045c - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return d2;
            }
            throw new JsonEncodingException("JSON forbids NaN and infinities: " + d2 + " at path " + p());
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.s + " at path " + p());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int t() throws IOException {
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF == 16) {
            long j2 = this.q;
            int i2 = (int) j2;
            if (j2 == i2) {
                this.p = 0;
                int[] iArr = this.f;
                int i3 = this.f3045c - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new JsonDataException("Expected an int but was " + this.q + " at path " + p());
        }
        if (iF == 17) {
            this.s = this.o.A(this.r);
        } else if (iF == 9 || iF == 8) {
            String strJ = iF == 9 ? J(j) : J(i);
            this.s = strJ;
            try {
                int i4 = Integer.parseInt(strJ);
                this.p = 0;
                int[] iArr2 = this.f;
                int i5 = this.f3045c - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return i4;
            } catch (NumberFormatException unused) {
            }
        } else if (iF != 11) {
            throw new JsonDataException("Expected an int but was " + x() + " at path " + p());
        }
        this.p = 11;
        try {
            double d2 = Double.parseDouble(this.s);
            int i6 = (int) d2;
            if (i6 == d2) {
                this.s = null;
                this.p = 0;
                int[] iArr3 = this.f;
                int i7 = this.f3045c - 1;
                iArr3[i7] = iArr3[i7] + 1;
                return i6;
            }
            throw new JsonDataException("Expected an int but was " + this.s + " at path " + p());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.s + " at path " + p());
        }
    }

    public String toString() {
        return "JsonReader(" + this.n + ")";
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String u() throws IOException {
        String strJ;
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF == 14) {
            strJ = K();
        } else if (iF == 13) {
            strJ = J(j);
        } else if (iF == 12) {
            strJ = J(i);
        } else {
            if (iF != 15) {
                throw new JsonDataException("Expected a name but was " + x() + " at path " + p());
            }
            strJ = this.s;
        }
        this.p = 0;
        this.f3047e[this.f3045c - 1] = strJ;
        return strJ;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String v() throws IOException {
        String strA;
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF == 10) {
            strA = K();
        } else if (iF == 9) {
            strA = J(j);
        } else if (iF == 8) {
            strA = J(i);
        } else if (iF == 11) {
            strA = this.s;
            this.s = null;
        } else if (iF == 16) {
            strA = Long.toString(this.q);
        } else {
            if (iF != 17) {
                throw new JsonDataException("Expected a string but was " + x() + " at path " + p());
            }
            strA = this.o.A(this.r);
        }
        this.p = 0;
        int[] iArr = this.f;
        int i2 = this.f3045c - 1;
        iArr[i2] = iArr[i2] + 1;
        return strA;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public JsonReader.Token x() throws IOException {
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        switch (iF) {
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
    public int z(JsonReader.a aVar) throws IOException {
        int iF = this.p;
        if (iF == 0) {
            iF = F();
        }
        if (iF < 12 || iF > 15) {
            return -1;
        }
        if (iF == 15) {
            return G(this.s, aVar);
        }
        int iJ = this.n.j(aVar.f3050b);
        if (iJ != -1) {
            this.p = 0;
            this.f3047e[this.f3045c - 1] = aVar.f3049a[iJ];
            return iJ;
        }
        String str = this.f3047e[this.f3045c - 1];
        String strU = u();
        int iG = G(strU, aVar);
        if (iG == -1) {
            this.p = 15;
            this.s = strU;
            this.f3047e[this.f3045c - 1] = str;
        }
        return iG;
    }
}
