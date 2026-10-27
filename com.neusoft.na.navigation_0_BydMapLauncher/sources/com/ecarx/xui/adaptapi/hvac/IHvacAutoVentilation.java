package com.ecarx.xui.adaptapi.hvac;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface IHvacAutoVentilation {
    public static final int CUSTOM_VENTILATION_AFTERNOON = 1;
    public static final int CUSTOM_VENTILATION_EVENING = 2;
    public static final int CUSTOM_VENTILATION_MORNING = 0;
    public static final int MODE_VENTILATION_CUSTOM = 1;
    public static final int MODE_VENTILATION_DEFAULT = 0;
    public static final int MODE_VENTILATION_OFF = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CustomVentilation {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VentilationMode {
    }

    String getCustomVentilationTime(int i);

    Map<String, Integer> getSupportedCustomVentilationTimes(int i);

    int[] getSupportedCustomVentilations();

    int[] getSupportedVentilationModes();

    int getVentilationMode();

    boolean setCustomVentilationTime(int i, int i2);

    boolean setVentilationMode(int i);
}
