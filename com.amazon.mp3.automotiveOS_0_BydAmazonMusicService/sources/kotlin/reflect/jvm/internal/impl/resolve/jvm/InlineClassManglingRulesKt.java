package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: inlineClassManglingRules.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class InlineClassManglingRulesKt {
    public static final boolean shouldHideConstructorDueToInlineClassTypeValueParameters(CallableMemberDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        ClassConstructorDescriptor classConstructorDescriptor = descriptor instanceof ClassConstructorDescriptor ? (ClassConstructorDescriptor) descriptor : null;
        if (classConstructorDescriptor == null || DescriptorVisibilities.isPrivate(classConstructorDescriptor.getVisibility())) {
            return false;
        }
        ClassDescriptor constructedClass = classConstructorDescriptor.getConstructedClass();
        Intrinsics.checkNotNullExpressionValue(constructedClass, "constructorDescriptor.constructedClass");
        if (InlineClassesUtilsKt.isInlineClass(constructedClass) || DescriptorUtils.isSealedClass(classConstructorDescriptor.getConstructedClass())) {
            return false;
        }
        List<ValueParameterDescriptor> valueParameters = classConstructorDescriptor.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "constructorDescriptor.valueParameters");
        if ((valueParameters instanceof Collection) && valueParameters.isEmpty()) {
            return false;
        }
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            KotlinType type = ((ValueParameterDescriptor) it.next()).getType();
            Intrinsics.checkNotNullExpressionValue(type, "it.type");
            if (requiresFunctionNameManglingInParameterTypes(type)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isInlineClassThatRequiresMangling(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        return InlineClassesUtilsKt.isInlineClass(declarationDescriptor) && !isDontMangleClass((ClassDescriptor) declarationDescriptor);
    }

    public static final boolean isInlineClassThatRequiresMangling(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = kotlinType.getConstructor().mo4084getDeclarationDescriptor();
        return classifierDescriptorMo4084getDeclarationDescriptor != null && isInlineClassThatRequiresMangling(classifierDescriptorMo4084getDeclarationDescriptor);
    }

    private static final boolean requiresFunctionNameManglingInParameterTypes(KotlinType kotlinType) {
        return isInlineClassThatRequiresMangling(kotlinType) || isTypeParameterWithUpperBoundThatRequiresMangling(kotlinType);
    }

    private static final boolean isDontMangleClass(ClassDescriptor classDescriptor) {
        return Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(classDescriptor), StandardNames.RESULT_FQ_NAME);
    }

    private static final boolean isTypeParameterWithUpperBoundThatRequiresMangling(KotlinType kotlinType) {
        ClassifierDescriptor classifierDescriptorMo4084getDeclarationDescriptor = kotlinType.getConstructor().mo4084getDeclarationDescriptor();
        TypeParameterDescriptor typeParameterDescriptor = classifierDescriptorMo4084getDeclarationDescriptor instanceof TypeParameterDescriptor ? (TypeParameterDescriptor) classifierDescriptorMo4084getDeclarationDescriptor : null;
        if (typeParameterDescriptor == null) {
            return false;
        }
        return requiresFunctionNameManglingInParameterTypes(TypeUtilsKt.getRepresentativeUpperBound(typeParameterDescriptor));
    }
}
