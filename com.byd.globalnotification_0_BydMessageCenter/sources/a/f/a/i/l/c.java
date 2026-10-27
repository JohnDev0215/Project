package a.f.a.i.l;

import a.f.a.i.d;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class c extends o {
    public ArrayList<o> k;
    public int l;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0026, code lost:
    
        r4.add(r0);
        r3 = r3.n(r2.f611f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002f, code lost:
    
        if (r3 == null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
    
        r4 = r2.k;
        r0 = r2.f611f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
    
        if (r0 != 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
    
        if (r0 != 1) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003c, code lost:
    
        r0 = r3.f559e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003f, code lost:
    
        r3 = r2.k.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
    
        if (r3.hasNext() == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004b, code lost:
    
        r4 = r3.next();
        r0 = r2.f611f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0053, code lost:
    
        if (r0 != 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0055, code lost:
    
        r4.f607b.f556b = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        if (r0 != 1) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
    
        r4.f607b.f557c = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0063, code lost:
    
        if (r2.f611f != 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006d, code lost:
    
        if (((a.f.a.i.e) r2.f607b.X).A0 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006f, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0071, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0072, code lost:
    
        if (r3 == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007a, code lost:
    
        if (r2.k.size() <= 1) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007c, code lost:
    
        r3 = r2.k;
        r2.f607b = r3.get(r3.size() - 1).f607b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008f, code lost:
    
        if (r2.f611f != 0) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0091, code lost:
    
        r3 = r2.f607b.o0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0096, code lost:
    
        r3 = r2.f607b.p0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009a, code lost:
    
        r2.l = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x009c, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0026, code lost:
    
        r0 = r3.f558d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0026, code lost:
    
        r0 = r3.f559e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        if (r0 == 1) goto L18;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x003a -> B:11:0x0025). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c(a.f.a.i.d r3, int r4) {
        /*
            r2 = this;
            r2.<init>(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r2.k = r3
            r2.f611f = r4
            a.f.a.i.d r3 = r2.f607b
        Le:
            int r4 = r2.f611f
            a.f.a.i.d r4 = r3.o(r4)
            if (r4 == 0) goto L18
            r3 = r4
            goto Le
        L18:
            r2.f607b = r3
            java.util.ArrayList<a.f.a.i.l.o> r4 = r2.k
            int r0 = r2.f611f
            r1 = 1
            if (r0 != 0) goto L22
            goto L37
        L22:
            if (r0 != r1) goto L25
            goto L3c
        L25:
            r0 = 0
        L26:
            r4.add(r0)
            int r4 = r2.f611f
            a.f.a.i.d r3 = r3.n(r4)
            if (r3 == 0) goto L3f
            java.util.ArrayList<a.f.a.i.l.o> r4 = r2.k
            int r0 = r2.f611f
            if (r0 != 0) goto L3a
        L37:
            a.f.a.i.l.k r0 = r3.f558d
            goto L26
        L3a:
            if (r0 != r1) goto L25
        L3c:
            a.f.a.i.l.m r0 = r3.f559e
            goto L26
        L3f:
            java.util.ArrayList<a.f.a.i.l.o> r3 = r2.k
            java.util.Iterator r3 = r3.iterator()
        L45:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L61
            java.lang.Object r4 = r3.next()
            a.f.a.i.l.o r4 = (a.f.a.i.l.o) r4
            int r0 = r2.f611f
            if (r0 != 0) goto L5a
            a.f.a.i.d r4 = r4.f607b
            r4.f556b = r2
            goto L45
        L5a:
            if (r0 != r1) goto L45
            a.f.a.i.d r4 = r4.f607b
            r4.f557c = r2
            goto L45
        L61:
            int r3 = r2.f611f
            if (r3 != 0) goto L71
            a.f.a.i.d r3 = r2.f607b
            a.f.a.i.d r3 = r3.X
            a.f.a.i.e r3 = (a.f.a.i.e) r3
            boolean r3 = r3.A0
            if (r3 == 0) goto L71
            r3 = r1
            goto L72
        L71:
            r3 = 0
        L72:
            if (r3 == 0) goto L8d
            java.util.ArrayList<a.f.a.i.l.o> r3 = r2.k
            int r3 = r3.size()
            if (r3 <= r1) goto L8d
            java.util.ArrayList<a.f.a.i.l.o> r3 = r2.k
            int r4 = r3.size()
            int r4 = r4 - r1
            java.lang.Object r3 = r3.get(r4)
            a.f.a.i.l.o r3 = (a.f.a.i.l.o) r3
            a.f.a.i.d r3 = r3.f607b
            r2.f607b = r3
        L8d:
            int r3 = r2.f611f
            if (r3 != 0) goto L96
            a.f.a.i.d r3 = r2.f607b
            int r3 = r3.o0
            goto L9a
        L96:
            a.f.a.i.d r3 = r2.f607b
            int r3 = r3.p0
        L9a:
            r2.l = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.l.c.<init>(a.f.a.i.d, int):void");
    }

    @Override // a.f.a.i.l.o, a.f.a.i.l.d
    public void a(d dVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        float f2;
        d.a aVar;
        int i6;
        int i7;
        int i8;
        d.a aVar2;
        d.a aVar3;
        float f3;
        int i9;
        int i10;
        d.a aVar4 = d.a.MATCH_CONSTRAINT;
        if (this.h.j && this.i.j) {
            a.f.a.i.d dVar2 = this.f607b.X;
            boolean z = dVar2 instanceof a.f.a.i.e ? ((a.f.a.i.e) dVar2).A0 : false;
            int i11 = this.i.g - this.h.g;
            int size = this.k.size();
            int i12 = 0;
            while (true) {
                i = -1;
                i2 = 8;
                if (i12 >= size) {
                    i12 = -1;
                    break;
                } else if (this.k.get(i12).f607b.k0 != 8) {
                    break;
                } else {
                    i12++;
                }
            }
            int i13 = size - 1;
            int i14 = i13;
            while (true) {
                if (i14 < 0) {
                    break;
                }
                if (this.k.get(i14).f607b.k0 != 8) {
                    i = i14;
                    break;
                }
                i14--;
            }
            int i15 = 0;
            while (i15 < 2) {
                int i16 = 0;
                i5 = 0;
                int i17 = 0;
                int i18 = 0;
                f2 = 0.0f;
                while (i16 < size) {
                    o oVar = this.k.get(i16);
                    if (oVar.f607b.k0 != i2) {
                        i18++;
                        if (i16 > 0 && i16 >= i12) {
                            i5 += oVar.h.f587f;
                        }
                        int i19 = oVar.f610e.g;
                        boolean z2 = oVar.f609d != aVar4;
                        if (!z2) {
                            if (oVar.f606a == 1 && i15 == 0) {
                                i19 = oVar.f610e.m;
                                i17++;
                            } else if (oVar.f610e.j) {
                            }
                            z2 = true;
                        } else {
                            if (this.f611f == 0 && !oVar.f607b.f558d.f610e.j) {
                                return;
                            }
                            if (this.f611f == 1 && !oVar.f607b.f559e.f610e.j) {
                                return;
                            }
                        }
                        if (z2) {
                            i5 += i19;
                        } else {
                            i17++;
                            float f4 = oVar.f607b.q0[this.f611f];
                            if (f4 >= 0.0f) {
                                f2 += f4;
                            }
                        }
                        if (i16 < i13 && i16 < i) {
                            i5 += -oVar.i.f587f;
                        }
                    }
                    i16++;
                    i2 = 8;
                }
                if (i5 < i11 || i17 == 0) {
                    i3 = i17;
                    i4 = i18;
                    break;
                } else {
                    i15++;
                    i2 = 8;
                }
            }
            i3 = 0;
            i4 = 0;
            i5 = 0;
            f2 = 0.0f;
            int i20 = this.h.g;
            if (z) {
                i20 = this.i.g;
            }
            if (i5 > i11) {
                int i21 = (int) (((i5 - i11) / 2.0f) + 0.5f);
                i20 = z ? i20 + i21 : i20 - i21;
            }
            if (i3 > 0) {
                float f5 = i11 - i5;
                int i22 = (int) ((f5 / i3) + 0.5f);
                int i23 = 0;
                int i24 = 0;
                while (i23 < size) {
                    o oVar2 = this.k.get(i23);
                    int i25 = i22;
                    a.f.a.i.d dVar3 = oVar2.f607b;
                    int i26 = i5;
                    int i27 = i20;
                    if (dVar3.k0 == 8 || oVar2.f609d != aVar4 || oVar2.f610e.j) {
                        aVar3 = aVar4;
                        f3 = f5;
                    } else {
                        int i28 = f2 > 0.0f ? (int) (((dVar3.q0[this.f611f] * f5) / f2) + 0.5f) : i25;
                        if (this.f611f == 0) {
                            a.f.a.i.d dVar4 = oVar2.f607b;
                            i10 = dVar4.x;
                            i9 = dVar4.w;
                            f3 = f5;
                        } else {
                            a.f.a.i.d dVar5 = oVar2.f607b;
                            int i29 = dVar5.A;
                            f3 = f5;
                            i9 = dVar5.z;
                            i10 = i29;
                        }
                        aVar3 = aVar4;
                        int iMax = Math.max(i9, oVar2.f606a == 1 ? Math.min(i28, oVar2.f610e.m) : i28);
                        if (i10 > 0) {
                            iMax = Math.min(i10, iMax);
                        }
                        if (iMax != i28) {
                            i24++;
                            i28 = iMax;
                        }
                        oVar2.f610e.c(i28);
                    }
                    i23++;
                    i22 = i25;
                    i5 = i26;
                    i20 = i27;
                    f5 = f3;
                    aVar4 = aVar3;
                }
                aVar = aVar4;
                i6 = i20;
                int i30 = i5;
                if (i24 > 0) {
                    i3 -= i24;
                    int i31 = 0;
                    for (int i32 = 0; i32 < size; i32++) {
                        o oVar3 = this.k.get(i32);
                        if (oVar3.f607b.k0 != 8) {
                            if (i32 > 0 && i32 >= i12) {
                                i31 += oVar3.h.f587f;
                            }
                            i31 += oVar3.f610e.g;
                            if (i32 < i13 && i32 < i) {
                                i31 += -oVar3.i.f587f;
                            }
                        }
                    }
                    i5 = i31;
                } else {
                    i5 = i30;
                }
                i8 = 2;
                if (this.l == 2 && i24 == 0) {
                    i7 = 0;
                    this.l = 0;
                } else {
                    i7 = 0;
                }
            } else {
                aVar = aVar4;
                i6 = i20;
                i7 = 0;
                i8 = 2;
            }
            if (i5 > i11) {
                this.l = i8;
            }
            if (i4 > 0 && i3 == 0 && i12 == i) {
                this.l = i8;
            }
            int i33 = this.l;
            if (i33 == 1) {
                int i34 = i4 > 1 ? (i11 - i5) / (i4 - 1) : i4 == 1 ? (i11 - i5) / 2 : i7;
                if (i3 > 0) {
                    i34 = i7;
                }
                int i35 = i7;
                int i36 = i6;
                while (i35 < size) {
                    o oVar4 = this.k.get(z ? size - (i35 + 1) : i35);
                    if (oVar4.f607b.k0 == 8) {
                        oVar4.h.c(i36);
                        oVar4.i.c(i36);
                        aVar2 = aVar;
                    } else {
                        if (i35 > 0) {
                            i36 = z ? i36 - i34 : i36 + i34;
                        }
                        if (i35 > 0 && i35 >= i12) {
                            int i37 = oVar4.h.f587f;
                            i36 = z ? i36 - i37 : i36 + i37;
                        }
                        (z ? oVar4.i : oVar4.h).c(i36);
                        g gVar = oVar4.f610e;
                        int i38 = gVar.g;
                        aVar2 = aVar;
                        if (oVar4.f609d == aVar2 && oVar4.f606a == 1) {
                            i38 = gVar.m;
                        }
                        i36 = z ? i36 - i38 : i36 + i38;
                        (z ? oVar4.h : oVar4.i).c(i36);
                        oVar4.g = true;
                        if (i35 < i13 && i35 < i) {
                            int i39 = -oVar4.i.f587f;
                            i36 = z ? i36 - i39 : i36 + i39;
                        }
                    }
                    i35++;
                    aVar = aVar2;
                }
                return;
            }
            d.a aVar5 = aVar;
            if (i33 == 0) {
                int i40 = (i11 - i5) / (i4 + 1);
                if (i3 > 0) {
                    i40 = i7;
                }
                int i41 = i6;
                for (int i42 = i7; i42 < size; i42++) {
                    o oVar5 = this.k.get(z ? size - (i42 + 1) : i42);
                    if (oVar5.f607b.k0 == 8) {
                        oVar5.h.c(i41);
                        oVar5.i.c(i41);
                    } else {
                        int i43 = z ? i41 - i40 : i41 + i40;
                        if (i42 > 0 && i42 >= i12) {
                            int i44 = oVar5.h.f587f;
                            i43 = z ? i43 - i44 : i43 + i44;
                        }
                        (z ? oVar5.i : oVar5.h).c(i43);
                        g gVar2 = oVar5.f610e;
                        int iMin = gVar2.g;
                        if (oVar5.f609d == aVar5 && oVar5.f606a == 1) {
                            iMin = Math.min(iMin, gVar2.m);
                        }
                        i41 = z ? i43 - iMin : i43 + iMin;
                        (z ? oVar5.h : oVar5.i).c(i41);
                        if (i42 < i13 && i42 < i) {
                            int i45 = -oVar5.i.f587f;
                            i41 = z ? i41 - i45 : i41 + i45;
                        }
                    }
                }
                return;
            }
            if (i33 == 2) {
                float f6 = this.f611f == 0 ? this.f607b.h0 : this.f607b.i0;
                if (z) {
                    f6 = 1.0f - f6;
                }
                int i46 = (int) (((i11 - i5) * f6) + 0.5f);
                if (i46 < 0 || i3 > 0) {
                    i46 = i7;
                }
                int i47 = z ? i6 - i46 : i6 + i46;
                for (int i48 = i7; i48 < size; i48++) {
                    o oVar6 = this.k.get(z ? size - (i48 + 1) : i48);
                    if (oVar6.f607b.k0 == 8) {
                        oVar6.h.c(i47);
                        oVar6.i.c(i47);
                    } else {
                        if (i48 > 0 && i48 >= i12) {
                            int i49 = oVar6.h.f587f;
                            i47 = z ? i47 - i49 : i47 + i49;
                        }
                        (z ? oVar6.i : oVar6.h).c(i47);
                        g gVar3 = oVar6.f610e;
                        int i50 = gVar3.g;
                        if (oVar6.f609d == aVar5 && oVar6.f606a == 1) {
                            i50 = gVar3.m;
                        }
                        i47 = z ? i47 - i50 : i47 + i50;
                        (z ? oVar6.h : oVar6.i).c(i47);
                        if (i48 < i13 && i48 < i) {
                            int i51 = -oVar6.i.f587f;
                            i47 = z ? i47 - i51 : i47 + i51;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0078 A[PHI: r0 r1
  0x0078: PHI (r0v15 int) = (r0v10 int), (r0v20 int) binds: [B:29:0x00c4, B:19:0x0076] A[DONT_GENERATE, DONT_INLINE]
  0x0078: PHI (r1v3 a.f.a.i.l.f) = (r1v2 a.f.a.i.l.f), (r1v7 a.f.a.i.l.f) binds: [B:29:0x00c4, B:19:0x0076] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // a.f.a.i.l.o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d() {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.l.c.d():void");
    }

    @Override // a.f.a.i.l.o
    public void e() {
        for (int i = 0; i < this.k.size(); i++) {
            this.k.get(i).e();
        }
    }

    @Override // a.f.a.i.l.o
    public void f() {
        this.f608c = null;
        Iterator<o> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
    }

    @Override // a.f.a.i.l.o
    public long j() {
        int size = this.k.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            o oVar = this.k.get(i);
            j = ((long) oVar.i.f587f) + oVar.j() + j + ((long) oVar.h.f587f);
        }
        return j;
    }

    @Override // a.f.a.i.l.o
    public boolean k() {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            if (!this.k.get(i).k()) {
                return false;
            }
        }
        return true;
    }

    public final a.f.a.i.d m() {
        for (int i = 0; i < this.k.size(); i++) {
            a.f.a.i.d dVar = this.k.get(i).f607b;
            if (dVar.k0 != 8) {
                return dVar;
            }
        }
        return null;
    }

    public final a.f.a.i.d n() {
        for (int size = this.k.size() - 1; size >= 0; size--) {
            a.f.a.i.d dVar = this.k.get(size).f607b;
            if (dVar.k0 != 8) {
                return dVar;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.f611f == 0 ? "horizontal : " : "vertical : ");
        for (o oVar : this.k) {
            sb.append("<");
            sb.append(oVar);
            sb.append("> ");
        }
        return sb.toString();
    }
}
