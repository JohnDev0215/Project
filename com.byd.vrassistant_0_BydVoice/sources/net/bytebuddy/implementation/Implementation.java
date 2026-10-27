package net.bytebuddy.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.RandomString;

/* JADX INFO: loaded from: classes3.dex */
public interface Implementation extends InstrumentedType.Prepareable {

    public interface Composable extends Implementation {
        Implementation andThen(Implementation implementation);
    }

    ByteCodeAppender appender(Target target);

    public interface SpecialMethodInvocation extends StackManipulation {
        MethodDescription getMethodDescription();

        TypeDescription getTypeDescription();

        public enum Illegal implements SpecialMethodInvocation {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return false;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Context context) {
                throw new IllegalStateException("Cannot implement an undefined method");
            }

            @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
            public MethodDescription getMethodDescription() {
                throw new IllegalStateException("An illegal special method invocation must not be applied");
            }

            @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
            public TypeDescription getTypeDescription() {
                throw new IllegalStateException("An illegal special method invocation must not be applied");
            }
        }

        public static abstract class AbstractBase implements SpecialMethodInvocation {
            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            public int hashCode() {
                return (getMethodDescription().asSignatureToken().hashCode() * 31) + getTypeDescription().hashCode();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SpecialMethodInvocation)) {
                    return false;
                }
                SpecialMethodInvocation specialMethodInvocation = (SpecialMethodInvocation) obj;
                return getMethodDescription().asSignatureToken().equals(specialMethodInvocation.getMethodDescription().asSignatureToken()) && getTypeDescription().equals(specialMethodInvocation.getTypeDescription());
            }
        }

        public static class Simple extends AbstractBase {
            private final MethodDescription methodDescription;
            private final StackManipulation stackManipulation;
            private final TypeDescription typeDescription;

            protected Simple(MethodDescription methodDescription, TypeDescription typeDescription, StackManipulation stackManipulation) {
                this.methodDescription = methodDescription;
                this.typeDescription = typeDescription;
                this.stackManipulation = stackManipulation;
            }

            public static SpecialMethodInvocation of(MethodDescription methodDescription, TypeDescription typeDescription) {
                StackManipulation stackManipulationSpecial = MethodInvocation.invoke(methodDescription).special(typeDescription);
                return stackManipulationSpecial.isValid() ? new Simple(methodDescription, typeDescription, stackManipulationSpecial) : Illegal.INSTANCE;
            }

            @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
            public MethodDescription getMethodDescription() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
            public TypeDescription getTypeDescription() {
                return this.typeDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Context context) {
                return this.stackManipulation.apply(methodVisitor, context);
            }
        }
    }

    public interface Target {

        public interface Factory {
            Target make(TypeDescription typeDescription, MethodGraph.Linked linked, ClassFileVersion classFileVersion);
        }

        TypeDescription getInstrumentedType();

        TypeDefinition getOriginType();

        SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken);

        SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken, TypeDescription typeDescription);

        SpecialMethodInvocation invokeDominant(MethodDescription.SignatureToken signatureToken);

        SpecialMethodInvocation invokeSuper(MethodDescription.SignatureToken signatureToken);

        public static abstract class AbstractBase implements Target {
            protected final DefaultMethodInvocation defaultMethodInvocation;
            protected final TypeDescription instrumentedType;
            protected final MethodGraph.Linked methodGraph;

            protected boolean canEqual(Object obj) {
                return obj instanceof AbstractBase;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof AbstractBase)) {
                    return false;
                }
                AbstractBase abstractBase = (AbstractBase) obj;
                if (!abstractBase.canEqual(this)) {
                    return false;
                }
                TypeDescription instrumentedType = getInstrumentedType();
                TypeDescription instrumentedType2 = abstractBase.getInstrumentedType();
                if (instrumentedType != null ? !instrumentedType.equals(instrumentedType2) : instrumentedType2 != null) {
                    return false;
                }
                MethodGraph.Linked linked = this.methodGraph;
                MethodGraph.Linked linked2 = abstractBase.methodGraph;
                if (linked != null ? !linked.equals(linked2) : linked2 != null) {
                    return false;
                }
                DefaultMethodInvocation defaultMethodInvocation = this.defaultMethodInvocation;
                DefaultMethodInvocation defaultMethodInvocation2 = abstractBase.defaultMethodInvocation;
                return defaultMethodInvocation != null ? defaultMethodInvocation.equals(defaultMethodInvocation2) : defaultMethodInvocation2 == null;
            }

            public int hashCode() {
                TypeDescription instrumentedType = getInstrumentedType();
                int iHashCode = instrumentedType == null ? 43 : instrumentedType.hashCode();
                MethodGraph.Linked linked = this.methodGraph;
                int iHashCode2 = ((iHashCode + 59) * 59) + (linked == null ? 43 : linked.hashCode());
                DefaultMethodInvocation defaultMethodInvocation = this.defaultMethodInvocation;
                return (iHashCode2 * 59) + (defaultMethodInvocation != null ? defaultMethodInvocation.hashCode() : 43);
            }

            protected AbstractBase(TypeDescription typeDescription, MethodGraph.Linked linked, DefaultMethodInvocation defaultMethodInvocation) {
                this.instrumentedType = typeDescription;
                this.methodGraph = linked;
                this.defaultMethodInvocation = defaultMethodInvocation;
            }

            @Override // net.bytebuddy.implementation.Implementation.Target
            public TypeDescription getInstrumentedType() {
                return this.instrumentedType;
            }

            @Override // net.bytebuddy.implementation.Implementation.Target
            public SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken) {
                SpecialMethodInvocation specialMethodInvocation = SpecialMethodInvocation.Illegal.INSTANCE;
                Iterator it = this.instrumentedType.getInterfaces().asErasures().iterator();
                while (it.hasNext()) {
                    SpecialMethodInvocation specialMethodInvocationInvokeDefault = invokeDefault(signatureToken, (TypeDescription) it.next());
                    if (specialMethodInvocationInvokeDefault.isValid()) {
                        if (specialMethodInvocation.isValid()) {
                            return SpecialMethodInvocation.Illegal.INSTANCE;
                        }
                        specialMethodInvocation = specialMethodInvocationInvokeDefault;
                    }
                }
                return specialMethodInvocation;
            }

            @Override // net.bytebuddy.implementation.Implementation.Target
            public SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken, TypeDescription typeDescription) {
                return this.defaultMethodInvocation.apply(this.methodGraph.getInterfaceGraph(typeDescription).locate(signatureToken), typeDescription);
            }

            @Override // net.bytebuddy.implementation.Implementation.Target
            public SpecialMethodInvocation invokeDominant(MethodDescription.SignatureToken signatureToken) {
                SpecialMethodInvocation specialMethodInvocationInvokeSuper = invokeSuper(signatureToken);
                return specialMethodInvocationInvokeSuper.isValid() ? specialMethodInvocationInvokeSuper : invokeDefault(signatureToken);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public enum DefaultMethodInvocation {
                ENABLED { // from class: net.bytebuddy.implementation.Implementation.Target.AbstractBase.DefaultMethodInvocation.1
                    @Override // net.bytebuddy.implementation.Implementation.Target.AbstractBase.DefaultMethodInvocation
                    protected SpecialMethodInvocation apply(MethodGraph.Node node, TypeDescription typeDescription) {
                        return node.getSort().isUnique() ? SpecialMethodInvocation.Simple.of(node.getRepresentative(), typeDescription) : SpecialMethodInvocation.Illegal.INSTANCE;
                    }
                },
                DISABLED { // from class: net.bytebuddy.implementation.Implementation.Target.AbstractBase.DefaultMethodInvocation.2
                    @Override // net.bytebuddy.implementation.Implementation.Target.AbstractBase.DefaultMethodInvocation
                    protected SpecialMethodInvocation apply(MethodGraph.Node node, TypeDescription typeDescription) {
                        return SpecialMethodInvocation.Illegal.INSTANCE;
                    }
                };

                protected abstract SpecialMethodInvocation apply(MethodGraph.Node node, TypeDescription typeDescription);

                public static DefaultMethodInvocation of(ClassFileVersion classFileVersion) {
                    return classFileVersion.isAtLeast(ClassFileVersion.JAVA_V8) ? ENABLED : DISABLED;
                }
            }
        }
    }

    public interface Context extends MethodAccessorFactory {

        public interface Factory {
            ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2);
        }

        FieldDescription.InDefinedShape cache(StackManipulation stackManipulation, TypeDescription typeDescription);

        ClassFileVersion getClassFileVersion();

        TypeDescription getInstrumentedType();

        TypeDescription register(AuxiliaryType auxiliaryType);

        public interface ExtractableView extends Context {
            void drain(TypeInitializer.Drain drain, ClassVisitor classVisitor, AnnotationValueFilter.Factory factory);

            List<DynamicType> getAuxiliaryTypes();

            boolean isEnabled();

            public static abstract class AbstractBase implements ExtractableView {
                protected final ClassFileVersion classFileVersion;
                protected final TypeDescription instrumentedType;

                protected boolean canEqual(Object obj) {
                    return obj instanceof AbstractBase;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof AbstractBase)) {
                        return false;
                    }
                    AbstractBase abstractBase = (AbstractBase) obj;
                    if (!abstractBase.canEqual(this)) {
                        return false;
                    }
                    TypeDescription instrumentedType = getInstrumentedType();
                    TypeDescription instrumentedType2 = abstractBase.getInstrumentedType();
                    if (instrumentedType != null ? !instrumentedType.equals(instrumentedType2) : instrumentedType2 != null) {
                        return false;
                    }
                    ClassFileVersion classFileVersion = getClassFileVersion();
                    ClassFileVersion classFileVersion2 = abstractBase.getClassFileVersion();
                    return classFileVersion != null ? classFileVersion.equals(classFileVersion2) : classFileVersion2 == null;
                }

                public int hashCode() {
                    TypeDescription instrumentedType = getInstrumentedType();
                    int iHashCode = instrumentedType == null ? 43 : instrumentedType.hashCode();
                    ClassFileVersion classFileVersion = getClassFileVersion();
                    return ((iHashCode + 59) * 59) + (classFileVersion != null ? classFileVersion.hashCode() : 43);
                }

                protected AbstractBase(TypeDescription typeDescription, ClassFileVersion classFileVersion) {
                    this.instrumentedType = typeDescription;
                    this.classFileVersion = classFileVersion;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context
                public TypeDescription getInstrumentedType() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context
                public ClassFileVersion getClassFileVersion() {
                    return this.classFileVersion;
                }
            }
        }

        public static class Disabled extends ExtractableView.AbstractBase {
            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public boolean isEnabled() {
                return false;
            }

            protected Disabled(TypeDescription typeDescription, ClassFileVersion classFileVersion) {
                super(typeDescription, classFileVersion);
            }

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public List<DynamicType> getAuxiliaryTypes() {
                return Collections.emptyList();
            }

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public void drain(TypeInitializer.Drain drain, ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                drain.apply(classVisitor, TypeInitializer.None.INSTANCE, this);
            }

            @Override // net.bytebuddy.implementation.Implementation.Context
            public TypeDescription register(AuxiliaryType auxiliaryType) {
                throw new IllegalStateException("Registration of auxiliary types was disabled: " + auxiliaryType);
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerAccessorFor(SpecialMethodInvocation specialMethodInvocation, MethodAccessorFactory.AccessType accessType) {
                throw new IllegalStateException("Registration of method accessors was disabled: " + specialMethodInvocation.getMethodDescription());
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerGetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                throw new IllegalStateException("Registration of field accessor was disabled: " + fieldDescription);
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerSetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                throw new IllegalStateException("Registration of field accessor was disabled: " + fieldDescription);
            }

            @Override // net.bytebuddy.implementation.Implementation.Context
            public FieldDescription.InDefinedShape cache(StackManipulation stackManipulation, TypeDescription typeDescription) {
                throw new IllegalStateException("Field values caching was disabled: " + typeDescription);
            }

            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.Implementation.Context.Factory
                public ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2) {
                    if (typeInitializer.isDefined()) {
                        throw new IllegalStateException("Cannot define type initializer which was explicitly disabled: " + typeInitializer);
                    }
                    return new Disabled(typeDescription, classFileVersion);
                }
            }
        }

        public static class Default extends ExtractableView.AbstractBase {
            public static final String ACCESSOR_METHOD_SUFFIX = "accessor";
            public static final String FIELD_CACHE_PREFIX = "cachedValue";
            private final ClassFileVersion auxiliaryClassFileVersion;
            private final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
            private final Map<AuxiliaryType, DynamicType> auxiliaryTypes;
            private boolean fieldCacheCanAppendEntries;
            private final Map<SpecialMethodInvocation, DelegationRecord> registeredAccessorMethods;
            private final Map<FieldCacheEntry, FieldDescription.InDefinedShape> registeredFieldCacheEntries;
            private final Map<FieldDescription, DelegationRecord> registeredGetters;
            private final Map<FieldDescription, DelegationRecord> registeredSetters;
            private final String suffix;
            private final TypeInitializer typeInitializer;

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public boolean isEnabled() {
                return true;
            }

            protected Default(TypeDescription typeDescription, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion2) {
                super(typeDescription, classFileVersion);
                this.auxiliaryTypeNamingStrategy = namingStrategy;
                this.typeInitializer = typeInitializer;
                this.auxiliaryClassFileVersion = classFileVersion2;
                this.registeredAccessorMethods = new HashMap();
                this.registeredGetters = new HashMap();
                this.registeredSetters = new HashMap();
                this.auxiliaryTypes = new HashMap();
                this.registeredFieldCacheEntries = new HashMap();
                this.suffix = RandomString.make();
                this.fieldCacheCanAppendEntries = true;
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerAccessorFor(SpecialMethodInvocation specialMethodInvocation, MethodAccessorFactory.AccessType accessType) {
                DelegationRecord delegationRecordWith;
                DelegationRecord delegationRecord = this.registeredAccessorMethods.get(specialMethodInvocation);
                if (delegationRecord == null) {
                    delegationRecordWith = new AccessorMethodDelegation(this.instrumentedType, this.suffix, accessType, specialMethodInvocation);
                } else {
                    delegationRecordWith = delegationRecord.with(accessType);
                }
                this.registeredAccessorMethods.put(specialMethodInvocation, delegationRecordWith);
                return delegationRecordWith.getMethod();
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerGetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                DelegationRecord delegationRecordWith;
                DelegationRecord delegationRecord = this.registeredGetters.get(fieldDescription);
                if (delegationRecord == null) {
                    delegationRecordWith = new FieldGetterDelegation(this.instrumentedType, this.suffix, accessType, fieldDescription);
                } else {
                    delegationRecordWith = delegationRecord.with(accessType);
                }
                this.registeredGetters.put(fieldDescription, delegationRecordWith);
                return delegationRecordWith.getMethod();
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerSetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                DelegationRecord delegationRecordWith;
                DelegationRecord delegationRecord = this.registeredSetters.get(fieldDescription);
                if (delegationRecord == null) {
                    delegationRecordWith = new FieldSetterDelegation(this.instrumentedType, this.suffix, accessType, fieldDescription);
                } else {
                    delegationRecordWith = delegationRecord.with(accessType);
                }
                this.registeredSetters.put(fieldDescription, delegationRecordWith);
                return delegationRecordWith.getMethod();
            }

            @Override // net.bytebuddy.implementation.Implementation.Context
            public TypeDescription register(AuxiliaryType auxiliaryType) {
                DynamicType dynamicTypeMake = this.auxiliaryTypes.get(auxiliaryType);
                if (dynamicTypeMake == null) {
                    dynamicTypeMake = auxiliaryType.make(this.auxiliaryTypeNamingStrategy.name(this.instrumentedType), this.auxiliaryClassFileVersion, this);
                    this.auxiliaryTypes.put(auxiliaryType, dynamicTypeMake);
                }
                return dynamicTypeMake.getTypeDescription();
            }

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public List<DynamicType> getAuxiliaryTypes() {
                return new ArrayList(this.auxiliaryTypes.values());
            }

            @Override // net.bytebuddy.implementation.Implementation.Context
            public FieldDescription.InDefinedShape cache(StackManipulation stackManipulation, TypeDescription typeDescription) {
                FieldCacheEntry fieldCacheEntry = new FieldCacheEntry(stackManipulation, typeDescription);
                FieldDescription.InDefinedShape inDefinedShape = this.registeredFieldCacheEntries.get(fieldCacheEntry);
                if (inDefinedShape != null) {
                    return inDefinedShape;
                }
                if (!this.fieldCacheCanAppendEntries) {
                    throw new IllegalStateException("Cached values cannot be registered after defining the type initializer for " + this.instrumentedType);
                }
                int iHashCode = stackManipulation.hashCode();
                while (true) {
                    int i = iHashCode + 1;
                    CacheValueField cacheValueField = new CacheValueField(this.instrumentedType, typeDescription.asGenericType(), this.suffix, iHashCode);
                    if (!this.registeredFieldCacheEntries.values().contains(cacheValueField)) {
                        this.registeredFieldCacheEntries.put(fieldCacheEntry, cacheValueField);
                        return cacheValueField;
                    }
                    iHashCode = i;
                }
            }

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public void drain(TypeInitializer.Drain drain, ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                this.fieldCacheCanAppendEntries = false;
                TypeInitializer typeInitializerExpandWith = this.typeInitializer;
                for (Map.Entry<FieldCacheEntry, FieldDescription.InDefinedShape> entry : this.registeredFieldCacheEntries.entrySet()) {
                    FieldVisitor fieldVisitorVisitField = classVisitor.visitField(entry.getValue().getModifiers(), entry.getValue().getInternalName(), entry.getValue().getDescriptor(), entry.getValue().getGenericSignature(), FieldDescription.NO_DEFAULT_VALUE);
                    if (fieldVisitorVisitField != null) {
                        fieldVisitorVisitField.visitEnd();
                        typeInitializerExpandWith = typeInitializerExpandWith.expandWith(entry.getKey().storeIn(entry.getValue()));
                    }
                }
                drain.apply(classVisitor, typeInitializerExpandWith, this);
                Iterator<DelegationRecord> it = this.registeredAccessorMethods.values().iterator();
                while (it.hasNext()) {
                    it.next().apply(classVisitor, this, factory);
                }
                Iterator<DelegationRecord> it2 = this.registeredGetters.values().iterator();
                while (it2.hasNext()) {
                    it2.next().apply(classVisitor, this, factory);
                }
                Iterator<DelegationRecord> it3 = this.registeredSetters.values().iterator();
                while (it3.hasNext()) {
                    it3.next().apply(classVisitor, this, factory);
                }
            }

            protected static class CacheValueField extends FieldDescription.InDefinedShape.AbstractBase {
                private final TypeDescription.Generic fieldType;
                private final int hashCode;
                private final TypeDescription instrumentedType;
                private final String suffix;

                protected CacheValueField(TypeDescription typeDescription, TypeDescription.Generic generic, String str, int i) {
                    this.instrumentedType = typeDescription;
                    this.fieldType = generic;
                    this.suffix = str;
                    this.hashCode = i;
                }

                @Override // net.bytebuddy.description.field.FieldDescription
                public TypeDescription.Generic getType() {
                    return this.fieldType;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return (this.instrumentedType.isInterface() ? 1 : 2) | 4120;
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getName() {
                    return String.format("%s$%s$%s", Default.FIELD_CACHE_PREFIX, this.suffix, RandomString.hashOf(this.hashCode));
                }
            }

            protected static class FieldCacheEntry implements StackManipulation {
                private final TypeDescription fieldType;
                private final StackManipulation fieldValue;

                protected boolean canEqual(Object obj) {
                    return obj instanceof FieldCacheEntry;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof FieldCacheEntry)) {
                        return false;
                    }
                    FieldCacheEntry fieldCacheEntry = (FieldCacheEntry) obj;
                    if (!fieldCacheEntry.canEqual(this)) {
                        return false;
                    }
                    StackManipulation stackManipulation = this.fieldValue;
                    StackManipulation stackManipulation2 = fieldCacheEntry.fieldValue;
                    if (stackManipulation != null ? !stackManipulation.equals(stackManipulation2) : stackManipulation2 != null) {
                        return false;
                    }
                    TypeDescription fieldType = getFieldType();
                    TypeDescription fieldType2 = fieldCacheEntry.getFieldType();
                    return fieldType != null ? fieldType.equals(fieldType2) : fieldType2 == null;
                }

                public int hashCode() {
                    StackManipulation stackManipulation = this.fieldValue;
                    int iHashCode = stackManipulation == null ? 43 : stackManipulation.hashCode();
                    TypeDescription fieldType = getFieldType();
                    return ((iHashCode + 59) * 59) + (fieldType != null ? fieldType.hashCode() : 43);
                }

                protected FieldCacheEntry(StackManipulation stackManipulation, TypeDescription typeDescription) {
                    this.fieldValue = stackManipulation;
                    this.fieldType = typeDescription;
                }

                protected ByteCodeAppender storeIn(FieldDescription fieldDescription) {
                    return new ByteCodeAppender.Simple(this, FieldAccess.forField(fieldDescription).write());
                }

                protected TypeDescription getFieldType() {
                    return this.fieldType;
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public boolean isValid() {
                    return this.fieldValue.isValid();
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public StackManipulation.Size apply(MethodVisitor methodVisitor, Context context) {
                    return this.fieldValue.apply(methodVisitor, context);
                }
            }

            protected static abstract class AbstractPropertyAccessorMethod extends MethodDescription.InDefinedShape.AbstractBase {
                protected abstract int getBaseModifiers();

                protected AbstractPropertyAccessorMethod() {
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return getBaseModifiers() | 4096 | (getDeclaringType().isInterface() ? 1 : 16);
                }
            }

            protected static class AccessorMethod extends AbstractPropertyAccessorMethod {
                private final TypeDescription instrumentedType;
                private final MethodDescription methodDescription;
                private final String suffix;

                protected AccessorMethod(TypeDescription typeDescription, MethodDescription methodDescription, String str) {
                    this.instrumentedType = typeDescription;
                    this.methodDescription = methodDescription;
                    this.suffix = str;
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return this.methodDescription.getReturnType().asRawType();
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes(this, this.methodDescription.getParameters().asTypeList().asRawTypes());
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return this.methodDescription.getExceptionTypes().asRawTypes();
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public AnnotationValue<?, ?> getDefaultValue() {
                    return AnnotationValue.UNDEFINED;
                }

                @Override // net.bytebuddy.description.TypeVariableSource
                public TypeList.Generic getTypeVariables() {
                    return new TypeList.Generic.Empty();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.AbstractPropertyAccessorMethod
                public int getBaseModifiers() {
                    return this.methodDescription.isStatic() ? 8 : 0;
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return String.format("%s$%s$%s", this.methodDescription.getInternalName(), Default.ACCESSOR_METHOD_SUFFIX, this.suffix);
                }
            }

            protected static class FieldGetter extends AbstractPropertyAccessorMethod {
                private final FieldDescription fieldDescription;
                private final TypeDescription instrumentedType;
                private final String suffix;

                protected FieldGetter(TypeDescription typeDescription, FieldDescription fieldDescription, String str) {
                    this.instrumentedType = typeDescription;
                    this.fieldDescription = fieldDescription;
                    this.suffix = str;
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return this.fieldDescription.getType().asRawType();
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Empty();
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return new TypeList.Generic.Empty();
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public AnnotationValue<?, ?> getDefaultValue() {
                    return AnnotationValue.UNDEFINED;
                }

                @Override // net.bytebuddy.description.TypeVariableSource
                public TypeList.Generic getTypeVariables() {
                    return new TypeList.Generic.Empty();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.AbstractPropertyAccessorMethod
                protected int getBaseModifiers() {
                    return this.fieldDescription.isStatic() ? 8 : 0;
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return String.format("%s$%s$%s", this.fieldDescription.getName(), Default.ACCESSOR_METHOD_SUFFIX, this.suffix);
                }
            }

            protected static class FieldSetter extends AbstractPropertyAccessorMethod {
                private final FieldDescription fieldDescription;
                private final TypeDescription instrumentedType;
                private final String suffix;

                protected FieldSetter(TypeDescription typeDescription, FieldDescription fieldDescription, String str) {
                    this.instrumentedType = typeDescription;
                    this.fieldDescription = fieldDescription;
                    this.suffix = str;
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return TypeDescription.Generic.VOID;
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes(this, (List<? extends TypeDefinition>) Collections.singletonList(this.fieldDescription.getType().asRawType()));
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return new TypeList.Generic.Empty();
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public AnnotationValue<?, ?> getDefaultValue() {
                    return AnnotationValue.UNDEFINED;
                }

                @Override // net.bytebuddy.description.TypeVariableSource
                public TypeList.Generic getTypeVariables() {
                    return new TypeList.Generic.Empty();
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.AbstractPropertyAccessorMethod
                protected int getBaseModifiers() {
                    return this.fieldDescription.isStatic() ? 8 : 0;
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return String.format("%s$%s$%s", this.fieldDescription.getName(), Default.ACCESSOR_METHOD_SUFFIX, this.suffix);
                }
            }

            protected static abstract class DelegationRecord extends TypeWriter.MethodPool.Record.ForDefinedMethod implements ByteCodeAppender {
                protected final MethodDescription.InDefinedShape methodDescription;
                protected final Visibility visibility;

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyHead(MethodVisitor methodVisitor) {
                }

                protected abstract DelegationRecord with(MethodAccessorFactory.AccessType accessType);

                protected boolean canEqual(Object obj) {
                    return obj instanceof DelegationRecord;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof DelegationRecord)) {
                        return false;
                    }
                    DelegationRecord delegationRecord = (DelegationRecord) obj;
                    if (!delegationRecord.canEqual(this)) {
                        return false;
                    }
                    MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
                    MethodDescription.InDefinedShape inDefinedShape2 = delegationRecord.methodDescription;
                    if (inDefinedShape != null ? !inDefinedShape.equals(inDefinedShape2) : inDefinedShape2 != null) {
                        return false;
                    }
                    Visibility visibility = getVisibility();
                    Visibility visibility2 = delegationRecord.getVisibility();
                    return visibility != null ? visibility.equals(visibility2) : visibility2 == null;
                }

                public int hashCode() {
                    MethodDescription.InDefinedShape inDefinedShape = this.methodDescription;
                    int iHashCode = inDefinedShape == null ? 43 : inDefinedShape.hashCode();
                    Visibility visibility = getVisibility();
                    return ((iHashCode + 59) * 59) + (visibility != null ? visibility.hashCode() : 43);
                }

                protected DelegationRecord(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility) {
                    this.methodDescription = inDefinedShape;
                    this.visibility = visibility;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public MethodDescription.InDefinedShape getMethod() {
                    return this.methodDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public TypeWriter.MethodPool.Record.Sort getSort() {
                    return TypeWriter.MethodPool.Record.Sort.IMPLEMENTED;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Visibility getVisibility() {
                    return this.visibility;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyBody(MethodVisitor methodVisitor, Context context, AnnotationValueFilter.Factory factory) {
                    methodVisitor.visitCode();
                    ByteCodeAppender.Size sizeApplyCode = applyCode(methodVisitor, context);
                    methodVisitor.visitMaxs(sizeApplyCode.getOperandStackSize(), sizeApplyCode.getLocalVariableSize());
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Context context) {
                    return apply(methodVisitor, context, getMethod());
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public TypeWriter.MethodPool.Record prepend(ByteCodeAppender byteCodeAppender) {
                    throw new UnsupportedOperationException("Cannot prepend code to a delegation for " + this.methodDescription);
                }
            }

            protected static class AccessorMethodDelegation extends DelegationRecord {
                private final StackManipulation accessorMethodInvocation;

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                protected boolean canEqual(Object obj) {
                    return obj instanceof AccessorMethodDelegation;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof AccessorMethodDelegation)) {
                        return false;
                    }
                    AccessorMethodDelegation accessorMethodDelegation = (AccessorMethodDelegation) obj;
                    if (!accessorMethodDelegation.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    StackManipulation stackManipulation = this.accessorMethodInvocation;
                    StackManipulation stackManipulation2 = accessorMethodDelegation.accessorMethodInvocation;
                    return stackManipulation != null ? stackManipulation.equals(stackManipulation2) : stackManipulation2 == null;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    StackManipulation stackManipulation = this.accessorMethodInvocation;
                    return (iHashCode * 59) + (stackManipulation == null ? 43 : stackManipulation.hashCode());
                }

                protected AccessorMethodDelegation(TypeDescription typeDescription, String str, MethodAccessorFactory.AccessType accessType, SpecialMethodInvocation specialMethodInvocation) {
                    this(new AccessorMethod(typeDescription, specialMethodInvocation.getMethodDescription(), str), accessType.getVisibility(), specialMethodInvocation);
                }

                private AccessorMethodDelegation(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility, StackManipulation stackManipulation) {
                    super(inDefinedShape, visibility);
                    this.accessorMethodInvocation = stackManipulation;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                protected DelegationRecord with(MethodAccessorFactory.AccessType accessType) {
                    return new AccessorMethodDelegation(this.methodDescription, this.visibility.expandTo(accessType.getVisibility()), this.accessorMethodInvocation);
                }

                @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Context context, MethodDescription methodDescription) {
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), this.accessorMethodInvocation, MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }

            protected static class FieldGetterDelegation extends DelegationRecord {
                private final FieldDescription fieldDescription;

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                protected boolean canEqual(Object obj) {
                    return obj instanceof FieldGetterDelegation;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof FieldGetterDelegation)) {
                        return false;
                    }
                    FieldGetterDelegation fieldGetterDelegation = (FieldGetterDelegation) obj;
                    if (!fieldGetterDelegation.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    FieldDescription fieldDescription = this.fieldDescription;
                    FieldDescription fieldDescription2 = fieldGetterDelegation.fieldDescription;
                    return fieldDescription != null ? fieldDescription.equals(fieldDescription2) : fieldDescription2 == null;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    FieldDescription fieldDescription = this.fieldDescription;
                    return (iHashCode * 59) + (fieldDescription == null ? 43 : fieldDescription.hashCode());
                }

                protected FieldGetterDelegation(TypeDescription typeDescription, String str, MethodAccessorFactory.AccessType accessType, FieldDescription fieldDescription) {
                    this(new FieldGetter(typeDescription, fieldDescription, str), accessType.getVisibility(), fieldDescription);
                }

                private FieldGetterDelegation(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility, FieldDescription fieldDescription) {
                    super(inDefinedShape, visibility);
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                protected DelegationRecord with(MethodAccessorFactory.AccessType accessType) {
                    return new FieldGetterDelegation(this.methodDescription, this.visibility.expandTo(accessType.getVisibility()), this.fieldDescription);
                }

                @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Context context, MethodDescription methodDescription) {
                    StackManipulation[] stackManipulationArr = new StackManipulation[3];
                    stackManipulationArr[0] = this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                    stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                    stackManipulationArr[2] = MethodReturn.of(this.fieldDescription.getType());
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }

            protected static class FieldSetterDelegation extends DelegationRecord {
                private final FieldDescription fieldDescription;

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                protected boolean canEqual(Object obj) {
                    return obj instanceof FieldSetterDelegation;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof FieldSetterDelegation)) {
                        return false;
                    }
                    FieldSetterDelegation fieldSetterDelegation = (FieldSetterDelegation) obj;
                    if (!fieldSetterDelegation.canEqual(this) || !super.equals(obj)) {
                        return false;
                    }
                    FieldDescription fieldDescription = this.fieldDescription;
                    FieldDescription fieldDescription2 = fieldSetterDelegation.fieldDescription;
                    return fieldDescription != null ? fieldDescription.equals(fieldDescription2) : fieldDescription2 == null;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public int hashCode() {
                    int iHashCode = super.hashCode() + 59;
                    FieldDescription fieldDescription = this.fieldDescription;
                    return (iHashCode * 59) + (fieldDescription == null ? 43 : fieldDescription.hashCode());
                }

                protected FieldSetterDelegation(TypeDescription typeDescription, String str, MethodAccessorFactory.AccessType accessType, FieldDescription fieldDescription) {
                    this(new FieldSetter(typeDescription, fieldDescription, str), accessType.getVisibility(), fieldDescription);
                }

                private FieldSetterDelegation(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility, FieldDescription fieldDescription) {
                    super(inDefinedShape, visibility);
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                protected DelegationRecord with(MethodAccessorFactory.AccessType accessType) {
                    return new FieldSetterDelegation(this.methodDescription, this.visibility.expandTo(accessType.getVisibility()), this.fieldDescription);
                }

                @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Context context, MethodDescription methodDescription) {
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), FieldAccess.forField(this.fieldDescription).write(), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }

            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.Implementation.Context.Factory
                public ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2) {
                    return new Default(typeDescription, classFileVersion, namingStrategy, typeInitializer, classFileVersion2);
                }
            }
        }
    }

    public static class Compound implements Implementation {
        private final List<Implementation> implementations;

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
            List<Implementation> list = this.implementations;
            List<Implementation> list2 = compound.implementations;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<Implementation> list = this.implementations;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        public Compound(Implementation... implementationArr) {
            this((List<? extends Implementation>) Arrays.asList(implementationArr));
        }

        public Compound(List<? extends Implementation> list) {
            this.implementations = new ArrayList();
            for (Implementation implementation : list) {
                if (implementation instanceof Compound) {
                    this.implementations.addAll(((Compound) implementation).implementations);
                } else {
                    this.implementations.add(implementation);
                }
            }
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            Iterator<Implementation> it = this.implementations.iterator();
            while (it.hasNext()) {
                instrumentedType = it.next().prepare(instrumentedType);
            }
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Target target) {
            ByteCodeAppender[] byteCodeAppenderArr = new ByteCodeAppender[this.implementations.size()];
            Iterator<Implementation> it = this.implementations.iterator();
            int i = 0;
            while (it.hasNext()) {
                byteCodeAppenderArr[i] = it.next().appender(target);
                i++;
            }
            return new ByteCodeAppender.Compound(byteCodeAppenderArr);
        }
    }

    public static class Simple implements Implementation {
        private final ByteCodeAppender byteCodeAppender;

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

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
            ByteCodeAppender byteCodeAppender = this.byteCodeAppender;
            ByteCodeAppender byteCodeAppender2 = simple.byteCodeAppender;
            return byteCodeAppender != null ? byteCodeAppender.equals(byteCodeAppender2) : byteCodeAppender2 == null;
        }

        public int hashCode() {
            ByteCodeAppender byteCodeAppender = this.byteCodeAppender;
            return 59 + (byteCodeAppender == null ? 43 : byteCodeAppender.hashCode());
        }

        public Simple(ByteCodeAppender... byteCodeAppenderArr) {
            this.byteCodeAppender = new ByteCodeAppender.Compound(byteCodeAppenderArr);
        }

        public Simple(StackManipulation... stackManipulationArr) {
            this.byteCodeAppender = new ByteCodeAppender.Simple(stackManipulationArr);
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Target target) {
            return this.byteCodeAppender;
        }
    }
}
