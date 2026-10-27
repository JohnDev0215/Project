package kotlinx.metadata.internal.metadata;

import kotlinx.metadata.internal.protobuf.Internal;

/* JADX INFO: loaded from: classes7.dex */
public enum ProtoBuf$Modality implements Internal.EnumLite {
    FINAL(0, 0),
    OPEN(1, 1),
    ABSTRACT(2, 2),
    SEALED(3, 3);

    public static final int ABSTRACT_VALUE = 2;
    public static final int FINAL_VALUE = 0;
    public static final int OPEN_VALUE = 1;
    public static final int SEALED_VALUE = 3;
    private static Internal.EnumLiteMap<ProtoBuf$Modality> internalValueMap = new Internal.EnumLiteMap<ProtoBuf$Modality>() { // from class: kotlinx.metadata.internal.metadata.ProtoBuf$Modality.1
        @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLiteMap
        public ProtoBuf$Modality findValueByNumber(int i) {
            return ProtoBuf$Modality.valueOf(i);
        }
    };
    private final int value;

    @Override // kotlinx.metadata.internal.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    public static ProtoBuf$Modality valueOf(int i) {
        if (i == 0) {
            return FINAL;
        }
        if (i == 1) {
            return OPEN;
        }
        if (i == 2) {
            return ABSTRACT;
        }
        if (i != 3) {
            return null;
        }
        return SEALED;
    }

    public static Internal.EnumLiteMap<ProtoBuf$Modality> internalGetValueMap() {
        return internalValueMap;
    }

    ProtoBuf$Modality(int i, int i2) {
        this.value = i2;
    }
}
