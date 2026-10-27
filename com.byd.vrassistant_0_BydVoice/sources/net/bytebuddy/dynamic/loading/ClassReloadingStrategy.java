package net.bytebuddy.dynamic.loading;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassInjector;

/* JADX INFO: loaded from: classes3.dex */
public class ClassReloadingStrategy implements ClassLoadingStrategy<ClassLoader> {
    private static final String INSTALLER_TYPE = "net.bytebuddy.agent.Installer";
    private static final String INSTRUMENTATION_GETTER = "getInstrumentation";
    private static final Object STATIC_MEMBER = null;
    private final BootstrapInjection bootstrapInjection;
    private final Instrumentation instrumentation;
    private final Map<String, Class<?>> preregisteredTypes;
    private final Strategy strategy;

    protected boolean canEqual(Object obj) {
        return obj instanceof ClassReloadingStrategy;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClassReloadingStrategy)) {
            return false;
        }
        ClassReloadingStrategy classReloadingStrategy = (ClassReloadingStrategy) obj;
        if (!classReloadingStrategy.canEqual(this)) {
            return false;
        }
        Instrumentation instrumentation = this.instrumentation;
        Instrumentation instrumentation2 = classReloadingStrategy.instrumentation;
        if (instrumentation != null ? !instrumentation.equals(instrumentation2) : instrumentation2 != null) {
            return false;
        }
        Strategy strategy = this.strategy;
        Strategy strategy2 = classReloadingStrategy.strategy;
        if (strategy != null ? !strategy.equals(strategy2) : strategy2 != null) {
            return false;
        }
        BootstrapInjection bootstrapInjection = this.bootstrapInjection;
        BootstrapInjection bootstrapInjection2 = classReloadingStrategy.bootstrapInjection;
        if (bootstrapInjection != null ? !bootstrapInjection.equals(bootstrapInjection2) : bootstrapInjection2 != null) {
            return false;
        }
        Map<String, Class<?>> map = this.preregisteredTypes;
        Map<String, Class<?>> map2 = classReloadingStrategy.preregisteredTypes;
        return map != null ? map.equals(map2) : map2 == null;
    }

    public int hashCode() {
        Instrumentation instrumentation = this.instrumentation;
        int iHashCode = instrumentation == null ? 43 : instrumentation.hashCode();
        Strategy strategy = this.strategy;
        int iHashCode2 = ((iHashCode + 59) * 59) + (strategy == null ? 43 : strategy.hashCode());
        BootstrapInjection bootstrapInjection = this.bootstrapInjection;
        int iHashCode3 = (iHashCode2 * 59) + (bootstrapInjection == null ? 43 : bootstrapInjection.hashCode());
        Map<String, Class<?>> map = this.preregisteredTypes;
        return (iHashCode3 * 59) + (map != null ? map.hashCode() : 43);
    }

    public ClassReloadingStrategy(Instrumentation instrumentation, Strategy strategy) {
        this(instrumentation, strategy, BootstrapInjection.Disabled.INSTANCE, Collections.emptyMap());
    }

    protected ClassReloadingStrategy(Instrumentation instrumentation, Strategy strategy, BootstrapInjection bootstrapInjection, Map<String, Class<?>> map) {
        this.instrumentation = instrumentation;
        this.strategy = strategy.validate(instrumentation);
        this.bootstrapInjection = bootstrapInjection;
        this.preregisteredTypes = map;
    }

    public static ClassReloadingStrategy of(Instrumentation instrumentation) {
        Strategy strategy;
        if (instrumentation.isRedefineClassesSupported()) {
            strategy = Strategy.REDEFINITION;
        } else if (instrumentation.isRetransformClassesSupported()) {
            strategy = Strategy.RETRANSFORMATION;
        } else {
            throw new IllegalArgumentException("Instrumentation does not support manipulation of loaded classes: " + instrumentation);
        }
        return new ClassReloadingStrategy(instrumentation, strategy);
    }

    public static ClassReloadingStrategy fromInstalledAgent() {
        try {
            return of((Instrumentation) ClassLoader.getSystemClassLoader().loadClass(INSTALLER_TYPE).getMethod(INSTRUMENTATION_GETTER, new Class[0]).invoke(STATIC_MEMBER, new Object[0]));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("The Byte Buddy agent is not installed or not accessible", e2);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: java.lang.instrument.UnmodifiableClassException */
    @Override // net.bytebuddy.dynamic.loading.ClassLoadingStrategy
    public Map<TypeDescription, Class<?>> load(ClassLoader classLoader, Map<TypeDescription, byte[]> map) {
        HashMap map2 = new HashMap(this.preregisteredTypes);
        for (Class cls : this.instrumentation.getInitiatedClasses(classLoader)) {
            map2.put(TypeDescription.ForLoadedType.getName(cls), cls);
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        HashMap map3 = new HashMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<TypeDescription, byte[]> entry : map.entrySet()) {
            Class cls2 = (Class) map2.get(entry.getKey().getName());
            if (cls2 != null) {
                concurrentHashMap.put(cls2, new ClassDefinition(cls2, entry.getValue()));
                map3.put(entry.getKey(), cls2);
            } else {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        try {
            this.strategy.apply(this.instrumentation, concurrentHashMap);
            if (!linkedHashMap.isEmpty()) {
                map3.putAll((classLoader == null ? this.bootstrapInjection.make(this.instrumentation) : new ClassInjector.UsingReflection(classLoader)).inject(linkedHashMap));
            }
            return map3;
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Could not locate classes for redefinition", e);
        } catch (UnmodifiableClassException e2) {
            throw new IllegalStateException("Cannot redefine specified class", e2);
        }
    }

    public ClassReloadingStrategy reset(Class<?>... clsArr) throws IOException {
        return clsArr.length == 0 ? this : reset(ClassFileLocator.ForClassLoader.of(clsArr[0].getClassLoader()), clsArr);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: java.lang.instrument.UnmodifiableClassException */
    public ClassReloadingStrategy reset(ClassFileLocator classFileLocator, Class<?>... clsArr) throws IOException {
        if (clsArr.length > 0) {
            try {
                this.strategy.reset(this.instrumentation, classFileLocator, Arrays.asList(clsArr));
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Cannot locate types " + Arrays.toString(clsArr), e);
            } catch (UnmodifiableClassException e2) {
                throw new IllegalStateException("Cannot reset types " + Arrays.toString(clsArr), e2);
            }
        }
        return this;
    }

    public ClassReloadingStrategy enableBootstrapInjection(File file) {
        return new ClassReloadingStrategy(this.instrumentation, this.strategy, new BootstrapInjection.Enabled(file), this.preregisteredTypes);
    }

    public ClassReloadingStrategy preregistered(Class<?>... clsArr) {
        HashMap map = new HashMap(this.preregisteredTypes);
        for (Class<?> cls : clsArr) {
            map.put(TypeDescription.ForLoadedType.getName(cls), cls);
        }
        return new ClassReloadingStrategy(this.instrumentation, this.strategy, this.bootstrapInjection, map);
    }

    public enum Strategy {
        REDEFINITION(true) { // from class: net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy.1
            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            protected void apply(Instrumentation instrumentation, Map<Class<?>, ClassDefinition> map) throws ClassNotFoundException, UnmodifiableClassException {
                instrumentation.redefineClasses((ClassDefinition[]) map.values().toArray(new ClassDefinition[map.size()]));
            }

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            protected Strategy validate(Instrumentation instrumentation) {
                if (instrumentation.isRedefineClassesSupported()) {
                    return this;
                }
                throw new IllegalArgumentException("Does not support redefinition: " + instrumentation);
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: java.lang.instrument.UnmodifiableClassException */
            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            public void reset(Instrumentation instrumentation, ClassFileLocator classFileLocator, List<Class<?>> list) throws ClassNotFoundException, IOException, UnmodifiableClassException {
                HashMap map = new HashMap(list.size());
                for (Class<?> cls : list) {
                    map.put(cls, new ClassDefinition(cls, classFileLocator.locate(TypeDescription.ForLoadedType.getName(cls)).resolve()));
                }
                apply(instrumentation, map);
            }
        },
        RETRANSFORMATION(0 == true ? 1 : 0) { // from class: net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy.2
            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            protected void apply(Instrumentation instrumentation, Map<Class<?>, ClassDefinition> map) throws UnmodifiableClassException {
                ClassRedefinitionTransformer classRedefinitionTransformer = new ClassRedefinitionTransformer(map);
                synchronized (this) {
                    instrumentation.addTransformer(classRedefinitionTransformer, true);
                    try {
                        instrumentation.retransformClasses((Class[]) map.keySet().toArray(new Class[map.size()]));
                    } finally {
                        instrumentation.removeTransformer(classRedefinitionTransformer);
                    }
                }
                classRedefinitionTransformer.assertTransformation();
            }

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            protected Strategy validate(Instrumentation instrumentation) {
                if (instrumentation.isRetransformClassesSupported()) {
                    return this;
                }
                throw new IllegalArgumentException("Does not support retransformation: " + instrumentation);
            }

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy
            public void reset(Instrumentation instrumentation, ClassFileLocator classFileLocator, List<Class<?>> list) throws IOException, ClassNotFoundException, UnmodifiableClassException {
                for (Class<?> cls : list) {
                    if (!instrumentation.isModifiableClass(cls)) {
                        throw new IllegalArgumentException("Cannot modify type: " + cls);
                    }
                }
                instrumentation.addTransformer(ClassResettingTransformer.INSTANCE, true);
                try {
                    instrumentation.retransformClasses((Class[]) list.toArray(new Class[list.size()]));
                } finally {
                    instrumentation.removeTransformer(ClassResettingTransformer.INSTANCE);
                }
            }
        };

        private static final byte[] NO_REDEFINITION = null;
        private static final boolean REDEFINE_CLASSES = true;
        private final boolean redefinition;

        protected abstract void apply(Instrumentation instrumentation, Map<Class<?>, ClassDefinition> map) throws ClassNotFoundException, UnmodifiableClassException;

        public abstract void reset(Instrumentation instrumentation, ClassFileLocator classFileLocator, List<Class<?>> list) throws IOException, ClassNotFoundException, UnmodifiableClassException;

        protected abstract Strategy validate(Instrumentation instrumentation);

        Strategy(boolean z) {
            this.redefinition = z;
        }

        public boolean isRedefinition() {
            return this.redefinition;
        }

        protected static class ClassRedefinitionTransformer implements ClassFileTransformer {
            private final Map<Class<?>, ClassDefinition> redefinedClasses;

            protected ClassRedefinitionTransformer(Map<Class<?>, ClassDefinition> map) {
                this.redefinedClasses = map;
            }

            public byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                ClassDefinition classDefinitionRemove;
                if (str != null && (classDefinitionRemove = this.redefinedClasses.remove(cls)) != null) {
                    return classDefinitionRemove.getDefinitionClassFile();
                }
                return Strategy.NO_REDEFINITION;
            }

            public void assertTransformation() {
                if (!this.redefinedClasses.isEmpty()) {
                    throw new IllegalStateException("Could not transform: " + this.redefinedClasses.keySet());
                }
            }
        }

        protected enum ClassResettingTransformer implements ClassFileTransformer {
            INSTANCE;

            public byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                return Strategy.NO_REDEFINITION;
            }
        }
    }

    protected interface BootstrapInjection {
        ClassInjector make(Instrumentation instrumentation);

        public enum Disabled implements BootstrapInjection {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.BootstrapInjection
            public ClassInjector make(Instrumentation instrumentation) {
                throw new IllegalStateException("Bootstrap injection is not enabled");
            }
        }

        public static class Enabled implements BootstrapInjection {
            private final File folder;

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
                File file = this.folder;
                File file2 = enabled.folder;
                return file != null ? file.equals(file2) : file2 == null;
            }

            public int hashCode() {
                File file = this.folder;
                return 59 + (file == null ? 43 : file.hashCode());
            }

            protected Enabled(File file) {
                this.folder = file;
            }

            @Override // net.bytebuddy.dynamic.loading.ClassReloadingStrategy.BootstrapInjection
            public ClassInjector make(Instrumentation instrumentation) {
                return ClassInjector.UsingInstrumentation.of(this.folder, ClassInjector.UsingInstrumentation.Target.BOOTSTRAP, instrumentation);
            }
        }
    }
}
