package E5;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: renamed from: E5.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0181f implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1806a;

    public /* synthetic */ C0181f(int i7) {
        this.f1806a = i7;
    }

    public static void b(Type type, Class cls) {
        Class<?> clsE = M.e(type);
        if (cls.isAssignableFrom(clsE)) {
            throw new IllegalArgumentException("No JsonAdapter for " + type + ", you should probably use " + cls.getSimpleName() + " instead of " + clsE.getSimpleName() + " (Moshi only supports the collection interfaces by default) or else register a custom JsonAdapter.");
        }
    }

    @Override // E5.p
    public final q a(Type type, Set set, H h10) {
        M c0183h;
        o oVar;
        Class clsE;
        Type[] actualTypeArguments;
        G5.b bVarC;
        Constructor<?> declaredConstructor;
        Object[] objArr;
        Type typeH = type;
        Class<?> cls = null;
        int i7 = 0;
        int i10 = 1;
        switch (this.f1806a) {
            case 0:
                Type genericComponentType = typeH instanceof GenericArrayType ? ((GenericArrayType) typeH).getGenericComponentType() : typeH instanceof Class ? ((Class) typeH).getComponentType() : null;
                if (genericComponentType == null || !set.isEmpty()) {
                    return null;
                }
                Class clsE2 = M.e(genericComponentType);
                h10.getClass();
                return new C0182g(clsE2, h10.c(genericComponentType, G5.f.f2685a, null)).c();
            case 1:
                if (!(typeH instanceof Class) && !(typeH instanceof ParameterizedType)) {
                    return null;
                }
                Class clsE3 = M.e(type);
                if (clsE3.isInterface() || clsE3.isEnum() || !set.isEmpty()) {
                    return null;
                }
                if (G5.f.d(clsE3)) {
                    b(typeH, List.class);
                    b(typeH, Set.class);
                    b(typeH, Map.class);
                    b(typeH, Collection.class);
                    String str = "Platform " + clsE3;
                    if (typeH instanceof ParameterizedType) {
                        str = str + " in " + typeH;
                    }
                    throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.h(str, " requires explicit JsonAdapter to be registered"));
                }
                if (clsE3.isAnonymousClass()) {
                    throw new IllegalArgumentException("Cannot serialize anonymous class ".concat(clsE3.getName()));
                }
                if (clsE3.isLocalClass()) {
                    throw new IllegalArgumentException("Cannot serialize local class ".concat(clsE3.getName()));
                }
                if (clsE3.getEnclosingClass() != null && !Modifier.isStatic(clsE3.getModifiers())) {
                    throw new IllegalArgumentException("Cannot serialize non-static nested class ".concat(clsE3.getName()));
                }
                if (Modifier.isAbstract(clsE3.getModifiers())) {
                    throw new IllegalArgumentException("Cannot serialize abstract class ".concat(clsE3.getName()));
                }
                Class<? extends Annotation> cls2 = G5.f.d;
                if (cls2 != null && clsE3.isAnnotationPresent(cls2)) {
                    throw new IllegalArgumentException("Cannot serialize Kotlin type " + clsE3.getName() + ". Reflective serialization of Kotlin classes without using kotlin-reflect has undefined and unexpected behavior. Please use KotlinJsonAdapterFactory from the moshi-kotlin artifact or use code gen from the moshi-kotlin-codegen artifact.");
                }
                try {
                    try {
                        try {
                            try {
                                Constructor declaredConstructor2 = clsE3.getDeclaredConstructor(null);
                                declaredConstructor2.setAccessible(true);
                                c0183h = new C0183h(declaredConstructor2, clsE3, i7);
                            } catch (Exception unused) {
                                throw new IllegalArgumentException("cannot construct instances of ".concat(clsE3.getName()));
                            }
                        } catch (NoSuchMethodException unused2) {
                            Class<?> cls3 = Class.forName("sun.misc.Unsafe");
                            Field declaredField = cls3.getDeclaredField("theUnsafe");
                            declaredField.setAccessible(true);
                            c0183h = new C0184i(cls3.getMethod("allocateInstance", Class.class), declaredField.get(null), clsE3);
                        }
                    } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException unused3) {
                        Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                        declaredMethod.setAccessible(true);
                        int iIntValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                        Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                        declaredMethod2.setAccessible(true);
                        c0183h = new C0185j(declaredMethod2, clsE3, iIntValue);
                    } catch (IllegalAccessException unused4) {
                        throw new AssertionError();
                    }
                    break;
                } catch (IllegalAccessException unused5) {
                    throw new AssertionError();
                } catch (NoSuchMethodException unused6) {
                    Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    declaredMethod3.setAccessible(true);
                    c0183h = new C0183h(declaredMethod3, clsE3, i10);
                    break;
                } catch (InvocationTargetException e10) {
                    G5.f.i(e10);
                    throw null;
                }
                TreeMap treeMap = new TreeMap();
                while (typeH != Object.class) {
                    Class clsE4 = M.e(typeH);
                    boolean zD = G5.f.d(clsE4);
                    Field[] declaredFields = clsE4.getDeclaredFields();
                    int length = declaredFields.length;
                    for (int i11 = i7; i11 < length; i11++) {
                        Field field = declaredFields[i11];
                        int modifiers = field.getModifiers();
                        if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers) && ((Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers) || !zD) && ((oVar = (o) field.getAnnotation(o.class)) == null || !oVar.ignore()))) {
                            Type typeH2 = G5.f.h(typeH, clsE4, field.getGenericType(), new LinkedHashSet());
                            Set setE = G5.f.e(field.getAnnotations());
                            String name = field.getName();
                            q qVarC = h10.c(typeH2, setE, name);
                            field.setAccessible(true);
                            if (oVar != null) {
                                String strName = oVar.name();
                                if (!"\u0000".equals(strName)) {
                                    name = strName;
                                }
                            }
                            C0186k c0186k = (C0186k) treeMap.put(name, new C0186k(name, field, qVarC));
                            if (c0186k != null) {
                                throw new IllegalArgumentException("Conflicting fields:\n    " + c0186k.f1817b + "\n    " + field);
                            }
                        }
                    }
                    Class clsE5 = M.e(typeH);
                    typeH = G5.f.h(typeH, clsE5, clsE5.getGenericSuperclass(), new LinkedHashSet());
                    i7 = 0;
                }
                return new C0187l(c0183h, treeMap).c();
            case 2:
                Class clsE6 = M.e(type);
                if (!set.isEmpty()) {
                    return null;
                }
                if (clsE6 == List.class || clsE6 == Collection.class) {
                    Type typeB = M.b(type);
                    h10.getClass();
                    return new C0188m(h10.c(typeB, G5.f.f2685a, null), 0).c();
                }
                if (clsE6 != Set.class) {
                    return null;
                }
                Type typeB2 = M.b(type);
                h10.getClass();
                return new C0188m(h10.c(typeB2, G5.f.f2685a, null), 1).c();
            case 3:
                if (!set.isEmpty() || (clsE = M.e(type)) != Map.class) {
                    return null;
                }
                if (typeH == Properties.class) {
                    actualTypeArguments = new Type[]{String.class, String.class};
                } else {
                    if (!Map.class.isAssignableFrom(clsE)) {
                        throw new IllegalArgumentException();
                    }
                    Type typeH3 = G5.f.h(typeH, clsE, G5.f.c(typeH, clsE, Map.class), new LinkedHashSet());
                    actualTypeArguments = typeH3 instanceof ParameterizedType ? ((ParameterizedType) typeH3).getActualTypeArguments() : new Type[]{Object.class, Object.class};
                }
                return new C0182g(h10, actualTypeArguments[0], actualTypeArguments[1]).c();
            case 4:
                return null;
            default:
                if (!set.isEmpty()) {
                    return null;
                }
                Class cls4 = Boolean.TYPE;
                J j3 = M.f1783b;
                if (typeH == cls4) {
                    return j3;
                }
                Class cls5 = Byte.TYPE;
                J j10 = M.c;
                if (typeH == cls5) {
                    return j10;
                }
                Class cls6 = Character.TYPE;
                J j11 = M.d;
                if (typeH == cls6) {
                    return j11;
                }
                Class cls7 = Double.TYPE;
                J j12 = M.f1784e;
                if (typeH == cls7) {
                    return j12;
                }
                Class cls8 = Float.TYPE;
                J j13 = M.f1785f;
                if (typeH == cls8) {
                    return j13;
                }
                Class cls9 = Integer.TYPE;
                J j14 = M.f1786g;
                if (typeH == cls9) {
                    return j14;
                }
                Class cls10 = Long.TYPE;
                J j15 = M.f1787h;
                if (typeH == cls10) {
                    return j15;
                }
                Class cls11 = Short.TYPE;
                J j16 = M.f1788i;
                if (typeH == cls11) {
                    return j16;
                }
                if (typeH == Boolean.class) {
                    return j3.c();
                }
                if (typeH == Byte.class) {
                    return j10.c();
                }
                if (typeH == Character.class) {
                    return j11.c();
                }
                if (typeH == Double.class) {
                    return j12.c();
                }
                if (typeH == Float.class) {
                    return j13.c();
                }
                if (typeH == Integer.class) {
                    return j14.c();
                }
                if (typeH == Long.class) {
                    return j15.c();
                }
                if (typeH == Short.class) {
                    return j16.c();
                }
                if (typeH == String.class) {
                    return M.f1789j.c();
                }
                if (typeH == Object.class) {
                    return new L(h10).c();
                }
                Class clsE7 = M.e(type);
                Set set2 = G5.f.f2685a;
                r rVar = (r) clsE7.getAnnotation(r.class);
                if (rVar != null && rVar.generateAdapter()) {
                    try {
                        try {
                            Class<?> cls12 = Class.forName(clsE7.getName().replace("$", "_") + "JsonAdapter", true, clsE7.getClassLoader());
                            try {
                                if (typeH instanceof ParameterizedType) {
                                    Type[] actualTypeArguments2 = ((ParameterizedType) typeH).getActualTypeArguments();
                                    try {
                                        declaredConstructor = cls12.getDeclaredConstructor(H.class, Type[].class);
                                        objArr = new Object[]{h10, actualTypeArguments2};
                                    } catch (NoSuchMethodException unused7) {
                                        declaredConstructor = cls12.getDeclaredConstructor(Type[].class);
                                        objArr = new Object[]{actualTypeArguments2};
                                    }
                                } else {
                                    try {
                                        declaredConstructor = cls12.getDeclaredConstructor(H.class);
                                        objArr = new Object[]{h10};
                                    } catch (NoSuchMethodException unused8) {
                                        declaredConstructor = cls12.getDeclaredConstructor(null);
                                        objArr = new Object[0];
                                    }
                                }
                                declaredConstructor.setAccessible(true);
                                bVarC = ((q) declaredConstructor.newInstance(objArr)).c();
                            } catch (NoSuchMethodException e11) {
                                e = e11;
                                cls = cls12;
                                if ((typeH instanceof ParameterizedType) || cls.getTypeParameters().length == 0) {
                                    throw new RuntimeException("Failed to find the generated JsonAdapter constructor for " + typeH, e);
                                }
                                throw new RuntimeException("Failed to find the generated JsonAdapter constructor for '" + typeH + "'. Suspiciously, the type was not parameterized but the target class '" + cls.getCanonicalName() + "' is generic. Consider using Types#newParameterizedType() to define these missing type variables.", e);
                            }
                        } catch (NoSuchMethodException e12) {
                            e = e12;
                        }
                    } catch (ClassNotFoundException e13) {
                        throw new RuntimeException("Failed to find the generated JsonAdapter class for " + typeH, e13);
                    } catch (IllegalAccessException e14) {
                        throw new RuntimeException("Failed to access the generated JsonAdapter for " + typeH, e14);
                    } catch (InstantiationException e15) {
                        throw new RuntimeException("Failed to instantiate the generated JsonAdapter for " + typeH, e15);
                    } catch (InvocationTargetException e16) {
                        G5.f.i(e16);
                        throw null;
                    }
                    break;
                } else {
                    bVarC = null;
                }
                if (bVarC != null) {
                    return bVarC;
                }
                if (clsE7.isEnum()) {
                    return new K(clsE7).c();
                }
                return null;
        }
    }
}
