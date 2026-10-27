package com.amazon.mp3.cloudqueue.sequencer;

import android.net.Uri;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.MediaLinkProvider;
import com.amazon.music.media.playback.MediaLinks;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import com.amazon.music.media.playback.config.BitRateSelection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UrlMediaLinkProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J.\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/cloudqueue/sequencer/UrlMediaLinkProvider;", "Lcom/amazon/music/media/playback/MediaLinkProvider;", "()V", "getMediaLinks", "Lcom/amazon/music/media/playback/MediaLinks;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "bitRateSelection", "Lcom/amazon/music/media/playback/config/BitRateSelection;", "includeSpatial", "", "linkTypes", "", "Lcom/amazon/music/media/playback/MediaLink$Type;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UrlMediaLinkProvider implements MediaLinkProvider {
    @Override // com.amazon.music.media.playback.MediaLinkProvider
    public MediaLinks getMediaLinks(MediaItem mediaItem, BitRateSelection bitRateSelection) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        Intrinsics.checkNotNullParameter(bitRateSelection, "bitRateSelection");
        return getMediaLinks(mediaItem, bitRateSelection, false, CollectionsKt__CollectionsJVMKt.listOf(MediaLink.Type.PROGRESSIVE));
    }

    @Override // com.amazon.music.media.playback.MediaLinkProvider
    public MediaLinks getMediaLinks(MediaItem mediaItem, BitRateSelection bitRateSelection, boolean includeSpatial, List<? extends MediaLink.Type> linkTypes) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        Intrinsics.checkNotNullParameter(bitRateSelection, "bitRateSelection");
        Intrinsics.checkNotNullParameter(linkTypes, "linkTypes");
        String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.URL, null);
        if (!linkTypes.contains(MediaLink.Type.PROGRESSIVE) || mediaItemIdString == null) {
            MediaLinks EMPTY = MediaLinks.EMPTY;
            Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
            return EMPTY;
        }
        return new MediaLinks(CollectionsKt__CollectionsJVMKt.listOf(new MediaLink(MediaLink.Type.DASH, bitRateSelection, Uri.parse(mediaItemIdString), Long.MAX_VALUE)), new PlaybackAttributes(PlaybackAudioQuality.UNKNOWN, -1, -1, -1));
    }
}
