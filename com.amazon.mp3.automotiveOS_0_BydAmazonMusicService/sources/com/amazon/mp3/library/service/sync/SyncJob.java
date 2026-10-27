package com.amazon.mp3.library.service.sync;

import android.app.Application;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.download.service.AutoDownloadPurchaseReceiver;
import com.amazon.mp3.download.widevineMigration.WidevineMigrationTracker;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabaseUtil;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate.DownloadStateUpdaterHelper;
import com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate.PreWidevineDBUpdateHelper;
import com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate.PrimePlaylistDownloadStateUpdater;
import com.amazon.mp3.library.service.sync.SyncOperation;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.ContentAccessType;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.User;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.subscription.ContentAccess;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class SyncJob extends Job {
    private static final HashMap<Class<?>, Integer> SYNC_OP_TO_STRING_MAP;
    private static final String TAG = "SyncJob";
    private int mFinalSyncResult = 4;
    private final int mFlags;
    private final Intent mIntent;

    private boolean operationFinishedWithFailure(final int result, final int errorCount) {
        return result != 2 ? result == 3 || result == 4 || result == 5 : errorCount + 1 > 4;
    }

    private boolean operationFinishedWithSuccess(final int result) {
        return result == 0;
    }

    @Override // com.amazon.mp3.service.job.Job
    public int getDefaultThreadPriority() {
        return 1;
    }

    @Override // com.amazon.mp3.service.job.Job
    public boolean restartable() {
        return false;
    }

    SyncJob(Intent intent) {
        this.mIntent = intent;
        this.mFlags = intent != null ? intent.getIntExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_FLAGS", 13987) : 13987;
    }

    @Override // com.amazon.mp3.service.job.Job
    public synchronized void cancel() {
    }

    public int getFinalResult() {
        return this.mFinalSyncResult;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00aa  */
    @Override // com.amazon.mp3.service.job.Job
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int run() throws java.lang.Throwable {
        /*
            r14 = this;
            java.lang.String r0 = "Broadcasting end of sync event"
            java.lang.String r1 = com.amazon.mp3.library.service.sync.SyncJob.TAG
            java.lang.String r2 = "Sync job starting"
            com.amazon.mp3.util.Log.debug(r1, r2)
            android.app.Application r2 = r14.getContext()
            boolean r3 = isInitialSync(r2)
            r4 = 1
            r5 = 5
            r6 = 0
            r7 = 3
            android.database.sqlite.SQLiteDatabase r2 = com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase.getWritableDatabase(r2)     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            if (r2 == 0) goto L6b
            java.lang.String r8 = "Performing full sync"
            com.amazon.mp3.util.Profiler.begin(r8)     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            r14.broadcastSyncEvent(r4)     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            long r8 = java.lang.System.nanoTime()     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            boolean r2 = r14.sync(r2)     // Catch: java.lang.Throwable -> L84 java.lang.InterruptedException -> L86
            long r10 = java.lang.System.nanoTime()     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            long r10 = r10 - r8
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            long r8 = r8.toSeconds(r10)     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            r12.<init>()     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            java.lang.String r13 = "Sync time: "
            r12.append(r13)     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            r12.append(r8)     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            java.lang.String r8 = " seconds"
            r12.append(r8)     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            java.lang.String r8 = r12.toString()     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            com.amazon.mp3.util.Log.info(r1, r8)     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            if (r2 == 0) goto L6a
            com.amazon.mp3.account.credentials.AccountCredentialUtil r8 = com.amazon.mp3.account.credentials.AccountCredentialUtil.get()     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            boolean r8 = r8.isSignedIn()     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            if (r8 == 0) goto L6a
            int r8 = r14.mFlags     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            r8 = r8 & 2
            if (r8 == 0) goto L6a
            if (r3 == 0) goto L67
            com.amazon.mp3.service.metrics.MetricsLogger.initialSync(r10)     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
            goto L6a
        L67:
            com.amazon.mp3.service.metrics.MetricsLogger.incrementalSync(r10)     // Catch: java.lang.InterruptedException -> L87 java.lang.Throwable -> L9a
        L6a:
            r6 = r2
        L6b:
            com.amazon.mp3.util.Log.info(r1, r0)
            if (r6 == 0) goto L71
            goto L72
        L71:
            r5 = r7
        L72:
            r14.broadcastSyncEvent(r5)
            if (r6 != 0) goto L80
        L77:
            int r0 = r14.mFlags
            com.amazon.music.metrics.mts.event.definition.flex.FlexEvent r0 = r14.createSyncFailedFlexEvent(r3, r0)
            com.amazon.mp3.service.metrics.MetricsLogger.sendEvent(r0)
        L80:
            com.amazon.mp3.util.Profiler.end()
            goto L95
        L84:
            r1 = move-exception
            goto L9c
        L86:
            r2 = r6
        L87:
            java.lang.String r1 = com.amazon.mp3.library.service.sync.SyncJob.TAG     // Catch: java.lang.Throwable -> L9a
            java.lang.String r8 = "InterruptedException"
            com.amazon.mp3.util.Log.warning(r1, r8)     // Catch: java.lang.Throwable -> L9a
            com.amazon.mp3.util.Log.info(r1, r0)
            r14.broadcastSyncEvent(r7)
            goto L77
        L95:
            if (r6 == 0) goto L98
            goto L99
        L98:
            r4 = r7
        L99:
            return r4
        L9a:
            r1 = move-exception
            r6 = r2
        L9c:
            java.lang.String r2 = com.amazon.mp3.library.service.sync.SyncJob.TAG
            com.amazon.mp3.util.Log.info(r2, r0)
            if (r6 == 0) goto La4
            goto La5
        La4:
            r5 = r7
        La5:
            r14.broadcastSyncEvent(r5)
            if (r6 != 0) goto Lb3
            int r0 = r14.mFlags
            com.amazon.music.metrics.mts.event.definition.flex.FlexEvent r0 = r14.createSyncFailedFlexEvent(r3, r0)
            com.amazon.mp3.service.metrics.MetricsLogger.sendEvent(r0)
        Lb3:
            com.amazon.mp3.util.Profiler.end()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.service.sync.SyncJob.run():int");
    }

    @VisibleForTesting
    FlexEvent createSyncFailedFlexEvent(final boolean initialSync, final int flags) {
        FlexEvent.Builder builder;
        if (initialSync) {
            builder = FlexEvent.builder("androidInitialSyncEventFailed");
        } else {
            builder = FlexEvent.builder("androidIncrementalSyncFailed");
        }
        builder.withFlexStr1(String.valueOf(flags));
        return builder.build();
    }

    public static boolean isInitialSync(Context context) {
        return TextUtils.isEmpty(SyncService.getSyncLastCheckpoint(context));
    }

    private static boolean isExplicitFullSyncForKatanaPending(Context context) {
        return TextUtils.isEmpty(SyncService.getExplicitFullTrackSync(context));
    }

    private static void setExplicitFullSyncForKatanaDone(Context context) {
        SyncService.setExplicitFullTrackSync(context, "done");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v13 */
    /* JADX WARN: Type inference failed for: r15v14 */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v7 */
    private boolean sync(SQLiteDatabase sQLiteDatabase) throws Throwable {
        boolean z;
        Iterator<SyncOperation> it;
        boolean z2;
        int iSync;
        boolean z3;
        ?? r15;
        boolean z4;
        ?? r152;
        boolean z5;
        int i;
        int i2;
        boolean z6;
        int updatedResourceCount;
        boolean z7;
        ?? r153;
        String str;
        Application context = getContext();
        ContentResolver contentResolver = context.getContentResolver();
        boolean z8 = false;
        try {
            ?? r6 = 1;
            if ((this.mFlags & 32) != 0) {
                try {
                    refreshAccountDetails();
                } catch (SyncOperation.AccountStatusException e) {
                    Log.info(TAG, "AccountStatusException handled. cause=%s", e.getCause().getClass());
                    this.mFinalSyncResult = 4;
                    onAccountStatusException(context, e);
                    SyncService.setSyncNotificationVisible(false);
                    Intent intent = new Intent();
                    intent.setAction("com.amazon.mp3.action.sync_updated");
                    context.sendBroadcast(intent);
                    return false;
                }
            }
            Iterator<SyncOperation> it2 = getOrderedOperations(sQLiteDatabase).iterator();
            int i3 = 0;
            boolean z9 = false;
            int i4 = 4;
            loop0: while (it2.hasNext()) {
                SyncOperation next = it2.next();
                Uri contentUri = next.getContentUri();
                String simpleName = next.getClass().getSimpleName();
                boolean z10 = z8 ? 1 : 0;
                int i5 = z10 ? 1 : 0;
                boolean z11 = z9 ? 1 : 0;
                int i6 = i3;
                int i7 = i4;
                boolean z12 = z11 ? 1 : 0;
                int i8 = r6;
                ?? r154 = z10;
                while (r154 == 0) {
                    if (SyncService.syncBlocked()) {
                        this.mFinalSyncResult = z8 ? 1 : 0;
                        SyncService.setSyncNotificationVisible(z8);
                        Intent intent2 = new Intent();
                        intent2.setAction("com.amazon.mp3.action.sync_updated");
                        context.sendBroadcast(intent2);
                        return i8;
                    }
                    try {
                        str = TAG;
                        it = it2;
                    } catch (SyncOperation.AccountStatusException e2) {
                        e = e2;
                        it = it2;
                    } catch (IllegalStateException e3) {
                        e = e3;
                        it = it2;
                    }
                    try {
                        Object[] objArr = new Object[i8];
                        objArr[z8 ? 1 : 0] = simpleName;
                        Log.info(str, "Starting sync [%s]", objArr);
                        iSync = next.sync();
                        z2 = z12;
                    } catch (SyncOperation.AccountStatusException e4) {
                        e = e4;
                        z2 = z12;
                        String str2 = TAG;
                        Object[] objArr2 = new Object[i8];
                        objArr2[z8 ? 1 : 0] = e.getCause().getClass();
                        Log.info(str2, "AccountStatusException handled. cause=%s", objArr2);
                        onAccountStatusException(context, e);
                        iSync = 4;
                    } catch (IllegalStateException e5) {
                        e = e5;
                        String str3 = "" + e.getMessage();
                        String str4 = TAG;
                        z2 = z12;
                        Object[] objArr3 = new Object[i8];
                        objArr3[z8 ? 1 : 0] = str3;
                        Log.debug(str4, "IllegalState exception handled. detail=%s", objArr3);
                        iSync = 2;
                    }
                    if (FullTrackSyncOperation.class.getSimpleName().equals(simpleName)) {
                        checkForLibrarySyncFinished(iSync, i6);
                    }
                    if (iSync == 0) {
                        String str5 = TAG;
                        Object[] objArr4 = new Object[1];
                        try {
                            objArr4[0] = simpleName;
                            Log.info(str5, "Sync result [%s]: RESULT_SYNC_FINISHED", objArr4);
                            next.onFinished();
                            z3 = true;
                            r15 = 1;
                            z4 = true;
                            z5 = z3;
                            r152 = r15;
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            SyncService.setSyncNotificationVisible(z);
                            Intent intent3 = new Intent();
                            intent3.setAction("com.amazon.mp3.action.sync_updated");
                            context.sendBroadcast(intent3);
                            throw th;
                        }
                    } else if (iSync == i8) {
                        String str6 = TAG;
                        Object[] objArr5 = new Object[1];
                        try {
                            objArr5[0] = simpleName;
                            Log.info(str6, "Sync result [%s]: RESULT_SYNC_HAS_MORE", objArr5);
                            broadcastSyncEvent(4);
                            z3 = z2;
                            i6 = 0;
                            r15 = r154;
                            z4 = true;
                            z5 = z3;
                            r152 = r15;
                        } catch (Throwable th2) {
                            th = th2;
                            z = false;
                            SyncService.setSyncNotificationVisible(z);
                            Intent intent32 = new Intent();
                            intent32.setAction("com.amazon.mp3.action.sync_updated");
                            context.sendBroadcast(intent32);
                            throw th;
                        }
                    } else {
                        if (iSync != 2) {
                            if (iSync == 3) {
                                String str7 = TAG;
                                Object[] objArr6 = new Object[i8];
                                objArr6[z8 ? 1 : 0] = simpleName;
                                Log.info(str7, "Sync result [%s]: RESULT_INVALID_RESPONSE", objArr6);
                                next.onFailed();
                                next.onFinished();
                            } else if (iSync != 4) {
                                if (iSync != 5) {
                                    z7 = z8 ? 1 : 0;
                                    r153 = r154;
                                } else {
                                    String str8 = TAG;
                                    Object[] objArr7 = new Object[i8];
                                    objArr7[z8 ? 1 : 0] = simpleName;
                                    Log.info(str8, "Sync result [%s]: RESULT_SYNC_ERROR. Sync continuing", objArr7);
                                    next.onFailed();
                                    next.onFinished();
                                    z7 = z8 ? 1 : 0;
                                    r153 = i8;
                                }
                                z5 = z2;
                                r152 = r153;
                                z4 = z7;
                            } else {
                                String str9 = TAG;
                                Object[] objArr8 = new Object[i8];
                                objArr8[z8 ? 1 : 0] = simpleName;
                                Log.info(str9, "Sync result [%s]: RESULT_UNRECOVERABLE_ERROR", objArr8);
                                next.onFailed();
                                next.onFinished();
                            }
                            i4 = iSync;
                            z9 = z8 ? 1 : 0;
                            break loop0;
                        }
                        String str10 = TAG;
                        Object[] objArr9 = new Object[i8];
                        objArr9[z8 ? 1 : 0] = simpleName;
                        Log.info(str10, "Sync result [%s]: RESULT_NETWORK_ERROR", objArr9);
                        int i9 = i6 + 1;
                        if (i9 > 4) {
                            next.onFailed();
                            next.onFinished();
                            i4 = iSync;
                            z9 = z8 ? 1 : 0;
                            break loop0;
                        }
                        long j = ((long) i9) * 2500;
                        Log.verbose(str10, "Sleeping for " + (j / 1000) + " seconds");
                        broadcastSyncEvent(2);
                        Thread.sleep(j);
                        z5 = z2;
                        i6 = i9;
                        z4 = false;
                        r152 = r154;
                    }
                    if (z4) {
                        try {
                            i = i5;
                            if (i != next.getUpdatedResourceCount()) {
                                String str11 = TAG;
                                i2 = iSync;
                                z6 = true;
                                Object[] objArr10 = new Object[1];
                                try {
                                    objArr10[0] = Integer.valueOf(i == true ? 1 : 0);
                                    Log.verbose(str11, "resource count changed to [%d], notifying...", objArr10);
                                    contentResolver.notifyChange(contentUri, null);
                                    CirrusDatabaseUtil.updateAllCachedTrackCounts(context);
                                    if (contentUri.equals(CirrusMediaSource.NOTIFICATION_URI_LOCAL)) {
                                        contentResolver.notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                                    }
                                    updatedResourceCount = next.getUpdatedResourceCount();
                                    SyncService.changesMade();
                                    i8 = z6;
                                    it2 = it;
                                    z8 = false;
                                    int i10 = i2;
                                    i5 = updatedResourceCount;
                                    i7 = i10;
                                    z12 = z5;
                                    r154 = r152;
                                } catch (Throwable th3) {
                                    th = th3;
                                    z = false;
                                    SyncService.setSyncNotificationVisible(z);
                                    Intent intent322 = new Intent();
                                    intent322.setAction("com.amazon.mp3.action.sync_updated");
                                    context.sendBroadcast(intent322);
                                    throw th;
                                }
                            } else {
                                i2 = iSync;
                                z6 = true;
                                Log.verbose(TAG, "sync() ok, but no need to notify");
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            z = false;
                        }
                    } else {
                        i = i5;
                        z6 = true;
                        i2 = iSync;
                    }
                    updatedResourceCount = i;
                    i8 = z6;
                    it2 = it;
                    z8 = false;
                    int i102 = i2;
                    i5 = updatedResourceCount;
                    i7 = i102;
                    z12 = z5;
                    r154 = r152;
                }
                boolean z13 = z12;
                i4 = i7;
                i3 = i6;
                z9 = z13 ? 1 : 0;
                r6 = i8;
            }
            this.mFinalSyncResult = i4;
            SyncService.setSyncNotificationVisible(false);
            Intent intent4 = new Intent();
            intent4.setAction("com.amazon.mp3.action.sync_updated");
            context.sendBroadcast(intent4);
            return z9;
        } catch (Throwable th5) {
            th = th5;
            z = false;
            SyncService.setSyncNotificationVisible(z);
            Intent intent3222 = new Intent();
            intent3222.setAction("com.amazon.mp3.action.sync_updated");
            context.sendBroadcast(intent3222);
            throw th;
        }
    }

    private void onAccountStatusException(Context context, SyncOperation.AccountStatusException exception) {
        Throwable cause = exception.getCause();
        if (cause != null) {
            if (cause instanceof CirrusExceptions.AccountNotFoundException) {
                Log.debug(TAG, "account not found");
            } else if (cause instanceof CirrusExceptions.InvalidPaymentMethodException) {
                CirrusExceptions.InvalidPaymentMethodException.setDetected(true);
            } else if (cause instanceof CirrusExceptions.DeviceRegistrationException) {
                CirrusExceptions.DeviceRegistrationException.setDetected(true);
            } else if (cause instanceof CirrusExceptions.CloudPlayerHomeChangedException) {
                CirrusExceptions.CloudPlayerHomeChangedException.setDetected(true);
            }
            getContext().sendBroadcast(new Intent("com.amazon.mp3.ACTION_CIRRUS_ACCOUNT_ERROR"));
            return;
        }
        try {
            AccountRequestUtil.refreshAndStoreAccountDetailsForCurrentUser(getContext());
        } catch (Exception unused) {
            Log.info(TAG, "failed to refresh account details, setting status to LOCKED");
            AccountDetailStorage.get().setAccountStatus(User.CloudAccountStatus.LOCKED);
        }
        AccountCredentialUtil.get().removeCredentialsAndSettings();
    }

    private void refreshAccountDetails() throws SyncOperation.AccountStatusException {
        if (AccountCredentialUtil.get().isSignedIn()) {
            try {
                String libraryHomeMarketplace = AccountDetailStorage.get().getLibraryHomeMarketplace("");
                String cloudPlayerMarketplaceId = AccountRequestUtil.getCustomerHome(getContext()).getCloudPlayerMarketplaceId();
                AccountRequestUtil.refreshAndStoreAccountDetailsForCurrentUser(getContext());
                if (!libraryHomeMarketplace.isEmpty() && !libraryHomeMarketplace.equals(cloudPlayerMarketplaceId)) {
                    throw new CirrusExceptions.CloudPlayerHomeChangedException();
                }
                AccountManagerSingleton.get().updateUser();
            } catch (AbstractHttpClient.HttpClientException | CirrusExceptions.CirrusException unused) {
            } catch (CirrusExceptions.InvalidAccountException e) {
                throw new SyncOperation.AccountStatusException(e);
            } catch (Exception e2) {
                Log.verbose(TAG, String.format(Locale.US, "unexpected exception handled and swallowed: %s", e2));
            }
        }
    }

    private LinkedList<SyncOperation> getOrderedOperations(SQLiteDatabase db) {
        boolean z;
        Application context = getContext();
        boolean zCanSyncRemoteContent = AccountDetailUtil.get(context).canSyncRemoteContent();
        boolean z2 = zCanSyncRemoteContent && (this.mFlags & 1) != 0;
        if (zCanSyncRemoteContent) {
            int i = this.mFlags & 512;
        }
        boolean z3 = zCanSyncRemoteContent && (this.mFlags & 2) != 0;
        int i2 = this.mFlags;
        boolean z4 = (i2 & 128) != 0;
        boolean z5 = zCanSyncRemoteContent && (i2 & 1024) != 0;
        boolean z6 = !SettingsUtil.hasRenormalized(context);
        boolean z7 = SyncService.isDbMigrationSyncNeeded() || (this.mFlags & 2048) != 0;
        boolean z8 = zCanSyncRemoteContent && AmazonApplication.getCapabilities().isLyricsSupported() && (this.mFlags & 4096) != 0;
        boolean z9 = zCanSyncRemoteContent && AmazonApplication.getCapabilities().isRecentsSyncSupported() && (this.mFlags & 8192) != 0;
        if (AmazonApplication.getCapabilities().isCustomerProfileSupported()) {
            int i3 = this.mFlags & 32768;
        }
        boolean zHasAnyInternetConnection = ConnectivityUtil.hasAnyInternetConnection();
        boolean zShouldCloudBeSupported = AmazonApplication.getCapabilities().shouldCloudBeSupported();
        UserSubscriptionUtil.getUserSubscription();
        LinkedList<SyncOperation> linkedList = new LinkedList<>();
        linkedList.add(new CheckAndFixDatabaseContentOperation(context, db));
        if (!SyncService.isPreWidevineDBUpdateNeeded() || PreWidevineDBUpdateOperation.isAlreadyRunning()) {
            z = z6;
        } else {
            z = z6;
            linkedList.add(new PreWidevineDBUpdateOperation(context, db, new PreWidevineDBUpdateHelper(new DownloadStateUpdaterHelper(context, db), new PrimePlaylistDownloadStateUpdater(context, db)), new WidevineMigrationTracker(context, db)));
        }
        if (z4) {
            linkedList.add(new LocalTrackSyncOperation(context, db));
        }
        if (zHasAnyInternetConnection) {
            linkedList.add(new CtaSyncOperation(context, db));
        }
        if (zHasAnyInternetConnection && zShouldCloudBeSupported) {
            ContentAccess contentAccess = new ContentAccess(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider());
            boolean zIsAccessible = contentAccess.canInteractWithContent(ContentAccessType.PRIME).isAccessible();
            boolean zIsAccessible2 = contentAccess.canInteractWithContent(ContentAccessType.HAWKFIRE).isAccessible();
            if ((zIsAccessible || zIsAccessible2) && z5) {
                linkedList.add(new AddRemovePrimeSyncOperation(context, db));
                linkedList.add(new RetryCachedUnfollowPrimePlaylistSyncOperation(context, db));
            }
            if (z3) {
                if (isInitialSync(getContext()) || (AmazonApplication.getCapabilities().isLibrarySyncApiDialing() && UserSubscriptionUtil.getUserSubscription().isKatana() && isExplicitFullSyncForKatanaPending(context))) {
                    linkedList.add(new FullTrackSyncOperation(context, db));
                    if (isInitialSync(context)) {
                        CatalogStatusTiersOperation.setShouldPerformSync(context, false);
                    }
                } else if (CatalogStatusTiersOperation.shouldPerformFullSync(context)) {
                    linkedList.add(new FullTrackSyncOperation(context, db, false));
                }
                setExplicitFullSyncForKatanaDone(context);
                linkedList.add(new IncrementalTrackSyncOperation(context, db));
                linkedList.add(new UpdateDownloadedContentOperation(context, db, 1));
            }
            if (z2) {
                linkedList.add(new PlaylistSyncOperation(context, db, false));
            }
            if (z7) {
                linkedList.add(new DbMigrationOperation(context, db));
            }
            if (z9) {
                linkedList.add(new RecentlyPlayedSyncOperation(context, db));
            }
            if (z8) {
                linkedList.add(new LyricsSyncOperation(context));
            }
        }
        if (z) {
            linkedList.add(new RenormalizeSyncOperation(context, db));
        }
        linkedList.add(new PlaylistBrowseCleanOperation(context, db));
        if (isInitialSync(context)) {
            linkedList.add(new UpdateDownloadedContentOperation(context, db, 0));
        }
        if (CatalogStatusTiersOperation.shouldPerformFullSync(context) || CatalogStatusTiersOperation.shouldPerformContentCatalogStatusSync(context)) {
            linkedList.add(new CatalogStatusTiersOperation(context, db));
        }
        if (SettingsUtil.hasCompletedWidevineDatabaseUpdate(context) && !SettingsUtil.hasCompletedWidevineMigration(context)) {
            linkedList.add(new WidevineMigrationProgressUpdateOperation(context, db));
        }
        return linkedList;
    }

    private void broadcastSyncEvent(int eventType) {
        Intent syncEventImplicitIntent = getSyncEventImplicitIntent(eventType);
        Intent syncEventExplicitIntent = getSyncEventExplicitIntent(eventType);
        getContext().sendBroadcast(syncEventImplicitIntent);
        if (syncEventExplicitIntent != null) {
            getContext().sendBroadcast(syncEventExplicitIntent);
        }
    }

    private Intent getSyncEventExplicitIntent(int eventType) {
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        Intent syncEventImplicitIntent = getSyncEventImplicitIntent(eventType);
        syncEventImplicitIntent.setComponent(new ComponentName(getContext(), (Class<?>) AutoDownloadPurchaseReceiver.class));
        return syncEventImplicitIntent;
    }

    private Intent getSyncEventImplicitIntent(int eventType) {
        String stringExtra;
        Intent intent = new Intent("com.amazon.mp3.library.service.sync.ACTION_SYNC_EVENT");
        intent.putExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_EVENT_TYPE", eventType);
        intent.putExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_FLAGS", this.mFlags);
        Intent intent2 = this.mIntent;
        if (intent2 != null && (stringExtra = intent2.getStringExtra("com.amazon.mp3.library.service.sync.EXTRA_BROADCAST_CODE")) != null && stringExtra.length() > 0) {
            intent.putExtra("com.amazon.mp3.library.service.sync.EXTRA_BROADCAST_CODE", stringExtra);
        }
        return intent;
    }

    private void checkForLibrarySyncFinished(final int result, final int errorCount) {
        if (operationFinishedWithSuccess(result) || operationFinishedWithFailure(result, errorCount)) {
            getContext().sendBroadcast(new Intent("com.amazon.mp3.library.service.sync.ACTION_LIBRARY_SYNC_STOPPED"));
        }
    }

    static {
        HashMap<Class<?>, Integer> map = new HashMap<>();
        SYNC_OP_TO_STRING_MAP = map;
        map.put(FullTrackSyncOperation.class, Integer.valueOf(R.string.dmusic_sync_progress_notification_full));
        map.put(IncrementalTrackSyncOperation.class, Integer.valueOf(R.string.dmusic_sync_progress_notification_incremental));
        map.put(PlaylistSyncOperation.class, Integer.valueOf(R.string.dmusic_sync_progress_notification_playlists));
        Integer numValueOf = Integer.valueOf(R.string.dmusic_sync_progress_notification_local_songs);
        map.put(LocalTrackSyncOperation.class, numValueOf);
        map.put(DbMigrationOperation.class, numValueOf);
    }
}
