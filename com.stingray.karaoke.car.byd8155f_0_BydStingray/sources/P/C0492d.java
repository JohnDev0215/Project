package P;

import E0.H;
import androidx.compose.material.ripple.RippleNode;
import h2.AbstractC1347d;
import java.lang.reflect.InvocationTargetException;
import l3.C1764u;
import n0.C1891t;
import n0.InterfaceC1892u;
import p0.C2071b;
import u.C2484x;
import x.C2629d;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: P.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0492d extends RippleNode {

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final C2484x f5223H;

    public C0492d(C.k kVar, boolean z6, float f10, InterfaceC1892u interfaceC1892u, InterfaceC2772a interfaceC2772a) {
        super(kVar, z6, f10, interfaceC1892u, interfaceC2772a);
        this.f5223H = new C2484x();
    }

    @Override // g0.n
    public final void A0() {
        this.f5223H.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0056  */
    @Override // androidx.compose.material.ripple.RippleNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void H0(C.m r19, long r20, float r22) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            u.x r2 = r0.f5223H
            java.lang.Object[] r3 = r2.f22644b
            java.lang.Object[] r4 = r2.c
            long[] r5 = r2.f22643a
            int r6 = r5.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L5b
            r8 = 0
        L12:
            r9 = r5[r8]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L56
            int r11 = r8 - r6
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = 0
        L2c:
            if (r13 >= r11) goto L54
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L50
            int r14 = r8 << 3
            int r14 = r14 + r13
            r15 = r3[r14]
            r14 = r4[r14]
            P.p r14 = (P.p) r14
            C.m r15 = (C.m) r15
            java.lang.Boolean r15 = java.lang.Boolean.TRUE
            T.e0 r7 = r14.f5247k
            r7.setValue(r15)
            m7.q r7 = m7.q.f19267a
            S8.o r14 = r14.f5245i
            r14.T(r7)
        L50:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L2c
        L54:
            if (r11 != r12) goto L5b
        L56:
            if (r8 == r6) goto L5b
            int r8 = r8 + 1
            goto L12
        L5b:
            r3 = 0
            boolean r4 = r0.f11530z
            if (r4 == 0) goto L68
            long r5 = r1.f834a
            m0.c r7 = new m0.c
            r7.<init>(r5)
            goto L69
        L68:
            r7 = r3
        L69:
            P.p r5 = new P.p
            r6 = r22
            r5.<init>(r7, r6, r4)
            r2.i(r1, r5)
            S8.w r2 = r18.v0()
            P.c r4 = new P.c
            r4.<init>(r5, r0, r1, r3)
            r1 = 3
            S8.AbstractC0727y.w(r2, r3, r3, r4, r1)
            E0.AbstractC0156f.m(r18)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: P.C0492d.H0(C.m, long, float):void");
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [A7.o, z7.a] */
    @Override // androidx.compose.material.ripple.RippleNode
    public final void I0(H h10) {
        float f10;
        long[] jArr;
        Object[] objArr;
        Object[] objArr2;
        float f11;
        long[] jArr2;
        Object[] objArr3;
        Object[] objArr4;
        int i7;
        float f12 = ((h) this.B.a()).d;
        if (f12 == 0.0f) {
            return;
        }
        C2484x c2484x = this.f5223H;
        Object[] objArr5 = c2484x.f22644b;
        Object[] objArr6 = c2484x.c;
        long[] jArr3 = c2484x.f22643a;
        int length = jArr3.length - 2;
        if (length < 0) {
            return;
        }
        int i10 = 0;
        while (true) {
            long j3 = jArr3[i10];
            if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8;
                int i12 = 8 - ((~(i10 - length)) >>> 31);
                int i13 = 0;
                while (i13 < i12) {
                    if ((255 & j3) < 128) {
                        int i14 = (i10 << 3) + i13;
                        Object obj = objArr5[i14];
                        p pVar = (p) objArr6[i14];
                        long jB = C1891t.b(f12, J0());
                        Float f13 = pVar.d;
                        C2071b c2071b = h10.l;
                        if (f13 == null) {
                            long jE = c2071b.e();
                            float f14 = q.f5248a;
                            f11 = f12;
                            pVar.d = Float.valueOf(Math.max(m0.f.d(jE), m0.f.b(jE)) * 0.3f);
                        } else {
                            f11 = f12;
                        }
                        long[] jArr4 = jArr3;
                        if (pVar.f5239a == null) {
                            objArr3 = objArr5;
                            pVar.f5239a = new m0.c(c2071b.S());
                        } else {
                            objArr3 = objArr5;
                        }
                        if (pVar.f5241e == null) {
                            pVar.f5241e = new m0.c(h6.e.a(m0.f.d(c2071b.e()) / 2.0f, m0.f.b(c2071b.e()) / 2.0f));
                        }
                        float fFloatValue = (!((Boolean) pVar.f5247k.getValue()).booleanValue() || ((Boolean) pVar.f5246j.getValue()).booleanValue()) ? ((Number) pVar.f5242f.d()).floatValue() : 1.0f;
                        Float f15 = pVar.d;
                        A7.m.c(f15);
                        objArr4 = objArr6;
                        float fO = AbstractC2673a.O(f15.floatValue(), pVar.f5240b, ((Number) pVar.f5243g.d()).floatValue());
                        m0.c cVar = pVar.f5239a;
                        A7.m.c(cVar);
                        float fD = m0.c.d(cVar.f18880a);
                        m0.c cVar2 = pVar.f5241e;
                        A7.m.c(cVar2);
                        float fD2 = m0.c.d(cVar2.f18880a);
                        C2629d c2629d = pVar.f5244h;
                        jArr2 = jArr4;
                        float fO2 = AbstractC2673a.O(fD, fD2, ((Number) c2629d.d()).floatValue());
                        m0.c cVar3 = pVar.f5239a;
                        A7.m.c(cVar3);
                        i7 = length;
                        float fE = m0.c.e(cVar3.f18880a);
                        m0.c cVar4 = pVar.f5241e;
                        A7.m.c(cVar4);
                        long jA = h6.e.a(fO2, AbstractC2673a.O(fE, m0.c.e(cVar4.f18880a), ((Number) c2629d.d()).floatValue()));
                        long jB2 = C1891t.b(C1891t.d(jB) * fFloatValue, jB);
                        if (pVar.c) {
                            float fD3 = m0.f.d(c2071b.e());
                            float fB = m0.f.b(c2071b.e());
                            h3.y yVar = c2071b.f20638m;
                            long jY = yVar.y();
                            yVar.m().m();
                            try {
                                ((h3.y) ((C1764u) yVar.f15499m).f18608m).m().i(0.0f, 0.0f, fD3, fB, 1);
                                h10.Q(jB2, fO, (120 & 4) != 0 ? h10.S() : jA, 1.0f, (120 & 16) != 0 ? p0.g.f20641a : null, null, 3);
                            } finally {
                                AbstractC1347d.o(yVar, jY);
                            }
                        } else {
                            h10.Q(jB2, fO, (120 & 4) != 0 ? h10.S() : jA, 1.0f, (120 & 16) != 0 ? p0.g.f20641a : null, null, 3);
                        }
                    } else {
                        f11 = f12;
                        jArr2 = jArr3;
                        objArr3 = objArr5;
                        objArr4 = objArr6;
                        i7 = length;
                    }
                    j3 >>= 8;
                    i13++;
                    i11 = 8;
                    f12 = f11;
                    objArr5 = objArr3;
                    objArr6 = objArr4;
                    jArr3 = jArr2;
                    length = i7;
                }
                f10 = f12;
                jArr = jArr3;
                objArr = objArr5;
                objArr2 = objArr6;
                int i15 = length;
                if (i12 != i11) {
                    return;
                } else {
                    length = i15;
                }
            } else {
                f10 = f12;
                jArr = jArr3;
                objArr = objArr5;
                objArr2 = objArr6;
            }
            if (i10 == length) {
                return;
            }
            i10++;
            f12 = f10;
            objArr5 = objArr;
            objArr6 = objArr2;
            jArr3 = jArr;
        }
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public final void L0(C.m mVar) throws IllegalAccessException, InvocationTargetException {
        p pVar = (p) this.f5223H.e(mVar);
        if (pVar != null) {
            pVar.f5247k.setValue(Boolean.TRUE);
            pVar.f5245i.T(m7.q.f19267a);
        }
    }
}
