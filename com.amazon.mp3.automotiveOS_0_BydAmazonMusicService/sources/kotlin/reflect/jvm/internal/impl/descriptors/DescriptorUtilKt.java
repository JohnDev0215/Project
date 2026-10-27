package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* JADX INFO: compiled from: descriptorUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class DescriptorUtilKt {
    public static final ClassDescriptor resolveClassByFqName(ModuleDescriptor moduleDescriptor, FqName fqName, LookupLocation lookupLocation) {
        ClassifierDescriptor classifierDescriptorMo4086getContributedClassifier;
        Intrinsics.checkNotNullParameter(moduleDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(lookupLocation, "lookupLocation");
        if (fqName.isRoot()) {
            return null;
        }
        FqName fqNameParent = fqName.parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent, "fqName.parent()");
        MemberScope memberScope = moduleDescriptor.getPackage(fqNameParent).getMemberScope();
        Name nameShortName = fqName.shortName();
        Intrinsics.checkNotNullExpressionValue(nameShortName, "fqName.shortName()");
        ClassifierDescriptor classifierDescriptorMo4086getContributedClassifier2 = memberScope.mo4086getContributedClassifier(nameShortName, lookupLocation);
        ClassDescriptor classDescriptor = classifierDescriptorMo4086getContributedClassifier2 instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo4086getContributedClassifier2 : null;
        if (classDescriptor != null) {
            return classDescriptor;
        }
        FqName fqNameParent2 = fqName.parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent2, "fqName.parent()");
        ClassDescriptor classDescriptorResolveClassByFqName = resolveClassByFqName(moduleDescriptor, fqNameParent2, lookupLocation);
        if (classDescriptorResolveClassByFqName == null) {
            classifierDescriptorMo4086getContributedClassifier = null;
        } else {
            MemberScope unsubstitutedInnerClassesScope = classDescriptorResolveClassByFqName.getUnsubstitutedInnerClassesScope();
            Name nameShortName2 = fqName.shortName();
            Intrinsics.checkNotNullExpressionValue(nameShortName2, "fqName.shortName()");
            classifierDescriptorMo4086getContributedClassifier = unsubstitutedInnerClassesScope.mo4086getContributedClassifier(nameShortName2, lookupLocation);
        }
        if (classifierDescriptorMo4086getContributedClassifier instanceof ClassDescriptor) {
            return (ClassDescriptor) classifierDescriptorMo4086getContributedClassifier;
        }
        return null;
    }

    public static final boolean isTopLevelInPackage(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        return declarationDescriptor.getContainingDeclaration() instanceof PackageFragmentDescriptor;
    }

    public static final ClassifierDescriptor getTopLevelContainingClassifier(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        DeclarationDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
        if (containingDeclaration == null || (declarationDescriptor instanceof PackageFragmentDescriptor)) {
            return null;
        }
        if (!isTopLevelInPackage(containingDeclaration)) {
            return getTopLevelContainingClassifier(containingDeclaration);
        }
        if (containingDeclaration instanceof ClassifierDescriptor) {
            return (ClassifierDescriptor) containingDeclaration;
        }
        return null;
    }
}
