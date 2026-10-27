package com.amazon.music.alps.liveevent.converters;

import com.amazon.music.alps.gating.AlpsFeatureGating;
import com.amazon.music.alps.liveevent.LiveEventStatus;
import com.amazon.music.alps.liveevent.enums.ImageType;
import com.amazon.music.platform.graphql.generated.ArtistMerchQuery;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.platform.graphql.generated.fragment.Merch;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEventDetailPageGridConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B_\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010&\u001a\u00020%\u0012\u0006\u0010)\u001a\u00020(\u0012\u0006\u0010,\u001a\u00020+\u0012\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b2\u00103J\b\u0010\u0004\u001a\u00020\u0003H\u0002J*\u0010\n\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00022\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0016J\u0018\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0016J\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0018\u0010\r\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u00101¨\u00064"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/LiveEventDetailPageGridConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "", "isImprovedEdpEnabled", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "artist", "", "setArtistMerch", "Lcom/amazon/music/alps/liveevent/converters/EventHeaderConverter;", "eventHeaderConverter", "Lcom/amazon/music/alps/liveevent/converters/EventHeaderConverter;", "Lcom/amazon/music/alps/liveevent/converters/NewEventHeaderConverter;", "newEventHeaderConverter", "Lcom/amazon/music/alps/liveevent/converters/NewEventHeaderConverter;", "Lcom/amazon/music/alps/liveevent/converters/WaysToWatchSectionConverter;", "waysToWatchSectionConverter", "Lcom/amazon/music/alps/liveevent/converters/WaysToWatchSectionConverter;", "Lcom/amazon/music/alps/liveevent/converters/NewWaysToWatchSectionConverter;", "newWaysToWatchSectionConverter", "Lcom/amazon/music/alps/liveevent/converters/NewWaysToWatchSectionConverter;", "Lcom/amazon/music/alps/liveevent/converters/EventDescriptionSectionConverter;", "descriptionSectionConverter", "Lcom/amazon/music/alps/liveevent/converters/EventDescriptionSectionConverter;", "Lcom/amazon/music/alps/liveevent/converters/LineupGroupsSectionConverter;", "lineupGroupsSectionConverter", "Lcom/amazon/music/alps/liveevent/converters/LineupGroupsSectionConverter;", "Lcom/amazon/music/alps/liveevent/converters/AlbumShovelerConverter;", "albumShovelerConverter", "Lcom/amazon/music/alps/liveevent/converters/AlbumShovelerConverter;", "Lcom/amazon/music/alps/liveevent/converters/MerchListShovelerConverter;", "merchListShovelerConverter", "Lcom/amazon/music/alps/liveevent/converters/MerchListShovelerConverter;", "Lcom/amazon/music/alps/liveevent/converters/ArtistMerchShovelerConverter;", "artistMerchShovelerConverter", "Lcom/amazon/music/alps/liveevent/converters/ArtistMerchShovelerConverter;", "Lcom/amazon/music/alps/liveevent/converters/PlaylistShovelerConverter;", "playlistShovelerConverter", "Lcom/amazon/music/alps/liveevent/converters/PlaylistShovelerConverter;", "Lcom/amazon/music/alps/liveevent/converters/SponsoredImageConverter;", "sponsoredImageConverter", "Lcom/amazon/music/alps/liveevent/converters/SponsoredImageConverter;", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "<init>", "(Lcom/amazon/music/alps/liveevent/converters/EventHeaderConverter;Lcom/amazon/music/alps/liveevent/converters/NewEventHeaderConverter;Lcom/amazon/music/alps/liveevent/converters/WaysToWatchSectionConverter;Lcom/amazon/music/alps/liveevent/converters/NewWaysToWatchSectionConverter;Lcom/amazon/music/alps/liveevent/converters/EventDescriptionSectionConverter;Lcom/amazon/music/alps/liveevent/converters/LineupGroupsSectionConverter;Lcom/amazon/music/alps/liveevent/converters/AlbumShovelerConverter;Lcom/amazon/music/alps/liveevent/converters/MerchListShovelerConverter;Lcom/amazon/music/alps/liveevent/converters/ArtistMerchShovelerConverter;Lcom/amazon/music/alps/liveevent/converters/PlaylistShovelerConverter;Lcom/amazon/music/alps/liveevent/converters/SponsoredImageConverter;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class LiveEventDetailPageGridConverter implements BaseContainerModelConverter<LiveEvent> {
    private final AlbumShovelerConverter albumShovelerConverter;
    private ArtistMerchQuery.Artist artist;
    private final ArtistMerchShovelerConverter artistMerchShovelerConverter;
    private final EventDescriptionSectionConverter descriptionSectionConverter;
    private final EventHeaderConverter eventHeaderConverter;
    private final LineupGroupsSectionConverter lineupGroupsSectionConverter;
    private final MerchListShovelerConverter merchListShovelerConverter;
    private final NewEventHeaderConverter newEventHeaderConverter;
    private final NewWaysToWatchSectionConverter newWaysToWatchSectionConverter;
    private final PlaylistShovelerConverter playlistShovelerConverter;
    private final SponsoredImageConverter sponsoredImageConverter;
    private final WaysToWatchSectionConverter waysToWatchSectionConverter;

    public LiveEventDetailPageGridConverter(EventHeaderConverter eventHeaderConverter, NewEventHeaderConverter newEventHeaderConverter, WaysToWatchSectionConverter waysToWatchSectionConverter, NewWaysToWatchSectionConverter newWaysToWatchSectionConverter, EventDescriptionSectionConverter descriptionSectionConverter, LineupGroupsSectionConverter lineupGroupsSectionConverter, AlbumShovelerConverter albumShovelerConverter, MerchListShovelerConverter merchListShovelerConverter, ArtistMerchShovelerConverter artistMerchShovelerConverter, PlaylistShovelerConverter playlistShovelerConverter, SponsoredImageConverter sponsoredImageConverter) {
        Intrinsics.checkNotNullParameter(eventHeaderConverter, "eventHeaderConverter");
        Intrinsics.checkNotNullParameter(newEventHeaderConverter, "newEventHeaderConverter");
        Intrinsics.checkNotNullParameter(waysToWatchSectionConverter, "waysToWatchSectionConverter");
        Intrinsics.checkNotNullParameter(newWaysToWatchSectionConverter, "newWaysToWatchSectionConverter");
        Intrinsics.checkNotNullParameter(descriptionSectionConverter, "descriptionSectionConverter");
        Intrinsics.checkNotNullParameter(lineupGroupsSectionConverter, "lineupGroupsSectionConverter");
        Intrinsics.checkNotNullParameter(albumShovelerConverter, "albumShovelerConverter");
        Intrinsics.checkNotNullParameter(merchListShovelerConverter, "merchListShovelerConverter");
        Intrinsics.checkNotNullParameter(artistMerchShovelerConverter, "artistMerchShovelerConverter");
        Intrinsics.checkNotNullParameter(playlistShovelerConverter, "playlistShovelerConverter");
        Intrinsics.checkNotNullParameter(sponsoredImageConverter, "sponsoredImageConverter");
        this.eventHeaderConverter = eventHeaderConverter;
        this.newEventHeaderConverter = newEventHeaderConverter;
        this.waysToWatchSectionConverter = waysToWatchSectionConverter;
        this.newWaysToWatchSectionConverter = newWaysToWatchSectionConverter;
        this.descriptionSectionConverter = descriptionSectionConverter;
        this.lineupGroupsSectionConverter = lineupGroupsSectionConverter;
        this.albumShovelerConverter = albumShovelerConverter;
        this.merchListShovelerConverter = merchListShovelerConverter;
        this.artistMerchShovelerConverter = artistMerchShovelerConverter;
        this.playlistShovelerConverter = playlistShovelerConverter;
        this.sponsoredImageConverter = sponsoredImageConverter;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(LiveEvent liveEvent, List list) {
        return convert2(liveEvent, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(LiveEvent data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (children == null || children.isEmpty()) {
            return null;
        }
        return new PageGridViewModel(null, null, null, null, children, 2, 0, null, null, null, null, 1807, null);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(LiveEvent data) {
        List listFilterNotNull;
        List<Merch> list;
        List listFilterNotNull2;
        ArrayList arrayList;
        BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert2;
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList2 = new ArrayList();
        if (isImprovedEdpEnabled()) {
            BaseViewModel baseViewModelConvert = this.newEventHeaderConverter.convert(data);
            if (baseViewModelConvert != null) {
                arrayList2.add(baseViewModelConvert);
            }
            BaseViewModel baseViewModelConvert2 = this.newWaysToWatchSectionConverter.convert(data);
            if (baseViewModelConvert2 != null) {
                arrayList2.add(baseViewModelConvert2);
            }
        } else {
            BaseViewModel baseViewModelConvert22 = this.eventHeaderConverter.convert(data);
            if (baseViewModelConvert22 != null) {
                arrayList2.add(baseViewModelConvert22);
            }
            BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert22 = this.waysToWatchSectionConverter.convert2(data, (List<? extends BaseViewModel>) this.waysToWatchSectionConverter.getChildrenModels(data));
            if (baseViewContainerModelConvert22 != null) {
                arrayList2.add(baseViewContainerModelConvert22);
            }
        }
        BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert23 = this.descriptionSectionConverter.convert2(data, (List<? extends BaseViewModel>) this.descriptionSectionConverter.getChildrenModels(data));
        if (baseViewContainerModelConvert23 != null) {
            arrayList2.add(baseViewContainerModelConvert23);
        }
        BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert24 = this.lineupGroupsSectionConverter.convert2(data, (List<? extends BaseViewModel>) this.lineupGroupsSectionConverter.getChildrenModels(data));
        if (baseViewContainerModelConvert24 != null) {
            arrayList2.add(baseViewContainerModelConvert24);
        }
        List<LiveEvent.Album> albums = data.getAlbums();
        List<LiveEvent.Album> listFilterNotNull3 = albums == null ? null : CollectionsKt___CollectionsKt.filterNotNull(albums);
        if (!(listFilterNotNull3 == null || listFilterNotNull3.isEmpty())) {
            BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert25 = this.albumShovelerConverter.convert2(listFilterNotNull3, (List<? extends BaseViewModel>) this.albumShovelerConverter.getChildrenModels2(listFilterNotNull3));
            if (baseViewContainerModelConvert25 != null) {
                arrayList2.add(baseViewContainerModelConvert25);
            }
        }
        List<LiveEvent.Merch> merch = data.getMerch();
        if (merch == null || (listFilterNotNull = CollectionsKt___CollectionsKt.filterNotNull(merch)) == null) {
            list = null;
        } else {
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listFilterNotNull, 10));
            Iterator it = listFilterNotNull.iterator();
            while (it.hasNext()) {
                arrayList3.add(((LiveEvent.Merch) it.next()).getMerch());
            }
            list = CollectionsKt___CollectionsKt.toList(arrayList3);
        }
        if (!(list == null || list.isEmpty())) {
            BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert26 = this.merchListShovelerConverter.convert2(list, (List<? extends BaseViewModel>) this.merchListShovelerConverter.getChildrenModels2(list));
            if (baseViewContainerModelConvert26 != null) {
                arrayList2.add(baseViewContainerModelConvert26);
            }
        }
        ArtistMerchQuery.Artist artist = this.artist;
        if (artist != null) {
            BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert27 = this.artistMerchShovelerConverter.convert2(artist, (List<? extends BaseViewModel>) this.artistMerchShovelerConverter.getChildrenModels(artist));
            if (baseViewContainerModelConvert27 != null) {
                arrayList2.add(baseViewContainerModelConvert27);
            }
        }
        List<LiveEvent.Playlist> playlists = data.getPlaylists();
        List<LiveEvent.Playlist> listFilterNotNull4 = playlists == null ? null : CollectionsKt___CollectionsKt.filterNotNull(playlists);
        if (LiveEventStatus.INSTANCE.from(data.getStatus()) == LiveEventStatus.AFTERGLOW) {
            if (!(listFilterNotNull4 == null || listFilterNotNull4.isEmpty())) {
                BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert28 = this.playlistShovelerConverter.convert2(listFilterNotNull4, (List<? extends BaseViewModel>) this.playlistShovelerConverter.getChildrenModels2(listFilterNotNull4));
                if (baseViewContainerModelConvert28 != null) {
                    arrayList2.add(baseViewContainerModelConvert28);
                }
            }
        }
        List<LiveEvent.Image> images = data.getImages();
        if (images == null || (listFilterNotNull2 = CollectionsKt___CollectionsKt.filterNotNull(images)) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (Object obj : listFilterNotNull2) {
                if (ImageType.INSTANCE.from(((LiveEvent.Image) obj).getImage().getImageType()) == ImageType.SPONSOR) {
                    arrayList.add(obj);
                }
            }
        }
        if (!(arrayList == null || arrayList.isEmpty()) && (baseViewContainerModelConvert2 = this.sponsoredImageConverter.convert(data)) != null) {
            arrayList2.add(baseViewContainerModelConvert2);
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        return arrayList2;
    }

    public final void setArtistMerch(ArtistMerchQuery.Artist artist) {
        Intrinsics.checkNotNullParameter(artist, "artist");
        this.artist = artist;
    }

    private final boolean isImprovedEdpEnabled() {
        return AlpsFeatureGating.IMPROVED_EDP.isEnabled();
    }
}
