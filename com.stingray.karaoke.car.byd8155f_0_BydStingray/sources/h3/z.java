package h3;

import B2.N;
import h2.AbstractC1346c;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class z implements I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC1358i f15501a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N f15502b = new N(10, new byte[10]);
    public int c = 0;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public h2.y f15503e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f15504f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f15505g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f15506h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15507i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f15508j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f15509k;
    public long l;

    public z(InterfaceC1358i interfaceC1358i) {
        this.f15501a = interfaceC1358i;
    }

    @Override // h3.I
    public final void a(h2.y yVar, B2.t tVar, H h10) {
        this.f15503e = yVar;
        this.f15501a.f(tVar, h10);
    }

    @Override // h3.I
    public final void b() {
        this.c = 0;
        this.d = 0;
        this.f15506h = false;
        this.f15501a.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [B2.N] */
    /* JADX WARN: Type inference failed for: r11v3, types: [int] */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v2 */
    /* JADX WARN: Type inference failed for: r3v0, types: [h3.i] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // h3.I
    public final void c(int i7, h2.t tVar) {
        int i10;
        int i11;
        int i12;
        boolean z6;
        int i13;
        AbstractC1346c.k(this.f15503e);
        int i14 = i7 & 1;
        ?? r32 = this.f15501a;
        int i15 = -1;
        int i16 = 3;
        int i17 = 2;
        ?? r92 = 0;
        if (i14 != 0) {
            int i18 = this.c;
            if (i18 != 0 && i18 != 1) {
                if (i18 == 2) {
                    AbstractC1346c.z("PesReader", "Unexpected start indicator reading extended header");
                } else {
                    if (i18 != 3) {
                        throw new IllegalStateException();
                    }
                    if (this.f15508j != -1) {
                        AbstractC1346c.z("PesReader", "Unexpected start indicator: expected " + this.f15508j + " more bytes");
                    }
                    r32.d(tVar.c == 0);
                }
            }
            this.c = 1;
            this.d = 0;
        }
        int i19 = i7;
        while (tVar.a() > 0) {
            int i20 = this.c;
            if (i20 != 0) {
                ?? r11 = this.f15502b;
                if (i20 == 1) {
                    i11 = i16;
                    ?? r72 = r92;
                    if (d(tVar, r11.d, 9)) {
                        r11.r(r72 == true ? 1 : 0);
                        int i21 = r11.i(24);
                        if (i21 != 1) {
                            AbstractC1347d.p("Unexpected start code prefix: ", i21, "PesReader");
                            this.f15508j = -1;
                            i10 = -1;
                            i13 = 0;
                            i12 = 2;
                        } else {
                            r11.u(8);
                            int i22 = r11.i(16);
                            r11.u(5);
                            this.f15509k = r11.h();
                            i12 = 2;
                            r11.u(2);
                            this.f15504f = r11.h();
                            this.f15505g = r11.h();
                            r11.u(6);
                            int i23 = r11.i(8);
                            this.f15507i = i23;
                            if (i22 == 0) {
                                this.f15508j = -1;
                                i10 = -1;
                            } else {
                                int i24 = (i22 - 3) - i23;
                                this.f15508j = i24;
                                if (i24 < 0) {
                                    AbstractC1346c.z("PesReader", "Found negative packet payload size: " + this.f15508j);
                                    i10 = -1;
                                    this.f15508j = -1;
                                } else {
                                    i10 = -1;
                                }
                            }
                            i13 = 2;
                        }
                        this.c = i13;
                        z6 = false;
                        this.d = 0;
                    } else {
                        z6 = r72 == true ? 1 : 0;
                        i10 = -1;
                        i12 = 2;
                    }
                } else if (i20 == i17) {
                    if (d(tVar, r11.d, Math.min(10, this.f15507i)) && d(tVar, null, this.f15507i)) {
                        r11.r(r92);
                        this.l = -9223372036854775807L;
                        if (this.f15504f) {
                            r11.u(4);
                            long jI = ((long) r11.i(i16)) << 30;
                            r11.u(1);
                            long jI2 = jI | ((long) (r11.i(15) << 15));
                            r11.u(1);
                            long jI3 = jI2 | ((long) r11.i(15));
                            r11.u(1);
                            if (!this.f15506h && this.f15505g) {
                                r11.u(4);
                                long jI4 = ((long) r11.i(i16)) << 30;
                                r11.u(1);
                                long jI5 = ((long) (r11.i(15) << 15)) | jI4;
                                r11.u(1);
                                long jI6 = jI5 | ((long) r11.i(15));
                                r11.u(1);
                                this.f15503e.b(jI6);
                                this.f15506h = true;
                            }
                            this.l = this.f15503e.b(jI3);
                        }
                        i19 |= this.f15509k ? 4 : 0;
                        r32.e(this.l, i19);
                        this.c = 3;
                        this.d = 0;
                        r92 = 0;
                        i17 = 2;
                        i16 = 3;
                        i15 = -1;
                    } else {
                        i11 = i16;
                        i10 = -1;
                        ?? r17 = r92;
                        i12 = i17;
                        z6 = r17 == true ? 1 : 0;
                    }
                } else {
                    if (i20 != i16) {
                        throw new IllegalStateException();
                    }
                    int iA = tVar.a();
                    int i25 = this.f15508j;
                    ?? r112 = i25 == i15 ? r92 : iA - i25;
                    if (r112 > 0) {
                        iA -= r112;
                        tVar.G(tVar.f15242b + iA);
                    }
                    r32.c(tVar);
                    int i26 = this.f15508j;
                    if (i26 != i15) {
                        int i27 = i26 - iA;
                        this.f15508j = i27;
                        if (i27 == 0) {
                            r32.d(r92);
                            this.c = 1;
                            this.d = r92;
                        }
                    }
                    int i28 = i16;
                    i10 = i15;
                    i11 = i28;
                    ?? r18 = r92;
                    i12 = i17;
                    z6 = r18 == true ? 1 : 0;
                }
            } else {
                int i29 = i16;
                i10 = i15;
                i11 = i29;
                ?? r182 = r92;
                i12 = i17;
                z6 = r182 == true ? 1 : 0;
                tVar.I(tVar.a());
            }
            int i30 = i10;
            i16 = i11;
            i15 = i30;
            int i31 = i12;
            r92 = z6;
            i17 = i31;
        }
    }

    public final boolean d(h2.t tVar, byte[] bArr, int i7) {
        int iMin = Math.min(tVar.a(), i7 - this.d);
        if (iMin <= 0) {
            return true;
        }
        if (bArr == null) {
            tVar.I(iMin);
        } else {
            tVar.f(this.d, iMin, bArr);
        }
        int i10 = this.d + iMin;
        this.d = i10;
        return i10 == i7;
    }
}
