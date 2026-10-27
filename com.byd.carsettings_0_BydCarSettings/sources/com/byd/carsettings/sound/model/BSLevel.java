package com.byd.carsettings.sound.model;

import b.b.g.o.h.b;

/* JADX INFO: loaded from: classes2.dex */
public enum BSLevel implements b {
    NORMAL(0),
    KUNPENG(1);

    public int value;

    BSLevel(int i) {
        this.value = i;
    }

    public static BSLevel getBSLevel(int i) {
        return i != 0 ? i != 1 ? NORMAL : KUNPENG : NORMAL;
    }
}
