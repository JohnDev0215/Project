package b8;

import A7.m;
import F8.AbstractC0287w;
import F8.Y;
import N7.n;
import N7.s;
import Q7.C0661n;
import Q7.InterfaceC0648a;
import Q7.InterfaceC0657j;
import Q7.L;
import Q7.N;
import Q7.O;
import T7.H;
import T7.I;
import T7.J;
import T7.Q;
import T7.u;
import Z7.x;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import c8.C1038c;
import g8.AbstractC1328r;
import java.util.ArrayList;
import m7.i;
import o8.C2058c;
import o8.C2061f;

/* JADX INFO: renamed from: b8.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1012g extends H implements InterfaceC1006a {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final boolean f12331M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final i f12332N;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1012g(InterfaceC0657j interfaceC0657j, R7.h hVar, int i7, C0661n c0661n, boolean z6, C2061f c2061f, N n10, L l, int i10, boolean z10, i iVar) {
        super(interfaceC0657j, l, hVar, i7, c0661n, z6, c2061f, i10, n10, false, false, false, false, false);
        if (interfaceC0657j == null) {
            K0(0);
            throw null;
        }
        if (hVar == null) {
            K0(1);
            throw null;
        }
        if (i7 == 0) {
            K0(2);
            throw null;
        }
        if (c0661n == null) {
            K0(3);
            throw null;
        }
        if (c2061f == null) {
            K0(4);
            throw null;
        }
        if (n10 == null) {
            K0(5);
            throw null;
        }
        if (i10 == 0) {
            K0(6);
            throw null;
        }
        this.f12331M = z10;
        this.f12332N = iVar;
    }

    public static /* synthetic */ void K0(int i7) {
        String str = i7 != 21 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i7 != 21 ? 3 : 2];
        switch (i7) {
            case 1:
            case 8:
                objArr[0] = "annotations";
                break;
            case 2:
            case 9:
                objArr[0] = "modality";
                break;
            case 3:
            case 10:
                objArr[0] = "visibility";
                break;
            case 4:
            case 11:
                objArr[0] = "name";
                break;
            case 5:
            case 12:
            case 18:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 6:
            case 16:
                objArr[0] = "kind";
                break;
            case 7:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 13:
                objArr[0] = "newOwner";
                break;
            case 14:
                objArr[0] = "newModality";
                break;
            case 15:
                objArr[0] = "newVisibility";
                break;
            case 17:
                objArr[0] = "newName";
                break;
            case 19:
                objArr[0] = "enhancedValueParameterTypes";
                break;
            case 20:
                objArr[0] = "enhancedReturnType";
                break;
            case 21:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaPropertyDescriptor";
                break;
            case 22:
                objArr[0] = "inType";
                break;
        }
        if (i7 != 21) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaPropertyDescriptor";
        } else {
            objArr[1] = "enhance";
        }
        switch (i7) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                objArr[2] = "create";
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 19:
            case 20:
                objArr[2] = "enhance";
                break;
            case 21:
                break;
            case 22:
                objArr[2] = "setInType";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 == 21) {
            throw new IllegalStateException(str2);
        }
    }

    public static C1012g k1(InterfaceC0657j interfaceC0657j, C1038c c1038c, C0661n c0661n, boolean z6, C2061f c2061f, V7.g gVar, boolean z10) {
        if (interfaceC0657j == null) {
            K0(7);
            throw null;
        }
        if (c2061f != null) {
            return new C1012g(interfaceC0657j, c1038c, 1, c0661n, z6, c2061f, gVar, null, 1, z10, null);
        }
        K0(11);
        throw null;
    }

    @Override // T7.H, Q7.U
    public final boolean G() {
        AbstractC0287w abstractC0287wB = b();
        if (this.f12331M) {
            m.f(abstractC0287wB, "type");
            if (((N7.i.E(abstractC0287wB) || s.a(abstractC0287wB)) && !Y.e(abstractC0287wB)) || N7.i.C(abstractC0287wB, n.f4849f)) {
                R7.i iVar = AbstractC1328r.f15028a;
                C2058c c2058c = x.f10974p;
                m.e(c2058c, "ENHANCED_NULLABILITY_ANNOTATION");
                if (!G8.g.u(abstractC0287wB, c2058c) || N7.i.C(abstractC0287wB, n.f4849f)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // T7.S, Q7.InterfaceC0649b
    public final boolean T() {
        return false;
    }

    @Override // T7.H
    public final H e1(InterfaceC0657j interfaceC0657j, int i7, C0661n c0661n, L l, int i10, C2061f c2061f) {
        O o = N.c;
        if (interfaceC0657j == null) {
            K0(13);
            throw null;
        }
        if (i7 == 0) {
            K0(14);
            throw null;
        }
        if (c0661n == null) {
            K0(15);
            throw null;
        }
        if (i10 == 0) {
            K0(16);
            throw null;
        }
        if (c2061f == null) {
            K0(17);
            throw null;
        }
        return new C1012g(interfaceC0657j, k(), i7, c0661n, this.f8801r, c2061f, o, l, i10, this.f12331M, this.f12332N);
    }

    @Override // T7.H
    public final void i1(AbstractC0287w abstractC0287w) {
    }

    @Override // T7.H, Q7.InterfaceC0649b
    public final Object w(InterfaceC0648a interfaceC0648a) {
        i iVar = this.f12332N;
        if (iVar == null || !((InterfaceC0648a) iVar.l).equals(interfaceC0648a)) {
            return null;
        }
        return iVar.f19259m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [A7.o, z7.a] */
    @Override // b8.InterfaceC1006a
    public final InterfaceC1006a y(AbstractC0287w abstractC0287w, ArrayList arrayList, AbstractC0287w abstractC0287w2, i iVar) {
        I i7;
        u uVar;
        J j3;
        L lB1 = b1() == this ? null : b1();
        C1012g c1012g = new C1012g(o(), k(), h(), e(), this.f8801r, getName(), g(), lB1, N(), this.f12331M, iVar);
        I i10 = this.f8797I;
        if (i10 != null) {
            i7 = i;
            I i11 = new I(c1012g, i10.k(), i10.h(), i10.e(), i10.f8773q, i10.f8774r, i10.f8777u, N(), lB1 == null ? null : lB1.c(), i10.g());
            i7.f8780x = i10.f8780x;
            i7.f8810y = abstractC0287w2;
        } else {
            i7 = null;
        }
        J j10 = this.f8798J;
        if (j10 != null) {
            j3 = j;
            J j11 = new J(c1012g, j10.k(), j10.h(), j10.e(), j10.f8773q, j10.f8774r, j10.f8777u, N(), lB1 == null ? null : lB1.d(), j10.g());
            j3.f8780x = j3.f8780x;
            Q q4 = (Q) j10.D0().get(0);
            if (q4 == null) {
                J.K0(6);
                throw null;
            }
            j3.f8812y = q4;
            uVar = null;
        } else {
            uVar = null;
            j3 = null;
        }
        c1012g.g1(i7, j3, this.f8799K, this.f8800L);
        ?? r22 = this.f8803t;
        if (r22 != 0) {
            c1012g.h1(this.f8802s, r22);
        }
        c1012g.h0(q());
        c1012g.j1(abstractC0287w2, t(), this.f8794F, abstractC0287w == null ? uVar : r8.m.k(this, abstractC0287w, R7.g.f7535a), n7.u.l);
        return c1012g;
    }
}
