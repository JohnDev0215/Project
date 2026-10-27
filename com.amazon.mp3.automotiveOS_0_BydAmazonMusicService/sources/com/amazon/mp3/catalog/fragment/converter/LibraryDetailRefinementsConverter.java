package com.amazon.mp3.catalog.fragment.converter;

import com.amazon.mp3.baseviews.model.configuration.ContainerModelConfiguration;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.catalog.fragment.viewmodel.AudioQualityRefinementEvaluator;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinementsModelBuilder;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryDetailRefinementsConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B/\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b%\u0010&J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\bH\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u000bH\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\u0002H\u0016R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u001f\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010!\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001b\u0010$\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u001e\u001a\u0004\b#\u0010 ¨\u0006'"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailRefinementsConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "Lcom/amazon/mp3/library/item/Album;", "album", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "convertAlbum", "convertArtist", "Lcom/amazon/mp3/library/item/Playlist;", "data", "convertPlaylist", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "convertCatalogPlaylist", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "", "isOffline", "Z", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "defaultFilters", "Ljava/util/Set;", "getDefaultFilters", "()Ljava/util/Set;", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "configuration", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "getConfiguration", "()Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "userPlaylistRefinementModel$delegate", "Lkotlin/Lazy;", "getUserPlaylistRefinementModel", "()Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "userPlaylistRefinementModel", "catalogPlaylistRefinementModel$delegate", "getCatalogPlaylistRefinementModel", "catalogPlaylistRefinementModel", "<init>", "(ZLjava/util/Set;Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryDetailRefinementsConverter implements SingleBaseModelConverter<AbstractItem> {

    /* JADX INFO: renamed from: catalogPlaylistRefinementModel$delegate, reason: from kotlin metadata */
    private final Lazy catalogPlaylistRefinementModel;
    private final ContainerModelConfiguration configuration;
    private final Set<RefinementFilterType> defaultFilters;
    private final boolean isOffline;

    /* JADX INFO: renamed from: userPlaylistRefinementModel$delegate, reason: from kotlin metadata */
    private final Lazy userPlaylistRefinementModel;

    public LibraryDetailRefinementsConverter() {
        this(false, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LibraryDetailRefinementsConverter(boolean z, Set<? extends RefinementFilterType> set, ContainerModelConfiguration containerModelConfiguration) {
        this.isOffline = z;
        this.defaultFilters = set;
        this.configuration = containerModelConfiguration;
        this.userPlaylistRefinementModel = LazyKt__LazyJVMKt.lazy(new Function0<ExposedRefinementListModel>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryDetailRefinementsConverter$userPlaylistRefinementModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ExposedRefinementListModel invoke() {
                return new RefinementsModelBuilder(null, null, 3, null).useOfflineState(this.this$0.isOffline).build(RefinementsModelBuilder.RefinementType.USER_PLAYLIST_DP);
            }
        });
        this.catalogPlaylistRefinementModel = LazyKt__LazyJVMKt.lazy(new Function0<ExposedRefinementListModel>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryDetailRefinementsConverter$catalogPlaylistRefinementModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ExposedRefinementListModel invoke() {
                return new RefinementsModelBuilder(null, null, 3, null).useOfflineState(this.this$0.isOffline).build(RefinementsModelBuilder.RefinementType.CATALOG_PLAYLIST_DP);
            }
        });
    }

    public /* synthetic */ LibraryDetailRefinementsConverter(boolean z, Set set, ContainerModelConfiguration containerModelConfiguration, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : set, (i & 4) != 0 ? null : containerModelConfiguration);
    }

    private final ExposedRefinementListModel getUserPlaylistRefinementModel() {
        return (ExposedRefinementListModel) this.userPlaylistRefinementModel.getValue();
    }

    private final ExposedRefinementListModel getCatalogPlaylistRefinementModel() {
        return (ExposedRefinementListModel) this.catalogPlaylistRefinementModel.getValue();
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(AbstractItem data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album) {
            return convertAlbum((Album) data);
        }
        if (data instanceof Artist) {
            return convertArtist();
        }
        if (data instanceof Playlist) {
            return convertPlaylist((Playlist) data);
        }
        if (data instanceof CatalogPlaylist) {
            return convertCatalogPlaylist((CatalogPlaylist) data);
        }
        return null;
    }

    private final ExposedRefinementListModel convertAlbum(Album album) {
        List<String> from = ContentEncodingDecoder.parseFrom(album.getContentEncodings());
        Intrinsics.checkNotNullExpressionValue(from, "parseFrom(album.contentEncodings)");
        return new RefinementsModelBuilder(new AudioQualityRefinementEvaluator(CollectionsKt___CollectionsKt.toSet(from)), this.defaultFilters).useOfflineState(this.isOffline).build(RefinementsModelBuilder.RefinementType.ALBUM_DP);
    }

    private final ExposedRefinementListModel convertArtist() {
        return new RefinementsModelBuilder(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0).build(RefinementsModelBuilder.RefinementType.ARTIST_DP);
    }

    private final ExposedRefinementListModel convertPlaylist(Playlist data) {
        ContainerModelConfiguration playlistModelConfiguration = this.configuration;
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
        byte b = 0;
        if (playlistModelConfiguration == null) {
            playlistModelConfiguration = new PlaylistModelConfiguration(cachedCapabilityActionProviderImpl, 1, b == true ? 1 : 0);
        }
        if (playlistModelConfiguration.shouldShowRefinementsOption(data)) {
            return getUserPlaylistRefinementModel();
        }
        return null;
    }

    private final ExposedRefinementListModel convertCatalogPlaylist(CatalogPlaylist data) {
        ContainerModelConfiguration playlistModelConfiguration = this.configuration;
        CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl = null;
        byte b = 0;
        if (playlistModelConfiguration == null) {
            playlistModelConfiguration = new PlaylistModelConfiguration(cachedCapabilityActionProviderImpl, 1, b == true ? 1 : 0);
        }
        if (playlistModelConfiguration.shouldShowRefinementsOption(data)) {
            return getCatalogPlaylistRefinementModel();
        }
        return null;
    }
}
