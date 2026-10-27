package com.ecarx.xui.adaptapi.hvac.impl;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.hvac.IHvacState;
import com.ecarx.xui.adaptapi.hvac.specific.AcModelHelper;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class HvacState implements IHvacState {
    private static final String TAG = "Hvac";
    private int mADSActive;
    private boolean mAcAutoOn;
    private int mAcComfortLevel;
    private boolean mAcMultipleTemp;
    private boolean mAcPTCHeat;
    private boolean mAcPanelActive;
    private boolean mAcRcp;
    private boolean mAcSeatDisplay;
    private boolean mAcThirdPanel;
    private int mAmbientPM25AQI;
    private int mAmbientPM25AQILevel;
    private int mAmbientTemperature;
    private boolean mBackDefrosterActive;
    private int mBlowingLevel;
    private int mBlowingMode;
    private Context mContext;
    private boolean mDisplayActive;
    private int mDisplayWindowType;
    private int mFraganceActive;
    private int mFraganceLevel;
    private int mFraganceType;
    private int mIonsMode;
    private boolean mIsACLostConnection;
    private boolean mIsAcCZISOpen;
    private boolean mIsAcEco;
    private boolean mIsAcMax;
    private boolean mIsAcOn;
    private boolean mIsAcVentilaDry;
    private boolean mIsAqsOn;
    private boolean mIsDualOn;
    private boolean mIsFrontDefrosting;
    private boolean mIsHvacOn;
    private boolean mIsRearDefrosting;
    private boolean mLeftAutoSeatHeating;
    private int mLeftHeatingLevel;
    private int mLeftMassageLevel;
    private int mLeftTemperature;
    private int mLeftVentilationLevel;
    private boolean mLoopMode;
    private int mPM25AQI;
    private int mPM25AQILevel;
    private boolean mParkingSwitch;
    private boolean mPmSwitch;
    private boolean mRearLock;
    private boolean mRightAutoSeatHeating;
    private int mRightHeatingLevel;
    private int mRightTemperature;
    private int mRightVentilationLevel;

    private int getFragranceLevel(int i) {
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? -2147483647 : 3;
        }
        return 2;
    }

    private int getFragranceType(int i) {
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? -2147483647 : 3;
        }
        return 2;
    }

    private int getHeatSeatLevel(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 2147483645;
        }
        if (i == 2) {
            return 2147483646;
        }
        if (i == 3) {
            return Integer.MAX_VALUE;
        }
        if (i != 4) {
            return i != 7 ? -2147483647 : -2147483646;
        }
        return 2147483644;
    }

    private int getMassageSeatLevel(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 2147483645;
        }
        if (i == 2) {
            return 2147483646;
        }
        if (i != 3) {
            return i != 7 ? -2147483647 : -2147483646;
        }
        return Integer.MAX_VALUE;
    }

    private int getVentilationSeatLevel(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 2147483645;
        }
        if (i == 2) {
            return 2147483646;
        }
        if (i != 3) {
            return i != 7 ? -2147483647 : -2147483646;
        }
        return Integer.MAX_VALUE;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getAQSLevel() {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getAirCoditionerAutoMode() {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getAmbientAQSLevel() {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public double getAmbientTemperature() {
        return -500.0d;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getAutoSeatHeatingDuration(int i) {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getAutoSeatHeatingLevel(int i) {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getAutoSeatVentilationDuration(int i) {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getAutoSeatVentilationLevel(int i) {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getHvacTemperatureTendency(int i) {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getParkingSeatHeatPreConditioning() {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getTemperatureUnit() {
        return 1;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getVehicleZoneUnderHvacControl() {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAirRaiseWindowSwitchOn() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAutoFrontDefrosting() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAutoRearDefrosting() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAutoSeatventilationOn(int i) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public FunctionStatus isBlowingModeAvaiable(int i) {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isFrontDefrostingMax() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isInternalLoopTimerOn() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public FunctionStatus isLoopModeAvaiable(int i) {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isRearDefrostingMax() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isSteeringWheelHeatingOn() {
        return false;
    }

    public HvacState(Context context) {
        this.mContext = context;
    }

    public void notifyHvacOn(boolean z) {
        this.mIsHvacOn = z;
    }

    public void notifyADS(int i) {
        this.mADSActive = i;
    }

    public void notifyFragance(int i) {
        this.mFraganceActive = i;
    }

    public void notifyFraganceLevel(int i) {
        this.mFraganceLevel = i;
    }

    public void notifyFraganceType(int i) {
        this.mFraganceType = i;
    }

    public void notifyAcOn(boolean z) {
        this.mIsAcOn = z;
    }

    public void notifyAcAutoOn(boolean z) {
        this.mAcAutoOn = z;
    }

    public void notifyAcMaxOn(boolean z) {
        this.mIsAcMax = z;
    }

    public void notifyBlowingMode(int i) {
        this.mBlowingMode = i;
    }

    public void notifyBlowingLevel(int i) {
        this.mBlowingLevel = i;
    }

    public void notifyFrontDefrostingOn(boolean z) {
        this.mIsFrontDefrosting = z;
    }

    public void notifyRearDefrostingOn(boolean z) {
        this.mIsRearDefrosting = z;
    }

    public void notifyLoopMode(boolean z) {
        this.mLoopMode = z;
    }

    public void notifyLeftTemperature(int i) {
        this.mLeftTemperature = i;
    }

    public void notifyRightTemperature(int i) {
        this.mRightTemperature = i;
    }

    public void notifyAmbientTemperature(int i) {
        this.mAmbientTemperature = i;
    }

    public void notifyIonsMode(int i) {
        this.mIonsMode = i;
    }

    public void notifyDualOn(boolean z) {
        this.mIsDualOn = z;
    }

    public void notifyPM25AQI(int i) {
        this.mPM25AQI = i;
    }

    public void notifyPM25AQILevel(int i) {
        this.mPM25AQILevel = i;
    }

    public void notifyAmbientPM25AQI(int i) {
        this.mAmbientPM25AQI = i;
    }

    public void notifyAmbientPM25AQILevel(int i) {
        this.mAmbientPM25AQILevel = i;
    }

    public void notifyAqsOn(boolean z) {
        this.mIsAqsOn = z;
    }

    public void notifyAutoLeftHeatingSwitch(boolean z) {
        this.mLeftAutoSeatHeating = z;
    }

    public void notifyAutoRightHeatingSwitch(boolean z) {
        this.mRightAutoSeatHeating = z;
    }

    public void notifyLeftHeatingLevel(int i) {
        this.mLeftHeatingLevel = i;
    }

    public void notifyRightHeatingLevel(int i) {
        this.mRightHeatingLevel = i;
    }

    public void notifyLeftVentilationLevel(int i) {
        this.mLeftVentilationLevel = i;
    }

    public void notifyRightVentilationLevel(int i) {
        this.mRightVentilationLevel = i;
    }

    public void notifyLeftMassageLevel(int i) {
        this.mLeftMassageLevel = i;
    }

    public void notifyDisplayActive(int i) {
        boolean z = i == 1;
        this.mDisplayActive = z;
        this.mAcPanelActive = z || this.mBackDefrosterActive;
    }

    public void notifyRearLock(boolean z) {
        this.mRearLock = z;
    }

    public void notifyPmSwitch(boolean z) {
        this.mPmSwitch = z;
    }

    public void notifyParkingSwitch(boolean z) {
        this.mParkingSwitch = z;
    }

    public void notifyBackDefrosterActive(int i) {
        boolean z = i == 1;
        this.mBackDefrosterActive = z;
        this.mAcPanelActive = this.mDisplayActive || z;
    }

    public void notifyDisplayWindowType(int i) {
        Log.d(TAG, "windowType=" + i);
        this.mDisplayWindowType = i;
    }

    public void notifyAcMultipleTemp(boolean z) {
        this.mAcMultipleTemp = z;
    }

    public void notifyAcThirdPanel(boolean z) {
        this.mAcThirdPanel = z;
    }

    public void notifyAcRcp(boolean z) {
        this.mAcRcp = z;
    }

    public void notifyAcPTCHeat(boolean z) {
        this.mAcPTCHeat = z;
    }

    public void notifyAcEco(boolean z) {
        this.mIsAcEco = z;
    }

    public void notifyAcVentilaDry(boolean z) {
        this.mIsAcVentilaDry = z;
    }

    public void notifyAcCZISOpen(boolean z) {
        this.mIsAcCZISOpen = z;
    }

    public void notifyAcComfortLevel(int i) {
        this.mAcComfortLevel = i;
    }

    public void notifyAcSeatDisplay(boolean z) {
        this.mAcSeatDisplay = z;
    }

    public void notifyIsAcLostConnection(boolean z) {
        this.mIsACLostConnection = z;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isHvacOn() {
        Log.d(TAG, "isHvacOn=" + this.mIsHvacOn);
        return this.mIsHvacOn;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean getHvacOnOffInVehicleZone(int i) {
        if (i == 32) {
            Log.d(TAG, "getHvacOnOffInVehicleZone,zone=" + i + ",mAcRcp=" + this.mAcRcp);
            return this.mAcRcp;
        }
        if (i != 512) {
            return false;
        }
        Log.d(TAG, "getHvacOnOffInVehicleZone,zone=" + i + ",mAcThirdPanel=" + this.mAcThirdPanel);
        return this.mAcThirdPanel;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAcOn() {
        Log.d(TAG, "isAcOn=" + this.mIsAcOn);
        return this.mIsAcOn;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    @Deprecated
    public boolean isAcAutoOn() {
        Log.d(TAG, "isAcAutoOn=" + this.mAcAutoOn);
        return this.mAcAutoOn;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAirConditionerAutoModeOn() {
        Log.d(TAG, "isAirConditionerAutoModeOn=" + this.mAcAutoOn);
        return this.mAcAutoOn;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAcMax() {
        Log.d(TAG, "isAcMax=" + this.mIsAcMax);
        return this.mIsAcMax;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getBlowingMode() {
        int i;
        switch (this.mBlowingMode) {
            case 0:
                i = 1;
                break;
            case 1:
                i = 3;
                break;
            case 2:
                i = 2;
                break;
            case 3:
                i = 6;
                break;
            case 4:
                i = 4;
                break;
            case 5:
                i = 5;
                break;
            case 6:
                i = 7;
                break;
            default:
                i = -2147483647;
                break;
        }
        Log.d(TAG, "getBlowingMode=" + i + ",mBlowingMode=" + this.mBlowingMode);
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getBlowingLevel() {
        Log.d(TAG, "getBlowingLevel=" + this.mBlowingLevel);
        return this.mBlowingLevel;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isFrontDefrosting() {
        Log.d(TAG, "isFrontDefrosting=" + this.mIsFrontDefrosting);
        return this.mIsFrontDefrosting;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isRearDefrosting() {
        Log.d(TAG, "isRearDefrosting=" + this.mIsRearDefrosting);
        return this.mIsRearDefrosting;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isDisplayRequestByAC() {
        Log.d(TAG, "isDisplayRequestByAC=" + this.mAcPanelActive + ",mDisplayActive=" + this.mDisplayActive + ",mBackDefrosterActive=" + this.mBackDefrosterActive);
        return this.mAcPanelActive;
    }

    private int getWindowType(int i) {
        Log.d(TAG, "type=" + i);
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 3;
        }
        if (i != 4) {
            return i != 2147483637 ? -2147483647 : 2147483637;
        }
        return 4;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getDisplayRequestWindowType() {
        Log.d(TAG, "mDisplayWindowType =" + this.mDisplayWindowType + "mDisplayType=-2147483647");
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getLoopMode() {
        Log.d(TAG, "getLoopMode=" + this.mLoopMode);
        return this.mLoopMode ? 4 : 2;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public double getLeftAreaTemperature() {
        int i = this.mLeftTemperature;
        double d = (i < 0 || i > 30) ? i == 31 ? -500.0d : -2.147483647E9d : ((double) ((float) (((double) (i - 1)) / 2.0d))) + 17.5d;
        Log.d(TAG, "getLeftAreaTemperature=" + d + ", mLeftTemperature" + this.mLeftTemperature);
        return d;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public double getRightAreaTemperature() {
        int i = this.mRightTemperature;
        double d = (i < 0 || i > 30) ? i == 31 ? -500.0d : -2.147483647E9d : ((double) ((float) (((double) (i - 1)) / 2.0d))) + 17.5d;
        Log.d(TAG, "getRightAreaTemperature=" + d + ", mRightTemperature" + this.mRightTemperature);
        return d;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getIonsMode() {
        int i = this.mIonsMode;
        int i2 = i != 0 ? i != 1 ? i != 3 ? -2147483647 : 16 : 128 : 0;
        Log.d(TAG, "getIonsMode=" + i2 + ", mIonsMode" + this.mIonsMode);
        return i2;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    @Deprecated
    public boolean isDualOn() {
        if (AcModelHelper.getInstance(this.mContext).isAcMultipleTemp()) {
            Log.d(TAG, "isDualOn=" + (!this.mAcMultipleTemp));
            return !this.mAcMultipleTemp;
        }
        Log.d(TAG, "isDualOn=" + this.mIsDualOn);
        return this.mIsDualOn;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isDualAreaSeparatelyControllOn() {
        if (AcModelHelper.getInstance(this.mContext).isAcMultipleTemp()) {
            Log.d(TAG, "isDualAreaSeparatelyControllOn=" + (!this.mAcMultipleTemp));
            return !this.mAcMultipleTemp;
        }
        Log.d(TAG, "isDualAreaSeparatelyControllOn=" + this.mIsDualOn);
        return this.mIsDualOn;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getPM2_5AQI() {
        Log.d(TAG, "getPM2_5AQI=" + this.mPM25AQI);
        int i = this.mPM25AQI;
        if (i > 999 || i < 0) {
            return Integer.MIN_VALUE;
        }
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getPM2_5AQILevel() {
        Log.d(TAG, "getPM2_5AQILevel=" + this.mPM25AQILevel);
        int i = this.mPM25AQILevel;
        if (i > 5 || i < 0) {
            return Integer.MIN_VALUE;
        }
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getAmbientPM2_5AQI() {
        Log.d(TAG, "getAmbientPM2_5AQI=" + this.mAmbientPM25AQI);
        int i = this.mAmbientPM25AQI;
        if (i > 999 || i < 0) {
            return Integer.MIN_VALUE;
        }
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isPm25Warning() {
        Log.d(TAG, "isPm25Warning, mPmSwitch=" + this.mPmSwitch);
        return this.mPmSwitch;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isHvacControlLockedInSpecifiedRow(int i) {
        Log.d(TAG, "isHvacControlLockedInSpecifiedRow=" + this.mRearLock);
        return this.mRearLock;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getAmbientPM2_5AQILevel() {
        Log.d(TAG, "getAmbientPM2_5AQILevel=" + this.mAmbientPM25AQILevel);
        int i = this.mAmbientPM25AQILevel;
        if (i > 5 || i < 0) {
            return Integer.MIN_VALUE;
        }
        return i;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAqsOn() {
        Log.d(TAG, "isAqsOn=" + this.mIsAqsOn);
        return this.mIsAqsOn;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isParkingAirPreConditioningOn() {
        Log.d(TAG, "isParkingAirPreConditioningOn=" + this.mParkingSwitch);
        return this.mParkingSwitch;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getSeatHeatingLevel(int i) {
        int heatSeatLevel = -2147483647;
        if (i == 1) {
            Log.d(TAG, "mLeftHeatingLevel=" + this.mLeftHeatingLevel);
            if (AcModelHelper.getInstance(this.mContext).hasDrvHeat()) {
                heatSeatLevel = getHeatSeatLevel(this.mLeftHeatingLevel);
            }
        } else if (i == 4) {
            Log.d(TAG, "mRightHeatingLevel=" + this.mRightHeatingLevel);
            if (AcModelHelper.getInstance(this.mContext).hasDrvHeat()) {
                heatSeatLevel = getHeatSeatLevel(this.mRightHeatingLevel);
            }
        }
        Log.d(TAG, "getSeatHeatingLevel=" + heatSeatLevel);
        return heatSeatLevel;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAutoSeatHeatingOn(int i) {
        LogTool.d(TAG, "isAutoSeatHeatingOn seatZone=" + i + ",Temperature==" + this.mLeftAutoSeatHeating + " ,mRightAutoSeatHeating=" + this.mRightAutoSeatHeating);
        if (i == 1) {
            return this.mLeftAutoSeatHeating;
        }
        if (i != 4) {
            return false;
        }
        return this.mRightAutoSeatHeating;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    @Deprecated
    public int getDriverSeatHeatingLevel() {
        int heatSeatLevel = AcModelHelper.getInstance(this.mContext).hasDrvHeat() ? getHeatSeatLevel(this.mLeftHeatingLevel) : -2147483647;
        Log.d(TAG, "mLeftHeatingLevel=" + this.mLeftHeatingLevel + " ,level=" + heatSeatLevel);
        return heatSeatLevel;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    @Deprecated
    public int getPassengerSeatHeatingLevel() {
        int heatSeatLevel = AcModelHelper.getInstance(this.mContext).hasDrvHeat() ? getHeatSeatLevel(this.mRightHeatingLevel) : -2147483647;
        Log.d(TAG, "mRightHeatingLevel=" + this.mRightHeatingLevel + " ,level=" + heatSeatLevel);
        return heatSeatLevel;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    @Deprecated
    public boolean isDriverSeatHeatingOn() {
        int i = this.mLeftHeatingLevel;
        return (i == 0 || i == 7) ? false : true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    @Deprecated
    public boolean isPassegerSeatHeatingOn() {
        int i = this.mRightHeatingLevel;
        return (i == 0 || i == 7) ? false : true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getSeatVentilationLevel(int i) {
        int ventilationSeatLevel = -2147483647;
        if (i == 1) {
            Log.d(TAG, "mLeftVentilationLevel=" + this.mLeftVentilationLevel);
            if (AcModelHelper.getInstance(this.mContext).hasDrvVent()) {
                ventilationSeatLevel = getVentilationSeatLevel(this.mLeftVentilationLevel);
            }
        } else if (i == 4) {
            Log.d(TAG, "mRightVentilationLevel=" + this.mRightVentilationLevel);
            if (AcModelHelper.getInstance(this.mContext).hasPassVent()) {
                ventilationSeatLevel = getVentilationSeatLevel(this.mRightVentilationLevel);
            }
        }
        Log.d(TAG, "getSeatVentilationLevel=" + ventilationSeatLevel);
        return ventilationSeatLevel;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getSeatMassageLevel(int i) {
        int massageSeatLevel = -2147483647;
        if (i == 1) {
            Log.d(TAG, "mLeftMassageLevel=" + this.mLeftMassageLevel);
            if (AcModelHelper.getInstance(this.mContext).hasDrvMassage()) {
                massageSeatLevel = getMassageSeatLevel(this.mLeftMassageLevel);
            }
        }
        Log.d(TAG, "getSeatMassageLevel=" + massageSeatLevel);
        return massageSeatLevel;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAutoSeatMassageOn(int i) {
        if (i == 1) {
            Log.d(TAG, "mLeftHeatingAutoOn=" + (this.mLeftHeatingLevel == 4));
            return this.mLeftHeatingLevel == 4;
        }
        if (i != 4) {
            return false;
        }
        Log.d(TAG, "mRightHeatingAutoOn=" + (this.mRightHeatingLevel == 4));
        return this.mRightHeatingLevel == 4;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isSupporedFuctionOn(int i) {
        Log.d(TAG, "isSupporedFuctionOn=" + i);
        if (i != 45) {
            if (i != 46) {
                if (i == 49) {
                    if (AcModelHelper.getInstance(this.mContext).hasPM()) {
                        return true;
                    }
                } else if (i == 50 && AcModelHelper.getInstance(this.mContext).hasPM()) {
                    return true;
                }
            } else if (AcModelHelper.getInstance(this.mContext).hasFragrance()) {
                LogTool.d(TAG, "mFraganceActive=" + this.mFraganceActive);
                return 1 == this.mFraganceActive;
            }
        } else if (AcModelHelper.getInstance(this.mContext).hasADS()) {
            LogTool.d(TAG, "mADSActive=" + this.mADSActive);
            if (1 == this.mADSActive) {
                return true;
            }
        }
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getFragranceLevel() {
        int fragranceLevel = AcModelHelper.getInstance(this.mContext).hasFragrance() ? getFragranceLevel(this.mFraganceLevel) : -2147483647;
        Log.d(TAG, "mFraganceLevel=" + this.mFraganceLevel + " ,level=" + fragranceLevel);
        return fragranceLevel;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getFragranceType() {
        int fragranceType = AcModelHelper.getInstance(this.mContext).hasFragrance() ? getFragranceType(this.mFraganceType) : -2147483647;
        Log.d(TAG, "mFraganceType=" + this.mFraganceType + " ,type=" + fragranceType);
        return fragranceType;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAcPTCHeat() {
        Log.d(TAG, "isAcPTCHeat--mAcPTCHear=" + this.mAcPTCHeat);
        return this.mAcPTCHeat;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAcEco() {
        Log.d(TAG, "isAcEco = " + this.mIsAcEco);
        return this.mIsAcEco;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAcVentilaDry() {
        Log.d(TAG, "isAcVentilaDry = " + this.mIsAcVentilaDry);
        return this.mIsAcVentilaDry;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isAcCZISOpen() {
        Log.d(TAG, "isAcCZISOpen = " + this.mIsAcCZISOpen);
        return this.mIsAcCZISOpen;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public int getAcComfortLevel() {
        Log.d(TAG, "getAcComfortLevel = " + this.mAcComfortLevel);
        return this.mAcComfortLevel;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isSeatDisplayRequestBySeat() {
        Log.d(TAG, "isSeatDisplayRequestBySeat = " + this.mAcSeatDisplay);
        return this.mAcSeatDisplay;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.IHvacState
    public boolean isACLostConnection() {
        Log.d(TAG, "isACLostConnection = " + this.mIsACLostConnection);
        return this.mIsACLostConnection;
    }
}
