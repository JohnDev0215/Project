package com.amazon.client.metrics.thirdparty;

/* JADX INFO: loaded from: classes2.dex */
public enum DataPointType {
    CT,
    TI,
    DV,
    CK;

    public static DataPointType fromInt(int i) {
        if (i < 0 || i > values().length) {
            throw new IllegalArgumentException("invalid datapoint " + i);
        }
        return values()[i];
    }

    @Deprecated
    public static DataPointType getCounterType() {
        return CT;
    }

    @Deprecated
    public static DataPointType getTimerType() {
        return TI;
    }

    @Deprecated
    public static DataPointType getDiscreteValueType() {
        return DV;
    }
}
