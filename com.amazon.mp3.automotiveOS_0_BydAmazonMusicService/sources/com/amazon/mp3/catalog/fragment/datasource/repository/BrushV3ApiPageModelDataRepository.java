package com.amazon.mp3.catalog.fragment.datasource.repository;

import android.content.Context;
import com.amazon.mp3.brush.RxUiV3Page;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.page.api.model.AutomotiveServicePageRequest;
import com.amazon.music.page.api.model.PageRequest;
import com.amazon.music.views.library.models.PageGridViewModel;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;

/* JADX INFO: compiled from: BrushV3ApiPageModelDataRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b&\u0010'J>\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0006\u0018\u00010\u0005H\u0002JL\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0006\u0018\u00010\u0005H\u0016JJ\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0006\u0018\u00010\u0005H\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J*\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0016J<\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0006\u0018\u00010\u0005H\u0016JX\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00022\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0002J$\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0002J\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002R\u0017\u0010\"\u001a\u00020!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%¨\u0006("}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/repository/BrushV3ApiPageModelDataRepository;", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageModelDataRepository;", "", "uri", "nextPageToken", "", "", "refinementLists", "Lrx/Observable;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "fetchPageFromNetwork", "", "isArtistUpsell", "getCached", "fetchInitPageGridData", "", "pageIndex", "fetchMorePageGridData", "isPageGridDataExpired", "invalidateCachedPageGridData", "browseId", "fetchPageFromNetworkWithBrowseId", "fetchPageFromNetworkWithRefinements", "platform", "presetIds", "downloadedIds", "recentlyPlayedIds", "recentlyModifiedIds", "widgetId", "fetchCarModePage", "podcastShowId", "fetchCarModePodcastEpisodeListPage", "invalidateCarModePage", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3ApiPageModelDataRepository implements PageModelDataRepository {
    private final Context context;

    public BrushV3ApiPageModelDataRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<PageGridViewModel> fetchInitPageGridData(String uri, String nextPageToken, boolean isArtistUpsell, boolean getCached, Map<String, ? extends List<String>> refinementLists) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return fetchPageFromNetwork(uri, nextPageToken, refinementLists);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<PageGridViewModel> fetchMorePageGridData(int pageIndex, String nextPageToken, boolean isArtistUpsell, String uri, Map<String, ? extends List<String>> refinementLists) {
        Intrinsics.checkNotNullParameter(nextPageToken, "nextPageToken");
        Intrinsics.checkNotNullParameter(uri, "uri");
        return fetchPageFromNetwork(uri, nextPageToken, refinementLists);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<Boolean> isPageGridDataExpired(String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        final RxUiV3Page rxUiV3PageCreateRxUiV3Page = BrowseFactory.createRxUiV3Page(this.context);
        final PageRequest pageRequestCreateBrushV3PageRequest = BrowseFactory.createBrushV3PageRequest(this.context, uri, null, Boolean.valueOf(PlatformUtil.isFireOS()), null);
        Observable<Boolean> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.catalog.fragment.datasource.repository.BrushV3ApiPageModelDataRepository$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BrushV3ApiPageModelDataRepository.m575isPageGridDataExpired$lambda0(rxUiV3PageCreateRxUiV3Page, pageRequestCreateBrushV3PageRequest);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable { rxUiV3Page.isPageExpired(request) }");
        return observableFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isPageGridDataExpired$lambda-0, reason: not valid java name */
    public static final Boolean m575isPageGridDataExpired$lambda0(RxUiV3Page rxUiV3Page, PageRequest request) {
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return Boolean.valueOf(rxUiV3Page.isPageExpired(request));
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<Boolean> invalidateCachedPageGridData(String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        final RxUiV3Page rxUiV3PageCreateRxUiV3Page = BrowseFactory.createRxUiV3Page(this.context);
        final PageRequest pageRequestCreateBrushV3PageRequest = BrowseFactory.createBrushV3PageRequest(this.context, uri, null, Boolean.valueOf(PlatformUtil.isFireOS()), null);
        Observable<Boolean> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.catalog.fragment.datasource.repository.BrushV3ApiPageModelDataRepository$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BrushV3ApiPageModelDataRepository.m573invalidateCachedPageGridData$lambda1(rxUiV3PageCreateRxUiV3Page, pageRequestCreateBrushV3PageRequest);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable { rxUiV3Pag…nvalidateCache(request) }");
        return observableFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invalidateCachedPageGridData$lambda-1, reason: not valid java name */
    public static final Boolean m573invalidateCachedPageGridData$lambda1(RxUiV3Page rxUiV3Page, PageRequest request) {
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return Boolean.valueOf(rxUiV3Page.invalidateCache(request));
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<PageGridViewModel> fetchPageFromNetworkWithBrowseId(String uri, String nextPageToken, String browseId) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return fetchPageFromNetwork$default(this, uri, nextPageToken, null, 4, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.datasource.repository.PageModelDataRepository
    public Observable<PageGridViewModel> fetchPageFromNetworkWithRefinements(String uri, String nextPageToken, Map<String, ? extends List<String>> refinementLists) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return fetchPageFromNetwork(uri, nextPageToken, refinementLists);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Observable fetchPageFromNetwork$default(BrushV3ApiPageModelDataRepository brushV3ApiPageModelDataRepository, String str, String str2, Map map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = null;
        }
        return brushV3ApiPageModelDataRepository.fetchPageFromNetwork(str, str2, map);
    }

    private final Observable<PageGridViewModel> fetchPageFromNetwork(String uri, String nextPageToken, Map<String, ? extends List<String>> refinementLists) {
        BrushLatencyReportingUtil.INSTANCE.trackLatency(uri, LatencyTrackingLeg.REQUEST);
        RxUiV3Page rxUiV3PageCreateRxUiV3Page = BrowseFactory.createRxUiV3Page(this.context);
        PageRequest request = BrowseFactory.createBrushV3PageRequest(this.context, uri, nextPageToken, Boolean.valueOf(PlatformUtil.isFireOS()), refinementLists);
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return rxUiV3PageCreateRxUiV3Page.get(request);
    }

    public final Observable<PageGridViewModel> fetchCarModePage(String uri, String platform, String presetIds, String downloadedIds, String recentlyPlayedIds, String recentlyModifiedIds, String widgetId) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(platform, "platform");
        RxUiV3Page rxUiV3PageCreateRxUiV3Page = BrowseFactory.createRxUiV3Page(this.context);
        AutomotiveServicePageRequest request = BrowseFactory.createAutomotiveBrushV3PageRequest(this.context, uri, platform, presetIds, downloadedIds, recentlyPlayedIds, recentlyModifiedIds, widgetId, null);
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return rxUiV3PageCreateRxUiV3Page.get(request);
    }

    public final Observable<PageGridViewModel> fetchCarModePodcastEpisodeListPage(String uri, String platform, String podcastShowId) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(podcastShowId, "podcastShowId");
        BrushLatencyReportingUtil.INSTANCE.trackLatency(uri, LatencyTrackingLeg.REQUEST);
        RxUiV3Page rxUiV3PageCreateRxUiV3Page = BrowseFactory.createRxUiV3Page(this.context);
        AutomotiveServicePageRequest request = BrowseFactory.createAutomotiveBrushV3PageRequest(this.context, uri, platform, null, null, null, null, null, podcastShowId);
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return rxUiV3PageCreateRxUiV3Page.get(request);
    }

    public final Observable<Boolean> invalidateCarModePage(String uri, String platform) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(platform, "platform");
        final RxUiV3Page rxUiV3PageCreateRxUiV3Page = BrowseFactory.createRxUiV3Page(this.context);
        final AutomotiveServicePageRequest automotiveServicePageRequestCreateAutomotiveBrushV3PageRequest = BrowseFactory.createAutomotiveBrushV3PageRequest(this.context, uri, platform, null, null, null, null, null, null);
        Observable<Boolean> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.catalog.fragment.datasource.repository.BrushV3ApiPageModelDataRepository$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BrushV3ApiPageModelDataRepository.m574invalidateCarModePage$lambda2(rxUiV3PageCreateRxUiV3Page, automotiveServicePageRequestCreateAutomotiveBrushV3PageRequest);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable { rxUiV3Pag…nvalidateCache(request) }");
        return observableFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invalidateCarModePage$lambda-2, reason: not valid java name */
    public static final Boolean m574invalidateCarModePage$lambda2(RxUiV3Page rxUiV3Page, AutomotiveServicePageRequest request) {
        Intrinsics.checkNotNullExpressionValue(request, "request");
        return Boolean.valueOf(rxUiV3Page.invalidateCache(request));
    }
}
