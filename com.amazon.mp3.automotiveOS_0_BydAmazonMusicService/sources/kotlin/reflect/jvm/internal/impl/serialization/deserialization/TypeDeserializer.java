package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionForAbsentTypeParameter;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: TypeDeserializer.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class TypeDeserializer {
    private final DeserializationContext c;
    private final Function1<Integer, ClassifierDescriptor> classifierDescriptors;
    private final String containerPresentableName;
    private final String debugName;
    private final TypeDeserializer parent;
    private final Function1<Integer, ClassifierDescriptor> typeAliasDescriptors;
    private final Map<Integer, TypeParameterDescriptor> typeParameterDescriptors;

    public TypeDeserializer(DeserializationContext c, TypeDeserializer typeDeserializer, List<ProtoBuf.TypeParameter> typeParameterProtos, String debugName, String containerPresentableName) {
        Map<Integer, TypeParameterDescriptor> linkedHashMap;
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(typeParameterProtos, "typeParameterProtos");
        Intrinsics.checkNotNullParameter(debugName, "debugName");
        Intrinsics.checkNotNullParameter(containerPresentableName, "containerPresentableName");
        this.c = c;
        this.parent = typeDeserializer;
        this.debugName = debugName;
        this.containerPresentableName = containerPresentableName;
        this.classifierDescriptors = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Integer, ClassifierDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$classifierDescriptors$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ ClassifierDescriptor invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final ClassifierDescriptor invoke(int i) {
                return this.this$0.computeClassifierDescriptor(i);
            }
        });
        this.typeAliasDescriptors = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Integer, ClassifierDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeAliasDescriptors$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ ClassifierDescriptor invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final ClassifierDescriptor invoke(int i) {
                return this.this$0.computeTypeAliasDescriptor(i);
            }
        });
        if (typeParameterProtos.isEmpty()) {
            linkedHashMap = MapsKt__MapsKt.emptyMap();
        } else {
            linkedHashMap = new LinkedHashMap<>();
            int i = 0;
            for (ProtoBuf.TypeParameter typeParameter : typeParameterProtos) {
                linkedHashMap.put(Integer.valueOf(typeParameter.getId()), new DeserializedTypeParameterDescriptor(this.c, typeParameter, i));
                i++;
            }
        }
        this.typeParameterDescriptors = linkedHashMap;
    }

    public final List<TypeParameterDescriptor> getOwnTypeParameters() {
        return CollectionsKt___CollectionsKt.toList(this.typeParameterDescriptors.values());
    }

    public final KotlinType type(ProtoBuf.Type proto) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        if (proto.hasFlexibleTypeCapabilitiesId()) {
            String string = this.c.getNameResolver().getString(proto.getFlexibleTypeCapabilitiesId());
            SimpleType simpleTypeSimpleType$default = simpleType$default(this, proto, false, 2, null);
            ProtoBuf.Type typeFlexibleUpperBound = ProtoTypeTableUtilKt.flexibleUpperBound(proto, this.c.getTypeTable());
            Intrinsics.checkNotNull(typeFlexibleUpperBound);
            return this.c.getComponents().getFlexibleTypeDeserializer().create(proto, string, simpleTypeSimpleType$default, simpleType$default(this, typeFlexibleUpperBound, false, 2, null));
        }
        return simpleType(proto, true);
    }

    public static /* synthetic */ SimpleType simpleType$default(TypeDeserializer typeDeserializer, ProtoBuf.Type type, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return typeDeserializer.simpleType(type, z);
    }

    public final SimpleType simpleType(final ProtoBuf.Type proto, boolean z) {
        SimpleType simpleTypeComputeLocalClassifierReplacementType;
        SimpleType simpleTypeSimpleType$default;
        Intrinsics.checkNotNullParameter(proto, "proto");
        if (proto.hasClassName()) {
            simpleTypeComputeLocalClassifierReplacementType = computeLocalClassifierReplacementType(proto.getClassName());
        } else {
            simpleTypeComputeLocalClassifierReplacementType = proto.hasTypeAliasName() ? computeLocalClassifierReplacementType(proto.getTypeAliasName()) : null;
        }
        if (simpleTypeComputeLocalClassifierReplacementType != null) {
            return simpleTypeComputeLocalClassifierReplacementType;
        }
        TypeConstructor typeConstructor = typeConstructor(proto);
        if (ErrorUtils.isError(typeConstructor.mo4084getDeclarationDescriptor())) {
            SimpleType simpleTypeCreateErrorTypeWithCustomConstructor = ErrorUtils.createErrorTypeWithCustomConstructor(typeConstructor.toString(), typeConstructor);
            Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorTypeWithCustomConstructor, "createErrorTypeWithCusto….toString(), constructor)");
            return simpleTypeCreateErrorTypeWithCustomConstructor;
        }
        DeserializedAnnotations deserializedAnnotations = new DeserializedAnnotations(this.c.getStorageManager(), new Function0<List<? extends AnnotationDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$simpleType$annotations$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends AnnotationDescriptor> invoke() {
                return this.this$0.c.getComponents().getAnnotationAndConstantLoader().loadTypeAnnotations(proto, this.this$0.c.getNameResolver());
            }
        });
        List<ProtoBuf.Type.Argument> listSimpleType$collectAllArguments = simpleType$collectAllArguments(proto, this);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSimpleType$collectAllArguments, 10));
        int i = 0;
        for (Object obj : listSimpleType$collectAllArguments) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            List<TypeParameterDescriptor> parameters = typeConstructor.getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "constructor.parameters");
            arrayList.add(typeArgument((TypeParameterDescriptor) CollectionsKt___CollectionsKt.getOrNull(parameters, i), (ProtoBuf.Type.Argument) obj));
            i = i2;
        }
        List<? extends TypeProjection> list = CollectionsKt___CollectionsKt.toList(arrayList);
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = typeConstructor.mo4084getDeclarationDescriptor();
        if (z && (classifierDescriptorMo4084getDeclarationDescriptor instanceof TypeAliasDescriptor)) {
            KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
            SimpleType simpleTypeComputeExpandedType = KotlinTypeFactory.computeExpandedType((TypeAliasDescriptor) classifierDescriptorMo4084getDeclarationDescriptor, list);
            simpleTypeSimpleType$default = simpleTypeComputeExpandedType.makeNullableAsSpecified(KotlinTypeKt.isNullable(simpleTypeComputeExpandedType) || proto.getNullable()).replaceAnnotations(Annotations.Companion.create(CollectionsKt___CollectionsKt.plus((Iterable) deserializedAnnotations, (Iterable) simpleTypeComputeExpandedType.getAnnotations())));
        } else {
            Boolean bool = Flags.SUSPEND_TYPE.get(proto.getFlags());
            Intrinsics.checkNotNullExpressionValue(bool, "SUSPEND_TYPE.get(proto.flags)");
            if (bool.booleanValue()) {
                simpleTypeSimpleType$default = createSuspendFunctionType(deserializedAnnotations, typeConstructor, list, proto.getNullable());
            } else {
                simpleTypeSimpleType$default = KotlinTypeFactory.simpleType$default(deserializedAnnotations, typeConstructor, list, proto.getNullable(), (KotlinTypeRefiner) null, 16, (Object) null);
                Boolean bool2 = Flags.DEFINITELY_NOT_NULL_TYPE.get(proto.getFlags());
                Intrinsics.checkNotNullExpressionValue(bool2, "DEFINITELY_NOT_NULL_TYPE.get(proto.flags)");
                if (bool2.booleanValue()) {
                    DefinitelyNotNullType definitelyNotNullTypeMakeDefinitelyNotNull$default = DefinitelyNotNullType.Companion.makeDefinitelyNotNull$default(DefinitelyNotNullType.Companion, simpleTypeSimpleType$default, false, 2, null);
                    if (definitelyNotNullTypeMakeDefinitelyNotNull$default == null) {
                        throw new IllegalStateException(("null DefinitelyNotNullType for '" + simpleTypeSimpleType$default + '\'').toString());
                    }
                    simpleTypeSimpleType$default = definitelyNotNullTypeMakeDefinitelyNotNull$default;
                }
            }
        }
        ProtoBuf.Type typeAbbreviatedType = ProtoTypeTableUtilKt.abbreviatedType(proto, this.c.getTypeTable());
        if (typeAbbreviatedType != null) {
            simpleTypeSimpleType$default = SpecialTypesKt.withAbbreviation(simpleTypeSimpleType$default, simpleType(typeAbbreviatedType, false));
        }
        if (proto.hasClassName()) {
            return this.c.getComponents().getPlatformDependentTypeTransformer().transformPlatformType(NameResolverUtilKt.getClassId(this.c.getNameResolver(), proto.getClassName()), simpleTypeSimpleType$default);
        }
        return simpleTypeSimpleType$default;
    }

    private static final List<ProtoBuf.Type.Argument> simpleType$collectAllArguments(ProtoBuf.Type type, TypeDeserializer typeDeserializer) {
        List<ProtoBuf.Type.Argument> argumentList = type.getArgumentList();
        Intrinsics.checkNotNullExpressionValue(argumentList, "argumentList");
        ProtoBuf.Type typeOuterType = ProtoTypeTableUtilKt.outerType(type, typeDeserializer.c.getTypeTable());
        List<ProtoBuf.Type.Argument> listSimpleType$collectAllArguments = typeOuterType == null ? null : simpleType$collectAllArguments(typeOuterType, typeDeserializer);
        if (listSimpleType$collectAllArguments == null) {
            listSimpleType$collectAllArguments = CollectionsKt__CollectionsKt.emptyList();
        }
        return CollectionsKt___CollectionsKt.plus((Collection) argumentList, (Iterable) listSimpleType$collectAllArguments);
    }

    private static final ClassDescriptor typeConstructor$notFoundClass(final TypeDeserializer typeDeserializer, ProtoBuf.Type type, int i) {
        ClassId classId = NameResolverUtilKt.getClassId(typeDeserializer.c.getNameResolver(), i);
        List<Integer> mutableList = SequencesKt___SequencesKt.toMutableList(SequencesKt___SequencesKt.map(SequencesKt__SequencesKt.generateSequence(type, (Function1<? super ProtoBuf.Type, ? extends ProtoBuf.Type>) ((Function1<? super Object, ? extends Object>) new Function1<ProtoBuf.Type, ProtoBuf.Type>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$notFoundClass$typeParametersCount$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ProtoBuf.Type invoke(ProtoBuf.Type it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return ProtoTypeTableUtilKt.outerType(it, this.this$0.c.getTypeTable());
            }
        })), new Function1<ProtoBuf.Type, Integer>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer$typeConstructor$notFoundClass$typeParametersCount$2
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(ProtoBuf.Type it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Integer.valueOf(it.getArgumentCount());
            }
        }));
        int iCount = SequencesKt___SequencesKt.count(SequencesKt__SequencesKt.generateSequence(classId, (Function1<? super ClassId, ? extends ClassId>) ((Function1<? super Object, ? extends Object>) TypeDeserializer$typeConstructor$notFoundClass$classNestingLevel$1.INSTANCE)));
        while (mutableList.size() < iCount) {
            mutableList.add(0);
        }
        return typeDeserializer.c.getComponents().getNotFoundClasses().getClass(classId, mutableList);
    }

    private final TypeConstructor typeConstructor(ProtoBuf.Type type) {
        ClassifierDescriptor classifierDescriptorInvoke;
        Object next;
        if (type.hasClassName()) {
            classifierDescriptorInvoke = this.classifierDescriptors.invoke(Integer.valueOf(type.getClassName()));
            if (classifierDescriptorInvoke == null) {
                classifierDescriptorInvoke = typeConstructor$notFoundClass(this, type, type.getClassName());
            }
        } else if (type.hasTypeParameter()) {
            classifierDescriptorInvoke = loadTypeParameter(type.getTypeParameter());
            if (classifierDescriptorInvoke == null) {
                TypeConstructor typeConstructorCreateErrorTypeConstructor = ErrorUtils.createErrorTypeConstructor("Unknown type parameter " + type.getTypeParameter() + ". Please try recompiling module containing \"" + this.containerPresentableName + '\"');
                Intrinsics.checkNotNullExpressionValue(typeConstructorCreateErrorTypeConstructor, "createErrorTypeConstruct…\\\"\"\n                    )");
                return typeConstructorCreateErrorTypeConstructor;
            }
        } else if (type.hasTypeParameterName()) {
            String string = this.c.getNameResolver().getString(type.getTypeParameterName());
            Iterator<T> it = getOwnTypeParameters().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(((TypeParameterDescriptor) next).getName().asString(), string)) {
                    break;
                }
            }
            classifierDescriptorInvoke = (TypeParameterDescriptor) next;
            if (classifierDescriptorInvoke == null) {
                TypeConstructor typeConstructorCreateErrorTypeConstructor2 = ErrorUtils.createErrorTypeConstructor("Deserialized type parameter " + string + " in " + this.c.getContainingDeclaration());
                Intrinsics.checkNotNullExpressionValue(typeConstructorCreateErrorTypeConstructor2, "createErrorTypeConstruct….containingDeclaration}\")");
                return typeConstructorCreateErrorTypeConstructor2;
            }
        } else if (type.hasTypeAliasName()) {
            classifierDescriptorInvoke = this.typeAliasDescriptors.invoke(Integer.valueOf(type.getTypeAliasName()));
            if (classifierDescriptorInvoke == null) {
                classifierDescriptorInvoke = typeConstructor$notFoundClass(this, type, type.getTypeAliasName());
            }
        } else {
            TypeConstructor typeConstructorCreateErrorTypeConstructor3 = ErrorUtils.createErrorTypeConstructor("Unknown type");
            Intrinsics.checkNotNullExpressionValue(typeConstructorCreateErrorTypeConstructor3, "createErrorTypeConstructor(\"Unknown type\")");
            return typeConstructorCreateErrorTypeConstructor3;
        }
        TypeConstructor typeConstructor = classifierDescriptorInvoke.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "classifier.typeConstructor");
        return typeConstructor;
    }

    private final SimpleType createSuspendFunctionType(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        int size;
        int size2 = typeConstructor.getParameters().size() - list.size();
        SimpleType simpleTypeCreateSuspendFunctionTypeForBasicCase = null;
        if (size2 == 0) {
            simpleTypeCreateSuspendFunctionTypeForBasicCase = createSuspendFunctionTypeForBasicCase(annotations, typeConstructor, list, z);
        } else if (size2 == 1 && (size = list.size() - 1) >= 0) {
            TypeConstructor typeConstructor2 = typeConstructor.getBuiltIns().getSuspendFunction(size).getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor2, "functionTypeConstructor.…on(arity).typeConstructor");
            simpleTypeCreateSuspendFunctionTypeForBasicCase = KotlinTypeFactory.simpleType$default(annotations, typeConstructor2, list, z, (KotlinTypeRefiner) null, 16, (Object) null);
        }
        if (simpleTypeCreateSuspendFunctionTypeForBasicCase != null) {
            return simpleTypeCreateSuspendFunctionTypeForBasicCase;
        }
        SimpleType simpleTypeCreateErrorTypeWithArguments = ErrorUtils.createErrorTypeWithArguments(Intrinsics.stringPlus("Bad suspend function in metadata with constructor: ", typeConstructor), list);
        Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorTypeWithArguments, "createErrorTypeWithArgum…      arguments\n        )");
        return simpleTypeCreateErrorTypeWithArguments;
    }

    private final SimpleType createSuspendFunctionTypeForBasicCase(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        SimpleType simpleTypeSimpleType$default = KotlinTypeFactory.simpleType$default(annotations, typeConstructor, list, z, (KotlinTypeRefiner) null, 16, (Object) null);
        if (FunctionTypesKt.isFunctionType(simpleTypeSimpleType$default)) {
            return transformRuntimeFunctionTypeToSuspendFunction(simpleTypeSimpleType$default);
        }
        return null;
    }

    private final SimpleType transformRuntimeFunctionTypeToSuspendFunction(KotlinType kotlinType) {
        TypeProjection typeProjection = (TypeProjection) CollectionsKt___CollectionsKt.lastOrNull((List) FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType));
        if (typeProjection == null) {
            return null;
        }
        KotlinType type = typeProjection.getType();
        Intrinsics.checkNotNullExpressionValue(type, "funType.getValueParamete…ll()?.type ?: return null");
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = type.getConstructor().mo4084getDeclarationDescriptor();
        FqName fqNameSafe = classifierDescriptorMo4084getDeclarationDescriptor == null ? null : DescriptorUtilsKt.getFqNameSafe(classifierDescriptorMo4084getDeclarationDescriptor);
        if (type.getArguments().size() != 1 || (!Intrinsics.areEqual(fqNameSafe, StandardNames.CONTINUATION_INTERFACE_FQ_NAME) && !Intrinsics.areEqual(fqNameSafe, TypeDeserializerKt.EXPERIMENTAL_CONTINUATION_FQ_NAME))) {
            return (SimpleType) kotlinType;
        }
        KotlinType type2 = ((TypeProjection) CollectionsKt___CollectionsKt.single((List) type.getArguments())).getType();
        Intrinsics.checkNotNullExpressionValue(type2, "continuationArgumentType.arguments.single().type");
        DeclarationDescriptor containingDeclaration = this.c.getContainingDeclaration();
        if (!(containingDeclaration instanceof CallableDescriptor)) {
            containingDeclaration = null;
        }
        CallableDescriptor callableDescriptor = (CallableDescriptor) containingDeclaration;
        if (Intrinsics.areEqual(callableDescriptor != null ? DescriptorUtilsKt.fqNameOrNull(callableDescriptor) : null, SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME)) {
            return createSimpleSuspendFunctionType(kotlinType, type2);
        }
        return createSimpleSuspendFunctionType(kotlinType, type2);
    }

    private final SimpleType createSimpleSuspendFunctionType(KotlinType kotlinType, KotlinType kotlinType2) {
        KotlinBuiltIns builtIns = TypeUtilsKt.getBuiltIns(kotlinType);
        Annotations annotations = kotlinType.getAnnotations();
        KotlinType receiverTypeFromFunctionType = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType);
        List listDropLast = CollectionsKt___CollectionsKt.dropLast(FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType), 1);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listDropLast, 10));
        Iterator it = listDropLast.iterator();
        while (it.hasNext()) {
            arrayList.add(((TypeProjection) it.next()).getType());
        }
        return FunctionTypesKt.createFunctionType(builtIns, annotations, receiverTypeFromFunctionType, arrayList, null, kotlinType2, true).makeNullableAsSpecified(kotlinType.isMarkedNullable());
    }

    private final TypeParameterDescriptor loadTypeParameter(int i) {
        TypeParameterDescriptor typeParameterDescriptor = this.typeParameterDescriptors.get(Integer.valueOf(i));
        if (typeParameterDescriptor != null) {
            return typeParameterDescriptor;
        }
        TypeDeserializer typeDeserializer = this.parent;
        if (typeDeserializer == null) {
            return null;
        }
        return typeDeserializer.loadTypeParameter(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassifierDescriptor computeClassifierDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), i);
        if (classId.isLocal()) {
            return this.c.getComponents().deserializeClass(classId);
        }
        return FindClassInModuleKt.findClassifierAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId);
    }

    private final SimpleType computeLocalClassifierReplacementType(int i) {
        if (NameResolverUtilKt.getClassId(this.c.getNameResolver(), i).isLocal()) {
            return this.c.getComponents().getLocalClassifierTypeSettings().getReplacementTypeForLocalClassifiers();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassifierDescriptor computeTypeAliasDescriptor(int i) {
        ClassId classId = NameResolverUtilKt.getClassId(this.c.getNameResolver(), i);
        if (classId.isLocal()) {
            return null;
        }
        return FindClassInModuleKt.findTypeAliasAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId);
    }

    private final TypeProjection typeArgument(TypeParameterDescriptor typeParameterDescriptor, ProtoBuf.Type.Argument argument) {
        if (argument.getProjection() == ProtoBuf.Type.Argument.Projection.STAR) {
            if (typeParameterDescriptor == null) {
                return new StarProjectionForAbsentTypeParameter(this.c.getComponents().getModuleDescriptor().getBuiltIns());
            }
            return new StarProjectionImpl(typeParameterDescriptor);
        }
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        ProtoBuf.Type.Argument.Projection projection = argument.getProjection();
        Intrinsics.checkNotNullExpressionValue(projection, "typeArgumentProto.projection");
        Variance variance = protoEnumFlags.variance(projection);
        ProtoBuf.Type type = ProtoTypeTableUtilKt.type(argument, this.c.getTypeTable());
        return type == null ? new TypeProjectionImpl(ErrorUtils.createErrorType("No type recorded")) : new TypeProjectionImpl(variance, type(type));
    }

    public String toString() {
        String str = this.debugName;
        TypeDeserializer typeDeserializer = this.parent;
        return Intrinsics.stringPlus(str, typeDeserializer == null ? "" : Intrinsics.stringPlus(". Child of ", typeDeserializer.debugName));
    }
}
