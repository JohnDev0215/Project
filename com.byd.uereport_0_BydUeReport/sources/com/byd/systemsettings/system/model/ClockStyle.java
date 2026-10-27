package com.byd.systemsettings.system.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public enum ClockStyle implements Serializable {
    CLOSE(0),
    CLOCK_1(1),
    CLOCK_2(2),
    CLOCK_3(3);

    public int value;

    ClockStyle(int i8) {
        this.value = i8;
    }

    public static ClockStyle getStyle(int i8) {
        return i8 != 0 ? i8 != 1 ? i8 != 2 ? i8 != 3 ? CLOSE : CLOCK_3 : CLOCK_2 : CLOCK_1 : CLOSE;
    }
}
