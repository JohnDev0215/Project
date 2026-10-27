package com.android.car.developeroptions.accounts;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.settingslib.accounts.AuthenticatorHelper;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class AccountHeaderPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnResume {
    private final Account mAccount;
    private final Activity mActivity;
    private LayoutPreference mHeaderPreference;
    private final PreferenceFragmentCompat mHost;
    private final UserHandle mUserHandle;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "account_header";
    }

    public AccountHeaderPreferenceController(Context context, Lifecycle lifecycle, Activity activity, PreferenceFragmentCompat preferenceFragmentCompat, Bundle bundle) {
        super(context);
        this.mActivity = activity;
        this.mHost = preferenceFragmentCompat;
        if (bundle != null && bundle.containsKey("account")) {
            this.mAccount = (Account) bundle.getParcelable("account");
        } else {
            this.mAccount = null;
        }
        if (bundle != null && bundle.containsKey("user_handle")) {
            this.mUserHandle = (UserHandle) bundle.getParcelable("user_handle");
        } else {
            this.mUserHandle = null;
        }
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return (this.mAccount == null || this.mUserHandle == null) ? false : true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mHeaderPreference = (LayoutPreference) preferenceScreen.findPreference("account_header");
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        AuthenticatorHelper authenticatorHelper = new AuthenticatorHelper(this.mContext, this.mUserHandle, null);
        EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(this.mActivity, this.mHost, this.mHeaderPreference.findViewById(R.id.entity_header));
        entityHeaderControllerNewInstance.setLabel(this.mAccount.name);
        entityHeaderControllerNewInstance.setIcon(authenticatorHelper.getDrawableForType(this.mContext, this.mAccount.type));
        entityHeaderControllerNewInstance.done(this.mActivity, true);
    }
}
