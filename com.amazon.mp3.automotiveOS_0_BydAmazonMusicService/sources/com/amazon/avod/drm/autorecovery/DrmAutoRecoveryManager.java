package com.amazon.avod.drm.autorecovery;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.drm.reporting.DrmReporter;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.ResetObserver;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DrmAutoRecoveryManager {
    private final DrmAutoRecoveryPolicy mDrmAutoRecoveryPolicy;
    private final DrmFramework mDrmFramework;
    private final DrmReporter mDrmReporter;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final Object mResetObserverSetMutex;
    private final Set<ResetObserver> mResetObservers;

    public DrmAutoRecoveryManager(@Nonnull DrmFramework drmFramework, @Nonnull EventReporterFactory eventReporterFactory) {
        this(NetworkConnectionManager.getInstance(), DrmAutoRecoveryPolicy.getInstance(), drmFramework, new DrmReporter(eventReporterFactory, String.format(Locale.US, "AndroidDrmFramework_%s", UUID.randomUUID().toString())));
    }

    DrmAutoRecoveryManager(@Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull DrmAutoRecoveryPolicy drmAutoRecoveryPolicy, @Nonnull DrmFramework drmFramework, @Nonnull DrmReporter drmReporter) {
        this.mResetObservers = Sets.newHashSet();
        this.mResetObserverSetMutex = new Object();
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mDrmAutoRecoveryPolicy = (DrmAutoRecoveryPolicy) Preconditions.checkNotNull(drmAutoRecoveryPolicy, "drmAutoRecoveryPolicy");
        this.mDrmFramework = (DrmFramework) Preconditions.checkNotNull(drmFramework, "drmFramework");
        this.mDrmReporter = (DrmReporter) Preconditions.checkNotNull(drmReporter, "DrmReporter");
    }

    public void addResetObserver(@Nonnull ResetObserver resetObserver) {
        Preconditions.checkNotNull(resetObserver, "resetObserver");
        synchronized (this.mResetObserverSetMutex) {
            this.mResetObservers.add(resetObserver);
        }
    }

    public DrmLicensingException handleLicensingException(@Nonnull DrmLicensingException drmLicensingException) {
        MediaException mediaExceptionHandleMediaException = handleMediaException(drmLicensingException);
        if (mediaExceptionHandleMediaException instanceof DrmLicensingException) {
            return (DrmLicensingException) mediaExceptionHandleMediaException;
        }
        return new DrmLicensingException(LicenseError.UNKNOWN_PLAYREADY_ERROR, mediaExceptionHandleMediaException);
    }

    public MediaException handleMediaException(@Nonnull MediaException mediaException) {
        if (!this.mDrmAutoRecoveryPolicy.getDrmAutoRecoveryEnabled(this.mNetworkConnectionManager.hasDataConnection()) || !this.mDrmAutoRecoveryPolicy.shouldHandle(mediaException)) {
            return mediaException;
        }
        this.mDrmAutoRecoveryPolicy.notifyException(mediaException);
        if (!this.mDrmAutoRecoveryPolicy.shouldReset()) {
            return mediaException;
        }
        this.mDrmReporter.reportRetriedLicensingException(mediaException);
        executeAutoReset(mediaException);
        return new DrmLicensingException(LicenseError.AIV_PLAYREADY_DRM_AUTO_RESET, "Drm System was in a bad state and was reset");
    }

    private void executeAutoReset(@Nonnull MediaException mediaException) {
        DLog.warnf("Executing Drm Auto Reset, DRM subsystem will be reset and all the licenses will be deleted and reacquired");
        this.mDrmAutoRecoveryPolicy.incrementAutoResetCount();
        this.mDrmReporter.reportDrmReset(mediaException);
        Profiler.reportCounterMetric(new SimpleCounterMetric("DrmReset-Attempt"));
        try {
            this.mDrmFramework.enableDrmClientCalls();
            this.mDrmFramework.removeRights("playready://AllLicenses.playready", "dummyOfflineKeyId", DrmScheme.PLAYREADY);
            Profiler.reportCounterMetric(new SimpleCounterMetric("DrmReset-Success"));
        } catch (DrmLicensingException e) {
            Profiler.reportCounterMetric(new SimpleCounterMetric("DrmReset-Failure"));
            this.mDrmReporter.reportDrmResetFailure(e);
        }
        synchronized (this.mResetObserverSetMutex) {
            for (ResetObserver resetObserver : this.mResetObservers) {
                DLog.logf("Notifying observer %s of drm auto-reset", resetObserver.getClass().getSimpleName());
                resetObserver.notifyReset();
            }
        }
    }
}
