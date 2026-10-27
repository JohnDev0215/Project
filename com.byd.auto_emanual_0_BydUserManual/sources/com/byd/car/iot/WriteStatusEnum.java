package com.byd.car.iot;

/* JADX INFO: loaded from: classes.dex */
public enum WriteStatusEnum {
    INVALID(0),
    CONTROL_SUCCESS(1),
    CONTROL_FAILED(2),
    DEVICE_DISCONNECTED(3),
    UNSUPPORTED(4),
    ILLEGAL_PARAM(5),
    EXECUTE_FINISHED(6),
    EXECUTE_FAILED(7),
    EXECUTING(8),
    LOW_BATTERY(9),
    IS_EXECUTED(10);

    public final int code;

    WriteStatusEnum(int i) {
        this.code = i;
    }
}
