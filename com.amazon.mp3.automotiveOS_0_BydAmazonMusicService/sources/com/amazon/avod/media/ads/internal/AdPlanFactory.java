package com.amazon.avod.media.ads.internal;

import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playbackresource.PlaybackResources;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface AdPlanFactory {
    AdPlanResult newAdPlan(@Nonnull VideoSpecification videoSpecification, @Nonnull Map<String, String> map, @Nonnull String str, @Nonnull PlaybackEventReporter playbackEventReporter, @Nullable String str2, @Nullable PlaybackResources playbackResources, @Nullable CuepointPlaylist cuepointPlaylist, @Nullable RendererSchemeType rendererSchemeType) throws MediaException;
}
