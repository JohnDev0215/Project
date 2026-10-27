package com.ecarx.xui.adaptapi.vehicle;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.Tribool;
import com.ecarx.xui.adaptapi.hvac.specific.Define;
import com.ecarx.xui.adaptapi.vehicle.ISettings;
import com.ecarx.xui.adaptapi.vehicle.settings.AmbientLightAdjustImpl;
import com.ecarx.xui.adaptapi.vehicle.settings.BackLightAdjustImpl;
import com.ecarx.xui.adaptapi.vehicle.settings.IAmbientLightAdjust;
import com.ecarx.xui.adaptapi.vehicle.settings.IBackLightAdjust;
import com.ecarx.xui.adaptapi.vehicle.settings.ILaneKeep;
import com.ecarx.xui.adaptapi.vehicle.settings.ITrunkLid;
import com.ecarx.xui.adaptapi.vehicle.settings.IUnlockDoorSettings;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.log.LogTool;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class SettingImpl implements ISettings {
    private static final String cTag = "SettingImpl";
    ISettings.FunctionEnableCallBack functionEnableCallBack;
    private ISettings.IFunctionSupportedCallBack mCallBack;
    private ISettings.CarAlarmTypeCallBack mCarAlarmCallBack;
    private Context mContext;
    private ISettings.SunroofRkeModeCallBack mSunroofCallBack;
    RpcManager.OnRpcListener listener_avas = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.SettingImpl.1
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            LogTool.d(SettingImpl.cTag, "opecode_avas=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1821 && bArr[0] == 11 && SettingImpl.this.functionEnableCallBack != null) {
                SettingImpl.this.functionEnableCallBack.onFunctionEnable(33, bArr[19] == 1 ? Tribool.yes : Tribool.no);
            }
        }
    };
    RpcManager.OnRpcListener listener_ads = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.SettingImpl.2
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            LogTool.d(SettingImpl.cTag, "opecode_ads=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1822 && bArr[0] == 4 && SettingImpl.this.functionEnableCallBack != null) {
                SettingImpl.this.functionEnableCallBack.onFunctionEnable(1, bArr[12] == 2 ? Tribool.yes : Tribool.no);
                SettingImpl.this.functionEnableCallBack.onFunctionEnable(2, bArr[11] == 2 ? Tribool.yes : Tribool.no);
            }
        }
    };
    RpcManager.OnRpcListener listener_0x07 = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.SettingImpl.3
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            LogTool.d(SettingImpl.cTag, "opecode_0x07=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1820 && bArr[0] == 7) {
                if (SettingImpl.this.functionEnableCallBack != null) {
                    SettingImpl.this.functionEnableCallBack.onFunctionEnable(10, bArr[2] == 1 ? Tribool.yes : Tribool.no);
                    SettingImpl.this.functionEnableCallBack.onFunctionEnable(10, bArr[3] == 1 ? Tribool.yes : Tribool.no);
                    SettingImpl.this.functionEnableCallBack.onFunctionEnable(5, bArr[1] == 1 ? Tribool.yes : Tribool.no);
                    SettingImpl.this.functionEnableCallBack.onFunctionEnable(3, bArr[4] == 1 ? Tribool.yes : Tribool.no);
                }
                if (SettingImpl.this.mSunroofCallBack != null) {
                    byte b = bArr[11];
                    if (b == 0) {
                        SettingImpl.this.mSunroofCallBack.onSunroofRkeModeChange(1);
                    } else {
                        if (b != 1) {
                            return;
                        }
                        SettingImpl.this.mSunroofCallBack.onSunroofRkeModeChange(2);
                    }
                }
            }
        }
    };
    RpcManager.OnRpcListener listener_0x0A = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.SettingImpl.4
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            LogTool.d(SettingImpl.cTag, "opecode_0x0A=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1820 && bArr[0] == 10) {
                if (SettingImpl.this.functionEnableCallBack != null) {
                    SettingImpl.this.functionEnableCallBack.onFunctionEnable(13, bArr[2] == 2 ? Tribool.yes : Tribool.no);
                    SettingImpl.this.functionEnableCallBack.onFunctionEnable(47, bArr[6] == 1 ? Tribool.yes : Tribool.no);
                }
                if (SettingImpl.this.mCarAlarmCallBack != null) {
                    byte b = bArr[1];
                    if (b == 1) {
                        SettingImpl.this.mCarAlarmCallBack.onCarAlarmTypeChange(3);
                    } else if (b == 2) {
                        SettingImpl.this.mCarAlarmCallBack.onCarAlarmTypeChange(2);
                    } else {
                        if (b != 3) {
                            return;
                        }
                        SettingImpl.this.mCarAlarmCallBack.onCarAlarmTypeChange(1);
                    }
                }
            }
        }
    };
    RpcManager.OnRpcListener listener_eng = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.SettingImpl.5
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            LogTool.d(SettingImpl.cTag, "opecode_ads=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1822 && bArr[0] == 3 && SettingImpl.this.functionEnableCallBack != null) {
                SettingImpl.this.functionEnableCallBack.onFunctionEnable(8, bArr[5] == 2 ? Tribool.yes : Tribool.no);
            }
        }
    };
    RpcManager.OnRpcListener listener = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.vehicle.SettingImpl.6
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                return;
            }
            LogTool.d("gaoTest", "opecode=" + i + ",data = " + Arrays.toString(bArr));
            if (i == 1820 && bArr[0] == 8 && SettingImpl.this.functionEnableCallBack != null) {
                SettingImpl.this.functionEnableCallBack.onFunctionEnable(23, bArr[14] == 1 ? Tribool.yes : Tribool.no);
            }
        }
    };

    private boolean getAccState() {
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public int getBackdoorAutoUnlockDistance() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public int getEpsMode() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public int getLDWThreshold() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public ILaneKeep getLaneKeepAidSetting() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public int getMirrorTiltedType() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public int[] getSupportedFunctionSubs(int i) {
        return new int[0];
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public int[] getSupportedSetFunctions() {
        return new int[0];
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public ITrunkLid getTrunkLid() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public IUnlockDoorSettings getUnlockDoorSettings() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void registerResetSettingCallBack(ISettings.IResetSettingCallBack iResetSettingCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public boolean resetSettingDataDefault() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setBackdoorAutoUnlockDistance(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setBackdoorAutoUnlockDistanceCallBack(ISettings.BackdoorAutoUnlockDistanceCallBack backdoorAutoUnlockDistanceCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setDimThemeCallBack(ISettings.IDimThemeCallBack iDimThemeCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setEPSMode(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setEPSModeCallBack(ISettings.EPSModeCallBack ePSModeCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setLDWThreshold(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setLDWThresholdCallBack(ISettings.LDWThresholdCallBack lDWThresholdCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setMaintainMilesChangeCallBack(ISettings.MaintainMilesChangeCallBack maintainMilesChangeCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setMirrorTiltedType(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setMirrorTiltedTypeCallBack(ISettings.MirrorTiltedCallBack mirrorTiltedCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unregisterResetSettingCallBack(ISettings.IResetSettingCallBack iResetSettingCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unsetBackdoorAutoUnlockDistanceCallBack(ISettings.BackdoorAutoUnlockDistanceCallBack backdoorAutoUnlockDistanceCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unsetDimThemeCallBack(ISettings.IDimThemeCallBack iDimThemeCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unsetEPSModeCallBack(ISettings.EPSModeCallBack ePSModeCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unsetLDWThresholdCallBack(ISettings.LDWThresholdCallBack lDWThresholdCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unsetMaintainMilesChangeCallBack(ISettings.MaintainMilesChangeCallBack maintainMilesChangeCallBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unsetMirrorTiltedTypeCallBack(ISettings.MirrorTiltedCallBack mirrorTiltedCallBack) {
    }

    public SettingImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public FunctionStatus isFunctionSupported(int i) {
        LogTool.d(cTag, "isFunctionSupported function=" + i);
        FunctionStatus functionStatus = FunctionStatus.notavailable;
        switch (i) {
            case 1:
                if (ModelHelper.getInstance(this.mContext).hasAebOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 2:
            case 4:
            case 6:
            case 9:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 22:
            case 34:
            case 37:
            case 41:
            case 42:
            case 53:
            case 55:
            case 59:
            case 61:
            case 62:
            case 63:
            case 64:
            case 67:
            case 68:
            default:
                return functionStatus;
            case 3:
                if (ModelHelper.getInstance(this.mContext).hasRLSModeEnable()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 5:
                if (ModelHelper.getInstance(this.mContext).hasRearMirrorFoldSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 7:
                if (ModelHelper.getInstance(this.mContext).hasSpeedAutoLockLayout()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 8:
                if (ModelHelper.getInstance(this.mContext).hasIhbcOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 10:
                if (ModelHelper.getInstance(this.mContext).hasDayRunLightSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 13:
                if (ModelHelper.getInstance(this.mContext).hasAutoCloseWindowSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 20:
                if (ModelHelper.getInstance(this.mContext).hasNearTailGatWithOuteUnlock()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 21:
                if (ModelHelper.getInstance(this.mContext).hasAPUOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 23:
                if (ModelHelper.getInstance(this.mContext).hasWelcomeLightSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 24:
                if (ModelHelper.getInstance(this.mContext).hasAnyDoorOpenWarnOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 25:
                if (ModelHelper.getInstance(this.mContext).hasAntiPinchWarnSetResp()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 26:
                if (ModelHelper.getInstance(this.mContext).hasSATOOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 27:
                if (ModelHelper.getInstance(this.mContext).hasTrunkUnlockDistanceOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 28:
                if (ModelHelper.getInstance(this.mContext).hasTrunkUnlockWithoutKeyOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 29:
                if (ModelHelper.getInstance(this.mContext).hasSeatMemoryReq()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 30:
                if (ModelHelper.getInstance(this.mContext).hasRoofLightConnectDoorSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 31:
                if (ModelHelper.getInstance(this.mContext).hasPAS_SVA_Status()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 32:
                if (ModelHelper.getInstance(this.mContext).hasSVA_Request()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 33:
                if (ModelHelper.getInstance(this.mContext).hasHUDFunction()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 35:
                if (ModelHelper.getInstance(this.mContext).hasEnergy()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 36:
                if (!ModelHelper.getInstance(this.mContext).isTcu()) {
                    return FunctionStatus.active;
                }
                return FunctionStatus.notavailable;
            case 38:
                if (ModelHelper.getInstance(this.mContext).hasRearMirrorReverseInclineReq()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 39:
                if (ModelHelper.getInstance(this.mContext).hasATWarnTypeSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 40:
                if (ModelHelper.getInstance(this.mContext).hasSunroofRollShade()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 43:
                return FunctionStatus.active;
            case 44:
                if (ModelHelper.getInstance(this.mContext).hasTerminalNotOFFWarnOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 45:
                if (ModelHelper.getInstance(this.mContext).hasKeyInReminderOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 46:
                if (ModelHelper.getInstance(this.mContext).hasCornerLampSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 47:
                if (ModelHelper.getInstance(this.mContext).hasWiperLinkage()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 48:
                if (ModelHelper.getInstance(this.mContext).hasTrunkUpBasicPositionSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 49:
                if (ModelHelper.getInstance(this.mContext).hasIGNOFFUnlockDoorSelect()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 50:
                if (ModelHelper.getInstance(this.mContext).hasRKEUnlockSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 51:
                return FunctionStatus.notavailable;
            case 52:
                if (ModelHelper.getInstance(this.mContext).hasEasyAccessReq()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 54:
                if (ModelHelper.getInstance(this.mContext).hasRightCameraSteerMode()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 56:
                if (ModelHelper.getInstance(this.mContext).hasFrontWiperIdleModeSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 57:
                if (ModelHelper.getInstance(this.mContext).hasACKeyBeep()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 58:
                if (ModelHelper.getInstance(this.mContext).hasAVASDisabledSet()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 60:
                if (ModelHelper.getInstance(this.mContext).hasLkaOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 65:
            case 69:
                if (ModelHelper.getInstance(this.mContext).hasEtc()) {
                    return FunctionStatus.active;
                }
                return FunctionStatus.notavailable;
            case 66:
                if (ModelHelper.getInstance(this.mContext).hasIccOption()) {
                    return FunctionStatus.active;
                }
                if (getAccState()) {
                    return FunctionStatus.notavailable;
                }
                return FunctionStatus.notactive;
            case 70:
                if (!ModelHelper.getInstance(this.mContext).hasEtc()) {
                    return functionStatus;
                }
                if (ModelHelper.getInstance(this.mContext).hasBreachAmbient()) {
                    return FunctionStatus.active;
                }
                return FunctionStatus.notavailable;
        }
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public FunctionStatus isFunctionSupported(int i, int i2) {
        return isFunctionSupported(i);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void registerFunctionSupportedCallBack(ISettings.IFunctionSupportedCallBack iFunctionSupportedCallBack) {
        this.mCallBack = iFunctionSupportedCallBack;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unregisterFunctionSupportedCallBack(ISettings.IFunctionSupportedCallBack iFunctionSupportedCallBack) {
        this.mCallBack = null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public Tribool getFunctionEnable(int i) {
        byte b;
        if (i == 1) {
            byte[] bArr = new byte[25];
            bArr[0] = 81;
            byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
            if (message == null || message.length == 0) {
                return null;
            }
            LogTool.d(cTag, "get AEB switch state. dataOut: " + Arrays.toString(message));
            return message[2] == 2 ? Tribool.yes : Tribool.no;
        }
        if (i == 2) {
            byte[] bArr2 = new byte[25];
            bArr2[0] = 81;
            byte[] message2 = RpcManager.getInstance().getMessage(1881, bArr2);
            if (message2 == null || message2.length == 0) {
                return null;
            }
            LogTool.d(cTag, "get FCW switch state. dataOut: " + Arrays.toString(message2));
            return message2[1] == 2 ? Tribool.yes : Tribool.no;
        }
        if (i == 3) {
            byte[] bArr3 = new byte[25];
            bArr3[0] = 2;
            byte[] message3 = RpcManager.getInstance().getMessage(1881, bArr3);
            if (message3 == null || message3.length == 0) {
                return null;
            }
            LogTool.d(cTag, "get RLS switch state. dataOut: " + Arrays.toString(message3));
            return message3[17] == 1 ? Tribool.yes : Tribool.no;
        }
        byte b2 = 63;
        if (i == 36) {
            LogTool.d(cTag, "getDimTheme() = " + getDimTheme());
            if (getDimTheme() == 63) {
                return Tribool.yes;
            }
            return Tribool.no;
        }
        if (i == 37) {
            LogTool.d(cTag, "getDimThemeforUser() = " + getDimTheme());
            if (getDimTheme() != 63) {
                return Tribool.yes;
            }
            return Tribool.no;
        }
        if (i == 69) {
            byte[] bArr4 = new byte[25];
            bArr4[0] = 0;
            byte[] message4 = RpcManager.getInstance().getMessage(1648, bArr4);
            if (message4 != null) {
                LogTool.d(cTag, "getEtcColor() data is " + Arrays.toString(message4));
                b = message4[2];
            } else {
                LogTool.d(cTag, "getEtcColor() data is null.return -1");
                b = 63;
            }
            if (b == 63) {
                return Tribool.yes;
            }
            return Tribool.no;
        }
        if (i != 70) {
            switch (i) {
                case 5:
                    byte[] bArr5 = new byte[25];
                    bArr5[0] = 2;
                    byte[] message5 = RpcManager.getInstance().getMessage(1881, bArr5);
                    if (message5 == null || message5.length == 0) {
                        return null;
                    }
                    LogTool.d(cTag, "get MIRROR switch state. dataOut: " + Arrays.toString(message5));
                    return message5[14] == 1 ? Tribool.yes : Tribool.no;
                case 8:
                    byte[] bArr6 = new byte[25];
                    bArr6[0] = 21;
                    byte[] message6 = RpcManager.getInstance().getMessage(1881, bArr6);
                    if (message6 == null || message6.length == 0) {
                        return null;
                    }
                    LogTool.d(cTag, "get IHBC switch state. dataOut: " + Arrays.toString(message6));
                    return message6[2] == 2 ? Tribool.yes : Tribool.no;
                case 10:
                    byte[] bArr7 = new byte[25];
                    bArr7[0] = 2;
                    byte[] message7 = RpcManager.getInstance().getMessage(1881, bArr7);
                    if (message7 == null || message7.length == 0) {
                        return null;
                    }
                    LogTool.d(cTag, "get DayRun switch state. dataOut: " + Arrays.toString(message7));
                    return message7[15] == 1 ? Tribool.yes : Tribool.no;
                case 13:
                    byte[] bArr8 = new byte[25];
                    bArr8[0] = 4;
                    byte[] message8 = RpcManager.getInstance().getMessage(1881, bArr8);
                    if (message8 == null || message8.length == 0) {
                        return null;
                    }
                    LogTool.d(cTag, "get AutoClose switch state. dataOut: " + Arrays.toString(message8));
                    return message8[3] == 2 ? Tribool.yes : Tribool.no;
                case 23:
                    byte[] bArr9 = new byte[25];
                    bArr9[0] = 0;
                    byte[] message9 = RpcManager.getInstance().getMessage(1648, bArr9);
                    if (message9 != null) {
                        LogTool.d(cTag, "getWelcome() = " + ((int) message9[12]));
                        return message9[12] == 1 ? Tribool.yes : Tribool.no;
                    }
                    LogTool.d(cTag, "getWelcome() datas is null.");
                    return Tribool.no;
                case 25:
                    byte[] bArr10 = new byte[25];
                    bArr10[0] = 2;
                    byte[] message10 = RpcManager.getInstance().getMessage(1881, bArr10);
                    if (message10 == null || message10.length == 0) {
                        return null;
                    }
                    LogTool.d(cTag, "get Anti switch state. dataOut: " + Arrays.toString(message10));
                    return message10[16] == 1 ? Tribool.yes : Tribool.no;
                case 33:
                    byte[] bArr11 = new byte[25];
                    bArr11[0] = 5;
                    byte[] message11 = RpcManager.getInstance().getMessage(1881, bArr11);
                    if (message11 == null || message11.length == 0) {
                        return null;
                    }
                    LogTool.d(cTag, "get hud switch state. dataOut: " + Arrays.toString(message11));
                    return message11[1] == 0 ? Tribool.yes : Tribool.no;
                case 43:
                    if (Settings.System.getInt(this.mContext.getContentResolver(), "sound_effects_enabled", 1) == 1) {
                        return Tribool.yes;
                    }
                    return Tribool.no;
                case 47:
                    byte[] bArr12 = new byte[25];
                    bArr12[0] = 4;
                    byte[] message12 = RpcManager.getInstance().getMessage(1881, bArr12);
                    if (message12 == null || message12.length == 0) {
                        return null;
                    }
                    LogTool.d(cTag, "get RearWiper switch state. dataOut: " + Arrays.toString(message12));
                    return message12[10] == 1 ? Tribool.yes : Tribool.no;
                case 54:
                    int i2 = Settings.System.getInt(this.mContext.getContentResolver(), "Aightblinddarea", 0);
                    LogTool.d(cTag, "get CAMERA_FOLLOW switch state. dataOut: " + i2);
                    return i2 == 1 ? Tribool.yes : Tribool.no;
                case 57:
                    if (getAcPressSoundState() == 1) {
                        return Tribool.yes;
                    }
                    return Tribool.no;
                default:
                    return null;
            }
        }
        byte[] bArr13 = new byte[25];
        bArr13[0] = 0;
        byte[] message13 = RpcManager.getInstance().getMessage(1648, bArr13);
        if (message13 != null) {
            LogTool.d(cTag, "getEtcColor() data is " + Arrays.toString(message13));
            b2 = message13[2];
        } else {
            LogTool.d(cTag, "getEtcColor() data is null.return -1");
        }
        if (b2 == 3) {
            return Tribool.yes;
        }
        return Tribool.no;
    }

    public byte getAcPressSoundState() {
        byte[] bArr = new byte[25];
        bArr[0] = 17;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message == null) {
            LogTool.d(cTag, "getAcPressSoundState is null.");
            return (byte) 0;
        }
        LogTool.d(cTag, "get acpress" + Arrays.toString(message));
        return message[21];
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setFunctionEnable(int i, boolean z) {
        LogTool.d(cTag, "setFunctionEnable functionId:" + i + "-enable:" + z);
        if (i == 1) {
            LogTool.d(cTag, "%s setFunctionEnable functionId:F_AUTONOMOUS_EMERGENCY_BRAKING");
            setOpeCode(1903, 0, z ? 2 : 1);
        }
        if (i == 2) {
            LogTool.d(cTag, "%s setFunctionEnable functionId:F_FORWARD_COLLISION_WARNING");
            setOpeCode(1903, 1, z ? 2 : 1);
            return;
        }
        if (i == 3) {
            LogTool.d(cTag, "%s setFunctionEnable functionId:F_RLS");
            setOpeCode(1884, 4, z ? 2 : 0);
            return;
        }
        switch (i) {
            case 5:
                LogTool.d(cTag, "%s setFunctionEnable functionId:F_MIRROR_AUTO_FOLDING");
                setOpeCode(1884, 1, z ? 2 : 1);
                break;
            case 8:
                LogTool.d(cTag, "%s setFunctionEnable functionId:F_INTELLIGENT_HIGH_BEAM_CONTROL");
                setOpeCode(1886, 1, z ? 2 : 1);
                break;
            case 10:
                LogTool.d(cTag, "%s setFunctionEnable functionId:F_DAY_RUNNING_LAMP");
                setOpeCode(1884, 2, z ? 2 : 1);
                break;
            case 13:
                LogTool.d(cTag, "%s setFunctionEnable functionId:F_AUTO_CLOSE_WINDOW");
                setOpeCode(1884, 59, z ? 2 : 0);
                break;
            case 23:
                setOpeCode(0, 6, z ? 1 : 0, 1638);
                break;
            case 25:
                LogTool.d(cTag, "%s setFunctionEnable functionId:F_WINDOW_CLAMP_WARNING");
                setOpeCode(1884, 3, z ? 2 : 1);
                break;
            case 33:
                LogTool.d(cTag, "%s setFunctionEnable functionId:F_HUD_SET");
                setOpeCode(1887, 37, z ? 2 : 1);
                break;
            case 36:
                if (z) {
                    Settings.System.putInt(this.mContext.getContentResolver(), "skin_with_drive_mode", 1);
                    sendSkinModeRPC(63);
                    setDriveMode(true);
                } else {
                    Settings.System.putInt(this.mContext.getContentResolver(), "skin_with_drive_mode", 0);
                    setDriveMode(false);
                }
                break;
            case 43:
                Settings.System.putInt(this.mContext.getContentResolver(), "sound_effects_enabled", z ? 1 : 0);
                break;
            case 47:
                LogTool.d(cTag, "%s setFunctionEnable functionId:F_WIPER_FOLLOW_REVERSE_GEAR");
                setOpeCode(1884, 53, z ? 1 : 0);
                break;
            case 54:
                LogTool.d(cTag, "%s setFunctionEnable functionId:F_RT_CAMERA_FOLLOW_TURNNING");
                setOpeCode(1321, 1, z ? 1 : 0);
                Settings.System.putInt(this.mContext.getContentResolver(), "Aightblinddarea", z ? 1 : 0);
                if (this.functionEnableCallBack != null) {
                    this.functionEnableCallBack.onFunctionEnable(54, Settings.System.getInt(this.mContext.getContentResolver(), "Aightblinddarea", 0) == 1 ? Tribool.yes : Tribool.no);
                }
                break;
            case 69:
                setOpeCode(0, 4, z ? 1 : 0, 1638);
                break;
        }
    }

    private void setOpeCode(int i, int i2) {
        byte[] bArr = new byte[25];
        bArr[0] = (byte) i;
        bArr[1] = (byte) i2;
        LogTool.d(cTag, "%s setInfo" + Arrays.toString(bArr));
        RpcManager.getInstance().sendMessage(1882, bArr);
    }

    private void setOpeCode(int i, int i2, int i3) {
        byte[] bArr = new byte[25];
        bArr[0] = (byte) i2;
        bArr[1] = (byte) i3;
        LogTool.d(cTag, "%s setInfo" + Arrays.toString(bArr));
        RpcManager.getInstance().sendMessage(i, bArr);
    }

    private void setOpeCode(int i, int i2, int i3, int i4) {
        byte[] bArr = new byte[25];
        bArr[0] = (byte) i;
        bArr[1] = (byte) i2;
        bArr[2] = (byte) i3;
        LogTool.d(cTag, "%s setInfo" + Arrays.toString(bArr));
        RpcManager.getInstance().sendMessage(i4, bArr);
    }

    public static void setDriveMode(boolean z) {
        byte[] bArr = new byte[25];
        bArr[0] = 14;
        bArr[1] = !z ? 1 : 0;
        LogTool.d(cTag, "setDriveMode() result " + RpcManager.getInstance().sendMessage(1898, bArr) + "  " + Arrays.toString(bArr));
    }

    public static void sendSkinModeRPC(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = Define.cSubCodeSetFraganceLevel;
        bArr[1] = (byte) i;
        LogTool.d(cTag, "sendSkinModeRPC() result " + RpcManager.getInstance().sendMessage(1887, bArr) + "  " + Arrays.toString(bArr));
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setFunctionEnableCallBack(ISettings.FunctionEnableCallBack functionEnableCallBack) {
        this.functionEnableCallBack = functionEnableCallBack;
        RpcManager.getInstance().registerListener(1821, (byte) 11, this.listener_avas);
        RpcManager.getInstance().registerListener(1822, (byte) 4, this.listener_ads);
        RpcManager.getInstance().registerListener(1820, (byte) 7, this.listener_0x07);
        RpcManager.getInstance().registerListener(1820, (byte) 10, this.listener_0x0A);
        RpcManager.getInstance().registerListener(1822, (byte) 3, this.listener_eng);
        RpcManager.getInstance().registerListener(1820, (byte) 8, this.listener);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unsetFunctionEnableCallBack(ISettings.FunctionEnableCallBack functionEnableCallBack) {
        this.functionEnableCallBack = null;
        RpcManager.getInstance().unregisterListener(1821, (byte) 11, this.listener_avas);
        RpcManager.getInstance().unregisterListener(1822, (byte) 4, this.listener_ads);
        RpcManager.getInstance().unregisterListener(1820, (byte) 7, this.listener_0x07);
        RpcManager.getInstance().unregisterListener(1820, (byte) 10, this.listener_0x0A);
        RpcManager.getInstance().unregisterListener(1822, (byte) 3, this.listener_eng);
        RpcManager.getInstance().unregisterListener(1820, (byte) 8, this.listener);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public int getRudderPosition() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(276);
        Log.d(cTag, "---------------getRudderPosition" + e01OfflineConfig);
        return e01OfflineConfig + 1;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public int getSunroofRkeMode() {
        byte[] bArr = new byte[25];
        bArr[0] = 7;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message != null && message.length != 0) {
            LogTool.d(cTag, "get SunroofRoll state. dataOut: " + Arrays.toString(message));
            byte b = message[2];
            if (b == 0) {
                return 1;
            }
            if (b == 1) {
                return 2;
            }
        }
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setSunroofRkeMode(int i) {
        setOpeCode(1884, 68, i != 1 ? 2 : 1);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setSunroofRkeModeCallBack(ISettings.SunroofRkeModeCallBack sunroofRkeModeCallBack) {
        this.mSunroofCallBack = sunroofRkeModeCallBack;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unsetSunroofRkeModeCallBack(ISettings.SunroofRkeModeCallBack sunroofRkeModeCallBack) {
        this.mSunroofCallBack = null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setCarAlarmType(int i) {
        LogTool.d(cTag, "setCarAlarmType--type" + i);
        int i2 = 3;
        if (i != 1) {
            if (i == 2) {
                i2 = 2;
            } else if (i != 3) {
                return;
            } else {
                i2 = 1;
            }
        }
        setOpeCode(1884, 49, i2);
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public int getCarAlarmType() {
        byte b;
        LogTool.d(cTag, "getCarAlarmType-");
        byte[] bArr = new byte[25];
        bArr[0] = 4;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message == null || message.length == 0) {
            b = 0;
        } else {
            LogTool.d(cTag, "get ATWarn switch state. dataOut: " + Arrays.toString(message));
            b = message[2];
        }
        if (b == 1) {
            return 3;
        }
        if (b != 2) {
            return b != 3 ? 0 : 1;
        }
        return 2;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void setCarAlarmTypeCallBack(ISettings.CarAlarmTypeCallBack carAlarmTypeCallBack) {
        this.mCarAlarmCallBack = carAlarmTypeCallBack;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public void unsetCarAlarmTypeCallBack(ISettings.CarAlarmTypeCallBack carAlarmTypeCallBack) {
        this.mCarAlarmCallBack = null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public int getDimTheme() {
        byte[] bArr = new byte[25];
        bArr[0] = 4;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        LogTool.d(cTag, "getDimTheme() array: " + Arrays.toString(bArr));
        if (message != null) {
            LogTool.d(cTag, "getDimTheme = " + Arrays.toString(message));
            byte b = message[17];
            if (b == 0) {
                return 3;
            }
            if (b == 1) {
                return 2;
            }
            if (b == 2) {
                return 1;
            }
            return b;
        }
        LogTool.d(cTag, "getDimTheme datas is null,return -1.");
        return -1;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public boolean setDimTheme(int i) {
        if (i == 1) {
            Settings.System.putInt(this.mContext.getContentResolver(), "skin_with_drive_mode", 0);
            setDriveMode(false);
            sendSkinModeRPC(2);
            return true;
        }
        if (i == 2) {
            Settings.System.putInt(this.mContext.getContentResolver(), "skin_with_drive_mode", 0);
            setDriveMode(false);
            sendSkinModeRPC(1);
            return true;
        }
        if (i != 3) {
            return false;
        }
        Settings.System.putInt(this.mContext.getContentResolver(), "skin_with_drive_mode", 0);
        setDriveMode(false);
        sendSkinModeRPC(0);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public IAmbientLightAdjust getAmbientLightSetting() {
        return new AmbientLightAdjustImpl();
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ISettings
    public IBackLightAdjust getBackLightAdjust() {
        return new BackLightAdjustImpl();
    }
}
