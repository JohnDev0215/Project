package T7;

import F8.AbstractC0268c;
import F8.AbstractC0287w;
import F8.T;
import F8.W;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0657j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import o8.C2061f;
import v8.AbstractC2564d;
import z8.C2793a;
import z8.C2794b;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public InterfaceC0657j f8781a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8782b;
    public C0661n c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8783e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final u f8786h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C2061f f8787i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AbstractC0287w f8788j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ H f8789k;
    public Q7.L d = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public T f8784f = T.f2453a;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f8785g = true;

    public G(H h10) {
        this.f8789k = h10;
        this.f8781a = h10.o();
        this.f8782b = h10.h();
        this.c = h10.e();
        this.f8783e = h10.N();
        this.f8786h = h10.f8794F;
        this.f8787i = h10.getName();
        this.f8788j = h10.b();
    }

    public static /* synthetic */ void a(int i7) {
        String str = (i7 == 1 || i7 == 2 || i7 == 3 || i7 == 5 || i7 == 7 || i7 == 9 || i7 == 11 || i7 == 19 || i7 == 13 || i7 == 14 || i7 == 16 || i7 == 17) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 1 || i7 == 2 || i7 == 3 || i7 == 5 || i7 == 7 || i7 == 9 || i7 == 11 || i7 == 19 || i7 == 13 || i7 == 14 || i7 == 16 || i7 == 17) ? 2 : 3];
        switch (i7) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            case 14:
            case 16:
            case 17:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
                break;
            case 4:
                objArr[0] = "type";
                break;
            case 6:
                objArr[0] = "modality";
                break;
            case 8:
                objArr[0] = "visibility";
                break;
            case 10:
                objArr[0] = "kind";
                break;
            case 12:
                objArr[0] = "typeParameters";
                break;
            case 15:
                objArr[0] = "substitution";
                break;
            case 18:
                objArr[0] = "name";
                break;
            default:
                objArr[0] = "owner";
                break;
        }
        if (i7 == 1) {
            objArr[1] = "setOwner";
        } else if (i7 == 2) {
            objArr[1] = "setOriginal";
        } else if (i7 == 3) {
            objArr[1] = "setPreserveSourceElement";
        } else if (i7 == 5) {
            objArr[1] = "setReturnType";
        } else if (i7 == 7) {
            objArr[1] = "setModality";
        } else if (i7 == 9) {
            objArr[1] = "setVisibility";
        } else if (i7 == 11) {
            objArr[1] = "setKind";
        } else if (i7 == 19) {
            objArr[1] = "setName";
        } else if (i7 == 13) {
            objArr[1] = "setTypeParameters";
        } else if (i7 == 14) {
            objArr[1] = "setDispatchReceiverParameter";
        } else if (i7 == 16) {
            objArr[1] = "setSubstitution";
        } else if (i7 != 17) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
        } else {
            objArr[1] = "setCopyOverrides";
        }
        switch (i7) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 9:
            case 11:
            case 13:
            case 14:
            case 16:
            case 17:
            case 19:
                break;
            case 4:
                objArr[2] = "setReturnType";
                break;
            case 6:
                objArr[2] = "setModality";
                break;
            case 8:
                objArr[2] = "setVisibility";
                break;
            case 10:
                objArr[2] = "setKind";
                break;
            case 12:
                objArr[2] = "setTypeParameters";
                break;
            case 15:
                objArr[2] = "setSubstitution";
                break;
            case 18:
                objArr[2] = "setName";
                break;
            default:
                objArr[2] = "setOwner";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 1 && i7 != 2 && i7 != 3 && i7 != 5 && i7 != 7 && i7 != 9 && i7 != 11 && i7 != 19 && i7 != 13 && i7 != 14 && i7 != 16 && i7 != 17) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [A7.o, z7.a] */
    public final H b() {
        u uVar;
        u uVar2;
        I i7;
        J j3;
        ?? r02;
        u uVar3;
        u uVar4;
        H h10 = this.f8789k;
        h10.getClass();
        InterfaceC0657j interfaceC0657j = this.f8781a;
        int i10 = this.f8782b;
        C0661n c0661n = this.c;
        Q7.L l = this.d;
        int i11 = this.f8783e;
        Q7.O o = Q7.N.c;
        H hE1 = h10.e1(interfaceC0657j, i10, c0661n, l, i11, this.f8787i);
        List listT = h10.t();
        ArrayList arrayList = new ArrayList(((ArrayList) listT).size());
        W wU = AbstractC0268c.u(listT, this.f8784f, hE1, arrayList);
        AbstractC0287w abstractC0287w = this.f8788j;
        AbstractC0287w abstractC0287wI = wU.i(3, abstractC0287w);
        if (abstractC0287wI != null) {
            AbstractC0287w abstractC0287wI2 = wU.i(2, abstractC0287w);
            if (abstractC0287wI2 != null) {
                hE1.i1(abstractC0287wI2);
            }
            u uVar5 = this.f8786h;
            if (uVar5 != null) {
                u uVarF = uVar5.f(wU);
                uVar = uVarF != null ? uVarF : null;
            }
            u uVar6 = h10.f8795G;
            if (uVar6 != null) {
                AbstractC0287w abstractC0287wI3 = wU.i(2, uVar6.b());
                if (abstractC0287wI3 == null) {
                    uVar4 = null;
                } else {
                    uVar6.c1();
                    uVar4 = new u(hE1, new C2794b(hE1, abstractC0287wI3), uVar6.k());
                }
                uVar2 = uVar4;
            } else {
                uVar2 = null;
            }
            ArrayList arrayList2 = new ArrayList();
            for (u uVar7 : h10.f8793E) {
                AbstractC0287w abstractC0287wI4 = wU.i(2, uVar7.b());
                if (abstractC0287wI4 == null) {
                    uVar3 = null;
                } else {
                    C2061f c2061fA1 = ((C2793a) uVar7.c1()).a1();
                    uVar7.c1();
                    uVar3 = new u(hE1, new C2793a(hE1, abstractC0287wI4, c2061fA1), uVar7.k());
                }
                if (uVar3 != null) {
                    arrayList2.add(uVar3);
                }
            }
            hE1.j1(abstractC0287wI, arrayList, uVar, uVar2, arrayList2);
            I i12 = h10.f8797I;
            if (i12 == null) {
                i7 = null;
            } else {
                R7.h hVarK = i12.k();
                int i13 = this.f8782b;
                C0661n c0661nE = h10.f8797I.e();
                if (this.f8783e == 2 && AbstractC0662o.e(AbstractC0662o.f(c0661nE.f7187a.c()))) {
                    c0661nE = AbstractC0662o.f7194h;
                }
                C0661n c0661n2 = c0661nE;
                I i14 = h10.f8797I;
                boolean z6 = i14.f8773q;
                int i15 = this.f8783e;
                Q7.L l10 = this.d;
                i7 = new I(hE1, hVarK, i13, c0661n2, z6, i14.f8774r, i14.f8777u, i15, l10 == null ? null : l10.c(), o);
            }
            if (i7 != null) {
                I i16 = h10.f8797I;
                AbstractC0287w abstractC0287w2 = i16.f8810y;
                i7.f8780x = H.f1(wU, i16);
                i7.f1(abstractC0287w2 != null ? wU.i(3, abstractC0287w2) : null);
            }
            J j10 = h10.f8798J;
            if (j10 == null) {
                j3 = null;
            } else {
                R7.h hVarK2 = j10.k();
                int i17 = this.f8782b;
                C0661n c0661nE2 = h10.f8798J.e();
                if (this.f8783e == 2 && AbstractC0662o.e(AbstractC0662o.f(c0661nE2.f7187a.c()))) {
                    c0661nE2 = AbstractC0662o.f7194h;
                }
                C0661n c0661n3 = c0661nE2;
                J j11 = h10.f8798J;
                boolean z10 = j11.f8773q;
                int i18 = this.f8783e;
                Q7.L l11 = this.d;
                j3 = new J(hE1, hVarK2, i17, c0661n3, z10, j11.f8774r, j11.f8777u, i18, l11 == null ? null : l11.d(), o);
            }
            if (j3 != null) {
                List listG1 = t.g1(j3, h10.f8798J.D0(), wU, false, false, null);
                if (listG1 == null) {
                    listG1 = Collections.singletonList(J.e1(j3, AbstractC2564d.e(this.f8781a).m(), ((Q) h10.f8798J.D0().get(0)).k()));
                }
                if (listG1.size() != 1) {
                    throw new IllegalStateException();
                }
                j3.f8780x = H.f1(wU, h10.f8798J);
                Q q4 = (Q) listG1.get(0);
                if (q4 == null) {
                    J.K0(6);
                    throw null;
                }
                j3.f8812y = q4;
            }
            r rVar = h10.f8799K;
            r rVar2 = rVar == null ? null : new r(rVar.k(), hE1);
            r rVar3 = h10.f8800L;
            hE1.g1(i7, j3, rVar2, rVar3 != null ? new r(rVar3.k(), hE1) : null);
            if (this.f8785g) {
                O8.i iVar = new O8.i();
                Iterator it = h10.q().iterator();
                while (it.hasNext()) {
                    iVar.add(((Q7.L) it.next()).f(wU));
                }
                hE1.f8806w = iVar;
            }
            if (!h10.G() || (r02 = h10.f8803t) == 0) {
                return hE1;
            }
            hE1.h1(h10.f8802s, r02);
            return hE1;
        }
        return null;
    }
}
