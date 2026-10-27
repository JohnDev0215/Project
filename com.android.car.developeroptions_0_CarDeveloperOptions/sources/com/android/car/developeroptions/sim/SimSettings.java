package com.android.car.developeroptions.sim;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.sysprop.TelephonyProperties;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.RestrictedSettingsFragment;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SimSettings extends RestrictedSettingsFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.sim.SimSettings.3
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            if (Utils.showSimCardTile(context)) {
                SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
                searchIndexableResource.xmlResId = R.xml.sim_settings;
                arrayList.add(searchIndexableResource);
            }
            return arrayList;
        }
    };
    private List<SubscriptionInfo> mAvailableSubInfos;
    private int[] mCallState;
    private Context mContext;
    private int mNumSlots;
    private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangeListener;
    private int mPhoneCount;
    private PhoneStateListener[] mPhoneStateListener;
    private List<SubscriptionInfo> mSelectableSubInfos;
    private PreferenceScreen mSimCards;
    private SubscriptionManager mSubscriptionManager;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 88;
    }

    public SimSettings() {
        super("no_config_sim");
        this.mAvailableSubInfos = null;
        this.mSelectableSubInfos = null;
        this.mSimCards = null;
        int phoneCount = TelephonyManager.getDefault().getPhoneCount();
        this.mPhoneCount = phoneCount;
        this.mCallState = new int[phoneCount];
        this.mPhoneStateListener = new PhoneStateListener[phoneCount];
        this.mOnSubscriptionsChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.android.car.developeroptions.sim.SimSettings.1
            @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
            public void onSubscriptionsChanged() {
                SimSettings.this.updateSubscriptions();
            }
        };
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        this.mSubscriptionManager = SubscriptionManager.from(getActivity());
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService("phone");
        addPreferencesFromResource(R.xml.sim_settings);
        this.mNumSlots = telephonyManager.getSimCount();
        this.mSimCards = (PreferenceScreen) findPreference("sim_cards");
        this.mAvailableSubInfos = new ArrayList(this.mNumSlots);
        this.mSelectableSubInfos = new ArrayList();
        SimSelectNotification.cancelNotification(getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSubscriptions() {
        this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        for (int i = 0; i < this.mNumSlots; i++) {
            Preference preferenceFindPreference = this.mSimCards.findPreference("sim" + i);
            if (preferenceFindPreference instanceof SimPreference) {
                this.mSimCards.removePreference(preferenceFindPreference);
            }
        }
        this.mAvailableSubInfos.clear();
        this.mSelectableSubInfos.clear();
        for (int i2 = 0; i2 < this.mNumSlots; i2++) {
            SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = this.mSubscriptionManager.getActiveSubscriptionInfoForSimSlotIndex(i2);
            SimPreference simPreference = new SimPreference(getPrefContext(), activeSubscriptionInfoForSimSlotIndex, i2);
            simPreference.setOrder(i2 - this.mNumSlots);
            this.mSimCards.addPreference(simPreference);
            this.mAvailableSubInfos.add(activeSubscriptionInfoForSimSlotIndex);
            if (activeSubscriptionInfoForSimSlotIndex != null) {
                this.mSelectableSubInfos.add(activeSubscriptionInfoForSimSlotIndex);
            }
        }
        updateAllOptions();
    }

    private void updateAllOptions() {
        updateSimSlotValues();
        updateActivitesCategory();
    }

    private void updateSimSlotValues() {
        int preferenceCount = this.mSimCards.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = this.mSimCards.getPreference(i);
            if (preference instanceof SimPreference) {
                ((SimPreference) preference).update();
            }
        }
    }

    private void updateActivitesCategory() {
        updateCellularDataValues();
        updateCallValues();
        updateSmsValues();
    }

    private void updateSmsValues() {
        Preference preferenceFindPreference = findPreference("sim_sms");
        SubscriptionInfo defaultSmsSubscriptionInfo = this.mSubscriptionManager.getDefaultSmsSubscriptionInfo();
        preferenceFindPreference.setTitle(R.string.sms_messages_title);
        if (defaultSmsSubscriptionInfo != null) {
            preferenceFindPreference.setSummary(defaultSmsSubscriptionInfo.getDisplayName());
            preferenceFindPreference.setEnabled(this.mSelectableSubInfos.size() > 1);
        } else if (defaultSmsSubscriptionInfo == null) {
            preferenceFindPreference.setSummary(R.string.sim_selection_required_pref);
            preferenceFindPreference.setEnabled(this.mSelectableSubInfos.size() >= 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCellularDataValues() {
        Preference preferenceFindPreference = findPreference("sim_cellular_data");
        SubscriptionInfo defaultDataSubscriptionInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
        preferenceFindPreference.setTitle(R.string.cellular_data_title);
        boolean zIsCallStateIdle = isCallStateIdle();
        boolean zBooleanValue = ((Boolean) TelephonyProperties.in_ecm_mode().orElse(Boolean.FALSE)).booleanValue();
        boolean z = false;
        if (defaultDataSubscriptionInfo != null) {
            preferenceFindPreference.setSummary(defaultDataSubscriptionInfo.getDisplayName());
            if (this.mSelectableSubInfos.size() > 1 && zIsCallStateIdle && !zBooleanValue) {
                z = true;
            }
            preferenceFindPreference.setEnabled(z);
            return;
        }
        if (defaultDataSubscriptionInfo == null) {
            preferenceFindPreference.setSummary(R.string.sim_selection_required_pref);
            if (this.mSelectableSubInfos.size() >= 1 && zIsCallStateIdle && !zBooleanValue) {
                z = true;
            }
            preferenceFindPreference.setEnabled(z);
        }
    }

    private void updateCallValues() {
        String string;
        Preference preferenceFindPreference = findPreference("sim_calls");
        TelecomManager telecomManagerFrom = TelecomManager.from(this.mContext);
        PhoneAccountHandle userSelectedOutgoingPhoneAccount = telecomManagerFrom.getUserSelectedOutgoingPhoneAccount();
        List<PhoneAccountHandle> callCapablePhoneAccounts = telecomManagerFrom.getCallCapablePhoneAccounts();
        preferenceFindPreference.setTitle(R.string.calls_title);
        if (userSelectedOutgoingPhoneAccount == null) {
            string = this.mContext.getResources().getString(R.string.sim_calls_ask_first_prefs_title);
        } else {
            string = (String) telecomManagerFrom.getPhoneAccount(userSelectedOutgoingPhoneAccount).getLabel();
        }
        preferenceFindPreference.setSummary(string);
        preferenceFindPreference.setEnabled(callCapablePhoneAccounts.size() > 1);
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
        updateSubscriptions();
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService("phone");
        if (this.mSelectableSubInfos.size() > 1) {
            Log.d("SimSettings", "Register for call state change");
            for (int i = 0; i < this.mPhoneCount; i++) {
                telephonyManager.createForSubscriptionId(this.mSelectableSubInfos.get(i).getSubscriptionId()).listen(getPhoneStateListener(i), 32);
            }
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mSubscriptionManager.removeOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        for (int i = 0; i < this.mPhoneCount; i++) {
            PhoneStateListener[] phoneStateListenerArr = this.mPhoneStateListener;
            if (phoneStateListenerArr[i] != null) {
                telephonyManager.listen(phoneStateListenerArr[i], 0);
                this.mPhoneStateListener[i] = null;
            }
        }
    }

    private PhoneStateListener getPhoneStateListener(final int i) {
        this.mPhoneStateListener[i] = new PhoneStateListener() { // from class: com.android.car.developeroptions.sim.SimSettings.2
            @Override // android.telephony.PhoneStateListener
            public void onCallStateChanged(int i2, String str) {
                SimSettings.this.mCallState[i] = i2;
                SimSettings.this.updateCellularDataValues();
            }
        };
        return this.mPhoneStateListener[i];
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        Context context = this.mContext;
        Intent intent = new Intent(context, (Class<?>) SimDialogActivity.class);
        intent.addFlags(268435456);
        if (preference instanceof SimPreference) {
            Intent intent2 = new Intent(context, (Class<?>) SimPreferenceDialog.class);
            intent2.putExtra("slot_id", ((SimPreference) preference).getSlotId());
            startActivity(intent2);
        } else if (findPreference("sim_cellular_data") == preference) {
            intent.putExtra(SimDialogActivity.DIALOG_TYPE_KEY, 0);
            context.startActivity(intent);
        } else if (findPreference("sim_calls") == preference) {
            intent.putExtra(SimDialogActivity.DIALOG_TYPE_KEY, 1);
            context.startActivity(intent);
        } else if (findPreference("sim_sms") == preference) {
            intent.putExtra(SimDialogActivity.DIALOG_TYPE_KEY, 2);
            context.startActivity(intent);
        }
        return true;
    }

    private class SimPreference extends Preference {
        Context mContext;
        private int mSlotId;
        private SubscriptionInfo mSubInfoRecord;

        public SimPreference(Context context, SubscriptionInfo subscriptionInfo, int i) {
            super(context);
            this.mContext = context;
            this.mSubInfoRecord = subscriptionInfo;
            this.mSlotId = i;
            setKey("sim" + this.mSlotId);
            update();
        }

        public void update() {
            Resources resources = this.mContext.getResources();
            setTitle(String.format(this.mContext.getResources().getString(R.string.sim_editor_title), Integer.valueOf(this.mSlotId + 1)));
            SubscriptionInfo subscriptionInfo = this.mSubInfoRecord;
            if (subscriptionInfo != null) {
                if (TextUtils.isEmpty(SimSettings.this.getPhoneNumber(subscriptionInfo))) {
                    setSummary(this.mSubInfoRecord.getDisplayName());
                } else {
                    setSummary(((Object) this.mSubInfoRecord.getDisplayName()) + " - " + ((Object) PhoneNumberUtils.createTtsSpannable(SimSettings.this.getPhoneNumber(this.mSubInfoRecord))));
                    setEnabled(true);
                }
                setIcon(new BitmapDrawable(resources, this.mSubInfoRecord.createIconBitmap(this.mContext)));
                return;
            }
            setSummary(R.string.sim_slot_empty);
            setFragment(null);
            setEnabled(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getSlotId() {
            return this.mSlotId;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPhoneNumber(SubscriptionInfo subscriptionInfo) {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).getLine1Number(subscriptionInfo.getSubscriptionId());
    }

    private boolean isCallStateIdle() {
        boolean z = true;
        int i = 0;
        while (true) {
            int[] iArr = this.mCallState;
            if (i < iArr.length) {
                if (iArr[i] != 0) {
                    z = false;
                }
                i++;
            } else {
                Log.d("SimSettings", "isCallStateIdle " + z);
                return z;
            }
        }
    }
}
