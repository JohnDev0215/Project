package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.f;
import com.google.zxing.common.h;
import com.google.zxing.i;
import com.google.zxing.j;
import com.google.zxing.qrcode.decoder.g;
import java.util.Map;

/* JADX INFO: compiled from: Detector.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.zxing.common.b f4523a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private j f4524b;

    public c(com.google.zxing.common.b bVar) {
        this.f4523a = bVar;
    }

    private float b(i iVar, i iVar2) {
        float fJ = j((int) iVar.c(), (int) iVar.d(), (int) iVar2.c(), (int) iVar2.d());
        float fJ2 = j((int) iVar2.c(), (int) iVar2.d(), (int) iVar.c(), (int) iVar.d());
        return Float.isNaN(fJ) ? fJ2 / 7.0f : Float.isNaN(fJ2) ? fJ / 7.0f : (fJ + fJ2) / 14.0f;
    }

    private static int c(i iVar, i iVar2, i iVar3, float f2) throws NotFoundException {
        int iC = ((com.google.zxing.common.l.a.c(i.b(iVar, iVar2) / f2) + com.google.zxing.common.l.a.c(i.b(iVar, iVar3) / f2)) / 2) + 7;
        int i = iC & 3;
        if (i == 0) {
            return iC + 1;
        }
        if (i == 2) {
            return iC - 1;
        }
        if (i != 3) {
            return iC;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static com.google.zxing.common.j d(i iVar, i iVar2, i iVar3, i iVar4, int i) {
        float fC;
        float fD;
        float f2;
        float f3 = i - 3.5f;
        if (iVar4 != null) {
            fC = iVar4.c();
            fD = iVar4.d();
            f2 = f3 - 3.0f;
        } else {
            fC = (iVar2.c() - iVar.c()) + iVar3.c();
            fD = (iVar2.d() - iVar.d()) + iVar3.d();
            f2 = f3;
        }
        return com.google.zxing.common.j.b(3.5f, 3.5f, f3, 3.5f, f2, f2, 3.5f, f3, iVar.c(), iVar.d(), iVar2.c(), iVar2.d(), fC, fD, iVar3.c(), iVar3.d());
    }

    private static com.google.zxing.common.b h(com.google.zxing.common.b bVar, com.google.zxing.common.j jVar, int i) throws NotFoundException {
        return h.b().d(bVar, i, i, jVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0087, code lost:
    
        if (r15 != r0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008f, code lost:
    
        return com.google.zxing.common.l.a.b(r19, r6, r1, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0090, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float i(int r18, int r19, int r20, int r21) {
        /*
            r17 = this;
            int r0 = r21 - r19
            int r0 = java.lang.Math.abs(r0)
            int r1 = r20 - r18
            int r1 = java.lang.Math.abs(r1)
            r3 = 1
            if (r0 <= r1) goto L11
            r0 = r3
            goto L12
        L11:
            r0 = 0
        L12:
            if (r0 == 0) goto L1d
            r4 = r18
            r1 = r19
            r6 = r20
            r5 = r21
            goto L25
        L1d:
            r1 = r18
            r4 = r19
            r5 = r20
            r6 = r21
        L25:
            int r7 = r5 - r1
            int r7 = java.lang.Math.abs(r7)
            int r8 = r6 - r4
            int r8 = java.lang.Math.abs(r8)
            int r9 = -r7
            r10 = 2
            int r9 = r9 / r10
            r11 = -1
            if (r1 >= r5) goto L39
            r12 = r3
            goto L3a
        L39:
            r12 = r11
        L3a:
            if (r4 >= r6) goto L3d
            r11 = r3
        L3d:
            int r5 = r5 + r12
            r13 = r1
            r14 = r4
            r15 = 0
        L41:
            if (r13 == r5) goto L82
            if (r0 == 0) goto L47
            r2 = r14
            goto L48
        L47:
            r2 = r13
        L48:
            if (r0 == 0) goto L4c
            r10 = r13
            goto L4d
        L4c:
            r10 = r14
        L4d:
            if (r15 != r3) goto L57
            r16 = r0
            r0 = r3
            r19 = r5
            r3 = r17
            goto L5e
        L57:
            r3 = r17
            r16 = r0
            r19 = r5
            r0 = 0
        L5e:
            com.google.zxing.common.b r5 = r3.f4523a
            boolean r2 = r5.d(r2, r10)
            if (r0 != r2) goto L70
            r0 = 2
            if (r15 != r0) goto L6e
            float r0 = com.google.zxing.common.l.a.b(r13, r14, r1, r4)
            return r0
        L6e:
            int r15 = r15 + 1
        L70:
            int r9 = r9 + r8
            if (r9 <= 0) goto L7a
            if (r14 == r6) goto L78
            int r14 = r14 + r11
            int r9 = r9 - r7
            goto L7a
        L78:
            r0 = 2
            goto L87
        L7a:
            int r13 = r13 + r12
            r5 = r19
            r0 = r16
            r3 = 1
            r10 = 2
            goto L41
        L82:
            r3 = r17
            r19 = r5
            r0 = r10
        L87:
            if (r15 != r0) goto L90
            r5 = r19
            float r0 = com.google.zxing.common.l.a.b(r5, r6, r1, r4)
            return r0
        L90:
            r0 = 2143289344(0x7fc00000, float:NaN)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.c.i(int, int, int, int):float");
    }

    private float j(int i, int i2, int i3, int i4) {
        float fK;
        float fH;
        float fI = i(i, i2, i3, i4);
        int iK = i - (i3 - i);
        int iH = 0;
        if (iK < 0) {
            fK = i / (i - iK);
            iK = 0;
        } else if (iK >= this.f4523a.k()) {
            fK = ((this.f4523a.k() - 1) - i) / (iK - i);
            iK = this.f4523a.k() - 1;
        } else {
            fK = 1.0f;
        }
        float f2 = i2;
        int i5 = (int) (f2 - ((i4 - i2) * fK));
        if (i5 < 0) {
            fH = f2 / (i2 - i5);
        } else if (i5 >= this.f4523a.h()) {
            fH = ((this.f4523a.h() - 1) - i2) / (i5 - i2);
            iH = this.f4523a.h() - 1;
        } else {
            iH = i5;
            fH = 1.0f;
        }
        return (fI + i(i, i2, (int) (i + ((iK - i) * fH)), iH)) - 1.0f;
    }

    protected final float a(i iVar, i iVar2, i iVar3) {
        return (b(iVar, iVar2) + b(iVar, iVar3)) / 2.0f;
    }

    public final f e(Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        j jVar = map == null ? null : (j) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        this.f4524b = jVar;
        return g(new FinderPatternFinder(this.f4523a, jVar).f(map));
    }

    protected final a f(float f2, int i, int i2, float f3) throws NotFoundException {
        int i3 = (int) (f3 * f2);
        int iMax = Math.max(0, i - i3);
        int iMin = Math.min(this.f4523a.k() - 1, i + i3) - iMax;
        float f4 = 3.0f * f2;
        if (iMin < f4) {
            throw NotFoundException.getNotFoundInstance();
        }
        int iMax2 = Math.max(0, i2 - i3);
        int iMin2 = Math.min(this.f4523a.h() - 1, i2 + i3) - iMax2;
        if (iMin2 >= f4) {
            return new b(this.f4523a, iMax, iMax2, iMin, iMin2, f2, this.f4524b).c();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected final f g(e eVar) throws NotFoundException, FormatException {
        d dVarB = eVar.b();
        d dVarC = eVar.c();
        d dVarA = eVar.a();
        float fA = a(dVarB, dVarC, dVarA);
        if (fA < 1.0f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int iC = c(dVarB, dVarC, dVarA, fA);
        g gVarG = g.g(iC);
        int iE = gVarG.e() - 7;
        a aVarF = null;
        if (gVarG.d().length > 0) {
            float fC = (dVarC.c() - dVarB.c()) + dVarA.c();
            float fD = (dVarC.d() - dVarB.d()) + dVarA.d();
            float f2 = 1.0f - (3.0f / iE);
            int iC2 = (int) (dVarB.c() + ((fC - dVarB.c()) * f2));
            int iD = (int) (dVarB.d() + (f2 * (fD - dVarB.d())));
            for (int i = 4; i <= 16; i <<= 1) {
                try {
                    aVarF = f(fA, iC2, iD, i);
                    break;
                } catch (NotFoundException unused) {
                }
            }
        }
        return new f(h(this.f4523a, d(dVarB, dVarC, dVarA, aVarF, iC), iC), aVarF == null ? new i[]{dVarA, dVarB, dVarC} : new i[]{dVarA, dVarB, dVarC, aVarF});
    }
}
