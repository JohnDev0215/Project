package l2;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

/* JADX INFO: compiled from: FieldInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements Comparable<c> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f6169d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f6170e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Field f6171f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6172g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Class<?> f6173h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Type f6174i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Class<?> f6175j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f6176k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f6177l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f6178m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f6179n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final d2.b f6180o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final d2.b f6181p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f6182q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f6183r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final char[] f6184s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f6185t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f6186u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f6187v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final String f6188w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final String[] f6189x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final long f6190y;

    public c(String str, Class<?> cls, Class<?> cls2, Type type, Field field, int i8, int i9, int i10) {
        this.f6172g = 0;
        i8 = i8 < 0 ? 0 : i8;
        this.f6169d = str;
        this.f6175j = cls;
        this.f6173h = cls2;
        this.f6174i = type;
        this.f6170e = null;
        this.f6171f = field;
        this.f6172g = i8;
        this.f6177l = i9;
        this.f6178m = i10;
        this.f6185t = cls2.isEnum();
        if (field != null) {
            int modifiers = field.getModifiers();
            int i11 = modifiers & 1;
            this.f6182q = true;
            this.f6183r = Modifier.isTransient(modifiers);
        } else {
            this.f6183r = false;
            this.f6182q = false;
        }
        this.f6184s = b();
        if (field != null) {
            l.K0(field);
        }
        this.f6179n = "";
        d2.b bVar = field == null ? null : (d2.b) l.N(field, d2.b.class);
        this.f6180o = bVar;
        this.f6181p = null;
        this.f6176k = false;
        this.f6186u = false;
        this.f6187v = false;
        this.f6188w = null;
        this.f6189x = new String[0];
        this.f6190y = m(str, bVar);
    }

    public static boolean e(Type[] typeArr, Map<TypeVariable, Type> map) {
        if (map == null || map.size() == 0) {
            return false;
        }
        boolean z7 = false;
        for (int i8 = 0; i8 < typeArr.length; i8++) {
            Type type = typeArr[i8];
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (e(actualTypeArguments, map)) {
                    typeArr[i8] = new h(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType());
                    z7 = true;
                }
            } else if ((type instanceof TypeVariable) && map.containsKey(type)) {
                typeArr[i8] = map.get(type);
                z7 = true;
            }
        }
        return z7;
    }

    public static boolean f(Type[] typeArr, TypeVariable[] typeVariableArr, Type[] typeArr2) {
        if (typeArr2 == null || typeVariableArr.length == 0) {
            return false;
        }
        boolean z7 = false;
        for (int i8 = 0; i8 < typeArr.length; i8++) {
            Type type = typeArr[i8];
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (f(actualTypeArguments, typeVariableArr, typeArr2)) {
                    typeArr[i8] = new h(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType());
                    z7 = true;
                }
            } else if (type instanceof TypeVariable) {
                for (int i9 = 0; i9 < typeVariableArr.length; i9++) {
                    if (type.equals(typeVariableArr[i9])) {
                        typeArr[i8] = typeArr2[i9];
                        z7 = true;
                    }
                }
            }
        }
        return z7;
    }

    public static Type h(Class<?> cls, Type type, Type type2) {
        return i(cls, type, type2, null);
    }

    public static Type i(Class<?> cls, Type type, Type type2, Map<TypeVariable, Type> map) {
        TypeVariable<Class<?>>[] typeParameters;
        ParameterizedType parameterizedType;
        if (cls != null && type != null) {
            if (type2 instanceof GenericArrayType) {
                Type genericComponentType = ((GenericArrayType) type2).getGenericComponentType();
                Type typeI = i(cls, type, genericComponentType, map);
                return genericComponentType != typeI ? Array.newInstance(l.P(typeI), 0).getClass() : type2;
            }
            if (!l.o0(type)) {
                return type2;
            }
            if (type2 instanceof TypeVariable) {
                ParameterizedType parameterizedType2 = (ParameterizedType) l.Y(type);
                TypeVariable typeVariable = (TypeVariable) type2;
                TypeVariable<Class<?>>[] typeParameters2 = l.P(parameterizedType2).getTypeParameters();
                for (int i8 = 0; i8 < typeParameters2.length; i8++) {
                    if (typeParameters2[i8].getName().equals(typeVariable.getName())) {
                        return parameterizedType2.getActualTypeArguments()[i8];
                    }
                }
            }
            if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType3 = (ParameterizedType) type2;
                Type[] actualTypeArguments = parameterizedType3.getActualTypeArguments();
                boolean zE = e(actualTypeArguments, map);
                if (!zE) {
                    if (type instanceof ParameterizedType) {
                        parameterizedType = (ParameterizedType) type;
                        typeParameters = cls.getTypeParameters();
                    } else if (cls.getGenericSuperclass() instanceof ParameterizedType) {
                        parameterizedType = (ParameterizedType) cls.getGenericSuperclass();
                        typeParameters = cls.getSuperclass().getTypeParameters();
                    } else {
                        typeParameters = type.getClass().getTypeParameters();
                        parameterizedType = parameterizedType3;
                    }
                    zE = f(actualTypeArguments, typeParameters, parameterizedType.getActualTypeArguments());
                }
                if (zE) {
                    return new h(actualTypeArguments, parameterizedType3.getOwnerType(), parameterizedType3.getRawType());
                }
            }
        }
        return type2;
    }

    public static Type k(Class<?> cls, Type type, TypeVariable<?> typeVariable) {
        Type[] actualTypeArguments;
        Class<?> cls2 = typeVariable.getGenericDeclaration() instanceof Class ? (Class) typeVariable.getGenericDeclaration() : null;
        if (cls2 == cls) {
            actualTypeArguments = type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments() : null;
        } else {
            Type[] typeArr = null;
            while (cls != null && cls != Object.class && cls != cls2) {
                Type genericSuperclass = cls.getGenericSuperclass();
                if (genericSuperclass instanceof ParameterizedType) {
                    Type[] actualTypeArguments2 = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
                    f(actualTypeArguments2, cls.getTypeParameters(), typeArr);
                    typeArr = actualTypeArguments2;
                }
                cls = cls.getSuperclass();
            }
            actualTypeArguments = typeArr;
        }
        if (actualTypeArguments == null || cls2 == null) {
            return null;
        }
        TypeVariable<Class<?>>[] typeParameters = cls2.getTypeParameters();
        for (int i8 = 0; i8 < typeParameters.length; i8++) {
            if (typeVariable.equals(typeParameters[i8])) {
                return actualTypeArguments[i8];
            }
        }
        return null;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(c cVar) {
        Method method = cVar.f6170e;
        if (method != null && this.f6170e != null && method.isBridge() && !this.f6170e.isBridge() && cVar.f6170e.getName().equals(this.f6170e.getName())) {
            return 1;
        }
        int i8 = this.f6172g;
        int i9 = cVar.f6172g;
        if (i8 < i9) {
            return -1;
        }
        if (i8 > i9) {
            return 1;
        }
        int iCompareTo = this.f6169d.compareTo(cVar.f6169d);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        Class<?> clsG = g();
        Class<?> clsG2 = cVar.g();
        if (clsG != null && clsG2 != null && clsG != clsG2) {
            if (clsG.isAssignableFrom(clsG2)) {
                return -1;
            }
            if (clsG2.isAssignableFrom(clsG)) {
                return 1;
            }
        }
        Field field = this.f6171f;
        boolean z7 = false;
        boolean z8 = field != null && field.getType() == this.f6173h;
        Field field2 = cVar.f6171f;
        if (field2 != null && field2.getType() == cVar.f6173h) {
            z7 = true;
        }
        if (z8 && !z7) {
            return 1;
        }
        if (z7 && !z8) {
            return -1;
        }
        if (cVar.f6173h.isPrimitive() && !this.f6173h.isPrimitive()) {
            return 1;
        }
        if (this.f6173h.isPrimitive() && !cVar.f6173h.isPrimitive()) {
            return -1;
        }
        if (cVar.f6173h.getName().startsWith("java.") && !this.f6173h.getName().startsWith("java.")) {
            return 1;
        }
        if (!this.f6173h.getName().startsWith("java.") || cVar.f6173h.getName().startsWith("java.")) {
            return this.f6173h.getName().compareTo(cVar.f6173h.getName());
        }
        return -1;
    }

    public char[] b() {
        int length = this.f6169d.length();
        char[] cArr = new char[length + 3];
        String str = this.f6169d;
        str.getChars(0, str.length(), cArr, 1);
        cArr[0] = '\"';
        cArr[length + 1] = '\"';
        cArr[length + 2] = ':';
        return cArr;
    }

    public Object c(Object obj) throws IllegalAccessException, InvocationTargetException {
        Method method = this.f6170e;
        return method != null ? method.invoke(obj, new Object[0]) : this.f6171f.get(obj);
    }

    public d2.b d() {
        d2.b bVar = this.f6180o;
        return bVar != null ? bVar : this.f6181p;
    }

    public Class<?> g() {
        Method method = this.f6170e;
        if (method != null) {
            return method.getDeclaringClass();
        }
        Field field = this.f6171f;
        if (field != null) {
            return field.getDeclaringClass();
        }
        return null;
    }

    public String j() {
        return this.f6188w;
    }

    public Member l() {
        Method method = this.f6170e;
        return method != null ? method : this.f6171f;
    }

    public final long m(String str, d2.b bVar) {
        return (bVar == null || bVar.name().length() == 0) ? l.J(str) : l.K(str);
    }

    public void n(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        Method method = this.f6170e;
        if (method != null) {
            method.invoke(obj, obj2);
        } else {
            this.f6171f.set(obj, obj2);
        }
    }

    public void o() throws SecurityException {
        Method method = this.f6170e;
        if (method != null) {
            l.K0(method);
        } else {
            l.K0(this.f6171f);
        }
    }

    public String toString() {
        return this.f6169d;
    }

    public c(String str, Method method, Field field, Class<?> cls, Type type, int i8, int i9, int i10, d2.b bVar, d2.b bVar2, String str2) {
        this(str, method, field, cls, type, i8, i9, i10, bVar, bVar2, str2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x013d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c(java.lang.String r14, java.lang.reflect.Method r15, java.lang.reflect.Field r16, java.lang.Class<?> r17, java.lang.reflect.Type r18, int r19, int r20, int r21, d2.b r22, d2.b r23, java.lang.String r24, java.util.Map<java.lang.reflect.TypeVariable, java.lang.reflect.Type> r25) {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l2.c.<init>(java.lang.String, java.lang.reflect.Method, java.lang.reflect.Field, java.lang.Class, java.lang.reflect.Type, int, int, int, d2.b, d2.b, java.lang.String, java.util.Map):void");
    }
}
