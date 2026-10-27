package net.bytebuddy.agent.builder;

import java.lang.instrument.ClassFileTransformer;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.implementation.auxiliary.TypeProxy;

/* JADX INFO: loaded from: classes3.dex */
public class LambdaFactory {
    public static final Map<ClassFileTransformer, LambdaFactory> CLASS_FILE_TRANSFORMERS = new ConcurrentHashMap();
    private static final String FIELD_NAME = "CLASS_FILE_TRANSFORMERS";
    private final Method dispatcher;
    private final Object target;

    protected boolean canEqual(Object obj) {
        return obj instanceof LambdaFactory;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LambdaFactory)) {
            return false;
        }
        LambdaFactory lambdaFactory = (LambdaFactory) obj;
        if (!lambdaFactory.canEqual(this)) {
            return false;
        }
        Object obj2 = this.target;
        Object obj3 = lambdaFactory.target;
        if (obj2 != null ? !obj2.equals(obj3) : obj3 != null) {
            return false;
        }
        Method method = this.dispatcher;
        Method method2 = lambdaFactory.dispatcher;
        return method != null ? method.equals(method2) : method2 == null;
    }

    public int hashCode() {
        Object obj = this.target;
        int iHashCode = obj == null ? 43 : obj.hashCode();
        Method method = this.dispatcher;
        return ((iHashCode + 59) * 59) + (method != null ? method.hashCode() : 43);
    }

    public LambdaFactory(Object obj, Method method) {
        this.target = obj;
        this.dispatcher = method;
    }

    public static boolean register(ClassFileTransformer classFileTransformer, Object obj) {
        boolean zIsEmpty;
        try {
            TypeDescription.ForLoadedType forLoadedType = new TypeDescription.ForLoadedType(LambdaFactory.class);
            Class<?> cls = ClassInjector.UsingReflection.ofSystemClassLoader().inject(Collections.singletonMap(forLoadedType, ClassFileLocator.ForClassLoader.read(LambdaFactory.class).resolve())).get(forLoadedType);
            Map map = (Map) cls.getField(FIELD_NAME).get(null);
            synchronized (map) {
                try {
                    try {
                        zIsEmpty = map.isEmpty();
                        map.put(classFileTransformer, cls.getConstructor(Object.class, Method.class).newInstance(obj, obj.getClass().getMethod(TypeProxy.REFLECTION_METHOD, Object.class, String.class, Object.class, Object.class, Object.class, Object.class, Boolean.TYPE, List.class, List.class, Collection.class)));
                    } catch (Throwable th) {
                        map.put(classFileTransformer, cls.getConstructor(Object.class, Method.class).newInstance(obj, obj.getClass().getMethod(TypeProxy.REFLECTION_METHOD, Object.class, String.class, Object.class, Object.class, Object.class, Object.class, Boolean.TYPE, List.class, List.class, Collection.class)));
                        throw th;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            return zIsEmpty;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("Could not register class file transformer", e2);
        }
    }

    public static boolean release(ClassFileTransformer classFileTransformer) {
        boolean z;
        try {
            Map map = (Map) ClassLoader.getSystemClassLoader().loadClass(LambdaFactory.class.getName()).getField(FIELD_NAME).get(null);
            synchronized (map) {
                z = map.remove(classFileTransformer) != null && map.isEmpty();
            }
            return z;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("Could not release class file transformer", e2);
        }
    }

    private byte[] invoke(Object obj, String str, Object obj2, Object obj3, Object obj4, Object obj5, boolean z, List<Class<?>> list, List<?> list2, Collection<ClassFileTransformer> collection) {
        try {
            return (byte[]) this.dispatcher.invoke(this.target, obj, str, obj2, obj3, obj4, obj5, Boolean.valueOf(z), list, list2, collection);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("Cannot create class for lambda expression", e2);
        }
    }

    public static byte[] make(Object obj, String str, Object obj2, Object obj3, Object obj4, Object obj5, boolean z, List<Class<?>> list, List<?> list2) {
        Map<ClassFileTransformer, LambdaFactory> map = CLASS_FILE_TRANSFORMERS;
        return map.values().iterator().next().invoke(obj, str, obj2, obj3, obj4, obj5, z, list, list2, map.keySet());
    }
}
