package com.ecarx.xui.adaptapi.vehicle.settings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ITrunkLid {
    public static final int TRUNK_LID_OPEN_LEVEL_1 = 1;
    public static final int TRUNK_LID_OPEN_LEVEL_2 = 2;
    public static final int TRUNK_LID_OPEN_LEVEL_3 = 3;
    public static final int TRUNK_LID_OPEN_LEVEL_4 = 4;
    public static final int TRUNK_LID_OPEN_LEVEL_5 = 5;
    public static final int TRUNK_LID_OPEN_LEVEL_DEFAULT = 2147483637;
    public static final int TRUNK_LID_OPEN_LEVEL_OFF = 0;

    public interface CallBack {
        void onLevelChanged(int i);

        void onLevelSettingChanged(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OpenLevel {
    }

    int getOpenLevelSetting();

    void registerCallBack(CallBack callBack);

    void setOpenLevelSetting(int i);

    void unregisterCallBack(CallBack callBack);
}
