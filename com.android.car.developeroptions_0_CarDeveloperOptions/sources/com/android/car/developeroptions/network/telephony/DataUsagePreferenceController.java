package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkTemplate;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.net.DataUsageController;

/* JADX INFO: loaded from: classes.dex */
public class DataUsagePreferenceController extends TelephonyBasePreferenceController {
    private DataUsageController.DataUsageInfo mDataUsageInfo;
    private Intent mIntent;
    private NetworkTemplate mTemplate;

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return i != -1 ? 0 : 1;
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyBasePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public DataUsagePreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        this.mContext.startActivity(this.mIntent);
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mSubId == -1) {
            preference.setEnabled(false);
            return;
        }
        long historicalUsageLevel = this.mDataUsageInfo.usageLevel;
        if (historicalUsageLevel <= 0) {
            historicalUsageLevel = new DataUsageController(this.mContext).getHistoricalUsageLevel(this.mTemplate);
        }
        boolean z = historicalUsageLevel > 0;
        preference.setEnabled(z);
        if (z) {
            Context context = this.mContext;
            preference.setSummary(context.getString(R.string.data_usage_template, Formatter.formatFileSize(context, this.mDataUsageInfo.usageLevel), this.mDataUsageInfo.period));
        }
    }

    public void init(int i) {
        this.mSubId = i;
        if (i != -1) {
            this.mTemplate = getNetworkTemplate(this.mContext, i);
            this.mDataUsageInfo = new DataUsageController(this.mContext).getDataUsageInfo(this.mTemplate);
            Intent intent = new Intent("android.settings.MOBILE_DATA_USAGE");
            this.mIntent = intent;
            intent.putExtra("network_template", (Parcelable) this.mTemplate);
            this.mIntent.putExtra("android.provider.extra.SUB_ID", this.mSubId);
        }
    }

    private NetworkTemplate getNetworkTemplate(Context context, int i) {
        TelephonyManager telephonyManagerCreateForSubscriptionId = TelephonyManager.from(context).createForSubscriptionId(i);
        return NetworkTemplate.normalize(NetworkTemplate.buildTemplateMobileAll(telephonyManagerCreateForSubscriptionId.getSubscriberId()), telephonyManagerCreateForSubscriptionId.getMergedSubscriberIds());
    }
}
