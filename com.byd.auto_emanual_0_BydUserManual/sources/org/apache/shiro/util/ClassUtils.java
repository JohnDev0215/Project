package org.apache.shiro.util;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.shiro.config.Ini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class ClassUtils {
    private static final ClassLoaderAccessor CLASS_CL_ACCESSOR;
    private static final ClassLoaderAccessor SYSTEM_CL_ACCESSOR;
    private static final ClassLoaderAccessor THREAD_CL_ACCESSOR;
    private static final Logger log = LoggerFactory.getLogger(ClassUtils.class);
    private static final HashMap<String, Class<?>> primClasses;

    private interface ClassLoaderAccessor {
        InputStream getResourceStream(String str);

        Class loadClass(String str);
    }

    static {
        HashMap<String, Class<?>> map = new HashMap<>(8, 1.0f);
        primClasses = map;
        map.put("boolean", Boolean.TYPE);
        map.put("byte", Byte.TYPE);
        map.put("char", Character.TYPE);
        map.put("short", Short.TYPE);
        map.put("int", Integer.TYPE);
        map.put("long", Long.TYPE);
        map.put("float", Float.TYPE);
        map.put("double", Double.TYPE);
        map.put("void", Void.TYPE);
        THREAD_CL_ACCESSOR = new ExceptionIgnoringAccessor() { // from class: org.apache.shiro.util.ClassUtils.1
            @Override // org.apache.shiro.util.ClassUtils.ExceptionIgnoringAccessor
            protected ClassLoader doGetClassLoader() throws Throwable {
                return Thread.currentThread().getContextClassLoader();
            }
        };
        CLASS_CL_ACCESSOR = new ExceptionIgnoringAccessor() { // from class: org.apache.shiro.util.ClassUtils.2
            @Override // org.apache.shiro.util.ClassUtils.ExceptionIgnoringAccessor
            protected ClassLoader doGetClassLoader() throws Throwable {
                return ClassUtils.class.getClassLoader();
            }
        };
        SYSTEM_CL_ACCESSOR = new ExceptionIgnoringAccessor() { // from class: org.apache.shiro.util.ClassUtils.3
            @Override // org.apache.shiro.util.ClassUtils.ExceptionIgnoringAccessor
            protected ClassLoader doGetClassLoader() throws Throwable {
                return ClassLoader.getSystemClassLoader();
            }
        };
    }

    public static InputStream getResourceAsStream(String str) {
        InputStream resourceStream = THREAD_CL_ACCESSOR.getResourceStream(str);
        if (resourceStream == null) {
            Logger logger = log;
            if (logger.isTraceEnabled()) {
                logger.trace("Resource [" + str + "] was not found via the thread context ClassLoader.  Trying the current ClassLoader...");
            }
            resourceStream = CLASS_CL_ACCESSOR.getResourceStream(str);
        }
        if (resourceStream == null) {
            Logger logger2 = log;
            if (logger2.isTraceEnabled()) {
                logger2.trace("Resource [" + str + "] was not found via the current class loader.  Trying the system/application ClassLoader...");
            }
            resourceStream = SYSTEM_CL_ACCESSOR.getResourceStream(str);
        }
        if (resourceStream == null) {
            Logger logger3 = log;
            if (logger3.isTraceEnabled()) {
                logger3.trace("Resource [" + str + "] was not found via the thread context, current, or system/application ClassLoaders.  All heuristics have been exhausted.  Returning null.");
            }
        }
        return resourceStream;
    }

    public static Class forName(String str) throws UnknownClassException {
        Class clsLoadClass = THREAD_CL_ACCESSOR.loadClass(str);
        if (clsLoadClass == null) {
            Logger logger = log;
            if (logger.isTraceEnabled()) {
                logger.trace("Unable to load class named [" + str + "] from the thread context ClassLoader.  Trying the current ClassLoader...");
            }
            clsLoadClass = CLASS_CL_ACCESSOR.loadClass(str);
        }
        if (clsLoadClass == null) {
            Logger logger2 = log;
            if (logger2.isTraceEnabled()) {
                logger2.trace("Unable to load class named [" + str + "] from the current ClassLoader.  Trying the system/application ClassLoader...");
            }
            clsLoadClass = SYSTEM_CL_ACCESSOR.loadClass(str);
        }
        if (clsLoadClass == null) {
            clsLoadClass = primClasses.get(str);
        }
        if (clsLoadClass != null) {
            return clsLoadClass;
        }
        throw new UnknownClassException("Unable to load class named [" + str + "] from the thread context, current, or system/application ClassLoaders.  All heuristics have been exhausted.  Class could not be found.");
    }

    public static boolean isAvailable(String str) {
        try {
            forName(str);
            return true;
        } catch (UnknownClassException unused) {
            return false;
        }
    }

    public static Object newInstance(String str) {
        return newInstance(forName(str));
    }

    public static Object newInstance(String str, Object... objArr) {
        return newInstance(forName(str), objArr);
    }

    public static Object newInstance(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Class method parameter cannot be null.");
        }
        try {
            return cls.newInstance();
        } catch (Exception e) {
            throw new InstantiationException("Unable to instantiate class [" + cls.getName() + Ini.SECTION_SUFFIX, e);
        }
    }

    public static Object newInstance(Class cls, Object... objArr) {
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return instantiate(getConstructor(cls, clsArr), objArr);
    }

    public static Constructor getConstructor(Class cls, Class... clsArr) {
        try {
            return cls.getConstructor(clsArr);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Object instantiate(Constructor constructor, Object... objArr) {
        try {
            return constructor.newInstance(objArr);
        } catch (Exception e) {
            throw new InstantiationException("Unable to instantiate Permission instance with constructor [" + constructor + Ini.SECTION_SUFFIX, e);
        }
    }

    public static List<Method> getAnnotatedMethods(Class<?> cls, Class<? extends Annotation> cls2) {
        ArrayList arrayList = new ArrayList();
        while (!Object.class.equals(cls)) {
            for (Method method : cls.getDeclaredMethods()) {
                if (cls2 == null || method.isAnnotationPresent(cls2)) {
                    arrayList.add(method);
                }
            }
            cls = cls.getSuperclass();
        }
        return arrayList;
    }

    private static abstract class ExceptionIgnoringAccessor implements ClassLoaderAccessor {
        protected abstract ClassLoader doGetClassLoader() throws Throwable;

        private ExceptionIgnoringAccessor() {
        }

        @Override // org.apache.shiro.util.ClassUtils.ClassLoaderAccessor
        public Class loadClass(String str) {
            ClassLoader classLoader = getClassLoader();
            if (classLoader != null) {
                try {
                    return Class.forName(str, false, classLoader);
                } catch (ClassNotFoundException unused) {
                    if (ClassUtils.log.isTraceEnabled()) {
                        ClassUtils.log.trace("Unable to load clazz named [" + str + "] from class loader [" + classLoader + Ini.SECTION_SUFFIX);
                    }
                }
            }
            return null;
        }

        @Override // org.apache.shiro.util.ClassUtils.ClassLoaderAccessor
        public InputStream getResourceStream(String str) {
            ClassLoader classLoader = getClassLoader();
            if (classLoader != null) {
                return classLoader.getResourceAsStream(str);
            }
            return null;
        }

        protected final ClassLoader getClassLoader() {
            try {
                return doGetClassLoader();
            } catch (Throwable th) {
                if (!ClassUtils.log.isDebugEnabled()) {
                    return null;
                }
                ClassUtils.log.debug("Unable to acquire ClassLoader.", th);
                return null;
            }
        }
    }
}
