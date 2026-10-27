package net.bytebuddy.implementation.auxiliary;

import androidx.core.app.NotificationCompat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
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
public class MethodCallProxy implements AuxiliaryType {
    private static final String FIELD_NAME_PREFIX = "argument";
    private final Assigner assigner;
    private final boolean serializableProxy;
    private final Implementation.SpecialMethodInvocation specialMethodInvocation;

    protected boolean canEqual(Object obj) {
        return obj instanceof MethodCallProxy;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MethodCallProxy)) {
            return false;
        }
        MethodCallProxy methodCallProxy = (MethodCallProxy) obj;
        if (!methodCallProxy.canEqual(this)) {
            return false;
        }
        Implementation.SpecialMethodInvocation specialMethodInvocation = this.specialMethodInvocation;
        Implementation.SpecialMethodInvocation specialMethodInvocation2 = methodCallProxy.specialMethodInvocation;
        if (specialMethodInvocation != null ? !specialMethodInvocation.equals(specialMethodInvocation2) : specialMethodInvocation2 != null) {
            return false;
        }
        if (this.serializableProxy != methodCallProxy.serializableProxy) {
            return false;
        }
        Assigner assigner = this.assigner;
        Assigner assigner2 = methodCallProxy.assigner;
        return assigner != null ? assigner.equals(assigner2) : assigner2 == null;
    }

    public int hashCode() {
        Implementation.SpecialMethodInvocation specialMethodInvocation = this.specialMethodInvocation;
        int iHashCode = (((specialMethodInvocation == null ? 43 : specialMethodInvocation.hashCode()) + 59) * 59) + (this.serializableProxy ? 79 : 97);
        Assigner assigner = this.assigner;
        return (iHashCode * 59) + (assigner != null ? assigner.hashCode() : 43);
    }

    public MethodCallProxy(Implementation.SpecialMethodInvocation specialMethodInvocation, boolean z) {
        this(specialMethodInvocation, z, Assigner.DEFAULT);
    }

    public MethodCallProxy(Implementation.SpecialMethodInvocation specialMethodInvocation, boolean z, Assigner assigner) {
        this.specialMethodInvocation = specialMethodInvocation;
        this.serializableProxy = z;
        this.assigner = assigner;
    }

    private static LinkedHashMap<String, TypeDescription> extractFields(MethodDescription methodDescription) {
        LinkedHashMap<String, TypeDescription> linkedHashMap = new LinkedHashMap<>();
        int i = 0;
        if (!methodDescription.isStatic()) {
            linkedHashMap.put(fieldName(0), methodDescription.getDeclaringType().asErasure());
            i = 1;
        }
        Iterator it = methodDescription.getParameters().iterator();
        while (it.hasNext()) {
            linkedHashMap.put(fieldName(i), ((ParameterDescription) it.next()).getType().asErasure());
            i++;
        }
        return linkedHashMap;
    }

    private static String fieldName(int i) {
        return String.format("%s%d", FIELD_NAME_PREFIX, Integer.valueOf(i));
    }

    @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
    public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
        MethodDescription.InDefinedShape inDefinedShapeRegisterAccessorFor = methodAccessorFactory.registerAccessorFor(this.specialMethodInvocation, MethodAccessorFactory.AccessType.DEFAULT);
        LinkedHashMap<String, TypeDescription> linkedHashMapExtractFields = extractFields(inDefinedShapeRegisterAccessorFor);
        DynamicType.Builder builderIntercept = new ByteBuddy(classFileVersion).with(PrecomputedMethodGraph.INSTANCE).subclass(Object.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement(Runnable.class, Callable.class).intercept(new MethodCall(inDefinedShapeRegisterAccessorFor, this.assigner)).implement(this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).defineConstructor(new ModifierContributor.ForMethod[0]).withParameters(linkedHashMapExtractFields.values()).intercept(ConstructorCall.INSTANCE);
        for (Map.Entry<String, TypeDescription> entry : linkedHashMapExtractFields.entrySet()) {
            builderIntercept = builderIntercept.defineField(entry.getKey(), entry.getValue(), Visibility.PRIVATE);
        }
        return builderIntercept.make();
    }

    protected enum PrecomputedMethodGraph implements MethodGraph.Compiler {
        INSTANCE;

        private final MethodGraph.Linked methodGraph;

        PrecomputedMethodGraph() {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            MethodDescription.Latent latent = new MethodDescription.Latent(new TypeDescription.ForLoadedType(Callable.class), NotificationCompat.CATEGORY_CALL, 1025, Collections.emptyList(), TypeDescription.Generic.OBJECT, Collections.emptyList(), Collections.singletonList(new TypeDescription.Generic.OfNonGenericType.ForLoadedType(Exception.class)), Collections.emptyList(), AnnotationValue.UNDEFINED, TypeDescription.Generic.UNDEFINED);
            linkedHashMap.put(latent.asSignatureToken(), new MethodGraph.Node.Simple(latent));
            MethodDescription.Latent latent2 = new MethodDescription.Latent(new TypeDescription.ForLoadedType(Runnable.class), "run", 1025, Collections.emptyList(), TypeDescription.Generic.VOID, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), AnnotationValue.UNDEFINED, TypeDescription.Generic.UNDEFINED);
            linkedHashMap.put(latent2.asSignatureToken(), new MethodGraph.Node.Simple(latent2));
            MethodGraph.Simple simple = new MethodGraph.Simple(linkedHashMap);
            this.methodGraph = new MethodGraph.Linked.Delegation(simple, simple, Collections.emptyMap());
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        public MethodGraph.Linked compile(TypeDescription typeDescription) {
            return compile(typeDescription, typeDescription);
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        public MethodGraph.Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription) {
            return this.methodGraph;
        }
    }

    protected enum ConstructorCall implements Implementation {
        INSTANCE;

        private final MethodDescription objectTypeDefaultConstructor = (MethodDescription) TypeDescription.OBJECT.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly();

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
            return new Appender(target.getInstrumentedType());
        }

        protected class Appender implements ByteCodeAppender {
            private final TypeDescription instrumentedType;

            private Appender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                FieldList<FieldDescription.InDefinedShape> declaredFields = this.instrumentedType.getDeclaredFields();
                ArrayList arrayList = new ArrayList(declaredFields.size());
                Iterator it = declaredFields.iterator();
                while (it.hasNext()) {
                    arrayList.add(new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription) it.next()).read()));
                }
                return new ByteCodeAppender.Size(new StackManipulation.Compound(new StackManipulation.Compound(arrayList), MethodInvocation.invoke(MethodCall.this.accessorMethod), MethodCall.this.assigner.assign(MethodCall.this.accessorMethod.getReturnType(), methodDescription.getReturnType(), Assigner.Typing.DYNAMIC), MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
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

    public static class AssignableSignatureCall implements StackManipulation {
        private final boolean serializable;
        private final Implementation.SpecialMethodInvocation specialMethodInvocation;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof AssignableSignatureCall;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AssignableSignatureCall)) {
                return false;
            }
            AssignableSignatureCall assignableSignatureCall = (AssignableSignatureCall) obj;
            if (!assignableSignatureCall.canEqual(this)) {
                return false;
            }
            Implementation.SpecialMethodInvocation specialMethodInvocation = this.specialMethodInvocation;
            Implementation.SpecialMethodInvocation specialMethodInvocation2 = assignableSignatureCall.specialMethodInvocation;
            if (specialMethodInvocation != null ? specialMethodInvocation.equals(specialMethodInvocation2) : specialMethodInvocation2 == null) {
                return this.serializable == assignableSignatureCall.serializable;
            }
            return false;
        }

        public int hashCode() {
            Implementation.SpecialMethodInvocation specialMethodInvocation = this.specialMethodInvocation;
            return (((specialMethodInvocation == null ? 43 : specialMethodInvocation.hashCode()) + 59) * 59) + (this.serializable ? 79 : 97);
        }

        public AssignableSignatureCall(Implementation.SpecialMethodInvocation specialMethodInvocation, boolean z) {
            this.specialMethodInvocation = specialMethodInvocation;
            this.serializable = z;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription typeDescriptionRegister = context.register(new MethodCallProxy(this.specialMethodInvocation, this.serializable));
            return new StackManipulation.Compound(TypeCreation.of(typeDescriptionRegister), Duplication.SINGLE, MethodVariableAccess.allArgumentsOf(this.specialMethodInvocation.getMethodDescription()).prependThisReference(), MethodInvocation.invoke((MethodDescription.InDefinedShape) typeDescriptionRegister.getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly())).apply(methodVisitor, context);
        }
    }
}
