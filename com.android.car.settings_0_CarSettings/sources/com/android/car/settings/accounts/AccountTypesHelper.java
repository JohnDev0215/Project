package com.android.car.settings.accounts;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import com.android.settingslib.accounts.AuthenticatorHelper;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class AccountTypesHelper {
    private Set<String> mAccountTypesExclusionFilter;
    private Set<String> mAccountTypesFilter;
    private AuthenticatorHelper mAuthenticatorHelper;
    private List<String> mAuthorities;
    private Set<String> mAuthorizedAccountTypes;
    private final Context mContext;
    private OnChangeListener mListener;
    private UserHandle mUserHandle;

    public interface OnChangeListener {
        void onAuthorizedAccountTypesChanged();
    }

    public AccountTypesHelper(Context context) {
        this.mContext = context;
        HashSet hashSet = new HashSet();
        this.mAccountTypesExclusionFilter = hashSet;
        hashSet.add("com.android.bluetooth.pbapsink");
        setAccountTypesExclusionFilter(this.mAccountTypesExclusionFilter);
        this.mUserHandle = UserHandle.of(UserHandle.myUserId());
        this.mAuthenticatorHelper = new AuthenticatorHelper(this.mContext, this.mUserHandle, new AuthenticatorHelper.OnAccountsUpdateListener() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountTypesHelper$llL6WBn63gHJA9K4JMKE_nb1YiU
            @Override // com.android.settingslib.accounts.AuthenticatorHelper.OnAccountsUpdateListener
            public final void onAccountsUpdate(UserHandle userHandle) {
                this.f$0.lambda$new$0$AccountTypesHelper(userHandle);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$AccountTypesHelper(UserHandle userHandle) {
        if (userHandle.equals(this.mUserHandle)) {
            updateAuthorizedAccountTypes(false);
        }
    }

    public void setAuthorities(List<String> authorities) {
        this.mAuthorities = authorities;
    }

    public void setAccountTypesFilter(Set<String> accountTypesFilter) {
        this.mAccountTypesFilter = accountTypesFilter;
    }

    protected void setAccountTypesExclusionFilter(Set<String> accountTypesExclusionFilter) {
        this.mAccountTypesExclusionFilter = accountTypesExclusionFilter;
    }

    public void setOnChangeListener(OnChangeListener listener) {
        this.mListener = listener;
    }

    private void updateAuthorizedAccountTypes(boolean isForced) {
        Set<String> set;
        Set<String> set2;
        AccountManager accountManager = AccountManager.get(this.mContext);
        AuthenticatorDescription[] authenticatorDescriptions = accountManager.getAuthenticatorTypesAsUser(this.mUserHandle.getIdentifier());
        Set<String> authorizedAccountTypes = new HashSet<>();
        for (AuthenticatorDescription authenticatorDescription : authenticatorDescriptions) {
            String accountType = authenticatorDescription.type;
            List<String> accountAuthorities = this.mAuthenticatorHelper.getAuthoritiesForAccountType(accountType);
            List<String> list = this.mAuthorities;
            boolean z = true;
            boolean authorized = list == null || list.isEmpty() || accountAuthorities == null || !Collections.disjoint(accountAuthorities, this.mAuthorities);
            boolean authorized2 = authorized && ((set2 = this.mAccountTypesFilter) == null || set2.contains(accountType));
            if (!authorized2 || ((set = this.mAccountTypesExclusionFilter) != null && set.contains(accountType))) {
                z = false;
            }
            boolean authorized3 = z;
            if (authorized3) {
                authorizedAccountTypes.add(accountType);
            }
        }
        if (isForced || !Objects.equals(this.mAuthorizedAccountTypes, authorizedAccountTypes)) {
            this.mAuthorizedAccountTypes = authorizedAccountTypes;
            OnChangeListener onChangeListener = this.mListener;
            if (onChangeListener != null) {
                onChangeListener.onAuthorizedAccountTypesChanged();
            }
        }
    }

    public Set<String> getAuthorizedAccountTypes() {
        if (this.mAuthorizedAccountTypes == null) {
            updateAuthorizedAccountTypes(false);
        }
        return this.mAuthorizedAccountTypes;
    }

    public void forceUpdate() {
        updateAuthorizedAccountTypes(true);
    }

    public void listenToAccountUpdates() {
        this.mAuthenticatorHelper.listenToAccountUpdates();
    }

    public void stopListeningToAccountUpdates() {
        this.mAuthenticatorHelper.stopListeningToAccountUpdates();
    }

    public CharSequence getLabelForType(String accountType) {
        return this.mAuthenticatorHelper.getLabelForType(this.mContext, accountType);
    }

    public Drawable getDrawableForType(String accountType) {
        return this.mAuthenticatorHelper.getDrawableForType(this.mContext, accountType);
    }

    AuthenticatorHelper getAuthenticatorHelper() {
        return this.mAuthenticatorHelper;
    }
}
