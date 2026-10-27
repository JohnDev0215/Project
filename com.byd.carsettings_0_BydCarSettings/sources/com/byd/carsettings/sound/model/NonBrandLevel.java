package com.byd.carsettings.sound.model;

import b.b.g.o.h.b;

/* JADX INFO: loaded from: classes2.dex */
public enum NonBrandLevel implements b {
    NORMAL(0),
    NO_SOUND_EFFECTS(1);

    public int value;

    NonBrandLevel(int i) {
        this.value = i;
    }

    public static NonBrandLevel getDiracLevel(int i) {
        return i != 0 ? i != 1 ? NORMAL : NO_SOUND_EFFECTS : NORMAL;
    }
}
