package com.amazon.music.find.api.searchsuggestion;

import androidx.annotation.VisibleForTesting;
import com.amazon.music.find.metrics.UiEventMetadata;
import com.amazon.music.find.model.InstantEntitySpecParams;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.SearchSuggestionEntity;
import com.amazon.music.find.model.SearchSuggestionText;
import com.amazon.music.find.model.configuration.InstantSearchSuggestionConfig;
import com.amazon.music.find.model.configuration.SearchSuggestionConfiguration;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.service.SearchSuggestionServiceException;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.utils.ContentTierUtils;
import com.amazon.music.platform.find.common.ContentTier;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.find.common.ParentalControls;
import com.amazon.music.platform.find.common.ParentalControlsPerContentTier;
import com.amazon.music.platform.find.service.common.EntityProperties;
import com.amazon.music.platform.find.service.common.SearchEntity;
import com.amazon.music.platform.model.Art;
import com.amazon.music.platform.model.ArtURL;
import com.amazon.music.platform.model.CatalogArtist;
import com.amazon.music.platform.model.CatalogPodcastShow;
import com.amazon.music.platform.model.CatalogTrack;
import com.amazon.music.platform.model.Document;
import com.amazon.music.search.suggestion.SearchSuggestionException;
import com.amazon.music.search.suggestion.instantsearch.HttpInstantSearchSuggestionService;
import com.amazon.music.search.suggestion.instantsearch.InstantSearchQueryRequest;
import com.amazon.music.search.suggestion.instantsearch.InstantSearchQueryResult;
import com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestion;
import com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionService;
import com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration;
import com.amazon.tenzing.textsearch.v1_1.InstantDocumentSpecification;
import com.amazon.tenzing.textsearch.v1_1.InstantEntityResult;
import com.amazon.tenzing.textsearch.v1_1.InstantEntitySpecification;
import com.amazon.tenzing.textsearch.v1_1.InstantSearchResult;
import com.amazon.tenzing.textsearch.v1_1.InstantTextResult;
import com.amazon.tenzing.textsearch.v1_1.InstantTextSpecification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SyncInstantSearchSuggestionApi.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 &2\u00020\u0001:\u0001&B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001a\u0010 \u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u001a\u0010\"\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001a\u0010$\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020%2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/music/find/api/searchsuggestion/SyncInstantSearchSuggestionApi;", "Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionApi;", "instantSearchServiceConfiguration", "Lcom/amazon/music/search/suggestion/instantsearch/InstantSearchSuggestionServiceConfiguration;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/search/suggestion/instantsearch/InstantSearchSuggestionServiceConfiguration;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "instantSearchSuggestion", "Lcom/amazon/music/search/suggestion/instantsearch/InstantSearchSuggestion;", "instantSearchSuggestionService", "Lcom/amazon/music/search/suggestion/instantsearch/InstantSearchSuggestionService;", "uiConfiguration", "Lcom/amazon/music/find/model/configuration/SearchSuggestionConfiguration;", "getEntitySpec", "Lcom/amazon/tenzing/textsearch/v1_1/InstantEntitySpecification;", "getSearchSuggestions", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionResponse;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "request", "Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionRequest;", "getTextSpec", "Lcom/amazon/tenzing/textsearch/v1_1/InstantTextSpecification;", "mapToArtist", "Lcom/amazon/music/find/model/SearchSuggestionEntity;", "document", "Lcom/amazon/music/platform/model/CatalogArtist;", "blockRef", "", "mapToEntity", "Lcom/amazon/music/platform/model/Document;", "mapToPodcastShow", "Lcom/amazon/music/platform/model/CatalogPodcastShow;", "mapToTrack", "Lcom/amazon/music/platform/model/CatalogTrack;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SyncInstantSearchSuggestionApi implements SearchSuggestionApi {
    private static final Logger logger = LoggerFactory.getLogger(SyncInstantSearchSuggestionApi.class.getName());
    private final InstantSearchSuggestion instantSearchSuggestion;
    private final InstantSearchSuggestionService instantSearchSuggestionService;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchSuggestionConfiguration uiConfiguration;

    public SyncInstantSearchSuggestionApi(InstantSearchSuggestionServiceConfiguration instantSearchServiceConfiguration, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(instantSearchServiceConfiguration, "instantSearchServiceConfiguration");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.searchFeaturesProvider = searchFeaturesProvider;
        SearchSuggestionConfiguration searchSuggestionConfiguration = uiConfigurationService.getSearchSuggestionConfiguration();
        this.uiConfiguration = searchSuggestionConfiguration;
        HttpInstantSearchSuggestionService httpInstantSearchSuggestionService = new HttpInstantSearchSuggestionService(instantSearchServiceConfiguration);
        this.instantSearchSuggestionService = httpInstantSearchSuggestionService;
        this.instantSearchSuggestion = new InstantSearchSuggestion(httpInstantSearchSuggestionService, searchSuggestionConfiguration.getMaxSearchSuggestionCount());
    }

    @Override // com.amazon.music.find.api.searchsuggestion.SearchSuggestionApi
    public Observable<SearchSuggestionResponse> getSearchSuggestions(final ExecutionContext executionContext, final SearchSuggestionRequest request) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(request, "request");
        Observable<SearchSuggestionResponse> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.music.find.api.searchsuggestion.SyncInstantSearchSuggestionApi$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                SyncInstantSearchSuggestionApi.m2029getSearchSuggestions$lambda2(executionContext, request, this, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSearchSuggestions$lambda-2, reason: not valid java name */
    public static final void m2029getSearchSuggestions$lambda2(ExecutionContext executionContext, SearchSuggestionRequest request, SyncInstantSearchSuggestionApi this$0, ObservableEmitter observableEmitter) {
        SearchSuggestion searchSuggestionText;
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            executionContext.markApiRequestStarted();
            InstantSearchQueryRequest.Builder builderWithQuery = InstantSearchQueryRequest.builder().withQuery(request.getQuery());
            UiEventMetadata uiEventMetadata = executionContext.getUiEventMetadata();
            InstantSearchQueryRequest.Builder builderWithActivityId = builderWithQuery.withActivityId(uiEventMetadata == null ? null : uiEventMetadata.getActivityId());
            UiEventMetadata uiEventMetadata2 = executionContext.getUiEventMetadata();
            InstantSearchQueryRequest.Builder builderWithContentTier = builderWithActivityId.withQueryId(uiEventMetadata2 == null ? null : uiEventMetadata2.getResponseId()).withTextSpec(this$0.getTextSpec()).withContentTier(this$0.searchFeaturesProvider.getSearchContentTier().toString());
            InstantEntitySpecification entitySpec = this$0.getEntitySpec();
            if (entitySpec != null) {
                builderWithContentTier.withEntitySpec(entitySpec);
            }
            InstantSearchQueryResult instantSearchQueryResultQuery = this$0.instantSearchSuggestion.query(builderWithContentTier.build());
            List<InstantSearchResult> results = instantSearchQueryResultQuery.getResults();
            Intrinsics.checkNotNullExpressionValue(results, "result.results");
            ArrayList arrayList = new ArrayList();
            for (InstantSearchResult instantSearchResult : results) {
                if (instantSearchResult instanceof InstantEntityResult) {
                    Document document = ((InstantEntityResult) instantSearchResult).getDocument();
                    Intrinsics.checkNotNullExpressionValue(document, "it.document");
                    String blockRef = ((InstantEntityResult) instantSearchResult).getBlockRef();
                    Intrinsics.checkNotNullExpressionValue(blockRef, "it.blockRef");
                    searchSuggestionText = this$0.mapToEntity(document, blockRef);
                } else if (instantSearchResult instanceof InstantTextResult) {
                    String term = ((InstantTextResult) instantSearchResult).getTerm();
                    String id = ((InstantTextResult) instantSearchResult).getId();
                    String blockRef2 = ((InstantTextResult) instantSearchResult).getBlockRef();
                    Intrinsics.checkNotNullExpressionValue(term, "term");
                    searchSuggestionText = new SearchSuggestionText(term, blockRef2, id);
                } else {
                    searchSuggestionText = null;
                }
                if (searchSuggestionText != null) {
                    arrayList.add(searchSuggestionText);
                }
            }
            SearchSuggestionResponse searchSuggestionResponse = new SearchSuggestionResponse(arrayList, instantSearchQueryResultQuery.getQueryId());
            executionContext.markApiRequestCompleted();
            observableEmitter.onNext(searchSuggestionResponse);
            observableEmitter.onComplete();
        } catch (SearchSuggestionException e) {
            String strStringPlus = Intrinsics.stringPlus("Something went wrong while getting Tenzing response: ", e.getMessage());
            logger.error(strStringPlus);
            observableEmitter.onError(new SearchSuggestionServiceException(strStringPlus));
        }
    }

    @VisibleForTesting
    public final SearchSuggestionEntity mapToEntity(Document document, String blockRef) {
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(blockRef, "blockRef");
        if (document instanceof CatalogArtist) {
            return mapToArtist((CatalogArtist) document, blockRef);
        }
        if (document instanceof CatalogTrack) {
            return mapToTrack((CatalogTrack) document, blockRef);
        }
        if (document instanceof CatalogPodcastShow) {
            return mapToPodcastShow((CatalogPodcastShow) document, blockRef);
        }
        return null;
    }

    private final SearchSuggestionEntity mapToPodcastShow(CatalogPodcastShow document, String blockRef) {
        Art artOriginal = document.getArtOriginal();
        ArtURL artURL = artOriginal instanceof ArtURL ? (ArtURL) artOriginal : null;
        String url = artURL == null ? null : artURL.getURL();
        if (document.getTitle() == null || document.getId() == null || url == null) {
            logger.error("A required podcast show entity field is missing. title: " + ((Object) document.getTitle()) + ", id: " + ((Object) document.getId()) + ", imageUrl: " + ((Object) url));
            return null;
        }
        String title = document.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "document.title");
        String id = document.getId();
        Intrinsics.checkNotNullExpressionValue(id, "document.id");
        EntityIdType entityIdType = EntityIdType.PODCAST_SHOW_ID;
        EntityType entityType = EntityType.PODCAST_SHOW;
        SearchFeaturesProvider searchFeaturesProvider = this.searchFeaturesProvider;
        String id2 = document.getId();
        Intrinsics.checkNotNullExpressionValue(id2, "document.id");
        String title2 = document.getTitle();
        Intrinsics.checkNotNullExpressionValue(title2, "document.title");
        String podcastShowContentUrl = searchFeaturesProvider.getPodcastShowContentUrl(id2, title2);
        String provider = document.getProvider();
        String variantId = document.getVariantId();
        ContentTierUtils contentTierUtils = ContentTierUtils.INSTANCE;
        List<String> contentTier = document.getContentTier();
        Intrinsics.checkNotNullExpressionValue(contentTier, "document.contentTier");
        List<ContentTier> listConvertFromSearchContentTierList = contentTierUtils.convertFromSearchContentTierList(contentTier);
        Boolean boolIsHasExplicitLanguage = document.getParentalControls().isHasExplicitLanguage();
        Intrinsics.checkNotNullExpressionValue(boolIsHasExplicitLanguage, "document.parentalControls.isHasExplicitLanguage");
        return new SearchSuggestionEntity(title, blockRef, new SearchEntity(id, entityIdType, entityType, url, podcastShowContentUrl, new EntityProperties((String) null, (String) null, (String) null, (String) null, provider, listConvertFromSearchContentTierList, (ParentalControlsPerContentTier) null, new ParentalControls(boolIsHasExplicitLanguage.booleanValue()), variantId, (Boolean) null, (Boolean) null, (String) null, (Boolean) null, (Integer) null, (List) null, (List) null, (String) null, (Boolean) null, (Boolean) null, 523791, (DefaultConstructorMarker) null), null, 64, null));
    }

    private final SearchSuggestionEntity mapToTrack(CatalogTrack document, String blockRef) {
        Art artOriginal = document.getArtOriginal();
        ArtURL artURL = artOriginal instanceof ArtURL ? (ArtURL) artOriginal : null;
        String url = artURL == null ? null : artURL.getURL();
        if (document.getTitle() == null || document.getAsin() == null || url == null) {
            logger.error("A required track entity field is missing. title: " + ((Object) document.getTitle()) + ", asin: " + ((Object) document.getAsin()) + ", imageUrl: " + ((Object) url));
            return null;
        }
        String title = document.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "document.title");
        String asin = document.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "document.asin");
        EntityIdType entityIdType = EntityIdType.ASIN;
        EntityType entityType = EntityType.TRACK;
        Boolean boolIsHasExplicitLanguage = document.getParentalControls().isHasExplicitLanguage();
        Intrinsics.checkNotNullExpressionValue(boolIsHasExplicitLanguage, "document.parentalControls.isHasExplicitLanguage");
        ParentalControls parentalControls = new ParentalControls(boolIsHasExplicitLanguage.booleanValue());
        ContentTierUtils contentTierUtils = ContentTierUtils.INSTANCE;
        List<String> contentTier = document.getContentTier();
        Intrinsics.checkNotNullExpressionValue(contentTier, "document.contentTier");
        return new SearchSuggestionEntity(title, blockRef, new SearchEntity(asin, entityIdType, entityType, url, null, new EntityProperties(document.getArtistAsin(), document.getArtistName(), document.getAlbumAsin(), document.getAlbumName(), (String) null, contentTierUtils.convertFromSearchContentTierList(contentTier), (ParentalControlsPerContentTier) null, parentalControls, (String) null, (Boolean) null, (Boolean) null, (String) null, (Boolean) null, (Integer) null, (List) null, (List) null, (String) null, (Boolean) null, (Boolean) null, 524048, (DefaultConstructorMarker) null), null, 80, null));
    }

    private final SearchSuggestionEntity mapToArtist(CatalogArtist document, String blockRef) {
        Art artOriginal = document.getArtOriginal();
        ArtURL artURL = artOriginal instanceof ArtURL ? (ArtURL) artOriginal : null;
        String url = artURL == null ? null : artURL.getURL();
        if (document.getName() == null || document.getAsin() == null || url == null) {
            logger.error("A required artist entity field is missing. name: " + ((Object) document.getName()) + ", asin: " + ((Object) document.getAsin()) + ", imageUrl: " + ((Object) url));
            return null;
        }
        String name = document.getName();
        Intrinsics.checkNotNullExpressionValue(name, "document.name");
        String asin = document.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "document.asin");
        return new SearchSuggestionEntity(name, blockRef, new SearchEntity(asin, EntityIdType.ASIN, EntityType.ARTIST, url, null, null, null, 112, null));
    }

    private final InstantTextSpecification getTextSpec() {
        InstantTextSpecification instantTextSpecificationBuild = InstantTextSpecification.builder().withEnabled(Boolean.valueOf(this.uiConfiguration.getInstantSearchConfig().isTextSpecEnabled())).withFields(InstantEntitySpecParams.INSTANCE.getTEXT_FIELD_SPEC()).build();
        Intrinsics.checkNotNullExpressionValue(instantTextSpecificationBuild, "builder()\n              …\n                .build()");
        return instantTextSpecificationBuild;
    }

    @VisibleForTesting
    public final InstantEntitySpecification getEntitySpec() {
        List<String> listEmptyList;
        InstantSearchSuggestionConfig instantSearchConfig = this.uiConfiguration.getInstantSearchConfig();
        ArrayList arrayList = new ArrayList();
        if (this.searchFeaturesProvider.isExplicitLanguageFilterEnabled()) {
            listEmptyList = CollectionsKt__CollectionsJVMKt.listOf(InstantEntitySpecParams.INSTANCE.getEXPLICIT_LANGUAGE_FILTER_FIELD());
        } else {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        if (instantSearchConfig.isArtistSpecEnabled()) {
            InstantDocumentSpecification instantDocumentSpecificationBuild = InstantDocumentSpecification.builder().withType(InstantEntitySpecParams.ARTIST_TYPE_SPEC).withFields(InstantEntitySpecParams.INSTANCE.getARTIST_FIELD_SPEC()).build();
            Intrinsics.checkNotNullExpressionValue(instantDocumentSpecificationBuild, "builder()\n              …                 .build()");
            arrayList.add(instantDocumentSpecificationBuild);
        }
        if (instantSearchConfig.isTrackSpecEnabled()) {
            InstantDocumentSpecification instantDocumentSpecificationBuild2 = InstantDocumentSpecification.builder().withType(InstantEntitySpecParams.TRACK_TYPE_SPEC).withFields(InstantEntitySpecParams.INSTANCE.getTRACK_FIELD_SPEC()).withFilters(listEmptyList).build();
            Intrinsics.checkNotNullExpressionValue(instantDocumentSpecificationBuild2, "builder()\n              …                 .build()");
            arrayList.add(instantDocumentSpecificationBuild2);
        }
        if (instantSearchConfig.isPodcastShowSpecEnabled()) {
            InstantDocumentSpecification instantDocumentSpecificationBuild3 = InstantDocumentSpecification.builder().withType(InstantEntitySpecParams.PODCAST_SHOW_TYPE_SPEC).withFields(InstantEntitySpecParams.INSTANCE.getPODCAST_SHOW_FIELD_SPEC()).withFilters(listEmptyList).build();
            Intrinsics.checkNotNullExpressionValue(instantDocumentSpecificationBuild3, "builder()\n              …                 .build()");
            arrayList.add(instantDocumentSpecificationBuild3);
        }
        if (!arrayList.isEmpty()) {
            return InstantEntitySpecification.builder().withDocumentSpecs(arrayList).build();
        }
        return null;
    }
}
