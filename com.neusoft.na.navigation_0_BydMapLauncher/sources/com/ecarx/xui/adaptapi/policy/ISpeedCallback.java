package com.ecarx.xui.adaptapi.policy;

/* JADX INFO: loaded from: classes2.dex */
public interface ISpeedCallback {
    public static final int SPEED_DOWN_BIG_RAIN = 1;
    public static final int SPEED_DOWN_UNKNOWN = -2147483646;

    public @interface SpeedDownReason {
    }

    void onSpeedDownReminder(int i);
}
