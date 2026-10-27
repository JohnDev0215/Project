package com.google.android.flexbox;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import b.g.k.g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: FlexboxHelper.java */
/* JADX INFO: loaded from: classes.dex */
class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.android.flexbox.a f3948a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean[] f3949b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int[] f3950c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    long[] f3951d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long[] f3952e;

    /* JADX INFO: compiled from: FlexboxHelper.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        List<com.google.android.flexbox.b> f3953a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3954b;

        b() {
        }

        void a() {
            this.f3953a = null;
            this.f3954b = 0;
        }
    }

    /* JADX INFO: renamed from: com.google.android.flexbox.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FlexboxHelper.java */
    private static class C0099c implements Comparable<C0099c> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3955b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f3956c;

        private C0099c() {
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(C0099c c0099c) {
            int i = this.f3956c;
            int i2 = c0099c.f3956c;
            return i != i2 ? i - i2 : this.f3955b - c0099c.f3955b;
        }

        public String toString() {
            return "Order{order=" + this.f3956c + ", index=" + this.f3955b + '}';
        }
    }

    c(com.google.android.flexbox.a aVar) {
        this.f3948a = aVar;
    }

    private int A(FlexItem flexItem, boolean z) {
        return z ? flexItem.l() : flexItem.c();
    }

    private int B(FlexItem flexItem, boolean z) {
        return z ? flexItem.c() : flexItem.l();
    }

    private int C(FlexItem flexItem, boolean z) {
        return z ? flexItem.t() : flexItem.n();
    }

    private int D(FlexItem flexItem, boolean z) {
        return z ? flexItem.n() : flexItem.t();
    }

    private int E(FlexItem flexItem, boolean z) {
        return z ? flexItem.b() : flexItem.a();
    }

    private int F(FlexItem flexItem, boolean z) {
        return z ? flexItem.a() : flexItem.b();
    }

    private int G(boolean z) {
        return z ? this.f3948a.getPaddingBottom() : this.f3948a.getPaddingEnd();
    }

    private int H(boolean z) {
        return z ? this.f3948a.getPaddingEnd() : this.f3948a.getPaddingBottom();
    }

    private int I(boolean z) {
        return z ? this.f3948a.getPaddingTop() : this.f3948a.getPaddingStart();
    }

    private int J(boolean z) {
        return z ? this.f3948a.getPaddingStart() : this.f3948a.getPaddingTop();
    }

    private int K(View view, boolean z) {
        return z ? view.getMeasuredHeight() : view.getMeasuredWidth();
    }

    private int L(View view, boolean z) {
        return z ? view.getMeasuredWidth() : view.getMeasuredHeight();
    }

    private boolean M(int i, int i2, com.google.android.flexbox.b bVar) {
        return i == i2 - 1 && bVar.c() != 0;
    }

    private boolean O(View view, int i, int i2, int i3, int i4, FlexItem flexItem, int i5, int i6, int i7) {
        if (this.f3948a.getFlexWrap() == 0) {
            return false;
        }
        if (flexItem.g()) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        int maxLine = this.f3948a.getMaxLine();
        if (maxLine != -1 && maxLine <= i7 + 1) {
            return false;
        }
        int iB = this.f3948a.b(view, i5, i6);
        if (iB > 0) {
            i4 += iB;
        }
        return i2 < i3 + i4;
    }

    private void S(int i, int i2, com.google.android.flexbox.b bVar, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int iMax;
        int i7 = bVar.f3947e;
        float f = bVar.k;
        float f2 = 0.0f;
        if (f <= 0.0f || i3 > i7) {
            return;
        }
        float f3 = (i7 - i3) / f;
        bVar.f3947e = i4 + bVar.f;
        if (!z) {
            bVar.g = Integer.MIN_VALUE;
        }
        int i8 = 0;
        boolean z2 = false;
        int i9 = 0;
        float f4 = 0.0f;
        while (i8 < bVar.h) {
            int i10 = bVar.o + i8;
            View viewF = this.f3948a.f(i10);
            if (viewF == null || viewF.getVisibility() == 8) {
                i5 = i7;
                i6 = i8;
            } else {
                FlexItem flexItem = (FlexItem) viewF.getLayoutParams();
                int flexDirection = this.f3948a.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    i5 = i7;
                    int i11 = i8;
                    int measuredWidth = viewF.getMeasuredWidth();
                    long[] jArr = this.f3952e;
                    if (jArr != null) {
                        measuredWidth = x(jArr[i10]);
                    }
                    int measuredHeight = viewF.getMeasuredHeight();
                    long[] jArr2 = this.f3952e;
                    if (jArr2 != null) {
                        measuredHeight = w(jArr2[i10]);
                    }
                    if (this.f3949b[i10] || flexItem.q() <= 0.0f) {
                        i6 = i11;
                    } else {
                        float fQ = measuredWidth - (flexItem.q() * f3);
                        i6 = i11;
                        if (i6 == bVar.h - 1) {
                            fQ += f4;
                            f4 = 0.0f;
                        }
                        int iRound = Math.round(fQ);
                        if (iRound < flexItem.f()) {
                            iRound = flexItem.f();
                            this.f3949b[i10] = true;
                            bVar.k -= flexItem.q();
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
                        int iY = y(i2, flexItem, bVar.m);
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iRound, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                        viewF.measure(iMakeMeasureSpec, iY);
                        int measuredWidth2 = viewF.getMeasuredWidth();
                        int measuredHeight2 = viewF.getMeasuredHeight();
                        Y(i10, iMakeMeasureSpec, iY, viewF);
                        this.f3948a.i(i10, viewF);
                        measuredWidth = measuredWidth2;
                        measuredHeight = measuredHeight2;
                    }
                    int iMax2 = Math.max(i9, measuredHeight + flexItem.t() + flexItem.l() + this.f3948a.k(viewF));
                    bVar.f3947e += measuredWidth + flexItem.n() + flexItem.c();
                    iMax = iMax2;
                } else {
                    int measuredHeight3 = viewF.getMeasuredHeight();
                    long[] jArr3 = this.f3952e;
                    if (jArr3 != null) {
                        measuredHeight3 = w(jArr3[i10]);
                    }
                    int measuredWidth3 = viewF.getMeasuredWidth();
                    long[] jArr4 = this.f3952e;
                    if (jArr4 != null) {
                        measuredWidth3 = x(jArr4[i10]);
                    }
                    if (this.f3949b[i10] || flexItem.q() <= f2) {
                        i5 = i7;
                        i6 = i8;
                    } else {
                        float fQ2 = measuredHeight3 - (flexItem.q() * f3);
                        if (i8 == bVar.h - 1) {
                            fQ2 += f4;
                            f4 = f2;
                        }
                        int iRound2 = Math.round(fQ2);
                        if (iRound2 < flexItem.e()) {
                            iRound2 = flexItem.e();
                            this.f3949b[i10] = true;
                            bVar.k -= flexItem.q();
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
                        int iZ = z(i, flexItem, bVar.m);
                        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iRound2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                        viewF.measure(iZ, iMakeMeasureSpec2);
                        measuredWidth3 = viewF.getMeasuredWidth();
                        int measuredHeight4 = viewF.getMeasuredHeight();
                        Y(i10, iZ, iMakeMeasureSpec2, viewF);
                        this.f3948a.i(i10, viewF);
                        measuredHeight3 = measuredHeight4;
                    }
                    iMax = Math.max(i9, measuredWidth3 + flexItem.n() + flexItem.c() + this.f3948a.k(viewF));
                    bVar.f3947e += measuredHeight3 + flexItem.t() + flexItem.l();
                }
                bVar.g = Math.max(bVar.g, iMax);
                i9 = iMax;
            }
            i8 = i6 + 1;
            i7 = i5;
            f2 = 0.0f;
        }
        int i12 = i7;
        if (!z2 || i12 == bVar.f3947e) {
            return;
        }
        S(i, i2, bVar, i3, i4, true);
    }

    private int[] T(int i, List<C0099c> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (C0099c c0099c : list) {
            int i3 = c0099c.f3955b;
            iArr[i2] = i3;
            sparseIntArray.append(i3, c0099c.f3956c);
            i2++;
        }
        return iArr;
    }

    private void U(View view, int i, int i2) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int iMin = Math.min(Math.max(((i - flexItem.n()) - flexItem.c()) - this.f3948a.k(view), flexItem.f()), flexItem.r());
        long[] jArr = this.f3952e;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? w(jArr[i2]) : view.getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        view.measure(iMakeMeasureSpec2, iMakeMeasureSpec);
        Y(i2, iMakeMeasureSpec2, iMakeMeasureSpec, view);
        this.f3948a.i(i2, view);
    }

    private void V(View view, int i, int i2) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int iMin = Math.min(Math.max(((i - flexItem.t()) - flexItem.l()) - this.f3948a.k(view), flexItem.e()), flexItem.k());
        long[] jArr = this.f3952e;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? x(jArr[i2]) : view.getMeasuredWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        Y(i2, iMakeMeasureSpec, iMakeMeasureSpec2, view);
        this.f3948a.i(i2, view);
    }

    private void Y(int i, int i2, int i3, View view) {
        long[] jArr = this.f3951d;
        if (jArr != null) {
            jArr[i] = R(i2, i3);
        }
        long[] jArr2 = this.f3952e;
        if (jArr2 != null) {
            jArr2[i] = R(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    private void a(List<com.google.android.flexbox.b> list, com.google.android.flexbox.b bVar, int i, int i2) {
        bVar.m = i2;
        this.f3948a.c(bVar);
        bVar.p = i;
        list.add(bVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void i(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexItem r0 = (com.google.android.flexbox.FlexItem) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.f()
            r4 = 1
            if (r1 >= r3) goto L1b
            int r1 = r0.f()
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
            int r5 = r0.e()
            if (r2 >= r5) goto L32
            int r2 = r0.e()
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
            r6.Y(r8, r1, r0, r7)
            com.google.android.flexbox.a r0 = r6.f3948a
            r0.i(r8, r7)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.c.i(android.view.View, int):void");
    }

    private List<com.google.android.flexbox.b> k(List<com.google.android.flexbox.b> list, int i, int i2) {
        int i3 = (i - i2) / 2;
        ArrayList arrayList = new ArrayList();
        com.google.android.flexbox.b bVar = new com.google.android.flexbox.b();
        bVar.g = i3;
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            if (i4 == 0) {
                arrayList.add(bVar);
            }
            arrayList.add(list.get(i4));
            if (i4 == list.size() - 1) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    private List<C0099c> l(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            FlexItem flexItem = (FlexItem) this.f3948a.a(i2).getLayoutParams();
            C0099c c0099c = new C0099c();
            c0099c.f3956c = flexItem.getOrder();
            c0099c.f3955b = i2;
            arrayList.add(c0099c);
        }
        return arrayList;
    }

    private void r(int i) {
        boolean[] zArr = this.f3949b;
        if (zArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.f3949b = new boolean[i];
        } else {
            if (zArr.length >= i) {
                Arrays.fill(zArr, false);
                return;
            }
            int length = zArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f3949b = new boolean[i];
        }
    }

    private void v(int i, int i2, com.google.android.flexbox.b bVar, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int iMax;
        double d2;
        int i7;
        double d3;
        float f = bVar.j;
        float f2 = 0.0f;
        if (f <= 0.0f || i3 < (i5 = bVar.f3947e)) {
            return;
        }
        float f3 = (i3 - i5) / f;
        bVar.f3947e = i4 + bVar.f;
        if (!z) {
            bVar.g = Integer.MIN_VALUE;
        }
        int i8 = 0;
        boolean z2 = false;
        int i9 = 0;
        float f4 = 0.0f;
        while (i8 < bVar.h) {
            int i10 = bVar.o + i8;
            View viewF = this.f3948a.f(i10);
            if (viewF == null || viewF.getVisibility() == 8) {
                i6 = i5;
            } else {
                FlexItem flexItem = (FlexItem) viewF.getLayoutParams();
                int flexDirection = this.f3948a.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    int i11 = i5;
                    int measuredWidth = viewF.getMeasuredWidth();
                    long[] jArr = this.f3952e;
                    if (jArr != null) {
                        measuredWidth = x(jArr[i10]);
                    }
                    int measuredHeight = viewF.getMeasuredHeight();
                    long[] jArr2 = this.f3952e;
                    i6 = i11;
                    if (jArr2 != null) {
                        measuredHeight = w(jArr2[i10]);
                    }
                    if (!this.f3949b[i10] && flexItem.h() > 0.0f) {
                        float fH = measuredWidth + (flexItem.h() * f3);
                        if (i8 == bVar.h - 1) {
                            fH += f4;
                            f4 = 0.0f;
                        }
                        int iRound = Math.round(fH);
                        if (iRound > flexItem.r()) {
                            iRound = flexItem.r();
                            this.f3949b[i10] = true;
                            bVar.j -= flexItem.h();
                            z2 = true;
                        } else {
                            f4 += fH - iRound;
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
                        int iY = y(i2, flexItem, bVar.m);
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iRound, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                        viewF.measure(iMakeMeasureSpec, iY);
                        int measuredWidth2 = viewF.getMeasuredWidth();
                        int measuredHeight2 = viewF.getMeasuredHeight();
                        Y(i10, iMakeMeasureSpec, iY, viewF);
                        this.f3948a.i(i10, viewF);
                        measuredWidth = measuredWidth2;
                        measuredHeight = measuredHeight2;
                    }
                    int iMax2 = Math.max(i9, measuredHeight + flexItem.t() + flexItem.l() + this.f3948a.k(viewF));
                    bVar.f3947e += measuredWidth + flexItem.n() + flexItem.c();
                    iMax = iMax2;
                } else {
                    int measuredHeight3 = viewF.getMeasuredHeight();
                    long[] jArr3 = this.f3952e;
                    if (jArr3 != null) {
                        measuredHeight3 = w(jArr3[i10]);
                    }
                    int measuredWidth3 = viewF.getMeasuredWidth();
                    long[] jArr4 = this.f3952e;
                    if (jArr4 != null) {
                        measuredWidth3 = x(jArr4[i10]);
                    }
                    if (this.f3949b[i10] || flexItem.h() <= f2) {
                        i7 = i5;
                    } else {
                        float fH2 = measuredHeight3 + (flexItem.h() * f3);
                        if (i8 == bVar.h - 1) {
                            fH2 += f4;
                            f4 = f2;
                        }
                        int iRound2 = Math.round(fH2);
                        if (iRound2 > flexItem.k()) {
                            iRound2 = flexItem.k();
                            this.f3949b[i10] = true;
                            bVar.j -= flexItem.h();
                            i7 = i5;
                            z2 = true;
                        } else {
                            f4 += fH2 - iRound2;
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
                        int iZ = z(i, flexItem, bVar.m);
                        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iRound2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                        viewF.measure(iZ, iMakeMeasureSpec2);
                        measuredWidth3 = viewF.getMeasuredWidth();
                        int measuredHeight4 = viewF.getMeasuredHeight();
                        Y(i10, iZ, iMakeMeasureSpec2, viewF);
                        this.f3948a.i(i10, viewF);
                        measuredHeight3 = measuredHeight4;
                    }
                    iMax = Math.max(i9, measuredWidth3 + flexItem.n() + flexItem.c() + this.f3948a.k(viewF));
                    bVar.f3947e += measuredHeight3 + flexItem.t() + flexItem.l();
                    i6 = i7;
                }
                bVar.g = Math.max(bVar.g, iMax);
                i9 = iMax;
            }
            i8++;
            i5 = i6;
            f2 = 0.0f;
        }
        int i12 = i5;
        if (!z2 || i12 == bVar.f3947e) {
            return;
        }
        v(i, i2, bVar, i3, i4, true);
    }

    private int y(int i, FlexItem flexItem, int i2) {
        com.google.android.flexbox.a aVar = this.f3948a;
        int iD = aVar.d(i, aVar.getPaddingTop() + this.f3948a.getPaddingBottom() + flexItem.t() + flexItem.l() + i2, flexItem.b());
        int size = View.MeasureSpec.getSize(iD);
        return size > flexItem.k() ? View.MeasureSpec.makeMeasureSpec(flexItem.k(), View.MeasureSpec.getMode(iD)) : size < flexItem.e() ? View.MeasureSpec.makeMeasureSpec(flexItem.e(), View.MeasureSpec.getMode(iD)) : iD;
    }

    private int z(int i, FlexItem flexItem, int i2) {
        com.google.android.flexbox.a aVar = this.f3948a;
        int iH = aVar.h(i, aVar.getPaddingLeft() + this.f3948a.getPaddingRight() + flexItem.n() + flexItem.c() + i2, flexItem.a());
        int size = View.MeasureSpec.getSize(iH);
        return size > flexItem.r() ? View.MeasureSpec.makeMeasureSpec(flexItem.r(), View.MeasureSpec.getMode(iH)) : size < flexItem.f() ? View.MeasureSpec.makeMeasureSpec(flexItem.f(), View.MeasureSpec.getMode(iH)) : iH;
    }

    boolean N(SparseIntArray sparseIntArray) {
        int flexItemCount = this.f3948a.getFlexItemCount();
        if (sparseIntArray.size() != flexItemCount) {
            return true;
        }
        for (int i = 0; i < flexItemCount; i++) {
            View viewA = this.f3948a.a(i);
            if (viewA != null && ((FlexItem) viewA.getLayoutParams()).getOrder() != sparseIntArray.get(i)) {
                return true;
            }
        }
        return false;
    }

    void P(View view, com.google.android.flexbox.b bVar, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.f3948a.getAlignItems();
        if (flexItem.o() != -1) {
            alignItems = flexItem.o();
        }
        int i5 = bVar.g;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (this.f3948a.getFlexWrap() == 2) {
                    view.layout(i, (i2 - i5) + view.getMeasuredHeight() + flexItem.t(), i3, (i4 - i5) + view.getMeasuredHeight() + flexItem.t());
                    return;
                } else {
                    int i6 = i2 + i5;
                    view.layout(i, (i6 - view.getMeasuredHeight()) - flexItem.l(), i3, i6 - flexItem.l());
                    return;
                }
            }
            if (alignItems == 2) {
                int measuredHeight = (((i5 - view.getMeasuredHeight()) + flexItem.t()) - flexItem.l()) / 2;
                if (this.f3948a.getFlexWrap() != 2) {
                    int i7 = i2 + measuredHeight;
                    view.layout(i, i7, i3, view.getMeasuredHeight() + i7);
                    return;
                } else {
                    int i8 = i2 - measuredHeight;
                    view.layout(i, i8, i3, view.getMeasuredHeight() + i8);
                    return;
                }
            }
            if (alignItems == 3) {
                if (this.f3948a.getFlexWrap() != 2) {
                    int iMax = Math.max(bVar.l - view.getBaseline(), flexItem.t());
                    view.layout(i, i2 + iMax, i3, i4 + iMax);
                    return;
                } else {
                    int iMax2 = Math.max((bVar.l - view.getMeasuredHeight()) + view.getBaseline(), flexItem.l());
                    view.layout(i, i2 - iMax2, i3, i4 - iMax2);
                    return;
                }
            }
            if (alignItems != 4) {
                return;
            }
        }
        if (this.f3948a.getFlexWrap() != 2) {
            view.layout(i, i2 + flexItem.t(), i3, i4 + flexItem.t());
        } else {
            view.layout(i, i2 - flexItem.l(), i3, i4 - flexItem.l());
        }
    }

    void Q(View view, com.google.android.flexbox.b bVar, boolean z, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.f3948a.getAlignItems();
        if (flexItem.o() != -1) {
            alignItems = flexItem.o();
        }
        int i5 = bVar.g;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (z) {
                    view.layout((i - i5) + view.getMeasuredWidth() + flexItem.n(), i2, (i3 - i5) + view.getMeasuredWidth() + flexItem.n(), i4);
                    return;
                } else {
                    view.layout(((i + i5) - view.getMeasuredWidth()) - flexItem.c(), i2, ((i3 + i5) - view.getMeasuredWidth()) - flexItem.c(), i4);
                    return;
                }
            }
            if (alignItems == 2) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int measuredWidth = (((i5 - view.getMeasuredWidth()) + g.b(marginLayoutParams)) - g.a(marginLayoutParams)) / 2;
                if (z) {
                    view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                    return;
                } else {
                    view.layout(i + measuredWidth, i2, i3 + measuredWidth, i4);
                    return;
                }
            }
            if (alignItems != 3 && alignItems != 4) {
                return;
            }
        }
        if (z) {
            view.layout(i - flexItem.c(), i2, i3 - flexItem.c(), i4);
        } else {
            view.layout(i + flexItem.n(), i2, i3 + flexItem.n(), i4);
        }
    }

    long R(int i, int i2) {
        return (((long) i) & 4294967295L) | (((long) i2) << 32);
    }

    void W() {
        X(0);
    }

    void X(int i) {
        View viewF;
        if (i >= this.f3948a.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.f3948a.getFlexDirection();
        if (this.f3948a.getAlignItems() != 4) {
            for (com.google.android.flexbox.b bVar : this.f3948a.getFlexLinesInternal()) {
                for (Integer num : bVar.n) {
                    View viewF2 = this.f3948a.f(num.intValue());
                    if (flexDirection == 0 || flexDirection == 1) {
                        V(viewF2, bVar.g, num.intValue());
                    } else {
                        if (flexDirection != 2 && flexDirection != 3) {
                            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                        }
                        U(viewF2, bVar.g, num.intValue());
                    }
                }
            }
            return;
        }
        int[] iArr = this.f3950c;
        List<com.google.android.flexbox.b> flexLinesInternal = this.f3948a.getFlexLinesInternal();
        int size = flexLinesInternal.size();
        for (int i2 = iArr != null ? iArr[i] : 0; i2 < size; i2++) {
            com.google.android.flexbox.b bVar2 = flexLinesInternal.get(i2);
            int i3 = bVar2.h;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = bVar2.o + i4;
                if (i4 < this.f3948a.getFlexItemCount() && (viewF = this.f3948a.f(i5)) != null && viewF.getVisibility() != 8) {
                    FlexItem flexItem = (FlexItem) viewF.getLayoutParams();
                    if (flexItem.o() == -1 || flexItem.o() == 4) {
                        if (flexDirection == 0 || flexDirection == 1) {
                            V(viewF, bVar2.g, i5);
                        } else {
                            if (flexDirection != 2 && flexDirection != 3) {
                                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                            }
                            U(viewF, bVar2.g, i5);
                        }
                    }
                }
            }
        }
    }

    void b(b bVar, int i, int i2, int i3, int i4, int i5, List<com.google.android.flexbox.b> list) {
        int i6;
        b bVar2;
        int i7;
        int i8;
        int i9;
        List<com.google.android.flexbox.b> list2;
        int i10;
        View view;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17 = i;
        int i18 = i2;
        int i19 = i5;
        boolean zJ = this.f3948a.j();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        List<com.google.android.flexbox.b> arrayList = list == null ? new ArrayList() : list;
        bVar.f3953a = arrayList;
        boolean z = i19 == -1;
        int iJ = J(zJ);
        int iH = H(zJ);
        int I = I(zJ);
        int iG = G(zJ);
        com.google.android.flexbox.b bVar3 = new com.google.android.flexbox.b();
        int i20 = i4;
        bVar3.o = i20;
        int i21 = iH + iJ;
        bVar3.f3947e = i21;
        int flexItemCount = this.f3948a.getFlexItemCount();
        boolean z2 = z;
        int i22 = Integer.MIN_VALUE;
        int i23 = 0;
        int iCombineMeasuredStates = 0;
        int i24 = 0;
        while (true) {
            if (i20 >= flexItemCount) {
                i6 = iCombineMeasuredStates;
                bVar2 = bVar;
                break;
            }
            View viewF = this.f3948a.f(i20);
            if (viewF == null) {
                if (M(i20, flexItemCount, bVar3)) {
                    a(arrayList, bVar3, i20, i23);
                }
            } else if (viewF.getVisibility() == 8) {
                bVar3.i++;
                bVar3.h++;
                if (M(i20, flexItemCount, bVar3)) {
                    a(arrayList, bVar3, i20, i23);
                }
            } else {
                FlexItem flexItem = (FlexItem) viewF.getLayoutParams();
                int i25 = flexItemCount;
                if (flexItem.o() == 4) {
                    bVar3.n.add(Integer.valueOf(i20));
                }
                int iF = F(flexItem, zJ);
                if (flexItem.m() != -1.0f && mode == 1073741824) {
                    iF = Math.round(size * flexItem.m());
                }
                if (zJ) {
                    int iH2 = this.f3948a.h(i17, i21 + D(flexItem, true) + B(flexItem, true), iF);
                    i7 = size;
                    i8 = mode;
                    int iD = this.f3948a.d(i18, I + iG + C(flexItem, true) + A(flexItem, true) + i23, E(flexItem, true));
                    viewF.measure(iH2, iD);
                    Y(i20, iH2, iD, viewF);
                    i9 = iH2;
                } else {
                    i7 = size;
                    i8 = mode;
                    int iH3 = this.f3948a.h(i18, I + iG + C(flexItem, false) + A(flexItem, false) + i23, E(flexItem, false));
                    int iD2 = this.f3948a.d(i17, D(flexItem, false) + i21 + B(flexItem, false), iF);
                    viewF.measure(iH3, iD2);
                    Y(i20, iH3, iD2, viewF);
                    i9 = iD2;
                }
                this.f3948a.i(i20, viewF);
                i(viewF, i20);
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, viewF.getMeasuredState());
                int i26 = i23;
                int i27 = i21;
                com.google.android.flexbox.b bVar4 = bVar3;
                int i28 = i20;
                list2 = arrayList;
                int i29 = i9;
                if (O(viewF, i8, i7, bVar3.f3947e, B(flexItem, zJ) + L(viewF, zJ) + D(flexItem, zJ), flexItem, i28, i24, arrayList.size())) {
                    if (bVar4.c() > 0) {
                        a(list2, bVar4, i28 > 0 ? i28 - 1 : 0, i26);
                        i23 = bVar4.g + i26;
                    } else {
                        i23 = i26;
                    }
                    if (!zJ) {
                        i10 = i2;
                        view = viewF;
                        i20 = i28;
                        if (flexItem.a() == -1) {
                            com.google.android.flexbox.a aVar = this.f3948a;
                            view.measure(aVar.h(i10, aVar.getPaddingLeft() + this.f3948a.getPaddingRight() + flexItem.n() + flexItem.c() + i23, flexItem.a()), i29);
                            i(view, i20);
                        }
                    } else if (flexItem.b() == -1) {
                        com.google.android.flexbox.a aVar2 = this.f3948a;
                        i10 = i2;
                        i20 = i28;
                        view = viewF;
                        view.measure(i29, aVar2.d(i10, aVar2.getPaddingTop() + this.f3948a.getPaddingBottom() + flexItem.t() + flexItem.l() + i23, flexItem.b()));
                        i(view, i20);
                    } else {
                        i10 = i2;
                        view = viewF;
                        i20 = i28;
                    }
                    bVar3 = new com.google.android.flexbox.b();
                    bVar3.h = 1;
                    i11 = i27;
                    bVar3.f3947e = i11;
                    bVar3.o = i20;
                    i13 = Integer.MIN_VALUE;
                    i12 = 0;
                } else {
                    i10 = i2;
                    view = viewF;
                    i20 = i28;
                    bVar3 = bVar4;
                    i11 = i27;
                    bVar3.h++;
                    i12 = i24 + 1;
                    i23 = i26;
                    i13 = i22;
                }
                int[] iArr = this.f3950c;
                if (iArr != null) {
                    iArr[i20] = list2.size();
                }
                bVar3.f3947e += L(view, zJ) + D(flexItem, zJ) + B(flexItem, zJ);
                bVar3.j += flexItem.h();
                bVar3.k += flexItem.q();
                this.f3948a.g(view, i20, i12, bVar3);
                int iMax = Math.max(i13, K(view, zJ) + C(flexItem, zJ) + A(flexItem, zJ) + this.f3948a.k(view));
                bVar3.g = Math.max(bVar3.g, iMax);
                if (zJ) {
                    if (this.f3948a.getFlexWrap() != 2) {
                        bVar3.l = Math.max(bVar3.l, view.getBaseline() + flexItem.t());
                    } else {
                        bVar3.l = Math.max(bVar3.l, (view.getMeasuredHeight() - view.getBaseline()) + flexItem.l());
                    }
                }
                i14 = i25;
                if (M(i20, i14, bVar3)) {
                    a(list2, bVar3, i20, i23);
                    i23 += bVar3.g;
                }
                i15 = i5;
                if (i15 != -1 && list2.size() > 0) {
                    if (list2.get(list2.size() - 1).p >= i15 && i20 >= i15 && !z2) {
                        i23 = -bVar3.a();
                        i16 = i3;
                        z2 = true;
                    }
                    if (i23 <= i16 && z2) {
                        bVar2 = bVar;
                        i6 = iCombineMeasuredStates;
                        break;
                    }
                    i24 = i12;
                    i22 = iMax;
                    i20++;
                    i17 = i;
                    flexItemCount = i14;
                    i18 = i10;
                    i21 = i11;
                    arrayList = list2;
                    mode = i8;
                    i19 = i15;
                    size = i7;
                }
                i16 = i3;
                if (i23 <= i16) {
                }
                i24 = i12;
                i22 = iMax;
                i20++;
                i17 = i;
                flexItemCount = i14;
                i18 = i10;
                i21 = i11;
                arrayList = list2;
                mode = i8;
                i19 = i15;
                size = i7;
            }
            i7 = size;
            i8 = mode;
            i10 = i18;
            i15 = i19;
            list2 = arrayList;
            i11 = i21;
            i14 = flexItemCount;
            i20++;
            i17 = i;
            flexItemCount = i14;
            i18 = i10;
            i21 = i11;
            arrayList = list2;
            mode = i8;
            i19 = i15;
            size = i7;
        }
        bVar2.f3954b = i6;
    }

    void c(b bVar, int i, int i2) {
        b(bVar, i, i2, Integer.MAX_VALUE, 0, -1, null);
    }

    void d(b bVar, int i, int i2, int i3, int i4, List<com.google.android.flexbox.b> list) {
        b(bVar, i, i2, i3, i4, -1, list);
    }

    void e(b bVar, int i, int i2, int i3, int i4, List<com.google.android.flexbox.b> list) {
        b(bVar, i, i2, i3, 0, i4, list);
    }

    void f(b bVar, int i, int i2) {
        b(bVar, i2, i, Integer.MAX_VALUE, 0, -1, null);
    }

    void g(b bVar, int i, int i2, int i3, int i4, List<com.google.android.flexbox.b> list) {
        b(bVar, i2, i, i3, i4, -1, list);
    }

    void h(b bVar, int i, int i2, int i3, int i4, List<com.google.android.flexbox.b> list) {
        b(bVar, i2, i, i3, 0, i4, list);
    }

    void j(List<com.google.android.flexbox.b> list, int i) {
        int i2 = this.f3950c[i];
        if (i2 == -1) {
            i2 = 0;
        }
        for (int size = list.size() - 1; size >= i2; size--) {
            list.remove(size);
        }
        int[] iArr = this.f3950c;
        int length = iArr.length - 1;
        if (i > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i, length, -1);
        }
        long[] jArr = this.f3951d;
        int length2 = jArr.length - 1;
        if (i > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i, length2, 0L);
        }
    }

    int[] m(SparseIntArray sparseIntArray) {
        int flexItemCount = this.f3948a.getFlexItemCount();
        return T(flexItemCount, l(flexItemCount), sparseIntArray);
    }

    /* JADX WARN: Multi-variable type inference failed */
    int[] n(View view, int i, ViewGroup.LayoutParams layoutParams, SparseIntArray sparseIntArray) {
        int flexItemCount = this.f3948a.getFlexItemCount();
        List<C0099c> listL = l(flexItemCount);
        C0099c c0099c = new C0099c();
        if (view == null || !(layoutParams instanceof FlexItem)) {
            c0099c.f3956c = 1;
        } else {
            c0099c.f3956c = ((FlexItem) layoutParams).getOrder();
        }
        if (i == -1 || i == flexItemCount || i >= this.f3948a.getFlexItemCount()) {
            c0099c.f3955b = flexItemCount;
        } else {
            c0099c.f3955b = i;
            while (i < flexItemCount) {
                listL.get(i).f3955b++;
                i++;
            }
        }
        listL.add(c0099c);
        return T(flexItemCount + 1, listL, sparseIntArray);
    }

    void o(int i, int i2, int i3) {
        int mode;
        int size;
        int flexDirection = this.f3948a.getFlexDirection();
        if (flexDirection == 0 || flexDirection == 1) {
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            mode = mode2;
            size = size2;
        } else {
            if (flexDirection != 2 && flexDirection != 3) {
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
            }
            mode = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
        }
        List<com.google.android.flexbox.b> flexLinesInternal = this.f3948a.getFlexLinesInternal();
        if (mode == 1073741824) {
            int sumOfCrossSize = this.f3948a.getSumOfCrossSize() + i3;
            int i4 = 0;
            if (flexLinesInternal.size() == 1) {
                flexLinesInternal.get(0).g = size - i3;
                return;
            }
            if (flexLinesInternal.size() >= 2) {
                int alignContent = this.f3948a.getAlignContent();
                if (alignContent == 1) {
                    int i5 = size - sumOfCrossSize;
                    com.google.android.flexbox.b bVar = new com.google.android.flexbox.b();
                    bVar.g = i5;
                    flexLinesInternal.add(0, bVar);
                    return;
                }
                if (alignContent == 2) {
                    this.f3948a.setFlexLines(k(flexLinesInternal, size, sumOfCrossSize));
                    return;
                }
                if (alignContent == 3) {
                    if (sumOfCrossSize >= size) {
                        return;
                    }
                    float size3 = (size - sumOfCrossSize) / (flexLinesInternal.size() - 1);
                    ArrayList arrayList = new ArrayList();
                    int size4 = flexLinesInternal.size();
                    float f = 0.0f;
                    while (i4 < size4) {
                        arrayList.add(flexLinesInternal.get(i4));
                        if (i4 != flexLinesInternal.size() - 1) {
                            com.google.android.flexbox.b bVar2 = new com.google.android.flexbox.b();
                            if (i4 == flexLinesInternal.size() - 2) {
                                bVar2.g = Math.round(f + size3);
                                f = 0.0f;
                            } else {
                                bVar2.g = Math.round(size3);
                            }
                            int i6 = bVar2.g;
                            f += size3 - i6;
                            if (f > 1.0f) {
                                bVar2.g = i6 + 1;
                                f -= 1.0f;
                            } else if (f < -1.0f) {
                                bVar2.g = i6 - 1;
                                f += 1.0f;
                            }
                            arrayList.add(bVar2);
                        }
                        i4++;
                    }
                    this.f3948a.setFlexLines(arrayList);
                    return;
                }
                if (alignContent == 4) {
                    if (sumOfCrossSize >= size) {
                        this.f3948a.setFlexLines(k(flexLinesInternal, size, sumOfCrossSize));
                        return;
                    }
                    int size5 = (size - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                    ArrayList arrayList2 = new ArrayList();
                    com.google.android.flexbox.b bVar3 = new com.google.android.flexbox.b();
                    bVar3.g = size5;
                    for (com.google.android.flexbox.b bVar4 : flexLinesInternal) {
                        arrayList2.add(bVar3);
                        arrayList2.add(bVar4);
                        arrayList2.add(bVar3);
                    }
                    this.f3948a.setFlexLines(arrayList2);
                    return;
                }
                if (alignContent == 5 && sumOfCrossSize < size) {
                    float size6 = (size - sumOfCrossSize) / flexLinesInternal.size();
                    int size7 = flexLinesInternal.size();
                    float f2 = 0.0f;
                    while (i4 < size7) {
                        com.google.android.flexbox.b bVar5 = flexLinesInternal.get(i4);
                        float f3 = bVar5.g + size6;
                        if (i4 == flexLinesInternal.size() - 1) {
                            f3 += f2;
                            f2 = 0.0f;
                        }
                        int iRound = Math.round(f3);
                        f2 += f3 - iRound;
                        if (f2 > 1.0f) {
                            iRound++;
                            f2 -= 1.0f;
                        } else if (f2 < -1.0f) {
                            iRound--;
                            f2 += 1.0f;
                        }
                        bVar5.g = iRound;
                        i4++;
                    }
                }
            }
        }
    }

    void p(int i, int i2) {
        q(i, i2, 0);
    }

    void q(int i, int i2, int i3) {
        int size;
        int paddingLeft;
        int paddingRight;
        r(this.f3948a.getFlexItemCount());
        if (i3 >= this.f3948a.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.f3948a.getFlexDirection();
        int flexDirection2 = this.f3948a.getFlexDirection();
        if (flexDirection2 == 0 || flexDirection2 == 1) {
            int mode = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
            if (mode != 1073741824) {
                size = this.f3948a.getLargestMainSize();
            }
            paddingLeft = this.f3948a.getPaddingLeft();
            paddingRight = this.f3948a.getPaddingRight();
        } else {
            if (flexDirection2 != 2 && flexDirection2 != 3) {
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
            }
            int mode2 = View.MeasureSpec.getMode(i2);
            size = View.MeasureSpec.getSize(i2);
            if (mode2 != 1073741824) {
                size = this.f3948a.getLargestMainSize();
            }
            paddingLeft = this.f3948a.getPaddingTop();
            paddingRight = this.f3948a.getPaddingBottom();
        }
        int i4 = paddingLeft + paddingRight;
        int[] iArr = this.f3950c;
        int i5 = iArr != null ? iArr[i3] : 0;
        List<com.google.android.flexbox.b> flexLinesInternal = this.f3948a.getFlexLinesInternal();
        int size2 = flexLinesInternal.size();
        for (int i6 = i5; i6 < size2; i6++) {
            com.google.android.flexbox.b bVar = flexLinesInternal.get(i6);
            if (bVar.f3947e < size) {
                v(i, i2, bVar, size, i4, false);
            } else {
                S(i, i2, bVar, size, i4, false);
            }
        }
    }

    void s(int i) {
        int[] iArr = this.f3950c;
        if (iArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.f3950c = new int[i];
        } else if (iArr.length < i) {
            int length = iArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f3950c = Arrays.copyOf(iArr, i);
        }
    }

    void t(int i) {
        long[] jArr = this.f3951d;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.f3951d = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f3951d = Arrays.copyOf(jArr, i);
        }
    }

    void u(int i) {
        long[] jArr = this.f3952e;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.f3952e = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f3952e = Arrays.copyOf(jArr, i);
        }
    }

    int w(long j) {
        return (int) (j >> 32);
    }

    int x(long j) {
        return (int) j;
    }
}
