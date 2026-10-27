package com.amazon.mp3.brush.converters;

import android.net.Uri;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.LivestreamMetadata;
import com.amazon.music.views.library.models.LivestreamBarkerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.LiveStream;
import com.amazon.musicensembleservice.brush.StreamProvider;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushLivestreamBarkerConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushLivestreamBarkerConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/LiveStream;", "livestream", "Lcom/amazon/music/views/library/models/LivestreamBarkerModel;", "convertLivestream", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushLivestreamBarkerConverter implements SingleBaseModelConverter<Entity> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof LiveStream) {
            return convertLivestream((LiveStream) data);
        }
        return null;
    }

    private final LivestreamBarkerModel convertLivestream(LiveStream livestream) {
        ArtistMetadata artistMetadata;
        List<Image> wordmarkImages;
        Image image;
        List<Artist> artists = livestream.getArtists();
        String url = null;
        if (artists == null || artists.size() <= 0) {
            artistMetadata = null;
        } else {
            String blockRef = livestream.getBlockRef();
            String asin = ((Artist) CollectionsKt___CollectionsKt.first((List) artists)).getAsin();
            String primaryTitle = ((Artist) CollectionsKt___CollectionsKt.first((List) artists)).getPrimaryTitle();
            Image image2 = ((Artist) CollectionsKt___CollectionsKt.first((List) artists)).getImage();
            artistMetadata = new ArtistMetadata(blockRef, ContentPlaybackUtils.INSTANCE.getArtistUri(), null, null, asin, primaryTitle, null, image2 == null ? null : image2.getUrl(), null, null, null, false, null, null, false, null, null, false, false, null, 1048396, null);
        }
        if (livestream.getPrimaryTitle() == null) {
            return null;
        }
        String blockRef2 = livestream.getBlockRef();
        List<Image> images = livestream.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "livestream.images");
        String url2 = ((Image) CollectionsKt___CollectionsKt.first((List) images)).getUrl();
        String primaryTitle2 = livestream.getPrimaryTitle();
        String secondaryTitle = livestream.getSecondaryTitle();
        String tertiaryTitle = livestream.getTertiaryTitle();
        String viewers = livestream.getViewers();
        StreamProvider streamProvider = livestream.getStreamProvider();
        if (streamProvider != null && (wordmarkImages = streamProvider.getWordmarkImages()) != null && (image = (Image) CollectionsKt___CollectionsKt.first((List) wordmarkImages)) != null) {
            url = image.getUrl();
        }
        String str = url;
        Intrinsics.checkNotNull(str);
        String blockRef3 = livestream.getBlockRef();
        String id = livestream.getId();
        Uri livestreamUri = ContentPlaybackUtils.INSTANCE.getLivestreamUri();
        Intrinsics.checkNotNullExpressionValue(id, "id");
        LivestreamMetadata livestreamMetadata = new LivestreamMetadata(blockRef3, livestreamUri, null, id, 4, null);
        Intrinsics.checkNotNullExpressionValue(url2, "url");
        Intrinsics.checkNotNullExpressionValue(primaryTitle2, "primaryTitle");
        return new LivestreamBarkerModel(blockRef2, url2, primaryTitle2, secondaryTitle, tertiaryTitle, 9, livestreamMetadata, viewers, artistMetadata, str);
    }
}
