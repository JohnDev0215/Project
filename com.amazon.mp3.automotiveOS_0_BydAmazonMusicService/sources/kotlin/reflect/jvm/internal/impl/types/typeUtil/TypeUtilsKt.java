package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.StubTypeForBuilderInference;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;

/* JADX INFO: compiled from: TypeUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class TypeUtilsKt {
    @JvmOverloads
    public static final boolean hasTypeParameterRecursiveBounds(TypeParameterDescriptor typeParameter) {
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        return hasTypeParameterRecursiveBounds$default(typeParameter, null, null, 6, null);
    }

    public static final KotlinBuiltIns getBuiltIns(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        KotlinBuiltIns builtIns = kotlinType.getConstructor().getBuiltIns();
        Intrinsics.checkNotNullExpressionValue(builtIns, "constructor.builtIns");
        return builtIns;
    }

    public static final KotlinType makeNullable(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        KotlinType kotlinTypeMakeNullable = TypeUtils.makeNullable(kotlinType);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNullable, "makeNullable(this)");
        return kotlinTypeMakeNullable;
    }

    public static final KotlinType makeNotNullable(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        KotlinType kotlinTypeMakeNotNullable = TypeUtils.makeNotNullable(kotlinType);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNotNullable, "makeNotNullable(this)");
        return kotlinTypeMakeNotNullable;
    }

    public static final boolean isTypeParameter(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return TypeUtils.isTypeParameter(kotlinType);
    }

    public static final boolean isSubtypeOf(KotlinType kotlinType, KotlinType superType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(kotlinType, superType);
    }

    public static final KotlinType replaceAnnotations(KotlinType kotlinType, Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return (kotlinType.getAnnotations().isEmpty() && newAnnotations.isEmpty()) ? kotlinType : kotlinType.unwrap().replaceAnnotations(newAnnotations);
    }

    public static final TypeProjection createProjection(KotlinType type, Variance projectionKind, TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(projectionKind, "projectionKind");
        if ((typeParameterDescriptor == null ? null : typeParameterDescriptor.getVariance()) == projectionKind) {
            projectionKind = Variance.INVARIANT;
        }
        return new TypeProjectionImpl(projectionKind, type);
    }

    public static final TypeProjection asTypeProjection(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return new TypeProjectionImpl(kotlinType);
    }

    public static final boolean contains(KotlinType kotlinType, Function1<? super UnwrappedType, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return TypeUtils.contains(kotlinType, predicate);
    }

    public static final Set<TypeParameterDescriptor> extractTypeParametersFromUpperBounds(KotlinType kotlinType, Set<? extends TypeParameterDescriptor> set) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        extractTypeParametersFromUpperBounds(kotlinType, kotlinType, linkedHashSet, set);
        return linkedHashSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void extractTypeParametersFromUpperBounds(KotlinType kotlinType, KotlinType kotlinType2, Set<TypeParameterDescriptor> set, Set<? extends TypeParameterDescriptor> set2) {
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = kotlinType.getConstructor().mo4084getDeclarationDescriptor();
        if (classifierDescriptorMo4084getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            if (!Intrinsics.areEqual(kotlinType.getConstructor(), kotlinType2.getConstructor())) {
                set.add(classifierDescriptorMo4084getDeclarationDescriptor);
                return;
            }
            for (KotlinType upperBound : ((TypeParameterDescriptor) classifierDescriptorMo4084getDeclarationDescriptor).getUpperBounds()) {
                Intrinsics.checkNotNullExpressionValue(upperBound, "upperBound");
                extractTypeParametersFromUpperBounds(upperBound, kotlinType2, set, set2);
            }
            return;
        }
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor2 = kotlinType.getConstructor().mo4084getDeclarationDescriptor();
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters = classifierDescriptorMo4084getDeclarationDescriptor2 instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) classifierDescriptorMo4084getDeclarationDescriptor2 : null;
        List<TypeParameterDescriptor> declaredTypeParameters = classifierDescriptorWithTypeParameters == null ? null : classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
        int i = 0;
        for (TypeProjection typeProjection : kotlinType.getArguments()) {
            int i2 = i + 1;
            TypeParameterDescriptor typeParameterDescriptor = declaredTypeParameters == null ? null : (TypeParameterDescriptor) CollectionsKt___CollectionsKt.getOrNull(declaredTypeParameters, i);
            if (!((typeParameterDescriptor == null || set2 == null || !set2.contains(typeParameterDescriptor)) ? false : true) && !typeProjection.isStarProjection() && !CollectionsKt___CollectionsKt.contains(set, typeProjection.getType().getConstructor().mo4084getDeclarationDescriptor()) && !Intrinsics.areEqual(typeProjection.getType().getConstructor(), kotlinType2.getConstructor())) {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkNotNullExpressionValue(type, "argument.type");
                extractTypeParametersFromUpperBounds(type, kotlinType2, set, set2);
            }
            i = i2;
        }
    }

    public static /* synthetic */ boolean hasTypeParameterRecursiveBounds$default(TypeParameterDescriptor typeParameterDescriptor, TypeConstructor typeConstructor, Set set, int i, Object obj) {
        if ((i & 2) != 0) {
            typeConstructor = null;
        }
        if ((i & 4) != 0) {
            set = null;
        }
        return hasTypeParameterRecursiveBounds(typeParameterDescriptor, typeConstructor, set);
    }

    @JvmOverloads
    public static final boolean hasTypeParameterRecursiveBounds(TypeParameterDescriptor typeParameter, TypeConstructor typeConstructor, Set<? extends TypeParameterDescriptor> set) {
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        List<KotlinType> upperBounds = typeParameter.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "typeParameter.upperBounds");
        if (!(upperBounds instanceof Collection) || !upperBounds.isEmpty()) {
            for (KotlinType upperBound : upperBounds) {
                Intrinsics.checkNotNullExpressionValue(upperBound, "upperBound");
                if (containsSelfTypeParameter(upperBound, typeParameter.getDefaultType().getConstructor(), set) && (typeConstructor == null || Intrinsics.areEqual(upperBound.getConstructor(), typeConstructor))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final boolean containsSelfTypeParameter(KotlinType kotlinType, TypeConstructor typeConstructor, Set<? extends TypeParameterDescriptor> set) {
        boolean zContainsSelfTypeParameter;
        if (Intrinsics.areEqual(kotlinType.getConstructor(), typeConstructor)) {
            return true;
        }
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = kotlinType.getConstructor().mo4084getDeclarationDescriptor();
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters = classifierDescriptorMo4084getDeclarationDescriptor instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) classifierDescriptorMo4084getDeclarationDescriptor : null;
        List<TypeParameterDescriptor> declaredTypeParameters = classifierDescriptorWithTypeParameters == null ? null : classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt___CollectionsKt.withIndex(kotlinType.getArguments());
        if (!(iterableWithIndex instanceof Collection) || !((Collection) iterableWithIndex).isEmpty()) {
            for (IndexedValue indexedValue : iterableWithIndex) {
                int index = indexedValue.getIndex();
                TypeProjection typeProjection = (TypeProjection) indexedValue.component2();
                TypeParameterDescriptor typeParameterDescriptor = declaredTypeParameters == null ? null : (TypeParameterDescriptor) CollectionsKt___CollectionsKt.getOrNull(declaredTypeParameters, index);
                if (((typeParameterDescriptor == null || set == null || !set.contains(typeParameterDescriptor)) ? false : true) || typeProjection.isStarProjection()) {
                    zContainsSelfTypeParameter = false;
                } else {
                    KotlinType type = typeProjection.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "argument.type");
                    zContainsSelfTypeParameter = containsSelfTypeParameter(type, typeConstructor, set);
                }
                if (zContainsSelfTypeParameter) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean containsTypeAliasParameters(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return contains(kotlinType, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.containsTypeAliasParameters.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(UnwrappedType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = it.getConstructor().mo4084getDeclarationDescriptor();
                return Boolean.valueOf(classifierDescriptorMo4084getDeclarationDescriptor == null ? false : TypeUtilsKt.isTypeAliasParameter(classifierDescriptorMo4084getDeclarationDescriptor));
            }
        });
    }

    public static final boolean isTypeAliasParameter(ClassifierDescriptor classifierDescriptor) {
        Intrinsics.checkNotNullParameter(classifierDescriptor, "<this>");
        return (classifierDescriptor instanceof TypeParameterDescriptor) && (((TypeParameterDescriptor) classifierDescriptor).getContainingDeclaration() instanceof TypeAliasDescriptor);
    }

    public static final boolean requiresTypeAliasExpansion(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return contains(kotlinType, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.requiresTypeAliasExpansion.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(UnwrappedType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = it.getConstructor().mo4084getDeclarationDescriptor();
                boolean z = false;
                if (classifierDescriptorMo4084getDeclarationDescriptor != null && ((classifierDescriptorMo4084getDeclarationDescriptor instanceof TypeAliasDescriptor) || (classifierDescriptorMo4084getDeclarationDescriptor instanceof TypeParameterDescriptor))) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    public static final KotlinType getRepresentativeUpperBound(TypeParameterDescriptor typeParameterDescriptor) {
        Object obj;
        Intrinsics.checkNotNullParameter(typeParameterDescriptor, "<this>");
        List<KotlinType> upperBounds = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "upperBounds");
        upperBounds.isEmpty();
        List<KotlinType> upperBounds2 = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds2, "upperBounds");
        Iterator<T> it = upperBounds2.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = ((KotlinType) next).getConstructor().mo4084getDeclarationDescriptor();
            ClassDescriptor classDescriptor = classifierDescriptorMo4084getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo4084getDeclarationDescriptor : null;
            boolean z = false;
            if (classDescriptor != null && classDescriptor.getKind() != ClassKind.INTERFACE && classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
                z = true;
            }
            if (z) {
                obj = next;
                break;
            }
        }
        KotlinType kotlinType = (KotlinType) obj;
        if (kotlinType != null) {
            return kotlinType;
        }
        List<KotlinType> upperBounds3 = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds3, "upperBounds");
        Object objFirst = CollectionsKt___CollectionsKt.first((List<? extends Object>) upperBounds3);
        Intrinsics.checkNotNullExpressionValue(objFirst, "upperBounds.first()");
        return (KotlinType) objFirst;
    }

    public static final boolean shouldBeUpdated(KotlinType kotlinType) {
        return kotlinType == null || contains(kotlinType, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.shouldBeUpdated.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(UnwrappedType it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf((it instanceof StubTypeForBuilderInference) || (it.getConstructor() instanceof TypeVariableTypeConstructorMarker) || KotlinTypeKt.isError(it));
            }
        });
    }

    public static final KotlinType replaceArgumentsWithStarProjections(KotlinType kotlinType) {
        UnwrappedType unwrappedTypeReplace$default;
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        UnwrappedType unwrappedTypeUnwrap = kotlinType.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedTypeUnwrap;
            SimpleType lowerBound = flexibleType.getLowerBound();
            if (!lowerBound.getConstructor().getParameters().isEmpty() && lowerBound.getConstructor().mo4084getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters = lowerBound.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "constructor.parameters");
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters, 10));
                Iterator<T> it = parameters.iterator();
                while (it.hasNext()) {
                    arrayList.add(new StarProjectionImpl((TypeParameterDescriptor) it.next()));
                }
                lowerBound = TypeSubstitutionKt.replace$default(lowerBound, arrayList, null, 2, null);
            }
            SimpleType upperBound = flexibleType.getUpperBound();
            if (!upperBound.getConstructor().getParameters().isEmpty() && upperBound.getConstructor().mo4084getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters2 = upperBound.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters2, "constructor.parameters");
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters2, 10));
                Iterator<T> it2 = parameters2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new StarProjectionImpl((TypeParameterDescriptor) it2.next()));
                }
                upperBound = TypeSubstitutionKt.replace$default(upperBound, arrayList2, null, 2, null);
            }
            unwrappedTypeReplace$default = KotlinTypeFactory.flexibleType(lowerBound, upperBound);
        } else if (unwrappedTypeUnwrap instanceof SimpleType) {
            SimpleType simpleType = (SimpleType) unwrappedTypeUnwrap;
            boolean zIsEmpty = simpleType.getConstructor().getParameters().isEmpty();
            unwrappedTypeReplace$default = simpleType;
            if (!zIsEmpty) {
                ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = simpleType.getConstructor().mo4084getDeclarationDescriptor();
                unwrappedTypeReplace$default = simpleType;
                if (classifierDescriptorMo4084getDeclarationDescriptor != null) {
                    List<TypeParameterDescriptor> parameters3 = simpleType.getConstructor().getParameters();
                    Intrinsics.checkNotNullExpressionValue(parameters3, "constructor.parameters");
                    ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters3, 10));
                    Iterator<T> it3 = parameters3.iterator();
                    while (it3.hasNext()) {
                        arrayList3.add(new StarProjectionImpl((TypeParameterDescriptor) it3.next()));
                    }
                    unwrappedTypeReplace$default = TypeSubstitutionKt.replace$default(simpleType, arrayList3, null, 2, null);
                }
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(unwrappedTypeReplace$default, unwrappedTypeUnwrap);
    }

    public static final KotlinType replaceArgumentsWithStarProjectionOrMapped(KotlinType kotlinType, TypeSubstitutor substitutor, Map<TypeConstructor, ? extends TypeProjection> substitutionMap, Variance variance, Set<? extends TypeParameterDescriptor> set) {
        UnwrappedType unwrappedTypeReplace$default;
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Intrinsics.checkNotNullParameter(substitutor, "substitutor");
        Intrinsics.checkNotNullParameter(substitutionMap, "substitutionMap");
        Intrinsics.checkNotNullParameter(variance, "variance");
        UnwrappedType unwrappedTypeUnwrap = kotlinType.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedTypeUnwrap;
            SimpleType lowerBound = flexibleType.getLowerBound();
            if (!lowerBound.getConstructor().getParameters().isEmpty() && lowerBound.getConstructor().mo4084getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters = lowerBound.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "constructor.parameters");
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters, 10));
                for (TypeParameterDescriptor typeParameterDescriptor : parameters) {
                    TypeProjection starProjectionImpl = (TypeProjection) CollectionsKt___CollectionsKt.getOrNull(kotlinType.getArguments(), typeParameterDescriptor.getIndex());
                    if ((set != null && set.contains(typeParameterDescriptor)) || starProjectionImpl == null || !substitutionMap.containsKey(starProjectionImpl.getType().getConstructor())) {
                        starProjectionImpl = new StarProjectionImpl(typeParameterDescriptor);
                    }
                    arrayList.add(starProjectionImpl);
                }
                lowerBound = TypeSubstitutionKt.replace$default(lowerBound, arrayList, null, 2, null);
            }
            SimpleType upperBound = flexibleType.getUpperBound();
            if (!upperBound.getConstructor().getParameters().isEmpty() && upperBound.getConstructor().mo4084getDeclarationDescriptor() != null) {
                List<TypeParameterDescriptor> parameters2 = upperBound.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters2, "constructor.parameters");
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters2, 10));
                for (TypeParameterDescriptor typeParameterDescriptor2 : parameters2) {
                    TypeProjection starProjectionImpl2 = (TypeProjection) CollectionsKt___CollectionsKt.getOrNull(kotlinType.getArguments(), typeParameterDescriptor2.getIndex());
                    if ((set != null && set.contains(typeParameterDescriptor2)) || starProjectionImpl2 == null || !substitutionMap.containsKey(starProjectionImpl2.getType().getConstructor())) {
                        starProjectionImpl2 = new StarProjectionImpl(typeParameterDescriptor2);
                    }
                    arrayList2.add(starProjectionImpl2);
                }
                upperBound = TypeSubstitutionKt.replace$default(upperBound, arrayList2, null, 2, null);
            }
            unwrappedTypeReplace$default = KotlinTypeFactory.flexibleType(lowerBound, upperBound);
        } else {
            if (!(unwrappedTypeUnwrap instanceof SimpleType)) {
                throw new NoWhenBranchMatchedException();
            }
            SimpleType simpleType = (SimpleType) unwrappedTypeUnwrap;
            if (simpleType.getConstructor().getParameters().isEmpty() || simpleType.getConstructor().mo4084getDeclarationDescriptor() == null) {
                unwrappedTypeReplace$default = simpleType;
            } else {
                List<TypeParameterDescriptor> parameters3 = simpleType.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters3, "constructor.parameters");
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(parameters3, 10));
                for (TypeParameterDescriptor typeParameterDescriptor3 : parameters3) {
                    TypeProjection starProjectionImpl3 = (TypeProjection) CollectionsKt___CollectionsKt.getOrNull(kotlinType.getArguments(), typeParameterDescriptor3.getIndex());
                    if ((set != null && set.contains(typeParameterDescriptor3)) || starProjectionImpl3 == null || !substitutionMap.containsKey(starProjectionImpl3.getType().getConstructor())) {
                        starProjectionImpl3 = new StarProjectionImpl(typeParameterDescriptor3);
                    }
                    arrayList3.add(starProjectionImpl3);
                }
                unwrappedTypeReplace$default = TypeSubstitutionKt.replace$default(simpleType, arrayList3, null, 2, null);
            }
        }
        KotlinType kotlinTypeSafeSubstitute = substitutor.safeSubstitute(TypeWithEnhancementKt.inheritEnhancement(unwrappedTypeReplace$default, unwrappedTypeUnwrap), variance);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeSafeSubstitute, "replaceArgumentsByParame…ubstitute(it, variance) }");
        return kotlinTypeSafeSubstitute;
    }
}
