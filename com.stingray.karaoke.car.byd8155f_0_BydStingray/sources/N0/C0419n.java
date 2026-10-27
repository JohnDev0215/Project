package N0;

import a.AbstractC0904a;
import android.text.Layout;
import java.io.Serializable;
import l3.C1764u;
import n0.C1877f;
import n0.C1883l;
import p0.C2071b;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N0.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0419n extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4387m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f4388n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Serializable f4389p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f4390q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0419n(long j3, float[] fArr, A7.x xVar, A7.w wVar) {
        super(1);
        this.f4388n = j3;
        this.o = fArr;
        this.f4389p = xVar;
        this.f4390q = wVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        O0.x xVar;
        Layout layout;
        float fA;
        float fA2;
        switch (this.f4387m) {
            case 0:
                r rVar = (r) obj;
                int i7 = rVar.f4403b;
                long j3 = this.f4388n;
                int iE = i7 > K.e(j3) ? rVar.f4403b : K.e(j3);
                int iD = K.d(j3);
                int iD2 = rVar.c;
                if (iD2 >= iD) {
                    iD2 = K.d(j3);
                }
                long jQ = AbstractC0904a.q(rVar.b(iE), rVar.b(iD2));
                A7.x xVar2 = (A7.x) this.f4389p;
                int i10 = xVar2.l;
                C0406a c0406a = rVar.f4402a;
                int iE2 = K.e(jQ);
                int iD3 = K.d(jQ);
                O0.x xVar3 = c0406a.d;
                Layout layout2 = xVar3.f5050f;
                int length = layout2.getText().length();
                if (iE2 < 0) {
                    throw new IllegalArgumentException("startOffset must be > 0");
                }
                if (iE2 >= length) {
                    throw new IllegalArgumentException("startOffset must be less than text length");
                }
                if (iD3 <= iE2) {
                    throw new IllegalArgumentException("endOffset must be greater than startOffset");
                }
                if (iD3 > length) {
                    throw new IllegalArgumentException("endOffset must be smaller or equal to text length");
                }
                int i11 = (iD3 - iE2) * 4;
                float[] fArr = (float[]) this.o;
                if (fArr.length - i10 < i11) {
                    throw new IllegalArgumentException("array.size - arrayStart must be greater or equal than (endOffset - startOffset) * 4");
                }
                int lineForOffset = layout2.getLineForOffset(iE2);
                int lineForOffset2 = layout2.getLineForOffset(iD3 - 1);
                K0.j jVar = new K0.j(xVar3);
                if (lineForOffset <= lineForOffset2) {
                    while (true) {
                        int lineStart = layout2.getLineStart(lineForOffset);
                        int iF = xVar3.f(lineForOffset);
                        int iMax = Math.max(iE2, lineStart);
                        int iMin = Math.min(iD3, iF);
                        float fG = xVar3.g(lineForOffset);
                        float fE = xVar3.e(lineForOffset);
                        int i12 = i10;
                        int i13 = iE2;
                        int i14 = iD3;
                        boolean z6 = false;
                        boolean z10 = layout2.getParagraphDirection(lineForOffset) == 1;
                        int i15 = iMax;
                        int i16 = i12;
                        while (i15 < iMin) {
                            boolean zIsRtlCharAt = layout2.isRtlCharAt(i15);
                            if (!z10 || zIsRtlCharAt) {
                                xVar = xVar3;
                                if (z10 && zIsRtlCharAt) {
                                    z6 = false;
                                    float fA3 = jVar.a(i15, false, false, false);
                                    layout = layout2;
                                    fA = jVar.a(i15 + 1, true, true, false);
                                    fA2 = fA3;
                                } else {
                                    layout = layout2;
                                    z6 = false;
                                    if (z10 || !zIsRtlCharAt) {
                                        fA = jVar.a(i15, false, false, false);
                                        fA2 = jVar.a(i15 + 1, true, true, false);
                                    } else {
                                        fA2 = jVar.a(i15, false, false, true);
                                        fA = jVar.a(i15 + 1, true, true, true);
                                        z6 = false;
                                    }
                                }
                            } else {
                                xVar = xVar3;
                                fA = jVar.a(i15, z6, z6, true);
                                fA2 = jVar.a(i15 + 1, true, true, true);
                                layout = layout2;
                                z6 = false;
                            }
                            fArr[i16] = fA;
                            fArr[i16 + 1] = fG;
                            fArr[i16 + 2] = fA2;
                            fArr[i16 + 3] = fE;
                            i16 += 4;
                            i15++;
                            xVar3 = xVar;
                            layout2 = layout;
                        }
                        O0.x xVar4 = xVar3;
                        Layout layout3 = layout2;
                        if (lineForOffset != lineForOffset2) {
                            lineForOffset++;
                            i10 = i16;
                            iE2 = i13;
                            iD3 = i14;
                            xVar3 = xVar4;
                            layout2 = layout3;
                        }
                    }
                }
                int iC = (K.c(jQ) * 4) + xVar2.l;
                int i17 = xVar2.l;
                while (true) {
                    A7.w wVar = (A7.w) this.f4390q;
                    if (i17 >= iC) {
                        xVar2.l = iC;
                        wVar.l = c0406a.b() + wVar.l;
                        return m7.q.f19267a;
                    }
                    int i18 = i17 + 1;
                    float f10 = fArr[i18];
                    float f11 = wVar.l;
                    fArr[i18] = f10 + f11;
                    int i19 = i17 + 3;
                    fArr[i19] = fArr[i19] + f11;
                    i17 += 4;
                }
                break;
            default:
                E0.H h10 = (E0.H) obj;
                h10.a();
                m0.d dVar = (m0.d) this.o;
                A7.z zVar = (A7.z) this.f4389p;
                long j10 = this.f4388n;
                C1883l c1883l = (C1883l) this.f4390q;
                C2071b c2071b = h10.l;
                C1764u c1764u = (C1764u) c2071b.f20638m.f15499m;
                float f12 = dVar.f18882a;
                float f13 = dVar.f18883b;
                c1764u.X(f12, f13);
                try {
                    p0.d.Z(h10, (C1877f) zVar.l, 0L, j10, 0L, 0.0f, c1883l, 0, 890);
                    ((C1764u) c2071b.f20638m.f15499m).X(-f12, -f13);
                    return m7.q.f19267a;
                } catch (Throwable th) {
                    ((C1764u) c2071b.f20638m.f15499m).X(-f12, -f13);
                    throw th;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0419n(m0.d dVar, A7.z zVar, long j3, C1883l c1883l) {
        super(1);
        this.o = dVar;
        this.f4389p = zVar;
        this.f4388n = j3;
        this.f4390q = c1883l;
    }
}
