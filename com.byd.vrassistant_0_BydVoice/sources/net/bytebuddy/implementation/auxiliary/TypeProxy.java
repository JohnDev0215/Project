package net.bytebuddy.implementation.auxiliary;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.Throw;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.matcher.ElementMatchers;

/* JADX INFO: loaded from: classes3.dex */
public class TypeProxy implements AuxiliaryType {
    public static final String INSTANCE_FIELD = "target";
    public static final String REFLECTION_METHOD = "make";
    private final boolean ignoreFinalizer;
    private final Implementation.Target implementationTarget;
    private final InvocationFactory invocationFactory;
    private final TypeDescription proxiedType;
    private final boolean serializableProxy;

    public interface InvocationFactory {

        public enum Default implements InvocationFactory {
            SUPER_METHOD { // from class: net.bytebuddy.implementation.auxiliary.TypeProxy.InvocationFactory.Default.1
                @Override // net.bytebuddy.implementation.auxiliary.TypeProxy.InvocationFactory
                public Implementation.SpecialMethodInvocation invoke(Implementation.Target target, TypeDescription typeDescription, MethodDescription methodDescription) {
                    return target.invokeDominant(methodDescription.asSignatureToken());
                }
            },
            DEFAULT_METHOD { // from class: net.bytebuddy.implementation.auxiliary.TypeProxy.InvocationFactory.Default.2
                @Override // net.bytebuddy.implementation.auxiliary.TypeProxy.InvocationFactory
                public Implementation.SpecialMethodInvocation invoke(Implementation.Target target, TypeDescription typeDescription, MethodDescription methodDescription) {
                    return target.invokeDefault(methodDescription.asSignatureToken(), typeDescription);
                }
            }
        }

