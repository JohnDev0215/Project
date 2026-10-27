package p;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public abstract class ds2 {
    public static final Set<Annotation> a = Collections.emptySet();
    public static final Type[] b = new Type[0];
    public static final Class<?> c;
    private static final Class<? extends Annotation> d;
    private static final Map<Class<?>, Class<?>> e;

    public static final class a implements GenericArrayType {
        private final Type c;

        public a(Type type) {
            this.c = ds2.a(type);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && zo2.d(this, (GenericArrayType) obj);
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.c;
        }

        public int hashCode() {
            return this.c.hashCode();
        }

        public String toString() {
            return ds2.v(this.c) + "[]";
        }
    }

    public static final class b implements ParameterizedType {
        private final Type c;
        private final Type e;
        public final Type[] k;

        public b(Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Class<?> enclosingClass = ((Class) type2).getEnclosingClass();
                if (type != null) {
                    if (enclosingClass == null || zo2.g(type) != enclosingClass) {
                        throw new IllegalArgumentException("unexpected owner type for " + type2 + ": " + type);
                    }
                } else if (enclosingClass != null) {
                    throw new IllegalArgumentException("unexpected owner type for " + type2 + ": null");
                }
            }
            this.c = type == null ? null : ds2.a(type);
            this.e = ds2.a(type2);
            this.k = (Type[]) typeArr.clone();
            int i = 0;
            while (true) {
                Type[] typeArr2 = this.k;
                if (i >= typeArr2.length) {
                    return;
                }
                typeArr2[i].getClass();
                ds2.b(this.k[i]);
                Type[] typeArr3 = this.k;
                typeArr3[i] = ds2.a(typeArr3[i]);
                i++;
            }
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && zo2.d(this, (ParameterizedType) obj);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.k.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.c;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.e;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.k) ^ this.e.hashCode()) ^ ds2.g(this.c);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((this.k.length + 1) * 30);
            sb.append(ds2.v(this.e));
            if (this.k.length == 0) {
                return sb.toString();
            }
            sb.append("<");
            sb.append(ds2.v(this.k[0]));
            for (int i = 1; i < this.k.length; i++) {
                sb.append(", ");
                sb.append(ds2.v(this.k[i]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    public static final class c implements WildcardType {
        private final Type c;
        private final Type e;

        public c(Type[] typeArr, Type[] typeArr2) {
            if (typeArr2.length > 1) {
                throw new IllegalArgumentException();
            }
            if (typeArr.length != 1) {
                throw new IllegalArgumentException();
            }
            if (typeArr2.length != 1) {
                typeArr[0].getClass();
                ds2.b(typeArr[0]);
                this.e = null;
                this.c = ds2.a(typeArr[0]);
                return;
            }
            typeArr2[0].getClass();
            ds2.b(typeArr2[0]);
            if (typeArr[0] != Object.class) {
                throw new IllegalArgumentException();
            }
            this.e = ds2.a(typeArr2[0]);
            this.c = Object.class;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && zo2.d(this, (WildcardType) obj);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type type = this.e;
            return type != null ? new Type[]{type} : ds2.b;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.c};
        }

        public int hashCode() {
            Type type = this.e;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.c.hashCode() + 31);
        }

        public String toString() {
            if (this.e != null) {
                return "? super " + ds2.v(this.e);
            }
            if (this.c == Object.class) {
                return "?";
            }
            return "? extends " + ds2.v(this.c);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Class<? extends Annotation> cls;
        try {
            cls = Class.forName(getKotlinMetadataClassName());
        } catch (ClassNotFoundException unused) {
            cls = 0;
        }
        d = cls;
        c = DefaultConstructorMarker.class;
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put(Boolean.TYPE, Boolean.class);
        linkedHashMap.put(Byte.TYPE, Byte.class);
        linkedHashMap.put(Character.TYPE, Character.class);
        linkedHashMap.put(Double.TYPE, Double.class);
        linkedHashMap.put(Float.TYPE, Float.class);
        linkedHashMap.put(Integer.TYPE, Integer.class);
        linkedHashMap.put(Long.TYPE, Long.class);
        linkedHashMap.put(Short.TYPE, Short.class);
        linkedHashMap.put(Void.TYPE, Void.class);
        e = Collections.unmodifiableMap(linkedHashMap);
    }

    public static Type a(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new a(a(cls.getComponentType())) : cls;
        }
        if (type instanceof ParameterizedType) {
            if (type instanceof b) {
                return type;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new b(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            return type instanceof a ? type : new a(((GenericArrayType) type).getGenericComponentType());
        }
        if (!(type instanceof WildcardType) || (type instanceof c)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        return new c(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
    }

    public static void b(Type type) {
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            throw new IllegalArgumentException("Unexpected primitive " + type + ". Use the boxed type.");
        }
    }

    public static Class<?> c(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    public static com.squareup.moshi.f<?> d(com.squareup.moshi.o oVar, Type type, Class<?> cls) {
        Constructor<?> declaredConstructor;
        Object[] objArr;
        c71 c71Var = (c71) cls.getAnnotation(c71.class);
        Class<?> cls2 = null;
        if (c71Var == null || !c71Var.generateAdapter()) {
            return null;
        }
        try {
            try {
                cls2 = Class.forName(zo2.e(cls.getName()), true, cls.getClassLoader());
                if (type instanceof ParameterizedType) {
                    Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                    try {
                        declaredConstructor = cls2.getDeclaredConstructor(com.squareup.moshi.o.class, Type[].class);
                        objArr = new Object[]{oVar, actualTypeArguments};
                    } catch (NoSuchMethodException unused) {
                        declaredConstructor = cls2.getDeclaredConstructor(Type[].class);
                        objArr = new Object[]{actualTypeArguments};
                    }
                } else {
                    try {
                        declaredConstructor = cls2.getDeclaredConstructor(com.squareup.moshi.o.class);
                        objArr = new Object[]{oVar};
                    } catch (NoSuchMethodException unused2) {
                        declaredConstructor = cls2.getDeclaredConstructor(new Class[0]);
                        objArr = new Object[0];
                    }
                }
                declaredConstructor.setAccessible(true);
                return ((com.squareup.moshi.f) declaredConstructor.newInstance(objArr)).nullSafe();
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException("Failed to find the generated JsonAdapter class for " + type, e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("Failed to access the generated JsonAdapter for " + type, e3);
            } catch (InstantiationException e4) {
                throw new RuntimeException("Failed to instantiate the generated JsonAdapter for " + type, e4);
            } catch (InvocationTargetException e5) {
                throw t(e5);
            }
        } catch (NoSuchMethodException e6) {
            if ((type instanceof ParameterizedType) || cls2.getTypeParameters().length == 0) {
                throw new RuntimeException("Failed to find the generated JsonAdapter constructor for " + type, e6);
            }
            throw new RuntimeException("Failed to find the generated JsonAdapter constructor for '" + type + "'. Suspiciously, the type was not parameterized but the target class '" + cls2.getCanonicalName() + "' is generic. Consider using Types#newParameterizedType() to define these missing type variables.", e6);
        }
    }

    public static Type e(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                Class<?> cls3 = interfaces[i];
                if (cls3 == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(cls3)) {
                    return e(cls.getGenericInterfaces()[i], interfaces[i], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<? super Object> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return e(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public static boolean f(Annotation[] annotationArr) {
        for (Annotation annotation : annotationArr) {
            if (annotation.annotationType().getSimpleName().equals("Nullable")) {
                return true;
            }
        }
        return false;
    }

    public static int g(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static String getKotlinMetadataClassName() {
        return "kotlin.Metadata";
    }

    public static int h(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public static boolean i(Class<?> cls) {
        Class<? extends Annotation> cls2 = d;
        return cls2 != null && cls.isAnnotationPresent(cls2);
    }

    public static boolean j(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith("android.") || name.startsWith("androidx.") || name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("kotlin.") || name.startsWith("kotlinx.") || name.startsWith("scala.");
    }

    public static Set<? extends Annotation> k(AnnotatedElement annotatedElement) {
        return l(annotatedElement.getAnnotations());
    }

    public static Set<? extends Annotation> l(Annotation[] annotationArr) {
        LinkedHashSet linkedHashSet = null;
        for (Annotation annotation : annotationArr) {
            if (annotation.annotationType().isAnnotationPresent(s71.class)) {
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.add(annotation);
            }
        }
        return linkedHashSet != null ? Collections.unmodifiableSet(linkedHashSet) : a;
    }

    public static String m(String str, AnnotatedElement annotatedElement) {
        return n(str, (z61) annotatedElement.getAnnotation(z61.class));
    }

    public static String n(String str, z61 z61Var) {
        if (z61Var == null) {
            return str;
        }
        String strName = z61Var.name();
        return "\u0000".equals(strName) ? str : strName;
    }

    public static JsonDataException o(String str, String str2, JsonReader jsonReader) {
        String strJ = jsonReader.J();
        return new JsonDataException(str2.equals(str) ? String.format("Required value '%s' missing at %s", str, strJ) : String.format("Required value '%s' (JSON name '%s') missing at %s", str, str2, strJ));
    }

    public static Type p(Type type) {
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        if (wildcardType.getLowerBounds().length != 0) {
            return type;
        }
        Type[] upperBounds = wildcardType.getUpperBounds();
        if (upperBounds.length == 1) {
            return upperBounds[0];
        }
        throw new IllegalArgumentException();
    }

    public static Type q(Type type, Class<?> cls, Type type2) {
        return r(type, cls, type2, new LinkedHashSet());
    }

    private static Type r(Type type, Class<?> cls, Type type2, Collection<TypeVariable<?>> collection) {
        while (type2 instanceof TypeVariable) {
            TypeVariable<?> typeVariable = (TypeVariable) type2;
            if (collection.contains(typeVariable)) {
                return type2;
            }
            collection.add(typeVariable);
            type2 = s(type, cls, typeVariable);
            if (type2 == typeVariable) {
                return type2;
            }
        }
        if (type2 instanceof Class) {
            Class cls2 = (Class) type2;
            if (cls2.isArray()) {
                Class<?> componentType = cls2.getComponentType();
                Type typeR = r(type, cls, componentType, collection);
                return componentType == typeR ? cls2 : zo2.b(typeR);
            }
        }
        if (type2 instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type2;
            Type genericComponentType = genericArrayType.getGenericComponentType();
            Type typeR2 = r(type, cls, genericComponentType, collection);
            return genericComponentType == typeR2 ? genericArrayType : zo2.b(typeR2);
        }
        if (type2 instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type2;
            Type ownerType = parameterizedType.getOwnerType();
            Type typeR3 = r(type, cls, ownerType, collection);
            boolean z = typeR3 != ownerType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            int length = actualTypeArguments.length;
            for (int i = 0; i < length; i++) {
                Type typeR4 = r(type, cls, actualTypeArguments[i], collection);
                if (typeR4 != actualTypeArguments[i]) {
                    if (!z) {
                        actualTypeArguments = (Type[]) actualTypeArguments.clone();
                        z = true;
                    }
                    actualTypeArguments[i] = typeR4;
                }
            }
            return z ? new b(typeR3, parameterizedType.getRawType(), actualTypeArguments) : parameterizedType;
        }
        boolean z2 = type2 instanceof WildcardType;
        Type type3 = type2;
        if (z2) {
            WildcardType wildcardType = (WildcardType) type2;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (lowerBounds.length == 1) {
                Type typeR5 = r(type, cls, lowerBounds[0], collection);
                type3 = wildcardType;
                if (typeR5 != lowerBounds[0]) {
                    return zo2.l(typeR5);
                }
            } else {
                type3 = wildcardType;
                if (upperBounds.length == 1) {
                    Type typeR6 = r(type, cls, upperBounds[0], collection);
                    type3 = wildcardType;
                    if (typeR6 != upperBounds[0]) {
                        return zo2.k(typeR6);
                    }
                }
            }
        }
        return type3;
    }

    public static Type s(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class<?> clsC = c(typeVariable);
        if (clsC == null) {
            return typeVariable;
        }
        Type typeE = e(type, cls, clsC);
        if (!(typeE instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) typeE).getActualTypeArguments()[h(clsC.getTypeParameters(), typeVariable)];
    }

    public static RuntimeException t(InvocationTargetException invocationTargetException) {
        Throwable targetException = invocationTargetException.getTargetException();
        if (targetException instanceof RuntimeException) {
            throw ((RuntimeException) targetException);
        }
        if (targetException instanceof Error) {
            throw ((Error) targetException);
        }
        throw new RuntimeException(targetException);
    }

    public static String u(Type type, Set<? extends Annotation> set) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        if (set.isEmpty()) {
            str = " (with no annotations)";
        } else {
            str = " annotated " + set;
        }
        sb.append(str);
        return sb.toString();
    }

    public static String v(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static JsonDataException w(String str, String str2, JsonReader jsonReader) {
        String strJ = jsonReader.J();
        return new JsonDataException(str2.equals(str) ? String.format("Non-null value '%s' was null at %s", str, strJ) : String.format("Non-null value '%s' (JSON name '%s') was null at %s", str, str2, strJ));
    }
}
