package com.amazon.avod.content;

import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentSessionFactory {
    ContentSession newContentSession(VideoSpecification videoSpecification, File file, ContentSessionType contentSessionType, AudioVideoUrls audioVideoUrls, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nonnull LifecycleProfiler lifecycleProfiler, @Nullable LiveWindowDuration liveWindowDuration, @Nullable String str, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface);

    ContentSession newSingleFileContentSession(VideoSpecification videoSpecification, File file, ContentSessionType contentSessionType, AudioVideoUrls audioVideoUrls, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, LifecycleProfiler lifecycleProfiler, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface);
}
