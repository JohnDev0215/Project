package android.os;

import android.os.PowerManager;
import android.view.KeyEvent;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PowerManagerInternal {
    public static final int BOOST_DISPLAY_UPDATE_IMMINENT = 1;
    public static final int BOOST_INTERACTION = 0;
    public static final int MODE_DEVICE_IDLE = 8;
    public static final int MODE_DISPLAY_INACTIVE = 9;
    public static final int MODE_DOUBLE_TAP_TO_WAKE = 0;
    public static final int MODE_EXPENSIVE_RENDERING = 6;
    public static final int MODE_FIXED_PERFORMANCE = 3;
    public static final int MODE_INTERACTIVE = 7;
    public static final int MODE_LAUNCH = 5;
    public static final int MODE_LOW_POWER = 1;
    public static final int MODE_SUSTAINED_PERFORMANCE = 2;
    public static final int MODE_VR = 4;
    public static final int WAKEFULNESS_ASLEEP = 0;
    public static final int WAKEFULNESS_AWAKE = 1;
    public static final int WAKEFULNESS_DOZING = 3;
    public static final int WAKEFULNESS_DREAMING = 2;

    public interface LowPowerModeListener {
        int getServiceType();

        void onLowPowerModeChanged(PowerSaveState powerSaveState);
    }

    public static boolean isInteractive(int i) {
        return i == 1 || i == 2;
    }

    public static int wakefulnessToProtoEnum(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    return i;
                }
            }
        }
        return i2;
    }

    public abstract void finishUidChanges();

    public abstract PowerManager.WakeData getLastWakeup();

    public abstract PowerSaveState getLowPowerState(int i);

    public abstract boolean interceptPowerKeyDown(KeyEvent keyEvent);

    public abstract void powerHint(int i, int i2);

    public abstract void registerLowPowerModeObserver(LowPowerModeListener lowPowerModeListener);

    public abstract boolean setDeviceIdleMode(boolean z);

    public abstract void setDeviceIdleTempWhitelist(int[] iArr);

    public abstract void setDeviceIdleWhitelist(int[] iArr);

    public abstract void setDozeOverrideFromDreamManager(int i, int i2);

    public abstract void setDrawWakeLockOverrideFromSidekick(boolean z);

    public abstract boolean setLightDeviceIdleMode(boolean z);

    public abstract void setMaximumScreenOffTimeoutFromDeviceAdmin(int i, long j);

    public abstract void setPowerBoost(int i, int i2);

    public abstract void setPowerMode(int i, boolean z);

    public abstract void setScreenBrightnessOverrideFromWindowManager(float f);

    public abstract void setUserActivityTimeoutOverrideFromWindowManager(long j);

    public abstract void setUserInactiveOverrideFromWindowManager();

    public abstract void startUidChanges();

    public abstract void uidActive(int i);

    public abstract void uidGone(int i);

    public abstract void uidIdle(int i);

    public abstract void updateUidProcState(int i, int i2);

    public abstract boolean wasDeviceIdleFor(long j);

    public static String wakefulnessToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? Integer.toString(i) : "Dozing" : "Dreaming" : "Awake" : "Asleep";
    }

    public void registerLowPowerModeObserver(final int i, final Consumer<PowerSaveState> consumer) {
        registerLowPowerModeObserver(new LowPowerModeListener() { // from class: android.os.PowerManagerInternal.1
            @Override // android.os.PowerManagerInternal.LowPowerModeListener
            public int getServiceType() {
                return i;
            }

            @Override // android.os.PowerManagerInternal.LowPowerModeListener
            public void onLowPowerModeChanged(PowerSaveState powerSaveState) {
                consumer.accept(powerSaveState);
            }
        });
    }
}
