package com.amazon.mp3.catalog.fragment.converter;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.baseviews.model.configuration.TrackModelConfiguration;
import com.amazon.mp3.brush.converters.TrackDownloadStateModelConverter;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.view.model.BadgeModel;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.DescriptiveRowItemModel;
import com.amazon.music.views.library.models.SlotNumber;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryDescriptiveRowItemConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryDescriptiveRowItemConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "Lcom/amazon/mp3/library/item/MusicTrack;", "track", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "Lcom/amazon/music/views/library/models/DescriptiveRowItemModel;", "convertTrack", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryDescriptiveRowItemConverter implements SingleBaseModelConverter<AbstractItem> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(AbstractItem data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ContentMetadata contentMetadataConvert$default = LibraryContentMetadataConverter.convert$default(data, null, 2, null);
        if (contentMetadataConvert$default != null && (data instanceof MusicTrack)) {
            return convertTrack((MusicTrack) data, contentMetadataConvert$default);
        }
        return null;
    }

    private final DescriptiveRowItemModel convertTrack(MusicTrack track, ContentMetadata metadata) {
        BadgeModel encodingBadgeModel = ContentEncodingDecoder.getEncodingBadgeModel(track.getContentEncodings(), AmazonApplication.getContext(), 8);
        BadgeModel immersiveContentBadgeModel = ContentEncodingDecoder.getImmersiveContentBadgeModel(track.getContentEncodings(), AmazonApplication.getContext(), 8);
        Integer numValueOf = new TrackModelConfiguration(0 == true ? 1 : 0, 1, 0 == true ? 1 : 0).shouldShowTrackNumber() ? Integer.valueOf(((int) track.getTrackNum()) - 1) : null;
        String title = track.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "track.title");
        return new DescriptiveRowItemModel(numValueOf, title, "", "", "", String.valueOf(track.getDuration()), Boolean.valueOf(track.hasLyrics()), Boolean.TRUE, encodingBadgeModel.getBadgeText(), immersiveContentBadgeModel.getBadgeText(), Boolean.FALSE, null, null, SlotNumber.ONE, false, null, null, "", track.getAssetType() == PrimePlaylistTracksTable.AssetType.VIDEO ? 7 : 2, metadata, track.isInLibrary(), !track.isInLibrary(), new TrackDownloadStateModelConverter().convert(Integer.valueOf(track.getDownloadState())), false, false, track.isLiked(), false, 92395520, null);
    }
}
