package F8;

import Q7.InterfaceC0652e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import o8.C2060e;

/* JADX INFO: renamed from: F8.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0275j extends AbstractC0267b {
    public final T7.y c;
    public final List d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Collection f2471e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0275j(T7.y yVar, List list, Collection collection, E8.o oVar) {
        super(oVar);
        if (list == null) {
            h(1);
            throw null;
        }
        if (collection == null) {
            h(2);
            throw null;
        }
        if (oVar == null) {
            h(3);
            throw null;
        }
        this.c = yVar;
        this.d = Collections.unmodifiableList(new ArrayList(list));
        this.f2471e = Collections.unmodifiableCollection(collection);
    }

    public static /* synthetic */ void h(int i7) {
        String str = (i7 == 4 || i7 == 5 || i7 == 6 || i7 == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 4 || i7 == 5 || i7 == 6 || i7 == 7) ? 2 : 3];
        switch (i7) {
            case 1:
                objArr[0] = "parameters";
                break;
            case 2:
                objArr[0] = "supertypes";
                break;
            case 3:
                objArr[0] = "storageManager";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/ClassTypeConstructorImpl";
                break;
            default:
                objArr[0] = "classDescriptor";
                break;
        }
        if (i7 == 4) {
            objArr[1] = "getParameters";
        } else if (i7 == 5) {
            objArr[1] = "getDeclarationDescriptor";
        } else if (i7 == 6) {
            objArr[1] = "computeSupertypes";
        } else if (i7 != 7) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/ClassTypeConstructorImpl";
        } else {
            objArr[1] = "getSupertypeLoopChecker";
        }
        if (i7 != 4 && i7 != 5 && i7 != 6 && i7 != 7) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 != 4 && i7 != 5 && i7 != 6 && i7 != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // F8.AbstractC0272g
    public final Collection b() {
        Collection collection = this.f2471e;
        if (collection != null) {
            return collection;
        }
        h(6);
        throw null;
    }

    @Override // F8.AbstractC0272g
    public final Q7.O d() {
        return Q7.O.f7181n;
    }

    @Override // F8.AbstractC0267b
    /* JADX INFO: renamed from: i */
    public final InterfaceC0652e n() {
        T7.y yVar = this.c;
        if (yVar != null) {
            return yVar;
        }
        h(5);
        throw null;
    }

    @Override // F8.K
    public final boolean m() {
        return true;
    }

    @Override // F8.K
    public final List p() {
        List list = this.d;
        if (list != null) {
            return list;
        }
        h(4);
        throw null;
    }

    public final String toString() {
        String str = r8.d.g(this.c).f20571a;
        if (str != null) {
            return str;
        }
        C2060e.a(4);
        throw null;
    }
}
