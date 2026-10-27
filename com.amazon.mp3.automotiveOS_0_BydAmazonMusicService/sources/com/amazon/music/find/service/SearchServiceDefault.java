package com.amazon.music.find.service;

import com.amazon.music.find.api.SearchApi;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.model.search.SearchLibrarySource;
import com.amazon.music.find.model.search.SearchRequest;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreRequest;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.find.utils.RxJavaUtils;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchServiceDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J&\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/find/service/SearchServiceDefault;", "Lcom/amazon/music/find/service/SearchService;", "remoteSearchApi", "Lcom/amazon/music/find/api/SearchApi;", "Lcom/amazon/music/find/model/search/GenericSearchResponse;", "librarySearchApi", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/api/SearchApi;Lcom/amazon/music/find/api/SearchApi;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "catalog", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/find/model/search/SearchResponse;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", SearchIntents.EXTRA_QUERY, "", "library", "librarySource", "Lcom/amazon/music/find/model/search/SearchLibrarySource;", "search", "searchRequest", "Lcom/amazon/music/find/model/search/SearchRequest;", PageUriUtils.DO_SEE_MORE_VALUE, "Lcom/amazon/music/find/model/search/SeeMoreResponse;", "seeMoreRequest", "Lcom/amazon/music/find/model/search/SeeMoreRequest;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchServiceDefault implements SearchService {
    private static final Logger logger = LoggerFactory.getLogger(SearchServiceDefault.class.getName());
    private final SearchApi<GenericSearchResponse> librarySearchApi;
    private final SearchApi<GenericSearchResponse> remoteSearchApi;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final UIConfigurationService uiConfigurationService;

    public SearchServiceDefault(SearchApi<GenericSearchResponse> remoteSearchApi, SearchApi<GenericSearchResponse> librarySearchApi, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(remoteSearchApi, "remoteSearchApi");
        Intrinsics.checkNotNullParameter(librarySearchApi, "librarySearchApi");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.remoteSearchApi = remoteSearchApi;
        this.librarySearchApi = librarySearchApi;
        this.uiConfigurationService = uiConfigurationService;
        this.searchFeaturesProvider = searchFeaturesProvider;
        RxJavaUtils.INSTANCE.configureRxJavaGlobalErrorHandler();
    }

    @Override // com.amazon.music.find.service.SearchService
    public Observable<SearchResponse> library(ExecutionContext executionContext, String query, SearchLibrarySource librarySource) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(librarySource, "librarySource");
        return search(executionContext, new SearchRequest(query, false, null, new SearchFilterSelections(UIConfigurationService.DefaultImpls.getFilterMetadata$default(this.uiConfigurationService, false, 1, null), CollectionsKt__CollectionsKt.mutableListOf(this.uiConfigurationService.getMyMusicFilterElement())), false, null, null, 118, null));
    }

    @Override // com.amazon.music.find.service.SearchService
    public Observable<SearchResponse> catalog(ExecutionContext executionContext, String query) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        return search(executionContext, new SearchRequest(query, true, null, null, false, null, null, 124, null));
    }

    @Override // com.amazon.music.find.service.SearchService
    public Observable<SearchResponse> search(ExecutionContext executionContext, SearchRequest searchRequest) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(searchRequest, "searchRequest");
        if (searchRequest.getQuery() != null) {
            if (searchRequest.getSearchFilterSelections().hasMyMusicFilter()) {
                Observable<SearchResponse> observableCast = this.librarySearchApi.query(executionContext, searchRequest.getQuery(), searchRequest.getEnableSpellCorrections(), searchRequest.getSpellCorrections(), searchRequest.isOffline(), searchRequest.getSearchFilterSelections(), searchRequest.getQueryMetadata(), searchRequest.getPageSize()).cast(SearchResponse.class);
                Intrinsics.checkNotNullExpressionValue(observableCast, "{\n                librar…class.java)\n            }");
                return observableCast;
            }
            Observable<SearchResponse> observableCast2 = this.remoteSearchApi.query(executionContext, searchRequest.getQuery(), searchRequest.getEnableSpellCorrections(), searchRequest.getSpellCorrections(), searchRequest.isOffline(), searchRequest.getSearchFilterSelections(), searchRequest.getQueryMetadata(), searchRequest.getPageSize()).cast(SearchResponse.class);
            Intrinsics.checkNotNullExpressionValue(observableCast2, "{\n                remote…class.java)\n            }");
            return observableCast2;
        }
        Observable<SearchResponse> observableJust = Observable.just(null);
        Intrinsics.checkNotNullExpressionValue(observableJust, "just(null)");
        return observableJust;
    }

    @Override // com.amazon.music.find.service.SearchService
    public Observable<SeeMoreResponse> seeMore(ExecutionContext executionContext, SeeMoreRequest seeMoreRequest) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(seeMoreRequest, "seeMoreRequest");
        if (seeMoreRequest.getSearchFilterSelections().hasMyMusicFilter()) {
            return this.librarySearchApi.seeMore(executionContext, seeMoreRequest.getQuery(), seeMoreRequest.getEnableSpellCorrections(), seeMoreRequest.getSpellCorrections(), seeMoreRequest.getEntityType(), seeMoreRequest.getPageToken(), seeMoreRequest.isOffline(), seeMoreRequest.getSearchFilterSelections(), seeMoreRequest.getQueryMetadata(), seeMoreRequest.getPageSize());
        }
        return this.remoteSearchApi.seeMore(executionContext, seeMoreRequest.getQuery(), seeMoreRequest.getEnableSpellCorrections(), seeMoreRequest.getSpellCorrections(), seeMoreRequest.getEntityType(), seeMoreRequest.getPageToken(), seeMoreRequest.isOffline(), seeMoreRequest.getSearchFilterSelections(), seeMoreRequest.getQueryMetadata(), seeMoreRequest.getPageSize());
    }
}
