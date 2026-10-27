package a.f.a.i;

import a.f.a.i.c;
import a.f.a.i.d;

/* JADX INFO: loaded from: classes.dex */
public class a extends h {
    public int x0 = 0;
    public boolean y0 = true;
    public int z0 = 0;
    public boolean A0 = false;

    @Override // a.f.a.i.d
    public boolean D() {
        return this.A0;
    }

    @Override // a.f.a.i.d
    public boolean E() {
        return this.A0;
    }

    public boolean W() {
        int i;
        c cVarI;
        c cVarI2;
        c cVarI3;
        int i2;
        int i3;
        c.a aVar = c.a.BOTTOM;
        c.a aVar2 = c.a.TOP;
        c.a aVar3 = c.a.RIGHT;
        c.a aVar4 = c.a.LEFT;
        int i4 = 0;
        boolean z = true;
        while (true) {
            i = this.w0;
            if (i4 >= i) {
                break;
            }
            d dVar = this.v0[i4];
            if ((this.y0 || dVar.e()) && ((((i2 = this.x0) == 0 || i2 == 1) && !dVar.D()) || (((i3 = this.x0) == 2 || i3 == 3) && !dVar.E()))) {
                z = false;
            }
            i4++;
        }
        if (!z || i <= 0) {
            return false;
        }
        int iMax = 0;
        boolean z2 = false;
        for (int i5 = 0; i5 < this.w0; i5++) {
            d dVar2 = this.v0[i5];
            if (this.y0 || dVar2.e()) {
                if (!z2) {
                    int i6 = this.x0;
                    if (i6 == 0) {
                        cVarI3 = dVar2.i(aVar4);
                    } else if (i6 == 1) {
                        cVarI3 = dVar2.i(aVar3);
                    } else if (i6 == 2) {
                        cVarI3 = dVar2.i(aVar2);
                    } else {
                        if (i6 == 3) {
                            cVarI3 = dVar2.i(aVar);
                        }
                        z2 = true;
                    }
                    iMax = cVarI3.c();
                    z2 = true;
                }
                int i7 = this.x0;
                if (i7 == 0) {
                    cVarI2 = dVar2.i(aVar4);
                } else {
                    if (i7 == 1) {
                        cVarI = dVar2.i(aVar3);
                    } else if (i7 == 2) {
                        cVarI2 = dVar2.i(aVar2);
                    } else if (i7 == 3) {
                        cVarI = dVar2.i(aVar);
                    }
                    iMax = Math.max(iMax, cVarI.c());
                }
                iMax = Math.min(iMax, cVarI2.c());
            }
        }
        int i8 = iMax + this.z0;
        int i9 = this.x0;
        if (i9 == 0 || i9 == 1) {
            L(i8, i8);
        } else {
            M(i8, i8);
        }
        this.A0 = true;
        return true;
    }

    public int X() {
        int i = this.x0;
        if (i == 0 || i == 1) {
            return 0;
        }
        return (i == 2 || i == 3) ? 1 : -1;
    }

