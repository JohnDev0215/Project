package N7;

import F8.A;
import F8.AbstractC0268c;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.K;
import F8.O;
import F8.P;
import F8.Y;
import F8.a0;
import Q7.AbstractC0669w;
import Q7.D;
import Q7.E;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import Q7.InterfaceC0671y;
import Q7.L;
import T.C0746i0;
import T7.B;
import T7.I;
import T7.J;
import T7.z;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import n7.AbstractC1996k;
import o8.C2057b;
import o8.C2058c;
import o8.C2060e;
import o8.C2061f;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C2061f f4796e = C2061f.g("<built-ins module>");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public z f4797a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E8.i f4798b;
    public final E8.e c;
    public final E8.l d;

    public i(E8.l lVar) {
        this.d = lVar;
        lVar.a(new f(this, 0));
        this.f4798b = new E8.i(lVar, new f(this, 1));
        this.c = lVar.b(new g(this, 0));
    }

    public static boolean A(AbstractC0287w abstractC0287w, C2060e c2060e) {
        if (c2060e != null) {
            return z(abstractC0287w, c2060e) && !abstractC0287w.F0();
        }
        a(135);
        throw null;
    }

    public static boolean B(InterfaceC0666t interfaceC0666t) {
        if (interfaceC0666t.b1().k().k(n.f4855m)) {
            return true;
        }
        if (!(interfaceC0666t instanceof L)) {
            return false;
        }
        L l = (L) interfaceC0666t;
        boolean zI = l.I();
        I iC = l.c();
        J jD = l.d();
        if (iC != null && B(iC)) {
            if (!zI) {
                return true;
            }
            if (jD != null && B(jD)) {
                return true;
            }
        }
        return false;
    }

    public static boolean C(AbstractC0287w abstractC0287w, C2060e c2060e) {
        if (abstractC0287w == null) {
            a(105);
            throw null;
        }
        if (c2060e != null) {
            return !abstractC0287w.F0() && z(abstractC0287w, c2060e);
        }
        a(106);
        throw null;
    }

    public static boolean D(AbstractC0287w abstractC0287w) {
        if (abstractC0287w == null) {
            a(136);
            throw null;
        }
        if (abstractC0287w != null) {
            return z(abstractC0287w, n.f4845b) && !Y.e(abstractC0287w);
        }
        a(138);
        throw null;
    }

    public static boolean E(AbstractC0287w abstractC0287w) {
        if (abstractC0287w == null) {
            a(94);
            throw null;
        }
        if (!abstractC0287w.F0()) {
            InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
            if (interfaceC0654gN instanceof InterfaceC0652e) {
                InterfaceC0652e interfaceC0652e = (InterfaceC0652e) interfaceC0654gN;
                if (interfaceC0652e == null) {
                    a(96);
                    throw null;
                }
                if (s(interfaceC0652e) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean F(K k10, C2060e c2060e) {
        if (k10 == null) {
            a(101);
            throw null;
        }
        if (c2060e != null) {
            InterfaceC0654g interfaceC0654gN = k10.n();
            return (interfaceC0654gN instanceof InterfaceC0652e) && b((InterfaceC0652e) interfaceC0654gN, c2060e);
        }
        a(102);
        throw null;
    }

    public static boolean G(InterfaceC0654g interfaceC0654g) {
        if (interfaceC0654g == null) {
            a(10);
            throw null;
        }
        for (InterfaceC0657j interfaceC0657jO = interfaceC0654g; interfaceC0657jO != null; interfaceC0657jO = interfaceC0657jO.o()) {
            if (interfaceC0657jO instanceof D) {
                return ((B) ((D) interfaceC0657jO)).f8767q.h(o.f4874i);
            }
        }
        return false;
    }

    public static /* synthetic */ void a(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 68:
            case 69:
            case 70:
            case 74:
            case 81:
            case 84:
            case 86:
            case 87:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 9:
            case 10:
            case 12:
            case 14:
            case 16:
            case 17:
            case 46:
            case 53:
            case 67:
            case 71:
            case 72:
            case 73:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 82:
            case 83:
            case 85:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 68:
            case 69:
            case 70:
            case 74:
            case 81:
            case 84:
            case 86:
            case 87:
                i10 = 2;
                break;
            case 9:
            case 10:
            case 12:
            case 14:
            case 16:
            case 17:
            case 46:
            case 53:
            case 67:
            case 71:
            case 72:
            case 73:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 82:
            case 83:
            case 85:
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 1:
            case 72:
                objArr[0] = "module";
                break;
            case 2:
                objArr[0] = "computation";
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 68:
            case 69:
            case 70:
            case 74:
            case 81:
            case 84:
            case 86:
            case 87:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/builtins/KotlinBuiltIns";
                break;
            case 9:
            case 10:
            case 76:
            case 77:
            case 89:
            case 96:
            case 103:
            case 107:
            case 108:
            case 143:
            case 146:
            case 147:
            case 149:
            case 157:
            case 158:
            case 159:
            case 160:
                objArr[0] = "descriptor";
                break;
            case 12:
            case 98:
            case 100:
            case 102:
            case 104:
            case 106:
            case 135:
                objArr[0] = "fqName";
                break;
            case 14:
                objArr[0] = "simpleName";
                break;
            case 16:
            case 17:
            case 53:
            case 88:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 97:
            case 99:
            case 105:
            case 109:
            case 110:
            case 111:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 144:
            case 145:
            case 148:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 162:
                objArr[0] = "type";
                break;
            case 46:
                objArr[0] = "classSimpleName";
                break;
            case 67:
                objArr[0] = "arrayType";
                break;
            case 71:
                objArr[0] = "notNullArrayType";
                break;
            case 73:
                objArr[0] = "primitiveType";
                break;
            case 75:
                objArr[0] = "kotlinType";
                break;
            case 78:
            case 82:
                objArr[0] = "projectionType";
                break;
            case 79:
            case 83:
            case 85:
                objArr[0] = "argument";
                break;
            case 80:
                objArr[0] = "annotations";
                break;
            case 101:
                objArr[0] = "typeConstructor";
                break;
            case 112:
                objArr[0] = "classDescriptor";
                break;
            case 161:
                objArr[0] = "declarationDescriptor";
                break;
            default:
                objArr[0] = "storageManager";
                break;
        }
        switch (i7) {
            case 3:
                objArr[1] = "getAdditionalClassPartsProvider";
                break;
            case 4:
                objArr[1] = "getPlatformDependentDeclarationFilter";
                break;
            case 5:
                objArr[1] = "getClassDescriptorFactories";
                break;
            case 6:
                objArr[1] = "getStorageManager";
                break;
            case 7:
                objArr[1] = "getBuiltInsModule";
                break;
            case 8:
                objArr[1] = "getBuiltInPackagesImportedByDefault";
                break;
            case 9:
            case 10:
            case 12:
            case 14:
            case 16:
            case 17:
            case 46:
            case 53:
            case 67:
            case 71:
            case 72:
            case 73:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 82:
            case 83:
            case 85:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/builtins/KotlinBuiltIns";
                break;
            case 11:
                objArr[1] = "getBuiltInsPackageScope";
                break;
            case 13:
                objArr[1] = "getBuiltInClassByFqName";
                break;
            case 15:
                objArr[1] = "getBuiltInClassByName";
                break;
            case 18:
                objArr[1] = "getSuspendFunction";
                break;
            case 19:
                objArr[1] = "getKFunction";
                break;
            case 20:
                objArr[1] = "getKSuspendFunction";
                break;
            case 21:
                objArr[1] = "getKClass";
                break;
            case 22:
                objArr[1] = "getKCallable";
                break;
            case 23:
                objArr[1] = "getKProperty";
                break;
            case 24:
                objArr[1] = "getKProperty0";
                break;
            case 25:
                objArr[1] = "getKProperty1";
                break;
            case 26:
                objArr[1] = "getKProperty2";
                break;
            case 27:
                objArr[1] = "getKMutableProperty0";
                break;
            case 28:
                objArr[1] = "getKMutableProperty1";
                break;
            case 29:
                objArr[1] = "getKMutableProperty2";
                break;
            case 30:
                objArr[1] = "getIterator";
                break;
            case 31:
                objArr[1] = "getIterable";
                break;
            case 32:
                objArr[1] = "getMutableIterable";
                break;
            case 33:
                objArr[1] = "getMutableIterator";
                break;
            case 34:
                objArr[1] = "getCollection";
                break;
            case 35:
                objArr[1] = "getMutableCollection";
                break;
            case 36:
                objArr[1] = "getList";
                break;
            case 37:
                objArr[1] = "getMutableList";
                break;
            case 38:
                objArr[1] = "getSet";
                break;
            case 39:
                objArr[1] = "getMutableSet";
                break;
            case 40:
                objArr[1] = "getMap";
                break;
            case 41:
                objArr[1] = "getMutableMap";
                break;
            case 42:
                objArr[1] = "getMapEntry";
                break;
            case 43:
                objArr[1] = "getMutableMapEntry";
                break;
            case 44:
                objArr[1] = "getListIterator";
                break;
            case 45:
                objArr[1] = "getMutableListIterator";
                break;
            case 47:
                objArr[1] = "getBuiltInTypeByClassName";
                break;
            case 48:
                objArr[1] = "getNothingType";
                break;
            case 49:
                objArr[1] = "getNullableNothingType";
                break;
            case 50:
                objArr[1] = "getAnyType";
                break;
            case 51:
                objArr[1] = "getNullableAnyType";
                break;
            case 52:
                objArr[1] = "getDefaultBound";
                break;
            case 54:
                objArr[1] = "getPrimitiveKotlinType";
                break;
            case 55:
                objArr[1] = "getNumberType";
                break;
            case 56:
                objArr[1] = "getByteType";
                break;
            case 57:
                objArr[1] = "getShortType";
                break;
            case 58:
                objArr[1] = "getIntType";
                break;
            case 59:
                objArr[1] = "getLongType";
                break;
            case 60:
                objArr[1] = "getFloatType";
                break;
            case 61:
                objArr[1] = "getDoubleType";
                break;
            case 62:
                objArr[1] = "getCharType";
                break;
            case 63:
                objArr[1] = "getBooleanType";
                break;
            case 64:
                objArr[1] = "getUnitType";
                break;
            case 65:
                objArr[1] = "getStringType";
                break;
            case 66:
                objArr[1] = "getIterableType";
                break;
            case 68:
            case 69:
            case 70:
                objArr[1] = "getArrayElementType";
                break;
            case 74:
                objArr[1] = "getPrimitiveArrayKotlinType";
                break;
            case 81:
            case 84:
                objArr[1] = "getArrayType";
                break;
            case 86:
                objArr[1] = "getEnumType";
                break;
            case 87:
                objArr[1] = "getAnnotationType";
                break;
        }
        switch (i7) {
            case 1:
                objArr[2] = "setBuiltInsModule";
                break;
            case 2:
                objArr[2] = "setPostponedBuiltinsModuleComputation";
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 68:
            case 69:
            case 70:
            case 74:
            case 81:
            case 84:
            case 86:
            case 87:
                break;
            case 9:
                objArr[2] = "isBuiltIn";
                break;
            case 10:
                objArr[2] = "isUnderKotlinPackage";
                break;
            case 12:
                objArr[2] = "getBuiltInClassByFqName";
                break;
            case 14:
                objArr[2] = "getBuiltInClassByName";
                break;
            case 16:
                objArr[2] = "getPrimitiveClassDescriptor";
                break;
            case 17:
                objArr[2] = "getPrimitiveArrayClassDescriptor";
                break;
            case 46:
                objArr[2] = "getBuiltInTypeByClassName";
                break;
            case 53:
                objArr[2] = "getPrimitiveKotlinType";
                break;
            case 67:
                objArr[2] = "getArrayElementType";
                break;
            case 71:
            case 72:
                objArr[2] = "getElementTypeForUnsignedArray";
                break;
            case 73:
                objArr[2] = "getPrimitiveArrayKotlinType";
                break;
            case 75:
                objArr[2] = "getPrimitiveArrayKotlinTypeByPrimitiveKotlinType";
                break;
            case 76:
            case 93:
                objArr[2] = "getPrimitiveType";
                break;
            case 77:
                objArr[2] = "getPrimitiveArrayType";
                break;
            case 78:
            case 79:
            case 80:
            case 82:
            case 83:
                objArr[2] = "getArrayType";
                break;
            case 85:
                objArr[2] = "getEnumType";
                break;
            case 88:
                objArr[2] = "isArray";
                break;
            case 89:
            case 90:
                objArr[2] = "isArrayOrPrimitiveArray";
                break;
            case 91:
                objArr[2] = "isPrimitiveArray";
                break;
            case 92:
                objArr[2] = "getPrimitiveArrayElementType";
                break;
            case 94:
                objArr[2] = "isPrimitiveType";
                break;
            case 95:
                objArr[2] = "isPrimitiveTypeOrNullablePrimitiveType";
                break;
            case 96:
                objArr[2] = "isPrimitiveClass";
                break;
            case 97:
            case 98:
            case 99:
            case 100:
                objArr[2] = "isConstructedFromGivenClass";
                break;
            case 101:
            case 102:
                objArr[2] = "isTypeConstructorForGivenClass";
                break;
            case 103:
            case 104:
                objArr[2] = "classFqNameEquals";
                break;
            case 105:
            case 106:
                objArr[2] = "isNotNullConstructedFromGivenClass";
                break;
            case 107:
                objArr[2] = "isSpecialClassWithNoSupertypes";
                break;
            case 108:
            case 109:
                objArr[2] = "isAny";
                break;
            case 110:
            case 112:
                objArr[2] = "isBoolean";
                break;
            case 111:
                objArr[2] = "isBooleanOrNullableBoolean";
                break;
            case 113:
                objArr[2] = "isNumber";
                break;
            case 114:
                objArr[2] = "isChar";
                break;
            case 115:
                objArr[2] = "isCharOrNullableChar";
                break;
            case 116:
                objArr[2] = "isInt";
                break;
            case 117:
                objArr[2] = "isByte";
                break;
            case 118:
                objArr[2] = "isLong";
                break;
            case 119:
                objArr[2] = "isLongOrNullableLong";
                break;
            case 120:
                objArr[2] = "isShort";
                break;
            case 121:
                objArr[2] = "isFloat";
                break;
            case 122:
                objArr[2] = "isFloatOrNullableFloat";
                break;
            case 123:
                objArr[2] = "isDouble";
                break;
            case 124:
                objArr[2] = "isUByte";
                break;
            case 125:
                objArr[2] = "isUShort";
                break;
            case 126:
                objArr[2] = "isUInt";
                break;
            case 127:
                objArr[2] = "isULong";
                break;
            case 128:
                objArr[2] = "isUByteArray";
                break;
            case 129:
                objArr[2] = "isUShortArray";
                break;
            case 130:
                objArr[2] = "isUIntArray";
                break;
            case 131:
                objArr[2] = "isULongArray";
                break;
            case 132:
                objArr[2] = "isUnsignedArrayType";
                break;
            case 133:
                objArr[2] = "isDoubleOrNullableDouble";
                break;
            case 134:
            case 135:
                objArr[2] = "isConstructedFromGivenClassAndNotNullable";
                break;
            case 136:
                objArr[2] = "isNothing";
                break;
            case 137:
                objArr[2] = "isNullableNothing";
                break;
            case 138:
                objArr[2] = "isNothingOrNullableNothing";
                break;
            case 139:
                objArr[2] = "isAnyOrNullableAny";
                break;
            case 140:
                objArr[2] = "isNullableAny";
                break;
            case 141:
                objArr[2] = "isDefaultBound";
                break;
            case 142:
                objArr[2] = "isUnit";
                break;
            case 143:
                objArr[2] = "mayReturnNonUnitValue";
                break;
            case 144:
                objArr[2] = "isUnitOrNullableUnit";
                break;
            case 145:
                objArr[2] = "isBooleanOrSubtype";
                break;
            case 146:
                objArr[2] = "isMemberOfAny";
                break;
            case 147:
            case 148:
                objArr[2] = "isEnum";
                break;
            case 149:
            case 150:
                objArr[2] = "isComparable";
                break;
            case 151:
                objArr[2] = "isCollectionOrNullableCollection";
                break;
            case 152:
                objArr[2] = "isListOrNullableList";
                break;
            case 153:
                objArr[2] = "isSetOrNullableSet";
                break;
            case 154:
                objArr[2] = "isMapOrNullableMap";
                break;
            case 155:
                objArr[2] = "isIterableOrNullableIterable";
                break;
            case 156:
                objArr[2] = "isThrowableOrNullableThrowable";
                break;
            case 157:
                objArr[2] = "isThrowable";
                break;
            case 158:
                objArr[2] = "isKClass";
                break;
            case 159:
                objArr[2] = "isNonPrimitiveArray";
                break;
            case 160:
                objArr[2] = "isCloneable";
                break;
            case 161:
                objArr[2] = "isDeprecated";
                break;
            case 162:
                objArr[2] = "isNotNullOrNullableFunctionSupertype";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 68:
            case 69:
            case 70:
            case 74:
            case 81:
            case 84:
            case 86:
            case 87:
                throw new IllegalStateException(str2);
            case 9:
            case 10:
            case 12:
            case 14:
            case 16:
            case 17:
            case 46:
            case 53:
            case 67:
            case 71:
            case 72:
            case 73:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 82:
            case 83:
            case 85:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public static boolean b(InterfaceC0652e interfaceC0652e, C2060e c2060e) {
        if (interfaceC0652e == null) {
            a(103);
            throw null;
        }
        if (c2060e != null) {
            return interfaceC0652e.getName().equals(c2060e.f()) && c2060e.equals(r8.d.g(interfaceC0652e));
        }
        a(104);
        throw null;
    }

    public static k q(InterfaceC0654g interfaceC0654g) {
        if (interfaceC0654g == null) {
            a(77);
            throw null;
        }
        if (n.f4844a0.contains(interfaceC0654g.getName())) {
            return (k) n.f4847c0.get(r8.d.g(interfaceC0654g));
        }
        return null;
    }

    public static k s(InterfaceC0652e interfaceC0652e) {
        if (interfaceC0652e == null) {
            a(76);
            throw null;
        }
        if (n.f4842Z.contains(interfaceC0652e.getName())) {
            return (k) n.f4846b0.get(r8.d.g(interfaceC0652e));
        }
        return null;
    }

    public static boolean w(AbstractC0287w abstractC0287w) {
        if (abstractC0287w != null) {
            return z(abstractC0287w, n.f4843a);
        }
        a(139);
        throw null;
    }

    public static boolean x(AbstractC0287w abstractC0287w) {
        if (abstractC0287w != null) {
            return z(abstractC0287w, n.f4850g);
        }
        a(88);
        throw null;
    }

    public static boolean y(InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j != null) {
            return r8.d.i(interfaceC0657j, C8.c.class, false) != null;
        }
        a(9);
        throw null;
    }

    public static boolean z(AbstractC0287w abstractC0287w, C2060e c2060e) {
        if (abstractC0287w == null) {
            a(97);
            throw null;
        }
        if (c2060e != null) {
            return F(abstractC0287w.B0(), c2060e);
        }
        a(98);
        throw null;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, m7.f] */
    public final void c(boolean z6) {
        C2061f c2061f = f4796e;
        A7.m.f(c2061f, "moduleName");
        E8.l lVar = this.d;
        z zVar = new z(c2061f, lVar, this, 48);
        this.f4797a = zVar;
        c.f4789a.getClass();
        E eA = ((C8.b) ((c) b.f4788b.getValue())).a(lVar, this.f4797a, l(), o(), d(), z6);
        A7.m.f(eA, "providerForModuleContent");
        zVar.f8925t = eA;
        z zVar2 = this.f4797a;
        zVar2.getClass();
        zVar2.f8924s = new C0746i0(AbstractC1996k.b0(new z[]{zVar2}));
    }

    public S7.b d() {
        return S7.a.f8077b;
    }

    public final A e() {
        A aJ = j("Any").j();
        if (aJ != null) {
            return aJ;
        }
        a(50);
        throw null;
    }

    public final AbstractC0287w f(AbstractC0287w abstractC0287w) {
        C2057b c2057bF;
        C2057b c2057b;
        InterfaceC0652e interfaceC0652eD;
        A aJ = null;
        if (abstractC0287w == null) {
            a(67);
            throw null;
        }
        if (x(abstractC0287w)) {
            if (abstractC0287w.o0().size() != 1) {
                throw new IllegalStateException();
            }
            AbstractC0287w abstractC0287wB = ((O) abstractC0287w.o0().get(0)).b();
            if (abstractC0287wB != null) {
                return abstractC0287wB;
            }
            a(68);
            throw null;
        }
        a0 a0VarG = Y.g(abstractC0287w, false);
        AbstractC0287w abstractC0287w2 = (AbstractC0287w) ((h) this.f4798b.a()).f4795b.get(a0VarG);
        if (abstractC0287w2 != null) {
            return abstractC0287w2;
        }
        int i7 = r8.d.f21880a;
        InterfaceC0654g interfaceC0654gN = a0VarG.B0().n();
        InterfaceC0671y interfaceC0671yE = interfaceC0654gN == null ? null : r8.d.e(interfaceC0654gN);
        if (interfaceC0671yE != null) {
            InterfaceC0654g interfaceC0654gN2 = a0VarG.B0().n();
            if (interfaceC0654gN2 != null) {
                Set set = s.f4886a;
                C2061f name = interfaceC0654gN2.getName();
                A7.m.f(name, "name");
                if (s.d.contains(name) && (c2057bF = AbstractC2564d.f(interfaceC0654gN2)) != null && (c2057b = (C2057b) s.f4887b.get(c2057bF)) != null && (interfaceC0652eD = AbstractC0669w.d(interfaceC0671yE, c2057b)) != null) {
                    aJ = interfaceC0652eD.j();
                }
            }
            if (aJ != null) {
                return aJ;
            }
        }
        throw new IllegalStateException("not array: " + abstractC0287w);
    }

    public final A g(int i7, AbstractC0287w abstractC0287w, R7.h hVar) {
        if (i7 == 0) {
            a(78);
            throw null;
        }
        if (abstractC0287w != null) {
            return C0269d.q(AbstractC0268c.w(hVar), j("Array"), Collections.singletonList(new P(i7, abstractC0287w)));
        }
        a(79);
        throw null;
    }

    public final A h(a0 a0Var) {
        if (a0Var != null) {
            return g(1, a0Var, R7.g.f7535a);
        }
        a(83);
        throw null;
    }

    public final InterfaceC0652e i(C2058c c2058c) {
        if (c2058c == null) {
            a(12);
            throw null;
        }
        InterfaceC0652e interfaceC0652eJ = AbstractC0669w.j(k(), c2058c);
        if (interfaceC0652eJ != null) {
            return interfaceC0652eJ;
        }
        a(13);
        throw null;
    }

    public final InterfaceC0652e j(String str) {
        if (str != null) {
            return (InterfaceC0652e) this.c.b(C2061f.e(str));
        }
        a(14);
        throw null;
    }

    public final z k() {
        this.f4797a.getClass();
        z zVar = this.f4797a;
        if (zVar != null) {
            return zVar;
        }
        a(7);
        throw null;
    }

    public Iterable l() {
        List listSingletonList = Collections.singletonList(new O7.a(this.d, k()));
        if (listSingletonList != null) {
            return listSingletonList;
        }
        a(5);
        throw null;
    }

    public final A m() {
        A aJ = j("Nothing").j();
        if (aJ != null) {
            return aJ;
        }
        a(48);
        throw null;
    }

    public final A n() {
        A aL0 = e().L0(true);
        if (aL0 != null) {
            return aL0;
        }
        a(51);
        throw null;
    }

    public S7.d o() {
        return S7.a.d;
    }

    public final A p(k kVar) {
        if (kVar == null) {
            a(73);
            throw null;
        }
        A a10 = (A) ((h) this.f4798b.a()).f4794a.get(kVar);
        if (a10 != null) {
            return a10;
        }
        a(74);
        throw null;
    }

    public final A r(k kVar) {
        if (kVar == null) {
            a(53);
            throw null;
        }
        A aJ = j(kVar.l.b()).j();
        if (aJ != null) {
            return aJ;
        }
        a(54);
        throw null;
    }

    public final A t() {
        A aJ = j("String").j();
        if (aJ != null) {
            return aJ;
        }
        a(65);
        throw null;
    }

    public final InterfaceC0652e u(int i7) {
        return i(o.f4870e.c(C2061f.e(O7.e.f5182p.f5186m + i7)));
    }

    public final A v() {
        A aJ = j("Unit").j();
        if (aJ != null) {
            return aJ;
        }
        a(64);
        throw null;
    }
}
