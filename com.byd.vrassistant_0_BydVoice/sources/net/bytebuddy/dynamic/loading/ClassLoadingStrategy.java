package net.bytebuddy.dynamic.loading;

import java.io.File;
import java.lang.ClassLoader;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Map;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.ByteArrayClassLoader;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy;

/* JADX INFO: loaded from: classes3.dex */
public interface ClassLoadingStrategy<T extends ClassLoader> {
    public static final ClassLoader BOOTSTRAP_LOADER = null;
    public static final ProtectionDomain NO_PROTECTION_DOMAIN = null;

    public interface Configurable<S extends ClassLoader> extends ClassLoadingStrategy<S> {
        Configurable<S> allowExistingTypes();

        Configurable<S> with(ProtectionDomain protectionDomain);

        Configurable<S> with(PackageDefinitionStrategy packageDefinitionStrategy);
    }

    Map<TypeDescription, Class<?>> load(T t, Map<TypeDescription, byte[]> map);

    public enum Default implements Configurable<ClassLoader> {
        WRAPPER(new WrappingDispatcher(ByteArrayClassLoader.PersistenceHandler.LATENT, false)),
        WRAPPER_PERSISTENT(new WrappingDispatcher(ByteArrayClassLoader.PersistenceHandler.MANIFEST, false)),
        CHILD_FIRST(new WrappingDispatcher(ByteArrayClassLoader.PersistenceHandler.LATENT, true)),
        CHILD_FIRST_PERSISTENT(new WrappingDispatcher(ByteArrayClassLoader.PersistenceHandler.MANIFEST, true)),
        INJECTION(new InjectionDispatcher());

        private static final boolean DEFAULT_FORBID_EXISTING = true;
        private final Configurable<ClassLoader> dispatcher;

        Default(Configurable configurable) {
            this.dispatcher = configurable;
        }

        @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy
        public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
            return this.dispatcher.load(classLoader, map);
        }

        @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Configurable
        public Configurable<ClassLoader> with(ProtectionDomain protectionDomain) {
            return this.dispatcher.with(protectionDomain);
        }

        @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Configurable
        public Configurable<ClassLoader> with(PackageDefinitionStrategy packageDefinitionStrategy) {
            return this.dispatcher.with(packageDefinitionStrategy);
        }

        @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Configurable
        public Configurable<ClassLoader> allowExistingTypes() {
            return this.dispatcher.allowExistingTypes();
        }

        protected static class InjectionDispatcher implements Configurable<ClassLoader> {
            private final boolean forbidExisting;
            private final PackageDefinitionStrategy packageDefinitionStrategy;
            private final ProtectionDomain protectionDomain;

