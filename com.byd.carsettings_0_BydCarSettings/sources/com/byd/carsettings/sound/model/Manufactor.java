package com.byd.carsettings.sound.model;

/* JADX INFO: loaded from: classes2.dex */
public enum Manufactor {
    NON_BRAND,
    DIRAC,
    DYNAUDIO,
    DEVIALET,
    HARMAN,
    BS;


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Manufactor[] f2867a;

    static {
        Manufactor manufactor = NON_BRAND;
        f2867a = new Manufactor[]{DYNAUDIO, BS, manufactor};
    }

    public static boolean supported(Manufactor manufactor) {
        for (Manufactor manufactor2 : f2867a) {
            if (manufactor2 == manufactor) {
                return true;
            }
        }
        return false;
    }
}
