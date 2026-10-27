package T7;

import F8.AbstractC0272g;
import F8.AbstractC0287w;
import Q7.InterfaceC0654g;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import r8.C2308a;
import r8.C2309b;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: T7.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0788g extends AbstractC0272g {
    public final Q7.O c;
    public final /* synthetic */ AbstractC0789h d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0788g(AbstractC0789h abstractC0789h, E8.o oVar, Q7.O o) {
        super(oVar);
        if (oVar == null) {
            h(0);
            throw null;
        }
        this.d = abstractC0789h;
        this.c = o;
    }

    public static /* synthetic */ void h(int i7) {
        String str = (i7 == 1 || i7 == 2 || i7 == 3 || i7 == 4 || i7 == 5 || i7 == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 1 || i7 == 2 || i7 == 3 || i7 == 4 || i7 == 5 || i7 == 8) ? 2 : 3];
        switch (i7) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor$TypeParameterTypeConstructor";
                break;
            case 6:
                objArr[0] = "type";
                break;
            case 7:
                objArr[0] = "supertypes";
                break;
            case 9:
                objArr[0] = "classifier";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        if (i7 == 1) {
            objArr[1] = "computeSupertypes";
        } else if (i7 == 2) {
            objArr[1] = "getParameters";
        } else if (i7 == 3) {
            objArr[1] = "getDeclarationDescriptor";
        } else if (i7 == 4) {
            objArr[1] = "getBuiltIns";
        } else if (i7 == 5) {
            objArr[1] = "getSupertypeLoopChecker";
        } else if (i7 != 8) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor$TypeParameterTypeConstructor";
        } else {
            objArr[1] = "processSupertypesWithoutCycles";
        }
        switch (i7) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
                break;
            case 6:
                objArr[2] = "reportSupertypeLoopError";
                break;
            case 7:
                objArr[2] = "processSupertypesWithoutCycles";
                break;
            case 9:
                objArr[2] = "isSameClassifier";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 1 && i7 != 2 && i7 != 3 && i7 != 4 && i7 != 5 && i7 != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // F8.AbstractC0272g
    public final Collection b() {
        List listD1 = this.d.d1();
        if (listD1 != null) {
            return listD1;
        }
        h(1);
        throw null;
    }

    @Override // F8.AbstractC0272g
    public final AbstractC0287w c() {
        return H8.i.c(H8.h.f2885r, new String[0]);
    }

    @Override // F8.AbstractC0272g
    public final Q7.O d() {
        Q7.O o = this.c;
        if (o != null) {
            return o;
        }
        h(5);
        throw null;
    }

    @Override // F8.AbstractC0272g
    public final boolean f(InterfaceC0654g interfaceC0654g) {
        if (interfaceC0654g instanceof Q7.Q) {
            AbstractC0789h abstractC0789h = this.d;
            A7.m.f(abstractC0789h, "a");
            if (C2309b.l.d(abstractC0789h, (Q7.Q) interfaceC0654g, true, C2308a.o)) {
                return true;
            }
        }
        return false;
    }

    @Override // F8.AbstractC0272g
    public final List g(List list) {
        List listC1 = this.d.c1(list);
        if (listC1 != null) {
            return listC1;
        }
        h(8);
        throw null;
    }

    @Override // F8.K
    public final N7.i l() {
        N7.i iVarE = AbstractC2564d.e(this.d);
        if (iVarE != null) {
            return iVarE;
        }
        h(4);
        throw null;
    }

    @Override // F8.K
    public final boolean m() {
        return true;
    }

    @Override // F8.K
    public final InterfaceC0654g n() {
        AbstractC0789h abstractC0789h = this.d;
        if (abstractC0789h != null) {
            return abstractC0789h;
        }
        h(3);
        throw null;
    }

    @Override // F8.K
    public final List p() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        h(2);
        throw null;
    }

    public final String toString() {
        return this.d.getName().l;
    }
}
