package javax.xml.bind;

import com.android.multidex.ClassPathElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.ServiceLoaderUtil;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;
import org.apache.commons.compress.utils.CharsetNames;

/* JADX INFO: loaded from: classes3.dex */
class ContextFinder {
    private static ServiceLoaderUtil.ExceptionHandler<JAXBException> EXCEPTION_HANDLER = null;
    private static final String JAXB_CONTEXT_FACTORY_DEPRECATED = "javax.xml.bind.context.factory";
    private static final String PLATFORM_DEFAULT_FACTORY_CLASS = "com.sun.xml.internal.bind.v2.ContextFactory";
    private static final Logger logger;

    ContextFinder() {
    }

    static {
        Logger logger2 = Logger.getLogger("javax.xml.bind");
        logger = logger2;
        try {
            if (AccessController.doPrivileged(new GetPropertyAction("jaxb.debug")) != null) {
                logger2.setUseParentHandlers(false);
                logger2.setLevel(Level.ALL);
                ConsoleHandler consoleHandler = new ConsoleHandler();
                consoleHandler.setLevel(Level.ALL);
                logger2.addHandler(consoleHandler);
            }
        } catch (Throwable unused) {
        }
        EXCEPTION_HANDLER = new ServiceLoaderUtil.ExceptionHandler<JAXBException>() { // from class: javax.xml.bind.ContextFinder.1
            @Override // javax.xml.bind.ServiceLoaderUtil.ExceptionHandler
            public JAXBException createException(Throwable th, String str) {
                return new JAXBException(str, th);
            }
        };
    }

    private static Throwable handleInvocationTargetException(InvocationTargetException invocationTargetException) throws JAXBException {
        Throwable targetException = invocationTargetException.getTargetException();
        if (targetException == null) {
            return invocationTargetException;
        }
        if (targetException instanceof JAXBException) {
            throw ((JAXBException) targetException);
        }
        if (targetException instanceof RuntimeException) {
            throw ((RuntimeException) targetException);
        }
        if (targetException instanceof Error) {
            throw ((Error) targetException);
        }
        return targetException;
    }

    private static JAXBException handleClassCastException(Class cls, Class cls2) {
        return new JAXBException(Messages.format("JAXBContext.IllegalCast", getClassClassLoader(cls).getResource("javax/xml/bind/JAXBContext.class"), which(cls2)));
    }

    static JAXBContext newInstance(String str, Class[] clsArr, String str2, ClassLoader classLoader, Map map) throws JAXBException {
        try {
            return newInstance(str, clsArr, ServiceLoaderUtil.safeLoadClass(str2, PLATFORM_DEFAULT_FACTORY_CLASS, classLoader), classLoader, map);
        } catch (ClassNotFoundException e) {
            throw new JAXBException(Messages.format("ContextFinder.DefaultProviderNotFound"), e);
        } catch (RuntimeException e2) {
            throw e2;
        } catch (JAXBException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new JAXBException(Messages.format("ContextFinder.CouldNotInstantiate", str2, e4), e4);
        }
    }

    static JAXBContext newInstance(String str, Class[] clsArr, Class cls, ClassLoader classLoader, Map map) throws JAXBException {
        Object objInvoke;
        try {
            ModuleUtil.delegateAddOpensToImplModule(clsArr, cls);
            try {
                objInvoke = cls.getMethod("createContext", String.class, ClassLoader.class, Map.class).invoke(instantiateProviderIfNecessary(cls), str, classLoader, map);
            } catch (NoSuchMethodException unused) {
                objInvoke = null;
            }
            if (objInvoke == null) {
                objInvoke = cls.getMethod("createContext", String.class, ClassLoader.class).invoke(instantiateProviderIfNecessary(cls), str, classLoader);
            }
            if (!(objInvoke instanceof JAXBContext)) {
                throw handleClassCastException(objInvoke.getClass(), JAXBContext.class);
            }
            return (JAXBContext) objInvoke;
        } catch (InvocationTargetException e) {
            Throwable thHandleInvocationTargetException = handleInvocationTargetException(e);
            throw new JAXBException(Messages.format("ContextFinder.CouldNotInstantiate", cls, thHandleInvocationTargetException), thHandleInvocationTargetException);
        } catch (Exception e2) {
            throw new JAXBException(Messages.format("ContextFinder.CouldNotInstantiate", cls, e2), e2);
        }
    }

