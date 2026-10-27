package com.ecarx.xui.adaptapi.vehicle.settings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IAmbientLightAdjust {
    public static final int AMBIENT_LIGHT_COLOR_BLUE = 3;
    public static final int AMBIENT_LIGHT_COLOR_CUSTOM = 255;
    public static final int AMBIENT_LIGHT_COLOR_DEFAULT = 0;
    public static final int AMBIENT_LIGHT_COLOR_GOLD = 4;
    public static final int AMBIENT_LIGHT_COLOR_GREEN = 5;
    public static final int AMBIENT_LIGHT_COLOR_ICEBLUE = 7;
    public static final int AMBIENT_LIGHT_COLOR_PINK = 8;
    public static final int AMBIENT_LIGHT_COLOR_PURPLE = 6;
    public static final int AMBIENT_LIGHT_COLOR_RED = 1;
    public static final int AMBIENT_LIGHT_COLOR_WHITE = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AmbientColor {
    }

    public interface CallBack {
        void onColorChanged(int i);

        void onCustomColorChanged(int i);
    }

    int getAmbientLightColor();

    int getAmbientLightCustomColor();

    int[] getSupportedAmbientLightColor();

    void registerCallBack(CallBack callBack);

    void setAmbientLightColor(int i);

    void setAmbientLightCustomColor(int i);

    void unregisterCallBack(CallBack callBack);
}
