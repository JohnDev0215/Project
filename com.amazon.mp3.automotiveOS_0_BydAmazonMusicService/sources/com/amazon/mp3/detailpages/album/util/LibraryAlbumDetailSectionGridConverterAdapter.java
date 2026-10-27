package com.amazon.mp3.detailpages.album.util;

import com.amazon.mp3.detailpages.util.DiscHeaderIndexer;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.views.TextRowItemView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryAlbumDetailSectionGridConverterAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u001a\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0002¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/detailpages/album/util/LibraryAlbumDetailSectionGridConverterAdapter;", "", "()V", "getChildrenModels", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "contentConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "trackItems", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "shouldCreateDiscHeaderModel", "discHeaderIndexer", "Lcom/amazon/mp3/detailpages/util/DiscHeaderIndexer;", "track", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryAlbumDetailSectionGridConverterAdapter {
    public static final LibraryAlbumDetailSectionGridConverterAdapter INSTANCE = new LibraryAlbumDetailSectionGridConverterAdapter();

    private LibraryAlbumDetailSectionGridConverterAdapter() {
    }

    public final List<BaseViewModel> getChildrenModels(SingleBaseModelConverter<AbstractItem> contentConverter, Collection<? extends MusicTrack> trackItems) {
        Intrinsics.checkNotNullParameter(contentConverter, "contentConverter");
        Intrinsics.checkNotNullParameter(trackItems, "trackItems");
        ArrayList arrayList = new ArrayList();
        DiscHeaderIndexer discHeaderIndexerInitializeIndexerWithAbstractItems = DiscHeaderIndexer.INSTANCE.initializeIndexerWithAbstractItems(trackItems);
        for (MusicTrack musicTrack : trackItems) {
            BaseViewModel baseViewModelConvert = contentConverter.convert(musicTrack);
            BaseViewModel baseViewModelShouldCreateDiscHeaderModel = shouldCreateDiscHeaderModel(discHeaderIndexerInitializeIndexerWithAbstractItems, musicTrack);
            if (baseViewModelShouldCreateDiscHeaderModel != null) {
                arrayList.add(baseViewModelShouldCreateDiscHeaderModel);
            }
            if (baseViewModelConvert != null) {
                arrayList.add(baseViewModelConvert);
            }
        }
        return arrayList;
    }

    private final BaseViewModel shouldCreateDiscHeaderModel(DiscHeaderIndexer discHeaderIndexer, MusicTrack track) {
        DiscHeaderIndexer.Disc discForMusicTrack = discHeaderIndexer.getDiscForMusicTrack(track);
        if (discForMusicTrack == null) {
            return null;
        }
        return new TextRowItemModel(null, discForMusicTrack.getTitle(), null, null, TextRowItemView.TextStyle.DISC_HEADER, false, 45, null);
    }
}
