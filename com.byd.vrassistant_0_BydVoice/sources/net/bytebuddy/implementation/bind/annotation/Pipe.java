package net.bytebuddy.implementation.bind.annotation;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
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
public @interface Pipe {
    boolean serializableProxy() default false;

    public static class Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<Pipe> {
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

        protected Binder(MethodDescription methodDescription) {
            this.forwardingMethod = methodDescription;
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<Pipe> install(Class<?> cls) {
            return install(new TypeDescription.ForLoadedType(cls));
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<Pipe> install(TypeDescription typeDescription) {
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
            if (methodDescription.getParameters().size() == 1 && ((ParameterDescription) methodDescription.getParameters().getOnly()).getType().asErasure().represents(Object.class)) {
                return methodDescription;
            }
            throw new IllegalArgumentException(methodDescription + " does not take a single Object-typed argument");
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public Class<Pipe> getHandledType() {
            return Pipe.class;
        }

        @Override // net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder.ParameterBinder
        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<Pipe> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            if (!parameterDescription.getType().asErasure().equals(this.forwardingMethod.getDeclaringType())) {
                throw new IllegalStateException("Illegal use of @Pipe for " + parameterDescription + " which was installed for " + this.forwardingMethod.getDeclaringType());
            }
            if (methodDescription.isStatic()) {
                return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            }
            return new MethodDelegationBinder.ParameterBinding.Anonymous(new Redirection(this.forwardingMethod.getDeclaringType().asErasure(), methodDescription, assigner, ((Pipe) loadable.loadSilent()).serializableProxy()));
        }

        protected static class Redirection implements AuxiliaryType, StackManipulation {
            private static final String FIELD_NAME_PREFIX = "argument";
            private final Assigner assigner;
            private final TypeDescription forwardingType;
            private final boolean serializableProxy;
            private final MethodDescription sourceMethod;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Redirection;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Redirection)) {
                    return false;
                }
                Redirection redirection = (Redirection) obj;
                if (!redirection.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.forwardingType;
                TypeDescription typeDescription2 = redirection.forwardingType;
                if (typeDescription != null ? !typeDescription.equals(typeDescription2) : typeDescription2 != null) {
                    return false;
                }
                MethodDescription methodDescription = this.sourceMethod;
                MethodDescription methodDescription2 = redirection.sourceMethod;
                if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                    return false;
                }
                Assigner assigner = this.assigner;
                Assigner assigner2 = redirection.assigner;
                if (assigner != null ? assigner.equals(assigner2) : assigner2 == null) {
                    return this.serializableProxy == redirection.serializableProxy;
                }
                return false;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.forwardingType;
                int iHashCode = typeDescription == null ? 43 : typeDescription.hashCode();
                MethodDescription methodDescription = this.sourceMethod;
                int iHashCode2 = ((iHashCode + 59) * 59) + (methodDescription == null ? 43 : methodDescription.hashCode());
                Assigner assigner = this.assigner;
                return (((iHashCode2 * 59) + (assigner != null ? assigner.hashCode() : 43)) * 59) + (this.serializableProxy ? 79 : 97);
            }

            protected Redirection(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, boolean z) {
                this.forwardingType = typeDescription;
                this.sourceMethod = methodDescription;
                this.assigner = assigner;
                this.serializableProxy = z;
            }

            private static LinkedHashMap<String, TypeDescription> extractFields(MethodDescription methodDescription) {
                TypeList typeListAsErasures = methodDescription.getParameters().asTypeList().asErasures();
                LinkedHashMap<String, TypeDescription> linkedHashMap = new LinkedHashMap<>();
                Iterator it = typeListAsErasures.iterator();
                int i = 0;
                while (it.hasNext()) {
                    linkedHashMap.put(fieldName(i), (TypeDescription) it.next());
                    i++;
                }
                return linkedHashMap;
            }

            private static String fieldName(int i) {
                return String.format("%s%d", FIELD_NAME_PREFIX, Integer.valueOf(i));
            }

            @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
            public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
                LinkedHashMap<String, TypeDescription> linkedHashMapExtractFields = extractFields(this.sourceMethod);
                DynamicType.Builder builderIntercept = new ByteBuddy(classFileVersion).subclass(this.forwardingType, ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement(this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).method(ElementMatchers.isAbstract().and(ElementMatchers.isDeclaredBy(this.forwardingType))).intercept(new MethodCall(this.sourceMethod, this.assigner)).defineConstructor(new ModifierContributor.ForMethod[0]).withParameters(linkedHashMapExtractFields.values()).intercept(ConstructorCall.INSTANCE);
                for (Map.Entry<String, TypeDescription> entry : linkedHashMapExtractFields.entrySet()) {
                    builderIntercept = builderIntercept.defineField(entry.getKey(), entry.getValue(), Visibility.PRIVATE);
                }
                return builderIntercept.make();
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                TypeDescription typeDescriptionRegister = context.register(this);
                return new StackManipulation.Compound(TypeCreation.of(typeDescriptionRegister), Duplication.SINGLE, MethodVariableAccess.allArgumentsOf(this.sourceMethod), MethodInvocation.invoke((MethodDescription.InDefinedShape) typeDescriptionRegister.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly())).apply(methodVisitor, context);
            }

