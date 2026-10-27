package com.byd.carsettings.sound.model;

import b.b.g.o.h.b;

/* JADX INFO: loaded from: classes2.dex */
public enum DiracLevel implements b {
    DIRAC_SIMPLE(0),
    DIRAC_PERFORMANCE(1),
    DIRAC_LIVE(2);

    public int value;

    DiracLevel(int i) {
        this.value = i;
    }

    public static DiracLevel getDiracLevel(int i) {
        return i != 0 ? i != 1 ? i != 2 ? DIRAC_SIMPLE : DIRAC_LIVE : DIRAC_PERFORMANCE : DIRAC_SIMPLE;
    }
}
