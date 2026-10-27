package com.ecarx.xui.adaptapi.vehicle.settings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IBackLightAdjust {
    public static final int BACK_LIGHT_ADJUST_LEVEL_1 = 1;
    public static final int BACK_LIGHT_ADJUST_LEVEL_10 = 10;
    public static final int BACK_LIGHT_ADJUST_LEVEL_11 = 11;
    public static final int BACK_LIGHT_ADJUST_LEVEL_12 = 12;
    public static final int BACK_LIGHT_ADJUST_LEVEL_13 = 13;
    public static final int BACK_LIGHT_ADJUST_LEVEL_14 = 14;
    public static final int BACK_LIGHT_ADJUST_LEVEL_15 = 15;
    public static final int BACK_LIGHT_ADJUST_LEVEL_16 = 16;
    public static final int BACK_LIGHT_ADJUST_LEVEL_2 = 2;
    public static final int BACK_LIGHT_ADJUST_LEVEL_3 = 3;
    public static final int BACK_LIGHT_ADJUST_LEVEL_4 = 4;
    public static final int BACK_LIGHT_ADJUST_LEVEL_5 = 5;
    public static final int BACK_LIGHT_ADJUST_LEVEL_6 = 6;
    public static final int BACK_LIGHT_ADJUST_LEVEL_7 = 7;
    public static final int BACK_LIGHT_ADJUST_LEVEL_8 = 8;
    public static final int BACK_LIGHT_ADJUST_LEVEL_9 = 9;
    public static final int BACK_LIGHT_ADJUST_LEVEL_DEFAULT = 2147483637;
    public static final int BACK_LIGHT_ADJUST_LEVEL_OFF = 0;

    public interface CallBack {
        void onLevelChanged(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Level {
    }

    int getLevel();

    void registerCallBack(CallBack callBack);

    void setLevel(int i);

    void unregisterCallBack(CallBack callBack);
}
