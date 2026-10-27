package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeEnhancementState;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt;
import kotlin.reflect.jvm.internal.impl.load.java.ReportLevel;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: signatureEnhancement.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class SignatureEnhancement {
    private final AnnotationTypeQualifierResolver annotationTypeQualifierResolver;
    private final JavaTypeEnhancementState javaTypeEnhancementState;
    private final JavaTypeEnhancement typeEnhancement;

    public SignatureEnhancement(AnnotationTypeQualifierResolver annotationTypeQualifierResolver, JavaTypeEnhancementState javaTypeEnhancementState, JavaTypeEnhancement typeEnhancement) {
        Intrinsics.checkNotNullParameter(annotationTypeQualifierResolver, "annotationTypeQualifierResolver");
        Intrinsics.checkNotNullParameter(javaTypeEnhancementState, "javaTypeEnhancementState");
        Intrinsics.checkNotNullParameter(typeEnhancement, "typeEnhancement");
        this.annotationTypeQualifierResolver = annotationTypeQualifierResolver;
        this.javaTypeEnhancementState = javaTypeEnhancementState;
        this.typeEnhancement = typeEnhancement;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final NullabilityQualifierWithMigrationStatus extractNullabilityTypeFromArgument(AnnotationDescriptor annotationDescriptor, boolean z) {
        ConstantValue<?> constantValueFirstArgument = DescriptorUtilsKt.firstArgument(annotationDescriptor);
        EnumValue enumValue = constantValueFirstArgument instanceof EnumValue ? (EnumValue) constantValueFirstArgument : null;
        if (enumValue == null) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, z);
        }
        String strAsString = enumValue.getEnumEntryName().asString();
        switch (strAsString.hashCode()) {
            case 73135176:
                if (!strAsString.equals("MAYBE")) {
                    return null;
                }
                break;
            case 74175084:
                if (!strAsString.equals("NEVER")) {
                    return null;
                }
                break;
            case 433141802:
                if (strAsString.equals("UNKNOWN")) {
                    return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.FORCE_FLEXIBILITY, z);
                }
                return null;
            case 1933739535:
                if (strAsString.equals("ALWAYS")) {
                    return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, z);
                }
                return null;
            default:
                return null;
        }
        return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, z);
    }

    public final NullabilityQualifierWithMigrationStatus extractNullability(AnnotationDescriptor annotationDescriptor, boolean z, boolean z2) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations;
        Intrinsics.checkNotNullParameter(annotationDescriptor, "annotationDescriptor");
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations2 = extractNullabilityFromKnownAnnotations(annotationDescriptor, z, z2);
        if (nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations2 != null) {
            return nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations2;
        }
        AnnotationDescriptor annotationDescriptorResolveTypeQualifierAnnotation = this.annotationTypeQualifierResolver.resolveTypeQualifierAnnotation(annotationDescriptor);
        if (annotationDescriptorResolveTypeQualifierAnnotation == null) {
            return null;
        }
        ReportLevel reportLevelResolveJsr305AnnotationState = this.annotationTypeQualifierResolver.resolveJsr305AnnotationState(annotationDescriptor);
        if (reportLevelResolveJsr305AnnotationState.isIgnore() || (nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations = extractNullabilityFromKnownAnnotations(annotationDescriptorResolveTypeQualifierAnnotation, z, z2)) == null) {
            return null;
        }
        return NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatusExtractNullabilityFromKnownAnnotations, null, reportLevelResolveJsr305AnnotationState.isWarning(), 1, null);
    }

    private final NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations(AnnotationDescriptor annotationDescriptor, boolean z, boolean z2) {
        FqName fqName = annotationDescriptor.getFqName();
        if (fqName == null) {
            return null;
        }
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusCommonMigrationStatus = commonMigrationStatus(fqName, annotationDescriptor, (annotationDescriptor instanceof LazyJavaAnnotationDescriptor) && (((LazyJavaAnnotationDescriptor) annotationDescriptor).isFreshlySupportedTypeUseAnnotation() || z2) && !z);
        if (nullabilityQualifierWithMigrationStatusCommonMigrationStatus == null) {
            return null;
        }
        return (!nullabilityQualifierWithMigrationStatusCommonMigrationStatus.isForWarningOnly() && (annotationDescriptor instanceof PossiblyExternalAnnotationDescriptor) && ((PossiblyExternalAnnotationDescriptor) annotationDescriptor).isIdeExternalAnnotation()) ? NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatusCommonMigrationStatus, null, true, 1, null) : nullabilityQualifierWithMigrationStatusCommonMigrationStatus;
    }

    private final NullabilityQualifierWithMigrationStatus commonMigrationStatus(FqName fqName, AnnotationDescriptor annotationDescriptor, boolean z) {
        ReportLevel reportLevelInvoke = this.javaTypeEnhancementState.getGetReportLevelForAnnotation().invoke(fqName);
        if (reportLevelInvoke.isIgnore()) {
            return null;
        }
        boolean z2 = reportLevelInvoke.isWarning() || z;
        if (JvmAnnotationNamesKt.getNULLABLE_ANNOTATIONS().contains(fqName)) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, z2);
        }
        if (JvmAnnotationNamesKt.getNOT_NULL_ANNOTATIONS().contains(fqName)) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, z2);
        }
        if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getJSPECIFY_NULLABLE())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, z2);
        }
        if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getJSPECIFY_NULLNESS_UNKNOWN())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.FORCE_FLEXIBILITY, z2);
        }
        if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION())) {
            return extractNullabilityTypeFromArgument(annotationDescriptor, z2);
        }
        if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getCOMPATQUAL_NULLABLE_ANNOTATION())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, z2);
        }
        if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getCOMPATQUAL_NONNULL_ANNOTATION())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, z2);
        }
        if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NON_NULL_ANNOTATION())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, z2);
        }
        if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NULLABLE_ANNOTATION())) {
            return new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, z2);
        }
        return null;
    }

    private final <D extends CallableMemberDescriptor> Annotations getDefaultAnnotations(D d, LazyJavaResolverContext lazyJavaResolverContext) {
        ClassifierDescriptor topLevelContainingClassifier = DescriptorUtilKt.getTopLevelContainingClassifier(d);
        if (topLevelContainingClassifier == null) {
            return d.getAnnotations();
        }
        LazyJavaClassDescriptor lazyJavaClassDescriptor = topLevelContainingClassifier instanceof LazyJavaClassDescriptor ? (LazyJavaClassDescriptor) topLevelContainingClassifier : null;
        List<JavaAnnotation> moduleAnnotations = lazyJavaClassDescriptor != null ? lazyJavaClassDescriptor.getModuleAnnotations() : null;
        if (moduleAnnotations == null || moduleAnnotations.isEmpty()) {
            return d.getAnnotations();
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(moduleAnnotations, 10));
        Iterator<T> it = moduleAnnotations.iterator();
        while (it.hasNext()) {
            arrayList.add(new LazyJavaAnnotationDescriptor(lazyJavaResolverContext, (JavaAnnotation) it.next(), true));
        }
        return Annotations.Companion.create(CollectionsKt___CollectionsKt.plus((Iterable) d.getAnnotations(), (Iterable) arrayList));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0191  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final <D extends kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor> D enhanceSignature(D r18, kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r19) {
        /*
            Method dump skipped, instruction units count: 554
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature(kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext):kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor");
    }

    public final KotlinType enhanceSuperType(KotlinType type, LazyJavaResolverContext context) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        return SignatureParts.enhance$default(new SignatureParts(null, type, CollectionsKt__CollectionsKt.emptyList(), false, context, AnnotationQualifierApplicabilityType.TYPE_USE, false, true, 64, null), null, false, 3, null).getType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: signatureEnhancement.kt */
    final class SignatureParts {
        private final AnnotationQualifierApplicabilityType containerApplicabilityType;
        private final LazyJavaResolverContext containerContext;
        private final Collection<KotlinType> fromOverridden;
        private final KotlinType fromOverride;
        private final boolean isCovariant;
        private final boolean isSuperTypesEnhancement;
        private final Annotated typeContainer;
        private final boolean typeParameterBounds;

        /* JADX WARN: Multi-variable type inference failed */
        public SignatureParts(SignatureEnhancement this$0, Annotated annotated, KotlinType fromOverride, Collection<? extends KotlinType> fromOverridden, boolean z, LazyJavaResolverContext containerContext, AnnotationQualifierApplicabilityType containerApplicabilityType, boolean z2, boolean z3) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(fromOverride, "fromOverride");
            Intrinsics.checkNotNullParameter(fromOverridden, "fromOverridden");
            Intrinsics.checkNotNullParameter(containerContext, "containerContext");
            Intrinsics.checkNotNullParameter(containerApplicabilityType, "containerApplicabilityType");
            SignatureEnhancement.this = this$0;
            this.typeContainer = annotated;
            this.fromOverride = fromOverride;
            this.fromOverridden = fromOverridden;
            this.isCovariant = z;
            this.containerContext = containerContext;
            this.containerApplicabilityType = containerApplicabilityType;
            this.typeParameterBounds = z2;
            this.isSuperTypesEnhancement = z3;
        }

        public /* synthetic */ SignatureParts(Annotated annotated, KotlinType kotlinType, Collection collection, boolean z, LazyJavaResolverContext lazyJavaResolverContext, AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(SignatureEnhancement.this, annotated, kotlinType, collection, z, lazyJavaResolverContext, annotationQualifierApplicabilityType, (i & 64) != 0 ? false : z2, (i & 128) != 0 ? false : z3);
        }

        private final boolean isForVarargParameter() {
            Annotated annotated = this.typeContainer;
            if (!(annotated instanceof ValueParameterDescriptor)) {
                annotated = null;
            }
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) annotated;
            return (valueParameterDescriptor != null ? valueParameterDescriptor.getVarargElementType() : null) != null;
        }

        public static /* synthetic */ PartEnhancementResult enhance$default(SignatureParts signatureParts, TypeEnhancementInfo typeEnhancementInfo, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                typeEnhancementInfo = null;
            }
            if ((i & 2) != 0) {
                z = false;
            }
            return signatureParts.enhance(typeEnhancementInfo, z);
        }

        public final PartEnhancementResult enhance(final TypeEnhancementInfo typeEnhancementInfo, boolean z) {
            boolean zContains;
            final Function1<Integer, JavaTypeQualifiers> function1ComputeIndexedQualifiersForOverride = computeIndexedQualifiersForOverride(z);
            Function1<Integer, JavaTypeQualifiers> function1 = typeEnhancementInfo == null ? null : new Function1<Integer, JavaTypeQualifiers>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$enhance$qualifiersWithPredefined$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ JavaTypeQualifiers invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final JavaTypeQualifiers invoke(int i) {
                    JavaTypeQualifiers javaTypeQualifiers = typeEnhancementInfo.getMap().get(Integer.valueOf(i));
                    return javaTypeQualifiers == null ? function1ComputeIndexedQualifiersForOverride.invoke(Integer.valueOf(i)) : javaTypeQualifiers;
                }
            };
            if (this.isSuperTypesEnhancement) {
                zContains = TypeUtils.containsStoppingAt(this.fromOverride, SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1.INSTANCE, new Function1<KotlinType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$enhance$containsFunctionN$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(KotlinType kotlinType) {
                        return Boolean.valueOf(kotlinType instanceof RawType);
                    }
                });
            } else {
                zContains = TypeUtils.contains(this.fromOverride, SignatureEnhancement$SignatureParts$enhance$containsFunctionN$3.INSTANCE);
            }
            JavaTypeEnhancement javaTypeEnhancement = SignatureEnhancement.this.typeEnhancement;
            KotlinType kotlinType = this.fromOverride;
            if (function1 != null) {
                function1ComputeIndexedQualifiersForOverride = function1;
            }
            KotlinType kotlinTypeEnhance = javaTypeEnhancement.enhance(kotlinType, function1ComputeIndexedQualifiersForOverride, this.isSuperTypesEnhancement);
            if (kotlinTypeEnhance != null) {
                return new PartEnhancementResult(kotlinTypeEnhance, true, zContains);
            }
            return new PartEnhancementResult(this.fromOverride, false, zContains);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean enhance$containsFunctionN(UnwrappedType unwrappedType) {
            ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = unwrappedType.getConstructor().mo4084getDeclarationDescriptor();
            if (classifierDescriptorMo4084getDeclarationDescriptor == null) {
                return false;
            }
            Name name = classifierDescriptorMo4084getDeclarationDescriptor.getName();
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            return Intrinsics.areEqual(name, javaToKotlinClassMap.getFUNCTION_N_FQ_NAME().shortName()) && Intrinsics.areEqual(DescriptorUtilsKt.fqNameOrNull(classifierDescriptorMo4084getDeclarationDescriptor), javaToKotlinClassMap.getFUNCTION_N_FQ_NAME());
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x004b  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0068  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers extractQualifiers(kotlin.reflect.jvm.internal.impl.types.KotlinType r12) {
            /*
                r11 = this;
                boolean r0 = kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt.isFlexible(r12)
                if (r0 == 0) goto L18
                kotlin.reflect.jvm.internal.impl.types.FlexibleType r0 = kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt.asFlexibleType(r12)
                kotlin.Pair r1 = new kotlin.Pair
                kotlin.reflect.jvm.internal.impl.types.SimpleType r2 = r0.getLowerBound()
                kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = r0.getUpperBound()
                r1.<init>(r2, r0)
                goto L1d
            L18:
                kotlin.Pair r1 = new kotlin.Pair
                r1.<init>(r12, r12)
            L1d:
                java.lang.Object r0 = r1.component1()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
                java.lang.Object r1 = r1.component2()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r1
                kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper r2 = kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMapper.INSTANCE
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r10 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers
                boolean r3 = r0.isMarkedNullable()
                r4 = 0
                if (r3 == 0) goto L38
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE
            L36:
                r5 = r3
                goto L42
            L38:
                boolean r3 = r1.isMarkedNullable()
                if (r3 != 0) goto L41
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
                goto L36
            L41:
                r5 = r4
            L42:
                boolean r0 = r2.isReadOnly(r0)
                if (r0 == 0) goto L4b
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r0 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier.READ_ONLY
                goto L55
            L4b:
                boolean r0 = r2.isMutable(r1)
                if (r0 == 0) goto L54
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r0 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier.MUTABLE
                goto L55
            L54:
                r0 = r4
            L55:
                kotlin.reflect.jvm.internal.impl.types.UnwrappedType r1 = r12.unwrap()
                boolean r1 = r1 instanceof kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NotNullTypeParameter
                if (r1 != 0) goto L68
                kotlin.reflect.jvm.internal.impl.types.UnwrappedType r12 = r12.unwrap()
                boolean r12 = r12 instanceof kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType
                if (r12 == 0) goto L66
                goto L68
            L66:
                r12 = 0
                goto L69
            L68:
                r12 = 1
            L69:
                r6 = r12
                r7 = 0
                r8 = 8
                r9 = 0
                r3 = r10
                r4 = r5
                r5 = r0
                r3.<init>(r4, r5, r6, r7, r8, r9)
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.extractQualifiers(kotlin.reflect.jvm.internal.impl.types.KotlinType):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers");
        }

        /* JADX WARN: Removed duplicated region for block: B:52:0x00d9  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers extractQualifiersFromAnnotations(kotlin.reflect.jvm.internal.impl.types.KotlinType r11, boolean r12, kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers r13, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r14, boolean r15) {
            /*
                Method dump skipped, instruction units count: 295
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.extractQualifiersFromAnnotations(kotlin.reflect.jvm.internal.impl.types.KotlinType, boolean, kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor, boolean):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers");
        }

        private static final <T> T extractQualifiersFromAnnotations$uniqueNotNull(T t, T t2) {
            if (t == null || t2 == null || Intrinsics.areEqual(t, t2)) {
                return t == null ? t2 : t;
            }
            return null;
        }

        private final NullabilityQualifierWithMigrationStatus computeNullabilityInfoInTheAbsenceOfExplicitAnnotation(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, JavaDefaultQualifiers javaDefaultQualifiers, TypeParameterDescriptor typeParameterDescriptor) {
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusBoundsNullability;
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus2 = null;
            if (nullabilityQualifierWithMigrationStatus == null) {
                nullabilityQualifierWithMigrationStatus = javaDefaultQualifiers == null ? null : javaDefaultQualifiers.getNullabilityQualifier();
            }
            if (typeParameterDescriptor != null && (nullabilityQualifierWithMigrationStatusBoundsNullability = boundsNullability(typeParameterDescriptor)) != null) {
                if (nullabilityQualifierWithMigrationStatusBoundsNullability.getQualifier() == NullabilityQualifier.NULLABLE) {
                    nullabilityQualifierWithMigrationStatusBoundsNullability = NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatusBoundsNullability, NullabilityQualifier.FORCE_FLEXIBILITY, false, 2, null);
                }
                nullabilityQualifierWithMigrationStatus2 = nullabilityQualifierWithMigrationStatusBoundsNullability;
            }
            return mostSpecific(nullabilityQualifierWithMigrationStatus2, nullabilityQualifierWithMigrationStatus);
        }

        private final NullabilityQualifierWithMigrationStatus mostSpecific(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus2) {
            return nullabilityQualifierWithMigrationStatus == null ? nullabilityQualifierWithMigrationStatus2 : nullabilityQualifierWithMigrationStatus2 == null ? nullabilityQualifierWithMigrationStatus : (!nullabilityQualifierWithMigrationStatus.isForWarningOnly() || nullabilityQualifierWithMigrationStatus2.isForWarningOnly()) ? (nullabilityQualifierWithMigrationStatus.isForWarningOnly() || !nullabilityQualifierWithMigrationStatus2.isForWarningOnly()) ? (nullabilityQualifierWithMigrationStatus.getQualifier().compareTo(nullabilityQualifierWithMigrationStatus2.getQualifier()) >= 0 && nullabilityQualifierWithMigrationStatus.getQualifier().compareTo(nullabilityQualifierWithMigrationStatus2.getQualifier()) > 0) ? nullabilityQualifierWithMigrationStatus : nullabilityQualifierWithMigrationStatus2 : nullabilityQualifierWithMigrationStatus : nullabilityQualifierWithMigrationStatus2;
        }

        private final Pair<NullabilityQualifierWithMigrationStatus, Boolean> nullabilityInfoBoundsForTypeParameterUsage(KotlinType kotlinType) {
            ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = kotlinType.getConstructor().mo4084getDeclarationDescriptor();
            TypeParameterDescriptor typeParameterDescriptor = classifierDescriptorMo4084getDeclarationDescriptor instanceof TypeParameterDescriptor ? (TypeParameterDescriptor) classifierDescriptorMo4084getDeclarationDescriptor : null;
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusBoundsNullability = typeParameterDescriptor == null ? null : boundsNullability(typeParameterDescriptor);
            if (nullabilityQualifierWithMigrationStatusBoundsNullability == null) {
                return new Pair<>(null, Boolean.FALSE);
            }
            NullabilityQualifier nullabilityQualifier = NullabilityQualifier.NOT_NULL;
            return new Pair<>(new NullabilityQualifierWithMigrationStatus(nullabilityQualifier, nullabilityQualifierWithMigrationStatusBoundsNullability.isForWarningOnly()), Boolean.valueOf(nullabilityQualifierWithMigrationStatusBoundsNullability.getQualifier() == nullabilityQualifier));
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0039  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus boundsNullability(kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r8) {
            /*
                Method dump skipped, instruction units count: 297
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.boundsNullability(kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus");
        }

        private final NullabilityQualifierWithMigrationStatus extractNullability(Annotations annotations, boolean z, boolean z2) {
            SignatureEnhancement signatureEnhancement = SignatureEnhancement.this;
            Iterator<AnnotationDescriptor> it = annotations.iterator();
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus = null;
            while (it.hasNext()) {
                NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullability = signatureEnhancement.extractNullability(it.next(), z, z2);
                if (nullabilityQualifierWithMigrationStatus != null) {
                    if (nullabilityQualifierWithMigrationStatusExtractNullability != null && !Intrinsics.areEqual(nullabilityQualifierWithMigrationStatusExtractNullability, nullabilityQualifierWithMigrationStatus) && (!nullabilityQualifierWithMigrationStatusExtractNullability.isForWarningOnly() || nullabilityQualifierWithMigrationStatus.isForWarningOnly())) {
                        if (nullabilityQualifierWithMigrationStatusExtractNullability.isForWarningOnly() || !nullabilityQualifierWithMigrationStatus.isForWarningOnly()) {
                            return null;
                        }
                    }
                }
                nullabilityQualifierWithMigrationStatus = nullabilityQualifierWithMigrationStatusExtractNullability;
            }
            return nullabilityQualifierWithMigrationStatus;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0060  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers> computeIndexedQualifiersForOverride(boolean r18) {
            /*
                Method dump skipped, instruction units count: 202
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.computeIndexedQualifiersForOverride(boolean):kotlin.jvm.functions.Function1");
        }

        private final List<TypeAndDefaultQualifiers> toIndexed(KotlinType kotlinType) {
            ArrayList arrayList = new ArrayList(1);
            toIndexed$add(this, arrayList, kotlinType, this.containerContext, null);
            return arrayList;
        }

        private static final void toIndexed$add(SignatureParts signatureParts, ArrayList<TypeAndDefaultQualifiers> arrayList, KotlinType kotlinType, LazyJavaResolverContext lazyJavaResolverContext, TypeParameterDescriptor typeParameterDescriptor) {
            AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType;
            JavaDefaultQualifiers javaDefaultQualifiers;
            LazyJavaResolverContext lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, kotlinType.getAnnotations());
            JavaTypeQualifiersByElementType defaultTypeQualifiers = lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
            if (defaultTypeQualifiers == null) {
                javaDefaultQualifiers = null;
            } else {
                if (signatureParts.typeParameterBounds) {
                    annotationQualifierApplicabilityType = AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS;
                } else {
                    annotationQualifierApplicabilityType = AnnotationQualifierApplicabilityType.TYPE_USE;
                }
                javaDefaultQualifiers = defaultTypeQualifiers.get(annotationQualifierApplicabilityType);
            }
            arrayList.add(new TypeAndDefaultQualifiers(kotlinType, javaDefaultQualifiers, typeParameterDescriptor, false));
            if (signatureParts.isSuperTypesEnhancement && (kotlinType instanceof RawType)) {
                return;
            }
            List<TypeProjection> arguments = kotlinType.getArguments();
            List<TypeParameterDescriptor> parameters = kotlinType.getConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "type.constructor.parameters");
            for (Pair pair : CollectionsKt___CollectionsKt.zip(arguments, parameters)) {
                TypeProjection typeProjection = (TypeProjection) pair.component1();
                TypeParameterDescriptor typeParameterDescriptor2 = (TypeParameterDescriptor) pair.component2();
                if (typeProjection.isStarProjection()) {
                    KotlinType type = typeProjection.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "arg.type");
                    arrayList.add(new TypeAndDefaultQualifiers(type, javaDefaultQualifiers, typeParameterDescriptor2, true));
                } else {
                    KotlinType type2 = typeProjection.getType();
                    Intrinsics.checkNotNullExpressionValue(type2, "arg.type");
                    toIndexed$add(signatureParts, arrayList, type2, lazyJavaResolverContextCopyWithNewDefaultTypeQualifiers, typeParameterDescriptor2);
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:73:0x0129  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x012b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers computeQualifiersForOverride(kotlin.reflect.jvm.internal.impl.types.KotlinType r16, java.util.Collection<? extends kotlin.reflect.jvm.internal.impl.types.KotlinType> r17, kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers r18, boolean r19, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r20, boolean r21, boolean r22) {
            /*
                Method dump skipped, instruction units count: 322
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.computeQualifiersForOverride(kotlin.reflect.jvm.internal.impl.types.KotlinType, java.util.Collection, kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers, boolean, kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor, boolean, boolean):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers");
        }

        private static final <T> T extractQualifiersFromAnnotations$ifPresent(List<FqName> list, Annotations annotations, T t) {
            boolean z = true;
            if ((list instanceof Collection) && list.isEmpty()) {
                z = false;
            } else {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (annotations.mo4078findAnnotation((FqName) it.next()) != null) {
                        break;
                    }
                }
                z = false;
            }
            if (z) {
                return t;
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: signatureEnhancement.kt */
    private static class PartEnhancementResult {
        private final boolean containsFunctionN;
        private final KotlinType type;
        private final boolean wereChanges;

        public PartEnhancementResult(KotlinType type, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.type = type;
            this.wereChanges = z;
            this.containsFunctionN = z2;
        }

        public final KotlinType getType() {
            return this.type;
        }

        public final boolean getWereChanges() {
            return this.wereChanges;
        }

        public final boolean getContainsFunctionN() {
            return this.containsFunctionN;
        }
    }

    private final SignatureParts partsForValueParameter(CallableMemberDescriptor callableMemberDescriptor, ValueParameterDescriptor valueParameterDescriptor, LazyJavaResolverContext lazyJavaResolverContext, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        if (valueParameterDescriptor != null) {
            lazyJavaResolverContext = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, valueParameterDescriptor.getAnnotations());
        }
        return parts(callableMemberDescriptor, valueParameterDescriptor, false, lazyJavaResolverContext, AnnotationQualifierApplicabilityType.VALUE_PARAMETER, function1);
    }

    private final SignatureParts parts(CallableMemberDescriptor callableMemberDescriptor, Annotated annotated, boolean z, LazyJavaResolverContext lazyJavaResolverContext, AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        KotlinType kotlinTypeInvoke = function1.invoke(callableMemberDescriptor);
        Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "this.overriddenDescriptors");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(overriddenDescriptors, 10));
        for (CallableMemberDescriptor it : overriddenDescriptors) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            arrayList.add(function1.invoke(it));
        }
        return new SignatureParts(annotated, kotlinTypeInvoke, arrayList, z, ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, function1.invoke(callableMemberDescriptor).getAnnotations()), annotationQualifierApplicabilityType, false, false, 192, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <D extends CallableMemberDescriptor> Collection<D> enhanceSignatures(LazyJavaResolverContext c, Collection<? extends D> platformSignatures) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(platformSignatures, "platformSignatures");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(platformSignatures, 10));
        Iterator<T> it = platformSignatures.iterator();
        while (it.hasNext()) {
            arrayList.add(enhanceSignature((CallableMemberDescriptor) it.next(), c));
        }
        return arrayList;
    }

    public final List<KotlinType> enhanceTypeParameterBounds(TypeParameterDescriptor typeParameter, List<? extends KotlinType> bounds, LazyJavaResolverContext context) {
        Iterator it;
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(bounds, 10));
        Iterator it2 = bounds.iterator();
        while (it2.hasNext()) {
            KotlinType type = (KotlinType) it2.next();
            if (TypeUtilsKt.contains(type, new Function1<UnwrappedType, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$enhanceTypeParameterBounds$1$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(UnwrappedType it3) {
                    Intrinsics.checkNotNullParameter(it3, "it");
                    return Boolean.valueOf(it3 instanceof RawType);
                }
            })) {
                it = it2;
            } else {
                it = it2;
                type = SignatureParts.enhance$default(new SignatureParts(typeParameter, type, CollectionsKt__CollectionsKt.emptyList(), false, context, AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS, true, false, 128, null), null, false, 3, null).getType();
            }
            arrayList.add(type);
            it2 = it;
        }
        return arrayList;
    }
}
