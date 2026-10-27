package com.google.zxing;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class EncodeHintType {
    public static final EncodeHintType c;
    public static final EncodeHintType e;
    public static final EncodeHintType k;
    public static final EncodeHintType l;
    public static final EncodeHintType m;
    public static final /* synthetic */ EncodeHintType[] n;

    static {
        EncodeHintType encodeHintType = new EncodeHintType("ERROR_CORRECTION", 0);
        c = encodeHintType;
        EncodeHintType encodeHintType2 = new EncodeHintType("CHARACTER_SET", 1);
        e = encodeHintType2;
        EncodeHintType encodeHintType3 = new EncodeHintType("DATA_MATRIX_SHAPE", 2);
        EncodeHintType encodeHintType4 = new EncodeHintType("MIN_SIZE", 3);
        EncodeHintType encodeHintType5 = new EncodeHintType("MAX_SIZE", 4);
        EncodeHintType encodeHintType6 = new EncodeHintType("MARGIN", 5);
        k = encodeHintType6;
        EncodeHintType encodeHintType7 = new EncodeHintType("PDF417_COMPACT", 6);
        EncodeHintType encodeHintType8 = new EncodeHintType("PDF417_COMPACTION", 7);
        EncodeHintType encodeHintType9 = new EncodeHintType("PDF417_DIMENSIONS", 8);
        EncodeHintType encodeHintType10 = new EncodeHintType("AZTEC_LAYERS", 9);
        EncodeHintType encodeHintType11 = new EncodeHintType("QR_VERSION", 10);
        l = encodeHintType11;
        EncodeHintType encodeHintType12 = new EncodeHintType("GS1_FORMAT", 11);
        m = encodeHintType12;
        n = new EncodeHintType[]{encodeHintType, encodeHintType2, encodeHintType3, encodeHintType4, encodeHintType5, encodeHintType6, encodeHintType7, encodeHintType8, encodeHintType9, encodeHintType10, encodeHintType11, encodeHintType12};
    }

    public static EncodeHintType valueOf(String str) {
        return (EncodeHintType) Enum.valueOf(EncodeHintType.class, str);
    }

    public static EncodeHintType[] values() {
        return (EncodeHintType[]) n.clone();
    }
}
