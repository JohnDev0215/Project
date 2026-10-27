package net.bytebuddy.dynamic;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collections;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class NexusAccessor {
    private static final Dispatcher DISPATCHER = (Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);
    private final ReferenceQueue<? super ClassLoader> referenceQueue;

    protected boolean canEqual(Object obj) {
        return obj instanceof NexusAccessor;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NexusAccessor)) {
            return false;
        }
        NexusAccessor nexusAccessor = (NexusAccessor) obj;
        if (!nexusAccessor.canEqual(this)) {
            return false;
        }
        ReferenceQueue<? super ClassLoader> referenceQueue = this.referenceQueue;
        ReferenceQueue<? super ClassLoader> referenceQueue2 = nexusAccessor.referenceQueue;
        return referenceQueue != null ? referenceQueue.equals(referenceQueue2) : referenceQueue2 == null;
    }

    public int hashCode() {
        ReferenceQueue<? super ClassLoader> referenceQueue = this.referenceQueue;
        return 59 + (referenceQueue == null ? 43 : referenceQueue.hashCode());
    }

    public NexusAccessor() {
        this(Nexus.NO_QUEUE);
    }

    public NexusAccessor(ReferenceQueue<? super ClassLoader> referenceQueue) {
        this.referenceQueue = referenceQueue;
    }

    public static boolean isAlive() {
        return DISPATCHER.isAlive();
    }

    public static void clean(Reference<? extends ClassLoader> reference) {
        DISPATCHER.clean(reference);
    }

    public void register(String str, ClassLoader classLoader, int i, LoadedTypeInitializer loadedTypeInitializer) {
        if (loadedTypeInitializer.isAlive()) {
            DISPATCHER.register(str, classLoader, this.referenceQueue, i, loadedTypeInitializer);
        }
    }

    public static class InitializationAppender implements ByteCodeAppender {
        private final int identification;

        protected boolean canEqual(Object obj) {
            return obj instanceof InitializationAppender;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof InitializationAppender)) {
                return false;
            }
            InitializationAppender initializationAppender = (InitializationAppender) obj;
            return initializationAppender.canEqual(this) && this.identification == initializationAppender.identification;
        }

        public int hashCode() {
            return 59 + this.identification;
        }

        public InitializationAppender(int i) {
            this.identification = i;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            try {
                StackManipulation[] stackManipulationArr = new StackManipulation[1];
                StackManipulation[] stackManipulationArr2 = new StackManipulation[10];
                stackManipulationArr2[0] = MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(ClassLoader.class.getMethod("getSystemClassLoader", new Class[0])));
                stackManipulationArr2[1] = new TextConstant(Nexus.class.getName());
                stackManipulationArr2[2] = MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(ClassLoader.class.getMethod("loadClass", String.class)));
                stackManipulationArr2[3] = new TextConstant("initialize");
                stackManipulationArr2[4] = ArrayFactory.forType(new TypeDescription.Generic.OfNonGenericType.ForLoadedType(Class.class)).withValues(Arrays.asList(ClassConstant.of(TypeDescription.CLASS), ClassConstant.of(new TypeDescription.ForLoadedType(Integer.TYPE))));
                stackManipulationArr2[5] = MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(Class.class.getMethod("getMethod", String.class, Class[].class)));
                stackManipulationArr2[6] = NullConstant.INSTANCE;
                ArrayFactory arrayFactoryForType = ArrayFactory.forType(TypeDescription.Generic.OBJECT);
                StackManipulation[] stackManipulationArr3 = new StackManipulation[2];
                stackManipulationArr3[0] = ClassConstant.of(methodDescription.getDeclaringType().asErasure());
                StackManipulation[] stackManipulationArr4 = new StackManipulation[2];
                try {
                    stackManipulationArr4[0] = IntegerConstant.forValue(this.identification);
                    stackManipulationArr4[1] = MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(Integer.class.getMethod("valueOf", Integer.TYPE)));
                    stackManipulationArr3[1] = new StackManipulation.Compound(stackManipulationArr4);
                    stackManipulationArr2[7] = arrayFactoryForType.withValues(Arrays.asList(stackManipulationArr3));
                    stackManipulationArr2[8] = MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(Method.class.getMethod("invoke", Object.class, Object[].class)));
                    stackManipulationArr2[9] = Removal.SINGLE;
                    stackManipulationArr[0] = new StackManipulation.Compound(stackManipulationArr2);
                    return new ByteCodeAppender.Simple(stackManipulationArr).apply(methodVisitor, context, methodDescription);
                } catch (NoSuchMethodException e) {
                    e = e;
                    throw new IllegalStateException("Cannot locate method", e);
                }
            } catch (NoSuchMethodException e2) {
                e = e2;
            }
        }
    }

    protected interface Dispatcher {
        void clean(Reference<? extends ClassLoader> reference);

        boolean isAlive();

        void register(String str, ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue, int i, LoadedTypeInitializer loadedTypeInitializer);

        public enum CreationAction implements PrivilegedAction<Dispatcher> {
            INSTANCE;

            @Override // java.security.PrivilegedAction
            public Dispatcher run() {
                if (Boolean.getBoolean(Nexus.PROPERTY)) {
                    return new Unavailable(new IllegalStateException("Nexus injection was explicitly disabled"));
                }
                try {
                    Class<?> cls = new ClassInjector.UsingReflection(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.NO_PROTECTION_DOMAIN).inject(Collections.singletonMap(new TypeDescription.ForLoadedType(Nexus.class), ClassFileLocator.ForClassLoader.read(Nexus.class).resolve())).get(new TypeDescription.ForLoadedType(Nexus.class));
                    return new Available(cls.getMethod("register", String.class, ClassLoader.class, ReferenceQueue.class, Integer.TYPE, Object.class), cls.getMethod("clean", Reference.class));
                } catch (Exception e) {
                    try {
                        Class<?> clsLoadClass = ClassLoader.getSystemClassLoader().loadClass(Nexus.class.getName());
                        return new Available(clsLoadClass.getMethod("register", String.class, ClassLoader.class, ReferenceQueue.class, Integer.TYPE, Object.class), clsLoadClass.getMethod("clean", Reference.class));
                    } catch (Exception unused) {
                        return new Unavailable(e);
                    }
                }
            }
        }

        public static class Available implements Dispatcher {
            private static final Object STATIC_METHOD = null;
            private final Method clean;
            private final Method register;

            @Override // net.bytebuddy.dynamic.NexusAccessor.Dispatcher
            public boolean isAlive() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Available;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Available)) {
                    return false;
                }
                Available available = (Available) obj;
                if (!available.canEqual(this)) {
                    return false;
                }
                Method method = this.register;
                Method method2 = available.register;
                if (method != null ? !method.equals(method2) : method2 != null) {
                    return false;
                }
                Method method3 = this.clean;
                Method method4 = available.clean;
                return method3 != null ? method3.equals(method4) : method4 == null;
            }

            public int hashCode() {
                Method method = this.register;
                int iHashCode = method == null ? 43 : method.hashCode();
                Method method2 = this.clean;
                return ((iHashCode + 59) * 59) + (method2 != null ? method2.hashCode() : 43);
            }

            protected Available(Method method, Method method2) {
                this.register = method;
                this.clean = method2;
            }

            @Override // net.bytebuddy.dynamic.NexusAccessor.Dispatcher
            public void clean(Reference<? extends ClassLoader> reference) {
                try {
                    this.clean.invoke(STATIC_METHOD, reference);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access: " + this.clean, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke: " + this.clean, e2.getCause());
                }
            }

            @Override // net.bytebuddy.dynamic.NexusAccessor.Dispatcher
            public void register(String str, ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue, int i, LoadedTypeInitializer loadedTypeInitializer) {
                try {
                    this.register.invoke(STATIC_METHOD, str, classLoader, referenceQueue, Integer.valueOf(i), loadedTypeInitializer);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access: " + this.register, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke: " + this.register, e2.getCause());
                }
            }
        }

        public static class Unavailable implements Dispatcher {
            private final Exception exception;

            @Override // net.bytebuddy.dynamic.NexusAccessor.Dispatcher
            public boolean isAlive() {
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

            @Override // net.bytebuddy.dynamic.NexusAccessor.Dispatcher
            public void clean(Reference<? extends ClassLoader> reference) {
                throw new IllegalStateException("Could not initialize Nexus accessor", this.exception);
            }

            @Override // net.bytebuddy.dynamic.NexusAccessor.Dispatcher
            public void register(String str, ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue, int i, LoadedTypeInitializer loadedTypeInitializer) {
                throw new IllegalStateException("Could not initialize Nexus accessor", this.exception);
            }
        }
    }
}
