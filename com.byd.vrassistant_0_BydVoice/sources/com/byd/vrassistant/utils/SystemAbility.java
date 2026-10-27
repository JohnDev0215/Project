package com.byd.vrassistant.utils;

import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.text.TextUtils;
import android.util.Log;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SystemAbility {
    private static final String TAG = "NEU_SystemAbility";
    List<Integer> offlineRegionAry = Collections.singletonList(90);
    List<Integer> ICCIDRegionAry = Collections.singletonList(286);
    List<Integer> disGPSRegionAry = Collections.singletonList(82);
    List<Integer> disGPSICCIDRegionAry = Collections.singletonList(450);

    public boolean isCountryCodeIn(List<Integer> list) {
        StringBuilder sb;
        String str = SystemPropertiesUtils.get(BYDAutoSysPropertiesDevice.COUNTRY_CODE);
        Log.e(TAG, "isCountryCodeIn : " + str);
        boolean zContains = false;
        try {
            try {
                if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
                    zContains = list.contains(Integer.valueOf(Integer.parseInt(str.trim())));
                    Log.e(TAG, "isCountryCodeIn : " + zContains);
                }
                sb = new StringBuilder("isCountryCodeIn : ");
            } catch (NumberFormatException e) {
                Log.e(TAG, "isCountryCodeIn : " + e);
                sb = new StringBuilder("isCountryCodeIn : ");
            }
            Log.d(TAG, sb.append(zContains).toString());
            return zContains;
        } catch (Throwable th) {
            Log.d(TAG, "isCountryCodeIn : " + zContains);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isICCIDRegion(java.util.List<java.lang.Integer> r10) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.utils.SystemAbility.isICCIDRegion(java.util.List):boolean");
    }

    public boolean isForceOfflineCountryCode() {
        return isCountryCodeIn(this.offlineRegionAry);
    }

    public boolean isDisGPSCountryCode() {
        return isCountryCodeIn(this.disGPSRegionAry);
    }

    public boolean isDisGPSICCID() {
        return isICCIDRegion(this.disGPSICCIDRegionAry);
    }

    public boolean isForceOfflineICCIDRegion() {
        return isICCIDRegion(this.ICCIDRegionAry);
    }

    public boolean supportNet() {
        return !isForceOfflineCountryCode();
    }

    public boolean noGps() {
        return isDisGPSCountryCode() || isDisGPSICCID();
    }
}
