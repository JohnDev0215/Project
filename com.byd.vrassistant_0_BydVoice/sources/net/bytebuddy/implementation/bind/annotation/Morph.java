package net.bytebuddy.implementation.bind.annotation;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
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
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
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
public @interface Morph {
    boolean defaultMethod() default false;

    Class<?> defaultTarget() default void.class;

    boolean serializableProxy() default false;

    public static class Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<Morph> {
        private static final MethodDescription.InDefinedShape DEFAULT_METHOD;
        private static final MethodDescription.InDefinedShape DEFAULT_TARGET;
        private static final MethodDescription.InDefinedShape SERIALIZABLE_PROXY;
        private final MethodDescription forwardingMethod;

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
            MethodDescription methodDescription = this.forwardingMethod;
            MethodDescription methodDescription2 = binder.forwardingMethod;
            return methodDescription != null ? methodDescription.equals(methodDescription2) : methodDescription2 == null;
        }

        public int hashCode() {
            MethodDescription methodDescription = this.forwardingMethod;
            return 59 + (methodDescription == null ? 43 : methodDescription.hashCode());
        }

        static {
            MethodList<MethodDescription.InDefinedShape> declaredMethods = new TypeDescription.ForLoadedType(Morph.class).getDeclaredMethods();
            SERIALIZABLE_PROXY = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("serializableProxy")).getOnly();
            DEFAULT_METHOD = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("defaultMethod")).getOnly();
            DEFAULT_TARGET = (MethodDescription.InDefinedShape) declaredMethods.filter(ElementMatchers.named("defaultTarget")).getOnly();
        }

        protected Binder(MethodDescription methodDescription) {
            this.forwardingMethod = methodDescription;
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<Morph> install(Class<?> cls) {
            return install(new TypeDescription.ForLoadedType(cls));
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<Morph> install(TypeDescription typeDescription) {
            return new Binder(onlyMethod(typeDescription));
        }

        private static MethodDescription onlyMethod(TypeDescription typeDescription) {
            if (!typeDescription.isInterface()) {
                throw new IllegalArgumentException(typeDescription + " is not an interface");
            }
            if (!typeDescription.getInterfaces().isEmpty()) {
                throw new IllegalArgumentException(typeDescription + " must not extend other interfaces");
            }
            if (!typeDescription.isPublic()) {
                throw new IllegalArgumentException(typeDescription + " is mot public");
            }
            MethodList methodListFilter = typeDescription.getDeclaredMethods().filter(ElementMatchers.isAbstract());
            if (methodListFilter.size() != 1) {
                throw new IllegalArgumentException(typeDescription + " must declare exactly one abstract method");
            }
            MethodDescription methodDescription = (MethodDescription) methodListFilter.getOnly();
            if (!methodDescription.getReturnType().asErasure().represents(Object.class)) {
                throw new IllegalArgumentException(methodDescription + " does not return an Object-type");
            }
            if (methodDescription.getParameters().size() == 1 && ((ParameterDescription) methodDescription.getParameters().get(0)).getType().asErasure().represents(Object[].class)) {
                return methodDescription;
            }
            throw new IllegalArgumentException(methodDescription + " does not take a single argument of type Object[]");
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public Class<Morph> getHandledType() {
            return Morph.class;
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<Morph> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            Implementation.SpecialMethodInvocation specialMethodInvocationResolve;
            if (!parameterDescription.getType().asErasure().equals(this.forwardingMethod.getDeclaringType())) {
                throw new IllegalStateException("Illegal use of @Morph for " + parameterDescription + " which was installed for " + this.forwardingMethod.getDeclaringType());
            }
            TypeDescription typeDescription = (TypeDescription) loadable.getValue(DEFAULT_TARGET).resolve(TypeDescription.class);
            if (typeDescription.represents(Void.TYPE) && !((Boolean) loadable.getValue(DEFAULT_METHOD).resolve(Boolean.class)).booleanValue()) {
                specialMethodInvocationResolve = target.invokeSuper(methodDescription.asSignatureToken());
            } else {
                specialMethodInvocationResolve = (typeDescription.represents(Void.TYPE) ? DefaultMethodLocator.Implicit.INSTANCE : new DefaultMethodLocator.Explicit(typeDescription)).resolve(target, methodDescription);
            }
            Implementation.SpecialMethodInvocation specialMethodInvocation = specialMethodInvocationResolve;
            return specialMethodInvocation.isValid() ? new MethodDelegationBinder.ParameterBinding.Anonymous(new RedirectionProxy(this.forwardingMethod.getDeclaringType().asErasure(), target.getInstrumentedType(), specialMethodInvocation, assigner, ((Boolean) loadable.getValue(SERIALIZABLE_PROXY).resolve(Boolean.class)).booleanValue())) : MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
        }

        protected interface DefaultMethodLocator {
            Implementation.SpecialMethodInvocation resolve(Implementation.Target target, MethodDescription methodDescription);

            public enum Implicit implements DefaultMethodLocator {
                INSTANCE;

                @Override // net.bytebuddy.implementation.bind.annotation.Morph.Binder.DefaultMethodLocator
                public Implementation.SpecialMethodInvocation resolve(Implementation.Target target, MethodDescription methodDescription) {
                    return target.invokeDefault(methodDescription.asSignatureToken());
                }
            }

            public static class Explicit implements DefaultMethodLocator {
                private final TypeDescription typeDescription;

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
                    TypeDescription typeDescription = this.typeDescription;
                    TypeDescription typeDescription2 = explicit.typeDescription;
                    return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
                }

                public int hashCode() {
                    TypeDescription typeDescription = this.typeDescription;
                    return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
                }

                public Explicit(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                @Override // net.bytebuddy.implementation.bind.annotation.Morph.Binder.DefaultMethodLocator
                public Implementation.SpecialMethodInvocation resolve(Implementation.Target target, MethodDescription methodDescription) {
                    if (!this.typeDescription.isInterface()) {
                        throw new IllegalStateException(methodDescription + " method carries default method call parameter on non-interface type");
                    }
                    return target.invokeDefault(methodDescription.asSignatureToken(), this.typeDescription);
                }
            }
        }

        protected static class RedirectionProxy implements AuxiliaryType, StackManipulation {
            protected static final String FIELD_NAME = "target";
            private final Assigner assigner;
            private final TypeDescription instrumentedType;
            private final TypeDescription morphingType;
            private final boolean serializableProxy;
            private final Implementation.SpecialMethodInvocation specialMethodInvocation;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof RedirectionProxy;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof RedirectionProxy)) {
                    return false;
                }
                RedirectionProxy redirectionProxy = (RedirectionProxy) obj;
                if (!redirectionProxy.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.morphingType;
                TypeDescription typeDescription2 = redirectionProxy.morphingType;
                if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                    return false;
                }
                TypeDescription typeDescription3 = this.instrumentedType;
                TypeDescription typeDescription4 = redirectionProxy.instrumentedType;
                if (typeDescription3 != null ? !typeDescription3.equals(typeDescription4) : typeDescription4 != null) {
                    return false;
                }
                Implementation.SpecialMethodInvocation specialMethodInvocation = this.specialMethodInvocation;
                Implementation.SpecialMethodInvocation specialMethodInvocation2 = redirectionProxy.specialMethodInvocation;
                if (specialMethodInvocation != null ? !specialMethodInvocation.equals(specialMethodInvocation2) : specialMethodInvocation2 != null) {
                    return false;
                }
                Assigner assigner = this.assigner;
                Assigner assigner2 = redirectionProxy.assigner;
                if (assigner != null ? assigner.equals(assigner2) : assigner2 == null) {
                    return this.serializableProxy == redirectionProxy.serializableProxy;
                }
                return false;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.morphingType;
                int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                TypeDescription typeDescription2 = this.instrumentedType;
                int iHashCode2 = ((iHashCode + 59) * 59) + (typeDescription2 == null ? 43 : typeDescription2.hashCode());
                Implementation.SpecialMethodInvocation specialMethodInvocation = this.specialMethodInvocation;
                int iHashCode3 = (iHashCode2 * 59) + (specialMethodInvocation == null ? 43 : specialMethodInvocation.hashCode());
                Assigner assigner = this.assigner;
                return (((iHashCode3 * 59) + (assigner != null ? assigner.hashCode() : 43)) * 59) + (this.serializableProxy ? 79 : 97);
            }

            protected RedirectionProxy(TypeDescription typeDescription, TypeDescription typeDescription2, Implementation.SpecialMethodInvocation specialMethodInvocation, Assigner assigner, boolean z) {
                this.morphingType = typeDescription;
                this.instrumentedType = typeDescription2;
                this.specialMethodInvocation = specialMethodInvocation;
                this.assigner = assigner;
                this.serializableProxy = z;
            }

            @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
            public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
                List listSingletonList;
                DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial<?> initialDefineConstructor = new ByteBuddy(classFileVersion).subclass(this.morphingType, ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement(this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).defineConstructor(new ModifierContributor.ForMethod[0]);
                if (this.specialMethodInvocation.getMethodDescription().isStatic()) {
                    listSingletonList = Collections.emptyList();
                } else {
                    listSingletonList = Collections.singletonList(this.instrumentedType);
                }
                return initialDefineConstructor.withParameters((Collection<? extends TypeDefinition>) listSingletonList).intercept(this.specialMethodInvocation.getMethodDescription().isStatic() ? StaticFieldConstructor.INSTANCE : new InstanceFieldConstructor(this.instrumentedType)).method(ElementMatchers.isAbstract().and(ElementMatchers.isDeclaredBy(this.morphingType))).intercept(new MethodCall(methodAccessorFactory.registerAccessorFor(this.specialMethodInvocation, MethodAccessorFactory.AccessType.DEFAULT), this.assigner)).make();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                TypeDescription typeDescriptionRegister = context.register(this);
                StackManipulation[] stackManipulationArr = new StackManipulation[4];
                stackManipulationArr[0] = TypeCreation.of(typeDescriptionRegister);
                stackManipulationArr[1] = Duplication.SINGLE;
                stackManipulationArr[2] = this.specialMethodInvocation.getMethodDescription().isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                stackManipulationArr[3] = MethodInvocation.invoke((MethodDescription.InDefinedShape) typeDescriptionRegister.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly());
                return new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context);
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
                    return instrumentedType.withField(new FieldDescription.Token("target", 18, this.instrumentedType.asGenericType()));
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
                        this.fieldDescription = (FieldDescription) target.getInstrumentedType().getDeclaredFields().filter(ElementMatchers.named("target")).getOnly();
                    }

                    @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodInvocation.invoke(StaticFieldConstructor.INSTANCE.objectTypeDefaultConstructor), MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), FieldAccess.forField(this.fieldDescription).write(), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }

            protected static class MethodCall implements Implementation {
                private final MethodDescription accessorMethod;
                private final Assigner assigner;

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected boolean canEqual(Object obj) {
                    return obj instanceof MethodCall;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof MethodCall)) {
                        return false;
                    }
                    MethodCall methodCall = (MethodCall) obj;
                    if (!methodCall.canEqual(this)) {
                        return false;
                    }
                    MethodDescription methodDescription = this.accessorMethod;
                    MethodDescription methodDescription2 = methodCall.accessorMethod;
                    if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                        return false;
                    }
                    Assigner assigner = this.assigner;
                    Assigner assigner2 = methodCall.assigner;
                    return assigner != null ? assigner.equals(assigner2) : assigner2 == null;
                }

                public int hashCode() {
                    MethodDescription methodDescription = this.accessorMethod;
                    int iHashCode = methodDescription == null ? 43 : methodDescription.hashCode();
                    Assigner assigner = this.assigner;
                    return ((iHashCode + 59) * 59) + (assigner != null ? assigner.hashCode() : 43);
                }

                protected MethodCall(MethodDescription methodDescription, Assigner assigner) {
                    this.accessorMethod = methodDescription;
                    this.assigner = assigner;
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
                        StackManipulation stackManipulationLoadFrom = MethodVariableAccess.REFERENCE.loadFrom(1);
                        StackManipulation[] stackManipulationArr = new StackManipulation[MethodCall.this.accessorMethod.getParameters().size()];
                        Iterator it = MethodCall.this.accessorMethod.getParameters().asTypeList().iterator();
                        int i = 0;
                        while (it.hasNext()) {
                            stackManipulationArr[i] = new StackManipulation.Compound(stackManipulationLoadFrom, IntegerConstant.forValue(i), ArrayAccess.REFERENCE.load(), MethodCall.this.assigner.assign(TypeDescription.Generic.OBJECT, (TypeDescription.Generic) it.next(), Assigner.Typing.DYNAMIC));
                            i++;
                        }
                        StackManipulation[] stackManipulationArr2 = new StackManipulation[5];
                        stackManipulationArr2[0] = MethodCall.this.accessorMethod.isStatic() ? StackManipulation.Trivial.INSTANCE : new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) this.typeDescription.getDeclaredFields().filter(ElementMatchers.named("target")).getOnly()).read());
                        stackManipulationArr2[1] = new StackManipulation.Compound(stackManipulationArr);
                        stackManipulationArr2[2] = MethodInvocation.invoke(MethodCall.this.accessorMethod);
                        stackManipulationArr2[3] = MethodCall.this.assigner.assign(MethodCall.this.accessorMethod.getReturnType(), methodDescription.getReturnType(), Assigner.Typing.DYNAMIC);
                        stackManipulationArr2[4] = MethodReturn.REFERENCE;
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr2).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }

                    private MethodCall getMethodCall() {
                        return MethodCall.this;
                    }

                    public boolean equals(Object obj) {
                        if (this != obj) {
                            if (obj != null && getClass() == obj.getClass()) {
                                Appender appender = (Appender) obj;
                                if (!MethodCall.this.equals(appender.getMethodCall()) || !this.typeDescription.equals(appender.typeDescription)) {
                                }
                            }
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return this.typeDescription.hashCode() + (MethodCall.this.hashCode() * 31);
                    }
                }
            }
        }
    }
}
