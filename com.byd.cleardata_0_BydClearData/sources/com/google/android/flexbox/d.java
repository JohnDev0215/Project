package com.google.android.flexbox;

import android.view.View;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.android.flexbox.a f2938a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean[] f2939b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int[] f2940c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    long[] f2941d;
    private long[] e;

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        List<c> f2942a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2943b;

        a() {
        }

        void a() {
            this.f2942a = null;
            this.f2943b = 0;
        }
    }

    d(com.google.android.flexbox.a aVar) {
        this.f2938a = aVar;
    }

    private int A(boolean z) {
        return z ? this.f2938a.f() : this.f2938a.e();
    }

    private int B(boolean z) {
        return z ? this.f2938a.j() : this.f2938a.m();
    }

    private int C(boolean z) {
        return z ? this.f2938a.m() : this.f2938a.j();
    }

    private int D(View view, boolean z) {
        return z ? view.getMeasuredHeight() : view.getMeasuredWidth();
    }

    private int E(View view, boolean z) {
        return z ? view.getMeasuredWidth() : view.getMeasuredHeight();
    }

    private boolean F(int i, int i2, c cVar) {
        return i == i2 - 1 && cVar.c() != 0;
    }

    private boolean G(View view, int i, int i2, int i3, int i4, b bVar, int i5, int i6, int i7) {
        if (this.f2938a.d() == 0) {
            return false;
        }
        if (bVar.e()) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        int iS = this.f2938a.s();
        if (iS != -1 && iS <= i7 + 1) {
            return false;
        }
        int iC = this.f2938a.c(view, i5, i6);
        if (iC > 0) {
            i4 += iC;
        }
        return i2 < i3 + i4;
    }

    private void K(int i, int i2, c cVar, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int iMax;
        int i7 = cVar.e;
        float f = cVar.k;
        float f2 = 0.0f;
        if (f <= 0.0f || i3 > i7) {
            return;
        }
        float f3 = (i7 - i3) / f;
        cVar.e = i4 + cVar.f;
        if (!z) {
            cVar.g = Integer.MIN_VALUE;
        }
        int i8 = 0;
        boolean z2 = false;
        int i9 = 0;
        float f4 = 0.0f;
        while (i8 < cVar.h) {
            int i10 = cVar.o + i8;
            View viewO = this.f2938a.o(i10);
            if (viewO == null || viewO.getVisibility() == 8) {
                i5 = i7;
                i6 = i8;
            } else {
                b bVar = (b) viewO.getLayoutParams();
                int iR = this.f2938a.r();
                if (iR == 0 || iR == 1) {
                    i5 = i7;
                    int i11 = i8;
                    int measuredWidth = viewO.getMeasuredWidth();
                    long[] jArr = this.e;
                    if (jArr != null) {
                        measuredWidth = q(jArr[i10]);
                    }
                    int measuredHeight = viewO.getMeasuredHeight();
                    long[] jArr2 = this.e;
                    if (jArr2 != null) {
                        measuredHeight = p(jArr2[i10]);
                    }
                    if (this.f2939b[i10] || bVar.q() <= 0.0f) {
                        i6 = i11;
                    } else {
                        float fQ = measuredWidth - (bVar.q() * f3);
                        i6 = i11;
                        if (i6 == cVar.h - 1) {
                            fQ += f4;
                            f4 = 0.0f;
                        }
                        int iRound = Math.round(fQ);
                        if (iRound < bVar.d()) {
                            iRound = bVar.d();
                            this.f2939b[i10] = true;
                            cVar.k -= bVar.q();
                            z2 = true;
                        } else {
                            f4 += fQ - iRound;
                            double d2 = f4;
                            if (d2 > 1.0d) {
                                iRound++;
                                f4 -= 1.0f;
                            } else if (d2 < -1.0d) {
                                iRound--;
                                f4 += 1.0f;
                            }
                        }
                        int iR2 = r(i2, bVar, cVar.m);
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iRound, 1073741824);
                        viewO.measure(iMakeMeasureSpec, iR2);
                        int measuredWidth2 = viewO.getMeasuredWidth();
                        int measuredHeight2 = viewO.getMeasuredHeight();
                        P(i10, iMakeMeasureSpec, iR2, viewO);
                        this.f2938a.u(i10, viewO);
                        measuredWidth = measuredWidth2;
                        measuredHeight = measuredHeight2;
                    }
                    int iMax2 = Math.max(i9, measuredHeight + bVar.t() + bVar.l() + this.f2938a.w(viewO));
                    cVar.e += measuredWidth + bVar.n() + bVar.a();
                    iMax = iMax2;
                } else {
                    int measuredHeight3 = viewO.getMeasuredHeight();
                    long[] jArr3 = this.e;
                    if (jArr3 != null) {
                        measuredHeight3 = p(jArr3[i10]);
                    }
                    int measuredWidth3 = viewO.getMeasuredWidth();
                    long[] jArr4 = this.e;
                    if (jArr4 != null) {
                        measuredWidth3 = q(jArr4[i10]);
                    }
                    if (this.f2939b[i10] || bVar.q() <= f2) {
                        i5 = i7;
                        i6 = i8;
                    } else {
                        float fQ2 = measuredHeight3 - (bVar.q() * f3);
                        if (i8 == cVar.h - 1) {
                            fQ2 += f4;
                            f4 = f2;
                        }
                        int iRound2 = Math.round(fQ2);
                        if (iRound2 < bVar.c()) {
                            iRound2 = bVar.c();
                            this.f2939b[i10] = true;
                            cVar.k -= bVar.q();
                            i5 = i7;
                            i6 = i8;
                            z2 = true;
                        } else {
                            f4 += fQ2 - iRound2;
                            i5 = i7;
                            i6 = i8;
                            double d3 = f4;
                            if (d3 > 1.0d) {
                                iRound2++;
                                f4 -= 1.0f;
                            } else if (d3 < -1.0d) {
                                iRound2--;
                                f4 += 1.0f;
                            }
                        }
                        int iS = s(i, bVar, cVar.m);
                        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iRound2, 1073741824);
                        viewO.measure(iS, iMakeMeasureSpec2);
                        measuredWidth3 = viewO.getMeasuredWidth();
                        int measuredHeight4 = viewO.getMeasuredHeight();
                        P(i10, iS, iMakeMeasureSpec2, viewO);
                        this.f2938a.u(i10, viewO);
                        measuredHeight3 = measuredHeight4;
                    }
                    iMax = Math.max(i9, measuredWidth3 + bVar.n() + bVar.a() + this.f2938a.w(viewO));
                    cVar.e += measuredHeight3 + bVar.t() + bVar.l();
                }
                cVar.g = Math.max(cVar.g, iMax);
                i9 = iMax;
            }
            i8 = i6 + 1;
            i7 = i5;
            f2 = 0.0f;
        }
        int i12 = i7;
        if (!z2 || i12 == cVar.e) {
            return;
        }
        K(i, i2, cVar, i3, i4, true);
    }

    private void L(View view, int i, int i2) {
        b bVar = (b) view.getLayoutParams();
        int iMin = Math.min(Math.max(((i - bVar.n()) - bVar.a()) - this.f2938a.w(view), bVar.d()), bVar.r());
        long[] jArr = this.e;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? p(jArr[i2]) : view.getMeasuredHeight(), 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, 1073741824);
        view.measure(iMakeMeasureSpec2, iMakeMeasureSpec);
        P(i2, iMakeMeasureSpec2, iMakeMeasureSpec, view);
        this.f2938a.u(i2, view);
    }

    private void M(View view, int i, int i2) {
        b bVar = (b) view.getLayoutParams();
        int iMin = Math.min(Math.max(((i - bVar.t()) - bVar.l()) - this.f2938a.w(view), bVar.c()), bVar.k());
        long[] jArr = this.e;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? q(jArr[i2]) : view.getMeasuredWidth(), 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, 1073741824);
        view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        P(i2, iMakeMeasureSpec, iMakeMeasureSpec2, view);
        this.f2938a.u(i2, view);
    }

    private void P(int i, int i2, int i3, View view) {
        long[] jArr = this.f2941d;
        if (jArr != null) {
            jArr[i] = J(i2, i3);
        }
        long[] jArr2 = this.e;
        if (jArr2 != null) {
            jArr2[i] = J(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    private void a(List<c> list, c cVar, int i, int i2) {
        cVar.m = i2;
        this.f2938a.i(cVar);
        cVar.p = i;
        list.add(cVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void g(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.b r0 = (com.google.android.flexbox.b) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.d()
            r4 = 1
            if (r1 >= r3) goto L1b
            int r1 = r0.d()
        L19:
            r3 = r4
            goto L27
        L1b:
            int r3 = r0.r()
            if (r1 <= r3) goto L26
            int r1 = r0.r()
            goto L19
        L26:
            r3 = 0
        L27:
            int r5 = r0.c()
            if (r2 >= r5) goto L32
            int r2 = r0.c()
            goto L3e
        L32:
            int r5 = r0.k()
            if (r2 <= r5) goto L3d
            int r2 = r0.k()
            goto L3e
        L3d:
            r4 = r3
        L3e:
            if (r4 == 0) goto L55
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.P(r8, r1, r0, r7)
            com.google.android.flexbox.a r0 = r6.f2938a
            r0.u(r8, r7)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.d.g(android.view.View, int):void");
    }

    private void k(int i) {
        boolean[] zArr = this.f2939b;
        if (zArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.f2939b = new boolean[i];
        } else {
            if (zArr.length >= i) {
                Arrays.fill(zArr, false);
                return;
            }
            int length = zArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f2939b = new boolean[i];
        }
    }

    private void o(int i, int i2, c cVar, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int iMax;
        double d2;
        int i7;
        double d3;
        float f = cVar.j;
        float f2 = 0.0f;
        if (f <= 0.0f || i3 < (i5 = cVar.e)) {
            return;
        }
        float f3 = (i3 - i5) / f;
        cVar.e = i4 + cVar.f;
        if (!z) {
            cVar.g = Integer.MIN_VALUE;
        }
        int i8 = 0;
        boolean z2 = false;
        int i9 = 0;
        float f4 = 0.0f;
        while (i8 < cVar.h) {
            int i10 = cVar.o + i8;
            View viewO = this.f2938a.o(i10);
            if (viewO == null || viewO.getVisibility() == 8) {
                i6 = i5;
            } else {
                b bVar = (b) viewO.getLayoutParams();
                int iR = this.f2938a.r();
                if (iR == 0 || iR == 1) {
                    int i11 = i5;
                    int measuredWidth = viewO.getMeasuredWidth();
                    long[] jArr = this.e;
                    if (jArr != null) {
                        measuredWidth = q(jArr[i10]);
                    }
                    int measuredHeight = viewO.getMeasuredHeight();
                    long[] jArr2 = this.e;
                    i6 = i11;
                    if (jArr2 != null) {
                        measuredHeight = p(jArr2[i10]);
                    }
                    if (!this.f2939b[i10] && bVar.f() > 0.0f) {
                        float f5 = measuredWidth + (bVar.f() * f3);
                        if (i8 == cVar.h - 1) {
                            f5 += f4;
                            f4 = 0.0f;
                        }
                        int iRound = Math.round(f5);
                        if (iRound > bVar.r()) {
                            iRound = bVar.r();
                            this.f2939b[i10] = true;
                            cVar.j -= bVar.f();
                            z2 = true;
                        } else {
                            f4 += f5 - iRound;
                            double d4 = f4;
                            if (d4 > 1.0d) {
                                iRound++;
                                d2 = d4 - 1.0d;
                            } else if (d4 < -1.0d) {
                                iRound--;
                                d2 = d4 + 1.0d;
                            }
                            f4 = (float) d2;
                        }
                        int iR2 = r(i2, bVar, cVar.m);
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iRound, 1073741824);
                        viewO.measure(iMakeMeasureSpec, iR2);
                        int measuredWidth2 = viewO.getMeasuredWidth();
                        int measuredHeight2 = viewO.getMeasuredHeight();
                        P(i10, iMakeMeasureSpec, iR2, viewO);
                        this.f2938a.u(i10, viewO);
                        measuredWidth = measuredWidth2;
                        measuredHeight = measuredHeight2;
                    }
                    int iMax2 = Math.max(i9, measuredHeight + bVar.t() + bVar.l() + this.f2938a.w(viewO));
                    cVar.e += measuredWidth + bVar.n() + bVar.a();
                    iMax = iMax2;
                } else {
                    int measuredHeight3 = viewO.getMeasuredHeight();
                    long[] jArr3 = this.e;
                    if (jArr3 != null) {
                        measuredHeight3 = p(jArr3[i10]);
                    }
                    int measuredWidth3 = viewO.getMeasuredWidth();
                    long[] jArr4 = this.e;
                    if (jArr4 != null) {
                        measuredWidth3 = q(jArr4[i10]);
                    }
                    if (this.f2939b[i10] || bVar.f() <= f2) {
                        i7 = i5;
                    } else {
                        float f6 = measuredHeight3 + (bVar.f() * f3);
                        if (i8 == cVar.h - 1) {
                            f6 += f4;
                            f4 = f2;
                        }
                        int iRound2 = Math.round(f6);
                        if (iRound2 > bVar.k()) {
                            iRound2 = bVar.k();
                            this.f2939b[i10] = true;
                            cVar.j -= bVar.f();
                            i7 = i5;
                            z2 = true;
                        } else {
                            f4 += f6 - iRound2;
                            i7 = i5;
                            double d5 = f4;
                            if (d5 > 1.0d) {
                                iRound2++;
                                d3 = d5 - 1.0d;
                            } else if (d5 < -1.0d) {
                                iRound2--;
                                d3 = d5 + 1.0d;
                            }
                            f4 = (float) d3;
                        }
                        int iS = s(i, bVar, cVar.m);
                        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iRound2, 1073741824);
                        viewO.measure(iS, iMakeMeasureSpec2);
                        measuredWidth3 = viewO.getMeasuredWidth();
                        int measuredHeight4 = viewO.getMeasuredHeight();
                        P(i10, iS, iMakeMeasureSpec2, viewO);
                        this.f2938a.u(i10, viewO);
                        measuredHeight3 = measuredHeight4;
                    }
                    iMax = Math.max(i9, measuredWidth3 + bVar.n() + bVar.a() + this.f2938a.w(viewO));
                    cVar.e += measuredHeight3 + bVar.t() + bVar.l();
                    i6 = i7;
                }
                cVar.g = Math.max(cVar.g, iMax);
                i9 = iMax;
            }
            i8++;
            i5 = i6;
            f2 = 0.0f;
        }
        int i12 = i5;
        if (!z2 || i12 == cVar.e) {
            return;
        }
        o(i, i2, cVar, i3, i4, true);
    }

    private int r(int i, b bVar, int i2) {
        int iC;
        com.google.android.flexbox.a aVar = this.f2938a;
        int iK = aVar.k(i, aVar.j() + this.f2938a.e() + bVar.t() + bVar.l() + i2, bVar.h());
        int size = View.MeasureSpec.getSize(iK);
        if (size > bVar.k()) {
            iC = bVar.k();
        } else {
            if (size >= bVar.c()) {
                return iK;
            }
            iC = bVar.c();
        }
        return View.MeasureSpec.makeMeasureSpec(iC, View.MeasureSpec.getMode(iK));
    }

    private int s(int i, b bVar, int i2) {
        int iD;
        com.google.android.flexbox.a aVar = this.f2938a;
        int iQ = aVar.q(i, aVar.l() + this.f2938a.b() + bVar.n() + bVar.a() + i2, bVar.g());
        int size = View.MeasureSpec.getSize(iQ);
        if (size > bVar.r()) {
            iD = bVar.r();
        } else {
            if (size >= bVar.d()) {
                return iQ;
            }
            iD = bVar.d();
        }
        return View.MeasureSpec.makeMeasureSpec(iD, View.MeasureSpec.getMode(iQ));
    }

    private int t(b bVar, boolean z) {
        return z ? bVar.l() : bVar.a();
    }

    private int u(b bVar, boolean z) {
        return z ? bVar.a() : bVar.l();
    }

    private int v(b bVar, boolean z) {
        return z ? bVar.t() : bVar.n();
    }

    private int w(b bVar, boolean z) {
        return z ? bVar.n() : bVar.t();
    }

    private int x(b bVar, boolean z) {
        return z ? bVar.h() : bVar.g();
    }

    private int y(b bVar, boolean z) {
        return z ? bVar.g() : bVar.h();
    }

    private int z(boolean z) {
        return z ? this.f2938a.e() : this.f2938a.f();
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void H(android.view.View r6, com.google.android.flexbox.c r7, int r8, int r9, int r10, int r11) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.d.H(android.view.View, com.google.android.flexbox.c, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void I(android.view.View r5, com.google.android.flexbox.c r6, boolean r7, int r8, int r9, int r10, int r11) {
        /*
            r4 = this;
            android.view.ViewGroup$LayoutParams r0 = r5.getLayoutParams()
            com.google.android.flexbox.b r0 = (com.google.android.flexbox.b) r0
            com.google.android.flexbox.a r1 = r4.f2938a
            int r1 = r1.t()
            int r2 = r0.o()
            r3 = -1
            if (r2 == r3) goto L17
            int r1 = r0.o()
        L17:
            int r6 = r6.g
            if (r1 == 0) goto L6a
            r2 = 1
            if (r1 == r2) goto L44
            r2 = 2
            if (r1 == r2) goto L28
            r6 = 3
            if (r1 == r6) goto L6a
            r6 = 4
            if (r1 == r6) goto L6a
            goto L84
        L28:
            android.view.ViewGroup$LayoutParams r0 = r5.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r0 = (android.view.ViewGroup.MarginLayoutParams) r0
            int r1 = r5.getMeasuredWidth()
            int r6 = r6 - r1
            int r1 = a.g.k.g.b(r0)
            int r6 = r6 + r1
            int r0 = a.g.k.g.a(r0)
            int r6 = r6 - r0
            int r6 = r6 / r2
            if (r7 != 0) goto L42
            int r8 = r8 + r6
            goto L75
        L42:
            int r8 = r8 - r6
            goto L80
        L44:
            if (r7 != 0) goto L58
            int r8 = r8 + r6
            int r7 = r5.getMeasuredWidth()
            int r8 = r8 - r7
            int r7 = r0.a()
            int r8 = r8 - r7
            int r10 = r10 + r6
            int r6 = r5.getMeasuredWidth()
            int r10 = r10 - r6
            goto L7c
        L58:
            int r8 = r8 - r6
            int r7 = r5.getMeasuredWidth()
            int r8 = r8 + r7
            int r7 = r0.n()
            int r8 = r8 + r7
            int r10 = r10 - r6
            int r6 = r5.getMeasuredWidth()
            int r10 = r10 + r6
            goto L71
        L6a:
            if (r7 != 0) goto L77
            int r6 = r0.n()
            int r8 = r8 + r6
        L71:
            int r6 = r0.n()
        L75:
            int r10 = r10 + r6
            goto L81
        L77:
            int r6 = r0.a()
            int r8 = r8 - r6
        L7c:
            int r6 = r0.a()
        L80:
            int r10 = r10 - r6
        L81:
            r5.layout(r8, r9, r10, r11)
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.d.I(android.view.View, com.google.android.flexbox.c, boolean, int, int, int, int):void");
    }

    long J(int i, int i2) {
        return (((long) i) & 4294967295L) | (((long) i2) << 32);
    }

    void N() {
        O(0);
    }

    void O(int i) {
        View viewO;
        if (i >= this.f2938a.h()) {
            return;
        }
        int iR = this.f2938a.r();
        if (this.f2938a.t() != 4) {
            for (c cVar : this.f2938a.g()) {
                for (Integer num : cVar.n) {
                    View viewO2 = this.f2938a.o(num.intValue());
                    if (iR == 0 || iR == 1) {
                        M(viewO2, cVar.g, num.intValue());
                    } else {
                        if (iR != 2 && iR != 3) {
                            throw new IllegalArgumentException("Invalid flex direction: " + iR);
                        }
                        L(viewO2, cVar.g, num.intValue());
                    }
                }
            }
            return;
        }
        int[] iArr = this.f2940c;
        List<c> listG = this.f2938a.g();
        int size = listG.size();
        for (int i2 = iArr != null ? iArr[i] : 0; i2 < size; i2++) {
            c cVar2 = listG.get(i2);
            int i3 = cVar2.h;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = cVar2.o + i4;
                if (i4 < this.f2938a.h() && (viewO = this.f2938a.o(i5)) != null && viewO.getVisibility() != 8) {
                    b bVar = (b) viewO.getLayoutParams();
                    if (bVar.o() == -1 || bVar.o() == 4) {
                        if (iR == 0 || iR == 1) {
                            M(viewO, cVar2.g, i5);
                        } else {
                            if (iR != 2 && iR != 3) {
                                throw new IllegalArgumentException("Invalid flex direction: " + iR);
                            }
                            L(viewO, cVar2.g, i5);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void b(com.google.android.flexbox.d.a r32, int r33, int r34, int r35, int r36, int r37, java.util.List<com.google.android.flexbox.c> r38) {
        /*
            Method dump skipped, instruction units count: 800
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.d.b(com.google.android.flexbox.d$a, int, int, int, int, int, java.util.List):void");
    }

    void c(a aVar, int i, int i2, int i3, int i4, List<c> list) {
        b(aVar, i, i2, i3, i4, -1, list);
    }

    void d(a aVar, int i, int i2, int i3, int i4, List<c> list) {
        b(aVar, i, i2, i3, 0, i4, list);
    }

    void e(a aVar, int i, int i2, int i3, int i4, List<c> list) {
        b(aVar, i2, i, i3, i4, -1, list);
    }

    void f(a aVar, int i, int i2, int i3, int i4, List<c> list) {
        b(aVar, i2, i, i3, 0, i4, list);
    }

    void h(List<c> list, int i) {
        int i2 = this.f2940c[i];
        if (i2 == -1) {
            i2 = 0;
        }
        for (int size = list.size() - 1; size >= i2; size--) {
            list.remove(size);
        }
        int[] iArr = this.f2940c;
        int length = iArr.length - 1;
        if (i > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i, length, -1);
        }
        long[] jArr = this.f2941d;
        int length2 = jArr.length - 1;
        if (i > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i, length2, 0L);
        }
    }

    void i(int i, int i2) {
        j(i, i2, 0);
    }

    void j(int i, int i2, int i3) {
        int size;
        int iL;
        int iB;
        k(this.f2938a.h());
        if (i3 >= this.f2938a.h()) {
            return;
        }
        int iR = this.f2938a.r();
        int iR2 = this.f2938a.r();
        if (iR2 == 0 || iR2 == 1) {
            int mode = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
            if (mode != 1073741824) {
                size = this.f2938a.a();
            }
            iL = this.f2938a.l();
            iB = this.f2938a.b();
        } else {
            if (iR2 != 2 && iR2 != 3) {
                throw new IllegalArgumentException("Invalid flex direction: " + iR);
            }
            int mode2 = View.MeasureSpec.getMode(i2);
            size = View.MeasureSpec.getSize(i2);
            if (mode2 != 1073741824) {
                size = this.f2938a.a();
            }
            iL = this.f2938a.j();
            iB = this.f2938a.e();
        }
        int i4 = iL + iB;
        int[] iArr = this.f2940c;
        int i5 = iArr != null ? iArr[i3] : 0;
        List<c> listG = this.f2938a.g();
        int size2 = listG.size();
        for (int i6 = i5; i6 < size2; i6++) {
            c cVar = listG.get(i6);
            if (cVar.e < size) {
                o(i, i2, cVar, size, i4, false);
            } else {
                K(i, i2, cVar, size, i4, false);
            }
        }
    }

    void l(int i) {
        int[] iArr = this.f2940c;
        if (iArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.f2940c = new int[i];
        } else if (iArr.length < i) {
            int length = iArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f2940c = Arrays.copyOf(iArr, i);
        }
    }

    void m(int i) {
        long[] jArr = this.f2941d;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.f2941d = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f2941d = Arrays.copyOf(jArr, i);
        }
    }

    void n(int i) {
        long[] jArr = this.e;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.e = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.e = Arrays.copyOf(jArr, i);
        }
    }

    int p(long j) {
        return (int) (j >> 32);
    }

    int q(long j) {
        return (int) j;
    }
}
