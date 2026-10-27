package com.byd.car.iot;

/* JADX INFO: loaded from: classes.dex */
public enum ReadStatusEnum {
    INVALID(0),
    READ_SUCCESS(1),
    READ_FAILED(2),
    DEVICE_DISCONNECTED(3),
    UNSUPPORTED(4),
    ILLEGAL_PARAM(5),
    LOW_BATTERY(6);

    public final int code;

    ReadStatusEnum(int i) {
        this.code = i;
    }

    public static ReadStatusEnum indexOf(int i) {
        for (ReadStatusEnum readStatusEnum : values()) {
            if (readStatusEnum.ordinal() == i) {
                return readStatusEnum;
            }
        }
        return INVALID;
    }
}
