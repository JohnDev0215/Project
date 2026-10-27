package com.android.car.developeroptions.wifi.calling;

import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
public class EmergencyCallLimitationDisclaimer extends DisclaimerItem {

    @VisibleForTesting
    static final String KEY_HAS_AGREED_EMERGENCY_LIMITATION_DISCLAIMER = "key_has_agreed_emergency_limitation_disclaimer";

    @Override // com.android.car.developeroptions.wifi.calling.DisclaimerItem
    protected int getMessageId() {
        return R.string.wfc_disclaimer_emergency_limitation_desc_text;
    }

    @Override // com.android.car.developeroptions.wifi.calling.DisclaimerItem
    protected String getName() {
        return "EmergencyCallLimitationDisclaimer";
    }

    @Override // com.android.car.developeroptions.wifi.calling.DisclaimerItem
    protected String getPrefKey() {
        return KEY_HAS_AGREED_EMERGENCY_LIMITATION_DISCLAIMER;
    }

    @Override // com.android.car.developeroptions.wifi.calling.DisclaimerItem
    protected int getTitleId() {
        return R.string.wfc_disclaimer_emergency_limitation_title_text;
    }

    public EmergencyCallLimitationDisclaimer(Context context, int i) {
        super(context, i);
    }

    @Override // com.android.car.developeroptions.wifi.calling.DisclaimerItem
    boolean shouldShow() {
        if (getCarrierConfig().getInt("emergency_notification_delay_int") == -1) {
            logd("shouldShow: false due to carrier config is default(-1).");
            return false;
        }
        return super.shouldShow();
    }
}
