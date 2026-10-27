package javax.xml.bind;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
class ServiceLoaderUtil {
    private static final String OSGI_SERVICE_LOADER_CLASS_NAME = "org.glassfish.hk2.osgiresourcelocator.ServiceLoader";
    private static final String OSGI_SERVICE_LOADER_METHOD_NAME = "lookupProviderClasses";

    ServiceLoaderUtil() {
    }

    static <P, T extends Exception> P firstByServiceLoader(Class<P> cls, Logger logger, ExceptionHandler<T> exceptionHandler) throws Exception {
        try {
            Iterator it = ServiceLoader.load(cls).iterator();
            if (!it.hasNext()) {
                return null;
            }
            P p = (P) it.next();
            logger.fine("ServiceProvider loading Facility used; returning object [" + p.getClass().getName() + "]");
            return p;
        } catch (Throwable th) {
            throw exceptionHandler.createException(th, "Error while searching for service [" + cls.getName() + "]");
        }
    }

    static Object lookupUsingOSGiServiceLoader(String str, Logger logger) {
        try {
            Iterator it = ((Iterable) Class.forName(OSGI_SERVICE_LOADER_CLASS_NAME).getMethod(OSGI_SERVICE_LOADER_METHOD_NAME, Class.class).invoke(null, Class.forName(str))).iterator();
            if (!it.hasNext()) {
                return null;
            }
            Object next = it.next();
            logger.fine("Found implementation using OSGi facility; returning object [" + next.getClass().getName() + "].");
            return next;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            logger.log(Level.FINE, "Unable to find from OSGi: [" + str + "]", e);
            return null;
        }
    }

    static void checkPackageAccess(String str) {
        int iLastIndexOf;
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager == null || (iLastIndexOf = str.lastIndexOf(46)) == -1) {
            return;
        }
        securityManager.checkPackageAccess(str.substring(0, iLastIndexOf));
    }

    static Class nullSafeLoadClass(String str, ClassLoader classLoader) throws ClassNotFoundException {
        if (classLoader == null) {
            return Class.forName(str);
        }
        return classLoader.loadClass(str);
    }

    static <T extends Exception> Object newInstance(String str, String str2, ExceptionHandler<T> exceptionHandler) throws Exception {
        try {
            return safeLoadClass(str, str2, contextClassLoader(exceptionHandler)).newInstance();
        } catch (ClassNotFoundException e) {
            throw exceptionHandler.createException(e, "Provider " + str + " not found");
        } catch (Exception e2) {
            throw exceptionHandler.createException(e2, "Provider " + str + " could not be instantiated: " + e2);
        }
    }

    static Class safeLoadClass(String str, String str2, ClassLoader classLoader) throws ClassNotFoundException {
        try {
            checkPackageAccess(str);
            return nullSafeLoadClass(str, classLoader);
        } catch (SecurityException e) {
            if (str2 != null && str2.equals(str)) {
                return Class.forName(str);
            }
            throw e;
        }
    }

    static ClassLoader contextClassLoader(ExceptionHandler exceptionHandler) throws Exception {
        try {
            return Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            throw exceptionHandler.createException(e, e.toString());
        }
    }

    static abstract class ExceptionHandler<T extends Exception> {
        public abstract T createException(Throwable th, String str);

        ExceptionHandler() {
        }
    }
}
