package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefinerKt;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeRefinementSupport;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: DescriptorUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class DescriptorUtilsKt {
    private static final Name RETENTION_PARAMETER_NAME;

    static {
        Name nameIdentifier = Name.identifier("value");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(\"value\")");
        RETENTION_PARAMETER_NAME = nameIdentifier;
    }

    public static final FqNameUnsafe getFqNameUnsafe(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        FqNameUnsafe fqName = DescriptorUtils.getFqName(declarationDescriptor);
        Intrinsics.checkNotNullExpressionValue(fqName, "getFqName(this)");
        return fqName;
    }

    public static final FqName getFqNameSafe(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        FqName fqNameSafe = DescriptorUtils.getFqNameSafe(declarationDescriptor);
        Intrinsics.checkNotNullExpressionValue(fqNameSafe, "getFqNameSafe(this)");
        return fqNameSafe;
    }

    public static final ModuleDescriptor getModule(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        ModuleDescriptor containingModule = DescriptorUtils.getContainingModule(declarationDescriptor);
        Intrinsics.checkNotNullExpressionValue(containingModule, "getContainingModule(this)");
        return containingModule;
    }

    public static final ClassDescriptor resolveTopLevelClass(ModuleDescriptor moduleDescriptor, FqName topLevelClassFqName, LookupLocation location) {
        Intrinsics.checkNotNullParameter(moduleDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(topLevelClassFqName, "topLevelClassFqName");
        Intrinsics.checkNotNullParameter(location, "location");
        topLevelClassFqName.isRoot();
        FqName fqNameParent = topLevelClassFqName.parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent, "topLevelClassFqName.parent()");
        MemberScope memberScope = moduleDescriptor.getPackage(fqNameParent).getMemberScope();
        Name nameShortName = topLevelClassFqName.shortName();
        Intrinsics.checkNotNullExpressionValue(nameShortName, "topLevelClassFqName.shortName()");
        ClassifierDescriptor classifierDescriptorMo4086getContributedClassifier = memberScope.mo4086getContributedClassifier(nameShortName, location);
        if (classifierDescriptorMo4086getContributedClassifier instanceof ClassDescriptor) {
            return (ClassDescriptor) classifierDescriptorMo4086getContributedClassifier;
        }
        return null;
    }

    public static final ClassId getClassId(ClassifierDescriptor classifierDescriptor) {
        if (classifierDescriptor == null) {
            return null;
        }
        DeclarationDescriptor owner = classifierDescriptor.getContainingDeclaration();
        if (owner instanceof PackageFragmentDescriptor) {
            return new ClassId(((PackageFragmentDescriptor) owner).getFqName(), classifierDescriptor.getName());
        }
        if (!(owner instanceof ClassifierDescriptorWithTypeParameters)) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(owner, "owner");
        ClassId classId = getClassId((ClassifierDescriptor) owner);
        if (classId == null) {
            return null;
        }
        return classId.createNestedClassId(classifierDescriptor.getName());
    }

    public static final ClassDescriptor getSuperClassNotAny(ClassDescriptor classDescriptor) {
        Intrinsics.checkNotNullParameter(classDescriptor, "<this>");
        for (KotlinType kotlinType : classDescriptor.getDefaultType().getConstructor().mo4085getSupertypes()) {
            if (!KotlinBuiltIns.isAnyOrNullableAny(kotlinType)) {
                ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = kotlinType.getConstructor().mo4084getDeclarationDescriptor();
                if (DescriptorUtils.isClassOrEnumClass(classifierDescriptorMo4084getDeclarationDescriptor)) {
                    if (classifierDescriptorMo4084getDeclarationDescriptor != null) {
                        return (ClassDescriptor) classifierDescriptorMo4084getDeclarationDescriptor;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                }
            }
        }
        return null;
    }

    public static final KotlinBuiltIns getBuiltIns(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        return getModule(declarationDescriptor).getBuiltIns();
    }

    public static final boolean declaresOrInheritsDefaultValue(ValueParameterDescriptor valueParameterDescriptor) {
        Intrinsics.checkNotNullParameter(valueParameterDescriptor, "<this>");
        Boolean boolIfAny = DFS.ifAny(CollectionsKt__CollectionsJVMKt.listOf(valueParameterDescriptor), new DFS.Neighbors() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final Iterable<ValueParameterDescriptor> getNeighbors(ValueParameterDescriptor valueParameterDescriptor2) {
                Collection<ValueParameterDescriptor> overriddenDescriptors = valueParameterDescriptor2.getOverriddenDescriptors();
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(overriddenDescriptors, 10));
                Iterator<T> it = overriddenDescriptors.iterator();
                while (it.hasNext()) {
                    arrayList.add(((ValueParameterDescriptor) it.next()).getOriginal());
                }
                return arrayList;
            }
        }, AnonymousClass2.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(boolIfAny, "ifAny(\n        listOf(th…eclaresDefaultValue\n    )");
        return boolIfAny.booleanValue();
    }

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$declaresOrInheritsDefaultValue$2, reason: invalid class name */
    /* JADX INFO: compiled from: DescriptorUtils.kt */
    /* synthetic */ class AnonymousClass2 extends FunctionReference implements Function1<ValueParameterDescriptor, Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "declaresDefaultValue";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(ValueParameterDescriptor.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "declaresDefaultValue()Z";
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(ValueParameterDescriptor p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return Boolean.valueOf(p0.declaresDefaultValue());
        }
    }

    public static final Sequence<DeclarationDescriptor> getParentsWithSelf(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        return SequencesKt__SequencesKt.generateSequence(declarationDescriptor, (Function1<? super DeclarationDescriptor, ? extends DeclarationDescriptor>) ((Function1<? super Object, ? extends Object>) new Function1<DeclarationDescriptor, DeclarationDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt$parentsWithSelf$1
            @Override // kotlin.jvm.functions.Function1
            public final DeclarationDescriptor invoke(DeclarationDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getContainingDeclaration();
            }
        }));
    }

    public static final Sequence<DeclarationDescriptor> getParents(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        return SequencesKt___SequencesKt.drop(getParentsWithSelf(declarationDescriptor), 1);
    }

    public static final CallableMemberDescriptor getPropertyIfAccessor(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "<this>");
        if (!(callableMemberDescriptor instanceof PropertyAccessorDescriptor)) {
            return callableMemberDescriptor;
        }
        PropertyDescriptor correspondingProperty = ((PropertyAccessorDescriptor) callableMemberDescriptor).getCorrespondingProperty();
        Intrinsics.checkNotNullExpressionValue(correspondingProperty, "correspondingProperty");
        return correspondingProperty;
    }

    public static final FqName fqNameOrNull(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        FqNameUnsafe fqNameUnsafe = getFqNameUnsafe(declarationDescriptor);
        if (!fqNameUnsafe.isSafe()) {
            fqNameUnsafe = null;
        }
        if (fqNameUnsafe == null) {
            return null;
        }
        return fqNameUnsafe.toSafe();
    }

    public static /* synthetic */ CallableMemberDescriptor firstOverridden$default(CallableMemberDescriptor callableMemberDescriptor, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return firstOverridden(callableMemberDescriptor, z, function1);
    }

    public static final CallableMemberDescriptor firstOverridden(CallableMemberDescriptor callableMemberDescriptor, final boolean z, final Function1<? super CallableMemberDescriptor, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        return (CallableMemberDescriptor) DFS.dfs(CollectionsKt__CollectionsJVMKt.listOf(callableMemberDescriptor), new DFS.Neighbors() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstOverridden.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors
            public final Iterable<CallableMemberDescriptor> getNeighbors(CallableMemberDescriptor callableMemberDescriptor2) {
                if (z) {
                    callableMemberDescriptor2 = callableMemberDescriptor2 == null ? null : callableMemberDescriptor2.getOriginal();
                }
                if (callableMemberDescriptor2 == null) {
                    return CollectionsKt__CollectionsKt.emptyList();
                }
                Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor2.getOverriddenDescriptors();
                Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "descriptor?.overriddenDescriptors ?: emptyList()");
                return overriddenDescriptors;
            }
        }, new DFS.AbstractNodeHandler<CallableMemberDescriptor, CallableMemberDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstOverridden.2
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(CallableMemberDescriptor current) {
                Intrinsics.checkNotNullParameter(current, "current");
                return objectRef.element == null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public void afterChildren(CallableMemberDescriptor current) {
                Intrinsics.checkNotNullParameter(current, "current");
                if (objectRef.element == null && predicate.invoke(current).booleanValue()) {
                    objectRef.element = current;
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public CallableMemberDescriptor result() {
                return objectRef.element;
            }
        });
    }

    public static final ClassDescriptor getAnnotationClass(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "<this>");
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = annotationDescriptor.getType().getConstructor().mo4084getDeclarationDescriptor();
        if (classifierDescriptorMo4084getDeclarationDescriptor instanceof ClassDescriptor) {
            return (ClassDescriptor) classifierDescriptorMo4084getDeclarationDescriptor;
        }
        return null;
    }

    public static final ConstantValue<?> firstArgument(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkNotNullParameter(annotationDescriptor, "<this>");
        return (ConstantValue) CollectionsKt___CollectionsKt.firstOrNull(annotationDescriptor.getAllValueArguments().values());
    }

    public static final KotlinTypeRefiner getKotlinTypeRefiner(ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkNotNullParameter(moduleDescriptor, "<this>");
        kotlin.reflect.jvm.internal.impl.types.checker.Ref ref = (kotlin.reflect.jvm.internal.impl.types.checker.Ref) moduleDescriptor.getCapability(KotlinTypeRefinerKt.getREFINER_CAPABILITY());
        TypeRefinementSupport typeRefinementSupport = ref == null ? null : (TypeRefinementSupport) ref.getValue();
        return typeRefinementSupport instanceof TypeRefinementSupport.Enabled ? ((TypeRefinementSupport.Enabled) typeRefinementSupport).getTypeRefiner() : KotlinTypeRefiner.Default.INSTANCE;
    }

    public static final boolean isTypeRefinementEnabled(ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkNotNullParameter(moduleDescriptor, "<this>");
        kotlin.reflect.jvm.internal.impl.types.checker.Ref ref = (kotlin.reflect.jvm.internal.impl.types.checker.Ref) moduleDescriptor.getCapability(KotlinTypeRefinerKt.getREFINER_CAPABILITY());
        return ref != null && ((TypeRefinementSupport) ref.getValue()).isEnabled();
    }
}
