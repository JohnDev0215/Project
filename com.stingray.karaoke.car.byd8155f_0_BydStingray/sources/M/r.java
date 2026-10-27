package M;

import A.C0004b;
import F0.AbstractC0214l0;
import F0.W0;
import H7.H;
import K.C0331b0;
import K.G0;
import K.P;
import N0.C0411f;
import N0.F;
import N0.G;
import O.K;
import T0.C0775a;
import T0.C0781g;
import a.AbstractC0904a;
import android.graphics.PointF;
import android.os.CancellationSignal;
import android.view.inputmethod.DeleteGesture;
import android.view.inputmethod.DeleteRangeGesture;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InsertGesture;
import android.view.inputmethod.JoinOrSplitGesture;
import android.view.inputmethod.PreviewableHandwritingGesture;
import android.view.inputmethod.RemoveSpaceGesture;
import android.view.inputmethod.SelectGesture;
import android.view.inputmethod.SelectRangeGesture;
import n0.AbstractC1861K;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f4149a = new r();

    private final void C(C0331b0 c0331b0, SelectGesture selectGesture, K k10) {
        if (k10 != null) {
            long jE = H.E(c0331b0, AbstractC1861K.G(selectGesture.getSelectionArea()), G(selectGesture.getGranularity()));
            C0331b0 c0331b02 = k10.d;
            if (c0331b02 != null) {
                c0331b02.f(jE);
            }
            C0331b0 c0331b03 = k10.d;
            if (c0331b03 != null) {
                c0331b03.e(N0.K.f4356b);
            }
            if (N0.K.b(jE)) {
                return;
            }
            k10.p(false);
            k10.n(P.l);
        }
    }

    private final void D(D d, SelectGesture selectGesture, C c) {
        AbstractC1861K.G(selectGesture.getSelectionArea());
        G(selectGesture.getGranularity());
        throw null;
    }

    private final void E(C0331b0 c0331b0, SelectRangeGesture selectRangeGesture, K k10) {
        if (k10 != null) {
            long jM = H.m(c0331b0, AbstractC1861K.G(selectRangeGesture.getSelectionStartArea()), AbstractC1861K.G(selectRangeGesture.getSelectionEndArea()), G(selectRangeGesture.getGranularity()));
            C0331b0 c0331b02 = k10.d;
            if (c0331b02 != null) {
                c0331b02.f(jM);
            }
            C0331b0 c0331b03 = k10.d;
            if (c0331b03 != null) {
                c0331b03.e(N0.K.f4356b);
            }
            if (N0.K.b(jM)) {
                return;
            }
            k10.p(false);
            k10.n(P.l);
        }
    }

    private final void F(D d, SelectRangeGesture selectRangeGesture, C c) {
        AbstractC1861K.G(selectRangeGesture.getSelectionStartArea());
        AbstractC1861K.G(selectRangeGesture.getSelectionEndArea());
        G(selectRangeGesture.getGranularity());
        throw null;
    }

    private final int G(int i7) {
        return i7 != 1 ? 0 : 1;
    }

    private final int a(D d, HandwritingGesture handwritingGesture) {
        throw null;
    }

    private final int b(HandwritingGesture handwritingGesture, InterfaceC2782k interfaceC2782k) {
        String fallbackText = handwritingGesture.getFallbackText();
        if (fallbackText == null) {
            return 3;
        }
        interfaceC2782k.b(new C0775a(fallbackText, 1));
        return 5;
    }

    private final int c(C0331b0 c0331b0, DeleteGesture deleteGesture, C0411f c0411f, InterfaceC2782k interfaceC2782k) {
        int iG = G(deleteGesture.getGranularity());
        long jE = H.E(c0331b0, AbstractC1861K.G(deleteGesture.getDeletionArea()), iG);
        if (N0.K.b(jE)) {
            return f4149a.b(p.i(deleteGesture), interfaceC2782k);
        }
        h(jE, c0411f, iG == 1, interfaceC2782k);
        return 1;
    }

    private final int d(D d, DeleteGesture deleteGesture, C c) {
        G(deleteGesture.getGranularity());
        AbstractC1861K.G(deleteGesture.getDeletionArea());
        throw null;
    }

    private final int e(C0331b0 c0331b0, DeleteRangeGesture deleteRangeGesture, C0411f c0411f, InterfaceC2782k interfaceC2782k) {
        int iG = G(deleteRangeGesture.getGranularity());
        long jM = H.m(c0331b0, AbstractC1861K.G(deleteRangeGesture.getDeletionStartArea()), AbstractC1861K.G(deleteRangeGesture.getDeletionEndArea()), iG);
        if (N0.K.b(jM)) {
            return f4149a.b(p.i(deleteRangeGesture), interfaceC2782k);
        }
        h(jM, c0411f, iG == 1, interfaceC2782k);
        return 1;
    }

    private final int f(D d, DeleteRangeGesture deleteRangeGesture, C c) {
        G(deleteRangeGesture.getGranularity());
        AbstractC1861K.G(deleteRangeGesture.getDeletionStartArea());
        AbstractC1861K.G(deleteRangeGesture.getDeletionEndArea());
        throw null;
    }

    private final void g(D d, long j3, boolean z6) {
        if (!z6) {
            throw null;
        }
        throw null;
    }

    private final void h(long j3, C0411f c0411f, boolean z6, InterfaceC2782k interfaceC2782k) {
        if (z6) {
            int i7 = N0.K.c;
            int iCharCount = (int) (j3 >> 32);
            int iCharCount2 = (int) (j3 & 4294967295L);
            int iCodePointBefore = iCharCount > 0 ? Character.codePointBefore(c0411f, iCharCount) : 10;
            int iCodePointAt = iCharCount2 < c0411f.l.length() ? Character.codePointAt(c0411f, iCharCount2) : 10;
            if (H.J(iCodePointBefore) && (H.I(iCodePointAt) || H.H(iCodePointAt))) {
                do {
                    iCharCount -= Character.charCount(iCodePointBefore);
                    if (iCharCount == 0) {
                        break;
                    } else {
                        iCodePointBefore = Character.codePointBefore(c0411f, iCharCount);
                    }
                } while (H.J(iCodePointBefore));
                j3 = AbstractC0904a.q(iCharCount, iCharCount2);
            } else if (H.J(iCodePointAt) && (H.I(iCodePointBefore) || H.H(iCodePointBefore))) {
                do {
                    iCharCount2 += Character.charCount(iCodePointAt);
                    if (iCharCount2 == c0411f.l.length()) {
                        break;
                    } else {
                        iCodePointAt = Character.codePointAt(c0411f, iCharCount2);
                    }
                } while (H.J(iCodePointAt));
                j3 = AbstractC0904a.q(iCharCount, iCharCount2);
            }
        }
        int i10 = (int) (4294967295L & j3);
        interfaceC2782k.b(new s(new T0.i[]{new T0.x(i10, i10), new C0781g(N0.K.c(j3), 0)}));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int k(K.C0331b0 r7, android.view.inputmethod.InsertGesture r8, F0.W0 r9, z7.InterfaceC2782k r10) {
        /*
            r6 = this;
            if (r9 != 0) goto Lb
            android.view.inputmethod.HandwritingGesture r7 = M.p.i(r8)
            int r6 = r6.b(r7, r10)
            return r6
        Lb:
            android.graphics.PointF r0 = M.p.c(r8)
            float r1 = r0.x
            float r0 = r0.y
            long r0 = h6.e.a(r1, r0)
            K.G0 r2 = r7.d()
            r3 = 1
            r4 = -1
            if (r2 == 0) goto L49
            N0.H r2 = r2.f3161a
            N0.o r2 = r2.f4349b
            C0.r r5 = r7.c()
            if (r5 == 0) goto L49
            long r0 = r5.B(r0)
            int r9 = H7.H.C(r2, r0, r9)
            if (r9 != r4) goto L34
            goto L49
        L34:
            float r5 = r2.d(r9)
            float r9 = r2.b(r9)
            float r9 = r9 + r5
            r5 = 1073741824(0x40000000, float:2.0)
            float r9 = r9 / r5
            long r0 = m0.c.a(r0, r9, r3)
            int r9 = r2.e(r0)
            goto L4a
        L49:
            r9 = r4
        L4a:
            if (r9 == r4) goto L63
            K.G0 r7 = r7.d()
            if (r7 == 0) goto L5b
            N0.H r7 = r7.f3161a
            boolean r7 = H7.H.n(r7, r9)
            if (r7 != r3) goto L5b
            goto L63
        L5b:
            java.lang.String r7 = M.p.m(r8)
            r6.m(r9, r7, r10)
            return r3
        L63:
            android.view.inputmethod.HandwritingGesture r7 = M.p.i(r8)
            int r6 = r6.b(r7, r10)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: M.r.k(K.b0, android.view.inputmethod.InsertGesture, F0.W0, z7.k):int");
    }

    private final int l(D d, InsertGesture insertGesture, C c, W0 w02) {
        PointF insertionPoint = insertGesture.getInsertionPoint();
        h6.e.a(insertionPoint.x, insertionPoint.y);
        throw null;
    }

    private final void m(int i7, String str, InterfaceC2782k interfaceC2782k) {
        interfaceC2782k.b(new s(new T0.i[]{new T0.x(i7, i7), new C0775a(str, 1)}));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int n(K.C0331b0 r11, android.view.inputmethod.JoinOrSplitGesture r12, N0.C0411f r13, F0.W0 r14, z7.InterfaceC2782k r15) {
        /*
            r10 = this;
            if (r14 != 0) goto Lb
            android.view.inputmethod.HandwritingGesture r11 = M.p.i(r12)
            int r10 = r10.b(r11, r15)
            return r10
        Lb:
            android.graphics.PointF r0 = M.p.d(r12)
            float r1 = r0.x
            float r0 = r0.y
            long r0 = h6.e.a(r1, r0)
            K.G0 r2 = r11.d()
            r3 = 1
            r4 = -1
            if (r2 == 0) goto L49
            N0.H r2 = r2.f3161a
            N0.o r2 = r2.f4349b
            C0.r r5 = r11.c()
            if (r5 == 0) goto L49
            long r0 = r5.B(r0)
            int r14 = H7.H.C(r2, r0, r14)
            if (r14 != r4) goto L34
            goto L49
        L34:
            float r5 = r2.d(r14)
            float r14 = r2.b(r14)
            float r14 = r14 + r5
            r5 = 1073741824(0x40000000, float:2.0)
            float r14 = r14 / r5
            long r0 = m0.c.a(r0, r14, r3)
            int r14 = r2.e(r0)
            goto L4a
        L49:
            r14 = r4
        L4a:
            if (r14 == r4) goto La5
            K.G0 r11 = r11.d()
            if (r11 == 0) goto L5b
            N0.H r11 = r11.f3161a
            boolean r11 = H7.H.n(r11, r14)
            if (r11 != r3) goto L5b
            goto La5
        L5b:
            r11 = r14
        L5c:
            if (r11 <= 0) goto L6f
            int r12 = java.lang.Character.codePointBefore(r13, r11)
            boolean r0 = H7.H.I(r12)
            if (r0 != 0) goto L69
            goto L6f
        L69:
            int r12 = java.lang.Character.charCount(r12)
            int r11 = r11 - r12
            goto L5c
        L6f:
            java.lang.String r12 = r13.l
            int r12 = r12.length()
            if (r14 >= r12) goto L88
            int r12 = java.lang.Character.codePointAt(r13, r14)
            boolean r0 = H7.H.I(r12)
            if (r0 != 0) goto L82
            goto L88
        L82:
            int r12 = java.lang.Character.charCount(r12)
            int r14 = r14 + r12
            goto L6f
        L88:
            long r5 = a.AbstractC0904a.q(r11, r14)
            boolean r11 = N0.K.b(r5)
            if (r11 == 0) goto L9d
            r11 = 32
            long r11 = r5 >> r11
            int r11 = (int) r11
            java.lang.String r12 = " "
            r10.m(r11, r12, r15)
            goto La4
        L9d:
            r8 = 0
            r4 = r10
            r7 = r13
            r9 = r15
            r4.h(r5, r7, r8, r9)
        La4:
            return r3
        La5:
            android.view.inputmethod.HandwritingGesture r11 = M.p.i(r12)
            int r10 = r10.b(r11, r15)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: M.r.n(K.b0, android.view.inputmethod.JoinOrSplitGesture, N0.f, F0.W0, z7.k):int");
    }

    private final int o(D d, JoinOrSplitGesture joinOrSplitGesture, C c, W0 w02) {
        throw null;
    }

    private final int p(C0331b0 c0331b0, RemoveSpaceGesture removeSpaceGesture, C0411f c0411f, W0 w02, InterfaceC2782k interfaceC2782k) {
        long jF;
        int i7;
        G0 g0D = c0331b0.d();
        N0.H h10 = g0D != null ? g0D.f3161a : null;
        PointF startPoint = removeSpaceGesture.getStartPoint();
        long jA = h6.e.a(startPoint.x, startPoint.y);
        PointF endPoint = removeSpaceGesture.getEndPoint();
        long jA2 = h6.e.a(endPoint.x, endPoint.y);
        C0.r rVarC = c0331b0.c();
        if (h10 == null || rVarC == null) {
            jF = N0.K.f4356b;
        } else {
            long jB = rVarC.B(jA);
            long jB2 = rVarC.B(jA2);
            N0.o oVar = h10.f4349b;
            int iC = H.C(oVar, jB, w02);
            int iC2 = H.C(oVar, jB2, w02);
            if (iC != -1) {
                if (iC2 != -1) {
                    iC = Math.min(iC, iC2);
                }
                iC2 = iC;
            } else if (iC2 == -1) {
                jF = N0.K.f4356b;
            }
            float fB = (oVar.b(iC2) + oVar.d(iC2)) / 2;
            jF = oVar.f(new m0.d(Math.min(m0.c.d(jB), m0.c.d(jB2)), fB - 0.1f, Math.max(m0.c.d(jB), m0.c.d(jB2)), fB + 0.1f), 0, F.f4338a);
        }
        if (N0.K.b(jF)) {
            return f4149a.b(p.i(removeSpaceGesture), interfaceC2782k);
        }
        A7.x xVar = new A7.x();
        xVar.l = -1;
        A7.x xVar2 = new A7.x();
        xVar2.l = -1;
        String strC = new Q8.g("\\s+").c(c0411f.subSequence(N0.K.e(jF), N0.K.d(jF)).l, new C0004b(xVar, 21, xVar2));
        int i10 = xVar.l;
        if (i10 == -1 || (i7 = xVar2.l) == -1) {
            return b(p.i(removeSpaceGesture), interfaceC2782k);
        }
        int i11 = (int) (jF >> 32);
        String strSubstring = strC.substring(i10, strC.length() - (N0.K.c(jF) - xVar2.l));
        A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        interfaceC2782k.b(new s(new T0.i[]{new T0.x(i11 + i10, i11 + i7), new C0775a(strSubstring, 1)}));
        return 1;
    }

    private final int q(D d, RemoveSpaceGesture removeSpaceGesture, C c, W0 w02) {
        throw null;
    }

    private final int r(C0331b0 c0331b0, SelectGesture selectGesture, K k10, InterfaceC2782k interfaceC2782k) {
        long jE = H.E(c0331b0, AbstractC1861K.G(selectGesture.getSelectionArea()), G(selectGesture.getGranularity()));
        if (N0.K.b(jE)) {
            return f4149a.b(p.i(selectGesture), interfaceC2782k);
        }
        v(jE, k10, interfaceC2782k);
        return 1;
    }

    private final int s(D d, SelectGesture selectGesture, C c) {
        AbstractC1861K.G(selectGesture.getSelectionArea());
        G(selectGesture.getGranularity());
        throw null;
    }

    private final int t(C0331b0 c0331b0, SelectRangeGesture selectRangeGesture, K k10, InterfaceC2782k interfaceC2782k) {
        long jM = H.m(c0331b0, AbstractC1861K.G(selectRangeGesture.getSelectionStartArea()), AbstractC1861K.G(selectRangeGesture.getSelectionEndArea()), G(selectRangeGesture.getGranularity()));
        if (N0.K.b(jM)) {
            return f4149a.b(p.i(selectRangeGesture), interfaceC2782k);
        }
        v(jM, k10, interfaceC2782k);
        return 1;
    }

    private final int u(D d, SelectRangeGesture selectRangeGesture, C c) {
        AbstractC1861K.G(selectRangeGesture.getSelectionStartArea());
        AbstractC1861K.G(selectRangeGesture.getSelectionEndArea());
        G(selectRangeGesture.getGranularity());
        throw null;
    }

    private final void v(long j3, K k10, InterfaceC2782k interfaceC2782k) {
        int i7 = N0.K.c;
        interfaceC2782k.b(new T0.x((int) (j3 >> 32), (int) (j3 & 4294967295L)));
        if (k10 != null) {
            k10.f(true);
        }
    }

    private final void w(C0331b0 c0331b0, DeleteGesture deleteGesture, K k10) {
        if (k10 != null) {
            long jE = H.E(c0331b0, AbstractC1861K.G(deleteGesture.getDeletionArea()), G(deleteGesture.getGranularity()));
            C0331b0 c0331b02 = k10.d;
            if (c0331b02 != null) {
                c0331b02.e(jE);
            }
            C0331b0 c0331b03 = k10.d;
            if (c0331b03 != null) {
                c0331b03.f(N0.K.f4356b);
            }
            if (N0.K.b(jE)) {
                return;
            }
            k10.p(false);
            k10.n(P.l);
        }
    }

    private final void x(D d, DeleteGesture deleteGesture, C c) {
        AbstractC1861K.G(deleteGesture.getDeletionArea());
        G(deleteGesture.getGranularity());
        throw null;
    }

    private final void y(C0331b0 c0331b0, DeleteRangeGesture deleteRangeGesture, K k10) {
        if (k10 != null) {
            long jM = H.m(c0331b0, AbstractC1861K.G(deleteRangeGesture.getDeletionStartArea()), AbstractC1861K.G(deleteRangeGesture.getDeletionEndArea()), G(deleteRangeGesture.getGranularity()));
            C0331b0 c0331b02 = k10.d;
            if (c0331b02 != null) {
                c0331b02.e(jM);
            }
            C0331b0 c0331b03 = k10.d;
            if (c0331b03 != null) {
                c0331b03.f(N0.K.f4356b);
            }
            if (N0.K.b(jM)) {
                return;
            }
            k10.p(false);
            k10.n(P.l);
        }
    }

    private final void z(D d, DeleteRangeGesture deleteRangeGesture, C c) {
        AbstractC1861K.G(deleteRangeGesture.getDeletionStartArea());
        AbstractC1861K.G(deleteRangeGesture.getDeletionEndArea());
        G(deleteRangeGesture.getGranularity());
        throw null;
    }

    public final boolean A(C0331b0 c0331b0, PreviewableHandwritingGesture previewableHandwritingGesture, K k10, CancellationSignal cancellationSignal) {
        G g7;
        C0411f c0411f = c0331b0.f3227j;
        if (c0411f == null) {
            return false;
        }
        G0 g0D = c0331b0.d();
        if (!c0411f.equals((g0D == null || (g7 = g0D.f3161a.f4348a) == null) ? null : g7.f4340a)) {
            return false;
        }
        if (p.r(previewableHandwritingGesture)) {
            C(c0331b0, p.j(previewableHandwritingGesture), k10);
        } else if (AbstractC0214l0.q(previewableHandwritingGesture)) {
            w(c0331b0, AbstractC0214l0.g(previewableHandwritingGesture), k10);
        } else if (AbstractC0214l0.u(previewableHandwritingGesture)) {
            E(c0331b0, AbstractC0214l0.l(previewableHandwritingGesture), k10);
        } else {
            if (!AbstractC0214l0.w(previewableHandwritingGesture)) {
                return false;
            }
            y(c0331b0, AbstractC0214l0.h(previewableHandwritingGesture), k10);
        }
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.setOnCancelListener(new K0.g(k10, 1));
        return true;
    }

    public final boolean B(D d, PreviewableHandwritingGesture previewableHandwritingGesture, C c, CancellationSignal cancellationSignal) {
        if (p.r(previewableHandwritingGesture)) {
            D(d, p.j(previewableHandwritingGesture), c);
        } else if (AbstractC0214l0.q(previewableHandwritingGesture)) {
            x(d, AbstractC0214l0.g(previewableHandwritingGesture), c);
        } else if (AbstractC0214l0.u(previewableHandwritingGesture)) {
            F(d, AbstractC0214l0.l(previewableHandwritingGesture), c);
        } else {
            if (!AbstractC0214l0.w(previewableHandwritingGesture)) {
                return false;
            }
            z(d, AbstractC0214l0.h(previewableHandwritingGesture), c);
        }
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.setOnCancelListener(new q());
        return true;
    }

    public final int i(C0331b0 c0331b0, HandwritingGesture handwritingGesture, K k10, W0 w02, InterfaceC2782k interfaceC2782k) {
        G g7;
        C0411f c0411f = c0331b0.f3227j;
        if (c0411f == null) {
            return 3;
        }
        G0 g0D = c0331b0.d();
        if (!c0411f.equals((g0D == null || (g7 = g0D.f3161a.f4348a) == null) ? null : g7.f4340a)) {
            return 3;
        }
        if (p.r(handwritingGesture)) {
            return r(c0331b0, p.j(handwritingGesture), k10, interfaceC2782k);
        }
        if (AbstractC0214l0.q(handwritingGesture)) {
            return c(c0331b0, AbstractC0214l0.g(handwritingGesture), c0411f, interfaceC2782k);
        }
        if (AbstractC0214l0.u(handwritingGesture)) {
            return t(c0331b0, AbstractC0214l0.l(handwritingGesture), k10, interfaceC2782k);
        }
        if (AbstractC0214l0.w(handwritingGesture)) {
            return e(c0331b0, AbstractC0214l0.h(handwritingGesture), c0411f, interfaceC2782k);
        }
        if (AbstractC0214l0.C(handwritingGesture)) {
            return n(c0331b0, AbstractC0214l0.j(handwritingGesture), c0411f, w02, interfaceC2782k);
        }
        if (AbstractC0214l0.y(handwritingGesture)) {
            return k(c0331b0, AbstractC0214l0.i(handwritingGesture), w02, interfaceC2782k);
        }
        if (AbstractC0214l0.A(handwritingGesture)) {
            return p(c0331b0, AbstractC0214l0.k(handwritingGesture), c0411f, w02, interfaceC2782k);
        }
        return 2;
    }

    public final int j(D d, HandwritingGesture handwritingGesture, C c, W0 w02) {
        if (p.r(handwritingGesture)) {
            return s(d, p.j(handwritingGesture), c);
        }
        if (AbstractC0214l0.q(handwritingGesture)) {
            return d(d, AbstractC0214l0.g(handwritingGesture), c);
        }
        if (AbstractC0214l0.u(handwritingGesture)) {
            return u(d, AbstractC0214l0.l(handwritingGesture), c);
        }
        if (AbstractC0214l0.w(handwritingGesture)) {
            return f(d, AbstractC0214l0.h(handwritingGesture), c);
        }
        if (AbstractC0214l0.C(handwritingGesture)) {
            return o(d, AbstractC0214l0.j(handwritingGesture), c, w02);
        }
        if (AbstractC0214l0.y(handwritingGesture)) {
            return l(d, AbstractC0214l0.i(handwritingGesture), c, w02);
        }
        if (AbstractC0214l0.A(handwritingGesture)) {
            return q(d, AbstractC0214l0.k(handwritingGesture), c, w02);
        }
        return 2;
    }
}
