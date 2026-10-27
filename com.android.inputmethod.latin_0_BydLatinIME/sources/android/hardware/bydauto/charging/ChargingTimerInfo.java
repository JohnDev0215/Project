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
        if (mChargingTimerInfo == null) {
            mChargingTimerInfo = new ChargingTimerInfo(context.getApplicationContext());
        }
        return mChargingTimerInfo;
    }

    public int getTimerSwitch() {
        return this.mChargingManagerImpl.getTimerSwitch();
    }

    public int setTimerSwitch(int i) {
        return this.mChargingManagerImpl.setTimerSwitch(i);
    }

    public int getTimerType() {
        return this.mChargingManagerImpl.getTimerType();
    }

    public int setTimerType(int i) {
        return this.mChargingManagerImpl.setTimerType(i);
    }

    public int getTimerUnitHour() {
        return this.mChargingManagerImpl.getTimerUnitHour();
    }

    public int setTimerUnitHour(int i) {
        return this.mChargingManagerImpl.setTimerUnitHour(i);
    }

    public int getTimerUnitMinite() {
        return this.mChargingManagerImpl.getTimerUnitMinite();
    }

    public int setTimerUnitMinite(int i) {
        return this.mChargingManagerImpl.setTimerUnitMinite(i);
    }

    public int getTimerCycleMon() {
        return this.mChargingManagerImpl.getTimerCycleMon();
    }

    public int setTimerCycleMon(int i) {
        return this.mChargingManagerImpl.setTimerCycleMon(i);
    }

    public int getTimerCycleTue() {
        return this.mChargingManagerImpl.getTimerCycleTue();
    }

    public int setTimerCycleTue(int i) {
        return this.mChargingManagerImpl.setTimerCycleTue(i);
    }

    public int getTimerCycleWed() {
        return this.mChargingManagerImpl.getTimerCycleWed();
    }

    public int setTimerCycleWed(int i) {
        return this.mChargingManagerImpl.setTimerCycleWed(i);
    }

    public int getTimerCycleThu() {
        return this.mChargingManagerImpl.getTimerCycleThu();
    }

    public int setTimerCycleThu(int i) {
        return this.mChargingManagerImpl.setTimerCycleThu(i);
    }

    public int getTimerCycleFri() {
        return this.mChargingManagerImpl.getTimerCycleFri();
    }

    public int setTimerCycleFri(int i) {
        return this.mChargingManagerImpl.setTimerCycleFri(i);
    }

    public int getTimerCycleSat() {
        return this.mChargingManagerImpl.getTimerCycleSat();
    }

    public int setTimerCycleSat(int i) {
        return this.mChargingManagerImpl.setTimerCycleSat(i);
    }

    public int getTimerCycleSun() {
        return this.mChargingManagerImpl.getTimerCycleSun();
    }

    public int setTimerCycleSun(int i) {
        return this.mChargingManagerImpl.setTimerCycleSun(i);
    }
}
