package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Super {
    Class<?>[] constructorParameters() default {};

    boolean ignoreFinalizer() default true;

    Class<?> proxyType() default void.class;

    boolean serializableProxy() default false;

    Instantiation strategy() default Instantiation.CONSTRUCTOR;

    public enum Instantiation {
        CONSTRUCTOR { // from class: net.bytebuddy.implementation.bind.annotation.Super.Instantiation.1
            @Override // net.bytebuddy.implementation.bind.annotation.Super.Instantiation
            protected StackManipulation proxyFor(TypeDescription typeDescription, Implementation.Target target, AnnotationDescription.Loadable<Super> loadable) {
                return new TypeProxy.ForSuperMethodByConstructor(typeDescription, target, Arrays.asList((Object[]) loadable.getValue(Instantiation.CONSTRUCTOR_PARAMETERS).resolve(TypeDescription[].class)), ((Boolean) loadable.getValue(Instantiation.IGNORE_FINALIZER).resolve(Boolean.class)).booleanValue(), ((Boolean) loadable.getValue(Instantiation.SERIALIZABLE_PROXY).resolve(Boolean.class)).booleanValue());
            }
        },
        UNSAFE { // from class: net.bytebuddy.implementation.bind.annotation.Super.Instantiation.2
            @Override // net.bytebuddy.implementation.bind.annotation.Super.Instantiation
            protected StackManipulation proxyFor(TypeDescription typeDescription, Implementation.Target target, AnnotationDescription.Loadable<Super> loadable) {
                return new TypeProxy.ForSuperMethodByReflectionFactory(typeDescription, target, ((Boolean) loadable.getValue(Instantiation.IGNORE_FINALIZER).resolve(Boolean.class)).booleanValue(), ((Boolean) loadable.getValue(Instantiation.SERIALIZABLE_PROXY).resolve(Boolean.class)).booleanValue());
            }
        };

        private static final MethodDescription.InDefinedShape CONSTRUCTOR_PARAMETERS;
        private static final MethodDescription.InDefinedShape IGNORE_FINALIZER;
        private static final MethodDescription.InDefinedShape SERIALIZABLE_PROXY;

        protected abstract StackManipulation proxyFor(TypeDescription typeDescription, Implementation.Target target, AnnotationDescription.Loadable<Super> loadable);

        static {
            MethodList<MethodDescription.InDefinedShape> declaredMethods = new TypeDescription.ForLoadedType(Super.class).getDeclaredMethods();
            IGNORE_FINALIZER = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("ignoreFinalizer")).getOnly();
            SERIALIZABLE_PROXY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("serializableProxy")).getOnly();
            CONSTRUCTOR_PARAMETERS = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("constructorParameters")).getOnly();
        }
    }

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<Super> {
        INSTANCE;

        private static final MethodDescription.InDefinedShape PROXY_TYPE;
        private static final MethodDescription.InDefinedShape STRATEGY;

        static {
            MethodList<MethodDescription.InDefinedShape> declaredMethods = new TypeDescription.ForLoadedType(Super.class).getDeclaredMethods();
            STRATEGY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("strategy")).getOnly();
            PROXY_TYPE = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("proxyType")).getOnly();
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public Class<Super> getHandledType() {
            return Super.class;
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<Super> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            if (parameterDescription.getType().isPrimitive() || parameterDescription.getType().isArray()) {
                throw new IllegalStateException(parameterDescription + " uses the @Super annotation on an invalid type");
            }
            TypeDescription typeDescriptionResolve = TypeLocator.ForType.of((TypeDescription) loadable.getValue(PROXY_TYPE).resolve(TypeDescription.class)).resolve(target.getInstrumentedType(), parameterDescription.getType());
            if (typeDescriptionResolve.isFinal()) {
                throw new IllegalStateException("Cannot extend final type as @Super proxy: " + typeDescriptionResolve);
            }
            if (methodDescription.isStatic() || !target.getInstrumentedType().isAssignableTo(typeDescriptionResolve)) {
                return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            }
            return new MethodDelegationBinder.ParameterBinding.Anonymous(((Instantiation) ((EnumerationDescription) loadable.getValue(STRATEGY).resolve(EnumerationDescription.class)).load(Instantiation.class)).proxyFor(typeDescriptionResolve, target, loadable));
        }

        protected interface TypeLocator {

            public enum ForInstrumentedType implements TypeLocator {
                INSTANCE;

                @Override // net.bytebuddy.implementation.bind.annotation.Super.Binder.TypeLocator
                public TypeDescription resolve(TypeDescription typeDescription, TypeDescription.Generic generic) {
                    return typeDescription;
                }
            }

            TypeDescription resolve(TypeDescription typeDescription, TypeDescription.Generic generic);

            public enum ForParameterType implements TypeLocator {
                INSTANCE;

                @Override // net.bytebuddy.implementation.bind.annotation.Super.Binder.TypeLocator
                public TypeDescription resolve(TypeDescription typeDescription, TypeDescription.Generic generic) {
                    TypeDescription typeDescriptionAsErasure = generic.asErasure();
                    return typeDescriptionAsErasure.equals(typeDescription) ? typeDescription : typeDescriptionAsErasure;
                }
            }

            public static class ForType implements TypeLocator {
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

                protected static TypeLocator of(TypeDescription typeDescription) {
                    if (typeDescription.represents(Void.TYPE)) {
                        return ForParameterType.INSTANCE;
                    }
                    if (typeDescription.represents(TargetType.class)) {
                        return ForInstrumentedType.INSTANCE;
                    }
                    if (typeDescription.isPrimitive() || typeDescription.isArray()) {
                        throw new IllegalStateException("Cannot assign proxy to " + typeDescription);
                    }
                    return new ForType(typeDescription);
                }

                @Override // net.bytebuddy.implementation.bind.annotation.Super.Binder.TypeLocator
                public TypeDescription resolve(TypeDescription typeDescription, TypeDescription.Generic generic) {
                    if (!this.typeDescription.isAssignableTo(generic.asErasure())) {
                        throw new IllegalStateException("Impossible to assign " + this.typeDescription + " to parameter of type " + generic);
                    }
                    return this.typeDescription;
                }
            }
        }
    }
}
