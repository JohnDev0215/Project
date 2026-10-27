package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.e;

/* JADX INFO: loaded from: classes2.dex */
public final class JavaTypeResolverKt {

    @NotNull
    private static final FqName JAVA_LANG_CLASS_FQ_NAME = new FqName("java.lang.Class");

    @NotNull
    public static final KotlinType getErasedUpperBound(@NotNull TypeParameterDescriptor typeParameterDescriptor, boolean z, @NotNull JavaTypeAttributes typeAttr, @NotNull Function0<? extends KotlinType> defaultValue) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(typeAttr, "typeAttr");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if (typeParameterDescriptor == typeAttr.getUpperBoundOfTypeParameter()) {
            return defaultValue.invoke();
        }
        JavaTypeAttributes javaTypeAttributesWithTypeParameter = typeAttr.getUpperBoundOfTypeParameter() == null ? typeAttr.withTypeParameter(typeParameterDescriptor) : typeAttr;
        SimpleType defaultType = typeParameterDescriptor.getDefaultType();
        Intrinsics.checkNotNullExpressionValue(defaultType, "defaultType");
        Set<TypeParameterDescriptor> setExtractTypeParametersFromUpperBounds = TypeUtilsKt.extractTypeParametersFromUpperBounds(defaultType, typeAttr.getUpperBoundOfTypeParameter());
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.b(setExtractTypeParametersFromUpperBounds)), 16));
        for (TypeParameterDescriptor typeParameterDescriptor2 : setExtractTypeParametersFromUpperBounds) {
            Pair pair = TuplesKt.to(typeParameterDescriptor2.getTypeConstructor(), typeParameterDescriptor2 != typeAttr.getUpperBoundOfTypeParameter() ? RawSubstitution.INSTANCE.computeProjection(typeParameterDescriptor2, z ? typeAttr : typeAttr.withFlexibility(JavaTypeFlexibility.INFLEXIBLE), typeParameterDescriptor2 != typeAttr.getUpperBoundOfTypeParameter() ? getErasedUpperBound$default(typeParameterDescriptor2, z, javaTypeAttributesWithTypeParameter, null, 4, null) : StarProjectionImplKt.starProjectionType(typeParameterDescriptor2)) : makeStarProjection(typeParameterDescriptor2, typeAttr));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        TypeSubstitutor typeSubstitutorCreate = TypeSubstitutor.create(TypeConstructorSubstitution.Companion.createByConstructorsMap$default(TypeConstructorSubstitution.Companion, linkedHashMap, false, 2, null));
        Intrinsics.checkNotNullExpressionValue(typeSubstitutorCreate, "create(TypeConstructorSubstitution.createByConstructorsMap(erasedUpperBounds))");
        List<KotlinType> upperBounds = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "upperBounds");
        KotlinType nextUpperBound = (KotlinType) CollectionsKt.first((List) upperBounds);
        if (nextUpperBound.getConstructor().mo1345getDeclarationDescriptor() instanceof ClassDescriptor) {
            Intrinsics.checkNotNullExpressionValue(nextUpperBound, "firstUpperBound");
            return TypeUtilsKt.replaceArgumentsWithStarProjectionOrMapped(nextUpperBound, typeSubstitutorCreate, linkedHashMap, Variance.OUT_VARIANCE, typeAttr.getUpperBoundOfTypeParameter());
        }
        TypeParameterDescriptor upperBoundOfTypeParameter = typeAttr.getUpperBoundOfTypeParameter();
        if (upperBoundOfTypeParameter != null) {
            typeParameterDescriptor = upperBoundOfTypeParameter;
        }
        do {
            ClassifierDescriptor classifierDescriptorMo1345getDeclarationDescriptor = nextUpperBound.getConstructor().mo1345getDeclarationDescriptor();
            Objects.requireNonNull(classifierDescriptorMo1345getDeclarationDescriptor, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
            TypeParameterDescriptor typeParameterDescriptor3 = (TypeParameterDescriptor) classifierDescriptorMo1345getDeclarationDescriptor;
            if (Intrinsics.areEqual(typeParameterDescriptor3, typeParameterDescriptor)) {
                return defaultValue.invoke();
            }
            List<KotlinType> upperBounds2 = typeParameterDescriptor3.getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds2, "current.upperBounds");
            nextUpperBound = (KotlinType) CollectionsKt.first((List) upperBounds2);
        } while (!(nextUpperBound.getConstructor().mo1345getDeclarationDescriptor() instanceof ClassDescriptor));
        Intrinsics.checkNotNullExpressionValue(nextUpperBound, "nextUpperBound");
        return TypeUtilsKt.replaceArgumentsWithStarProjectionOrMapped(nextUpperBound, typeSubstitutorCreate, linkedHashMap, Variance.OUT_VARIANCE, typeAttr.getUpperBoundOfTypeParameter());
    }

    public static /* synthetic */ KotlinType getErasedUpperBound$default(final TypeParameterDescriptor typeParameterDescriptor, boolean z, JavaTypeAttributes javaTypeAttributes, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = new Function0<SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt.getErasedUpperBound.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final SimpleType invoke() {
                    StringBuilder sbK = e.k("Can't compute erased upper bound of type parameter `");
                    sbK.append(typeParameterDescriptor);
                    sbK.append('`');
                    SimpleType simpleTypeCreateErrorType = ErrorUtils.createErrorType(sbK.toString());
                    Intrinsics.checkNotNullExpressionValue(simpleTypeCreateErrorType, "createErrorType(\"Can't compute erased upper bound of type parameter `$this`\")");
                    return simpleTypeCreateErrorType;
                }
            };
        }
        return getErasedUpperBound(typeParameterDescriptor, z, javaTypeAttributes, function0);
    }

    @NotNull
    public static final TypeProjection makeStarProjection(@NotNull TypeParameterDescriptor typeParameter, @NotNull JavaTypeAttributes attr) {
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        Intrinsics.checkNotNullParameter(attr, "attr");
        return attr.getHowThisTypeIsUsed() == TypeUsage.SUPERTYPE ? new TypeProjectionImpl(StarProjectionImplKt.starProjectionType(typeParameter)) : new StarProjectionImpl(typeParameter);
    }

    @NotNull
    public static final JavaTypeAttributes toAttributes(@NotNull TypeUsage typeUsage, boolean z, @Nullable TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkNotNullParameter(typeUsage, "<this>");
        return new JavaTypeAttributes(typeUsage, null, z, typeParameterDescriptor, 2, null);
    }

    public static /* synthetic */ JavaTypeAttributes toAttributes$default(TypeUsage typeUsage, boolean z, TypeParameterDescriptor typeParameterDescriptor, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            typeParameterDescriptor = null;
        }
        return toAttributes(typeUsage, z, typeParameterDescriptor);
    }
}