        Implementation.SpecialMethodInvocation invoke(Implementation.Target target, TypeDescription typeDescription, MethodDescription methodDescription);
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TypeProxy;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TypeProxy)) {
            return false;
        }
        TypeProxy typeProxy = (TypeProxy) obj;
        if (!typeProxy.canEqual(this)) {
            return false;
        }
        TypeDescription typeDescription = this.proxiedType;
        TypeDescription typeDescription2 = typeProxy.proxiedType;
        if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
            return false;
        }
        Implementation.Target target = this.implementationTarget;
        Implementation.Target target2 = typeProxy.implementationTarget;
        if (target != null ? !target.equals(target2) : target2 != null) {
            return false;
        }
        InvocationFactory invocationFactory = this.invocationFactory;
        InvocationFactory invocationFactory2 = typeProxy.invocationFactory;
        if (invocationFactory != null ? invocationFactory.equals(invocationFactory2) : invocationFactory2 == null) {
            return this.ignoreFinalizer == typeProxy.ignoreFinalizer && this.serializableProxy == typeProxy.serializableProxy;
        }
        return false;
    }

    public int hashCode() {
        TypeDescription typeDescription = this.proxiedType;
        int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
        Implementation.Target target = this.implementationTarget;
        int iHashCode2 = ((iHashCode + 59) * 59) + (target == null ? 43 : target.hashCode());
        InvocationFactory invocationFactory = this.invocationFactory;
        return (((((iHashCode2 * 59) + (invocationFactory != null ? invocationFactory.hashCode() : 43)) * 59) + (this.ignoreFinalizer ? 79 : 97)) * 59) + (this.serializableProxy ? 79 : 97);
    }

    public TypeProxy(TypeDescription typeDescription, Implementation.Target target, InvocationFactory invocationFactory, boolean z, boolean z2) {
        this.proxiedType = typeDescription;
        this.implementationTarget = target;
        this.invocationFactory = invocationFactory;
        this.ignoreFinalizer = z;
        this.serializableProxy = z2;
    }

    @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
    public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
        return new ByteBuddy(classFileVersion).ignore(this.ignoreFinalizer ? ElementMatchers.isFinalizer() : ElementMatchers.none()).subclass(this.proxiedType).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement(this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).method(ElementMatchers.any()).intercept(new MethodCall(methodAccessorFactory)).defineMethod(REFLECTION_METHOD, TargetType.class, Ownership.STATIC).intercept(SilentConstruction.INSTANCE).make();
    }

    protected enum AbstractMethodErrorThrow implements StackManipulation {
        INSTANCE;

        private final StackManipulation implementation;

        AbstractMethodErrorThrow() {
            TypeDescription.ForLoadedType forLoadedType = new TypeDescription.ForLoadedType(AbstractMethodError.class);
            this.implementation = new StackManipulation.Compound(TypeCreation.of(forLoadedType), Duplication.SINGLE, MethodInvocation.invoke((MethodDescription) forLoadedType.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(0))).getOnly()), Throw.INSTANCE);
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return this.implementation.isValid();
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return this.implementation.apply(methodVisitor, context);
        }
    }

    protected enum SilentConstruction implements Implementation {
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
            public static final String GET_DECLARED_CONSTRUCTOR_METHOD_DESCRIPTOR = "([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;";
            public static final String GET_DECLARED_CONSTRUCTOR_METHOD_NAME = "getDeclaredConstructor";
            public static final String GET_REFLECTION_FACTORY_METHOD_DESCRIPTOR = "()Lsun/reflect/ReflectionFactory;";
            public static final String GET_REFLECTION_FACTORY_METHOD_NAME = "getReflectionFactory";
            public static final String JAVA_LANG_CLASS_INTERNAL_NAME = "java/lang/Class";
            public static final String JAVA_LANG_CONSTRUCTOR_INTERNAL_NAME = "java/lang/reflect/Constructor";
            public static final String JAVA_LANG_OBJECT_DESCRIPTOR = "Ljava/lang/Object;";
            public static final String JAVA_LANG_OBJECT_INTERNAL_NAME = "java/lang/Object";
            public static final String NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_DESCRIPTOR = "(Ljava/lang/Class;Ljava/lang/reflect/Constructor;)Ljava/lang/reflect/Constructor;";
            public static final String NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_NAME = "newConstructorForSerialization";
            public static final String NEW_INSTANCE_METHOD_DESCRIPTOR = "([Ljava/lang/Object;)Ljava/lang/Object;";
            public static final String NEW_INSTANCE_METHOD_NAME = "newInstance";
            public static final String REFLECTION_FACTORY_INTERNAL_NAME = "sun/reflect/ReflectionFactory";
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

            private Appender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                methodVisitor.visitMethodInsn(184, REFLECTION_FACTORY_INTERNAL_NAME, GET_REFLECTION_FACTORY_METHOD_NAME, GET_REFLECTION_FACTORY_METHOD_DESCRIPTOR, false);
                methodVisitor.visitLdcInsn(Type.getType(this.instrumentedType.getDescriptor()));
                methodVisitor.visitLdcInsn(Type.getType(JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(3);
                methodVisitor.visitTypeInsn(189, JAVA_LANG_CLASS_INTERNAL_NAME);
                methodVisitor.visitMethodInsn(182, JAVA_LANG_CLASS_INTERNAL_NAME, GET_DECLARED_CONSTRUCTOR_METHOD_NAME, GET_DECLARED_CONSTRUCTOR_METHOD_DESCRIPTOR, false);
                methodVisitor.visitMethodInsn(182, REFLECTION_FACTORY_INTERNAL_NAME, NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_NAME, NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_DESCRIPTOR, false);
                methodVisitor.visitInsn(3);
                methodVisitor.visitTypeInsn(189, JAVA_LANG_OBJECT_INTERNAL_NAME);
                methodVisitor.visitMethodInsn(182, JAVA_LANG_CONSTRUCTOR_INTERNAL_NAME, NEW_INSTANCE_METHOD_NAME, NEW_INSTANCE_METHOD_DESCRIPTOR, false);
                methodVisitor.visitTypeInsn(192, this.instrumentedType.getInternalName());
                methodVisitor.visitInsn(176);
                return new ByteCodeAppender.Size(4, 0);
            }
        }
    }

    public static class ForSuperMethodByConstructor implements StackManipulation {
        private final List<TypeDescription> constructorParameters;
        private final boolean ignoreFinalizer;
        private final Implementation.Target implementationTarget;
        private final TypeDescription proxiedType;
        private final boolean serializableProxy;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForSuperMethodByConstructor;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForSuperMethodByConstructor)) {
                return false;
            }
            ForSuperMethodByConstructor forSuperMethodByConstructor = (ForSuperMethodByConstructor) obj;
            if (!forSuperMethodByConstructor.canEqual(this)) {
                return false;
            }
            TypeDescription typeDescription = this.proxiedType;
            TypeDescription typeDescription2 = forSuperMethodByConstructor.proxiedType;
            if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                return false;
            }
            Implementation.Target target = this.implementationTarget;
            Implementation.Target target2 = forSuperMethodByConstructor.implementationTarget;
            if (target != null ? !target.equals(target2) : target2 != null) {
                return false;
            }
            List<TypeDescription> list = this.constructorParameters;
            List<TypeDescription> list2 = forSuperMethodByConstructor.constructorParameters;
            if (list != null ? list.equals(list2) : list2 == null) {
                return this.ignoreFinalizer == forSuperMethodByConstructor.ignoreFinalizer && this.serializableProxy == forSuperMethodByConstructor.serializableProxy;
            }
            return false;
        }

        public int hashCode() {
            TypeDescription typeDescription = this.proxiedType;
            int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
            Implementation.Target target = this.implementationTarget;
            int iHashCode2 = ((iHashCode + 59) * 59) + (target == null ? 43 : target.hashCode());
            List<TypeDescription> list = this.constructorParameters;
            return (((((iHashCode2 * 59) + (list != null ? list.hashCode() : 43)) * 59) + (this.ignoreFinalizer ? 79 : 97)) * 59) + (this.serializableProxy ? 79 : 97);
        }

        public ForSuperMethodByConstructor(TypeDescription typeDescription, Implementation.Target target, List<TypeDescription> list, boolean z, boolean z2) {
            this.proxiedType = typeDescription;
            this.implementationTarget = target;
            this.constructorParameters = list;
            this.ignoreFinalizer = z;
            this.serializableProxy = z2;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription typeDescriptionRegister = context.register(new TypeProxy(this.proxiedType, this.implementationTarget, InvocationFactory.Default.SUPER_METHOD, this.ignoreFinalizer, this.serializableProxy));
            StackManipulation[] stackManipulationArr = new StackManipulation[this.constructorParameters.size()];
            Iterator<TypeDescription> it = this.constructorParameters.iterator();
            int i = 0;
            while (it.hasNext()) {
                stackManipulationArr[i] = DefaultValue.of(it.next());
                i++;
            }
            return new StackManipulation.Compound(TypeCreation.of(typeDescriptionRegister), Duplication.SINGLE, new StackManipulation.Compound(stackManipulationArr), MethodInvocation.invoke((MethodDescription.InDefinedShape) typeDescriptionRegister.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(this.constructorParameters))).getOnly()), Duplication.SINGLE, MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) typeDescriptionRegister.getDeclaredFields().filter(ElementMatchers.named(TypeProxy.INSTANCE_FIELD)).getOnly()).write()).apply(methodVisitor, context);
        }
    }

    public static class ForSuperMethodByReflectionFactory implements StackManipulation {
        private final boolean ignoreFinalizer;
        private final Implementation.Target implementationTarget;
        private final TypeDescription proxiedType;
        private final boolean serializableProxy;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForSuperMethodByReflectionFactory;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForSuperMethodByReflectionFactory)) {
                return false;
            }
            ForSuperMethodByReflectionFactory forSuperMethodByReflectionFactory = (ForSuperMethodByReflectionFactory) obj;
            if (!forSuperMethodByReflectionFactory.canEqual(this)) {
                return false;
            }
            TypeDescription typeDescription = this.proxiedType;
            TypeDescription typeDescription2 = forSuperMethodByReflectionFactory.proxiedType;
            if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                return false;
            }
            Implementation.Target target = this.implementationTarget;
            Implementation.Target target2 = forSuperMethodByReflectionFactory.implementationTarget;
            if (target != null ? target.equals(target2) : target2 == null) {
                return this.ignoreFinalizer == forSuperMethodByReflectionFactory.ignoreFinalizer && this.serializableProxy == forSuperMethodByReflectionFactory.serializableProxy;
            }
            return false;
        }

        public int hashCode() {
            TypeDescription typeDescription = this.proxiedType;
            int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
            Implementation.Target target = this.implementationTarget;
            return ((((((iHashCode + 59) * 59) + (target != null ? target.hashCode() : 43)) * 59) + (this.ignoreFinalizer ? 79 : 97)) * 59) + (this.serializableProxy ? 79 : 97);
        }

        public ForSuperMethodByReflectionFactory(TypeDescription typeDescription, Implementation.Target target, boolean z, boolean z2) {
            this.proxiedType = typeDescription;
            this.implementationTarget = target;
            this.ignoreFinalizer = z;
            this.serializableProxy = z2;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription typeDescriptionRegister = context.register(new TypeProxy(this.proxiedType, this.implementationTarget, InvocationFactory.Default.SUPER_METHOD, this.ignoreFinalizer, this.serializableProxy));
            return new StackManipulation.Compound(MethodInvocation.invoke((MethodDescription.InDefinedShape) typeDescriptionRegister.getDeclaredMethods().filter(ElementMatchers.named(TypeProxy.REFLECTION_METHOD).and(ElementMatchers.takesArguments(0))).getOnly()), Duplication.SINGLE, MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) typeDescriptionRegister.getDeclaredFields().filter(ElementMatchers.named(TypeProxy.INSTANCE_FIELD)).getOnly()).write()).apply(methodVisitor, context);
        }
    }

    public static class ForDefaultMethod implements StackManipulation {
        private final Implementation.Target implementationTarget;
        private final TypeDescription proxiedType;
        private final boolean serializableProxy;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ForDefaultMethod;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ForDefaultMethod)) {
                return false;
            }
            ForDefaultMethod forDefaultMethod = (ForDefaultMethod) obj;
            if (!forDefaultMethod.canEqual(this)) {
                return false;
            }
            TypeDescription typeDescription = this.proxiedType;
            TypeDescription typeDescription2 = forDefaultMethod.proxiedType;
            if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                return false;
            }
            Implementation.Target target = this.implementationTarget;
            Implementation.Target target2 = forDefaultMethod.implementationTarget;
            if (target != null ? target.equals(target2) : target2 == null) {
                return this.serializableProxy == forDefaultMethod.serializableProxy;
            }
            return false;
        }

        public int hashCode() {
            TypeDescription typeDescription = this.proxiedType;
            int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
            Implementation.Target target = this.implementationTarget;
            return ((((iHashCode + 59) * 59) + (target != null ? target.hashCode() : 43)) * 59) + (this.serializableProxy ? 79 : 97);
        }

        public ForDefaultMethod(TypeDescription typeDescription, Implementation.Target target, boolean z) {
            this.proxiedType = typeDescription;
            this.implementationTarget = target;
            this.serializableProxy = z;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription typeDescriptionRegister = context.register(new TypeProxy(this.proxiedType, this.implementationTarget, InvocationFactory.Default.DEFAULT_METHOD, true, this.serializableProxy));
            return new StackManipulation.Compound(TypeCreation.of(typeDescriptionRegister), Duplication.SINGLE, MethodInvocation.invoke((MethodDescription.InDefinedShape) typeDescriptionRegister.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly()), Duplication.SINGLE, MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) typeDescriptionRegister.getDeclaredFields().filter(ElementMatchers.named(TypeProxy.INSTANCE_FIELD)).getOnly()).write()).apply(methodVisitor, context);
        }
    }

    protected class MethodCall implements Implementation {
        private final MethodAccessorFactory methodAccessorFactory;

        protected MethodCall(MethodAccessorFactory methodAccessorFactory) {
            this.methodAccessorFactory = methodAccessorFactory;
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType.withField(new FieldDescription.Token(TypeProxy.INSTANCE_FIELD, 4096, TypeProxy.this.implementationTarget.getInstrumentedType().asGenericType()));
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType());
        }

        private TypeProxy getTypeProxy() {
            return TypeProxy.this;
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj != null && getClass() == obj.getClass()) {
                    MethodCall methodCall = (MethodCall) obj;
                    if (!this.methodAccessorFactory.equals(methodCall.methodAccessorFactory) || !TypeProxy.this.equals(methodCall.getTypeProxy())) {
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (TypeProxy.this.hashCode() * 31) + this.methodAccessorFactory.hashCode();
        }

        protected class Appender implements ByteCodeAppender {
            private final StackManipulation fieldLoadingInstruction;

            protected Appender(TypeDescription typeDescription) {
                this.fieldLoadingInstruction = FieldAccess.forField((FieldDescription.InDefinedShape) typeDescription.getDeclaredFields().filter(ElementMatchers.named(TypeProxy.INSTANCE_FIELD)).getOnly()).read();
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                Implementation.SpecialMethodInvocation specialMethodInvocationInvoke = TypeProxy.this.invocationFactory.invoke(TypeProxy.this.implementationTarget, TypeProxy.this.proxiedType, methodDescription);
                return new ByteCodeAppender.Size((specialMethodInvocationInvoke.isValid() ? new AccessorMethodInvocation(methodDescription, specialMethodInvocationInvoke) : AbstractMethodErrorThrow.INSTANCE).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }

            private MethodCall getMethodCall() {
                return MethodCall.this;
            }

            public boolean equals(Object obj) {
                if (this != obj) {
                    if (obj != null && getClass() == obj.getClass()) {
                        Appender appender = (Appender) obj;
                        if (!this.fieldLoadingInstruction.equals(appender.fieldLoadingInstruction) || !MethodCall.this.equals(appender.getMethodCall())) {
                        }
                    }
                    return false;
                }
                return true;
            }

            public int hashCode() {
                return (MethodCall.this.hashCode() * 31) + this.fieldLoadingInstruction.hashCode();
            }

            protected class AccessorMethodInvocation implements StackManipulation {
                private final MethodDescription instrumentedMethod;
                private final Implementation.SpecialMethodInvocation specialMethodInvocation;

                protected AccessorMethodInvocation(MethodDescription methodDescription, Implementation.SpecialMethodInvocation specialMethodInvocation) {
                    this.instrumentedMethod = methodDescription;
                    this.specialMethodInvocation = specialMethodInvocation;
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public boolean isValid() {
                    return this.specialMethodInvocation.isValid();
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                    MethodDescription.InDefinedShape inDefinedShapeRegisterAccessorFor = MethodCall.this.methodAccessorFactory.registerAccessorFor(this.specialMethodInvocation, MethodAccessorFactory.AccessType.DEFAULT);
                    return new StackManipulation.Compound(MethodVariableAccess.loadThis(), Appender.this.fieldLoadingInstruction, MethodVariableAccess.allArgumentsOf(this.instrumentedMethod).asBridgeOf(inDefinedShapeRegisterAccessorFor), MethodInvocation.invoke(inDefinedShapeRegisterAccessorFor), MethodReturn.of(this.instrumentedMethod.getReturnType())).apply(methodVisitor, context);
                }

                private Appender getAppender() {
                    return Appender.this;
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    AccessorMethodInvocation accessorMethodInvocation = (AccessorMethodInvocation) obj;
                    return Appender.this.equals(accessorMethodInvocation.getAppender()) && this.instrumentedMethod.equals(accessorMethodInvocation.instrumentedMethod) && this.specialMethodInvocation.equals(accessorMethodInvocation.specialMethodInvocation);
                }

                public int hashCode() {
                    return (((Appender.this.hashCode() * 31) + this.instrumentedMethod.hashCode()) * 31) + this.specialMethodInvocation.hashCode();
                }
            }
        }
    }
}
