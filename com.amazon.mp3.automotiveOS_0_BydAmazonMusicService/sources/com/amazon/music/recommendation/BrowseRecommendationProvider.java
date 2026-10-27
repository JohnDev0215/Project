package com.amazon.music.recommendation;

import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.recommendation.widgets.BrowseWidgetRequest;
import com.amazon.music.storeservice.model.BaseRecommendations;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsRequest;
import com.android.volley.VolleyError;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
class BrowseRecommendationProvider implements RecommendationProvider {
    private final Locale locale;
    private final Marketplace marketplace;
    private final String musicTerritory;
    private final RecommendationService service;

    private boolean isInitialRequest(int i, String str) {
        return i < 0 && str == null;
    }

    public BrowseRecommendationProvider(RecommendationService recommendationService, Locale locale, Marketplace marketplace, String str) {
        this.service = recommendationService;
        this.locale = locale;
        this.marketplace = marketplace;
        this.musicTerritory = str;
    }

    public List<BaseRecommendations> getRecommendations(BrowseWidgetRequest browseWidgetRequest) throws VolleyError {
        return getRecommendations(createRequest(browseWidgetRequest));
    }

    @Override // com.amazon.music.recommendation.RecommendationProvider
    public List<BaseRecommendations> getRecommendations(RecommendationRequest recommendationRequest) throws VolleyError {
        return getRecommendations(createRequest((BrowseRequest) recommendationRequest));
    }

    @Override // com.amazon.music.recommendation.RecommendationProvider
    public List<BaseRecommendations> getRecommendationsPage(RecommendationPageRequest recommendationPageRequest) throws VolleyError {
        GetBrowseRecommendationsRequest getBrowseRecommendationsRequestCreateRequest = createRequest((BrowseRequest) recommendationPageRequest.getRequest());
        int nextPageToken = recommendationPageRequest.getNextPageToken();
        String widgetId = recommendationPageRequest.getWidgetId();
        if (!isInitialRequest(nextPageToken, widgetId)) {
            getBrowseRecommendationsRequestCreateRequest.setWidgetIdTokenMap(Collections.singletonMap(widgetId, Integer.valueOf(nextPageToken)));
        }
        return getRecommendations(getBrowseRecommendationsRequestCreateRequest);
    }

    private List<BaseRecommendations> getRecommendations(GetBrowseRecommendationsRequest getBrowseRecommendationsRequest) throws VolleyError {
        return this.service.getBrowseRecommendation(getBrowseRecommendationsRequest).getRecommendations();
    }

    private GetBrowseRecommendationsRequest createRequest(BrowseRequest browseRequest) {
        GetBrowseRecommendationsRequest getBrowseRecommendationsRequest = new GetBrowseRecommendationsRequest();
        if (!StringUtils.isEmpty(this.musicTerritory)) {
            getBrowseRecommendationsRequest.setMusicTerritory(this.musicTerritory);
        } else {
            Marketplace marketplace = this.marketplace;
            if (marketplace != null) {
                getBrowseRecommendationsRequest.setMarketplaceId(marketplace.getObfuscatedId());
            }
        }
        getBrowseRecommendationsRequest.setMinResultsPerWidget(0);
        getBrowseRecommendationsRequest.setMaxResultsPerWidget(Integer.valueOf(browseRequest.getMaxCount()));
        getBrowseRecommendationsRequest.setLang(this.locale.getLanguage());
        getBrowseRecommendationsRequest.setRequestedContent(browseRequest.getRequestedContent());
        getBrowseRecommendationsRequest.setDeviceId(browseRequest.getDeviceId());
        getBrowseRecommendationsRequest.setDeviceType(browseRequest.getDeviceType());
        getBrowseRecommendationsRequest.setAllowedParentalControls(Configuration.getParentalControls(browseRequest.isExplicitFilterEnabled()));
        getBrowseRecommendationsRequest.setMusicRequestIdentityContextToken(browseRequest.getMusicRequestIdentityContextToken());
        return getBrowseRecommendationsRequest;
    }
}
