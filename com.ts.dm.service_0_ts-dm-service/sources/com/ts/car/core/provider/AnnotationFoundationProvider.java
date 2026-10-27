package com.ts.car.core.provider;

import com.ts.car.core.cache.FoundationScanCache;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AnnotationFoundationProvider implements IFoundationProvider {
    @Override // com.ts.car.core.provider.IFoundationProvider
    public <T> T create(Class<T> cls, Object... objArr) {
        List<FoundationScanCache.ClassInfo> foundationClassInfo;
        FoundationScanCache foundationScanCache = FoundationScanCache.getInstance();
        if (!foundationScanCache.isScanned()) {
            synchronized (FoundationScanCache.class) {
                new AnnotationFoundationScanner().doScan(foundationScanCache);
            }
        }
        if (!foundationScanCache.isScanned() || (foundationClassInfo = foundationScanCache.getFoundationClassInfo(cls)) == null || foundationClassInfo.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<FoundationScanCache.ClassInfo> it = foundationClassInfo.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            FoundationScanCache.ClassInfo next = it.next();
            if (cls.isAssignableFrom(next.clazz) && checkParameters(next, objArr)) {
                arrayList.add(next.constructor);
                break;
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (T) createInstance((Constructor) arrayList.get(0), cls, objArr);
    }

    @Override // com.ts.car.core.provider.IFoundationProvider
    public <T> T[] creates(Class<T> cls, Object... objArr) {
        List<FoundationScanCache.ClassInfo> foundationClassInfo;
        FoundationScanCache foundationScanCache = FoundationScanCache.getInstance();
        if (!foundationScanCache.isScanned()) {
            synchronized (FoundationScanCache.class) {
                new AnnotationFoundationScanner().doScan(foundationScanCache);
            }
        }
        if (!foundationScanCache.isScanned() || (foundationClassInfo = foundationScanCache.getFoundationClassInfo(cls)) == null || foundationClassInfo.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (FoundationScanCache.ClassInfo classInfo : foundationClassInfo) {
            if (cls.isAssignableFrom(classInfo.clazz) && checkParameters(classInfo, objArr)) {
                arrayList.add(classInfo.constructor);
            }
        }
        return (T[]) createInstances(arrayList, cls, objArr);
    }

    private <T> T createInstance(Constructor constructor, Class<T> clazz, Object... args) {
        try {
            Object obj = constructor.newInstance(args);
            return clazz.cast(obj);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private <T> T[] createInstances(List<Constructor> list, Class<T> cls, Object... objArr) {
        if (list.size() == 0) {
            return null;
        }
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, list.size()));
        for (int i = 0; i < list.size(); i++) {
            try {
                tArr[i] = cls.cast(list.get(i).newInstance(objArr));
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return tArr;
    }

    private boolean checkParameters(FoundationScanCache.ClassInfo classInfo, Object... args) {
        Class<?>[] param = classInfo.constructor.getParameterTypes();
        Class<?>[] paramClasses = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            paramClasses[i] = args[i].getClass();
        }
        return isParamsEqual(param, paramClasses);
    }

    private boolean isParamsEqual(Class<?>[] definedClasses, Class<?>[] paramsClasses) {
        if (definedClasses == null && paramsClasses == null) {
            return true;
        }
        if (definedClasses == null || paramsClasses == null || definedClasses.length != paramsClasses.length) {
            return false;
        }
        for (int i = 0; i < definedClasses.length; i++) {
            if (definedClasses[i] != paramsClasses[i] && !definedClasses[i].isAssignableFrom(paramsClasses[i])) {
                if (definedClasses[i].isPrimitive()) {
                    Class<?> typeClass = getPrimitiveClass(definedClasses[i]);
                    if (paramsClasses[i] == typeClass) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    private Class<?> getPrimitiveClass(Class<?> clazz) {
        String name = clazz.getName();
        if (name.equals("int")) {
            return Integer.class;
        }
        if (name.equals("boolean")) {
            return Boolean.class;
        }
        if (name.equals("char")) {
            return Character.class;
        }
        if (name.equals("byte")) {
            return Byte.class;
        }
        if (name.equals("short")) {
            return Short.class;
        }
        if (name.equals("long")) {
            return Long.class;
        }
        if (name.equals("float")) {
            return Float.class;
        }
        if (name.equals("double")) {
            return Double.class;
        }
        return null;
    }
}
