package com.amazon.mp3.catalog.fragment.converter;

import com.amazon.mp3.detailpages.album.util.LibraryAlbumDetailSectionGridConverterAdapter;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryDetailSectionGridConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J\u0018\u0010\u0007\u001a\u00020\u00062\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0002J\u001e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\bH\u0002J\u001e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\bH\u0002J\"\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u00022\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0016J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u001c\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003J\u001c\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailSectionGridConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/SectionGridViewModel;", "convertTrackGroup", "", "contentItems", "getContentModels", "Lcom/amazon/mp3/library/item/MusicTrack;", "trackItems", "getAlbumChildrenModels", "data", "convert", "getChildrenModels", "tracks", "getChildrenModelsWithTracks", "list", "getSectionChildrenModels", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "contentConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "<init>", "(Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryDetailSectionGridConverter implements BaseContainerModelConverter<AbstractItem> {
    private final SingleBaseModelConverter<AbstractItem> contentConverter;

    public LibraryDetailSectionGridConverter(SingleBaseModelConverter<AbstractItem> contentConverter) {
        Intrinsics.checkNotNullParameter(contentConverter, "contentConverter");
        this.contentConverter = contentConverter;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(AbstractItem abstractItem, List list) {
        return convert2(abstractItem, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public SectionGridViewModel convert2(AbstractItem data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album ? true : data instanceof Playlist ? true : data instanceof CatalogPlaylist) {
            return convertTrackGroup(children);
        }
        return null;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(AbstractItem data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (!(data instanceof Album)) {
            return null;
        }
        Collection<MusicTrack> tracksWithDiscNumberSortOrder = ((Album) data).getTracksWithDiscNumberSortOrder();
        Intrinsics.checkNotNullExpressionValue(tracksWithDiscNumberSortOrder, "data.tracksWithDiscNumberSortOrder");
        return getAlbumChildrenModels(tracksWithDiscNumberSortOrder);
    }

    public final List<BaseViewModel> getChildrenModelsWithTracks(List<? extends MusicTrack> tracks) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        return getAlbumChildrenModels(tracks);
    }

    public final List<BaseViewModel> getSectionChildrenModels(List<? extends AbstractItem> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        return getContentModels(list);
    }

    private final SectionGridViewModel convertTrackGroup(List<? extends BaseViewModel> children) {
        return new SectionGridViewModel(null, children == null ? CollectionsKt__CollectionsKt.emptyList() : children, LibraryLayoutTypeUtil.INSTANCE.getLayoutType(this.contentConverter), CollectionsKt__CollectionsKt.mutableListOf("TRACK_LIST_TAG", "FILTERABLE_MODELS_CONTAINER_TAG"), null, null, null, null, null, null, false, children == null ? null : Integer.valueOf(children.size()), false, null, null, null, null, 129009, null);
    }

    private final List<BaseViewModel> getContentModels(Collection<? extends AbstractItem> contentItems) {
        ArrayList arrayList = new ArrayList();
        Iterator<? extends AbstractItem> it = contentItems.iterator();
        while (it.hasNext()) {
            BaseViewModel baseViewModelConvert = this.contentConverter.convert(it.next());
            if (baseViewModelConvert != null) {
                arrayList.add(baseViewModelConvert);
            }
        }
        return arrayList;
    }

    private final List<BaseViewModel> getAlbumChildrenModels(Collection<? extends MusicTrack> trackItems) {
        return LibraryAlbumDetailSectionGridConverterAdapter.INSTANCE.getChildrenModels(this.contentConverter, trackItems);
    }
}
