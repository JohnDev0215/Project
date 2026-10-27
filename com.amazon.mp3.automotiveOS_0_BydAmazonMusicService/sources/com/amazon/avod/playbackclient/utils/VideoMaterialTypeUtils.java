package com.amazon.avod.playbackclient.utils;

import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VideoMaterialTypeUtils {
    private static final ImmutableBiMap<ContentType, VideoMaterialType> PLAYERS_CONTENT_TYPES;
    private static final Map<VideoMaterialType, ContentType> PLAYERS_CONTENT_TYPES_INVERSE;

    static {
        ContentType contentType = ContentType.Trailer;
        VideoMaterialType videoMaterialType = VideoMaterialType.Trailer;
        ContentType contentType2 = ContentType.Feature;
        ImmutableBiMap<ContentType, VideoMaterialType> immutableBiMap = (ImmutableBiMap) Preconditions2.checkFullKeyMapping(ContentType.class, ImmutableBiMap.of(contentType, videoMaterialType, contentType2, VideoMaterialType.Feature, ContentType.LiveStreaming, VideoMaterialType.LiveStreaming, ContentType.External, VideoMaterialType.External));
        PLAYERS_CONTENT_TYPES = immutableBiMap;
        PLAYERS_CONTENT_TYPES_INVERSE = ImmutableMap.builder().putAll(immutableBiMap.inverse()).put(VideoMaterialType.ValueAdded, contentType2).build();
    }

    public static boolean isLive(@Nonnull VideoMaterialType videoMaterialType) {
        Preconditions.checkNotNull(videoMaterialType, "videoMaterialType");
        return isExternal(videoMaterialType) || isLiveSubscription(videoMaterialType);
    }

    public static boolean isExternal(@Nonnull VideoMaterialType videoMaterialType) {
        Preconditions.checkNotNull(videoMaterialType, "videoMaterialType");
        return videoMaterialType == VideoMaterialType.External;
    }

    public static boolean isLiveSubscription(@Nonnull VideoMaterialType videoMaterialType) {
        Preconditions.checkNotNull(videoMaterialType, "videoMaterialType");
        return videoMaterialType == VideoMaterialType.LiveStreaming;
    }

    @Nonnull
    public static ContentType toPlayersContentType(@Nonnull VideoMaterialType videoMaterialType) {
        Preconditions.checkNotNull(videoMaterialType, "videoMaterialType");
        return PLAYERS_CONTENT_TYPES_INVERSE.get(videoMaterialType);
    }

    @Nonnull
    public static VideoMaterialType fromPlayersContentType(@Nonnull ContentType contentType) {
        Preconditions.checkNotNull(contentType, "contentType");
        return PLAYERS_CONTENT_TYPES.get(contentType);
    }
}
