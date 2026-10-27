package com.amazon.music.recommendation;

import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.storeservice.model.BaseRecommendations;
import com.amazon.music.storeservice.model.GetSimilarityRecommendationsRequest;
import com.android.volley.VolleyError;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
class SimilarityRecommendationProvider implements RecommendationProvider {
    private final Locale locale;
    private final Marketplace marketplace;
    private final String musicTerritory;
    private final RecommendationService service;

    private boolean isInitialRequest(int i, String str) {
        return i < 0 && str == null;
    }

    public SimilarityRecommendationProvider(RecommendationService recommendationService, Locale locale, Marketplace marketplace, String str) {
        this.service = recommendationService;
        this.locale = locale;
        this.marketplace = marketplace;
        this.musicTerritory = str;
    }

    @Override // com.amazon.music.recommendation.RecommendationProvider
    public List<BaseRecommendations> getRecommendations(RecommendationRequest recommendationRequest) throws VolleyError {
        return this.service.getSimilarityRecommendation(createRequest((SimilarityRequest) recommendationRequest)).getRecommendations();
    }

    @Override // com.amazon.music.recommendation.RecommendationProvider
    public List<BaseRecommendations> getRecommendationsPage(RecommendationPageRequest recommendationPageRequest) throws VolleyError {
        GetSimilarityRecommendationsRequest getSimilarityRecommendationsRequestCreateRequest = createRequest((SimilarityRequest) recommendationPageRequest.getRequest());
        int nextPageToken = recommendationPageRequest.getNextPageToken();
        String widgetId = recommendationPageRequest.getWidgetId();
        if (!isInitialRequest(nextPageToken, widgetId)) {
            getSimilarityRecommendationsRequestCreateRequest.setWidgetIdTokenMap(Collections.singletonMap(widgetId, Integer.valueOf(nextPageToken)));
        }
        return this.service.getSimilarityRecommendation(getSimilarityRecommendationsRequestCreateRequest).getRecommendations();
    }

    private GetSimilarityRecommendationsRequest createRequest(SimilarityRequest similarityRequest) {
        GetSimilarityRecommendationsRequest getSimilarityRecommendationsRequest = new GetSimilarityRecommendationsRequest();
        if (!StringUtils.isEmpty(this.musicTerritory)) {
            getSimilarityRecommendationsRequest.setMusicTerritory(this.musicTerritory);
        } else {
            Marketplace marketplace = this.marketplace;
            if (marketplace != null) {
                getSimilarityRecommendationsRequest.setMarketplaceId(marketplace.getObfuscatedId());
            }
        }
        getSimilarityRecommendationsRequest.setMinResultsPerWidget(0);
        getSimilarityRecommendationsRequest.setMaxResultsPerWidget(Integer.valueOf(similarityRequest.getItemsCount()));
        getSimilarityRecommendationsRequest.setLang(this.locale.getLanguage());
        getSimilarityRecommendationsRequest.setAsin(similarityRequest.getAsin());
        getSimilarityRecommendationsRequest.setRequestedContent(similarityRequest.getRequestedContent());
        getSimilarityRecommendationsRequest.setDeviceType(similarityRequest.getDeviceType());
        getSimilarityRecommendationsRequest.setDeviceId(similarityRequest.getDeviceId());
        getSimilarityRecommendationsRequest.setAllowedParentalControls(Configuration.getParentalControls(similarityRequest.isExplicitFilterEnabled()));
        getSimilarityRecommendationsRequest.setMusicRequestIdentityContextToken(similarityRequest.getMusicRequestIdentityContextToken());
        return getSimilarityRecommendationsRequest;
    }
}
