package r8;

import F8.AbstractC0287w;
import F8.K;
import Q7.AbstractC0662o;
import Q7.D;
import Q7.I;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0658k;
import Q7.InterfaceC0660m;
import Q7.InterfaceC0671y;
import Q7.O;
import T7.B;
import T7.J;
import T7.w;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import o8.AbstractC2063h;
import o8.C2058c;
import o8.C2060e;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f21880a = 0;

    static {
        new C2058c("kotlin.jvm.JvmName");
    }

    public static /* synthetic */ void a(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 63:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case 89:
            case 94:
            case 96:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 63:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case 89:
            case 94:
            case 96:
                i10 = 2;
                break;
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
            case 13:
            case 14:
            case 15:
            case 21:
            case 23:
            case 24:
            case 34:
            case 35:
            case 36:
            case 57:
            case 58:
            case 59:
            case 61:
            case 64:
            case 82:
            case 95:
            case 97:
                objArr[0] = "descriptor";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 63:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case 89:
            case 94:
            case 96:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils";
                break;
            case 16:
                objArr[0] = "first";
                break;
            case 17:
                objArr[0] = "second";
                break;
            case 18:
            case 19:
                objArr[0] = "aClass";
                break;
            case 20:
                objArr[0] = "kotlinType";
                break;
            case 25:
                objArr[0] = "declarationDescriptor";
                break;
            case 26:
            case 28:
                objArr[0] = "subClass";
                break;
            case 27:
            case 29:
            case 33:
                objArr[0] = "superClass";
                break;
            case 30:
            case 32:
            case 45:
            case 67:
                objArr[0] = "type";
                break;
            case 31:
                objArr[0] = "other";
                break;
            case 37:
                objArr[0] = "classKind";
                break;
            case 38:
            case 39:
            case 41:
            case 44:
            case 48:
            case 54:
            case 68:
            case 69:
            case 70:
            case 77:
            case 78:
                objArr[0] = "classDescriptor";
                break;
            case 46:
                objArr[0] = "typeConstructor";
                break;
            case 55:
                objArr[0] = "innerClassName";
                break;
            case 56:
                objArr[0] = "location";
                break;
            case 66:
                objArr[0] = "variable";
                break;
            case 71:
                objArr[0] = "f";
                break;
            case 73:
                objArr[0] = "current";
                break;
            case 74:
                objArr[0] = "result";
                break;
            case 75:
                objArr[0] = "memberDescriptor";
                break;
            case 79:
            case 80:
            case 81:
                objArr[0] = "annotated";
                break;
            case 85:
            case 87:
            case 90:
            case 92:
                objArr[0] = "scope";
                break;
            case 88:
            case 91:
            case 93:
                objArr[0] = "name";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i7) {
            case 4:
                objArr[1] = "getFqNameSafe";
                break;
            case 7:
                objArr[1] = "getFqNameUnsafe";
                break;
            case 9:
            case 10:
                objArr[1] = "getFqNameFromTopLevelClass";
                break;
            case 12:
                objArr[1] = "getClassIdForNonLocalClass";
                break;
            case 22:
                objArr[1] = "getContainingModule";
                break;
            case 40:
                objArr[1] = "getSuperclassDescriptors";
                break;
            case 42:
            case 43:
                objArr[1] = "getSuperClassType";
                break;
            case 47:
                objArr[1] = "getClassDescriptorForTypeConstructor";
                break;
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
                objArr[1] = "getDefaultConstructorVisibility";
                break;
            case 60:
                objArr[1] = "unwrapFakeOverride";
                break;
            case 62:
            case 63:
                objArr[1] = "unwrapSubstitutionOverride";
                break;
            case 65:
                objArr[1] = "unwrapFakeOverrideToAnyDeclaration";
                break;
            case 72:
                objArr[1] = "getAllOverriddenDescriptors";
                break;
            case 76:
                objArr[1] = "getAllOverriddenDeclarations";
                break;
            case 83:
            case 84:
                objArr[1] = "getContainingSourceFile";
                break;
            case 86:
                objArr[1] = "getAllDescriptors";
                break;
            case 89:
                objArr[1] = "getFunctionByName";
                break;
            case 94:
                objArr[1] = "getPropertyByName";
                break;
            case 96:
                objArr[1] = "getDirectMember";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils";
                break;
        }
        switch (i7) {
            case 1:
                objArr[2] = "isLocal";
                break;
            case 2:
                objArr[2] = "getFqName";
                break;
            case 3:
                objArr[2] = "getFqNameSafe";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 63:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case 89:
            case 94:
            case 96:
                break;
            case 5:
                objArr[2] = "getFqNameSafeIfPossible";
                break;
            case 6:
                objArr[2] = "getFqNameUnsafe";
                break;
            case 8:
                objArr[2] = "getFqNameFromTopLevelClass";
                break;
            case 11:
                objArr[2] = "getClassIdForNonLocalClass";
                break;
            case 13:
                objArr[2] = "isExtension";
                break;
            case 14:
                objArr[2] = "isOverride";
                break;
            case 15:
                objArr[2] = "isStaticDeclaration";
                break;
            case 16:
            case 17:
                objArr[2] = "areInSameModule";
                break;
            case 18:
            case 19:
                objArr[2] = "getParentOfType";
                break;
            case 20:
            case 23:
                objArr[2] = "getContainingModuleOrNull";
                break;
            case 21:
                objArr[2] = "getContainingModule";
                break;
            case 24:
                objArr[2] = "getContainingClass";
                break;
            case 25:
                objArr[2] = "isAncestor";
                break;
            case 26:
            case 27:
                objArr[2] = "isDirectSubclass";
                break;
            case 28:
            case 29:
                objArr[2] = "isSubclass";
                break;
            case 30:
            case 31:
                objArr[2] = "isSameClass";
                break;
            case 32:
            case 33:
                objArr[2] = "isSubtypeOfClass";
                break;
            case 34:
                objArr[2] = "isAnonymousObject";
                break;
            case 35:
                objArr[2] = "isAnonymousFunction";
                break;
            case 36:
                objArr[2] = "isEnumEntry";
                break;
            case 37:
                objArr[2] = "isKindOf";
                break;
            case 38:
                objArr[2] = "hasAbstractMembers";
                break;
            case 39:
                objArr[2] = "getSuperclassDescriptors";
                break;
            case 41:
                objArr[2] = "getSuperClassType";
                break;
            case 44:
                objArr[2] = "getSuperClassDescriptor";
                break;
            case 45:
                objArr[2] = "getClassDescriptorForType";
                break;
            case 46:
                objArr[2] = "getClassDescriptorForTypeConstructor";
                break;
            case 48:
                objArr[2] = "getDefaultConstructorVisibility";
                break;
            case 54:
            case 55:
            case 56:
                objArr[2] = "getInnerClassByName";
                break;
            case 57:
                objArr[2] = "isStaticNestedClass";
                break;
            case 58:
                objArr[2] = "isTopLevelOrInnerClass";
                break;
            case 59:
                objArr[2] = "unwrapFakeOverride";
                break;
            case 61:
                objArr[2] = "unwrapSubstitutionOverride";
                break;
            case 64:
                objArr[2] = "unwrapFakeOverrideToAnyDeclaration";
                break;
            case 66:
            case 67:
                objArr[2] = "shouldRecordInitializerForProperty";
                break;
            case 68:
                objArr[2] = "classCanHaveAbstractFakeOverride";
                break;
            case 69:
                objArr[2] = "classCanHaveAbstractDeclaration";
                break;
            case 70:
                objArr[2] = "classCanHaveOpenMembers";
                break;
            case 71:
                objArr[2] = "getAllOverriddenDescriptors";
                break;
            case 73:
            case 74:
                objArr[2] = "collectAllOverriddenDescriptors";
                break;
            case 75:
                objArr[2] = "getAllOverriddenDeclarations";
                break;
            case 77:
                objArr[2] = "isSingletonOrAnonymousObject";
                break;
            case 78:
                objArr[2] = "canHaveDeclaredConstructors";
                break;
            case 79:
                objArr[2] = "getJvmName";
                break;
            case 80:
                objArr[2] = "findJvmNameAnnotation";
                break;
            case 81:
                objArr[2] = "hasJvmNameAnnotation";
                break;
            case 82:
                objArr[2] = "getContainingSourceFile";
                break;
            case 85:
                objArr[2] = "getAllDescriptors";
                break;
            case 87:
            case 88:
                objArr[2] = "getFunctionByName";
                break;
            case 90:
            case 91:
                objArr[2] = "getFunctionByNameOrNull";
                break;
            case 92:
            case 93:
                objArr[2] = "getPropertyByName";
                break;
            case 95:
                objArr[2] = "getDirectMember";
                break;
            case 97:
                objArr[2] = "isMethodOfAny";
                break;
            default:
                objArr[2] = "getDispatchReceiverParameterIfNeeded";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 63:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case 89:
            case 94:
            case 96:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public static void b(InterfaceC0649b interfaceC0649b, LinkedHashSet linkedHashSet) {
        if (interfaceC0649b == null) {
            a(73);
            throw null;
        }
        if (linkedHashSet.contains(interfaceC0649b)) {
            return;
        }
        Iterator it = interfaceC0649b.b1().q().iterator();
        while (it.hasNext()) {
            InterfaceC0649b interfaceC0649bB1 = ((InterfaceC0649b) it.next()).b1();
            b(interfaceC0649bB1, linkedHashSet);
            linkedHashSet.add(interfaceC0649bB1);
        }
    }

    public static InterfaceC0652e c(AbstractC0287w abstractC0287w) {
        if (abstractC0287w == null) {
            a(45);
            throw null;
        }
        K kB0 = abstractC0287w.B0();
        if (kB0 == null) {
            a(46);
            throw null;
        }
        InterfaceC0652e interfaceC0652e = (InterfaceC0652e) kB0.n();
        if (interfaceC0652e != null) {
            return interfaceC0652e;
        }
        a(47);
        throw null;
    }

    public static InterfaceC0671y d(InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j == null) {
            a(21);
            throw null;
        }
        InterfaceC0671y interfaceC0671yE = e(interfaceC0657j);
        if (interfaceC0671yE != null) {
            return interfaceC0671yE;
        }
        a(22);
        throw null;
    }

    public static InterfaceC0671y e(InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j == null) {
            a(23);
            throw null;
        }
        while (interfaceC0657j != null) {
            if (interfaceC0657j instanceof InterfaceC0671y) {
                return (InterfaceC0671y) interfaceC0657j;
            }
            if (interfaceC0657j instanceof I) {
                return ((w) ((I) interfaceC0657j)).o;
            }
            interfaceC0657j = interfaceC0657j.o();
        }
        return null;
    }

    public static O f(InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j == null) {
            a(82);
            throw null;
        }
        if (interfaceC0657j instanceof J) {
            interfaceC0657j = ((J) interfaceC0657j).c1();
        }
        boolean z6 = interfaceC0657j instanceof InterfaceC0658k;
        O o = O.f7180m;
        if (z6) {
            ((InterfaceC0658k) interfaceC0657j).g().getClass();
        }
        return o;
    }

    public static C2060e g(InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j != null) {
            C2058c c2058cH = h(interfaceC0657j);
            return c2058cH != null ? c2058cH.i() : g(interfaceC0657j.o()).b(interfaceC0657j.getName());
        }
        a(2);
        throw null;
    }

    public static C2058c h(InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j == null) {
            a(5);
            throw null;
        }
        if ((interfaceC0657j instanceof InterfaceC0671y) || H8.i.f(interfaceC0657j)) {
            return C2058c.c;
        }
        if (interfaceC0657j instanceof I) {
            return ((w) ((I) interfaceC0657j)).f8913p;
        }
        if (interfaceC0657j instanceof D) {
            return ((B) ((D) interfaceC0657j)).f8767q;
        }
        return null;
    }

    public static InterfaceC0657j i(InterfaceC0657j interfaceC0657j, Class cls, boolean z6) {
        if (interfaceC0657j == null) {
            return null;
        }
        if (z6) {
            interfaceC0657j = interfaceC0657j.o();
        }
        while (interfaceC0657j != null) {
            if (cls.isInstance(interfaceC0657j)) {
                return interfaceC0657j;
            }
            interfaceC0657j = interfaceC0657j.o();
        }
        return null;
    }

    public static InterfaceC0652e j(InterfaceC0652e interfaceC0652e) {
        if (interfaceC0652e == null) {
            a(44);
            throw null;
        }
        Iterator it = interfaceC0652e.C().o().iterator();
        while (it.hasNext()) {
            InterfaceC0652e interfaceC0652eC = c((AbstractC0287w) it.next());
            if (interfaceC0652eC.E() != 2) {
                return interfaceC0652eC;
            }
        }
        return null;
    }

    public static boolean k(InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j != null) {
            return n(interfaceC0657j, 1) && interfaceC0657j.getName().equals(AbstractC2063h.f20575a);
        }
        a(34);
        throw null;
    }

    public static boolean l(InterfaceC0657j interfaceC0657j) {
        return n(interfaceC0657j, 6) && ((InterfaceC0652e) interfaceC0657j).F();
    }

    public static boolean m(InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j != null) {
            return n(interfaceC0657j, 4);
        }
        a(36);
        throw null;
    }

    public static boolean n(InterfaceC0657j interfaceC0657j, int i7) {
        if (i7 != 0) {
            return (interfaceC0657j instanceof InterfaceC0652e) && ((InterfaceC0652e) interfaceC0657j).E() == i7;
        }
        a(37);
        throw null;
    }

    public static boolean o(InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j == null) {
            a(1);
            throw null;
        }
        while (interfaceC0657j != null) {
            if (k(interfaceC0657j) || ((interfaceC0657j instanceof InterfaceC0660m) && ((InterfaceC0660m) interfaceC0657j).e() == AbstractC0662o.f7192f)) {
                return true;
            }
            interfaceC0657j = interfaceC0657j.o();
        }
        return false;
    }

    public static boolean p(AbstractC0287w abstractC0287w, InterfaceC0652e interfaceC0652e) {
        if (abstractC0287w == null) {
            a(30);
            throw null;
        }
        if (interfaceC0652e == null) {
            a(31);
            throw null;
        }
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        if (interfaceC0654gN == null) {
            return false;
        }
        InterfaceC0657j interfaceC0657jB1 = interfaceC0654gN.b1();
        return (interfaceC0657jB1 instanceof InterfaceC0654g) && interfaceC0652e.C().equals(((InterfaceC0654g) interfaceC0657jB1).C());
    }

    public static boolean q(InterfaceC0657j interfaceC0657j) {
        return (n(interfaceC0657j, 1) || n(interfaceC0657j, 2)) && ((InterfaceC0652e) interfaceC0657j).h() == 2;
    }

    public static boolean r(AbstractC0287w abstractC0287w, InterfaceC0652e interfaceC0652e) {
        if (abstractC0287w == null) {
            a(32);
            throw null;
        }
        if (interfaceC0652e == null) {
            a(33);
            throw null;
        }
        if (p(abstractC0287w, interfaceC0652e)) {
            return true;
        }
        Iterator it = abstractC0287w.B0().o().iterator();
        while (it.hasNext()) {
            if (r((AbstractC0287w) it.next(), interfaceC0652e)) {
                return true;
            }
        }
        return false;
    }

    public static boolean s(InterfaceC0657j interfaceC0657j) {
        return interfaceC0657j != null && (interfaceC0657j.o() instanceof D);
    }

    public static InterfaceC0650c t(InterfaceC0650c interfaceC0650c) {
        if (interfaceC0650c == null) {
            a(59);
            throw null;
        }
        while (interfaceC0650c.N() == 2) {
            Collection collectionQ = interfaceC0650c.q();
            if (collectionQ.isEmpty()) {
                throw new IllegalStateException("Fake override should have at least one overridden descriptor: " + interfaceC0650c);
            }
            interfaceC0650c = (InterfaceC0650c) collectionQ.iterator().next();
        }
        return interfaceC0650c;
    }
}
