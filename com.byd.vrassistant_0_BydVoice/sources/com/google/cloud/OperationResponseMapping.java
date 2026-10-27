package com.google.cloud;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* JADX INFO: loaded from: classes2.dex */
public enum OperationResponseMapping implements ProtocolMessageEnum {
    UNDEFINED(0),
    NAME(1),
    STATUS(2),
    ERROR_CODE(3),
    ERROR_MESSAGE(4),
    UNRECOGNIZED(-1);

    public static final int ERROR_CODE_VALUE = 3;
    public static final int ERROR_MESSAGE_VALUE = 4;
    public static final int NAME_VALUE = 1;
    public static final int STATUS_VALUE = 2;
    public static final int UNDEFINED_VALUE = 0;
    private final int value;
    private static final Internal.EnumLiteMap<OperationResponseMapping> internalValueMap = new Internal.EnumLiteMap<OperationResponseMapping>() { // from class: com.google.cloud.OperationResponseMapping.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public OperationResponseMapping findValueByNumber(int i) {
            return OperationResponseMapping.forNumber(i);
        }
    };
    private static final OperationResponseMapping[] VALUES = values();

    @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static OperationResponseMapping valueOf(int i) {
        return forNumber(i);
    }

    public static OperationResponseMapping forNumber(int i) {
        if (i == 0) {
            return UNDEFINED;
        }
        if (i == 1) {
            return NAME;
        }
        if (i == 2) {
            return STATUS;
        }
        if (i == 3) {
            return ERROR_CODE;
        }
        if (i != 4) {
            return null;
        }
        return ERROR_MESSAGE;
    }

    public static Internal.EnumLiteMap<OperationResponseMapping> internalGetValueMap() {
        return internalValueMap;
    }

    @Override // com.google.protobuf.ProtocolMessageEnum
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        if (this == UNRECOGNIZED) {
            throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
        }
        return getDescriptor().getValues().get(ordinal());
    }

    @Override // com.google.protobuf.ProtocolMessageEnum
    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return ExtendedOperationsProto.getDescriptor().getEnumTypes().get(0);
    }

    public static OperationResponseMapping valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
        if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[enumValueDescriptor.getIndex()];
    }

    OperationResponseMapping(int i) {
        this.value = i;
    }
}
