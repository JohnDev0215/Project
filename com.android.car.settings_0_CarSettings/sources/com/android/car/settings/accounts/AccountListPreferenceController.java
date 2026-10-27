package com.android.car.settings.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import androidx.collection.ArrayMap;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.car.settings.R;
import com.android.car.settings.accounts.AccountListPreferenceController;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.users.UserHelper;
import com.android.car.ui.preference.CarUiPreference;
import com.android.settingslib.accounts.AuthenticatorHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: classes5.dex */
public class AccountListPreferenceController extends PreferenceController<PreferenceCategory> implements AuthenticatorHelper.OnAccountsUpdateListener {
    private static final String NO_ACCOUNT_PREF_KEY = "no_accounts_added";
    private AuthenticatorHelper mAuthenticatorHelper;
    private String[] mAuthorities;
    private boolean mListenerRegistered;
    private final ArrayMap<String, Preference> mPreferences;
    private final UserInfo mUserInfo;
    private final BroadcastReceiver mUserUpdateReceiver;

    public AccountListPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPreferences = new ArrayMap<>();
        this.mListenerRegistered = false;
        this.mUserUpdateReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.accounts.AccountListPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                AccountListPreferenceController.this.onUsersUpdate();
            }
        };
        this.mUserInfo = UserHelper.getInstance(context).getCurrentProcessUserInfo();
        this.mAuthenticatorHelper = new AuthenticatorHelper(context, this.mUserInfo.getUserHandle(), this);
    }

    public void setAuthorities(String[] authorities) {
        this.mAuthorities = authorities;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceCategory> getPreferenceType() {
        return PreferenceCategory.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceCategory preference) {
        forceUpdateAccountsCategory();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        boolean canModifyAccounts = UserHelper.getInstance(getContext()).canCurrentProcessModifyAccounts();
        return canModifyAccounts ? 0 : 3;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mAuthenticatorHelper.listenToAccountUpdates();
        registerForUserEvents();
        this.mListenerRegistered = true;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mAuthenticatorHelper.stopListeningToAccountUpdates();
        unregisterForUserEvents();
        this.mListenerRegistered = false;
    }

    @Override // com.android.settingslib.accounts.AuthenticatorHelper.OnAccountsUpdateListener
    public void onAccountsUpdate(UserHandle userHandle) {
        if (userHandle.equals(this.mUserInfo.getUserHandle())) {
            forceUpdateAccountsCategory();
        }
    }

    void onUsersUpdate() {
        forceUpdateAccountsCategory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onAccountPreferenceClicked, reason: merged with bridge method [inline-methods] */
    public boolean lambda$getAccountPreferences$0$AccountListPreferenceController(AccountPreference preference) {
        getFragmentController().launchFragment(AccountDetailsFragment.newInstance(preference.getAccount(), preference.getLabel(), this.mUserInfo));
        return true;
    }

    private void forceUpdateAccountsCategory() {
        getPreference().setTitle(getContext().getString(R.string.account_list_title, this.mUserInfo.name));
        this.mAuthenticatorHelper.stopListeningToAccountUpdates();
        AuthenticatorHelper authenticatorHelper = new AuthenticatorHelper(getContext(), this.mUserInfo.getUserHandle(), this);
        this.mAuthenticatorHelper = authenticatorHelper;
        if (this.mListenerRegistered) {
            authenticatorHelper.listenToAccountUpdates();
        }
        Set<String> preferencesToRemove = new HashSet<>(this.mPreferences.keySet());
        List<? extends Preference> preferences = getAccountPreferences(preferencesToRemove);
        for (int i = 0; i < preferences.size(); i++) {
            Preference pref = preferences.get(i);
            pref.setOrder(i);
            this.mPreferences.put(pref.getKey(), pref);
            getPreference().addPreference(pref);
        }
        for (String key : preferencesToRemove) {
            getPreference().removePreference(this.mPreferences.get(key));
            this.mPreferences.remove(key);
        }
    }

    private List<? extends Preference> getAccountPreferences(Set<String> preferencesToRemove) {
        String[] accountTypes;
        String[] accountTypes2 = this.mAuthenticatorHelper.getEnabledAccountTypes();
        ArrayList<AccountPreference> accountPreferences = new ArrayList<>(accountTypes2.length);
        int i = 0;
        while (true) {
            int i2 = 0;
            if (i >= accountTypes2.length) {
                break;
            }
            String accountType = accountTypes2[i];
            if (!accountTypeHasAnyRequestedAuthorities(accountType)) {
                accountTypes = accountTypes2;
            } else {
                CharSequence label = this.mAuthenticatorHelper.getLabelForType(getContext(), accountType);
                if (label == null) {
                    accountTypes = accountTypes2;
                } else {
                    Account[] accounts = AccountManager.get(getContext()).getAccountsByTypeAsUser(accountType, this.mUserInfo.getUserHandle());
                    Drawable icon = this.mAuthenticatorHelper.getDrawableForType(getContext(), accountType);
                    for (int length = accounts.length; i2 < length; length = length) {
                        Account account = accounts[i2];
                        String key = AccountPreference.buildKey(account);
                        AccountPreference preference = (AccountPreference) this.mPreferences.getOrDefault(key, new AccountPreference(getContext(), account, label, icon));
                        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountListPreferenceController$gPqN7wTvFv08Z4i7ReRD0lG1ozY
                            @Override // androidx.preference.Preference.OnPreferenceClickListener
                            public final boolean onPreferenceClick(Preference preference2) {
                                return this.f$0.lambda$getAccountPreferences$0$AccountListPreferenceController(preference2);
                            }
                        });
                        accountPreferences.add(preference);
                        preferencesToRemove.remove(key);
                        i2++;
                        accountTypes2 = accountTypes2;
                    }
                    accountTypes = accountTypes2;
                    this.mAuthenticatorHelper.preloadDrawableForType(getContext(), accountType);
                }
            }
            i++;
            accountTypes2 = accountTypes;
        }
        if (accountPreferences.isEmpty()) {
            preferencesToRemove.remove(NO_ACCOUNT_PREF_KEY);
            return Arrays.asList(this.mPreferences.getOrDefault(NO_ACCOUNT_PREF_KEY, createNoAccountsAddedPreference()));
        }
        Collections.sort(accountPreferences, Comparator.comparing(new Function() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountListPreferenceController$hnw5ldarGvAMnpMoQKJ-jaa1_S8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AccountListPreferenceController.AccountPreference) obj).getSummary().toString();
            }
        }).thenComparing(new Function() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountListPreferenceController$f8OSEHF5HlHAJzviHixWrIIJZmM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AccountListPreferenceController.AccountPreference) obj).getTitle().toString();
            }
        }));
        return accountPreferences;
    }

    private Preference createNoAccountsAddedPreference() {
        CarUiPreference emptyPreference = new CarUiPreference(getContext());
        emptyPreference.setTitle(R.string.no_accounts_added);
        emptyPreference.setKey(NO_ACCOUNT_PREF_KEY);
        emptyPreference.setSelectable(false);
        return emptyPreference;
    }

    private void registerForUserEvents() {
        IntentFilter filter = new IntentFilter("android.intent.action.USER_INFO_CHANGED");
        getContext().registerReceiver(this.mUserUpdateReceiver, filter);
    }

    private void unregisterForUserEvents() {
        getContext().unregisterReceiver(this.mUserUpdateReceiver);
    }

    private boolean accountTypeHasAnyRequestedAuthorities(String accountType) {
        String[] strArr = this.mAuthorities;
        if (strArr == null || strArr.length == 0) {
            return true;
        }
        ArrayList<String> authoritiesForType = this.mAuthenticatorHelper.getAuthoritiesForAccountType(accountType);
        if (authoritiesForType == null) {
            return false;
        }
        int j = 0;
        while (true) {
            String[] strArr2 = this.mAuthorities;
            if (j >= strArr2.length) {
                return false;
            }
            if (authoritiesForType.contains(strArr2[j])) {
                return true;
            }
            j++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class AccountPreference extends CarUiPreference {
        private final Account mAccount;
        private final CharSequence mLabel;

        private AccountPreference(Context context, Account account, CharSequence label, Drawable icon) {
            super(context);
            this.mAccount = account;
            this.mLabel = label;
            setKey(buildKey(account));
            setTitle(account.name);
            setSummary(label);
            setIcon(icon);
            setShowChevron(false);
        }

        public static String buildKey(Account account) {
            return String.valueOf(account.hashCode());
        }

        public Account getAccount() {
            return this.mAccount;
        }

        public CharSequence getLabel() {
            return this.mLabel;
        }
    }
}
