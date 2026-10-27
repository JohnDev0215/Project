package com.amazon.avod.media.playback.support;

import android.content.Context;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface RendererScheme extends ResetObserver {
    @Nonnull
    VideoRenderer createRenderer();

    @Nonnull
    AvailabilityStatus getAvailabilityStatus();

    @Nonnull
    DeviceCapabilityDetector getCapabilityDetector();

    @Nonnull
    DrmFramework getDrmFramework();

    @Nonnull
    Set<LoadableNativeLibrary> getNativeLibraries();

    @Nonnull
    RendererSchemeType getSchemeType();

    void initialize(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaProfiler mediaProfiler, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull RendererSchemeOverrides rendererSchemeOverrides);

    @Nonnull
    AvailabilityStatus isAvailable(@Nonnull Context context);
}
