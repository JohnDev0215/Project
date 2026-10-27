package net.bytebuddy.dynamic;

import com.android.multidex.ClassPathElement;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.StreamDrainer;

/* JADX INFO: loaded from: classes3.dex */
public interface ClassFileLocator extends Closeable {
    public static final String CLASS_FILE_EXTENSION = ".class";

    Resolution locate(String str) throws IOException;

    public interface Resolution {
        boolean isResolved();

        byte[] resolve();

        public static class Illegal implements Resolution {
            private final String typeName;

            @Override // net.bytebuddy.dynamic.ClassFileLocator.Resolution
            public boolean isResolved() {
                return false;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Illegal;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Illegal)) {
                    return false;
                }
                Illegal illegal = (Illegal) obj;
                if (!illegal.canEqual(this)) {
                    return false;
                }
                String str = this.typeName;
                String str2 = illegal.typeName;
                return str != null ? str.equals(str2) : str2 == null;
            }

            public int hashCode() {
                String str = this.typeName;
                return 59 + (str == null ? 43 : str.hashCode());
            }

            public Illegal(String str) {
                this.typeName = str;
            }

            @Override // net.bytebuddy.dynamic.ClassFileLocator.Resolution
            public byte[] resolve() {
                throw new IllegalStateException("Could not locate class file for " + this.typeName);
            }
        }

        public static class Explicit implements Resolution {
            private final byte[] binaryRepresentation;

            @Override // net.bytebuddy.dynamic.ClassFileLocator.Resolution
            public boolean isResolved() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Explicit;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Explicit)) {
                    return false;
                }
                Explicit explicit = (Explicit) obj;
                return explicit.canEqual(this) && Arrays.equals(this.binaryRepresentation, explicit.binaryRepresentation);
            }

            public int hashCode() {
                return 59 + Arrays.hashCode(this.binaryRepresentation);
            }

            public Explicit(byte[] bArr) {
                this.binaryRepresentation = bArr;
            }

            @Override // net.bytebuddy.dynamic.ClassFileLocator.Resolution
            public byte[] resolve() {
                return this.binaryRepresentation;
            }
        }
    }

    public enum NoOp implements ClassFileLocator {
        INSTANCE;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            return new Resolution.Illegal(str);
        }
    }

    public static class Simple implements ClassFileLocator {
        private final Map<String, byte[]> classFiles;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof Simple;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Simple)) {
                return false;
            }
            Simple simple = (Simple) obj;
            if (!simple.canEqual(this)) {
                return false;
            }
            Map<String, byte[]> map = this.classFiles;
            Map<String, byte[]> map2 = simple.classFiles;
            return map != null ? map.equals(map2) : map2 == null;
        }

        public int hashCode() {
            Map<String, byte[]> map = this.classFiles;
            return 59 + (map == null ? 43 : map.hashCode());
        }

        public Simple(Map<String, byte[]> map) {
            this.classFiles = map;
        }

        public static ClassFileLocator of(String str, byte[] bArr) {
            return new Simple(Collections.singletonMap(str, bArr));
        }

        public static ClassFileLocator of(String str, byte[] bArr, ClassFileLocator classFileLocator) {
            return new Compound(new Simple(Collections.singletonMap(str, bArr)), classFileLocator);
        }

        public static ClassFileLocator of(DynamicType dynamicType) {
            return of(dynamicType.getAllTypes());
        }

        public static ClassFileLocator of(Map<TypeDescription, byte[]> map) {
            HashMap map2 = new HashMap();
            for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
                map2.put(entry.getKey().getName(), entry.getValue());
            }
            return new Simple(map2);
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            byte[] bArr = this.classFiles.get(str);
            return bArr == null ? new Resolution.Illegal(str) : new Resolution.Explicit(bArr);
        }
    }

    public static class ForClassLoader implements ClassFileLocator {
        private final ClassLoader classLoader;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForClassLoader;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForClassLoader)) {
                return false;
            }
            ForClassLoader forClassLoader = (ForClassLoader) obj;
            if (!forClassLoader.canEqual(this)) {
                return false;
            }
            ClassLoader classLoader = this.classLoader;
            ClassLoader classLoader2 = forClassLoader.classLoader;
            return classLoader != null ? classLoader.equals(classLoader2) : classLoader2 == null;
        }

        public int hashCode() {
            ClassLoader classLoader = this.classLoader;
            return 59 + (classLoader == null ? 43 : classLoader.hashCode());
        }

        protected ForClassLoader(ClassLoader classLoader) {
            this.classLoader = classLoader;
        }

        public static ClassFileLocator ofClassPath() {
            return new ForClassLoader(ClassLoader.getSystemClassLoader());
        }

        public static ClassFileLocator of(ClassLoader classLoader) {
            if (classLoader == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
            return new ForClassLoader(classLoader);
        }

        public static Resolution read(Class<?> cls) {
            try {
                ClassLoader classLoader = cls.getClassLoader();
                if (classLoader == null) {
                    classLoader = ClassLoader.getSystemClassLoader();
                }
                return locate(classLoader, TypeDescription.ForLoadedType.getName(cls));
            } catch (IOException e) {
                throw new IllegalStateException("Cannot read class file for " + cls, e);
            }
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) throws IOException {
            return locate(this.classLoader, str);
        }

        protected static Resolution locate(ClassLoader classLoader, String str) throws IOException {
            InputStream resourceAsStream = classLoader.getResourceAsStream(str.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ClassFileLocator.CLASS_FILE_EXTENSION);
            if (resourceAsStream != null) {
                try {
                    return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(resourceAsStream));
                } finally {
                    resourceAsStream.close();
                }
            }
            return new Resolution.Illegal(str);
        }

        public static class WeaklyReferenced extends WeakReference<ClassLoader> implements ClassFileLocator {
            private final int hashCode;

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
            }

            protected WeaklyReferenced(ClassLoader classLoader) {
                super(classLoader);
                this.hashCode = System.identityHashCode(classLoader);
            }

            public static ClassFileLocator of(ClassLoader classLoader) {
                return (classLoader == null || classLoader == ClassLoader.getSystemClassLoader() || classLoader == ClassLoader.getSystemClassLoader().getParent()) ? ForClassLoader.of(classLoader) : new WeaklyReferenced(classLoader);
            }

            @Override // net.bytebuddy.dynamic.ClassFileLocator
            public Resolution locate(String str) throws IOException {
                ClassLoader classLoader = (ClassLoader) get();
                if (classLoader == null) {
                    return new Resolution.Illegal(str);
                }
                return ForClassLoader.locate(classLoader, str);
            }

            public int hashCode() {
                return this.hashCode;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ClassLoader classLoader = (ClassLoader) ((WeaklyReferenced) obj).get();
                return classLoader != null && get() == classLoader;
            }
        }
    }

    public static class ForModule implements ClassFileLocator {
        private final JavaModule module;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForModule;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForModule)) {
                return false;
            }
            ForModule forModule = (ForModule) obj;
            if (!forModule.canEqual(this)) {
                return false;
            }
            JavaModule javaModule = this.module;
            JavaModule javaModule2 = forModule.module;
            return javaModule != null ? javaModule.equals(javaModule2) : javaModule2 == null;
        }

        public int hashCode() {
            JavaModule javaModule = this.module;
            return 59 + (javaModule == null ? 43 : javaModule.hashCode());
        }

        protected ForModule(JavaModule javaModule) {
            this.module = javaModule;
        }

        public static ClassFileLocator ofBootLayer() {
            try {
                HashMap map = new HashMap();
                Class<?> cls = Class.forName("java.lang.ModuleLayer");
                Method method = JavaType.MODULE.load().getMethod("getPackages", new Class[0]);
                for (Object obj : (Set) cls.getMethod("modules", new Class[0]).invoke(cls.getMethod("boot", new Class[0]).invoke(null, new Object[0]), new Object[0])) {
                    ClassFileLocator classFileLocatorOf = of(JavaModule.of(obj));
                    Iterator it = ((Set) method.invoke(obj, new Object[0])).iterator();
                    while (it.hasNext()) {
                        map.put((String) it.next(), classFileLocatorOf);
                    }
                }
                return new PackageDiscriminating(map);
            } catch (Exception e) {
                throw new IllegalStateException("Cannot process boot layer", e);
            }
        }

        public static ClassFileLocator of(JavaModule javaModule) {
            return javaModule.isNamed() ? new ForModule(javaModule) : ForClassLoader.of(javaModule.getClassLoader());
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) throws IOException {
            return locate(this.module, str);
        }

        protected static Resolution locate(JavaModule javaModule, String str) throws IOException {
            InputStream resourceAsStream = javaModule.getResourceAsStream(str.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ClassFileLocator.CLASS_FILE_EXTENSION);
            if (resourceAsStream != null) {
                try {
                    return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(resourceAsStream));
                } finally {
                    resourceAsStream.close();
                }
            }
            return new Resolution.Illegal(str);
        }

        public static class WeaklyReferenced extends WeakReference<Object> implements ClassFileLocator {
            private final int hashCode;

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
            }

            protected WeaklyReferenced(Object obj) {
                super(obj);
                this.hashCode = System.identityHashCode(obj);
            }

            public static ClassFileLocator of(JavaModule javaModule) {
                if (javaModule.isNamed()) {
                    if (javaModule.getClassLoader() == null || javaModule.getClassLoader() == ClassLoader.getSystemClassLoader() || javaModule.getClassLoader() == ClassLoader.getSystemClassLoader().getParent()) {
                        return new ForModule(javaModule);
                    }
                    return new WeaklyReferenced(javaModule.unwrap());
                }
                return ForClassLoader.WeaklyReferenced.of(javaModule.getClassLoader());
            }

            @Override // net.bytebuddy.dynamic.ClassFileLocator
            public Resolution locate(String str) throws IOException {
                Object obj = get();
                if (obj == null) {
                    return new Resolution.Illegal(str);
                }
                return ForModule.locate(JavaModule.of(obj), str);
            }

            public int hashCode() {
                return this.hashCode;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Object obj2 = ((WeaklyReferenced) obj).get();
                return obj2 != null && get() == obj2;
            }
        }
    }

    public static class ForJarFile implements ClassFileLocator {
        private static final List<String> RUNTIME_LOCATIONS = Arrays.asList("lib/rt.jar", "../lib/rt.jar", "../Classes/classes.jar");
        private final JarFile jarFile;

        protected boolean canEqual(Object obj) {
            return obj instanceof ForJarFile;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForJarFile)) {
                return false;
            }
            ForJarFile forJarFile = (ForJarFile) obj;
            if (!forJarFile.canEqual(this)) {
                return false;
            }
            JarFile jarFile = this.jarFile;
            JarFile jarFile2 = forJarFile.jarFile;
            return jarFile != null ? jarFile.equals(jarFile2) : jarFile2 == null;
        }

        public int hashCode() {
            JarFile jarFile = this.jarFile;
            return 59 + (jarFile == null ? 43 : jarFile.hashCode());
        }

        public ForJarFile(JarFile jarFile) {
            this.jarFile = jarFile;
        }

        public static ClassFileLocator of(File file) throws IOException {
            return new ForJarFile(new JarFile(file));
        }

        public static ClassFileLocator ofClassPath() throws IOException {
            return ofClassPath(System.getProperty("java.class.path"));
        }

        public static ClassFileLocator ofClassPath(String str) throws IOException {
            ArrayList arrayList = new ArrayList();
            for (String str2 : Pattern.compile(System.getProperty("path.separator"), 16).split(str)) {
                File file = new File(str2);
                if (file.isDirectory()) {
                    arrayList.add(new ForFolder(file));
                } else if (file.isFile()) {
                    arrayList.add(of(file));
                }
            }
            return new Compound(arrayList);
        }

        public static ClassFileLocator ofRuntimeJar() throws IOException {
            File file;
            String strReplace = System.getProperty("java.home").replace('\\', ClassPathElement.SEPARATOR_CHAR);
            Iterator<String> it = RUNTIME_LOCATIONS.iterator();
            while (true) {
                if (!it.hasNext()) {
                    file = null;
                    break;
                }
                file = new File(strReplace, it.next());
                if (file.isFile()) {
                    break;
                }
            }
            if (file == null) {
                throw new IllegalStateException("Runtime jar does not exist in " + strReplace + " for any of " + RUNTIME_LOCATIONS);
            }
            return of(file);
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) throws IOException {
            ZipEntry entry = this.jarFile.getEntry(str.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ClassFileLocator.CLASS_FILE_EXTENSION);
            if (entry == null) {
                return new Resolution.Illegal(str);
            }
            InputStream inputStream = this.jarFile.getInputStream(entry);
            try {
                return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(inputStream));
            } finally {
                inputStream.close();
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.jarFile.close();
        }
    }

    public static class ForModuleFile implements ClassFileLocator {
        private static final List<String> BOOT_LOCATIONS = Arrays.asList("jmods", "../jmods");
        private static final String JMOD_FILE_EXTENSION = ".jmod";
        private final ZipFile zipFile;

        protected boolean canEqual(Object obj) {
            return obj instanceof ForModuleFile;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForModuleFile)) {
                return false;
            }
            ForModuleFile forModuleFile = (ForModuleFile) obj;
            if (!forModuleFile.canEqual(this)) {
                return false;
            }
            ZipFile zipFile = this.zipFile;
            ZipFile zipFile2 = forModuleFile.zipFile;
            return zipFile != null ? zipFile.equals(zipFile2) : zipFile2 == null;
        }

        public int hashCode() {
            ZipFile zipFile = this.zipFile;
            return 59 + (zipFile == null ? 43 : zipFile.hashCode());
        }

        public ForModuleFile(ZipFile zipFile) {
            this.zipFile = zipFile;
        }

        public static ClassFileLocator ofBootPath() throws IOException {
            File file;
            String strReplace = System.getProperty("java.home").replace('\\', ClassPathElement.SEPARATOR_CHAR);
            Iterator<String> it = BOOT_LOCATIONS.iterator();
            while (true) {
                if (!it.hasNext()) {
                    file = null;
                    break;
                }
                file = new File(strReplace, it.next());
                if (file.isDirectory()) {
                    break;
                }
            }
            if (file == null) {
                throw new IllegalStateException("Boot modules do not exist in " + strReplace + " for any of " + BOOT_LOCATIONS);
            }
            return ofBootPath(file);
        }

        public static ClassFileLocator ofBootPath(File file) throws IOException {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return NoOp.INSTANCE;
            }
            ArrayList arrayList = new ArrayList(fileArrListFiles.length);
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    arrayList.add(of(file2));
                }
            }
            return new Compound(arrayList);
        }

        public static ClassFileLocator ofModulePath() throws IOException {
            String property = System.getProperty("jdk.module.path");
            if (property == null) {
                return NoOp.INSTANCE;
            }
            return ofModulePath(property);
        }

        public static ClassFileLocator ofModulePath(String str) throws IOException {
            return ofModulePath(str, System.getProperty("user.dir"));
        }

        public static ClassFileLocator ofModulePath(String str, String str2) throws IOException {
            ClassFileLocator classFileLocatorOf;
            ClassFileLocator classFileLocatorOf2;
            ArrayList arrayList = new ArrayList();
            for (String str3 : Pattern.compile(System.getProperty("path.separator"), 16).split(str)) {
                File file = new File(str2, str3);
                if (file.isDirectory()) {
                    File[] fileArrListFiles = file.listFiles();
                    if (fileArrListFiles != null) {
                        for (File file2 : fileArrListFiles) {
                            if (file2.isDirectory()) {
                                arrayList.add(new ForFolder(file2));
                            } else if (file2.isFile()) {
                                if (file2.getName().endsWith(JMOD_FILE_EXTENSION)) {
                                    classFileLocatorOf2 = of(file2);
                                } else {
                                    classFileLocatorOf2 = ForJarFile.of(file2);
                                }
                                arrayList.add(classFileLocatorOf2);
                            }
                        }
                    }
                } else if (file.isFile()) {
                    if (file.getName().endsWith(JMOD_FILE_EXTENSION)) {
                        classFileLocatorOf = of(file);
                    } else {
                        classFileLocatorOf = ForJarFile.of(file);
                    }
                    arrayList.add(classFileLocatorOf);
                }
            }
            return new Compound(arrayList);
        }

        public static ClassFileLocator of(File file) throws IOException {
            return new ForModuleFile(new ZipFile(file));
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) throws IOException {
            ZipEntry entry = this.zipFile.getEntry("classes/" + str.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ClassFileLocator.CLASS_FILE_EXTENSION);
            if (entry == null) {
                return new Resolution.Illegal(str);
            }
            InputStream inputStream = this.zipFile.getInputStream(entry);
            try {
                return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(inputStream));
            } finally {
                inputStream.close();
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.zipFile.close();
        }
    }

    public static class ForFolder implements ClassFileLocator {
        private final File folder;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForFolder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForFolder)) {
                return false;
            }
            ForFolder forFolder = (ForFolder) obj;
            if (!forFolder.canEqual(this)) {
                return false;
            }
            File file = this.folder;
            File file2 = forFolder.folder;
            return file != null ? file.equals(file2) : file2 == null;
        }

        public int hashCode() {
            File file = this.folder;
            return 59 + (file == null ? 43 : file.hashCode());
        }

        public ForFolder(File file) {
            this.folder = file;
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) throws IOException {
            File file = new File(this.folder, str.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, File.separatorChar) + ClassFileLocator.CLASS_FILE_EXTENSION);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    return new Resolution.Explicit(StreamDrainer.DEFAULT.drain(fileInputStream));
                } finally {
                    fileInputStream.close();
                }
            }
            return new Resolution.Illegal(str);
        }
    }

    public static class AgentBased implements ClassFileLocator {
        private static final String INSTALLER_TYPE = "net.bytebuddy.agent.Installer";
        private static final String INSTRUMENTATION_GETTER = "getInstrumentation";
        private static final Object STATIC_MEMBER = null;
        private final ClassLoadingDelegate classLoadingDelegate;
        private final Instrumentation instrumentation;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof AgentBased;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AgentBased)) {
                return false;
            }
            AgentBased agentBased = (AgentBased) obj;
            if (!agentBased.canEqual(this)) {
                return false;
            }
            Instrumentation instrumentation = this.instrumentation;
            Instrumentation instrumentation2 = agentBased.instrumentation;
            if (instrumentation != null ? !instrumentation.equals(instrumentation2) : instrumentation2 != null) {
                return false;
            }
            ClassLoadingDelegate classLoadingDelegate = this.classLoadingDelegate;
            ClassLoadingDelegate classLoadingDelegate2 = agentBased.classLoadingDelegate;
            return classLoadingDelegate != null ? classLoadingDelegate.equals(classLoadingDelegate2) : classLoadingDelegate2 == null;
        }

        public int hashCode() {
            Instrumentation instrumentation = this.instrumentation;
            int iHashCode = instrumentation == null ? 43 : instrumentation.hashCode();
            ClassLoadingDelegate classLoadingDelegate = this.classLoadingDelegate;
            return ((iHashCode + 59) * 59) + (classLoadingDelegate != null ? classLoadingDelegate.hashCode() : 43);
        }

        public AgentBased(Instrumentation instrumentation, ClassLoader classLoader) {
            this(instrumentation, ClassLoadingDelegate.Default.of(classLoader));
        }

        public AgentBased(Instrumentation instrumentation, ClassLoadingDelegate classLoadingDelegate) {
            if (!instrumentation.isRetransformClassesSupported()) {
                throw new IllegalArgumentException(instrumentation + " does not support retransformation");
            }
            this.instrumentation = instrumentation;
            this.classLoadingDelegate = classLoadingDelegate;
        }

        public static ClassFileLocator fromInstalledAgent(ClassLoader classLoader) {
            try {
                return new AgentBased((Instrumentation) ClassLoader.getSystemClassLoader().loadClass(INSTALLER_TYPE).getMethod(INSTRUMENTATION_GETTER, new Class[0]).invoke(STATIC_MEMBER, new Object[0]), classLoader);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new IllegalStateException("The Byte Buddy agent is not installed or not accessible", e2);
            }
        }

        public static ClassFileLocator of(Instrumentation instrumentation, Class<?> cls) {
            return new AgentBased(instrumentation, ClassLoadingDelegate.Explicit.of(cls));
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) {
            try {
                ExtractionClassFileTransformer extractionClassFileTransformer = new ExtractionClassFileTransformer(this.classLoadingDelegate.getClassLoader(), str);
                this.instrumentation.addTransformer(extractionClassFileTransformer, true);
                try {
                    this.instrumentation.retransformClasses(new Class[]{this.classLoadingDelegate.locate(str)});
                    byte[] binaryRepresentation = extractionClassFileTransformer.getBinaryRepresentation();
                    return binaryRepresentation == null ? new Resolution.Illegal(str) : new Resolution.Explicit(binaryRepresentation);
                } finally {
                    this.instrumentation.removeTransformer(extractionClassFileTransformer);
                }
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
                return new Resolution.Illegal(str);
            }
        }

        public interface ClassLoadingDelegate {
            ClassLoader getClassLoader();

            Class<?> locate(String str) throws ClassNotFoundException;

            public static class Default implements ClassLoadingDelegate {
                protected final ClassLoader classLoader;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Default;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Default)) {
                        return false;
                    }
                    Default r4 = (Default) obj;
                    if (!r4.canEqual(this)) {
                        return false;
                    }
                    ClassLoader classLoader = getClassLoader();
                    ClassLoader classLoader2 = r4.getClassLoader();
                    return classLoader != null ? classLoader.equals(classLoader2) : classLoader2 == null;
                }

                public int hashCode() {
                    ClassLoader classLoader = getClassLoader();
                    return 59 + (classLoader == null ? 43 : classLoader.hashCode());
                }

                protected Default(ClassLoader classLoader) {
                    this.classLoader = classLoader;
                }

                public static ClassLoadingDelegate of(ClassLoader classLoader) {
                    if (ForDelegatingClassLoader.isDelegating(classLoader)) {
                        return new ForDelegatingClassLoader(classLoader);
                    }
                    if (classLoader == null) {
                        classLoader = ClassLoader.getSystemClassLoader();
                    }
                    return new Default(classLoader);
                }

                @Override // net.bytebuddy.dynamic.ClassFileLocator.AgentBased.ClassLoadingDelegate
                public Class<?> locate(String str) throws ClassNotFoundException {
                    return this.classLoader.loadClass(str);
                }

                @Override // net.bytebuddy.dynamic.ClassFileLocator.AgentBased.ClassLoadingDelegate
                public ClassLoader getClassLoader() {
                    return this.classLoader;
                }
            }

            public static class ForDelegatingClassLoader extends Default {
                private static final String DELEGATING_CLASS_LOADER_NAME = "sun.reflect.DelegatingClassLoader";
                private static final Dispatcher.Initializable DISPATCHER = (Dispatcher.Initializable) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);
                private static final int ONLY = 0;

                protected ForDelegatingClassLoader(ClassLoader classLoader) {
                    super(classLoader);
                }

                protected static boolean isDelegating(ClassLoader classLoader) {
                    return classLoader != null && classLoader.getClass().getName().equals(DELEGATING_CLASS_LOADER_NAME);
                }

                @Override // net.bytebuddy.dynamic.ClassFileLocator.AgentBased.ClassLoadingDelegate.Default, net.bytebuddy.dynamic.ClassFileLocator.AgentBased.ClassLoadingDelegate
                public Class<?> locate(String str) throws ClassNotFoundException {
                    try {
                        Vector<Class<?>> vectorExtract = DISPATCHER.initialize().extract(this.classLoader);
                        if (vectorExtract.size() != 1) {
                            return super.locate(str);
                        }
                        Class<?> cls = vectorExtract.get(0);
                        return TypeDescription.ForLoadedType.getName(cls).equals(str) ? cls : super.locate(str);
                    } catch (RuntimeException unused) {
                        return super.locate(str);
                    }
                }

                protected interface Dispatcher {

                    public interface Initializable {
                        Dispatcher initialize();
                    }

                    Vector<Class<?>> extract(ClassLoader classLoader);

                    public enum CreationAction implements PrivilegedAction<Initializable> {
                        INSTANCE;

                        @Override // java.security.PrivilegedAction
                        public Initializable run() {
                            try {
                                return new Resolved(ClassLoader.class.getDeclaredField("classes"));
                            } catch (Exception e) {
                                return new Unresolved(e);
                            }
                        }
                    }

                    public static class Resolved implements Dispatcher, Initializable, PrivilegedAction<Dispatcher> {
                        private final Field field;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof Resolved;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof Resolved)) {
                                return false;
                            }
                            Resolved resolved = (Resolved) obj;
                            if (!resolved.canEqual(this)) {
                                return false;
                            }
                            Field field = this.field;
                            Field field2 = resolved.field;
                            return field != null ? field.equals(field2) : field2 == null;
                        }

                        public int hashCode() {
                            Field field = this.field;
                            return 59 + (field == null ? 43 : field.hashCode());
                        }

                        public Resolved(Field field) {
                            this.field = field;
                        }

                        @Override // net.bytebuddy.dynamic.ClassFileLocator.AgentBased.ClassLoadingDelegate.ForDelegatingClassLoader.Dispatcher.Initializable
                        public Dispatcher initialize() {
                            return (Dispatcher) AccessController.doPrivileged(this);
                        }

                        @Override // net.bytebuddy.dynamic.ClassFileLocator.AgentBased.ClassLoadingDelegate.ForDelegatingClassLoader.Dispatcher
                        public Vector<Class<?>> extract(ClassLoader classLoader) {
                            try {
                                return (Vector) this.field.get(classLoader);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access field", e);
                            }
                        }

                        @Override // java.security.PrivilegedAction
                        public Dispatcher run() {
                            this.field.setAccessible(true);
                            return this;
                        }
                    }

                    public static class Unresolved implements Initializable {
                        private final Exception exception;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof Unresolved;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof Unresolved)) {
                                return false;
                            }
                            Unresolved unresolved = (Unresolved) obj;
                            if (!unresolved.canEqual(this)) {
                                return false;
                            }
                            Exception exc = this.exception;
                            Exception exc2 = unresolved.exception;
                            return exc != null ? exc.equals(exc2) : exc2 == null;
                        }

                        public int hashCode() {
                            Exception exc = this.exception;
                            return 59 + (exc == null ? 43 : exc.hashCode());
                        }

                        public Unresolved(Exception exc) {
                            this.exception = exc;
                        }

                        @Override // net.bytebuddy.dynamic.ClassFileLocator.AgentBased.ClassLoadingDelegate.ForDelegatingClassLoader.Dispatcher.Initializable
                        public Dispatcher initialize() {
                            throw new IllegalStateException("Could not locate classes vector", this.exception);
                        }
                    }
                }
            }

            public static class Explicit implements ClassLoadingDelegate {
                private final ClassLoadingDelegate fallbackDelegate;
                private final Map<String, Class<?>> types;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Explicit;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Explicit)) {
                        return false;
                    }
                    Explicit explicit = (Explicit) obj;
                    if (!explicit.canEqual(this)) {
                        return false;
                    }
                    ClassLoadingDelegate classLoadingDelegate = this.fallbackDelegate;
                    ClassLoadingDelegate classLoadingDelegate2 = explicit.fallbackDelegate;
                    if (classLoadingDelegate != null ? !classLoadingDelegate.equals(classLoadingDelegate2) : classLoadingDelegate2 != null) {
                        return false;
                    }
                    Map<String, Class<?>> map = this.types;
                    Map<String, Class<?>> map2 = explicit.types;
                    return map != null ? map.equals(map2) : map2 == null;
                }

                public int hashCode() {
                    ClassLoadingDelegate classLoadingDelegate = this.fallbackDelegate;
                    int iHashCode = classLoadingDelegate == null ? 43 : classLoadingDelegate.hashCode();
                    Map<String, Class<?>> map = this.types;
                    return ((iHashCode + 59) * 59) + (map != null ? map.hashCode() : 43);
                }

                public Explicit(ClassLoader classLoader, Collection<? extends Class<?>> collection) {
                    this(Default.of(classLoader), collection);
                }

                public Explicit(ClassLoadingDelegate classLoadingDelegate, Collection<? extends Class<?>> collection) {
                    this.fallbackDelegate = classLoadingDelegate;
                    this.types = new HashMap();
                    for (Class<?> cls : collection) {
                        this.types.put(TypeDescription.ForLoadedType.getName(cls), cls);
                    }
                }

                public static ClassLoadingDelegate of(Class<?> cls) {
                    return new Explicit(cls.getClassLoader(), Collections.singleton(cls));
                }

                @Override // net.bytebuddy.dynamic.ClassFileLocator.AgentBased.ClassLoadingDelegate
                public Class<?> locate(String str) throws ClassNotFoundException {
                    Class<?> cls = this.types.get(str);
                    return cls == null ? this.fallbackDelegate.locate(str) : cls;
                }

                @Override // net.bytebuddy.dynamic.ClassFileLocator.AgentBased.ClassLoadingDelegate
                public ClassLoader getClassLoader() {
                    return this.fallbackDelegate.getClassLoader();
                }
            }
        }

        protected static class ExtractionClassFileTransformer implements ClassFileTransformer {
            private static final byte[] DO_NOT_TRANSFORM = null;
            private volatile byte[] binaryRepresentation;
            private final ClassLoader classLoader;
            private final String typeName;

            protected ExtractionClassFileTransformer(ClassLoader classLoader, String str) {
                this.classLoader = classLoader;
                this.typeName = str;
            }

            public byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                if (str != null && ElementMatchers.isChildOf(this.classLoader).matches(classLoader) && this.typeName.equals(str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH))) {
                    this.binaryRepresentation = (byte[]) bArr.clone();
                }
                return DO_NOT_TRANSFORM;
            }

            protected byte[] getBinaryRepresentation() {
                return this.binaryRepresentation;
            }
        }
    }

    public static class PackageDiscriminating implements ClassFileLocator {
        private final Map<String, ClassFileLocator> classFileLocators;

        protected boolean canEqual(Object obj) {
            return obj instanceof PackageDiscriminating;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PackageDiscriminating)) {
                return false;
            }
            PackageDiscriminating packageDiscriminating = (PackageDiscriminating) obj;
            if (!packageDiscriminating.canEqual(this)) {
                return false;
            }
            Map<String, ClassFileLocator> map = this.classFileLocators;
            Map<String, ClassFileLocator> map2 = packageDiscriminating.classFileLocators;
            return map != null ? map.equals(map2) : map2 == null;
        }

        public int hashCode() {
            Map<String, ClassFileLocator> map = this.classFileLocators;
            return 59 + (map == null ? 43 : map.hashCode());
        }

        public PackageDiscriminating(Map<String, ClassFileLocator> map) {
            this.classFileLocators = map;
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) throws IOException {
            int iLastIndexOf = str.lastIndexOf(46);
            ClassFileLocator classFileLocator = this.classFileLocators.get(iLastIndexOf == -1 ? "" : str.substring(0, iLastIndexOf));
            if (classFileLocator == null) {
                return new Resolution.Illegal(str);
            }
            return classFileLocator.locate(str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            Iterator<ClassFileLocator> it = this.classFileLocators.values().iterator();
            while (it.hasNext()) {
                it.next().close();
            }
        }
    }

    public static class Compound implements ClassFileLocator, Closeable {
        private final List<ClassFileLocator> classFileLocators;

        protected boolean canEqual(Object obj) {
            return obj instanceof Compound;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Compound)) {
                return false;
            }
            Compound compound = (Compound) obj;
            if (!compound.canEqual(this)) {
                return false;
            }
            List<ClassFileLocator> list = this.classFileLocators;
            List<ClassFileLocator> list2 = compound.classFileLocators;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<ClassFileLocator> list = this.classFileLocators;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Compound(ClassFileLocator... classFileLocatorArr) {
            this((List<? extends ClassFileLocator>) Arrays.asList(classFileLocatorArr));
        }

        public Compound(List<? extends ClassFileLocator> list) {
            this.classFileLocators = new ArrayList();
            for (ClassFileLocator classFileLocator : list) {
                if (classFileLocator instanceof Compound) {
                    this.classFileLocators.addAll(((Compound) classFileLocator).classFileLocators);
                } else if (!(classFileLocator instanceof NoOp)) {
                    this.classFileLocators.add(classFileLocator);
                }
            }
        }

        @Override // net.bytebuddy.dynamic.ClassFileLocator
        public Resolution locate(String str) throws IOException {
            Iterator<ClassFileLocator> it = this.classFileLocators.iterator();
            while (it.hasNext()) {
                Resolution resolutionLocate = it.next().locate(str);
                if (resolutionLocate.isResolved()) {
                    return resolutionLocate;
                }
            }
            return new Resolution.Illegal(str);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            Iterator<ClassFileLocator> it = this.classFileLocators.iterator();
            while (it.hasNext()) {
                it.next().close();
            }
        }
    }
}