            protected enum ConstructorCall implements Implementation {
                INSTANCE;

                private final MethodDescription.InDefinedShape objectTypeDefaultConstructor = (MethodDescription.InDefinedShape) TypeDescription.OBJECT.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly();

                @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                ConstructorCall() {
                }

                @Override // net.bytebuddy.implementation.Implementation
                public ByteCodeAppender appender(Implementation.Target target) {
                    return new Appender(target.getInstrumentedType());
                }

                private static class Appender implements ByteCodeAppender {
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
                        FieldList<FieldDescription.InDefinedShape> declaredFields = this.instrumentedType.getDeclaredFields();
                        StackManipulation[] stackManipulationArr = new StackManipulation[declaredFields.size()];
                        Iterator it = declaredFields.iterator();
                        int i = 0;
                        while (it.hasNext()) {
                            stackManipulationArr[i] = new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodVariableAccess.load((ParameterDescription) methodDescription.getParameters().get(i)), FieldAccess.forField((FieldDescription) it.next()).write());
                            i++;
                        }
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodInvocation.invoke(ConstructorCall.INSTANCE.objectTypeDefaultConstructor), new StackManipulation.Compound(stackManipulationArr), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }

            protected static class MethodCall implements Implementation {
                private final Assigner assigner;
                private final MethodDescription redirectedMethod;

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
                    MethodDescription methodDescription = this.redirectedMethod;
                    MethodDescription methodDescription2 = methodCall.redirectedMethod;
                    if (methodDescription != null ? !methodDescription.equals(methodDescription2) : methodDescription2 != null) {
                        return false;
                    }
                    Assigner assigner = this.assigner;
                    Assigner assigner2 = methodCall.assigner;
                    return assigner != null ? assigner.equals(assigner2) : assigner2 == null;
                }

                public int hashCode() {
                    MethodDescription methodDescription = this.redirectedMethod;
                    int iHashCode = methodDescription == null ? 43 : methodDescription.hashCode();
                    Assigner assigner = this.assigner;
                    return ((iHashCode + 59) * 59) + (assigner != null ? assigner.hashCode() : 43);
                }

                private MethodCall(MethodDescription methodDescription, Assigner assigner) {
                    this.redirectedMethod = methodDescription;
                    this.assigner = assigner;
                }

                @Override // net.bytebuddy.implementation.Implementation
                public ByteCodeAppender appender(Implementation.Target target) {
                    if (!this.redirectedMethod.isAccessibleTo(target.getInstrumentedType())) {
                        throw new IllegalStateException("Cannot invoke " + this.redirectedMethod + " from outside of class via @Pipe proxy");
                    }
                    return new Appender(target.getInstrumentedType());
                }

                private class Appender implements ByteCodeAppender {
                    private final TypeDescription instrumentedType;

                    private Appender(TypeDescription typeDescription) {
                        this.instrumentedType = typeDescription;
                    }

                    @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        StackManipulation stackManipulationLoadFrom = MethodVariableAccess.of(this.instrumentedType).loadFrom(0);
                        FieldList<FieldDescription.InDefinedShape> declaredFields = this.instrumentedType.getDeclaredFields();
                        StackManipulation[] stackManipulationArr = new StackManipulation[declaredFields.size()];
                        Iterator it = declaredFields.iterator();
                        int i = 0;
                        while (it.hasNext()) {
                            stackManipulationArr[i] = new StackManipulation.Compound(stackManipulationLoadFrom, FieldAccess.forField((FieldDescription) it.next()).read());
                            i++;
                        }
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.REFERENCE.loadFrom(1), MethodCall.this.assigner.assign(TypeDescription.Generic.OBJECT, MethodCall.this.redirectedMethod.getDeclaringType().asGenericType(), Assigner.Typing.DYNAMIC), new StackManipulation.Compound(stackManipulationArr), MethodInvocation.invoke(MethodCall.this.redirectedMethod), MethodCall.this.assigner.assign(MethodCall.this.redirectedMethod.getReturnType(), methodDescription.getReturnType(), Assigner.Typing.DYNAMIC), MethodReturn.REFERENCE).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }

                    private MethodCall getMethodCall() {
                        return MethodCall.this;
                    }

                    public boolean equals(Object obj) {
                        if (this != obj) {
                            if (obj != null && getClass() == obj.getClass()) {
                                Appender appender = (Appender) obj;
                                if (!this.instrumentedType.equals(appender.instrumentedType) || !MethodCall.this.equals(appender.getMethodCall())) {
                                }
                            }
                            return false;
                        }
                        return true;
                    }

                    public int hashCode() {
                        return (MethodCall.this.hashCode() * 31) + this.instrumentedType.hashCode();
                    }
                }
            }
        }
    }
}
