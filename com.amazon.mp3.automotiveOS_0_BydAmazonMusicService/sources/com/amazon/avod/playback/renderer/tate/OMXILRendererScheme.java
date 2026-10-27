package com.amazon.avod.playback.renderer.tate;

import android.content.Context;
import com.amazon.avod.drm.AndroidDrmFramework;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.support.AvailabilityCode;
import com.amazon.avod.media.playback.support.AvailabilityStatus;
import com.amazon.avod.media.playback.support.DefaultDeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.DeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeOverrides;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class OMXILRendererScheme implements RendererScheme {
    private static final RendererSchemeType mRendererSchemeType = RendererSchemeType.OMXIL;
    private AvailabilityStatus mAvailabilityStatus;
    private DeviceCapabilityDetector mCapabilityDetector;
    private MediaSystemSharedContext mContext;
    private AndroidDrmFramework mDrmFramework;
    private final Set<LoadableNativeLibrary> mNativeLibraries = ImmutableSet.of(new OMXILNativeLibrary());
    private OMXILRenderer mRenderer = null;
    private final PlaybackConfig mPlaybackConfig = PlaybackConfig.INSTANCE;

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public AvailabilityStatus isAvailable(@Nonnull Context context) {
        AvailabilityStatus availabilityStatus = new AvailabilityStatus(AvailabilityCode.AVAILABLE);
        this.mAvailabilityStatus = availabilityStatus;
        return availabilityStatus;
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    public void initialize(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaProfiler mediaProfiler, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull RendererSchemeOverrides rendererSchemeOverrides) {
        MediaSystemSharedContext mediaSystemSharedContext2 = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "context");
        this.mContext = mediaSystemSharedContext2;
        this.mDrmFramework = new AndroidDrmFramework(mediaSystemSharedContext2.getAppContext(), mediaProfiler, this.mContext.getExecutorService());
        this.mCapabilityDetector = new DefaultDeviceCapabilityDetector(mediaSystemSharedContext);
        this.mDrmFramework.initialize();
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public Set<LoadableNativeLibrary> getNativeLibraries() {
        return this.mNativeLibraries;
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public DeviceCapabilityDetector getCapabilityDetector() {
        return this.mCapabilityDetector;
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public DrmFramework getDrmFramework() {
        return this.mDrmFramework;
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public VideoRenderer createRenderer() {
        if (!this.mPlaybackConfig.isRendererReuseEnabled() || this.mRenderer == null) {
            DLog.logf("creating a new renderer instance");
            this.mRenderer = new OMXILRenderer(this.mContext);
        } else {
            DLog.logf("Reusing a previously created renderer instance");
        }
        return this.mRenderer;
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public RendererSchemeType getSchemeType() {
        return mRendererSchemeType;
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public AvailabilityStatus getAvailabilityStatus() {
        Preconditions.checkState(this.mAvailabilityStatus != null, "Must call isAvailable() before calling getAvailabilityStatus()");
        return this.mAvailabilityStatus;
    }

    @Override // com.amazon.avod.media.playback.support.ResetObserver
    public void notifyReset() {
        DLog.warnf("Invalidating OMXILRenderer singleton state.");
        OMXILRenderer.invalidateOMXILState();
    }
}
