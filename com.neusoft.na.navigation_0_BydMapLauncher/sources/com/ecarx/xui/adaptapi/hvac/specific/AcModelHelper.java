package com.ecarx.xui.adaptapi.hvac.specific;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;

/* JADX INFO: loaded from: classes2.dex */
public class AcModelHelper {
    private static final String TAG = "ModelHelper";
    private static volatile AcModelHelper mModelHelper;
    private Context mContext;
    private final String mVehicleModel = SystemProperties.get("ro.product.device");

    public boolean hasADS() {
        return false;
    }

    public boolean hasFragrance() {
        return false;
    }

    public boolean hasMaxAc() {
        return true;
    }

    public static AcModelHelper getInstance(Context context) {
        if (mModelHelper == null) {
            synchronized (AcModelHelper.class) {
                if (mModelHelper == null) {
                    mModelHelper = new AcModelHelper(context);
                }
            }
        }
        return mModelHelper;
    }

    private AcModelHelper(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public boolean isBX11() {
        return this.mVehicleModel.contains("BX11");
    }

    public boolean isVCU() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(26);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "isVCU = " + z);
        return z;
    }

    public boolean isBSG() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(29);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "isBSG = " + z);
        return z;
    }

    public boolean hasPHEV() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(7);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasPHEV = " + z);
        return z;
    }

    public boolean isPhevType() {
        if (1 != OfflineConfig.getVehicleOfflineConfig(this.mContext, 284) || 1 != OfflineConfig.getVehicleOfflineConfig(this.mContext, 255)) {
            return false;
        }
        Log.d(TAG, "isPhevType phev ");
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean hasAuto() {
        /*
            r3 = this;
            r0 = 116(0x74, float:1.63E-43)
            int r1 = com.neusoft.optimus.utility.offlineconfig.OfflineConfig.getE01OfflineConfig(r0)
            if (r1 == 0) goto L10
            int r0 = com.neusoft.optimus.utility.offlineconfig.OfflineConfig.getE01OfflineConfig(r0)
            r1 = 1
            if (r1 == r0) goto L10
            goto L11
        L10:
            r1 = 0
        L11:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "mVehicleModel = "
            r0.<init>(r2)
            java.lang.String r2 = r3.mVehicleModel
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = "hasAuto = "
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "ModelHelper"
            android.util.Log.d(r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.xui.adaptapi.hvac.specific.AcModelHelper.hasAuto():boolean");
    }

    public boolean hasAqs() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(118);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasAqs = " + z);
        return z;
    }

    public boolean hasIon() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(119);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasIon = " + z);
        return z;
    }

    public boolean hasPM() {
        boolean z = OfflineConfig.getE01OfflineConfig(157) != 0;
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasPM = " + z);
        return z;
    }

    public boolean isBlowingLevel8() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(246);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "isBlowingLevel8 = " + z);
        return z;
    }

    public boolean hasRearLock() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(120);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasRearLock = " + z);
        return z;
    }

    public boolean hasParking() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(237) && isPhevType();
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasParking = " + z);
        return z;
    }

    public boolean hasAc() {
        boolean z = OfflineConfig.getE01OfflineConfig(13) != 0;
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasAc = " + z);
        return z;
    }

    public int getHvacType() {
        if (1 == OfflineConfig.getE01OfflineConfig(116)) {
            Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "getHvacType = 4");
            return 4;
        }
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "getHvacType = 2");
        return 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean hasDual() {
        /*
            r3 = this;
            r0 = 116(0x74, float:1.63E-43)
            int r0 = com.neusoft.optimus.utility.offlineconfig.OfflineConfig.getE01OfflineConfig(r0)
            if (r0 == 0) goto Lf
            r1 = 1
            if (r1 == r0) goto Lf
            r2 = 2
            if (r2 == r0) goto Lf
            goto L10
        Lf:
            r1 = 0
        L10:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "mVehicleModel = "
            r0.<init>(r2)
            java.lang.String r2 = r3.mVehicleModel
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = "hasDual = "
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "ModelHelper"
            android.util.Log.d(r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.xui.adaptapi.hvac.specific.AcModelHelper.hasDual():boolean");
    }

    public boolean isAcMultipleTemp() {
        boolean z = 4 == OfflineConfig.getE01OfflineConfig(116) || 5 == OfflineConfig.getE01OfflineConfig(116);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "isAcMultipleTemp = " + z);
        return z;
    }

    public boolean hasRCPSet() {
        boolean z = 4 == OfflineConfig.getE01OfflineConfig(116) || 5 == OfflineConfig.getE01OfflineConfig(116);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasRCPSet = " + z);
        return z;
    }

    public boolean hasAcThirdPanel() {
        boolean z = 5 == OfflineConfig.getE01OfflineConfig(116);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasAcThirdPanel = " + z);
        return z;
    }

    public boolean hasAcEco() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(238);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasAcEco = " + z);
        return z;
    }

    public boolean hasAcCO2() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(239);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasAcCO2 = " + z);
        return z;
    }

    public boolean hasDrvHeat() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(152);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasDrvHeat = " + z);
        return z;
    }

    public boolean hasDrvVent() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(135);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasDrvVent = " + z);
        return z;
    }

    public boolean hasPassVent() {
        return hasDrvVent();
    }

    public boolean hasDrvMassage() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(228);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasDrvMassage = " + z);
        return z;
    }

    public boolean hasRearDefrost() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(275);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasRearDefrost = " + z);
        return z;
    }

    public boolean hasAcPTCHeat() {
        boolean z = 1 == OfflineConfig.getE01OfflineConfig(241);
        Log.d(TAG, "mVehicleModel = " + this.mVehicleModel + "hasAcPTCHeat = " + z);
        return z;
    }
}
