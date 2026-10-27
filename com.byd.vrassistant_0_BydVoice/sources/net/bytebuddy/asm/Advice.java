package net.bytebuddy.asm;

import com.byd.common.bean.WuwInfoBean;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.bytecode.Addition;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.MethodConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.SerializedConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor;
import net.bytebuddy.utility.visitor.LineNumberPrependingMethodVisitor;
import net.bytebuddy.utility.visitor.StackAwareMethodVisitor;

/* JADX INFO: loaded from: classes3.dex */
public class Advice implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper, Implementation {
    private static final MethodDescription.InDefinedShape INLINE_ENTER;
    private static final MethodDescription.InDefinedShape INLINE_EXIT;
    private static final MethodDescription.InDefinedShape ON_THROWABLE;
    private static final MethodDescription.InDefinedShape PREPEND_LINE_NUMBER;
    private static final MethodDescription.InDefinedShape SKIP_ON;
    private static final MethodDescription.InDefinedShape SUPPRESS_ENTER;
    private static final MethodDescription.InDefinedShape SUPPRESS_EXIT;
    private static final ClassReader UNDEFINED = null;
    private final Assigner assigner;
    private final Implementation delegate;
    private final StackManipulation exceptionHandler;
    private final Dispatcher.Resolved.ForMethodEnter methodEnter;
    private final Dispatcher.Resolved.ForMethodExit methodExit;

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AllArguments {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Argument {
        boolean optional() default false;

        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;

        int value();
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Enter {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FieldValue {
        Class<?> declaringType() default void.class;

        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;

        String value();
    }

    @Target({ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface OnMethodEnter {
        boolean inline() default true;

        boolean prependLineNumber() default true;

        Class<?> skipOn() default void.class;

        Class<? extends Throwable> suppress() default NoExceptionHandler.class;
    }

    @Target({ElementType.METHOD})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface OnMethodExit {
        boolean inline() default true;

        Class<? extends Throwable> onThrowable() default NoExceptionHandler.class;

        Class<? extends Throwable> suppress() default NoExceptionHandler.class;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Origin {
        public static final String DEFAULT = "";

        String value() default "";
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Return {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface StubValue {
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface This {
        boolean optional() default false;

        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Thrown {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.DYNAMIC;
    }

    @Target({ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Unused {
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof Advice;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Advice)) {
            return false;
        }
        Advice advice = (Advice) obj;
        if (!advice.canEqual(this)) {
            return false;
        }
        Dispatcher.Resolved.ForMethodEnter forMethodEnter = this.methodEnter;
        Dispatcher.Resolved.ForMethodEnter forMethodEnter2 = advice.methodEnter;
        if (forMethodEnter != null ? !forMethodEnter.equals(forMethodEnter2) : forMethodEnter2 != null) {
            return false;
        }
        Dispatcher.Resolved.ForMethodExit forMethodExit = this.methodExit;
        Dispatcher.Resolved.ForMethodExit forMethodExit2 = advice.methodExit;
        if (forMethodExit != null ? !forMethodExit.equals(forMethodExit2) : forMethodExit2 != null) {
            return false;
        }
        Assigner assigner = this.assigner;
        Assigner assigner2 = advice.assigner;
        if (assigner != null ? !assigner.equals(assigner2) : assigner2 != null) {
            return false;
        }
        StackManipulation stackManipulation = this.exceptionHandler;
        StackManipulation stackManipulation2 = advice.exceptionHandler;
        if (stackManipulation != null ? !stackManipulation.equals(stackManipulation2) : stackManipulation2 != null) {
            return false;
        }
        Implementation implementation = this.delegate;
        Implementation implementation2 = advice.delegate;
        return implementation != null ? implementation.equals(implementation2) : implementation2 == null;
    }

    public int hashCode() {
        Dispatcher.Resolved.ForMethodEnter forMethodEnter = this.methodEnter;
        int iHashCode = forMethodEnter == null ? 43 : forMethodEnter.hashCode();
        Dispatcher.Resolved.ForMethodExit forMethodExit = this.methodExit;
        int iHashCode2 = ((iHashCode + 59) * 59) + (forMethodExit == null ? 43 : forMethodExit.hashCode());
        Assigner assigner = this.assigner;
        int iHashCode3 = (iHashCode2 * 59) + (assigner == null ? 43 : assigner.hashCode());
        StackManipulation stackManipulation = this.exceptionHandler;
        int iHashCode4 = (iHashCode3 * 59) + (stackManipulation == null ? 43 : stackManipulation.hashCode());
        Implementation implementation = this.delegate;
        return (iHashCode4 * 59) + (implementation != null ? implementation.hashCode() : 43);
    }

    static {
        MethodList<MethodDescription.InDefinedShape> declaredMethods = new TypeDescription.ForLoadedType(OnMethodEnter.class).getDeclaredMethods();
        INLINE_ENTER = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("inline")).getOnly();
        SUPPRESS_ENTER = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("suppress")).getOnly();
        SKIP_ON = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("skipOn")).getOnly();
        PREPEND_LINE_NUMBER = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("prependLineNumber")).getOnly();
        MethodList<MethodDescription.InDefinedShape> declaredMethods2 = new TypeDescription.ForLoadedType(OnMethodExit.class).getDeclaredMethods();
        INLINE_EXIT = (MethodDescription.InDefinedShape) declaredMethods2.filter(ElementMatchers.named("inline")).getOnly();
        SUPPRESS_EXIT = (MethodDescription.InDefinedShape) declaredMethods2.filter(ElementMatchers.named("suppress")).getOnly();
        ON_THROWABLE = (MethodDescription.InDefinedShape) declaredMethods2.filter(ElementMatchers.named("onThrowable")).getOnly();
    }

    protected Advice(Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit) {
        this(forMethodEnter, forMethodExit, Assigner.DEFAULT, Removal.of(TypeDescription.THROWABLE), SuperMethodCall.INSTANCE);
    }

    private Advice(Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, Assigner assigner, StackManipulation stackManipulation, Implementation implementation) {
        this.methodEnter = forMethodEnter;
        this.methodExit = forMethodExit;
        this.assigner = assigner;
        this.exceptionHandler = stackManipulation;
        this.delegate = implementation;
    }

    public static Advice to(Class<?> cls) {
        return to(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public static Advice to(Class<?> cls, ClassFileLocator classFileLocator) {
        return to(new TypeDescription.ForLoadedType(cls), classFileLocator);
    }

    public static Advice to(TypeDescription typeDescription) {
        return to(typeDescription, ClassFileLocator.NoOp.INSTANCE);
    }

    public static Advice to(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        return to(typeDescription, classFileLocator, (List<? extends OffsetMapping.Factory<?>>) Collections.emptyList());
    }

    protected static Advice to(TypeDescription typeDescription, ClassFileLocator classFileLocator, List<? extends OffsetMapping.Factory<?>> list) {
        ClassReader classReader;
        Dispatcher.Unresolved unresolvedLocate = Dispatcher.Inactive.INSTANCE;
        Dispatcher.Unresolved unresolvedLocate2 = Dispatcher.Inactive.INSTANCE;
        for (MethodDescription.InDefinedShape inDefinedShape : typeDescription.getDeclaredMethods()) {
            unresolvedLocate = locate(OnMethodEnter.class, INLINE_ENTER, unresolvedLocate, inDefinedShape);
            unresolvedLocate2 = locate(OnMethodExit.class, INLINE_EXIT, unresolvedLocate2, inDefinedShape);
        }
        if (!unresolvedLocate.isAlive() && !unresolvedLocate2.isAlive()) {
            throw new IllegalArgumentException("No advice defined by " + typeDescription);
        }
        try {
            if (unresolvedLocate.isBinary() || unresolvedLocate2.isBinary()) {
                classReader = new ClassReader(classFileLocator.locate(typeDescription.getName()).resolve());
            } else {
                classReader = UNDEFINED;
            }
            Dispatcher.Resolved.ForMethodEnter forMethodEnterAsMethodEnter = unresolvedLocate.asMethodEnter(list, classReader);
            return new Advice(forMethodEnterAsMethodEnter, unresolvedLocate2.asMethodExitTo(list, classReader, forMethodEnterAsMethodEnter));
        } catch (IOException e) {
            throw new IllegalStateException("Error reading class file of " + typeDescription, e);
        }
    }

    public static Advice to(Class<?> cls, Class<?> cls2) {
        ClassLoader classLoader = cls.getClassLoader();
        ClassLoader classLoader2 = cls2.getClassLoader();
        return to(cls, cls2, classLoader == classLoader2 ? ClassFileLocator.ForClassLoader.of(classLoader) : new ClassFileLocator.Compound(ClassFileLocator.ForClassLoader.of(classLoader), ClassFileLocator.ForClassLoader.of(classLoader2)));
    }

    public static Advice to(Class<?> cls, Class<?> cls2, ClassFileLocator classFileLocator) {
        return to(new TypeDescription.ForLoadedType(cls), new TypeDescription.ForLoadedType(cls2), classFileLocator);
    }

    public static Advice to(TypeDescription typeDescription, TypeDescription typeDescription2) {
        return to(typeDescription, typeDescription2, ClassFileLocator.NoOp.INSTANCE);
    }

    public static Advice to(TypeDescription typeDescription, TypeDescription typeDescription2, ClassFileLocator classFileLocator) {
        return to(typeDescription, typeDescription2, classFileLocator, Collections.emptyList());
    }

    protected static Advice to(TypeDescription typeDescription, TypeDescription typeDescription2, ClassFileLocator classFileLocator, List<? extends OffsetMapping.Factory<?>> list) {
        Dispatcher.Unresolved unresolvedLocate = Dispatcher.Inactive.INSTANCE;
        Dispatcher.Unresolved unresolvedLocate2 = Dispatcher.Inactive.INSTANCE;
        Iterator it = typeDescription.getDeclaredMethods().iterator();
        while (it.hasNext()) {
            unresolvedLocate = locate(OnMethodEnter.class, INLINE_ENTER, unresolvedLocate, (MethodDescription.InDefinedShape) it.next());
        }
        if (!unresolvedLocate.isAlive()) {
            throw new IllegalArgumentException("No enter advice defined by " + typeDescription);
        }
        Iterator it2 = typeDescription2.getDeclaredMethods().iterator();
        while (it2.hasNext()) {
            unresolvedLocate2 = locate(OnMethodExit.class, INLINE_EXIT, unresolvedLocate2, (MethodDescription.InDefinedShape) it2.next());
        }
        if (!unresolvedLocate2.isAlive()) {
            throw new IllegalArgumentException("No enter advice defined by " + typeDescription2);
        }
        try {
            Dispatcher.Resolved.ForMethodEnter forMethodEnterAsMethodEnter = unresolvedLocate.asMethodEnter(list, unresolvedLocate.isBinary() ? new ClassReader(classFileLocator.locate(typeDescription.getName()).resolve()) : UNDEFINED);
            return new Advice(forMethodEnterAsMethodEnter, unresolvedLocate2.asMethodExitTo(list, unresolvedLocate2.isBinary() ? new ClassReader(classFileLocator.locate(typeDescription2.getName()).resolve()) : UNDEFINED, forMethodEnterAsMethodEnter));
        } catch (IOException e) {
            throw new IllegalStateException("Error reading class file of " + typeDescription + " or " + typeDescription2, e);
        }
    }

    private static Dispatcher.Unresolved locate(Class<? extends Annotation> cls, MethodDescription.InDefinedShape inDefinedShape, Dispatcher.Unresolved unresolved, MethodDescription.InDefinedShape inDefinedShape2) {
        AnnotationDescription.Loadable loadableOfType = inDefinedShape2.getDeclaredAnnotations().ofType(cls);
        if (loadableOfType == null) {
            return unresolved;
        }
        if (unresolved.isAlive()) {
            throw new IllegalStateException("Duplicate advice for " + unresolved + " and " + inDefinedShape2);
        }
        if (inDefinedShape2.isStatic()) {
            return ((Boolean) loadableOfType.getValue(inDefinedShape).resolve(Boolean.class)).booleanValue() ? new Dispatcher.Inlining(inDefinedShape2) : new Dispatcher.Delegating(inDefinedShape2);
        }
        throw new IllegalStateException("Advice for " + inDefinedShape2 + " is not static");
    }

    public static WithCustomMapping withCustomMapping() {
        return new WithCustomMapping();
    }

    public AsmVisitorWrapper.ForDeclaredMethods on(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new AsmVisitorWrapper.ForDeclaredMethods().method(elementMatcher, this);
    }

    @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper
    public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
        return (methodDescription.isAbstract() || methodDescription.isNative()) ? methodVisitor : doWrap(typeDescription, methodDescription, methodVisitor, context, i, i2);
    }

    protected MethodVisitor doWrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, int i, int i2) {
        MethodVisitor lineNumberPrependingMethodVisitor = this.methodEnter.isPrependLineNumber() ? new LineNumberPrependingMethodVisitor(methodVisitor) : methodVisitor;
        if (!this.methodExit.isAlive()) {
            return new AdviceVisitor.WithoutExitAdvice(lineNumberPrependingMethodVisitor, context, this.assigner, this.exceptionHandler, typeDescription, methodDescription, this.methodEnter, i, i2);
        }
        if (this.methodExit.getThrowable().represents(NoExceptionHandler.class)) {
            return new AdviceVisitor.WithExitAdvice.WithoutExceptionHandling(lineNumberPrependingMethodVisitor, context, this.assigner, this.exceptionHandler, typeDescription, methodDescription, this.methodEnter, this.methodExit, i, i2);
        }
        if (methodDescription.isConstructor()) {
            throw new IllegalStateException("Cannot catch exception during constructor call for " + methodDescription);
        }
        Assigner assigner = this.assigner;
        StackManipulation stackManipulation = this.exceptionHandler;
        Dispatcher.Resolved.ForMethodEnter forMethodEnter = this.methodEnter;
        Dispatcher.Resolved.ForMethodExit forMethodExit = this.methodExit;
        return new AdviceVisitor.WithExitAdvice.WithExceptionHandling(lineNumberPrependingMethodVisitor, context, assigner, stackManipulation, typeDescription, methodDescription, forMethodEnter, forMethodExit, i, i2, forMethodExit.getThrowable());
    }

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return this.delegate.prepare(instrumentedType);
    }

    @Override // net.bytebuddy.implementation.Implementation
    public ByteCodeAppender appender(Implementation.Target target) {
        return new Appender(this, target, this.delegate.appender(target));
    }

    public Advice withAssigner(Assigner assigner) {
        return new Advice(this.methodEnter, this.methodExit, assigner, this.exceptionHandler, this.delegate);
    }

    public Advice withExceptionPrinting() {
        try {
            return withExceptionHandler(MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(Throwable.class.getMethod("printStackTrace", new Class[0]))));
        } catch (NoSuchMethodException unused) {
            throw new IllegalStateException("Cannot locate Throwable::printStackTrace");
        }
    }

    public Advice withExceptionHandler(StackManipulation stackManipulation) {
        return new Advice(this.methodEnter, this.methodExit, this.assigner, stackManipulation, this.delegate);
    }

    public Implementation wrap(Implementation implementation) {
        return new Advice(this.methodEnter, this.methodExit, this.assigner, this.exceptionHandler, implementation);
    }

    public interface OffsetMapping {
        Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context);

        public interface Context {
            int getPadding();

            boolean isInitialized();

            public enum ForMethodEntry implements Context {
                INITIALIZED(true),
                NON_INITIALIZED(false);

                private final boolean initialized;

                protected static Context of(MethodDescription methodDescription) {
                    return methodDescription.isConstructor() ? NON_INITIALIZED : INITIALIZED;
                }

                ForMethodEntry(boolean z) {
                    this.initialized = z;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Context
                public boolean isInitialized() {
                    return this.initialized;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Context
                public int getPadding() {
                    return StackSize.ZERO.getSize();
                }
            }

            public enum ForMethodExit implements Context {
                ZERO(StackSize.ZERO),
                SINGLE(StackSize.SINGLE),
                DOUBLE(StackSize.DOUBLE);

                private final StackSize stackSize;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Context
                public boolean isInitialized() {
                    return true;
                }

                ForMethodExit(StackSize stackSize) {
                    this.stackSize = stackSize;
                }

                protected static Context of(TypeDefinition typeDefinition) {
                    int i = AnonymousClass1.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[typeDefinition.getStackSize().ordinal()];
                    if (i == 1) {
                        return ZERO;
                    }
                    if (i == 2) {
                        return SINGLE;
                    }
                    if (i == 3) {
                        return DOUBLE;
                    }
                    throw new IllegalStateException("Unknown stack size: " + typeDefinition);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Context
                public int getPadding() {
                    return this.stackSize.getSize();
                }
            }
        }

        public interface Target {
            StackManipulation resolveIncrement(int i);

            StackManipulation resolveRead();

            StackManipulation resolveWrite();

            public static abstract class AbstractReadOnlyAdapter implements Target {
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveWrite() {
                    throw new IllegalStateException("Cannot write to read-only value");
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveIncrement(int i) {
                    throw new IllegalStateException("Cannot write to read-only value");
                }
            }

            public static abstract class ForDefaultValue implements Target {
                protected final StackManipulation readAssignment;
                protected final TypeDefinition typeDefinition;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForDefaultValue;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForDefaultValue)) {
                        return false;
                    }
                    ForDefaultValue forDefaultValue = (ForDefaultValue) obj;
                    if (!forDefaultValue.canEqual(this)) {
                        return false;
                    }
                    TypeDefinition typeDefinition = this.typeDefinition;
                    TypeDefinition typeDefinition2 = forDefaultValue.typeDefinition;
                    if (typeDefinition != null ? !typeDefinition.equals(typeDefinition2) : typeDefinition2 != null) {
                        return false;
                    }
                    StackManipulation stackManipulation = this.readAssignment;
                    StackManipulation stackManipulation2 = forDefaultValue.readAssignment;
                    return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
                }

                public int hashCode() {
                    TypeDefinition typeDefinition = this.typeDefinition;
                    int iHashCode = typeDefinition == null ? 43 : typeDefinition.hashCode();
                    StackManipulation stackManipulation = this.readAssignment;
                    return ((iHashCode + 59) * 59) + (stackManipulation != null ? stackManipulation.hashCode() : 43);
                }

                protected ForDefaultValue(TypeDefinition typeDefinition, StackManipulation stackManipulation) {
                    this.typeDefinition = typeDefinition;
                    this.readAssignment = stackManipulation;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveRead() {
                    return new StackManipulation.Compound(DefaultValue.of(this.typeDefinition), this.readAssignment);
                }

                public static class ReadOnly extends ForDefaultValue {
                    public ReadOnly(TypeDefinition typeDefinition) {
                        this(typeDefinition, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadOnly(TypeDefinition typeDefinition, StackManipulation stackManipulation) {
                        super(typeDefinition, stackManipulation);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only default value");
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot write to read-only default value");
                    }
                }

                public static class ReadWrite extends ForDefaultValue {
                    public ReadWrite(TypeDefinition typeDefinition) {
                        this(typeDefinition, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadWrite(TypeDefinition typeDefinition, StackManipulation stackManipulation) {
                        super(typeDefinition, stackManipulation);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        return Removal.of(this.typeDefinition);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        return StackManipulation.Trivial.INSTANCE;
                    }
                }
            }

            public static abstract class ForVariable implements Target {
                protected final int offset;
                protected final StackManipulation readAssignment;
                protected final TypeDefinition typeDefinition;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForVariable;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForVariable)) {
                        return false;
                    }
                    ForVariable forVariable = (ForVariable) obj;
                    if (!forVariable.canEqual(this)) {
                        return false;
                    }
                    TypeDefinition typeDefinition = this.typeDefinition;
                    TypeDefinition typeDefinition2 = forVariable.typeDefinition;
                    if (typeDefinition != null ? !typeDefinition.equals(typeDefinition2) : typeDefinition2 != null) {
                        return false;
                    }
                    if (this.offset != forVariable.offset) {
                        return false;
                    }
                    StackManipulation stackManipulation = this.readAssignment;
                    StackManipulation stackManipulation2 = forVariable.readAssignment;
                    return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
                }

                public int hashCode() {
                    TypeDefinition typeDefinition = this.typeDefinition;
                    int iHashCode = (((typeDefinition == null ? 43 : typeDefinition.hashCode()) + 59) * 59) + this.offset;
                    StackManipulation stackManipulation = this.readAssignment;
                    return (iHashCode * 59) + (stackManipulation != null ? stackManipulation.hashCode() : 43);
                }

                protected ForVariable(TypeDefinition typeDefinition, int i, StackManipulation stackManipulation) {
                    this.typeDefinition = typeDefinition;
                    this.offset = i;
                    this.readAssignment = stackManipulation;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveRead() {
                    return new StackManipulation.Compound(MethodVariableAccess.of(this.typeDefinition).loadFrom(this.offset), this.readAssignment);
                }

                public static class ReadOnly extends ForVariable {
                    public ReadOnly(ParameterDescription parameterDescription) {
                        this(parameterDescription, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadOnly(ParameterDescription parameterDescription, StackManipulation stackManipulation) {
                        this(parameterDescription.getType(), parameterDescription.getOffset(), stackManipulation);
                    }

                    public ReadOnly(TypeDefinition typeDefinition, int i) {
                        this(typeDefinition, i, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadOnly(TypeDefinition typeDefinition, int i, StackManipulation stackManipulation) {
                        super(typeDefinition, i, stackManipulation);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only parameter " + this.typeDefinition + " at " + this.offset);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot write to read-only variable " + this.typeDefinition + " at " + this.offset);
                    }
                }

                public static class ReadWrite extends ForVariable {
                    private final StackManipulation writeAssignment;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForVariable
                    protected boolean canEqual(Object obj) {
                        return obj instanceof ReadWrite;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForVariable
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ReadWrite)) {
                            return false;
                        }
                        ReadWrite readWrite = (ReadWrite) obj;
                        if (!readWrite.canEqual(this) || !super.equals(obj)) {
                            return false;
                        }
                        StackManipulation stackManipulation = this.writeAssignment;
                        StackManipulation stackManipulation2 = readWrite.writeAssignment;
                        return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForVariable
                    public int hashCode() {
                        int iHashCode = super.hashCode() + 59;
                        StackManipulation stackManipulation = this.writeAssignment;
                        return (iHashCode * 59) + (stackManipulation == null ? 43 : stackManipulation.hashCode());
                    }

                    public ReadWrite(ParameterDescription parameterDescription) {
                        this(parameterDescription, StackManipulation.Trivial.INSTANCE, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadWrite(ParameterDescription parameterDescription, StackManipulation stackManipulation, StackManipulation stackManipulation2) {
                        this(parameterDescription.getType(), parameterDescription.getOffset(), stackManipulation, stackManipulation2);
                    }

                    public ReadWrite(TypeDefinition typeDefinition, int i) {
                        this(typeDefinition, i, StackManipulation.Trivial.INSTANCE, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadWrite(TypeDefinition typeDefinition, int i, StackManipulation stackManipulation, StackManipulation stackManipulation2) {
                        super(typeDefinition, i, stackManipulation);
                        this.writeAssignment = stackManipulation2;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        return new StackManipulation.Compound(this.writeAssignment, MethodVariableAccess.of(this.typeDefinition).storeAt(this.offset));
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        return this.typeDefinition.represents(Integer.TYPE) ? MethodVariableAccess.of(this.typeDefinition).increment(this.offset, i) : new StackManipulation.Compound(resolveRead(), IntegerConstant.forValue(1), Addition.INTEGER, resolveWrite());
                    }
                }
            }

            public static abstract class ForArray implements Target {
                protected final TypeDescription.Generic target;
                protected final List<? extends StackManipulation> valueReads;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForArray;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForArray)) {
                        return false;
                    }
                    ForArray forArray = (ForArray) obj;
                    if (!forArray.canEqual(this)) {
                        return false;
                    }
                    TypeDescription.Generic generic = this.target;
                    TypeDescription.Generic generic2 = forArray.target;
                    if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                        return false;
                    }
                    List<? extends StackManipulation> list = this.valueReads;
                    List<? extends StackManipulation> list2 = forArray.valueReads;
                    return list != null ? list.equals(list2) : list2 == null;
                }

                public int hashCode() {
                    TypeDescription.Generic generic = this.target;
                    int iHashCode = generic == null ? 43 : generic.hashCode();
                    List<? extends StackManipulation> list = this.valueReads;
                    return ((iHashCode + 59) * 59) + (list != null ? list.hashCode() : 43);
                }

                protected ForArray(TypeDescription.Generic generic, List<? extends StackManipulation> list) {
                    this.target = generic;
                    this.valueReads = list;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveRead() {
                    return ArrayFactory.forType(this.target).withValues(this.valueReads);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveIncrement(int i) {
                    throw new IllegalStateException("Cannot increment read-only array value");
                }

                public static class ReadOnly extends ForArray {
                    public ReadOnly(TypeDescription.Generic generic, List<? extends StackManipulation> list) {
                        super(generic, list);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only array value");
                    }
                }

                public static class ReadWrite extends ForArray {
                    private final List<? extends StackManipulation> valueWrites;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray
                    protected boolean canEqual(Object obj) {
                        return obj instanceof ReadWrite;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ReadWrite)) {
                            return false;
                        }
                        ReadWrite readWrite = (ReadWrite) obj;
                        if (!readWrite.canEqual(this) || !super.equals(obj)) {
                            return false;
                        }
                        List<? extends StackManipulation> list = this.valueWrites;
                        List<? extends StackManipulation> list2 = readWrite.valueWrites;
                        return list != null ? list.equals(list2) : list2 == null;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray
                    public int hashCode() {
                        int iHashCode = super.hashCode() + 59;
                        List<? extends StackManipulation> list = this.valueWrites;
                        return (iHashCode * 59) + (list == null ? 43 : list.hashCode());
                    }

                    public ReadWrite(TypeDescription.Generic generic, List<? extends StackManipulation> list, List<? extends StackManipulation> list2) {
                        super(generic, list);
                        this.valueWrites = list2;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        return ArrayAccess.of(this.target).forEach(this.valueWrites);
                    }
                }
            }

            public static abstract class ForField implements Target {
                protected final FieldDescription fieldDescription;
                protected final StackManipulation readAssignment;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForField;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForField)) {
                        return false;
                    }
                    ForField forField = (ForField) obj;
                    if (!forField.canEqual(this)) {
                        return false;
                    }
                    FieldDescription fieldDescription = this.fieldDescription;
                    FieldDescription fieldDescription2 = forField.fieldDescription;
                    if (fieldDescription != null ? !fieldDescription.equals(fieldDescription2) : fieldDescription2 != null) {
                        return false;
                    }
                    StackManipulation stackManipulation = this.readAssignment;
                    StackManipulation stackManipulation2 = forField.readAssignment;
                    return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
                }

                public int hashCode() {
                    FieldDescription fieldDescription = this.fieldDescription;
                    int iHashCode = fieldDescription == null ? 43 : fieldDescription.hashCode();
                    StackManipulation stackManipulation = this.readAssignment;
                    return ((iHashCode + 59) * 59) + (stackManipulation != null ? stackManipulation.hashCode() : 43);
                }

                protected ForField(FieldDescription fieldDescription, StackManipulation stackManipulation) {
                    this.fieldDescription = fieldDescription;
                    this.readAssignment = stackManipulation;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveRead() {
                    StackManipulation[] stackManipulationArr = new StackManipulation[3];
                    stackManipulationArr[0] = this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                    stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                    stackManipulationArr[2] = this.readAssignment;
                    return new StackManipulation.Compound(stackManipulationArr);
                }

                public static class ReadOnly extends ForField {
                    public ReadOnly(FieldDescription fieldDescription) {
                        this(fieldDescription, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadOnly(FieldDescription fieldDescription, StackManipulation stackManipulation) {
                        super(fieldDescription, stackManipulation);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only field value");
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot write to read-only field value");
                    }
                }

                public static class ReadWrite extends ForField {
                    private final StackManipulation writeAssignment;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForField
                    protected boolean canEqual(Object obj) {
                        return obj instanceof ReadWrite;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForField
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ReadWrite)) {
                            return false;
                        }
                        ReadWrite readWrite = (ReadWrite) obj;
                        if (!readWrite.canEqual(this) || !super.equals(obj)) {
                            return false;
                        }
                        StackManipulation stackManipulation = this.writeAssignment;
                        StackManipulation stackManipulation2 = readWrite.writeAssignment;
                        return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target.ForField
                    public int hashCode() {
                        int iHashCode = super.hashCode() + 59;
                        StackManipulation stackManipulation = this.writeAssignment;
                        return (iHashCode * 59) + (stackManipulation == null ? 43 : stackManipulation.hashCode());
                    }

                    public ReadWrite(FieldDescription fieldDescription) {
                        this(fieldDescription, StackManipulation.Trivial.INSTANCE, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadWrite(FieldDescription fieldDescription, StackManipulation stackManipulation, StackManipulation stackManipulation2) {
                        super(fieldDescription, stackManipulation);
                        this.writeAssignment = stackManipulation2;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveWrite() {
                        StackManipulation compound;
                        if (this.fieldDescription.isStatic()) {
                            compound = StackManipulation.Trivial.INSTANCE;
                        } else {
                            compound = new StackManipulation.Compound(MethodVariableAccess.loadThis(), Duplication.SINGLE.flipOver(this.fieldDescription.getType()), Removal.SINGLE);
                        }
                        return new StackManipulation.Compound(this.writeAssignment, compound, FieldAccess.forField(this.fieldDescription).write());
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                    public StackManipulation resolveIncrement(int i) {
                        return new StackManipulation.Compound(resolveRead(), IntegerConstant.forValue(i), Addition.INTEGER, resolveWrite());
                    }
                }
            }

            public static class ForStackManipulation implements Target {
                private final StackManipulation stackManipulation;

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForStackManipulation;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForStackManipulation)) {
                        return false;
                    }
                    ForStackManipulation forStackManipulation = (ForStackManipulation) obj;
                    if (!forStackManipulation.canEqual(this)) {
                        return false;
                    }
                    StackManipulation stackManipulation = this.stackManipulation;
                    StackManipulation stackManipulation2 = forStackManipulation.stackManipulation;
                    return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
                }

                public int hashCode() {
                    StackManipulation stackManipulation = this.stackManipulation;
                    return 59 + (stackManipulation == null ? 43 : stackManipulation.hashCode());
                }

                public ForStackManipulation(StackManipulation stackManipulation) {
                    this.stackManipulation = stackManipulation;
                }

                public static Target of(MethodDescription.InDefinedShape inDefinedShape) {
                    return new ForStackManipulation(MethodConstant.forMethod(inDefinedShape));
                }

                public static Target of(TypeDescription typeDescription) {
                    return new ForStackManipulation(ClassConstant.of(typeDescription));
                }

                public static Target of(Object obj) {
                    if (obj == null) {
                        return new ForStackManipulation(NullConstant.INSTANCE);
                    }
                    if (obj instanceof Boolean) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Boolean) obj).booleanValue()));
                    }
                    if (obj instanceof Byte) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Byte) obj).byteValue()));
                    }
                    if (obj instanceof Short) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Short) obj).shortValue()));
                    }
                    if (obj instanceof Character) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Character) obj).charValue()));
                    }
                    if (obj instanceof Integer) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Integer) obj).intValue()));
                    }
                    if (obj instanceof Long) {
                        return new ForStackManipulation(LongConstant.forValue(((Long) obj).longValue()));
                    }
                    if (obj instanceof Float) {
                        return new ForStackManipulation(FloatConstant.forValue(((Float) obj).floatValue()));
                    }
                    if (obj instanceof Double) {
                        return new ForStackManipulation(DoubleConstant.forValue(((Double) obj).doubleValue()));
                    }
                    if (obj instanceof String) {
                        return new ForStackManipulation(new TextConstant((String) obj));
                    }
                    throw new IllegalArgumentException("Not a constant value: " + obj);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveRead() {
                    return this.stackManipulation;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveWrite() {
                    throw new IllegalStateException("Cannot write to constant value: " + this.stackManipulation);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Target
                public StackManipulation resolveIncrement(int i) {
                    throw new IllegalStateException("Cannot write to constant value: " + this.stackManipulation);
                }
            }
        }

        public interface Factory<T extends Annotation> {
            Class<T> getAnnotationType();

            OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, AdviceType adviceType);

            public enum AdviceType {
                DELEGATION(true),
                INLINING(false);

                private final boolean delegation;

                AdviceType(boolean z) {
                    this.delegation = z;
                }

                public boolean isDelegation() {
                    return this.delegation;
                }
            }

            public static class Simple<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final OffsetMapping offsetMapping;

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
                    Class<T> annotationType = getAnnotationType();
                    Class<T> annotationType2 = simple.getAnnotationType();
                    if (annotationType != null ? !annotationType.equals(annotationType2) : annotationType2 != null) {
                        return false;
                    }
                    OffsetMapping offsetMapping = this.offsetMapping;
                    OffsetMapping offsetMapping2 = simple.offsetMapping;
                    return offsetMapping != null ? offsetMapping.equals(offsetMapping2) : offsetMapping2 == null;
                }

                public int hashCode() {
                    Class<T> annotationType = getAnnotationType();
                    int iHashCode = annotationType == null ? 43 : annotationType.hashCode();
                    OffsetMapping offsetMapping = this.offsetMapping;
                    return ((iHashCode + 59) * 59) + (offsetMapping != null ? offsetMapping.hashCode() : 43);
                }

                public Simple(Class<T> cls, OffsetMapping offsetMapping) {
                    this.annotationType = cls;
                    this.offsetMapping = offsetMapping;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, AdviceType adviceType) {
                    return this.offsetMapping;
                }
            }

            public static class Illegal<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;

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
                    Class<T> annotationType = getAnnotationType();
                    Class<T> annotationType2 = illegal.getAnnotationType();
                    return annotationType != null ? annotationType.equals(annotationType2) : annotationType2 == null;
                }

                public int hashCode() {
                    Class<T> annotationType = getAnnotationType();
                    return 59 + (annotationType == null ? 43 : annotationType.hashCode());
                }

                public Illegal(Class<T> cls) {
                    this.annotationType = cls;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, AdviceType adviceType) {
                    throw new IllegalStateException("Usage of " + this.annotationType + " is not allowed on " + inDefinedShape);
                }
            }
        }

        public static abstract class ForArgument implements OffsetMapping {
            protected final boolean readOnly;
            protected final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            protected abstract ParameterDescription resolve(MethodDescription methodDescription);

            protected boolean canEqual(Object obj) {
                return obj instanceof ForArgument;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForArgument)) {
                    return false;
                }
                ForArgument forArgument = (ForArgument) obj;
                if (!forArgument.canEqual(this)) {
                    return false;
                }
                TypeDescription.Generic generic = this.target;
                TypeDescription.Generic generic2 = forArgument.target;
                if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                    return false;
                }
                if (this.readOnly != forArgument.readOnly) {
                    return false;
                }
                Assigner.Typing typing = this.typing;
                Assigner.Typing typing2 = forArgument.typing;
                return typing != null ? typing.equals(typing2) : typing2 == null;
            }

            public int hashCode() {
                TypeDescription.Generic generic = this.target;
                int iHashCode = (((generic == null ? 43 : generic.hashCode()) + 59) * 59) + (this.readOnly ? 79 : 97);
                Assigner.Typing typing = this.typing;
                return (iHashCode * 59) + (typing != null ? typing.hashCode() : 43);
            }

            protected ForArgument(TypeDescription.Generic generic, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                ParameterDescription parameterDescriptionResolve = resolve(methodDescription);
                StackManipulation stackManipulationAssign = assigner.assign(parameterDescriptionResolve.getType(), this.target, this.typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + parameterDescriptionResolve + " to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(parameterDescriptionResolve, stackManipulationAssign);
                }
                StackManipulation stackManipulationAssign2 = assigner.assign(this.target, parameterDescriptionResolve.getType(), this.typing);
                if (!stackManipulationAssign2.isValid()) {
                    throw new IllegalStateException("Cannot assign " + parameterDescriptionResolve + " to " + this.target);
                }
                return new Target.ForVariable.ReadWrite(parameterDescriptionResolve, stackManipulationAssign, stackManipulationAssign2);
            }

            public static class Unresolved extends ForArgument {
                private final int index;
                private final boolean optional;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                protected boolean canEqual(Object obj) {
                    return obj instanceof Unresolved;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Unresolved)) {
                        return false;
                    }
                    Unresolved unresolved = (Unresolved) obj;
                    return unresolved.canEqual(this) && super.equals(obj) && this.index == unresolved.index && this.optional == unresolved.optional;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                public int hashCode() {
                    return ((((super.hashCode() + 59) * 59) + this.index) * 59) + (this.optional ? 79 : 97);
                }

                protected Unresolved(TypeDescription.Generic generic, Argument argument) {
                    this(generic, argument.readOnly(), argument.typing(), argument.value(), argument.optional());
                }

                protected Unresolved(ParameterDescription parameterDescription) {
                    this(parameterDescription.getType(), true, Assigner.Typing.STATIC, parameterDescription.getIndex());
                }

                public Unresolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, int i) {
                    this(generic, z, typing, i, false);
                }

                public Unresolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, int i, boolean z2) {
                    super(generic, z, typing);
                    this.index = i;
                    this.optional = z2;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                protected ParameterDescription resolve(MethodDescription methodDescription) {
                    ParameterList<?> parameters = methodDescription.getParameters();
                    int size = parameters.size();
                    int i = this.index;
                    if (size <= i) {
                        throw new IllegalStateException(methodDescription + " does not define an index " + this.index);
                    }
                    return (ParameterDescription) parameters.get(i);
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument, net.bytebuddy.asm.Advice.OffsetMapping
                public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                    if (!this.optional || methodDescription.getParameters().size() > this.index) {
                        return super.resolve(typeDescription, methodDescription, assigner, context);
                    }
                    return this.readOnly ? new Target.ForDefaultValue.ReadOnly(this.target) : new Target.ForDefaultValue.ReadWrite(this.target);
                }

                protected enum Factory implements Factory<Argument> {
                    INSTANCE;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public Class<Argument> getAnnotationType() {
                        return Argument.class;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Argument> loadable, Factory.AdviceType adviceType) {
                        if (adviceType.isDelegation() && !((Argument) loadable.loadSilent()).readOnly()) {
                            throw new IllegalStateException("Cannot define writable field access for " + inDefinedShape + " when using delegation");
                        }
                        return new Unresolved(inDefinedShape.getType(), (Argument) loadable.loadSilent());
                    }
                }
            }

            public static class Resolved extends ForArgument {
                private final ParameterDescription parameterDescription;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                protected boolean canEqual(Object obj) {
                    return obj instanceof Resolved;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Resolved)) {
                        return false;
                    }
                    Resolved resolved = (Resolved) obj;
                    if (!resolved.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    ParameterDescription parameterDescription = this.parameterDescription;
                    ParameterDescription parameterDescription2 = resolved.parameterDescription;
                    return parameterDescription != null ? parameterDescription.equals(parameterDescription2) : parameterDescription2 == null;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    ParameterDescription parameterDescription = this.parameterDescription;
                    return (iHashCode * 59) + (parameterDescription == null ? 43 : parameterDescription.hashCode());
                }

                public Resolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, ParameterDescription parameterDescription) {
                    super(generic, z, typing);
                    this.parameterDescription = parameterDescription;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForArgument
                protected ParameterDescription resolve(MethodDescription methodDescription) {
                    if (!this.parameterDescription.getDeclaringMethod().equals(methodDescription)) {
                        throw new IllegalStateException(this.parameterDescription + " is not a parameter of " + methodDescription);
                    }
                    return this.parameterDescription;
                }

                public static class Factory<T extends Annotation> implements Factory<T> {
                    private final Class<T> annotationType;
                    private final ParameterDescription parameterDescription;
                    private final boolean readOnly;
                    private final Assigner.Typing typing;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Factory;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Factory)) {
                            return false;
                        }
                        Factory factory = (Factory) obj;
                        if (!factory.canEqual(this)) {
                            return false;
                        }
                        Class<T> annotationType = getAnnotationType();
                        Class<T> annotationType2 = factory.getAnnotationType();
                        if (annotationType != null ? !annotationType.equals(annotationType2) : annotationType2 != null) {
                            return false;
                        }
                        ParameterDescription parameterDescription = this.parameterDescription;
                        ParameterDescription parameterDescription2 = factory.parameterDescription;
                        if (parameterDescription != null ? !parameterDescription.equals(parameterDescription2) : parameterDescription2 != null) {
                            return false;
                        }
                        if (this.readOnly != factory.readOnly) {
                            return false;
                        }
                        Assigner.Typing typing = this.typing;
                        Assigner.Typing typing2 = factory.typing;
                        return typing != null ? typing.equals(typing2) : typing2 == null;
                    }

                    public int hashCode() {
                        Class<T> annotationType = getAnnotationType();
                        int iHashCode = annotationType == null ? 43 : annotationType.hashCode();
                        ParameterDescription parameterDescription = this.parameterDescription;
                        int iHashCode2 = ((((iHashCode + 59) * 59) + (parameterDescription == null ? 43 : parameterDescription.hashCode())) * 59) + (this.readOnly ? 79 : 97);
                        Assigner.Typing typing = this.typing;
                        return (iHashCode2 * 59) + (typing != null ? typing.hashCode() : 43);
                    }

                    public Factory(Class<T> cls, ParameterDescription parameterDescription) {
                        this(cls, parameterDescription, true, Assigner.Typing.STATIC);
                    }

                    public Factory(Class<T> cls, ParameterDescription parameterDescription, boolean z, Assigner.Typing typing) {
                        this.annotationType = cls;
                        this.parameterDescription = parameterDescription;
                        this.readOnly = z;
                        this.typing = typing;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public Class<T> getAnnotationType() {
                        return this.annotationType;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                        return new Resolved(inDefinedShape.getType(), this.readOnly, this.typing, this.parameterDescription);
                    }
                }
            }
        }

        public static class ForThisReference implements OffsetMapping {
            private static final int THIS_REFERENCE = 0;
            private final boolean optional;
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForThisReference;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForThisReference)) {
                    return false;
                }
                ForThisReference forThisReference = (ForThisReference) obj;
                if (!forThisReference.canEqual(this)) {
                    return false;
                }
                TypeDescription.Generic generic = this.target;
                TypeDescription.Generic generic2 = forThisReference.target;
                if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                    return false;
                }
                if (this.readOnly != forThisReference.readOnly) {
                    return false;
                }
                Assigner.Typing typing = this.typing;
                Assigner.Typing typing2 = forThisReference.typing;
                if (typing != null ? typing.equals(typing2) : typing2 == null) {
                    return this.optional == forThisReference.optional;
                }
                return false;
            }

            public int hashCode() {
                TypeDescription.Generic generic = this.target;
                int iHashCode = (((generic == null ? 43 : generic.hashCode()) + 59) * 59) + (this.readOnly ? 79 : 97);
                Assigner.Typing typing = this.typing;
                return (((iHashCode * 59) + (typing != null ? typing.hashCode() : 43)) * 59) + (this.optional ? 79 : 97);
            }

            protected ForThisReference(TypeDescription.Generic generic, This r4) {
                this(generic, r4.readOnly(), r4.typing(), r4.optional());
            }

            public ForThisReference(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, boolean z2) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
                this.optional = z2;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                if (methodDescription.isStatic() || !context.isInitialized()) {
                    if (this.optional) {
                        return this.readOnly ? new Target.ForDefaultValue.ReadOnly(typeDescription) : new Target.ForDefaultValue.ReadWrite(typeDescription);
                    }
                    throw new IllegalStateException("Cannot map this reference for static method or constructor start: " + methodDescription);
                }
                StackManipulation stackManipulationAssign = assigner.assign(typeDescription.asGenericType(), this.target, this.typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + typeDescription + " to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(typeDescription.asGenericType(), 0, stackManipulationAssign);
                }
                StackManipulation stackManipulationAssign2 = assigner.assign(this.target, typeDescription.asGenericType(), this.typing);
                if (!stackManipulationAssign2.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.target + " to " + typeDescription);
                }
                return new Target.ForVariable.ReadWrite(typeDescription.asGenericType(), 0, stackManipulationAssign, stackManipulationAssign2);
            }

            protected enum Factory implements Factory<This> {
                INSTANCE;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<This> getAnnotationType() {
                    return This.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<This> loadable, Factory.AdviceType adviceType) {
                    if (adviceType.isDelegation() && !((This) loadable.loadSilent()).readOnly()) {
                        throw new IllegalStateException("Cannot write to this reference for " + inDefinedShape + " in read-only context");
                    }
                    return new ForThisReference(inDefinedShape.getType(), (This) loadable.loadSilent());
                }
            }
        }

        public static class ForAllArguments implements OffsetMapping {
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForAllArguments;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForAllArguments)) {
                    return false;
                }
                ForAllArguments forAllArguments = (ForAllArguments) obj;
                if (!forAllArguments.canEqual(this)) {
                    return false;
                }
                TypeDescription.Generic generic = this.target;
                TypeDescription.Generic generic2 = forAllArguments.target;
                if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                    return false;
                }
                if (this.readOnly != forAllArguments.readOnly) {
                    return false;
                }
                Assigner.Typing typing = this.typing;
                Assigner.Typing typing2 = forAllArguments.typing;
                return typing != null ? typing.equals(typing2) : typing2 == null;
            }

            public int hashCode() {
                TypeDescription.Generic generic = this.target;
                int iHashCode = (((generic == null ? 43 : generic.hashCode()) + 59) * 59) + (this.readOnly ? 79 : 97);
                Assigner.Typing typing = this.typing;
                return (iHashCode * 59) + (typing != null ? typing.hashCode() : 43);
            }

            protected ForAllArguments(TypeDescription.Generic generic, AllArguments allArguments) {
                this(generic, allArguments.readOnly(), allArguments.typing());
            }

            public ForAllArguments(TypeDescription.Generic generic, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
                Iterator it = methodDescription.getParameters().iterator();
                while (it.hasNext()) {
                    ParameterDescription parameterDescription = (ParameterDescription) it.next();
                    StackManipulation stackManipulationAssign = assigner.assign(parameterDescription.getType(), this.target, this.typing);
                    if (!stackManipulationAssign.isValid()) {
                        throw new IllegalStateException("Cannot assign " + parameterDescription + " to " + this.target);
                    }
                    arrayList.add(new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription), stackManipulationAssign));
                }
                if (this.readOnly) {
                    return new Target.ForArray.ReadOnly(this.target, arrayList);
                }
                ArrayList arrayList2 = new ArrayList(methodDescription.getParameters().size());
                Iterator it2 = methodDescription.getParameters().iterator();
                while (it2.hasNext()) {
                    ParameterDescription parameterDescription2 = (ParameterDescription) it2.next();
                    StackManipulation stackManipulationAssign2 = assigner.assign(this.target, parameterDescription2.getType(), this.typing);
                    if (!stackManipulationAssign2.isValid()) {
                        throw new IllegalStateException("Cannot assign " + this.target + " to " + parameterDescription2);
                    }
                    arrayList2.add(new StackManipulation.Compound(stackManipulationAssign2, MethodVariableAccess.store(parameterDescription2)));
                }
                return new Target.ForArray.ReadWrite(this.target, arrayList, arrayList2);
            }

            protected enum Factory implements Factory<AllArguments> {
                INSTANCE;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<AllArguments> getAnnotationType() {
                    return AllArguments.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<AllArguments> loadable, Factory.AdviceType adviceType) {
                    if (!inDefinedShape.getType().represents(Object.class) && !inDefinedShape.getType().isArray()) {
                        throw new IllegalStateException("Cannot use AllArguments annotation on a non-array type");
                    }
                    if (adviceType.isDelegation() && !((AllArguments) loadable.loadSilent()).readOnly()) {
                        throw new IllegalStateException("Cannot define writable field access for " + inDefinedShape);
                    }
                    return new ForAllArguments(inDefinedShape.getType().represents(Object.class) ? TypeDescription.Generic.OBJECT : inDefinedShape.getType().getComponentType(), (AllArguments) loadable.loadSilent());
                }
            }
        }

        public enum ForInstrumentedType implements OffsetMapping {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                return Target.ForStackManipulation.of(typeDescription);
            }
        }

        public enum ForInstrumentedMethod implements OffsetMapping {
            METHOD { // from class: net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod.1
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected boolean isRepresentable(MethodDescription methodDescription) {
                    return methodDescription.isMethod();
                }
            },
            CONSTRUCTOR { // from class: net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod.2
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected boolean isRepresentable(MethodDescription methodDescription) {
                    return methodDescription.isConstructor();
                }
            },
            EXECUTABLE { // from class: net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod.3
                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForInstrumentedMethod
                protected boolean isRepresentable(MethodDescription methodDescription) {
                    return true;
                }
            };

            protected abstract boolean isRepresentable(MethodDescription methodDescription);

            /* synthetic */ ForInstrumentedMethod(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                if (!isRepresentable(methodDescription)) {
                    throw new IllegalStateException("Cannot represent " + methodDescription + " as given method constant");
                }
                return Target.ForStackManipulation.of(methodDescription.asDefined());
            }
        }

        public static abstract class ForField implements OffsetMapping {
            private static final MethodDescription.InDefinedShape DECLARING_TYPE;
            private static final MethodDescription.InDefinedShape READ_ONLY;
            private static final MethodDescription.InDefinedShape TYPING;
            private static final MethodDescription.InDefinedShape VALUE;
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            protected abstract FieldDescription resolve(TypeDescription typeDescription);

            protected boolean canEqual(Object obj) {
                return obj instanceof ForField;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForField)) {
                    return false;
                }
                ForField forField = (ForField) obj;
                if (!forField.canEqual(this)) {
                    return false;
                }
                TypeDescription.Generic generic = this.target;
                TypeDescription.Generic generic2 = forField.target;
                if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                    return false;
                }
                if (this.readOnly != forField.readOnly) {
                    return false;
                }
                Assigner.Typing typing = this.typing;
                Assigner.Typing typing2 = forField.typing;
                return typing != null ? typing.equals(typing2) : typing2 == null;
            }

            public int hashCode() {
                TypeDescription.Generic generic = this.target;
                int iHashCode = (((generic == null ? 43 : generic.hashCode()) + 59) * 59) + (this.readOnly ? 79 : 97);
                Assigner.Typing typing = this.typing;
                return (iHashCode * 59) + (typing != null ? typing.hashCode() : 43);
            }

            static {
                MethodList<MethodDescription.InDefinedShape> declaredMethods = new TypeDescription.ForLoadedType(FieldValue.class).getDeclaredMethods();
                VALUE = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named(WuwInfoBean.ValueKey)).getOnly();
                DECLARING_TYPE = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("declaringType")).getOnly();
                READ_ONLY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("readOnly")).getOnly();
                TYPING = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("typing")).getOnly();
            }

            public ForField(TypeDescription.Generic generic, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                FieldDescription fieldDescriptionResolve = resolve(typeDescription);
                if (!fieldDescriptionResolve.isStatic() && methodDescription.isStatic()) {
                    throw new IllegalStateException("Cannot read non-static field " + fieldDescriptionResolve + " from static method " + methodDescription);
                }
                if (!context.isInitialized() && !fieldDescriptionResolve.isStatic()) {
                    throw new IllegalStateException("Cannot access non-static field before calling constructor: " + methodDescription);
                }
                StackManipulation stackManipulationAssign = assigner.assign(fieldDescriptionResolve.getType(), this.target, this.typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + fieldDescriptionResolve + " to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForField.ReadOnly(fieldDescriptionResolve, stackManipulationAssign);
                }
                StackManipulation stackManipulationAssign2 = assigner.assign(this.target, fieldDescriptionResolve.getType(), this.typing);
                if (!stackManipulationAssign2.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.target + " to " + fieldDescriptionResolve);
                }
                return new Target.ForField.ReadWrite(fieldDescriptionResolve.asDefined(), stackManipulationAssign, stackManipulationAssign2);
            }

            public static abstract class Unresolved extends ForField {
                private final String name;

                protected abstract FieldLocator fieldLocator(TypeDescription typeDescription);

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                protected boolean canEqual(Object obj) {
                    return obj instanceof Unresolved;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Unresolved)) {
                        return false;
                    }
                    Unresolved unresolved = (Unresolved) obj;
                    if (!unresolved.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    String str = this.name;
                    String str2 = unresolved.name;
                    return str != null ? str.equals(str2) : str2 == null;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    String str = this.name;
                    return (iHashCode * 59) + (str == null ? 43 : str.hashCode());
                }

                public Unresolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, String str) {
                    super(generic, z, typing);
                    this.name = str;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                protected FieldDescription resolve(TypeDescription typeDescription) {
                    FieldLocator.Resolution resolutionLocate = fieldLocator(typeDescription).locate(this.name);
                    if (!resolutionLocate.isResolved()) {
                        throw new IllegalStateException("Cannot locate field named " + this.name + " for " + typeDescription);
                    }
                    return resolutionLocate.getField();
                }

                public static class WithImplicitType extends Unresolved {
                    protected WithImplicitType(TypeDescription.Generic generic, AnnotationDescription.Loadable<FieldValue> loadable) {
                        this(generic, ((Boolean) loadable.getValue(ForField.READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(ForField.TYPING).loadSilent(Assigner.Typing.class.getClassLoader()).resolve(Assigner.Typing.class), (String) loadable.getValue(ForField.VALUE).resolve(String.class));
                    }

                    public WithImplicitType(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, String str) {
                        super(generic, z, typing, str);
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved
                    protected FieldLocator fieldLocator(TypeDescription typeDescription) {
                        return new FieldLocator.ForClassHierarchy(typeDescription);
                    }
                }

                public static class WithExplicitType extends Unresolved {
                    private final TypeDescription declaringType;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved, net.bytebuddy.asm.Advice.OffsetMapping.ForField
                    protected boolean canEqual(Object obj) {
                        return obj instanceof WithExplicitType;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved, net.bytebuddy.asm.Advice.OffsetMapping.ForField
                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof WithExplicitType)) {
                            return false;
                        }
                        WithExplicitType withExplicitType = (WithExplicitType) obj;
                        if (!withExplicitType.canEqual(this) || !super.equals(obj)) {
                            return false;
                        }
                        TypeDescription typeDescription = this.declaringType;
                        TypeDescription typeDescription2 = withExplicitType.declaringType;
                        return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved, net.bytebuddy.asm.Advice.OffsetMapping.ForField
                    public int hashCode() {
                        int iHashCode = super.hashCode() + 59;
                        TypeDescription typeDescription = this.declaringType;
                        return (iHashCode * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                    }

                    protected WithExplicitType(TypeDescription.Generic generic, AnnotationDescription.Loadable<FieldValue> loadable, TypeDescription typeDescription) {
                        this(generic, ((Boolean) loadable.getValue(ForField.READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(ForField.TYPING).loadSilent(Assigner.Typing.class.getClassLoader()).resolve(Assigner.Typing.class), (String) loadable.getValue(ForField.VALUE).resolve(String.class), typeDescription);
                    }

                    public WithExplicitType(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, String str, TypeDescription typeDescription) {
                        super(generic, z, typing, str);
                        this.declaringType = typeDescription;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved
                    protected FieldLocator fieldLocator(TypeDescription typeDescription) {
                        if (!this.declaringType.represents(TargetType.class) && !typeDescription.isAssignableTo(this.declaringType)) {
                            throw new IllegalStateException(this.declaringType + " is no super type of " + typeDescription);
                        }
                        return new FieldLocator.ForExactType(TargetType.resolve(this.declaringType, typeDescription));
                    }
                }

                protected enum Factory implements Factory<FieldValue> {
                    INSTANCE;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public Class<FieldValue> getAnnotationType() {
                        return FieldValue.class;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<FieldValue> loadable, Factory.AdviceType adviceType) {
                        if (!adviceType.isDelegation() || ((Boolean) loadable.getValue(ForField.READ_ONLY).resolve(Boolean.class)).booleanValue()) {
                            TypeDescription typeDescription = (TypeDescription) loadable.getValue(ForField.DECLARING_TYPE).resolve(TypeDescription.class);
                            if (typeDescription.represents(Void.TYPE)) {
                                return new WithImplicitType(inDefinedShape.getType(), loadable);
                            }
                            return new WithExplicitType(inDefinedShape.getType(), loadable, typeDescription);
                        }
                        throw new IllegalStateException("Cannot write to field for " + inDefinedShape + " in read-only context");
                    }
                }
            }

            public static class Resolved extends ForField {
                private final FieldDescription fieldDescription;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                protected boolean canEqual(Object obj) {
                    return obj instanceof Resolved;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Resolved)) {
                        return false;
                    }
                    Resolved resolved = (Resolved) obj;
                    if (!resolved.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    FieldDescription fieldDescription = this.fieldDescription;
                    FieldDescription fieldDescription2 = resolved.fieldDescription;
                    return fieldDescription != null ? fieldDescription.equals(fieldDescription2) : fieldDescription2 == null;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    FieldDescription fieldDescription = this.fieldDescription;
                    return (iHashCode * 59) + (fieldDescription == null ? 43 : fieldDescription.hashCode());
                }

                public Resolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, FieldDescription fieldDescription) {
                    super(generic, z, typing);
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForField
                protected FieldDescription resolve(TypeDescription typeDescription) {
                    if (!this.fieldDescription.isStatic() && !this.fieldDescription.getDeclaringType().asErasure().isAssignableFrom(typeDescription)) {
                        throw new IllegalStateException(this.fieldDescription + " is no member of " + typeDescription);
                    }
                    if (!this.fieldDescription.isAccessibleTo(typeDescription)) {
                        throw new IllegalStateException("Cannot access " + this.fieldDescription + " from " + typeDescription);
                    }
                    return this.fieldDescription;
                }

                public static class Factory<T extends Annotation> implements Factory<T> {
                    private final Class<T> annotationType;
                    private final FieldDescription fieldDescription;
                    private final boolean readOnly;
                    private final Assigner.Typing typing;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Factory;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Factory)) {
                            return false;
                        }
                        Factory factory = (Factory) obj;
                        if (!factory.canEqual(this)) {
                            return false;
                        }
                        Class<T> annotationType = getAnnotationType();
                        Class<T> annotationType2 = factory.getAnnotationType();
                        if (annotationType != null ? !annotationType.equals(annotationType2) : annotationType2 != null) {
                            return false;
                        }
                        FieldDescription fieldDescription = this.fieldDescription;
                        FieldDescription fieldDescription2 = factory.fieldDescription;
                        if (fieldDescription != null ? !fieldDescription.equals(fieldDescription2) : fieldDescription2 != null) {
                            return false;
                        }
                        if (this.readOnly != factory.readOnly) {
                            return false;
                        }
                        Assigner.Typing typing = this.typing;
                        Assigner.Typing typing2 = factory.typing;
                        return typing != null ? typing.equals(typing2) : typing2 == null;
                    }

                    public int hashCode() {
                        Class<T> annotationType = getAnnotationType();
                        int iHashCode = annotationType == null ? 43 : annotationType.hashCode();
                        FieldDescription fieldDescription = this.fieldDescription;
                        int iHashCode2 = ((((iHashCode + 59) * 59) + (fieldDescription == null ? 43 : fieldDescription.hashCode())) * 59) + (this.readOnly ? 79 : 97);
                        Assigner.Typing typing = this.typing;
                        return (iHashCode2 * 59) + (typing != null ? typing.hashCode() : 43);
                    }

                    public Factory(Class<T> cls, FieldDescription fieldDescription) {
                        this(cls, fieldDescription, true, Assigner.Typing.STATIC);
                    }

                    public Factory(Class<T> cls, FieldDescription fieldDescription, boolean z, Assigner.Typing typing) {
                        this.annotationType = cls;
                        this.fieldDescription = fieldDescription;
                        this.readOnly = z;
                        this.typing = typing;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public Class<T> getAnnotationType() {
                        return this.annotationType;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                    public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                        return new Resolved(inDefinedShape.getType(), this.readOnly, this.typing, this.fieldDescription);
                    }
                }
            }
        }

        public static class ForOrigin implements OffsetMapping {
            private static final char DELIMITER = '#';
            private static final char ESCAPE = '\\';
            private final List<Renderer> renderers;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForOrigin;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForOrigin)) {
                    return false;
                }
                ForOrigin forOrigin = (ForOrigin) obj;
                if (!forOrigin.canEqual(this)) {
                    return false;
                }
                List<Renderer> list = this.renderers;
                List<Renderer> list2 = forOrigin.renderers;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<Renderer> list = this.renderers;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            public ForOrigin(List<Renderer> list) {
                this.renderers = list;
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public static net.bytebuddy.asm.Advice.OffsetMapping parse(java.lang.String r9) {
                /*
                    Method dump skipped, instruction units count: 276
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.parse(java.lang.String):net.bytebuddy.asm.Advice$OffsetMapping");
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                StringBuilder sb = new StringBuilder();
                Iterator<Renderer> it = this.renderers.iterator();
                while (it.hasNext()) {
                    sb.append(it.next().apply(typeDescription, methodDescription));
                }
                return Target.ForStackManipulation.of(sb.toString());
            }

            public interface Renderer {
                String apply(TypeDescription typeDescription, MethodDescription methodDescription);

                public enum ForMethodName implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 'm';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.getInternalName();
                    }
                }

                public enum ForTypeName implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 't';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return typeDescription.getName();
                    }
                }

                public enum ForDescriptor implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 'd';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.getDescriptor();
                    }
                }

                public enum ForJavaSignature implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 's';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        StringBuilder sb = new StringBuilder("(");
                        boolean z = false;
                        for (TypeDescription typeDescription2 : methodDescription.getParameters().asTypeList().asErasures()) {
                            if (z) {
                                sb.append(',');
                            } else {
                                z = true;
                            }
                            sb.append(typeDescription2.getName());
                        }
                        return sb.append(')').toString();
                    }
                }

                public enum ForReturnTypeName implements Renderer {
                    INSTANCE;

                    public static final char SYMBOL = 'r';

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.getReturnType().asErasure().getName();
                    }
                }

                public enum ForStringRepresentation implements Renderer {
                    INSTANCE;

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.toString();
                    }
                }

                public static class ForConstantValue implements Renderer {
                    private final String value;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof ForConstantValue;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof ForConstantValue)) {
                            return false;
                        }
                        ForConstantValue forConstantValue = (ForConstantValue) obj;
                        if (!forConstantValue.canEqual(this)) {
                            return false;
                        }
                        String str = this.value;
                        String str2 = forConstantValue.value;
                        return str != null ? str.equals(str2) : str2 == null;
                    }

                    public int hashCode() {
                        String str = this.value;
                        return 59 + (str == null ? 43 : str.hashCode());
                    }

                    public ForConstantValue(String str) {
                        this.value = str;
                    }

                    @Override // net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer
                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return this.value;
                    }
                }
            }

            protected enum Factory implements Factory<Origin> {
                INSTANCE;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<Origin> getAnnotationType() {
                    return Origin.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Origin> loadable, Factory.AdviceType adviceType) {
                    if (inDefinedShape.getType().asErasure().represents(Class.class)) {
                        return ForInstrumentedType.INSTANCE;
                    }
                    if (inDefinedShape.getType().asErasure().represents(Method.class)) {
                        return ForInstrumentedMethod.METHOD;
                    }
                    if (inDefinedShape.getType().asErasure().represents(Constructor.class)) {
                        return ForInstrumentedMethod.CONSTRUCTOR;
                    }
                    if (JavaType.EXECUTABLE.getTypeStub().equals(inDefinedShape.getType().asErasure())) {
                        return ForInstrumentedMethod.EXECUTABLE;
                    }
                    if (inDefinedShape.getType().asErasure().isAssignableFrom(String.class)) {
                        return ForOrigin.parse(((Origin) loadable.loadSilent()).value());
                    }
                    throw new IllegalStateException("Non-supported type " + inDefinedShape.getType() + " for @Origin annotation");
                }
            }
        }

        public static class ForUnusedValue implements OffsetMapping {
            private final TypeDefinition target;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForUnusedValue;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForUnusedValue)) {
                    return false;
                }
                ForUnusedValue forUnusedValue = (ForUnusedValue) obj;
                if (!forUnusedValue.canEqual(this)) {
                    return false;
                }
                TypeDefinition typeDefinition = this.target;
                TypeDefinition typeDefinition2 = forUnusedValue.target;
                return typeDefinition != null ? typeDefinition.equals(typeDefinition2) : typeDefinition2 == null;
            }

            public int hashCode() {
                TypeDefinition typeDefinition = this.target;
                return 59 + (typeDefinition == null ? 43 : typeDefinition.hashCode());
            }

            public ForUnusedValue(TypeDefinition typeDefinition) {
                this.target = typeDefinition;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                return new Target.ForDefaultValue.ReadWrite(this.target);
            }

            protected enum Factory implements Factory<Unused> {
                INSTANCE;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<Unused> getAnnotationType() {
                    return Unused.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Unused> loadable, Factory.AdviceType adviceType) {
                    return new ForUnusedValue(inDefinedShape.getType());
                }
            }
        }

        public enum ForStubValue implements OffsetMapping, Factory<StubValue> {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                return new Target.ForDefaultValue.ReadOnly(methodDescription.getReturnType(), assigner.assign(methodDescription.getReturnType(), TypeDescription.Generic.OBJECT, Assigner.Typing.DYNAMIC));
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
            public Class<StubValue> getAnnotationType() {
                return StubValue.class;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
            public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<StubValue> loadable, Factory.AdviceType adviceType) {
                if (inDefinedShape.getType().represents(Object.class)) {
                    return this;
                }
                throw new IllegalStateException("Cannot use StubValue on non-Object parameter type " + inDefinedShape);
            }
        }

        public static class ForEnterValue implements OffsetMapping {
            private final TypeDescription.Generic enterType;
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForEnterValue;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForEnterValue)) {
                    return false;
                }
                ForEnterValue forEnterValue = (ForEnterValue) obj;
                if (!forEnterValue.canEqual(this)) {
                    return false;
                }
                TypeDescription.Generic generic = this.target;
                TypeDescription.Generic generic2 = forEnterValue.target;
                if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                    return false;
                }
                TypeDescription.Generic generic3 = this.enterType;
                TypeDescription.Generic generic4 = forEnterValue.enterType;
                if (generic3 != null ? !generic3.equals(generic4) : generic4 != null) {
                    return false;
                }
                if (this.readOnly != forEnterValue.readOnly) {
                    return false;
                }
                Assigner.Typing typing = this.typing;
                Assigner.Typing typing2 = forEnterValue.typing;
                return typing != null ? typing.equals(typing2) : typing2 == null;
            }

            public int hashCode() {
                TypeDescription.Generic generic = this.target;
                int iHashCode = generic == null ? 43 : generic.hashCode();
                TypeDescription.Generic generic2 = this.enterType;
                int iHashCode2 = ((((iHashCode + 59) * 59) + (generic2 == null ? 43 : generic2.hashCode())) * 59) + (this.readOnly ? 79 : 97);
                Assigner.Typing typing = this.typing;
                return (iHashCode2 * 59) + (typing != null ? typing.hashCode() : 43);
            }

            protected ForEnterValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, Enter enter) {
                this(generic, generic2, enter.readOnly(), enter.typing());
            }

            public ForEnterValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.enterType = generic2;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                StackManipulation stackManipulationAssign = assigner.assign(this.enterType, this.target, this.typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.enterType + " to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(this.target, methodDescription.getStackSize(), stackManipulationAssign);
                }
                StackManipulation stackManipulationAssign2 = assigner.assign(this.target, this.enterType, this.typing);
                if (!stackManipulationAssign2.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.target + " to " + this.enterType);
                }
                return new Target.ForVariable.ReadWrite(this.target, methodDescription.getStackSize(), stackManipulationAssign, stackManipulationAssign2);
            }

            protected static class Factory implements Factory<Enter> {
                private final TypeDefinition enterType;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Factory;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Factory)) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    if (!factory.canEqual(this)) {
                        return false;
                    }
                    TypeDefinition typeDefinition = this.enterType;
                    TypeDefinition typeDefinition2 = factory.enterType;
                    return typeDefinition != null ? typeDefinition.equals(typeDefinition2) : typeDefinition2 == null;
                }

                public int hashCode() {
                    TypeDefinition typeDefinition = this.enterType;
                    return 59 + (typeDefinition == null ? 43 : typeDefinition.hashCode());
                }

                protected Factory(TypeDefinition typeDefinition) {
                    this.enterType = typeDefinition;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<Enter> getAnnotationType() {
                    return Enter.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Enter> loadable, Factory.AdviceType adviceType) {
                    if (adviceType.isDelegation() && !((Enter) loadable.loadSilent()).readOnly()) {
                        throw new IllegalStateException("Cannot use writable " + inDefinedShape + " on read-only parameter");
                    }
                    return new ForEnterValue(inDefinedShape.getType(), this.enterType.asGenericType(), (Enter) loadable.loadSilent());
                }
            }
        }

        public static class ForReturnValue implements OffsetMapping {
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForReturnValue;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForReturnValue)) {
                    return false;
                }
                ForReturnValue forReturnValue = (ForReturnValue) obj;
                if (!forReturnValue.canEqual(this)) {
                    return false;
                }
                TypeDescription.Generic generic = this.target;
                TypeDescription.Generic generic2 = forReturnValue.target;
                if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                    return false;
                }
                if (this.readOnly != forReturnValue.readOnly) {
                    return false;
                }
                Assigner.Typing typing = this.typing;
                Assigner.Typing typing2 = forReturnValue.typing;
                return typing != null ? typing.equals(typing2) : typing2 == null;
            }

            public int hashCode() {
                TypeDescription.Generic generic = this.target;
                int iHashCode = (((generic == null ? 43 : generic.hashCode()) + 59) * 59) + (this.readOnly ? 79 : 97);
                Assigner.Typing typing = this.typing;
                return (iHashCode * 59) + (typing != null ? typing.hashCode() : 43);
            }

            protected ForReturnValue(TypeDescription.Generic generic, Return r3) {
                this(generic, r3.readOnly(), r3.typing());
            }

            public ForReturnValue(TypeDescription.Generic generic, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                int stackSize = methodDescription.getStackSize() + context.getPadding();
                StackManipulation stackManipulationAssign = assigner.assign(methodDescription.getReturnType(), this.target, this.typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + methodDescription.getReturnType() + " to " + this.target);
                }
                if (this.readOnly) {
                    return methodDescription.getReturnType().represents(Void.TYPE) ? new Target.ForDefaultValue.ReadOnly(this.target) : new Target.ForVariable.ReadOnly(methodDescription.getReturnType(), stackSize, stackManipulationAssign);
                }
                StackManipulation stackManipulationAssign2 = assigner.assign(this.target, methodDescription.getReturnType(), this.typing);
                if (stackManipulationAssign2.isValid()) {
                    return methodDescription.getReturnType().represents(Void.TYPE) ? new Target.ForDefaultValue.ReadWrite(this.target) : new Target.ForVariable.ReadWrite(methodDescription.getReturnType(), stackSize, stackManipulationAssign, stackManipulationAssign2);
                }
                throw new IllegalStateException("Cannot assign " + this.target + " to " + methodDescription.getReturnType());
            }

            protected enum Factory implements Factory<Return> {
                INSTANCE;

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<Return> getAnnotationType() {
                    return Return.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Return> loadable, Factory.AdviceType adviceType) {
                    if (adviceType.isDelegation() && !((Return) loadable.loadSilent()).readOnly()) {
                        throw new IllegalStateException("Cannot write return value for " + inDefinedShape + " in read-only context");
                    }
                    return new ForReturnValue(inDefinedShape.getType(), (Return) loadable.loadSilent());
                }
            }
        }

        public static class ForThrowable implements OffsetMapping {
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForThrowable;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForThrowable)) {
                    return false;
                }
                ForThrowable forThrowable = (ForThrowable) obj;
                if (!forThrowable.canEqual(this)) {
                    return false;
                }
                TypeDescription.Generic generic = this.target;
                TypeDescription.Generic generic2 = forThrowable.target;
                if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                    return false;
                }
                if (this.readOnly != forThrowable.readOnly) {
                    return false;
                }
                Assigner.Typing typing = this.typing;
                Assigner.Typing typing2 = forThrowable.typing;
                return typing != null ? typing.equals(typing2) : typing2 == null;
            }

            public int hashCode() {
                TypeDescription.Generic generic = this.target;
                int iHashCode = (((generic == null ? 43 : generic.hashCode()) + 59) * 59) + (this.readOnly ? 79 : 97);
                Assigner.Typing typing = this.typing;
                return (iHashCode * 59) + (typing != null ? typing.hashCode() : 43);
            }

            protected ForThrowable(TypeDescription.Generic generic, Thrown thrown) {
                this(generic, thrown.readOnly(), thrown.typing());
            }

            public ForThrowable(TypeDescription.Generic generic, boolean z, Assigner.Typing typing) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                int stackSize = methodDescription.getStackSize() + context.getPadding() + methodDescription.getReturnType().getStackSize().getSize();
                StackManipulation stackManipulationAssign = assigner.assign(TypeDescription.THROWABLE.asGenericType(), this.target, this.typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot assign Throwable to " + this.target);
                }
                if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(TypeDescription.THROWABLE, stackSize, stackManipulationAssign);
                }
                StackManipulation stackManipulationAssign2 = assigner.assign(this.target, TypeDescription.THROWABLE.asGenericType(), this.typing);
                if (!stackManipulationAssign2.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.target + " to Throwable");
                }
                return new Target.ForVariable.ReadWrite(TypeDescription.THROWABLE, stackSize, stackManipulationAssign, stackManipulationAssign2);
            }

            protected enum Factory implements Factory<Thrown> {
                INSTANCE;

                protected static Factory<?> of(MethodDescription.InDefinedShape inDefinedShape) {
                    return ((TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.ON_THROWABLE).resolve(TypeDescription.class)).represents(NoExceptionHandler.class) ? new Factory.Illegal(Thrown.class) : INSTANCE;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<Thrown> getAnnotationType() {
                    return Thrown.class;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Thrown> loadable, Factory.AdviceType adviceType) {
                    if (adviceType.isDelegation() && !((Thrown) loadable.loadSilent()).readOnly()) {
                        throw new IllegalStateException("Cannot use writable " + inDefinedShape + " on read-only parameter");
                    }
                    return new ForThrowable(inDefinedShape.getType(), (Thrown) loadable.loadSilent());
                }
            }
        }

        public static class ForStackManipulation implements OffsetMapping {
            private final StackManipulation stackManipulation;
            private final TypeDescription.Generic targetType;
            private final TypeDescription.Generic typeDescription;
            private final Assigner.Typing typing;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForStackManipulation)) {
                    return false;
                }
                ForStackManipulation forStackManipulation = (ForStackManipulation) obj;
                if (!forStackManipulation.canEqual(this)) {
                    return false;
                }
                StackManipulation stackManipulation = this.stackManipulation;
                StackManipulation stackManipulation2 = forStackManipulation.stackManipulation;
                if (stackManipulation != null ? !stackManipulation.equals(stackManipulation2) : stackManipulation2 != null) {
                    return false;
                }
                TypeDescription.Generic generic = this.typeDescription;
                TypeDescription.Generic generic2 = forStackManipulation.typeDescription;
                if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                    return false;
                }
                TypeDescription.Generic generic3 = this.targetType;
                TypeDescription.Generic generic4 = forStackManipulation.targetType;
                if (generic3 != null ? !generic3.equals(generic4) : generic4 != null) {
                    return false;
                }
                Assigner.Typing typing = this.typing;
                Assigner.Typing typing2 = forStackManipulation.typing;
                return typing != null ? typing.equals(typing2) : typing2 == null;
            }

            public int hashCode() {
                StackManipulation stackManipulation = this.stackManipulation;
                int iHashCode = stackManipulation == null ? 43 : stackManipulation.hashCode();
                TypeDescription.Generic generic = this.typeDescription;
                int iHashCode2 = ((iHashCode + 59) * 59) + (generic == null ? 43 : generic.hashCode());
                TypeDescription.Generic generic2 = this.targetType;
                int iHashCode3 = (iHashCode2 * 59) + (generic2 == null ? 43 : generic2.hashCode());
                Assigner.Typing typing = this.typing;
                return (iHashCode3 * 59) + (typing != null ? typing.hashCode() : 43);
            }

            public ForStackManipulation(StackManipulation stackManipulation, TypeDescription.Generic generic, TypeDescription.Generic generic2, Assigner.Typing typing) {
                this.stackManipulation = stackManipulation;
                this.typeDescription = generic;
                this.targetType = generic2;
                this.typing = typing;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                StackManipulation stackManipulationAssign = assigner.assign(this.typeDescription, this.targetType, this.typing);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.typeDescription + " to " + this.targetType);
                }
                return new Target.ForStackManipulation(new StackManipulation.Compound(this.stackManipulation, stackManipulationAssign));
            }

            public static class Factory<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final StackManipulation stackManipulation;
                private final TypeDescription.Generic typeDescription;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Factory;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Factory)) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    if (!factory.canEqual(this)) {
                        return false;
                    }
                    Class<T> annotationType = getAnnotationType();
                    Class<T> annotationType2 = factory.getAnnotationType();
                    if (annotationType != null ? !annotationType.equals(annotationType2) : annotationType2 != null) {
                        return false;
                    }
                    StackManipulation stackManipulation = this.stackManipulation;
                    StackManipulation stackManipulation2 = factory.stackManipulation;
                    if (stackManipulation != null ? !stackManipulation.equals(stackManipulation2) : stackManipulation2 != null) {
                        return false;
                    }
                    TypeDescription.Generic generic = this.typeDescription;
                    TypeDescription.Generic generic2 = factory.typeDescription;
                    return generic != null ? generic.equals(generic2) : generic2 == null;
                }

                public int hashCode() {
                    Class<T> annotationType = getAnnotationType();
                    int iHashCode = annotationType == null ? 43 : annotationType.hashCode();
                    StackManipulation stackManipulation = this.stackManipulation;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (stackManipulation == null ? 43 : stackManipulation.hashCode());
                    TypeDescription.Generic generic = this.typeDescription;
                    return (iHashCode2 * 59) + (generic != null ? generic.hashCode() : 43);
                }

                public Factory(Class<T> cls, TypeDescription typeDescription) {
                    this(cls, ClassConstant.of(typeDescription), TypeDescription.CLASS.asGenericType());
                }

                public Factory(Class<T> cls, EnumerationDescription enumerationDescription) {
                    this(cls, FieldAccess.forEnumeration(enumerationDescription), enumerationDescription.getEnumerationType().asGenericType());
                }

                public Factory(Class<T> cls, StackManipulation stackManipulation, TypeDescription.Generic generic) {
                    this.annotationType = cls;
                    this.stackManipulation = stackManipulation;
                    this.typeDescription = generic;
                }

                public static <S extends Annotation> Factory<S> of(Class<S> cls, Object obj) {
                    TypeDescription forLoadedType;
                    StackManipulation stackManipulationForValue;
                    if (obj == null) {
                        return new OfDefaultValue(cls);
                    }
                    if (obj instanceof Boolean) {
                        stackManipulationForValue = IntegerConstant.forValue(((Boolean) obj).booleanValue());
                        forLoadedType = new TypeDescription.ForLoadedType(Boolean.TYPE);
                    } else if (obj instanceof Byte) {
                        stackManipulationForValue = IntegerConstant.forValue(((Byte) obj).byteValue());
                        forLoadedType = new TypeDescription.ForLoadedType(Byte.TYPE);
                    } else if (obj instanceof Short) {
                        stackManipulationForValue = IntegerConstant.forValue(((Short) obj).shortValue());
                        forLoadedType = new TypeDescription.ForLoadedType(Short.TYPE);
                    } else if (obj instanceof Character) {
                        stackManipulationForValue = IntegerConstant.forValue(((Character) obj).charValue());
                        forLoadedType = new TypeDescription.ForLoadedType(Character.TYPE);
                    } else if (obj instanceof Integer) {
                        stackManipulationForValue = IntegerConstant.forValue(((Integer) obj).intValue());
                        forLoadedType = new TypeDescription.ForLoadedType(Integer.TYPE);
                    } else if (obj instanceof Long) {
                        stackManipulationForValue = LongConstant.forValue(((Long) obj).longValue());
                        forLoadedType = new TypeDescription.ForLoadedType(Long.TYPE);
                    } else if (obj instanceof Float) {
                        stackManipulationForValue = FloatConstant.forValue(((Float) obj).floatValue());
                        forLoadedType = new TypeDescription.ForLoadedType(Float.TYPE);
                    } else if (obj instanceof Double) {
                        stackManipulationForValue = DoubleConstant.forValue(((Double) obj).doubleValue());
                        forLoadedType = new TypeDescription.ForLoadedType(Double.TYPE);
                    } else if (obj instanceof String) {
                        TextConstant textConstant = new TextConstant((String) obj);
                        forLoadedType = TypeDescription.STRING;
                        stackManipulationForValue = textConstant;
                    } else {
                        throw new IllegalStateException("Not a constant value: " + obj);
                    }
                    return new Factory(cls, stackManipulationForValue, forLoadedType.asGenericType());
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    return new ForStackManipulation(this.stackManipulation, this.typeDescription, inDefinedShape.getType(), Assigner.Typing.STATIC);
                }
            }

            public static class OfDefaultValue<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;

                protected boolean canEqual(Object obj) {
                    return obj instanceof OfDefaultValue;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof OfDefaultValue)) {
                        return false;
                    }
                    OfDefaultValue ofDefaultValue = (OfDefaultValue) obj;
                    if (!ofDefaultValue.canEqual(this)) {
                        return false;
                    }
                    Class<T> annotationType = getAnnotationType();
                    Class<T> annotationType2 = ofDefaultValue.getAnnotationType();
                    return annotationType != null ? annotationType.equals(annotationType2) : annotationType2 == null;
                }

                public int hashCode() {
                    Class<T> annotationType = getAnnotationType();
                    return 59 + (annotationType == null ? 43 : annotationType.hashCode());
                }

                public OfDefaultValue(Class<T> cls) {
                    this.annotationType = cls;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    return new ForStackManipulation(DefaultValue.of(inDefinedShape.getType()), inDefinedShape.getType(), inDefinedShape.getType(), Assigner.Typing.STATIC);
                }
            }

            public static class OfAnnotationProperty<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final MethodDescription.InDefinedShape property;

                protected boolean canEqual(Object obj) {
                    return obj instanceof OfAnnotationProperty;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof OfAnnotationProperty)) {
                        return false;
                    }
                    OfAnnotationProperty ofAnnotationProperty = (OfAnnotationProperty) obj;
                    if (!ofAnnotationProperty.canEqual(this)) {
                        return false;
                    }
                    Class<T> annotationType = getAnnotationType();
                    Class<T> annotationType2 = ofAnnotationProperty.getAnnotationType();
                    if (annotationType != null ? !annotationType.equals(annotationType2) : annotationType2 != null) {
                        return false;
                    }
                    MethodDescription.InDefinedShape inDefinedShape = this.property;
                    MethodDescription.InDefinedShape inDefinedShape2 = ofAnnotationProperty.property;
                    return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
                }

                public int hashCode() {
                    Class<T> annotationType = getAnnotationType();
                    int iHashCode = annotationType == null ? 43 : annotationType.hashCode();
                    MethodDescription.InDefinedShape inDefinedShape = this.property;
                    return ((iHashCode + 59) * 59) + (inDefinedShape != null ? inDefinedShape.hashCode() : 43);
                }

                protected OfAnnotationProperty(Class<T> cls, MethodDescription.InDefinedShape inDefinedShape) {
                    this.annotationType = cls;
                    this.property = inDefinedShape;
                }

                public static <S extends Annotation> Factory<S> of(Class<S> cls, String str) {
                    if (!cls.isAnnotation()) {
                        throw new IllegalArgumentException("Not an annotation type: " + cls);
                    }
                    try {
                        return new OfAnnotationProperty(cls, new MethodDescription.ForLoadedMethod(cls.getMethod(str, new Class[0])));
                    } catch (NoSuchMethodException e) {
                        throw new IllegalArgumentException("Cannot find a property " + str + " on " + cls, e);
                    }
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    Factory factoryOf;
                    Object objResolve = loadable.getValue(this.property).resolve();
                    if (objResolve instanceof TypeDescription) {
                        factoryOf = new Factory(this.annotationType, (TypeDescription) objResolve);
                    } else if (objResolve instanceof EnumerationDescription) {
                        factoryOf = new Factory(this.annotationType, (EnumerationDescription) objResolve);
                    } else {
                        if (objResolve instanceof AnnotationDescription) {
                            throw new IllegalStateException("Cannot bind annotation as fixed value for " + this.property);
                        }
                        factoryOf = Factory.of(this.annotationType, objResolve);
                    }
                    return factoryOf.make(inDefinedShape, loadable, adviceType);
                }
            }
        }

        public static class ForSerializedValue implements OffsetMapping {
            private final StackManipulation deserialization;
            private final TypeDescription.Generic target;
            private final TypeDescription typeDescription;

            protected boolean canEqual(Object obj) {
                return obj instanceof ForSerializedValue;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ForSerializedValue)) {
                    return false;
                }
                ForSerializedValue forSerializedValue = (ForSerializedValue) obj;
                if (!forSerializedValue.canEqual(this)) {
                    return false;
                }
                TypeDescription.Generic generic = this.target;
                TypeDescription.Generic generic2 = forSerializedValue.target;
                if (generic != null ? !generic.equals(generic2) : generic2 != null) {
                    return false;
                }
                TypeDescription typeDescription = this.typeDescription;
                TypeDescription typeDescription2 = forSerializedValue.typeDescription;
                if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                    return false;
                }
                StackManipulation stackManipulation = this.deserialization;
                StackManipulation stackManipulation2 = forSerializedValue.deserialization;
                return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
            }

            public int hashCode() {
                TypeDescription.Generic generic = this.target;
                int iHashCode = generic == null ? 43 : generic.hashCode();
                TypeDescription typeDescription = this.typeDescription;
                int iHashCode2 = ((iHashCode + 59) * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                StackManipulation stackManipulation = this.deserialization;
                return (iHashCode2 * 59) + (stackManipulation != null ? stackManipulation.hashCode() : 43);
            }

            public ForSerializedValue(TypeDescription.Generic generic, TypeDescription typeDescription, StackManipulation stackManipulation) {
                this.target = generic;
                this.typeDescription = typeDescription;
                this.deserialization = stackManipulation;
            }

            @Override // net.bytebuddy.asm.Advice.OffsetMapping
            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Context context) {
                StackManipulation stackManipulationAssign = assigner.assign(this.typeDescription.asGenericType(), this.target, Assigner.Typing.DYNAMIC);
                if (!stackManipulationAssign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.typeDescription + " to " + this.target);
                }
                return new Target.ForStackManipulation(new StackManipulation.Compound(this.deserialization, stackManipulationAssign));
            }

            public static class Factory<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final StackManipulation deserialization;
                private final TypeDescription typeDescription;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Factory;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Factory)) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    if (!factory.canEqual(this)) {
                        return false;
                    }
                    Class<T> annotationType = getAnnotationType();
                    Class<T> annotationType2 = factory.getAnnotationType();
                    if (annotationType != null ? !annotationType.equals(annotationType2) : annotationType2 != null) {
                        return false;
                    }
                    TypeDescription typeDescription = this.typeDescription;
                    TypeDescription typeDescription2 = factory.typeDescription;
                    if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                        return false;
                    }
                    StackManipulation stackManipulation = this.deserialization;
                    StackManipulation stackManipulation2 = factory.deserialization;
                    return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
                }

                public int hashCode() {
                    Class<T> annotationType = getAnnotationType();
                    int iHashCode = annotationType == null ? 43 : annotationType.hashCode();
                    TypeDescription typeDescription = this.typeDescription;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (typeDescription == null ? 43 : typeDescription.hashCode());
                    StackManipulation stackManipulation = this.deserialization;
                    return (iHashCode2 * 59) + (stackManipulation != null ? stackManipulation.hashCode() : 43);
                }

                protected Factory(Class<T> cls, TypeDescription typeDescription, StackManipulation stackManipulation) {
                    this.annotationType = cls;
                    this.typeDescription = typeDescription;
                    this.deserialization = stackManipulation;
                }

                public static <S extends Annotation> Factory<S> of(Class<S> cls, Serializable serializable, Class<?> cls2) {
                    if (!cls2.isInstance(serializable)) {
                        throw new IllegalArgumentException(serializable + " is no instance of " + cls2);
                    }
                    return new Factory(cls, new TypeDescription.ForLoadedType(cls2), SerializedConstant.of(serializable));
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                @Override // net.bytebuddy.asm.Advice.OffsetMapping.Factory
                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    return new ForSerializedValue(inDefinedShape.getType(), this.typeDescription, this.deserialization);
                }
            }
        }
    }

    /* JADX INFO: renamed from: net.bytebuddy.asm.Advice$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize;

        static {
            int[] iArr = new int[StackSize.values().length];
            $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize = iArr;
            try {
                iArr[StackSize.ZERO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[StackSize.SINGLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[StackSize.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected interface MethodSizeHandler {
        public static final int UNDEFINED_SIZE = 32767;

        public interface ForAdvice extends MethodSizeHandler {
            void recordMaxima(int i, int i2);

            void recordPadding(int i);

            void requireStackSize(int i);
        }

        public interface ForInstrumentedMethod extends MethodSizeHandler {
            ForAdvice bindEntry(MethodDescription.InDefinedShape inDefinedShape);

            ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape, boolean z);

            int compoundLocalVariableLength(int i);

            int compoundStackSize(int i);
        }

        public enum NoOp implements ForInstrumentedMethod, ForAdvice {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public ForAdvice bindEntry(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape, boolean z) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public int compoundLocalVariableLength(int i) {
                return MethodSizeHandler.UNDEFINED_SIZE;
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public int compoundStackSize(int i) {
                return MethodSizeHandler.UNDEFINED_SIZE;
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
            public void recordMaxima(int i, int i2) {
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
            public void recordPadding(int i) {
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler
            public void requireLocalVariableLength(int i) {
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
            public void requireStackSize(int i) {
            }
        }

        void requireLocalVariableLength(int i);

        public static class Default implements ForInstrumentedMethod {
            private final MethodDescription instrumentedMethod;
            private int localVariableLength;
            private final TypeList requiredTypes;
            private int stackSize;
            private final TypeList yieldedTypes;

            protected Default(MethodDescription methodDescription, TypeList typeList, TypeList typeList2) {
                this.instrumentedMethod = methodDescription;
                this.requiredTypes = typeList;
                this.yieldedTypes = typeList2;
            }

            protected static ForInstrumentedMethod of(MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, int i) {
                return (i & 3) != 0 ? NoOp.INSTANCE : new Default(methodDescription, new TypeList.Explicit(list), new TypeList.Explicit(list2));
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public ForAdvice bindEntry(MethodDescription.InDefinedShape inDefinedShape) {
                this.stackSize = Math.max(this.stackSize, inDefinedShape.getReturnType().getStackSize().getSize());
                return new ForAdvice(inDefinedShape, new TypeList.Empty(), new TypeList.Explicit(this.requiredTypes));
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape, boolean z) {
                this.stackSize = Math.max(this.stackSize, inDefinedShape.getReturnType().getStackSize().maximum(z ? StackSize.ZERO : StackSize.SINGLE).getSize());
                return new ForAdvice(inDefinedShape, new TypeList.Explicit((List<? extends TypeDescription>) CompoundList.of((List) this.requiredTypes, (List) this.yieldedTypes)), new TypeList.Empty());
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public int compoundStackSize(int i) {
                return Math.max(this.stackSize, i);
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForInstrumentedMethod
            public int compoundLocalVariableLength(int i) {
                return Math.max(this.localVariableLength, i + this.requiredTypes.getStackSize() + this.yieldedTypes.getStackSize());
            }

            @Override // net.bytebuddy.asm.Advice.MethodSizeHandler
            public void requireLocalVariableLength(int i) {
                this.localVariableLength = Math.max(this.localVariableLength, i);
            }

            protected class ForAdvice implements ForAdvice {
                private final MethodDescription.InDefinedShape adviceMethod;
                private int padding;
                private final TypeList requiredTypes;
                private final TypeList yieldedTypes;

                protected ForAdvice(MethodDescription.InDefinedShape inDefinedShape, TypeList typeList, TypeList typeList2) {
                    this.adviceMethod = inDefinedShape;
                    this.requiredTypes = typeList;
                    this.yieldedTypes = typeList2;
                    Default.this.stackSize = Math.max(Default.this.stackSize, inDefinedShape.getReturnType().getStackSize().getSize());
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler
                public void requireLocalVariableLength(int i) {
                    Default.this.requireLocalVariableLength(i);
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
                public void requireStackSize(int i) {
                    Default r0 = Default.this;
                    r0.stackSize = Math.max(r0.stackSize, i);
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
                public void recordMaxima(int i, int i2) {
                    Default r0 = Default.this;
                    r0.stackSize = Math.max(r0.stackSize, i) + this.padding;
                    Default r3 = Default.this;
                    r3.localVariableLength = Math.max(r3.localVariableLength, (i2 - this.adviceMethod.getStackSize()) + Default.this.instrumentedMethod.getStackSize() + this.requiredTypes.getStackSize() + this.yieldedTypes.getStackSize());
                }

                @Override // net.bytebuddy.asm.Advice.MethodSizeHandler.ForAdvice
                public void recordPadding(int i) {
                    this.padding = Math.max(this.padding, i);
                }
            }
        }
    }

    protected interface StackMapFrameHandler {

        public interface ForAdvice extends StackMapFrameHandler {
        }

        public interface ForInstrumentedMethod extends StackMapFrameHandler {
            ForAdvice bindEntry(MethodDescription.InDefinedShape inDefinedShape);

            ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape);

            int getReaderHint();
        }

        public enum NoOp implements ForInstrumentedMethod, ForAdvice {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public ForAdvice bindEntry(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public int getReaderHint() {
                return 4;
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public void injectCompletionFrame(MethodVisitor methodVisitor, boolean z) {
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public void injectExceptionFrame(MethodVisitor methodVisitor) {
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public void injectReturnFrame(MethodVisitor methodVisitor) {
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public void translateFrame(MethodVisitor methodVisitor, int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
            }
        }

        void injectCompletionFrame(MethodVisitor methodVisitor, boolean z);

        void injectExceptionFrame(MethodVisitor methodVisitor);

        void injectReturnFrame(MethodVisitor methodVisitor);

        void translateFrame(MethodVisitor methodVisitor, int i, int i2, Object[] objArr, int i3, Object[] objArr2);

        public static class Default implements ForInstrumentedMethod {
            private static final Object[] EMPTY = new Object[0];
            private int currentFrameDivergence;
            private final boolean expandFrames;
            protected final MethodDescription instrumentedMethod;
            private final TypeDescription instrumentedType;
            protected final TypeList requiredTypes;
            protected final TypeList yieldedTypes;

            protected Default(TypeDescription typeDescription, MethodDescription methodDescription, TypeList typeList, TypeList typeList2, boolean z) {
                this.instrumentedType = typeDescription;
                this.instrumentedMethod = methodDescription;
                this.requiredTypes = typeList;
                this.yieldedTypes = typeList2;
                this.expandFrames = z;
            }

            protected static ForInstrumentedMethod of(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, ClassFileVersion classFileVersion, int i, int i2) {
                if ((i & 2) != 0 || classFileVersion.isLessThan(ClassFileVersion.JAVA_V6)) {
                    return NoOp.INSTANCE;
                }
                return new Default(typeDescription, methodDescription, new TypeList.Explicit(list), new TypeList.Explicit(list2), (i2 & 8) != 0);
            }

            protected static Object toFrame(TypeDescription typeDescription) {
                if (typeDescription.represents(Boolean.TYPE) || typeDescription.represents(Byte.TYPE) || typeDescription.represents(Short.TYPE) || typeDescription.represents(Character.TYPE) || typeDescription.represents(Integer.TYPE)) {
                    return Opcodes.INTEGER;
                }
                if (typeDescription.represents(Long.TYPE)) {
                    return Opcodes.LONG;
                }
                if (typeDescription.represents(Float.TYPE)) {
                    return Opcodes.FLOAT;
                }
                if (typeDescription.represents(Double.TYPE)) {
                    return Opcodes.DOUBLE;
                }
                return typeDescription.getInternalName();
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public ForAdvice bindEntry(MethodDescription.InDefinedShape inDefinedShape) {
                return new ForAdvice(inDefinedShape, new TypeList.Empty(), this.requiredTypes, TranslationMode.ENTRY);
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                return new ForAdvice(inDefinedShape, new TypeList.Explicit((List<? extends TypeDescription>) CompoundList.of((List) this.requiredTypes, (List) this.yieldedTypes)), new TypeList.Empty(), TranslationMode.EXIT);
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.ForInstrumentedMethod
            public int getReaderHint() {
                return this.expandFrames ? 8 : 0;
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public void translateFrame(MethodVisitor methodVisitor, int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                translateFrame(methodVisitor, TranslationMode.COPY, this.instrumentedMethod, this.requiredTypes, i, i2, objArr, i3, objArr2);
            }

            protected void translateFrame(MethodVisitor methodVisitor, TranslationMode translationMode, MethodDescription methodDescription, TypeList typeList, int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                int i4;
                int i5;
                Object[] objArr3;
                if (i == -1 || i == 0) {
                    if (methodDescription.getParameters().size() + (!methodDescription.isStatic() ? 1 : 0) > i2) {
                        throw new IllegalStateException("Inconsistent frame length for " + methodDescription + ": " + i2);
                    }
                    if (methodDescription.isStatic()) {
                        i4 = 0;
                    } else {
                        if (!translationMode.isPossibleThisFrameValue(this.instrumentedType, this.instrumentedMethod, objArr[0])) {
                            throw new IllegalStateException(methodDescription + " is inconsistent for 'this' reference: " + objArr[0]);
                        }
                        i4 = 1;
                    }
                    for (int i6 = 0; i6 < methodDescription.getParameters().size(); i6++) {
                        int i7 = i6 + i4;
                        if (!toFrame(((ParameterDescription) methodDescription.getParameters().get(i6)).getType().asErasure()).equals(objArr[i7])) {
                            throw new IllegalStateException(methodDescription + " is inconsistent at " + i6 + ": " + objArr[i7]);
                        }
                    }
                    int size = ((i2 - methodDescription.getParameters().size()) - (!methodDescription.isStatic() ? 1 : 0)) + this.instrumentedMethod.getParameters().size() + (!this.instrumentedMethod.isStatic() ? 1 : 0) + typeList.size();
                    Object[] objArr4 = new Object[size];
                    int iCopy = translationMode.copy(this.instrumentedType, this.instrumentedMethod, methodDescription, objArr, objArr4);
                    Iterator it = typeList.iterator();
                    while (it.hasNext()) {
                        objArr4[iCopy] = toFrame((TypeDescription) it.next());
                        iCopy++;
                    }
                    int i8 = size - iCopy;
                    System.arraycopy(objArr, methodDescription.getParameters().size() + (!methodDescription.isStatic() ? 1 : 0), objArr4, iCopy, i8);
                    this.currentFrameDivergence = i8;
                    i5 = size;
                    objArr3 = objArr4;
                } else {
                    if (i == 1) {
                        this.currentFrameDivergence += i2;
                    } else if (i == 2) {
                        this.currentFrameDivergence -= i2;
                    } else if (i != 3 && i != 4) {
                        throw new IllegalArgumentException("Unexpected frame type: " + i);
                    }
                    i5 = i2;
                    objArr3 = objArr;
                }
                methodVisitor.visitFrame(i, i5, objArr3, i3, objArr2);
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public void injectReturnFrame(MethodVisitor methodVisitor) {
                List<? extends TypeDescription> listSingletonList;
                if (!this.expandFrames && this.currentFrameDivergence == 0 && !this.instrumentedMethod.isConstructor()) {
                    if (this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                        Object[] objArr = EMPTY;
                        methodVisitor.visitFrame(3, objArr.length, objArr, objArr.length, objArr);
                        return;
                    } else {
                        Object[] objArr2 = EMPTY;
                        methodVisitor.visitFrame(4, objArr2.length, objArr2, 1, new Object[]{toFrame(this.instrumentedMethod.getReturnType().asErasure())});
                        return;
                    }
                }
                TypeList typeList = this.requiredTypes;
                if (this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                    listSingletonList = Collections.emptyList();
                } else {
                    listSingletonList = Collections.singletonList(this.instrumentedMethod.getReturnType().asErasure());
                }
                injectFullFrame(methodVisitor, typeList, listSingletonList);
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public void injectExceptionFrame(MethodVisitor methodVisitor) {
                if (!this.expandFrames && this.currentFrameDivergence == 0) {
                    Object[] objArr = EMPTY;
                    methodVisitor.visitFrame(4, objArr.length, objArr, 1, new Object[]{Type.getInternalName(Throwable.class)});
                } else {
                    injectFullFrame(methodVisitor, this.requiredTypes, Collections.singletonList(TypeDescription.THROWABLE));
                }
            }

            @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
            public void injectCompletionFrame(MethodVisitor methodVisitor, boolean z) {
                if (this.expandFrames || this.currentFrameDivergence != 0 || (!z && this.instrumentedMethod.isConstructor())) {
                    injectFullFrame(methodVisitor, CompoundList.of((List) this.requiredTypes, (List) this.yieldedTypes), Collections.emptyList());
                    return;
                }
                if (z) {
                    Object[] objArr = EMPTY;
                    methodVisitor.visitFrame(3, objArr.length, objArr, objArr.length, objArr);
                    return;
                }
                int size = this.yieldedTypes.size();
                Object[] objArr2 = new Object[size];
                Iterator it = this.yieldedTypes.iterator();
                int i = 0;
                while (it.hasNext()) {
                    objArr2[i] = toFrame((TypeDescription) it.next());
                    i++;
                }
                Object[] objArr3 = EMPTY;
                methodVisitor.visitFrame(1, size, objArr2, objArr3.length, objArr3);
            }

            protected void injectFullFrame(MethodVisitor methodVisitor, List<? extends TypeDescription> list, List<? extends TypeDescription> list2) {
                int i = 1;
                int size = this.instrumentedMethod.getParameters().size() + (!this.instrumentedMethod.isStatic() ? 1 : 0) + list.size();
                Object[] objArr = new Object[size];
                if (this.instrumentedMethod.isStatic()) {
                    i = 0;
                } else {
                    objArr[0] = toFrame(this.instrumentedType);
                }
                Iterator it = this.instrumentedMethod.getParameters().asTypeList().asErasures().iterator();
                while (it.hasNext()) {
                    objArr[i] = toFrame((TypeDescription) it.next());
                    i++;
                }
                Iterator<? extends TypeDescription> it2 = list.iterator();
                while (it2.hasNext()) {
                    objArr[i] = toFrame(it2.next());
                    i++;
                }
                int size2 = list2.size();
                Object[] objArr2 = new Object[size2];
                Iterator<? extends TypeDescription> it3 = list2.iterator();
                int i2 = 0;
                while (it3.hasNext()) {
                    objArr2[i2] = toFrame(it3.next());
                    i2++;
                }
                methodVisitor.visitFrame(this.expandFrames ? -1 : 0, size, objArr, size2, objArr2);
                this.currentFrameDivergence = 0;
            }

            protected enum TranslationMode {
                COPY { // from class: net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode.1
                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2) {
                        int size = methodDescription.getParameters().size() + (!methodDescription.isStatic() ? 1 : 0);
                        System.arraycopy(objArr, 0, objArr2, 0, size);
                        return size;
                    }

                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj) {
                        return (methodDescription.isConstructor() && Opcodes.UNINITIALIZED_THIS.equals(obj)) || Default.toFrame(typeDescription).equals(obj);
                    }
                },
                ENTRY { // from class: net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode.2
                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2) {
                        int i = 0;
                        if (!methodDescription.isStatic()) {
                            objArr2[0] = methodDescription.isConstructor() ? Opcodes.UNINITIALIZED_THIS : Default.toFrame(typeDescription);
                            i = 1;
                        }
                        Iterator it = methodDescription.getParameters().asTypeList().asErasures().iterator();
                        while (it.hasNext()) {
                            objArr2[i] = Default.toFrame((TypeDescription) it.next());
                            i++;
                        }
                        return i;
                    }

                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj) {
                        if (methodDescription.isConstructor()) {
                            return Opcodes.UNINITIALIZED_THIS.equals(obj);
                        }
                        return Default.toFrame(typeDescription).equals(obj);
                    }
                },
                EXIT { // from class: net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode.3
                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2) {
                        int i = 0;
                        if (!methodDescription.isStatic()) {
                            objArr2[0] = Default.toFrame(typeDescription);
                            i = 1;
                        }
                        Iterator it = methodDescription.getParameters().asTypeList().asErasures().iterator();
                        while (it.hasNext()) {
                            objArr2[i] = Default.toFrame((TypeDescription) it.next());
                            i++;
                        }
                        return i;
                    }

                    @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.TranslationMode
                    protected boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj) {
                        return Default.toFrame(typeDescription).equals(obj);
                    }
                };

                protected abstract int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2);

                protected abstract boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj);

                /* synthetic */ TranslationMode(AnonymousClass1 anonymousClass1) {
                    this();
                }
            }

            protected class ForAdvice implements ForAdvice {
                protected final MethodDescription.InDefinedShape adviceMethod;
                protected final TypeList requiredTypes;
                protected final TranslationMode translationMode;
                private final TypeList yieldedTypes;

                protected ForAdvice(MethodDescription.InDefinedShape inDefinedShape, TypeList typeList, TypeList typeList2, TranslationMode translationMode) {
                    this.adviceMethod = inDefinedShape;
                    this.requiredTypes = typeList;
                    this.yieldedTypes = typeList2;
                    this.translationMode = translationMode;
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void translateFrame(MethodVisitor methodVisitor, int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                    Default.this.translateFrame(methodVisitor, this.translationMode, this.adviceMethod, this.requiredTypes, i, i2, objArr, i3, objArr2);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectReturnFrame(MethodVisitor methodVisitor) {
                    List<? extends TypeDescription> listEmptyList;
                    if (!Default.this.expandFrames && Default.this.currentFrameDivergence == 0) {
                        if (this.yieldedTypes.isEmpty() || this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                            methodVisitor.visitFrame(3, Default.EMPTY.length, Default.EMPTY, Default.EMPTY.length, Default.EMPTY);
                            return;
                        } else {
                            methodVisitor.visitFrame(4, Default.EMPTY.length, Default.EMPTY, 1, new Object[]{Default.toFrame(this.adviceMethod.getReturnType().asErasure())});
                            return;
                        }
                    }
                    Default r0 = Default.this;
                    TypeList typeList = this.requiredTypes;
                    if (this.yieldedTypes.isEmpty() || this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                        listEmptyList = Collections.emptyList();
                    } else {
                        listEmptyList = Collections.singletonList(this.adviceMethod.getReturnType().asErasure());
                    }
                    r0.injectFullFrame(methodVisitor, typeList, listEmptyList);
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectExceptionFrame(MethodVisitor methodVisitor) {
                    if (!Default.this.expandFrames && Default.this.currentFrameDivergence == 0) {
                        methodVisitor.visitFrame(4, Default.EMPTY.length, Default.EMPTY, 1, new Object[]{Type.getInternalName(Throwable.class)});
                    } else {
                        Default.this.injectFullFrame(methodVisitor, this.requiredTypes, Collections.singletonList(TypeDescription.THROWABLE));
                    }
                }

                @Override // net.bytebuddy.asm.Advice.StackMapFrameHandler
                public void injectCompletionFrame(MethodVisitor methodVisitor, boolean z) {
                    if (!Default.this.expandFrames && Default.this.currentFrameDivergence == 0 && this.yieldedTypes.size() < 4) {
                        if (z || this.yieldedTypes.isEmpty()) {
                            methodVisitor.visitFrame(3, Default.EMPTY.length, Default.EMPTY, Default.EMPTY.length, Default.EMPTY);
                            return;
                        }
                        int size = this.yieldedTypes.size();
                        Object[] objArr = new Object[size];
                        Iterator it = this.yieldedTypes.iterator();
                        int i = 0;
                        while (it.hasNext()) {
                            objArr[i] = Default.toFrame((TypeDescription) it.next());
                            i++;
                        }
                        methodVisitor.visitFrame(1, size, objArr, Default.EMPTY.length, Default.EMPTY);
                        return;
                    }
                    Default.this.injectFullFrame(methodVisitor, CompoundList.of((List) this.requiredTypes, (List) this.yieldedTypes), Collections.emptyList());
                }
            }
        }
    }

    protected interface Dispatcher {
        public static final AnnotationVisitor IGNORE_ANNOTATION = null;
        public static final MethodVisitor IGNORE_METHOD = null;

        public interface Bound {

            public interface ForMethodEnter extends Bound {
                void apply(SkipHandler skipHandler);
            }

            public interface ForMethodExit extends Bound {
                void apply();
            }

            public interface SkipHandler {
                void apply(MethodVisitor methodVisitor);
            }

            void prepare();
        }

        public interface Unresolved extends Dispatcher {
            Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader);

            Resolved.ForMethodExit asMethodExitTo(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Resolved.ForMethodEnter forMethodEnter);

            boolean isBinary();
        }

        boolean isAlive();

        public interface SuppressionHandler {

            public interface Bound {
                void onEnd(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, ReturnValueProducer returnValueProducer);

                void onEndSkipped(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, ReturnValueProducer returnValueProducer);

                void onPrepare(MethodVisitor methodVisitor);

                void onStart(MethodVisitor methodVisitor);
            }

            public enum NoOp implements SuppressionHandler, Bound {
                INSTANCE;

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler
                public Bound bind(StackManipulation stackManipulation) {
                    return this;
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                public void onEnd(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, ReturnValueProducer returnValueProducer) {
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                public void onEndSkipped(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, ReturnValueProducer returnValueProducer) {
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                public void onPrepare(MethodVisitor methodVisitor) {
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                public void onStart(MethodVisitor methodVisitor) {
                }
            }

            public interface ReturnValueProducer {
                void onDefaultValue(MethodVisitor methodVisitor);
            }

            Bound bind(StackManipulation stackManipulation);

            public static class Suppressing implements SuppressionHandler {
                private final TypeDescription suppressedType;

                protected boolean canEqual(Object obj) {
                    return obj instanceof Suppressing;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof Suppressing)) {
                        return false;
                    }
                    Suppressing suppressing = (Suppressing) obj;
                    if (!suppressing.canEqual(this)) {
                        return false;
                    }
                    TypeDescription typeDescription = this.suppressedType;
                    TypeDescription typeDescription2 = suppressing.suppressedType;
                    return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.suppressedType;
                    return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                }

                protected Suppressing(TypeDescription typeDescription) {
                    this.suppressedType = typeDescription;
                }

                protected static SuppressionHandler of(TypeDescription typeDescription) {
                    return typeDescription.represents(NoExceptionHandler.class) ? NoOp.INSTANCE : new Suppressing(typeDescription);
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler
                public Bound bind(StackManipulation stackManipulation) {
                    return new Bound(this.suppressedType, stackManipulation);
                }

                protected static class Bound implements Bound {
                    private final StackManipulation exceptionHandler;
                    private final TypeDescription suppressedType;
                    private final Label startOfMethod = new Label();
                    private final Label endOfMethod = new Label();

                    protected Bound(TypeDescription typeDescription, StackManipulation stackManipulation) {
                        this.suppressedType = typeDescription;
                        this.exceptionHandler = stackManipulation;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                    public void onPrepare(MethodVisitor methodVisitor) {
                        Label label = this.startOfMethod;
                        Label label2 = this.endOfMethod;
                        methodVisitor.visitTryCatchBlock(label, label2, label2, this.suppressedType.getInternalName());
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                    public void onStart(MethodVisitor methodVisitor) {
                        methodVisitor.visitLabel(this.startOfMethod);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                    public void onEnd(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, ReturnValueProducer returnValueProducer) {
                        methodVisitor.visitLabel(this.endOfMethod);
                        forAdvice2.injectExceptionFrame(methodVisitor);
                        forAdvice.requireStackSize(this.exceptionHandler.apply(methodVisitor, context).getMaximalSize() + 1);
                        returnValueProducer.onDefaultValue(methodVisitor);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.Bound
                    public void onEndSkipped(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, ReturnValueProducer returnValueProducer) {
                        Label label = new Label();
                        methodVisitor.visitJumpInsn(167, label);
                        onEnd(methodVisitor, context, forAdvice, forAdvice2, returnValueProducer);
                        methodVisitor.visitLabel(label);
                    }
                }
            }
        }

        public interface Resolved extends Dispatcher {

            public interface ForMethodExit extends Resolved {
                @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                Bound.ForMethodExit bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation);

                TypeDescription getThrowable();
            }

            Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation);

            public interface ForMethodEnter extends Resolved {
                @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                Bound.ForMethodEnter bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation);

                TypeDefinition getEnterType();

                boolean isPrependLineNumber();

                public interface SkipDispatcher {

                    public enum Disabled implements SkipDispatcher {
                        INSTANCE;

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher
                        public void apply(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, MethodDescription methodDescription, Bound.SkipHandler skipHandler) {
                        }
                    }

                    void apply(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, MethodDescription methodDescription, Bound.SkipHandler skipHandler);

                    /* JADX WARN: Enum visitor error
                    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'FOR_LONG' uses external variables
                    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
                    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
                    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
                    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
                    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
                    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
                     */
                    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
                    public static abstract class ForValue implements SkipDispatcher {
                        private static final /* synthetic */ ForValue[] $VALUES;
                        public static final ForValue FOR_DOUBLE;
                        public static final ForValue FOR_FLOAT;
                        public static final ForValue FOR_INTEGER;
                        public static final ForValue FOR_LONG;
                        public static final ForValue FOR_REFERENCE;
                        private final int defaultJump;
                        private final int load;
                        private final int nonDefaultJump;

                        protected abstract void convertValue(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice);

                        /* synthetic */ ForValue(String str, int i, int i2, int i3, int i4, AnonymousClass1 anonymousClass1) {
                            this(str, i, i2, i3, i4);
                        }

                        public static ForValue valueOf(String str) {
                            return (ForValue) Enum.valueOf(ForValue.class, str);
                        }

                        public static ForValue[] values() {
                            return (ForValue[]) $VALUES.clone();
                        }

                        static {
                            ForValue forValue = new ForValue("FOR_INTEGER", 0, 21, 154, 153) { // from class: net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher.ForValue.1
                                @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher.ForValue
                                protected void convertValue(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice) {
                                }

                                {
                                    AnonymousClass1 anonymousClass1 = null;
                                }
                            };
                            FOR_INTEGER = forValue;
                            int i = 154;
                            int i2 = 153;
                            ForValue forValue2 = new ForValue("FOR_LONG", 1, 22, i, i2) { // from class: net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher.ForValue.2
                                {
                                    AnonymousClass1 anonymousClass1 = null;
                                }

                                @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher.ForValue
                                protected void convertValue(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice) {
                                    methodVisitor.visitInsn(136);
                                }
                            };
                            FOR_LONG = forValue2;
                            ForValue forValue3 = new ForValue("FOR_FLOAT", 2, 23, 154, 153) { // from class: net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher.ForValue.3
                                {
                                    AnonymousClass1 anonymousClass1 = null;
                                }

                                @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher.ForValue
                                protected void convertValue(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice) {
                                    methodVisitor.visitInsn(11);
                                    methodVisitor.visitInsn(149);
                                    forAdvice.requireStackSize(2);
                                }
                            };
                            FOR_FLOAT = forValue3;
                            ForValue forValue4 = new ForValue("FOR_DOUBLE", 3, 24, i, i2) { // from class: net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher.ForValue.4
                                {
                                    AnonymousClass1 anonymousClass1 = null;
                                }

                                @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher.ForValue
                                protected void convertValue(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice) {
                                    methodVisitor.visitInsn(14);
                                    methodVisitor.visitInsn(151);
                                    forAdvice.requireStackSize(4);
                                }
                            };
                            FOR_DOUBLE = forValue4;
                            ForValue forValue5 = new ForValue("FOR_REFERENCE", 4, 25, 199, 198) { // from class: net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher.ForValue.5
                                @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher.ForValue
                                protected void convertValue(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice) {
                                }

                                {
                                    AnonymousClass1 anonymousClass1 = null;
                                }
                            };
                            FOR_REFERENCE = forValue5;
                            $VALUES = new ForValue[]{forValue, forValue2, forValue3, forValue4, forValue5};
                        }

                        private ForValue(String str, int i, int i2, int i3, int i4) {
                            this.load = i2;
                            this.defaultJump = i3;
                            this.nonDefaultJump = i4;
                        }

                        protected static SkipDispatcher of(TypeDefinition typeDefinition, boolean z) {
                            ForValue forValue;
                            if (typeDefinition.represents(Long.TYPE)) {
                                forValue = FOR_LONG;
                            } else if (typeDefinition.represents(Float.TYPE)) {
                                forValue = FOR_FLOAT;
                            } else if (typeDefinition.represents(Double.TYPE)) {
                                forValue = FOR_DOUBLE;
                            } else {
                                if (typeDefinition.represents(Void.TYPE)) {
                                    throw new IllegalStateException("Cannot skip on default value for void return type");
                                }
                                if (typeDefinition.isPrimitive()) {
                                    forValue = FOR_INTEGER;
                                } else {
                                    forValue = FOR_REFERENCE;
                                }
                            }
                            return z ? forValue.inverted() : forValue;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher
                        public void apply(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, MethodDescription methodDescription, Bound.SkipHandler skipHandler) {
                            doApply(methodVisitor, forAdvice, forAdvice2, methodDescription, skipHandler, false);
                        }

                        protected void doApply(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, MethodDescription methodDescription, Bound.SkipHandler skipHandler, boolean z) {
                            methodVisitor.visitVarInsn(this.load, methodDescription.getStackSize());
                            convertValue(methodVisitor, forAdvice);
                            Label label = new Label();
                            methodVisitor.visitJumpInsn(z ? this.nonDefaultJump : this.defaultJump, label);
                            skipHandler.apply(methodVisitor);
                            methodVisitor.visitLabel(label);
                            forAdvice2.injectCompletionFrame(methodVisitor, true);
                        }

                        private SkipDispatcher inverted() {
                            return new Inverted();
                        }

                        protected class Inverted implements SkipDispatcher {
                            protected Inverted() {
                            }

                            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher
                            public void apply(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, MethodDescription methodDescription, Bound.SkipHandler skipHandler) {
                                ForValue.this.doApply(methodVisitor, forAdvice, forAdvice2, methodDescription, skipHandler, true);
                            }

                            private SkipDispatcher getOuter() {
                                return ForValue.this;
                            }

                            public int hashCode() {
                                return ForValue.this.hashCode();
                            }

                            public boolean equals(Object obj) {
                                if (obj == this) {
                                    return true;
                                }
                                if (obj == null || obj.getClass() != getClass()) {
                                    return false;
                                }
                                return ((Inverted) obj).getOuter().equals(ForValue.this);
                            }
                        }
                    }

                    public static class ForType implements SkipDispatcher {
                        private final TypeDescription typeDescription;

                        protected boolean canEqual(Object obj) {
                            return obj instanceof ForType;
                        }

                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof ForType)) {
                                return false;
                            }
                            ForType forType = (ForType) obj;
                            if (!forType.canEqual(this)) {
                                return false;
                            }
                            TypeDescription typeDescription = this.typeDescription;
                            TypeDescription typeDescription2 = forType.typeDescription;
                            return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                        }

                        public int hashCode() {
                            TypeDescription typeDescription = this.typeDescription;
                            return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                        }

                        protected ForType(TypeDescription typeDescription) {
                            this.typeDescription = typeDescription;
                        }

                        public static SkipDispatcher of(MethodDescription methodDescription) {
                            return of((TypeDescription) methodDescription.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.SKIP_ON).resolve(TypeDescription.class), methodDescription);
                        }

                        protected static SkipDispatcher of(TypeDescription typeDescription, MethodDescription methodDescription) {
                            if (typeDescription.represents(Void.TYPE)) {
                                return Disabled.INSTANCE;
                            }
                            if (typeDescription.represents(OnDefaultValue.class)) {
                                return ForValue.of(methodDescription.getReturnType(), false);
                            }
                            if (typeDescription.represents(OnNonDefaultValue.class)) {
                                return ForValue.of(methodDescription.getReturnType(), true);
                            }
                            if (typeDescription.isPrimitive() || methodDescription.getReturnType().isPrimitive()) {
                                throw new IllegalStateException("Cannot skip method by instance type for primitive return value on " + methodDescription);
                            }
                            return new ForType(typeDescription);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter.SkipDispatcher
                        public void apply(MethodVisitor methodVisitor, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, MethodDescription methodDescription, Bound.SkipHandler skipHandler) {
                            methodVisitor.visitVarInsn(25, methodDescription.getStackSize());
                            methodVisitor.visitTypeInsn(193, this.typeDescription.getInternalName());
                            Label label = new Label();
                            methodVisitor.visitJumpInsn(153, label);
                            skipHandler.apply(methodVisitor);
                            methodVisitor.visitLabel(label);
                            forAdvice2.injectCompletionFrame(methodVisitor, true);
                        }
                    }
                }
            }
        }

        public enum Inactive implements Unresolved, Resolved.ForMethodEnter, Resolved.ForMethodExit, Bound.ForMethodEnter, Bound.ForMethodExit {
            INSTANCE;

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound.ForMethodExit
            public void apply() {
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound.ForMethodEnter
            public void apply(Bound.SkipHandler skipHandler) {
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Resolved.ForMethodExit asMethodExitTo(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Resolved.ForMethodEnter forMethodEnter) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
            public Inactive bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                return this;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher
            public boolean isAlive() {
                return false;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public boolean isBinary() {
                return false;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
            public boolean isPrependLineNumber() {
                return false;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
            public void prepare() {
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
            public TypeDescription getThrowable() {
                return NoExceptionHandler.DESCRIPTION;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
            public TypeDefinition getEnterType() {
                return TypeDescription.VOID;
            }
        }

        public static class Inlining implements Unresolved {
            protected final MethodDescription.InDefinedShape adviceMethod;

            @Override // net.bytebuddy.asm.Advice.Dispatcher
            public boolean isAlive() {
                return true;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public boolean isBinary() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Inlining;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Inlining)) {
                    return false;
                }
                Inlining inlining = (Inlining) obj;
                if (!inlining.canEqual(this)) {
                    return false;
                }
                MethodDescription.InDefinedShape inDefinedShape = this.adviceMethod;
                MethodDescription.InDefinedShape inDefinedShape2 = inlining.adviceMethod;
                return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
            }

            public int hashCode() {
                MethodDescription.InDefinedShape inDefinedShape = this.adviceMethod;
                return 59 + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
            }

            protected Inlining(MethodDescription.InDefinedShape inDefinedShape) {
                this.adviceMethod = inDefinedShape;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader) {
                return new Resolved.ForMethodEnter(this.adviceMethod, list, classReader);
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Resolved.ForMethodExit asMethodExitTo(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Resolved.ForMethodEnter forMethodEnter) {
                return Resolved.ForMethodExit.of(this.adviceMethod, list, classReader, forMethodEnter.getEnterType());
            }

            protected static abstract class Resolved implements Resolved {
                private static final boolean READ_ONLY = true;
                protected final MethodDescription.InDefinedShape adviceMethod;
                protected final ClassReader classReader;
                protected final Map<Integer, OffsetMapping> offsetMappings;
                protected final SuppressionHandler suppressionHandler;

                protected abstract MethodVisitor apply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound);

                @Override // net.bytebuddy.asm.Advice.Dispatcher
                public boolean isAlive() {
                    return true;
                }

                protected Resolved(MethodDescription.InDefinedShape inDefinedShape, List<OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDescription typeDescription) {
                    this.adviceMethod = inDefinedShape;
                    HashMap map = new HashMap();
                    for (OffsetMapping.Factory<?> factory : list) {
                        map.put(new TypeDescription.ForLoadedType(factory.getAnnotationType()), factory);
                    }
                    this.offsetMappings = new HashMap();
                    for (ParameterDescription.InDefinedShape inDefinedShape2 : inDefinedShape.getParameters()) {
                        OffsetMapping unresolved = null;
                        for (AnnotationDescription annotationDescription : inDefinedShape2.getDeclaredAnnotations()) {
                            OffsetMapping.Factory factory2 = (OffsetMapping.Factory) map.get(annotationDescription.getAnnotationType());
                            if (factory2 != null) {
                                OffsetMapping offsetMappingMake = factory2.make(inDefinedShape2, annotationDescription.prepare(factory2.getAnnotationType()), OffsetMapping.Factory.AdviceType.INLINING);
                                if (unresolved != null) {
                                    throw new IllegalStateException(inDefinedShape2 + " is bound to both " + offsetMappingMake + " and " + unresolved);
                                }
                                unresolved = offsetMappingMake;
                            }
                        }
                        Map<Integer, OffsetMapping> map2 = this.offsetMappings;
                        Integer numValueOf = Integer.valueOf(inDefinedShape2.getOffset());
                        if (unresolved == null) {
                            unresolved = new OffsetMapping.ForArgument.Unresolved(inDefinedShape2);
                        }
                        map2.put(numValueOf, unresolved);
                    }
                    this.classReader = classReader;
                    this.suppressionHandler = SuppressionHandler.Suppressing.of(typeDescription);
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Resolved resolved = (Resolved) obj;
                    return this.adviceMethod.equals(resolved.adviceMethod) && this.offsetMappings.equals(resolved.offsetMappings) && this.suppressionHandler.equals(resolved.suppressionHandler);
                }

                public int hashCode() {
                    return (((this.adviceMethod.hashCode() * 31) + this.offsetMappings.hashCode()) * 31) + this.suppressionHandler.hashCode();
                }

                protected abstract class AdviceMethodInliner extends ClassVisitor implements Bound {
                    protected final Assigner assigner;
                    protected final ClassReader classReader;
                    protected final Implementation.Context implementationContext;
                    protected final MethodDescription instrumentedMethod;
                    protected final TypeDescription instrumentedType;
                    protected List<Label> labels;
                    protected final MethodSizeHandler.ForInstrumentedMethod methodSizeHandler;
                    protected final MethodVisitor methodVisitor;
                    protected final StackMapFrameHandler.ForInstrumentedMethod stackMapFrameHandler;
                    protected final SuppressionHandler.Bound suppressionHandler;

                    protected AdviceMethodInliner(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, SuppressionHandler.Bound bound, ClassReader classReader) {
                        super(Opcodes.ASM6);
                        this.instrumentedType = typeDescription;
                        this.instrumentedMethod = methodDescription;
                        this.methodVisitor = methodVisitor;
                        this.implementationContext = context;
                        this.assigner = assigner;
                        this.methodSizeHandler = forInstrumentedMethod;
                        this.stackMapFrameHandler = forInstrumentedMethod2;
                        this.suppressionHandler = bound;
                        this.classReader = classReader;
                        this.labels = new ArrayList();
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
                    public void prepare() {
                        this.classReader.accept(new ExceptionTableExtractor(), 6);
                        this.suppressionHandler.onPrepare(this.methodVisitor);
                    }

                    protected void doApply() {
                        this.classReader.accept(this, this.stackMapFrameHandler.getReaderHint() | 2);
                    }

                    @Override // net.bytebuddy.jar.asm.ClassVisitor
                    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                        return (Resolved.this.adviceMethod.getInternalName().equals(str) && Resolved.this.adviceMethod.getDescriptor().equals(str2)) ? new ExceptionTableSubstitutor(Resolved.this.apply(this.methodVisitor, this.implementationContext, this.assigner, this.methodSizeHandler, this.stackMapFrameHandler, this.instrumentedType, this.instrumentedMethod, this.suppressionHandler)) : Dispatcher.IGNORE_METHOD;
                    }

                    protected class ExceptionTableExtractor extends ClassVisitor {
                        protected ExceptionTableExtractor() {
                            super(Opcodes.ASM6);
                        }

                        @Override // net.bytebuddy.jar.asm.ClassVisitor
                        public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                            if (!Resolved.this.adviceMethod.getInternalName().equals(str) || !Resolved.this.adviceMethod.getDescriptor().equals(str2)) {
                                return Dispatcher.IGNORE_METHOD;
                            }
                            AdviceMethodInliner adviceMethodInliner = AdviceMethodInliner.this;
                            return adviceMethodInliner.new ExceptionTableCollector(adviceMethodInliner.methodVisitor);
                        }
                    }

                    protected class ExceptionTableCollector extends MethodVisitor {
                        private final MethodVisitor methodVisitor;

                        protected ExceptionTableCollector(MethodVisitor methodVisitor) {
                            super(Opcodes.ASM6);
                            this.methodVisitor = methodVisitor;
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
                            this.methodVisitor.visitTryCatchBlock(label, label2, label3, str);
                            AdviceMethodInliner.this.labels.addAll(Arrays.asList(label, label2, label3));
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typePath, String str, boolean z) {
                            return this.methodVisitor.visitTryCatchAnnotation(i, typePath, str, z);
                        }
                    }

                    protected class ExceptionTableSubstitutor extends MethodVisitor {
                        private int index;
                        private final Map<Label, Label> substitutions;

                        protected ExceptionTableSubstitutor(MethodVisitor methodVisitor) {
                            super(Opcodes.ASM6, methodVisitor);
                            this.substitutions = new IdentityHashMap();
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
                            Map<Label, Label> map = this.substitutions;
                            List<Label> list = AdviceMethodInliner.this.labels;
                            int i = this.index;
                            this.index = i + 1;
                            map.put(label, list.get(i));
                            Map<Label, Label> map2 = this.substitutions;
                            List<Label> list2 = AdviceMethodInliner.this.labels;
                            int i2 = this.index;
                            this.index = i2 + 1;
                            map2.put(label2, list2.get(i2));
                            List<Label> list3 = AdviceMethodInliner.this.labels;
                            int i3 = this.index;
                            this.index = i3 + 1;
                            Label label4 = list3.get(i3);
                            this.substitutions.put(label3, label4);
                            ((CodeTranslationVisitor) this.mv).propagateHandler(label4);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typePath, String str, boolean z) {
                            return Dispatcher.IGNORE_ANNOTATION;
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitLabel(Label label) {
                            super.visitLabel(resolve(label));
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitJumpInsn(int i, Label label) {
                            super.visitJumpInsn(i, resolve(label));
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
                            super.visitTableSwitchInsn(i, i2, label, resolve(labelArr));
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
                            super.visitLookupSwitchInsn(resolve(label), iArr, resolve(labelArr));
                        }

                        private Label[] resolve(Label[] labelArr) {
                            Label[] labelArr2 = new Label[labelArr.length];
                            int length = labelArr.length;
                            int i = 0;
                            int i2 = 0;
                            while (i < length) {
                                labelArr2[i2] = resolve(labelArr[i]);
                                i++;
                                i2++;
                            }
                            return labelArr2;
                        }

                        private Label resolve(Label label) {
                            Label label2 = this.substitutions.get(label);
                            return label2 == null ? label : label2;
                        }
                    }
                }

                protected static class ForMethodEnter extends Resolved implements Resolved.ForMethodEnter {
                    private final boolean prependLineNumber;
                    private final Resolved.ForMethodEnter.SkipDispatcher skipDispatcher;

                    protected ForMethodEnter(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader) {
                        super(inDefinedShape, CompoundList.of(Arrays.asList(OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE, OffsetMapping.ForAllArguments.Factory.INSTANCE, OffsetMapping.ForThisReference.Factory.INSTANCE, OffsetMapping.ForField.Unresolved.Factory.INSTANCE, OffsetMapping.ForOrigin.Factory.INSTANCE, OffsetMapping.ForUnusedValue.Factory.INSTANCE, OffsetMapping.ForStubValue.INSTANCE, OffsetMapping.ForThrowable.Factory.INSTANCE, new OffsetMapping.Factory.Illegal(Thrown.class), new OffsetMapping.Factory.Illegal(Enter.class), new OffsetMapping.Factory.Illegal(Return.class)), (List) list), classReader, (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.SUPPRESS_ENTER).resolve(TypeDescription.class));
                        this.skipDispatcher = Resolved.ForMethodEnter.SkipDispatcher.ForType.of(inDefinedShape);
                        this.prependLineNumber = ((Boolean) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.PREPEND_LINE_NUMBER).resolve(Boolean.class)).booleanValue();
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                    public Bound.ForMethodEnter bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                        return new AdviceMethodInliner(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, this.suppressionHandler.bind(stackManipulation), this.classReader, this.skipDispatcher);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
                    public TypeDefinition getEnterType() {
                        return this.adviceMethod.getReturnType();
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
                    public boolean isPrependLineNumber() {
                        return this.prependLineNumber;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                    protected MethodVisitor apply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound) {
                        HashMap map = new HashMap();
                        for (Map.Entry<Integer, OffsetMapping> entry : this.offsetMappings.entrySet()) {
                            map.put(entry.getKey(), entry.getValue().resolve(typeDescription, methodDescription, assigner, OffsetMapping.Context.ForMethodEntry.of(methodDescription)));
                        }
                        return new CodeTranslationVisitor.ForMethodEnter(methodVisitor, context, forInstrumentedMethod.bindEntry(this.adviceMethod), forInstrumentedMethod2.bindEntry(this.adviceMethod), methodDescription, this.adviceMethod, map, bound);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                            return false;
                        }
                        ForMethodEnter forMethodEnter = (ForMethodEnter) obj;
                        return this.prependLineNumber == forMethodEnter.prependLineNumber && this.skipDispatcher.equals(forMethodEnter.skipDispatcher);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                    public int hashCode() {
                        return (((super.hashCode() * 31) + this.skipDispatcher.hashCode()) * 31) + (this.prependLineNumber ? 1 : 0);
                    }

                    protected class AdviceMethodInliner extends AdviceMethodInliner implements Bound.ForMethodEnter {
                        private final Resolved.ForMethodEnter.SkipDispatcher skipDispatcher;

                        protected AdviceMethodInliner(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, SuppressionHandler.Bound bound, ClassReader classReader, Resolved.ForMethodEnter.SkipDispatcher skipDispatcher) {
                            super(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, bound, classReader);
                            this.skipDispatcher = skipDispatcher;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound.ForMethodEnter
                        public void apply(Bound.SkipHandler skipHandler) {
                            doApply();
                            this.skipDispatcher.apply(this.methodVisitor, this.methodSizeHandler.bindEntry(ForMethodEnter.this.adviceMethod), this.stackMapFrameHandler.bindEntry(ForMethodEnter.this.adviceMethod), this.instrumentedMethod, skipHandler);
                        }
                    }
                }

                protected static abstract class ForMethodExit extends Resolved implements Resolved.ForMethodExit {
                    private final TypeDefinition enterType;

                    protected abstract StackSize getPadding();

                    protected ForMethodExit(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition) {
                        super(inDefinedShape, CompoundList.of(Arrays.asList(OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE, OffsetMapping.ForAllArguments.Factory.INSTANCE, OffsetMapping.ForThisReference.Factory.INSTANCE, OffsetMapping.ForField.Unresolved.Factory.INSTANCE, OffsetMapping.ForOrigin.Factory.INSTANCE, OffsetMapping.ForUnusedValue.Factory.INSTANCE, OffsetMapping.ForStubValue.INSTANCE, new OffsetMapping.ForEnterValue.Factory(typeDefinition), OffsetMapping.ForReturnValue.Factory.INSTANCE, OffsetMapping.ForThrowable.Factory.of(inDefinedShape)), (List) list), classReader, (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.SUPPRESS_EXIT).resolve(TypeDescription.class));
                        this.enterType = typeDefinition;
                    }

                    protected static Resolved.ForMethodExit of(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition) {
                        TypeDescription typeDescription = (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.ON_THROWABLE).resolve(TypeDescription.class);
                        return typeDescription.represents(NoExceptionHandler.class) ? new WithoutExceptionHandler(inDefinedShape, list, classReader, typeDefinition) : new WithExceptionHandler(inDefinedShape, list, classReader, typeDefinition, typeDescription);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                    protected MethodVisitor apply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound) {
                        HashMap map = new HashMap();
                        for (Map.Entry<Integer, OffsetMapping> entry : this.offsetMappings.entrySet()) {
                            map.put(entry.getKey(), entry.getValue().resolve(typeDescription, methodDescription, assigner, OffsetMapping.Context.ForMethodExit.of(this.enterType)));
                        }
                        return new CodeTranslationVisitor.ForMethodExit(methodVisitor, context, forInstrumentedMethod.bindExit(this.adviceMethod, getThrowable().represents(NoExceptionHandler.class)), forInstrumentedMethod2.bindExit(this.adviceMethod), methodDescription, this.adviceMethod, map, bound, this.enterType.getStackSize().getSize() + getPadding().getSize());
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                    public Bound.ForMethodExit bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                        return new AdviceMethodInliner(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, this.suppressionHandler.bind(stackManipulation), this.classReader);
                    }

                    protected class AdviceMethodInliner extends AdviceMethodInliner implements Bound.ForMethodExit {
                        public AdviceMethodInliner(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, SuppressionHandler.Bound bound, ClassReader classReader) {
                            super(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, bound, classReader);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound.ForMethodExit
                        public void apply() {
                            doApply();
                        }
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
                            return this.enterType.equals(((ForMethodExit) obj).enterType);
                        }
                        return false;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                    public int hashCode() {
                        return (super.hashCode() * 31) + this.enterType.hashCode();
                    }

                    protected static class WithExceptionHandler extends ForMethodExit {
                        private final TypeDescription throwable;

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Resolved
                        public /* bridge */ /* synthetic */ Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                            return super.bind(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, stackManipulation);
                        }

                        protected boolean canEqual(Object obj) {
                            return obj instanceof WithExceptionHandler;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof WithExceptionHandler)) {
                                return false;
                            }
                            WithExceptionHandler withExceptionHandler = (WithExceptionHandler) obj;
                            if (!withExceptionHandler.canEqual(this) || !super.equals(obj)) {
                                return false;
                            }
                            TypeDescription throwable = getThrowable();
                            TypeDescription throwable2 = withExceptionHandler.getThrowable();
                            return throwable != null ? throwable.equals(throwable2) : throwable2 == null;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved
                        public int hashCode() {
                            int iHashCode = super.hashCode() + 59;
                            TypeDescription throwable = getThrowable();
                            return (iHashCode * 59) + (throwable == null ? 43 : throwable.hashCode());
                        }

                        protected WithExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition, TypeDescription typeDescription) {
                            super(inDefinedShape, list, classReader, typeDefinition);
                            this.throwable = typeDescription;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodExit
                        protected StackSize getPadding() {
                            return this.throwable.getStackSize();
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
                        public TypeDescription getThrowable() {
                            return this.throwable;
                        }
                    }

                    protected static class WithoutExceptionHandler extends ForMethodExit {
                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Resolved
                        public /* bridge */ /* synthetic */ Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                            return super.bind(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, stackManipulation);
                        }

                        protected WithoutExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition) {
                            super(inDefinedShape, list, classReader, typeDefinition);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodExit
                        protected StackSize getPadding() {
                            return StackSize.ZERO;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
                        public TypeDescription getThrowable() {
                            return NoExceptionHandler.DESCRIPTION;
                        }
                    }
                }
            }

            protected static abstract class CodeTranslationVisitor extends MethodVisitor implements SuppressionHandler.ReturnValueProducer {
                protected final MethodDescription.InDefinedShape adviceMethod;
                protected final Label endOfMethod;
                protected final Implementation.Context implementationContext;
                protected final MethodDescription instrumentedMethod;
                protected final MethodSizeHandler.ForAdvice methodSizeHandler;
                protected final MethodVisitor methodVisitor;
                private final Map<Integer, OffsetMapping.Target> offsetMappings;
                protected final StackMapFrameHandler.ForAdvice stackMapFrameHandler;
                private final SuppressionHandler.Bound suppressionHandler;

                protected abstract int adjust(int i);

                protected abstract void onMethodReturn();

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitAttribute(Attribute attribute) {
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public abstract void visitInsn(int i);

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitParameter(String str, int i) {
                }

                protected CodeTranslationVisitor(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, MethodDescription methodDescription, MethodDescription.InDefinedShape inDefinedShape, Map<Integer, OffsetMapping.Target> map, SuppressionHandler.Bound bound) {
                    super(Opcodes.ASM6, new StackAwareMethodVisitor(methodVisitor, methodDescription));
                    this.methodVisitor = methodVisitor;
                    this.implementationContext = context;
                    this.methodSizeHandler = forAdvice;
                    this.stackMapFrameHandler = forAdvice2;
                    this.instrumentedMethod = methodDescription;
                    this.adviceMethod = inDefinedShape;
                    this.offsetMappings = map;
                    this.suppressionHandler = bound;
                    this.endOfMethod = new Label();
                }

                protected void propagateHandler(Label label) {
                    ((StackAwareMethodVisitor) this.mv).register(label, Collections.singletonList(StackSize.SINGLE));
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public AnnotationVisitor visitAnnotationDefault() {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitCode() {
                    this.suppressionHandler.onStart(this.methodVisitor);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                    this.stackMapFrameHandler.translateFrame(this.methodVisitor, i, i2, objArr, i3, objArr2);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitEnd() {
                    this.suppressionHandler.onEnd(this.methodVisitor, this.implementationContext, this.methodSizeHandler, this.stackMapFrameHandler, this);
                    this.methodVisitor.visitLabel(this.endOfMethod);
                    onMethodReturn();
                    this.stackMapFrameHandler.injectCompletionFrame(this.methodVisitor, false);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitMaxs(int i, int i2) {
                    this.methodSizeHandler.recordMaxima(i, i2);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitVarInsn(int i, int i2) {
                    StackManipulation stackManipulationResolveRead;
                    StackSize stackSize;
                    OffsetMapping.Target target = this.offsetMappings.get(Integer.valueOf(i2));
                    if (target != null) {
                        switch (i) {
                            case 21:
                            case 23:
                            case 25:
                                stackManipulationResolveRead = target.resolveRead();
                                stackSize = StackSize.SINGLE;
                                break;
                            case 22:
                            case 24:
                                stackManipulationResolveRead = target.resolveRead();
                                stackSize = StackSize.DOUBLE;
                                break;
                            default:
                                switch (i) {
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                    case 58:
                                        stackManipulationResolveRead = target.resolveWrite();
                                        stackSize = StackSize.ZERO;
                                        break;
                                    default:
                                        throw new IllegalStateException("Unexpected opcode: " + i);
                                }
                                break;
                        }
                        this.methodSizeHandler.recordPadding(stackManipulationResolveRead.apply(this.mv, this.implementationContext).getMaximalSize() - stackSize.getSize());
                        return;
                    }
                    this.mv.visitVarInsn(i, adjust((i2 + this.instrumentedMethod.getStackSize()) - this.adviceMethod.getStackSize()));
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitIincInsn(int i, int i2) {
                    OffsetMapping.Target target = this.offsetMappings.get(Integer.valueOf(i));
                    if (target != null) {
                        this.methodSizeHandler.recordPadding(target.resolveIncrement(i2).apply(this.mv, this.implementationContext).getMaximalSize());
                    } else {
                        this.mv.visitIincInsn(adjust((i + this.instrumentedMethod.getStackSize()) - this.adviceMethod.getStackSize()), i2);
                    }
                }

                protected static class ForMethodEnter extends CodeTranslationVisitor {
                    private boolean doesReturn;

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.CodeTranslationVisitor
                    protected int adjust(int i) {
                        return i;
                    }

                    protected ForMethodEnter(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, MethodDescription methodDescription, MethodDescription.InDefinedShape inDefinedShape, Map<Integer, OffsetMapping.Target> map, SuppressionHandler.Bound bound) {
                        super(methodVisitor, context, forAdvice, forAdvice2, methodDescription, inDefinedShape, map, bound);
                        this.doesReturn = false;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.CodeTranslationVisitor, net.bytebuddy.jar.asm.MethodVisitor
                    public void visitInsn(int i) {
                        switch (i) {
                            case 172:
                                this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(54, 21, StackSize.SINGLE));
                                break;
                            case 173:
                                this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(55, 22, StackSize.DOUBLE));
                                break;
                            case 174:
                                this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(56, 23, StackSize.SINGLE));
                                break;
                            case 175:
                                this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(57, 24, StackSize.DOUBLE));
                                break;
                            case 176:
                                this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(58, 25, StackSize.SINGLE));
                                break;
                            case 177:
                                ((StackAwareMethodVisitor) this.mv).drainStack();
                                break;
                            default:
                                this.mv.visitInsn(i);
                                return;
                        }
                        this.mv.visitJumpInsn(167, this.endOfMethod);
                        this.doesReturn = true;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.ReturnValueProducer
                    public void onDefaultValue(MethodVisitor methodVisitor) {
                        if (this.adviceMethod.getReturnType().represents(Boolean.TYPE) || this.adviceMethod.getReturnType().represents(Byte.TYPE) || this.adviceMethod.getReturnType().represents(Short.TYPE) || this.adviceMethod.getReturnType().represents(Character.TYPE) || this.adviceMethod.getReturnType().represents(Integer.TYPE)) {
                            methodVisitor.visitInsn(3);
                        } else if (this.adviceMethod.getReturnType().represents(Long.TYPE)) {
                            methodVisitor.visitInsn(9);
                        } else if (this.adviceMethod.getReturnType().represents(Float.TYPE)) {
                            methodVisitor.visitInsn(11);
                        } else if (this.adviceMethod.getReturnType().represents(Double.TYPE)) {
                            methodVisitor.visitInsn(14);
                        } else if (!this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                            methodVisitor.visitInsn(1);
                        }
                        this.doesReturn = true;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.CodeTranslationVisitor
                    protected void onMethodReturn() {
                        Type type = Type.getType(this.adviceMethod.getReturnType().asErasure().getDescriptor());
                        if (!this.doesReturn || type.equals(Type.VOID_TYPE)) {
                            return;
                        }
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(type.getOpcode(54), this.instrumentedMethod.getStackSize());
                    }
                }

                protected static class ForMethodExit extends CodeTranslationVisitor {
                    private final int padding;

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.ReturnValueProducer
                    public void onDefaultValue(MethodVisitor methodVisitor) {
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.CodeTranslationVisitor
                    protected void onMethodReturn() {
                    }

                    protected ForMethodExit(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, MethodDescription methodDescription, MethodDescription.InDefinedShape inDefinedShape, Map<Integer, OffsetMapping.Target> map, SuppressionHandler.Bound bound, int i) {
                        super(methodVisitor, context, forAdvice, forAdvice2, methodDescription, inDefinedShape, map, bound);
                        this.padding = i;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.CodeTranslationVisitor, net.bytebuddy.jar.asm.MethodVisitor
                    public void visitInsn(int i) {
                        switch (i) {
                            case 172:
                            case 174:
                            case 176:
                                this.mv.visitInsn(87);
                                break;
                            case 173:
                            case 175:
                                this.mv.visitInsn(88);
                                break;
                            case 177:
                                break;
                            default:
                                this.mv.visitInsn(i);
                                return;
                        }
                        ((StackAwareMethodVisitor) this.mv).drainStack();
                        this.mv.visitJumpInsn(167, this.endOfMethod);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Inlining.CodeTranslationVisitor
                    protected int adjust(int i) {
                        return this.instrumentedMethod.getReturnType().getStackSize().getSize() + this.padding + i;
                    }
                }
            }
        }

        public static class Delegating implements Unresolved {
            protected final MethodDescription.InDefinedShape adviceMethod;

            @Override // net.bytebuddy.asm.Advice.Dispatcher
            public boolean isAlive() {
                return true;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public boolean isBinary() {
                return false;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Delegating;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Delegating)) {
                    return false;
                }
                Delegating delegating = (Delegating) obj;
                if (!delegating.canEqual(this)) {
                    return false;
                }
                MethodDescription.InDefinedShape inDefinedShape = this.adviceMethod;
                MethodDescription.InDefinedShape inDefinedShape2 = delegating.adviceMethod;
                return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
            }

            public int hashCode() {
                MethodDescription.InDefinedShape inDefinedShape = this.adviceMethod;
                return 59 + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
            }

            protected Delegating(MethodDescription.InDefinedShape inDefinedShape) {
                this.adviceMethod = inDefinedShape;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader) {
                return new Resolved.ForMethodEnter(this.adviceMethod, list);
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Unresolved
            public Resolved.ForMethodExit asMethodExitTo(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Resolved.ForMethodEnter forMethodEnter) {
                return Resolved.ForMethodExit.of(this.adviceMethod, list, forMethodEnter.getEnterType());
            }

            protected static abstract class Resolved<T extends Bound> implements Resolved {
                protected final MethodDescription.InDefinedShape adviceMethod;
                protected final List<OffsetMapping> offsetMappings;
                protected final SuppressionHandler suppressionHandler;

                @Override // net.bytebuddy.asm.Advice.Dispatcher
                public boolean isAlive() {
                    return true;
                }

                protected abstract T resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation);

                protected Resolved(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDescription typeDescription) {
                    this.adviceMethod = inDefinedShape;
                    HashMap map = new HashMap();
                    for (OffsetMapping.Factory<?> factory : list) {
                        map.put(new TypeDescription.ForLoadedType(factory.getAnnotationType()), factory);
                    }
                    this.offsetMappings = new ArrayList();
                    for (ParameterDescription.InDefinedShape inDefinedShape2 : inDefinedShape.getParameters()) {
                        OffsetMapping unresolved = null;
                        for (AnnotationDescription annotationDescription : inDefinedShape2.getDeclaredAnnotations()) {
                            OffsetMapping.Factory factory2 = (OffsetMapping.Factory) map.get(annotationDescription.getAnnotationType());
                            if (factory2 != null) {
                                OffsetMapping offsetMappingMake = factory2.make(inDefinedShape2, annotationDescription.prepare(factory2.getAnnotationType()), OffsetMapping.Factory.AdviceType.DELEGATION);
                                if (unresolved != null) {
                                    throw new IllegalStateException(inDefinedShape2 + " is bound to both " + offsetMappingMake + " and " + unresolved);
                                }
                                unresolved = offsetMappingMake;
                            }
                        }
                        List<OffsetMapping> list2 = this.offsetMappings;
                        if (unresolved == null) {
                            unresolved = new OffsetMapping.ForArgument.Unresolved(inDefinedShape2);
                        }
                        list2.add(unresolved);
                    }
                    this.suppressionHandler = SuppressionHandler.Suppressing.of(typeDescription);
                }

                @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved
                public T bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                    if (!this.adviceMethod.isVisibleTo(typeDescription)) {
                        throw new IllegalStateException(this.adviceMethod + " is not visible to " + methodDescription.getDeclaringType());
                    }
                    return (T) resolve(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, stackManipulation);
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Resolved resolved = (Resolved) obj;
                    return this.adviceMethod.equals(resolved.adviceMethod) && this.offsetMappings.equals(resolved.offsetMappings) && this.suppressionHandler.equals(resolved.suppressionHandler);
                }

                public int hashCode() {
                    return (((this.adviceMethod.hashCode() * 31) + this.offsetMappings.hashCode()) * 31) + this.suppressionHandler.hashCode();
                }

                protected static abstract class AdviceMethodWriter implements Bound, SuppressionHandler.ReturnValueProducer {
                    private static final int EMPTY = 0;
                    protected final MethodDescription.InDefinedShape adviceMethod;
                    protected final Implementation.Context implementationContext;
                    protected final MethodDescription instrumentedMethod;
                    protected final MethodSizeHandler.ForAdvice methodSizeHandler;
                    protected final MethodVisitor methodVisitor;
                    private final List<OffsetMapping.Target> offsetMappings;
                    protected final StackMapFrameHandler.ForAdvice stackMapFrameHandler;
                    private final SuppressionHandler.Bound suppressionHandler;

                    protected abstract void onMethodReturn();

                    protected AdviceMethodWriter(MethodDescription.InDefinedShape inDefinedShape, MethodDescription methodDescription, List<OffsetMapping.Target> list, MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, SuppressionHandler.Bound bound) {
                        this.adviceMethod = inDefinedShape;
                        this.instrumentedMethod = methodDescription;
                        this.offsetMappings = list;
                        this.methodVisitor = methodVisitor;
                        this.implementationContext = context;
                        this.methodSizeHandler = forAdvice;
                        this.stackMapFrameHandler = forAdvice2;
                        this.suppressionHandler = bound;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound
                    public void prepare() {
                        this.suppressionHandler.onPrepare(this.methodVisitor);
                    }

                    protected void doApply() {
                        this.suppressionHandler.onStart(this.methodVisitor);
                        int size = 0;
                        int i = 0;
                        int iMax = 0;
                        for (OffsetMapping.Target target : this.offsetMappings) {
                            size += ((ParameterDescription.InDefinedShape) this.adviceMethod.getParameters().get(i)).getType().getStackSize().getSize();
                            iMax = Math.max(iMax, target.resolveRead().apply(this.methodVisitor, this.implementationContext).getMaximalSize() + size);
                            i++;
                        }
                        this.methodVisitor.visitMethodInsn(184, this.adviceMethod.getDeclaringType().getInternalName(), this.adviceMethod.getInternalName(), this.adviceMethod.getDescriptor(), false);
                        onMethodReturn();
                        this.suppressionHandler.onEndSkipped(this.methodVisitor, this.implementationContext, this.methodSizeHandler, this.stackMapFrameHandler, this);
                        this.stackMapFrameHandler.injectCompletionFrame(this.methodVisitor, false);
                        this.methodSizeHandler.recordMaxima(Math.max(iMax, this.adviceMethod.getReturnType().getStackSize().getSize()), 0);
                    }

                    protected static class ForMethodEnter extends AdviceMethodWriter implements Bound.ForMethodEnter {
                        private final Resolved.ForMethodEnter.SkipDispatcher skipDispatcher;

                        protected ForMethodEnter(MethodDescription.InDefinedShape inDefinedShape, MethodDescription methodDescription, List<OffsetMapping.Target> list, MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, SuppressionHandler.Bound bound, Resolved.ForMethodEnter.SkipDispatcher skipDispatcher) {
                            super(inDefinedShape, methodDescription, list, methodVisitor, context, forAdvice, forAdvice2, bound);
                            this.skipDispatcher = skipDispatcher;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.AdviceMethodWriter
                        protected void onMethodReturn() {
                            if (this.adviceMethod.getReturnType().represents(Boolean.TYPE) || this.adviceMethod.getReturnType().represents(Byte.TYPE) || this.adviceMethod.getReturnType().represents(Short.TYPE) || this.adviceMethod.getReturnType().represents(Character.TYPE) || this.adviceMethod.getReturnType().represents(Integer.TYPE)) {
                                this.methodVisitor.visitVarInsn(54, this.instrumentedMethod.getStackSize());
                                return;
                            }
                            if (this.adviceMethod.getReturnType().represents(Long.TYPE)) {
                                this.methodVisitor.visitVarInsn(55, this.instrumentedMethod.getStackSize());
                                return;
                            }
                            if (this.adviceMethod.getReturnType().represents(Float.TYPE)) {
                                this.methodVisitor.visitVarInsn(56, this.instrumentedMethod.getStackSize());
                            } else if (this.adviceMethod.getReturnType().represents(Double.TYPE)) {
                                this.methodVisitor.visitVarInsn(57, this.instrumentedMethod.getStackSize());
                            } else {
                                if (this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                                    return;
                                }
                                this.methodVisitor.visitVarInsn(58, this.instrumentedMethod.getStackSize());
                            }
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound.ForMethodEnter
                        public void apply(Bound.SkipHandler skipHandler) {
                            doApply();
                            this.skipDispatcher.apply(this.methodVisitor, this.methodSizeHandler, this.stackMapFrameHandler, this.instrumentedMethod, skipHandler);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.ReturnValueProducer
                        public void onDefaultValue(MethodVisitor methodVisitor) {
                            if (this.adviceMethod.getReturnType().represents(Boolean.TYPE) || this.adviceMethod.getReturnType().represents(Byte.TYPE) || this.adviceMethod.getReturnType().represents(Short.TYPE) || this.adviceMethod.getReturnType().represents(Character.TYPE) || this.adviceMethod.getReturnType().represents(Integer.TYPE)) {
                                methodVisitor.visitInsn(3);
                                methodVisitor.visitVarInsn(54, this.instrumentedMethod.getStackSize());
                                return;
                            }
                            if (this.adviceMethod.getReturnType().represents(Long.TYPE)) {
                                methodVisitor.visitInsn(9);
                                methodVisitor.visitVarInsn(55, this.instrumentedMethod.getStackSize());
                                return;
                            }
                            if (this.adviceMethod.getReturnType().represents(Float.TYPE)) {
                                methodVisitor.visitInsn(11);
                                methodVisitor.visitVarInsn(56, this.instrumentedMethod.getStackSize());
                            } else if (this.adviceMethod.getReturnType().represents(Double.TYPE)) {
                                methodVisitor.visitInsn(14);
                                methodVisitor.visitVarInsn(57, this.instrumentedMethod.getStackSize());
                            } else {
                                if (this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                                    return;
                                }
                                methodVisitor.visitInsn(1);
                                methodVisitor.visitVarInsn(58, this.instrumentedMethod.getStackSize());
                            }
                        }
                    }

                    protected static class ForMethodExit extends AdviceMethodWriter implements Bound.ForMethodExit {
                        @Override // net.bytebuddy.asm.Advice.Dispatcher.SuppressionHandler.ReturnValueProducer
                        public void onDefaultValue(MethodVisitor methodVisitor) {
                        }

                        protected ForMethodExit(MethodDescription.InDefinedShape inDefinedShape, MethodDescription methodDescription, List<OffsetMapping.Target> list, MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, SuppressionHandler.Bound bound) {
                            super(inDefinedShape, methodDescription, list, methodVisitor, context, forAdvice, forAdvice2, bound);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound.ForMethodExit
                        public void apply() {
                            doApply();
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.AdviceMethodWriter
                        protected void onMethodReturn() {
                            int i = AnonymousClass1.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[this.adviceMethod.getReturnType().getStackSize().ordinal()];
                            if (i != 1) {
                                if (i == 2) {
                                    this.methodVisitor.visitInsn(87);
                                } else {
                                    if (i == 3) {
                                        this.methodVisitor.visitInsn(88);
                                        return;
                                    }
                                    throw new IllegalStateException("Unexpected size: " + this.adviceMethod.getReturnType().getStackSize());
                                }
                            }
                        }
                    }
                }

                protected static class ForMethodEnter extends Resolved<Bound.ForMethodEnter> implements Resolved.ForMethodEnter {
                    private final boolean prependLineNumber;
                    private final Resolved.ForMethodEnter.SkipDispatcher skipDispatcher;

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved, net.bytebuddy.asm.Advice.Dispatcher.Resolved
                    public /* bridge */ /* synthetic */ Bound.ForMethodEnter bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                        return (Bound.ForMethodEnter) super.bind(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, stackManipulation);
                    }

                    protected ForMethodEnter(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list) {
                        super(inDefinedShape, CompoundList.of(Arrays.asList(OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE, OffsetMapping.ForAllArguments.Factory.INSTANCE, OffsetMapping.ForThisReference.Factory.INSTANCE, OffsetMapping.ForField.Unresolved.Factory.INSTANCE, OffsetMapping.ForOrigin.Factory.INSTANCE, OffsetMapping.ForUnusedValue.Factory.INSTANCE, OffsetMapping.ForStubValue.INSTANCE, new OffsetMapping.Factory.Illegal(Thrown.class), new OffsetMapping.Factory.Illegal(Enter.class), new OffsetMapping.Factory.Illegal(Return.class)), (List) list), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.SUPPRESS_ENTER).resolve(TypeDescription.class));
                        this.skipDispatcher = Resolved.ForMethodEnter.SkipDispatcher.ForType.of(inDefinedShape);
                        this.prependLineNumber = ((Boolean) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.PREPEND_LINE_NUMBER).resolve(Boolean.class)).booleanValue();
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
                    public TypeDefinition getEnterType() {
                        return this.adviceMethod.getReturnType();
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter
                    public boolean isPrependLineNumber() {
                        return this.prependLineNumber;
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                    public Bound.ForMethodEnter resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                        ArrayList arrayList = new ArrayList(this.offsetMappings.size());
                        Iterator<OffsetMapping> it = this.offsetMappings.iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next().resolve(typeDescription, methodDescription, assigner, OffsetMapping.Context.ForMethodEntry.of(methodDescription)));
                        }
                        return new AdviceMethodWriter.ForMethodEnter(this.adviceMethod, methodDescription, arrayList, methodVisitor, context, forInstrumentedMethod.bindEntry(this.adviceMethod), forInstrumentedMethod2.bindEntry(this.adviceMethod), this.suppressionHandler.bind(stackManipulation), this.skipDispatcher);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                            return false;
                        }
                        ForMethodEnter forMethodEnter = (ForMethodEnter) obj;
                        return this.prependLineNumber == forMethodEnter.prependLineNumber && this.skipDispatcher.equals(forMethodEnter.skipDispatcher);
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                    public int hashCode() {
                        return (((super.hashCode() * 31) + this.skipDispatcher.hashCode()) * 31) + (this.prependLineNumber ? 1 : 0);
                    }
                }

                protected static abstract class ForMethodExit extends Resolved<Bound.ForMethodExit> implements Resolved.ForMethodExit {
                    private final TypeDefinition enterType;

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved, net.bytebuddy.asm.Advice.Dispatcher.Resolved
                    public /* bridge */ /* synthetic */ Bound.ForMethodExit bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                        return (Bound.ForMethodExit) super.bind(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, stackManipulation);
                    }

                    protected ForMethodExit(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition) {
                        super(inDefinedShape, CompoundList.of(Arrays.asList(OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE, OffsetMapping.ForAllArguments.Factory.INSTANCE, OffsetMapping.ForThisReference.Factory.INSTANCE, OffsetMapping.ForField.Unresolved.Factory.INSTANCE, OffsetMapping.ForOrigin.Factory.INSTANCE, OffsetMapping.ForUnusedValue.Factory.INSTANCE, OffsetMapping.ForStubValue.INSTANCE, new OffsetMapping.ForEnterValue.Factory(typeDefinition), OffsetMapping.ForReturnValue.Factory.INSTANCE, OffsetMapping.ForThrowable.Factory.of(inDefinedShape)), (List) list), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.SUPPRESS_EXIT).resolve(TypeDescription.class));
                        this.enterType = typeDefinition;
                    }

                    protected static Resolved.ForMethodExit of(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition) {
                        TypeDescription typeDescription = (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.ON_THROWABLE).resolve(TypeDescription.class);
                        return typeDescription.represents(NoExceptionHandler.class) ? new WithoutExceptionHandler(inDefinedShape, list, typeDefinition) : new WithExceptionHandler(inDefinedShape, list, typeDefinition, typeDescription);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                    public Bound.ForMethodExit resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                        ArrayList arrayList = new ArrayList(this.offsetMappings.size());
                        Iterator<OffsetMapping> it = this.offsetMappings.iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next().resolve(typeDescription, methodDescription, assigner, OffsetMapping.Context.ForMethodExit.of(this.enterType)));
                        }
                        return new AdviceMethodWriter.ForMethodExit(this.adviceMethod, methodDescription, arrayList, methodVisitor, context, forInstrumentedMethod.bindExit(this.adviceMethod, getThrowable().represents(NoExceptionHandler.class)), forInstrumentedMethod2.bindExit(this.adviceMethod), this.suppressionHandler.bind(stackManipulation));
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
                            return this.enterType.equals(((ForMethodExit) obj).enterType);
                        }
                        return false;
                    }

                    @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                    public int hashCode() {
                        return (super.hashCode() * 31) + this.enterType.hashCode();
                    }

                    protected static class WithExceptionHandler extends ForMethodExit {
                        private final TypeDescription throwable;

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved, net.bytebuddy.asm.Advice.Dispatcher.Resolved
                        public /* bridge */ /* synthetic */ Bound.ForMethodExit bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                            return (Bound.ForMethodExit) super.bind(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, stackManipulation);
                        }

                        protected boolean canEqual(Object obj) {
                            return obj instanceof WithExceptionHandler;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                        public boolean equals(Object obj) {
                            if (obj == this) {
                                return true;
                            }
                            if (!(obj instanceof WithExceptionHandler)) {
                                return false;
                            }
                            WithExceptionHandler withExceptionHandler = (WithExceptionHandler) obj;
                            if (!withExceptionHandler.canEqual(this) || !super.equals(obj)) {
                                return false;
                            }
                            TypeDescription throwable = getThrowable();
                            TypeDescription throwable2 = withExceptionHandler.getThrowable();
                            return throwable != null ? throwable.equals(throwable2) : throwable2 == null;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                        public int hashCode() {
                            int iHashCode = super.hashCode() + 59;
                            TypeDescription throwable = getThrowable();
                            return (iHashCode * 59) + (throwable == null ? 43 : throwable.hashCode());
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                        protected /* bridge */ /* synthetic */ Bound resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                            return super.resolve(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, stackManipulation);
                        }

                        protected WithExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, TypeDescription typeDescription) {
                            super(inDefinedShape, list, typeDefinition);
                            this.throwable = typeDescription;
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
                        public TypeDescription getThrowable() {
                            return this.throwable;
                        }
                    }

                    protected static class WithoutExceptionHandler extends ForMethodExit {
                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved, net.bytebuddy.asm.Advice.Dispatcher.Resolved
                        public /* bridge */ /* synthetic */ Bound.ForMethodExit bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                            return (Bound.ForMethodExit) super.bind(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, stackManipulation);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved.ForMethodExit, net.bytebuddy.asm.Advice.Dispatcher.Delegating.Resolved
                        protected /* bridge */ /* synthetic */ Bound resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod2, StackManipulation stackManipulation) {
                            return super.resolve(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, stackManipulation);
                        }

                        protected WithoutExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition) {
                            super(inDefinedShape, list, typeDefinition);
                        }

                        @Override // net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit
                        public TypeDescription getThrowable() {
                            return NoExceptionHandler.DESCRIPTION;
                        }
                    }
                }
            }
        }
    }

    protected static abstract class AdviceVisitor extends ExceptionTableSensitiveMethodVisitor implements Dispatcher.Bound.SkipHandler {
        private static final int NO_OFFSET = 0;
        protected final MethodDescription instrumentedMethod;
        private final Dispatcher.Bound.ForMethodEnter methodEnter;
        protected final Dispatcher.Bound.ForMethodExit methodExit;
        protected final MethodSizeHandler.ForInstrumentedMethod methodSizeHandler;
        protected final MethodVisitor methodVisitor;
        private final int padding;
        protected final StackMapFrameHandler.ForInstrumentedMethod stackMapFrameHandler;

        protected abstract void onUserEnd();

        protected abstract void onUserPrepare();

        protected abstract void onUserStart();

        protected AdviceVisitor(MethodVisitor methodVisitor, MethodVisitor methodVisitor2, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, List<? extends TypeDescription> list, int i, int i2) {
            List listSingletonList;
            super(Opcodes.ASM6, methodVisitor2);
            this.methodVisitor = methodVisitor;
            this.instrumentedMethod = methodDescription;
            this.padding = forMethodEnter.getEnterType().getStackSize().getSize();
            if (forMethodEnter.getEnterType().represents(Void.TYPE)) {
                listSingletonList = Collections.emptyList();
            } else {
                listSingletonList = Collections.singletonList(forMethodEnter.getEnterType().asErasure());
            }
            List list2 = listSingletonList;
            MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethodOf = MethodSizeHandler.Default.of(methodDescription, list2, list, i);
            this.methodSizeHandler = forInstrumentedMethodOf;
            StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethodOf2 = StackMapFrameHandler.Default.of(typeDescription, methodDescription, list2, list, context.getClassFileVersion(), i, i2);
            this.stackMapFrameHandler = forInstrumentedMethodOf2;
            this.methodEnter = forMethodEnter.bind(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethodOf, forInstrumentedMethodOf2, stackManipulation);
            this.methodExit = forMethodExit.bind(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethodOf, forInstrumentedMethodOf2, stackManipulation);
        }

        @Override // net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor
        protected void onAfterExceptionTable() {
            this.methodEnter.prepare();
            onUserPrepare();
            this.methodExit.prepare();
            this.methodEnter.apply(this);
            onUserStart();
        }

        @Override // net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor
        protected void onVisitVarInsn(int i, int i2) {
            this.mv.visitVarInsn(i, resolve(i2));
        }

        @Override // net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor
        protected void onVisitIincInsn(int i, int i2) {
            this.mv.visitIincInsn(resolve(i), i2);
        }

        protected void variable(int i) {
            variable(i, 0);
        }

        protected void variable(int i, int i2) {
            this.methodVisitor.visitVarInsn(i, this.instrumentedMethod.getStackSize() + this.padding + i2);
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
            this.stackMapFrameHandler.translateFrame(this.methodVisitor, i, i2, objArr, i3, objArr2);
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitMaxs(int i, int i2) {
            onUserEnd();
            this.methodVisitor.visitMaxs(this.methodSizeHandler.compoundStackSize(i), this.methodSizeHandler.compoundLocalVariableLength(i2));
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
            this.mv.visitLocalVariable(str, str2, str3, label, label2, resolve(i));
        }

        @Override // net.bytebuddy.jar.asm.MethodVisitor
        public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
            return this.mv.visitLocalVariableAnnotation(i, typePath, labelArr, labelArr2, resolve(iArr), str, z);
        }

        private int[] resolve(int[] iArr) {
            int[] iArr2 = new int[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                iArr2[i] = resolve(iArr[i]);
            }
            return iArr2;
        }

        private int resolve(int i) {
            return i < this.instrumentedMethod.getStackSize() ? i : i + this.padding;
        }

        protected static class WithoutExitAdvice extends AdviceVisitor {
            @Override // net.bytebuddy.asm.Advice.AdviceVisitor
            protected void onUserEnd() {
            }

            @Override // net.bytebuddy.asm.Advice.AdviceVisitor
            protected void onUserPrepare() {
            }

            @Override // net.bytebuddy.asm.Advice.AdviceVisitor
            protected void onUserStart() {
            }

            protected WithoutExitAdvice(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, int i, int i2) {
                super(methodVisitor, methodVisitor, context, assigner, stackManipulation, typeDescription, methodDescription, forMethodEnter, Dispatcher.Inactive.INSTANCE, Collections.emptyList(), i, i2);
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound.SkipHandler
            public void apply(MethodVisitor methodVisitor) {
                if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                    methodVisitor.visitInsn(3);
                    methodVisitor.visitInsn(172);
                    return;
                }
                if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                    methodVisitor.visitInsn(9);
                    methodVisitor.visitInsn(173);
                    return;
                }
                if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                    methodVisitor.visitInsn(11);
                    methodVisitor.visitInsn(174);
                } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                    methodVisitor.visitInsn(14);
                    methodVisitor.visitInsn(175);
                } else if (this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                    methodVisitor.visitInsn(177);
                } else {
                    methodVisitor.visitInsn(1);
                    methodVisitor.visitInsn(176);
                }
            }
        }

        protected static abstract class WithExitAdvice extends AdviceVisitor {
            protected boolean doesReturn;
            protected final Label returnHandler;

            protected abstract void onExitAdviceReturn();

            protected abstract void onUserReturn();

            protected WithExitAdvice(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, List<? extends TypeDescription> list, int i, int i2) {
                super(methodVisitor, new StackAwareMethodVisitor(methodVisitor, methodDescription), context, assigner, stackManipulation, typeDescription, methodDescription, forMethodEnter, forMethodExit, list, i, i2);
                this.returnHandler = new Label();
                this.doesReturn = false;
            }

            @Override // net.bytebuddy.asm.Advice.Dispatcher.Bound.SkipHandler
            public void apply(MethodVisitor methodVisitor) {
                if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                    methodVisitor.visitInsn(3);
                } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                    methodVisitor.visitInsn(9);
                } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                    methodVisitor.visitInsn(11);
                } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                    methodVisitor.visitInsn(14);
                } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                    methodVisitor.visitInsn(1);
                }
                methodVisitor.visitJumpInsn(167, this.returnHandler);
                this.doesReturn = true;
            }

            @Override // net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor
            protected void onVisitInsn(int i) {
                switch (i) {
                    case 172:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(54, 21, StackSize.SINGLE));
                        break;
                    case 173:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(55, 22, StackSize.DOUBLE));
                        break;
                    case 174:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(56, 23, StackSize.SINGLE));
                        break;
                    case 175:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(57, 24, StackSize.DOUBLE));
                        break;
                    case 176:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(58, 25, StackSize.SINGLE));
                        break;
                    case 177:
                        ((StackAwareMethodVisitor) this.mv).drainStack();
                        break;
                    default:
                        this.mv.visitInsn(i);
                        return;
                }
                this.mv.visitJumpInsn(167, this.returnHandler);
                this.doesReturn = true;
            }

            @Override // net.bytebuddy.asm.Advice.AdviceVisitor
            protected void onUserEnd() {
                Type type = Type.getType(this.instrumentedMethod.getReturnType().asErasure().getDescriptor());
                this.methodVisitor.visitLabel(this.returnHandler);
                if (this.doesReturn) {
                    this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                    if (!type.equals(Type.VOID_TYPE)) {
                        variable(type.getOpcode(54));
                    }
                }
                onUserReturn();
                this.methodExit.apply();
                onExitAdviceReturn();
                if (type.equals(Type.VOID_TYPE)) {
                    this.methodVisitor.visitInsn(177);
                } else {
                    variable(type.getOpcode(21));
                    this.methodVisitor.visitInsn(type.getOpcode(172));
                }
            }

            protected static class WithoutExceptionHandling extends WithExitAdvice {
                @Override // net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice
                protected void onExitAdviceReturn() {
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor
                protected void onUserPrepare() {
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor
                protected void onUserStart() {
                }

                /* JADX WARN: Illegal instructions before constructor call */
                protected WithoutExceptionHandling(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, int i, int i2) {
                    List listSingletonList;
                    if (methodDescription.getReturnType().represents(Void.TYPE)) {
                        listSingletonList = Collections.emptyList();
                    } else {
                        listSingletonList = Collections.singletonList(methodDescription.getReturnType().asErasure());
                    }
                    super(methodVisitor, context, assigner, stackManipulation, typeDescription, methodDescription, forMethodEnter, forMethodExit, listSingletonList, i, i2);
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice
                protected void onUserReturn() {
                    if (this.doesReturn && this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                        return;
                    }
                    this.stackMapFrameHandler.injectCompletionFrame(this.methodVisitor, false);
                }
            }

            protected static class WithExceptionHandling extends WithExitAdvice {
                private final Label exceptionHandler;
                private final TypeDescription throwable;
                private final Label userStart;

                protected WithExceptionHandling(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, int i, int i2, TypeDescription typeDescription2) {
                    super(methodVisitor, context, assigner, stackManipulation, typeDescription, methodDescription, forMethodEnter, forMethodExit, methodDescription.getReturnType().represents(Void.TYPE) ? Collections.singletonList(TypeDescription.THROWABLE) : Arrays.asList(methodDescription.getReturnType().asErasure(), TypeDescription.THROWABLE), i, i2);
                    this.throwable = typeDescription2;
                    this.userStart = new Label();
                    this.exceptionHandler = new Label();
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor
                protected void onUserPrepare() {
                    this.methodVisitor.visitTryCatchBlock(this.userStart, this.returnHandler, this.exceptionHandler, this.throwable.getInternalName());
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor
                protected void onUserStart() {
                    this.methodVisitor.visitLabel(this.userStart);
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice
                protected void onUserReturn() {
                    Label label = new Label();
                    if (this.doesReturn) {
                        this.methodVisitor.visitInsn(1);
                        variable(58, this.instrumentedMethod.getReturnType().getStackSize().getSize());
                        this.methodVisitor.visitJumpInsn(167, label);
                    }
                    this.methodVisitor.visitLabel(this.exceptionHandler);
                    this.stackMapFrameHandler.injectExceptionFrame(this.methodVisitor);
                    variable(58, this.instrumentedMethod.getReturnType().getStackSize().getSize());
                    storeDefaultReturn();
                    if (this.doesReturn) {
                        this.methodVisitor.visitLabel(label);
                    }
                    this.stackMapFrameHandler.injectCompletionFrame(this.methodVisitor, false);
                }

                @Override // net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice
                protected void onExitAdviceReturn() {
                    variable(25, this.instrumentedMethod.getReturnType().getStackSize().getSize());
                    Label label = new Label();
                    this.methodVisitor.visitJumpInsn(198, label);
                    variable(25, this.instrumentedMethod.getReturnType().getStackSize().getSize());
                    this.methodVisitor.visitInsn(191);
                    this.methodVisitor.visitLabel(label);
                    this.stackMapFrameHandler.injectCompletionFrame(this.methodVisitor, true);
                }

                private void storeDefaultReturn() {
                    if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                        this.methodVisitor.visitInsn(3);
                        variable(54);
                        return;
                    }
                    if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                        this.methodVisitor.visitInsn(9);
                        variable(55);
                        return;
                    }
                    if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                        this.methodVisitor.visitInsn(11);
                        variable(56);
                    } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                        this.methodVisitor.visitInsn(14);
                        variable(57);
                    } else {
                        if (this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                            return;
                        }
                        this.methodVisitor.visitInsn(1);
                        variable(58);
                    }
                }
            }
        }
    }

    protected static class Appender implements ByteCodeAppender {
        private final Advice advice;
        private final ByteCodeAppender delegate;
        private final Implementation.Target implementationTarget;

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
            Advice advice = this.advice;
            Advice advice2 = appender.advice;
            if (advice != null ? !advice.equals(advice2) : advice2 != null) {
                return false;
            }
            Implementation.Target target = this.implementationTarget;
            Implementation.Target target2 = appender.implementationTarget;
            if (target != null ? !target.equals(target2) : target2 != null) {
                return false;
            }
            ByteCodeAppender byteCodeAppender = this.delegate;
            ByteCodeAppender byteCodeAppender2 = appender.delegate;
            return byteCodeAppender != null ? byteCodeAppender.equals(byteCodeAppender2) : byteCodeAppender2 == null;
        }

        public int hashCode() {
            Advice advice = this.advice;
            int iHashCode = advice == null ? 43 : advice.hashCode();
            Implementation.Target target = this.implementationTarget;
            int iHashCode2 = ((iHashCode + 59) * 59) + (target == null ? 43 : target.hashCode());
            ByteCodeAppender byteCodeAppender = this.delegate;
            return (iHashCode2 * 59) + (byteCodeAppender != null ? byteCodeAppender.hashCode() : 43);
        }

        protected Appender(Advice advice, Implementation.Target target, ByteCodeAppender byteCodeAppender) {
            this.advice = advice;
            this.implementationTarget = target;
            this.delegate = byteCodeAppender;
        }

        @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            EmulatingMethodVisitor emulatingMethodVisitor = new EmulatingMethodVisitor(methodVisitor, this.delegate);
            return emulatingMethodVisitor.resolve(this.advice.doWrap(this.implementationTarget.getInstrumentedType(), methodDescription, emulatingMethodVisitor, context, 0, 0), context, methodDescription);
        }

        protected static class EmulatingMethodVisitor extends MethodVisitor {
            private final ByteCodeAppender delegate;
            private int localVariableLength;
            private int stackSize;

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitCode() {
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitEnd() {
            }

            protected EmulatingMethodVisitor(MethodVisitor methodVisitor, ByteCodeAppender byteCodeAppender) {
                super(Opcodes.ASM6, methodVisitor);
                this.delegate = byteCodeAppender;
            }

            protected ByteCodeAppender.Size resolve(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                methodVisitor.visitCode();
                ByteCodeAppender.Size sizeApply = this.delegate.apply(methodVisitor, context, methodDescription);
                methodVisitor.visitMaxs(sizeApply.getOperandStackSize(), sizeApply.getLocalVariableSize());
                methodVisitor.visitEnd();
                return new ByteCodeAppender.Size(this.stackSize, this.localVariableLength);
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitMaxs(int i, int i2) {
                this.stackSize = i;
                this.localVariableLength = i2;
            }
        }
    }

    public static class WithCustomMapping {
        private final Map<Class<? extends Annotation>, OffsetMapping.Factory<?>> offsetMappings;

        protected boolean canEqual(Object obj) {
            return obj instanceof WithCustomMapping;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof WithCustomMapping)) {
                return false;
            }
            WithCustomMapping withCustomMapping = (WithCustomMapping) obj;
            if (!withCustomMapping.canEqual(this)) {
                return false;
            }
            Map<Class<? extends Annotation>, OffsetMapping.Factory<?>> map = this.offsetMappings;
            Map<Class<? extends Annotation>, OffsetMapping.Factory<?>> map2 = withCustomMapping.offsetMappings;
            return map != null ? map.equals(map2) : map2 == null;
        }

        public int hashCode() {
            Map<Class<? extends Annotation>, OffsetMapping.Factory<?>> map = this.offsetMappings;
            return 59 + (map == null ? 43 : map.hashCode());
        }

        protected WithCustomMapping() {
            this(Collections.emptyMap());
        }

        protected WithCustomMapping(Map<Class<? extends Annotation>, OffsetMapping.Factory<?>> map) {
            this.offsetMappings = map;
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Object obj) {
            return bind(OffsetMapping.ForStackManipulation.Factory.of(cls, obj));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Field field) {
            return bind((Class) cls, (FieldDescription) new FieldDescription.ForLoadedField(field));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, FieldDescription fieldDescription) {
            return bind(new OffsetMapping.ForField.Resolved.Factory(cls, fieldDescription));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Method method, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("A parameter cannot be negative: " + i);
            }
            if (method.getParameterTypes().length <= i) {
                throw new IllegalArgumentException(method + " does not declare a parameter with index " + i);
            }
            return bind((Class) cls, (ParameterDescription) new MethodDescription.ForLoadedMethod(method).getParameters().get(i));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Constructor<?> constructor, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("A parameter cannot be negative: " + i);
            }
            if (constructor.getParameterTypes().length <= i) {
                throw new IllegalArgumentException(constructor + " does not declare a parameter with index " + i);
            }
            return bind((Class) cls, (ParameterDescription) new MethodDescription.ForLoadedConstructor(constructor).getParameters().get(i));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, ParameterDescription parameterDescription) {
            return bind(new OffsetMapping.ForArgument.Resolved.Factory(cls, parameterDescription));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Class<?> cls2) {
            return bind((Class) cls, (TypeDescription) new TypeDescription.ForLoadedType(cls2));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, TypeDescription typeDescription) {
            return bind(new OffsetMapping.ForStackManipulation.Factory(cls, typeDescription));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Enum<?> r3) {
            return bind((Class) cls, (EnumerationDescription) new EnumerationDescription.ForLoadedEnumeration(r3));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, EnumerationDescription enumerationDescription) {
            return bind(new OffsetMapping.ForStackManipulation.Factory(cls, enumerationDescription));
        }

        public <T extends Annotation> WithCustomMapping bindSerialized(Class<T> cls, Serializable serializable) {
            return bindSerialized(cls, serializable, serializable.getClass());
        }

        public <T extends Annotation, S extends Serializable> WithCustomMapping bindSerialized(Class<T> cls, S s, Class<? super S> cls2) {
            return bind(OffsetMapping.ForSerializedValue.Factory.of(cls, s, cls2));
        }

        public <T extends Annotation> WithCustomMapping bindProperty(Class<T> cls, String str) {
            return bind(OffsetMapping.ForStackManipulation.OfAnnotationProperty.of(cls, str));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, StackManipulation stackManipulation, java.lang.reflect.Type type) {
            return bind(cls, stackManipulation, TypeDefinition.Sort.describe(type));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, StackManipulation stackManipulation, TypeDescription.Generic generic) {
            return bind(new OffsetMapping.ForStackManipulation.Factory(cls, stackManipulation, generic));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, OffsetMapping offsetMapping) {
            return bind(new OffsetMapping.Factory.Simple(cls, offsetMapping));
        }

        public WithCustomMapping bind(OffsetMapping.Factory<?> factory) {
            HashMap map = new HashMap(this.offsetMappings);
            if (!factory.getAnnotationType().isAnnotation()) {
                throw new IllegalArgumentException("Not an annotation type: " + factory.getAnnotationType());
            }
            if (map.put(factory.getAnnotationType(), factory) != null) {
                throw new IllegalArgumentException("Annotation type already mapped: " + factory.getAnnotationType());
            }
            return new WithCustomMapping(map);
        }

        public Advice to(Class<?> cls) {
            return to(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
        }

        public Advice to(Class<?> cls, ClassFileLocator classFileLocator) {
            return to(new TypeDescription.ForLoadedType(cls), classFileLocator);
        }

        public Advice to(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
            return Advice.to(typeDescription, classFileLocator, new ArrayList(this.offsetMappings.values()));
        }

        public Advice to(Class<?> cls, Class<?> cls2) {
            ClassLoader classLoader = cls.getClassLoader();
            ClassLoader classLoader2 = cls2.getClassLoader();
            return to(cls, cls2, classLoader == classLoader2 ? ClassFileLocator.ForClassLoader.of(classLoader) : new ClassFileLocator.Compound(ClassFileLocator.ForClassLoader.of(classLoader), ClassFileLocator.ForClassLoader.of(classLoader2)));
        }

        public Advice to(Class<?> cls, Class<?> cls2, ClassFileLocator classFileLocator) {
            return to(new TypeDescription.ForLoadedType(cls), new TypeDescription.ForLoadedType(cls2), classFileLocator);
        }

        public Advice to(TypeDescription typeDescription, TypeDescription typeDescription2) {
            return to(typeDescription, typeDescription2, ClassFileLocator.NoOp.INSTANCE);
        }

        public Advice to(TypeDescription typeDescription, TypeDescription typeDescription2, ClassFileLocator classFileLocator) {
            return Advice.to(typeDescription, typeDescription2, classFileLocator, new ArrayList(this.offsetMappings.values()));
        }
    }

    private static class NoExceptionHandler extends Throwable {
        private static final TypeDescription DESCRIPTION = new TypeDescription.ForLoadedType(NoExceptionHandler.class);

        private NoExceptionHandler() {
            throw new UnsupportedOperationException("This marker class is not supposed to be instantiated");
        }
    }

    public static final class OnDefaultValue {
        private OnDefaultValue() {
            throw new UnsupportedOperationException("This marker class is not supposed to be instantiated");
        }
    }

    public static final class OnNonDefaultValue {
        private OnNonDefaultValue() {
            throw new UnsupportedOperationException("This marker class is not supposed to be instantiated");
        }
    }
}
