package b.a.a.n;

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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f430b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Field f431c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f432d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Class<?> f433e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Type f434f;
    public final Class<?> g;
    public final boolean h;
    public final int i;
    public final int j;
    public final String k;
    public final b.a.a.h.b l;
    public final b.a.a.h.b m;
    public final boolean n;
    public final boolean o;
    public final char[] p;
    public final boolean q;
    public final boolean r;
    public final boolean s;
    public final String t;
    public final String[] u;
    public final long v;

    public c(String str, Class<?> cls, Class<?> cls2, Type type, Field field, int i, int i2, int i3) {
        this.f432d = 0;
        i = i < 0 ? 0 : i;
        this.f429a = str;
        this.g = cls;
        this.f433e = cls2;
        this.f434f = type;
        this.f430b = null;
        this.f431c = field;
        this.f432d = i;
        this.i = i2;
        this.j = i3;
        this.q = cls2.isEnum();
        if (field != null) {
            int modifiers = field.getModifiers();
            int i4 = modifiers & 1;
            this.n = true;
            this.o = Modifier.isTransient(modifiers);
        } else {
            this.o = false;
            this.n = false;
        }
        this.p = b();
        if (field != null) {
            l.K0(field);
        }
        this.k = "";
        b.a.a.h.b bVar = field == null ? null : (b.a.a.h.b) l.N(field, b.a.a.h.b.class);
        this.l = bVar;
        this.m = null;
        this.h = false;
        this.r = false;
        this.s = false;
        this.t = null;
        this.u = new String[0];
        this.v = m(str, bVar);
    }

    public static boolean e(Type[] typeArr, Map<TypeVariable, Type> map) {
        if (map == null || map.size() == 0) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < typeArr.length; i++) {
            Type type = typeArr[i];
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (e(actualTypeArguments, map)) {
                    typeArr[i] = new h(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType());
                    z = true;
                }
            } else if ((type instanceof TypeVariable) && map.containsKey(type)) {
                typeArr[i] = map.get(type);
                z = true;
            }
        }
        return z;
    }

    public static boolean f(Type[] typeArr, TypeVariable[] typeVariableArr, Type[] typeArr2) {
        if (typeArr2 == null || typeVariableArr.length == 0) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < typeArr.length; i++) {
            Type type = typeArr[i];
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (f(actualTypeArguments, typeVariableArr, typeArr2)) {
                    typeArr[i] = new h(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType());
                    z = true;
                }
            } else if (type instanceof TypeVariable) {
                for (int i2 = 0; i2 < typeVariableArr.length; i2++) {
                    if (type.equals(typeVariableArr[i2])) {
                        typeArr[i] = typeArr2[i2];
                        z = true;
                    }
                }
            }
        }
        return z;
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
                for (int i = 0; i < typeParameters2.length; i++) {
                    if (typeParameters2[i].getName().equals(typeVariable.getName())) {
                        return parameterizedType2.getActualTypeArguments()[i];
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
        for (int i = 0; i < typeParameters.length; i++) {
            if (typeVariable.equals(typeParameters[i])) {
                return actualTypeArguments[i];
            }
        }
        return null;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(c cVar) {
        Method method = cVar.f430b;
        if (method != null && this.f430b != null && method.isBridge() && !this.f430b.isBridge() && cVar.f430b.getName().equals(this.f430b.getName())) {
            return 1;
        }
        int i = this.f432d;
        int i2 = cVar.f432d;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int iCompareTo = this.f429a.compareTo(cVar.f429a);
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
        Field field = this.f431c;
        boolean z = false;
        boolean z2 = field != null && field.getType() == this.f433e;
        Field field2 = cVar.f431c;
        if (field2 != null && field2.getType() == cVar.f433e) {
            z = true;
        }
        if (z2 && !z) {
            return 1;
        }
        if (z && !z2) {
            return -1;
        }
        if (cVar.f433e.isPrimitive() && !this.f433e.isPrimitive()) {
            return 1;
        }
        if (this.f433e.isPrimitive() && !cVar.f433e.isPrimitive()) {
            return -1;
        }
        if (cVar.f433e.getName().startsWith("java.") && !this.f433e.getName().startsWith("java.")) {
            return 1;
        }
        if (!this.f433e.getName().startsWith("java.") || cVar.f433e.getName().startsWith("java.")) {
            return this.f433e.getName().compareTo(cVar.f433e.getName());
        }
        return -1;
    }

    public char[] b() {
        int length = this.f429a.length();
        char[] cArr = new char[length + 3];
        String str = this.f429a;
        str.getChars(0, str.length(), cArr, 1);
        cArr[0] = '\"';
        cArr[length + 1] = '\"';
        cArr[length + 2] = ':';
        return cArr;
    }

    public Object c(Object obj) throws IllegalAccessException, InvocationTargetException {
        Method method = this.f430b;
        return method != null ? method.invoke(obj, new Object[0]) : this.f431c.get(obj);
    }

    public b.a.a.h.b d() {
        b.a.a.h.b bVar = this.l;
        return bVar != null ? bVar : this.m;
    }

    public Class<?> g() {
        Method method = this.f430b;
        if (method != null) {
            return method.getDeclaringClass();
        }
        Field field = this.f431c;
        if (field != null) {
            return field.getDeclaringClass();
        }
        return null;
    }

    public String j() {
        return this.t;
    }

    public Member l() {
        Method method = this.f430b;
        return method != null ? method : this.f431c;
    }

    public final long m(String str, b.a.a.h.b bVar) {
        return (bVar == null || bVar.name().length() == 0) ? l.J(str) : l.K(str);
    }

    public void n(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        Method method = this.f430b;
        if (method != null) {
            method.invoke(obj, obj2);
        } else {
            this.f431c.set(obj, obj2);
        }
    }

    public void o() throws SecurityException {
        Method method = this.f430b;
        if (method != null) {
            l.K0(method);
        } else {
            l.K0(this.f431c);
        }
    }

    public String toString() {
        return this.f429a;
    }

    public c(String str, Method method, Field field, Class<?> cls, Type type, int i, int i2, int i3, b.a.a.h.b bVar, b.a.a.h.b bVar2, String str2) {
        this(str, method, field, cls, type, i, i2, i3, bVar, bVar2, str2, null);
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
    public c(java.lang.String r14, java.lang.reflect.Method r15, java.lang.reflect.Field r16, java.lang.Class<?> r17, java.lang.reflect.Type r18, int r19, int r20, int r21, b.a.a.h.b r22, b.a.a.h.b r23, java.lang.String r24, java.util.Map<java.lang.reflect.TypeVariable, java.lang.reflect.Type> r25) {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.n.c.<init>(java.lang.String, java.lang.reflect.Method, java.lang.reflect.Field, java.lang.Class, java.lang.reflect.Type, int, int, int, b.a.a.h.b, b.a.a.h.b, java.lang.String, java.util.Map):void");
    }
}
