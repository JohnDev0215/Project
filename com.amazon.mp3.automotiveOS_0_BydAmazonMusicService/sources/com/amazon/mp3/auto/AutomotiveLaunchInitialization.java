package com.amazon.mp3.auto;

import android.content.Context;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.det.PendingCrashLogs;
import com.amazon.mp3.det.PendingReports;
import com.amazon.mp3.init.HarleyTombstoneTask;
import com.amazon.mp3.init.InitializationTask;
import com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.health.AppHealthEventMonitor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: AutomotiveLaunchInitialization.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/auto/AutomotiveLaunchInitialization;", "Lcom/amazon/mp3/init/InitializationTask;", "context", "Landroid/content/Context;", "appHealthEventMonitor", "Lcom/amazon/mp3/util/health/AppHealthEventMonitor;", "(Landroid/content/Context;Lcom/amazon/mp3/util/health/AppHealthEventMonitor;)V", "getName", "", "getOnNextAction", "Lrx/functions/Action1;", "initialize", "", "subscribeToBootstrap", "uploadLogsForCrash", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutomotiveLaunchInitialization implements InitializationTask {
    private static final String TAG = AutomotiveLaunchInitialization.class.getSimpleName();
    private final AppHealthEventMonitor appHealthEventMonitor;
    private final Context context;

    @Override // com.amazon.mp3.init.InitializationTask
    public Action1<?> getOnNextAction() {
        return null;
    }

    public AutomotiveLaunchInitialization(Context context, AppHealthEventMonitor appHealthEventMonitor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appHealthEventMonitor, "appHealthEventMonitor");
        this.context = context;
        this.appHealthEventMonitor = appHealthEventMonitor;
    }

    @Override // com.amazon.mp3.init.InitializationTask
    public void initialize() {
        subscribeToBootstrap();
        StyleSheetProvider.initializeStyleSheet(this.context);
        this.appHealthEventMonitor.appOpened(ApplicationAttributes.getInstance(this.context).getVersionShort());
        SettingsUtil.setSfa(this.context, true);
        PendingReports.submitPendingReports(this.context);
    }

    @Override // com.amazon.mp3.init.InitializationTask
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    private final void subscribeToBootstrap() {
        BootstrapSingletonTask.get().registerUserObserver().subscribe(new Action1() { // from class: com.amazon.mp3.auto.AutomotiveLaunchInitialization$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AutomotiveLaunchInitialization.m249subscribeToBootstrap$lambda1(this.f$0, (Void) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.AutomotiveLaunchInitialization$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AutomotiveLaunchInitialization.m251subscribeToBootstrap$lambda2((Throwable) obj);
            }
        });
        HarleyTombstoneTask.uploadHarleyTombstones(this.context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToBootstrap$lambda-1, reason: not valid java name */
    public static final void m249subscribeToBootstrap$lambda1(final AutomotiveLaunchInitialization this$0, Void r2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new Thread(new Runnable() { // from class: com.amazon.mp3.auto.AutomotiveLaunchInitialization$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AutomotiveLaunchInitialization.m250subscribeToBootstrap$lambda1$lambda0(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToBootstrap$lambda-1$lambda-0, reason: not valid java name */
    public static final void m250subscribeToBootstrap$lambda1$lambda0(AutomotiveLaunchInitialization this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.uploadLogsForCrash(this$0.context);
        HardCodedRefreshStrategy.INSTANCE.tryRefreshHardCodeLimitCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToBootstrap$lambda-2, reason: not valid java name */
    public static final void m251subscribeToBootstrap$lambda2(Throwable th) {
        Log.warning(TAG, "BootstrapSingletonTask error. Could not complete initialization due to: %s", th);
    }

    private final void uploadLogsForCrash(Context context) {
        new PendingCrashLogs().uploadPendingCrashLogsIfRequired(context);
    }
}
