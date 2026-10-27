package com.ecarx.xui.adaptapi.hvac.impl;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.hvac.Hvac;
import com.ecarx.xui.adaptapi.hvac.IAQSState;
import com.ecarx.xui.adaptapi.hvac.IHvacAutoVentilation;
import com.ecarx.xui.adaptapi.hvac.IHvacCallback;
import com.ecarx.xui.adaptapi.hvac.IHvacState;
import com.ecarx.xui.adaptapi.hvac.IHvacTouchBoard;
import com.ecarx.xui.adaptapi.hvac.specific.AcBean;
import com.ecarx.xui.adaptapi.hvac.specific.AcModelHelper;
import com.ecarx.xui.adaptapi.hvac.specific.AcProxy;
import com.ecarx.xui.adaptapi.hvac.specific.Define;
import com.ecarx.xui.adaptapi.hvac.specific.HvacReceiver;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class HvacImpl extends Hvac {
    private static final String PM_ACTION = "com.neusoft.ecarx.pm25";
    private static final int SEAT_ROW_1_ALL = 5;
    private static final String TAG = "Hvac";
    private AcBean mACBean;
    private Context mContext;
    private IHvacCallback mHvacCallback;
    private HvacReceiver mHvacReceiver;
    private Timer timer = new Timer();
    private final String mVehicleModel = SystemProperties.get("ro.product.device");

    private int getFragranceLevel(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? -1 : 3;
        }
        return 2;
    }

    private int getFragranceType(int i) {
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? -1 : 3;
        }
        return 2;
    }

    private int getHeatingLevel(int i) {
        if (i == 0) {
            return 0;
        }
        switch (i) {
            case 2147483644:
                return 4;
            case 2147483645:
                return 1;
            case 2147483646:
                return 2;
            case Integer.MAX_VALUE:
                return 3;
            default:
                return -1;
        }
    }

    private int getIsAutoSeatingHeating(boolean z) {
        return z ? 4 : 0;
    }

    private int getIsOpenAds(boolean z) {
        return z ? 1 : 3;
    }

    private int getMassageLevel(int i) {
        if (i == 0) {
            return 0;
        }
        switch (i) {
            case 2147483645:
                return 1;
            case 2147483646:
                return 2;
            case Integer.MAX_VALUE:
                return 3;
            default:
                return -1;
        }
    }

    private int getVentilationLevel(int i) {
        if (i == 0) {
            return 0;
        }
        switch (i) {
            case 2147483645:
                return 1;
            case 2147483646:
                return 2;
            case Integer.MAX_VALUE:
                return 3;
            default:
                return -1;
        }
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int getAutoModeMaxBlowingLevel() {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public IHvacAutoVentilation getAutoVentilation() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int getMaxBlowingLevel() {
        return 8;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int[] getSeatHeatingDurationForSettingDisplay() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int getSeatHeatingDurationInMinute(int i) {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int[] getSeatMassageDurationForSettingDisplay() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int getSeatMassageDurationInMinute(int i) {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int[] getSeatVentilationDurationForSettingDisplay() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int getSeatVentilationDurationInMinute(int i) {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public double getTemperatureAdjustStepping() {
        return 0.5d;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public IHvacTouchBoard getTouchBoard() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean isSupportSteeringWheelHeating() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAirCoditionerAutoMode(int i) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAirRaiseWindowSwitch(boolean z) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAutoFrontDefrosting(boolean z) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAutoRearDefrosting(boolean z) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAutoSeatHeatingDuration(int i, int i2) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAutoSeatHeatingLevel(int i, int i2) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAutoSeatMassageOnOff(boolean z, int i) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAutoSeatVentilationDuration(int i, int i2) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAutoSeatVentilationLevel(int i, int i2) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setFrontDefrostingMax(boolean z) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setInternalLoopTimerOnOff(boolean z) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setParkingSeatHeatPreConditioning(int i) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setRearDefrostingMax(boolean z) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setSeatHeatingDuration(int i, int i2) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setSeatMassageDuration(int i, int i2) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setSeatVentilationAutoModeOnOff(int i, boolean z) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setSeatVentilationDuration(int i, int i2) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setSteeringWheelHeatingOnOff(boolean z) {
        return false;
    }

    public HvacImpl(Context context) {
        Log.d(TAG, "HvacImpl-------context.getPackageName()=" + context.getPackageName());
        this.mContext = context;
        AcBean acBean = new AcBean(context);
        this.mACBean = acBean;
        this.mHvacReceiver = new HvacReceiver(acBean);
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public void setCallback(IHvacCallback iHvacCallback) {
        Log.d(TAG, "set HvacCallback");
        this.mHvacCallback = iHvacCallback;
        this.mACBean.setHvacCallBack(iHvacCallback);
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public void unsetCallback(IHvacCallback iHvacCallback) {
        Log.d(TAG, "set unsetCallback");
        this.mHvacCallback = iHvacCallback;
        this.mHvacReceiver.unRegisterListener();
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean isHavcDisplayOnIHURequired() {
        Log.d(TAG, "isHavcDisplayOnIHURequired=" + AcModelHelper.getInstance(this.mContext).hasAc());
        return AcModelHelper.getInstance(this.mContext).hasAc();
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean isHavcAvailable() {
        if (!AcModelHelper.getInstance(this.mContext).hasAc()) {
            return false;
        }
        byte[] bArr = new byte[25];
        bArr[0] = 9;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message == null) {
            Log.d(TAG, "peps timeout init byte is null");
            this.timer.schedule(new PEPSTimerTask(), 5000L);
        } else {
            Log.d(TAG, "init peps time out is " + ((int) message[22]));
            if (message[22] == 1) {
                this.mACBean.setHvacAvailable(false);
                return false;
            }
            byte[] bArr2 = new byte[25];
            bArr2[0] = 6;
            byte[] message2 = RpcManager.getInstance().getMessage(1881, bArr2);
            if (message2 != null) {
                Log.d(TAG, "init pepsValid is " + ((int) message2[12]) + ",pepsPowerMode is" + ((int) message2[11]));
                if (message2[12] == 2) {
                    if (AcModelHelper.getInstance(this.mContext).isBSG()) {
                        byte b = message2[11];
                        if (b == 2 || b == 4) {
                            this.mACBean.setHvacAvailable(true);
                            return true;
                        }
                        this.mACBean.setHvacAvailable(false);
                        return false;
                    }
                    if (message2[11] == 2) {
                        this.mACBean.setHvacAvailable(true);
                        return true;
                    }
                    this.mACBean.setHvacAvailable(false);
                    return false;
                }
                this.mACBean.setHvacAvailable(false);
                return false;
            }
        }
        this.mACBean.setHvacAvailable(false);
        return false;
    }

    class PEPSTimerTask extends TimerTask {
        PEPSTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            HvacImpl.this.isHavcAvailable();
        }
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int getHvacType() {
        return AcModelHelper.getInstance(this.mContext).getHvacType();
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public IHvacState getHvacState() {
        this.mHvacReceiver.initializeData();
        return this.mACBean.getHvacState();
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public IAQSState getAQSState() {
        this.mHvacReceiver.initAqsState();
        return this.mACBean.getAqsState();
    }

    private void setData(int i, byte b, byte b2) {
        byte[] bArr = new byte[20];
        bArr[0] = b;
        bArr[1] = b2;
        AcProxy.getInstance().sendCommand(i, bArr);
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setHavcPower(boolean z) {
        if (z) {
            Log.d(TAG, "setHavcPower true");
            if (getInitialize((byte) 17, 17) == 1) {
                return true;
            }
            setData(1886, Define.cSubCodeSetPowerOn, (byte) 1);
        } else {
            if (getInitialize((byte) 17, 17) == 0) {
                return true;
            }
            setData(1886, (byte) 10, (byte) 1);
            Log.d(TAG, "close ac power!");
        }
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAc(boolean z) {
        if ((getInitialize((byte) 17, 1) == 1) == z) {
            return true;
        }
        setData(1886, (byte) 5, (byte) 1);
        Log.d(TAG, "set AC Switch : 1on ==" + z);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    @Deprecated
    public boolean setAcAuto(boolean z) {
        setData(1886, (byte) 1, (byte) 1);
        Log.d(TAG, "set Auto Switch :1on ==" + z);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAirCoditionerAutoMode(boolean z) {
        if (!AcModelHelper.getInstance(this.mContext).hasAuto()) {
            return false;
        }
        if ((getInitialize((byte) 17, 9) == 1) == z) {
            return true;
        }
        setData(1886, (byte) 1, (byte) 1);
        Log.d(TAG, "set Auto Switch :1on ==" + z);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setHvacOnOffInVehicleZone(int i, boolean z) {
        Log.d(TAG, "setHvacOnOffInVehicleZone:zone=" + i + ",onOff=" + z);
        if (i == 32) {
            if (!AcModelHelper.getInstance(this.mContext).hasRCPSet()) {
                return false;
            }
            setData(1886, (byte) 17, (byte) (z ? 1 : 2));
            return true;
        }
        if (i != 512 || !AcModelHelper.getInstance(this.mContext).hasAcThirdPanel()) {
            return false;
        }
        setData(1886, Define.cSubCodeSetThirdPanel, (byte) (z ? 1 : 2));
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAcMax(boolean z) {
        if ((getInitialize((byte) 17, 10) == 1) == z) {
            return true;
        }
        setData(1886, (byte) 2, (byte) 1);
        Log.d(TAG, "set ACMax Switch : 1on ==" + z);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public FunctionStatus isSupportBlowingMode(int i) {
        Log.d(TAG, "isSupportBlowingMode,mode is" + i);
        FunctionStatus functionStatus = FunctionStatus.notavailable;
        if (i != Integer.MAX_VALUE) {
            switch (i) {
                case 1:
                    functionStatus = FunctionStatus.active;
                    break;
                case 2:
                    functionStatus = FunctionStatus.active;
                    break;
                case 3:
                    functionStatus = FunctionStatus.active;
                    break;
                case 4:
                    functionStatus = FunctionStatus.active;
                    break;
                case 5:
                    if (AcModelHelper.getInstance(this.mContext).isBX11()) {
                        functionStatus = FunctionStatus.active;
                    } else {
                        functionStatus = FunctionStatus.notavailable;
                    }
                    break;
                case 6:
                    functionStatus = FunctionStatus.active;
                    break;
                case 7:
                    if (AcModelHelper.getInstance(this.mContext).isBX11()) {
                        functionStatus = FunctionStatus.active;
                    } else {
                        functionStatus = FunctionStatus.notavailable;
                    }
                    break;
            }
        } else if (AcModelHelper.getInstance(this.mContext).isBX11()) {
            functionStatus = FunctionStatus.active;
        } else {
            functionStatus = FunctionStatus.notavailable;
        }
        Log.d(TAG, "isSupportBlowingMode,functionStatus is" + functionStatus);
        return functionStatus;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setBlowingMode(int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = 0;
                break;
            case 2:
                i2 = 2;
                break;
            case 3:
                i2 = 1;
                break;
            case 4:
                i2 = 4;
                break;
            case 5:
                i2 = 5;
                break;
            case 6:
                i2 = 3;
                break;
            case 7:
                i2 = 6;
                break;
            default:
                i2 = -1;
                break;
        }
        setData(1886, (byte) 9, (byte) i2);
        Log.d(TAG, "set Wind Mode : " + i2 + ",mode=" + i);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setBlowingLevel(int i) {
        setData(1886, (byte) 6, (byte) i);
        Log.d(TAG, "set Wind Level :" + i);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setFrontDefrosting(boolean z) {
        if ((getInitialize((byte) 22, 2) == 1) == z) {
            return true;
        }
        setData(1886, (byte) 15, (byte) 1);
        Log.d(TAG, "set Front Defrost Switch : 1on ==" + z);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setRearDefrosting(boolean z) {
        if ((getInitialize((byte) 22, 3) == 1) == z) {
            return true;
        }
        setData(1886, (byte) 16, (byte) 1);
        Log.d(TAG, "set Rear Defrost Switch : 1on ==" + z);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setLoopMode(int i) {
        int i2;
        Log.d(TAG, "turn Loop Switch");
        if (getInitialize((byte) 17, 11) == 0) {
            i2 = 2;
        } else {
            i2 = getInitialize((byte) 17, 11) == 1 ? 4 : 0;
        }
        Log.d(TAG, "freshMode=" + i2 + ",mode=" + i);
        if (i2 == i) {
            return true;
        }
        setData(1886, (byte) 3, (byte) 1);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setLeftAreaTemperature(double d) {
        Log.d(TAG, "temperature=" + d);
        if (d < 17.0d || d > 32.0d) {
            return false;
        }
        int i = ((int) ((d - 17.5d) * 2.0d)) + 1;
        setData(1886, (byte) 12, (byte) i);
        Log.d(TAG, "set left Temperature : " + i);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setRightAreaTemperature(double d) {
        Log.d(TAG, "temperature=" + d);
        if (d < 17.0d || d > 32.0d) {
            return false;
        }
        int i = ((int) ((d - 17.5d) * 2.0d)) + 1;
        setData(1886, (byte) 13, (byte) i);
        Log.d(TAG, "set right Temperature : " + i);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setIonsMode(int i) {
        int i2 = 0;
        if (!AcModelHelper.getInstance(this.mContext).hasIon()) {
            return false;
        }
        Log.d(TAG, "turn Ion Switch");
        if (getInitialize((byte) 17, 15) != 0) {
            if (getInitialize((byte) 17, 15) == 1) {
                i2 = 128;
            } else if (getInitialize((byte) 17, 15) == 3) {
                i2 = 16;
            }
        }
        Log.d(TAG, "ionMode=" + i2 + ",mode=" + i);
        if (i2 == i) {
            return true;
        }
        setData(1886, (byte) 8, (byte) 1);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setDual(boolean z) {
        if (!AcModelHelper.getInstance(this.mContext).hasDual()) {
            return false;
        }
        if (AcModelHelper.getInstance(this.mContext).isAcMultipleTemp()) {
            setData(1886, Define.cSubCodeSetAcSync, (byte) 1);
        } else {
            setData(1886, (byte) 7, (byte) 1);
        }
        Log.d(TAG, "set Dual Switch : 1");
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean isSupportDual() {
        return AcModelHelper.getInstance(this.mContext).hasDual();
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public double[] getLeftTemperatureRange() {
        return new double[]{17.5d, 31.5d};
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public double[] getRightTemperatureRange() {
        return new double[]{17.5d, 31.5d};
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAQS(boolean z) {
        if (!AcModelHelper.getInstance(this.mContext).hasAqs()) {
            return false;
        }
        if ((getInitialize((byte) 17, 12) == 1) == z) {
            return true;
        }
        setData(1886, (byte) 4, (byte) 1);
        Log.d(TAG, "set Aqs Switch :1on ==" + z);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setHvacControlLockedInRow(int i, boolean z) {
        if (!AcModelHelper.getInstance(this.mContext).hasRearLock()) {
            return false;
        }
        byte b = (byte) (z ? 2 : 1);
        setData(1886, (byte) 11, b);
        Log.d(TAG, "set Rear Lock Switch :" + ((int) b));
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    @Deprecated
    public boolean setDriverSeatHeating(int i) {
        int i2 = 0;
        if (!AcModelHelper.getInstance(this.mContext).hasDrvHeat()) {
            return false;
        }
        if (i != 0) {
            switch (i) {
                case 2147483645:
                    i2 = 1;
                    break;
                case 2147483646:
                    i2 = 2;
                    break;
                case Integer.MAX_VALUE:
                    i2 = 3;
                    break;
                default:
                    i2 = -1;
                    break;
            }
        }
        Log.d(TAG, "set left heating seat:" + i2 + ", level is " + i);
        setData(1884, (byte) 12, (byte) i2);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    @Deprecated
    public boolean setPassegerSeatHeating(int i) {
        int i2 = 0;
        if (!AcModelHelper.getInstance(this.mContext).hasDrvHeat()) {
            return false;
        }
        if (i != 0) {
            switch (i) {
                case 2147483645:
                    i2 = 1;
                    break;
                case 2147483646:
                    i2 = 2;
                    break;
                case Integer.MAX_VALUE:
                    i2 = 3;
                    break;
                default:
                    i2 = -1;
                    break;
            }
        }
        Log.d(TAG, "set right heating seat:" + i2 + ", level is " + i);
        setData(1884, (byte) 13, (byte) i2);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setSeatHeating(int i, int i2) {
        if (!AcModelHelper.getInstance(this.mContext).hasDrvHeat()) {
            return false;
        }
        int heatingLevel = getHeatingLevel(i);
        if (i2 == 1) {
            Log.d(TAG, "set left heating seat:" + heatingLevel + ", level is " + i);
            setData(1884, (byte) 12, (byte) heatingLevel);
        } else if (i2 == 4) {
            Log.d(TAG, "set right heating seat:" + heatingLevel + ", level is " + i);
            setData(1884, (byte) 13, (byte) heatingLevel);
        } else if (i2 == 5) {
            Log.d(TAG, "set left and right heating seat:" + heatingLevel + ", level is " + i);
            byte b = (byte) heatingLevel;
            setData(1884, (byte) 12, b);
            setData(1884, (byte) 13, b);
        }
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAutoSeatHeatingOnOff(boolean z, int i) {
        if (!AcModelHelper.getInstance(this.mContext).hasDrvHeat()) {
            return false;
        }
        int isAutoSeatingHeating = getIsAutoSeatingHeating(z);
        if (i == 1) {
            Log.d(TAG, "set left AutoHeating seat:" + isAutoSeatingHeating);
            setData(1884, (byte) 12, (byte) isAutoSeatingHeating);
        } else if (i == 4) {
            Log.d(TAG, "set Right AutoHeating seat:" + isAutoSeatingHeating);
            setData(1884, (byte) 13, (byte) isAutoSeatingHeating);
        } else if (i == 5) {
            Log.d(TAG, "set Right and left AutoHeating seat:" + isAutoSeatingHeating);
            byte b = (byte) isAutoSeatingHeating;
            setData(1884, (byte) 12, b);
            setData(1884, (byte) 13, b);
        }
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean isSeatHeatingSupported(int i) {
        if (i == 1 || i == 4) {
            return AcModelHelper.getInstance(this.mContext).hasDrvHeat();
        }
        if (i == 5) {
            return AcModelHelper.getInstance(this.mContext).hasDrvHeat();
        }
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int getHeatingSupportedSeatCount() {
        return AcModelHelper.getInstance(this.mContext).hasDrvHeat() ? 2 : 0;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setSeatVentilation(int i, int i2) {
        if (i2 == 1) {
            if (!AcModelHelper.getInstance(this.mContext).hasDrvVent()) {
                return false;
            }
            int ventilationLevel = getVentilationLevel(i);
            Log.d(TAG, "set left ventilation seat:" + ventilationLevel + ", level is " + i);
            setData(1884, (byte) 15, (byte) ventilationLevel);
            return true;
        }
        if (i2 == 4) {
            if (!AcModelHelper.getInstance(this.mContext).hasPassVent()) {
                return false;
            }
            int ventilationLevel2 = getVentilationLevel(i);
            Log.d(TAG, "set right ventilation seat:" + ventilationLevel2 + ", level is " + i);
            setData(1884, Define.cSubCodeSetRightWindSeat, (byte) ventilationLevel2);
            return true;
        }
        if (i2 != 5) {
            return false;
        }
        int ventilationLevel3 = getVentilationLevel(i);
        byte b = (byte) ventilationLevel3;
        setData(1884, (byte) 15, b);
        if (AcModelHelper.getInstance(this.mContext).hasPassVent()) {
            Log.d(TAG, "set left and right ventilation seat:" + ventilationLevel3 + ", level is " + i);
            setData(1884, Define.cSubCodeSetRightWindSeat, b);
        }
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean isSupportIon() {
        return AcModelHelper.getInstance(this.mContext).hasIon();
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    @Deprecated
    public boolean isSupportAuto() {
        return AcModelHelper.getInstance(this.mContext).hasAuto();
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean isSupportAirConditionerAutoMode() {
        return AcModelHelper.getInstance(this.mContext).hasAuto();
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int getVentilationSupportedSeatCount() {
        return AcModelHelper.getInstance(this.mContext).hasDrvVent() ? 2 : 0;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean isSeatVentilationSupported(int i) {
        if (i == 1) {
            return AcModelHelper.getInstance(this.mContext).hasDrvVent();
        }
        if (i == 4) {
            return AcModelHelper.getInstance(this.mContext).hasPassVent();
        }
        if (i == 5) {
            return AcModelHelper.getInstance(this.mContext).hasDrvVent() || AcModelHelper.getInstance(this.mContext).hasPassVent();
        }
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setSeatMassage(int i, int i2) {
        if (i2 != 1 || !AcModelHelper.getInstance(this.mContext).hasDrvMassage()) {
            return false;
        }
        int massageLevel = getMassageLevel(i);
        Log.d(TAG, "set left massage seat:" + massageLevel + ", level is " + i);
        setData(1884, (byte) 14, (byte) massageLevel);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean isSeatMassageSupported(int i) {
        if (i == 1) {
            return AcModelHelper.getInstance(this.mContext).hasDrvMassage();
        }
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int getMassageSupportedSeatCount() {
        return AcModelHelper.getInstance(this.mContext).hasDrvMassage() ? 1 : 0;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setSupportedFunctionOn(int i, boolean z) {
        int isOpenAds = getIsOpenAds(z);
        Log.d(TAG, "setSupportedFunctionOn function=" + i + ", mAdsOn=" + isOpenAds);
        if (i != 45 || !AcModelHelper.getInstance(this.mContext).hasADS()) {
            return false;
        }
        setData(1886, Define.cSubCodeSetADS, (byte) isOpenAds);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int[] getSupportedFragrancesType() {
        return AcModelHelper.getInstance(this.mContext).hasFragrance() ? new int[]{1, 2, 3} : new int[0];
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setFragranceLevel(int i) {
        if (!AcModelHelper.getInstance(this.mContext).hasFragrance()) {
            return false;
        }
        int fragranceLevel = getFragranceLevel(i);
        Log.d(TAG, "set fragranceLevel :" + fragranceLevel + ", level is " + i);
        if (fragranceLevel == 0) {
            setData(1886, Define.cSubCodeSetFraganceSwitch, (byte) 1);
        } else if (this.mACBean.getIsFragranceOn()) {
            setData(1886, Define.cSubCodeSetFraganceLevel, (byte) fragranceLevel);
        } else {
            setData(1886, Define.cSubCodeSetFraganceSwitch, (byte) 2);
            setData(1886, Define.cSubCodeSetFraganceLevel, (byte) fragranceLevel);
        }
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setFragranceType(int i) {
        if (!AcModelHelper.getInstance(this.mContext).hasFragrance()) {
            return false;
        }
        int fragranceType = getFragranceType(i);
        Log.d(TAG, "set fragranceType :" + fragranceType + ", Type is " + i);
        if (this.mACBean.getIsFragranceOn()) {
            setData(1886, Define.cSubCodeSetFraganceType, (byte) fragranceType);
            return true;
        }
        setData(1886, Define.cSubCodeSetFraganceSwitch, (byte) 2);
        setData(1886, Define.cSubCodeSetFraganceType, (byte) fragranceType);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAcPTCHeat(boolean z) {
        if ((getInitialize((byte) 18, 4) == 1) == z) {
            return true;
        }
        setData(1886, Define.cSubCodeSetAcPTCHeating, (byte) 1);
        Log.d(TAG, "setAcPTCHeat :1on ==" + z);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAcEco(boolean z) {
        if ((getInitialize((byte) 18, 6) == 1) == z) {
            return true;
        }
        setData(1886, Define.cSubCodeSetAcEco, (byte) 1);
        Log.d(TAG, "setAcEco :1on ==" + z);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAcVentilaDry(boolean z) {
        Log.d(TAG, "setAcVentilaDry :on ==" + z);
        setData(1886, (byte) 63, z ? (byte) 2 : (byte) 1);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAcCZISOpen(boolean z) {
        Log.d(TAG, "setAcCZISOpen :on ==" + z);
        setData(1886, Define.cSubCodeSetAcZISOpen, z ? (byte) 2 : (byte) 1);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setAcComfortLevel(int i) {
        Log.d(TAG, "setAcComfortLevel :level ==" + i);
        setData(1886, Define.cSubCodeSetAcComfortLevel, (byte) i);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setRCP(boolean z) {
        Log.d(TAG, "setRCP :on ==" + z);
        setData(1886, (byte) 17, z ? (byte) 1 : (byte) 2);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setThridPanel(boolean z) {
        Log.d(TAG, "setThridPanel :on ==" + z);
        setData(1886, Define.cSubCodeSetThirdPanel, z ? (byte) 1 : (byte) 2);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public int[] getSupportedFunctionSubs(int i) {
        int[] iArr = new int[0];
        AcModelHelper acModelHelper = AcModelHelper.getInstance(this.mContext);
        Log.d(TAG, "getSupportedFunctionSubs function=" + i);
        if (i != 18) {
            if (i != 19) {
                if (i != 29) {
                    if (i == 33 && acModelHelper.hasDrvMassage()) {
                        iArr = new int[]{0, 2147483645, 2147483646};
                    }
                } else if (acModelHelper.hasIon()) {
                    iArr = new int[]{0, 128};
                }
            } else if (acModelHelper.hasDrvVent()) {
                iArr = new int[]{0, 2147483645, 2147483646, Integer.MAX_VALUE};
            }
        } else if (acModelHelper.hasDrvHeat()) {
            if (OfflineConfig.getE01OfflineConfig(116) == 0) {
                iArr = new int[]{0, 2147483645};
            } else if (1 == OfflineConfig.getE01OfflineConfig(116)) {
                iArr = new int[]{0, 2147483645, 2147483646};
            } else if (2 == OfflineConfig.getE01OfflineConfig(116)) {
                iArr = new int[]{0, 2147483645, 2147483646, Integer.MAX_VALUE};
            } else if (3 == OfflineConfig.getE01OfflineConfig(116)) {
                iArr = new int[]{0, 2147483645, 2147483646};
            }
        }
        Log.d(TAG, "getSupportedFunctionSubs res=" + Arrays.toString(iArr));
        return iArr;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setDoubleAreaTemperature(double d, double d2) {
        Log.d(TAG, "leftTemperature=" + d + ",rightTemperature=" + d2);
        if (d >= 17.0d && d <= 32.0d) {
            int i = ((int) ((d - 17.5d) * 2.0d)) + 1;
            setData(1886, (byte) 12, (byte) i);
            Log.d(TAG, "set left Temperature : " + i);
        }
        if (d2 >= 17.0d && d2 <= 32.0d) {
            int i2 = ((int) ((d2 - 17.5d) * 2.0d)) + 1;
            setData(1886, (byte) 13, (byte) i2);
            Log.d(TAG, "set right Temperature : " + i2);
        }
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean isSupportPM25() {
        return AcModelHelper.getInstance(this.mContext).hasPM();
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setPm25Warning(boolean z) {
        if (!AcModelHelper.getInstance(this.mContext).hasPM()) {
            return false;
        }
        Log.d(TAG, "setPm25Warning, on=" + z);
        if ((getInitialize((byte) 17, 8) == 1 || getInitialize((byte) 17, 8) == 2) == z) {
            return true;
        }
        setData(1900, (byte) 10, (byte) 1);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public boolean setParkingAirPreConditioningOnOff(boolean z) {
        if (!AcModelHelper.getInstance(this.mContext).hasParking()) {
            return false;
        }
        Log.d(TAG, "setParkingAirPreConditioningOnOff:" + ((int) ((byte) (z ? 2 : 1))));
        setData(1886, (byte) 14, (byte) (z ? 2 : 1));
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public FunctionStatus isFunctionSupported(int i) {
        FunctionStatus functionStatus;
        Log.d(TAG, "isFunctionSupported,function is" + i);
        FunctionStatus functionStatus2 = FunctionStatus.notavailable;
        switch (i) {
            case 1:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 2:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 5:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 6:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasMaxAc() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 7:
                functionStatus2 = FunctionStatus.active;
                break;
            case 8:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 9:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasRearDefrost() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 11:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasDual() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 12:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasDrvHeat() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 13:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasDrvVent() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 14:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 15:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 16:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 18:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 19:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 20:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 21:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 22:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasParking() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 23:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 24:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 28:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAqs() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 29:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasIon() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 30:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasRearLock() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 31:
                functionStatus2 = FunctionStatus.active;
                break;
            case 32:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 33:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasDrvMassage() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 34:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasPM() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 35:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasPM() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 36:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasPM() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 37:
                functionStatus2 = FunctionStatus.active;
                break;
            case 39:
                functionStatus2 = FunctionStatus.active;
                break;
            case 43:
                functionStatus2 = FunctionStatus.active;
                break;
            case 44:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAuto() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 45:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 46:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasFragrance() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 47:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 48:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 49:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasPM() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 50:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasPM() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 54:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAcPTCHeat() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 55:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAcCO2() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 56:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAcEco() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 57:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasRCPSet() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 58:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAcThirdPanel() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 59:
                functionStatus = AcModelHelper.getInstance(this.mContext).isBlowingLevel8() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
        }
        Log.d(TAG, "isFunctionSupported,functionStatus is" + functionStatus2);
        return functionStatus2;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public FunctionStatus isFunctionSupportedByTarget(int i, int i2) {
        FunctionStatus functionStatus;
        Log.d(TAG, "isFunctionSupportedByTarget, function is" + i + ",target is" + i2);
        FunctionStatus functionStatus2 = FunctionStatus.notavailable;
        if (i != 12) {
            if (i != 13) {
                if (i != 33) {
                    if (i == 51) {
                        if (i2 == 32) {
                            functionStatus = AcModelHelper.getInstance(this.mContext).hasRCPSet() ? FunctionStatus.active : FunctionStatus.notavailable;
                        } else if (i2 == 512) {
                            functionStatus = AcModelHelper.getInstance(this.mContext).hasAcThirdPanel() ? FunctionStatus.active : FunctionStatus.notavailable;
                        }
                        functionStatus2 = functionStatus;
                    }
                } else if (i2 == 1) {
                    if (AcModelHelper.getInstance(this.mContext).hasDrvMassage()) {
                        functionStatus2 = FunctionStatus.active;
                    }
                } else if (i2 == 4) {
                    functionStatus2 = FunctionStatus.notavailable;
                }
            } else if (i2 == 1) {
                if (AcModelHelper.getInstance(this.mContext).hasDrvVent()) {
                    functionStatus2 = FunctionStatus.active;
                }
            } else if (i2 == 4) {
                if (AcModelHelper.getInstance(this.mContext).hasPassVent()) {
                    functionStatus2 = FunctionStatus.active;
                }
            } else if (i2 == 5 && (AcModelHelper.getInstance(this.mContext).hasPassVent() || AcModelHelper.getInstance(this.mContext).hasDrvVent())) {
                functionStatus2 = FunctionStatus.active;
            }
        } else if ((i2 == 1 || i2 == 4 || i2 == 5) && AcModelHelper.getInstance(this.mContext).hasDrvHeat()) {
            functionStatus2 = FunctionStatus.active;
        }
        Log.d(TAG, "isFunctionSupportedByTarget, functionStatus is" + functionStatus2);
        return functionStatus2;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    public FunctionStatus isFunctionSupportedByOperation(int i, int i2) {
        FunctionStatus functionStatus;
        Log.d(TAG, "isFunctionSupported,function is" + i);
        FunctionStatus functionStatus2 = FunctionStatus.notavailable;
        switch (i) {
            case 1:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 2:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 5:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 6:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasMaxAc() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 7:
                functionStatus2 = FunctionStatus.active;
                break;
            case 8:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 9:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasRearDefrost() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 11:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasDual() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 12:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasDrvHeat() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 13:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasDrvVent() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 14:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 15:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 16:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 18:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 19:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 20:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 21:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 22:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasParking() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 23:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 24:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 28:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAqs() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 29:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasIon() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 30:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasRearLock() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 31:
                functionStatus2 = FunctionStatus.active;
                break;
            case 32:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 33:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasDrvMassage() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 34:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasPM() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 35:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasPM() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 36:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasPM() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 37:
                functionStatus2 = FunctionStatus.active;
                break;
            case 39:
                functionStatus2 = FunctionStatus.active;
                break;
            case 43:
                functionStatus2 = FunctionStatus.active;
                break;
            case 44:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAuto() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 45:
                if (AcModelHelper.getInstance(this.mContext).hasADS()) {
                    functionStatus2 = FunctionStatus.active;
                }
                break;
            case 46:
                if (AcModelHelper.getInstance(this.mContext).hasFragrance()) {
                    functionStatus2 = FunctionStatus.active;
                }
                break;
            case 47:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 48:
                functionStatus2 = FunctionStatus.notavailable;
                break;
            case 49:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasPM() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 50:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasPM() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 51:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAc() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 54:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAcPTCHeat() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 55:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAcCO2() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
            case 56:
                functionStatus = AcModelHelper.getInstance(this.mContext).hasAcEco() ? FunctionStatus.active : FunctionStatus.notavailable;
                functionStatus2 = functionStatus;
                break;
        }
        Log.d(TAG, "isFunctionSupported,functionStatus is" + functionStatus2);
        return functionStatus2;
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    @Deprecated
    public boolean isSupportDriverSeatHeating() {
        return AcModelHelper.getInstance(this.mContext).hasDrvHeat();
    }

    @Override // com.ecarx.xui.adaptapi.hvac.Hvac
    @Deprecated
    public boolean isSupportPassegerSeatHeating() {
        return AcModelHelper.getInstance(this.mContext).hasDrvHeat();
    }

    private byte getInitialize(byte b, int i) {
        byte[] bArr = new byte[25];
        bArr[0] = b;
        Log.d(TAG, "set initialize data : " + Arrays.toString(bArr));
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message != null) {
            Log.d(TAG, "return initialize data : " + Arrays.toString(message));
            return message[i];
        }
        Log.d(TAG, "return initialize is null ");
        return (byte) 0;
    }
}
