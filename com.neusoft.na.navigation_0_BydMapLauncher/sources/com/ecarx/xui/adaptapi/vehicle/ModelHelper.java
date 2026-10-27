package com.ecarx.xui.adaptapi.vehicle;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import com.ecarx.xui.adaptapi.policy.ContextProxy;
import com.ecarx.xui.adaptapi.policy.VoiceAssistantPolicy;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ModelHelper {
    private static final String CV_PROJECT_CODE = "VF11";
    private static final String FE5_PROJECT_CODE = "FE-5";
    private static final String FE_PROJECT_CODE = "FE-6";
    private static final String KC_PROJECT_CODE = "KC-2";
    private static final String NEUSOFT_SUPPLIER_CODE = "111003";
    private static final String NL3B_PROJECT_CODE = "NL3B";
    private static final String NL5_PROJECT_CODE = "NL5";
    private static final String SX11_PROJECT_CODE = "SX11";
    private static final String SX12_PROJECT_CODE = "SX12";
    private static final String TAG = "Device.ModelHelper";
    private static ModelHelper mModelHelper;
    private Context mContext;
    private final String mVehicleModel = SystemProperties.get("ro.product.device");

    public String getSupplierCode() {
        return NEUSOFT_SUPPLIER_CODE;
    }

    public boolean hasATWarnTypeSet() {
        return true;
    }

    public boolean hasCarPlayOrCarLife() {
        return false;
    }

    public boolean hasCornerLampSet() {
        return false;
    }

    public boolean hasTerminalNotOFFWarnOption() {
        return false;
    }

    public boolean hasVrSetting() {
        return false;
    }

    public int hasWindowSeatSwitch() {
        return 0;
    }

    private ModelHelper(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static synchronized ModelHelper getInstance(Context context) {
        if (mModelHelper == null) {
            mModelHelper = new ModelHelper(context);
        }
        return mModelHelper;
    }

    public String getVehicleType() {
        return this.mVehicleModel;
    }

    public String getProjectCode() {
        return this.mVehicleModel.contains("KC") ? "KC-2" : this.mVehicleModel.contains("FE6") ? FE_PROJECT_CODE : this.mVehicleModel.contains("FE5") ? FE5_PROJECT_CODE : this.mVehicleModel.contains(ContextProxy.CV) ? CV_PROJECT_CODE : this.mVehicleModel.contains(SX11_PROJECT_CODE) ? SX11_PROJECT_CODE : this.mVehicleModel.contains(SX12_PROJECT_CODE) ? SX12_PROJECT_CODE : this.mVehicleModel.contains(NL5_PROJECT_CODE) ? NL5_PROJECT_CODE : this.mVehicleModel.contains(NL3B_PROJECT_CODE) ? NL3B_PROJECT_CODE : "";
    }

    public boolean hasDVR() {
        boolean z;
        if (isSX()) {
            z = 1 == OfflineConfig.getVehicleOfflineConfig(299);
            Log.d(TAG, "sx hasDVR = " + z);
            return z;
        }
        z = 1 == OfflineConfig.getVehicleOfflineConfig(288);
        Log.d(TAG, "hasDVR = " + z);
        return z;
    }

    public boolean hasRearViewCamera() {
        return this.mVehicleModel.contains("KC") && !this.mVehicleModel.contains("KCQJ");
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

    public boolean isCV() {
        return this.mVehicleModel.contains(ContextProxy.CV);
    }

    public boolean isSX() {
        return this.mVehicleModel.contains(ContextProxy.SX);
    }

    public boolean isSX11() {
        return this.mVehicleModel.contains(SX11_PROJECT_CODE);
    }

    public boolean isNL() {
        return this.mVehicleModel.contains(ContextProxy.NL);
    }

    public boolean isNL5() {
        return this.mVehicleModel.contains(NL5_PROJECT_CODE);
    }

    public boolean isNL3B() {
        return this.mVehicleModel.contains(NL3B_PROJECT_CODE);
    }

    public boolean isNL3BQJ() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.NL3BQJ);
    }

    public boolean isNL3BHH() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.NL3BHH);
    }

    public boolean isNL5QJ() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.NL5QJ) || this.mVehicleModel.equals("NL5HBQJ");
    }

    public boolean isNL5HH() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.NL5HH);
    }

    public boolean isNL5HBQJ() {
        return this.mVehicleModel.equals("NL5HBQJ");
    }

    public boolean isNL5HBHH() {
        return this.mVehicleModel.equals("NL5HBHH");
    }

    public boolean isFE6() {
        return this.mVehicleModel.contains("FE6");
    }

    public boolean isFE6QJ() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.FE6QJ);
    }

    public boolean isFE6SS() {
        return this.mVehicleModel.equals(VoiceAssistantPolicy.FE6SS);
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

    public boolean isHH() {
        return this.mVehicleModel.contains("HH");
    }

    public boolean isHB() {
        return this.mVehicleModel.contains("HB");
    }

    public boolean isVCU() {
        return 1 == OfflineConfig.getVehicleOfflineConfig(284);
    }

    public boolean hasAntiPinchWarnSetResp() {
        if (isKC2()) {
            return false;
        }
        boolean z = 2 == OfflineConfig.getE01OfflineConfig(17) || 3 == OfflineConfig.getE01OfflineConfig(17);
        Log.d(TAG, "vehicle type: " + this.mVehicleModel + " hasAntiPinchWarn: " + z + " Window_Clamp =" + OfflineConfig.getE01OfflineConfig(17));
        return z;
    }

    public boolean hasIGNOFFUnlockDoorSelect() {
        Log.d(TAG, "---------------hasIGNOFFUnlockDoorSelect");
        return 1 == OfflineConfig.getE01OfflineConfig(213);
    }

    public boolean hasAnyDoorOpenWarnOption() {
        Log.d(TAG, "---------------hasAnyDoorOpenWarnOption");
        return 1 == OfflineConfig.getE01OfflineConfig(216);
    }

    public boolean noWifiAp() {
        return isFE5HASS() || isFE6SS();
    }

    public boolean hasAPUOption() {
        Log.d(TAG, "---------------hasAPUOption");
        return 1 == OfflineConfig.getE01OfflineConfig(269);
    }

    public boolean hasAVASDisabledSet() {
        Log.d(TAG, "---------------hasAVASDisabledSet");
        return 1 == OfflineConfig.getE01OfflineConfig(250);
    }

    public boolean hasPAS_SVA_Status() {
        Log.d(TAG, "---------------hasPAS_SVA_Status");
        return 1 == OfflineConfig.getE01OfflineConfig(126);
    }

    public boolean hasSVA_Request() {
        Log.d(TAG, "---------------hasSVA_Request");
        return 1 == OfflineConfig.getE01OfflineConfig(123);
    }

    public boolean hasAebOption() {
        Log.d(TAG, "---------------hasAebOption");
        return 1 == OfflineConfig.getE01OfflineConfig(249);
    }

    public boolean hasDayRunLightSet() {
        Log.d(TAG, "---------------hasAebOption");
        return 1 == OfflineConfig.getE01OfflineConfig(260);
    }

    public boolean hasVideoForbidden() {
        return !isKC2();
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

    public boolean hasHUDFunction() {
        boolean z = 1 == OfflineConfig.getVehicleOfflineConfig(43);
        Log.d(TAG, "kc hasHUDFunction: " + z);
        return z;
    }

    public boolean hasSpeedAutoLockLayout() {
        Log.d(TAG, "---------------hasSpeedAutoLockLayout");
        return false;
    }

    public boolean hasRearMirrorFoldSet() {
        Log.d(TAG, "---------------hasRearMirrorFoldSet");
        return 1 == OfflineConfig.getE01OfflineConfig(259);
    }

    public boolean hasRLSModeEnable() {
        Log.d(TAG, "---------------hasRLSModeEnable");
        return 1 == OfflineConfig.getE01OfflineConfig(206);
    }

    public boolean hasRearMirrorReverseInclineReq() {
        Log.d(TAG, "---------------hasRearMirrorReverseInclineReq");
        return 1 == OfflineConfig.getE01OfflineConfig(263);
    }

    public boolean hasIhbcOption() {
        Log.d(TAG, "---------------hasIhbcOption");
        return 1 == OfflineConfig.getE01OfflineConfig(252);
    }

    public boolean hasWelcomeLightSet() {
        Log.d(TAG, "---------------hasWelcomeLightSet");
        return 1 == OfflineConfig.getE01OfflineConfig(261);
    }

    public boolean hasWALOption() {
        Log.d(TAG, "---------------hasWALOption");
        return 1 == OfflineConfig.getE01OfflineConfig(268);
    }

    public boolean hasWifi() {
        if (isCV()) {
            return true;
        }
        boolean z = 2 == OfflineConfig.getE01OfflineConfig(1);
        Log.d(TAG, "hasWifi: " + OfflineConfig.getE01OfflineConfig(1));
        return z;
    }

    public boolean hasWiperLinkage() {
        Log.d(TAG, "---------------hasWiperLinkage");
        return 1 == OfflineConfig.getE01OfflineConfig(262);
    }

    public boolean hasWirelessCharge() {
        boolean z = 2 == OfflineConfig.getE01OfflineConfig(11);
        Log.d(TAG, "vehicle type " + this.mVehicleModel + " hasWirelessCharge: " + OfflineConfig.getE01OfflineConfig(11));
        return z;
    }

    public boolean hasVolumeVersusSpeed() {
        return !isKC2();
    }

    public boolean hasLkaOption() {
        Log.d(TAG, "---------------hasLkaOption");
        return 1 == OfflineConfig.getE01OfflineConfig(253);
    }

    public boolean hasAutoCloseWindowSet() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(207);
        Log.d(TAG, "sx_vehicle_mode  " + z);
        return z;
    }

    public boolean hasNearTailGatWithOuteUnlock() {
        Log.d(TAG, "---------------hasNearTailGatWithOuteUnlock");
        return 1 == OfflineConfig.getE01OfflineConfig(267);
    }

    public boolean hasSATOOption() {
        Log.d(TAG, "---------------hasSATOOption");
        return 1 == OfflineConfig.getE01OfflineConfig(266);
    }

    public boolean hasTrunkUnlockDistanceOption() {
        Log.d(TAG, "---------------hasTrunkUnlockDistanceOption");
        return 1 == OfflineConfig.getE01OfflineConfig(266);
    }

    public boolean hasTrunkUnlockWithoutKeyOption() {
        Log.d(TAG, "---------------hasTrunkUnlockWithoutKeyOption");
        return 1 == OfflineConfig.getE01OfflineConfig(211);
    }

    public boolean hasSeatMemoryReq() {
        Log.d(TAG, "---------------hasSeatMemoryReq");
        return 1 == OfflineConfig.getE01OfflineConfig(229);
    }

    public boolean hasRoofLightConnectDoorSet() {
        Log.d(TAG, "---------------hasRoofLightConnectDoorSet");
        return false;
    }

    public boolean hasEnergy() {
        Log.d(TAG, "---------------hasEnergy");
        return false;
    }

    public boolean hasKeyInReminderOption() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(218);
        Log.d(TAG, "---------------hasKeyInReminderOption" + z);
        return z;
    }

    public boolean hasTrunkUpBasicPositionSet() {
        boolean z = OfflineConfig.getE01OfflineConfig(212) == 0;
        Log.d(TAG, "---------------hasTrunkUpBasicPositionSet" + z);
        return !z;
    }

    public boolean hasRKEUnlockSet() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(264);
        Log.d(TAG, "---------------hasRKEUnlockSet" + z);
        return z;
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

    public boolean hasRightCameraSteerMode() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(126);
        Log.d(TAG, "hasRightCameraSteerMode() mRightCameraState=" + e01OfflineConfig);
        return e01OfflineConfig == 1;
    }

    public boolean hasBreachAmbient() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(231);
        Log.d(TAG, "hasBreachAmbient() breachAmbient:" + e01OfflineConfig);
        return e01OfflineConfig == 1;
    }

    public boolean hasACKeyBeep() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(236);
        Log.d(TAG, "hasACKeyBeep" + z);
        return z;
    }

    public boolean hasFrontWiperIdleModeSet() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(205);
        Log.d(TAG, "hasFrontWiperIdleModeSet" + z);
        return z;
    }

    public boolean hasIccOption() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(248);
        Log.d(TAG, "hasIccOption" + z);
        return z;
    }

    public boolean hasFollowMeHomeTimeSelect() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(203);
        Log.d(TAG, "hasFollowMeHomeTimeSelect" + z);
        return z;
    }

    public boolean hasFlasherAnimControlSet() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(202);
        Log.d(TAG, "hasFlasherAnimControlSet" + z);
        return z;
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

    private boolean isSx12() {
        return isWhichProject("sx12");
    }

    public boolean isTcu() {
        int e01OfflineConfig = OfflineConfig.getE01OfflineConfig(4);
        Log.d(TAG, "isTcu tcuMode =" + e01OfflineConfig);
        return e01OfflineConfig == 0;
    }

    public boolean hasSunroofRollShade() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(223);
        Log.d(TAG, "hasSunroofRollShade" + z);
        return z;
    }
}
