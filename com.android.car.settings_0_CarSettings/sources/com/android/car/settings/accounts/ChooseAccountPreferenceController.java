package com.android.car.settings.accounts;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import androidx.collection.ArrayMap;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.accounts.AccountTypesHelper;
import com.android.car.settings.accounts.ChooseAccountPreferenceController;
import com.android.car.settings.common.ActivityResultCallback;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;
import com.android.settingslib.accounts.AuthenticatorHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: classes5.dex */
public class ChooseAccountPreferenceController extends PreferenceController<PreferenceGroup> implements ActivityResultCallback {
    static final int ADD_ACCOUNT_REQUEST_CODE = 100;
    private AccountTypesHelper mAccountTypesHelper;
    private boolean mHasPendingBack;
    private boolean mIsStarted;
    private ArrayMap<String, AuthenticatorDescriptionPreference> mPreferences;

    interface AddAccountListener {
        void addAccount(String str);
    }

    public ChooseAccountPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPreferences = new ArrayMap<>();
        this.mIsStarted = false;
        this.mHasPendingBack = false;
        AccountTypesHelper accountTypesHelper = new AccountTypesHelper(context);
        this.mAccountTypesHelper = accountTypesHelper;
        accountTypesHelper.setOnChangeListener(new AccountTypesHelper.OnChangeListener() { // from class: com.android.car.settings.accounts.-$$Lambda$ChooseAccountPreferenceController$Ib5pmGhLrApQ5xK0a2u7w5VJPlM
            @Override // com.android.car.settings.accounts.AccountTypesHelper.OnChangeListener
            public final void onAuthorizedAccountTypesChanged() {
                this.f$0.forceUpdateAccountsCategory();
            }
        });
    }

    public void setAuthorities(List<String> authorities) {
        this.mAccountTypesHelper.setAuthorities(authorities);
    }

    public void setAccountTypesFilter(Set<String> accountTypesFilter) {
        this.mAccountTypesHelper.setAccountTypesFilter(accountTypesFilter);
    }

    protected void setAccountTypesExclusionFilter(Set<String> accountTypesExclusionFilterFilter) {
        this.mAccountTypesHelper.setAccountTypesExclusionFilter(accountTypesExclusionFilterFilter);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        this.mAccountTypesHelper.forceUpdate();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mIsStarted = true;
        this.mAccountTypesHelper.listenToAccountUpdates();
        if (this.mHasPendingBack) {
            this.mHasPendingBack = false;
            new Handler().post(new Runnable() { // from class: com.android.car.settings.accounts.-$$Lambda$ChooseAccountPreferenceController$4cqigRyqMAgxbsyFPXvc-JpY5q8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onStartInternal$0$ChooseAccountPreferenceController();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onStartInternal$0$ChooseAccountPreferenceController() {
        getFragmentController().goBack();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mAccountTypesHelper.stopListeningToAccountUpdates();
        this.mIsStarted = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceUpdateAccountsCategory() {
        Set<String> preferencesToRemove = new HashSet<>(this.mPreferences.keySet());
        List<AuthenticatorDescriptionPreference> preferences = getAuthenticatorDescriptionPreferences(preferencesToRemove);
        for (int i = 0; i < preferences.size(); i++) {
            AuthenticatorDescriptionPreference preference = preferences.get(i);
            preference.setOrder(i);
            String key = preference.getKey();
            getPreference().addPreference(preference);
            this.mPreferences.put(key, preference);
        }
        for (String key2 : preferencesToRemove) {
            getPreference().removePreference(this.mPreferences.get(key2));
            this.mPreferences.remove(key2);
        }
    }

    private List<AuthenticatorDescriptionPreference> getAuthenticatorDescriptionPreferences(Set<String> preferencesToRemove) {
        ArrayList<AuthenticatorDescriptionPreference> authenticatorDescriptionPreferences = new ArrayList<>();
        Set<String> authorizedAccountTypes = this.mAccountTypesHelper.getAuthorizedAccountTypes();
        for (String accountType : authorizedAccountTypes) {
            CharSequence label = this.mAccountTypesHelper.getLabelForType(accountType);
            Drawable icon = this.mAccountTypesHelper.getDrawableForType(accountType);
            final AuthenticatorDescriptionPreference preference = this.mPreferences.getOrDefault(accountType, new AuthenticatorDescriptionPreference(getContext(), accountType, label, icon));
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.accounts.-$$Lambda$ChooseAccountPreferenceController$oC03tNQ1mHQaM9xTpvzNann1BRo
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return this.f$0.lambda$getAuthenticatorDescriptionPreferences$1$ChooseAccountPreferenceController(preference, preference2);
                }
            });
            authenticatorDescriptionPreferences.add(preference);
            preferencesToRemove.remove(accountType);
        }
        Collections.sort(authenticatorDescriptionPreferences, Comparator.comparing(new Function() { // from class: com.android.car.settings.accounts.-$$Lambda$ChooseAccountPreferenceController$9sOlVUN3TJ5KjYsuoyLWUQwcUkg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ChooseAccountPreferenceController.AuthenticatorDescriptionPreference) obj).getTitle().toString();
            }
        }));
        return authenticatorDescriptionPreferences;
    }

    public /* synthetic */ boolean lambda$getAuthenticatorDescriptionPreferences$1$ChooseAccountPreferenceController(AuthenticatorDescriptionPreference preference, Preference pref) {
        Intent intent = AddAccountActivity.createAddAccountActivityIntent(getContext(), preference.getAccountType());
        getFragmentController().startActivityForResult(intent, 100, this);
        return true;
    }

    AuthenticatorHelper getAuthenticatorHelper() {
        return this.mAccountTypesHelper.getAuthenticatorHelper();
    }

    @Override // com.android.car.settings.common.ActivityResultCallback
    public void processActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (this.mIsStarted) {
                getFragmentController().goBack();
            } else {
                this.mHasPendingBack = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class AuthenticatorDescriptionPreference extends CarUiPreference {
        private final String mType;

        AuthenticatorDescriptionPreference(Context context, String accountType, CharSequence label, Drawable icon) {
            super(context);
            this.mType = accountType;
            setKey(accountType);
            setTitle(label);
            setIcon(icon);
            setShowChevron(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getAccountType() {
            return this.mType;
        }
    }
}
