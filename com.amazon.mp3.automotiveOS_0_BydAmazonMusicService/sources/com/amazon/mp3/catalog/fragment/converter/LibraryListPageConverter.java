package com.amazon.mp3.catalog.fragment.converter;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.converter.LibraryFollowedArtistPageConverter;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinementsModelBuilder;
import com.amazon.mp3.library.cache.likes.LikesStateCacheManager;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.RefinementKt;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FeatureBarkerModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryListPageConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001BI\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010!\u0012\b\b\u0002\u0010$\u001a\u00020\b\u0012\u0010\b\u0002\u0010(\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b:\u0010;J4\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0003H\u0002J$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J&\u0010\u0015\u001a\u00020\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0002H\u0016J\u001e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J$\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00160\u00022\u0006\u0010\u0007\u001a\u00020\u0006J$\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00180\u00022\u0006\u0010\u0007\u001a\u00020\u0006J$\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00022\u0006\u0010\u0007\u001a\u00020\u0006J$\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00022\u0006\u0010\u0007\u001a\u00020\u0006R\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u001c\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u001c\u0010(\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001b\u00100\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u001b\u00103\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010-\u001a\u0004\b2\u0010/R\u001b\u00106\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u0010-\u001a\u0004\b5\u0010/R\u001b\u00109\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b7\u0010-\u001a\u0004\b8\u0010/¨\u0006<"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryListPageConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "", "Lcom/amazon/mp3/library/item/AbstractItem;", "data", "item", "", "pageIndex", "", "isDataEmpty", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "getChildrenModels", "Lcom/amazon/music/views/library/models/FeatureBarkerModel;", "getFeatureBarker", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "getRefinementListModel", "convertToBaseViewContentModel", "indexInPage", "getModelPosition", "children", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "convert", "Lcom/amazon/mp3/library/item/Playlist;", "getPlaylistChildrenModels", "Lcom/amazon/mp3/library/item/MusicTrack;", "getTracksChildrenModels", "Lcom/amazon/mp3/library/item/Artist;", "getArtistsChildrenModels", "Lcom/amazon/mp3/library/item/Album;", "getAlbumsChildrenModels", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "contentConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "withRefinements", "Z", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "defaultFilters", "Ljava/util/Set;", "pageSize", "Ljava/lang/Integer;", "librarySongsRefinementsModel$delegate", "Lkotlin/Lazy;", "getLibrarySongsRefinementsModel", "()Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "librarySongsRefinementsModel", "libraryPlaylistsRefinementsModel$delegate", "getLibraryPlaylistsRefinementsModel", "libraryPlaylistsRefinementsModel", "libraryAlbumsRefinementListModel$delegate", "getLibraryAlbumsRefinementListModel", "libraryAlbumsRefinementListModel", "libraryArtistRefinementListModel$delegate", "getLibraryArtistRefinementListModel", "libraryArtistRefinementListModel", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;ZLjava/util/Set;Ljava/lang/Integer;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryListPageConverter implements BaseContainerModelConverter<List<? extends AbstractItem>> {
    private final SingleBaseModelConverter<AbstractItem> contentConverter;
    private final Context context;
    private final Set<RefinementFilterType> defaultFilters;

    /* JADX INFO: renamed from: libraryAlbumsRefinementListModel$delegate, reason: from kotlin metadata */
    private final Lazy libraryAlbumsRefinementListModel;

    /* JADX INFO: renamed from: libraryArtistRefinementListModel$delegate, reason: from kotlin metadata */
    private final Lazy libraryArtistRefinementListModel;

    /* JADX INFO: renamed from: libraryPlaylistsRefinementsModel$delegate, reason: from kotlin metadata */
    private final Lazy libraryPlaylistsRefinementsModel;

    /* JADX INFO: renamed from: librarySongsRefinementsModel$delegate, reason: from kotlin metadata */
    private final Lazy librarySongsRefinementsModel;
    private final Integer pageSize;
    private final boolean withRefinements;

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(List<? extends AbstractItem> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LibraryListPageConverter(Context context, SingleBaseModelConverter<AbstractItem> singleBaseModelConverter, boolean z, Set<? extends RefinementFilterType> set, Integer num) {
        this.context = context;
        this.contentConverter = singleBaseModelConverter;
        this.withRefinements = z;
        this.defaultFilters = set;
        this.pageSize = num;
        this.librarySongsRefinementsModel = LazyKt__LazyJVMKt.lazy(new Function0<ExposedRefinementListModel>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryListPageConverter$librarySongsRefinementsModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ExposedRefinementListModel invoke() {
                return new RefinementsModelBuilder(null, this.this$0.defaultFilters, 1, null).build(RefinementsModelBuilder.RefinementType.LIBRARY_SONGS);
            }
        });
        this.libraryPlaylistsRefinementsModel = LazyKt__LazyJVMKt.lazy(new Function0<ExposedRefinementListModel>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryListPageConverter$libraryPlaylistsRefinementsModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ExposedRefinementListModel invoke() {
                return new RefinementsModelBuilder(null, this.this$0.defaultFilters, 1, null).build(RefinementsModelBuilder.RefinementType.LIBRARY_PLAYLISTS);
            }
        });
        this.libraryAlbumsRefinementListModel = LazyKt__LazyJVMKt.lazy(new Function0<ExposedRefinementListModel>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryListPageConverter$libraryAlbumsRefinementListModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ExposedRefinementListModel invoke() {
                return new RefinementsModelBuilder(null, this.this$0.defaultFilters, 1, null).build(RefinementsModelBuilder.RefinementType.LIBRARY_ALBUMS);
            }
        });
        this.libraryArtistRefinementListModel = LazyKt__LazyJVMKt.lazy(new Function0<ExposedRefinementListModel>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryListPageConverter$libraryArtistRefinementListModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ExposedRefinementListModel invoke() {
                return new RefinementsModelBuilder(null, this.this$0.defaultFilters, 1, null).build(RefinementsModelBuilder.RefinementType.LIBRARY_ARTISTS);
            }
        });
    }

    public /* synthetic */ LibraryListPageConverter(Context context, SingleBaseModelConverter singleBaseModelConverter, boolean z, Set set, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, singleBaseModelConverter, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : set, (i & 16) != 0 ? null : num);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(List<? extends AbstractItem> list, List list2) {
        return convert2(list, (List<? extends BaseViewModel>) list2);
    }

    private final ExposedRefinementListModel getLibrarySongsRefinementsModel() {
        return (ExposedRefinementListModel) this.librarySongsRefinementsModel.getValue();
    }

    private final ExposedRefinementListModel getLibraryPlaylistsRefinementsModel() {
        return (ExposedRefinementListModel) this.libraryPlaylistsRefinementsModel.getValue();
    }

    private final ExposedRefinementListModel getLibraryAlbumsRefinementListModel() {
        return (ExposedRefinementListModel) this.libraryAlbumsRefinementListModel.getValue();
    }

    private final ExposedRefinementListModel getLibraryArtistRefinementListModel() {
        return (ExposedRefinementListModel) this.libraryArtistRefinementListModel.getValue();
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public PageGridViewModel convert2(List<? extends AbstractItem> data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new PageGridViewModel(null, null, null, null, children == null ? CollectionsKt__CollectionsKt.emptyList() : children, LibraryLayoutTypeUtil.INSTANCE.getLayoutType(this.contentConverter), 0, "", null, null, null, 1807, null);
    }

    public final List<BaseViewModel> getPlaylistChildrenModels(List<? extends Playlist> data, int pageIndex) {
        Intrinsics.checkNotNullParameter(data, "data");
        boolean zIsEmpty = data.isEmpty();
        return getChildrenModels(data, !zIsEmpty ? (Playlist) CollectionsKt___CollectionsKt.first((List) data) : new Playlist(), pageIndex, zIsEmpty);
    }

    public final List<BaseViewModel> getTracksChildrenModels(List<? extends MusicTrack> data, int pageIndex) {
        Intrinsics.checkNotNullParameter(data, "data");
        LikesStateCacheManager instance$default = LikesStateCacheManager.Companion.getInstance$default(LikesStateCacheManager.INSTANCE, null, 1, null);
        boolean zIsEmpty = data.isEmpty();
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            try {
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(data, 10));
                Iterator<T> it = data.iterator();
                while (it.hasNext()) {
                    arrayList.add(((MusicTrack) it.next()).getAsin());
                }
                LikesServiceProxy.INSTANCE.getLikesStateForTrackAsinsAndCache(this.context, arrayList);
            } catch (Exception e) {
                String message = e.getMessage();
                if (message != null) {
                    Log.error("com.amazon.mp3.util.extensions.Context", message);
                }
            }
            for (MusicTrack musicTrack : data) {
                if (instance$default != null) {
                    String asin = musicTrack.getAsin();
                    if (!(asin == null || asin.length() == 0)) {
                        String asin2 = musicTrack.getAsin();
                        Intrinsics.checkNotNullExpressionValue(asin2, "it.asin");
                        Boolean bool = instance$default.get(asin2);
                        if (bool != null) {
                            musicTrack.setIsLiked(bool.booleanValue());
                        }
                    }
                }
            }
        }
        return getChildrenModels(data, !zIsEmpty ? (MusicTrack) CollectionsKt___CollectionsKt.first((List) data) : new MusicTrack(), pageIndex, zIsEmpty);
    }

    public final List<BaseViewModel> getArtistsChildrenModels(List<? extends Artist> data, int pageIndex) {
        Intrinsics.checkNotNullParameter(data, "data");
        boolean zIsEmpty = data.isEmpty();
        return getChildrenModels(data, !zIsEmpty ? (Artist) CollectionsKt___CollectionsKt.first((List) data) : new Artist(), pageIndex, zIsEmpty);
    }

    public final List<BaseViewModel> getAlbumsChildrenModels(List<? extends Album> data, int pageIndex) {
        Intrinsics.checkNotNullParameter(data, "data");
        boolean zIsEmpty = data.isEmpty();
        return getChildrenModels(data, !zIsEmpty ? (Album) CollectionsKt___CollectionsKt.first((List) data) : new Album(), pageIndex, zIsEmpty);
    }

    private final List<BaseViewModel> getChildrenModels(List<? extends AbstractItem> data, AbstractItem item, int pageIndex, boolean isDataEmpty) {
        ArrayList arrayList = new ArrayList();
        if (pageIndex == 0) {
            BaseViewModel baseViewModelConvert2 = new LibraryListPageHeaderConverter(this.context).convert(item);
            if (baseViewModelConvert2 != null) {
                arrayList.add(baseViewModelConvert2);
            }
            final ExposedRefinementListModel refinementListModel = getRefinementListModel(item);
            if (refinementListModel != null) {
                if (this.withRefinements) {
                    arrayList.add(refinementListModel);
                }
                if (isDataEmpty) {
                    Context context = this.context;
                    if (context != null) {
                        Resources resources = context.getResources();
                        Intrinsics.checkNotNullExpressionValue(resources, "it.resources");
                        BaseViewModel baseViewModelConvert22 = new LibraryListPageEmptyStateConverter(resources, new Function0<Boolean>() { // from class: com.amazon.mp3.catalog.fragment.converter.LibraryListPageConverter$getChildrenModels$2$1$1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                return Boolean.valueOf(RefinementKt.areRefinementsApplied(refinementListModel));
                            }
                        }, null, null, 12, null).convert(item);
                        if (baseViewModelConvert22 != null) {
                            arrayList.add(baseViewModelConvert22);
                        }
                    }
                    return arrayList;
                }
            }
        }
        arrayList.addAll(convertToBaseViewContentModel(data, pageIndex));
        if ((item instanceof Artist) && ConnectivityUtil.hasAnyInternetConnection(this.context) && AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled() && Build.VERSION.SDK_INT >= 28) {
            arrayList.add(getFeatureBarker());
        }
        return arrayList;
    }

    private final FeatureBarkerModel getFeatureBarker() {
        Resources resources;
        Configuration configuration;
        boolean zIsTabletDevice = BrowseFactory.isTabletDevice();
        LibraryFollowedArtistPageConverter.ATCIngressImageUrl aTCIngressImageUrl = LibraryFollowedArtistPageConverter.ATCIngressImageUrl.INSTANCE;
        String defaultATCIngressImage = aTCIngressImageUrl.getDefaultATCIngressImage(zIsTabletDevice);
        Context context = this.context;
        LocaleList locales = null;
        if (context != null && (resources = context.getResources()) != null && (configuration = resources.getConfiguration()) != null) {
            locales = configuration.getLocales();
        }
        if (locales != null && locales.size() > 0) {
            Locale locale = locales.get(0);
            Intrinsics.checkNotNullExpressionValue(locale, "localeList.get(0)");
            defaultATCIngressImage = aTCIngressImageUrl.getATCImageUrl(locale, zIsTabletDevice);
        }
        String str = defaultATCIngressImage;
        return new FeatureBarkerModel(null, "", null, null, str, str, null, null, true, 205, null);
    }

    private final ExposedRefinementListModel getRefinementListModel(AbstractItem item) {
        if (item instanceof Playlist) {
            return getLibraryPlaylistsRefinementsModel();
        }
        if (item instanceof MusicTrack) {
            return getLibrarySongsRefinementsModel();
        }
        if (item instanceof Artist) {
            return getLibraryArtistRefinementListModel();
        }
        if (item instanceof Album) {
            return getLibraryAlbumsRefinementListModel();
        }
        return null;
    }

    private final List<BaseViewModel> convertToBaseViewContentModel(List<? extends AbstractItem> data, int pageIndex) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : data) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            AbstractItem abstractItem = (AbstractItem) obj;
            int modelPosition = getModelPosition(i, pageIndex);
            SingleBaseModelConverter<AbstractItem> singleBaseModelConverter = this.contentConverter;
            BaseViewContentModel baseViewContentModel = null;
            if (singleBaseModelConverter instanceof LibraryVisualRowItemConverter) {
                BaseViewModel baseViewModelConvert = ((LibraryVisualRowItemConverter) singleBaseModelConverter).convert(abstractItem, Integer.valueOf(modelPosition));
                if (baseViewModelConvert instanceof BaseViewContentModel) {
                    baseViewContentModel = (BaseViewContentModel) baseViewModelConvert;
                }
            } else {
                BaseViewModel baseViewModelConvert2 = singleBaseModelConverter == null ? null : singleBaseModelConverter.convert(abstractItem);
                if (baseViewModelConvert2 instanceof BaseViewContentModel) {
                    baseViewContentModel = (BaseViewContentModel) baseViewModelConvert2;
                }
            }
            if (baseViewContentModel != null) {
                arrayList.add(baseViewContentModel);
            }
            i = i2;
        }
        return arrayList;
    }

    private final int getModelPosition(int indexInPage, int pageIndex) {
        Integer num = this.pageSize;
        return num == null ? indexInPage : (pageIndex * num.intValue()) + indexInPage;
    }
}
