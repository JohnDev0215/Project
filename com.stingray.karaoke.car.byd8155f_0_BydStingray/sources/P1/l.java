package p1;

/* JADX INFO: loaded from: classes.dex */
public final class l extends p {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f20671k = new int[2];

    public static void m(int[] iArr, int i7, int i10, int i11, int i12, float f10, int i13) {
        int i14 = i10 - i7;
        int i15 = i12 - i11;
        if (i13 != -1) {
            if (i13 == 0) {
                iArr[0] = (int) ((i15 * f10) + 0.5f);
                iArr[1] = i15;
                return;
            } else {
                if (i13 != 1) {
                    return;
                }
                iArr[0] = i14;
                iArr[1] = (int) ((i14 * f10) + 0.5f);
                return;
            }
        }
        int i16 = (int) ((i15 * f10) + 0.5f);
        int i17 = (int) ((i14 / f10) + 0.5f);
        if (i16 <= i14) {
            iArr[0] = i16;
            iArr[1] = i15;
        } else if (i17 <= i15) {
            iArr[0] = i14;
            iArr[1] = i17;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0267  */
    @Override // p1.InterfaceC2077e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(p1.InterfaceC2077e r24) {
        /*
            Method dump skipped, instruction units count: 907
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p1.l.a(p1.e):void");
    }

    @Override // p1.p
    public final void d() {
        o1.d dVar;
        o1.e eVar;
        int i7;
        o1.e eVar2;
        o1.e eVar3;
        int i10;
        o1.d dVar2 = this.f20680b;
        boolean z6 = dVar2.f20282a;
        C2080h c2080h = this.f20681e;
        if (z6) {
            c2080h.d(dVar2.r());
        }
        boolean z10 = c2080h.f20667j;
        C2079g c2079g = this.f20685i;
        C2079g c2079g2 = this.f20684h;
        if (!z10) {
            o1.d dVar3 = this.f20680b;
            int i11 = dVar3.f20311q0[0];
            this.d = i11;
            if (i11 != 3) {
                if (i11 == 4 && (eVar3 = dVar3.f20276U) != null && ((i10 = eVar3.f20311q0[0]) == 1 || i10 == 4)) {
                    int iR = (eVar3.r() - this.f20680b.f20265J.e()) - this.f20680b.f20267L.e();
                    p.b(c2079g2, eVar3.d.f20684h, this.f20680b.f20265J.e());
                    p.b(c2079g, eVar3.d.f20685i, -this.f20680b.f20267L.e());
                    c2080h.d(iR);
                    return;
                }
                if (i11 == 1) {
                    c2080h.d(dVar3.r());
                }
            }
        } else if (this.d == 4 && (eVar = (dVar = this.f20680b).f20276U) != null && ((i7 = eVar.f20311q0[0]) == 1 || i7 == 4)) {
            p.b(c2079g2, eVar.d.f20684h, dVar.f20265J.e());
            p.b(c2079g, eVar.d.f20685i, -this.f20680b.f20267L.e());
            return;
        }
        if (c2080h.f20667j) {
            o1.d dVar4 = this.f20680b;
            if (dVar4.f20282a) {
                o1.c[] cVarArr = dVar4.f20273R;
                o1.c cVar = cVarArr[0];
                o1.c cVar2 = cVar.f20253f;
                if (cVar2 != null && cVarArr[1].f20253f != null) {
                    if (dVar4.y()) {
                        c2079g2.f20663f = this.f20680b.f20273R[0].e();
                        c2079g.f20663f = -this.f20680b.f20273R[1].e();
                        return;
                    }
                    C2079g c2079gH = p.h(this.f20680b.f20273R[0]);
                    if (c2079gH != null) {
                        p.b(c2079g2, c2079gH, this.f20680b.f20273R[0].e());
                    }
                    C2079g c2079gH2 = p.h(this.f20680b.f20273R[1]);
                    if (c2079gH2 != null) {
                        p.b(c2079g, c2079gH2, -this.f20680b.f20273R[1].e());
                    }
                    c2079g2.f20661b = true;
                    c2079g.f20661b = true;
                    return;
                }
                if (cVar2 != null) {
                    C2079g c2079gH3 = p.h(cVar);
                    if (c2079gH3 != null) {
                        p.b(c2079g2, c2079gH3, this.f20680b.f20273R[0].e());
                        p.b(c2079g, c2079g2, c2080h.f20664g);
                        return;
                    }
                    return;
                }
                o1.c cVar3 = cVarArr[1];
                if (cVar3.f20253f != null) {
                    C2079g c2079gH4 = p.h(cVar3);
                    if (c2079gH4 != null) {
                        p.b(c2079g, c2079gH4, -this.f20680b.f20273R[1].e());
                        p.b(c2079g2, c2079g, -c2080h.f20664g);
                        return;
                    }
                    return;
                }
                if ((dVar4 instanceof o1.i) || dVar4.f20276U == null || dVar4.j(7).f20253f != null) {
                    return;
                }
                o1.d dVar5 = this.f20680b;
                p.b(c2079g2, dVar5.f20276U.d.f20684h, dVar5.s());
                p.b(c2079g, c2079g2, c2080h.f20664g);
                return;
            }
        }
        if (this.d == 3) {
            o1.d dVar6 = this.f20680b;
            int i12 = dVar6.f20313s;
            if (i12 == 2) {
                o1.e eVar4 = dVar6.f20276U;
                if (eVar4 != null) {
                    C2080h c2080h2 = eVar4.f20288e.f20681e;
                    c2080h.l.add(c2080h2);
                    c2080h2.f20668k.add(c2080h);
                    c2080h.f20661b = true;
                    c2080h.f20668k.add(c2079g2);
                    c2080h.f20668k.add(c2079g);
                }
            } else if (i12 == 3) {
                if (dVar6.f20314t == 3) {
                    c2079g2.f20660a = this;
                    c2079g.f20660a = this;
                    n nVar = dVar6.f20288e;
                    nVar.f20684h.f20660a = this;
                    nVar.f20685i.f20660a = this;
                    c2080h.f20660a = this;
                    if (dVar6.z()) {
                        c2080h.l.add(this.f20680b.f20288e.f20681e);
                        this.f20680b.f20288e.f20681e.f20668k.add(c2080h);
                        n nVar2 = this.f20680b.f20288e;
                        nVar2.f20681e.f20660a = this;
                        c2080h.l.add(nVar2.f20684h);
                        c2080h.l.add(this.f20680b.f20288e.f20685i);
                        this.f20680b.f20288e.f20684h.f20668k.add(c2080h);
                        this.f20680b.f20288e.f20685i.f20668k.add(c2080h);
                    } else if (this.f20680b.y()) {
                        this.f20680b.f20288e.f20681e.l.add(c2080h);
                        c2080h.f20668k.add(this.f20680b.f20288e.f20681e);
                    } else {
                        this.f20680b.f20288e.f20681e.l.add(c2080h);
                    }
                } else {
                    C2080h c2080h3 = dVar6.f20288e.f20681e;
                    c2080h.l.add(c2080h3);
                    c2080h3.f20668k.add(c2080h);
                    this.f20680b.f20288e.f20684h.f20668k.add(c2080h);
                    this.f20680b.f20288e.f20685i.f20668k.add(c2080h);
                    c2080h.f20661b = true;
                    c2080h.f20668k.add(c2079g2);
                    c2080h.f20668k.add(c2079g);
                    c2079g2.l.add(c2080h);
                    c2079g.l.add(c2080h);
                }
            }
        }
        o1.d dVar7 = this.f20680b;
        o1.c[] cVarArr2 = dVar7.f20273R;
        o1.c cVar4 = cVarArr2[0];
        o1.c cVar5 = cVar4.f20253f;
        if (cVar5 != null && cVarArr2[1].f20253f != null) {
            if (dVar7.y()) {
                c2079g2.f20663f = this.f20680b.f20273R[0].e();
                c2079g.f20663f = -this.f20680b.f20273R[1].e();
                return;
            }
            C2079g c2079gH5 = p.h(this.f20680b.f20273R[0]);
            C2079g c2079gH6 = p.h(this.f20680b.f20273R[1]);
            if (c2079gH5 != null) {
                c2079gH5.b(this);
            }
            if (c2079gH6 != null) {
                c2079gH6.b(this);
            }
            this.f20686j = 4;
            return;
        }
        if (cVar5 != null) {
            C2079g c2079gH7 = p.h(cVar4);
            if (c2079gH7 != null) {
                p.b(c2079g2, c2079gH7, this.f20680b.f20273R[0].e());
                c(c2079g, c2079g2, 1, c2080h);
                return;
            }
            return;
        }
        o1.c cVar6 = cVarArr2[1];
        if (cVar6.f20253f != null) {
            C2079g c2079gH8 = p.h(cVar6);
            if (c2079gH8 != null) {
                p.b(c2079g, c2079gH8, -this.f20680b.f20273R[1].e());
                c(c2079g2, c2079g, -1, c2080h);
                return;
            }
            return;
        }
        if ((dVar7 instanceof o1.i) || (eVar2 = dVar7.f20276U) == null) {
            return;
        }
        p.b(c2079g2, eVar2.d.f20684h, dVar7.s());
        c(c2079g, c2079g2, 1, c2080h);
    }

    @Override // p1.p
    public final void e() {
        C2079g c2079g = this.f20684h;
        if (c2079g.f20667j) {
            this.f20680b.f20281Z = c2079g.f20664g;
        }
    }

    @Override // p1.p
    public final void f() {
        this.c = null;
        this.f20684h.c();
        this.f20685i.c();
        this.f20681e.c();
        this.f20683g = false;
    }

    @Override // p1.p
    public final boolean k() {
        return this.d != 3 || this.f20680b.f20313s == 0;
    }

    public final void n() {
        this.f20683g = false;
        C2079g c2079g = this.f20684h;
        c2079g.c();
        c2079g.f20667j = false;
        C2079g c2079g2 = this.f20685i;
        c2079g2.c();
        c2079g2.f20667j = false;
        this.f20681e.f20667j = false;
    }

    public final String toString() {
        return "HorizontalRun " + this.f20680b.f20297i0;
    }
}
