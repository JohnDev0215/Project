package com.ts.car.core.provider;

import com.ts.car.core.annotation.FoundationComponent;
import com.ts.car.core.annotation.FoundationConstruction;
import com.ts.car.core.annotation.FoundationInterface;
import com.ts.car.core.annotation.RuntimeType;
import com.ts.car.core.cache.FoundationScanCache;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class AnnotationFoundationScanner {
    private static final String TAG = AnnotationFoundationScanner.class.getSimpleName();
    private FoundationScanCache mCache;

    public boolean doScan(FoundationScanCache cache) {
        this.mCache = cache;
        if (cache == null) {
            return false;
        }
        ClassLoader cl = getClass().getClassLoader();
        if (!(cl instanceof BaseDexClassLoader)) {
            return false;
        }
        BaseDexClassLoader dexCl = (BaseDexClassLoader) cl;
        searchAndLoad(dexCl);
        return true;
    }

    private void searchAndLoad(BaseDexClassLoader dexCl) {
        Object[] elements = getDexElements(dexCl);
        if (elements == null) {
            return;
        }
        for (Object object : elements) {
            DexFile dexFile = getDexFile(object);
            if (dexFile != null) {
                Enumeration<String> enumeration = dexFile.entries();
                while (enumeration.hasMoreElements()) {
                    String className = enumeration.nextElement();
                    if (className != null && !className.isEmpty()) {
                        try {
                            Class<?> clazz = getClass().getClassLoader().loadClass(className);
                            if (clazz != null && clazz.isAnnotationPresent(FoundationComponent.class)) {
                                Set<Class<?>> clazzes = findAllInterfaces(clazz);
                                for (Class<?> interfaceClazz : clazzes) {
                                    if (interfaceClazz.getAnnotation(FoundationInterface.class) != null) {
                                        saveFoundationCache(interfaceClazz, clazz);
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private Set<Class<?>> findAllInterfaces(Class<?> clazz) {
        Set<Class<?>> retList = new HashSet<>();
        List<Class<?>> checkList = new ArrayList<>();
        if (clazz.equals(Object.class)) {
            return retList;
        }
        if (clazz.getSuperclass() != null && (!clazz.getSuperclass().equals(Object.class) || !clazz.isInterface())) {
            checkList.add(clazz.getSuperclass());
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces != null) {
            for (Class<?> interClass : interfaces) {
                checkList.add(interClass);
                retList.add(interClass);
            }
        }
        Iterator<Class<?>> it = checkList.iterator();
        while (it.hasNext()) {
            Set<Class<?>> subList = findAllInterfaces(it.next());
            retList.addAll(subList);
        }
        return retList;
    }

    private void saveFoundationCache(Class<?> interfaceClazz, Class<?> clazz) {
        FoundationComponent plugin;
        FoundationComponent plugin2;
        if (!clazz.isAnnotationPresent(FoundationComponent.class) || (plugin = (FoundationComponent) clazz.getAnnotation(FoundationComponent.class)) == null) {
            return;
        }
        int sequence = plugin.sequence();
        RuntimeType type = plugin.runtimeType();
        String sceneName = plugin.sceneName();
        Constructor<?>[] constructors = clazz.getConstructors();
        int length = constructors.length;
        int i = 0;
        while (i < length) {
            Constructor<?> constructor = constructors[i];
            if (constructor.isAnnotationPresent(FoundationConstruction.class)) {
                plugin2 = plugin;
                this.mCache.addFoundationClass(interfaceClazz, new FoundationScanCache.ClassInfo(clazz, constructor, sequence, type, sceneName));
            } else {
                plugin2 = plugin;
            }
            i++;
            plugin = plugin2;
        }
    }

    private Object[] getDexElements(BaseDexClassLoader dexCl) {
        if (dexCl == null) {
            return null;
        }
        Class<?> clazz = dexCl.getClass();
        while (!clazz.equals(BaseDexClassLoader.class)) {
            clazz = clazz.getSuperclass();
            if (clazz == null || clazz == Object.class) {
                return null;
            }
        }
        try {
            Field field = clazz.getDeclaredField("pathList");
            field.setAccessible(true);
            Object pathList = field.get(dexCl);
            if (pathList == null) {
                return null;
            }
            Field field2 = pathList.getClass().getDeclaredField("dexElements");
            field2.setAccessible(true);
            return (Object[]) Object[].class.cast(field2.get(pathList));
        } catch (IllegalAccessException | NoSuchFieldException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private DexFile getDexFile(Object element) {
        try {
            Field field = element.getClass().getDeclaredField("dexFile");
            if (field == null) {
                return null;
            }
            field.setAccessible(true);
            Object dexFile = field.get(element);
            if (dexFile == null) {
                return null;
            }
            return (DexFile) DexFile.class.cast(dexFile);
        } catch (IllegalAccessException | NoSuchFieldException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
