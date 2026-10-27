package kotlinx.metadata.internal.metadata;

import kotlinx.metadata.internal.protobuf.Internal;

/* JADX INFO: loaded from: classes7.dex */
public enum ProtoBuf$MemberKind implements Internal.EnumLite {
    DECLARATION(0, 0),
    FAKE_OVERRIDE(1, 1),
    DELEGATION(2, 2),
    SYNTHESIZED(3, 3);

    public static final int DECLARATION_VALUE = 0;
    public static final int DELEGATION_VALUE = 2;
    public static final int FAKE_OVERRIDE_VALUE = 1;
    public static final int SYNTHESIZED_VALUE = 3;
    private static Internal.EnumLiteMap<ProtoBuf$MemberKind> internalValueMap = new Internal.EnumLiteMap<ProtoBuf$MemberKind>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf$MemberKind.1
        @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLiteMap
        public ProtoBuf$MemberKind findValueByNumber(int i) {
            return ProtoBuf$MemberKind.valueOf(i);
        }
    };
    private final int value;

    @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static ProtoBuf$MemberKind valueOf(int i) {
        if (i == 0) {
            return DECLARATION;
        }
        if (i == 1) {
            return FAKE_OVERRIDE;
        }
        if (i == 2) {
            return DELEGATION;
        }
        if (i != 3) {
            return null;
        }
        return SYNTHESIZED;
    }

    public static Internal.EnumLiteMap<ProtoBuf$MemberKind> internalGetValueMap() {
        return internalValueMap;
    }

    ProtoBuf$MemberKind(int i, int i2) {
        this.value = i2;
    }
}
