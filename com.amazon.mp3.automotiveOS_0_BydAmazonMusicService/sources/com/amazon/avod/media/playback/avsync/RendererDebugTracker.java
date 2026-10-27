package com.amazon.avod.media.playback.avsync;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.JSONUtils;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RendererDebugTracker {
    private final TimeSpan mAVSyncThreshold;
    private Long mAudioSamplePTSInUs;
    private final Map<String, Set<String>> mDebugInformation;
    private final ExecutorService mExecutorService;
    private Long mVideoSamplePTSInUs;

    public RendererDebugTracker() {
        this(new HashMap(), MediaDefaultConfiguration.getInstance().getAVSyncThreshold(), ExecutorBuilder.newBuilderFor(RendererDebugTracker.class, new String[0]).withFixedThreadPoolSize(1).allowCoreThreadExpiry(100L, TimeUnit.MILLISECONDS).build());
    }

    RendererDebugTracker(@Nonnull Map<String, Set<String>> map, @Nonnull TimeSpan timeSpan, @Nonnull ExecutorService executorService) {
        this.mDebugInformation = (Map) Preconditions.checkNotNull(map, "debugInformation");
        this.mAVSyncThreshold = (TimeSpan) Preconditions.checkNotNull(timeSpan, "avSyncThreshold");
        this.mExecutorService = (ExecutorService) Preconditions.checkNotNull(executorService, "mExecutorService");
    }

    public void clearPTS() {
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.avod.media.playback.avsync.RendererDebugTracker.1
            @Override // java.lang.Runnable
            public void run() {
                RendererDebugTracker.this.mAudioSamplePTSInUs = null;
                RendererDebugTracker.this.mVideoSamplePTSInUs = null;
            }
        });
    }

    public void updateFirstSampleInStreamPTS(final boolean z, final long j) {
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.avod.media.playback.avsync.RendererDebugTracker.2
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    RendererDebugTracker.this.mAudioSamplePTSInUs = Long.valueOf(j);
                } else {
                    RendererDebugTracker.this.mVideoSamplePTSInUs = Long.valueOf(j);
                }
                if (RendererDebugTracker.this.mAudioSamplePTSInUs == null || RendererDebugTracker.this.mVideoSamplePTSInUs == null) {
                    return;
                }
                if (RendererDebugTracker.this.mAVSyncThreshold.getTotalMicroseconds() < Math.abs(RendererDebugTracker.this.mAudioSamplePTSInUs.longValue() - RendererDebugTracker.this.mVideoSamplePTSInUs.longValue())) {
                    RendererDebugTracker.this.updateDebugInfo(RendererDebugInformationType.AVPTSMisMatch.name(), String.format("APTSUs:%d, VPTSUs:%d", RendererDebugTracker.this.mAudioSamplePTSInUs, RendererDebugTracker.this.mVideoSamplePTSInUs));
                    DLog.devf("RendererDebugTracker Info AudioSamplePTSInUs:%d, VideoSamplePTSInUs:%d, DiffInUs:%d", RendererDebugTracker.this.mAudioSamplePTSInUs, RendererDebugTracker.this.mVideoSamplePTSInUs, Long.valueOf(Math.abs(RendererDebugTracker.this.mAudioSamplePTSInUs.longValue() - RendererDebugTracker.this.mVideoSamplePTSInUs.longValue())));
                }
                RendererDebugTracker.this.clearPTS();
            }
        });
    }

    public void updateDebugInfo(@Nonnull final String str, @Nonnull final String str2) {
        Preconditions.checkNotNull(str, "debugInfoType");
        Preconditions.checkNotNull(str2, "debugInformation");
        this.mExecutorService.execute(new Runnable() { // from class: com.amazon.avod.media.playback.avsync.RendererDebugTracker.3
            @Override // java.lang.Runnable
            public void run() {
                RendererDebugTracker.this.updateDebugInformation(str, str2);
            }
        });
    }

    void updateDebugInformation(@Nonnull String str, @Nonnull String str2) {
        Set<String> hashSet;
        if (!this.mDebugInformation.containsKey(str)) {
            hashSet = new HashSet<>();
        } else {
            hashSet = this.mDebugInformation.get(str);
        }
        hashSet.add(str2);
        this.mDebugInformation.put(str, hashSet);
    }

    @Nonnull
    public String getRendererDebugInfo() {
        try {
            return (String) this.mExecutorService.submit(new GetRendererDebugTrackerInfo(this.mDebugInformation)).get();
        } catch (InterruptedException | ExecutionException e) {
            DLog.devf("Unable to parse JSON string from RendererDebugTracker.GetRendererDebugTrackerInfo: %s", e);
            return "";
        }
    }

    static class GetRendererDebugTrackerInfo implements Callable<String> {
        private final Map<String, Set<String>> mDebugInformation;

        GetRendererDebugTrackerInfo(@Nonnull Map<String, Set<String>> map) {
            this.mDebugInformation = (Map) Preconditions.checkNotNull(map, "debugInformation");
        }

        @Override // java.util.concurrent.Callable
        @Nonnull
        public String call() {
            if (this.mDebugInformation.size() <= 0) {
                return "";
            }
            String mapAsJsonString = JSONUtils.getMapAsJsonString(this.mDebugInformation);
            this.mDebugInformation.clear();
            return mapAsJsonString;
        }
    }
}
