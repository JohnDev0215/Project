package com.amazon.music.find.api;

import com.amazon.music.find.cache.Cache;
import com.amazon.music.find.cache.CacheManager;
import com.amazon.music.find.data.database.LibrarySearchDao;
import com.amazon.music.find.model.ExecuteQueryRequest;
import com.amazon.music.find.providers.SearchEntityFactory;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.search.SearchResult;
import com.amazon.music.search.SearchService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CachedRemoteSearchApi.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000fH\u0014J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/find/api/CachedRemoteSearchApi;", "Lcom/amazon/music/find/api/RemoteSearchApi;", "searchConfiguration", "Lcom/amazon/music/search/SearchService$Configuration;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "searchEntityFactory", "Lcom/amazon/music/find/providers/SearchEntityFactory;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "librarySearchDao", "Lcom/amazon/music/find/data/database/LibrarySearchDao;", "(Lcom/amazon/music/search/SearchService$Configuration;Lcom/amazon/music/find/providers/SearchFeaturesProvider;Lcom/amazon/music/find/providers/SearchEntityFactory;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/data/database/LibrarySearchDao;)V", "cache", "Lcom/amazon/music/find/cache/Cache;", "Lcom/amazon/music/find/model/ExecuteQueryRequest;", "Lcom/amazon/music/search/SearchResult;", "executeSearch", "queryRequest", "reportCacheHitMetric", "", "cacheHit", "", "searchTerm", "", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CachedRemoteSearchApi extends RemoteSearchApi {
    private static final String CACHE_NAME;
    private static final long CACHE_SIZE = 5;
    private static final long CACHE_WRITE_TTL = 300000;
    private static final String FLEX_EVENT_CATALOG_SEARCH_RESULTS_CACHE_HIT = "findSearchResultsCacheHit";
    private static final String FLEX_EVENT_CATALOG_SEARCH_RESULTS_CACHE_MISSED = "findSearchResultsCacheMissed";
    private static final String TAG = "SPL";
    private static final Logger logger;
    private final Cache<ExecuteQueryRequest, SearchResult> cache;
    private final LibrarySearchDao librarySearchDao;
    private final SearchService.Configuration searchConfiguration;
    private final SearchEntityFactory searchEntityFactory;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final UIConfigurationService uiConfigurationService;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CachedRemoteSearchApi(SearchService.Configuration searchConfiguration, SearchFeaturesProvider searchFeaturesProvider, SearchEntityFactory searchEntityFactory, UIConfigurationService uiConfigurationService, LibrarySearchDao librarySearchDao) {
        super(searchConfiguration, searchFeaturesProvider, searchEntityFactory, uiConfigurationService, librarySearchDao);
        Intrinsics.checkNotNullParameter(searchConfiguration, "searchConfiguration");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(searchEntityFactory, "searchEntityFactory");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(librarySearchDao, "librarySearchDao");
        this.searchConfiguration = searchConfiguration;
        this.searchFeaturesProvider = searchFeaturesProvider;
        this.searchEntityFactory = searchEntityFactory;
        this.uiConfigurationService = uiConfigurationService;
        this.librarySearchDao = librarySearchDao;
        CacheManager cacheManager = CacheManager.INSTANCE;
        String CACHE_NAME2 = CACHE_NAME;
        Intrinsics.checkNotNullExpressionValue(CACHE_NAME2, "CACHE_NAME");
        this.cache = cacheManager.getCache(CACHE_NAME2);
    }

    static {
        Logger logger2 = LoggerFactory.getLogger(CachedRemoteSearchApi.class.getName());
        logger = logger2;
        String CACHE_NAME2 = CachedRemoteSearchApi.class.getName();
        CACHE_NAME = CACHE_NAME2;
        try {
            logger2.debug("SPL::registering " + ((Object) CACHE_NAME2) + " cache");
            CacheManager cacheManager = CacheManager.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(CACHE_NAME2, "CACHE_NAME");
            cacheManager.buildCache(CACHE_NAME2, new Function1<Cache.Builder<? super ExecuteQueryRequest, SearchResult>, Unit>() { // from class: com.amazon.music.find.api.CachedRemoteSearchApi.Companion.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Cache.Builder<? super ExecuteQueryRequest, SearchResult> builder) {
                    invoke2(builder);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Cache.Builder<? super ExecuteQueryRequest, SearchResult> builder) {
                    Intrinsics.checkNotNullParameter(builder, "builder");
                    builder.withMaxCacheSize(CachedRemoteSearchApi.CACHE_SIZE).withWriteExpireTimeMilliseconds(300000L);
                }
            });
        } catch (Exception e) {
            logger.error(Intrinsics.stringPlus("SPL::init. Unable to initialize cache manager. ", e.getMessage()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.music.find.api.RemoteSearchApi
    public SearchResult executeSearch(final ExecuteQueryRequest queryRequest) {
        Intrinsics.checkNotNullParameter(queryRequest, "queryRequest");
        return this.cache.get(queryRequest, new Function0<SearchResult>() { // from class: com.amazon.music.find.api.CachedRemoteSearchApi.executeSearch.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SearchResult invoke() {
                return CachedRemoteSearchApi.super.executeSearch(queryRequest);
            }
        }, new Function2<Boolean, SearchResult, Unit>() { // from class: com.amazon.music.find.api.CachedRemoteSearchApi.executeSearch.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(Boolean bool, SearchResult searchResult) {
                invoke(bool.booleanValue(), searchResult);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, SearchResult noName_1) {
                Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
                CachedRemoteSearchApi.this.reportCacheHitMetric(z, queryRequest.getSearchTerm());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reportCacheHitMetric(boolean cacheHit, String searchTerm) {
        MetricsHolder.getManager().handleEvent(FlexEvent.builder(cacheHit ? FLEX_EVENT_CATALOG_SEARCH_RESULTS_CACHE_HIT : FLEX_EVENT_CATALOG_SEARCH_RESULTS_CACHE_MISSED).withFlexStr1(searchTerm).build());
    }
}
