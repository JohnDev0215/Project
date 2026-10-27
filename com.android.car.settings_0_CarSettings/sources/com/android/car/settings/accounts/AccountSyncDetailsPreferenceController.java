package com.android.car.settings.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SyncAdapterType;
import android.content.SyncInfo;
import android.content.SyncStatusInfo;
import android.content.SyncStatusObserver;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.text.format.DateFormat;
import androidx.collection.ArrayMap;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.accounts.AccountSyncHelper;
import com.android.car.settings.common.ActivityResultCallback;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.accounts.AuthenticatorHelper;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: classes5.dex */
public class AccountSyncDetailsPreferenceController extends PreferenceController<PreferenceGroup> implements AuthenticatorHelper.OnAccountsUpdateListener {
    private static final Logger LOG = new Logger((Class<?>) AccountSyncDetailsPreferenceController.class);
    private Account mAccount;
    private AuthenticatorHelper mAuthenticatorHelper;
    private boolean mIsStarted;
    private Object mStatusChangeListenerHandle;
    private final Map<String, SyncPreference> mSyncPreferences;
    private SyncStatusObserver mSyncStatusObserver;
    private UserHandle mUserHandle;

    public /* synthetic */ void lambda$new$1$AccountSyncDetailsPreferenceController(int which) {
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSyncDetailsPreferenceController$VfI530GYvYbrUAkpe8Cn6BKMOv0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$AccountSyncDetailsPreferenceController();
            }
        });
    }

    public /* synthetic */ void lambda$new$0$AccountSyncDetailsPreferenceController() {
        if (this.mIsStarted) {
            forceUpdateSyncCategory();
        }
    }

    public AccountSyncDetailsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mSyncPreferences = new ArrayMap();
        this.mIsStarted = false;
        this.mSyncStatusObserver = new SyncStatusObserver() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSyncDetailsPreferenceController$8O_YFsfjaWq-NZLFslxFmUKKdMc
            @Override // android.content.SyncStatusObserver
            public final void onStatusChanged(int i) {
                this.f$0.lambda$new$1$AccountSyncDetailsPreferenceController(i);
            }
        };
    }

    public void setAccount(Account account) {
        this.mAccount = account;
    }

    public void setUserHandle(UserHandle userHandle) {
        this.mUserHandle = userHandle;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        LOG.v("checkInitialized");
        if (this.mAccount == null) {
            throw new IllegalStateException("AccountSyncDetailsPreferenceController must be initialized by calling setAccount(Account)");
        }
        if (this.mUserHandle == null) {
            throw new IllegalStateException("AccountSyncDetailsPreferenceController must be initialized by calling setUserHandle(UserHandle)");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mAuthenticatorHelper = new AuthenticatorHelper(getContext(), this.mUserHandle, this);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mIsStarted = true;
        this.mAuthenticatorHelper.listenToAccountUpdates();
        this.mStatusChangeListenerHandle = ContentResolver.addStatusChangeListener(13, this.mSyncStatusObserver);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mIsStarted = false;
        this.mAuthenticatorHelper.stopListeningToAccountUpdates();
        Object obj = this.mStatusChangeListenerHandle;
        if (obj != null) {
            ContentResolver.removeStatusChangeListener(obj);
        }
    }

    @Override // com.android.settingslib.accounts.AuthenticatorHelper.OnAccountsUpdateListener
    public void onAccountsUpdate(UserHandle userHandle) {
        if (userHandle.equals(this.mUserHandle)) {
            forceUpdateSyncCategory();
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        forceUpdateSyncCategory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onSyncPreferenceClicked, reason: merged with bridge method [inline-methods] */
    public boolean lambda$getSyncPreferences$4$AccountSyncDetailsPreferenceController(SyncPreference preference) {
        String authority = preference.getKey();
        String packageName = preference.getPackageName();
        int uid = preference.getUid();
        if (preference.isOneTimeSyncMode()) {
            if (requestAccountAccessIfNeeded(packageName, uid)) {
                return true;
            }
            requestSync(authority);
        } else {
            boolean syncOn = preference.isChecked();
            int userId = this.mUserHandle.getIdentifier();
            boolean oldSyncState = ContentResolver.getSyncAutomaticallyAsUser(this.mAccount, authority, userId);
            if (syncOn == oldSyncState || (syncOn && requestAccountAccessIfNeeded(packageName, uid))) {
                return true;
            }
            ContentResolver.setSyncAutomaticallyAsUser(this.mAccount, authority, syncOn, userId);
            if (syncOn) {
                requestSync(authority);
            } else {
                cancelSync(authority);
            }
        }
        return true;
    }

    private void requestSync(String authority) {
        AccountSyncHelper.requestSyncIfAllowed(this.mAccount, authority, this.mUserHandle.getIdentifier());
    }

    private void cancelSync(String authority) {
        ContentResolver.cancelSyncAsUser(this.mAccount, authority, this.mUserHandle.getIdentifier());
    }

    private boolean requestAccountAccessIfNeeded(String packageName, int uid) {
        IntentSender intent;
        if (packageName == null) {
            return false;
        }
        AccountManager accountManager = (AccountManager) getContext().getSystemService(AccountManager.class);
        if (!accountManager.hasAccountAccess(this.mAccount, packageName, this.mUserHandle) && (intent = accountManager.createRequestAccountAccessIntentSenderAsUser(this.mAccount, packageName, this.mUserHandle)) != null) {
            try {
                getFragmentController().startIntentSenderForResult(intent, uid, null, 0, 0, null, new ActivityResultCallback() { // from class: com.android.car.settings.accounts.-$$Lambda$MpYOaEph8zz6ghmwNbClF4Pv12w
                    @Override // com.android.car.settings.common.ActivityResultCallback
                    public final void processActivityResult(int i, int i2, Intent intent2) {
                        this.f$0.onAccountRequestApproved(i, i2, intent2);
                    }
                });
                return true;
            } catch (IntentSender.SendIntentException e) {
                LOG.e("Error requesting account access", e);
            }
        }
        return false;
    }

    public void onAccountRequestApproved(int uid, int resultCode, Intent data) {
        if (resultCode == -1) {
            for (SyncPreference pref : this.mSyncPreferences.values()) {
                if (pref.getUid() == uid) {
                    lambda$getSyncPreferences$4$AccountSyncDetailsPreferenceController(pref);
                    return;
                }
            }
        }
    }

    private void forceUpdateSyncCategory() {
        Set<String> preferencesToRemove = new HashSet<>(this.mSyncPreferences.keySet());
        List<SyncPreference> preferences = getSyncPreferences(preferencesToRemove);
        Collections.sort(preferences, Comparator.comparing(new Function() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSyncDetailsPreferenceController$iFn5kLB4OUUqTv5RdL6syBmeqR4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SyncPreference) obj).getTitle().toString();
            }
        }).thenComparing(new Function() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSyncDetailsPreferenceController$_mER8k3cexW-slehtXuqHm30qG0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SyncPreference) obj).getSummary().toString();
            }
        }));
        for (int i = 0; i < preferences.size(); i++) {
            SyncPreference pref = preferences.get(i);
            pref.setOrder(i);
            this.mSyncPreferences.put(pref.getKey(), pref);
            getPreference().addPreference(pref);
        }
        for (String key : preferencesToRemove) {
            getPreference().removePreference(this.mSyncPreferences.get(key));
            this.mSyncPreferences.remove(key);
        }
    }

    private List<SyncPreference> getSyncPreferences(Set<String> preferencesToRemove) {
        final AccountSyncDetailsPreferenceController accountSyncDetailsPreferenceController = this;
        int userId = accountSyncDetailsPreferenceController.mUserHandle.getIdentifier();
        PackageManager packageManager = getContext().getPackageManager();
        List<SyncInfo> currentSyncs = ContentResolver.getCurrentSyncsAsUser(userId);
        boolean oneTimeSyncMode = !ContentResolver.getMasterSyncAutomaticallyAsUser(userId);
        List<SyncPreference> syncPreferences = new ArrayList<>();
        Set<SyncAdapterType> syncAdapters = AccountSyncHelper.getVisibleSyncAdaptersForAccount(getContext(), accountSyncDetailsPreferenceController.mAccount, accountSyncDetailsPreferenceController.mUserHandle);
        for (SyncAdapterType syncAdapter : syncAdapters) {
            String authority = syncAdapter.authority;
            try {
                int uid = packageManager.getPackageUidAsUser(syncAdapter.getPackageName(), userId);
                SyncPreference pref = accountSyncDetailsPreferenceController.mSyncPreferences.getOrDefault(authority, new SyncPreference(getContext(), authority));
                pref.setUid(uid);
                pref.setPackageName(syncAdapter.getPackageName());
                pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSyncDetailsPreferenceController$LVxyb-zJW8qcUOKjKpcRE-zhhjU
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$getSyncPreferences$4$AccountSyncDetailsPreferenceController(preference);
                    }
                });
                CharSequence title = AccountSyncHelper.getTitle(getContext(), authority, accountSyncDetailsPreferenceController.mUserHandle);
                pref.setTitle(title);
                syncPreferences.add(pref);
                preferencesToRemove.remove(authority);
                SyncStatusInfo status = ContentResolver.getSyncStatusAsUser(accountSyncDetailsPreferenceController.mAccount, authority, userId);
                boolean syncEnabled = ContentResolver.getSyncAutomaticallyAsUser(accountSyncDetailsPreferenceController.mAccount, authority, userId);
                boolean activelySyncing = AccountSyncHelper.isSyncing(accountSyncDetailsPreferenceController.mAccount, currentSyncs, authority);
                boolean checked = oneTimeSyncMode || syncEnabled;
                int userId2 = userId;
                pref.setChecked(checked);
                String summary = accountSyncDetailsPreferenceController.getSummary(status, syncEnabled, activelySyncing);
                pref.setSummary(summary);
                AccountSyncHelper.SyncState syncState = AccountSyncHelper.getSyncState(status, syncEnabled, activelySyncing);
                pref.setSyncState(syncState);
                pref.setOneTimeSyncMode(oneTimeSyncMode);
                accountSyncDetailsPreferenceController = this;
                userId = userId2;
            } catch (PackageManager.NameNotFoundException e) {
                LOG.e("No uid for package" + syncAdapter.getPackageName(), e);
                accountSyncDetailsPreferenceController = this;
                userId = userId;
            }
        }
        return syncPreferences;
    }

    private String getSummary(SyncStatusInfo status, boolean syncEnabled, boolean activelySyncing) {
        long successEndTime = status == null ? 0L : status.lastSuccessTime;
        if (!syncEnabled) {
            return getContext().getString(R.string.sync_disabled);
        }
        if (activelySyncing) {
            return getContext().getString(R.string.sync_in_progress);
        }
        if (successEndTime != 0) {
            Date date = new Date();
            date.setTime(successEndTime);
            String timeString = formatSyncDate(date);
            return getContext().getString(R.string.last_synced, timeString);
        }
        return "";
    }

    String formatSyncDate(Date date) {
        return DateFormat.getDateFormat(getContext()).format(date) + " " + DateFormat.getTimeFormat(getContext()).format(date);
    }
}
