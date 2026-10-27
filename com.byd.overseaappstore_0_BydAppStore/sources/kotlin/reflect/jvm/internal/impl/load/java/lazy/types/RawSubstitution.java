package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class RawSubstitution extends TypeSubstitution {

    @NotNull
    public static final RawSubstitution INSTANCE = new RawSubstitution();

    @NotNull
    private static final JavaTypeAttributes lowerTypeAttr;

    @NotNull
    private static final JavaTypeAttributes upperTypeAttr;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JavaTypeFlexibility.values().length];
            iArr[JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND.ordinal()] = 1;
            iArr[JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND.ordinal()] = 2;
            iArr[JavaTypeFlexibility.INFLEXIBLE.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        TypeUsage typeUsage = TypeUsage.COMMON;
        lowerTypeAttr = JavaTypeResolverKt.toAttributes$default(typeUsage, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND);
        upperTypeAttr = JavaTypeResolverKt.toAttributes$default(typeUsage, false, null, 3, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND);
    }

    private RawSubstitution() {
    }

    public static /* synthetic */ TypeProjection computeProjection$default(RawSubstitution rawSubstitution, TypeParameterDescriptor typeParameterDescriptor, JavaTypeAttributes javaTypeAttributes, KotlinType kotlinType, int i, Object obj) {
        if ((i & 4) != 0) {
            kotlinType = JavaTypeResolverKt.getErasedUpperBound$default(typeParameterDescriptor, true, javaTypeAttributes, null, 4, null);
        }
        return rawSubstitution.computeProjection(typeParameterDescriptor, javaTypeAttributes, kotlinType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<SimpleType, Boolean> eraseInflexibleBasedOnClassDescriptor(final SimpleType simpleType, final ClassDescriptor classDescriptor, final JavaTypeAttributes javaTypeAttributes) {
        Boolean bool;
        if (simpleType.getConstructor().getParameters().isEmpty()) {
            bool = Boolean.FALSE;
        } else {
            if (KotlinBuiltIns.isArray(simpleType)) {
                TypeProjection typeProjection = simpleType.getArguments().get(0);
                Variance projectionKind = typeProjection.getProjectionKind();
                KotlinType type = typeProjection.getType();
                Intrinsics.checkNotNullExpressionValue(type, "componentTypeProjection.type");
                List listListOf = CollectionsKt.listOf(new TypeProjectionImpl(projectionKind, eraseType(type, javaTypeAttributes)));
                KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
                simpleType = KotlinTypeFactory.simpleType$default(simpleType.getAnnotations(), simpleType.getConstructor(), listListOf, simpleType.isMarkedNullable(), null, 16, null);
            } else if (KotlinTypeKt.isError(simpleType)) {
                simpleType = ErrorUtils.createErrorType(Intrinsics.stringPlus("Raw error type: ", simpleType.getConstructor()));
                Intrinsics.checkNotNullExpressionValue(simpleType, "createErrorType(\"Raw error type: ${type.constructor}\")");
            } else {
                MemberScope memberScope = classDescriptor.getMemberScope(INSTANCE);
                Intrinsics.checkNotNullExpressionValue(memberScope, "declaration.getMemberScope(RawSubstitution)");
                KotlinTypeFactory kotlinTypeFactory2 = KotlinTypeFactory.INSTANCE;
                Annotations annotations = simpleType.getAnnotations();
                TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
                Intrinsics.checkNotNullExpressionValue(typeConstructor, "declaration.typeConstructor");
                List<TypeParameterDescriptor> parameters = classDescriptor.getTypeConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "declaration.typeConstructor.parameters");
                ArrayList arrayList = new ArrayList(CollectionsKt.b(parameters));
                for (TypeParameterDescriptor parameter : parameters) {
                    RawSubstitution rawSubstitution = INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(parameter, "parameter");
                    arrayList.add(computeProjection$default(rawSubstitution, parameter, javaTypeAttributes, null, 4, null));
                }
                simpleType = KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations, typeConstructor, arrayList, simpleType.isMarkedNullable(), memberScope, new Function1<KotlinTypeRefiner, SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawSubstitution.eraseInflexibleBasedOnClassDescriptor.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @Nullable
                    public final SimpleType invoke(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
                        ClassDescriptor classDescriptorFindClassAcrossModuleDependencies;
                        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
                        ClassDescriptor classDescriptor2 = classDescriptor;
                        if (!(classDescriptor2 instanceof ClassDescriptor)) {
                            classDescriptor2 = null;
                        }
                        ClassId classId = classDescriptor2 == null ? null : DescriptorUtilsKt.getClassId(classDescriptor2);
                        if (classId == null || (classDescriptorFindClassAcrossModuleDependencies = kotlinTypeRefiner.findClassAcrossModuleDependencies(classId)) == null || Intrinsics.areEqual(classDescriptorFindClassAcrossModuleDependencies, classDescriptor)) {
                            return null;
                        }
                        return (SimpleType) RawSubstitution.INSTANCE.eraseInflexibleBasedOnClassDescriptor(simpleType, classDescriptorFindClassAcrossModuleDependencies, javaTypeAttributes).getFirst();
                    }
                });
                bool = Boolean.TRUE;
            }
            bool = Boolean.FALSE;
        }
        return TuplesKt.to(simpleType, bool);
    }

    private final KotlinType eraseType(KotlinType kotlinType, JavaTypeAttributes javaTypeAttributes) {
        ClassifierDescriptor classifierDescriptorMo1345getDeclarationDescriptor = kotlinType.getConstructor().mo1345getDeclarationDescriptor();
        if (classifierDescriptorMo1345getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return eraseType(JavaTypeResolverKt.getErasedUpperBound$default((TypeParameterDescriptor) classifierDescriptorMo1345getDeclarationDescriptor, true, javaTypeAttributes, null, 4, null), javaTypeAttributes);
        }
        if (!(classifierDescriptorMo1345getDeclarationDescriptor instanceof ClassDescriptor)) {
            throw new IllegalStateException(Intrinsics.stringPlus("Unexpected declaration kind: ", classifierDescriptorMo1345getDeclarationDescriptor).toString());
        }
        ClassifierDescriptor classifierDescriptorMo1345getDeclarationDescriptor2 = FlexibleTypesKt.upperIfFlexible(kotlinType).getConstructor().mo1345getDeclarationDescriptor();
        if (!(classifierDescriptorMo1345getDeclarationDescriptor2 instanceof ClassDescriptor)) {
            throw new IllegalStateException(("For some reason declaration for upper bound is not a class but \"" + classifierDescriptorMo1345getDeclarationDescriptor2 + "\" while for lower it's \"" + classifierDescriptorMo1345getDeclarationDescriptor + Typography.quote).toString());
        }
        Pair<SimpleType, Boolean> pairEraseInflexibleBasedOnClassDescriptor = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.lowerIfFlexible(kotlinType), (ClassDescriptor) classifierDescriptorMo1345getDeclarationDescriptor, lowerTypeAttr);
        SimpleType simpleTypeComponent1 = pairEraseInflexibleBasedOnClassDescriptor.component1();
        boolean zBooleanValue = pairEraseInflexibleBasedOnClassDescriptor.component2().booleanValue();
        Pair<SimpleType, Boolean> pairEraseInflexibleBasedOnClassDescriptor2 = eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.upperIfFlexible(kotlinType), (ClassDescriptor) classifierDescriptorMo1345getDeclarationDescriptor2, upperTypeAttr);
        SimpleType simpleTypeComponent12 = pairEraseInflexibleBasedOnClassDescriptor2.component1();
        boolean zBooleanValue2 = pairEraseInflexibleBasedOnClassDescriptor2.component2().booleanValue();
        if (zBooleanValue || zBooleanValue2) {
            return new RawTypeImpl(simpleTypeComponent1, simpleTypeComponent12);
        }
        KotlinTypeFactory kotlinTypeFactory = KotlinTypeFactory.INSTANCE;
        return KotlinTypeFactory.flexibleType(simpleTypeComponent1, simpleTypeComponent12);
    }

    public static /* synthetic */ KotlinType eraseType$default(RawSubstitution rawSubstitution, KotlinType kotlinType, JavaTypeAttributes javaTypeAttributes, int i, Object obj) {
        if ((i & 2) != 0) {
            javaTypeAttributes = new JavaTypeAttributes(TypeUsage.COMMON, null, false, null, 14, null);
        }
        return rawSubstitution.eraseType(kotlinType, javaTypeAttributes);
    }

    @NotNull
    public final TypeProjection computeProjection(@NotNull TypeParameterDescriptor parameter, @NotNull JavaTypeAttributes attr, @NotNull KotlinType erasedUpperBound) {
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        Intrinsics.checkNotNullParameter(attr, "attr");
        Intrinsics.checkNotNullParameter(erasedUpperBound, "erasedUpperBound");
        int i = WhenMappings.$EnumSwitchMapping$0[attr.getFlexibility().ordinal()];
        if (i == 1) {
            return new TypeProjectionImpl(Variance.INVARIANT, erasedUpperBound);
        }
        if (i != 2 && i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        if (!parameter.getVariance().getAllowsOutPosition()) {
            return new TypeProjectionImpl(Variance.INVARIANT, DescriptorUtilsKt.getBuiltIns(parameter).getNothingType());
        }
        List<TypeParameterDescriptor> parameters = erasedUpperBound.getConstructor().getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "erasedUpperBound.constructor.parameters");
        return parameters.isEmpty() ^ true ? new TypeProjectionImpl(Variance.OUT_VARIANCE, erasedUpperBound) : JavaTypeResolverKt.makeStarProjection(parameter, attr);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    @NotNull
    /* JADX INFO: renamed from: get */
    public TypeProjectionImpl mo1352get(@NotNull KotlinType key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return new TypeProjectionImpl(eraseType$default(this, key, null, 2, null));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return false;
    }
}
