package com.ecarx.xui.adaptapi.vehicle.newenergy;

import com.ecarx.xui.adaptapi.FunctionStatus;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IChargeState {
    public static final int CHARGE_PLUG_STATE_CHARGING = 2;
    public static final int CHARGE_PLUG_STATE_COMPLETED = 3;
    public static final int CHARGE_PLUG_STATE_PREPARED = 1;
    public static final int CHARGE_PLUG_STATE_UNKNOW = 0;
    public static final int PRECHARGE_STATE_CANCEL_FAILURE = 4;
    public static final int PRECHARGE_STATE_CANCEL_SUCCEED = 3;
    public static final int PRECHARGE_STATE_SET_FAILURE = 2;
    public static final int PRECHARGE_STATE_SET_SUCCEED = 1;
    public static final int PRECHARGE_STATE_UNKNOW = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ChargePlugState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PreChargeState {
    }

    int getChargePlugState();

    long getPreChargeDelayTime();

    long getPreChargeDuration();

    long getPreChargeStartTime();

    int getPreChargeState();

    FunctionStatus isChargeSupported();

    boolean isPreChargeOutofDate();

    boolean isPreChargePerDay();
}
