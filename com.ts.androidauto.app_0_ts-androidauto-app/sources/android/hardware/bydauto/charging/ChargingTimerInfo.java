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
    private static volatile ChargingTimerInfo mChargingTimerInfo = null;
    private ChargingManagerImpl mChargingManagerImpl;
    private Context mContext;

    public ChargingTimerInfo(Context con) {
        this.mChargingManagerImpl = null;
        this.mChargingManagerImpl = ChargingManagerImpl.getInstance(con);
    }

    public static synchronized ChargingTimerInfo getInstance(Context con) {
        if (mChargingTimerInfo == null) {
            mChargingTimerInfo = new ChargingTimerInfo(con.getApplicationContext());
        }
        return mChargingTimerInfo;
    }

    public int getTimerSwitch() {
        return this.mChargingManagerImpl.getTimerSwitch();
    }

    public int setTimerSwitch(int timerSwitch) {
        return this.mChargingManagerImpl.setTimerSwitch(timerSwitch);
    }

    public int getTimerType() {
        return this.mChargingManagerImpl.getTimerType();
    }

    public int setTimerType(int timerType) {
        return this.mChargingManagerImpl.setTimerType(timerType);
    }

    public int getTimerUnitHour() {
        return this.mChargingManagerImpl.getTimerUnitHour();
    }

    public int setTimerUnitHour(int timerUnitHour) {
        return this.mChargingManagerImpl.setTimerUnitHour(timerUnitHour);
    }

    public int getTimerUnitMinite() {
        return this.mChargingManagerImpl.getTimerUnitMinite();
    }

    public int setTimerUnitMinite(int timerUnitMinite) {
        return this.mChargingManagerImpl.setTimerUnitMinite(timerUnitMinite);
    }

    public int getTimerCycleMon() {
        return this.mChargingManagerImpl.getTimerCycleMon();
    }

    public int setTimerCycleMon(int timerCycleMon) {
        return this.mChargingManagerImpl.setTimerCycleMon(timerCycleMon);
    }

    public int getTimerCycleTue() {
        return this.mChargingManagerImpl.getTimerCycleTue();
    }

    public int setTimerCycleTue(int timerCycleTue) {
        return this.mChargingManagerImpl.setTimerCycleTue(timerCycleTue);
    }

    public int getTimerCycleWed() {
        return this.mChargingManagerImpl.getTimerCycleWed();
    }

    public int setTimerCycleWed(int timerCycleWed) {
        return this.mChargingManagerImpl.setTimerCycleWed(timerCycleWed);
    }

    public int getTimerCycleThu() {
        return this.mChargingManagerImpl.getTimerCycleThu();
    }

    public int setTimerCycleThu(int timerCycleThu) {
        return this.mChargingManagerImpl.setTimerCycleThu(timerCycleThu);
    }

    public int getTimerCycleFri() {
        return this.mChargingManagerImpl.getTimerCycleFri();
    }

    public int setTimerCycleFri(int timerCycleFri) {
        return this.mChargingManagerImpl.setTimerCycleFri(timerCycleFri);
    }

    public int getTimerCycleSat() {
        return this.mChargingManagerImpl.getTimerCycleSat();
    }

    public int setTimerCycleSat(int timerCycleSat) {
        return this.mChargingManagerImpl.setTimerCycleSat(timerCycleSat);
    }

    public int getTimerCycleSun() {
        return this.mChargingManagerImpl.getTimerCycleSun();
    }

    public int setTimerCycleSun(int timerCycleSun) {
        return this.mChargingManagerImpl.setTimerCycleSun(timerCycleSun);
    }
}
