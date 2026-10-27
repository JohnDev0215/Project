package com.android.car.settings.accounts;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncAdapterType;
import android.content.SyncInfo;
import android.content.SyncStatusInfo;
import android.content.SyncStatusObserver;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.accounts.AccountSyncHelper;
import com.android.car.settings.common.FragmentController;
import com.android.settingslib.utils.ThreadUtils;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class AccountDetailsWithSyncStatusPreferenceController extends AccountDetailsPreferenceController {
    private boolean mIsStarted;
    private Object mStatusChangeListenerHandle;
    private SyncStatusObserver mSyncStatusObserver;

    public /* synthetic */ void lambda$new$1$AccountDetailsWithSyncStatusPreferenceController(int which) {
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountDetailsWithSyncStatusPreferenceController$f2mbspjzpvWA2a9mXFB_GTgb39o
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$AccountDetailsWithSyncStatusPreferenceController();
            }
        });
    }

    public /* synthetic */ void lambda$new$0$AccountDetailsWithSyncStatusPreferenceController() {
        if (this.mIsStarted) {
            refreshUi();
        }
    }

    public AccountDetailsWithSyncStatusPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mIsStarted = false;
        this.mSyncStatusObserver = new SyncStatusObserver() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountDetailsWithSyncStatusPreferenceController$wqm2v6XOGaCkeWxssD5x5TtpQXc
            @Override // android.content.SyncStatusObserver
            public final void onStatusChanged(int i) {
                this.f$0.lambda$new$1$AccountDetailsWithSyncStatusPreferenceController(i);
            }
        };
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mIsStarted = true;
        this.mStatusChangeListenerHandle = ContentResolver.addStatusChangeListener(13, this.mSyncStatusObserver);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mIsStarted = false;
        Object obj = this.mStatusChangeListenerHandle;
        if (obj != null) {
            ContentResolver.removeStatusChangeListener(obj);
        }
    }

    @Override // com.android.car.settings.accounts.AccountDetailsPreferenceController, com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        super.updateState(preference);
        if (isSyncFailing()) {
            preference.setSummary(R.string.sync_is_failing);
        } else {
            preference.setSummary("");
        }
    }

    private boolean isSyncFailing() {
        int userId = getUserHandle().getIdentifier();
        List<SyncInfo> currentSyncs = ContentResolver.getCurrentSyncsAsUser(userId);
        boolean syncIsFailing = false;
        Set<SyncAdapterType> syncAdapters = AccountSyncHelper.getVisibleSyncAdaptersForAccount(getContext(), getAccount(), getUserHandle());
        for (SyncAdapterType syncAdapter : syncAdapters) {
            String authority = syncAdapter.authority;
            SyncStatusInfo status = ContentResolver.getSyncStatusAsUser(getAccount(), authority, userId);
            boolean syncEnabled = ContentResolver.getSyncAutomaticallyAsUser(getAccount(), authority, userId);
            boolean activelySyncing = AccountSyncHelper.isSyncing(getAccount(), currentSyncs, authority);
            AccountSyncHelper.SyncState syncState = AccountSyncHelper.getSyncState(status, syncEnabled, activelySyncing);
            boolean syncIsPending = status != null && status.pending;
            if (syncState == AccountSyncHelper.SyncState.FAILED && !activelySyncing && !syncIsPending) {
                syncIsFailing = true;
            }
        }
        return syncIsFailing;
    }
}