            protected boolean canEqual(Object obj) {
                return obj instanceof InjectionDispatcher;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof InjectionDispatcher)) {
                    return false;
                }
                InjectionDispatcher injectionDispatcher = (InjectionDispatcher) obj;
                if (!injectionDispatcher.canEqual(this)) {
                    return false;
                }
                ProtectionDomain protectionDomain = this.protectionDomain;
                ProtectionDomain protectionDomain2 = injectionDispatcher.protectionDomain;
                if (protectionDomain != null ? !protectionDomain.equals(protectionDomain2) : protectionDomain2 != null) {
                    return false;
                }
                PackageDefinitionStrategy packageDefinitionStrategy = this.packageDefinitionStrategy;
                PackageDefinitionStrategy packageDefinitionStrategy2 = injectionDispatcher.packageDefinitionStrategy;
                if (packageDefinitionStrategy != null ? packageDefinitionStrategy.equals(packageDefinitionStrategy2) : packageDefinitionStrategy2 == null) {
                    return this.forbidExisting == injectionDispatcher.forbidExisting;
                }
                return false;
            }

            public int hashCode() {
                ProtectionDomain protectionDomain = this.protectionDomain;
                int iHashCode = protectionDomain == null ? 43 : protectionDomain.hashCode();
                PackageDefinitionStrategy packageDefinitionStrategy = this.packageDefinitionStrategy;
                return ((((iHashCode + 59) * 59) + (packageDefinitionStrategy != null ? packageDefinitionStrategy.hashCode() : 43)) * 59) + (this.forbidExisting ? 79 : 97);
            }

            protected InjectionDispatcher() {
                this(NO_PROTECTION_DOMAIN, PackageDefinitionStrategy.NoOp.INSTANCE, true);
            }

            private InjectionDispatcher(ProtectionDomain protectionDomain, PackageDefinitionStrategy packageDefinitionStrategy, boolean z) {
                this.protectionDomain = protectionDomain;
                this.packageDefinitionStrategy = packageDefinitionStrategy;
                this.forbidExisting = z;
            }

            @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy
            public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
                return new ClassInjector.UsingReflection(classLoader, this.protectionDomain, this.packageDefinitionStrategy, this.forbidExisting).inject(map);
            }

            @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Configurable
            public Configurable<ClassLoader> with(ProtectionDomain protectionDomain) {
                return new InjectionDispatcher(protectionDomain, this.packageDefinitionStrategy, this.forbidExisting);
            }

            @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Configurable
            public Configurable<ClassLoader> with(PackageDefinitionStrategy packageDefinitionStrategy) {
                return new InjectionDispatcher(this.protectionDomain, packageDefinitionStrategy, this.forbidExisting);
            }

            @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Configurable
            public Configurable<ClassLoader> allowExistingTypes() {
                return new InjectionDispatcher(this.protectionDomain, this.packageDefinitionStrategy, false);
            }
        }

        protected static class WrappingDispatcher implements Configurable<ClassLoader> {
            private static final boolean CHILD_FIRST = true;
            private static final boolean PARENT_FIRST = false;
            private final boolean childFirst;
            private final boolean forbidExisting;
            private final PackageDefinitionStrategy packageDefinitionStrategy;
            private final ByteArrayClassLoader.PersistenceHandler persistenceHandler;
            private final ProtectionDomain protectionDomain;

            protected boolean canEqual(Object obj) {
                return obj instanceof WrappingDispatcher;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WrappingDispatcher)) {
                    return false;
                }
                WrappingDispatcher wrappingDispatcher = (WrappingDispatcher) obj;
                if (!wrappingDispatcher.canEqual(this)) {
                    return false;
                }
                ProtectionDomain protectionDomain = this.protectionDomain;
                ProtectionDomain protectionDomain2 = wrappingDispatcher.protectionDomain;
                if (protectionDomain != null ? !protectionDomain.equals(protectionDomain2) : protectionDomain2 != null) {
                    return false;
                }
                ByteArrayClassLoader.PersistenceHandler persistenceHandler = this.persistenceHandler;
                ByteArrayClassLoader.PersistenceHandler persistenceHandler2 = wrappingDispatcher.persistenceHandler;
                if (persistenceHandler != null ? !persistenceHandler.equals(persistenceHandler2) : persistenceHandler2 != null) {
                    return false;
                }
                PackageDefinitionStrategy packageDefinitionStrategy = this.packageDefinitionStrategy;
                PackageDefinitionStrategy packageDefinitionStrategy2 = wrappingDispatcher.packageDefinitionStrategy;
                if (packageDefinitionStrategy != null ? packageDefinitionStrategy.equals(packageDefinitionStrategy2) : packageDefinitionStrategy2 == null) {
                    return this.childFirst == wrappingDispatcher.childFirst && this.forbidExisting == wrappingDispatcher.forbidExisting;
                }
                return false;
            }

            public int hashCode() {
                ProtectionDomain protectionDomain = this.protectionDomain;
                int iHashCode = protectionDomain == null ? 43 : protectionDomain.hashCode();
                ByteArrayClassLoader.PersistenceHandler persistenceHandler = this.persistenceHandler;
                int iHashCode2 = ((iHashCode + 59) * 59) + (persistenceHandler == null ? 43 : persistenceHandler.hashCode());
                PackageDefinitionStrategy packageDefinitionStrategy = this.packageDefinitionStrategy;
                return (((((iHashCode2 * 59) + (packageDefinitionStrategy != null ? packageDefinitionStrategy.hashCode() : 43)) * 59) + (this.childFirst ? 79 : 97)) * 59) + (this.forbidExisting ? 79 : 97);
            }

            protected WrappingDispatcher(ByteArrayClassLoader.PersistenceHandler persistenceHandler, boolean z) {
                this(NO_PROTECTION_DOMAIN, PackageDefinitionStrategy.Trivial.INSTANCE, persistenceHandler, z, true);
            }

            private WrappingDispatcher(ProtectionDomain protectionDomain, PackageDefinitionStrategy packageDefinitionStrategy, ByteArrayClassLoader.PersistenceHandler persistenceHandler, boolean z, boolean z2) {
                this.protectionDomain = protectionDomain;
                this.packageDefinitionStrategy = packageDefinitionStrategy;
                this.persistenceHandler = persistenceHandler;
                this.childFirst = z;
                this.forbidExisting = z2;
            }

            @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy
            public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
                return ByteArrayClassLoader.load(classLoader, map, this.protectionDomain, this.persistenceHandler, this.packageDefinitionStrategy, this.childFirst, this.forbidExisting);
            }

            @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Configurable
            public Configurable<ClassLoader> with(ProtectionDomain protectionDomain) {
                return new WrappingDispatcher(protectionDomain, this.packageDefinitionStrategy, this.persistenceHandler, this.childFirst, this.forbidExisting);
            }

            @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Configurable
            public Configurable<ClassLoader> with(PackageDefinitionStrategy packageDefinitionStrategy) {
                return new WrappingDispatcher(this.protectionDomain, packageDefinitionStrategy, this.persistenceHandler, this.childFirst, this.forbidExisting);
            }

            @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Configurable
            public Configurable<ClassLoader> allowExistingTypes() {
                return new InjectionDispatcher(this.protectionDomain, this.packageDefinitionStrategy, false);
            }
        }
    }

    public static class UsingLookup implements ClassLoadingStrategy<ClassLoader> {
        private final ClassInjector classInjector;
        private final ClassLoader classLoader;

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
            ClassInjector classInjector = this.classInjector;
            ClassInjector classInjector2 = usingLookup.classInjector;
            if (classInjector != null ? !classInjector.equals(classInjector2) : classInjector2 != null) {
                return false;
            }
            ClassLoader classLoader = this.classLoader;
            ClassLoader classLoader2 = usingLookup.classLoader;
            return classLoader != null ? classLoader.equals(classLoader2) : classLoader2 == null;
        }

        public int hashCode() {
            ClassInjector classInjector = this.classInjector;
            int iHashCode = classInjector == null ? 43 : classInjector.hashCode();
            ClassLoader classLoader = this.classLoader;
            return ((iHashCode + 59) * 59) + (classLoader != null ? classLoader.hashCode() : 43);
        }

        protected UsingLookup(ClassInjector classInjector, ClassLoader classLoader) {
            this.classInjector = classInjector;
            this.classLoader = classLoader;
        }

        public static ClassLoadingStrategy<ClassLoader> of(Object obj) {
            ClassInjector.UsingLookup usingLookupOf = ClassInjector.UsingLookup.of(obj);
            return new UsingLookup(usingLookupOf, usingLookupOf.lookupType().getClassLoader());
        }

        @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy
        public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
            if (classLoader != this.classLoader) {
                throw new IllegalStateException("Cannot define a type in " + classLoader + " with lookup based on " + this.classLoader);
            }
            return this.classInjector.inject(map);
        }
    }

    public static class ForBootstrapInjection implements ClassLoadingStrategy<ClassLoader> {
        private final File folder;
        private final Instrumentation instrumentation;

        protected boolean canEqual(Object obj) {
            return obj instanceof ForBootstrapInjection;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForBootstrapInjection)) {
                return false;
            }
            ForBootstrapInjection forBootstrapInjection = (ForBootstrapInjection) obj;
            if (!forBootstrapInjection.canEqual(this)) {
                return false;
            }
            Instrumentation instrumentation = this.instrumentation;
            Instrumentation instrumentation2 = forBootstrapInjection.instrumentation;
            if (instrumentation != null ? !instrumentation.equals(instrumentation2) : instrumentation2 != null) {
                return false;
            }
            File file = this.folder;
            File file2 = forBootstrapInjection.folder;
            return file != null ? file.equals(file2) : file2 == null;
        }

        public int hashCode() {
            Instrumentation instrumentation = this.instrumentation;
            int iHashCode = instrumentation == null ? 43 : instrumentation.hashCode();
            File file = this.folder;
            return ((iHashCode + 59) * 59) + (file != null ? file.hashCode() : 43);
        }

        public ForBootstrapInjection(Instrumentation instrumentation, File file) {
            this.instrumentation = instrumentation;
            this.folder = file;
        }

        @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy
        public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
            return (classLoader == null ? ClassInjector.UsingInstrumentation.of(this.folder, ClassInjector.UsingInstrumentation.Target.BOOTSTRAP, this.instrumentation) : new ClassInjector.UsingReflection(classLoader)).inject(map);
        }
    }

    public static class ForUnsafeInjection implements ClassLoadingStrategy<ClassLoader> {
        private final ProtectionDomain protectionDomain;

        protected boolean canEqual(Object obj) {
            return obj instanceof ForUnsafeInjection;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForUnsafeInjection)) {
                return false;
            }
            ForUnsafeInjection forUnsafeInjection = (ForUnsafeInjection) obj;
            if (!forUnsafeInjection.canEqual(this)) {
                return false;
            }
            ProtectionDomain protectionDomain = this.protectionDomain;
            ProtectionDomain protectionDomain2 = forUnsafeInjection.protectionDomain;
            return protectionDomain != null ? protectionDomain.equals(protectionDomain2) : protectionDomain2 == null;
        }

        public int hashCode() {
            ProtectionDomain protectionDomain = this.protectionDomain;
            return 59 + (protectionDomain == null ? 43 : protectionDomain.hashCode());
        }

        public ForUnsafeInjection() {
            this(NO_PROTECTION_DOMAIN);
        }

        public ForUnsafeInjection(ProtectionDomain protectionDomain) {
            this.protectionDomain = protectionDomain;
        }

        @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy
        public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
            return new ClassInjector.UsingUnsafe(classLoader, this.protectionDomain).inject(map);
        }
    }
}
