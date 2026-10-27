package T7;

import F8.AbstractC0287w;
import F8.W;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0659l;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import o8.AbstractC2063h;
import o8.C2061f;
import z8.C2795c;
import z8.C2797e;
import z8.InterfaceC2796d;

/* JADX INFO: loaded from: classes.dex */
public final class u extends AbstractC0794m implements Q7.J {
    public final /* synthetic */ int o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final InterfaceC0657j f8908p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final InterfaceC2796d f8909q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(InterfaceC0652e interfaceC0652e) {
        super(R7.g.f7535a, AbstractC2063h.d);
        if (interfaceC0652e == null) {
            K0(0);
            throw null;
        }
        this.f8908p = interfaceC0652e;
        this.f8909q = new C2795c(interfaceC0652e);
    }

    public static /* synthetic */ void K0(int i7) {
        String str = (i7 == 1 || i7 == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 1 || i7 == 2) ? 2 : 3];
        if (i7 == 1 || i7 == 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazyClassReceiverParameterDescriptor";
        } else if (i7 != 3) {
            objArr[0] = "descriptor";
        } else {
            objArr[0] = "newOwner";
        }
        if (i7 == 1) {
            objArr[1] = "getValue";
        } else if (i7 != 2) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazyClassReceiverParameterDescriptor";
        } else {
            objArr[1] = "getContainingDeclaration";
        }
        if (i7 != 1 && i7 != 2) {
            if (i7 != 3) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "copy";
            }
        }
        String str2 = String.format(str, objArr);
        if (i7 != 1 && i7 != 2) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static /* synthetic */ void L0(int i7) {
        String str = (i7 == 7 || i7 == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 7 || i7 == 8) ? 2 : 3];
        switch (i7) {
            case 1:
            case 4:
                objArr[0] = "value";
                break;
            case 2:
            case 5:
                objArr[0] = "annotations";
                break;
            case 3:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 6:
                objArr[0] = "name";
                break;
            case 7:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ReceiverParameterDescriptorImpl";
                break;
            case 9:
                objArr[0] = "newOwner";
                break;
            case 10:
                objArr[0] = "outType";
                break;
        }
        if (i7 == 7) {
            objArr[1] = "getValue";
        } else if (i7 != 8) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ReceiverParameterDescriptorImpl";
        } else {
            objArr[1] = "getContainingDeclaration";
        }
        switch (i7) {
            case 7:
            case 8:
                break;
            case 9:
                objArr[2] = "copy";
                break;
            case 10:
                objArr[2] = "setOutType";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 7 && i7 != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static /* synthetic */ void b1(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                i10 = 2;
                break;
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = "substitutor";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractReceiverParameterDescriptor";
                break;
            default:
                objArr[0] = "annotations";
                break;
        }
        switch (i7) {
            case 4:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 5:
                objArr[1] = "getTypeParameters";
                break;
            case 6:
                objArr[1] = "getType";
                break;
            case 7:
                objArr[1] = "getValueParameters";
                break;
            case 8:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 9:
                objArr[1] = "getVisibility";
                break;
            case 10:
                objArr[1] = "getOriginal";
                break;
            case 11:
                objArr[1] = "getSource";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractReceiverParameterDescriptor";
                break;
        }
        switch (i7) {
            case 3:
                objArr[2] = "substitute";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    @Override // Q7.InterfaceC0649b
    public final u B() {
        return null;
    }

    @Override // Q7.InterfaceC0649b
    public final List D0() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        b1(7);
        throw null;
    }

    @Override // Q7.InterfaceC0649b
    public final u K() {
        return null;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.o(this, obj);
    }

    @Override // Q7.InterfaceC0649b
    public final boolean T() {
        return false;
    }

    @Override // T7.AbstractC0794m, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0649b b1() {
        return this;
    }

    @Override // A9.b, z8.InterfaceC2796d
    public final AbstractC0287w b() {
        AbstractC0287w abstractC0287wB = c1().b();
        if (abstractC0287wB != null) {
            return abstractC0287wB;
        }
        b1(6);
        throw null;
    }

    public final InterfaceC2796d c1() {
        switch (this.o) {
            case 0:
                C2795c c2795c = (C2795c) this.f8909q;
                if (c2795c != null) {
                    return c2795c;
                }
                K0(1);
                throw null;
            default:
                A9.b bVar = (A9.b) this.f8909q;
                if (bVar != null) {
                    return bVar;
                }
                L0(7);
                throw null;
        }
    }

    @Override // Q7.P
    /* JADX INFO: renamed from: d1, reason: merged with bridge method [inline-methods] */
    public final u f(W w10) {
        if (w10 == null) {
            b1(3);
            throw null;
        }
        if (w10.f2455a.e()) {
            return this;
        }
        AbstractC0287w abstractC0287wI = o() instanceof InterfaceC0652e ? w10.i(3, b()) : w10.i(1, b());
        if (abstractC0287wI == null) {
            return null;
        }
        return abstractC0287wI == b() ? this : new u(o(), new C2797e(abstractC0287wI), k());
    }

    @Override // Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        C0661n c0661n = AbstractC0662o.f7192f;
        if (c0661n != null) {
            return c0661n;
        }
        b1(9);
        throw null;
    }

    @Override // Q7.InterfaceC0658k
    public final Q7.N g() {
        return Q7.N.c;
    }

    @Override // Q7.InterfaceC0657j
    public final InterfaceC0657j o() {
        switch (this.o) {
            case 0:
                InterfaceC0652e interfaceC0652e = (InterfaceC0652e) this.f8908p;
                if (interfaceC0652e != null) {
                    return interfaceC0652e;
                }
                K0(2);
                throw null;
            default:
                InterfaceC0657j interfaceC0657j = this.f8908p;
                if (interfaceC0657j != null) {
                    return interfaceC0657j;
                }
                L0(8);
                throw null;
        }
    }

    @Override // Q7.InterfaceC0649b
    public final AbstractC0287w p() {
        return b();
    }

    @Override // Q7.InterfaceC0649b
    public final Collection q() {
        Set setEmptySet = Collections.emptySet();
        if (setEmptySet != null) {
            return setEmptySet;
        }
        b1(8);
        throw null;
    }

    @Override // Q7.InterfaceC0649b
    public final List t() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        b1(5);
        throw null;
    }

    @Override // T7.AbstractC0794m, A9.b
    public String toString() {
        switch (this.o) {
            case 0:
                return "class " + ((InterfaceC0652e) this.f8908p).getName() + "::this";
            default:
                return super.toString();
        }
    }

    @Override // T7.AbstractC0794m, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0657j b1() {
        return this;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public u(InterfaceC0657j interfaceC0657j, A9.b bVar, R7.h hVar) {
        this(interfaceC0657j, bVar, hVar, AbstractC2063h.d);
        if (interfaceC0657j == null) {
            L0(0);
            throw null;
        }
        if (hVar != null) {
        } else {
            L0(2);
            throw null;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(InterfaceC0657j interfaceC0657j, A9.b bVar, R7.h hVar, C2061f c2061f) {
        super(hVar, c2061f);
        if (interfaceC0657j == null) {
            L0(3);
            throw null;
        }
        if (hVar == null) {
            L0(5);
            throw null;
        }
        if (c2061f != null) {
            this.f8908p = interfaceC0657j;
            this.f8909q = bVar;
            return;
        }
        L0(6);
        throw null;
    }
}
