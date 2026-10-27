package com.android.car.settings.accounts;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncAdapterType;
import android.content.SyncInfo;
import android.content.SyncStatusInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import com.android.car.settings.common.Logger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes5.dex */
class AccountSyncHelper {
    private static final Logger LOG = new Logger((Class<?>) AccountSyncHelper.class);

    public enum SyncState {
        ACTIVE,
        PENDING,
        FAILED,
        NONE
    }

    private AccountSyncHelper() {
    }

    static Set<SyncAdapterType> getVisibleSyncAdaptersForAccount(final Context context, Account account, final UserHandle userHandle) {
        Set<SyncAdapterType> syncableAdapters = getSyncableSyncAdaptersForAccount(account, userHandle);
        syncableAdapters.removeIf(new Predicate() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSyncHelper$Xcrm38GtfKgNVRW-E3lDh2DgXEM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AccountSyncHelper.lambda$getVisibleSyncAdaptersForAccount$0(context, userHandle, (SyncAdapterType) obj);
            }
        });
        return syncableAdapters;
    }

    static /* synthetic */ boolean lambda$getVisibleSyncAdaptersForAccount$0(Context context, UserHandle userHandle, SyncAdapterType syncAdapter) {
        return !isVisible(context, syncAdapter, userHandle);
    }

    static Set<SyncAdapterType> getSyncableSyncAdaptersForAccount(Account account, UserHandle userHandle) {
        Set<SyncAdapterType> adapters = new HashSet<>();
        SyncAdapterType[] syncAdapters = ContentResolver.getSyncAdapterTypesAsUser(userHandle.getIdentifier());
        for (SyncAdapterType syncAdapter : syncAdapters) {
            String authority = syncAdapter.authority;
            if (syncAdapter.accountType.equals(account.type)) {
                boolean isSyncable = ContentResolver.getIsSyncableAsUser(account, authority, userHandle.getIdentifier()) > 0;
                if (isSyncable) {
                    adapters.add(syncAdapter);
                }
            }
        }
        return adapters;
    }

    static void requestSyncIfAllowed(Account account, String authority, int userId) {
        if (!syncIsAllowed(account, authority, userId)) {
            return;
        }
        Bundle extras = new Bundle();
        extras.putBoolean("force", true);
        ContentResolver.requestSyncAsUser(account, authority, userId, extras);
    }

    static CharSequence getTitle(Context context, String authority, UserHandle userHandle) {
        PackageManager packageManager = context.getPackageManager();
        ProviderInfo providerInfo = packageManager.resolveContentProviderAsUser(authority, 0, userHandle.getIdentifier());
        if (providerInfo == null) {
            return "";
        }
        return providerInfo.loadLabel(packageManager);
    }

    static boolean isSyncing(Account account, List<SyncInfo> currentSyncs, String authority) {
        for (SyncInfo syncInfo : currentSyncs) {
            if (syncInfo.account.equals(account) && syncInfo.authority.equals(authority)) {
                return true;
            }
        }
        return false;
    }

    static SyncState getSyncState(SyncStatusInfo status, boolean syncEnabled, boolean activelySyncing) {
        boolean initialSync = status != null && status.initialize;
        boolean syncIsPending = status != null && status.pending;
        boolean lastSyncFailed = (!syncEnabled || status == null || status.lastFailureTime == 0 || status.getLastFailureMesgAsInt(0) == 1) ? false : true;
        if (activelySyncing && !initialSync) {
            return SyncState.ACTIVE;
        }
        if (syncIsPending && !initialSync) {
            return SyncState.PENDING;
        }
        if (lastSyncFailed) {
            return SyncState.FAILED;
        }
        return SyncState.NONE;
    }

    private static boolean syncIsAllowed(Account account, String authority, int userId) {
        boolean oneTimeSyncMode = !ContentResolver.getMasterSyncAutomaticallyAsUser(userId);
        boolean syncEnabled = ContentResolver.getSyncAutomaticallyAsUser(account, authority, userId);
        return oneTimeSyncMode || syncEnabled;
    }

    private static boolean isVisible(Context context, SyncAdapterType syncAdapter, UserHandle userHandle) {
        String authority = syncAdapter.authority;
        if (!syncAdapter.isUserVisible()) {
            return false;
        }
        try {
            context.getPackageManager().getPackageUidAsUser(syncAdapter.getPackageName(), userHandle.getIdentifier());
            CharSequence title = getTitle(context, authority, userHandle);
            return !TextUtils.isEmpty(title);
        } catch (PackageManager.NameNotFoundException e) {
            LOG.e("No uid for package" + syncAdapter.getPackageName(), e);
            return false;
        }
    }
}
