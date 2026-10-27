package net.bytebuddy.implementation.bind.annotation;

import com.byd.common.bean.WuwInfoBean;
import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.ExceptionMethod;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldProxy {
    Class<?> declaringType() default void.class;

    boolean serializableProxy() default false;

    String value() default "";

    public static class Binder extends TargetMethodAnnotationDrivenBinder.ParameterBinder.ForFieldBinding<FieldProxy> {
        private static final MethodDescription.InDefinedShape DECLARING_TYPE;
        private static final MethodDescription.InDefinedShape FIELD_NAME;
        private static final MethodDescription.InDefinedShape SERIALIZABLE_PROXY;
        private final FieldResolver.Factory fieldResolverFactory;

        protected boolean canEqual(Object obj) {
            return obj instanceof Binder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Binder)) {
                return false;
            }
            Binder binder = (Binder) obj;
            if (!binder.canEqual(this)) {
                return false;
            }
            FieldResolver.Factory factory = this.fieldResolverFactory;
            FieldResolver.Factory factory2 = binder.fieldResolverFactory;
            return factory != null ? factory.equals(factory2) : factory2 == null;
        }

        public int hashCode() {
            FieldResolver.Factory factory = this.fieldResolverFactory;
            return 59 + (factory == null ? 43 : factory.hashCode());
        }

        static {
            MethodList<MethodDescription.InDefinedShape> declaredMethods = new TypeDescription.ForLoadedType(FieldProxy.class).getDeclaredMethods();
            DECLARING_TYPE = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("declaringType")).getOnly();
            FIELD_NAME = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named(WuwInfoBean.ValueKey)).getOnly();
            SERIALIZABLE_PROXY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("serializableProxy")).getOnly();
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldProxy> install(Class<?> cls) {
            return install(new TypeDescription.ForLoadedType(cls));
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldProxy> install(TypeDescription typeDescription) {
            if (!typeDescription.isInterface()) {
                throw new IllegalArgumentException(typeDescription + " is not an interface");
            }
            if (!typeDescription.getInterfaces().isEmpty()) {
                throw new IllegalArgumentException(typeDescription + " must not extend other interfaces");
            }
            if (!typeDescription.isPublic()) {
                throw new IllegalArgumentException(typeDescription + " is not public");
            }
            MethodList methodListFilter = typeDescription.getDeclaredMethods().filter(ElementMatchers.isAbstract());
            if (methodListFilter.size() != 2) {
                throw new IllegalArgumentException(typeDescription + " does not declare exactly two non-abstract methods");
            }
            MethodList methodListFilter2 = methodListFilter.filter(ElementMatchers.isGetter((Class<?>) Object.class));
            if (methodListFilter2.size() != 1) {
                throw new IllegalArgumentException(typeDescription + " does not declare a getter with an Object type");
            }
            MethodList methodListFilter3 = methodListFilter.filter(ElementMatchers.isSetter((Class<?>) Object.class));
            if (methodListFilter3.size() != 1) {
                throw new IllegalArgumentException(typeDescription + " does not declare a setter with an Object type");
            }
            return new Binder(typeDescription, (MethodDescription.InDefinedShape) methodListFilter2.getOnly(), (MethodDescription.InDefinedShape) methodListFilter3.getOnly());
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldProxy> install(Class<?> cls, Class<?> cls2) {
            return install(new TypeDescription.ForLoadedType(cls), new TypeDescription.ForLoadedType(cls2));
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldProxy> install(TypeDescription typeDescription, TypeDescription typeDescription2) {
            MethodDescription.InDefinedShape inDefinedShapeOnlyMethod = onlyMethod(typeDescription);
            if (!inDefinedShapeOnlyMethod.getReturnType().asErasure().represents(Object.class)) {
                throw new IllegalArgumentException(inDefinedShapeOnlyMethod + " must take a single Object-typed parameter");
            }
            if (inDefinedShapeOnlyMethod.getParameters().size() != 0) {
                throw new IllegalArgumentException(inDefinedShapeOnlyMethod + " must not declare parameters");
            }
            MethodDescription.InDefinedShape inDefinedShapeOnlyMethod2 = onlyMethod(typeDescription2);
            if (!inDefinedShapeOnlyMethod2.getReturnType().asErasure().represents(Void.TYPE)) {
                throw new IllegalArgumentException(inDefinedShapeOnlyMethod2 + " must return void");
            }
            if (inDefinedShapeOnlyMethod2.getParameters().size() != 1 || !((ParameterDescription.InDefinedShape) inDefinedShapeOnlyMethod2.getParameters().get(0)).getType().asErasure().represents(Object.class)) {
                throw new IllegalArgumentException(inDefinedShapeOnlyMethod2 + " must declare a single Object-typed parameters");
            }
            return new Binder(inDefinedShapeOnlyMethod, inDefinedShapeOnlyMethod2);
        }

        private static MethodDescription.InDefinedShape onlyMethod(TypeDescription typeDescription) {
            if (!typeDescription.isInterface()) {
                throw new IllegalArgumentException(typeDescription + " is not an interface");
            }
            if (!typeDescription.getInterfaces().isEmpty()) {
                throw new IllegalArgumentException(typeDescription + " must not extend other interfaces");
            }
            if (!typeDescription.isPublic()) {
                throw new IllegalArgumentException(typeDescription + " is not public");
            }
            MethodList methodListFilter = typeDescription.getDeclaredMethods().filter(ElementMatchers.isAbstract());
            if (methodListFilter.size() != 1) {
                throw new IllegalArgumentException(typeDescription + " must declare exactly one abstract method");
            }
            return (MethodDescription.InDefinedShape) methodListFilter.getOnly();
        }

        protected Binder(MethodDescription.InDefinedShape inDefinedShape, MethodDescription.InDefinedShape inDefinedShape2) {
            this(new FieldResolver.Factory.Simplex(inDefinedShape, inDefinedShape2));
        }

        protected Binder(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodDescription.InDefinedShape inDefinedShape2) {
            this(new FieldResolver.Factory.Duplex(typeDescription, inDefinedShape, inDefinedShape2));
        }

        protected Binder(FieldResolver.Factory factory) {
            this.fieldResolverFactory = factory;
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public Class<FieldProxy> getHandledType() {
            return FieldProxy.class;
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder.ForFieldBinding
        protected String fieldName(AnnotationDescription.Loadable<FieldProxy> loadable) {
            return (String) loadable.getValue(FIELD_NAME).resolve(String.class);
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder.ForFieldBinding
        protected TypeDescription declaringType(AnnotationDescription.Loadable<FieldProxy> loadable) {
            return (TypeDescription) loadable.getValue(DECLARING_TYPE).resolve(TypeDescription.class);
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder.ForFieldBinding
        protected MethodDelegationBinder.ParameterBinding<?> bind(FieldDescription fieldDescription, AnnotationDescription.Loadable<FieldProxy> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner) {
            FieldResolver fieldResolverResolve = this.fieldResolverFactory.resolve(parameterDescription.getType().asErasure(), fieldDescription);
            if (fieldResolverResolve.isResolved()) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new AccessorProxy(fieldDescription, target.getInstrumentedType(), fieldResolverResolve, assigner, ((Boolean) loadable.getValue(SERIALIZABLE_PROXY).resolve(Boolean.class)).booleanValue()));
            }
            return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
        }

        protected interface FieldResolver {
            DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory);

            TypeDescription getProxyType();

            boolean isResolved();

            public interface Factory {
                FieldResolver resolve(TypeDescription typeDescription, FieldDescription fieldDescription);

                public static class Duplex implements Factory {
                    private final MethodDescription.InDefinedShape getterMethod;
                    private final TypeDescription proxyType;
                    private final MethodDescription.InDefinedShape setterMethod;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Duplex;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Duplex)) {
                            return false;
                        }
                        Duplex duplex = (Duplex) obj;
                        if (!duplex.canEqual(this)) {
                            return false;
                        }
                        TypeDescription typeDescription = this.proxyType;
                        TypeDescription typeDescription2 = duplex.proxyType;
                        if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                            return false;
                        }
                        MethodDescription.InDefinedShape inDefinedShape = this.getterMethod;
                        MethodDescription.InDefinedShape inDefinedShape2 = duplex.getterMethod;
                        if (inDefinedShape != null ? !inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 != null) {
                            return false;
                        }
                        MethodDescription.InDefinedShape inDefinedShape3 = this.setterMethod;
                        MethodDescription.InDefinedShape inDefinedShape4 = duplex.setterMethod;
                        return inDefinedShape3 != null ? inDefinedShape3.equals(inDefinedShape4) : inDefinedShape4 == null;
                    }

                    public int hashCode() {
                        TypeDescription typeDescription = this.proxyType;
                        int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                        MethodDescription.InDefinedShape inDefinedShape = this.getterMethod;
                        int iHashCode2 = ((iHashCode + 59) * 59) + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
                        MethodDescription.InDefinedShape inDefinedShape2 = this.setterMethod;
                        return (iHashCode2 * 59) + (inDefinedShape2 != null ? inDefinedShape2.hashCode() : 43);
                    }

                    protected Duplex(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodDescription.InDefinedShape inDefinedShape2) {
                        this.proxyType = typeDescription;
                        this.getterMethod = inDefinedShape;
                        this.setterMethod = inDefinedShape2;
                    }

                    @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver.Factory
                    public FieldResolver resolve(TypeDescription typeDescription, FieldDescription fieldDescription) {
                        if (typeDescription.equals(this.proxyType)) {
                            return new ForGetterSetterPair(this.proxyType, this.getterMethod, this.setterMethod);
                        }
                        throw new IllegalStateException("Cannot use @FieldProxy on a non-installed type");
                    }
                }

                public static class Simplex implements Factory {
                    private final MethodDescription.InDefinedShape getterMethod;
                    private final MethodDescription.InDefinedShape setterMethod;

                    protected boolean canEqual(Object obj) {
                        return obj instanceof Simplex;
                    }

                    public boolean equals(Object obj) {
                        if (obj == this) {
                            return true;
                        }
                        if (!(obj instanceof Simplex)) {
                            return false;
                        }
                        Simplex simplex = (Simplex) obj;
                        if (!simplex.canEqual(this)) {
                            return false;
                        }
                        MethodDescription.InDefinedShape inDefinedShape = this.getterMethod;
                        MethodDescription.InDefinedShape inDefinedShape2 = simplex.getterMethod;
                        if (inDefinedShape != null ? !inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 != null) {
                            return false;
                        }
                        MethodDescription.InDefinedShape inDefinedShape3 = this.setterMethod;
                        MethodDescription.InDefinedShape inDefinedShape4 = simplex.setterMethod;
                        return inDefinedShape3 != null ? inDefinedShape3.equals(inDefinedShape4) : inDefinedShape4 == null;
                    }

                    public int hashCode() {
                        MethodDescription.InDefinedShape inDefinedShape = this.getterMethod;
                        int iHashCode = inDefinedShape == null ? 43 : inDefinedShape.hashCode();
                        MethodDescription.InDefinedShape inDefinedShape2 = this.setterMethod;
                        return ((iHashCode + 59) * 59) + (inDefinedShape2 != null ? inDefinedShape2.hashCode() : 43);
                    }

                    protected Simplex(MethodDescription.InDefinedShape inDefinedShape, MethodDescription.InDefinedShape inDefinedShape2) {
                        this.getterMethod = inDefinedShape;
                        this.setterMethod = inDefinedShape2;
                    }

                    @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver.Factory
                    public FieldResolver resolve(TypeDescription typeDescription, FieldDescription fieldDescription) {
                        if (typeDescription.equals(this.getterMethod.getDeclaringType())) {
                            return new ForGetter(this.getterMethod);
                        }
                        if (typeDescription.equals(this.setterMethod.getDeclaringType())) {
                            return fieldDescription.isFinal() ? Unresolved.INSTANCE : new ForSetter(this.setterMethod);
                        }
                        throw new IllegalStateException("Cannot use @FieldProxy on a non-installed type");
                    }
                }
            }

            public enum Unresolved implements FieldResolver {
                INSTANCE;

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public boolean isResolved() {
                    return false;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public TypeDescription getProxyType() {
                    throw new IllegalStateException("Cannot read type for unresolved field resolver");
                }

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory) {
                    throw new IllegalStateException("Cannot apply unresolved field resolver");
                }
            }

            public static class ForGetter implements FieldResolver {
                private final MethodDescription.InDefinedShape getterMethod;

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public boolean isResolved() {
                    return true;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForGetter;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForGetter)) {
                        return false;
                    }
                    ForGetter forGetter = (ForGetter) obj;
                    if (!forGetter.canEqual(this)) {
                        return false;
                    }
                    MethodDescription.InDefinedShape inDefinedShape = this.getterMethod;
                    MethodDescription.InDefinedShape inDefinedShape2 = forGetter.getterMethod;
                    return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
                }

                public int hashCode() {
                    MethodDescription.InDefinedShape inDefinedShape = this.getterMethod;
                    return 59 + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
                }

                protected ForGetter(MethodDescription.InDefinedShape inDefinedShape) {
                    this.getterMethod = inDefinedShape;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public TypeDescription getProxyType() {
                    return this.getterMethod.getDeclaringType();
                }

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory) {
                    return builder.method(ElementMatchers.definedMethod(ElementMatchers.is(this.getterMethod))).intercept(new FieldGetter(fieldDescription, assigner, methodAccessorFactory));
                }
            }

            public static class ForSetter implements FieldResolver {
                private final MethodDescription.InDefinedShape setterMethod;

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public boolean isResolved() {
                    return true;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForSetter;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForSetter)) {
                        return false;
                    }
                    ForSetter forSetter = (ForSetter) obj;
                    if (!forSetter.canEqual(this)) {
                        return false;
                    }
                    MethodDescription.InDefinedShape inDefinedShape = this.setterMethod;
                    MethodDescription.InDefinedShape inDefinedShape2 = forSetter.setterMethod;
                    return inDefinedShape != null ? inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 == null;
                }

                public int hashCode() {
                    MethodDescription.InDefinedShape inDefinedShape = this.setterMethod;
                    return 59 + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
                }

                protected ForSetter(MethodDescription.InDefinedShape inDefinedShape) {
                    this.setterMethod = inDefinedShape;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public TypeDescription getProxyType() {
                    return this.setterMethod.getDeclaringType();
                }

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory) {
                    return builder.method(ElementMatchers.is(this.setterMethod)).intercept(new FieldSetter(fieldDescription, assigner, methodAccessorFactory));
                }
            }

            public static class ForGetterSetterPair implements FieldResolver {
                private final MethodDescription.InDefinedShape getterMethod;
                private final TypeDescription proxyType;
                private final MethodDescription.InDefinedShape setterMethod;

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public boolean isResolved() {
                    return true;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof ForGetterSetterPair;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ForGetterSetterPair)) {
                        return false;
                    }
                    ForGetterSetterPair forGetterSetterPair = (ForGetterSetterPair) obj;
                    if (!forGetterSetterPair.canEqual(this)) {
                        return false;
                    }
                    TypeDescription proxyType = getProxyType();
                    TypeDescription proxyType2 = forGetterSetterPair.getProxyType();
                    if (proxyType != null ? !proxyType.equals(proxyType2) : proxyType2 != null) {
                        return false;
                    }
                    MethodDescription.InDefinedShape inDefinedShape = this.getterMethod;
                    MethodDescription.InDefinedShape inDefinedShape2 = forGetterSetterPair.getterMethod;
                    if (inDefinedShape != null ? !inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 != null) {
                        return false;
                    }
                    MethodDescription.InDefinedShape inDefinedShape3 = this.setterMethod;
                    MethodDescription.InDefinedShape inDefinedShape4 = forGetterSetterPair.setterMethod;
                    return inDefinedShape3 != null ? inDefinedShape3.equals(inDefinedShape4) : inDefinedShape4 == null;
                }

                public int hashCode() {
                    TypeDescription proxyType = getProxyType();
                    int iHashCode = proxyType == null ? 43 : proxyType.hashCode();
                    MethodDescription.InDefinedShape inDefinedShape = this.getterMethod;
                    int iHashCode2 = ((iHashCode + 59) * 59) + (inDefinedShape == null ? 43 : inDefinedShape.hashCode());
                    MethodDescription.InDefinedShape inDefinedShape2 = this.setterMethod;
                    return (iHashCode2 * 59) + (inDefinedShape2 != null ? inDefinedShape2.hashCode() : 43);
                }

                protected ForGetterSetterPair(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodDescription.InDefinedShape inDefinedShape2) {
                    this.proxyType = typeDescription;
                    this.getterMethod = inDefinedShape;
                    this.setterMethod = inDefinedShape2;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public TypeDescription getProxyType() {
                    return this.proxyType;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.FieldProxy.Binder.FieldResolver
                public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory) {
                    return builder.method(ElementMatchers.is(this.getterMethod)).intercept(new FieldGetter(fieldDescription, assigner, methodAccessorFactory)).method(ElementMatchers.is(this.setterMethod)).intercept(fieldDescription.isFinal() ? ExceptionMethod.throwing((Class<? extends Throwable>) UnsupportedOperationException.class, "Cannot set final field " + fieldDescription) : new FieldSetter(fieldDescription, assigner, methodAccessorFactory));
                }
            }
        }

        protected enum StaticFieldConstructor implements Implementation {
            INSTANCE;

            private final MethodDescription objectTypeDefaultConstructor = (MethodDescription) TypeDescription.OBJECT.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly();

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            StaticFieldConstructor() {
            }

            @Override // net.bytebuddy.implementation.Implementation
            public ByteCodeAppender appender(Implementation.Target target) {
                return new ByteCodeAppender.Simple(MethodVariableAccess.loadThis(), MethodInvocation.invoke(this.objectTypeDefaultConstructor), MethodReturn.VOID);
            }
        }

        protected static class InstanceFieldConstructor implements Implementation {
            private final TypeDescription instrumentedType;

            protected boolean canEqual(Object obj) {
                return obj instanceof InstanceFieldConstructor;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof InstanceFieldConstructor)) {
                    return false;
                }
                InstanceFieldConstructor instanceFieldConstructor = (InstanceFieldConstructor) obj;
                if (!instanceFieldConstructor.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.instrumentedType;
                TypeDescription typeDescription2 = instanceFieldConstructor.instrumentedType;
                return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.instrumentedType;
                return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
            }

            protected InstanceFieldConstructor(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType.withField(new FieldDescription.Token("instance", 18, this.instrumentedType.asGenericType()));
            }

            @Override // net.bytebuddy.implementation.Implementation
            public ByteCodeAppender appender(Implementation.Target target) {
                return new Appender(target);
            }

            protected static class Appender implements ByteCodeAppender {
                private final FieldDescription fieldDescription;

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
                    FieldDescription fieldDescription = this.fieldDescription;
                    FieldDescription fieldDescription2 = appender.fieldDescription;
                    return fieldDescription != null ? fieldDescription.equals(fieldDescription2) : fieldDescription2 == null;
                }

                public int hashCode() {
                    FieldDescription fieldDescription = this.fieldDescription;
                    return 59 + (fieldDescription == null ? 43 : fieldDescription.hashCode());
                }

                protected Appender(Implementation.Target target) {
                    this.fieldDescription = (FieldDescription) target.getInstrumentedType().getDeclaredFields().filter(ElementMatchers.named("instance")).getOnly();
                }

                @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodInvocation.invoke(StaticFieldConstructor.INSTANCE.objectTypeDefaultConstructor), MethodVariableAccess.allArgumentsOf(methodDescription.asDefined()).prependThisReference(), FieldAccess.forField(this.fieldDescription).write(), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }
        }

        protected static class FieldGetter implements Implementation {
            private final Assigner assigner;
            private final FieldDescription fieldDescription;
            private final MethodAccessorFactory methodAccessorFactory;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof FieldGetter;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof FieldGetter)) {
                    return false;
                }
                FieldGetter fieldGetter = (FieldGetter) obj;
                if (!fieldGetter.canEqual(this)) {
                    return false;
                }
                FieldDescription fieldDescription = this.fieldDescription;
                FieldDescription fieldDescription2 = fieldGetter.fieldDescription;
                if (fieldDescription != null ? !fieldDescription.equals(fieldDescription2) : fieldDescription2 != null) {
                    return false;
                }
                Assigner assigner = this.assigner;
                Assigner assigner2 = fieldGetter.assigner;
                if (assigner != null ? !assigner.equals(assigner2) : assigner2 != null) {
                    return false;
                }
                MethodAccessorFactory methodAccessorFactory = this.methodAccessorFactory;
                MethodAccessorFactory methodAccessorFactory2 = fieldGetter.methodAccessorFactory;
                return methodAccessorFactory != null ? methodAccessorFactory.equals(methodAccessorFactory2) : methodAccessorFactory2 == null;
            }

            public int hashCode() {
                FieldDescription fieldDescription = this.fieldDescription;
                int iHashCode = fieldDescription == null ? 43 : fieldDescription.hashCode();
                Assigner assigner = this.assigner;
                int iHashCode2 = ((iHashCode + 59) * 59) + (assigner == null ? 43 : assigner.hashCode());
                MethodAccessorFactory methodAccessorFactory = this.methodAccessorFactory;
                return (iHashCode2 * 59) + (methodAccessorFactory != null ? methodAccessorFactory.hashCode() : 43);
            }

            protected FieldGetter(FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory) {
                this.fieldDescription = fieldDescription;
                this.assigner = assigner;
                this.methodAccessorFactory = methodAccessorFactory;
            }

            @Override // net.bytebuddy.implementation.Implementation
            public ByteCodeAppender appender(Implementation.Target target) {
                return new Appender(target);
            }

            protected class Appender implements ByteCodeAppender {
                private final TypeDescription typeDescription;

                protected Appender(Implementation.Target target) {
                    this.typeDescription = target.getInstrumentedType();
                }

                @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                    MethodDescription.InDefinedShape inDefinedShapeRegisterGetterFor = FieldGetter.this.methodAccessorFactory.registerGetterFor(FieldGetter.this.fieldDescription, MethodAccessorFactory.AccessType.DEFAULT);
                    StackManipulation[] stackManipulationArr = new StackManipulation[4];
                    stackManipulationArr[0] = FieldGetter.this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) this.typeDescription.getDeclaredFields().filter(ElementMatchers.named("instance")).getOnly()).read());
                    stackManipulationArr[1] = MethodInvocation.invoke((MethodDescription) inDefinedShapeRegisterGetterFor);
                    stackManipulationArr[2] = FieldGetter.this.assigner.assign(inDefinedShapeRegisterGetterFor.getReturnType(), methodDescription.getReturnType(), Assigner.Typing.DYNAMIC);
                    stackManipulationArr[3] = MethodReturn.of(methodDescription.getReturnType().asErasure());
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }

                private FieldGetter getOuter() {
                    return FieldGetter.this;
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Appender appender = (Appender) obj;
                    return this.typeDescription.equals(appender.typeDescription) && FieldGetter.this.equals(appender.getOuter());
                }

                public int hashCode() {
                    return this.typeDescription.hashCode() + (FieldGetter.this.hashCode() * 31);
                }
            }
        }

        protected static class FieldSetter implements Implementation {
            private final Assigner assigner;
            private final FieldDescription fieldDescription;
            private final MethodAccessorFactory methodAccessorFactory;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof FieldSetter;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof FieldSetter)) {
                    return false;
                }
                FieldSetter fieldSetter = (FieldSetter) obj;
                if (!fieldSetter.canEqual(this)) {
                    return false;
                }
                FieldDescription fieldDescription = this.fieldDescription;
                FieldDescription fieldDescription2 = fieldSetter.fieldDescription;
                if (fieldDescription != null ? !fieldDescription.equals(fieldDescription2) : fieldDescription2 != null) {
                    return false;
                }
                Assigner assigner = this.assigner;
                Assigner assigner2 = fieldSetter.assigner;
                if (assigner != null ? !assigner.equals(assigner2) : assigner2 != null) {
                    return false;
                }
                MethodAccessorFactory methodAccessorFactory = this.methodAccessorFactory;
                MethodAccessorFactory methodAccessorFactory2 = fieldSetter.methodAccessorFactory;
                return methodAccessorFactory != null ? methodAccessorFactory.equals(methodAccessorFactory2) : methodAccessorFactory2 == null;
            }

            public int hashCode() {
                FieldDescription fieldDescription = this.fieldDescription;
                int iHashCode = fieldDescription == null ? 43 : fieldDescription.hashCode();
                Assigner assigner = this.assigner;
                int iHashCode2 = ((iHashCode + 59) * 59) + (assigner == null ? 43 : assigner.hashCode());
                MethodAccessorFactory methodAccessorFactory = this.methodAccessorFactory;
                return (iHashCode2 * 59) + (methodAccessorFactory != null ? methodAccessorFactory.hashCode() : 43);
            }

            protected FieldSetter(FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory) {
                this.fieldDescription = fieldDescription;
                this.assigner = assigner;
                this.methodAccessorFactory = methodAccessorFactory;
            }

            @Override // net.bytebuddy.implementation.Implementation
            public ByteCodeAppender appender(Implementation.Target target) {
                return new Appender(target);
            }

            protected class Appender implements ByteCodeAppender {
                private final TypeDescription typeDescription;

                protected Appender(Implementation.Target target) {
                    this.typeDescription = target.getInstrumentedType();
                }

                @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                    TypeDescription.Generic type = ((ParameterDescription) methodDescription.getParameters().get(0)).getType();
                    MethodDescription.InDefinedShape inDefinedShapeRegisterSetterFor = FieldSetter.this.methodAccessorFactory.registerSetterFor(FieldSetter.this.fieldDescription, MethodAccessorFactory.AccessType.DEFAULT);
                    StackManipulation[] stackManipulationArr = new StackManipulation[5];
                    stackManipulationArr[0] = FieldSetter.this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) this.typeDescription.getDeclaredFields().filter(ElementMatchers.named("instance")).getOnly()).read());
                    stackManipulationArr[1] = MethodVariableAccess.of(type).loadFrom(1);
                    stackManipulationArr[2] = FieldSetter.this.assigner.assign(type, ((ParameterDescription) inDefinedShapeRegisterSetterFor.getParameters().get(0)).getType(), Assigner.Typing.DYNAMIC);
                    stackManipulationArr[3] = MethodInvocation.invoke((MethodDescription) inDefinedShapeRegisterSetterFor);
                    stackManipulationArr[4] = MethodReturn.VOID;
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }

                private FieldSetter getOuter() {
                    return FieldSetter.this;
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Appender appender = (Appender) obj;
                    return this.typeDescription.equals(appender.typeDescription) && FieldSetter.this.equals(appender.getOuter());
                }

                public int hashCode() {
                    return this.typeDescription.hashCode() + (FieldSetter.this.hashCode() * 31);
                }
            }
        }

        protected class AccessorProxy implements AuxiliaryType, StackManipulation {
            protected static final String FIELD_NAME = "instance";
            private final Assigner assigner;
            private final FieldDescription fieldDescription;
            private final FieldResolver fieldResolver;
            private final TypeDescription instrumentedType;
            private final boolean serializableProxy;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected AccessorProxy(FieldDescription fieldDescription, TypeDescription typeDescription, FieldResolver fieldResolver, Assigner assigner, boolean z) {
                this.fieldDescription = fieldDescription;
                this.instrumentedType = typeDescription;
                this.fieldResolver = fieldResolver;
                this.assigner = assigner;
                this.serializableProxy = z;
            }

            @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
            public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
                List listSingletonList;
                FieldResolver fieldResolver = this.fieldResolver;
                DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial<?> initialDefineConstructor = new ByteBuddy(classFileVersion).subclass(this.fieldResolver.getProxyType(), ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement(this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).defineConstructor(new ModifierContributor.ForMethod[0]);
                if (this.fieldDescription.isStatic()) {
                    listSingletonList = Collections.emptyList();
                } else {
                    listSingletonList = Collections.singletonList(this.instrumentedType);
                }
                return fieldResolver.apply(initialDefineConstructor.withParameters((Collection<? extends TypeDefinition>) listSingletonList).intercept(this.fieldDescription.isStatic() ? StaticFieldConstructor.INSTANCE : new InstanceFieldConstructor(this.instrumentedType)), this.fieldDescription, this.assigner, methodAccessorFactory).make();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                TypeDescription typeDescriptionRegister = context.register(this);
                StackManipulation[] stackManipulationArr = new StackManipulation[4];
                stackManipulationArr[0] = TypeCreation.of(typeDescriptionRegister);
                stackManipulationArr[1] = Duplication.SINGLE;
                stackManipulationArr[2] = this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                stackManipulationArr[3] = MethodInvocation.invoke((MethodDescription.InDefinedShape) typeDescriptionRegister.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly());
                return new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context);
            }

            private Binder getOuter() {
                return Binder.this;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                AccessorProxy accessorProxy = (AccessorProxy) obj;
                return this.serializableProxy == accessorProxy.serializableProxy && this.fieldDescription.equals(accessorProxy.fieldDescription) && this.instrumentedType.equals(accessorProxy.instrumentedType) && this.fieldResolver.equals(accessorProxy.fieldResolver) && this.assigner.equals(accessorProxy.assigner) && Binder.this.equals(accessorProxy.getOuter());
            }

            public int hashCode() {
                return (((((((((this.fieldDescription.hashCode() * 31) + Binder.this.hashCode()) * 31) + this.instrumentedType.hashCode()) * 31) + this.fieldResolver.hashCode()) * 31) + this.assigner.hashCode()) * 31) + (this.serializableProxy ? 1 : 0);
            }
        }
    }
}
