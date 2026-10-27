package net.bytebuddy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.EnumerationState;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.TypeManifestation;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.PackageDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer;
import net.bytebuddy.dynamic.scaffold.inline.RebaseDynamicTypeBuilder;
import net.bytebuddy.dynamic.scaffold.inline.RedefinitionDynamicTypeBuilder;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.dynamic.scaffold.subclass.SubclassDynamicTypeBuilder;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;

/* JADX INFO: loaded from: classes3.dex */
public class ByteBuddy {
    private static final String BYTE_BUDDY_DEFAULT_PREFIX = "ByteBuddy";
    private static final String BYTE_BUDDY_DEFAULT_SUFFIX = "auxiliary";
    protected final AnnotationRetention annotationRetention;
    protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
    protected final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
    protected final ClassFileVersion classFileVersion;
    protected final LatentMatcher<? super MethodDescription> ignoredMethods;
    protected final Implementation.Context.Factory implementationContextFactory;
    protected final InstrumentedType.Factory instrumentedTypeFactory;
    protected final MethodGraph.Compiler methodGraphCompiler;
    protected final NamingStrategy namingStrategy;
    protected final TypeValidation typeValidation;

    protected boolean canEqual(Object obj) {
        return obj instanceof ByteBuddy;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteBuddy)) {
            return false;
        }
        ByteBuddy byteBuddy = (ByteBuddy) obj;
        if (!byteBuddy.canEqual(this)) {
            return false;
        }
        ClassFileVersion classFileVersion = this.classFileVersion;
        ClassFileVersion classFileVersion2 = byteBuddy.classFileVersion;
        if (classFileVersion != null ? !classFileVersion.equals(classFileVersion2) : classFileVersion2 != null) {
            return false;
        }
        NamingStrategy namingStrategy = this.namingStrategy;
        NamingStrategy namingStrategy2 = byteBuddy.namingStrategy;
        if (namingStrategy != null ? !namingStrategy.equals(namingStrategy2) : namingStrategy2 != null) {
            return false;
        }
        AuxiliaryType.NamingStrategy namingStrategy3 = this.auxiliaryTypeNamingStrategy;
        AuxiliaryType.NamingStrategy namingStrategy4 = byteBuddy.auxiliaryTypeNamingStrategy;
        if (namingStrategy3 != null ? !namingStrategy3.equals(namingStrategy4) : namingStrategy4 != null) {
            return false;
        }
        AnnotationValueFilter.Factory factory = this.annotationValueFilterFactory;
        AnnotationValueFilter.Factory factory2 = byteBuddy.annotationValueFilterFactory;
        if (factory != null ? !factory.equals(factory2) : factory2 != null) {
            return false;
        }
        AnnotationRetention annotationRetention = this.annotationRetention;
        AnnotationRetention annotationRetention2 = byteBuddy.annotationRetention;
        if (annotationRetention != null ? !annotationRetention.equals(annotationRetention2) : annotationRetention2 != null) {
            return false;
        }
        Implementation.Context.Factory factory3 = this.implementationContextFactory;
        Implementation.Context.Factory factory4 = byteBuddy.implementationContextFactory;
        if (factory3 != null ? !factory3.equals(factory4) : factory4 != null) {
            return false;
        }
        MethodGraph.Compiler compiler = this.methodGraphCompiler;
        MethodGraph.Compiler compiler2 = byteBuddy.methodGraphCompiler;
        if (compiler != null ? !compiler.equals(compiler2) : compiler2 != null) {
            return false;
        }
        InstrumentedType.Factory factory5 = this.instrumentedTypeFactory;
        InstrumentedType.Factory factory6 = byteBuddy.instrumentedTypeFactory;
        if (factory5 != null ? !factory5.equals(factory6) : factory6 != null) {
            return false;
        }
        LatentMatcher<? super MethodDescription> latentMatcher = this.ignoredMethods;
        LatentMatcher<? super MethodDescription> latentMatcher2 = byteBuddy.ignoredMethods;
        if (latentMatcher != null ? !latentMatcher.equals(latentMatcher2) : latentMatcher2 != null) {
            return false;
        }
        TypeValidation typeValidation = this.typeValidation;
        TypeValidation typeValidation2 = byteBuddy.typeValidation;
        return typeValidation != null ? typeValidation.equals(typeValidation2) : typeValidation2 == null;
    }

    public int hashCode() {
        ClassFileVersion classFileVersion = this.classFileVersion;
        int iHashCode = classFileVersion == null ? 43 : classFileVersion.hashCode();
        NamingStrategy namingStrategy = this.namingStrategy;
        int iHashCode2 = ((iHashCode + 59) * 59) + (namingStrategy == null ? 43 : namingStrategy.hashCode());
        AuxiliaryType.NamingStrategy namingStrategy2 = this.auxiliaryTypeNamingStrategy;
        int iHashCode3 = (iHashCode2 * 59) + (namingStrategy2 == null ? 43 : namingStrategy2.hashCode());
        AnnotationValueFilter.Factory factory = this.annotationValueFilterFactory;
        int iHashCode4 = (iHashCode3 * 59) + (factory == null ? 43 : factory.hashCode());
        AnnotationRetention annotationRetention = this.annotationRetention;
        int iHashCode5 = (iHashCode4 * 59) + (annotationRetention == null ? 43 : annotationRetention.hashCode());
        Implementation.Context.Factory factory2 = this.implementationContextFactory;
        int iHashCode6 = (iHashCode5 * 59) + (factory2 == null ? 43 : factory2.hashCode());
        MethodGraph.Compiler compiler = this.methodGraphCompiler;
        int iHashCode7 = (iHashCode6 * 59) + (compiler == null ? 43 : compiler.hashCode());
        InstrumentedType.Factory factory3 = this.instrumentedTypeFactory;
        int iHashCode8 = (iHashCode7 * 59) + (factory3 == null ? 43 : factory3.hashCode());
        LatentMatcher<? super MethodDescription> latentMatcher = this.ignoredMethods;
        int iHashCode9 = (iHashCode8 * 59) + (latentMatcher == null ? 43 : latentMatcher.hashCode());
        TypeValidation typeValidation = this.typeValidation;
        return (iHashCode9 * 59) + (typeValidation != null ? typeValidation.hashCode() : 43);
    }

    public ByteBuddy() {
        this(ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V6));
    }

    public ByteBuddy(ClassFileVersion classFileVersion) {
        this(classFileVersion, new NamingStrategy.SuffixingRandom(BYTE_BUDDY_DEFAULT_PREFIX), new AuxiliaryType.NamingStrategy.SuffixingRandom(BYTE_BUDDY_DEFAULT_SUFFIX), AnnotationValueFilter.Default.APPEND_DEFAULTS, AnnotationRetention.ENABLED, Implementation.Context.Default.Factory.INSTANCE, MethodGraph.Compiler.DEFAULT, InstrumentedType.Factory.Default.MODIFIABLE, TypeValidation.ENABLED, new LatentMatcher.Resolved(ElementMatchers.isSynthetic().or(ElementMatchers.isDefaultFinalizer())));
    }

    protected ByteBuddy(ClassFileVersion classFileVersion, NamingStrategy namingStrategy, AuxiliaryType.NamingStrategy namingStrategy2, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, InstrumentedType.Factory factory3, TypeValidation typeValidation, LatentMatcher<? super MethodDescription> latentMatcher) {
        this.classFileVersion = classFileVersion;
        this.namingStrategy = namingStrategy;
        this.auxiliaryTypeNamingStrategy = namingStrategy2;
        this.annotationValueFilterFactory = factory;
        this.annotationRetention = annotationRetention;
        this.implementationContextFactory = factory2;
        this.methodGraphCompiler = compiler;
        this.instrumentedTypeFactory = factory3;
        this.ignoredMethods = latentMatcher;
        this.typeValidation = typeValidation;
    }

    public <T> DynamicType.Builder<T> subclass(Class<T> cls) {
        return (DynamicType.Builder<T>) subclass(new TypeDescription.ForLoadedType(cls));
    }

    public <T> DynamicType.Builder<T> subclass(Class<T> cls, ConstructorStrategy constructorStrategy) {
        return (DynamicType.Builder<T>) subclass(new TypeDescription.ForLoadedType(cls), constructorStrategy);
    }

    public DynamicType.Builder<?> subclass(Type type) {
        return subclass(TypeDefinition.Sort.describe(type));
    }

    public DynamicType.Builder<?> subclass(Type type, ConstructorStrategy constructorStrategy) {
        return subclass(TypeDefinition.Sort.describe(type), constructorStrategy);
    }

    public DynamicType.Builder<?> subclass(TypeDefinition typeDefinition) {
        return subclass(typeDefinition, ConstructorStrategy.Default.IMITATE_SUPER_CLASS_OPENING);
    }

    public DynamicType.Builder<?> subclass(TypeDefinition typeDefinition, ConstructorStrategy constructorStrategy) {
        TypeDescription.Generic genericAsGenericType;
        TypeList.Generic empty;
        if (typeDefinition.isPrimitive() || typeDefinition.isArray() || typeDefinition.isFinal()) {
            throw new IllegalArgumentException("Cannot subclass primitive, array or final types: " + typeDefinition);
        }
        if (typeDefinition.isInterface()) {
            genericAsGenericType = TypeDescription.Generic.OBJECT;
            empty = new TypeList.Generic.Explicit(typeDefinition);
        } else {
            genericAsGenericType = typeDefinition.asGenericType();
            empty = new TypeList.Generic.Empty();
        }
        return new SubclassDynamicTypeBuilder(this.instrumentedTypeFactory.subclass(this.namingStrategy.subclass(typeDefinition.asGenericType()), ModifierContributor.Resolver.of(Visibility.PUBLIC, TypeManifestation.PLAIN).resolve(typeDefinition.getModifiers()), genericAsGenericType).withInterfaces(empty), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods, constructorStrategy);
    }

    public DynamicType.Builder<?> makeInterface() {
        return makeInterface((Collection<? extends TypeDefinition>) Collections.emptyList());
    }

    public <T> DynamicType.Builder<T> makeInterface(Class<T> cls) {
        return (DynamicType.Builder<T>) makeInterface(Collections.singletonList(cls));
    }

    public DynamicType.Builder<?> makeInterface(Type... typeArr) {
        return makeInterface(Arrays.asList(typeArr));
    }

    public DynamicType.Builder<?> makeInterface(List<? extends Type> list) {
        return makeInterface((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
    }

    public DynamicType.Builder<?> makeInterface(TypeDefinition... typeDefinitionArr) {
        return makeInterface((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
    }

    public DynamicType.Builder<?> makeInterface(Collection<? extends TypeDefinition> collection) {
        return subclass(Object.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).implement(collection).modifiers(TypeManifestation.INTERFACE, Visibility.PUBLIC);
    }

    public DynamicType.Builder<?> makePackage(String str) {
        return new SubclassDynamicTypeBuilder(this.instrumentedTypeFactory.subclass(str + ".package-info", PackageDescription.PACKAGE_MODIFIERS, TypeDescription.Generic.OBJECT), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods, ConstructorStrategy.Default.NO_CONSTRUCTORS);
    }

    public DynamicType.Builder<? extends Annotation> makeAnnotation() {
        return new SubclassDynamicTypeBuilder(this.instrumentedTypeFactory.subclass(this.namingStrategy.subclass(TypeDescription.Generic.ANNOTATION), ModifierContributor.Resolver.of(Visibility.PUBLIC, TypeManifestation.ANNOTATION).resolve(), TypeDescription.Generic.OBJECT).withInterfaces((TypeList.Generic) new TypeList.Generic.Explicit(TypeDescription.Generic.ANNOTATION)), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods, ConstructorStrategy.Default.NO_CONSTRUCTORS);
    }

    public DynamicType.Builder<? extends Enum<?>> makeEnumeration(String... strArr) {
        return makeEnumeration(Arrays.asList(strArr));
    }

    public DynamicType.Builder<? extends Enum<?>> makeEnumeration(Collection<? extends String> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Require at least one enumeration constant");
        }
        TypeDescription.Generic genericBuild = TypeDescription.Generic.Builder.parameterizedType((Class<?>) Enum.class, TargetType.class).build();
        return new SubclassDynamicTypeBuilder(this.instrumentedTypeFactory.subclass(this.namingStrategy.subclass(genericBuild), ModifierContributor.Resolver.of(Visibility.PUBLIC, TypeManifestation.FINAL, EnumerationState.ENUMERATION).resolve(), genericBuild), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods, ConstructorStrategy.Default.NO_CONSTRUCTORS).defineConstructor(Visibility.PRIVATE).withParameters(String.class, Integer.TYPE).intercept(SuperMethodCall.INSTANCE).defineMethod("valueOf", TargetType.class, Visibility.PUBLIC, Ownership.STATIC).withParameters(String.class).intercept(MethodCall.invoke((MethodDescription) genericBuild.getDeclaredMethods().filter(ElementMatchers.named("valueOf").and(ElementMatchers.takesArguments((Class<?>[]) new Class[]{Class.class, String.class}))).getOnly()).withOwnType().withArgument(0).withAssigner(Assigner.DEFAULT, Assigner.Typing.DYNAMIC)).defineMethod("values", TargetType[].class, Visibility.PUBLIC, Ownership.STATIC).intercept(new EnumerationImplementation(new ArrayList(collection)));
    }

    public <T> DynamicType.Builder<T> redefine(Class<T> cls) {
        return redefine(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public <T> DynamicType.Builder<T> redefine(Class<T> cls, ClassFileLocator classFileLocator) {
        return redefine(new TypeDescription.ForLoadedType(cls), classFileLocator);
    }

    public <T> DynamicType.Builder<T> redefine(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        return new RedefinitionDynamicTypeBuilder(this.instrumentedTypeFactory.represent(typeDescription), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods, typeDescription, classFileLocator);
    }

    public <T> DynamicType.Builder<T> rebase(Class<T> cls) {
        return rebase(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public <T> DynamicType.Builder<T> rebase(Class<T> cls, ClassFileLocator classFileLocator) {
        return rebase(new TypeDescription.ForLoadedType(cls), classFileLocator);
    }

    public <T> DynamicType.Builder<T> rebase(Class<T> cls, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
        return rebase(new TypeDescription.ForLoadedType(cls), classFileLocator, methodNameTransformer);
    }

    public <T> DynamicType.Builder<T> rebase(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        return rebase(typeDescription, classFileLocator, MethodNameTransformer.Suffixing.withRandomSuffix());
    }

    public <T> DynamicType.Builder<T> rebase(TypeDescription typeDescription, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
        return new RebaseDynamicTypeBuilder(this.instrumentedTypeFactory.represent(typeDescription), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.ignoredMethods, typeDescription, classFileLocator, methodNameTransformer);
    }

    public DynamicType.Builder<?> rebase(Package r2, ClassFileLocator classFileLocator) {
        return rebase(new PackageDescription.ForLoadedPackage(r2), classFileLocator);
    }

    public DynamicType.Builder<?> rebase(PackageDescription packageDescription, ClassFileLocator classFileLocator) {
        return rebase(new TypeDescription.ForPackageDescription(packageDescription), classFileLocator);
    }

    public ByteBuddy with(ClassFileVersion classFileVersion) {
        return new ByteBuddy(classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.ignoredMethods);
    }

    public ByteBuddy with(NamingStrategy namingStrategy) {
        return new ByteBuddy(this.classFileVersion, namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.ignoredMethods);
    }

    public ByteBuddy with(AuxiliaryType.NamingStrategy namingStrategy) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, namingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.ignoredMethods);
    }

    public ByteBuddy with(AnnotationValueFilter.Factory factory) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, factory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.ignoredMethods);
    }

    public ByteBuddy with(AnnotationRetention annotationRetention) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.ignoredMethods);
    }

    public ByteBuddy with(Implementation.Context.Factory factory) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, factory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.ignoredMethods);
    }

    public ByteBuddy with(MethodGraph.Compiler compiler) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, compiler, this.instrumentedTypeFactory, this.typeValidation, this.ignoredMethods);
    }

    public ByteBuddy with(InstrumentedType.Factory factory) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, factory, this.typeValidation, this.ignoredMethods);
    }

    public ByteBuddy with(TypeValidation typeValidation) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, typeValidation, this.ignoredMethods);
    }

    public ByteBuddy ignore(ElementMatcher<? super MethodDescription> elementMatcher) {
        return ignore(new LatentMatcher.Resolved(elementMatcher));
    }

    public ByteBuddy ignore(LatentMatcher<? super MethodDescription> latentMatcher) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, latentMatcher);
    }

    protected static class EnumerationImplementation implements Implementation {
        protected static final String CLONE_METHOD_NAME = "clone";
        private static final int ENUM_FIELD_MODIFIERS = 25;
        private static final String ENUM_VALUES = "$VALUES";
        protected static final String ENUM_VALUES_METHOD_NAME = "values";
        protected static final String ENUM_VALUE_OF_METHOD_NAME = "valueOf";
        private final List<String> values;

        protected boolean canEqual(Object obj) {
            return obj instanceof EnumerationImplementation;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EnumerationImplementation)) {
                return false;
            }
            EnumerationImplementation enumerationImplementation = (EnumerationImplementation) obj;
            if (!enumerationImplementation.canEqual(this)) {
                return false;
            }
            List<String> list = this.values;
            List<String> list2 = enumerationImplementation.values;
            return list != null ? list.equals(list2) : list2 == null;
        }

        public int hashCode() {
            List<String> list = this.values;
            return 59 + (list == null ? 43 : list.hashCode());
        }

        protected EnumerationImplementation(List<String> list) {
            this.values = list;
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            Iterator<String> it = this.values.iterator();
            while (it.hasNext()) {
                instrumentedType = instrumentedType.withField(new FieldDescription.Token(it.next(), 16409, TargetType.DESCRIPTION.asGenericType()));
            }
            return instrumentedType.withField(new FieldDescription.Token(ENUM_VALUES, 4121, TypeDescription.ArrayProjection.of(TargetType.DESCRIPTION).asGenericType())).withInitializer(new InitializationAppender(this.values));
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Implementation.Target target) {
            return new ValuesMethodAppender(target.getInstrumentedType());
        }

        protected static class ValuesMethodAppender implements ByteCodeAppender {
            private final TypeDescription instrumentedType;

            protected boolean canEqual(Object obj) {
                return obj instanceof ValuesMethodAppender;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ValuesMethodAppender)) {
                    return false;
                }
                ValuesMethodAppender valuesMethodAppender = (ValuesMethodAppender) obj;
                if (!valuesMethodAppender.canEqual(this)) {
                    return false;
                }
                TypeDescription typeDescription = this.instrumentedType;
                TypeDescription typeDescription2 = valuesMethodAppender.instrumentedType;
                return typeDescription != null ? typeDescription.equals(typeDescription2) : typeDescription2 == null;
            }

            public int hashCode() {
                TypeDescription typeDescription = this.instrumentedType;
                return 59 + (typeDescription == null ? 43 : typeDescription.hashCode());
            }

            protected ValuesMethodAppender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                FieldDescription fieldDescription = (FieldDescription) this.instrumentedType.getDeclaredFields().filter(ElementMatchers.named(EnumerationImplementation.ENUM_VALUES)).getOnly();
                return new ByteCodeAppender.Size(new StackManipulation.Compound(FieldAccess.forField(fieldDescription).read(), MethodInvocation.invoke((MethodDescription) TypeDescription.Generic.OBJECT.getDeclaredMethods().filter(ElementMatchers.named(EnumerationImplementation.CLONE_METHOD_NAME)).getOnly()).virtual(fieldDescription.getType().asErasure()), TypeCasting.to(fieldDescription.getType().asErasure()), MethodReturn.REFERENCE).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }
        }

        protected static class InitializationAppender implements ByteCodeAppender {
            private final List<String> values;

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
                if (!initializationAppender.canEqual(this)) {
                    return false;
                }
                List<String> list = this.values;
                List<String> list2 = initializationAppender.values;
                return list != null ? list.equals(list2) : list2 == null;
            }

            public int hashCode() {
                List<String> list = this.values;
                return 59 + (list == null ? 43 : list.hashCode());
            }

            protected InitializationAppender(List<String> list) {
                this.values = list;
            }

            @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                TypeDescription typeDescriptionAsErasure = methodDescription.getDeclaringType().asErasure();
                MethodDescription methodDescription2 = (MethodDescription) typeDescriptionAsErasure.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments((Class<?>[]) new Class[]{String.class, Integer.TYPE}))).getOnly();
                StackManipulation stackManipulation = StackManipulation.Trivial.INSTANCE;
                ArrayList arrayList = new ArrayList(this.values.size());
                int i = 0;
                for (String str : this.values) {
                    FieldDescription fieldDescription = (FieldDescription) typeDescriptionAsErasure.getDeclaredFields().filter(ElementMatchers.named(str)).getOnly();
                    StackManipulation.Compound compound = new StackManipulation.Compound(stackManipulation, TypeCreation.of(typeDescriptionAsErasure), Duplication.SINGLE, new TextConstant(str), IntegerConstant.forValue(i), MethodInvocation.invoke(methodDescription2), FieldAccess.forField(fieldDescription).write());
                    arrayList.add(fieldDescription);
                    i++;
                    stackManipulation = compound;
                }
                ArrayList arrayList2 = new ArrayList(this.values.size());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(FieldAccess.forField((FieldDescription) it.next()).read());
                }
                return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulation, ArrayFactory.forType(typeDescriptionAsErasure.asGenericType()).withValues(arrayList2), FieldAccess.forField((FieldDescription.InDefinedShape) typeDescriptionAsErasure.getDeclaredFields().filter(ElementMatchers.named(EnumerationImplementation.ENUM_VALUES)).getOnly()).write()).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }
        }
    }
}
