package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class ProtoEnumFlagsUtilsKt {

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[ProtoBuf.MemberKind.values().length];
            iArr[ProtoBuf.MemberKind.DECLARATION.ordinal()] = 1;
            iArr[ProtoBuf.MemberKind.FAKE_OVERRIDE.ordinal()] = 2;
            iArr[ProtoBuf.MemberKind.DELEGATION.ordinal()] = 3;
            iArr[ProtoBuf.MemberKind.SYNTHESIZED.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CallableMemberDescriptor.Kind.values().length];
            iArr2[CallableMemberDescriptor.Kind.DECLARATION.ordinal()] = 1;
            iArr2[CallableMemberDescriptor.Kind.FAKE_OVERRIDE.ordinal()] = 2;
            iArr2[CallableMemberDescriptor.Kind.DELEGATION.ordinal()] = 3;
            iArr2[CallableMemberDescriptor.Kind.SYNTHESIZED.ordinal()] = 4;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ProtoBuf.Visibility.values().length];
            iArr3[ProtoBuf.Visibility.INTERNAL.ordinal()] = 1;
            iArr3[ProtoBuf.Visibility.PRIVATE.ordinal()] = 2;
            iArr3[ProtoBuf.Visibility.PRIVATE_TO_THIS.ordinal()] = 3;
            iArr3[ProtoBuf.Visibility.PROTECTED.ordinal()] = 4;
            iArr3[ProtoBuf.Visibility.PUBLIC.ordinal()] = 5;
            iArr3[ProtoBuf.Visibility.LOCAL.ordinal()] = 6;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    @NotNull
    public static final DescriptorVisibility descriptorVisibility(@NotNull ProtoEnumFlags protoEnumFlags, @Nullable ProtoBuf.Visibility visibility) {
        DescriptorVisibility descriptorVisibility;
        Intrinsics.checkNotNullParameter(protoEnumFlags, "<this>");
        int i = visibility == null ? -1 : WhenMappings.$EnumSwitchMapping$2[visibility.ordinal()];
        String str = "PRIVATE";
        switch (i) {
            case 1:
                descriptorVisibility = DescriptorVisibilities.INTERNAL;
                str = "INTERNAL";
                break;
            case 2:
            default:
                descriptorVisibility = DescriptorVisibilities.PRIVATE;
                break;
            case 3:
                descriptorVisibility = DescriptorVisibilities.PRIVATE_TO_THIS;
                str = "PRIVATE_TO_THIS";
                break;
            case 4:
                descriptorVisibility = DescriptorVisibilities.PROTECTED;
                str = "PROTECTED";
                break;
            case 5:
                descriptorVisibility = DescriptorVisibilities.PUBLIC;
                str = "PUBLIC";
                break;
            case 6:
                descriptorVisibility = DescriptorVisibilities.LOCAL;
                str = "LOCAL";
                break;
        }
        Intrinsics.checkNotNullExpressionValue(descriptorVisibility, str);
        return descriptorVisibility;
    }

    @NotNull
    public static final CallableMemberDescriptor.Kind memberKind(@NotNull ProtoEnumFlags protoEnumFlags, @Nullable ProtoBuf.MemberKind memberKind) {
        Intrinsics.checkNotNullParameter(protoEnumFlags, "<this>");
        int i = memberKind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[memberKind.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return CallableMemberDescriptor.Kind.FAKE_OVERRIDE;
            }
            if (i == 3) {
                return CallableMemberDescriptor.Kind.DELEGATION;
            }
            if (i == 4) {
                return CallableMemberDescriptor.Kind.SYNTHESIZED;
            }
        }
        return CallableMemberDescriptor.Kind.DECLARATION;
    }
}
