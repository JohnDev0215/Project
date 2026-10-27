package com.amazon.avod.core.constants;

import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public enum UrlType {
    TRAILER(1),
    CONTENT(2),
    LIVE(3),
    LIVE_EXTERNAL(4);

    private static final ImmutableBiMap<UrlType, ContentType> PLAYERS_CONTENT_TYPES;
    private static final Map<ContentType, UrlType> PLAYERS_TYPES_INVERSE;
    private static final ImmutableBiMap<UrlType, VideoMaterialType> VIDEO_MATERIAL_TYPES;
    private static final Map<VideoMaterialType, UrlType> VIDEO_MATERIAL_TYPES_INVERSE;
    private final int mValue;

    static {
        UrlType urlType = TRAILER;
        UrlType urlType2 = CONTENT;
        UrlType urlType3 = LIVE;
        UrlType urlType4 = LIVE_EXTERNAL;
        ImmutableBiMap<UrlType, VideoMaterialType> immutableBiMap = (ImmutableBiMap) Preconditions2.checkFullKeyMapping(UrlType.class, ImmutableBiMap.of(urlType, VideoMaterialType.Trailer, urlType2, VideoMaterialType.Feature, urlType3, VideoMaterialType.LiveStreaming, urlType4, VideoMaterialType.External));
        VIDEO_MATERIAL_TYPES = immutableBiMap;
        VIDEO_MATERIAL_TYPES_INVERSE = ImmutableMap.builder().putAll(immutableBiMap.inverse()).put(VideoMaterialType.ValueAdded, urlType2).build();
        ImmutableBiMap<UrlType, ContentType> immutableBiMap2 = (ImmutableBiMap) Preconditions2.checkFullKeyMapping(UrlType.class, ImmutableBiMap.of(urlType, ContentType.Trailer, urlType2, ContentType.Feature, urlType3, ContentType.LiveStreaming, urlType4, ContentType.External));
        PLAYERS_CONTENT_TYPES = immutableBiMap2;
        PLAYERS_TYPES_INVERSE = Preconditions2.checkFullKeyMapping(ContentType.class, immutableBiMap2.inverse());
    }

    UrlType(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }

    @Nonnull
    public String getLegacyName() {
        int i = this.mValue;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "TYPE_UNKNOWN" : "TYPE_EXTERNAL" : "TYPE_LIVE" : "TYPE_CONTENT" : "TYPE_PREVIEW";
    }

    @Nullable
    public static UrlType forValue(int i) {
        for (UrlType urlType : values()) {
            if (urlType.getValue() == i) {
                return urlType;
            }
        }
        return null;
    }

    public static boolean isContent(UrlType urlType) {
        return CONTENT == urlType;
    }

    public static boolean isTrailer(UrlType urlType) {
        return TRAILER == urlType;
    }

    public static boolean isLive(UrlType urlType) {
        return isExternal(urlType) || isLiveSubscription(urlType);
    }

    public static boolean isExternal(UrlType urlType) {
        return LIVE_EXTERNAL == urlType;
    }

    public static boolean isLiveSubscription(UrlType urlType) {
        return LIVE == urlType;
    }

    @Nonnull
    public static VideoMaterialType toVideoMaterialType(@Nonnull UrlType urlType) {
        Preconditions.checkNotNull(urlType, "urlType");
        return VIDEO_MATERIAL_TYPES.get(urlType);
    }

    @Nonnull
    public static UrlType fromVideoMaterialType(@Nonnull VideoMaterialType videoMaterialType) {
        Preconditions.checkNotNull(videoMaterialType, "videoMaterialType");
        return VIDEO_MATERIAL_TYPES_INVERSE.get(videoMaterialType);
    }

    @Nonnull
    public static ContentType toPlayersContentType(@Nonnull UrlType urlType) {
        Preconditions.checkNotNull(urlType, "urlType");
        return PLAYERS_CONTENT_TYPES.get(urlType);
    }

    @Nonnull
    public static UrlType fromPlayersContentType(@Nonnull ContentType contentType) {
        Preconditions.checkNotNull(contentType, "contentType");
        return PLAYERS_TYPES_INVERSE.get(contentType);
    }
}
