package com.amazon.avod.playback.renderer.visualon;

import android.content.Context;
import com.amazon.avod.drm.SoftwarePlayReadyDrmFramework;
import com.amazon.avod.drm.SoftwarePlayReadyNativeLibrary;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName;
import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.support.AvailabilityCode;
import com.amazon.avod.media.playback.support.AvailabilityStatus;
import com.amazon.avod.media.playback.support.DeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeOverrides;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VisualOnRendererScheme implements RendererScheme {
    static final String LICENSE_ASSET_FILENAME = "voVidDec.dat";
    private static final RendererSchemeType mRendererSchemeType = RendererSchemeType.VISUAL_ON;
    private AvailabilityStatus mAvailabilityStatus;
    private VisualOnDeviceCapabilityDetector mCapabilityDetector;
    private MediaSystemSharedContext mContext;
    private SoftwarePlayReadyDrmFramework mDrmFramework;
    private FileSystem mFileSystem;
    private PlaybackNativeLibrariesLoader mLibrariesLoader;
    private final VisualOnRendererType mRendererType;

    @Override // com.amazon.avod.media.playback.support.ResetObserver
    public void notifyReset() {
    }

    public VisualOnRendererScheme() {
        this(VisualOnRendererType.SOFTWARE);
    }

    public VisualOnRendererScheme(@Nonnull VisualOnRendererType visualOnRendererType) {
        this.mRendererType = (VisualOnRendererType) Preconditions.checkNotNull(visualOnRendererType, "rendererType");
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public AvailabilityStatus isAvailable(@Nonnull Context context) {
        try {
            this.mAvailabilityStatus = ImmutableSet.copyOf(context.getAssets().list("")).contains(LICENSE_ASSET_FILENAME) ? new AvailabilityStatus(AvailabilityCode.AVAILABLE, ThirdPartyProfileName.SD, false) : new AvailabilityStatus(AvailabilityCode.VISUAL_ON_LICENSE_ASSET_MISSING);
        } catch (IOException unused) {
            this.mAvailabilityStatus = new AvailabilityStatus(AvailabilityCode.VISUAL_ON_LICENSE_ASSET_MISSING);
        }
        return this.mAvailabilityStatus;
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    public void initialize(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaProfiler mediaProfiler, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull RendererSchemeOverrides rendererSchemeOverrides) {
        this.mContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "context");
        this.mFileSystem = (FileSystem) Preconditions.checkNotNull(fileSystem, "fileSystem");
        PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader2 = (PlaybackNativeLibrariesLoader) Preconditions.checkNotNull(playbackNativeLibrariesLoader, "librariesLoader");
        this.mLibrariesLoader = playbackNativeLibrariesLoader2;
        this.mCapabilityDetector = new VisualOnDeviceCapabilityDetector(playbackNativeLibrariesLoader2);
        SoftwarePlayReadyDrmFramework softwarePlayReadyDrmFramework = new SoftwarePlayReadyDrmFramework(mediaSystemSharedContext.getAppContext(), this.mFileSystem, this.mLibrariesLoader);
        this.mDrmFramework = softwarePlayReadyDrmFramework;
        softwarePlayReadyDrmFramework.initialize();
    }

    @Override // com.amazon.avod.media.playback.support.RendererScheme
    @Nonnull
    public Set<LoadableNativeLibrary> getNativeLibraries() {
        return ImmutableSet.of((SoftwarePlayReadyNativeLibrary) new VisualOnNativeLibrary(this.mContext), new SoftwarePlayReadyNativeLibrary());
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
        return new VisualOnRenderer(this.mContext, this.mRendererType, this.mFileSystem, this.mLibrariesLoader);
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
}
