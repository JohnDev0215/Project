package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.MappingUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.InlineClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.ScopesHolderForClass;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNamesUtilKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.InnerClassesScopeWrapper;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

/* JADX INFO: compiled from: LazyJavaClassDescriptor.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class LazyJavaClassDescriptor extends ClassDescriptorBase implements JavaClassDescriptor {
    public static final Companion Companion = new Companion(null);
    private static final Set<String> PUBLIC_METHOD_NAMES_IN_OBJECT = SetsKt__SetsKt.setOf((Object[]) new String[]{"equals", "hashCode", "getClass", "wait", "notify", "notifyAll", "toString"});
    private final ClassDescriptor additionalSupertypeClassDescriptor;
    private final Annotations annotations;
    private final LazyJavaResolverContext c;
    private final NotNullLazyValue<List<TypeParameterDescriptor>> declaredParameters;
    private final InnerClassesScopeWrapper innerClassesScope;
    private final boolean isInner;
    private final JavaClass jClass;
    private final ClassKind kind;
    private final Modality modality;
    private final Lazy moduleAnnotations$delegate;
    private final LazyJavaResolverContext outerContext;
    private final ScopesHolderForClass<LazyJavaClassMemberScope> scopeHolder;
    private final LazyJavaStaticClassScope staticScope;
    private final LazyJavaClassTypeConstructor typeConstructor;
    private final LazyJavaClassMemberScope unsubstitutedMemberScope;
    private final Visibility visibility;

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    /* JADX INFO: renamed from: getCompanionObjectDescriptor */
    public ClassDescriptor mo4076getCompanionObjectDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public InlineClassRepresentation<SimpleType> getInlineClassRepresentation() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    /* JADX INFO: renamed from: getUnsubstitutedPrimaryConstructor */
    public ClassConstructorDescriptor mo4077getUnsubstitutedPrimaryConstructor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isData() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isFun() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isInline() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        return false;
    }

    public /* synthetic */ LazyJavaClassDescriptor(LazyJavaResolverContext lazyJavaResolverContext, DeclarationDescriptor declarationDescriptor, JavaClass javaClass, ClassDescriptor classDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lazyJavaResolverContext, declarationDescriptor, javaClass, (i & 8) != 0 ? null : classDescriptor);
    }

    public final LazyJavaResolverContext getOuterContext() {
        return this.outerContext;
    }

    public final JavaClass getJClass() {
        return this.jClass;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaClassDescriptor(LazyJavaResolverContext outerContext, DeclarationDescriptor containingDeclaration, JavaClass jClass, ClassDescriptor classDescriptor) {
        ClassKind classKind;
        Modality modalityConvertFromFlags;
        super(outerContext.getStorageManager(), containingDeclaration, jClass.getName(), outerContext.getComponents().getSourceElementFactory().source(jClass), false);
        Intrinsics.checkNotNullParameter(outerContext, "outerContext");
        Intrinsics.checkNotNullParameter(containingDeclaration, "containingDeclaration");
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.outerContext = outerContext;
        this.jClass = jClass;
        this.additionalSupertypeClassDescriptor = classDescriptor;
        LazyJavaResolverContext lazyJavaResolverContextChildForClassOrPackage$default = ContextKt.childForClassOrPackage$default(outerContext, this, jClass, 0, 4, null);
        this.c = lazyJavaResolverContextChildForClassOrPackage$default;
        lazyJavaResolverContextChildForClassOrPackage$default.getComponents().getJavaResolverCache().recordClass(jClass, this);
        jClass.getLightClassOriginKind();
        this.moduleAnnotations$delegate = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends JavaAnnotation>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor$moduleAnnotations$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends JavaAnnotation> invoke() {
                ClassId classId = DescriptorUtilsKt.getClassId(this.this$0);
                if (classId == null) {
                    return null;
                }
                return this.this$0.getOuterContext().getComponents().getJavaModuleResolver().getAnnotationsForModuleOwnerOfClass(classId);
            }
        });
        if (jClass.isAnnotationType()) {
            classKind = ClassKind.ANNOTATION_CLASS;
        } else if (jClass.isInterface()) {
            classKind = ClassKind.INTERFACE;
        } else {
            classKind = jClass.isEnum() ? ClassKind.ENUM_CLASS : ClassKind.CLASS;
        }
        this.kind = classKind;
        if (jClass.isAnnotationType() || jClass.isEnum()) {
            modalityConvertFromFlags = Modality.FINAL;
        } else {
            modalityConvertFromFlags = Modality.Companion.convertFromFlags(jClass.isSealed(), jClass.isSealed() || jClass.isAbstract() || jClass.isInterface(), !jClass.isFinal());
        }
        this.modality = modalityConvertFromFlags;
        this.visibility = jClass.getVisibility();
        this.isInner = (jClass.getOuterClass() == null || jClass.isStatic()) ? false : true;
        this.typeConstructor = new LazyJavaClassTypeConstructor(this);
        LazyJavaClassMemberScope lazyJavaClassMemberScope = new LazyJavaClassMemberScope(lazyJavaResolverContextChildForClassOrPackage$default, this, jClass, classDescriptor != null, null, 16, null);
        this.unsubstitutedMemberScope = lazyJavaClassMemberScope;
        this.scopeHolder = ScopesHolderForClass.Companion.create(this, lazyJavaResolverContextChildForClassOrPackage$default.getStorageManager(), lazyJavaResolverContextChildForClassOrPackage$default.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner(), new Function1<KotlinTypeRefiner, LazyJavaClassMemberScope>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor$scopeHolder$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LazyJavaClassMemberScope invoke(KotlinTypeRefiner it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LazyJavaResolverContext lazyJavaResolverContext = this.this$0.c;
                LazyJavaClassDescriptor lazyJavaClassDescriptor = this.this$0;
                return new LazyJavaClassMemberScope(lazyJavaResolverContext, lazyJavaClassDescriptor, lazyJavaClassDescriptor.getJClass(), this.this$0.additionalSupertypeClassDescriptor != null, this.this$0.unsubstitutedMemberScope);
            }
        });
        this.innerClassesScope = new InnerClassesScopeWrapper(lazyJavaClassMemberScope);
        this.staticScope = new LazyJavaStaticClassScope(lazyJavaResolverContextChildForClassOrPackage$default, jClass, this);
        this.annotations = LazyJavaAnnotationsKt.resolveAnnotations(lazyJavaResolverContextChildForClassOrPackage$default, jClass);
        this.declaredParameters = lazyJavaResolverContextChildForClassOrPackage$default.getStorageManager().createLazyValue(new Function0<List<? extends TypeParameterDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor$declaredParameters$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends TypeParameterDescriptor> invoke() {
                List<JavaTypeParameter> typeParameters = this.this$0.getJClass().getTypeParameters();
                LazyJavaClassDescriptor lazyJavaClassDescriptor = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeParameters, 10));
                for (JavaTypeParameter javaTypeParameter : typeParameters) {
                    TypeParameterDescriptor typeParameterDescriptorResolveTypeParameter = lazyJavaClassDescriptor.c.getTypeParameterResolver().resolveTypeParameter(javaTypeParameter);
                    if (typeParameterDescriptorResolveTypeParameter == null) {
                        throw new AssertionError("Parameter " + javaTypeParameter + " surely belongs to class " + lazyJavaClassDescriptor.getJClass() + ", so it must be resolved");
                    }
                    arrayList.add(typeParameterDescriptorResolveTypeParameter);
                }
                return arrayList;
            }
        });
    }

    /* JADX INFO: compiled from: LazyJavaClassDescriptor.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final List<JavaAnnotation> getModuleAnnotations() {
        return (List) this.moduleAnnotations$delegate.getValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassKind getKind() {
        return this.kind;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public Modality getModality() {
        return this.modality;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public DescriptorVisibility getVisibility() {
        if (Intrinsics.areEqual(this.visibility, DescriptorVisibilities.PRIVATE) && this.jClass.getOuterClass() == null) {
            DescriptorVisibility descriptorVisibility = JavaDescriptorVisibilities.PACKAGE_VISIBILITY;
            Intrinsics.checkNotNullExpressionValue(descriptorVisibility, "{\n            JavaDescri…KAGE_VISIBILITY\n        }");
            return descriptorVisibility;
        }
        return UtilsKt.toDescriptorVisibility(this.visibility);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        return this.isInner;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public LazyJavaClassMemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return (LazyJavaClassMemberScope) this.scopeHolder.getScope(kotlinTypeRefiner);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getUnsubstitutedInnerClassesScope() {
        return this.innerClassesScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getStaticScope() {
        return this.staticScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public LazyJavaClassMemberScope getUnsubstitutedMemberScope() {
        return (LazyJavaClassMemberScope) super.getUnsubstitutedMemberScope();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List<ClassConstructorDescriptor> getConstructors() {
        return this.unsubstitutedMemberScope.getConstructors$descriptors_jvm().invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return this.annotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        return this.declaredParameters.invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection<ClassDescriptor> getSealedSubclasses() {
        if (this.modality == Modality.SEALED) {
            JavaTypeAttributes attributes$default = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 3, null);
            Collection<JavaClassifierType> permittedTypes = this.jClass.getPermittedTypes();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = permittedTypes.iterator();
            while (it.hasNext()) {
                ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = this.c.getTypeResolver().transformJavaType((JavaClassifierType) it.next(), attributes$default).getConstructor().mo4084getDeclarationDescriptor();
                ClassDescriptor classDescriptor = classifierDescriptorMo4084getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo4084getDeclarationDescriptor : null;
                if (classDescriptor != null) {
                    arrayList.add(classDescriptor);
                }
            }
            return arrayList;
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    public String toString() {
        return Intrinsics.stringPlus("Lazy Java class ", DescriptorUtilsKt.getFqNameUnsafe(this));
    }

    /* JADX INFO: compiled from: LazyJavaClassDescriptor.kt */
    private final class LazyJavaClassTypeConstructor extends AbstractClassTypeConstructor {
        private final NotNullLazyValue<List<TypeParameterDescriptor>> parameters;
        final /* synthetic */ LazyJavaClassDescriptor this$0;

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LazyJavaClassTypeConstructor(final LazyJavaClassDescriptor this$0) {
            super(this$0.c.getStorageManager());
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
            this.parameters = this$0.c.getStorageManager().createLazyValue(new Function0<List<? extends TypeParameterDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor$LazyJavaClassTypeConstructor$parameters$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final List<? extends TypeParameterDescriptor> invoke() {
                    return TypeParameterUtilsKt.computeConstructorTypeParameters(this$0);
                }
            });
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public List<TypeParameterDescriptor> getParameters() {
            return this.parameters.invoke();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected Collection<KotlinType> computeSupertypes() {
            Collection<JavaClassifierType> supertypes = this.this$0.getJClass().getSupertypes();
            ArrayList arrayList = new ArrayList(supertypes.size());
            ArrayList arrayList2 = new ArrayList(0);
            KotlinType purelyImplementedSupertype = getPurelyImplementedSupertype();
            Iterator<JavaClassifierType> it = supertypes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                JavaClassifierType next = it.next();
                KotlinType kotlinTypeEnhanceSuperType = this.this$0.c.getComponents().getSignatureEnhancement().enhanceSuperType(this.this$0.c.getTypeResolver().transformJavaType(next, JavaTypeResolverKt.toAttributes$default(TypeUsage.SUPERTYPE, false, null, 3, null)), this.this$0.c);
                if (kotlinTypeEnhanceSuperType.getConstructor().mo4084getDeclarationDescriptor() instanceof NotFoundClasses.MockClassDescriptor) {
                    arrayList2.add(next);
                }
                if (!Intrinsics.areEqual(kotlinTypeEnhanceSuperType.getConstructor(), purelyImplementedSupertype != null ? purelyImplementedSupertype.getConstructor() : null) && !KotlinBuiltIns.isAnyOrNullableAny(kotlinTypeEnhanceSuperType)) {
                    arrayList.add(kotlinTypeEnhanceSuperType);
                }
            }
            ClassDescriptor classDescriptor = this.this$0.additionalSupertypeClassDescriptor;
            CollectionsKt.addIfNotNull(arrayList, classDescriptor != null ? MappingUtilKt.createMappedTypeParametersSubstitution(classDescriptor, this.this$0).buildSubstitutor().substitute(classDescriptor.getDefaultType(), Variance.INVARIANT) : null);
            CollectionsKt.addIfNotNull(arrayList, purelyImplementedSupertype);
            if (!arrayList2.isEmpty()) {
                ErrorReporter errorReporter = this.this$0.c.getComponents().getErrorReporter();
                ClassDescriptor classDescriptorMo4084getDeclarationDescriptor = mo4084getDeclarationDescriptor();
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(((JavaClassifierType) ((JavaType) it2.next())).getPresentableText());
                }
                errorReporter.reportIncompleteHierarchy(classDescriptorMo4084getDeclarationDescriptor, arrayList3);
            }
            return arrayList.isEmpty() ^ true ? CollectionsKt___CollectionsKt.toList(arrayList) : CollectionsKt__CollectionsJVMKt.listOf(this.this$0.c.getModule().getBuiltIns().getAnyType());
        }

        /* JADX WARN: Removed duplicated region for block: B:4:0x0008  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final kotlin.reflect.jvm.internal.impl.types.KotlinType getPurelyImplementedSupertype() {
            /*
                Method dump skipped, instruction units count: 211
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor.LazyJavaClassTypeConstructor.getPurelyImplementedSupertype():kotlin.reflect.jvm.internal.impl.types.KotlinType");
        }

        private final FqName getPurelyImplementsFqNameFromAnnotation() {
            Annotations annotations = this.this$0.getAnnotations();
            FqName PURELY_IMPLEMENTS_ANNOTATION = JvmAnnotationNames.PURELY_IMPLEMENTS_ANNOTATION;
            Intrinsics.checkNotNullExpressionValue(PURELY_IMPLEMENTS_ANNOTATION, "PURELY_IMPLEMENTS_ANNOTATION");
            AnnotationDescriptor annotationDescriptorMo4078findAnnotation = annotations.mo4078findAnnotation(PURELY_IMPLEMENTS_ANNOTATION);
            if (annotationDescriptorMo4078findAnnotation == null) {
                return null;
            }
            Object objSingleOrNull = CollectionsKt___CollectionsKt.singleOrNull(annotationDescriptorMo4078findAnnotation.getAllValueArguments().values());
            StringValue stringValue = objSingleOrNull instanceof StringValue ? (StringValue) objSingleOrNull : null;
            if (stringValue == null) {
                return null;
            }
            String value = stringValue.getValue();
            if (FqNamesUtilKt.isValidJavaFqName(value)) {
                return new FqName(value);
            }
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected SupertypeLoopChecker getSupertypeLoopChecker() {
            return this.this$0.c.getComponents().getSupertypeLoopChecker();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor, kotlin.reflect.jvm.internal.impl.types.ClassifierBasedTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        /* JADX INFO: renamed from: getDeclarationDescriptor */
        public ClassDescriptor mo4084getDeclarationDescriptor() {
            return this.this$0;
        }

        public String toString() {
            String strAsString = this.this$0.getName().asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
            return strAsString;
        }
    }

    public final LazyJavaClassDescriptor copy$descriptors_jvm(JavaResolverCache javaResolverCache, ClassDescriptor classDescriptor) {
        Intrinsics.checkNotNullParameter(javaResolverCache, "javaResolverCache");
        LazyJavaResolverContext lazyJavaResolverContext = this.c;
        LazyJavaResolverContext lazyJavaResolverContextReplaceComponents = ContextKt.replaceComponents(lazyJavaResolverContext, lazyJavaResolverContext.getComponents().replace(javaResolverCache));
        DeclarationDescriptor containingDeclaration = getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "containingDeclaration");
        return new LazyJavaClassDescriptor(lazyJavaResolverContextReplaceComponents, containingDeclaration, this.jClass, classDescriptor);
    }
}
