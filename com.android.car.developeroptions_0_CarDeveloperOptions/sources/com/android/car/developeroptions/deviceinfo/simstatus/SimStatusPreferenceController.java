package com.android.car.developeroptions.deviceinfo.simstatus;

import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.deviceinfo.AbstractSimStatusImeiInfoPreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SimStatusPreferenceController extends AbstractSimStatusImeiInfoPreferenceController implements PreferenceControllerMixin {
    private final Fragment mFragment;
    private final List<Preference> mPreferenceList;
    private final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "sim_status";
    }

    public SimStatusPreferenceController(Context context, Fragment fragment) {
        super(context);
        this.mPreferenceList = new ArrayList();
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
        this.mFragment = fragment;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (isAvailable() && preferenceFindPreference != null && preferenceFindPreference.isVisible()) {
            this.mPreferenceList.add(preferenceFindPreference);
            int order = preferenceFindPreference.getOrder();
            for (int i = 1; i < this.mTelephonyManager.getPhoneCount(); i++) {
                Preference preferenceCreateNewPreference = createNewPreference(preferenceScreen.getContext());
                preferenceCreateNewPreference.setOrder(order + i);
                preferenceCreateNewPreference.setKey("sim_status" + i);
                preferenceScreen.addPreference(preferenceCreateNewPreference);
                this.mPreferenceList.add(preferenceCreateNewPreference);
            }
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        for (int i = 0; i < this.mPreferenceList.size(); i++) {
            Preference preference2 = this.mPreferenceList.get(i);
            preference2.setTitle(getPreferenceTitle(i));
            preference2.setSummary(getCarrierName(i));
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        int iIndexOf = this.mPreferenceList.indexOf(preference);
        if (iIndexOf == -1) {
            return false;
        }
        SimStatusDialogFragment.show(this.mFragment, iIndexOf, getPreferenceTitle(iIndexOf));
        return true;
    }

    private String getPreferenceTitle(int i) {
        if (this.mTelephonyManager.getPhoneCount() > 1) {
            return this.mContext.getString(R.string.sim_status_title_sim_slot, Integer.valueOf(i + 1));
        }
        return this.mContext.getString(R.string.sim_status_title);
    }

    private CharSequence getCarrierName(int i) {
        List<SubscriptionInfo> activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList != null) {
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getSimSlotIndex() == i) {
                    return subscriptionInfo.getCarrierName();
                }
            }
        }
        return this.mContext.getText(R.string.device_info_not_available);
    }

    Preference createNewPreference(Context context) {
        return new Preference(context);
    }
}
