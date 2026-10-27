package com.sun.xml.bind.v2.runtime.reflect.opt;

import com.android.multidex.ClassPathElement;
import com.sun.xml.bind.Util;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
final class Injector {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Object U;
    private static Method defineClass;
    private static Method findLoadedClass;
    private static final Map<ClassLoader, WeakReference<Injector>> injectors;
    private static final Lock ir;
    private static final ReentrantReadWriteLock irwl;
    private static final Lock iw;
    private static final Logger logger;
    private static Method resolveClass;
    private final Map<String, Class> classes = new HashMap();
    private final boolean loadable;
    private final ClassLoader parent;
    private final Lock r;
    private final ReentrantReadWriteLock rwl;
    private final Lock w;

    static {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        irwl = reentrantReadWriteLock;
        ir = reentrantReadWriteLock.readLock();
        iw = reentrantReadWriteLock.writeLock();
        injectors = new WeakHashMap();
        logger = Util.getClassLogger();
        try {
            try {
                Method[] methodArr = (Method[]) AccessController.doPrivileged(new PrivilegedAction<Method[]>() { // from class: com.sun.xml.bind.v2.runtime.reflect.opt.Injector.1
                    @Override // java.security.PrivilegedAction
                    public Method[] run() {
                        return new Method[]{Injector.getMethod(ClassLoader.class, "defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE), Injector.getMethod(ClassLoader.class, "resolveClass", Class.class), Injector.getMethod(ClassLoader.class, "findLoadedClass", String.class)};
                    }
                });
                defineClass = methodArr[0];
                resolveClass = methodArr[1];
                findLoadedClass = methodArr[2];
            } catch (SecurityException | PrivilegedActionException e) {
                Logger.getLogger(Injector.class.getName()).log(Level.SEVERE, (String) null, e);
            }
        } catch (Throwable unused) {
            U = AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: com.sun.xml.bind.v2.runtime.reflect.opt.Injector.2
                @Override // java.security.PrivilegedExceptionAction
                public Object run() throws Exception {
                    Field declaredField = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
                    declaredField.setAccessible(true);
                    return declaredField.get(null);
                }
            });
            defineClass = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() { // from class: com.sun.xml.bind.v2.runtime.reflect.opt.Injector.3
                @Override // java.security.PrivilegedExceptionAction
                public Method run() throws Exception {
                    try {
                        return Injector.U.getClass().getMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class);
                    } catch (NoSuchMethodException | SecurityException e2) {
                        throw e2;
                    }
                }
            });
        }
    }

    static Class inject(ClassLoader classLoader, String str, byte[] bArr) {
        Injector injector = get(classLoader);
        if (injector != null) {
            return injector.inject(str, bArr);
        }
        return null;
    }

    static Class find(ClassLoader classLoader, String str) {
        Injector injector = get(classLoader);
        if (injector != null) {
            return injector.find(str);
        }
        return null;
    }

    private static Injector get(ClassLoader classLoader) {
        Lock lock = ir;
        lock.lock();
        try {
            Map<ClassLoader, WeakReference<Injector>> map = injectors;
            WeakReference<Injector> weakReference = map.get(classLoader);
            lock.unlock();
            Injector injector = weakReference != null ? weakReference.get() : null;
            if (injector != null) {
                return injector;
            }
            try {
                Injector injector2 = new Injector(classLoader);
                WeakReference<Injector> weakReference2 = new WeakReference<>(injector2);
                Lock lock2 = iw;
                lock2.lock();
                try {
                    if (!map.containsKey(classLoader)) {
                        map.put(classLoader, weakReference2);
                    }
                    lock2.unlock();
                    return injector2;
                } catch (Throwable th) {
                    iw.unlock();
                    throw th;
                }
            } catch (SecurityException e) {
                logger.log(Level.FINE, "Unable to set up a back-door for the injector", (Throwable) e);
                return null;
            }
        } catch (Throwable th2) {
            ir.unlock();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    private Injector(ClassLoader classLoader) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.rwl = reentrantReadWriteLock;
        this.r = reentrantReadWriteLock.readLock();
        this.w = reentrantReadWriteLock.writeLock();
        this.parent = classLoader;
        boolean z = false;
        try {
            if (classLoader.loadClass(Accessor.class.getName()) == Accessor.class) {
                z = true;
            }
        } catch (ClassNotFoundException unused) {
        }
        this.loadable = z;
    }

    private Class inject(String str, byte[] bArr) throws Throwable {
        boolean z;
        Class cls;
        Method method;
        if (!this.loadable) {
            return null;
        }
        boolean z2 = false;
        try {
            this.r.lock();
            z = true;
            try {
                Class cls2 = this.classes.get(str);
                this.r.unlock();
                try {
                    if (cls2 == null && (method = findLoadedClass) != null) {
                        try {
                            cls2 = (Class) method.invoke(this.parent, str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH));
                        } catch (IllegalAccessException e) {
                            e = e;
                            logger.log(Level.FINE, "Unable to find " + str, e);
                        } catch (IllegalArgumentException e2) {
                            e = e2;
                            logger.log(Level.FINE, "Unable to find " + str, e);
                        } catch (InvocationTargetException e3) {
                            logger.log(Level.FINE, "Unable to find " + str, e3.getTargetException());
                        }
                        if (cls2 != null) {
                            this.w.lock();
                            this.classes.put(str, cls2);
                            this.w.unlock();
                            return cls2;
                        }
                    }
                    if (cls2 == null) {
                        this.r.lock();
                        cls2 = this.classes.get(str);
                        this.r.unlock();
                        if (cls2 == null) {
                            try {
                                try {
                                    if (resolveClass != null) {
                                        cls = (Class) defineClass.invoke(this.parent, str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH), bArr, 0, Integer.valueOf(bArr.length));
                                        resolveClass.invoke(this.parent, cls);
                                    } else {
                                        cls = (Class) defineClass.invoke(U, str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH), bArr, 0, Integer.valueOf(bArr.length), this.parent, Injector.class.getProtectionDomain());
                                    }
                                    cls2 = cls;
                                    this.w.lock();
                                    if (!this.classes.containsKey(str)) {
                                        this.classes.put(str, cls2);
                                    }
                                    this.w.unlock();
                                } catch (LinkageError e4) {
                                    logger.log(Level.FINE, "Unable to inject " + str, (Throwable) e4);
                                    return null;
                                } catch (InvocationTargetException e5) {
                                    Throwable targetException = e5.getTargetException();
                                    if (targetException instanceof LinkageError) {
                                        logger.log(Level.FINE, "duplicate class definition bug occured? Please report this : " + str, targetException);
                                    } else {
                                        logger.log(Level.FINE, "Unable to inject " + str, targetException);
                                    }
                                    return null;
                                }
                            } catch (IllegalAccessException e6) {
                                logger.log(Level.FINE, "Unable to inject " + str, (Throwable) e6);
                                return null;
                            } catch (SecurityException e7) {
                                logger.log(Level.FINE, "Unable to inject " + str, (Throwable) e7);
                                return null;
                            }
                        }
                    }
                    return cls2;
                } catch (Throwable th) {
                    th = th;
                    if (z2) {
                        this.r.unlock();
                    }
                    if (z) {
                        this.w.unlock();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                z = false;
                z2 = true;
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }

    private Class find(String str) {
        this.r.lock();
        try {
            return this.classes.get(str);
        } finally {
            this.r.unlock();
        }
    }
}
