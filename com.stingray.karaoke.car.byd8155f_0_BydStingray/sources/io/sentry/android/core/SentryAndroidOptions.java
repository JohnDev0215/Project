package io.sentry.android.core;

import io.sentry.C1489n1;
import io.sentry.F1;

/* JADX INFO: loaded from: classes.dex */
public final class SentryAndroidOptions extends F1 {
    private boolean anrEnabled;
    private boolean anrReportInDebug;
    private long anrTimeoutIntervalMillis;
    private boolean attachAnrThreadDump;
    private boolean attachScreenshot;
    private boolean attachViewHierarchy;
    private X beforeScreenshotCaptureCallback;
    private X beforeViewHierarchyCaptureCallback;
    private boolean collectAdditionalContext;
    private J debugImagesLoader;
    private boolean enableActivityLifecycleBreadcrumbs;
    private boolean enableActivityLifecycleTracingAutoFinish;
    private boolean enableAppComponentBreadcrumbs;
    private boolean enableAppLifecycleBreadcrumbs;
    private boolean enableAutoActivityLifecycleTracing;
    private boolean enableFramesTracking;
    private boolean enableNdk;
    private boolean enableNetworkEventBreadcrumbs;
    private boolean enablePerformanceV2;
    private boolean enableRootCheck;
    private boolean enableScopeSync;
    private boolean enableSystemEventBreadcrumbs;
    private io.sentry.android.core.internal.util.l frameMetricsCollector;
    private String nativeSdkName;
    private boolean reportHistoricalAnrs;
    private final long startupCrashDurationThresholdMillis;
    private long startupCrashFlushTimeoutMillis;

    public SentryAndroidOptions() {
        super(false);
        this.anrEnabled = true;
        this.anrTimeoutIntervalMillis = 5000L;
        this.anrReportInDebug = false;
        this.enableActivityLifecycleBreadcrumbs = true;
        this.enableAppLifecycleBreadcrumbs = true;
        this.enableSystemEventBreadcrumbs = true;
        this.enableAppComponentBreadcrumbs = true;
        this.enableNetworkEventBreadcrumbs = true;
        this.enableAutoActivityLifecycleTracing = true;
        this.enableActivityLifecycleTracingAutoFinish = true;
        this.debugImagesLoader = P.l;
        this.collectAdditionalContext = true;
        this.startupCrashFlushTimeoutMillis = 5000L;
        this.startupCrashDurationThresholdMillis = 2000L;
        this.enableFramesTracking = true;
        this.nativeSdkName = null;
        this.enableRootCheck = true;
        this.enableNdk = true;
        this.enableScopeSync = true;
        this.reportHistoricalAnrs = false;
        this.attachAnrThreadDump = false;
        this.enablePerformanceV2 = false;
        setSentryClientName("sentry.java.android/7.18.0");
        setSdkVersion(createSdkVersion());
        setAttachServerName(false);
    }

    private io.sentry.protocol.r createSdkVersion() {
        io.sentry.protocol.r sdkVersion = getSdkVersion();
        if (sdkVersion == null) {
            sdkVersion = new io.sentry.protocol.r("sentry.java.android", "7.18.0");
        } else {
            sdkVersion.l = "sentry.java.android";
            sdkVersion.f16766m = "7.18.0";
        }
        C1489n1.s().g("maven:io.sentry:sentry-android-core");
        return sdkVersion;
    }

    public void enableAllAutoBreadcrumbs(boolean z6) {
        this.enableActivityLifecycleBreadcrumbs = z6;
        this.enableAppComponentBreadcrumbs = z6;
        this.enableSystemEventBreadcrumbs = z6;
        this.enableAppLifecycleBreadcrumbs = z6;
        this.enableNetworkEventBreadcrumbs = z6;
        setEnableUserInteractionBreadcrumbs(z6);
    }

    public long getAnrTimeoutIntervalMillis() {
        return this.anrTimeoutIntervalMillis;
    }

    public X getBeforeScreenshotCaptureCallback() {
        return null;
    }

    public X getBeforeViewHierarchyCaptureCallback() {
        return null;
    }

    public J getDebugImagesLoader() {
        return this.debugImagesLoader;
    }

    public io.sentry.android.core.internal.util.l getFrameMetricsCollector() {
        return this.frameMetricsCollector;
    }

    public String getNativeSdkName() {
        return this.nativeSdkName;
    }

    @Deprecated
    public int getProfilingTracesIntervalMillis() {
        return 0;
    }

    public long getStartupCrashDurationThresholdMillis() {
        return 2000L;
    }

    public long getStartupCrashFlushTimeoutMillis() {
        return this.startupCrashFlushTimeoutMillis;
    }

    public boolean isAnrEnabled() {
        return this.anrEnabled;
    }

    public boolean isAnrReportInDebug() {
        return this.anrReportInDebug;
    }

    public boolean isAttachAnrThreadDump() {
        return this.attachAnrThreadDump;
    }

