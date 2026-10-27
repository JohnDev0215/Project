package com.sun.xml.bind.v2.model.nav;

import com.sun.xml.bind.v2.runtime.Location;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collection;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
final class ReflectionNavigator implements Navigator<Type, Class, Field, Method> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ReflectionNavigator INSTANCE = new ReflectionNavigator();
    private static final TypeVisitor<Type, Class> baseClassFinder = new TypeVisitor<Type, Class>() { // from class: com.sun.xml.bind.v2.model.nav.ReflectionNavigator.1
        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Type onGenericArray(GenericArrayType genericArrayType, Class cls) {
            return null;
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Type onWildcard(WildcardType wildcardType, Class cls) {
            return null;
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Type onClass(Class cls, Class cls2) {
            Type typeVisit;
            if (cls2 == cls) {
                return cls2;
            }
            Type genericSuperclass = cls.getGenericSuperclass();
            if (genericSuperclass != null && (typeVisit = visit(genericSuperclass, cls2)) != null) {
                return typeVisit;
            }
            for (Type type : cls.getGenericInterfaces()) {
                Type typeVisit2 = visit(type, cls2);
                if (typeVisit2 != null) {
                    return typeVisit2;
                }
            }
            return null;
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Type onParameterizdType(ParameterizedType parameterizedType, Class cls) {
            Class cls2 = (Class) parameterizedType.getRawType();
            if (cls2 == cls) {
                return parameterizedType;
            }
            Type genericSuperclass = cls2.getGenericSuperclass();
            if (genericSuperclass != null) {
                genericSuperclass = visit(bind(genericSuperclass, cls2, parameterizedType), cls);
            }
            if (genericSuperclass != null) {
                return genericSuperclass;
            }
            for (Type type : cls2.getGenericInterfaces()) {
                Type typeVisit = visit(bind(type, cls2, parameterizedType), cls);
                if (typeVisit != null) {
                    return typeVisit;
                }
            }
            return null;
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Type onVariable(TypeVariable typeVariable, Class cls) {
            return visit(typeVariable.getBounds()[0], cls);
        }

        private Type bind(Type type, GenericDeclaration genericDeclaration, ParameterizedType parameterizedType) {
            return (Type) ReflectionNavigator.binder.visit(type, new BinderArg(genericDeclaration, parameterizedType.getActualTypeArguments()));
        }
    };
    private static final TypeVisitor<Type, BinderArg> binder = new TypeVisitor<Type, BinderArg>() { // from class: com.sun.xml.bind.v2.model.nav.ReflectionNavigator.2
        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Type onClass(Class cls, BinderArg binderArg) {
            return cls;
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Type onParameterizdType(ParameterizedType parameterizedType, BinderArg binderArg) {
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            int i = 0;
            boolean z = false;
            while (true) {
                boolean z2 = true;
                if (i >= actualTypeArguments.length) {
                    break;
                }
                Type type = actualTypeArguments[i];
                Type typeVisit = visit(type, binderArg);
                actualTypeArguments[i] = typeVisit;
                if (type == typeVisit) {
                    z2 = false;
                }
                z |= z2;
                i++;
            }
            Type ownerType = parameterizedType.getOwnerType();
            if (ownerType != null) {
                ownerType = visit(ownerType, binderArg);
            }
            return !(z | (parameterizedType.getOwnerType() != ownerType)) ? parameterizedType : new ParameterizedTypeImpl((Class) parameterizedType.getRawType(), actualTypeArguments, ownerType);
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Type onGenericArray(GenericArrayType genericArrayType, BinderArg binderArg) {
            Type typeVisit = visit(genericArrayType.getGenericComponentType(), binderArg);
            return typeVisit == genericArrayType.getGenericComponentType() ? genericArrayType : new GenericArrayTypeImpl(typeVisit);
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Type onVariable(TypeVariable typeVariable, BinderArg binderArg) {
            return binderArg.replace(typeVariable);
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Type onWildcard(WildcardType wildcardType, BinderArg binderArg) {
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type[] upperBounds = wildcardType.getUpperBounds();
            int i = 0;
            boolean z = false;
            while (true) {
                boolean z2 = true;
                if (i >= lowerBounds.length) {
                    break;
                }
                Type type = lowerBounds[i];
                Type typeVisit = visit(type, binderArg);
                lowerBounds[i] = typeVisit;
                if (type == typeVisit) {
                    z2 = false;
                }
                z |= z2;
                i++;
            }
            for (int i2 = 0; i2 < upperBounds.length; i2++) {
                Type type2 = upperBounds[i2];
                Type typeVisit2 = visit(type2, binderArg);
                upperBounds[i2] = typeVisit2;
                z |= type2 != typeVisit2;
            }
            return !z ? wildcardType : new WildcardTypeImpl(lowerBounds, upperBounds);
        }
    };
    private static final TypeVisitor<Class, Void> eraser = new TypeVisitor<Class, Void>() { // from class: com.sun.xml.bind.v2.model.nav.ReflectionNavigator.6
        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Class onClass(Class cls, Void r2) {
            return cls;
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Class onParameterizdType(ParameterizedType parameterizedType, Void r2) {
            return visit(parameterizedType.getRawType(), null);
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Class onGenericArray(GenericArrayType genericArrayType, Void r2) {
            return Array.newInstance((Class<?>) visit(genericArrayType.getGenericComponentType(), null), 0).getClass();
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Class onVariable(TypeVariable typeVariable, Void r2) {
            return visit(typeVariable.getBounds()[0], null);
        }

        @Override // com.sun.xml.bind.v2.model.nav.TypeVisitor
        public Class onWildcard(WildcardType wildcardType, Void r2) {
            return visit(wildcardType.getUpperBounds()[0], null);
        }
    };

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Class asDecl(Class cls) {
        return cls;
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Type getPrimitive(Class cls) {
        return cls;
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    /* JADX INFO: renamed from: ref, reason: merged with bridge method [inline-methods] */
    public Type ref2(Class cls) {
        return cls;
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Class use(Class cls) {
        return cls;
    }

    static ReflectionNavigator getInstance() {
        return INSTANCE;
    }

    private ReflectionNavigator() {
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Class getSuperClass(Class cls) {
        if (cls == Object.class) {
            return null;
        }
        Class superclass = cls.getSuperclass();
        return superclass == null ? Object.class : superclass;
    }

    private static class BinderArg {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final Type[] args;
        final TypeVariable[] params;

        BinderArg(TypeVariable[] typeVariableArr, Type[] typeArr) {
            this.params = typeVariableArr;
            this.args = typeArr;
        }

        public BinderArg(GenericDeclaration genericDeclaration, Type[] typeArr) {
            this(genericDeclaration.getTypeParameters(), typeArr);
        }

        Type replace(TypeVariable typeVariable) {
            int i = 0;
            while (true) {
                TypeVariable[] typeVariableArr = this.params;
                if (i >= typeVariableArr.length) {
                    return typeVariable;
                }
                if (typeVariableArr[i].equals(typeVariable)) {
                    return this.args[i];
                }
                i++;
            }
        }
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Type getBaseClass(Type type, Class cls) {
        return baseClassFinder.visit(type, cls);
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public String getClassName(Class cls) {
        return cls.getName();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public String getTypeName(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (cls.isArray()) {
                return getTypeName((Type) cls.getComponentType()) + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            }
            return cls.getName();
        }
        return type.toString();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public String getClassShortName(Class cls) {
        return cls.getSimpleName();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Collection<? extends Field> getDeclaredFields(final Class cls) {
        return Arrays.asList((Field[]) AccessController.doPrivileged(new PrivilegedAction<Field[]>() { // from class: com.sun.xml.bind.v2.model.nav.ReflectionNavigator.3
            @Override // java.security.PrivilegedAction
            public Field[] run() {
                return cls.getDeclaredFields();
            }
        }));
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Field getDeclaredField(final Class cls, final String str) {
        return (Field) AccessController.doPrivileged(new PrivilegedAction<Field>() { // from class: com.sun.xml.bind.v2.model.nav.ReflectionNavigator.4
            @Override // java.security.PrivilegedAction
            public Field run() {
                try {
                    return cls.getDeclaredField(str);
                } catch (NoSuchFieldException unused) {
                    return null;
                }
            }
        });
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Collection<? extends Method> getDeclaredMethods(final Class cls) {
        return Arrays.asList((Method[]) AccessController.doPrivileged(new PrivilegedAction<Method[]>() { // from class: com.sun.xml.bind.v2.model.nav.ReflectionNavigator.5
            @Override // java.security.PrivilegedAction
            public Method[] run() {
                return cls.getDeclaredMethods();
            }
        }));
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Class getDeclaringClassForField(Field field) {
        return field.getDeclaringClass();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Class getDeclaringClassForMethod(Method method) {
        return method.getDeclaringClass();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Type getFieldType(Field field) {
        if (field.getType().isArray()) {
            Class<?> componentType = field.getType().getComponentType();
            if (componentType.isPrimitive()) {
                return Array.newInstance(componentType, 0).getClass();
            }
        }
        return fix(field.getGenericType());
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public String getFieldName(Field field) {
        return field.getName();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public String getMethodName(Method method) {
        return method.getName();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Type getReturnType(Method method) {
        return fix(method.getGenericReturnType());
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Type[] getMethodParameters(Method method) {
        return method.getGenericParameterTypes();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isStaticMethod(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isFinalMethod(Method method) {
        return Modifier.isFinal(method.getModifiers());
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isSubClassOf(Type type, Type type2) {
        return erasure(type2).isAssignableFrom(erasure(type));
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Class asDecl(Type type) {
        return erasure(type);
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public <T> Class<T> erasure(Type type) {
        return eraser.visit(type, null);
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isAbstract(Class cls) {
        return Modifier.isAbstract(cls.getModifiers());
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isFinal(Class cls) {
        return Modifier.isFinal(cls.getModifiers());
    }

    public Type createParameterizedType(Class cls, Type... typeArr) {
        return new ParameterizedTypeImpl(cls, typeArr, null);
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isArray(Type type) {
        if (type instanceof Class) {
            return ((Class) type).isArray();
        }
        return type instanceof GenericArrayType;
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isArrayButNotByteArray(Type type) {
        if (!(type instanceof Class)) {
            return (type instanceof GenericArrayType) && ((GenericArrayType) type).getGenericComponentType() != Byte.TYPE;
        }
        Class cls = (Class) type;
        return cls.isArray() && cls != byte[].class;
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Type getComponentType(Type type) {
        if (type instanceof Class) {
            return ((Class) type).getComponentType();
        }
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        throw new IllegalArgumentException();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Type getTypeArgument(Type type, int i) {
        if (type instanceof ParameterizedType) {
            return fix(((ParameterizedType) type).getActualTypeArguments()[i]);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isParameterizedType(Type type) {
        return type instanceof ParameterizedType;
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isPrimitive(Type type) {
        if (type instanceof Class) {
            return ((Class) type).isPrimitive();
        }
        return false;
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Location getClassLocation(final Class cls) {
        return new Location() { // from class: com.sun.xml.bind.v2.model.nav.ReflectionNavigator.7
            @Override // com.sun.xml.bind.v2.runtime.Location
            public String toString() {
                return cls.getName();
            }
        };
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Location getFieldLocation(final Field field) {
        return new Location() { // from class: com.sun.xml.bind.v2.model.nav.ReflectionNavigator.8
            @Override // com.sun.xml.bind.v2.runtime.Location
            public String toString() {
                return field.toString();
            }
        };
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Location getMethodLocation(final Method method) {
        return new Location() { // from class: com.sun.xml.bind.v2.model.nav.ReflectionNavigator.9
            @Override // com.sun.xml.bind.v2.runtime.Location
            public String toString() {
                return method.toString();
            }
        };
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean hasDefaultConstructor(Class cls) {
        try {
            cls.getDeclaredConstructor(new Class[0]);
            return true;
        } catch (NoSuchMethodException unused) {
            return false;
        }
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isStaticField(Field field) {
        return Modifier.isStatic(field.getModifiers());
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isPublicMethod(Method method) {
        return Modifier.isPublic(method.getModifiers());
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isPublicField(Field field) {
        return Modifier.isPublic(field.getModifiers());
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isEnum(Class cls) {
        return Enum.class.isAssignableFrom(cls);
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Field[] getEnumConstants(Class cls) {
        try {
            Object[] enumConstants = cls.getEnumConstants();
            Field[] fieldArr = new Field[enumConstants.length];
            for (int i = 0; i < enumConstants.length; i++) {
                fieldArr[i] = cls.getField(((Enum) enumConstants[i]).name());
            }
            return fieldArr;
        } catch (NoSuchFieldException e) {
            throw new NoSuchFieldError(e.getMessage());
        }
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Type getVoidType() {
        return Void.class;
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public String getPackageName(Class cls) {
        String name = cls.getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf < 0 ? "" : name.substring(0, iLastIndexOf);
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public Class loadObjectFactory(Class cls, String str) {
        ClassLoader classClassLoader = SecureLoader.getClassClassLoader(cls);
        if (classClassLoader == null) {
            classClassLoader = SecureLoader.getSystemClassLoader();
        }
        try {
            return classClassLoader.loadClass(str + ".ObjectFactory");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isBridgeMethod(Method method) {
        return method.isBridge();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isOverriding(Method method, final Class cls) {
        final String name = method.getName();
        final Class<?>[] parameterTypes = method.getParameterTypes();
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: com.sun.xml.bind.v2.model.nav.ReflectionNavigator.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public Boolean run() {
                for (Class superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                    try {
                        if (superclass.getDeclaredMethod(name, parameterTypes) != null) {
                            return Boolean.TRUE;
                        }
                        continue;
                    } catch (NoSuchMethodException unused) {
                    }
                }
                return Boolean.FALSE;
            }
        })).booleanValue();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isInterface(Class cls) {
        return cls.isInterface();
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isTransient(Field field) {
        return Modifier.isTransient(field.getModifiers());
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isInnerClass(Class cls) {
        return (cls.getEnclosingClass() == null || Modifier.isStatic(cls.getModifiers())) ? false : true;
    }

    @Override // com.sun.xml.bind.v2.model.nav.Navigator
    public boolean isSameType(Type type, Type type2) {
        return type.equals(type2);
    }

    private Type fix(Type type) {
        if (!(type instanceof GenericArrayType)) {
            return type;
        }
        GenericArrayType genericArrayType = (GenericArrayType) type;
        return genericArrayType.getGenericComponentType() instanceof Class ? Array.newInstance((Class<?>) genericArrayType.getGenericComponentType(), 0).getClass() : type;
    }
}
