package p1;

import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class n extends p {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public C2079g f20674k;
    public C2073a l;

    @Override // p1.InterfaceC2077e
    public final void a(InterfaceC2077e interfaceC2077e) {
        float f10;
        float f11;
        float f12;
        int i7;
        if (AbstractC2641j.c(this.f20686j) == 3) {
            o1.d dVar = this.f20680b;
            l(dVar.f20266K, dVar.f20268M, 1);
            return;
        }
        C2080h c2080h = this.f20681e;
        if (c2080h.c && !c2080h.f20667j && this.d == 3) {
            o1.d dVar2 = this.f20680b;
            int i10 = dVar2.f20314t;
            if (i10 == 2) {
                o1.e eVar = dVar2.f20276U;
                if (eVar != null) {
                    if (eVar.f20288e.f20681e.f20667j) {
                        c2080h.d((int) ((r5.f20664g * dVar2.f20257A) + 0.5f));
                    }
                }
            } else if (i10 == 3) {
                C2080h c2080h2 = dVar2.d.f20681e;
                if (c2080h2.f20667j) {
                    int i11 = dVar2.f20280Y;
                    if (i11 == -1) {
                        f10 = c2080h2.f20664g;
                        f11 = dVar2.f20279X;
                    } else if (i11 == 0) {
                        f12 = c2080h2.f20664g * dVar2.f20279X;
                        i7 = (int) (f12 + 0.5f);
                        c2080h.d(i7);
                    } else if (i11 != 1) {
                        i7 = 0;
                        c2080h.d(i7);
                    } else {
                        f10 = c2080h2.f20664g;
                        f11 = dVar2.f20279X;
                    }
                    f12 = f10 / f11;
                    i7 = (int) (f12 + 0.5f);
                    c2080h.d(i7);
                }
            }
        }
        C2079g c2079g = this.f20684h;
        if (c2079g.c) {
            C2079g c2079g2 = this.f20685i;
            if (c2079g2.c) {
                if (c2079g.f20667j && c2079g2.f20667j && c2080h.f20667j) {
                    return;
                }
                if (!c2080h.f20667j && this.d == 3) {
                    o1.d dVar3 = this.f20680b;
                    if (dVar3.f20313s == 0 && !dVar3.z()) {
                        C2079g c2079g3 = (C2079g) c2079g.l.get(0);
                        C2079g c2079g4 = (C2079g) c2079g2.l.get(0);
                        int i12 = c2079g3.f20664g + c2079g.f20663f;
                        int i13 = c2079g4.f20664g + c2079g2.f20663f;
                        c2079g.d(i12);
                        c2079g2.d(i13);
                        c2080h.d(i13 - i12);
                        return;
                    }
                }
                if (!c2080h.f20667j && this.d == 3 && this.f20679a == 1 && c2079g.l.size() > 0 && c2079g2.l.size() > 0) {
                    C2079g c2079g5 = (C2079g) c2079g.l.get(0);
                    int i14 = (((C2079g) c2079g2.l.get(0)).f20664g + c2079g2.f20663f) - (c2079g5.f20664g + c2079g.f20663f);
                    int i15 = c2080h.f20669m;
                    if (i14 < i15) {
                        c2080h.d(i14);
                    } else {
                        c2080h.d(i15);
                    }
                }
                if (c2080h.f20667j && c2079g.l.size() > 0 && c2079g2.l.size() > 0) {
                    C2079g c2079g6 = (C2079g) c2079g.l.get(0);
                    C2079g c2079g7 = (C2079g) c2079g2.l.get(0);
                    int i16 = c2079g6.f20664g;
                    int i17 = c2079g.f20663f + i16;
                    int i18 = c2079g7.f20664g;
                    int i19 = c2079g2.f20663f + i18;
                    float f13 = this.f20680b.f20291f0;
                    if (c2079g6 == c2079g7) {
                        f13 = 0.5f;
                    } else {
                        i16 = i17;
                        i18 = i19;
                    }
                    c2079g.d((int) ((((i18 - i16) - c2080h.f20664g) * f13) + i16 + 0.5f));
                    c2079g2.d(c2079g.f20664g + c2080h.f20664g);
                }
            }
        }
    }

    @Override // p1.p
    public final void d() {
        o1.d dVar;
        o1.e eVar;
        o1.e eVar2;
        o1.e eVar3;
        o1.d dVar2 = this.f20680b;
        boolean z6 = dVar2.f20282a;
        C2080h c2080h = this.f20681e;
        if (z6) {
            c2080h.d(dVar2.l());
        }
        boolean z10 = c2080h.f20667j;
        C2079g c2079g = this.f20685i;
        C2079g c2079g2 = this.f20684h;
        if (!z10) {
            o1.d dVar3 = this.f20680b;
            this.d = dVar3.f20311q0[1];
            if (dVar3.f20261F) {
                this.l = new C2073a(this);
            }
            int i7 = this.d;
            if (i7 != 3) {
                if (i7 == 4 && (eVar3 = this.f20680b.f20276U) != null && eVar3.f20311q0[1] == 1) {
                    int iL = (eVar3.l() - this.f20680b.f20266K.e()) - this.f20680b.f20268M.e();
                    p.b(c2079g2, eVar3.f20288e.f20684h, this.f20680b.f20266K.e());
                    p.b(c2079g, eVar3.f20288e.f20685i, -this.f20680b.f20268M.e());
                    c2080h.d(iL);
                    return;
                }
                if (i7 == 1) {
                    c2080h.d(this.f20680b.l());
                }
            }
        } else if (this.d == 4 && (eVar = (dVar = this.f20680b).f20276U) != null && eVar.f20311q0[1] == 1) {
            p.b(c2079g2, eVar.f20288e.f20684h, dVar.f20266K.e());
            p.b(c2079g, eVar.f20288e.f20685i, -this.f20680b.f20268M.e());
            return;
        }
        boolean z11 = c2080h.f20667j;
        C2079g c2079g3 = this.f20674k;
        if (z11) {
            o1.d dVar4 = this.f20680b;
            if (dVar4.f20282a) {
                o1.c[] cVarArr = dVar4.f20273R;
                o1.c cVar = cVarArr[2];
                o1.c cVar2 = cVar.f20253f;
                if (cVar2 != null && cVarArr[3].f20253f != null) {
                    if (dVar4.z()) {
                        c2079g2.f20663f = this.f20680b.f20273R[2].e();
                        c2079g.f20663f = -this.f20680b.f20273R[3].e();
                    } else {
                        C2079g c2079gH = p.h(this.f20680b.f20273R[2]);
                        if (c2079gH != null) {
                            p.b(c2079g2, c2079gH, this.f20680b.f20273R[2].e());
                        }
                        C2079g c2079gH2 = p.h(this.f20680b.f20273R[3]);
                        if (c2079gH2 != null) {
                            p.b(c2079g, c2079gH2, -this.f20680b.f20273R[3].e());
                        }
                        c2079g2.f20661b = true;
                        c2079g.f20661b = true;
                    }
                    o1.d dVar5 = this.f20680b;
                    if (dVar5.f20261F) {
                        p.b(c2079g3, c2079g2, dVar5.f20285b0);
                        return;
                    }
                    return;
                }
                if (cVar2 != null) {
                    C2079g c2079gH3 = p.h(cVar);
                    if (c2079gH3 != null) {
                        p.b(c2079g2, c2079gH3, this.f20680b.f20273R[2].e());
                        p.b(c2079g, c2079g2, c2080h.f20664g);
                        o1.d dVar6 = this.f20680b;
                        if (dVar6.f20261F) {
                            p.b(c2079g3, c2079g2, dVar6.f20285b0);
                            return;
                        }
                        return;
                    }
                    return;
                }
                o1.c cVar3 = cVarArr[3];
                if (cVar3.f20253f != null) {
                    C2079g c2079gH4 = p.h(cVar3);
                    if (c2079gH4 != null) {
                        p.b(c2079g, c2079gH4, -this.f20680b.f20273R[3].e());
                        p.b(c2079g2, c2079g, -c2080h.f20664g);
                    }
                    o1.d dVar7 = this.f20680b;
                    if (dVar7.f20261F) {
                        p.b(c2079g3, c2079g2, dVar7.f20285b0);
                        return;
                    }
                    return;
                }
                o1.c cVar4 = cVarArr[4];
                if (cVar4.f20253f != null) {
                    C2079g c2079gH5 = p.h(cVar4);
                    if (c2079gH5 != null) {
                        p.b(c2079g3, c2079gH5, 0);
                        p.b(c2079g2, c2079g3, -this.f20680b.f20285b0);
                        p.b(c2079g, c2079g2, c2080h.f20664g);
                        return;
                    }
                    return;
                }
                if ((dVar4 instanceof o1.i) || dVar4.f20276U == null || dVar4.j(7).f20253f != null) {
                    return;
                }
                o1.d dVar8 = this.f20680b;
                p.b(c2079g2, dVar8.f20276U.f20288e.f20684h, dVar8.t());
                p.b(c2079g, c2079g2, c2080h.f20664g);
                o1.d dVar9 = this.f20680b;
                if (dVar9.f20261F) {
                    p.b(c2079g3, c2079g2, dVar9.f20285b0);
                    return;
                }
                return;
            }
        }
        if (z11 || this.d != 3) {
            c2080h.b(this);
        } else {
            o1.d dVar10 = this.f20680b;
            int i10 = dVar10.f20314t;
            if (i10 == 2) {
                o1.e eVar4 = dVar10.f20276U;
                if (eVar4 != null) {
                    C2080h c2080h2 = eVar4.f20288e.f20681e;
                    c2080h.l.add(c2080h2);
                    c2080h2.f20668k.add(c2080h);
                    c2080h.f20661b = true;
                    c2080h.f20668k.add(c2079g2);
                    c2080h.f20668k.add(c2079g);
                }
            } else if (i10 == 3 && !dVar10.z()) {
                o1.d dVar11 = this.f20680b;
                if (dVar11.f20313s != 3) {
                    C2080h c2080h3 = dVar11.d.f20681e;
                    c2080h.l.add(c2080h3);
                    c2080h3.f20668k.add(c2080h);
                    c2080h.f20661b = true;
                    c2080h.f20668k.add(c2079g2);
                    c2080h.f20668k.add(c2079g);
                }
            }
        }
        o1.d dVar12 = this.f20680b;
        o1.c[] cVarArr2 = dVar12.f20273R;
        o1.c cVar5 = cVarArr2[2];
        o1.c cVar6 = cVar5.f20253f;
        if (cVar6 != null && cVarArr2[3].f20253f != null) {
            if (dVar12.z()) {
                c2079g2.f20663f = this.f20680b.f20273R[2].e();
                c2079g.f20663f = -this.f20680b.f20273R[3].e();
            } else {
                C2079g c2079gH6 = p.h(this.f20680b.f20273R[2]);
                C2079g c2079gH7 = p.h(this.f20680b.f20273R[3]);
                if (c2079gH6 != null) {
                    c2079gH6.b(this);
                }
                if (c2079gH7 != null) {
                    c2079gH7.b(this);
                }
                this.f20686j = 4;
            }
            if (this.f20680b.f20261F) {
                c(c2079g3, c2079g2, 1, this.l);
            }
        } else if (cVar6 != null) {
            C2079g c2079gH8 = p.h(cVar5);
            if (c2079gH8 != null) {
                p.b(c2079g2, c2079gH8, this.f20680b.f20273R[2].e());
                c(c2079g, c2079g2, 1, c2080h);
                if (this.f20680b.f20261F) {
                    c(c2079g3, c2079g2, 1, this.l);
                }
                if (this.d == 3) {
                    o1.d dVar13 = this.f20680b;
                    if (dVar13.f20279X > 0.0f) {
                        l lVar = dVar13.d;
                        if (lVar.d == 3) {
                            lVar.f20681e.f20668k.add(c2080h);
                            c2080h.l.add(this.f20680b.d.f20681e);
                            c2080h.f20660a = this;
                        }
                    }
                }
            }
        } else {
            o1.c cVar7 = cVarArr2[3];
            if (cVar7.f20253f != null) {
                C2079g c2079gH9 = p.h(cVar7);
                if (c2079gH9 != null) {
                    p.b(c2079g, c2079gH9, -this.f20680b.f20273R[3].e());
                    c(c2079g2, c2079g, -1, c2080h);
                    if (this.f20680b.f20261F) {
                        c(c2079g3, c2079g2, 1, this.l);
                    }
                }
            } else {
                o1.c cVar8 = cVarArr2[4];
                if (cVar8.f20253f != null) {
                    C2079g c2079gH10 = p.h(cVar8);
                    if (c2079gH10 != null) {
                        p.b(c2079g3, c2079gH10, 0);
                        c(c2079g2, c2079g3, -1, this.l);
                        c(c2079g, c2079g2, 1, c2080h);
                    }
                } else if (!(dVar12 instanceof o1.i) && (eVar2 = dVar12.f20276U) != null) {
                    p.b(c2079g2, eVar2.f20288e.f20684h, dVar12.t());
                    c(c2079g, c2079g2, 1, c2080h);
                    if (this.f20680b.f20261F) {
                        c(c2079g3, c2079g2, 1, this.l);
                    }
                    if (this.d == 3) {
                        o1.d dVar14 = this.f20680b;
                        if (dVar14.f20279X > 0.0f) {
                            l lVar2 = dVar14.d;
                            if (lVar2.d == 3) {
                                lVar2.f20681e.f20668k.add(c2080h);
                                c2080h.l.add(this.f20680b.d.f20681e);
                                c2080h.f20660a = this;
                            }
                        }
                    }
                }
            }
        }
        if (c2080h.l.size() == 0) {
            c2080h.c = true;
        }
    }

    @Override // p1.p
    public final void e() {
        C2079g c2079g = this.f20684h;
        if (c2079g.f20667j) {
            this.f20680b.f20283a0 = c2079g.f20664g;
        }
    }

    @Override // p1.p
    public final void f() {
        this.c = null;
        this.f20684h.c();
        this.f20685i.c();
        this.f20674k.c();
        this.f20681e.c();
        this.f20683g = false;
    }

    @Override // p1.p
    public final boolean k() {
        return this.d != 3 || this.f20680b.f20314t == 0;
    }

    public final void m() {
        this.f20683g = false;
        C2079g c2079g = this.f20684h;
        c2079g.c();
        c2079g.f20667j = false;
        C2079g c2079g2 = this.f20685i;
        c2079g2.c();
        c2079g2.f20667j = false;
        C2079g c2079g3 = this.f20674k;
        c2079g3.c();
        c2079g3.f20667j = false;
        this.f20681e.f20667j = false;
    }

    public final String toString() {
        return "VerticalRun " + this.f20680b.f20297i0;
    }
}
