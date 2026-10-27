package com.android.car.developeroptions.homepage.contextualcards.deviceinfo;

import android.content.Context;
import android.content.Intent;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.CustomSliceable;
import com.android.settingslib.DeviceInfoUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DeviceInfoSlice implements CustomSliceable {
    private final Context mContext;
    private final SubscriptionManager mSubscriptionManager;

    @Override // com.android.car.developeroptions.slices.CustomSliceable
    public void onNotifyChange(Intent intent) {
    }

    public DeviceInfoSlice(Context context) {
        this.mContext = context;
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
    }

    CharSequence getPhoneNumber() {
        SubscriptionInfo firstSubscriptionInfo = getFirstSubscriptionInfo();
        if (firstSubscriptionInfo == null) {
            return this.mContext.getString(R.string.device_info_default);
        }
        String formattedPhoneNumber = DeviceInfoUtils.getFormattedPhoneNumber(this.mContext, firstSubscriptionInfo);
        return TextUtils.isEmpty(formattedPhoneNumber) ? this.mContext.getString(R.string.device_info_default) : BidiFormatter.getInstance().unicodeWrap(formattedPhoneNumber, TextDirectionHeuristics.LTR);
    }

    SubscriptionInfo getFirstSubscriptionInfo() {
        List activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null || activeSubscriptionInfoList.isEmpty()) {
            return null;
        }
        return (SubscriptionInfo) activeSubscriptionInfoList.get(0);
    }
}
