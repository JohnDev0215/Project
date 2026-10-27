package com.ts.car.core.cache;

import com.ts.car.core.annotation.RuntimeType;
import com.ts.car.core.cache.FoundationScanCache;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FoundationScanCache {
    private static FoundationScanCache gInstance;
    private Map<Class<?>, List<ClassInfo>> mFoundationCacheMap = new HashMap();
    private Map<Class<?>, List<ClassInfo>> mServiceCacheMap = new HashMap();
    private boolean mScanned = false;

    public static class ClassInfo {
        public Class<?> clazz;
        public Constructor constructor;
        public String sceneName;
        public int sequence;
        public RuntimeType type;

        public ClassInfo(Class<?> clazz, Constructor method) {
            this.clazz = clazz;
            this.constructor = method;
        }

        public ClassInfo(Class<?> clazz, Constructor method, int sequence, RuntimeType type, String sceneName) {
            this.clazz = clazz;
            this.constructor = method;
            this.sequence = sequence;
            this.type = type;
            this.sceneName = sceneName;
        }
    }

    public static synchronized FoundationScanCache getInstance() {
        if (gInstance == null) {
            gInstance = new FoundationScanCache();
        }
        return gInstance;
    }

    private FoundationScanCache() {
    }

    public synchronized void clear() {
        this.mFoundationCacheMap.clear();
        this.mServiceCacheMap.clear();
        this.mScanned = false;
    }

    public boolean isScanned() {
        return this.mScanned;
    }

    public synchronized void addFoundationClass(Class<?> interfaceClazz, ClassInfo classInfo) {
        if (interfaceClazz == null || classInfo == null) {
            return;
        }
        addInterfaceClass(this.mFoundationCacheMap, interfaceClazz, classInfo);
    }

    public synchronized List<ClassInfo> getFoundationClassInfo(Class<?> interfaceClazz) {
        return this.mFoundationCacheMap.get(interfaceClazz);
    }

    public synchronized void addServiceClass(Class<?> interfaceClazz, ClassInfo classInfo) {
        if (interfaceClazz == null || classInfo == null) {
            return;
        }
        addInterfaceClass(this.mServiceCacheMap, interfaceClazz, classInfo);
    }

    public synchronized List<ClassInfo> getServiceClassInfo(Class<?> interfaceClazz) {
        return this.mServiceCacheMap.get(interfaceClazz);
    }

    private void addInterfaceClass(Map<Class<?>, List<ClassInfo>> cacheMap, Class<?> interfaceClazz, ClassInfo classInfo) {
        List<ClassInfo> list = cacheMap.get(interfaceClazz);
        if (list == null) {
            list = new ArrayList();
            cacheMap.put(interfaceClazz, list);
        }
        list.add(classInfo);
        list.sort(new Comparator() { // from class: com.ts.car.core.cache.-$$Lambda$FoundationScanCache$quFTXX00ywQkcE_AyC2cgFgwYNM
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Integer.compare(((FoundationScanCache.ClassInfo) obj).sequence, ((FoundationScanCache.ClassInfo) obj2).sequence);
            }
        });
        this.mScanned = true;
    }
}
