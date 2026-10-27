package com.android.dx.stock;

import com.android.dx.Code;
import com.android.dx.Comparison;
import com.android.dx.DexMaker;
import com.android.dx.Label;
import com.android.dx.Local;
import com.android.dx.MethodId;
import com.android.dx.TypeId;
import com.android.dx.rop.code.RegisterSpec;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes.dex */
public final class ProxyBuilder<T> {
    private static final String FIELD_NAME_HANDLER = "$__handler";
    private static final String FIELD_NAME_METHODS = "$__methodArray";
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_BOXED;
    private static final Map<Class<?>, MethodId<?, ?>> PRIMITIVE_TO_UNBOX_METHOD;
    private static final Map<TypeId<?>, MethodId<?, ?>> PRIMITIVE_TYPE_TO_UNBOX_METHOD;
    public static final int VERSION = 1;
    private static final Map<Class<?>, Class<?>> generatedProxyClasses = Collections.synchronizedMap(new HashMap());
    private final Class<T> baseClass;
    private File dexCache;
    private InvocationHandler handler;
    private Method[] methods;
    private boolean sharedClassLoader;
    private ClassLoader parentClassLoader = ProxyBuilder.class.getClassLoader();
    private Class<?>[] constructorArgTypes = new Class[0];
    private Object[] constructorArgValues = new Object[0];
    private Set<Class<?>> interfaces = new HashSet();

