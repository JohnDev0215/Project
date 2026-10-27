package net.bytebuddy.dynamic.loading;

import com.android.multidex.ClassPathElement;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public class ByteArrayClassLoader extends InjectionClassLoader {
    private static final int FROM_BEGINNING = 0;
    private static final URL NO_URL = null;
    private static final PackageLookupStrategy PACKAGE_LOOKUP_STRATEGY = (PackageLookupStrategy) AccessController.doPrivileged(PackageLookupStrategy.CreationAction.INSTANCE);
    protected static final SynchronizationStrategy.Initializable SYNCHRONIZATION_STRATEGY = (SynchronizationStrategy.Initializable) AccessController.doPrivileged(SynchronizationStrategy.CreationAction.INSTANCE);
    private static final Class<?> UNLOADED_TYPE = null;
    public static final String URL_SCHEMA = "bytebuddy";
    protected final AccessControlContext accessControlContext;
    protected final ClassFileTransformer classFileTransformer;
    protected final PackageDefinitionStrategy packageDefinitionStrategy;
    protected final PersistenceHandler persistenceHandler;
    protected final ProtectionDomain protectionDomain;
    protected final ConcurrentMap<String, byte[]> typeDefinitions;

    public ByteArrayClassLoader(ClassLoader classLoader, Map<String, byte[]> map) {
        this(classLoader, map, PersistenceHandler.LATENT);
    }

    public ByteArrayClassLoader(ClassLoader classLoader, Map<String, byte[]> map, PersistenceHandler persistenceHandler) {
        this(classLoader, map, ClassLoadingStrategy.NO_PROTECTION_DOMAIN, persistenceHandler, PackageDefinitionStrategy.Trivial.INSTANCE);
    }

    public ByteArrayClassLoader(ClassLoader classLoader, Map<String, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy) {
        this(classLoader, map, protectionDomain, persistenceHandler, packageDefinitionStrategy, NoOpClassFileTransformer.INSTANCE);
    }

    public ByteArrayClassLoader(ClassLoader classLoader, Map<String, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, ClassFileTransformer classFileTransformer) {
        super(classLoader);
        this.typeDefinitions = new ConcurrentHashMap(map);
        this.protectionDomain = protectionDomain;
        this.persistenceHandler = persistenceHandler;
        this.packageDefinitionStrategy = packageDefinitionStrategy;
        this.classFileTransformer = classFileTransformer;
        this.accessControlContext = AccessController.getContext();
    }

    public static ClassLoader of(ClassLoader classLoader, Map<TypeDescription, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, boolean z) {
        HashMap map2 = new HashMap();
        for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
            map2.put(entry.getKey().getName(), entry.getValue());
        }
        return z ? new ChildFirst(classLoader, map2, protectionDomain, persistenceHandler, packageDefinitionStrategy) : new ByteArrayClassLoader(classLoader, map2, protectionDomain, persistenceHandler, packageDefinitionStrategy);
    }

    public static Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, boolean z, boolean z2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ClassLoader classLoaderOf = of(classLoader, map, protectionDomain, persistenceHandler, packageDefinitionStrategy, z);
        for (TypeDescription typeDescription : map.keySet()) {
            try {
                Class<?> cls = Class.forName(typeDescription.getName(), false, classLoaderOf);
                if (z2 && cls.getClassLoader() != classLoaderOf) {
                    throw new IllegalStateException("Class already loaded: " + cls);
                }
                linkedHashMap.put(typeDescription, cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Cannot load class " + typeDescription, e);
            }
        }
        return linkedHashMap;
    }

    @Override // net.bytebuddy.dynamic.loading.InjectionClassLoader
    public Class<?> defineClass(String str, byte[] bArr) throws ClassNotFoundException {
        Class<?> clsLoadClass;
        synchronized (SYNCHRONIZATION_STRATEGY.initialize().getClassLoadingLock(this, str)) {
            byte[] bArrPutIfAbsent = this.typeDefinitions.putIfAbsent(str, bArr);
            try {
                clsLoadClass = loadClass(str);
                if (clsLoadClass == null || clsLoadClass.getClassLoader() != this) {
                }
            } finally {
                if (bArrPutIfAbsent == null) {
                    this.typeDefinitions.remove(str);
                } else {
                    this.typeDefinitions.put(str, bArrPutIfAbsent);
                }
            }
        }
        return clsLoadClass;
    }

    @Override // java.lang.ClassLoader
    protected Class<?> findClass(String str) throws ClassNotFoundException {
        byte[] bArrLookup = this.persistenceHandler.lookup(str, this.typeDefinitions);
        if (bArrLookup == null) {
            throw new ClassNotFoundException(str);
        }
        try {
            byte[] bArrTransform = this.classFileTransformer.transform(this, str, UNLOADED_TYPE, this.protectionDomain, bArrLookup);
            if (bArrTransform != null) {
                bArrLookup = bArrTransform;
            }
            return (Class) AccessController.doPrivileged(new ClassDefinitionAction(str, bArrLookup), this.accessControlContext);
        } catch (IllegalClassFormatException e) {
            throw new IllegalStateException("The class file for " + str + " is not legal", e);
        }
    }

    @Override // java.lang.ClassLoader
    protected URL findResource(String str) {
        return this.persistenceHandler.url(str, this.typeDefinitions);
    }

    @Override // java.lang.ClassLoader
    protected Enumeration<URL> findResources(String str) {
        URL url = this.persistenceHandler.url(str, this.typeDefinitions);
        return url == null ? EmptyEnumeration.INSTANCE : new SingletonEnumeration(url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Package doGetPackage(String str) {
        return getPackage(str);
    }

    protected interface SynchronizationStrategy {

        public enum ForLegacyVm implements SynchronizationStrategy, Initializable {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy
            public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                return classLoader;
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.Initializable
            public SynchronizationStrategy initialize() {
                return this;
            }
        }

        public interface Initializable {
            SynchronizationStrategy initialize();
        }

        Object getClassLoadingLock(ClassLoader classLoader, String str);

        public enum CreationAction implements PrivilegedAction<Initializable> {
            INSTANCE;

            @Override // java.security.PrivilegedAction
            public Initializable run() {
                try {
                    return new ForJava7CapableVm(ClassLoader.class.getDeclaredMethod("getClassLoadingLock", String.class));
                } catch (Exception unused) {
                    return ForLegacyVm.INSTANCE;
                }
            }
        }

        public static class ForJava7CapableVm implements SynchronizationStrategy, Initializable {
            private final Method method;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForJava7CapableVm;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForJava7CapableVm)) {
                    return false;
                }
                ForJava7CapableVm forJava7CapableVm = (ForJava7CapableVm) obj;
                if (!forJava7CapableVm.canEqual(this)) {
                    return false;
                }
                Method method = this.method;
                Method method2 = forJava7CapableVm.method;
                return method != null ? method.equals(method2) : method2 == null;
            }

            public int hashCode() {
                Method method = this.method;
                return 59 + (method == null ? 43 : method.hashCode());
            }

            protected ForJava7CapableVm(Method method) {
                this.method = method;
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy
            public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                try {
                    return this.method.invoke(classLoader, str);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access class loading lock for " + str + " on " + classLoader, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Error when getting " + str + " on " + classLoader, e2);
                }
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.SynchronizationStrategy.Initializable
            public SynchronizationStrategy initialize() {
                try {
                    this.method.setAccessible(true);
                    return this;
                } catch (Exception unused) {
                    return ForLegacyVm.INSTANCE;
                }
            }
        }
    }

    protected class ClassDefinitionAction implements PrivilegedAction<Class<?>> {
        private final byte[] binaryRepresentation;
        private final String name;

        protected ClassDefinitionAction(String str, byte[] bArr) {
            this.name = str;
            this.binaryRepresentation = bArr;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public Class<?> run() {
            int iLastIndexOf = this.name.lastIndexOf(46);
            if (iLastIndexOf != -1) {
                String strSubstring = this.name.substring(0, iLastIndexOf);
                PackageDefinitionStrategy.Definition definitionDefine = ByteArrayClassLoader.this.packageDefinitionStrategy.define(ByteArrayClassLoader.this, strSubstring, this.name);
                if (definitionDefine.isDefined()) {
                    Package packageApply = ByteArrayClassLoader.PACKAGE_LOOKUP_STRATEGY.apply(ByteArrayClassLoader.this, strSubstring);
                    if (packageApply == null) {
                        ByteArrayClassLoader.this.definePackage(strSubstring, definitionDefine.getSpecificationTitle(), definitionDefine.getSpecificationVersion(), definitionDefine.getSpecificationVendor(), definitionDefine.getImplementationTitle(), definitionDefine.getImplementationVersion(), definitionDefine.getImplementationVendor(), definitionDefine.getSealBase());
                    } else if (!definitionDefine.isCompatibleTo(packageApply)) {
                        throw new SecurityException("Sealing violation for package " + strSubstring);
                    }
                }
            }
            ByteArrayClassLoader byteArrayClassLoader = ByteArrayClassLoader.this;
            String str = this.name;
            byte[] bArr = this.binaryRepresentation;
            return byteArrayClassLoader.defineClass(str, bArr, 0, bArr.length, byteArrayClassLoader.protectionDomain);
        }

        private ByteArrayClassLoader getOuter() {
            return ByteArrayClassLoader.this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ClassDefinitionAction classDefinitionAction = (ClassDefinitionAction) obj;
            return this.name.equals(classDefinitionAction.name) && ByteArrayClassLoader.this.equals(classDefinitionAction.getOuter()) && Arrays.equals(this.binaryRepresentation, classDefinitionAction.binaryRepresentation);
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + ByteArrayClassLoader.this.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation);
        }
    }

    protected interface PackageLookupStrategy {
        Package apply(ByteArrayClassLoader byteArrayClassLoader, String str);

        public enum CreationAction implements PrivilegedAction<PackageLookupStrategy> {
            INSTANCE;

            @Override // java.security.PrivilegedAction
            public PackageLookupStrategy run() {
                try {
                    return new ForJava9CapableVm(ClassLoader.class.getDeclaredMethod("getDefinedPackage", String.class));
                } catch (Exception unused) {
                    return ForLegacyVm.INSTANCE;
                }
            }
        }

        public enum ForLegacyVm implements PackageLookupStrategy {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PackageLookupStrategy
            public Package apply(ByteArrayClassLoader byteArrayClassLoader, String str) {
                return byteArrayClassLoader.doGetPackage(str);
            }
        }

        public static class ForJava9CapableVm implements PackageLookupStrategy {
            private final Method getDefinedPackage;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForJava9CapableVm;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForJava9CapableVm)) {
                    return false;
                }
                ForJava9CapableVm forJava9CapableVm = (ForJava9CapableVm) obj;
                if (!forJava9CapableVm.canEqual(this)) {
                    return false;
                }
                Method method = this.getDefinedPackage;
                Method method2 = forJava9CapableVm.getDefinedPackage;
                return method != null ? method.equals(method2) : method2 == null;
            }

            public int hashCode() {
                Method method = this.getDefinedPackage;
                return 59 + (method == null ? 43 : method.hashCode());
            }

            protected ForJava9CapableVm(Method method) {
                this.getDefinedPackage = method;
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PackageLookupStrategy
            public Package apply(ByteArrayClassLoader byteArrayClassLoader, String str) {
                try {
                    return (Package) this.getDefinedPackage.invoke(byteArrayClassLoader, str);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.getDefinedPackage, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.getDefinedPackage, e2.getCause());
                }
            }
        }
    }

    public enum PersistenceHandler {
        MANIFEST(true) { // from class: net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler.1
            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler
            protected byte[] lookup(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                return concurrentMap.get(str);
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler
            protected URL url(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                if (!str.endsWith(".class")) {
                    return ByteArrayClassLoader.NO_URL;
                }
                if (str.startsWith("/")) {
                    str = str.substring(1);
                }
                byte[] bArr = concurrentMap.get(str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH).substring(0, str.length() - 6));
                if (bArr == null) {
                    return ByteArrayClassLoader.NO_URL;
                }
                return (URL) AccessController.doPrivileged(new UrlDefinitionAction(str, bArr));
            }
        },
        LATENT(0 == true ? 1 : 0) { // from class: net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler.2
            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler
            protected byte[] lookup(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                return concurrentMap.remove(str);
            }

            @Override // net.bytebuddy.dynamic.loading.ByteArrayClassLoader.PersistenceHandler
            protected URL url(String str, ConcurrentMap<String, byte[]> concurrentMap) {
                return ByteArrayClassLoader.NO_URL;
            }
        };

        private static final String CLASS_FILE_SUFFIX = ".class";
        private final boolean manifest;

        protected abstract byte[] lookup(String str, ConcurrentMap<String, byte[]> concurrentMap);

        protected abstract URL url(String str, ConcurrentMap<String, byte[]> concurrentMap);

        PersistenceHandler(boolean z) {
            this.manifest = z;
        }

        public boolean isManifest() {
            return this.manifest;
        }

        protected static class UrlDefinitionAction implements PrivilegedAction<URL> {
            private static final String ENCODING = "UTF-8";
            private static final String NO_FILE = "";
            private static final int NO_PORT = -1;
            private final byte[] binaryRepresentation;
            private final String typeName;

            protected boolean canEqual(Object obj) {
                return obj instanceof UrlDefinitionAction;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof UrlDefinitionAction)) {
                    return false;
                }
                UrlDefinitionAction urlDefinitionAction = (UrlDefinitionAction) obj;
                if (!urlDefinitionAction.canEqual(this)) {
                    return false;
                }
                String str = this.typeName;
                String str2 = urlDefinitionAction.typeName;
                if (str != null ? str.equals(str2) : str2 == null) {
                    return Arrays.equals(this.binaryRepresentation, urlDefinitionAction.binaryRepresentation);
                }
                return false;
            }

            public int hashCode() {
                String str = this.typeName;
                return (((str == null ? 43 : str.hashCode()) + 59) * 59) + Arrays.hashCode(this.binaryRepresentation);
            }

            protected UrlDefinitionAction(String str, byte[] bArr) {
                this.typeName = str;
                this.binaryRepresentation = bArr;
            }

            @Override // java.security.PrivilegedAction
            public URL run() {
                try {
                    return new URL(ByteArrayClassLoader.URL_SCHEMA, URLEncoder.encode(this.typeName.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR), "UTF-8"), -1, "", new ByteArrayUrlStreamHandler(this.binaryRepresentation));
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalStateException("Could not find encoding: UTF-8", e);
                } catch (MalformedURLException e2) {
                    throw new IllegalStateException("Cannot create URL for " + this.typeName, e2);
                }
            }

            protected static class ByteArrayUrlStreamHandler extends URLStreamHandler {
                private final byte[] binaryRepresentation;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ByteArrayUrlStreamHandler;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ByteArrayUrlStreamHandler)) {
                        return false;
                    }
                    ByteArrayUrlStreamHandler byteArrayUrlStreamHandler = (ByteArrayUrlStreamHandler) obj;
                    return byteArrayUrlStreamHandler.canEqual(this) && Arrays.equals(this.binaryRepresentation, byteArrayUrlStreamHandler.binaryRepresentation);
                }

                public int hashCode() {
                    return 59 + Arrays.hashCode(this.binaryRepresentation);
                }

                protected ByteArrayUrlStreamHandler(byte[] bArr) {
                    this.binaryRepresentation = bArr;
                }

                @Override // java.net.URLStreamHandler
                protected URLConnection openConnection(URL url) throws IOException {
                    return new ByteArrayUrlConnection(url, new ByteArrayInputStream(this.binaryRepresentation));
                }

                protected static class ByteArrayUrlConnection extends URLConnection {
                    private final InputStream inputStream;

                    protected ByteArrayUrlConnection(URL url, InputStream inputStream) {
                        super(url);
                        this.inputStream = inputStream;
                    }

                    @Override // java.net.URLConnection
                    public void connect() {
                        this.connected = true;
                    }

                    @Override // java.net.URLConnection
                    public InputStream getInputStream() {
                        connect();
                        return this.inputStream;
                    }
                }
            }
        }
    }

    public static class ChildFirst extends ByteArrayClassLoader {
        private static final String CLASS_FILE_SUFFIX = ".class";

        public ChildFirst(ClassLoader classLoader, Map<String, byte[]> map) {
            super(classLoader, map);
        }

        public ChildFirst(ClassLoader classLoader, Map<String, byte[]> map, PersistenceHandler persistenceHandler) {
            super(classLoader, map, persistenceHandler);
        }

        public ChildFirst(ClassLoader classLoader, Map<String, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy) {
            super(classLoader, map, protectionDomain, persistenceHandler, packageDefinitionStrategy);
        }

        public ChildFirst(ClassLoader classLoader, Map<String, byte[]> map, ProtectionDomain protectionDomain, PersistenceHandler persistenceHandler, PackageDefinitionStrategy packageDefinitionStrategy, ClassFileTransformer classFileTransformer) {
            super(classLoader, map, protectionDomain, persistenceHandler, packageDefinitionStrategy, classFileTransformer);
        }

        @Override // java.lang.ClassLoader
        protected Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            synchronized (SYNCHRONIZATION_STRATEGY.initialize().getClassLoadingLock(this, str)) {
                Class<?> clsFindLoadedClass = findLoadedClass(str);
                if (clsFindLoadedClass != null) {
                    return clsFindLoadedClass;
                }
                try {
                    Class<?> clsFindClass = findClass(str);
                    if (z) {
                        resolveClass(clsFindClass);
                    }
                    return clsFindClass;
                } catch (ClassNotFoundException unused) {
                    return super.loadClass(str, z);
                }
            }
        }

        @Override // java.lang.ClassLoader
        public URL getResource(String str) {
            URL url = this.persistenceHandler.url(str, this.typeDefinitions);
            return (url != null || isShadowed(str)) ? url : super.getResource(str);
        }

        @Override // java.lang.ClassLoader
        public Enumeration<URL> getResources(String str) throws IOException {
            URL url = this.persistenceHandler.url(str, this.typeDefinitions);
            if (url == null) {
                return super.getResources(str);
            }
            return new PrependingEnumeration(url, super.getResources(str));
        }

        private boolean isShadowed(String str) {
            boolean z = false;
            if (this.persistenceHandler.isManifest() || !str.endsWith(".class")) {
                return false;
            }
            synchronized (this) {
                String strSubstring = str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH).substring(0, str.length() - 6);
                if (this.typeDefinitions.containsKey(strSubstring)) {
                    return true;
                }
                Class clsFindLoadedClass = findLoadedClass(strSubstring);
                if (clsFindLoadedClass != null && clsFindLoadedClass.getClassLoader() == this) {
                    z = true;
                }
                return z;
            }
        }

        protected static class PrependingEnumeration implements Enumeration<URL> {
            private final Enumeration<URL> enumeration;
            private URL nextElement;

            protected PrependingEnumeration(URL url, Enumeration<URL> enumeration) {
                this.nextElement = url;
                this.enumeration = enumeration;
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.nextElement != null && this.enumeration.hasMoreElements();
            }

            @Override // java.util.Enumeration
            public URL nextElement() {
                if (this.nextElement != null && this.enumeration.hasMoreElements()) {
                    try {
                        return this.nextElement;
                    } finally {
                        this.nextElement = this.enumeration.nextElement();
                    }
                }
                throw new NoSuchElementException();
            }
        }
    }

    protected enum EmptyEnumeration implements Enumeration<URL> {
        INSTANCE;

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public URL nextElement() {
            throw new NoSuchElementException();
        }
    }

    protected static class SingletonEnumeration implements Enumeration<URL> {
        private URL element;

        protected SingletonEnumeration(URL url) {
            this.element = url;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.element != null;
        }

        @Override // java.util.Enumeration
        public URL nextElement() {
            URL url = this.element;
            if (url == null) {
                throw new NoSuchElementException();
            }
            this.element = null;
            return url;
        }
    }
}
