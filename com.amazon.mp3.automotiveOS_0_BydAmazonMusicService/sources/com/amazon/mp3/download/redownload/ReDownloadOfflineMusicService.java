package com.amazon.mp3.download.redownload;

import android.content.Context;
import android.content.Intent;
import androidx.core.app.JobIntentService;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.ConnectivityBroadcastReceiver;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.redownload.ReDownloadOfflineMusicService;
import com.amazon.mp3.download.widevineMigration.WidevineMigrationTrackerMetrics;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RedownloadOfflineMusicService.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/download/redownload/ReDownloadOfflineMusicService;", "Landroidx/core/app/JobIntentService;", "()V", "onDestroy", "", "onHandleWork", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ReDownloadOfflineMusicService extends JobIntentService {
    private static boolean isQueued;
    private static ConnectivityBroadcastReceiver receiver;
    private static ReDownloadOfflineMusicService serviceInstance;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RedownloadOfflineMusicOperation redownloadOfflineMusicOperation = new RedownloadOfflineMusicOperation();
    private static final String TAG = ReDownloadOfflineMusicService.class.getSimpleName();

    public ReDownloadOfflineMusicService() {
        serviceInstance = this;
        Log.debug(TAG, "initializing redownload service");
    }

    /* JADX INFO: compiled from: RedownloadOfflineMusicService.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0012\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\nH\u0007J\u0010\u0010\u0017\u001a\u00020\u00122\b\b\u0002\u0010\u0018\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/download/redownload/ReDownloadOfflineMusicService$Companion;", "", "()V", "JOB_ID", "", "SHOULD_SEND_SERVICE_INITIATED_EVENT", "", "TAG", "kotlin.jvm.PlatformType", "isQueued", "", "receiver", "Lcom/amazon/mp3/download/ConnectivityBroadcastReceiver;", "redownloadOfflineMusicOperation", "Lcom/amazon/mp3/download/redownload/RedownloadOfflineMusicOperation;", "serviceInstance", "Lcom/amazon/mp3/download/redownload/ReDownloadOfflineMusicService;", "cancel", "", "context", "Landroid/content/Context;", "queueOrStart", "isUserInitialized", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "sendServiceInitatedMetric", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(boolean sendServiceInitatedMetric) {
            Context context = AmazonApplication.getContext();
            Intent intent = new Intent(context, (Class<?>) ReDownloadOfflineMusicService.class);
            intent.putExtra("SHOULD_SEND_SERVICE_INITIATED_EVENT", sendServiceInitatedMetric);
            JobIntentService.enqueueWork(context, (Class<?>) ReDownloadOfflineMusicService.class, 1000, intent);
        }

        public static /* synthetic */ void queueOrStart$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = true;
            }
            companion.queueOrStart(z);
        }

        @JvmOverloads
        public final synchronized void queueOrStart(boolean isUserInitialized) {
            Context context = AmazonApplication.getContext();
            Boolean boolIsDownloadOnWifiOnly = MusicDownloader.getInstance(context).isDownloadOnWifiOnly();
            Intrinsics.checkNotNullExpressionValue(boolIsDownloadOnWifiOnly, "getInstance(context).isDownloadOnWifiOnly");
            boolean zBooleanValue = boolIsDownloadOnWifiOnly.booleanValue();
            boolean zIsWifiConnected = ConnectivityUtil.isWifiConnected(context);
            boolean zHasAnyInternetConnection = ConnectivityUtil.hasAnyInternetConnection(context);
            boolean z = ReDownloadOfflineMusicService.serviceInstance == null;
            if (ReDownloadOfflineMusicService.isQueued) {
                Log.debug(ReDownloadOfflineMusicService.TAG, "Redownload service already queued, canceling");
                Intrinsics.checkNotNullExpressionValue(context, "context");
                cancel(context);
            }
            if (MusicDownloader.getInstance(context).hasSuitableConnection(isUserInitialized)) {
                Log.debug(ReDownloadOfflineMusicService.TAG, "Starting redownload service");
                start(z);
            } else {
                Log.debug(ReDownloadOfflineMusicService.TAG, "Queuing redownload service. isWifiOnly = " + zBooleanValue + ", wifiConnected = " + zIsWifiConnected + ", hasAnyInternetConnection = " + zHasAnyInternetConnection + ", isUserInitialized = " + isUserInitialized);
                if (ReDownloadOfflineMusicService.serviceInstance == null) {
                    ReDownloadOfflineMusicService.isQueued = true;
                    ReDownloadOfflineMusicService.receiver = new ConnectivityBroadcastReceiver(isUserInitialized, new Function0<Unit>() { // from class: com.amazon.mp3.download.redownload.ReDownloadOfflineMusicService$Companion$queueOrStart$1
                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            ReDownloadOfflineMusicService.Companion.queueOrStart$default(ReDownloadOfflineMusicService.INSTANCE, false, 1, null);
                            ReDownloadOfflineMusicService.receiver = null;
                        }
                    });
                    ConnectivityBroadcastReceiver connectivityBroadcastReceiver = ReDownloadOfflineMusicService.receiver;
                    if (connectivityBroadcastReceiver != null) {
                        connectivityBroadcastReceiver.register();
                    }
                    WidevineMigrationTrackerMetrics.INSTANCE.sendMigrationQueued();
                }
            }
        }

        public final synchronized void cancel(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            ConnectivityBroadcastReceiver connectivityBroadcastReceiver = ReDownloadOfflineMusicService.receiver;
            if (connectivityBroadcastReceiver != null) {
                connectivityBroadcastReceiver.unregister();
            }
            ReDownloadOfflineMusicService.receiver = null;
            ReDownloadOfflineMusicService.isQueued = false;
            ReDownloadOfflineMusicService.redownloadOfflineMusicOperation.cancel(context);
            ReDownloadOfflineMusicService reDownloadOfflineMusicService = ReDownloadOfflineMusicService.serviceInstance;
            if (reDownloadOfflineMusicService != null) {
                reDownloadOfflineMusicService.stopSelf();
            }
            ReDownloadOfflineMusicService.serviceInstance = null;
        }
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        String str = TAG;
        Log.debug(str, "Started issuing redownload commands for re-downloads.");
        SettingsUtil.setBooleanPref(this, R.string.setting_key_redownload_started, true);
        boolean booleanExtra = intent.getBooleanExtra("SHOULD_SEND_SERVICE_INITIATED_EVENT", false);
        RedownloadOfflineMusicOperation redownloadOfflineMusicOperation2 = redownloadOfflineMusicOperation;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        Log.debug(str, Intrinsics.stringPlus("Finished issuing redownload commands for re-downloads with result ", redownloadOfflineMusicOperation2.redownload(applicationContext, booleanExtra)));
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onDestroy() {
        Log.debug(TAG, "Destroying Service");
        ConnectivityBroadcastReceiver connectivityBroadcastReceiver = receiver;
        if (connectivityBroadcastReceiver != null) {
            connectivityBroadcastReceiver.unregister();
        }
        receiver = null;
        isQueued = false;
        serviceInstance = null;
        super.onDestroy();
    }
}
