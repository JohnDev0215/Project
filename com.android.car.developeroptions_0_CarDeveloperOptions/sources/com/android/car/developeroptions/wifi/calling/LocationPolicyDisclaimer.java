package com.android.car.developeroptions.wifi.calling;

import android.content.Context;
import android.os.PersistableBundle;
import com.android.car.developeroptions.R;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
class LocationPolicyDisclaimer extends DisclaimerItem {

    @VisibleForTesting
    static final String KEY_HAS_AGREED_LOCATION_DISCLAIMER = "key_has_agreed_location_disclaimer";

    @Override // com.android.car.developeroptions.wifi.calling.DisclaimerItem
    protected int getMessageId() {
        return R.string.wfc_disclaimer_location_desc_text;
    }

    @Override // com.android.car.developeroptions.wifi.calling.DisclaimerItem
    protected String getName() {
        return "LocationPolicyDisclaimer";
    }

    @Override // com.android.car.developeroptions.wifi.calling.DisclaimerItem
    protected String getPrefKey() {
        return KEY_HAS_AGREED_LOCATION_DISCLAIMER;
    }

    @Override // com.android.car.developeroptions.wifi.calling.DisclaimerItem
    protected int getTitleId() {
        return R.string.wfc_disclaimer_location_title_text;
    }

    LocationPolicyDisclaimer(Context context, int i) {
        super(context, i);
    }

    @Override // com.android.car.developeroptions.wifi.calling.DisclaimerItem
    boolean shouldShow() {
        PersistableBundle carrierConfig = getCarrierConfig();
        if (!carrierConfig.getBoolean("show_wfc_location_privacy_policy_bool")) {
            logd("shouldShow: false due to carrier config is false.");
            return false;
        }
        if (carrierConfig.getBoolean("carrier_default_wfc_ims_enabled_bool")) {
            logd("shouldShow: false due to WFC is on as default.");
            return false;
        }
        return super.shouldShow();
    }
}
