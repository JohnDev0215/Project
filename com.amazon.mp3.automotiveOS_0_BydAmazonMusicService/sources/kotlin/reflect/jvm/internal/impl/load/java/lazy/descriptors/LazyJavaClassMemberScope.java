package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithDifferentJvmName;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature;
import kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.JavaIncompatibilityRulesOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator;
import kotlin.reflect.jvm.internal.impl.load.java.components.TypeUsage;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaForKotlinOverridePropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.ValueParameterData;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeResolverKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaRecordComponent;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

/* JADX INFO: compiled from: LazyJavaClassMemberScope.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class LazyJavaClassMemberScope extends LazyJavaScope {
    private final NotNullLazyValue<List<ClassConstructorDescriptor>> constructors;
    private final NotNullLazyValue<Map<Name, JavaField>> enumEntryIndex;
    private final JavaClass jClass;
    private final NotNullLazyValue<Set<Name>> nestedClassIndex;
    private final MemoizedFunctionToNullable<Name, ClassDescriptorBase> nestedClasses;
    private final ClassDescriptor ownerDescriptor;
    private final boolean skipRefinement;

    public /* synthetic */ LazyJavaClassMemberScope(LazyJavaResolverContext lazyJavaResolverContext, ClassDescriptor classDescriptor, JavaClass javaClass, boolean z, LazyJavaClassMemberScope lazyJavaClassMemberScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lazyJavaResolverContext, classDescriptor, javaClass, z, (i & 16) != 0 ? null : lazyJavaClassMemberScope);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public /* bridge */ /* synthetic */ Set computeFunctionNames(DescriptorKindFilter descriptorKindFilter, Function1 function1) {
        return computeFunctionNames(descriptorKindFilter, (Function1<? super Name, Boolean>) function1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public ClassDescriptor getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaClassMemberScope(final LazyJavaResolverContext c, ClassDescriptor ownerDescriptor, JavaClass jClass, boolean z, LazyJavaClassMemberScope lazyJavaClassMemberScope) {
        super(c, lazyJavaClassMemberScope);
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(ownerDescriptor, "ownerDescriptor");
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.ownerDescriptor = ownerDescriptor;
        this.jClass = jClass;
        this.skipRefinement = z;
        this.constructors = c.getStorageManager().createLazyValue(new Function0<List<? extends ClassConstructorDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$constructors$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Removed duplicated region for block: B:17:0x006b  */
            @Override // kotlin.jvm.functions.Function0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.util.List<? extends kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor> invoke() {
                /*
                    r9 = this;
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r0 = r9.this$0
                    kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass r0 = kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.access$getJClass$p(r0)
                    java.util.Collection r0 = r0.getConstructors()
                    java.util.ArrayList r1 = new java.util.ArrayList
                    int r2 = r0.size()
                    r1.<init>(r2)
                    java.util.Iterator r0 = r0.iterator()
                L17:
                    boolean r2 = r0.hasNext()
                    if (r2 == 0) goto L2d
                    java.lang.Object r2 = r0.next()
                    kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor r2 = (kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor) r2
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r3 = r9.this$0
                    kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor r2 = kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.access$resolveConstructor(r3, r2)
                    r1.add(r2)
                    goto L17
                L2d:
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r0 = r9.this$0
                    kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass r0 = kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.access$getJClass$p(r0)
                    boolean r0 = r0.isRecord()
                    if (r0 == 0) goto L81
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r0 = r9.this$0
                    kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r0 = kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.access$createDefaultRecordConstructor(r0)
                    r2 = 0
                    r3 = 2
                    r4 = 0
                    java.lang.String r5 = kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt.computeJvmDescriptor$default(r0, r2, r2, r3, r4)
                    boolean r6 = r1.isEmpty()
                    r7 = 1
                    if (r6 == 0) goto L4f
                L4d:
                    r2 = r7
                    goto L69
                L4f:
                    java.util.Iterator r6 = r1.iterator()
                L53:
                    boolean r8 = r6.hasNext()
                    if (r8 == 0) goto L4d
                    java.lang.Object r8 = r6.next()
                    kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r8 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor) r8
                    java.lang.String r8 = kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt.computeJvmDescriptor$default(r8, r2, r2, r3, r4)
                    boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r5)
                    if (r8 == 0) goto L53
                L69:
                    if (r2 == 0) goto L81
                    r1.add(r0)
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r2 = r2
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r2 = r2.getComponents()
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache r2 = r2.getJavaResolverCache()
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r3 = r9.this$0
                    kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass r3 = kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.access$getJClass$p(r3)
                    r2.recordConstructor(r3, r0)
                L81:
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r0 = r2
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r0 = r0.getComponents()
                    kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider r0 = r0.getSyntheticPartsProvider()
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r2 = r9.this$0
                    kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r2 = r2.getOwnerDescriptor()
                    r0.generateConstructors(r2, r1)
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r0 = r2
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents r0 = r0.getComponents()
                    kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement r0 = r0.getSignatureEnhancement()
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r2 = r2
                    kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope r3 = r9.this$0
                    boolean r4 = r1.isEmpty()
                    if (r4 == 0) goto Lb0
                    kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r1 = kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.access$createDefaultConstructor(r3)
                    java.util.List r1 = kotlin.collections.CollectionsKt.listOfNotNull(r1)
                Lb0:
                    java.util.Collection r0 = r0.enhanceSignatures(r2, r1)
                    java.util.List r0 = kotlin.collections.CollectionsKt.toList(r0)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$constructors$1.invoke():java.util.List");
            }
        });
        this.nestedClassIndex = c.getStorageManager().createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$nestedClassIndex$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Set<? extends Name> invoke() {
                return CollectionsKt___CollectionsKt.toSet(this.this$0.jClass.getInnerClassNames());
            }
        });
        this.enumEntryIndex = c.getStorageManager().createLazyValue(new Function0<Map<Name, ? extends JavaField>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$enumEntryIndex$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<Name, ? extends JavaField> invoke() {
                Collection<JavaField> fields = this.this$0.jClass.getFields();
                ArrayList arrayList = new ArrayList();
                for (Object obj : fields) {
                    if (((JavaField) obj).isEnumEntry()) {
                        arrayList.add(obj);
                    }
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10)), 16));
                for (Object obj2 : arrayList) {
                    linkedHashMap.put(((JavaField) obj2).getName(), obj2);
                }
                return linkedHashMap;
            }
        });
        this.nestedClasses = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<Name, ClassDescriptorBase>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$nestedClasses$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ClassDescriptorBase invoke(Name name) {
                Intrinsics.checkNotNullParameter(name, "name");
                if (!((Set) this.this$0.nestedClassIndex.invoke()).contains(name)) {
                    JavaField javaField = (JavaField) ((Map) this.this$0.enumEntryIndex.invoke()).get(name);
                    if (javaField == null) {
                        return null;
                    }
                    StorageManager storageManager = c.getStorageManager();
                    final LazyJavaClassMemberScope lazyJavaClassMemberScope2 = this.this$0;
                    return EnumEntrySyntheticClassDescriptor.create(c.getStorageManager(), this.this$0.getOwnerDescriptor(), name, storageManager.createLazyValue(new Function0<Set<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$nestedClasses$1$enumMemberNames$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Set<? extends Name> invoke() {
                            return SetsKt___SetsKt.plus((Set) lazyJavaClassMemberScope2.getFunctionNames(), (Iterable) lazyJavaClassMemberScope2.getVariableNames());
                        }
                    }), LazyJavaAnnotationsKt.resolveAnnotations(c, javaField), c.getComponents().getSourceElementFactory().source(javaField));
                }
                JavaClassFinder finder = c.getComponents().getFinder();
                ClassId classId = DescriptorUtilsKt.getClassId(this.this$0.getOwnerDescriptor());
                Intrinsics.checkNotNull(classId);
                ClassId classIdCreateNestedClassId = classId.createNestedClassId(name);
                Intrinsics.checkNotNullExpressionValue(classIdCreateNestedClassId, "ownerDescriptor.classId!…createNestedClassId(name)");
                JavaClass javaClassFindClass = finder.findClass(new JavaClassFinder.Request(classIdCreateNestedClassId, null, this.this$0.jClass, 2, null));
                if (javaClassFindClass == null) {
                    return null;
                }
                LazyJavaResolverContext lazyJavaResolverContext = c;
                LazyJavaClassDescriptor lazyJavaClassDescriptor = new LazyJavaClassDescriptor(lazyJavaResolverContext, this.this$0.getOwnerDescriptor(), javaClassFindClass, null, 8, null);
                lazyJavaResolverContext.getComponents().getJavaClassesTracker().reportClass(lazyJavaClassDescriptor);
                return lazyJavaClassDescriptor;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public ClassDeclaredMemberIndex computeMemberIndex() {
        return new ClassDeclaredMemberIndex(this.jClass, new Function1<JavaMember, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeMemberIndex.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(JavaMember it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(!it.isStatic());
            }
        });
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected LinkedHashSet<Name> computeFunctionNames(DescriptorKindFilter kindFilter, Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Collection<KotlinType> collectionMo4085getSupertypes = getOwnerDescriptor().getTypeConstructor().mo4085getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collectionMo4085getSupertypes, "ownerDescriptor.typeConstructor.supertypes");
        LinkedHashSet<Name> linkedHashSet = new LinkedHashSet<>();
        Iterator<T> it = collectionMo4085getSupertypes.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, ((KotlinType) it.next()).getMemberScope().getFunctionNames());
        }
        linkedHashSet.addAll(getDeclaredMemberIndex().invoke().getMethodNames());
        linkedHashSet.addAll(getDeclaredMemberIndex().invoke().getRecordComponentNames());
        linkedHashSet.addAll(computeClassNames(kindFilter, function1));
        linkedHashSet.addAll(getC().getComponents().getSyntheticPartsProvider().getMethodNames(getOwnerDescriptor()));
        return linkedHashSet;
    }

    public final NotNullLazyValue<List<ClassConstructorDescriptor>> getConstructors$descriptors_jvm() {
        return this.constructors;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassConstructorDescriptor createDefaultRecordConstructor() {
        ClassDescriptor ownerDescriptor = getOwnerDescriptor();
        JavaClassConstructorDescriptor javaClassConstructorDescriptorCreateJavaConstructor = JavaClassConstructorDescriptor.createJavaConstructor(ownerDescriptor, Annotations.Companion.getEMPTY(), true, getC().getComponents().getSourceElementFactory().source(this.jClass));
        Intrinsics.checkNotNullExpressionValue(javaClassConstructorDescriptorCreateJavaConstructor, "createJavaConstructor(\n ….source(jClass)\n        )");
        List<ValueParameterDescriptor> listCreateRecordConstructorParameters = createRecordConstructorParameters(javaClassConstructorDescriptorCreateJavaConstructor);
        javaClassConstructorDescriptorCreateJavaConstructor.setHasSynthesizedParameterNames(false);
        javaClassConstructorDescriptorCreateJavaConstructor.initialize(listCreateRecordConstructorParameters, getConstructorVisibility(ownerDescriptor));
        javaClassConstructorDescriptorCreateJavaConstructor.setHasStableParameterNames(false);
        javaClassConstructorDescriptorCreateJavaConstructor.setReturnType(ownerDescriptor.getDefaultType());
        return javaClassConstructorDescriptorCreateJavaConstructor;
    }

    private final List<ValueParameterDescriptor> createRecordConstructorParameters(ClassConstructorDescriptorImpl classConstructorDescriptorImpl) {
        Collection<JavaRecordComponent> recordComponents = this.jClass.getRecordComponents();
        ArrayList arrayList = new ArrayList(recordComponents.size());
        KotlinType kotlinType = null;
        JavaTypeAttributes attributes$default = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 2, null);
        int i = 0;
        for (JavaRecordComponent javaRecordComponent : recordComponents) {
            int i2 = i + 1;
            KotlinType kotlinTypeTransformJavaType = getC().getTypeResolver().transformJavaType(javaRecordComponent.getType(), attributes$default);
            arrayList.add(new ValueParameterDescriptorImpl(classConstructorDescriptorImpl, null, i, Annotations.Companion.getEMPTY(), javaRecordComponent.getName(), kotlinTypeTransformJavaType, false, false, false, javaRecordComponent.isVararg() ? getC().getComponents().getModule().getBuiltIns().getArrayElementType(kotlinTypeTransformJavaType) : kotlinType, getC().getComponents().getSourceElementFactory().source(javaRecordComponent)));
            i = i2;
            kotlinType = null;
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected boolean isVisibleAsFunction(JavaMethodDescriptor javaMethodDescriptor) {
        Intrinsics.checkNotNullParameter(javaMethodDescriptor, "<this>");
        if (this.jClass.isAnnotationType()) {
            return false;
        }
        return isVisibleAsFunctionInCurrentClass(javaMethodDescriptor);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0077 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[LOOP:0: B:8:0x001f->B:45:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0074 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[LOOP:1: B:16:0x003f->B:50:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isVisibleAsFunctionInCurrentClass(final kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r7) {
        /*
            r6 = this;
            kotlin.reflect.jvm.internal.impl.name.Name r0 = r7.getName()
            java.lang.String r1 = "function.name"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.util.List r0 = kotlin.reflect.jvm.internal.impl.load.java.PropertiesConventionUtilKt.getPropertyNamesCandidatesByAccessorName(r0)
            boolean r1 = r0 instanceof java.util.Collection
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L1b
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L1b
        L19:
            r0 = r3
            goto L78
        L1b:
            java.util.Iterator r0 = r0.iterator()
        L1f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L19
            java.lang.Object r1 = r0.next()
            kotlin.reflect.jvm.internal.impl.name.Name r1 = (kotlin.reflect.jvm.internal.impl.name.Name) r1
            java.util.Set r1 = r6.getPropertiesFromSupertypes(r1)
            boolean r4 = r1 instanceof java.util.Collection
            if (r4 == 0) goto L3b
            boolean r4 = r1.isEmpty()
            if (r4 == 0) goto L3b
        L39:
            r1 = r3
            goto L75
        L3b:
            java.util.Iterator r1 = r1.iterator()
        L3f:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L39
            java.lang.Object r4 = r1.next()
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor) r4
            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$isVisibleAsFunctionInCurrentClass$1$1$1 r5 = new kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$isVisibleAsFunctionInCurrentClass$1$1$1
            r5.<init>()
            boolean r5 = r6.doesClassOverridesProperty(r4, r5)
            if (r5 == 0) goto L71
            boolean r4 = r4.isVar()
            if (r4 != 0) goto L6f
            kotlin.reflect.jvm.internal.impl.name.Name r4 = r7.getName()
            java.lang.String r4 = r4.asString()
            java.lang.String r5 = "function.name.asString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            boolean r4 = kotlin.reflect.jvm.internal.impl.load.java.JvmAbi.isSetterName(r4)
            if (r4 != 0) goto L71
        L6f:
            r4 = r2
            goto L72
        L71:
            r4 = r3
        L72:
            if (r4 == 0) goto L3f
            r1 = r2
        L75:
            if (r1 == 0) goto L1f
            r0 = r2
        L78:
            if (r0 == 0) goto L7b
            return r3
        L7b:
            boolean r0 = r6.doesOverrideRenamedBuiltins(r7)
            if (r0 != 0) goto L8e
            boolean r0 = r6.shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters(r7)
            if (r0 != 0) goto L8e
            boolean r7 = r6.doesOverrideSuspendFunction(r7)
            if (r7 != 0) goto L8e
            goto L8f
        L8e:
            r2 = r3
        L8f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.isVisibleAsFunctionInCurrentClass(kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor):boolean");
    }

    private final boolean shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters(SimpleFunctionDescriptor simpleFunctionDescriptor) {
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        Name name = simpleFunctionDescriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        if (!builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            return false;
        }
        Name name2 = simpleFunctionDescriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "name");
        Set<SimpleFunctionDescriptor> functionsFromSupertypes = getFunctionsFromSupertypes(name2);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = functionsFromSupertypes.iterator();
        while (it.hasNext()) {
            FunctionDescriptor overriddenBuiltinFunctionWithErasedValueParametersInJava = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava((SimpleFunctionDescriptor) it.next());
            if (overriddenBuiltinFunctionWithErasedValueParametersInJava != null) {
                arrayList.add(overriddenBuiltinFunctionWithErasedValueParametersInJava);
            }
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            if (hasSameJvmDescriptorButDoesNotOverride(simpleFunctionDescriptor, (FunctionDescriptor) it2.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<SimpleFunctionDescriptor> searchMethodsByNameWithoutBuiltinMagic(Name name) {
        Collection<JavaMethod> collectionFindMethodsByName = getDeclaredMemberIndex().invoke().findMethodsByName(name);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(collectionFindMethodsByName, 10));
        Iterator<T> it = collectionFindMethodsByName.iterator();
        while (it.hasNext()) {
            arrayList.add(resolveMethodToFunctionDescriptor((JavaMethod) it.next()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection<SimpleFunctionDescriptor> searchMethodsInSupertypesWithoutBuiltinMagic(Name name) {
        Set<SimpleFunctionDescriptor> functionsFromSupertypes = getFunctionsFromSupertypes(name);
        ArrayList arrayList = new ArrayList();
        for (Object obj : functionsFromSupertypes) {
            SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) obj;
            if (!(SpecialBuiltinMembers.doesOverrideBuiltinWithDifferentJvmName(simpleFunctionDescriptor) || BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(simpleFunctionDescriptor) != null)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean doesOverrideRenamedBuiltins(kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r9) {
        /*
            r8 = this;
            kotlin.reflect.jvm.internal.impl.load.java.SpecialGenericSignatures$Companion r0 = kotlin.reflect.jvm.internal.impl.load.java.SpecialGenericSignatures.Companion
            kotlin.reflect.jvm.internal.impl.name.Name r1 = r9.getName()
            java.lang.String r2 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.util.List r0 = r0.getBuiltinFunctionNamesByJvmName(r1)
            boolean r1 = r0 instanceof java.util.Collection
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L1d
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L1d
        L1b:
            r2 = r3
            goto L7d
        L1d:
            java.util.Iterator r0 = r0.iterator()
        L21:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L1b
            java.lang.Object r1 = r0.next()
            kotlin.reflect.jvm.internal.impl.name.Name r1 = (kotlin.reflect.jvm.internal.impl.name.Name) r1
            java.util.Set r4 = r8.getFunctionsFromSupertypes(r1)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r4 = r4.iterator()
        L3a:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L51
            java.lang.Object r6 = r4.next()
            r7 = r6
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r7 = (kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor) r7
            boolean r7 = kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.doesOverrideBuiltinWithDifferentJvmName(r7)
            if (r7 == 0) goto L3a
            r5.add(r6)
            goto L3a
        L51:
            boolean r4 = r5.isEmpty()
            if (r4 == 0) goto L59
        L57:
            r1 = r3
            goto L7b
        L59:
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r1 = r8.createRenamedCopy(r9, r1)
            boolean r4 = r5.isEmpty()
            if (r4 == 0) goto L64
            goto L57
        L64:
            java.util.Iterator r4 = r5.iterator()
        L68:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L57
            java.lang.Object r5 = r4.next()
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r5 = (kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor) r5
            boolean r5 = r8.doesOverrideRenamedDescriptor(r5, r1)
            if (r5 == 0) goto L68
            r1 = r2
        L7b:
            if (r1 == 0) goto L21
        L7d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.doesOverrideRenamedBuiltins(kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor):boolean");
    }

    private final boolean doesOverrideSuspendFunction(SimpleFunctionDescriptor simpleFunctionDescriptor) {
        SimpleFunctionDescriptor simpleFunctionDescriptorCreateSuspendView = createSuspendView(simpleFunctionDescriptor);
        if (simpleFunctionDescriptorCreateSuspendView == null) {
            return false;
        }
        Name name = simpleFunctionDescriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        Set<SimpleFunctionDescriptor> functionsFromSupertypes = getFunctionsFromSupertypes(name);
        if ((functionsFromSupertypes instanceof Collection) && functionsFromSupertypes.isEmpty()) {
            return false;
        }
        for (SimpleFunctionDescriptor simpleFunctionDescriptor2 : functionsFromSupertypes) {
            if (simpleFunctionDescriptor2.isSuspend() && doesOverride(simpleFunctionDescriptorCreateSuspendView, simpleFunctionDescriptor2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0012  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor createSuspendView(kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r6) {
        /*
            r5 = this;
            java.util.List r0 = r6.getValueParameters()
            java.lang.String r1 = "valueParameters"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.Object r0 = kotlin.collections.CollectionsKt.lastOrNull(r0)
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r0
            r2 = 0
            if (r0 != 0) goto L14
        L12:
            r0 = r2
            goto L3f
        L14:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r3 = r0.getType()
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r3 = r3.getConstructor()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r3 = r3.mo4084getDeclarationDescriptor()
            if (r3 != 0) goto L24
        L22:
            r3 = r2
            goto L37
        L24:
            kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe r3 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.getFqNameUnsafe(r3)
            boolean r4 = r3.isSafe()
            if (r4 == 0) goto L2f
            goto L30
        L2f:
            r3 = r2
        L30:
            if (r3 != 0) goto L33
            goto L22
        L33:
            kotlin.reflect.jvm.internal.impl.name.FqName r3 = r3.toSafe()
        L37:
            kotlin.reflect.jvm.internal.impl.name.FqName r4 = kotlin.reflect.jvm.internal.impl.builtins.StandardNames.CONTINUATION_INTERFACE_FQ_NAME
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L12
        L3f:
            if (r0 != 0) goto L42
            return r2
        L42:
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder r2 = r6.newCopyBuilder()
            java.util.List r6 = r6.getValueParameters()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)
            r1 = 1
            java.util.List r6 = kotlin.collections.CollectionsKt.dropLast(r6, r1)
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder r6 = r2.setValueParameters(r6)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getType()
            java.util.List r0 = r0.getArguments()
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r0 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r0
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getType()
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder r6 = r6.setReturnType(r0)
            kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r6 = r6.build()
            kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor r6 = (kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor) r6
            r0 = r6
            kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl r0 = (kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl) r0
            if (r0 != 0) goto L79
            goto L7c
        L79:
            r0.setSuspend(r1)
        L7c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.createSuspendView(kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor):kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor");
    }

    private final SimpleFunctionDescriptor createRenamedCopy(SimpleFunctionDescriptor simpleFunctionDescriptor, Name name) {
        FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> copyBuilderNewCopyBuilder = simpleFunctionDescriptor.newCopyBuilder();
        copyBuilderNewCopyBuilder.setName(name);
        copyBuilderNewCopyBuilder.setSignatureChange();
        copyBuilderNewCopyBuilder.setPreserveSourceElement();
        FunctionDescriptor functionDescriptorBuild = copyBuilderNewCopyBuilder.build();
        Intrinsics.checkNotNull(functionDescriptorBuild);
        return (SimpleFunctionDescriptor) functionDescriptorBuild;
    }

    private final boolean doesOverrideRenamedDescriptor(SimpleFunctionDescriptor simpleFunctionDescriptor, FunctionDescriptor functionDescriptor) {
        if (BuiltinMethodsWithDifferentJvmName.INSTANCE.isRemoveAtByIndex(simpleFunctionDescriptor)) {
            functionDescriptor = functionDescriptor.getOriginal();
        }
        Intrinsics.checkNotNullExpressionValue(functionDescriptor, "if (superDescriptor.isRe…iginal else subDescriptor");
        return doesOverride(functionDescriptor, simpleFunctionDescriptor);
    }

    private final boolean doesOverride(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2) {
        OverridingUtil.OverrideCompatibilityInfo.Result result = OverridingUtil.DEFAULT.isOverridableByWithoutExternalConditions(callableDescriptor2, callableDescriptor, true).getResult();
        Intrinsics.checkNotNullExpressionValue(result, "DEFAULT.isOverridableByW…iptor, this, true).result");
        return result == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE && !JavaIncompatibilityRulesOverridabilityCondition.Companion.doesJavaOverrideHaveIncompatibleValueParameterKinds(callableDescriptor2, callableDescriptor);
    }

    private final SimpleFunctionDescriptor findGetterOverride(PropertyDescriptor propertyDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        PropertyGetterDescriptor getter = propertyDescriptor.getGetter();
        PropertyGetterDescriptor propertyGetterDescriptor = getter == null ? null : (PropertyGetterDescriptor) SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(getter);
        String builtinSpecialPropertyGetterName = propertyGetterDescriptor != null ? ClassicBuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(propertyGetterDescriptor) : null;
        if (builtinSpecialPropertyGetterName != null && !SpecialBuiltinMembers.hasRealKotlinSuperClassWithOverrideOf(getOwnerDescriptor(), propertyGetterDescriptor)) {
            return findGetterByName(propertyDescriptor, builtinSpecialPropertyGetterName, function1);
        }
        String strAsString = propertyDescriptor.getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
        return findGetterByName(propertyDescriptor, JvmAbi.getterName(strAsString), function1);
    }

    private final SimpleFunctionDescriptor findGetterByName(PropertyDescriptor propertyDescriptor, String str, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        SimpleFunctionDescriptor simpleFunctionDescriptor;
        Name nameIdentifier = Name.identifier(str);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(getterName)");
        Iterator<T> it = function1.invoke(nameIdentifier).iterator();
        do {
            simpleFunctionDescriptor = null;
            if (!it.hasNext()) {
                break;
            }
            SimpleFunctionDescriptor simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) it.next();
            if (simpleFunctionDescriptor2.getValueParameters().size() == 0) {
                KotlinTypeChecker kotlinTypeChecker = KotlinTypeChecker.DEFAULT;
                KotlinType returnType = simpleFunctionDescriptor2.getReturnType();
                if (returnType == null ? false : kotlinTypeChecker.isSubtypeOf(returnType, propertyDescriptor.getType())) {
                    simpleFunctionDescriptor = simpleFunctionDescriptor2;
                }
            }
        } while (simpleFunctionDescriptor == null);
        return simpleFunctionDescriptor;
    }

    private final SimpleFunctionDescriptor findSetterOverride(PropertyDescriptor propertyDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        SimpleFunctionDescriptor simpleFunctionDescriptor;
        KotlinType returnType;
        String strAsString = propertyDescriptor.getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "name.asString()");
        Name nameIdentifier = Name.identifier(JvmAbi.setterName(strAsString));
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(JvmAbi.setterName(name.asString()))");
        Iterator<T> it = function1.invoke(nameIdentifier).iterator();
        do {
            simpleFunctionDescriptor = null;
            if (!it.hasNext()) {
                break;
            }
            SimpleFunctionDescriptor simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) it.next();
            if (simpleFunctionDescriptor2.getValueParameters().size() == 1 && (returnType = simpleFunctionDescriptor2.getReturnType()) != null && KotlinBuiltIns.isUnit(returnType)) {
                KotlinTypeChecker kotlinTypeChecker = KotlinTypeChecker.DEFAULT;
                List<ValueParameterDescriptor> valueParameters = simpleFunctionDescriptor2.getValueParameters();
                Intrinsics.checkNotNullExpressionValue(valueParameters, "descriptor.valueParameters");
                if (kotlinTypeChecker.equalTypes(((ValueParameterDescriptor) CollectionsKt___CollectionsKt.single((List) valueParameters)).getType(), propertyDescriptor.getType())) {
                    simpleFunctionDescriptor = simpleFunctionDescriptor2;
                }
            }
        } while (simpleFunctionDescriptor == null);
        return simpleFunctionDescriptor;
    }

    private final boolean doesClassOverridesProperty(PropertyDescriptor propertyDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        if (JavaDescriptorUtilKt.isJavaField(propertyDescriptor)) {
            return false;
        }
        SimpleFunctionDescriptor simpleFunctionDescriptorFindGetterOverride = findGetterOverride(propertyDescriptor, function1);
        SimpleFunctionDescriptor simpleFunctionDescriptorFindSetterOverride = findSetterOverride(propertyDescriptor, function1);
        if (simpleFunctionDescriptorFindGetterOverride == null) {
            return false;
        }
        if (propertyDescriptor.isVar()) {
            return simpleFunctionDescriptorFindSetterOverride != null && simpleFunctionDescriptorFindSetterOverride.getModality() == simpleFunctionDescriptorFindGetterOverride.getModality();
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void computeNonDeclaredFunctions(java.util.Collection<kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor> r11, kotlin.reflect.jvm.internal.impl.name.Name r12) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeNonDeclaredFunctions(java.util.Collection, kotlin.reflect.jvm.internal.impl.name.Name):void");
    }

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$computeNonDeclaredFunctions$3, reason: invalid class name */
    /* JADX INFO: compiled from: LazyJavaClassMemberScope.kt */
    /* synthetic */ class AnonymousClass3 extends FunctionReference implements Function1<Name, Collection<? extends SimpleFunctionDescriptor>> {
        AnonymousClass3(Object obj) {
            super(1, obj);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "searchMethodsByNameWithoutBuiltinMagic";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(LazyJavaClassMemberScope.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "searchMethodsByNameWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final Collection<SimpleFunctionDescriptor> invoke(Name p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((LazyJavaClassMemberScope) this.receiver).searchMethodsByNameWithoutBuiltinMagic(p0);
        }
    }

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope$computeNonDeclaredFunctions$4, reason: invalid class name */
    /* JADX INFO: compiled from: LazyJavaClassMemberScope.kt */
    /* synthetic */ class AnonymousClass4 extends FunctionReference implements Function1<Name, Collection<? extends SimpleFunctionDescriptor>> {
        AnonymousClass4(Object obj) {
            super(1, obj);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "searchMethodsInSupertypesWithoutBuiltinMagic";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(LazyJavaClassMemberScope.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "searchMethodsInSupertypesWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final Collection<SimpleFunctionDescriptor> invoke(Name p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((LazyJavaClassMemberScope) this.receiver).searchMethodsInSupertypesWithoutBuiltinMagic(p0);
        }
    }

    private final void addFunctionFromSupertypes(Collection<SimpleFunctionDescriptor> collection, Name name, Collection<? extends SimpleFunctionDescriptor> collection2, boolean z) {
        Collection<? extends SimpleFunctionDescriptor> collectionResolveOverridesForNonStaticMembers = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name, collection2, collection, getOwnerDescriptor(), getC().getComponents().getErrorReporter(), getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
        Intrinsics.checkNotNullExpressionValue(collectionResolveOverridesForNonStaticMembers, "resolveOverridesForNonSt….overridingUtil\n        )");
        if (!z) {
            collection.addAll(collectionResolveOverridesForNonStaticMembers);
            return;
        }
        List listPlus = CollectionsKt___CollectionsKt.plus((Collection) collection, (Iterable) collectionResolveOverridesForNonStaticMembers);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(collectionResolveOverridesForNonStaticMembers, 10));
        for (SimpleFunctionDescriptor resolvedOverride : collectionResolveOverridesForNonStaticMembers) {
            SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) SpecialBuiltinMembers.getOverriddenSpecialBuiltin(resolvedOverride);
            if (simpleFunctionDescriptor == null) {
                Intrinsics.checkNotNullExpressionValue(resolvedOverride, "resolvedOverride");
            } else {
                Intrinsics.checkNotNullExpressionValue(resolvedOverride, "resolvedOverride");
                resolvedOverride = createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(resolvedOverride, simpleFunctionDescriptor, listPlus);
            }
            arrayList.add(resolvedOverride);
        }
        collection.addAll(arrayList);
    }

    private final void addOverriddenSpecialMethods(Name name, Collection<? extends SimpleFunctionDescriptor> collection, Collection<? extends SimpleFunctionDescriptor> collection2, Collection<SimpleFunctionDescriptor> collection3, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        for (SimpleFunctionDescriptor simpleFunctionDescriptor : collection2) {
            CollectionsKt.addIfNotNull(collection3, obtainOverrideForBuiltinWithDifferentJvmName(simpleFunctionDescriptor, function1, name, collection));
            CollectionsKt.addIfNotNull(collection3, obtainOverrideForBuiltInWithErasedValueParametersInJava(simpleFunctionDescriptor, function1, collection));
            CollectionsKt.addIfNotNull(collection3, obtainOverrideForSuspend(simpleFunctionDescriptor, function1));
        }
    }

    private final SimpleFunctionDescriptor obtainOverrideForBuiltInWithErasedValueParametersInJava(SimpleFunctionDescriptor simpleFunctionDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1, Collection<? extends SimpleFunctionDescriptor> collection) {
        SimpleFunctionDescriptor simpleFunctionDescriptorCreateOverrideForBuiltinFunctionWithErasedParameterIfNeeded;
        FunctionDescriptor overriddenBuiltinFunctionWithErasedValueParametersInJava = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(simpleFunctionDescriptor);
        if (overriddenBuiltinFunctionWithErasedValueParametersInJava == null || (simpleFunctionDescriptorCreateOverrideForBuiltinFunctionWithErasedParameterIfNeeded = createOverrideForBuiltinFunctionWithErasedParameterIfNeeded(overriddenBuiltinFunctionWithErasedValueParametersInJava, function1)) == null) {
            return null;
        }
        if (!isVisibleAsFunctionInCurrentClass(simpleFunctionDescriptorCreateOverrideForBuiltinFunctionWithErasedParameterIfNeeded)) {
            simpleFunctionDescriptorCreateOverrideForBuiltinFunctionWithErasedParameterIfNeeded = null;
        }
        if (simpleFunctionDescriptorCreateOverrideForBuiltinFunctionWithErasedParameterIfNeeded == null) {
            return null;
        }
        return createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(simpleFunctionDescriptorCreateOverrideForBuiltinFunctionWithErasedParameterIfNeeded, overriddenBuiltinFunctionWithErasedValueParametersInJava, collection);
    }

    private final SimpleFunctionDescriptor obtainOverrideForBuiltinWithDifferentJvmName(SimpleFunctionDescriptor simpleFunctionDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1, Name name, Collection<? extends SimpleFunctionDescriptor> collection) {
        SimpleFunctionDescriptor simpleFunctionDescriptor2 = (SimpleFunctionDescriptor) SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(simpleFunctionDescriptor);
        if (simpleFunctionDescriptor2 == null) {
            return null;
        }
        String jvmMethodNameIfSpecial = SpecialBuiltinMembers.getJvmMethodNameIfSpecial(simpleFunctionDescriptor2);
        Intrinsics.checkNotNull(jvmMethodNameIfSpecial);
        Name nameIdentifier = Name.identifier(jvmMethodNameIfSpecial);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(nameInJava)");
        Iterator<? extends SimpleFunctionDescriptor> it = function1.invoke(nameIdentifier).iterator();
        while (it.hasNext()) {
            SimpleFunctionDescriptor simpleFunctionDescriptorCreateRenamedCopy = createRenamedCopy(it.next(), name);
            if (doesOverrideRenamedDescriptor(simpleFunctionDescriptor2, simpleFunctionDescriptorCreateRenamedCopy)) {
                return createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(simpleFunctionDescriptorCreateRenamedCopy, simpleFunctionDescriptor2, collection);
            }
        }
        return null;
    }

    private final SimpleFunctionDescriptor obtainOverrideForSuspend(SimpleFunctionDescriptor simpleFunctionDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        if (!simpleFunctionDescriptor.isSuspend()) {
            return null;
        }
        Name name = simpleFunctionDescriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "descriptor.name");
        Iterator<T> it = function1.invoke(name).iterator();
        while (it.hasNext()) {
            SimpleFunctionDescriptor simpleFunctionDescriptorCreateSuspendView = createSuspendView((SimpleFunctionDescriptor) it.next());
            if (simpleFunctionDescriptorCreateSuspendView == null || !doesOverride(simpleFunctionDescriptorCreateSuspendView, simpleFunctionDescriptor)) {
                simpleFunctionDescriptorCreateSuspendView = null;
            }
            if (simpleFunctionDescriptorCreateSuspendView != null) {
                return simpleFunctionDescriptorCreateSuspendView;
            }
        }
        return null;
    }

    private final SimpleFunctionDescriptor createOverrideForBuiltinFunctionWithErasedParameterIfNeeded(FunctionDescriptor functionDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        Object next;
        Name name = functionDescriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "overridden.name");
        Iterator<T> it = function1.invoke(name).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (hasSameJvmDescriptorButDoesNotOverride((SimpleFunctionDescriptor) next, functionDescriptor)) {
                break;
            }
        }
        SimpleFunctionDescriptor simpleFunctionDescriptor = (SimpleFunctionDescriptor) next;
        if (simpleFunctionDescriptor == null) {
            return null;
        }
        FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> copyBuilderNewCopyBuilder = simpleFunctionDescriptor.newCopyBuilder();
        List<ValueParameterDescriptor> valueParameters = functionDescriptor.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "overridden.valueParameters");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(valueParameters, 10));
        for (ValueParameterDescriptor valueParameterDescriptor : valueParameters) {
            KotlinType type = valueParameterDescriptor.getType();
            Intrinsics.checkNotNullExpressionValue(type, "it.type");
            arrayList.add(new ValueParameterData(type, valueParameterDescriptor.declaresDefaultValue()));
        }
        List<ValueParameterDescriptor> valueParameters2 = simpleFunctionDescriptor.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters2, "override.valueParameters");
        copyBuilderNewCopyBuilder.setValueParameters(UtilKt.copyValueParameters(arrayList, valueParameters2, functionDescriptor));
        copyBuilderNewCopyBuilder.setSignatureChange();
        copyBuilderNewCopyBuilder.setPreserveSourceElement();
        copyBuilderNewCopyBuilder.putUserData(JavaMethodDescriptor.HAS_ERASED_VALUE_PARAMETERS, Boolean.TRUE);
        return (SimpleFunctionDescriptor) copyBuilderNewCopyBuilder.build();
    }

    private final Set<SimpleFunctionDescriptor> getFunctionsFromSupertypes(Name name) {
        Collection<KotlinType> collectionComputeSupertypes = computeSupertypes();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = collectionComputeSupertypes.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, ((KotlinType) it.next()).getMemberScope().getContributedFunctions(name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS));
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeImplicitlyDeclaredFunctions(Collection<SimpleFunctionDescriptor> result, Name name) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(name, "name");
        if (this.jClass.isRecord() && getDeclaredMemberIndex().invoke().findRecordComponentByName(name) != null) {
            boolean z = true;
            if (!result.isEmpty()) {
                Iterator<T> it = result.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (((SimpleFunctionDescriptor) it.next()).getValueParameters().isEmpty()) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                JavaRecordComponent javaRecordComponentFindRecordComponentByName = getDeclaredMemberIndex().invoke().findRecordComponentByName(name);
                Intrinsics.checkNotNull(javaRecordComponentFindRecordComponentByName);
                result.add(resolveRecordComponentToFunctionDescriptor(javaRecordComponentFindRecordComponentByName));
            }
        }
        getC().getComponents().getSyntheticPartsProvider().generateMethods(getOwnerDescriptor(), name, result);
    }

    private final JavaMethodDescriptor resolveRecordComponentToFunctionDescriptor(JavaRecordComponent javaRecordComponent) {
        JavaMethodDescriptor javaMethodDescriptorCreateJavaMethod = JavaMethodDescriptor.createJavaMethod(getOwnerDescriptor(), LazyJavaAnnotationsKt.resolveAnnotations(getC(), javaRecordComponent), javaRecordComponent.getName(), getC().getComponents().getSourceElementFactory().source(javaRecordComponent), true);
        Intrinsics.checkNotNullExpressionValue(javaMethodDescriptorCreateJavaMethod, "createJavaMethod(\n      …omponent), true\n        )");
        javaMethodDescriptorCreateJavaMethod.initialize(null, getDispatchReceiverParameter(), CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList(), getC().getTypeResolver().transformJavaType(javaRecordComponent.getType(), JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, false, null, 2, null)), Modality.Companion.convertFromFlags(false, false, true), DescriptorVisibilities.PUBLIC, null);
        javaMethodDescriptorCreateJavaMethod.setParameterNamesStatus(false, false);
        getC().getComponents().getJavaResolverCache().recordMethod(javaRecordComponent, javaMethodDescriptorCreateJavaMethod);
        return javaMethodDescriptorCreateJavaMethod;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredProperties(Name name, Collection<PropertyDescriptor> result) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(result, "result");
        if (this.jClass.isAnnotationType()) {
            computeAnnotationProperties(name, result);
        }
        Set<PropertyDescriptor> propertiesFromSupertypes = getPropertiesFromSupertypes(name);
        if (propertiesFromSupertypes.isEmpty()) {
            return;
        }
        SmartSet.Companion companion = SmartSet.Companion;
        SmartSet smartSetCreate = companion.create();
        SmartSet smartSetCreate2 = companion.create();
        addPropertyOverrideByMethod(propertiesFromSupertypes, result, smartSetCreate, new Function1<Name, Collection<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeNonDeclaredProperties.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Collection<SimpleFunctionDescriptor> invoke(Name it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return LazyJavaClassMemberScope.this.searchMethodsByNameWithoutBuiltinMagic(it);
            }
        });
        addPropertyOverrideByMethod(SetsKt___SetsKt.minus((Set) propertiesFromSupertypes, (Iterable) smartSetCreate), smartSetCreate2, null, new Function1<Name, Collection<? extends SimpleFunctionDescriptor>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeNonDeclaredProperties.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Collection<SimpleFunctionDescriptor> invoke(Name it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return LazyJavaClassMemberScope.this.searchMethodsInSupertypesWithoutBuiltinMagic(it);
            }
        });
        Collection<? extends PropertyDescriptor> collectionResolveOverridesForNonStaticMembers = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name, SetsKt___SetsKt.plus((Set) propertiesFromSupertypes, (Iterable) smartSetCreate2), result, getOwnerDescriptor(), getC().getComponents().getErrorReporter(), getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
        Intrinsics.checkNotNullExpressionValue(collectionResolveOverridesForNonStaticMembers, "resolveOverridesForNonSt…rridingUtil\n            )");
        result.addAll(collectionResolveOverridesForNonStaticMembers);
    }

    private final void addPropertyOverrideByMethod(Set<? extends PropertyDescriptor> set, Collection<PropertyDescriptor> collection, Set<PropertyDescriptor> set2, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        for (PropertyDescriptor propertyDescriptor : set) {
            JavaPropertyDescriptor javaPropertyDescriptorCreatePropertyDescriptorByMethods = createPropertyDescriptorByMethods(propertyDescriptor, function1);
            if (javaPropertyDescriptorCreatePropertyDescriptorByMethods != null) {
                collection.add(javaPropertyDescriptorCreatePropertyDescriptorByMethods);
                if (set2 == null) {
                    return;
                }
                set2.add(propertyDescriptor);
                return;
            }
        }
    }

    private final void computeAnnotationProperties(Name name, Collection<PropertyDescriptor> collection) {
        JavaMethod javaMethod = (JavaMethod) CollectionsKt___CollectionsKt.singleOrNull(getDeclaredMemberIndex().invoke().findMethodsByName(name));
        if (javaMethod == null) {
            return;
        }
        collection.add(createPropertyDescriptorWithDefaultGetter$default(this, javaMethod, null, Modality.FINAL, 2, null));
    }

    static /* synthetic */ JavaPropertyDescriptor createPropertyDescriptorWithDefaultGetter$default(LazyJavaClassMemberScope lazyJavaClassMemberScope, JavaMethod javaMethod, KotlinType kotlinType, Modality modality, int i, Object obj) {
        if ((i & 2) != 0) {
            kotlinType = null;
        }
        return lazyJavaClassMemberScope.createPropertyDescriptorWithDefaultGetter(javaMethod, kotlinType, modality);
    }

    private final JavaPropertyDescriptor createPropertyDescriptorWithDefaultGetter(JavaMethod javaMethod, KotlinType kotlinType, Modality modality) {
        JavaPropertyDescriptor javaPropertyDescriptorCreate = JavaPropertyDescriptor.create(getOwnerDescriptor(), LazyJavaAnnotationsKt.resolveAnnotations(getC(), javaMethod), modality, UtilsKt.toDescriptorVisibility(javaMethod.getVisibility()), false, javaMethod.getName(), getC().getComponents().getSourceElementFactory().source(javaMethod), false);
        Intrinsics.checkNotNullExpressionValue(javaPropertyDescriptorCreate, "create(\n            owne…inal = */ false\n        )");
        PropertyGetterDescriptorImpl propertyGetterDescriptorImplCreateDefaultGetter = DescriptorFactory.createDefaultGetter(javaPropertyDescriptorCreate, Annotations.Companion.getEMPTY());
        Intrinsics.checkNotNullExpressionValue(propertyGetterDescriptorImplCreateDefaultGetter, "createDefaultGetter(prop…iptor, Annotations.EMPTY)");
        javaPropertyDescriptorCreate.initialize(propertyGetterDescriptorImplCreateDefaultGetter, null);
        KotlinType kotlinTypeComputeMethodReturnType = kotlinType == null ? computeMethodReturnType(javaMethod, ContextKt.childForMethod$default(getC(), javaPropertyDescriptorCreate, javaMethod, 0, 4, null)) : kotlinType;
        javaPropertyDescriptorCreate.setType(kotlinTypeComputeMethodReturnType, CollectionsKt__CollectionsKt.emptyList(), getDispatchReceiverParameter(), null);
        propertyGetterDescriptorImplCreateDefaultGetter.initialize(kotlinTypeComputeMethodReturnType);
        return javaPropertyDescriptorCreate;
    }

    private final JavaPropertyDescriptor createPropertyDescriptorByMethods(PropertyDescriptor propertyDescriptor, Function1<? super Name, ? extends Collection<? extends SimpleFunctionDescriptor>> function1) {
        SimpleFunctionDescriptor simpleFunctionDescriptorFindSetterOverride;
        PropertySetterDescriptorImpl propertySetterDescriptorImplCreateSetter = null;
        if (!doesClassOverridesProperty(propertyDescriptor, function1)) {
            return null;
        }
        SimpleFunctionDescriptor simpleFunctionDescriptorFindGetterOverride = findGetterOverride(propertyDescriptor, function1);
        Intrinsics.checkNotNull(simpleFunctionDescriptorFindGetterOverride);
        if (propertyDescriptor.isVar()) {
            simpleFunctionDescriptorFindSetterOverride = findSetterOverride(propertyDescriptor, function1);
            Intrinsics.checkNotNull(simpleFunctionDescriptorFindSetterOverride);
        } else {
            simpleFunctionDescriptorFindSetterOverride = null;
        }
        if (simpleFunctionDescriptorFindSetterOverride != null) {
            simpleFunctionDescriptorFindSetterOverride.getModality();
            simpleFunctionDescriptorFindGetterOverride.getModality();
        }
        JavaForKotlinOverridePropertyDescriptor javaForKotlinOverridePropertyDescriptor = new JavaForKotlinOverridePropertyDescriptor(getOwnerDescriptor(), simpleFunctionDescriptorFindGetterOverride, simpleFunctionDescriptorFindSetterOverride, propertyDescriptor);
        KotlinType returnType = simpleFunctionDescriptorFindGetterOverride.getReturnType();
        Intrinsics.checkNotNull(returnType);
        javaForKotlinOverridePropertyDescriptor.setType(returnType, CollectionsKt__CollectionsKt.emptyList(), getDispatchReceiverParameter(), null);
        PropertyGetterDescriptorImpl propertyGetterDescriptorImplCreateGetter = DescriptorFactory.createGetter(javaForKotlinOverridePropertyDescriptor, simpleFunctionDescriptorFindGetterOverride.getAnnotations(), false, false, false, simpleFunctionDescriptorFindGetterOverride.getSource());
        propertyGetterDescriptorImplCreateGetter.setInitialSignatureDescriptor(simpleFunctionDescriptorFindGetterOverride);
        propertyGetterDescriptorImplCreateGetter.initialize(javaForKotlinOverridePropertyDescriptor.getType());
        Intrinsics.checkNotNullExpressionValue(propertyGetterDescriptorImplCreateGetter, "createGetter(\n          …escriptor.type)\n        }");
        if (simpleFunctionDescriptorFindSetterOverride != null) {
            List<ValueParameterDescriptor> valueParameters = simpleFunctionDescriptorFindSetterOverride.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters, "setterMethod.valueParameters");
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) CollectionsKt___CollectionsKt.firstOrNull((List) valueParameters);
            if (valueParameterDescriptor == null) {
                throw new AssertionError(Intrinsics.stringPlus("No parameter found for ", simpleFunctionDescriptorFindSetterOverride));
            }
            propertySetterDescriptorImplCreateSetter = DescriptorFactory.createSetter(javaForKotlinOverridePropertyDescriptor, simpleFunctionDescriptorFindSetterOverride.getAnnotations(), valueParameterDescriptor.getAnnotations(), false, false, false, simpleFunctionDescriptorFindSetterOverride.getVisibility(), simpleFunctionDescriptorFindSetterOverride.getSource());
            propertySetterDescriptorImplCreateSetter.setInitialSignatureDescriptor(simpleFunctionDescriptorFindSetterOverride);
        }
        javaForKotlinOverridePropertyDescriptor.initialize(propertyGetterDescriptorImplCreateGetter, propertySetterDescriptorImplCreateSetter);
        return javaForKotlinOverridePropertyDescriptor;
    }

    private final Set<PropertyDescriptor> getPropertiesFromSupertypes(Name name) {
        Collection<KotlinType> collectionComputeSupertypes = computeSupertypes();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collectionComputeSupertypes.iterator();
        while (it.hasNext()) {
            Collection<? extends PropertyDescriptor> contributedVariables = ((KotlinType) it.next()).getMemberScope().getContributedVariables(name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS);
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(contributedVariables, 10));
            Iterator<T> it2 = contributedVariables.iterator();
            while (it2.hasNext()) {
                arrayList2.add((PropertyDescriptor) it2.next());
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList2);
        }
        return CollectionsKt___CollectionsKt.toSet(arrayList);
    }

    private final Collection<KotlinType> computeSupertypes() {
        if (!this.skipRefinement) {
            return getC().getComponents().getKotlinTypeChecker().getKotlinTypeRefiner().refineSupertypes(getOwnerDescriptor());
        }
        Collection<KotlinType> collectionMo4085getSupertypes = getOwnerDescriptor().getTypeConstructor().mo4085getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collectionMo4085getSupertypes, "ownerDescriptor.typeConstructor.supertypes");
        return collectionMo4085getSupertypes;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected LazyJavaScope.MethodSignatureData resolveMethodSignature(JavaMethod method, List<? extends TypeParameterDescriptor> methodTypeParameters, KotlinType returnType, List<? extends ValueParameterDescriptor> valueParameters) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(methodTypeParameters, "methodTypeParameters");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(valueParameters, "valueParameters");
        SignaturePropagator.PropagatedSignature propagatedSignatureResolvePropagatedSignature = getC().getComponents().getSignaturePropagator().resolvePropagatedSignature(method, getOwnerDescriptor(), returnType, null, valueParameters, methodTypeParameters);
        Intrinsics.checkNotNullExpressionValue(propagatedSignatureResolvePropagatedSignature, "c.components.signaturePr…dTypeParameters\n        )");
        KotlinType returnType2 = propagatedSignatureResolvePropagatedSignature.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType2, "propagated.returnType");
        KotlinType receiverType = propagatedSignatureResolvePropagatedSignature.getReceiverType();
        List<ValueParameterDescriptor> valueParameters2 = propagatedSignatureResolvePropagatedSignature.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters2, "propagated.valueParameters");
        List<TypeParameterDescriptor> typeParameters = propagatedSignatureResolvePropagatedSignature.getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters, "propagated.typeParameters");
        boolean zHasStableParameterNames = propagatedSignatureResolvePropagatedSignature.hasStableParameterNames();
        List<String> errors = propagatedSignatureResolvePropagatedSignature.getErrors();
        Intrinsics.checkNotNullExpressionValue(errors, "propagated.errors");
        return new LazyJavaScope.MethodSignatureData(returnType2, receiverType, valueParameters2, typeParameters, zHasStableParameterNames, errors);
    }

    private final boolean hasSameJvmDescriptorButDoesNotOverride(SimpleFunctionDescriptor simpleFunctionDescriptor, FunctionDescriptor functionDescriptor) {
        String strComputeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor, false, false, 2, null);
        FunctionDescriptor original = functionDescriptor.getOriginal();
        Intrinsics.checkNotNullExpressionValue(original, "builtinWithErasedParameters.original");
        return Intrinsics.areEqual(strComputeJvmDescriptor$default, MethodSignatureMappingKt.computeJvmDescriptor$default(original, false, false, 2, null)) && !doesOverride(simpleFunctionDescriptor, functionDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JavaClassConstructorDescriptor resolveConstructor(JavaConstructor javaConstructor) {
        ClassDescriptor ownerDescriptor = getOwnerDescriptor();
        JavaClassConstructorDescriptor javaClassConstructorDescriptorCreateJavaConstructor = JavaClassConstructorDescriptor.createJavaConstructor(ownerDescriptor, LazyJavaAnnotationsKt.resolveAnnotations(getC(), javaConstructor), false, getC().getComponents().getSourceElementFactory().source(javaConstructor));
        Intrinsics.checkNotNullExpressionValue(javaClassConstructorDescriptorCreateJavaConstructor, "createJavaConstructor(\n …ce(constructor)\n        )");
        LazyJavaResolverContext lazyJavaResolverContextChildForMethod = ContextKt.childForMethod(getC(), javaClassConstructorDescriptorCreateJavaConstructor, javaConstructor, ownerDescriptor.getDeclaredTypeParameters().size());
        LazyJavaScope.ResolvedValueParameters resolvedValueParametersResolveValueParameters = resolveValueParameters(lazyJavaResolverContextChildForMethod, javaClassConstructorDescriptorCreateJavaConstructor, javaConstructor.getValueParameters());
        List<TypeParameterDescriptor> declaredTypeParameters = ownerDescriptor.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(declaredTypeParameters, "classDescriptor.declaredTypeParameters");
        List<JavaTypeParameter> typeParameters = javaConstructor.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            TypeParameterDescriptor typeParameterDescriptorResolveTypeParameter = lazyJavaResolverContextChildForMethod.getTypeParameterResolver().resolveTypeParameter((JavaTypeParameter) it.next());
            Intrinsics.checkNotNull(typeParameterDescriptorResolveTypeParameter);
            arrayList.add(typeParameterDescriptorResolveTypeParameter);
        }
        javaClassConstructorDescriptorCreateJavaConstructor.initialize(resolvedValueParametersResolveValueParameters.getDescriptors(), UtilsKt.toDescriptorVisibility(javaConstructor.getVisibility()), CollectionsKt___CollectionsKt.plus((Collection) declaredTypeParameters, (Iterable) arrayList));
        javaClassConstructorDescriptorCreateJavaConstructor.setHasStableParameterNames(false);
        javaClassConstructorDescriptorCreateJavaConstructor.setHasSynthesizedParameterNames(resolvedValueParametersResolveValueParameters.getHasSynthesizedNames());
        javaClassConstructorDescriptorCreateJavaConstructor.setReturnType(ownerDescriptor.getDefaultType());
        lazyJavaResolverContextChildForMethod.getComponents().getJavaResolverCache().recordConstructor(javaConstructor, javaClassConstructorDescriptorCreateJavaConstructor);
        return javaClassConstructorDescriptorCreateJavaConstructor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassConstructorDescriptor createDefaultConstructor() {
        List<ValueParameterDescriptor> listEmptyList;
        boolean zIsAnnotationType = this.jClass.isAnnotationType();
        if ((this.jClass.isInterface() || !this.jClass.hasDefaultConstructor()) && !zIsAnnotationType) {
            return null;
        }
        ClassDescriptor ownerDescriptor = getOwnerDescriptor();
        JavaClassConstructorDescriptor javaClassConstructorDescriptorCreateJavaConstructor = JavaClassConstructorDescriptor.createJavaConstructor(ownerDescriptor, Annotations.Companion.getEMPTY(), true, getC().getComponents().getSourceElementFactory().source(this.jClass));
        Intrinsics.checkNotNullExpressionValue(javaClassConstructorDescriptorCreateJavaConstructor, "createJavaConstructor(\n ….source(jClass)\n        )");
        if (zIsAnnotationType) {
            listEmptyList = createAnnotationConstructorParameters(javaClassConstructorDescriptorCreateJavaConstructor);
        } else {
            listEmptyList = Collections.emptyList();
        }
        javaClassConstructorDescriptorCreateJavaConstructor.setHasSynthesizedParameterNames(false);
        javaClassConstructorDescriptorCreateJavaConstructor.initialize(listEmptyList, getConstructorVisibility(ownerDescriptor));
        javaClassConstructorDescriptorCreateJavaConstructor.setHasStableParameterNames(true);
        javaClassConstructorDescriptorCreateJavaConstructor.setReturnType(ownerDescriptor.getDefaultType());
        getC().getComponents().getJavaResolverCache().recordConstructor(this.jClass, javaClassConstructorDescriptorCreateJavaConstructor);
        return javaClassConstructorDescriptorCreateJavaConstructor;
    }

    private final DescriptorVisibility getConstructorVisibility(ClassDescriptor classDescriptor) {
        DescriptorVisibility visibility = classDescriptor.getVisibility();
        Intrinsics.checkNotNullExpressionValue(visibility, "classDescriptor.visibility");
        if (!Intrinsics.areEqual(visibility, JavaDescriptorVisibilities.PROTECTED_STATIC_VISIBILITY)) {
            return visibility;
        }
        DescriptorVisibility PROTECTED_AND_PACKAGE = JavaDescriptorVisibilities.PROTECTED_AND_PACKAGE;
        Intrinsics.checkNotNullExpressionValue(PROTECTED_AND_PACKAGE, "PROTECTED_AND_PACKAGE");
        return PROTECTED_AND_PACKAGE;
    }

    private final List<ValueParameterDescriptor> createAnnotationConstructorParameters(ClassConstructorDescriptorImpl classConstructorDescriptorImpl) {
        Pair pair;
        Collection<JavaMethod> methods = this.jClass.getMethods();
        ArrayList arrayList = new ArrayList(methods.size());
        JavaTypeAttributes attributes$default = JavaTypeResolverKt.toAttributes$default(TypeUsage.COMMON, true, null, 2, null);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : methods) {
            if (Intrinsics.areEqual(((JavaMethod) obj).getName(), JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                arrayList2.add(obj);
            } else {
                arrayList3.add(obj);
            }
        }
        Pair pair2 = new Pair(arrayList2, arrayList3);
        List list = (List) pair2.component1();
        List<JavaMethod> list2 = (List) pair2.component2();
        list.size();
        JavaMethod javaMethod = (JavaMethod) CollectionsKt___CollectionsKt.firstOrNull(list);
        if (javaMethod != null) {
            JavaType returnType = javaMethod.getReturnType();
            if (returnType instanceof JavaArrayType) {
                JavaArrayType javaArrayType = (JavaArrayType) returnType;
                pair = new Pair(getC().getTypeResolver().transformArrayType(javaArrayType, attributes$default, true), getC().getTypeResolver().transformJavaType(javaArrayType.getComponentType(), attributes$default));
            } else {
                pair = new Pair(getC().getTypeResolver().transformJavaType(returnType, attributes$default), null);
            }
            addAnnotationValueParameter(arrayList, classConstructorDescriptorImpl, 0, javaMethod, (KotlinType) pair.component1(), (KotlinType) pair.component2());
        }
        int i = 0;
        int i2 = javaMethod == null ? 0 : 1;
        for (JavaMethod javaMethod2 : list2) {
            addAnnotationValueParameter(arrayList, classConstructorDescriptorImpl, i + i2, javaMethod2, getC().getTypeResolver().transformJavaType(javaMethod2.getReturnType(), attributes$default), null);
            i++;
        }
        return arrayList;
    }

    private final void addAnnotationValueParameter(List<ValueParameterDescriptor> list, ConstructorDescriptor constructorDescriptor, int i, JavaMethod javaMethod, KotlinType kotlinType, KotlinType kotlinType2) {
        Annotations empty = Annotations.Companion.getEMPTY();
        Name name = javaMethod.getName();
        KotlinType kotlinTypeMakeNotNullable = TypeUtils.makeNotNullable(kotlinType);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNotNullable, "makeNotNullable(returnType)");
        list.add(new ValueParameterDescriptorImpl(constructorDescriptor, null, i, empty, name, kotlinTypeMakeNotNullable, javaMethod.getHasAnnotationParameterDefaultValue(), false, false, kotlinType2 == null ? null : TypeUtils.makeNotNullable(kotlinType2), getC().getComponents().getSourceElementFactory().source(javaMethod)));
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return DescriptorUtils.getDispatchReceiverParameterIfNeeded(getOwnerDescriptor());
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    /* JADX INFO: renamed from: getContributedClassifier */
    public ClassifierDescriptor mo4086getContributedClassifier(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        recordLookup(name, location);
        LazyJavaClassMemberScope lazyJavaClassMemberScope = (LazyJavaClassMemberScope) getMainScope();
        ClassDescriptorBase classDescriptorBaseInvoke = lazyJavaClassMemberScope == null ? null : lazyJavaClassMemberScope.nestedClasses.invoke(name);
        return classDescriptorBaseInvoke == null ? this.nestedClasses.invoke(name) : classDescriptorBaseInvoke;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection<SimpleFunctionDescriptor> getContributedFunctions(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        recordLookup(name, location);
        return super.getContributedFunctions(name, location);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection<PropertyDescriptor> getContributedVariables(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        recordLookup(name, location);
        return super.getContributedVariables(name, location);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set<Name> computeClassNames(DescriptorKindFilter kindFilter, Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        return SetsKt___SetsKt.plus((Set) this.nestedClassIndex.invoke(), (Iterable) this.enumEntryIndex.invoke().keySet());
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set<Name> computePropertyNames(DescriptorKindFilter kindFilter, Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        if (this.jClass.isAnnotationType()) {
            return getFunctionNames();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(getDeclaredMemberIndex().invoke().getFieldNames());
        Collection<KotlinType> collectionMo4085getSupertypes = getOwnerDescriptor().getTypeConstructor().mo4085getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collectionMo4085getSupertypes, "ownerDescriptor.typeConstructor.supertypes");
        Iterator<T> it = collectionMo4085getSupertypes.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(linkedHashSet, ((KotlinType) it.next()).getMemberScope().getVariableNames());
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        kotlin.reflect.jvm.internal.impl.incremental.UtilsKt.record(getC().getComponents().getLookupTracker(), location, getOwnerDescriptor(), name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public String toString() {
        return Intrinsics.stringPlus("Lazy Java member scope for ", this.jClass.getFqName());
    }

    private final SimpleFunctionDescriptor createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(SimpleFunctionDescriptor simpleFunctionDescriptor, CallableDescriptor callableDescriptor, Collection<? extends SimpleFunctionDescriptor> collection) {
        boolean z = false;
        if ((collection instanceof Collection) && collection.isEmpty()) {
            z = true;
        } else {
            for (SimpleFunctionDescriptor simpleFunctionDescriptor2 : collection) {
                if (!Intrinsics.areEqual(simpleFunctionDescriptor, simpleFunctionDescriptor2) && simpleFunctionDescriptor2.getInitialSignatureDescriptor() == null && doesOverride(simpleFunctionDescriptor2, callableDescriptor)) {
                    break;
                }
            }
            z = true;
        }
        if (z) {
            return simpleFunctionDescriptor;
        }
        FunctionDescriptor functionDescriptorBuild = simpleFunctionDescriptor.newCopyBuilder().setHiddenToOvercomeSignatureClash().build();
        Intrinsics.checkNotNull(functionDescriptorBuild);
        return (SimpleFunctionDescriptor) functionDescriptorBuild;
    }
}
