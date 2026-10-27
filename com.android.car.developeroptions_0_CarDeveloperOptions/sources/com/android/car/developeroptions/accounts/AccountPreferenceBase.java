package com.android.car.developeroptions.accounts;

import android.content.ContentResolver;
import android.content.SyncStatusObserver;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.format.DateFormat;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.Utils;
import com.android.settingslib.accounts.AuthenticatorHelper;
import com.android.settingslib.utils.ThreadUtils;

/* JADX INFO: loaded from: classes.dex */
abstract class AccountPreferenceBase extends SettingsPreferenceFragment implements AuthenticatorHelper.OnAccountsUpdateListener {
    protected AuthenticatorHelper mAuthenticatorHelper;
    private Object mStatusChangeListenerHandle;
    private SyncStatusObserver mSyncStatusObserver = new SyncStatusObserver() { // from class: com.android.car.developeroptions.accounts.-$$Lambda$AccountPreferenceBase$8EqJ2-a54NhyU7DPyUNaDo2QHXg
        @Override // android.content.SyncStatusObserver
        public final void onStatusChanged(int i) {
            this.f$0.lambda$new$1$AccountPreferenceBase(i);
        }
    };
    private UserManager mUm;
    protected UserHandle mUserHandle;

    @Override // com.android.settingslib.accounts.AuthenticatorHelper.OnAccountsUpdateListener
    public void onAccountsUpdate(UserHandle userHandle) {
    }

    protected void onAuthDescriptionsUpdated() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: onSyncStateUpdated, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0$AccountPreferenceBase() {
    }

    AccountPreferenceBase() {
    }

    static {
        Log.isLoggable("AccountPreferenceBase", 2);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUm = (UserManager) getSystemService("user");
        FragmentActivity activity = getActivity();
        this.mUserHandle = Utils.getSecureTargetUser(activity.getActivityToken(), this.mUm, getArguments(), activity.getIntent().getExtras());
        this.mAuthenticatorHelper = new AuthenticatorHelper(activity, this.mUserHandle, this);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        DateFormat.getDateFormat(activity);
        DateFormat.getTimeFormat(activity);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mStatusChangeListenerHandle = ContentResolver.addStatusChangeListener(13, this.mSyncStatusObserver);
        lambda$new$0();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        ContentResolver.removeStatusChangeListener(this.mStatusChangeListenerHandle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$1$AccountPreferenceBase(int i) {
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.accounts.-$$Lambda$AccountPreferenceBase$YDvXroaS-kD6HspDaz0uxRcnzoY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$AccountPreferenceBase();
            }
        });
    }

    public void updateAuthDescriptions() {
        this.mAuthenticatorHelper.updateAuthDescriptions(getActivity());
        onAuthDescriptionsUpdated();
    }

    protected Drawable getDrawableForType(String str) {
        return this.mAuthenticatorHelper.getDrawableForType(getActivity(), str);
    }

    protected CharSequence getLabelForType(String str) {
        return this.mAuthenticatorHelper.getLabelForType(getActivity(), str);
    }
}
