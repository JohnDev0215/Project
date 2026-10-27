package com.ecarx.xui.adaptapi.vehicle.bcm;

import com.ecarx.xui.adaptapi.FunctionStatus;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IBcm {
    public static final int WIPER_GEAR_AUTO = 5;
    public static final int WIPER_GEAR_HIGH = 3;
    public static final int WIPER_GEAR_INTERMITTENT = 4;
    public static final int WIPER_GEAR_LOW = 2;
    public static final int WIPER_GEAR_OFF = 0;
    public static final int WIPER_GEAR_ON = 1;
    public static final int WIPER_STATUS_HIGH_SPEED = 3;
    public static final int WIPER_STATUS_LOW_SPEED = 2;
    public static final int WIPER_STATUS_NORMAL_SPEED = 1;
    public static final int WIPER_STATUS_STATIONARY = 0;
    public static final int WIPER_TYPE_REAR_WIPER = 1;
    public static final int WIPER_TYPE_WINDSCREEN_WIPER = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface WiperGear {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WiperStatus {
    }

    public interface WiperStatusListener {
        void onWiperGearChanged(int i, int i2);

        void onWiperStatusChanged(int i, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WiperType {
    }

    void attachWiperStatusListener(WiperStatusListener wiperStatusListener);

    void detachWiperStatusListener(WiperStatusListener wiperStatusListener);

    IDoorAndWindow getDoorAndWindowState();

    ILighting getLightingState();

    IWasher getWasherState();

    int getWiperGear(int i);

    int getWiperStatus(int i);

    FunctionStatus isWiperOperationSupported(int i, int i2);

    boolean setWiperGear(int i, int i2);
}
