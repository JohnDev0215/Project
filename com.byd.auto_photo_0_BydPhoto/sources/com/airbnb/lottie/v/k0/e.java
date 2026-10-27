package com.airbnb.lottie.v.k0;

import androidx.annotation.Nullable;
import com.airbnb.lottie.v.k0.c;
import com.byd.dilink.os.BootBusinessConstants;
import java.io.EOFException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class e extends c {
    private static final c.d h = c.d.c("'\\");
    private static final c.d i = c.d.c("\"\\");
    private static final c.d j = c.d.c("{}[]:, \n\t\r\f/\\;#=");
    private static final c.d k = c.d.c("\n\r");
    private static final c.d l = c.d.c("*/");
    private final c.c m;
    private final c.a n;
    private int o = 0;
    private long p;
    private int q;

    @Nullable
    private String r;

    e(c.c cVar) {
        Objects.requireNonNull(cVar, "source == null");
        this.m = cVar;
        this.n = cVar.d();
        x(6);
    }

    private void D() throws b {
        if (!this.f) {
            throw C("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x014b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int E() throws com.airbnb.lottie.v.k0.b, java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.v.k0.e.E():int");
    }

    private int F(String str, c.a aVar) {
        int length = aVar.f936a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(aVar.f936a[i2])) {
                this.o = 0;
                this.d[this.f934b - 1] = str;
                return i2;
            }
        }
        return -1;
    }

    private boolean G(int i2) throws b {
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
    
        r6.n.B(r3 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (r1 != 47) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
    
        if (r6.m.b(2) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003b, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
    
        D();
        r3 = r6.n.m(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
    
        if (r3 == 42) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004b, code lost:
    
        if (r3 == 47) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004d, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004e, code lost:
    
        r6.n.q();
        r6.n.q();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
    
        P();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
    
        r6.n.q();
        r6.n.q();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006a, code lost:
    
        if (O() == false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0073, code lost:
    
        throw C("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0076, code lost:
    
        if (r1 != 35) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0078, code lost:
    
        D();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007c, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int H(boolean r7) throws com.airbnb.lottie.v.k0.b, java.io.EOFException {
        /*
            r6 = this;
            r0 = 0
        L1:
            r1 = r0
        L2:
            c.c r2 = r6.m
            int r3 = r1 + 1
            long r4 = (long) r3
            boolean r2 = r2.b(r4)
            if (r2 == 0) goto L7f
            c.a r2 = r6.n
            long r4 = (long) r1
            byte r1 = r2.m(r4)
            r2 = 10
            if (r1 == r2) goto L7d
            r2 = 32
            if (r1 == r2) goto L7d
            r2 = 13
            if (r1 == r2) goto L7d
            r2 = 9
            if (r1 != r2) goto L25
            goto L7d
        L25:
            c.a r2 = r6.n
            int r3 = r3 + (-1)
            long r3 = (long) r3
            r2.B(r3)
            r2 = 47
            if (r1 != r2) goto L74
            c.c r3 = r6.m
            r4 = 2
            boolean r3 = r3.b(r4)
            if (r3 != 0) goto L3c
            return r1
        L3c:
            r6.D()
            c.a r3 = r6.n
            r4 = 1
            byte r3 = r3.m(r4)
            r4 = 42
            if (r3 == r4) goto L5c
            if (r3 == r2) goto L4e
            return r1
        L4e:
            c.a r1 = r6.n
            r1.q()
            c.a r1 = r6.n
            r1.q()
        L58:
            r6.P()
            goto L1
        L5c:
            c.a r1 = r6.n
            r1.q()
            c.a r1 = r6.n
            r1.q()
            boolean r1 = r6.O()
            if (r1 == 0) goto L6d
            goto L1
        L6d:
            java.lang.String r7 = "Unterminated comment"
            com.airbnb.lottie.v.k0.b r7 = r6.C(r7)
            throw r7
        L74:
            r2 = 35
            if (r1 != r2) goto L7c
            r6.D()
            goto L58
        L7c:
            return r1
        L7d:
            r1 = r3
            goto L2
        L7f:
            if (r7 != 0) goto L83
            r7 = -1
            return r7
        L83:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r0 = "End of input"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.v.k0.e.H(boolean):int");
    }

    private String I(c.d dVar) throws b {
        StringBuilder sb = null;
        while (true) {
            long jF = this.m.f(dVar);
            if (jF == -1) {
                throw C("Unterminated string");
            }
            if (this.n.m(jF) != 92) {
                String strY = this.n.y(jF);
                if (sb == null) {
                    this.n.q();
                    return strY;
                }
                sb.append(strY);
                this.n.q();
                return sb.toString();
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.n.y(jF));
            this.n.q();
            sb.append(M());
        }
    }

    private String J() {
        long jF = this.m.f(j);
        return jF != -1 ? this.n.y(jF) : this.n.x();
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
    private int K() throws EOFException {
        int i2;
        String str;
        String str2;
        byte bM = this.n.m(0L);
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
            str = BootBusinessConstants.SubBusinessState.SUB_BUSINESS_NULL;
            str2 = "NULL";
        }
        int length = str.length();
        int i3 = 1;
        while (i3 < length) {
            int i4 = i3 + 1;
            if (!this.m.b(i4)) {
                return 0;
            }
            byte bM2 = this.n.m(i3);
            if (bM2 != str.charAt(i3) && bM2 != str2.charAt(i3)) {
                return 0;
            }
            i3 = i4;
        }
        if (this.m.b(length + 1) && G(this.n.m(length))) {
            return 0;
        }
        this.n.B(length);
        this.o = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0082, code lost:
    
        if (G(r11) != false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0084, code lost:
    
        if (r6 != 2) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0086, code lost:
    
        if (r7 == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008c, code lost:
    
        if (r8 != Long.MIN_VALUE) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
    
        if (r10 == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0092, code lost:
    
        if (r8 != 0) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0094, code lost:
    
        if (r10 != false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0096, code lost:
    
        if (r10 == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0099, code lost:
    
        r8 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009a, code lost:
    
        r16.p = r8;
        r16.n.B(r5);
        r1 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a4, code lost:
    
        r16.o = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a6, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a7, code lost:
    
        if (r6 == 2) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00aa, code lost:
    
        if (r6 == 4) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ad, code lost:
    
        if (r6 != 7) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b0, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00b2, code lost:
    
        r16.q = r5;
        r1 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00b7, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int L() throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.v.k0.e.L():int");
    }

    private char M() throws b, EOFException {
        int i2;
        int i3;
        if (!this.m.b(1L)) {
            throw C("Unterminated escape sequence");
        }
        byte bQ = this.n.q();
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
            if (this.f) {
                return (char) bQ;
            }
            throw C("Invalid escape sequence: \\" + ((char) bQ));
        }
        if (!this.m.b(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + o());
        }
        char c2 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            byte bM = this.n.m(i4);
            char c3 = (char) (c2 << 4);
            if (bM < 48 || bM > 57) {
                if (bM >= 97 && bM <= 102) {
                    i2 = bM - 97;
                } else {
                    if (bM < 65 || bM > 70) {
                        throw C("\\u" + this.n.y(4L));
                    }
                    i2 = bM - 65;
                }
                i3 = i2 + 10;
            } else {
                i3 = bM - 48;
            }
            c2 = (char) (c3 + i3);
        }
        this.n.B(4L);
        return c2;
    }

    private void N(c.d dVar) throws b, EOFException {
        while (true) {
            long jF = this.m.f(dVar);
            if (jF == -1) {
                throw C("Unterminated string");
            }
            if (this.n.m(jF) != 92) {
                this.n.B(jF + 1);
                return;
            } else {
                this.n.B(jF + 1);
                M();
            }
        }
    }

    private boolean O() throws EOFException {
        c.c cVar = this.m;
        c.d dVar = l;
        long jC = cVar.c(dVar);
        boolean z = jC != -1;
        c.a aVar = this.n;
        aVar.B(z ? jC + ((long) dVar.j()) : aVar.A());
        return z;
    }

    private void P() throws EOFException {
        long jF = this.m.f(k);
        c.a aVar = this.n;
        aVar.B(jF != -1 ? jF + 1 : aVar.A());
    }

    private void Q() throws EOFException {
        long jF = this.m.f(j);
        c.a aVar = this.n;
        if (jF == -1) {
            jF = aVar.A();
        }
        aVar.B(jF);
    }

    @Override // com.airbnb.lottie.v.k0.c
    public void A() throws b, EOFException {
        c.d dVar;
        if (this.g) {
            throw new a("Cannot skip unexpected " + w() + " at " + o());
        }
        int i2 = 0;
        do {
            int iE = this.o;
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
                        throw new a("Expected a value but was " + w() + " at path " + o());
                    }
                } else if (iE == 2) {
                    i2--;
                    if (i2 < 0) {
                        throw new a("Expected a value but was " + w() + " at path " + o());
                    }
                } else {
                    if (iE == 14 || iE == 10) {
                        Q();
                    } else {
                        if (iE == 9 || iE == 13) {
                            dVar = i;
                        } else if (iE == 8 || iE == 12) {
                            dVar = h;
                        } else if (iE == 17) {
                            this.n.B(this.q);
                        } else if (iE == 18) {
                            throw new a("Expected a value but was " + w() + " at path " + o());
                        }
                        N(dVar);
                    }
                    this.o = 0;
                }
                this.f934b--;
                this.o = 0;
            }
            i2++;
            this.o = 0;
        } while (i2 != 0);
        int[] iArr = this.e;
        int i3 = this.f934b;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.d[i3 - 1] = BootBusinessConstants.SubBusinessState.SUB_BUSINESS_NULL;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.o = 0;
        this.f935c[0] = 8;
        this.f934b = 1;
        this.n.j();
        this.m.close();
    }

    @Override // com.airbnb.lottie.v.k0.c
    public void k() throws b, EOFException {
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 3) {
            x(1);
            this.e[this.f934b - 1] = 0;
            this.o = 0;
        } else {
            throw new a("Expected BEGIN_ARRAY but was " + w() + " at path " + o());
        }
    }

    @Override // com.airbnb.lottie.v.k0.c
    public void l() throws b, EOFException {
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 1) {
            x(3);
            this.o = 0;
            return;
        }
        throw new a("Expected BEGIN_OBJECT but was " + w() + " at path " + o());
    }

    @Override // com.airbnb.lottie.v.k0.c
    public void m() throws b, EOFException {
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE != 4) {
            throw new a("Expected END_ARRAY but was " + w() + " at path " + o());
        }
        int i2 = this.f934b - 1;
        this.f934b = i2;
        int[] iArr = this.e;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.o = 0;
    }

    @Override // com.airbnb.lottie.v.k0.c
    public void n() throws b, EOFException {
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE != 2) {
            throw new a("Expected END_OBJECT but was " + w() + " at path " + o());
        }
        int i2 = this.f934b - 1;
        this.f934b = i2;
        this.d[i2] = null;
        int[] iArr = this.e;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.o = 0;
    }

    @Override // com.airbnb.lottie.v.k0.c
    public boolean p() throws b, EOFException {
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        return (iE == 2 || iE == 4 || iE == 18) ? false : true;
    }

    @Override // com.airbnb.lottie.v.k0.c
    public boolean q() throws b, EOFException {
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 5) {
            this.o = 0;
            int[] iArr = this.e;
            int i2 = this.f934b - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (iE == 6) {
            this.o = 0;
            int[] iArr2 = this.e;
            int i3 = this.f934b - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw new a("Expected a boolean but was " + w() + " at path " + o());
    }

    @Override // com.airbnb.lottie.v.k0.c
    public double r() throws b, EOFException {
        String strJ;
        c.d dVar;
        double d;
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 16) {
            this.o = 0;
            int[] iArr = this.e;
            int i2 = this.f934b - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.p;
        }
        try {
            if (iE == 17) {
                strJ = this.n.y(this.q);
            } else {
                if (iE == 9) {
                    dVar = i;
                } else if (iE == 8) {
                    dVar = h;
                } else {
                    if (iE != 10) {
                        if (iE != 11) {
                            throw new a("Expected a double but was " + w() + " at path " + o());
                        }
                        this.o = 11;
                        d = Double.parseDouble(this.r);
                        if (this.f && (Double.isNaN(d) || Double.isInfinite(d))) {
                            throw new b("JSON forbids NaN and infinities: " + d + " at path " + o());
                        }
                        this.r = null;
                        this.o = 0;
                        int[] iArr2 = this.e;
                        int i3 = this.f934b - 1;
                        iArr2[i3] = iArr2[i3] + 1;
                        return d;
                    }
                    strJ = J();
                }
                strJ = I(dVar);
            }
            d = Double.parseDouble(this.r);
            if (this.f) {
            }
            this.r = null;
            this.o = 0;
            int[] iArr22 = this.e;
            int i32 = this.f934b - 1;
            iArr22[i32] = iArr22[i32] + 1;
            return d;
        } catch (NumberFormatException unused) {
            throw new a("Expected a double but was " + this.r + " at path " + o());
        }
        this.r = strJ;
        this.o = 11;
    }

    @Override // com.airbnb.lottie.v.k0.c
    public int s() throws b, EOFException {
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 16) {
            long j2 = this.p;
            int i2 = (int) j2;
            if (j2 == i2) {
                this.o = 0;
                int[] iArr = this.e;
                int i3 = this.f934b - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new a("Expected an int but was " + this.p + " at path " + o());
        }
        if (iE == 17) {
            this.r = this.n.y(this.q);
        } else if (iE == 9 || iE == 8) {
            String strI = I(iE == 9 ? i : h);
            this.r = strI;
            try {
                int i4 = Integer.parseInt(strI);
                this.o = 0;
                int[] iArr2 = this.e;
                int i5 = this.f934b - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return i4;
            } catch (NumberFormatException unused) {
            }
        } else if (iE != 11) {
            throw new a("Expected an int but was " + w() + " at path " + o());
        }
        this.o = 11;
        try {
            double d = Double.parseDouble(this.r);
            int i6 = (int) d;
            if (i6 == d) {
                this.r = null;
                this.o = 0;
                int[] iArr3 = this.e;
                int i7 = this.f934b - 1;
                iArr3[i7] = iArr3[i7] + 1;
                return i6;
            }
            throw new a("Expected an int but was " + this.r + " at path " + o());
        } catch (NumberFormatException unused2) {
            throw new a("Expected an int but was " + this.r + " at path " + o());
        }
    }

    @Override // com.airbnb.lottie.v.k0.c
    public String t() throws b, EOFException {
        String strI;
        c.d dVar;
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 14) {
            strI = J();
        } else {
            if (iE == 13) {
                dVar = i;
            } else if (iE == 12) {
                dVar = h;
            } else {
                if (iE != 15) {
                    throw new a("Expected a name but was " + w() + " at path " + o());
                }
                strI = this.r;
            }
            strI = I(dVar);
        }
        this.o = 0;
        this.d[this.f934b - 1] = strI;
        return strI;
    }

    public String toString() {
        return "JsonReader(" + this.m + ")";
    }

    @Override // com.airbnb.lottie.v.k0.c
    public String u() throws b, EOFException {
        String strY;
        c.d dVar;
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 10) {
            strY = J();
        } else {
            if (iE == 9) {
                dVar = i;
            } else if (iE == 8) {
                dVar = h;
            } else if (iE == 11) {
                strY = this.r;
                this.r = null;
            } else if (iE == 16) {
                strY = Long.toString(this.p);
            } else {
                if (iE != 17) {
                    throw new a("Expected a string but was " + w() + " at path " + o());
                }
                strY = this.n.y(this.q);
            }
            strY = I(dVar);
        }
        this.o = 0;
        int[] iArr = this.e;
        int i2 = this.f934b - 1;
        iArr[i2] = iArr[i2] + 1;
        return strY;
    }

    @Override // com.airbnb.lottie.v.k0.c
    public c.b w() throws b, EOFException {
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        switch (iE) {
            case 1:
                return c.b.BEGIN_OBJECT;
            case 2:
                return c.b.END_OBJECT;
            case 3:
                return c.b.BEGIN_ARRAY;
            case 4:
                return c.b.END_ARRAY;
            case 5:
            case 6:
                return c.b.BOOLEAN;
            case 7:
                return c.b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return c.b.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return c.b.NAME;
            case 16:
            case 17:
                return c.b.NUMBER;
            case 18:
                return c.b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    @Override // com.airbnb.lottie.v.k0.c
    public int y(c.a aVar) throws b, EOFException {
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE < 12 || iE > 15) {
            return -1;
        }
        if (iE == 15) {
            return F(this.r, aVar);
        }
        int i2 = this.m.i(aVar.f937b);
        if (i2 != -1) {
            this.o = 0;
            this.d[this.f934b - 1] = aVar.f936a[i2];
            return i2;
        }
        String str = this.d[this.f934b - 1];
        String strT = t();
        int iF = F(strT, aVar);
        if (iF == -1) {
            this.o = 15;
            this.r = strT;
            this.d[this.f934b - 1] = str;
        }
        return iF;
    }

    @Override // com.airbnb.lottie.v.k0.c
    public void z() throws b, EOFException {
        c.d dVar;
        if (this.g) {
            throw new a("Cannot skip unexpected " + w() + " at " + o());
        }
        int iE = this.o;
        if (iE == 0) {
            iE = E();
        }
        if (iE == 14) {
            Q();
        } else {
            if (iE == 13) {
                dVar = i;
            } else if (iE == 12) {
                dVar = h;
            } else if (iE != 15) {
                throw new a("Expected a name but was " + w() + " at path " + o());
            }
            N(dVar);
        }
        this.o = 0;
        this.d[this.f934b - 1] = BootBusinessConstants.SubBusinessState.SUB_BUSINESS_NULL;
    }
}
