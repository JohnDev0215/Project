package T7;

import F8.AbstractC0287w;
import F8.W;
import Q7.C0661n;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0665s;
import Q7.InterfaceC0666t;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.util.List;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public class K extends t {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K(InterfaceC0657j interfaceC0657j, K k10, R7.h hVar, C2061f c2061f, int i7, Q7.N n10) {
        super(i7, interfaceC0657j, k10, n10, hVar, c2061f);
        if (interfaceC0657j == null) {
            K0(0);
            throw null;
        }
        if (hVar == null) {
            K0(1);
            throw null;
        }
        if (c2061f == null) {
            K0(2);
            throw null;
        }
        if (i7 == 0) {
            K0(3);
            throw null;
        }
        if (n10 != null) {
        } else {
            K0(4);
            throw null;
        }
    }

    public static /* synthetic */ void K0(int i7) {
        String str = (i7 == 13 || i7 == 18 || i7 == 23 || i7 == 24 || i7 == 29 || i7 == 30) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 13 || i7 == 18 || i7 == 23 || i7 == 24 || i7 == 29 || i7 == 30) ? 2 : 3];
        switch (i7) {
            case 1:
            case 6:
            case 27:
                objArr[0] = "annotations";
                break;
            case 2:
            case 7:
                objArr[0] = "name";
                break;
            case 3:
            case 8:
            case 26:
                objArr[0] = "kind";
                break;
            case 4:
            case 9:
            case 28:
                objArr[0] = BYDAutoMultimediaDevice.PARAM_SOURCE;
                break;
            case 5:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 10:
            case 15:
            case 20:
                objArr[0] = "typeParameters";
                break;
            case 11:
            case 16:
            case 21:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 12:
            case 17:
            case 22:
                objArr[0] = "visibility";
                break;
            case 13:
            case 18:
            case 23:
            case 24:
            case 29:
            case 30:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl";
                break;
            case 14:
            case 19:
                objArr[0] = "contextReceiverParameters";
                break;
            case 25:
                objArr[0] = "newOwner";
                break;
        }
        if (i7 == 13 || i7 == 18 || i7 == 23) {
            objArr[1] = "initialize";
        } else if (i7 == 24) {
            objArr[1] = "getOriginal";
        } else if (i7 == 29) {
            objArr[1] = "copy";
        } else if (i7 != 30) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl";
        } else {
            objArr[1] = "newCopyBuilder";
        }
        switch (i7) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                objArr[2] = "create";
                break;
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
                objArr[2] = "initialize";
                break;
            case 13:
            case 18:
            case 23:
            case 24:
            case 29:
            case 30:
                break;
            case 25:
            case 26:
            case 27:
            case 28:
                objArr[2] = "createSubstitutedCopy";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 13 && i7 != 18 && i7 != 23 && i7 != 24 && i7 != 29 && i7 != 30) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static K n1(InterfaceC0652e interfaceC0652e, C2061f c2061f, int i7, Q7.N n10) {
        R7.f fVar = R7.g.f7535a;
        if (interfaceC0652e == null) {
            K0(5);
            throw null;
        }
        if (c2061f == null) {
            K0(7);
            throw null;
        }
        if (i7 == 0) {
            K0(8);
            throw null;
        }
        if (n10 != null) {
            return new K(interfaceC0652e, null, fVar, c2061f, i7, n10);
        }
        K0(9);
        throw null;
    }

    @Override // T7.t
    public t e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, Q7.N n10, R7.h hVar, C2061f c2061f) {
        if (interfaceC0657j == null) {
            K0(25);
            throw null;
        }
        if (i7 == 0) {
            K0(26);
            throw null;
        }
        if (hVar == null) {
            K0(27);
            throw null;
        }
        K k10 = (K) interfaceC0666t;
        if (c2061f == null) {
            c2061f = getName();
        }
        return new K(interfaceC0657j, k10, hVar, c2061f, i7, n10);
    }

    @Override // T7.AbstractC0795n
    /* JADX INFO: renamed from: o1, reason: merged with bridge method [inline-methods] */
    public final K b1() {
        K k10 = (K) super.b1();
        if (k10 != null) {
            return k10;
        }
        K0(24);
        throw null;
    }

    @Override // T7.t
    /* JADX INFO: renamed from: p1, reason: merged with bridge method [inline-methods] */
    public final K h1(u uVar, u uVar2, List list, List list2, List list3, AbstractC0287w abstractC0287w, int i7, C0661n c0661n) {
        if (list == null) {
            K0(14);
            throw null;
        }
        if (list2 == null) {
            K0(15);
            throw null;
        }
        if (list3 == null) {
            K0(16);
            throw null;
        }
        if (c0661n != null) {
            return q1(uVar, uVar2, list, list2, list3, abstractC0287w, i7, c0661n, null);
        }
        K0(17);
        throw null;
    }

    public K q1(u uVar, u uVar2, List list, List list2, List list3, AbstractC0287w abstractC0287w, int i7, C0661n c0661n, n7.v vVar) {
        if (list == null) {
            K0(19);
            throw null;
        }
        if (list2 == null) {
            K0(20);
            throw null;
        }
        if (list3 == null) {
            K0(21);
            throw null;
        }
        if (c0661n != null) {
            super.h1(uVar, uVar2, list, list2, list3, abstractC0287w, i7, c0661n);
            return this;
        }
        K0(22);
        throw null;
    }

    @Override // T7.t, Q7.InterfaceC0666t
    public InterfaceC0665s v0() {
        return i1(W.f2454b);
    }
}
