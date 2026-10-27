package E5;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0181f f1782a = new C0181f(5);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final J f1783b = new J(1);
    public static final J c = new J(2);
    public static final J d = new J(3);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final J f1784e = new J(4);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final J f1785f = new J(5);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final J f1786g = new J(6);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final J f1787h = new J(7);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final J f1788i = new J(8);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final J f1789j = new J(0);

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final E5.q a(E5.H r3, H7.x r4) {
        /*
            java.lang.String r0 = "ktype"
            A7.m.f(r4, r0)
            boolean r0 = r4 instanceof A7.n
            r1 = 0
            if (r0 == 0) goto L1e
            r0 = r4
            A7.n r0 = (A7.n) r0
            K7.o0 r0 = (K7.o0) r0
            K7.t0 r0 = r0.f3825b
            if (r0 == 0) goto L1a
            java.lang.Object r0 = r0.a()
            java.lang.reflect.Type r0 = (java.lang.reflect.Type) r0
            goto L1b
        L1a:
            r0 = r1
        L1b:
            if (r0 == 0) goto L1e
            goto L23
        L1e:
            r0 = 0
            java.lang.reflect.Type r0 = H7.H.r(r4, r0)
        L23:
            java.util.Set r2 = G5.f.f2685a
            E5.q r3 = r3.c(r0, r2, r1)
            boolean r0 = r3 instanceof G5.b
            if (r0 != 0) goto L46
            boolean r0 = r3 instanceof G5.a
            if (r0 == 0) goto L32
            goto L46
        L32:
            boolean r4 = r4.b()
            if (r4 == 0) goto L3d
            G5.b r3 = r3.c()
            goto L46
        L3d:
            if (r0 == 0) goto L40
            goto L46
        L40:
            G5.a r4 = new G5.a
            r4.<init>(r3)
            r3 = r4
        L46:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: E5.M.a(E5.H, H7.x):E5.q");
    }

    public static Type b(Type type) {
        if (!Collection.class.isAssignableFrom(Collection.class)) {
            throw new IllegalArgumentException();
        }
        Type typeH = G5.f.h(type, Collection.class, G5.f.c(type, Collection.class, Collection.class), new LinkedHashSet());
        if (typeH instanceof WildcardType) {
            typeH = ((WildcardType) typeH).getUpperBounds()[0];
        }
        return typeH instanceof ParameterizedType ? ((ParameterizedType) typeH).getActualTypeArguments()[0] : Object.class;
    }

    public static boolean c(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type2 instanceof GenericArrayType ? c(((Class) type).getComponentType(), ((GenericArrayType) type2).getGenericComponentType()) : type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            return c(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType instanceof G5.d ? ((G5.d) parameterizedType).f2683n : parameterizedType.getActualTypeArguments(), parameterizedType2 instanceof G5.d ? ((G5.d) parameterizedType2).f2683n : parameterizedType2.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            if (type2 instanceof Class) {
                return c(((Class) type2).getComponentType(), ((GenericArrayType) type).getGenericComponentType());
            }
            if (type2 instanceof GenericArrayType) {
                return c(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        }
        if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
        }
        if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        }
        TypeVariable typeVariable = (TypeVariable) type;
        TypeVariable typeVariable2 = (TypeVariable) type2;
        return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
    }

    public static String d(int i7, int[] iArr, String[] strArr, int[] iArr2) {
        StringBuilder sb = new StringBuilder("$");
        for (int i10 = 0; i10 < i7; i10++) {
            int i11 = iArr[i10];
            if (i11 == 1 || i11 == 2) {
                sb.append('[');
                sb.append(iArr2[i10]);
                sb.append(']');
            } else if (i11 == 3 || i11 == 4 || i11 == 5) {
                sb.append('.');
                String str = strArr[i10];
                if (str != null) {
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }

    public static Class e(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance((Class<?>) e(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return e(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    public static G5.d g(Class cls, Type... typeArr) {
        if (typeArr.length != 0) {
            return new G5.d(null, cls, typeArr);
        }
        throw new IllegalArgumentException("Missing type arguments for " + cls);
    }

    public static int h(u uVar, String str, int i7, int i10) {
        int iQ = uVar.q();
        if (iQ >= i7 && iQ <= i10) {
            return iQ;
        }
        throw new A9.k("Expected " + str + " but was " + iQ + " at path " + uVar.k());
    }

    public abstract Object f();
}
