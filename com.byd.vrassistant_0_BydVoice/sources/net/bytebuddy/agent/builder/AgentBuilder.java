package net.bytebuddy.agent.builder;

import com.android.multidex.ClassPathElement;
import java.io.File;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.EntryPoint;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.MethodManifestation;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.TypeManifestation;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.NexusAccessor;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.ExceptionMethod;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.JavaType;

/* JADX INFO: loaded from: classes3.dex */
public interface AgentBuilder {

    public interface Identified {

        public interface Extendable extends AgentBuilder, Identified {
            AgentBuilder asDecorator();
        }

        public interface Narrowable extends Matchable<Narrowable>, Identified {
        }

        Extendable transform(Transformer transformer);
    }

    public interface Ignored extends Matchable<Ignored>, AgentBuilder {
    }

    public interface RedefinitionListenable extends AgentBuilder {

        public interface WithImplicitDiscoveryStrategy extends RedefinitionListenable {
            RedefinitionListenable redefineOnly(Class<?>... clsArr);

            RedefinitionListenable with(RedefinitionStrategy.DiscoveryStrategy discoveryStrategy);
        }

        public interface WithoutBatchStrategy extends WithImplicitDiscoveryStrategy {
            WithImplicitDiscoveryStrategy with(RedefinitionStrategy.BatchAllocator batchAllocator);
        }

        RedefinitionListenable with(RedefinitionStrategy.Listener listener);

        AgentBuilder withResubmission(RedefinitionStrategy.ResubmissionScheduler resubmissionScheduler);

        AgentBuilder withResubmission(RedefinitionStrategy.ResubmissionScheduler resubmissionScheduler, ElementMatcher<? super Throwable> elementMatcher);
    }

    AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection);

    AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Class<?>... clsArr);

    AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, JavaModule... javaModuleArr);

    AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection);

    AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Class<?>... clsArr);

    AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, JavaModule... javaModuleArr);

    AgentBuilder disableBootstrapInjection();

    AgentBuilder disableClassFormatChanges();

    AgentBuilder disableNativeMethodPrefix();

    AgentBuilder enableBootstrapInjection(Instrumentation instrumentation, File file);

    AgentBuilder enableNativeMethodPrefix(String str);

    AgentBuilder enableUnsafeBootstrapInjection();

    Ignored ignore(RawMatcher rawMatcher);

    Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher);

    Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2);

    Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3);

    ResettableClassFileTransformer installOn(Instrumentation instrumentation);

    ResettableClassFileTransformer installOnByteBuddyAgent();

    ClassFileTransformer makeRaw();

    Identified.Narrowable type(RawMatcher rawMatcher);

    Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher);

    Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2);

    Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3);

    RedefinitionListenable.WithoutBatchStrategy with(RedefinitionStrategy redefinitionStrategy);

    AgentBuilder with(ByteBuddy byteBuddy);

    AgentBuilder with(CircularityLock circularityLock);

    AgentBuilder with(DescriptionStrategy descriptionStrategy);

    AgentBuilder with(FallbackStrategy fallbackStrategy);

    AgentBuilder with(InitializationStrategy initializationStrategy);

    AgentBuilder with(InstallationListener installationListener);

    AgentBuilder with(LambdaInstrumentationStrategy lambdaInstrumentationStrategy);

    AgentBuilder with(Listener listener);

    AgentBuilder with(LocationStrategy locationStrategy);

    AgentBuilder with(PoolStrategy poolStrategy);

    AgentBuilder with(TypeStrategy typeStrategy);

    public interface Matchable<T extends Matchable<T>> {
        T and(RawMatcher rawMatcher);

        T and(ElementMatcher<? super TypeDescription> elementMatcher);

        T and(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2);

        T and(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3);

        T or(RawMatcher rawMatcher);

        T or(ElementMatcher<? super TypeDescription> elementMatcher);

        T or(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2);

        T or(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3);

        public static abstract class AbstractBase<S extends Matchable<S>> implements Matchable<S> {
            @Override // net.bytebuddy.agent.builder.AgentBuilder.Matchable
            public S and(ElementMatcher<? super TypeDescription> elementMatcher) {
                return (S) and(elementMatcher, ElementMatchers.any());
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Matchable
            public S and(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
                return (S) and(elementMatcher, elementMatcher2, ElementMatchers.any());
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Matchable
            public S and(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
                return and(new RawMatcher.ForElementMatchers(elementMatcher, elementMatcher2, elementMatcher3));
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Matchable
            public S or(ElementMatcher<? super TypeDescription> elementMatcher) {
                return (S) or(elementMatcher, ElementMatchers.any());
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Matchable
            public S or(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
                return (S) or(elementMatcher, elementMatcher2, ElementMatchers.any());
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Matchable
            public S or(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
                return or(new RawMatcher.ForElementMatchers(elementMatcher, elementMatcher2, elementMatcher3));
            }
        }
    }

    public interface RawMatcher {
        boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain);

        public enum Trivial implements RawMatcher {
            MATCHING(true),
            NON_MATCHING(false);

            private final boolean matches;

            Trivial(boolean z) {
                this.matches = z;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RawMatcher
            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return this.matches;
            }
        }

        public enum ForLoadState implements RawMatcher {
            LOADED(false),
            UNLOADED(true);

            private final boolean unloaded;

            ForLoadState(boolean z) {
                this.unloaded = z;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RawMatcher
            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return (cls == null) == this.unloaded;
            }
        }

        public enum ForResolvableTypes implements RawMatcher {
            INSTANCE;

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RawMatcher
            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                if (cls == null) {
                    return true;
                }
                try {
                    return Class.forName(cls.getName(), true, classLoader) == cls;
                } catch (Throwable unused) {
                    return false;
                }
            }

            public RawMatcher inverted() {
                return new Inversion(this);
            }
        }

        public static class Conjunction implements RawMatcher {
            private final RawMatcher left;
            private final RawMatcher right;

            protected boolean canEqual(Object obj) {
                return obj instanceof Conjunction;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Conjunction)) {
                    return false;
                }
                Conjunction conjunction = (Conjunction) obj;
                if (!conjunction.canEqual(this)) {
                    return false;
                }
                RawMatcher rawMatcher = this.left;
                RawMatcher rawMatcher2 = conjunction.left;
                if (rawMatcher != null ? !rawMatcher.equals(rawMatcher2) : rawMatcher2 != null) {
                    return false;
                }
                RawMatcher rawMatcher3 = this.right;
                RawMatcher rawMatcher4 = conjunction.right;
                return rawMatcher3 != null ? rawMatcher3.equals(rawMatcher4) : rawMatcher4 == null;
            }

            public int hashCode() {
                RawMatcher rawMatcher = this.left;
                int iHashCode = rawMatcher == null ? 43 : rawMatcher.hashCode();
                RawMatcher rawMatcher2 = this.right;
                return ((iHashCode + 59) * 59) + (rawMatcher2 != null ? rawMatcher2.hashCode() : 43);
            }

            protected Conjunction(RawMatcher rawMatcher, RawMatcher rawMatcher2) {
                this.left = rawMatcher;
                this.right = rawMatcher2;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RawMatcher
            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return this.left.matches(typeDescription, classLoader, javaModule, cls, protectionDomain) && this.right.matches(typeDescription, classLoader, javaModule, cls, protectionDomain);
            }
        }

        public static class Disjunction implements RawMatcher {
            private final RawMatcher left;
            private final RawMatcher right;

            protected boolean canEqual(Object obj) {
                return obj instanceof Disjunction;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Disjunction)) {
                    return false;
                }
                Disjunction disjunction = (Disjunction) obj;
                if (!disjunction.canEqual(this)) {
                    return false;
                }
                RawMatcher rawMatcher = this.left;
                RawMatcher rawMatcher2 = disjunction.left;
                if (rawMatcher != null ? !rawMatcher.equals(rawMatcher2) : rawMatcher2 != null) {
                    return false;
                }
                RawMatcher rawMatcher3 = this.right;
                RawMatcher rawMatcher4 = disjunction.right;
                return rawMatcher3 != null ? rawMatcher3.equals(rawMatcher4) : rawMatcher4 == null;
            }

            public int hashCode() {
                RawMatcher rawMatcher = this.left;
                int iHashCode = rawMatcher == null ? 43 : rawMatcher.hashCode();
                RawMatcher rawMatcher2 = this.right;
                return ((iHashCode + 59) * 59) + (rawMatcher2 != null ? rawMatcher2.hashCode() : 43);
            }

            protected Disjunction(RawMatcher rawMatcher, RawMatcher rawMatcher2) {
                this.left = rawMatcher;
                this.right = rawMatcher2;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RawMatcher
            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return this.left.matches(typeDescription, classLoader, javaModule, cls, protectionDomain) || this.right.matches(typeDescription, classLoader, javaModule, cls, protectionDomain);
            }
        }

        public static class Inversion implements RawMatcher {
            private final RawMatcher matcher;

            protected boolean canEqual(Object obj) {
                return obj instanceof Inversion;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Inversion)) {
                    return false;
                }
                Inversion inversion = (Inversion) obj;
                if (!inversion.canEqual(this)) {
                    return false;
                }
                RawMatcher rawMatcher = this.matcher;
                RawMatcher rawMatcher2 = inversion.matcher;
                return rawMatcher != null ? rawMatcher.equals(rawMatcher2) : rawMatcher2 == null;
            }

            public int hashCode() {
                RawMatcher rawMatcher = this.matcher;
                return 59 + (rawMatcher == null ? 43 : rawMatcher.hashCode());
            }

            public Inversion(RawMatcher rawMatcher) {
                this.matcher = rawMatcher;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RawMatcher
            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return !this.matcher.matches(typeDescription, classLoader, javaModule, cls, protectionDomain);
            }
        }

        public static class ForElementMatchers implements RawMatcher {
            private final ElementMatcher<? super ClassLoader> classLoaderMatcher;
            private final ElementMatcher<? super JavaModule> moduleMatcher;
            private final ElementMatcher<? super TypeDescription> typeMatcher;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForElementMatchers;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForElementMatchers)) {
                    return false;
                }
                ForElementMatchers forElementMatchers = (ForElementMatchers) obj;
                if (!forElementMatchers.canEqual(this)) {
                    return false;
                }
                ElementMatcher<? super TypeDescription> elementMatcher = this.typeMatcher;
                ElementMatcher<? super TypeDescription> elementMatcher2 = forElementMatchers.typeMatcher;
                if (elementMatcher != null ? !elementMatcher.equals(elementMatcher2) : elementMatcher2 != null) {
                    return false;
                }
                ElementMatcher<? super ClassLoader> elementMatcher3 = this.classLoaderMatcher;
                ElementMatcher<? super ClassLoader> elementMatcher4 = forElementMatchers.classLoaderMatcher;
                if (elementMatcher3 != null ? !elementMatcher3.equals(elementMatcher4) : elementMatcher4 != null) {
                    return false;
                }
                ElementMatcher<? super JavaModule> elementMatcher5 = this.moduleMatcher;
                ElementMatcher<? super JavaModule> elementMatcher6 = forElementMatchers.moduleMatcher;
                return elementMatcher5 != null ? elementMatcher5.equals(elementMatcher6) : elementMatcher6 == null;
            }

            public int hashCode() {
                ElementMatcher<? super TypeDescription> elementMatcher = this.typeMatcher;
                int iHashCode = elementMatcher == null ? 43 : elementMatcher.hashCode();
                ElementMatcher<? super ClassLoader> elementMatcher2 = this.classLoaderMatcher;
                int iHashCode2 = ((iHashCode + 59) * 59) + (elementMatcher2 == null ? 43 : elementMatcher2.hashCode());
                ElementMatcher<? super JavaModule> elementMatcher3 = this.moduleMatcher;
                return (iHashCode2 * 59) + (elementMatcher3 != null ? elementMatcher3.hashCode() : 43);
            }

            public ForElementMatchers(ElementMatcher<? super TypeDescription> elementMatcher) {
                this(elementMatcher, ElementMatchers.any());
            }

            public ForElementMatchers(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
                this(elementMatcher, elementMatcher2, ElementMatchers.any());
            }

            public ForElementMatchers(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
                this.typeMatcher = elementMatcher;
                this.classLoaderMatcher = elementMatcher2;
                this.moduleMatcher = elementMatcher3;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RawMatcher
            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return this.moduleMatcher.matches(javaModule) && this.classLoaderMatcher.matches(classLoader) && this.typeMatcher.matches(typeDescription);
            }
        }
    }

    public interface Listener {
        public static final boolean LOADED = true;

        public static abstract class Adapter implements Listener {
            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
            }
        }

        public enum NoOp implements Listener {
            INSTANCE;

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
            }
        }

        void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z);

        void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z);

        void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th);

        void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z);

        void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType);

        public static class StreamWriting implements Listener {
            protected static final String PREFIX = "[Byte Buddy]";
            private final PrintStream printStream;

            protected boolean canEqual(Object obj) {
                return obj instanceof StreamWriting;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof StreamWriting)) {
                    return false;
                }
                StreamWriting streamWriting = (StreamWriting) obj;
                if (!streamWriting.canEqual(this)) {
                    return false;
                }
                PrintStream printStream = this.printStream;
                PrintStream printStream2 = streamWriting.printStream;
                return printStream != null ? printStream.equals(printStream2) : printStream2 == null;
            }

            public int hashCode() {
                PrintStream printStream = this.printStream;
                return 59 + (printStream == null ? 43 : printStream.hashCode());
            }

            public StreamWriting(PrintStream printStream) {
                this.printStream = printStream;
            }

            public static Listener toSystemOut() {
                return new StreamWriting(System.out);
            }

            public static Listener toSystemError() {
                return new StreamWriting(System.err);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                this.printStream.printf("[Byte Buddy] DISCOVERY %s [%s, %s, loaded=%b]%n", str, classLoader, javaModule, Boolean.valueOf(z));
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
                this.printStream.printf("[Byte Buddy] TRANSFORM %s [%s, %s, loaded=%b]%n", typeDescription.getName(), classLoader, javaModule, Boolean.valueOf(z));
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                this.printStream.printf("[Byte Buddy] IGNORE %s [%s, %s, loaded=%b]%n", typeDescription.getName(), classLoader, javaModule, Boolean.valueOf(z));
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
                synchronized (this.printStream) {
                    this.printStream.printf("[Byte Buddy] ERROR %s [%s, %s, loaded=%b]%n", str, classLoader, javaModule, Boolean.valueOf(z));
                    th.printStackTrace(this.printStream);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                this.printStream.printf("[Byte Buddy] COMPLETE %s [%s, %s, loaded=%b]%n", str, classLoader, javaModule, Boolean.valueOf(z));
            }
        }

        public static class Filtering implements Listener {
            private final Listener delegate;
            private final ElementMatcher<? super String> matcher;

            protected boolean canEqual(Object obj) {
                return obj instanceof Filtering;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Filtering)) {
                    return false;
                }
                Filtering filtering = (Filtering) obj;
                if (!filtering.canEqual(this)) {
                    return false;
                }
                ElementMatcher<? super String> elementMatcher = this.matcher;
                ElementMatcher<? super String> elementMatcher2 = filtering.matcher;
                if (elementMatcher != null ? !elementMatcher.equals(elementMatcher2) : elementMatcher2 != null) {
                    return false;
                }
                Listener listener = this.delegate;
                Listener listener2 = filtering.delegate;
                return listener != null ? listener.equals(listener2) : listener2 == null;
            }

            public int hashCode() {
                ElementMatcher<? super String> elementMatcher = this.matcher;
                int iHashCode = elementMatcher == null ? 43 : elementMatcher.hashCode();
                Listener listener = this.delegate;
                return ((iHashCode + 59) * 59) + (listener != null ? listener.hashCode() : 43);
            }

            public Filtering(ElementMatcher<? super String> elementMatcher, Listener listener) {
                this.matcher = elementMatcher;
                this.delegate = listener;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                if (this.matcher.matches(str)) {
                    this.delegate.onDiscovery(str, classLoader, javaModule, z);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
                if (this.matcher.matches(typeDescription.getName())) {
                    this.delegate.onTransformation(typeDescription, classLoader, javaModule, z, dynamicType);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                if (this.matcher.matches(typeDescription.getName())) {
                    this.delegate.onIgnored(typeDescription, classLoader, javaModule, z);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
                if (this.matcher.matches(str)) {
                    this.delegate.onError(str, classLoader, javaModule, z, th);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                if (this.matcher.matches(str)) {
                    this.delegate.onComplete(str, classLoader, javaModule, z);
                }
            }
        }

        public static class ModuleReadEdgeCompleting extends Adapter {
            private final boolean addTargetEdge;
            private final Instrumentation instrumentation;
            private final Set<? extends JavaModule> modules;

            protected boolean canEqual(Object obj) {
                return obj instanceof ModuleReadEdgeCompleting;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ModuleReadEdgeCompleting)) {
                    return false;
                }
                ModuleReadEdgeCompleting moduleReadEdgeCompleting = (ModuleReadEdgeCompleting) obj;
                if (!moduleReadEdgeCompleting.canEqual(this)) {
                    return false;
                }
                Instrumentation instrumentation = this.instrumentation;
                Instrumentation instrumentation2 = moduleReadEdgeCompleting.instrumentation;
                if (instrumentation != null ? !instrumentation.equals(instrumentation2) : instrumentation2 != null) {
                    return false;
                }
                if (this.addTargetEdge != moduleReadEdgeCompleting.addTargetEdge) {
                    return false;
                }
                Set<? extends JavaModule> set = this.modules;
                Set<? extends JavaModule> set2 = moduleReadEdgeCompleting.modules;
                return set != null ? set.equals(set2) : set2 == null;
            }

            public int hashCode() {
                Instrumentation instrumentation = this.instrumentation;
                int iHashCode = (((instrumentation == null ? 43 : instrumentation.hashCode()) + 59) * 59) + (this.addTargetEdge ? 79 : 97);
                Set<? extends JavaModule> set = this.modules;
                return (iHashCode * 59) + (set != null ? set.hashCode() : 43);
            }

            public ModuleReadEdgeCompleting(Instrumentation instrumentation, boolean z, Set<? extends JavaModule> set) {
                this.instrumentation = instrumentation;
                this.addTargetEdge = z;
                this.modules = set;
            }

            protected static Listener of(Instrumentation instrumentation, boolean z, Class<?>... clsArr) {
                HashSet hashSet = new HashSet();
                for (Class<?> cls : clsArr) {
                    JavaModule javaModuleOfType = JavaModule.ofType(cls);
                    if (javaModuleOfType.isNamed()) {
                        hashSet.add(javaModuleOfType);
                    }
                }
                return hashSet.isEmpty() ? NoOp.INSTANCE : new ModuleReadEdgeCompleting(instrumentation, z, hashSet);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener.Adapter, net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
                if (javaModule == JavaModule.UNSUPPORTED || !javaModule.isNamed()) {
                    return;
                }
                for (JavaModule javaModule2 : this.modules) {
                    if (!javaModule.canRead(javaModule2)) {
                        javaModule.addReads(this.instrumentation, javaModule2);
                    }
                    if (this.addTargetEdge && !javaModule2.canRead(javaModule)) {
                        javaModule2.addReads(this.instrumentation, javaModule);
                    }
                }
            }
        }

        public static class Compound implements Listener {
            private final List<Listener> listeners;

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
                List<Listener> list = this.listeners;
                List<Listener> list2 = compound.listeners;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<Listener> list = this.listeners;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            public Compound(Listener... listenerArr) {
                this((List<? extends Listener>) Arrays.asList(listenerArr));
            }

            public Compound(List<? extends Listener> list) {
                this.listeners = new ArrayList();
                for (Listener listener : list) {
                    if (listener instanceof Compound) {
                        this.listeners.addAll(((Compound) listener).listeners);
                    } else if (!(listener instanceof NoOp)) {
                        this.listeners.add(listener);
                    }
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                Iterator<Listener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onDiscovery(str, classLoader, javaModule, z);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
                Iterator<Listener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onTransformation(typeDescription, classLoader, javaModule, z, dynamicType);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                Iterator<Listener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onIgnored(typeDescription, classLoader, javaModule, z);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
                Iterator<Listener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onError(str, classLoader, javaModule, z, th);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener
            public void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                Iterator<Listener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onComplete(str, classLoader, javaModule, z);
                }
            }
        }
    }

    public interface CircularityLock {

        public enum Inactive implements CircularityLock {
            INSTANCE;

            @Override // net.bytebuddy.agent.builder.AgentBuilder.CircularityLock
            public boolean acquire() {
                return true;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.CircularityLock
            public void release() {
            }
        }

        boolean acquire();

        void release();

        public static class Default extends ThreadLocal<Boolean> implements CircularityLock {
            private static final Boolean NOT_ACQUIRED = null;

            @Override // net.bytebuddy.agent.builder.AgentBuilder.CircularityLock
            public boolean acquire() {
                if (get() != NOT_ACQUIRED) {
                    return false;
                }
                set(true);
                return true;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.CircularityLock
            public void release() {
                set(NOT_ACQUIRED);
            }
        }

        public static class Global implements CircularityLock {
            private final Lock lock;
            private final long time;
            private final TimeUnit timeUnit;

            protected boolean canEqual(Object obj) {
                return obj instanceof Global;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Global)) {
                    return false;
                }
                Global global = (Global) obj;
                if (!global.canEqual(this)) {
                    return false;
                }
                Lock lock = this.lock;
                Lock lock2 = global.lock;
                if (lock != null ? !lock.equals(lock2) : lock2 != null) {
                    return false;
                }
                if (this.time != global.time) {
                    return false;
                }
                TimeUnit timeUnit = this.timeUnit;
                TimeUnit timeUnit2 = global.timeUnit;
                return timeUnit != null ? timeUnit.equals(timeUnit2) : timeUnit2 == null;
            }

            public int hashCode() {
                Lock lock = this.lock;
                int iHashCode = lock == null ? 43 : lock.hashCode();
                long j = this.time;
                int i = ((iHashCode + 59) * 59) + ((int) (j ^ (j >>> 32)));
                TimeUnit timeUnit = this.timeUnit;
                return (i * 59) + (timeUnit != null ? timeUnit.hashCode() : 43);
            }

            public Global() {
                this(0L, TimeUnit.MILLISECONDS);
            }

            public Global(long j, TimeUnit timeUnit) {
                this.lock = new ReentrantLock();
                this.time = j;
                this.timeUnit = timeUnit;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.CircularityLock
            public boolean acquire() {
                try {
                    long j = this.time;
                    if (j == 0) {
                        return this.lock.tryLock();
                    }
                    return this.lock.tryLock(j, this.timeUnit);
                } catch (InterruptedException unused) {
                    return false;
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.CircularityLock
            public void release() {
                this.lock.unlock();
            }
        }
    }

    public interface TypeStrategy {
        DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer);

        public enum Default implements TypeStrategy {
            REBASE { // from class: net.bytebuddy.agent.builder.AgentBuilder.TypeStrategy.Default.1
                @Override // net.bytebuddy.agent.builder.AgentBuilder.TypeStrategy
                public DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                    return byteBuddy.rebase(typeDescription, classFileLocator, methodNameTransformer);
                }
            },
            REDEFINE { // from class: net.bytebuddy.agent.builder.AgentBuilder.TypeStrategy.Default.2
                @Override // net.bytebuddy.agent.builder.AgentBuilder.TypeStrategy
                public DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                    return byteBuddy.redefine(typeDescription, classFileLocator);
                }
            },
            REDEFINE_FROZEN { // from class: net.bytebuddy.agent.builder.AgentBuilder.TypeStrategy.Default.3
                @Override // net.bytebuddy.agent.builder.AgentBuilder.TypeStrategy
                public DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                    return byteBuddy.with(InstrumentedType.Factory.Default.FROZEN).redefine(typeDescription, classFileLocator).ignoreAlso(LatentMatcher.ForSelfDeclaredMethod.NOT_DECLARED);
                }
            };

            /* synthetic */ Default(AnonymousClass1 anonymousClass1) {
                this();
            }
        }

        public static class ForBuildEntryPoint implements TypeStrategy {
            private final EntryPoint entryPoint;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForBuildEntryPoint;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForBuildEntryPoint)) {
                    return false;
                }
                ForBuildEntryPoint forBuildEntryPoint = (ForBuildEntryPoint) obj;
                if (!forBuildEntryPoint.canEqual(this)) {
                    return false;
                }
                EntryPoint entryPoint = this.entryPoint;
                EntryPoint entryPoint2 = forBuildEntryPoint.entryPoint;
                return entryPoint != null ? entryPoint.equals(entryPoint2) : entryPoint2 == null;
            }

            public int hashCode() {
                EntryPoint entryPoint = this.entryPoint;
                return 59 + (entryPoint == null ? 43 : entryPoint.hashCode());
            }

            public ForBuildEntryPoint(EntryPoint entryPoint) {
                this.entryPoint = entryPoint;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.TypeStrategy
            public DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
                return this.entryPoint.transform(typeDescription, byteBuddy, classFileLocator, methodNameTransformer);
            }
        }
    }

    public interface Transformer {

        public enum NoOp implements Transformer {
            INSTANCE;

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                return builder;
            }
        }

        DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule);

        public static class ForBuildPlugin implements Transformer {
            private final Plugin plugin;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForBuildPlugin;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForBuildPlugin)) {
                    return false;
                }
                ForBuildPlugin forBuildPlugin = (ForBuildPlugin) obj;
                if (!forBuildPlugin.canEqual(this)) {
                    return false;
                }
                Plugin plugin = this.plugin;
                Plugin plugin2 = forBuildPlugin.plugin;
                return plugin != null ? plugin.equals(plugin2) : plugin2 == null;
            }

            public int hashCode() {
                Plugin plugin = this.plugin;
                return 59 + (plugin == null ? 43 : plugin.hashCode());
            }

            public ForBuildPlugin(Plugin plugin) {
                this.plugin = plugin;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                return this.plugin.apply(builder, typeDescription);
            }
        }

        public static class ForAdvice implements Transformer {
            private final Advice.WithCustomMapping advice;
            private final Assigner assigner;
            private final ClassFileLocator classFileLocator;
            private final List<Entry> entries;
            private final StackManipulation exceptionHandler;
            private final LocationStrategy locationStrategy;
            private final PoolStrategy poolStrategy;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForAdvice;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForAdvice)) {
                    return false;
                }
                ForAdvice forAdvice = (ForAdvice) obj;
                if (!forAdvice.canEqual(this)) {
                    return false;
                }
                Advice.WithCustomMapping withCustomMapping = this.advice;
                Advice.WithCustomMapping withCustomMapping2 = forAdvice.advice;
                if (withCustomMapping != null ? !withCustomMapping.equals(withCustomMapping2) : withCustomMapping2 != null) {
                    return false;
                }
                StackManipulation stackManipulation = this.exceptionHandler;
                StackManipulation stackManipulation2 = forAdvice.exceptionHandler;
                if (stackManipulation != null ? !stackManipulation.equals(stackManipulation2) : stackManipulation2 != null) {
                    return false;
                }
                Assigner assigner = this.assigner;
                Assigner assigner2 = forAdvice.assigner;
                if (assigner != null ? !assigner.equals(assigner2) : assigner2 != null) {
                    return false;
                }
                ClassFileLocator classFileLocator = this.classFileLocator;
                ClassFileLocator classFileLocator2 = forAdvice.classFileLocator;
                if (classFileLocator != null ? !classFileLocator.equals(classFileLocator2) : classFileLocator2 != null) {
                    return false;
                }
                PoolStrategy poolStrategy = this.poolStrategy;
                PoolStrategy poolStrategy2 = forAdvice.poolStrategy;
                if (poolStrategy != null ? !poolStrategy.equals(poolStrategy2) : poolStrategy2 != null) {
                    return false;
                }
                LocationStrategy locationStrategy = this.locationStrategy;
                LocationStrategy locationStrategy2 = forAdvice.locationStrategy;
                if (locationStrategy != null ? !locationStrategy.equals(locationStrategy2) : locationStrategy2 != null) {
                    return false;
                }
                List<Entry> list = this.entries;
                List<Entry> list2 = forAdvice.entries;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                Advice.WithCustomMapping withCustomMapping = this.advice;
                int iHashCode = withCustomMapping == null ? 43 : withCustomMapping.hashCode();
                StackManipulation stackManipulation = this.exceptionHandler;
                int iHashCode2 = ((iHashCode + 59) * 59) + (stackManipulation == null ? 43 : stackManipulation.hashCode());
                Assigner assigner = this.assigner;
                int iHashCode3 = (iHashCode2 * 59) + (assigner == null ? 43 : assigner.hashCode());
                ClassFileLocator classFileLocator = this.classFileLocator;
                int iHashCode4 = (iHashCode3 * 59) + (classFileLocator == null ? 43 : classFileLocator.hashCode());
                PoolStrategy poolStrategy = this.poolStrategy;
                int iHashCode5 = (iHashCode4 * 59) + (poolStrategy == null ? 43 : poolStrategy.hashCode());
                LocationStrategy locationStrategy = this.locationStrategy;
                int iHashCode6 = (iHashCode5 * 59) + (locationStrategy == null ? 43 : locationStrategy.hashCode());
                List<Entry> list = this.entries;
                return (iHashCode6 * 59) + (list != null ? list.hashCode() : 43);
            }

            public ForAdvice() {
                this(Advice.withCustomMapping());
            }

            public ForAdvice(Advice.WithCustomMapping withCustomMapping) {
                this(withCustomMapping, Removal.of(TypeDescription.THROWABLE), Assigner.DEFAULT, ClassFileLocator.NoOp.INSTANCE, PoolStrategy.Default.FAST, LocationStrategy.ForClassLoader.STRONG, Collections.emptyList());
            }

            protected ForAdvice(Advice.WithCustomMapping withCustomMapping, StackManipulation stackManipulation, Assigner assigner, ClassFileLocator classFileLocator, PoolStrategy poolStrategy, LocationStrategy locationStrategy, List<Entry> list) {
                this.advice = withCustomMapping;
                this.exceptionHandler = stackManipulation;
                this.assigner = assigner;
                this.classFileLocator = classFileLocator;
                this.poolStrategy = poolStrategy;
                this.locationStrategy = locationStrategy;
                this.entries = list;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                ClassFileLocator.Compound compound = new ClassFileLocator.Compound(this.locationStrategy.classFileLocator(classLoader, javaModule), this.classFileLocator);
                TypePool typePool = this.poolStrategy.typePool(compound, classLoader);
                AsmVisitorWrapper.ForDeclaredMethods forDeclaredMethods = new AsmVisitorWrapper.ForDeclaredMethods();
                for (Entry entry : this.entries) {
                    forDeclaredMethods = forDeclaredMethods.method((ElementMatcher<? super MethodDescription>) entry.getMatcher().resolve(typeDescription), entry.resolve(this.advice, typePool, compound).withAssigner(this.assigner).withExceptionHandler(this.exceptionHandler));
                }
                return builder.visit(forDeclaredMethods);
            }

            public ForAdvice with(PoolStrategy poolStrategy) {
                return new ForAdvice(this.advice, this.exceptionHandler, this.assigner, this.classFileLocator, poolStrategy, this.locationStrategy, this.entries);
            }

            public ForAdvice with(LocationStrategy locationStrategy) {
                return new ForAdvice(this.advice, this.exceptionHandler, this.assigner, this.classFileLocator, this.poolStrategy, locationStrategy, this.entries);
            }

            public ForAdvice withExceptionHandler(StackManipulation stackManipulation) {
                return new ForAdvice(this.advice, stackManipulation, this.assigner, this.classFileLocator, this.poolStrategy, this.locationStrategy, this.entries);
            }

            public ForAdvice with(Assigner assigner) {
                return new ForAdvice(this.advice, this.exceptionHandler, assigner, this.classFileLocator, this.poolStrategy, this.locationStrategy, this.entries);
            }

            public ForAdvice include(ClassLoader... classLoaderArr) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (ClassLoader classLoader : classLoaderArr) {
                    linkedHashSet.add(ClassFileLocator.ForClassLoader.of(classLoader));
                }
                return include(new ArrayList(linkedHashSet));
            }

            public ForAdvice include(ClassFileLocator... classFileLocatorArr) {
                return include(Arrays.asList(classFileLocatorArr));
            }

            public ForAdvice include(List<? extends ClassFileLocator> list) {
                return new ForAdvice(this.advice, this.exceptionHandler, this.assigner, new ClassFileLocator.Compound((List<? extends ClassFileLocator>) CompoundList.of(this.classFileLocator, list)), this.poolStrategy, this.locationStrategy, this.entries);
            }

            public ForAdvice advice(ElementMatcher<? super MethodDescription> elementMatcher, String str) {
                return advice(new LatentMatcher.Resolved(elementMatcher), str);
            }

            public ForAdvice advice(LatentMatcher<? super MethodDescription> latentMatcher, String str) {
                return new ForAdvice(this.advice, this.exceptionHandler, this.assigner, this.classFileLocator, this.poolStrategy, this.locationStrategy, CompoundList.of(this.entries, new Entry.ForUnifiedAdvice(latentMatcher, str)));
            }

            public ForAdvice advice(ElementMatcher<? super MethodDescription> elementMatcher, String str, String str2) {
                return advice(new LatentMatcher.Resolved(elementMatcher), str, str2);
            }

            public ForAdvice advice(LatentMatcher<? super MethodDescription> latentMatcher, String str, String str2) {
                return new ForAdvice(this.advice, this.exceptionHandler, this.assigner, this.classFileLocator, this.poolStrategy, this.locationStrategy, CompoundList.of(this.entries, new Entry.ForSplitAdvice(latentMatcher, str, str2)));
            }

            protected static abstract class Entry {
                private final LatentMatcher<? super MethodDescription> matcher;

                protected abstract Advice resolve(Advice.WithCustomMapping withCustomMapping, TypePool typePool, ClassFileLocator classFileLocator);

                protected boolean canEqual(Object obj) {
                    return obj instanceof Entry;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Entry)) {
                        return false;
                    }
                    Entry entry = (Entry) obj;
                    if (!entry.canEqual(this)) {
                        return false;
                    }
                    LatentMatcher<? super MethodDescription> matcher = getMatcher();
                    LatentMatcher<? super MethodDescription> matcher2 = entry.getMatcher();
                    return matcher != null ? matcher.equals(matcher2) : matcher2 == null;
                }

                public int hashCode() {
                    LatentMatcher<? super MethodDescription> matcher = getMatcher();
                    return 59 + (matcher == null ? 43 : matcher.hashCode());
                }

                protected Entry(LatentMatcher<? super MethodDescription> latentMatcher) {
                    this.matcher = latentMatcher;
                }

                protected LatentMatcher<? super MethodDescription> getMatcher() {
                    return this.matcher;
                }

                protected static class ForUnifiedAdvice extends Entry {
                    protected final String name;

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer.ForAdvice.Entry
                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForUnifiedAdvice;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer.ForAdvice.Entry
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForUnifiedAdvice)) {
                            return false;
                        }
                        ForUnifiedAdvice forUnifiedAdvice = (ForUnifiedAdvice) obj;
                        if (!forUnifiedAdvice.canEqual(this) || !super.equals(obj)) {
                            return false;
                        }
                        String str = this.name;
                        String str2 = forUnifiedAdvice.name;
                        return str != null ? str.equals(str2) : str2 == null;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer.ForAdvice.Entry
                    public int hashCode() {
                        int iHashCode = super.hashCode() + 59;
                        String str = this.name;
                        return (iHashCode * 59) + (str == null ? 43 : str.hashCode());
                    }

                    protected ForUnifiedAdvice(LatentMatcher<? super MethodDescription> latentMatcher, String str) {
                        super(latentMatcher);
                        this.name = str;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer.ForAdvice.Entry
                    protected Advice resolve(Advice.WithCustomMapping withCustomMapping, TypePool typePool, ClassFileLocator classFileLocator) {
                        return withCustomMapping.to(typePool.describe(this.name).resolve(), classFileLocator);
                    }
                }

                protected static class ForSplitAdvice extends Entry {
                    private final String enter;
                    private final String exit;

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer.ForAdvice.Entry
                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForSplitAdvice;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer.ForAdvice.Entry
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForSplitAdvice)) {
                            return false;
                        }
                        ForSplitAdvice forSplitAdvice = (ForSplitAdvice) obj;
                        if (!forSplitAdvice.canEqual(this) || !super.equals(obj)) {
                            return false;
                        }
                        String str = this.enter;
                        String str2 = forSplitAdvice.enter;
                        if (str != null ? !str.equals(str2) : str2 != null) {
                            return false;
                        }
                        String str3 = this.exit;
                        String str4 = forSplitAdvice.exit;
                        return str3 != null ? str3.equals(str4) : str4 == null;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer.ForAdvice.Entry
                    public int hashCode() {
                        int iHashCode = super.hashCode() + 59;
                        String str = this.enter;
                        int iHashCode2 = (iHashCode * 59) + (str == null ? 43 : str.hashCode());
                        String str2 = this.exit;
                        return (iHashCode2 * 59) + (str2 != null ? str2.hashCode() : 43);
                    }

                    protected ForSplitAdvice(LatentMatcher<? super MethodDescription> latentMatcher, String str, String str2) {
                        super(latentMatcher);
                        this.enter = str;
                        this.exit = str2;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer.ForAdvice.Entry
                    protected Advice resolve(Advice.WithCustomMapping withCustomMapping, TypePool typePool, ClassFileLocator classFileLocator) {
                        return withCustomMapping.to(typePool.describe(this.enter).resolve(), typePool.describe(this.exit).resolve(), classFileLocator);
                    }
                }
            }
        }

        public static class Compound implements Transformer {
            private final List<Transformer> transformers;

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
                List<Transformer> list = this.transformers;
                List<Transformer> list2 = compound.transformers;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<Transformer> list = this.transformers;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            public Compound(Transformer... transformerArr) {
                this((List<? extends Transformer>) Arrays.asList(transformerArr));
            }

            public Compound(List<? extends Transformer> list) {
                this.transformers = new ArrayList();
                for (Transformer transformer : list) {
                    if (transformer instanceof Compound) {
                        this.transformers.addAll(((Compound) transformer).transformers);
                    } else if (!(transformer instanceof NoOp)) {
                        this.transformers.add(transformer);
                    }
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Transformer
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                Iterator<Transformer> it = this.transformers.iterator();
                while (it.hasNext()) {
                    builder = it.next().transform(builder, typeDescription, classLoader, javaModule);
                }
                return builder;
            }
        }
    }

    public interface PoolStrategy {
        TypePool typePool(ClassFileLocator classFileLocator, ClassLoader classLoader);

        public enum Default implements PoolStrategy {
            EXTENDED(TypePool.Default.ReaderMode.EXTENDED),
            FAST(TypePool.Default.ReaderMode.FAST);

            private final TypePool.Default.ReaderMode readerMode;

            Default(TypePool.Default.ReaderMode readerMode) {
                this.readerMode = readerMode;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy
            public TypePool typePool(ClassFileLocator classFileLocator, ClassLoader classLoader) {
                return new TypePool.Default.WithLazyResolution(TypePool.CacheProvider.Simple.withObjectType(), classFileLocator, this.readerMode);
            }
        }

        public enum Eager implements PoolStrategy {
            EXTENDED(TypePool.Default.ReaderMode.EXTENDED),
            FAST(TypePool.Default.ReaderMode.FAST);

            private final TypePool.Default.ReaderMode readerMode;

            Eager(TypePool.Default.ReaderMode readerMode) {
                this.readerMode = readerMode;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy
            public TypePool typePool(ClassFileLocator classFileLocator, ClassLoader classLoader) {
                return new TypePool.Default(TypePool.CacheProvider.Simple.withObjectType(), classFileLocator, this.readerMode);
            }
        }

        public enum ClassLoading implements PoolStrategy {
            EXTENDED(TypePool.Default.ReaderMode.EXTENDED),
            FAST(TypePool.Default.ReaderMode.FAST);

            private final TypePool.Default.ReaderMode readerMode;

            ClassLoading(TypePool.Default.ReaderMode readerMode) {
                this.readerMode = readerMode;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy
            public TypePool typePool(ClassFileLocator classFileLocator, ClassLoader classLoader) {
                return TypePool.ClassLoading.of(classLoader, new TypePool.Default.WithLazyResolution(TypePool.CacheProvider.Simple.withObjectType(), classFileLocator, this.readerMode));
            }
        }

        public static abstract class WithTypePoolCache implements PoolStrategy {
            protected final TypePool.Default.ReaderMode readerMode;

            protected abstract TypePool.CacheProvider locate(ClassLoader classLoader);

            protected boolean canEqual(Object obj) {
                return obj instanceof WithTypePoolCache;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WithTypePoolCache)) {
                    return false;
                }
                WithTypePoolCache withTypePoolCache = (WithTypePoolCache) obj;
                if (!withTypePoolCache.canEqual(this)) {
                    return false;
                }
                TypePool.Default.ReaderMode readerMode = this.readerMode;
                TypePool.Default.ReaderMode readerMode2 = withTypePoolCache.readerMode;
                return readerMode != null ? readerMode.equals(readerMode2) : readerMode2 == null;
            }

            public int hashCode() {
                TypePool.Default.ReaderMode readerMode = this.readerMode;
                return 59 + (readerMode == null ? 43 : readerMode.hashCode());
            }

            protected WithTypePoolCache(TypePool.Default.ReaderMode readerMode) {
                this.readerMode = readerMode;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy
            public TypePool typePool(ClassFileLocator classFileLocator, ClassLoader classLoader) {
                return new TypePool.Default.WithLazyResolution(locate(classLoader), classFileLocator, this.readerMode);
            }

            public static class Simple extends WithTypePoolCache {
                private final ConcurrentMap<? super ClassLoader, TypePool.CacheProvider> cacheProviders;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.WithTypePoolCache
                protected boolean canEqual(Object obj) {
                    return obj instanceof Simple;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.WithTypePoolCache
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Simple)) {
                        return false;
                    }
                    Simple simple = (Simple) obj;
                    if (!simple.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    ConcurrentMap<? super ClassLoader, TypePool.CacheProvider> concurrentMap = this.cacheProviders;
                    ConcurrentMap<? super ClassLoader, TypePool.CacheProvider> concurrentMap2 = simple.cacheProviders;
                    return concurrentMap != null ? concurrentMap.equals(concurrentMap2) : concurrentMap2 == null;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.WithTypePoolCache
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    ConcurrentMap<? super ClassLoader, TypePool.CacheProvider> concurrentMap = this.cacheProviders;
                    return (iHashCode * 59) + (concurrentMap == null ? 43 : concurrentMap.hashCode());
                }

                public Simple(ConcurrentMap<? super ClassLoader, TypePool.CacheProvider> concurrentMap) {
                    this(TypePool.Default.ReaderMode.FAST, concurrentMap);
                }

                public Simple(TypePool.Default.ReaderMode readerMode, ConcurrentMap<? super ClassLoader, TypePool.CacheProvider> concurrentMap) {
                    super(readerMode);
                    this.cacheProviders = concurrentMap;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.WithTypePoolCache
                protected TypePool.CacheProvider locate(ClassLoader classLoader) {
                    if (classLoader == null) {
                        classLoader = getBootstrapMarkerLoader();
                    }
                    TypePool.CacheProvider cacheProviderWithObjectType = this.cacheProviders.get(classLoader);
                    while (cacheProviderWithObjectType == null) {
                        cacheProviderWithObjectType = TypePool.CacheProvider.Simple.withObjectType();
                        TypePool.CacheProvider cacheProviderPutIfAbsent = this.cacheProviders.putIfAbsent(classLoader, cacheProviderWithObjectType);
                        if (cacheProviderPutIfAbsent != null) {
                            cacheProviderWithObjectType = cacheProviderPutIfAbsent;
                        }
                    }
                    return cacheProviderWithObjectType;
                }

                protected ClassLoader getBootstrapMarkerLoader() {
                    return ClassLoader.getSystemClassLoader();
                }
            }
        }
    }

    public interface InitializationStrategy {

        public interface Dispatcher {

            public interface InjectorFactory {
                ClassInjector resolve();
            }

            DynamicType.Builder<?> apply(DynamicType.Builder<?> builder);

            void register(DynamicType dynamicType, ClassLoader classLoader, InjectorFactory injectorFactory);
        }

        public enum NoOp implements InitializationStrategy, Dispatcher {
            INSTANCE;

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher
            public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder) {
                return builder;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy
            public Dispatcher dispatcher() {
                return this;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher
            public void register(DynamicType dynamicType, ClassLoader classLoader, Dispatcher.InjectorFactory injectorFactory) {
            }
        }

        Dispatcher dispatcher();

        public enum Minimal implements InitializationStrategy, Dispatcher {
            INSTANCE;

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher
            public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder) {
                return builder;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy
            public Dispatcher dispatcher() {
                return this;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher
            public void register(DynamicType dynamicType, ClassLoader classLoader, Dispatcher.InjectorFactory injectorFactory) {
                Map<TypeDescription, byte[]> auxiliaryTypes = dynamicType.getAuxiliaryTypes();
                Map<? extends TypeDescription, byte[]> linkedHashMap = new LinkedHashMap<>(auxiliaryTypes);
                for (TypeDescription typeDescription : auxiliaryTypes.keySet()) {
                    if (!typeDescription.getDeclaredAnnotations().isAnnotationPresent(AuxiliaryType.SignatureRelevant.class)) {
                        linkedHashMap.remove(typeDescription);
                    }
                }
                if (linkedHashMap.isEmpty()) {
                    return;
                }
                ClassInjector classInjectorResolve = injectorFactory.resolve();
                Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers = dynamicType.getLoadedTypeInitializers();
                for (Map.Entry<TypeDescription, Class<?>> entry : classInjectorResolve.inject(linkedHashMap).entrySet()) {
                    loadedTypeInitializers.get(entry.getKey()).onLoad(entry.getValue());
                }
            }
        }

        public static abstract class SelfInjection implements InitializationStrategy {
            protected final NexusAccessor nexusAccessor;

            protected abstract Dispatcher dispatcher(int i);

            protected boolean canEqual(Object obj) {
                return obj instanceof SelfInjection;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SelfInjection)) {
                    return false;
                }
                SelfInjection selfInjection = (SelfInjection) obj;
                if (!selfInjection.canEqual(this)) {
                    return false;
                }
                NexusAccessor nexusAccessor = this.nexusAccessor;
                NexusAccessor nexusAccessor2 = selfInjection.nexusAccessor;
                return nexusAccessor != null ? nexusAccessor.equals(nexusAccessor2) : nexusAccessor2 == null;
            }

            public int hashCode() {
                NexusAccessor nexusAccessor = this.nexusAccessor;
                return 59 + (nexusAccessor == null ? 43 : nexusAccessor.hashCode());
            }

            protected SelfInjection(NexusAccessor nexusAccessor) {
                this.nexusAccessor = nexusAccessor;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy
            public Dispatcher dispatcher() {
                return dispatcher(new Random().nextInt());
            }

            protected static abstract class Dispatcher implements Dispatcher {
                protected final int identification;
                protected final NexusAccessor nexusAccessor;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Dispatcher;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Dispatcher)) {
                        return false;
                    }
                    Dispatcher dispatcher = (Dispatcher) obj;
                    if (!dispatcher.canEqual(this)) {
                        return false;
                    }
                    NexusAccessor nexusAccessor = this.nexusAccessor;
                    NexusAccessor nexusAccessor2 = dispatcher.nexusAccessor;
                    if (nexusAccessor != null ? nexusAccessor.equals(nexusAccessor2) : nexusAccessor2 == null) {
                        return this.identification == dispatcher.identification;
                    }
                    return false;
                }

                public int hashCode() {
                    NexusAccessor nexusAccessor = this.nexusAccessor;
                    return (((nexusAccessor == null ? 43 : nexusAccessor.hashCode()) + 59) * 59) + this.identification;
                }

                protected Dispatcher(NexusAccessor nexusAccessor, int i) {
                    this.nexusAccessor = nexusAccessor;
                    this.identification = i;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher
                public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder) {
                    return builder.initializer(new NexusAccessor.InitializationAppender(this.identification));
                }

                protected static class InjectingInitializer implements LoadedTypeInitializer {
                    private final ClassInjector classInjector;
                    private final TypeDescription instrumentedType;
                    private final Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers;
                    private final Map<TypeDescription, byte[]> rawAuxiliaryTypes;

                    @Override // net.bytebuddy.implementation.LoadedTypeInitializer
                    public boolean isAlive() {
                        return true;
                    }

                    protected boolean canEqual(Object obj) {
                        return obj instanceof InjectingInitializer;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof InjectingInitializer)) {
                            return false;
                        }
                        InjectingInitializer injectingInitializer = (InjectingInitializer) obj;
                        if (!injectingInitializer.canEqual(this)) {
                            return false;
                        }
                        TypeDescription typeDescription = this.instrumentedType;
                        TypeDescription typeDescription2 = injectingInitializer.instrumentedType;
                        if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                            return false;
                        }
                        Map<TypeDescription, byte[]> map = this.rawAuxiliaryTypes;
                        Map<TypeDescription, byte[]> map2 = injectingInitializer.rawAuxiliaryTypes;
                        if (map != null ? !map.equals(map2) : map2 != null) {
                            return false;
                        }
                        Map<TypeDescription, LoadedTypeInitializer> map3 = this.loadedTypeInitializers;
                        Map<TypeDescription, LoadedTypeInitializer> map4 = injectingInitializer.loadedTypeInitializers;
                        if (map3 != null ? !map3.equals(map4) : map4 != null) {
                            return false;
                        }
                        ClassInjector classInjector = this.classInjector;
                        ClassInjector classInjector2 = injectingInitializer.classInjector;
                        return classInjector != null ? classInjector.equals(classInjector2) : classInjector2 == null;
                    }

                    public int hashCode() {
                        TypeDescription typeDescription = this.instrumentedType;
                        int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                        Map<TypeDescription, byte[]> map = this.rawAuxiliaryTypes;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (map == null ? 43 : map.hashCode());
                        Map<TypeDescription, LoadedTypeInitializer> map2 = this.loadedTypeInitializers;
                        int iHashCode3 = (iHashCode2 * 59) + (map2 == null ? 43 : map2.hashCode());
                        ClassInjector classInjector = this.classInjector;
                        return (iHashCode3 * 59) + (classInjector != null ? classInjector.hashCode() : 43);
                    }

                    protected InjectingInitializer(TypeDescription typeDescription, Map<TypeDescription, byte[]> map, Map<TypeDescription, LoadedTypeInitializer> map2, ClassInjector classInjector) {
                        this.instrumentedType = typeDescription;
                        this.rawAuxiliaryTypes = map;
                        this.loadedTypeInitializers = map2;
                        this.classInjector = classInjector;
                    }

                    @Override // net.bytebuddy.implementation.LoadedTypeInitializer
                    public void onLoad(Class<?> cls) {
                        for (Map.Entry<TypeDescription, Class<?>> entry : this.classInjector.inject(this.rawAuxiliaryTypes).entrySet()) {
                            this.loadedTypeInitializers.get(entry.getKey()).onLoad(entry.getValue());
                        }
                        this.loadedTypeInitializers.get(this.instrumentedType).onLoad(cls);
                    }
                }
            }

            public static class Split extends SelfInjection {
                public Split() {
                    this(new NexusAccessor());
                }

                public Split(NexusAccessor nexusAccessor) {
                    super(nexusAccessor);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection
                protected Dispatcher dispatcher(int i) {
                    return new Dispatcher(this.nexusAccessor, i);
                }

                protected static class Dispatcher extends Dispatcher {
                    protected Dispatcher(NexusAccessor nexusAccessor, int i) {
                        super(nexusAccessor, i);
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher
                    public void register(DynamicType dynamicType, ClassLoader classLoader, Dispatcher.InjectorFactory injectorFactory) {
                        LoadedTypeInitializer injectingInitializer;
                        Map<TypeDescription, byte[]> auxiliaryTypes = dynamicType.getAuxiliaryTypes();
                        if (!auxiliaryTypes.isEmpty()) {
                            TypeDescription typeDescription = dynamicType.getTypeDescription();
                            ClassInjector classInjectorResolve = injectorFactory.resolve();
                            LinkedHashMap linkedHashMap = new LinkedHashMap(auxiliaryTypes);
                            LinkedHashMap linkedHashMap2 = new LinkedHashMap(auxiliaryTypes);
                            for (TypeDescription typeDescription2 : auxiliaryTypes.keySet()) {
                                (typeDescription2.getDeclaredAnnotations().isAnnotationPresent(AuxiliaryType.SignatureRelevant.class) ? linkedHashMap2 : linkedHashMap).remove(typeDescription2);
                            }
                            Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers = dynamicType.getLoadedTypeInitializers();
                            if (!linkedHashMap.isEmpty()) {
                                for (Map.Entry<TypeDescription, Class<?>> entry : classInjectorResolve.inject(linkedHashMap).entrySet()) {
                                    loadedTypeInitializers.get(entry.getKey()).onLoad(entry.getValue());
                                }
                            }
                            HashMap map = new HashMap(loadedTypeInitializers);
                            loadedTypeInitializers.keySet().removeAll(linkedHashMap.keySet());
                            injectingInitializer = map.size() > 1 ? new Dispatcher.InjectingInitializer(typeDescription, linkedHashMap2, map, classInjectorResolve) : (LoadedTypeInitializer) map.get(typeDescription);
                        } else {
                            injectingInitializer = dynamicType.getLoadedTypeInitializers().get(dynamicType.getTypeDescription());
                        }
                        this.nexusAccessor.register(dynamicType.getTypeDescription().getName(), classLoader, this.identification, injectingInitializer);
                    }
                }
            }

            public static class Lazy extends SelfInjection {
                public Lazy() {
                    this(new NexusAccessor());
                }

                public Lazy(NexusAccessor nexusAccessor) {
                    super(nexusAccessor);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection
                protected Dispatcher dispatcher(int i) {
                    return new Dispatcher(this.nexusAccessor, i);
                }

                protected static class Dispatcher extends Dispatcher {
                    protected Dispatcher(NexusAccessor nexusAccessor, int i) {
                        super(nexusAccessor, i);
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher
                    public void register(DynamicType dynamicType, ClassLoader classLoader, Dispatcher.InjectorFactory injectorFactory) {
                        LoadedTypeInitializer injectingInitializer;
                        Map<TypeDescription, byte[]> auxiliaryTypes = dynamicType.getAuxiliaryTypes();
                        if (auxiliaryTypes.isEmpty()) {
                            injectingInitializer = dynamicType.getLoadedTypeInitializers().get(dynamicType.getTypeDescription());
                        } else {
                            injectingInitializer = new Dispatcher.InjectingInitializer(dynamicType.getTypeDescription(), auxiliaryTypes, dynamicType.getLoadedTypeInitializers(), injectorFactory.resolve());
                        }
                        this.nexusAccessor.register(dynamicType.getTypeDescription().getName(), classLoader, this.identification, injectingInitializer);
                    }
                }
            }

            public static class Eager extends SelfInjection {
                public Eager() {
                    this(new NexusAccessor());
                }

                public Eager(NexusAccessor nexusAccessor) {
                    super(nexusAccessor);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection
                protected Dispatcher dispatcher(int i) {
                    return new Dispatcher(this.nexusAccessor, i);
                }

                protected static class Dispatcher extends Dispatcher {
                    protected Dispatcher(NexusAccessor nexusAccessor, int i) {
                        super(nexusAccessor, i);
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher
                    public void register(DynamicType dynamicType, ClassLoader classLoader, Dispatcher.InjectorFactory injectorFactory) {
                        Map<TypeDescription, byte[]> auxiliaryTypes = dynamicType.getAuxiliaryTypes();
                        Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers = dynamicType.getLoadedTypeInitializers();
                        if (!auxiliaryTypes.isEmpty()) {
                            for (Map.Entry<TypeDescription, Class<?>> entry : injectorFactory.resolve().inject(auxiliaryTypes).entrySet()) {
                                loadedTypeInitializers.get(entry.getKey()).onLoad(entry.getValue());
                            }
                        }
                        this.nexusAccessor.register(dynamicType.getTypeDescription().getName(), classLoader, this.identification, loadedTypeInitializers.get(dynamicType.getTypeDescription()));
                    }
                }
            }
        }
    }

    public interface DescriptionStrategy {
        TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule);

        boolean isLoadedFirst();

        public enum Default implements DescriptionStrategy {
            HYBRID(true) { // from class: net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy.Default.1
                @Override // net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy
                public TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule) {
                    return cls == null ? typePool.describe(str).resolve() : new TypeDescription.ForLoadedType(cls);
                }
            },
            POOL_ONLY(0 == true ? 1 : 0) { // from class: net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy.Default.2
                @Override // net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy
                public TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule) {
                    return typePool.describe(str).resolve();
                }
            },
            POOL_FIRST(0 == true ? 1 : 0) { // from class: net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy.Default.3
                @Override // net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy
                public TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule) {
                    TypePool.Resolution resolutionDescribe = typePool.describe(str);
                    return (resolutionDescribe.isResolved() || cls == null) ? resolutionDescribe.resolve() : new TypeDescription.ForLoadedType(cls);
                }
            };

            private final boolean loadedFirst;

            /* synthetic */ Default(boolean z, AnonymousClass1 anonymousClass1) {
                this(z);
            }

            Default(boolean z) {
                this.loadedFirst = z;
            }

            public DescriptionStrategy withSuperTypeLoading() {
                return new SuperTypeLoading(this);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy
            public boolean isLoadedFirst() {
                return this.loadedFirst;
            }

            public DescriptionStrategy withSuperTypeLoading(ExecutorService executorService) {
                return new SuperTypeLoading.Asynchronous(this, executorService);
            }
        }

        public static class SuperTypeLoading implements DescriptionStrategy {
            private final DescriptionStrategy delegate;

            protected boolean canEqual(Object obj) {
                return obj instanceof SuperTypeLoading;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SuperTypeLoading)) {
                    return false;
                }
                SuperTypeLoading superTypeLoading = (SuperTypeLoading) obj;
                if (!superTypeLoading.canEqual(this)) {
                    return false;
                }
                DescriptionStrategy descriptionStrategy = this.delegate;
                DescriptionStrategy descriptionStrategy2 = superTypeLoading.delegate;
                return descriptionStrategy != null ? descriptionStrategy.equals(descriptionStrategy2) : descriptionStrategy2 == null;
            }

            public int hashCode() {
                DescriptionStrategy descriptionStrategy = this.delegate;
                return 59 + (descriptionStrategy == null ? 43 : descriptionStrategy.hashCode());
            }

            public SuperTypeLoading(DescriptionStrategy descriptionStrategy) {
                this.delegate = descriptionStrategy;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy
            public boolean isLoadedFirst() {
                return this.delegate.isLoadedFirst();
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy
            public TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule) {
                TypeDescription typeDescriptionApply = this.delegate.apply(str, cls, typePool, circularityLock, classLoader, javaModule);
                return typeDescriptionApply instanceof TypeDescription.ForLoadedType ? typeDescriptionApply : new TypeDescription.SuperTypeLoading(typeDescriptionApply, classLoader, new UnlockingClassLoadingDelegate(circularityLock));
            }

            protected static class UnlockingClassLoadingDelegate implements TypeDescription.SuperTypeLoading.ClassLoadingDelegate {
                private final CircularityLock circularityLock;

                protected boolean canEqual(Object obj) {
                    return obj instanceof UnlockingClassLoadingDelegate;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof UnlockingClassLoadingDelegate)) {
                        return false;
                    }
                    UnlockingClassLoadingDelegate unlockingClassLoadingDelegate = (UnlockingClassLoadingDelegate) obj;
                    if (!unlockingClassLoadingDelegate.canEqual(this)) {
                        return false;
                    }
                    CircularityLock circularityLock = this.circularityLock;
                    CircularityLock circularityLock2 = unlockingClassLoadingDelegate.circularityLock;
                    return circularityLock != null ? circularityLock.equals(circularityLock2) : circularityLock2 == null;
                }

                public int hashCode() {
                    CircularityLock circularityLock = this.circularityLock;
                    return 59 + (circularityLock == null ? 43 : circularityLock.hashCode());
                }

                protected UnlockingClassLoadingDelegate(CircularityLock circularityLock) {
                    this.circularityLock = circularityLock;
                }

                @Override // net.bytebuddy.description.type.TypeDescription.SuperTypeLoading.ClassLoadingDelegate
                public Class<?> load(String str, ClassLoader classLoader) throws ClassNotFoundException {
                    this.circularityLock.release();
                    try {
                        return Class.forName(str, false, classLoader);
                    } finally {
                        this.circularityLock.acquire();
                    }
                }
            }

            public static class Asynchronous implements DescriptionStrategy {
                private final DescriptionStrategy delegate;
                private final ExecutorService executorService;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Asynchronous;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Asynchronous)) {
                        return false;
                    }
                    Asynchronous asynchronous = (Asynchronous) obj;
                    if (!asynchronous.canEqual(this)) {
                        return false;
                    }
                    DescriptionStrategy descriptionStrategy = this.delegate;
                    DescriptionStrategy descriptionStrategy2 = asynchronous.delegate;
                    if (descriptionStrategy != null ? !descriptionStrategy.equals(descriptionStrategy2) : descriptionStrategy2 != null) {
                        return false;
                    }
                    ExecutorService executorService = this.executorService;
                    ExecutorService executorService2 = asynchronous.executorService;
                    return executorService != null ? executorService.equals(executorService2) : executorService2 == null;
                }

                public int hashCode() {
                    DescriptionStrategy descriptionStrategy = this.delegate;
                    int iHashCode = descriptionStrategy == null ? 43 : descriptionStrategy.hashCode();
                    ExecutorService executorService = this.executorService;
                    return ((iHashCode + 59) * 59) + (executorService != null ? executorService.hashCode() : 43);
                }

                public Asynchronous(DescriptionStrategy descriptionStrategy, ExecutorService executorService) {
                    this.delegate = descriptionStrategy;
                    this.executorService = executorService;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy
                public boolean isLoadedFirst() {
                    return this.delegate.isLoadedFirst();
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy
                public TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule) {
                    TypeDescription typeDescriptionApply = this.delegate.apply(str, cls, typePool, circularityLock, classLoader, javaModule);
                    return typeDescriptionApply instanceof TypeDescription.ForLoadedType ? typeDescriptionApply : new TypeDescription.SuperTypeLoading(typeDescriptionApply, classLoader, new ThreadSwitchingClassLoadingDelegate(this.executorService));
                }

                protected static class ThreadSwitchingClassLoadingDelegate implements TypeDescription.SuperTypeLoading.ClassLoadingDelegate {
                    private final ExecutorService executorService;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ThreadSwitchingClassLoadingDelegate;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ThreadSwitchingClassLoadingDelegate)) {
                            return false;
                        }
                        ThreadSwitchingClassLoadingDelegate threadSwitchingClassLoadingDelegate = (ThreadSwitchingClassLoadingDelegate) obj;
                        if (!threadSwitchingClassLoadingDelegate.canEqual(this)) {
                            return false;
                        }
                        ExecutorService executorService = this.executorService;
                        ExecutorService executorService2 = threadSwitchingClassLoadingDelegate.executorService;
                        return executorService != null ? executorService.equals(executorService2) : executorService2 == null;
                    }

                    public int hashCode() {
                        ExecutorService executorService = this.executorService;
                        return 59 + (executorService == null ? 43 : executorService.hashCode());
                    }

                    protected ThreadSwitchingClassLoadingDelegate(ExecutorService executorService) {
                        this.executorService = executorService;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription.SuperTypeLoading.ClassLoadingDelegate
                    public Class<?> load(String str, ClassLoader classLoader) throws ClassNotFoundException {
                        boolean z = classLoader != null && Thread.holdsLock(classLoader);
                        AtomicBoolean atomicBoolean = new AtomicBoolean(z);
                        Future futureSubmit = this.executorService.submit(z ? new NotifyingClassLoadingAction(str, classLoader, atomicBoolean) : new SimpleClassLoadingAction(str, classLoader));
                        while (z) {
                            try {
                                if (!atomicBoolean.get()) {
                                    break;
                                }
                                classLoader.wait();
                            } catch (ExecutionException e) {
                                throw new IllegalStateException("Could not load " + str + " asynchronously", e.getCause());
                            } catch (Exception e2) {
                                throw new IllegalStateException("Could not load " + str + " asynchronously", e2);
                            }
                        }
                        return (Class) futureSubmit.get();
                    }

                    protected static class SimpleClassLoadingAction implements Callable<Class<?>> {
                        private final ClassLoader classLoader;
                        private final String name;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof SimpleClassLoadingAction;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof SimpleClassLoadingAction)) {
                                return false;
                            }
                            SimpleClassLoadingAction simpleClassLoadingAction = (SimpleClassLoadingAction) obj;
                            if (!simpleClassLoadingAction.canEqual(this)) {
                                return false;
                            }
                            String str = this.name;
                            String str2 = simpleClassLoadingAction.name;
                            if (str != null ? !str.equals(str2) : str2 != null) {
                                return false;
                            }
                            ClassLoader classLoader = this.classLoader;
                            ClassLoader classLoader2 = simpleClassLoadingAction.classLoader;
                            return classLoader != null ? classLoader.equals(classLoader2) : classLoader2 == null;
                        }

                        public int hashCode() {
                            String str = this.name;
                            int iHashCode = str == null ? 43 : str.hashCode();
                            ClassLoader classLoader = this.classLoader;
                            return ((iHashCode + 59) * 59) + (classLoader != null ? classLoader.hashCode() : 43);
                        }

                        protected SimpleClassLoadingAction(String str, ClassLoader classLoader) {
                            this.name = str;
                            this.classLoader = classLoader;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.concurrent.Callable
                        public Class<?> call() throws ClassNotFoundException {
                            return Class.forName(this.name, false, this.classLoader);
                        }
                    }

                    protected static class NotifyingClassLoadingAction implements Callable<Class<?>> {
                        private final ClassLoader classLoader;
                        private final String name;
                        private final AtomicBoolean signal;

                        protected NotifyingClassLoadingAction(String str, ClassLoader classLoader, AtomicBoolean atomicBoolean) {
                            this.name = str;
                            this.classLoader = classLoader;
                            this.signal = atomicBoolean;
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.concurrent.Callable
                        public Class<?> call() throws ClassNotFoundException {
                            Class<?> cls;
                            synchronized (this.classLoader) {
                                try {
                                    cls = Class.forName(this.name, false, this.classLoader);
                                } finally {
                                    this.signal.set(false);
                                    this.classLoader.notifyAll();
                                }
                            }
                            return cls;
                        }
                    }
                }
            }
        }
    }

    public interface LocationStrategy {
        ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule);

        public enum NoOp implements LocationStrategy {
            INSTANCE;

            @Override // net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy
            public ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule) {
                return ClassFileLocator.NoOp.INSTANCE;
            }
        }

        public enum ForClassLoader implements LocationStrategy {
            STRONG { // from class: net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy.ForClassLoader.1
                @Override // net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy
                public ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule) {
                    return ClassFileLocator.ForClassLoader.of(classLoader);
                }
            },
            WEAK { // from class: net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy.ForClassLoader.2
                @Override // net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy
                public ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule) {
                    return ClassFileLocator.ForClassLoader.WeaklyReferenced.of(classLoader);
                }
            };

            /* synthetic */ ForClassLoader(AnonymousClass1 anonymousClass1) {
                this();
            }

            public LocationStrategy withFallbackTo(ClassFileLocator... classFileLocatorArr) {
                return withFallbackTo((Collection<? extends ClassFileLocator>) Arrays.asList(classFileLocatorArr));
            }

            public LocationStrategy withFallbackTo(Collection<? extends ClassFileLocator> collection) {
                ArrayList arrayList = new ArrayList(collection.size());
                Iterator<? extends ClassFileLocator> it = collection.iterator();
                while (it.hasNext()) {
                    arrayList.add(new Simple(it.next()));
                }
                return withFallbackTo((List<? extends LocationStrategy>) arrayList);
            }

            public LocationStrategy withFallbackTo(LocationStrategy... locationStrategyArr) {
                return withFallbackTo(Arrays.asList(locationStrategyArr));
            }

            public LocationStrategy withFallbackTo(List<? extends LocationStrategy> list) {
                ArrayList arrayList = new ArrayList(list.size() + 1);
                arrayList.add(this);
                arrayList.addAll(list);
                return new Compound(arrayList);
            }
        }

        public static class Simple implements LocationStrategy {
            private final ClassFileLocator classFileLocator;

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
                ClassFileLocator classFileLocator = this.classFileLocator;
                ClassFileLocator classFileLocator2 = simple.classFileLocator;
                return classFileLocator != null ? classFileLocator.equals(classFileLocator2) : classFileLocator2 == null;
            }

            public int hashCode() {
                ClassFileLocator classFileLocator = this.classFileLocator;
                return 59 + (classFileLocator == null ? 43 : classFileLocator.hashCode());
            }

            public Simple(ClassFileLocator classFileLocator) {
                this.classFileLocator = classFileLocator;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy
            public ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule) {
                return this.classFileLocator;
            }
        }

        public static class Compound implements LocationStrategy {
            private final List<LocationStrategy> locationStrategies;

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
                List<LocationStrategy> list = this.locationStrategies;
                List<LocationStrategy> list2 = compound.locationStrategies;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<LocationStrategy> list = this.locationStrategies;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            public Compound(LocationStrategy... locationStrategyArr) {
                this((List<? extends LocationStrategy>) Arrays.asList(locationStrategyArr));
            }

            public Compound(List<? extends LocationStrategy> list) {
                this.locationStrategies = new ArrayList();
                for (LocationStrategy locationStrategy : list) {
                    if (locationStrategy instanceof Compound) {
                        this.locationStrategies.addAll(((Compound) locationStrategy).locationStrategies);
                    } else if (!(locationStrategy instanceof NoOp)) {
                        this.locationStrategies.add(locationStrategy);
                    }
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy
            public ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule) {
                ArrayList arrayList = new ArrayList(this.locationStrategies.size());
                Iterator<LocationStrategy> it = this.locationStrategies.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().classFileLocator(classLoader, javaModule));
                }
                return new ClassFileLocator.Compound(arrayList);
            }
        }
    }

    public interface FallbackStrategy {
        boolean isFallback(Class<?> cls, Throwable th);

        public enum Simple implements FallbackStrategy {
            ENABLED(true),
            DISABLED(false);

            private final boolean enabled;

            Simple(boolean z) {
                this.enabled = z;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy
            public boolean isFallback(Class<?> cls, Throwable th) {
                return this.enabled;
            }
        }

        public static class ByThrowableType implements FallbackStrategy {
            private final Set<? extends Class<? extends Throwable>> types;

            protected boolean canEqual(Object obj) {
                return obj instanceof ByThrowableType;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ByThrowableType)) {
                    return false;
                }
                ByThrowableType byThrowableType = (ByThrowableType) obj;
                if (!byThrowableType.canEqual(this)) {
                    return false;
                }
                Set<? extends Class<? extends Throwable>> set = this.types;
                Set<? extends Class<? extends Throwable>> set2 = byThrowableType.types;
                return set != null ? set.equals(set2) : set2 == null;
            }

            public int hashCode() {
                Set<? extends Class<? extends Throwable>> set = this.types;
                return 59 + (set == null ? 43 : set.hashCode());
            }

            public ByThrowableType(Class<? extends Throwable>... clsArr) {
                this(new HashSet(Arrays.asList(clsArr)));
            }

            public ByThrowableType(Set<? extends Class<? extends Throwable>> set) {
                this.types = set;
            }

            public static FallbackStrategy ofOptionalTypes() {
                return new ByThrowableType((Class<? extends Throwable>[]) new Class[]{LinkageError.class, TypeNotPresentException.class});
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy
            public boolean isFallback(Class<?> cls, Throwable th) {
                Iterator<? extends Class<? extends Throwable>> it = this.types.iterator();
                while (it.hasNext()) {
                    if (it.next().isInstance(th)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    public interface InstallationListener {
        public static final Throwable SUPPRESS_ERROR = null;

        public static abstract class Adapter implements InstallationListener {
            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th) {
                return th;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }
        }

        public enum NoOp implements InstallationListener {
            INSTANCE;

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th) {
                return th;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }
        }

        void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer);

        Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th);

        void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer);

        void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer);

        public enum ErrorSuppressing implements InstallationListener {
            INSTANCE;

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th) {
                return SUPPRESS_ERROR;
            }
        }

        public static class StreamWriting implements InstallationListener {
            protected static final String PREFIX = "[Byte Buddy]";
            private final PrintStream printStream;

            protected boolean canEqual(Object obj) {
                return obj instanceof StreamWriting;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof StreamWriting)) {
                    return false;
                }
                StreamWriting streamWriting = (StreamWriting) obj;
                if (!streamWriting.canEqual(this)) {
                    return false;
                }
                PrintStream printStream = this.printStream;
                PrintStream printStream2 = streamWriting.printStream;
                return printStream != null ? printStream.equals(printStream2) : printStream2 == null;
            }

            public int hashCode() {
                PrintStream printStream = this.printStream;
                return 59 + (printStream == null ? 43 : printStream.hashCode());
            }

            public StreamWriting(PrintStream printStream) {
                this.printStream = printStream;
            }

            public static InstallationListener toSystemOut() {
                return new StreamWriting(System.out);
            }

            public static InstallationListener toSystemError() {
                return new StreamWriting(System.err);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                this.printStream.printf("[Byte Buddy] BEFORE_INSTALL %s on %s%n", resettableClassFileTransformer, instrumentation);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                this.printStream.printf("[Byte Buddy] INSTALL %s on %s%n", resettableClassFileTransformer, instrumentation);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th) {
                synchronized (this.printStream) {
                    this.printStream.printf("[Byte Buddy] ERROR %s on %s%n", resettableClassFileTransformer, instrumentation);
                    th.printStackTrace(this.printStream);
                }
                return th;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                this.printStream.printf("[Byte Buddy] RESET %s on %s%n", resettableClassFileTransformer, instrumentation);
            }
        }

        public static class Compound implements InstallationListener {
            private final List<InstallationListener> installationListeners;

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
                List<InstallationListener> list = this.installationListeners;
                List<InstallationListener> list2 = compound.installationListeners;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<InstallationListener> list = this.installationListeners;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            public Compound(InstallationListener... installationListenerArr) {
                this((List<? extends InstallationListener>) Arrays.asList(installationListenerArr));
            }

            public Compound(List<? extends InstallationListener> list) {
                this.installationListeners = new ArrayList();
                for (InstallationListener installationListener : list) {
                    if (installationListener instanceof Compound) {
                        this.installationListeners.addAll(((Compound) installationListener).installationListeners);
                    } else if (!(installationListener instanceof NoOp)) {
                        this.installationListeners.add(installationListener);
                    }
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                Iterator<InstallationListener> it = this.installationListeners.iterator();
                while (it.hasNext()) {
                    it.next().onBeforeInstall(instrumentation, resettableClassFileTransformer);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                Iterator<InstallationListener> it = this.installationListeners.iterator();
                while (it.hasNext()) {
                    it.next().onInstall(instrumentation, resettableClassFileTransformer);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th) {
                for (InstallationListener installationListener : this.installationListeners) {
                    if (th == SUPPRESS_ERROR) {
                        return SUPPRESS_ERROR;
                    }
                    th = installationListener.onError(instrumentation, resettableClassFileTransformer, th);
                }
                return th;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
            public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                Iterator<InstallationListener> it = this.installationListeners.iterator();
                while (it.hasNext()) {
                    it.next().onReset(instrumentation, resettableClassFileTransformer);
                }
            }
        }
    }

    public enum RedefinitionStrategy {
        DISABLED(0 == true ? 1 : 0, 0 == true ? 1 : 0) { // from class: net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.1
            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy
            public void apply(Instrumentation instrumentation, Listener listener, CircularityLock circularityLock, PoolStrategy poolStrategy, LocationStrategy locationStrategy, DiscoveryStrategy discoveryStrategy, BatchAllocator batchAllocator, Listener listener2, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, RawMatcher rawMatcher, RawMatcher rawMatcher2) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy
            protected void check(Instrumentation instrumentation) {
                throw new IllegalStateException("Cannot apply redefinition on disabled strategy");
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy
            protected Collector make() {
                throw new IllegalStateException("A disabled redefinition strategy cannot create a collector");
            }
        },
        REDEFINITION(true ? 1 : 0, 0 == true ? 1 : 0) { // from class: net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.2
            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy
            protected void check(Instrumentation instrumentation) {
                if (!instrumentation.isRedefineClassesSupported()) {
                    throw new IllegalStateException("Cannot apply redefinition on " + instrumentation);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy
            protected Collector make() {
                return new Collector.ForRedefinition();
            }
        },
        RETRANSFORMATION(true ? 1 : 0, true ? 1 : 0) { // from class: net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.3
            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy
            protected void check(Instrumentation instrumentation) {
                if (!instrumentation.isRetransformClassesSupported()) {
                    throw new IllegalStateException("Cannot apply redefinition on " + instrumentation);
                }
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy
            protected Collector make() {
                return new Collector.ForRetransformation();
            }
        };

        private final boolean enabled;
        private final boolean retransforming;

        protected abstract void check(Instrumentation instrumentation);

        protected abstract Collector make();

        /* synthetic */ RedefinitionStrategy(boolean z, boolean z2, AnonymousClass1 anonymousClass1) {
            this(z, z2);
        }

        RedefinitionStrategy(boolean z, boolean z2) {
            this.enabled = z;
            this.retransforming = z2;
        }

        protected boolean isRetransforming() {
            return this.retransforming;
        }

        protected boolean isEnabled() {
            return this.enabled;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00a2  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00cc  */
        /* JADX WARN: Type inference failed for: r11v0 */
        /* JADX WARN: Type inference failed for: r11v1, types: [net.bytebuddy.utility.JavaModule] */
        /* JADX WARN: Type inference failed for: r11v3 */
        /* JADX WARN: Type inference failed for: r11v4 */
        /* JADX WARN: Type inference failed for: r11v5 */
        /* JADX WARN: Type inference failed for: r11v6, types: [net.bytebuddy.utility.JavaModule] */
        /* JADX WARN: Type inference failed for: r13v0 */
        /* JADX WARN: Type inference failed for: r13v1, types: [net.bytebuddy.utility.JavaModule] */
        /* JADX WARN: Type inference failed for: r13v2 */
        /* JADX WARN: Type inference failed for: r13v3 */
        /* JADX WARN: Type inference failed for: r13v4, types: [net.bytebuddy.agent.builder.AgentBuilder$Listener] */
        /* JADX WARN: Type inference failed for: r13v5, types: [net.bytebuddy.agent.builder.AgentBuilder$Listener] */
        /* JADX WARN: Type inference failed for: r13v6 */
        /* JADX WARN: Type inference failed for: r13v7 */
        /* JADX WARN: Type inference failed for: r13v8 */
        /* JADX WARN: Type inference failed for: r13v9, types: [net.bytebuddy.agent.builder.AgentBuilder$Listener] */
        /* JADX WARN: Type inference failed for: r22v0, types: [net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$Collector] */
        /* JADX WARN: Type inference failed for: r26v0, types: [net.bytebuddy.agent.builder.AgentBuilder$Listener] */
        /* JADX WARN: Type inference failed for: r29v0, types: [net.bytebuddy.agent.builder.AgentBuilder$LocationStrategy] */
        /* JADX WARN: Type inference failed for: r34v0, types: [net.bytebuddy.agent.builder.AgentBuilder$DescriptionStrategy] */
        /* JADX WARN: Type inference failed for: r4v1, types: [net.bytebuddy.utility.JavaModule] */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v3 */
        /* JADX WARN: Type inference failed for: r6v6, types: [net.bytebuddy.utility.JavaModule] */
        /* JADX WARN: Type inference failed for: r8v0 */
        /* JADX WARN: Type inference failed for: r8v1 */
        /* JADX WARN: Type inference failed for: r8v16 */
        /* JADX WARN: Type inference failed for: r8v2 */
        /* JADX WARN: Type inference failed for: r8v4 */
        /* JADX WARN: Type inference failed for: r8v7 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void apply(java.lang.instrument.Instrumentation r25, net.bytebuddy.agent.builder.AgentBuilder.Listener r26, net.bytebuddy.agent.builder.AgentBuilder.CircularityLock r27, net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy r28, net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy r29, net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy r30, net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator r31, net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener r32, net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy r33, net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy r34, net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy r35, net.bytebuddy.agent.builder.AgentBuilder.RawMatcher r36, net.bytebuddy.agent.builder.AgentBuilder.RawMatcher r37) {
            /*
                Method dump skipped, instruction units count: 304
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.apply(java.lang.instrument.Instrumentation, net.bytebuddy.agent.builder.AgentBuilder$Listener, net.bytebuddy.agent.builder.AgentBuilder$CircularityLock, net.bytebuddy.agent.builder.AgentBuilder$PoolStrategy, net.bytebuddy.agent.builder.AgentBuilder$LocationStrategy, net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$DiscoveryStrategy, net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$BatchAllocator, net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$Listener, net.bytebuddy.agent.builder.AgentBuilder$LambdaInstrumentationStrategy, net.bytebuddy.agent.builder.AgentBuilder$DescriptionStrategy, net.bytebuddy.agent.builder.AgentBuilder$FallbackStrategy, net.bytebuddy.agent.builder.AgentBuilder$RawMatcher, net.bytebuddy.agent.builder.AgentBuilder$RawMatcher):void");
        }

        public interface BatchAllocator {
            public static final int FIRST_BATCH = 0;

            Iterable<? extends List<Class<?>>> batch(List<Class<?>> list);

            public enum ForTotal implements BatchAllocator {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator
                public Iterable<? extends List<Class<?>>> batch(List<Class<?>> list) {
                    if (list.isEmpty()) {
                        return Collections.emptySet();
                    }
                    return Collections.singleton(list);
                }
            }

            public static class ForFixedSize implements BatchAllocator {
                private final int size;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForFixedSize;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForFixedSize)) {
                        return false;
                    }
                    ForFixedSize forFixedSize = (ForFixedSize) obj;
                    return forFixedSize.canEqual(this) && this.size == forFixedSize.size;
                }

                public int hashCode() {
                    return 59 + this.size;
                }

                protected ForFixedSize(int i) {
                    this.size = i;
                }

                public static BatchAllocator ofSize(int i) {
                    if (i > 0) {
                        return new ForFixedSize(i);
                    }
                    if (i == 0) {
                        return ForTotal.INSTANCE;
                    }
                    throw new IllegalArgumentException("Cannot define a batch with a negative size: " + i);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator
                public Iterable<? extends List<Class<?>>> batch(List<Class<?>> list) {
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    while (i < list.size()) {
                        arrayList.add(new ArrayList(list.subList(i, Math.min(list.size(), this.size + i))));
                        i += this.size;
                    }
                    return arrayList;
                }
            }

            public static class ForMatchedGrouping implements BatchAllocator {
                private final Collection<? extends ElementMatcher<? super TypeDescription>> matchers;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForMatchedGrouping;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForMatchedGrouping)) {
                        return false;
                    }
                    ForMatchedGrouping forMatchedGrouping = (ForMatchedGrouping) obj;
                    if (!forMatchedGrouping.canEqual(this)) {
                        return false;
                    }
                    Collection<? extends ElementMatcher<? super TypeDescription>> collection = this.matchers;
                    Collection<? extends ElementMatcher<? super TypeDescription>> collection2 = forMatchedGrouping.matchers;
                    return collection != null ? collection.equals(collection2) : collection2 == null;
                }

                public int hashCode() {
                    Collection<? extends ElementMatcher<? super TypeDescription>> collection = this.matchers;
                    return 59 + (collection == null ? 43 : collection.hashCode());
                }

                public ForMatchedGrouping(ElementMatcher<? super TypeDescription>... elementMatcherArr) {
                    this(new LinkedHashSet(Arrays.asList(elementMatcherArr)));
                }

                public ForMatchedGrouping(Collection<? extends ElementMatcher<? super TypeDescription>> collection) {
                    this.matchers = collection;
                }

                public BatchAllocator withMinimum(int i) {
                    return Slicing.withMinimum(i, this);
                }

                public BatchAllocator withMaximum(int i) {
                    return Slicing.withMaximum(i, this);
                }

                public BatchAllocator withinRange(int i, int i2) {
                    return Slicing.withinRange(i, i2, this);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator
                public Iterable<? extends List<Class<?>>> batch(List<Class<?>> list) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    ArrayList arrayList = new ArrayList();
                    Iterator<? extends ElementMatcher<? super TypeDescription>> it = this.matchers.iterator();
                    while (it.hasNext()) {
                        linkedHashMap.put(it.next(), new ArrayList());
                    }
                    for (Class<?> cls : list) {
                        Iterator<? extends ElementMatcher<? super TypeDescription>> it2 = this.matchers.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                ElementMatcher<? super TypeDescription> next = it2.next();
                                if (next.matches(new TypeDescription.ForLoadedType(cls))) {
                                    ((List) linkedHashMap.get(next)).add(cls);
                                    break;
                                }
                            } else {
                                arrayList.add(cls);
                                break;
                            }
                        }
                    }
                    ArrayList arrayList2 = new ArrayList(this.matchers.size() + 1);
                    for (List list2 : linkedHashMap.values()) {
                        if (!list2.isEmpty()) {
                            arrayList2.add(list2);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        arrayList2.add(arrayList);
                    }
                    return arrayList2;
                }
            }

            public static class Slicing implements BatchAllocator {
                private final BatchAllocator batchAllocator;
                private final int maximum;
                private final int minimum;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Slicing;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Slicing)) {
                        return false;
                    }
                    Slicing slicing = (Slicing) obj;
                    if (!slicing.canEqual(this) || this.minimum != slicing.minimum || this.maximum != slicing.maximum) {
                        return false;
                    }
                    BatchAllocator batchAllocator = this.batchAllocator;
                    BatchAllocator batchAllocator2 = slicing.batchAllocator;
                    return batchAllocator != null ? batchAllocator.equals(batchAllocator2) : batchAllocator2 == null;
                }

                public int hashCode() {
                    int i = ((this.minimum + 59) * 59) + this.maximum;
                    BatchAllocator batchAllocator = this.batchAllocator;
                    return (i * 59) + (batchAllocator == null ? 43 : batchAllocator.hashCode());
                }

                protected Slicing(int i, int i2, BatchAllocator batchAllocator) {
                    this.minimum = i;
                    this.maximum = i2;
                    this.batchAllocator = batchAllocator;
                }

                public static BatchAllocator withMinimum(int i, BatchAllocator batchAllocator) {
                    return withinRange(i, Integer.MAX_VALUE, batchAllocator);
                }

                public static BatchAllocator withMaximum(int i, BatchAllocator batchAllocator) {
                    return withinRange(1, i, batchAllocator);
                }

                public static BatchAllocator withinRange(int i, int i2, BatchAllocator batchAllocator) {
                    if (i <= 0) {
                        throw new IllegalArgumentException("Minimum must be a positive number: " + i);
                    }
                    if (i > i2) {
                        throw new IllegalArgumentException("Minimum must not be bigger than maximum: " + i + " >" + i2);
                    }
                    return new Slicing(i, i2, batchAllocator);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator
                public Iterable<? extends List<Class<?>>> batch(List<Class<?>> list) {
                    return new SlicingIterable(this.minimum, this.maximum, this.batchAllocator.batch(list));
                }

                protected static class SlicingIterable implements Iterable<List<Class<?>>> {
                    private final Iterable<? extends List<Class<?>>> iterable;
                    private final int maximum;
                    private final int minimum;

                    protected SlicingIterable(int i, int i2, Iterable<? extends List<Class<?>>> iterable) {
                        this.minimum = i;
                        this.maximum = i2;
                        this.iterable = iterable;
                    }

                    @Override // java.lang.Iterable
                    public Iterator<List<Class<?>>> iterator() {
                        return new SlicingIterator(this.minimum, this.maximum, this.iterable.iterator());
                    }

                    protected static class SlicingIterator implements Iterator<List<Class<?>>> {
                        private List<Class<?>> buffer = new ArrayList();
                        private final Iterator<? extends List<Class<?>>> iterator;
                        private final int maximum;
                        private final int minimum;

                        protected SlicingIterator(int i, int i2, Iterator<? extends List<Class<?>>> it) {
                            this.minimum = i;
                            this.maximum = i2;
                            this.iterator = it;
                        }

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            return !this.buffer.isEmpty() || this.iterator.hasNext();
                        }

                        @Override // java.util.Iterator
                        public List<Class<?>> next() {
                            if (this.buffer.isEmpty()) {
                                this.buffer = this.iterator.next();
                            }
                            while (this.buffer.size() < this.minimum && this.iterator.hasNext()) {
                                this.buffer.addAll(this.iterator.next());
                            }
                            int size = this.buffer.size();
                            int i = this.maximum;
                            if (size > i) {
                                try {
                                    return this.buffer.subList(0, i);
                                } finally {
                                    List<Class<?>> list = this.buffer;
                                    this.buffer = new ArrayList(list.subList(this.maximum, list.size()));
                                }
                            }
                            try {
                                return this.buffer;
                            } finally {
                                this.buffer = new ArrayList();
                            }
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            throw new UnsupportedOperationException("remove");
                        }
                    }
                }
            }

            public static class Partitioning implements BatchAllocator {
                private final int parts;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Partitioning;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Partitioning)) {
                        return false;
                    }
                    Partitioning partitioning = (Partitioning) obj;
                    return partitioning.canEqual(this) && this.parts == partitioning.parts;
                }

                public int hashCode() {
                    return 59 + this.parts;
                }

                protected Partitioning(int i) {
                    this.parts = i;
                }

                public static BatchAllocator of(int i) {
                    if (i < 1) {
                        throw new IllegalArgumentException("A batch size must be positive: " + i);
                    }
                    return new Partitioning(i);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator
                public Iterable<? extends List<Class<?>>> batch(List<Class<?>> list) {
                    if (list.isEmpty()) {
                        return Collections.emptyList();
                    }
                    ArrayList arrayList = new ArrayList();
                    int size = list.size() / this.parts;
                    int size2 = list.size() % this.parts;
                    int i = size2;
                    while (i < list.size()) {
                        int i2 = i + size;
                        arrayList.add(new ArrayList(list.subList(i, i2)));
                        i = i2;
                    }
                    if (arrayList.isEmpty()) {
                        return Collections.singletonList(list);
                    }
                    ((List) arrayList.get(0)).addAll(0, list.subList(0, size2));
                    return arrayList;
                }
            }
        }

        public interface Listener {
            void onBatch(int i, List<Class<?>> list, List<Class<?>> list2);

            void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map);

            Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2);

            public enum NoOp implements Listener {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    return Collections.emptyList();
                }
            }

            public enum Yielding implements Listener {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                    if (i > 0) {
                        Thread.yield();
                    }
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    return Collections.emptyList();
                }
            }

            public enum ErrorEscalating implements Listener {
                FAIL_FAST { // from class: net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.ErrorEscalating.1
                    @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                    public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                    public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                        throw new IllegalStateException("Could not transform any of " + list, th);
                    }
                },
                FAIL_LAST { // from class: net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.ErrorEscalating.2
                    @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                    public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                        return Collections.emptyList();
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                    public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                        if (!map.isEmpty()) {
                            throw new IllegalStateException("Could not transform any of " + map);
                        }
                    }
                };

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                }

                /* synthetic */ ErrorEscalating(AnonymousClass1 anonymousClass1) {
                    this();
                }
            }

            public static abstract class Adapter implements Listener {
                public int hashCode() {
                    return 1;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof Adapter;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    return (obj instanceof Adapter) && ((Adapter) obj).canEqual(this);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    return Collections.emptyList();
                }
            }

            public static class BatchReallocator extends Adapter {
                private final BatchAllocator batchAllocator;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.Adapter
                protected boolean canEqual(Object obj) {
                    return obj instanceof BatchReallocator;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.Adapter
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof BatchReallocator)) {
                        return false;
                    }
                    BatchReallocator batchReallocator = (BatchReallocator) obj;
                    if (!batchReallocator.canEqual(this)) {
                        return false;
                    }
                    BatchAllocator batchAllocator = this.batchAllocator;
                    BatchAllocator batchAllocator2 = batchReallocator.batchAllocator;
                    return batchAllocator != null ? batchAllocator.equals(batchAllocator2) : batchAllocator2 == null;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.Adapter
                public int hashCode() {
                    BatchAllocator batchAllocator = this.batchAllocator;
                    return 59 + (batchAllocator == null ? 43 : batchAllocator.hashCode());
                }

                public BatchReallocator(BatchAllocator batchAllocator) {
                    this.batchAllocator = batchAllocator;
                }

                public static Listener splitting() {
                    return new BatchReallocator(new BatchAllocator.Partitioning(2));
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.Adapter, net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    if (list.size() < 2) {
                        return Collections.emptyList();
                    }
                    return this.batchAllocator.batch(list);
                }
            }

            public static class Pausing extends Adapter {
                private final long value;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.Adapter
                protected boolean canEqual(Object obj) {
                    return obj instanceof Pausing;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.Adapter
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Pausing)) {
                        return false;
                    }
                    Pausing pausing = (Pausing) obj;
                    return pausing.canEqual(this) && this.value == pausing.value;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.Adapter
                public int hashCode() {
                    long j = this.value;
                    return 59 + ((int) (j ^ (j >>> 32)));
                }

                protected Pausing(long j) {
                    this.value = j;
                }

                public static Listener of(long j, TimeUnit timeUnit) {
                    if (j > 0) {
                        return new Pausing(timeUnit.toMillis(j));
                    }
                    if (j == 0) {
                        return NoOp.INSTANCE;
                    }
                    throw new IllegalArgumentException("Cannot sleep for a non-positive amount of time: " + j);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.Adapter, net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                    if (i > 0) {
                        try {
                            Thread.sleep(this.value);
                        } catch (InterruptedException e) {
                            throw new RuntimeException("Sleep was interrupted", e);
                        }
                    }
                }
            }

            public static class StreamWriting implements Listener {
                private final PrintStream printStream;

                protected boolean canEqual(Object obj) {
                    return obj instanceof StreamWriting;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof StreamWriting)) {
                        return false;
                    }
                    StreamWriting streamWriting = (StreamWriting) obj;
                    if (!streamWriting.canEqual(this)) {
                        return false;
                    }
                    PrintStream printStream = this.printStream;
                    PrintStream printStream2 = streamWriting.printStream;
                    return printStream != null ? printStream.equals(printStream2) : printStream2 == null;
                }

                public int hashCode() {
                    PrintStream printStream = this.printStream;
                    return 59 + (printStream == null ? 43 : printStream.hashCode());
                }

                public StreamWriting(PrintStream printStream) {
                    this.printStream = printStream;
                }

                public static Listener toSystemOut() {
                    return new StreamWriting(System.out);
                }

                public static Listener toSystemError() {
                    return new StreamWriting(System.err);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                    this.printStream.printf("[Byte Buddy] REDEFINE BATCH #%d [%d of %d type(s)]%n", Integer.valueOf(i), Integer.valueOf(list.size()), Integer.valueOf(list2.size()));
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    synchronized (this.printStream) {
                        this.printStream.printf("[Byte Buddy] REDEFINE ERROR #%d [%d of %d type(s)]%n", Integer.valueOf(i), Integer.valueOf(list.size()), Integer.valueOf(list2.size()));
                        th.printStackTrace(this.printStream);
                    }
                    return Collections.emptyList();
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                    this.printStream.printf("[Byte Buddy] REDEFINE COMPLETE #%d batch(es) containing %d types [%d failed batch(es)]%n", Integer.valueOf(i), Integer.valueOf(list.size()), Integer.valueOf(map.size()));
                }
            }

            public static class Compound implements Listener {
                private final List<Listener> listeners;

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
                    List<Listener> list = this.listeners;
                    List<Listener> list2 = compound.listeners;
                    return list != null ? list.equals(list2) : list2 == null;
                }

                public int hashCode() {
                    List<Listener> list = this.listeners;
                    return 59 + (list == null ? 43 : list.hashCode());
                }

                public Compound(Listener... listenerArr) {
                    this((List<? extends Listener>) Arrays.asList(listenerArr));
                }

                public Compound(List<? extends Listener> list) {
                    this.listeners = new ArrayList();
                    for (Listener listener : list) {
                        if (listener instanceof Compound) {
                            this.listeners.addAll(((Compound) listener).listeners);
                        } else if (!(listener instanceof NoOp)) {
                            this.listeners.add(listener);
                        }
                    }
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onBatch(i, list, list2);
                    }
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().onError(i, list, th, list2));
                    }
                    return new CompoundIterable(arrayList);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener
                public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                    Iterator<Listener> it = this.listeners.iterator();
                    while (it.hasNext()) {
                        it.next().onComplete(i, list, map);
                    }
                }

                protected static class CompoundIterable implements Iterable<List<Class<?>>> {
                    private final List<Iterable<? extends List<Class<?>>>> iterables;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof CompoundIterable;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof CompoundIterable)) {
                            return false;
                        }
                        CompoundIterable compoundIterable = (CompoundIterable) obj;
                        if (!compoundIterable.canEqual(this)) {
                            return false;
                        }
                        List<Iterable<? extends List<Class<?>>>> list = this.iterables;
                        List<Iterable<? extends List<Class<?>>>> list2 = compoundIterable.iterables;
                        return list != null ? list.equals(list2) : list2 == null;
                    }

                    public int hashCode() {
                        List<Iterable<? extends List<Class<?>>>> list = this.iterables;
                        return 59 + (list == null ? 43 : list.hashCode());
                    }

                    protected CompoundIterable(List<Iterable<? extends List<Class<?>>>> list) {
                        this.iterables = list;
                    }

                    @Override // java.lang.Iterable
                    public Iterator<List<Class<?>>> iterator() {
                        return new CompoundIterator(new ArrayList(this.iterables));
                    }

                    protected static class CompoundIterator implements Iterator<List<Class<?>>> {
                        private final List<Iterable<? extends List<Class<?>>>> backlog;
                        private Iterator<? extends List<Class<?>>> current;

                        protected CompoundIterator(List<Iterable<? extends List<Class<?>>>> list) {
                            this.backlog = list;
                            forward();
                        }

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            Iterator<? extends List<Class<?>>> it = this.current;
                            return it != null && it.hasNext();
                        }

                        @Override // java.util.Iterator
                        public List<Class<?>> next() {
                            try {
                                Iterator<? extends List<Class<?>>> it = this.current;
                                if (it != null) {
                                    return it.next();
                                }
                                throw new NoSuchElementException();
                            } finally {
                                forward();
                            }
                        }

                        private void forward() {
                            while (true) {
                                Iterator<? extends List<Class<?>>> it = this.current;
                                if ((it != null && it.hasNext()) || this.backlog.isEmpty()) {
                                    return;
                                } else {
                                    this.current = this.backlog.remove(0).iterator();
                                }
                            }
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            throw new UnsupportedOperationException("remove");
                        }
                    }
                }
            }
        }

        public interface DiscoveryStrategy {
            Iterable<Iterable<Class<?>>> resolve(Instrumentation instrumentation);

            public enum SinglePass implements DiscoveryStrategy {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy
                public Iterable<Iterable<Class<?>>> resolve(Instrumentation instrumentation) {
                    return Collections.singleton(Arrays.asList(instrumentation.getAllLoadedClasses()));
                }
            }

            public enum Reiterating implements DiscoveryStrategy {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy
                public Iterable<Iterable<Class<?>>> resolve(Instrumentation instrumentation) {
                    return new ReiteratingIterable(instrumentation);
                }

                protected static class ReiteratingIterable implements Iterable<Iterable<Class<?>>> {
                    private final Instrumentation instrumentation;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ReiteratingIterable;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ReiteratingIterable)) {
                            return false;
                        }
                        ReiteratingIterable reiteratingIterable = (ReiteratingIterable) obj;
                        if (!reiteratingIterable.canEqual(this)) {
                            return false;
                        }
                        Instrumentation instrumentation = this.instrumentation;
                        Instrumentation instrumentation2 = reiteratingIterable.instrumentation;
                        return instrumentation != null ? instrumentation.equals(instrumentation2) : instrumentation2 == null;
                    }

                    public int hashCode() {
                        Instrumentation instrumentation = this.instrumentation;
                        return 59 + (instrumentation == null ? 43 : instrumentation.hashCode());
                    }

                    protected ReiteratingIterable(Instrumentation instrumentation) {
                        this.instrumentation = instrumentation;
                    }

                    @Override // java.lang.Iterable
                    public Iterator<Iterable<Class<?>>> iterator() {
                        return new ReiteratingIterator(this.instrumentation);
                    }
                }

                protected static class ReiteratingIterator implements Iterator<Iterable<Class<?>>> {
                    private final Instrumentation instrumentation;
                    private final Set<Class<?>> processed = new HashSet();
                    private List<Class<?>> types;

                    protected ReiteratingIterator(Instrumentation instrumentation) {
                        this.instrumentation = instrumentation;
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        if (this.types == null) {
                            this.types = new ArrayList();
                            for (Class<?> cls : this.instrumentation.getAllLoadedClasses()) {
                                if (this.processed.add(cls)) {
                                    this.types.add(cls);
                                }
                            }
                        }
                        return !this.types.isEmpty();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public Iterable<Class<?>> next() {
                        if (hasNext()) {
                            try {
                                return this.types;
                            } finally {
                                this.types = null;
                            }
                        }
                        throw new NoSuchElementException();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException("remove");
                    }
                }
            }

            public static class Explicit implements DiscoveryStrategy {
                private final Set<Class<?>> types;

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
                    Set<Class<?>> set = this.types;
                    Set<Class<?>> set2 = explicit.types;
                    return set != null ? set.equals(set2) : set2 == null;
                }

                public int hashCode() {
                    Set<Class<?>> set = this.types;
                    return 59 + (set == null ? 43 : set.hashCode());
                }

                public Explicit(Class<?>... clsArr) {
                    this(new LinkedHashSet(Arrays.asList(clsArr)));
                }

                public Explicit(Set<Class<?>> set) {
                    this.types = set;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy
                public Iterable<Iterable<Class<?>>> resolve(Instrumentation instrumentation) {
                    return Collections.singleton(this.types);
                }
            }
        }

        public interface ResubmissionScheduler {
            boolean isAlive();

            Cancelable schedule(Runnable runnable);

            public interface Cancelable {

                public enum NoOp implements Cancelable {
                    INSTANCE;

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler.Cancelable
                    public void cancel() {
                    }
                }

                void cancel();

                public static class ForFuture implements Cancelable {
                    private final Future<?> future;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForFuture;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForFuture)) {
                            return false;
                        }
                        ForFuture forFuture = (ForFuture) obj;
                        if (!forFuture.canEqual(this)) {
                            return false;
                        }
                        Future<?> future = this.future;
                        Future<?> future2 = forFuture.future;
                        return future != null ? future.equals(future2) : future2 == null;
                    }

                    public int hashCode() {
                        Future<?> future = this.future;
                        return 59 + (future == null ? 43 : future.hashCode());
                    }

                    public ForFuture(Future<?> future) {
                        this.future = future;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler.Cancelable
                    public void cancel() {
                        this.future.cancel(true);
                    }
                }
            }

            public enum NoOp implements ResubmissionScheduler {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler
                public boolean isAlive() {
                    return false;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler
                public Cancelable schedule(Runnable runnable) {
                    return Cancelable.NoOp.INSTANCE;
                }
            }

            public static class AtFixedRate implements ResubmissionScheduler {
                private final ScheduledExecutorService scheduledExecutorService;
                private final long time;
                private final TimeUnit timeUnit;

                protected boolean canEqual(Object obj) {
                    return obj instanceof AtFixedRate;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof AtFixedRate)) {
                        return false;
                    }
                    AtFixedRate atFixedRate = (AtFixedRate) obj;
                    if (!atFixedRate.canEqual(this)) {
                        return false;
                    }
                    ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
                    ScheduledExecutorService scheduledExecutorService2 = atFixedRate.scheduledExecutorService;
                    if (scheduledExecutorService != null ? !scheduledExecutorService.equals(scheduledExecutorService2) : scheduledExecutorService2 != null) {
                        return false;
                    }
                    if (this.time != atFixedRate.time) {
                        return false;
                    }
                    TimeUnit timeUnit = this.timeUnit;
                    TimeUnit timeUnit2 = atFixedRate.timeUnit;
                    return timeUnit != null ? timeUnit.equals(timeUnit2) : timeUnit2 == null;
                }

                public int hashCode() {
                    ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
                    int iHashCode = scheduledExecutorService == null ? 43 : scheduledExecutorService.hashCode();
                    long j = this.time;
                    int i = ((iHashCode + 59) * 59) + ((int) (j ^ (j >>> 32)));
                    TimeUnit timeUnit = this.timeUnit;
                    return (i * 59) + (timeUnit != null ? timeUnit.hashCode() : 43);
                }

                public AtFixedRate(ScheduledExecutorService scheduledExecutorService, long j, TimeUnit timeUnit) {
                    this.scheduledExecutorService = scheduledExecutorService;
                    this.time = j;
                    this.timeUnit = timeUnit;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler
                public boolean isAlive() {
                    return !this.scheduledExecutorService.isShutdown();
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler
                public Cancelable schedule(Runnable runnable) {
                    ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
                    long j = this.time;
                    return new Cancelable.ForFuture(scheduledExecutorService.scheduleAtFixedRate(runnable, j, j, this.timeUnit));
                }
            }

            public static class WithFixedDelay implements ResubmissionScheduler {
                private final ScheduledExecutorService scheduledExecutorService;
                private final long time;
                private final TimeUnit timeUnit;

                protected boolean canEqual(Object obj) {
                    return obj instanceof WithFixedDelay;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof WithFixedDelay)) {
                        return false;
                    }
                    WithFixedDelay withFixedDelay = (WithFixedDelay) obj;
                    if (!withFixedDelay.canEqual(this)) {
                        return false;
                    }
                    ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
                    ScheduledExecutorService scheduledExecutorService2 = withFixedDelay.scheduledExecutorService;
                    if (scheduledExecutorService != null ? !scheduledExecutorService.equals(scheduledExecutorService2) : scheduledExecutorService2 != null) {
                        return false;
                    }
                    if (this.time != withFixedDelay.time) {
                        return false;
                    }
                    TimeUnit timeUnit = this.timeUnit;
                    TimeUnit timeUnit2 = withFixedDelay.timeUnit;
                    return timeUnit != null ? timeUnit.equals(timeUnit2) : timeUnit2 == null;
                }

                public int hashCode() {
                    ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
                    int iHashCode = scheduledExecutorService == null ? 43 : scheduledExecutorService.hashCode();
                    long j = this.time;
                    int i = ((iHashCode + 59) * 59) + ((int) (j ^ (j >>> 32)));
                    TimeUnit timeUnit = this.timeUnit;
                    return (i * 59) + (timeUnit != null ? timeUnit.hashCode() : 43);
                }

                public WithFixedDelay(ScheduledExecutorService scheduledExecutorService, long j, TimeUnit timeUnit) {
                    this.scheduledExecutorService = scheduledExecutorService;
                    this.time = j;
                    this.timeUnit = timeUnit;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler
                public boolean isAlive() {
                    return !this.scheduledExecutorService.isShutdown();
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler
                public Cancelable schedule(Runnable runnable) {
                    ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
                    long j = this.time;
                    return new Cancelable.ForFuture(scheduledExecutorService.scheduleWithFixedDelay(runnable, j, j, this.timeUnit));
                }
            }
        }

        protected interface ResubmissionStrategy {
            Installation apply(Instrumentation instrumentation, LocationStrategy locationStrategy, Listener listener, InstallationListener installationListener, CircularityLock circularityLock, RawMatcher rawMatcher, RedefinitionStrategy redefinitionStrategy, BatchAllocator batchAllocator, Listener listener2);

            public enum Disabled implements ResubmissionStrategy {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionStrategy
                public Installation apply(Instrumentation instrumentation, LocationStrategy locationStrategy, Listener listener, InstallationListener installationListener, CircularityLock circularityLock, RawMatcher rawMatcher, RedefinitionStrategy redefinitionStrategy, BatchAllocator batchAllocator, Listener listener2) {
                    return new Installation(listener, installationListener);
                }
            }

            public static class Enabled implements ResubmissionStrategy {
                private final ElementMatcher<? super Throwable> matcher;
                private final ResubmissionScheduler resubmissionScheduler;

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
                    ResubmissionScheduler resubmissionScheduler = this.resubmissionScheduler;
                    ResubmissionScheduler resubmissionScheduler2 = enabled.resubmissionScheduler;
                    if (resubmissionScheduler != null ? !resubmissionScheduler.equals(resubmissionScheduler2) : resubmissionScheduler2 != null) {
                        return false;
                    }
                    ElementMatcher<? super Throwable> elementMatcher = this.matcher;
                    ElementMatcher<? super Throwable> elementMatcher2 = enabled.matcher;
                    return elementMatcher != null ? elementMatcher.equals(elementMatcher2) : elementMatcher2 == null;
                }

                public int hashCode() {
                    ResubmissionScheduler resubmissionScheduler = this.resubmissionScheduler;
                    int iHashCode = resubmissionScheduler == null ? 43 : resubmissionScheduler.hashCode();
                    ElementMatcher<? super Throwable> elementMatcher = this.matcher;
                    return ((iHashCode + 59) * 59) + (elementMatcher != null ? elementMatcher.hashCode() : 43);
                }

                protected Enabled(ResubmissionScheduler resubmissionScheduler, ElementMatcher<? super Throwable> elementMatcher) {
                    this.resubmissionScheduler = resubmissionScheduler;
                    this.matcher = elementMatcher;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionStrategy
                public Installation apply(Instrumentation instrumentation, LocationStrategy locationStrategy, Listener listener, InstallationListener installationListener, CircularityLock circularityLock, RawMatcher rawMatcher, RedefinitionStrategy redefinitionStrategy, BatchAllocator batchAllocator, Listener listener2) {
                    if (redefinitionStrategy.isEnabled() && this.resubmissionScheduler.isAlive()) {
                        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                        return new Installation(new Listener.Compound(new ResubmissionListener(this.matcher, concurrentHashMap), listener), new InstallationListener.Compound(new ResubmissionInstallationListener(this.resubmissionScheduler, instrumentation, locationStrategy, listener, circularityLock, rawMatcher, redefinitionStrategy, batchAllocator, listener2, concurrentHashMap), installationListener));
                    }
                    return new Installation(listener, installationListener);
                }

                protected static class ResubmissionListener extends Listener.Adapter {
                    private final ElementMatcher<? super Throwable> matcher;
                    private final ConcurrentMap<StorageKey, Set<String>> types;

                    protected ResubmissionListener(ElementMatcher<? super Throwable> elementMatcher, ConcurrentMap<StorageKey, Set<String>> concurrentMap) {
                        this.matcher = elementMatcher;
                        this.types = concurrentMap;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Listener.Adapter, net.bytebuddy.agent.builder.AgentBuilder.Listener
                    public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
                        Set<String> setPutIfAbsent;
                        if (z || !this.matcher.matches(th)) {
                            return;
                        }
                        Set<String> setNewSetFromMap = this.types.get(new LookupKey(classLoader));
                        if (setNewSetFromMap == null && (setPutIfAbsent = this.types.putIfAbsent(new StorageKey(classLoader), (setNewSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap())))) != null) {
                            setNewSetFromMap = setPutIfAbsent;
                        }
                        setNewSetFromMap.add(str);
                    }
                }

                protected static class ResubmissionInstallationListener extends InstallationListener.Adapter implements Runnable {
                    private volatile ResubmissionScheduler.Cancelable cancelable;
                    private final CircularityLock circularityLock;
                    private final Instrumentation instrumentation;
                    private final Listener listener;
                    private final LocationStrategy locationStrategy;
                    private final RawMatcher matcher;
                    private final BatchAllocator redefinitionBatchAllocator;
                    private final Listener redefinitionBatchListener;
                    private final RedefinitionStrategy redefinitionStrategy;
                    private final ResubmissionScheduler resubmissionScheduler;
                    private final ConcurrentMap<StorageKey, Set<String>> types;

                    protected ResubmissionInstallationListener(ResubmissionScheduler resubmissionScheduler, Instrumentation instrumentation, LocationStrategy locationStrategy, Listener listener, CircularityLock circularityLock, RawMatcher rawMatcher, RedefinitionStrategy redefinitionStrategy, BatchAllocator batchAllocator, Listener listener2, ConcurrentMap<StorageKey, Set<String>> concurrentMap) {
                        this.resubmissionScheduler = resubmissionScheduler;
                        this.instrumentation = instrumentation;
                        this.locationStrategy = locationStrategy;
                        this.listener = listener;
                        this.circularityLock = circularityLock;
                        this.matcher = rawMatcher;
                        this.redefinitionStrategy = redefinitionStrategy;
                        this.redefinitionBatchAllocator = batchAllocator;
                        this.redefinitionBatchListener = listener2;
                        this.types = concurrentMap;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener.Adapter, net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
                    public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                        this.cancelable = this.resubmissionScheduler.schedule(this);
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.InstallationListener.Adapter, net.bytebuddy.agent.builder.AgentBuilder.InstallationListener
                    public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                        ResubmissionScheduler.Cancelable cancelable = this.cancelable;
                        if (cancelable != null) {
                            cancelable.cancel();
                        }
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        boolean zAcquire = this.circularityLock.acquire();
                        try {
                            Iterator<Map.Entry<StorageKey, Set<String>>> it = this.types.entrySet().iterator();
                            ArrayList arrayList = new ArrayList();
                            while (!Thread.interrupted() && it.hasNext()) {
                                Map.Entry<StorageKey, Set<String>> next = it.next();
                                ClassLoader classLoader = (ClassLoader) next.getKey().get();
                                if (classLoader != null || next.getKey().isBootstrapLoader()) {
                                    Iterator<String> it2 = next.getValue().iterator();
                                    while (it2.hasNext()) {
                                        try {
                                            Class<?> cls = Class.forName(it2.next(), false, classLoader);
                                            try {
                                                if (this.instrumentation.isModifiableClass(cls) && this.matcher.matches(new TypeDescription.ForLoadedType(cls), cls.getClassLoader(), JavaModule.ofType(cls), cls, cls.getProtectionDomain())) {
                                                    arrayList.add(cls);
                                                }
                                            } catch (Throwable th) {
                                                try {
                                                    this.listener.onError(TypeDescription.ForLoadedType.getName(cls), cls.getClassLoader(), JavaModule.ofType(cls), true, th);
                                                    this.listener.onComplete(TypeDescription.ForLoadedType.getName(cls), cls.getClassLoader(), JavaModule.ofType(cls), true);
                                                } catch (Throwable th2) {
                                                    this.listener.onComplete(TypeDescription.ForLoadedType.getName(cls), cls.getClassLoader(), JavaModule.ofType(cls), true);
                                                    throw th2;
                                                }
                                            }
                                        } catch (Throwable unused) {
                                            continue;
                                        }
                                        it2.remove();
                                    }
                                } else {
                                    it.remove();
                                }
                            }
                            if (!arrayList.isEmpty()) {
                                Collector collectorMake = this.redefinitionStrategy.make();
                                collectorMake.include(arrayList);
                                collectorMake.apply(this.instrumentation, this.circularityLock, this.locationStrategy, this.listener, this.redefinitionBatchAllocator, this.redefinitionBatchListener, 0);
                            }
                        } finally {
                            if (zAcquire) {
                                this.circularityLock.release();
                            }
                        }
                    }
                }

                protected static class LookupKey {
                    private final ClassLoader classLoader;
                    private final int hashCode;

                    protected LookupKey(ClassLoader classLoader) {
                        this.classLoader = classLoader;
                        this.hashCode = System.identityHashCode(classLoader);
                    }

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj instanceof LookupKey) {
                            return this.classLoader == ((LookupKey) obj).classLoader;
                        }
                        if (!(obj instanceof StorageKey)) {
                            return false;
                        }
                        StorageKey storageKey = (StorageKey) obj;
                        return this.hashCode == storageKey.hashCode && this.classLoader == storageKey.get();
                    }

                    public int hashCode() {
                        return this.hashCode;
                    }
                }

                protected static class StorageKey extends WeakReference<ClassLoader> {
                    private final int hashCode;

                    protected StorageKey(ClassLoader classLoader) {
                        super(classLoader);
                        this.hashCode = System.identityHashCode(classLoader);
                    }

                    protected boolean isBootstrapLoader() {
                        return this.hashCode == 0;
                    }

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj instanceof LookupKey) {
                            LookupKey lookupKey = (LookupKey) obj;
                            return this.hashCode == lookupKey.hashCode && get() == lookupKey.classLoader;
                        }
                        if (!(obj instanceof StorageKey)) {
                            return false;
                        }
                        StorageKey storageKey = (StorageKey) obj;
                        return this.hashCode == storageKey.hashCode && get() == storageKey.get();
                    }

                    public int hashCode() {
                        return this.hashCode;
                    }
                }
            }

            public static class Installation {
                private final InstallationListener installationListener;
                private final Listener listener;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Installation;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Installation)) {
                        return false;
                    }
                    Installation installation = (Installation) obj;
                    if (!installation.canEqual(this)) {
                        return false;
                    }
                    Listener listener = getListener();
                    Listener listener2 = installation.getListener();
                    if (listener != null ? !listener.equals(listener2) : listener2 != null) {
                        return false;
                    }
                    InstallationListener installationListener = getInstallationListener();
                    InstallationListener installationListener2 = installation.getInstallationListener();
                    return installationListener != null ? installationListener.equals(installationListener2) : installationListener2 == null;
                }

                public int hashCode() {
                    Listener listener = getListener();
                    int iHashCode = listener == null ? 43 : listener.hashCode();
                    InstallationListener installationListener = getInstallationListener();
                    return ((iHashCode + 59) * 59) + (installationListener != null ? installationListener.hashCode() : 43);
                }

                protected Installation(Listener listener, InstallationListener installationListener) {
                    this.listener = listener;
                    this.installationListener = installationListener;
                }

                protected Listener getListener() {
                    return this.listener;
                }

                protected InstallationListener getInstallationListener() {
                    return this.installationListener;
                }
            }
        }

        protected static abstract class Collector {
            private static final Class<?> NO_LOADED_TYPE = null;
            protected final List<Class<?>> types = new ArrayList();

            protected abstract void doApply(Instrumentation instrumentation, CircularityLock circularityLock, List<Class<?>> list, LocationStrategy locationStrategy, Listener listener) throws ClassNotFoundException, UnmodifiableClassException;

            protected Collector() {
            }

            protected void consider(RawMatcher rawMatcher, RawMatcher rawMatcher2, Listener listener, TypeDescription typeDescription, Class<?> cls, JavaModule javaModule) {
                consider(rawMatcher, rawMatcher2, listener, typeDescription, cls, NO_LOADED_TYPE, javaModule, false);
            }

            /* JADX WARN: Finally extract failed */
            protected void consider(RawMatcher rawMatcher, RawMatcher rawMatcher2, Listener listener, TypeDescription typeDescription, Class<?> cls, Class<?> cls2, JavaModule javaModule, boolean z) {
                if (z || rawMatcher2.matches(typeDescription, cls.getClassLoader(), javaModule, cls2, cls.getProtectionDomain()) || !rawMatcher.matches(typeDescription, cls.getClassLoader(), javaModule, cls2, cls.getProtectionDomain()) || !this.types.add(cls)) {
                    boolean z2 = true;
                    try {
                        try {
                            listener.onIgnored(typeDescription, cls.getClassLoader(), javaModule, cls2 != null);
                            String name = typeDescription.getName();
                            ClassLoader classLoader = cls.getClassLoader();
                            if (cls2 == null) {
                                z2 = false;
                            }
                            listener.onComplete(name, classLoader, javaModule, z2);
                        } catch (Throwable th) {
                            String name2 = typeDescription.getName();
                            ClassLoader classLoader2 = cls.getClassLoader();
                            if (cls2 == null) {
                                z2 = false;
                            }
                            listener.onComplete(name2, classLoader2, javaModule, z2);
                            throw th;
                        }
                    } catch (Throwable unused) {
                    }
                }
            }

            protected void include(List<Class<?>> list) {
                this.types.addAll(list);
            }

            protected int apply(Instrumentation instrumentation, CircularityLock circularityLock, LocationStrategy locationStrategy, Listener listener, BatchAllocator batchAllocator, Listener listener2, int i) {
                HashMap map = new HashMap();
                PrependableIterator prependableIterator = new PrependableIterator(batchAllocator.batch(this.types));
                while (prependableIterator.hasNext()) {
                    List<Class<?>> next = prependableIterator.next();
                    listener2.onBatch(i, next, this.types);
                    try {
                        doApply(instrumentation, circularityLock, next, locationStrategy, listener);
                    } catch (Throwable th) {
                        prependableIterator.prepend(listener2.onError(i, next, th, this.types));
                        map.put(next, th);
                    }
                    i++;
                }
                listener2.onComplete(i, this.types, map);
                return i;
            }

            protected static class PrependableIterator implements Iterator<List<Class<?>>> {
                private final Deque<Iterator<? extends List<Class<?>>>> backlog = new ArrayDeque();
                private Iterator<? extends List<Class<?>>> current;

                protected PrependableIterator(Iterable<? extends List<Class<?>>> iterable) {
                    this.current = iterable.iterator();
                }

                public void prepend(Iterable<? extends List<Class<?>>> iterable) {
                    Iterator<? extends List<Class<?>>> it = iterable.iterator();
                    if (it.hasNext()) {
                        if (this.current.hasNext()) {
                            this.backlog.addLast(this.current);
                        }
                        this.current = it;
                    }
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.current.hasNext();
                }

                @Override // java.util.Iterator
                public List<Class<?>> next() {
                    boolean zHasNext;
                    boolean zIsEmpty;
                    try {
                        while (true) {
                            if (!zHasNext) {
                                if (zIsEmpty) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        return this.current.next();
                    } finally {
                        while (!this.current.hasNext() && !this.backlog.isEmpty()) {
                            this.current = this.backlog.removeLast();
                        }
                    }
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException("remove");
                }
            }

            protected static class ForRedefinition extends Collector {
                protected ForRedefinition() {
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Collector
                protected void doApply(Instrumentation instrumentation, CircularityLock circularityLock, List<Class<?>> list, LocationStrategy locationStrategy, Listener listener) throws ClassNotFoundException, UnmodifiableClassException {
                    ArrayList arrayList = new ArrayList(list.size());
                    for (Class<?> cls : list) {
                        try {
                            arrayList.add(new ClassDefinition(cls, locationStrategy.classFileLocator(cls.getClassLoader(), JavaModule.ofType(cls)).locate(TypeDescription.ForLoadedType.getName(cls)).resolve()));
                        } catch (Throwable th) {
                            try {
                                JavaModule javaModuleOfType = JavaModule.ofType(cls);
                                try {
                                    listener.onError(TypeDescription.ForLoadedType.getName(cls), cls.getClassLoader(), javaModuleOfType, true, th);
                                    listener.onComplete(TypeDescription.ForLoadedType.getName(cls), cls.getClassLoader(), javaModuleOfType, true);
                                } catch (Throwable th2) {
                                    listener.onComplete(TypeDescription.ForLoadedType.getName(cls), cls.getClassLoader(), javaModuleOfType, true);
                                    throw th2;
                                }
                            } catch (Throwable unused) {
                                continue;
                            }
                        }
                    }
                    if (arrayList.isEmpty()) {
                        return;
                    }
                    circularityLock.release();
                    try {
                        instrumentation.redefineClasses((ClassDefinition[]) arrayList.toArray(new ClassDefinition[arrayList.size()]));
                    } finally {
                        circularityLock.acquire();
                    }
                }
            }

            protected static class ForRetransformation extends Collector {
                protected ForRetransformation() {
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Collector
                protected void doApply(Instrumentation instrumentation, CircularityLock circularityLock, List<Class<?>> list, LocationStrategy locationStrategy, Listener listener) throws UnmodifiableClassException {
                    if (list.isEmpty()) {
                        return;
                    }
                    circularityLock.release();
                    try {
                        instrumentation.retransformClasses((Class[]) list.toArray(new Class[list.size()]));
                    } finally {
                        circularityLock.acquire();
                    }
                }
            }
        }
    }

    public enum LambdaInstrumentationStrategy {
        ENABLED { // from class: net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy.1
            @Override // net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy
            protected boolean isInstrumented(Class<?> cls) {
                return true;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy
            protected void apply(ByteBuddy byteBuddy, Instrumentation instrumentation, ClassFileTransformer classFileTransformer) {
                if (LambdaFactory.register(classFileTransformer, new LambdaInstanceFactory(byteBuddy))) {
                    try {
                        Class<?> cls = Class.forName("java.lang.invoke.LambdaMetafactory");
                        byteBuddy.with(Implementation.Context.Disabled.Factory.INSTANCE).redefine(cls).visit(new AsmVisitorWrapper.ForDeclaredMethods().method(ElementMatchers.named("metafactory"), MetaFactoryRedirection.INSTANCE).method(ElementMatchers.named("altMetafactory"), AlternativeMetaFactoryRedirection.INSTANCE)).make().load(cls.getClassLoader(), ClassReloadingStrategy.of(instrumentation));
                    } catch (ClassNotFoundException unused) {
                    }
                }
            }
        },
        DISABLED { // from class: net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy.2
            @Override // net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy
            protected void apply(ByteBuddy byteBuddy, Instrumentation instrumentation, ClassFileTransformer classFileTransformer) {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy
            protected boolean isInstrumented(Class<?> cls) {
                return cls == null || !cls.getName().contains("/");
            }
        };

        protected static final MethodVisitor IGNORE_ORIGINAL;
        private static final String UNSAFE_CLASS;

        protected abstract void apply(ByteBuddy byteBuddy, Instrumentation instrumentation, ClassFileTransformer classFileTransformer);

        protected abstract boolean isInstrumented(Class<?> cls);

        /* synthetic */ LambdaInstrumentationStrategy(AnonymousClass1 anonymousClass1) {
            this();
        }

        static {
            UNSAFE_CLASS = ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V6).isAtLeast(ClassFileVersion.JAVA_V9) ? "jdk/internal/misc/Unsafe" : "sun/misc/Unsafe";
            IGNORE_ORIGINAL = null;
        }

        public static void release(ClassFileTransformer classFileTransformer, Instrumentation instrumentation) {
            if (LambdaFactory.release(classFileTransformer)) {
                try {
                    ClassReloadingStrategy.of(instrumentation).reset(Class.forName("java.lang.invoke.LambdaMetafactory"));
                } catch (Exception e) {
                    throw new IllegalStateException("Could not release lambda transformer", e);
                }
            }
        }

        public static LambdaInstrumentationStrategy of(boolean z) {
            return z ? ENABLED : DISABLED;
        }

        public boolean isEnabled() {
            return this == ENABLED;
        }

        protected static class LambdaInstanceFactory {
            private static final String FIELD_PREFIX = "arg$";
            private static final String LAMBDA_FACTORY = "get$Lambda";
            private static final AtomicInteger LAMBDA_NAME_COUNTER = new AtomicInteger();
            private static final String LAMBDA_TYPE_INFIX = "$$Lambda$ByteBuddy$";
            private static final Class<?> NOT_PREVIOUSLY_DEFINED = null;
            private final ByteBuddy byteBuddy;

            protected boolean canEqual(Object obj) {
                return obj instanceof LambdaInstanceFactory;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof LambdaInstanceFactory)) {
                    return false;
                }
                LambdaInstanceFactory lambdaInstanceFactory = (LambdaInstanceFactory) obj;
                if (!lambdaInstanceFactory.canEqual(this)) {
                    return false;
                }
                ByteBuddy byteBuddy = this.byteBuddy;
                ByteBuddy byteBuddy2 = lambdaInstanceFactory.byteBuddy;
                return byteBuddy != null ? byteBuddy.equals(byteBuddy2) : byteBuddy2 == null;
            }

            public int hashCode() {
                ByteBuddy byteBuddy = this.byteBuddy;
                return 59 + (byteBuddy == null ? 43 : byteBuddy.hashCode());
            }

            protected LambdaInstanceFactory(ByteBuddy byteBuddy) {
                this.byteBuddy = byteBuddy;
            }

            public byte[] make(Object obj, String str, Object obj2, Object obj3, Object obj4, Object obj5, boolean z, List<Class<?>> list, List<?> list2, Collection<? extends ClassFileTransformer> collection) {
                char c;
                JavaConstant.MethodType methodTypeOfLoaded = JavaConstant.MethodType.ofLoaded(obj2);
                JavaConstant.MethodType methodTypeOfLoaded2 = JavaConstant.MethodType.ofLoaded(obj3);
                JavaConstant.MethodHandle methodHandleOfLoaded = JavaConstant.MethodHandle.ofLoaded(obj4, obj);
                JavaConstant.MethodType methodTypeOfLoaded3 = JavaConstant.MethodType.ofLoaded(obj5);
                Class<?> clsLookupType = JavaConstant.MethodHandle.lookupType(obj);
                String str2 = clsLookupType.getName() + LAMBDA_TYPE_INFIX + LAMBDA_NAME_COUNTER.incrementAndGet();
                boolean z2 = false;
                DynamicType.Builder builderIntercept = this.byteBuddy.subclass(methodTypeOfLoaded.getReturnType(), ConstructorStrategy.Default.NO_CONSTRUCTORS).modifiers(TypeManifestation.FINAL, Visibility.PUBLIC).implement((List<? extends Type>) list).name(str2).defineConstructor(Visibility.PUBLIC).withParameters((Collection<? extends TypeDefinition>) methodTypeOfLoaded.getParameterTypes()).intercept(ConstructorImplementation.INSTANCE).method(ElementMatchers.named(str).and(ElementMatchers.takesArguments(methodTypeOfLoaded2.getParameterTypes())).and(ElementMatchers.returns(methodTypeOfLoaded2.getReturnType()))).intercept(new LambdaMethodImplementation(methodHandleOfLoaded, methodTypeOfLoaded3));
                Iterator it = methodTypeOfLoaded.getParameterTypes().iterator();
                int i = 0;
                while (it.hasNext()) {
                    i++;
                    builderIntercept = builderIntercept.defineField(FIELD_PREFIX + i, (TypeDescription) it.next(), Visibility.PRIVATE, FieldManifestation.FINAL);
                    z2 = false;
                }
                boolean z3 = z2;
                if (!methodTypeOfLoaded.getParameterTypes().isEmpty()) {
                    TypeDescription returnType = methodTypeOfLoaded.getReturnType();
                    ModifierContributor.ForMethod[] forMethodArr = new ModifierContributor.ForMethod[2];
                    forMethodArr[z3 ? 1 : 0] = Visibility.PRIVATE;
                    forMethodArr[1] = Ownership.STATIC;
                    builderIntercept = builderIntercept.defineMethod(LAMBDA_FACTORY, returnType, forMethodArr).withParameters((Collection<? extends TypeDefinition>) methodTypeOfLoaded.getParameterTypes()).intercept(FactoryImplementation.INSTANCE);
                }
                if (z) {
                    if (list.contains(Serializable.class)) {
                        c = 0;
                    } else {
                        c = 0;
                        builderIntercept = builderIntercept.implement(Serializable.class);
                    }
                    ModifierContributor.ForMethod[] forMethodArr2 = new ModifierContributor.ForMethod[1];
                    forMethodArr2[c] = Visibility.PRIVATE;
                    builderIntercept = builderIntercept.defineMethod("writeReplace", Object.class, forMethodArr2).intercept(new SerializationImplementation(new TypeDescription.ForLoadedType(clsLookupType), methodTypeOfLoaded.getReturnType(), str, methodTypeOfLoaded2, methodHandleOfLoaded, JavaConstant.MethodType.ofLoaded(obj5)));
                } else if (methodTypeOfLoaded.getReturnType().isAssignableTo(Serializable.class)) {
                    builderIntercept = builderIntercept.defineMethod("readObject", Void.TYPE, Visibility.PRIVATE).withParameters(ObjectInputStream.class).throwing(NotSerializableException.class).intercept(ExceptionMethod.throwing((Class<? extends Throwable>) NotSerializableException.class, "Non-serializable lambda")).defineMethod("writeObject", Void.TYPE, Visibility.PRIVATE).withParameters(ObjectOutputStream.class).throwing(NotSerializableException.class).intercept(ExceptionMethod.throwing((Class<? extends Throwable>) NotSerializableException.class, "Non-serializable lambda"));
                }
                Iterator<?> it2 = list2.iterator();
                while (it2.hasNext()) {
                    JavaConstant.MethodType methodTypeOfLoaded4 = JavaConstant.MethodType.ofLoaded(it2.next());
                    builderIntercept = builderIntercept.defineMethod(str, methodTypeOfLoaded4.getReturnType(), MethodManifestation.BRIDGE, Visibility.PUBLIC).withParameters((Collection<? extends TypeDefinition>) methodTypeOfLoaded4.getParameterTypes()).intercept(new BridgeMethodImplementation(str, methodTypeOfLoaded2));
                }
                byte[] bytes = builderIntercept.make().getBytes();
                Iterator<? extends ClassFileTransformer> it3 = collection.iterator();
                while (it3.hasNext()) {
                    try {
                        byte[] bArrTransform = it3.next().transform(clsLookupType.getClassLoader(), str2.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR), NOT_PREVIOUSLY_DEFINED, clsLookupType.getProtectionDomain(), bytes);
                        if (bArrTransform != null) {
                            bytes = bArrTransform;
                        }
                    } catch (Throwable unused) {
                    }
                }
                return bytes;
            }

            protected enum ConstructorImplementation implements Implementation {
                INSTANCE;

                private final MethodDescription.InDefinedShape objectConstructor = (MethodDescription.InDefinedShape) TypeDescription.OBJECT.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly();

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                ConstructorImplementation() {
                }

                @Override // net.bytebuddy.implementation.Implementation
                public ByteCodeAppender appender(Implementation.Target target) {
                    return new Appender(target.getInstrumentedType().getDeclaredFields());
                }

                protected static class Appender implements ByteCodeAppender {
                    private final List<FieldDescription.InDefinedShape> declaredFields;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Appender;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Appender)) {
                            return false;
                        }
                        Appender appender = (Appender) obj;
                        if (!appender.canEqual(this)) {
                            return false;
                        }
                        List<FieldDescription.InDefinedShape> list = this.declaredFields;
                        List<FieldDescription.InDefinedShape> list2 = appender.declaredFields;
                        return list != null ? list.equals(list2) : list2 == null;
                    }

                    public int hashCode() {
                        List<FieldDescription.InDefinedShape> list = this.declaredFields;
                        return 59 + (list == null ? 43 : list.hashCode());
                    }

                    protected Appender(List<FieldDescription.InDefinedShape> list) {
                        this.declaredFields = list;
                    }

                    @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        ArrayList arrayList = new ArrayList(this.declaredFields.size() * 3);
                        Iterator it = methodDescription.getParameters().iterator();
                        while (it.hasNext()) {
                            ParameterDescription parameterDescription = (ParameterDescription) it.next();
                            arrayList.add(MethodVariableAccess.loadThis());
                            arrayList.add(MethodVariableAccess.load(parameterDescription));
                            arrayList.add(FieldAccess.forField(this.declaredFields.get(parameterDescription.getIndex())).write());
                        }
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodInvocation.invoke(ConstructorImplementation.INSTANCE.objectConstructor), new StackManipulation.Compound(arrayList), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }

            protected enum FactoryImplementation implements Implementation {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                @Override // net.bytebuddy.implementation.Implementation
                public ByteCodeAppender appender(Implementation.Target target) {
                    return new Appender(target.getInstrumentedType());
                }

                protected static class Appender implements ByteCodeAppender {
                    private final TypeDescription instrumentedType;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Appender;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Appender)) {
                            return false;
                        }
                        Appender appender = (Appender) obj;
                        if (!appender.canEqual(this)) {
                            return false;
                        }
                        TypeDescription typeDescription = this.instrumentedType;
                        TypeDescription typeDescription2 = appender.instrumentedType;
                        return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                    }

                    public int hashCode() {
                        TypeDescription typeDescription = this.instrumentedType;
                        return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                    }

                    protected Appender(TypeDescription typeDescription) {
                        this.instrumentedType = typeDescription;
                    }

                    @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(TypeCreation.of(this.instrumentedType), Duplication.SINGLE, MethodVariableAccess.allArgumentsOf(methodDescription), MethodInvocation.invoke((MethodDescription.InDefinedShape) this.instrumentedType.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly()), MethodReturn.REFERENCE).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }

            protected static class LambdaMethodImplementation implements Implementation {
                private final JavaConstant.MethodType specializedLambdaMethod;
                private final JavaConstant.MethodHandle targetMethod;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof LambdaMethodImplementation;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof LambdaMethodImplementation)) {
                        return false;
                    }
                    LambdaMethodImplementation lambdaMethodImplementation = (LambdaMethodImplementation) obj;
                    if (!lambdaMethodImplementation.canEqual(this)) {
                        return false;
                    }
                    JavaConstant.MethodHandle methodHandle = this.targetMethod;
                    JavaConstant.MethodHandle methodHandle2 = lambdaMethodImplementation.targetMethod;
                    if (methodHandle != null ? !methodHandle.equals(methodHandle2) : methodHandle2 != null) {
                        return false;
                    }
                    JavaConstant.MethodType methodType = this.specializedLambdaMethod;
                    JavaConstant.MethodType methodType2 = lambdaMethodImplementation.specializedLambdaMethod;
                    return methodType != null ? methodType.equals(methodType2) : methodType2 == null;
                }

                public int hashCode() {
                    JavaConstant.MethodHandle methodHandle = this.targetMethod;
                    int iHashCode = methodHandle == null ? 43 : methodHandle.hashCode();
                    JavaConstant.MethodType methodType = this.specializedLambdaMethod;
                    return ((iHashCode + 59) * 59) + (methodType != null ? methodType.hashCode() : 43);
                }

                protected LambdaMethodImplementation(JavaConstant.MethodHandle methodHandle, JavaConstant.MethodType methodType) {
                    this.targetMethod = methodHandle;
                    this.specializedLambdaMethod = methodType;
                }

                @Override // net.bytebuddy.implementation.Implementation
                public ByteCodeAppender appender(Implementation.Target target) {
                    return new Appender((MethodDescription) this.targetMethod.getOwnerType().getDeclaredMethods().filter(ElementMatchers.hasMethodName(this.targetMethod.getName()).and(ElementMatchers.returns(this.targetMethod.getReturnType())).and(ElementMatchers.takesArguments(this.targetMethod.getParameterTypes()))).getOnly(), this.specializedLambdaMethod, target.getInstrumentedType().getDeclaredFields());
                }

                protected static class Appender implements ByteCodeAppender {
                    private final List<FieldDescription.InDefinedShape> declaredFields;
                    private final JavaConstant.MethodType specializedLambdaMethod;
                    private final MethodDescription targetMethod;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Appender;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Appender)) {
                            return false;
                        }
                        Appender appender = (Appender) obj;
                        if (!appender.canEqual(this)) {
                            return false;
                        }
                        MethodDescription methodDescription = this.targetMethod;
                        MethodDescription methodDescription2 = appender.targetMethod;
                        if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                            return false;
                        }
                        JavaConstant.MethodType methodType = this.specializedLambdaMethod;
                        JavaConstant.MethodType methodType2 = appender.specializedLambdaMethod;
                        if (methodType != null ? !methodType.equals(methodType2) : methodType2 != null) {
                            return false;
                        }
                        List<FieldDescription.InDefinedShape> list = this.declaredFields;
                        List<FieldDescription.InDefinedShape> list2 = appender.declaredFields;
                        return list != null ? list.equals(list2) : list2 == null;
                    }

                    public int hashCode() {
                        MethodDescription methodDescription = this.targetMethod;
                        int iHashCode = methodDescription == null ? 43 : methodDescription.hashCode();
                        JavaConstant.MethodType methodType = this.specializedLambdaMethod;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (methodType == null ? 43 : methodType.hashCode());
                        List<FieldDescription.InDefinedShape> list = this.declaredFields;
                        return (iHashCode2 * 59) + (list != null ? list.hashCode() : 43);
                    }

                    protected Appender(MethodDescription methodDescription, JavaConstant.MethodType methodType, List<FieldDescription.InDefinedShape> list) {
                        this.targetMethod = methodDescription;
                        this.specializedLambdaMethod = methodType;
                        this.declaredFields = list;
                    }

                    @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        TypeDescription.Generic returnType;
                        StackManipulation compound = this.targetMethod.isConstructor() ? new StackManipulation.Compound(TypeCreation.of(this.targetMethod.getDeclaringType().asErasure()), Duplication.SINGLE) : StackManipulation.Trivial.INSTANCE;
                        ArrayList arrayList = new ArrayList((this.declaredFields.size() * 2) + 1);
                        for (FieldDescription.InDefinedShape inDefinedShape : this.declaredFields) {
                            arrayList.add(MethodVariableAccess.loadThis());
                            arrayList.add(FieldAccess.forField(inDefinedShape).read());
                        }
                        ArrayList arrayList2 = new ArrayList(methodDescription.getParameters().size() * 2);
                        Iterator it = methodDescription.getParameters().iterator();
                        while (it.hasNext()) {
                            ParameterDescription parameterDescription = (ParameterDescription) it.next();
                            arrayList2.add(MethodVariableAccess.load(parameterDescription));
                            arrayList2.add(Assigner.DEFAULT.assign(parameterDescription.getType(), ((TypeDescription) this.specializedLambdaMethod.getParameterTypes().get(parameterDescription.getIndex())).asGenericType(), Assigner.Typing.DYNAMIC));
                        }
                        StackManipulation[] stackManipulationArr = new StackManipulation[6];
                        stackManipulationArr[0] = compound;
                        stackManipulationArr[1] = new StackManipulation.Compound(arrayList);
                        stackManipulationArr[2] = new StackManipulation.Compound(arrayList2);
                        stackManipulationArr[3] = MethodInvocation.invoke(this.targetMethod);
                        Assigner assigner = Assigner.DEFAULT;
                        if (this.targetMethod.isConstructor()) {
                            returnType = this.targetMethod.getDeclaringType().asGenericType();
                        } else {
                            returnType = this.targetMethod.getReturnType();
                        }
                        stackManipulationArr[4] = assigner.assign(returnType, this.specializedLambdaMethod.getReturnType().asGenericType(), Assigner.Typing.DYNAMIC);
                        stackManipulationArr[5] = MethodReturn.of(this.specializedLambdaMethod.getReturnType());
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }

            protected static class SerializationImplementation implements Implementation {
                private final JavaConstant.MethodType lambdaMethod;
                private final String lambdaMethodName;
                private final TypeDescription lambdaType;
                private final JavaConstant.MethodType specializedMethod;
                private final JavaConstant.MethodHandle targetMethod;
                private final TypeDescription targetType;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof SerializationImplementation;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof SerializationImplementation)) {
                        return false;
                    }
                    SerializationImplementation serializationImplementation = (SerializationImplementation) obj;
                    if (!serializationImplementation.canEqual(this)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.targetType;
                    TypeDescription typeDescription2 = serializationImplementation.targetType;
                    if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                        return false;
                    }
                    TypeDescription typeDescription3 = this.lambdaType;
                    TypeDescription typeDescription4 = serializationImplementation.lambdaType;
                    if (typeDescription3 != null ? !typeDescription3.equals(typeDescription4) : typeDescription4 != null) {
                        return false;
                    }
                    String str = this.lambdaMethodName;
                    String str2 = serializationImplementation.lambdaMethodName;
                    if (str != null ? !str.equals(str2) : str2 != null) {
                        return false;
                    }
                    JavaConstant.MethodType methodType = this.lambdaMethod;
                    JavaConstant.MethodType methodType2 = serializationImplementation.lambdaMethod;
                    if (methodType != null ? !methodType.equals(methodType2) : methodType2 != null) {
                        return false;
                    }
                    JavaConstant.MethodHandle methodHandle = this.targetMethod;
                    JavaConstant.MethodHandle methodHandle2 = serializationImplementation.targetMethod;
                    if (methodHandle != null ? !methodHandle.equals(methodHandle2) : methodHandle2 != null) {
                        return false;
                    }
                    JavaConstant.MethodType methodType3 = this.specializedMethod;
                    JavaConstant.MethodType methodType4 = serializationImplementation.specializedMethod;
                    return methodType3 != null ? methodType3.equals(methodType4) : methodType4 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.targetType;
                    int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                    TypeDescription typeDescription2 = this.lambdaType;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (typeDescription2 == null ? 43 : typeDescription2.hashCode());
                    String str = this.lambdaMethodName;
                    int iHashCode3 = (iHashCode2 * 59) + (str == null ? 43 : str.hashCode());
                    JavaConstant.MethodType methodType = this.lambdaMethod;
                    int iHashCode4 = (iHashCode3 * 59) + (methodType == null ? 43 : methodType.hashCode());
                    JavaConstant.MethodHandle methodHandle = this.targetMethod;
                    int iHashCode5 = (iHashCode4 * 59) + (methodHandle == null ? 43 : methodHandle.hashCode());
                    JavaConstant.MethodType methodType2 = this.specializedMethod;
                    return (iHashCode5 * 59) + (methodType2 != null ? methodType2.hashCode() : 43);
                }

                protected SerializationImplementation(TypeDescription typeDescription, TypeDescription typeDescription2, String str, JavaConstant.MethodType methodType, JavaConstant.MethodHandle methodHandle, JavaConstant.MethodType methodType2) {
                    this.targetType = typeDescription;
                    this.lambdaType = typeDescription2;
                    this.lambdaMethodName = str;
                    this.lambdaMethod = methodType;
                    this.targetMethod = methodHandle;
                    this.specializedMethod = methodType2;
                }

                @Override // net.bytebuddy.implementation.Implementation
                public ByteCodeAppender appender(Implementation.Target target) {
                    try {
                        TypeDescription.ForLoadedType forLoadedType = new TypeDescription.ForLoadedType(Class.forName("java.lang.invoke.SerializedLambda"));
                        ArrayList arrayList = new ArrayList(target.getInstrumentedType().getDeclaredFields().size());
                        for (FieldDescription.InDefinedShape inDefinedShape : target.getInstrumentedType().getDeclaredFields()) {
                            arrayList.add(new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField(inDefinedShape).read(), Assigner.DEFAULT.assign(inDefinedShape.getType(), TypeDescription.Generic.OBJECT, Assigner.Typing.STATIC)));
                        }
                        return new ByteCodeAppender.Simple(new StackManipulation.Compound(TypeCreation.of(forLoadedType), Duplication.SINGLE, ClassConstant.of(this.targetType), new TextConstant(this.lambdaType.getInternalName()), new TextConstant(this.lambdaMethodName), new TextConstant(this.lambdaMethod.getDescriptor()), IntegerConstant.forValue(this.targetMethod.getHandleType().getIdentifier()), new TextConstant(this.targetMethod.getOwnerType().getInternalName()), new TextConstant(this.targetMethod.getName()), new TextConstant(this.targetMethod.getDescriptor()), new TextConstant(this.specializedMethod.getDescriptor()), ArrayFactory.forType(TypeDescription.Generic.OBJECT).withValues(arrayList), MethodInvocation.invoke((MethodDescription.InDefinedShape) forLoadedType.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly()), MethodReturn.REFERENCE));
                    } catch (ClassNotFoundException e) {
                        throw new IllegalStateException("Cannot find class for lambda serialization", e);
                    }
                }
            }

            protected static class BridgeMethodImplementation implements Implementation {
                private final JavaConstant.MethodType lambdaMethod;
                private final String lambdaMethodName;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof BridgeMethodImplementation;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof BridgeMethodImplementation)) {
                        return false;
                    }
                    BridgeMethodImplementation bridgeMethodImplementation = (BridgeMethodImplementation) obj;
                    if (!bridgeMethodImplementation.canEqual(this)) {
                        return false;
                    }
                    String str = this.lambdaMethodName;
                    String str2 = bridgeMethodImplementation.lambdaMethodName;
                    if (str != null ? !str.equals(str2) : str2 != null) {
                        return false;
                    }
                    JavaConstant.MethodType methodType = this.lambdaMethod;
                    JavaConstant.MethodType methodType2 = bridgeMethodImplementation.lambdaMethod;
                    return methodType != null ? methodType.equals(methodType2) : methodType2 == null;
                }

                public int hashCode() {
                    String str = this.lambdaMethodName;
                    int iHashCode = str == null ? 43 : str.hashCode();
                    JavaConstant.MethodType methodType = this.lambdaMethod;
                    return ((iHashCode + 59) * 59) + (methodType != null ? methodType.hashCode() : 43);
                }

                protected BridgeMethodImplementation(String str, JavaConstant.MethodType methodType) {
                    this.lambdaMethodName = str;
                    this.lambdaMethod = methodType;
                }

                @Override // net.bytebuddy.implementation.Implementation
                public ByteCodeAppender appender(Implementation.Target target) {
                    return new Appender(target.invokeSuper(new MethodDescription.SignatureToken(this.lambdaMethodName, this.lambdaMethod.getReturnType(), this.lambdaMethod.getParameterTypes())));
                }

                protected static class Appender implements ByteCodeAppender {
                    private final Implementation.SpecialMethodInvocation bridgeTargetInvocation;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Appender;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Appender)) {
                            return false;
                        }
                        Appender appender = (Appender) obj;
                        if (!appender.canEqual(this)) {
                            return false;
                        }
                        Implementation.SpecialMethodInvocation specialMethodInvocation = this.bridgeTargetInvocation;
                        Implementation.SpecialMethodInvocation specialMethodInvocation2 = appender.bridgeTargetInvocation;
                        return specialMethodInvocation != null ? specialMethodInvocation.equals(specialMethodInvocation2) : specialMethodInvocation2 == null;
                    }

                    public int hashCode() {
                        Implementation.SpecialMethodInvocation specialMethodInvocation = this.bridgeTargetInvocation;
                        return 59 + (specialMethodInvocation == null ? 43 : specialMethodInvocation.hashCode());
                    }

                    protected Appender(Implementation.SpecialMethodInvocation specialMethodInvocation) {
                        this.bridgeTargetInvocation = specialMethodInvocation;
                    }

                    @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        ByteCodeAppender[] byteCodeAppenderArr = new ByteCodeAppender[1];
                        StackManipulation[] stackManipulationArr = new StackManipulation[4];
                        stackManipulationArr[0] = MethodVariableAccess.allArgumentsOf(methodDescription).asBridgeOf(this.bridgeTargetInvocation.getMethodDescription()).prependThisReference();
                        Implementation.SpecialMethodInvocation specialMethodInvocation = this.bridgeTargetInvocation;
                        stackManipulationArr[1] = specialMethodInvocation;
                        stackManipulationArr[2] = specialMethodInvocation.getMethodDescription().getReturnType().asErasure().isAssignableTo(methodDescription.getReturnType().asErasure()) ? StackManipulation.Trivial.INSTANCE : TypeCasting.to(methodDescription.getReceiverType());
                        stackManipulationArr[3] = MethodReturn.of(methodDescription.getReturnType());
                        byteCodeAppenderArr[0] = new ByteCodeAppender.Simple(stackManipulationArr);
                        return new ByteCodeAppender.Compound(byteCodeAppenderArr).apply(methodVisitor, context, methodDescription);
                    }
                }
            }
        }

        protected enum MetaFactoryRedirection implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
            INSTANCE;

            @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper
            public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
                methodVisitor.visitCode();
                methodVisitor.visitMethodInsn(184, LambdaInstrumentationStrategy.UNSAFE_CLASS, "getUnsafe", "()L" + LambdaInstrumentationStrategy.UNSAFE_CLASS + ";", false);
                methodVisitor.visitVarInsn(58, 6);
                methodVisitor.visitVarInsn(25, 6);
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodHandles$Lookup", "lookupClass", "()Ljava/lang/Class;", false);
                methodVisitor.visitMethodInsn(184, "java/lang/ClassLoader", "getSystemClassLoader", "()Ljava/lang/ClassLoader;", false);
                methodVisitor.visitLdcInsn("net.bytebuddy.agent.builder.LambdaFactory");
                methodVisitor.visitMethodInsn(182, "java/lang/ClassLoader", "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;", false);
                methodVisitor.visitLdcInsn(TypeProxy.REFLECTION_METHOD);
                methodVisitor.visitIntInsn(16, 9);
                methodVisitor.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(3);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(4);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/lang/String;"));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(5);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(6);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(7);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(8);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 6);
                methodVisitor.visitFieldInsn(178, "java/lang/Boolean", "TYPE", "Ljava/lang/Class;");
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 7);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/util/List;"));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 8);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/util/List;"));
                methodVisitor.visitInsn(83);
                methodVisitor.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "getDeclaredMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
                methodVisitor.visitInsn(1);
                methodVisitor.visitIntInsn(16, 9);
                methodVisitor.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(3);
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(4);
                methodVisitor.visitVarInsn(25, 1);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(5);
                methodVisitor.visitVarInsn(25, 2);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(6);
                methodVisitor.visitVarInsn(25, 3);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(7);
                methodVisitor.visitVarInsn(25, 4);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(8);
                methodVisitor.visitVarInsn(25, 5);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 6);
                methodVisitor.visitInsn(3);
                methodVisitor.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 7);
                methodVisitor.visitMethodInsn(184, "java/util/Collections", "emptyList", "()Ljava/util/List;", false);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 8);
                methodVisitor.visitMethodInsn(184, "java/util/Collections", "emptyList", "()Ljava/util/List;", false);
                methodVisitor.visitInsn(83);
                methodVisitor.visitMethodInsn(182, "java/lang/reflect/Method", "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
                methodVisitor.visitTypeInsn(192, "[B");
                methodVisitor.visitInsn(1);
                methodVisitor.visitMethodInsn(182, LambdaInstrumentationStrategy.UNSAFE_CLASS, "defineAnonymousClass", "(Ljava/lang/Class;[B[Ljava/lang/Object;)Ljava/lang/Class;", false);
                methodVisitor.visitVarInsn(58, 7);
                methodVisitor.visitVarInsn(25, 6);
                methodVisitor.visitVarInsn(25, 7);
                methodVisitor.visitMethodInsn(182, LambdaInstrumentationStrategy.UNSAFE_CLASS, "ensureClassInitialized", "(Ljava/lang/Class;)V", false);
                methodVisitor.visitVarInsn(25, 2);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodType", "parameterCount", "()I", false);
                Label label = new Label();
                methodVisitor.visitJumpInsn(154, label);
                methodVisitor.visitTypeInsn(187, "java/lang/invoke/ConstantCallSite");
                methodVisitor.visitInsn(89);
                methodVisitor.visitVarInsn(25, 2);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodType", "returnType", "()Ljava/lang/Class;", false);
                methodVisitor.visitVarInsn(25, 7);
                methodVisitor.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "getDeclaredConstructors", "()[Ljava/lang/reflect/Constructor;", false);
                methodVisitor.visitInsn(3);
                methodVisitor.visitInsn(50);
                methodVisitor.visitInsn(3);
                methodVisitor.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                methodVisitor.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CONSTRUCTOR_INTERNAL_NAME, TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_DESCRIPTOR, false);
                methodVisitor.visitMethodInsn(184, "java/lang/invoke/MethodHandles", "constant", "(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/invoke/MethodHandle;", false);
                methodVisitor.visitMethodInsn(183, "java/lang/invoke/ConstantCallSite", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/invoke/MethodHandle;)V", false);
                Label label2 = new Label();
                methodVisitor.visitJumpInsn(167, label2);
                methodVisitor.visitLabel(label);
                methodVisitor.visitFrame(1, 2, new Object[]{LambdaInstrumentationStrategy.UNSAFE_CLASS, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME}, 0, null);
                methodVisitor.visitTypeInsn(187, "java/lang/invoke/ConstantCallSite");
                methodVisitor.visitInsn(89);
                methodVisitor.visitFieldInsn(178, "java/lang/invoke/MethodHandles$Lookup", "IMPL_LOOKUP", "Ljava/lang/invoke/MethodHandles$Lookup;");
                methodVisitor.visitVarInsn(25, 7);
                methodVisitor.visitLdcInsn("get$Lambda");
                methodVisitor.visitVarInsn(25, 2);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodHandles$Lookup", "findStatic", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/MethodHandle;", false);
                methodVisitor.visitMethodInsn(183, "java/lang/invoke/ConstantCallSite", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/invoke/MethodHandle;)V", false);
                methodVisitor.visitLabel(label2);
                methodVisitor.visitFrame(4, 0, null, 1, new Object[]{"java/lang/invoke/CallSite"});
                methodVisitor.visitInsn(176);
                methodVisitor.visitMaxs(8, 8);
                methodVisitor.visitEnd();
                return LambdaInstrumentationStrategy.IGNORE_ORIGINAL;
            }
        }

        protected enum AlternativeMetaFactoryRedirection implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
            INSTANCE;

            @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper
            public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
                methodVisitor.visitCode();
                methodVisitor.visitVarInsn(25, 3);
                methodVisitor.visitInsn(6);
                methodVisitor.visitInsn(50);
                methodVisitor.visitTypeInsn(192, "java/lang/Integer");
                methodVisitor.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I", false);
                methodVisitor.visitVarInsn(54, 4);
                methodVisitor.visitInsn(7);
                methodVisitor.visitVarInsn(54, 5);
                methodVisitor.visitVarInsn(21, 4);
                methodVisitor.visitInsn(5);
                methodVisitor.visitInsn(126);
                Label label = new Label();
                methodVisitor.visitJumpInsn(153, label);
                methodVisitor.visitVarInsn(25, 3);
                methodVisitor.visitVarInsn(21, 5);
                methodVisitor.visitIincInsn(5, 1);
                methodVisitor.visitInsn(50);
                methodVisitor.visitTypeInsn(192, "java/lang/Integer");
                methodVisitor.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I", false);
                methodVisitor.visitVarInsn(54, 7);
                methodVisitor.visitVarInsn(21, 7);
                methodVisitor.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME);
                methodVisitor.visitVarInsn(58, 6);
                methodVisitor.visitVarInsn(25, 3);
                methodVisitor.visitVarInsn(21, 5);
                methodVisitor.visitVarInsn(25, 6);
                methodVisitor.visitInsn(3);
                methodVisitor.visitVarInsn(21, 7);
                methodVisitor.visitMethodInsn(184, "java/lang/System", "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V", false);
                methodVisitor.visitVarInsn(21, 5);
                methodVisitor.visitVarInsn(21, 7);
                methodVisitor.visitInsn(96);
                methodVisitor.visitVarInsn(54, 5);
                Label label2 = new Label();
                methodVisitor.visitJumpInsn(167, label2);
                methodVisitor.visitLabel(label);
                methodVisitor.visitFrame(1, 2, new Object[]{Opcodes.INTEGER, Opcodes.INTEGER}, 0, null);
                methodVisitor.visitInsn(3);
                methodVisitor.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME);
                methodVisitor.visitVarInsn(58, 6);
                methodVisitor.visitLabel(label2);
                methodVisitor.visitFrame(1, 1, new Object[]{"[Ljava/lang/Class;"}, 0, null);
                methodVisitor.visitVarInsn(21, 4);
                methodVisitor.visitInsn(7);
                methodVisitor.visitInsn(126);
                Label label3 = new Label();
                methodVisitor.visitJumpInsn(153, label3);
                methodVisitor.visitVarInsn(25, 3);
                methodVisitor.visitVarInsn(21, 5);
                methodVisitor.visitIincInsn(5, 1);
                methodVisitor.visitInsn(50);
                methodVisitor.visitTypeInsn(192, "java/lang/Integer");
                methodVisitor.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I", false);
                methodVisitor.visitVarInsn(54, 8);
                methodVisitor.visitVarInsn(21, 8);
                methodVisitor.visitTypeInsn(189, "java/lang/invoke/MethodType");
                methodVisitor.visitVarInsn(58, 7);
                methodVisitor.visitVarInsn(25, 3);
                methodVisitor.visitVarInsn(21, 5);
                methodVisitor.visitVarInsn(25, 7);
                methodVisitor.visitInsn(3);
                methodVisitor.visitVarInsn(21, 8);
                methodVisitor.visitMethodInsn(184, "java/lang/System", "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V", false);
                Label label4 = new Label();
                methodVisitor.visitJumpInsn(167, label4);
                methodVisitor.visitLabel(label3);
                methodVisitor.visitFrame(3, 0, null, 0, null);
                methodVisitor.visitInsn(3);
                methodVisitor.visitTypeInsn(189, "java/lang/invoke/MethodType");
                methodVisitor.visitVarInsn(58, 7);
                methodVisitor.visitLabel(label4);
                methodVisitor.visitFrame(1, 1, new Object[]{"[Ljava/lang/invoke/MethodType;"}, 0, null);
                methodVisitor.visitMethodInsn(184, LambdaInstrumentationStrategy.UNSAFE_CLASS, "getUnsafe", "()L" + LambdaInstrumentationStrategy.UNSAFE_CLASS + ";", false);
                methodVisitor.visitVarInsn(58, 8);
                methodVisitor.visitVarInsn(25, 8);
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodHandles$Lookup", "lookupClass", "()Ljava/lang/Class;", false);
                methodVisitor.visitMethodInsn(184, "java/lang/ClassLoader", "getSystemClassLoader", "()Ljava/lang/ClassLoader;", false);
                methodVisitor.visitLdcInsn("net.bytebuddy.agent.builder.LambdaFactory");
                methodVisitor.visitMethodInsn(182, "java/lang/ClassLoader", "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;", false);
                methodVisitor.visitLdcInsn(TypeProxy.REFLECTION_METHOD);
                methodVisitor.visitIntInsn(16, 9);
                methodVisitor.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(3);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(4);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/lang/String;"));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(5);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(6);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(7);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(8);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 6);
                methodVisitor.visitFieldInsn(178, "java/lang/Boolean", "TYPE", "Ljava/lang/Class;");
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 7);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/util/List;"));
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 8);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/util/List;"));
                methodVisitor.visitInsn(83);
                methodVisitor.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "getDeclaredMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
                methodVisitor.visitInsn(1);
                methodVisitor.visitIntInsn(16, 9);
                methodVisitor.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(3);
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(4);
                methodVisitor.visitVarInsn(25, 1);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(5);
                methodVisitor.visitVarInsn(25, 2);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(6);
                methodVisitor.visitVarInsn(25, 3);
                methodVisitor.visitInsn(3);
                methodVisitor.visitInsn(50);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(7);
                methodVisitor.visitVarInsn(25, 3);
                methodVisitor.visitInsn(4);
                methodVisitor.visitInsn(50);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitInsn(8);
                methodVisitor.visitVarInsn(25, 3);
                methodVisitor.visitInsn(5);
                methodVisitor.visitInsn(50);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 6);
                methodVisitor.visitVarInsn(21, 4);
                methodVisitor.visitInsn(4);
                methodVisitor.visitInsn(126);
                Label label5 = new Label();
                methodVisitor.visitJumpInsn(153, label5);
                methodVisitor.visitInsn(4);
                Label label6 = new Label();
                methodVisitor.visitJumpInsn(167, label6);
                methodVisitor.visitLabel(label5);
                methodVisitor.visitFrame(0, 9, new Object[]{"java/lang/invoke/MethodHandles$Lookup", "java/lang/String", "java/lang/invoke/MethodType", "[Ljava/lang/Object;", Opcodes.INTEGER, Opcodes.INTEGER, "[Ljava/lang/Class;", "[Ljava/lang/invoke/MethodType;", LambdaInstrumentationStrategy.UNSAFE_CLASS}, 7, new Object[]{LambdaInstrumentationStrategy.UNSAFE_CLASS, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "java/lang/reflect/Method", Opcodes.NULL, "[Ljava/lang/Object;", "[Ljava/lang/Object;", Opcodes.INTEGER});
                methodVisitor.visitInsn(3);
                methodVisitor.visitLabel(label6);
                methodVisitor.visitFrame(0, 9, new Object[]{"java/lang/invoke/MethodHandles$Lookup", "java/lang/String", "java/lang/invoke/MethodType", "[Ljava/lang/Object;", Opcodes.INTEGER, Opcodes.INTEGER, "[Ljava/lang/Class;", "[Ljava/lang/invoke/MethodType;", LambdaInstrumentationStrategy.UNSAFE_CLASS}, 8, new Object[]{LambdaInstrumentationStrategy.UNSAFE_CLASS, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "java/lang/reflect/Method", Opcodes.NULL, "[Ljava/lang/Object;", "[Ljava/lang/Object;", Opcodes.INTEGER, Opcodes.INTEGER});
                methodVisitor.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 7);
                methodVisitor.visitVarInsn(25, 6);
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "asList", "([Ljava/lang/Object;)Ljava/util/List;", false);
                methodVisitor.visitInsn(83);
                methodVisitor.visitInsn(89);
                methodVisitor.visitIntInsn(16, 8);
                methodVisitor.visitVarInsn(25, 7);
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "asList", "([Ljava/lang/Object;)Ljava/util/List;", false);
                methodVisitor.visitInsn(83);
                methodVisitor.visitMethodInsn(182, "java/lang/reflect/Method", "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
                methodVisitor.visitTypeInsn(192, "[B");
                methodVisitor.visitInsn(1);
                methodVisitor.visitMethodInsn(182, LambdaInstrumentationStrategy.UNSAFE_CLASS, "defineAnonymousClass", "(Ljava/lang/Class;[B[Ljava/lang/Object;)Ljava/lang/Class;", false);
                methodVisitor.visitVarInsn(58, 9);
                methodVisitor.visitVarInsn(25, 8);
                methodVisitor.visitVarInsn(25, 9);
                methodVisitor.visitMethodInsn(182, LambdaInstrumentationStrategy.UNSAFE_CLASS, "ensureClassInitialized", "(Ljava/lang/Class;)V", false);
                methodVisitor.visitVarInsn(25, 2);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodType", "parameterCount", "()I", false);
                Label label7 = new Label();
                methodVisitor.visitJumpInsn(154, label7);
                methodVisitor.visitTypeInsn(187, "java/lang/invoke/ConstantCallSite");
                methodVisitor.visitInsn(89);
                methodVisitor.visitVarInsn(25, 2);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodType", "returnType", "()Ljava/lang/Class;", false);
                methodVisitor.visitVarInsn(25, 9);
                methodVisitor.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "getDeclaredConstructors", "()[Ljava/lang/reflect/Constructor;", false);
                methodVisitor.visitInsn(3);
                methodVisitor.visitInsn(50);
                methodVisitor.visitInsn(3);
                methodVisitor.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                methodVisitor.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CONSTRUCTOR_INTERNAL_NAME, TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_DESCRIPTOR, false);
                methodVisitor.visitMethodInsn(184, "java/lang/invoke/MethodHandles", "constant", "(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/invoke/MethodHandle;", false);
                methodVisitor.visitMethodInsn(183, "java/lang/invoke/ConstantCallSite", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/invoke/MethodHandle;)V", false);
                Label label8 = new Label();
                methodVisitor.visitJumpInsn(167, label8);
                methodVisitor.visitLabel(label7);
                methodVisitor.visitFrame(1, 1, new Object[]{TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME}, 0, null);
                methodVisitor.visitTypeInsn(187, "java/lang/invoke/ConstantCallSite");
                methodVisitor.visitInsn(89);
                methodVisitor.visitFieldInsn(178, "java/lang/invoke/MethodHandles$Lookup", "IMPL_LOOKUP", "Ljava/lang/invoke/MethodHandles$Lookup;");
                methodVisitor.visitVarInsn(25, 9);
                methodVisitor.visitLdcInsn("get$Lambda");
                methodVisitor.visitVarInsn(25, 2);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodHandles$Lookup", "findStatic", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/MethodHandle;", false);
                methodVisitor.visitMethodInsn(183, "java/lang/invoke/ConstantCallSite", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/invoke/MethodHandle;)V", false);
                methodVisitor.visitLabel(label8);
                methodVisitor.visitFrame(4, 0, null, 1, new Object[]{"java/lang/invoke/CallSite"});
                methodVisitor.visitInsn(176);
                methodVisitor.visitMaxs(9, 10);
                methodVisitor.visitEnd();
                return LambdaInstrumentationStrategy.IGNORE_ORIGINAL;
            }
        }
    }

    public static class Default implements AgentBuilder {
        private static final CircularityLock DEFAULT_LOCK = new CircularityLock.Default();
        private static final String INSTALLER_TYPE = "net.bytebuddy.agent.Installer";
        private static final String INSTRUMENTATION_GETTER = "getInstrumentation";
        private static final Class<?> NO_LOADED_TYPE = null;
        private static final byte[] NO_TRANSFORMATION = null;
        private static final Object STATIC_MEMBER = null;
        protected final BootstrapInjectionStrategy bootstrapInjectionStrategy;
        protected final ByteBuddy byteBuddy;
        protected final CircularityLock circularityLock;
        protected final DescriptionStrategy descriptionStrategy;
        protected final FallbackStrategy fallbackStrategy;
        protected final RawMatcher ignoredTypeMatcher;
        protected final InitializationStrategy initializationStrategy;
        protected final InstallationListener installationListener;
        protected final LambdaInstrumentationStrategy lambdaInstrumentationStrategy;
        protected final Listener listener;
        protected final LocationStrategy locationStrategy;
        protected final NativeMethodStrategy nativeMethodStrategy;
        protected final PoolStrategy poolStrategy;
        protected final RedefinitionStrategy.BatchAllocator redefinitionBatchAllocator;
        protected final RedefinitionStrategy.DiscoveryStrategy redefinitionDiscoveryStrategy;
        protected final RedefinitionStrategy.Listener redefinitionListener;
        protected final RedefinitionStrategy.ResubmissionStrategy redefinitionResubmissionStrategy;
        protected final RedefinitionStrategy redefinitionStrategy;
        protected final Transformation transformation;
        protected final TypeStrategy typeStrategy;

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
            Default r5 = (Default) obj;
            if (!r5.canEqual(this)) {
                return false;
            }
            ByteBuddy byteBuddy = this.byteBuddy;
            ByteBuddy byteBuddy2 = r5.byteBuddy;
            if (byteBuddy != null ? !byteBuddy.equals(byteBuddy2) : byteBuddy2 != null) {
                return false;
            }
            Listener listener = this.listener;
            Listener listener2 = r5.listener;
            if (listener != null ? !listener.equals(listener2) : listener2 != null) {
                return false;
            }
            CircularityLock circularityLock = this.circularityLock;
            CircularityLock circularityLock2 = r5.circularityLock;
            if (circularityLock != null ? !circularityLock.equals(circularityLock2) : circularityLock2 != null) {
                return false;
            }
            PoolStrategy poolStrategy = this.poolStrategy;
            PoolStrategy poolStrategy2 = r5.poolStrategy;
            if (poolStrategy != null ? !poolStrategy.equals(poolStrategy2) : poolStrategy2 != null) {
                return false;
            }
            TypeStrategy typeStrategy = this.typeStrategy;
            TypeStrategy typeStrategy2 = r5.typeStrategy;
            if (typeStrategy != null ? !typeStrategy.equals(typeStrategy2) : typeStrategy2 != null) {
                return false;
            }
            LocationStrategy locationStrategy = this.locationStrategy;
            LocationStrategy locationStrategy2 = r5.locationStrategy;
            if (locationStrategy != null ? !locationStrategy.equals(locationStrategy2) : locationStrategy2 != null) {
                return false;
            }
            NativeMethodStrategy nativeMethodStrategy = this.nativeMethodStrategy;
            NativeMethodStrategy nativeMethodStrategy2 = r5.nativeMethodStrategy;
            if (nativeMethodStrategy != null ? !nativeMethodStrategy.equals(nativeMethodStrategy2) : nativeMethodStrategy2 != null) {
                return false;
            }
            InitializationStrategy initializationStrategy = this.initializationStrategy;
            InitializationStrategy initializationStrategy2 = r5.initializationStrategy;
            if (initializationStrategy != null ? !initializationStrategy.equals(initializationStrategy2) : initializationStrategy2 != null) {
                return false;
            }
            RedefinitionStrategy redefinitionStrategy = this.redefinitionStrategy;
            RedefinitionStrategy redefinitionStrategy2 = r5.redefinitionStrategy;
            if (redefinitionStrategy != null ? !redefinitionStrategy.equals(redefinitionStrategy2) : redefinitionStrategy2 != null) {
                return false;
            }
            RedefinitionStrategy.DiscoveryStrategy discoveryStrategy = this.redefinitionDiscoveryStrategy;
            RedefinitionStrategy.DiscoveryStrategy discoveryStrategy2 = r5.redefinitionDiscoveryStrategy;
            if (discoveryStrategy != null ? !discoveryStrategy.equals(discoveryStrategy2) : discoveryStrategy2 != null) {
                return false;
            }
            RedefinitionStrategy.BatchAllocator batchAllocator = this.redefinitionBatchAllocator;
            RedefinitionStrategy.BatchAllocator batchAllocator2 = r5.redefinitionBatchAllocator;
            if (batchAllocator != null ? !batchAllocator.equals(batchAllocator2) : batchAllocator2 != null) {
                return false;
            }
            RedefinitionStrategy.Listener listener3 = this.redefinitionListener;
            RedefinitionStrategy.Listener listener4 = r5.redefinitionListener;
            if (listener3 != null ? !listener3.equals(listener4) : listener4 != null) {
                return false;
            }
            RedefinitionStrategy.ResubmissionStrategy resubmissionStrategy = this.redefinitionResubmissionStrategy;
            RedefinitionStrategy.ResubmissionStrategy resubmissionStrategy2 = r5.redefinitionResubmissionStrategy;
            if (resubmissionStrategy != null ? !resubmissionStrategy.equals(resubmissionStrategy2) : resubmissionStrategy2 != null) {
                return false;
            }
            BootstrapInjectionStrategy bootstrapInjectionStrategy = this.bootstrapInjectionStrategy;
            BootstrapInjectionStrategy bootstrapInjectionStrategy2 = r5.bootstrapInjectionStrategy;
            if (bootstrapInjectionStrategy != null ? !bootstrapInjectionStrategy.equals(bootstrapInjectionStrategy2) : bootstrapInjectionStrategy2 != null) {
                return false;
            }
            LambdaInstrumentationStrategy lambdaInstrumentationStrategy = this.lambdaInstrumentationStrategy;
            LambdaInstrumentationStrategy lambdaInstrumentationStrategy2 = r5.lambdaInstrumentationStrategy;
            if (lambdaInstrumentationStrategy != null ? !lambdaInstrumentationStrategy.equals(lambdaInstrumentationStrategy2) : lambdaInstrumentationStrategy2 != null) {
                return false;
            }
            DescriptionStrategy descriptionStrategy = this.descriptionStrategy;
            DescriptionStrategy descriptionStrategy2 = r5.descriptionStrategy;
            if (descriptionStrategy != null ? !descriptionStrategy.equals(descriptionStrategy2) : descriptionStrategy2 != null) {
                return false;
            }
            FallbackStrategy fallbackStrategy = this.fallbackStrategy;
            FallbackStrategy fallbackStrategy2 = r5.fallbackStrategy;
            if (fallbackStrategy != null ? !fallbackStrategy.equals(fallbackStrategy2) : fallbackStrategy2 != null) {
                return false;
            }
            InstallationListener installationListener = this.installationListener;
            InstallationListener installationListener2 = r5.installationListener;
            if (installationListener != null ? !installationListener.equals(installationListener2) : installationListener2 != null) {
                return false;
            }
            RawMatcher rawMatcher = this.ignoredTypeMatcher;
            RawMatcher rawMatcher2 = r5.ignoredTypeMatcher;
            if (rawMatcher != null ? !rawMatcher.equals(rawMatcher2) : rawMatcher2 != null) {
                return false;
            }
            Transformation transformation = this.transformation;
            Transformation transformation2 = r5.transformation;
            return transformation != null ? transformation.equals(transformation2) : transformation2 == null;
        }

        public int hashCode() {
            ByteBuddy byteBuddy = this.byteBuddy;
            int iHashCode = byteBuddy == null ? 43 : byteBuddy.hashCode();
            Listener listener = this.listener;
            int iHashCode2 = ((iHashCode + 59) * 59) + (listener == null ? 43 : listener.hashCode());
            CircularityLock circularityLock = this.circularityLock;
            int iHashCode3 = (iHashCode2 * 59) + (circularityLock == null ? 43 : circularityLock.hashCode());
            PoolStrategy poolStrategy = this.poolStrategy;
            int iHashCode4 = (iHashCode3 * 59) + (poolStrategy == null ? 43 : poolStrategy.hashCode());
            TypeStrategy typeStrategy = this.typeStrategy;
            int iHashCode5 = (iHashCode4 * 59) + (typeStrategy == null ? 43 : typeStrategy.hashCode());
            LocationStrategy locationStrategy = this.locationStrategy;
            int iHashCode6 = (iHashCode5 * 59) + (locationStrategy == null ? 43 : locationStrategy.hashCode());
            NativeMethodStrategy nativeMethodStrategy = this.nativeMethodStrategy;
            int iHashCode7 = (iHashCode6 * 59) + (nativeMethodStrategy == null ? 43 : nativeMethodStrategy.hashCode());
            InitializationStrategy initializationStrategy = this.initializationStrategy;
            int iHashCode8 = (iHashCode7 * 59) + (initializationStrategy == null ? 43 : initializationStrategy.hashCode());
            RedefinitionStrategy redefinitionStrategy = this.redefinitionStrategy;
            int iHashCode9 = (iHashCode8 * 59) + (redefinitionStrategy == null ? 43 : redefinitionStrategy.hashCode());
            RedefinitionStrategy.DiscoveryStrategy discoveryStrategy = this.redefinitionDiscoveryStrategy;
            int iHashCode10 = (iHashCode9 * 59) + (discoveryStrategy == null ? 43 : discoveryStrategy.hashCode());
            RedefinitionStrategy.BatchAllocator batchAllocator = this.redefinitionBatchAllocator;
            int iHashCode11 = (iHashCode10 * 59) + (batchAllocator == null ? 43 : batchAllocator.hashCode());
            RedefinitionStrategy.Listener listener2 = this.redefinitionListener;
            int iHashCode12 = (iHashCode11 * 59) + (listener2 == null ? 43 : listener2.hashCode());
            RedefinitionStrategy.ResubmissionStrategy resubmissionStrategy = this.redefinitionResubmissionStrategy;
            int iHashCode13 = (iHashCode12 * 59) + (resubmissionStrategy == null ? 43 : resubmissionStrategy.hashCode());
            BootstrapInjectionStrategy bootstrapInjectionStrategy = this.bootstrapInjectionStrategy;
            int iHashCode14 = (iHashCode13 * 59) + (bootstrapInjectionStrategy == null ? 43 : bootstrapInjectionStrategy.hashCode());
            LambdaInstrumentationStrategy lambdaInstrumentationStrategy = this.lambdaInstrumentationStrategy;
            int iHashCode15 = (iHashCode14 * 59) + (lambdaInstrumentationStrategy == null ? 43 : lambdaInstrumentationStrategy.hashCode());
            DescriptionStrategy descriptionStrategy = this.descriptionStrategy;
            int iHashCode16 = (iHashCode15 * 59) + (descriptionStrategy == null ? 43 : descriptionStrategy.hashCode());
            FallbackStrategy fallbackStrategy = this.fallbackStrategy;
            int iHashCode17 = (iHashCode16 * 59) + (fallbackStrategy == null ? 43 : fallbackStrategy.hashCode());
            InstallationListener installationListener = this.installationListener;
            int iHashCode18 = (iHashCode17 * 59) + (installationListener == null ? 43 : installationListener.hashCode());
            RawMatcher rawMatcher = this.ignoredTypeMatcher;
            int iHashCode19 = (iHashCode18 * 59) + (rawMatcher == null ? 43 : rawMatcher.hashCode());
            Transformation transformation = this.transformation;
            return (iHashCode19 * 59) + (transformation != null ? transformation.hashCode() : 43);
        }

        public Default() {
            this(new ByteBuddy());
        }

        public Default(ByteBuddy byteBuddy) {
            this(byteBuddy, Listener.NoOp.INSTANCE, DEFAULT_LOCK, PoolStrategy.Default.FAST, TypeStrategy.Default.REBASE, LocationStrategy.ForClassLoader.STRONG, NativeMethodStrategy.Disabled.INSTANCE, new InitializationStrategy.SelfInjection.Split(), RedefinitionStrategy.DISABLED, RedefinitionStrategy.DiscoveryStrategy.SinglePass.INSTANCE, RedefinitionStrategy.BatchAllocator.ForTotal.INSTANCE, RedefinitionStrategy.Listener.NoOp.INSTANCE, RedefinitionStrategy.ResubmissionStrategy.Disabled.INSTANCE, BootstrapInjectionStrategy.Disabled.INSTANCE, LambdaInstrumentationStrategy.DISABLED, DescriptionStrategy.Default.HYBRID, FallbackStrategy.ByThrowableType.ofOptionalTypes(), InstallationListener.NoOp.INSTANCE, new RawMatcher.Disjunction(new RawMatcher.ForElementMatchers(ElementMatchers.any(), ElementMatchers.isBootstrapClassLoader()), new RawMatcher.ForElementMatchers(ElementMatchers.nameStartsWith("net.bytebuddy.").or(ElementMatchers.nameStartsWith("sun.reflect.")).or(ElementMatchers.isSynthetic()))), Transformation.Ignored.INSTANCE);
        }

        protected Default(ByteBuddy byteBuddy, Listener listener, CircularityLock circularityLock, PoolStrategy poolStrategy, TypeStrategy typeStrategy, LocationStrategy locationStrategy, NativeMethodStrategy nativeMethodStrategy, InitializationStrategy initializationStrategy, RedefinitionStrategy redefinitionStrategy, RedefinitionStrategy.DiscoveryStrategy discoveryStrategy, RedefinitionStrategy.BatchAllocator batchAllocator, RedefinitionStrategy.Listener listener2, RedefinitionStrategy.ResubmissionStrategy resubmissionStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, InstallationListener installationListener, RawMatcher rawMatcher, Transformation transformation) {
            this.byteBuddy = byteBuddy;
            this.listener = listener;
            this.circularityLock = circularityLock;
            this.poolStrategy = poolStrategy;
            this.typeStrategy = typeStrategy;
            this.locationStrategy = locationStrategy;
            this.nativeMethodStrategy = nativeMethodStrategy;
            this.initializationStrategy = initializationStrategy;
            this.redefinitionStrategy = redefinitionStrategy;
            this.redefinitionDiscoveryStrategy = discoveryStrategy;
            this.redefinitionBatchAllocator = batchAllocator;
            this.redefinitionListener = listener2;
            this.redefinitionResubmissionStrategy = resubmissionStrategy;
            this.bootstrapInjectionStrategy = bootstrapInjectionStrategy;
            this.lambdaInstrumentationStrategy = lambdaInstrumentationStrategy;
            this.descriptionStrategy = descriptionStrategy;
            this.fallbackStrategy = fallbackStrategy;
            this.installationListener = installationListener;
            this.ignoredTypeMatcher = rawMatcher;
            this.transformation = transformation;
        }

        public static AgentBuilder of(Plugin... pluginArr) {
            return of((List<? extends Plugin>) Arrays.asList(pluginArr));
        }

        public static AgentBuilder of(List<? extends Plugin> list) {
            return of(EntryPoint.Default.REBASE, list);
        }

        public static AgentBuilder of(EntryPoint entryPoint, Plugin... pluginArr) {
            return of(entryPoint, (List<? extends Plugin>) Arrays.asList(pluginArr));
        }

        public static AgentBuilder of(EntryPoint entryPoint, List<? extends Plugin> list) {
            AgentBuilder agentBuilderWith = new Default(entryPoint.getByteBuddy()).with(new TypeStrategy.ForBuildEntryPoint(entryPoint));
            for (Plugin plugin : list) {
                agentBuilderWith = agentBuilderWith.type(plugin).transform(new Transformer.ForBuildPlugin(plugin));
            }
            return agentBuilderWith;
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(ByteBuddy byteBuddy) {
            return new Default(byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(Listener listener) {
            return new Default(this.byteBuddy, new Listener.Compound(this.listener, listener), this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(CircularityLock circularityLock) {
            return new Default(this.byteBuddy, this.listener, circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(TypeStrategy typeStrategy) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(PoolStrategy poolStrategy) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(LocationStrategy locationStrategy) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder enableNativeMethodPrefix(String str) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, NativeMethodStrategy.ForPrefix.of(str), this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder disableNativeMethodPrefix() {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, NativeMethodStrategy.Disabled.INSTANCE, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public RedefinitionListenable.WithoutBatchStrategy with(RedefinitionStrategy redefinitionStrategy) {
            return new Redefining(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, redefinitionStrategy, RedefinitionStrategy.DiscoveryStrategy.SinglePass.INSTANCE, RedefinitionStrategy.BatchAllocator.ForTotal.INSTANCE, RedefinitionStrategy.Listener.NoOp.INSTANCE, RedefinitionStrategy.ResubmissionStrategy.Disabled.INSTANCE, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(InitializationStrategy initializationStrategy) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(LambdaInstrumentationStrategy lambdaInstrumentationStrategy) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(DescriptionStrategy descriptionStrategy) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(FallbackStrategy fallbackStrategy) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder with(InstallationListener installationListener) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, new InstallationListener.Compound(this.installationListener, installationListener), this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder enableBootstrapInjection(Instrumentation instrumentation, File file) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, new BootstrapInjectionStrategy.Enabled(file, instrumentation), this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder enableUnsafeBootstrapInjection() {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, BootstrapInjectionStrategy.Unsafe.INSTANCE, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder disableBootstrapInjection() {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, BootstrapInjectionStrategy.Disabled.INSTANCE, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder disableClassFormatChanges() {
            return new Default(this.byteBuddy.with(Implementation.Context.Disabled.Factory.INSTANCE), this.listener, this.circularityLock, this.poolStrategy, TypeStrategy.Default.REDEFINE_FROZEN, this.locationStrategy, NativeMethodStrategy.Disabled.INSTANCE, InitializationStrategy.NoOp.INSTANCE, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Class<?>... clsArr) {
            return JavaModule.isSupported() ? with(Listener.ModuleReadEdgeCompleting.of(instrumentation, false, clsArr)) : this;
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, JavaModule... javaModuleArr) {
            return assureReadEdgeTo(instrumentation, Arrays.asList(javaModuleArr));
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection) {
            return with(new Listener.ModuleReadEdgeCompleting(instrumentation, false, new HashSet(collection)));
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Class<?>... clsArr) {
            return JavaModule.isSupported() ? with(Listener.ModuleReadEdgeCompleting.of(instrumentation, true, clsArr)) : this;
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, JavaModule... javaModuleArr) {
            return assureReadEdgeFromAndTo(instrumentation, Arrays.asList(javaModuleArr));
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection) {
            return with(new Listener.ModuleReadEdgeCompleting(instrumentation, true, new HashSet(collection)));
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public Identified.Narrowable type(RawMatcher rawMatcher) {
            return new Transforming(rawMatcher, Transformer.NoOp.INSTANCE, false);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher) {
            return type(elementMatcher, ElementMatchers.any());
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
            return type(elementMatcher, elementMatcher2, ElementMatchers.any());
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
            return type(new RawMatcher.ForElementMatchers(elementMatcher, elementMatcher2, ElementMatchers.not(ElementMatchers.supportsModules()).or(elementMatcher3)));
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher) {
            return ignore(elementMatcher, ElementMatchers.any());
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
            return ignore(elementMatcher, elementMatcher2, ElementMatchers.any());
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
            return ignore(new RawMatcher.ForElementMatchers(elementMatcher, elementMatcher2, ElementMatchers.not(ElementMatchers.supportsModules()).or(elementMatcher3)));
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public Ignored ignore(RawMatcher rawMatcher) {
            return new Ignoring(rawMatcher);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public ResettableClassFileTransformer makeRaw() {
            return makeRaw(this.listener, InstallationListener.NoOp.INSTANCE);
        }

        private ResettableClassFileTransformer makeRaw(Listener listener, InstallationListener installationListener) {
            return ExecutingTransformer.FACTORY.make(this.byteBuddy, listener, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, installationListener, this.ignoredTypeMatcher, this.transformation, this.circularityLock);
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public ResettableClassFileTransformer installOn(Instrumentation instrumentation) throws Throwable {
            ResettableClassFileTransformer resettableClassFileTransformer;
            Instrumentation instrumentation2;
            Default r1 = this;
            if (!r1.circularityLock.acquire()) {
                throw new IllegalStateException("Could not acquire the circularity lock upon installation.");
            }
            try {
                RedefinitionStrategy.ResubmissionStrategy.Installation installationApply = r1.redefinitionResubmissionStrategy.apply(instrumentation, r1.locationStrategy, r1.listener, r1.installationListener, r1.circularityLock, new RawMatcher.Conjunction(new RawMatcher.Inversion(r1.ignoredTypeMatcher), r1.transformation), r1.redefinitionStrategy, r1.redefinitionBatchAllocator, r1.redefinitionListener);
                ResettableClassFileTransformer resettableClassFileTransformerMakeRaw = r1.makeRaw(installationApply.getListener(), installationApply.getInstallationListener());
                installationApply.getInstallationListener().onBeforeInstall(instrumentation, resettableClassFileTransformerMakeRaw);
                try {
                    instrumentation.addTransformer(resettableClassFileTransformerMakeRaw, r1.redefinitionStrategy.isRetransforming());
                    if (r1.nativeMethodStrategy.isEnabled(instrumentation)) {
                        instrumentation.setNativeMethodPrefix(resettableClassFileTransformerMakeRaw, r1.nativeMethodStrategy.getPrefix());
                    }
                    r1.lambdaInstrumentationStrategy.apply(r1.byteBuddy, instrumentation, resettableClassFileTransformerMakeRaw);
                    if (r1.redefinitionStrategy.isEnabled()) {
                        RedefinitionStrategy redefinitionStrategy = r1.redefinitionStrategy;
                        Listener listener = installationApply.getListener();
                        CircularityLock circularityLock = r1.circularityLock;
                        PoolStrategy poolStrategy = r1.poolStrategy;
                        LocationStrategy locationStrategy = r1.locationStrategy;
                        RedefinitionStrategy.DiscoveryStrategy discoveryStrategy = r1.redefinitionDiscoveryStrategy;
                        RedefinitionStrategy.BatchAllocator batchAllocator = r1.redefinitionBatchAllocator;
                        RedefinitionStrategy.Listener listener2 = r1.redefinitionListener;
                        LambdaInstrumentationStrategy lambdaInstrumentationStrategy = r1.lambdaInstrumentationStrategy;
                        DescriptionStrategy descriptionStrategy = r1.descriptionStrategy;
                        FallbackStrategy fallbackStrategy = r1.fallbackStrategy;
                        Transformation transformation = r1.transformation;
                        RawMatcher rawMatcher = r1.ignoredTypeMatcher;
                        resettableClassFileTransformer = resettableClassFileTransformerMakeRaw;
                        try {
                            redefinitionStrategy.apply(instrumentation, listener, circularityLock, poolStrategy, locationStrategy, discoveryStrategy, batchAllocator, listener2, lambdaInstrumentationStrategy, descriptionStrategy, fallbackStrategy, transformation, rawMatcher);
                        } catch (Throwable th) {
                            th = th;
                            try {
                                instrumentation2 = instrumentation;
                                Throwable thOnError = installationApply.getInstallationListener().onError(instrumentation2, resettableClassFileTransformer, th);
                                if (thOnError != null) {
                                    ResettableClassFileTransformer resettableClassFileTransformer2 = resettableClassFileTransformer;
                                    r1 = this;
                                    instrumentation2.removeTransformer(resettableClassFileTransformer2);
                                    throw new IllegalStateException("Could not install class file transformer", thOnError);
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                r1 = this;
                                r1.circularityLock.release();
                                throw th;
                            }
                        }
                    } else {
                        resettableClassFileTransformer = resettableClassFileTransformerMakeRaw;
                    }
                    instrumentation2 = instrumentation;
                } catch (Throwable th3) {
                    th = th3;
                    resettableClassFileTransformer = resettableClassFileTransformerMakeRaw;
                }
                installationApply.getInstallationListener().onInstall(instrumentation2, resettableClassFileTransformer);
                ResettableClassFileTransformer resettableClassFileTransformer3 = resettableClassFileTransformer;
                this.circularityLock.release();
                return resettableClassFileTransformer3;
            } catch (Throwable th4) {
                th = th4;
            }
        }

        @Override // net.bytebuddy.agent.builder.AgentBuilder
        public ResettableClassFileTransformer installOnByteBuddyAgent() {
            try {
                return installOn((Instrumentation) ClassLoader.getSystemClassLoader().loadClass(INSTALLER_TYPE).getMethod(INSTRUMENTATION_GETTER, new Class[0]).invoke(STATIC_MEMBER, new Object[0]));
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new IllegalStateException("The Byte Buddy agent is not installed or not accessible", e2);
            }
        }

        protected interface BootstrapInjectionStrategy {
            ClassInjector make(ProtectionDomain protectionDomain);

            public enum Disabled implements BootstrapInjectionStrategy {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.BootstrapInjectionStrategy
                public ClassInjector make(ProtectionDomain protectionDomain) {
                    throw new IllegalStateException("Injecting classes into the bootstrap class loader was not enabled");
                }
            }

            public enum Unsafe implements BootstrapInjectionStrategy {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.BootstrapInjectionStrategy
                public ClassInjector make(ProtectionDomain protectionDomain) {
                    return new ClassInjector.UsingUnsafe(ClassLoadingStrategy.BOOTSTRAP_LOADER, protectionDomain);
                }

                @Override // java.lang.Enum
                public String toString() {
                    return "AgentBuilder.Default.BootstrapInjectionStrategy.Unsafe." + name();
                }
            }

            public static class Enabled implements BootstrapInjectionStrategy {
                private final File folder;
                private final Instrumentation instrumentation;

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
                    if (file != null ? !file.equals(file2) : file2 != null) {
                        return false;
                    }
                    Instrumentation instrumentation = this.instrumentation;
                    Instrumentation instrumentation2 = enabled.instrumentation;
                    return instrumentation != null ? instrumentation.equals(instrumentation2) : instrumentation2 == null;
                }

                public int hashCode() {
                    File file = this.folder;
                    int iHashCode = file == null ? 43 : file.hashCode();
                    Instrumentation instrumentation = this.instrumentation;
                    return ((iHashCode + 59) * 59) + (instrumentation != null ? instrumentation.hashCode() : 43);
                }

                public Enabled(File file, Instrumentation instrumentation) {
                    this.folder = file;
                    this.instrumentation = instrumentation;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.BootstrapInjectionStrategy
                public ClassInjector make(ProtectionDomain protectionDomain) {
                    return ClassInjector.UsingInstrumentation.of(this.folder, ClassInjector.UsingInstrumentation.Target.BOOTSTRAP, this.instrumentation);
                }
            }
        }

        protected interface NativeMethodStrategy {
            String getPrefix();

            boolean isEnabled(Instrumentation instrumentation);

            MethodNameTransformer resolve();

            public enum Disabled implements NativeMethodStrategy {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.NativeMethodStrategy
                public boolean isEnabled(Instrumentation instrumentation) {
                    return false;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.NativeMethodStrategy
                public MethodNameTransformer resolve() {
                    return MethodNameTransformer.Suffixing.withRandomSuffix();
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.NativeMethodStrategy
                public String getPrefix() {
                    throw new IllegalStateException("A disabled native method strategy does not define a method name prefix");
                }
            }

            public static class ForPrefix implements NativeMethodStrategy {
                private final String prefix;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForPrefix;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForPrefix)) {
                        return false;
                    }
                    ForPrefix forPrefix = (ForPrefix) obj;
                    if (!forPrefix.canEqual(this)) {
                        return false;
                    }
                    String prefix = getPrefix();
                    String prefix2 = forPrefix.getPrefix();
                    return prefix != null ? prefix.equals(prefix2) : prefix2 == null;
                }

                public int hashCode() {
                    String prefix = getPrefix();
                    return 59 + (prefix == null ? 43 : prefix.hashCode());
                }

                protected ForPrefix(String str) {
                    this.prefix = str;
                }

                protected static NativeMethodStrategy of(String str) {
                    if (str.length() == 0) {
                        throw new IllegalArgumentException("A method name prefix must not be the empty string");
                    }
                    return new ForPrefix(str);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.NativeMethodStrategy
                public MethodNameTransformer resolve() {
                    return new MethodNameTransformer.Prefixing(this.prefix);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.NativeMethodStrategy
                public boolean isEnabled(Instrumentation instrumentation) {
                    if (instrumentation.isNativeMethodPrefixSupported()) {
                        return true;
                    }
                    throw new IllegalArgumentException("A prefix for native methods is not supported: " + instrumentation);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.NativeMethodStrategy
                public String getPrefix() {
                    return this.prefix;
                }
            }
        }

        protected interface Transformation extends RawMatcher {
            Resolution resolve(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool);

            public interface Resolution {

                public interface Decoratable extends Resolution {
                    Resolution append(Transformer transformer);
                }

                byte[] apply(InitializationStrategy initializationStrategy, ClassFileLocator classFileLocator, TypeStrategy typeStrategy, ByteBuddy byteBuddy, NativeMethodStrategy nativeMethodStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, AccessControlContext accessControlContext, Listener listener);

                Resolution asDecoratorOf(Resolution resolution);

                Sort getSort();

                Resolution prepend(Decoratable decoratable);

                public enum Sort {
                    TERMINAL(true),
                    DECORATOR(true),
                    UNDEFINED(false);

                    private final boolean alive;

                    Sort(boolean z) {
                        this.alive = z;
                    }

                    protected boolean isAlive() {
                        return this.alive;
                    }
                }

                public static class Unresolved implements Resolution {
                    private final ClassLoader classLoader;
                    private final boolean loaded;
                    private final JavaModule module;
                    private final TypeDescription typeDescription;

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution
                    public Resolution asDecoratorOf(Resolution resolution) {
                        return resolution;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution
                    public Resolution prepend(Decoratable decoratable) {
                        return decoratable;
                    }

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
                        TypeDescription typeDescription = this.typeDescription;
                        TypeDescription typeDescription2 = unresolved.typeDescription;
                        if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                            return false;
                        }
                        ClassLoader classLoader = this.classLoader;
                        ClassLoader classLoader2 = unresolved.classLoader;
                        if (classLoader != null ? !classLoader.equals(classLoader2) : classLoader2 != null) {
                            return false;
                        }
                        JavaModule javaModule = this.module;
                        JavaModule javaModule2 = unresolved.module;
                        if (javaModule != null ? javaModule.equals(javaModule2) : javaModule2 == null) {
                            return this.loaded == unresolved.loaded;
                        }
                        return false;
                    }

                    public int hashCode() {
                        TypeDescription typeDescription = this.typeDescription;
                        int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                        ClassLoader classLoader = this.classLoader;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (classLoader == null ? 43 : classLoader.hashCode());
                        JavaModule javaModule = this.module;
                        return (((iHashCode2 * 59) + (javaModule != null ? javaModule.hashCode() : 43)) * 59) + (this.loaded ? 79 : 97);
                    }

                    protected Unresolved(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                        this.typeDescription = typeDescription;
                        this.classLoader = classLoader;
                        this.module = javaModule;
                        this.loaded = z;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution
                    public Sort getSort() {
                        return Sort.UNDEFINED;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution
                    public byte[] apply(InitializationStrategy initializationStrategy, ClassFileLocator classFileLocator, TypeStrategy typeStrategy, ByteBuddy byteBuddy, NativeMethodStrategy nativeMethodStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, AccessControlContext accessControlContext, Listener listener) {
                        listener.onIgnored(this.typeDescription, this.classLoader, this.module, this.loaded);
                        return Default.NO_TRANSFORMATION;
                    }
                }
            }

            public enum Ignored implements Transformation {
                INSTANCE;

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RawMatcher
                public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                    return false;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation
                public Resolution resolve(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool) {
                    return new Resolution.Unresolved(typeDescription, classLoader, javaModule, z);
                }
            }

            public static class Simple implements Transformation {
                private final boolean decorator;
                private final RawMatcher rawMatcher;
                private final Transformer transformer;

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
                    RawMatcher rawMatcher = this.rawMatcher;
                    RawMatcher rawMatcher2 = simple.rawMatcher;
                    if (rawMatcher != null ? !rawMatcher.equals(rawMatcher2) : rawMatcher2 != null) {
                        return false;
                    }
                    Transformer transformer = this.transformer;
                    Transformer transformer2 = simple.transformer;
                    if (transformer != null ? transformer.equals(transformer2) : transformer2 == null) {
                        return this.decorator == simple.decorator;
                    }
                    return false;
                }

                public int hashCode() {
                    RawMatcher rawMatcher = this.rawMatcher;
                    int iHashCode = rawMatcher == null ? 43 : rawMatcher.hashCode();
                    Transformer transformer = this.transformer;
                    return ((((iHashCode + 59) * 59) + (transformer != null ? transformer.hashCode() : 43)) * 59) + (this.decorator ? 79 : 97);
                }

                protected Simple(RawMatcher rawMatcher, Transformer transformer, boolean z) {
                    this.rawMatcher = rawMatcher;
                    this.transformer = transformer;
                    this.decorator = z;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RawMatcher
                public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                    return this.rawMatcher.matches(typeDescription, classLoader, javaModule, cls, protectionDomain);
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation
                public Resolution resolve(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool) {
                    return matches(typeDescription, classLoader, javaModule, cls, protectionDomain) ? new Resolution(typeDescription, classLoader, javaModule, protectionDomain, z, typePool, this.transformer, this.decorator) : new Resolution.Unresolved(typeDescription, classLoader, javaModule, z);
                }

                protected static class Resolution implements Resolution.Decoratable {
                    private final ClassLoader classLoader;
                    private final boolean decorator;
                    private final boolean loaded;
                    private final JavaModule module;
                    private final ProtectionDomain protectionDomain;
                    private final Transformer transformer;
                    private final TypeDescription typeDescription;
                    private final TypePool typePool;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Resolution;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Resolution)) {
                            return false;
                        }
                        Resolution resolution = (Resolution) obj;
                        if (!resolution.canEqual(this)) {
                            return false;
                        }
                        TypeDescription typeDescription = this.typeDescription;
                        TypeDescription typeDescription2 = resolution.typeDescription;
                        if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                            return false;
                        }
                        ClassLoader classLoader = this.classLoader;
                        ClassLoader classLoader2 = resolution.classLoader;
                        if (classLoader != null ? !classLoader.equals(classLoader2) : classLoader2 != null) {
                            return false;
                        }
                        JavaModule javaModule = this.module;
                        JavaModule javaModule2 = resolution.module;
                        if (javaModule != null ? !javaModule.equals(javaModule2) : javaModule2 != null) {
                            return false;
                        }
                        ProtectionDomain protectionDomain = this.protectionDomain;
                        ProtectionDomain protectionDomain2 = resolution.protectionDomain;
                        if (protectionDomain != null ? !protectionDomain.equals(protectionDomain2) : protectionDomain2 != null) {
                            return false;
                        }
                        if (this.loaded != resolution.loaded) {
                            return false;
                        }
                        TypePool typePool = this.typePool;
                        TypePool typePool2 = resolution.typePool;
                        if (typePool != null ? !typePool.equals(typePool2) : typePool2 != null) {
                            return false;
                        }
                        Transformer transformer = this.transformer;
                        Transformer transformer2 = resolution.transformer;
                        if (transformer != null ? transformer.equals(transformer2) : transformer2 == null) {
                            return this.decorator == resolution.decorator;
                        }
                        return false;
                    }

                    public int hashCode() {
                        TypeDescription typeDescription = this.typeDescription;
                        int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                        ClassLoader classLoader = this.classLoader;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (classLoader == null ? 43 : classLoader.hashCode());
                        JavaModule javaModule = this.module;
                        int iHashCode3 = (iHashCode2 * 59) + (javaModule == null ? 43 : javaModule.hashCode());
                        ProtectionDomain protectionDomain = this.protectionDomain;
                        int iHashCode4 = (((iHashCode3 * 59) + (protectionDomain == null ? 43 : protectionDomain.hashCode())) * 59) + (this.loaded ? 79 : 97);
                        TypePool typePool = this.typePool;
                        int iHashCode5 = (iHashCode4 * 59) + (typePool == null ? 43 : typePool.hashCode());
                        Transformer transformer = this.transformer;
                        return (((iHashCode5 * 59) + (transformer != null ? transformer.hashCode() : 43)) * 59) + (this.decorator ? 79 : 97);
                    }

                    protected Resolution(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain, boolean z, TypePool typePool, Transformer transformer, boolean z2) {
                        this.typeDescription = typeDescription;
                        this.classLoader = classLoader;
                        this.module = javaModule;
                        this.protectionDomain = protectionDomain;
                        this.loaded = z;
                        this.typePool = typePool;
                        this.transformer = transformer;
                        this.decorator = z2;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution
                    public Resolution.Sort getSort() {
                        return this.decorator ? Resolution.Sort.DECORATOR : Resolution.Sort.TERMINAL;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution
                    public Resolution asDecoratorOf(Resolution resolution) {
                        return resolution.prepend(this);
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution
                    public Resolution prepend(Resolution.Decoratable decoratable) {
                        return decoratable.append(this.transformer);
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution.Decoratable
                    public Resolution append(Transformer transformer) {
                        return new Resolution(this.typeDescription, this.classLoader, this.module, this.protectionDomain, this.loaded, this.typePool, new Transformer.Compound(this.transformer, transformer), this.decorator);
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution
                    public byte[] apply(InitializationStrategy initializationStrategy, ClassFileLocator classFileLocator, TypeStrategy typeStrategy, ByteBuddy byteBuddy, NativeMethodStrategy nativeMethodStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, AccessControlContext accessControlContext, Listener listener) {
                        InitializationStrategy.Dispatcher dispatcher = initializationStrategy.dispatcher();
                        DynamicType.Unloaded<?> unloadedMake = dispatcher.apply(this.transformer.transform(typeStrategy.builder(this.typeDescription, byteBuddy, classFileLocator, nativeMethodStrategy.resolve()), this.typeDescription, this.classLoader, this.module)).make(TypeResolutionStrategy.Disabled.INSTANCE, this.typePool);
                        dispatcher.register(unloadedMake, this.classLoader, new BootstrapClassLoaderCapableInjectorFactory(bootstrapInjectionStrategy, this.classLoader, this.protectionDomain));
                        listener.onTransformation(this.typeDescription, this.classLoader, this.module, this.loaded, unloadedMake);
                        return unloadedMake.getBytes();
                    }

                    protected static class BootstrapClassLoaderCapableInjectorFactory implements InitializationStrategy.Dispatcher.InjectorFactory {
                        private final BootstrapInjectionStrategy bootstrapInjectionStrategy;
                        private final ClassLoader classLoader;
                        private final ProtectionDomain protectionDomain;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof BootstrapClassLoaderCapableInjectorFactory;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof BootstrapClassLoaderCapableInjectorFactory)) {
                                return false;
                            }
                            BootstrapClassLoaderCapableInjectorFactory bootstrapClassLoaderCapableInjectorFactory = (BootstrapClassLoaderCapableInjectorFactory) obj;
                            if (!bootstrapClassLoaderCapableInjectorFactory.canEqual(this)) {
                                return false;
                            }
                            BootstrapInjectionStrategy bootstrapInjectionStrategy = this.bootstrapInjectionStrategy;
                            BootstrapInjectionStrategy bootstrapInjectionStrategy2 = bootstrapClassLoaderCapableInjectorFactory.bootstrapInjectionStrategy;
                            if (bootstrapInjectionStrategy != null ? !bootstrapInjectionStrategy.equals(bootstrapInjectionStrategy2) : bootstrapInjectionStrategy2 != null) {
                                return false;
                            }
                            ClassLoader classLoader = this.classLoader;
                            ClassLoader classLoader2 = bootstrapClassLoaderCapableInjectorFactory.classLoader;
                            if (classLoader != null ? !classLoader.equals(classLoader2) : classLoader2 != null) {
                                return false;
                            }
                            ProtectionDomain protectionDomain = this.protectionDomain;
                            ProtectionDomain protectionDomain2 = bootstrapClassLoaderCapableInjectorFactory.protectionDomain;
                            return protectionDomain != null ? protectionDomain.equals(protectionDomain2) : protectionDomain2 == null;
                        }

                        public int hashCode() {
                            BootstrapInjectionStrategy bootstrapInjectionStrategy = this.bootstrapInjectionStrategy;
                            int iHashCode = bootstrapInjectionStrategy == null ? 43 : bootstrapInjectionStrategy.hashCode();
                            ClassLoader classLoader = this.classLoader;
                            int iHashCode2 = ((iHashCode + 59) * 59) + (classLoader == null ? 43 : classLoader.hashCode());
                            ProtectionDomain protectionDomain = this.protectionDomain;
                            return (iHashCode2 * 59) + (protectionDomain != null ? protectionDomain.hashCode() : 43);
                        }

                        protected BootstrapClassLoaderCapableInjectorFactory(BootstrapInjectionStrategy bootstrapInjectionStrategy, ClassLoader classLoader, ProtectionDomain protectionDomain) {
                            this.bootstrapInjectionStrategy = bootstrapInjectionStrategy;
                            this.classLoader = classLoader;
                            this.protectionDomain = protectionDomain;
                        }

                        @Override // net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher.InjectorFactory
                        public ClassInjector resolve() {
                            return this.classLoader == null ? this.bootstrapInjectionStrategy.make(this.protectionDomain) : new ClassInjector.UsingReflection(this.classLoader, this.protectionDomain);
                        }
                    }
                }
            }

            public static class Compound implements Transformation {
                private final List<Transformation> transformations;

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
                    List<Transformation> list = this.transformations;
                    List<Transformation> list2 = compound.transformations;
                    return list != null ? list.equals(list2) : list2 == null;
                }

                public int hashCode() {
                    List<Transformation> list = this.transformations;
                    return 59 + (list == null ? 43 : list.hashCode());
                }

                protected Compound(Transformation... transformationArr) {
                    this((List<? extends Transformation>) Arrays.asList(transformationArr));
                }

                protected Compound(List<? extends Transformation> list) {
                    this.transformations = new ArrayList();
                    for (Transformation transformation : list) {
                        if (transformation instanceof Compound) {
                            this.transformations.addAll(((Compound) transformation).transformations);
                        } else if (!(transformation instanceof Ignored)) {
                            this.transformations.add(transformation);
                        }
                    }
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.RawMatcher
                public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                    Iterator<Transformation> it = this.transformations.iterator();
                    while (it.hasNext()) {
                        if (it.next().matches(typeDescription, classLoader, javaModule, cls, protectionDomain)) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation
                public Resolution resolve(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool) {
                    boolean z2;
                    TypeDescription typeDescription2;
                    ClassLoader classLoader2;
                    JavaModule javaModule2;
                    if (cls != null) {
                        typeDescription2 = typeDescription;
                        classLoader2 = classLoader;
                        javaModule2 = javaModule;
                        z2 = true;
                    } else {
                        z2 = false;
                        typeDescription2 = typeDescription;
                        classLoader2 = classLoader;
                        javaModule2 = javaModule;
                    }
                    Resolution unresolved = new Resolution.Unresolved(typeDescription2, classLoader2, javaModule2, z2);
                    Iterator<Transformation> it = this.transformations.iterator();
                    while (it.hasNext()) {
                        Resolution resolutionResolve = it.next().resolve(typeDescription, classLoader, javaModule, cls, z, protectionDomain, typePool);
                        int i = AnonymousClass1.$SwitchMap$net$bytebuddy$agent$builder$AgentBuilder$Default$Transformation$Resolution$Sort[resolutionResolve.getSort().ordinal()];
                        if (i == 1) {
                            return unresolved.asDecoratorOf(resolutionResolve);
                        }
                        if (i == 2) {
                            unresolved = unresolved.asDecoratorOf(resolutionResolve);
                        } else if (i != 3) {
                            throw new IllegalStateException("Unexpected resolution type: " + resolutionResolve.getSort());
                        }
                    }
                    return unresolved;
                }
            }
        }

        protected static class ExecutingTransformer extends ResettableClassFileTransformer.AbstractBase {
            protected static final Factory FACTORY = (Factory) AccessController.doPrivileged(Factory.CreationAction.INSTANCE);
            private final AccessControlContext accessControlContext = AccessController.getContext();
            private final BootstrapInjectionStrategy bootstrapInjectionStrategy;
            private final ByteBuddy byteBuddy;
            private final CircularityLock circularityLock;
            private final DescriptionStrategy descriptionStrategy;
            private final FallbackStrategy fallbackStrategy;
            private final RawMatcher ignoredTypeMatcher;
            private final InitializationStrategy initializationStrategy;
            private final InstallationListener installationListener;
            private final LambdaInstrumentationStrategy lambdaInstrumentationStrategy;
            private final Listener listener;
            private final LocationStrategy locationStrategy;
            private final NativeMethodStrategy nativeMethodStrategy;
            private final PoolStrategy poolStrategy;
            private final Transformation transformation;
            private final TypeStrategy typeStrategy;

            public ExecutingTransformer(ByteBuddy byteBuddy, Listener listener, PoolStrategy poolStrategy, TypeStrategy typeStrategy, LocationStrategy locationStrategy, NativeMethodStrategy nativeMethodStrategy, InitializationStrategy initializationStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, InstallationListener installationListener, RawMatcher rawMatcher, Transformation transformation, CircularityLock circularityLock) {
                this.byteBuddy = byteBuddy;
                this.typeStrategy = typeStrategy;
                this.poolStrategy = poolStrategy;
                this.locationStrategy = locationStrategy;
                this.listener = listener;
                this.nativeMethodStrategy = nativeMethodStrategy;
                this.initializationStrategy = initializationStrategy;
                this.bootstrapInjectionStrategy = bootstrapInjectionStrategy;
                this.lambdaInstrumentationStrategy = lambdaInstrumentationStrategy;
                this.descriptionStrategy = descriptionStrategy;
                this.fallbackStrategy = fallbackStrategy;
                this.installationListener = installationListener;
                this.ignoredTypeMatcher = rawMatcher;
                this.transformation = transformation;
                this.circularityLock = circularityLock;
            }

            public byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                if (!this.circularityLock.acquire()) {
                    return Default.NO_TRANSFORMATION;
                }
                try {
                    return (byte[]) AccessController.doPrivileged(new LegacyVmDispatcher(classLoader, str, cls, protectionDomain, bArr), this.accessControlContext);
                } finally {
                    this.circularityLock.release();
                }
            }

            protected byte[] transform(Object obj, ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                if (!this.circularityLock.acquire()) {
                    return Default.NO_TRANSFORMATION;
                }
                try {
                    return (byte[]) AccessController.doPrivileged(new Java9CapableVmDispatcher(obj, classLoader, str, cls, protectionDomain, bArr), this.accessControlContext);
                } finally {
                    this.circularityLock.release();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public byte[] transform(JavaModule javaModule, ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                if (str == null || !this.lambdaInstrumentationStrategy.isInstrumented(cls)) {
                    return Default.NO_TRANSFORMATION;
                }
                String strReplace = str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
                try {
                    this.listener.onDiscovery(strReplace, classLoader, javaModule, cls != null);
                    ClassFileLocator classFileLocatorOf = ClassFileLocator.Simple.of(strReplace, bArr, this.locationStrategy.classFileLocator(classLoader, javaModule));
                    TypePool typePool = this.poolStrategy.typePool(classFileLocatorOf, classLoader);
                    try {
                        return doTransform(javaModule, classLoader, strReplace, cls, cls != null, protectionDomain, typePool, classFileLocatorOf);
                    } catch (Throwable th) {
                        if (cls != null && this.descriptionStrategy.isLoadedFirst() && this.fallbackStrategy.isFallback(cls, th)) {
                            byte[] bArrDoTransform = doTransform(javaModule, classLoader, strReplace, Default.NO_LOADED_TYPE, true, protectionDomain, typePool, classFileLocatorOf);
                            this.listener.onComplete(strReplace, classLoader, javaModule, cls != null);
                            return bArrDoTransform;
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    try {
                        this.listener.onError(strReplace, classLoader, javaModule, cls != null, th2);
                        byte[] bArr2 = Default.NO_TRANSFORMATION;
                        this.listener.onComplete(strReplace, classLoader, javaModule, cls != null);
                        return bArr2;
                    } finally {
                        this.listener.onComplete(strReplace, classLoader, javaModule, cls != null);
                    }
                }
            }

            private byte[] doTransform(JavaModule javaModule, ClassLoader classLoader, String str, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool, ClassFileLocator classFileLocator) {
                return resolve(javaModule, classLoader, str, cls, z, protectionDomain, typePool).apply(this.initializationStrategy, classFileLocator, this.typeStrategy, this.byteBuddy, this.nativeMethodStrategy, this.bootstrapInjectionStrategy, this.accessControlContext, this.listener);
            }

            private Transformation.Resolution resolve(JavaModule javaModule, ClassLoader classLoader, String str, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool) {
                TypeDescription typeDescriptionApply = this.descriptionStrategy.apply(str, cls, typePool, this.circularityLock, classLoader, javaModule);
                return this.ignoredTypeMatcher.matches(typeDescriptionApply, classLoader, javaModule, cls, protectionDomain) ? new Transformation.Resolution.Unresolved(typeDescriptionApply, classLoader, javaModule, z) : this.transformation.resolve(typeDescriptionApply, classLoader, javaModule, cls, z, protectionDomain, typePool);
            }

            @Override // net.bytebuddy.agent.builder.ResettableClassFileTransformer
            public synchronized boolean reset(Instrumentation instrumentation, RedefinitionStrategy redefinitionStrategy, RedefinitionStrategy.DiscoveryStrategy discoveryStrategy, RedefinitionStrategy.BatchAllocator batchAllocator, RedefinitionStrategy.Listener listener) {
                if (!instrumentation.removeTransformer(this)) {
                    return false;
                }
                redefinitionStrategy.apply(instrumentation, Listener.NoOp.INSTANCE, CircularityLock.Inactive.INSTANCE, this.poolStrategy, this.locationStrategy, discoveryStrategy, batchAllocator, listener, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.transformation, this.ignoredTypeMatcher);
                this.installationListener.onReset(instrumentation, this);
                return true;
            }

            protected interface Factory {
                ResettableClassFileTransformer make(ByteBuddy byteBuddy, Listener listener, PoolStrategy poolStrategy, TypeStrategy typeStrategy, LocationStrategy locationStrategy, NativeMethodStrategy nativeMethodStrategy, InitializationStrategy initializationStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, InstallationListener installationListener, RawMatcher rawMatcher, Transformation transformation, CircularityLock circularityLock);

                public enum CreationAction implements PrivilegedAction<Factory> {
                    INSTANCE;

                    @Override // java.security.PrivilegedAction
                    public Factory run() {
                        try {
                            return new ForJava9CapableVm(new ByteBuddy().subclass(ExecutingTransformer.class).name(ExecutingTransformer.class.getName() + "$ByteBuddy$ModuleSupport").method(ElementMatchers.named("transform").and(ElementMatchers.takesArgument(0, JavaType.MODULE.load()))).intercept(MethodCall.invoke(ExecutingTransformer.class.getDeclaredMethod("transform", Object.class, ClassLoader.class, String.class, Class.class, ProtectionDomain.class, byte[].class)).onSuper().withAllArguments()).make().load(ExecutingTransformer.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER_PERSISTENT.with(ExecutingTransformer.class.getProtectionDomain())).getLoaded().getDeclaredConstructor(ByteBuddy.class, Listener.class, PoolStrategy.class, TypeStrategy.class, LocationStrategy.class, NativeMethodStrategy.class, InitializationStrategy.class, BootstrapInjectionStrategy.class, LambdaInstrumentationStrategy.class, DescriptionStrategy.class, FallbackStrategy.class, InstallationListener.class, RawMatcher.class, Transformation.class, CircularityLock.class));
                        } catch (Exception unused) {
                            return ForLegacyVm.INSTANCE;
                        }
                    }
                }

                public static class ForJava9CapableVm implements Factory {
                    private final Constructor<? extends ResettableClassFileTransformer> executingTransformer;

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
                        Constructor<? extends ResettableClassFileTransformer> constructor = this.executingTransformer;
                        Constructor<? extends ResettableClassFileTransformer> constructor2 = forJava9CapableVm.executingTransformer;
                        return constructor != null ? constructor.equals(constructor2) : constructor2 == null;
                    }

                    public int hashCode() {
                        Constructor<? extends ResettableClassFileTransformer> constructor = this.executingTransformer;
                        return 59 + (constructor == null ? 43 : constructor.hashCode());
                    }

                    protected ForJava9CapableVm(Constructor<? extends ResettableClassFileTransformer> constructor) {
                        this.executingTransformer = constructor;
                    }

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.ExecutingTransformer.Factory
                    public ResettableClassFileTransformer make(ByteBuddy byteBuddy, Listener listener, PoolStrategy poolStrategy, TypeStrategy typeStrategy, LocationStrategy locationStrategy, NativeMethodStrategy nativeMethodStrategy, InitializationStrategy initializationStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, InstallationListener installationListener, RawMatcher rawMatcher, Transformation transformation, CircularityLock circularityLock) {
                        try {
                            return this.executingTransformer.newInstance(byteBuddy, listener, poolStrategy, typeStrategy, locationStrategy, nativeMethodStrategy, initializationStrategy, bootstrapInjectionStrategy, lambdaInstrumentationStrategy, descriptionStrategy, fallbackStrategy, installationListener, rawMatcher, transformation, circularityLock);
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Cannot access " + this.executingTransformer, e);
                        } catch (InstantiationException e2) {
                            throw new IllegalStateException("Cannot instantiate " + this.executingTransformer.getDeclaringClass(), e2);
                        } catch (InvocationTargetException e3) {
                            throw new IllegalStateException("Cannot invoke " + this.executingTransformer, e3.getCause());
                        }
                    }
                }

                public enum ForLegacyVm implements Factory {
                    INSTANCE;

                    @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.ExecutingTransformer.Factory
                    public ResettableClassFileTransformer make(ByteBuddy byteBuddy, Listener listener, PoolStrategy poolStrategy, TypeStrategy typeStrategy, LocationStrategy locationStrategy, NativeMethodStrategy nativeMethodStrategy, InitializationStrategy initializationStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, InstallationListener installationListener, RawMatcher rawMatcher, Transformation transformation, CircularityLock circularityLock) {
                        return new ExecutingTransformer(byteBuddy, listener, poolStrategy, typeStrategy, locationStrategy, nativeMethodStrategy, initializationStrategy, bootstrapInjectionStrategy, lambdaInstrumentationStrategy, descriptionStrategy, fallbackStrategy, installationListener, rawMatcher, transformation, circularityLock);
                    }
                }
            }

            protected class LegacyVmDispatcher implements PrivilegedAction<byte[]> {
                private final byte[] binaryRepresentation;
                private final Class<?> classBeingRedefined;
                private final ClassLoader classLoader;
                private final String internalTypeName;
                private final ProtectionDomain protectionDomain;

                protected LegacyVmDispatcher(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                    this.classLoader = classLoader;
                    this.internalTypeName = str;
                    this.classBeingRedefined = cls;
                    this.protectionDomain = protectionDomain;
                    this.binaryRepresentation = bArr;
                }

                @Override // java.security.PrivilegedAction
                public byte[] run() {
                    return ExecutingTransformer.this.transform(JavaModule.UNSUPPORTED, this.classLoader, this.internalTypeName, this.classBeingRedefined, this.protectionDomain, this.binaryRepresentation);
                }

                private ExecutingTransformer getOuter() {
                    return ExecutingTransformer.this;
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    LegacyVmDispatcher legacyVmDispatcher = (LegacyVmDispatcher) obj;
                    ClassLoader classLoader = this.classLoader;
                    if (classLoader == null ? legacyVmDispatcher.classLoader == null : classLoader.equals(legacyVmDispatcher.classLoader)) {
                        String str = this.internalTypeName;
                        if (str == null ? legacyVmDispatcher.internalTypeName == null : str.equals(legacyVmDispatcher.internalTypeName)) {
                            Class<?> cls = this.classBeingRedefined;
                            if (cls == null ? legacyVmDispatcher.classBeingRedefined == null : cls.equals(legacyVmDispatcher.classBeingRedefined)) {
                                if (this.protectionDomain.equals(legacyVmDispatcher.protectionDomain) && ExecutingTransformer.this.equals(legacyVmDispatcher.getOuter()) && Arrays.equals(this.binaryRepresentation, legacyVmDispatcher.binaryRepresentation)) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }

                public int hashCode() {
                    ClassLoader classLoader = this.classLoader;
                    int iHashCode = (classLoader != null ? classLoader.hashCode() : 0) * 31;
                    String str = this.internalTypeName;
                    int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
                    Class<?> cls = this.classBeingRedefined;
                    return ((((((iHashCode2 + (cls != null ? cls.hashCode() : 0)) * 31) + this.protectionDomain.hashCode()) * 31) + ExecutingTransformer.this.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation);
                }
            }

            protected class Java9CapableVmDispatcher implements PrivilegedAction<byte[]> {
                private final byte[] binaryRepresentation;
                private final Class<?> classBeingRedefined;
                private final ClassLoader classLoader;
                private final String internalTypeName;
                private final ProtectionDomain protectionDomain;
                private final Object rawModule;

                protected Java9CapableVmDispatcher(Object obj, ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                    this.rawModule = obj;
                    this.classLoader = classLoader;
                    this.internalTypeName = str;
                    this.classBeingRedefined = cls;
                    this.protectionDomain = protectionDomain;
                    this.binaryRepresentation = bArr;
                }

                @Override // java.security.PrivilegedAction
                public byte[] run() {
                    return ExecutingTransformer.this.transform(JavaModule.of(this.rawModule), this.classLoader, this.internalTypeName, this.classBeingRedefined, this.protectionDomain, this.binaryRepresentation);
                }

                private ExecutingTransformer getOuter() {
                    return ExecutingTransformer.this;
                }

                public boolean equals(Object obj) {
                    ClassLoader classLoader;
                    String str;
                    Class<?> cls;
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Java9CapableVmDispatcher java9CapableVmDispatcher = (Java9CapableVmDispatcher) obj;
                    return this.rawModule.equals(java9CapableVmDispatcher.rawModule) && ((classLoader = this.classLoader) == null ? java9CapableVmDispatcher.classLoader == null : classLoader.equals(java9CapableVmDispatcher.classLoader)) && ((str = this.internalTypeName) == null ? java9CapableVmDispatcher.internalTypeName == null : str.equals(java9CapableVmDispatcher.internalTypeName)) && ((cls = this.classBeingRedefined) == null ? java9CapableVmDispatcher.classBeingRedefined == null : cls.equals(java9CapableVmDispatcher.classBeingRedefined)) && this.protectionDomain.equals(java9CapableVmDispatcher.protectionDomain) && ExecutingTransformer.this.equals(java9CapableVmDispatcher.getOuter()) && Arrays.equals(this.binaryRepresentation, java9CapableVmDispatcher.binaryRepresentation);
                }

                public int hashCode() {
                    int iHashCode = this.rawModule.hashCode() * 31;
                    ClassLoader classLoader = this.classLoader;
                    int iHashCode2 = (iHashCode + (classLoader != null ? classLoader.hashCode() : 0)) * 31;
                    String str = this.internalTypeName;
                    int iHashCode3 = (iHashCode2 + (str != null ? str.hashCode() : 0)) * 31;
                    Class<?> cls = this.classBeingRedefined;
                    return ((((((iHashCode3 + (cls != null ? cls.hashCode() : 0)) * 31) + this.protectionDomain.hashCode()) * 31) + ExecutingTransformer.this.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation);
                }
            }
        }

        protected abstract class Delegator<T extends Matchable<T>> extends Matchable.AbstractBase<T> implements AgentBuilder {
            protected abstract AgentBuilder materialize();

            protected Delegator() {
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(ByteBuddy byteBuddy) {
                return materialize().with(byteBuddy);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(Listener listener) {
                return materialize().with(listener);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(CircularityLock circularityLock) {
                return materialize().with(circularityLock);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(TypeStrategy typeStrategy) {
                return materialize().with(typeStrategy);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(PoolStrategy poolStrategy) {
                return materialize().with(poolStrategy);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(LocationStrategy locationStrategy) {
                return materialize().with(locationStrategy);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(InitializationStrategy initializationStrategy) {
                return materialize().with(initializationStrategy);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public RedefinitionListenable.WithoutBatchStrategy with(RedefinitionStrategy redefinitionStrategy) {
                return materialize().with(redefinitionStrategy);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(LambdaInstrumentationStrategy lambdaInstrumentationStrategy) {
                return materialize().with(lambdaInstrumentationStrategy);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(DescriptionStrategy descriptionStrategy) {
                return materialize().with(descriptionStrategy);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(FallbackStrategy fallbackStrategy) {
                return materialize().with(fallbackStrategy);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder with(InstallationListener installationListener) {
                return materialize().with(installationListener);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder enableBootstrapInjection(Instrumentation instrumentation, File file) {
                return materialize().enableBootstrapInjection(instrumentation, file);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder enableUnsafeBootstrapInjection() {
                return materialize().enableUnsafeBootstrapInjection();
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder disableBootstrapInjection() {
                return materialize().disableBootstrapInjection();
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder enableNativeMethodPrefix(String str) {
                return materialize().enableNativeMethodPrefix(str);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder disableNativeMethodPrefix() {
                return materialize().disableNativeMethodPrefix();
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder disableClassFormatChanges() {
                return materialize().disableClassFormatChanges();
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Class<?>... clsArr) {
                return materialize().assureReadEdgeTo(instrumentation, clsArr);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, JavaModule... javaModuleArr) {
                return materialize().assureReadEdgeTo(instrumentation, javaModuleArr);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection) {
                return materialize().assureReadEdgeTo(instrumentation, collection);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Class<?>... clsArr) {
                return materialize().assureReadEdgeFromAndTo(instrumentation, clsArr);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, JavaModule... javaModuleArr) {
                return materialize().assureReadEdgeFromAndTo(instrumentation, javaModuleArr);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection) {
                return materialize().assureReadEdgeFromAndTo(instrumentation, collection);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher) {
                return materialize().type(elementMatcher);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
                return materialize().type(elementMatcher, elementMatcher2);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
                return materialize().type(elementMatcher, elementMatcher2, elementMatcher3);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public Identified.Narrowable type(RawMatcher rawMatcher) {
                return materialize().type(rawMatcher);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher) {
                return materialize().ignore(elementMatcher);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
                return materialize().ignore(elementMatcher, elementMatcher2);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
                return materialize().ignore(elementMatcher, elementMatcher2, elementMatcher3);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public Ignored ignore(RawMatcher rawMatcher) {
                return materialize().ignore(rawMatcher);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public ClassFileTransformer makeRaw() {
                return materialize().makeRaw();
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public ResettableClassFileTransformer installOn(Instrumentation instrumentation) {
                return materialize().installOn(instrumentation);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder
            public ResettableClassFileTransformer installOnByteBuddyAgent() {
                return materialize().installOnByteBuddyAgent();
            }
        }

        protected class Ignoring extends Delegator<Ignored> implements Ignored {
            private final RawMatcher rawMatcher;

            protected Ignoring(RawMatcher rawMatcher) {
                super();
                this.rawMatcher = rawMatcher;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Delegator
            protected AgentBuilder materialize() {
                return new Default(Default.this.byteBuddy, Default.this.listener, Default.this.circularityLock, Default.this.poolStrategy, Default.this.typeStrategy, Default.this.locationStrategy, Default.this.nativeMethodStrategy, Default.this.initializationStrategy, Default.this.redefinitionStrategy, Default.this.redefinitionDiscoveryStrategy, Default.this.redefinitionBatchAllocator, Default.this.redefinitionListener, Default.this.redefinitionResubmissionStrategy, Default.this.bootstrapInjectionStrategy, Default.this.lambdaInstrumentationStrategy, Default.this.descriptionStrategy, Default.this.fallbackStrategy, Default.this.installationListener, this.rawMatcher, Default.this.transformation);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Matchable
            public Ignored and(RawMatcher rawMatcher) {
                return Default.this.new Ignoring(new RawMatcher.Conjunction(this.rawMatcher, rawMatcher));
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Matchable
            public Ignored or(RawMatcher rawMatcher) {
                return Default.this.new Ignoring(new RawMatcher.Disjunction(this.rawMatcher, rawMatcher));
            }

            private Default getOuter() {
                return Default.this;
            }

            public boolean equals(Object obj) {
                if (this != obj) {
                    if (obj != null && getClass() == obj.getClass()) {
                        Ignoring ignoring = (Ignoring) obj;
                        if (!this.rawMatcher.equals(ignoring.rawMatcher) || !Default.this.equals(ignoring.getOuter())) {
                        }
                    }
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return (this.rawMatcher.hashCode() * 31) + Default.this.hashCode();
            }
        }

        protected static class Redefining extends Default implements RedefinitionListenable.WithoutBatchStrategy {
            @Override // net.bytebuddy.agent.builder.AgentBuilder.Default, net.bytebuddy.agent.builder.AgentBuilder
            public /* bridge */ /* synthetic */ ClassFileTransformer makeRaw() {
                return super.makeRaw();
            }

            protected Redefining(ByteBuddy byteBuddy, Listener listener, CircularityLock circularityLock, PoolStrategy poolStrategy, TypeStrategy typeStrategy, LocationStrategy locationStrategy, NativeMethodStrategy nativeMethodStrategy, InitializationStrategy initializationStrategy, RedefinitionStrategy redefinitionStrategy, RedefinitionStrategy.DiscoveryStrategy discoveryStrategy, RedefinitionStrategy.BatchAllocator batchAllocator, RedefinitionStrategy.Listener listener2, RedefinitionStrategy.ResubmissionStrategy resubmissionStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, InstallationListener installationListener, RawMatcher rawMatcher, Transformation transformation) {
                super(byteBuddy, listener, circularityLock, poolStrategy, typeStrategy, locationStrategy, nativeMethodStrategy, initializationStrategy, redefinitionStrategy, discoveryStrategy, batchAllocator, listener2, resubmissionStrategy, bootstrapInjectionStrategy, lambdaInstrumentationStrategy, descriptionStrategy, fallbackStrategy, installationListener, rawMatcher, transformation);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionListenable.WithoutBatchStrategy
            public RedefinitionListenable.WithImplicitDiscoveryStrategy with(RedefinitionStrategy.BatchAllocator batchAllocator) {
                if (!this.redefinitionStrategy.isEnabled()) {
                    throw new IllegalStateException("Cannot set redefinition batch allocator when redefinition is disabled");
                }
                return new Redefining(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, batchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionListenable.WithImplicitDiscoveryStrategy
            public RedefinitionListenable redefineOnly(Class<?>... clsArr) {
                return with(new RedefinitionStrategy.DiscoveryStrategy.Explicit(clsArr));
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionListenable.WithImplicitDiscoveryStrategy
            public RedefinitionListenable with(RedefinitionStrategy.DiscoveryStrategy discoveryStrategy) {
                if (!this.redefinitionStrategy.isEnabled()) {
                    throw new IllegalStateException("Cannot set redefinition discovery strategy when redefinition is disabled");
                }
                return new Redefining(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, discoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionListenable
            public RedefinitionListenable with(RedefinitionStrategy.Listener listener) {
                if (!this.redefinitionStrategy.isEnabled()) {
                    throw new IllegalStateException("Cannot set redefinition listener when redefinition is disabled");
                }
                return new Redefining(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, new RedefinitionStrategy.Listener.Compound(this.redefinitionListener, listener), this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionListenable
            public AgentBuilder withResubmission(RedefinitionStrategy.ResubmissionScheduler resubmissionScheduler) {
                return withResubmission(resubmissionScheduler, ElementMatchers.any());
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.RedefinitionListenable
            public AgentBuilder withResubmission(RedefinitionStrategy.ResubmissionScheduler resubmissionScheduler, ElementMatcher<? super Throwable> elementMatcher) {
                if (!this.redefinitionStrategy.isEnabled()) {
                    throw new IllegalStateException("Cannot enable redefinition resubmission when redefinition is disabled");
                }
                return new Redefining(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, new RedefinitionStrategy.ResubmissionStrategy.Enabled(resubmissionScheduler, elementMatcher), this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
            }
        }

        protected class Transforming extends Delegator<Identified.Narrowable> implements Identified.Extendable, Identified.Narrowable {
            private final boolean decorator;
            private final RawMatcher rawMatcher;
            private final Transformer transformer;

            protected Transforming(RawMatcher rawMatcher, Transformer transformer, boolean z) {
                super();
                this.rawMatcher = rawMatcher;
                this.transformer = transformer;
                this.decorator = z;
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Default.Delegator
            protected AgentBuilder materialize() {
                return new Default(Default.this.byteBuddy, Default.this.listener, Default.this.circularityLock, Default.this.poolStrategy, Default.this.typeStrategy, Default.this.locationStrategy, Default.this.nativeMethodStrategy, Default.this.initializationStrategy, Default.this.redefinitionStrategy, Default.this.redefinitionDiscoveryStrategy, Default.this.redefinitionBatchAllocator, Default.this.redefinitionListener, Default.this.redefinitionResubmissionStrategy, Default.this.bootstrapInjectionStrategy, Default.this.lambdaInstrumentationStrategy, Default.this.descriptionStrategy, Default.this.fallbackStrategy, Default.this.installationListener, Default.this.ignoredTypeMatcher, new Transformation.Compound(new Transformation.Simple(this.rawMatcher, this.transformer, this.decorator), Default.this.transformation));
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Identified
            public Identified.Extendable transform(Transformer transformer) {
                return Default.this.new Transforming(this.rawMatcher, new Transformer.Compound(this.transformer, transformer), this.decorator);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Identified.Extendable
            public AgentBuilder asDecorator() {
                return Default.this.new Transforming(this.rawMatcher, this.transformer, true);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Matchable
            public Identified.Narrowable and(RawMatcher rawMatcher) {
                return Default.this.new Transforming(new RawMatcher.Conjunction(this.rawMatcher, rawMatcher), this.transformer, this.decorator);
            }

            @Override // net.bytebuddy.agent.builder.AgentBuilder.Matchable
            public Identified.Narrowable or(RawMatcher rawMatcher) {
                return Default.this.new Transforming(new RawMatcher.Disjunction(this.rawMatcher, rawMatcher), this.transformer, this.decorator);
            }

            private Default getOuter() {
                return Default.this;
            }

            public boolean equals(Object obj) {
                if (this != obj) {
                    if (obj != null && getClass() == obj.getClass()) {
                        Transforming transforming = (Transforming) obj;
                        if (this.decorator != transforming.decorator || !this.rawMatcher.equals(transforming.rawMatcher) || !this.transformer.equals(transforming.transformer) || !Default.this.equals(transforming.getOuter())) {
                        }
                    }
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return (((((this.rawMatcher.hashCode() * 31) + (this.decorator ? 1 : 0)) * 31) + this.transformer.hashCode()) * 31) + Default.this.hashCode();
            }
        }
    }

    /* JADX INFO: renamed from: net.bytebuddy.agent.builder.AgentBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$agent$builder$AgentBuilder$Default$Transformation$Resolution$Sort;

        static {
            int[] iArr = new int[Default.Transformation.Resolution.Sort.values().length];
            $SwitchMap$net$bytebuddy$agent$builder$AgentBuilder$Default$Transformation$Resolution$Sort = iArr;
            try {
                iArr[Default.Transformation.Resolution.Sort.TERMINAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$bytebuddy$agent$builder$AgentBuilder$Default$Transformation$Resolution$Sort[Default.Transformation.Resolution.Sort.DECORATOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$bytebuddy$agent$builder$AgentBuilder$Default$Transformation$Resolution$Sort[Default.Transformation.Resolution.Sort.UNDEFINED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
