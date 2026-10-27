package net.bytebuddy.dynamic.loading;

import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.description.type.TypeDescription;

/* JADX INFO: loaded from: classes3.dex */
public abstract class InjectionClassLoader extends ClassLoader {
    public abstract Class<?> defineClass(String str, byte[] bArr) throws ClassNotFoundException;

    protected InjectionClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    public enum Strategy implements ClassLoadingStrategy<InjectionClassLoader> {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy
        public /* bridge */ /* synthetic */ Map load(ClassLoader classLoader, Map map) {
            return load((InjectionClassLoader) classLoader, (Map<TypeDescription, byte[]>) map);
        }

        public Map<TypeDescription, Class<?>> load(InjectionClassLoader injectionClassLoader, Map<TypeDescription, byte[]> map) {
            if (injectionClassLoader == null) {
                throw new IllegalArgumentException("Cannot add types to bootstrap class loader: " + map);
            }
            HashMap map2 = new HashMap();
            try {
                for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
                    map2.put(entry.getKey(), injectionClassLoader.defineClass(entry.getKey().getName(), entry.getValue()));
                }
                return map2;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Cannot load classes: " + map, e);
            }
        }
    }
}
