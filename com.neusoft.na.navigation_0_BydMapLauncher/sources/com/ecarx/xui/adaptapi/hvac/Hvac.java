package com.ecarx.xui.adaptapi.hvac;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.hvac.impl.HvacImpl;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Hvac extends AdaptAPI {
    public static final int HVAC_TYPE_AUTOMATIC = 2;
    public static final int HVAC_TYPE_ELECTRIC = 4;
    public static final int HVAC_TYPE_MANUAL = 1;
    private static final String TAG = "Hvac";

    @Retention(RetentionPolicy.SOURCE)
    @interface HvacType {
    }

    public abstract IAQSState getAQSState();

    public abstract int getAutoModeMaxBlowingLevel();

    public abstract IHvacAutoVentilation getAutoVentilation();

    public abstract int getHeatingSupportedSeatCount();

    public abstract IHvacState getHvacState();

    public abstract int getHvacType();

    public abstract double[] getLeftTemperatureRange();

    public abstract int getMassageSupportedSeatCount();

    public abstract int getMaxBlowingLevel();

    public abstract double[] getRightTemperatureRange();

    public abstract int[] getSeatHeatingDurationForSettingDisplay();

    public abstract int getSeatHeatingDurationInMinute(int i);

    public abstract int[] getSeatMassageDurationForSettingDisplay();

    public abstract int getSeatMassageDurationInMinute(int i);

    public abstract int[] getSeatVentilationDurationForSettingDisplay();

    public abstract int getSeatVentilationDurationInMinute(int i);

    public abstract int[] getSupportedFragrancesType();

    public abstract int[] getSupportedFunctionSubs(int i);

    public abstract double getTemperatureAdjustStepping();

    public abstract IHvacTouchBoard getTouchBoard();

    public abstract int getVentilationSupportedSeatCount();

    public abstract FunctionStatus isFunctionSupported(int i);

    public abstract FunctionStatus isFunctionSupportedByOperation(int i, int i2);

    public abstract FunctionStatus isFunctionSupportedByTarget(int i, int i2);

    public abstract boolean isHavcAvailable();

    public abstract boolean isHavcDisplayOnIHURequired();

    public abstract boolean isSeatHeatingSupported(int i);

    public abstract boolean isSeatMassageSupported(int i);

    public abstract boolean isSeatVentilationSupported(int i);

    public abstract boolean isSupportAirConditionerAutoMode();

    @Deprecated
    public abstract boolean isSupportAuto();

    @Deprecated
    public abstract FunctionStatus isSupportBlowingMode(int i);

    @Deprecated
    public abstract boolean isSupportDriverSeatHeating();

    public abstract boolean isSupportDual();

    public abstract boolean isSupportIon();

    public abstract boolean isSupportPM25();

    @Deprecated
    public abstract boolean isSupportPassegerSeatHeating();

    public abstract boolean isSupportSteeringWheelHeating();

    public abstract boolean setAQS(boolean z);

    public abstract boolean setAc(boolean z);

    @Deprecated
    public abstract boolean setAcAuto(boolean z);

    public abstract boolean setAcCZISOpen(boolean z);

    public abstract boolean setAcComfortLevel(int i);

    public abstract boolean setAcEco(boolean z);

    public abstract boolean setAcMax(boolean z);

    public abstract boolean setAcPTCHeat(boolean z);

    public abstract boolean setAcVentilaDry(boolean z);

    public abstract boolean setAirCoditionerAutoMode(int i);

    public abstract boolean setAirCoditionerAutoMode(boolean z);

    public abstract boolean setAirRaiseWindowSwitch(boolean z);

    public abstract boolean setAutoFrontDefrosting(boolean z);

    public abstract boolean setAutoRearDefrosting(boolean z);

    public abstract boolean setAutoSeatHeatingDuration(int i, int i2);

    public abstract boolean setAutoSeatHeatingLevel(int i, int i2);

    public abstract boolean setAutoSeatHeatingOnOff(boolean z, int i);

    public abstract boolean setAutoSeatMassageOnOff(boolean z, int i);

    public abstract boolean setAutoSeatVentilationDuration(int i, int i2);

    public abstract boolean setAutoSeatVentilationLevel(int i, int i2);

    public abstract boolean setBlowingLevel(int i);

    public abstract boolean setBlowingMode(int i);

    public abstract void setCallback(IHvacCallback iHvacCallback);

    public abstract boolean setDoubleAreaTemperature(double d, double d2);

    @Deprecated
    public abstract boolean setDriverSeatHeating(int i);

    public abstract boolean setDual(boolean z);

    public abstract boolean setFragranceLevel(int i);

    public abstract boolean setFragranceType(int i);

    public abstract boolean setFrontDefrosting(boolean z);

    public abstract boolean setFrontDefrostingMax(boolean z);

    public abstract boolean setHavcPower(boolean z);

    public abstract boolean setHvacControlLockedInRow(int i, boolean z);

    public abstract boolean setHvacOnOffInVehicleZone(int i, boolean z);

    public abstract boolean setInternalLoopTimerOnOff(boolean z);

    public abstract boolean setIonsMode(int i);

    public abstract boolean setLeftAreaTemperature(double d);

    public abstract boolean setLoopMode(int i);

    public abstract boolean setParkingAirPreConditioningOnOff(boolean z);

    public abstract boolean setParkingSeatHeatPreConditioning(int i);

    @Deprecated
    public abstract boolean setPassegerSeatHeating(int i);

    public abstract boolean setPm25Warning(boolean z);

    public abstract boolean setRCP(boolean z);

    public abstract boolean setRearDefrosting(boolean z);

    public abstract boolean setRearDefrostingMax(boolean z);

    public abstract boolean setRightAreaTemperature(double d);

    public abstract boolean setSeatHeating(int i, int i2);

    public abstract boolean setSeatHeatingDuration(int i, int i2);

    public abstract boolean setSeatMassage(int i, int i2);

    public abstract boolean setSeatMassageDuration(int i, int i2);

    public abstract boolean setSeatVentilation(int i, int i2);

    public abstract boolean setSeatVentilationAutoModeOnOff(int i, boolean z);

    public abstract boolean setSeatVentilationDuration(int i, int i2);

    public abstract boolean setSteeringWheelHeatingOnOff(boolean z);

    public abstract boolean setSupportedFunctionOn(int i, boolean z);

    public abstract boolean setThridPanel(boolean z);

    public abstract void unsetCallback(IHvacCallback iHvacCallback);

    public static Hvac create(Context context) {
        return new HvacImpl(context);
    }
}