    @Override // a.f.a.i.d
    public void d(a.f.a.d dVar, boolean z) {
        c[] cVarArr;
        boolean z2;
        a.f.a.g gVar;
        c cVar;
        int i;
        int i2;
        a.f.a.g gVar2;
        int i3;
        d.a aVar = d.a.MATCH_CONSTRAINT;
        c[] cVarArr2 = this.T;
        cVarArr2[0] = this.L;
        cVarArr2[2] = this.M;
        cVarArr2[1] = this.N;
        cVarArr2[3] = this.O;
        int i4 = 0;
        while (true) {
            cVarArr = this.T;
            if (i4 >= cVarArr.length) {
                break;
            }
            cVarArr[i4].i = dVar.l(cVarArr[i4]);
            i4++;
        }
        int i5 = this.x0;
        if (i5 < 0 || i5 >= 4) {
            return;
        }
        c cVar2 = cVarArr[i5];
        if (!this.A0) {
            W();
        }
        if (this.A0) {
            this.A0 = false;
            int i6 = this.x0;
            if (i6 == 0 || i6 == 1) {
                dVar.e(this.L.i, this.c0);
                gVar2 = this.N.i;
                i3 = this.c0;
            } else {
                if (i6 != 2 && i6 != 3) {
                    return;
                }
                dVar.e(this.M.i, this.d0);
                gVar2 = this.O.i;
                i3 = this.d0;
            }
            dVar.e(gVar2, i3);
            return;
        }
        for (int i7 = 0; i7 < this.w0; i7++) {
            d dVar2 = this.v0[i7];
            if ((this.y0 || dVar2.e()) && ((((i = this.x0) == 0 || i == 1) && dVar2.m() == aVar && dVar2.L.f548f != null && dVar2.N.f548f != null) || (((i2 = this.x0) == 2 || i2 == 3) && dVar2.t() == aVar && dVar2.M.f548f != null && dVar2.O.f548f != null))) {
                z2 = true;
                break;
            }
        }
        z2 = false;
        boolean z3 = this.L.e() || this.N.e();
        boolean z4 = this.M.e() || this.O.e();
        int i8 = !z2 && ((this.x0 == 0 && z3) || ((this.x0 == 2 && z4) || ((this.x0 == 1 && z3) || (this.x0 == 3 && z4)))) ? 5 : 4;
        for (int i9 = 0; i9 < this.w0; i9++) {
            d dVar3 = this.v0[i9];
            if (this.y0 || dVar3.e()) {
                a.f.a.g gVarL = dVar.l(dVar3.T[this.x0]);
                c[] cVarArr3 = dVar3.T;
                int i10 = this.x0;
                cVarArr3[i10].i = gVarL;
                int i11 = (cVarArr3[i10].f548f == null || cVarArr3[i10].f548f.f546d != this) ? 0 : cVarArr3[i10].g + 0;
                int i12 = this.x0;
                if (i12 == 0 || i12 == 2) {
                    a.f.a.g gVar3 = cVar2.i;
                    int i13 = this.z0 - i11;
                    a.f.a.b bVarM = dVar.m();
                    a.f.a.g gVarN = dVar.n();
                    gVarN.f526e = 0;
                    bVarM.f(gVar3, gVarL, gVarN, i13);
                    dVar.c(bVarM);
                } else {
                    a.f.a.g gVar4 = cVar2.i;
                    int i14 = this.z0 + i11;
                    a.f.a.b bVarM2 = dVar.m();
                    a.f.a.g gVarN2 = dVar.n();
                    gVarN2.f526e = 0;
                    bVarM2.e(gVar4, gVarL, gVarN2, i14);
                    dVar.c(bVarM2);
                }
                dVar.d(cVar2.i, gVarL, this.z0 + i11, i8);
            }
        }
        int i15 = this.x0;
        if (i15 == 0) {
            dVar.d(this.N.i, this.L.i, 0, 8);
            dVar.d(this.L.i, this.X.N.i, 0, 4);
            gVar = this.L.i;
            cVar = this.X.L;
        } else if (i15 == 1) {
            dVar.d(this.L.i, this.N.i, 0, 8);
            dVar.d(this.L.i, this.X.L.i, 0, 4);
            gVar = this.L.i;
            cVar = this.X.N;
        } else if (i15 == 2) {
            dVar.d(this.O.i, this.M.i, 0, 8);
            dVar.d(this.M.i, this.X.O.i, 0, 4);
            gVar = this.M.i;
            cVar = this.X.M;
        } else {
            if (i15 != 3) {
                return;
            }
            dVar.d(this.M.i, this.O.i, 0, 8);
            dVar.d(this.M.i, this.X.M.i, 0, 4);
            gVar = this.M.i;
            cVar = this.X.O;
        }
        dVar.d(gVar, cVar.i, 0, 0);
    }

    @Override // a.f.a.i.d
    public boolean e() {
        return true;
    }

    @Override // a.f.a.i.d
    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("[Barrier] ");
        sbD.append(this.m0);
        sbD.append(" {");
        String string = sbD.toString();
        for (int i = 0; i < this.w0; i++) {
            d dVar = this.v0[i];
            if (i > 0) {
                string = b.a.a.a.a.c(string, ", ");
            }
            StringBuilder sbD2 = b.a.a.a.a.d(string);
            sbD2.append(dVar.m0);
            string = sbD2.toString();
        }
        return b.a.a.a.a.c(string, "}");
    }
}
