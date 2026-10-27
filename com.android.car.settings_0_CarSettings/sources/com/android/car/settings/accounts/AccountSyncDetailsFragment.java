package com.android.car.settings.accounts;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncAdapterType;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.os.UserHandle;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import com.android.settingslib.utils.ThreadUtils;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class AccountSyncDetailsFragment extends SettingsFragment {
    private static final String EXTRA_ACCOUNT = "extra_account";
    private static final String EXTRA_USER_HANDLE = "extra_user_handle";
    private Object mStatusChangeListenerHandle;
    private MenuItem mSyncButton;
    private boolean mIsStarted = false;
    private SyncStatusObserver mSyncStatusObserver = new SyncStatusObserver() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSyncDetailsFragment$w4CYrBBhC4m_1P2YAA-_QPvOZD4
        @Override // android.content.SyncStatusObserver
        public final void onStatusChanged(int i) {
            this.f$0.lambda$new$1$AccountSyncDetailsFragment(i);
        }
    };

    public /* synthetic */ void lambda$new$1$AccountSyncDetailsFragment(int which) {
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSyncDetailsFragment$QZQnb1jpMDZy0f8Nag6RUKaXu80
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$AccountSyncDetailsFragment();
            }
        });
    }

    public /* synthetic */ void lambda$new$0$AccountSyncDetailsFragment() {
        if (this.mIsStarted) {
            updateSyncButton();
        }
    }

    public static AccountSyncDetailsFragment newInstance(Account account, UserHandle userHandle) {
        AccountSyncDetailsFragment accountSyncDetailsFragment = new AccountSyncDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_account", account);
        bundle.putParcelable(EXTRA_USER_HANDLE, userHandle);
        accountSyncDetailsFragment.setArguments(bundle);
        return accountSyncDetailsFragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.account_sync_details_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mSyncButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSyncButton = new MenuItem.Builder(getContext()).build();
        updateSyncButton();
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Account account = (Account) getArguments().getParcelable("extra_account");
        UserHandle userHandle = (UserHandle) getArguments().getParcelable(EXTRA_USER_HANDLE);
        ((AccountDetailsWithSyncStatusPreferenceController) use(AccountDetailsWithSyncStatusPreferenceController.class, R.string.pk_account_details_with_sync)).setAccount(account);
        ((AccountDetailsWithSyncStatusPreferenceController) use(AccountDetailsWithSyncStatusPreferenceController.class, R.string.pk_account_details_with_sync)).setUserHandle(userHandle);
        ((AccountSyncDetailsPreferenceController) use(AccountSyncDetailsPreferenceController.class, R.string.pk_account_sync_details)).setAccount(account);
        ((AccountSyncDetailsPreferenceController) use(AccountSyncDetailsPreferenceController.class, R.string.pk_account_sync_details)).setUserHandle(userHandle);
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateSyncButton();
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mIsStarted = true;
        this.mStatusChangeListenerHandle = ContentResolver.addStatusChangeListener(13, this.mSyncStatusObserver);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mIsStarted = false;
        Object obj = this.mStatusChangeListenerHandle;
        if (obj != null) {
            ContentResolver.removeStatusChangeListener(obj);
        }
    }

    private void updateSyncButton() {
        MenuItem.OnClickListener onClickListener;
        UserHandle userHandle = (UserHandle) getArguments().getParcelable(EXTRA_USER_HANDLE);
        boolean hasActiveSyncs = !ContentResolver.getCurrentSyncsAsUser(userHandle.getIdentifier()).isEmpty();
        this.mSyncButton.setTitle(hasActiveSyncs ? R.string.sync_button_sync_cancel : R.string.sync_button_sync_now);
        MenuItem menuItem = this.mSyncButton;
        if (hasActiveSyncs) {
            onClickListener = new MenuItem.OnClickListener() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSyncDetailsFragment$twbGPWmWPgEenruIan0siQsF_TI
                @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
                public final void onClick(MenuItem menuItem2) {
                    this.f$0.lambda$updateSyncButton$2$AccountSyncDetailsFragment(menuItem2);
                }
            };
        } else {
            onClickListener = new MenuItem.OnClickListener() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSyncDetailsFragment$ZHaC605msGcUcKXFQIIVweO5UzA
                @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
                public final void onClick(MenuItem menuItem2) {
                    this.f$0.lambda$updateSyncButton$3$AccountSyncDetailsFragment(menuItem2);
                }
            };
        }
        menuItem.setOnClickListener(onClickListener);
    }

    public /* synthetic */ void lambda$updateSyncButton$2$AccountSyncDetailsFragment(MenuItem i) {
        cancelSyncForEnabledProviders();
    }

    public /* synthetic */ void lambda$updateSyncButton$3$AccountSyncDetailsFragment(MenuItem i) {
        requestSyncForEnabledProviders();
    }

    private void requestSyncForEnabledProviders() {
        Account account = (Account) getArguments().getParcelable("extra_account");
        UserHandle userHandle = (UserHandle) getArguments().getParcelable(EXTRA_USER_HANDLE);
        int userId = userHandle.getIdentifier();
        Set<SyncAdapterType> adapters = AccountSyncHelper.getSyncableSyncAdaptersForAccount(account, userHandle);
        for (SyncAdapterType adapter : adapters) {
            AccountSyncHelper.requestSyncIfAllowed(account, adapter.authority, userId);
        }
    }

    private void cancelSyncForEnabledProviders() {
        Account account = (Account) getArguments().getParcelable("extra_account");
        UserHandle userHandle = (UserHandle) getArguments().getParcelable(EXTRA_USER_HANDLE);
        int userId = userHandle.getIdentifier();
        Set<SyncAdapterType> adapters = AccountSyncHelper.getSyncableSyncAdaptersForAccount(account, userHandle);
        for (SyncAdapterType adapter : adapters) {
            ContentResolver.cancelSyncAsUser(account, adapter.authority, userId);
        }
    }
}
