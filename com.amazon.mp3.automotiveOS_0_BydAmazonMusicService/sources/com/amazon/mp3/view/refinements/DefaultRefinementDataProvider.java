package com.amazon.mp3.view.refinements;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinementsModelBuilder;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.refinement.sort.SortOrder;
import com.amazon.mp3.view.refinements.DefaultRefinementDataProvider;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultRefinementDataProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u000eJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\u0011R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\n\u0010\u0006R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/view/refinements/DefaultRefinementDataProvider;", "", "()V", "albumsDefaultRefinementData", "Lcom/amazon/mp3/view/refinements/DefaultRefinementDataProvider$DefaultRefinementData;", "getAlbumsDefaultRefinementData", "()Lcom/amazon/mp3/view/refinements/DefaultRefinementDataProvider$DefaultRefinementData;", "albumsDefaultRefinementData$delegate", "Lkotlin/Lazy;", "artistsDefaultRefinementData", "getArtistsDefaultRefinementData", "artistsDefaultRefinementData$delegate", "dateAddedDefaultRefinementDataMapping", "", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementsModelBuilder$RefinementType;", "defaultRefinementDataMapping", "pageTypeToRefinementTypeMapping", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "purchasedDefaultRefinementDataMapping", "getDefaultRefinementData", "refinementType", "pageType", "DefaultRefinementData", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultRefinementDataProvider {
    public static final DefaultRefinementDataProvider INSTANCE;

    /* JADX INFO: renamed from: albumsDefaultRefinementData$delegate, reason: from kotlin metadata */
    private static final Lazy albumsDefaultRefinementData;

    /* JADX INFO: renamed from: artistsDefaultRefinementData$delegate, reason: from kotlin metadata */
    private static final Lazy artistsDefaultRefinementData;
    private static final Map<RefinementsModelBuilder.RefinementType, DefaultRefinementData> dateAddedDefaultRefinementDataMapping;
    private static final Map<RefinementsModelBuilder.RefinementType, DefaultRefinementData> defaultRefinementDataMapping;
    private static final Map<PageType, RefinementsModelBuilder.RefinementType> pageTypeToRefinementTypeMapping;
    private static final Map<RefinementsModelBuilder.RefinementType, DefaultRefinementData> purchasedDefaultRefinementDataMapping;

    private DefaultRefinementDataProvider() {
    }

    static {
        DefaultRefinementDataProvider defaultRefinementDataProvider = new DefaultRefinementDataProvider();
        INSTANCE = defaultRefinementDataProvider;
        PageType pageType = PageType.CLOUD_LIBRARY_PLAYLISTS;
        RefinementsModelBuilder.RefinementType refinementType = RefinementsModelBuilder.RefinementType.LIBRARY_PLAYLISTS;
        PageType pageType2 = PageType.CLOUD_LIBRARY_SONGS;
        RefinementsModelBuilder.RefinementType refinementType2 = RefinementsModelBuilder.RefinementType.LIBRARY_SONGS;
        PageType pageType3 = PageType.CLOUD_LIBRARY_ARTISTS;
        RefinementsModelBuilder.RefinementType refinementType3 = RefinementsModelBuilder.RefinementType.LIBRARY_ARTISTS;
        PageType pageType4 = PageType.CLOUD_LIBRARY_ALBUMS;
        RefinementsModelBuilder.RefinementType refinementType4 = RefinementsModelBuilder.RefinementType.LIBRARY_ALBUMS;
        pageTypeToRefinementTypeMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(pageType, refinementType), TuplesKt.to(PageType.OFFLINE_LIBRARY_PLAYLISTS, refinementType), TuplesKt.to(pageType2, refinementType2), TuplesKt.to(PageType.OFFLINE_LIBRARY_SONGS, refinementType2), TuplesKt.to(pageType3, refinementType3), TuplesKt.to(PageType.OFFLINE_LIBRARY_ARTISTS, refinementType3), TuplesKt.to(pageType4, refinementType4), TuplesKt.to(PageType.OFFLINE_LIBRARY_ALBUMS, refinementType4));
        artistsDefaultRefinementData = LazyKt__LazyJVMKt.lazy(new Function0<DefaultRefinementData>() { // from class: com.amazon.mp3.view.refinements.DefaultRefinementDataProvider$artistsDefaultRefinementData$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DefaultRefinementDataProvider.DefaultRefinementData invoke() {
                if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
                    RefinementFilterType refinementFilterType = RefinementFilterType.DATE_ADDED;
                    return new DefaultRefinementDataProvider.DefaultRefinementData(refinementFilterType, "date_created", refinementFilterType.defaultSortOrder());
                }
                RefinementFilterType refinementFilterType2 = RefinementFilterType.ARTIST_NAME;
                return new DefaultRefinementDataProvider.DefaultRefinementData(refinementFilterType2, String.valueOf(CirrusMediaSource.ARTISTS_MAP.get("sort_name")), refinementFilterType2.defaultSortOrder());
            }
        });
        albumsDefaultRefinementData = LazyKt__LazyJVMKt.lazy(new Function0<DefaultRefinementData>() { // from class: com.amazon.mp3.view.refinements.DefaultRefinementDataProvider$albumsDefaultRefinementData$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DefaultRefinementDataProvider.DefaultRefinementData invoke() {
                if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
                    RefinementFilterType refinementFilterType = RefinementFilterType.DATE_ADDED;
                    return new DefaultRefinementDataProvider.DefaultRefinementData(refinementFilterType, "date_created", refinementFilterType.defaultSortOrder());
                }
                RefinementFilterType refinementFilterType2 = RefinementFilterType.ALBUM_NAME;
                return new DefaultRefinementDataProvider.DefaultRefinementData(refinementFilterType2, "sort_album", refinementFilterType2.defaultSortOrder());
            }
        });
        RefinementFilterType refinementFilterType = RefinementFilterType.TITLE;
        RefinementFilterType refinementFilterType2 = RefinementFilterType.LAST_TOUCHED;
        defaultRefinementDataMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementType4, defaultRefinementDataProvider.getAlbumsDefaultRefinementData()), TuplesKt.to(refinementType3, defaultRefinementDataProvider.getArtistsDefaultRefinementData()), TuplesKt.to(refinementType2, new DefaultRefinementData(refinementFilterType, "sort_title", refinementFilterType.defaultSortOrder())), TuplesKt.to(refinementType, new DefaultRefinementData(refinementFilterType2, "dateLastTouched", refinementFilterType2.defaultSortOrder())));
        RefinementFilterType refinementFilterType3 = RefinementFilterType.DATE_ADDED;
        dateAddedDefaultRefinementDataMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementType4, new DefaultRefinementData(refinementFilterType3, "date_created", refinementFilterType3.defaultSortOrder())), TuplesKt.to(refinementType3, new DefaultRefinementData(refinementFilterType3, "date_created", refinementFilterType3.defaultSortOrder())), TuplesKt.to(refinementType2, new DefaultRefinementData(refinementFilterType3, "date_created", refinementFilterType3.defaultSortOrder())));
        RefinementFilterType refinementFilterType4 = RefinementFilterType.PURCHASED;
        purchasedDefaultRefinementDataMapping = MapsKt__MapsKt.mapOf(TuplesKt.to(refinementType4, new DefaultRefinementData(refinementFilterType4, "ownership_status", refinementFilterType4.defaultSortOrder())), TuplesKt.to(refinementType2, new DefaultRefinementData(refinementFilterType4, "ownership_status", refinementFilterType4.defaultSortOrder())));
    }

    /* JADX INFO: compiled from: DefaultRefinementDataProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/view/refinements/DefaultRefinementDataProvider$DefaultRefinementData;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "refinementFilterType", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "getRefinementFilterType", "()Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "sortColumn", "Ljava/lang/String;", "getSortColumn", "()Ljava/lang/String;", "Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "sortOrder", "Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "getSortOrder", "()Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "<init>", "(Lcom/amazon/mp3/view/refinements/RefinementFilterType;Ljava/lang/String;Lcom/amazon/mp3/library/refinement/sort/SortOrder;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class DefaultRefinementData {
        private final RefinementFilterType refinementFilterType;
        private final String sortColumn;
        private final SortOrder sortOrder;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DefaultRefinementData)) {
                return false;
            }
            DefaultRefinementData defaultRefinementData = (DefaultRefinementData) other;
            return this.refinementFilterType == defaultRefinementData.refinementFilterType && Intrinsics.areEqual(this.sortColumn, defaultRefinementData.sortColumn) && this.sortOrder == defaultRefinementData.sortOrder;
        }

        public int hashCode() {
            return (((this.refinementFilterType.hashCode() * 31) + this.sortColumn.hashCode()) * 31) + this.sortOrder.hashCode();
        }

        public String toString() {
            return "DefaultRefinementData(refinementFilterType=" + this.refinementFilterType + ", sortColumn=" + this.sortColumn + ", sortOrder=" + this.sortOrder + ')';
        }

        public DefaultRefinementData(RefinementFilterType refinementFilterType, String sortColumn, SortOrder sortOrder) {
            Intrinsics.checkNotNullParameter(refinementFilterType, "refinementFilterType");
            Intrinsics.checkNotNullParameter(sortColumn, "sortColumn");
            Intrinsics.checkNotNullParameter(sortOrder, "sortOrder");
            this.refinementFilterType = refinementFilterType;
            this.sortColumn = sortColumn;
            this.sortOrder = sortOrder;
        }

        public final RefinementFilterType getRefinementFilterType() {
            return this.refinementFilterType;
        }

        public final String getSortColumn() {
            return this.sortColumn;
        }

        public final SortOrder getSortOrder() {
            return this.sortOrder;
        }
    }

    private final DefaultRefinementData getArtistsDefaultRefinementData() {
        return (DefaultRefinementData) artistsDefaultRefinementData.getValue();
    }

    private final DefaultRefinementData getAlbumsDefaultRefinementData() {
        return (DefaultRefinementData) albumsDefaultRefinementData.getValue();
    }

    public final DefaultRefinementData getDefaultRefinementData(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        RefinementsModelBuilder.RefinementType refinementType = pageTypeToRefinementTypeMapping.get(pageType);
        if (refinementType == null) {
            return null;
        }
        return INSTANCE.getDefaultRefinementData(refinementType);
    }

    public final DefaultRefinementData getDefaultRefinementData(RefinementsModelBuilder.RefinementType refinementType) {
        Intrinsics.checkNotNullParameter(refinementType, "refinementType");
        if (FMPMFeatureGating.SHOULD_MOVE_PURCHASED_TAB.isEnabled()) {
            DefaultRefinementData defaultRefinementData = purchasedDefaultRefinementDataMapping.get(refinementType);
            return defaultRefinementData == null ? defaultRefinementDataMapping.get(refinementType) : defaultRefinementData;
        }
        if (Feature.use_date_added_default_refinement.isEnabled()) {
            DefaultRefinementData defaultRefinementData2 = dateAddedDefaultRefinementDataMapping.get(refinementType);
            return defaultRefinementData2 == null ? defaultRefinementDataMapping.get(refinementType) : defaultRefinementData2;
        }
        return defaultRefinementDataMapping.get(refinementType);
    }
}
