package com.android.car.developeroptions.deviceinfo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.IntentFilter;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.DeviceInfoUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PhoneNumberPreferenceController extends BasePreferenceController {
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private final List<Preference> mPreferenceList;
    private final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public boolean isCopyableSlice() {
        return true;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return true;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public boolean useDynamicSliceSummary() {
        return true;
    }

    public PhoneNumberPreferenceController(Context context, String str) {
        super(context, str);
        this.mPreferenceList = new ArrayList();
        this.mTelephonyManager = (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
        this.mSubscriptionManager = (SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mTelephonyManager.isVoiceCapable() ? 0 : 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return getFirstPhoneNumber();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mPreferenceList.add(preferenceFindPreference);
        int order = preferenceFindPreference.getOrder();
        for (int i = 1; i < this.mTelephonyManager.getPhoneCount(); i++) {
            Preference preferenceCreateNewPreference = createNewPreference(preferenceScreen.getContext());
            preferenceCreateNewPreference.setOrder(order + i);
            preferenceCreateNewPreference.setKey(KEY_PHONE_NUMBER + i);
            preferenceScreen.addPreference(preferenceCreateNewPreference);
            this.mPreferenceList.add(preferenceCreateNewPreference);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        for (int i = 0; i < this.mPreferenceList.size(); i++) {
            Preference preference2 = this.mPreferenceList.get(i);
            preference2.setTitle(getPreferenceTitle(i));
            preference2.setSummary(getPhoneNumber(i));
        }
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public void copy() {
        ((ClipboardManager) this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", getFirstPhoneNumber()));
        Context context = this.mContext;
        Toast.makeText(this.mContext, context.getString(R.string.copyable_slice_toast, context.getText(R.string.status_number)), 0).show();
    }

    private CharSequence getFirstPhoneNumber() {
        List activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null) {
            return this.mContext.getText(R.string.device_info_default);
        }
        return getFormattedPhoneNumber((SubscriptionInfo) activeSubscriptionInfoList.get(0));
    }

    private CharSequence getPhoneNumber(int i) {
        SubscriptionInfo subscriptionInfo = getSubscriptionInfo(i);
        if (subscriptionInfo == null) {
            return this.mContext.getText(R.string.device_info_default);
        }
        return getFormattedPhoneNumber(subscriptionInfo);
    }

    private CharSequence getPreferenceTitle(int i) {
        if (this.mTelephonyManager.getPhoneCount() > 1) {
            return this.mContext.getString(R.string.status_number_sim_slot, Integer.valueOf(i + 1));
        }
        return this.mContext.getString(R.string.status_number);
    }

    SubscriptionInfo getSubscriptionInfo(int i) {
        List<SubscriptionInfo> activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null) {
            return null;
        }
        for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
            if (subscriptionInfo.getSimSlotIndex() == i) {
                return subscriptionInfo;
            }
        }
        return null;
    }

    CharSequence getFormattedPhoneNumber(SubscriptionInfo subscriptionInfo) {
        String formattedPhoneNumber = DeviceInfoUtils.getFormattedPhoneNumber(this.mContext, subscriptionInfo);
        return TextUtils.isEmpty(formattedPhoneNumber) ? this.mContext.getString(R.string.device_info_default) : BidiFormatter.getInstance().unicodeWrap(formattedPhoneNumber, TextDirectionHeuristics.LTR);
    }

    Preference createNewPreference(Context context) {
        return new Preference(context);
    }
}
