package com.amazon.avod.content.urlvending;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentUrlFetcher {
    @Nonnull
    AudioVideoUrls getAudioVideoUrls(@Nonnull VideoSpecification videoSpecification, @Nonnull Map<String, String> map, @Nonnull ContentSessionType contentSessionType, @Nullable String str, @Nullable PlaybackEventReporter playbackEventReporter, @Nullable RendererSchemeType rendererSchemeType, @Nonnull RendererScheme rendererScheme, boolean z) throws ContentException;
}
