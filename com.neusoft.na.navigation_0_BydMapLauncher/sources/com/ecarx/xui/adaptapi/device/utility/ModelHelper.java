package com.ecarx.xui.adaptapi.device.utility;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;

/* JADX INFO: loaded from: classes2.dex */
public class ModelHelper {
    private static final String BX11_PROJECT_CODE = "BX11";
    private static final String FE5AB_PROJECT_CODE = "FE5AB";
    private static final String FE6DB_PROJECT_CODE = "FE6DB";
    private static final String GE12A_PROJECT_CODE = "GE13";
    private static final String KC2A_PROJECT_CODE = "KC2A";
    private static final String NEUSOFT_SUPPLIER_CODE = "111003";
    private static final String NL5_PROJECT_CODE = "NL5";
    private static final String SX11RAL_PROJECT_CODE = "MEABGRL";
    private static final String SX11RA_PROJECT_CODE = "MEABGRD";
    private static final String SX11RA_SUPPLIER_CODE = "21634";
    private static final String SX11_PROJECT_CODE = "SX11";
    private static final String SX12_PROJECT_CODE = "SX12";
    private static final String TAG = "Device.ModelHelper";
    private static final String VF11_PROJECT_CODE = "VF11";
    private static final String VF12_PROJECT_CODE = "VF12";
    private static ModelHelper mModelHelper;
    private Context mContext;
    private final String mVehicleModel = SystemProperties.get("ro.product.device");

    public boolean hasAntiPinchWarnSetResp() {
        return false;
    }

    public boolean hasBsgEnergyFlow() {
        return false;
    }

    public boolean hasCarPlayOrCarLife() {
        return false;
    }

    public boolean hasDayRunLightSet() {
        return false;
    }

    public boolean hasEtc() {
        return false;
    }

    public boolean hasRLSModeEnable() {
        return false;
    }

    public boolean hasRearMirrorReverseInclineReq() {
        return false;
    }

    public boolean hasSailing() {
        return false;
    }

    public boolean hasTerminalNotOFFWarnOption() {
        return false;
    }

    public boolean hasVideoForbidden() {
        return true;
    }

    public boolean hasVolumeVersusSpeed() {
        return true;
    }

    public boolean hasVrSetting() {
        return false;
    }

    public boolean hasWelcomeLightSet() {
        return false;
    }

    public boolean hasWifi() {
        return false;
    }

    public int hasWindowSeatSwitch() {
        return 0;
    }

    public boolean hasWiperLinkage() {
        return false;
    }

    public boolean noWifiAp() {
        return false;
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
        return this.mVehicleModel.contains("SX11RA_QJ") ? "SX11RA" : this.mVehicleModel.contains("SX11RA_DS") ? "SX11RAL" : this.mVehicleModel;
    }

    public String getProjectCode() {
        return this.mVehicleModel.contains("BX11QJ") ? BX11_PROJECT_CODE : this.mVehicleModel.contains("GE13QJ") ? GE12A_PROJECT_CODE : this.mVehicleModel.contains("VF12ZG") ? VF12_PROJECT_CODE : this.mVehicleModel.contains("KC2AQJ") ? KC2A_PROJECT_CODE : this.mVehicleModel.contains("FE5ABHH") ? FE5AB_PROJECT_CODE : this.mVehicleModel.contains("FE6DBQJ") ? FE6DB_PROJECT_CODE : this.mVehicleModel.contains(SX12_PROJECT_CODE) ? SX12_PROJECT_CODE : this.mVehicleModel.contains("SX11RA_DS") ? SX11RAL_PROJECT_CODE : this.mVehicleModel.contains("SX11RA_QJ") ? SX11RA_PROJECT_CODE : this.mVehicleModel.contains(NL5_PROJECT_CODE) ? NL5_PROJECT_CODE : this.mVehicleModel.contains(VF11_PROJECT_CODE) ? VF11_PROJECT_CODE : this.mVehicleModel.contains(SX11_PROJECT_CODE) ? SX11_PROJECT_CODE : "";
    }

    public String getSupplierCode() {
        return this.mVehicleModel.contains("SX11RA") ? SX11RA_SUPPLIER_CODE : NEUSOFT_SUPPLIER_CODE;
    }

    public boolean hasDVR() {
        Log.d(TAG, "hasDVR = " + (1 == OfflineConfig.getE01OfflineConfig(35)));
        return false;
    }

    public boolean hasDVRRecord() {
        return hasDVR();
    }