    public boolean isAttachScreenshot() {
        return this.attachScreenshot;
    }

    public boolean isAttachViewHierarchy() {
        return this.attachViewHierarchy;
    }

    public boolean isCollectAdditionalContext() {
        return this.collectAdditionalContext;
    }

    public boolean isEnableActivityLifecycleBreadcrumbs() {
        return this.enableActivityLifecycleBreadcrumbs;
    }

    public boolean isEnableActivityLifecycleTracingAutoFinish() {
        return this.enableActivityLifecycleTracingAutoFinish;
    }

    public boolean isEnableAppComponentBreadcrumbs() {
        return this.enableAppComponentBreadcrumbs;
    }

    public boolean isEnableAppLifecycleBreadcrumbs() {
        return this.enableAppLifecycleBreadcrumbs;
    }

    public boolean isEnableAutoActivityLifecycleTracing() {
        return this.enableAutoActivityLifecycleTracing;
    }

    public boolean isEnableFramesTracking() {
        return this.enableFramesTracking;
    }

    public boolean isEnableNdk() {
        return this.enableNdk;
    }

    public boolean isEnableNetworkEventBreadcrumbs() {
        return this.enableNetworkEventBreadcrumbs;
    }

    public boolean isEnablePerformanceV2() {
        return this.enablePerformanceV2;
    }

    public boolean isEnableRootCheck() {
        return this.enableRootCheck;
    }

    public boolean isEnableScopeSync() {
        return this.enableScopeSync;
    }

    public boolean isEnableSystemEventBreadcrumbs() {
        return this.enableSystemEventBreadcrumbs;
    }

    public boolean isReportHistoricalAnrs() {
        return this.reportHistoricalAnrs;
    }

    public void setAnrEnabled(boolean z6) {
        this.anrEnabled = z6;
    }

    public void setAnrReportInDebug(boolean z6) {
        this.anrReportInDebug = z6;
    }

    public void setAnrTimeoutIntervalMillis(long j3) {
        this.anrTimeoutIntervalMillis = j3;
    }

    public void setAttachAnrThreadDump(boolean z6) {
        this.attachAnrThreadDump = z6;
    }

    public void setAttachScreenshot(boolean z6) {
        this.attachScreenshot = z6;
    }

    public void setAttachViewHierarchy(boolean z6) {
        this.attachViewHierarchy = z6;
    }

    public void setBeforeScreenshotCaptureCallback(X x8) {
    }

    public void setBeforeViewHierarchyCaptureCallback(X x8) {
    }

    public void setCollectAdditionalContext(boolean z6) {
        this.collectAdditionalContext = z6;
    }

    public void setDebugImagesLoader(J j3) {
        if (j3 == null) {
            j3 = P.l;
        }
        this.debugImagesLoader = j3;
    }

    public void setEnableActivityLifecycleBreadcrumbs(boolean z6) {
        this.enableActivityLifecycleBreadcrumbs = z6;
    }

    public void setEnableActivityLifecycleTracingAutoFinish(boolean z6) {
        this.enableActivityLifecycleTracingAutoFinish = z6;
    }

    public void setEnableAppComponentBreadcrumbs(boolean z6) {
        this.enableAppComponentBreadcrumbs = z6;
    }

    public void setEnableAppLifecycleBreadcrumbs(boolean z6) {
        this.enableAppLifecycleBreadcrumbs = z6;
    }

    public void setEnableAutoActivityLifecycleTracing(boolean z6) {
        this.enableAutoActivityLifecycleTracing = z6;
    }

    public void setEnableFramesTracking(boolean z6) {
        this.enableFramesTracking = z6;
    }

    public void setEnableNdk(boolean z6) {
        this.enableNdk = z6;
    }

    public void setEnableNetworkEventBreadcrumbs(boolean z6) {
        this.enableNetworkEventBreadcrumbs = z6;
    }

    public void setEnablePerformanceV2(boolean z6) {
        this.enablePerformanceV2 = z6;
    }

    public void setEnableRootCheck(boolean z6) {
        this.enableRootCheck = z6;
    }

    public void setEnableScopeSync(boolean z6) {
        this.enableScopeSync = z6;
    }

    public void setEnableSystemEventBreadcrumbs(boolean z6) {
        this.enableSystemEventBreadcrumbs = z6;
    }

    public void setFrameMetricsCollector(io.sentry.android.core.internal.util.l lVar) {
        this.frameMetricsCollector = lVar;
    }

    public void setNativeSdkName(String str) {
        this.nativeSdkName = str;
    }

    @Deprecated
    public void setProfilingTracesIntervalMillis(int i7) {
    }

    public void setReportHistoricalAnrs(boolean z6) {
        this.reportHistoricalAnrs = z6;
    }

    public void setStartupCrashFlushTimeoutMillis(long j3) {
        this.startupCrashFlushTimeoutMillis = j3;
    }
}
