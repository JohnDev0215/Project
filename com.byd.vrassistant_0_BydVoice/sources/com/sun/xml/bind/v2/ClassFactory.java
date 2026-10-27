package com.sun.xml.bind.v2;

import com.sun.xml.bind.Util;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
public final class ClassFactory {
    private static final Class[] emptyClass = new Class[0];
    private static final Object[] emptyObject = new Object[0];
    private static final Logger logger = Util.getClassLogger();
    private static final ThreadLocal<Map<Class, WeakReference<Constructor>>> tls = new ThreadLocal<Map<Class, WeakReference<Constructor>>>() { // from class: com.sun.xml.bind.v2.ClassFactory.1
        @Override // java.lang.ThreadLocal
        public Map<Class, WeakReference<Constructor>> initialValue() {
            return new WeakHashMap();
        }
    };

    public static void cleanCache() {
        ThreadLocal<Map<Class, WeakReference<Constructor>>> threadLocal = tls;
        if (threadLocal != null) {
            try {
                threadLocal.remove();
            } catch (Exception e) {
                logger.log(Level.WARNING, "Unable to clean Thread Local cache of classes used in Unmarshaller: {0}", e.getLocalizedMessage());
            }
        }
    }

    public static <T> T create0(final Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Map<Class, WeakReference<Constructor>> map = tls.get();
        WeakReference<Constructor> weakReference = map.get(cls);
        Constructor constructorTryGetDeclaredConstructor = weakReference != null ? weakReference.get() : null;
        if (constructorTryGetDeclaredConstructor == null) {
            if (System.getSecurityManager() == null) {
                constructorTryGetDeclaredConstructor = tryGetDeclaredConstructor(cls);
            } else {
                constructorTryGetDeclaredConstructor = (Constructor) AccessController.doPrivileged(new PrivilegedAction<Constructor<T>>() { // from class: com.sun.xml.bind.v2.ClassFactory.2
                    @Override // java.security.PrivilegedAction
                    public Constructor<T> run() {
                        return ClassFactory.tryGetDeclaredConstructor(cls);
                    }
                });
            }
            if (!Modifier.isPublic(cls.getModifiers()) || !Modifier.isPublic(constructorTryGetDeclaredConstructor.getModifiers())) {
                try {
                    constructorTryGetDeclaredConstructor.setAccessible(true);
                } catch (SecurityException e) {
                    logger.log(Level.FINE, "Unable to make the constructor of " + cls + " accessible", (Throwable) e);
                    throw e;
                }
            }
            map.put(cls, new WeakReference<>(constructorTryGetDeclaredConstructor));
        }
        return (T) constructorTryGetDeclaredConstructor.newInstance(emptyObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> Constructor<T> tryGetDeclaredConstructor(Class<T> cls) {
        NoSuchMethodError noSuchMethodError;
        try {
            return cls.getDeclaredConstructor(emptyClass);
        } catch (NoSuchMethodException e) {
            logger.log(Level.INFO, "No default constructor found on " + cls, (Throwable) e);
            if (cls.getDeclaringClass() != null && !Modifier.isStatic(cls.getModifiers())) {
                noSuchMethodError = new NoSuchMethodError(Messages.NO_DEFAULT_CONSTRUCTOR_IN_INNER_CLASS.format(cls.getName()));
            } else {
                noSuchMethodError = new NoSuchMethodError(e.getMessage());
            }
            noSuchMethodError.initCause(e);
            throw noSuchMethodError;
        }
    }

    public static <T> T create(Class<T> cls) {
        try {
            return (T) create0(cls);
        } catch (IllegalAccessException e) {
            logger.log(Level.INFO, "failed to create a new instance of " + cls, (Throwable) e);
            throw new IllegalAccessError(e.toString());
        } catch (InstantiationException e2) {
            logger.log(Level.INFO, "failed to create a new instance of " + cls, (Throwable) e2);
            throw new InstantiationError(e2.toString());
        } catch (InvocationTargetException e3) {
            Throwable targetException = e3.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            if (targetException instanceof Error) {
                throw ((Error) targetException);
            }
            throw new IllegalStateException(targetException);
        }
    }

    public static Object create(Method method) {
        try {
            return method.invoke(null, emptyObject);
        } catch (ExceptionInInitializerError e) {
            e = e;
            logger.log(Level.INFO, "failed to create a new instance of " + method.getReturnType().getName(), e);
            NoSuchMethodError noSuchMethodError = new NoSuchMethodError(e.getMessage());
            noSuchMethodError.initCause(e);
            throw noSuchMethodError;
        } catch (IllegalAccessException e2) {
            logger.log(Level.INFO, "failed to create a new instance of " + method.getReturnType().getName(), (Throwable) e2);
            throw new IllegalAccessError(e2.toString());
        } catch (IllegalArgumentException e3) {
            e = e3;
            logger.log(Level.INFO, "failed to create a new instance of " + method.getReturnType().getName(), e);
            NoSuchMethodError noSuchMethodError2 = new NoSuchMethodError(e.getMessage());
            noSuchMethodError2.initCause(e);
            throw noSuchMethodError2;
        } catch (NullPointerException e4) {
            e = e4;
            logger.log(Level.INFO, "failed to create a new instance of " + method.getReturnType().getName(), e);
            NoSuchMethodError noSuchMethodError22 = new NoSuchMethodError(e.getMessage());
            noSuchMethodError22.initCause(e);
            throw noSuchMethodError22;
        } catch (InvocationTargetException e5) {
            Throwable targetException = e5.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            if (targetException instanceof Error) {
                throw ((Error) targetException);
            }
            throw new IllegalStateException(targetException);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Class<? extends T> inferImplClass(Class<T> cls, Class[] clsArr) {
        if (!cls.isInterface()) {
            return cls;
        }
        for (Class cls2 : clsArr) {
            if (cls.isAssignableFrom(cls2)) {
                return cls2.asSubclass(cls);
            }
        }
        return null;
    }
}
