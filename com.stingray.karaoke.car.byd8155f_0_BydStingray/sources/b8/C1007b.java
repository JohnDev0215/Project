package b8;

import F8.AbstractC0287w;
import Q7.InterfaceC0648a;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import Q7.N;
import T7.C0790i;
import T7.t;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.ArrayList;
import l9.l;
import m7.i;
import n7.u;
import o8.C2061f;
import r8.m;

/* JADX INFO: renamed from: b8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1007b extends C0790i implements InterfaceC1006a {

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public Boolean f12325Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public Boolean f12326R;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1007b(InterfaceC0652e interfaceC0652e, C1007b c1007b, R7.h hVar, boolean z6, int i7, N n10) {
        super(interfaceC0652e, c1007b, hVar, z6, i7, n10);
        if (interfaceC0652e == null) {
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
        if (n10 == null) {
            K0(3);
            throw null;
        }
        this.f12325Q = null;
        this.f12326R = null;
    }

    public static /* synthetic */ void K0(int i7) {
        String str = (i7 == 11 || i7 == 18) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 11 || i7 == 18) ? 2 : 3];
        switch (i7) {
            case 1:
            case 5:
            case 9:
            case 15:
                objArr[0] = "annotations";
                break;
            case 2:
            case 8:
            case 13:
                objArr[0] = "kind";
                break;
            case 3:
            case 6:
            case 10:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 4:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 7:
            case 12:
                objArr[0] = "newOwner";
                break;
            case 11:
            case 18:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor";
                break;
            case 14:
                objArr[0] = "sourceElement";
                break;
            case 16:
                objArr[0] = "enhancedValueParameterTypes";
                break;
            case 17:
                objArr[0] = "enhancedReturnType";
                break;
        }
        if (i7 == 11) {
            objArr[1] = "createSubstitutedCopy";
        } else if (i7 != 18) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor";
        } else {
            objArr[1] = "enhance";
        }
        switch (i7) {
            case 4:
            case 5:
            case 6:
                objArr[2] = "createJavaConstructor";
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 11:
            case 18:
                break;
            case 12:
            case 13:
            case 14:
            case 15:
                objArr[2] = "createDescriptor";
                break;
            case 16:
            case 17:
                objArr[2] = "enhance";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 11 && i7 != 18) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static C1007b t1(InterfaceC0652e interfaceC0652e, R7.h hVar, boolean z6, V7.g gVar) {
        if (interfaceC0652e != null) {
            return new C1007b(interfaceC0652e, null, hVar, z6, 1, gVar);
        }
        K0(4);
        throw null;
    }

    @Override // T7.t, Q7.InterfaceC0649b
    public final boolean T() {
        return this.f12326R.booleanValue();
    }

    @Override // T7.C0790i, T7.t
    public final /* bridge */ /* synthetic */ t e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, N n10, R7.h hVar, C2061f c2061f) {
        return u1(interfaceC0657j, interfaceC0666t, i7, hVar, n10);
    }

    @Override // T7.t
    public final void k1(boolean z6) {
        this.f12325Q = Boolean.valueOf(z6);
    }

    @Override // T7.t
    public final void l1(boolean z6) {
        this.f12326R = Boolean.valueOf(z6);
    }

    @Override // T7.C0790i
    /* JADX INFO: renamed from: n1 */
    public final /* bridge */ /* synthetic */ C0790i e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, N n10, R7.h hVar, C2061f c2061f) {
        return u1(interfaceC0657j, interfaceC0666t, i7, hVar, n10);
    }

    public final C1007b u1(InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, int i7, R7.h hVar, N n10) {
        if (interfaceC0657j == null) {
            K0(7);
            throw null;
        }
        if (i7 == 0) {
            K0(8);
            throw null;
        }
        if (hVar == null) {
            K0(9);
            throw null;
        }
        if (n10 == null) {
            K0(10);
            throw null;
        }
        if (i7 != 1 && i7 != 4) {
            throw new IllegalStateException("Attempt at creating a constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + interfaceC0657j + "\nkind: " + M5.b.u(i7));
        }
        InterfaceC0652e interfaceC0652e = (InterfaceC0652e) interfaceC0657j;
        C1007b c1007b = (C1007b) interfaceC0666t;
        if (i7 == 0) {
            K0(13);
            throw null;
        }
        C1007b c1007b2 = new C1007b(interfaceC0652e, c1007b, hVar, this.f8843P, i7, n10);
        Boolean bool = this.f12325Q;
        bool.getClass();
        c1007b2.f12325Q = bool;
        Boolean bool2 = this.f12326R;
        bool2.getClass();
        c1007b2.f12326R = bool2;
        return c1007b2;
    }

    @Override // b8.InterfaceC1006a
    public final InterfaceC1006a y(AbstractC0287w abstractC0287w, ArrayList arrayList, AbstractC0287w abstractC0287w2, i iVar) {
        C1007b c1007bU1 = u1(o(), null, N(), k(), g());
        c1007bU1.h1(abstractC0287w == null ? null : m.k(c1007bU1, abstractC0287w, R7.g.f7535a), this.f8903v, u.l, t(), l.H(arrayList, D0(), c1007bU1), abstractC0287w2, h(), e());
        if (iVar != null) {
            c1007bU1.j1((InterfaceC0648a) iVar.l, iVar.f19259m);
        }
        return c1007bU1;
    }
}
