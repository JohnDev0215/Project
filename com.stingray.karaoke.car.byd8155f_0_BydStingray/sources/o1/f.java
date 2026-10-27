package o1;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f20341a;
    public c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f20343e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f20344f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f20345g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f20346h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f20347i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f20348j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f20349k;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f20353q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ g f20354r;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f20342b = null;
    public int c = 0;
    public int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20350m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20351n = 0;
    public int o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f20352p = 0;

    public f(g gVar, int i7, c cVar, c cVar2, c cVar3, c cVar4, int i10) {
        this.f20354r = gVar;
        this.f20346h = 0;
        this.f20347i = 0;
        this.f20348j = 0;
        this.f20349k = 0;
        this.f20353q = 0;
        this.f20341a = i7;
        this.d = cVar;
        this.f20343e = cVar2;
        this.f20344f = cVar3;
        this.f20345g = cVar4;
        this.f20346h = gVar.f20391v0;
        this.f20347i = gVar.f20389t0;
        this.f20348j = gVar.f20392w0;
        this.f20349k = gVar.f20390u0;
        this.f20353q = i10;
    }

    public final void a(d dVar) {
        int i7 = this.f20341a;
        g gVar = this.f20354r;
        if (i7 == 0) {
            int iA0 = gVar.a0(dVar, this.f20353q);
            if (dVar.f20311q0[0] == 3) {
                this.f20352p++;
                iA0 = 0;
            }
            this.l = iA0 + (dVar.f20295h0 != 8 ? gVar.f20366O0 : 0) + this.l;
            int iZ = gVar.Z(dVar, this.f20353q);
            if (this.f20342b == null || this.c < iZ) {
                this.f20342b = dVar;
                this.c = iZ;
                this.f20350m = iZ;
            }
        } else {
            int iA02 = gVar.a0(dVar, this.f20353q);
            int iZ2 = gVar.Z(dVar, this.f20353q);
            if (dVar.f20311q0[1] == 3) {
                this.f20352p++;
                iZ2 = 0;
            }
            this.f20350m = iZ2 + (dVar.f20295h0 != 8 ? gVar.f20367P0 : 0) + this.f20350m;
            if (this.f20342b == null || this.c < iA02) {
                this.f20342b = dVar;
                this.c = iA02;
                this.l = iA02;
            }
        }
        this.o++;
    }

    public final void b(int i7, boolean z6, boolean z10) {
        g gVar;
        int i10;
        int i11;
        int i12;
        d dVar;
        int i13;
        char c;
        int i14;
        float f10;
        float f11;
        int i15;
        float f12;
        int i16;
        int i17 = this.o;
        int i18 = 0;
        while (true) {
            gVar = this.f20354r;
            if (i18 >= i17 || (i16 = this.f20351n + i18) >= gVar.f20378a1) {
                break;
            }
            d dVar2 = gVar.f20377Z0[i16];
            if (dVar2 != null) {
                dVar2.E();
            }
            i18++;
        }
        if (i17 == 0 || this.f20342b == null) {
            return;
        }
        boolean z11 = z10 && i7 == 0;
        int i19 = -1;
        int i20 = -1;
        for (int i21 = 0; i21 < i17; i21++) {
            int i22 = this.f20351n + (z6 ? (i17 - 1) - i21 : i21);
            if (i22 >= gVar.f20378a1) {
                break;
            }
            d dVar3 = gVar.f20377Z0[i22];
            if (dVar3 != null && dVar3.f20295h0 == 0) {
                if (i19 == -1) {
                    i19 = i21;
                }
                i20 = i21;
            }
        }
        if (this.f20341a != 0) {
            d dVar4 = this.f20342b;
            dVar4.f20299j0 = gVar.f20355C0;
            int i23 = this.f20346h;
            if (i7 > 0) {
                i23 += gVar.f20366O0;
            }
            c cVar = dVar4.f20265J;
            c cVar2 = dVar4.f20267L;
            if (z6) {
                cVar2.a(this.f20344f, i23);
                if (z10) {
                    cVar.a(this.d, this.f20348j);
                }
                if (i7 > 0) {
                    this.f20344f.d.f20265J.a(cVar2, 0);
                }
            } else {
                cVar.a(this.d, i23);
                if (z10) {
                    cVar2.a(this.f20344f, this.f20348j);
                }
                if (i7 > 0) {
                    this.d.d.f20267L.a(cVar, 0);
                }
            }
            d dVar5 = null;
            for (int i24 = 0; i24 < i17; i24++) {
                int i25 = this.f20351n + i24;
                if (i25 >= gVar.f20378a1) {
                    return;
                }
                d dVar6 = gVar.f20377Z0[i25];
                if (dVar6 != null) {
                    c cVar3 = dVar6.f20266K;
                    if (i24 == 0) {
                        dVar6.g(cVar3, this.f20343e, this.f20347i);
                        int i26 = gVar.f20356D0;
                        float f13 = gVar.f20361J0;
                        if (this.f20351n == 0) {
                            i12 = gVar.f20358F0;
                            i10 = i26;
                            i11 = -1;
                            if (i12 != -1) {
                                f13 = gVar.f20363L0;
                            }
                            dVar6.f20301k0 = i12;
                            dVar6.f20291f0 = f13;
                        } else {
                            i10 = i26;
                            i11 = -1;
                        }
                        if (!z10 || (i12 = gVar.f20359H0) == i11) {
                            i12 = i10;
                        } else {
                            f13 = gVar.f20365N0;
                        }
                        dVar6.f20301k0 = i12;
                        dVar6.f20291f0 = f13;
                    }
                    if (i24 == i17 - 1) {
                        dVar6.g(dVar6.f20268M, this.f20345g, this.f20349k);
                    }
                    if (dVar5 != null) {
                        int i27 = gVar.f20367P0;
                        c cVar4 = dVar5.f20268M;
                        cVar3.a(cVar4, i27);
                        if (i24 == i19) {
                            int i28 = this.f20347i;
                            if (cVar3.h()) {
                                cVar3.f20255h = i28;
                            }
                        }
                        cVar4.a(cVar3, 0);
                        if (i24 == i20 + 1) {
                            int i29 = this.f20349k;
                            if (cVar4.h()) {
                                cVar4.f20255h = i29;
                            }
                        }
                    }
                    if (dVar6 != dVar4) {
                        c cVar5 = dVar6.f20267L;
                        c cVar6 = dVar6.f20265J;
                        if (z6) {
                            int i30 = gVar.f20368Q0;
                            if (i30 == 0) {
                                cVar5.a(cVar2, 0);
                            } else if (i30 == 1) {
                                cVar6.a(cVar, 0);
                            } else if (i30 == 2) {
                                cVar6.a(cVar, 0);
                                cVar5.a(cVar2, 0);
                            }
                            dVar5 = dVar6;
                        } else {
                            int i31 = gVar.f20368Q0;
                            if (i31 == 0) {
                                cVar6.a(cVar, 0);
                            } else if (i31 == 1) {
                                cVar5.a(cVar2, 0);
                            } else if (i31 == 2) {
                                if (z11) {
                                    cVar6.a(this.d, this.f20346h);
                                    cVar5.a(this.f20344f, this.f20348j);
                                } else {
                                    cVar6.a(cVar, 0);
                                    cVar5.a(cVar2, 0);
                                }
                            }
                            dVar5 = dVar6;
                        }
                    } else {
                        dVar5 = dVar6;
                    }
                }
            }
            return;
        }
        d dVar7 = this.f20342b;
        dVar7.f20301k0 = gVar.f20356D0;
        int i32 = this.f20347i;
        if (i7 > 0) {
            i32 += gVar.f20367P0;
        }
        c cVar7 = this.f20343e;
        c cVar8 = dVar7.f20266K;
        cVar8.a(cVar7, i32);
        c cVar9 = dVar7.f20268M;
        if (z10) {
            cVar9.a(this.f20345g, this.f20349k);
        }
        if (i7 > 0) {
            this.f20343e.d.f20268M.a(cVar8, 0);
        }
        if (gVar.f20369R0 != 3 || dVar7.f20261F) {
            dVar = dVar7;
        } else {
            for (int i33 = 0; i33 < i17; i33++) {
                int i34 = this.f20351n + (z6 ? (i17 - 1) - i33 : i33);
                if (i34 >= gVar.f20378a1) {
                    break;
                }
                dVar = gVar.f20377Z0[i34];
                if (dVar.f20261F) {
                    break;
                }
            }
            dVar = dVar7;
        }
        int i35 = 0;
        d dVar8 = null;
        while (i35 < i17) {
            int i36 = z6 ? (i17 - 1) - i35 : i35;
            int i37 = this.f20351n + i36;
            if (i37 >= gVar.f20378a1) {
                return;
            }
            d dVar9 = gVar.f20377Z0[i37];
            if (dVar9 == null) {
                i13 = i17;
                c = 3;
            } else {
                c cVar10 = dVar9.f20265J;
                if (i35 == 0) {
                    dVar9.g(cVar10, this.d, this.f20346h);
                }
                if (i36 == 0) {
                    int i38 = gVar.f20355C0;
                    if (z6) {
                        i14 = i38;
                        f10 = 1.0f - gVar.f20360I0;
                    } else {
                        i14 = i38;
                        f10 = gVar.f20360I0;
                    }
                    if (this.f20351n == 0) {
                        int i39 = gVar.f20357E0;
                        f11 = f10;
                        if (i39 != -1) {
                            f12 = z6 ? 1.0f - gVar.f20362K0 : gVar.f20362K0;
                            i15 = i39;
                        }
                        dVar9.f20299j0 = i15;
                        dVar9.f20289e0 = f12;
                    } else {
                        f11 = f10;
                    }
                    if (!z10 || (i15 = gVar.G0) == -1) {
                        i15 = i14;
                        f12 = f11;
                    } else {
                        f12 = z6 ? 1.0f - gVar.f20364M0 : gVar.f20364M0;
                    }
                    dVar9.f20299j0 = i15;
                    dVar9.f20289e0 = f12;
                }
                if (i35 == i17 - 1) {
                    i13 = i17;
                    dVar9.g(dVar9.f20267L, this.f20344f, this.f20348j);
                } else {
                    i13 = i17;
                }
                if (dVar8 != null) {
                    int i40 = gVar.f20366O0;
                    c cVar11 = dVar8.f20267L;
                    cVar10.a(cVar11, i40);
                    if (i35 == i19) {
                        int i41 = this.f20346h;
                        if (cVar10.h()) {
                            cVar10.f20255h = i41;
                        }
                    }
                    cVar11.a(cVar10, 0);
                    if (i35 == i20 + 1) {
                        int i42 = this.f20348j;
                        if (cVar11.h()) {
                            cVar11.f20255h = i42;
                        }
                    }
                }
                if (dVar9 != dVar7) {
                    int i43 = gVar.f20369R0;
                    c = 3;
                    if (i43 == 3 && dVar.f20261F && dVar9 != dVar && dVar9.f20261F) {
                        dVar9.f20269N.a(dVar.f20269N, 0);
                    } else {
                        c cVar12 = dVar9.f20266K;
                        if (i43 != 0) {
                            c cVar13 = dVar9.f20268M;
                            if (i43 == 1) {
                                cVar13.a(cVar9, 0);
                            } else if (z11) {
                                cVar12.a(this.f20343e, this.f20347i);
                                cVar13.a(this.f20345g, this.f20349k);
                            } else {
                                cVar12.a(cVar8, 0);
                                cVar13.a(cVar9, 0);
                            }
                        } else {
                            cVar12.a(cVar8, 0);
                        }
                    }
                } else {
                    c = 3;
                }
                dVar8 = dVar9;
            }
            i35++;
            i17 = i13;
        }
    }

    public final int c() {
        return this.f20341a == 1 ? this.f20350m - this.f20354r.f20367P0 : this.f20350m;
    }

    public final int d() {
        return this.f20341a == 0 ? this.l - this.f20354r.f20366O0 : this.l;
    }

    public final void e(int i7) {
        g gVar;
        int i10;
        int i11 = this.f20352p;
        if (i11 == 0) {
            return;
        }
        int i12 = this.o;
        int i13 = i7 / i11;
        int i14 = 0;
        while (true) {
            gVar = this.f20354r;
            if (i14 >= i12 || (i10 = this.f20351n + i14) >= gVar.f20378a1) {
                break;
            }
            d dVar = gVar.f20377Z0[i10];
            if (this.f20341a == 0) {
                if (dVar != null) {
                    int[] iArr = dVar.f20311q0;
                    if (iArr[0] == 3 && dVar.f20313s == 0) {
                        gVar.Y(1, i13, iArr[1], dVar.l(), dVar);
                    }
                }
            } else if (dVar != null) {
                int[] iArr2 = dVar.f20311q0;
                if (iArr2[1] == 3 && dVar.f20314t == 0) {
                    gVar.Y(iArr2[0], dVar.r(), 1, i13, dVar);
                }
            }
            i14++;
        }
        this.l = 0;
        this.f20350m = 0;
        this.f20342b = null;
        this.c = 0;
        int i15 = this.o;
        for (int i16 = 0; i16 < i15; i16++) {
            int i17 = this.f20351n + i16;
            if (i17 >= gVar.f20378a1) {
                return;
            }
            d dVar2 = gVar.f20377Z0[i17];
            if (this.f20341a == 0) {
                int iR = dVar2.r();
                int i18 = gVar.f20366O0;
                if (dVar2.f20295h0 == 8) {
                    i18 = 0;
                }
                this.l = iR + i18 + this.l;
                int iZ = gVar.Z(dVar2, this.f20353q);
                if (this.f20342b == null || this.c < iZ) {
                    this.f20342b = dVar2;
                    this.c = iZ;
                    this.f20350m = iZ;
                }
            } else {
                int iA0 = gVar.a0(dVar2, this.f20353q);
                int iZ2 = gVar.Z(dVar2, this.f20353q);
                int i19 = gVar.f20367P0;
                if (dVar2.f20295h0 == 8) {
                    i19 = 0;
                }
                this.f20350m = iZ2 + i19 + this.f20350m;
                if (this.f20342b == null || this.c < iA0) {
                    this.f20342b = dVar2;
                    this.c = iA0;
                    this.l = iA0;
                }
            }
        }
    }

    public final void f(int i7, c cVar, c cVar2, c cVar3, c cVar4, int i10, int i11, int i12, int i13, int i14) {
        this.f20341a = i7;
        this.d = cVar;
        this.f20343e = cVar2;
        this.f20344f = cVar3;
        this.f20345g = cVar4;
        this.f20346h = i10;
        this.f20347i = i11;
        this.f20348j = i12;
        this.f20349k = i13;
        this.f20353q = i14;
    }
}
