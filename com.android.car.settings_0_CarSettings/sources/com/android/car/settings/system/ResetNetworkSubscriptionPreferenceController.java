package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ResetNetworkSubscriptionPreferenceController extends PreferenceController<ListPreference> {
    private final SubscriptionManager mSubscriptionManager;

    public ResetNetworkSubscriptionPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ListPreference> getPreferenceType() {
        return ListPreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    public int getAvailabilityStatus() {
        return getContext().getPackageManager().hasSystemFeature("android.hardware.telephony") ? 0 : 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(ListPreference preference) {
        List<SubscriptionInfo> subscriptions = this.mSubscriptionManager.getActiveSubscriptionInfoList();
        if (subscriptions == null || subscriptions.isEmpty()) {
            preference.setValue(String.valueOf(-1));
            preference.setVisible(false);
            return;
        }
        if (subscriptions.size() == 1) {
            preference.setValue(String.valueOf(subscriptions.get(0).getSubscriptionId()));
            preference.setVisible(false);
            return;
        }
        int defaultSubscriptionId = getDefaultSubscriptionId();
        int selectedIndex = 0;
        int size = subscriptions.size();
        List<String> subscriptionNames = new ArrayList<>(size);
        List<String> subscriptionIds = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            SubscriptionInfo subscription = subscriptions.get(i);
            int subscriptionId = subscription.getSubscriptionId();
            if (subscriptionId == defaultSubscriptionId) {
                selectedIndex = i;
            }
            subscriptionNames.add(getSubscriptionName(subscription));
            subscriptionIds.add(String.valueOf(subscriptionId));
        }
        preference.setEntries(toCharSequenceArray(subscriptionNames));
        preference.setEntryValues(toCharSequenceArray(subscriptionIds));
        preference.setTitle(subscriptionNames.get(selectedIndex));
        preference.setValueIndex(selectedIndex);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(ListPreference preference, Object newValue) {
        String subscriptionIdStr = (String) newValue;
        int index = preference.findIndexOfValue(subscriptionIdStr);
        CharSequence subscriptionName = preference.getEntries()[index];
        preference.setTitle(subscriptionName);
        return true;
    }

    private int getDefaultSubscriptionId() {
        int defaultSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        if (!SubscriptionManager.isUsableSubIdValue(defaultSubscriptionId)) {
            defaultSubscriptionId = SubscriptionManager.getDefaultVoiceSubscriptionId();
        }
        if (!SubscriptionManager.isUsableSubIdValue(defaultSubscriptionId)) {
            defaultSubscriptionId = SubscriptionManager.getDefaultSmsSubscriptionId();
        }
        if (!SubscriptionManager.isUsableSubIdValue(defaultSubscriptionId)) {
            return SubscriptionManager.getDefaultSubscriptionId();
        }
        return defaultSubscriptionId;
    }

    private String getSubscriptionName(SubscriptionInfo subscription) {
        String name = subscription.getDisplayName().toString();
        if (TextUtils.isEmpty(name)) {
            name = subscription.getNumber();
        }
        if (TextUtils.isEmpty(name)) {
            name = subscription.getCarrierName().toString();
        }
        if (TextUtils.isEmpty(name)) {
            return getContext().getString(R.string.reset_network_fallback_subscription_name, Integer.valueOf(subscription.getMcc()), Integer.valueOf(subscription.getMnc()), Integer.valueOf(subscription.getSimSlotIndex()), Integer.valueOf(subscription.getSubscriptionId()));
        }
        return name;
    }

    private CharSequence[] toCharSequenceArray(List<String> list) {
        CharSequence[] array = new CharSequence[list.size()];
        list.toArray(array);
        return array;
    }
}