    static {
        HashMap map = new HashMap();
        PRIMITIVE_TO_BOXED = map;
        map.put(Boolean.TYPE, Boolean.class);
        map.put(Integer.TYPE, Integer.class);
        map.put(Byte.TYPE, Byte.class);
        map.put(Long.TYPE, Long.class);
        map.put(Short.TYPE, Short.class);
        map.put(Float.TYPE, Float.class);
        map.put(Double.TYPE, Double.class);
        map.put(Character.TYPE, Character.class);
        PRIMITIVE_TYPE_TO_UNBOX_METHOD = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            TypeId<?> typeId = TypeId.get((Class) entry.getKey());
            TypeId typeId2 = TypeId.get((Class) entry.getValue());
            PRIMITIVE_TYPE_TO_UNBOX_METHOD.put(typeId, typeId2.getMethod(typeId2, "valueOf", typeId));
        }
        HashMap map2 = new HashMap();
        map2.put(Boolean.TYPE, TypeId.get(Boolean.class).getMethod(TypeId.BOOLEAN, "booleanValue", new TypeId[0]));
        map2.put(Integer.TYPE, TypeId.get(Integer.class).getMethod(TypeId.INT, "intValue", new TypeId[0]));
        map2.put(Byte.TYPE, TypeId.get(Byte.class).getMethod(TypeId.BYTE, "byteValue", new TypeId[0]));
        map2.put(Long.TYPE, TypeId.get(Long.class).getMethod(TypeId.LONG, "longValue", new TypeId[0]));
        map2.put(Short.TYPE, TypeId.get(Short.class).getMethod(TypeId.SHORT, "shortValue", new TypeId[0]));
        map2.put(Float.TYPE, TypeId.get(Float.class).getMethod(TypeId.FLOAT, "floatValue", new TypeId[0]));
        map2.put(Double.TYPE, TypeId.get(Double.class).getMethod(TypeId.DOUBLE, "doubleValue", new TypeId[0]));
        map2.put(Character.TYPE, TypeId.get(Character.class).getMethod(TypeId.CHAR, "charValue", new TypeId[0]));
        PRIMITIVE_TO_UNBOX_METHOD = map2;
    }

    private ProxyBuilder(Class<T> cls) {
        this.baseClass = cls;
    }

    public static <T> ProxyBuilder<T> forClass(Class<T> cls) {
        return new ProxyBuilder<>(cls);
    }

    public ProxyBuilder<T> parentClassLoader(ClassLoader classLoader) {
        this.parentClassLoader = classLoader;
        return this;
    }

    public ProxyBuilder<T> handler(InvocationHandler invocationHandler) {
        this.handler = invocationHandler;
        return this;
    }

    public ProxyBuilder<T> dexCache(File file) {
        File file2 = new File(file, RegisterSpec.PREFIX + Integer.toString(1));
        this.dexCache = file2;
        file2.mkdir();
        return this;
    }

    public ProxyBuilder<T> implementing(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            if (!cls.isInterface()) {
                throw new IllegalArgumentException("Not an interface: " + cls.getName());
            }
            this.interfaces.add(cls);
        }
        return this;
    }

    public ProxyBuilder<T> constructorArgValues(Object... objArr) {
        this.constructorArgValues = objArr;
        return this;
    }

    public ProxyBuilder<T> constructorArgTypes(Class<?>... clsArr) {
        this.constructorArgTypes = clsArr;
        return this;
    }

    public ProxyBuilder<T> onlyMethods(Method[] methodArr) {
        this.methods = methodArr;
        return this;
    }

    public ProxyBuilder<T> withSharedClassLoader() {
        this.sharedClassLoader = true;
        return this;
    }

    public T build() throws IOException {
        check(this.handler != null, "handler == null");
        check(this.constructorArgTypes.length == this.constructorArgValues.length, "constructorArgValues.length != constructorArgTypes.length");
        try {
            try {
                T tNewInstance = buildProxyClass().getConstructor(this.constructorArgTypes).newInstance(this.constructorArgValues);
                setInvocationHandler(tNewInstance, this.handler);
                return tNewInstance;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InstantiationException e2) {
                throw new AssertionError(e2);
            } catch (InvocationTargetException e3) {
                throw launderCause(e3);
            }
        } catch (NoSuchMethodException unused) {
            throw new IllegalArgumentException("No constructor for " + this.baseClass.getName() + " with parameter types " + Arrays.toString(this.constructorArgTypes));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Class<? extends T> buildProxyClass() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dx.stock.ProxyBuilder.buildProxyClass():java.lang.Class");
    }

    private Class<? extends T> loadClass(ClassLoader classLoader, String str) throws ClassNotFoundException {
        return (Class<? extends T>) classLoader.loadClass(str);
    }

    private static RuntimeException launderCause(InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause instanceof Error) {
            throw ((Error) cause);
        }
        if (cause instanceof RuntimeException) {
            throw ((RuntimeException) cause);
        }
        throw new UndeclaredThrowableException(cause);
    }

    private static void setMethodsStaticField(Class<?> cls, Method[] methodArr) {
        try {
            Field declaredField = cls.getDeclaredField(FIELD_NAME_METHODS);
            declaredField.setAccessible(true);
            declaredField.set(null, methodArr);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (NoSuchFieldException e2) {
            throw new AssertionError(e2);
        }
    }

    public static InvocationHandler getInvocationHandler(Object obj) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(FIELD_NAME_HANDLER);
            declaredField.setAccessible(true);
            return (InvocationHandler) declaredField.get(obj);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (NoSuchFieldException e2) {
            throw new IllegalArgumentException("Not a valid proxy instance", e2);
        }
    }

    public static void setInvocationHandler(Object obj, InvocationHandler invocationHandler) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(FIELD_NAME_HANDLER);
            declaredField.setAccessible(true);
            declaredField.set(obj, invocationHandler);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (NoSuchFieldException e2) {
            throw new IllegalArgumentException("Not a valid proxy instance", e2);
        }
    }

    public static boolean isProxyClass(Class<?> cls) {
        try {
            cls.getDeclaredField(FIELD_NAME_HANDLER);
            return true;
        } catch (NoSuchFieldException unused) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T, G extends T> void generateCodeForAllMethods(DexMaker dexMaker, TypeId<G> typeId, Method[] methodArr, TypeId<T> typeId2) {
        MethodId methodId;
        DexMaker dexMaker2 = dexMaker;
        TypeId<G> typeId3 = typeId;
        Method[] methodArr2 = methodArr;
        TypeId<V> typeId4 = TypeId.get(InvocationHandler.class);
        TypeId<V> typeId5 = TypeId.get(Method[].class);
        Object field = typeId3.getField(typeId4, FIELD_NAME_HANDLER);
        Object field2 = typeId3.getField(typeId5, FIELD_NAME_METHODS);
        TypeId<?> typeId6 = TypeId.get(Method.class);
        TypeId<?> typeId7 = TypeId.get(Object[].class);
        MethodId method = typeId4.getMethod(TypeId.OBJECT, "invoke", TypeId.OBJECT, typeId6, typeId7);
        int i = 0;
        Object obj = typeId4;
        Object obj2 = typeId5;
        while (i < methodArr2.length) {
            Method method2 = methodArr2[i];
            String name = method2.getName();
            Class<?>[] parameterTypes = method2.getParameterTypes();
            int length = parameterTypes.length;
            TypeId<?>[] typeIdArr = new TypeId[length];
            for (int i2 = 0; i2 < length; i2++) {
                typeIdArr[i2] = TypeId.get(parameterTypes[i2]);
            }
            Class<?> returnType = method2.getReturnType();
            TypeId<R> typeId8 = TypeId.get(returnType);
            Object obj3 = field;
            MethodId methodId2 = method;
            MethodId method3 = typeId2.getMethod(typeId8, name, typeIdArr);
            Code codeDeclare = dexMaker2.declare(typeId3.getMethod(typeId8, name, typeIdArr), 1);
            Local local = codeDeclare.getThis(typeId3);
            Local localNewLocal = codeDeclare.newLocal(obj);
            Local localNewLocal2 = codeDeclare.newLocal(TypeId.OBJECT);
            Local localNewLocal3 = codeDeclare.newLocal(TypeId.INT);
            Local localNewLocal4 = codeDeclare.newLocal(typeId7);
            TypeId<?> typeId9 = typeId7;
            Local localNewLocal5 = codeDeclare.newLocal(TypeId.INT);
            Local localNewLocal6 = codeDeclare.newLocal(TypeId.OBJECT);
            Local localNewLocal7 = codeDeclare.newLocal(typeId8);
            Local localNewLocal8 = codeDeclare.newLocal(obj2);
            Object obj4 = obj2;
            Local localNewLocal9 = codeDeclare.newLocal(typeId6);
            TypeId<?> typeId10 = typeId6;
            Local localNewLocal10 = codeDeclare.newLocal(TypeId.INT);
            Class<?> cls = PRIMITIVE_TO_BOXED.get(returnType);
            Local localNewLocal11 = cls != null ? codeDeclare.newLocal(TypeId.get(cls)) : null;
            int length2 = parameterTypes.length;
            Local[] localArr = new Local[length2];
            Local localNewLocal12 = codeDeclare.newLocal(typeId8);
            Local localNewLocal13 = codeDeclare.newLocal(obj);
            Object obj5 = obj;
            codeDeclare.loadConstant(localNewLocal10, Integer.valueOf(i));
            codeDeclare.sget(field2, localNewLocal8);
            codeDeclare.aget(localNewLocal9, localNewLocal8, localNewLocal10);
            codeDeclare.loadConstant(localNewLocal5, Integer.valueOf(length));
            codeDeclare.newArray(localNewLocal4, localNewLocal5);
            Object obj6 = obj3;
            codeDeclare.iget(obj6, localNewLocal, local);
            codeDeclare.loadConstant(localNewLocal13, null);
            Label label = new Label();
            codeDeclare.compare(Comparison.EQ, label, localNewLocal13, localNewLocal);
            int i3 = 0;
            while (i3 < length) {
                codeDeclare.loadConstant(localNewLocal3, Integer.valueOf(i3));
                codeDeclare.aput(localNewLocal4, localNewLocal3, boxIfRequired(codeDeclare, codeDeclare.getParameter(i3, typeIdArr[i3]), localNewLocal6));
                i3++;
                obj6 = obj6;
            }
            Object obj7 = obj6;
            codeDeclare.invokeInterface(methodId2, localNewLocal2, localNewLocal, local, localNewLocal9, localNewLocal4);
            generateCodeForReturnStatement(codeDeclare, returnType, localNewLocal2, localNewLocal7, localNewLocal11);
            codeDeclare.mark(label);
            for (int i4 = 0; i4 < length2; i4++) {
                localArr[i4] = codeDeclare.getParameter(i4, typeIdArr[i4]);
            }
            if (Void.TYPE.equals(returnType)) {
                methodId = method3;
                codeDeclare.invokeSuper(methodId, null, local, localArr);
                codeDeclare.returnVoid();
            } else {
                methodId = method3;
                invokeSuper(methodId, codeDeclare, local, localArr, localNewLocal12);
                codeDeclare.returnValue(localNewLocal12);
            }
            Code codeDeclare2 = dexMaker.declare(typeId.getMethod(typeId8, superMethodName(method2), typeIdArr), 1);
            Local<T> local2 = codeDeclare2.getThis(typeId);
            int length3 = parameterTypes.length;
            Local<?>[] localArr2 = new Local[length3];
            for (int i5 = 0; i5 < length3; i5++) {
                localArr2[i5] = codeDeclare2.getParameter(i5, typeIdArr[i5]);
            }
            if (Void.TYPE.equals(returnType)) {
                codeDeclare2.invokeSuper(methodId, null, local2, localArr2);
                codeDeclare2.returnVoid();
            } else {
                Local<T> localNewLocal14 = codeDeclare2.newLocal(typeId8);
                invokeSuper(methodId, codeDeclare2, local2, localArr2, localNewLocal14);
                codeDeclare2.returnValue(localNewLocal14);
            }
            i++;
            methodArr2 = methodArr;
            method = methodId2;
            typeId3 = typeId;
            dexMaker2 = dexMaker;
            field = obj7;
            typeId7 = typeId9;
            obj2 = obj4;
            typeId6 = typeId10;
            obj = obj5;
        }
    }

    private static void invokeSuper(MethodId methodId, Code code, Local local, Local[] localArr, Local local2) {
        code.invokeSuper(methodId, local2, local, localArr);
    }

    private static Local<?> boxIfRequired(Code code, Local<?> local, Local<Object> local2) {
        MethodId<?, ?> methodId = PRIMITIVE_TYPE_TO_UNBOX_METHOD.get(local.getType());
        if (methodId == null) {
            return local;
        }
        code.invokeStatic(methodId, local2, local);
        return local2;
    }

    public static Object callSuper(Object obj, Method method, Object... objArr) throws Throwable {
        try {
            return obj.getClass().getMethod(superMethodName(method), method.getParameterTypes()).invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private static String superMethodName(Method method) {
        return "super$" + method.getName() + "$" + method.getReturnType().getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, '_').replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH, '_').replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INDEXED_TYPE_DELIMITER, '_');
    }

    private static void check(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    private static <T, G extends T> void generateConstructorsAndFields(DexMaker dexMaker, TypeId<G> typeId, TypeId<T> typeId2, Class<T> cls) {
        TypeId<V> typeId3 = TypeId.get(InvocationHandler.class);
        TypeId<V> typeId4 = TypeId.get(Method[].class);
        dexMaker.declare(typeId.getField(typeId3, FIELD_NAME_HANDLER), 2, null);
        dexMaker.declare(typeId.getField(typeId4, FIELD_NAME_METHODS), 10, null);
        for (Constructor constructor : getConstructorsToOverwrite(cls)) {
            if (constructor.getModifiers() != 16) {
                TypeId<?>[] typeIdArrClassArrayToTypeArray = classArrayToTypeArray(constructor.getParameterTypes());
                Code codeDeclare = dexMaker.declare(typeId.getConstructor(typeIdArrClassArrayToTypeArray), 1);
                Local<T> local = codeDeclare.getThis(typeId);
                int length = typeIdArrClassArrayToTypeArray.length;
                Local<?>[] localArr = new Local[length];
                for (int i = 0; i < length; i++) {
                    localArr[i] = codeDeclare.getParameter(i, typeIdArrClassArrayToTypeArray[i]);
                }
                codeDeclare.invokeDirect(typeId2.getConstructor(typeIdArrClassArrayToTypeArray), null, local, localArr);
                codeDeclare.returnVoid();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> Constructor<T>[] getConstructorsToOverwrite(Class<T> cls) {
        return cls.getDeclaredConstructors();
    }

    private TypeId<?>[] getInterfacesAsTypeIds() {
        TypeId<?>[] typeIdArr = new TypeId[this.interfaces.size()];
        Iterator<Class<?>> it = this.interfaces.iterator();
        int i = 0;
        while (it.hasNext()) {
            typeIdArr[i] = TypeId.get(it.next());
            i++;
        }
        return typeIdArr;
    }

    private Method[] getMethodsToProxyRecursive() {
        int i;
        Set<MethodSetEntry> hashSet = new HashSet<>();
        Set<MethodSetEntry> hashSet2 = new HashSet<>();
        for (Class<T> superclass = this.baseClass; superclass != null; superclass = superclass.getSuperclass()) {
            getMethodsToProxy(hashSet, hashSet2, superclass);
        }
        Class<T> superclass2 = this.baseClass;
        while (true) {
            i = 0;
            if (superclass2 == null) {
                break;
            }
            Class<?>[] interfaces = superclass2.getInterfaces();
            int length = interfaces.length;
            while (i < length) {
                getMethodsToProxy(hashSet, hashSet2, interfaces[i]);
                i++;
            }
            superclass2 = superclass2.getSuperclass();
        }
        Iterator<Class<?>> it = this.interfaces.iterator();
        while (it.hasNext()) {
            getMethodsToProxy(hashSet, hashSet2, it.next());
        }
        Method[] methodArr = new Method[hashSet.size()];
        Iterator<MethodSetEntry> it2 = hashSet.iterator();
        while (it2.hasNext()) {
            methodArr[i] = it2.next().originalMethod;
            i++;
        }
        return methodArr;
    }

    private void getMethodsToProxy(Set<MethodSetEntry> set, Set<MethodSetEntry> set2, Class<?> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if ((method.getModifiers() & 16) != 0) {
                MethodSetEntry methodSetEntry = new MethodSetEntry(method);
                set2.add(methodSetEntry);
                set.remove(methodSetEntry);
            } else if ((method.getModifiers() & 8) == 0 && ((Modifier.isPublic(method.getModifiers()) || Modifier.isProtected(method.getModifiers()) || (this.sharedClassLoader && !Modifier.isPrivate(method.getModifiers()))) && (!method.getName().equals("finalize") || method.getParameterTypes().length != 0))) {
                MethodSetEntry methodSetEntry2 = new MethodSetEntry(method);
                if (!set2.contains(methodSetEntry2)) {
                    set.add(methodSetEntry2);
                }
            }
        }
        if (cls.isInterface()) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                getMethodsToProxy(set, set2, cls2);
            }
        }
    }

    private static <T> String getMethodNameForProxyOf(Class<T> cls) {
        return cls.getName().replace(".", "/") + "_Proxy";
    }

    private static TypeId<?>[] classArrayToTypeArray(Class<?>[] clsArr) {
        TypeId<?>[] typeIdArr = new TypeId[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            typeIdArr[i] = TypeId.get(clsArr[i]);
        }
        return typeIdArr;
    }

    private static void generateCodeForReturnStatement(Code code, Class cls, Local local, Local local2, Local local3) {
        if (PRIMITIVE_TO_UNBOX_METHOD.containsKey(cls)) {
            code.cast(local3, local);
            code.invokeVirtual(getUnboxMethodForPrimitive(cls), local2, local3, new Local[0]);
            code.returnValue(local2);
        } else if (Void.TYPE.equals(cls)) {
            code.returnVoid();
        } else {
            code.cast(local2, local);
            code.returnValue(local2);
        }
    }

    private static <T> Set<T> asSet(T... tArr) {
        return new CopyOnWriteArraySet(Arrays.asList(tArr));
    }

    private static MethodId<?, ?> getUnboxMethodForPrimitive(Class<?> cls) {
        return PRIMITIVE_TO_UNBOX_METHOD.get(cls);
    }

    public static class MethodSetEntry {
        public final String name;
        public final Method originalMethod;
        public final Class<?>[] paramTypes;
        public final Class<?> returnType;

        public MethodSetEntry(Method method) {
            this.originalMethod = method;
            this.name = method.getName();
            this.paramTypes = method.getParameterTypes();
            this.returnType = method.getReturnType();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MethodSetEntry)) {
                return false;
            }
            MethodSetEntry methodSetEntry = (MethodSetEntry) obj;
            return this.name.equals(methodSetEntry.name) && this.returnType.equals(methodSetEntry.returnType) && Arrays.equals(this.paramTypes, methodSetEntry.paramTypes);
        }

        public int hashCode() {
            int iHashCode = 527 + this.name.hashCode() + 17;
            int iHashCode2 = iHashCode + (iHashCode * 31) + this.returnType.hashCode();
            return iHashCode2 + (iHashCode2 * 31) + Arrays.hashCode(this.paramTypes);
        }
    }
}
