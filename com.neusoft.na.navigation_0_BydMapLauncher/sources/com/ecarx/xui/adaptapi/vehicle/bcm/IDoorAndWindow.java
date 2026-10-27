package com.ecarx.xui.adaptapi.vehicle.bcm;

import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.Tribool;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IDoorAndWindow {

    @Deprecated
    public static final int FRONT_LEFT_DOOR = 1;

    @Deprecated
    public static final int FRONT_LEFT_WINDOW = 16;

    @Deprecated
    public static final int FRONT_RIGHT_DOOR = 4;

    @Deprecated
    public static final int FRONT_RIGHT_WINDOW = 32;

    @Deprecated
    public static final int REAR_LEFT_DOOR = 16;

    @Deprecated
    public static final int REAR_LEFT_WINDOW = 256;

    @Deprecated
    public static final int REAR_RIGHT_DOOR = 64;

    @Deprecated
    public static final int REAR_RIGHT_WINDOW = 512;
    public static final int SUNROOF_TILT_CLOSE = 0;
    public static final int SUNROOF_TILT_HALF = 1;
    public static final int SUNROOF_TILT_OPEN = 2;
    public static final int WINDOW_ROOF_ABAT_VENT = 8;
    public static final int WINDOW_ROOF_TOP = 4;

    public interface DoorStatusListener {
        void onDoorLockStatus(int i, boolean z);

        void onDoorOpenStatus(int i, boolean z);
    }

    public interface ISunroofWindowStatusListener {
        void onSunroofWindowTiltStatus(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SunroofTiltStatus {
    }

    public interface WindowStatusListener {
        void onWindowOpenStatus(int i, int i2);
    }

    void attachDoorStatusListener(DoorStatusListener doorStatusListener, int[] iArr);

    void attachSunroofWindowStatusListener(ISunroofWindowStatusListener iSunroofWindowStatusListener);

    void attachWindowStatusListener(WindowStatusListener windowStatusListener, int[] iArr);

    void detachDoorStatusListener(DoorStatusListener doorStatusListener);

    void detachSunroofWindowStatusListener(ISunroofWindowStatusListener iSunroofWindowStatusListener);

    void detachWindowStatusListener(WindowStatusListener windowStatusListener);

    int[] getSunroofWindowSupportedTiltStatus();

    int getSunroofWindowTiltStatus();

    int getWindowStatus(int i);

    Tribool isDoorLocked(int i);

    Tribool isDoorOpened(int i);

    FunctionStatus isDoorOperationSupported(int i, int i2);

    FunctionStatus isSunroofWindowTiltSupported(int i);

    Tribool isWindowOpened(int i);

    FunctionStatus isWindowOperationSupported(int i, int i2);

    boolean lockDoor(int i, boolean z);

    boolean setDoorOnOff(int i, boolean z);

    boolean setSunroofWindowTiltStatus(int i);

    boolean setWindowRolling(int i, boolean z);

    boolean setWindowRolling(boolean z);

    boolean setWindowStatus(int i, int i2);

    @Deprecated
    boolean tiltSunroofWindow(boolean z);
}
