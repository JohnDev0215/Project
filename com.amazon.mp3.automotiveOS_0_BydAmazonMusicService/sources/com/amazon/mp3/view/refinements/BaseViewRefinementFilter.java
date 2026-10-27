package com.amazon.mp3.view.refinements;

import android.content.Context;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.refinement.datasource.ListSource;
import com.amazon.mp3.library.refinement.sort.SortOrder;
import com.amazon.mp3.library.refinement.sort.list.ListSort;
import com.amazon.mp3.library.refinement.sort.list.ListSortAlphabetical;
import com.amazon.mp3.library.refinement.sort.list.ListSortNumeric;
import com.amazon.mp3.platform.providers.Coroutines;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.DescriptiveRowItemModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesJvmKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BaseViewRefinementFilter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J0\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J*\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u000eH\u0002J&\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$2\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010%\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020&2\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006'"}, d2 = {"Lcom/amazon/mp3/view/refinements/BaseViewRefinementFilter;", "", "()V", "applyAtmosFilter", "Lkotlin/sequences/Sequence;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "elementsToFilter", "applyDownloadedFilter", "applyFiltersToModels", "", "filtersToApply", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "context", "Landroid/content/Context;", "applyLyricsFilter", "applyMyMusicFilter", "applyPurchasedFilter", "applyRA360Filter", "applySortStrategy", "elementsToSort", "sortStrategy", "Lcom/amazon/mp3/library/refinement/sort/list/ListSort;", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "applyUHDFilter", "applyUploadedFilter", "isTrackUnavailableOnDisk", "", "trackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "updateMissingSdCardIcon", "", "isDownloadedFilterApplied", ContextMappingConstants.ELEMENTS, "updateSDCardVisibilityForDescriptiveRowItemModel", "model", "Lcom/amazon/music/views/library/models/DescriptiveRowItemModel;", "updateSDCardVisibilityForVisualRowItemModel", "Lcom/amazon/music/views/library/models/VisualRowItemModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseViewRefinementFilter {

    /* JADX INFO: compiled from: BaseViewRefinementFilter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RefinementFilterType.values().length];
            iArr[RefinementFilterType.DOWNLOADED.ordinal()] = 1;
            iArr[RefinementFilterType.LIBRARY.ordinal()] = 2;
            iArr[RefinementFilterType.PURCHASED.ordinal()] = 3;
            iArr[RefinementFilterType.ULTRA_HD.ordinal()] = 4;
            iArr[RefinementFilterType.ATMOS.ordinal()] = 5;
            iArr[RefinementFilterType.RA360.ordinal()] = 6;
            iArr[RefinementFilterType.LYRICS.ordinal()] = 7;
            iArr[RefinementFilterType.UPLOADED.ordinal()] = 8;
            iArr[RefinementFilterType.ALBUM_NAME.ordinal()] = 9;
            iArr[RefinementFilterType.ARTIST_NAME.ordinal()] = 10;
            iArr[RefinementFilterType.DATE_ADDED.ordinal()] = 11;
            iArr[RefinementFilterType.DURATION.ordinal()] = 12;
            iArr[RefinementFilterType.POPULARITY.ordinal()] = 13;
            iArr[RefinementFilterType.TITLE.ordinal()] = 14;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final List<BaseViewModel> applyFiltersToModels(List<? extends BaseViewModel> elementsToFilter, Set<? extends RefinementFilterType> filtersToApply, Context context) {
        Intrinsics.checkNotNullParameter(elementsToFilter, "elementsToFilter");
        Intrinsics.checkNotNullParameter(filtersToApply, "filtersToApply");
        Intrinsics.checkNotNullParameter(context, "context");
        Sequence<BaseViewModel> sequenceAsSequence = CollectionsKt___CollectionsKt.asSequence(elementsToFilter);
        for (RefinementFilterType refinementFilterType : filtersToApply) {
            switch (WhenMappings.$EnumSwitchMapping$0[refinementFilterType.ordinal()]) {
                case 1:
                    sequenceAsSequence = applyDownloadedFilter(sequenceAsSequence);
                    break;
                case 2:
                    sequenceAsSequence = applyMyMusicFilter(sequenceAsSequence);
                    break;
                case 3:
                    sequenceAsSequence = applyPurchasedFilter(sequenceAsSequence);
                    break;
                case 4:
                    sequenceAsSequence = applyUHDFilter(sequenceAsSequence);
                    break;
                case 5:
                    sequenceAsSequence = applyAtmosFilter(sequenceAsSequence);
                    break;
                case 6:
                    sequenceAsSequence = applyRA360Filter(sequenceAsSequence);
                    break;
                case 7:
                    sequenceAsSequence = applyLyricsFilter(sequenceAsSequence);
                    break;
                case 8:
                    sequenceAsSequence = applyUploadedFilter(sequenceAsSequence);
                    break;
                case 9:
                    sequenceAsSequence = applySortStrategy(sequenceAsSequence, new ListSortAlphabetical(null, new Function1<BaseViewContentModel, String>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter$applyFiltersToModels$sortStrategy$1
                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(BaseViewContentModel it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            ContentMetadata metadata = it.getMetadata();
                            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                            if (trackMetadata == null) {
                                return null;
                            }
                            return trackMetadata.getAlbumTitle();
                        }
                    }, 1, null));
                    break;
                case 10:
                    sequenceAsSequence = applySortStrategy(sequenceAsSequence, new ListSortAlphabetical(null, new Function1<BaseViewContentModel, String>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter$applyFiltersToModels$sortStrategy$2
                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(BaseViewContentModel it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            ContentMetadata metadata = it.getMetadata();
                            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                            if (trackMetadata == null) {
                                return null;
                            }
                            return trackMetadata.getArtistTitle();
                        }
                    }, 1, null));
                    break;
                case 11:
                    sequenceAsSequence = applySortStrategy(sequenceAsSequence, new ListSortNumeric(refinementFilterType.defaultSortOrder(), new Function1<BaseViewContentModel, Long>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter$applyFiltersToModels$sortStrategy$3
                        @Override // kotlin.jvm.functions.Function1
                        public final Long invoke(BaseViewContentModel it) {
                            Long dateCreated;
                            Intrinsics.checkNotNullParameter(it, "it");
                            ContentMetadata metadata = it.getMetadata();
                            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                            long jLongValue = 0;
                            if (trackMetadata != null && (dateCreated = trackMetadata.getDateCreated()) != null) {
                                jLongValue = dateCreated.longValue();
                            }
                            return Long.valueOf(jLongValue);
                        }
                    }));
                    break;
                case 12:
                    sequenceAsSequence = applySortStrategy(sequenceAsSequence, new ListSortNumeric(null, new Function1<BaseViewContentModel, Integer>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter$applyFiltersToModels$sortStrategy$4
                        @Override // kotlin.jvm.functions.Function1
                        public final Integer invoke(BaseViewContentModel it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            ContentMetadata metadata = it.getMetadata();
                            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                            return Integer.valueOf(trackMetadata == null ? 0 : trackMetadata.getDuration());
                        }
                    }, 1, null));
                    break;
                case 13:
                    sequenceAsSequence = applySortStrategy(sequenceAsSequence, new ListSortNumeric(SortOrder.DESCENDING, new Function1<BaseViewContentModel, Double>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter$applyFiltersToModels$sortStrategy$5
                        @Override // kotlin.jvm.functions.Function1
                        public final Double invoke(BaseViewContentModel it) {
                            Double popularity;
                            Intrinsics.checkNotNullParameter(it, "it");
                            ContentMetadata metadata = it.getMetadata();
                            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                            double dDoubleValue = 0.0d;
                            if (trackMetadata != null && (popularity = trackMetadata.getPopularity()) != null) {
                                dDoubleValue = popularity.doubleValue();
                            }
                            return Double.valueOf(dDoubleValue);
                        }
                    }));
                    break;
                case 14:
                    sequenceAsSequence = applySortStrategy(sequenceAsSequence, new ListSortAlphabetical(null, new Function1<BaseViewContentModel, String>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter$applyFiltersToModels$sortStrategy$6
                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(BaseViewContentModel it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            ContentMetadata metadata = it.getMetadata();
                            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                            if (trackMetadata == null) {
                                return null;
                            }
                            return trackMetadata.getSortTitle();
                        }
                    }, 1, null));
                    break;
            }
        }
        updateMissingSdCardIcon(filtersToApply.contains(RefinementFilterType.DOWNLOADED), sequenceAsSequence, context);
        return SequencesKt___SequencesKt.toList(sequenceAsSequence);
    }

    private final Sequence<BaseViewModel> applyDownloadedFilter(Sequence<? extends BaseViewModel> elementsToFilter) {
        return SequencesKt___SequencesKt.filter(elementsToFilter, new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.applyDownloadedFilter.1
            /* JADX WARN: Removed duplicated region for block: B:18:0x002e  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(com.amazon.music.views.library.models.base.BaseViewModel r4) {
                /*
                    r3 = this;
                    java.lang.String r0 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                    boolean r0 = r4 instanceof com.amazon.music.views.library.models.base.BaseViewContentModel
                    r1 = 1
                    r2 = 0
                    if (r0 == 0) goto L2e
                    com.amazon.music.views.library.models.base.BaseViewContentModel r4 = (com.amazon.music.views.library.models.base.BaseViewContentModel) r4
                    com.amazon.music.views.library.metadata.ContentMetadata r4 = r4.getMetadata()
                    boolean r0 = r4 instanceof com.amazon.music.views.library.metadata.TrackMetadata
                    if (r0 == 0) goto L18
                    com.amazon.music.views.library.metadata.TrackMetadata r4 = (com.amazon.music.views.library.metadata.TrackMetadata) r4
                    goto L19
                L18:
                    r4 = 0
                L19:
                    if (r4 != 0) goto L1d
                L1b:
                    r4 = r2
                    goto L2b
                L1d:
                    java.lang.Integer r4 = r4.getDownloadState()
                    if (r4 != 0) goto L24
                    goto L1b
                L24:
                    int r4 = r4.intValue()
                    if (r4 != 0) goto L1b
                    r4 = r1
                L2b:
                    if (r4 == 0) goto L2e
                    goto L2f
                L2e:
                    r1 = r2
                L2f:
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r1)
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.C04111.invoke(com.amazon.music.views.library.models.base.BaseViewModel):java.lang.Boolean");
            }
        });
    }

    private final Sequence<BaseViewModel> applyMyMusicFilter(Sequence<? extends BaseViewModel> elementsToFilter) {
        return SequencesKt___SequencesKt.filter(elementsToFilter, new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.applyMyMusicFilter.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BaseViewModel it) {
                Intrinsics.checkNotNullParameter(it, "it");
                boolean z = false;
                if (it instanceof BaseViewContentModel) {
                    ContentMetadata metadata = ((BaseViewContentModel) it).getMetadata();
                    TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                    if (trackMetadata == null ? false : Intrinsics.areEqual(trackMetadata.getIsInLibrary(), Boolean.TRUE)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final Sequence<BaseViewModel> applyPurchasedFilter(Sequence<? extends BaseViewModel> elementsToFilter) {
        return SequencesKt___SequencesKt.filter(elementsToFilter, new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.applyPurchasedFilter.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BaseViewModel it) {
                Intrinsics.checkNotNullParameter(it, "it");
                boolean z = false;
                if (it instanceof BaseViewContentModel) {
                    ContentMetadata metadata = ((BaseViewContentModel) it).getMetadata();
                    TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                    if (trackMetadata == null ? false : Intrinsics.areEqual(trackMetadata.getIsOwned(), Boolean.TRUE)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final Sequence<BaseViewModel> applyUHDFilter(Sequence<? extends BaseViewModel> elementsToFilter) {
        return SequencesKt___SequencesKt.filter(elementsToFilter, new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.applyUHDFilter.1
            /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(com.amazon.music.views.library.models.base.BaseViewModel r4) {
                /*
                    r3 = this;
                    java.lang.String r0 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                    boolean r0 = r4 instanceof com.amazon.music.views.library.models.base.BaseViewContentModel
                    r1 = 1
                    r2 = 0
                    if (r0 == 0) goto L31
                    com.amazon.music.views.library.models.base.BaseViewContentModel r4 = (com.amazon.music.views.library.models.base.BaseViewContentModel) r4
                    com.amazon.music.views.library.metadata.ContentMetadata r4 = r4.getMetadata()
                    boolean r0 = r4 instanceof com.amazon.music.views.library.metadata.TrackMetadata
                    if (r0 == 0) goto L18
                    com.amazon.music.views.library.metadata.TrackMetadata r4 = (com.amazon.music.views.library.metadata.TrackMetadata) r4
                    goto L19
                L18:
                    r4 = 0
                L19:
                    if (r4 != 0) goto L1d
                L1b:
                    r4 = r2
                    goto L2e
                L1d:
                    java.util.List r4 = r4.getContentEncodings()
                    if (r4 != 0) goto L24
                    goto L1b
                L24:
                    java.lang.String r0 = "uhdAvailable"
                    boolean r4 = r4.contains(r0)
                    if (r4 != r1) goto L1b
                    r4 = r1
                L2e:
                    if (r4 == 0) goto L31
                    goto L32
                L31:
                    r1 = r2
                L32:
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r1)
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.C04161.invoke(com.amazon.music.views.library.models.base.BaseViewModel):java.lang.Boolean");
            }
        });
    }

    private final Sequence<BaseViewModel> applyAtmosFilter(Sequence<? extends BaseViewModel> elementsToFilter) {
        return SequencesKt___SequencesKt.filter(elementsToFilter, new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.applyAtmosFilter.1
            /* JADX WARN: Removed duplicated region for block: B:18:0x0030  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(com.amazon.music.views.library.models.base.BaseViewModel r4) {
                /*
                    r3 = this;
                    java.lang.String r0 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                    boolean r0 = r4 instanceof com.amazon.music.views.library.models.base.BaseViewContentModel
                    r1 = 1
                    r2 = 0
                    if (r0 == 0) goto L30
                    com.amazon.music.views.library.models.base.BaseViewContentModel r4 = (com.amazon.music.views.library.models.base.BaseViewContentModel) r4
                    com.amazon.music.views.library.metadata.ContentMetadata r4 = r4.getMetadata()
                    boolean r0 = r4 instanceof com.amazon.music.views.library.metadata.TrackMetadata
                    if (r0 == 0) goto L18
                    com.amazon.music.views.library.metadata.TrackMetadata r4 = (com.amazon.music.views.library.metadata.TrackMetadata) r4
                    goto L19
                L18:
                    r4 = 0
                L19:
                    if (r4 != 0) goto L1d
                L1b:
                    r4 = r2
                    goto L2d
                L1d:
                    java.util.List r4 = r4.getContentEncodings()
                    if (r4 != 0) goto L24
                    goto L1b
                L24:
                    java.lang.String r0 = "atmosAvailable"
                    boolean r4 = r4.contains(r0)
                    if (r4 != r1) goto L1b
                    r4 = r1
                L2d:
                    if (r4 == 0) goto L30
                    goto L31
                L30:
                    r1 = r2
                L31:
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r1)
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.AnonymousClass1.invoke(com.amazon.music.views.library.models.base.BaseViewModel):java.lang.Boolean");
            }
        });
    }

    private final Sequence<BaseViewModel> applyRA360Filter(Sequence<? extends BaseViewModel> elementsToFilter) {
        return SequencesKt___SequencesKt.filter(elementsToFilter, new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.applyRA360Filter.1
            /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(com.amazon.music.views.library.models.base.BaseViewModel r4) {
                /*
                    r3 = this;
                    java.lang.String r0 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                    boolean r0 = r4 instanceof com.amazon.music.views.library.models.base.BaseViewContentModel
                    r1 = 1
                    r2 = 0
                    if (r0 == 0) goto L31
                    com.amazon.music.views.library.models.base.BaseViewContentModel r4 = (com.amazon.music.views.library.models.base.BaseViewContentModel) r4
                    com.amazon.music.views.library.metadata.ContentMetadata r4 = r4.getMetadata()
                    boolean r0 = r4 instanceof com.amazon.music.views.library.metadata.TrackMetadata
                    if (r0 == 0) goto L18
                    com.amazon.music.views.library.metadata.TrackMetadata r4 = (com.amazon.music.views.library.metadata.TrackMetadata) r4
                    goto L19
                L18:
                    r4 = 0
                L19:
                    if (r4 != 0) goto L1d
                L1b:
                    r4 = r2
                    goto L2e
                L1d:
                    java.util.List r4 = r4.getContentEncodings()
                    if (r4 != 0) goto L24
                    goto L1b
                L24:
                    java.lang.String r0 = "ra360Available"
                    boolean r4 = r4.contains(r0)
                    if (r4 != r1) goto L1b
                    r4 = r1
                L2e:
                    if (r4 == 0) goto L31
                    goto L32
                L31:
                    r1 = r2
                L32:
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r1)
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.C04151.invoke(com.amazon.music.views.library.models.base.BaseViewModel):java.lang.Boolean");
            }
        });
    }

    private final Sequence<BaseViewModel> applyLyricsFilter(Sequence<? extends BaseViewModel> elementsToFilter) {
        return SequencesKt___SequencesKt.filter(elementsToFilter, new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.applyLyricsFilter.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BaseViewModel it) {
                Intrinsics.checkNotNullParameter(it, "it");
                boolean z = false;
                if (it instanceof BaseViewContentModel) {
                    ContentMetadata metadata = ((BaseViewContentModel) it).getMetadata();
                    TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                    if (trackMetadata == null ? false : Intrinsics.areEqual(trackMetadata.getHasLyrics(), Boolean.TRUE)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final Sequence<BaseViewModel> applyUploadedFilter(Sequence<? extends BaseViewModel> elementsToFilter) {
        return SequencesKt___SequencesKt.filter(elementsToFilter, new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.applyUploadedFilter.1
            /* JADX WARN: Removed duplicated region for block: B:15:0x0027  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(com.amazon.music.views.library.models.base.BaseViewModel r4) {
                /*
                    r3 = this;
                    java.lang.String r0 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                    boolean r0 = r4 instanceof com.amazon.music.views.library.models.base.BaseViewContentModel
                    r1 = 1
                    r2 = 0
                    if (r0 == 0) goto L27
                    com.amazon.music.views.library.models.base.BaseViewContentModel r4 = (com.amazon.music.views.library.models.base.BaseViewContentModel) r4
                    com.amazon.music.views.library.metadata.ContentMetadata r4 = r4.getMetadata()
                    boolean r0 = r4 instanceof com.amazon.music.views.library.metadata.TrackMetadata
                    if (r0 == 0) goto L18
                    com.amazon.music.views.library.metadata.TrackMetadata r4 = (com.amazon.music.views.library.metadata.TrackMetadata) r4
                    goto L19
                L18:
                    r4 = 0
                L19:
                    if (r4 != 0) goto L1d
                L1b:
                    r4 = r2
                    goto L24
                L1d:
                    boolean r4 = r4.getIsSideLoaded()
                    if (r4 != r1) goto L1b
                    r4 = r1
                L24:
                    if (r4 == 0) goto L27
                    goto L28
                L27:
                    r1 = r2
                L28:
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r1)
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.refinements.BaseViewRefinementFilter.C04171.invoke(com.amazon.music.views.library.models.base.BaseViewModel):java.lang.Boolean");
            }
        });
    }

    private final Sequence<BaseViewModel> applySortStrategy(Sequence<? extends BaseViewModel> elementsToSort, ListSort<BaseViewContentModel> sortStrategy) {
        ListSource listSource = new ListSource(SequencesKt___SequencesKt.toList(SequencesKt___SequencesJvmKt.filterIsInstance(elementsToSort, BaseViewContentModel.class)), null, null, 6, null);
        listSource.setSort(sortStrategy);
        return CollectionsKt___CollectionsKt.asSequence(listSource.query());
    }

    /* JADX INFO: renamed from: com.amazon.mp3.view.refinements.BaseViewRefinementFilter$updateMissingSdCardIcon$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BaseViewRefinementFilter.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.view.refinements.BaseViewRefinementFilter$updateMissingSdCardIcon$1", f = "BaseViewRefinementFilter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Sequence<BaseViewModel> $elements;
        final /* synthetic */ boolean $isDownloadedFilterApplied;
        int label;
        final /* synthetic */ BaseViewRefinementFilter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04181(Sequence<? extends BaseViewModel> sequence, BaseViewRefinementFilter baseViewRefinementFilter, boolean z, Context context, Continuation<? super C04181> continuation) {
            super(2, continuation);
            this.$elements = sequence;
            this.this$0 = baseViewRefinementFilter;
            this.$isDownloadedFilterApplied = z;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04181(this.$elements, this.this$0, this.$isDownloadedFilterApplied, this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            for (BaseViewModel baseViewModel : this.$elements) {
                if (baseViewModel instanceof VisualRowItemModel) {
                    this.this$0.updateSDCardVisibilityForVisualRowItemModel((VisualRowItemModel) baseViewModel, this.$isDownloadedFilterApplied, this.$context);
                } else if (baseViewModel instanceof DescriptiveRowItemModel) {
                    this.this$0.updateSDCardVisibilityForDescriptiveRowItemModel((DescriptiveRowItemModel) baseViewModel, this.$isDownloadedFilterApplied, this.$context);
                }
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateMissingSdCardIcon(boolean isDownloadedFilterApplied, Sequence<? extends BaseViewModel> elements, Context context) {
        BuildersKt__Builders_commonKt.launch$default(Coroutines.INSTANCE.getIoScope(), null, null, new C04181(elements, this, isDownloadedFilterApplied, context, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSDCardVisibilityForVisualRowItemModel(VisualRowItemModel model, boolean isDownloadedFilterApplied, Context context) {
        if (isDownloadedFilterApplied) {
            ContentMetadata metadata = model.getMetadata();
            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
            if (trackMetadata == null) {
                return;
            }
            model.updateSDCardVisibility(isTrackUnavailableOnDisk(trackMetadata, context));
            return;
        }
        model.updateSDCardVisibility(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSDCardVisibilityForDescriptiveRowItemModel(DescriptiveRowItemModel model, boolean isDownloadedFilterApplied, Context context) {
        if (isDownloadedFilterApplied) {
            ContentMetadata metadata = model.getMetadata();
            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
            if (trackMetadata == null) {
                return;
            }
            model.updateSDCardVisibility(isTrackUnavailableOnDisk(trackMetadata, context));
            return;
        }
        model.updateSDCardVisibility(false);
    }

    private final boolean isTrackUnavailableOnDisk(TrackMetadata trackMetadata, Context context) {
        AbstractItem item = new CirrusSourceLibraryItemFactory(context).getItem(trackMetadata.getUri());
        MusicTrack musicTrack = item instanceof MusicTrack ? (MusicTrack) item : null;
        return musicTrack != null && musicTrack.getOfflinePlaybackUnavailabilityReason() == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK;
    }
}
