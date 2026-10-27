package net.bytebuddy.dynamic.loading;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.net.URL;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;

/* JADX INFO: loaded from: classes3.dex */
public interface ClassInjector {
    public static final boolean ALLOW_EXISTING_TYPES = false;

    Map<TypeDescription, Class<?>> inject(Map<? extends TypeDescription, byte[]> map);

    public static class UsingReflection implements ClassInjector {
        private static final Dispatcher.Initializable DISPATCHER = (Dispatcher.Initializable) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);
        private final ClassLoader classLoader;
        private final boolean forbidExisting;
        private final PackageDefinitionStrategy packageDefinitionStrategy;
        private final ProtectionDomain protectionDomain;

        protected boolean canEqual(Object obj) {
            return obj instanceof UsingReflection;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UsingReflection)) {
                return false;
            }
            UsingReflection usingReflection = (UsingReflection) obj;
            if (!usingReflection.canEqual(this)) {
                return false;
            }
            ClassLoader classLoader = this.classLoader;
            ClassLoader classLoader2 = usingReflection.classLoader;
            if (classLoader != null ? !classLoader.equals(classLoader2) : classLoader2 != null) {
                return false;
            }
            ProtectionDomain protectionDomain = this.protectionDomain;
            ProtectionDomain protectionDomain2 = usingReflection.protectionDomain;
            if (protectionDomain != null ? !protectionDomain.equals(protectionDomain2) : protectionDomain2 != null) {
                return false;
            }
            PackageDefinitionStrategy packageDefinitionStrategy = this.packageDefinitionStrategy;
            PackageDefinitionStrategy packageDefinitionStrategy2 = usingReflection.packageDefinitionStrategy;
            if (packageDefinitionStrategy != null ? packageDefinitionStrategy.equals(packageDefinitionStrategy2) : packageDefinitionStrategy2 == null) {
                return this.forbidExisting == usingReflection.forbidExisting;
            }
            return false;
        }

        public int hashCode() {
            ClassLoader classLoader = this.classLoader;
            int iHashCode = classLoader == null ? 43 : classLoader.hashCode();
            ProtectionDomain protectionDomain = this.protectionDomain;
            int iHashCode2 = ((iHashCode + 59) * 59) + (protectionDomain == null ? 43 : protectionDomain.hashCode());
            PackageDefinitionStrategy packageDefinitionStrategy = this.packageDefinitionStrategy;
            return (((iHashCode2 * 59) + (packageDefinitionStrategy != null ? packageDefinitionStrategy.hashCode() : 43)) * 59) + (this.forbidExisting ? 79 : 97);
        }

        public UsingReflection(ClassLoader classLoader) {
            this(classLoader, ClassLoadingStrategy.NO_PROTECTION_DOMAIN);
        }

        public UsingReflection(ClassLoader classLoader, ProtectionDomain protectionDomain) {
            this(classLoader, protectionDomain, PackageDefinitionStrategy.Trivial.INSTANCE, false);
        }

        public UsingReflection(ClassLoader classLoader, ProtectionDomain protectionDomain, PackageDefinitionStrategy packageDefinitionStrategy, boolean z) {
            if (classLoader == null) {
                throw new IllegalArgumentException("Cannot inject classes into the bootstrap class loader");
            }
            this.classLoader = classLoader;
            this.protectionDomain = protectionDomain;
            this.packageDefinitionStrategy = packageDefinitionStrategy;
            this.forbidExisting = z;
        }

        public static boolean isAvailable() {
            return DISPATCHER.isAvailable();
        }

        public static ClassInjector ofSystemClassLoader() {
            return new UsingReflection(ClassLoader.getSystemClassLoader());
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public Map<TypeDescription, Class<?>> inject(Map<? extends TypeDescription, byte[]> map) {
            Dispatcher dispatcherInitialize = DISPATCHER.initialize();
            HashMap map2 = new HashMap();
            for (Map.Entry<? extends TypeDescription, byte[]> entry : map.entrySet()) {
                String name = entry.getKey().getName();
                synchronized (dispatcherInitialize.getClassLoadingLock(this.classLoader, name)) {
                    Class<?> clsFindClass = dispatcherInitialize.findClass(this.classLoader, name);
                    if (clsFindClass == null) {
                        int iLastIndexOf = name.lastIndexOf(46);
                        if (iLastIndexOf != -1) {
                            String strSubstring = name.substring(0, iLastIndexOf);
                            PackageDefinitionStrategy.Definition definitionDefine = this.packageDefinitionStrategy.define(this.classLoader, strSubstring, name);
                            if (definitionDefine.isDefined()) {
                                Package r3 = dispatcherInitialize.getPackage(this.classLoader, strSubstring);
                                if (r3 == null) {
                                    dispatcherInitialize.definePackage(this.classLoader, strSubstring, definitionDefine.getSpecificationTitle(), definitionDefine.getSpecificationVersion(), definitionDefine.getSpecificationVendor(), definitionDefine.getImplementationTitle(), definitionDefine.getImplementationVersion(), definitionDefine.getImplementationVendor(), definitionDefine.getSealBase());
                                } else if (!definitionDefine.isCompatibleTo(r3)) {
                                    throw new SecurityException("Sealing violation for package " + strSubstring);
                                }
                            }
                        }
                        clsFindClass = dispatcherInitialize.defineClass(this.classLoader, name, entry.getValue(), this.protectionDomain);
                    } else if (this.forbidExisting) {
                        throw new IllegalStateException("Cannot inject already loaded type: " + clsFindClass);
                    }
                    map2.put(entry.getKey(), clsFindClass);
                }
            }
            return map2;
        }

        protected interface Dispatcher {
            public static final Class<?> UNDEFINED = null;

            public interface Initializable {
                Dispatcher initialize();

                boolean isAvailable();
            }

            Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain);

            Package definePackage(ClassLoader classLoader, String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url);

            Class<?> findClass(ClassLoader classLoader, String str);

            Object getClassLoadingLock(ClassLoader classLoader, String str);

            Package getPackage(ClassLoader classLoader, String str);

            public enum CreationAction implements PrivilegedAction<Initializable> {
                INSTANCE;

                @Override // java.security.PrivilegedAction
                public Initializable run() {
                    try {
                        if (JavaModule.isSupported()) {
                            return Indirect.make();
                        }
                        return Direct.make();
                    } catch (Exception e) {
                        return new Unavailable(e);
                    }
                }
            }

            public static abstract class Direct implements Dispatcher, Initializable {
                protected final Method defineClass;
                protected final Method definePackage;
                protected final Method findLoadedClass;
                protected final Method getPackage;

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public boolean isAvailable() {
                    return true;
                }

                protected abstract void onInitialization();

                protected boolean canEqual(Object obj) {
                    return obj instanceof Direct;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Direct)) {
                        return false;
                    }
                    Direct direct = (Direct) obj;
                    if (!direct.canEqual(this)) {
                        return false;
                    }
                    Method method = this.findLoadedClass;
                    Method method2 = direct.findLoadedClass;
                    if (method != null ? !method.equals(method2) : method2 != null) {
                        return false;
                    }
                    Method method3 = this.defineClass;
                    Method method4 = direct.defineClass;
                    if (method3 != null ? !method3.equals(method4) : method4 != null) {
                        return false;
                    }
                    Method method5 = this.getPackage;
                    Method method6 = direct.getPackage;
                    if (method5 != null ? !method5.equals(method6) : method6 != null) {
                        return false;
                    }
                    Method method7 = this.definePackage;
                    Method method8 = direct.definePackage;
                    return method7 != null ? method7.equals(method8) : method8 == null;
                }

                public int hashCode() {
                    Method method = this.findLoadedClass;
                    int iHashCode = method == null ? 43 : method.hashCode();
                    Method method2 = this.defineClass;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (method2 == null ? 43 : method2.hashCode());
                    Method method3 = this.getPackage;
                    int iHashCode3 = (iHashCode2 * 59) + (method3 == null ? 43 : method3.hashCode());
                    Method method4 = this.definePackage;
                    return (iHashCode3 * 59) + (method4 != null ? method4.hashCode() : 43);
                }

                protected Direct(Method method, Method method2, Method method3, Method method4) {
                    this.findLoadedClass = method;
                    this.defineClass = method2;
                    this.getPackage = method3;
                    this.definePackage = method4;
                }

                protected static Initializable make() throws Exception {
                    Method declaredMethod;
                    try {
                        declaredMethod = ClassLoader.class.getDeclaredMethod("getDefinedPackage", String.class);
                    } catch (NoSuchMethodException unused) {
                        declaredMethod = ClassLoader.class.getDeclaredMethod("getPackage", String.class);
                    }
                    Method declaredMethod2 = ClassLoader.class.getDeclaredMethod("findLoadedClass", String.class);
                    Method declaredMethod3 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                    Method declaredMethod4 = ClassLoader.class.getDeclaredMethod("definePackage", String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class);
                    try {
                        return new ForJava7CapableVm(declaredMethod2, declaredMethod3, declaredMethod, declaredMethod4, ClassLoader.class.getDeclaredMethod("getClassLoadingLock", String.class));
                    } catch (NoSuchMethodException unused2) {
                        return new ForLegacyVm(declaredMethod2, declaredMethod3, declaredMethod, declaredMethod4);
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return (Class) this.findLoadedClass.invoke(classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#findClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#findClass", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(classLoader, str, bArr, 0, Integer.valueOf(bArr.length), protectionDomain);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#defineClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#defineClass", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package getPackage(ClassLoader classLoader, String str) {
                    try {
                        return (Package) this.getPackage.invoke(classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#getPackage", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#getPackage", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package definePackage(ClassLoader classLoader, String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
                    try {
                        return (Package) this.definePackage.invoke(classLoader, str, str2, str3, str4, str5, str6, str7, url);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access java.lang.ClassLoader#definePackage", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.ClassLoader#definePackage", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public Dispatcher initialize() {
                    try {
                        this.findLoadedClass.setAccessible(true);
                        this.defineClass.setAccessible(true);
                        this.getPackage.setAccessible(true);
                        this.definePackage.setAccessible(true);
                        onInitialization();
                        return this;
                    } catch (Exception e) {
                        return new Unavailable(e);
                    }
                }

                protected static class ForJava7CapableVm extends Direct {
                    private final Method getClassLoadingLock;

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Direct
                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForJava7CapableVm;
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Direct
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForJava7CapableVm)) {
                            return false;
                        }
                        ForJava7CapableVm forJava7CapableVm = (ForJava7CapableVm) obj;
                        if (!forJava7CapableVm.canEqual(this) || !super.equals(obj)) {
                            return false;
                        }
                        Method method = this.getClassLoadingLock;
                        Method method2 = forJava7CapableVm.getClassLoadingLock;
                        return method != null ? method.equals(method2) : method2 == null;
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Direct
                    public int hashCode() {
                        int iHashCode = super.hashCode() + 59;
                        Method method = this.getClassLoadingLock;
                        return (iHashCode * 59) + (method == null ? 43 : method.hashCode());
                    }

                    protected ForJava7CapableVm(Method method, Method method2, Method method3, Method method4, Method method5) {
                        super(method, method2, method3, method4);
                        this.getClassLoadingLock = method5;
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        try {
                            return this.getClassLoadingLock.invoke(classLoader, str);
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Could not access java.lang.ClassLoader#getClassLoadingLock", e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException("Error invoking java.lang.ClassLoader#getClassLoadingLock", e2.getCause());
                        }
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Direct
                    protected void onInitialization() {
                        this.getClassLoadingLock.setAccessible(true);
                    }
                }

                protected static class ForLegacyVm extends Direct {
                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                    public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                        return classLoader;
                    }

                    @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Direct
                    protected void onInitialization() {
                    }

                    protected ForLegacyVm(Method method, Method method2, Method method3, Method method4) {
                        super(method, method2, method3, method4);
                    }
                }
            }

            public static class Indirect implements Dispatcher, Initializable {
                private static final Permission ACCESS_PERMISSION = new ReflectPermission("suppressAccessChecks");
                private final Object accessor;
                private final Method defineClass;
                private final Method definePackage;
                private final Method findLoadedClass;
                private final Method getClassLoadingLock;
                private final Method getPackage;

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public boolean isAvailable() {
                    return true;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof Indirect;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Indirect)) {
                        return false;
                    }
                    Indirect indirect = (Indirect) obj;
                    if (!indirect.canEqual(this)) {
                        return false;
                    }
                    Object obj2 = this.accessor;
                    Object obj3 = indirect.accessor;
                    if (obj2 != null ? !obj2.equals(obj3) : obj3 != null) {
                        return false;
                    }
                    Method method = this.findLoadedClass;
                    Method method2 = indirect.findLoadedClass;
                    if (method != null ? !method.equals(method2) : method2 != null) {
                        return false;
                    }
                    Method method3 = this.defineClass;
                    Method method4 = indirect.defineClass;
                    if (method3 != null ? !method3.equals(method4) : method4 != null) {
                        return false;
                    }
                    Method method5 = this.getPackage;
                    Method method6 = indirect.getPackage;
                    if (method5 != null ? !method5.equals(method6) : method6 != null) {
                        return false;
                    }
                    Method method7 = this.definePackage;
                    Method method8 = indirect.definePackage;
                    if (method7 != null ? !method7.equals(method8) : method8 != null) {
                        return false;
                    }
                    Method method9 = this.getClassLoadingLock;
                    Method method10 = indirect.getClassLoadingLock;
                    return method9 != null ? method9.equals(method10) : method10 == null;
                }

                public int hashCode() {
                    Object obj = this.accessor;
                    int iHashCode = obj == null ? 43 : obj.hashCode();
                    Method method = this.findLoadedClass;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (method == null ? 43 : method.hashCode());
                    Method method2 = this.defineClass;
                    int iHashCode3 = (iHashCode2 * 59) + (method2 == null ? 43 : method2.hashCode());
                    Method method3 = this.getPackage;
                    int iHashCode4 = (iHashCode3 * 59) + (method3 == null ? 43 : method3.hashCode());
                    Method method4 = this.definePackage;
                    int iHashCode5 = (iHashCode4 * 59) + (method4 == null ? 43 : method4.hashCode());
                    Method method5 = this.getClassLoadingLock;
                    return (iHashCode5 * 59) + (method5 != null ? method5.hashCode() : 43);
                }

                protected Indirect(Object obj, Method method, Method method2, Method method3, Method method4, Method method5) {
                    this.accessor = obj;
                    this.findLoadedClass = method;
                    this.defineClass = method2;
                    this.getPackage = method3;
                    this.definePackage = method4;
                    this.getClassLoadingLock = method5;
                }

                public static Initializable make() throws Exception {
                    Method declaredMethod;
                    DynamicType.Builder builderIntercept;
                    Class<?> cls = Class.forName("sun.misc.Unsafe");
                    Field declaredField = cls.getDeclaredField("theUnsafe");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(null);
                    try {
                        declaredMethod = ClassLoader.class.getDeclaredMethod("getDeclaredPackage", String.class);
                    } catch (NoSuchMethodException unused) {
                        declaredMethod = ClassLoader.class.getDeclaredMethod("getPackage", String.class);
                    }
                    DynamicType.Builder builderIntercept2 = new ByteBuddy().subclass(Object.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).name(ClassLoader.class.getName() + "$ByteBuddyAccessor$" + RandomString.make()).defineMethod("findLoadedClass", Class.class, Visibility.PUBLIC).withParameters(ClassLoader.class, String.class).intercept(MethodCall.invoke(ClassLoader.class.getDeclaredMethod("findLoadedClass", String.class)).onArgument(0).withArgument(1)).defineMethod("defineClass", Class.class, Visibility.PUBLIC).withParameters(ClassLoader.class, String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class).intercept(MethodCall.invoke(ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class)).onArgument(0).withArgument(1, 2, 3, 4, 5)).defineMethod("getPackage", Package.class, Visibility.PUBLIC).withParameters(ClassLoader.class, String.class).intercept(MethodCall.invoke(declaredMethod).onArgument(0).withArgument(1)).defineMethod("definePackage", Package.class, Visibility.PUBLIC).withParameters(ClassLoader.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class).intercept(MethodCall.invoke(ClassLoader.class.getDeclaredMethod("definePackage", String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class)).onArgument(0).withArgument(1, 2, 3, 4, 5, 6, 7, 8));
                    try {
                        builderIntercept = builderIntercept2.defineMethod("getClassLoadingLock", Object.class, Visibility.PUBLIC).withParameters(ClassLoader.class, String.class).intercept(MethodCall.invoke(ClassLoader.class.getDeclaredMethod("getClassLoadingLock", String.class)).onArgument(0).withArgument(1));
                    } catch (NoSuchMethodException unused2) {
                        builderIntercept = builderIntercept2.defineMethod("getClassLoadingLock", Object.class, Visibility.PUBLIC).withParameters(ClassLoader.class, String.class).intercept(FixedValue.argument(0));
                    }
                    Class loaded = builderIntercept.make().load(ClassLoadingStrategy.BOOTSTRAP_LOADER, new ClassLoadingStrategy.ForUnsafeInjection()).getLoaded();
                    return new Indirect(cls.getDeclaredMethod("allocateInstance", Class.class).invoke(obj, loaded), loaded.getMethod("findLoadedClass", ClassLoader.class, String.class), loaded.getMethod("defineClass", ClassLoader.class, String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class), loaded.getMethod("getPackage", ClassLoader.class, String.class), loaded.getMethod("definePackage", ClassLoader.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class), loaded.getMethod("getClassLoadingLock", ClassLoader.class, String.class));
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public Dispatcher initialize() {
                    SecurityManager securityManager = System.getSecurityManager();
                    if (securityManager != null) {
                        securityManager.checkPermission(ACCESS_PERMISSION);
                    }
                    return this;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                    try {
                        return this.getClassLoadingLock.invoke(this.accessor, classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access (accessor)::getClassLoadingLock", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking (accessor)::getClassLoadingLock", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return (Class) this.findLoadedClass.invoke(this.accessor, classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access (accessor)::findLoadedClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking (accessor)::findLoadedClass", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(this.accessor, classLoader, str, bArr, 0, Integer.valueOf(bArr.length), protectionDomain);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access (accessor)::defineClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking (accessor)::defineClass", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package getPackage(ClassLoader classLoader, String str) {
                    try {
                        return (Package) this.getPackage.invoke(this.accessor, classLoader, str);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access (accessor)::getPackage", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking (accessor)::getPackage", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package definePackage(ClassLoader classLoader, String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
                    try {
                        return (Package) this.definePackage.invoke(this.accessor, classLoader, str, str2, str3, str4, str5, str6, str7, url);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access (accessor)::definePackage", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking (accessor)::definePackage", e2.getCause());
                    }
                }
            }

            public static class Unavailable implements Dispatcher, Initializable {
                private final Exception exception;

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Object getClassLoadingLock(ClassLoader classLoader, String str) {
                    return classLoader;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public Dispatcher initialize() {
                    return this;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher.Initializable
                public boolean isAvailable() {
                    return false;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof Unavailable;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Unavailable)) {
                        return false;
                    }
                    Unavailable unavailable = (Unavailable) obj;
                    if (!unavailable.canEqual(this)) {
                        return false;
                    }
                    Exception exc = this.exception;
                    Exception exc2 = unavailable.exception;
                    return exc != null ? exc.equals(exc2) : exc2 == null;
                }

                public int hashCode() {
                    Exception exc = this.exception;
                    return 59 + (exc == null ? 43 : exc.hashCode());
                }

                protected Unavailable(Exception exc) {
                    this.exception = exc;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> findClass(ClassLoader classLoader, String str) {
                    try {
                        return classLoader.loadClass(str);
                    } catch (ClassNotFoundException unused) {
                        return UNDEFINED;
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                    throw new UnsupportedOperationException("Cannot define class using reflection", this.exception);
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package getPackage(ClassLoader classLoader, String str) {
                    throw new UnsupportedOperationException("Cannot get package using reflection", this.exception);
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingReflection.Dispatcher
                public Package definePackage(ClassLoader classLoader, String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) {
                    throw new UnsupportedOperationException("Cannot define package using injection", this.exception);
                }
            }
        }
    }

    public static class UsingLookup implements ClassInjector {
        private static final Dispatcher DISPATCHER = (Dispatcher) AccessController.doPrivileged(Dispatcher.Creator.INSTANCE);
        private static final int PACKAGE_LOOKUP = 8;
        private final Object lookup;

        protected boolean canEqual(Object obj) {
            return obj instanceof UsingLookup;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UsingLookup)) {
                return false;
            }
            UsingLookup usingLookup = (UsingLookup) obj;
            if (!usingLookup.canEqual(this)) {
                return false;
            }
            Object obj2 = this.lookup;
            Object obj3 = usingLookup.lookup;
            return obj2 != null ? obj2.equals(obj3) : obj3 == null;
        }

        public int hashCode() {
            Object obj = this.lookup;
            return 59 + (obj == null ? 43 : obj.hashCode());
        }

        protected UsingLookup(Object obj) {
            this.lookup = obj;
        }

        public static UsingLookup of(Object obj) {
            Dispatcher dispatcher = DISPATCHER;
            if (!dispatcher.isAlive()) {
                throw new IllegalStateException("The current VM does not support class definition via method handle lookups");
            }
            if (!JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isInstance(obj)) {
                throw new IllegalArgumentException("Not a method handle lookup: " + obj);
            }
            if ((dispatcher.lookupModes(obj) & 8) == 0) {
                throw new IllegalArgumentException("Lookup does not imply package-access: " + obj);
            }
            return new UsingLookup(dispatcher.dropLookupMode(obj, 2));
        }

        public Class<?> lookupType() {
            return DISPATCHER.lookupType(this.lookup);
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public Map<TypeDescription, Class<?>> inject(Map<? extends TypeDescription, byte[]> map) {
            HashMap map2 = new HashMap();
            for (Map.Entry<? extends TypeDescription, byte[]> entry : map.entrySet()) {
                if (!entry.getKey().isSamePackage(new TypeDescription.ForLoadedType(lookupType()))) {
                    throw new IllegalArgumentException(entry.getKey() + " cannot be defined in its package using " + this.lookup);
                }
                map2.put(entry.getKey(), DISPATCHER.defineClass(this.lookup, entry.getValue()));
            }
            return map2;
        }

        protected interface Dispatcher {
            Class<?> defineClass(Object obj, byte[] bArr);

            Object dropLookupMode(Object obj, int i);

            boolean isAlive();

            int lookupModes(Object obj);

            Class<?> lookupType(Object obj);

            public enum Creator implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                @Override // java.security.PrivilegedAction
                public Dispatcher run() {
                    try {
                        Class<?> clsLoad = JavaType.METHOD_HANDLES_LOOKUP.load();
                        return new ForJava9CapableVm(clsLoad.getMethod("lookupClass", new Class[0]), clsLoad.getMethod("lookupModes", new Class[0]), clsLoad.getMethod("dropLookupMode", Integer.TYPE), clsLoad.getMethod("defineClass", byte[].class));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingLookup.Dispatcher
                public boolean isAlive() {
                    return false;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingLookup.Dispatcher
                public Class<?> lookupType(Object obj) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.invoke.MethodHandles$Lookup");
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingLookup.Dispatcher
                public int lookupModes(Object obj) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.invoke.MethodHandles$Lookup");
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingLookup.Dispatcher
                public Object dropLookupMode(Object obj, int i) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.invoke.MethodHandles$Lookup");
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingLookup.Dispatcher
                public Class<?> defineClass(Object obj, byte[] bArr) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.invoke.MethodHandles$Lookup");
                }
            }

            public static class ForJava9CapableVm implements Dispatcher {
                private final Method defineClass;
                private final Method dropLookupMode;
                private final Method lookupClass;
                private final Method lookupModes;

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingLookup.Dispatcher
                public boolean isAlive() {
                    return true;
                }

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
                    Method method = this.lookupClass;
                    Method method2 = forJava9CapableVm.lookupClass;
                    if (method != null ? !method.equals(method2) : method2 != null) {
                        return false;
                    }
                    Method method3 = this.lookupModes;
                    Method method4 = forJava9CapableVm.lookupModes;
                    if (method3 != null ? !method3.equals(method4) : method4 != null) {
                        return false;
                    }
                    Method method5 = this.dropLookupMode;
                    Method method6 = forJava9CapableVm.dropLookupMode;
                    if (method5 != null ? !method5.equals(method6) : method6 != null) {
                        return false;
                    }
                    Method method7 = this.defineClass;
                    Method method8 = forJava9CapableVm.defineClass;
                    return method7 != null ? method7.equals(method8) : method8 == null;
                }

                public int hashCode() {
                    Method method = this.lookupClass;
                    int iHashCode = method == null ? 43 : method.hashCode();
                    Method method2 = this.lookupModes;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (method2 == null ? 43 : method2.hashCode());
                    Method method3 = this.dropLookupMode;
                    int iHashCode3 = (iHashCode2 * 59) + (method3 == null ? 43 : method3.hashCode());
                    Method method4 = this.defineClass;
                    return (iHashCode3 * 59) + (method4 != null ? method4.hashCode() : 43);
                }

                protected ForJava9CapableVm(Method method, Method method2, Method method3, Method method4) {
                    this.lookupClass = method;
                    this.lookupModes = method2;
                    this.defineClass = method4;
                    this.dropLookupMode = method3;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingLookup.Dispatcher
                public Class<?> lookupType(Object obj) {
                    try {
                        return (Class) this.lookupClass.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles$Lookup#lookupClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles$Lookup#lookupClass", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingLookup.Dispatcher
                public int lookupModes(Object obj) {
                    try {
                        return ((Integer) this.lookupModes.invoke(obj, new Object[0])).intValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles$Lookup#lookupModes", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles$Lookup#lookupModes", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingLookup.Dispatcher
                public Object dropLookupMode(Object obj, int i) {
                    try {
                        return this.dropLookupMode.invoke(obj, Integer.valueOf(i));
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles$Lookup#lookupModes", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles$Lookup#lookupModes", e2.getCause());
                    }
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingLookup.Dispatcher
                public Class<?> defineClass(Object obj, byte[] bArr) {
                    try {
                        return (Class) this.defineClass.invoke(obj, bArr);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles$Lookup#defineClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles$Lookup#defineClass", e2.getCause());
                    }
                }
            }
        }
    }

    public static class UsingUnsafe implements ClassInjector {
        private final ClassLoader classLoader;
        private final ProtectionDomain protectionDomain;
        private static final Dispatcher.Initializable DISPATCHER = (Dispatcher.Initializable) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);
        private static final Object BOOTSTRAP_LOADER_LOCK = new Object();

        protected boolean canEqual(Object obj) {
            return obj instanceof UsingUnsafe;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UsingUnsafe)) {
                return false;
            }
            UsingUnsafe usingUnsafe = (UsingUnsafe) obj;
            if (!usingUnsafe.canEqual(this)) {
                return false;
            }
            ClassLoader classLoader = this.classLoader;
            ClassLoader classLoader2 = usingUnsafe.classLoader;
            if (classLoader != null ? !classLoader.equals(classLoader2) : classLoader2 != null) {
                return false;
            }
            ProtectionDomain protectionDomain = this.protectionDomain;
            ProtectionDomain protectionDomain2 = usingUnsafe.protectionDomain;
            return protectionDomain != null ? protectionDomain.equals(protectionDomain2) : protectionDomain2 == null;
        }

        public int hashCode() {
            ClassLoader classLoader = this.classLoader;
            int iHashCode = classLoader == null ? 43 : classLoader.hashCode();
            ProtectionDomain protectionDomain = this.protectionDomain;
            return ((iHashCode + 59) * 59) + (protectionDomain != null ? protectionDomain.hashCode() : 43);
        }

        public UsingUnsafe(ClassLoader classLoader) {
            this(classLoader, ClassLoadingStrategy.NO_PROTECTION_DOMAIN);
        }

        public UsingUnsafe(ClassLoader classLoader, ProtectionDomain protectionDomain) {
            this.classLoader = classLoader;
            this.protectionDomain = protectionDomain;
        }

        public static boolean isAvailable() {
            return DISPATCHER.isAvailable();
        }

        public static ClassInjector ofBootstrapLoader() {
            return new UsingUnsafe(ClassLoadingStrategy.BOOTSTRAP_LOADER);
        }

        public static ClassInjector ofClassPath() {
            return new UsingUnsafe(ClassLoader.getSystemClassLoader());
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public Map<TypeDescription, Class<?>> inject(Map<? extends TypeDescription, byte[]> map) {
            Dispatcher dispatcherInitialize = DISPATCHER.initialize();
            HashMap map2 = new HashMap();
            Object obj = this.classLoader;
            if (obj == null) {
                obj = BOOTSTRAP_LOADER_LOCK;
            }
            synchronized (obj) {
                for (Map.Entry<? extends TypeDescription, byte[]> entry : map.entrySet()) {
                    try {
                        map2.put(entry.getKey(), Class.forName(entry.getKey().getName(), false, this.classLoader));
                    } catch (ClassNotFoundException unused) {
                        map2.put(entry.getKey(), dispatcherInitialize.defineClass(this.classLoader, entry.getKey().getName(), entry.getValue(), this.protectionDomain));
                    }
                }
            }
            return map2;
        }

        interface Dispatcher {

            public interface Initializable {
                Dispatcher initialize();

                boolean isAvailable();
            }

            Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain);

            public enum CreationAction implements PrivilegedAction<Initializable> {
                INSTANCE;

                @Override // java.security.PrivilegedAction
                public Initializable run() {
                    try {
                        Class<?> cls = Class.forName("sun.misc.Unsafe");
                        return new Enabled(cls.getDeclaredField("theUnsafe"), cls.getMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class));
                    } catch (Exception e) {
                        return new Disabled(e);
                    }
                }
            }

            public static class Enabled implements Dispatcher, Initializable {
                private final Method defineClass;
                private final Field theUnsafe;

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher.Initializable
                public boolean isAvailable() {
                    return true;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof Enabled;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Enabled)) {
                        return false;
                    }
                    Enabled enabled = (Enabled) obj;
                    if (!enabled.canEqual(this)) {
                        return false;
                    }
                    Field field = this.theUnsafe;
                    Field field2 = enabled.theUnsafe;
                    if (field != null ? !field.equals(field2) : field2 != null) {
                        return false;
                    }
                    Method method = this.defineClass;
                    Method method2 = enabled.defineClass;
                    return method != null ? method.equals(method2) : method2 == null;
                }

                public int hashCode() {
                    Field field = this.theUnsafe;
                    int iHashCode = field == null ? 43 : field.hashCode();
                    Method method = this.defineClass;
                    return ((iHashCode + 59) * 59) + (method != null ? method.hashCode() : 43);
                }

                protected Enabled(Field field, Method method) {
                    this.theUnsafe = field;
                    this.defineClass = method;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher.Initializable
                public Dispatcher initialize() {
                    this.theUnsafe.setAccessible(true);
                    return this;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher
                public Class<?> defineClass(ClassLoader classLoader, String str, byte[] bArr, ProtectionDomain protectionDomain) {
                    try {
                        return (Class) this.defineClass.invoke(this.theUnsafe.get(null), str, bArr, 0, Integer.valueOf(bArr.length), classLoader, protectionDomain);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access Unsafe::defineClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking Unsafe::defineClass", e2.getCause());
                    }
                }
            }

            public static class Disabled implements Initializable {
                private final Exception exception;

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher.Initializable
                public boolean isAvailable() {
                    return false;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof Disabled;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Disabled)) {
                        return false;
                    }
                    Disabled disabled = (Disabled) obj;
                    if (!disabled.canEqual(this)) {
                        return false;
                    }
                    Exception exc = this.exception;
                    Exception exc2 = disabled.exception;
                    return exc != null ? exc.equals(exc2) : exc2 == null;
                }

                public int hashCode() {
                    Exception exc = this.exception;
                    return 59 + (exc == null ? 43 : exc.hashCode());
                }

                protected Disabled(Exception exc) {
                    this.exception = exc;
                }

                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingUnsafe.Dispatcher.Initializable
                public Dispatcher initialize() {
                    throw new IllegalStateException("Could not find sun.misc.Unsafe", this.exception);
                }
            }
        }
    }

    public static class UsingInstrumentation implements ClassInjector {
        private static final String CLASS_FILE_EXTENSION = ".class";
        private static final String PREFIX = "jar";
        private final File folder;
        private final Instrumentation instrumentation;
        private final RandomString randomString;
        private final Target target;

        public enum Target {
            BOOTSTRAP { // from class: net.bytebuddy.dynamic.loading.ClassInjector.UsingInstrumentation.Target.1
                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingInstrumentation.Target
                protected void inject(Instrumentation instrumentation, JarFile jarFile) {
                    instrumentation.appendToBootstrapClassLoaderSearch(jarFile);
                }
            },
            SYSTEM { // from class: net.bytebuddy.dynamic.loading.ClassInjector.UsingInstrumentation.Target.2
                @Override // net.bytebuddy.dynamic.loading.ClassInjector.UsingInstrumentation.Target
                protected void inject(Instrumentation instrumentation, JarFile jarFile) {
                    instrumentation.appendToSystemClassLoaderSearch(jarFile);
                }
            };

            protected abstract void inject(Instrumentation instrumentation, JarFile jarFile);
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof UsingInstrumentation;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UsingInstrumentation)) {
                return false;
            }
            UsingInstrumentation usingInstrumentation = (UsingInstrumentation) obj;
            if (!usingInstrumentation.canEqual(this)) {
                return false;
            }
            Instrumentation instrumentation = this.instrumentation;
            Instrumentation instrumentation2 = usingInstrumentation.instrumentation;
            if (instrumentation != null ? !instrumentation.equals(instrumentation2) : instrumentation2 != null) {
                return false;
            }
            Target target = this.target;
            Target target2 = usingInstrumentation.target;
            if (target != null ? !target.equals(target2) : target2 != null) {
                return false;
            }
            File file = this.folder;
            File file2 = usingInstrumentation.folder;
            if (file != null ? !file.equals(file2) : file2 != null) {
                return false;
            }
            RandomString randomString = this.randomString;
            RandomString randomString2 = usingInstrumentation.randomString;
            return randomString != null ? randomString.equals(randomString2) : randomString2 == null;
        }

        public int hashCode() {
            Instrumentation instrumentation = this.instrumentation;
            int iHashCode = instrumentation == null ? 43 : instrumentation.hashCode();
            Target target = this.target;
            int iHashCode2 = ((iHashCode + 59) * 59) + (target == null ? 43 : target.hashCode());
            File file = this.folder;
            int iHashCode3 = (iHashCode2 * 59) + (file == null ? 43 : file.hashCode());
            RandomString randomString = this.randomString;
            return (iHashCode3 * 59) + (randomString != null ? randomString.hashCode() : 43);
        }

        public static ClassInjector of(File file, Target target, Instrumentation instrumentation) {
            return new UsingInstrumentation(file, target, instrumentation, new RandomString());
        }

        protected UsingInstrumentation(File file, Target target, Instrumentation instrumentation, RandomString randomString) {
            this.folder = file;
            this.target = target;
            this.instrumentation = instrumentation;
            this.randomString = randomString;
        }

        @Override // net.bytebuddy.dynamic.loading.ClassInjector
        public Map<TypeDescription, Class<?>> inject(Map<? extends TypeDescription, byte[]> map) {
            File file = new File(this.folder, String.format("%s%s.jar", "jar", this.randomString.nextString()));
            try {
                if (!file.createNewFile()) {
                    throw new IllegalStateException("Cannot create file " + file);
                }
                JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(file));
                try {
                    for (Map.Entry<? extends TypeDescription, byte[]> entry : map.entrySet()) {
                        jarOutputStream.putNextEntry(new JarEntry(entry.getKey().getInternalName() + ".class"));
                        jarOutputStream.write(entry.getValue());
                    }
                    jarOutputStream.close();
                    this.target.inject(this.instrumentation, new JarFile(file));
                    HashMap map2 = new HashMap();
                    for (TypeDescription typeDescription : map.keySet()) {
                        map2.put(typeDescription, Class.forName(typeDescription.getName(), false, ClassLoader.getSystemClassLoader()));
                    }
                    return map2;
                } catch (Throwable th) {
                    jarOutputStream.close();
                    throw th;
                }
            } catch (IOException e) {
                throw new IllegalStateException("Cannot write jar file to disk", e);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Cannot load injected class", e2);
            }
        }
    }
}
