package com.byd.carsettings.model;

/* JADX INFO: loaded from: classes2.dex */
public enum CommonItemId {
    ATMOSPHERE_LAMP_COLOR(0),
    ATMOSPHERE_LAMP_BRIGHTNESS(1),
    SOC_SETTINGS(2),
    ENERGY_FEEDBACK(3),
    REGULATE_INSTRUMENT_BRIGHTNESS(4),
    POWER_STEERING_MODE(5),
    SIMULATION_ENGINE_SOUND(6),
    ACTIVE_SUSPENSION(7),
    ATMOSPHERE_LAMP_AREA(8),
    ATMOSPHERE_LAMP_MUSIC_RHYTHM(9),
    REGULATE_MEDIA_BRIGHTNESS(10),
    DARK_LIGHT_MODE(11);

    private final int value;

    CommonItemId(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