    public boolean hasRearViewCamera() {
        return this.mVehicleModel.contains("KC") && !this.mVehicleModel.contains("KCQJ");
    }

    public boolean isBX11() {
        return this.mVehicleModel.contains(BX11_PROJECT_CODE);
    }

    public boolean isGE12A() {
        return this.mVehicleModel.contains(GE12A_PROJECT_CODE);
    }

    public boolean isVF12() {
        return this.mVehicleModel.contains(VF12_PROJECT_CODE);
    }

    public boolean isKC2A() {
        return this.mVehicleModel.contains(KC2A_PROJECT_CODE);
    }

    public boolean isFE5AB() {
        return this.mVehicleModel.contains(FE5AB_PROJECT_CODE);
    }

    public boolean isSX12() {
        return this.mVehicleModel.contains(SX12_PROJECT_CODE);
    }

    public boolean isFE6DB() {
        return this.mVehicleModel.contains(FE6DB_PROJECT_CODE);
    }

    public boolean isVCU() {
        return 1 == OfflineConfig.getE01OfflineConfig(26);
    }

    public boolean hasAnyDoorOpenWarnOption() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(216);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasAnyDoorOpenWarnOption = " + z);
        return z;
    }

    public boolean hasAPUOption() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(269);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasAPUOption = " + z);
        return z;
    }

    public boolean hasWALOption() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(268);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasWALOption = " + z);
        return z;
    }

    public boolean hasAVASDisabledSet() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(36);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasAVASDisabledSet = " + z);
        return z;
    }

    public boolean hasPAS_SVA_Status() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(123);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasPAS_SVA_Status = " + z);
        return z;
    }

    public boolean hasHUDFunction() {
        boolean z = OfflineConfig.getE01OfflineConfig(226) != 0;
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasHUDFunction = " + z);
        return z;
    }

    public boolean hasRearMirrorFoldSet() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(259);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasRearMirrorFoldSet = " + z);
        return z;
    }

    public boolean hasWirelessCharge() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(108);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasWirelessCharge = " + z);
        return z;
    }

    public boolean hasALAD() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(253);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasALAD = " + z);
        return z;
    }

    public boolean hasSLIF() {
        if (OfflineConfig.getE01OfflineConfig(256) != 0) {
            z = 1 == OfflineConfig.getE01OfflineConfig(250);
            Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasSLIF = " + z);
        }
        return z;
    }

    public boolean hasRCTA() {
        boolean z = OfflineConfig.getE01OfflineConfig(271) != 0;
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasRCTA = " + z);
        return z;
    }

    public boolean hasLCA() {
        boolean z = OfflineConfig.getE01OfflineConfig(270) != 0;
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasLCA = " + z);
        return z;
    }

    public boolean hasDOW() {
        boolean z = OfflineConfig.getE01OfflineConfig(273) != 0;
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasDOW = " + z);
        return z;
    }

    public boolean hasRCW() {
        boolean z = OfflineConfig.getE01OfflineConfig(272) != 0;
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasRCW = " + z);
        return z;
    }

    public boolean hasALOD() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(248);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasALOD = " + z);
        return z;
    }

    public boolean hasStopStart() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(225);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasStopStart = " + z);
        return z;
    }

    public boolean hasPBChargingSwitch() {
        if (!isBX11()) {
            return false;
        }
        boolean zIsPhevType = isPhevType();
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasPBChargingSwitch = " + hasPBChargingSwitch());
        return zIsPhevType;
    }

    public boolean hasPBVolumeMaintenanceSwitch() {
        if (!isBX11()) {
            return false;
        }
        boolean zIsPhevType = isPhevType();
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasPBVolumeMaintenanceSwitch = " + zIsPhevType);
        return zIsPhevType;
    }

    public boolean isPhevType() {
        if (1 != OfflineConfig.getE01OfflineConfig(26) || 1 != OfflineConfig.getE01OfflineConfig(7)) {
            return false;
        }
        Log.d(TAG, "isPhevType phev ");
        return true;
    }

    public boolean hasPDC() {
        if (isGE12A()) {
            return false;
        }
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(220);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasPDC = " + z);
        return z;
    }

    public boolean hasAUTOHOLD() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(221);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasAUTOHOLD = " + z);
        return z;
    }

    public boolean hasHDC() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(222);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasHDC = " + z);
        return z;
    }

    public boolean hasTbox() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(9);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasTbox = " + z);
        return z;
    }
}
