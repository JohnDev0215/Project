package r;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: Flow.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends m {
    private e[] G1;

    /* JADX INFO: renamed from: j1, reason: collision with root package name */
    private int f9284j1 = -1;

    /* JADX INFO: renamed from: k1, reason: collision with root package name */
    private int f9285k1 = -1;

    /* JADX INFO: renamed from: l1, reason: collision with root package name */
    private int f9286l1 = -1;

    /* JADX INFO: renamed from: m1, reason: collision with root package name */
    private int f9287m1 = -1;

    /* JADX INFO: renamed from: n1, reason: collision with root package name */
    private int f9288n1 = -1;

    /* JADX INFO: renamed from: o1, reason: collision with root package name */
    private int f9289o1 = -1;

    /* JADX INFO: renamed from: p1, reason: collision with root package name */
    private float f9290p1 = 0.5f;

    /* JADX INFO: renamed from: q1, reason: collision with root package name */
    private float f9291q1 = 0.5f;

    /* JADX INFO: renamed from: r1, reason: collision with root package name */
    private float f9292r1 = 0.5f;

    /* JADX INFO: renamed from: s1, reason: collision with root package name */
    private float f9293s1 = 0.5f;

    /* JADX INFO: renamed from: t1, reason: collision with root package name */
    private float f9294t1 = 0.5f;

    /* JADX INFO: renamed from: u1, reason: collision with root package name */
    private float f9295u1 = 0.5f;

    /* JADX INFO: renamed from: v1, reason: collision with root package name */
    private int f9296v1 = 0;

    /* JADX INFO: renamed from: w1, reason: collision with root package name */
    private int f9297w1 = 0;

    /* JADX INFO: renamed from: x1, reason: collision with root package name */
    private int f9298x1 = 2;

    /* JADX INFO: renamed from: y1, reason: collision with root package name */
    private int f9299y1 = 2;

    /* JADX INFO: renamed from: z1, reason: collision with root package name */
    private int f9300z1 = 0;
    private int A1 = -1;
    private int B1 = 0;
    private ArrayList<a> C1 = new ArrayList<>();
    private e[] D1 = null;
    private e[] E1 = null;
    private int[] F1 = null;
    private int H1 = 0;

    /* JADX INFO: compiled from: Flow.java */
    private class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f9301a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private e f9302b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private d f9303c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private d f9304d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private d f9305e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private d f9306f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f9307g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f9308h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f9309i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private int f9310j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private int f9311k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private int f9312l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        final /* synthetic */ g f9313m;

        public void a(boolean z6, int i7, boolean z7) {
            e eVar;
            char c7;
            float f7;
            float f8;
            int i8 = this.f9312l;
            for (int i9 = 0; i9 < i8 && this.f9311k + i9 < this.f9313m.H1; i9++) {
                e eVar2 = this.f9313m.G1[this.f9311k + i9];
                if (eVar2 != null) {
                    eVar2.x0();
                }
            }
            if (i8 == 0 || this.f9302b == null) {
                return;
            }
            boolean z8 = z7 && i7 == 0;
            int i10 = -1;
            int i11 = -1;
            for (int i12 = 0; i12 < i8; i12++) {
                int i13 = z6 ? (i8 - 1) - i12 : i12;
                if (this.f9311k + i13 >= this.f9313m.H1) {
                    break;
                }
                e eVar3 = this.f9313m.G1[this.f9311k + i13];
                if (eVar3 != null && eVar3.X() == 0) {
                    if (i10 == -1) {
                        i10 = i12;
                    }
                    i11 = i12;
                }
            }
            e eVar4 = null;
            if (this.f9301a != 0) {
                e eVar5 = this.f9302b;
                eVar5.Q0(this.f9313m.f9284j1);
                int i14 = this.f9307g;
                if (i7 > 0) {
                    i14 += this.f9313m.f9296v1;
                }
                if (z6) {
                    eVar5.S.a(this.f9305e, i14);
                    if (z7) {
                        eVar5.Q.a(this.f9303c, this.f9309i);
                    }
                    if (i7 > 0) {
                        this.f9305e.f9196d.Q.a(eVar5.S, 0);
                    }
                } else {
                    eVar5.Q.a(this.f9303c, i14);
                    if (z7) {
                        eVar5.S.a(this.f9305e, this.f9309i);
                    }
                    if (i7 > 0) {
                        this.f9303c.f9196d.S.a(eVar5.Q, 0);
                    }
                }
                for (int i15 = 0; i15 < i8 && this.f9311k + i15 < this.f9313m.H1; i15++) {
                    e eVar6 = this.f9313m.G1[this.f9311k + i15];
                    if (eVar6 != null) {
                        if (i15 == 0) {
                            eVar6.l(eVar6.R, this.f9304d, this.f9308h);
                            int i16 = this.f9313m.f9285k1;
                            float f9 = this.f9313m.f9291q1;
                            if (this.f9311k == 0 && this.f9313m.f9287m1 != -1) {
                                i16 = this.f9313m.f9287m1;
                                f9 = this.f9313m.f9293s1;
                            } else if (z7 && this.f9313m.f9289o1 != -1) {
                                i16 = this.f9313m.f9289o1;
                                f9 = this.f9313m.f9295u1;
                            }
                            eVar6.h1(i16);
                            eVar6.g1(f9);
                        }
                        if (i15 == i8 - 1) {
                            eVar6.l(eVar6.T, this.f9306f, this.f9310j);
                        }
                        if (eVar4 != null) {
                            eVar6.R.a(eVar4.T, this.f9313m.f9297w1);
                            if (i15 == i10) {
                                eVar6.R.u(this.f9308h);
                            }
                            eVar4.T.a(eVar6.R, 0);
                            if (i15 == i11 + 1) {
                                eVar4.T.u(this.f9310j);
                            }
                        }
                        if (eVar6 == eVar5) {
                            eVar4 = eVar6;
                        } else if (z6) {
                            int i17 = this.f9313m.f9298x1;
                            if (i17 == 0) {
                                eVar6.S.a(eVar5.S, 0);
                            } else if (i17 == 1) {
                                eVar6.Q.a(eVar5.Q, 0);
                            } else if (i17 == 2) {
                                eVar6.Q.a(eVar5.Q, 0);
                                eVar6.S.a(eVar5.S, 0);
                            }
                            eVar4 = eVar6;
                        } else {
                            int i18 = this.f9313m.f9298x1;
                            if (i18 == 0) {
                                eVar6.Q.a(eVar5.Q, 0);
                            } else if (i18 == 1) {
                                eVar6.S.a(eVar5.S, 0);
                            } else if (i18 == 2) {
                                if (z8) {
                                    eVar6.Q.a(this.f9303c, this.f9307g);
                                    eVar6.S.a(this.f9305e, this.f9309i);
                                } else {
                                    eVar6.Q.a(eVar5.Q, 0);
                                    eVar6.S.a(eVar5.S, 0);
                                }
                            }
                            eVar4 = eVar6;
                        }
                    }
                }
                return;
            }
            e eVar7 = this.f9302b;
            eVar7.h1(this.f9313m.f9285k1);
            int i19 = this.f9308h;
            if (i7 > 0) {
                i19 += this.f9313m.f9297w1;
            }
            eVar7.R.a(this.f9304d, i19);
            if (z7) {
                eVar7.T.a(this.f9306f, this.f9310j);
            }
            if (i7 > 0) {
                this.f9304d.f9196d.T.a(eVar7.R, 0);
            }
            char c8 = 3;
            if (this.f9313m.f9299y1 != 3 || eVar7.b0()) {
                eVar = eVar7;
            } else {
                for (int i20 = 0; i20 < i8; i20++) {
                    int i21 = z6 ? (i8 - 1) - i20 : i20;
                    if (this.f9311k + i21 >= this.f9313m.H1) {
                        break;
                    }
                    eVar = this.f9313m.G1[this.f9311k + i21];
                    if (eVar.b0()) {
                        break;
                    }
                }
                eVar = eVar7;
            }
            int i22 = 0;
            while (i22 < i8) {
                int i23 = z6 ? (i8 - 1) - i22 : i22;
                if (this.f9311k + i23 >= this.f9313m.H1) {
                    return;
                }
                e eVar8 = this.f9313m.G1[this.f9311k + i23];
                if (eVar8 == null) {
                    eVar8 = eVar4;
                    c7 = c8;
                } else {
                    if (i22 == 0) {
                        eVar8.l(eVar8.Q, this.f9303c, this.f9307g);
                    }
                    if (i23 == 0) {
                        int i24 = this.f9313m.f9284j1;
                        float f10 = this.f9313m.f9290p1;
                        if (z6) {
                            f10 = 1.0f - f10;
                        }
                        if (this.f9311k != 0 || this.f9313m.f9286l1 == -1) {
                            if (z7 && this.f9313m.f9288n1 != -1) {
                                i24 = this.f9313m.f9288n1;
                                if (z6) {
                                    f8 = this.f9313m.f9294t1;
                                    f7 = 1.0f - f8;
                                    f10 = f7;
                                } else {
                                    f7 = this.f9313m.f9294t1;
                                    f10 = f7;
                                }
                            }
                            eVar8.Q0(i24);
                            eVar8.P0(f10);
                        } else {
                            i24 = this.f9313m.f9286l1;
                            if (z6) {
                                f8 = this.f9313m.f9292r1;
                                f7 = 1.0f - f8;
                                f10 = f7;
                                eVar8.Q0(i24);
                                eVar8.P0(f10);
                            } else {
                                f7 = this.f9313m.f9292r1;
                                f10 = f7;
                                eVar8.Q0(i24);
                                eVar8.P0(f10);
                            }
                        }
                    }
                    if (i22 == i8 - 1) {
                        eVar8.l(eVar8.S, this.f9305e, this.f9309i);
                    }
                    if (eVar4 != null) {
                        eVar8.Q.a(eVar4.S, this.f9313m.f9296v1);
                        if (i22 == i10) {
                            eVar8.Q.u(this.f9307g);
                        }
                        eVar4.S.a(eVar8.Q, 0);
                        if (i22 == i11 + 1) {
                            eVar4.S.u(this.f9309i);
                        }
                    }
                    if (eVar8 != eVar7) {
                        c7 = 3;
                        if (this.f9313m.f9299y1 == 3 && eVar.b0() && eVar8 != eVar && eVar8.b0()) {
                            eVar8.U.a(eVar.U, 0);
                        } else {
                            int i25 = this.f9313m.f9299y1;
                            if (i25 == 0) {
                                eVar8.R.a(eVar7.R, 0);
                            } else if (i25 == 1) {
                                eVar8.T.a(eVar7.T, 0);
                            } else if (z8) {
                                eVar8.R.a(this.f9304d, this.f9308h);
                                eVar8.T.a(this.f9306f, this.f9310j);
                            } else {
                                eVar8.R.a(eVar7.R, 0);
                                eVar8.T.a(eVar7.T, 0);
                            }
                        }
                    } else {
                        c7 = 3;
                    }
                }
                i22++;
                c8 = c7;
                eVar4 = eVar8;
            }
        }
    }

    private void W1(boolean z6) {
        e eVar;
        float f7;
        int i7;
        if (this.F1 == null || this.E1 == null || this.D1 == null) {
            return;
        }
        for (int i8 = 0; i8 < this.H1; i8++) {
            this.G1[i8].x0();
        }
        int[] iArr = this.F1;
        int i9 = iArr[0];
        int i10 = iArr[1];
        e eVar2 = null;
        float f8 = this.f9290p1;
        int i11 = 0;
        while (i11 < i9) {
            if (z6) {
                i7 = (i9 - i11) - 1;
                f7 = 1.0f - this.f9290p1;
            } else {
                f7 = f8;
                i7 = i11;
            }
            e eVar3 = this.E1[i7];
            if (eVar3 != null && eVar3.X() != 8) {
                if (i11 == 0) {
                    eVar3.l(eVar3.Q, this.Q, z1());
                    eVar3.Q0(this.f9284j1);
                    eVar3.P0(f7);
                }
                if (i11 == i9 - 1) {
                    eVar3.l(eVar3.S, this.S, A1());
                }
                if (i11 > 0 && eVar2 != null) {
                    eVar3.l(eVar3.Q, eVar2.S, this.f9296v1);
                    eVar2.l(eVar2.S, eVar3.Q, 0);
                }
                eVar2 = eVar3;
            }
            i11++;
            f8 = f7;
        }
        for (int i12 = 0; i12 < i10; i12++) {
            e eVar4 = this.D1[i12];
            if (eVar4 != null && eVar4.X() != 8) {
                if (i12 == 0) {
                    eVar4.l(eVar4.R, this.R, B1());
                    eVar4.h1(this.f9285k1);
                    eVar4.g1(this.f9291q1);
                }
                if (i12 == i10 - 1) {
                    eVar4.l(eVar4.T, this.T, y1());
                }
                if (i12 > 0 && eVar2 != null) {
                    eVar4.l(eVar4.R, eVar2.T, this.f9297w1);
                    eVar2.l(eVar2.T, eVar4.R, 0);
                }
                eVar2 = eVar4;
            }
        }
        for (int i13 = 0; i13 < i9; i13++) {
            for (int i14 = 0; i14 < i10; i14++) {
                int i15 = (i14 * i9) + i13;
                if (this.B1 == 1) {
                    i15 = (i13 * i10) + i14;
                }
                e[] eVarArr = this.G1;
                if (i15 < eVarArr.length && (eVar = eVarArr[i15]) != null && eVar.X() != 8) {
                    e eVar5 = this.E1[i13];
                    e eVar6 = this.D1[i14];
                    if (eVar != eVar5) {
                        eVar.l(eVar.Q, eVar5.Q, 0);
                        eVar.l(eVar.S, eVar5.S, 0);
                    }
                    if (eVar != eVar6) {
                        eVar.l(eVar.R, eVar6.R, 0);
                        eVar.l(eVar.T, eVar6.T, 0);
                    }
                }
            }
        }
    }

    @Override // r.e
    public void g(o.d dVar, boolean z6) {
        super.g(dVar, z6);
        boolean z7 = M() != null && ((f) M()).S1();
        int i7 = this.f9300z1;
        if (i7 != 0) {
            if (i7 == 1) {
                int size = this.C1.size();
                int i8 = 0;
                while (i8 < size) {
                    this.C1.get(i8).a(z7, i8, i8 == size + (-1));
                    i8++;
                }
            } else if (i7 == 2) {
                W1(z7);
            } else if (i7 == 3) {
                int size2 = this.C1.size();
                int i9 = 0;
                while (i9 < size2) {
                    this.C1.get(i9).a(z7, i9, i9 == size2 + (-1));
                    i9++;
                }
            }
        } else if (this.C1.size() > 0) {
            this.C1.get(0).a(z7, 0, true);
        }
        D1(false);
    }

    @Override // r.j, r.e
    public void n(e eVar, HashMap<e, e> map) {
        super.n(eVar, map);
        g gVar = (g) eVar;
        this.f9284j1 = gVar.f9284j1;
        this.f9285k1 = gVar.f9285k1;
        this.f9286l1 = gVar.f9286l1;
        this.f9287m1 = gVar.f9287m1;
        this.f9288n1 = gVar.f9288n1;
        this.f9289o1 = gVar.f9289o1;
        this.f9290p1 = gVar.f9290p1;
        this.f9291q1 = gVar.f9291q1;
        this.f9292r1 = gVar.f9292r1;
        this.f9293s1 = gVar.f9293s1;
        this.f9294t1 = gVar.f9294t1;
        this.f9295u1 = gVar.f9295u1;
        this.f9296v1 = gVar.f9296v1;
        this.f9297w1 = gVar.f9297w1;
        this.f9298x1 = gVar.f9298x1;
        this.f9299y1 = gVar.f9299y1;
        this.f9300z1 = gVar.f9300z1;
        this.A1 = gVar.A1;
        this.B1 = gVar.B1;
    }
}
