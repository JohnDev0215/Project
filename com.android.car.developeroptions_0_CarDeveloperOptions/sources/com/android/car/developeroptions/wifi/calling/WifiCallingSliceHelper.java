package com.android.car.developeroptions.wifi.calling;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import com.android.ims.ImsManager;

/* JADX INFO: loaded from: classes.dex */
public class WifiCallingSliceHelper {
    private final Context mContext;

    public WifiCallingSliceHelper(Context context) {
        this.mContext = context;
    }

    protected ImsManager getImsManager(int i) {
        return ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(i));
    }

    public void handleWifiCallingChanged(Intent intent) {
        int defaultVoiceSubId = getDefaultVoiceSubId();
        if (defaultVoiceSubId > -1) {
            ImsManager imsManager = getImsManager(defaultVoiceSubId);
            if (imsManager.isWfcEnabledByPlatform() && imsManager.isWfcProvisionedOnDevice()) {
                boolean z = imsManager.isWfcEnabledByUser() && imsManager.isNonTtyOrTtyOnVolteEnabled();
                boolean booleanExtra = intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", z);
                Intent wifiCallingCarrierActivityIntent = getWifiCallingCarrierActivityIntent(defaultVoiceSubId);
                if ((!booleanExtra || wifiCallingCarrierActivityIntent == null) && booleanExtra != z) {
                    imsManager.setWfcSetting(booleanExtra);
                }
            }
        }
        this.mContext.getContentResolver().notifyChange(CustomSliceRegistry.WIFI_CALLING_URI, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleWifiCallingPreferenceChanged(android.content.Intent r10) {
        /*
            r9 = this;
            int r0 = r9.getDefaultVoiceSubId()
            r1 = -1
            if (r0 <= r1) goto L84
            java.lang.String r2 = "editable_wfc_mode_bool"
            r3 = 0
            boolean r2 = r9.isCarrierConfigManagerKeyEnabled(r2, r0, r3)
            java.lang.String r4 = "carrier_wfc_supports_wifi_only_bool"
            r5 = 1
            boolean r4 = r9.isCarrierConfigManagerKeyEnabled(r4, r0, r5)
            com.android.ims.ImsManager r0 = r9.getImsManager(r0)
            if (r2 == 0) goto L84
            boolean r2 = r0.isWfcEnabledByPlatform()
            if (r2 == 0) goto L84
            boolean r2 = r0.isWfcProvisionedOnDevice()
            if (r2 == 0) goto L84
            boolean r2 = r0.isWfcEnabledByUser()
            if (r2 == 0) goto L84
            boolean r2 = r0.isNonTtyOrTtyOnVolteEnabled()
            if (r2 == 0) goto L84
            int r2 = r0.getWfcMode(r3)
            java.lang.String r10 = r10.getAction()
            int r6 = r10.hashCode()
            r7 = -1458618481(0xffffffffa90f3f8f, float:-3.1807507E-14)
            r8 = 2
            if (r6 == r7) goto L64
            r7 = -1029084578(0xffffffffc2a96a5e, float:-84.70775)
            if (r6 == r7) goto L5a
            r7 = -428754189(0xffffffffe671baf3, float:-2.8538473E23)
            if (r6 == r7) goto L50
            goto L6e
        L50:
            java.lang.String r6 = "com.android.car.developeroptions.slice.action.WIFI_CALLING_PREFERENCE_CELLULAR_PREFERRED"
            boolean r10 = r10.equals(r6)
            if (r10 == 0) goto L6e
            r10 = r8
            goto L6f
        L5a:
            java.lang.String r6 = "com.android.car.developeroptions.slice.action.WIFI_CALLING_PREFERENCE_WIFI_PREFERRED"
            boolean r10 = r10.equals(r6)
            if (r10 == 0) goto L6e
            r10 = r5
            goto L6f
        L64:
            java.lang.String r6 = "com.android.car.developeroptions.slice.action.WIFI_CALLING_PREFERENCE_WIFI_ONLY"
            boolean r10 = r10.equals(r6)
            if (r10 == 0) goto L6e
            r10 = r3
            goto L6f
        L6e:
            r10 = r1
        L6f:
            if (r10 == 0) goto L78
            if (r10 == r5) goto L76
            if (r10 == r8) goto L7d
            goto L7c
        L76:
            r5 = r8
            goto L7d
        L78:
            if (r4 == 0) goto L7c
            r5 = r3
            goto L7d
        L7c:
            r5 = r1
        L7d:
            if (r5 == r1) goto L84
            if (r5 == r2) goto L84
            r0.setWfcMode(r5, r3)
        L84:
            android.content.Context r9 = r9.mContext
            android.content.ContentResolver r9 = r9.getContentResolver()
            android.net.Uri r10 = com.android.car.developeroptions.slices.CustomSliceRegistry.WIFI_CALLING_PREFERENCE_URI
            r0 = 0
            r9.notifyChange(r10, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.wifi.calling.WifiCallingSliceHelper.handleWifiCallingPreferenceChanged(android.content.Intent):void");
    }

    protected boolean isCarrierConfigManagerKeyEnabled(String str, int i, boolean z) {
        PersistableBundle configForSubId;
        CarrierConfigManager carrierConfigManager = getCarrierConfigManager(this.mContext);
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(i)) == null) {
            return false;
        }
        return configForSubId.getBoolean(str, z);
    }

    protected CarrierConfigManager getCarrierConfigManager(Context context) {
        return (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
    }

    protected int getDefaultVoiceSubId() {
        return SubscriptionManager.getDefaultVoiceSubscriptionId();
    }

    protected Intent getWifiCallingCarrierActivityIntent(int i) {
        PersistableBundle configForSubId;
        ComponentName componentNameUnflattenFromString;
        CarrierConfigManager carrierConfigManager = getCarrierConfigManager(this.mContext);
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(i)) == null) {
            return null;
        }
        String string = configForSubId.getString("wfc_emergency_address_carrier_app_string");
        if (TextUtils.isEmpty(string) || (componentNameUnflattenFromString = ComponentName.unflattenFromString(string)) == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.setComponent(componentNameUnflattenFromString);
        return intent;
    }
}
