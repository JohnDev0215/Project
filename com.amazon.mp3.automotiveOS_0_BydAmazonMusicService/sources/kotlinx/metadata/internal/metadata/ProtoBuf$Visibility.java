package kotlinx.metadata.internal.metadata;

import kotlinx.metadata.internal.protobuf.Internal;

/* JADX INFO: loaded from: classes7.dex */
public enum ProtoBuf$Visibility implements Internal.EnumLite {
    INTERNAL(0, 0),
    PRIVATE(1, 1),
    PROTECTED(2, 2),
    PUBLIC(3, 3),
    PRIVATE_TO_THIS(4, 4),
    LOCAL(5, 5);

    public static final int INTERNAL_VALUE = 0;
    public static final int LOCAL_VALUE = 5;
    public static final int PRIVATE_TO_THIS_VALUE = 4;
    public static final int PRIVATE_VALUE = 1;
    public static final int PROTECTED_VALUE = 2;
    public static final int PUBLIC_VALUE = 3;
    private static Internal.EnumLiteMap<ProtoBuf$Visibility> internalValueMap = new Internal.EnumLiteMap<ProtoBuf$Visibility>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf$Visibility.1
        @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLiteMap
        public ProtoBuf$Visibility findValueByNumber(int i) {
            return ProtoBuf$Visibility.valueOf(i);
        }
    };
    private final int value;

    @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static ProtoBuf$Visibility valueOf(int i) {
        if (i == 0) {
            return INTERNAL;
        }
        if (i == 1) {
            return PRIVATE;
        }
        if (i == 2) {
            return PROTECTED;
        }
        if (i == 3) {
            return PUBLIC;
        }
        if (i == 4) {
            return PRIVATE_TO_THIS;
        }
        if (i != 5) {
            return null;
        }
        return LOCAL;
    }

    public static Internal.EnumLiteMap<ProtoBuf$Visibility> internalGetValueMap() {
        return internalValueMap;
    }

    ProtoBuf$Visibility(int i, int i2) {
        this.value = i2;
    }
}
