package com.amazon.mp3.catalog.fragment.converter;

import android.content.Context;
import android.content.res.Resources;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.ContainerModelConfiguration;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ButtonType;
import com.amazon.music.views.library.models.DescriptiveTextItemModel;
import com.amazon.music.views.library.models.DescriptiveTextModel;
import com.amazon.music.views.library.models.EmptyStateButtonType;
import com.amazon.music.views.library.models.EmptyStateModel;
import com.amazon.music.views.library.models.HighlightableMessageModel;
import com.amazon.music.views.library.models.MultiStateButtonModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderModel;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.Hideable;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.TextRowItemView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryDetailPageConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 K2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001KBm\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00020-\u0012\u0006\u00101\u001a\u000200\u0012\b\b\u0002\u00103\u001a\u00020\u000f\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u000105\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u000108\u0012\n\b\u0002\u0010<\u001a\u0004\u0018\u00010;\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010>\u0012\n\b\u0002\u0010B\u001a\u0004\u0018\u00010A¢\u0006\u0004\bI\u0010JJ \u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00032\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0002J \u0010\f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0002J \u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J.\u0010\u0018\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002J\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J\u0010\u0010 \u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u0002H\u0002J\"\u0010!\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001f\u001a\u00020\u00022\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J\u0018\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\u0002H\u0016J&\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\u00022\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u0005J:\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001f\u001a\u00020\u00022\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0013J&\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\u00022\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u0005R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010,R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0014\u00103\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00106\u001a\u0004\u0018\u0001058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00109\u001a\u0004\u0018\u0001088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010<\u001a\u0004\u0018\u00010;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010?\u001a\u0004\u0018\u00010>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010B\u001a\u0004\u0018\u00010A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u001b\u0010H\u001a\u0002058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G¨\u0006L"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailPageConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/mp3/library/item/AbstractItem;", "Lcom/amazon/mp3/library/item/Album;", "album", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "convertAlbum", "Lcom/amazon/mp3/library/item/Artist;", "artist", "convertArtist", Environment.PLAYLIST_PATH, "convertPlaylist", "", "isCatalog", "Lcom/amazon/music/views/library/models/EmptyStateModel;", "getEmptyStateModelForPlaylist", "", "title", "message", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "buttonType", "getEmptyStateModel", "Landroid/content/Context;", "context", "", "trackCount", "Lcom/amazon/music/views/library/models/HighlightableMessageModel;", "getHighlightableMessageModel", "data", "isCatalogPlaylist", "convert", "getChildrenModels", "Lcom/amazon/mp3/library/item/MusicTrack;", "tracks", "getAlbumChildrenModels", "albums", "followerCount", "catalogArtistName", "getArtistChildrenModels", "trackList", "getPlaylistChildrenModels", "Landroid/content/Context;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "contentConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailHeaderConverter;", "headerConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailHeaderConverter;", "isOffline", "Z", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailRefinementsConverter;", "refinementConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailRefinementsConverter;", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailButtonNavigatorConverter;", "buttonNavigatorConverter", "Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailButtonNavigatorConverter;", "Lcom/amazon/mp3/catalog/fragment/converter/PlaylistDetailAddSongsButtonNavigatorConverter;", "addSongsNavigatorConverter", "Lcom/amazon/mp3/catalog/fragment/converter/PlaylistDetailAddSongsButtonNavigatorConverter;", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "configuration", "Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "cachedActionProviderImpl", "Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;", "refinementsConverter$delegate", "Lkotlin/Lazy;", "getRefinementsConverter", "()Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailRefinementsConverter;", "refinementsConverter", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailHeaderConverter;ZLcom/amazon/mp3/catalog/fragment/converter/LibraryDetailRefinementsConverter;Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailButtonNavigatorConverter;Lcom/amazon/mp3/catalog/fragment/converter/PlaylistDetailAddSongsButtonNavigatorConverter;Lcom/amazon/mp3/baseviews/model/configuration/ContainerModelConfiguration;Lcom/amazon/mp3/eligibility/CachedCapabilityActionProviderImpl;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryDetailPageConverter implements BaseContainerModelConverter<AbstractItem> {
    private final PlaylistDetailAddSongsButtonNavigatorConverter addSongsNavigatorConverter;
    private final LibraryDetailButtonNavigatorConverter buttonNavigatorConverter;
    private final CachedCapabilityActionProviderImpl cachedActionProviderImpl;
    private final ContainerModelConfiguration configuration;
    private final SingleBaseModelConverter<AbstractItem> contentConverter;
    private final Context context;
    private final LibraryDetailHeaderConverter headerConverter;
    private final boolean isOffline;
    private final LibraryDetailRefinementsConverter refinementConverter;

    /* JADX INFO: renamed from: refinementsConverter$delegate, reason: from kotlin metadata */
    private final Lazy refinementsConverter;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int ALL_ACCESS_USER_PLAYLIST_COMPLIANT_MIN = 15;
    private static final int ALL_ACCESS_USER_PLAYLIST_COMPLIANT_MAX = 50;

    public LibraryDetailPageConverter(Context context, SingleBaseModelConverter<AbstractItem> contentConverter, LibraryDetailHeaderConverter headerConverter, boolean z, LibraryDetailRefinementsConverter libraryDetailRefinementsConverter, LibraryDetailButtonNavigatorConverter libraryDetailButtonNavigatorConverter, PlaylistDetailAddSongsButtonNavigatorConverter playlistDetailAddSongsButtonNavigatorConverter, ContainerModelConfiguration containerModelConfiguration, CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl) {
        Intrinsics.checkNotNullParameter(contentConverter, "contentConverter");
        Intrinsics.checkNotNullParameter(headerConverter, "headerConverter");
        this.context = context;
        this.contentConverter = contentConverter;
        this.headerConverter = headerConverter;
        this.isOffline = z;
        this.refinementConverter = libraryDetailRefinementsConverter;
        this.buttonNavigatorConverter = libraryDetailButtonNavigatorConverter;
        this.addSongsNavigatorConverter = playlistDetailAddSongsButtonNavigatorConverter;
        this.configuration = containerModelConfiguration;
        this.cachedActionProviderImpl = cachedCapabilityActionProviderImpl;
        this.refinementsConverter = LazyKt__LazyJVMKt.lazy(new Function0<LibraryDetailRefinementsConverter>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryDetailPageConverter$refinementsConverter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LibraryDetailRefinementsConverter invoke() {
                return new LibraryDetailRefinementsConverter(this.this$0.isOffline, null, this.this$0.configuration, 2, null);
            }
        });
    }

    public /* synthetic */ LibraryDetailPageConverter(Context context, SingleBaseModelConverter singleBaseModelConverter, LibraryDetailHeaderConverter libraryDetailHeaderConverter, boolean z, LibraryDetailRefinementsConverter libraryDetailRefinementsConverter, LibraryDetailButtonNavigatorConverter libraryDetailButtonNavigatorConverter, PlaylistDetailAddSongsButtonNavigatorConverter playlistDetailAddSongsButtonNavigatorConverter, ContainerModelConfiguration containerModelConfiguration, CachedCapabilityActionProviderImpl cachedCapabilityActionProviderImpl, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, singleBaseModelConverter, libraryDetailHeaderConverter, (i & 8) != 0 ? false : z, (i & 16) != 0 ? null : libraryDetailRefinementsConverter, (i & 32) != 0 ? null : libraryDetailButtonNavigatorConverter, (i & 64) != 0 ? null : playlistDetailAddSongsButtonNavigatorConverter, (i & 128) != 0 ? null : containerModelConfiguration, (i & 256) != 0 ? null : cachedCapabilityActionProviderImpl);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(AbstractItem abstractItem, List list) {
        return convert2(abstractItem, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: compiled from: LibraryDetailPageConverter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryDetailPageConverter$Companion;", "", "()V", "ALL_ACCESS_USER_PLAYLIST_COMPLIANT_MAX", "", "getALL_ACCESS_USER_PLAYLIST_COMPLIANT_MAX", "()I", "ALL_ACCESS_USER_PLAYLIST_COMPLIANT_MIN", "getALL_ACCESS_USER_PLAYLIST_COMPLIANT_MIN", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getALL_ACCESS_USER_PLAYLIST_COMPLIANT_MIN() {
            return LibraryDetailPageConverter.ALL_ACCESS_USER_PLAYLIST_COMPLIANT_MIN;
        }

        public final int getALL_ACCESS_USER_PLAYLIST_COMPLIANT_MAX() {
            return LibraryDetailPageConverter.ALL_ACCESS_USER_PLAYLIST_COMPLIANT_MAX;
        }
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public PageGridViewModel convert2(AbstractItem data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album) {
            return convertAlbum((Album) data, children);
        }
        if (data instanceof Artist) {
            return convertArtist((Artist) data, children);
        }
        if (data instanceof Playlist ? true : data instanceof CatalogPlaylist) {
            return convertPlaylist(data, children);
        }
        return null;
    }

    private final PageGridViewModel convertAlbum(Album album, List<? extends BaseViewModel> children) {
        return new PageGridViewModel(null, null, album.getTitle(), null, children == null ? CollectionsKt__CollectionsKt.emptyList() : children, 2, 0, "", null, null, null, 1803, null);
    }

    private final PageGridViewModel convertArtist(Artist artist, List<? extends BaseViewModel> children) {
        return new PageGridViewModel(null, null, artist.getName(), null, children == null ? CollectionsKt__CollectionsKt.emptyList() : children, 2, 0, "", null, null, null, 1803, null);
    }

    private final PageGridViewModel convertPlaylist(AbstractItem playlist, List<? extends BaseViewModel> children) {
        String title;
        if (playlist instanceof Playlist) {
            title = ((Playlist) playlist).getName();
        } else {
            title = playlist instanceof CatalogPlaylist ? ((CatalogPlaylist) playlist).getTitle() : null;
        }
        return new PageGridViewModel(null, null, title, null, children == null ? CollectionsKt__CollectionsKt.emptyList() : children, 2, 0, "", null, null, null, 1803, null);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(AbstractItem data) {
        Intrinsics.checkNotNullParameter(data, "data");
        BaseViewModel baseViewModelConvert = this.headerConverter.convert(data);
        LibraryDetailRefinementsConverter libraryDetailRefinementsConverter = this.refinementConverter;
        if (libraryDetailRefinementsConverter == null) {
            libraryDetailRefinementsConverter = new LibraryDetailRefinementsConverter(this.isOffline, null, null, 6, null);
        }
        return CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new BaseViewModel[]{baseViewModelConvert, libraryDetailRefinementsConverter.convert(data), new LibraryDetailSectionGridConverter(this.contentConverter).convert2(data, (List<? extends BaseViewModel>) new LibraryDetailSectionGridConverter(this.contentConverter).getChildrenModels(data))});
    }

    public final List<BaseViewModel> getAlbumChildrenModels(AbstractItem data, List<? extends MusicTrack> tracks) {
        Intrinsics.checkNotNullParameter(data, "data");
        BaseViewModel baseViewModelConvert = this.headerConverter.convert(data);
        LibraryDetailRefinementsConverter libraryDetailRefinementsConverter = this.refinementConverter;
        if (libraryDetailRefinementsConverter == null) {
            libraryDetailRefinementsConverter = new LibraryDetailRefinementsConverter(this.isOffline, null, null, 6, null);
        }
        BaseViewModel baseViewModelConvert2 = libraryDetailRefinementsConverter.convert(data);
        LibraryDetailSectionGridConverter libraryDetailSectionGridConverter = new LibraryDetailSectionGridConverter(this.contentConverter);
        LibraryDetailSectionGridConverter libraryDetailSectionGridConverter2 = new LibraryDetailSectionGridConverter(this.contentConverter);
        if (tracks == null) {
            tracks = CollectionsKt__CollectionsKt.emptyList();
        }
        return CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new BaseViewModel[]{baseViewModelConvert, baseViewModelConvert2, libraryDetailSectionGridConverter.convert2(data, (List<? extends BaseViewModel>) libraryDetailSectionGridConverter2.getChildrenModelsWithTracks(tracks))});
    }

    public final List<BaseViewModel> getArtistChildrenModels(AbstractItem data, List<? extends Album> albums, String followerCount, String catalogArtistName) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(albums, "albums");
        BaseViewModel baseViewModelConvert = this.headerConverter.convert(data);
        PageHeaderModel pageHeaderModel = baseViewModelConvert instanceof PageHeaderModel ? (PageHeaderModel) baseViewModelConvert : null;
        if (pageHeaderModel != null) {
            if (catalogArtistName != null) {
                pageHeaderModel.setHeadlineTitle(catalogArtistName);
            }
            if (followerCount != null) {
                pageHeaderModel.setSecondaryTitle(followerCount);
            }
        }
        BaseViewModel baseViewModelConvert2 = this.isOffline ? null : new LibraryDetailRefinementsConverter(false, null, null, 7, null).convert(data);
        Context context = this.context;
        TextRowItemModel textRowItemModel = new TextRowItemModel(null, context == null ? null : context.getString(R.string.dmusic_library_songs_tab), null, null, null, false, 61, null);
        LibraryDetailSectionGridConverter libraryDetailSectionGridConverter = new LibraryDetailSectionGridConverter(this.contentConverter);
        ArrayList arrayList = new ArrayList();
        for (Album album : albums) {
            Collection<MusicTrack> tracks = album.getTracks();
            List<? extends MusicTrack> list = tracks instanceof List ? (List) tracks : null;
            if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                Collection<MusicTrack> tracks2 = album.getTracks();
                if (tracks2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<com.amazon.mp3.library.item.MusicTrack>");
                }
                list = (List) tracks2;
                LikesServiceProxy.INSTANCE.getLikesStateForMusicTrack(this.context, list);
            }
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : list) {
                    if (((AbstractItem) obj).isInLibrary()) {
                        arrayList2.add(obj);
                    }
                }
                List<BaseViewModel> sectionChildrenModels = libraryDetailSectionGridConverter.getSectionChildrenModels(arrayList2);
                if (sectionChildrenModels != null) {
                    arrayList.addAll(sectionChildrenModels);
                }
            }
        }
        return CollectionsKt__IterablesKt.flatten(CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new List[]{CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new BaseViewModel[]{baseViewModelConvert, baseViewModelConvert2, textRowItemModel}), arrayList}));
    }

    private final LibraryDetailRefinementsConverter getRefinementsConverter() {
        return (LibraryDetailRefinementsConverter) this.refinementsConverter.getValue();
    }

    public final List<BaseViewModel> getPlaylistChildrenModels(AbstractItem data, List<? extends MusicTrack> trackList) {
        MusicTrack musicTrack;
        TextRowItemModel textRowItemModel;
        EmptyStateModel emptyStateModelForPlaylist;
        String string;
        String string2;
        String string3;
        HighlightableMessageModel highlightableMessageModel;
        Intrinsics.checkNotNullParameter(data, "data");
        boolean z = ((trackList != null && (musicTrack = (MusicTrack) CollectionsKt___CollectionsKt.firstOrNull((List) trackList)) != null) ? musicTrack.getAssetType() : null) == PrimePlaylistTracksTable.AssetType.VIDEO;
        BaseViewModel baseViewModelConvert = this.headerConverter.convert(data);
        BaseViewModel baseViewModelConvert2 = getRefinementsConverter().convert(data);
        LibraryDetailButtonNavigatorConverter libraryDetailButtonNavigatorConverter = this.buttonNavigatorConverter;
        BaseViewModel baseViewModelConvert3 = libraryDetailButtonNavigatorConverter == null ? null : libraryDetailButtonNavigatorConverter.convert(data);
        boolean z2 = data instanceof Playlist;
        Playlist playlist = z2 ? (Playlist) data : null;
        int trackCount = playlist == null ? 0 : (int) playlist.getTrackCount();
        GetOnDemandPlaylistsService getOnDemandPlaylistsService = GetOnDemandPlaylistsService.INSTANCE.get();
        Playlist playlist2 = z2 ? (Playlist) data : null;
        boolean zIsOnDemandPlaylist = getOnDemandPlaylistsService.isOnDemandPlaylist(playlist2 == null ? null : playlist2.getLuid());
        PlaylistDetailAddSongsButtonNavigatorConverter playlistDetailAddSongsButtonNavigatorConverter = this.addSongsNavigatorConverter;
        BaseViewModel baseViewModelConvert22 = playlistDetailAddSongsButtonNavigatorConverter == null ? null : playlistDetailAddSongsButtonNavigatorConverter.convert(data);
        boolean zShouldShowShuffledWithSection = new PlaylistModelConfiguration(this.cachedActionProviderImpl).shouldShowShuffledWithSection(data);
        boolean zShouldShowTracklistPreview = new PlaylistModelConfiguration(this.cachedActionProviderImpl).shouldShowTracklistPreview(data);
        if (new PlaylistModelConfiguration(this.cachedActionProviderImpl).shouldShowShuffleText(data)) {
            Context context = this.context;
            textRowItemModel = new TextRowItemModel(null, context == null ? null : context.getString(R.string.dmusic_shuffle_playlist_text_SR), null, null, TextRowItemView.TextStyle.HEADER, false, 45, null);
        } else {
            textRowItemModel = null;
        }
        if (textRowItemModel != null) {
            textRowItemModel.setInsetHorizontally(true);
        }
        List<BaseViewModel> sectionChildrenModels = trackList == null ? null : new LibraryDetailSectionGridConverter(this.contentConverter).getSectionChildrenModels(trackList);
        ArrayList arrayList = new ArrayList();
        if (baseViewModelConvert != null) {
            arrayList.add(baseViewModelConvert);
        }
        if (((z || PlaylistUtil.isRecentlyAddedPlaylist(data.getContentUri()) || trackList == null || !(trackList.isEmpty() ^ true)) ? false : true) && baseViewModelConvert2 != null) {
            arrayList.add(baseViewModelConvert2);
        }
        if (baseViewModelConvert3 != null && !zIsOnDemandPlaylist) {
            arrayList.add(baseViewModelConvert3);
        }
        if (zIsOnDemandPlaylist && Feature.is_all_access_user_playlist_pdp_messaging_enabled.isEnabled() && (highlightableMessageModel = getHighlightableMessageModel(this.context, trackCount)) != null) {
            arrayList.add(highlightableMessageModel);
        }
        if (zShouldShowShuffledWithSection && isCatalogPlaylist(data)) {
            Context context2 = this.context;
            TextRowItemModel textRowItemModel2 = new TextRowItemModel(null, context2 == null ? null : context2.getString(R.string.dmusic_hide_tracklist_shuffle_text), null, null, TextRowItemView.TextStyle.HEADER, false, 45, null);
            textRowItemModel2.setInsetHorizontally(true);
            arrayList.add(textRowItemModel2);
            if (trackList != null) {
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(trackList, 10));
                for (MusicTrack musicTrack2 : trackList) {
                    arrayList2.add(new DescriptiveTextItemModel(musicTrack2.getTitle(), musicTrack2.getArtistName()));
                }
                Context context3 = this.context;
                arrayList.add(new DescriptiveTextModel(arrayList2, (context3 == null || (string3 = context3.getString(R.string.dmusic_hide_tracklist_footer_text)) == null) ? "" : string3, " • ", null, 0, 0, false, 120, null));
            }
            if (textRowItemModel != null) {
                textRowItemModel.setHidden(true);
            }
        }
        if (zShouldShowTracklistPreview && isCatalogPlaylist(data)) {
            Context context4 = this.context;
            String str = (context4 == null || (string = context4.getString(R.string.dmusic_preview_tracklist_button_primary)) == null) ? "" : string;
            Context context5 = this.context;
            arrayList.add(new MultiStateButtonModel(null, null, -2, str, (context5 == null || (string2 = context5.getString(R.string.dmusic_preview_tracklist_button_secondary)) == null) ? "" : string2, false, false, Boolean.TRUE, ButtonSizeKey.MEDIUM, ButtonStyleKey.GLASS_OUTLINE, SpacerKey.MEDIUM, SpacerKey.SMALL, 99, null));
        }
        if (textRowItemModel != null) {
            arrayList.add(textRowItemModel);
        }
        if (baseViewModelConvert22 != null && sectionChildrenModels != null && (!sectionChildrenModels.isEmpty())) {
            arrayList.add(baseViewModelConvert22);
        }
        if (sectionChildrenModels != null && (!sectionChildrenModels.isEmpty())) {
            if (zShouldShowShuffledWithSection && isCatalogPlaylist(data)) {
                for (Object obj : sectionChildrenModels) {
                    Hideable hideable = obj instanceof Hideable ? (Hideable) obj : null;
                    if (hideable != null) {
                        hideable.updateVisibility(true);
                    }
                }
            }
            arrayList.addAll(sectionChildrenModels);
        } else if (!zIsOnDemandPlaylist && (emptyStateModelForPlaylist = getEmptyStateModelForPlaylist(data instanceof CatalogPlaylist)) != null) {
            arrayList.add(emptyStateModelForPlaylist);
        }
        return arrayList;
    }

    private final EmptyStateModel getEmptyStateModelForPlaylist(boolean isCatalog) {
        Resources resources;
        String string;
        Resources resources2;
        EmptyStateButtonType.ADDSONGS addsongs;
        Resources resources3;
        String string2;
        Resources resources4;
        String str = null;
        if (isCatalog) {
            Context context = this.context;
            string = (context == null || (resources4 = context.getResources()) == null) ? null : resources4.getString(R.string.dmusic_library_empty_cirrus_bottom_txt);
            addsongs = null;
        } else if (Feature.pdp_add_songs_button_enabled.isEnabled()) {
            Context context2 = this.context;
            String str2 = "";
            if (context2 != null && (resources3 = context2.getResources()) != null && (string2 = resources3.getString(R.string.dmusic_playlist_context_add_songs)) != null) {
                str2 = string2;
            }
            addsongs = new EmptyStateButtonType.ADDSONGS(str2);
            string = "Lets add some songs to this playlist!";
        } else {
            Context context3 = this.context;
            string = (context3 == null || (resources = context3.getResources()) == null) ? null : resources.getString(R.string.dmusic_empty_songs_header);
            Context context4 = this.context;
            String string3 = (context4 == null || (resources2 = context4.getResources()) == null) ? null : resources2.getString(R.string.dmusic_empty_songs_subtext);
            addsongs = null;
            str = string3;
        }
        return getEmptyStateModel(string, str, addsongs);
    }

    private final EmptyStateModel getEmptyStateModel(String title, String message, EmptyStateButtonType buttonType) {
        if (title == null) {
            return null;
        }
        return new EmptyStateModel(title, message, buttonType);
    }

    private final HighlightableMessageModel getHighlightableMessageModel(Context context, int trackCount) {
        Resources resources;
        String string;
        int i = ALL_ACCESS_USER_PLAYLIST_COMPLIANT_MIN;
        if (trackCount < i) {
            int i2 = i - trackCount;
            String str = "To play on-demand and download this playlist, add " + i2 + " or more songs";
            String str2 = "";
            if (context != null && (resources = context.getResources()) != null && (string = resources.getString(R.string.dmusic_playlist_context_add_songs)) != null) {
                str2 = string;
            }
            return new HighlightableMessageModel(str, new ButtonType.ADD_SONGS(str2, ButtonStyleKey.SOLID, ButtonSizeKey.MEDIUM, SpacerKey.SMALL), String.valueOf(i2), trackCount == 0);
        }
        int i3 = ALL_ACCESS_USER_PLAYLIST_COMPLIANT_MAX;
        if (trackCount <= i3) {
            return null;
        }
        int i4 = trackCount - i3;
        return new HighlightableMessageModel("To play on-demand and download this playlist, remove " + i4 + " or more songs", null, String.valueOf(i4), false, 8, null);
    }

    private final boolean isCatalogPlaylist(AbstractItem data) {
        return (data instanceof CatalogPlaylist) && !((CatalogPlaylist) data).isSharedUserPlaylist();
    }
}
