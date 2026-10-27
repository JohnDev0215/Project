package com.amazon.avod.media.drm;

import android.content.Context;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.support.AvailabilityCode;
import com.amazon.avod.media.playback.support.AvailabilityStatus;
import com.amazon.avod.media.playback.support.RendererSchemeResolverConfig;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MediaDrmSanityEvaluator {
    @Nonnull
    public static AvailabilityStatus evaluateDrmSanity(@Nonnull DrmScheme drmScheme, @Nonnull Context context) {
        AvailabilityStatus availabilityStatus;
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        Preconditions.checkNotNull(context, "context");
        if (!RendererSchemeResolverConfig.getInstance().isMediaDrmSanityEvaluatorEnabled()) {
            DLog.logf("Skipping Drm sanity evaluation as it is disabled by server config.");
            return new AvailabilityStatus(AvailabilityCode.AVAILABLE);
        }
        if (RendererSchemeResolverConfig.getInstance().isEmulator()) {
            DLog.logf("Skipping Drm sanity evaluation as the underlying platform is an emulator.");
            return new AvailabilityStatus(AvailabilityCode.AVAILABLE);
        }
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorBuild = ScheduledExecutorBuilder.newBuilderFor(MediaDrmSanityEvaluator.class, new String[0]).withProfilerTraceLevel(Profiler.TraceLevel.VERBOSE).withFixedThreadPoolSize(1).build();
        try {
            try {
                availabilityStatus = (AvailabilityStatus) scheduledThreadPoolExecutorBuild.submit(new DrmSanityEvaluationTask(drmScheme, context)).get(MediaDefaultConfiguration.getInstance().getSanityEvaluationTimeoutMillis(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                AvailabilityStatus availabilityStatus2 = new AvailabilityStatus(AvailabilityCode.DRM_SANITY_TIMEOUT);
                DLog.warnf("MediaDrm %s sanity check timed out due to: %s", drmScheme, e);
                scheduledThreadPoolExecutorBuild.shutdown();
                availabilityStatus = availabilityStatus2;
            }
            return availabilityStatus;
        } finally {
            scheduledThreadPoolExecutorBuild.shutdown();
        }
    }

    private static class DrmSanityEvaluationTask implements Callable<AvailabilityStatus> {
        private final Context mContext;
        private final DrmScheme mDrmScheme;

        private DrmSanityEvaluationTask(@Nonnull DrmScheme drmScheme, @Nonnull Context context) {
            this.mDrmScheme = drmScheme;
            this.mContext = context;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:79:0x015d  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x018b  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01a4  */
        @Override // java.util.concurrent.Callable
        @javax.annotation.Nonnull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.amazon.avod.media.playback.support.AvailabilityStatus call() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 442
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.drm.MediaDrmSanityEvaluator.DrmSanityEvaluationTask.call():com.amazon.avod.media.playback.support.AvailabilityStatus");
        }
    }
}
