package com.amazon.music.platform.graphql.mediagateway.generated.type;

import com.apollographql.apollo3.api.EnumType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0086\u0001\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "", "rawValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getRawValue", "()Ljava/lang/String;", "ARTIST", "MUSIC_TRACK_LIST", "MUSIC_TRACK", "MUSIC_ALBUM", "PLAYLIST", "QUEUE", "STATION", "LIVE_STATION", "STATION_FOR_ANYTHING", "PODCAST_EPISODE", "PODCAST_GROUP", "UNKNOWN__", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum MediaType {
    ARTIST("ARTIST"),
    MUSIC_TRACK_LIST("MUSIC_TRACK_LIST"),
    MUSIC_TRACK("MUSIC_TRACK"),
    MUSIC_ALBUM("MUSIC_ALBUM"),
    PLAYLIST("PLAYLIST"),
    QUEUE("QUEUE"),
    STATION("STATION"),
    LIVE_STATION("LIVE_STATION"),
    STATION_FOR_ANYTHING("STATION_FOR_ANYTHING"),
    PODCAST_EPISODE("PODCAST_EPISODE"),
    PODCAST_GROUP("PODCAST_GROUP"),
    UNKNOWN__("UNKNOWN__");

    private final String rawValue;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final EnumType type = new EnumType("MediaType");

    MediaType(String str) {
        this.rawValue = str;
    }

    public final String getRawValue() {
        return this.rawValue;
    }

    /* JADX INFO: compiled from: MediaType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType$Companion;", "", "", "rawValue", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "safeValueOf", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MediaType safeValueOf(String rawValue) {
            MediaType mediaType;
            Intrinsics.checkNotNullParameter(rawValue, "rawValue");
            MediaType[] mediaTypeArrValues = MediaType.values();
            int length = mediaTypeArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    mediaType = null;
                    break;
                }
                mediaType = mediaTypeArrValues[i];
                if (Intrinsics.areEqual(mediaType.getRawValue(), rawValue)) {
                    break;
                }
                i++;
            }
            return mediaType == null ? MediaType.UNKNOWN__ : mediaType;
        }
    }
}
