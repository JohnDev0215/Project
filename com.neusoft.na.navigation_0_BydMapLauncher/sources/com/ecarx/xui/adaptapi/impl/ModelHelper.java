package com.ecarx.xui.adaptapi.impl;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import com.ecarx.xui.adaptapi.policy.ContextProxy;
import com.ecarx.xui.adaptapi.policy.VoiceAssistantPolicy;
import com.neusoft.optimus.utility.log.LogTool;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ModelHelper {
    private static final String TAG = "ModelHelper";
    private static ModelHelper mModelHelper;
    private Context mContext;
    private boolean mPopStartSoc = false;
    private boolean mPopStartEnergy = false;
    private final String mVehicleModel = SystemProperties.get("ro.product.device");
    private boolean misFristSave = false;

    public boolean hasATWarnTypeSet() {
        return true;
    }

    public boolean hasCarMode() {
        return false;
    }

    public boolean hasNavi() {
        return true;
    }

    public boolean hasRearCurtainConnectReverse() {
        return false;
    }

    public boolean hasRearCurtainSw() {
        return false;
    }

    public boolean hasSumModeControl() {
        return false;
    }

    public boolean hasWifi() {
        return true;
    }

    public int hasWindowSeatSwitch() {
        return 0;
    }

    public static synchronized ModelHelper getInstance(Context context) {
        if (mModelHelper == null) {
            mModelHelper = new ModelHelper(context);
        }
        return mModelHelper;
    }

    private ModelHelper(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public String getVehicleModel() {
        return this.mVehicleModel;
    }

    public void setmFirstSave(boolean z) {
        this.misFristSave = z;
    }

    public boolean getFirstSave() {
        return this.misFristSave;
    }

    public boolean isKC2() {
        return this.mVehicleModel.contains("KC2");
    }

    public boolean isKC2QJ() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.KC2QJ);
    }

    public boolean isKC2ZG() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.KC2ZG);
    }

    public boolean isKC2SS() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.KC2SS);
    }

    public boolean isKC2HH() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.KC2HH);
    }

    public boolean isFE6() {
        return this.mVehicleModel.contains("FE6");
    }

    public boolean isFE5() {
        return this.mVehicleModel.contains("FE5");
    }

    public boolean isFE5HAQJ() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.FE5HAQJ);
    }

    public boolean isFE5HAHH() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.FE5HAHH);
    }

    public boolean isFE5HASS() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.FE5HASS);
    }

    public boolean isFE6QJ() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.FE6QJ);
    }

    public boolean isFE6SS() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.FE6SS);
    }

    public boolean isCV() {
        return this.mVehicleModel.contains(ContextProxy.CV);
    }

    public boolean isCV11() {
        return this.mVehicleModel.contains(ContextProxy.CV) && !this.mVehicleModel.contains("CV12");
    }

    public boolean isCV12() {
        return this.mVehicleModel.contains("CV12");
    }

    public boolean isCVQJOrCVHBQJ() {
        return isCV() && hasDVR();
    }

    public boolean isCVQJ() {
        return isCVQJOrCVHBQJ() && !isVCU();
    }

    public boolean isCV11QJ() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.CVQJ);
    }

    public boolean isCV12QJ() {
        return this.mVehicleModel.equals("CV12QJ");
    }

    public boolean isCVHBQJ() {
        return isCVQJOrCVHBQJ() && isVCU();
    }

    public boolean isSX() {
        return this.mVehicleModel.contains(ContextProxy.SX);
    }

    public boolean isSportModeOfSX() {
        int offlineConfig = OfflineConfig.getOfflineConfig(this.mContext, 46);
        Log.d(TAG, "sx_vehicle_mode  " + offlineConfig);
        return offlineConfig == 4 || offlineConfig == 5;
    }

    public boolean isSX11() {
        return this.mVehicleModel.contains("SX11");
    }

    public boolean isSX12() {
        return this.mVehicleModel.contains("SX12");
    }

    public boolean isNL5() {
        return this.mVehicleModel.contains("NL5");
    }

    public boolean hasSoundMode() {
        return isNL3BQJ() || isNL5();
    }

    public boolean isNL() {
        return this.mVehicleModel.contains(ContextProxy.NL);
    }

    public boolean isNL5QJ() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.NL5QJ) || this.mVehicleModel.equals("NL5HBQJ");
    }

    public boolean isNL5QJ1() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.NL5QJ);
    }

    public boolean isNL5HBQJ() {
        return this.mVehicleModel.equals("NL5HBQJ");
    }

    public boolean isNL5HH() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.NL5HH);
    }

    public boolean isNL5HBHH() {
        return this.mVehicleModel.equals("NL5HBHH");
    }

    public boolean isHH() {
        return this.mVehicleModel.contains("HH");
    }

    public boolean isNL3B() {
        return this.mVehicleModel.contains("NL3B");
    }

    public boolean isNL3BQJ() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.NL3BQJ);
    }

    public boolean isNL3BHH() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.NL3BHH);
    }

    public void setPopState(boolean z) {
        this.mPopStartSoc = z;
    }

    public boolean getPopState() {
        return this.mPopStartSoc;
    }

    public void setPopEnergy(boolean z) {
        this.mPopStartEnergy = z;
    }

    public boolean getPopEnergyStatus() {
        return this.mPopStartEnergy;
    }

    public boolean isHB() {
        return this.mVehicleModel.contains("HB");
    }

    public boolean isVCU() {
        return 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 284);
    }

    public boolean hasWirelessCharge() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(108);
        Log.d(TAG, "vehicle type " + this.mVehicleModel + " hasWirelessCharge: " + OfflineConfig.getE01OfflineConfig(108));
        return z;
    }

    public boolean hasEtc() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(140);
        int e01OfflineConfig2 = OfflineConfig.getE01OfflineConfig(141);
        int e01OfflineConfig3 = OfflineConfig.getE01OfflineConfig(142);
        int e01OfflineConfig4 = OfflineConfig.getE01OfflineConfig(143);
        int e01OfflineConfig5 = OfflineConfig.getE01OfflineConfig(144);
        int e01OfflineConfig6 = OfflineConfig.getE01OfflineConfig(145);
        int e01OfflineConfig7 = OfflineConfig.getE01OfflineConfig(146);
        int e01OfflineConfig8 = OfflineConfig.getE01OfflineConfig(147);
        int e01OfflineConfig9 = OfflineConfig.getE01OfflineConfig(148);
        int e01OfflineConfig10 = OfflineConfig.getE01OfflineConfig(149);
        int e01OfflineConfig11 = OfflineConfig.getE01OfflineConfig(150);
        int e01OfflineConfig12 = OfflineConfig.getE01OfflineConfig(151);
        Log.d(TAG, "etcmode1: " + e01OfflineConfig + " etcmode2 " + e01OfflineConfig2 + " etcmode3 " + e01OfflineConfig3 + " etcmode4 " + e01OfflineConfig4 + " etcmode5 " + e01OfflineConfig5 + " etcmode6 " + e01OfflineConfig6 + " etcmode7 " + e01OfflineConfig7 + " etcmode8 " + e01OfflineConfig8 + " etcmode9 " + e01OfflineConfig9 + " etcmode10 " + e01OfflineConfig10 + " etcmode11 " + e01OfflineConfig11 + " etcmode12 " + e01OfflineConfig12);
        return ((((((((((e01OfflineConfig + e01OfflineConfig2) + e01OfflineConfig3) + e01OfflineConfig4) + e01OfflineConfig5) + e01OfflineConfig6) + e01OfflineConfig7) + e01OfflineConfig8) + e01OfflineConfig9) + e01OfflineConfig10) + e01OfflineConfig11) + e01OfflineConfig12 != 0;
    }

    public boolean isEtcMonochrome() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(130);
        boolean z = e01OfflineConfig == 0;
        Log.d(TAG, "isEtcMonochrome: " + z + " etcMode " + e01OfflineConfig);
        return z;
    }

    public boolean hasIndoorTopEtc() {
        return OfflineConfig.getOfflineConfig(this.mContext, 91) == 1;
    }

    public boolean hasDoorPlankEtc() {
        return OfflineConfig.getOfflineConfig(this.mContext, 92) == 1;
    }

    public boolean hasDashboardPlankEtc() {
        return OfflineConfig.getOfflineConfig(this.mContext, 93) == 1;
    }

    public boolean hasSubsidiaryDashboardPlankEtc() {
        return OfflineConfig.getOfflineConfig(this.mContext, 94) == 1;
    }

    public boolean hasDrinkHolderEtc() {
        return OfflineConfig.getOfflineConfig(this.mContext, 95) == 1;
    }

    public boolean hasRLSModeEnable() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(206);
        Log.d(TAG, "vehicle type " + this.mVehicleModel + " sx hasRLSModeEnable: " + z + "value is " + OfflineConfig.getE01OfflineConfig(206));
        return z;
    }

    public boolean hasRearMirrorFoldSet() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(259);
        LogTool.d(TAG, "vehicel type " + this.mVehicleModel + " hasRearMirrorFold " + z + "value is " + OfflineConfig.getE01OfflineConfig(259));
        return z;
    }

    public boolean hasDayRunLightSet() {
        return 1 == OfflineConfig.getE01OfflineConfig(260);
    }

    public boolean hasFlasherAnimControlSet() {
        return 1 == OfflineConfig.getE01OfflineConfig(202);
    }

    public boolean hasHUDFunction() {
        boolean z = true;
        if (OfflineConfig.getE01OfflineConfig(226) != 1 && OfflineConfig.getE01OfflineConfig(226) != 2) {
            z = false;
        }
        LogTool.d(TAG, "hasHUDFunction: " + z + "value is " + OfflineConfig.getE01OfflineConfig(226));
        return z;
    }

    public int hudState() {
        return OfflineConfig.getE01OfflineConfig(226);
    }

    public boolean hasAntiPinchWarnSetResp() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(208);
        LogTool.d(TAG, "vehicle type: " + this.mVehicleModel + " hasAntiPinchWarn: " + z + " Window_Clamp =" + OfflineConfig.getE01OfflineConfig(208));
        return z;
    }

    public boolean hasRearMirrorReverseInclineReq() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(263);
        LogTool.d(TAG, "vehicle type: " + this.mVehicleModel + " hasReveringMirror: " + z);
        return z;
    }

    public boolean hasTerminalNotOFFWarnOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(217);
    }

    public boolean hasAnyDoorOpenWarnOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(216);
    }

    public boolean hasSATOOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(266);
    }

    public boolean hasAPUOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(269);
    }

    public boolean hasWALOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(268);
    }

    public boolean hasBrakeModeOption() {
        if (isNL5HBHH()) {
            return false;
        }
        isNL5HBQJ();
        return false;
    }

    public boolean hasTrunkUnlockWithoutKeyOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(211);
    }

    public boolean isPhevType() {
        if (1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 284)) {
            if (1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 285)) {
                Log.d(TAG, "nl5 phev ");
                return true;
            }
            OfflineConfig.getVehicleOfflineConfig(this.mContext, 285);
        }
        return false;
    }

    public boolean hasBookChargingOption() {
        if (1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 284)) {
            return 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 285) || OfflineConfig.getVehicleOfflineConfig(this.mContext, 285) != 0;
        }
        return false;
    }

    public boolean hasAfsLightOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(19);
    }

    public boolean hasAcDryOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(242);
    }

    public boolean hasCzisOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(243);
    }

    public boolean hasTrunkUnlockDistanceOption() {
        if (isKC2()) {
            return true;
        }
        if (isFE6() || isCV() || isSX11() || isNL5()) {
            return false;
        }
        isFE5();
        return false;
    }

    public boolean hasKeyInReminderOption() {
        if (isKC2() || isFE6() || isCV() || isSX11() || isNL5()) {
            return false;
        }
        isFE5();
        return false;
    }

    public boolean hasAutoCloseWindowSet() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(207);
        Log.d(TAG, "sx_vehicle_mode  " + z);
        return z;
    }

    public boolean hasSVA_Request() {
        return 1 == OfflineConfig.getE01OfflineConfig(123);
    }

    public boolean hasPAS_SVA_Status() {
        if (isKC2()) {
            return isKC2QJ();
        }
        if (isSX12()) {
            return false;
        }
        boolean z = 1 == OfflineConfig.getOfflineConfig(this.mContext, 20);
        Log.d(TAG, "hasPAS_SVA: " + OfflineConfig.getOfflineConfig(this.mContext, 20));
        return z;
    }

    public boolean hasSeatMemoryReq() {
        if (isKC2()) {
            LogTool.d(TAG, "hasSeatMemoryReq" + OfflineConfig.getVehicleOfflineConfig(this.mContext, 274));
            return 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 274);
        }
        if (isFE6() || isCV() || isSX11() || isNL5()) {
            return false;
        }
        isFE5();
        return false;
    }

    public boolean hasSeatMemoryAlert() {
        if (isKC2()) {
            LogTool.d(TAG, "KC hasSeatMemoryAlert" + OfflineConfig.getVehicleOfflineConfig(this.mContext, 274));
            return 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 274);
        }
        if (!isNL()) {
            return false;
        }
        LogTool.d(TAG, "NL hasSeatMemoryAlert" + OfflineConfig.getVehicleOfflineConfig(this.mContext, 274));
        return 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 274);
    }

    public boolean hasFollowMeHomeTimeSelect() {
        return 1 == OfflineConfig.getE01OfflineConfig(203);
    }

    public boolean hasFrontWiperIdleModeSet() {
        return 1 == OfflineConfig.getE01OfflineConfig(205);
    }

    public boolean hasTrunkUpBasicPositionSet() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(212);
        Log.d(TAG, "CV hasTruckPosition: " + z + " value is " + OfflineConfig.getE01OfflineConfig(212));
        return z;
    }

    public boolean hasNearTailGateUnlock() {
        return 1 == OfflineConfig.getE01OfflineConfig(267);
    }

    public boolean hasCornerLampSet() {
        if (isKC2() || isFE6() || isCV() || isSX11() || isNL5()) {
            return false;
        }
        isFE5();
        return false;
    }

    public boolean hasWelcomeLightSet() {
        return 1 == OfflineConfig.getE01OfflineConfig(261);
    }

    public boolean hasIGNOFFUnlockDoorSelect() {
        Log.d(TAG, "---------------hasIGNOFFUnlockDoorSelect");
        return 1 == OfflineConfig.getE01OfflineConfig(213);
    }

    public boolean hasSpeedAutoLockLayout() {
        Log.d(TAG, "---------------hasIGNOFFUnlockDoorSelect");
        return 1 == OfflineConfig.getE01OfflineConfig(215);
    }

    public boolean hasBacklightadjust() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(201);
        Log.d(TAG, "hasBacklightadjust: " + z + " value is " + OfflineConfig.getE01OfflineConfig(201));
        return z;
    }

    public boolean hasEasyAccessReq() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(265);
        Log.d(TAG, "NL3b hasSeatFormat: " + z + " value is " + OfflineConfig.getE01OfflineConfig(265));
        return z;
    }

    public boolean hasRKEUnlockSet() {
        return 1 == OfflineConfig.getE01OfflineConfig(264);
    }

    public boolean hasRoofLightConnectDoorSet() {
        if (isKC2()) {
            return true;
        }
        if (isFE6() || isCV() || isSX11() || isNL5()) {
            return false;
        }
        isFE5();
        return false;
    }

    public boolean hasVehicleSearchWarningSet() {
        if (isKC2()) {
            return true;
        }
        if (isFE6() || isCV() || isSX11() || isNL5()) {
            return false;
        }
        isFE5();
        return false;
    }

    public boolean hasIBooster() {
        if (isKC2() || isFE6() || isCV() || isSX11()) {
            return false;
        }
        if (isNL5()) {
            return true;
        }
        isFE5();
        return false;
    }

    public int hasAdasOption() {
        return OfflineConfig.getE01OfflineConfig(256);
    }

    public boolean hasAebOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(249);
    }

    public boolean hasIccOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(248);
    }

    public boolean hasIhbcOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(252);
    }

    public boolean hasLkaOption() {
        return 1 == OfflineConfig.getE01OfflineConfig(253);
    }

    public boolean hasEmergencyLka() {
        return 1 == OfflineConfig.getE01OfflineConfig(254);
    }

    public boolean hasRRadaAssist() {
        return 1 == OfflineConfig.getE01OfflineConfig(255);
    }

    public boolean hasRoadSign() {
        return 1 == OfflineConfig.getE01OfflineConfig(251);
    }

    public boolean hasAcAutoLevel() {
        return 1 == OfflineConfig.getE01OfflineConfig(240);
    }

    public boolean hasDVR() {
        boolean z;
        if (isSX()) {
            z = 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 299);
            Log.d(TAG, "sx hasDVR = " + z);
            return z;
        }
        z = 1 == OfflineConfig.getE01OfflineConfig(35);
        Log.d(TAG, "hasDVR = " + z);
        return z;
    }

    public boolean hasRadarVolume() {
        boolean z = false;
        if (isKC2()) {
            if (1 != OfflineConfig.getOfflineConfig(this.mContext, 6) && OfflineConfig.getOfflineConfig(this.mContext, 6) != 0) {
                z = true;
            }
            Log.d(TAG, "kc hasRadarVolume = " + z);
            return z;
        }
        if (isFE6() || isCV() || isSX11() || isNL5() || isNL3B() || isSX12()) {
            return true;
        }
        if (isFE5()) {
            if (1 != OfflineConfig.getOfflineConfig(this.mContext, 6) && OfflineConfig.getOfflineConfig(this.mContext, 6) != 0) {
                z = true;
            }
            Log.d(TAG, "fe hasRadarVolume = " + z);
        }
        return z;
    }

    public boolean hasCallReminder() {
        if (isKC2()) {
            return true;
        }
        if (isFE6()) {
            return !isFE6SS();
        }
        if (isCV() || isSX11() || isNL5() || isNL3B()) {
            return true;
        }
        if (isFE5()) {
            return !isFE5HASS();
        }
        return false;
    }

    public boolean hasVrSetting() {
        return !isFE5HASS();
    }

    public boolean hasAVASDisabledSet() {
        boolean z;
        if (isBx11()) {
            z = 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 292);
            Log.d(TAG, "cv hasAVAS = " + z);
            return z;
        }
        if (isVF12()) {
            z = 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 292);
            Log.d(TAG, "sx11 hasAVAS = " + z);
            return z;
        }
        if (!isGE13()) {
            return false;
        }
        LogTool.d(TAG, "nl vcu: " + OfflineConfig.getVehicleOfflineConfig(this.mContext, 284));
        return 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 284);
    }

    public boolean hasPHEV() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(26) && 1 == OfflineConfig.getE01OfflineConfig(7);
        Log.d(TAG, "sx12 hasPHEV: " + z);
        return z;
    }

    public boolean hasDriveMode() {
        return 1 == OfflineConfig.getE01OfflineConfig(219);
    }

    public boolean hasHEV() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(26) && OfflineConfig.getE01OfflineConfig(7) == 0;
        Log.d(TAG, "sx12 hasHEV: " + z);
        return z;
    }

    public boolean hasAdas() {
        boolean z = 2 == OfflineConfig.getE01OfflineConfig(256);
        Log.d(TAG, "ge13 hasAdas: " + z);
        return z;
    }

    public boolean hasEscOff() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(258);
        Log.d(TAG, "ge13  hasEscOff: " + z);
        return z;
    }

    public boolean hasHdcOff() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(222);
        Log.d(TAG, "ge13  hasHdcOff: " + z);
        return z;
    }

    public boolean hasPdcOff() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(220);
        Log.d(TAG, "ge13  hasPdcOff: " + z);
        return z;
    }

    public boolean hasWelcomeLightAnim() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(204);
        Log.d(TAG, "ge13  hasLightEffect: " + z);
        return z;
    }

    public boolean hasAirClean() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(244);
        Log.d(TAG, "ge13  hasAirClean: " + z);
        return z;
    }

    public boolean hasSeatMemory() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(229);
        Log.d(TAG, "ge13  hasSeatMemory: " + z);
        return z;
    }

    public boolean hasEnergyFlow() {
        if (isBx11()) {
            return 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 284);
        }
        if (isVF12()) {
            boolean z = 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 284);
            Log.d(TAG, "sx11 hasPHEV: " + z);
            return z;
        }
        if (isGE13()) {
            Log.d(TAG, "sx11 hasPHEV: " + (1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 284)));
        }
        return false;
    }

    public boolean isBsg() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(29);
        Log.d(TAG, "isBsg()= " + OfflineConfig.getE01OfflineConfig(29));
        return z;
    }

    public boolean hasBsgEnergyFlow() {
        if (isBx11()) {
            boolean zIsBsg = isBsg();
            LogTool.d(TAG, "kc bsgEnergy: " + zIsBsg);
            return zIsBsg;
        }
        if (isVF12()) {
            boolean zIsBsg2 = isBsg();
            LogTool.d(TAG, "nl3b bsgEnergy: " + zIsBsg2);
            return zIsBsg2;
        }
        if (!isGE13()) {
            return false;
        }
        boolean zIsBsg3 = isBsg();
        LogTool.d(TAG, "nl3b bsgEnergy: " + zIsBsg3);
        return zIsBsg3;
    }

    public boolean hasWirelessConnection() {
        return isCV();
    }

    public boolean hasWiperLinkage() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(262);
        LogTool.d(TAG, "hasRearWiper = " + z + "reverse_wiper_combined = " + OfflineConfig.getE01OfflineConfig(262));
        return z;
    }

    public boolean hasSocPoint() {
        if (isBx11()) {
            return false;
        }
        if (isVF12()) {
            return isVCU();
        }
        if (isGE13()) {
            return isVCU();
        }
        return false;
    }

    public boolean hasAVM() {
        if (isKC2()) {
            return isKC2QJ();
        }
        if (isFE6() || isFE5()) {
            return 2 == OfflineConfig.getOfflineConfig(this.mContext, 2);
        }
        if (isCV()) {
            return isCVQJOrCVHBQJ() && 2 == OfflineConfig.getOfflineConfig(this.mContext, 2);
        }
        if (isSX()) {
            return 2 == OfflineConfig.getOfflineConfig(this.mContext, 2);
        }
        if (isNL5()) {
            return isNL5HH() || isNL5HBHH();
        }
        if (isNL3B()) {
            return isNL3BHH();
        }
        if (!this.mVehicleModel.contains("GE")) {
            return false;
        }
        Log.d(TAG, "avm = " + OfflineConfig.getE01OfflineConfig(44));
        return 1 == OfflineConfig.getE01OfflineConfig(44);
    }

    public boolean hasWitDoll() {
        return isKC2();
    }

    public boolean noWifiAp() {
        return isFE5HASS() || isFE6SS();
    }

    public boolean hasNearTailGatWithOuteUnlock() {
        if (isNL5()) {
            return false;
        }
        isNL3B();
        return false;
    }

    public boolean hasUserPage() {
        return (isKC2SS() || isFE6SS() || isFE5HASS()) ? false : true;
    }

    public boolean hasOffset() {
        if (isKC2QJ()) {
            return true;
        }
        if (!isCV()) {
            return false;
        }
        boolean z = 2 == OfflineConfig.getOfflineConfig(this.mContext, 4);
        Log.d(TAG, "CV hasAMP = " + z + ",AMP = " + OfflineConfig.getOfflineConfig(this.mContext, 4));
        return z;
    }

    public boolean hasPAS() {
        boolean z;
        if (isSX11()) {
            z = 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 267);
            Log.d(TAG, "sx hasPAS is " + z + ",PAS = " + OfflineConfig.getVehicleOfflineConfig(this.mContext, 267));
        } else if (isCV()) {
            z = 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 267);
            Log.d(TAG, "cv hasPAS is " + z + ",PAS = " + OfflineConfig.getVehicleOfflineConfig(this.mContext, 267));
        } else {
            if (!isFE6() && !isFE5()) {
                return false;
            }
            z = 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 267);
            Log.d(TAG, "fe hasPAS is " + z + ",PAS = " + OfflineConfig.getVehicleOfflineConfig(this.mContext, 267));
        }
        return z;
    }

    public boolean hasDVRFault() {
        if (isSX()) {
            z = 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 288);
            Log.d(TAG, "sx hasDVR = " + z + " , dvr = " + OfflineConfig.getVehicleOfflineConfig(this.mContext, 288));
        }
        return z;
    }

    public boolean hasCustomEQ() {
        Log.d(TAG, "hasCustomEQ()");
        return 2 == getAmpType();
    }

    public boolean hasSurround() {
        Log.d(TAG, "hasSurround()");
        return 2 == getAmpType();
    }

    public int getAmpType() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(104);
        Log.d(TAG, "getAmpType() ampType:" + e01OfflineConfig);
        return e01OfflineConfig;
    }

    public boolean hasPmDetection() {
        return isKC2QJ();
    }

    public boolean hasCarPlayOrCarLife() {
        return !isKC2SS();
    }

    public boolean hasEnergy() {
        if (isKC2()) {
            return isVCU();
        }
        return false;
    }

    public boolean hasDriveModeOfFe() {
        int offlineConfig = OfflineConfig.getOfflineConfig(this.mContext, 37);
        LogTool.d(TAG, "eco_comfort_sport: " + offlineConfig);
        return offlineConfig == 1;
    }

    public boolean hasHvacHKSound() {
        return isSX12();
    }

    public boolean hasBabyMode() {
        return isCV11();
    }

    public boolean hasRightCameraSteerMode() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(126);
        Log.d(TAG, "hasRightCameraSteerMode() mRightCameraState=" + e01OfflineConfig);
        return e01OfflineConfig == 1;
    }

    public boolean hasVeriface() {
        int offlineConfig = OfflineConfig.getOfflineConfig(this.mContext, 26);
        Log.d(TAG, "hasVeriface() mode: " + offlineConfig);
        return offlineConfig == 1;
    }

    public boolean hasBSD() {
        boolean z = 1 == OfflineConfig.getVehicleOfflineConfig(this.mContext, 293);
        Log.d(TAG, "hasBSD hasBSD=" + z);
        return z;
    }

    public boolean hasCustomButton() {
        return (1 == OfflineConfig.getE01OfflineConfig(257)) | (1 == OfflineConfig.getE01OfflineConfig(129));
    }

    public boolean is3ColorsEtc() {
        return isKc2a();
    }

    public boolean is7ColorsEtc() {
        return isGE13();
    }

    public boolean isCustomColorEtc() {
        return isVF12();
    }

    public boolean isCustomColorWithDefaultEtc() {
        return isBx11() || isKc2a() || isSX();
    }

    public boolean hasSteplessAmbient() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(230);
        Log.d(TAG, "hasSteplessAmbient() steplessAmbient:" + e01OfflineConfig);
        return e01OfflineConfig == 1;
    }

    public boolean hasBreachAmbient() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(231);
        Log.d(TAG, "hasBreachAmbient() breachAmbient:" + e01OfflineConfig);
        return e01OfflineConfig == 1;
    }

    public boolean hasSpeedAmbient() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(232);
        Log.d(TAG, "hasSpeedAmbient() speedAmbient:" + e01OfflineConfig);
        return e01OfflineConfig == 1;
    }

    public boolean hasSpeedAlertAmbient() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(233);
        Log.d(TAG, "hasSpeedAlertAmbient() speedAlertAmbient:" + e01OfflineConfig);
        return e01OfflineConfig == 1;
    }

    public boolean hasWelcomeAmbient() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(234);
        Log.d(TAG, "hasWelcomeAmbient() welcomeAmbient:" + e01OfflineConfig);
        return e01OfflineConfig == 1;
    }

    public boolean hasParkingAmbient() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(235);
        Log.d(TAG, "hasParkingAmbient() parkingAmbient:" + e01OfflineConfig);
        return e01OfflineConfig == 1;
    }

    public boolean hasACKeyBeep() {
        return isSx12();
    }

    private boolean isSx12() {
        return isWhichProject("sx12");
    }

    private boolean isFe5ab() {
        return isWhichProject("fe5ab");
    }

    private boolean isKc2a() {
        return isWhichProject("kc2a");
    }

    private boolean isBx11() {
        return isWhichProject("bx11");
    }

    private boolean isVF12() {
        return isWhichProject("vf12");
    }

    public boolean isGE13() {
        return isWhichProject("ge13");
    }

    private boolean isFE() {
        return isWhichProject("fe");
    }

    private boolean isWhichProject(String str) {
        Log.d(TAG, "isWhichProject() VehicleModel is " + this.mVehicleModel + ", project is " + str);
        try {
            return this.mVehicleModel.toUpperCase().contains(str.toUpperCase());
        } catch (Exception e) {
            Log.d(TAG, "isWhichProject() occurred an exception.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean SX12VehicleMode() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(101);
        Log.d(TAG, "SX12VehicleMode() mode=" + e01OfflineConfig);
        return e01OfflineConfig == 2 || e01OfflineConfig == 5 || e01OfflineConfig == 8;
    }

    public boolean isTcu() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(4);
        LogTool.d(TAG, "isTcu tcuMode =" + e01OfflineConfig);
        return e01OfflineConfig == 0;
    }
}
