package com.amazon.video.sdk.player;

import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoTypeUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\u001a\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0004H\u0000\"*\u0010\u0000\u001a\u001e\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00040\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"PLAYERS_CONTENT_TYPES", "Lcom/google/common/collect/ImmutableBiMap;", "Lcom/amazon/avod/media/playback/ContentType;", "kotlin.jvm.PlatformType", "Lcom/amazon/video/sdk/player/VideoType;", "PLAYERS_CONTENT_TYPES_INVERSE", "", "toPlayersContentType", "videoType", "AmazonAndroidVideoPlayer_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class VideoTypeUtilsKt {
    private static final ImmutableBiMap<ContentType, VideoType> PLAYERS_CONTENT_TYPES;
    private static final Map<VideoType, ContentType> PLAYERS_CONTENT_TYPES_INVERSE;

    static {
        Map mapCheckFullKeyMappingWithBlacklist = Preconditions2.checkFullKeyMappingWithBlacklist(ContentType.class, ImmutableBiMap.of(ContentType.Feature, VideoType.VOD, ContentType.Trailer, VideoType.Trailer, ContentType.LiveStreaming, VideoType.Live), ImmutableSet.of(ContentType.External));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMappingWithBlacklist, "checkFullKeyMappingWithB…ntentType.External)\n    )");
        ImmutableBiMap<ContentType, VideoType> immutableBiMap = (ImmutableBiMap) mapCheckFullKeyMappingWithBlacklist;
        PLAYERS_CONTENT_TYPES = immutableBiMap;
        ImmutableMap immutableMapBuild = ImmutableMap.builder().putAll(immutableBiMap.inverse()).build();
        Intrinsics.checkNotNullExpressionValue(immutableMapBuild, "builder<VideoType, Conte…S.inverse())\n    .build()");
        PLAYERS_CONTENT_TYPES_INVERSE = immutableMapBuild;
    }

    public static final ContentType toPlayersContentType(VideoType videoType) {
        Intrinsics.checkNotNullParameter(videoType, "videoType");
        Preconditions.checkNotNull(videoType, "videoType", new Object[0]);
        ContentType contentType = PLAYERS_CONTENT_TYPES_INVERSE.get(videoType);
        if (contentType == null) {
            contentType = ContentType.Feature;
        }
        return contentType;
    }
}
