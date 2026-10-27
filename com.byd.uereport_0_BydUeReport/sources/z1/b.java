package z1;

import com.airbnb.lottie.parser.moshi.JsonDataException;
import com.airbnb.lottie.parser.moshi.JsonEncodingException;
import com.airbnb.lottie.parser.moshi.JsonReader;
import i7.d;
import java.io.EOFException;
import java.io.IOException;
import java.util.Objects;
import okio.ByteString;

/* JADX INFO: compiled from: JsonUtf8Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends JsonReader {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final ByteString f9484k = ByteString.encodeUtf8("'\\");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final ByteString f9485l = ByteString.encodeUtf8("\"\\");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final ByteString f9486m = ByteString.encodeUtf8("{}[]:, \n\t\r\f/\\;#=");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final ByteString f9487n = ByteString.encodeUtf8("\n\r");

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final ByteString f9488o = ByteString.encodeUtf8("*/");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final d f9489p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final i7.b f9490q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f9491r = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f9492s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f9493t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f9494u;

    public b(d dVar) {
        Objects.requireNonNull(dVar, "source == null");
        this.f9489p = dVar;
        this.f9490q = dVar.G();
        B(6);
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int C(JsonReader.a aVar) throws IOException {
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN < 12 || iN > 15) {
            return -1;
        }
        if (iN == 15) {
            return O(this.f9494u, aVar);
        }
        int iP0 = this.f9489p.p0(aVar.f2522b);
        if (iP0 != -1) {
            this.f9491r = 0;
            this.f2517g[this.f2515e - 1] = aVar.f2521a[iP0];
            return iP0;
        }
        String str = this.f2517g[this.f2515e - 1];
        String strT = t();
        int iO = O(strT, aVar);
        if (iO == -1) {
            this.f9491r = 15;
            this.f9494u = strT;
            this.f2517g[this.f2515e - 1] = str;
        }
        return iO;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void E() throws IOException {
        if (this.f2520j) {
            throw new JsonDataException("Cannot skip unexpected " + x() + " at " + a());
        }
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 14) {
            e0();
        } else if (iN == 13) {
            a0(f9485l);
        } else if (iN == 12) {
            a0(f9484k);
        } else if (iN != 15) {
            throw new JsonDataException("Expected a name but was " + x() + " at path " + a());
        }
        this.f9491r = 0;
        this.f2517g[this.f2515e - 1] = "null";
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void F() throws IOException {
        if (this.f2520j) {
            throw new JsonDataException("Cannot skip unexpected " + x() + " at " + a());
        }
        int i8 = 0;
        do {
            int iN = this.f9491r;
            if (iN == 0) {
                iN = N();
            }
            if (iN == 3) {
                B(1);
            } else if (iN == 1) {
                B(3);
            } else {
                if (iN == 4) {
                    i8--;
                    if (i8 < 0) {
                        throw new JsonDataException("Expected a value but was " + x() + " at path " + a());
                    }
                    this.f2515e--;
                } else if (iN == 2) {
                    i8--;
                    if (i8 < 0) {
                        throw new JsonDataException("Expected a value but was " + x() + " at path " + a());
                    }
                    this.f2515e--;
                } else if (iN == 14 || iN == 10) {
                    e0();
                } else if (iN == 9 || iN == 13) {
                    a0(f9485l);
                } else if (iN == 8 || iN == 12) {
                    a0(f9484k);
                } else if (iN == 17) {
                    this.f9490q.L(this.f9493t);
                } else if (iN == 18) {
                    throw new JsonDataException("Expected a value but was " + x() + " at path " + a());
                }
                this.f9491r = 0;
            }
            i8++;
            this.f9491r = 0;
        } while (i8 != 0);
        int[] iArr = this.f2518h;
        int i9 = this.f2515e;
        int i10 = i9 - 1;
        iArr[i10] = iArr[i10] + 1;
        this.f2517g[i9 - 1] = "null";
    }

    public final void M() throws IOException {
        if (!this.f2519i) {
            throw L("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    public final int N() throws IOException {
        int[] iArr = this.f2516f;
        int i8 = this.f2515e;
        int i9 = iArr[i8 - 1];
        if (i9 == 1) {
            iArr[i8 - 1] = 2;
        } else if (i9 == 2) {
            int iR = R(true);
            this.f9490q.q();
            if (iR != 44) {
                if (iR != 59) {
                    if (iR != 93) {
                        throw L("Unterminated array");
                    }
                    this.f9491r = 4;
                    return 4;
                }
                M();
            }
        } else {
            if (i9 == 3 || i9 == 5) {
                iArr[i8 - 1] = 4;
                if (i9 == 5) {
                    int iR2 = R(true);
                    this.f9490q.q();
                    if (iR2 != 44) {
                        if (iR2 != 59) {
                            if (iR2 != 125) {
                                throw L("Unterminated object");
                            }
                            this.f9491r = 2;
                            return 2;
                        }
                        M();
                    }
                }
                int iR3 = R(true);
                if (iR3 == 34) {
                    this.f9490q.q();
                    this.f9491r = 13;
                    return 13;
                }
                if (iR3 == 39) {
                    this.f9490q.q();
                    M();
                    this.f9491r = 12;
                    return 12;
                }
                if (iR3 != 125) {
                    M();
                    if (!P((char) iR3)) {
                        throw L("Expected name");
                    }
                    this.f9491r = 14;
                    return 14;
                }
                if (i9 == 5) {
                    throw L("Expected name");
                }
                this.f9490q.q();
                this.f9491r = 2;
                return 2;
            }
            if (i9 == 4) {
                iArr[i8 - 1] = 5;
                int iR4 = R(true);
                this.f9490q.q();
                if (iR4 != 58) {
                    if (iR4 != 61) {
                        throw L("Expected ':'");
                    }
                    M();
                    if (this.f9489p.p(1L) && this.f9490q.j(0L) == 62) {
                        this.f9490q.q();
                    }
                }
            } else if (i9 == 6) {
                iArr[i8 - 1] = 7;
            } else if (i9 == 7) {
                if (R(false) == -1) {
                    this.f9491r = 18;
                    return 18;
                }
                M();
            } else if (i9 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iR5 = R(true);
        if (iR5 == 34) {
            this.f9490q.q();
            this.f9491r = 9;
            return 9;
        }
        if (iR5 == 39) {
            M();
            this.f9490q.q();
            this.f9491r = 8;
            return 8;
        }
        if (iR5 != 44 && iR5 != 59) {
            if (iR5 == 91) {
                this.f9490q.q();
                this.f9491r = 3;
                return 3;
            }
            if (iR5 != 93) {
                if (iR5 == 123) {
                    this.f9490q.q();
                    this.f9491r = 1;
                    return 1;
                }
                int iX = X();
                if (iX != 0) {
                    return iX;
                }
                int iY = Y();
                if (iY != 0) {
                    return iY;
                }
                if (!P(this.f9490q.j(0L))) {
                    throw L("Expected value");
                }
                M();
                this.f9491r = 10;
                return 10;
            }
            if (i9 == 1) {
                this.f9490q.q();
                this.f9491r = 4;
                return 4;
            }
        }
        if (i9 != 1 && i9 != 2) {
            throw L("Unexpected value");
        }
        M();
        this.f9491r = 7;
        return 7;
    }

    public final int O(String str, JsonReader.a aVar) {
        int length = aVar.f2521a.length;
        for (int i8 = 0; i8 < length; i8++) {
            if (str.equals(aVar.f2521a[i8])) {
                this.f9491r = 0;
                this.f2517g[this.f2515e - 1] = str;
                return i8;
            }
        }
        return -1;
    }

    public final boolean P(int i8) throws IOException {
        if (i8 == 9 || i8 == 10 || i8 == 12 || i8 == 13 || i8 == 32) {
            return false;
        }
        if (i8 != 35) {
            if (i8 == 44) {
                return false;
            }
            if (i8 != 47 && i8 != 61) {
                if (i8 == 123 || i8 == 125 || i8 == 58) {
                    return false;
                }
                if (i8 != 59) {
                    switch (i8) {
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
        M();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
    
        r6.f9490q.L(r3 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (r1 != 47) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
    
        if (r6.f9489p.p(2) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003b, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        M();
        r3 = r6.f9490q.j(1);
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
    
        r6.f9490q.q();
        r6.f9490q.q();
        d0();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
    
        r6.f9490q.q();
        r6.f9490q.q();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006a, code lost:
    
        if (c0() == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0073, code lost:
    
        throw L("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0076, code lost:
    
        if (r1 != 35) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0078, code lost:
    
        M();
        d0();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007f, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int R(boolean r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
        L1:
            r1 = r0
        L2:
            i7.d r2 = r6.f9489p
            int r3 = r1 + 1
            long r4 = (long) r3
            boolean r2 = r2.p(r4)
            if (r2 == 0) goto L82
            i7.b r2 = r6.f9490q
            long r4 = (long) r1
            byte r1 = r2.j(r4)
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
            i7.b r2 = r6.f9490q
            int r3 = r3 + (-1)
            long r3 = (long) r3
            r2.L(r3)
            r2 = 47
            if (r1 != r2) goto L74
            i7.d r3 = r6.f9489p
            r4 = 2
            boolean r3 = r3.p(r4)
            if (r3 != 0) goto L3c
            return r1
        L3c:
            r6.M()
            i7.b r3 = r6.f9490q
            r4 = 1
            byte r3 = r3.j(r4)
            r4 = 42
            if (r3 == r4) goto L5c
            if (r3 == r2) goto L4e
            return r1
        L4e:
            i7.b r1 = r6.f9490q
            r1.q()
            i7.b r1 = r6.f9490q
            r1.q()
            r6.d0()
            goto L1
        L5c:
            i7.b r1 = r6.f9490q
            r1.q()
            i7.b r1 = r6.f9490q
            r1.q()
            boolean r1 = r6.c0()
            if (r1 == 0) goto L6d
            goto L1
        L6d:
            java.lang.String r7 = "Unterminated comment"
            com.airbnb.lottie.parser.moshi.JsonEncodingException r7 = r6.L(r7)
            throw r7
        L74:
            r2 = 35
            if (r1 != r2) goto L7f
            r6.M()
            r6.d0()
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
        throw new UnsupportedOperationException("Method not decompiled: z1.b.R(boolean):int");
    }

    public final String T(ByteString byteString) throws IOException {
        StringBuilder sb = null;
        while (true) {
            long jQ = this.f9489p.Q(byteString);
            if (jQ == -1) {
                throw L("Unterminated string");
            }
            if (this.f9490q.j(jQ) != 92) {
                if (sb == null) {
                    String strE = this.f9490q.E(jQ);
                    this.f9490q.q();
                    return strE;
                }
                sb.append(this.f9490q.E(jQ));
                this.f9490q.q();
                return sb.toString();
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.f9490q.E(jQ));
            this.f9490q.q();
            sb.append(Z());
        }
    }

    public final String V() throws IOException {
        long jQ = this.f9489p.Q(f9486m);
        return jQ != -1 ? this.f9490q.E(jQ) : this.f9490q.C();
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
    public final int X() throws IOException {
        int i8;
        String str;
        String str2;
        byte bJ = this.f9490q.j(0L);
        if (bJ == 116 || bJ == 84) {
            i8 = 5;
            str = "true";
            str2 = "TRUE";
        } else if (bJ == 102 || bJ == 70) {
            i8 = 6;
            str = "false";
            str2 = "FALSE";
        } else {
            if (bJ != 110 && bJ != 78) {
                return 0;
            }
            i8 = 7;
            str = "null";
            str2 = "NULL";
        }
        int length = str.length();
        int i9 = 1;
        while (i9 < length) {
            int i10 = i9 + 1;
            if (!this.f9489p.p(i10)) {
                return 0;
            }
            byte bJ2 = this.f9490q.j(i9);
            if (bJ2 != str.charAt(i9) && bJ2 != str2.charAt(i9)) {
                return 0;
            }
            i9 = i10;
        }
        if (this.f9489p.p(length + 1) && P(this.f9490q.j(length))) {
            return 0;
        }
        this.f9490q.L(length);
        this.f9491r = i8;
        return i8;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0082, code lost:
    
        if (P(r11) != false) goto L71;
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
    
        r16.f9492s = r8;
        r16.f9490q.L(r5);
        r16.f9491r = 16;
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
    
        r16.f9493t = r5;
        r16.f9491r = 17;
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
    public final int Y() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: z1.b.Y():int");
    }

    public final char Z() throws IOException {
        int i8;
        int i9;
        if (!this.f9489p.p(1L)) {
            throw L("Unterminated escape sequence");
        }
        byte bQ = this.f9490q.q();
        if (bQ == 10 || bQ == 34 || bQ == 39 || bQ == 47 || bQ == 92) {
            return (char) bQ;
        }
        if (bQ == 98) {
            return '\b';
        }
        if (bQ == 102) {
            return '\f';
        }
        if (bQ == 110) {
            return '\n';
        }
        if (bQ == 114) {
            return '\r';
        }
        if (bQ == 116) {
            return '\t';
        }
        if (bQ != 117) {
            if (this.f2519i) {
                return (char) bQ;
            }
            throw L("Invalid escape sequence: \\" + ((char) bQ));
        }
        if (!this.f9489p.p(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + a());
        }
        char c8 = 0;
        for (int i10 = 0; i10 < 4; i10++) {
            byte bJ = this.f9490q.j(i10);
            char c9 = (char) (c8 << 4);
            if (bJ < 48 || bJ > 57) {
                if (bJ >= 97 && bJ <= 102) {
                    i8 = bJ - 97;
                } else {
                    if (bJ < 65 || bJ > 70) {
                        throw L("\\u" + this.f9490q.E(4L));
                    }
                    i8 = bJ - 65;
                }
                i9 = i8 + 10;
            } else {
                i9 = bJ - 48;
            }
            c8 = (char) (c9 + i9);
        }
        this.f9490q.L(4L);
        return c8;
    }

    public final void a0(ByteString byteString) throws IOException {
        while (true) {
            long jQ = this.f9489p.Q(byteString);
            if (jQ == -1) {
                throw L("Unterminated string");
            }
            if (this.f9490q.j(jQ) != 92) {
                this.f9490q.L(jQ + 1);
                return;
            } else {
                this.f9490q.L(jQ + 1);
                Z();
            }
        }
    }

    public final boolean c0() throws IOException {
        d dVar = this.f9489p;
        ByteString byteString = f9488o;
        long jD = dVar.D(byteString);
        boolean z7 = jD != -1;
        i7.b bVar = this.f9490q;
        bVar.L(z7 ? jD + ((long) byteString.size()) : bVar.I());
        return z7;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f9491r = 0;
        this.f2516f[0] = 8;
        this.f2515e = 1;
        this.f9490q.e();
        this.f9489p.close();
    }

    public final void d0() throws IOException {
        long jQ = this.f9489p.Q(f9487n);
        i7.b bVar = this.f9490q;
        bVar.L(jQ != -1 ? jQ + 1 : bVar.I());
    }

    public final void e0() throws IOException {
        long jQ = this.f9489p.Q(f9486m);
        i7.b bVar = this.f9490q;
        if (jQ == -1) {
            jQ = bVar.I();
        }
        bVar.L(jQ);
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void g() throws IOException {
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 3) {
            B(1);
            this.f2518h[this.f2515e - 1] = 0;
            this.f9491r = 0;
        } else {
            throw new JsonDataException("Expected BEGIN_ARRAY but was " + x() + " at path " + a());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void i() throws IOException {
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 1) {
            B(3);
            this.f9491r = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + x() + " at path " + a());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void j() throws IOException {
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN != 4) {
            throw new JsonDataException("Expected END_ARRAY but was " + x() + " at path " + a());
        }
        int i8 = this.f2515e - 1;
        this.f2515e = i8;
        int[] iArr = this.f2518h;
        int i9 = i8 - 1;
        iArr[i9] = iArr[i9] + 1;
        this.f9491r = 0;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void m() throws IOException {
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN != 2) {
            throw new JsonDataException("Expected END_OBJECT but was " + x() + " at path " + a());
        }
        int i8 = this.f2515e - 1;
        this.f2515e = i8;
        this.f2517g[i8] = null;
        int[] iArr = this.f2518h;
        int i9 = i8 - 1;
        iArr[i9] = iArr[i9] + 1;
        this.f9491r = 0;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean n() throws IOException {
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        return (iN == 2 || iN == 4 || iN == 18) ? false : true;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean o() throws IOException {
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 5) {
            this.f9491r = 0;
            int[] iArr = this.f2518h;
            int i8 = this.f2515e - 1;
            iArr[i8] = iArr[i8] + 1;
            return true;
        }
        if (iN == 6) {
            this.f9491r = 0;
            int[] iArr2 = this.f2518h;
            int i9 = this.f2515e - 1;
            iArr2[i9] = iArr2[i9] + 1;
            return false;
        }
        throw new JsonDataException("Expected a boolean but was " + x() + " at path " + a());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public double q() throws IOException {
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 16) {
            this.f9491r = 0;
            int[] iArr = this.f2518h;
            int i8 = this.f2515e - 1;
            iArr[i8] = iArr[i8] + 1;
            return this.f9492s;
        }
        if (iN == 17) {
            this.f9494u = this.f9490q.E(this.f9493t);
        } else if (iN == 9) {
            this.f9494u = T(f9485l);
        } else if (iN == 8) {
            this.f9494u = T(f9484k);
        } else if (iN == 10) {
            this.f9494u = V();
        } else if (iN != 11) {
            throw new JsonDataException("Expected a double but was " + x() + " at path " + a());
        }
        this.f9491r = 11;
        try {
            double d8 = Double.parseDouble(this.f9494u);
            if (this.f2519i || !(Double.isNaN(d8) || Double.isInfinite(d8))) {
                this.f9494u = null;
                this.f9491r = 0;
                int[] iArr2 = this.f2518h;
                int i9 = this.f2515e - 1;
                iArr2[i9] = iArr2[i9] + 1;
                return d8;
            }
            throw new JsonEncodingException("JSON forbids NaN and infinities: " + d8 + " at path " + a());
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.f9494u + " at path " + a());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int r() throws IOException {
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 16) {
            long j8 = this.f9492s;
            int i8 = (int) j8;
            if (j8 == i8) {
                this.f9491r = 0;
                int[] iArr = this.f2518h;
                int i9 = this.f2515e - 1;
                iArr[i9] = iArr[i9] + 1;
                return i8;
            }
            throw new JsonDataException("Expected an int but was " + this.f9492s + " at path " + a());
        }
        if (iN == 17) {
            this.f9494u = this.f9490q.E(this.f9493t);
        } else if (iN == 9 || iN == 8) {
            String strT = iN == 9 ? T(f9485l) : T(f9484k);
            this.f9494u = strT;
            try {
                int i10 = Integer.parseInt(strT);
                this.f9491r = 0;
                int[] iArr2 = this.f2518h;
                int i11 = this.f2515e - 1;
                iArr2[i11] = iArr2[i11] + 1;
                return i10;
            } catch (NumberFormatException unused) {
            }
        } else if (iN != 11) {
            throw new JsonDataException("Expected an int but was " + x() + " at path " + a());
        }
        this.f9491r = 11;
        try {
            double d8 = Double.parseDouble(this.f9494u);
            int i12 = (int) d8;
            if (i12 == d8) {
                this.f9494u = null;
                this.f9491r = 0;
                int[] iArr3 = this.f2518h;
                int i13 = this.f2515e - 1;
                iArr3[i13] = iArr3[i13] + 1;
                return i12;
            }
            throw new JsonDataException("Expected an int but was " + this.f9494u + " at path " + a());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.f9494u + " at path " + a());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String t() throws IOException {
        String strT;
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 14) {
            strT = V();
        } else if (iN == 13) {
            strT = T(f9485l);
        } else if (iN == 12) {
            strT = T(f9484k);
        } else {
            if (iN != 15) {
                throw new JsonDataException("Expected a name but was " + x() + " at path " + a());
            }
            strT = this.f9494u;
        }
        this.f9491r = 0;
        this.f2517g[this.f2515e - 1] = strT;
        return strT;
    }

    public String toString() {
        return "JsonReader(" + this.f9489p + ")";
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String v() throws IOException {
        String strE;
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 10) {
            strE = V();
        } else if (iN == 9) {
            strE = T(f9485l);
        } else if (iN == 8) {
            strE = T(f9484k);
        } else if (iN == 11) {
            strE = this.f9494u;
            this.f9494u = null;
        } else if (iN == 16) {
            strE = Long.toString(this.f9492s);
        } else {
            if (iN != 17) {
                throw new JsonDataException("Expected a string but was " + x() + " at path " + a());
            }
            strE = this.f9490q.E(this.f9493t);
        }
        this.f9491r = 0;
        int[] iArr = this.f2518h;
        int i8 = this.f2515e - 1;
        iArr[i8] = iArr[i8] + 1;
        return strE;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public JsonReader.Token x() throws IOException {
        int iN = this.f9491r;
        if (iN == 0) {
            iN = N();
        }
        switch (iN) {
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
}
