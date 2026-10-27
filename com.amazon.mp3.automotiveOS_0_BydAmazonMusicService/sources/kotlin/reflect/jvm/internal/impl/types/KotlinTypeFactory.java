package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptorKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.TypeAliasExpansionReportStrategy;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* JADX INFO: compiled from: KotlinTypeFactory.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class KotlinTypeFactory {
    public static final KotlinTypeFactory INSTANCE = new KotlinTypeFactory();
    private static final Function1<KotlinTypeRefiner, SimpleType> EMPTY_REFINED_TYPE_FACTORY = new Function1() { // from class: kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory$EMPTY_REFINED_TYPE_FACTORY$1
        @Override // kotlin.jvm.functions.Function1
        public final Void invoke(KotlinTypeRefiner noName_0) {
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            return null;
        }
    };

    private KotlinTypeFactory() {
    }

    private final MemberScope computeMemberScope(TypeConstructor typeConstructor, List<? extends TypeProjection> list, KotlinTypeRefiner kotlinTypeRefiner) {
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = typeConstructor.mo4084getDeclarationDescriptor();
        if (classifierDescriptorMo4084getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return ((TypeParameterDescriptor) classifierDescriptorMo4084getDeclarationDescriptor).getDefaultType().getMemberScope();
        }
        if (classifierDescriptorMo4084getDeclarationDescriptor instanceof ClassDescriptor) {
            if (kotlinTypeRefiner == null) {
                kotlinTypeRefiner = DescriptorUtilsKt.getKotlinTypeRefiner(DescriptorUtilsKt.getModule(classifierDescriptorMo4084getDeclarationDescriptor));
            }
            if (list.isEmpty()) {
                return ModuleAwareClassDescriptorKt.getRefinedUnsubstitutedMemberScopeIfPossible((ClassDescriptor) classifierDescriptorMo4084getDeclarationDescriptor, kotlinTypeRefiner);
            }
            return ModuleAwareClassDescriptorKt.getRefinedMemberScopeIfPossible((ClassDescriptor) classifierDescriptorMo4084getDeclarationDescriptor, TypeConstructorSubstitution.Companion.create(typeConstructor, list), kotlinTypeRefiner);
        }
        if (classifierDescriptorMo4084getDeclarationDescriptor instanceof TypeAliasDescriptor) {
            MemberScope memberScopeCreateErrorScope = ErrorUtils.createErrorScope(Intrinsics.stringPlus("Scope for abbreviation: ", ((TypeAliasDescriptor) classifierDescriptorMo4084getDeclarationDescriptor).getName()), true);
            Intrinsics.checkNotNullExpressionValue(memberScopeCreateErrorScope, "createErrorScope(\"Scope …{descriptor.name}\", true)");
            return memberScopeCreateErrorScope;
        }
        if (typeConstructor instanceof IntersectionTypeConstructor) {
            return ((IntersectionTypeConstructor) typeConstructor).createScopeForKotlinType();
        }
        throw new IllegalStateException("Unsupported classifier: " + classifierDescriptorMo4084getDeclarationDescriptor + " for constructor: " + typeConstructor);
    }

    public static /* synthetic */ SimpleType simpleType$default(Annotations annotations, TypeConstructor typeConstructor, List list, boolean z, KotlinTypeRefiner kotlinTypeRefiner, int i, Object obj) {
        if ((i & 16) != 0) {
            kotlinTypeRefiner = null;
        }
        return simpleType(annotations, typeConstructor, (List<? extends TypeProjection>) list, z, kotlinTypeRefiner);
    }

    @JvmStatic
    @JvmOverloads
    public static final SimpleType simpleType(final Annotations annotations, final TypeConstructor constructor, final List<? extends TypeProjection> arguments, final boolean z, KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        if (annotations.isEmpty() && arguments.isEmpty() && !z && constructor.mo4084getDeclarationDescriptor() != null) {
            ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = constructor.mo4084getDeclarationDescriptor();
            Intrinsics.checkNotNull(classifierDescriptorMo4084getDeclarationDescriptor);
            SimpleType defaultType = classifierDescriptorMo4084getDeclarationDescriptor.getDefaultType();
            Intrinsics.checkNotNullExpressionValue(defaultType, "constructor.declarationDescriptor!!.defaultType");
            return defaultType;
        }
        return simpleTypeWithNonTrivialMemberScope(annotations, constructor, arguments, z, INSTANCE.computeMemberScope(constructor, arguments, kotlinTypeRefiner), new Function1<KotlinTypeRefiner, SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.simpleType.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final SimpleType invoke(KotlinTypeRefiner refiner) {
                Intrinsics.checkNotNullParameter(refiner, "refiner");
                ExpandedTypeOrRefinedConstructor expandedTypeOrRefinedConstructorRefineConstructor = KotlinTypeFactory.INSTANCE.refineConstructor(constructor, refiner, arguments);
                if (expandedTypeOrRefinedConstructorRefineConstructor == null) {
                    return null;
                }
                SimpleType expandedType = expandedTypeOrRefinedConstructorRefineConstructor.getExpandedType();
                if (expandedType != null) {
                    return expandedType;
                }
                Annotations annotations2 = annotations;
                TypeConstructor refinedConstructor = expandedTypeOrRefinedConstructorRefineConstructor.getRefinedConstructor();
                Intrinsics.checkNotNull(refinedConstructor);
                return KotlinTypeFactory.simpleType(annotations2, refinedConstructor, arguments, z, refiner);
            }
        });
    }

    @JvmStatic
    public static final SimpleType computeExpandedType(TypeAliasDescriptor typeAliasDescriptor, List<? extends TypeProjection> arguments) {
        Intrinsics.checkNotNullParameter(typeAliasDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        return new TypeAliasExpander(TypeAliasExpansionReportStrategy.DO_NOTHING.INSTANCE, false).expand(TypeAliasExpansion.Companion.create(null, typeAliasDescriptor, arguments), Annotations.Companion.getEMPTY());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ExpandedTypeOrRefinedConstructor refineConstructor(TypeConstructor typeConstructor, KotlinTypeRefiner kotlinTypeRefiner, List<? extends TypeProjection> list) {
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = typeConstructor.mo4084getDeclarationDescriptor();
        ClassifierDescriptor classifierDescriptorRefineDescriptor = classifierDescriptorMo4084getDeclarationDescriptor == null ? null : kotlinTypeRefiner.refineDescriptor(classifierDescriptorMo4084getDeclarationDescriptor);
        if (classifierDescriptorRefineDescriptor == null) {
            return null;
        }
        if (classifierDescriptorRefineDescriptor instanceof TypeAliasDescriptor) {
            return new ExpandedTypeOrRefinedConstructor(computeExpandedType((TypeAliasDescriptor) classifierDescriptorRefineDescriptor, list), null);
        }
        TypeConstructor typeConstructorRefine = classifierDescriptorRefineDescriptor.getTypeConstructor().refine(kotlinTypeRefiner);
        Intrinsics.checkNotNullExpressionValue(typeConstructorRefine, "descriptor.typeConstruct…refine(kotlinTypeRefiner)");
        return new ExpandedTypeOrRefinedConstructor(null, typeConstructorRefine);
    }

    /* JADX INFO: compiled from: KotlinTypeFactory.kt */
    private static final class ExpandedTypeOrRefinedConstructor {
        private final SimpleType expandedType;
        private final TypeConstructor refinedConstructor;

        public ExpandedTypeOrRefinedConstructor(SimpleType simpleType, TypeConstructor typeConstructor) {
            this.expandedType = simpleType;
            this.refinedConstructor = typeConstructor;
        }

        public final SimpleType getExpandedType() {
            return this.expandedType;
        }

        public final TypeConstructor getRefinedConstructor() {
            return this.refinedConstructor;
        }
    }

    @JvmStatic
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(final Annotations annotations, final TypeConstructor constructor, final List<? extends TypeProjection> arguments, final boolean z, final MemberScope memberScope) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(memberScope, "memberScope");
        SimpleTypeImpl simpleTypeImpl = new SimpleTypeImpl(constructor, arguments, z, memberScope, new Function1<KotlinTypeRefiner, SimpleType>() { // from class: kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final SimpleType invoke(KotlinTypeRefiner kotlinTypeRefiner) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
                ExpandedTypeOrRefinedConstructor expandedTypeOrRefinedConstructorRefineConstructor = KotlinTypeFactory.INSTANCE.refineConstructor(constructor, kotlinTypeRefiner, arguments);
                if (expandedTypeOrRefinedConstructorRefineConstructor == null) {
                    return null;
                }
                SimpleType expandedType = expandedTypeOrRefinedConstructorRefineConstructor.getExpandedType();
                if (expandedType != null) {
                    return expandedType;
                }
                Annotations annotations2 = annotations;
                TypeConstructor refinedConstructor = expandedTypeOrRefinedConstructorRefineConstructor.getRefinedConstructor();
                Intrinsics.checkNotNull(refinedConstructor);
                return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(annotations2, refinedConstructor, arguments, z, memberScope);
            }
        });
        return annotations.isEmpty() ? simpleTypeImpl : new AnnotatedSimpleType(simpleTypeImpl, annotations);
    }

    @JvmStatic
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(Annotations annotations, TypeConstructor constructor, List<? extends TypeProjection> arguments, boolean z, MemberScope memberScope, Function1<? super KotlinTypeRefiner, ? extends SimpleType> refinedTypeFactory) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(memberScope, "memberScope");
        Intrinsics.checkNotNullParameter(refinedTypeFactory, "refinedTypeFactory");
        SimpleTypeImpl simpleTypeImpl = new SimpleTypeImpl(constructor, arguments, z, memberScope, refinedTypeFactory);
        return annotations.isEmpty() ? simpleTypeImpl : new AnnotatedSimpleType(simpleTypeImpl, annotations);
    }

    @JvmStatic
    public static final SimpleType simpleNotNullType(Annotations annotations, ClassDescriptor descriptor, List<? extends TypeProjection> arguments) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        TypeConstructor typeConstructor = descriptor.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "descriptor.typeConstructor");
        return simpleType$default(annotations, typeConstructor, (List) arguments, false, (KotlinTypeRefiner) null, 16, (Object) null);
    }

    public static /* synthetic */ SimpleType simpleType$default(SimpleType simpleType, Annotations annotations, TypeConstructor typeConstructor, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            annotations = simpleType.getAnnotations();
        }
        if ((i & 4) != 0) {
            typeConstructor = simpleType.getConstructor();
        }
        if ((i & 8) != 0) {
            list = simpleType.getArguments();
        }
        if ((i & 16) != 0) {
            z = simpleType.isMarkedNullable();
        }
        return simpleType(simpleType, annotations, typeConstructor, (List<? extends TypeProjection>) list, z);
    }

    @JvmStatic
    public static final SimpleType simpleType(SimpleType baseType, Annotations annotations, TypeConstructor constructor, List<? extends TypeProjection> arguments, boolean z) {
        Intrinsics.checkNotNullParameter(baseType, "baseType");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        return simpleType$default(annotations, constructor, arguments, z, (KotlinTypeRefiner) null, 16, (Object) null);
    }

    @JvmStatic
    public static final UnwrappedType flexibleType(SimpleType lowerBound, SimpleType upperBound) {
        Intrinsics.checkNotNullParameter(lowerBound, "lowerBound");
        Intrinsics.checkNotNullParameter(upperBound, "upperBound");
        return Intrinsics.areEqual(lowerBound, upperBound) ? lowerBound : new FlexibleTypeImpl(lowerBound, upperBound);
    }

    @JvmStatic
    public static final SimpleType integerLiteralType(Annotations annotations, IntegerLiteralTypeConstructor constructor, boolean z) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        MemberScope memberScopeCreateErrorScope = ErrorUtils.createErrorScope("Scope for integer literal type", true);
        Intrinsics.checkNotNullExpressionValue(memberScopeCreateErrorScope, "createErrorScope(\"Scope …eger literal type\", true)");
        return simpleTypeWithNonTrivialMemberScope(annotations, constructor, listEmptyList, z, memberScopeCreateErrorScope);
    }
}