    private static Object instantiateProviderIfNecessary(final Class<?> cls) throws JAXBException {
        try {
            if (JAXBContextFactory.class.isAssignableFrom(cls)) {
                return AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() { // from class: javax.xml.bind.ContextFinder.2
                    @Override // java.security.PrivilegedExceptionAction
                    public Object run() throws Exception {
                        return cls.newInstance();
                    }
                });
            }
            return null;
        } catch (PrivilegedActionException e) {
            Throwable cause = e.getCause();
            PrivilegedActionException cause2 = e;
            if (cause != null) {
                cause2 = e.getCause();
            }
            throw new JAXBException(Messages.format("ContextFinder.CouldNotInstantiate", cls, cause2), cause2);
        }
    }

    static JAXBContext newInstance(Class[] clsArr, Map map, String str) throws JAXBException {
        try {
            Class clsSafeLoadClass = ServiceLoaderUtil.safeLoadClass(str, PLATFORM_DEFAULT_FACTORY_CLASS, getContextClassLoader());
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.log(Level.FINE, "loaded {0} from {1}", new Object[]{str, which(clsSafeLoadClass)});
            }
            return newInstance(clsArr, map, clsSafeLoadClass);
        } catch (ClassNotFoundException e) {
            throw new JAXBException(Messages.format("ContextFinder.DefaultProviderNotFound"), e);
        }
    }

    static JAXBContext newInstance(Class[] clsArr, Map map, Class cls) throws JAXBException {
        try {
            ModuleUtil.delegateAddOpensToImplModule(clsArr, cls);
            Object objInvoke = cls.getMethod("createContext", Class[].class, Map.class).invoke(instantiateProviderIfNecessary(cls), clsArr, map);
            if (!(objInvoke instanceof JAXBContext)) {
                throw handleClassCastException(objInvoke.getClass(), JAXBContext.class);
            }
            return (JAXBContext) objInvoke;
        } catch (IllegalAccessException e) {
            e = e;
            throw new JAXBException(e);
        } catch (NoSuchMethodException e2) {
            e = e2;
            throw new JAXBException(e);
        } catch (InvocationTargetException e3) {
            throw new JAXBException(handleInvocationTargetException(e3));
        }
    }

    static JAXBContext find(String str, String str2, ClassLoader classLoader, Map map) throws Throwable {
        if (str2 == null || str2.isEmpty()) {
            throw new JAXBException(Messages.format("ContextFinder.NoPackageInContextPath"));
        }
        Class[] classesFromContextPath = ModuleUtil.getClassesFromContextPath(str2, classLoader);
        String strJaxbProperties = jaxbProperties(str2, classLoader, str);
        if (strJaxbProperties == null && classesFromContextPath != null) {
            strJaxbProperties = jaxbProperties(classesFromContextPath, str);
        }
        if (strJaxbProperties != null) {
            return newInstance(str2, classesFromContextPath, strJaxbProperties, classLoader, map);
        }
        String strClassNameFromSystemProperties = classNameFromSystemProperties();
        if (strClassNameFromSystemProperties != null) {
            return newInstance(str2, classesFromContextPath, strClassNameFromSystemProperties, classLoader, map);
        }
        Logger logger2 = logger;
        JAXBContextFactory jAXBContextFactory = (JAXBContextFactory) ServiceLoaderUtil.firstByServiceLoader(JAXBContextFactory.class, logger2, EXCEPTION_HANDLER);
        if (jAXBContextFactory != null) {
            ModuleUtil.delegateAddOpensToImplModule(classesFromContextPath, jAXBContextFactory.getClass());
            return jAXBContextFactory.createContext(str2, classLoader, map);
        }
        String strFirstByServiceLoaderDeprecated = firstByServiceLoaderDeprecated(JAXBContext.class, classLoader);
        if (strFirstByServiceLoaderDeprecated != null) {
            return newInstance(str2, classesFromContextPath, strFirstByServiceLoaderDeprecated, classLoader, map);
        }
        Class cls = (Class) ServiceLoaderUtil.lookupUsingOSGiServiceLoader("javax.xml.bind.JAXBContext", logger2);
        if (cls != null) {
            return newInstance(str2, classesFromContextPath, cls, classLoader, map);
        }
        logger2.fine("Trying to create the platform default provider");
        return newInstance(str2, classesFromContextPath, PLATFORM_DEFAULT_FACTORY_CLASS, classLoader, map);
    }

    static JAXBContext find(Class<?>[] clsArr, Map<String, ?> map) throws Throwable {
        URL resourceUrl;
        logger.fine("Searching jaxb.properties");
        for (Class<?> cls : clsArr) {
            if (cls.getPackage() != null && (resourceUrl = getResourceUrl(cls, "jaxb.properties")) != null) {
                return newInstance(clsArr, map, classNameFromPackageProperties(resourceUrl, JAXBContext.JAXB_CONTEXT_FACTORY, JAXB_CONTEXT_FACTORY_DEPRECATED));
            }
        }
        String strClassNameFromSystemProperties = classNameFromSystemProperties();
        if (strClassNameFromSystemProperties != null) {
            return newInstance(clsArr, map, strClassNameFromSystemProperties);
        }
        Logger logger2 = logger;
        JAXBContextFactory jAXBContextFactory = (JAXBContextFactory) ServiceLoaderUtil.firstByServiceLoader(JAXBContextFactory.class, logger2, EXCEPTION_HANDLER);
        if (jAXBContextFactory != null) {
            ModuleUtil.delegateAddOpensToImplModule(clsArr, jAXBContextFactory.getClass());
            return jAXBContextFactory.createContext(clsArr, map);
        }
        String strFirstByServiceLoaderDeprecated = firstByServiceLoaderDeprecated(JAXBContext.class, getContextClassLoader());
        if (strFirstByServiceLoaderDeprecated != null) {
            return newInstance(clsArr, map, strFirstByServiceLoaderDeprecated);
        }
        logger2.fine("Trying to create the platform default provider");
        Class cls2 = (Class) ServiceLoaderUtil.lookupUsingOSGiServiceLoader("javax.xml.bind.JAXBContext", logger2);
        if (cls2 != null) {
            return newInstance(clsArr, map, cls2);
        }
        logger2.fine("Trying to create the platform default provider");
        return newInstance(clsArr, map, PLATFORM_DEFAULT_FACTORY_CLASS);
    }

    private static String classNameFromPackageProperties(URL url, String... strArr) throws JAXBException {
        logger.log(Level.FINE, "Trying to locate {0}", url.toString());
        Properties propertiesLoadJAXBProperties = loadJAXBProperties(url);
        for (String str : strArr) {
            if (propertiesLoadJAXBProperties.containsKey(str)) {
                return propertiesLoadJAXBProperties.getProperty(str);
            }
        }
        String externalForm = url.toExternalForm();
        throw new JAXBException(Messages.format("ContextFinder.MissingProperty", externalForm.substring(0, externalForm.indexOf("/jaxb.properties")), strArr[0]));
    }

    private static String classNameFromSystemProperties() throws JAXBException {
        String systemProperty = getSystemProperty(JAXBContext.JAXB_CONTEXT_FACTORY);
        if (systemProperty != null) {
            return systemProperty;
        }
        String deprecatedSystemProperty = getDeprecatedSystemProperty(JAXB_CONTEXT_FACTORY_DEPRECATED);
        if (deprecatedSystemProperty != null) {
            return deprecatedSystemProperty;
        }
        String deprecatedSystemProperty2 = getDeprecatedSystemProperty(JAXBContext.class.getName());
        if (deprecatedSystemProperty2 != null) {
            return deprecatedSystemProperty2;
        }
        return null;
    }

    private static String getDeprecatedSystemProperty(String str) {
        String systemProperty = getSystemProperty(str);
        if (systemProperty != null) {
            logger.log(Level.WARNING, "Using non-standard property: {0}. Property {1} should be used instead.", new Object[]{str, JAXBContext.JAXB_CONTEXT_FACTORY});
        }
        return systemProperty;
    }

    private static String getSystemProperty(String str) {
        Logger logger2 = logger;
        logger2.log(Level.FINE, "Checking system property {0}", str);
        String str2 = (String) AccessController.doPrivileged(new GetPropertyAction(str));
        if (str2 != null) {
            logger2.log(Level.FINE, "  found {0}", str2);
        } else {
            logger2.log(Level.FINE, "  not found");
        }
        return str2;
    }

    private static Properties loadJAXBProperties(URL url) throws JAXBException {
        try {
            logger.log(Level.FINE, "loading props from {0}", url);
            Properties properties = new Properties();
            InputStream inputStreamOpenStream = url.openStream();
            properties.load(inputStreamOpenStream);
            inputStreamOpenStream.close();
            return properties;
        } catch (IOException e) {
            logger.log(Level.FINE, "Unable to load " + url.toString(), (Throwable) e);
            throw new JAXBException(e.toString(), e);
        }
    }

    private static URL getResourceUrl(ClassLoader classLoader, String str) {
        if (classLoader == null) {
            return ClassLoader.getSystemResource(str);
        }
        return classLoader.getResource(str);
    }

    private static URL getResourceUrl(Class<?> cls, String str) {
        return cls.getResource(str);
    }

    static URL which(Class cls, ClassLoader classLoader) {
        String str = cls.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ClassFileLocator.CLASS_FILE_EXTENSION;
        if (classLoader == null) {
            classLoader = getSystemClassLoader();
        }
        return classLoader.getResource(str);
    }

    static URL which(Class cls) {
        return which(cls, getClassClassLoader(cls));
    }

    private static ClassLoader getContextClassLoader() {
        if (System.getSecurityManager() == null) {
            return Thread.currentThread().getContextClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.xml.bind.ContextFinder.3
            @Override // java.security.PrivilegedAction
            public Object run() {
                return Thread.currentThread().getContextClassLoader();
            }
        });
    }

    private static ClassLoader getClassClassLoader(final Class cls) {
        if (System.getSecurityManager() == null) {
            return cls.getClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.xml.bind.ContextFinder.4
            @Override // java.security.PrivilegedAction
            public Object run() {
                return cls.getClassLoader();
            }
        });
    }

    private static ClassLoader getSystemClassLoader() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.xml.bind.ContextFinder.5
            @Override // java.security.PrivilegedAction
            public Object run() {
                return ClassLoader.getSystemClassLoader();
            }
        });
    }

    @Deprecated
    static String firstByServiceLoaderDeprecated(Class cls, ClassLoader classLoader) throws Throwable {
        InputStream resourceAsStream;
        String name = cls.getName();
        Logger logger2 = logger;
        logger2.fine("Searching META-INF/services");
        String str = "META-INF/services/" + name;
        BufferedReader bufferedReader = null;
        try {
            try {
                if (classLoader == null) {
                    resourceAsStream = ClassLoader.getSystemResourceAsStream(str);
                } else {
                    resourceAsStream = classLoader.getResourceAsStream(str);
                }
                if (resourceAsStream != null) {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetNames.UTF_8));
                    try {
                        String line = bufferedReader2.readLine();
                        if (line != null) {
                            line = line.trim();
                        }
                        bufferedReader2.close();
                        logger2.log(Level.FINE, "Configured factorty class:{0}", line);
                        try {
                            bufferedReader2.close();
                        } catch (IOException e) {
                            logger.log(Level.SEVERE, "Unable to close resource: " + str, (Throwable) e);
                        }
                        return line;
                    } catch (IOException e2) {
                        e = e2;
                        throw new JAXBException(e);
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                logger.log(Level.SEVERE, "Unable to close resource: " + str, (Throwable) e3);
                            }
                        }
                        throw th;
                    }
                }
                logger2.log(Level.FINE, "Unable to load:{0}", str);
                return null;
            } catch (IOException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static String jaxbProperties(String str, ClassLoader classLoader, String str2) throws JAXBException {
        for (String str3 : str.split(":")) {
            URL resourceUrl = getResourceUrl(classLoader, str3.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + "/jaxb.properties");
            if (resourceUrl != null) {
                return classNameFromPackageProperties(resourceUrl, str2, JAXB_CONTEXT_FACTORY_DEPRECATED);
            }
        }
        return null;
    }

    private static String jaxbProperties(Class[] clsArr, String str) throws JAXBException {
        for (Class cls : clsArr) {
            URL resourceUrl = getResourceUrl((Class<?>) cls, "jaxb.properties");
            if (resourceUrl != null) {
                return classNameFromPackageProperties(resourceUrl, str, JAXB_CONTEXT_FACTORY_DEPRECATED);
            }
        }
        return null;
    }
}
