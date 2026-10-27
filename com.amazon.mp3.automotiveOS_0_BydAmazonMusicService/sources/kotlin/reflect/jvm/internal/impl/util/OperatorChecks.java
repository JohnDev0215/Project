package kotlin.reflect.jvm.internal.impl.util;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.util.MemberKindCheck;
import kotlin.reflect.jvm.internal.impl.util.ReturnsCheck;
import kotlin.reflect.jvm.internal.impl.util.ValueParameterCountCheck;

/* JADX INFO: compiled from: modifierChecks.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class OperatorChecks extends AbstractModifierChecks {
    public static final OperatorChecks INSTANCE = new OperatorChecks();
    private static final List<Checks> checks;

    private OperatorChecks() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks
    public List<Checks> getChecks$descriptors() {
        return checks;
    }

    static {
        Name name = OperatorNameConventions.GET;
        MemberKindCheck.MemberOrExtension memberOrExtension = MemberKindCheck.MemberOrExtension.INSTANCE;
        Check[] checkArr = {memberOrExtension, new ValueParameterCountCheck.AtLeast(1)};
        Name name2 = OperatorNameConventions.SET;
        Check[] checkArr2 = {memberOrExtension, new ValueParameterCountCheck.AtLeast(2)};
        Name name3 = OperatorNameConventions.GET_VALUE;
        NoDefaultAndVarargsCheck noDefaultAndVarargsCheck = NoDefaultAndVarargsCheck.INSTANCE;
        IsKPropertyCheck isKPropertyCheck = IsKPropertyCheck.INSTANCE;
        Name name4 = OperatorNameConventions.CONTAINS;
        ValueParameterCountCheck.SingleValueParameter singleValueParameter = ValueParameterCountCheck.SingleValueParameter.INSTANCE;
        ReturnsCheck.ReturnsBoolean returnsBoolean = ReturnsCheck.ReturnsBoolean.INSTANCE;
        Name name5 = OperatorNameConventions.ITERATOR;
        ValueParameterCountCheck.NoValueParameters noValueParameters = ValueParameterCountCheck.NoValueParameters.INSTANCE;
        checks = CollectionsKt__CollectionsKt.listOf((Object[]) new Checks[]{new Checks(name, checkArr, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(name2, checkArr2, new Function1<FunctionDescriptor, String>() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$1
            /* JADX WARN: Removed duplicated region for block: B:4:0x0018  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.String invoke(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r4) {
                /*
                    r3 = this;
                    java.lang.String r0 = "$this$$receiver"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                    java.util.List r4 = r4.getValueParameters()
                    java.lang.String r0 = "valueParameters"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
                    java.lang.Object r4 = kotlin.collections.CollectionsKt.lastOrNull(r4)
                    kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r4
                    r0 = 1
                    r1 = 0
                    if (r4 != 0) goto L1a
                L18:
                    r0 = r1
                    goto L2b
                L1a:
                    boolean r2 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue(r4)
                    if (r2 != 0) goto L28
                    kotlin.reflect.jvm.internal.impl.types.KotlinType r4 = r4.getVarargElementType()
                    if (r4 != 0) goto L28
                    r4 = r0
                    goto L29
                L28:
                    r4 = r1
                L29:
                    if (r4 != r0) goto L18
                L2b:
                    kotlin.reflect.jvm.internal.impl.util.OperatorChecks r4 = kotlin.reflect.jvm.internal.impl.util.OperatorChecks.INSTANCE
                    if (r0 != 0) goto L32
                    java.lang.String r4 = "last parameter should not have a default value or be a vararg"
                    goto L33
                L32:
                    r4 = 0
                L33:
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$1.invoke(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor):java.lang.String");
            }
        }), new Checks(name3, new Check[]{memberOrExtension, noDefaultAndVarargsCheck, new ValueParameterCountCheck.AtLeast(2), isKPropertyCheck}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.SET_VALUE, new Check[]{memberOrExtension, noDefaultAndVarargsCheck, new ValueParameterCountCheck.AtLeast(3), isKPropertyCheck}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.PROVIDE_DELEGATE, new Check[]{memberOrExtension, noDefaultAndVarargsCheck, new ValueParameterCountCheck.Equals(2), isKPropertyCheck}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.INVOKE, new Check[]{memberOrExtension}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(name4, new Check[]{memberOrExtension, singleValueParameter, noDefaultAndVarargsCheck, returnsBoolean}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(name5, new Check[]{memberOrExtension, noValueParameters}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.NEXT, new Check[]{memberOrExtension, noValueParameters}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.HAS_NEXT, new Check[]{memberOrExtension, noValueParameters, returnsBoolean}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.RANGE_TO, new Check[]{memberOrExtension, singleValueParameter, noDefaultAndVarargsCheck}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.EQUALS, new Check[]{MemberKindCheck.Member.INSTANCE}, new Function1<FunctionDescriptor, String>() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2
            private static final boolean invoke$isAny(DeclarationDescriptor declarationDescriptor) {
                return (declarationDescriptor instanceof ClassDescriptor) && KotlinBuiltIns.isAny((ClassDescriptor) declarationDescriptor);
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x004c  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.String invoke(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "$this$$receiver"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    kotlin.reflect.jvm.internal.impl.util.OperatorChecks r0 = kotlin.reflect.jvm.internal.impl.util.OperatorChecks.INSTANCE
                    kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r5.getContainingDeclaration()
                    java.lang.String r1 = "containingDeclaration"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    boolean r0 = invoke$isAny(r0)
                    r1 = 1
                    r2 = 0
                    if (r0 != 0) goto L4d
                    java.util.Collection r5 = r5.getOverriddenDescriptors()
                    java.lang.String r0 = "overriddenDescriptors"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
                    boolean r0 = r5.isEmpty()
                    if (r0 == 0) goto L29
                L27:
                    r5 = r2
                    goto L49
                L29:
                    java.util.Iterator r5 = r5.iterator()
                L2d:
                    boolean r0 = r5.hasNext()
                    if (r0 == 0) goto L27
                    java.lang.Object r0 = r5.next()
                    kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor) r0
                    kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r0.getContainingDeclaration()
                    java.lang.String r3 = "it.containingDeclaration"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
                    boolean r0 = invoke$isAny(r0)
                    if (r0 == 0) goto L2d
                    r5 = r1
                L49:
                    if (r5 == 0) goto L4c
                    goto L4d
                L4c:
                    r1 = r2
                L4d:
                    if (r1 != 0) goto L52
                    java.lang.String r5 = "must override ''equals()'' in Any"
                    goto L53
                L52:
                    r5 = 0
                L53:
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$2.invoke(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor):java.lang.String");
            }
        }), new Checks(OperatorNameConventions.COMPARE_TO, new Check[]{memberOrExtension, ReturnsCheck.ReturnsInt.INSTANCE, singleValueParameter, noDefaultAndVarargsCheck}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.BINARY_OPERATION_NAMES, new Check[]{memberOrExtension, singleValueParameter, noDefaultAndVarargsCheck}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.SIMPLE_UNARY_OPERATION_NAMES, new Check[]{memberOrExtension, noValueParameters}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(CollectionsKt__CollectionsKt.listOf((Object[]) new Name[]{OperatorNameConventions.INC, OperatorNameConventions.DEC}), new Check[]{memberOrExtension}, new Function1<FunctionDescriptor, String>() { // from class: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$3
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(FunctionDescriptor $receiver) {
                boolean zIsSubtypeOf;
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                ReceiverParameterDescriptor dispatchReceiverParameter = $receiver.getDispatchReceiverParameter();
                if (dispatchReceiverParameter == null) {
                    dispatchReceiverParameter = $receiver.getExtensionReceiverParameter();
                }
                OperatorChecks operatorChecks = OperatorChecks.INSTANCE;
                boolean z = false;
                if (dispatchReceiverParameter != null) {
                    KotlinType returnType = $receiver.getReturnType();
                    if (returnType == null) {
                        zIsSubtypeOf = false;
                    } else {
                        KotlinType type = dispatchReceiverParameter.getType();
                        Intrinsics.checkNotNullExpressionValue(type, "receiver.type");
                        zIsSubtypeOf = TypeUtilsKt.isSubtypeOf(returnType, type);
                    }
                    if (zIsSubtypeOf) {
                        z = true;
                    }
                }
                if (z) {
                    return null;
                }
                return "receiver must be a supertype of the return type";
            }
        }), new Checks(OperatorNameConventions.ASSIGNMENT_OPERATIONS, new Check[]{memberOrExtension, ReturnsCheck.ReturnsUnit.INSTANCE, singleValueParameter, noDefaultAndVarargsCheck}, (Function1) null, 4, (DefaultConstructorMarker) null), new Checks(OperatorNameConventions.COMPONENT_REGEX, new Check[]{memberOrExtension, noValueParameters}, (Function1) null, 4, (DefaultConstructorMarker) null)});
    }
}
