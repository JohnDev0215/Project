package com.ecarx.xui.adaptapi.navigation.speed;

/* JADX INFO: loaded from: classes2.dex */
public interface ISpeedCallback {
    public static final int SPEED_DOWN_BIG_RAIN = 1;
    public static final int SPEED_DOWN_TSR = 2;
    public static final int SPEED_DOWN_UNKNOWN = -2147483646;

    public @interface SpeedDownReason {
    }

    void onSpeedDownReminder(int i);

    void onSpeedLimitEnableChanged(boolean z);

    void onTsrSpeedLimit(int i);
}
