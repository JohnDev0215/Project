package com.amazon.mp3.voice;

import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: MusicEMPTrackIdProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/voice/MusicEMPTrackIdProvider;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MusicEMPTrackIdProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: MusicEMPTrackIdProvider.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/voice/MusicEMPTrackIdProvider$Companion;", "", "()V", "FORMAT", "", "INVALID", "PREFIX_AD", "PREFIX_ASIN", "PREFIX_OBJECT_ID", "getTrackId", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTrackId(MediaItem mediaItem) {
            String mediaItemIdString;
            String str;
            Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
            MediaItem.Type type = mediaItem.getType();
            if (Intrinsics.areEqual(MediaItem.Type.AD_CONTENT, type)) {
                mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.URL, "");
                str = "ad";
            } else if (Intrinsics.areEqual(MediaItem.Type.OWNED_CONTENT, type)) {
                mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.OWNED, "");
                str = "objectId";
            } else {
                mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, "");
                str = "asin";
            }
            if (mediaItemIdString == null) {
                return "INVALID";
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str2 = String.format("%s://%s/", Arrays.copyOf(new Object[]{str, mediaItemIdString}, 2));
            Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
            return str2;
        }
    }
}
