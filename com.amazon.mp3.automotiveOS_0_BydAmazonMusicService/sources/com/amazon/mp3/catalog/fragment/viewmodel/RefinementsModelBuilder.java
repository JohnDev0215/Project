package com.amazon.mp3.catalog.fragment.viewmodel;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinementContentType;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.refinements.BaseViewsRefinementModelBuilder;
import com.amazon.mp3.view.refinements.DefaultRefinementDataProvider;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementsModelBuilder.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0011\u0018\u00002\u00020\u0001:\u00016B#\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u001d\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0002J\u001e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00062\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0012\u0010\"\u001a\u00020\u001f2\b\u0010#\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010$\u001a\u00020\u001fH\u0002J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001f0&H\u0002J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001f0&H\u0002J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001f0&H\u0002J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001f0&H\u0002J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001f0&H\u0002J\u000e\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001f0&H\u0002J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001f0&H\u0002J\u000e\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001f0&H\u0002J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00020\t0&H\u0002J\u0012\u0010/\u001a\u00020\u001f2\b\u0010#\u001a\u0004\u0018\u00010\u0006H\u0002J\u0016\u00100\u001a\u00020\u00132\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001f0&H\u0002J&\u00102\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u00062\b\u00103\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0006H\u0002J\u000e\u00105\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fR!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementsModelBuilder;", "", "refinementEvaluator", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementEvaluator;", "defaultFilters", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementEvaluator;Ljava/util/Set;)V", "audioQualityFilters", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementContentType$Refinement;", "getAudioQualityFilters", "()Ljava/util/Set;", "audioQualityFilters$delegate", "Lkotlin/Lazy;", "isOffline", "", "refinementsBuilder", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementModelBuilder;", "addModelToBuilder", "", PageUriUtils.FILTER_QUERY_PARAM, "isSelected", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "refinementType", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementsModelBuilder$RefinementType;", "createFilterModelItem", "Lcom/amazon/music/views/library/models/FilterItemModel;", "refinementFilterType", "isDefaultFilter", "filterOption", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementContentType;", "refinement", "initialRefinements", "getAlbumSortFilter", "defaultRefinementFilterType", "getDownloadedFilter", "getRefinementsListForAlbumDetailPage", "", "getRefinementsListForArtistDetailPage", "getRefinementsListForCatalogPlaylistDetailPage", "getRefinementsListForLibraryAlbums", "getRefinementsListForLibraryArtists", "getRefinementsListForLibraryPlaylists", "getRefinementsListForLibrarySongs", "getRefinementsListForUserPlaylistDetailPage", "getSortRefinementsListForLibraryPlaylists", "getTrackSortFilter", "parseRefinementContentType", "refinementContentType", "sortOption", "defaultSort", "initialSort", "useOfflineState", "RefinementType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementsModelBuilder {

    /* JADX INFO: renamed from: audioQualityFilters$delegate, reason: from kotlin metadata */
    private final Lazy audioQualityFilters;
    private final Set<RefinementFilterType> defaultFilters;
    private boolean isOffline;
    private final RefinementEvaluator refinementEvaluator;
    private final BaseViewsRefinementModelBuilder refinementsBuilder;

    /* JADX INFO: compiled from: RefinementsModelBuilder.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementsModelBuilder$RefinementType;", "", "(Ljava/lang/String;I)V", "CATALOG_PLAYLIST_DP", "USER_PLAYLIST_DP", "ALBUM_DP", "ARTIST_DP", "LIBRARY_SONGS", "LIBRARY_PLAYLISTS", "LIBRARY_ARTISTS", "LIBRARY_ALBUMS", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum RefinementType {
        CATALOG_PLAYLIST_DP,
        USER_PLAYLIST_DP,
        ALBUM_DP,
        ARTIST_DP,
        LIBRARY_SONGS,
        LIBRARY_PLAYLISTS,
        LIBRARY_ARTISTS,
        LIBRARY_ALBUMS
    }

    /* JADX INFO: compiled from: RefinementsModelBuilder.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[RefinementType.values().length];
            iArr[RefinementType.ALBUM_DP.ordinal()] = 1;
            iArr[RefinementType.CATALOG_PLAYLIST_DP.ordinal()] = 2;
            iArr[RefinementType.USER_PLAYLIST_DP.ordinal()] = 3;
            iArr[RefinementType.ARTIST_DP.ordinal()] = 4;
            iArr[RefinementType.LIBRARY_PLAYLISTS.ordinal()] = 5;
            iArr[RefinementType.LIBRARY_SONGS.ordinal()] = 6;
            iArr[RefinementType.LIBRARY_ARTISTS.ordinal()] = 7;
            iArr[RefinementType.LIBRARY_ALBUMS.ordinal()] = 8;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[RefinementGroupType.values().length];
            iArr2[RefinementGroupType.SORT.ordinal()] = 1;
            iArr2[RefinementGroupType.AUDIO_QUALITY.ordinal()] = 2;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public RefinementsModelBuilder() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RefinementsModelBuilder(RefinementEvaluator refinementEvaluator, Set<? extends RefinementFilterType> set) {
        this.refinementEvaluator = refinementEvaluator;
        this.defaultFilters = set;
        this.audioQualityFilters = LazyKt__LazyJVMKt.lazy(new Function0<Set<? extends RefinementContentType.Refinement>>() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.RefinementsModelBuilder$audioQualityFilters$2
            @Override // kotlin.jvm.functions.Function0
            public final Set<? extends RefinementContentType.Refinement> invoke() {
                if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
                    return SetsKt__SetsKt.setOf((Object[]) new RefinementContentType.Refinement[]{new RefinementContentType.Refinement(RefinementFilterType.ULTRA_HD, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.ATMOS, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.RA360, false, false, 6, null)});
                }
                return SetsKt__SetsKt.emptySet();
            }
        });
        this.refinementsBuilder = new BaseViewsRefinementModelBuilder();
    }

    public /* synthetic */ RefinementsModelBuilder(RefinementEvaluator refinementEvaluator, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : refinementEvaluator, (i & 2) != 0 ? null : set);
    }

    private final Set<RefinementContentType.Refinement> getAudioQualityFilters() {
        return (Set) this.audioQualityFilters.getValue();
    }

    public final RefinementsModelBuilder useOfflineState(boolean isOffline) {
        this.isOffline = isOffline;
        return this;
    }

    public final ExposedRefinementListModel build(RefinementType refinementType) {
        List<RefinementContentType> refinementsListForAlbumDetailPage;
        Intrinsics.checkNotNullParameter(refinementType, "refinementType");
        switch (WhenMappings.$EnumSwitchMapping$0[refinementType.ordinal()]) {
            case 1:
                refinementsListForAlbumDetailPage = getRefinementsListForAlbumDetailPage();
                break;
            case 2:
                refinementsListForAlbumDetailPage = getRefinementsListForCatalogPlaylistDetailPage();
                break;
            case 3:
                refinementsListForAlbumDetailPage = getRefinementsListForUserPlaylistDetailPage();
                break;
            case 4:
                refinementsListForAlbumDetailPage = getRefinementsListForArtistDetailPage();
                break;
            case 5:
                refinementsListForAlbumDetailPage = getRefinementsListForLibraryPlaylists();
                break;
            case 6:
                refinementsListForAlbumDetailPage = getRefinementsListForLibrarySongs();
                break;
            case 7:
                refinementsListForAlbumDetailPage = getRefinementsListForLibraryArtists();
                break;
            case 8:
                refinementsListForAlbumDetailPage = getRefinementsListForLibraryAlbums();
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        parseRefinementContentType(refinementsListForAlbumDetailPage);
        return this.refinementsBuilder.build();
    }

    private final List<RefinementContentType> getRefinementsListForAlbumDetailPage() {
        RefinementContentType.Refinement refinement = new RefinementContentType.Refinement(RefinementFilterType.DOWNLOADED, false, false, 6, null);
        List<RefinementContentType> listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new RefinementContentType.Group(RefinementGroupType.SORT, CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType.Refinement[]{new RefinementContentType.Refinement(RefinementFilterType.POPULARITY, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.DURATION, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.TITLE, false, false, 6, null)})), new RefinementContentType.Group(RefinementGroupType.AUDIO_QUALITY, CollectionsKt___CollectionsKt.toList(getAudioQualityFilters())), new RefinementContentType.Refinement(RefinementFilterType.LIBRARY, false, false, 6, null), refinement, new RefinementContentType.Refinement(RefinementFilterType.LYRICS, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.PURCHASED, false, false, 6, null));
        if (this.isOffline) {
            listMutableListOf.remove(refinement);
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listMutableListOf, 10));
        for (RefinementContentType refinementContentType : listMutableListOf) {
            RefinementContentType.Refinement refinement2 = refinementContentType instanceof RefinementContentType.Refinement ? (RefinementContentType.Refinement) refinementContentType : null;
            if (refinement2 != null) {
                Set<RefinementFilterType> set = this.defaultFilters;
                refinement2.setSelected(set != null && set.contains(refinement2.getType()));
            }
            arrayList.add(refinementContentType);
        }
        return arrayList;
    }

    private final List<RefinementContentType> getRefinementsListForCatalogPlaylistDetailPage() {
        RefinementContentType.Refinement refinement = new RefinementContentType.Refinement(RefinementFilterType.DOWNLOADED, false, false, 6, null);
        List<RefinementContentType> listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new RefinementContentType.Group(RefinementGroupType.SORT, CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType.Refinement[]{new RefinementContentType.Refinement(RefinementFilterType.ARTIST_NAME, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.DATE_ADDED, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.DURATION, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.TITLE, false, false, 6, null)})), new RefinementContentType.Refinement(RefinementFilterType.LIBRARY, false, false, 6, null), new RefinementContentType.Group(RefinementGroupType.AUDIO_QUALITY, CollectionsKt___CollectionsKt.toList(getAudioQualityFilters())), refinement, new RefinementContentType.Refinement(RefinementFilterType.LYRICS, false, false, 6, null));
        if (this.isOffline) {
            listMutableListOf.remove(refinement);
        }
        return listMutableListOf;
    }

    private final List<RefinementContentType> getRefinementsListForUserPlaylistDetailPage() {
        RefinementContentType.Refinement refinement = new RefinementContentType.Refinement(RefinementFilterType.DOWNLOADED, false, false, 6, null);
        List<RefinementContentType> listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new RefinementContentType.Group(RefinementGroupType.SORT, CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType.Refinement[]{new RefinementContentType.Refinement(RefinementFilterType.ARTIST_NAME, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.DATE_ADDED, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.DURATION, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.TITLE, false, false, 6, null)})), refinement, new RefinementContentType.Group(RefinementGroupType.AUDIO_QUALITY, CollectionsKt___CollectionsKt.toList(getAudioQualityFilters())), new RefinementContentType.Refinement(RefinementFilterType.LYRICS, false, false, 6, null));
        if (this.isOffline) {
            listMutableListOf.remove(refinement);
        }
        return listMutableListOf;
    }

    private final List<RefinementContentType> getRefinementsListForArtistDetailPage() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType[]{new RefinementContentType.Refinement(RefinementFilterType.LIBRARY, false, false, 6, null), getDownloadedFilter()});
    }

    private final List<RefinementContentType> getRefinementsListForLibraryPlaylists() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType[]{new RefinementContentType.Group(RefinementGroupType.SORT, getSortRefinementsListForLibraryPlaylists()), new RefinementContentType.Refinement(RefinementFilterType.BY_ME, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.FOLLOWING, false, false, 6, null), getDownloadedFilter(), new RefinementContentType.Group(RefinementGroupType.AUDIO_QUALITY, CollectionsKt___CollectionsKt.toList(getAudioQualityFilters()))});
    }

    private final List<RefinementContentType.Refinement> getSortRefinementsListForLibraryPlaylists() {
        DefaultRefinementDataProvider.DefaultRefinementData defaultRefinementData = DefaultRefinementDataProvider.INSTANCE.getDefaultRefinementData(RefinementType.LIBRARY_PLAYLISTS);
        RefinementFilterType refinementFilterType = defaultRefinementData == null ? null : defaultRefinementData.getRefinementFilterType();
        if (AmazonApplication.getCapabilities().isPlaylistsSortingEnabled()) {
            RefinementFilterType refinementFilterType2 = refinementFilterType;
            return CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType.Refinement[]{sortOption$default(this, RefinementFilterType.LAST_TOUCHED, refinementFilterType2, null, 4, null), sortOption$default(this, RefinementFilterType.LAST_MODIFIED, refinementFilterType2, null, 4, null), sortOption$default(this, RefinementFilterType.DATE_ADDED, refinementFilterType2, null, 4, null), sortOption$default(this, RefinementFilterType.TITLE, refinementFilterType2, null, 4, null), sortOption$default(this, RefinementFilterType.DURATION, refinementFilterType2, null, 4, null)});
        }
        RefinementFilterType refinementFilterType3 = refinementFilterType;
        return CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType.Refinement[]{sortOption$default(this, RefinementFilterType.TITLE, refinementFilterType3, null, 4, null), sortOption$default(this, RefinementFilterType.DURATION, refinementFilterType3, null, 4, null)});
    }

    private final List<RefinementContentType> getRefinementsListForLibrarySongs() {
        DefaultRefinementDataProvider.DefaultRefinementData defaultRefinementData = DefaultRefinementDataProvider.INSTANCE.getDefaultRefinementData(RefinementType.LIBRARY_SONGS);
        RefinementFilterType refinementFilterType = defaultRefinementData == null ? null : defaultRefinementData.getRefinementFilterType();
        if (FMPMFeatureGating.SHOULD_MOVE_PURCHASED_TAB.isEnabled()) {
            return CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType[]{getTrackSortFilter(refinementFilterType), new RefinementContentType.Refinement(RefinementFilterType.PURCHASED, false, false, 6, null), getDownloadedFilter(), new RefinementContentType.Group(RefinementGroupType.AUDIO_QUALITY, CollectionsKt___CollectionsKt.toList(getAudioQualityFilters())), new RefinementContentType.Refinement(RefinementFilterType.LYRICS, false, false, 6, null)});
        }
        return CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType[]{getTrackSortFilter(refinementFilterType), getDownloadedFilter(), new RefinementContentType.Group(RefinementGroupType.AUDIO_QUALITY, CollectionsKt___CollectionsKt.toList(getAudioQualityFilters())), new RefinementContentType.Refinement(RefinementFilterType.LYRICS, false, false, 6, null), new RefinementContentType.Refinement(RefinementFilterType.PURCHASED, false, false, 6, null)});
    }

    private final List<RefinementContentType> getRefinementsListForLibraryArtists() {
        DefaultRefinementDataProvider.DefaultRefinementData defaultRefinementData = DefaultRefinementDataProvider.INSTANCE.getDefaultRefinementData(RefinementType.LIBRARY_ARTISTS);
        RefinementFilterType refinementFilterType = defaultRefinementData == null ? null : defaultRefinementData.getRefinementFilterType();
        return CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType[]{new RefinementContentType.Group(RefinementGroupType.SORT, CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType.Refinement[]{sortOption$default(this, RefinementFilterType.ARTIST_NAME, refinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.DATE_ADDED, refinementFilterType, null, 4, null)})), getDownloadedFilter(), new RefinementContentType.Group(RefinementGroupType.AUDIO_QUALITY, CollectionsKt___CollectionsKt.toList(getAudioQualityFilters()))});
    }

    private final List<RefinementContentType> getRefinementsListForLibraryAlbums() {
        DefaultRefinementDataProvider.DefaultRefinementData defaultRefinementData = DefaultRefinementDataProvider.INSTANCE.getDefaultRefinementData(RefinementType.LIBRARY_ALBUMS);
        RefinementFilterType refinementFilterType = defaultRefinementData == null ? null : defaultRefinementData.getRefinementFilterType();
        if (FMPMFeatureGating.SHOULD_MOVE_PURCHASED_TAB.isEnabled()) {
            return CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType[]{getAlbumSortFilter(refinementFilterType), new RefinementContentType.Refinement(RefinementFilterType.PURCHASED, false, false, 6, null), getDownloadedFilter(), new RefinementContentType.Group(RefinementGroupType.AUDIO_QUALITY, CollectionsKt___CollectionsKt.toList(getAudioQualityFilters()))});
        }
        return CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType[]{getAlbumSortFilter(refinementFilterType), getDownloadedFilter(), new RefinementContentType.Group(RefinementGroupType.AUDIO_QUALITY, CollectionsKt___CollectionsKt.toList(getAudioQualityFilters())), new RefinementContentType.Refinement(RefinementFilterType.PURCHASED, false, false, 6, null)});
    }

    private final RefinementContentType filterOption(RefinementFilterType refinement, Set<? extends RefinementFilterType> initialRefinements) {
        return new RefinementContentType.Refinement(refinement, false, initialRefinements.contains(refinement), 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ com.amazon.mp3.catalog.fragment.viewmodel.RefinementContentType.Refinement sortOption$default(com.amazon.mp3.catalog.fragment.viewmodel.RefinementsModelBuilder r0, com.amazon.mp3.view.refinements.RefinementFilterType r1, com.amazon.mp3.view.refinements.RefinementFilterType r2, com.amazon.mp3.view.refinements.RefinementFilterType r3, int r4, java.lang.Object r5) {
        /*
            r4 = r4 & 4
            if (r4 == 0) goto L29
            java.util.Set<com.amazon.mp3.view.refinements.RefinementFilterType> r3 = r0.defaultFilters
            if (r3 != 0) goto L9
            goto L26
        L9:
            java.util.Iterator r3 = r3.iterator()
        Ld:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L21
            java.lang.Object r4 = r3.next()
            r5 = r4
            com.amazon.mp3.view.refinements.RefinementFilterType r5 = (com.amazon.mp3.view.refinements.RefinementFilterType) r5
            boolean r5 = r5.isSortType()
            if (r5 == 0) goto Ld
            goto L22
        L21:
            r4 = 0
        L22:
            com.amazon.mp3.view.refinements.RefinementFilterType r4 = (com.amazon.mp3.view.refinements.RefinementFilterType) r4
            if (r4 != 0) goto L28
        L26:
            r3 = r2
            goto L29
        L28:
            r3 = r4
        L29:
            com.amazon.mp3.catalog.fragment.viewmodel.RefinementContentType$Refinement r0 = r0.sortOption(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.viewmodel.RefinementsModelBuilder.sortOption$default(com.amazon.mp3.catalog.fragment.viewmodel.RefinementsModelBuilder, com.amazon.mp3.view.refinements.RefinementFilterType, com.amazon.mp3.view.refinements.RefinementFilterType, com.amazon.mp3.view.refinements.RefinementFilterType, int, java.lang.Object):com.amazon.mp3.catalog.fragment.viewmodel.RefinementContentType$Refinement");
    }

    private final RefinementContentType.Refinement sortOption(RefinementFilterType refinement, RefinementFilterType defaultSort, RefinementFilterType initialSort) {
        return new RefinementContentType.Refinement(refinement, defaultSort == refinement, initialSort == refinement);
    }

    private final RefinementContentType getDownloadedFilter() {
        Set<RefinementFilterType> set = this.defaultFilters;
        RefinementContentType refinementContentTypeFilterOption = set == null ? null : filterOption(RefinementFilterType.DOWNLOADED, set);
        return refinementContentTypeFilterOption == null ? new RefinementContentType.Refinement(RefinementFilterType.DOWNLOADED, false, false, 6, null) : refinementContentTypeFilterOption;
    }

    private final RefinementContentType getAlbumSortFilter(RefinementFilterType defaultRefinementFilterType) {
        if (FMPMFeatureGating.SHOULD_MOVE_PURCHASED_TAB.isEnabled()) {
            return new RefinementContentType.Group(RefinementGroupType.SORT, CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType.Refinement[]{sortOption$default(this, RefinementFilterType.PURCHASED, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.ALBUM_NAME, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.ARTIST_NAME, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.DATE_ADDED, defaultRefinementFilterType, null, 4, null)}));
        }
        return new RefinementContentType.Group(RefinementGroupType.SORT, CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType.Refinement[]{sortOption$default(this, RefinementFilterType.ALBUM_NAME, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.ARTIST_NAME, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.DATE_ADDED, defaultRefinementFilterType, null, 4, null)}));
    }

    private final RefinementContentType getTrackSortFilter(RefinementFilterType defaultRefinementFilterType) {
        if (FMPMFeatureGating.SHOULD_MOVE_PURCHASED_TAB.isEnabled()) {
            return new RefinementContentType.Group(RefinementGroupType.SORT, CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType.Refinement[]{sortOption$default(this, RefinementFilterType.PURCHASED, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.TITLE, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.ARTIST_NAME, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.ALBUM_NAME, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.DATE_ADDED, defaultRefinementFilterType, null, 4, null)}));
        }
        return new RefinementContentType.Group(RefinementGroupType.SORT, CollectionsKt__CollectionsKt.listOf((Object[]) new RefinementContentType.Refinement[]{sortOption$default(this, RefinementFilterType.TITLE, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.ARTIST_NAME, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.ALBUM_NAME, defaultRefinementFilterType, null, 4, null), sortOption$default(this, RefinementFilterType.DATE_ADDED, defaultRefinementFilterType, null, 4, null)}));
    }

    static /* synthetic */ FilterItemModel createFilterModelItem$default(RefinementsModelBuilder refinementsModelBuilder, RefinementFilterType refinementFilterType, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return refinementsModelBuilder.createFilterModelItem(refinementFilterType, z, z2);
    }

    private final FilterItemModel createFilterModelItem(RefinementFilterType refinementFilterType, boolean isDefaultFilter, boolean isSelected) {
        return new FilterItemModel(null, RefinementFilterType.INSTANCE.getDisplayName(refinementFilterType), RefinementFilterType.getSubtitleText$default(refinementFilterType, false, null, isDefaultFilter, 3, null), isSelected, null, Integer.valueOf(refinementFilterType.viewId()), isDefaultFilter, null, null);
    }

    private final void addModelToBuilder(RefinementFilterType filter, boolean isSelected) {
        this.refinementsBuilder.addFilterModel(filter.name(), null, RefinementFilterType.INSTANCE.getDisplayName(filter), isSelected, null, filter.viewId());
    }

    private final void parseRefinementContentType(List<? extends RefinementContentType> refinementContentType) {
        Object next;
        RefinementFilterType type;
        for (RefinementContentType refinementContentType2 : refinementContentType) {
            boolean z = false;
            if (refinementContentType2 instanceof RefinementContentType.Refinement) {
                RefinementEvaluator refinementEvaluator = this.refinementEvaluator;
                if (refinementEvaluator != null && !refinementEvaluator.isEligible(((RefinementContentType.Refinement) refinementContentType2).getType())) {
                    z = true;
                }
                if (!z) {
                    RefinementContentType.Refinement refinement = (RefinementContentType.Refinement) refinementContentType2;
                    addModelToBuilder(refinement.getType(), refinement.getIsSelected());
                }
            } else if (refinementContentType2 instanceof RefinementContentType.Group) {
                RefinementContentType.Group group = (RefinementContentType.Group) refinementContentType2;
                List<RefinementContentType.Refinement> refinements = group.getRefinements();
                ArrayList<RefinementContentType.Refinement> arrayList = new ArrayList();
                for (Object obj : refinements) {
                    RefinementContentType.Refinement refinement2 = (RefinementContentType.Refinement) obj;
                    RefinementEvaluator refinementEvaluator2 = this.refinementEvaluator;
                    if (!((refinementEvaluator2 == null || refinementEvaluator2.isEligible(refinement2.getType())) ? false : true)) {
                        arrayList.add(obj);
                    }
                }
                if (!arrayList.isEmpty()) {
                    int i = WhenMappings.$EnumSwitchMapping$1[group.getGroupType().ordinal()];
                    if (i == 1) {
                        BaseViewsRefinementModelBuilder baseViewsRefinementModelBuilder = this.refinementsBuilder;
                        String displayName = RefinementFilterType.INSTANCE.getDisplayName(RefinementFilterType.SORT);
                        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
                        for (RefinementContentType.Refinement refinement3 : arrayList) {
                            arrayList2.add(createFilterModelItem(refinement3.getType(), refinement3.getIsDefaultValue(), refinement3.getIsSelected()));
                        }
                        Iterator it = arrayList.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                next = it.next();
                                if (((RefinementContentType.Refinement) next).getIsDefaultValue()) {
                                    break;
                                }
                            } else {
                                next = null;
                                break;
                            }
                        }
                        RefinementContentType.Refinement refinement4 = (RefinementContentType.Refinement) next;
                        baseViewsRefinementModelBuilder.addFilterCollectionModel(null, displayName, arrayList2, false, (refinement4 == null || (type = refinement4.getType()) == null) ? null : createFilterModelItem$default(this, type, true, false, 4, null), true, Integer.valueOf(RefinementFilterType.SORT.viewId()));
                    } else if (i == 2) {
                        BaseViewsRefinementModelBuilder baseViewsRefinementModelBuilder2 = this.refinementsBuilder;
                        String displayName2 = RefinementFilterType.INSTANCE.getDisplayName(RefinementFilterType.AUDIO_QUALITY);
                        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            arrayList3.add(createFilterModelItem$default(this, ((RefinementContentType.Refinement) it2.next()).getType(), false, false, 6, null));
                        }
                        baseViewsRefinementModelBuilder2.addFilterCollectionModel(null, displayName2, arrayList3, false, null, false, Integer.valueOf(RefinementFilterType.AUDIO_QUALITY.viewId()));
                    }
                }
            }
        }
    }
}
