package com.android.phone.settings;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.sip.SipManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.UserManager;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.Phone;
import com.android.phone.PhoneUtils;
import com.android.phone.R;
import com.android.phone.SubscriptionInfoHelper;
import com.android.phone.settings.AccountSelectionPreference;
import com.android.services.telephony.sip.SipAccountRegistry;
import com.android.services.telephony.sip.SipPreferences;
import com.android.services.telephony.sip.SipUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class PhoneAccountSettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener, AccountSelectionPreference.AccountSelectionListener {
    private static final String LOG_TAG = PhoneAccountSettingsFragment.class.getSimpleName();
    private PreferenceCategory mAccountList;
    private Preference mAllCallingAccounts;
    private AccountSelectionPreference mDefaultOutgoingAccount;
    private PreferenceCategory mMakeAndReceiveCallsCategory;
    private boolean mMakeAndReceiveCallsCategoryPresent;
    private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.android.phone.settings.PhoneAccountSettingsFragment.1
        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            PhoneAccountSettingsFragment.this.updateAccounts();
        }
    };
    private SipPreferences mSipPreferences;
    private SwitchPreference mSipReceiveCallsPreference;
    private SubscriptionManager mSubscriptionManager;
    private TelecomManager mTelecomManager;
    private TelephonyManager mTelephonyManager;
    private ListPreference mUseSipCalling;

    static /* synthetic */ boolean lambda$getLaunchSmartForwardingMenuIntent$2(PersistableBundle persistableBundle) {
        return persistableBundle != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    @Override // com.android.phone.settings.AccountSelectionPreference.AccountSelectionListener
    public void onAccountChanged(AccountSelectionPreference accountSelectionPreference) {
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTelecomManager = (TelecomManager) getActivity().getSystemService(TelecomManager.class);
        this.mTelephonyManager = TelephonyManager.from(getActivity());
        this.mSubscriptionManager = SubscriptionManager.from(getActivity());
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        if (getPreferenceScreen() != null) {
            getPreferenceScreen().removeAll();
        }
        addPreferencesFromResource(R.xml.phone_account_settings);
        this.mAccountList = (PreferenceCategory) getPreferenceScreen().findPreference("phone_accounts_accounts_list_category_key");
        this.mDefaultOutgoingAccount = (AccountSelectionPreference) getPreferenceScreen().findPreference("default_outgoing_account");
        this.mAllCallingAccounts = getPreferenceScreen().findPreference("phone_accounts_all_calling_accounts");
        this.mMakeAndReceiveCallsCategory = (PreferenceCategory) getPreferenceScreen().findPreference("make_and_receive_calls_settings_category_key");
        this.mMakeAndReceiveCallsCategoryPresent = false;
        updateAccounts();
        updateMakeCallsOptions();
        if (isPrimaryUser() && SipUtil.isVoipSupported(getActivity())) {
            this.mSipPreferences = new SipPreferences(getActivity());
            ListPreference listPreference = (ListPreference) getPreferenceScreen().findPreference("use_sip_calling_options_key");
            this.mUseSipCalling = listPreference;
            listPreference.setEntries(!SipManager.isSipWifiOnly(getActivity()) ? R.array.sip_call_options_wifi_only_entries : R.array.sip_call_options_entries);
            this.mUseSipCalling.setOnPreferenceChangeListener(this);
            int iFindIndexOfValue = this.mUseSipCalling.findIndexOfValue(this.mSipPreferences.getSipCallOption());
            if (iFindIndexOfValue == -1) {
                this.mSipPreferences.setSipCallOption(getResources().getString(R.string.sip_address_only));
                iFindIndexOfValue = this.mUseSipCalling.findIndexOfValue(this.mSipPreferences.getSipCallOption());
            }
            this.mUseSipCalling.setValueIndex(iFindIndexOfValue);
            ListPreference listPreference2 = this.mUseSipCalling;
            listPreference2.setSummary(listPreference2.getEntry());
            SwitchPreference switchPreference = (SwitchPreference) getPreferenceScreen().findPreference("sip_receive_calls_key");
            this.mSipReceiveCallsPreference = switchPreference;
            switchPreference.setEnabled(SipUtil.isPhoneIdle(getActivity()));
            this.mSipReceiveCallsPreference.setChecked(this.mSipPreferences.isReceivingCallsEnabled());
            this.mSipReceiveCallsPreference.setOnPreferenceChangeListener(this);
        } else {
            getPreferenceScreen().removePreference(getPreferenceScreen().findPreference("phone_accounts_sip_settings_category_key"));
        }
        SubscriptionManager.from(getActivity()).addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
    }

    @Override // android.app.Fragment
    public void onPause() {
        SubscriptionManager.from(getActivity()).removeOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
        super.onPause();
    }

    @Override // android.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mUseSipCalling) {
            String string = obj.toString();
            this.mSipPreferences.setSipCallOption(string);
            ListPreference listPreference = this.mUseSipCalling;
            listPreference.setValueIndex(listPreference.findIndexOfValue(string));
            ListPreference listPreference2 = this.mUseSipCalling;
            listPreference2.setSummary(listPreference2.getEntry());
            return true;
        }
        SwitchPreference switchPreference = this.mSipReceiveCallsPreference;
        if (preference != switchPreference) {
            return false;
        }
        final boolean z = !switchPreference.isChecked();
        new Thread(new Runnable() { // from class: com.android.phone.settings.PhoneAccountSettingsFragment.2
            @Override // java.lang.Runnable
            public void run() {
                PhoneAccountSettingsFragment.this.handleSipReceiveCallsOption(z);
            }
        }).start();
        return true;
    }

    @Override // com.android.phone.settings.AccountSelectionPreference.AccountSelectionListener
    public boolean onAccountSelected(AccountSelectionPreference accountSelectionPreference, PhoneAccountHandle phoneAccountHandle) {
        if (accountSelectionPreference != this.mDefaultOutgoingAccount) {
            return false;
        }
        this.mTelecomManager.setUserSelectedOutgoingPhoneAccount(phoneAccountHandle);
        return true;
    }

    @Override // com.android.phone.settings.AccountSelectionPreference.AccountSelectionListener
    public void onAccountSelectionDialogShow(AccountSelectionPreference accountSelectionPreference) {
        if (accountSelectionPreference == this.mDefaultOutgoingAccount) {
            updateDefaultOutgoingAccountsModel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void handleSipReceiveCallsOption(boolean z) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        this.mSipPreferences.setReceivingCallsEnabled(z);
        SipUtil.useSipToReceiveIncomingCalls(activity, z);
        SipAccountRegistry.getInstance().restartSipService(activity);
    }

    private void updateDefaultOutgoingAccountsModel() {
        this.mDefaultOutgoingAccount.setModel(this.mTelecomManager, getCallingAccounts(true, false), this.mTelecomManager.getUserSelectedOutgoingPhoneAccount(), getString(R.string.phone_accounts_ask_every_time));
    }

    private void initAccountList(List<PhoneAccountHandle> list) {
        SubscriptionInfo activeSubscriptionInfo;
        boolean zIsMultiSimEnabled = this.mTelephonyManager.isMultiSimEnabled();
        if (zIsMultiSimEnabled || !getCallingAccounts(false, false).isEmpty()) {
            ArrayList<PhoneAccount> arrayList = new ArrayList();
            Iterator<PhoneAccountHandle> it = list.iterator();
            while (it.hasNext()) {
                PhoneAccount phoneAccount = this.mTelecomManager.getPhoneAccount(it.next());
                if (phoneAccount != null) {
                    arrayList.add(phoneAccount);
                }
            }
            Collections.sort(arrayList, new Comparator<PhoneAccount>() { // from class: com.android.phone.settings.PhoneAccountSettingsFragment.3
                @Override // java.util.Comparator
                public int compare(PhoneAccount phoneAccount2, PhoneAccount phoneAccount3) {
                    boolean zHasCapabilities = phoneAccount2.hasCapabilities(4);
                    int iCompareTo = zHasCapabilities != phoneAccount3.hasCapabilities(4) ? zHasCapabilities ? -1 : 1 : 0;
                    int subIdForPhoneAccount = PhoneAccountSettingsFragment.this.mTelephonyManager.getSubIdForPhoneAccount(phoneAccount2);
                    int subIdForPhoneAccount2 = PhoneAccountSettingsFragment.this.mTelephonyManager.getSubIdForPhoneAccount(phoneAccount3);
                    if (subIdForPhoneAccount != -1 && subIdForPhoneAccount2 != -1) {
                        SubscriptionManager unused = PhoneAccountSettingsFragment.this.mSubscriptionManager;
                        int slotIndex = SubscriptionManager.getSlotIndex(subIdForPhoneAccount);
                        SubscriptionManager unused2 = PhoneAccountSettingsFragment.this.mSubscriptionManager;
                        iCompareTo = slotIndex < SubscriptionManager.getSlotIndex(subIdForPhoneAccount2) ? -1 : 1;
                    }
                    if (iCompareTo == 0) {
                        iCompareTo = phoneAccount2.getAccountHandle().getComponentName().getPackageName().compareTo(phoneAccount3.getAccountHandle().getComponentName().getPackageName());
                    }
                    if (iCompareTo == 0) {
                        iCompareTo = PhoneAccountSettingsFragment.this.nullToEmpty(phoneAccount2.getLabel().toString()).compareTo(PhoneAccountSettingsFragment.this.nullToEmpty(phoneAccount3.getLabel().toString()));
                    }
                    return iCompareTo == 0 ? phoneAccount2.hashCode() - phoneAccount3.hashCode() : iCompareTo;
                }
            });
            int i = 100;
            for (PhoneAccount phoneAccount2 : arrayList) {
                PhoneAccountHandle accountHandle = phoneAccount2.getAccountHandle();
                Intent intentBuildPhoneAccountConfigureIntent = null;
                if (!phoneAccount2.hasCapabilities(4)) {
                    intentBuildPhoneAccountConfigureIntent = buildPhoneAccountConfigureIntent(getActivity(), accountHandle);
                } else if (zIsMultiSimEnabled && (activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mTelephonyManager.getSubIdForPhoneAccount(phoneAccount2))) != null) {
                    intentBuildPhoneAccountConfigureIntent = new Intent("android.telecom.action.SHOW_CALL_SETTINGS");
                    intentBuildPhoneAccountConfigureIntent.setFlags(67108864);
                    SubscriptionInfoHelper.addExtrasToIntent(intentBuildPhoneAccountConfigureIntent, activeSubscriptionInfo);
                }
                Preference preference = new Preference(getActivity());
                CharSequence label = phoneAccount2.getLabel();
                boolean zHasCapabilities = phoneAccount2.hasCapabilities(4);
                if (TextUtils.isEmpty(label) && zHasCapabilities) {
                    label = getString(R.string.phone_accounts_default_account_label);
                }
                preference.setTitle(label);
                Icon icon = phoneAccount2.getIcon();
                if (icon != null) {
                    preference.setIcon(icon.loadDrawable(getActivity()));
                }
                if (intentBuildPhoneAccountConfigureIntent != null) {
                    preference.setIntent(intentBuildPhoneAccountConfigureIntent);
                }
                preference.setOrder(i);
                this.mAccountList.addPreference(preference);
                i++;
            }
        }
    }

    private boolean shouldShowConnectionServiceList(List<PhoneAccountHandle> list) {
        return this.mTelephonyManager.isMultiSimEnabled() || list.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAccounts() {
        PreferenceCategory preferenceCategory = this.mAccountList;
        if (preferenceCategory != null) {
            preferenceCategory.removeAll();
            List<PhoneAccountHandle> callingAccounts = getCallingAccounts(false, true);
            if (shouldShowConnectionServiceList(callingAccounts)) {
                List<PhoneAccountHandle> callingAccounts2 = getCallingAccounts(true, false);
                initAccountList(callingAccounts2);
                if (callingAccounts2.size() > 1) {
                    this.mMakeAndReceiveCallsCategory.addPreference(this.mDefaultOutgoingAccount);
                    this.mMakeAndReceiveCallsCategoryPresent = true;
                    this.mDefaultOutgoingAccount.setListener(this);
                    updateDefaultOutgoingAccountsModel();
                } else {
                    this.mMakeAndReceiveCallsCategory.removePreference(this.mDefaultOutgoingAccount);
                }
                if (!callingAccounts.isEmpty()) {
                    this.mAccountList.addPreference(this.mAllCallingAccounts);
                    return;
                } else {
                    this.mAccountList.removePreference(this.mAllCallingAccounts);
                    return;
                }
            }
            getPreferenceScreen().removePreference(this.mAccountList);
        }
    }

    private List<PhoneAccountHandle> getCallingAccounts(boolean z, boolean z2) {
        PhoneAccountHandle emergencyPhoneAccount = getEmergencyPhoneAccount();
        List<PhoneAccountHandle> callCapablePhoneAccounts = this.mTelecomManager.getCallCapablePhoneAccounts(z2);
        Iterator<PhoneAccountHandle> it = callCapablePhoneAccounts.iterator();
        while (it.hasNext()) {
            PhoneAccountHandle next = it.next();
            if (next.equals(emergencyPhoneAccount)) {
                it.remove();
            } else {
                PhoneAccount phoneAccount = this.mTelecomManager.getPhoneAccount(next);
                if (phoneAccount == null) {
                    it.remove();
                } else if (!z && phoneAccount.hasCapabilities(4)) {
                    it.remove();
                }
            }
        }
        return callCapablePhoneAccounts;
    }

    private PhoneAccountHandle getEmergencyPhoneAccount() {
        return PhoneUtils.makePstnPhoneAccountHandleWithPrefix((Phone) null, "", true);
    }

    public static Intent buildPhoneAccountConfigureIntent(Context context, PhoneAccountHandle phoneAccountHandle) {
        Intent intentBuildConfigureIntent = buildConfigureIntent(context, phoneAccountHandle, "android.telecom.action.CONFIGURE_PHONE_ACCOUNT");
        if (intentBuildConfigureIntent == null && (intentBuildConfigureIntent = buildConfigureIntent(context, phoneAccountHandle, "android.telecom.action.CONNECTION_SERVICE_CONFIGURE")) != null) {
            Log.w(LOG_TAG, "Phone account using old configuration intent: " + phoneAccountHandle);
        }
        return intentBuildConfigureIntent;
    }

    private static Intent buildConfigureIntent(Context context, PhoneAccountHandle phoneAccountHandle, String str) {
        if (phoneAccountHandle == null || phoneAccountHandle.getComponentName() == null || TextUtils.isEmpty(phoneAccountHandle.getComponentName().getPackageName())) {
            return null;
        }
        Intent intent = new Intent(str);
        intent.setPackage(phoneAccountHandle.getComponentName().getPackageName());
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        if (context.getPackageManager().queryIntentActivities(intent, 0).size() == 0) {
            return null;
        }
        return intent;
    }

    private boolean isPrimaryUser() {
        return ((UserManager) getActivity().getSystemService("user")).isPrimaryUser();
    }

    private void updateMakeCallsOptions() {
        if (this.mMakeAndReceiveCallsCategory == null) {
            return;
        }
        Intent launchSmartForwardingMenuIntent = getLaunchSmartForwardingMenuIntent();
        if (launchSmartForwardingMenuIntent != null) {
            this.mMakeAndReceiveCallsCategory.findPreference("smart_forwarding_configuration_key").setIntent(launchSmartForwardingMenuIntent);
            this.mMakeAndReceiveCallsCategoryPresent = true;
        } else {
            this.mMakeAndReceiveCallsCategory.removePreference(getPreferenceScreen().findPreference("smart_forwarding_configuration_key"));
        }
        if (this.mMakeAndReceiveCallsCategoryPresent) {
            return;
        }
        getPreferenceScreen().removePreference(this.mMakeAndReceiveCallsCategory);
    }

    private Intent getLaunchSmartForwardingMenuIntent() {
        final CarrierConfigManager carrierConfigManager;
        List<SubscriptionInfo> activeSubscriptionInfoList;
        if (this.mTelephonyManager.getPhoneCount() <= 1 || (carrierConfigManager = (CarrierConfigManager) getActivity().getSystemService("carrier_config")) == null || (activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList()) == null) {
            return null;
        }
        List list = (List) activeSubscriptionInfoList.stream().filter(new Predicate() { // from class: com.android.phone.settings.-$$Lambda$PhoneAccountSettingsFragment$0CwwbqNxh-91KEZQVfSIA_WajSE
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PhoneAccountSettingsFragment.lambda$getLaunchSmartForwardingMenuIntent$0((SubscriptionInfo) obj);
            }
        }).collect(Collectors.toList());
        if (list.size() < 2) {
            return null;
        }
        String str = null;
        for (String str2 : (List) list.stream().map(new Function() { // from class: com.android.phone.settings.-$$Lambda$PhoneAccountSettingsFragment$1wstnY7W8jIM5ZG_8hKTg0K2rr8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return carrierConfigManager.getConfigForSubId(((SubscriptionInfo) obj).getSubscriptionId());
            }
        }).filter(new Predicate() { // from class: com.android.phone.settings.-$$Lambda$PhoneAccountSettingsFragment$XItpYu2DpSMZo-WUR3FcW8zxBpk
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PhoneAccountSettingsFragment.lambda$getLaunchSmartForwardingMenuIntent$2((PersistableBundle) obj);
            }
        }).map(new Function() { // from class: com.android.phone.settings.-$$Lambda$PhoneAccountSettingsFragment$PWs3tBfpiA-nAvjQAz0AjHrDL80
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((PersistableBundle) obj).getString("smart_forwarding_config_component_name_string");
            }
        }).filter(new Predicate() { // from class: com.android.phone.settings.-$$Lambda$PhoneAccountSettingsFragment$3fabbZCvnE3yseZey6zRzNLEiSo
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PhoneAccountSettingsFragment.lambda$getLaunchSmartForwardingMenuIntent$4((String) obj);
            }
        }).collect(Collectors.toList())) {
            if (str == null) {
                str = str2;
            } else if (!str.equals(str2)) {
                Log.w(LOG_TAG, "ignore smart forward component: " + str2);
            }
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(ComponentName.unflattenFromString(str));
        if (getActivity().getPackageManager().queryIntentActivities(intent, 0).size() == 0) {
            return null;
        }
        return intent;
    }

    static /* synthetic */ boolean lambda$getLaunchSmartForwardingMenuIntent$0(SubscriptionInfo subscriptionInfo) {
        return !subscriptionInfo.isOpportunistic();
    }

    static /* synthetic */ boolean lambda$getLaunchSmartForwardingMenuIntent$4(String str) {
        return !TextUtils.isEmpty(str);
    }
}
