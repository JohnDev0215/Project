package com.ts.car.core.provider;

import android.util.Log;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import com.ts.car.core.cache.ServiceScanCache;
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
public class AnnotationServiceScanner {
    private static final String TAG = AnnotationServiceScanner.class.getSimpleName();
    private ServiceScanCache mCache;

    public boolean doScan(ServiceScanCache cache) {
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
                            if (clazz != null && clazz.isAnnotationPresent(PlatformService.class)) {
                                Class<?>[] clazzes = clazz.getInterfaces();
                                for (Class<?> interfaceClazz : clazzes) {
                                    saveServiceCache(interfaceClazz, clazz);
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

    private void saveServiceCache(Class<?> interfaceClazz, Class<?> clazz) {
        if ((interfaceClazz == null && clazz == null) || !clazz.isAnnotationPresent(PlatformService.class)) {
            return;
        }
        int modifiers = clazz.getModifiers();
        if ((modifiers & 1024) == 1024 || (modifiers & 512) == 512) {
            Log.w(TAG, clazz.getName() + " is an abstract class!");
            return;
        }
        Constructor<?> constructor = null;
        for (Constructor<?> constructor2 : clazz.getConstructors()) {
            if (constructor2.isAnnotationPresent(PlatformConstruction.class)) {
                if (constructor != null) {
                    Log.w(TAG, "GetInstanceFunction Annotation MUST ONLY be addressed to ONE static function! " + clazz.getName() + " has multi annotation defined! " + constructor.getName() + " is used.");
                } else {
                    constructor = constructor2;
                }
            }
        }
        this.mCache.addServiceClass(interfaceClazz, new ServiceScanCache.ClassInfo(clazz, constructor));
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
