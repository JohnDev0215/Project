package com.google.zxing.common.l;

import com.google.zxing.NotFoundException;
import com.google.zxing.i;

/* JADX INFO: compiled from: WhiteRectangleDetector.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.zxing.common.b f4227a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4228b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4229c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4230d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f4231e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f4232f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f4233g;

    public b(com.google.zxing.common.b bVar) throws NotFoundException {
        this(bVar, 10, bVar.k() / 2, bVar.h() / 2);
    }

    private i[] a(i iVar, i iVar2, i iVar3, i iVar4) {
        float fC = iVar.c();
        float fD = iVar.d();
        float fC2 = iVar2.c();
        float fD2 = iVar2.d();
        float fC3 = iVar3.c();
        float fD3 = iVar3.d();
        float fC4 = iVar4.c();
        float fD4 = iVar4.d();
        return fC < ((float) this.f4229c) / 2.0f ? new i[]{new i(fC4 - 1.0f, fD4 + 1.0f), new i(fC2 + 1.0f, fD2 + 1.0f), new i(fC3 - 1.0f, fD3 - 1.0f), new i(fC + 1.0f, fD - 1.0f)} : new i[]{new i(fC4 + 1.0f, fD4 + 1.0f), new i(fC2 + 1.0f, fD2 - 1.0f), new i(fC3 - 1.0f, fD3 + 1.0f), new i(fC - 1.0f, fD - 1.0f)};
    }

    private boolean b(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.f4227a.d(i, i3)) {
                    return true;
                }
                i++;
            }
            return false;
        }
        while (i <= i2) {
            if (this.f4227a.d(i3, i)) {
                return true;
            }
            i++;
        }
        return false;
    }

    private i d(float f2, float f3, float f4, float f5) {
        int iC = a.c(a.a(f2, f3, f4, f5));
        float f6 = iC;
        float f7 = (f4 - f2) / f6;
        float f8 = (f5 - f3) / f6;
        for (int i = 0; i < iC; i++) {
            float f9 = i;
            int iC2 = a.c((f9 * f7) + f2);
            int iC3 = a.c((f9 * f8) + f3);
            if (this.f4227a.d(iC2, iC3)) {
                return new i(iC2, iC3);
            }
        }
        return null;
    }

    public i[] c() throws NotFoundException {
        int i = this.f4230d;
        int i2 = this.f4231e;
        int i3 = this.f4233g;
        int i4 = this.f4232f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = true;
        while (z7) {
            boolean z8 = false;
            boolean zB = true;
            while (true) {
                if ((!zB && z2) || i2 >= this.f4229c) {
                    break;
                }
                zB = b(i3, i4, i2, false);
                if (zB) {
                    i2++;
                    z2 = true;
                    z8 = true;
                } else if (!z2) {
                    i2++;
                }
            }
            if (i2 < this.f4229c) {
                boolean zB2 = true;
                while (true) {
                    if ((!zB2 && z3) || i4 >= this.f4228b) {
                        break;
                    }
                    zB2 = b(i, i2, i4, true);
                    if (zB2) {
                        i4++;
                        z3 = true;
                        z8 = true;
                    } else if (!z3) {
                        i4++;
                    }
                }
                if (i4 < this.f4228b) {
                    boolean zB3 = true;
                    while (true) {
                        if ((!zB3 && z4) || i < 0) {
                            break;
                        }
                        zB3 = b(i3, i4, i, false);
                        if (zB3) {
                            i--;
                            z4 = true;
                            z8 = true;
                        } else if (!z4) {
                            i--;
                        }
                    }
                    if (i >= 0) {
                        z7 = z8;
                        boolean zB4 = true;
                        while (true) {
                            if ((!zB4 && z6) || i3 < 0) {
                                break;
                            }
                            zB4 = b(i, i2, i3, true);
                            if (zB4) {
                                i3--;
                                z7 = true;
                                z6 = true;
                            } else if (!z6) {
                                i3--;
                            }
                        }
                        if (i3 >= 0) {
                            if (z7) {
                                z5 = true;
                            }
                        }
                    }
                }
            }
            z = true;
            break;
        }
        if (z || !z5) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i5 = i2 - i;
        i iVarD = null;
        i iVarD2 = null;
        for (int i6 = 1; iVarD2 == null && i6 < i5; i6++) {
            iVarD2 = d(i, i4 - i6, i + i6, i4);
        }
        if (iVarD2 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        i iVarD3 = null;
        for (int i7 = 1; iVarD3 == null && i7 < i5; i7++) {
            iVarD3 = d(i, i3 + i7, i + i7, i3);
        }
        if (iVarD3 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        i iVarD4 = null;
        for (int i8 = 1; iVarD4 == null && i8 < i5; i8++) {
            iVarD4 = d(i2, i3 + i8, i2 - i8, i3);
        }
        if (iVarD4 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        for (int i9 = 1; iVarD == null && i9 < i5; i9++) {
            iVarD = d(i2, i4 - i9, i2 - i9, i4);
        }
        if (iVarD != null) {
            return a(iVarD, iVarD2, iVarD4, iVarD3);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public b(com.google.zxing.common.b bVar, int i, int i2, int i3) throws NotFoundException {
        this.f4227a = bVar;
        int iH = bVar.h();
        this.f4228b = iH;
        int iK = bVar.k();
        this.f4229c = iK;
        int i4 = i / 2;
        int i5 = i2 - i4;
        this.f4230d = i5;
        int i6 = i2 + i4;
        this.f4231e = i6;
        int i7 = i3 - i4;
        this.f4233g = i7;
        int i8 = i3 + i4;
        this.f4232f = i8;
        if (i7 < 0 || i5 < 0 || i8 >= iH || i6 >= iK) {
            throw NotFoundException.getNotFoundInstance();
        }
    }
}
