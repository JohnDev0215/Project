package com.amazon.mp3.catalog.fragment.usecase;

import android.app.Application;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.brush.converters.BrushPodcastEpisodeConverter;
import com.amazon.mp3.brush.converters.BrushPodcastShowConverter;
import com.amazon.mp3.brush.converters.BrushRecentlyPlayedConverter;
import com.amazon.mp3.catalog.fragment.datasource.repository.BrushApiPageModelDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.BrushV3ApiPageModelDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository;
import com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.views.RxRecentlyPlayedRepository;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.podcast.PodcastEpisode;
import com.amazon.podcast.PodcastShow;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PageModelUseCase.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 ;2\u00020\u0001:\u0003<=;B\u0011\b\u0016\u0012\u0006\u00108\u001a\u000207¢\u0006\u0004\b9\u0010:J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u0002J(\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t\u0018\u00010\u0007J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\f\u001a\u00020\bJH\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00052\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t\u0018\u00010\u0007J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0004H\u0004J\u001e\u0010\u001c\u001a\u00020\u001b2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0005J\u001e\u0010\u001f\u001a\u00020\u001b2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0005J\u001e\u0010 \u001a\u00020\u001b2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0005J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u00042\u0006\u0010!\u001a\u00020\bH\u0002J\u001e\u0010&\u001a\u00020\u00052\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\t2\u0006\u0010\u001a\u001a\u00020\u0005H\u0002J\u0010\u0010'\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\bH\u0002R\u0016\u0010(\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010)R\u0016\u0010+\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010)R\u0016\u0010-\u001a\u00020,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0016\u0010!\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010/R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010/R\u0016\u0010\u0011\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010)R\"\u00101\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u0002000\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R*\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\t\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u00102R\u001a\u00105\u001a\b\u0012\u0004\u0012\u000204038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106¨\u0006>"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/usecase/PageModelUseCase;", "", "", "getCached", "Lrx/Observable;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getInitPageData", "", "", "", "refinementLists", "getFilteredPageDataBrushV3", "browseId", "getFilteredPageData", "", "pageIndex", "nextPageToken", "isArtistUpsell", "originalPage", "getMorePageData", "isPageDataExpired", "invalidateCachedPageData", "Ljava/lang/Void;", "getBootstrapObservable", "Lcom/amazon/podcast/PodcastEpisode;", "podcastEpisodes", "pageModel", "", "replacePodcastJumpBackInEpisodes", "Lcom/amazon/podcast/PodcastShow;", "podcastShows", "replacePodcastFollowedShows", "replacePodcastSavedEpisodes", "uri", "Lcom/amazon/mp3/catalog/fragment/usecase/PageModelUseCase$BrushVersion;", "getBrushVersion", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItem;", "recentItems", "replaceRecentModel", "isBrushV3Uri", "withBootStrap", "Z", "withRecentlyPlayed", "withRecentSearches", "Landroid/app/Application;", "application", "Landroid/app/Application;", "Ljava/lang/String;", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageModelDataRepository;", "brushRepositoryMap", "Ljava/util/Map;", "", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "latencyTrackingLegs", "Ljava/util/Set;", "Lcom/amazon/mp3/catalog/fragment/usecase/PageModelUseCase$Builder;", "builder", "<init>", "(Lcom/amazon/mp3/catalog/fragment/usecase/PageModelUseCase$Builder;)V", "Companion", "BrushVersion", "Builder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class PageModelUseCase {
    private Application application;
    private Map<BrushVersion, ? extends PageModelDataRepository> brushRepositoryMap;
    private boolean isArtistUpsell;
    private final Set<LatencyTrackingLeg> latencyTrackingLegs;
    private String nextPageToken;
    private Map<String, ? extends List<String>> refinementLists;
    private String uri;
    private boolean withBootStrap;
    private boolean withRecentSearches;
    private boolean withRecentlyPlayed;
    private static List<String> BRUSH_V3_URIS = CollectionsKt__CollectionsKt.mutableListOf("find", ParserUtil.GENRE_SEGMENT_NAME, "uhd", "3d", "videos", "ra360", "atmos", "new", "popular", "playlists", "uri://genre", "uri://uhd", "uri://3d", "uri://videos", "uri://new", "uri://popular", "uri://ra360", "uri://atmos", "uri://playlists");

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: PageModelUseCase.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/usecase/PageModelUseCase$BrushVersion;", "", "(Ljava/lang/String;I)V", "BRUSH_V2", "BRUSH_V3", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    enum BrushVersion {
        BRUSH_V2,
        BRUSH_V3
    }

    public PageModelUseCase(Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.withBootStrap = builder.getWithBootstrap();
        this.withRecentlyPlayed = builder.getWithRecentlyPlayed();
        this.withRecentSearches = builder.getWithRecentSearches();
        this.uri = builder.getUri();
        this.application = builder.getApplication();
        this.nextPageToken = builder.getNextPageToken();
        this.isArtistUpsell = builder.getIsArtistUpsell();
        Set<LatencyTrackingLeg> latencyEvents = builder.getLatencyEvents();
        latencyEvents = latencyEvents == null ? ArraysKt___ArraysKt.toSet(LatencyTrackingLeg.values()) : latencyEvents;
        this.latencyTrackingLegs = latencyEvents;
        this.brushRepositoryMap = MapsKt__MapsKt.mapOf(TuplesKt.to(BrushVersion.BRUSH_V3, new BrushV3ApiPageModelDataRepository(this.application)), TuplesKt.to(BrushVersion.BRUSH_V2, new BrushApiPageModelDataRepository(this.application, latencyEvents)));
        this.refinementLists = builder.getRefinementLists();
    }

    public static /* synthetic */ Observable getInitPageData$default(PageModelUseCase pageModelUseCase, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getInitPageData");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        return pageModelUseCase.getInitPageData(z);
    }

    public final Observable<PageGridViewModel> getInitPageData(final boolean getCached) {
        Observable<PageGridViewModel> observableCompose = getBootstrapObservable().concatMap(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda5
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m594getInitPageData$lambda0(this.f$0, (Void) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).concatMap(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda6
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m595getInitPageData$lambda1(this.f$0, getCached, (PageModelUseCase.BrushVersion) obj);
            }
        }).observeOn(Schedulers.io()).compose(new Observable.Transformer() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda7
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m596getInitPageData$lambda3(this.f$0, (Observable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCompose, "getBootstrapObservable()…      }\n                }");
        return observableCompose;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitPageData$lambda-0, reason: not valid java name */
    public static final Observable m594getInitPageData$lambda0(PageModelUseCase this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.getBrushVersion(this$0.uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitPageData$lambda-1, reason: not valid java name */
    public static final Observable m595getInitPageData$lambda1(PageModelUseCase this$0, boolean z, BrushVersion brushVersion) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PageModelDataRepository pageModelDataRepository = this$0.brushRepositoryMap.get(brushVersion);
        if (pageModelDataRepository == null) {
            return null;
        }
        return pageModelDataRepository.fetchInitPageGridData(this$0.uri, this$0.nextPageToken, this$0.isArtistUpsell, z, this$0.refinementLists);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitPageData$lambda-3, reason: not valid java name */
    public static final Observable m596getInitPageData$lambda3(final PageModelUseCase this$0, Observable observable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return (!this$0.withRecentlyPlayed || Feature.home_recently_played_from_service.isEnabled()) ? observable : observable.zipWith(RxRecentlyPlayedRepository.INSTANCE.fetchRecentlyPlayedItems(this$0.application), new Func2() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda11
            @Override // rx.functions.Func2
            public final Object call(Object obj, Object obj2) {
                return PageModelUseCase.m597getInitPageData$lambda3$lambda2(this.f$0, (PageGridViewModel) obj, (List) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitPageData$lambda-3$lambda-2, reason: not valid java name */
    public static final PageGridViewModel m597getInitPageData$lambda3$lambda2(PageModelUseCase this$0, PageGridViewModel pageModel, List recentItems) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(recentItems, "recentItems");
        Intrinsics.checkNotNullExpressionValue(pageModel, "pageModel");
        return this$0.replaceRecentModel(recentItems, pageModel);
    }

    private final Observable<BrushVersion> getBrushVersion(String uri) {
        Observable<BrushVersion> observableFromCallable;
        String str;
        if (isBrushV3Uri(uri)) {
            observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda8
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return PageModelUseCase.BrushVersion.BRUSH_V3;
                }
            });
            str = "fromCallable { BrushVersion.BRUSH_V3 }";
        } else {
            observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda9
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return PageModelUseCase.BrushVersion.BRUSH_V2;
                }
            });
            str = "fromCallable { BrushVersion.BRUSH_V2 }";
        }
        Intrinsics.checkNotNullExpressionValue(observableFromCallable, str);
        return observableFromCallable;
    }

    public final Observable<PageGridViewModel> getFilteredPageDataBrushV3(final Map<String, ? extends List<String>> refinementLists) {
        Observable<PageGridViewModel> observableObserveOn = getBootstrapObservable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).concatMap(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda10
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m593getFilteredPageDataBrushV3$lambda6(this.f$0, refinementLists, (Void) obj);
            }
        }).observeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "getBootstrapObservable()…bserveOn(Schedulers.io())");
        return observableObserveOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getFilteredPageDataBrushV3$lambda-6, reason: not valid java name */
    public static final Observable m593getFilteredPageDataBrushV3$lambda6(PageModelUseCase this$0, Map map, Void r3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PageModelDataRepository pageModelDataRepository = this$0.brushRepositoryMap.get(BrushVersion.BRUSH_V3);
        if (pageModelDataRepository == null) {
            return null;
        }
        return pageModelDataRepository.fetchPageFromNetworkWithRefinements(this$0.uri, this$0.nextPageToken, map);
    }

    public final Observable<PageGridViewModel> getFilteredPageData(final String browseId) {
        Intrinsics.checkNotNullParameter(browseId, "browseId");
        Observable<PageGridViewModel> observableObserveOn = getBootstrapObservable().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).concatMap(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda14
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m592getFilteredPageData$lambda7(this.f$0, browseId, (Void) obj);
            }
        }).observeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "getBootstrapObservable()…bserveOn(Schedulers.io())");
        return observableObserveOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getFilteredPageData$lambda-7, reason: not valid java name */
    public static final Observable m592getFilteredPageData$lambda7(PageModelUseCase this$0, String browseId, Void r3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(browseId, "$browseId");
        PageModelDataRepository pageModelDataRepository = this$0.brushRepositoryMap.get(BrushVersion.BRUSH_V2);
        if (pageModelDataRepository == null) {
            return null;
        }
        return pageModelDataRepository.fetchPageFromNetworkWithBrowseId(this$0.uri, this$0.nextPageToken, browseId);
    }

    public final Observable<PageGridViewModel> getMorePageData(final int pageIndex, final String nextPageToken, final boolean isArtistUpsell, final PageGridViewModel originalPage, final Map<String, ? extends List<String>> refinementLists) {
        Intrinsics.checkNotNullParameter(nextPageToken, "nextPageToken");
        Intrinsics.checkNotNullParameter(originalPage, "originalPage");
        Observable<PageGridViewModel> map = getBootstrapObservable().concatMap(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m599getMorePageData$lambda8(this.f$0, (Void) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).concatMap(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda1
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m600getMorePageData$lambda9(this.f$0, pageIndex, nextPageToken, isArtistUpsell, refinementLists, (PageModelUseCase.BrushVersion) obj);
            }
        }).map(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda2
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m598getMorePageData$lambda10(originalPage, pageIndex, (PageGridViewModel) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "getBootstrapObservable()…eIndex)\n                }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getMorePageData$lambda-8, reason: not valid java name */
    public static final Observable m599getMorePageData$lambda8(PageModelUseCase this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.getBrushVersion(this$0.uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getMorePageData$lambda-9, reason: not valid java name */
    public static final Observable m600getMorePageData$lambda9(PageModelUseCase this$0, int i, String nextPageToken, boolean z, Map map, BrushVersion brushVersion) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(nextPageToken, "$nextPageToken");
        PageModelDataRepository pageModelDataRepository = this$0.brushRepositoryMap.get(brushVersion);
        if (pageModelDataRepository == null) {
            return null;
        }
        return pageModelDataRepository.fetchMorePageGridData(i, nextPageToken, z, this$0.uri, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getMorePageData$lambda-10, reason: not valid java name */
    public static final PageGridViewModel m598getMorePageData$lambda10(PageGridViewModel originalPage, int i, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(originalPage, "$originalPage");
        List<BaseViewModel> models = originalPage.getModels();
        List<BaseViewModel> models2 = pageGridViewModel == null ? null : pageGridViewModel.getModels();
        if (models2 == null) {
            models2 = Collections.emptyList();
            Intrinsics.checkNotNullExpressionValue(models2, "emptyList()");
        }
        return originalPage.copy((1839 & 1) != 0 ? originalPage.viewId : null, (1839 & 2) != 0 ? originalPage.nestedModelViewId : null, (1839 & 4) != 0 ? originalPage.title : null, (1839 & 8) != 0 ? originalPage.subTitle : null, (1839 & 16) != 0 ? originalPage.getModels() : CollectionsKt___CollectionsKt.plus((Collection) models, (Iterable) models2), (1839 & 32) != 0 ? originalPage.getTileType() : 0, (1839 & 64) != 0 ? originalPage.pageIndex : i, (1839 & 128) != 0 ? originalPage.nextPageToken : pageGridViewModel == null ? null : pageGridViewModel.getNextPageToken(), (1839 & 256) != 0 ? originalPage.filters : null, (1839 & 512) != 0 ? originalPage.context : null, (1839 & 1024) != 0 ? originalPage.header : null);
    }

    public final Observable<Boolean> isPageDataExpired() {
        Observable<Boolean> observableConcatMap = getBootstrapObservable().concatMap(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda3
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m603isPageDataExpired$lambda11(this.f$0, (Void) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).concatMap(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda4
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m604isPageDataExpired$lambda12(this.f$0, (PageModelUseCase.BrushVersion) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableConcatMap, "getBootstrapObservable()…ageGridDataExpired(uri) }");
        return observableConcatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isPageDataExpired$lambda-11, reason: not valid java name */
    public static final Observable m603isPageDataExpired$lambda11(PageModelUseCase this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.getBrushVersion(this$0.uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isPageDataExpired$lambda-12, reason: not valid java name */
    public static final Observable m604isPageDataExpired$lambda12(PageModelUseCase this$0, BrushVersion brushVersion) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PageModelDataRepository pageModelDataRepository = this$0.brushRepositoryMap.get(brushVersion);
        if (pageModelDataRepository == null) {
            return null;
        }
        return pageModelDataRepository.isPageGridDataExpired(this$0.uri);
    }

    public final Observable<Boolean> invalidateCachedPageData() {
        Observable<Boolean> observableConcatMap = getBootstrapObservable().concatMap(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda12
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m601invalidateCachedPageData$lambda13(this.f$0, (Void) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).concatMap(new Func1() { // from class: com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase$$ExternalSyntheticLambda13
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PageModelUseCase.m602invalidateCachedPageData$lambda14(this.f$0, (PageModelUseCase.BrushVersion) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableConcatMap, "getBootstrapObservable()…CachedPageGridData(uri) }");
        return observableConcatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invalidateCachedPageData$lambda-13, reason: not valid java name */
    public static final Observable m601invalidateCachedPageData$lambda13(PageModelUseCase this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.getBrushVersion(this$0.uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invalidateCachedPageData$lambda-14, reason: not valid java name */
    public static final Observable m602invalidateCachedPageData$lambda14(PageModelUseCase this$0, BrushVersion brushVersion) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PageModelDataRepository pageModelDataRepository = this$0.brushRepositoryMap.get(brushVersion);
        if (pageModelDataRepository == null) {
            return null;
        }
        return pageModelDataRepository.invalidateCachedPageGridData(this$0.uri);
    }

    protected final Observable<Void> getBootstrapObservable() {
        if (this.withBootStrap) {
            Observable<Void> observableTake = BootstrapSingletonTask.get().registerUserObserver().take(1);
            Intrinsics.checkNotNullExpressionValue(observableTake, "{\n            BootstrapS…       .take(1)\n        }");
            return observableTake;
        }
        Observable<Void> observableJust = Observable.just(null);
        Intrinsics.checkNotNullExpressionValue(observableJust, "{\n            Observable.just(null)\n        }");
        return observableJust;
    }

    private final PageGridViewModel replaceRecentModel(List<? extends RecentlyPlayedItem> recentItems, PageGridViewModel pageModel) {
        if (recentItems.isEmpty()) {
            return pageModel;
        }
        List<BaseViewModel> models = pageModel.getModels();
        Intrinsics.checkNotNull(models);
        for (BaseViewModel baseViewModel : models) {
            if ((baseViewModel instanceof BaseViewContainerModel) && BrushRecentlyPlayedConverter.INSTANCE.containsRecentlyPlayedTag(baseViewModel.getTags())) {
                BrushRecentlyPlayedConverter brushRecentlyPlayedConverter = new BrushRecentlyPlayedConverter(this.application, (BaseViewContainerModel) baseViewModel);
                brushRecentlyPlayedConverter.convert2(recentItems, (List<? extends BaseViewModel>) brushRecentlyPlayedConverter.getChildrenModels(recentItems));
            }
        }
        return pageModel;
    }

    public final void replacePodcastJumpBackInEpisodes(List<PodcastEpisode> podcastEpisodes, PageGridViewModel pageModel) {
        Intrinsics.checkNotNullParameter(pageModel, "pageModel");
        if (podcastEpisodes == null) {
            return;
        }
        Iterator<T> it = BrushPodcastEpisodeConverter.INSTANCE.containsPodcastJumpBackInShoveler(pageModel).iterator();
        while (it.hasNext()) {
            BrushPodcastEpisodeConverter brushPodcastEpisodeConverter = new BrushPodcastEpisodeConverter((BaseViewContainerModel) ((BaseViewModel) it.next()));
            brushPodcastEpisodeConverter.convert2(podcastEpisodes, (List<? extends BaseViewModel>) brushPodcastEpisodeConverter.getChildrenModels2(podcastEpisodes));
        }
    }

    public final void replacePodcastFollowedShows(List<PodcastShow> podcastShows, PageGridViewModel pageModel) {
        Intrinsics.checkNotNullParameter(pageModel, "pageModel");
        if (podcastShows == null) {
            return;
        }
        Iterator<T> it = BrushPodcastShowConverter.INSTANCE.containsPodcastFollowedShowsShoveler(pageModel).iterator();
        while (it.hasNext()) {
            BrushPodcastShowConverter brushPodcastShowConverter = new BrushPodcastShowConverter((BaseViewContainerModel) ((BaseViewModel) it.next()));
            brushPodcastShowConverter.convert2(podcastShows, (List<? extends BaseViewModel>) brushPodcastShowConverter.getChildrenModels2(podcastShows));
        }
    }

    public final void replacePodcastSavedEpisodes(List<PodcastEpisode> podcastEpisodes, PageGridViewModel pageModel) {
        Intrinsics.checkNotNullParameter(pageModel, "pageModel");
        if (podcastEpisodes == null) {
            return;
        }
        Iterator<T> it = BrushPodcastEpisodeConverter.INSTANCE.containsPodcastSavedEpisodesShoveler(pageModel).iterator();
        while (it.hasNext()) {
            BrushPodcastEpisodeConverter brushPodcastEpisodeConverter = new BrushPodcastEpisodeConverter((BaseViewContainerModel) ((BaseViewModel) it.next()));
            brushPodcastEpisodeConverter.convert2(podcastEpisodes, (List<? extends BaseViewModel>) brushPodcastEpisodeConverter.getChildrenModels2(podcastEpisodes));
        }
    }

    private final boolean isBrushV3Uri(String uri) {
        List<String> list = BRUSH_V3_URIS;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (StringsKt__StringsJVMKt.startsWith$default(uri, (String) it.next(), false, 2, null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: compiled from: PageModelUseCase.kt */
    @Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b$\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u001a\u001a\u00020\u0003¢\u0006\u0004\b7\u00108J\u0006\u0010\u0002\u001a\u00020\u0000J\u0010\u0010\u0005\u001a\u00020\u00002\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\t\u001a\u00020\u0000J\u0006\u0010\n\u001a\u00020\u0000J\"\u0010\u000e\u001a\u00020\u00002\u001a\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\f\u0018\u00010\u000bJ\u0014\u0010\u0012\u001a\u00020\u00002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u0006\u0010\u0014\u001a\u00020\u0013R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\"\u0010\u001e\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010\t\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u001f\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\"\u0010\n\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u001f\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R$\u0010(\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010\u001b\u001a\u0004\b)\u0010\u001d\"\u0004\b*\u0010+R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b\u0007\u0010!\"\u0004\b,\u0010#R6\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\f\u0018\u00010\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R*\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106¨\u00069"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/usecase/PageModelUseCase$Builder;", "", "withBootStrap", "", "token", "withNextPageToken", "", "isArtistUpsell", "withArtistUpsell", "withRecentlyPlayed", "withRecentSearches", "", "", "refinementLists", "withRefinementsLists", "", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "latencyEvents", "withBrushLatencyEvents", "Lcom/amazon/mp3/catalog/fragment/usecase/PageModelUseCase;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Landroid/app/Application;", "application", "Landroid/app/Application;", "getApplication", "()Landroid/app/Application;", "uri", "Ljava/lang/String;", "getUri", "()Ljava/lang/String;", "withBootstrap", "Z", "getWithBootstrap", "()Z", "setWithBootstrap", "(Z)V", "getWithRecentlyPlayed", "setWithRecentlyPlayed", "getWithRecentSearches", "setWithRecentSearches", "nextPageToken", "getNextPageToken", "setNextPageToken", "(Ljava/lang/String;)V", "setArtistUpsell", "Ljava/util/Map;", "getRefinementLists", "()Ljava/util/Map;", "setRefinementLists", "(Ljava/util/Map;)V", "Ljava/util/Set;", "getLatencyEvents", "()Ljava/util/Set;", "setLatencyEvents", "(Ljava/util/Set;)V", "<init>", "(Landroid/app/Application;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Builder {
        private final Application application;
        private boolean isArtistUpsell;
        private Set<? extends LatencyTrackingLeg> latencyEvents;
        private String nextPageToken;
        private Map<String, ? extends List<String>> refinementLists;
        private final String uri;
        private boolean withBootstrap;
        private boolean withRecentSearches;
        private boolean withRecentlyPlayed;

        public Builder(Application application, String uri) {
            Intrinsics.checkNotNullParameter(application, "application");
            Intrinsics.checkNotNullParameter(uri, "uri");
            this.application = application;
            this.uri = uri;
        }

        public final Application getApplication() {
            return this.application;
        }

        public final String getUri() {
            return this.uri;
        }

        public final boolean getWithBootstrap() {
            return this.withBootstrap;
        }

        public final boolean getWithRecentlyPlayed() {
            return this.withRecentlyPlayed;
        }

        public final boolean getWithRecentSearches() {
            return this.withRecentSearches;
        }

        public final String getNextPageToken() {
            return this.nextPageToken;
        }

        /* JADX INFO: renamed from: isArtistUpsell, reason: from getter */
        public final boolean getIsArtistUpsell() {
            return this.isArtistUpsell;
        }

        public final Map<String, List<String>> getRefinementLists() {
            return this.refinementLists;
        }

        public final Set<LatencyTrackingLeg> getLatencyEvents() {
            return this.latencyEvents;
        }

        public final Builder withBootStrap() {
            this.withBootstrap = true;
            return this;
        }

        public final Builder withNextPageToken(String token) {
            this.nextPageToken = token;
            return this;
        }

        public final Builder withArtistUpsell(boolean isArtistUpsell) {
            this.isArtistUpsell = isArtistUpsell;
            return this;
        }

        public final Builder withRecentlyPlayed() {
            this.withRecentlyPlayed = true;
            return this;
        }

        public final Builder withRecentSearches() {
            this.withRecentSearches = true;
            return this;
        }

        public final Builder withRefinementsLists(Map<String, ? extends List<String>> refinementLists) {
            this.refinementLists = refinementLists;
            return this;
        }

        public final Builder withBrushLatencyEvents(Set<? extends LatencyTrackingLeg> latencyEvents) {
            Intrinsics.checkNotNullParameter(latencyEvents, "latencyEvents");
            this.latencyEvents = latencyEvents;
            return this;
        }

        public final PageModelUseCase build() {
            return new PageModelUseCase(this);
        }
    }
}
