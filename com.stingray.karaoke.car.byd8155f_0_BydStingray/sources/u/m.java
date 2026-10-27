package U;

import E0.F;
import E0.Y;
import H7.H;
import T.B0;
import T.C0733c;
import T.C0754m0;
import T.v0;
import a.AbstractC0904a;
import java.util.ArrayList;
import l3.P;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class m extends C {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final m f8953e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final m f8955g;
    public final /* synthetic */ int c;
    public static final m d = new m(1, 2, 0);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final m f8954f = new m(1, 2, 2);

    static {
        int i7 = 1;
        f8953e = new m(i7, i7, 1);
        int i10 = 1;
        f8955g = new m(i10, i10, 3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(int i7, int i10, int i11) {
        super(i7, i10);
        this.c = i11;
    }

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        int iC;
        int iO;
        switch (this.c) {
            case 0:
                Object objA = ((InterfaceC2772a) zVar.g(0)).a();
                C0733c c0733c = (C0733c) zVar.g(1);
                zVar.f(0);
                A7.m.d(p10, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
                c0733c.getClass();
                b02.M(b02.c(c0733c), objA);
                p10.getClass();
                p10.n(objA);
                break;
            case 1:
                C0733c c0733c2 = (C0733c) zVar.g(0);
                int iF = zVar.f(0);
                p10.I();
                c0733c2.getClass();
                Object objW = b02.w(b02.c(c0733c2));
                p10.getClass();
                ((F) p10.f18538n).N(iF, (F) objW);
                break;
            case 2:
                Object objG = zVar.g(0);
                C0733c c0733c3 = (C0733c) zVar.g(1);
                int iF2 = zVar.f(0);
                if (objG instanceof v0) {
                    ((ArrayList) y10.c).add(((v0) objG).f8385a);
                }
                int iC2 = b02.c(c0733c3);
                int iG = b02.g(b02.F(iC2, iF2));
                Object[] objArr = b02.c;
                Object obj = objArr[iG];
                objArr[iG] = objG;
                if (obj instanceof v0) {
                    int iO2 = b02.o() - b02.F(iC2, iF2);
                    v0 v0Var = (v0) obj;
                    C0733c c0733c4 = v0Var.f8386b;
                    if (c0733c4 == null || !c0733c4.a()) {
                        iC = -1;
                        iO = -1;
                    } else {
                        iC = b02.c(c0733c4);
                        iO = b02.o() - b02.f(b02.f8158b, b02.p(b02.q(iC) + iC));
                    }
                    y10.h(iO2, iC, iO, v0Var.f8385a);
                } else if (obj instanceof C0754m0) {
                    ((C0754m0) obj).d();
                }
                break;
            default:
                Object objG2 = zVar.g(0);
                int iF3 = zVar.f(0);
                if (objG2 instanceof v0) {
                    ((ArrayList) y10.c).add(((v0) objG2).f8385a);
                }
                int iG2 = b02.g(b02.F(b02.f8172t, iF3));
                Object[] objArr2 = b02.c;
                Object obj2 = objArr2[iG2];
                objArr2[iG2] = objG2;
                if (obj2 instanceof v0) {
                    y10.h(b02.o() - b02.F(b02.f8172t, iF3), -1, -1, ((v0) obj2).f8385a);
                } else if (obj2 instanceof C0754m0) {
                    ((C0754m0) obj2).d();
                }
                break;
        }
    }

    @Override // U.C
    public final String b(int i7) {
        switch (this.c) {
            case 0:
                if (!H.y(i7, 0)) {
                    break;
                }
                break;
            case 1:
                if (!H.y(i7, 0)) {
                    break;
                }
                break;
            case 2:
                if (!H.y(i7, 0)) {
                    break;
                }
                break;
            default:
                if (!H.y(i7, 0)) {
                    break;
                }
                break;
        }
        return super.b(i7);
    }

    @Override // U.C
    public final String c(int i7) {
        switch (this.c) {
            case 0:
                if (!AbstractC0904a.B(i7, 0)) {
                    if (!AbstractC0904a.B(i7, 1)) {
                        break;
                    }
                }
                break;
            case 1:
                if (!AbstractC0904a.B(i7, 0)) {
                    break;
                }
                break;
            case 2:
                if (!AbstractC0904a.B(i7, 0)) {
                    if (!AbstractC0904a.B(i7, 1)) {
                        break;
                    }
                }
                break;
            default:
                if (!AbstractC0904a.B(i7, 0)) {
                    break;
                }
                break;
        }
        return super.c(i7);
    }
}
