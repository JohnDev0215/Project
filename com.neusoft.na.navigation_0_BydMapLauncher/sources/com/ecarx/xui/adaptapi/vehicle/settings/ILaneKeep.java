package com.ecarx.xui.adaptapi.vehicle.settings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ILaneKeep {
    public static final int LANE_KEEP_AID_LEVEL_ALARM = 1;
    public static final int LANE_KEEP_AID_LEVEL_HIGHT = 3;
    public static final int LANE_KEEP_AID_LEVEL_LOW = 2;
    public static final int LANE_KEEP_AID_LEVEL_OFF = 0;
    public static final int LANE_KEEP_AID_LEVEL_ON = 255;

    public interface CallBack {
        void onLevelChangedPeriodic(int i);

        void onLevelSetChanged(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LaneKeepAidLevel {
    }

    int getLevel();

    void registerCallBack(CallBack callBack);

    void setLevel(int i);

    void unregisterCallBack(CallBack callBack);
}
