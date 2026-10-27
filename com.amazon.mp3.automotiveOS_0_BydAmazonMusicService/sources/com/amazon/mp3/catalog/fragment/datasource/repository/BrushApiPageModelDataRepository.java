package com.amazon.mp3.catalog.fragment.datasource.repository;

import android.app.Application;
import com.amazon.mp3.brush.RxUiPage;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.browse.BrowsePageFactory;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.browse.page.PageRequest;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.views.library.models.PageGridViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;

/* JADX INFO: compiled from: BrushApiPageModelDataRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f¢\u0006\u0004\b#\u0010$J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J0\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002JL\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0018\u00010\u000eH\u0016JJ\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0018\u00010\u000eH\u0016J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J*\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0016J<\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0018\u00010\u000eH\u0016R\u0017\u0010\u001b\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/repository/BrushApiPageModelDataRepository;", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageModelDataRepository;", "Lcom/amazon/mp3/brush/RxUiPage;", "getRxUiPage", "", "uri", "Lrx/Observable;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "fetchFromCache", "nextPageToken", "", "isArtistUpsell", "getCached", "fetchPageFromNetwork", "", "", "refinementLists", "fetchInitPageGridData", "", "pageIndex", "fetchMorePageGridData", "isPageGridDataExpired", "invalidateCachedPageGridData", "browseId", "fetchPageFromNetworkWithBrowseId", "fetchPageFromNetworkWithRefinements", "Landroid/app/Application;", "application", "Landroid/app/Application;", "getApplication", "()Landroid/app/Application;", "", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "withLatencyEvents", "Ljava/util/Set;", "<init>", "(Landroid/app/Application;Ljava/util/Set;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushApiPageModelDataRepository implements PageModelDataRepository {
    private final Application application;
    private final Set<LatencyTrackingLeg> withLatencyEvents;

    /* JADX WARN: Multi-variable type inference failed */
    public BrushApiPageModelDataRepository(Application application, Set<? extends LatencyTrackingLeg> withLatencyEvents) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(withLatencyEvents, "withLatencyEvents");
        this.application = application;
        this.withLatencyEvents = withLatencyEvents;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<PageGridViewModel> fetchInitPageGridData(String uri, String nextPageToken, boolean isArtistUpsell, boolean getCached, Map<String, ? extends List<String>> refinementLists) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Observable<PageGridViewModel> observableSwitchIfEmpty = fetchFromCache(uri).switchIfEmpty(fetchPageFromNetwork(uri, nextPageToken, isArtistUpsell, getCached));
        Intrinsics.checkNotNullExpressionValue(observableSwitchIfEmpty, "fetchFromCache(uri)\n    …ArtistUpsell, getCached))");
        return observableSwitchIfEmpty;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<PageGridViewModel> fetchMorePageGridData(int pageIndex, String nextPageToken, boolean isArtistUpsell, String uri, Map<String, ? extends List<String>> refinementLists) {
        Intrinsics.checkNotNullParameter(nextPageToken, "nextPageToken");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Observable<PageGridViewModel> observableSwitchIfEmpty = fetchFromCache(uri).switchIfEmpty(fetchPageFromNetwork(uri, nextPageToken, isArtistUpsell, false));
        Intrinsics.checkNotNullExpressionValue(observableSwitchIfEmpty, "fetchFromCache(uri)\n    …, isArtistUpsell, false))");
        return observableSwitchIfEmpty;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<Boolean> isPageGridDataExpired(String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        final RxUiPage rxUiPage = getRxUiPage();
        final PageRequest pageRequestCreatePageRequest = BrowseFactory.createPageRequest(this.application, uri, null, false);
        final String profileId = BrowsePageFactory.INSTANCE.getProfileId(this.application);
        Observable<Boolean> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.catalog.fragment.datasource.repository.BrushApiPageModelDataRepository$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BrushApiPageModelDataRepository.m572isPageGridDataExpired$lambda0(rxUiPage, pageRequestCreatePageRequest, profileId);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable {rxUiPage.i…ired(request, profileId)}");
        return observableFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isPageGridDataExpired$lambda-0, reason: not valid java name */
    public static final Boolean m572isPageGridDataExpired$lambda0(RxUiPage rxUiPage, PageRequest request, String str) {
        Intrinsics.checkNotNullParameter(rxUiPage, "$rxUiPage");
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return Boolean.valueOf(rxUiPage.isPageExpired(request, str));
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<Boolean> invalidateCachedPageGridData(String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        final RxUiPage rxUiPage = getRxUiPage();
        final PageRequest pageRequestCreatePageRequest = BrowseFactory.createPageRequest(this.application, uri, null, false);
        final String profileId = BrowsePageFactory.INSTANCE.getProfileId(this.application);
        Observable<Boolean> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.catalog.fragment.datasource.repository.BrushApiPageModelDataRepository$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BrushApiPageModelDataRepository.m571invalidateCachedPageGridData$lambda1(rxUiPage, pageRequestCreatePageRequest, profileId);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable {rxUiPage.i…ache(request, profileId)}");
        return observableFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invalidateCachedPageGridData$lambda-1, reason: not valid java name */
    public static final Boolean m571invalidateCachedPageGridData$lambda1(RxUiPage rxUiPage, PageRequest request, String str) {
        Intrinsics.checkNotNullParameter(rxUiPage, "$rxUiPage");
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return Boolean.valueOf(rxUiPage.invalidateCache(request, str));
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<PageGridViewModel> fetchPageFromNetworkWithBrowseId(String uri, String nextPageToken, String browseId) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        RxUiPage rxUiPage = getRxUiPage();
        PageRequest request = BrowseFactory.createPageRequestWithBrowseId(this.application, uri, nextPageToken, browseId);
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return RxUiPage.get$default(rxUiPage, request, this.application, false, 4, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<PageGridViewModel> fetchPageFromNetworkWithRefinements(String uri, String nextPageToken, Map<String, ? extends List<String>> refinementLists) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        RxUiPage rxUiPage = getRxUiPage();
        PageRequest request = BrowseFactory.createPageRequestWithBrowseId(this.application, uri, nextPageToken, null);
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return RxUiPage.get$default(rxUiPage, request, this.application, false, 4, null);
    }

    private final RxUiPage getRxUiPage() {
        RxUiPage rxUiPageCreateRxUiPage = BrowseFactory.createRxUiPage(this.application, this.withLatencyEvents.contains(LatencyTrackingLeg.RESPONSE), this.withLatencyEvents.contains(LatencyTrackingLeg.PROCESSING));
        Intrinsics.checkNotNullExpressionValue(rxUiPageCreateRxUiPage, "createRxUiPage(\n        …yTrackingLeg.PROCESSING))");
        return rxUiPageCreateRxUiPage;
    }

    private final Observable<PageGridViewModel> fetchFromCache(String uri) {
        Observable<PageGridViewModel> observableFrom = Observable.from(new ArrayList());
        Intrinsics.checkNotNullExpressionValue(observableFrom, "from(list)");
        return observableFrom;
    }

    private final Observable<PageGridViewModel> fetchPageFromNetwork(String uri, String nextPageToken, boolean isArtistUpsell, boolean getCached) {
        Set<LatencyTrackingLeg> set = this.withLatencyEvents;
        LatencyTrackingLeg latencyTrackingLeg = LatencyTrackingLeg.REQUEST;
        if (set.contains(latencyTrackingLeg)) {
            BrushLatencyReportingUtil.INSTANCE.trackLatency(uri, latencyTrackingLeg);
        }
        RxUiPage rxUiPage = getRxUiPage();
        PageRequest request = BrowseFactory.createPageRequest(this.application, uri, nextPageToken, isArtistUpsell);
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return rxUiPage.get(request, this.application, getCached);
    }
}
