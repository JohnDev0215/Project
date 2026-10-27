package com.amazon.music.platform.graphql.mediagateway.generated.type;

import com.apollographql.apollo3.api.EnumType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaIdentifierType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0001\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "", "rawValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getRawValue", "()Ljava/lang/String;", "ASIN", "ASIN_LIST", "OBJECT_ID", "PODCAST_ITEM_ID", "PODCAST_EPISODE_ID", "SHARED_PLAYLIST_ID", "STATION_KEY", "STATION_SEED_ID", "UUID", "UNKNOWN__", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum MediaIdentifierType {
    ASIN("ASIN"),
    ASIN_LIST("ASIN_LIST"),
    OBJECT_ID("OBJECT_ID"),
    PODCAST_ITEM_ID("PODCAST_ITEM_ID"),
    PODCAST_EPISODE_ID("PODCAST_EPISODE_ID"),
    SHARED_PLAYLIST_ID("SHARED_PLAYLIST_ID"),
    STATION_KEY("STATION_KEY"),
    STATION_SEED_ID("STATION_SEED_ID"),
    UUID("UUID"),
    UNKNOWN__("UNKNOWN__");

    private final String rawValue;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final EnumType type = new EnumType("MediaIdentifierType");

    MediaIdentifierType(String str) {
        this.rawValue = str;
    }

    public final String getRawValue() {
        return this.rawValue;
    }

    /* JADX INFO: compiled from: MediaIdentifierType.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType$Companion;", "", "", "rawValue", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "safeValueOf", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MediaIdentifierType safeValueOf(String rawValue) {
            MediaIdentifierType mediaIdentifierType;
            Intrinsics.checkNotNullParameter(rawValue, "rawValue");
            MediaIdentifierType[] mediaIdentifierTypeArrValues = MediaIdentifierType.values();
            int length = mediaIdentifierTypeArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    mediaIdentifierType = null;
                    break;
                }
                mediaIdentifierType = mediaIdentifierTypeArrValues[i];
                if (Intrinsics.areEqual(mediaIdentifierType.getRawValue(), rawValue)) {
                    break;
                }
                i++;
            }
            return mediaIdentifierType == null ? MediaIdentifierType.UNKNOWN__ : mediaIdentifierType;
        }
    }
}
