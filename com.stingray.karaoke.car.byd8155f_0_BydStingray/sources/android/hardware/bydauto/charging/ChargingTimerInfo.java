package android.hardware.bydauto.charging;

import android.content.Context;
import android.hardware.bydauto.charging.tsimpl.ChargingManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class ChargingTimerInfo {
    public static final int TIMER_24H_HOUR_INVALID = 24;
    public static final int TIMER_24H_HOUR_MAX = 23;
    public static final int TIMER_24H_HOUR_MIN = 0;
    public static final int TIMER_24H_MINITE_INVALID = 60;
    public static final int TIMER_24H_MINITE_MAX = 59;
    public static final int TIMER_24H_MINITE_MIN = 0;
    public static final int TIMER_CYCLE_DAY_DISABLE = 0;
    public static final int TIMER_CYCLE_DAY_ENABLE = 1;
    public static final int TIMER_SWITCH_INVALID = -1;
    public static final int TIMER_SWITCH_OFF = 0;
    public static final int TIMER_SWITCH_ON = 1;
    public static final int TIMER_TYPE_1 = 3;
    public static final int TIMER_TYPE_2 = 4;
    private static volatile ChargingTimerInfo mChargingTimerInfo;
    private ChargingManagerImpl mChargingManagerImpl;
    private Context mContext;

    public ChargingTimerInfo(Context context) {
        this.mChargingManagerImpl = null;
        this.mChargingManagerImpl = ChargingManagerImpl.getInstance(context);
    }

    public static synchronized ChargingTimerInfo getInstance(Context context) {
        try {
            if (mChargingTimerInfo == null) {
                mChargingTimerInfo = new ChargingTimerInfo(context.getApplicationContext());
            }
        } catch (Throwable th) {
            throw th;
        }
        return mChargingTimerInfo;
    }

    public int getTimerCycleFri() {
        return this.mChargingManagerImpl.getTimerCycleFri();
    }

    public int getTimerCycleMon() {
        return this.mChargingManagerImpl.getTimerCycleMon();
    }

    public int getTimerCycleSat() {
        return this.mChargingManagerImpl.getTimerCycleSat();
    }

    public int getTimerCycleSun() {
        return this.mChargingManagerImpl.getTimerCycleSun();
    }

    public int getTimerCycleThu() {
        return this.mChargingManagerImpl.getTimerCycleThu();
    }

    public int getTimerCycleTue() {
        return this.mChargingManagerImpl.getTimerCycleTue();
    }

    public int getTimerCycleWed() {
        return this.mChargingManagerImpl.getTimerCycleWed();
    }

    public int getTimerSwitch() {
        return this.mChargingManagerImpl.getTimerSwitch();
    }

    public int getTimerType() {
        return this.mChargingManagerImpl.getTimerType();
    }

    public int getTimerUnitHour() {
        return this.mChargingManagerImpl.getTimerUnitHour();
    }

    public int getTimerUnitMinite() {
        return this.mChargingManagerImpl.getTimerUnitMinite();
    }

    public int setTimerCycleFri(int i7) {
        return this.mChargingManagerImpl.setTimerCycleFri(i7);
    }

    public int setTimerCycleMon(int i7) {
        return this.mChargingManagerImpl.setTimerCycleMon(i7);
    }

    public int setTimerCycleSat(int i7) {
        return this.mChargingManagerImpl.setTimerCycleSat(i7);
    }

    public int setTimerCycleSun(int i7) {
        return this.mChargingManagerImpl.setTimerCycleSun(i7);
    }

    public int setTimerCycleThu(int i7) {
        return this.mChargingManagerImpl.setTimerCycleThu(i7);
    }

    public int setTimerCycleTue(int i7) {
        return this.mChargingManagerImpl.setTimerCycleTue(i7);
    }

    public int setTimerCycleWed(int i7) {
        return this.mChargingManagerImpl.setTimerCycleWed(i7);
    }

    public int setTimerSwitch(int i7) {
        return this.mChargingManagerImpl.setTimerSwitch(i7);
    }

    public int setTimerType(int i7) {
        return this.mChargingManagerImpl.setTimerType(i7);
    }

    public int setTimerUnitHour(int i7) {
        return this.mChargingManagerImpl.setTimerUnitHour(i7);
    }

    public int setTimerUnitMinite(int i7) {
        return this.mChargingManagerImpl.setTimerUnitMinite(i7);
    }
}
