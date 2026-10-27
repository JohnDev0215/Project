package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.telephony.SubscriptionManager;
import com.android.car.developeroptions.slices.CustomSliceRegistry;
import com.android.ims.ImsManager;

/* JADX INFO: loaded from: classes.dex */
public class Enhanced4gLteSliceHelper {
    private final Context mContext;

    public Enhanced4gLteSliceHelper(Context context) {
        this.mContext = context;
    }

    protected ImsManager getImsManager(int i) {
        return ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(i));
    }

    public void handleEnhanced4gLteChanged(Intent intent) {
        int defaultVoiceSubId = getDefaultVoiceSubId();
        if (defaultVoiceSubId > -1) {
            ImsManager imsManager = getImsManager(defaultVoiceSubId);
            if (imsManager.isVolteEnabledByPlatform() && imsManager.isVolteProvisionedOnDevice()) {
                boolean z = imsManager.isEnhanced4gLteModeSettingEnabledByUser() && imsManager.isNonTtyOrTtyOnVolteEnabled();
                boolean booleanExtra = intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", z);
                if (booleanExtra != z) {
                    imsManager.setEnhanced4gLteModeSetting(booleanExtra);
                }
            }
        }
        this.mContext.getContentResolver().notifyChange(CustomSliceRegistry.ENHANCED_4G_SLICE_URI, null);
    }

    protected int getDefaultVoiceSubId() {
        return SubscriptionManager.getDefaultVoiceSubscriptionId();
    }
}
