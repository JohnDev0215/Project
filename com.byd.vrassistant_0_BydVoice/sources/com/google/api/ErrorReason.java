package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* JADX INFO: loaded from: classes2.dex */
public enum ErrorReason implements ProtocolMessageEnum {
    ERROR_REASON_UNSPECIFIED(0),
    SERVICE_DISABLED(1),
    BILLING_DISABLED(2),
    API_KEY_INVALID(3),
    API_KEY_SERVICE_BLOCKED(4),
    API_KEY_HTTP_REFERRER_BLOCKED(7),
    API_KEY_IP_ADDRESS_BLOCKED(8),
    API_KEY_ANDROID_APP_BLOCKED(9),
    API_KEY_IOS_APP_BLOCKED(13),
    RATE_LIMIT_EXCEEDED(5),
    RESOURCE_QUOTA_EXCEEDED(6),
    LOCATION_TAX_POLICY_VIOLATED(10),
    USER_PROJECT_DENIED(11),
    CONSUMER_SUSPENDED(12),
    CONSUMER_INVALID(14),
    SECURITY_POLICY_VIOLATED(15),
    ACCESS_TOKEN_EXPIRED(16),
    ACCESS_TOKEN_SCOPE_INSUFFICIENT(17),
    ACCOUNT_STATE_INVALID(18),
    ACCESS_TOKEN_TYPE_UNSUPPORTED(19),
    UNRECOGNIZED(-1);

    public static final int ACCESS_TOKEN_EXPIRED_VALUE = 16;
    public static final int ACCESS_TOKEN_SCOPE_INSUFFICIENT_VALUE = 17;
    public static final int ACCESS_TOKEN_TYPE_UNSUPPORTED_VALUE = 19;
    public static final int ACCOUNT_STATE_INVALID_VALUE = 18;
    public static final int API_KEY_ANDROID_APP_BLOCKED_VALUE = 9;
    public static final int API_KEY_HTTP_REFERRER_BLOCKED_VALUE = 7;
    public static final int API_KEY_INVALID_VALUE = 3;
    public static final int API_KEY_IOS_APP_BLOCKED_VALUE = 13;
    public static final int API_KEY_IP_ADDRESS_BLOCKED_VALUE = 8;
    public static final int API_KEY_SERVICE_BLOCKED_VALUE = 4;
    public static final int BILLING_DISABLED_VALUE = 2;
    public static final int CONSUMER_INVALID_VALUE = 14;
    public static final int CONSUMER_SUSPENDED_VALUE = 12;
    public static final int ERROR_REASON_UNSPECIFIED_VALUE = 0;
    public static final int LOCATION_TAX_POLICY_VIOLATED_VALUE = 10;
    public static final int RATE_LIMIT_EXCEEDED_VALUE = 5;
    public static final int RESOURCE_QUOTA_EXCEEDED_VALUE = 6;
    public static final int SECURITY_POLICY_VIOLATED_VALUE = 15;
    public static final int SERVICE_DISABLED_VALUE = 1;
    public static final int USER_PROJECT_DENIED_VALUE = 11;
    private final int value;
    private static final Internal.EnumLiteMap<ErrorReason> internalValueMap = new Internal.EnumLiteMap<ErrorReason>() { // from class: com.google.api.ErrorReason.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ErrorReason findValueByNumber(int i) {
            return ErrorReason.forNumber(i);
        }
    };
    private static final ErrorReason[] VALUES = values();

    @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static ErrorReason valueOf(int i) {
        return forNumber(i);
    }

    public static ErrorReason forNumber(int i) {
        switch (i) {
            case 0:
                return ERROR_REASON_UNSPECIFIED;
            case 1:
                return SERVICE_DISABLED;
            case 2:
                return BILLING_DISABLED;
            case 3:
                return API_KEY_INVALID;
            case 4:
                return API_KEY_SERVICE_BLOCKED;
            case 5:
                return RATE_LIMIT_EXCEEDED;
            case 6:
                return RESOURCE_QUOTA_EXCEEDED;
            case 7:
                return API_KEY_HTTP_REFERRER_BLOCKED;
            case 8:
                return API_KEY_IP_ADDRESS_BLOCKED;
            case 9:
                return API_KEY_ANDROID_APP_BLOCKED;
            case 10:
                return LOCATION_TAX_POLICY_VIOLATED;
            case 11:
                return USER_PROJECT_DENIED;
            case 12:
                return CONSUMER_SUSPENDED;
            case 13:
                return API_KEY_IOS_APP_BLOCKED;
            case 14:
                return CONSUMER_INVALID;
            case 15:
                return SECURITY_POLICY_VIOLATED;
            case 16:
                return ACCESS_TOKEN_EXPIRED;
            case 17:
                return ACCESS_TOKEN_SCOPE_INSUFFICIENT;
            case 18:
                return ACCOUNT_STATE_INVALID;
            case 19:
                return ACCESS_TOKEN_TYPE_UNSUPPORTED;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<ErrorReason> internalGetValueMap() {
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
        return ErrorReasonProto.getDescriptor().getEnumTypes().get(0);
    }

    public static ErrorReason valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
        if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[enumValueDescriptor.getIndex()];
    }

    ErrorReason(int i) {
        this.value = i;
    }
}
