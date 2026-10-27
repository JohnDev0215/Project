package com.qtxa.annotation;

import com.qtxa.qtxadefend.w;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static <T> HashMap<String, T> a(Class<T> cls) {
        return b(c.class.getClassLoader(), cls);
    }

    public static <T> HashMap<String, T> b(ClassLoader classLoader, Class<T> cls) {
        LinkedHashMap linkedHashMap = (HashMap<String, T>) new HashMap();
        for (a aVar : ((b) cls.getAnnotation(b.class)).value()) {
            String strType = aVar.type();
            w.e("HandlerAnnotation", "ComponentName name::" + aVar.name() + " type::" + strType);
            try {
                Class<?> clsLoadClass = classLoader.loadClass(strType);
                if (clsLoadClass != null) {
                    linkedHashMap.put(aVar.name(), clsLoadClass.newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return linkedHashMap;
    }

    public static <T> T c(Class<T> cls) {
        return (T) d(c.class.getClassLoader(), cls);
    }

    public static <T> T d(ClassLoader classLoader, Class<T> cls) {
        Class<?> clsLoadClass;
        try {
            d dVar = (d) cls.getAnnotation(d.class);
            if (dVar == null || (clsLoadClass = classLoader.loadClass(dVar.value())) == null) {
                return null;
            }
            return (T) clsLoadClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
