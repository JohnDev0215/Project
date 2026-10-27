package com.byd.carsettings.system.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public enum ClockStyle implements Serializable {
    CLOSE(0),
    CLOCK_1(1),
    CLOCK_2(2),
    CLOCK_3(3);

    public int value;

    ClockStyle(int i) {
        this.value = i;
    }

    public static ClockStyle getStyle(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? CLOSE : CLOCK_3 : CLOCK_2 : CLOCK_1 : CLOSE;
    }
}
